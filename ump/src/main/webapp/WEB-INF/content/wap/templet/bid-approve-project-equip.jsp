<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>



<div id="billContent">
	<%@ include file="template-var.jsp"%>
	
	<c:set var="isSy">false</c:set>
	<c:if test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
	<c:set var="isSy">true</c:set>
	</c:if>
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
	</s:set>

		<c:if test="${isSy}">
			<div class="div_row">
				<div><s:checkbox name="templateBean.businessCompany" cssClass="group"></s:checkbox><span>商业公司发起</span></div>
				<div><s:checkbox name="templateBean.businessGroup" cssClass="group"></s:checkbox><span>商业集团发起</span></div>
			</div>
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
		
		<s:if test="authTypeCd=='BLSY_ZCGL_DB_20'||authTypeCd=='BLSY_ZCGL_DB_30'||authTypeCd=='BLSY_ZCGL_DB_40'||authTypeCd=='BLSY_ZCGL_DB_70'">
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
				<span class="wap_title">施工工期:</span>
				<span class="wap_value">${templateBean.fromDate}</span>
				<span class="wap_title">至</span>
				<span class="wap_value">${templateBean.toDate}</span>
				<span class="wap_title">共</span>
				<span class="wap_value">${templateBean.totalDay}</span>
				<span class="wap_title">天</span>
			</div>
			
			<div class="div_row">
				<span class="wap_title">质量要求:</span>
				<span class="wap_value">${templateBean.qualityRequirement}</span>
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
				
			<div class="div_row">
				<span class="wap_title">中标单位:</span>
				<span class="wap_value">${templateBean.bidUnit}</span>
			</div>
				 
			<div class="div_row">
				<span class="wap_title">备注:</span>
				<span class="wap_value">${templateBean.remark}</span>
			</div>
			
				  
			<div class="div_row">
				<span class="wap_title">是垄断:</span>
				<div><s:checkbox name="templateBean.isMonopoly" cssClass="group"></s:checkbox><span></span></div>
			</div>
			
				
			<div class="div_row">
				<span class="wap_title">推荐中标理由:</span>
				<span class="wap_value">${templateBean.successfulBidReason}</span>
			</div>
			
				
			<div class="div_row">
				<span class="wap_title">
					<c:choose>
						<c:when test="${isSy}">
						立项审批费用(元)
						</c:when>
						<c:otherwise>
						目标成本(元)
						</c:otherwise>
					</c:choose>:
				</span>
				<span class="wap_value">${templateBean.targetCost}</span>
			</div>
				 
			<div class="div_row">
				<span class="wap_title">中标价(元):</span>
				<span class="wap_value">${templateBean.successfulBidPrice}</span>
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
				<span class="wap_title">5、设备技术参数及规格</span>
				<div id="show_techBidEvaluateId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.techBidEvaluateId}','techBidEvaluateId','${canEdit}');
				</script>
			</div>
			
			
		</div>
</div>