<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>配置加密组</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/global.jsp" %>


	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/treePanel.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/ConvertUtil.js" ></script>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"  ></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js" ></script>
	
	<!-- TreePanel机构树 -->
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
</head>

<body>
	<table style="width:100%;background-color: white;">
		<tr>
			<td valign="top" style="width:30%;">
				<div id="content" >
					<div id="group_list_panel"></div>
				</div>
			</td>
			<td valign="top" style="width:60%;">
				<table style="width:100%;">
					<tr>
						<td valign="top">
							<!-- 组信息 -->
							<div id="group_detail_panel"></div>
						</td>
					</tr>
					<tr>
						<td valign="top">
							<a id="btnSaveBatchGroupUsers" disabled="true" href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="saveBatchGroupUsers();">保存批量组用户</a>
							<div id="userInfo"></div>
							<div id="oldUserList"></div>
						</td>
					</tr>
					<tr>
						<td valign="top">
							<!-- 组内成员 -->
							<div id="rightTree" style="width: 200px; width: 0px !important; min-width: 200px !important; width: 100%; min-height: 400px; padding: 10px; overflow-x: auto; overflow-y: auto;">
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
	<!-- 存放临时变量 -->
	<input type="hidden" id="currentSelectGroupId" name="currentSelectGroupId" />
	
	<script language="javascript">
		loadGroupPanel();

		function getGroupId(){
			return $("#currentSelectGroupId").html();
		}
		function setGroupId(secGroupId, secGroupName) {
			$("#currentSelectGroupId").html(secGroupId);
			//$("#currentSelectGroupName").html("<b color='red'>" + secGroupName + "</b>");
		}

		//加载组列表
		function loadGroupPanel() {
			$("body").mask('载入组列表中,请稍后...');
			var url = "${ctx}/plaspd/plas-rank-group!list.action";
			$.post(url, function(result) {
				$("body").unmask();
				if (result) {
					$("#group_list_panel").html(result).show();
				}
			});
		}
		//查看机构
		function viewGroup(dictCd, dictName, remark, updatedDeptCd, updatedDate) {
			setGroupId(dictCd, dictName);
			loadGroupDetail(dictCd, dictName, remark, updatedDeptCd, updatedDate);
			loadrightTree(dictCd)
		}
		//加载机构人员树
		function loadGroupDetail(dictCd, dictName, remark, updatedDeptCd, updatedDate) {
			var url = "${ctx}/plaspd/plas-rank-group!getGroupDetail.action?dictCd="+ dictCd;
			$.post(url, function(result) {
				if (result) {
					$("#group_detail_panel").html(result).show();
				}
			});
		}
		//加载机构用户树
		var rightTree;
		function loadrightTree(dictCd) {
			$("#rightTree").html('');
			$("body").mask('正在载入机构人员树,请稍后...');
			$.post('${ctx}/plaspd/plas-rank-tree!getRankTreeNode.action?dictCd='+ dictCd, function(result) {
				$("body").unmask();
				if (result) {
					//TODO:未完成
					rightTree = new TreePanel({
						renderTo: "rightTree",
						'root'  : eval('('+result+')'),
						'ctx'	:'${ctx}'
					}); 
					rightTree.render();  
					rightTree.on(function(node){
						if(node.isExpand){
							node.collapse();
						}else{
							node.expand();
						}
					});
					$('#btnSaveBatchGroupUsers').linkbutton('enable');
				}
			});
		}
	
		//批量保存用户职级信息
		function saveBatchGroupUsers() {
	
				var dictCd = getGroupId();
				var addDels = getAddDeleteIds()
				var addUserIds = addDels[0];
				var delUserIds = addDels[1];
	
				if ((addUserIds == '') && (delUserIds == '')) {
					$.messager.alert('Info', "请选择授权或收回人员");
					return;
				}
	
				$("body").mask('保存设置中...');
				var url = '${ctx}/plaspd/plas-rank-tree!saveBatchGroupUsers.action';
				var data = {
					dictCd : dictCd,
					addUserIds : addUserIds,
					delUserIds : delUserIds
				}
				$.post(url, data, function(result) {
					$("body").unmask();
					if (result == 'success') {
						$("#rightTree").html('<span>授权成功.</span>');
					} else {
						$.messager.alert('Info', '授权失败');
					}
				});
				$('#btnSaveBatchGroupUsers').linkbutton('disable');
		}
	
		//获取授权或收回的用户IDs
		function getAddDeleteIds() {
			var modifyNodes = rightTree.getModifiedLeafNodes('0');//0-用户
			var addIds = modifyNodes[0];
			var delIds = modifyNodes[2];
	
			var strAddIds = "";
			var strDelIds = "";
	
			for ( var i = 0; i < addIds.length; i++) {
				if (i > 0) {
					strAddIds += ',';
				}
				strAddIds += addIds[i];
			}
	
			for ( var i = 0; i < delIds.length; i++) {
				if (i > 0) {
					strDelIds += ',';
				}
				strDelIds += delIds[i];
			}
			return [ strAddIds, strDelIds ];
		}
	</script>
</body>
</html> 