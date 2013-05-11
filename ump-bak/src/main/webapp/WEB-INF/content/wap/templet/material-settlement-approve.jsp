<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 材料设备结算审批表 --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<span class="wap_title">审批权限:</span>
		<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
	</div>
	<div class="div_row">
	  <span class="wap_title">合同编号:</span>
	  <span class="wap_value">${templateBean.contNo}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">合同名称:</span>
	  <span class="wap_value">${templateBean.contName}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">项目名称:</span>
	  <span class="wap_value">${templateBean.projectName}(${templateBean.projectPeriod})期</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">乙方:</span>
	  <span class="wap_value">${templateBean.partB}</span>
    </div>
    
    <div class="div_row">
	  <span class="wap_title">丙方:</span>
	  <span class="wap_value">${templateBean.partC}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">实际供方:</span>
	  <span class="wap_value">${templateBean.realProvName}</span>
    </div>

    <div class="div_row">
	  <span class="wap_title">原合同总价:</span>
	  <span class="wap_value">${templateBean.totalPrice}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">合同性质:</span>
	  <span class="wap_value">${templateBean.contProperty}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">已支付金额:</span>
	  <span class="wap_value">${templateBean.payMoney}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">占合同总金额:</span>
	  <span class="wap_value">${templateBean.tatalRate}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">乙方报审金额(元):</span>
	  <span class="wap_value">${templateBean.partBReviewAmount}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">
	  <c:choose>
		<c:when test="${isSy}">
		商业公司审核费用(元):
		</c:when>
		<c:otherwise>
		地产公司审核费用(元):
		</c:otherwise>
	 </c:choose>
	</span>
	  <span class="wap_value">${templateBean.partBReviewAmount}</span>
    </div>
    <s:iterator value="templateBean.otherProperties" var="item" status="s">
    <div class="div_label">
    <div class="div_row">
	  <span class="wap_title">领货单位名称:</span>
	  <span class="wap_value">${item.receiveUnitName}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">相应合同编号:</span>
	  <span class="wap_value">${item.receiveContNo}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">金额(元):</span>
	  <span class="wap_value">${item.receiveAmount}</span>
    </div>
    </div>
    </s:iterator>
    <div class="div_row">
	  <span class="wap_title">库存管理单位名称:</span>
	  <span class="wap_value">${templateBean.storageUnitName}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">库存(元):</span>
	  <span class="wap_value">${templateBean.storageAmount}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">供货和领货说明:</span>
	  <span class="wap_value">${templateBean.description}</span>
    </div>
    <div class="div_label">
      <span class="wap_label">【附件】</span>
      <div class="div_row">
	     <span class="wap_title">一审结算汇总表:</span>
	     <div id="show_settlementSummaryId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.settlementSummaryId}','settlementSummaryId','${canEdit}');
				</script>
	  </div>
	  <div class="div_row">
	     <span class="wap_title">供货单位结算:</span>
	     <div id="show_provideUnitSettlementId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.provideUnitSettlementId}','provideUnitSettlementId','${canEdit}');
				</script>
	  </div>
	  <div class="div_row">
	     <span class="wap_title">三方签收资料:</span>
	     <div id="show_threePartReceiveId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.threePartReceiveId}','threePartReceiveId','${canEdit}');
				</script>
	  </div>
	  <div class="div_row">
	     <span class="wap_title">现场验收照片(不少于5张，特别是元器件是否对版，否则退单):</span>
	     <div id="show_curPicId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.curPicId}','curPicId','${canEdit}');
				</script>
	  </div>
    </div>
</div>
