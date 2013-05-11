<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 合同条款审批表  -->


<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<div id="billContent">
		<%@ include file="template-var.jsp"%>
	    <%--合同文本库引用 start --%>
		<%@ include file="bid-contract-base.jsp"%>
		<%--合同文本库引用 end --%>
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
		
		<div class="div_label">
			<span class="wap_label">【标准合同条款】<red>(可左右拖动查看)</red></span>
			<div class="div_scroll">
				<s:iterator value="templateBean.otherProperties" var="item" status="s">
				<table>
					<col width="120"/>
					 <col width="60"/>
					 <col/>
					<tr>
					  <td rowspan="2" class="wap_title">标准合同条款</td>
					  <td class="wap_title">条款项</td>
					  <td>
					  <span class="wap_value">${item.itemNo}</span>
					  </td>
					 </tr>
					 <tr>
					  <td class="wap_title">条款内容</td>
					  <td>
					  <span class="wap_value">${item.itemContent}</span>
					  </td>
					 </tr>
					 <tr>
					  <td class="wap_title">乙方反馈意见</td>
					  <td colspan="2">
					  <span class="wap_value">${item.sideBFeedback}</span>
					  </td>
					 </tr>
					 <tr>
					  <td class="wap_title">责任部门</td>
					  <td colspan="2">
					  <span class="wap_value"><%=CodeNameUtil.getResNodeNameByCd(JspUtil.findString("#item.resDeptCd")) %></span>
					  </td>
					 </tr>
					 <c:set var="curDeptCd" >;${item.resDeptCd};</c:set>
					 <tr>
					  <td class="wap_title">责任部门意见</td>
					  <td colspan="2">
					  	<c:if test="${!(fn:contains(myNode, curDeptCd)&&isMyApprove==1)}">
							<span class="wap_value">${item.resDeptOption}</span>
						</c:if>
						<c:if test="${(fn:contains(myNode, curDeptCd) && isMyApprove==1)}">
							<s:hidden id="isEdit" value="true"/>
							<div class="div_input">
								<textarea edit='true' validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].resDeptOption">${item.resDeptOption}</textarea>
							</div>
						</c:if>
					  </td>
					 </tr>
					 <tr>
					  <td class="wap_title">成本中心意见</td>
					  <td colspan="2">
					  	<s:if test="#curNodeCd==13 && isMyApprove==1">
						<s:hidden id="isEdit" value="true"/>
						<div class="div_input">
							<textarea  edit='true' validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].costCenterOption">${item.costCenterOption}</textarea>
						</div>
						</s:if>
						<s:else>
						<span class="wap_value">${item.costCenterOption}</span>
						</s:else>
					  	
					  </td>
					 </tr>
					 <tr>
					  <td class="wap_title">最终意见商业总经理</td>
					  <td colspan="2">
					  	<s:if test="#curNodeCd==157 && isMyApprove==1">
						<s:hidden id="isEdit" value="true"/>
						<div class="div_input">
							<textarea  edit='true' validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].vicePresidentsOption">${item.vicePresidentsOption}</textarea>
						</div>
						</s:if>
						<s:else>
						<span class="wap_value">${item.vicePresidentsOption}</span>
						</s:else>
					  </td>
					 </tr>
				</table>
			</s:iterator>
			</div>
		</div>
		
</div>
