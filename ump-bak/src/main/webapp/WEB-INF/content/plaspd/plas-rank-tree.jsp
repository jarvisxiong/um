<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>  	<!-- TreePanel机构树 -->


<table width="100%">
  <tr>
    <td width="50%">
		<div id="oldUserList"></div>
		<div id="leftPanel" style="min-height:100px;">
			<div id="rightTree" ></div>
		</div>
	</td>
    <td width="50%" style="vertical-align:top">
    
		<input style="float:right;" id="btnSaveBatchGroupUsersGroupUsers" type="button" class="buttom" onclick="saveBatchGroupUsers()" value="保存批量组用户"></input>
		<div id="userInfo"></div>
	</td>
  </tr>
</table>


<script type="text/javascript">
var selectNode;
//配置角色
var orgUserTree;
function loadRoleTree(){
	$("#rightTree").html('<div><image src="${ctx}/images/loading.gif"/>加载机构人员树...</div>').show();
	$.post('${ctx}/plaspd/plas-rank-tree!getRankTreeNode.action?dictCd='+'${dictCd}',, function(result) {
		$("#rightTree").html('');
		if (result) {
			orgUserTree = new TreePanel({
				renderTo: "rightTree",
				'root'  : eval('('+result+')'),
				'ctx'	:'${ctx}'
			}); 
			//自定义
			orgUserTree.isDelegateIcon = true;
			orgUserTree.delegateGetDelegateIcon = function(node){
				return node.iconPath;
			};

			orgUserTree.render(); 
			//修饰所有节点
			for(var k in orgUserTree.nodeHash){
				var node = orgUserTree.nodeHash[k];
				var nodeType = node.attributes.nodeType;
				if( nodeType== "0"){
					node.iconPath = _ctx + "/images/webim/male_online.jpg"; 
					
				}
				if( nodeType== "1" && node.isLeaf()){
					node.iconPath = _ctx + "/images/imgTree/page.gif";
				}
			}
			//单击事件
			orgUserTree.on(function(node){
				selectNode = node;
				var id  = node.id;
				var parentId = node.parentNode.id;
				if( $.trim(id) == '' || $.trim(id)=='0'){
					return;
				}
				
				var nodeType = node.attributes.nodeType;
				if( nodeType == "1"){//应用或角色组

					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
					//机构id
					//$('#nodeOrgId').val(node.id);
				}
			});
		}
	});
	$('#btnSaveBatchGroupUsers').linkbutton('enable');
}
//批量保存用户职级信息 
function saveBatchGroupUsers(){
	var plasSysPositionId=selectNode.id; 
	if(plasSysPositionId){
	$.messager.confirm('Confirm','Are you sure?',function(r){
		if (r){
			var dictCd = '${dictCd}';
			var addDels = getAddDeleteIds()
			var addUserIds = addDels[0];
			var delUserIds = addDels[1];
			
			if((addUserIds == '') && (delUserIds == '') ){
				$.messager.alert('Info',"请选择授权或收回人员");
				return;
			}
			$('#btnSaveBatchGroupUsers').linkbutton('disable');
			$("#rightTree").html('<span><image src="${ctx}/images/loading.gif"/>保存设置中...</span>');
			var url='${ctx}/plaspd/plas-rank-tree!saveBatchGroupUsers.action';
			var data = {dictCd : dictCd, addUserIds: addUserIds,delUserIds:delUserIds}
			$.post(url, data, function(result) {
				if(result == 'success'){
					$("#rightTree").html('<span>授权成功.</span>');
					//$.messager.alert('Info','授权成功');
				}else{
					$.messager.alert('Info','授权失败');
				}
			});
			//loadRoleTree(plasSysPositionId);
		}
		
	});
	}
	
}


</script>