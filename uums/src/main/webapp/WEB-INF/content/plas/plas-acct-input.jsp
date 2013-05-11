<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div id="divDetailInfo" 
class="easyui-panel" 
title="<s:if test='plasAcctId == null'>创建</s:if><s:else>编辑</s:else>账号信息"
style="overflow:hidden;background-color: white;+position: relative" fit="true">

	<form id="acctForm" action="plas-acct!save.action" method="post">
		<input type="hidden" id="plasAcctId" name="plasAcctId" value="${plasAcctId}"/>
		<input type="hidden" name="id" value="${plasAcctId}"/>
		<input type="hidden" name="plasUserId" value="${plasUser.plasUserId}"/>
		
		<%-- "操作提示"代替原来的"弹出提示" --%>
		<span id="tip_operate_result" style="color:red;line-height: 40px;"></span>
		
		<table class="edit_table" style="width:100%;">
			<col width="100px"/>
			<col />
			<col width="90px"/>
			<col />
			
			<!-- 管理员不能注销 -->
			<s:if test=" uiid != 'super_admin' && uiid != 'admin'">
			<tr>
				<td colspan="4">
					<div style="float:left;">
						<!-- 操作提示 -->
						<span id="operate_result_tip" style="display: inline;margin-left:10px;"></span>
						
						<!-- 用户状态:0-未启用 1-正常 2-冻结 (3-解冻) 4.注销 -->
						<s:if test="plasAcctId != null && plasAcctId != ''">
						 	<a style="float:right;" href="#" class="easyui-linkbutton" plain="false" iconCls="icon-reload" onclick="refreshAcctArea('${plasAcctId}')">刷新</a>
						 
							<%-- 未启用 to 注销 --%>
							<s:if test="statusCd == 0">
								<a style="float:right;" href="#" class="easyui-linkbutton" plain="false" iconCls="icon-unfreeze" onclick="processAcct('${plasAcctId}','acctEnable','启用账号')">启用</a>
								<a title="禁止该用户所有操作权限" style="float:right;" href="#" class="easyui-linkbutton" plain="false" iconCls="icon-remove" onclick="processAcct('${plasAcctId}','acctClose','注销账号')">注销</a>
							</s:if>
							
							<%-- 正常   to 冻结/注销 --%>
							<s:if test="statusCd == 1">
								<a title="禁止该用户登录" style="float:right;" href="#" class="easyui-linkbutton" plain="false" iconCls="icon-freeze" onclick="processAcct('${plasAcctId}','acctFreeze','冻结账号')">冻结</a>
								<a title="禁止该用户所有操作权限" style="float:right;" href="#" class="easyui-linkbutton" plain="false" iconCls="icon-remove" onclick="processAcct('${plasAcctId}','acctClose','注销账号')">注销</a>
							</s:if>
							
							<%-- 冻结  to 解冻/注销 --%>
							<s:if test="statusCd == 2">
								<a style="float:right;" href="#" class="easyui-linkbutton" plain="false" iconCls="icon-unfreeze" onclick="processAcct('${plasAcctId}','acctEnable','解冻账号')">解冻</a>
								<a title="禁止该用户所有操作权限" style="float:right;" href="#" class="easyui-linkbutton" plain="false" iconCls="icon-remove" onclick="processAcct('${plasAcctId}','acctClose','注销账号')">注销</a>
							</s:if>
							
							<%-- 解冻  to 冻结/注销 --%>
							<s:if test="statusCd == 3">
								<a title="禁止该用户登录" style="float:right;" href="#" class="easyui-linkbutton" plain="false" iconCls="icon-freeze" onclick="processAcct('${plasAcctId}','acctFreeze','冻结账号')">冻结</a>
								<a style="float:right;" href="#" class="easyui-linkbutton" plain="false" iconCls="icon-remove" onclick="processAcct('${plasAcctId}','acctClose','注销账号')">注销</a>
							</s:if>
							
							<%-- 注销   to 正常 --%>
							<s:if test="statusCd == 4">
								<a style="float:right;" href="#" class="easyui-linkbutton" plain="false" iconCls="icon-add" onclick="processAcct('${plasAcctId}','acctEnable','启用账号')">启用</a>
							</s:if>
						 
							<%-- 重置密码 --%>
							<a title="由系统发送随机密码至手机" style="float:right;" href="#" class="easyui-linkbutton" plain="false" iconCls="icon-config" onclick="processAcct('${plasAcctId}','acctResetPassword','重置密码')">重置密码</a>
							
							<security:authorize ifAnyGranted="A_ADMIN">
								<a title="手动重置密码" href="#" class="easyui-linkbutton" plain="false" iconCls="icon-config" onclick="$(this).next().show();">手动重置密码&nbsp;</a>
								<div id="handArea" style="display: none;line-height: 40px;">
									<span>请手动设置密码：</span>
									<input type="text" id="inputResetPwd" value="654321"/>
									<input type="button" onclick="processAcct('${plasAcctId}','acctResetPasswordHand','手动重置密码')" value="确定"/>
									<input type="button" onclick="$(this).parent().hide();" value="取消"/>
								</div>
							</security:authorize>
						</s:if>
					</div>
					<div style="float:left;margin-left:10px;">
						
						<%--
						<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-save" onclick="saveAcctForm()">保存</a>
						<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-undo" onclick="resetAcctForm()">重置</a>
						 --%>
						
						<%--
						[申请状态：<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapAcctAuditFlg()" value="applyStatusCd"/>]
						 --%>
						[当前状态: <p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapAcctStatus()" value="statusCd"/>]
					</div>
				</td>
			</tr>
			</s:if>
			
			<tr>
				<td>登录账号:</td>
				<td>${uiid}
					<security:authorize ifAnyGranted="A_ADMIN">
						<s:if test="easFlg == 0 && mysoftFlg == 0 && emailFlg == 0">
							<input id="btnChgUiid" type="button" value="修改账号" onclick="$('#divChg').show();$(this).hide();"/>
							<div id="divChg" style="display:none;">
								<span style="color:red;">请输入新账号:</span>
								<input id="chgToUiid" type="text" style="width:80px" />
								<input type="button" value="保存" onclick='
									var oldUiid = "${uiid}";
									var newUiid = $("#chgToUiid").val();
									if($.trim(oldUiid) == $.trim(newUiid)){
										alert("无变化!");
										return;
									}
									if(window.confirm("请先确认是否删除外部邮箱! 是否继续?")){
										$.post("${ctx}/plas/plas-acct!chgUiid.action", {oldUiid: oldUiid, newUiid: newUiid} ,function(result){
											if("success" == result){
												refreshAcctArea("${plasAcctId}");//plas-acct.jsp
											}else{
												alert(result);	
											}
										});
									}
								''/>
								<input type="button" value="取消" onclick="$('#btnChgUiid').show();$('#divChg').hide();"/>
							</div>
						</s:if>
					</security:authorize>
				</td>
				<td>姓名:</td>
				<td>${plasUser.userName}</td>
			</tr>
			<%--
			<tr>
				<td>自定义</td>
				<td><input style="width:90%;" type="text" id="custLoginName" name="custLoginName" value="${custLoginName}" /></td>
				<td>账号状态:</td>
				<td style="font-weight: bolder;color:blue;">
					<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapAcctStatus()" value="statusCd"/>
				</td>
			</tr>
			 --%>
			<input type="hidden" id="sequenceNo" name="sequenceNo" value='<s:if test="sequenceNo == null">0</s:if><s:else>${sequenceNo}</s:else>'/>
			<%--
			<tr>
				<td width="120">排序序号</td>
				<td>
					<input type="text" id="sequenceNo" name="sequenceNo" value="${sequenceNo}" class="easyui-numberbox" validType="length[1,10]"/>
				</td>
			</tr>
			 --%>
			<security:authorize ifAnyGranted="A_ADMIN">
			<tr>
				<td valign="top">密码策略:</td>
				<td valign="top"><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapPwdStrategyCd()" listKey="key" listValue="value" id="pwdStrategyCd" name="pwdStrategyCd"/>
				</td>
				<td valign="top">最近修改日期:</td>
				<td valign="top"><s:textfield id="pwdLastModDate" name="pwdLastModDate" cssClass="easyui-datebox" /><br/>(格式:yyyy-MM-dd)</td>
			</tr>
			<tr>
				<td valign="top">启用日期:</td>
				<td valign="top"><s:textfield id="effectDate"  name="effectDate"  cssClass="easyui-datebox" cssStyle="width:150px;"/><br/>(格式:yyyy-MM-dd)</td>
				<td valign="top">失效日期:</td>
				<td valign="top"><s:textfield id="invalidDate" name="invalidDate" cssClass="easyui-datebox" cssStyle="width:150px;"/><br/>(格式:yyyy-MM-dd)</td>
			</tr>
			<s:if test="plasAcctId!=null && plasAcctId != ''">
			<!-- eas -->
			<tr class="panel-header">
				<td colspan="4" style="padding-left:5px;">（1）金蝶财务(EAS)</td>
			</tr>
			<tr>
				<td valign="top">是否开通:</td>
				<td colspan="3">
					<div id="easTip" style="float: left;"></div>
					<b style="color: red;padding:0 5px 0 5px;" id="easDesc"><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapEasFlg()" value="easFlg"/></b>
				 	
			 		<s:if test="easFlg == 0">
						<input id="btnEasOpen"    type="button" class="buttom" value="开通Eas" onclick="processEas('${plasAcctId}','easOpen','开通eas')"/>
					</s:if>
			 		<s:if test="easFlg == 2">
						<input id="btnEasEnable"  type="button" class="buttom" value="激活Eas" onclick="processEas('${plasAcctId}','easEnable','激活Eas')"/>
					</s:if>
			 		<s:if test="easFlg == 1">
						<input id="btnEasDisable" type="button" class="buttom" value="禁用Eas" onclick="processEas('${plasAcctId}','easDisable','禁用Eas')"/>
						<input id="btnEasSynAcct" type="button" class="buttom" value="同步Eas用户信息" onclick="processEas('${plasAcctId}','easSynAcct','同步Eas用户信息')"/>
						<input id="btnEasSynPwd"  type="button" class="buttom" value="重置Eas密码" onclick="processEas('${plasAcctId}','easResetPassword','重置Eas密码')"/>
					</s:if>
					
					<%-- 脚本见:plas-acct.jsp --%>
					<input type="button" onclick="validateOpenEas('${uiid}')" value="检查是否开通EAS"/>
					<s:if test="easFlg == 0 || easFlg == null">
						<input type="button" onclick="markOpenEas('${uiid}','${plasAcctId}')" value="手动关联EAS"/>
					</s:if>
					
					<%-- 操作提示 --%>
					<span style="display:block;" id="eas_operate_tip"></span>
				</td>
			</tr>
			<tr>
				<td valign="top">同步密码:</td>
				<td colspan="3" id="td_easPasswordSetFlg" style="padding-left:5px;">
					<s:if test="easPasswordSetFlg == 0">
						未同步
					</s:if>
					<s:elseif test="easPasswordSetFlg == 1">
						已同步
					</s:elseif>
					<s:else>
						${easPasswordSetFlg }
					</s:else> 
				</td>
			</tr>
			<!-- 明源软件  开始-->
			<tr class="panel-header">
				<td colspan="4" style="padding-left:5px;">（2）明源软件(Mysoft)</td>
			</tr>
			<tr>
				<td valign="top">是否开通:</td>
				<td colspan="3">
					<div id="mysoftTip" style="float: left;"></div>
					<b style="color: red;padding:0 5px 0 5px;" id="mysoftDesc">
						<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapMysoftFlg()" value="mysoftFlg"/>
					</b>
				 	
			 		<s:if test="mysoftFlg == 0">
						<input id="btnMysoftOpen"    type="button" class="buttom" value="开通Mysoft" onclick="processMysoft('${plasAcctId}','mysoftOpen','开通mysoft')"/>
					</s:if>
			 		<s:if test="mysoftFlg == 2">
						<input id="btnMysoftEnable"  type="button" class="buttom" value="激活Mysoft" onclick="processMysoft('${plasAcctId}','mysoftEnable','激活Mysoft')"/>
					</s:if>
			 		<s:if test="mysoftFlg == 1">
						<input id="btnMysoftDisable" type="button" class="buttom" value="禁用Mysoft" onclick="processMysoft('${plasAcctId}','mysoftDisable','禁用Mysoft')"/>
						<input id="btnMysoftSynAcct" type="button" class="buttom" value="同步Mysoft用户信息" onclick="processMysoft('${plasAcctId}','mysoftSynAcct','同步Mysoft用户信息')"/>
						<input id="btnMysoftSynPwd"  type="button" class="buttom" value="同步Mysoft密码" onclick="processMysoft('${plasAcctId}','mysoftResetPassword','同步Mysoft密码')"/>
					</s:if>
					
					<%-- 脚本见:plas-acct.jsp
					<input type="button" onclick="validateOpenMysoft('${uiid}')" value="检查是否开通Mysoft"/>
					--%>
					<s:if test="mysoftFlg == 0 || mysoftFlg == null">
					<input type="button" onclick="markOpenMysoft('${uiid}','${plasAcctId}')" value="手动关联Mysoft"/>
					</s:if>
					
					<%-- 操作提示 --%>
					<span style="display:block;" id="mysoft_operate_tip"></span>
				</td>
			</tr>
			<tr>
				<td valign="top">同步密码:</td>
				<td colspan="3" id="td_mysoftPasswordSetFlg" style="padding-left:5px;">
					<s:if test="mysoftPasswordSetFlg == 0">
						未同步
					</s:if>
					<s:elseif test="mysoftPasswordSetFlg == 1">
						已同步
					</s:elseif>
					<s:else>
						${mysoftPasswordSetFlg }
					</s:else> 
				</td>
			</tr>
			<!-- 明源软件 结束 -->
			<%-- exchange
			<tr class="panel-header" style="padding-left:5px;">
				<td colspan="4">（3）外部邮箱(Exchange)</td>
			</tr>
			<tr>
				<td valign="top">外部邮箱:</td>
				<td colspan="3">
				
					<div id="email" style="float: left;">${email}</div>
					<!--
					<input id="email" name="email" value="${email}" size="40" style="width: 150px; float: left;" readonly="readonly"/>
					 -->
					 
					<div id="emailTip" style="float: left;"></div>
					<b style="color: red;padding:0 5px 0 5px;" id="emailDesc"><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapEmailFlg()" value="emailFlg"/></b>
			 		<s:if test="emailFlg == 0">
						<input id="btnEmailOpen"    type="button" class="buttom" value="开通邮箱" onclick="processEmail('${plasAcctId}','emailOpen','开通邮箱')"/>
			 		</s:if>
			 		<s:if test="emailFlg == 2">
						<input id="btnEmailEnable"  type="button" class="buttom" value="激活邮箱" onclick="processEmail('${plasAcctId}','emailEnable','激活邮箱')"/>
					</s:if>
			 		<s:if test="emailFlg == 1">
						<input id="btnEmailDisable" type="button" class="buttom" value="禁用邮箱" onclick="processEmail('${plasAcctId}','emailDisable','禁用邮箱')"/>
						<input id="btnEmailSynAcct" type="button" class="buttom" value="同步用户信息" onclick="processEmail('${plasAcctId}','emailSynAcct','同步邮箱用户信息')"/>
						<input id="btnEmailSynPwd"  type="button" class="buttom" value="同步密码" onclick="processEmail('${plasAcctId}','emailResetPassword','同步邮箱密码')" title="同步后，请通知用户重新登录PD，系统自动将密码同步到邮箱"/>
						<input id="btnSynEmailToContact"  type="button" class="buttom" value="同步邮箱至通讯录" onclick="synEmailToContact('${plasAcctId}')"/>
				 	</s:if>
				 	
					<!-- 操作提示 -->
					<span style="display:block;color:red;" id="email_operate_tip"></span>
				</td>
			</tr>
			<tr>
				<td valign="top">同步密码:</td>
				<td colspan="3" id="td_emailPasswordSetFlg" style="padding-left:5px;">
					<s:if test="emailPasswordSetFlg == 0">
						未同步
					</s:if>
					<s:elseif test="emailPasswordSetFlg == 1">
						已同步
					</s:elseif>
					<s:else>
						${emailPasswordSetFlg }
					</s:else> 
				</td>
			</tr>
			 --%>
			<!-- coremail -->
			<tr class="panel-header" style="padding-left:5px;">
				<td colspan="4">（4）新版邮箱(coremail)</td>
			</tr>
			<tr>
				<td valign="top">新版邮箱:</td>
				<td colspan="3">
					<div id="email" style="float: left;">${email}</div>
					<div id="cmailTip" style="float: left;"></div>
					<b style="color: red;padding:0 5px 0 5px;" id="cmailDesc"><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapEmailFlg()" value="cmailFlg"/></b>
			 		<s:if test="cmailFlg == 0">
						<input id="btnCmailOpen"    type="button" class="buttom" value="开通邮箱" onclick="processCmail('${plasAcctId}','cmailOpen','开通邮箱')"/>
			 		</s:if>
			 		<s:if test="cmailFlg == 2">
						<input id="btnCmailEnable"  type="button" class="buttom" value="激活邮箱" onclick="processCmail('${plasAcctId}','cmailEnable','激活邮箱')"/>
					</s:if>
			 		<s:if test="cmailFlg == 1">
						<input id="btnCmailDisable" type="button" class="buttom" value="禁用邮箱" onclick="processCmail('${plasAcctId}','cmailDisable','禁用邮箱')"/>
						<input id="btnCmailSynPwd"  type="button" class="buttom" value="同步密码" onclick="processCmail('${plasAcctId}','cmailResetPassword','同步邮箱密码')" title="同步后，请通知用户重新登录PD，系统自动将密码同步到邮箱"/>
				 	</s:if>
				 	
					<%-- 脚本见:plas-acct.jsp
					<input type="button" onclick="validateOpenCmail('${uiid}')" value="检查是否开通coremail"/>
					
					<s:if test="cmailFlg == 0 || cmailFlg == null">
					<input type="button" onclick="markOpenCmail('${uiid}','${plasAcctId}')" value="手动关联coremail"/>
					</s:if>
					--%>
					<%-- 操作提示 --%>
					<span style="display:block;color:red;" id="cmail_operate_tip"></span>
				</td>
			</tr>
			<tr>
				<td valign="top">同步密码:</td>
				<td colspan="3" id="td_cmailPasswordSetFlg" style="padding-left:5px;">
					<s:if test="cmailPasswordSetFlg == 0">
						未同步
					</s:if>
					<s:elseif test="cmailPasswordSetFlg == 1">
						已同步
					</s:elseif>
					<s:else>
						${cmailPasswordSetFlg }
					</s:else> 
				</td>
			</tr>
			</s:if>

			
			<tr class="panel-header" style="padding-left:5px;">
				<td colspan="4">其他</td>
			</tr>
			<tr>
				<td >MAC码是否锁定:</td>
				<td colspan="3">
					<s:radio list="@com.powerlong.plas.utils.DictMapUtil@getMapMacLockedFlg()" listKey="key" listValue="value" id="macLockedFlg" name="macLockedFlg"/>
				</td>
			</tr>
			<tr>
				<td valign="top">MAC地址:</td>
				<td colspan="3">
					<s:textarea key="macAddress"  id="macAddress" name="macAddress" cssStyle="height:30px;width:90%;" cssClass="easyui-validatebox"  validType="length[1,200]"/>
				</td>
			</tr>
			
			
			<s:if test="plasAcctId!=null && plasAcctId != ''">
			<tr>
				<td>密码连续错误:</td>
				<td>
					<input style="text-align:center; width:50px;" type="text" id="failureTimes" name="failureTimes" class="easyui-numberbox" required="true" value="${failureTimes}"/>&nbsp;次
				</td>
				<td>最近锁定时间:</td>
				<td>
					<s:date name="lockedDate" format="yyy-MM-dd hh:mm:ss"/>
				</td>
			</tr>
			<tr>
				<td>上次访问IP地址:</td>
				<td>${lastLoginIP}</td>
				<td>认证方式:</td>
				<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapAuthenticType()" listKey="key" listValue="value"  id="authenticTypeCd" name="authenticTypeCd"/></td>
			</tr>
			</s:if>
			</security:authorize>
			
			
			<s:if test="plasAcctId!=null && plasAcctId != ''"> 
			<tr>
				<td>上次登录时间:</td>
				<td><s:date name="lastLoginDate" format="yyyy-MM-dd hh:mm:ss" />
					<s:if test="plasAcctId!=null && plasAcctId != '' && lastLoginDate == null">
						<%--
						<s:textfield id="lastLoginDate" name="lastLoginDate" cssClass="easyui-datebox" /><br/>(格式:yyyy-MM-dd)
						 --%>
						<security:authorize ifAnyGranted="A_ADMIN">
						</security:authorize>
						<input type="button" id="btnIgnoreLoginValidate" onclick="ignoreLoginValidate('${plasAcctId}')" value="设置不需要登录验证"/>
						<span style="color:red;" id="ignoreLoginValidateTip"></span>
					</s:if>
				</td>
				<td>上次签退时间:</td>
				<td><s:date name="lastLogoutDate" format="yyyy-MM-dd hh:mm:ss" /></td>
			</tr>
			</s:if>
			<tr>
				<td valign="top">备注:</td>
				<td colspan="3">
					<textarea id="remark" name="remark"  style="width:90%;" class="easyui-validatebox" validType="length[1,200]">${remark}</textarea>
				</td>
			</tr>
		</table>
	</form>

	<br/>
	<br/>

	<security:authorize ifAnyGranted="A_ADMIN">
	<s:if test="plasAcctId!=null && plasAcctId != ''">
		<div style="line-height: 25px;margin:5px 0;">
			<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-reload" onclick="searchPositionList('${plasAcctId}')">刷新职位列表</a>
			<security:authorize ifAnyGranted="A_ADMIN">
			<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-save" onclick="showPositionTree('${plasAcctId}')">配置账号持有的职位</a>
			</security:authorize>
		</div>
	</s:if> 
	</security:authorize>
	
	<div id="relPositionListDiv"  style="height:400px;+position:relative;overflow-y: auto;border: 1px solid #99BBE8;">
		<%@ include file="plas-acct-positionList.jsp"%>
	</div>
</div>

<script type="text/javascript">
$(function(){
	//选择职位
	$('#wConfigPos').window({
		title: '请选择职位',
		modal: true,
		closed: true,
		cache: false,
		top: 10,
		iconCls:"icon-save",
		onOpen:function(){
			$('body').mask();
		},
		onClose:function(){
			$('body').unmask();
		}
	});
	$('#effectDate').datebox({
		formatter: function(date){ 
			return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
		},   
		parser: function(date){ 
			return new Date(Date.parse(date.replace(/-/g,"/"))); 
		}
	});  
	$('#invalidDate').datebox({
		formatter: function(date){ 
			return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
		},   
		parser: function(date){ 
			return new Date(Date.parse(date.replace(/-/g,"/"))); 
		}
	});  
});
//保存账户信息
function saveAcctForm(){
	$('#acctForm').form('submit',{
		url:'${ctx}/plas/plas-acct!save.action',
		onSubmit: function(){
			var flag = $('#acctForm').form('validate');
			return flag;
		},
		success:function(result){
			if(result != 'failure'){
				$.messager.alert('提示','保存成功!');
				reloadAcctTree();
			}else{
				$.messager.alert('提示','保存失败!'+result);
			}
		}
	});
}
//重置
function resetAcctForm(){
	$('#acctForm')[0].reset();
}

</script>
