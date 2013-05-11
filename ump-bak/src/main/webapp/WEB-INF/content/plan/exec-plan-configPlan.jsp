<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<div id="configPlanHeadDiv" style="padding-left:5px; background-color: #E6E6E6; height: 28px; line-height: 28px;">
	<div style="float:left;" >请输入业态名称：<input type="text" id="projPlanNameInput" name="projPlanName" /></div>
	<div style="float:left;" class="btn_add_plan" onclick="addPlanModule();">新增业态</div>
	<div style="float:left;" class="btn_goback"   onclick="backToProjPlan();">返回</div>
</div>

<div id="configPlanContentDiv" style="padding-left:5px;">
	<table class="table_list" cellpadding="0" cellspacing="0" style="table-layout: fixed; width: 100%;">
		<thead>
			<tr class="header">
				<th>业态名称</th>
				<th width="100">创建时间</th>
				<th width="100">当前状态</th>
				<th width="30x">排序</th>
				<th width="100" style="background: none;">操作</th>
			</tr>
		</thead>
		
		<tbody>
			<s:iterator value="execPlanLayouts">
			<tr class="data">
				<td style="cursor: pointer;" onclick="enablePlanNameEdit('${execPlanLayoutId}');">
					<div id="${execPlanLayoutId}PlanNameDiv">${executionPlanName}</div>
					
					<input type="text" id="${execPlanLayoutId}PlanNameInput" 
						name="${execPlanLayoutId}PlanNameInput"
						value="${executionPlanName}"
						onblur="savePlanName('${execPlanLayoutId}');"
						style="width: 85%; cursor: pointer; display: none;"/>
				</td>
				<td align="left">
					<s:date name="createdDate" format="yyyy-MM-dd"/>
				</td>
				<td align="left">
					<c:if test="${activeFlg == '1'}">
						<span style="color: green;">已激活</span>
					</c:if>
					<c:if test="${activeFlg == '0'}">
						<span style="color: red;">已禁用</span>
					</c:if>
				</td>
				<td>
					<input type="text" id="${execPlanLayoutId}sequenceNo" 
						name="${execPlanLayoutId}sequenceNo"
						value="${sequenceNo}"
						onblur="savePlanName('${execPlanLayoutId}');"
						style="width: 30px;"/>
				</td>
				<td align="left">
					<button onClick="savePlanName('${execPlanLayoutId}');">保存</button>
					<img src="${ctx}/pics/companyvi/edit_file.gif" style="cursor: pointer;"
						<c:if test="${activeFlg == '1'}">title="点击禁用该业态"</c:if>
						<c:if test="${activeFlg == '0'}">title="点击激活该业态"</c:if>
						onclick="changePlanState('${execPlanLayoutId}', '${activeFlg}','${ifBis}');"
					>
				</td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
</div>

