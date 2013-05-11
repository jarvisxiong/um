
/**
 * 选择甲供料类型
 */
function selectMateType(){
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
			$.post(_ctx+"/mate/mate-type!getItemTree.action", function(result){
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

//					 	var path = newParentNode.getPath('text');
//					 	alert(path);
//					 	var pathNames = path.split("/");//与TreePanel.js的pathSeparator值一致.
						
//						for(node.attributes.parentId!=null){
//							node.attrebudes.parentNode;
//						}
//						alert(parentName);
					}
				});
				tree.render();
			});
	},
	handler:function(e){
		if("ok"==e){
			$("#ownerMaterialType").val(selectId);
			$("#projectCd").val(selectType);
			$("#ownerMaterialTypeName").val(selectName);
//			$("#parentNames").val(parentName);
			//alert($("#ownerMaterialType").val()+"---"+$("#projectId").val()+"----"+$("#parentNames").val());
			
		}
	}
	});
}

