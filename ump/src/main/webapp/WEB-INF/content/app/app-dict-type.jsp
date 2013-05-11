<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>字典管理</title>
	<%@ include file="/common/global.jsp" %>
	<link href="${ctx}/resources/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/resources/js/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/resources/css/email/email.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery-1.4.4.min.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui/jquery.easyui.min.js"  ></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/ConvertUtil.js" ></script>
	<script type="text/javascript" src="${ctx}/js/validate/jquery.validate.js"></script>
	
	
</head>

<body style="margin: 0px;">
   <div class="mailTop" style="height:40px;">
	<s:form id="searchForm" action="app-module" method="post">
	
	    <table>
	    <tr>
	    <td>
	      	<s:text name="appAppDictType.dictTypeName"/>:<s:textfield name="filter_LIKES_dictTypeName" id="filter_LIKES_dictTypeName" size="18" maxlength="30" />
	      	<s:text name="appAppDictType.dictTypeCd"/>:<s:textfield name="filter_LIKES_dictTypeCd" id="filter_LIKES_dictTypeCd" size="18" maxlength="30" />
	  	  	<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="Convert.search('searchForm','tt');">搜索</a> 
	  	  	<a href="#" id="btn" iconCls="icon-add" class="easyui-linkbutton" onclick="editrow('');">新增</a> 
	      	
		</td>
	
	    </tr>
	    </table>
	 </s:form>
	</div>
	<div id="mailBottom" style="height:475px;">
		<table id="tt" fit="true"
				 singleSelect="true" rownumbers="true"
				idField="appDictTypeId" url="${ctx}/app/app-dict-type!list.action">
			
		</table>
	</div>
<div id="w" class="easyui-window" style="top:10px;width:650px;height:450px;">
</div>

<script type="text/javascript">

	var lastIndex;
	var inputUrl;
	$(function(){
		init();

	});
	function init(){
		$('#tt').datagrid({
			pagination:true,
			pageSize:50,
			pageList:[50,40,30,20,10],
			columns:[[
				{field:'dictTypeCd',title:'字典类型CD',sortable:true,width:150,height:30},
				{field:'dictTypeName',title:'字典类型名称',width:150},
				{field:'dispOrderNo',title:'显示序号',width:50,align:'center'	},
				{field:'defaultFlg',title:'是否初始化默认',width:100,align:'center',
					formatter:function(value,row,index){
						if(value=='1')
							return '是';
						else 
							return '否';
					}
				},
				{field:'creator',title:'创建时间',  width:80,},
				{field:'createdDate',title:'更新时间',width:70,align:'center',},
				{field:'action',title:'操作',width:120,align:'center',
					formatter:function(value,row,index){
						var tmp ='<a href="#" class="easyui-linkbuton l-btn" ><span class="l-btn-left" style="color:#444444">';
						var tmpEnd='</span>';	
						var id =row.appDictTypeId;
						var e = '<button onclick=editrow("'+id+'") >编辑</button>';
						var d = '<button onclick=deleterow('+index+',"'+id+'") >删除</button>';
						return e+d;
					}
				}
			]],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
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
					$.post("${ctx}/app/app-dict-type!delete.action",{id:id} , function(result) {
						$('#tt').datagrid('acceptChanges');
					});
				}else{
					$('#tt').datagrid('acceptChanges');
				}
			}
		});
	}
	
	function editrow(id){
		var url="${ctx}/app/app-dict-type!input.action?id="+id;
		$('#w').window({href:url});
		$('#w').window("open");
	}
	function clearForm(){
		$('#searchForm').form('clear');
	}
	
	var options;
	function saveDict(){
		$('#inputForm').form('submit', options);
		$('#w').window("close");// modify by chenyk 保存成功后需要应该关闭窗口而不是打开
		//$('#ff').submit();
	}
</script>
</body>
</html>