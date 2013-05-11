<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<script type="text/javascript">

function doBizHtlDelete(id){
	if(id!=null){
		if(confirm("确定要删除该条记录吗？")){
			var url="${ctx}/biz/biz-htl-hoe!delete.action";
			$.post(url,{id:id},
					function(result) {
				if('success' == result){
					bizHtlSearch();
				}else{
					alert("删除失败");
					return false;
				}
					});
		}
	}
}


</script>
<form action="${ctx}/biz/biz-htl-hoe!bizHtlDetail.action" method="post" id="bizHtlListForm">
<input type="hidden" name="id" id="id">
<div id="TableDiv"  style="height: 100%;width:100% padding-left: 5px; overflow: hidden;">
	<table width="100%" id="result_table" class="content_table" >
	  <thead>
	        <tr class="header">
	            <th scope="col" width="85px;" align="left" style="background: none;">酒店名称</th>
	            <th scope="col" align="left" width="90px;"  >品名类别</th>
	            <th scope="col" align="left" >供应商名称</th>
	            <th scope="col" align="left" >HOE名称</th>
	            <th scope="col" align="left" >规格型号</th>
	            <th scope="col" align="left" width="50px;">数量</th>
	            <th scope="col" align="left" width="50px;">单价</th>
	            <th scope="col" align="left" width="50px;">总价</th>
	            <th scope="col" align="left" width="30px;">序号</th>
	            <th scope="col" align="left" width="30px;">附件</th>
	            <security:authorize ifAnyGranted="A_BIZ_HOTEL_ADMIN">
	            	<th scope="col" align="left" width="50px;" >操作</th>
	            </security:authorize>
	        </tr>
	  </thead>
	  <tbody>
			  <s:iterator value="page.result">
				    <tr class="mainTr" >
		  	    		<td align="left" class="pd-chill-tip" title='${hotelName}'  onclick="bizHtlEdit('${bizHtlHoeId}');"  ><div class="ellipsisDiv_full" >${hotelName}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${hoeTypeName}'  onclick="bizHtlEdit('${bizHtlHoeId}');"  ><div class="ellipsisDiv_full" >${hoeTypeName}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${supName}' onclick="bizHtlEdit('${bizHtlHoeId}');"  ><div class="ellipsisDiv_full" >${supName}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${hoeName}' onclick="bizHtlEdit('${bizHtlHoeId}');"  ><div class="ellipsisDiv_full" >${hoeName}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${hoeModel}' onclick="bizHtlEdit('${bizHtlHoeId}');"  ><div class="ellipsisDiv_full" >${hoeModel}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${hoeAmount}'  onclick="bizHtlEdit('${bizHtlHoeId}');"  ><div class="ellipsisDiv_full" >${hoeAmount}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${singelPrice}'  onclick="bizHtlEdit('${bizHtlHoeId}');"  ><div class="ellipsisDiv_full" >${singelPrice}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${totalPrice}'  onclick="bizHtlEdit('${bizHtlHoeId}');"  ><div class="ellipsisDiv_full" >${totalPrice}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${sequenceNo}'  onclick="bizHtlEdit('${bizHtlHoeId}');"  ><div class="ellipsisDiv_full" >${sequenceNo}</div></td>
		  	    		<td align="left">				  
			  	    		<div class="atta_readOnly" style="float:left;cursor: pointer;"  onclick="showAttachment('${bizHoeAttaId}')">
						   <s:if test="bizHoeAttaId ==''||bizHoeAttaId ==null">
							<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
							</s:if>
							<s:else>
							<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" />
							</s:else>
					 		</div>
				 		</td>
		  	    		<security:authorize ifAnyGranted="A_BIZ_HOTEL_ADMIN">
		  	    		<td><input id="delBtn" name="delBtn"  class="btn_red" onclick="doBizHtlDelete('${bizHtlHoeId}');" type="button" value ="删除"/></td>
		  	    		</security:authorize>
			    	</tr>
			  </s:iterator>
		</tbody>
	</table>
		<div class="table_pager" style="margin-top:5px;">
			<p:page pageInfo="page"/>
		</div>
</div>
</form>
<script language="javascript">
//$(function(){
//	<security:authorize ifAnyGranted="A_BIZ_RELA_QUERY">
	//$('tbody tr td').removeAttr('onclick');
//	</security:authorize>
//});
</script>