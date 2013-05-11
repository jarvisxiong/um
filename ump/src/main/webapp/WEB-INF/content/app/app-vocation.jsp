<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>假期管理</title>
<%@ include file="/common/global.jsp"%>

<link href="${ctx}/resources/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/js/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/ConvertUtil.js"></script>


</head>
<body class="easyui-layout">
<div region="north" title="搜索" icon="icon-search" style="height: 70px; padding-top: 5px; overflow: hidden;" border="false">
<form id="searchForm" method="post">
<table>
	<tr>
		<td style="width: 60px;">是否上班:</td>
		<td>
		<s:select list="@com.hhz.ump.util.DictMapUtil@getMapEnableFlgNum()" listKey="key" listValue="value" style='width: 100px;' name="filter_EQS_ifOnDuty"></s:select>	
		</td>
		<td style="width: 20px;">&nbsp;&nbsp;年:</td>
		<td style='text-align: left;'><input type="text" id="filter_EQL_inYear" style='width: 100px;' name="filter_EQS_inYear" onclick="WdatePicker();"></input></td>


		<td><a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="Convert.search('searchForm', 'tt')">搜索</a></td>


	</tr>
</table>
</form>
</div>
<div region="center" border="false">
<table id="tt" fit="true" title="假期管理" iconCls="icon-edit" singleSelect="true" idField="appVocationId" url="${ctx}/app/app-vocation!list.action">
</table>
</div>
<script type="text/javascript">
	$(function() {

		var lastIndex;
		$('#tt').datagrid( {
			pagination : true,
			pageSize : 50,
			beforePageText : '第',//页数文本框前显示的汉字 
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
			loadMsg : '数据加载中......',
			pageList : [ 50, 40, 30, 20, 10 ],
			columns : [ [

			{
				field : 'vocationDay',
				title : '日   期',
				editor : {
					type : 'datetext'
				},
				sortable : true,
				align : 'center',
				width : 100,
				formatter : function(value, row, index) {
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="' + value + '">' + value + '</div>';
				}
			}, {
				field : 'ifOnDuty',
				title : '是否上班',
				editor : {
					type : 'checkbox',
					options : {
						on : '1',
						off : '0'
					}
				},
				sortable : true,
				align : 'center',
				width : 100,
				formatter : function(value, row, index) {
					if (value == '1')
						return '是';
					else
						return '<label style="color:green">否</label>';
				}
			}, {
				field : 'inYear',
				title : '年',
				editor : {
					type : 'text'
				},
				sortable : true,
				align : 'center',
				width : 60
			}, {
				field : 'inMonth',
				title : '月',
				editor : {
					type : 'text'
				},
				sortable : true,
				align : 'center',
				width : 60
			}, {
				field : 'inDay',
				title : '日',
				editor : {
					type : 'text'
				},
				sortable : true,
				align : 'center',
				width : 60
			},

			{
				field : 'remark',
				title : '备注',
				editor : {
					type : 'text'
				},
				width : 330,
				formatter : function(value, row, index) {
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="' + value + '">' + value + '</div>';
				}
			},

			{
				field : 'creator',
				title : '创建人',
				sortable : true,
				width : 80,
				formatter : function(value, row, index) {
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="' + value + '">' + value + '</div>';
				}
			}, {
				field : 'createdDate',
				title : '创建时间',
				sortable : true,
				width : 80,
				formatter : function(value, row, index) {
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="' + value + '">' + value + '</div>';
				}
			}, {
				field : 'updator',
				title : '更新人',
				sortable : true,
				width : 80,
				formatter : function(value, row, index) {
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="' + value + '">' + value + '</div>';
				}
			}, {
				field : 'updatedDate',
				title : '更新时间',
				sortable : true,
				width : 80,
				formatter : function(value, row, index) {
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="' + value + '">' + value + '</div>';
				}
			} ] ],
			toolbar : [ {
				text : '新增',
				iconCls : 'icon-add',
				handler : function() {
					$('#tt').datagrid('endEdit', lastIndex);
					$('#tt').datagrid('appendRow', {
						vocationDay : '',
						ifOnDuty : '0',
						inYear : '',
						inMonth : '',//默认值
						inDay : '',//默认值
						remark : ''
					});
					lastIndex = $('#tt').datagrid('getRows').length - 1;

					$('#tt').datagrid('beginEdit', lastIndex);
					$("#tt").datagrid("selectRow", lastIndex);

				/*	$("input.datagrid-editable-input").unbind("click");

					$("input.datagrid-editable-input").bind("click", function() {
						var _tr = $(this).parent().parent().parent().parent().parent().parent().parent();

						var _index = $(_tr).attr("datagrid-row-index");
						$('#tt').datagrid('endEdit', lastIndex);
						$("#tt").datagrid("selectRow", _index);
						$('#tt').datagrid('beginEdit', _index);

						lastIndex = _index;
					});
					*/

					initEvent();

					$("input[offval=0]").bind("click", function() {

						var _tr = $(this).parent().parent().parent().parent().parent().parent().parent();
						var _index = $(_tr).attr("datagrid-row-index");
						$('#tt').datagrid('endEdit', lastIndex);
						$("#tt").datagrid("selectRow", _index);
						$('#tt').datagrid('beginEdit', _index);
						lastIndex = _index;

					});
				}

			}, '-', {
				text : '保存',
				iconCls : 'icon-save',
				handler : function() {
					saveEdit();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					var row = $('#tt').datagrid('getSelected');
					if (row) {
						$.messager.confirm('提示', '确认要删除该记录吗?', function(t) {
							if (t) {
								//TODO:如果该记录被引用,是否强制不允许删除?
								$.post("${ctx}/app/app-vocation!delete.action", {
									id : row.appVocationId
								}, function(result) {
									var rObj = eval("("+result+")");
									if (rObj.success) {
										$('#tt').datagrid('reload');
									} else {
										alert(rObj.failure);
									}
								});
							}
						});
					}
				}
			}, '-' ],
			onClickRow : function(rowIndex) {
				if (lastIndex != rowIndex) {
					$('#tt').datagrid('endEdit', lastIndex);
					$('#tt').datagrid('beginEdit', rowIndex);
				}
				$("#tt").datagrid("selectRow", rowIndex);
				lastIndex = rowIndex;
				initEvent();

			}
		});
	});

function	splitDate(ele){


	var _td = $(ele).parent().parent().parent().parent().parent().parent();
	var _index = $(ele).attr("index");

	var _val = $(ele).val();
	var _arr = _val.split('-');
	for ( var i = 0; i < _arr.length; i++) {
		switch (i) {
		case 0:

			if ($.trim("" + _arr[i]) == "") {
				break;
			}
			$("input[fld=inYear" + _index + "]").val(parseFloat(_arr[i]));
			break;
		case 1:
			$("input[fld=inMonth" + _index + "]").val(parseFloat(_arr[i]));
			break;
		case 2:
			$("input[fld=inDay" + _index + "]").val(parseFloat(_arr[i]));

			break;
		default:
			break;
		}

	}
}
	function initEvent() {

		var _disabeldFiled = "inYear,inMonth,inDay";
		var _index = 0;
		$.each($("input.datagrid-editable-input"), function(i, inputCtrl) {

			var _td = $(inputCtrl).parent().parent().parent().parent().parent().parent();
			var _tr = $(_td).parent();
			_index = $(_tr).attr("datagrid-row-index");

			var _field = $(_td).attr("field");

			if (_field == "vocationDay") {
				$(inputCtrl).attr("index", _index);
			} else if (_disabeldFiled.indexOf(_field) > -1) {

				$(inputCtrl).attr("disabled", true);
				$(inputCtrl).attr("fld", _field + _index);
			}
		});
	}
	function saveEdit() {
		var row = $('#tt').datagrid('getSelected');
		if (row) {
			var index = $('#tt').datagrid('getRowIndex', row);
			$('#tt').datagrid('endEdit', index);
		}
		$.post("${ctx}/app/app-vocation!saveBatch.action", Convert.ToSaveParam("tt"), function(result) {
			var rObj = eval("("+result+")");
			if (rObj.success) {
				$('#tt').datagrid('reload');
			} else {
				alert(rObj.failure);
			}
		});
	}
	function StringBuffer() {
		this._strs = new Array;
	}
	StringBuffer.prototype.append = function(str) {
		this._strs.push(str);
	};
	StringBuffer.prototype.toString = function() {
		return this._strs.join("");
	};
</script>
</body>
</html>