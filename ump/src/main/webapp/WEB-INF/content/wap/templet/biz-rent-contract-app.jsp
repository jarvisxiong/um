<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 合同条款审批表  -->


<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%><div id="billContent">
	
		<%@ include file="template-var.jsp"%>
		<%--Add by zhuxj on 2012.4.12 start --%>
			<%@ include file="bid-contract-base.jsp"%>
			<%--Add for import contract by zhuxj on 2012.4.12 end --%>
		<div class="div_row">
			<span class="wap_title">文件标题:</span>
			<span class="wap_value">${templateBean.fileName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">所在项目:</span>
			<span class="wap_value">${templateBean.projectName}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【合同双方】</span>
			<div class="div_row">
				<span class="wap_title">甲方:</span>
				<span class="wap_value">${templateBean.partA}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">乙方:</span>
				<span class="wap_value">${templateBean.partB}</span>
			</div>
			<!-- Start Add for part C by zhuxj on 2012.3.31 -->
			<div class="div_row">
				<span class="wap_title">丙方:</span>
				<span class="wap_value">${templateBean.partC}</span>
			</div>
			<!-- End   Add for part C by zhuxj on 2012.3.31 -->
		</div>
		
		<div class="div_label">
			<span class="wap_label">【合同主要内容】</span>
			<div class="div_row">
				<span class="wap_title">经营品牌:</span>
				<span class="wap_value">${templateBean.manageBrand}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">经营业态:</span>
				<span class="wap_value">${templateBean.manageBusiness}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">租凭位置:</span>
				<span class="wap_value">${templateBean.rentPosition}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">计租面积:</span>
				<span class="wap_value">${templateBean.rentArea}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">租期:</span>
				<span class="wap_value">${templateBean.rentPeriod}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">租金:</span>
				<span class="wap_value">${templateBean.rentMoney}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">物业管理费或综合管理费:</span>
				<span class="wap_value">${templateBean.manageMoney}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">交付时间:</span>
				<span class="wap_value">${templateBean.payTime}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">开业时间:</span>
				<span class="wap_value">${templateBean.startTime}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">装修期:</span>
				<span class="wap_value">${templateBean.fitPeriod}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">租金优惠期:</span>
				<span class="wap_value">${templateBean.rentDownPeriod}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">其它事项:</span>
				<span class="wap_value">${templateBean.otherContent}</span>
			</div>
		</div>
		<div class="div_label">
			<span class="wap_label">【资料附件】</span>
			<div class="div_row">
				<span class="wap_title">标准合同</span>
				<div id="show_standardContId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.standardContId}','standardContId','${canEdit}');
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
					  <td class="wap_title">法务意见</td>
					  <td colspan="2">
					  	<s:if test="#curNodeCd==3 && isMyApprove==1">
						<s:hidden id="isEdit" value="true"/>
						<div class="div_input">
							<textarea  edit='true' validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].lawCenterOption">${item.lawCenterOption}</textarea>
						</div>
						</s:if>
						<s:else>
						<span class="wap_value">${item.lawCenterOption}</span>
						</s:else>
					  	
					  </td>
					 </tr>
					 <tr>
					  <td class="wap_title">宝龙商业副总经理</td>
					  <td colspan="2">
					  	<s:if test="#curNodeCd==204 && isMyApprove==1">
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
