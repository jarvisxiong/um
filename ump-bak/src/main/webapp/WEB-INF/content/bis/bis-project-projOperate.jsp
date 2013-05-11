<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/customInput/customInput.js"></script>
<form action="${ctx}/bis/bis-project!projectOperate.action" method="post" id="projOpertForm">
	<input type ="hidden" name ="floorType" id="floorType" value="${floorType}">
	<input type ="hidden" name ="bisProjectId" id="bisProjectId" value="${bisProjectId}">
	<table class="mainTable" width="100%" style="font-size:12px;" >
		<col width="30%" />
		<col />
		<tr class="mainTr" >
			<s:set name="count" value="0" />
			<s:iterator value="bisFloorList" status="status">
				<s:set name="count" value="#count + 1" />
				<s:if test="#count == 5">
				  <tr>
				</s:if>
					<td align="center" style="padding-top: 8px;">
						<div style="width:140px;height: 100px; background-color: #464646;background-image: url('${ctx}/resources/images/common/FZ_1F.png');" id="${bisFloorId}"  value="${floorNum}" onclick="storeOperator('${bisFloorId}','${bisProjectId}');" ></div>
						<div style="position: fixed;">${floorNum}层</div> 
					</td>
				<s:if test="#count == 8">
				  </tr>
				  <s:set name="count" value="0" />
				</s:if>
					<!--<td align="center">${floorNum}层</td>-->
			</s:iterator>
		</tr>
	</table>
</form>
<div id="projectPanel" style="position:absolute; width:460px; top:90px; right:0px;display:none; background-color:#fff; border:1px solid #000; padding:5px;"></div>
<script type="text/javascript">
//function gotoMap(bisProjectId){
//	$('#bisProjectId').val(bisProjectId);
//	$('#projOpertForm').submit();
//}
function storeOperator(bisFloorId,bisProjectId){
	var url="${ctx}/bis/bis-tenant!storeOperate.action?bisFloorId="+bisFloorId+"&bisProjectId="+bisProjectId;

	parent.TabUtils.newTab("bis-tenant-storeOperate","商铺租户汇总",url,true);
}
</script>