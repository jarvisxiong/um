<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 


<s:if test="id == null">
	<div class="easyui-panel" title="新增角色" style="overflow-x:hidden;"></div>
</s:if>
<s:else>
	<div class="easyui-panel" title="编辑角色" style="overflow-x:hidden;"></div>
</s:else>


<table style="width:100%;">
<tr>
<td valign="top" style="width:310px;">
	<div style="width:99%;">
	<s:form id="inputForm" action="plas-role!save.action" method="post" theme="simple">
		<!-- very important! -->
		<input type="hidden" name="id" value="${plasRoleId}" id="id"/>
		<input type="hidden" name="plasAppId" value="${plasAppId}" />
		<table style="width:300px;">
			<col width="90px">
			<col />
			<tr>
				<td valign="top">应用名称:</td>
				<td> ${plasApp.appChnName}/${plasApp.appEngName}</td>
			</tr>
			<tr>
				<td valign="top">角色组名称:</td>
				<td>
					<s:select list="mapGroup" listKey="key" listValue="value" id="plasRoleGroupId" name="plasRoleGroupId"  value="plasRoleGroup.plasRoleGroupId"/>
				</td>
			</tr>
			<tr align="left">
				<td valign="top">角色编号:</td>
				<td>
					<input style="width:150px;"  class="easyui-validatebox" required="true" value="<s:if test='roleCd==null'>A_</s:if><s:else>${roleCd }</s:else>" validType="length[1,20]" name="roleCd" size="40" id="roleCd" onblur="validateRoleCd();"/>
					<span style="color:red">*</span>
					<input name="oldRoleCd" id="oldRoleCd" value="${roleCd}" type="hidden"/>
					<span id="roleCdTip" style="color:red;font-weight: bold;display: none;">角色编号已经存在！</span>
				</td>
			</tr>
			<tr>
				<td valign="top">角色业务编号:</td>
				<td>
					<input style="width:150px;" class="easyui-validatebox" validType="length[1,20]"  value="${roleBizCd }" name="roleBizCd" id="roleBizCd" required="true" size="40" >
					<span style="color:red">*</span>
					<input name="oldRoleBizCd" id="oldRoleBizCd" value="${roleBizCd}" type="hidden"/>
					<span id="roleBizCdTip" style="color:red;font-weight: bold;display: none;">角色业务编号已经存在！</span>
				</td>
			</tr>
			<tr>
				<td valign="top">角色名称:</td>
				<td>
					<input style="width:150px;" class="easyui-validatebox" validType="length[1,50]"  required="true" value="${roleName }"  id="roleName" name="roleName" size="40"/>
					<span style="color:red">*</span>
					<input name="oldRoleName" id="oldRoleName" value="${roleName}" type="hidden"/>
					<span id="roleNameTip" style="color:red;font-weight: bold;display: none;">角色名称已经存在！</span>
				</td>
			</tr>
	 		<tr>	
	 			<td valign="top">显示序号:</td>
	 			<td><input style="width:150px;" type="text" name="sequenceNo"  id="sequenceNo" value='${sequenceNo}' class="easyui-numberbox" required="true" validType="length[0,6]"></input>
	 				<span style="color:red">*</span>
	 				<br/>
	 				<span style="color:red">(数字:1-6位,显示越小越靠前)</span>
	 			</td>
	 		</tr>
			<tr>
				<td>备注:</td>
				<td><s:textarea key="remark" id="remark" name="remark" style="width:200px;height:50px;" cssClass="easyui-validatebox" validType="length[0,200]"/></td>
			</tr>	
			<s:if test="plasRoleId != null">
			<tr>
				<td style="padding-left:5px;">创建时间:</td>
				<td><s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss"/> </td>
			</tr>
			<tr>
				<td>创建人员:</td>
				<td>${creator}</td>
			</tr>
			<tr>
				<td>创建部门:</td>
				<td>${createdDeptCd}</td>
			</tr>
			<tr>
				<td style="padding-left:5px;">更新时间:</td>
				<td><s:date name="updatedDate" format="yyyy-MM-dd HH:mm:ss"/> </td>
			</tr>
			<tr>
				<td>更新人员:</td>
				<td>${updator}</td>
			</tr>
			<tr>
				<td>更新部门:</td>
				<td>${updatedDeptCd}</td>
			</tr>
			</s:if>
			<tr>
				<td colspan="2">
					<div class="toolbar" style="line-height:60px;">
						<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveRole('${plasRoleId}');">保存</a>
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="reset()">重置</a>
						<span id="tip_saverole" style="color:red;"></span>
					</div>
				</td>
			</tr>	
		</table>
	</s:form>
	</div>
</td>
<td valign="top">
	<div style="width:99%;overflow:auto;">
	<div style="text-align: left;">
		<s:if test="plasRoleId != null">
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()">删除角色</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-copy" onclick="copyRole();" id="copyRole" >复制角色</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-config" onclick="viewRolePosRelList('${plasRoleId}')">查看职位</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-config" onclick="loadRolePosRelRel('${plasRoleId}')">配置职位</a>
		</s:if>
	</div>
	<div id="viewRolePosRelListPanel" style="width:99%;">
		<!-- 查看职位 -->
		<div class="panel-header">
			角色职位明细树(请点击查看职位):
		</div>
		<table align="left" style="width:100%;">
			<tr>
				<td valign="top" style="width:100%;">
					<div id="group_role_syspos_panel" style="overflow:auto;width:99%;height: 400px;">
						<div id="rightTreePos" style="min-height:400px;"></div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div id="roleSysPanel" style="display:none;overflow:hidden;width:99%;">
		<div class="panel-header">
			配置的系统职位如下:
			<span id="userInfo"></span>
			<a style="float:right;" href="#" id="btnSaveBatchSysPos" iconCls="icon-save" disabled="true" class="easyui-linkbutton" onclick="saveBatchSysPos('${plasRoleId}')" >保存</a>
		</div>
		<table align="left" style="width:100%;">
			<tr>
				<td valign="top" style="width:100%;">
					<div id="group_syspos_panel" style="overflow:auto;width:99%;">
						<div id="rightTree" style="min-height:450px;"></div>
					</div>
					<div id="oldsysList"></div>
				</td>
			</tr>
		</table>
	</div>
	<div id="wSaveConfirm"
		 class="easyui-window" closed="true" style="min-width:400px;height:500px;padding:5px;background: #fafafa;"
		 title="授权职位变化如下:<input title='全部' type='checkbox' id='pos_all_flag' onchange=loadRolePosRelRel('${plasRoleId}')/>"
		>
		<div style="width: 100%;text-align: left;">
			<input type="button" style="cursor:pointer" class="buttom" onclick="savePopConfirm()" value="确定" />
			<input type="button" style="cursor:pointer" class="buttom" onclick="closePopConfirm()" value="取消" />
		</div>
		<div id="confirmDetailDiv">
			
		</div>
	</div>
	</div>
</td>
</tr>
</table>

<script type="text/javascript">
//校验角色cd的表达式：必须以A_打头，由字母和下划线组成
var role_cd_regExp = /^[A]{1}[_]{1}[a-zA-Z_]+$/;
$(function(){
	//不默认加载已配置的系统职位
	//viewRolePosRelList($("#id").val());
	$("#wSaveConfirm").window({
		onOpen:function(){
			$('body').mask();
		},
		onClose:function(){
			$('body').unmask();
		}
	});
});


//校验角色业务CD
function validateRoleCd(){
	var url = '${ctx}/plas/plas-role!isRoleBizCdExist.action';
	$.post(url,{roleBizCd: $('#roleBizCd').val(),oldRoleBizCd: $('#oldRoleBizCd').val()},function(result){
		if('success' == result){
			$('#roleBizCdTip').hide();
		}else{
			$('#roleBizCdTip').show();
		}
	});
}

//校验角色CD
function validateRoleCd(){
	var url = '${ctx}/plas/plas-role!isRoleCdExist.action';
	var roleCd = $('#roleCd').val();
	if(!role_cd_regExp.exec(roleCd)){
		alert('角色编号必须以"A_"开头,且由字母和“_”组成；如A_ADMIN_ORG');
		return ;
	}else{
		$.post(url,{roleCd: $('#roleCd').val(),oldRoleCd: $('#oldRoleCd').val()},function(result){
			if('success' == result){
				$('#roleCdTip').hide();
			}else{
				$('#roleCdTip').show();
			}
		});
	}
}
//保存角色
function saveRole(plasRoleId){		
	//roleCd
	var url1 = '${ctx}/plas/plas-role!isRoleCdExist.action';
	var roleCd = $('#roleCd').val();
	if(!role_cd_regExp.exec(roleCd)){
		alert('角色编号必须以[A_]开头');
		return ;
	}else{	if(roleCd)
		$.post(url1,{roleCd: $('#roleCd').val(),oldRoleCd: $('#oldRoleCd').val()},function(result){
			if('success' == result){
				//roleBizCd
				var url2 = '${ctx}/plas/plas-role!isRoleBizCdExist.action';
				$.post(url2,{roleBizCd: $('#roleBizCd').val(),oldRoleBizCd: $('#oldRoleBizCd').val()},function(result){
					if('success' == result){
						//roleName
						var url3 = '${ctx}/plas/plas-role!isRoleNameExist.action';
						$.post(url3,{roleName: $('#roleName').val(),oldRoleName: $('#oldRoleName').val()},function(result){
							if('success' == result){
								//提交
								$('#inputForm').form('submit',{
									url:'${ctx}/plas/plas-role!save.action',
									onSubmit: function(){
										var flag = $('#inputForm').form('validate');
										return flag;
									},
									success:function(result){
										if(result=='success'){
											$("#moduleDetailPanel").hide();
											$("#menuDetailPanel").hide();
											//$.messager.alert('提示','保存成功!');
											$('#tip_saverole').html('保存成功!');
											reloadGroupTree();
										}else{
											$.messager.alert('提示',result);
										}	
									}
								});		
								$('#roleNameTip').hide();
							}else{
								$('#roleNameTip').show();
							}
						});
						$('#roleBizCdTip').hide();
					}else{
						$('#roleBizCdTip').show();
					}
				});
				$('#roleCdTip').hide();
			}else{
				$('#roleCdTip').show();
			}
		});
	}
}
function reset(){
	$('#inputForm')[0].reset();
}

var gOrgPosTree;
//最近选中角色ID
var gRoleId;

function loadRolePosRelRel(roleId){
	gRoleId = roleId;
	$('#roleSysPanel').show();
	$('#roleSysPanel').prev().hide();
	$("#btnSaveBatchSysPos").linkbutton('disable');
	
	$("#rightTree").html('<div style="height:100px;width:100%;">&nbsp;</div>').mask('正在载入角色与职位关系树,请稍候...');
	
	var url = '${ctx}/plas/plas-sys-pos-role-rel!loadRoleSysPosRelTree.action';
	var data = {roleId: roleId, isAllFlg: ($('#pos_all_flag').attr('checked'))};
	$.post(url, data, function(result) {
		$("#rightTree").html('').unmask();
		if (result) {
			gOrgPosTree = new TreePanel({
				renderTo: "rightTree",
				'root'  : ( result ),
				'ctx'	:'${ctx}'
			}); 
			//自定义
			gOrgPosTree.isDelegateIcon = true;
			gOrgPosTree.delegateGetDelegateIcon = function(node){
				return node.iconPath;
			};

			//修饰所有节点
			for(var k in gOrgPosTree.nodeHash){
				var node = gOrgPosTree.nodeHash[k];
				var nodeType = node.attributes.nodeType;
				//系统职位
				if( nodeType== "3"){
					node.iconPath = _ctx + "/images/webim/male_online.jpg"; 
				}
				//机构
				else if( nodeType== "1" && node.isLeaf()){
					node.iconPath = _ctx + "/images/imgTree/page.gif";
				}
				else{
					node.iconPath = _ctx + "/images/webim/male_online.jpg"; 
				}
			}
			gOrgPosTree.render(); 
			
			//单击事件
			gOrgPosTree.on(function(node){
				var id  = node.id;
				var parentId = node.parentNode.id;
				if( $.trim(id) == '' || $.trim(id)=='0'){
					return;
				}
				
				var nodeType = node.attributes.nodeType;
				//系统职位
				if( nodeType == "1"){
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
				}
			});
			$("#btnSaveBatchSysPos").linkbutton('enable');
		}else{
			$("#btnSaveBatchSysPos").linkbutton('disable');
		}
	});
}

//查看角色对应的职位列表
function viewRolePosRelList(roleId){
	$('#roleSysPanel').hide();
	$('#roleSysPanel').prev().show();
	$("#rightTreePos").html('<div style="height:100px;width:100%;">&nbsp;</div>').mask('正在载入角色与职位关系树,请稍候...');
	var url = '${ctx}/plas/plas-sys-pos-role-rel!loadRoleSysPosRelTreeDetail.action';
	var data = {roleId: roleId, isAllFlg: ($('#pos_all_flag').attr('checked'))};
	$.post(url, data, function(result) {
		$("#rightTreePos").html('').unmask();
		if (result) {
			gOrgPosTree = new TreePanel({
				renderTo: "rightTreePos",
				'root'  : (result),
				'ctx'	:'${ctx}'
			}); 
			//自定义
			gOrgPosTree.isDelegateIcon = true;
			gOrgPosTree.delegateGetDelegateIcon = function(node){
				return node.iconPath;
			};

			//修饰所有节点
			for(var k in gOrgPosTree.nodeHash){
				var node = gOrgPosTree.nodeHash[k];
				var nodeType = node.attributes.nodeType;
				//系统职位
				if( nodeType== "3"){
					node.iconPath = _ctx + "/images/webim/male_online.jpg"; 
				}
				//机构
				else if( nodeType== "1" && node.isLeaf()){
					node.iconPath = _ctx + "/images/imgTree/page.gif";
				}
				else{
					node.iconPath = _ctx + "/images/webim/male_online.jpg"; 
				}
			}
			gOrgPosTree.render();
			
			//全部展开叶子节点
			for(var i in gOrgPosTree.nodeHash){
				var node = gOrgPosTree.nodeHash[i];
				node.expand();
			}
			//单击事件
			gOrgPosTree.on(function(node){
				var id  = node.id;
				var parentId = node.parentNode.id;
				if( $.trim(id) == '' || $.trim(id)=='0'){
					return;
				}
				var nodeType = node.attributes.nodeType;
				//系统职位
				if( nodeType == "1"){
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
				}
			});
		}else{
		}
	});
}


//批量保存授权系统职位信息
function saveBatchSysPos(roleId){
	var modifyNodes = gOrgPosTree.getModifiedLeafNodes('3');//0-系统职位
	var addDels = getAddDeleteIds(modifyNodes)
	var addPosARoleIds = addDels[0];
	var delPosARoleIds = addDels[1];
	
	if((addPosARoleIds == '') && (delPosARoleIds == '') ){
		$.messager.alert('提示',"未发生变化!");
		return;
	}

	$("#confirmDetailDiv").html(getPopDiv());
	$("#wSaveConfirm").window('open');
}

//确认保存
function savePopConfirm(){
	var modifyNodes = gOrgPosTree.getModifiedLeafNodes('3');//菜单
	var addDels    = getAddDeleteIds(modifyNodes);
	var addPosARoleIds = addDels[0];
	var delPosARoleIds = addDels[1];

	$("body").mask('正在保存,请稍候...');
	var url='${ctx}/plas/plas-sys-pos-role-rel!saveBatchRoleSysPosRel.action';
	var data = {roleId : gRoleId, addPosARoleIds: addPosARoleIds,delPosARoleIds:delPosARoleIds}
	$.post(url, data, function(result) {
		$("body").unmask();
		if(result == 'success'){
			//注意:必须重新加载菜单树,否则点击保存方法,仍可以保存.
			loadRolePosRelRel(gRoleId);
			closePopConfirm();
			//$.messager.alert('提示','保存授权成功');
		}else{
			$.messager.alert('提示','保存授权失败');
		}
	});
	
}

//关闭
function closePopConfirm(){
	$('#wSaveConfirm').window('close');
}

//返回html片段
function getPopDiv(){
	var modifyNodes = gOrgPosTree.getModifiedLeafNodes('3');
	var addIds   = modifyNodes[0];
	var addTexts = modifyNodes[1];
	var delIds 	 = modifyNodes[2];
	var delTexts = modifyNodes[3];

	//新增部分
	var addDiv   = new Array();
		addDiv.push("<table style='min-width:150px;'>");
	for(var i=0; i<addIds.length; i++){
		addDiv.push("<tr><td valign='top' id='"+addIds[i]+"'>"+addTexts[i]+"<span class='x_close' style='float:right;'></span></td></tr>");
	}
		addDiv.push("</table>");

	//删除部分
	var delDiv  = new Array();
		delDiv.push("<table style='min-width:150px;'>");
	for(var i=0; i<delIds.length; i++){
		delDiv.push("<tr><td valign='top' id='"+delIds[i]+"'>"+delTexts[i]+"<span class='x_close' style='float:right;'></span></td></tr>");
	}
		delDiv.push("</table>");

	//弹出框
	var popDiv = new Array();
	popDiv.push("<table style='min-width:300px;border:1px solid #D2E0F2;' >");
	popDiv.push("	<tr class='panel-header'><th style='text-align:center;'>新增清单</th><th style='text-align:center;'>收回清单</th></tr>" );
	popDiv.push("	<tr><td valign='top'><div>"+ addDiv.join("")+"</div></td><td valign='top'><div>"+ delDiv.join("")+"</div></td></tr>");
	popDiv.push("</table>");

	return popDiv.join("");
}
//获取授权或收回的用户IDs
function getAddDeleteIds(modifyNodes){
	var addIds   = modifyNodes[0];
	var delIds 	 = modifyNodes[2];

	var strAddIds = "";
	var strDelIds = "";
	
	for(var i=0; i<addIds.length; i++){
		if(i>0){
			strAddIds +=',';
		}
		strAddIds += addIds[i];
	}

	for(var i=0; i<delIds.length; i++){
		if(i>0){
			strDelIds +=',';
		}
		strDelIds += delIds[i];
	}
	return [strAddIds,strDelIds];
}	
	</script>
