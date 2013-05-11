<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>


<%-- 定标审批表（设计类） --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<c:if test="${(empty templateBean.isAuto) || (templateBean.isAuto eq 'false')}">
		<input  edit='true' validate="required" type="hidden" name="templateBean.isAuto" value="true" />
		<s:hidden id="isEdit" value="true"/>
	</c:if>
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
 		<div><s:checkbox name="templateBean.noProjectFlg" cssClass="group"></s:checkbox><span>无项目</span></div>
 	</div>
 	
	<div class="div_row">
		<span class="wap_title">设计阶段:</span>
		<div><s:checkbox name="templateBean.designStage1" cssClass="group"></s:checkbox><span>概念</span></div>
		<div><s:checkbox name="templateBean.designStage2" cssClass="group"></s:checkbox><span>方案</span></div>
		<div><s:checkbox name="templateBean.designStage3" cssClass="group"></s:checkbox><span>扩初</span></div>
		<div><s:checkbox name="templateBean.designStage4" cssClass="group"></s:checkbox><span>施工图</span></div>
	</div>

	<s:if test="authTypeCd=='BLSY_ZCGL_DB_10'">
	<div class="div_row">
		<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
		<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
	</div>
	</s:if>
	
	<div class="div_label">
		<span class="wap_title">【设计主要内容】</span>
		
		<div class="div_row">
			<span class="wap_title">设计范围:</span>
			<span class="wap_value">${templateBean.designRange}</span>
		</div>
		
		<div class="div_row">
			<span class="wap_title">设计周期:</span>
			<span class="wap_value">${templateBean.fromDate}</span>
			<span class="wap_title">至</span>
			<span class="wap_value">${templateBean.toDate}</span>
			<span class="wap_title">共</span>
			<span class="wap_value">${templateBean.totalDay}</span>
			<span class="wap_title">天</span>
		</div>
		
		<div class="div_row">
			<span class="wap_title">付款方式:</span>
			<span class="wap_value">${templateBean.paymentType}</span>
		</div>
	</div>
	<div class="div_row">
		<div><s:checkbox name="templateBean.isProjectAuth" cssClass="group"></s:checkbox><span>项目权限内</span></div>
	</div>
     <div class="div_row">
		<div><s:checkbox name="templateBean.isMonopoly" cssClass="group"></s:checkbox><span>是垄断</span></div>
	</div>
	<s:if test="templateBean.bidUnit != ''">
	<div class="div_row">
		<span class="wap_title">中标单位:</span>
		<span class="wap_value">${templateBean.bidUnit}</span>
	</div>
	</s:if>
	
	<div class="div_row">
		<span class="wap_title">备注:</span>
		<span class="wap_value">${templateBean.remark}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">推荐中标理由:</span>
		<span class="wap_value">${templateBean.successfulBidReason}</span>
	</div>
	
	
	<s:if test="templateBean.tentativeArea != ''">
	<div class="div_row">
		<span class="wap_title">暂定面积（平米）:</span>
		<span class="wap_value">${templateBean.tentativeArea}</span>
	</div>
	</s:if>
	
	<div class="div_row">
		<span class="wap_title">
			<c:choose>
				<c:when test="${isSy}">
				立项审批费用(元)
				</c:when>
				<c:otherwise>
				目标成本(元)
				</c:otherwise>
			</c:choose>
		</span>
		<span class="wap_value">${templateBean.targetCost}</span>
	</div>
			 
	<s:if test="templateBean.successfulBidPrice != ''">
	<div class="div_row">
		<span class="wap_title">中标价(元):</span>
		<span class="wap_value">${templateBean.successfulBidPrice}</span>
	</div>
	</s:if>
	<s:if test="templateBean.unilateralCost != ''">
	<div class="div_row">
		<span class="wap_title">单方造价（元/平米）:</span>
		<span class="wap_value">${templateBean.unilateralCost}</span>
	</div>
	</s:if>
	<div class="div_label">
		<span class="wap_title">【定标资料附件】</span>
		<div class="div_row">
			<span class="wap_title">1、中标单位报价清单:</span>
			<div id="show_bidPriceListId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.bidPriceListId}','bidPriceListId','${canEdit}');
			</script>
		</div>
		
		<div class="div_row">
			<span class="wap_title">2、设计任务书:</span>
			<div id="show_designTaskBookId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.designTaskBookId}','designTaskBookId','${canEdit}');
			</script>
		</div>
		
		<div class="div_row">
			<span class="wap_title">3、设计深度要求:</span>
			<div id="show_designDepthRequirementId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.designDepthRequirementId}','designDepthRequirementId','${canEdit}');
			</script>
		</div>
	</div>
</div>