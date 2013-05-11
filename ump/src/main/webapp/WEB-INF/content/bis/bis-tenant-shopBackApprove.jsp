<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css"/>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>

<title>退铺发起</title>
</head>

<body>
<div class="title_bar">
	<div style="font-weight:bold;padding-left:8px;font-size:14px;float:left;">退铺审核</div>
	<div style="float:right;font-size:12px;padding-right:10px;line-height:30px;">
		<s:if test="statusCd!=2">
		<input type="button" value="审核" class="btn_blue" onclick="doShopBackApprove();" />
		<input type="button" value="驳回" class="btn_red" onclick="doShopBackReject();" />
		</s:if>
		<input type="button" value="返回" class="btn_green" onclick="closeWindow();" style="margin-top:4px;"/>
	</div>
</div>

<div style="padding: 10px 10px 0 10px;">
<div style="padding-left: 10px; height: 30px; line-height:30px; border: 1px solid #eeeeee;">
	商家：<span style="color: blue;">${nameCn}</span>&nbsp;&nbsp;&nbsp;&nbsp;商铺：<span style="color: blue;">${bisStoreNos}</span>
	<input type="hidden" id="bisProjectId" name="bisProjectId" value="${bisProjectId}"/>
	<input type="hidden" id="bisTenantId" name="bisTenantId" value="${bisTenantId}"/>
</div>
</div>

<div id="shopBackPanel" style="padding: 10px;">
	<div class="title_bar"  style="background-color:#e4e7ec;font-weight:bold ; padding-left: 8px;font-size: 12px;color: #464646;">
		退铺信息
	</div>
	<div id="tenantContsPage" style="border: 1px solid #cccccc; padding: 10px;">
	<table style="width:100%; line-height:30px;">
		<col width="120"/>
		<col width="350"/>
		<col />
		<tr>
			<td style="text-align: right;">退铺原因：</td>
			<td>
				<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisBackReason()" value="backReason"/>
			</td>
			<td></td>
		</tr>
		<tr>
			<td style="text-align: right;">退铺日期：</td>
			<td>
				<s:date name="backDate" format="yyyy-MM-dd"/>
			</td>
			<td></td>
		</tr>
		<tr>
			<td style="text-align: right;">退铺资料上传：</td>
			<td>
				<a href="javascript:showAttachment('${bisTenantId}');" >
				<img id="bisTenantAttachId" style="padding-top: 12px;" <s:if test="attachFlg==1">src="${ctx}/resources/images/common/atta_y.gif"</s:if><s:else>src="${ctx}/resources/images/common/atta.gif"</s:else> />
				</a>
			</td>
			<td></td>
		</tr>
		<tr>
			<td style="text-align: right;">备注：</td>
			<td>
				<textarea style="width:100%;height:80px;font-size:12px;background-color:#dddbdc;border:0;" name="remark" readonly="readonly">${remark}</textarea>
			</td>
			<td></td>
		</tr>
	</table>
	</div>

	<div class="title_bar"  style="background-color:#e4e7ec;font-weight:bold ; padding-left: 8px; margin-top:20px; font-size: 12px;color: #464646;">
		相关费用&nbsp;&nbsp;&nbsp;&nbsp;
		<input class="btn_green" type="button" onclick="viewFeeDetail();" value="查看收费明细" style="width:90px; margin-top:4px;">
	</div>
	<div id="tenantContsPage">
	<table class="content_table" >
		<col width="150"/>
		<col width="120"/>
		<col width="120"/>
		<col />
		<thead>
		<tr class="header">
			<th align="left" style="background: none; padding-left: 10px;">费用类别</th>
			<th align="left">应收总计</th>
			<th align="left" colspan="2">实收总计</th>
		</tr>
		</thead>
		<tbody>
		<s:iterator value="tenantFees">
		<tr class="mainTr" onclick="viewFeeDetail('${bisTenantId}');" >	
			<td style="padding-left: 10px;">
				<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapChargeType()" value="chargeTypeCd"/>
			</td>
			<td <s:if test="mustTotal > factTotal">style="color:red;"</s:if> >${mustTotal}</td>
			<td <s:if test="mustTotal > factTotal">style="color:red;"</s:if> >${factTotal}</td>
			<td></td>
		</tr>
		</s:iterator>
		<tr class="mainTr" onclick="viewFeeDetail('${bisTenantId}');" >	
			<td style="padding-left: 10px; color: red;">
				总计：
			</td>
			<td <s:if test="totalFeeVo.mustTotal > totalFeeVo.factTotal">style="color:red;"</s:if> >${totalFeeVo.mustTotal}</td>
			<td <s:if test="totalFeeVo.mustTotal > totalFeeVo.factTotal">style="color:red;"</s:if> >${totalFeeVo.factTotal}</td>
			<td></td>
		</tr>
		</tbody>
	</table>
	</div>
	
	<div class="title_bar"  style="background-color:#e4e7ec;font-weight:bold ; padding-left: 8px; margin-top:20px; font-size: 12px;color: #464646;">
		相关合同
	</div>
	<div id="tenantContsPage" style="overflow: auto;">
	<table class="content_table" >
		<col width="180"/>
		<col width="180"/>
		<col width="100"/>
		<col width="70"/>
		<col width="70"/>
		<col width="80"/>
		<col />
		<thead>
		<tr class="header">
			<th align="left" style="background: none; padding-left: 10px;">合同编号</th>
			<th align="left">商铺</th>
			<th align="left">合同类别</th>
			<th align="left">合同开始</th>
			<th align="left">合同结束</th>
			<th align="left">状态</th>
			<th align="left">操作</th>
		</tr>
		</thead>
		<tbody>
		<s:iterator value="tenantConts">
			<tr class="mainTr" onclick="viewContDetail('${bisContId}');" >	
				<s:set var="stname">
					<s:property value="%{getContStoreNos(bisContId)}"/>&nbsp;
				</s:set>
				<s:set var="status">
					<s:if test="activeBl">
					<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisContStatus()" value="statusCd"/>
					<s:if test="updateBl">(变更中)</s:if>
					</s:if>
					<s:else>已失效</s:else>
				</s:set>
				<td class="pd-chill-tip" style="padding-left: 10px; padding-right: 6px;" title="${contNo}" >
					<div class="ellipsisDiv_full" >
					${contNo}
					</div>
				</td>
				<td class="pd-chill-tip" style="padding-right: 6px;" title="${stname}" >
					<div class="ellipsisDiv_full" >
					${stname}
					</div>
				</td>
				<td><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContBigType()" value="contBigTypeCd"/>-<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContSmallType()" value="contSmallTypeCd"/></td>
				<td><s:date name="contStartDate" format="yy-MM-dd"/></td>
				<td><s:date name="contEndDate" format="yy-MM-dd"/></td>
				<td <s:if test="statusCd==4">style="color: red"</s:if><s:elseif test="statusCd==3">style="color: blue;"</s:elseif> >
					${status}
				</td>
				<td><input class="btn_green" type="button" value="查看合同明细" style="width:85px;"></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	</div>
	

</div>

<script type="text/javascript">
//合同明细
function viewContDetail(id) {
	if(isEmpty(id)) {
		return;
	}
	var url = _ctx+"/bis/bis-cont!input.action?id="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab("bisContMenu","合同明细",url,true);
}
//查看费用明细
function viewFeeDetail(){
	var bisTenantId = $('#bisTenantId').val();
	var bisProjectId = $('#bisProjectId').val();
	if(isEmpty(bisTenantId) || isEmpty(bisProjectId)) {
		return;
	}
	//var url = _ctx+"/bis/bis-fact!list.action?dimension=2"+"&bisTenantId="+bisTenantId+"&bisProjectId="+bisProjectId;
	//parent.TabUtils.newTab("bisFee","收费明细",url,true);
	//跳转至收费明细页面：欠费维度
	var url="${ctx}/bis/bis-manage!layout.action?ifFromReport=true&module=3&dimension=3&bisTenantId="+bisTenantId+'&bisProjectId='+bisProjectId;
	if(null!=parent.TabUtils){
		parent.TabUtils.newTab("bis-manage-layout","费用明细",url,true);
	}else{
		window.open(url);
	}
}
//关闭标签
function closeWindow() {
	var cfg = {};
	cfg.data = {tabId:"shopBackMenu"};
	parent.TabUtils.closeTab(cfg);
}

//查看附件
function showAttachment(entityId){
	ymPrompt.win({
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=bisProjectLayout&filterType=image|office&onlyShow=true",
		width:500,
		height:300,
		title: '附件查看',
		afterShow:function(){},
		iframe:true,
		btn:[["完成",'close']]
	});
}

//审核
function doShopBackApprove() {
	var url = "${ctx}/bis/bis-tenant!doShopBackApprove.action";
	$.post(url,{bisTenantId:'${bisTenantId}'},function(result){
		alert("操作成功");
		closeWindow();
	});
}
//驳回
function doShopBackReject() {
	var url = "${ctx}/bis/bis-tenant!doShopBackReject.action";
	$.post(url,{bisTenantId:'${bisTenantId}'},function(result){
		alert("操作成功");
		closeWindow();
	});
}
isEmpty = function (str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
};
</script>

</body>
</html>