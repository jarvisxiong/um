<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<s:iterator value="secOndCats" var="sec" status="st">
	<s:if test="st.index==0">
		<option value="">--</option>
	</s:if>
	<s:else>
		<option value="${key }">${value}</option>
	</s:else>
</s:iterator>

