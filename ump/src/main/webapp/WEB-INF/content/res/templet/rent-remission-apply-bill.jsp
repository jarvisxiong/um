<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
	租金减免审批表(eg:租金减免审批表) 
--%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">

	
	
	<!-- 
	注：1、此单审批完成后，应向宝龙商业财务部、宝龙地产财务管理中心备案。
	 -->
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="120px">
			<col />
			<col width="120px">
			<col />
			<tr>
				<td style="border-top:0 none;" class="td_title">项目名称</td>
				<td style="border-top: none;" colspan="3">
					<input id="projectName" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" name="templateBean.projectCd" value="${templateBean.projectCd}" />
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">商户名称</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.storeName" value="${templateBean.storeName}" />
				</td>
				<td style="border-top:0 none;" class="td_title">联系人</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.storeLinkman" value="${templateBean.storeLinkman}" />
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">联系地址</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.storeLinkAddr" value="${templateBean.storeLinkAddr}" />
				</td>
				<td style="border-top:0 none;" class="td_title">联系电话</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.storeLinkPhone" value="${templateBean.storeLinkPhone}" />
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">商户分类</td>
				<td style="border-top:0 none;" class="chkGroup" align="left"  validate="required" colspan="3">
					<s:checkbox name="templateBean.storeTypeCd1"  cssClass="group"></s:checkbox>主力店
					<s:checkbox name="templateBean.storeTypeCd2"  cssClass="group"></s:checkbox>次主力店
					<s:checkbox name="templateBean.storeTypeCd3"  cssClass="group"></s:checkbox>中型店
					<s:checkbox name="templateBean.storeTypeCd4"  cssClass="group"></s:checkbox>品牌店
				</td> 
			</tr>
			<tr>
				<td style="border-top:0 none;" class="td_title">租金减免金额</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.remissionAmt" value="${templateBean.remissionAmt}"  onblur="formatVal($(this));" />
				</td>
				<td style="border-top:0 none;" class="td_title">租金减免 起止期</td>
				<td style="border-top: none;">
					<%--
					<input class="inputBorder" validate="required" type="text" name="templateBean.dateRankDesc" value="${templateBean.dateRankDesc}" />
					 --%>
					 <input style="width:100px" name="templateBean.startDate" value="${templateBean.startDate}" onfocus="WdatePicker({dateFmt:'yyyy年M月dd日'})" class="Wdate inputBorder" type="text"/>
					 至
					 <input style="width:100px" name="templateBean.endDate" value="${templateBean.endDate}" onfocus="WdatePicker({dateFmt:'yyyy年M月dd日'})" class="Wdate inputBorder" type="text"/>
				</td>
			</tr>
			<tr>
				<td style="border-top:0 none;" class="td_title">租金减免内容</td>
				<td style="border-top:0 none;" colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.rentDesc">${templateBean.rentDesc}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
