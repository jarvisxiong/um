<%@page import="com.hhz.uums.utils.DictMapUtil"%>
<%@page import="com.hhz.uums.utils.CodeNameUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.hhz.uums.utils.Constants"%>
<%@ include file="/common/taglibs.jsp"%>

<div id="tip_result_success">
<div class="easyui-layout" fit="true" style="+position: relative;overflow:auto;background-color: white;padding-top: 10px;">
	<span class="span_h3" style="color: red;">
			<s:if test="applyTypeCd == 1">人员新增</s:if>
		<s:elseif test="applyTypeCd == 2">人员调动</s:elseif>
		<s:elseif test="applyTypeCd == 3">人员冻结</s:elseif>
		<s:elseif test="applyTypeCd == 4">人员解冻</s:elseif>
		<s:elseif test="applyTypeCd == 5">人员离职</s:elseif>
		<s:elseif test="applyTypeCd == 6">人员启用</s:elseif>提交成功!
	</span>
	<div>
		<span><input type="button" class="plbtn plblue" value="继续新增人员"  onclick="refreshApproveArea('');"/></span>
		<span><input type="button" class="plbtn plgreen" value="查看申请"    onclick="refreshApproveArea('${plasApproveInfoId}');"/></span>
	</div>
</div>
</div>