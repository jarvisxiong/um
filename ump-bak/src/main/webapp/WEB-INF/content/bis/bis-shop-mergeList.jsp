<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<style type="text/css">
.table_pager {
	float: right;
	margin: 5px 0;
}
.btn_red_50_20{
	width: 50px;
	height: 20px;
	line-height: 22px;
	background-color:rgb(172, 39, 39);
	color: #FFF;
	cursor:pointer;
	text-align: center;
}
</style>
<div id="TableDiv"  style="height: 100%;width:100% padding-left: 5px; overflow: hidden;">
	<table style="width:100%;" id="table1" class="content_table">
	  <thead>
	        <tr class="header">
	            <th align="center"  width="20" style="background-image: url('');">
	            	<input type="checkbox" onclick="checkAll(this);" id="allFilesCheckBox" style="margin-bottom: 2px;margin-left: 9px;" name="allFilesCheckBox">
	            </th>
	            <th align="center"  width="80">中文名称</th>
	            <th align="center"  width="60">英文名称</th>
	            <th align="center"  width="60">经营性质</th>
	            <th align="center"  width="100">公司名称</th>
	        <%--     <th align="center"  >总部地址</th> --%>
	            <th align="center"  width="60">业务员</th>
	         <%--   <th align="center"  width="60">建档时间</th>  --%>
	            <th align="center"  width="40">状态</th>
			    <%--   
			    <th align="center"  width="70">操作</th>  
			    --%>
	        </tr>
	  </thead>
	  <s:iterator value="page.result">
	  <tr class="mainTr" style="height: 40px;">
	    <td><input type="checkbox" id="${bisShopId}_checkBox" value="${bisShopId}" recordversion="3" name="allFilesCheckBox" style="margin-left: 10px;"></td>
	    <td onclick="doMergeShop('${bisShopId}');">${nameCn}</td>
	    <td onclick="doMergeShop('${bisShopId}');">${nameEn}</td>
	    <td onclick="doMergeShop('${bisShopId}');"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" value="manageCd" /></td>
	    <td onclick="doMergeShop('${bisShopId}');">${companyName}</td>
	   <%-- <td onclick="doMergeShop('${bisShopId}');">${hqAddr}</td> --%>
	  <td onclick="doMergeShop('${bisShopId}');"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator"))%></td> 
	    
	    <%--  <td onclick="doMergeShop('${bisShopId}');"><s:date name ="createdDate" format="yy-MM-dd"/></td>
	    --%>
	     <s:if test="bisShopAudit==3">
	     	<td>
	     		<div style="background-color:#a4b45f;width:5px;height:36px;float:left;margin-right: 3px;"></div>
	     		<div style="padding-top: 10px;">已审核</div>
	     	</td>
         </s:if>
	     <s:elseif test="bisShopAudit==2">
	     	<td>
	     		<div style="background-color:#0167A2;width:5px;height:36px;float:left;margin-right: 3px;"></div>
	     		<div style="padding-top: 10px;">预审核</div>
	     	</td>
	     </s:elseif>
	     <s:elseif test="bisShopAudit==1">
	     	<td>
	     		<div style="background-color:#0167A2;width:5px;height:36px;float:left;margin-right: 3px;"></div>
	     		<div style="padding-top: 10px;">待审核</div>
	     	</td>
	     </s:elseif>
	     <s:else>
	     	<td>
		     	<div style="background-color:#f45614;width:5px;height:36px;float:left;margin-right: 3px;"></div>
	     		<div style="padding-top: 10px;">未提交</div>
			</td>
		 </s:else>
	    <%-- 
	    <td>
	    <s:if test="creator==nowUiid">
	    <button type="button" id="DelBtn" class="btn_red_50_20" onclick="doDeleteShop('${bisShopId}');">删除</button>
	    </s:if>
	    </td>
	     --%>
	    </tr>
	  </s:iterator>
	</table>
</div>
<table style="width:100%;margin-bottom: 10px;">
<tr>
 <td><input type="button" class="btn_blue" onclick="merge('${mergeId}');" value="合并" style="margin-left: 12px;margin-top: 5px;"/></td>
 <td align="right">
<div class="table_pager">
	<p:page />
</div>
 </td>
</tr>
</table>
<script type="text/javascript">
//跳转至给定的页面
function jumpPageTo(){
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
</script>