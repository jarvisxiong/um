<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--笔记本购置申请单-->
<%@ include file="template-header.jsp"%>


<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%><div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>   
		<table  class="mainTable" width="100%;">
			<col width="100"/>
			<col width="80" />
			<col width="80"/>
			<col />
			<col width="60"/>
			<col width="140"/>
			<col>
			<tr>
				<td class="td_title">所属中心</td>
				<td colspan="6">
					<s:if test="resApproveInfoId==null">
					<c:set var="sendCenterName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %></c:set>
					<c:set var="sendCenterCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></c:set>
					</s:if>
					<s:else>
					<c:set var="sendCenterName">${templateBean.appCenterName}</c:set>
					<c:set var="sendCenterCd">${templateBean.appCenterCd}</c:set>
					</s:else>
					<input validate="required" type="text" name="templateBean.appCenterName" value="${sendCenterName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="centerCd" name="templateBean.appCenterCd" value="${sendCenterCd}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title" >申请人</td>
				<td colspan="2"><input class="inputBorder" validate="required" type="text" name="templateBean.applicant" value="${templateBean.applicant}"  /></td>
			    <td class="td_title">职位</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.position" value="${templateBean.position}"/></td>
			</tr>
			<tr>
			  	<td class="td_title">申购理由</td>
			  	<td colspan="6"><input class="inputBorder" validate="required" type="text" name="templateBean.appReason" value="${templateBean.appReason}"/></td>
			</tr>
			<s:if test="authTypeCd=='XZRL_XZGL_115'">
			<s:if test="nodeCd==13 && isMyApprove==1">
			<s:hidden id="isEdit" value="true"/>
			<tr>
				<td class="td_title" rowspan="2">成本控制中心<br/>审核</td>
			 	<td colspan="6">
				    <table id="tbConItem" width="100%;">
						<col width="100" />
						<col width="100"/>
						<col width="80"/>
						<col width="80"/>
						<col/>
						<col width="30"/>
						<tr>
				           	<td align="center">配置</td>
				            <td align="center">品牌型号</td>
				            <td align="center">价格(元)</td>
				            <td align="center">供应商</td>
				            <td align="center">注</td>
				            <td>&nbsp;</td>
			          	</tr>
					  	<tr id="trConItem" style="display: none;margin-bottom:5px;">
						    <td><input class="inputBorder" edit='true'  validate="required" type="text" name="templateBean.notebookComputer[0].configuration"/></td>
						    <td><input class="inputBorder" edit='true'  validate="required" type="text" name="templateBean.notebookComputer[0].computerType"/></td>
						    <td><input class="inputBorder" edit='true'  validate="required" type="text" name="templateBean.notebookComputer[0].price" onblur="formatVal($(this));"/></td>
						    <td><input class="inputBorder" edit='true'  validate="required" type="text" name="templateBean.notebookComputer[0].suppplier"/></td>
						    <td><input class="inputBorder" edit='true'  validate="required" type="text" name="templateBean.notebookComputer[0].compRemark"/></td>
						    <td valign="middle">
						      	<a herf="javascript:void(0);" onclick="doDeleteCont(this);">
						      		<img border="0" title="删除" src="${ctx}/resources/images/common/del_22_22.gif"/>
						      	</a>
						    </td>
					 	</tr>
						<s:set var="conItemCount"><s:property value="templateBean.notebookComputer.size()"/></s:set>
						<s:iterator value="templateBean.notebookComputer" var="item" status="s">
						<tr>
						    <td><input class="inputBorder" edit='true'  validate="required" type="text" name="templateBean.notebookComputer[<s:property value="#s.index" />].configuration" value="${item.configuration}"/></td>
						    <td><input class="inputBorder" edit='true'  validate="required" type="text" name="templateBean.notebookComputer[<s:property value="#s.index" />].computerType" value="${item.computerType}"/></td>
						    <td><input class="inputBorder" edit='true'  validate="required" type="text" name="templateBean.notebookComputer[<s:property value="#s.index" />].price" onblur="formatVal($(this));" value="${item.price}"/></td>
						    <td><input class="inputBorder" edit='true'  validate="required" type="text" name="templateBean.notebookComputer[<s:property value="#s.index" />].suppplier" value="${item.suppplier}"/></td>
						    <td><input class="inputBorder" edit='true'  type="text" name="templateBean.notebookComputer[<s:property value="#s.index" />].compRemark" value="${item.compRemark}"/></td>
						    <td valign="middle">
						   		<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
						      	<a herf="javascript:void(0)" onclick="doDeleteCont(this);">
						      		<img border="0" title="删除" src="${ctx}/resources/images/common/del_22_22.gif"/>
						      	</a>
						      	</s:if>
						    </td>
						</tr>
					 	</s:iterator>
			 		</table>
			 		<input type="button" onclick="doNewConf();" value="新增配置" class="btn_blue_75_55">
				</td>
			</tr>
			<tr>
			    <td colspan="2" class="td_title">确认机型及供应商</td>
			    <td colspan="4"><input class="inputBorder" edit='true'  type="text" name="templateBean.checkSupplier" value="${templateBean.checkSupplier}"/></td>
			</tr>
			</s:if>
			<s:else>
			<tr>
				<td class="td_title" rowspan="2">成本控制中心<br/>审核</td>
			 	<td colspan="6">
				    <table id="tbConItem" width="100%;">
						<col width="100" />
						<col width="100"/>
						<col width="80"/>
						<col width="80"/>
						<col/>
						<col width="30"/>
						<tr>
				           	<td align="center">配置</td>
				            <td align="center">品牌型号</td>
				            <td align="center">价格(元)</td>
				            <td align="center">供应商</td>
				            <td align="center">注</td>
				            <td>&nbsp;</td>
			          	</tr>
						<s:set var="conItemCount"><s:property value="templateBean.notebookComputer.size()"/></s:set>
						<s:iterator value="templateBean.notebookComputer" var="item" status="s">
						<tr>
						    <td><input class="inputBorder" validate="required" type="text" name="templateBean.notebookComputer[<s:property value="#s.index" />].configuration" value="${item.configuration}"/></td>
						    <td><input class="inputBorder" validate="required" type="text" name="templateBean.notebookComputer[<s:property value="#s.index" />].computerType" value="${item.computerType}"/></td>
						    <td><input class="inputBorder" validate="required" type="text" name="templateBean.notebookComputer[<s:property value="#s.index" />].price" onblur="formatVal($(this));" value="${item.price}"/></td>
						    <td><input class="inputBorder" validate="required" type="text" name="templateBean.notebookComputer[<s:property value="#s.index" />].suppplier" value="${item.suppplier}"/></td>
						    <td><input class="inputBorder" type="text" name="templateBean.notebookComputer[<s:property value="#s.index" />].compRemark" value="${item.compRemark}"/></td>
						    <td valign="middle">
						   		<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
						      	<a herf="javascript:void(0)" onclick="doDeleteCont(this);">
						      		<img border="0" title="删除" src="${ctx}/resources/images/common/del_22_22.gif"/>
						      	</a>
						      	</s:if>
						    </td>
						</tr>
					 	</s:iterator>
			 		</table>
				</td>
			</tr>
			<tr>
			    <td colspan="2" class="td_title">确认机型及供应商</td>
			    <td colspan="4"><input class="inputBorder" type="text" name="templateBean.checkSupplier" value="${templateBean.checkSupplier}"/></td>
			</tr>
			</s:else>
			</s:if>
			<s:if test="(nodeCd==115 || nodeCd==230) &&isMyApprove==1">
			<s:hidden id="isEdit" value="true"/>
			<tr>
			  	<td class="td_title">使用人</td>
			  	<td colspan="2"><input class="inputBorder" validate="required" edit='true' type="text" name="templateBean.userName" value="${templateBean.userName}"/></td>
			  	<td colspan="2" class="td_title">笔记本资产编码</td>
			  	<td colspan="2"><input class="inputBorder" validate="required" edit='true' type="text" name="templateBean.computerId" value="${templateBean.computerId}"/></td>
			</tr>
			<tr>
			  	<td class="td_title">笔记本价格(元)</td>
			  	<td colspan="2"><input class="inputBorder" onblur="formatVal($(this));" edit='true' validate="required" type="text" name="templateBean.computerPrice" value="${templateBean.computerPrice}"/></td>
			  	<td colspan="2" class="td_title">每月扣款金额(元)</td>
			  	<td colspan="2"><input class="inputBorder" onblur="formatVal($(this));" edit='true' validate="required" type="text" name="templateBean.payForMonth" value="${templateBean.payForMonth}"/></td>
			</tr>
			<tr>
			  	<td class="td_title">抵款起始日期</td>
			  	<td colspan="2"><input class="inputBorder Wdate" edit='true' onfocus="WdatePicker()" class="Wdate" validate="required" type="text" name="templateBean.payFromDate" value="${templateBean.payFromDate}"/></td>
			  	<td colspan="2" class="td_title">抵款终止日期</td>
			  	<td colspan="2"><input class="inputBorder Wdate" edit='true' validate="required"  onfocus="WdatePicker()" class="Wdate"type="text" name="templateBean.payToDate" value="${templateBean.payToDate}"/></td>
			</tr>
			<tr>
			  	<td class="td_title">笔记本返款日期</td>
			  	<td colspan="2"><input class="inputBorder Wdate" edit='true' onfocus="WdatePicker()" class="Wdate" validate="required" type="text" name="templateBean.inFromDate" value="${templateBean.inFromDate}"/></td>
			  	<td colspan="4"></td>
			</tr>
			<tr>
			   	<td class="td_title">备注</td>
			   	<td colspan="6"><textarea class="inputBorder contentTextArea" edit='true' name="templateBean.deptRemark">${templateBean.deptRemark}</textarea></td>
			</tr>
			</s:if>
			<s:else>
			<tr>
			  	<td class="td_title">使用人</td>
			  	<td colspan="2"><input class="inputBorder" type="text" name="templateBean.userName" value="${templateBean.userName}"/></td>
			  	<td colspan="2" class="td_title">笔记本资产编码</td>
			  	<td colspan="2"><input class="inputBorder" type="text" name="templateBean.computerId" value="${templateBean.computerId}"/></td>
			</tr>
			<tr>
			  	<td class="td_title">笔记本价格(元)</td>
			  	<td colspan="2"><input class="inputBorder" onblur="formatVal($(this));" type="text" name="templateBean.computerPrice" value="${templateBean.computerPrice}"/></td>
			  	<td colspan="2" class="td_title">每月扣款金额(元)</td>
			  	<td colspan="2"><input class="inputBorder" onblur="formatVal($(this));" type="text" name="templateBean.payForMonth" value="${templateBean.payForMonth}"/></td>
			</tr>
			<tr>
			  	<td class="td_title">抵款起始日期</td>
			  	<td colspan="2"><input class="inputBorder Wdate" onfocus="WdatePicker()" class="Wdate" type="text" name="templateBean.payFromDate" value="${templateBean.payFromDate}"/></td>
			  	<td colspan="2" class="td_title">抵款终止日期</td>
			  	<td colspan="2"><input class="inputBorder Wdate" onfocus="WdatePicker()" class="Wdate" type="text" name="templateBean.payToDate" value="${templateBean.payToDate}"/></td>
			</tr>
			<tr>
			  	<td class="td_title">笔记本返款日期</td>
			  	<td colspan="2"><input class="inputBorder Wdate" onfocus="WdatePicker()" class="Wdate" type="text" name="templateBean.inFromDate" value="${templateBean.inFromDate}"/></td>
			  	<td colspan="4"></td>
			</tr>
			<tr>
			   	<td class="td_title">备注</td>
			   	<td colspan="6"><textarea class="inputBorder contentTextArea" name="templateBean.deptRemark">${templateBean.deptRemark}</textarea></td>
			</tr>
			</s:else>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>

<script type="text/javascript">
var trApprover=$("#trConItem").clone();
$("#trConItem").remove();
var cloneCount = 0;
var index=${conItemCount};
function doNewConf(){
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
function doDeleteCont(dom){
	if(index<2){
		alert("不能删除");
		return false;
	}
	$(dom).parent().parent().remove();
	index--;
}
</script>
