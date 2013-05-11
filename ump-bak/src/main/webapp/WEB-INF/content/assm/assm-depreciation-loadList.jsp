<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div style="margin: 10px 10px 10px 10px;" class="res_tip">
	<span style="margin-left: 10px;font-size: 16px;font-weight: bold;">资产折旧</span>
	<span style="margin-left: 10px;">注意：1、固定资产满折旧后，不论能否继续使用，均不再提取折旧；2、已报废的固定资产，不再折旧。</span>
</div>
<div style="margin:10px 10px 10px 10px;">
<table class="assmTable" cellpadding="0" cellspacing="0" style="margin-top: 5px;width: 100%;">			
	<thead>
		<tr>
			<th title="序号" nowrap="nowrap" style="background-image: url('');width:35px;">序号</th>
			<th title="固定资产类型" nowrap="nowrap" style="width:200px;">固定资产类型</th>
			<th title="折旧年限(年)" nowrap="nowrap" style="width:150px;">折旧年限(年)</th>
			<th title="预计残值率" nowrap="nowrap" style="width:150px;">预计残值率</th>
			<th title="年折旧率" nowrap="nowrap" style="width:150px;">年折旧率</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="page.result" var="assmdepr" status="st">
			<tr style="height: 40px;">
				<td class="pd-chill-tip">
					<div class="partHide" style="margin-left: 10px;"><s:property value="#st.index+1"/></div>
				</td>
				<td class="pd-chill-tip" title="${assmName}">
					<div class="partHide">${assmName}</div>
				</td>
				<td class="pd-chill-tip" title="${deprePeriod}年">
					<div class="partHide">${deprePeriod}&nbsp;年</div>
				</td>
				<td class="pd-chill-tip" title="${preRemain}%">
					<div class="partHide">${preRemain}&nbsp;%</div>
				</td>
				<td class="pd-chill-tip" title="${depreRate}%">
					<div class="partHide">${depreRate}&nbsp;%</div>
				</td>
			</tr>
		</s:iterator>			
	</tbody>
</table>
</div>
<s:if test="page.result==null||page.result.size()<1">
	<center>
		<div style="margin-top: 10px;">无记录！</div>
	</center>
</s:if>
<s:else>
	<div class="pageFooter">
		<form id="deprePageFooter" action="${ctx}/assm/assm-depreciation!list.action" method="post">
			<p:page/>
		</form>
	</div>
</s:else>

<script type="text/javascript">

function jumpPageTo(){
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
function jumpPage(pageNo){
	$("#pageNo").val(pageNo);
	TB_showMaskLayer("正在搜索...");
	$("#deprePageFooter").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$("#mainDiv").html(result);
	});
}
</script>

