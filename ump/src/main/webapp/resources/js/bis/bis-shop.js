//搜索商家种类的整棵树，haveChecked:是否要选择框，haveExpand:树是否要展开,id:是否需要ID，若有ID并有选择框
function loadSortTree(haveChecked,haveExpand,haveChild,itemDiv,sortId){
	$.post(_ctx+"/bis/bis-shop-sort!loadSortTree.action?haveChecked="+haveChecked, function(result){
		var tree = new TreePanel({
			renderTo:itemDiv,
			'root' : eval('('+result+')'),
			'ctx':_ctx
		});
		tree.on(function(node){
			$("#id").val(node.attributes.id);
			$("#sortName").val(node.attributes.text);
			$("#parentId").val(node.attributes.parentId);
			$("#parentName").val(node.attributes.parentName);
			$("#sequenceNo").val(node.attributes.orderNo);
			$("#sortType").val(node.attributes.finType);
		});
		if(haveChild){
			tree.addListener("check",function(node){
				$("#shopSortId").val(tree.getCheckedId());
			});
			/*tree.addListener("select",function(node){
				$("#shopSortId").val(tree.getCheckedId());
				alert(tree.getCheckedId());
			});*/
		}else{
			tree.addListener("check",function(node){
				if(node.isLeaf()){
					removeMainShopNodeSelect(node);
				}else{
					node.setCheck("0");
					var childNodes = node.childNodes;
					var count = childNodes.length;
					for(var i=0;i<count;i++){
						var nodes=childNodes[i];
						nodes.setCheck("0");
						var child =nodes.childNodes;
						var childLength =child.length;
						for(var j=0;j<childLength;j++){
							child[j].setCheck("0");
						}
					}
					node.parentNode.setCheck("0");
				}
				$("#shopSortId").val(tree.getAllCheckedId());
			});
			
		}
		tree.render();
		if(haveExpand)
		  doExpandTreeNode(tree.getRootNode().childNodes,sortId);
	});
}
//修改树节点，把原来选中的树勾上
function loadTreeCheckSelect(id,itemDiv){
	$.post(_ctx+"/bis/bis-shop!loadTreeCheckSelect.action?id="+id, function(result){
		var tree = new TreePanel({
			renderTo:itemDiv,
			'root' : eval('('+result+')'),
			'ctx':_ctx
		});
		tree.on(function(node){
			$("#id").val(node.attributes.id);
			$("#sortName").val(node.attributes.text);
			$("#parentId").val(node.attributes.parentId);
			$("#parentName").val(node.attributes.parentName);
			$("#sequenceNo").val(node.attributes.orderNo);
			$("#sortType").val(node.attributes.finType);
		});
		tree.haveParentClick=false;
		tree.addListener("check",function(node){
			removeMainShopNodeSelect(node);
			$("#shopSortId").val(tree.getAllCheckedId());
		});
		tree.render();
		doExpandTreeNode(tree.getRootNode().childNodes,"");
	});
}

/**
 * 控制'主力店'节点下的子节点只能选择一个。
 * @param node
 */
function removeMainShopNodeSelect(node){
	var nodeId = node.attributes.id;
	var mainShopNode = node.parentNode.parentNode;
	if(mainShopNode != null && mainShopNode != undefined){
		var mainShopName = mainShopNode.attributes.text;
		//特殊判断，‘主力店’名字如果改了，这里必须跟着改
		if("主力店" == mainShopName){
			var childNodes = mainShopNode.childNodes;
			var tmpNode = null;
			for(var i=0;i<childNodes.length;i++){
				var nodes = childNodes[i];
				var child = nodes.childNodes;
				tmpNode = nodes;
				for(var j = 0;j < child.length;j++){
					var childId = child[j].attributes.id;
					if(nodeId != childId){
						child[j].setCheck("0");
					}
					if(tmpNode != null){
						if(node.parentNode.attributes.id != tmpNode.attributes.id){
							tmpNode.setCheck("0");
						}
					}
				}
			}
		}
	}
}

//搜索页面的树结构
function sortTreeByQuery(id,itemDiv){
	$.post(_ctx+"/bis/bis-shop!sortTreeByQuery.action?id="+id, function(result){
		var tree = new TreePanel({
			renderTo:itemDiv,
			'root' : eval('('+result+')'),
			'ctx':_ctx
		});
		tree.on(function(node){
			$("#id").val(node.attributes.id);
			$("#sortName").val(node.attributes.text);
			$("#parentId").val(node.attributes.parentId);
			$("#parentName").val(node.attributes.parentName);
			$("#sequenceNo").val(node.attributes.orderNo);
			$("#sortType").val(node.attributes.finType);
		});
		tree.render();
		doExpandTreeNode(tree.getRootNode().childNodes,"");
	});
}
function doExpandTreeNode(children,sortId){
	if(children!=null&&children.length>0){
		for(var i=0;i<children.length;i++){
			var node =children[i];
			node.expand();
			if(node.attributes.id==sortId){
				node.setCheck("1");
			}
			doExpandTreeNode(node.childNodes,sortId);
		}
	}
}
function showSortAdd(){
	$("#id").val("");
	$("#sortName").val("");
	$("#parentId").val("");
	$("#parentName").val("");
	$("#sequenceNo").val("");
	$("#sortType").val("");
}
function doSortSave(){
	if($("#itemName").val()==""){
		alert("请输入项目名称");
		return;
	}
	$("#sortForm").attr("action",_ctx+"/bis/bis-shop-sort!save.action");
	$("#sortForm").ajaxSubmit(function(result) {
		if(result){
			alert(result);
		}else{
			showSortAdd();
			$("#sortDiv").text("");
			loadSortTree(false,false,false,"sortDiv");
		}
	});
}
/**
 * 搜索供应商类型的父结点
 */
function doParentSort(){
	var selectId ="";
	var selectName ="";
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"供应类别",
		message:"<div id='parentDiv'></div>",
		useSlide:true,
		winPos:"c",
		width:300,
		height:400,
		allowRightMenu:true,
		afterShow:function(){
			$.post(_ctx+"/bis/bis-shop-sort!loadSortTree.action", function(result){
				var tree = new TreePanel_New({
					renderTo:"parentDiv",
					'root' : eval('('+result+')'),
					'ctx':_ctx
				});
				tree.on(function(node){
					selectId =node.attributes.id;
					selectName =node.attributes.text;
				});
				tree.render();
			});
	},
	handler:function(e){
		if("ok"==e){
			$("#parentId").val(selectId);
			$("#parentName").val(selectName);
		}
	}
	});
}
//删除商家类别信息
function doSortDelete(){
	if($("#id").val()==""){
		alert("请选择要删除的对象");
		return;
	}
	if (confirm("确定要删除此项目？")){
		$("#itemRight").addClass("waiting");
		var param = {id:$("#id").val()};
		$.post(_ctx+"/bis/bis-shop-sort!delete.action",param, function(result) {
			if("1"==result||"2"==result){
				if("1"==result){
					alert("该类别为固定类别不能删除");
				}else{
					alert("该类别有关联数据，不能删除");
				}
			}else{
				showSortAdd();
				$("#sortDiv").text("");
				loadSortTree(false,false,false,"sortDiv");
			}
		});
	}
}
//新增/修改商家信息
function doBisShop(id){
	var url;
	if(""==id)
		url =_ctx+"/bis/bis-shop!input.action";
	else
	    url =_ctx+"/bis/bis-shop!input.action?id="+id;
	var util =parent.TabUtils;
	var typeName="商家信息";
	if(util==null)
		window.open(url);
	else{
		 util.newTab("bis-shop-input",typeName,url,true);
	}
	 
}
function doShopSave(audit){
	//nameEn\nameCn\companyName
	//中文名称或英文名称+公司名称确定其唯一性
	var nameCn =$("#nameCn").val();
	var nameEn =$("#nameEn").val();
	var companyName =$("#companyName").val();
	if(nameCn==""&&nameEn==""){
		alert("请输入中文名称或英文名称");
		return;
	}
	if(companyName==""){
		alert("请输入公司名称");
		return;
	}
	if(""==$("#bisShopId").val()&&""==$("#shopSortId").val()){
			alert("请选择商家类别");
			return false;
	}
	var req=0;
	$(".required").each(function(i){
		if(""==$(this).val()){
			req=1;
			return false;
		}
	});
	if(req==1){
		alert("请输入红色标识的必选项");
		return false;
	}
	if($("#selecManageCd").val()==1){
		$(".build_required").each(function(i){
			if(""==$(this).val()){
				req=1;
				return false;
			}
		});
	}
	if(req==1){
		alert("建筑要求都为必选项");
		return false;
	}
	if($("#founderDate").val()!=""){
		$("#founderDate").val($("#founderDate").val()+"-01-01");
	}
	$('input[alt="amount"]').each(function(i){
		if("-"==$(this).val()||""){
			$(this).val("0");
		}
	});
	if(audit){
		$("#bisShopAudit").val(audit);
	}
	TB_showMaskLayer();
	var manageCd =$("#selecManageCd").val();
	var bisShopId =$("#bisShopId").val();
	//若为主力店次主力店，则需要校验
	/*if(("1"==manageCd||"2"==manageCd)&&""==bisShopId){
		$.post(_ctx+"/bis/bis-shop!shopValidate.action",
				{nameCn:nameCn,nameEn:nameEn},//,companyName:companyName
			function(result){
					TB_removeMaskLayer();
					if(""!=$("#bisShopId").val()){
						//修改
						if(!(result=="1"||result=="0")){
							//$("#bis-shop-form").submit();
							alert("该公司已存在,无法保存");
						}else{
							$("#bis-shop-form").submit();
						}
					}else{
						//新增
						if(result=="0"){
							$("#bis-shop-form").submit();
						}else{
							alert("该品牌名已存在，无法保存！请搜索同名品牌，进行经销商(代理商)信息新增。");
						}
					//}
					
		});
	}else{
		$("#bis-shop-form").submit();
	}*/
	$("#bis-shop-form").submit();
}
function jumpPage(pageNo){
	TB_showMaskLayer("正在搜索");
	var nameCn =$("#nameCn").val();
	var nameEn =$("#nameEn").val();
	var selectManageCd =$("#selectManageCd").val();
	//var selectShopType =$("#selectShopType").val();selectShopType:selectShopType,
	var companyName =$("#companyName").val();
	var arrCheck=$("#shopSortId").val();
	var salesman =$("#salesman").val();
	var shopAudit =$("#shopAudit").val();
	var centerCd =$("#centerCd").val();
	var createDate1=$("#createDate1").val();
	var createDate2=$("#createDate2").val();
	var quickQueryText;
	//注：’快速搜索...‘这几个汉字要改的话注意在bis-shop.jsp中也要改，不然点击搜索可能有问题！
	if("快速搜索..."==$("#quickQueryText").val()){
		quickQueryText="";
	}else{
		quickQueryText=$("#quickQueryText").val().trim();
	}
	var param = {
			shopSortId:arrCheck,
			currentPageNo:pageNo,
			nameCn:nameCn,
			nameEn:nameEn,
			selectManageCd:selectManageCd,
			quickQueryText:quickQueryText,
			companyName:companyName,
			salesman:salesman,
			shopAudit:shopAudit,
			centerCd:centerCd,
			createDate1:createDate1,
			createDate2:createDate2
	};
	$.post(_ctx+"/bis/bis-shop!list.action",
			param,
		function(result){
			if(result){
				TB_removeMaskLayer();
				$("#mailRight").html(result);
			}
	});
}
function doBisShopSort(){
	parent.TabUtils.newTab("bis-shop-sort","商家类别",_ctx+"/bis/bis-shop-sort!input.action",true);
}
//删除商家信息
function doDeleteShop(id){
	if(id){
		if(confirm("确定要删除该记录吗？")){
		  var param={id:id};
		  $.post(_ctx+"/bis/bis-shop!updateStatus.action",param,function(result){
			if(result==1){
				queryBisShopList();
			}
		});
		}
	}
}
function doSelectManange(dom){
	if($(dom).val()==1){
		alert("建筑要求内容都为必选项");
		$(".build_required").addClass("required");
		$("#auditDiv").show();
	}else if($(dom).val()==2){
		$(".build_required").removeClass("required");
		$("#auditDiv").show();
	}else{
		$("#auditDiv").hide();
		$(".build_required").removeClass("required");
	}
}
function doExportShop(){
	/*if(null!=tree&&""!=tree.getCheckedId()){
		$("#shopSortId").val(tree.getCheckedId());
	}
	if(null!=treeDept&&""!=treeDept.getCheckedId()){
		$("#centerCd").val(treeDept.getCheckedId());
	}*/
	if($("#search_condtion").css("display")!="none"){
		//$("#search_condtion").hide();
		$("#mailRight").show();
	}
	$("#nameCn_exl").val($("#nameCn").val());
	$("#nameEn_exl").val($("#nameEn").val());
	$("#selectManageCd_exl").val($("#selectManageCd").val());
	//var selectShopType =$("#selectShopType").val();selectShopType:selectShopType,
	$("#companyName_exl").val($("#companyName").val());
	$("#shopSortId_exl").val($("#shopSortId").val());
	$("#salesman_exl").val($("#salesman").val());
	$("#shopAudit_exl").val($("#shopAudit").val());
	$("#centerCd_exl").val($("#centerCd").val());
	$("#createDate1_exl").val($("#createDate1").val());
	$("#createDate2_exl").val($("#createDate2").val());
	var quickQueryText;
	if("快速搜索..."==$("#quickQueryText").val()){
		$("#quickQueryText_exl").val("");
	}else{
		$("#quickQueryText_exl").val($("#quickQueryText").val());
	}
	$("#exportForm").submit();
}