/*
 * 功能说明: 模糊搜索下拉框

 * 参数说明:
 * @param url 后台方法路径
 * @param showArray 显示后台返回的json对象的属性集合
 * @param idArray 选中控件后,将后台json对象属性赋值至对应字段 
 * @param param json对象,用于额外传至后台参数
 * @param callback
 * @param dataMapIdName {name1: domeId1,name2:domeId2} 生成 &name1=$('#domeId').val() key不能一样
 * 
 * 注: 返回json格式为二维数组
 * 		[['ad2323434','青岛XX公司'],['ffere22','青岛YY公司'],['12edr','青岛ZZ公司']]
 * 选择后控件会将数组第一个值赋给隐藏域,第二个值赋给显示域
 * created by shixy at 2010-12-06 14:59
 * 
 * 
 * 前台调用方式,如下:
 * $('#delayApproveUserName').quickSearch(
 *   _PATH+'/desk/desk-wab!quickSearch.action',//调用url
 *   ['uiid','userName','orgName','centerName'],//下拉显示为:  uiid|userName|orgName|centerName
 *   {uiid:'delayApproveUserCd',userName:'delayApproveUserName'}//将uiid赋值至delayApproveUserCd,userName赋值至delayApproveUserName
 * );
 * @version 1.0.1
 * @history 载入前提示'查询中...'
 */
(function($){
	$.fn.quickSearch = function(url,showArray,idArray,param,callback,dataMapIdName){
		var result_con_id = "quickSearch";
		var _this = $(this);
		//var _next = nextId?$('#'+nextId):$(this).next();
		if( $('#'+result_con_id).length == 0){
			$('body').append('<div id="'+result_con_id+'" style="white-space:nowrap;height:150px;min-width:150px; margin:5px; position: absolute;z-index:9999999; display: none;border: 1px solid gray;background-color: #e8e8e8;overflow-x:hidden; overflow-y:auto;"></div>');
		}
		
		
		// 查询数据
		var searchTime = null;
		var search = function(){
			if(searchTime)clearTimeout(searchTime);
			
			//add waiting tip 
			var $offset = _this.offset();
			$('#'+result_con_id).css({left:$offset.left,top:$offset.top+_this.height()+10}).empty().show();
			$('#'+result_con_id).append('<div id="'+result_con_id+'_waiting" style="width:100px;">查询中...</div>');
			
			searchTime = setTimeout(function(){
				var val = $.trim(_this.val());
				//_next.val("");
				if(val == ""){
					$('#'+result_con_id).slideUp(200);
					return;
				}
				var data={value:val};
				//前台赋值字段
				if(idArray){
					for(var j in idArray){
						if (_this.attr('id')!=idArray[j])
							$("#"+idArray[j]).val('');
					}
				}
				//固定参数
				if (param){
					for(var i in param){
						data[i]=param[i];
					}
				}
				//动态参数
				if (dataMapIdName){
					$.each( dataMapIdName, function(name, domId){
						//alert( "name: " + name + ", domId: " + domId );
						data[name] = $('#'+domId).val();
					});
				}
				$.post(url,data,function(result){
					
					//remove waiting tip
					$('#' + result_con_id+'_waiting').remove();
					
					
					//alert(result);
					
					result = eval(result);
					var htmlL = '';
					$.each(result,function(i,n){
						var h = "";
						if (showArray){
							 for(var j=0;j<showArray.length;j++){
								 if(h!=""){
									 h=h+"/";
								 }
								 h=h+"<span>"+n[showArray[j]]+"</span>";
							}
						}	
						var html = "<div style='padding:5px;border-bottom: 1px solid #fff;cursor: pointer;'";
						for(var i in n){
							html=html+" "+i+"='"+n[i]+"'";
						}
						html=html+"'>"+h+"</div>";
						htmlL += html;
					});
					$('#'+result_con_id).html(htmlL);
					$("#quickSearch div")
					.hover(function(){
						$(this).css({'backgroundColor':'#4d7b9d','color':'#fff'});
					},function(){
						$(this).css({'backgroundColor':'#e8e8e8','color':'#000'});
					})
					.click(function(){
						//var name = $(this).attr("sname");
						//var id = $(this).attr("sid");
						//_this.val(name);
						//_next.val(id);
						if(idArray){
							for(var j in idArray){
								if(typeof(j) != 'undefined'){
									$("#"+idArray[j]).val($(this).attr(j));
								}
							}
						}
						$('#'+result_con_id).slideUp(200);
						if (callback){
							callback($(this));
						}
					});
					$('#'+result_con_id).css({'overflow-x':'hidden','overflow-y':'auto'});
				});
			},500);
			$(document).bind('click',function(event){
				var event  = window.event || event;
				var obj = event.srcElement ? event.srcElement : event.target;
				if(obj != _this[0]){
					$('#'+result_con_id).slideUp(200);
					if(idArray){
						var isAllEmpty=true;
						for(var j in idArray){
							if(typeof(j) != 'undefined'){
								if (_this.attr('id')!=idArray[j]&&(''!=$("#"+idArray[j]).val())){
									isAllEmpty=false;
									break;
								}
							}
						}
						if(isAllEmpty){
							_this.val('');
						}
					}
					$(document).unbind('click');
				}
			});
		};
		$(this).bind('keyup', search);
		$(this).bind('paste', search);//粘贴事件
		$(this).bind('keydown',function(event){
			var event  = window.event || event;
			if (event.keyCode==9){
			var obj = event.srcElement ? event.srcElement : event.target;
				if(idArray){
					var isAllEmpty=true;
					for(var j in idArray){
						if (_this.attr('id')!=idArray[j]&&''!=$("#"+idArray[j]).val()){
							isAllEmpty=false;
							break;
						}
					}
					if(isAllEmpty && !canWrite){
						_this.val('');
						$('#'+result_con_id).slideUp(100);
					}
				}
			}
		
		});
		return this;
	};
})(jQuery);