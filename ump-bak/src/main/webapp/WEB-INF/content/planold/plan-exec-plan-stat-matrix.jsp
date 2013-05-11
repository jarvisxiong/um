<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<table id="mainMatrix" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top" id="topLeftTd" style="width:253px;">
			<div id="topLeftDiv" style="width:100%;overflow: auto;">
				<table id="topLeftTitle" style="height:41px;width:100%;background-color: #D6D6D6;border-top: 1px solid black;" cellpadding="0" cellspacing="0">
					<tr>
						<td style="width: 30px;border-right: 1px solid black; border-bottom: 1px solid black;">序号</td>
						<td style="width:100px;border-right: 1px solid black; border-bottom: 1px solid black;">任务分解节点1</td>
						<td style="width:120px;border-right: 1px solid black; border-bottom: 1px solid black;">主责方</td>
					</tr> 
				</table>
			</div>
		</td>
		<td valign="top" id="topRightTd">
			<div id="topRightDiv" style="width: 550px; overflow: hidden;">
				<table id="topRightTitle" style="background-color: #D6D6D6;border-top: 1px solid black;" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="${curMonthsSize+1}" style="height:20px;text-align: center;border-right: 1px solid black; border-bottom: 1px solid black;">${curYear}年</td>
						<td colspan="${nextMonthsSize}"  style="height:20px;text-align: center;border-right: 1px solid black; border-bottom: 1px solid black;">${nextYear}年</td>
					</tr>
					<tr>
						<td style="height:20px;width:40px; border-right: 1px solid black; border-bottom: 1px solid black;text-align: center;">延期</td>
						<s:iterator value="thMonths" var="month">
						<td style="height:20px;width:40px;border-right: 1px solid black; border-bottom: 1px solid black;text-align: center;"><s:property />月</td>
						</s:iterator>
					</tr>
				</table>
			</div>
		</td>
	</tr>
	<tr>
		<td valign="top">
			<div id="bottomLeftDiv" style="height:350px;width:253px;overflow: hidden;">
				<table id="bottomLeftContent" cellpadding="0" cellspacing="0" style="border-left: 1px solid black;">
					<s:iterator value="detailNodes" var="node"  status="nodeStatus">
					<tr>
						<td style="width: 30px;border-right: 1px solid black; border-bottom: 1px solid black;height:20px;"><s:property value="#nodeStatus.count" /></td>
						<td style="width:100px;border-right: 1px solid black; border-bottom: 1px solid black;" title="${node.planProjectNodeRel.nodeName}" class="pd-chill-tip"><div style="width: 90px; overflow:hidden; white-space:nowrap; text-align: left;">${node.planProjectNodeRel.nodeName}</div></td>
						<td style="width:120px;border-right: 1px solid black; border-bottom: 1px solid black;" title="${node.planProjectNodeRel.resOrgName}" class="pd-chill-tip"><div style="width: 100px; overflow:hidden; white-space:nowrap; text-align: left;">${node.planProjectNodeRel.resOrgName}</div></td>
					</tr>	
					</s:iterator>
				</table>
			</div>
		</td>
		<td valign="top" style="width:550px;">
			<div id="bottomRightDiv" style="height:350px;width: 533px; overflow: auto;" onscroll="resetLayout($(this));">
				<table id="bottomRightContent" style="height:350px;width: 533px;" cellpadding="0" cellspacing="0" >
					<s:iterator value="detailNodes" var="node"  status="nodeStatus">
					<tr>
						<s:iterator value="yearMonths" var="yearMonth">
							<s:set name="key" value="#node.planExecPlanDetailId + '_' + #yearMonth"></s:set>
							<s:set name="flag" value="flagMap[#key]"></s:set>
							
							<s:if test="#flag == 'delay'">
							<td class="pd-chill-tip"
								title='
								计划开始时间:<s:date format="yyyyMMdd" name="#node.scheduleStartDate"/> <br/>
								计划完成时间:<s:date format="yyyyMMdd" name="#node.scheduleEndDate"/> <br/>
								实际开始时间:<s:date format="yyyyMMdd" name="#node.realStartDate"/> <br/>
								实际完成时间:<s:date format="yyyyMMdd" name="#node.realEndDate"/> 
								' 
								style="width:50px;border-right: 1px solid black; border-bottom: 1px solid black;background-color: red;height:20px;"></td>
							</s:if>
							<s:elseif test="#flag == 'done'">
								<td class="pd-chill-tip"
								title='
								计划开始时间:<s:date format="yyyyMMdd" name="#node.scheduleStartDate"/> <br/>
								计划完成时间:<s:date format="yyyyMMdd" name="#node.scheduleEndDate"/> <br/>
								实际开始时间:<s:date format="yyyyMMdd" name="#node.realStartDate"/> <br/>
								实际完成时间:<s:date format="yyyyMMdd" name="#node.realEndDate"/> 
								' 
								style="width:50px;border-right: 1px solid black; border-bottom: 1px solid black;background-color: green;height:20px;"></td>
							</s:elseif>
							<s:elseif test="#flag == 'todo'">
								<td class="pd-chill-tip"
								title='
								计划开始时间:<s:date format="yyyyMMdd" name="#node.scheduleStartDate"/> <br/>
								计划完成时间:<s:date format="yyyyMMdd" name="#node.scheduleEndDate"/> <br/>
								实际开始时间:<s:date format="yyyyMMdd" name="#node.realStartDate"/> <br/>
								实际完成时间:<s:date format="yyyyMMdd" name="#node.realEndDate"/> 
								' 
								style="width:50px;border-right: 1px solid black; border-bottom: 1px solid black;background-color: blue;height:20px;"></td>
							</s:elseif>
							<s:else>
								<td class="pd-chill-tip" 
								style="width:50px;border-right: 1px solid black; border-bottom: 1px solid black;height:20px;"></td>
							</s:else>
						</s:iterator>
					</tr>
					</s:iterator>
				</table>
			</div>
		</td>
	</tr>
</table>


	<%--

<table id="stat" style="width:100%;border: 1px black solid;padding:2px;" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="16">
			<table id="topRightTitle" style="width:100%">
				<tr style="background-color: #D6D6D6;">
					<th rowspan="2" style="width:30px;border-right: 1px solid black; border-bottom: 1px solid black;">序号</th>
					<th rowspan="2" style="width:100px;border-right: 1px solid black; border-bottom: 1px solid black;">节点</th>
					<th rowspan="2" style="width:120px;border-right: 1px solid black; border-bottom: 1px solid black;">主责方</th>
					
					<th colspan="${curMonthsSize+1}" style="text-align: center;border-right: 1px solid black; border-bottom: 1px solid black;">${curYear}年</th>
					<th colspan="${nextMonthsSize}" style="text-align: center;border-right: 1px solid black; border-bottom: 1px solid black;">${nextYear}年</th>
				</tr>
				<tr style="background-color: #D6D6D6;">
					<th style="border-right: 1px solid black; border-bottom: 1px solid black;">延期节点</th>
					<s:iterator value="thMonths" var="month">
						<th style="border-right: 1px solid black; border-bottom: 1px solid black;"><s:property />月</th>
					</s:iterator>
				</tr>
			</table>
		</td>
	</tr>
	
	<!-- 按业态明细 -->
	<s:iterator value="detailNodes" var="node"  status="nodeStatus">
	<tr>
		<td style="width:30px;border-right: 1px solid black; border-bottom: 1px solid black;"><s:property value="#nodeStatus.count" /></td>
		<td style="width:100px;border-right: 1px solid black; border-bottom: 1px solid black;" title="${node.planProjectNodeRel.nodeName}" class="pd-chill-tip"><div style="width: 95%;  overflow: hidden; text-align: left;">${node.planProjectNodeRel.nodeName}</div></td>
		<td style="width:120px;border-right: 1px solid black; border-bottom: 1px solid black;" title="${node.planProjectNodeRel.resOrgName}" class="pd-chill-tip"><div style="width: 95%;  overflow: hidden; text-align: left;">${node.planProjectNodeRel.resOrgName}</div></td>
		
		<s:iterator value="yearMonths" var="yearMonth">
			<s:set name="key" value="#node.planExecPlanDetailId + '_' + #yearMonth"></s:set>
			<s:set name="flag" value="flagMap[#key]"></s:set>
			
			<s:if test="#flag == 'delay'">
			<td class="pd-chill-tip"
				title='
				计划开始时间:<s:date format="yyyyMMdd" name="#node.scheduleStartDate"/> <br/>
				计划完成时间:<s:date format="yyyyMMdd" name="#node.scheduleEndDate"/> <br/>
				实际开始时间:<s:date format="yyyyMMdd" name="#node.realStartDate"/> <br/>
				实际完成时间:<s:date format="yyyyMMdd" name="#node.realEndDate"/> 
				' 
				style="border-right: 1px solid black; border-bottom: 1px solid black;background-color: red;"></td>
			</s:if>
			<s:elseif test="#flag == 'done'">
				<td class="pd-chill-tip"
				title='
				计划开始时间:<s:date format="yyyyMMdd" name="#node.scheduleStartDate"/> <br/>
				计划完成时间:<s:date format="yyyyMMdd" name="#node.scheduleEndDate"/> <br/>
				实际开始时间:<s:date format="yyyyMMdd" name="#node.realStartDate"/> <br/>
				实际完成时间:<s:date format="yyyyMMdd" name="#node.realEndDate"/> 
				' 
				style="border-right: 1px solid black; border-bottom: 1px solid black;background-color: green;"></td>
			</s:elseif>
			<s:elseif test="#flag == 'todo'">
				<td class="pd-chill-tip"
				title='
				计划开始时间:<s:date format="yyyyMMdd" name="#node.scheduleStartDate"/> <br/>
				计划完成时间:<s:date format="yyyyMMdd" name="#node.scheduleEndDate"/> <br/>
				实际开始时间:<s:date format="yyyyMMdd" name="#node.realStartDate"/> <br/>
				实际完成时间:<s:date format="yyyyMMdd" name="#node.realEndDate"/> 
				' 
				style="border-right: 1px solid black; border-bottom: 1px solid black;background-color: blue;"></td>
			</s:elseif>
			<s:else>
				<td class="pd-chill-tip"
				style="border-right: 1px solid black; border-bottom: 1px solid black;"></td>
			</s:else>
		</s:iterator>
	</tr>
	</s:iterator>
	 --%>

	<%--
	按节点
	
	<s:iterator value="ctrlNodes" var="node" status="nodeStatus">
	<tr>
		
		<td style="border-right: 1px solid black; border-bottom: 1px solid black;">${#nodeStatus.count}</td>
		<td style="border-right: 1px solid black; border-bottom: 1px solid black;"><div style="width: 95%;  overflow: hidden; text-align: left;">${node.nodeName}</div></td>
		<td style="border-right: 1px solid black; border-bottom: 1px solid black;"><div style="width: 95%;  overflow: hidden; text-align: left;">${node.resOrgName}</div></td>
		
		<s:iterator value="yearMonths" var="yearMonth">
			<s:set name="key" value="#node.planProjectNodeRelId + '_' + #yearMonth"></s:set>
			<s:set name="flag" value="flagMap[#key]"></s:set>
			
			<s:if test="#flag == 'delay'">
				<td class="pd-chill-tip"
				title='
				计划开始时间:<s:date format="yyyyMMdd" name="#node.scheduleStartDate"/> </br>
				计划完成时间:<s:date format="yyyyMMdd" name="#node.scheduleEndDate"/> </br>
				实际开始时间:<s:date format="yyyyMMdd" name="#node.realStartDate"/> </br>
				实际完成时间:<s:date format="yyyyMMdd" name="#node.realEndDate"/> 
				' 
				style="border-right: 1px solid black; border-bottom: 1px solid black;background-color: red;">未完成<s:property value="#flag" /></td>
			</s:if>
			<s:elseif test="#flag == 'done'">
				<td class="pd-chill-tip"
				title='
				计划开始时间:<s:date format="yyyyMMdd" name="#node.scheduleStartDate"/> </br>
				计划完成时间:<s:date format="yyyyMMdd" name="#node.scheduleEndDate"/> </br>
				实际开始时间:<s:date format="yyyyMMdd" name="#node.realStartDate"/> </br>
				实际完成时间:<s:date format="yyyyMMdd" name="#node.realEndDate"/> 
				' 
				style="border-right: 1px solid black; border-bottom: 1px solid black;background-color: green;">已完成</td>
			</s:elseif>
			<s:elseif test="#flag == 'todo'">
				<td class="pd-chill-tip"
				title='
				计划开始时间:<s:date format="yyyyMMdd" name="#node.scheduleStartDate"/> </br>
				计划完成时间:<s:date format="yyyyMMdd" name="#node.scheduleEndDate"/> </br>
				实际开始时间:<s:date format="yyyyMMdd" name="#node.realStartDate"/> </br>
				实际完成时间:<s:date format="yyyyMMdd" name="#node.realEndDate"/> 
				' 
				style="border-right: 1px solid black; border-bottom: 1px solid black;background-color: blue;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</s:elseif>
			<s:else>
				<td class="pd-chill-tip"
				style="border-right: 1px solid black; border-bottom: 1px solid black;"></td>
			</s:else>
		</s:iterator>
	</tr>
	</s:iterator>
</table>
	--%>
	
