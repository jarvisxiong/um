<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<SCRIPT LANGUAGE="JavaScript">
	var request = {
		QueryString : function(val) {
			var uri = window.location.search;
			var re = new RegExp("" + val + "\=([^\&\?]*)", "ig");
			return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1))
					: null);
		},
		QueryStrings : function() {
			var uri = window.location.search;
			var re = /\w*\=([^\&\?]*)/ig;
			var retval = [];
			while ((arr = re.exec(uri)) != null)
				retval.push(arr[0]);
			return retval;
		},
		setQuery : function(val1, val2) {
			var a = this.QueryStrings();
			var retval = "";
			var seted = false;
			var re = new RegExp("^" + val1 + "\=([^\&\?]*)$", "ig");
			for ( var i = 0; i < a.length; i++) {
				if (re.test(a[i])) {
					seted = true;
					a[i] = val1 + "=" + val2;
				}
			}
			retval = a.join("&");
			var rtnVal=  "?" + retval
					+ (seted ? "" : (retval ? "&" : "") + val1 + "=" + val2);
			
			return rtnVal;
		},
		toLang: function (val1, val2){
			var url2=this.setQuery(val1,val2);
			window.location.href=url2;
		}
	}
	//-->
</SCRIPT>
<div id="header">
<div id="title">
<h1><s:text name="powerlong.title" /></h1>
<div align="right">
	<a href="javascript:request.toLang('request_locale','zh_CN')">简体</a> 
 	<s:a href="javascript:request.toLang('request_locale','zh_TW')">繁體</s:a>
	<a href="javascript:request.toLang('request_locale','en')">English</a>

</div>
</div>
<div id="menu">
<ul>
	<li><a href="${ctx}/security/user.action">帐号列表</a></li>
	<li><a href="${ctx}/security/role.action">角色列表</a></li>
	<li><a href="${ctx}/j_spring_security_logout">退出登录</a></li>
</ul>
</div>
</div>