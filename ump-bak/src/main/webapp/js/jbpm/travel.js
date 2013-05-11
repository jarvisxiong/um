var config;
function saveComment() {
	var comment = $("#comment").val();
	//alert(comment);
	if (comment != '' && (comment != undefined) ) {
		$("#inputFromComment").ajaxSubmit(function(result) {
			$("#divComment").html(result);
			if(config['styleCall'])config['styleCall']();
		});
	}
}

function save(){
	var pdv = new Validate("inputFromTravel");
	if (pdv.validate()) {
	saveComment();
	$("#inputFromTravel").submit();
	}
}
//申请
function apply(){
	var pdv = new Validate("inputFromTravel");
	if (pdv.validate()) {
	saveComment();
	$("#inputFromTravel").attr("action","jbpm-travel-flow!apply.action");
	$("#inputFromTravel").submit();
	}
}
function cancel(taskId,id) {
	saveComment();
	window.location.href =config['ctx']+'/jbpm/jbpm-travel-flow!cancel.action?taskId=' + taskId+"&isDesk="+config['isDesk']+"&id="+id;
}
function openAttachment(title,entityId){
	ymPrompt.win({message:config['ctx']+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=jbpmTravel&filterType=image|office",width:500,height:300,title:'附件管理',iframe:true});
}
function loadAttachment(){
	$.get(
			$("#attach_files_div").attr("href"),
			function(data) {
				$("#attach_files_div").html(data);
			}
	);
}
function doDelete(id){
	window.location.href = 'jbpm-travel-flow!delete.action?isDesk='+config['isDesk']+"&id="+id;
}
function doReturn(pageNo,searchScop){
	var isDesk=config['isDesk'];
	if (isDesk=='1'){
	 window.location.href = 'jbpm-travel-flow!desk.action?isDesk='+config['isDesk']+"&pageApprove.pageNo="+pageNo+"&searchScop="+searchScop;
	}else{
	window.location.href = 'jbpm-travel-flow.action?isDesk='+config['isDesk']+"&pageApprove.pageNo="+pageNo+"&searchScop="+searchScop;
	}
}
//载入审批意见
function loadComment() {
	var data = {
		executionId :config['executionId'],
		statusCd : config['statusCd'],
		taskId : config['taskId'],
		isMyTask :config['isMyTask'] ,
		isMyApprove : config['isMyApprove']
	};
	$.post(config['ctx']+"/jbpm/jbpm-travel-flow!listComments.action", data, function(
			result) {
		$("#divComment").html(result);
		if(config['styleCall'])config['styleCall']();
	});
}
//驳回
function doBack(taskId){
	//saveComment();
	var comment = $("#comment").val();
	if (validateComm(comment)){
	window.location.href = config['ctx']+'/jbpm/jbpm-travel-flow!back.action?taskId='+taskId+'&comment='+comment+'&isDesk='+config['isDesk']+"&id="+config['id'];
	}
}
//通过
function approve(taskId) {
	var comment = $("#comment").val();
	if (validateComm(comment)){
	window.location.href = config['ctx']+'/jbpm/jbpm-travel-flow!process.action?taskId=' + taskId+'&comment='+comment+'&isDesk='+config['isDesk']+"&id="+config['id'];
	}
}
function validateComm(comm){
	var result=false;
	if (comm==''){
		result= confirm('审批意见为空是否继续？');
	}else{
		result= true;
	}
	return result;
}
function wrap(dom){
	$(dom).next().hide();
}
function unwrap(dom){
	$(dom).next().s();
}


