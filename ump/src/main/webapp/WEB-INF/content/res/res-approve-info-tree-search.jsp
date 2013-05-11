<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<style>
	.tree_module_body {
	    font-size:12px;
		padding: 5px;
		cursor:pointer;
		border-bottom: 1px solid #DDDBDC;
	}
	.tree_module_body:hover{
		color:#0167A2;
		background: white;
	}
	</style>
 <s:iterator value="searchModuleList">
	 	<div class="tree_module_body" id="${resAuthTypes[0].authTypeCd}" onclick="openAuthType('${resAuthTypes[0].authTypeCd}','${resAuthTypes[0].displayName}','${moduleName }');">
			<s:if test="resAuthTypes[0].publish == true">
			${remark }/${resAuthTypes[0].authTypeName }
			</s:if>
			<s:else>
			<span style="color: rgb(138, 138, 138);">${remark }/${resAuthTypes[0].authTypeName }</span>
			</s:else>
		</div>
</s:iterator>
