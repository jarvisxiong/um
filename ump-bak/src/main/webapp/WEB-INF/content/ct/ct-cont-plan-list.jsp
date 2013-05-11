<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<form id="ctContPlanForm" action="${ctx}/ct/ct-cont-plan!list.action" method="post">
<input type="hidden" name="ctLedgerId" id="ctLedgerId"	value="${ctLedgerId}">
<div style="padding-left: 5px;">
<div class="clsPlanAndConPlan" style="border-bottom:1px solid;border-bottom-color:#0167a2;">
<div class="clsAddPlan">
<security:authorize ifAnyGranted="A_CT_RESOLVE">
<input type="button" value="新增合约大类" style="width: 100px; height: 28px;" class="btn_green" onclick="addCtContPlan('${ctLedgerId}');"  id="btn_addContPlan"/>


</security:authorize>

	<s:set var="ctPlanCount">
		<s:property value="ctPlanList.size()" />
	</s:set>
<input type="hidden" id="ctPlanCount" value="${ctPlanCount}"/>
</div>
<s:iterator value="ctPlanList" >
	<div class="btn_whites" id="btn_${ctPlanId}" >
	<div id="${ctPlanId}" title='${planName}' class="clsPlan" onclick="queryCtContPlan('${ctPlanId}',$('#btn_${ctPlanId}'));">${planName}</div>
	<div class="close_tab" onclick="deleteplan('${ctPlanId}');" style="display: none;"></div>
	</div>
</s:iterator>
</div>
</div>
<div id="ctContPlanDetail" style="padding-top: 4px;"></div>
</form>

<script type="text/javascript">

function queryCtContPlan(ctPlanId,dom){
	$(dom).removeClass('btn_whites');
	$(dom).addClass('btn_blues').siblings(".btn_blues").removeClass('btn_blues').addClass("btn_whites");
	$(".close_tab").hide();
	$(dom).children(".close_tab").show();
	var data={ctPlanId:ctPlanId};
	TB_showMaskLayer("正在搜索请稍后...");
	$.post("${ctx}/ct/ct-cont-plan!queryCtContPlan.action",
			data,
			function(result) {
				if (result) {
					$("#ctContPlanDetail").html(result);
	            TB_removeMaskLayer();

	            if(!isNum($("#ctPlanCount").val()) || !isBindEvent()){
					$("#btn_addCont").hide();
					}
				
	       }
				 
			});
}
function addCtContPlan(ctLedgerId){
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='ctContPlanNew'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 390,
		height : 100,
		title : "编辑详细信息",
		closeBtn:true,
		afterShow : function() {
			var url = _ctx+"/ct/ct-cont-plan!input.action";
			$.post(url,{ctLedgerId:ctLedgerId}, function(result) {
				$("#ctContPlanNew").html(result);
			});
		},
		handler : function(btn){
			 
			if(btn=='ok'){
				 if($("input[name=ctPlan.planName]").val().trim() ==""){
	                    alert("合约规划名称不能为空！");
	                    $("input[name=ctPlan.planName]").focus();
	                    return;
					  }
					$('#ctContPlanAdd').ajaxSubmit(function(result) { 
						ctContPlanShow($('#ctLedgerId').val());
					});
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["取消",'cancel']]
	});
}
function deleteplan(ctPlanId){
	if(ctPlanId!=null){

		if(!isBindEvent()){
			   alert("不能删除合约规划大类,当前台账信息正在审核中或者已审核通过!");
				return;
			}
		if($("td[ctr=n0]").length>0){
			  alert("不能删除当前合约规划大类,请先删除其子合约!");
				return;
			}
		if(confirm("确定要删除该合约大类吗？")){
			var url=_ctx+"/ct/ct-cont-plan!deletePlan.action";
			$.post(url,{ctPlanId:ctPlanId},
					function(result) {
						if(ctPlanId == result){
							alert("删除成功");
							ctContPlanShow($('#ctLedgerId').val());
						}else{
							alert("删除失败");
							return false;
						}
					});
		}
	}
}
</script>