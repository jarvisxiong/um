<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--未刷卡原因确认单-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<s:set var="conItemCount"><s:property value="templateBean.slotCardProperties.size()"/></s:set>
		<table id="tbConItem" class="mainTable" style="margin-top: 5px;">
			<col width="100">
			<col>
			<col width="60">
			<col>
			<col>
			<col>
			<col width="40">
			<tr>
				<td class="td_title">中心</td>
				<td colspan="3">
					<input validate="required" type="text" name="templateBean.centerOrgName" value="${templateBean.centerOrgName}" id="centerOrgName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="centerOrgCd"  name="templateBean.centerOrgCd" value="${templateBean.centerOrgCd}"  />
				</td>
				<td class="td_title">部门</td>
				<td colspan="2">
					<input validate="required" class="inputBorder" type="text" name="templateBean.deptOrgName" value="${templateBean.deptOrgName}"/>
					<input type="hidden" id="deptOrgCd"  name="templateBean.deptOrgCd" value="${templateBean.deptOrgCd}"  />
				</td>
			</tr> 
			<tr>
				<td class="td_title">姓名</td>
				<td>
					<input id="slotUserName" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else>/>
					<input id="slotUserCd" type="hidden" name="templateBean.userCd" value="${templateBean.userCd}"  />
				</td>
				<td class="td_title">职位</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.positionName" value="${templateBean.positionName}"/>
					<input type="hidden" id="positionCd"  name="templateBean.positionCd" value="${templateBean.positionCd}"  />
				</td> 
				<td class="td_title">
					刷卡月份
				</td>
				<td colspan="2">
					<input validate="required" class="inputBorder Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" type="text" name="templateBean.yourMonthDate" value="${templateBean.yourMonthDate}"/>
				</td>
			</tr>  
			<tr style="font-weight: bolder;">
				<td style="text-align: center;" class="td_title">日期</td>
				<td style="text-align: center;" colspan="3">班次</td>
				<td style="text-align: center;" colspan="2">原因</td>
				<td style="text-align: center;">操作</td>
			</tr>
			<!-- 模板开始 -->
			<s:if test="statusCd==0 || statusCd==3">
			<tr id="trConItem" style="display:none; margin-bottom:5px;">
				<td>
					<input validate="required" class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.slotCardProperties[0].slotDate" value="${item.slotDate}"/>
				</td>
				<td colspan="3" align="left" validate="required" >
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<tr>
						<td><s:checkbox name="templateBean.slotCardProperties[0].selectMorning1" cssClass="group"></s:checkbox>上午上班</td>
						<td><s:checkbox name="templateBean.slotCardProperties[0].selectMorning2" cssClass="group"></s:checkbox>上午下班</td>
					</tr>
					<tr>
						<td><s:checkbox name="templateBean.slotCardProperties[0].selectAfternoon1" cssClass="group"></s:checkbox>下午上班</td>
						<td><s:checkbox name="templateBean.slotCardProperties[0].selectAfternoon2" cssClass="group"></s:checkbox>下午下班</td>
					</tr>
					</table>
				</td>
				<td colspan="2">
					<textarea class="inputBorder contentTextArea" name="templateBean.slotCardProperties[0].noSlotReasonDesc" style="width:100%;height:50px;">${item.noSlotReasonDesc}</textarea>
				</td>
				<td width="15px" align="center"><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" title="删除记录" border="0"/></a></td>
			</tr> 
			</s:if>
			<!-- 模板结束 -->
					
			<s:iterator value="templateBean.slotCardProperties" var="item" status="s">
			<tr>
				<td>
					<input validate="required" class="inputBorder Wdate" onfocus="WdatePicker({dateFmt:'MM-dd'})" type="text" name="templateBean.slotCardProperties[<s:property value="#s.index" />].slotDate" value="${item.slotDate}"/>
				</td>
				<td colspan="3" validate="required" align="left" >
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<tr>
						<td><s:checkbox name="templateBean.slotCardProperties[%{#s.index}].selectMorning1" cssClass="group"></s:checkbox>上午上班</td>
						<td><s:checkbox name="templateBean.slotCardProperties[%{#s.index}].selectMorning2" cssClass="group"></s:checkbox>上午下班</td>
					</tr>
					<tr>
						<td><s:checkbox name="templateBean.slotCardProperties[%{#s.index}].selectAfternoon1" cssClass="group"></s:checkbox>下午上班</td>
						<td><s:checkbox name="templateBean.slotCardProperties[%{#s.index}].selectAfternoon2" cssClass="group"></s:checkbox>下午下班</td>
					</tr>
					</table>
				</td>
				<td colspan="2">
					<textarea class="inputBorder contentTextArea" name="templateBean.slotCardProperties[<s:property value="#s.index" />].noSlotReasonDesc" style="width:100%;height:50px;">${item.noSlotReasonDesc}</textarea>
				</td>
				<td width="15px" align="center">
				<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
				<a href="javascript:void(0)" onclick="delItem(this)">
				<img src="${ctx}/resources/images/common/del_22_22.gif" title="删除记录" border="0"/></a>
				</s:if>
				</td>
			</tr> 
			</s:iterator>
		</table>
		
		<s:if test="statusCd==0||statusCd==3">
			<input type="button"  class="btn_blue_75_55" value="增加条款" onclick="addItem();" />
		</s:if>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>

<script type="text/javascript">
	var trApprover=$("#trConItem").clone();
	$("#trConItem").remove();
	var cloneCount = 0;
	var index=${conItemCount};
	function addItem(){
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();
		$(trApprover_new).find(".inputBorder,:checkbox").each(function(i){
			this.name=this.name.replace('0',index);
		});
		//$(trApprover_new).find("#cloneUserCds").attr("id",cloneCount+"cloneUserCds");
		$("#tbConItem").append(trApprover_new);
		index++;
		addClickAction(trApprover_new);
	}
	function delItem(dom){
		$(dom).parent().parent().remove();
	}
</script>

<%--默认1行 --%>
<s:if test="resApproveInfoId==null">
	
	<script type="text/javascript">
		addItem();
		$('#centerOrgName').val('<%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %>');
		$('#centerOrgCd').val('<%=SpringSecurityUtils.getCurrentCenterCd() %>');
	</script>
</s:if>