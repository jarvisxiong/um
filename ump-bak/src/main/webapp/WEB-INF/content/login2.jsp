<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.hhz.ump.util.Constants"%>


<%@page import="com.hhz.ump.util.LoginUtil"%>
<%@page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>宝龙管理平台</title>
<%@ include file="/common/meta.jsp"%>
<link href="${ctx}/css/desk/thickbox.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/dmm-green/ymPrompt.css" id='skin' />
<script type="text/javascript" src="${ctx}/js/cookie.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<link href="${ctx}/css/desk/thickbox.css" rel="stylesheet" type="text/css" />
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

	$(function() {
		//if (!verifyCorrectBrowser()){
		//	$("#ieDownlond").show();
		//}
		doOnload();
		idNoValidate();
		foucusInput();
	});

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
-->
</style>
</head>
<body>

<!--收集mac地址-->
<div id="divJre" style="width:0;height:0;">
</div>
<div style="display:none;">
<button id="btn_login1" class="login_in_normal"></button>
<button id="btn_login2" class="login_in_press"></button>
<button id="login_button_ie8_normal" class="login_button_ie8_normal"></button>
<button id="login_button_ie8_hover" class="login_button_ie8_hover"></button>
<button id="login_button_help_normal" class="login_button_help_normal"></button>
<button id="login_button_help_hover" class="login_button_help_hover"></button>
</div>
<div id="login_div" style="visibility:hidden; background-image:url('${ctx}/images/login3/pd2_1_back.jpg'); width:856px; height:628px; position:absolute; left:0px; top:0px;">
	<div style="position:relative;">
	<%if(!LoginUtil.isFirstlogin(request)){ %>
		<form id="frm" action="<c:url value='j_spring_security_check'/>" method="post">
	    	<div id="login_name_div" style="position:absolute; left:114px; top:125px;">
	    		<div style="font-size:28px;">登录</div>
	    		<div style="height:10px;"></div>
	    		<div>用户名:</div>
	    		<div style="height:2px;"></div>
  				<input type="text" id="login_name" name="j_username" value='<c:if test="${not empty param.error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' style="width:250px;" onkeypress="onkeypress_login('login_name',event)" onfocus="this.select();"/>
	        	<input type="hidden" id="idNo" />
	        </div>
	       
			<div id="password_div" style="position:absolute; left:114px; top:217px;">
	    		<div>密&nbsp;&nbsp;&nbsp;&nbsp;码:</div>
	    		<div style="height:2px;"></div>
				<s:password id="password" name="j_password" cssStyle="width:250px;" size="13" value="" onkeypress="onkeypress_login('password',event)"/>
	        </div>
			<div id="saveCookie_div" style="position:absolute; left:113px; top:280px; cursor:pointer;">
				<div style="float:left; margin-top:-2px;"><input id="saveCookie" type="checkbox" /></div>
				<div style="float:left;">&nbsp;<label for="saveCookie">记住用户名</label></div>
	        </div>
	  </form>
	<%}%>
		<div style="position:absolute; top:95px; width: 100%; text-align:center;">
	      <font color="red">
	      	<%if(LoginUtil.getErrorInfo(request)!=null){ %>
	      		<%=LoginUtil.getErrorInfo(request)%>
	      	<%}%>
	      </font>
		</div>
		
		<div style="position:absolute; left:366px; top:110px; display:none;" id="loginLoadingDiv">
			<span style="font-size:14px;font-weight:bold;">正在登录,请稍候...</span>
		</div>
		<div style="position:absolute; left:446px; top:340px; ">
			<div><span onclick="window.open('${ctx}/common/loginFlash.jsp');" style="cursor:pointer;">点击下载PowerDesk2.1 入门指引(flash)</span></div>
			<div>本指引可用IE浏览器、flash播放器、超级解码、完美解码</div>
			<div>等播放器打开。<a href="/app/download.action?fileName=flashplayer.exe&amp;realFileName=flashplayer.exe&amp;bizModuleCd=public">点击下载flash.10播放器</a></div>
		</div>
		<!-- 
		<div style="position:absolute; left:416px; top:360px; ">
			<a href="http://test.powerlong.com" target="_blank" style="font-size:14px; font-weight:bolder;">点击进入测试系统：http://test.powerlong.com</a>
		</div>
		 -->
		<div style="position:absolute; left:294px; top:268px; width:71px; height:42px; cursor:pointer; color:#fff; line-height:42px; text-align:center;" onclick="user_login();">
			<span style="font-size:14px;">登录</span>
		</div>
        <s:url id="down" action="show" namespace="/app"  includeParams="none" escapeAmp="true"  >
				  	  <s:param name="fileName">PdHelp.pdf</s:param>
				  	  <s:param name="realFileName">PdHelp.pdf</s:param>
				  	  <s:param name="bizModuleCd">public</s:param>
				  	  <s:param name="filterType">pdf</s:param>
				  	  <s:param name="operator">inline;</s:param>
		</s:url>
	    <div style="position:absolute; left:116px; top:435px; font-size:12px;">
	    	<div class="chkGroup" title="如果您的网络速度不理想，可以试着换一个网络链路">
	    	<div style="float:left; ">选择网段：</div>
	    	<span style="float:left; margin-top:-2px;"><input type="checkbox" class="group" id="telecom" name="network" value="telecom" /></span>
	    	<span style="float:left;">&nbsp;<label for="telecom">电信</label>&nbsp;&nbsp;</span>
	    	<span style="float:left; margin-top:-2px;"><input type="checkbox" class="group" id="netcom" name="network" value="netcom"/></span>
	    	<span style="float:left;">&nbsp;<label for="netcom">网通</label>&nbsp;&nbsp;</span>
	    	<span style="float:left; margin-top:-2px;"><input type="checkbox" class="group" id="backup" name="network" value="backup"/></span>
	    	<span style="float:left;">&nbsp;<label for="backup">备用</label></span>
	    	</div>
	    	<div style="height:6px;clear:both;"></div>
	    
			<div>本系统需要运行在IE8以上浏览器，如果您是首次登录系统请确认您的浏览器版本。<a href="#" onclick="ieDownload();">浏览器下载</a>。</div>
		</div>
		
  </div>
<!-- <s:url id="JRE" action="download" namespace="/app"  includeParams="none"  >-->
<!--				  	  <s:param name="fileName">jre-6u20-windows-i586.exe</s:param>-->
<!--				  	  <s:param name="realFileName">jre-6u20-windows-i586.exe</s:param>-->
<!--				  	  <s:param name="bizModuleCd">public</s:param>-->
<!--</s:url>-->
</div>
<div id="loadingMask" style="position: absolute;top:0;left:0;z-index: 99;height:100%;width:100%;cursor: wait;display: none;"></div>

</body>
</html>
<script>
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
addClickAction();
</script>
