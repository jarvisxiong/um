<%@page import="java.util.Map"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<form id="nodeSearchForm" action="${ctx}/planold/plan-exec-plan-node!list.action" method="post" onsubmit="return false;">
	<input type="hidden" name="projectCd"  value="${projectCd}"  />
	<input type="hidden" name="planTypeCd" value="${planTypeCd}" />
	
	<div id="configNodeHeadDiv" style="padding-left:5px; background-color: #E6E6E6; height: 28px; line-height: 28px;">
		<div style="float:left;" >节点名称: <input type="text" name="filterNodeName" value="${filterNodeName}" /></div>
		<div style="float:left;" class="btn_search" onclick="searchNodes();">搜索</div>
		<div style="float:left;" class="btn_goback" onclick="backToProjPlan();">返回</div>
		<div style="float:left;font-size:12px; padding-left:10px;" class="color_red" id="succInfoMsg"></div>
		<div style="float:right;" class="btn_add_plan" onclick="dispAddNewNode();">新增节点</div>
	</div>
</form>

	<div  id="configNodeContentDiv" style="padding-left:5px; overflow: auto; overflow-x: hidden; clear: both;">
	<table class="table_list" cellpadding="0" cellspacing="0" style="table-layout: fixed; width: 100%;">
		<thead>
			<tr class="header">
				<th width="40">序号</th>
				<th>名称</th>
				<th width="100">主责方</th>
				<%--
				<th width="100">配合方</th>
				 --%>
				<th width="200">关键输出成果</th>
				<th width="90" style="text-align: center;">是否控制节点</th>
				<th width="50" style="text-align: center;background: none;">操作</th>
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
								<!-- 标志主责方为空 -->
								<s:if test="(resOrgCd == null || resOrgCd == '')&&(resOrgName != null && resOrgName != '') "><font color="red">*</font></s:if>
							</td>
							
							<%--
							<td>
								<input type="text" class="pd-chill-tip" id="newAddedCorOrgName" onfocus="enableCorOrgNameSel(null)" 
									name="corOrgName" style="width: 95%; cursor: pointer;" readonly="readonly" />
								<input type="hidden" id="newAddedCorOrgCd" name="corOrgCd" />
								<!-- 标志配合方为空 -->
								<s:if test="(corOrgCd == null || corOrgCd == '')&&(corOrgName != null && corOrgName != '') "><font color="red">*</font></s:if>
							</td>
							--%>
							
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
								<div class="func_icon" style="float: left;" onclick="if (confirm('确认新增节点？')){addNewNode('${projectCd}');}">保存</div>
								<div class="func_icon" style="float: left; margin-left: 5px;" onclick="$('#newNodeAddedTr').hide();">取消</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>			
			<s:iterator value="projNodeList" status="node">
				<tr class="data">
					<td style="cursor: pointer;" onclick="enableNodeCdEdit('${planProjectNodeRelId}','<%= (String)((Map)JspUtil.findValue("mapExecCostPlan")).get(JspUtil.findString("nodeCd"))%>');">
						<div id="${planProjectNodeRelId}NodeCd">${nodeCd}</div>
						<input onblur="saveNodeCd('${planProjectNodeRelId}');" id="${planProjectNodeRelId}NodeCdInput" type="text"
							name="${planProjectNodeRelId}nodeCd" value="${nodeCd}" style="width: 80%; display: none;" />
					</td>
					<td style="cursor: pointer;" onclick="enableNodeNameEdit('${planProjectNodeRelId}');" title="点击编辑节点名称">
						<div id="${planProjectNodeRelId}NodeName" title="${nodeName}">${nodeName}</div>
						<input onblur="saveNodeName('${planProjectNodeRelId}');" id="${planProjectNodeRelId}NodeNameInput" type="text"
							name="${planProjectNodeRelId}nodeName" value="${nodeName}" style="width: 95%; display: none;" />
					</td>
					<td align="left" style="cursor: pointer;" onclick="enableResOrgNameSel('${planProjectNodeRelId}');" title="点击编辑主责方">
						<div id="${planProjectNodeRelId}ResOrgName" class="pd-chill-tip" title="${resOrgName}">${resOrgName}</div>
					</td>
					<%--
					<td align="left" style="cursor: pointer;" onclick="enableCorOrgNameSel('${planProjectNodeRelId}');" title="点击编辑配合方">
						<div id="${planProjectNodeRelId}CorOrgName" class="pd-chill-tip" title="${corOrgName}">${corOrgName}</div>
					</td>
					--%>
					<td align="left" style="cursor: pointer;" onclick="enableOutputFilesEdit('${planProjectNodeRelId}');" title="点击编辑关键成果输出">
						<div id="${planProjectNodeRelId}OutputFiles" class="pd-chill-tip" title="${outputFiles}">${outputFiles}</div>
						<input onblur="saveOutputFiles('${planProjectNodeRelId}');" id="${planProjectNodeRelId}OutputFilesInput" type="text"
							name="${planProjectNodeRelId}outputFiles" value="${outputFiles}" style="width: 90%; display: none;" />
					</td>
					<td align="left" style="cursor: pointer;text-align: center;" onclick="enableControlNodeFlgSet('${planProjectNodeRelId}');" title="点击设置当前节点是否为控制计划节点">
						<div id="${planProjectNodeRelId}ControlNodeFlg">
							<s:if test="controlNodeFlg == 1">是</s:if>
							<s:else>否</s:else>
						</div>
						<select onblur="$(this).prev().show(); $(this).hide();" onclick="jQuery.Event(event).stopPropagation();" style="display: none;" onchange="saveControlNodeFlg('${planProjectNodeRelId}');"
							id="${planProjectNodeRelId}ControlNodeFlgSelect" name="${planProjectNodeRelId}controlNodeFlg">
							<option value="1" <s:if test="controlNodeFlg == 1">selected="selected"</s:if>>是</option>
							<option value="0" <s:if test="controlNodeFlg == 0">selected="selected"</s:if>>否</option>
						</select>
					</td>
					<td id="${planProjectNodeRelId}OpsTd" style="text-align: center;">
						<% if(((Map)JspUtil.findValue("mapExecCostPlan")).containsKey(JspUtil.findString("nodeCd"))){ %>
							<div  class="pd-chill-tip" title="成本节点名称: <%= (String)((Map)JspUtil.findValue("mapExecCostPlan")).get(JspUtil.findString("nodeCd"))%>">
								关联
							</div>
						<% }else{  %>
							<img style="cursor: pointer; margin-left: 10px;" 
								 title="删除节点"  src="${ctx}/pics/note/note_del.gif"
								 onclick="deleteNode('${planProjectNodeRelId}');" />
						<% } %>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</div>

<script type="text/javascript">

//注意:getCurProjectCd()引自 plan-exec-plan.js
	
	function dispAddNewNode(){
		if ($('#newNodeAddedTr').css('display') == 'none'){
			$('#newNodeAddedTr').show();
		}else{
			$('#newNodeAddedTr').hide();
		};
	}

	function enableNodeCdEdit(nodeId,nodeName) {
		if($.trim(nodeName) != '' && $.trim(nodeName) != 'null'){
			return;
		}
		$("#" + nodeId + "NodeCd").hide();
		$("#" + nodeId + "NodeCdInput").show().focus();
	}
	function saveNodeCd(nodeId) {
		var oriNodeCd = $.trim($("#" + nodeId + "NodeCd").text());
		var newNodeCd = $.trim($("#" + nodeId + "NodeCdInput").val());

		if (!validateNodeCd(newNodeCd)) {
			alert("请输入格式正确的节点CD(必须为大于0的整数，例如：123)!");
			$("#" + nodeId + "NodeCdInput").show().focus();
			return;
		}
		
		if (oriNodeCd != newNodeCd) {
			updateNode(nodeId, "nodeCd", newNodeCd);
		}

		$("#" + nodeId + "NodeCd").text(newNodeCd).show();
		$("#" + nodeId + "NodeCdInput").val(newNodeCd).hide();
	}

	function enableNodeNameEdit(nodeId) {
		$("#" + nodeId + "NodeName").hide();
		$("#" + nodeId + "NodeNameInput").show().focus();
	}
	function saveNodeName(nodeId) {
		var oriNodeName = $.trim($("#" + nodeId + "NodeName").text());
		var newNodeName = $.trim($("#" + nodeId + "NodeNameInput").val());

		if (!newNodeName) {
			alert("请输入节点名称!");
			$("#" + nodeId + "NodeNameInput").show().focus();
			return;
		}
		
		if (oriNodeName != newNodeName) {
			updateNode(nodeId, "nodeName", newNodeName);
		}

		$("#" + nodeId + "NodeName").text(newNodeName).show();
		$("#" + nodeId + "NodeNameInput").val(newNodeName).hide();
	}

	var tempOrgCd;
	var tempOrgName;
	var curNodeId;
	// 打开主责方机构选择框
	function enableResOrgNameSel(nodeId) {
		tempOrgCd = null;
		tempOrgName = null;
		curNodeId = nodeId;
		
		ymPrompt.confirmInfo({
			icoCls:"",
			title:"选择主责方机构",
			message:"<div id='deptDiv' style='padding-top: 10px;'></div>",
			useSlide:true,
			winPos:"t",
			width:350,
			height:400,
			maxBtn:false,
			allowRightMenu:true,
			handler: saveResOrgInfo,
			afterShow:function(){
				$.post("${ctx}/planold/plan-exec-plan-node!buildOrgs.action",
						function(result) {
							if (result) {
								tree = new TreePanel({
									renderTo:'deptDiv',
									'root' : eval('('+result+')'),
									'ctx':'${ctx}'
								});
								tree.render();
								
								tree.on(function(node){
									var id = node.attributes.id;
									if( $.trim(id) == '' || $.trim(id)=='0'){
										tempOrgCd = "";
										tempOrgName = "";
									} else{
										tempOrgCd = node.attributes.id;
										tempOrgName = node.attributes.text;
										
										if(node.isExpand){
											node.collapse();
										}else{
											node.expand();
										}
									}
								});
							}
				});
			}
		});
	}
	// 保存主责方机构信息
	function saveResOrgInfo(tp) {
		if (tp == "ok") {
			if (curNodeId && $.trim(curNodeId).length > 0) {
				if (confirm("确认修改负责方吗？")) {
					var url = "${ctx}/planold/plan-exec-plan-node!saveResOrgInfo.action";
					var params = { id : curNodeId, resOrgCd : tempOrgCd, resOrgName : tempOrgName, projCd : getCurProjectCd()};
		
					$.post(url, params, function(result) {
						if (result == "done") {
							$("#" + curNodeId + "ResOrgName").attr("title", tempOrgName).html(tempOrgName);
							displaySuccInfo("主责方修改成功");
						}
					});
				}
			} else {
				$("#newAddedResOrgCd").val(tempOrgCd);
				$("#newAddedResOrgName").attr("title", tempOrgName).val(tempOrgName);
			}
		}
	}

	// 弹出配合方机构选择框
	function enableCorOrgNameSel(nodeId) {
		tempOrgCd = null;
		tempOrgName = null;
		curNodeId = nodeId;
		
		ymPrompt.confirmInfo({
			icoCls:"",
			title:"选择配合方机构",
			message:"<div id='deptDiv' style='padding-top: 10px;'></div>",
			useSlide:true,
			winPos:"t",
			width:350,
			height:400,
			maxBtn:false,
			allowRightMenu:true,
			handler: saveCorOrgInfo,
			afterShow:function(){
				$.post("${ctx}/planold/plan-exec-plan-node!buildCheckOrgs.action",
						function(result) {
							if (result) {
								tree = new TreePanel({
									renderTo:'deptDiv',
									'root' : eval('('+result+')'),
									'ctx':'${ctx}'
								});
								tree.isAutoChecked = false;
								tree.render();
							}
				});
			}
		});
	}
	// 保存配合方
	function saveCorOrgInfo(tp) {
		if (tp == "ok") {
			var cd = "" + tree.getChecked("id");
			var n = "" + tree.getChecked("text");
			if (curNodeId && $.trim(curNodeId).length > 0) {
				if (confirm("确认修改配合方吗？")) {
					var url = "${ctx}/planold/plan-exec-plan-node!saveCorOrgInfo.action";
					var params = { id : curNodeId, corOrgCd : cd, corOrgName : n, projCd : getCurProjectCd() };
		
					$.post(url, params, function(result) {
						if (result == "done") {
							$("#" + curNodeId + "CorOrgName").attr("title", n).html(n);
							displaySuccInfo("配合方修改成功");
						}
					});
				}
			} else {
				$("#newAddedCorOrgCd").val(cd);
				$("#newAddedCorOrgName").attr("title", n).val(n);
			}
		}
	}

	function enableOutputFilesEdit(nodeId) {
		$("#" + nodeId + "OutputFiles").hide();
		$("#" + nodeId + "OutputFilesInput").show().focus();
	}
	function saveOutputFiles(nodeId) {
		var oriOutput = $.trim($("#" + nodeId + "OutputFiles").text());
		var newOutput = $.trim($("#" + nodeId + "OutputFilesInput").val());
		if (oriOutput != newOutput) {
			updateNode(nodeId, "outputFiles", newOutput);
		}
		$("#" + nodeId + "OutputFiles").attr("title", newOutput).text(newOutput).show();
		$("#" + nodeId + "OutputFilesInput").attr("title", newOutput).val(newOutput).hide();
	}

	function enableControlNodeFlgSet(nodeId) {
		$("#" + nodeId + "ControlNodeFlg").hide();
		$("#" + nodeId + "ControlNodeFlgSelect").show().focus();
	}
	function saveControlNodeFlg(nodeId) {
		var newVal = $("#" + nodeId + "ControlNodeFlgSelect").val();
		updateNode(nodeId, "controlNodeFlg", newVal);
		$("#" + nodeId + "ControlNodeFlg").text(newVal == "1" ? "是" : "否").show();
		$("#" + nodeId + "ControlNodeFlgSelect").hide();
	}

	function deleteNode(nodeId) {
		if(!window.confirm("确定删除吗?")){
			return;
		}
		var url = "${ctx}/planold/plan-exec-plan-node!save.action";
		$.post(url,
				{
					id : nodeId,
					deletedFlg : "1",
					opType : "delete",
					projCd : getCurProjectCd()
				},
				function(result) {
					if (result == "succ") {
						$("#" + nodeId + "OpsTd").html("<span style='color: red;'>已删除</span>");
						displaySuccInfo("节点已删除");
					}
				}
		);
	}
	
	function updateNode(nodeId, type, newVal) {
		var url = "${ctx}/planold/plan-exec-plan-node!save.action";
		var params = null;
		var msg = "";

		switch (type) {
		case "nodeCd" :
			params = {id : nodeId, nodeCd : newVal, opType : type, projCd : getCurProjectCd() };
			msg = "更新节点序号成功";
			break;
		case "nodeName" :
			params = {id : nodeId, nodeName : newVal, opType : type, projCd : getCurProjectCd() };
			msg = "更新节点名称成功";
			break;
		case "outputFiles" :
			params = {id : nodeId, outputFiles : newVal, opType : type, projCd : getCurProjectCd() };
			msg = "更新关键输出成果成功";
			break;
		case "controlNodeFlg" :
			params = {id : nodeId, controlNodeFlg : newVal, opType : type, projCd : getCurProjectCd() };
			if (newVal == "1") {
				msg = "设置为控制节点成功";
			} else {
				msg = "设置为执行计划节点成功";
			}
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

	function searchNodes() {
		$("#planContainerDiv").addClass("waiting").css("background-color", "#F3F3F3");
		$("#nodeSearchForm").ajaxSubmit(function(result) {
			$("#planContainerDiv").removeClass("waiting").css("background-color", "").html(result).show();
		});
	}
	
	function addNewNode(projCd) {
		var nodeCd = $.trim($("#newAddedNodeCd").val());
		if (!validateNodeCd(nodeCd)) {
			alert("请输入格式正确的节点CD(必须为大于0的整数，例如：123)!");
			$("#newAddedNodeCd").focus();
			return;
		}
		var nodeName = $.trim($("#newAddedNodeName").val());
		if (nodeName == "") {
			alert("请输入节点名称");
			$("#newAddedNodeName").focus();
			return;
		}
		
		$("#planContainerDiv").addClass("waiting").css("background-color", "#F3F3F3");
		var url = "${ctx}/planold/plan-exec-plan-node!save.action";
		$.post(url,
				{
					projectCd : projCd,
					nodeCd : nodeCd,
					nodeName : nodeName,
					planTypeCd : getPlanTypeCd(),//来源：plan-exec-plan.js
					resOrgName : $("#newAddedResOrgName").val(),
					corOrgName : $("#newAddedCorOrgName").val(),
					outputFiles : $("#newAddedOutputFiles").val(),
					controlNodeFlg : $("#newAddedControlNodeFlg").val()
				},
				function(result) {
					if (result == "succ") {
						searchNodes();
					}
		});
	}
</script>