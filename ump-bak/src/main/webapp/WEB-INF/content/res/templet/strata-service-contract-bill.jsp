<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
	物业服务合同审批表(eg:  物业服务合同审批表（主办店/次主力店）)	
--%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">

	
	<!-- 
	1、此单审批完成后，应向宝龙商业财务部、宝龙商业招商中心、宝龙商业大客户中心备案。
	 -->
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="120px">
			<col width="60px"/>
			<col />
			<col width="60px"/>
			<col />
			<tr>
				<td style="border-top:0 none;" class="td_title" rowspan="3">合同双方</td>
				<td style="border-top:0 none;" class="td_title">甲方</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.companyName1" value="${templateBean.companyName1}" />
					<input type="hidden" name="templateBean.companyCd1" value="${templateBean.companyCd1}" />
				</td>
				<td style="border-top:0 none;" class="td_title">签约人</td>
				<td style="border-top: none;">
					<input id="signerName1" name="templateBean.signerName1" value="${templateBean.signerName1}" validate="required" type="text" />
					<input type="hidden" name="templateBean.signerCd1" value="${templateBean.signerCd1}" />
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">乙方</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.companyName2" value="${templateBean.companyName2}" />
					<input type="hidden" name="templateBean.companyCd2" value="${templateBean.companyCd2}" />
				</td>
				<td style="border-top:0 none;" class="td_title">签约人</td>
				<td style="border-top: none;">
					<input id="signerName2" name="templateBean.signerName2" value="${templateBean.signerName2}" validate="required" type="text" />
					<input type="hidden" name="templateBean.signerCd2" value="${templateBean.signerCd2}" />
				</td>
			</tr> 
			<!-- Start Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td style="border-top:0 none;" class="td_title">丙方</td>
				<td style="border-top: none;">
					<input class="inputBorder" type="text" name="templateBean.companyName3" value="${templateBean.companyName3}" />
					<input type="hidden" name="templateBean.companyCd3" value="${templateBean.companyCd3}" />
				</td>
				<td style="border-top:0 none;" class="td_title">签约人</td>
				<td style="border-top: none;">
					<input id="signerName3" name="templateBean.signerName3" value="${templateBean.signerName3}" type="text" />
					<input type="hidden" name="templateBean.signerCd3" value="${templateBean.signerCd3}" />
				</td>
			</tr>
			<!-- End   Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td style="border-top:0 none;" class="td_title" rowspan="2">物业管理费标准</td>
				<td style="border-top:0 none;" colspan="4">
					注：（文字说明价格依据）
				</td>
			</tr>
			<tr>
				<td style="border-top:0 none;" colspan="4">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentDesc">${templateBean.contentDesc}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
