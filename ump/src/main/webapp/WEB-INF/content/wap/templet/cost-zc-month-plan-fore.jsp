<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--成本月招采计划(集团)-->

<div id="billContent">
		<%@ include file="template-var.jsp"%>
 		<s:if  test="(nodeCd == 129) && isMyApprove==1">
 		<s:hidden id="isEdit" value="true"/>
 		</s:if>
		<!-- 总记录数 -->
		<!-- 列表 -->
		<s:iterator value="templateBean.planProperties" var="item" status="s">
		<div class="div_label">
			<span class="wap_title">项目:</span>
			<span class="wap_label">【${item.projectName}】</span>
			<div class="div_row">
				<span class="wap_title">工程名称:</span>
				<span class="wap_value">${item.projectDesc}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">招标范围/采购内容:</span>
				<span class="wap_value">${item.invPurDesc}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">预计金额(元):</span>
				<span class="wap_value">${item.totalPrice}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">进场时间:</span>
				<span class="wap_value">${item.enterDate}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">预计上单时间:</span>
				<span class="wap_value">${item.preUploadDate}</span>
			</div>
			<s:if  test="(nodeCd == 129) && isMyApprove==1">
			<div class="div_row">
				<span class="wap_title">定标完成时间:</span>
				<div class="div_input">
					<input edit='true' type="text" validate="required" name="templateBean.planProperties[<s:property value="#s.index" />].bidCompleteDate" value="${item.bidCompleteDate}"/>
				</div>
			</div>
			</s:if>
			<s:else>
				<div class="div_row">
				<span class="wap_title">定标完成时间:</span>
				<span class="wap_value">${item.bidCompleteDate}</span>
				</div>
			</s:else>
			<div class="div_row">
				<span class="wap_title">备注:</span>
				<span class="wap_value">${item.remark}</span>
			</div>
			
			<s:if  test="(nodeCd == 129) && isMyApprove==1">
			<div class="div_row">
				<span class="wap_title">区域成本意见:</span>
				<div class="div_input">
					<textarea edit='true' type="text" validate="required" name="templateBean.planProperties[<s:property value="#s.index" />].areaCostOptionDesc">${item.areaCostOptionDesc}</textarea>
				</div>
			</div>
			</s:if>
			<s:else>
				<div class="div_row">
				<span class="wap_title">区域成本意见:</span>
				<span class="wap_value">${item.areaCostOptionDesc}</span>
				</div>
			</s:else>
		</div>
		</s:iterator>
</div>
