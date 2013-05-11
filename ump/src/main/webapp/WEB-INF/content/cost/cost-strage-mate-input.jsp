<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>战略材料库明细</title>
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/res/resCss.css"/>
	<script type="text/javascript" src="${ctx}/resources/js/res/resSingleUpload.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/res/resApprove_0220.js"></script>
</head>
<body style="background-color: white;">

<div title="title_bar">
	<div class="fRight">
		<input type="button" class="btn_new btn_full_new" onclick="window.open(location.href)" value="全屏" />
		<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href" value="刷新" />
	</div>
</div>
<div class="main_input_content" id="templet" style="margin-top: 10px;margin-bottom: 10px;">
	<div align="center">
		<span class="subject"><s:property value="authTypeName"/></span>
	</div>
	<div align="center" class="billContent">
		<table  class="mainTable">
			<col width="180"/>
			<col/>
			<col width="120"/>
			<col/>
			<tr>
				<td class="td_title">项目名称：</td>
				<td align="left" colspan="3"><s:property value="projectName"/></td>
			</tr>
			<tr>
				<td class="td_title">材料设备名称：</td>
				<td align="left" colspan="3"><s:property value="mateName"/></td>
			</tr>
			<tr>
				<td class="td_title">领用单位：</td>
				<td align="left"><s:property value="takeUnitName"/></td>
				<td class="td_title">领用单位合同号：</td>
				<td align="left"><s:property value="takeUnitContNo"/></td>
			</tr>
			<tr>
				<td class="td_title">使用区域：</td>
				<td align="left"><s:property value="useAeraDesc"/></td>
				<td class="td_title">进场时间：</td>
				<td align="left"><s:property value="enterDate"/></td>
			</tr>
			<tr>
				<td class="td_title">采购总价(元)：</td>
				<td align="left"><s:property value="buyTotalAmt"/></td>
				<td class="td_title">含战略外总价(元)：</td>
				<td align="left"><s:property value="outStragePrice"/></td>
			</tr>	
			<tr>
				<td class="td_title">总量：</td>
				<td align="left"><s:property value="contTotalNum"/></td>
				<td class="td_title">本期止累计领用量(含本期)：</td>
				<td align="left"><s:property value="toThisTotalNum"/></td>
			</tr>
			<tr>
				<td class="td_title">应提供细项(请作为附件上传)：</td>
				<td style="height:40px;text-align: center;" validate="required"  value="${purchaseTechConfirmFileId}">采购数量及技术参数确认单(须工程副总以上人员签字)</td>
				<td colspan="2" align="left">
					<div id="show_purchaseTechConfirmFileId"></div>
					<script type="text/javascript">
					showUploadedFile('${purchaseTechConfirmFileId}','purchaseTechConfirmFileId','false');
					</script>
				</td>
			</tr>
			
			<tr>
				<td class="td_title">甲方：</td>
				<td colspan="3" align="left"><s:property value="parta"/></td>
			</tr>
			<tr>
				<td class="td_title">乙方：</td>
				<td colspan="3" align="left"><s:property value="partb"/></td>
			</tr>
			<tr>
				<td class="td_title">订单说明：</td>
				<td colspan="3" align="left"><s:property value="orderDesc"/></td>
			</tr>
			<tr>			
				<td class="td_title">采购数量及技术参数：</td>
				<td colspan="3">
				  <table id="tbConItem1" style="margin-top: 5px;margin-bottom: 5px;" width="100%">
					<tr>
						<td align="center" style="width: 30px;">&nbsp;</td>
						<td align="center" style="width: 30px;">序号</td>
						<td align="center" style="width: 100px;">规格</td>
						<td align="center" style="width: 100px;">型号</td>
						<td align="center" style="width: 100px;">数量</td>
						<td align="center" style="width: 100px;">总价(元)</td>
					</tr>
					<s:iterator value="costStrageMateDetails" var="arrItem" status="s1">
					<tr>
						<td>
							<s:if test="arrItem.costMatePriceId == null || arrItem.costMatePriceId == ''">
								战略外${arrItem.costMatePriceId }
							</s:if>
							<s:else>
								战略内${arrItem.costMatePriceId }
							</s:else>
						</td>
						<td class="tdGroupNodes" style="text-align:center;">
						   <s:property value="#s1.index+1" />
						</td>		    
					    <td class="tdGroupNodes" style="text-align:center;">
					   	 	${arrItem.specName}
					    </td>
						<td class="tdGroupNodes" style="text-align:center;">
							${arrItem.modelName}
						</td>
					 	<td style="text-align:center;">
					 		${arrItem.orderNum}
					 	</td>
					 	<td style="text-align:center;">
					 		${arrItem.orderNum*arrItem.price}
					 	</td>
					</tr>
					</s:iterator>
				</table>
				</td>
			</tr>	
			<tr>
				<td class="td_title">合同编号：</td>
				<td colspan="3" align="left"><s:property value="contNo"/></td>
			</tr> 
		</table>
	</div>
</div>
<script type="text/javascript">

function getMateCol(){
	var mateId = $("#materialId").val();
	$("#priceTr").show();
	$("#priceListTr").show();
	$("#purchaseTotalAmt").val("");
	$("#priceName").val(""); 
	$("#costMatePriceId").val(""); 
	$("#allMatePriceId").val("");
	$("#costPriceList").html("");
	//得到设备属性列名称
	var url = "${ctx}/cost/cost-mate-price!getMateCol.action";
	var data = {costMateId : mateId};
	$.post(url,data,function(result) {
		if(result) {
			$("#headNames").val(result);
		}else{
			$("#headNames").val('');
		}
	});
}
</script>
</body>
</html>
