<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 工程结算审批表 --%>

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
<div id="billContent">
	<s:if test="aboutHotel">
		<div class="div_row">
			<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
		</div>
	</s:if>
	<div class="div_row">
			<span class="wap_title"> 合同编号:</span>
			<span class="wap_value">${templateBean.contNo}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">合同名称 :</span>
			<span class="wap_value">${templateBean.contName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">乙方 	 :</span>
			<span class="wap_value">${templateBean.partB}</span> 
	</div>
	<div class="div_row">
			<span class="wap_title"> 项目名称:</span>
			<span class="wap_value">${templateBean.projectName}(${templateBean.projectPeriod})期</span>
	</div>
	<div class="div_row">
			<span class="wap_title"> 面积(m2):</span>
			<span class="wap_value">${templateBean.area}</span>
	</div>
	<div class="div_row">
			<span class="wap_title"> 合同性质:</span>
			<span class="wap_value">${templateBean.contCharacter}</span>
	</div>
	<div class="div_row">
			<span class="wap_title"> 原合同总价:</span>
			<span class="wap_value">${templateBean.oriTotalPrice}</span>
	</div>
	<div class="div_row">
			<span class="wap_title"> 原单方指标(元/m2):</span>
			<span class="wap_value">${templateBean.oriNorm}</span>
	</div>
	<div class="div_row">
			<span class="wap_title"> 已确认合同总价:</span>
			<span class="wap_value">${templateBean.confirmToalPrice}</span>
	</div>
	<div class="div_row">
			<span class="wap_title"> 现单方指标(元/m2):</span>
			<span class="wap_value">${templateBean.confirmNorm}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">累计变更费用(元):</span>
			<span class="wap_value">${templateBean.changeTotal}</span>
	</div>
	<div class="div_row">
			<span class="wap_title"> 累计变更比率(%):</span>
			<span class="wap_value">${templateBean.changeRate}</span>
	</div>
	<div class="div_row">
			<span class="wap_title"> 已支付金额(数字):</span>
			<span class="wap_value">${templateBean.payMoney}</span>
	</div>
	<div class="div_row">
			<span class="wap_title"> 占合同总金额(%):</span>
			<span class="wap_value">${templateBean.tatalRate}</span>
	</div>
	<div class="div_row">
			<span class="wap_title"> 乙方报审金额(元) 	:</span>
			<span class="wap_value">${templateBean.applyMoneyB}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">相应单方指标(元/m2) :</span>
			<span class="wap_value">${templateBean.normB}</span>
	</div>
	
	<div class="div_row">
	<c:choose>
		<c:when test="${isSy}">
		<span class="wap_title">商业公司审核金额(元)</span>
		</c:when>
		<c:otherwise>
		<span class="wap_title">地产公司审核金额(元)</span>
		</c:otherwise>
	</c:choose>
			<span class="wap_value">${templateBean.landApproveMoney}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">相应单方指标(元/m2) 	 :</span>
			<span class="wap_value">${templateBean.normland}</span>
	</div>
	<div class="div_row">
	<c:choose>
					<c:when test="${isSy}">
					<span class="wap_title">立项金额(元)</span>
					</c:when>
					<c:otherwise>
					<span class="wap_title">目标成本(元):</span>
					</c:otherwise>
				</c:choose>
			
			<span class="wap_value">${templateBean.targetMoney}</span>
	</div>
	<div class="div_row">
			<span class="wap_title"> 相应单方指标(元/m2):</span>
			<span class="wap_value">${templateBean.normTarget}</span>
	</div>
	<div class="div_row">
			<span class="wap_title"> 工程结算额及说明:</span>
			<span class="wap_value">${templateBean.remark}</span>
	</div>
	
	<div class="div_label">
		<span class="wap_label">【资料附件】</span>
		<div class="div_row">
			<span class="wap_title"> 一审结算汇总表:</span>
			<div id="show_firstApproveFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.firstApproveFileId}','firstApproveFileId','${canEdit}');
				</script>
		</div>
		<div class="div_row">
			<span class="wap_title"> 结算争议事项汇总表:</span>
			<div id="show_balanceItemFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.balanceItemFileId}','balanceItemFileId','${canEdit}');
				</script>
		</div>
		<div class="div_row">
			<span class="wap_title"> 工程竣工遗留问题确认单:</span>
			<div id="show_leaveProblemFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.leaveProblemFileId}','leaveProblemFileId','${canEdit}');
				</script>
		</div>
		<div class="div_row">
			<span class="wap_title"> 结算审核意见书:</span>
			<div id="show_balanceApproveFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.balanceApproveFileId}','balanceApproveFileId','${canEdit}');
				</script>
		</div>
		<div class="div_row">
			<span class="wap_title"> 甲供材/设备结算核对资料:</span>
			<div id="show_jgcSbFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.jgcSbFileId}','jgcSbFileId','${canEdit}');
				</script>
		</div>
		<div class="div_row">
			<span class="wap_title"> 施工单位预算附件:</span>
			<div id="show_consUnitFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.consUnitFileId}','consUnitFileId','${canEdit}');
				</script>
		</div>
		<div class="div_row">
			<span class="wap_title"> 地产公司成本部审核预算附件:</span>
			<div id="show_landCostFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.landCostFileId}','landCostFileId','${canEdit}');
				</script>
		</div>
		<div class="div_row">
			<span class="wap_title">图纸及目录:</span>
			<div id="show_drawingListFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.drawingListFileId}','drawingListFileId','${canEdit}');
				</script>
		</div>
		<div class="div_row">
			<span class="wap_title">原合同扫描件:</span>
			<div id="show_oriContractFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.oriContractFileId}','oriContractFileId','${canEdit}');
				</script>
		</div>
		<div class="div_row">
			<span class="wap_title">现场工程验收照片(不少于10张，否则退单):</span>
			<div id="show_curPicId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.curPicId}','curPicId','${canEdit}');
				</script>
		</div>
	</div>
	<s:if test="statusCd==2">
		<div class="div_row">
			<span class="wap_title"> 相集团核定费用(元):</span>
			<span class="wap_value">${templateBean.groupMoney}</span>
		</div>
		<div class="div_row">
			<span class="wap_title"> 单方指标(元/m2):</span>
			<span class="wap_value">${templateBean.normGroup}</span>
		</div>
	</s:if>
</div>
