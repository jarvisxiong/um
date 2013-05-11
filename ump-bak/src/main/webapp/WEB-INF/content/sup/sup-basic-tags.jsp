<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<div style="margin-left:2px;">
	<s:if test="listTag1 != null">
	<ul id="tag1List" style="list-style-type: none;margin: 2px 0 0 0;padding: 0;clear: both;">
		<li style="margin-left:5px;float:left;margin-right: 10px;">地区:</li>
		<s:iterator value="listTag1">
		<li style="margin-left:5px;float:left;">
			<input type="checkbox" tagcd='${tagCd}' tagName="${tagName}" style="margin-right:3px;"/>${tagName}
		</li>
		</s:iterator>
	</ul>
	</s:if>
	
	<s:if test="listTag2 != null">
	<ul id="tag2List" style="list-style-type: none;margin: 2px 0 0 0;padding: 0;clear: both;">
		<li style="margin-left:5px;float:left;margin-right: 10px;">品牌:</li>
		<s:iterator value="listTag2">
		<li style="margin-left:5px;float:left;">
			<input type="checkbox" tagcd='${tagCd}' tagName="${tagName}" style="margin-right:3px;"/>${tagName}
		</li>
		</s:iterator>
	</ul>
	</s:if>
</div>