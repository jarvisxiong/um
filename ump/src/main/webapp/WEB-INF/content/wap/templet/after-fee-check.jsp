<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>


<%-- 标后核对费用核定单 --%>
<div id="billContent">
	 <s:set var="canEdit">
		<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
		true
		</s:if>
		<s:else>
		false
		</s:else>
	</s:set>
	
	
	<div>
		<div class="div_row">
			<span class="wap_title">合同编号:</span>
			<span class="wap_value">${templateBean.contNo}</span>
		</div>
	
		<div class="div_row">
			<span class="wap_title">合同名称:</span>
			<span class="wap_value">${templateBean.contName}</span>
		</div>
	
		<div class="div_row">
			<span class="wap_title">乙方:</span>
			<span class="wap_value">${templateBean.partB}</span>
		</div>
		
		
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
			(<span class="wap_value">${templateBean.period}</span>)期
		</div>
		
		<div class="div_row">
			<span class="wap_title">面积:</span>
			<span class="wap_value">${templateBean.area}</span>
		</div>
		
		<div class="div_row">
			<span class="wap_title">核对内容:</span>
			<span class="wap_value">${templateBean.checkContent}</span>
		</div>
		
		
		<div class="div_row">
			<span class="wap_title">暂定合同总价(元)(暂定合同总价):</span>
			<span class="wap_value">${templateBean.temporaryPrice}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">原合同单位指标(元/m2):</span>
			<span class="wap_value">${templateBean.contTarget}</span>
		</div>
		 
		 
		<div class="div_label">
			<div class="div_row">
				<span class="wap_title">施工单位上报金额(元):</span>
				<span class="wap_value">${templateBean.projAppMoney}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">相应单位指标(元/m2):</span>
				<span class="wap_value">${templateBean.relProjTarget}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">施工单位上报时间:</span>
				<span class="wap_value">${templateBean.projAppTime}</span>
			</div>
		</div>
		 
		 
		 
		<div class="div_label">
			<div class="div_row">
				<span class="wap_title">咨询机构上报金额(元):</span>
				<span class="wap_value">${templateBean.instAppMoney}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">相应单位指标(元/m2):</span>
				<span class="wap_value">${templateBean.relInstTarget}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">核减率%:</span>
				<span class="wap_value">${templateBean.cutInstRate}</span>
			</div>
		</div>
		
		 
		<div class="div_label">
			<div class="div_row">
				<span class="wap_title">区域公司核对金额(元):</span>
				<span class="wap_value">${templateBean.compCheckMoney}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">相应单位指标(元/m2):</span>
				<span class="wap_value">${templateBean.relComTarget}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">核减率%:</span>
				<span class="wap_value">${templateBean.cutComRate}</span>
			</div>
		</div>
		
		 
		<div class="div_label">
			<div class="div_row">
				<span class="wap_title">目标成本金额(元):</span>
				<span class="wap_value">${templateBean.targetCostMoney}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">相应单位指标(元/m2):</span>
				<span class="wap_value">${templateBean.relCostTarget}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">差异率%:</span>
				<span class="wap_value">${templateBean.differRate}</span>
			</div>
		</div>
		
	
		<div class="div_row">
			<span class="wap_title">编制说明:</span>
			<span class="wap_value">${templateBean.orgExplan}</span>
		</div>
		
		<div class="div_row">
			<span class="wap_title">原合同、标后核对差异说明:</span>
			<span class="wap_value">${templateBean.differExplan}</span>
		</div>
		
		
		<div class="div_label">
			<span class="wap_label">附件</span>
			
			<div class="div_row">
				<span class="wap_title">核对的图纸清单目录</span>
				<div id="show_checkGraphListId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.checkGraphListId}','checkGraphListId','${canEdit}');
				</script>
			</div>
			
			<div class="div_row">
				<span class="wap_title">单位工程预算汇总和指示表</span>
				<div id="show_projBudgetId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.projBudgetId}','projBudgetId','${canEdit}');
				</script>
			</div>
			
			<div class="div_row">
				<span class="wap_title">原合同、标后核对差异附表</span>
				<div id="show_origCheckDiffId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.origCheckDiffId}','origCheckDiffId','${canEdit}');
				</script>
			</div>
			
			<div class="div_row">
				<span class="wap_title">地产公司审核意见汇总</span>
				<div id="show_easteAuditId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.easteAuditId}','easteAuditId','${canEdit}');
				</script>
			</div>
			
			<div class="div_row">
				<span class="wap_title">甲供材料设备数量表</span>
				<div id="show_materialNumId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.materialNumId}','materialNumId','${canEdit}');
				</script>
			</div>
			
			
			<div class="div_row">
				<span class="wap_title">咨询机构成果(含编制说明、主要工程量指标统计表、预算书):</span>
				<span class="wap_value">需邮寄</span>
			</div>
			
			<div class="div_row">
				<span class="wap_title">施工单位上报预算书</span>
				<span class="wap_value">需邮寄</span>
			</div>
		</div>

		<s:if test="statusCd==2">
			<s:hidden id="isEdit" value="true"/>
			<div class="div_row">
				<span class="wap_title">核定费用</span>
				<s:if test="statusCd==2 && userCd==#curUserCd && !isImported">
				<div class="div_input">
					<input class="inputBorder" 
						 	edit='true' validate="required"
							type="text" id="groupMoney" name="templateBean.groupMoney" 
							value="${templateBean.groupMoney}"
					/>
				</div>
				</s:if> 
				<s:else>
				<span class="wap_value">${templateBean.groupMoney}</span>
				</s:else>
			</div>
		</s:if>
	</div>
</div> 