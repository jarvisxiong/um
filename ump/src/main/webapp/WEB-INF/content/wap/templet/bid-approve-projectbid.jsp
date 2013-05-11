<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 定标审批表（项目招标） --%>

<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>

<div id="billContent">

		<%@ include file="template-var.jsp"%>
		<c:if test="${(empty templateBean.isAuto) || (templateBean.isAuto eq 'false')}">
			<input  edit='true' validate="required" type="hidden" name="templateBean.isAuto" value="true" />
			<s:hidden id="isEdit" value="true"/>
		</c:if>
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
			(<span class="wap_value">${templateBean.projectPeriod}</span>)期
		</div> 
		<div class="div_row">
			<span class="wap_title">工程名称:</span>
			<span class="wap_value">${templateBean.engineeringName}</span>
		</div> 
		<div class="div_row">
			<span class="wap_title">审批权限:</span>
			<div><s:checkbox name="templateBean.below30" cssClass="group"></s:checkbox><span>金额≤30万</span></div>
			<div><s:checkbox name="templateBean.from30to100" cssClass="group"></s:checkbox><span>30万＜金额≤100万</span></div>
			<div><s:checkbox name="templateBean.from100to500" cssClass="group"></s:checkbox><span>100万＜金额≤500万</span></div>
			<div><s:checkbox name="templateBean.above500" cssClass="group"></s:checkbox><span>金额＞500万</span></div>
		</div>
		
		<s:if test="authTypeCd == 'ZCGL_HTQS_80'">
		<div class="div_row">
			<span class="wap_title">招采计划编号:</span>
			<span class="wap_value">${templateBean.ccbpNo}</span>
		</div> 
		</s:if>
			
			
		<div class="div_label">
			<span class="wap_title">【招标主要内容】</span>
			<div class="div_row">
				<span class="wap_title">招标范围:</span>
				<span class="wap_value">${templateBean.bidRange}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">施工面积:</span>
				<span class="wap_value">${templateBean.constructionArea}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">施工工期:</span>
				<span class="wap_value">${templateBean.fromDate}</span>
				<span class="wap_title">至</span>
				<span class="wap_value">${templateBean.toDate}</span>
				<span class="wap_title">共</span>
				<span class="wap_value">${templateBean.totalDay}</span>
				<span class="wap_title">天</span>
			</div>
			<div class="div_row">
			<span class="wap_title">合同性质:</span>
			<div><s:checkbox name="templateBean.pricingModel1" cssClass="group"></s:checkbox><span>总价包干</span></div>
			<div><s:checkbox name="templateBean.pricingModel2" cssClass="group"></s:checkbox><span>可调总价包干</span></div>
			<div><s:checkbox name="templateBean.pricingModel3" cssClass="group"></s:checkbox><span>单价包干</span></div>
			<div><s:checkbox name="templateBean.pricingModel4" cssClass="group"></s:checkbox><span>定额计价</span></div>
			</div>
			<div class="div_row">
				<span class="wap_title">付款方式:</span>
				<span class="wap_value">${templateBean.paymentType}</span>
			</div>
		</div>
		<div class="div_row">
				<div><s:checkbox name="templateBean.isProjectAuth" cssClass="group"></s:checkbox><span>项目权限内</span></div>
				<div><s:checkbox name="templateBean.isMonopoly" cssClass="group"></s:checkbox><span>是垄断</span></div>
		</div>
		<div class="div_row">
				<span class="wap_title">推荐中标单位:</span>
				<span class="wap_value">${templateBean.bidUnit}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">备注:</span>
				<span class="wap_value">${templateBean.remark}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">推荐中标理由:</span>
				<span class="wap_value">${templateBean.successfulBidReason}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">目标成本(元):</span>
				<span class="wap_value">${templateBean.targetCost}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">标底(元):</span>
				<span class="wap_value">${templateBean.baseBidPrice}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">中标价(元):</span>
				<span class="wap_value">${templateBean.successfulBidPrice}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">单方造价（元/平米）:</span>
				<span class="wap_value">${templateBean.unilateralCost}</span>
		</div>
		<div class="div_label">
			<span class="wap_title">【定标资料附件】</span>
			<div class="div_row">
				<span class="wap_title">1、邀标单位资质审批表</span>
				<div id="show_bidQualificationApproveId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidQualificationApproveId}','bidQualificationApproveId','${canEdit}');
				</script>
			</div>
			<div class="div_row">
				<span class="wap_title">2、中标单位报价清单</span>
				<div id="show_bidPriceListId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidPriceListId}','bidPriceListId','${canEdit}');
				</script>
			</div>
			<div class="div_row">
				<span class="wap_title">3、投标报价汇总表</span>
				<div id="show_bidSummaryId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidSummaryId}','bidSummaryId','${canEdit}');
				</script>
			</div>
			<div class="div_row">
				<span class="wap_title">4、预算和批准文件</span>
				<div id="show_budgetApproveFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.budgetApproveFileId}','budgetApproveFileId','${canEdit}');
				</script>
			</div>
			<div class="div_row">
				<span class="wap_title">5、招标文件、投标文件</span>
				<div id="show_inviteBidFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.inviteBidFileId}','inviteBidFileId','${canEdit}');
				</script>
			</div>
		</div>
</div>
