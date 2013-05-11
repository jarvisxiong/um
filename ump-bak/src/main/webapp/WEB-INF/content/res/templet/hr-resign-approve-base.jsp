<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.LoginUtil"%>
<%@page import="com.hhz.core.utils.DateOperator"%>

<%@ include file="template-var.jsp"%>
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
	</s:set>
<s:if test="resApproveInfoId==null">
	<c:set var="sendCenterName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %></c:set>
	<c:set var="sendCenterCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></c:set>
	<c:set var="defaultPositionName"><%=SpringSecurityUtils.getRealPositonName() %></c:set>
	<c:set var="defaultUserName"><%=SpringSecurityUtils.getCurrentUserName() %></c:set>
	<c:set var="defaultUserCd"><%=SpringSecurityUtils.getCurrentUiid() %></c:set>
	<c:set var="defaultAttendWorkDate"><%=DateOperator.formatDate(SpringSecurityUtils.getAttendWorkDate()) %></c:set>
</s:if>
<s:else>
	<c:set var="sendCenterName">${templateBean.center}</c:set>
	<c:set var="sendCenterCd">${templateBean.centerCd}</c:set>
	<c:set var="defaultPositionName">${templateBean.position}</c:set>
	<c:set var="defaultUserName">${templateBean.userName}</c:set>
	<c:set var="defaultUserCd">${templateBean.userCd}</c:set>
	<c:set var="defaultAttendWorkDate">${templateBean.enterDate}</c:set>
</s:else>
<!-- 辞职审批表 -->
<table class="mainTable" style="margin-bottom: 5px;">
	<col width="120px"/>
	<col />
	<col width="120px"/>
	<col />
	<tr>
		<td class="td_title">姓名</td>
		<td>
			<input validate="required" type="text" name="templateBean.userName" value="${defaultUserName}" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else> />
			<input id="userCd" type="hidden" name="templateBean.userCd" value="${defaultUserCd}"  />
		</td>
		<td class="td_title">中心/部门</td>
		<td>
			<input validate="required" type="text" name="templateBean.center" value="${sendCenterName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
			<input type="hidden"  name="templateBean.centerCd" value="${sendCenterCd}" id="centerCd"/>
		</td>
	</tr>
	<tr>
		<td class="td_title">职位</td>
		<td>
			<input class="inputBorder" validate="required" type="text" name="templateBean.position" value="${defaultPositionName}" />
		</td>
		<td class="td_title">入职日期</td>
		<td>
			<input class="inputBorder Wdate" validate="required" type="text" name="templateBean.enterDate" value="${defaultAttendWorkDate}" onfocus="WdatePicker()"/>
		</td>
	</tr>
	<tr>
		<td class="td_title">合同期限</td>
		<td>
			<input class="inputBorder" validate="required" type="text" name="templateBean.contractExpires" value="${templateBean.contractExpires}"  />
		</td>
		<td class="td_title">最后工作日</td>
		<td>
			<input class="inputBorder Wdate" validate="required" type="text" name="templateBean.lastWorkDay" value="${templateBean.lastWorkDay}" onfocus="WdatePicker()"/>
		</td>
	</tr>
	<tr>
		<td class="td_title">辞职原因</td>
		<td colspan="3">
			<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.resignReason">${templateBean.resignReason}</textarea>
		</td>	
	</tr>
	<tr>
		<td class="td_title">附件</td>
		<td colspan="3" align="left">
			<c:set var="bizEntityId" value="${resApproveInfoId==null?entityTmpId:resApproveInfoId}"/>
			<r:fileUpload canEdit="${canEdit}" fileId="attachStr" fileValue="${templateBean.attachStr}"  bizEntityId="${bizEntityId}" title="" isRequired="true" />
		</td>
	</tr>
</table>
