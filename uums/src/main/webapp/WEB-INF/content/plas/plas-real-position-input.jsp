<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<div class="easyui-layout" fit="true">	
	<div region="center" style="padding:5px 20px 5px 5px;+position: relative;overflow-x:hidden;" border="false">
		<form id="realPositionForm" method="post">
			<table class="edit_table">
				<tr>
					<td style="width:80px;">职位代码:</td>
					<td><input style="width:100%;" name="realPosCd" id="realPosCd" class="easyui-validatebox"  validType="length[0,50]"required="true"  type="text" value="${realPosCd}" onblur="realPosCdExist();"></input></td>
				</tr>
				<tr>
					<td>职位名称:</td>
					<td><input style="width:100%;" name="realPosName" id="realPosName" class="easyui-validatebox" required="true" validType="length[0,50]" type="text" value="${realPosName}" onblur="realPosNameExist();"></input></td>
				</tr>
				<tr>
					<td>显示名称:</td>
					<td><input style="width:100%;" name="realPosNameShow" id="realPosNameShow" class="easyui-validatebox" type="text" validType="length[0,50]" required="true"  class="easyui-numberbox" max="100000" value="${sequenceNo}" onblur="realPosNameShowExist();"></input></td>
				</tr>
				<tr>
					<td>显示序号:</td>
					<td><input style="width:100%;" name="sequenceNo" type="text"  class="easyui-numberbox" max="100000" value="${sequenceNo}"></input></td>
				</tr>
				<tr>
					<td>备注:</td>
					<td><textarea style="width:100%;" name="remark" class="easyui-validatebox" validType="length[0,200]">${remark}</textarea> </td>
				</tr>
			</table>
		</form>
	</div>
	<div region="south" border="false" style="height:60px;line-height:30px;">
		<div class="toolbar">
			<s:if test="plasRealPositionId != null">
				<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="editrow('${plasRealPositionId}');">刷新</a>
			</s:if>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveEdit();">保存</a>
			<s:if test="plasRealPositionId != null">
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteOneRow('${plasRealPositionId}');">删除</a>
			</s:if>
			<a id="add" href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="editrow('');">新增</a>
			
		</div>
	</div>
</div>
<script type="text/javascript">
    var flg = true;
	//realPosCd唯一性检测
	function realPosCdExist(){
		var value=$("#realPosCd").val();
		$.post("${ctx}/plas/plas-real-position!isTypeExists.action?realPosCd=" + encodeURIComponent(value) , function(result) {
			var obj = eval(result);
			if(obj.success){
				flg = true;
       		}else {
       			$.messager.alert('提示','已存在,不可用');
       			$("#realPosCd").val("");//输入重复时置空文本框
       			flg = false;
       		}
		});
		return flg;
	}
	//realPosName 唯一性检测
	function realPosNameExist(){
		var value=$("#realPosName").val();
		$.post("${ctx}/plas/plas-real-position!isTypeExists.action?realPosName=" + encodeURIComponent(value) , function(result) {
			var obj = eval(result);
			if(obj.success){//jquery ajax 唯一性检测后返回校验成功与否
				flg = true;
       		}else {
       			$.messager.alert('提示','已存在,不可用');
       			$("#realPosName").val("");
       			flg = false;
       		}
		});
		return flg;
	}
	//realPosNameShow 唯一性检测
	function realPosNameShowExist(){
		var value=$("#realPosNameShow").val();
		$.post("${ctx}/plas/plas-real-position!isTypeExists.action?realPosNameShow=" + encodeURIComponent(value) , function(result) {
			var obj = eval(result);
			if(obj.success){
				flg = true;
       		}else {
       			$.messager.alert('提示',"已存在,不可用");
       			$("#realPosNameShow").val("");
       			flg = false;
       		}
		});
		return flg;
	}
	//保存动作执行
	function saveEdit(){
		// modify by chenyk 2011-4-24 防止编辑页面单击保存跳过校验
		realPosCdExist()
		return flg;
        realPosNameExist()
        return flg;
		realPosNameShowExist()
		return flg;
		$('#realPositionForm').form('submit',{
			url:'${ctx}/plas/plas-real-position!save.action',
			onSubmit: function(){//modify by chen-yk 增加提交表单时增加非空监测，因为表单录入错误时直接置空
				var flag = $('#realPositionForm').form('validate');
				return flag;
				/*
				var realPosCd=$("#realPosCd").val();
				var realPosName=$("#realPosName").val();
				var realPosNameShow=$("#realPosNameShow").val();
				
			    if(null!=realPosCd&&""!=realPosCd&&null!=realPosName&&""!=realPosName&&null!=realPosNameShow&&""!=realPosNameShow){
			        var flag = $('#realPositionForm').form('validate');
			        return flag;
			    }else{
			    	$.messager.alert('提示','请输入职位数据');
		    	    return false;
			    }
			    */
			},
			success:function(result){
				if(result=='success'){
					$.messager.alert('提示','保存成功!');
					$('#w').window("close");
				}else{
					$.messager.alert('提示',result);
				}	
			}
		});	
	}
</script>