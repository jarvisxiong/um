
	
	//获取url
	function getUrl(){
		var jDom = $('#item-nav li[class*=item-nav-click]');
		var url = jDom.attr('url');
		//若点击分部分项,则从不同数据来源获取数据;
		//if ('divisiton' == jDom.attr('itemtype')) {
		//	var typeCd = $("#bidSupId").find("option:selected").attr("typeCd");
		//	//投标单位
		//	if (typeCd == 1) {
		//		url = _ctx + "/bid/bid-division-sup-rel!list.action";
		//	//标底单位
		//	} else if (typeCd == 2) {
		//		url = _ctx + "/bid/bid-divisiton!list.action";
		//	}else{
		//		showResultTip('tipItem','未找到投标单位类型!');
		//		return '';
		//	}
		//} 
		return url;
	}

	//获取工程id
	function getProjectId(){
		return $("#projectId").find("option:selected").val();
	}
	//获取投标单位id
	function getSupId(){
		return $("#bidSupId").find("option:selected").val();
	}
	function getBatchNo(){
		var t = $('#batch-nav li[class*=batch-nav-click]').attr('batchno');
		if(typeof(t) == 'undefined'){
			return '';
		}else{
			return t;
		}
	}
	
	//检查必须选择 工程和单位
	function validate() {
		var projectId = getProjectId();
		var bidSupId  = getSupId();
		//必须选择工程
		if (projectId == '' || typeof (projectId) == 'undefined') {
			//showResultTip('tipBidProject','请选择标工程!');
			$("#tipPro").show();
			return false;
		}else{
			$("#tipPro").hide();
		}
		//必须选择标底或投标单位
		if (bidSupId == '' || typeof (bidSupId) == 'undefined') {
			//showResultTip('tipBidSup','请选择标底或投标单位!');
			$("#tipSup").show();
			return false;
		}else{
			$("#tipSup").hide();
		}
		return true;
	}
	
	//服务器端验证提示
	function importTipValidate(){
		var projectId=$("#projectId").val();
		var bidSupId=$("#bidSupId").val();
		var data={projectId:projectId,bidSupId:bidSupId};
		var url = _ctx + "/bid/bid-ledger-excel!importTip.action";
		$.post(url, data, function(result){
			var rs=result.split(",");
			if(1==rs[0]){
				if(window.confirm(rs[1])){
					//批量删除,且导入
					importExcelFile();
				}else{
					return false;
				}
			}else{
				//导入EXCEL数据
				importExcelFile();
			}
		});
		return false;
	}

	//导入数据验证
	function importData() {
		$("#resultTip").html('');
		if (!validate()) {
			return;
		}
		
		if ($.trim($("#importFile").val()) == '') {
			showResultTip('tipFile',"请先选择要导入的文件");
			$("#importFile").focus();
			return;
		}
		//导入EXCEL数据服务器端验证
		importTipValidate();
	}
	
	
	
	function importExcelFile(){
		TB_showMaskLayer("正在导入...");
		$("#mainForm").ajaxSubmit(function(result) {			
			TB_removeMaskLayer();
			var msg = result.split(",");
			if (msg[1] == "success") {
				//showResultTip('tipFile',"导入成功：共新增" + msg[2] + "条记录，更新" + msg[3] + "条记录，耗时" + msg[4] + "秒");
				$("#tipFile").html("导入成功：共新增" + msg[2] + "条记录，耗时" + msg[3] + "秒").show();
				$("#importFile").val('');
			} else {
				$("#tipFile").html("导入失败："+ msg[2].replace("&lt;","<").replace("&gt;",">")).show();
				//showResultTip('tipFile',"导入失败：" + msg[2]);
			}
		});
		
	}

	
	
	
	//导出模板
	function exportTemplate(batchNo,downList) {
		if (!validate()) {
			return;
		}
		TB_showMaskLayer("正在导出...");
		//导出清单
		if('downList'==downList){			
			window.location.href = _ctx + "/bid/bid-ledger-excel!exportExcel.action?projectId=" + getProjectId() + "&bidSupId=" + getSupId()+"&batchNo="+batchNo+"&downList=downList";
		}else 
		//导出未导入清单的项目编号
		if('umimported'==downList){
			window.location.href = _ctx + "/bid/bid-ledger-excel!exportExcel.action?projectId=" + getProjectId() + "&bidSupId=" + getSupId()+"&batchNo="+batchNo+"&downList=umimported";
		}else{
		//导出模板
			window.location.href = _ctx + "/bid/bid-ledger-excel!exportExcel.action?projectId=" + getProjectId() + "&bidSupId=" + getSupId()+"&batchNo="+batchNo;
		}
		TB_removeMaskLayer();
		
	}

	//跳转至给定的页面,配合前台分页搜索使用	
	function jumpPage(pageNo) {
		if (pageNo == '' || typeof (pageNo) == 'undefined') {
			pageNo = '1';
		}
		ajaxTable(pageNo);
	}
	
	function jumpPageTo(){
		var pageTo=$("input#pageTo").val();
		ajaxTable(pageTo);
	}
	
	//提示信息
	function showResultTip(id,text){
		$('#'+id).html(text).show().fadeOut(1000);
	}
	
	//分页搜索
	var pageSize = 10;
	function ajaxTable(pageNo) {
		
		var batchNo = getBatchNo();
		if( batchNo == ''){
			showResultTip('tipBatch', '请选择批次!');
			return;
		}
		
		var url = getUrl();
		var data = {
			projectId : getProjectId(),
			bidSupId : getSupId(),
			rows : pageSize,
			page : pageNo,
			batchno : batchNo 
		};
		TB_showMaskLayer("正在搜索...");
		$.post(url, data, function(result) {
			$('#tableResult').html(result);
			TB_removeMaskLayer();
		});
	}
	
	//工程或者供应商切换之后自动搜索
	function  autoSearch(dom){		
		$("#item-nav >li[class=item-nav-click]").click();
		
	}
	
	//根据供应商控制轮次显示
	function changeSup(dom){
		var jdom=$(dom);
		
		if("bidSupId"==jdom.attr('id')){
			var typeCd=jdom.find("option:selected").attr('typecd');
			if('1'==typeCd){
				$("#importQingdan").hide();
				
				//$("#batch-nav li").each(function(){
				//	$(this).removeClass("batch-nav-click");
				//	$(this).show();
				//});
				//$("#batch-nav li:first").next().hide();
				var csize=$("#batch-nav li").filter('.batch-nav-click').size();
				if(csize<1)
				$("#batch-nav li:nth-child(2)").addClass("batch-nav-click");
				$("#tipSup").hide();
			}
			else
				if('2'==typeCd){
					$("#importQingdan").show();
					var csize=$("#batch-nav li").filter('.batch-nav-click').size();
					if(csize<1)
					$("#batch-nav li:nth-child(2)").addClass("batch-nav-click");
				}
			//else
			//if('2'==typeCd){
			//	$("#batch-nav li").each(function(){
			//		$(this).removeClass("batch-nav-click");
			//		$(this).hide();					
			//	});	
			//	$("#batch-nav li:first").show();
			//	$("#batch-nav li:nth-child(2)").addClass("batch-nav-click").show();
			//	$("#tipSup").hide();
			//}
		else{
				$("#tipSup").show();
			}
			
		}
	}
	
	function changePro(dom){
		var jdom=$(dom);
		var value=jdom.find("option:selected").val();
		if(''==value||value==null||typeof (value) == 'undefined'){
			$("#tipPro").show();
		}else{
			$("#tipPro").hide();
		}
	}
	
	
	