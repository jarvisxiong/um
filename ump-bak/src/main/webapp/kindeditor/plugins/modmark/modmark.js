/*******************************************************************************
 * KindEditor - WYSIWYG HTML Editor for Internet Copyright (C) 2006-2011
 * kindsoft.net
 * 
 * @author Roddy <luolonghao@gmail.com>
 * @site http://www.kindsoft.net/
 * @licence http://www.kindsoft.net/license.php
 ******************************************************************************/
_isua = navigator.userAgent.toLowerCase(),
_ISIE = _isua.indexOf('msie') > -1 && _isua.indexOf('opera') == -1;
var $ok ,firstTable;
var markEdit, aHtml;
var _edit = $("#keEditIframe");


// 遍历查找表格
function findTable(node,tagName){
	while(node){
		if(node.nodeName != null && node.nodeName.toLowerCase() == tagName){
			return node;
		}else if(node.nodeName != null && node.nodeName.toLowerCase() == "body" || node.nodeName.toLowerCase() == "html"){
			  return null;
		}else if(node.parentNode != null && typeof(node.parentNode) != "undefined"){
		  	if(node.parentNode.nodeName != null && typeof(node.parentNode.nodeName) != "undefined"  
		  		&& node.parentNode.nodeName.toLowerCase() == tagName){
		  		return node.parentNode;
		  	}else if(node.parentNode.offsetParent != null && typeof(node.parentNode.offsetParent) != "undefined" 
		  		&& node.parentNode.offsetParent.nodeName.toLowerCase() != tagName){
		  	 	node = node.parentNode.offsetParent;
		  	}else if(node.parentNode.offsetParent != null && typeof(node.parentNode.offsetParent) != "undefined" 
		  		&& node.parentNode.offsetParent.nodeName.toLowerCase() == tagName){
		  		return node.parentNode.offsetParent;
		  	}else{
		  		return null;
		  	}
		}else{
			return null;
		}
	}
}

// 遍历搜索包含整个表格中第一个表格，方向向下
function findAllNextTable(node,tagName){
	while(node){
		if(node.tagName != null && typeof(node.tagName) != "undefined" 
				&& node.tagName.toLowerCase() == tagName){
		   return node;
		}
		if(node.nextSibling != null){
			node = node.nextSibling;
			//修订版
			if(node.innerHTML != null && typeof(node.innerHTML) != "undefined"
					&& node.innerHTML.toLowerCase().indexOf(tagName) >= 0){
				return node;
			}else if(node.tagName != null && typeof(node.tagName) != "undefined" 
					&& node.tagName.toLowerCase() == tagName){
				return node;
			}
		}else{
			return null;
		}
	}
}
// 方向向上
function findAllPreTable(node,tagName){
	while(node){
		if(node.tagName != null && typeof(node.tagName) != "undefined" 
				&& node.tagName.toLowerCase() == tagName){
		   return node;
		}
		if(node.previousSibling != null){
			node = node.previousSibling;
			// 修订版
			if(node.innerHTML != null && typeof(node.innerHTML) != "undefined"
					&& node.innerHTML.toLowerCase().indexOf(tagName) >= 0){
				return node;
			}else if(node.tagName != null && typeof(node.tagName) != "undefined" && node.tagName.toLowerCase() == tagName){
				return node;
			}
		}else{
			return null;
		}
	}
}
/*
 * 检查是否选中已经修订内容 param @val:内容 @range:range对象
 */ 
function checkSelect(val,range){
	var flag = false;
	if(val.toLowerCase().indexOf("<ins") >= 0){
		flag =  true ;
		return flag;
	}
	else if(range.nodeName != null && typeof(range.nodeName) != "undefined" && range.nodeName.toLowerCase() == "ins"){
		flag =  true ;
		return flag;
	}
	
	// 判断其父结节是否是修订版
	var node = range.parentNode;
	while(node){
		if(node.nodeName.toLowerCase() == "ins"){
			flag = true;
			return flag;
		}else if(node.nodeName.toLowerCase() == "html" || node.nodeName.toLowerCase() == "body"){
			flag = false;
			return flag;
		}else if(node.parentNode != null){
			node = node.parentNode;
		}
	}
	return flag;
}

// 是否是删除操作
function isDel(val){
	var flag = false;
	if(val==null || val==undefined){
		return false;
	}
	var tval = val.toLowerCase().trim();
	if(tval  == ""){
		flag = true;
	}
	else if(tval == "&nbsp;"){
		flag = true;
	}
	else if(tval == "<br />"){
		flag = true;
	}
	else if(tval == "<strong></strong>&nbsp;"){
		flag = true;
	}
	return flag;
}

// 找出选择中包含中的第一个表格
function findIncludeTable(testR,tagName){
	var tnode;
	// 如果是开始是字符串则先到父结点
	if(testR.startContainer.nodeType == "3" && testR.startContainer.nextSibling == null ){
		tnode = testR.startContainer.parentNode;
	}else{
		tnode = testR.startContainer.nextSibling;
	}
	if(tnode != null){
		tnode = tnode.nextSibling;
		firstTable = findAllNextTable(tnode,tagName);
	}
	else if(firstTable == null){
		if(testR.endContainer.nodeType == "3" && testR.endContainer.previousSibling == null){
			tnode = testR.endContainer.parentNode;
		}else{
			tnode = testR.endContainer.previousSibling;
		}
		if(tnode != null){
			tnode = tnode.previousSibling;
		}
		firstTable = findAllPreTable(tnode,tagName);
	}
}

// 获取表格和粗体
function getAllElement(content,range,tagName){
	var getEle = null;
	var isOk = false;
	var returnVal = null;
	if(tagName.toLowerCase() == "table"){
		if(content.toLowerCase().indexOf(tagName)>=0 || content.toLowerCase().indexOf("td")>=0){
			isOk = true;
		}
	}else{
		if(content.toLowerCase().indexOf(tagName)>=0){
			isOk = true;
		}
	}
	if(isOk){
		var pnode;
		// 是否从表格上方选中但没有全选中
		pnode = range.startContainer;
		getEle = findTable(pnode,tagName);
		if(getEle == null || typeof(getEle) == "undefined"){
			// 是否从表格下方选中但没有全选中
			pnode = range.endContainer.parentNode;
			getEle = findTable(pnode,tagName);
		}

		// 包含整个表格
		if(getEle == null || typeof(getEle) == "undefined"){
			// findIncludeTable(range,tagName);
		}
		
		if(getEle != null && typeof(getEle) != "undefined"){
			returnVal = getEle.outerHTML.toString();
		}
	}
	return returnVal;
}


KindEditor.plugin('modmark', function(K) {
	var self = this, name = 'mark', modname = "modmark", css = 'background-color: yellow;text-decoration: none;cursor: pointer;';
	self.plugin.modmark = {		
		mod : function() {
			var modEdit;
			var lang = self.lang(name + '.'), 
			html = '<div style="padding:10px 20px;">' + '<div class="ke-dialog-row">' 
					+ '<label for="markDesc">' + lang.descript + '</label>'
					+ '<textarea id="markDesc" rows="10" cols="30" name="markDesc" style="width:100%;"></textarea></div></div>', 
			dialog = self.createDialog({
				name : name,
				width : 600,
				title :"修改批注",
				body : html,
				yesBtn : {
					name : self.lang('yes'),
					click : function(e) {
					var modCss = "background-color: yellow;text-decoration: line-through;cursor: pointer;";
					_val = markEdit.html();
					// 将p元素用正规全干掉
					_val = _val.replace(/<p>/i,"").replace(/<\/p>/i,"");
					_val = _val.trim();
					if(isDel(_val)){
						_val = "<span style='"+modCss+"'>"+aHtml+"</span>";
					}
					$(_edit).contents().find("#" + _markId).html(_val);
					$(_edit).contents().find("#" + _markId).find("table").css("background","yellow");
					self.hideDialog().focus();						
						
					var _content= "" +editor.html();
					_content=_content.replace(/'/ig, "‘");
			
					$.post(_ctx + "/sc/sc-contract-templet-info!saveHistory.action", {
							scContractId : $("#scContractId").val(),
							markContent:_val,
							markId:_markId,
							hisConId:$("#hisContId").val(),
							content:_content
						}, function(result) {
							var _resultJson = toJson(result);
								if (_resultJson != undefined && _resultJson.status != undefined) {
									if (_resultJson.status != true) {
										alert(_resultJson.errorMsg);
									} else {
										$("#hisContId").val(_resultJson.hisConId);
										//liwei3 add
										//alert("ttt");
										//alert(_markId);
										//alert(_val);
										//var ttt = $("div[ctrType=mark][isdblclick=true][forid="+_markId+"]").html();
										//alert(ttt);
									    if(typeof callbackEditMarkContent!='undefined' && callbackEditMarkContent instanceof Function){        
									    	callbackEditMarkContent(_markId,_val);  
									    }  
									    //var curMark = $("div[ctrType=mark][isDblclick=true][forid="+_markId+"]");
									    //curMark.html(_val);
									    //curMark.show();
									    return;
									}
								} else {
									alert("无法解析返回的信息！");
								}
						});
						return;
					}
				}
			}), 
			div = dialog.div, 
			markBox = K('textarea[name="markDesc"]', div);

			var _markId = "" + $("div[isDblclick=true]").attr("forid");

			//liwei3 add 因为editor是每次动态创建的,所以有可能取不到以前定位的keEditIframe元素
			//if(null==_edit || undefined==_edit || 0 == _edit.length){
			_edit = $("#keEditIframe");
			//}
				
			// 获取对应的标注内容
			var _val = $(_edit).contents().find("#" + _markId).html();
			var aHtml = _val;
			if(aHtml) {
				if(_val.toLowerCase().indexOf("<table")>-1 && _val.toLowerCase().indexOf("tableid")>-1){
					aHtml=_val.replace("background-color: rgb(255, 99, 71);","").replace("Tomato","").replace("yellow","").replace("BACKGROUND: tomato","");
				}
				markBox.val(aHtml);
				markEdit = null;
				markEdit = K.create('textarea[name="markDesc"]', {
					resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					items : [ 'bold','table']
				});
				markEdit.focus();
			}
		}
	};
	self.clickToolbar(modname, self.plugin.modmark.mod);
});

	