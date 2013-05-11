<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--成本月招采计划(项目)-->

<div id="billContent">
	
	<!-- 区域技术管理部/区域项目管理部/区域成本管理部nodeCd==153||nodeCd==154||nodeCd==156 -->
	<s:if test="(nodeCd == 129) && isMyApprove==1">
		<s:hidden id="isEdit" value="true"/>
	</s:if> 
		<%@ include file="template-var.jsp"%>
		<s:iterator value="templateBean.planProperties" var="item" status="s">
		<div class="div_label">
			<span class="wap_title">项目:</span>
			<span class="wap_label">【${item.projectName}】</span>
			
			
		<div class="div_row">
			<span class="wap_title">类别:</span>
			<div><s:checkbox name="templateBean.bidType"  cssClass="group"></s:checkbox><span>招标</span></div>
			<div><s:checkbox name="templateBean.purcType"  cssClass="group"></s:checkbox><span>采购</span></div>
			<div><s:checkbox name="templateBean.marketType"  cssClass="group"></s:checkbox><span>营销</span></div>
		</div>
		<%--
 		<div class="div_row">
 			<span class="wap_title">类别:</span>
 			<div><s:checkbox name="templateBean.planProperties[%{#s.index}].typeCd1" cssClass="group"></s:checkbox><span>招标</span></div>
 			<div><s:checkbox name="templateBean.planProperties[%{#s.index}].typeCd2" cssClass="group"></s:checkbox><span>采购</span></div>
 		</div>
		 --%>
 		<div class="div_row">
 			<span class="wap_title">工程名称:</span>
 			<span class="wap_value">${item.projectDesc}</span>
 		</div>
 		<div class="div_row">
 			<span class="wap_title">招标范围/采购内容:</span>
 			<span class="wap_value">${item.invPurDesc}</span>
 		</div>
 		<div class="div_row">
 			<span class="wap_title">计算指标(面积/数量):</span>
 			<span class="wap_value">${item.calcIndexSquare}</span>
 		</div>
 		<s:if test="(nodeCd == 129) && isMyApprove==1">
 		<div class="div_row">
			<span class="wap_title">*计算指标(单价):</span>
			<div class="div_input">
					<input edit='true' type="text" validate="required" name="templateBean.planProperties[<s:property value="#s.index" />].calcIndexPrice" value="${item.calcIndexPrice}"/>
			</div>
		</div>
		<div class="div_row">
			<span class="wap_title">*预计金额(元):</span>
			<div class="div_input">
					<input edit='true' type="text" validate="required" name="templateBean.planProperties[<s:property value="#s.index" />].totalPrice" value="${item.totalPrice}"/>
			</div>
		</div>
		<div class="div_row">
			<span class="wap_title">*区域成本意见:</span>
			<div class="div_input">
					<input edit='true' type="text" validate="required" name="templateBean.planProperties[<s:property value="#s.index" />].areaCostOptionDesc" value="${item.areaCostOptionDesc}"/>
			</div>
		</div>
 		</s:if>
 		<s:else>
 		<div class="div_row">
 			<span class="wap_title">*计算指标(单价):</span>
 			<span class="wap_value">${item.calcIndexPrice}</span>
 		</div>
 		<div class="div_row">
 			<span class="wap_title">*预计金额(元):</span>
 			<span class="wap_value">${item.totalPrice}</span>
 		</div>
 		<div class="div_row">
 			<span class="wap_title">*区域成本意见:</span>
 			<span class="wap_value">${item.areaCostOptionDesc}</span>
 		</div>
 		</s:else>
 		<div class="div_row">
 			<span class="wap_title">进场时间:</span>
 			<span class="wap_value">${item.enterDate}</span>
 		</div>
 		<div class="div_row">
 			<span class="wap_title">方案上报:</span>
 			<div><s:checkbox name="templateBean.planProperties[%{#s.index}].isUp1" cssClass="group"></s:checkbox><span>是</span></div>
 			<div><s:checkbox name="templateBean.planProperties[%{#s.index}].isUp2" cssClass="group"></s:checkbox><span>否</span></div>
 		</div>
 		<div class="div_row">
 			<span class="wap_title">提供时间:</span>
 			<span class="wap_value">${item.provideDate}</span>
 		</div>
 		
 		<%--出图时间、定标时间  -Add by liuzhihui 2012-04-11 --%>
 		<div class="div_row">
 			<span class="wap_title">出图时间:</span>
 			<span class="wap_value">${item.plotDate}</span>
 		</div>
 		<div class="div_row">
 			<span class="wap_title">定标完成时间:</span>
 			<span class="wap_value">${item.calibrationCompleteDate}</span>
 		</div>
 		<div class="div_row">
 			<span class="wap_title">定样完成时间:</span>
 			<span class="wap_value">${item.sampleCompleteDate}</span>
 		</div>
 		<div class="div_row">
 			<span class="wap_title">方案审批时间:</span>
 			<span class="wap_value">${item.programApprovalDate}</span>
 		</div>
 		
 		
 		<div class="div_row">
 			<span class="wap_title">备注:</span>
 			<span class="wap_value">${item.remark}</span>
 		</div>
 		</div>
		</s:iterator>
</div>
