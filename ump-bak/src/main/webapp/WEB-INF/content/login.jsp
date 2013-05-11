<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.hhz.ump.util.Constants"%>
<%@page import="com.hhz.ump.util.LoginUtil"%>
<%@page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>宝龙管理平台</title>
<%@ include file="/common/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/dmm-green/ymPrompt.css" id='skin' />
<script type="text/javascript" src="${ctx}/js/cookie.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script language="javascript">
function getJREVersions() {
	function _64(_65) {
		var _66 = "JavaWebStart.isInstalled." + _65 + ".0";
		if (!ActiveXObject) {
			return false;
		}
		try {
			return (new ActiveXObject(_66) != null);
		} catch (exception) {
			return false;
		}
	}

	function _67(v1, v2) {
		var v1a = v1.split(".");
		var v2a = v2.split(".");
		var _68 = v1a.length;
		if (v2a.length > _68) {
			_68 = v2a.length;
		}

		for ( var i = 0; i < _68; i++) {
			if (v1a.length == i) {
				return v2;
			} else {
				if (v2a.length == i) {
					return v1;
				} else {
					if (v1a[i] < v2a[i]) {
						return v2;
					} else {
						if (v1a[i] > v2a[i]) {
							return v1;
						}
					}
				}
			}
		}
		return v1;
	}

	if (!navigator.javaEnabled()) {
		return "none";
	}
	var _69 = "undefined";
	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1) {
		if (_64("1.8.0")) {
			_69 = "1.8.0";
		} else {
			if (_64("1.7.0")) {
				_69 = "1.7.0";
			} else {
				if (_64("1.6.0")) {
					_69 = "1.6.0";
				} else {
					if (_64("1.5.0")) {
						_69 = "1.5.0";
					} else {
						if (_64("1.4.2")) {
							_69 = "1.4.2";
						} else {
							_69 = "none";
						}
					}
				}
			}
		}
	} else {
		if (navigator.mimeTypes) {
			for ( var i = 0; i < navigator.mimeTypes.length; i++) {
				s = navigator.mimeTypes[i].type;
				var p = /^application\/x-java-applet\x3Bversion=([0-9\.]+)/;
				var m = s.match(p);
				if (m != null) {
					if (_69 != "undefined") {
						_69 = _67(_69, RegExp.$1);
					} else {
						_69 = RegExp.$1;
					}
				}
			}
		}
	}
	return _69;
}



var rand = Math.random()*10000;
// 系统登录
function user_login(){
	$("#login_name").val($.trim($("#login_name").val()));
	$("#password").val($.trim($("#password").val()));
	var str = rand.toString();
	var len = str.length;
	var random;
	//判断网段
	var network = $(':input[name="network"]:checked').val();
	random=str.substring(0,str.indexOf("."));
	switch(random.length)
	{
	  case 1:random="000"+random;break;
	  case 2:random="00"+random;break;
	  case 3:random="0"+random;break;
	  default:random=random.substring(0,4);break;
	}
	
	if(checkData()){
		$("#loginLoadingDiv").show();
		$("#loadingMask").show();
		var login_nameValue = $("#login_name").val();   
        var passwordValue = $("#password").val();   
        //服务器验证（模拟）       
        if( $("#saveCookie")[0].checked){     
            setCookie("login_name",$("#login_name").val(),24,"/");   
        }
        //验证是否选择网段
        setCookie("network",network,24,"/");   
		switch(network){
		case "telecom":$("#frm").attr("action","http://pd.powerlong.com/j_spring_security_check");break;
		case "netcom":$("#frm").attr("action","http://wt.powerlong.com/j_spring_security_check");break;
		case "backup":$("#frm").attr("action","http://180.169.33.85/j_spring_security_check");break;
		default:break;
		}

        setCookie("password","",24,"/"); 


        
		$("#frm").submit();
	}
}


// 取消登录
function user_cancel(){
	document.location = "login.jsp";
}

//获得验证码
function checkcode(){
	document.write("<img style='margin-left: -5px;margin-top: -1px;' border=0 src='${ctx}/image.jsp?Rand=" + rand + "' align='absmiddle'>");
}

//验证数据合法性
function checkData(){
	if($.trim($("#login_name").val()) == ""){
		alert("登录名不能为空或者全部为空格，请重新填写!");
		$("#login_name").focus();
		return false;
	}
	if($.trim($("#password").val())  == ""){
		alert("密码不能为空或者全部为空格，请重新填写!");
		$("#password").focus();
		return false;
	}
	return true;
}


function onkeypress_login(id,e){
	var keyCode;
	if($.browser.msie){
		keyCode = event.keyCode;
	}else{
		keyCode = e.which;
	}
	if(keyCode==13){
		if(id=="login_name"){
			$("#password").focus();
		}else if(id=="password"){
			//$("#checkcode").focus();
			user_login();
		}else{
			user_login();
		}
	}
}
function onCancel(){
	document.getElementById("login_name").value="";
	document.getElementById("password").value="";
}

	function verifyCorrectBrowser() {
		if (navigator.appName == "Microsoft Internet Explorer")
			if (navigator.appVersion.indexOf("MSIE 6.") >= 0)
				return (true);
			else
				return (false);
	}

	function doOnload() {
		try{
			document.getElementById("login_name").value = getCookieValue("login_name");

			//选中cookie中默认网段
			var n = getCookieValue("network");
			$("#"+n).attr("checked","true");
			$("#"+n).siblings().attr('checked',false);			
			var curName=$("#"+n).attr("id");
			$("#"+n).parents(".chkGroup").find(".group").each(function(i){
				var tmpName=this.id;
				if (tmpName!=curName){
					$(this).attr('checked',false);
				}
			});
		}catch(e){}
		_PAGEWIDTH = document.documentElement.clientWidth;
		_PAGEHEIGHT = document.documentElement.clientHeight;
		var login_div_width = 856;
		var login_div_height = 628;//http://localhost:8080/PowerDesk/login.action
		var login_div_left = 0;
		var login_div_top = 0;
		login_div_left = (_PAGEWIDTH - login_div_width) / 2;
		document.getElementById("login_div").style.left = login_div_left + "px";
		document.getElementById("login_div").style.visibility = "visible";
		/*
		try{
			var ua = navigator.userAgent.toLowerCase();  
			var is360se = ua.indexOf("360se") > -1 ;	//判断是否是360浏览器
			if(is360se){
				document.getElementById("login_name_div").style.left = "117px";
				document.getElementById("password_div").style.left = "117px";
				document.getElementById("saveCookie_div").style.left = "112px";
				document.getElementById("saveCookie_div").style.top = "292px";
			}else{
				document.getElementById("saveCookie_div").style.left = "111px";
				document.getElementById("saveCookie_div").style.top = "293px";
			}
		}catch(e){}
		*/
	}
	function ieDownload(){
		ymPrompt.win({message:'${ctx}/common/IEdownload.jsp',width:300,height:220,title:'支持浏览器下载',iframe:true});
	}
	function submit(){
		$("#idNo").val($("#idNoValid").val());
	}
	function idNoValidate() {
		<%if(LoginUtil.isFirstlogin(request)){ %>
		ymPrompt.win( {
			message :"<div><br/><form action='j_spring_security_check' method='post'>请输入您的身份证号码：<br/><br/><input type='hidden' value='<%=LoginUtil.getUiid(request)%>' name='j_username'/><input type='hidden' value='<%=LoginUtil.getPwd(request)%>' name='j_password'/><input type='text' id='idNoValid' name='idNoValid'/><input type='submit' value='提交' /></form></div>",
			width : 200,
			height : 120,
			winPos:[550,250],
			title : '身份证校验',
			closeBtn: false
		});
		<%}%>
	}
	  function getApplet(){
          if (window.navigator.userAgent.indexOf("MSIE")>=1) {
              _6666.method();
          }
          else
              document._5555.method();
      }
      function foucusInput(){
		var $name =$('#login_name'); 
		var $password =$('#password'); 
		if($.trim($name.val()) == ''){
			$name.focus();
		}else{
			$password.focus();
		}
	}
      /*
	$(function (){
		
		if (getJREVersions()!='none'){
			TB_showMaskLayer('正在获取MAC地址');
			$.post("${ctx}/loadApplet.action",function(result) {
				$("#divJre").html(result);
				TB_removeMaskLayer();
			});	
		}
	});
	*/
	function doSaveCookieDiv(){
		var myHtml = $("#saveCookieDiv").html();
		if(null==myHtml || ""==myHtml || "undefined"==myHtml){
			$("#saveCookieDiv").html('√');
		}else{
			$("#saveCookieDiv").html('');
		}
		$("#saveCookie").click();
	}
	function addClickAction(){
		var chks;
		chks=$(".group");
		chks.click(function(){
			if($(this).attr('checked')){
				$(this).siblings().attr('checked',false);
				var curName=$(this).attr("id");
				$(this).parents(".chkGroup").find(".group").each(function(i){
					var tmpName=this.id;
					if (tmpName!=curName){
						$(this).attr('checked',false);
					}
				});
			}
		});
	}

	$(function() {
		//if (!verifyCorrectBrowser()){
		//	$("#ieDownlond").show();
		//}
		doOnload();
		idNoValidate();
		foucusInput();
		addClickAction();
		$("#bigPicImg").attr("src","${ctx}/resources/images/login3/pic5.png");
	});
</script>

<style type="text/css">
<!--
html{
	font: 12px Tahoma, Helvetica, Arial, "\5b8b\4f53", sans-serif;
	scrollbar-face-color:#C0C0C0;
	scrollbar-highlight-color:#FFFFFF;
	scrollbar-3dlight-color:#C0C0C0;
	scrollbar-darkshadow-color:#737373;
	scrollbar-shadow-color:#a6a6a6;
	scrollbar-arrow-color:#696969;
	scrollbar-track-color:#e0e0e0;
}
body{
	background-color:#e0e0e0;
	color:#0167a2;
	margin:0px;
	padding:0px;
	font-size:12px;
	overflow-x:hidden;
	overflow-y:hidden;
}
.loginDiv{ 
	background-color:#2c8bef;
}
.loginDiv:hover{
	background-color:#277cd7;
}
-->
</style>
</head>
<body>

<!--收集mac地址-->
<div id="divJre" style="width:0;height:0;">
</div>
<!--按钮预载入-->
<div style="display:none;">
<button id="btn_login1" class="login_in_normal"></button>
<button id="btn_login2" class="login_in_press"></button>
<button id="login_button_ie8_normal" class="login_button_ie8_normal"></button>
<button id="login_button_ie8_hover" class="login_button_ie8_hover"></button>
<button id="login_button_help_normal" class="login_button_help_normal"></button>
<button id="login_button_help_hover" class="login_button_help_hover"></button>
</div>
<!-- <div style="background-color:#2d8bef; width:100%; height:1px; position:absolute; top:91px; z-index:100;"></div> -->
<div id="login_div" style="visibility:hidden;  width:855px; height:628px; position:absolute; left:0px; top:0px;">
	<div style="height:111px;">
		<div style="float:left; margin-left:58px; margin-top:43px; width:151px; height:56px;"><img src="${ctx}/resources/images/login3/logo.png" width="151px" height="56px"/></div>
		<div style="float:right; margin-right:36px; margin-top:75px;"><img src="${ctx}/resources/images/login3/powerdesk3.png"/></div>
	</div>
	<div style="float:left; width:323px; height:400px;">
		<div style="padding-left:58px; font-size:14px;">
		<%if(!LoginUtil.isFirstlogin(request)){ %>
			<form id="frm" action="<c:url value='j_spring_security_check'/>" method="post">
				<div id="login_name_div" style="height:26px; line-height:26px;">
					用户名：&nbsp;
					<input type="text" id="login_name" name="j_username" style="width:189px; border: 1px solid #909090;" value='<c:if test="${not empty param.error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' onkeypress="onkeypress_login('login_name',event)" onfocus="this.select();"/>
					<input type="hidden" id="idNo" />
				</div>
				<div id="password_div" style="height:26px; line-height:26px; margin-top:8px;">
					密<span style="visibility:hidden;">一</span>码：&nbsp;
					<s:password id="password" name="j_password" cssStyle="width:189px; border: 1px solid #909090;" size="13" value="" onkeypress="onkeypress_login('password',event)"/>
				</div>
				<div id="saveCookie_div" style="height:26px; line-height:26px; margin-top:8px;">
					<div style="float:left;"><span style="visibility:hidden;">密一码：&nbsp;</span></div>
					<input id="saveCookie" type="checkbox" style="display:none;"/>
					<div id="saveCookieDiv" style="float:left; text-align:center; display:block; background-color:#f56518; width:26px; height:26px; margin-left:3px; border:none; color:#fff; cursor:pointer; font-weight:bolder; font-size:18px;" onclick="doSaveCookieDiv();"></div>
					<div style="float:left; text-align:center; cursor:pointer; font-size:12px;"><label for="saveCookie">&nbsp;&nbsp;记住用户名&nbsp;&nbsp;</label></div>
					<div class="loginDiv" style="float:left; text-align:center; display:block; width:94px; height:26px; border:none; color:#fff; cursor:pointer;" onclick="user_login();">登&nbsp;&nbsp;&nbsp;&nbsp;录</div>
				</div>
				<div class="chkGroup" style="margin-top:24px; font-size:12px;" title="如果您的网络速度不理想，可以试着换一个网络链路">
	    			选择网段：
		    		<span><input type="checkbox" class="group" id="telecom" name="network" value="telecom" /></span>
		    		<span>&nbsp;<label for="telecom">电信</label>&nbsp;&nbsp;</span>
		    		<span><input type="checkbox" class="group" id="netcom" name="network" value="netcom"/></span>
		    		<span>&nbsp;<label for="netcom">网通</label>&nbsp;&nbsp;</span>
		    		<span><input type="checkbox" class="group" id="backup" name="network" value="backup"/></span>
		    		<span>&nbsp;<label for="backup">备用</label></span>
				</div>
				<div style="clear:both; margin-top:8px; line-height:18px; font-size:12px;">本系统需要运行在IE8以上浏览器，如果您是首次登录系统请确认您的浏览器版本。<a href="#" onclick="ieDownload();">浏览器下载</a>。</div>
				<s:url id="down" action="show" namespace="/app"  includeParams="none" escapeAmp="true"  >
						  	  <s:param name="fileName">PdHelp.pdf</s:param>
						  	  <s:param name="realFileName">PdHelp.pdf</s:param>
						  	  <s:param name="bizModuleCd">public</s:param>
						  	  <s:param name="filterType">pdf</s:param>
						  	  <s:param name="operator">inline;</s:param>
				</s:url>
				<div style="height:26px; text-align:center; line-height:26px; margin-top:24px; display:none;" id="loginLoadingDiv">
					<span style="font-size:14px;font-weight:bold;">正在登录,请稍候...</span>
				</div>
				<div style="height:26px; text-align:center; line-height:26px; margin-top:8px; color:red;">
			      	<%if(LoginUtil.getErrorInfo(request)!=null){ %>
			      		<%=LoginUtil.getErrorInfo(request)%>
			      	<%}%>
				</div>
			</form>
		<%}%>
		</div>
	</div>
	<div style="float:left; width:480px; height:350px; margin-left:16px;">
		<div style="clear:both; width:100%; position:relative;">
			<img id="bigPicImg" width="480px" height="294px"/>
			<div style="position:absolute; display:block; width:480px; height:180px; left:0px; top:0px; cursor:pointer;" onclick="javascript:window.open('${ctx}/app/download.action?fileName=PD3.0.ppsx&amp;realFileName=PD3.0_intro.ppsx&amp;bizModuleCd=public');" title="点击下载PD3.0新特性说明">
					<font style="font-size:48px;">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</font>
			</div>
		</div>
		<div style="clear:both; width:100%; margin-top:16px;">
			<div style="margin-top:5px;"><a href="${ctx}/app/download.action?fileName=PD3.0.ppsx&amp;realFileName=PD3.0_intro.ppsx&amp;bizModuleCd=public" target="_blank">点击下载PowerDesk3.0版新特性说明</a></div>
		</div>
	</div>
	
	
</div>
<div id="loadingMask" style="position: absolute;top:0;left:0;z-index: 99;height:100%;width:100%;cursor: wait;display: none;"></div>
</body>
</html>
