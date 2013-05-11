<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>计划节点模板</title>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" /> 
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/planold/plan.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/planold/plan-exec-plan.css" /> 
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>

</head>

<body style="overflow: hidden;">
	
	<div id="configNodeHeadDiv" style="padding-left:5px; background-color: #E6E6E6; height: 28px; line-height: 28px;">
		<form id="nodeSearchForm" action="${ctx}/planold/plan-node-template!list.action" method="post">
			<input type="hidden" id="planTypeCd" name="planTypeCd"  value="${planTypeCd}"  />
			<div style="float:left;" >
				<s:if test="planTypeCd == 1">[执行计划模板]</s:if>
				<s:if test="planTypeCd == 0">[前期计划模板]</s:if>
				节点名称: 
				<input type="text" id="filter_LIKES_nodeName" name="filter_LIKES_nodeName" value="${filter_LIKES_nodeName}"/>
				是否控制节点
				<select id="filter_EQS_controlNodeFlg" name="filter_EQS_controlNodeFlg">
					<option value=""></option>
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</div>
			<div style="float:left;"><button class="btn_search" type="submit">搜索</button></div>
			<div style="float:left;" class="btn_goback" onclick="searchNodesAll();">全部节点</div>
			<div style="float:left;font-size:12px; padding-left:10px;" class="color_red" id="succInfoMsg"></div>
			<div style="float:right;padding-right:5px;">一共 ${fn:length(templateNodeList)} 个节点</div>
		</form>
	</div>
	<div  id="configNodeContentDiv" style="padding-left:5px; overflow: auto; overflow-x: hidden; clear: both;">
	 	<script type="text/javascript">
	 		$("#configNodeContentDiv").height($(document).height()-$("#configNodeHeadDiv").height()-5); 
		</script>

		<table class="table_list" cellpadding="0" cellspacing="0" style="table-layout: fixed; width: 100%;">
		<thead>
			<tr class="header">
				<th width="40">序号</th>
				<th>名称</th>
				<th width="100">主责方</th>
				<th width="200">关键输出成果</th>
				<th width="90" style="text-align: center;">是否控制节点</th>
			</tr>
		</thead>
		
		<tbody>
			<tr id="newNodeAddedTr" style="display: none;" class="data">
				<td colspan="7">
					<table cellpadding="0" cellspacing="0" class="table_list">
						<tr class="data">
							<td>
								<input type="text" id="newAddedNodeCd" name="nodeCd" style="width: 80%;" />
							</td>
							<td>
								<input type="text" id="newAddedNodeName" name="nodeName" style="width: 95%;" />
							</td>
							<td>
								<input type="text" class="pd-chill-tip" id="newAddedResOrgName" onfocus="enableResOrgNameSel(null);" 
									name="resOrgName" style="width: 95%; cursor: pointer;" readonly="readonly" />
								<input type="hidden" id="newAddedResOrgCd" name="resOrgCd" />
								<s:if test="(resOrgCd == null || resOrgCd == '')&&(resOrgName != null && resOrgName != '') "><font color="red">*</font></s:if>
							</td>
							<td>
								<input type="text" class="pd-chill-tip" id="newAddedOutputFiles" name="outputFiles" style="width: 95%;" />
							</td>
							<td style="text-align: center;">
								<select id="newAddedControlNodeFlg" name="controlNodeFlg">
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							</td>
						</tr>
					</table>
				</td>
			</tr>			
			<s:iterator value="templateNodeList" status="node">
				<tr class="data">
					<td style="cursor: pointer;" onclick="enableNodeCdEdit('${planExecutionPlanNodeId}');">
						<div id="${planExecutionPlanNodeId}NodeCd">${nodeCd}</div>
						<input onblur="saveNodeCd('${planExecutionPlanNodeId}');" id="${planExecutionPlanNodeId}NodeCdInput" type="text"
							name="${planExecutionPlanNodeId}nodeCd" value="${nodeCd}" style="width: 80%; display: none;" />
					</td>
					<td style="cursor: pointer;" onclick="enableNodeNameEdit('${planExecutionPlanNodeId}');" title="点击编辑节点名称">
						<div id="${planExecutionPlanNodeId}NodeName" title="${nodeName}">${nodeName}</div>
						<input onblur="saveNodeName('${planExecutionPlanNodeId}');" id="${planExecutionPlanNodeId}NodeNameInput" type="text"
							name="${planExecutionPlanNodeId}nodeName" value="${nodeName}" style="width: 95%; display: none;" />
					</td>
					<td align="left" style="cursor: pointer;" onclick="enableResOrgNameSel('${planExecutionPlanNodeId}');" title="点击编辑主责方">
						<div id="${planExecutionPlanNodeId}ResOrgName" class="pd-chill-tip" title="${resOrgName}">${resOrgName}</div>
					</td>
					<td align="left" style="cursor: pointer;" onclick="enableOutputFilesEdit('${planExecutionPlanNodeId}');" title="点击编辑关键成果输出">
						<div id="${planExecutionPlanNodeId}OutputFiles" class="pd-chill-tip" title="${outputFiles}">${outputFiles}</div>
						<input onblur="saveOutputFiles('${planExecutionPlanNodeId}');" id="${planExecutionPlanNodeId}OutputFilesInput" type="text"
							name="${planExecutionPlanNodeId}outputFiles" value="${outputFiles}" style="width: 90%; display: none;" />
					</td>
					<td align="left" style="cursor: pointer;text-align: center;" onclick="enableControlNodeFlgSet('${planExecutionPlanNodeId}');" title="点击设置当前节点是否为控制计划节点">
						<div id="${planExecutionPlanNodeId}ControlNodeFlg">
							<s:if test="controlNodeFlg == 1">是</s:if>
							<s:else>否</s:else>
						</div>
						<select onblur="$(this).prev().show(); $(this).hide();" onclick="jQuery.Event(event).stopPropagation();" style="display: none;" onchange="saveControlNodeFlg('${planExecutionPlanNodeId}');"
							id="${planExecutionPlanNodeId}ControlNodeFlgSelect" name="${planExecutionPlanNodeId}controlNodeFlg">
							<option value="1" <s:if test="controlNodeFlg == 1">selected="selected"</s:if>>是</option>
							<option value="0" <s:if test="controlNodeFlg == 0">selected="selected"</s:if>>否</option>
						</select>
					</td>
				</tr>
			</s:iterator>
		</tbody>
		</table>
	</div>　　

	<script type="text/javascript">
	
		function getPlanTypeCd(){
			return $("#planTypeCd").val();
		}
	  
		function enableNodeCdEdit(nodeId) {
			return; 
		} 
		function enableNodeNameEdit(nodeId) {
			return;
		} 
		function enableResOrgNameSel(nodeId) {
			return; 
		} 
		function enableOutputFilesEdit(nodeId) {
			return; 
		} 
		function enableControlNodeFlgSet(nodeId) {
			return;
		} 
		function searchNodesAll(){
			document.location = "${ctx}/planold/plan-node-template!list.action?planTypeCd="+getPlanTypeCd();
		}
	</script>
</body>
</html>

