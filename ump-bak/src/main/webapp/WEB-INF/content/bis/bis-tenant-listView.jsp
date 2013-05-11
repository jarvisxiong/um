<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
<form action="${ctx}/bis/bis-tenant!list.action" id="SearchForm" method="post">
<div style="height:8px;"></div>
<div style="padding-left: 8px; height: 30px; line-height:30px; border: 1px solid #eeeeee;">
	<table class="tb_search">
		<col width="45"/>
		<col width="100"/>
		<col width="50"/>
		<col width="90"/>
		<col width="50"/>
		<col width="80"/>
		<col width="50"/>
		<col width="80"/>
		<col />
		<tr>
			<td align="right">项目：</td>
			<td>
			<input type="text" id="bisProjectName" name="bisProjectName" value="${bisProjectName}" style="width:100%; cursor: pointer; font-size: 12px; color: #ff0000;"/>
			<input type="hidden" id="projectId" name="bisProjectId" value="${bisProjectId}"/>
			</td>
			<td align="right">楼层：</td>
			<td>
			<s:select cssStyle="width:100%;" list="mapBisFloor" listKey="key" listValue="value" id="floorId" name="bisFloorId" ></s:select>
			</td>
			<td align="right">商家：</td>
			<td>
			<input class="enterQuery" type="text" id="filter_LIKES_shopName" name="filter_LIKES_shopName" value="${filter_LIKES_shopName}" style="width:100%;"/>
			</td>
			<td align="right">商铺：</td>
			<td >
			<input class="enterQuery" type="text" id="filter_LIKES_storeNo" name="filter_LIKES_storeNo" value="${filter_LIKES_storeNo}" style="width:100%;"/>
			</td>
			<td style="padding-left:10px; line-height:20px;">
			<input type="button" value="搜索" class="btn_blue" onclick="submitSearch();" />
			<%-- 
			<s:if test="#curUserCd == 'baolm'">
			<input type="button" value="合并" class="btn_blue" onclick="toMergeTenant();" />
			</s:if>
			--%>
			</td>
		</tr>
	</table>
</div>

<div style="padding: 8px; border-bottom: 1px solid #8c8f94;">
<table class="content_table" id="tbConItem">
	<col />
	<col />
	<col />
	<col width="60"/>
	<col width="60"/>
	<col width="70"/>
	<col width="60"/>
	<col width="70"/>
	<col width="70"/>
	<thead>
		<tr class="header">
			<th align="left" style="font-weight:bolder;">商家/业主</th>
			<th align="left" style="font-weight:bolder;">经销商</th>
			<th align="left" style="font-weight:bolder;">商铺</th>
			<th align="left" style="font-weight:bolder;">开始时间</th>
			<th align="left" style="font-weight:bolder;">结束时间</th>
			<th align="left" style="font-weight:bolder;">经营性质</th>
			<th align="left" style="font-weight:bolder;">业态</th>
			<th align="left" style="font-weight:bolder;">状态</th>
			<th align="left" style="font-weight:bolder;">操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="page.result" status="s">
			<s:set var="shopName">
				<s:if test="bisShopId == null">
				${owner}
				</s:if>
				<s:else>
				<s:property value="%{getShopName(bisShopId)}"/>
				</s:else>
			</s:set>
			<s:set var="shopConnName">
				<s:if test="bisShopConnId != null">
				<s:property value="%{getShopConnName(bisShopConnId)}"/>
				</s:if>
				&nbsp;
			</s:set>
			<s:set var="bisStoreNos">
				<s:property value="%{getStoreNos(bisTenantId)}"/>&nbsp;
			</s:set>
			<s:set var="layout">
				<s:if test="layoutCd != null">
				<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapStoreLayout()" value="layoutCd"/>
				</s:if>
				&nbsp;
			</s:set>
			<s:set var="status">
				<s:if test="statusCd==null">正常</s:if>
				<s:elseif test="statusCd==1">退铺待审核</s:elseif>
				<s:elseif test="statusCd==2">已退铺</s:elseif>
				<s:else>退铺驳回</s:else>
			</s:set>
			<tr class="mainTr" id="main_${bisTenantId}" value="${bisTenantId}"
				<s:if test="statusCd==2">style="color:#909090;"</s:if>
				>	
				<td onclick="selShopStore('${bisTenantId}');" class="pd-chill-tip" title="${shopName}">
					<div class="ellipsisDiv_full" >
					${shopName}
					</div>
				</td>
				<td onclick="selShopStore('${bisTenantId}');" class="pd-chill-tip" title="${shopConnName}">
					<div class="ellipsisDiv_full" >
					${shopConnName}
					</div>
				</td>
				<td onclick="selShopStore('${bisTenantId}');" class="pd-chill-tip" title="${bisStoreNos}">
					<div class="ellipsisDiv_full" >
					${bisStoreNos}
					</div>
				</td>
				<td onclick="selShopStore('${bisTenantId}');"><s:date name="rentStartDate" format="yy-MM-dd"/></td>
				<td onclick="selShopStore('${bisTenantId}');"><s:date name="rentEndDate" format="yy-MM-dd"/></td>
				<td onclick="selShopStore('${bisTenantId}');"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" value="manageCd"/></td>
				<td onclick="selShopStore('${bisTenantId}');"  class="pd-chill-tip" title="${layout}">
					<div class="ellipsisDiv_full" >
					${layout}
					</div>
				</td>
				<td>${status}</td>
				<td>
					<s:if test="#curUserCd == 'baolm'">
					</s:if>
					<s:if test="statusCd==null || statusCd==3">
					<security:authorize ifAnyGranted="A_TENE_OUT_LAUN">
					<!--<input type="button" class="btn_blue" value="退铺发起" onclick="doShopBack('${bisTenantId}');"/>-->
					</security:authorize>
					</s:if>
					<s:elseif test="statusCd==1">
					<security:authorize ifAnyGranted="A_TENE_OUT_CHECK">
					<input type="button" class="btn_blue" value="退铺审核" onclick="doShopBackApprove('${bisTenantId}');"/>
					</security:authorize>
					</s:elseif>
					<s:else>
					<input type="button" class="btn_green" value="退铺查看" onclick="viewShopBack('${bisTenantId}');"/>
					</s:else>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>

<style type="text/css">
.table_pager {
	float: right;
	margin: 5px 0;
}
</style>
<div class="table_pager">
	<p:page />
</div>

</div>
</form>

<script type="text/javascript">
$(function(){
	$('#bisProjectName').onSelect({
		muti:false,
		callback:function(project){
			$("#bisProjectName").val(project.projectName);
			$("#projectId").val(project.bisProjectId);
			reloadFloor();
		}
	});
	
	var bisTenantId = $('#bisTenantId').val();
	$("#main_"+bisTenantId).addClass("tr_tenant_click");
});
//重载楼层列表
function reloadFloor() {
	var bisProjectId = $("#projectId").val();
	var floorType = "1";
	if(isEmpty(bisProjectId) || isEmpty(floorType)) {
		return false;
	}
	var subSele = $("#floorId");
	subSele.empty();
	subSele.append('<option value="">--选择--</option>');
	
	var url = "${ctx}/bis/bis-project!getFloorNo.action";
	$.post(url,{bisProjectId:bisProjectId, floorType:floorType},function(data){
		var data = eval('('+data+')');
		$.each(data,function(i,n){
			var option = '<option value="'+i+'">'+n+'</option>';
			subSele.append(option);
		});
	});
}
//ENTER事件
$(".enterQuery").keydown(function(event){
	if(event.keyCode==13){
		submitSearch();
	}
});
//搜索
function submitSearch() {
	$("#pageNo").val("1");
	TB_showMaskLayer("正在搜索...");
	$("#SearchForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$("#viewPanel").html(result);
	});
}
function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	TB_showMaskLayer("正在搜索...");
	$("#SearchForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$("#viewPanel").html(result);
	});
}
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
//退铺发起
function doShopBack(id) {
	var url = _ctx+"/bis/bis-tenant!shopBack.action?bisTenantId="+id+"&bisProjectId="+$("#projectId").val();
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab("shopBackMenu","退铺",url,true);
}
//退铺审核
function doShopBackApprove(id) {
	var url = _ctx+"/bis/bis-tenant!shopBackApprove.action?bisTenantId="+id+"&bisProjectId="+$("#projectId").val();
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab("shopBackMenu","退铺",url,true);
}
//查看退铺信息
function viewShopBack(id) {
	var url = _ctx+"/bis/bis-tenant!shopBackView.action?bisTenantId="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab("shopBackMenu","退铺",url,true);
}
</script>