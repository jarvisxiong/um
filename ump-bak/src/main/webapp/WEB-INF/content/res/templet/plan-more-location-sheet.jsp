<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--计划内多经点位审批表--%>
<%@ include file="template-header.jsp"%>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>

<%@page import="com.hhz.ump.util.DictContants"%>
<div align="center" class="billContent"><s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
</s:set>

<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill"><%@ include file="template-var.jsp"%>
         <%--不自动设置项目编号 --%>
	     <input type="hidden" id="isAutosetProject" value="1"/>	
		 <%--合同库引用 start --%>
		 <%@ include file="bid-contract-base.jsp"%>
<s:if test="statusCd!=0">
<%@ include file="bid-contract-mark.jsp" %>
</s:if>
		<%--合同库引用 end --%>
<table class="mainTable">
	<col style="width:40px"/>
	<col />
	<col style="width:80px" />
	<col />
	<col />
	<col />
	<tr>
		<td class="td_title require"></td>
		<td colspan="5" class="chkGroup" align="center" validate="required">
		<table class="tb_checkbox">
			<col width="150">
			<col width="150">
			<tr>
				<td><s:checkbox name="templateBean.outFlag" id="outFlag" cssClass="group"></s:checkbox>计划外</td>
				<td><s:checkbox name="templateBean.inFlag" id="inFlag" cssClass="group"></s:checkbox>计划内</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td class="td_title" style="text-align: center;"  rowspan="2">项目</td>
		<td style="text-align: left" colspan="2" rowspan="2">
		<input validate="required" type="text" style="height:20px;line-height: 20px;" name="templateBean.projectName" value="${templateBean.projectName}"
			id="projectName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if >
			<s:else> class="inputBorderNoTip" </s:else> /> 
			<input type="hidden" id="projectCd" name="templateBean.projectCd" value="${templateBean.projectCd}" />
		</td>
		<td class="td_title" style="text-align: center;"  rowspan="2" >审请商户</td>
		<td colspan="2" class="chkGroup" align="center"  validate="required">
					<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
					<td><s:checkbox name="templateBean.inCustomer" id="inCustomer" cssClass="group"></s:checkbox>场内商户</td>
					<td><s:checkbox name="templateBean.outCustomer" id="outCustomer" cssClass="group"></s:checkbox>场外</td>
					</tr>
					</table>
		</td>
	</tr>	
	<tr>		
		<td colspan="2" >
		名称：
		<input validate="required" class="inputBorder" type="text"
			id="customerName" name="templateBean.customerName" value="${templateBean.customerName}"   style="width:140px"/>
		</td>
	</tr>
 	<tr>
		<td class="td_title" style="text-align: center;">区域</td>
		<td >
			<input class="inputBorder" validate="required" id="area" name="templateBean.area" value="${templateBean.area}" style="width:100px"></input>
		</td>
		<td class="td_title" style="text-align: center;">点位类型</td>
		<td style="text-align: left">
			<input class="inputBorder" validate="required" id="locationType" name="templateBean.locationType"
			value="${templateBean.locationType}" style="width:100px"></input>
		</td>
		<td class="td_title" style="text-align: center;">点位编号</td>
		<td style="text-align: left">
			<input class="inputBorder" validate="required" id="locationNum" name="templateBean.locationNum"
			value="${templateBean.locationNum}" style="width:100px"></input>
		</td>
	</tr>

	<tr id="trHeader">
		<td align="center">序号</td>
		<td align="center" colspan="2">科目</td>
		<td align="center">标准</td>
		<td align="center">审批条件</td>
		<td align="center">批注</td>

	</tr>
	<tr>
		<td class="td_title" style="text-align: center;">1</td>
		<td colspan="2" style="text-align: left">经营业态</td>


		<td style="text-align: left"><input class="inputBorder" validate="required" id="manageType1" name="templateBean.manageType1"
			value="${templateBean.manageType1}"  style="width:100px"></input></td>

		<td style="text-align: left"><input class="inputBorder" validate="required" id="manageType2" name="templateBean.manageType2"
			value="${templateBean.manageType2}"  style="width:100px"></input></td>
		<td style="text-align: left"><input class="inputBorder"  id="manageType3" name="templateBean.manageType3"
			value="${templateBean.manageType3}"  style="width:100px"></input></td>
	</tr>
	<tr>
		<td class="td_title" style="text-align: center;">2</td>
		<td colspan="2" style="text-align: left">租赁面积</td>
		<td style="text-align: left"><input class="inputBorder" validate="required" id="rentType1" name="templateBean.rentType1"
			value="${templateBean.rentType1}"  style="width:100px"></input></td>

		<td style="text-align: left"><input class="inputBorder" validate="required" id="rentType2" name="templateBean.rentType2"
			value="${templateBean.rentType2}"  style="width:100px"></input></td>
		<td style="text-align: left"><input class="inputBorder"  id="rentType3" name="templateBean.rentType3"
			value="${templateBean.rentType3}"  style="width:100px"></input></td>
	</tr>
	<tr>
		<td class="td_title" style="text-align: center;">3</td>
		<td colspan="2" style="text-align: left">装修标准</td>


		<td style="text-align: left"><input class="inputBorder" validate="required" id="fillerType1" name="templateBean.fillerType1"
			value="${templateBean.fillerType1}"  style="width:100px"></input></td>

		<td style="text-align: left"><input class="inputBorder" validate="required" id="fillerType2" name="templateBean.fillerType2"
			value="${templateBean.fillerType2}"  style="width:100px"></input></td>
		<td style="text-align: left"><input class="inputBorder"  id="fillerType3" name="templateBean.fillerType3"
			value="${templateBean.fillerType3}"  style="width:100px"></input></td>
	</tr>
	<tr>
		<td class="td_title" style="text-align: center;">4</td>
		<td colspan="2" style="text-align: left">合约期限</td>
		<td style="text-align: left"><input class="inputBorder" validate="required" id="contDateType1" name="templateBean.contDateType1"
			value="${templateBean.contDateType1}"  style="width:100px"></input></td>

		<td style="text-align: left"><input class="inputBorder" validate="required" id="contDateType3" name="templateBean.contDateType2"
			value="${templateBean.contDateType2}"  style="width:100px"></input></td>
		<td style="text-align: left"><input class="inputBorder"  id="contDateType3" name="templateBean.contDateType3"
			value="${templateBean.contDateType3}"  style="width:100px"></input></td>
	</tr>
	<tr>
		<td class="td_title" style="text-align: center;">5</td>
		<td colspan="2" style="text-align: left">免租期</td>
		<td style="text-align: left"><input class="inputBorder" validate="required" id="rentFreePeriodType1" name="templateBean.rentFreePeriodType1"
			value="${templateBean.rentFreePeriodType1}"  style="width:100px"></input></td>

		<td style="text-align: left"><input class="inputBorder" validate="required" id="rentFreePeriodType2" name="templateBean.rentFreePeriodType2"
			value="${templateBean.rentFreePeriodType2}"  style="width:100px"></input></td>
		<td style="text-align: left"><input class="inputBorder"  id="rentFreePeriodType3" name="templateBean.rentFreePeriodType3"
			value="${templateBean.rentFreePeriodType3}"  style="width:100px"></input></td>
	</tr>
	<tr>
		<td class="td_title" style="text-align: center;">5</td>
		<td colspan="2" style="text-align: left">租金标准（元/月）</td>
		<td style="text-align: left"><input class="inputBorder" validate="required" id="rentStandrdType1" name="templateBean.rentStandrdType1"
			value="${templateBean.rentStandrdType1}"  style="width:100px"></input></td>

		<td style="text-align: left"><input class="inputBorder" validate="required" id="rentStandrdType2" name="templateBean.rentStandrdType2"
			value="${templateBean.rentStandrdType2}"  style="width:100px"></input></td>
		<td style="text-align: left"><input class="inputBorder"  id="rentStandrdType3" name="templateBean.rentStandrdType3"
			value="${templateBean.rentStandrdType3}"  style="width:100px"></input></td>
	</tr>
	<tr>
		<td class="td_title" style="text-align: center;">6</td>
		<td colspan="2" style="text-align: left">租赁期总收益（元）</td>
		<td style="text-align: left"><input class="inputBorder" validate="required" id="rentIncomeType1" name="templateBean.rentIncomeType1"
			value="${templateBean.rentIncomeType1}"  style="width:100px"></input></td>

		<td style="text-align: left"><input class="inputBorder" validate="required" id="rentIncomeType2" name="templateBean.rentIncomeType2"
			value="${templateBean.rentIncomeType2}"  style="width:100px"></input></td>
		<td style="text-align: left"><input class="inputBorder"  id="rentIncomeType3" name="templateBean.rentIncomeType3"
			value="${templateBean.rentIncomeType3}"  style="width:100px"></input></td>
	</tr>
	<tr>
		<td class="td_title" style="text-align: center;">7</td>
		<td colspan="2" style="text-align: left">付款方式</td>
		<td style="text-align: left"><input class="inputBorder" validate="required" id="methodPaymentType1" name="templateBean.methodPaymentType1"
			value="${templateBean.methodPaymentType1}"  style="width:100px"></input></td>

		<td style="text-align: left"><input class="inputBorder" validate="required" id="methodPaymentType2" name="templateBean.methodPaymentType2"
			value="${templateBean.methodPaymentType2}"  style="width:100px"></input></td>
		<td style="text-align: left"><input class="inputBorder"  id="methodPaymentType3" name="templateBean.methodPaymentType3"
			value="${templateBean.methodPaymentType3}"  style="width:100px"></input></td>
	</tr>
	<tr>
		<td class="td_title" style="text-align: center;">9</td>
		<td colspan="2" style="text-align: left">经营保证金</td>
		<td style="text-align: left"><input class="inputBorder" validate="required" id="operatingMarginType1" name="templateBean.operatingMarginType1"
			value="${templateBean.operatingMarginType1}"  style="width:100px"></input></td>
		<td style="text-align: left"><input class="inputBorder" validate="required" id="operatingMarginType2" name="templateBean.operatingMarginType2"
			value="${templateBean.operatingMarginType2}"  style="width:100px"></input></td>
		<td style="text-align: left"><input class="inputBorder"  id="operatingMarginType3" name="templateBean.operatingMarginType3"
			value="${templateBean.operatingMarginType3}"  style="width:100px"></input></td>
	</tr>

	<tr>
		<td class="td_title" style="text-align: center;">10</td>
		<td colspan="2" style="text-align: left">交付时间</td>

		<td style="text-align: left"><input class="inputBorder" validate="required" id="handOverDateType1" name="templateBean.handOverDateType1"
			value="${templateBean.handOverDateType1}"  style="width:100px"></input></td>

		<td style="text-align: left"><input class="inputBorder" validate="required" id="handOverDateType2" name="templateBean.handOverDateType2"
			value="${templateBean.handOverDateType2}"  style="width:100px"></input></td>
		<td style="text-align: left"><input class="inputBorder"  id="handOverDateType3" name="templateBean.handOverDateType3"
			value="${templateBean.handOverDateType3}"  style="width:100px"></input></td>
	</tr>
	<tr>
		<td class="td_title" style="text-align: center;">11</td>
		<td colspan="2" style="text-align: left">装修期</td>
		<td style="text-align: left"><input class="inputBorder" validate="required" id="fillerPeriodType1" name="templateBean.fillerPeriodType1"
			value="${templateBean.fillerPeriodType1}"  style="width:100px"></input></td>
		<td style="text-align: left"><input class="inputBorder" validate="required" id="fillerPeriodType2" name="templateBean.fillerPeriodType2"
			value="${templateBean.fillerPeriodType2}"  style="width:100px"></input></td>
		<td style="text-align: left"><input class="inputBorder"  id="fillerPeriodType3" name="templateBean.fillerPeriodType3"
			value="${templateBean.fillerPeriodType3}"  style="width:100px"></input></td>
	</tr>
	<tr>
		<td class="td_title" style="text-align: center;">12</td>
		<td colspan="2" style="text-align: left">其它条件</td>
		<td colspan="3" style="text-align: left"><textarea class="inputBorder contentTextArea"  name="templateBean.otherCondType">${templateBean.otherCondType}</textarea>
		</td>
	</tr>
	

</table>
<script>
/*
/**
 * 合同文本库选择回调函数
 *//*
function callChooseContlibFun(flag){
	//移除标记合同功能
	$("#doMark").remove();	
	if(flag == "true"){
             $("tr[otype=attach]").hide();
		}else{
			 $("tr[otype=attach]").show();
		}
	
}*/
//是否回执标准库选择回调函数
var isCallChooseCont=true;
//是否有选择合同库

if($("#contlib").attr("checked") == true || $("#contlib").attr("checked") == "checked"){
	userCont('true');
}else{
	userCont('false');
}
</script>


<%@ include file="template-approver.jsp"%></form>

</div>

<%@ include file="template-footer.jsp"%>


