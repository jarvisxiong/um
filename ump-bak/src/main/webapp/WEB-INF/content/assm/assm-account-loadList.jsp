<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<table class="assmTable" cellpadding="0" cellspacing="0" style="width: 100%;">			
	<thead>
		<tr>
			<th nowrap="nowrap" style="background-image: url('');width: 20px;">
				<input type="checkbox" onclick="checkAll(this);" style="vertical-align:middle;"/>
			</th>
			<th title="商业公司/总部" nowrap="nowrap" style="width: 90px;">商业公司/总部</th>
			<th title="资产名称" nowrap="nowrap" style="width: 90px;">资产名称</th>
			<th title="资产类别" nowrap="nowrap" style="width: 80px;">资产类别</th>
			<th title="资产编码" nowrap="nowrap" style="width: 60px;">资产编码</th>
			<th title="使用部门" nowrap="nowrap" style="width: 60px;">使用部门</th>
			<th title="使用情况" nowrap="nowrap" style="width: 60px;">使用情况</th>
			<th title="原值" nowrap="nowrap" style="width: 50px;">原值(元)</th>
			<th title="残值" nowrap="nowrap" style="width: 50px;">净值(元)</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="page.result" var="bided" status="st">
			<tr style="cursor:pointer;height: 40px;">
				<td class="pd-chill-tip">
					<div class="partHide">
						<input type="checkbox" assmId="${assmAccountId}" style="vertical-align:middle;"/>
					</div>
				</td>
				<td class="pd-chill-tip" onclick="updateAccount('${assmAccountId}');" title="<p:code2name mapCodeName="mapBisProject" value="projectCd"/>">
					<div class="short_div" style="width:90px;">
						<p:code2name mapCodeName="mapBisProject" value="projectCd"/>
					</div>
				</td>
				<td class="pd-chill-tip" onclick="updateAccount('${assmAccountId}');" title="${assmName}">
					<div class="short_div" style="width:90px;">
						${assmName}
					</div>
				</td>
				<td class="pd-chill-tip" onclick="updateAccount('${assmAccountId}');" title="<p:code2name mapCodeName="mapAssmModelAll" value="assmModel.assmModelId"/>">
					<div class="short_div" style="width:90px;">
						<p:code2name mapCodeName="mapAssmModelAll" value="assmModel.assmModelId"/>
					</div>
				</td>
				<td class="pd-chill-tip" onclick="updateAccount('${assmAccountId}');" title="${code}">
					<div class="short_div" style="width:60px;">
						${code}
					</div>
				</td>
				<td class="pd-chill-tip" onclick="updateAccount('${assmAccountId}');" title="<%= CodeNameUtil.getDeptNameByCd(JspUtil.findString("useDepartment"))%>">
					<div class="short_div" style="width:60px;">
						<%= CodeNameUtil.getDeptNameByCd(JspUtil.findString("useDepartment"))%>
					</div>
				</td>
				<td class="pd-chill-tip" onclick="updateAccount('${assmAccountId}');" title="<p:code2name mapCodeName="mapUseStatus" value="useStatus"/>">
					<div class="short_div" style="width:50px;">
						<p:code2name mapCodeName="mapUseStatus" value="useStatus"/>
					</div>
				</td>
				<td class="pd-chill-tip" onclick="updateAccount('${assmAccountId}');" title="${srcValue}">
					<div class="short_div" style="width:50px;">
						${srcValue}
					</div>
				</td>								
				<td class="pd-chill-tip" onclick="updateAccount('${assmAccountId}');" title="${remainVal}">
					<div class="short_div" style="width:60px;">
						${remainVal}
					</div>
				</td>
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
function jumpPageTo(){
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
//跳转到相应的页
function jumpPage(pageNo){
	$("#pageNo").val(pageNo);
	$("#parentId").val($("#gloab_hide_pratentId").val());
	$("#mainForm").attr('action',_ctx+'/assm/assm-account!list.action');
	TB_showMaskLayer("正在搜索...");
	$("#mainForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$("#resultTable").html(result);
	});
}
</script>

