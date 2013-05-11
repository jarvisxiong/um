<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 目标成本设定审批表 --%>
<%@ include file="template-header.jsp"%>
<div class="billContent" align="center">
	
	<%--是否可编辑表单 --%>
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
	  <table class="mainTable">
	    <col width="120"/>
		<col/>
		<col/>
		<col width="120"/>
		<col/>
		<col/>
		<tr>
			<td class="td_title">审核权限</td>
			<td align="left" colspan="5">
				<s:checkbox name="templateBean.approvePrivFlg"/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td style="border-top:0 none;" class="chkGroup" align="left"  validate="required" colspan="5">
				<table class="tb_checkbox">
					<colgroup>
						<col width="150">
						<col width="150">
					</colgroup>
					<tr>
						<td><s:checkbox name="templateBean.ctSearchFlg"  cssClass="group"></s:checkbox>目标成本(可研版)</td>
						<td><s:checkbox name="templateBean.ctFlg"  cssClass="group"></s:checkbox>目标成本</td>
					</tr>
				</table>
			</td> 
		</tr>
		<tr>
			<td class="td_title">项目名称</td>
			<td align="left" colspan="4">
				<input validate="required" id="projectName inputBorderNoTip" class="inputBorder" type="text" readonly="readonly" name="templateBean.projectName" value="${templateBean.projectName}" style="width:100%;cursor: pointer;" 
				<s:if test="#canEdit=='true'">
					onclick="initSelectFlag($(this))" 
				</s:if>	
				/>
				<input id="projectCd" type="hidden"  name="templateBean.projectCd" value="${templateBean.projectCd}"/>
			</td>
			<td>
				(<input class="inputBorder" type="text" name="templateBean.periods" value="${templateBean.periods}" style="width:60px;text-align: center;"/>)期
			</td>
		</tr>
		<tr>
			<td class="td_title">开工时间</td>
			<td align="left" colspan="2">
				<input class="inputBorder" type="text" id="startDate" name="templateBean.startDate"  value="${templateBean.startDate}" validate="required"  onfocus="WdatePicker();" />
			</td>
			<td class="td_title">交付时间</td>
			<td align="left" colspan="2">
				<input class="inputBorder" type="text" id="handDate"  name="templateBean.handDate"  value="${templateBean.handDate}" validate="required" onfocus="WdatePicker();" />
			</td>
		</tr>
		<tr>
			<td class="td_title">总建筑面积</td>
			<td align="left" colspan="2">
				<input class="inputBorder" type="text" name="templateBean.totalConsArea" id="totalConsArea" value="${templateBean.totalConsArea}" validate="required"  onblur="formatVal($(this));"/>
			</td>
			<td class="td_title">计入容积率建筑面积(m2)</td>
			<td align="left" colspan="2">
				<input class="inputBorder" type="text" name="templateBean.plotRateArea" id="plotRateArea" value="${templateBean.plotRateArea}"  validate="required" onblur="formatVal($(this));"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">目标成本总额(元)</td>
			<td align="left" colspan="2">
				<input class="inputBorder" type="text" name="templateBean.costTargetTotalAmt" id="costTargetTotalAmt" value="${templateBean.costTargetTotalAmt}"  validate="required" onblur="formatVal($(this));"/>
			</td>
			<td class="td_title">容积率面积单方造价(元/m2)</td>
			<td align="left" colspan="2">
				<input class="inputBorder" type="text" name="templateBean.plotRateUnitAmt" id="plotRateUnitAmt" value="${templateBean.plotRateUnitAmt}"  validate="required" onblur="formatVal($(this));"/>
			</td>
		</tr>
		 <tr>
		    <td class="td_title">主要内容及说明</td>
			<td align="left" colspan="5">
				<textarea validate="required" name="templateBean.mainContentDesc" style="width:100%;">${templateBean.mainContentDesc }</textarea>
			</td>
		</tr>
		<tr>
			<td rowspan="3" align="right">附件上传</td>
			<td colspan="2" align="center" style="height:40px;">规划指标或产品标准(附件)</td>
			<td align="center" validate="required" value="${templateBean.productAttachId}"><%--注意:这里的td赋值value,用于检查是否上传附件 --%>
				<s:if test="#canEdit=='true'">
					<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('规划指标或产品标准(附件)','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','productAttachId',event);"/>
					<input id="productAttachId" type="hidden" name="templateBean.productAttachId" value="${templateBean.productAttachId}"/>
				</s:if>
			</td>
			<td colspan="2">
				<div id="show_productAttachId"></div>
				<script type="text/javascript">
					showUploadedFile('${templateBean.productAttachId}','productAttachId','${canEdit}');
				</script>
			</td>
		</tr>
		<tr>
			<td align="center" style="height:40px;" value="${templateBean.ctDescAttachId}" colspan="2">目标成本编制说明(附件)</td>
			<td align="center">
				<s:if test="#canEdit=='true'">
					<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('目标成本编制说明(附件)','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','ctDescAttachId',event);"/>
					<input id="ctDescAttachId" type="hidden" name="templateBean.ctDescAttachId" value="${templateBean.ctDescAttachId}"/>
				</s:if>
			</td>
			<td colspan="2">
				<div id="show_ctDescAttachId"></div>
				<script type="text/javascript">
					showUploadedFile('${templateBean.ctDescAttachId}','ctDescAttachId','${canEdit}');
				</script>
			</td>
		</tr>
		<tr>
			<td align="center" style="height:40px;" value="${templateBean.ctConsAttachId}" colspan="2">目标成本组成表(附件)</td>
			<td align="center">
				<s:if test="#canEdit=='true'">
					<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('目标成本组成表(附件)','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','ctConsAttachId',event);"/>
					<input id="ctConsAttachId" type="hidden" name="templateBean.ctConsAttachId" value="${templateBean.ctConsAttachId}"/>
				</s:if>
			</td>
			<td colspan="2">
				<div id="show_ctConsAttachId"></div>
				<script type="text/javascript">
					showUploadedFile('${templateBean.ctConsAttachId}','ctConsAttachId','${canEdit}');
				</script>
			</td>
		</tr>
	  </table>
	  <%@ include file="template-approver.jsp"%>
	 </form>
</div>
<%@ include file="template-footer.jsp"%> 


<script language="javascript">
	function initSelectFlag(jDom){
		if( jDom.attr('initselectflg') != '1'){
			jDom.attr('initselectflg','1'); 
			jDom.orgSelect({
				showProjectOrg:true
			});
			jDom.trigger('click');
		}
	}
</script>