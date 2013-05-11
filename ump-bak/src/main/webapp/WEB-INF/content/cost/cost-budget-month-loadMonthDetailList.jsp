<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--创建月度资金预算抬头按钮 --%>
<s:if test="entity == null || entity.costBudgetMonthId == null || entity.costBudgetMonthId == ''">
	<div id="createdTipPanel" style="line-height: 50px;margin:0 auto;">
		<span>搜索结果: 无月度计划，您可以点击创建。</span>
		<input type="button"
			   class="btn_new btn_add_new" 
			   id="btnCreatMonthBudget" 
			   value="创建本月度计划"
			   style="width: 105px;"
		 	   onclick="createMonthBudget();"
		 />
	</div>	
</s:if>
<s:else>

	<%-- 很重要 --%>
	<input type="hidden" id="costBudgetMonthId" name="costBudgetMonthId" value="${entity.costBudgetMonthId}" /> 
	

	<div id="secondPanel" style="margin:10px; margin-bottom:0px;">
		<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			<s:if test="entity!=null&&entity.statusCd==1">	
				<input type="button" onclick="appendNewRow(this);" id="btnAddRow"    class="btn_new btn_add_new" value="新增无合同预算" style="width: 100px;"  />
				<input type="button" onclick="deleteRow(this);"    id="btnDeleteRow" class="btn_new btn_del_new" value="删除"       style="display: none;"/>		 				    	
			</s:if>			
			<s:else>
				<input type="hidden" id="btnDeleteRow"></input>
			</s:else>
		</security:authorize>
			<%--显示无预算 --%>
			<span style="margin-left: 10px;">显示无预算：</span>
			<input 
				type="checkbox" 
				name="contactVisiable" 
				id="cheContactVisiable" 
				style="line-height: 20px;vertical-align: middle;"
				<s:if test="nocontactVisiable=='show'">
					checked="checked"
				</s:if>				
				onclick="showNocontact(this);"></input>
			<%--显示是否战略 --%>
			<span style="margin-left: 5px;">显示战略：</span>
			<s:select   list="mapStrageFlg" 
						id="strageFlgSelect"
						listKey="key" 
						listValue="value" 
						name="strageFlg"  			
						cssClass="text"
						onchange="showNocontact(this);"
						cssStyle="line-height:20px;width:40px;"/>
			<%--结果提示 --%>
			<span style="margin-left:20px;" id="operateResultTip"> </span>	
		
	</div>
	
	
	
	<div style="clear:both; height:10px;width:100%px;"></div>
	<div class="fakeContainer" >
	<table class="stat_table"  id="MyTable" >
	 	<thead>
	 		<tr class="fixTop"> 		
	 		 <th width="40" nowrap="nowrap" style="background-image: url('')">序号</th>	
	 			 <th width="150" nowrap="nowrap">合同名称</th>
		 		<th width="160" nowrap="nowrap">施工单位（乙方）</th>		 		
		 		<th width="120" nowrap="nowrap">成本科目</th>
		 		<s:if test="entity!=null&&entity.statusCd==1">
		 			<th width="45" nowrap="nowrap">战略</th>
		 		</s:if>
		 		<s:else>
		 			<th width="25" nowrap="nowrap">战略</th>
		 		</s:else>
		 		<th width="150" nowrap="nowrap">合同编号</th>		 		
		 		<th width="120" nowrap="nowrap">合同总价</th>
		 		<th width="120" nowrap="nowrap">实际合同总价</th>
		 		<th width="120" nowrap="nowrap">结算价</th>
		 		<th width="140" nowrap="nowrap">已完产值合计（元）</th>
		 		<th width="140" nowrap="nowrap">其中甲供料产值（元）</th>
		 		<th width="140" nowrap="nowrap">累计应付款合计(元)</th>
		 		<th width="140" nowrap="nowrap">累计实际支付（元）</th>
		 		<th width="120" nowrap="nowrap">累计支付比率%</th>
		 		<th width="140" nowrap="nowrap">本期拟确认产值（元）</th>
		 		<th width="160" nowrap="nowrap">本期产值内甲供料（元）</th>
		 		<th width="160" nowrap="nowrap">本期：资金应付预算（元）</th>
		 		<th width="140" nowrap="nowrap">累计：应付未付（元）</th>
		 		<th width="140" nowrap="nowrap">本期资金预算<br/>(理论计算应付)</th>
		 		<th width="145" nowrap="nowrap">调整本期资金预算<br/>（地产上报）</th>
		 		<%--如果是-1,2，则不现实 --%>
		 		<s:if test="entity!=null&&entity.statusCd==1">
			 	</s:if>
			 	<s:elseif test="entity!=null&&entity.statusCd==2">
			 		<th width="145" nowrap="nowrap">本期资金预算<br/>（区域审核）</th>
			 	</s:elseif>
			 	<s:else>		 		
			 		<th width="145" nowrap="nowrap">本期资金预算<br/>（区域审核）</th>
			 		<th width="145" nowrap="nowrap">本期资金预算（元）<br/>（批准）</th>
		 		</s:else>
		 		<th width="120" nowrap="nowrap">备注</th>
		 		 		
		 	</tr>
	 	</thead>
	 	<tbody>
	 	<s:iterator value="monthDetailPage.result" var="costBudget" status="st">
	 	
	 		<%-- 若为正常导入的合同 --%>
		 	<s:if test='contactId!="-"'>
		 		<tr style="height: 30px;line-height: 30px;" 
		 			monthid="${costBudgetMonth.costBudgetMonthId}" 
		 			detailid="${costBudgetMonthDetailId}" 
		 			sequenceNo="${sequenceNo}" 
		 			line='<s:property value="#st.index+1"></s:property>'
		 		>
		 			<td nowrap="nowrap">
		 				<div class="partHide" id="index_" style="width: 30px;text-align: center;"><s:property value="#st.index+1"></s:property></div>
		 			</td>
		 			<%--合同名称 --%>
			 		<td nowrap="nowrap" title="${contactName}" style="padding:0 5px 0 3px;overflow:hidden;">
			 			<div class="partHide" style="width: 150px;white-space:nowrap;">${contactName}</div>
			 		</td>
			 		<%--施工单位（乙方） --%>
			 		<td nowrap="nowrap" title="${partb}" style="padding:0 5px 0 3px;overflow:hidden;">
			 			<div class="partHide"  style="width: 160px;white-space:nowrap;">${partb}</div>
			 		</td>
		 			<%--成本科目 --%>
			 		<td nowrap="nowrap" title="${subjectName}" style="padding-left: 3px;" align="left">
			 			<div class="partHide"  style="width: 150px;white-space:nowrap;">${subjectName}</div>
			 		</td>
		 			<%--战略 --%>
			 		<td nowrap="nowrap" title="" style="padding-left: 3px;" align="left">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">			 				
							<s:if test="entity!=null&&entity.statusCd==1">	
								<s:select   list="mapStrageFlg"						
											listKey="key" 
											listValue="value" 
											name="strageFlg" 
											value="strageFlg" 			
											cssClass="text"
											al="战略"
											onchange="saveMonthDetailProp(this);"
											line="${st.index+1}"
											budgetid="${costBudgetMonthDetailId}"
											cssStyle="line-height:20px;width:40px;"/>
							</s:if>
							<s:else>
								<div class="partHide"  style="white-space:nowrap;">
									<s:if test="strageFlg==null">
									</s:if>
									<s:elseif test="strageFlg==1">是</s:elseif>
									<s:else>否</s:else>
								</div>
							</s:else>
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
							<div class="partHide inpNoborder"  style="float: left;">
								<s:if test="strageFlg==null">
								</s:if>
								<s:elseif test="strageFlg==1">是</s:elseif>
								<s:else>否</s:else>
							</div>
						</security:authorize>
					</security:authorize>
			 		</td>
			 		<%--合同编号 --%>
			 		<td nowrap="nowrap" title="${contactNo}" style="padding:0 5px 0  3px;overflow:hidden;">
			 			<div class="partHide" style="width: 150px;white-space:nowrap;">${contactNo}</div>
			 		</td>
			 	
			 		<%--合同总价 --%>
			 		<td nowrap="nowrap" align="right" title="${contactTotalAmt}" >
			 			<div class="partHideInner " style="padding-right: 2px;text-align: right;">${contactTotalAmt}</div>
			 		</td>
			 		<%--实际合同总价 --%>
			 		<td nowrap="nowrap" align="right" title="${contactRealTotalAmt}" >
			 			<div class="partHideInner" style="padding-right: 2px;text-align: right;">${contactRealTotalAmt}</div>
			 		</td>
			 		<%-- 结算价--%>
			 		<td nowrap="nowrap" align="right" title="${settleAmt}" >
			 			<div class="partHideInner" style="padding-right: 2px;text-align: right;">${settleAmt}</div>
			 		</td>
			 		<%--已完产值合计（元） --%>
			 		<td nowrap="nowrap" align="right" title="${finishProdTotalAmt}" >
			 			<div class="partHideInner" style="padding-right: 2px;text-align: right;">${finishProdTotalAmt}</div>
			 		</td>
			 		<%-- 其中甲供料产值--%>
			 		<td nowrap="nowrap" align="right" title="${nailFeedWorthAmt}" >
			 			<div class="partHideInner" style="padding-right: 2px;text-align: right;">${nailFeedWorthAmt}</div>
			 		</td>
			 		<%--累计应付款合计(元) --%>
			 		<td nowrap="nowrap" align="right" title="${cumuMustPayTotalAmt}" >
			 			<div class="partHideInner"  style="padding-right: 2px;text-align: right;">${cumuMustPayTotalAmt}</div>
			 		</td>
			 		<%--累计实际支付 --%>
			 		<td nowrap="nowrap" align="right" title="${cumuRealPayTotalAmt}">
			 			<%--如果没有授权“地产财务 A_COST_BUD_DC_FIN”,则不能编辑 --%>
			 			<security:authorize ifNotGranted="A_COST_BUD_DC_FIN">
			 			<div class="partHideInner"  style="padding-right: 2px;text-align: right;">${cumuRealPayTotalAmt}</div>
			 			</security:authorize>
			 			<%--如果有授权“地产财务 A_COST_BUD_DC_FIN”,则能编辑 --%>
			 			<security:authorize  ifAnyGranted="A_COST_BUD_DC_FIN">
			 				<input  <s:if test="entity!=null&&entity.statusCd==1">			 				    	
								 	</s:if>
								 	<s:else>
								 		readonly="readonly"
									 	disabled="disabled"	
								 	</s:else>
			 				title="${cumuRealPayTotalAmt}" tabindex="-1" al="累计实际支付" line="${st.index+1}"  name="cumuRealPayTotalAmt" value="${cumuRealPayTotalAmt}" onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
			 			</security:authorize>
			 		</td>
			 		<%--累计支付比率 --%>
			 		<td nowrap="nowrap" align="right" title="${cumuPaiedRate}" >
			 			<div class="partHideInner "  style="padding-right: 2px;text-align: right;">${cumuPaiedRate}</div>
			 		</td>
			 		<%-- 本期拟确认产值 --%>
			 		<td nowrap="nowrap" align="right" style="padding-right: 3px;">		
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input 
			 				title="${curPeriodPlanConfmAmt}" 
			 				tabindex="-1" al="本期拟确认产值" 
			 				line="${st.index+1}" 
			 				<s:if test="entity!=null&&entity.statusCd==1">			 				    	
						 	</s:if>
						 	<s:else>
						 		readonly="readonly"
							 	disabled="disabled"	
						 	</s:else>
			 				name="curPeriodPlanConfmAmt" value="${curPeriodPlanConfmAmt}" onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text" class="inpNoborder"></input>
			 			</security:authorize>
			 			<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
			 			<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
			 				<div class="partHideInner inpNoborder" >${curPeriodPlanConfmAmt}</div>
			 			</security:authorize>
			 			</security:authorize>
			 		</td>
			 		<%-- 本期产值内甲供料--%>
			 		<td nowrap="nowrap"  align="right" style="padding-right: 3px;">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input 
			 				title="${curPeriodNailFeedAmt }" 
			 				tabindex="-1" 
			 				al="本期产值内甲供料" 
			 				<s:if test="entity!=null&&entity.statusCd==1">			 				    	
						 	</s:if>
						 	<s:else>
						 		readonly="readonly"
							 	disabled="disabled"	
						 	</s:else>
						 	line="${st.index+1}"  name="curPeriodNailFeedAmt"  value="${curPeriodNailFeedAmt }"  onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>	
						</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${curPeriodNailFeedAmt}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<%-- 本期：资金应付预算--%>
			 		<td nowrap="nowrap"  align="right" style="padding-right: 3px;">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">					 				
				 		<input 
				 		title="${curPeriodFundMpayAmt }" 
				 		tabindex="-1" 
				 		al="本期：资金应付预算"
				 		<s:if test="entity!=null&&entity.statusCd==1">			 				    	
					 	</s:if>
					 	<s:else>
					 		readonly="readonly"
						 	disabled="disabled"	
					 	</s:else>
				 		line="${st.index+1}"  name="curPeriodFundMpayAmt"  value="${curPeriodFundMpayAmt }"  onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
						</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${curPeriodFundMpayAmt}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<%--累计：应付未付 --%>
			 		<td nowrap="nowrap"  align="right"  style="padding-right: 3px;">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">					 				
				 		<input 
				 		title="${cumuMustNoPayAmt}" 
				 		tabindex="-1" 
				 		al="累计：应付未付" 
				 		<s:if test="entity!=null&&entity.statusCd==1">			 				    	
					 	</s:if>
					 	<s:else>
					 		readonly="readonly"
						 	disabled="disabled"	
					 	</s:else>
				 		line="${st.index+1}"  name="cumuMustNoPayAmt"  value="${cumuMustNoPayAmt}"  onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
						</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${cumuMustNoPayAmt}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<%-- 本期资金预算(理论计算应付)--%>
			 		<td nowrap="nowrap"  align="right" style="padding-right: 3px;">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">					 				
			 				<input 
			 				title="${curPeriodFundBudgetAmt }" 
			 				tabindex="-1" 
			 				al="本期资金预算(理论计算应付)" 			 				
			 				line="${st.index+1}"  name="curPeriodFundBudgetAmt"  disabled="disabled" readonly="readonly"  value="${curPeriodFundBudgetAmt}"  onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>						 		
						</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${curPeriodFundBudgetAmt}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		
			 		
			 		<%--调整本期资金预算（地产上报） --%>
			 		<td width="125" nowrap="nowrap"  align="right" style="padding-right: 3px;">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">	
			 				<c:set value="${curPeriodFundBudgetAmt}" var="amt"></c:set>			 				
			 				<input  type="text"
			 						title="${curPeriodFundBudgetAmt1 }" 
					 				tabindex="-1" al="调整本期资金预算（地产上报）" 
					 				line="${st.index+1}"  
					 				name="curPeriodFundBudgetAmt1"  
					 				value="${curPeriodFundBudgetAmt1 }"  
					 				onblur="saveMonthDetailPropValue(this);"   
					 				onkeyup="clearNoNum_1(this);"  
					 				budgetid="${costBudgetMonthDetailId}" 					 				
					 				<s:if test="curPeriodFundBudgetAmt1==null">
					 					<c:choose>
							 				<c:when test="amt<0">
							 				class="inpNoborder bigger"
							 				</c:when>
							 				<c:otherwise>
							 					class="inpNoborder"
							 				</c:otherwise>
							 			</c:choose>	
					 				</s:if>			 				
					 				<s:elseif test="curPeriodFundBudgetAmt.compareTo(curPeriodFundBudgetAmt1)>=0">
					 					class="inpNoborder"
					 				</s:elseif> 
					 				<s:else>			 					
					 					class="inpNoborder bigger"
					 				</s:else>
						 			<%--如果是1状态，只有地产角色能编辑 --%>
						 			<s:if test="entity != null && entity.statusCd == 1"> 
						 				<security:authorize ifNotGranted="A_COST_BUD_DC_CHK,A_COST_BUD_DC_CFM">
						 					 readonly="readonly"
									 		 disabled="disabled"
						 				</security:authorize>
						 			</s:if>	
						 			<%--如果是2状态，地产角色,财务不能编辑 --%>
						 			<s:elseif test="entity != null && entity.statusCd == 2">
						 				<security:authorize ifNotGranted="A_COST_BUD_QY_CHK,A_COST_BUD_QY_CFM">
					 						readonly="readonly"
								 			disabled="disabled"
						 				</security:authorize>
						 			</s:elseif>	
						 			<%--如果是3状态，区域角色不能编辑 --%>
						 			<s:elseif test="entity != null && entity.statusCd == 3">
						 				<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					 						readonly="readonly"
								 			disabled="disabled"
						 				</security:authorize>
						 			</s:elseif>
					 				<%--归档状态不能编辑 --%>
					 				<s:elseif test="entity != null && entity.statusCd == 4">
							 				 readonly="readonly"
										 	 disabled="disabled"
						 			</s:elseif> 
			 				></input>						 		
						</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${curPeriodFundBudgetAmt1}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		
			 		<%--本期资金预算（区域审核） --%>
				 	<s:if test="entity!=null && (entity.statusCd == 2 ||entity.statusCd == 3|| entity.statusCd == 4)">
				 		<td width="125" nowrap="nowrap"  align="right" style="padding-right: 3px;">
				 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">					 				
				 				<input 
				 				title="${curPeriodFundBudgetAmt2 }" 
				 				tabindex="-1" 
				 				al="本期资金预算（区域审核）" 
					 			<%--如果是2状态，地产角色,财务不能编辑 --%>
					 			<s:if test="entity != null && entity.statusCd == 2">
					 				<security:authorize ifNotGranted="A_COST_BUD_QY_CHK,A_COST_BUD_QY_CFM">
				 						readonly="readonly"
							 			disabled="disabled"
					 				</security:authorize>
					 			</s:if>	
					 			<%--如果是3状态，区域角色不能编辑 --%>
					 			<s:elseif test="entity != null && entity.statusCd == 3">
					 				<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
				 						readonly="readonly"
							 			disabled="disabled"
					 				</security:authorize>
					 			</s:elseif>
				 				<%--归档状态不能编辑 --%>
				 				<s:elseif test="entity != null && entity.statusCd == 4">
						 				 readonly="readonly"
									 	 disabled="disabled"
					 			</s:elseif> 
				 				line="${st.index+1}"  name="curPeriodFundBudgetAmt2"  value="${curPeriodFundBudgetAmt2 }"  
				 				onblur="saveMonthDetailPropValue(this);"   
				 				onkeyup="clearNoNum_1(this);"  
				 				budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>						 		
							</security:authorize>
							<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
							<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
							<div class="partHideInner inpNoborder" >${curPeriodFundBudgetAmt2}</div>
							</security:authorize>
							</security:authorize>
				 		</td>
				 	</s:if> 
				 		 
			 		<%--本期资金预算（批准） --%>
				 	<s:if test="entity!=null && (entity.statusCd == 3|| entity.statusCd == 4)">
				 		<td width="125" nowrap="nowrap"  align="right" style="padding-right: 3px;">
				 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">					 				
				 				<input 
				 				title="${curPeriodFundBudgetAmt3 }" 
				 				tabindex="-1" 
				 				al="本期资金预算（批准）" 
					 			<%--如果是3状态，区域角色不能编辑 --%>
					 			<s:if test="entity != null && entity.statusCd == 3">
					 				<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
				 						readonly="readonly"
							 			disabled="disabled"
					 				</security:authorize>
					 			</s:if>
				 				<%--归档状态不能编辑 --%>
				 				<s:elseif test="entity != null && entity.statusCd == 4">
						 				 readonly="readonly"
									 	 disabled="disabled"
					 			</s:elseif> 
				 				line="${st.index+1}"  name="curPeriodFundBudgetAmt3"  value="${curPeriodFundBudgetAmt3 }"  onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>						 		
							</security:authorize>
							<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
							<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
							<div class="partHideInner inpNoborder" >${curPeriodFundBudgetAmt3}</div>
							</security:authorize>
							</security:authorize>
				 		</td>
				 	</s:if>
				 		
			 		<%--备注 --%>
			 		<td nowrap="nowrap"  align="right" style="padding-right: 3px;">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input 
			 				title="${memoDesc }" 
			 				al="备注" tabindex="-1"  
			 				line="${st.index+1}" 
			 				<s:if test="entity!=null&&entity.statusCd==1">			 				    	
						 	</s:if>
						 	<s:else>
						 		readonly="readonly"
							 	disabled="disabled"	
						 	</s:else>
			 				name="memoDesc"  value="${memoDesc }"  onblur="saveMonthDetailProp(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
						</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHide inpNoborder" >${memoDesc}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 	</tr>	
		 	</s:if>
		 	<%-- 若是手工添加合同 --%>
		 	<s:else>
			 	<tr style="height: 30px;line-height: 30px;" nocontact="1" monthid="${costBudgetMonth.costBudgetMonthId}" detailid="${costBudgetMonthDetailId}" sequenceNo="${sequenceNo}" >
		 			<td width="40" nowrap="nowrap" title="无合同" 
			 			<s:if test="latestDetailId==costBudgetMonthDetailId">
			 			 class="trClicked"
			 			</s:if>
		 			>
			 			<div id="index" style="width: 30px;text-align: center;">
			 				<s:property value="#st.index+1"></s:property>*
			 			</div>
		 			</td>
		 			<td nowrap="nowrap" title="${contactName}" style="padding-left: 3px;">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input  <s:if test="entity!=null&&entity.statusCd==1">			 				    	
								 	</s:if>
								 	<s:else>
								 		readonly="readonly"
									 	disabled="disabled"	
								 	</s:else>
			 				title="${contactName}" tabindex="-1" al="合同名称" line="${st.index+1}"  name="contactName" value="${contactName}" onblur="saveMonthDetailProp(this);"    budgetid="${costBudgetMonthDetailId}" type="text"  class="nobordeRight" style="float: left;"></input>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHide inpNoborder"  style="float: left;">${contactName}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<td nowrap="nowrap" title="${partb}"  style="padding-left: 3px;">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input  <s:if test="entity!=null&&entity.statusCd==1">			 				    	
								 	</s:if>
								 	<s:else>
								 		readonly="readonly"
									 	disabled="disabled"	
								 	</s:else>
			 				title="${partb}" tabindex="-1" al="施工单位（乙方）" line="${st.index+1}" name="partb" value="${partb}" onblur="saveMonthDetailProp(this);"    budgetid="${costBudgetMonthDetailId}" type="text"  class="nobordeRight" style="float: left;"></input>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHide inpNoborder"  style="float: left;">${partb}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<%--科目 --%>
			 		<td nowrap="nowrap" title="${subjectName}" style="padding-left: 3px;text-align: left;" align="left">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">			 				
							<s:if test="entity!=null&&entity.statusCd==1">
								<s:select 	list="mapTypeByEstate" 
									listValue="value" 
									listKey="key"
									onchange="saveMonthDetailProp(this);"
									name="subjectCd"
									value="subjectCd"					
									title="请选择项目" 
									cssStyle="border-width: 0 0 1px 0; border-bottom:1px solid #aaabb0;"										
									tabindex="-1"
									al="成本科目"
									line="${st.index+1}"
									budgetid="${costBudgetMonthDetailId}"										
									>
								</s:select>									
							</s:if>
							<s:else>
							 	${subjectName}
							</s:else>
							
			 				<%--
			 				<input  title="${subjectName}" tabindex="-1" al="成本科目" line="${st.index+1}"  name="subjectName" value="${subjectName}" onblur="saveMonthDetailProp(this);"   budgetid="${costBudgetMonthDetailId}" type="text"  class=nobordeRight></input>
			 				 --%>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHide inpNoborder"  style="float: left;">${subjectName}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<%--战略--%>
			 		<td nowrap="nowrap" title="" style="padding-left: 3px;text-align: left;" align="left">
			 			<%--否 --%>
			 		</td>
			 		<td nowrap="nowrap" title="${contactNo}" style="padding-left: 3px;">					 			
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input  <s:if test="entity!=null&&entity.statusCd==1">			 				    	
								 	</s:if>
								 	<s:else>
								 		readonly="readonly"
									 	disabled="disabled"	
								 	</s:else> 
			 				title="${contactNo}" tabindex="-1" al="合同编号" line="${st.index+1}"  name="contactNo" value="${contactNo}" onblur="saveMonthDetailProp(this);"     budgetid="${costBudgetMonthDetailId}" type="text"  class="nobordeRight" style="float: left;"></input>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHide inpNoborder"  style="float: left;">${contactNo}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		
			 		<td nowrap="nowrap"  title="${contactTotalAmt}">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 		 		<input  <s:if test="entity!=null&&entity.statusCd==1">			 				    	
								 	</s:if>
								 	<s:else>
								 		readonly="readonly"
									 	disabled="disabled"	
								 	</s:else>
			 		 		title="${contactTotalAmt}" tabindex="-1" al="合同总价" line="${st.index+1}"  name="contactTotalAmt" value="${contactTotalAmt}" onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${contactTotalAmt}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<td nowrap="nowrap" align="right" title="${contactRealTotalAmt}">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input  <s:if test="entity!=null&&entity.statusCd==1">			 				    	
								 	</s:if>
								 	<s:else>
								 		readonly="readonly"
									 	disabled="disabled"	
								 	</s:else>
			 				title="${contactRealTotalAmt}" tabindex="-1" al="实际合同总价" line="${st.index+1}"  name="contactRealTotalAmt" value="${contactRealTotalAmt}" onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${contactRealTotalAmt}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<td nowrap="nowrap" align="right" title="${settleAmt}">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input  <s:if test="entity!=null&&entity.statusCd==1">			 				    	
								 	</s:if>
								 	<s:else>
								 		readonly="readonly"
									 	disabled="disabled"	
								 	</s:else>
			 				title="${settleAmt}" tabindex="-1" al="结算价" line="${st.index+1}"  name="settleAmt" value="${settleAmt}" onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${settleAmt}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<td nowrap="nowrap" align="right" title="${finishProdTotalAmt}" >
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input  <s:if test="entity!=null&&entity.statusCd==1">			 				    	
								 	</s:if>
								 	<s:else>
								 		readonly="readonly"
									 	disabled="disabled"	
								 	</s:else>
			 				title="${finishProdTotalAmt}" tabindex="-1" al="已完产值合计（元）" line="${st.index+1}"  name="finishProdTotalAmt" value="${finishProdTotalAmt}" onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${finishProdTotalAmt}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<td nowrap="nowrap" align="right" title="${nailFeedWorthAmt}">					 		
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input  <s:if test="entity!=null&&entity.statusCd==1">			 				    	
								 	</s:if>
								 	<s:else>
								 		readonly="readonly"
									 	disabled="disabled"	
								 	</s:else>
			 				title="${nailFeedWorthAmt}" tabindex="-1" al="其中甲供料产值" line="${st.index+1}"  name="nailFeedWorthAmt" value="${nailFeedWorthAmt}" onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${nailFeedWorthAmt}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<td nowrap="nowrap" align="right" title="${cumuMustPayTotalAmt}">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 			<input  <s:if test="entity!=null&&entity.statusCd==1">			 				    	
							 	</s:if>
							 	<s:else>
							 		readonly="readonly"
								 	disabled="disabled"	
							 	</s:else>
					 	 title="${cumuMustPayTotalAmt}" tabindex="-1" al="累计应付款合计(元)" line="${st.index+1}"  name="cumuMustPayTotalAmt" value="${cumuMustPayTotalAmt}" onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
		 				<input type="hidden" name="payTotal" tabindex="-1" id="payTotal${st.index+1}" value="${cumuMustPayTotalAmt}"/>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${cumuMustPayTotalAmt}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<td nowrap="nowrap" align="right" title="${cumuRealPayTotalAmt}">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input  <s:if test="entity!=null&&entity.statusCd==1">			 				    	
								 	</s:if>
								 	<s:else>
								 		readonly="readonly"
									 	disabled="disabled"	
								 	</s:else>
			 				title="${cumuRealPayTotalAmt}" tabindex="-1" al="累计实际支付" line="${st.index+1}"  name="cumuRealPayTotalAmt" value="${cumuRealPayTotalAmt}" onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${cumuRealPayTotalAmt}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<td nowrap="nowrap" align="right" title="${cumuPaiedRate}">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input  <s:if test="entity!=null&&entity.statusCd==1">			 				    	
								 	</s:if>
								 	<s:else>
								 		readonly="readonly"
									 	disabled="disabled"	
								 	</s:else>
			 				title="${cumuPaiedRate}" tabindex="-1" al="累计支付比率%" line="${st.index+1}"  name="cumuPaiedRate" value="${cumuPaiedRate}" onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${cumuPaiedRate}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<%-- 本期拟确认产值 --%>
			 		<td nowrap="nowrap" align="right">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input  <s:if test="entity!=null&&entity.statusCd==1">			 				    	
								 	</s:if>
								 	<s:else>
								 		readonly="readonly"
									 	disabled="disabled"	
								 	</s:else>
			 				title="${curPeriodPlanConfmAmt}" tabindex="-1" al="本期拟确认产值" line="${st.index+1}"  name="curPeriodPlanConfmAmt" value="${curPeriodPlanConfmAmt }" onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${curPeriodPlanConfmAmt}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<%-- 本期产值内甲供料--%>
			 		<td nowrap="nowrap"  align="right">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input  <s:if test="entity!=null&&entity.statusCd==1">			 				    	
								 	</s:if>
								 	<s:else>
								 		readonly="readonly"
									 	disabled="disabled"	
								 	</s:else>
			 				title="${curPeriodNailFeedAmt}" tabindex="-1" al="本期产值内甲供料" line="${st.index+1}"  name="curPeriodNailFeedAmt"  value="${curPeriodNailFeedAmt }"  onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${curPeriodNailFeedAmt}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<%-- 本期：资金应付预算--%>
			 		<td nowrap="nowrap"  align="right">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input  <s:if test="entity!=null&&entity.statusCd==1">			 				    	
								 	</s:if>
								 	<s:else>
								 		readonly="readonly"
									 	disabled="disabled"	
								 	</s:else>
			 				title="${curPeriodFundMpayAmt}" tabindex="-1" al="本期：资金应付预算" line="${st.index+1}"  name="curPeriodFundMpayAmt"  value="${curPeriodFundMpayAmt}"  onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${curPeriodFundMpayAmt}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<%--累计：应付未付 --%>
			 		<td nowrap="nowrap"  align="right" >
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input  <s:if test="entity!=null&&entity.statusCd==1">			 				    	
								 	</s:if>
								 	<s:else>
								 		readonly="readonly"
									 	disabled="disabled"	
								 	</s:else>
			 				title="${cumuMustNoPayAmt}" tabindex="-1" al="累计：应付未付" line="${st.index+1}"  name="cumuMustNoPayAmt"  value="${cumuMustNoPayAmt}"  onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${cumuMustNoPayAmt}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		<%-- 本期资金预算(理论计算应付)--%>
			 		<td nowrap="nowrap"  align="right"  style="padding-right: 3px;">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<input title="${curPeriodFundBudgetAmt}" tabindex="-1" al="本期资金预算(理论计算应付)" line="${st.index+1}"  name="curPeriodFundBudgetAmt" disabled="disabled" readonly="readonly" value="${curPeriodFundBudgetAmt}"  onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${curPeriodFundBudgetAmt}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		
			 		
			 		<%--调整本期资金预算（地产上报） --%>
			 		<td width="125" nowrap="nowrap"  align="right" style="padding-right: 3px;">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 				<c:set value="${curPeriodFundBudgetAmt}" var="amt"></c:set>					 								 				
			 				<input  type="text"  
			 						title="${curPeriodFundBudgetAmt1}" 
					 				tabindex="-1" 
					 				al="调整本期资金预算（地产上报）" 
					 				line="${st.index+1}"  
					 				name="curPeriodFundBudgetAmt1"  
					 				value="${curPeriodFundBudgetAmt1 }"  
					 				onblur="saveMonthDetailPropValue(this);"   
					 				onkeyup="clearNoNum_1(this);"  
					 				budgetid="${costBudgetMonthDetailId}"					 					 				
					 				<s:if test="curPeriodFundBudgetAmt1==null">
					 						<c:choose>
							 				<c:when test="amt<0">
							 				class="inpNoborder bigger"
							 				</c:when>
							 				<c:otherwise>
							 					class="inpNoborder"
							 				</c:otherwise>
							 			</c:choose>					 					
					 				</s:if>			 				
					 				<s:elseif test="curPeriodFundBudgetAmt.compareTo(curPeriodFundBudgetAmt1)>=0">
					 					class="inpNoborder"
					 				</s:elseif> 
					 				<s:else>			 					
					 					class="inpNoborder bigger"
					 				</s:else>
						 			<%--如果是1状态，只有地产角色能编辑 --%>
						 			<s:if test="entity != null && entity.statusCd == 1"> 
						 				<security:authorize ifNotGranted="A_COST_BUD_DC_CHK,A_COST_BUD_DC_CFM">
						 					 readonly="readonly"
									 		 disabled="disabled"
						 				</security:authorize>
						 			</s:if>	
						 			<%--如果是2状态，地产角色,财务不能编辑 --%>
						 			<s:elseif test="entity != null && entity.statusCd == 2">
						 				<security:authorize ifNotGranted="A_COST_BUD_QY_CHK,A_COST_BUD_QY_CFM">
					 						readonly="readonly"
								 			disabled="disabled"
						 				</security:authorize>
						 			</s:elseif>	
						 			<%--如果是3状态，区域角色不能编辑 --%>
						 			<s:elseif test="entity != null && entity.statusCd == 3">
						 				<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					 						readonly="readonly"
								 			disabled="disabled"
						 				</security:authorize>
						 			</s:elseif>
					 				<%--归档状态不能编辑 --%>
					 				<s:elseif test="entity != null && entity.statusCd == 4">
							 				 readonly="readonly"
										 	 disabled="disabled"
						 			</s:elseif> 
			 				></input>						 		
						</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHideInner inpNoborder" >${curPeriodFundBudgetAmt1}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 		
			 		<%--本期资金预算（区域审核） --%>
				 	<s:if test="entity!=null && (entity.statusCd == 2 ||entity.statusCd == 3|| entity.statusCd == 4)">
				 		<td width="125" nowrap="nowrap"  align="right" style="padding-right: 3px;">
				 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">					 				
				 				<input  <%--如果是2状态，地产角色,财务不能编辑 --%>
							 			<s:if test="entity != null && entity.statusCd == 2">
							 				<security:authorize ifNotGranted="A_COST_BUD_QY_CHK,A_COST_BUD_QY_CFM">
						 						readonly="readonly"
									 			disabled="disabled"
							 				</security:authorize>
							 			</s:if>	
							 			<%--如果是3状态，区域角色不能编辑 --%>
							 			<s:elseif test="entity != null && entity.statusCd == 3">
							 				<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
						 						readonly="readonly"
									 			disabled="disabled"
							 				</security:authorize>
							 			</s:elseif>
						 				<%--归档状态不能编辑 --%>
						 				<s:elseif test="entity != null && entity.statusCd == 4">
								 				 readonly="readonly"
											 	 disabled="disabled"
							 			</s:elseif> 
				 				title="${curPeriodFundBudgetAmt2 }" tabindex="-1" al="本期资金预算（区域审核）" line="${st.index+1}"  name="curPeriodFundBudgetAmt2"  value="${curPeriodFundBudgetAmt2 }"  onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>						 		
							</security:authorize>
							<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
							<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
							<div class="partHideInner inpNoborder" >${curPeriodFundBudgetAmt2}</div>
							</security:authorize>
							</security:authorize>
				 		</td>
				 	</s:if>
				 	  
			 		<%--本期资金预算（批准） --%>
				 	<s:if test="entity!=null && (entity.statusCd == 3|| entity.statusCd == 4)">
				 		<%-- 本期资金预算（批准）--%>
				 		<td width="125" nowrap="nowrap"  align="right"  style="padding-right: 3px;">
				 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
				 				<input  <%--如果是3状态，区域角色不能编辑 --%>
							 			<s:if test="entity != null && entity.statusCd == 3">
							 				<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
						 						readonly="readonly"
									 			disabled="disabled"
							 				</security:authorize>
							 			</s:if>
						 				<%--归档状态不能编辑 --%>
						 				<s:elseif test="entity != null && entity.statusCd == 4">
								 				 readonly="readonly"
											 	 disabled="disabled"
							 			</s:elseif> 
				 				title="${curPeriodFundBudgetAmt3}" tabindex="-1" al="本期资金预算（批准）" line="${st.index+1}"  name="curPeriodFundBudgetAmt3" value="${curPeriodFundBudgetAmt3 }"  onblur="saveMonthDetailPropValue(this);"   onkeyup="clearNoNum_1(this);"  budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
				 			</security:authorize>
							<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
							<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
							<div class="partHideInner inpNoborder" >${curPeriodFundBudgetAmt3}</div>
							</security:authorize>
							</security:authorize>
				 		</td>
				 	</s:if>
			 		<%--备注 --%>
			 		<td nowrap="nowrap" align="left">
			 			<security:authorize ifAnyGranted="A_COST_BUD_EDIT_M">
			 			<input 
			 			<s:if test="entity!=null&&entity.statusCd==1">			 				    	
					 	</s:if>
					 	<s:else>
					 		readonly="readonly"
						 	disabled="disabled"	
					 	</s:else>
			 			title="${memoDesc }" al="备注" tabindex="-1" line="${st.index+1}"  name="memoDesc"  value="${memoDesc }"  onblur="saveMonthDetailProp(this);"     budgetid="${costBudgetMonthDetailId}" type="text"  class="inpNoborder"></input>
			 			</security:authorize>
						<security:authorize ifNotGranted="A_COST_BUD_EDIT_M">
						<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M">
						<div class="partHide inpNoborder" >${memoDesc}</div>
						</security:authorize>
						</security:authorize>
			 		</td>
			 	</tr>	
		 	</s:else>
		 	</s:iterator>
		 	
		 	
		 	<%--汇总行 --%>
	 		<tr style="height: 30px;line-height: 30px;" >
				<%--
				<td width="40" nowrap="nowrap">
					<div class="partHideInner" style="width: 40px;text-align: center;"></div>
				</td>
				 --%>				
				<%--成本科目 --%>
				<td nowrap="nowrap" colspan="3" title="" style="padding-left: 3px;" align="center">
					<div class="partHideInner"  style="text-align: center;">合计</div>
				</td>
				<%--成本科目 --%>
				<td nowrap="nowrap" title="" style="padding-left: 3px;">
					<div class="partHideInner" style="width: 120px;"></div>
				</td>
				<%--战略--%>
				<td nowrap="nowrap"  title="" style="padding-left: 3px;" align="center">
					<div class="partHideInner"  style="text-align: center;"></div>
				</td>
				<%--合同编号 --%>
				<td nowrap="nowrap" title=""  style="padding-left: 3px;">
					<div class="partHideInner"  style="width: 120px;"></div>
				</td>
				<%--合同总价 --%>
				<td nowrap="nowrap" align="right" title="${totalRow.contactTotalAmt}" >
					<div class="partHideInner " style="padding-right: 2px;text-align: right;" nc="t_contactTotalAmt">${totalRow.contactTotalAmt}</div>
				</td>
				<%--实际合同总价 --%>
				<td nowrap="nowrap" align="right" title="${totalRow.contactRealTotalAmt}" >
					<div class="partHideInner" style="padding-right: 2px;text-align: right;" nc="t_contactRealTotalAmt">${totalRow.contactRealTotalAmt}</div>
				</td>
				<%-- 结算价--%>
				<td nowrap="nowrap" align="right" title="${totalRow.settleAmt}" >
					<div class="partHideInner" style="padding-right: 2px;text-align: right;" nc="t_settleAmt">${totalRow.settleAmt}</div>
				</td>
				<%--已完产值合计（元） --%>
				<td nowrap="nowrap" align="right" title="${totalRow.finishProdTotalAmt}" >
					<div class="partHideInner" style="padding-right: 2px;text-align: right;" nc="t_finishProdTotalAmt">${totalRow.finishProdTotalAmt}</div>
				</td>
				<%-- 其中甲供料产值--%>
				<td nowrap="nowrap" align="right" title="${totalRow.nailFeedWorthAmt}" >
					<div class="partHideInner" style="padding-right: 2px;text-align: right;" nc="t_nailFeedWorthAmt">${totalRow.nailFeedWorthAmt}</div>
				</td>
				<%--累计应付款合计(元) --%>
				<td nowrap="nowrap" align="right" title="${totalRow.cumuMustPayTotalAmt}" >
					<div class="partHideInner"  style="padding-right: 2px;text-align: right;" nc="t_cumuMustPayTotalAmt">${totalRow.cumuMustPayTotalAmt}</div>
				</td>
				<%--累计实际支付 --%>
				<td nowrap="nowrap" align="right" title="${totalRow.cumuRealPayTotalAmt}">
					<div class="partHideInner"  style="padding-right: 2px;text-align: right;" nc="t_cumuRealPayTotalAmt">${totalRow.cumuRealPayTotalAmt}</div>
				</td>
				<%--累计支付比率 --%>
				<td nowrap="nowrap" align="right" title="${totalRow.cumuPaiedRate}" >
					<div class="partHideInner "  style="padding-right: 2px;text-align: right;" nc="t_cumuPaiedRate">${totalRow.cumuPaiedRate}</div>
				</td>
				<%-- 本期拟确认产值 --%>
				<td nowrap="nowrap" align="right" style="padding-right: 3px;" >
					<div class="partHideInner inpNoborder" nc="t_curPeriodPlanConfmAmt"  style="padding-right: 2px;text-align: right;">${totalRow.curPeriodPlanConfmAmt}</div>
				</td>
				<%-- 本期产值内甲供料--%>
				<td nowrap="nowrap"  align="right" style="padding-right: 3px;" >
					<div class="partHideInner inpNoborder" nc="t_curPeriodNailFeedAmt"  style="padding-right: 2px;text-align: right;">${totalRow.curPeriodNailFeedAmt}</div>
				</td>
				<%-- 本期：资金应付预算--%>
				<td nowrap="nowrap"  align="right" style="padding-right: 3px;">
					<div class="partHideInner inpNoborder" nc="t_curPeriodFundMpayAmt"  style="padding-right: 2px;text-align: right;">${totalRow.curPeriodFundMpayAmt}</div>
				</td>
				<%--累计：应付未付 --%>
				<td nowrap="nowrap"  align="right"  style="padding-right: 3px;">
					<div class="partHideInner inpNoborder" nc="t_cumuMustNoPayAmt"  style="padding-right: 2px;text-align: right;">${totalRow.cumuMustNoPayAmt}</div>
				</td>
				<%-- 本期资金预算(理论计算应付)--%>
				<td nowrap="nowrap"  align="right" style="padding-right: 3px;">
					<div class="partHideInner inpNoborder" nc="t_curPeriodFundBudgetAmt"  style="padding-right: 2px;text-align: right;">${totalRow.curPeriodFundBudgetAmt}</div>
				</td>
				<%-- 调整本期资金预算（地产上报）--%>
				<td width="125" nowrap="nowrap"  align="right" style="padding-right: 3px;">
					<div class="partHideInner inpNoborder" nc="t_curPeriodFundBudgetAmt1"  style="padding-right: 2px;text-align: right;">${totalRow.curPeriodFundBudgetAmt1}</div>
				</td>
				<%--如果是待地产上报-1，则不现实 --%>			 		
			 		<s:if test="entity!=null&&entity.statusCd==1">
				 	</s:if>
				 	<s:elseif test="entity!=null&&entity.statusCd==2">
				 		<%-- 本期资金预算（区域审核）--%>
						<td width="125" nowrap="nowrap"  align="right" style="padding-right: 3px;">
							<div class="partHideInner inpNoborder" nc="t_curPeriodFundBudgetAmt2"  style="padding-right: 2px;text-align: right;">${totalRow.curPeriodFundBudgetAmt2}</div>
						</td>
				 	</s:elseif>
			 	<s:else>
			 		<%-- 本期资金预算（区域审核）--%>
					<td width="125" nowrap="nowrap"  align="right" style="padding-right: 3px;">
						<div class="partHideInner inpNoborder" nc="t_curPeriodFundBudgetAmt2"  style="padding-right: 2px;text-align: right;">${totalRow.curPeriodFundBudgetAmt2}</div>
					</td>
					<%-- 本期资金预算（批准）--%>
					<td width="125" nowrap="nowrap"  align="right" style="padding-right: 3px;">
						<div class="partHideInner inpNoborder" nc="t_curPeriodFundBudgetAmt3"  style="padding-right: 2px;text-align: right;">${totalRow.curPeriodFundBudgetAmt3}</div>
					</td>
			 	</s:else>	
					
				
				<%--备注 --%>
				<td nowrap="nowrap"  align="right" style="padding-right: 3px;">
					<div class="partHideInner inpNoborder" >${totalRow.memoDesc}</div>
				</td>
			</tr>	
	 	</tbody>	 	
	</table>
	</div>
</s:else>
<div style="clear:both; height:10px;width:100%px;"></div>

<div id="commitBtn" style="clear:left;margin: 0px;">
	<s:if test="entity!=null&&entity.statusCd==1">
		<span>
		当前状态：待地产上报
		</span>
		<security:authorize ifAnyGranted="A_COST_BUD_DC_CFM">
			<input type="button" onclick="commitBudget(this,'1');" style="margin:5px;cursor:pointer;" class="searchBtn" value="提交" title="进入待区域审核"/>
		</security:authorize>
	</s:if>
	<s:elseif  test="entity!=null&&entity.statusCd==2">
		<span>
			当前状态：待区域审核
		</span>
		<security:authorize ifAnyGranted="A_COST_BUD_QY_CFM">
			<input type="button" onclick="commitBudget(this,'2');" style="margin:5px;cursor:pointer;" class="searchBtn" value="提交" title="进入待批准"/>
			<input type="button" onclick="rejectBudget(this,'1');" style="margin:5px;cursor:pointer;" class="searchBtn" value="驳回" title="进入待地产上报"/>
		</security:authorize>
	</s:elseif>
	<s:elseif  test="entity!=null&&entity.statusCd==3">
		<span>
			当前状态：待批准
		</span>
		<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">
			<input type="button" onclick="commitBudget(this,'3');" style="margin:5px;cursor:pointer;" class="searchBtn" value="提交" title="进入归档"/>
			<input type="button" onclick="rejectBudget(this,'2');" style="margin:5px;cursor:pointer;" class="searchBtn" value="驳回" title="进入待区域审核"/>
		</security:authorize>
	</s:elseif>
	<s:elseif test="entity!=null&&entity.statusCd==4">		
			<span style="padding: 5px;margin-top: 5px;">
				当前状态：归档
			</span>
	</s:elseif>	
</div>
 <script type="text/javascript">
 $(function(){
	//合同自动选择时间监听和处理
	$("#quickSearchContNo").quickSearch(
		(_ctx + '/cost/cost-budget!quickSearchForCost.action'),
		['contNo', 'contName'], //显示的字段
		{						//以下都是回填字段 (source->dest)
			contLedgerId	: "contLedgerId",//合同ID
			contNo			: "contNo",//合同编号
			contName		: "contName",//合同名称
			projectCd		: "projectCd",//项目代码
			projectName		: "projectName",//项目名称
			partB			: "partB",//乙方
			totalPrice		: "totalPrice",//合同总价
			clearPrice		: "clearPrice",//结算价
			updateTotal		: "confirmToalPrice",//实际合同总价
			hasFinishedPrice: "completeNum",//已完产值合计（元）	
			jiaMatePrice	: "matieralNum",//其中甲供料产值
			actMustPayTotal	: "currentAdd",//累计应付款合计(元)
			actHasPayTotal	: "currentPay",//累计实际支付
			hasPayRate		: "payRate",//累计支付比率%
			contTypeCd2		: "contTypeCd2",//成本科目
			currentPayBudget: "currentPayBudget"//本期：资金应付预算
		},
		{},
		function(map) {
			//合同ID
			var contLedgerId = map[0].attributes['contledgerid'].value;
			//成本科目
			var contTypeCd2 = map[0].attributes['contTypeCd2'].value;
			$("#contTypeCd2").html(contTypeCd2);
			//合同编号
			var contNo = map[0].attributes['contno'].value;
			$("#contNo").html(contNo);
			//合同名称
			var contName = map[0].attributes['contname'].value;
			$("#contName").html(contName);
			//施工单位（乙方）
			var partB = map[0].attributes['partb'].value;
			$("#partB").html(partB);
			//合同总价
			var totalPrice = map[0].attributes['totalprice'].value;
			$("#totalPrice").html(totalPrice);
			//结算价
			var clearPrice = map[0].attributes['clearprice'].value;
			$("#clearPrice").html(clearPrice);
			//已完产值合计（元）
			var hasFinishedPrice = map[0].attributes['completenum'].value;
			$("#hasFinishedPrice").html(hasFinishedPrice);
			//其中甲供料产值
			var jiaMatePrice = map[0].attributes['matieralnum'].value;
			$("#jiaMatePrice").html(jiaMatePrice);
			//累计应付款合计(元)
			var actMustPayTotal = map[0].attributes['currentadd'].value;
			$("#actMustPayTotal").html(actMustPayTotal);
			///累计实际支付
			var actHasPayTotal = map[0].attributes['currentpay'].value;
			$("#actHasPayTotal").html(actHasPayTotal);
			//累计支付比率%
			var hasPayRate = map[0].attributes['payrate'].value;
			$("#hasPayRate").html(hasPayRate);
			
			saveContBudget(contNo);//见: cost-budget.js
		}, 
		{
			planSectionId : 'planSectionId',//后台:前台id
			planYearMonth : 'planYearMonth'
		}
	);
	
	//新增无合同事件
	$("tr[detailid]").click(function(){
		var btn1 = $("#btnAddRow");
		var btn2 = $("#btnDeleteRow");
		//月度资金状态
		var monthStatus=0;
		<s:if test="entity!=null">
			monthStatus=${entity.statusCd};
		</s:if>		
		//如果是无合同和在“待地产上报”状态下，才显示删除按钮
		if($(this).attr('nocontact')==1&&monthStatus==1){
			//显示删除按钮
			showBtnDelete();
			}
		else{
			$("#btnDeleteRow").hide();
			}
		//取消所有焦点
		$("tr[detailid]").each(function(){
			$(this).children().eq(0).removeClass('trClicked');
		});
		var o = $(this);
		//将本焦点激活着色
		$("tr[detailid='"+o.attr('detailid')+"']").each(function(){
			$(this).children().eq(0).addClass('trClicked').siblings().removeClass('trClicked');
			});		
		btn1.attr("chooseId",o.attr('detailid'));
		btn1.attr("monthid",o.attr('monthid'));
		btn1.attr("sequenceNo",o.attr('sequenceNo'));
		btn1.attr("detailid",o.attr('detailid'));
		
		btn2.attr("chooseId",o.attr('detailid'));
		btn2.attr("monthid",o.attr('monthid'));
		btn2.attr("sequenceNo",o.attr('sequenceNo'));
		btn2.attr("detailid",o.attr('detailid'));
	});
});
 </script>
