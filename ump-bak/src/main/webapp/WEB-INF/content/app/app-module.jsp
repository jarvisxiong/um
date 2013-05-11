<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理区域</title>
	<%@ include file="/common/global.jsp" %>
	<link href="${ctx}/resources/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/resources/js/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery-1.4.4.min.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/ConvertUtil.js" ></script>
</head>

<body class="easyui-layout">
<div region="north" title="搜索"  style="width:100%;height:70px;padding-top:5px;overflow:hidden;" border="false">
   
<s:form id="searchForm" action="app-module" method="post">
    <table>
    <tr>
	    <td>
	      	<s:text name="appAppModule.moduleName"/>:<s:textfield name="filter_LIKES_moduleName" id="filter_LIKES_moduleName" size="18" maxlength="30" />
	  	  	<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="Convert.search('searchForm','tt');">搜索</a> 
		</td>
    </tr>
    </table>
 </s:form>
</div>

<div id="tableDiv" region="center" border="false">
<table id="tt" fit="true"
		title="页面列表" singleSelect="true" rownumbers="true"
		idField="appModuleId" url="${ctx}/app/app-module!list.action">
	
</table>
</div>
<div id="w" class="easyui-window" style="width:400px;height:400px;">
</div>
<script language="javascript">
	function configModuleMenuRel(){
		document.location = "${ctx}/app/app-module-menu-rel.action";
	}
	$(function(){


		init();
	});
	
	function init(){
		$('#tt').datagrid({
			pagination:true,
			pageSize:50,
			pageList:[50,40,30,20,10],
			columns:[[
				{field:'moduleName',title:'模块名称',sortable:true,width:70},
				{field:'moduleTip',title:'模块提示',width:150},
				{field:'pageCd',title:'页面名称Cd',width:240,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'remark',title:'备注',width:100},
				{field:'dispOrderNo',title:'显示序号',  width:60,},
				{field:'action',title:'操作',width:90,align:'center',
					formatter:function(value,row,index){
						var id =row.appModuleId;
						var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+id+'") >编辑</a> ';
						var d = '<a href="#" class="easyui-linkbutton" onclick=deleterow('+index+',"'+id+'") >删除</a>';
						return e+d;
					}
				}
				]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					editrow('');
				}
			},'-'],
			
		});
		$('#w').window({
			title: '模块管理',
			modal:true,
			closed: true,
			collapsible : false,
			minimizable : false,
			cache:false,
			iconCls:"icon-save",
			onClose:function(){
				$('#tt').datagrid('reload');
			}
		});
	}

	function deleterow(index,id){
		$.messager.confirm('确认','你确认要删除该记录吗?',function(r){
			if (r){
				$('#tt').datagrid('deleteRow', index);
				var nextSelect=index>0?index-1:0;
				$('#tt').datagrid('selectRow', nextSelect);
				if(id){
					$.post("${ctx}/app/app-module!delete.action",{id:id} , function(result) {
						$('#tt').datagrid('acceptChanges');
					});
				}else{
					$('#tt').datagrid('acceptChanges');
				}
			}
		});
	}

	function editrow(id){
		var url="${ctx}/app/app-module!input.action?id="+id;
		$('#w').window({href:url});
		$('#w').window("open");
	}
</script>
</body>
</html>
