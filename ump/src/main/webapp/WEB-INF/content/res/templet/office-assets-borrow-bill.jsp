<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--办公资产借用单-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
		<table  class="mainTable">
			<col width="100"/>
			<col/>
			<col width="100"/>
			<col/>
			<tr>
				<td class="td_title">借用期间</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.borrowPeriod" value="${templateBean.borrowPeriod}"  />
				</td>
				<td class="td_title">经办人</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.operator" value="${templateBean.operator}"  />
				</td>
			</tr>
			<tr>
				<td  class="td_title">借用公司/中心</td>
				<td>
					<input validate="required" type="text" name="templateBean.centerName" value="${templateBean.centerName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="centerCd" name="templateBean.centerCd" value="${templateBean.centerCd}"  />
				</td>
				<td class="td_title">借用责任人</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.borrowPerson" value="${templateBean.borrowPerson}"  />
				</td>
			</tr>
			
			<tr>
				<td class="td_title">金额(元)</td>
				<td colspan="3">
					<input class="inputBorder" validate="required"  onblur="formatVal($(this));" type="text" name="templateBean.totalMoney" value="${templateBean.totalMoney}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">申请借用原因</td>
				<td colspan="3"><textarea id="idDesc" class="inputBorder contentTextArea" validate="required" name="templateBean.reason">${templateBean.reason}</textarea></td>
			</tr>
		</table>
		<table  class="mainTable" id="giftTable">
			<col width="15%"/>
			<col width="30%"/>
			<col width="15%"/>
			<col width="60"/>
			<col width="20%"/>
			<col width="30"/>
			<tr>
				<td align="center">分类编号</td>
				<td align="center">设备名称</td>
				<td align="center">规格型号</td>
				<td align="center">数量</td>
				<td align="center">备注</td>
				<td align="center"></td>
			</tr>
			<tr id="trConItem" style="display:none;">
				<td >
				<input class="inputBorder" validate="required" type="text" name="templateBean.otherProperties[0].sortNo"/>
				</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.otherProperties[0].equipName"/>
				</td>
				<td>
				<input class="inputBorder" validate="required"  type="text" name="templateBean.otherProperties[0].standardNo"/>
				</td>
				<td>
				<input class="inputBorder" validate="required"  type="text" onblur="formatVal($(this));" name="templateBean.otherProperties[0].quantity"/>
				</td>
				<td>
				<input class="inputBorder"  type="text" name="templateBean.otherProperties[0].remark"/>
				</td>
				<td>
				<a href="javascript:void(0)" onclick="delItem(this)"><img title="删除" src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
				</td>
			</tr>
			<s:iterator value="templateBean.otherProperties" var="item" status="s">
			<tr>
				<td >
				<input class="inputBorder" validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].sortNo" value="${item.sortNo}"/>
				</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].equipName" value="${item.equipName}"/>
				</td>
				<td>
				<input class="inputBorder" validate="required" type="text"  name="templateBean.otherProperties[<s:property value="#s.index" />].standardNo" value="${item.standardNo}"/>
				</td>
				<td>
				<input class="inputBorder" validate="required" type="text"  onblur="formatVal($(this));" name="templateBean.otherProperties[<s:property value="#s.index" />].quantity" value="${item.quantity}"/>
				</td>
				<td>
				<input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].remark" value="${item.remark}"/>
				</td>
				<td>
				<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
				<a href="javascript:void(0)" onclick="delItem(this)"><img title="删除" src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
				</s:if>
				</td>
			</tr>
			</s:iterator>
		</table>
		<s:if test="statusCd==0 || statusCd==3">
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
	var index=0;
	//var index=${conItemCount};
	function addItem(){
		
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();
		$(trApprover_new).find(".inputBorder").each(function(i){
			this.name=this.name.replace('0',index);
		});
		//$(trApprover_new).find("#cloneUserCds").attr("id",cloneCount+"cloneUserCds");
		$("#giftTable").append(trApprover_new);
		index++;
	}
	//addItem();
	function delItem(dom){
		$(dom).parent().parent().remove();
	}
</script>


<%--默认1行 --%>
<s:if test="resApproveInfoId==null">
	
	<script type="text/javascript">
		addItem();
		$('#centerName').val('<%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %>');
		$('#centerCd').val('<%=SpringSecurityUtils.getCurrentCenterCd() %>');
	</script>
</s:if>