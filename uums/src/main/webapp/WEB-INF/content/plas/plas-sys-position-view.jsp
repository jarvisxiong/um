<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="easyui-panel" title='<s:if test="id == null">创建</s:if><s:else>编辑</s:else>系统职位<span id="tip_operate" style="color:red;"></span>' style="overflow-x:hidden;">
<table style="width:100%;">
	<tr>
		<td style="width:280px;" valign="top">
			<s:form id="sysPosForm" action="plas-sys-position!save.action" method="post" theme="simple">
				<input type="hidden" name="id" value="${plasSysPositionId}" id="plasSysPositionId"/>
				<input type="hidden" name="plasOrgId" value="${plasOrg.plasOrgId}"/>
				<input type="hidden" name="plasAcctId" id="plasAcctId" value="${plasAcct.plasAcctId }"/>
						
				<table style="width: 100%;"> 
					<col width="80px;"/>
					<col/>
					<tr>
						<td valign="top">职位机构:</td>
						<td>${sysPosOrgName}</td>
					</tr>
					<tr>
						<td>编制职位CD:</td>
						<!-- 可编辑 -->
						<td>${sysPosCd}</td>
					</tr>
					<tr>
						<td>编制职位名:</td>
						<td>${sysPosName}</td>
					</tr>
					<tr
						<s:if test="plasSysPositionId == null || plasSysPositionId == '' ">
							style="display:none;"
						</s:if>
					>
						<td valign="top">关联账号：</td>
						<td style="text-align: left;">
							${quickSearchField}
						</td>
					</tr>
					<tr>
						<td>显示序号:</td>
						<td>${sequenceNo }</td>
					</tr>
					<tr>
						<td>是否可用:</td>
						<td>
							<s:checkbox id="quickActiveBl" name="activeBl"></s:checkbox> 
						</td>
					</tr>  
					<s:if test="plasSysPositionId != null && plasSysPositionId != ''">
					<tr>
						<td>创建人:</td>
						<td>${creator}</td>
					</tr>
					<tr>
						<td>创建日期:</td>
						<td><s:date name="createdDate" format="yyyy-MM-dd"/></td>
					</tr>  
					<tr>
						<td>更新人:</td>
						<td>${updator}</td>
					</tr>
					<tr>
						<td>更新日期:</td>
						<td><s:date name="updatedDate" format="yyyy-MM-dd"/></td>
					</tr>  
					</s:if>
					<tr>
						<td valign="top">备注:</td>
						<td>
							${remark}
						</td>
					</tr>
				 
				</table> 
			</s:form>
		</td> 
	</tr>
</table>
</div>
<script type="text/javascript">
$(function(){ 
	$('#roleNameList_area').toggle(
		function(){
			$(this).css({"height":"200px"});
		},
		function(){
			$(this).css({"height":"40px"});
		}
	);
	$('#orgNameList_area').toggle(
		function(){
			$(this).css({"height":"200px"});
		},
		function(){
			$(this).css({"height":"40px"});
		}
	);
	$("#wSaveConfirm").window({
		onOpen:function(){
			$('body').mask();
		},
		onClose:function(){
			$('body').unmask();
		}
	});
});

</script>