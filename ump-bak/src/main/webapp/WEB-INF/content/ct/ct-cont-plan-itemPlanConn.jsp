<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%><html>
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<textarea rows="" cols="" id="treeJson" class="chkbxHide">${ctDismantleJson}</textarea>

<div style='margin-right: 5px; margin-left: 5px;'>
<div style="height: 3x;">&nbsp;&nbsp;</div>
<div class='txtRed' style='vertical-align: bottom;color: red;'>*本次可分解最高金额为:${totalAmt}元</div>
<div style="height: 3x;">&nbsp;&nbsp;</div>
<div style='vertical-align: middle; height: 30px;display:none;'>
<input type="button" class="btnStyle handler" value="已选科目"></input>
<input type="button" class="btnStyle handler" value="查看已选科目" style='width: 120px'> </input>
</div>
<form action="${ctx}/ct/ct-cont-plan!saveItemPlanConn.action" method="post" id="saveItemPlanConn">
<div style="width: 498px; height: 320px; border: 1px gray solid;">
<input type="hidden" id="itemSelec" value="${itemSelec}" name="itemSelec"> 
<input	type="hidden" id="ctLedgerId" value="${ctLedgerId}" name="ctLedgerId"> 
	<input type="hidden" value="${totalAmt}" id="totalAmt" name="totalAmt">
<div id="ctItemTreePanel" style="width: 200px; height: 100%; float: left; overflow: auto; border-right: 1px gray solid;"></div>

<div style="width: 280px; float: left; height: 100%; overflow: auto; border: 1px soild #0167A2; margin-left: 5px;">
		<table class="shop-table" style="min-width: 280px;"  >
			  <col width="90" style="text-align: right;"/>
			  <col />
			 <tr>
			  <td style="text-align:right" style="text-align:right;width:100px;">合约规则大类：</td>
			  <td>
				<select id="supExamResu" style='width: 180px' onchange="btRelationContPlanDetails(this)" id="ctplanSelect">
					<option value="">请选择</option>
					<s:iterator value="ctPlanList" var="stat">
						<option value="${stat.ctPlanId}">${stat.planName}</option>
					</s:iterator>
				</select>
			   </td>
			</tr>
			<div style="height: 20px;width:160px;">&nbsp;&nbsp;</div>
			<tr>
			<td style="text-align:right;width:100px;">
			关联合约规划：
			</td>
			<td>
			 <select style='width: 180px' id="ctContPlanSelect" name="ctContPlanSelect" onchange="contPlanDetail(this.value);">
				<option value="" >请选择</option>
			
			</select>
			 </td>
			</tr>
			<tr>
			<td style="text-align:right">
			调整金额：
			</td>
			<td>
			      <input type="text" style="height: 20px;width:180px;" maxlength="15" name ="changeAmt" id="changeAmt" alt="amount">
			 </td>
			</tr>
		</table>
<div id="contPlanDetail"></div>
</div>
</div>
</form>
</div>

</body>
</html>

<script>
$(function(){
	$('input[alt="amount"]').live('keyup',function(){
		clearNoNum_1(this);
		if($('.percent').val()>100){
			this.value=0;
		}
	});
});
	//主页面
	//加载区域项目树
	var gTreePanel;
	loadAreaProjectTree();
	function loadAreaProjectTree() {
		var url = _ctx + '/ct/ct-ledger!getAreaProjectTree.action';
		var _jsontext = $("#treeJson").val();
		_jsontext = _jsontext.substring(1, _jsontext.length);
		_jsontext = _jsontext.substring(0, _jsontext.length - 1);

		gTreePanel = new TreePanel( {
			renderTo : 'ctItemTreePanel',
			'root' : eval('(' + _jsontext + ')'),
			'ctx' : _ctx
		});
		gTreePanel.render();
		gTreePanel.isExpendSelect = true;
		gTreePanel.on(function(node) {
			var nodeType = node.attributes.nodeType;
			//若非根节点 
				if ("0" != nodeType) {
					if (node.isExpand) {
						node.collapse();
					} else {
						node.expand();
					}
				}
			});
	}
	function btRelationContPlanDetails(selectEle) {
    var _ctPlanId = $(selectEle).val();
	var _url =_ctx + "/ct/ct-cont-plan!getCtContPlan.action";
	$.ajax({
				url : _url,
				type : "POST",
				data : "ctPlanId=" + _ctPlanId ,
				success : function(responseText) {
		       $("#ctContPlanSelect").empty();
					var _jsonData = toJson(responseText);
					
					if (_jsonData != undefined && _jsonData.status != undefined) {

						if (_jsonData.status != true) {
							alert("error");
							return;
						}
					} else {

                     $.each(_jsonData,function(i,data){
                    	 if(i==0){
                    	  $("#ctContPlanSelect").append("<option value=''>请选择</option>");
                    	 }
                    	  $("#ctContPlanSelect").append("<option title='"+data.ledgerTypeCd+"' value='"+data.ctContPlanId+"'>"+data.ledgerTypeCd+"</option>");
                    	  
                         });
					}
				},
				error : function(responseText) {

				}
			});
	}
	function contPlanDetail(ctContPlanId){
			var data={ctContPlanId:ctContPlanId};
			$.post(_ctx+"/ct/ct-cont-plan!contPlanDetail.action",
					data,
					function(result) {
						TB_removeMaskLayer();
						if (result) {
							$("#contPlanDetail").html(result);
						}
						 
					}
				);
	}
</script>