
/**
 * 选择合同类型
 */
function selectMaterialType(){
	var selectId ="";
	var selectName ="";
	var selectType ="";
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"甲供类型",
		message:"<div id='contractType1_Div'></div>",
		useSlide:true,
		winPos:"c",
		width:300,
		height:400,
		allowRightMenu:true,
		afterShow:function(){
			$.post(_ctx+"/material/material-type!getItemTree.action", function(result){
				var tree = new TreePanel({
					renderTo:"contractType1_Div",
					'root' : eval('('+result+')'),
					'ctx':_ctx
				});
				tree.on(function(node){
					if(node.attributes.children==null||node.attributes.children==""){
						selectId =node.attributes.id;
						selectName =node.attributes.text;
						selectType =node.attributes.finType;
					}
				});
				tree.render();
			});
	},
	handler:function(e){
		if("ok"==e){
			$("#ownerMaterialType").val(selectId);
			$("#projectId").val(selectType);
			$("#parentName").val(selectName);
		//	alert($("#ownerMaterialType").val()+"---"+$("#projectId").val()+"----"+$("#projectId").val());
		}
	}
	});
}

