<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 答疑审批表  -->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
		<table  class="mainTable" style="margin-bottom: 0px;">
			<col width="150"/>
			<col/>
			<col width="120"/>
			<col/>
			<tr>
				<td class="td_title">项目名称</td>
				<td>
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
				<td class="td_title">工程名称</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.engineeringName" value="${templateBean.engineeringName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">招标文件编号</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.bidFileCd" value="${templateBean.bidFileCd}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">答疑回复时间</td>
				<td>
				<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.answerDate" value="${templateBean.answerDate}"/>
				</td>
				<td class="td_title">答疑附件页数</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.answerAttachmentTotalPage" value="${templateBean.answerAttachmentTotalPage}" onblur="formatNum($(this));" />
				</td>
			</tr>
		</table>
		
		<table id="tbNew" class="mainTable" style="margin-top: 5px;">
			<col width="150"/>
			<col/>
			<col width="120"/>
			<col/>
			<col width="40"/>
			<tr>
				<td class="td_title">投标单位疑问</td>
				<td colspan="3">
				<input class="inputBorder"  type="text" name="templateBean.otherProperties[0].bidUnitQuestion" />
				</td>
				<td align="center">
				<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
				<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
				</s:if>
				</td>
			</tr>
			<tr>
				<td class="td_title">责任部门</td>
				<td class="tdGroupNodes">
				<s:select list="mapGroupNodes" listKey="key" listValue="value" validate="required" name="templateBean.otherProperties[0].resDeptCd" cssClass="inputBorder" emptyOption="true" ></s:select>
				</td>
				<td class="td_title">责任部门回复</td>
				<td colspan="2">
				<!--<input class="inputBorder"  type="text" name="templateBean.otherProperties[0].resDeptOption" />-->
				</td>
			</tr>
			<tr>
				<td class="td_title">区域公司成本管理部回复</td>
				<td colspan="4">
				<!--<input class="inputBorder"  type="text" name="templateBean.otherProperties[0].senderOption" />-->
				</td>
			</tr>
			<tr>
				<td class="td_title">区域总经理回复</td>
				<td>
				<!--<input class="inputBorder"  type="text" name="templateBean.otherProperties[0].regionManagerOption" />-->
				</td>
				<td class="td_title">最终回复执行副总裁</td>
				<td colspan="2">
				<!--<input class="inputBorder"  type="text" name="templateBean.otherProperties[0].vicePresidentsOption" />-->
				</td>
			</tr>
		</table>
		<s:iterator value="templateBean.otherProperties" var="item" status="s">
			<table id="tbNew" class="mainTable" style="margin-top: 5px;">
				<col width="150"/>
				<col/>
				<col width="120"/>
				<col/>
				<col width="40"/>
				<tr>
					<td class="td_title">投标单位疑问</td>
					<td colspan="3">
					<input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].bidUnitQuestion" value="${item.bidUnitQuestion}" />
					</td>
					<td align="center">
					<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
					<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
					</td>
				</tr>
				<tr>
					<td class="td_title">责任部门</td>
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
					<td class="td_title">责任部门回复</td>
					<td colspan="2">
					<c:set var="curDeptCd" >;${item.resDeptCd};</c:set>
					<c:if test="${!(fn:contains(myNode, curDeptCd) && isMyApprove==1)}">
					<input class="inputBorder" readonly="readonly" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].resDeptOption" value="${item.resDeptOption}"/>
					</c:if>
					<c:if test="${(fn:contains(myNode, curDeptCd) && isMyApprove==1)}">
					<s:hidden id="isEdit" value="true"/>
					<input class="inputBorder" edit='true' validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].resDeptOption" value="${item.resDeptOption}"/>
					</c:if>
					</td>
				</tr>
				<tr>
					<td class="td_title">区域公司成本管理部回复</td>
					<td colspan="4">
					<s:if test="(#curNodeCd==156 && isMyApprove==1)">
					<s:hidden id="isEdit" value="true"/>
					<input class="inputBorder" edit='true' type="text" validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].costDeptOption" value="${item.costDeptOption}" />
					</s:if>
					<s:else>
					<input class="inputBorder" readonly="readonly" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].costDeptOption" value="${item.costDeptOption}" />
					</s:else>
					</td>
				</tr>
				<tr>
					<td class="td_title">区域总经理回复</td>
					<td>
					<s:if test="(#curNodeCd==157 && isMyApprove==1)">
					<s:hidden id="isEdit" value="true"/>
					<input class="inputBorder" edit='true' type="text" validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].regionManagerOption" value="${item.regionManagerOption}" />
					</s:if>
					<s:else>
					<input class="inputBorder" readonly="readonly" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].regionManagerOption" value="${item.regionManagerOption}" />
					</s:else>
					</td>
					<td class="td_title">最终回复执行副总裁</td>
					<td colspan="2">
					<s:if test="(#curNodeCd==148 && isMyApprove==1)">
					<s:hidden id="isEdit" value="true"/>
					<input class="inputBorder" edit='true' type="text" validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].vicePresidentsOption" value="${item.vicePresidentsOption}" />
					</s:if>
					<s:else>
					<input class="inputBorder" readonly="readonly" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].vicePresidentsOption" value="${item.vicePresidentsOption}" />
					</s:else>
					</td>
				</tr>
			</table>
		</s:iterator>
		
		<div id="userTable">
		</div>
		
		<s:if test="statusCd==0 || statusCd==3">
		<input type="button" id="btAdd" class="btn_blue_75_55" value="增加条款" onclick="addItem();" />
		</s:if>
				
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
	var trApprover=$("#tbNew").clone();
	$("#tbNew").remove();
	var cloneCount = 0;
	var index=${conItemCount};
	
	function addItem(){
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();
		$(trApprover_new).find(".inputBorder").each(function(i){
			this.name=this.name.replace('0',index);
		});
		$(trApprover_new).find("#cloneUserCds").attr("id",cloneCount+"cloneUserCds");
		$("#userTable").append(trApprover_new);
		index++;
	}
	function delItem(dom){
		$(dom).parent().parent().parent().remove();
	}
	
</script>
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>
