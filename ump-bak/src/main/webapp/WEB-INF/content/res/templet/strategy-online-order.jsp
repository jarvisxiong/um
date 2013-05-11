<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
		


<script type="text/javascript" src="${ctx}/resources/js/res/stragy_online_order.js"></script>
	

<style>　
	
	table.cost_mate_table{
		margin:5px 0 20px 0;
	}
	
	table.cost_mate_table tr{
		background-color: #edeff3;
		color: #40a3de;
	}
	 table.cost_mate_table tr td{
		text-align: center;
	}
	table.cost_mate_table tr td input{
		width: 100%;
		text-align: center;
		/*background-color: white;*/
	}
	
	/*
	table.cost_mate_table tr td div{
		text-align: left;
		
		width:99%;
		max-width: 99%;
		overflow: hidden;
		white-space: nowrap;
		
		text-overflow:ellipsis;
	}*/
	
	.scopcol{
		display: none;
	}
	
</style>

<!--战略采购网上下单审批表-->
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>

<c:set var="isSy">false</c:set>
<c:if test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
<c:set var="isSy">true</c:set>
</c:if>


<div align="center" class="billContent">

	<form action="res-approve-info!save.action" method="post" id="templetForm" >
		<%@ include file="template-var.jsp"%>
		<input type="hidden" id="canEditFlg" value="${canEdit}" />
		<input type="hidden" id="hasMateFlg" name="templateBean.hasMateFlg" value="${templateBean.hasMateFlg}" /><%--是否有材料设备明细,1-是 0/null-无 --%>

		<!-- 总记录数 -->
		<s:set var="itemRowCount"><s:property value="templateBean.strategyMatePrices.size()"/></s:set>
		
		<table  class="mainTable">
			<col width="140"/>
			<col/>
			<col width="120"/>
			<col/>
			<c:choose>
				<c:when test="${isSy}">
				<input type="hidden" id="codeType" value="2"/>
				</c:when>
				<c:otherwise>
				<input type="hidden" id="codeType" value="1"/>
				</c:otherwise>
			</c:choose> 
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="3">
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else> resname="projectName" resContLedField="projectName" title="输入项目名称"/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" resname="projectCd" resContLedField="projectCd" value="${templateBean.projectCd}"  />
				</td>
			</tr>   
			<tr>
				<td class="td_title">材料设备类型</td>
				<td>
				    <input readonly="readonly" class="inputBorder" type="text" id="mateModuleName" name="templateBean.mateModuleName" onclick="selectCostMateModule()" style="cursor:pointer;" value="${templateBean.mateModuleName}" title="点击选择材料设备"/>
				    <input type="hidden" id="mateModuleId"  name="templateBean.mateModuleId" value="${templateBean.mateModuleId}"  />
				    <input type="hidden" id="excludePriceIds" />
				</td>
				<td class="td_title">下单日期</td>
				<td align="left"><input onfocus="WdatePicker()" class="inputBorder Wdate" style="width:100px;" validate="required" type="text" name="templateBean.orderDate" value="${templateBean.orderDate}"/></td>
			</tr>
			<tr>
				<td class="td_title">是否商业</td>
				<td class="chkGroup" validate="required">
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<tr>
						<td><s:checkbox id="bizFlg1" name="templateBean.bizFlg1" cssClass="group"></s:checkbox>是</td>
						<td><s:checkbox id="bizFlg0" name="templateBean.bizFlg0" cssClass="group"></s:checkbox>否</td>
					</tr>
					</table>
				</td>
				<td></td>
				<td></td>
			</tr>
			
			<%--商业:是 --%>
			<tr id="tr_biz1" style="display:none">
				<td class="td_title">领用单位(商业)</td>
				<td>
					<input class="inputBorder" id="bizTakeUnitName" validate="required" type="text" name="templateBean.bizTakeUnitName" value="${templateBean.bizTakeUnitName}" title="选择商业公司"/>
					<input type="hidden" id="bizTakeUnitId" name="templateBean.bizTakeUnitId" value="${templateBean.bizTakeUnitId}" />
				</td>
				<td class="td_title">领用单位合同号</td>
				<td>
					<input class="inputBorder" id="bizTakeUnitContNo" validate="required" type="text" name="templateBean.bizTakeUnitContNo" value="${templateBean.bizTakeUnitContNo}" />
				</td>
			</tr>
			
			<%-- 商业:否 --%>
			<tr id="tr_biz0" style="display:none">
				<td class="td_title">领用单位</td>
				<td>
					<input class="inputBorder" id="takeUnitName" validate="required" type="text" name="templateBean.takeUnitName" value="${templateBean.takeUnitName}" title="查找合同乙方或合同编号"/>
					<input type="hidden" id="takeUnitId" name="templateBean.takeUnitId" value="${templateBean.takeUnitId}" />
				</td>
				<td class="td_title">领用单位合同号</td>
				<td>
					<s:if test="#canEdit=='true'">
						<input class="inputBorder" id="takeUnitContNo" validate="required" type="text" name="templateBean.takeUnitContNo" value="${templateBean.takeUnitContNo}" readonly="readonly" title="选择领用单位，自动带出合同"/>
					</s:if>
					<s:else>
						<span class="link" onclick="getCont('${templateBean.takeUnitId}');" title="点击查看合同明细">${templateBean.takeUnitContNo}</span>
					</s:else>
				</td>
			</tr>
			
			
			<tr>
				<td class="td_title">使用区域</td>
				<td><input  class="inputBorder" validate="required" type="text" name="templateBean.useCoverage" value="${templateBean.useCoverage}"/></td>
				<td class="td_title">进场时间</td>
				<td align="left"><input onfocus="WdatePicker()" class="inputBorder Wdate" style="width:100px;" validate="required" type="text" name="templateBean.enterDate" value="${templateBean.enterDate}"/></td>
			</tr>
			<tr>
				<td class="td_title">采购总价(元)</td>
				<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="purchaseTotalAmt" name="templateBean.purchaseTotalAmt" value="${templateBean.purchaseTotalAmt}"  readonly="readonly" title="自动汇总采购总价"/></td>
				<td class="td_title">含战略外总价(元)</td>
				<td>
					<%-- 战略内 --%>
					<input type="hidden" onblur="formatVal($(this));" id="purchaseStrategyTotalAmt" name="templateBean.purchaseStrategyTotalAmt" 
					value="${templateBean.purchaseStrategyTotalAmt}"  title="自动汇总战略内总价"/>
					
					<%-- 战略外 --%>
					<input class="inputBorder"  type="text" onblur="formatVal($(this));" id="strategyOutTotalAmt" name="templateBean.strategyOutTotalAmt" 
					value="${templateBean.strategyOutTotalAmt}"  readonly="readonly" title="自动汇总战略外总价"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">总量</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.contTotalNum" value="${templateBean.contTotalNum}" onkeyup="clearNoNum_1(this);"/></td>
				<td class="td_title">本期止累计领用量<br/>(含本期)</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.toThisTotalNum" value="${templateBean.toThisTotalNum}" onkeyup="clearNoNum_1(this);"/></td>
			</tr>
			<tr>
				<td class="td_title">应提供细项<br/>(请作为附件上传)</td> 
				<td colspan="3" >
					<c:set var="bizEntityId" value="${resApproveInfoId==null?entityTmpId:resApproveInfoId}"/>
					<r:fileUpload canEdit="${canEdit}" bizEntityId="${bizEntityId}" fileId="purchaseTechConfirmFileId" fileValue="${templateBean.purchaseTechConfirmFileId}" title="采购数量及技术参数确认单(须工程副总以上人员签字)" isRequired="true" isRele2Contract="true"/>
					<div id="appendFiles">
						<r:fileUpload canEdit="${canEdit}" bizEntityId="${bizEntityId}" fileId="attachFileId1" fileValue="${templateBean.attachFileId1}" title="供应商与贸易公司订货合同"  isRequired="true" isRele2Contract="true"/>
						<r:fileUpload canEdit="${canEdit}" bizEntityId="${bizEntityId}" fileId="attachFileId2" fileValue="${templateBean.attachFileId2}" title="贸易公司与项目公司订货合同" isRequired="true" isRele2Contract="true"/>
					</div>
				</td>
			</tr>
			<tr>
				<td class="td_title">甲方</td>
				<td colspan="3">
				<input class="inputBorderNoTip" validate="required" type="text" name="templateBean.partA" value="${templateBean.partA}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">乙方</td>
				<td colspan="3">
					<input class="inputBorderNoTip" id="partB" validate="required" type="text" name="templateBean.partB" value="${templateBean.partB}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">订单说明</td>
				<td colspan="3" ><textarea class="inputBorder contentTextArea" name="templateBean.orderDesc" style="height:50px;">${templateBean.orderDesc}</textarea></td>
			</tr> 
			
			<s:if test="#canEdit=='true'">
			<tr id="tr_purc">
				<td class="td_title">下单搜索</td>
				<td colspan="3" align="left">
					<input  type="text" 
								id="quickSearchPriceName" 
								name="quickSearchPriceName" 
								class="inputBorder" 
								style="width: 200px;padding:2px 5px;"/>
								 
						<span id="quickSearchPriceTip" style="color: red;"></span>
						(按名称、规格或型号)
				</td>
			</tr>
			</s:if>
			<s:else>
				<td class="td_title" style="text-align: center;" colspan="4">采购数量及技术参数</td>
			</s:else>
			
			<tr>
				<td colspan="4" style="padding:5px 0;">
					<div class="strageTables">
						<%-- 战略内 --%>
						<div align="left" style="padding-left:5px;" id="strategy_in_span">
						战略内 (总价：<span id="strageInAmt">${templateBean.purchaseStrategyTotalAmt}</span>元)
						<%-- 提示 --%>
						<span id="inputTip">${templateBean.priceTip}</span>
						<input type="hidden" name="templateBean.priceTip" value="${templateBean.priceTip}" />
						<%-- 失败提示 --%>
						&nbsp;<span id="failureTip" style="color:red;float:right;margin-right:10px;"></span>
						</div>
					  	<div id="strategyInDiv">
							<%@ include file="strategy-online-order-in.jsp"%>
					  	</div>
					  	
					  	<div>&nbsp;</div>
					  	
					  	
						<%-- 战略外 --%>
						<div style="align:right">
							<s:if test="#canEdit=='true'">
								<input id="btnExtAppend" type="button" class="fRight btn_green" style="width:80px" onclick="insertRowOut();" value="增加战略外"/>
							</s:if> 
						</div>
							
						<div align="left" style="padding-left:5px;" id="strategy_out_span">
						战略外 (总价：<span id="strageOutAmt">${templateBean.strategyOutTotalAmt}</span>元)
						</div>
						<div id="strategyOutDiv">
							<%@ include file="strategy-online-order-out.jsp"%>
						</div>
					</div>
				</td>
			</tr>
			
			<s:if test="statusCd==2">
			<tr>
				<%--
				<td class="td_title">合同编号</td>
				<td>
					<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' validate="required"</s:if> type="text" id="contractNo" name="templateBean.contractNo" value="${templateBean.contractNo}" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> onblur="contNoValidate(this);"</s:if>/>
				</td>
				--%>
				<td class="td_title">审定价(元)</td>
				<td>
					<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported">  edit='true' validate="required" </s:if>  type="text" id="contractPrice" name="templateBean.contractPrice" resname="contractPrice" resContLedField="contractPrice" value="${templateBean.contractPrice}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title" align="right">
					<s:if test="!isImported"></s:if>
					<s:if test="true">
						<div id="btn_importContLib" class="btn_green" title="把当前定标审批表导入到合同文本库" onclick="doImportContLib('${resApproveInfoId}');" style="width:65px;">新建合同</div>
				    </s:if>
			   	</td>
				<td>
			        <s:if test="!isImported">
				 	请到合同文本库新建合同，系统会自动生成合同台账
				 	</s:if>
				 	 <s:else>
				 		 <span>合同台账编号：${templateBean.contractNo}</span>
				 	 </s:else>
					<input class="inputBorder"  type="hidden" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true'  onblur="contNoValidate(this);"</s:if>  id="contractNo" name="templateBean.contractNo" value="${templateBean.contractNo}"/>
			  </td>	
			</tr> 
			</s:if>
		</table>
			
		<%@ include file="template-approver.jsp"%>
	</form>
</div>

<%@ include file="template-footer.jsp"%>

<%--标准合同库引入方法 --%>
<%@include file="bid-contract-import-base.jsp"%>

<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>

<script type="text/javascript">


//审批状态
var statusCd = ${statusCd}; //statusCd=1审批中

//进入页面，已存在的材料设备数
var itemRowCount = ${itemRowCount};   

$(function(){

	//注册事件
	initBindAll();
	
	//快速搜索项目,支持两种: 1-地产 2-商业
	$("#projectName").quickSearch(
		"${ctx}/cont/cont-project-code!quickSearch.action",
		['projectName'],
		{projectCd:'projectCd',projectName:'projectName'},
		{codeType:$('#codeType').val()}
	);

	//注册"领用单位"(商业)
	$("#bizTakeUnitName").quickSearch(
		"${ctx}/cont/cont-project-code!quickSearch.action",
		['projectName'],
		{projectCd:'bizTakeUnitId',projectName:'bizTakeUnitName'},
		{codeType: '2'}//类型： 2-商业
	);
	//注册"领用单位"
	$("#takeUnitName").quickSearch(
		"${ctx}/cont/cont-ledger!quickSearchPartB.action",
		['partB','contNo'],
		{partB:'takeUnitName',contLedgerId:'takeUnitId',contNo:'takeUnitContNo'},
		{codeType:'1'}//类型： 1-地产
	);

	//注册"关键字/规格/型号"
	$('#quickSearchPriceName').quickSearch(
		"${ctx}/cost/cost-mate-price!quickSearch.action",
		['groupName','groupName2','groupName3','groupName4','materialName','specName','modelName'],//'groupName3','groupName2',
		{},
		'',
		function(jDomTr){ 
			var mateId = jDomTr.attr('costMateId');
			var priceId = jDomTr.attr('costMatePriceId');
			insertRow(mateId, priceId);
		},
		{costMateModuleId: 'mateModuleId',excludePriceIds: 'excludePriceIds'}//将控件"mateModuleId"的值，命名为"costMateModuleId"传给后台
	);

	<s:if test="#canEdit=='true'">
	//注册事件
	$('#bizFlg1').bind('click',processBizFlg);
	$('#bizFlg0').bind('click',processBizFlg);
	</s:if>
	
	processBizFlg();
}); 

function processBizFlg(){

	var biz1 = $('#bizFlg1').attr("checked");
	var biz0 =  $('#bizFlg0').attr("checked");
	if((!biz1) && (!biz0)){
		$('#bizFlg0').attr('checked',"checked");
		$('#tr_biz0').removeAttr('style');//显示
	}else{
		//若是商业,则可以填写"采购总价(元)","含战略外总价(元)"
		if(biz1){
			$('#tr_biz1').removeAttr('style');
		}else{
			//$('#tr_biz1 input').val('')
			$('#tr_biz1').attr('style','display:none');
		}
		
		if( biz0){
			$('#tr_biz0').removeAttr('style');
		}else{
			//$('#tr_biz0 input').val('')
			$('#tr_biz0').attr('style','display:none');
		}
	}
}
</script>