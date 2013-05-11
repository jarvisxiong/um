<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
	租金减免审批表(eg:租金减免审批表) 
--%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">

	

	<!-- 
		注：1、此单审批完成后，应向宝龙商业财务部备案。
	 -->
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="130px">
			<col />
			<col width="130px">
			<col />
			
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="3">
					<input id="projectName" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" name="templateBean.projectCd" value="${templateBean.projectCd}" />
				</td>
			</tr> 
			<tr>
				<td class="td_title">商户名称</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.storeName" value="${templateBean.storeName}" />
				</td>
				<td class="td_title">店面号</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.storeNo" value="${templateBean.storeNo}" />
				</td>
			</tr> 
			<tr>
				<td class="td_title">面积</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.area" value="${templateBean.area}" />
				</td>
				<td class="td_title">合同期</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.contractPeriod" value="${templateBean.contractPeriod}" />
				</td>
			</tr> 
			<tr>
				<td class="td_title">减免类型</td>
				<td class="chkGroup" align="left"  validate="required">
					<table class="tb_checkbox">
						<col width="100">
						<col width="100">
						<tr>
						<td><s:checkbox name="templateBean.feeTypeCd1" ></s:checkbox>物业管理费 </td>
						<td><s:checkbox name="templateBean.feeTypeCd2" ></s:checkbox>公摊费用</td>
						</tr>
						<tr>
						<td><s:checkbox name="templateBean.feeTypeCd3" ></s:checkbox>违约金 </td>
						<td><s:checkbox name="templateBean.feeTypeCd4" ></s:checkbox>对商户补偿</td>
						</tr>
					</table>
				</td> 
				<td class="td_title">物业性质</td>
				<td class="chkGroup" align="left"  validate="required">
					<table class="tb_checkbox">
						<col width="100">
						<col width="100">
						<tr>
						<td><s:checkbox name="templateBean.charactCd1"  cssClass="group"></s:checkbox>自持</td>
						<td><s:checkbox name="templateBean.charactCd2"  cssClass="group"></s:checkbox>销售</td>
						</tr>
					</table>
				</td> 
			</tr>
			<tr>
				<td class="td_title">物业管理费减免金额(元)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.strataFeeAmt" value="${templateBean.strataFeeAmt}"  onblur="formatVal($(this));" />
				</td>
				<td class="td_title">物业管理费减免起止期</td>
				<td>
					 <input style="width:120px" name="templateBean.strataStartDate" value="${templateBean.strataStartDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate inputBorder" type="text"/>
					 至
					 <input style="width:120px" name="templateBean.strataEndDate" value="${templateBean.strataEndDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate inputBorder" type="text"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">公摊费用减免金额(元)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.publicFeeAmt" value="${templateBean.publicFeeAmt}"  onblur="formatVal($(this));" />
				</td>
				<td class="td_title">公摊费用减免起止期</td>
				<td>
					 <input style="width:120px" name="templateBean.publicStartDate" value="${templateBean.publicStartDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate inputBorder" type="text"/>
					 至
					 <input style="width:120px" name="templateBean.publicEndDate" value="${templateBean.publicEndDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate inputBorder" type="text"/>
				</td>
				</td>
			</tr>
			<tr>
				<td class="td_title">违约金减免金额(元)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.reduceAmt" value="${templateBean.reduceAmt}"  onblur="formatVal($(this));" />
				</td>
				<td class="td_title">对商户补偿金额(元)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.offsetAmt" value="${templateBean.offsetAmt}"  onblur="formatVal($(this));" />
				</td>
				</td>
			</tr>
			<tr>
				<td class="td_title">物业管理费/公摊费用<br/>减免内容</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentDesc">${templateBean.contentDesc}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
