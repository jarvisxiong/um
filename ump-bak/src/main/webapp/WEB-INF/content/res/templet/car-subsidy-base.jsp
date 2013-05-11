<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--车辆补贴报销申请单-->
<%@ include file="template-header.jsp"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<div align="center" class="billContent">
	
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="80"/>
			<col/>
			<col width="90"/>
			<col/>
			<tr>
				<td class="td_title">中心</td>
				<td>
				<s:if test="resApproveInfoId==null">
				<s:set var="centerName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </s:set>
				<s:set var="centerCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></s:set>
				</s:if>
				<s:else>
				<s:set var="centerName">${templateBean.centerName}</s:set>
				<s:set var="centerCd">${templateBean.centerCd}</s:set>
				</s:else>
				<input validate="required" type="text" name="templateBean.centerName" value="${centerName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
				<input type="hidden" id="centerCd" name="templateBean.centerCd" value="${centerCd}"  />
				</td>
				
				<td class="td_title">职级</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.positionName" value="${templateBean.positionName}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">申请人姓名</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">申请额度</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.applyLimit" value="${templateBean.applyLimit}" onblur="formatVal($(this))"/>
				</td>
				<td class="td_title">开始报销日期</td>
				<td>
				<input onfocus="WdatePicker()" class="Wdate" type="text" validate="required" name="templateBean.startDate" value="${templateBean.startDate}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">申请理由</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.applyReason">${templateBean.applyReason}</textarea>
				</td>
			</tr>
		</table>
</div>
