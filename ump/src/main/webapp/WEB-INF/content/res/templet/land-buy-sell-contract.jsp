<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--土地买卖租赁合同审批表-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="65"/>
			<col width="120"/>
			<col width="30%"/>
			<col width="20%"/>
			<col width="30%"/>
			<tr>
				<td class="td_title" colspan="2">标题</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.titleName" value="${templateBean.titleName}" />
				</td>
			</tr>
			<tr>
				<td rowspan="3" class="td_title">合同双方</td>
				<td class="td_title">甲方</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.partyA" value="${templateBean.partyA}"  /></td>
			    <td class="td_title">签约人</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.partyASigning" value="${templateBean.partyASigning}"  /></td>
			</tr>
			<tr>
			    <td class="td_title">乙方</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.partyB" value="${templateBean.partyB}"  /></td>
			    <td class="td_title">签约人</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.partyBSigning" value="${templateBean.partyBSigning}"  /></td>
			</tr>
			<!-- Start Add for part C by zhuxj on 2012.3.31 -->
			<tr>
			    <td class="td_title">丙方</td>
				<td><input class="inputBorder" type="text" name="templateBean.partyC" value="${templateBean.partyC}"  /></td>
			    <td class="td_title">签约人</td>
				<td><input class="inputBorder" type="text" name="templateBean.partyCSigning" value="${templateBean.partyCSigning}"  /></td>
			</tr>
			<!-- End   Add for part C by zhuxj on 2012.3.31 -->
			<tr>
			    <td class="td_title" rowspan="2">选择合作<br>单位方式</td>
			    <td class="td_title"><s:checkbox name="templateBean.bidding"></s:checkbox>招标</td>
			    <td class="td_title">(标书编号)</td>
			    <td colspan="2"><input class="inputBorder" validate="required" type="text" name="templateBean.biddingNum" value="${templateBean.biddingNum}"  /></td>
			</tr>
			<tr>
			    <td class="td_title"><s:checkbox name="templateBean.entrust"></s:checkbox>直接委托</td>
			     <td class="td_title">(标书编号)</td>
			    <td colspan="2">   
			    <input class="inputBorder" validate="required" type="text" name="templateBean.entrustReason" value="${templateBean.entrustReason}"  />
			     </td>
			</tr>
			<tr>
			    <td class="td_title">合同价款(元)</td>
			    <td colspan="4"><input class="inputBorder" onblur="formatVal($(this));" validate="required" name="templateBean.contractPrice" value="${templateBean.contractPrice}"/></td>
			</tr>
			<tr>
			    <td class="td_title">付款方式</td>
			    <td colspan="4"><input class="inputBorder" validate="required" name="templateBean.priceWay" value="${templateBean.priceWay}"/></td>
			</tr>
			<tr>
			    <td rowspan="3" class="td_title">合同主要内容</td>
			    <td colspan="4"><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.mainContents" >${templateBean.mainContents}</textarea></td>
			</tr>
			
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
