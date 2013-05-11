var _menus = {"menus":[
						{"menuid":"2","icon":"icon-sys","menuname":"控件使用",
							"menus":[{"menuid":"21","menuname":"折叠菜单","icon":"icon-nav","url":"js/demo/accordion.html"},
									{"menuid":"22","menuname":"日历","icon":"icon-nav","url":"js/demo/calendar.html"},
									{"menuid":"23","menuname":"日期选择","icon":"icon-nav","url":"js/demo/datebox.html"},
									{"menuid":"24","menuname":"下拉框","icon":"icon-nav","url":"js/demo/combobox.html"},
									{"menuid":"25","menuname":"下拉表格","icon":"icon-nav","url":"js/demo/combogrid.html"},
									{"menuid":"26","menuname":"下拉树","icon":"icon-nav","url":"js/demo/combotree.html"},
									{"menuid":"27","menuname":"数据表格","icon":"icon-nav","url":"js/demo/datagrid.html"},
									{"menuid":"28","menuname":"自定义表格","icon":"icon-nav","url":"js/demo/datagrid4.html"},
									{"menuid":"29","menuname":"表头支持右键","icon":"icon-nav","url":"js/demo/datagrid3.html"},
									{"menuid":"210","menuname":"可编辑表格","icon":"icon-nav","url":"js/demo/datagrid2.html"},
									{"menuid":"211","menuname":"对话框","icon":"icon-nav","url":"js/demo/dialog.html"},
									{"menuid":"212","menuname":"表单","icon":"icon-nav","url":"js/demo/form.html"},
									{"menuid":"213","menuname":"布局示例1","icon":"icon-nav","url":"js/demo/layout1.html"},
									{"menuid":"214","menuname":"布局示例2","icon":"icon-nav","url":"js/demo/layout2.html"},
									{"menuid":"215","menuname":"按钮","icon":"icon-nav","url":"js/demo/linkbutton.html"},
									{"menuid":"216","menuname":"菜单","icon":"icon-nav","url":"js/demo/menu.html"},
									{"menuid":"217","menuname":"菜单按钮","icon":"icon-nav","url":"js/demo/menubutton.html"},
									{"menuid":"218","menuname":"消息框","icon":"icon-nav","url":"js/demo/messager.html"},
									{"menuid":"219","menuname":"数字输入框","icon":"icon-nav","url":"js/demo/numberbox.html"},
									{"menuid":"220","menuname":"数字微调","icon":"icon-nav","url":"js/demo/numberspinner.html"},
									{"menuid":"221","menuname":"分页控件","icon":"icon-nav","url":"js/demo/pagination.html"},
									{"menuid":"222","menuname":"面板示例1","icon":"icon-nav","url":"js/demo/panel.html"},
									{"menuid":"223","menuname":"面板示例2","icon":"icon-nav","url":"js/demo/panel-demo/panel.html"},
									{"menuid":"224","menuname":"标签页","icon":"icon-nav","url":"js/demo/tabs.html"},
									{"menuid":"225","menuname":"树","icon":"icon-nav","url":"js/demo/tree.html"},
									{"menuid":"226","menuname":"树形表格","icon":"icon-nav","url":"js/demo/treegrid.html"},
									{"menuid":"227","menuname":"购物车示例","icon":"icon-nav","url":"js/demo/shopping/shopping-cart.html"}
								]
						},{"menuid":"1","icon":"icon-sys","menuname":"综合示例",
							"menus":[
									{"menuid":"12","menuname":"网易","icon":"icon-add","url":"http://www.163.com"},
									{"menuid":"13","menuname":"待添加","icon":"icon-users"}
								]
						}
				]};	

$(function(){
	InitLeftMenu();
	bindTabEvent();
	bindTabMenuEvent();
	extentValidate();
	initDialog();
});

//初始化左侧
function InitLeftMenu() {
	$("#nav").accordion({animate:true});

    $.each(_menus.menus, function(i, n) {
		var menulist ='';
		menulist +='<ul>';
        $.each(n.menus, function(j, o) {
			menulist += '<li><div><a ref="'+o.menuid+'" href="#" rel="' + o.url + '" ><span class="icon '+o.icon+'" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';
        });
		menulist += '</ul>';

		$('#nav').accordion('add', {
            title: n.menuname,
            content: menulist,
            iconCls: 'icon ' + n.icon
        });

    });

	$('.easyui-accordion li a').click(function(){
		var tabTitle = $(this).children('.nav').text();

		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		var icon = getIcon(menuid,icon);

		addTab(tabTitle,url,icon);
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function(){
		$(this).parent().addClass("hover");
	},function(){
		$(this).parent().removeClass("hover");
	});

	//选中第一个
	var panels = $('#nav').accordion('panels');
	var t = panels[0].panel('options').title;
    $('#nav').accordion('select', t);
}
//获取左侧导航的图标
function getIcon(menuid){
	var icon = 'icon ';
	$.each(_menus.menus, function(i, n) {
		 $.each(n.menus, function(j, o) {
		 	if(o.menuid==menuid){
				icon += o.icon;
			}
		 });
	});

	return icon;
}

//添加一个新的tab,全部以iframe的形式来加载
function addTab(subtitle,url,icon){
	if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			icon:icon
		});
	}else{
		$('#tabs').tabs('select',subtitle);
		$('#mm-tabupdate').click();//刷新tab页
	}
	bindTabEvent();
}

function createFrame(url){
	var s;
	if(url&&url!='undefined'&&$.trim(url) != ""){
		s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	}else{
		s="<div align=center style='margin-top:30px;color:red;font-size:16px;font-weight:bold;'>网页未实现</div>"
	}
	
	return s;
}

function bindTabEvent()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	});
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}
//绑定tab右键菜单事件
function bindTabMenuEvent()
{
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update',{
			tab:currTab,
			options:{
				content:createFrame(url)
			}
		});
	});
	//关闭当前
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		if($('#tabs').tabs('getTab',currtab_title).panel('options').closable){
			$('#tabs').tabs('close',currtab_title);
		}
	});
	//全部关闭
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			if ($(this).parent().next().is('.tabs-close')) {
				var t = $(n).text();
				$('#tabs').tabs('close', t);
			}
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('.tabs-inner span').each(function(i, n) {
			if ($(this).parent().next().is('.tabs-close')) {
				var t = $(n).text();
				if (t != currtab_title)
					$('#tabs').tabs('close', t);
			}
		});
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i, n) {
			if ($('a.tabs-close', $(n)).length > 0) {
				var t = $('a:eq(0) span', $(n)).text();
				$('#tabs').tabs('close', t);
			}
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 1) {
			alert('前边没有啦~~');
			return false;
		}
		prevall.each(function(i, n) {
			if ($('a.tabs-close', $(n)).length > 0) {
				var t = $('a:eq(0) span', $(n)).text();
				$('#tabs').tabs('close', t);
			}
		});
		return false;
	});
}
/**    
 * 刷新tab
 * @cfg 
 *example: {tabTitle:'tabTitle',url:'refreshUrl'}
 *如果tabTitle为空，则默认刷新当前选中的tab
 *如果url为空，则默认以原来的url进行reload
 */
function refreshTab(cfg){
	var refresh_tab = cfg.tabTitle?$('#tabs').tabs('getTab',cfg.tabTitle):$('#tabs').tabs('getSelected');
	if(refresh_tab && refresh_tab.find('iframe').length > 0){
	var _refresh_ifram = refresh_tab.find('iframe')[0];
	var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;
	//_refresh_ifram.src = refresh_url;
	_refresh_ifram.contentWindow.location.href=refresh_url;
	}
}

function extentValidate(){
	$.extend($.fn.validatebox.defaults.rules, {
	    equalTo: {
	        validator: function(value, param){
	        	 return value == $('#'+param[0]).val();
	        },
	        message: '两次输入的密码不一致'
	    }
	});
}

function initDialog(){
	$('#changePwdDiv').dialog({
		buttons:[{
			text:'确定',
			iconCls:'icon-ok',
			handler:function(){
				submitPwdForm();
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$('#changePwdDiv').dialog('close');
			}
		}]
	}).dialog('close');
	
}
function logout(){
	$.messager.confirm('提示','您确认要退出系统吗？',function(t){
		if(t){
			window.location.href=_ctx+'/j_spring_security_logout';
		}
	});
}
function changePwd(){
	$('#oldPwd,#newPwd,#newPwdRepeat').val('');
	$('#changePwdDiv').dialog('open');
}
function submitPwdForm(){
	if(!$('#changePwdForm').form('validate'))return;
	var param = $('#changePwdForm').serialize();
	var url = _ctx+'/admin/app/app-user!changePwd.action';
	$.post(url,param,function(data){
		if('error1' == data){
			$('#oldPwdWrong').show();
		}else if('error' == data){
			$.messager.alert('提示','保存失败');
		}else if('success' == data){
			$.messager.show({
				title:'提示',
				msg:'保存成功,请妥善保管您的密码！',
				timeout:5000,
				showType:'slide'
			});
			$('#changePwdDiv').dialog('close');
		}
	});
}
