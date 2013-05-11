<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<table class="assmTable" cellpadding="0" cellspacing="0" style="width: 100%;">			
	<thead>
		<tr>
			<th nowrap="nowrap" style="background-image: url('');width:20px;">
				<input type="checkbox" onclick="checkAll(this);" style="vertical-align:middle;"/>
			</th>
			<th title="设备名称" nowrap="nowrap" style="width:150px;">设备名称</th>
			<th title="编码" nowrap="nowrap" style="width:120px;">编码</th>
			<th title="专业编码" nowrap="nowrap" style="width:90px;">专业编码</th>
			<th title="长编码" nowrap="nowrap" style="width:90px;">长编码</th>
			<th title="级次" nowrap="nowrap" style="width:50px;">级次</th>
			<security:authorize ifAnyGranted="A_ASSM_MOD_NEW">			
				<th title="操作" nowrap="nowrap" width="30px;" style="padding-left: 10px;">操作</th>
			</security:authorize>		
		</tr>
	</thead>
	<tbody id="body">
		<s:iterator value="page.result" var="mo" status="st">
			<tr style="height: 40px;">
				<td class="pd-chill-tip">
					<div class="partHide">
						<s:if test="assmLevel!=1 && assmLevel!=2">
							<input type="checkbox" assmId="${assmModelId}" style="vertical-align:middle;"/>
						</s:if>
					</div>
				</td>
				<td class="pd-chill-tip" title="${assmName}">
					<div class="short_div" style="width:150px;margin-left: 5px;">
						${assmName}
					</div>
				</td>
				<td class="pd-chill-tip" title="${assmCode}">
					<div class="short_div" style="width:120px;">
						${assmCode}
					</div>
				</td>
				<td class="pd-chill-tip" title="${proCode}">
					<div class="short_div" style="width:90px;">
						${proCode}
					</div>
				</td>
				<td class="pd-chill-tip" title="${fullCode}">
					<div class="short_div" style="width:90px;">
						${fullCode}
					</div>
				</td>
				<td class="pd-chill-tip" title="${assmLevel}">
					<div>${assmLevel}</div>
				</td>
				<security:authorize ifAnyGranted="A_ASSM_MOD_NEW">
					<td>
						<s:if test="assmLevel!=1 && assmLevel!=2">
							<div class="btns clearfix" style="padding-left: 10px;">
							 	<button type="button" class="green min" style="height: 25px;width: 50px;" onclick="modifyModel('${assmModelId}');">编辑</button>
							</div>
						</s:if>
					</td>
				</security:authorize>
			</tr>
		</s:iterator>			
	</tbody>
</table>
<s:if test="page.result==null||page.result.size()<1">
	<center>
		<div style="margin-top: 10px;">无记录！</div>
	</center>
</s:if>
<s:else>
	<div class="pageFooter">
		<p:page/>
	</div>
</s:else>
	
<script type="text/javascript">
//跳转至给定的页面,配合前台的分页搜索
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
//跳转到相应的页
function jumpPage(pageNo) {	
	$("#pageNo").val(pageNo);
	$("#parentId").val($("#gloab_hide_pratentId").val());
	
	$("#mainForm").attr('action',_ctx+'/assm/assm-model!loadList.action');
	TB_showMaskLayer("正在搜索...");
	$("#mainForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		//显示表格
		$("#resultTable").html(result);
	});
}

//编辑设备型号信息
function modifyModel(assmModelId){
	var data={assmModelId:assmModelId};
	var url = '${ctx}/assm/assm-model!input.action';
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='selectTypeDiv' style='padding-right:10px;'><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
		width : 380,
		height :270,
		title : "编辑",
		afterShow : function() {
			$.post(url, data, function(result) {
				$("#selectTypeDiv").html(result);
			});
		},
		handler : function(btn){
			ymPrompt.close();
		},
		closebtn:true,
		btn:[]
	});
}

</script>
	

