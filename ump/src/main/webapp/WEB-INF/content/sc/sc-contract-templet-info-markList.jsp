<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%--
<c:out value="${fn:length(resApproveNodes)}"></c:out>
--%>
 
<s:if test="#attr.markList.size()>0">
<table class="mainTable">
	<tr><td style="float:left; margin-left:0px; margin-top:0px; padding:0">
		<table class="content_table" style="margin-left:0px; margin-top:0px; padding:0;width:100%">
			<tr>
				<th scope="col" title='序号' style="width:30px;"><div class="ellipsisDiv_full" align="center">序号</div></th>
				<th scope="col" title='原始文本'><div class="ellipsisDiv_full" align="center">原始文本</div></th>
				<th scope="col" title='批注文本'><div class="ellipsisDiv_full" align="center">批注文本</div></th>
				<th scope="col" title='最终确认文本'><div class="ellipsisDiv_full" align="center">最终确认文本</div></th>
				
				<th scope="col" title='操作' style="width:140px;" display_oper_flag="true"><div class="ellipsisDiv_full" align="center">操作</div></th>
				
			</tr>
		
			<c:forEach items="${markList}" var="mark">
			<tr class="mainTr" >
			<%-- onclick="getContHtml('${contractTempletInfoId}','${contractTempletHisId}');" --%>
				<td onclick="gotoCont('${contractTempletInfoId}','${contractTempletHisId}','${mark.contractMarkId}');" align="center" class="pd-chill-tip" title="点击查看详情">${mark.contractMarkNo}</td>
	    		<td onclick="gotoCont('${contractTempletInfoId}','${contractTempletHisId}','${mark.contractMarkId}');" class="pd-chill-tip" title="点击查看详情">
	    			<div ctrType="comment" forid='${mark.contractMarkId}'>${mark.comment}</div>
	    		</td>
				<td onclick="gotoCont('${contractTempletInfoId}','${contractTempletHisId}','${mark.contractMarkId}');" class="pd-chill-tip" title="点击查看详情">
					<div ctrType="markHis" forid='${mark.contractMarkId}'>${mark.hisMarkContent}</div>
				</td>
				<td onclick="gotoCont('${contractTempletInfoId}','${contractTempletHisId}','${mark.contractMarkId}');" class="pd-chill-tip"  title="点击查看详情">
					<div ctrType="mark" forid='${mark.contractMarkId}' 
						style="display:<s:if test="#attr.mark.contractMarkContent == #attr.mark.hisMarkContent">none;</s:if><s:else>block;</s:else>">
						${mark.contractMarkContent}
					</div>
				</td>
				
				<td display_oper_flag="true">
					<input type="button" class="btn_blue" value="修改" forid='${mark.contractMarkId}' btn_type="edit_mark"
						style="display:<s:if test='#attr.mark.contractMarkContent == #attr.mark.hisMarkContent'>inline;</s:if><s:else>none;</s:else>"
						onclick="doEditMarkContent('${mark.contractMarkId}')"/>
						
					<input type="button" class="btn_blue" value="取消修改" forid='${mark.contractMarkId}' btn_type="cancel_edit_mark"
						style="display:<s:if test='#attr.mark.contractMarkContent == #attr.mark.hisMarkContent'>none;</s:if><s:else>inline;</s:else>"
						onclick="cancelEditMarkContent('${mark.contractMarkId}')"/>
					
					<input type="button" id="reject_mark" class="btn_red" value="删除" onclick="rejectMarkContent('${mark.contractMarkId}')"/>
				</td>
			</tr>
			</c:forEach>
		</table>
	</td></tr>
</table>

<textarea name="contractHtml" class='content' id="contractHtml" style="display:none;">${contractHtml}</textarea>
<input type="hidden" id="scContractId" name="scContractId" value="${contractTempletInfoId}" />
<input type="hidden" id="hisContId" name="hisContId" value="${contractTempletHisId}" />

<script type="text/javascript">
$.getScript('${ctx}/kindeditor/kindeditor-min.js', function() {
	editor = KindEditor.create('textarea[name="contractHtml"]', {
		width : "95%",
		height : 500 + "px",
		allowFileManager : true,
		items : [],
		afterCreate : function() {
		$("div.ke-container").hide();
		}
	});
});
if($("#display_oper_flag").val()!='true'){
	$("[display_oper_flag='true']").hide();
}
</script>
</s:if>
<s:else>
<script type="text/javascript">
$("#divNoMark").show();
</script>

</s:else>

