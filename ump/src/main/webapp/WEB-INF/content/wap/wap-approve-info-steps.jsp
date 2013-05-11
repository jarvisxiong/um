<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="toolBar">
	<input type="button" value="返回" class="btn_green" onclick="openDetail();" />
</div>
<div class="list_header" style="margin-top:10px;"><span style="font-size:24px;">所有流程</span></div>

<div class="divSteps">
<ol style="padding-left:25px;">
<s:set var="cSize"><s:property value="resAuthType.resConditonTypes.size" /></s:set>
<s:iterator value="resAuthType.resConditonTypes" status="s" var="c">
	<s:if test="#cSize>1">
	<li><span style="font-size: 16px;font-weight: bold;">${c.conditionName}</span></li><br/>
	</s:if>
	<s:set var="isHq">false</s:set>
	<s:set var="isHad">false</s:set>
	<span style="font-size: 16px;">
	<s:iterator value="resApproveSteps" status="s1" var="step">
		<s:if test="#step.stepOptionCd !=2">
			<s:if test="#step.groupNodeCd !=null">
			<s:if test="#isHad=='true'&&#isHq=='false'">
			-->
			</s:if>
			<s:set var="isHq">true</s:set>
			[</s:if>
			<s:else>
			<s:if test="#isHad=='true'">
			-->
			</s:if>
			<s:if test="#isHq=='true'">
			<s:set var="isHq">false</s:set>
			</s:if>
			</s:else>
			
			<p:code2name mapCodeName="mapNode" value="#step.nodeCd"/>
			<s:if test="#step.groupNodeCd !=null">]</s:if>
			<s:set var="isHad">true</s:set>
		</s:if>
	</s:iterator>
	</span>
	<br/>
	<br/>
</s:iterator>
</ol>
</div>
<div><span style="color: red;font-size: 16px;">注：[]内属于会签节点</span></div>

