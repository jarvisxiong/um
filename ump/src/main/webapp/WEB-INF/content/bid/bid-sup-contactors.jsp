<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<div>
 <span style="padding-left: 2px;">【帐号信息】以下供应商存在一个或以上联系人，请选择联系人发送帐号及密码：</span>
 <br/>
 <br/>
 
  <table style="width: 100%;" >
  <s:iterator value="listBidSupVo" var="vo" status="st">
  
	  <tr>
	  	<td>
	  	    <div style="padding-left: 5px;padding-bottom:5px;">${bidSupName}:</div>
		  	<div style="padding-left: 5px;padding-bottom:10px;" class="supList">		
			  		 <s:iterator value="contactors" var="con" status="stCon">			  		 				  		 
			  			<input type="checkbox" contactId="${contactId}" <s:if test="#stCon.index==0"> checked='true' </s:if>></input>${name}			  			
			  		</s:iterator>		  		
		  	</div>
	  	</td>
	  	
	  </tr>  
	  
  </s:iterator>
  </table>
  <div style="vertical-align: bottom;padding-left: 150px;">
	  <input onclick="sendMessage('${bidLedgerId}');" value="确定" id="btn_save" class="btn_green"></input>
	  <input onclick="cancelConfirm('${bidLedgerId}');" value="取消" id="btn_save" class="btn_green"></input>
  </div>
</div>