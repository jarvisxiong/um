<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisStoreSelect.css" />

<input type="hidden" id="bisTenantId" value="${bisTenantId}"/>
<input type="hidden" id="bisProjectHideId" value="${bisProjectId}"/>
<input type="hidden" id="bigTypeId" value="${contBigTypeCd}"/>
<input type="hidden" id="smallTypeId" />

<div style="padding: 20px 20px 0 20px; height: 160px;">
	<div id="divBigType" style="float: left; margin-right: 20px; width: 130px; height: 100%; border: 1px solid #ccc;">
		<div class="ctsltTitle" ><font color="#ff0000">*</font>&nbsp;合同大类</div>
		<ul id="ulBigType" class="ctslt-nav" style="border-top: 1px solid #ccc;">
			<s:iterator value="mapContBigType">
			<li id="btli_${key}" value="${key}" onclick="clkBigType(this);" >${value}</li>
			</s:iterator>
		</ul>
	</div>
	
	<div id="divSmallType" style="float: left; width: 130px; height: 100%; border: 1px solid #ccc;">
		<div class="ctsltTitle" ><font color="#ff0000">*</font>&nbsp;合同小类</div>
		<ul id="ulSmallType" class="ctslt-nav" style="border-top: 1px solid #ccc;">
			<!--<s:iterator value="mapContSmallType">
			<li id="stli_${key}" value="${key}" onclick="clkSmallType(this);">${value}</li>
			</s:iterator>
			-->
		</ul>
	</div>
</div>

<script type="text/javascript">

function clkBigType(dom) {
	$("#ulBigType li").removeClass("ctslt-nav-click");
	$(dom).addClass("ctslt-nav-click");
	$("#bigTypeId").val(dom.value);
	$("#smallTypeId").val("");
	loadSmallType(dom.value);
}

function loadSmallType(bigtype) {
	$.post("${ctx}/bis/bis-cont!loadSmallType.action", {contBigTypeCd:bigtype}, function(result) {
		$("#ulSmallType").html(result);
	});
}

function clkSmallType(dom) {
	$("#ulSmallType li").removeClass("ctslt-nav-click");
	$(dom).addClass("ctslt-nav-click");
	$("#smallTypeId").val(dom.value);
}
</script>