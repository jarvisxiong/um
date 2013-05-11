<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html" />
	<title>网批选项</title>

	<%@ include file="/common/global.jsp" %>
	<link href="${ctx}/resources/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/resources/js/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/resources/css/email/email.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery-1.4.4.min.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui/jquery.easyui.min.js"  ></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/ConvertUtil.js" ></script>
	<script type="text/javascript" src="${ctx}/js/validate/jquery.validate.js"></script>
	
</head>
<body>
   <div class="mailTop" style="height:60px;">
	<s:form id="searchForm" action="res-option" method="post">
     	选项的名称:<input name="optionName" id="optionName" value="${optionName}" />
     	<%-- 
     	选项的属性名:<input name="optionIdName" id="optionIdName" value="${optionIdName}" />
     	选项的类型:<input name="optionType" id="optionType" value="${optionType}" />
     	--%>
 	  	<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="Convert.search('searchForm','tt');">搜索</a> 
 	  	<%-- 
        <a href="#" id="btn" iconCls="icon-add" class="easyui-linkbutton" onclick="doback();">确定</a> 
        --%>
	 </s:form>
	</div>
	<div id="mailBottom" style="height:380px;background-color: #ffffff;">
		<table id="tt" fit="true"
				 singleSelect="true" rownumbers="true"
				idField="resOptionId" url="${ctx}/res/res-option!list.action">
			
		</table>
	</div>
<div id="w" class="easyui-window" style="top:10px;width:630px;height:450px;">
</div>
<input type="hidden" id="name"/>
<input type="hidden" id="id"/>
</body>
</html>
<script type="text/javascript">

	var lastIndex;
	var inputUrl;
	$(function(){
		init();

	});
	function init(){
		$('#tt').datagrid({
			pagination:true,
			pageSize:10,
			pageList:[30,20,10],
			columns:[[
				{field:'optionName',title:'选项的名称',sortable:true,width:150,height:30},
				{field:'optionIdName',title:'选项的属性名',width:150},
				{field:'optionType',title:'选择的类型',width:100,align:'center'},
				{field:'resOptionId',title:'选择的类型',width:100,align:'center',hidden:true}
				
			]],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			},
			onClickRow:function(rowIndex, rowData){
				//$("#name").val(rowData.optionName);
				//$("#id").val(rowData.resOptionId);

				var info ={};
				info.name = rowData.optionName ;
				info.id = rowData.resOptionId;
				window.parent.ymPrompt.doHandler(info, true);
                
				}
		});
		$('#w').window({
			title: '网批选项管理',
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
		$.messager.confirm('提示','你确认要删除该记录吗?',function(r){
			if (r){
				$('#tt').datagrid('deleteRow', index);
				var nextSelect=index>0?index-1:0;
				$('#tt').datagrid('selectRow', nextSelect);
				if(id){
					$.post("${ctx}/res/res-option!delete.action",{id:id} , function(result) {
						$('#tt').datagrid('acceptChanges');
					});
				}else{
					$('#tt').datagrid('acceptChanges');
				}
			}
		});
	}
	
	function editrow(id){
		var url="${ctx}/res/res-option!input.action?id="+id;
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
	}

	function doback(){
		if($("#name").val()==''){
             alert("请选择一条记录！");
             return;
			}
		var info ={};
		info.name = $("#name").val() ;
		info.id = $("#id").val();
		window.parent.ymPrompt.doHandler(info, true);
		}
</script>