//导入验证
function importDataValidate(){
	if (!validate()) {
			return;
		}
	if ($.trim($("#importFile").val()) == '') {
			alert("请先选择要导入的文件");
			$("#importFile").focus();
			return;
		}
		$("#qingdan").val("0");
		var bidSupId=$("#bidSupId option:selected").val();
		var projectId=$("#projectId option:selected").val();
		var currentBatchNo=$("#currentBatchNo").val();
		//查看是否已经导入过标底
		var url1 = _ctx + "/bid/bid-excel-imp!diaodiHasImported.action";
		var data1={projectId:projectId,batchno:currentBatchNo};
		$.post(url1, data1, function(result){
			var rs=result.split(";");
			//如果已经导入过标底
			if('success'==rs[1]){
				//查看是否已经导入过数据
				var url = _ctx + "/bid/bid-excel-imp!valiHasImported.action";
				var data={validateType:'imp',bidSupId:bidSupId,projectId:projectId,batchno:currentBatchNo};
				$.post(url, data, function(result){
					var rs=result.split(";");
					TB_removeMaskLayer();
					//如果前面不存在导入
					if("success"==rs[1]){				
						importDataSubmit();				
					}else{
						//如果前面已经导入过数据，需确认
						if (window.confirm(rs[2])){
							importDataSubmit();
						} 
					}
				});
			}else{
				alert("还没有清单数据，不能导入。");
			}
		});
		
		
	}
	
function importDataSubmit(){
	TB_showMaskLayer("正在上传并检验文件中...")
	$("#mainForm").ajaxSubmit(function(result) {			
			TB_removeMaskLayer();
			var msg = result.split(",");
			if (msg[1] == "success") {
				//验证通过，执行导入正式库
				activeImp(msg[2],msg[3],msg[4],msg[5],msg[6],'0');
				$("#importFile").val('');
			} else {
				if('false'==msg[2]){
					alert(msg[3].replace('</PRE>',''));
				}else{
					//验证未通过，获取未验证通过的数据
					getValidateResult(msg[2],msg[3],msg[4],msg[5]);
				}
				
				
			}
		});
}
	
//导入清单
function importQingdan(){
	if (!validate()) {
			return;
		}
	if ($.trim($("#importFile").val()) == '') {
			alert("请先选择要导入的文件");
			$("#importFile").focus();
			return;
		}
		$("#qingdan").val("1");
		var bidSupId=$("#bidSupId option:selected").val();
		var projectId=$("#projectId option:selected").val();
		var currentBatchNo=$("#currentBatchNo").val();
		//查看是否已经导入过清单
		var url = _ctx + "/bid/bid-excel-imp!valiHasImported.action";
		var data={validateType:'qingdan',bidSupId:bidSupId,projectId:projectId,batchno:currentBatchNo};
		$.post(url, data, function(result){
			var rs=result.split(";");
			TB_removeMaskLayer();
			//如果前面不存在导入
			if("success"==rs[1]){
				qingdanSubmit();
			}else{
				//如果前面已经导入过数据，需确认
				if (window.confirm(rs[2])){
					qingdanSubmit();
				} 
			}
		});
		
		
	}
	
	function qingdanSubmit(){		
		TB_showMaskLayer("正在验证...");
		$("#mainForm").ajaxSubmit(function(result) {			
			TB_removeMaskLayer();
			var msg = result.split(",");
			if (msg[1] == "success") {
				//验证通过，执行导入正式库
				activeImp(msg[2],msg[3],msg[4],msg[5],msg[6],'1');
				$("#importFile").val('');
			} else {
				if('false'==msg[2]){
					alert(msg[3].replace('</PRE>',''));
				}else{
					//验证未通过，获取未验证通过的数据
					getValidateResult(msg[2],msg[3],msg[4],msg[5]);
				}
				
				
			}
		});
	}
//导入正式库
function activeImp(batchId,bidLedgerId,bidSupId,projectId,batchNo,qingdan){
	TB_showMaskLayer("正在导入...");
	var url = _ctx + "/bid/bid-excel-imp!doActiveImport.action";
	var data={bachNo:batchId,bidLedgerId:bidLedgerId,bidSupId:bidSupId,projectId:projectId,batchNo:batchNo,qingdan:qingdan};
	$.post(url, data, function(result){
		TB_removeMaskLayer();
		var rs=result.split(',');
		if("success"==rs[0]){
			if("1"==rs[1]){
				alert("在清单中的分部分项,存在["+rs[2]+"]条未导入,请点击‘未导入清单项’按钮,点击下载并查看！");
			}else{
				alert("导入成功");
			}
			
		}
	});
}


function getValidateResult(batchNo,bidLedgerId,bidSupId,projectId){
	ymPrompt.confirmInfo({
			icoCls : "",
			autoClose:false,
			message : "<div id='validateResult' style='over'><img align='absMiddle' src='"
				+ _ctx + "/images/loading.gif'></div>",
			width : 600,
			height : 250,
			title : "错误信息列表(导入未成功,请修正数据后,重新导入!)",
			closeBtn:true,
			allowRightMenu:true,
			allowSelect:true,
			afterShow : function() {
				TB_showMaskLayer("正在获取未通过数据...");
				var url = _ctx + "/bid/bid-excel-imp!unvalidateData.action";
				var data={bachNo:batchNo,bidLedgerId:bidLedgerId,bidSupId:bidSupId,projectId:projectId};
				$.post(url, data, function(result){
					TB_removeMaskLayer();
					$("#validateResult").html(result);
				});
			},
			handler : function(btn){
				ymPrompt.close();
			},
			btn:[["确定",'ok']]
		});

}
//获取未通过数据
function showValidateResult(batchNo,bidLedgerId,bidSupId,projectId){
	TB_showMaskLayer("正在获取未通过数据...");
	var url = _ctx + "/bid/bid-excel-imp!unvalidateData.action";
	var data={bachNo:batchNo,bidLedgerId:bidLedgerId,bidSupId:bidSupId,projectId:projectId};
	$.post(url, data, function(result){
		TB_removeMaskLayer();
		$("#delayWindowDiv").html(result).show();
	});
}

function cancelMessage(){
	$("#delayWindowDiv").hide();
}