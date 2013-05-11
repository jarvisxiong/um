<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--年假延期申请单 --%>

<div align="center" class="billContent">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="110px"/>
			<col/>
			<col width="80px"/>
			<col/>
			<col width="80px"/>
			<col/>
			<tr>
				<td class="td_title">中心/部门</td>
				<td colspan="5">
					<s:if test="resApproveInfoId==null">
					<s:set var="sendCenterName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </s:set>
					<s:set var="sendCenterCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></s:set>
					</s:if>
					<s:else>
					<s:set var="sendCenterName">${templateBean.centerOrgName}</s:set>
					<s:set var="sendCenterCd">${templateBean.centerOrgCd}</s:set>
					</s:else>
					<input validate="required" type="text" name="templateBean.centerOrgName" value="${sendCenterName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="centerCd" name="templateBean.centerOrgCd" value="${sendCenterCd}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">申请人</td>
				<td>
					<input id="applyUserName" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else> />
					<input id="applyUserCd" type="hidden" name="templateBean.userCd" value="${templateBean.userCd}"  />
				</td>
				<td class="td_title">职位</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.position" value="${templateBean.position}"  />
				</td>
				<td class="td_title">入职日期</td>
				<td>
					<input class="inputBorder Wdate" onfocus="WdatePicker()" validate="required" type="text" name="templateBean.entryDate" value="${templateBean.entryDate}"  />
				</td>
				
			</tr>
			<tr>
				<td class="td_title">事由</td>
				<td colspan="5">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.reason">${templateBean.reason}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">剩余年假<br/><span style="font-size: 10px;">(如:2011年上半年4天)</span></td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.remaindDays" value="${templateBean.remaindDays}"/>
				</td>
				<td class="td_title">延期时间</td>
				<td colspan="3">
				<table class="tb_checkbox chkGroup"  validate="required">
					<col width="100">
					<col width="100">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.delayMonth1"  cssClass="group"></s:checkbox>一个月 </td>
					<td><s:checkbox name="templateBean.delayMonth2"  cssClass="group"></s:checkbox>两个月</td>
					<td><s:checkbox name="templateBean.delayMonth3"  cssClass="group"></s:checkbox>三个月</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.delayMonth4"  cssClass="group"></s:checkbox>四个月</td>
					<td><s:checkbox name="templateBean.delayMonth5"  cssClass="group"></s:checkbox>五个月</td>
					<td><s:checkbox name="templateBean.delayMonth6"  cssClass="group"></s:checkbox>六个月</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
</div>
