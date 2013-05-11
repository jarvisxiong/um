/***
 * easyui.validType扩展
 */
$.extend($.fn.validatebox.defaults.rules, {
	isNumber: {
        validator: function(value){
        	if(value=="0" || parseInt(value)) {
        		return true;
        	}
            return false;
        },
        message: '请输入数字！'
    },
    isBoolean: {
        validator: function(value){
        	if(value=="true" || value=="false") {
        		return true;
        	}
            return false;
        },
        message: '请输入 \"true\" 或者 \"false\"！'
    }
});

var lastIndex;

$(function(){
	init();
	clearForm();
});

function init(){
	
	initOptionValueDateGrid();
	
	// 设置选项编辑弹出框
	$('#w').window({
		title: '网批选项管理',
		modal:true,
		closed: true,
		collapsible : false,
		minimizable : false,
		cache:false,
		iconCls:"icon-save",
		onClose:function(){
			//$('#tt').datagrid('reload');
		}
	});
	
	// 设置表单提交的options
	options={
	        url: _ctx + '/res/res-option!save.action',
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
	        		$('#tt2').datagrid('endEdit', lastIndex);
			        Convert.setChildren2Form("inputForm","tt2");
	        	}
	        	if(flag) {
	        		$('#w').window("close");// modify by chenyk 保存成功后需要应该关闭窗口而不是打开
	        	}
	        	return flag;
	        },
	        success:function(data){
	        	if(data=='execute_save') {
		        	//$.messager.alert('Info','保存成功');
	        	} else if(data=='execute_update') {
		        	//$.messager.alert('Info','修改成功');
	        	} else {
	        		$.messager.alert('Info','保存失败');
	        	}
				$('#tt').datagrid('reload');
	        }
		};
}

/***
 * 初始化绑定选项表DataGrid
 */
function initOptionValueDateGrid() {
	// 绑定选项的数据到DataGrid
	$('#tt').datagrid({
		pagination:true,
		pageSize:30,
		pageList:[30,20,10],
		columns:[[
			{field:'optionName',title:'选项的名称',width:200,height:30,sortable:true},
			{field:'optionIdName',title:'选项的属性名',width:150},
			{field:'optionType',title:'选择的类型',width:120,align:'center',
				formatter:function(value,row,index){
					var type = "";
					if(value=="1"){
						type = "单行文本框";
					} else if(value=="2") {
						type = "多行文本框";
					} else if(value=="3") {
						type = "HTML编辑器";
					} else if(value=="4") {
						type = "单选";
					} else if(value=="5") {
						type = "多选";
					}
					return type;
				}},
			{field:'action',title:'操作',width:120,align:'center',
				formatter:function(value,row,index){
					var id =row.resOptionId;
					var edit = '<button onclick=editrow("'+id+'") >编辑</button>';
					var del = '<button onclick=deleterow('+index+',"'+id+'") >删除</button>';
					return edit+del;
				}
			}
		]],
		onLoadSuccess:function(){
			$('a.easyui-linkbutton').linkbutton();
		}
	});
}

/***
 * 初始化选项内容表单DataGrid
 */
function initOptionDataGrid() {
	// 选项内容表单数据绑定到DataGrid
	$('#tt2').datagrid({
		columns:[[
			{field:'optionName',title:'选项内容名称',width:200,editor:{type:'validatebox',options:{required:'true'}}},
			{field:'optitonValue',title:'选项内容值',width:150,editor:{type:'validatebox',options:{required:'true'}}},
			{field:'ifDefault',title:'是否默认',width:60,align:'center',editor:{type:'validatebox',options:{max:'1',validType:'isBoolean'}}},
			{field:'sequenceNo',title:'排序',width:60,align:'center',editor:{type:'validatebox',options:{required:'true',validType:'isNumber'}}},
			{field:'action',title:'操作',width:70,align:'center',
				formatter:function(value,row,index){
					var id = row.resOptionValueId;
					var d = '<a href="#" class="easyui-linkbutton" onclick=deleterow2('+index+',"'+id+'") >删除</a>';
					return d;
				}
			}
			]],
		toolbar:[{
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				$('#tt2').datagrid('endEdit', lastIndex);
				var index = $('#tt2').datagrid('getData').total;
				if(!index)index = $('#tt2').datagrid('getRows').length;
				$('#tt2').datagrid('appendRow',{
					optionName:'',
					optitonValue:'',
					ifDefault:'false',
					sequenceNo:parseInt(index)+1
				});
				lastIndex = $('#tt2').datagrid('getRows').length-1;
				$('#tt2').datagrid('selectRow', lastIndex);
				$('#tt2').datagrid('beginEdit', lastIndex);
			}
		}],
		onClickRow:function(rowIndex){
			$('#tt2').datagrid('endEdit', lastIndex);
			$('#tt2').datagrid('beginEdit', rowIndex);
			lastIndex = rowIndex;
		}
	});
}

//设置表单提交的options
var options;
function saveOption(){
	$('#inputForm').form('submit', options);
}

/***
 * 删除某条选项记录
 * @param index
 * @param id
 */
function deleterow(index,id){
	$.messager.confirm('提示','你确认要删除该 "选项" 记录吗?',function(r){
		if (r){
			$('#tt').datagrid('deleteRow', index);
			var nextSelect=index>0?index-1:0;
			$('#tt').datagrid('selectRow', nextSelect);
			if(id){
				$.post(_ctx + "/res/res-option!delete.action",{id:id} , function(result) {
					if(deleteExceptionMsg(result, 'tt') == false) {
						return;
					}
					$('#tt').datagrid('acceptChanges');
					$('#tt').datagrid('reload');
				});
			}else{
				$('#tt').datagrid('acceptChanges');
			}
		}
	});
}

/***
 * 删除单条选项内容记录
 * @param index
 * @param id
 */
function deleterow2(index,id){
	$.messager.confirm('提示','确定删除此 "选项内容" 记录吗？',function(r){
		if (r){
			$('#tt2').datagrid('deleteRow', index);
			var nextSelect=index>0?index-1:0;
			$('#tt2').datagrid('selectRow', nextSelect);
			if(id && id != "undefined"){
				$.post(_ctx + "/res/res-option!deleteSub.action",{subId:id} , function(result) {
					if(deleteExceptionMsg(result, 'tt2') == false) {
						return;
					}
					if(result == "execute_deleteSub") {
			        	$.messager.alert('Info','删除成功！');
						$('#tt2').datagrid('acceptChanges');
					} else {
			        	$.messager.alert('Info','删除失败！');
					}
					$('#tt2').datagrid('reload');
				});
			}
		}
	});
}

/***
 * 删除选项内容记录列表
 */
function deleteOptValList(){
	$.messager.confirm('提示','确定删除此 "选项内容" 列表？',function(r){
		if (r){
			$.post(_ctx + "/res/res-option!deleteSubBatch.action",{id:$("#resOptionId").val()} , function(result) {
				if(deleteExceptionMsg(result, 'tt2') == false) {
					return;
				}
				if(result == "execute_deleteSubBatch") {
		        	$.messager.alert('Info','删除成功！');
					$('#tt2').datagrid('acceptChanges');
				} else {
		        	$.messager.alert('Info','删除失败！');
				}
				$('#tt2').datagrid('reload');
				// $('#w').window("close");
			});
		}
	});
}

/***
 * 删除异常提示
 * @param msg
 * @param tableId
 * @returns {Boolean}
 */
function deleteExceptionMsg(msg, tableId) {
	if(msg == "be_used_error") {
    	$.messager.alert('Info','此条记录可能被其他其它记录使用！\n如果确认要删除，请先删除使用到此记录的其它记录！');
		$('#' + tableId).datagrid('reload');
		return false;
	}
	if(msg == "no_row_error") {
    	$.messager.alert('Info','此条记录可能已经被删除！');
		$('#' + tableId).datagrid('reload');
		return false;
	}
	return true;
}

/***
 * 编辑选项
 * @param id
 */
function editrow(id){
	var url = _ctx + "/res/res-option!input.action?id="+id;
	$('#w').window({href:url});
	$('#w').window("open");
}

/***
 * 清除表格内数值
 */
function clearForm(){
	$('#searchForm').form('clear');
}
