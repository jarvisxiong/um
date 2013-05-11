<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--	合同审批表（其他情况） -->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
		<table  class="mainTable">
			<col width="80">
			<col width="70">
			<col>
			<col width="60">
			<col>
			<tr>
				<td  class="td_title">标题</td>
				<td colspan="4">
				<table class="tb_noborder">
					<col>
					<col width="40">
					<col>
					<col width="40">
					<tr>
					<td>
						<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
						<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
					</td>
					<td>项目</td>
					<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.contactName" size="100"  value="${templateBean.contactName}"/>
					</td>
					<td>合同</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td rowspan="2"  class="td_title">合同双方</td>
				<td  class="td_title">甲方</td>
				<td><input  class="inputBorder" type="text" name="templateBean.sideA" value="${templateBean.sideA}"/></td>
				<td class="td_title">签约人</td>
				<td><input class="inputBorder"  type="text" name="templateBean.signerA" value="${templateBean.signerA}"  /></td>
			</tr>	
			<tr>
				<td class="td_title">乙方</td>
				<td><input  class="inputBorder" type="text" name="templateBean.sideB" value="${templateBean.sideB}"  /></td>
				<td class="td_title">签约人</td>
				<td><input class="inputBorder"  type="text" name="templateBean.signerB" value="${templateBean.signerB}"/></td>
			</tr>
			<!-- Start Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td class="td_title">丙方</td>
				<td><input  class="inputBorder" type="text" name="templateBean.sideC" value="${templateBean.sideC}"  /></td>
				<td class="td_title">签约人</td>
				<td><input class="inputBorder"  type="text" name="templateBean.signerC" value="${templateBean.signerC}"/></td>
			</tr>
			<!-- End   Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td style="padding-left:5px;">选择合作<br/>单位方式</td>
				<td colspan="4" class="chkGroup">
					<table class="tb_noborder">
					<col width="120">
					<col>
					<tr>
					<td align="left"><s:checkbox name="templateBean.isInvite" cssClass="group" ></s:checkbox>招标(标书编号)</td>
					<td><input class="inputBorder" type="text" name="templateBean.inviteNo" value="${templateBean.inviteNo}"  /></td>
					</tr>
					<tr>
					<td align="left"><s:checkbox name="templateBean.isDirect" cssClass="group" ></s:checkbox>直接委托(理由)</td>
					<td><input class="inputBorder"  type="text" name="templateBean.directReason" value="${templateBean.directReason}"  /></td>
					</tr>
					<tr>
					<td align="left"><s:checkbox name="templateBean.isCompetitive" cssClass="group" ></s:checkbox>竞争性谈判</td>
					<td></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td rowspan="5" class="td_title">合同主要内容</td>
				<td class="td_title">标段</td>
				<td colspan="3"><input class="inputBorder"  type="text" name="templateBean.section" value="${templateBean.section}"  /></td>
			</tr>
			<tr>
				<td class="td_title" >工期</td>
				<td colspan="3"><input class="inputBorder"  type="text" name="templateBean.timeLimit" value="${templateBean.timeLimit}"  /></td>
			</tr>
			<tr>
				<td class="td_title" >范围/数量</td>
				<td colspan="3"><input class="inputBorder"  type="text" name="templateBean.scope" value="${templateBean.scope}"  /></td>
			</tr>
			<tr>
				<td class="td_title" >合同价款</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.conPrice" value="${templateBean.conPrice}"  /></td>
			</tr>
			<tr>
				<td class="td_title" >付款方式</td>
				<td colspan="3"><input class="inputBorder"  type="text" name="templateBean.conPayMode" value="${templateBean.conPayMode}"  /></td>
			</tr>
			<tr>
				<td  class="td_title" >备注</td>
				<td colspan="4"><textarea class="inputBorder contentTextArea" name="templateBean.conContent">${templateBean.conContent}</textarea></td>
			</tr>
		</table>
		<div style="color:red;">请先输入合同价款，再增加合同条款</div>
		<table id="tbConItem" class="mainTable" style="margin-top: 5px;">
			<tr>
				<th colspan="2" width="200">标准合同条款</th>
				<th rowspan="2">乙方修改意见</th>
				<th rowspan="2">责任部门</th>
				<th rowspan="2">责任部门意见</th>
				<th rowspan="2">成本意见</th>
				<th rowspan="2" width="40">操作</th>
			</tr>
			<tr>
				<th>条款号</th>
				<th>条款内容</th>
			</tr>
			<s:if test="statusCd==0 || statusCd==3">
			<tr id="trConItem" style="display: none;margin-bottom:5px;">
				<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[0].itemNo"  /></td>
				<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[0].itemContent"  /></td>
				<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[0].modifyOptionB"  /></td>
				<td class="tdGroupNodes"><s:select list="mapGroupNodes" listKey="key" listValue="value" validate="required" name="templateBean.otherProperties[0].resDeptCd" cssClass="inputBorder" emptyOption="true" ></s:select>
				</td>
				<td><!-- <input class="inputBorderItem"  type="text" name="templateBean.otherProperties[0].resDeptOption"  /> --></td>
				<td><!-- <input class="inputBorderItem"  type="text" name="templateBean.otherProperties[0].costOption"  /> --></td>
				<td width="15px" align="center"><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a></td>
			</tr>
			</s:if>
			<s:iterator value="templateBean.otherProperties" var="item" status="s">
				<tr style="margin-bottom:5px;">
					<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].itemNo" value="${item.itemNo}"  /></td>
					<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].itemContent" value="${item.itemContent}"  /></td>
					<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].modifyOptionB" value="${item.modifyOptionB}"  /></td>
					<td class="tdGroupNodes">
					<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
					<select class="inputBorder" validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].resDeptCd">
					    <option value=""></option>
					    <s:iterator value="mapGroupNodes" var="m" >
					    <option value="${m.key}" <c:if test="${item.resDeptCd==m.key}">selected="selected"</c:if>>${m.value}</option>
					    </s:iterator>
					</select>
					</s:if>
					<s:else>
					<input class="inputBorder"  type="hidden" name="templateBean.otherProperties[<s:property value="#s.index" />].resDeptCd" value="${item.resDeptCd}"  />
					<input class="inputBorder"  type="text" value="<%=CodeNameUtil.getResNodeNameByCd(JspUtil.findString("#item.resDeptCd")) %>"  />
					</s:else>
					</td>
					<c:set var="curDeptCd" >;${item.resDeptCd};</c:set>
					<c:if test="${!(fn:contains(myNode, curDeptCd)&&isMyApprove==1)}">
					<td><input class="inputBorder" readonly="readonly" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].resDeptOption" value="${item.resDeptOption}"/></td>
					</c:if>
					<c:if test="${(fn:contains(myNode, curDeptCd) && isMyApprove==1)}">
					<s:hidden id="isEdit" value="true"/>
					<td><input class="inputBorder" edit='true' validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].resDeptOption" value="${item.resDeptOption}"/></td>
					</c:if>
					<s:if test="curNodeCd==97 && isMyApprove==1">
					<s:hidden id="isEdit" value="true"/>
					<td><input class="inputBorder" edit='true' validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].costOption" value="${item.costOption}"  /></td>
					</s:if>
					<s:else>
					<td><input class="inputBorder" readonly="readonly" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].costOption" value="${item.costOption}"  /></td>
					</s:else>
					
					<td width="15px" align="center">
					<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
					<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
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
	var index=${conItemCount};
	
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
		$(dom).parent().parent().remove();
	}
</script>
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>