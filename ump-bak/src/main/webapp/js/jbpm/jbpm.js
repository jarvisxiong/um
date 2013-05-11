var config;
function jumpPageApply(pageNo) {
	$("#pageNoApplyHidden").val(pageNo);
	$("#searchApply").ajaxSubmit(function(result) {
		$("#divApply").html(result);
		if(config['styleCall'])config['styleCall']();

	});
}
function jumpPageApprove(pageNo) {
	$("#pageNoApproveHidden").val(pageNo);
	var url=$("#searchApprove").attr("action");
	$("#searchApprove").ajaxSubmit(function(result) {
		$("#divApprove").html(result);
		if(config['styleCall'])config['styleCall']();
	});
}
function jumpPageToApply() {
	var index = $("#pageToApply").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPageApply(index);
	}
}
function jumpPageToApprove() {
	var index = $("#pageToApprove").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPageApprove(index);
	}
}
function listApply() {
	$("#searchApply").ajaxSubmit(function(result) {
			$("#divApply").html(result);
			if(config['styleCall'])config['styleCall']();
		});
	}
function listApprove() {
	$("#searchApprove").ajaxSubmit(function(result) {
		$("#divApprove").html(result);
		if(config['styleCall'])config['styleCall']();
	});
}
//转入报销
function reimburse(id){
	var isDesk=config['isDesk'];
	window.location.href =config['ctx']+'/jbpm/jbpm-travel-flow!reimburse.action?id=' + id+"&isDesk="+isDesk;
}
//打开任务，编辑或浏览
function openTask(statusCd,id,isMyTask,pageNo,searchScop){
	var isDesk=config['isDesk'];
	//4取消,2完成,-1新增,0审批中,2驳回
	//	if ((statusCd==2 || statusCd==-1||isMyTask)&&statusCd!=4){
	if ((isMyTask|| statusCd==-1||statusCd==2)&&statusCd!=4){
		var editUrl=config['ctx']+'/jbpm/jbpm-'+config['module']+'-flow!input.action?id='+id+'&taskId='+$("#taskId_"+id).val()+"&isDesk="+isDesk+"&pageApprove.pageNo="+pageNo+"&searchScop="+searchScop;
		window.location.href = editUrl;
	}else{
		var viewUrl=config['ctx']+'/jbpm/jbpm-'+config['module']+'-flow!view.action?id='+id+'&taskId='+$("#taskId_"+id).val()+"&isDesk="+isDesk+"&pageApprove.pageNo="+pageNo+"&searchScop="+searchScop;
		window.location.href = viewUrl;
		
	}
}
//申请
function apply(){
	var viewUrl=config['ctx']+'/jbpm/jbpm-'+config['module']+'-flow!input.action?taskId='+"&isDesk="+config['isDesk'];
	window.location.href = viewUrl;
}
//发放管理
function sendManage(){
	var viewUrl=config['ctx']+'/jbpm/jbpm-'+config['module']+'-flow!listAll.action?taskId='+"&isDesk="+config['isDesk'];
	window.location.href = viewUrl;
}
function loadApply() {
	var jbpmCd = config['filter_LIKES_travelCd'];
	var data = {
			isDesk : config['isDesk'],
			'page.pageSize' : config['pageSize']
		};
	if (!isEmpty(jbpmCd)) {
		data['filter_LIKES_travelCd']=jbpmCd;
	}
	$.post(config['ctx'] + '/jbpm/jbpm-' + config['module']
			+ '-flow!listApply.action', data, function(result) {
		$("#divApply").html(result);
		if (config['styleCall'])
			config['styleCall']();
	});
}
function loadApprove(pageNo,searchScop){
	var data = {
			isDesk : config['isDesk'],
			'page.pageSize' : config['pageSize'],
			'pageApprove.pageNo':isEmpty(pageNo)?1:pageNo,
			searchScop:isEmpty(searchScop)?0:searchScop
		};
	$.post(config['ctx']+'/jbpm/jbpm-'+config['module']+'-flow!listApprove.action',data, function(result) {
		$("#divApprove").html(result);
		if(config['styleCall'])config['styleCall']();
	});
}
function isEmpty(str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
};