function xmlTree(name) {
	this.name = name; // 实例名称
	this.xmlFile = 'ResourceMapping.xml'; // 默认xml文件
	this.iconPath = 'images/' // 默认图标路径
	this.iconFolder = 'tree_icon_folder.gif'; // 默认文件夹图标
	this.iconFile = 'tree_icon_file.gif'; // 默认文件图标
	this.iconOpen = 'tree_arrow_open.gif'; // 默认箭头展开图标
	this.iconOver = 'tree_arrow_over.gif'; // 默认箭头活动图标
	this.iconClose = 'tree_arrow_close.gif'; // 默认箭头关闭图标
	this.mode = 0; // 初始模式，默认全部关闭。0全部关闭，1全部展开
	this.html = ''; // 最终输出html代码
	this.prevTip = null; // 上一次被显示的tip的时间编号 (内部使用)
	this.prevSelected = null; // 上一次被选中的节点的自动编号 (内部使用)
	this.treeNodesCount = 0;
}

xmlTree.prototype.createXMLDOM = function() { // 生成XMLDOM对象
	var xmldom;
	if (window.ActiveXObject) {
		try {
			xmldom = new ActiveXObject("MsXml2.XMLDOM");
		} catch (e) {
			try {
				xmldom = new ActiveXObject("Microsoft.XMLDOM");
			} catch (e) {
			}
		}
	} else {
		if (document.implementation && document.implementation.createDocument) {
			var xmldom = document.implementation
					.createDocument("", "doc", null);
		}
	}
	xmldom.async = false;
	xmldom.resolveExternals = false;
	xmldom.validateOnParse = false;
	xmldom.preserveWhiteSpace = true;
	return xmldom;
}

xmlTree.prototype.createTree = function() { // 生成并打印
	var xmldom = this.createXMLDOM();
	document.write('<div id="tree"><\/div>'); // 树所用层
	document.write('<div id="treeTip"><\/div>'); // 提示所用层
	document.getElementById('treeTip').style.visibility = 'visible';
	document.getElementById('treeTip').style.display = 'none';
	if (xmldom.load(this.xmlFile)) {
		this.createNodes(xmldom);
	} else {
		this.html = 'Load XML Error';
	}
	document.getElementById('tree').innerHTML = this.html;
	return;
}

xmlTree.prototype.getFirstChildData = function(obj, name) { // 取得指定名称节点的第一个子节点的数据
	var result = '';
	if (typeof(obj) == 'object' && name != null && name != '') {
		var node = obj.getElementsByTagName(name);
		if (node != null && node.length > 0) {
			result = node[0].firstChild.data;
		}
	}
	return result;
}

xmlTree.prototype.checkChildNodes = function(obj, id) { // 检测是否有分支
	var result = false;
	var nodes = obj.getElementsByTagName('node');
	if (nodes != null && nodes.length > 0) {
		for (var i = 0; i < nodes.length; i++) {
			if (nodes[i].getAttribute('parentid') == id) {
				result = true;
				break;
			}
		}
	}
	return result;
}

xmlTree.prototype.createNodes = function(obj, id) { // 生成指定编号节点的树
	if (typeof(id) == 'undefined')
		id = null; // 如果没有id传入则为根节点
	var nodes = obj.getElementsByTagName('node');
	if (nodes != null && nodes.length > 0) {
		this.treeNodesCount = nodes.length;
		var modeClass, modeSrc, iconClass, iconSrc;
		var nid, npid, nname, nicon, nlink, ntarget, nexplain, hasChildNodes;

		// 判断模式类型，并应用样式
		modeClass = 'close';
		modeSrc = this.iconPath + this.iconClose;
		if (this.mode == 1) {
			modeSrc = this.iconPath + this.iconOpen;
			modeClass = 'open';
		}
		if (id == null)
			modeClass = 'open'; // 若为根节点则显示
		this.html += '<ul id="tree_' + id + '_c" class="' + modeClass + '">';

		for (var i = 0; i < nodes.length; i++) {
			npid = nodes[i].getAttribute('parentid');
			if (npid == id) {
				// 初始化
				modeClass = 'close';
				iconClass = 'icon-file';
				iconSrc = this.iconPath + this.iconFile;

				// 取得节点编号并检测
				nid = nodes[i].getAttribute('id');
				this.html += '<li id="tree_' + nid + '"><span onclick="'
						+ this.name + '.action(this,event);" onmouseover="'
						+ this.name + '.action(this,event);" onmouseout="'
						+ this.name + '.action(this,event);">';

				// 取得节点自定义图标
				// 判断是否含有子节点，并确定箭头和图标的图片及样式
				nicon = this.getFirstChildData(nodes[i], 'icon');
				hasChildNodes = this.checkChildNodes(obj, nid);
				if (hasChildNodes) {
					iconClass = '';
					iconSrc = this.iconPath + this.iconFolder;
					this.html += '<img id="tree_' + nid + '_a" src="' + modeSrc
							+ '" class="arrow" \/>'; // 箭头
				}
				if (nicon != '')
					iconSrc = nicon;
				this.html += '<img id="tree_' + nid + '_i" src="' + iconSrc
						+ '" class="' + iconClass + '" \/>'; // 图标

				// 取得节点连接
				nlink = this.getFirstChildData(nodes[i], 'link');
				if (nlink != '') {
					nlink = ' href="' + nlink + '"';
				} else {
					nlink = ' href="javascript:;"';
				}

				// 取得节点目标
				ntarget = this.getFirstChildData(nodes[i], 'target');
				if (ntarget != '') {
					ntarget = ' target="' + ntarget + '"';
				}

				// 取得节点说明
				nexplain = this.getFirstChildData(nodes[i], 'explain');
				if (nexplain != '') {
					nexplain = ' onmouseover="' + this.name + '.treeTips(\''
							+ nexplain + '\');" onmouseout="' + this.name
							+ '.treeTips();"'
				}

				// 取得节点名称
				nname = this.getFirstChildData(nodes[i], 'nameCN');
				// nname = this.getFirstChildData(nodes[i], 'nameEN');
				this.html += '<input type="checkbox" nname="' + nname
						+ '" nexplain="'
						+ this.getFirstChildData(nodes[i], 'explain')
						+ '" id="' + nid + '" onclick="' + this.name
						+ '.action(this,event);"><\/input>' + '<a id="tree_'
						+ nid + '_l" onclick="' + this.name
						+ '.action(this,event);"' + nlink + ntarget + nexplain
						+ '>' + nname + '<\/a><\/span>';

				// obj.documentElement.removeChild(nodes[i]);
				if (hasChildNodes)
					this.createNodes(obj, nid); // 迭代子节点

				this.html += '<\/li>';
			}
		}
		this.html += '<\/ul>';
	}
	return;
}

xmlTree.prototype.getAllChildrenNodes = function(objNid, objChecked) {
	var hasChildNodes, xmldom, nid, npid;
	xmldom = this.createXMLDOM();
	if (xmldom.load(this.xmlFile)) {
		hasChildNodes = this.checkChildNodes(xmldom, objNid);
	} else {
		this.html = 'Load XML Error';
	}
	if (hasChildNodes) {
		var nodes = xmldom.getElementsByTagName('node');
		if (nodes != null && nodes.length > 0) {
			for (var i = 0; i < nodes.length; i++) {
				npid = nodes[i].getAttribute('parentid');
				if (npid == objNid) {
					nid = nodes[i].getAttribute('id');
					hasChildNodes = this.checkChildNodes(xmldom, nid);
					// 如果选中那么加入到节点数组中
					if (objChecked == true) {
						document.getElementById(nid).checked = true;
					}
					// 否则从该数组中移出
					else {
						document.getElementById(nid).checked = false;
					}
					if (hasChildNodes) {
						this.getAllChildrenNodes(nid, objChecked);
					}
				}
			}
		}
	}
}

// 根据选中的复选框得到相应的树节点
xmlTree.prototype.getSelectedNode = function(obj) {
	this.getAllChildrenNodes(obj.id, obj.checked);
	var a, cb, s = "";
	for (var i = 1; i <= this.treeNodesCount; i++) {
		cb = document.getElementById(i);
		if (cb.checked == true) {
			a = cb.id;
			s += a + ", ";
		}
	}
	document.getElementById("form_hf_treeNode").value = s;
	this.startRequestGetRoleResourceTable(s);
}

xmlTree.prototype.action = function(obj, e) { // 节点操作
	e = e ? e : (window.event ? window.event : null); // 获取操作类型
	e = e.type;
	if (obj.tagName == 'INPUT') {
		try {
			this.prevSelected.className = '';
		} catch (e) {
		}
		this.prevSelected = obj;
		obj.className = 'selected';
		this.getSelectedNode(obj);
	}
	if (obj.tagName == 'A') {
		try {
			this.prevSelected.className = '';
		} catch (e) {
		}
		this.prevSelected = obj;
		obj.className = 'selected';
	}
	if (obj.tagName == 'SPAN') {
		var arrow = obj.firstChild; // 取得箭头对象
		var borther = obj;
		while (borther.tagName != 'UL') { // 取得分支对象
			borther = borther.nextSibling;
			if (borther == null)
				break;
		}
		if (borther != null) {
			switch (e) { // 检测操作类型并执行相应代码
				case 'click' :
					if (borther.className == 'open') {
						borther.className = 'close';
						arrow.src = this.iconPath + this.iconClose;
					} else {
						borther.className = 'open';
						arrow.src = this.iconPath + this.iconOpen;
					}
					break;
				case 'mouseover' :
					if (arrow.tagName == 'IMG' && borther.className == 'close') {
						arrow.src = this.iconPath + this.iconOver;
					}
					break;
				case 'mouseout' :
					if (arrow.tagName == 'IMG' && borther.className == 'close') {
						arrow.src = this.iconPath + this.iconClose;
					}
					break;
			}
		}
	}
	return;
}

xmlTree.prototype.treeTips = function(msg) { // 提示栏
	if (this.prevTip != null)
		clearTimeout(this.prevTip);
	var obj = document.getElementById('treeTip');
	if (obj != null) {
		if (this.treeTips.arguments.length < 1) { // hide
			obj.style.display = 'none';
		} else { // show
			obj.innerHTML = msg;
			this.prevTip = setTimeout(
					'document.getElementById("treeTip").style.display = "block"',
					300);
			document.onmousemove = this.moveToMouseLoc;
		}
	}
	return;
}

xmlTree.prototype.moveToMouseLoc = function(e) { // 移动到鼠标所在位置
	var obj = document.getElementById('treeTip');
	if (obj != null) {
		var offsetX = -10, offsetY = 20;
		var x = 0, y = 0;
		if (window.event) {
			x = event.x + document.body.scrollLeft;
			y = event.y + document.body.scrollTop;
		} else {
			x = e.pageX;
			y = e.pageY;
		}
		obj.style.left = x + offsetX + 'px';
		obj.style.top = y + offsetY + 'px';
	}
	return;
}

// AJAX
var xmlHttpGetRoleResourceTable;
// 创建XMLHttpRequest对象
xmlTree.prototype.createXMLHttpRequest = function() {
	if (window.XMLHttpRequest) {
		xmlHttpGetRoleResourceTable = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		try {
			xmlHttpGetRoleResourceTable = new ActiveXObject("MsXml2.XMLHTTP");
		} catch (e) {
			try {
				xmlHttpGetRoleResourceTable = new ActiveXObject("Microsoft.XMLHttp");
			} catch (e) {
			}
		}
	}
}

xmlTree.prototype.startRequestGetRoleResourceTable = function(s) {
	this.createXMLHttpRequest();
	xmlHttpGetRoleResourceTable.onreadystatechange = this.handleStateChange;
	xmlHttpGetRoleResourceTable.open("POST",
			"../admin/ajax-RoleResourceTable.htm?resource=" + s, true);
	xmlHttpGetRoleResourceTable.send(null);
}

xmlTree.prototype.handleStateChange = function() {
	if (xmlHttpGetRoleResourceTable.readyState == 4) {
		if (xmlHttpGetRoleResourceTable.status == 200) {
			document.getElementById("RoleResourceTable").innerHTML = xmlHttpGetRoleResourceTable.responseText;
		}
	}
}
