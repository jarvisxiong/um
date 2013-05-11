<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
	开业后业态面积调整审批表(eg:开业后业态面积调整审批表)	 
--%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">

	
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="120px">
			<col >
			<col >
			<col >
			<col >
			<col >
			<tr>
				<td class="td_title" rowspan="2">条件选择</td>
				<td colspan="5" validate="required"  class="chkGroup">
					<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<col width="150">
					<tr>
						<td><s:checkbox name="templateBean.areaRate1" cssClass="group"></s:checkbox>面积占比≤10%</td>
						<td><s:checkbox name="templateBean.areaRate2" cssClass="group"></s:checkbox>10%&lt;面积占比≤20%</td>
						<td><s:checkbox name="templateBean.areaRate3" cssClass="group"></s:checkbox>面积占比&gt;20%</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="5" validate="required"  class="chkGroup">
					<table class="tb_checkbox">
					<col width="300">
					<col width="300">
					<tr>
						<td><s:checkbox name="templateBean.rentMoney1" cssClass="group"></s:checkbox>租金收益高于标准水平</td>
						<td><s:checkbox name="templateBean.rentMoney2" cssClass="group"></s:checkbox>租金收益低于标准水平</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="border-top:0 none;" class="td_title">文件标题</td>
				<td style="border-top: none;" colspan="5">
					关于
					<input class="inputBorder" style="width:300px;" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" />
					<input type="hidden" name="templateBean.projectCd" value="${templateBean.projectCd}" />
					业态面积调整的审批
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">密级</td>
				<td style="border-top:0 none;" class="chkGroup" align="left"  validate="required" colspan="5">
					<s:checkbox name="templateBean.securityCd1"  cssClass="group"></s:checkbox>绝密
					<s:checkbox name="templateBean.securityCd2"  cssClass="group"></s:checkbox>机密
					<s:checkbox name="templateBean.securityCd3"  cssClass="group"></s:checkbox>保密
					<s:checkbox name="templateBean.securityCd4"  cssClass="group"></s:checkbox>内部公开
				</td> 
			</tr>
			<tr>
				<td style="border-top:0 none;" class="td_title">店面类别</td>
				<td style="border-top:0 none;" class="chkGroup" align="left"  validate="required" colspan="5">
					<s:checkbox name="templateBean.storeTypeCd1"  cssClass="group"></s:checkbox>主力店（百货、超市）
					<s:checkbox name="templateBean.storeTypeCd2"  cssClass="group"></s:checkbox>次主力店
					<s:checkbox name="templateBean.storeTypeCd3"  cssClass="group"></s:checkbox>品牌店
					<s:checkbox name="templateBean.storeTypeCd4"  cssClass="group"></s:checkbox>其他
					<input class="inputBorder" style="width:150px;" type="text" name="templateBean.storeTypeCd4Desc" value="${templateBean.storeTypeCd4Desc}" />
				</td> 
			</tr>
			<tr>
				<td style="border-top:0 none;" class="td_title">紧急</td>
				<td style="border-top:0 none;" class="chkGroup" align="left" colspan="5">
					<s:checkbox name="templateBean.urgencyCd1"  cssClass="group"></s:checkbox>是
					<s:checkbox name="templateBean.urgencyCd2"  cssClass="group"></s:checkbox>否
				</td> 
			</tr>
			<tr>
				<td style="border-top:0 none;" class="td_title">中心</td>
				<td style="border-top: none;" colspan="5">
					<input id="centerName" validate="required" type="text" name="templateBean.centerName" value="${templateBean.centerName}"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" name="templateBean.centerCd" value="${templateBean.centerCd}" />
				</td> 
			</tr>
			<tr>
				<td style="border-top:0 none;" class="td_title">业态调整说明</td>
				<td style="border-top: none;" colspan="5">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.adjustDesc">${templateBean.adjustDesc}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
