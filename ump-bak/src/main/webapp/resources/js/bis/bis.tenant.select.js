//----------------------------自动引入依赖的css 和 js文件----------------------------
function loadJs(url, callback){
	//防止重复加载
	var i = 0;
	$('script[src]','head').each(function(){
		var src = $(this).attr('src');
		if(src == url){
			i++;
		}
	});
	if(i>0)return;
	var done = false;
	var script = document.createElement('script');
	script.type = 'text/javascript';
	script.language = 'javascript';
	script.src = url;
	script.onload = script.onreadystatechange = function(){
		if (!done && (!script.readyState || script.readyState == 'loaded' || script.readyState == 'complete')){
			done = true;
			script.onload = script.onreadystatechange = null;
			if (callback){
				callback.call(script);
			}
		}
	};
	document.getElementsByTagName("head")[0].appendChild(script);
};
function loadCss(url, callback){
	var i = 0;
	$('link[src]','head').each(function(){
		var src = $(this).attr('src');
		if(src == url){
			i++;
		}
	});
	if(i>0)return;
	var link = document.createElement('link');
	link.rel = 'stylesheet';
	link.type = 'text/css';
	link.media = 'screen';
	link.href = url;
	document.getElementsByTagName('head')[0].appendChild(link);
	if (callback){
		callback.call(link);
	}
}
loadCss(_ctx+'/resources/js/jqueryplugin/jqModal/jqModal.css');
loadJs(_ctx+'/resources/js/common/common.js');
loadJs(_ctx+'/resources/js/jqueryplugin/jqModal/jqModal.js');

//----------------------------自动引入依赖的css 和 js文件----------------------------

/**
 * jquery插件形式对话框 ，选择租户
 */
 (function($){
	 $.extend({
		 onSelect : function(dom,opts){
			 var _this = dom;
			 //缓存容器
			 var _cacheMap = {};
			 var defaultOpt = {
					 width : '500',
					 height : '400',
					 title	: '请选择租户',
					 zIndex : 1000,//窗口层叠值
					 opacity : 30,//遮罩透明度
					 ctx:_ctx,
					 waitingHtml:'<p>Please wait..</p>',
					 muti:true,//租户多选，默认为true，设置false：单选
					 maxSelected:5,
					 nameField:'tenantName',//实体属性，回显文本对应的字段，
					 cdField:'bisTenantId'//实体属性，回显cd对应的字段，
					 
			 };
			 //初始化参数配置
			 var option = $.extend(defaultOpt,opts);
			 //先清空也米昂上可能已渲染过的弹出框，避免重复
			 var id = $(_this).attr('id');
			 //$('#jqmDialog_'+id).remove(); 保留渲染过的弹出框
			 //初始化弹出窗口容器
			 var jqmDlg = $('<div class="jqmDialog" id="jqmDialog_'+id+'"<div class="projectHeader"><div class="jqmClose">X</div>'+
					'<div class="jqmTitle">'+option.title+'</div></div><div class="jqmBody">'+option.waitingHtml+'</div>'+	
					'<div align=center class="jqmBottom"> <button class="jqmConfirmBtn">确定</button><button class="jqmCancelBtn">取消</button></div></div>');
			 $('div.jqmBody',jqmDlg).height(option.height+"px");
			 jqmDlg.css({'width':option.width+'px','marginLeft':(0-option.width/2)+'px'});
			 $('body').append(jqmDlg);
			 initDlgEvents();
			 var t = $('div.jqmBody',jqmDlg);
			 //初始化租户列表，并绑定点击忽而选择事件 -- 显示已选租户
			 function initTenants(){
				 var map = $(_this).data('cacheMap');
				 if(typeof (map)==='object'){
					 _cacheMap = $.extend(true,{},map);
					 var emptyFlg = true;
					 for(var k in _cacheMap){
						 emptyFlg = false;
						 break;
					 }
				 }
				 if(typeof(map)==='undefined'){
					 var name = option.nameEId?$('#'+option.nameEId).val():$('#'+option.nameEId).val();
					 var cd = option.cdEId?$('#'+option.cdEId).val():$('#'+option.cdEId).val();
					 var nameArray = $.trim(name).split(';');
					 var cdArray = $.trim(cd).split(';');
					 _cacheMap = {};
					 var k=0;
					 $.each(nameArray,function(i,n){
						 if(n!=''){
							 var o = {
									 tenantName:n,
									 bisTenantId:cdArray[i]
							 };
							 _cacheMap[cdArray[i]]=0;
							 k++;
						 }
					 });
					 
				 }
			 };
			 //初始化部分元素的绑定事件：
			 function initEvents(){
				 //初始显示租户toggle事件绑定
				 $('.tenantDisplay tr').each(function(){
					var obj = {};
					obj.bisTenantId = $(this).attr('bisTenantId');
					obj.tenantName = $(this).attr('tenantName');
					addAndBindTenant(obj,false);
				 });
			 }
			 //初始化窗口的确定和取消事件
			 function initDlgEvents(){
				 //确定 
				 $('.jqmConfirmBtn',jqmDlg).click(function(){
				 	callBack();
				 });
				 $('.jqmCancelBtn').click(function(){
					 //_cacheMap = {};
					 $(jqmDlg).jqmHide();
				 });
			 }
			 //租户选择框确定事件
			 function callBack(){
				 var check = true;
				 if(option.maxSelected>0){
					 var i = 0;
					 for( var k in _cacheMap){
						 i++;
						 if(i>option.maxSelected){
							 alert('对不起，最多选择'+option.maxSeletedProject+'个租户');
							 check = false;
							 break;
						 }
					 };
				 }
				 if(!check)return ;
				 var data = $.extend(true,{},_cacheMap);
				 $(_this).data('cacheMap',data);
				 var names = [];
				 var cds = [];
				 var nameStr = '';
				 var cdStr = '';
				 for ( var k in _cacheMap){
					 var obj = _cacheMap[k];
					 if(obj){
						 names.push(obj[option.nameField]);
						 cds.push(obj[option.cdField]);
					 }
				 }
				 if(names.length>0){
					 nameStr = names.join(';');
					 cdStr = cds.join(';');
					 if(option.muti){
						 nameStr +=';';
						 cdStr +=';';
						 
					 }
					 
				 }
				// if(option.nameEId){ $('#'+option.nameEId).val(nameStr); $('#'+option.cdEId).val(cdStr);	}else{	 $(_this).val(nameStr).next().val(cdStr);	 }
				 if($.isFunction(option.callback)){
					 option.callback(_cacheMap);
				 }
				 $(jqmDlg).jqmHide();
			 }
			 //根据租户信息添加并绑定租户事件
			 function addAndBindTenant(objects,addFlag,checked){
				 if($.isArray(objects)){
					 //记录长度
					 var s_start = 0;
					 //统计处理条数
					 var s_count = 1 ; 
					 //数据的长度
					 var len = objects.length;
					 for(var i=s_start;i<len;i++){
						 if(i<len){
							 //逻辑处理
							 if(objects[i])
								 bind(objects[i],addFlag,checked);
						
						 }else{
							 return ;
						 }
					 }
				 }else{
					 bind(objects,addFlag);
				 }
				 function bind(obj,flg,cFlg){
					 if(obj.bisTenantId=='')return ;
					 if(flg){
						 var strList = new Array();
					 }
					 var objTenant = $('#'+obj.bisTenantId);
					 objTenant.click(function(){
						var className = $(this).attr('className');
						if(className=='selected'){
							$(this).attr('className','unSelected');
							delete _cacheMap[obj.bisTenantId];
						}else if(className == 'unSelected'){
							$(this).attr('className','selected');
							_cacheMap[obj.bisTenantId] = obj;
							
						}
					 });
					 var projectObj = _cacheMap[obj.bisTenantId];
					 if(projectObj){
						 objTenant.attr('className','selected');
					 }
					 if(cFlg == '0'){
						 if(projectObj){
							 objTenant.trigger('click');
						 }
					 }else if(cFlg == '1'){
						 if(!projectObj){
							 objTenant.trigger('click');
						 }
					 }
				 }
			 }
			 //点击时弹出租户选择框
			 $(_this).click(function(){
				var url = option.ctx + '/bis/bis-tenant!mustFactInterface.action';
				$(jqmDlg).jqm({
					trigger:false,
					ajax:url,
					ajaxText:option.waitingHtml,
					target:t,
					modal:true,
					zIndex:option.zIndex,
					overlay:option.opacity,
					onHide:function(h){
						$('div.jqmBody',jqmDlg).empty();
						//h.w.slideUp(200);
						//添加动画效果
						var wE = h.w;
						var width = wE.width();
						var height = wE.height();
						wE.animate({
							height:20,width:20
						},300,function(){
							wE.hide();
							wE.css({'width':width,'height':height});
						});
						h.o.remove();
					},
					onLoad:function(r){
						initTenants();
						initEvents();
						$('div.jqmBody',jqmDlg).css('overflow','auto');
					}
				}).jqDrag('.jqmTitle');
				$(jqmDlg).jqmShow();
			 });
		 }
	 });
	 $.fn.onSelect = function(opts){
			return this.each(function(){
				$.onSelect(this,opts||{});
			});
		};
 })(jQuery);