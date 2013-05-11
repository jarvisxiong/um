<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<script type="text/javascript">

function doBizRelaReduct(id){
	if(id!=null){
		if(confirm("确定要还原该条记录吗？")){
			var url="${ctx}/biz/biz-rela-lib!relaLibReduction.action";
			$.post(url,{id:id},
					function(result) {
				if('success' == result){
					alert("还原成功");
					bizRelaSearchDel();
				}else{
					alert("还原失败");
					return false;
				}
					});
		}
	}
}

</script>
<div id="TableDiv"  style="height: 100%;width:100% padding-left: 5px; overflow: hidden;">
	<table width="100%" id="result_table" class="content_table" >
	  <thead>
	        <tr class="header">
	            <th scope="col" align="left" width="50px;"  style="background: none;">省份</th>
	            <th scope="col" align="left" >地区</th>
	            <th scope="col" align="left" width="50px;" >姓名</th>
	            <th scope="col" align="left" >单位</th>
	            <th scope="col" align="left" >职位</th>
	            <th scope="col" align="left" >通讯地址</th>
	            <th scope="col" align="left" width="80px;">手机</th>
	            <th scope="col" align="left" >中心</th>
	            <th scope="col" align="left" width="35px;">类别</th>
	            <th scope="col" align="left" width="55px;" >操作</th>
	        </tr>
	  </thead>
	  <tbody>
			  <s:iterator value="bizRelaDelLis">
				    <tr class="mainTr" >
		  	    		<td align="left" class="pd-chill-tip" title='${relaProvince}'  ><div class="ellipsisDiv_full" >${relaProvince}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${relaArea}' ><div class="ellipsisDiv_full" >${relaArea}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${relaName}'  ><div class="ellipsisDiv_full" >${relaName}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${relaUnit}' ><div class="ellipsisDiv_full" >${relaUnit}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${relaPos}' ><div class="ellipsisDiv_full" >${relaPos}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${relaAddress}'  ><div class="ellipsisDiv_full" >${relaAddress}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${relaMobile}'  ><div class="ellipsisDiv_full" >${relaMobile}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${submitCenterCd}' ><div class="ellipsisDiv_full" >${submitCenterCd}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapRelaType()" value="relaTypeCd"/>'  ><div class="ellipsisDiv_full" ><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapRelaType()" value="relaTypeCd"/></div></td>
		  	    		<td><input id="delBtn" name="delBtn"  class="btn_green" onclick="doBizRelaReduct('${bizRelaLibDelId}');" type="button" value ="还原"/></td>
			    	</tr>
			  </s:iterator>
		</tbody>
	</table>
		<div class="table_pager" style="margin-top:5px;">
			<p:page pageInfo="pageDeL"/>
		</div>
</div>
<script language="javascript">
//$(function(){
//	<security:authorize ifAnyGranted="A_BIZ_RELA_QUERY">
	//$('tbody tr td').removeAttr('onclick');
//	</security:authorize>
//});
</script>