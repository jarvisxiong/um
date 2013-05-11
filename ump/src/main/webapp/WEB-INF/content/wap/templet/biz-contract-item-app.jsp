<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>


<!-- 商业合同条款审批表  -->
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<%--合同库引用 start --%>
	<%@ include file="bid-contract-base.jsp"%>
	<%--合同库引用 end --%>
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
	
	<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
		
	
	<c:if test="${isSy}">
	<div class="div_row">
		<div><s:checkbox name="templateBean.businessCompany" cssClass="group"></s:checkbox><span>商业公司发起</span></div>
		<div><s:checkbox name="templateBean.businessGroup" cssClass="group"></s:checkbox><span>商业集团发起</span></div>
	</div>
	</c:if> 
	

	<div class="div_row">
		<span class="wap_title">项目名称:</span>
		<span class="wap_value">${templateBean.projectName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">工程名称:</span>
		<span class="wap_value">${templateBean.engineeringName}</span>
	</div> 
	
	
	<div class="div_label">
		<span class="wap_label">【合同双方】</span>
		<div class="div_row">
			<span class="wap_title">甲方:</span>
			<span class="wap_value">${templateBean.sideA}</span>
		</div> 
		<div class="div_row">
			<span class="wap_title">乙方:</span>
			<span class="wap_value">${templateBean.sideB}</span>
		</div>
		<!-- Start Add for part C by zhuxj on 2012.3.31 -->
		<div class="div_row">
			<span class="wap_title">丙方:</span>
			<span class="wap_value">${templateBean.sideC}</span>
		</div> 
		<!-- End   Add for part C by zhuxj on 2012.3.31 -->
	</div> 
	
	
	<div class="div_label">
		<span class="wap_label">【招标主要内容】</span>
		<div class="div_row">
			<span class="wap_title">招标范围:</span>
			<span class="wap_value">${templateBean.bidRange}</span>
		</div> 
		<div class="div_row">
			<span class="wap_title">施工工期:</span>
			<span class="wap_value">${templateBean.constructionTimeLimit}</span>
		</div> 
		
		<div class="div_row">
			<span class="wap_title">质量要求:</span>
			<span class="wap_value">${templateBean.qualityRequirement}</span>
		</div>  
		<div class="div_row">
			<span class="wap_title">预算金额(元):</span>
			<span class="wap_value">${templateBean.budgetPrice}</span>
		</div>  
		<div class="div_row">
			<span class="wap_title">付款方式:</span>
			<span class="wap_value">${templateBean.payMode}</span>
		</div>  
			  
		<div class="div_row">
			<span class="wap_title">付款方式:</span>
			<span class="wap_value">${templateBean.payMode}</span>
		</div>  
	</div>
	
	<div class="div_label">
		<span class="wap_label">【资料附件】</span>
		<div class="div_row">
			<span class="wap_title">标准合同:</span>
			<div id="show_criterionContractId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.criterionContractId}','criterionContractId','${canEdit}');
			</script>
		</div> 
	</div> 
	
	
	
	<s:iterator value="templateBean.otherProperties" var="item" status="s">
	
		<div class="div_label">
			<span class="wap_label">【标准合同条款】</span>
			<div class="div_row">
				<span class="wap_title">条款项:</span>
				<span class="wap_value">${item.itemNo}</span>
			</div>  
			<div class="div_row">
				<span class="wap_title">条款内容:</span>
				<span class="wap_value">${item.itemContent}</span>
			</div>  
		</div> 
		
		<div class="div_row">
			<span class="wap_title">乙方反馈意见:</span>
			<span class="wap_value">${item.sideBFeedback}</span>
		</div>  
		
		
		<div class="div_row">
			<span class="wap_title">责任部门:</span>
			<span class="wap_value"><%= CodeNameUtil.getResNodeNameByCd(JspUtil.findString("#item.resDeptCd")) %></span>
		</div>  
		<div class="div_row">
			<span class="wap_title">责任部门意见:</span>
		    <c:set var="curDeptCd" >;${item.resDeptCd};</c:set>
		    
			<c:if test="${!(fn:contains(myNode, curDeptCd) && isMyApprove==1)}">
				<span class="wap_value">${item.resDeptOption}</span>
			</c:if>
			
			<c:if test="${ (fn:contains(myNode, curDeptCd) && isMyApprove==1)}">
				<div class="div_input">
					<s:hidden id="isEdit" value="true"/>
					<textarea edit='true' validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].resDeptOption">${item.resDeptOption}</textarea>
				</div>
			</c:if>
		</div>  
		
		
		<div class="div_row">
			<span class="wap_title">成本中心意见:</span>
		    <s:if test="#curNodeCd==13 && isMyApprove==1">
		    	<div class="div_input">
					<s:hidden id="isEdit" value="true"/>
					<textarea edit='true' validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].costCenterOption">${item.costCenterOption}</textarea>
		    	</div>
			</s:if>
			<s:else>
				<span class="wap_value">${item.costCenterOption}</span>
			</s:else>
		</div>  
			
		<div class="div_row">
			<span class="wap_title">最终意见商业总经理:</span>
		  	<!-- 最终意见：宝龙商业副总经理(招商) -->
			<s:if test="#curNodeCd==204 && isMyApprove==1">
				<div class="div_input">
					<s:hidden id="isEdit" value="true"/>
					<textarea edit='true' validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].vicePresidentsOption">${item.vicePresidentsOption}</textarea>
				</div>
			</s:if>
			<s:else>
				<span class="wap_value">${item.vicePresidentsOption}</span>
			</s:else>
		</div>  
	</s:iterator>
</div>