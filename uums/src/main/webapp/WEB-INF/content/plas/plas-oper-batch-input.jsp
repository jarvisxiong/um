<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>查看申请明细</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
	
	<%-- 富文本编辑器 --%>
	<script type="text/javascript" src="${ctx}/js/plugins/xheditor/xheditor-src-zh-cn.js"></script>
</head>
<body style="background-color: white;font-size: 12px;" class="easyui-layout">
	<div region="center" border="false" style="padding:5px 10px;">
	 	<s:form id="inputForm" action="plas-oper-batch!save.action" method="post" theme="simple">
	 	
			<input type="hidden" name="id" value="${plasOperBatchId}" id="plasOperBatchId"/>
			<input type="hidden" name="applyStatusCd" value="${applyStatusCd}" id="applyStatusCd"/>
			
			<s:if test="id == null">
			<table style="width: 100%;"> 
				<col width="80px;"/>
				<col/>
				<tr>
					<td valign="top">处理状态:</td>
					<td style="color:red;">正在填写...</td>
				</tr>			
				<tr>
					<td valign="top">问题描述:</td>
					<td ><input type="text" name="briefTitle" id="briefTitle" style="width:100%;"/></td>
				</tr>			
				<tr>
					<td valign="top">备注:</td>
					<td valign="top">
						<textarea name="applyDesc" id="applyDesc" style="width:100%;height: 300px;">${applyDesc}</textarea>
						<span id="applyDescTip"></span>
					</td>
				</tr>	
				<tr>
					<td></td>
					<td>
						<input type="button" onclick="saveApply()"    value="发起申请"/>
						<%--
						<input type="button" onclick="saveApplyTmp()" value="保存,暂不发起"/>
						 --%>
					</td>
				</tr>
			</table>
			</s:if>
			<s:else>
			<table style="width: 100%;"> 
				<col width="80px;"/>
				<col/>
				<col width="60px;"/>
				<col/>
				<col width="60px;"/>
				<col/>
				<tr>
					<td valign="top">处理状态:</td>
					<td style="color:red;">
						<s:if test="applyStatusCd == 1">待审批
						
							<%--
							<security:authorize ifAnyGranted="A_ADMIN">
								<input type="button" onclick="deleteRecord()" value="删除"/>
							</security:authorize>
							 --%>
							<security:authorize ifNotGranted="A_ADMIN">
								<s:if test="curUiid == applyUserCd">
									<input type="button" onclick="deleteRecord()" value="删除"/>
								</s:if>
							</security:authorize>
							
						</s:if>
						<s:if test="applyStatusCd == 2">已处理</s:if>
						<s:if test="applyStatusCd == 3">不予处理</s:if>
					</td>
					<td valign="top">申请人:</td>
					<td style="text-align: left;"><s:property value="applyUserName"/> </td>
					<td valign="top">申请日期:</td>
					<td style="text-align: left;"><s:date name="applyDate" format="yyyy-MM-dd"/> </td>
				</tr>
				<tr>
					<td valign="top">问题描述:</td>
					<td colspan="5"><s:property value="briefTitle"/> </td>
				</tr>			
				<tr>
					<td valign="top">备注:</td>
					<td valign="top" colspan="5"><textarea name="applyDesc" id="applyDesc" style="width:90%;height: 180px;"><s:property value='applyDesc' /></textarea></td>
				</tr>			 	
				<tr>
					<td valign="top">答复内容如下:</td>
					<td valign="top" colspan="6"><textarea name="replyDesc" id="replyDesc" style="width:90%;height: 180px;"><s:property value='replyDesc' /></textarea></td>
				</tr>
				<security:authorize ifAllGranted="A_ADMIN">
				<tr>
					<td></td>
					<td colspan="5">
						<input type="button" onclick="doDone()" value="标记已处理"/>
						<input type="button" onclick="doSuspend()" value="不予处理"/>
						<div id="span_oper" style="color:red;"></div>
						<div id="span_result"></div>
					</td>
				</tr>	
				</security:authorize>
			</table>
			</s:else>
		</s:form>
	</div> 
	
	<script type="text/javascript">

		var STATUS_CD_TMP = 9;
		var STATUS_CD_APPLY = 1;
		var STATUS_CD_OK = 2;
		var STATUS_CD_NO = 3;
		var STATUS_CD_DEL = 0;
	
		var editor1 = null;
		var editor2 = null;
		$(function(){
			editor1 = showEditor("applyDesc");
			editor2 = showEditor("replyDesc");
		});
		
		function showEditor(domId){
			return $('#'+domId).xheditor(true,{
				emots:{'qq':{'name':'QQ','count':55,'width':25,'height':25,'line':11}}, 
				skin:'o2007blue'
				/*,
				upLinkUrl:_ctx+"/com/upload!upload.action?bizTmpId=${bizTmpId}&fileTypeCd=1&bizTypeCd=11",upLinkExt:"xlsx,docx,doc,xls,pdf,zip,rar,txt",
				upImgUrl :_ctx+"/com/upload!upload.action?bizTmpId=${bizTmpId}&fileTypeCd=2&bizTypeCd=11",upImgExt:"jpg,jpeg,gif,png"
				*/
			});
		}
		//临时保存
		function saveApplyTmp(){
			var t = editor1.getSource();
			if($.trim(t) == ''){
				$('#applyDescTip').html('请填写申请内容!').fadeIn(2000);
				//return;
			}
			$("#applyDesc").val(t);	
			$('#applyStatusCd').val(STATUS_CD_TMP);
			$("#inputForm").ajaxSubmit(function(result) {
				if (result.split(',').length == 2 && result.split(',')[0] == 'success') {
					$('#plasOperBatchId').val(result.split(',')[1]);
					$('#applyStatusCd').val('2');
				}else{
					alert(result);
				}
			});
		}
		
		//发起申请
		function saveApply(){
			$('body').mask('正在提交...');
			var t = editor1.getSource();
			if($.trim(t) == ''){
				$('#applyDescTip').html('请填写申请内容!').fadeIn(2000);
				return;
			}
			$("#applyDesc").val(t);	
			$('#applyStatusCd').val(STATUS_CD_APPLY);
			$("#inputForm").ajaxSubmit(function(result) {
				$('body').unmask();
				if (result.split(',').length == 2 && result.split(',')[0] == 'success') {
					var id = result.split(',')[1];
					$('#plasOperBatchId').val(id);
					//window.location.href = '${ctx}/plas/plas-oper-batch!input.action?id='+id;
					window.location.href = '${ctx}/plas/plas-oper-batch.action';
				}else{
					alert(result);
				}
			});
		}
		
		
		//标记已处理
		function doDone(){
			var id = $('#plasOperBatchId').val();
			var replyDesc = editor2.getSource();

			var url="${ctx}/plas/plas-oper-batch!done.action";
			var id = $('#plasOperBatchId').val();
			var data = {id: id,replyDesc: replyDesc,applyStatusCd: STATUS_CD_OK};
			$.post(url,data,function(result){
				if (result.split(',').length == 2 && result.split(',')[0] == 'success') {
					$('#span_oper').html('已标记处理!').fadeIn(2000);
					$('#applyStatusCd').val(STATUS_CD_OK);
					window.location.reload(true);
				}else{
					$('#span_result').html(result);
				}
			});
		}
		//暂不处理
		function doSuspend(){
			var id = $('#plasOperBatchId').val();
			var replyDesc = editor2.getSource();

			var url="${ctx}/plas/plas-oper-batch!suspend.action";
			var id = $('#plasOperBatchId').val();
			var data = {id: id,replyDesc: replyDesc,applyStatusCd: STATUS_CD_NO};
			$.post(url,data,function(result){
				if (result.split(',').length == 2 && result.split(',')[0] == 'success') {
					$('#span_oper').html('已标记暂不处理!').fadeIn(2000);
					$('#applyStatusCd').val(STATUS_CD_NO);
					window.location.reload(true);
				}else{
					$('#span_result').html(result);
				}
			});
		}
		
		function deleteRecord(){
			var id = $('#plasOperBatchId').val();
			var url="${ctx}/plas/plas-oper-batch!delete.action";
			var id = $('#plasOperBatchId').val();
			var data = {id: id};
			$.post(url,data,function(result){
			 	if('success' == result){
			 		window.location.href = '${ctx}/plas/plas-oper-batch.action';
			 	}else{
			 		alert(result);
			 	}
			});
		}
	</script>
</body>
</html>