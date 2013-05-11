<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--加班申请表-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
		<table  class="mainTable">
			<col width="90"/>
			<col/>
			<tr>
				<td class="td_title">中心/区域</td>
				<td>
					<s:if test="resApproveInfoId==null">
						<c:set var="centerName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </c:set>
						<c:set var="centerCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></c:set>
						<c:set var="defaultOrgName"><%=SpringSecurityUtils.getCurrentDeptName() %></c:set>
						<c:set var="defaultPositionName"><%=SpringSecurityUtils.getRealPositonName() %></c:set>
						<c:set var="defaultUserName"><%=SpringSecurityUtils.getCurrentUserName() %></c:set>
						<c:set var="defaultUserCd"><%=SpringSecurityUtils.getCurrentUiid() %></c:set>
					</s:if>
					<s:else>
						<c:set var="centerName">${templateBean.centerName}</c:set>
						<c:set var="centerCd">${templateBean.centerCd}</c:set>
						<c:set var="defaultOrgName">${templateBean.deptName}</c:set>
					</s:else>
					<input validate="required" type="text" name="templateBean.centerName" value="${centerName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="centerCd" name="templateBean.centerCd" value="${centerCd}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">部门</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.deptName" value="${defaultOrgName}"  />
				</td>
			</tr>
		</table>
		<s:if test="statusCd==0 || statusCd==3">
		<table  class="mainTable" id="trConItem" style="display:none;margin-top: 10px;">
			<col width="90"/>
			<col width="90"/>
			<col width="60"/>
			<col width="80"/>
			<col width="60"/>
			<col width="80"/>
			<col width="40"/>
			<col/>
			
			<tr>
				<td class="td_title" style="border-top:0 none;">姓名</td>
				<td  style="border-top: none;">
					<input id="applyUserName0" validate="required" type="text" readonly="readonly" name="templateBean.otherProperties[0].applyUserName" class="inputBorderNoTip userName" style="cursor: pointer;" value="${defaultUserName}" />
					<input id="applyUserCd0" type="hidden" class="userCd" name="templateBean.otherProperties[0].applyUserCd" value="${defaultUserCd}" />
				</td>
				<td class="td_title" style="border-top:0 none;">职位</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.otherProperties[0].positionName" value="${defaultPositionName}" />
				</td>
				<td class="td_title" style="border-top: none;">职级</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.otherProperties[0].positionLevel"/>
				</td>
				<td colspan="2" style="border-top: none;" align="right">
						<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
				</td>
			</tr>
			<tr>
				<td class="td_title">加班日期</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.otherProperties[0].overtimeDate"/>
				</td>
				<td class="td_title">从（时）</td>
				<td>
					<input onfocus="WdatePicker({dateFmt:'HH:mm'})" class="Wdate inputBorder" type="text" validate="required" name="templateBean.otherProperties[0].fromTime"/>
				</td>
				<td class="td_title">到（时）</td>
				<td>
					<input onfocus="WdatePicker({dateFmt:'HH:mm'})" class="Wdate inputBorder" type="text" validate="required" name="templateBean.otherProperties[0].toTime"/>
				</td>
				<td class="td_title">时数</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.otherProperties[0].totalHour" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">加班类型</td>
				<td colspan="7" class="chkGroup" align="left"  validate="required">
				<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
					<td><s:checkbox name="templateBean.otherProperties[0].sendType1"  cssClass="group"></s:checkbox>休息日加班</td>
					<td><s:checkbox name="templateBean.otherProperties[0].sendType2"  cssClass="group"></s:checkbox>法定假日加班</td>
					</tr>
				</table>
				
				</td>
			</tr>
			<tr>
				<td class="td_title">补贴方式</td>
				<td colspan="7" class="chkGroup" align="left"  validate="required">
				<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
					<td><s:checkbox name="templateBean.otherProperties[0].subsidy1"  cssClass="group"></s:checkbox>安排调休</td>
					<td><s:checkbox name="templateBean.otherProperties[0].subsidy2"  cssClass="group"></s:checkbox>支付工资</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">加班原因</td>
				<td colspan="7">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.otherProperties[0].reason"></textarea>
				</td>
			</tr>
		</table>
		</s:if>
		<s:iterator value="templateBean.otherProperties" var="item" status="s">
		<table  class="mainTable" style="margin-top: 10px;">
			<col width="90"/>
			<col/>
			<col width="60"/>
			<col/>
			<col width="60"/>
			<col/>
			<col width="40"/>
			<col/>
			<tr>
				<td class="td_title">姓名</td>
				<td >
				<input id="applyUserName<s:property value="#s.index" />" validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].applyUserName" value="${item.applyUserName}" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip userName singleSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip userName" </s:else>/>
				<input id="applyUserCd<s:property value="#s.index" />" type="hidden" class="userCd" name="templateBean.otherProperties[<s:property value="#s.index" />].applyUserCd" value="${item.applyUserCd}"  />
				
				</td>
				<td class="td_title">职位</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].positionName" value="${item.positionName}"  />
				</td>
				<td class="td_title">职级</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].positionLevel" value="${item.positionLevel}"  />
				</td>
				<td colspan="2">
				<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
					<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
				</s:if>
				</td>
			</tr>
			<tr>
				<td class="td_title">加班日期</td>
				<td >
				<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].overtimeDate" value="${item.overtimeDate}"/>
				</td>
				<td class="td_title">从(时)</td>
				<td>
				<input onfocus="WdatePicker({dateFmt:'HH:mm'})" class="Wdate inputBorder" type="text" validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].fromTime" value="${item.fromTime}"/>
				</td>
				<td class="td_title">到(时)</td>
				<td>
				<input onfocus="WdatePicker({dateFmt:'HH:mm'})" class="Wdate inputBorder" type="text" validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].toTime" value="${item.toTime}"/>
				</td>
				<td class="td_title">时数</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].totalHour" value="${item.totalHour}"  onblur="formatVal($(this));"/>
				</td>
			</tr>
			
			<tr>
				<td class="td_title">加班类型</td>
				<td colspan="7" class="chkGroup" align="left"  validate="required">
				<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
					<td><s:checkbox name="templateBean.otherProperties[%{#s.index}].sendType1"  cssClass="group"></s:checkbox>休息日加班</td>
					<td><s:checkbox name="templateBean.otherProperties[%{#s.index}].sendType2"  cssClass="group"></s:checkbox>法定假日加班</td>
					</tr>
				</table>
				
				</td>
			</tr>
			<tr>
				<td class="td_title">补贴方式</td>
				<td colspan="7" class="chkGroup" align="left"  validate="required">
				<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
					<td><s:checkbox name="templateBean.otherProperties[%{#s.index}].subsidy1"  cssClass="group"></s:checkbox>安排调休</td>
					<td><s:checkbox name="templateBean.otherProperties[%{#s.index}].subsidy2"  cssClass="group"></s:checkbox>支付工资</td>
					</tr>
				</table>
				</td>
			</tr>
			
			<tr>
				<td class="td_title">加班原因</td>
				<td colspan="7">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].reason">${item.reason}</textarea>
				</td>
			</tr>
		</table>
		</s:iterator>
		<div id="userTable">
		</div>
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
		$(trApprover_new).find(":input").each(function(i){
			this.name=this.name.replace('0',index);
		});
		var tmpIdx=index;
		
		var userNameDom=$(trApprover_new).find(".userName");
		userNameDom.attr("id","applyUserName"+index);
		userNameDom.userSelect({
			muti:false
		});
		$(trApprover_new).find(".userCd").attr("id","applyUserCd"+index);
		$("#userTable").append(trApprover_new);
		index++;
		addClickAction(trApprover_new);
	}
	function delItem(dom){
		$(dom).parents("#trConItem").remove();
	}
</script>

<!-- 默认1条申请记录 -->
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>

</s:if>