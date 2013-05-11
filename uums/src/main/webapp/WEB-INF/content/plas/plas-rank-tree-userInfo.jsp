<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div>
<fieldset>
<s:if test="page.result.size() > 0">
<div style="background-color:#DBDBDB">
	
				<s:iterator value="page.result" >
						<div style="cursor:pointer;">姓名：${userName}</div>
						<div>性别：${sexCd }</div>
						<div>手机：${mobilePhone }</div>
						<div>座机：${fixedPhone }</div>					
				</s:iterator>
</div>
</s:if>
<s:else>
 	暂无该用户信息
 	<a href="gotoHome()">返回</a>
</s:else>
</fieldset>
</div>
