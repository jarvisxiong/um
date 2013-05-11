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
loadCss(_ctx+'/css/common/select.css');
loadCss(_ctx+'/css/common/treePanel.css');
loadCss(_ctx+'/js/plugins/jqModal/jqModal.css');
loadJs(_ctx+'/js/common/common.js');
loadJs(_ctx+'/js/plugins/jqModal/jqModal.js');
loadJs(_ctx+'/js/common/treePanel.js');

//----------------------------自动引入依赖的css 和 js文件----------------------------

//------------------------------以下为人员选择插件代码----------------------------------------


/**
 * 人员/机构选择框
 * shixy 
 * 2011-5-6 17:54
 */
(function($){
	//人员选择框
	$.extend({
		ouSelect : function(dom,opts){
			var _this = dom;
			var _orgTree;
			//人员容器
			var _userMap = {};
			//机构
			var selectedOrgCd;
			var selectedOrgName;
			
			//人员查询keyup时间控制器
			var outTimer;
			
			var defaultOpt = {
					type:'user',//选择框类型  user：人员选择框    org：机构选择框
					title: '请选择人员',	
					width:'500',
					height:'400',
					zIndex: 1000,//窗口层叠值
					opacity: 30,//遮罩透明度
					ctx:_ctx,
					waitingHtml:'<p>Please wait...</p>',
					muti:true,//人员多选,默认为true  设置为false后就只能单选
					maxSelectedUser:0,
					orgMuti:false,//机构多选(勾选)
					nameField:'userName',//实体属性,回显 文本对应的字段  默认为userName
					cdField:'uiid',//实体属性,回显cd对应的字段   默认为uiid
					sysPosField:'sysPosCd',//实体属性
					//nameEId:'',回调函数 文本填充 domId  如：用户姓名 对应的输入框
					//cdEId:'',cd填充domId  如：用户cd 对应的输入框(一般为隐藏域),如果传入这两个参数，插件会默认将name填充到当前控件，密码填入到下一个input控件中
					sysPosEId:'',//系统职位对应的DOM ID
					parentOrgCd : '',//显示指定机构下的机构树
					showAllOrg : true,//是否显示所有的机构   默认
					showCenterOrg:false,//是否显示用户所管辖中心/部门机构树
					showProjectOrg:false,//是否显示项目机构树
					showGroupFlg : false,//是否显示自定义组
					showRank : false
					//callback:function(map){},回调函数  如传入 插件将不会自动填充值到nameEId cdEId中，需用户自行处理。参数map为人员uiid-人员信息 json
			};
			
			var orgDefault = {};
			if(opts && opts.type == 'org'){
				orgDefault = {
						title: '请选择机构',	
						width:250,
						height:300
				};
			};
			
			//初始参数配置
			var option = $.extend(defaultOpt,orgDefault,opts);
			
			//先清空页面上可能已经渲染过的弹出窗,避免重复
			var id = $(_this).attr('id');
			$('#jqmDialog_'+id).remove();
			
			//初始化弹出窗口容器
			var tPopBody = new Array();
			tPopBody.push('<div class="jqmDialog" id="jqmDialog_'+id+'">');
			tPopBody.push('	<div class="jqmHeader">');
			tPopBody.push('		<div class="jqmClose">X</div>');
			tPopBody.push('		<div class="jqmTitle">'+option.title+'</div>');
			tPopBody.push('	</div>');
			
			//项目或中心
			if(option.showCenterOrg || option.showProjectOrg || option.type == "org"){
				tPopBody.push(' <div style="height:28px;line-height:28px;padding-left: 10px;"> ');
				tPopBody.push('     <img style="vertical-align: middle;cursor:pointer;" src="'+_ctx+'/images/webim/search_zoom.jpg" onclick="$(this).prev().trigger(\'keyup\')" title="查询下一个"/>');///PowerDesk
				tPopBody.push('		<input class="jqmSearchInput" type="text" style="width:150px;" /> ');
				tPopBody.push(' </div>');
			}
			
			tPopBody.push('	<div class="jqmBody">'+option.waitingHtml+'</div>');
			tPopBody.push('	<div align=center>');
			tPopBody.push('		<button class="jqmConfirmBtn">确定</button><button class="jqmCancelBtn">取消</button>');
			tPopBody.push('	</div>');
			tPopBody.push('</div>'); 
			var jqmDlg = $(tPopBody.join(''));
			$('div.jqmBody',jqmDlg).height(option.height+'px');
			jqmDlg.css({'width':option.width+'px','marginLeft':(0-option.width/2)+'px'});
			$('body').append(jqmDlg);
			initDlgEvents();
			var t = $('div.jqmBody',jqmDlg);
			 
			//初始化机构树，并绑定点击和选择事件
			function initOrgTree(){
				var param = {
						parentOrgCd : option.parentOrgCd,
						orgMuti : option.orgMuti
				};
				if(option.showAllOrg){
					param.orgTreeType = 1;
				}
				if(option.showProjectOrg){
					param.orgTreeType = 2;
				}
				if(option.showCenterOrg){
					param.orgTreeType = 3;
				}
				$.post(option.ctx+'/com/select!getOrgTree.action',param, function(result) {
					//add by huangbj 解决jquery1.3,1.4兼容性问题.
					var tmpJson;
					try{
						tmpJson = val('('+result+')');
					}catch(e){
						try{
							tmpJson = (result);
						}catch(ee){
						}
					}
					
					_orgTree = new TreePanel({
						renderTo:'deptDiv',
						'root' : tmpJson,
						'ctx':option.ctx
					});
					_orgTree.on(function(node){
						//展开/收起
						if(!node.isExpand){
							//node.collapse();
						//}else{
							node.expand();
						}
						
						getUsersByOrg(node);
					});
					_orgTree.addListener('check',function(node){
						getUsersByOrg(node,true);
						
					});
					_orgTree.render();
					if(option.type=='org'){
						initOrgData();
					}
				});
			};
			
			// 根据部门代码加载该部门所有人员
			function getUsersByOrg(node,tag){
				
				selectedOrgCd = node.attributes.entityCd; 
				selectedOrgName = node.attributes.text;
				if(option.type == 'org')return;
				
				var orgCds = node.getAllChildren(node,'entityCd');
				var checked = node.checked;
				$('#memberDiv').css('cursor','wait');
				$('#userDisplay').empty().addClass('waiting');
				$.post(option.ctx+'/com/select!getUsersbyOrg.action',{orgCds:orgCds.join(','),showSysPos:option.sysPosEId?true:false},function(result){
					$('#deptDisplay').text(selectedOrgName);
					result = eval(result);
					if(tag){
						addAndBindUser(result,true,checked);
					}else{
						addAndBindUser(result,true);
					}
					$('#memberDiv').css('cursor','');
					$('#userDisplay').removeClass('waiting');
				});
			};
			
			//初始化部分元素的绑定事件
			function initEvents(){
				//初始显示人员toggle事件绑定
				$('#userDisplay li').each(function() {
					var obj = {};
					obj.uiid = $(this).attr('id');
					obj.userName = $(this).attr('name');
					obj.orgName = $(this).attr('orgName');
					obj.sysPosName = $(this).attr('sysPosName');
					obj.sysPosCd = $(this).attr('sysPosCd');
					addAndBindUser(obj, false);
				});
				//人员查询
				$('#searchUser').keyup(function(){
					var value = $.trim($(this).val());
					if(outTimer)clearTimeout(outTimer);
					outTimer= setTimeout(function(){
						if(value=='')return;
						$('#mailUserRight').addClass('waiting');
						$('#userDisplay').html("<img align='absMiddle' src='"+option.ctx+"/images/loading.gif'>");
						var param  = {value:value,showSysPos:option.sysPosEId?true:false};
						if(option.showCenterOrg){
							param.orgTreeType = 3;
						}
						$.post(option.ctx+'/com/select!getUsersByFilter.action',param,function(result){
							$('#userDisplay').empty();
							$('#deptDisplay').text('查询结果');
							result = eval(result);
							addAndBindUser(result,true);
							$('#mailUserRight').removeClass('waiting');
							delete result;
						});
					},300);
				});
				//全部添加
				$('#addAll').click(function(){addAll();});
				//全部删除
				$('#delAll').click(function(){delAll();});
				//显示已选择人员
				$('#allSelected').click(function(){
					$("#userDisplay").empty();
					$("#deptDisplay").text("已选人员");
					for ( var k in _userMap) {
						addAndBindUser(_userMap[k], true);
					}
				});
				//自定义组选择人员
				$('#groupDiv input').click(function(){showMember('group',this,true);});
				$('#groupDiv span').click(function(){showMember('group',this);});
				//按照职级选择人员
				$('#rankDiv input').click(function(){showMember('rank',this,true);});
				$('#rankDiv span').click(function(){showMember('rank',this);});
			};
			
			function addAll() {
				$("#userDisplay li.userUnSelected").each(function(){
					$(this).click();
				});
			}
			function delAll() {
				$("#userDisplay li.userSelected").each(function(){
					$(this).trigger('click');
				});
			}
			
			//初始化-- 显示已选人员
			function initUserData(){
				var map = $(_this).data('userMap');
				if(typeof(map) === 'object'){
					_userMap = $.extend(true,{},map);
					var emptyFlg=true;
					for(var k in _userMap){
						emptyFlg = false;
						break;
					}
					if(!emptyFlg){
						$('#allSelected').trigger('click');
					}
				}
				if(typeof(map) === 'undefined'){
					var name = option.nameEId?$('#'+option.nameEId).val():$(_this).val();
					var cd = option.cdEId?$('#'+option.cdEId).val():$(_this).next().val();
					var sysPosCd = option.sysPosEId?$('#'+option.sysPosEId).val():'';
					var nameArray = $.trim(name).split(';');
					var cdArray = $.trim(cd).split(';');
					var sysPosCdArray = $.trim(sysPosCd).split(';');
					_userMap = {};
					var k=0;
					$.each(nameArray,function(i,n){
						if(n!=''){
							var o = {
									userName:n,
									uiid:cdArray[i],
									sysPosCd:option.sysPosEId?sysPosCdArray[i]:''
							};
							_userMap[cdArray[i]] = o;
							k++;
						}
					});
					if(k > 0){
						$('#allSelected').trigger('click');
					}
				}
			}
			
			//初始化 --定位到已选的机构
			function initOrgData(){
				var cd = option.cdEId?$('#'+option.cdEId).val():$(_this).next().val();
				if(cd == '')return;
				if(option.orgMuti){
					$.each(cd.split(','),function(i,n){
						var searchNode = _orgTree.getNodeByAttr('entityCd', n);
						if(searchNode.isLeaf()){
							searchNode.onCheck();
						}
					});
				}else{
					var searchNode = _orgTree.getNodeByAttr('entityCd', cd);
					var nodes = searchNode.getPathNodes();
					for(var i= 0; i < nodes.length; i++){
						nodes[i].expand();
					}
					_orgTree.setFocusNode(searchNode);
					var nodeDom = searchNode['html-element']['text'];
					var jqBody = $('div.jqmBody',jqmDlg);
					var toh = jqBody.height();
					var top = jqBody[0].scrollTop;
					var noh = $(nodeDom).offset().top;
					jqBody.animate({ scrollTop : top+noh-toh }, "normal");
				}
			}
			
			function showMember(type,dom,checkFlg){
				var $parent = $(dom).parent();
				var dataId = $parent.attr("dataId");
				var title = $parent.text();
				$("#userDisplay").empty();
				$("#deptDisplay").text(title);
				var url = '';
				if(type=='group'){
					url = option.ctx+"/com/select!loadGroup.action";
				}else if(type='rank'){
					url = option.ctx+"/com/select!loadRank.action";
				}
				$.post(url,{dataId:dataId},function(result){
					result = eval(result);
					if(checkFlg){
						var checked = $(dom).attr("checked");
						if(checked){
							addAndBindUser(result,true,'1');
						}else{
							addAndBindUser(result,true,'0');
						} 
					}else{
						addAndBindUser(result,true);
					}
				});
			};

			// 搜索框
			var _curVal;
			var _curNode;
			var _searchTree;
			
			//初始化窗口的确定和取消按钮事件
			function initDlgEvents(){

				//搜索机构
				if(option.showCenterOrg || option.showProjectOrg || option.type == "org"){
 					$('.jqmSearchInput').keyup(function(){
						var jqDom = $(this);
						var t = $.trim(jqDom.val());
						if(_searchTree)clearTimeout(_searchTree);
						_searchTree = setTimeout(function(){
							if( t == _curVal){
								//NONE
							}else{
								_curVal = t;
								_curNode = null;
							}
							_curNode = _orgTree.searchNode(_curVal, _curNode);
							if(_curNode){
								var nodes = _curNode.getPathNodes();
								for(var i= 0; i < nodes.length; i++){
									nodes[i].expand();
								}
								_orgTree.setFocusNode(_curNode);
								var nodeDom = _curNode['html-element']['text'];
								var toh = jqDom.parent().next().height();
								var top = jqDom.parent().next()[0].scrollTop;
								var noh = $(nodeDom).offset().top;
								$('.jqmBody').animate({ scrollTop : top+noh-toh }, "normal");
							}
						}, 300);
					}); 
				}
				
				//确定   *********************填充控件*****************************
				$('.jqmConfirmBtn',jqmDlg).click(function(){
					if(option.type == 'user'){
						userCallback();
					}else{
						orgCallback();
					}
				});
				//关闭
				$('.jqmCancelBtn',jqmDlg).click(function(){
					_userMap = {};
					$(jqmDlg).jqmHide();
				});
			}
			
			//人员选择框确定事件
			function userCallback(){
				var check = true;
				if(option.maxSelectedUser>0){
					var i = 0;
					for(var k in _userMap){
						i++;
						if(i>option.maxSelectedUser){
							alert("对不起，您权限不足，您最多选择"+option.maxSelectedUser+"个人");
							check = false;
							break;
						}
					};
				}
				if(!check)return;
				
				var data = $.extend(true,{},_userMap);
				$(_this).data('userMap',data);
				var names = [];
				var cds = [];
				var postions = [];
				var nameStr = '';
				var cdStr = '';
				var postionStr = '';
				for(var k in _userMap){
					var obj = _userMap[k];
					names.push(obj[option.nameField]);
					cds.push(obj[option.cdField]);
					postions.push(obj[option.sysPosField]);
				}
				if(names.length>0){
					nameStr = names.join(';');
					cdStr = cds.join(';');
					postionStr = postions.join(';');
					if(option.muti){
						nameStr+=';';
						cdStr+=';';
						postionStr+=';';
					}
				}
				if(option.nameEId){
					$('#'+option.nameEId).val(nameStr);
					$('#'+option.cdEId).val(cdStr);
				}else{
					$(_this).val(nameStr).next().val(cdStr);
				}
				if(option.sysPosEId){
					$('#'+option.sysPosEId).val(postionStr);
				}
				if($.isFunction(option.callback)){
					option.callback(_userMap);
				}
				$(jqmDlg).jqmHide();
			}
			//机构选择框确定事件
			function orgCallback(){
				var nameStr = '';
				var cdStr = '';
				if(option.orgMuti){
					cdStr = _orgTree.getChecked("entityCd");
					nameStr = _orgTree.getChecked("text");
				}else{
					nameStr = selectedOrgName;
					cdStr = selectedOrgCd;
				}
				if(option.nameEId){
					$('#'+option.nameEId).val(nameStr);
					$('#'+option.cdEId).val(cdStr);
				}else{
					$(_this).val(nameStr).next().val(cdStr);
				}
				if($.isFunction(option.callback)){
					option.callback(nameStr,cdStr);
				}
				$(jqmDlg).jqmHide();
			}
			//根据人员信息添加并绑定人员事件
			//2011-5-26 性能优化.解决办法：每0.2秒加载100条记录.缓解浏览器假死状况
			function addAndBindUser(objects,addFlag,checked){
				if($.isArray(objects)){
					//记录长度   
					var s_start = 0;   
					//统计处理条数   
					var s_count = 1;   
					//每次处理的条数   
					var s_length = 100; 
					//数据的长度
					var len = objects.length;
					
					for(var i=s_start; i< len; i++){
			            if(i < len){   
			            	//逻辑处理
			            	if(objects[i])bind(objects[i], addFlag, checked);
			            }else{
			                return;   
			            }   
			        }
				}else{
					bind(objects, addFlag);
				}
				
				function bind(obj,flg,cFlg){
					if(obj.uiid == '')return;
					if(flg){
						//内容
						var desc = new Array();
						desc.push(obj.userName);
						if(obj.orgName){
							desc.push('|');
							desc.push(obj.orgName);
						}
						if(obj.sysPosCd){
							desc.push('|');
							desc.push(obj.sysPosName);
						}
						var dd = desc.join('');
						//行
						var strList = new Array();
						strList.push('<li class="userUnSelected" id="');
						strList.push(obj.uiid);
						strList.push('">');
						strList.push('<div class="userStatus_online" title="');
						strList.push(dd);
						strList.push('">');
						strList.push(dd);
						strList.push('</div></li>');
						$('#userDisplay').append(strList.join(''));
					}
					var objUser = $('#' + obj.uiid);
					objUser.click(function() {
						var className = $(this).attr('className');
						if(className == 'userSelected'){
							$(this).attr('className','userUnSelected');
							delete _userMap[obj.uiid];
						}else if(className == 'userUnSelected'){
							if(!option.muti){//单选
								$('#userDisplay li.userSelected').attr('className','userUnSelected');
								_userMap = {};
							}
							$(this).attr('className','userSelected');
							_userMap[obj.uiid] = obj;
						}
					});
					var userObj = _userMap[obj.uiid];
					if(userObj){
						objUser.attr('className','userSelected');
					}
					if(cFlg == '0'){
						if(userObj){
							objUser.trigger('click');
						}
					}else if(cFlg == '1'){
						if(!userObj){
							objUser.trigger('click');
						}
					}
				}
			}
			
			//点击时弹出人员选择框
			$(_this).click(function(){
				var url = option.ctx+'/com/select.action?pageType='
					+option.type+'&showGroupFlg='
					+option.showGroupFlg+'&showSysPos='
					+(option.sysPosEId?'true':'false')
					+'&showRank='
					+option.showRank;
				$(jqmDlg).jqm({
					trigger: false,
					ajax: url,
					ajaxText:option.waitingHtml,
					target: t,
					modal: true,
					zIndex:option.zIndex,
					overlay:option.opacity,
					onHide: function(h) {
						$('div.jqmBody',jqmDlg).empty();
						//h.w.slideUp(200);
						//添加动画效果
						var wE = h.w;
						var width = wE.width();
						var height = wE.height();
						wE.animate({
							height:20,
							width:20
						},300,function(){
							wE.hide();
							wE.css({'width':width,'height':height});
						});
						h.o.remove();
					},
					onLoad : function(r){
						initOrgTree();
						if(option.type == 'user'){
							initEvents();
							initUserData();
							$('#treeUserContainer').height(option.height-50);
						}else{
							$('div.jqmBody',jqmDlg).css('overflow','auto');
						}
					}
				}).jqDrag('.jqmTitle');
				$(jqmDlg).jqmShow();
			});
		}
		
	});
	
	
	$.fn.ouSelect = function(opts){
		return this.each(function(){
			$.ouSelect(this,opts||{});
		});
	};
	$.fn.orgSelect = function(opts){
		return this.each(function(){
			$.ouSelect(this,$.extend({type:'org'},opts||{}));
		});
	};
	$.fn.userSelect = function(opts){
		return this.each(function(){
			$.ouSelect(this,$.extend({type:'user'},opts||{}));
		});
	};
	
})(jQuery);