<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--笔记本购置申请单-->

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<div id="billContent">
		<%@ include file="template-var.jsp"%>   
		<div class="div_row">
			<span class="wap_title">所属中心:</span>
			<s:if test="resApproveInfoId==null">
					<s:set var="sendCenterName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </s:set>
					</s:if>
					<s:else>
					<s:set var="sendCenterName">${templateBean.appCenterName}</s:set>
			</s:else>
			<span class="wap_value">${sendCenterName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">申请人:</span>
			<span class="wap_value">${templateBean.applicant}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">申购理由:</span>
			<span class="wap_value">${templateBean.appReason}</span>
		</div>
		<s:if test="authTypeCd=='XZRL_XZGL_115'">
			<s:if test="nodeCd==13 && isMyApprove==1">
			<s:hidden id="isEdit" value="true"/>
			<div class="div_label">
				<span class="wap_label">【成本控制中心审核】</span>
				<div class="div_row">
					<span class="wap_title">配置:</span>
					<div class="div_input">
						<input  edit='true'  validate="required" type="text" name="templateBean.notebookComputer[0].configuration"/>
					</div>
					
				</div>
				<div class="div_row">
					<span class="wap_title">品牌型号:</span>
					<div class="div_input">
						<input  edit='true'  validate="required" type="text" name="templateBean.notebookComputer[0].computerType"/>
					</div>
				</div>
				<div class="div_row">
					<span class="wap_title">价格(元):</span>
					<div class="div_input">
						<input  edit='true'  validate="required" type="text" name="templateBean.notebookComputer[0].price" />
					</div>
				</div>
				<div class="div_row">
					<span class="wap_title">供应商:</span>
					<div class="div_input">
					<input  edit='true'  validate="required" type="text" name="templateBean.notebookComputer[0].suppplier"/>
					</div>
				</div>
				<div class="div_row">
					<span class="wap_title">注:</span>
					<div class="div_input">
					<input  edit='true'  validate="required" type="text" name="templateBean.notebookComputer[0].compRemark"/>
					</div>
				</div>
				
			</div>
			
			<div class="div_row">
					<span class="wap_title">确认机型及供应商:</span>
					<div class="div_input">
					<input  edit='true'  type="text" name="templateBean.checkSupplier" value="${templateBean.checkSupplier}"/>
					</div>
			</div>
			</s:if>
			<s:else>
			<div class="div_label">
					<span class="wap_label">【成本控制中心审核】</span>
					<div class="div_row">
						<span>序号/配置/品牌型号/价格(元)/供应商/注</span>
						<div class="orgDiv">
							<s:iterator value="templateBean.notebookComputer" var="item" status="s">
								<span class="wap_value"><s:property value="#s.index+1"/>./${item.configuration}/${item.computerType}/${item.price}/${item.suppplier}/${item.compRemark}</span>
							</s:iterator>
						</div>
					</div>
					<div class="div_row">
					<span class="wap_title">确认机型及供应商:</span>
					<span class="wap_value">${templateBean.checkSupplier}</span>
					</div>
				
			</div>
			</s:else>
			</s:if>
			<s:if test="(nodeCd==115 || nodeCd==230) &&isMyApprove==1">
			<s:hidden id="isEdit" value="true"/>
			<div class="div_row">
				<span class="wap_title">使用人:</span>
				<div class="div_input">
					<input  validate="required" edit='true' type="text" name="templateBean.userName" value="${templateBean.userName}"/>
				</div>
			</div>
			<div class="div_row">
				<span class="wap_title">笔记本资产编码:</span>
				<div class="div_input">
					<input  validate="required" edit='true' type="text" name="templateBean.computerId" value="${templateBean.computerId}"/>
				</div>
			</div>
			<div class="div_row">
				<span class="wap_title">笔记本价格(元):</span>
				<div class="div_input">
					<input   edit='true' validate="required" type="text" name="templateBean.computerPrice" value="${templateBean.computerPrice}"/>
				</div>
			</div>
			<div class="div_row">
				<span class="wap_title">每月扣款金额(元):</span>
				<div class="div_input">
					<input  edit='true' validate="required" type="text" name="templateBean.payForMonth" value="${templateBean.payForMonth}"/>
				</div>
			</div>
			<div class="div_row">
				<span class="wap_title">抵款起始日期:</span>
				<div class="div_input">
					<input  edit='true'  validate="required" type="text" name="templateBean.payFromDate" value="${templateBean.payFromDate}"/>
				</div>
			</div>
			<div class="div_row">
				<span class="wap_title">抵款终止日期:</span>
				<div class="div_input">
					<input  edit='true' validate="required" type="text" name="templateBean.payToDate" value="${templateBean.payToDate}"/>
				</div>
			</div>
			<div class="div_row">
				<span class="wap_title">笔记本返款日期:</span>
				<div class="div_input">
					<input  edit='true'  validate="required" type="text" name="templateBean.inFromDate" value="${templateBean.inFromDate}"/>
				</div>
			</div>
			<div class="div_row">
				<span class="wap_title">备注:</span>
				<div class="div_input">
					<textarea  edit='true' name="templateBean.deptRemark">${templateBean.deptRemark}</textarea>
				</div>
			</div>
			</s:if>
			<s:else>
			<div class="div_row">
				<span class="wap_title">使用人:</span>
				<span class="wap_value">${templateBean.userName}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">笔记本资产编码:</span>
				<span class="wap_value">${templateBean.computerId}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">笔记本价格(元):</span>
				<span class="wap_value">${templateBean.computerPrice}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">每月扣款金额(元):</span>
				<span class="wap_value">${templateBean.payForMonth}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">抵款起始日期:</span>
				<span class="wap_value">${templateBean.payFromDate}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">抵款终止日期:</span>
				<span class="wap_value">${templateBean.payToDate}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">笔记本返款日期:</span>
				<span class="wap_value">${templateBean.inFromDate}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">备注:</span>
				<span class="wap_value">${templateBean.deptRemark}</span>
			</div>
			</s:else>
		
</div>
