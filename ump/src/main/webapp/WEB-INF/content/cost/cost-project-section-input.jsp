<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<form action="${ctx}/cost/cost-project-section!save.action" method="post" id="sectionForm">
	<!-- 这个很重要！ -->
	<input type="hidden" name="id" id="id" value = "${costProjectSectionId}" />
	<input type="hidden" name="costProjectSectionId" id="costProjectSectionId" value = "${costProjectSectionId}" />
	<input type="hidden" name="recordVersion" id="recordVersion" value = "${recordVersion}" />
	<fieldset style="margin-left: 5px;margin-right: 5px;margin-top: 10px;">
	<table class="cont-show-table">
		<col style="width:90px;"/>
		<col />
		<tr>
			<td align="right">*项目公司:</td>
			<td>
				<input type="text" name="projectName"  id="projectName" value="${projectName}" readonly="readonly" style="cursor: pointer;width: 150px;" title="点击选择项目公司"/> 
				<input type="hidden" id="projectCd" name="projectCd" value="${projectCd}"/>
			</td>
		</tr>
		<tr>
			<td align="right">*项目名称:</td>
		    <td>
		    <input type="text" id="sectionName" name ="sectionName" value="${sectionName}" maxlength='40' style="width: 150px;" readonly="readonly"/>
		    <input type="hidden" name="briefName" value="${briefName}"/>
		    </td>
		    
		</tr>
		<%--
		<tr>
			<td align="right">*项目编号:</td>
		    <td><input type="text" id="sectionBizCd" name ="sectionBizCd" value="${sectionBizCd}" maxlength='40' style="width: 150px;"/></td>
		</tr>
		 --%>
		<tr style="display: none;">
			<td align="right">显示序号:</td>
		    <td><input type="text" id="sequenceNo" name ="sequenceNo" value='<s:if test="sequenceNo == null">0</s:if><s:else>${sequenceNo}</s:else>' onkeyup="clearNoNum_2(this);" maxlength='10' style="width: 150px;" /></td>
		</tr>
		<tr>
			<td align="right">授权用户:</td>
			<td>
				<input type="text" name="curUserName"  id="curUserName" value="${authUiidName}" readonly="readonly" style="cursor: pointer;width:85%;" title="点击选择授权用户"/> 
				<input type="hidden" id="authUiid" name="authUiid" value="${authUiid}"/>
				<input type="hidden" id="costBudgetAuthId" name="costBudgetAuthId" value="${costBudgetAuthId}"/>(请选择)
			</td>
		</tr>
		<tr>
			<td align="right" valign="top">备注 :</td>
			<td>&nbsp;
				<textarea id="remark" name="remark" style="width:85%;height:80px;border:1px solid #ccc;color: #316685;font-size: 13px;" onpropertychange="if(value.length>150) value=value.substr(0,150)">${remark}</textarea>
			</td>
		</tr>
	    <tr>
	    	<td>&nbsp;</td>
	    	<td>&nbsp;	
				<security:authorize ifAnyGranted="A_COST_BUD_ADMIN">
		    		<input type="button" class="searchBtn" onclick="costSectionSave();" value="保存" style="cursor: pointer;margin-left: 0px;"/>
			    	<input type="button"  class="searchBtn" onclick="doAddSection();" value="关闭" style="cursor: pointer;margin-left: 5px;"/>
				</security:authorize>
				<span id="tips" style="color: red;"></span>
	    	</td>
	    </tr>
	</table>
	</fieldset>
</form>
<script type="text/javascript">
$(function(){
	/*选择项目公司-添加
	$('#projectName').orgSelect({
		muti:true,
		showProjectOrg : true,
		orgTreeType : '2'
	});
	*/
	
	//选择人员
	$("#curUserName").userSelect({
        muti:true,
        nameField:'curUserName',
        cdField:'authUiid'
	});
});

//保存项目
function costSectionSave(){
	var projectName = $('#projectName').val();
	var sectionName = $('#sectionName').val();
	if(projectName == null || projectName==""){
		alert("请选择项目公司");
		return false;
	}
	if(sectionName == null || sectionName==""){
		alert("请输入项目名称");
		return false;
	}
	$("#sectionForm").ajaxSubmit(function(result){
		if(result != "fail"){
			$.post("${ctx}/cost/cost-project-section!input.action",
					{id:result},function(result) {
				if(result){
					$("#addDiv").html(result);
					$('#tips').html('保存成功!').show().fadeOut(5000);
				}
			});
			//$('#tips').html('保存成功!').show().fadeOut(5000);
			//doAddSection(result);
			//doAddSection();
		}else{
			$('#tips').html('保存失败!').show().fadeOut(5000);
		}
	});
	//$('#sectionForm').submit();
}
</script>
	
