<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>成本计划节点模板</title>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" /> 
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/plan.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/plan-exec-plan.css" /> 
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>

</head>

<body style="overflow: hidden;">
	<div id="configNodeHeadDiv" style="padding-left:5px; background-color: #E6E6E6; height: 28px; line-height: 28px;">
		<form id="nodeSearchForm" action="${ctx}/plan/cost-node-template!list.action" method="post">
			<input type="hidden" id="planTypeCd" name="planTypeCd"  value="${planTypeCd}"  />
			<div style="float:left;" >
				<s:if test="planTypeCd == 2">[成本计划模板]</s:if>
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
			<div style="float:right;padding-right:5px;">一共  ${fn:length(templateNodeList)} 个节点</div>
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
				<th width="140" style="text-align: center;">关联执行计划节点</th>
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
							<td>
								<s:select list="mapExecPlanNodes" listKey="key" listValue="value" name="planExecutionPlanNodeId"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>			
			<s:iterator value="templateNodeList" status="node">
				<tr class="data">
					<td style="cursor: pointer;" onclick="enableNodeCdEdit('${costExecutionPlanNodeId}');">
						<div id="${costExecutionPlanNodeId}NodeCd">${nodeCd}</div>
						<input onblur="saveNodeCd('${costExecutionPlanNodeId}');" id="${costExecutionPlanNodeId}NodeCdInput" type="text"
							name="${costExecutionPlanNodeId}nodeCd" value="${nodeCd}" style="width: 80%; display: none;" />
					</td>
					<td style="cursor: pointer;" onclick="enableNodeNameEdit('${costExecutionPlanNodeId}');" title="点击编辑节点名称">
						<div id="${costExecutionPlanNodeId}NodeName" title="${nodeName}">${nodeName}</div>
						<input onblur="saveNodeName('${costExecutionPlanNodeId}');" id="${costExecutionPlanNodeId}NodeNameInput" type="text"
							name="${costExecutionPlanNodeId}nodeName" value="${nodeName}" style="width: 95%; display: none;" />
					</td>
					<td align="left" style="cursor: pointer;" onclick="enableResOrgNameSel('${costExecutionPlanNodeId}');" title="点击编辑主责方">
						<div id="${costExecutionPlanNodeId}ResOrgName" class="pd-chill-tip" title="${resOrgName}">${resOrgName}</div>
					</td>
					<td align="left" style="cursor: pointer;" onclick="enableOutputFilesEdit('${costExecutionPlanNodeId}');" title="点击编辑关键成果输出">
						<div id="${costExecutionPlanNodeId}OutputFiles" class="pd-chill-tip" title="${outputFiles}">${outputFiles}</div>
						<input onblur="saveOutputFiles('${costExecutionPlanNodeId}');" id="${costExecutionPlanNodeId}OutputFilesInput" type="text"
							name="${costExecutionPlanNodeId}outputFiles" value="${outputFiles}" style="width: 90%; display: none;" />
					</td>
					<td align="left" style="cursor: pointer;text-align: center;" onclick="enableControlNodeFlgSet('${costExecutionPlanNodeId}');" title="点击设置当前节点是否为控制计划节点">
						<div id="${costExecutionPlanNodeId}ControlNodeFlg">
							<s:if test="controlNodeFlg == 1">是</s:if>
							<s:else>否</s:else>
						</div>
						<select onblur="$(this).prev().show(); $(this).hide();" 
								onclick="jQuery.Event(event).stopPropagation();" 
								style="display: none;" 
								onchange="saveControlNodeFlg('${costExecutionPlanNodeId}');"
								id="${costExecutionPlanNodeId}ControlNodeFlgSelect" 
								name="${costExecutionPlanNodeId}controlNodeFlg">
							<option value="1" <s:if test="controlNodeFlg == 1">selected="selected"</s:if>>是</option>
							<option value="0" <s:if test="controlNodeFlg == 0">selected="selected"</s:if>>否</option>
						</select>
					</td>
					 
					<td align="left"  style="cursor: pointer;text-align: left;"
							 onclick="enablePlanExecutionPlanNodeIdSet('${costExecutionPlanNodeId}');" >
						<div id="${costExecutionPlanNodeId}PlanExecutionPlanNodeId"
							 title="点击设置当前节点关联执行计划节点">
							<p:code2name mapCodeName="mapExecPlanNodes" value="planExecutionPlanNodeId" />
						</div>
						<div id="${costExecutionPlanNodeId}PlanExecutionPlanNodeIdSelect" style="display:none;">
							<s:select   onblur="$(this).parent().prev().show(); $(this).parent().hide();"
										onclick="jQuery.Event(event).stopPropagation();" 
										onchange="savePlanExecutionPlanNodeId(this);"
										list="execPlanNodes" listKey="planExecutionPlanNodeId" listValue="nodeName"
										value="planExecutionPlanNodeId"
										headerKey="" headerValue=""
										cssStyle="text-align:left;"
										>
							</s:select>
							<span style="display: none;">${costExecutionPlanNodeId}</span>
						</div>
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
		function enablePlanExecutionPlanNodeIdSet(nodeId) {
			$("#" + nodeId + "PlanExecutionPlanNodeId").hide();
			$("#" + nodeId + "PlanExecutionPlanNodeIdSelect").show().focus();
		} 
		
		function onblurPlanExecutionPlanNodeId(dom) {
			$(dom).prev().show(); 
			$(dom).hide();
		} 
		
		function saveNodeCd(){
			
		}
		function saveNodeName(){
			
		}
		function saveOutputFiles(){
			
		}
		function saveControlNodeFlg(){
			
		}
		function savePlanExecutionPlanNodeId(dom){
			var nodeId = $(dom).next().html();//隐藏域
			var newVal = $(dom).val();
			var newTxt = $(dom).find("option:selected").text();
			updateNode(nodeId, "planExecutionPlanNodeId", newVal, newTxt);

			$("#" + nodeId + "PlanExecutionPlanNodeId").text(newTxt).show();
			$("#" + nodeId + "PlanExecutionPlanNodeIdSelect").hide();
		}

		function updateNode(nodeId, type, newVal,extMsg) {
			var url = "${ctx}/plan/cost-node-template!save.action";
			var params = null;
			var msg = "";

			switch (type) {
			case "nodeCd" :
				params = {id : nodeId, nodeCd : newVal, opType : type, planTypeCd : getPlanTypeCd() };
				msg = "更新节点序号成功";
				break;
			case "nodeName" :
				params = {id : nodeId, nodeName : newVal, opType : type, planTypeCd : getPlanTypeCd() };
				msg = "更新节点名称成功";
				break;
			case "outputFiles" :
				params = {id : nodeId, outputFiles : newVal, opType : type, planTypeCd : getPlanTypeCd() };
				msg = "更新关键输出成果成功";
				break;
			case "controlNodeFlg" :
				params = {id : nodeId, controlNodeFlg : newVal, opType : type, planTypeCd : getPlanTypeCd() };
				if (newVal == "1") {
					msg = "设置为控制节点成功";
				} else {
					msg = "设置为计划节点成功";
				}
				break;
			case "planExecutionPlanNodeId" :
				params = {id : nodeId, planExecutionPlanNodeId : newVal, opType : type, planTypeCd : getPlanTypeCd() };
				msg = "更新关联执行计划节点("+extMsg+")成功";
				break;
			default :
				return;
			}
			
			
			$.post(url, params, function(result) {
				if (result == "succ") {
					displaySuccInfo(msg);
				}
			});
		}
		// 展示操作成功信息
		function displaySuccInfo(msg) {
			$("#succInfoMsg").html(msg).show().fadeOut(2000);
		}
		function validateNodeCd(nodeCd) {
			if (!nodeCd || $.trim(nodeCd) == "") {
				return false;
			}
			for (var i = 0; i < nodeCd.length; i ++) {
				var num = parseInt(nodeCd.substring(i, i + 1));
				if (isNaN(num)) {
					return false;
				} else {
					if (i == 0 && num == 0) {
						return false;
					}
				}
			}
			return true;
		}

		function searchNodesAll(){
			document.location = "${ctx}/plan/cost-node-template!list.action?planTypeCd="+getPlanTypeCd();
		}
	</script>
</body>
</html>


