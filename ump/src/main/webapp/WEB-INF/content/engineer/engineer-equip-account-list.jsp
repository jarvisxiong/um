<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<script type="text/javascript">
$(document).ready(function(){
	$(".equipTable .pd-chill-tip").bind("click",function(){
		var id = $(this).parent().attr("enquipId");
		updateAccount(id);
	});
});
</script>
<table class="equipTable" cellpadding="0" cellspacing="0" style="width: 100%;">			
	<thead>
		<tr>
			<th nowrap="nowrap" style="background-image: url('');width: 2%">
				<input type="checkbox" onclick="checkAll(this);" style="vertical-align:middle;"/>
			</th>
			<th title="设备编号" nowrap="nowrap" style="width: 10%">设备编号</th>
			<th title="名称" nowrap="nowrap" style="width:  8%">名称</th>
			<th title="型号规格主要参数" nowrap="nowrap" style="width: 10%">型号规格</th>
			<th title="国名" nowrap="nowrap" style="width:  10%">制造厂名</th>
			<th title="系统" nowrap="nowrap" style="width:  8%">系统</th>
			<th title="出厂编号" nowrap="nowrap" style="width: 10%">出厂编号</th>
			<th title="出厂日期" nowrap="nowrap" style="width:5%">出厂日期</th>
			<th title="数量" nowrap="nowrap" style="width: 5%;text-align: center;">数量</th>
			<th title="设备位置" nowrap="nowrap" style="width: 8%">设备位置</th>
		</tr>
	</thead>
	<tbody>
	<c:choose>
	   <c:when test="${requestScope.haselist}">
		<s:iterator value="page.result" var="bided" status="st">
			<tr style="cursor:pointer;height: 40px;" enquipId="${engineerEquipAccountId}">
				<td >
					<div class="partHide">
						<input type="checkbox" enquipId="${engineerEquipAccountId}" style="vertical-align:middle;"/>
					</div>
				</td>
				<td class="pd-chill-tip" title="${equipCd }"><div class="short_div" style="width: 80px;">${ equipCd}</div></td>
				<td class="pd-chill-tip" title="${equipName }"><div class="short_div" style="width: 80px;">${ equipName}</div></td>
				<td class="pd-chill-tip" title="${equipModels }"><div class="short_div" style="width: 60px;">${ equipModels}</div></td>
				<td class="pd-chill-tip" title="${equipMaker }"><div class="short_div">${ equipMaker}</div></td>
				<td class="pd-chill-tip" title='<p:code2name mapCodeName="mapEquipBelongTo" value="equipBelongtoCd"/>'><div class="short_div" style="width: 60px;"><p:code2name mapCodeName="mapEquipBelongTo" value="equipBelongtoCd"/></div></td>
				<td class="pd-chill-tip" title="${equipSerialNum }"><div class="short_div" style="width: 50px;">${equipSerialNum }</div></td>
				<td class="pd-chill-tip" title="${equipSerialDate }">${equipSerialDate }</td>
				<td class="pd-chill-tip" title="${equipAmount }" style="text-align: center;"><div class="short_div" style="width: 60px;">${equipAmount }</div></td>
				<td class="pd-chill-tip" title="${equipPosition }"><div class="short_div">${equipPosition }</div></td>
		</s:iterator>
	   </c:when>
	   <c:otherwise>
			<tr><td colspan="10" align="center">暂无数据</td></tr>
		</c:otherwise>
	</c:choose>
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
	TB_showMaskLayer("正在搜索...");
	$("#mainForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$("#resultTable").html(result);
	});
}
</script>