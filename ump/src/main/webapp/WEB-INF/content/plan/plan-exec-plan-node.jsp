<%@page import="java.util.Map"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<%-- 用于ajax搜索后显示结果列表 --%>
<div id="searchResultDiv" class="searchUserDiv" style="position: absolute;z-index:9999999; display: none;border: 1px solid gray;background-color: #e8e8e8;padding:5px;"></div>

<form id="nodeSearchForm" action="${ctx}/plan/plan-exec-plan-node!list.action" method="post" onsubmit="return false;">
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

	<div  id="configNodeContentDiv" style="width:100%">
	<table id="managerNodeList" class="table_list" cellpadding="0" cellspacing="0" style="width: expression(this.parentNode.offsetHeight >this.parentNode.scrollHeight ? '100%' :parseInt(this.parentNode.offsetWidth - 18) + 'px');">
		<thead>
			<tr class="header">
				<th width="80">序号</th>
				<th>名称</th>
				<th width="100">主责方</th>
				<th width="160">关键输出成果</th>
				<th width="40">级别</th>
				<th width="80">上级节点</th>
				<th width="80">显示节点</th>
				<th width="60" style="text-align: center;">控制节点</th>
				<th width="80" style="text-align: center;background: none;">操作</th>
			</tr>
		</thead>
		
		<tbody>
			<tr id="newNodeAddedTr" style="display: none;" class="data">
						<td>
							<input type="text" id="order1" name="order1" value="" style="width:20px;" />
							<input type="text" id="order2" name="order2" value="" style="width:20px;" />
							<input type="text" id="order3" name="order3" value="" style="width:20px;" />
							<%--
							<input type="text" id="newAddedNodeCd" name="nodeCd" style="width: 80%;" />
							 --%>
						</td>
						<td>
							<input type="text" id="newAddedNodeName" name="nodeName" style="width: 95%;" />
						</td>
						<td>
							<input type="text" class="pd-chill-tip" id="newAddedResOrgName" onfocus="enableResOrgNameSel(null);" 
								name="resOrgName" style="width: 95%; cursor: pointer;" readonly="readonly" />
							<input type="hidden" id="newAddedResOrgCd" name="resOrgCd" />
							<%-- 标志主责方为空 --%>
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
						<td>
							<select id="treeType" name="treeType">
								<option value="3">3级</option>
								<option value="2">2级</option>
								<option value="1">1级</option>
							</select>
						</td>
						<td align="left" style="cursor: pointer;" onclick="enableTreeParentNodeIdEdit('');" title="点击编辑上级节点">
							<input type="hidden" id="TreeParentNodeId" name="TreeParentNodeId"/>
							<div id="TreeParentNodeName" class="pd-chill-tip"></div>
							<input style="width:100%;display:none;" type="text" id="TreeParentNodeIdInput" name="TreeParentNodeIdInput" onblur="hideTreeParent('')" onkeyup="searchNodeList('1','','TreeParentNodeIdInput','TreeParentNodeName','TreeParentNodeIdInput')"></input>
						</td>
						<td align="left" style="cursor: pointer;" onclick="enableRelDisplayNodeIdEdit('');" title="点击编辑上级节点">
							<input type="hidden" id="RelDisplayNodeId" name="RelDisplayNodeId"/>
							<div id="RelDisplayNodeName" class="pd-chill-tip"></div>
							<input style="width:100%;display:none;" type="text" id="RelDisplayNodeIdInput" name="RelDisplayNodeIdInput" onblur="hideRelDisplay('')" onkeyup="searchNodeList('2','','RelDisplayNodeIdInput','RelDisplayNodeName','RelDisplayNodeIdInput')"></input>
						</td>
						<td style="text-align: center;">
							<select id="newAddedControlNodeFlg" name="controlNodeFlg">
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</td>
						<td>
							<div class="func_icon" style="float: left;" onclick="addNewNode('${projectCd}');">保存</div>
							<div class="func_icon" style="float: left; margin-left: 5px;" onclick="$('#newNodeAddedTr').hide();">取消</div>
						</td>
			</tr>			
			<s:iterator value="projNodeList" status="node">
				<tr class="levelTreeRow${treeType}" id="${planProjectNodeRelId}_levelTreeRow">
					<td style="cursor: pointer;" onclick="enableOrderEdit('${planProjectNodeRelId}');">
						<span id="${planProjectNodeRelId}_order1"><s:if test="order1>0">${order1}</s:if></span>
						<span id="${planProjectNodeRelId}_order2"><s:if test="order2>0">${order2}</s:if></span>
						<span id="${planProjectNodeRelId}_order3"><s:if test="order3>0">${order3}</s:if></span>
						<input onblur="saveOrder1('${planProjectNodeRelId}');" id="${planProjectNodeRelId}_order1Input" type="text" value="${order1}" style="width:20px; display: none;" />
						<input onblur="saveOrder2('${planProjectNodeRelId}');" id="${planProjectNodeRelId}_order2Input" type="text" value="${order2}" style="width:20px; display: none;" />
						<input onblur="saveOrder3('${planProjectNodeRelId}');" id="${planProjectNodeRelId}_order3Input" type="text" value="${order3}" style="width:20px; display: none;" />
					</td>
					<td style="cursor: pointer;white-space: nowrap; overflow-x:hidden; padding-right:3px;" onclick="enableNodeNameEdit('${planProjectNodeRelId}');" title="点击编辑节点名称" >
						<div class="head" id="${planProjectNodeRelId}NodeName" title="${nodeName}">
							${nodeName}
						</div>
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
					<td align="left" style="cursor: pointer;overflow-x:hidden;white-space: nowrap;padding-right:5px;" onclick="enableOutputFilesEdit('${planProjectNodeRelId}');" title="点击编辑关键成果输出">
						<div id="${planProjectNodeRelId}OutputFiles" class="pd-chill-tip" title="${outputFiles}">${outputFiles}</div>
						<input onblur="saveOutputFiles('${planProjectNodeRelId}');" id="${planProjectNodeRelId}OutputFilesInput" type="text"
							name="${planProjectNodeRelId}outputFiles" value="${outputFiles}" style="width: 90%; display: none;" />
					</td>
					<td align="left" style="cursor: pointer;overflow-x:hidden;white-space: nowrap;text-align: center;" onclick="enableTreeTypeEdit('${planProjectNodeRelId}');" title="点击编辑节点级别">
						<div id="${planProjectNodeRelId}_treeType" class="pd-chill-tip" title="${treeType}级">${treeType}<s:if test="treeType==1||treeType==2||treeType==3">级</s:if></div>
						<select id="${planProjectNodeRelId}_treeTypeInput" name="${planProjectNodeRelId}_treeType" onChange="updateNode('${planProjectNodeRelId}', 'treeType', $(this).val());" style="display:none;">
							<option value=""></option>
							<option value="1" <s:if test="treeType==1">selected</s:if>>1级</option>
							<option value="2" <s:if test="treeType==2">selected</s:if>>2级</option>
							<option value="3" <s:if test="treeType==3">selected</s:if>>3级</option>
						</select>
					</td>
					
					<%--
					<td align="left" style="cursor: pointer;" onclick="enableTreeParentNodeId('${planProjectNodeRelId}','${treeType}');"title="点击编辑父节点">
						<div id="${planProjectNodeRelId}_treeParentNodeName" class="pd-chill-tip" title="${remark}">${remark}</div>
					</td>
					 --%>
					<td align="left" style="cursor: pointer;overflow-x:hidden;white-space: nowrap;" onclick="enableTreeParentNodeIdEdit('${planProjectNodeRelId}');" title="点击编辑父节点">
						<input type="hidden" id="${planProjectNodeRelId}TreeParentNodeId" name="${planProjectNodeRelId}TreeParentNodeId" value="${treeParentNodeId}"/>
						<div id="${planProjectNodeRelId}TreeParentNodeName" class="pd-chill-tip" title="${treeParentNodeName}">${treeParentNodeName}</div>
						<input style="width:100%;display:none;" type="text" id="${planProjectNodeRelId}TreeParentNodeIdInput" name="${planProjectNodeRelId}TreeParentNodeIdInput" onblur="hideTreeParent('${planProjectNodeRelId}')" onkeyup="searchNodeList('1','${planProjectNodeRelId}','${planProjectNodeRelId}TreeParentNodeIdInput','${planProjectNodeRelId}TreeParentNodeName','${planProjectNodeRelId}TreeParentNodeIdInput')"></input>
					</td>
					<td align="left" style="cursor: pointer;overflow-x:hidden;white-space: nowrap;" onclick="enableRelDisplayNodeIdEdit('${planProjectNodeRelId}');" title="点击编辑父节点">
						<input type="hidden" id="${planProjectNodeRelId}RelDisplayNodeId" name="${planProjectNodeRelId}RelDisplayNodeId" value="${relDisplayNodeId}"/>
						<div id="${planProjectNodeRelId}RelDisplayNodeName" class="pd-chill-tip" title="${relDisplayNodeName}">${relDisplayNodeName}</div>
						<input style="width:100%;display:none;" type="text" id="${planProjectNodeRelId}RelDisplayNodeIdInput" name="${planProjectNodeRelId}RelDisplayNodeIdInput" onblur="hideRelDisplay('${planProjectNodeRelId}')" onkeyup="searchNodeList('2','${planProjectNodeRelId}','${planProjectNodeRelId}RelDisplayNodeIdInput','${planProjectNodeRelId}RelDisplayNodeName','${planProjectNodeRelId}RelDisplayNodeIdInput')"></input>
					</td>
					
					<td align="left" style="cursor: pointer;text-align: center;" onclick="enableControlNodeFlgSet('${planProjectNodeRelId}');" title="点击设置当前节点是否为控制计划节点">
						<div id="${planProjectNodeRelId}ControlNodeFlg">
							<s:if test="controlNodeFlg == 1">是</s:if>
							<s:else>否</s:else>
						</div>
						<select onblur="$(this).prev().show(); $(this).hide();" onclick="jQuery.Event(event).stopPropagation();" style="display: none;" onchange="saveControlNodeFlg('${planProjectNodeRelId}');"
							id="${planProjectNodeRelId}ControlNodeFlgSelect" name="${planProjectNodeRelId}controlNodeFlg">
							<option value="0" <s:if test="controlNodeFlg == 0">selected="selected"</s:if>>否</option>
							<option value="1" <s:if test="controlNodeFlg == 1">selected="selected"</s:if>>是</option>
						</select>
					</td>
					<td id="${planProjectNodeRelId}OpsTd" style="text-align: center;">
						<% if(((Map)JspUtil.findValue("mapExecCostPlan")).containsKey(JspUtil.findString("nodeCd"))){ %>
							<div  class="pd-chill-tip" title="成本节点名称: <%= (String)((Map)JspUtil.findValue("mapExecCostPlan")).get(JspUtil.findString("nodeCd"))%>">
								已关联
							</div>
						<% }else{  %>
							<img style="cursor: pointer; margin-left: 10px;" 
								 title="您可以点击删除节点"  src="${ctx}/pics/note/note_del.gif"
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

		/*
		if (!validateNodeCd(newNodeCd)) {
			alert("请输入格式正确的节点CD(必须为大于0的整数，例如：123)!");
			$("#" + nodeId + "NodeCdInput").show().focus();
			return;
		}
		*/
		
		if (oriNodeCd != newNodeCd) {
			updateNode(nodeId, "nodeCd", newNodeCd);
		}

		$("#" + nodeId + "NodeCd").text(newNodeCd).show();
		$("#" + nodeId + "NodeCdInput").val(newNodeCd).hide();
	}
	
	function enableOrderEdit(nodeId) {
		$("#" + nodeId + "_order1").hide();
		$("#" + nodeId + "_order1Input").show();
		$("#" + nodeId + "_order2").hide();
		$("#" + nodeId + "_order2Input").show();
		$("#" + nodeId + "_order3").hide();
		$("#" + nodeId + "_order3Input").show();
	}
	function doUpOrder(nodeId) {
		$("#" + nodeId + "_order1").show();
		$("#" + nodeId + "_order1Input").hide();
		$("#" + nodeId + "_order2").show();
		$("#" + nodeId + "_order2Input").hide();
		$("#" + nodeId + "_order3").show();
		$("#" + nodeId + "_order3Input").hide();
	}
	function saveOrder1(nodeId) {
		var oriNodeCd = $.trim($("#" + nodeId + "_order1").text());
		var newNodeCd = $.trim($("#" + nodeId + "_order1Input").val());
		if(oriNodeCd==newNodeCd || ""==oriNodeCd&&0==newNodeCd){
			return false;
		}
		if (!validateNodeCd(newNodeCd)) {
			alert("请输入格式正确的节点CD(必须为大于0的整数，例如：123)!");
			$("#" + nodeId + "_order1Input").show().focus();
			return;
		}
		if (oriNodeCd != newNodeCd) {
			updateNode(nodeId, "order1", newNodeCd);
		}
		$("#" + nodeId + "_order1").text(newNodeCd);
		$("#" + nodeId + "_order1Input").val(newNodeCd);
		doUpOrder(nodeId);
	}
	function saveOrder2(nodeId) {
		var oriNodeCd = $.trim($("#" + nodeId + "_order2").text());
		var newNodeCd = $.trim($("#" + nodeId + "_order2Input").val());
		if(oriNodeCd==newNodeCd || ""==oriNodeCd&&0==newNodeCd){
			return false;
		}
		if (!validateNodeCd(newNodeCd)) {
			alert("请输入格式正确的节点CD(必须为大于0的整数，例如：123)!");
			$("#" + nodeId + "_order2Input").show().focus();
			return;
		}
		if (oriNodeCd != newNodeCd) {
			updateNode(nodeId, "order2", newNodeCd);
		}
		$("#" + nodeId + "_order2").text(newNodeCd);
		$("#" + nodeId + "_order2Input").val(newNodeCd);
		doUpOrder(nodeId);
	}
	function saveOrder3(nodeId) {
		var oriNodeCd = $.trim($("#" + nodeId + "_order3").text());
		var newNodeCd = $.trim($("#" + nodeId + "_order3Input").val());
		if(oriNodeCd==newNodeCd || ""==oriNodeCd&&0==newNodeCd){
			return false;
		}
		if (!validateNodeCd(newNodeCd)) {
			alert("请输入格式正确的节点CD(必须为大于0的整数，例如：123)!");
			$("#" + nodeId + "_order3Input").show().focus();
			return;
		}
		if (oriNodeCd != newNodeCd) {
			updateNode(nodeId, "order3", newNodeCd);
		}
		$("#" + nodeId + "_order3").text(newNodeCd);
		$("#" + nodeId + "_order3Input").val(newNodeCd);
		doUpOrder(nodeId);
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
				$.post("${ctx}/plan/plan-exec-plan-node!buildOrgs.action",
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
					var url = "${ctx}/plan/plan-exec-plan-node!saveResOrgInfo.action";
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
				$.post("${ctx}/plan/plan-exec-plan-node!buildCheckOrgs.action",
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
					var url = "${ctx}/plan/plan-exec-plan-node!saveCorOrgInfo.action";
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
	function enableTreeTypeEdit(nodeId) {
		$("#" + nodeId + "_treeType").hide();
		$("#" + nodeId + "_treeTypeInput").show().focus();
	}
	function enableTreeParentNodeIdEdit(nodeId) {
		/*
		if($('#'+nodeId+'_treeTypeInput').val() == '1'){
			return;
		}
		*/
		$("#" + nodeId + "TreeParentNodeId").hide();
		$("#" + nodeId + "TreeParentNodeName").hide();
		$("#" + nodeId + "TreeParentNodeIdInput").val($("#" + nodeId + "TreeParentNodeName").text()).show().focus();
	} 
	function saveTreeParentNodeId(nodeId){
		var oriOutput = $.trim($("#" + nodeId + "TreeParentNodeId").val());
		var newOutput = $.trim($("#" + nodeId + "TreeParentNodeIdInput").val());
		if (oriOutput != newOutput) {
			if(nodeId!=''){//新增记录无ID
				$("#" + nodeId + "TreeParentNodeId").val(newOutput);
				$("#" + nodeId + "TreeParentNodeIdInput").attr("title", newOutput).val(newOutput).hide();
				updateNode(nodeId, "treeParentNodeId", newOutput);
			}
		}
	}
	function enableRelDisplayNodeIdEdit(nodeId) {
		/*
		if($('#'+nodeId+'_treeTypeInput').val() == '1'){
			return;
		}
		*/
		$("#" + nodeId + "RelDisplayNodeId").hide();
		$("#" + nodeId + "RelDisplayNodeName").hide();
		$("#" + nodeId + "RelDisplayNodeIdInput").val($("#" + nodeId + "RelDisplayNodeName").text()).show().focus();
	} 
	function saveRelDisplayNodeId(nodeId){
		var oriOutput = $.trim($("#" + nodeId + "RelDisplayNodeId").val());
		var newOutput = $.trim($("#" + nodeId + "RelDisplayNodeIdInput").val());
		if (oriOutput != newOutput) {
			if(nodeId!=''){//新增记录无ID
				$("#" + nodeId + "RelDisplayNodeId").val(newOutput);
				$("#" + nodeId + "RelDisplayNodeIdInput").attr("title", newOutput).val(newOutput).hide();
				updateNode(nodeId, "relDisplayNodeId", newOutput);
			}
		}
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
		var url = "${ctx}/plan/plan-exec-plan-node!save.action";
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
						$('#'+nodeId+'_levelTreeRow').remove();
					}
				}
		);
	}
	function updateNode(nodeId, type, newVal) {
		var url = "${ctx}/plan/plan-exec-plan-node!save.action";
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
		case "treeType" :
			$('#' + nodeId + '_levelTreeRow').removeClass().addClass('levelTreeRow'+newVal);
			params = {id : nodeId, treeType : newVal, opType : type, projCd : getCurProjectCd() };
			msg = "更新节点级别成功";
			break;
		case "treeParentNodeId" :
			params = {id : nodeId, treeParentNodeId : newVal, opType : type, projCd : getCurProjectCd(),ifChangeTreeParent:true };
			msg = "更新父级节点成功";
			break;
		case "relDisplayNodeId" :
			params = {id : nodeId, relDisplayNodeId : newVal, opType : type, projCd : getCurProjectCd(),ifChangeRelDisplay:true};
			msg = "更新显示节点成功";
			break;
		case "remark" :
			params = {id : nodeId, remark : newVal, opType : type, projCd : getCurProjectCd() };
			break;
		case "order1" :
			params = {id : nodeId, order1 : newVal, opType : type, projCd : getCurProjectCd() };
			msg = "更新1级节点序号成功";
			break;
		case "order2" :
			params = {id : nodeId, order2 : newVal, opType : type, projCd : getCurProjectCd() };
			msg = "更新2级节点序号成功";
			break;
		case "order3" :
			params = {id : nodeId, order3 : newVal, opType : type, projCd : getCurProjectCd() };
			msg = "更新3级节点序号成功";
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
				/*
				if (i == 0 && num == 0) {
					return false;
				}
				*/
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
		/*
		var nodeCd = $.trim($("#newAddedNodeCd").val());
		if (!validateNodeCd(nodeCd)) {
			alert("请输入格式正确的节点CD(必须为大于0的整数，例如：123)!");
			$("#newAddedNodeCd").focus();
			return;
		}
		*/
		var order1 = $.trim($("#order1").val());
		if (!validateNodeCd(order1)) {
			alert("请输入格式正确的节点CD(必须为大于0的整数，例如：123)!");
			$("#order1").focus();
			return;
		} 
		var order2 = $.trim($("#order2").val());
		if (!validateNodeCd(order2)) {
			alert("请输入格式正确的节点CD(必须为大于0的整数，例如：123)!");
			$("#order2").focus();
			return;
		} 
		var order3 = $.trim($("#order3").val());
		if (!validateNodeCd(order3)) {
			alert("请输入格式正确的节点CD(必须为大于0的整数，例如：123)!");
			$("#order3").focus();
			return;
		} 
		
		var nodeName = $.trim($("#newAddedNodeName").val());
		if (nodeName == "") {
			alert("请输入节点名称");
			$("#newAddedNodeName").focus();
			return;
		}
		$("#planContainerDiv").addClass("waiting").css("background-color", "#F3F3F3");
		var url = "${ctx}/plan/plan-exec-plan-node!save.action";
		$.post(url,
		{
			projectCd : projCd,
			order1 : order1,
			order2 : order2,
			order3 : order3,
			nodeName : nodeName,
			planTypeCd : getPlanTypeCd(),//来源：plan-exec-plan.js
			resOrgName : $("#newAddedResOrgName").val(),
			corOrgName : $("#newAddedCorOrgName").val(),
			outputFiles : $("#newAddedOutputFiles").val(),
			controlNodeFlg : $("#newAddedControlNodeFlg").val(),
			treeType: $('#treeType').val(),
			treeParentNodeId : $("#TreeParentNodeId").val(),
			relDisplayNodeId: $("#RelDisplayNodeId").val(),
			ifChangeTreeParent: true,
			ifChangeRelDisplay: true
		},
		function(result) {
			if (result == "succ") {
				searchNodes();
			}
		});
	}
	//ajax 搜索用户列表
	var searchMgr;
	function searchNodeList(fieldType,relNodeId,idDomId,idDomName,idDomInput){
		//清空
		//$("#"+idDomId).val('');
		if(searchMgr)clearTimeout(searchMgr);
		
		searchMgr = setTimeout(function(){
			//plan-exec-plan.js
			var projectCd = getCurProjectCd();
			var planTypeCd = getPlanTypeCd();
			var filterNodeName =$('#'+idDomInput).val();
			$.post("${ctx}/plan/plan-exec-plan-node!searchNodeList.action",{projectCd:projectCd, planTypeCd:planTypeCd, relNodeId: relNodeId, filterNodeName: filterNodeName},function(result){
				var $offset = $("#"+idDomId).offset();
				$("#searchResultDiv").css({left:$offset.left,top:$offset.top+$("#"+idDomId).height()+5}).empty().show();
				result = eval(result);
				
				var arr = new Array();
				$.each(result,function(i,node){
					var tmp =(node.treeType=='')?'':'级|';
					arr.push("<div style='cursor:pointer;' tnodeRelId='"+node.nodeRelId+"' tnodeCd='"+node.nodeCd+"' ttreeType='"+node.treeType+"' tnodeName='"+node.nodeName+"'><span>"+ node.treeType+tmp+"</span><span>"+node.nodeName+"</span></div>");
				});
				$("#searchResultDiv").append(arr.join(""));
				$("#searchResultDiv div")
				.hover(function(){
					$(this).css({'backgroundColor':'#4d7b9d','color':'#fff'});
				},function(){
					$(this).css({'backgroundColor':'#e8e8e8','color':'#000'});
				})
				.click(function(){
					$("#searchResultDiv").slideUp(200);
					$("#searchResultDiv").hide();
					
					var tid = $(this).attr("tnodeRelId");
					var tnodeName = $(this).attr("tnodeName");
					var ttreeType = $(this).attr("ttreeType");
					//关闭
					if(tnodeName=='关闭'){
						return;
					} 
					//设置
					$('#'+idDomName).text(tnodeName).show();
					$('#'+idDomInput).val(tid).hide();
					
					//若是新增记录
					if(fieldType=='1'){
						if(relNodeId == ''){
							$('#TreeParentNodeId').val(tid);
						}
						saveTreeParentNodeId(relNodeId);
						if(ttreeType && ttreeType!= ''){
							$('#'+relNodeId+'_treeTypeInput').val(parseInt(ttreeType)+1);
						}
					}else{
						if(relNodeId == ''){
							$('#RelDisplayNodeId').val(tid);
						}
						saveRelDisplayNodeId(relNodeId);
					}
				});
			});
		}, 800);
			
		$(document).bind('click',function(event){
			var event  = window.event || event;
		    var obj = event.srcElement ? event.srcElement : event.target;
		    if( obj != $("#"+idDomId)[0] && obj != $("#searchResultDiv")[0]){
		    	$("#searchResultDiv").slideUp(200);
		    	if( $("#"+idDomId).val() == ''){
		    		//$("#"+idDomName).val('');
		    	}
		    }
		    $(document).unbind('click');
		});
	}
	function hideTreeParent(nodeId){
		if($("#searchResultDiv attr[display=none]")){
			$('#'+nodeId + 'TreeParentNodeName').show();
			$('#'+nodeId + 'TreeParentNodeIdInput').hide();
		}
	}
	function hideRelDisplay(nodeId){
		if($("#searchResultDiv attr[display=none]")){
			$('#'+nodeId + 'RelDisplayNodeName').show();
			$('#'+nodeId + 'RelDisplayNodeIdInput').hide();
		}
	}
</script>