<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>


<%-- 定标审批表（材料及设备类） --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<c:if test="${(empty templateBean.isAuto) || (templateBean.isAuto eq 'false')}">
		<input  edit='true' validate="required" type="hidden" name="templateBean.isAuto" value="true" />
		<s:hidden id="isEdit" value="true"/>
	</c:if>
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
	</s:set>
	<c:set var="isJD">false</c:set>
	<c:if test='${fn:startsWith(authTypeCd, "JD_")}'>
	<c:set var="isJD">true</c:set>
	</c:if>
	

	<s:if test="aboutHotel">
		<div class="div_row">
			<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
		</div>
	</s:if>
		 

	<div class="div_row">
		<span class="wap_title">项目名称:</span>
		<span class="wap_value">${templateBean.projectName}</span>
		(<span class="wap_value">${templateBean.projectPeriod}</span>)期
	</div> 
	
	
	<div class="div_row">
		<span class="wap_title">工程名称:</span>
		<span class="wap_value">${templateBean.engineeringName}</span>
	</div> 
 
		
	<s:if test="authTypeCd=='BLSY_ZCGL_DB_10'||authTypeCd=='JD_ZCGL_DB_10'||authTypeCd=='JD_ZCGL_DB_20'||authTypeCd=='JD_ZCGL_DB_30'||authTypeCd=='JD_ZCGL_DB_30'||authTypeCd=='JD_ZCGL_DB_40'">
		<div class="div_row">
			<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
			<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
		</div>
	</s:if>
	
	
		<div class="div_label">
			<span class="wap_title">【招标主要内容】</span>
			
			<div class="div_row">
				<span class="wap_title">招标范围:</span>
				<span class="wap_value">${templateBean.bidRange}</span>
			</div>
			
			<div class="div_row">
				<span class="wap_title">数&nbsp;&nbsp;&nbsp;&nbsp;量:</span>
				<span class="wap_value">${templateBean.quatity}</span>
			</div>
			
			<div class="div_row">
				<span class="wap_title">供货周期:</span>
				<span class="wap_value">${templateBean.supplyPeriod}</span>
			</div>
			
			<div class="div_row">
				<span class="wap_title">技术要求:</span>
				<span class="wap_value">${templateBean.teckRequirement}</span>
			</div>
			
			
			<div class="div_row">
				<span class="wap_title">计价模式:</span>
				<div><s:checkbox name="templateBean.pricingModel1" cssClass="group"></s:checkbox><span>总价包干</span></div>
				<div><s:checkbox name="templateBean.pricingModel2" cssClass="group"></s:checkbox><span>单价包干</span></div>
			</div>
			
			<div class="div_row">
				<span class="wap_title">付款方式:</span>
				<span class="wap_value">${templateBean.paymentType}</span>
			</div>
		</div>
		
		<div class="div_row">
			<span class="wap_title">项目权限内:</span>
			<div><s:checkbox name="templateBean.isProjectAuth" cssClass="group"></s:checkbox><span></span></div>
		</div>
		
		<div class="div_row">
			<span class="wap_title">是垄断:</span>
			<div><s:checkbox name="templateBean.isMonopoly" cssClass="group"></s:checkbox><span></span></div>
		</div>
		
		<div class="div_row">
			<span class="wap_title">中标单位:</span>
			<span class="wap_value">${templateBean.bidUnit}</span>
		</div>
		
		
		<div class="div_row">
			<span class="wap_title">备注:</span>
			<span class="wap_value">${templateBean.remark}</span>
		</div>
		
			<div class="div_row">
			<span class="wap_title">本次评审结果:</span>
				<div><s:checkbox name="templateBean.contTypeCd1" id="contTypeCd1"  cssClass="group" ></s:checkbox>二方合同</div>
				<div><s:checkbox name="templateBean.contTypeCd2" id="contTypeCd2"  cssClass="group" ></s:checkbox>三方合同</div>
				<div><s:checkbox name="templateBean.contTypeCd3" id="contTypeCd3"  cssClass="group" ></s:checkbox>带贸易公司合同</div>
		</div>
		<div class="div_row">
			<span class="wap_title">甲方:</span>
			<span class="wap_value">${templateBean.parta}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">已方:</span>
			<span class="wap_value">${templateBean.partb}</span>
		</div>
		
		<s:if test="templateBean.contTypeCd2=='true'">
		<div class="div_row">
			<span class="wap_title">丙方:</span>
			<span class="wap_value">${templateBean.partC}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">领料施工单位:</span>
			<span class="wap_value">${templateBean.realUseName}</span>
		</div>
		</s:if>
		<s:elseif test="templateBean.contTypeCd3=='true'">
		<div class="div_row">
			<span class="wap_title">实际供方:</span>
			<span class="wap_value">${templateBean.realProvName}</span>
		</div>
		</s:elseif>
		
		<div class="div_row">
			<span class="wap_title">推荐中标理由:</span>
			<span class="wap_value">${templateBean.successfulBidReason}</span>
		</div>
		
		
		
		<div class="div_row">
			<span class="wap_title">预算金额(元):</span>
			<span class="wap_value">${templateBean.budgetPrice}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">目标成本(元):</span>
			<span class="wap_value">${templateBean.targetCost}</span>
		</div>
		
		
		<div class="div_row">
			<span class="wap_title">中标价(元):</span>
			<span class="wap_value">${templateBean.successfulBidPrice}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">单价(元):</span>
			<span class="wap_value">${templateBean.unitPrice}</span>
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
				<span class="wap_title">4、招标答疑及询标问卷</span> 
				<div id="show_bidAnswerAndBidQueryId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidAnswerAndBidQueryId}','bidAnswerAndBidQueryId','${canEdit}');
				</script>
			</div>
			
			
			<div class="div_row">
				<span class="wap_title">5、技术标评结果</span> 
				<div id="show_techBidEvaluateId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.techBidEvaluateId}','techBidEvaluateId','${canEdit}');
				</script>
			</div>
			
			
			<div class="div_row">
				<span class="wap_title">6、招标图纸/技术要求</span> 
				<div id="show_bidPictrueId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidPictrueId}','bidPictrueId','${canEdit}');
				</script>
			</div>
		</div>
</div>