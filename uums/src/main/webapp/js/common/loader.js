 var height = window.screen.height-180;
 var width = window.screen.width;
 var leftW = 300;
 if(width>1200){
 	leftW = 500;
 }else if(width>1000){
 	leftW = 350;
 }else {
 	leftW = 100;
 }
 
 var _html = "<div id='loading' style='position:absolute;left:0;width:100%;height:"+height+"px;top:0;background:#fff;z-index:99999;'>"+
 "<div style='position:absolute;cursor:wait;left:"+leftW+"px;top:200px;width:auto;height:16px;padding:12px 5px 10px 30px;" +
 		"background:#fff url("+_ctx+"/js/jquery-easyui/themes/default/images/pagination_loading.gif) no-repeat scroll 5px 10px;border:2px solid #99BBE8;color:#000;'>"+
 "正在渲染组件，请稍候...</div></div>";
 
 window.onload = function(){
 	var _mask = document.getElementById('loading');
 	_mask.parentNode.removeChild(_mask);
 };
 document.write(_html);
 