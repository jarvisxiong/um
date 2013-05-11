<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%><script>
function onchange_DesignTotalDay() {
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	if(isEmpty(fromDate) || isEmpty(toDate)) {
		return;
	}
	var fArray = fromDate.split("-");
	var tArray = toDate.split("-");
	var fDate = new Date(fArray[0],fArray[1]-1,fArray[2]);
	var tDate = new Date(tArray[0],tArray[1]-1,tArray[2]);
	if(tDate.getTime()<fDate.getTime()) {
		alert("归还时间不能小于借阅时间");
		return;
	}
}
</script>
<%--档案资料使用审批表--%>
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
<div class="billContent" align="center">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
	<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
		<col width="120px">
		<col/>
		<col width="100px">
		<col/>
			<s:if test="resApproveInfoId==null">
					<s:set var="defaultOrgName"><%=SpringSecurityUtils.getCurrentDeptName() %></s:set>
					<s:set var="defaultUserName"><%=SpringSecurityUtils.getCurrentUserName() %></s:set>
					<s:set var="defaultUserCd"><%=SpringSecurityUtils.getCurrentUiid() %></s:set>
				</s:if>
				<s:else>
					<s:set var="defaultOrgName">${templateBean.applyUserDept}</s:set>
					<s:set var="defaultUserName">${templateBean.applyUser}</s:set>
					<s:set var="defaultUserCd">${templateBean.applyUserCd}</s:set>
				</s:else>
		<tr>
			<td class="td_title">申请人</td>
			<td>
			 <input validate="required" class="inputBorder" type="text" name="templateBean.applyUser" value="${defaultUserName}" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else>/>
			 <input id="applyUserCd" type="hidden" name="templateBean.applyUserCd" value="${defaultUserCd}"  />
			</td>
			<td class="td_title">部门</td>
			<td>
			 <input validate="required" class="inputBorder" type="text" name="templateBean.applyUserDept" value="${defaultOrgName}"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">借阅性质</td>
			<td colspan="3" class="chkGroup" align="left"  validate="required">
					<table class="tb_checkbox">
					<col width="50">
					<col width="50">
					<col width="50">
					<col width="50">
					<col width="50">
					<col>
					<tr>
					<td><s:checkbox name="templateBean.borrowerProperties1" id="borrowerProperties1" cssClass="group"></s:checkbox>查阅</td>
					<td><s:checkbox name="templateBean.borrowerProperties2" id="borrowerProperties2" cssClass="group"></s:checkbox>复印</td>
					<td><s:checkbox name="templateBean.borrowerProperties3" id="borrowerProperties3" cssClass="group"></s:checkbox>借用</td>
					<td><s:checkbox name="templateBean.borrowerProperties4" id="borrowerProperties4" cssClass="group"></s:checkbox>领用</td>
					<td><s:checkbox name="templateBean.borrowerProperties5" id="borrowerProperties5" cssClass="group"></s:checkbox>其它
					</td>
					<td><input class="inputBorder" type="text" name="templateBean.borrowerOther" value="${templateBean.borrowerOther}" /></td>
					</tr>
					</table>
			</td>
		</tr>
		<tr>
			<td class="td_title">档案密级</td>
			<td colspan="3" class="chkGroup" align="left"  validate="required">
					<table class="tb_checkbox">
					<col width="80">
					<col width="80">
					<col width="80">
					<col width="80">
					<col width="80">
					<tr>
					<td><s:checkbox name="templateBean.securityLevel1" id="securityLevel1" cssClass="group"></s:checkbox>绝密</td>
					<td><s:checkbox name="templateBean.securityLevel2" id="securityLevel2" cssClass="group"></s:checkbox>机密</td>
					<td><s:checkbox name="templateBean.securityLevel3" id="securityLevel3" cssClass="group"></s:checkbox>秘密</td>
					<td><s:checkbox name="templateBean.securityLevel4" id="securityLevel4" cssClass="group"></s:checkbox>内部公开</td>
					</tr>
					</table>
			</td>
		</tr>
		<tr>
			<td class="td_title">借阅日期</td>
			<td>
			 <input validate="required"  onfocus="WdatePicker()" onchange="onchange_DesignTotalDay()" class="Wdate inputBorder" id="fromDate" type="text" name="templateBean.borrowerTime" value="${templateBean.borrowerTime}"/>
			</td>
			<td class="td_title">归还日期</td>
			<td>
			 <input onfocus="WdatePicker()" onchange="onchange_DesignTotalDay()" class="Wdate inputBorder" type="text" validate="required" id="toDate" name="templateBean.returnTime" value="${templateBean.returnTime}"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">借阅用途说明</td>
			<td colspan="3">
			 <textarea class="inputBorder contentTextArea" validate="required" name="templateBean.borrowerState">${templateBean.borrowerState}</textarea>
			</td>
		</tr>
		<tr>
			<td class="td_title">档案资料</td>
			<td colspan="3">
				<table id="tbConItem" class="mainTable" style="margin-top: 5px;width:100%;">
				<tr class="thConItem" >
					<th >名称</th>
					<th>档案检索号</th>
					<th>页数</th>
					<th width="40">操作</th>
				</tr>
				<s:if test="statusCd==0 || statusCd==3">
				<tr id="trConItem" style="display: none;margin-bottom:5px;">
					<td><input class="inputBorder" validate="required"  type="text" name="templateBean.otherProperties[0].archivesName"  /></td>
					<td><input class="inputBorder" validate="required"  type="text" name="templateBean.otherProperties[0].archivesIndexNum"  /></td>
					<td><input class="inputBorder" validate="required"  type="text" name="templateBean.otherProperties[0].pageNum"  /></td>
					<td width="15px" align="center"><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a></td>
				</tr>
				</s:if>
				<s:iterator value="templateBean.otherProperties" var="item" status="s">
					<tr style="margin-bottom:5px;">
						<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].archivesName" value="${item.archivesName}" /></td>
						<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].archivesIndexNum" value="${item.archivesIndexNum}"  /></td>
						<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].pageNum" value="${item.pageNum}"  /></td>
						<td width="15px" align="center">
						<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
						<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
						</s:if>
						</td>
					</tr>
				</s:iterator>
				</table>
			</td>
		</tr>
		</table>
		
		<s:if test="#canEdit=='true'">
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
	var index='${conItemCount}';
	
	function addItem(){
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();
		$(trApprover_new).find(".inputBorder").each(function(i){
			this.name=this.name.replace('0',index);
		});
		//$(trApprover_new).find("#cloneUserCds").attr("id",cloneCount+"cloneUserCds");
		$("#tbConItem").append(trApprover_new);
		index++;
	}
	function delItem(dom){
		if($("#tbConItem tr").size() > 2) {
			$(dom).parent().parent().remove();
		}else{
			alert("必须至少有一条档案资料！");
			}
	}
</script>
<s:if test="#canEdit=='true'">
<script type="text/javascript">
	addItem();
</script>
</s:if>