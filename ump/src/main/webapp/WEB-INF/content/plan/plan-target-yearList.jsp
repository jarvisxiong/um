<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<form action="${ctx}/plan/plan-target!saveyear.action" id="newYearPlan" method="post">
    <input type="hidden" id="id" name="id"/>
    <input type="hidden" id="newCenter" name="entity.center" value="${currentCenterCd}"/>
    <input type="hidden" id="newSequenceNumber" name="entity.sequenceNumber"/>
    <input type="hidden" id="newYearTarget" name="entity.yearTarget"/>
    <input type="hidden" id="newContent" name="entity.content"/>
    <input type="hidden" id="newTargetYear" name="entity.targetYear"/>
</form>
<table class="content_table" id="mainContent">
	<thead>
		<tr>
			<th align="left" width="30px;" style="background:none;">序号</th>
			<th align="left" nowrap="nowrap">年度重点工作目标</th>
			<th align="left" nowrap="nowrap">具体措施</th>
			<th align="left" width="30px" nowrap="nowrap">1月</th>
			<th align="left" width="30px" nowrap="nowrap">2月</th>
			<th align="left" width="30px" nowrap="nowrap">3月</th>
			<th align="left" width="30px" nowrap="nowrap">4月</th>
			<th align="left" width="30px" nowrap="nowrap">5月</th>
			<th align="left" width="30px" nowrap="nowrap">6月</th>
			<th align="left" width="30px" nowrap="nowrap">7月</th>
			<th align="left" width="30px" nowrap="nowrap">8月</th>
			<th align="left" width="30px" nowrap="nowrap">9月</th>
			<th align="left" width="30px" nowrap="nowrap">10月</th>
			<th align="left" width="30px" nowrap="nowrap">11月</th>
			<th align="left" width="30px" nowrap="nowrap">12月</th>
			<th align="left" width="55px" nowrap="nowrap">操作</th>
		</tr>
	</thead>
	<tr class="newYearPlan" style="display:none;">
	    <td><input type="text" id="sequenceNumber"/></td>
	    <td><input type="text" id="yearTarget"/></td>
	    <td><input type="text" id="content"/></td>
	    <td align="left"><input type="checkbox" name="point" value="1"/></td>
		<td align="left"><input type="checkbox" name="point" value="2"/></td>
		<td align="left"><input type="checkbox" name="point" value="3"/></td>
		<td align="left"><input type="checkbox" name="point" value="4"/></td>
		<td align="left"><input type="checkbox" name="point" value="5"/></td>
		<td align="left"><input type="checkbox" name="point" value="6"/></td>
		<td align="left"><input type="checkbox" name="point" value="7"/></td>
		<td align="left"><input type="checkbox" name="point" value="8"/></td>
		<td align="left"><input type="checkbox" name="point" value="9"/></td>
		<td align="left"><input type="checkbox" name="point" value="10"/></td>
		<td align="left"><input type="checkbox" name="point" value="11"/></td>
		<td align="left"><input type="checkbox" name="point" value="12"/></td>
		<td></td>
	</tr>
	<tr class="newYearPlan" style="display:none;">
	 <td colspan="16">
	  <input type="button" class="yearBtn btn_Green" onclick="doYearSubmit();" value="提交"/>
	  <input type="button" class="yearBtn btn_Green" onclick="newYearPlan();" value="返回"/>
	 </td>
	</tr>
	<s:iterator value="monthList" var="month">
	<tr id="${planTargetId}" class="mainTr">
	 <td onclick="doYearDetail('${planTargetId}')">${sequenceNumber}</td>
	 <td onclick="doYearDetail('${planTargetId}')">${yearTarget}</td>
	 <td onclick="doYearDetail('${planTargetId}')">${content}</td>
	 <s:if test="month1==1"><td class="yearMonth" onclick="doYearDetail('${planTargetId}')">1月</td></s:if>
	   <s:else><td onclick="doYearDetail('${planTargetId}')"></td></s:else>
	 <s:if test="month2==1"><td class="yearMonth" onclick="doYearDetail('${planTargetId}')">2月</td></s:if>
	   <s:else><td onclick="doYearDetail('${planTargetId}')"></td></s:else>
	 <s:if test="month3==1"><td class="yearMonth" onclick="doYearDetail('${planTargetId}')">3月</td></s:if>
	  <s:else><td onclick="doYearDetail('${planTargetId}')"></td></s:else>
	  <s:if test="month4==1"><td class="yearMonth" onclick="doYearDetail('${planTargetId}')">4月</td></s:if>
	  <s:else><td onclick="doYearDetail('${planTargetId}')"></td></s:else>
	  <s:if test="month5==1"><td class="yearMonth" onclick="doYearDetail('${planTargetId}')">5月</td></s:if>
	  <s:else><td onclick="doYearDetail('${planTargetId}')"></td></s:else>
	  <s:if test="month6==1"><td class="yearMonth" onclick="doYearDetail('${planTargetId}')">6月</td></s:if>
	  <s:else><td onclick="doYearDetail('${planTargetId}')"></td></s:else>
	  <s:if test="month7==1"><td class="yearMonth" onclick="doYearDetail('${planTargetId}')">7月</td></s:if>
	  <s:else><td onclick="doYearDetail('${planTargetId}')"></td></s:else>
	  <s:if test="month8==1"><td class="yearMonth" onclick="doYearDetail('${planTargetId}')">8月</td></s:if>
	  <s:else><td onclick="doYearDetail('${planTargetId}')"></td></s:else>
	  <s:if test="month9==1"><td class="yearMonth" onclick="doYearDetail('${planTargetId}')">9月</td></s:if>
	  <s:else><td onclick="doYearDetail('${planTargetId}')"></td></s:else>
	  <s:if test="month10==1"><td class="yearMonth" onclick="doYearDetail('${planTargetId}')">10月</td></s:if>
	  <s:else><td onclick="doYearDetail('${planTargetId}')"></td></s:else>
	  <s:if test="month11==1"><td class="yearMonth" onclick="doYearDetail('${planTargetId}')">11月</td></s:if>
	  <s:else><td onclick="doYearDetail('${planTargetId}')"></td></s:else>
	  <s:if test="month12==1"><td class="yearMonth" onclick="doYearDetail('${planTargetId}')">12月</td></s:if>
	  <s:else><td onclick="doYearDetail('${planTargetId}')"></td></s:else>
	  <td>
	  <security:authorize ifAnyGranted="A_PLAN_WORK2_YEAR">
	   <input type="button" value="删除" class="btn_red" onclick="doDelYearTarget('${planTargetId}');"/>
	   </security:authorize>
	  </td>
	</tr>
	<tr id="deta_${planTargetId}" class="deta">
	 <td>
	 <input type="hidden" id="planTargetId_${planTargetId}"/>
	 <input type="text" id="sequenceNumber_${planTargetId}" value="${sequenceNumber}"/></td>
	 <td><input type="text" id="yearTarget_${planTargetId}" value="${yearTarget}"/></td>
	 <td><textarea id="content_${planTargetId}" >${content}</textarea>
	 </td>
	 <td><input type="checkbox" name="month_${planTargetId}" value="1" 
	  <s:if test="month1==1">checked</s:if> /></td>
	 <td><input type="checkbox" name="month_${planTargetId}" value="2"
	  <s:if test="month2==1">checked</s:if> /></td>
	 <td><input type="checkbox" name="month_${planTargetId}" value="3"
	  <s:if test="month3==1">checked</s:if> /></td>
	 <td><input type="checkbox" name="month_${planTargetId}" value="4"
	  <s:if test="month4==1">checked</s:if> /></td>
	 <td><input type="checkbox" name="month_${planTargetId}" value="5"
	  <s:if test="month5==1">checked</s:if> /></td>
	 <td><input type="checkbox" name="month_${planTargetId}" value="6"
	  <s:if test="month6==1">checked</s:if> /></td>
	 <td><input type="checkbox" name="month_${planTargetId}" value="7"
	  <s:if test="month7==1">checked</s:if> /></td>
	 <td><input type="checkbox" name="month_${planTargetId}" value="8"
	  <s:if test="month8==1">checked</s:if> /></td>
	 <td><input type="checkbox" name="month_${planTargetId}" value="9"
	  <s:if test="month9==1">checked</s:if> /></td>
	 <td><input type="checkbox" name="month_${planTargetId}" value="10"
	  <s:if test="month10==1">checked</s:if> /></td>
	 <td><input type="checkbox" name="month_${planTargetId}" value="11"
	  <s:if test="month11==1">checked</s:if> /></td>
	 <td><input type="checkbox" name="month_${planTargetId}" value="12"
	  <s:if test="month12==1">checked</s:if> /></td>
	 <td>
	 <security:authorize ifAnyGranted="A_PLAN_WORK2_YEAR">
	 <input type="button" value="保存" class="btn_green" onclick="doSaveYearTarget('${planTargetId}');"/>
	 </security:authorize>
	  </td>
	</tr>
	</s:iterator>
</table>