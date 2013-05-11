/*
 * 功能说明: 模糊搜索下拉框
 * 参数说明:
 * @param url 后台方法路径
 * @param showArray 显示后台返回的json对象的属性集合
 * @param idArray 选中控件后,将后台json对象属性赋值至对应字段 
 * @param param json对象,用于额外传至后台参数
 * @param callback
 * @param paramDomId jsp的ID变量,根据指定元素id，取得对应的值当做参数传递到后台
 * @param canWrite 是否能够输入其他值，不需要全部从下拉中选择
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
 */
(function($){
	$.fn.quickSearch = function(url,showArray,idArray,param,callback,paramDomId,canWrite,delCallback){
		var result_con_id = "quickSearch";
		var _this = $(this);
		if(_this.attr('readOnly')==true){
			return this;
		}
		//var _next = nextId?$('#'+nextId):$(this).next();
		if($('#'+result_con_id).length == 0){
			$('body').append('<div id="'+result_con_id+'" style="padding:3px 5px;position: absolute;z-index:9999999; display: none;border: 1px solid gray;background-color: #e8e8e8"></div>');
		}
		// 搜索数据
		var searchTime;
		var search = function(){
			if(searchTime)clearTimeout(searchTime);
			searchTime = setTimeout(function(){
				var val = $.trim(_this.val());
				//_next.val("");
				if(idArray)
					for(var j in idArray){
						if (_this.attr('id')!=idArray[j])
							$("#"+idArray[j]).val('');
					}
				if(val == ""){
					$('#'+result_con_id).slideUp(200);
					return;
				}
				var data={value:val};
				if (param){
					for(var i in param){
						data[i]=param[i];
					}
				}
				if(paramDomId){
					for(var i in paramDomId){
						var paramValue=$("#"+paramDomId[i]).val();
						data[i]=paramValue;
					}
				}
				$.post(url,data,function(result){
					//alert(result);
					var $offset = _this.offset();
					if("/PowerDesk/cont/cont-ledger!quickSearch.action"==url){
						$('#'+result_con_id).css({left:$offset.left-400,top:$offset.top+_this.height()}).empty().show();
					}else{
						$('#'+result_con_id).css({left:$offset.left-120,top:$offset.top+_this.height()}).empty().show();
					}
					result = eval(result);
					
					//表格
					var tmpTableArr = new Array();
					tmpTableArr.push('<table>');
					
					//逐行拼写
					$.each(result,function(i,n){
						var tmpTrArr = new Array();
						tmpTrArr.push("<tr ");
						//tr class
						if(i%2 ==0){
							tmpTrArr.push(" class='odd' ");
						}else{
							tmpTrArr.push(" class='even' ");
						}
						
						//tr属性
						for(var i in n){
							tmpTrArr.push(" ");
							tmpTrArr.push(i);
							tmpTrArr.push("='");
							tmpTrArr.push(n[i]);
							tmpTrArr.push("'");
						}
						tmpTrArr.push("'>");

						if (showArray){
							 for(var j=0;j<showArray.length;j++){
								 /*
								 if(h!=""){
									 h=h+"|";
								 }*/
								 tmpTrArr.push("<td><div title='"+n[showArray[j]]+"'>");
								 tmpTrArr.push(n[showArray[j]]);
								 tmpTrArr.push("</div></td>");
							}
						}

						tmpTrArr.push("</tr>");
						tmpTableArr.push(tmpTrArr.join(''));
					});
					
					tmpTableArr.push('</table>');
					$('#'+result_con_id).html(tmpTableArr.join(''));
					$("#quickSearch table tr")
					/*
					.hover(
						function(){
							$(this).css({'backgroundColor':'#4d7b9d','color':'#fff'});
						},function(){
							$(this).css({'backgroundColor':'#e8e8e8','color':'#000'});
						}
					)*/
					.click(function(){
						
						//var name = $(this).attr("sname");
						//var id = $(this).attr("sid");
						//_this.val(name);
						//_next.val(id);
						
						var headFlg = $(this).attr('headFlg');
						if( '1' == headFlg){//1-标题
							return;
						}
						
						if(idArray){
							for(var j in idArray){
								$("#"+idArray[j]).val($(this).attr(j));
							}
						}
						
						$('#'+result_con_id).slideUp(200);
						if (callback){
							callback($(this));
						}
					});
				});
			},300);
			var clear=function(event){
				var event  = window.event || event;
				var obj = event.srcElement ? event.srcElement : event.target;
				if(obj != _this[0]){
					$('#'+result_con_id).slideUp(100);
					if(idArray){
						var isAllEmpty=true;
						for(var j in idArray){
							if (_this.attr('id')!=idArray[j]&&''!=$("#"+idArray[j]).val()&&'undefined'!=$("#"+idArray[j]).val()){
								isAllEmpty=false;
								break;
							}
						}
						if(isAllEmpty && !canWrite){
							_this.val('');
							if (delCallback){
								delCallback($(this));
							}
						}
					}
					$(document).unbind('click');
				}
			};
			$(document).bind('click',clear);
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
						if (delCallback){
							delCallback($(this));
						}
					}
				}
			}
		
		});
		return this;
	};
})(jQuery);
//isEmpty = function (str) {
//	return (typeof (str) === "undefined" || str === null || (str.length === 0));
//};
//isNotEmpty = function (str) {
//	return (!String.isEmpty(str));
//};