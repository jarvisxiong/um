<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>缓存管理</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
	<link href="${ctx}/css/desk/thickbox.css" rel="stylesheet" type="text/css" />
	<script language="javascript"  src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
</head>

<body>
	<div style="width:95%;height:100%;margin-left: 10px;">
	<fieldset>
		<legend>缓存管理</legend>
		<table width="100%">
			<tr>
				<td width="70%" align="left">部门缓存</td>
				<td width="30%" align="left"><button onclick="exeCache('org')" style="height:25px;width:80px;">立即执行</button></td>
			</tr>
			<tr>
				<td>数据字典缓存</td>
				<td><button onclick="exeCache('dict')" style="height:25px;width:80px;">立即执行</button></td>
			</tr>
		</table>
	</fieldset>
	<fieldset>
		<legend>垃圾清理</legend>
		<table width="100%">
			<tr>
				<td style="width:70%;">清除已删除、临时的附件</td>
				<td><button onclick="clearFile()" style="height:25px;width:80px;">立即执行</button></td>
			</tr>
		</table>
	</fieldset>
	<fieldset>
		<legend>定时器管理</legend>
		<table width="100%">
			<tr>
				<td width="70%" align="left">每日事务定时邮件提醒</td>
				<td width="30%" align="left"><button onclick="exeTaskRemind()" style="height:25px;width:80px;">立即执行</button></td>
			</tr>
		</table>
	</fieldset>
	<fieldset>
		<legend>用户名更换(仅转移邮件,用户名请至UAAP数据库直接更改)</legend>
		<table width="100%">
			<col style="width:70%;"/>
			<col />
			<tr>
				<td colspan="2" style="color:red;">
				注意：该操作不可逆。<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果旧用户已经产生了审批或者其他任务数据，请停止操作。<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户名重新设置后，只会将旧用户的邮件转移过去。<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;更换前请确认新用户名在UAAP中不存在。<br/>
				</td>
			</tr>
			<tr>
				<td>
					原用户名：<input type="text" id="oldUiid" /> 
					新用户名:<input type="text" id="newUiid" />
				</td>
				<td><button onclick="resetUser()" style="height:25px;width:80px;">立即更换</button></td>
			</tr>
		</table>
	</fieldset>
	<fieldset>
		<legend>发送网批短信通知</legend>
		<table width="100%">
			<tr>
				<td><button onclick="notifyAllUser()" style="height:25px;width:80px;">立即发送</button></td>
			</tr>
		</table>
	</fieldset>
	<fieldset>
		<legend>初始化便签数据<font color='red'>(谨慎操作)</font></legend>
		<table width="100%">
			<tr>
				<td style="width:70%;"><button onclick="initNotes()" style="height:25px;width:150px;">初始化所有人便签</button></td>
				<td>
					用户uiid:<input type="text" id="uiidNote" /> 	
					<button onclick="initNotesByUiid();">初始化个人便签</button> <br/>
				</td>
			</tr>
		</table>
	</fieldset>
	<fieldset>
		<legend>提醒修改密码</legend>
		<table width="100%">
			<tr>
				<td style="width:70%;" valign="top">
					已经邮件通知人员如下：
					<div id="email2User"></div>
				</td>
				<td valign="top">
					<button onclick="sendEmail2Pwd123();" style="height:25px;width:80px;">发送邮件</button> 
					邮件通知密码为123的用户修改密码!
				</td>
			</tr>
		</table>
			
	</fieldset>
	
	
	
	
	<fieldset>
		<legend>EAS每日提醒</legend>
		<table width="100%">
			<tr>
				<td style="width:70%;" valign="top">
					<button onclick="tipEas('birthDay');"     	style="height:25px;min-width:80px;">提醒生日</button> 
					<button onclick="tipEas('positiveView1');" 	style="height:25px;min-width:80px;">月度考核</button> 
					<button onclick="tipEas('positiveView2');" 	style="height:25px;min-width:80px;">转正考核</button> 
					<button onclick="tipEas('contEndView1');" 	style="height:25px;min-width:80px;">合同到期（一般员工）</button> 
					<button onclick="tipEas('contEndView2');" 	style="height:25px;min-width:80px;">合同到期（总经理级以上）</button>
					<button onclick="tipEas('all');"     	style="height:25px;min-width:80px;">全部</button> 
					
					<div id="tip_eas"></div>
				</td>
			</tr>
		</table>
	</fieldset>
	
	</div>
	
	<div>
		<button onclick="testResInf()" style="height:25px;min-width:80px;">测试填写网批节点接口</button>
	</div>
	<div>
		<button onclick="testPlasNode()" style="height:25px;min-width:80px;">测试网批节点和系统职务对应关系表</button>
	</div>
	
	<script type="text/javascript">
		function testPlasNode(){
			TB_showMaskLayer('正在执行...');
			$.post('${ctx}/plaspd/plas-quartz!testPlasNode.action',function(result){
				if(result == 'success'){
					alert('执行成功');
					TB_removeMaskLayer();
				}else{
					alert('执行失败,请重试');
					TB_removeMaskLayer();
				}
			});
		}
		function testResInf(){
			TB_showMaskLayer('正在执行...');
			$.post('${ctx}/plaspd/plas-quartz!testResInf.action',function(result){
				if(result == 'success'){
					alert('执行成功');
					TB_removeMaskLayer();
				}else{
					alert('执行失败,请重试');
					TB_removeMaskLayer();
				}
			});
		}
		function clearFile(){
			TB_showMaskLayer('正在执行...');
			$.post('${ctx}/plaspd/plas-quartz!clearFile.action',function(result){
				if(result == 'success'){
					alert('执行成功');
					TB_removeMaskLayer();
				}else{
					alert('执行失败,请重试');
					TB_removeMaskLayer();
				}
			});
		}
		function exeCache(className){
			TB_showMaskLayer('正在执行...');
			$.post('${ctx}/plaspd/plas-quartz!refreshCache.action',{className:className},function(result){
				if(result == 'success'){
					alert('执行成功');
					TB_removeMaskLayer();
				}else{
					alert('执行失败,请重试');
					TB_removeMaskLayer();
				}
			});
		}

		function exeTaskRemind(){
			TB_showMaskLayer('正在执行...');
			$.post('${ctx}/plaspd/plas-quartz!exeTaskRemind.action',function(result){
				if(result == 'success'){
					alert('执行成功');
					TB_removeMaskLayer();
				}else{
					alert('执行失败,请重试');
					TB_removeMaskLayer();
				}
			});
		}
		function resetUser(){
			var oldUiid = $("#oldUiid").val();
			var newUiid = $("#newUiid").val();
			if(oldUiid == "" || newUiid == ""){
				alert("不能为空");
				return;
			}
			if(!window.confirm('确定替换uiid?')){
				return;
			}
			TB_showMaskLayer('正在执行...');
			$.post("${ctx}/oa/oa-email-body!resetUser.action",{oldUiid:oldUiid,newUiid:newUiid},function(){
				alert("执行成功");
				TB_removeMaskLayer();
			});
		} 
		function notifyAllUser(){
			if(window.confirm("确认发送短信吗?")){
				TB_showMaskLayer('正在执行...');
				$.post('${ctx}/plaspd/plas-quartz!notifyAllUser.action',function(result){
					if(result == 'success'){
						alert("执行成功");
						TB_removeMaskLayer();
					}else{
						alert('执行失败,请重试');
						TB_removeMaskLayer();
					}
				});
			}
		}
		function initNotes(){
			if(window.confirm("确认初始化吗?")){
				TB_showMaskLayer('正在执行...');
				$.post("${ctx}/dly/dly-note!initNotes.action",function(result){
					if(result == 'success'){
						alert("执行成功");
						TB_removeMaskLayer();
					}else{
						alert('执行失败,请重试');
						TB_removeMaskLayer();
					}
				});
			}
		}
		function initNotesByUiid(){
			var uiid = $('#uiidNote').val();
			if(uiid == '')return;
			if(window.confirm("确认初始化吗?")){
				TB_showMaskLayer('正在执行...');
				$.post("${ctx}/dly/dly-note!initNotesByUiid.action",{'uiid':uiid},function(result){
					if(result == 'success'){
						alert("执行成功");
						TB_removeMaskLayer();
					}else{
						alert('执行失败,请重试');
						TB_removeMaskLayer();
					}
				});
			}
		}
		function sendEmail2Pwd123(){
			TB_showMaskLayer('正在执行...');
			$.post("${ctx}/plaspd/plas-quartz!sendEmail2Pwd123User.action",function(r){
				alert("执行成功");
				$('#email2User').text(r);
				TB_removeMaskLayer();
			});
		}

		function tipEas(type){
			TB_showMaskLayer('正在执行...');
			$('#tip_eas').text('处理中...');
			$.post("${ctx}/plaspd/plas-quartz!tipEas.action",{type: type},function(result){
				if('success' == result){
					alert('执行成功!');
					$('#tip_eas').text('执行成功!');
				}else{
					$('#tip_eas').text(r);
				}
				TB_removeMaskLayer();
			});
		}
		
	</script>
</body>
</html>
