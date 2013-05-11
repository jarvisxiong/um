/* tangbin - http://www.planeArt.cn - MIT Licensed */
(function($){
	// tipWrap: 	提示消息的容器
	// maxNumber: 	最大输入字符
	$.fn.artTxtCount = function(tipWrap, maxNumber){
		// 统计字数
		var count = function(){
			var btn = $(this).closest('form').find(':submit'),
				val = $(this).val().length,
				
				// 是否禁用提交按钮
				disabled = {
					on: function(){
						btn.removeAttr('disabled');
					},
					off: function(){
						btn.attr('disabled', 'disabled');
					}
				};
				
			if (val == 0) disabled.off();
			if(val <= maxNumber){
				if (val > 0) disabled.on();
				tipWrap.html('<span>\u8FD8\u80FD\u8F93\u5165 </span><span style="font-weight:bold;color:green;">' + (maxNumber - val) + '</span> \u4E2A\u5B57</span>');
			}else{
				disabled.off();
				tipWrap.html('<span>\u5DF2\u7ECF\u8D85\u51FA </span><span style="font-weight:bold;color:red;">' + (val - maxNumber) + '</span><span> \u4E2A\u5B57</span>');
			};
		};
		$(this).bind('keyup change', count);
		
		return this;
	};
})(jQuery);