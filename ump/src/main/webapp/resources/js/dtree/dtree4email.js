/*--------------------------------------------------|
| dTree 2.05 | www.destroydrop.com/javascript/tree/ |

|---------------------------------------------------|
| Copyright (c) 2002-2003 Geir Landr?               |
|                                                   |
| This script can be used freely as long as all     |
| copyright messages are intact.                    |
|                                                   |
| Updated: 17.04.2003                               |
|--------------------------------------------------*/

//add by hbj 2009-12-30
//Note: the parent id and child node ID must not equal identical.
//Either parent or child nodes must first sequenced in the background, then display at the front desk.



//modified by wallimn. 20070831
//to support checkbox object
//updated 2009-02-13
//welcome to visit my blog: http://blog.csdn.net/wallimn
//please keep these message.
// Node object

/**
 * 定义节点
 * 
 * id  		：节点自身的id
 * pid   	：节点的父节点的id
 * name    	：节点显示在页面上的名称
 * url   	：节点的链接地址
 * title 	：鼠标放在节点上所出现的提示信息
 * target   ：节点链接所打开的目标frame（如框架目标mainFrame或是_blank,_self之类）
 * icon   	：节点关闭时的显示图片的路径
 * iconOpen	：节点打开时的显示图片的路径
 * open    	：布尔型，节点是否打开（默认为false）
 * 
 * 注意
 * 
 * open项	：顶级节点一般采用true，即pid是-1的节点
 */
function Node(id, pid, name, url, title, target, icon, iconOpen, open, extParam) {
	this.id 		= id;
	this.pid 		= pid;
	this.name 		= name;
	this.url 		= url;
	this.title 		= title;
	this.target 	= target;
	this.icon 		= icon;
	this.iconOpen 	= iconOpen;
	this._io 		= open || false;
	this._is 		= false;
	this._ls 		= false;
	this._hc 		= false;
	this._ai 		= 0;
	this._p			   ;
	this.extParam	= extParam;
};
// Tree object
/**
 * 定义树型结构
 */
function dTree(objName,iconPath) {
	this.config = {
		target			: null,
		folderLinks		: true,
		useSelection	: true,
		useCookies		: true,
		useLines		: true,
		useIcons		: true,
		useStatusText	: false,
		closeSameLevel	: false,
		inOrder			: false,
		check			: false,		//added by wallimn 2009-02-05, to control whether checkbox is showed
		mouseOverFucName: null,			//added by huangbijin 20100302  on mouse over  callback
		mouseOutFuncName: null,			//added by huangbijin 20100302  on mouse out   callback
		mouseClickFucName:null			//added by huangbijin 20100302  on mouse click callback
	};
	//modified by wallimn 2009-02-05, in order to make client can modify path of icons;
	if(!iconPath)iconPath="img/";//set the default value, be compatible with dtree
	
	//默认图片的路径，注意要指对
	this.icon = {
			root		: iconPath+'empty.gif',//base.gif
			folder		: iconPath+'triangle_right.gif',//folder.png
			folderOpen	: iconPath+'triangle_down.gif',//folderopen.png
			node		: iconPath+'tubiao_touxiang_lv.jpg',
			empty		: iconPath+'empty.gif',
			line		: iconPath+'empty.gif',//line.gif
			join		: iconPath+'empty.gif',//join.gif
			joinBottom	: iconPath+'empty.gif',//joinbottom.gif
			plus		: iconPath+'empty.gif',//plus.gif
			plusBottom	: iconPath+'empty.gif',//plusbottom.gif
			minus		: iconPath+'empty.gif',//minus.gif
			minusBottom	: iconPath+'empty.gif',//minusbottom.gif
			nlPlus		: iconPath+'empty.gif',//nolines_plus.gif
			nlMinus		: iconPath+'empty.gif',//nolines_minus.gif
			
			//选择框:不选 半选择 全选   add by huangbijin 2009100309 
			noneChecked : iconPath+'checkbox_0.gif',
			 allChecked : iconPath+'checkbox_1.gif',
			halfChecked : iconPath+'checkbox_2.gif'
	};
	//added by wallimn, to cache checkbox object and improve speed of set checked status
	this.cbCollection = new Object();
	this.obj = objName;
	this.aNodes = [];
	this.aIndent = [];
	this.root = new Node(-1);
	this.selectedNode = null;
	this.selectedFound = false;
	this.completed = false;
};

// Adds a new node to the node array
// 后面5个参数可以省略
dTree.prototype.add = function(id, pid, name, url, title, target, icon, iconOpen, open, extParam) {
	this.aNodes[this.aNodes.length] = new Node(id, pid, name, url, title, target, icon, iconOpen, open, extParam);
};

// Open/close all nodes
dTree.prototype.openAll = function() {
	this.oAll(true);
};
dTree.prototype.closeAll = function() {
	this.oAll(false);
};

// Outputs the tree to the page
dTree.prototype.toString = function() {
	var str = '<div class="dtree">\n';
	if (document.getElementById) {
		if (this.config.useCookies) this.selectedNode = this.getSelected();
		str += this.addNode(this.root);
	} else str += 'Browser not supported.';
	str += '</div>';
	if (!this.selectedFound) this.selectedNode = null;
	this.completed = true;
	return str;
};

// Creates the tree structure
dTree.prototype.addNode = function(pNode) {
	var str = '';
	var n=0;
	if (this.config.inOrder) n = pNode._ai;
	for (n; n<this.aNodes.length; n++) {
		if (this.aNodes[n].pid == pNode.id) {
			var cn = this.aNodes[n];
			cn._p = pNode;
			cn._ai = n;
			this.setCS(cn);
			if (!cn.target && this.config.target) cn.target = this.config.target;
			if (cn._hc && !cn._io && this.config.useCookies) cn._io = this.isOpen(cn.id);
			if (!this.config.folderLinks && cn._hc) cn.url = null;
			if (this.config.useSelection && cn.id == this.selectedNode && !this.selectedFound) {
					cn._is = true;
					this.selectedNode = n;
					this.selectedFound = true;
			}
			str += this.node(cn, n);
			if (cn._ls) break;
		}
	}
	return str;
};
//get checkbox object by id(input by client)
//added by wallimn,
//获取选中的节点
dTree.prototype.co=function(id){
	if (this.cbCollection[id])return this.cbCollection[id];
	for(var n=0; n<this.aNodes.length; n++){
		if(this.aNodes[n].id==id){
			this.cbCollection[id]=document.getElementById("c"+this.obj+n);
			break;
		}
	}
	return this.cbCollection[id];
};
//get the ids of the checkbox which check status is true;
//added by wallimn, updated: 2009-02-13
dTree.prototype.getCheckedNodes=function(){

	var res = new Array();
	var cko;//checkobject
	for(var n=0; n<this.aNodes.length; n++){
		cko = this.co(this.aNodes[n].id);
		if( cko && cko.className == 'allChecked' && (this.aNodes[n].icon.indexOf(this.icon.node) != -1)){
			res[res.length] = this.aNodes[n];
		}
	}
	alert(res);
	return res;
	
	// contented by huangbijin 20090309 begin
	/*
	var res = new Array();
	var cko;//checkobject
	for(var n=0; n<this.aNodes.length; n++){
		//alert("c"+this.obj+n+(document.getElementById("c"+this.obj+n).checked));
		//cache the object to improve speed when you have very,very many nodes and call this function many times in one page
		//i.e. with ajax technology
		//document.getElementById("c"+this.obj+n).checked
		cko = this.co(this.aNodes[n].id);
		if(cko.checked==true){//TODO:HUANGBIJIN
			
			//contented by huanbijin 20100204 begin
			//res[res.length]=this.aNodes[n].id;
			//contented by huanbijin 20100204 end

			//added by huanbijin 20100204 begin
		    var selNode =new Array();
		    selNode[0] =this.aNodes[n].pid;
		    selNode[1]=this.aNodes[n].id;
			res[res.length] = selNode;
			//added by huanbijin 20100204 end
		}
	}
	return res;
	*/
	//contented by huangbijin 20090309 end
};

dTree.prototype.getCheckedNodeIds=function(){

	var res = new Array();
	for(var n=0; n<this.aNodes.length; n++){
		var cko = this.co(this.aNodes[n].id);
		if( cko && cko.className == 'allChecked' && (this.aNodes[n].icon.indexOf(this.icon.node) != -1)){
			var id = this.aNodes[n].id;
			res[res.length] = id;
		}
	}
	alert(res);
	return res;
};

//设置当前节点状态
dTree.prototype.changeChxBoxStatus=function(nodeId){
	var chkBox = document.getElementById("c"+this.obj+nodeId);
	var cs = chkBox.className;
	switch(cs){
		case 'noneChecked':
		case 'halfChecked':
  			 this.setChkBoxStatus(chkBox,'allChecked');
  			 break;
		case 'allChecked':
			 this.setChkBoxStatus(chkBox,'noneChecked');
		default: break;
	}
};


/**
 * @param nodeId
 * @param status  noneChecked,halfChecked,allChecked
 * @return
 */
dTree.prototype.setChkBoxStatus=function(chkBox,status){
	switch (status) {
		case 'noneChecked':
			 chkBox.src = this.icon.noneChecked;//改变图标
			 chkBox.className = 'noneChecked';
			 break;
		case 'halfChecked':
			 chkBox.src = this.icon.halfChecked;
			 chkBox.className = 'halfChecked';
			 break;
		case 'allChecked':
			 chkBox.src = this.icon.allChecked;
			 chkBox.className = 'allChecked';
			 break;
	
		default:
			 break;
	}
};



//added by wallimn
dTree.prototype.cc=function(nodeId){

	//注意:当前节点状态不会半选
	
	//向下 
	var cs = document.getElementById("c"+this.obj+nodeId).className;
	var csSrc = document.getElementById("c"+this.obj+nodeId).src;
	var n,node = this.aNodes[nodeId];
	var len =this.aNodes.length;
	for (n=0; n<len; n++) {
		if (this.aNodes[n].pid == node.id) {
			document.getElementById("c"+this.obj+n).className=cs;
			document.getElementById("c"+this.obj+n).src=csSrc;
			this.cc(n);		
		}
	}
	if(!cs)return;
	
	//向上
	var pid=node.pid;
	var bSearch;
	do{
		bSearch=false;
		for(n=0;n<len;n++){
			var tmpNode = this.aNodes[n];
			if(tmpNode.id==pid){
				//找到父亲
				var tmpParentNode = document.getElementById("c"+this.obj+n);
				
				var sonLength = 0;//儿子节点个数
				var sonChecks = 0;//儿子节点选中
				
				for(j=0;j<len;j++){
					var sonNode = this.aNodes[j];
					if( sonNode.pid==pid){
						sonLength++;
						if(document.getElementById("c"+this.obj+j).className=='allChecked'){
							sonChecks++; 
						}
					}
				}
				
				if( sonLength == sonChecks){
					//alert('equal!');
					this.setChkBoxStatus(tmpParentNode, 'allChecked');
				}

				if( sonLength > sonChecks){
					//alert('equal!');
					this.setChkBoxStatus(tmpParentNode, 'halfChecked');
				}
				
				if( sonChecks == 0){
					this.setChkBoxStatus(tmpParentNode, 'noneChecked');
				}
				
				pid=this.aNodes[n].pid;
				bSearch= true;				
				break;
			}
		}
	}while(bSearch==true);
	
	
	/* CONTENTED BY HUANGBIJIN 20100309 BEGIN
	var cs = document.getElementById("c"+this.obj+nodeId).checked;
	var n,node = this.aNodes[nodeId];
	var len =this.aNodes.length;
	for (n=0; n<len; n++) {
		//alert(this.aNodes[n].pid+"--"+this.aNodes[n].id);
		if (this.aNodes[n].pid == node.id) {
			//if (this.aNodes[n]._io) this.nodeStatus(false, n, this.aNodes[n]._ls);
			//this.aNodes[n]._io = false;
			document.getElementById("c"+this.obj+n).checked=cs;
			this.cc(n);		
		}
	}
	if(cs==false)return;
	var pid=node.pid;
	var bSearch;
	do{
		bSearch=false;
		for(n=0;n<len;n++){
			if(this.aNodes[n].id==pid){
				document.getElementById("c"+this.obj+n).checked=true;
				pid=this.aNodes[n].pid;
				bSearch= true;				
				break;
			}
		}
	}while(bSearch==true)
	*/ 
	//CONTENTED BY HUANGBIJIN 20100309 END
};

// Creates the node icon, url and text
dTree.prototype.node = function(node, nodeId) {
	var str = '<div class="dTreeNode" id="dTreeNode_'+nodeId+'">' + this.indent(node, nodeId);
	if (this.config.useIcons) {
		
		if (!node.icon) node.icon = (this.root.id == node.pid) ? this.icon.root : ((node._hc) ? this.icon.folder : this.icon.node);
		if (!node.iconOpen) node.iconOpen = (node._hc) ? this.icon.folderOpen : this.icon.node;
		if (this.root.id == node.pid) {
			node.icon = this.icon.root;
			node.iconOpen = this.icon.root;
		}

		//added by huangbijin 20100302 begin
		var nodeIcon = (node.icon == this.icon.root)?node.icon:this.delegateGetLeafImg(node);
		str += '<img id="i' + this.obj + nodeId + '" src="' + ((node._io)?node.iconOpen : nodeIcon) + '" alt="" />';
		//added by huangbijin 20100302 end
		
		//contented by huangbijin 20100302 begin
		//str += '<img id="i' + this.obj + nodeId + '" src="' + ((node._io)?node.iconOpen : node.icon) + '" alt="" />';
		//added by huangbijin 20100302 end
		//alert("img:"+str);
	}
	if(this.config.check==true){
		//contented by huangbijin 20100309 begin
		//str+= '<input type="checkbox" class="cx" id="c'+  this.obj + nodeId + '" onclick="javascript:'+this.obj+'.cc('+nodeId+')"/>';
		//alert("check:"+str);
		//contented by huangbijin 20100309 end
		
		//added by huangbijin begin
		str+= '<image style="display:inline;" class="noneChecked" src="'+this.icon.noneChecked+'" id="c'+  this.obj + nodeId + '" onclick="javascript:'+this.obj+'.changeChxBoxStatus('+nodeId+');'+this.obj+'.cc('+nodeId+')"/>';
		//added by huangbijin end
	}
	if (node.url) {
		str += '<a id="s' + this.obj + nodeId + '" class="' + ((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node') + '" href="' + node.url + '"';
		//alert(str);
		if (node.title) str += ' title="' + node.title + '"';
		if (node.target) str += ' target="' + node.target + '"';
		
		
		//contented by huangbijin 20100302 begin
		//if (this.config.useStatusText) str += ' onmouseover="window.status=\'' + node.name + '\';return true;" onmouseout="window.status=\'\';return true;" ';
		//if (this.config.useSelection && ((node._hc && this.config.folderLinks) || !node._hc))	str += ' onclick="javascript: ' + this.obj + '.s(' + nodeId + ');"';
		//contented by huangbijin 20100302 end
		
		//added by huangbijin 20100302 begin
		if (this.config.useStatusText) {
			if(this.config.mouseOverFucName){
				str  += ' onmouseover="javascript:'+ this.config.mouseOverFucName+ '(this,\''+ node.id +'\',\''+ node.extParam +'\')" ';
			}
			if(this.config.mouseOutFucName){
				str  += ' onmouseout ="javascript:'+ this.config.mouseOutFucName + '(this,\''+ node.id +'\',\''+ node.extParam +'\')" ';
			}
		}
		if (this.config.useSelection && ((node._hc && this.config.folderLinks) || !node._hc)){

			var partJsMouseClick = "";
			if(this.config.mouseClickFucName){
				partJsMouseClick = this.config.mouseClickFucName + '(this,\''+ node.id +'\',\''+ node.extParam +'\');';
			}
			
			str += ' onclick="javascript: ' + this.obj + '.s(' + nodeId + ');'+ partJsMouseClick +'"';
			
		}
		//added by huangbijin 20100302 end

		str += '>';
		//alert("url:"+str);
	}
	else if ((!this.config.folderLinks || !node.url) && node._hc && node.pid != this.root.id){
		str += '<a href="javascript: ' + this.obj + '.o(' + nodeId + ');" class="node">';
		//alert("folderLinks:"+str);
	}
	str += node.name;
	if (node.url || ((!this.config.folderLinks || !node.url) && node._hc)) str += '</a>';
	str += '</div>';
	if (node._hc) {
		str += '<div id="d' + this.obj + nodeId + '" class="clip" style="display:' + ((this.root.id == node.pid || node._io) ? 'block' : 'none') + ';">';
		str += this.addNode(node);
		str += '</div>';
	}
	this.aIndent.pop();
	//alert(str);
	return str;
};

// Adds the empty and line icons
dTree.prototype.indent = function(node, nodeId) {
	var str = '';
	if (this.root.id != node.pid) {
		for (var n=0; n<this.aIndent.length; n++)
			str += '<img src="' + ( (this.aIndent[n] == 1 && this.config.useLines) ? this.icon.line : this.icon.empty ) + '" alt="" />';
		(node._ls) ? this.aIndent.push(0) : this.aIndent.push(1);
		if (node._hc) {
			str += '<a href="javascript: ' + this.obj + '.o(' + nodeId + ');"><img id="j' + this.obj + nodeId + '" src="';
			if (!this.config.useLines) str += (node._io) ? this.icon.nlMinus : this.icon.nlPlus;
			else str += ( (node._io) ? ((node._ls && this.config.useLines) ? this.icon.minusBottom : this.icon.minus) : ((node._ls && this.config.useLines) ? this.icon.plusBottom : this.icon.plus ) );
			str += '" alt="" /></a>';
		} else str += '<img src="' + ( (this.config.useLines) ? ((node._ls) ? this.icon.joinBottom : this.icon.join ) : this.icon.empty) + '" alt="" />';
	}
	return str;
};

// Checks if a node has any children and if it is the last sibling
dTree.prototype.setCS = function(node) {
	var lastId;
	for (var n=0; n<this.aNodes.length; n++) {
		if (this.aNodes[n].pid == node.id) node._hc = true;
		if (this.aNodes[n].pid == node.pid) lastId = this.aNodes[n].id;
	}
	if (lastId==node.id) node._ls = true;
};

// Returns the selected node
dTree.prototype.getSelected = function() {
	var sn = this.getCookie('cs' + this.obj);
	return (sn) ? sn : null;
};

// Highlights the selected node
dTree.prototype.s = function(id) {
	if (!this.config.useSelection) return;
	var cn = this.aNodes[id];
	if (cn._hc && !this.config.folderLinks) return;
	if (this.selectedNode != id) {
		if (this.selectedNode || this.selectedNode==0) {
			eOld = document.getElementById("s" + this.obj + this.selectedNode);
			eOld.className = "node";
		}
		eNew = document.getElementById("s" + this.obj + id);
		eNew.className = "nodeSel";
		this.selectedNode = id;
		if (this.config.useCookies) this.setCookie('cs' + this.obj, cn.id);

		//add by hbj 20090118 begin
		this.delegateHighlightNode(cn);
		//add by hbj 20090118 end 
	}
};

// Toggle Open or close
dTree.prototype.o = function(id) {
	var cn = this.aNodes[id];
	this.nodeStatus(!cn._io, id, cn._ls);
	cn._io = !cn._io;
	if (this.config.closeSameLevel) this.closeLevel(cn);
	if (this.config.useCookies) this.updateCookie();
};

// Open or close all nodes
dTree.prototype.oAll = function(status) {
	for (var n=0; n<this.aNodes.length; n++) {
		if (this.aNodes[n]._hc && this.aNodes[n].pid != this.root.id) {
			this.nodeStatus(status, n, this.aNodes[n]._ls)
			this.aNodes[n]._io = status;
		}
	}
	if (this.config.useCookies) this.updateCookie();
};

// Opens the tree to a specific node
dTree.prototype.openTo = function(nId, bSelect, bFirst) {
	if (!bFirst) {
		for (var n=0; n<this.aNodes.length; n++) {
			if (this.aNodes[n].id == nId) {
				nId=n;
				break;
			}
		}
	}
	var cn=this.aNodes[nId];
	if (cn.pid==this.root.id || !cn._p) return;
	cn._io = true;
	cn._is = bSelect;
	if (this.completed && cn._hc) this.nodeStatus(true, cn._ai, cn._ls);
	if (this.completed && bSelect) this.s(cn._ai);
	else if (bSelect) this._sn=cn._ai;
	this.openTo(cn._p._ai, false, true);
};

// Closes all nodes on the same level as certain node
dTree.prototype.closeLevel = function(node) {
	for (var n=0; n<this.aNodes.length; n++) {
		if (this.aNodes[n].pid == node.pid && this.aNodes[n].id != node.id && this.aNodes[n]._hc) {
			this.nodeStatus(false, n, this.aNodes[n]._ls);
			this.aNodes[n]._io = false;
			this.closeAllChildren(this.aNodes[n]);
		}
	}
};

// Closes all children of a node
dTree.prototype.closeAllChildren = function(node) {
	for (var n=0; n<this.aNodes.length; n++) {
		if (this.aNodes[n].pid == node.id && this.aNodes[n]._hc) {
			if (this.aNodes[n]._io) this.nodeStatus(false, n, this.aNodes[n]._ls);
			this.aNodes[n]._io = false;
			this.closeAllChildren(this.aNodes[n]);		
		}
	}
};

// Change the status of a node(open or closed)
dTree.prototype.nodeStatus = function(status, id, bottom) {
	eDiv	= document.getElementById('d' + this.obj + id);
	eJoin	= document.getElementById('j' + this.obj + id);
	if (this.config.useIcons) {
		eIcon	= document.getElementById('i' + this.obj + id);
		eIcon.src = (status) ? this.aNodes[id].iconOpen : this.aNodes[id].icon;
	}
	eJoin.src = (this.config.useLines)?
	((status)?((bottom)?this.icon.minusBottom:this.icon.minus):((bottom)?this.icon.plusBottom:this.icon.plus)):
	((status)?this.icon.nlMinus:this.icon.nlPlus);
	eDiv.style.display = (status) ? 'block': 'none';
};
// [Cookie] Clears a cookie
dTree.prototype.clearCookie = function() {
	var now = new Date();
	var yesterday = new Date(now.getTime() - 1000 * 60 * 60 * 24);
	this.setCookie('co'+this.obj, 'cookieValue', yesterday);
	this.setCookie('cs'+this.obj, 'cookieValue', yesterday);
};

// [Cookie] Sets value in a cookie
dTree.prototype.setCookie = function(cookieName, cookieValue, expires, path, domain, secure) 

{
	document.cookie =
		escape(cookieName) + '=' + escape(cookieValue)
		+ (expires ? '; expires=' + expires.toGMTString() : '')
		+ (path ? '; path=' + path : '')
		+ (domain ? '; domain=' + domain : '')
		+ (secure ? '; secure' : '');
};

// [Cookie] Gets a value from a cookie
dTree.prototype.getCookie = function(cookieName) {
	var cookieValue = '';
	var posName = document.cookie.indexOf(escape(cookieName) + '=');
	if (posName != -1) {
		var posValue = posName + (escape(cookieName) + '=').length;
		var endPos = document.cookie.indexOf(';', posValue);
		if (endPos != -1) cookieValue = unescape(document.cookie.substring(posValue, endPos));
		else cookieValue = unescape(document.cookie.substring(posValue));
	}
	return (cookieValue);
};

// [Cookie] Returns ids of open nodes as a string
dTree.prototype.updateCookie = function() {
	var str = '';
	for (var n=0; n<this.aNodes.length; n++) {
		if (this.aNodes[n]._io && this.aNodes[n].pid != this.root.id) {
			if (str) str += '.';
			str += this.aNodes[n].id;
		}
	}
	this.setCookie('co' + this.obj, str);
};

// [Cookie] Checks if a node id is in a cookie
dTree.prototype.isOpen = function(id) {
	var aOpen = this.getCookie('co' + this.obj).split('.');
	for (var n=0; n<aOpen.length; n++)
		if (aOpen[n] == id) return true;
	return false;
};

// If Push and pop is not implemented by the browser
if (!Array.prototype.push) {
	Array.prototype.push = function array_push() {
		for(var i=0;i<arguments.length;i++)
			this[this.length]=arguments[i];
		return this.length;
	};
};
if (!Array.prototype.pop) {
	Array.prototype.pop = function array_pop() {
		lastElement = this[this.length-1];
		this.length = Math.max(this.length-1,0);
		return lastElement;
	};
};

//******************************************************************************************************//
// [custom extend mehtod added by huangbijin 20100204]
//******************************************************************************************************//

//execute on click node
//已经通过注册"mouseClickFucName"方法替代
dTree.prototype.delegateHighlightNode=function(cn){

}; 
//一定要返回值,生成节点的图片
dTree.prototype.delegateGetLeafImg=function(cn){
	return cn.icon;
}; 
 
