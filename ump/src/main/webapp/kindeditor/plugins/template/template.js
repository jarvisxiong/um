/*******************************************************************************
 * KindEditor - WYSIWYG HTML Editor for Internet Copyright (C) 2006-2011
 * kindsoft.net
 * 
 * @author Roddy <luolonghao@gmail.com>
 * @site http://www.kindsoft.net/
 * @licence http://www.kindsoft.net/license.php
 ******************************************************************************/

KindEditor.plugin('template', function(K) {
	var self = this, name = 'template', lang = self.lang(name + '.'), htmlPath = self.pluginsPath + name + '/html/';
	function getFilePath(fileName) {
	//return "file://E:\\upload\\sctemplet\\20120208111947MPtR.html"+'?ver=' + encodeURIComponent(K.DEBUG ? K.TIME : K.VERSION);;
		return htmlPath + fileName + '?ver=' + encodeURIComponent(K.DEBUG ? K.TIME : K.VERSION);
	}
	self.clickToolbar(name, function() {
		var lang = self.lang(name + '.'), arr = [ '<div class="ke-plugin-template" style="padding:10px 20px;">', '<div class="ke-header">',
		// left start
				'<div class="ke-left">', lang.selectTemplate + ' <select>' ];
		K.each(lang.fileList, function(key, val) {
			arr.push('<option value="' + key + '">' + val + '</option>');
		});
		html = [ arr.join(''), '</select></div>',
				// right start
				'<div class="ke-right">', '<input type="checkbox" id="keReplaceFlag" name="replaceFlag" value="1" /> <label for="keReplaceFlag">' + lang.replaceContent + '</label>', '</div>',
				'<div class="ke-clearfix"></div>', '</div>', '<iframe class="ke-textarea" frameborder="0" style="width:458px;height:260px;background-color:#FFF;"></iframe>', '</div>' ].join('');
		var dialog = self.createDialog( {
			name : name,
			width : 500,
			title : self.lang(name),
			body : html,
			yesBtn : {
				name : self.lang('yes'),
				click : function(e) {
					var doc = K.iframeDoc(iframe);
					self[checkbox[0].checked ? 'html' : 'insertHtml'](doc.body.innerHTML).hideDialog().focus();
                    //替换输入控件
					var _edit = $("#keEditIframe");
					$(_edit).contents().find("span[otype=min]").unbind("click");
					$(_edit).contents().find("span[otype=min]").each(function(idx, input) {
						var _len = $(input).attr("len");
						if (_len == undefined) {
							_len = 10;
						}
						$(input).attr("style", "background-color:#dee1dd;display:-moz-inline-box;display: inline-block; overflow: visible; white-space:nowrap; vertical-align:bottom;cursor:pointer; width:" + _len + "px;border-bottom:1px solid #000;");
						$(this).html("&nbsp;&nbsp;");
						$(input).click(function() {
							var _edit = $("#keEditIframe");
							$(_edit).contents().find("span[otype=min]").attr("isClick", "false");
							$(this).attr("isClick", "true");
							editor.clickToolbar('datainput');					

						});

					});
				}
			}
		});
		var selectBox = K('select', dialog.div), checkbox = K('[name="replaceFlag"]', dialog.div), iframe = K('iframe', dialog.div);
		checkbox[0].checked = true;
		iframe.attr('src', getFilePath(selectBox.val()));
		selectBox.change(function() {
			iframe.attr('src', getFilePath(this.value));
		});
	});
});
