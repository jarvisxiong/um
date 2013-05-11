function getProjectRel(projectCd){
	if(projectCd){
		var url = _ctx + "/plan/plan-exec-plan!getCenterPlanRel.action?projectCd=" + projectCd;
		window.parent.TabUtils.newTab("work_plan_exec_rel_list","地产计划",url,true);
	}
}
function getCenterRel(centerCd){
	if(centerCd!=""){
		var url =_ctx + "/plan/plan-exec-plan!getCenterPlanRel.action?centerCd="+"11";
		window.parent.TabUtils.newTab("work_plan_cen_rel_list","中心计划",url,true);
	}else{
		alert("请点击中心");
	}
}