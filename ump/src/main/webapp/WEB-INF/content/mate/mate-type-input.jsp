<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.css"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
</head>
<body>
	<div class="title_bar">
		<div class="fLeft banTitle">
			甲供料类别设置
		</div>
		<div class="fRight">
	 		<security:authorize ifAnyGranted="A_MATE_COMM">
			<input type="button" name="add"  title="新增" class="btn_new btn_add_new"  onclick="javascript:showAdd();" value="新增"/>
			<input type="button" name="save" title="保存" class="btn_new btn_green_new" onclick="javascript:doSave();" value="保存"/>
			</security:authorize>
			<security:authorize ifAnyGranted="A_MATE_TYPEDEL">
			<input type="button" name="del" title="删除" class="btn_new btn_del_new" onclick="javascript:doDelete();" value="删除" />
			</security:authorize>
			<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href;" value="刷新" />
			<font color="red">${msg}</font>
		</div>
	</div>
	
	
	<table>
	<tr>
		<td>
			<div style="width:200px; height:85%; border:1px solid #80AB73;">
				<div id="itemDiv"  style="height: 100%; width: 100%; overflow: auto;padding:5px;"></div>
			</div>
		</td>
		<td valign="top" align="left">
			<div id="itemRight" style="padding: 5px;">
			<form id="itemForm" method="post">
				<table cellpadding="0" cellspacing="0"  border="0">
					<colgroup>
						<col width="100px"/>
						<col />
					</colgroup>
					<tr>
						<td colspan="2" style="line-height:40px;"><font  color=red >注：可按多层结构、自定义维护甲供料类别树</font></td>
					</tr>
					<tr>
						<td align="right">请选择上级项目：</td>
						<td>
							<input type="text" id="parentName" onclick="doParentItem();" readonly="readonly" style="background-color: #cbcbcb;cursor: pointer;"/> 
							<s:hidden id="ownerParMaterialType" name="ownerParMaterialType" />
						</td>
					</tr>
					<tr>
						<td align="right">节点名称：</td>
						<td>
							<s:textfield  id="itemName" name="typeName" /> 
							<s:hidden style="width:180px;" id="id" name="id" /> 
							<input type="hidden" id="itemType" name="ownerMateType" />
							<input type="hidden" id="projectCd" name="projectCd" />
						</td>
					</tr>
				</table>
			</form>
			</div>
		</td>
	</tr>
</table>
<script type="text/javascript">
	$(function(){
		getItemTree("itemDiv");
	})
	function getItemTree(itemDiv){
		$('#itemDiv').empty();
		$('#itemDiv').mask('正在加载...');
		$.post("${ctx}/mate/mate-type!getItemTree.action", function(result){
			$('#itemDiv').unmask();
			var tree = new TreePanel({
				renderTo:itemDiv,
				'root' : eval('('+result+')'),
				'ctx':'${ctx}'
			});
			tree.on(function(node){
				$("#id").val(node.attributes.trueId);
				$("#itemName").val(node.attributes.text);
				$("#ownerParMaterialType").val(node.attributes.parentId);
				$("#parentName").val(node.attributes.parentName);
				$("#projectCd").val(node.attributes.finType);
				$("#itemType").val(node.attributes.finItemCd);
				
				if(node.isExpand){
					node.collapse();
				}else{
					node.expand();
				} 
			});
			tree.addListener("check",function(node){
				//getUsersByOrg(node);
				//if($("#id").val()==node.attributes.id)return;
			});
			tree.render();
		});
	}
	function showAdd(){
		$("#itemName").val("");
		$("#parentCd").val("");
		$("#projectCd").val("");
		$("#parentName").val("");
		$("#orderNo").val("");
		$("#id").val("");
		$("#itemType").val("");
	}
	function doSave(){
		if($("#itemName").val()==""){
			alert("请输入节点名称");
			return;
		}
		if($("#parentName").val()==""){
			alert("请选择上级项目");
			return;
		}
		if(null!=$("#id").val()&&""!=$("#id").val()){
			if(!(confirm("对于甲供料类型的调整会同步影响其关联数据,请确认!"))){
				return false;
			};
		}
		$("#itemForm").attr("action","${ctx}/mate/mate-type!save.action");
		$("#itemForm").ajaxSubmit(function(result) {
			showAdd();
			$("#itemDiv").text("");
			getItemTree("itemDiv");
			
		});
	}
	function doParentItem(){
		var selectId ="";
		var selectName ="";
		var selectType ="";
		ymPrompt.confirmInfo({
			icoCls:"",
			title:"甲供材料类型",
			message:"<div id='parentDiv'></div>",
			useSlide:true,
			winPos:"c",
			width:400,
			height:400,
			allowRightMenu:true,
			afterShow:function(){
				$('#parentDiv').empty();
				$('#parentDiv').mask('正在加载...');
				$.post("${ctx}/mate/mate-type!getParentTree.action", function(result){
					$('#parentDiv').unmask();
					var tree = new TreePanel({
						renderTo:"parentDiv",
						'root' : eval('('+result+')'),
						'ctx':'${ctx}'
					});
					tree.on(function(node){
						selectId =node.attributes.id;
						selectName =node.attributes.text;
						selectType =node.attributes.finType;
					});
					tree.render();
				});
		},
		handler:function(e){
			if("ok"==e){
				if(selectId=="0"){
					alert("不能选择合同类型");
					return false;
				}
				$("#ownerParMaterialType").val(selectId);
				$("#projectCd").val(selectType);
				$("#parentName").val(selectName);
				//$("#itemType").val(selectType);
			}
		}
		});
	}
	function doDelete(){
		if($("#id").val()==""){
			alert("请选择要删除的对象");
			return;
		}
		if (confirm("确定要删除此项目？")){
			$("#itemRight").addClass("waiting");
			var param = {id:$("#id").val()};
			$.post("${ctx}/mate/mate-type!delete.action",param, function(result) {
				showAdd();
				$("#itemDiv").text("");
				getItemTree("itemDiv");
			});
		}
	}
</script>
</body>
</html>