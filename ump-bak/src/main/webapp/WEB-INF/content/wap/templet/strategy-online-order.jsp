<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--战略采购订单审批表-->
<s:set var="canEdit">false</s:set>
<div class="billContent">
		<%@ include file="template-var.jsp"%>
		<%--
		<div class="div_row">
			<span class="wap_title">标题:</span>
			<span class="wap_value">${templateBean.titleName}</span>
		</div>
		 --%>
		
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
		</div>
		
		
		<div class="div_row">
			<span class="wap_title">材料设备类型:</span>
			<span class="wap_value">${templateBean.mateModuleName}</span>
		</div>
		
		<div class="div_row">
			<span class="wap_title">下单日期:</span>
			<span class="wap_value">${templateBean.orderDate}</span>
		</div>
		
		<div class="div_row">
			<span class="wap_title">是否商业:</span>
			<span class="wap_value"><s:if test="templateBean.bizFlg1 == 'true'">是</s:if><s:else>否</s:else></span>
		</div>
		
		<div class="div_row">
			<span class="wap_title">领用单位:</span>
			<span class="wap_value"><s:if test="templateBean.bizFlg1 == 'true'">${templateBean.bizTakeUnitName}</s:if><s:else>${templateBean.takeUnitName}</s:else></span>
		</div>
		<div class="div_row">
			<span class="wap_title">领用单位合同号:</span>
			<span class="wap_value"><s:if test="templateBean.bizFlg1 == 'true'">${templateBean.bizTakeUnitContNo}</s:if><s:else>${templateBean.takeUnitContNo}</s:else></span>
		</div>
		
		<div class="div_row">
			<span class="wap_title">使用区域:</span>
			<span class="wap_value">${templateBean.useCoverage}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">进场时间:</span>
			<span class="wap_value">${templateBean.enterDate}</span>
		</div>
		
		
		<div class="div_row">
			<span class="wap_title">采购总价(元):</span>
			<span class="wap_value">${templateBean.purchaseTotalAmt}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">含战略外总价(元):</span>
			<span class="wap_value">${templateBean.strategyOutTotalAmt}</span>
		</div>
		
		
		<div class="div_row">
			<span class="wap_title">总量:</span>
			<span class="wap_value">${templateBean.contTotalNum}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">本期止累计领用量(含本期)</span>
			<span class="wap_value">${templateBean.toThisTotalNum}</span>
		</div>
		 
		<div class="div_label">
			<span class="wap_label">【应提供细项】</span>
			<div class="div_row">
				<span class="wap_title">采购数量及技术参数确认单(须工程副总以上人员签字):</span>
				<div id="show_purchaseTechConfirmFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.purchaseTechConfirmFileId}','purchaseTechConfirmFileId','${canEdit}');
				</script>
			</div>
			<s:if test="templateBean.mateModuleId == null || templateBean.mateModuleId =='' ">
			<div class="div_row">
				<span class="wap_title">供应商与贸易公司订货合同:</span>
				<div id="show_attachFileId1"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.attachFileId1}','attachFileId1','${canEdit}');
				</script>
			</div>
			<div class="div_row">
				<span class="wap_title">贸易公司与项目公司订货合同:</span>
				<div id="show_attachFileId2"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.attachFileId2}','attachFileId2','${canEdit}');
				</script>
			</div>
			</s:if>
		</div>
		
		<div class="div_row">
			<span class="wap_title">甲方:</span>
			<span class="wap_value">${templateBean.partA}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">乙方:</span>
			<span class="wap_value">${templateBean.partB}</span>
		</div>   
		<div class="div_row">
			<span class="wap_title">订单说明:</span>
			<span class="wap_value">${templateBean.orderDesc}</span>
		</div>   
		
		<div class=div_table_row>
			<div class="div_row">
				<span class="wap_label">【采购数量及技术参数】</span>
			</div> 
		
			
			<%--针对电缆，插入铜价 --%>
			<s:if test="templateBean.mateModuleName == '电缆'">
				<c:set var="insertDesc" value="铜价(元)"/>
			</s:if>
			<s:else>
				<c:set var="insertDesc" value=""/>
			</s:else>
			
			
			<div class="div_row">
			<span class="wap_title">1.战略内总价(元):</span>
			<span class="wap_value"><s:if test="templateBean.purchaseStrategyTotalAmt == null || templateBean.purchaseStrategyTotalAmt == ''">0</s:if><s:else>${templateBean.purchaseStrategyTotalAmt}</s:else></span>
			</div>
			
			<s:set var="curRowNum">0</s:set>
			<s:iterator value="templateBean.strategyMatePrices" var="item" status="s">
			<%--战略内 --%>
			<s:if test="strageTypeCd == 1">
			<div style="line-height:30px;">
				<s:if test="headFlg == 1">
					<s:if test="h01 !=null"><s:set var="maxColSeq">1</s:set></s:if>
					<s:if test="h02 !=null"><s:set var="maxColSeq">2</s:set></s:if>
					<s:if test="h03 !=null"><s:set var="maxColSeq">3</s:set></s:if>
					<s:if test="h04 !=null"><s:set var="maxColSeq">4</s:set></s:if>
					<s:if test="h05 !=null"><s:set var="maxColSeq">5</s:set></s:if>
					<s:if test="h06 !=null"><s:set var="maxColSeq">6</s:set></s:if>
					<s:if test="h07 !=null"><s:set var="maxColSeq">7</s:set></s:if>
					<s:if test="h08 !=null"><s:set var="maxColSeq">8</s:set></s:if>
					<s:if test="h09 !=null"><s:set var="maxColSeq">9</s:set></s:if>
					<s:if test="h10 !=null"><s:set var="maxColSeq">10</s:set></s:if>
					
					<s:if test="#maxColSeq >=1">&nbsp;${h01}</s:if>
					<s:if test="#maxColSeq >=2">/&nbsp;${h02}</s:if>
					<s:if test="#maxColSeq >=3">/&nbsp;${h03}</s:if>
					<s:if test="#maxColSeq >=4">/&nbsp;${h04}</s:if>
					<s:if test="#maxColSeq >=5">/&nbsp;${h05}</s:if>
					<s:if test="#maxColSeq >=6">/&nbsp;${h06}</s:if>
					<s:if test="#maxColSeq >=7">/&nbsp;${h07}</s:if>
					<s:if test="#maxColSeq >=8">/&nbsp;${h08}</s:if>
					<s:if test="#maxColSeq >=9">/&nbsp;${h09}</s:if>
					<s:if test="#maxColSeq >=10">/&nbsp;${h10}</s:if>
					<s:if test="insertDesc !='' && insertDesc != null">/&nbsp;${insertDesc}</s:if>/&nbsp;数量/&nbsp;小计(元)
				</s:if>
				<s:else>
					<s:set var="curRowNum">${curRowNum+1}</s:set>
					&nbsp;(${curRowNum })
					<s:if test="#maxColSeq >=1">&nbsp;${f01}</s:if>
					<s:if test="#maxColSeq >=2">/&nbsp;${f02}</s:if>
					<s:if test="#maxColSeq >=3">/&nbsp;${f03}</s:if>
					<s:if test="#maxColSeq >=4">/&nbsp;${f04}</s:if>
					<s:if test="#maxColSeq >=5">/&nbsp;${f05}</s:if>
					<s:if test="#maxColSeq >=6">/&nbsp;${f06}</s:if>
					<s:if test="#maxColSeq >=7">/&nbsp;${f07}</s:if>
					<s:if test="#maxColSeq >=8">/&nbsp;${f08}</s:if>
					<s:if test="#maxColSeq >=9">/&nbsp;${f09}</s:if>
					<s:if test="#maxColSeq >=10">/&nbsp;${f10}</s:if>
					<s:if test="insertDesc !='' && insertDesc != null">/&nbsp;${currentPrices}</s:if>/&nbsp;${buyNum}/&nbsp;${totalPrice }
				</s:else>  
			</div>
			</s:if>
			</s:iterator>
			
			
			<div class="div_row">
			<span class="wap_title">2.战略外总价(元):</span>
			<span class="wap_value"><s:if test="templateBean.strategyOutTotalAmt == null || templateBean.strategyOutTotalAmt == ''">0</s:if><s:else>${templateBean.strategyOutTotalAmt}</s:else></span>
			</div>
		
			<s:set var="curRowNum">0</s:set>
			<s:iterator value="templateBean.strategyMatePrices" var="item" status="s">
			<s:if test="strageTypeCd == 2">
			<div style="line-height:30px;">
				<s:if test="headFlg == 1">
					<s:if test="h01 !=null"><s:set var="maxColSeq">1</s:set></s:if>
					<s:if test="h02 !=null"><s:set var="maxColSeq">2</s:set></s:if>
					<s:if test="h03 !=null"><s:set var="maxColSeq">3</s:set></s:if>
					<s:if test="h04 !=null"><s:set var="maxColSeq">4</s:set></s:if>
					<s:if test="h05 !=null"><s:set var="maxColSeq">5</s:set></s:if>
					<s:if test="h06 !=null"><s:set var="maxColSeq">6</s:set></s:if>
					<s:if test="h07 !=null"><s:set var="maxColSeq">7</s:set></s:if>
					<s:if test="h08 !=null"><s:set var="maxColSeq">8</s:set></s:if>
					<s:if test="h09 !=null"><s:set var="maxColSeq">9</s:set></s:if>
					<s:if test="h10 !=null"><s:set var="maxColSeq">10</s:set></s:if>
					
					<s:if test="#maxColSeq >=1">&nbsp;${h01}</s:if>
					<s:if test="#maxColSeq >=2">/&nbsp;${h02}</s:if>
					<s:if test="#maxColSeq >=3">/&nbsp;${h03}</s:if>
					<s:if test="#maxColSeq >=4">/&nbsp;${h04}</s:if>
					<s:if test="#maxColSeq >=5">/&nbsp;${h05}</s:if>
					<s:if test="#maxColSeq >=6">/&nbsp;${h06}</s:if>
					<s:if test="#maxColSeq >=7">/&nbsp;${h07}</s:if>
					<s:if test="#maxColSeq >=8">/&nbsp;${h08}</s:if>
					<s:if test="#maxColSeq >=9">/&nbsp;${h09}</s:if>
					<s:if test="#maxColSeq >=10">/&nbsp;${h10}</s:if>
					<s:if test="insertDesc !='' && insertDesc != null">/&nbsp;${insertDesc}</s:if>/&nbsp;数量/&nbsp;小计(元)
				</s:if>
				<s:else>
					<s:set var="curRowNum">${curRowNum+1}</s:set>
					&nbsp;(${curRowNum })
					<s:if test="#maxColSeq >=1">&nbsp;${f01}</s:if>
					<s:if test="#maxColSeq >=2">/&nbsp;${f02}</s:if>
					<s:if test="#maxColSeq >=3">/&nbsp;${f03}</s:if>
					<s:if test="#maxColSeq >=4">/&nbsp;${f04}</s:if>
					<s:if test="#maxColSeq >=5">/&nbsp;${f05}</s:if>
					<s:if test="#maxColSeq >=6">/&nbsp;${f06}</s:if>
					<s:if test="#maxColSeq >=7">/&nbsp;${f07}</s:if>
					<s:if test="#maxColSeq >=8">/&nbsp;${f08}</s:if>
					<s:if test="#maxColSeq >=9">/&nbsp;${f09}</s:if>
					<s:if test="#maxColSeq >=10">/&nbsp;${f10}</s:if>
					<s:if test="insertDesc !='' && insertDesc != null">/&nbsp;${currentPrices}</s:if>/&nbsp;${buyNum}/&nbsp;${totalPrice }
				</s:else>  
			</div>
			</s:if>
			</s:iterator>
		</div>
</div>
