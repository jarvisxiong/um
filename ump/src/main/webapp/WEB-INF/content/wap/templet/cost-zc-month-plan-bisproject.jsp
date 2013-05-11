<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--成本月招采计划(商业公司)-->
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
<div id="billContent">
	<!-- 商业公司财务负责人173/成本控制中心招采副总93 -->
	<s:if test="(nodeCd == 173 || nodeCd==93) && isMyApprove==1">
		<s:hidden id="isEdit" value="true"/>
		<s:set var="isEdit" value="true" />
	</s:if> 
	<%@ include file="template-var.jsp"%>
	<!-- 列表 -->
	<s:iterator value="templateBean.planProperties" var="item" status="s">
	<div class="div_label">
	<div class="div_row">
		<span class="wap_title">商业公司:</span>
		<span class="wap_label">【${item.projectName}】</span>
	</div>
	<div class="div_row">
		<span class="wap_title">类别:</span>
		<div><s:checkbox name="templateBean.planProperties[%{#s.index}].typeCd1" cssClass="group"></s:checkbox><span>工程</span></div>
		<div><s:checkbox name="templateBean.planProperties[%{#s.index}].typeCd2" cssClass="group"></s:checkbox><span>企划</span></div>
		<div><s:checkbox name="templateBean.planProperties[%{#s.index}].typeCd3" cssClass="group"></s:checkbox><span>营运</span></div>
		<div><s:checkbox name="templateBean.planProperties[%{#s.index}].typeCd4" cssClass="group"></s:checkbox><span>行政</span></div>
	</div>
	<div class="div_row">
		<span class="wap_title">名称:</span>
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
	<s:if test="#isEdit=='true'">
	<div class="div_row">
		<span class="wap_title">计算指标(*单价):</span>
		<div class="div_input">
				<input edit="true" type="text" validate="required" name="templateBean.planProperties[<s:property value="#s.index" />].calcIndexPrice" value="${item.calcIndexPrice}"/>
		</div>
	</div>
	<div class="div_row">
		<span class="wap_title">预计金额(*元):</span>
		<div class="div_input">
				<input edit='true' type="text" validate="required" name="templateBean.planProperties[<s:property value="#s.index" />].totalPrice" value="${item.totalPrice}"/>
		</div>
	</div>
	</s:if>
	<s:else>
	<div class="div_row">
		<span class="wap_title">计算指标(*单价):</span>
		<span class="wap_value">${item.calcIndexPrice}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">预计金额(*元):</span>
		<span class="wap_value">${item.totalPrice}</span>
	</div>
	</s:else>
	<div class="div_row">
		<span class="wap_title">工期要求:</span>
		<span class="wap_value">${item.timeLimitDesc}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">进场时间:</span>
		<span class="wap_value">${item.enterDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">定标完成时间:</span>
		<span class="wap_value">${item.bidCompleteDate}</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【附件】</span>
		<div class="div_row">
			<span class="wap_title">立项审批表或方案审批表</span>
			<div id="show_${s.index}lxfaFileId"></div>
			<script type="text/javascript">
			showUploadedFile('${item.lxfaFileId}','${s.index}lxfaFileId','${canEdit}');
			</script>
		</div>
		<div class="div_row">
			<span class="wap_title">预算费用审批表</span>
			<div id="show_${s.index}ysfyFileId"></div>
			<script type="text/javascript">
			showUploadedFile('${item.ysfyFileId}','${s.index}ysfyFileId','${canEdit}');
			</script>
		</div>
		<div class="div_row">
			<span class="wap_title">招标方案、图纸及资料</span>
			<div id="show_${s.index}zbfaFileId"></div>
			<script type="text/javascript">
			showUploadedFile('${item.zbfaFileId}','${s.index}zbfaFileId','${canEdit}');
			</script>
		</div>
	</div>
	<div class="div_row">
		<span class="wap_title">备注:</span>
		<span class="wap_value">${item.remark}</span>
	</div>
	</div>
	</s:iterator>
</div>

