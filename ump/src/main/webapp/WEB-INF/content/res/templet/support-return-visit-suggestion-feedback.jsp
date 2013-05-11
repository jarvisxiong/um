<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--供方履约情况回访意见反馈汇总表--%>
<%@ include file="template-header.jsp"%>


<%@page import="com.hhz.ump.util.DictContants"%>
<div align="center" class="billContent">
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
	</s:set>
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="60"/>
			<col/>
			<col/>
			<tr>
				<td class="td_title">回访范围</td>
				<td colspan="2">
				<table id="tbConItem" style="margin-top: 5px;" width="100%">
							<col>
							<col>
							<col>
							<col>
							<col>
							<col width="40">
							<tr>
								<td align="center">供方类型</td>
								<td align="center">供方单位名称</td>
								<td align="center">承接项目</td>
								<td align="center">对接人</td>
								<td align="center">手机</td>
								<td align="center">操作</td>
							</tr>
							<s:if test="statusCd==0 || statusCd==3">
							<tr id="trConItem" style="display: none;margin-bottom:5px;">
								<td class="tdGroupNodes">
									<s:if test="#isMy!=1">
										<input class="inputBorderNoTip" type="text" name="templateBean.supportReturnVisitScope[0].supportType" value="<%=CodeNameUtil.getDictNameByCd(DictContants.SUPPORT_APPOINTMENT_TYPE,JspUtil.findString("templateBean.supportReturnVisitScope[0].supportType")) %>"/>
									</s:if>
									<s:else>
										<s:select cssClass="inputBorder" validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapSupportType()" listKey="key" listValue="value" name="templateBean.supportReturnVisitScope[0].supportType" ></s:select>
									</s:else>
								</td>
								<td class="tdGroupNodes">
									<input validate="required" class="inputBorder" type="text" name="templateBean.supportReturnVisitScope[0].supportUnitName"/>
								</td>
								<td class="tdGroupNodes">
									<input validate="required" class="inputBorder" type="text" name="templateBean.supportReturnVisitScope[0].acceptProject"/>
								</td>
								<td>
									<input validate="required" class="inputBorder" type="text" name="templateBean.supportReturnVisitScope[0].joinPeople"/>
								</td>
								<td>
									<input validate="required" class="inputBorder" type="text" name="templateBean.supportReturnVisitScope[0].telNo"/>
								</td>
								<td><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/></a></td>
							</tr>
							</s:if>
							<s:set var="conItemCount"><s:property value="templateBean.supportReturnVisitScope.size()"/></s:set>
							<s:iterator value="templateBean.supportReturnVisitScope" var="item" status="s">
								<tr style="margin-bottom:5px;">
									<td class="tdGroupNodes">
									<s:if test="#isMy!=1">
										<input class="inputBorderNoTip" type="text" name="templateBean.supportReturnVisitScope[0].supportType" value="<%=CodeNameUtil.getDictNameByCd(DictContants.SUPPORT_APPOINTMENT_TYPE,JspUtil.findString("templateBean.supportReturnVisitScope[0].supportType")) %>"/>
									</s:if>
									<s:else>
										<s:select cssClass="inputBorder" validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapSupportType()" listKey="key" listValue="value" name="templateBean.supportReturnVisitScope[#s.index].supportType" ></s:select>
									</s:else>
								</td>
								<td class="tdGroupNodes">
									<input validate="required" class="inputBorder" type="text" name="templateBean.supportReturnVisitScope[<s:property value="#s.index" />].supportUnitName" value="${item.supportUnitName}"/>
								</td>
								<td class="tdGroupNodes">
									<input validate="required" class="inputBorder" type="text" name="templateBean.supportReturnVisitScope[<s:property value="#s.index" />].acceptProject" value="${item.acceptProject}"/>
								</td>
								<td class="tdGroupNodes">
									<input validate="required" class="inputBorder" type="text" name="templateBean.supportReturnVisitScope[<s:property value="#s.index" />].joinPeople" value="${item.joinPeople}"/>
								</td>
								<td class="tdGroupNodes">
									<input validate="required" class="inputBorder" type="text" name="templateBean.supportReturnVisitScope[<s:property value="#s.index" />].telNo" value="${item.telNo}"/>
								</td>
									
								<td>	
									<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
									<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/></a>
									</s:if>
								</td>
								</tr>
							</s:iterator>
						</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">反馈问题简述</td>
				<td colspan="2">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.feedbackDesc">${templateBean.feedbackDesc}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">附件上传</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input value="上传附件" class="btn_green_65_20" style="border: medium none;" onclick="showUploadSingleAttachDialog('回访意见反馈表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','attachmentes',event);" type="button">
				<input name="templateBean.attachmentes" id="attachmentes" value="${templateBean.attachmentes}" type="hidden">
				</s:if>
				</td>
				<td>
				<div id="show_attachmentes"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.attachmentes}','attachmentes','${canEdit}');
				</script>
				</td>
			</tr>
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
	try{
		index = Number("${conItemCount}");
	}catch(e){}
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
<!-- 默认1条申请记录 -->
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>
