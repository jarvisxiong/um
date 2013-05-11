<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<table>
<tr valign="top">
	<%-- 左边 --%>
	<td>
	<div class="easyui-panel" region="west" split="true" style="min-width:200px;">
		<div class="panel-header" style="line-height: 22px;">
			角色包:
			<span id="successTip"></span>
			<a style="float:right;" href="#" id="btnSaveBatchRole" iconCls="icon-save" class="easyui-linkbutton" onclick="savePlasRolePackPosRel('${plasSysPositionId}')" >保存配置</a>
		</div>
		<div style="width: 100%;height: 310px;overflow-x:auto;overflow-y:auto;">
		
		<s:if test="plasRolePackList.size() > 0">
			<table style="margin-top:5px;" cellpadding="0" cellspacing="0" id="packTab">
			<s:iterator value="plasRolePackList">
			<tr valign="top" class="rank_group_row" style="cursor:pointer;" title="点击查看角色">
				<td valign="middle">
					<s:if test="check==true">
						<input type="hidden" name="oldIds" value="${plasRolePackId}"/>
						<input type="checkbox" checked="checked" name="idsY" value="${plasRolePackId}"/>
					</s:if>
					<s:else>
						<input type="checkbox" name="idsN" value="${plasRolePackId}"/>
					</s:else>
				</td>
				<td valign="middle">
					<div class="datagrid-cell" id="group_${plasRolePackId}" onclick="loadRoleTree2('${plasRolePackId}');">${packName}</div>
				</td>
			</tr>
			</s:iterator>
			</table>
		</s:if>
		<s:else>
		 	暂无角色包！
		</s:else>
		</div>
	</div>
	</td>
	<td>
	<%-- 右边 --%>
	<div class="easyui-panel" id="rolePackTreePanel" region="east" split="true" style="display:none;width:300px;">
		<div class="panel-header" style="line-height: 22px;">角色树:</div>
		<div style="width: 100%;height: 310px;overflow-x:auto;overflow-y:auto;">
		<table align="left" style="width:100%;border:1px solid #99BBE8;">
			<tr>
				<td valign="top" style="width:100%;">
					<div style="width:100%;">
						<div id="rolePackTree"></div>
					</div>
				</td>
				<td valign="middle" style="vertical-align:top;">
				</td>
			</tr>
		</table>
		</div>
	</div>
	</td>
</tr>
</table>
<script type="text/javascript">
	$(function(){
		$(".rank_group_row").bind("click", function(){
			$(".rank_group_row").css({'background-color':''});
			$(this).css({'background-color':'#FBEC88'});
		});
	});


	function savePlasRolePackPosRel(sysPositionId){
		var checkbox_addIds = new Array();
		var checkbox_delIds = new Array();
		
		var ids = new Array();
		var ids2 = new Array();
		//旧的ids
		$("#packTab input[name='oldIds']").each(function(i, dom) {
			ids.push($(dom).val());
		});
		//在旧的ids上没去除勾选的ids
		$("#packTab input[name='idsY']:checked").each(function(i, dom) {
			ids2.push($(dom).val());
		});
		//新增的ids
		$("#packTab input[name='idsN']:checked").each(function(i, dom) {
			checkbox_addIds.push($(dom).val());
		});
		if(ids.length > ids2.length){
			var delIds = compare(ids,ids2);
			if(delIds.length>0){
				for(var i=0;i<delIds.length;i++){
					checkbox_delIds.push(delIds[i]);
				}
			}
		}else if(ids = ids2 && checkbox_addIds.length==0){
			alert("未发生变化!");
			return;
		}
		var params = "addPackIds="+checkbox_addIds+"&delPackIds="+checkbox_delIds+"&plasSysPositionId="+sysPositionId;
		var url = "${ctx}/plas/plas-sys-position!saveRolePackPosRel.action";
		$.post(url, params, function(result) {
			if(result=="success"){
				$.messager.alert('提示','配置角色包成功!');
			}else{
				$.messager.alert('提示','对不起,配置角色包失败!');
			}
			loadRolePack(sysPositionId);
		});
	}
	
	function loadRoleTree2(plasRolePackId){
		$('#rolePackTreePanel').show();
		$("#rolePackTree").html('<div style="height:60px;width:100%;">&nbsp;</div>').mask('正在载入角色树,请稍候...');
		var url = '${ctx}/plas/plas-role-pack!loadRoleTree.action';
		$.post(url,{id:plasRolePackId,isChecked:true}, function(result) {
			$("#rolePackTree").html('');
			if (result) {
				roleTree = new TreePanel({
					renderTo: "rolePackTree",
					'root'  : (result),
					'ctx'	:'${ctx}'
				}); 
				//自定义
				roleTree.isDelegateIcon = true;
				roleTree.delegateGetDelegateIcon = function(node){
					return node.iconPath;
				};
				roleTree.delegateOnMouseOverNode = function(node){
					return '';
				};
				//修饰所有节点
				for(var k in roleTree.nodeHash){
					var node = roleTree.nodeHash[k];
					var nodeType = node.attributes.nodeType;
					if( nodeType== "0"){
						node.iconPath = _ctx + "/images/webim/male_online.jpg"; 
					}
					else if( nodeType== "1" && node.isLeaf()){
						node.iconPath = _ctx + "/images/imgTree/page.gif";
					}
				}
				roleTree.render(); 
				//全部展开叶子节点
				for(var i in roleTree.nodeHash){
					var node = roleTree.nodeHash[i];
					node.expand();
				}
				//单击事件
				roleTree.on(function(node){
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
					}
				});
			}
			//treePanel动态生成,只能在这里赋值height:400px;overflow-y:auto;+position: relative;
			$('#rolePackTree').find('.TreePanel').css({"height":"350px;","overflow-y": "auto","+position":"relative"});
		});
	}
	
	function compare(arr1, arr2) {
        var c = [];
        var d = arr1.slice(0);
        var e = arr2.slice(0);
        var temp;
        var i = 0;
        var j = 0;
        Array.prototype.index = function(s) { //获取元素在数组中的下标
            var temp = this.join(',');
            var len = temp.length;
            temp = temp.replace((new RegExp(',*' + s + '.*', 'g')), '');
            var len2 = temp.length;
            return len2 == len? -1 : len2? temp.split(',').length : 0;
        }
        while(i < d.length) {//循环比较d中的每一个元素是否在e中出现
            temp = d[i];
            j = e.index(temp);
            if(j >= 0) {//如果d,e中都存在此元素，则在d,e数组中删除它,并将其存入c
                c.push(temp);
                d.splice(i, 1);
                e.splice(j, 1);
            } else {//如果不存在，则此元素只存在于d中，比较对象转移为d[i + 1]
                i += 1;
            }
        }
        return d;
    }
</script>
