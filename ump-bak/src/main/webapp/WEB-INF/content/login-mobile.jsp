<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>

<%@page import="com.hhz.ump.util.LoginUtil"%><html>
<head>
<title>宝龙管理平台</title>
<meta name="HandHeldFriendly" content="true">
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="${ctx}/resources/css/wap/login_mobile.css" />
<script>
function gotoWeb(){
	window.location.href='${ctx}';
}
</script>
</head>
<body style="background-color: #cccccc;">
	<div id="content" >
	<div id="div_logo"></div>
	<form id="frm" action="<c:url value='j_spring_security_check'/>" method="post" class="loginForm">
			<div style="top:95px; width: 100%; text-align:left;">
		      <font color="red">
		      	<%if(LoginUtil.getErrorInfo(request)!=null){ %>
		      		<%=LoginUtil.getErrorInfo(request)%>
		      	<%}%>
		      </font>
			</div>
			<input type="hidden" name="loginMobile" value="1"/>
	    	<div><span  style="font-size:30px;color: #333333;">登录手机版</span></div>
	    	<div>
  				<input class="input_text"  type="text" id="j_username" name="j_username" required="true" placeholder="用户名" onkeypress="onkeypress_login('login_name',event)" />
	        </div>
			<div>
				<input class="input_text" type="password" id="j_password" name="j_password" required="true" placeholder="密码" onkeypress="onkeypress_login('password',event)" />
	        </div>
	        <div id="saveCookie_div">
				<div><input id="saveCookie" type="checkbox"/>记住用户名</div>
	        </div>
			<div id="div_btn_login" onclick="user_login();">
				<input class="btn_font" type="button" id="btnSubmit"  value="登录">
			</div>
			<div id="div_btn_gotoweb" onclick="gotoWeb();">
				<input class="btn_font" type="button" id="btnSubmit" value="转至网页版">
			</div>
	  </form>
	  <div style="clear:both; font-size:16px;color:#333333;">●手机版PD提供精简过的必要功能</div>
	  </div>
</body>
</html>
<script>
init();
//如果有cookie值则返回cookie值
function init(){
	var cookieV = getCookieValue("j_username");
	//如果cookie有值则自动选中checkbox
	if(cookieV!=""){
		document.getElementById("j_username").value = cookieV;
		document.getElementById("saveCookie").checked = "true";
	}
}

function checkData(){
	if(document.getElementById("j_username").value == ""){
		alert("用户名不能为空！！");
		document.getElementById("j_username").focus();
		return false;
	}else if(document.getElementById("j_password").value == ""){
		alert("密码不能为空！！");
		document.getElementById("j_password").focus();
		return false;
	}else{
		return true;
	}
	return true;	
	
}
function setCookie(name,value,hours,path){   
    var name = escape(name);   
    var value = escape(value);   
    var expires = new Date();   
    expires.setTime(expires.getTime() + hours*3600000000);
    path = path == "" ? "" : ";path=" + path;   
    _expires = (typeof hours) == "string" ? "" : ";expires=" + expires.toUTCString();   
    document.cookie = name + "=" + value + _expires + path;   
}   
function getCookieValue(name){   
    var name = escape(name);   
    var allcookies = document.cookie;          
    name += "=";   
    var pos = allcookies.indexOf(name);       
    if (pos != -1){                                     
        var start = pos + name.length;                  
        var end = allcookies.indexOf(";",start);          
        if (end == -1) end = allcookies.length;        
        var value = allcookies.substring(start,end);  
        return unescape(value);                           
        }      
    else return "";                                            
}   
function deleteCookie(name,path){   
    var name = escape(name);   
    var expires = new Date(0);   
    path = path == "" ? "" : ";path=" + path;   
    document.cookie = name + "="+ ";expires=" + expires.toUTCString() + path;   
}
function foucusInput(){
	
	if(document.getElementById("j_username").value == ''){
		//document.getElementById("j_username").style.cssText="border: #ff0000 solid 1px;";
		document.getElementById("j_password").style.cssText = "";
		document.getElementById("j_username").focus();
	}else{
		//document.getElementById("j_password").style.cssText="border: #ff0000 solid 1px;";
		document.getElementById("j_username").style.cssText="";
		document.getElementById("j_password").focus();
	}
}
function onkeypress_login(id,e){
	var keyCode;
	e = (e) ? e : ((window.event) ? window.event : "");
	keyCode = e.keyCode ? e.keyCode : (e.which ? e.which : e.charCode);
	if(keyCode==13){
			if(id=="login_name"){
				document.getElementById("j_password").focus();
			}else if(id=="password"){
				user_login();
			}else{
				user_login();
			}
			
	}
}

// 系统登录
function user_login(){
	if(checkData()){
		if(document.getElementById("saveCookie").checked){
			setCookie("j_username",document.getElementById("j_username").value,24,"/");  
			setCookie("j_password","",24,"/"); 
		}else{
			deleteCookie("j_username","/");
			deleteCookie("j_password","/");
			}
		document.getElementById("frm").submit();
	}
}

foucusInput();
</script>
