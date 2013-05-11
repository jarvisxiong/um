<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.LoginUtil"%>
<%@page import="com.hhz.core.utils.DateOperator"%>

<style>
.dateInput {
	width: 120px !important;
	margin-left:5px;
	margin-right:5px;
}
</style>

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
	<c:set var="defaultUserName"><%=SpringSecurityUtils.getCurrentUserName() %></c:set>
	<c:set var="defaultUserCd"><%=SpringSecurityUtils.getCurrentUiid() %></c:set>
	<c:set var="defaultPositionName"><%=SpringSecurityUtils.getRealPositonName() %></c:set>
	<c:set var="defaultAttendWorkDate"><%=DateOperator.formatDate(SpringSecurityUtils.getAttendWorkDate()) %></c:set>
</s:if>
<s:else>
	<c:set var="sendCenterName">${templateBean.centerName}</c:set>
	<c:set var="sendCenterCd">${templateBean.centerCd}</c:set>
	<c:set var="defaultUserName">${templateBean.userName}</c:set>
	<c:set var="defaultUserCd">${templateBean.userCd}</c:set>
	<c:set var="defaultPositionName">${templateBean.position}</c:set>
	<c:set var="defaultAttendWorkDate">${templateBean.enterDate}</c:set>
</s:else>
<!--  续签合同申请表  -->
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
			<input validate="required" type="text" name="templateBean.centerName" value="${sendCenterName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
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
		<td class="td_title">
			最近一次合同类型
		</td>
		<td colspan="3" class="chkGroup" align="left" validate="required">
			<table id="table_contractType" class="tb_checkbox" style="width:100%;">
				<col width="140px;">
				<col>
				<tbody>
					<tr>
						<td valign="top"><s:checkbox name="templateBean.conFixedExpires" cssClass="group"></s:checkbox>固定期限：</td>
						<td>
							从<input class="inputBorder dateInput Wdate" validate="required" type="text" name="templateBean.conDateFrom" value="${templateBean.conDateFrom}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'});" />
							至<input class="inputBorder dateInput Wdate" validate="required" type="text" name="templateBean.conDateTo" value="${templateBean.conDateTo}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'});" />
						</td>
					</tr>
					<tr>
						<td><s:checkbox name="templateBean.conNonFixedExpires" cssClass="group"></s:checkbox>无固定期限：</td>
						<td>
							从<input class="inputBorder dateInput Wdate" validate="required" type="text" name="templateBean.conNonDateFrom" value="${templateBean.conNonDateFrom}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'});" />起
						</td>
					</tr>
					<tr>
						<td><s:checkbox name="templateBean.conCompleteTask" cssClass="group"></s:checkbox>以完成一定工作任务：</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.conCompleteTaskInput" value="${templateBean.conCompleteTaskInput}" />
						</td>
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
	<tr>
		<td class="td_title">申请次数</td>
		<td colspan="3" align="left">
			本次申请为第<input type="text" validate="required" class="inputBorder" name="templateBean.applyRenewTime" value="${templateBean.applyRenewTime}" style="width: 60px;margin-left:5px; margin-right:5px;"/>次申请续签劳动合同
		</td>
	</tr>
	<tr>
		<td class="td_title">
			续签申请：
		</td>
		<td colspan="3" class="chkGroup" align="left" validate="required">
			<table id="table_applyType" class="tb_checkbox" style="width:100%;">
				<col width="140px;">
				<col>
				<tbody>
					<tr>
						<td colspan="2"><s:checkbox name="templateBean.reDisAgreeRenew" cssClass="group"></s:checkbox>不同意续签</td>
					</tr>
					<tr>
						<td valign="top"><s:checkbox name="templateBean.reApplyFixedExpires" cssClass="group"></s:checkbox>申请固定期限：</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.reMonth" value="${templateBean.reMonth}" style="margin-right:5px;width:45px;"/>个月，
							从<input class="inputBorder dateInput Wdate" validate="required" type="text" name="templateBean.reDateFrom" value="${templateBean.reDateFrom}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'});" />
							至<input class="inputBorder dateInput Wdate" validate="required" type="text" name="templateBean.reDateTo" value="${templateBean.reDateTo}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'});" />
						</td>
					</tr>
					<tr>
						<td><s:checkbox name="templateBean.reCompleteTask" cssClass="group"></s:checkbox>以完成一定工作任务：</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.reCompleteTaskInput" value="${templateBean.reCompleteTaskInput}" />
						</td>
					</tr>
					<tr>
						<td colspan="2"><s:checkbox name="templateBean.reNonFixedExpires" cssClass="group"></s:checkbox>无固定期限</td>
					</tr>
				</tbody>
			</table>
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

<script type="text/javascript">

function checkboxClick(cbx) {
	$(cbx).bind("click", function() {
		// 初始化各选项设置
		$(cbx).parent().next().find("input[type='text']")
			.attr({"disabled": "disabled", "validate": "", "readonly": "readonly", "title": ""})
			.css({"border": "1px solid #999999", "cursor": "default"});
		
		if($(this).attr("checked")) { // 当checkbox被选中的时候，修改该选项对应的控件设置
			$(this).parent().next().find("input[type='text']")
				.attr({"disabled": "", "validate": "required", "readonly": "", "title": "请输入！"})
				.css({"border-left": "2px solid red", "cursor": "pointer"});
		} else { // 当checkbox取消选中的时候，修改该选项对应的控件设置
			$(this).parent().next().find("input[type='text']")
				.attr({"disabled": "disabled", "validate": "", "readonly": "readonly", "title": ""})
				.css({"border": "1px solid #999999", "cursor": "default"});
		}
	});
}


var cbx = $("#table_contractType input[type='checkbox']");
$(cbx).parent().next().find("input[type='text']").attr({"disabled": "disabled", "validate": "", "readonly": "readonly"});
checkboxClick(cbx);

var cbx1 = $("#table_applyType input[type='checkbox']");
$(cbx1).parent().next().find("input[type='text']").attr({"disabled": "disabled", "validate": "", "readonly": "readonly"});
checkboxClick(cbx1);

</script>
