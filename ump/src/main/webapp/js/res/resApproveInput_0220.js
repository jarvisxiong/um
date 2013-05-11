	var isUploaded=false;
	var backUrl=_ctx+'/res/res-approve-info!back.action';
	var agreeUrl=_ctx+'/res/res-approve-info!agree.action';
	
	function initSrh2Form(formId){
		$('.srh_hidden').clone().appendTo("#"+formId);//templetForm
	}
	function getValidValue(value){
		if(typeof(value)=='undefined'){
			value='';
		}
		return value;
	}

	function initSrhData(){
		var data={
				resAuthTypeCd:curAuthTypeCd,
				'page.pageNo':getValidValue($('#srh_pageNo').val()),
				selectedOrderBy:getValidValue($('#srh_selectedOrderBy').val()),
				selectedOrder:getValidValue($('#srh_selectedOrder').val()),
				listMode:getValidValue($('#srh_listMode').val()),
				qsCondition:getValidValue($('#srh_qsCondition').val()),
				selectNodeCd:getValidValue($('#srh_selectNodeCd').val()),
				selectOpinion:getValidValue($('#srh_selectOpinion').val()),
				approveCdSrh:getValidValue($('#srh_approveCd').val()),
				filter_LIKES_statusCd:getValidValue($('#srh_filter_LIKES_statusCd').val()),
				filter_GED_startDate:getValidValue($('#srh_filter_GED_startDate').val()),
				filter_LTD_startDate:getValidValue($('#srh_filter_LTD_startDate').val()),
				filter_LIKES_landproject:getValidValue($('#srh_filter_LIKES_landproject').val()),
				filter_LIKES_titlename:getValidValue($('#srh_filter_LIKES_titlename').val()),
				filter_LIKES_approveUserName:getValidValue($('#srh_auditorUserNames').val()),
				filter_LIKES_approveUserCd:getValidValue($('#srh_auditorUserCds').val()),
				filter_LIKES_userName:getValidValue($('#srh_creatorUserNames').val()),
				filter_LIKES_userCd:getValidValue($('#srh_creatorUserCds').val()),
				filter_LIKES_authTypeName:getValidValue($('#srh_filter_LIKES_authTypeName').val()),
				filter_LIKES_authTypeCd:getValidValue($('#srh_filter_LIKES_authTypeCd').val()),
				filter_LIKES_randomNo:getValidValue($('#srh_filter_LIKES_randomNo').val()),
				filter_LIKES_displayNo:getValidValue($('#srh_filter_LIKES_displayNo').val()),
				quicksearchValue:getValidValue($('#srh_quicksearchValue').val()),
				moduleTypeCdSrh:getValidValue($('#srh_moduleTypeCd').val())
				};
		return data;
	}
	function doReturn(){
		var isModified= eval($("#isModified").val());
		if (isModified){
			alert("内容已更改，请先保存");
			return false;
		}
		var data=initSrhData();
		TB_showMaskLayer("正在返回...",5000);
		$.post(_ctx+"/res/res-approve-info!load.action",data,
			function(result) {
				TB_removeMaskLayer();
			 	$("#content").html(result);
			 	initSearchCondition();
			 	resetLeftPanel();
			 	$("#inputSearchVal").focus();
		});
	}
	function doSave(){
		$(document).click();
		TB_showMaskLayer('正在执行...',5000);
		initSrh2Form("templetForm");
		if (content_editor){
			$('textarea.xheditor-simple').val(content_editor.getSource());
		}
		$("#templetForm").ajaxSubmit(function(result) {
			 $("#content").html(result);
			 TB_removeMaskLayer();
		});	
	}
	/**
	 * 更改人：qilb 5/7/2012
	 * 增加合同文本库与合同编号一致性判断
	 * @return
	 */
	function doSubmit(){
		$(document).click();
		//是否判断合同文本库编号与合同库编号
		if($("#isJudageCtLedgerCons").val() == "1"){			
			if($("#contNo").val() != $("#contractNo").val()){				
				//alert('合同文本库编号和合同台账编号不一致\n请先增加一个合同台账或合同文本！');
				//$("#contractNo").focus();
				//return;
			}			
		}
		//end
		var pdv = new Validate("form_user");
		if (pdv.validate()) {
			TB_showMaskLayer('正在执行...',5000);
			initSrh2Form("form_user");
			$("#form_user").ajaxSubmit(function(result) {
				 $("#content").html(result);
				 resetLeftPanel();
				 TB_removeMaskLayer();
			});
		}
	}
	function doEdit() {
		var data={
			id : $("#resApproveInfoId").val()
		};
		if(curAuthTypeCd){
			data.resAuthTypeCd=curAuthTypeCd;
		}
		TB_showMaskLayer("正在查看明细...",5000);
		
		$.post(_ctx + "/res/res-approve-info!input.action",data , function(result) {
			TB_removeMaskLayer();
			$("#content").html(result);
			// 修复bug
			$('#pdChilltip').remove();
			resetLeftPanel();
		});
	}
	function doLoadUser(){
		//商业合同申请前验证 Add By baolm
		if (typeof beforeApply != 'undefined' && beforeApply instanceof Function) {  
			$.ajaxSettings.async = false;
			var flag = beforeApply();
			$.ajaxSettings.async = true;
			if(flag=='0') {
				return;
			}
		}
		$(document).click();
		if (content_editor){
			$('textarea.xheditor-simple').val(content_editor.getSource());
		}
		var validAuditor = validateFileAuditor();
		if(!validAuditor){
			ymPrompt.alert({message:'请先添加审批人！',title:"提示",width:220,height:180});		
			return false;
		}
		var pdv = new Validate("templetForm");
		if (pdv.validate()) {
			//合同费用，判断预算内、预算外必选
			var outFlag=$("#outFlag").attr('checked');
			var inFlag=$("#inFlag").attr('checked');
			var salaryFlag=$("#salaryFlag").attr('checked');
			if (typeof (salaryFlag) === "undefined" ){
				if(inFlag==false && outFlag== false){
					ymPrompt.alert({message:'请选择预算内或者预算外！',title:"提示",width:250,height:180});
					return ;
				}
			}else{
				if(inFlag==false && outFlag== false && salaryFlag==false ){
					ymPrompt.alert({message:'请选择预算内、预算外或者员工工资！',title:"提示",width:300,height:180});
					return ;
				}
			}
			
			TB_showMaskLayer('正在执行...',5000);
			$("#templetForm").attr("action",_ctx+"/res/res-approve-info!user.action");
			if ($("#templetCd").val()=='9' || $("#templetCd").val()=='199' || $("#templetCd").val()=='288' || $("#srh_moduleTypeCd").val()=='2'){
				initSrh2Form("templetForm");
			}
			$("#templetForm").ajaxSubmit(function(result) {
				if ($("#templetCd").val()=='9' || $("#templetCd").val()=='199' || $("#templetCd").val()=='288' || $("#srh_moduleTypeCd").val()=='2'){
				 $("#content").html(result);
				}else{
				 $("#div_approve_user").html(result);
				 $("#btn_loadUser").hide();
				 $("#btn_edit").show();
				 $("#btn_apply").show();
				 $("#resApproveInfoId").val($("#resApproveInfoId_c").val());
				 $("#recordVersion").val($("#recordVersion_c").val());
				 disapledAll();
				}
				 resetLeftPanel();
				 $("#templetForm").attr("action",_ctx+"/res/res-approve-info!save.action");
				 TB_removeMaskLayer();
			});
		}
	}
	function process(url,fn,remark){
//		var rejectTo=$("#idRejectTo").val();
		var addNode=$("#idAddNode").val();
		var data=initSrhData();
		data.id=$("#resApproveInfoId").val();
		data.approveRemark=$("#approveRemark").val();
		if(isNotEmpty(addNode)){
			data["addNode"] = addNode;
		}
		if(isNotEmpty(rejectTo)){
			data["rejectTo"] = rejectTo;
		}
		if(isEmpty($("#approveRemark").val())){
			data["approveRemark"] = remark;
		}
		var isEdit=$("#isEdit").val();
		if (isEdit=='true'&& url!=backUrl){
			data.isEdit=true;
			var pdv = new Validate("templetForm");
			if (pdv.validate()) {
			TB_showMaskLayer('正在执行...',5000);
			for(var i in data){
				if (i!='id'&&i!='resAuthTypeCd'){
				$("#templetForm").append("<input type='hidden' name='"+i+"' value='"+data[i]+"' />");
				}
			}
			$("#templetForm").attr("action",url);
			$("#templetForm").ajaxSubmit(function(result) {
				if(result=='fail'){
					ymPrompt.errorInfo({message:'操作失败,您无权处理该记录!',title:"提示",width:220,height:180});
				}else{
					$("#content").html(result);
					resetLeftPanel();
				 	$("#inputSearchVal").focus();
				}
				if (fn) fn();
				TB_removeMaskLayer();
			});
			}
		}else{
			//合同跟踪人确认
			$(".param").each(function(i,dom){
				//alert($(dom).attr("name")+":"+$(dom).val());
				data[$(dom).attr("name")]=$(dom).val();
			});
			TB_showMaskLayer('正在执行...',5000);
			//return false;
			$.post(url,data,
				function(result) {
					if(result=='fail'){
						ymPrompt.errorInfo({message:'操作失败,您无权处理该记录!',title:"提示",width:220,height:180});
					}else{
						$("#content").html(result);
						resetLeftPanel();
					 	$("#inputSearchVal").focus();
					}
					if (fn) fn();
					TB_removeMaskLayer();
			});
		}
	}
	
	function doRollback(){
		ymPrompt.confirmInfo({message:'确认追回记录？',title:'追回',winPos:[300,100],handler:function (tp){
			if (tp=='ok'){
			TB_showMaskLayer('正在执行...',5000);
			var data=initSrhData();
			data.id=$("#resApproveInfoId").val();
			data.recordVersion=$("#recordVersion").val();
			$.post(_ctx+"/res/res-approve-info!rollback.action",data,
				function(result) {
					if(result=='fail'){
						ymPrompt.alert({message:'撤回失败，对方已经签批!',title:"提示",width:220,height:180,winPos:[300,100]});
					}else{
						 $("#content").html(result);
						 resetLeftPanel();
					}
					TB_removeMaskLayer();
			});
			}
		}});
	}
	
	function back2Last(){
		ymPrompt.confirmInfo({message:'确认追回记录？',title:'追回',winPos:[300,100],handler:function (tp){
			if (tp=='ok'){
			TB_showMaskLayer('正在执行...',5000);
			var data=initSrhData();
			data.id=$("#resApproveInfoId").val();
			data.recordVersion=$("#recordVersion").val();
			$.post(_ctx+"/res/res-approve-info!back2Last.action",data,
				function(result) {
					if(result=='fail'){
						ymPrompt.alert({message:'撤回失败',title:"提示",width:220,height:180,winPos:[300,100]});
					}else{
						 $("#content").html(result);
						 resetLeftPanel();
					}
					TB_removeMaskLayer();
			});
			}
		}});
	}

	function doDelete(){
		ymPrompt.confirmInfo({message:'确认删除记录？',title:'删除',winPos:[300,100],handler:function (tp){
			if (tp=='ok'){
				TB_showMaskLayer('正在执行...',5000);
				var data=initSrhData();
				data.id=$("#resApproveInfoId").val();
				$.post(_ctx+"/res/res-approve-info!delete.action",data,
						 function(result) {
						 $("#content").html(result);
						 resetLeftPanel();
						 TB_removeMaskLayer();
				});
			}
		}});
	}
	function validateFileAuditor(){
		var ret = true ;
		var auditor = $('#templetForm input.auditPerson');
		if(auditor.length===1){
			ret =  false;	
		}else{
			var i = 1;
			for(;i<auditor.length;i++){
				if(isEmpty(auditor[i].value)){
					ret = false ; 
					break;
				}
			}
		}
		return ret;
	}
	function openMeeting(){
		window.parent.TabUtils.newTab('144','会议室预定',_ctx+'/oa/oa-meeting-room-res!main.action');
	}
	function doMeeting(){
		doProcess(agreeUrl,"完成",openMeeting);
		
	}
	function doComplete(){
		doProcess(agreeUrl,"同意");
	}
	
	//发起决策会,上会
	function doProcessJcw(){
		ymPrompt.confirmInfo({message:'确认发起决策会？',title:"上会",handler:function (tp){
			if (tp=='ok'){
				$("#idAddNode").val('toMeeting');
				process(agreeUrl,null,"发起决策会");
			}
		}});
		
	}
	//给执行总裁
	function toCeo(){
		ymPrompt.confirmInfo({message:'确认直接给执行总裁？',title:"给执行总裁",handler:function (tp){
			if (tp=='ok'){
				$("#idAddNode").val('toCeo');
				doProcessMust(agreeUrl,"给执行总裁",null);
			}
		}});
		
	}
	//给执行总裁
	function toPresident(){
		ymPrompt.confirmInfo({message:'确认直接给总裁？',title:"给总裁",handler:function (tp){
			if (tp=='ok'){
				$("#idAddNode").val('toPresident');
				doProcessMust(agreeUrl,"给总裁",null);
			}
		}});
		
	}
	
	//二级审批人同意不同意判断  isAgree 0同意 -1 不同意
	
	function doCd2Agree(isAgree){
		 if(isAgree == '0'){//同意
			 doProcess(agreeUrl,"同意");
		 }else{             //不同意
			 doProcess(agreeUrl,"不同意");
		 }
	}
	function doAgree(nodeCd,curUserCd,isJcw){
		if('21' == nodeCd || curUserCd=='xujk'){
			//21-总裁
			var signFile = $("#signFile").val();
			if($.trim(signFile) == ''){
				alert('请上传总裁签字');
				return;
			}
			doProcess(agreeUrl,"同意");
		}else if(curUserCd=='xiaoqp'){
			var signFileX = $("#signFileX").val();
			if($.trim(signFileX) == ''){
				alert('请上传肖总签字');
				return;
			}
			doProcess(agreeUrl,"同意");
		}else if (isJcw=='true'){
			//企管部，决策委
			var uploadFlg=$("#uploadFlg").val();
			if (uploadFlg!='true'){
				alert('请上传附件');
				return;
			}
			doProcess(agreeUrl,"同意");
		}else{
			doProcess(agreeUrl,"同意");
		}
	}
	

	function doBack(){
		doProcess(backUrl,"驳回");
	}
	function doProcessError(){
		$("#isEdit").val('false');
		process(backUrl,null,"流程错误，请查看权责手册，并请选择正确的表单发起审批！");
	}
	
	function doProcessError2() {
		var approveRemark=$("#approveRemark").val();
		if(approveRemark==''){
			ymPrompt.alert({message:'请填写详细原因!',title:'资料不齐'});
		}else{
			$("#isEdit").val('false');
			process(backUrl);
		}
	}
	
	function doDisagreeOption(){
		$("#isEdit").val('false');
		process(agreeUrl,null,"不同意");
	}
	var rejectTo;
	function doProcess(url,title,fn){
		var approveRemark=$("#approveRemark").val();
		rejectTo=$("#idRejectTo").val();
		if(isEmpty(approveRemark)){
			var msg='您的意见为空，将默认填写'+title+'，请问是否继续？';
			ymPrompt.confirmInfo({message:msg,title:title,handler:function (tp){
				if (tp=='ok'){
					if(title=="驳回"){
						$("#isEdit").val('false');
						//$("#approveRemark").val('驳回');
						process(url,fn,'驳回');
					}else if(title=="同意"){
						//$("#approveRemark").val('同意');
						process(url,fn,'同意');
					}else if(title =="不同意"){
					    process(url,fn,'不同意');//二级审批人增加不同意选项，不影响流程
					}
					
				}
			}});
			rePosition($("#ym-window"));
		}else{
			process(url);
		}
	}
	//资料不全/驳回方法
	function doProcessMust(url,title,fn){
		var approveRemark=$("#approveRemark").val();
		if(approveRemark==''){
			ymPrompt.alert({message:'请输入你的意见！',title:title});
		}else{
			process(url);
		}
	}
	function openPrint()
    {
		if ($('#nodeCd').val() == '21'){
			$("#detailPanelDiv").append("<div class='signArea' style='height:20px;width:100%;margin-top:20px;' >总裁审批：</div>");
		}else if ($('#curUserCd').val()== 'xiaoqp'){
			$("#detailPanelDiv").append("<div class='signArea' style='height:20px;width:100%;margin-top:20px;' >执行副总裁审批：</div>");
		}else if ($('#isJcw').val()=='true'){
			//决策委员会打印时，多出一个签名框，并且列出所有名单
			var appendTr1="<tr class='signArea' style='border-bottom: 1px solid #000;'><td style='padding:20px 0px;text-align: left;' colspan='5'><span style='font-size:14px;font-weight:bold;color:#000;'>决策委员会名单：</span></td></tr>";
			$("#showTableApproveRec").append(appendTr1);
		}
		$("#div_print").remove();
        window.open(_ctx+'/common/print.jsp','print', 'toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
        savePrint();
    }
	//记录打印日志
	function savePrint(){
		$.post(_ctx+"/app/app-print-his!save.action",{
			bizEntityId:$("#resApproveInfoId").val(),
			bizTableName:"RES_APPROVE_INFO"
			},
		function(result) {
				$("#div_position").append("<div id='div_print' style='color: #5a5a5a;margin-right:15px;'>"+result+"</div>");
		});
	}
	
	function doShare(){
		var shareUserCds=$("#shareUserCds").val();
		var shareUserNames=$("#shareUserNames").val();
		var shareMsg=$("#shareMsg").val();
		$.post(_ctx+"/res/res-approve-info!share.action",{
			shareUserCds:shareUserCds,
			shareUserNames:shareUserNames,
			shareMsg:shareMsg,
			isAcc:$("#isAcc").val(),
			id:$("#resApproveInfoId").val()
			},
		function(result) {
				ymPrompt.succeedInfo({message:"操作已成功执行",title:"提示",width:220,height:180});
				loadShare();
				if (isNotEmpty(shareMsg))
					reloadMsg();
		});
	}

	function loadAttachment(myMod,lockUser,contFollower){
		var nodeCd=$('#nodeCd').val();
		var userCd=$('#userCd').val();// 发起人
		var isJcw=eval(nodeCd==30||nodeCd==31||nodeCd==32||nodeCd==33||nodeCd==34||nodeCd==35);
		var data = {
				bizEntityId : $("#resApproveInfoId").val(),
				myMod : myMod,
				lockUser : lockUser, // 当前锁定合同的用户
				resUserCd : contFollower, // 跟踪人
				nodeCd : nodeCd, // 当前节点
				userCd : userCd  // 发起人
			};
		$.post(_ctx+"/app/app-attachment!resShow.action",data,
				function(data) {
					$("#attach_files_div").html(data);
					resetLeftPanel();
				});
		if (isJcw==true){
			//企管部，决策委
			$.post(_ctx+"/res/res-approve-info!isUploaded.action",{bizModuleCd:'resJcw',id:$("#resApproveInfoId").val()},
					function(data) {
						$("#uploadFlg").val(data);
					});
		}
	}
	function showAttach() {
		var id=$("#resApproveInfoId").val();
		if(id=='')id=$("#entityTmpId").val();;
		loadAttachment(id);
	}

	//resContract-合同文本管理
	//resContractOther -其他附件管理
	function openAttachmentByModel(title, bizModuleCd,
			attachModelType,uiid,fileIds) {
		var entityId=$("#resApproveInfoId").val();
		if (entityId==''){
			entityId=$("#entityTmpId").val();
		}
		if (typeof (bizModuleCd) == "undefined") {
			alert("bizModuleCd is null! ");
			return;
		}
		if (typeof (attachModelType) == "undefined") {
			alert("attachModelType is null! ");
			return;
		}
		if (isEmpty(uiid)){
			uiid='';
		}
		//
		ymPrompt
				.win( {
					message : _ctx+"/app/app-attachment!list.action?bizEntityId="
							+ entityId
							+ "&bizModuleCd="
							+ bizModuleCd
							+ "&filterType=image|office&bizEntityName=ResApproveInfo&attachModelType="
							+ attachModelType+"&uiid="+uiid,
					width : 500,
					height : 300,
					winPos : [300,100],
					title : title,
					iframe : true,
					afterShow : function(){},
					handler : showAttach
				});
		rePosition($("#ym-window"));
	}

	// 显示驳回对话框
	function showRejectDialog(event,admin){
		jQuery.Event(event).stopPropagation();
		var isAdmin=false;
		if (admin){
			isAdmin=true;
		}
		var approveRemark=$("#approveRemark").val();
		if(isEmpty(approveRemark) && !isAdmin){
			ymPrompt.alert({message:'请填写驳回原因!',title:"提示",width:220,height:180,winPos:[300,100]});
			return ;
		}

		ymPrompt.confirmInfo( {
			icoCls : "",
			title : '驳回到节点',
			message : "<div id='rejectDiv' style='width:100%;align:center;'><img align='absMiddle' src='"
					+ _ctx + "/images/loading.gif'></div>",
			useSlide : true,
			winPos : "c",
			width : 400,
			height : 130,
			maxBtn : false,
			allowRightMenu : true,
			afterShow : function() {
				var url = _ctx
						+ "/res/res-approve-info!showRejectTo.action";
				var data = {
					id : $("#resApproveInfoId").val()
				};
				$.post(url, data, function(result) {
					$("#rejectDiv").html(result);
				});
			},
			handler : function(btn){
				if (btn == 'ok') {
					doProcess(backUrl,"驳回");
				}
			},
			autoClose : true
		});
	}
	
	// 肖总签名扫描件上传
	function doChiefXiaoAttachUpload(btn) {
		//确定
		if (btn == 'ok') {
			var fileName = $("#attachChiefForm_uploadInput").val();
			if (!fileName) {
				alert("请选择待上传的文件!");
				return;
			}
			$("#attachChiefForm").ajaxSubmit(function(result) {
				if (result.indexOf("success") != -1) {
					var rtn = result.split(",");
					if ('success' == rtn[0]) {
						$("#signFileX").val(rtn[1]);
						showChiefCopyDiv('resChiefXiao');
					} else {
						alert(result);
					}
				} else {
					alert(result);
				}
			});
		}
		//取消
		else if (btn == 'cancel') {

		} else {

		}
		ymPrompt.close();
	}
	// 总裁签名扫描件上传
	function doChiefAttachUpload(btn) {
		//确定
		if (btn == 'ok') {
			var fileName = $("#attachChiefForm_uploadInput").val();
			if (!fileName) {
				alert("请选择待上传的文件!");
				return;
			}
			$("#attachChiefForm").ajaxSubmit(function(result) {
				if (result.indexOf("success") != -1) {
					var rtn = result.split(",");
					if ('success' == rtn[0]) {
						$("#signFile").val(rtn[1]);
						showChiefCopyDiv('resChief');
					} else {
						alert(result);
					}
				} else {
					alert(result);
				}
			});
		}
		//取消
		else if (btn == 'cancel') {

		} else {

		}
		ymPrompt.close();
	}
	// 上传总裁意见副本对话框
	function showUploadChiefAttachDialog(bizModuleCd, event) {
		jQuery.Event(event).stopPropagation();
			
		var dTitle = "上传总裁意见副本";
		var dHandler = doChiefAttachUpload ; 
		if(bizModuleCd=='resChiefXiao'){
			dTitle = "上传肖总意见副本";	
			dHandler = doChiefXiaoAttachUpload ; 
		}
		ymPrompt
				.confirmInfo( {
					icoCls : "",
					title : dTitle,
					message : "<div id='chiefAttachDiv'><img align='absMiddle' src='"
							+ _ctx + "/images/loading.gif'></div>",
					useSlide : true,
					winPos : "c",
					width : 400,
					height : 130,
					winPos:[300,100],
					maxBtn : false,
					allowRightMenu : true,
					afterShow : function() {
						var url = _ctx
								+ "/app/app-attachment!chiefAttachUpload.action";
						var data = {
							bizEntityId : $("#resApproveInfoId").val(),
							bizModuleCd : bizModuleCd
						};
						$.post(url, data, function(result) {
							$("#chiefAttachDiv").html(result);
						});
					},
					handler : dHandler,
					autoClose : false
				});
	}

	//显示总裁意见副本
	function showChiefCopyDiv(bizModuleCd) {
		var url = _ctx + "/res/res-approve-info!chiefCopy.action";
		var data = {
			id :  $("#resApproveInfoId").val(),
			bizModuleCd:bizModuleCd
		};
		$.post(url, data, function(result) {
			if(bizModuleCd=='resChief'){
				$("#chiefCopyDiv").html(result);
				$("#chiefHasAnswer").show();
				$("#chiefHasAnswerEnter").hide();
			}else{
				$("#chiefXiaoCopyDiv").html(result);
				$("#chiefXiaoHasAnswer").show();
				$("#chiefXiaoHasAnswerEnter").hide();
			}
			resetLeftPanel();//resApprove.js
			});
	}
	
	//打开共享信息窗口
	function toShareMsgInput(){
		var shareUserCds=$("#shareUserCds").val();
		if (isNotEmpty(shareUserCds)){
			ymPrompt.confirmInfo( {
				icoCls : "",
				autoClose:false,
				message : '<div style="padding:20px;"><div>留言给共享人:</div><div><textarea id="tmpShareMsg" name="tmpShareMsg" rows="4" cols="40"></textarea></div></div>',
				width : 400,
				height : 250,
				title : "共享原因录入",
				winPos:[300,100],
				closeBtn:false,
				afterShow : function(){},
				handler : function(btn){
					if(btn=='ok'){
						var msg = $('#tmpShareMsg').val();
						if(isEmpty(msg)){
							alert('请输入共享原因!');	
							return false;
						}
						$('#shareMsg').val(msg);
						doShare();
						
					}
					ymPrompt.close();
				},
				btn:[["确定",'ok'],["取消操作",'cancel']]
			});
		}else{
			doShare();
		}
	}

	function doPush(){
		var pushTo=$("#pushUserCds").val();
		if(isEmpty(pushTo)){
			ymPrompt.alert({message:'请选择需要推送的目标!',title:"提示",width:220,height:180});
			return false;
		}
		$.post(_ctx+"/res/res-approve-info!push.action",{pushUserCds:pushTo,id: $("#resApproveInfoId").val()},
			function(result) {
				if(result=='success'){
					$('#resPushList').show();
					$('#divPushUsers').html($('#pushUserNames').val());
					ymPrompt.succeedInfo({message:"推送操作已成功执行",title:"提示",width:220,height:180});
					
				}
		});
	}

	function loadShare(){
		var id =$('#resApproveInfoId').val();
		if(isNotEmpty(id)){
			var url = _ctx + '/res/res-approve-info!loadShare.action';
			$.post(url,  
					{id:id},
					function(result) {
						if(result!='fail'){
							$('#resSharedList').html(result);
							resetLeftPanel();
						}
					}
				);
		}
	}
	//查看流程
	function viewSteps(authTypeCd) {
		ymPrompt.win({
			message : _ctx+"/res/res-approve-info!viewSteps.action?resAuthTypeCd="+authTypeCd,
			titleBar:false,
			btn:[['关闭','close']],
			fixPosition: true,
			winPos: 'c',
			width : 600,
			height: 500,
			iframe: true
		});
		rePosition($("#ym-window"));
	}
	function agreePerson(person){
		var agreeWith = '' ; 
		agreeWith += person ; 
		var param1 = '同意';
		var param2 = '的审批意见。';
		var msg = param1 + agreeWith + param2 ;
		$("#approveRemark").val(msg);
		$("#approveRemark").focus();
	}
	//跳过中间步骤，将审批直接分配给我
	function giveMe(){
		var id =$('#resApproveInfoId').val();
		TB_showMaskLayer('正在执行...',5000);
		$.post(_ctx+"/res/res-approve-info!giveMe.action",{id:id},
			 function(result) {
			 $("#content").html(result);
			 resetLeftPanel();
			 TB_removeMaskLayer();
		});
	}
	function doImport(){
		var pdv = new Validate("templetForm");
		if (pdv.validate()) {
			TB_showMaskLayer('正在执行...',5000);
			$("#templetForm").attr("action",_ctx+"/res/res-approve-info!doImport.action");
			$("#templetForm").ajaxSubmit(function(result) {
				if (result=='success'){
					 $("#btnImport2Cont").hide();//按钮隐藏
					 ymPrompt.succeedInfo({message:"成功执行",title:"提示",width:200,height:180});
				 }
				 TB_removeMaskLayer();
			});
		}
	}
	
	// 查看审批历史
	function viewApproveHis() {
		var id =$('#resApproveInfoId').val();
		ymPrompt.win({
			message :_ctx+ "/res/res-approve-info!showApproveHis.action?id="+id,
			titleBar:false,
			btn:[['关闭','close']],
			fixPosition: true,
			width : 800,
			height: 500,
			iframe: true
		});
		rePosition($("#ym-window"));
	}

	function spread(){
		$('.res_tip').find("div:first").slideDown({
			duration: 'normal', 
			easing: 'swing'});
		$('.res_tip').find(".spread").hide();
		$('.res_tip').find(".packup").show();
		
	}
	function packup(){
		$('.res_tip').find("div:first").slideUp({
			duration: 'fast', 
			easing: 'swing'});
		$('.res_tip').find(".packup").hide();
		$('.res_tip').find(".spread").show();
	}
	
	// 推送退回
	function backToPusher(){
		ymPrompt.confirmInfo( {
			icoCls : "",
			autoClose:false,
			message : '<div style="padding:20px;"><div>退回原因：</div><div><textarea id="tmpBackMsg" name="tmpBackMsg" rows="4" cols="40"></textarea></div></div>',
			width : 400,
			height : 250,
			title : "推送退回",
			closeBtn:false,
			afterShow : function(){},
			handler : function(btn){
				if(btn=='ok'){
					var msg = $('#tmpBackMsg').val();
					if(isEmpty(msg)){
						alert('请输入退回原因!');	
						return false;
					}
					doPushBack(msg);
				}
				ymPrompt.close();
			},
			btn:[["确定",'ok'],["取消",'cancel']]
		});
	}
	function doPushBack(backMsg){
		var id =$('#resApproveInfoId').val();
		$.post(_ctx+"/res/res-approve-info!doPushBack.action",{
			backMsg:backMsg,
			id:id
			},
			function(result) {
				ymPrompt.succeedInfo({message:"操作已成功执行",title:"提示",width:220,height:180});
				$("#btnPushBack").hide();
			});
	}
	//查看招标审批
	function viewZB(id) {
		window.location.href =_ctx+'/res/res-approve-info.action?id=' + id;
	}
	

	function reloadMsg(content){
		var resApproveInfoId = $('#resApproveInfoId').val();
		if (resApproveInfoId!=''){
			var replyMsgId = $('#idReplyMsg').val();  
			var isShare = $('#isShare').val();  
			var data={id:resApproveInfoId,'referMsgId':replyMsgId};
			
			if(isNotEmpty(content)){
				data.content=content;
			}
			if(isNotEmpty(isShare)){
				data.isShare=isShare;
			}
			$.post(_ctx+"/res/res-approve-info!say.action",data,
					 function(result) {
					 $("#msgContent").html(result);
					 resetLeftPanel();
				});
			$("#comment").css("border","0px solid red");
		}
	}
	function loadApprove(){
		var resApproveInfoId = $('#resApproveInfoId').val();  
		if (resApproveInfoId!=''){
			var data={id:resApproveInfoId};
			$.post(_ctx+"/res/res-approve-info!loadApprove.action",data,
					 function(result) {
				 $("#divApprove").html(result);
				 resetLeftPanel();
				});
		}
	}
	function disapledAll(){
		$("#templetForm :input").each(function(i,n){});
		$("#templetForm :input").filter(".inputBorder").filter("[edit!=true]").attr("readonly","readonly").removeAttr("validate");
		$("#templetForm :input").filter(".inputBorderNoTip").filter("[edit!=true]").attr("readonly","readonly").removeAttr("validate");
		$("#templetForm :input").filter(".inputBorder").filter("[edit!=true]").addClass("inputBorder_readOnly");
		$("#templetForm :input").filter(".inputBorderNoTip").filter("[edit!=true]").addClass("inputBorder_readOnly");
		$("#templetForm .otherUser").attr("readonly","");
		$("#templetForm .inputBorder").filter("select").attr("disabled","disabled").removeAttr("validate");
		$("#templetForm .inputBorder").filter("select").attr("class","inputBorder_readOnly");
		$("#templetForm .Wdate").filter("[edit!=true]").attr("onclick","");
		$("#templetForm .Wdate").filter("[edit!=true]").attr("onfocus","");
		$("#templetForm .Wdate").filter("[edit!=true]").attr("onblur","");
		$("#templetForm .Wdate").filter("[edit!=true]").removeClass("Wdate").removeAttr("validate");
		$("input[type='checkbox']").filter("[edit!=true]").unbind('click').removeAttr("validate");
		$("input[type='checkbox']").filter("[edit!=true]").click(function(){
			return false;
		});
		$('.singleSelect').unbind('click').css('cursor','');
		$('.mutiSelect').unbind('click').css('cursor','');
		$('.orgSelect').unbind('click').css('cursor','');
		$('.projSelect').unbind('click').css('cursor','');
		
		//liwei3 modify 显示批注按钮,取消隐藏模板里的button
		//$("#templetForm input[type='button']").hide();
		//liwei3 add 显示批注按钮
		//$("#templetForm input[type='button'][#id$='_mark']").show();
	}
	//给checkbox添加单选事件
	function addClickAction(parentDom,isMy){
		var chks;
		if (isMy){
			if (parentDom){
				chks=parentDom.find(".group");
			}else{
				chks=$(".group");
			}
		}else{
			if (parentDom){
				chks=parentDom.find(".group").filter("[edit]");
			}else{
				chks=$(".group").filter("[edit]");
			}
		}
		chks.click(function(){
			if($(this).attr('checked')){
				$(this).siblings().attr('checked',false);
				var curName=$(this).attr("name");
				$(this).parents(".chkGroup").find(".group").each(function(i){
					var tmpName=this.name;
					if (tmpName!=curName){
						$(this).attr('checked',false);
					}
				});
			}
		});
	}
	function addRequireCss(isMy){
		if (isMy){
			$("#templetForm *").filter("[validate*=required]").addClass("required");
			$(".require").each(function (i){
				$(this).html("<span style='color:red'>*</span>"+$(this).html());
			});
		}else{
			$("#templetForm *").filter("[edit]").filter("[validate*=required]").addClass("required");
			$(".require").filter("[edit]").each(function (i){
				$(this).html("<span style='color:red'>*</span>"+$(this).html());
			});
		}
	}
	/****************延期相关-start*********************/
	//查看延期申请历史记录
	function viewDelayApproveHistory(){
		var id =$('#resApproveInfoId').val();
		$.post(_ctx + "/res/res-approve-delay!history.action",{resApproveId: id},function(result) {
			if (result) { 
				ymPrompt.win('<div class=\'result\'>'+result+'</div>',750,500,'申请延期历史');
			}
		});
	}

	//取消延期
	function cancelApproveDelay(){
		var id =$('#resApproveInfoId').val();
		if(!window.confirm('确认取消?')){
			return;
		}
		$.post(_ctx + "/res/res-approve-delay!cancelApproveDelay.action",{resApproveId: id}, function(result) {
			if ('success' == result) {
				$('#resDelayProcessTip').text('已撤销延期申请!').show().fadeIn(2000).fadeOut(5000);
				$('#resDelayProcess').html('');
				$('#btnDoApproveDelay').show();
				$('#btnCancelApproveDelay').hide();
			}else if('process' == result){
				$('#resDelayProcessTip').text('');
				$('#resDelayProcess').html('');
				$('#btnDoApproveDelay').show();
				$('#btnCancelApproveDelay').hide();
				
				alert('您的延期申请已处理,载入中...');
				loadDelay();
			}else{
				alert(result);
			}
		});
	}
	//发起延期
	function doApproveDelay(){
		var id =$('#resApproveInfoId').val();
		$.post(_ctx + "/res/res-approve-delay!validateProcessing.action",{resApproveId: id}, function(result) {
			if ('success' == result) {
				$.post(_ctx + "/res/res-approve-delay!input.action",{resApproveId: id}, function(result) {
					if (result) { 
						//ymPrompt.win('<div class=\'result\'>'+result+'</div>',470,300,'申请延迟');
						$("#delayWindowDiv").html(result);
						

						//调整弹出框的位置(参照'功能'区位置)
						var _jDom = $('#funcPanelDiv');
						var $offset = _jDom.offset();
						$('#delayWindowDiv').hide();
						$('#delayWindowDiv').css({left:$offset.left,top:$offset.top + _jDom.height()+10}).show();
						
						//一定要放这里,异步可能出不来
						//$('#delayDay').artTxtCount($('#delayTip'),2);
						//$('#delayHour').artTxtCount($('#delayTip'),2);
						$('#applyReason').artTxtCount($('#applyReasonTip'),300);
					
						/*
						$('#delayApproveUserName').quickSearch(
							_PATH+'/desk/desk-wab!quickSearch.action',
							['uiid','userName','orgName','centerName'],
							{uiid:'delayApproveUserCd',userName:'delayApproveUserName'}
						);
						*/
					}
				});
			}else{
				alert(result);
			}
		});
	}
	

	//加载延期信息
	function loadDelay(){
		var id =$('#resApproveInfoId').val();
		$.post(_ctx + "/res/res-approve-delay!loadChief.action",{resApproveId: id}, function(result) {
			$('#resDelayProcess').html(result);
			resetLeftPanel();
		});
	}
	

	function saveApproveDelay(resApproveId) {
		if($.trim($('#delayDay').val()) == '' && $.trim($('#delayHour').val()) == ''){
			alert('请输入延期时间!');
			$('#delayDay').focus();
			return;
		}
		if($.trim($('#applyReason').val()) == ''){
			alert('请输入延期原因!');
			$('#applyReason').focus();
			return;
		}
		
		//经发起中心经理
		var tmpAcctTypeCd = $('input[name="applyTypeCd"]:checked','#resApproveDelayDiv').val();
		if(tmpAcctTypeCd == '0'){
			if($.trim($('#delayApproveUserCd').val()) == ''){
				alert('请选择发起中心总经理!');
				$('#delayApproveUserCd').focus();
				return;
			}
		}else{
			$('#delayApproveUserCd').val('');
		}
		
		$("#resApproveDelayForm").ajaxSubmit(
			function(result) {
				if('success' == result){
					//ymPrompt.close();
					$("#delayWindowDiv").hide();
					loadDelay();
					$('#btnDoApproveDelay').hide();
					$('#btnCancelApproveDelay').show();
					$('#btnDelayHistory').show();
					alert("已提交延期申请!");
				}else{
					alert(result);
					loadDelay();
					$('#btnDoApproveDelay').show();
					$('#btnCancelApproveDelay').hide();
				}
			}
		);
	}
	//审核同意
	function onChiefAgree(resApproveDelayId){
		comChiefDelay(resApproveDelayId, '1');
	}

	//审核拒绝
	function onChiefDegree(resApproveDelayId){
		comChiefDelay(resApproveDelayId, '2');
	}
	//qgb审核同意
	function onConfirmAgree(resApproveDelayId){
		comChiefDelay(resApproveDelayId, '3');
	}
	//qgb审核拒绝
	function onConfirmDegree(resApproveDelayId){
		comChiefDelay(resApproveDelayId, '4');
	}
	function comChiefDelay(resApproveDelayId, type){
		$.post(_ctx + "/res/res-approve-delay!reason.action",{id: resApproveDelayId,delayChiefTypeCd: type}, function(result) {
			ymPrompt.win('<div class=\'result\'>'+result+'</div>',300,150,'提示');
			$('#applyReason').artTxtCount($('#applyReasonTip'),300);
		});
	}
	function submitDelayChiefReason(resApproveDelayId,type,resApproveId){
		var delayReason = $.trim($('#delayReason').val());
		if( delayReason == ''){
			alert('请输入意见!');
			return;
		}
		if('1' == type){
			if(!window.confirm('确认同意?')){
				return;
			}
			$.post(_ctx + "/res/res-approve-delay!chiefAgree.action",{resApproveDelayId: resApproveDelayId, reason: delayReason}, function(result) {
				if('success' == result){
					$('#resDelayProcessTip').text('您已同意申请延期!').show().fadeIn(2000);
					$('#resDelayProcess').html('');
					ymPrompt.close();
					loadDelay();
				}else{
					alert(result);
				}
			});
		}
		else if('2' == type){
			if(!window.confirm('确认拒绝?')){
				return;
			}
			$.post(_ctx + "/res/res-approve-delay!chiefDegree.action",{resApproveDelayId: resApproveDelayId, reason: delayReason}, function(result) {
				if('success' == result){
					$('#resDelayProcessTip').text('您已拒绝申请延期!').show().fadeIn(2000);
					$('#resDelayProcess').html('');
					ymPrompt.close();
				}else{
					alert(result);
				}
			});
		}
		else if('3' == type){
			if(!window.confirm('确认同意?')){
				return;
			}
			$.post(_ctx + "/res/res-approve-delay!confirmAgree.action",{resApproveDelayId: resApproveDelayId, reason: delayReason}, function(result) {
				if('success' == result){
					$('#resDelayProcessTip').text('您已同意申请延期!').show().fadeIn(2000);
					$('#resDelayProcess').html('');
					ymPrompt.close();
				}else{
					alert(result);
				}
			});
		}
		else if('4' == type){
			if(!window.confirm('确认拒绝?')){
				return;
			}
			$.post(_ctx + "/res/res-approve-delay!confirmDegree.action",{resApproveDelayId: resApproveDelayId, reason: delayReason}, function(result) {
				if('success' == result){
					$('#resDelayProcessTip').text('您已拒绝申请延期!').show().fadeIn(2000);
					$('#resDelayProcess').html('');
					ymPrompt.close();
				}else{
					alert(result);
				}
			});
		}
	}
	/****************延期相关-end*********************/
	/******************/
	isEmpty = function (str) {
		return (typeof (str) === "undefined" || str === null || (str.length === 0));
	};
	isNotEmpty = function (str) {
		return (!isEmpty(str));
	};