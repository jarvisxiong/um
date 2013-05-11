<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="${ctx}/js/jquery/jquery.js"  type="text/javascript" ></script>

	<%--easyui --%>
	<link href="${ctx}/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/js/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css" >
	<script src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"  type="text/javascript" ></script>

<!-- quick search  -->
  
<script src="${ctx}/js/jquery/jquery.quickSearch.js"  type="text/javascript" ></script>
<div class="easyui-panel" title="<s:if test="id == null"><s:if test="uiid==null">创建</s:if><s:else>编辑</s:else></s:if><s:else>编辑</s:else>账号信息"  style="overflow:auto;background-color: white;" fit="true" style="+position: relative">




<form id="acctForm" action="plas-acct!save.action" method="post">
	<input type="hidden" name="id" value="${plasAcctId }"/>
	<input type="hidden" name="plasUserId" value="${plasUser.plasUserId }"/>
	
	<div class="toolbar" style="text-align: right;">
		<!-- 账号状态转化  1：正常  --冻结--》 2 ：冻结-->
		<s:if test='statusCd != null&&"1".equals(statusCd)'>
			<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-freeze" onclick="freezeAcct('0')">冻结</a>
			
		</s:if>
		<!-- 账号状态转化  2：冻结  --解冻--》1：正常 -->
		<s:elseif test='statusCd!=null&&("2".equals(statusCd))'>
			<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-unfreeze" onclick="freezeAcct('1')">解冻</a>
		</s:elseif>
		<!-- 账号状态转化  0：未启用，1：正常，2：冻结， ---注销--》》4 ：销户 -->
		<s:if test='statusCd != null&&!"4".equals(statusCd)'>
			<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-freeze" onclick="activeAcct('0')">注销</a></s:if>
		<!-- 账号状态转化  0：未启用，4：销户  --启用--》》 1：正常 -->
		<s:elseif test='statusCd != null&&("0".equals(statusCd)||"4".equals(statusCd))'>
			<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-unfreeze" onclick="activeAcct('1')">启用</a>
		</s:elseif>
	</div>
	
	<table class="eddit_table">
		<tr align="left" width="40">
			<td width="120">登录名:</td>
			<td>
				<input type="text" id="uiid" name="uiid" value="${uiid }" class="easyui-validatebox" required="true" validType="length[1,50]"/>
				<span style="color:red">&nbsp;&nbsp;&#42;</span>

				
				
			</td>
			<td width="120">排序序号</td>
			<td>
				<input type="text" id="sequenceNo" name="sequenceNo" value="${sequenceNo }" class="easyui-numberbox" validType="length[1,10]"/>
			</td>
		</tr>
		<tr align="left">
			<td>姓名:</td>
			<td>
				<input type="text" id="custLoginName" name="custLoginName" value="${ custLoginName}" class="easyui-validatebox" required="true" validType="length[1,50]"/>
				<span style="color:red">&nbsp;&nbsp;&#42;</span>
			</td>
			<td>状态:</td>
			<td>
				<input type="text" id="statusCd" name="statusCd" value="${statusCd }" readonly="readonly"/>
			</td>
		</tr>
		<tr align="left">
			<td>用户锁定时间:</td>
			<td>
				<input type="text" id="lockedDate" name="lockedDate" value="${lockedDate }" class="easyui-datebox"  />
			</td>
			<td>认证方式:</td>
			<td>
				<s:select   list="@com.powerlong.plas.utils.DictMapUtil@getMapAuthenticType()" listKey="key" listValue="value" 
				id="authenticTypeCd" name="authenticTypeCd" />
			</td>
		</tr>
		<tr align="left">
			<td>密码:</td>
			<td>
				<input type="password" id="loginInPassword" name="loginInPassword" value="${loginInPassword }" class="easyui-validatebox" required="true" validType="length[1,100]"/>
				<span style="color:red">&nbsp;&nbsp;&#42;</span>
			</td>
			<td>密码连续错误次数:</td>
			<td>
				<input type="text" id="failureTimes" name="failureTimes" value="${failureTimes }" class="easyui-numberbox"/>
			</td>
		</tr>
		<tr align="left">
			<td>上次登录时间:</td>
			<td>
				
				<input readonly="readonly" type="text" id="lastLoginDate" name="lastLoginDate" value="${lastLoginDate }" />
			</td>
			<td>上次签退时间:</td>
			<td>
				<input readonly="readonly" type="text" id="lastLogoutDate" name="lastLogoutDate" value="${lastLogoutDate }" />
			</td>
		</tr>
		<tr align="left">
			<td>生效日期:</td>
			<td>
				<input type="text" id="effectDate" name="effectDate" value="${effectDate }" class="easyui-datebox" />
			</td>
			<td>失效日期:</td>
			<td>
				<input type="text" id="invalidDate" name="invalidDate" value="${ invalidDate}" class="easyui-datebox" />
			</td>
			
		</tr>
		<tr align="left">
			
			<td>上次访问IP地址</td>
			<td colspan="3">
				<input type="text" id="lastLoginIP" name="lastLoginIP" value="${ lastLoginIP}"  style="width:100%;" class="easyui-validatebox"  validType="length[1,15]"/>
			</td>
			
		</tr>
		<tr align="left">
			<td>备注:</td>
			<td colspan="3">
				<s:textarea type="text" id="remark" name="remark"  cssStyle="width:100%;" cssClass="easyui-validatebox" validType="length[1,200]"/>
			</td>
		</tr>
		<tr align="left">
			<td>MAC地址:</td>
			<td colspan="3">
				<s:textarea key="macAddress"  id="macAddress" name="macAddress" cssStyle="width:100%;" cssClass="easyui-validatebox"  validType="length[1,200]"/>
			</td>
		</tr>
		
		<tr >
			<td >MAC码是否锁定:</td>
			<td colspan="3">
				<s:radio list="@com.powerlong.plas.utils.DictMapUtil@getMapMacLockedFlg()" listKey="key" listValue="value" id="macLockedFlg" name="macLockedFlg"/>
			</td>
		</tr>

		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td>EAS开通标志:</td>
			<td colspan="3">
				<div id="easTip" style="float: left;"></div>
							<s:if test="plasAcctId != null && plasAcctId != ''">
								<b style="color: red;padding:0 5px 0 5px;" id="easDesc"><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapEasFlg()" value="easFlg"/></b>
							</s:if>
							<s:if test="plasAcctId != null && plasAcctId != ''">
								<%-- 若未开通,则可激活 --%>
								<s:if test="easFlg == 0 || easFlg=='' || easFlg == null ">
									<input id="btnOpenEas" style="display:inline;" type="button" class="buttom" value="开通Eas" onclick="openEas('${plasAcctId}')"/>
									<input id="btnEnableEas" style="display:none;" type="button" class="buttom" value="激活Eas" onclick="enableEas('${plasAcctId}')"/>
									<input id="btnDisableEas" style="display:none;" type="button" class="buttom" value="禁用Eas" onclick="disableEas('${plasAcctId}')"/>
									<input id="btnSynEasPwd" style="display:none;" type="button" class="buttom" value="同步密码" onclick="synEasPwd('${plasAcctId}')"/>
								</s:if>
								<%-- 若禁用,则可激活 --%>
								<s:if test="easFlg == 2">
									<input id="btnOpenEas" style="display:none;" type="button" class="buttom" value="开通Eas" onclick="openEas('${plasAcctId}')"/>
									<input id="btnEnableEas" style="display:inline;" type="button" class="buttom" value="激活Eas" onclick="enableEas('${plasAcctId}')"/>
									<input id="btnDisableEas" style="display:none;" type="button" class="buttom" value="禁用Eas" onclick="disableEas('${plasAcctId}')"/>
									<input id="btnSynEasPwd" style="display:none;" type="button" class="buttom" value="同步密码" onclick="synEasPwd('${plasAcctId}')"/>
								</s:if> 
								<%-- 若激活,则可禁用 --%>
								<s:if test="easFlg == 1">
									<input id="btnOpenEas" style="display:none;" type="button" class="buttom" value="开通Eas" onclick="openEas('${plasAcctId}')"/>
									<input id="btnEnableEas" style="display:none;" type="button" class="buttom" value="激活Eas" onclick="enableEas('${plasAcctId}')"/>
									<input id="btnDisableEas" style="display:inline;" type="button" class="buttom" value="禁用Eas" onclick="disableEas('${plasAcctId}')"/>
									<input id="btnSynEasPwd" style="display:inline;" type="button" class="buttom" value="同步Eas密码" onclick="synEasPwd('${plasAcctId}')"/>
								</s:if>
							</s:if>
							<%-- 操作提示 --%>
							<span style="display:block;" id="eas_operate_tip"></span>
			</td>
		</tr>
		<tr align="left">
			<td>外部邮箱:</td>
			<td colspan="2">
				<s:textfield key="email" id="email" name="email" size="40" cssStyle="width: 150px; float: left;" readonly="true"/>
				<div id="emailTip" style="float: left;"></div>
				<s:if test="plasAcctId != null && plasAcctId!= ''">
					<b style="color: red;padding:0 5px 0 5px;" id="emailDesc"><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapEmailFlg()" value="emailFlg"/></b>
				</s:if>
				<s:if test="plasAcctId != null && plasAcctId != ''">
					<%-- 若未开通,则可激活 --%>
					<s:if test="emailFlg == 0 || emailFlg=='' || emailFlg == null ">
						<input id="btnOpenEmail" style="display:inline;" type="button" class="buttom" value="开通邮箱" onclick="openEmail('${plasAcctId}')"/>
						<input id="btnEnableEmail" style="display:none;" type="button" class="buttom" value="激活邮箱" onclick="enableEmail('${plasAcctId}')"/>
						<input id="btnDisableEmail" style="display:none;" type="button" class="buttom" value="禁用邮箱" onclick="disableEmail('${plasAcctId}')"/>
						<input id="btnSynEmailPwd" style="display:none;" type="button" class="buttom" value="同步密码" onclick="synEmailPwd('${plasAcctId}')"/>
					</s:if>
					
					<%-- 若禁用,则可激活 --%>
					<s:if test="emailFlg == 2">
						<input id="btnOpenEmail" style="display:none;" type="button" class="buttom" value="开通邮箱" onclick="openEmail('${plasAcctId}')"/>
						<input id="btnEnableEmail" style="display:inline;" type="button" class="buttom" value="激活邮箱" onclick="enableEmail('${plasAcctId}')"/>
						<input id="btnDisableEmail" style="display:none;" type="button" class="buttom" value="禁用邮箱" onclick="disableEmail('${plasAcctId}')"/>
						<input id="btnSynEmailPwd" style="display:none;" type="button" class="buttom" value="同步密码" onclick="synEmailPwd('${plasAcctId}')"/>
					</s:if> 
					
					<%-- 若激活,则可禁用 --%>
					<s:if test="emailFlg == 1">
						<input id="btnOpenEmail" style="display:none;" type="button" class="buttom" value="开通邮箱" onclick="openEmail('${plasAcctId}')"/>
						<input id="btnEnableEmail" style="display:none;" type="button" class="buttom" value="激活邮箱" onclick="enableEmail('${plasAcctId}')"/>
						<input id="btnDisableEmail" style="display:inline;" type="button" class="buttom" value="禁用邮箱" onclick="disableEmail('${plasAcctId}')"/>
						<input id="btnSynEmailPwd" style="display:inline;" type="button" class="buttom" value="同步邮箱密码" onclick="synEmailPwd('${plasAcctId}')"/>
					</s:if>
					<%-- 操作提示 --%>
					<span style="display:block;" id="email_operate_tip"></span>
				</s:if>
			</td>
		</tr>
		<tr  >
			<td colspan="2">
			<div class="toolbar">
				
					<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-save" onclick="saveAcct()">保存</a>
					<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-undo" onclick="resetAcct()">重置</a>
				<security:authorize ifAnyGranted="A_ADMIN">
				</security:authorize>
			</div>
			</td>
		</tr>
	</table>
</form>




</div>

<script type="text/javascript">

var menuOptions={
		url:'${ctx}/plas/plas-acct!save.action',
		onSubmit: function(){
			var flag = $('#acctForm').form('validate');
			return flag;
		},
		success:function(data){
			if(data != 'failure'){
				$.messager.alert('提示','保存成功!');
				$.ajaxSettings.async = false;
				$('#leftTree').tree('reload');
				var node = $('#leftTree').tree('find',data);
				if(node){
					$('#leftTree').tree('expandTo',node.target);
					$('#leftTree').tree('select',node.target);
				}
				$.ajaxSettings.async = true;
			}else{
				$.messager.alert('提示','保存失败!');
			}
		}
	
}
function saveAcct(){
	$('#acctForm').form('submit',menuOptions);
}
//解冻或者冻结账号：flag:0 冻结，1激活
function freezeAcct(flag){
	if(flag){
		var url = '${ctx}/plas/plas-acct!freezeAcct.action?id='+'${plasAcctId}'+'&flag='+flag;
		
		if(flag=='0'){
			//冻结账号
			$.messager.confirm('确认','冻结账号，您确定继续吗?',function(r){
				if(r){
					$.post(url,function(result){
						var rObj = eval(result);
						if(rObj.success){
							$('#leftTree').tree('reload');
							$.messager.alert('info',rObj.success);
						}else{
							$.messager.alert('Info',rObj.failure);
						}
					});
				}
			});
		}else if(flag="1"){
			//解冻账号
			$.post(url,function(result){
				var rObj = eval(result);
				if(rObj.success){
					$('#leftTree').tree('reload');
					$.messager.alert('info',rObj.success);
				}else{
					$.messager.alert('Info',rObj.failure);
				}
			});
		}
		
	}
}
//启用或者注销账号
function activeAcct(flag){
	alert(flag);
	if(flag){
		var url = '${ctx}/plas/plas-acct!openOrDisable.action?id='+'${plasAcctId}'+'&flag='+flag;
		$.messager.confirm('确认','注销账号，您确定继续吗?',function(r){
			if(r){
				$.post(url,function(result){
					var rObj = eval(result);
					if(rObj.success){
						$('#leftTree').tree('reload');
						$.messager.alert('info',rObj.success);
					}else{
						$.messager.alert('Info',rObj.failure);
					}
				});
			}
		});
	}
	
}
//开通eas账号
function openEas(plasAcctId){
	if(plasAcctId){
		operaEas(plasAcctId,'1');
	}
}
//激活Eas
function enablesEas(plasAcctId){
	if(plasAcctId){
		operaEas(plasAcctId,'2');
	}
}
//禁用eas
function disableEas(plasAcctId){
	if(uiid){
		operaEas(uiid,'3');
	}
}
//同步eas密码
function sysEasPwd(plasAcctId){
	if(plasAcctId){
		operaEas(plasAcctId,'4');
	}
}
//
/**
*	eas账号操作
	opera	标示
	0		开通eas
	1		激活eas
	2		禁用eas
	3		同步eas密码
	4		同步eas密码(等待用户登录)
	5		同步eas用户信息(直接拷贝)
**/
function operaEas(plasAcctId,opera){
	var url = '${ctx}/plas/plas-acct!easOpera.action?id='+plasAcctId+'&flag='+opera;
	$.post(url,function(data){
		var result = eval(data);
		
		if(result.success){
			$.messager.alert('提示',result.success);
		}else{
			$.messager.alert('提示',result.failure);
		}
	});
}
//开通邮箱
function openEmail(plasAcctId){
	if(plasAcctId){
		operaEmail(plasAcctId,'0');
	}
}
//激活邮箱
function enableEmail(plasAcctId){
	if(plasAcctId){
		operaEmail(plasAcctId,'1');
	}
}
//禁用邮箱
function disableEmail(plasAcctId){
	if(plasAcctId){
		operaEmail(plasAcctId,'2');
	}
}
//同步邮箱密码
function sysEmailPwd(plasAcctId){
	if(plasAcctId){
		operaEmail(plasAcctId,'3');
	}
}
/**
 * 邮箱相关操作
 	flag	标示
 	0		开通邮箱
 	1		激活邮箱
 	2		禁用邮箱
 	3		同步邮箱密码
 */
 function operaEmail(plasAcctId,flag){
	var url = '${ctx}/plas/plas-acct!emailOpera.action?id='+plasAcctId+'&flag='+flag;
	$.post(url,function(result){
		var data = eval(result);
		if(data.success){
			$.messager.alert('提示',data.success);
		}else {
			$.messager.alert('提示',data.failure);
		}
	});
}
function resetAcct(){
	$('#acctForm')[0].reset();
}
</script>