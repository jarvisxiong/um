<#setting number_format="#"> 
<table id="mainMatrix" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top" id="topLeftTd" style="width:273px;"class="first_column">
			<div id="topLeftDiv" style="width:100%;height:100%;">
				<table id="topLeftTitle" style="width:100%;height:39px" cellpadding="0" cellspacing="0">
					<tr>
						<td style="width: 30px;" >序号</td>
						<td style="width:115px;">任务分解节点</td>
						<td style="width:115px;">主责方</td>
					</tr> 
				</table>
			</div>
		</td>
		<td valign="top" id="topRightTd" style="width: 425px;">
			<div id="topRightDiv" style="width: 403px; overflow: hidden;">
				<table id="topRightTitle" style="width: 403px;height:40px" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="${curMonthsSize+1}" style="text-align: center;">${curYear}年</td>
						<td colspan="${nextMonthsSize}"  style="text-align: center;">${nextYear}年</td>
					</tr>
					<tr>
						<td style="width:30px;text-align: center;">过期</td>
						<#list thMonths as month>
						<td style="width:30px;text-align: center;">${month}月</td>
						</#list>
					</tr>
				</table>
			</div>
		</td>
	</tr>
	<tr>
		<td valign="top" style="width:273px;">
			<div id="bottomLeftDiv" style="height:350px;overflow: hidden;width:100%;">
				<table id="bottomLeftContent" cellpadding="0" cellspacing="0">
					<#list detailNodes as node>
					<tr>
						<td style="padding-left:2px;width: 30px;height:20px;" class="first_column">${node_index+1}</td>
						<td style="padding-left:2px;width:120px;" title="${node.planProjectNodeRel.nodeName}" class="pd-chill-tip"><div style="width: 95%; height: 18px; line-height: 18px; overflow: hidden; text-align: left;">${node.planProjectNodeRel.nodeName}</div></td>
						<td style="padding-left:2px;width:120px;" title="${node.planProjectNodeRel.resOrgName}" class="pd-chill-tip"><div style="width: 95%; height: 18px; line-height: 18px; overflow: hidden; text-align: left;">${node.planProjectNodeRel.resOrgName}</div></td>
					</tr>	
					</#list>
				</table>
			</div>
		</td>
		<td valign="top" style="width:425px;">
			<div id="bottomRightDiv" style="height:350px; overflow: auto;" onscroll="resetLayout($(this));">
				<table id="bottomRightContent" style="width: 403px;" cellpadding="0" cellspacing="0" >
					<#list detailNodes as node>
					<tr>
						<#list yearMonths as yearMonth>
							<#assign key = (node.planProjectNodeRel.planProjectNodeRelId + "_" + yearMonth)>
							<#assign flag = flagMap[key]> 
							
							<#if (flag == "delay")>
							<td class="cont_cell pd-chill-tip task_delay"
								title='
								计划开始时间:<#if (flag != "" && node.scheduleStartDate!=null)>
												${node.scheduleStartDate?string("yyyy-MM-dd")}
											<#else>
												&nbsp;
											</#if>
											<br/>
								计划完成时间:<#if (flag != "" && node.scheduleEndDate!= null)>
												${node.scheduleEndDate?string("yyyy-MM-dd")}
											<#else>
												&nbsp;
											</#if>
											<br/>
								实际开始时间:<#if (flag != "" && node.realStartDate!= null)>
												${node.realStartDate?string("yyyy-MM-dd")}
											<#else>
												&nbsp;
											</#if>
											<br/>
								实际完成时间:<#if (flag != "" && node.realEndDate!= null)>
												${node.realEndDate?string("yyyy-MM-dd")}
											<#else>
												&nbsp;
											</#if>
											<br/>
								' 
								></td>
							<#elseif (flag == "done")>
								<td class="cont_cell pd-chill-tip task_done"
								title='
								计划开始时间:<#if (flag != "" && node.scheduleStartDate!= null)>
												${node.scheduleStartDate?string("yyyy-MM-dd")}
											<#else>
												&nbsp;
											</#if>
											<br/>
								计划完成时间:<#if (flag != "" && node.scheduleEndDate!= null)>
												${node.scheduleEndDate?string("yyyy-MM-dd")}
											<#else>
												&nbsp;
											</#if>
											<br/>
								实际开始时间:<#if (flag != "" && node.realStartDate!= null)>
												${node.realStartDate?string("yyyy-MM-dd")}
											<#else>
												&nbsp;
											</#if>
											<br/>
								实际完成时间:<#if (flag != "" && node.realEndDate!= null)>
												${node.realEndDate?string("yyyy-MM-dd")}
											<#else>
												&nbsp;
											</#if>
											<br/>
								' 
								></td>
							<#elseif (flag == "todo")>
								<td class="cont_cell pd-chill-tip task_process"
								title='
								计划开始时间:<#if (flag != "" && node.scheduleStartDate!= null)>
												${node.scheduleStartDate?string("yyyy-MM-dd")}
											<#else>
												&nbsp;
											</#if>
											<br/>
								计划完成时间:<#if (flag != "" && node.scheduleEndDate!= null)>
												${node.scheduleEndDate?string("yyyy-MM-dd")}
											<#else>
												&nbsp;
											</#if>
											<br/>
								实际开始时间:<#if (flag != "" && node.realStartDate!= null)>
												${node.realStartDate?string("yyyy-MM-dd")}
											<#else>
												&nbsp;
											</#if>
											<br/>
								实际完成时间:<#if (flag != "" && node.realEndDate!= null)>
												${node.realEndDate?string("yyyy-MM-dd")}
											<#else>
												&nbsp;
											</#if>
											<br/>
								' 
								></td>
							<#else>
								<td class="cont_cell">&nbsp;</td>
							</#if>
						</#list>
					</tr>
					</#list>
				</table>
			</div>
		</td>
	</tr>
</table>