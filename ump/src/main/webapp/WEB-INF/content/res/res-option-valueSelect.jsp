<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html" />
	<title>网批选项</title>
<link href="${ctx}/resources/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/resources/js/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/resources/css/email/email.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery-1.4.4.min.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui/jquery.easyui.min.js"  ></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/ConvertUtil.js" ></script>
	<script type="text/javascript" src="${ctx}/js/validate/jquery.validate.js"></script>
</head>
<body>

<div region="center" border="false">
		
		<div>
			<table id="tt2" 
				 singleSelect="true" rownumbers="true"
				idField="resOptionId" url="${ctx}/res/res-option!listSub.action?id=${resOptionId}">
			</table>
		</div>
		
	</div>

<script type="text/javascript">
	var lastIndex2;
	var isExists=false;
	$(function(){
		initResOptionValueInput();
	});
	
	function initResOptionValueInput(){
		$('#tt2').datagrid({
			columns:[[
				{field:'optionName',title:'选项内容名称',sortable:true,width:100,editor:{type:'validatebox',options:{required:'true',validType:'length[1,50]'}}},
				{field:'optitonValue',title:'选项内容值',sortable:true,width:100,editor:{type:'validatebox',options:{required:'true',validType:'length[1,50]'}}},
				{field:'ifDefault',title:'是否默认',align:'right',width:60,editor:{type:'checkbox',options:{max:'10'}},
					formatter:function(value, row, index){
						var cbx = '';
						if(value == "1") {
							cbx += '是';
						} else {
							cbx += '否';
						}
						return cbx;
					}
				},
				{field:'sequenceNo',title:'排序',width:100,editor:{type:'validatebox',options:{validType:'length[0,200]'}}},
				{field:'ifOtherText',title:'是否多选',width:60,editor:{type:'checkbox',options:{max:'10'}},
					formatter:function(value,row,index){
						var cbx = ' ';
						if(value == "1") {
							cbx += '是';
						} else {
							cbx += '否';
						}
						return cbx;
					}
				}
				]],
			onBeforeLoad:function(){
				$(this).datagrid('rejectChanges');
			},
			onClickRow:function(rowIndex,rowData){
				var info ={};
				info.name = rowData.optionName ;
				info.id = rowData.resOptionValueId;
				window.parent.ymPrompt.doHandler(info, true);
			}
		});
// 		$("#ff").validate({
// 			rules: {
// 				dictTypeCd: {
// 					remote: "${ctx}/res/app-dict-type!isTypeExists.action?oldDictTypeCd=" + encodeURIComponent('${dictTypeCd}')
// 				}
// 			},
// 			messages: {
// 				dictTypeCd: {
// 					remote: "已经存在！"
// 				}
// 			}
// 		});
	}
	options={
        url:'${ctx}/res/res-option!save.action',
        onSubmit: function(){
        	var flag=$('#inputForm').form('validate');
        	if (flag){
        		var changeRows=$('#tt2').datagrid('getChanges','inserted','updated');
	        	for(var i=0;i<changeRows.length;i++){
	        		var rowIndex=$('#tt2').datagrid('getRowIndex',changeRows[i]);
	        		flag=$('#tt2').datagrid('validateRow',rowIndex);
	        		if (!flag){
	        			$('#tt2').datagrid('selectRow',rowIndex);
	        			$('#tt2').datagrid('beginEdit', rowIndex);
						break;
	        		}else{
	        			$('#tt2').datagrid('endEdit', rowIndex);
	        		}
	        	}
        	}
        	if(flag){
        		$('#tt2').datagrid('endEdit', lastIndex2);
		        Convert.setChildren2Form("inputForm","tt2");
        	}
        	return flag;
        },
        success:function(data){
        	if(data!='failure'){
	        	$.messager.alert('Info','保存成功');
	        	
        	}else{
        		$.messager.alert('Info','保存失败');
        	}
        }
	};
	
	// 删除单条选项内容
	function deleterow2(index,id){
		$.messager.confirm('提示','确定删除此项选项内容？',function(r){
			if (r){
				$('#tt2').datagrid('deleteRow', index);
				var nextSelect=index>0?index-1:0;
				$('#tt2').datagrid('selectRow', nextSelect);
				if(id){
					$.post("${ctx}/res/res-option!deleteSub.action",{subId:id} , function(result) {
						$('#tt2').datagrid('acceptChanges');
					});
				}
			}
		});
	}
	
	// 删除选项内容列表
	function deleteOptValList(){
		$.messager.confirm('提示','确定删除此选项内容列表？',function(r){
			if (r){
				$.post("${ctx}/res/res-option!delete.action",{id:'${resOptionId}'} , function(result) {
					$('#w').window("close");
				});
			}
		});
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


	function btnCancel(){
		   window.parent.ymPrompt.doHandler('', true);
		   }
</script>
</body>
</html>