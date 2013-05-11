<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 工程造价咨询单项委托单审批表 --%>
<%@ include file="template-header.jsp"%>
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
			<col width="110"/>
			<col/>
			<col width="125"/>
			<col/>
			<tr>
				<td class="td_title">合同编号</td>
				<td colspan="3" align="left">
				
				<input type="hidden" id="contLedgerId" name="templateBean.contLedgerId" value="${templateBean.contLedgerId}"/>
				
				<s:if test="!templateBean.contLedgerId.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getCont('${templateBean.contLedgerId}');">${templateBean.contNo}</span>
						<input id="contNo" type="hidden" name="templateBean.contNo" value="${templateBean.contNo}" />
					</s:if>
					<s:else>
					<input class="inputBorder" validate="required" type="text" id="contNo" name="templateBean.contNo" value="${templateBean.contNo}"/>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="td_title">合同名称</td>
				<td>
				<input class="inputBorder" validate="required" readonly="readonly" type="text" id="contName" name="templateBean.contName" value="${templateBean.contName}"/>
				</td>
				<td class="td_title">乙方</td>
				<td>
				<input class="inputBorder" validate="required" id="partB" readonly="readonly" type="text" name="templateBean.partB" value="${templateBean.partB}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">项目名称</td>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col/>
						<col width="20"/>
						<col width="40"/>
						<col width="20"/>
						<tr>
						<td>
						<input class="inputBorder" id="projectName" validate="required" readonly="readonly" type="text" name="templateBean.projectName" value="${templateBean.projectName}"/>
						<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
						</td>
						<td style="text-align: right;">(</td>
						<td>
						<input class="inputBorder" id="projectPeriod" type="text" name="templateBean.projectPeriod" value="${templateBean.projectPeriod}" />
						</td>
						<td>)期</td>
						</tr>
					</table>
				</td>
				<td class="td_title">面积(m2)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" id="area" name="templateBean.area" value="${templateBean.area}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">甲方委托部门</td>
				<td>
					<input class="inputBorder" id="contCharacter" validate="required" type="text" name="templateBean.partATrustPart" value="${templateBean.partATrustPart}"/>
				</td>
				<td class="td_title">甲方经办人</td>
				<td>
					<input class="inputBorder" id="contCharacter" validate="required" type="text" name="templateBean.partAOperator" value="${templateBean.partAOperator}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">咨询费用计算规则</td>
				<td>
				<input class="inputBorder" type="text" name="templateBean.consultFeeRule" value="${templateBean.consultFeeRule}"/>
				</td>
				<td class="td_title">咨询费比例</td>
				<td>
				<input class="inputBorder" type="text" validate="required" name="templateBean.consultRate" value="${templateBean.consultRate}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">委托事项</td>
				<td>
				 <table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<tr>
						<td>
						<input class="inputBorder" type="text" name="templateBean.trustBidItem" value="${templateBean.trustBidItem}" style="width:50%;"/>
						工程招标造价咨询
						</td>
						</tr>
						<tr>
						<td>
						<input class="inputBorder" type="text" name="templateBean.settleItem" value="${templateBean.settleItem}" style="width:50%;"/>
						工程结算造价咨询
						</td>
						</tr>
					</table>
				</td>
				<td class="td_title">预估咨询费(元)</td>
				<td>
				<input class="inputBorder" type="text" validate="required" name="templateBean.preConsultFee" value="${templateBean.preConsultFee}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">招标报价模式</td>
				<td colspan="3" class="chkGroup">
				 <table class="tb_checkbox">
					<tr>
					<td><s:checkbox name="templateBean.totalDoPrice" cssClass="group"></s:checkbox>总价包干报价</td>
					<td><s:checkbox name="templateBean.totalMainPrice" cssClass="group"></s:checkbox>总价包干为主，局部模拟清单报价</td>
					<td><s:checkbox name="templateBean.analogPrice" cssClass="group"></s:checkbox>模拟清单报价</td>
					<td><s:checkbox name="templateBean.quotaPrice" cssClass="group"></s:checkbox>定额费率报价</td>
					<td><s:checkbox name="templateBean.otherPrice" cssClass="group"></s:checkbox>其他</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="7">咨询业务内容</td>
				<td>
				<table class="tb_checkbox">
					<tr>
					<td><s:checkbox name="templateBean.totalDoPrice" cssClass="group"></s:checkbox>编制工程量清单</td>
					</tr>
					</table>
				</td>
				<td>提交时间</td>
				<td><input class="inputBorder" type="text" name="templateBean.reformListDate" value="${templateBean.reformListDate}" onfocus="WdatePicker();"/></td>
			</tr>
			<tr>
				<td>
				<table class="tb_checkbox">
					<tr>
					<td><s:checkbox name="templateBean.controlProjectList" cssClass="group"></s:checkbox>编制工程造价标底及最高控制价</td>
					</tr>
				 </table>
				</td>
				<td>提交时间</td>
				<td><input  class="inputBorder" type="text" name="templateBean.controlListDate" value="${templateBean.controlListDate}" onfocus="WdatePicker();"/></td>
			</tr>
			<tr>
			 <td>
				<table class="tb_checkbox">
					<tr>
					<td><s:checkbox name="templateBean.afterProjectList" cssClass="group"></s:checkbox>工程量偏差标后核对(适用于总价包干报价范围)</td>
					</tr>
				 </table>
				</td>
				<td>完成时间(定标前)</td>
				<td><input  class="inputBorder" type="text" name="templateBean.afterListDate" value="${templateBean.afterListDate}" onfocus="WdatePicker();"/></td>
			</tr>
			<tr>
			 <td>
				<table class="tb_checkbox">
					<tr>
					<td><s:checkbox name="templateBean.drawingAfterList" cssClass="group"></s:checkbox>重新度量，施工图预算标后核对(适用于模拟清单、定额费率报价)</td>
					</tr>
				 </table>
				</td>
				<td>预计工作时间段</td>
				<td><input  class="inputBorder" type="text" name="templateBean.planWorkPeriod" value="${templateBean.planWorkPeriod}" onfocus="WdatePicker();"/></td>
			</tr>
			<tr>
			 <td>
				<table class="tb_checkbox">
					<tr>
					<td><s:checkbox name="templateBean.writtenComments" cssClass="group"></s:checkbox>施工图的错漏彭雀的书面意见反馈</td>
					</tr>
				 </table>
				</td>
				<td>提交时间</td>
				<td><input  class="inputBorder" type="text" name="templateBean.submitDate" value="${templateBean.submitDate}" onfocus="WdatePicker();"/></td>
			</tr>
			<tr>
			 <td>
				<table class="tb_checkbox">
					<tr>
					<td><s:checkbox name="templateBean.projectSettlement" cssClass="group"></s:checkbox>施工结算</td>
					</tr>
				 </table>
				</td>
				<td>预计工作时间段</td>
				<td><input  class="inputBorder" type="text" name="templateBean.projectWorkPeriod" value="${templateBean.projectWorkPeriod}" onfocus="WdatePicker();"/></td>
			</tr>
			<tr>
				<td>
					<table class="tb_checkbox">
						<tr>
						<td><s:checkbox name="templateBean.otherConsultContent" cssClass="group"></s:checkbox>其他咨询内容</td>
						</tr>
					 </table>
				 </td>
				 <td colspan="2">
				 <input  class="inputBorder" type="text" name="templateBean.ohterConsContent" value="${templateBean.ohterConsContent}"/>
				 </td>
			</tr>
			<tr>
			 <td rowspan="4">委托方(甲方)提供资料</td>
			 <td>
			 招标施工图(加盖招标专用章)
			  </td>
			 <td>提交时间</td>
			 <td><input  class="inputBorder" type="text" name="templateBean.bidDrawingDate" value="${templateBean.bidDrawingDate}" onfocus="WdatePicker();"/></td>
			</tr>
			<tr>
			 <td>招标文件</td>
				<td>提交时间</td>
				<td><input  class="inputBorder" type="text" name="templateBean.bidFileDate" value="${templateBean.bidFileDate}" onfocus="WdatePicker();"/></td>
			</tr>
			<tr>
			 <td>正式施工图(适用于标后核对，加盖标后核对专用章)</td>
			 <td>预计提交时间</td>
			 <td><input  class="inputBorder" type="text" name="templateBean.officialSubmitDate" value="${templateBean.officialSubmitDate}" onfocus="WdatePicker();"/></td>
			</tr>
			<tr>
			 <td colspan="3">其他
			 <input class="inputBorder" style="width:90%" type="text" name="templateBean.other" value="${templateBean.other}"/>
			 </td>
			</tr>
			<tr>
			 <td>其他咨询机构回执确认签字</td>
			 <td colspan="3"></td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
function getCont(id){
	var url="${ctx}/cont/cont-ledger!input.action?id="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cont-ledger-input","合同台账查看",url,true);
}
var mapPrama={
		contLedgerId:'contLedgerId',
		contNo:'contNo',
		contName:'contName',
		projectCd:'projectCd',
		projectName:'projectName',
		partB:'partB'
	};
	$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama);
</script>