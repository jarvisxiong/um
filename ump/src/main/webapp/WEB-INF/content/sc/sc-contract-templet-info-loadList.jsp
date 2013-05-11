<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<input type="hidden" id="scTempletId" value="${scContractParams.scContractTemletId}" />
<input type="hidden" id="scNonConTempletId" value="${scContractParams.scNonContractTempletId}"/>
<input type="hidden" id="isVaild" value="${scContractParams.isValid}" />
<input type="hidden" id="scIsStandard" value="${scContractParams.isStandard}"/>
<script>
function getCont(id){
	_isCurDel=true;	

	window.setTimeout("	_isCurDel=false;", 1000);
		
	var url="${ctx}/cont/cont-ledger!input.action?id="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cont-ledger-input","合同台账查看",url,true);
}
</script>
<%--是否有删除权限 --%>
<s:set var="isCanDel" value="0"/>
<security:authorize ifAnyGranted="A_CONT_STAN_DEL">
	<s:set var="isCanDel" value="1"/>
</security:authorize>
<table width="100%" id="result_table" class="content_table" style="margin:0px;">
	<thead>
		<tr class="header">
			<th width="80px" style="background:none;">合同编号<input type="hidden" id="isCanDel" value="${isCanDel}"/></th>
			<th>合同名称</th>
	       	<th width="150px">合同类别</th>
			<th width="35px">类型</th>
			<th width="80px" align="right">总价</th>
			<th width="50px">创建人</th>
			<th width="40px">更新</th>
			<th width="40px">状态</th>
			<!-- 删除状态不显示 -->
			<s:if test="scContractParams.isDel!=1">
			<th width="40px">操作</th>
			</s:if>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="voContractTempletInfoList" status="stat">
			<tr class="mainTr" onclick="btnLoadConInfo('${contractTempletInfoId}','${statusCd}','${isstandard}');" style="cursor: pointer;">

				<td style="text-align: left" class="clswrap" title="${contractNo}">${contractNo}</td>
				<%-- 合同名称 --%>
				<td class="clswrap" title="${contractName}">${contractName}</td>
				<%-- 合同类别--%>
				<td class="clswrap" title="${contractTempletName}">${contractTempletName}</td>
				<%-- 合同类型--%>
				<td class="clswrap" >
					<s:if test="isstandard== 1">
						标准
					</s:if>
					<s:else>
						非标
					</s:else>
				</td>
				<td align="right">${contractPrice}</td>
				<td>${creatorName}</td>
				<td><s:date name="updatedDate" format="MM-dd" /></td>
				<td style="text-align: center">
					<s:if test="scContractParams.isDel==1">
						<font class="abled">已删除</font>
					</s:if>
					<s:else>
						<s:if test="statusCd==10||statusCd==20||statusCd==30||statusCd==40||statusCd==45">
							<font class="abled">进行中</font>					
						</s:if>
						<s:if test="statusCd==50">
							<font class="abled">网批中</font>
						</s:if>
						<s:elseif test="statusCd == 60">
						 <font class="abled">网批结束</font>
						</s:elseif>
						<s:elseif test="statusCd==70">
							<font class="abled">可打印</font>
						</s:elseif>
						<s:elseif test="statusCd==80">
							<font class="abled">已签署</font>
						</s:elseif>
					</s:else>
				</td>
				<!-- 删除状态不显示 -->
				<s:if test="scContractParams.isDel!=1">
				<td style="text-align: center">
					<s:if test="statusCd == null || statusCd==10 ||#isCanDel==1">
						<input type="button" class="btn_del_new" style="width: 50px;height:20px;" value="删除" onclick="deleteTempletInfo('${contractTempletInfoId}','${creator}')"/>
					</s:if>	
					<s:if test="statusCd ==10 || statusCd==20 || statusCd ==30 || statusCd==40 || statusCd ==45">
					<input optype="extract" type="button" class="btn_green_new" style="display:none;width: 50px;height:20px;"  value="提取" 
					onclick="doExtractConInfo('${contractTempletInfoId}','${contractTempletHisId}','${contractNo}','${contractName}','${isstandard}','${projectName}','${projectCd}')" />
					</s:if>
				</td>
				</s:if>
			</tr>
		</s:iterator>

	</tbody>
</table>
<div class="pagerRight" id="sorucePager"><p:page pageInfo="voPage" /></div>
