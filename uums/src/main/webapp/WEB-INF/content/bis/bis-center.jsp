<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>同步控制室</title>
	<%@ include file="/common/global.jsp" %>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
</head>
<body class="easyui-layout" style="overflow:auto;background-color: white;"> 
	<div region="center" style="text-align: left;padding:20px;">

		<div id="div_adobe" style="width:100%;margin:30px 0;">
			<div class="row_header">Adobe文件加密系统</div>
			<table style="width:100%;">
				<tr>
					<td valign="top" style="width:230px;">同步加密服务器的数据(用户/机构/组)</td>
					<td valign="top">
						<input type="button" class="buttom" value="执行同步" onclick="synLiveCycleData()"/>
						<span id="synLiveCycleData_result"></span>
					</td>
				</tr>
				<tr>
					<td valign="top">查看同步状态</td>
					<td valign="top"><input id="btn_getLiveCycleStatus"  type="button" class="buttom" value="刷新" onclick="getLiveCycleStatus();"/>
									 <input id="btn_stopLiveCycleStatus" type="button" class="buttom" value="停止" onclick="stopLiveCycleStatus();" style="display:none;"/>
					</td>
				</tr>
				<tr>
					<td></td>
					<td valign="top"><span id="getLiveCycleStatus_result"></span></td>
				</tr>
			</table>
		</div>
		
		<div id="div_email" style="width:100%;margin:30px 0;">
			<div class="row_header">Exchange邮件系统</div>
			<table>
				<tr>
					<td valign="top" style="width:230px;">同步所有邮件用户信息</td>
					<td valign="top">
						<input id="btn_synUpdateEmailRel" type="button" class="buttom" value="执行同步" onclick="synUpdateEmailRel()"/>
						<input id="btn_stopEmailSyn" type="button" class="buttom" value="停止" onclick="stopEmailSyn();" style="display:none;"/>
					</td>
				</tr>
				<tr>
					<td valign="top" colspan="2">
						<span id="batchEmail_total"></span>
						<span id="batchEmail_process"></span>
						<span id="batchUpdateEmailRel_result"></span>
					</td>
				</tr> 
				<tr>
					<td colspan="2">
						<div id="processLoading"></div>
						<table style="width:100%;display: none;" id="email_update_list">
							<tr>
								<td title="单击切换是否显示" id="email_success_list_area" style="cursor: pointer;" onmouseover="$(this).css({'background-color': '#f4f4f4'});" onmouseout="$(this).css({'background-color': 'white'})"> 
									<div>成功用户清单</div>
									<div id="email_success_list"></div>
								</td>
							</tr>
							<tr>
								<td id="email_failure_list_area" style="cursor: pointer;" onmouseover="$(this).css({'background-color': '#f4f4f4'});" onmouseout="$(this).css({'background-color': 'white'})"> 
									<div>失败用户清单</div>
									<div id="email_failure_list"></div>
								</td>
							</tr>
					</table>
					</td>
				</tr>
			</table>
		</div>
		
		
		<div id="div_emailCore" style="width:100%;margin:30px 0;">
			<div class="row_header">Coremail邮件系统</div>
			<table>
				<tr>
					<td valign="top" style="width:230px;">同步所有邮件用户信息</td>
					<td valign="top">
						<input id="btn_synUpdateEmailRelCore" type="button" class="buttom" value="执行同步" onclick="synUpdateEmailRelCore()"/>
						<input id="btn_stopEmailSynCore" type="button" class="buttom" value="停止" onclick="stopEmailSynCore();" style="display:none;"/>
					</td>
				</tr>
				<tr>
					<td valign="top" colspan="2">
						<span id="batchEmail_totalCore"></span>
						<span id="batchEmail_processCore"></span>
						<span id="batchUpdateEmailRel_resultCore"></span>
					</td>
				</tr> 
				<tr>
					<td colspan="2">
						<div id="processLoadingCore"></div>
						<table style="width:100%;display: none;" id="email_update_listCore">
							<tr>
								<td title="单击切换是否显示" id="email_success_list_areaCore" style="cursor: pointer;" onmouseover="$(this).css({'background-color': '#f4f4f4'});" onmouseout="$(this).css({'background-color': 'white'})"> 
									<div>成功用户清单</div>
									<div id="email_success_listCore"></div>
								</td>
							</tr>
							<tr>
								<td id="email_failure_list_areaCore" style="cursor: pointer;" onmouseover="$(this).css({'background-color': '#f4f4f4'});" onmouseout="$(this).css({'background-color': 'white'})"> 
									<div>失败用户清单</div>
									<div id="email_failure_listCore"></div>
								</td>
							</tr>
					</table>
					</td>
				</tr>
			</table>
		</div>  
		
		
		
		<div id="div_exportFile" style="width:100%;">
			<div class="row_header">导出EXCL文件</div>
			<div style="margin:5px 0;">
				<input type="button" class="buttom" value="导出账号与角色关系清单" onclick="exportAcctRoleRelList()"/>(约耗时90秒)
				<input type="button" class="buttom" value="导出角色列表清单" onclick="exportRoleList()"/>(约耗时1秒)
			</div>
		</div>
		
		<div id="div_openAcct" style="width:100%;">
			<div class="row_header">启用账号</div>
			<table>
				<tr>
					<td valign="top" style="width:210px;">请选择"账号生效日期"起止区间</td>
					<td valign="top">
						&nbsp;从&nbsp;<input id="openStartDate" type="text" name="openStartDate" class="easyui-datebox" style="text-align:left;padding:5px 30px 5px 10px;"/>
						&nbsp;至&nbsp;<input id="openEndDate" type="text" name="openEndDate" class="easyui-datebox" style="text-align:left;padding:5px 30px 5px 10px;"/>
						<input type="button" class="buttom" value="开通账号" onclick="openAcct()"/>
					</td>
				</tr>
				<tr>
					<td valign="top" colspan="2"><span id="openUser_result"></span></td>
				</tr> 
			</table>
		</div>
		
		<div id="div_sendPwd" style="width:100%;">
			<div class="row_header">发送密码</div>
			<table>
				<tr>
					<td valign="top">
						<input type="button" class="buttom" value="发送密码" onclick="sendPwd()"/>
					</td>
				</tr>
				<tr>
					<td valign="top" colspan="2"><span id="sendPwd_result"></span></td>
				</tr> 
			</table>
		</div>
		
	</div>
		 
	
	<script type="text/javascript">
	
		///***************** 加密系统 *****************///
		//同步数据
		function synLiveCycleData(){
			if(window.confirm("确认同步加密服务器的数据(用户/机构/组)吗?")){
				$('#div_adobe').mask('开启同步...');
				var url = "${ctx}/bis/bis-center!synLiveCycleData.action";
				$.post(url, function(result) {
					$('#div_adobe').unmask();
					if("success" == result){
						$("#synLiveCycleData_result").html('<div style="color:green;">调用成功!</div>');
					}else{
						$("#synLiveCycleData_result").html('<div><div style="cursor:pointer;color:red;" onclick="operateThis(\'syn_data_err\')">调用不成功!</div><div id="syn_data_err">'+result+'</div></div>');
					}
				});
			}
		}

		//查看同步状态(自动查询间隔 2s)
		var liveCycleProcessFlg = false;
		function getLiveCycleStatus(){
			$("#getLiveCycleStatus_result").html('<div><image src="${ctx}/images/loading.gif"/>查看同步状态中...</div>');
			
			liveCycleProcessFlg = true;
			setTimeout("updateLiveCycle()",10);
		}
		function stopLiveCycleStatus(){
			$("#btn_stopLiveCycleStatus").hide();
			liveCycleProcessFlg = false;
		}
		function updateLiveCycle(){
			if(liveCycleProcessFlg){
				$("#btn_stopLiveCycleStatus").show();
			}else{
				alert("中断查询!");
				$("#btn_stopLiveCycleStatus").hide();
				return;
			}
			var url = "${ctx}/bis/bis-center!getLiveCycleStatus.action";
			$.post(url, function(result) {
				if(result!=null && result.split(",").length == 2){//eg:1,成功
					$("#getLiveCycleStatus_result").html('<div>'+result.split(",")[1]+'</div>');
					var flag = parseInt(result.split(",")[0]); 
					//若2-同步中,继续刷新
					if( flag == 2){
						setTimeout("updateLiveCycle()",2000)
					}else{
						$("#btn_stopLiveCycleStatus").hide();
						alert("执行完毕!");
					}
				} else{
					$("#btn_stopLiveCycleStatus").hide();
					$("#getLiveCycleStatus_result").html('<div><div style="cursor:pointer;color:red;" onclick="operateThis(\'get_data_err\')">调用不成功! </div><div id="get_data_err">'+result+'</div></div>');
				}
			});
		}

		//显示/隐藏明细
		function operateThis(id){
			if( $("#"+id+":hidden").length ==0){
				$("#"+id).hide();
			}else{
				$("#"+id).show();
			}
		}

		///***************** exchange邮箱系统 *****************///
		//同步邮件用户信息
		var totalCount = 0;
		var dealedCount = 0;
		var emailProcessFlg = false;
		var pageSize = 20;//每次同步20个人
		
		function stopEmailSyn(){
			$("#btn_stopEmailSyn").hide();
			emailProcessFlg = false;
		}
		function synUpdateEmailRel(){
			if(window.confirm("确认 同步邮件用户信息吗?")){
				totalCount  = 0;
				dealedCount = 0;
				emailProcessFlg = true;

				$("#email_update_list").hide();
				$("#email_success_list").append('');
				$("#email_failure_list").append('');
				$("#batchUpdateEmailRel_result").html('');

				$("#batchUpdateEmailRel_result").html('<div><image src="${ctx}/images/loading.gif"/>同步邮件用户信息...</div>');
				var url = "${ctx}/bis/bis-center!getTotalEmails.action";
				$.post(url, function(result) {
					if(result!=null && result.split(",").length == 2){
						totalCount = parseInt(result.split(",")[1]);
						$("#batchEmail_total").html('<div>共有 '+ totalCount +' 个邮箱用户</div>');
						$("#batchUpdateEmailRel_result").html('');
						
						if(totalCount != 0){
							if(window.confirm("立即同步吗?")){
								setTimeout("updateEmailRel()",20)
							}
						}
					} else{
						$("#batchUpdateEmailRel_result").html('<div><div style="cursor:pointer;color:red;" onclick="operateThis(\'email_data_err\')">调用不成功!</div><div id="email_data_err">'+result+'</div></div>');
					}
				});
			}
		}

		//单个批次同步(自动查询间隔20ms)
		function updateEmailRel(){

			if(emailProcessFlg){
				$("#btn_stopEmailSyn").show();
				$("#processLoading").html('<div><image src="${ctx}/images/loading.gif"/>同步中...</div>');
			}else{
				alert("中断同步!");
				$("#btn_stopEmailSyn").hide();
				$("#processLoading").html('');
				return false;
			}
			
			var url = "${ctx}/bis/bis-center!updateEmailRel.action";
			var data = {batchSize: pageSize};
			//注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
			//$.ajaxSettings.async = false;
			$.post(url, data, function(result) {
				if(result!=null && result.split(",").length == 3){
					var tmpCount =parseInt(result.split(",")[0]);
					dealedCount += tmpCount;
					//alert(dealedCount);
					$("#batchEmail_process").html('已处理 '+ dealedCount + ' 个 处理率 ' +(((dealedCount/totalCount).toFixed(2))*100)+"%");
					$("#email_success_list").append(result.split(",")[1]);
					if( result.split(",").length == 3 && ($.trim(result.split(",")[2]))!=''){
						$("#email_failure_list").append(result.split(",")[2]);
					}
					$("#email_update_list").show();
					if( tmpCount > 0){ 
						setTimeout("updateEmailRel()",10)
					}else{
						$("#btn_stopEmailSyn").hide();
						alert("更新完成!");
					}
				}else{
					//退出循环
					emailProcessFlg = false;
					$("#btn_stopEmailSyn").hide();
					$("#batchUpdateEmailRel_result").html('<div><div style="cursor:pointer;color:red;" onclick="operateThis(\'email_data_err\')">调用不成功!</div><div id="email_data_err">'+result+'</div></div>');
				}
			});
		}
		
		$("#email_success_list_area").toggle(
			function () {
			    $("#email_success_list").hide();
			},
			function () {
			    $("#email_success_list").show();
			}
		);
		
		$("#email_failure_list_area").toggle(
			function () {
			    $("#email_failure_list").hide();
			},
			function () {
			    $("#email_failure_list").show();
			}
		);

		///***************** coremail邮箱系统 *****************///
		function testCoremail(typeCd){
			$('#div_testCoremail_result').show();
			
			var orgCd = $('#coremail_orgCd').val();
			var uiid = $('#coremail_uiid').val();
			
			switch(typeCd){
				case 'resetOrgSeq':
				case 'refreshMail':
				case 'refreshMailOrg':
				case 'delMailOrg':
				case 'refreshMailUser':
				case 'delMailUser':
					if(!window.confirm('需要等待一定时间,是否继续?')){
						return;
					}
					break;
				case 'updateCoremailOrg':
				case 'deleteCoremailOrg':{
					if($.trim(orgCd) == ''){
						$('#coremail_orgCd').focus();
						return;
					}
					break;
				}
				case 'updateCoremailUser':
				case 'deleteCoremailUser': 
					if($.trim(uiid) == ''){
						$('#coremail_uiid').focus();
						return;
					}
					if(!window.confirm('移除用户的同时,将永久删除邮件,是否继续?')){
						return;
					}
					break;
				default:
					break;
			} 

			$('#div_testCoremail').mask(maskDiv());
			var url = "${ctx}/bis/bis-center!testCoremail.action";
			$.post(url,{typeCd: typeCd, orgCd: orgCd, uiid: uiid}, function(result) {
				$('#div_testCoremail_result').html(result).show();
				$('#div_testCoremail').unmask();
			});
		}
		
		function maskDiv(){
			return '正在处理中,请等待...<input type="button" onclck="stopProcess(this)" value="停止"/>';
		}
		
		function stopProcess(dom){
			var url = '${ctx}/bis/bis-center!stopProcess.action';
			$.post(url, function(result) {
				if('success' == result){
					$(dom).parent().unmask();
				}else{
					alert(result);
				}
			});
		}
		///////////////////////////
		
		//启用账号
		function openAcct(){
			if(!window.confirm('确认开通账号?')){
				return;
			}
			var startDate = $.trim($('input[name="openStartDate"]').val());
			var endDate   = $.trim($('input[name="openEndDate"]').val());
			if(endDate == ''){
				alert('请选择账号启用的截止日期!');
				return;
			}
				
			var url = "${ctx}/bis/bis-center!timerOpenAccts.action";
			$.post(url,{openStartDate: startDate,openEndDate: endDate}, function(result) {
				$("#openUser_result").html(result);
			});
		}
		
		//发送密码
		function sendPwd(){
			var url = "${ctx}/bis/bis-center!timerSendPwd.action";
			$.post(url,{}, function(result) {
				$("#sendPwd_result").html(result);
			});
		}
		 
		//导出账号与角色关系清单
		function exportAcctRoleRelList(){
			window.open("${ctx}/bis/bis-center!exportAcctRoleRelList.action");
		}
		//导出角色列表清单
		function exportRoleList(){
			window.open("${ctx}/bis/bis-center!exportRoleList.action");
		}
		
	</script> 
</body>
</html>