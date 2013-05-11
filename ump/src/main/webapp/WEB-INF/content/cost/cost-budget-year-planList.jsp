<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="com.hhz.ump.util.DateUtil" %>


<div id="cellUnitPanel" style="margin: 5px 15px;">
	<span>
		<font>单位：万元 </font>
		<font style="margin-left:200px;">月度分解 </font>
	</span>
	<%-- 操作提示 --%>
	<span id="operateResultTip" style="color:red;margin-left:30px;"></span>
</div>

<div style="clear:both; height:10px;width:100%px;"></div>
<div class="fakeContainer" >		
<table class="stat_table" id="MyTable">
 	<thead>
 		<tr> 		
 		 <th width="40" nowrap="nowrap" style="background-image: url('')">序号</th>	
 		 	<th width="60" nowrap="nowrap">年份</th>		 		
	 		<th width="160" nowrap="nowrap">项目名称</th>
	 		<th width="120" nowrap="nowrap">目标成本</th>
	 		<th width="175" nowrap="nowrap">本年度之前已付额</th>
	 		<th width="120" nowrap="nowrap">本年度预算批准额</th>
	 		<th width="120" nowrap="nowrap">备注</th>
	 		<th width="120" nowrap="nowrap">1月份</th>
	 		<th width="120" nowrap="nowrap">2月份</th>
	 		<th width="120" nowrap="nowrap">3月份</th>
	 		<th width="120" nowrap="nowrap">4月份</th>
	 		<th width="120" nowrap="nowrap">5月份</th>
	 		<th width="120" nowrap="nowrap">6月份</th>
	 		<th width="120" nowrap="nowrap">7月份</th>
	 		<th width="120" nowrap="nowrap">8月份</th>
	 		<th width="120" nowrap="nowrap">9月份</th>
	 		<th width="120" nowrap="nowrap">10月份</th>
	 		<th width="120" nowrap="nowrap">11月份</th>
	 		<th width="120" nowrap="nowrap">12月份</th>
	 		 		
	 	</tr>
 	</thead>
 	<tbody>
 	<s:if test="(page.result.size() +uncreateYears.size()) > 0">
 	<s:iterator value="page.result" status="st">
			<c:set value="<%=Long.parseLong(DateUtil.getCurrYear())%>" var="curYear"></c:set>
			<c:set value="${budgetYear}"  var="comYear"></c:set>	
			<c:set value="<%=Integer.parseInt(DateUtil.getCurrMonth())%>"  var="curMonth"></c:set>	
	 	<tr style="height: 30px;line-height: 30px;" tb="${costBudgetYearId }" line="<s:property value="#st.index+1"></s:property>">
	 			<td width="40" nowrap="nowrap"><div class="partHideInner" id="contTypeCd2" style="width: 40px;text-align: center;"><s:property value="#st.index+1"></s:property></div></td>
	 			<td width="60" style="text-align: center;">${budgetYear}</td>
	 			<%--项目名称 --%>
		 		<td nowrap="nowrap" title="${costProjectSection.sectionName}" style="text-align: left;padding-left: 5px;">
		 			<div class="partHideInner" id="contTypeCd2" style="width: 160px;">${costProjectSection.sectionName}</div>
		 		</td>
		 		<%--目标成本 --%>
		 		<td nowrap="nowrap" title="${targetCostAmt}" style="text-align: left;padding-left: 2px;" >
	 				<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">	
		 				<input al="目标成本"  tabindex="-1"  name="targetCostAmt" sn="${costProjectSection.sectionName}" title="${targetCostAmt}" line="${st.index+1}" value="${targetCostAmt}" onblur="saveYearPropValue(this);"  onkeyup="clearNoNum_1(this);"    costBudgetYearId="${costBudgetYearId}" type="text" class="inpNoborder"></input>
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N,A_COST_BUD_VIEW_N">
						<div  class="partHideInner" style="text-align:right;padding-left: 2px;">${targetCostAmt}</div>
					</security:authorize>
					</security:authorize>		 		
		 		</td>
		 		<%--本年度之前已付额 --%>
		 		<td nowrap="nowrap" title="${preYearPaiedAmt}"  >		 		
	 				<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">				 				
		 				<input 
		 				al="本年度之前已付额"
		 				<s:if test="cfmPreYearAmtFlg==1">
			 					readonly="readonly"	
			 					disabled="disabled"		 					
			 				</s:if>
			 				<s:else>
				 				 onblur="saveYearPropValue(this);"   onkeyup="clearNoNum_1(this);" 
			 				</s:else>   
		 				tabindex="-1"  name="preYearPaiedAmt" sn="${costProjectSection.sectionName}"  title="${preYearPaiedAmt}" line="${st.index+1}"  value="${preYearPaiedAmt}" costBudgetYearId="${costBudgetYearId}" type="text" class="inpNoborder"></input>
		 				
		 				<input		 				
			 				<s:if test="cfmPreYearAmtFlg==1">
			 					class="btn_new btn_gray_new"
			 				</s:if>
			 				<s:else>
				 				class="btn_new btn_orange"
				 				onclick="savePreYearPaiedAmt(this);" 
			 				</s:else>
		 				type="button"		 				
		 				value="确认" style="width: 50px;" title="注意：保存后不能修改" costBudgetYearId="${costBudgetYearId }" />		
		 				
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N,A_COST_BUD_VIEW_N">
						<div  class="partHideInner " style="text-align:right;padding-right: 1px;">${preYearPaiedAmt}</div>
					</security:authorize>
					</security:authorize>
		 		</td>
		 		<%--本年度预算批准额 --%>
		 		<td nowrap="nowrap" title="${groupTotalAmt}" >
		 			<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">					 				
		 				<input 
		 				al="本年度预算批准额"  
		 				tabindex="-1" 
		 				name="groupTotalAmt"  
		 				readonly="readonly" 
		 				disabled="disabled"
		 				sn="${costProjectSection.sectionName}"  
		 				title="${groupTotalAmt}" 
		 				line="${st.index+1}" 
		 				value="${groupTotalAmt}" 	 				
		 				costBudgetYearId="${costBudgetYearId}" 
		 				type="text" 
		 				class="inpNoborder"></input>
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N,A_COST_BUD_VIEW_N">
						<div  class="partHideInner" style="text-align:right;padding-right: 1px;">${groupTotalAmt}</div>
					</security:authorize>
					</security:authorize>
		 		</td>
		 		
		 		<%--备注--%>
		 		<td nowrap="nowrap" align="right" title="${memoDesc}">
					<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">				 				
		 				<input al="备注" name="memoDesc"  tabindex="-1" sn="${costProjectSection.sectionName}"  title="${memoDesc}" line="${st.index+1}"  value="${memoDesc}" onblur="saveYearProp(this);"  costBudgetYearId="${costBudgetYearId}" type="text" class="inpNoborder"></input>
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N,A_COST_BUD_VIEW_N">
						<div  class="partHide" style="text-align:left;padding-left: 2px;">${memoDesc}</div>
					</security:authorize>
					</security:authorize>
		 		</td>
		 		<%--1月份--%>
		 		<td nowrap="nowrap" align="right" title="${planAmtM01 }" >	
					<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">							 				
		 				<input 
		 				name="planAmtM01" 
		 				<%-- 
		 				<c:if test="${comYear<curYear||(comYear == curYear&&curMonth>=1)}">
		 					readonly="readonly" disabled="disabled"
		 				</c:if>	
		 				--%>
		 				onblur="saveYearPropValue(this);" tabindex="-1" al="1月份" sn="${costProjectSection.sectionName}"  title="${planAmtM01}" line="${st.index+1}"  value="${planAmtM01}"   onkeyup="clearNoNum_1(this);"  costBudgetYearId="${costBudgetYearId}" type="text" class="inpNoborder"></input>
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N,A_COST_BUD_VIEW_N">
						<div  class="partHideInner"  style="text-align:right;padding-right: 1px;">${planAmtM01}</div>
					</security:authorize>
					</security:authorize>
		 		</td>
		 		<%--2月份--%>
		 		<td nowrap="nowrap" align="right" title="${planAmtM02}" >
					<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">					 				
		 				<input 
		 				name="planAmtM02" 
		 				<%-- <c:if test="${comYear<curYear||(comYear == curYear&&curMonth>=2)}">
		 					readonly="readonly" disabled="disabled"
		 				</c:if>	
		 				--%>
		 				tabindex="-1" al="2月份" sn="${costProjectSection.sectionName}"  title="${planAmtM02}" line="${st.index+1}"  value="${planAmtM02}" onblur="saveYearPropValue(this);"   onkeyup="clearNoNum_1(this);"  costBudgetYearId="${costBudgetYearId}" type="text" class="inpNoborder"></input>
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N,A_COST_BUD_VIEW_N">
						<div  class="partHideInner "  style="text-align:right;padding-right: 1px;">${planAmtM02}</div>
					</security:authorize>
					</security:authorize>
		 		</td>
		 		<%--3月份--%>
		 		<td nowrap="nowrap" align="right" title="${planAmtM03 }" >
		 			<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">					 				
		 				<input 
		 				name="planAmtM03" 
		 				<%--<c:if test="${comYear<curYear||(comYear == curYear&&curMonth>=3)}">
		 					readonly="readonly" disabled="disabled"
		 				</c:if>	--%>
		 				tabindex="-1" al="3月份" sn="${costProjectSection.sectionName}"  title="${planAmtM03}" line="${st.index+1}"  value="${planAmtM03}" onblur="saveYearPropValue(this);"   onkeyup="clearNoNum_1(this);"  costBudgetYearId="${costBudgetYearId}" type="text" class="inpNoborder"></input>
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N,A_COST_BUD_VIEW_N">
						<div  class="partHideInner "  style="text-align:right;padding-right: 1px;">${planAmtM03}</div>
					</security:authorize>
					</security:authorize>
		 		</td>
		 		<%--4月份--%>
		 		<td nowrap="nowrap" align="right" title="${planAmtM04 }">
		 			<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">					 				
		 				<input 
		 				name="planAmtM04" 
		 				<%--<c:if test="${comYear<curYear||(comYear == curYear&&curMonth>=4)}">
		 					readonly="readonly" disabled="disabled"
		 				</c:if>	--%>
		 				tabindex="-1" al="4月份" sn="${costProjectSection.sectionName}"  title="${planAmtM04}" line="${st.index+1}"  value="${planAmtM04}" onblur="saveYearPropValue(this);"   onkeyup="clearNoNum_1(this);"  costBudgetYearId="${costBudgetYearId}" type="text" class="inpNoborder"></input>
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N,A_COST_BUD_VIEW_N">
						<div  class="partHideInner "  style="text-align:right;padding-right: 1px;">${planAmtM04}</div>
					</security:authorize>
					</security:authorize>
		 		</td>
		 		<%--5月份--%>
		 		<td nowrap="nowrap" align="right" title="${planAmtM05 }" >
		 			<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">					 				
		 				<input 
		 				name="planAmtM05" 
		 				<c:if test="${comYear<curYear||(comYear == curYear&&curMonth>=5)}">
		 					readonly="readonly" disabled="disabled"
		 				</c:if>	
		 				tabindex="-1" al="5月份" sn="${costProjectSection.sectionName}"  title="${planAmtM05}" line="${st.index+1}"  value="${planAmtM05}" onblur="saveYearPropValue(this);"   onkeyup="clearNoNum_1(this);"  costBudgetYearId="${costBudgetYearId}" type="text" class="inpNoborder"></input>
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N,A_COST_BUD_VIEW_N">
						<div  class="partHideInner "  style="text-align:right;padding-right: 1px;">${planAmtM05}</div>
					</security:authorize>
					</security:authorize>
		 		</td>
		 		<%--6月份--%>
		 		<td nowrap="nowrap" align="right" title="${ planAmtM06}">
		 			<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">					 				
		 				<input 
		 				name="planAmtM06" 
		 				<c:if test="${comYear<curYear||(comYear == curYear&&curMonth>=6)}">
		 					readonly="readonly" disabled="disabled"
		 				</c:if>	
		 				tabindex="-1" al="6月份" sn="${costProjectSection.sectionName}"  title="${planAmtM06}" line="${st.index+1}"  value="${planAmtM06}" onblur="saveYearPropValue(this);"   onkeyup="clearNoNum_1(this);"  costBudgetYearId="${costBudgetYearId}" type="text" class="inpNoborder"></input>
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N,A_COST_BUD_VIEW_N">
						<div  class="partHideInner "  style="text-align:right;padding-right: 1px;">${planAmtM06}</div>
					</security:authorize>
					</security:authorize>
		 		</td>
		 		<%--7月份--%>
		 		<td nowrap="nowrap" align="right"title="${planAmtM07 }"  >
		 			<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">					 				
		 				<input 
		 				name="planAmtM07" 
		 				<c:if test="${comYear<curYear||(comYear == curYear&&curMonth>=7)}">
		 					readonly="readonly" disabled="disabled"
		 				</c:if>
		 				tabindex="-1" al="7月份" sn="${costProjectSection.sectionName}"  title="${planAmtM07}" line="${st.index+1}"  value="${planAmtM07}" onblur="saveYearPropValue(this);"   onkeyup="clearNoNum_1(this);"  costBudgetYearId="${costBudgetYearId}" type="text" class="inpNoborder"></input>
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N,A_COST_BUD_VIEW_N">
						<div  class="partHideInner "  style="text-align:right;padding-right: 1px;">${planAmtM07}</div>
					</security:authorize>
					</security:authorize>
		 		</td>
		 		<%--8月份--%>
		 		<td nowrap="nowrap" title="${planAmtM08 }">
		 			<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">					 				
		 				<input 
		 				name="planAmtM08" 
		 				<c:if test="${comYear<curYear||(comYear == curYear&&curMonth>=8)}">
		 					readonly="readonly" disabled="disabled"
		 				</c:if>
		 				tabindex="-1" al="8月份" sn="${costProjectSection.sectionName}"  title="${planAmtM08}" line="${st.index+1}"  value="${planAmtM08}"  onblur="saveYearPropValue(this);"   onkeyup="clearNoNum_1(this);"  costBudgetYearId="${costBudgetYearId}" type="text" class="inpNoborder"></input>
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N,A_COST_BUD_VIEW_N">
						<div  class="partHideInner "  style="text-align:right;padding-right: 1px;">${planAmtM08}</div>
					</security:authorize>
					</security:authorize>
		 		</td>
		 		<%--9月份--%>
		 		<td nowrap="nowrap"  title="${ planAmtM09}">
		 			<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">					 				
		 				<input 
		 				name="planAmtM09" 
		 				<c:if test="${comYear<curYear||(comYear == curYear&&curMonth>=9)}">
		 					readonly="readonly" disabled="disabled"
		 				</c:if>
		 				tabindex="-1" al="9月份" sn="${costProjectSection.sectionName}"  title="${planAmtM09}" line="${st.index+1}"  value="${planAmtM09}"  onblur="saveYearPropValue(this);"   onkeyup="clearNoNum_1(this);"  costBudgetYearId="${costBudgetYearId}" type="text" class="inpNoborder"></input>
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N,A_COST_BUD_VIEW_N">
						<div  class="partHideInner "  style="text-align:right;padding-right: 1px;">${planAmtM09}</div>
					</security:authorize>
					</security:authorize>
		 		</td>
		 		<%--10月份--%>
		 		<td nowrap="nowrap"  align="right" title="${planAmtM10}">
		 		<%--<input name="cumuMustNoPayAmt"  value="${cumuMustNoPayAmt }"  onblur="modifyBudget(this);"  budgetid="${costBudgetCurPeriodId}" type="text" class="inpNoborder"></input> --%>
		 			<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">					 				
		 				<input 
		 				name="planAmtM10" 
		 				<c:if test="${comYear<curYear||(comYear == curYear&&curMonth>=10)}">
		 					readonly="readonly" disabled="disabled"
		 				</c:if>
		 				tabindex="-1" al="10月份" sn="${costProjectSection.sectionName}"  title="${planAmtM10}" line="${st.index+1}"  value="${planAmtM10}" onblur="saveYearPropValue(this);"   onkeyup="clearNoNum_1(this);"  costBudgetYearId="${costBudgetYearId}" type="text" class="inpNoborder"></input>
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N,A_COST_BUD_VIEW_N">
						<div  class="partHideInner "  style="text-align:right;padding-right: 1px;">${planAmtM10}</div>
					</security:authorize>
					</security:authorize>
		 		</td>
		 		<%--11月份--%>
		 		<td nowrap="nowrap"  title="${ planAmtM11}">
		 			<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">					 				
		 				<input 
		 				name="planAmtM11" 
		 				<c:if test="${comYear<curYear||(comYear == curYear&&curMonth>=11)}">
		 					readonly="readonly" disabled="disabled"
		 				</c:if>
		 				tabindex="-1" al="11月份"  sn="${costProjectSection.sectionName}"  title="${ planAmtM11}" line="${st.index+1}"  value="${ planAmtM11}"  onblur="saveYearPropValue(this);"   onkeyup="clearNoNum_1(this);"  costBudgetYearId="${costBudgetYearId}" type="text" class="inpNoborder"></input>
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N,A_COST_BUD_VIEW_N">
						<div  class="partHideInner "  style="text-align:right;padding-right: 1px;">${planAmtM11}</div>
					</security:authorize>
					</security:authorize>
		 		</td>
		 		<%--12月份--%>
		 		<td nowrap="nowrap"  title="${planAmtM12 }">
		 			<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">					 				
		 				<input 
		 				name="planAmtM12" 
		 				<c:if test="${comYear<curYear||(comYear == curYear&&curMonth>=12)}">
		 					readonly="readonly" disabled="disabled"
		 				</c:if>
		 				tabindex="-1"s al="12月份" sn="${costProjectSection.sectionName}" title="${planAmtM12 }" line="${st.index+1}"   value="${planAmtM12 }"  onblur="saveYearPropValue(this);"    onkeyup="clearNoNum_1(this);" costBudgetYearId="${costBudgetYearId}" type="text" class="inpNoborder"></input>
					</security:authorize>
					<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N,A_COST_BUD_VIEW_N">
						<div  class="partHideInner "  style="text-align:right;padding-right: 1px;">${planAmtM12}</div>
					</security:authorize>
					</security:authorize>
		 		</td>
	 	</tr>	
 	</s:iterator>
 	<s:if test="uncreateYears!=null">
	<s:iterator value="uncreateYears" status="st">
		<tr style="height: 30px; line-height: 30px;" title="未创建年度计划" line="<s:property value="#st.index+1+page.result.size()"></s:property>" >
			<td width="40" nowrap="nowrap">
				<div class="partHideInner" id="contTypeCd2"
					style="width: 40px; text-align: center;">
					<s:property value="#st.index+1+page.result.size()"></s:property>
				</div>
			</td>
			<td width="60" style="text-align: center;">${year}</td>
			<%--项目名称 --%>
			<td nowrap="nowrap" title="${sectionName}"
				style="text-align: left; padding-left: 5px;">
				<div class="partHideInner" style="width: 120px;">${sectionName}</div>
			</td>
			<td colspan="16" align="left" style="padding-left: 10px;">
				<div style="float: left;">
					<span style="padding-right: 10px;">未创建</span>
					<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N">
					<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">
						<input type="button"
							onclick="createYearBudget('${year}','${costProjectSectionId}');"
							style="margin: 0 auto; width: 50px; margin-bottom: 0px; text-align: center;"
							value="创建" title="创建本年度计划" id="newYearBudget"
							sectionId='${costProjectSectionId}' class="searchBtn" />
					</security:authorize>
					</security:authorize>
				</div>
			</td>
		</tr>
	</s:iterator>
	</s:if>
	</s:if>
	<s:else>
		<tr>
			<td colspan="6" style="text-align: center;">查无年计划，请联系管理员授权项目权限！</td>
			<td colspan="12">&nbsp;</td>
		</tr>
	</s:else>	

 	
 	<s:if test="totalRow!=null">
 		<tr style="height: 30px;line-height: 30px;background-color: #E4E7EC;">	 			
	 			<td colspan="3"  style="text-align: center;">合计</td>		 		
		 		<%--目标成本 --%>
		 		<td nowrap="nowrap" title="${totalRow.targetCostAmt}" style="text-align: left;padding-left: 2px;" >	 				
					<div  class="partHideInner" style="text-align:right;padding-right: 2px;" nc="t_targetCostAmt" content="${totalRow.targetCostAmt}">${totalRow.targetCostAmt}</div>							 		
		 		</td>
		 		<%--本年度之前已付额 --%>
		 		<td nowrap="nowrap" title="${totalRow.preYearPaiedAmt}"  style="text-align: left;padding-left: 2px;" align="center">
		 			<div  class="partHideInner " style="text-align:right;padding-right: 2px;" nc="t_preYearPaiedAmt">${totalRow.preYearPaiedAmt}</div>
		 		</td>
		 		<%--本年度预算批准额 --%>
		 		<td nowrap="nowrap" title="${totalRow.groupTotalAmt}" style="text-align: left;padding-left: 2px;">		 			
					<div  class="partHideInner" style="text-align:right;padding-right: 2px;" nc="t_groupTotalAmt">${totalRow.groupTotalAmt}</div>
		 		</td>
		 		
		 		<%--备注--%>
		 		<td nowrap="nowrap" align="right" title="${totalRow.memoDesc}" style="padding-right: 2px;">
		 			<div  class="partHideInner " style="text-align:left;padding-left: 2px;" >${totalRow.memoDesc}</div>
		 		</td>
		 		<%--1月份--%>
		 		<td nowrap="nowrap" align="right" title="${totalRow.planAmtM01 }"  style="padding-right: 2px;">
					<div  class="partHideInner"  style="text-align:right;padding-right: 2px;" nc="t_planAmtM01">${totalRow.planAmtM01}</div>
		 		</td>
		 		<%--2月份--%>
		 		<td nowrap="nowrap" align="right" title="${totalRow.planAmtM02}" style="padding-right: 2px;">
						<div  class="partHideInner "  style="text-align:right;padding-right: 2px;" nc="t_planAmtM02">${totalRow.planAmtM02}</div>
		 		</td>
		 		<%--3月份--%>
		 		<td nowrap="nowrap" align="right" title="${totalRow.planAmtM03 }" style="padding-right: 2px;">
						<div  class="partHideInner "  style="text-align:right;padding-right: 2px;" nc="t_planAmtM03">${totalRow.planAmtM03}</div>
		 		</td>
		 		<%--4月份--%>
		 		<td nowrap="nowrap" align="right" title="${totalRow.planAmtM04 }" style="padding-right: 2px;">
						<div  class="partHideInner "  style="text-align:right;padding-right: 2px;" nc="t_planAmtM04">${totalRow.planAmtM04}</div>
		 		</td>
		 		<%--5月份--%>
		 		<td nowrap="nowrap" align="right" title="${totalRow.planAmtM05 }" style="padding-right: 2px;">
						<div  class="partHideInner "  style="text-align:right;padding-right: 2px;" nc="t_planAmtM05">${totalRow.planAmtM05}</div>
		 		</td>
		 		<%--6月份--%>
		 		<td nowrap="nowrap" align="right" title="${ totalRow.planAmtM06}" style="padding-right: 2px;">
						<div  class="partHideInner "  style="text-align:right;padding-right: 2px;" nc="t_planAmtM06">${totalRow.planAmtM06}</div>
		 		</td>
		 		<%--7月份--%>
		 		<td nowrap="nowrap" align="right"title="${totalRow.planAmtM07 }"  style="padding-right: 2px;" >
						<div  class="partHideInner "  style="text-align:right;padding-right: 2px;" nc="t_planAmtM07">${totalRow.planAmtM07}</div>
		 		</td>
		 		<%--8月份--%>
		 		<td nowrap="nowrap" title="${totalRow.planAmtM08 }" style="padding-right: 2px;" >
						<div  class="partHideInner "  style="text-align:right;padding-right: 2px;" nc="t_planAmtM08">${totalRow.planAmtM08}</div>
		 		</td>
		 		<%--9月份--%>
		 		<td nowrap="nowrap"  title="${ totalRow.planAmtM09}" style="padding-right: 2px;">
						<div  class="partHideInner "  style="text-align:right;padding-right: 2px;" nc="t_planAmtM09">${totalRow.planAmtM09}</div>
		 		</td>
		 		<%--10月份--%>
		 		<td nowrap="nowrap"  align="right" title="${totalRow.planAmtM10}" style="padding-right: 2px;">
						<div  class="partHideInner "  style="text-align:right;padding-right: 2px;" nc="t_planAmtM10">${totalRow.planAmtM10}</div>
		 		</td>
		 		<%--11月份--%>
		 		<td nowrap="nowrap"  title="${totalRow. planAmtM11}" style="padding-right: 2px;">
						<div  class="partHideInner "  style="text-align:right;padding-right: 2px;" nc="t_planAmtM11">${totalRow.planAmtM11}</div>
		 		</td>
		 		<%--12月份--%>
		 		<td nowrap="nowrap"  title="${totalRow.planAmtM12 }" style="padding-right: 2px;">
						<div  class="partHideInner "  style="text-align:right;padding-right: 2px;" nc="t_planAmtM12">${totalRow.planAmtM12}</div>
		 		</td>
	 	</tr>	
 	</s:if>
 	</tbody>
 </table>
 </div>
<script type="text/javascript">	
	$(function(){	
		$("tr[line]").click(function(){
			//删除其他行的选中
			$("tr").each(function(){
				$(".trClicked").removeClass('trClicked');				
			});
			//本行选中
			$("tr[line='"+$(this).attr('line')+"']").each(function(){
				$(this).children().eq(0).addClass('trClicked');
				});
		});
	});	
</script>				
