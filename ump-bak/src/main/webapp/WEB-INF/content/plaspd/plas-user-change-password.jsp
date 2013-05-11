<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理区域</title>
	
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" charset="UTF-8" ></script>
	
	<!-- 密码强度 -->	
	<script type="text/javascript"  src="${ctx}/js/PasswordStrength.js"></script>

	<!-- //加载jQuery类库 -->
	<script type="text/javascript" src="${ctx}/js/formValidator/jquery_last.js" charset="UTF-8" ></script>
	<!-- //加载插件  -->
	<script type="text/javascript" src="${ctx}/js/formValidator/formValidator.js" charset="UTF-8" ></script>
	<!-- //加载扩展库 -->
	<script type="text/javascript" src="${ctx}/js/formValidator/formValidatorRegex.js" charset="UTF-8" ></script>
	
	<!-- //加载插件的样式库，如果你是自动构建提示层，请加载validatorAuto.css -->
	<link type="text/css" rel="stylesheet" href="${ctx}/js/formValidator/style/validator.css"></link>
</head>

<body>
		<s:if test="pwdExpiredFlag == 1">
			<%--
			<div class="common_tip">安全提醒:&nbsp;您的密码已过期,请修改密码后重新登录,谢谢!</div>
			 --%>
			 
			<div class="common_tip">
				<s:if test="pwdStrategyCd == 1">
				您的密码已过期,请立即更改密码（密码有效期为30天）
				</s:if>
				
				<s:if test="pwdStrategyCd == 2">
				您的密码已过期,请立即更改密码（密码有效期为60天）
				</s:if>
				
				<s:if test="pwdStrategyCd == 3">
				您的密码已过期,请立即更改密码（密码有效期为90天）
				</s:if>
				
				<s:if test="pwdStrategyCd == 9">
				根据EAS财务系统安全策略要求,<br/>EAS财务用户必须定期更改密码（密码有效期为90天）<br/>
				</s:if>
			</div>
		</s:if>
		<div id="passwordDiv" style="padding: 10px 0 0 10px;">
			<!--div><s:text name="uaapUaapUser.passwordChange" /></div-->
			<div class="passwordChange">
				<!-- 修改密码 -->
				<s:form id="inputForm" action="plas-user-change!savePaswordChange.action" method="post">	
				<!-- 是否密码过期 -->
				<input type="hidden" name="pwdExpiredFlag" value="${pwdExpiredFlag}"/>
				<table border="0" cellspacing="0" cellpadding="0">
			   		<tr>
			   			<td style="width:120px;height: 20px;">&nbsp;&nbsp;密码强度检测：</td>
			    		<td style="width:200px">
						   	<script language="javascript">
								var ps = new PasswordStrength(true,'100%','100%');
						   	</script>
						</td>			
						<td style="width:160px"></td>			   	
			   		</tr>
			   		<tr>
			   			<td>&nbsp;&nbsp;请输入旧密码：</td>
			   			<td><input type="password" name="oldPassword" id="oldPassword" size="20" style="width:193px;" onchange="validataOldPassWs()"/></td>
						<td><div id="oldPasswordTip" ></div></td>
			   		</tr>
			   		<tr>
			   			<td>&nbsp;&nbsp;请输入新密码：</td>
			   			<td><input type="password" name="newPassword" id="newPassword" size="20" style="width:193px;" onkeyup="ps.update(this.value);" /></td>
						<td><div id="newPasswordTip" ></div></td>
			   		</tr>
			   		<tr>
			   			<td>&nbsp;&nbsp;请确认新密码：</td>
			   			<td><input type="password"  name="newPassword2" id="newPassword2" size="20" style="width:193px;"/></td>
			   			<td><div id="newPassword2Tip" ></div></td>
			   		</tr>
					<tr align="center" height="50px">
						<td align="center" colspan="3">
							<input type="button" class="btn_blue" id="mySubmit" onclick="mysubmit()" value="提交" />
						</td>
					</tr>	
			   	</table>
			   	</s:form>
			   	
   	<script language="javascript">

	   	function doClean(){
	   		$("#oldPassword").val("");
	   		$("#newPassword").val("");
	   		$("#newPassword2").val("");
	   	}

		$.formValidator.initConfig({formid:"inputForm",onerror:function(msg){alert(msg);}});

		$("#newPassword").formValidator({onshow:"请输入新密码",onfocus:"请输入新密码",oncorrect:"已输入",onempty:""}).inputValidator({min:1,max:20,onerror:"长度不正确,请确认"});
		
		$("#newPassword2").formValidator({onshow:"请确认新密码",onfocus:"请确认新密码",oncorrect:"已输入",onempty:""}).inputValidator({min:1,max:20,onerror:"长度不正确,请确认"})
		.compareValidator({desid:"newPassword",operateor:"=",onerror:"您2次密码不一致"});
		var flag;
		function validataOldPassWs(){
			var oldPassword =$("#oldPassword").val();
			$.post("${ctx}/plaspd/plas-user-change!isOldPasswordCorrect.action",{oldPassword:oldPassword} , function(result) {
				if(result=='false'){
					alert('旧密码不对！');
					flag= 'false';
				}else{
					flag= 'true';
				}
			});
		}
		function mysubmit(){
			validataOldPassWs();
			if(flag=='false'){
				return;
			}
			/*
			//若果是1-弱,提示用户.
			if( ps.getSelectedIndex() == 1){
				alert("您的密码强度不够!");
				$("#inputForm").submit();
			}
			*/
			
			//密码至少需要6位,强度不够
			if($.trim($("#newPassword").val()).length < 6){
				alert("为保证密码安全,请至少输入6位!");
			}else{
				$("#inputForm").submit();
			}
		}
   	</script>
			</div>
	</div>
</body>
</html>
