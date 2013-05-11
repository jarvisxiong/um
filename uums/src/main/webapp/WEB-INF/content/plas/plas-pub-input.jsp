<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>查看申请明细</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/xheditor/xheditor-src-zh-cn.js"></script>
	
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
</head>
<body style="background-color: white;font-size: 12px;" class="easyui-layout">
	<div region="center" border="false" style="padding:5px 20px 5px 5px;+position: relative;overflow-x:hidden;">
		<form id="saveForm" method="post">
			<input type="hidden" name="id" id="plasPubId" value="${plasPubId}" /> 
			<table class="edit_table">
				<tr>
					<td>发布日期</td>
					<td><input class="easyui-datebox" name="pubDate" id="pubDate" style="width:120px;" value='<s:date name="pubDate" format="yyyy-MM-dd"/>'/>(例如: 2011-10-01)</td>
				</tr>
				<tr>
					<td style="width:60px;text-align: right;">标题:</td>
					<td><input style="width:100%;" name="briefTitle" class="easyui-validatebox" required="true" validType="length[0,200]" type="text" value="${briefTitle}"/></td>
				</tr>
				<tr>
					<td style="text-align: right;">发布内容:</td>
					<td><textarea style="width:100%;height:200px;" name="content" id="showdetail_content"><s:property value='content' /></textarea></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"> 
						<security:authorize ifAnyGranted="A_ADMIN">
						<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="savePub();">保存</a>
						<s:if test="plasPubId != null">
							<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="deletePub('${plasPubId}');">删除</a>
						</s:if>
						</security:authorize>
					</td> 
				</tr>
			</table>
		</form>
	</div>
</div>
<script type="text/javascript">
	
	$(function(){
		refreshXEditor();

		$('#pubDate').datebox({
			formatter: function(date){
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
			},   
			parser: function(date){
				return new Date(Date.parse(date.replace(/-/g,"/"))); 
			}
		});  
	}); 

	var xEditor = null;
	//编辑新闻内容
	function refreshXEditor(){
		xEditor = $("#showdetail_content").xheditor(true,{
			emots:{'qq':{'name':'QQ','count':55,'width':25,'height':20,'line':11}},
			skin:'o2007blue'
			//,
			//upLinkUrl:_ctx+"/com/upload!upload.action?bizTmpId=${bizTmpId}&fileTypeCd=1&bizTypeCd=bizNews",upLinkExt:"xlsx,docx,doc,xls,pdf,zip,rar,txt",
			//upImgUrl :_ctx+"/com/upload!upload.action?bizTmpId=${bizTmpId}&fileTypeCd=2&bizTypeCd=bizNews",upImgExt:"jpg,jpeg,gif,png,bmp"
		});
	}
	function savePub(){
		$('#saveForm').form('submit',{
			url:"${ctx}/plas/plas-pub!save.action",
			onSubmit:function(){
				var flag = $('#saveForm').form('validate');
				if(flag){
					$("#showdetail_content").val(xEditor.getSource());	
					$("body").mask('发布中,请稍候...');
				}else{
					$("body").unmask();
				}
				return flag;
			},
			success:function(result){
				$("body").unmask();
				var rtn = result.split(',');
				if( rtn.length == 2 && rtn[0] == 'success'){
					$('#plasPubId').val(rtn[1]);
					$.messager.alert('Info','保存成功');
					//$('#w').window('close');
					//$('#tt').datagrid('reload');
				}else{
					$.messager.alert('Info','保存失败,请重试！\n ' + result);
				}
			}
		});
	}
	function deletePub(id){
		$.messager.confirm('Confirm','Are you sure?',function(r){
			if(r){
				$.post('${ctx}/plas/plas-pub!delete.action',{id:id},function(r){
					if(r.success){
						$.messager.alert('Info','删除成功');
						$('#w').window('close');
						$('#tt').datagrid('reload');
					}else{
						$.messager.alert('Info','删除失败');
					}
				});
			}
		});
	}
</script>
</body>
</html>