<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--战略采购订单审批表-->
<s:set var="canEdit">false</s:set>
<div class="billContent">
		<%@ include file="template-var.jsp"%>
		<%--合同文本库引用 start --%>
		<%@ include file="bid-contract-strategy-base.jsp"%>
		<%--合同文本引用 end --%>
		<div class="div_row">
			<span class="wap_title">标题:</span>
			<span class="wap_value">${templateBean.titleName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
		</div>
		<div class="div_row">
 			<div><s:checkbox name="templateBean.haierFlg" cssClass="group"></s:checkbox><span>海尔</span></div>
 		</div>
		<div class="div_row">
			<span class="wap_title">战略合同名称:</span>
			<span class="wap_value">${templateBean.contactName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">甲方:</span>
			<span class="wap_value">${templateBean.partA}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">乙方:</span>
			<span class="wap_value">${templateBean.partBName }</span>
		</div>
		<!-- Start Add for part C by zhuxj on 2012.3.31 -->
		<div class="div_row">
			<span class="wap_title">丙方:</span>
			<span class="wap_value">${templateBean.partCName}</span>
		</div>
		<!-- End   Add for part C by zhuxj on 2012.3.31 -->
		<div class="div_row">
			<span class="wap_title">实际供方:</span>
			<span class="wap_value">${templateBean.partBReal}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">材料名称:</span>
			<span class="wap_value">${templateBean.materialName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">使用区域:</span>
			<span class="wap_value">${templateBean.useCoverage}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">采购周期:</span>
			<span class="wap_value">${templateBean.purchasePreiod}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">进场时间:</span>
			<span class="wap_value">${templateBean.enterDate}</span>
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
			<span class="wap_title">战略外价格:</span>
			<span class="wap_value">${templateBean.beyondStraAmt}</span>
			<div id="show_beyondStraAmtId" scresattachname="战略外价格"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.beyondStraAmtId}','beyondStraAmtId','${canEdit}');
			</script>
		</div>
		<div class="div_row">
			<span class="wap_title">订单说明:</span>
			<span class="wap_value">${templateBean.purchaseTotalAmt}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【应提供细项】</span>
			<div class="div_row">
				<span class="wap_title">供应商与贸易公司订货合同:</span>
				<%-- 加上scresattachname 属性系统就会自动抓取与合同文本库 有关的附件（在此表示需要抓取【供应商与贸易公司订货合同 】附件，与合同库附件关联是通件类型名称   ）--%>
				<div id="show_supplyTradeFileId" scresattachname="供应商与贸易公司订货合同"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.supplyTradeFileId}','supplyTradeFileId','${canEdit}');
				</script>
			</div>
			<s:if test="templateBean.tradeHaierFileId != ''">
			<div class="div_row">
				<span class="wap_title">贸易公司与海尔公司订货合同:</span>
				<div id="show_tradeHaierFileId" scresattachname="贸易公司与海尔公司订货合同"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.tradeHaierFileId}','tradeHaierFileId','${canEdit}');
				</script>
			</div>
			
			</s:if>
			<s:if test="templateBean.haierProjectFileId != ''">
			<div class="div_row">
				<span class="wap_title">海尔公司与项目公司订货合同:</span>
				<div id="show_haierProjectFileId" scresattachname="海尔公司与项目公司订货合同"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.haierProjectFileId}','haierProjectFileId','${canEdit}');
				</script>
			</div>
			</s:if>
			<s:if test="templateBean.tradeProjectFileId != ''">
				<div class="div_row">
				<span class="wap_title">贸易公司与项目公司订货合同:</span>
				<div id="show_tradeProjectFileId" scresattachname="贸易公司与项目公司订货合同" ></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.tradeProjectFileId}','tradeProjectFileId','${canEdit}');
				</script>
			</div>
			</s:if>
			<div class="div_row">
				<span class="wap_title">采购数量及技术参数确认单:</span>
				<div id="show_purchaseTechConfirmFileId" scresattachname="采购数量及技术参数确认单"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.purchaseTechConfirmFileId}','purchaseTechConfirmFileId','${canEdit}');
				</script>
			</div>
		</div>
		
		
</div>
