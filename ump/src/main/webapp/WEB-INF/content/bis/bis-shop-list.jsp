<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<style type="text/css">
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
	            <th align="center"  width="30" style="background-image: url('');">&nbsp;</th>
	            <th align="center"  width="70" style="background-image:url('');">中文名称</th>
	            <th align="center"  width="60">英文名称</th>
	            <th align="center"  width="60">经营性质</th>
	            <th align="center"  width="100">公司名称</th>
	            <th align="center"  width="120" >总部地址</th>
	            <th align="center"  width="50">业务员</th>
	            <th align="center"  width="60">建档时间</th>  
	            <th align="center"  width="50">状态</th>
			    <security:authorize ifAnyGranted="A_SHOP_DEL">
			    <th align="center"  width="40">操作</th>  
			    </security:authorize>
	        </tr>
	  </thead>
	  <s:iterator value="page.result">
	  <tr class="mainTr" style="height: 40px;">
	    <td align="center">
	    		<s:if test="attention==1">
	    			<img src="${ctx}/pics/email/attention.gif" bsId="${bisShopId}" class="attention">
	    		</s:if>
	    		<s:else>
	    			 <img src="${ctx}/pics/email/attention_cancel.gif" bsId="${bisShopId}"  class="attention">
	    		</s:else>
	    </td>
	    <td onclick="doBisShop('${bisShopId}');" >${nameCn}</td>
	    <td onclick="doBisShop('${bisShopId}');" >${nameEn}</td>
	    <td onclick="doBisShop('${bisShopId}');" ><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" value="manageCd" /></td>
	    <td onclick="doBisShop('${bisShopId}');" >${companyName}</td>
	    <td onclick="doBisShop('${bisShopId}');" >${hqAddr}</td>
	    <td onclick="doBisShop('${bisShopId}');" ><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("salesman"))%></td> 
	    <td onclick="doBisShop('${bisShopId}');" ><s:date name ="createdDate" format="yy-MM-dd"/></td>
	    <s:if test="deleteBl==2"><td>已合并</td></s:if>
	    <s:else>
		     <s:if test="bisShopAudit==3">
		     	<td onclick="doBisShop('${bisShopId}');" valign="middle">
		     		<div style="background-color:#a4b45f;width:5px;height:36px;float:left;margin-right: 3px;"></div>
		     		<div style="padding-top: 10px;">已审核</div>
		     	</td>
		     </s:if>
		     <s:elseif test="bisShopAudit==2">
		     	<td onclick="doBisShop('${bisShopId}');">
		     		<div style="background-color:#0167A2;width:5px;height:36px;float:left;margin-right: 3px;"></div>
		     		<div style="padding-top: 10px;">预审核</div>
		     	</td>
		     </s:elseif>
		     <s:elseif test="bisShopAudit==1">
		     	<td onclick="doBisShop('${bisShopId}');">
		     		<div style="background-color:#0167A2;width:5px;height:36px;float:left;margin-right: 3px;"></div>
		     		<div style="padding-top: 10px;">待审核</div>
		     	</td>
		     </s:elseif>
		     <s:else>
		     	<td onclick="doBisShop('${bisShopId}');">
		     		<div style="background-color:#f45614;width:5px;height:36px;float:left;margin-right: 3px;"></div>
		     		<div style="padding-top: 10px;">未提交</div>
	     		</td>
		     </s:else>
	     </s:else>
	   <security:authorize ifAnyGranted="A_SHOP_DEL">
	    <td align="center">
		   <s:if test="bisShopAudit!=3"> 
			   <span onclick="doDeleteShop('${bisShopId}');" id="DelBtn">
			   		<button class="btn_red_50_20" type="button">删除</button>
			   </span>
		   </s:if> 
	    </td>
	   </security:authorize>
	    </tr>
	  </s:iterator>
	</table>
</div>
<style type="text/css">
.table_pager {
	float: right;
	margin: 5px 0;
}
</style>
<div class="table_pager">
	<p:page />
</div>
<security:authorize ifAnyGranted="A_SHOP_ATTENTION">
<script type="text/javascript">
$(function () {
	$(".attention").click(function () {
		$.post("${ctx}/bis/bis-shop!updateAttention.action",{id:$(this).attr("bsId")}, function(result){
			if(result==="1"){
				queryBisShopList();
			}
		});
	});
});
//跳转至给定的页面
function jumpPageTo(){
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
</script>
</security:authorize>