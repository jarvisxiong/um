<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="easyui-layout" fit="true">	
	<div region="center" style="padding:5px 20px 5px 5px;+position: relative;overflow-x:hidden;" border="false">
		<form id="inputForm" method="post">
			<input type="hidden" name="id" value="${appDictTypeId}" /> 
			<table class="edit_table">
				<col style="width:80px;"/>
				<col />
				<col style="width:80px;"/>
				<col />
				<tr>
				<!--modify by chenyk 2011-4-15 取消 属性 isExists 否则保存时执行更新数据出现数据not defined问题 -->
					<td style="text-align: right;">字典代码:</td>
					<td><input style="width:100%;" name="dictTypeCd" id="dictTypeCd" class="easyui-validatebox" required="true"  type="text" value="${dictTypeCd}"></input></td>
					<td style="text-align: right;">字典名称:</td>
					<td><input style="width:100%;" name="dictTypeName" class="easyui-validatebox" required="true" validType="length[0,50]" type="text" value="${dictTypeName}"></input></td>
				</tr>
				<tr>
					<td style="text-align: right;">显示序号:</td>
					<td><input style="width:100%;" name="dispOrderNo" type="text"  class="easyui-numberbox" max="100000" value="${dispOrderNo}"></input></td>
					<td style="text-align: right;">是否默认:</td>
					<td><s:checkbox name="defaultFlg"></s:checkbox>  </td>
				</tr>
				<tr>
					<td style="text-align: right;" valign="top">备注:</td>
					<td colspan="3"><textarea style="width:100%;" name="remark" class="easyui-validatebox" validType="length[0,200]">${remark}</textarea> </td>
				</tr>
			</table>
		</form>
		<div>
			<table id="tt2" 
				title="字典子表" singleSelect="true" rownumbers="true"
				idField="appDictDataId" url="${ctx}/app/app-dict-type!listSub.action?id=${appDictTypeId}">
			</table>
		</div>
		
	</div>
	<div region="south" border="false" style="height:30px;line-height:30px;">
		<div class="toolbar">
			<s:if test="appDictTypeId != null">
				<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="editrow('${appDictTypeId}');">刷新</a>
			</s:if>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveDict();">保存</a>
			<s:if test="appDictTypeId != null">
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteType();">删除</a>
			</s:if>
			<a id="add" href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="editrow('');">新增</a>
		</div>
	</div>
</div>


<script type="text/javascript">
	var lastIndex2;
	var isExists=false;
	$(function(){
		initAppDictInput();
	});
	function initAppDictInput(){
		$('#tt2').datagrid({
			columns:[[
				{field:'dictCd',title:'代码',sortable:true,width:100,editor:{type:'validatebox',options:{required:'true',validType:'length[1,50]'}}},
				{field:'dictName',title:'名称',sortable:true,width:100,editor:{type:'validatebox',options:{required:'true',validType:'length[1,50]'}}},
				{field:'dispOrderNo',title:'显示序号',align:'right',width:60,editor:{type:'numberbox',options:{max:'10'}}},
				{field:'remark',title:'备注',width:100,editor:{type:'validatebox',options:{validType:'length[0,200]'}},
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'updatedDate',title:'最后更新时间',sortable:true,width:80,
					formatter:function(value,row,index){
						if(value){
							return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
						}else{
							return '';
						}
					}
				},
				{field:'action',title:'操作',width:70,align:'center',
					formatter:function(value,row,index){
						var id =row.appDictDataId;
						var d = '<a href="#" class="easyui-linkbutton" onclick=deleterow2('+index+',"'+id+'") >删除</a>';
						return d;
					}
				}
				]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					var rowSel=$('#tt2').datagrid('getSelected', lastIndex2);
					$('#tt2').datagrid('endEdit', lastIndex2);
					var index = $('#tt2').datagrid('getData').total;
					if(!index)index = $('#tt2').datagrid('getRows').length;
					$('#tt2').datagrid('appendRow',{
						appDictDataId:'',
						dictCd:'',
						dictName:'',
						remark:'',
						sequenceNo:index
					});
					lastIndex2 = $('#tt2').datagrid('getRows').length-1;
					$('#tt2').datagrid('selectRow', lastIndex2);
					$('#tt2').datagrid('beginEdit', lastIndex2);
				}
			}],
			onBeforeLoad:function(){
				$(this).datagrid('rejectChanges');
			},
			onClickRow:function(rowIndex){
				if (lastIndex2 != rowIndex){
					$('#tt2').datagrid('endEdit', lastIndex2);
					$('#tt2').datagrid('beginEdit', rowIndex);
				}
				lastIndex2 = rowIndex;
			}
		});
		$("#ff").validate({
			rules: {
				dictTypeCd: {
					remote: "${ctx}/app/app-dict-type!isTypeExists.action?oldDictTypeCd=" + encodeURIComponent('${dictTypeCd}')
				}
			},
			messages: {
				dictTypeCd: {
					remote: "已经存在！"
				}
			}
		});
	}
	options={
        url:'${ctx}/app/app-dict-type!save.action',
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
	
	function deleterow2(index,id){
		$.messager.confirm('Confirm','Are you sure?',function(r){
			if (r){
				$('#tt2').datagrid('deleteRow', index);
				var nextSelect=index>0?index-1:0;
				$('#tt2').datagrid('selectRow', nextSelect);
				if(id){
					$.post("${ctx}/app/app-dict-type!deleteSub.action",{subId:id} , function(result) {
						$('#tt2').datagrid('acceptChanges');
					});
				}else{
					//$('#tt2').datagrid('acceptChanges');
				}
			}
		});
	}
	function deleteType(){
		$.messager.confirm('Confirm','Are you sure?',function(r){
			if (r){
				$.post("${ctx}/app/app-dict-type!delete.action",{id:'${appDictTypeId}'} , function(result) {
					$('#w').window("close");
				});
			}
		});
	}
</script>