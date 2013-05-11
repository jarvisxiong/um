/**
 * 页面 JS Tree 的实现
 * Copyright (c) 2009 YaoYiLang 
 * @email  redrainyi@gmail.com
 * @datetime 2009-10-01 12:11:08
 * @version 2.0
 * 
 * @author hy
 * @datetime 2011-11-15 
 * 支持异步加载
 */

getUniqueID = function(){
	return ('_' + (new Date().getTime()) + '_' + parseInt(Math.random()*10000));
};


TreePanel = function(config){
	this.nodeHash = {};
	this.root = null;
	this.ctx = '';
	this._id = getUniqueID();
	this.iconPath = '/images/imgTree/';
	this.clickListeners = [];
	this.checkListeners = [];
	this.element = document.createElement('div');
	this.element.className='TreePanel';
	this.container = null;
	this.focusNode = null;
	this.on=this.addListener;
	this.check = this.checkListener;
	this.initialize.apply(this, arguments);
	this.isDelegateIcon = false;//自定义叶节点图标
	this.isAutoChecked = true; // 当点击某个节点时是否自动勾选上级或者下级节点
    this.haveClickText =false; //自定义叶子节点事件，点击叶子节点是否能选中
    this.isExpendSelect= false;//是否扩展选中事件
    this.haveParentClick=true;//点击父节点是否能选中checkbox

};

TreePanel.prototype.delegateGetDelegateIcon = function(node){
	return '';
};
TreePanel.prototype.delegateOnMouseOverNode = function(elem, node){
	return '';
};
TreePanel.prototype.delegateOnMouseOutNode = function(elem, node){
	return '';
};
TreePanel.prototype.delegateOnDblClick = function(node){
	return '';
};
TreePanel.prototype.delegateOnSelect = function(node){
	return '';
};


TreePanel.prototype={
	initialize : function(config){
		var renderTo = config['renderTo'];
		this.container = (String.isInstance(renderTo) ? document.getElementById(renderTo) : renderTo ) || document.body;
		var handler= config['handler'];
		if(Function.isInstance(handler)){
			this.addListener('click',handler);
		}
		this.iconPath=config['ctx']+this.iconPath;
		var iconPath = config['iconPath'];
		if(String.isInstance(iconPath)){
			this.iconPath = iconPath;
		}
		this.haveClickText =config['haveClickText'];
		var node = new TreeNode(config.root,this);
		this.setRootNode(node);
	},
	pathSeparator: "/",
	getRootNode : function(){
		return this.root;
	},
	setRootNode : function(node){
		this.root = node;
		node.ownerTree = this;
		//this.root.setOwnerTree(this);
		this.registerNode(node);
		node.cascade((function(node){
			this.registerNode(node);
		}),this);
	},
	getNodeById : function(id){
		return this.nodeHash[id];
	},
	getNodeByAttr : function(attrName,attrValue){
		for(var k in this.nodeHash){
			var node = this.nodeHash[k];
			if(node.attributes[attrName] == attrValue){
				return node;
			}
		}
		return null;
	},
	registerNode : function(node){
		this.nodeHash[node.id] = node;
	},
	unregisterNode : function(node){
		delete this.nodeHash[node.id];
	},
	render : function(){
		this.element.innerHTML = '';
		this.root.render();
		if(this.container){
			this.container.appendChild(this.element);
		}
		this.initEvent();
	},
	getIcon : function(icontype){
		return this.iconPath + this.icon[icontype];
	},
	getIconByType : function(type){
		return type;
	},
	initEvent : function(){
		var _this = this;
		this.element.onclick=function(event){
			var event = event || window.event;
			var elem=(event.srcElement || event.target);
			var _type = elem['_type_'];
			if(typeof(_type) === undefined){
				return;
			}
			elem = elem.parentNode || elem.parentElement;
			if(_type == 'clip'){
				if(elem.indexId!=null){
					var node = _this.nodeHash[ elem.indexId ];
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
				}
			}else if(_this.haveClickText ==true && _type == 'text'){
				var node = _this.nodeHash[elem.indexId];
				node.onCheck();
				for(var i=0; i < _this.checkListeners.length; i++){
					_this.checkListeners[i](node);
				}
			}else if(_type == 'icon' || _type == 'text'){
				var node = _this.nodeHash[elem.indexId];
				for(var i=0; i < _this.clickListeners.length; i++){
					_this.clickListeners[i](node);
				}
				_this.setFocusNode(node);
			}else if(_this.haveParentClick ==false&&_type == 'checked'){
				var node =_this.nodeHash[elem.indexId];
				if(node.isLeaf()){
					var node = _this.nodeHash[elem.indexId];
					node.onCheck();
					for(var i=0; i < _this.checkListeners.length; i++){
						_this.checkListeners[i](node);
					}
				}
			}else if(_type == 'checked'){
				var node = _this.nodeHash[elem.indexId];
				node.onCheck();
				for(var i=0; i < _this.checkListeners.length; i++){
					_this.checkListeners[i](node);
				}
			}
		};

		//自定义WEBIM(屏蔽异常)
		this.element.onmouseover=function(event){
			try{
				var event = event || window.event;
				var elem=(event.srcElement || event.target);
				var _type = elem['_type_'];
				if(typeof(_type) === undefined){
					return;
				}
				elem = elem.parentNode || elem.parentElement;
				if(_type == 'clip' || _type == 'icon' || _type == 'text'){
					if(elem.indexId!=null){
						var node = _this.nodeHash[ elem.indexId ];
						try{
							//_this.delegateOnMouseOverNode(elem, node);
						}catch(e){
						}
					}
				}
			}catch(e){
				//alert(e);
			}
		};  
		
		//双击人员
		this.element.ondblclick=function(event){
			try{
				var event = event || window.event;
				var elem=(event.srcElement || event.target);
				var _type = elem['_type_'];
				if(typeof(_type) === undefined){
					return;
				}
				elem = elem.parentNode || elem.parentElement;
				if(_type == 'clip' || _type == 'icon' || _type == 'text'){
					if(elem.indexId!=null){
						var node = _this.nodeHash[ elem.indexId ];
						_this.delegateOnDblClick(node);
					}
				}
			}catch(e){
				//alert(e);
			}
		};
	},
	getModified: function(name,nType,relIdField){
		var checkeds = [];
		var checkedTypes = [];
		var checkedRels = [];
		name = name||'id';
		relIdField=relIdField||'relId';
		for(var k in this.nodeHash){
			var node = this.nodeHash[k];
			if(node.checked!=node.checkedOld){
				var nodeType = node.attributes["nodeType"]+"";
				nodeType=nodeType==null?"":nodeType;
				var indx=nType.indexOf(nodeType);
				var relId = node.attributes[relIdField];
				relId=relId==null?"":relId;
				if(indx!=-1&&(relId==""||node.checked=='0')){
					checkedTypes.push(nodeType);
					var value = node.attributes[name];
					checkeds.push(value);
					if (relId!=""&&node.checked=='2'){
						relId="";
					}
					checkedRels.push(relId);
				}
			}
		}
		return [checkeds,checkedTypes,checkedRels];
	},
	//发生变化的叶子节点
	getModifiedLeafNodes: function(modifyNodeType){
		
		var addIds 	 = [];
		var addTexts = [];
		var delIds 	 = [];
		var delTexts = [];
		var addExtParams = [];
		var delExtParams = [];
		
		for(var k in this.nodeHash){
			var node = this.nodeHash[k];
			if( node.isLeaf()){
				var newChecked = node.checked;
				var oldChecked = node.checkedOld;
				var nodeType   = node.attributes.nodeType;
				if(newChecked != oldChecked){
					var index = modifyNodeType.indexOf(nodeType);
					if( index == -1){
						continue;
					}
					//新增
					if(newChecked == '1'){
						addIds.push(node.attributes.entityId);
						addTexts.push(node.attributes.text);
						addExtParams.push(node.attributes.extParam);
					}
					//删除
					if(newChecked == '0'){
						delIds.push(node.attributes.entityId);
						delTexts.push(node.attributes.text);
						delExtParams.push(node.attributes.extParam);
					}
				}
			}
		}
		return [addIds,addTexts,delIds,delTexts,addExtParams,delExtParams];
	},
	getChecked : function(name,isLeaf){
		var checkeds = [];
		name = name||'id';
		isLeaf=(typeof isLeaf!="undefined")||isLeaf;
		for(var k in this.nodeHash){
			var node = this.nodeHash[k];
			if(node.checked==1){
				if(!isLeaf || node.isLeaf()){
				var value = node.attributes[name];
				if(value != null){
					checkeds.push(value);
				}
			}
			}
		}
		return checkeds;
	},
	getCheckedId : function(name,isLeaf){
		var checkeds = [];
		name = name||'id';
		isLeaf=(typeof isLeaf!="undefined")||isLeaf;
		for(var k in this.nodeHash){
			var node = this.nodeHash[k];
			if(node.checked==1){
				if(node.childNodes.length==0){
				var value = node.attributes[name];
				if(value != null){
					checkeds.push(value);
				}
			   }
			}
		}
		return checkeds;
	},
	getAllCheckedId : function(name,isLeaf){
		var checkeds = [];
		name = name||'id';
		isLeaf=(typeof isLeaf!="undefined")||isLeaf;
		for(var k in this.nodeHash){
			var node = this.nodeHash[k];
			if(node.checked!=0&&"0"!=node.attributes[name]){
				//if(node.childNodes.length==0){
				var value = node.attributes[name];
				if(value != null){
					checkeds.push(value);
				}
			  // }
			}
		}
		return checkeds;
	},
	getCheckedName:function(name,isLeaf){
		var names = [];
		name = name||'text';
		isLeaf=(typeof isLeaf!="undefined")||isLeaf;
		for(var k in this.nodeHash){
			var node = this.nodeHash[k];
			if(node.checked==1){
				if(node.childNodes.length==0){
				var value = node.attributes[name];
				if(value != null){
					names.push(value);
				}
			}
			}
		}
		return names;
	},
	getCheckedType:function(name,isLeaf){
		var names = [];
		name = name||'finType';
		isLeaf=(typeof isLeaf!="undefined")||isLeaf;
		for(var k in this.nodeHash){
			var node = this.nodeHash[k];
			if(node.checked==1){
				if(node.childNodes.length==0){
				var value = node.attributes[name];
				if(value != null){
					var haveValue="1";
					for(var i=0;i<names.length;i++){
						if(names[i]==value){
							haveValue ="0";
							break;
						}
					}
					if(haveValue=="1")
					   names.push(value);
				}
			}
			}
		}
		return names;
	},
	addListener : function(type,handler){
		if(Function.isInstance(type)){
			handler=type;
			type === 'click';
		}
		if(type=="check"){
			this.checkListeners.push(handler);
		}else{
			this.clickListeners.push(handler);	
		}
	},
	
	setFocusNode : function(node){
		if(this.focusNode){
			try{
				this.focusNode.unselect();
			}catch(e){
				//alert(e.msg);
			}
		}
		this.focusNode = node;
		if(node.parentNode){
			node.parentNode.expand();
		}
		node.select();
	},
	toString : function(){
		return "[Tree"+(this.id?" "+this.id:"")+"]";
	},
	//获取变化节点,新增，删除
	getAddDeleteIds : function(nodeType){
		if(!nodeType){
			alert('请传入节点类型');
			return ['',''];
		}
	 	var modifyNodes = this.getModifiedLeafNodes(nodeType);
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
	 },
	//获取新增或者去除节点复选框提示信息
	getPopDiv : function(nodeType,textAdd,textDel){
		var modifyNodes = this.getModifiedLeafNodes(nodeType);//获取节点类型
		var addIds   = modifyNodes[0];
		var addTexts = modifyNodes[1];
		var delIds 	 = modifyNodes[2];
		var delTexts = modifyNodes[3];

		//新增部分
		var addDiv   = new Array();
			addDiv.push("<table style='width:100%'>");
		for(var i=0; i<addIds.length; i++){
			addDiv.push("<tr><td valign='top' id='"+addIds[i]+"'>"+addTexts[i]+"<span class='x_close' style='float:right;'></span></td></tr>");
		}
			addDiv.push("</table>");

		//删除部分
		var delDiv  = new Array();
			delDiv.push("<table style='width:100%;'>");
		for(var i=0; i<delIds.length; i++){
			delDiv.push("<tr><td valign='top' id='"+delIds[i]+"'>"+delTexts[i]+"<span class='x_close' style='float:right;'></span></td></tr>");
		}
			delDiv.push("</table>");

		//弹出框
		var popDiv = new Array();
		popDiv.push("<table style='width:100%;border:1px solid #D2E0F2;'>");
		popDiv.push("	<tr class='panel-header'><th style='text-align:center;width:50%;'>"+textAdd+"</th><th style='text-align:center;width:50%;'>"+textDel+"</th></tr>" );
		popDiv.push("	<tr><td valign='top'><div>"+ addDiv.join("")+"</div></td><td><div>"+ delDiv.join("")+"</div></td></tr>");
		popDiv.push("</table>");

		return popDiv.join("");
	},
	//搜索节点,并定位
	//nodeName:模糊搜索节点名称;cNode:当前选中节点
	searchNode: function(nodeName,cNode){
		var findFlag = true;
		if( cNode){
			findFlag = false;
		}
		
		for(var k in this.nodeHash){
			var node = this.nodeHash[k];
			if(!findFlag){
				if(node.attributes.text == cNode.attributes.text){
					findFlag = true;
				}else{
					continue;
				}
			}else{
				if(node.attributes.text.indexOf(nodeName) > -1){
					return node;
				}
			}
		}
		return null;
	}
};

TreePanel.prototype.icon = {
	root		: 'root.gif',//root.gif
	folder		: 'folder.gif',//folder.gif
	folderOpen	: 'folderopen.gif',//folderopen.gif
	node		: 'page.gif',
	empty		: 'empty.gif',
	line		: 'line.gif',//
	join		: 'join.gif',//join.gif
	joinBottom	: 'joinbottom.gif',//joinbottom.gif
	plus		: 'plus.gif',//plus.gif
	plusBottom	: 'plusbottom.gif',//plusbottom.gif
	minus		: 'minus.gif',//minus.gif
	minusBottom	: 'minusbottom.gif',//minusbottom.gif
	nlPlus		: 'nolines_plus.gif',//nolines_plus.gif
	nlMinus		: 'nolines_minus.gif',//nolines_minus.gif
	checkbox0	: 'checkbox_0.gif',
	checkbox1	: 'checkbox_1.gif',
	checkbox2	: 'checkbox_2.gif',
	org			: 'org.gif',
	edp			: 'edp.gif',
	emp			: 'emp.gif' 
};


TreeNode=function(attributes,observer) {
	
	this.observer  = observer;
	this.iconPath  = '';//自定义
	
	this['attributes'] = attributes || {};
	this['html-element'] = false;//null
	if(!attributes.id){
		attributes.id = getUniqueID();
	}
	this.id = attributes.id;
	this.parentNode = null;
	this.childNodes = [];
	this.parentNode = null;
	this.lastChild = null;
	this.firstChild = null;
	this.previousSibling = null;
	this.nextSibling = null;
	this.childrenRendered = false;
	this.isExpand = false;
	this.checked = this['attributes']['checked'];
	// added by shixy 后台默认为undefined时不显示checkbox
	this.checked = eval(this.checked);
	
	this.checked = this.checked==null ? false : this.checked;
	this.checkedOld=this.checked;
	this.leaf = this.attributes.leaf;
	var children = attributes.children || [];
	for(var i=0,j=children.length;i<j;i++){
		var node = new TreeNode(children[i],observer);
		this.appendChild(node);
	}
};


TreeNode.prototype={
	initEl : function(){
		this['html-element']={};
		this['html-element']['element'] = document.createElement('div');
		this['html-element']['line']	 = document.createElement('span');
		this['html-element']['clip']	 = document.createElement('img');
		this['html-element']['icon']	 = document.createElement('img');
		this['html-element']['text']	 = document.createElement('span');
 		this['html-element']['checkbox'] = document.createElement('img');
		this['html-element']['child'] = document.createElement('div');
		this['html-element']['element'].appendChild(this['html-element']['line']);
		this['html-element']['element'].appendChild(this['html-element']['clip']);
		this['html-element']['element'].appendChild(this['html-element']['icon']);
		this['html-element']['element'].appendChild(this['html-element']['checkbox']);	
		this['html-element']['element'].appendChild(this['html-element']['text']);
		this['html-element']['element'].appendChild(this['html-element']['child']);
		this['html-element']['text'].className='TreeNode';
		this['html-element']['element'].noWrap='true';
		this['html-element']['line']['_type_'] ='line';
		this['html-element']['clip']['_type_'] ='clip';
		this['html-element']['icon']['_type_'] ='icon';
		this['html-element']['text']['_type_'] ='text';
		this['html-element']['checkbox']['_type_'] ='checked';
		this['html-element']['child'].style.display='none';
		if(this.checked===false){
			this['html-element']['checkbox'].style.display='none';
		}
		
		//自定义
		if(this.observer != undefined && this.observer.isDelegateIcon){
			this.observer.delegateGetDelegateIcon(this);
			//img赋值
			this['html-element']['icon'].id = this.attributes.id;//usertreenode_xxx(userCd)
			this['html-element']['icon'].sexcd = this.attributes.sexCd;
			this['html-element']['icon'].online = this.attributes.online;
			this['html-element']['icon'].usericontype = '1';//用户图标
			
			//div赋值
			this['html-element']['element'].id= "orguserdiv_"+this.attributes.id;//userCd,userCd;
		}
	},
	render : function(){
		if(!this['html-element']){
			this.initEl();
		}
		if(this.isRoot()){
			this.ownerTree.element.appendChild(this['html-element']['element']);
			this.expand();
		}else{
			this.parentNode['html-element']['child'].appendChild(this['html-element']['element']);
		}
		this.paintPrefix();
		this['html-element']['element'].indexId = this.id;
	},
	//add by huangbijin 20101122
	getChildNodes : function(){
		return this.childNodes;
	},
	paintPrefix : function(){
		this.paintLine();
		this.paintClipIcoImg();
		this.paintCheckboxImg();
		this.paintIconImg();
		this.paintText();
	},
	paintLine : function(){
		var ownerTree = this.getOwnerTree();
		this['html-element']['line'].innerHTML = '';
		var pathNodes = this.getPathNodes();
		for(var i = 1 ,count = pathNodes.length-1 ; i < count ; i++){
			var node = pathNodes[i];
			var img = document.createElement('img');
			if( node.parentNode==null  ){
				img.src = ownerTree.getIcon('empty');
			}else{
				img.src = ownerTree.getIcon('line');
			}
			this['html-element']['line'].appendChild(img);
		}
	},
	paintClipIcoImg : function(){
		if(this.isRoot()){
			this['html-element']['clip'].style.display='none';//不显示根节点的clip
			return;
		}
		var ownerTree = this.getOwnerTree();
		var icon = 'empty';
		if(this.isRoot()){
			icon = this.isExpand ? 'nlMinus':'nlPlus';
		}else{
			//alert(this['attributes']["entityStatusCd"]);
			//实现树结构异步加载
			//auth: hy 2011-11-16
			if(this['attributes']["entityStatusCd"] == "isChildrens"){
				if(this.isExpand){ //展开
					if(this.isLast()){
						icon = 'minusBottom';
					}else{
						icon = 'minus';
					}
				}else{ //折叠
					if(this.isLast()){
						icon = 'plusBottom';
					}else{
						icon = 'plus';
					}
				}
			}
			else if(this.isLeaf()){ //是叶节点
				if(this.isLast()){
					icon = 'joinBottom';
				}else{
					icon = 'join';
				}
			}else{ //非叶节点
				if(this.isExpand){ //展开
					if(this.isLast()){
						icon = 'minusBottom';
					}else{
						icon = 'minus';
					}
				}else{ //折叠
					if(this.isLast()){
						icon = 'plusBottom';
					}else{
						icon = 'plus';
					}
				}
			}
		};
		this['html-element']['clip'].src = ownerTree.getIcon(icon);
	},
	paintIconImg : function(){
		var isDelegateIcon = false;//是否重写
		var ownerTree = this.getOwnerTree();
		var icon = this['attributes']['icon'];
		if(!icon){
			
			var type = this['attributes']['type'];
			if(type){
				icon = ownerTree.getIconByType(type);
			}
			if(!icon){
				//实现树异步加载子树
				//auth: hy 2011-11-16
				if(this['attributes']["entityStatusCd"] == "isChildrens"){
					if(this.isExpand){
						icon = 'folderOpen';
					}else{
						icon = 'folder';
					}
				}
				else if(this.isRoot()){
					icon = 'root';
				}else if(this.isLeaf()){
					icon = 'node';
					if(this && this.observer && this.observer.isDelegateIcon){
						isDelegateIcon = true;
					}
				}else if(this.isExpand){
					icon = 'folderOpen';
				}else{
					icon = 'folder';
				}
			}
		}
		//add by huangbj
		if( isDelegateIcon){
			this['html-element']['icon'].src = this.iconPath;
		}else{
			this['html-element']['icon'].src = ownerTree.getIcon(icon);
		}
	},
	paintCheckboxImg : function(){
		var ownerTree = this.getOwnerTree();
		var checked = this.checked;
		if(this['html-element']){
			this['html-element']['checkbox'].src = ownerTree.getIcon(((checked==2)?'checkbox2':(checked==1)?'checkbox1':'checkbox0'));
		}
	},	
	paintText : function(){
		var text = 	this['attributes']['text'];
		this['html-element']['text'].style.cursor='hand';
		this['html-element']['text'].title=text;//显示title
		this['html-element']['text'].innerText=text;
		this['html-element']['text'].textContent=text;
		
		//2011-2-22  added by shixy 用于网上审批树，将未上线表单给予删除线标志
		var enableFlg = this['attributes']['extParam'];
		var nodeType = this['attributes']['nodeType'];
		if(enableFlg && enableFlg == '0' && nodeType == 'authType'){
			this['html-element']['text'].style.color='#8a8a8a';
			this['html-element']['text'].title='该表单未上线,请走纸质流程！';//显示title
		}
	},
	paintChildren : function(){
		if(!this.childrenRendered){
			this['html-element']['child'].innerHTML = '';
			this.childrenRendered = true;
			var childNodes = this.childNodes;
			for(var i=0;i < childNodes.length;i++){
				childNodes[i].render();
			}
		};
	},
	collapse : function(){
		this.isExpand=false;
		this['html-element']['child'].style.display='none';
		this.paintIconImg();
		this.paintClipIcoImg();
	},
	expand : function(){
		if(!this.isLeaf()&&this.childNodes.length>0){
			this.isExpand=true;
			this.paintChildren();
			this['html-element']['child'].style.display='block';
		}
		this.paintIconImg();
		this.paintClipIcoImg();
	},
	select : function(){
		this.isSelect = true;
		
		//this['html-element']['text'].style.backgroundColor='#CCCCFF';
		//this['html-element']['text'].style.color = "#4984ae";
		
		this['html-element']['text'].style.backgroundColor = "#6fb0ce";
		
		//扩展选中事件
		var ownerTree = this.getOwnerTree();
		this['html-element']['text'].style.color = "#ffffff";
		
		/*
		if(ownerTree.isExpendSelect){
			this['html-element']['text'].style.backgroundColor = "#CCCCFF";
		}else{
			this['html-element']['text'].style.backgroundColor = "";
		}
		*/
	},
	unselect : function(){
		this.isSelect = false;
		//扩展选中事件
		var ownerTree = this.getOwnerTree();
		this['html-element']['text'].style.backgroundColor = "";
		this['html-element']['text'].style.color = "";
		
		//2011-2-22  added by shixy 用于网上审批树，将未上线表单给予删除线标志
		var enableFlg = this['attributes']['extParam'];
		var nodeType = this['attributes']['nodeType'];
		if(enableFlg&&enableFlg=='0'&&nodeType=='authType'){
			this['html-element']['text'].style.color='#8a8a8a';
			this['html-element']['text'].title='该表单未上线,请走纸质流程！';//显示title
		}
	},
	getEl :  function(){
		return this['html-element'];
	},
	setCheck : function(checked){
		if(checked==2||checked==3){
			var childNodes = this.childNodes;
			var count = childNodes.length;
			if(count==0){
				this.checked=checked==2?0:1;
			}else{
				var checked1 = 0;
				var checked2 = 0;
				for(var i=0;i<count;i++){
				var checked = childNodes[i].checked;
					if(checked==1){
						checked1++;
					}else if(checked==2){
						checked2++;
					}
				}
				this.checked = (childNodes.length==checked1) ? 1 : (checked1>0||checked2>0) ? 2 : 0;
			}
		}else{
			this.checked=checked;
		}
		this.paintCheckboxImg();
	},
	onCheck : function(){
		if(this.checked!==false){
			if(this.checked==1){
				if (this.getOwnerTree().isAutoChecked) {
					this.cascade((function(checked){
						this.setCheck(checked);
					}),null, 0);
				
					this.bubble((function(checked){
						this.setCheck(checked);
					}),null,2);
				} else {
					this.setCheck("0");
				}
			}else{
				if (this.getOwnerTree().isAutoChecked) {
					this.cascade((function(checked){
						this.setCheck(checked);
					}),null,1);
					
					this.bubble((function(checked){
						this.setCheck(checked);
					}),null,3);
				} else {
					this.setCheck("1");
				}	
			}
		}
	},

	isRoot : function(){
		return (this.ownerTree!=null) && (this.ownerTree.root === this);
	},
	isLeaf : function(){
		return this.childNodes.length===0;
		//return this.leaf === true;
  },
	isLast : function(){
		return (!this.parentNode ? true : this.parentNode.lastChild == this);
	},
	isFirst : function(){
		return (!this.parentNode ? true : this.parentNode.firstChild == this);
	},
	hasChildNodes : function(){
		return !this.isLeaf() && this.childNodes.length > 0;
	},
	getAllChildren : function(node,name){
		var children = new Array();
		node.cascade((function(node){
			children.push(name?node.attributes[name]:node.attributes.id);
		}),this);
		return children;
	},
	// private
	setFirstChild : function(node){
		this.firstChild = node;
	},
	//private
	setLastChild : function(node){
		this.lastChild = node;
	},
	appendChild : function(node){
		var multi = false;
		if(Array.isInstance(node)){
			multi = node;
		}else if(arguments.length > 1){
			multi = arguments;
		}
		if(multi){
	    	for(var i = 0, len = multi.length; i < len; i++) {
				this.appendChild(multi[i]);
			}
		}else{
			//>>beforeappend
	      var oldParent = node.parentNode;
	      //>>beforemove
	      if(oldParent){
	    	  oldParent.removeChild(node);
	      }
	      var index = this.childNodes.length;
	      if(index == 0){
				this.setFirstChild(node);
	      }
	      this.childNodes.push(node);
	      node.parentNode = this;
	      //
	      var ps = this.childNodes[index-1];
	      if(ps){
	    	  node.previousSibling = ps;
	    	  ps.nextSibling = node;
	      }else{
	    	  node.previousSibling = null;
		  }
		  node.nextSibling = null;
	      this.setLastChild(node);
	      node.setOwnerTree(this.getOwnerTree());
			//>>append
			//if(oldParent) >>move
		
	      if(node && this.childrenRendered){
			  node.render();
			  if(node.previousSibling){
				  node.previousSibling.paintPrefix();//paintLine();
			  }
	      }
	      if(this['html-element']){
	      	 this.paintPrefix();
	      }
	      return node;//true
		}
	},
	removeChild : function(node){
		var index = this.childNodes.indexOf(node);
		if(index == -1){
			return false;
		}
		//>>beforeremove
		this.childNodes.splice(index, 1);
		if(node.previousSibling){
			node.previousSibling.nextSibling = node.nextSibling;
		}
		if(node.nextSibling){
			node.nextSibling.previousSibling = node.previousSibling;
		}
		if(this.firstChild == node){
			this.setFirstChild(node.nextSibling);
		}
		if(this.lastChild == node){
			this.setLastChild(node.previousSibling);
		}
		node.setOwnerTree(null);
		//clear
		node.parentNode = null;
		node.previousSibling = null;
		node.nextSibling = null;
		//>>remove UI
		if(this.childrenRendered){
			if(node['html-element']&&node['html-element']['element']){
				this['html-element']['child'].removeChild(node['html-element']['element']);
			}
			if(this.childNodes.length==0){
				this.collapse();
			}
	    }
	    if(this['html-element']){
	    	this.paintPrefix();
	    }
		return node;
	},
	insertBefore : function(node, refNode){
		if(!refNode){
			return this.appendChild(node);
		}
		//移动位置是自身位置(不需要移动)
		if(node == refNode){
			return false;
		}
		var index = this.childNodes.indexOf(refNode);
		var oldParent = node.parentNode;
		var refIndex = index;
		//是子节点，并且是向后移动
		if(oldParent == this && this.childNodes.indexOf(node) < index){
			refIndex--;
		}
		if(oldParent){
			oldParent.removeChild(node);
		}
		//设置节点间关系
		if(refIndex == 0){
			this.setFirstChild(node);
		}
		this.childNodes.splice(refIndex, 0, node);
		node.parentNode = this;
		var ps = this.childNodes[refIndex-1];
		if(ps){
			node.previousSibling = ps;
			ps.nextSibling = node;
		}else{
			node.previousSibling = null;
		}
		node.nextSibling = refNode;
		refNode.previousSibling = node;
		node.setOwnerTree(this.getOwnerTree());
		return node;
	},
	replaceChild : function(newChild, oldChild){
		this.insertBefore(newChild, oldChild);
		this.removeChild(oldChild);
		return oldChild;
	},
	indexOf : function(child){
		return this.childNodes.indexOf(child);
	},
	getOwnerTree : function(){
		if(!this.ownerTree){
			var p = this;
			while(p){
				if(p.ownerTree){
					this.ownerTree = p.ownerTree;
					break;
				}
				p = p.parentNode;
			}
		}
		return this.ownerTree;
	},
	//获得节点深度
	getDepth : function(){
  	var depth = 0;
		var p = this;
		while(p.parentNode){
			depth++;
			p = p.parentNode;
		}
		return depth;
	},
  //private
	setOwnerTree : function(tree){
		if(tree != this.ownerTree){
			if(this.ownerTree){
				this.ownerTree.unregisterNode(this);
			}
			this.ownerTree = tree;
			var cs = this.childNodes;
			for(var i = 0, len = cs.length; i < len; i++) {
				cs[i].setOwnerTree(tree);
			}
			if(tree){
				tree.registerNode(this);
			}
		}
	},
	getPathNodes : function(){
		var nodes = [];
		for(var parent=this; parent!=null; parent=parent.parentNode){nodes.push(parent);};
		return nodes.reverse();
	},
	getPath : function(attr){
		attr = attr || "id";
		var p = this.parentNode;
		var b = [this['attributes'][attr]];
		while(p){
			b.unshift(p.attributes[attr]);
			p = p.parentNode;
    }
		var sep = this.getOwnerTree().pathSeparator;
		return sep + b.join(sep);
	},
	//冒泡(遍历所有父节点)
	bubble : function(fn, scope, args){
  	var p = this;
		while(p){
			if(fn.call(scope || p, (args==null)?p:args) === false){
	    	break;
			}
	    p = p.parentNode;
		}
	},
	//瀑布(遍历所有子节点)
	cascade : function(fn, scope, args){
		if(fn.call(scope || this, (args==null)?this:args) !== false){
			var cs = this.childNodes;
			for(var i = 0, len = cs.length; i < len; i++) {
				cs[i].cascade(fn, scope, args);
			}
		}
	},
	//查找
	findChild : function(attribute, value){
		var cs = this.childNodes;
		for(var i = 0, len = cs.length; i < len; i++) {
			if(cs[i].attributes[attribute] == value){
				return cs[i];
			}
		}
		return null;
	},
  findChildBy : function(fn, scope){
      var cs = this.childNodes;
      for(var i = 0, len = cs.length; i < len; i++) {
      	if(fn.call(scope||cs[i], cs[i]) === true){
      	    return cs[i];
      	}
      }
      return null;
  },
	sort : function(fn, scope){
		var cs = this.childNodes;
		var len = cs.length;
		if(len > 0){
			var sortFn = scope ? function(){fn.apply(scope, arguments);} : fn;
			cs.sort(sortFn);
			for(var i = 0; i < len; i++){
				var n = cs[i];
		        n.previousSibling = cs[i-1];
		        n.nextSibling = cs[i+1];
		        if(i == 0){
		        	this.setFirstChild(n);
		     	}
		        if(i == len-1){
		        	this.setLastChild(n);
				}
			}
		}
	},
  contains : function(node){
     var p = node.parentNode;
      while(p){
          if(p == this){
              return true;
          }
          p = p.parentNode;
      }
      return false;
  },
  toString : function(){
      return "[Node"+(this.id?" "+this.id:"")+"]";
  },
  //add by huangbijin 2012-06-20
  //@param deeps:展开层级 0-根节点展开 1-根节点下级展开
  expandDeeps:function(deeps){
	  if(typeof(deeps) == undefined){
		  deeps = 0;
	  }
	  this.expandDeep(deeps, 0);
  },
  expandDeep: function(deeps,deep){
	  if(deeps < deep){
		  return;
	  }
	  var children = this.getChildNodes();
	  if(children.length == 0){
		  return;
	  }
	  this.render();
	  this.expand();
	  for(var k=0; k<children.length; k++){
		  var t = children[k];
		  t.expandDeep(deeps,(deep+1));
	  }
  }
};
