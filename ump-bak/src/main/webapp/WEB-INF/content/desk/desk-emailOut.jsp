<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>


<!-- 本页面不使用 -->

<% 


String[] fields = SpringSecurityUtils.getCoreMailFields();
String sid = fields[1];
String outUrl = "http://mail.powerlong.com/coremail/XJS/mbox/list_oa.jsp?"+ sid +"&fid=0";
String outUrlExt = fields[0]+"/coremail/XJS/index.jsp?"+ sid; 

//String outUrl = "http://mail1.powerlong.com/coremail/XJS/mbox/list_oa.jsp?"+ SpringSecurityUtils.getCoreMailSID()[0] +"&fid=0";  
 //String outUrlExt = "http://mail1.powerlong.com/coremail/XJS/index.jsp?"+ SpringSecurityUtils.getCoreMailSID()[0]; 
  
  %>

<%--
<% String outUrl = "http://mail1.powerlong.com/coremail/XJS/mbox/list_oa.jsp?sid=BABiTSZZjkCgpxbVuIZZsrxFmINXprIj&fid=0"; %>
<% String outUrlExt = "http://mail1.powerlong.com/coremail/XJS/index.jsp?sid=BAeiTSZZfkCgpxxVuIZZqaFRaanfeOIj"; %>
<% String outUrlExt = "http://mail1.powerlong.com/coremail/main.jsp?sid=BAeiTSZZfkCgpxxVuIZZqaFRaanfeOIj"; %>

 --%>

<!--  标题和分页 -->
<div class="outMailHead">
	<div style="display:none; height:27px; width:100%; background-color:#e4e7ec; line-height:27px;">
		<div align="left" style="float:left; width: 300px;padding-left:5px;">
			<span style="cursor: pointer;" onclick="openWinMailOut('<%= outUrlExt %>')" title="点击弹出新窗口">
				<span style="font-size:14px;font-weight: bolder; ">邮件信息</span>
				<span style="color:red;"><span class="numOfNoReadOut"></span></span>
			</span>
			| 
			<span onclick="showTabMailInner()" style="cursor:pointer;" title="查看全部未读邮件">
				<span>提醒信息</span>
				<span style="color:red;"><span class="numOfNoRead"></span></span>
			</span>
		</div>
	</div>
</div>

<!-- 邮件列表 -->
<div style="background-color: #FFFFFF;width:100%;height:240px;overflow-x: hidden;">
	<%--
	<iframe src="<%= outUrl %>" width="100%" height="200" scrolling="auto" frameborder="0" name="frameOutMailList" id="frameOutMailList">
	</iframe>
	 --%>
</div>