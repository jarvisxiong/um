<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>测试面板</title>
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
	
	<style type="text/css">
		input{
			line-height: 22px;
			height:30px;
			padding:2px 5px;
			cursor:pointer;
		}
	</style>
</head>
<body class="easyui-layout" style="overflow:auto;background-color: white;"> 
	<div region="center" style="text-align: left;padding:10px;">
		   
		<div id="div_testZony" style="width:100%;margin:30px 0;">
			<div class="row_header">测试接口(仲尼服务)</div>
			<table style="width:100%;">
				<tr>
					<td valign="top">
						<input type="button" class="buttom" value="获取用户列表" onclick="testZony('getUsers');"/>
						说明: 查询可以同步到加密系统的所有用户.<br/>
						<input type="button" class="buttom" value="获取机构列表" onclick="testZony('getOrgs');"/>
						说明: 查询可以同步到加密系统的所有机构.<br/>
						<input type="button" class="buttom" value="获取组列表" onclick="testZony('getGroups');"/>
						说明: 查询可以同步到加密系统的加密组,如职级.<br/>
						<input type="button" class="buttom" value="获取组与用户关系列表" onclick="testZony('getUserGroupRels');"/>
						说明: 查询加密组和用户关系,如职级与用户关系.<br/>
						<input type="button" class="buttom" value="获取指定组的用户列表" onclick="testZony('getGroupUserRelsByGroup');"/>
						说明: 查询特定加密组下的用户列表,如职级下的用户列表.<br/>
					</td>
				</tr> 
			</table>
		</div>
		
		
		<div id="div_testPlas" style="width:100%;margin:30px 0;">
			<div class="row_header">测试接口(PLAS服务)</div>
			<table style="width:100%;">
				<tr>
					<td valign="top">
						<input type="button" class="buttom" value="获取所有机构" onclick="testPlas('getBizOrgs');"/>
						<input type="button" class="buttom" value="查询与账号相关的职位列表所在的机构" onclick="testPlas('getRelPosOrgIdList');"/>
						<input type="button" class="buttom" value="更新所有人员所在中心" onclick="testPlas('refreshUserCenter');" title="uiid"/>
					</td>
				</tr> 
			</table>
		</div>
		<div id="div_testPd" style="width:100%;margin:30px 0;">
			<div class="row_header">测试接口(Pd服务)</div>
			<table style="width:100%;">
				<tr>
					<td valign="top">
						<input type="button" class="buttom" value="发送邮件" onclick="testPd();"/>
					</td>
				</tr> 
			</table>
		</div>
		
		<div id="div_testCoremail" style="width:100%;margin:30px 0;">
			<div class="row_header" style="100%;">测试接口(coremail服务)</div>
			<div style="width:100%;padding:5px 0;">
				<%--
				<input type="button" class="buttom" value="机构重新排序" onclick="testCoremail('resetOrgSeq');"/>
				 --%>
				 <%--
				<input type="button" class="buttom" value="导入Exchange用户列表" onclick="testCoremail('importExchangeAcct');"/>
				<input type="button" class="buttom" value="初始化外邮用户的密码" onclick="testCoremail('resetOutMail');"/>
				  --%>
				<br/>
				<input type="button" class="buttom" value="同步有效的机构和用户" onclick="testCoremail('refreshMail');"  title="(约耗1.5小时) 说明:初始化coremail邮箱机构和用户,对应PLAS的物理机构视图."/>
				<input type="button" class="buttom" value="删除失效的机构" onclick="testCoremail('refreshInvalidMailOrg');" />
				<div style="width:100%;">
					<input type="button" class="buttom" value="同步有效机构(新增/更新)" onclick="testCoremail('refreshMailOrg');"  title="(约耗1.5小时) 说明:初始化coremail邮箱机构,对应PLAS的物理机构视图."/>
					<%--
					<input type="button" class="buttom" value="移除全部机构(禁止操作)" onclick="testCoremail('delMailOrg');"  title="移除机构"/>
					 --%>
					机构orgCd：<input type="text" id="coremail_orgCd"  style="width:100px"/>
					&nbsp;&nbsp;
					<input type="button" class="buttom" value="新增机构" onclick="testCoremail('updateCoremailOrg');"/>
					<input type="button" class="buttom" value="更新机构" onclick="testCoremail('updateCoremailOrg');"/>
					<input type="button" class="buttom" value="移除机构" onclick="testCoremail('deleteCoremailOrg');" title="orgCd"/>
				</div>
				<div style="width:100%;">
					<input type="button" class="buttom" value="同步有效用户(新增/更新)" onclick="testCoremail('refreshMailUser');" title="(约耗2分钟)
					说明:初始化coremail邮箱用户,同时满足条件:
					1.不论已开通或未开通exchange;
					2.可以挂在邮件机构树下;
					另外: 注销的账号导入coremail，但不显示."/>
					<%--
					<input type="button" class="buttom" value="移除全部用户(禁止操作)" onclick="testCoremail('delMailUser');" title="移除用户"/>
					 --%>
					用户UIID：<input type="text" id="coremail_uiid" style="width:100px"/>
					<input type="button" class="buttom" value="新增人员" onclick="testCoremail('updateCoremailUser');"/>
					<input type="button" class="buttom" value="更新人员" onclick="testCoremail('updateCoremailUser');"/>
					<input type="button" class="buttom" value="移除人员" onclick="testCoremail('deleteCoremailUser');" title="uiid"/>
					<br/>
					
					<input type="button" class="buttom" value="同步可用人员" onclick="testCoremail('refreshMailUser');" title="uiid"/>
					<input type="button" class="buttom" value="同步注销人员" onclick="testCoremail('refreshInvalidMailUser');" title="uiid"/>
					
					<input type="button" value="同步未读邮件数" onclick="execEmailStat()" title="将邮箱用户未读邮件的统计数，写入plas库,以便查看"/>
				</div>
				 
				<div id="div_testCoremail_result" style="height:200px;overflow-y:auto;+position:relative;">
					
				</div>
			</div>
			
		</div>
	</div>
		 
	
	<script type="text/javascript">
  
		//测试接口
		function testZony(typeCd){
			$('#div_testZony').mask(maskDiv());
			var url = "${ctx}/bis/bis-center!testZony.action";
			$.post(url,{typeCd: typeCd}, function(result) {
				$('#div_testZony').unmask();
				alert(result);
			});
		}
		//测试接口
		function testPlas(typeCd){
			$('#div_testPlas').mask(maskDiv());
			var url = "${ctx}/plas/plas-user!" + typeCd + ".action";
			$.post(url,{typeCd: typeCd}, function(result) {
				$('#div_testPlas').unmask();
				alert(result);
			});
		}
		//测试接口
		function testPd(typeCd){
			var url = "${ctx}/bis/bis-center!testPd.action";
			$.post(url,{}, function(result) {
				alert(result);
			});
		}
		//测试接口
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
					if($.trim(uiid) == ''){
						$('#coremail_uiid').focus();
						return;
					}
					break;
				case 'deleteCoremailUser': 
					if($.trim(uiid) == ''){
						$('#coremail_uiid').focus();
						return;
					}
					if(!window.confirm('移除用户的同时,将永久删除邮件,是否继续?')){
						return;
					}
					break;
				case 'refreshInvalidMailOrg':
				case 'refreshInvalidMailUser'://失效用户
				case 'refreshMailUser'://有效用户
				default:
					break;
			} 
			if(!window.confirm('确定执行?')){
				return;
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
	
		//统计未读文件
		function execEmailStat(){
			$('#div_testCoremail').mask(maskDiv());
			var url = '${ctx}/bis/bis-center!execEmailStat.action';
			$.post(url, function(result) { 
				$('#div_testCoremail').unmask();
				alert(result);
			});
		}
	</script> 
</body>
</html>