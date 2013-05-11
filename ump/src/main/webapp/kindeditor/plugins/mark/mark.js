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
var rangeHy,$ok ,firstTable;
var tempOk;
var markEdit, aHtml;
var _edit = $("#keEditIframe");;
function insertIEHTML(html) {
	if (keEditIframe.document.selection.type.toLowerCase() != "none"){
		keEditIframe.document.selection.clear() ;
	}
	rangeHy.pasteHTML(html) ; 
}
// 遍历查找表格
function findTable(node,tagName){
	while(node){
			 if(node.nodeName != null && node.nodeName.toLowerCase() == tagName){
				return node;
			}
			 else if(node.nodeName != null && node.nodeName.toLowerCase() == "body" || node.nodeName.toLowerCase() == "html"){
				  return null;
			}
			else if(node.parentNode != null && typeof(node.parentNode) != "undefined"){
		  	if(node.parentNode.nodeName != null && typeof(node.parentNode.nodeName) != "undefined"  && node.parentNode.nodeName.toLowerCase() == tagName){
		  		return node.parentNode;
		  	}
		  	else if(node.parentNode.offsetParent != null && typeof(node.parentNode.offsetParent) != "undefined" 
		  	&& node.parentNode.offsetParent.nodeName.toLowerCase() != tagName
		  	){
		  	 	node = node.parentNode.offsetParent;
		  	}
		  	else if(node.parentNode.offsetParent != null && typeof(node.parentNode.offsetParent) != "undefined" 
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
		if(node.tagName != null 
				&& typeof(node.tagName) != "undefined" 
					&& node.tagName.toLowerCase() == tagName){
		   return node;
		}
		if(node.nextSibling != null){
			node = node.nextSibling;
			// 修订版
			if(node.innerHTML != null && typeof(node.innerHTML) != "undefined"
					&& node.innerHTML.toLowerCase().indexOf(tagName) >= 0
					){
				return node;
			}else if(node.tagName != null && typeof(node.tagName) != "undefined" && node.tagName.toLowerCase() == tagName){
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
		if(node.tagName != null 
				&& typeof(node.tagName) != "undefined" 
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
// 创建弹出对话框
function createDialog(obj,name,html,css){
	var d;
	d = obj.createDialog( {
		name : name,
		width : 800,
		title : obj.lang(name),
		body : html,
		yesBtn : {
			name : obj.lang('yes'),
			click : function(e) {
				var _markId = "" + (new Date().getTime());
				var _val = markEdit.html();
				var reHtml,v ;
				var statusContent = "";
					// 将p元素用正规全干掉
					_val = _val.replace(/<p>/i,"").replace(/<\/p>/i,"");
					_val = _val.trim();
					// 如何是整个空值
					if(isDel(_val)){
						css = 'background-color: yellow;text-decoration: line-through;cursor: pointer;';
						_val = aHtml;
						reHtml = "<ins  id='" + _markId + "'  style='" + css + "' isdel='true'  isNew='1'></ins>";
						v  = "<ins  id='" + _markId + "' comment='"+_val+"'  style='" + css + "' isdel='true' isNew='1'>"+_val+"</ins>";
					}else{
						reHtml = "<ins  id='" + _markId + "'  style='" + css + "'  isNew='1'></ins>";
						v  = "<ins  id='" + _markId + "' comment='"+_val+"'  style='" + css + "'  isNew='1'>"+_val+"</ins>";
					}
			
				// IE
				if(_ISIE){
						if((rangeHy.parentElement() != null 
								&& (rangeHy.parentElement().nodeName.toLowerCase() == 'table' || rangeHy.parentElement().nodeName.toLowerCase() == 'tbody')	) ){
							// var nmd = editor.cmd.range;
							var tRange = obj.cmd.range.selectNode(tempOk);
							obj.insertHtml(reHtml).hideDialog().focus();
						}
						else if((rangeHy.parentElement() != null 
								&& (rangeHy.parentElement().nodeName.toLowerCase() == 'strong' || rangeHy.parentElement().nodeName.toLowerCase() == 'tbody')	) ){
							var tRange = obj.cmd.range.selectNode(tempOk);
							obj.insertHtml(reHtml).hideDialog().focus();
						}
						else if(firstTable != null && typeof(firstTable) != "undefined"){
							var nmd = editor.cmd.range;
							var tRange = nmd.selectNode(firstTable);
							obj.insertHtml(reHtml).hideDialog().focus();
						}
						else{
							insertIEHTML(v);
							obj.hideDialog().focus();
						}
				}
				// 非IE
				else{
					obj.insertHtml(reHtml).hideDialog().focus();
					
				}
			
				
				
				$(_edit).contents().find("#" + _markId).attr("comment", aHtml.replace(/'/ig, "‘").replace(/"/ig, "“")).html(_val);
				
				
				var _ins=$(_edit).contents().find("#"+_markId);
				 
			     $(_ins).find("table").attr("tableid",_markId).css("background","yellow");
				
				if (isCanEditor()) {
					$(_edit).contents().find("span[otype=min]").click(function() {
						$(_edit).contents().find("span[otype=min]").attr("isClick", "false");
						$(this).attr("isClick", "true");
						editor.clickToolbar('datainput');
					});
				}
				statusContent = "<span style='color:red;'>批注成功</span>";
	
				var _content= "" +editor.html();
				_content=_content.replace(/'/ig, "‘");
				$("#markId").val(_markId);
			
				$.post(_ctx + "/sc/sc-contract-templet-info!saveHistory.action", {
							scContractId : $("#scContractId").val(),
							hisConId:$("#hisContId").val(),		
							markId:_markId,
							actType:"add",						
							markContent:_val,										
						    content:_content
					
						}, function(result) {

							var _resultJson = toJson(result);

							if (_resultJson != undefined && _resultJson.status != undefined) {

								if (_resultJson.status != true) {
									alert(_resultJson.errorMsg);

								} else {
									$("#hisContId").val(_resultJson.hisConId);							
									var _ins=$(_edit).contents().find("#"+$("#markId").val());
									_ins.focus();
									var _instable=$(_edit).contents().find("#"+$("#markId").val()).find("table[tableid="+$("#markId").val()+"]");
									
							    	var _divMarkRight=$("div[forid="+$("#markId").val()+"]");
									$(_ins).attr("id",_resultJson.markId);
									$(_divMarkRight).attr("forid",_resultJson.markId);
									$(_instable).attr("tableid",_resultJson.markId);
									//return;
								}
								// 加载右侧原始标注信息
								loader();
							} else {
								alert("无法解析返回的信息！");
							}
							
						});			
			$("#contractStatus").html(statusContent);
			return false;

		}
		},noBtn : {
			name : obj.lang('no'),
			click : function(e) {	obj.hideDialog().focus();}
		}
	});
	return d;
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
KindEditor.plugin('mark', function(K) {
	var self = this, name = 'mark', modname = "modmark", css = 'background-color: yellow;text-decoration: none;cursor: pointer;';
	self.plugin.mark = {
		edit : function() {
			var r ;
			var lang = self.lang(name + '.'), 
			html = '<div style="padding:10px 20px;">' + '<div class="ke-dialog-row">' + '<label for="markDesc">' + lang.descript + '</label>'
					+ '<textarea id="markDesc" rows="10" cols="30" name="markDesc" style="width:100%;"></textarea></div>' + '</div>'
					;
			aHtml = "";
			var ed = editor.cmd.range.startContainer;
				// IE下直接调用range对象
				if(_ISIE){
					var selectContent;
					var p;
					firstTable = null;
					keEditIframe.focus();
					rangeHy = keEditIframe.document.selection.createRange();
					s = rangeHy.parentElement();
					// 表格
					// if((rangeHy.parentElement().offsetParent != null &&
					// rangeHy.parentElement().offsetParent.nodeName.toLowerCase()
					// == 'table') ){
					if((rangeHy.parentElement() != null 
							&& (rangeHy.parentElement().nodeName.toLowerCase() == 'table' || rangeHy.parentElement().nodeName.toLowerCase() == 'tbody')) ){
						tempOk = rangeHy.parentElement().offsetParent;
						aHtml = "" + $.trim(tempOk.outerHTML.toString());;
					}
					else if((rangeHy.parentElement() != null 
							&& (rangeHy.parentElement().nodeName.toLowerCase() == 'strong' || rangeHy.parentElement().nodeName.toLowerCase() == 'tbody')) ){
						tempOk = rangeHy.parentElement();
						aHtml = "" + $.trim(tempOk.outerHTML.toString());;
					}
					
					else{
						var testR = editor.cmd.range;
						var tRange ;
						selectContent = rangeHy.htmlText.toString();
						if(selectContent.toLowerCase().indexOf("ins") >= 0){
							self.focus();
							alert("不能包含已有修订项！！\n如要修改请双击右侧批注栏内容！！！");
							return false;
						}
						
						
						/**
						 * 判断是不是填空 start
						 */
						if(selectContent.toLowerCase().indexOf("span") >= 0){
							var $s = $(document.createElement("div"));
							$s.html(selectContent);
							var $fillTemp = $s.find("span:first");
							if($fillTemp.attr("otype") == "min"){
								// alert($fillTemp.attr("id")+"这是填空");
								$(_edit).contents().find("span[otype=min]").attr("isClick",
								"false");
								$(_edit).contents().find("span[id="+$fillTemp.attr("id")+"]").attr("isClick",
								"true");
								editor.clickToolbar('datainput');
								return false;
							}
						}else{
							pnode = testR.startContainer;
							firstTable = findTable(pnode,"span");
							if(firstTable == null || typeof(firstTable) == "undefined"){
								// 是否从表格下方选中但没有全选中
								pnode = testR.endContainer.parentNode;
								firstTable = findTable(pnode,"span");
							}
							if(firstTable != null && typeof(firstTable) != "undefined"){
								var $s = $(document.createElement("div"));
								$s.html( firstTable.outerHTML.toString());
								var $fillTemp = $s.find("span:first");
								if($fillTemp.attr("otype") == "min"){
									// alert($fillTemp.attr("id")+"这是填空");
									$(_edit).contents().find("span[otype=min]").attr("isClick",
									"false");
									$(_edit).contents().find("span[id="+$fillTemp.attr("id")+"]").attr("isClick",
									"true");
									editor.clickToolbar('datainput');
									return false;
								}
							}
							
						}
						/**
						 * 判断填空 end
						 */
						
						
						
						// 是否有表格
						if(selectContent.toLowerCase().indexOf("table")>=0 || selectContent.toLowerCase().indexOf("td")>=0){
							var pnode;
							// 是否从表格上方选中但没有全选中
							pnode = testR.startContainer;
							firstTable = findTable(pnode,"table");
							if(firstTable == null || typeof(firstTable) == "undefined"){
								// 是否从表格下方选中但没有全选中
								pnode = testR.endContainer.parentNode;
								firstTable = findTable(pnode,"table");
							}
							
							/*
							 * 包含整个表格 if(firstTable == null ||
							 * typeof(firstTable) == "undefined"){
							 * //findIncludeTable(testR,"table"); }
							 */
							
							if(firstTable != null && typeof(firstTable) != "undefined"){
								selectContent = firstTable.outerHTML.toString();
							}
						}			
						// 是否有Strong加粗
						else  if(selectContent.toLowerCase().indexOf("strong")>=0){
							var pnode;
							// 是否从表格上方选中但没有全选中
							pnode = testR.startContainer;
							firstTable = findTable(pnode,"strong");
							if(firstTable == null || typeof(firstTable) == "undefined"){
								// 是否从表格下方选中但没有全选中
								pnode = testR.endContainer.parentNode;
								firstTable = findTable(pnode,"strong");
							}
							
							
							/*
							 * 包含整个表格 if(firstTable == null ||
							 * typeof(firstTable) == "undefined"){
							 * //findIncludeTable(testR,"strong"); }
							 */
							
							if(firstTable != null && typeof(firstTable) != "undefined"){
								selectContent = firstTable.outerHTML.toString();
							}
						}
						// 是否是填空项
						else  if(selectContent.toLowerCase().indexOf("span")>=0){
							var pnode;
							// 是否从表格上方选中但没有全选中
							pnode = testR.startContainer;
							firstTable = findTable(pnode,"span");
							if(firstTable == null || typeof(firstTable) == "undefined"){
								// 是否从表格下方选中但没有全选中
								pnode = testR.endContainer.parentNode;
								firstTable = findTable(pnode,"span");
							}
							
							
							/* 包含整个表格 */
							if(firstTable == null || typeof(firstTable) == "undefined"){
								// findIncludeTable(testR,"span");
							}
							
							if(firstTable != null && typeof(firstTable) != "undefined"){
								//alert($(firstTable).attr("id"));
								// $(_edit).contents().find("span[id="+$(firstTable).attr("id")+"]");
							}
						}
						aHtml = "" + $.trim(selectContent);
						
					}
					if(checkSelect(aHtml,editor.cmd.range.startContainer)){
						self.focus();
						alert("不能包含已有修订项！！\n如要修改请双击右侧批注栏内容！！！");
						return false;
					}			
					
				}else{
					
					aHtml = "" + editor.selectedHtml();
					
					var testR = editor.cmd.range;
					// 获取strong
					var pnode;
					
					
					/**
					 * 判断是不是填空 start
					 */
					if(aHtml.toLowerCase().indexOf("span") >= 0){
						var $s = $(document.createElement("div"));
						$s.html(aHtml);
						var $fillTemp = $s.find("span:first");
						if($fillTemp.attr("otype") == "min"){
							// alert($fillTemp.attr("id")+"这是填空");
							$(_edit).contents().find("span[otype=min]").attr("isClick",
							"false");
							$(_edit).contents().find("span[id="+$fillTemp.attr("id")+"]").attr("isClick",
							"true");
							editor.clickToolbar('datainput');
							return false;
						}
					}else{
						pnode = testR.startContainer;
						firstTable = findTable(pnode,"span");
						if(firstTable == null || typeof(firstTable) == "undefined"){
							// 是否从表格下方选中但没有全选中
							pnode = testR.endContainer.parentNode;
							firstTable = findTable(pnode,"span");
						}
						if(firstTable != null && typeof(firstTable) != "undefined"){
							var $s = $(document.createElement("div"));
							var selHTML="";
							// 如果支持outerHTML
							if(typeof(firstTable.outerHTML)!="undefined"){							
								selHTML = firstTable.outerHTML.toString();
								
							}else{										
								//$(firstTable).html()直接使用此方法无法达到IE中的outerHTML效果，故借助临时div达到同样效果
								var tempContainer = $("<div></div>");	
								//克隆数据
								var firstTablec=$(firstTable).clone();
								$(tempContainer).append(firstTablec);
								// 否则使用jquery获取控件HTML数据      
								selHTML =$(tempContainer).html();						
								
							}
							
							
							$s.html( selHTML);
							var $fillTemp = $s.find("span:first");
							if($fillTemp.attr("otype") == "min"){
								// alert($fillTemp.attr("id")+"这是填空");
								$(_edit).contents().find("span[otype=min]").attr("isClick",
								"false");
								$(_edit).contents().find("span[id="+$fillTemp.attr("id")+"]").attr("isClick",
								"true");
								editor.clickToolbar('datainput');
								return false;
							}
						}
						
					}
					/**
					 * 判断填空 end
					 */
					
					
					// 是否从表格上方选中但没有全选中
					pnode = testR.startContainer;
					firstTable = findTable(pnode,"strong");
					if(firstTable == null || typeof(firstTable) == "undefined"){
						// 是否从表格下方选中但没有全选中
						pnode = testR.endContainer.parentNode;
						firstTable = findTable(pnode,"strong");
					}
					
					if(firstTable != null && typeof(firstTable) != "undefined"){
						// 如果支持outerHTML
						if(typeof(firstTable.outerHTML)!="undefined"){							
							aHtml = firstTable.outerHTML.toString();
							
						}else{
							//修改人：qlb 2012/2/29  firefox下粗体选择				
							//$(firstTable).html()直接使用此方法无法达到IE中的outerHTML效果，故借助临时div达到同样效果
							var tempContainer = $("<div></div>");	
							//克隆数据
							var firstTablec=$(firstTable).clone();
							$(tempContainer).append(firstTablec);
							// 否则使用jquery获取控件HTML数据      
							aHtml =$(tempContainer).html();
							
							
						}
						
						testR.selectNode(firstTable);
					}
				
					
					
					if(aHtml.toLowerCase().indexOf("ins") >= 0){
						self.focus();
						alert("不能包含已有修订项！！\n如要修改请双击右侧批注栏内容！！！");
						return false;
					}
					if(checkSelect(aHtml,editor.cmd.range.startContainer)){
						self.focus();
						alert("不能包含已有修订项！！\n如要修改请双击右侧批注栏内容！！！");
						return false;
					}
				}
				if(aHtml.trim()==""){
					
					alert("请选择要增加批注的文本！");
					return;
				}
				dialog = createDialog(self,name,html,css);
				
				
				if (aHtml) {
					div = dialog.div, markBox = K('textarea[name="markDesc"]', div);
					r = editor.cmd.range;
					markBox.val(aHtml);
					markEdit = null;
					markEdit = K.create('textarea[name="markDesc"]', {
						resizeType : 2,
						allowPreviewEmoticons : false,
						allowImageUpload : false,
						items : [ 'bold','table' ]
					});
				}

		}

	};
	self.clickToolbar(name, self.plugin.mark.edit);
	
});

	