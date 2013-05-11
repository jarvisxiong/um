<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="org.springframework.security.ui.AbstractProcessingFilter"%>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page import="org.springframework.security.AuthenticationException"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>宝龙信息系统认证授权平台</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/login/style.css"/>
	<script src="${ctx}/js/jquery/jquery-lasted.min.js" type="text/javascript"></script>
	<script src="${ctx}/js/jquery/jquery.cookies.js" type="text/javascript"></script>
</head>

<body>
	<div class="login_page">
	<div class='login_main'>
		<div class=login_top>
		</div>
		<div class=login_middle>
			<div class="login_middleleft">
			</div>
			<div 
			class="login_middleright"
			>
				<form id="login_form" name="login_form" class="login_form" action="${ctx}/j_spring_security_check" method="post" onsubmit="return validateForm();">
					<table style="width:100%;">
						<tr style="line-height: 22px;">
							<td style="width:49px;text-align: right;">用户名：</td>
							<td style="text-align: left;"><input type="text" name="j_username" id="j_username" style="height:23px;width:186px;"/></td>
						</tr>
						<tr>
							<td style="padding-top:10px;text-align: right;">密&nbsp;&nbsp;&nbsp;码：</td>
							<td style="padding-top:10px;text-align: left;"><input type=password name="j_password" id="j_password" style="height:23px;width:186px;"/></td>
						</tr>
						<tr>
							<td></td>
							<td style="padding-top:15px;text-align: left;">
								<div id="btnLogin"
									 style="cursor: pointer;width:100px;height:40px;line-height:40px; font-size:14px; text-align: center;" 
									 onclick="$('#login_form').submit();"
									 onmouseover="$(this).addClass('login_btnLogin');"
									 onmouseout="$(this).removeClass('login_btnLogin');"
									 >
									登录
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center;">
								<div style="margin-top:70px; color: #909090;">宝龙集团发展有限公司版权所有©2011-2012</div>
							</td>
						</tr>
					</table>
				</form>
				<%if (session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {%>
						<div class=login_info>登录失败，请重试.</div>
				<%
					}
				%>
				<div class=login_info id="username_empty" style="left:250px;display: none;">用户名不能为空</div>
				<div class=login_info id="login_wait" style="display: none;">登录中,请稍后...</div>
				<div class=login_info id="pwd_empty" style="left:500px;display: none;">密码不能为空</div>
			</div>

		</div>
	</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$('#j_username').val($.cookie('_pl_username'));
			//$('#j_password').val('123');
			//$('#btnLogin').click();
			
			$("#j_username").keyup(function(event){
			  if(event.keyCode == 13){
				  if($.trim($(this).val())!= ''){
				    $("#j_password").focus();
				  }
			  }
			});
			$("#j_password").keyup(function(event){
			  if(event.keyCode == 13){
				  if($.trim($(this).val())!= ''){
				    $("#btnLogin").click();
				  }
			  }
			});
		});
		function validateForm(){
			$('.login_info').hide();
			var $name = $('#j_username');
			if($.trim($name.val()) == ''){
				$name.focus();
				$('#username_empty').show();
				return false;
			}
			if($('#j_password').val() == ''){
				$('#j_password').focus();
				$('#pwd_empty').show();
				return false;
			}
			$('#login_wait').show();
			$.cookie('_pl_username',$name.val(),{expires: 10});
			return true;
		}
	</script>
</body>
</html>