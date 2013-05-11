<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"  />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>

<title>项目总况</title>
</head>

<body onload="adjustHeight();">

<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
<div id="mainDiv" style="overflow: auto; ">
<div class="title_bar" >
	<div style="font-weight:bold;padding-left:8px;padding-right:8px; font-size:14px;float:left;">项目总况</div>
	<div style="float:left;"><%@ include file="bis-manage-version.jsp" %></div>
	
            <div style="float:right; height:26px; margin-right:5px; margin-top:6px;">
                <div class="btn_green" onclick="clkFullScreen('${bisProjectId}');">全屏</div>
            </div>
</div>

<div id="overviewInfoDiv" style="height:160px;background-color:#e4e7ec;margin:10px 8px;">
	<input type="hidden" id="bisProjectId" value="${bisProjectId}"/>
	<table class="tb_content">
		<col width="90"/>
		<col width="120"/>
		<col width="100"/>
		<col width="130"/>
		<col width="130"/>
		<col />
		<tr>
			<s:set var="area">
			<s:if test="bisProjectAreaCd=='SOUTH'">南区</s:if>
			<s:elseif test="bisProjectAreaCd=='NORTH'">北区</s:elseif>
			<s:elseif test="bisProjectAreaCd=='SH'">上海</s:elseif>
			</s:set>
			<th colspan="8" style="text-align:left;padding-left:8px;">
				正在查看： ${area}&nbsp;>&nbsp;<span class="link_bis" style="font-size: 14px; color: red;" id="btn_changeProject">${bisProjectName}</span></font>
				<input id="conditionType" type="hidden" value="${conditionType}"/>
				
				<span id="byMonth" <s:if test="conditionType==1">style="display: none;"</s:if> >
				&nbsp;&nbsp;<input class="Wdate" style="width: 70px;" type="text" id="reportDate" value='${reportDate}' onfocus="WdatePicker({dateFmt:'yyyy-MM'});" />
				&nbsp;&nbsp;<input type="button" class="btn_green" style="width:75px;" value="按时间段查" onclick="changeCondition(1);"/>
				</span>
				
				<span id="byTimePeriod" <s:if test="conditionType!=1">style="display: none;"</s:if> >
				&nbsp;&nbsp;<input class="Wdate" style="width: 70px;" type="text" id="startDate" value='${startDate}' onfocus="WdatePicker({dateFmt:'yyyy-MM'});" />
				&nbsp;-&nbsp;<input class="Wdate" style="width: 70px;" type="text" id="endDate" value='${endDate}' onfocus="WdatePicker({dateFmt:'yyyy-MM'});" />
				&nbsp;&nbsp;<input type="button" class="btn_green" style="width:75px;" value="按月查" onclick="changeCondition(0);"/>
				</span>
				
				<s:if test="#curUserCd=='baolm' || #curUserCd=='lujy' || #curUserCd=='shenjian' || #curUserCd=='jiaoxf' || #curUserCd=='dengyh'">
				&nbsp;&nbsp;<input type="button" class="btn_blue" value="搜索" onclick="reloadOverview();"/>
				&nbsp;&nbsp;<input type="button" class="btn_blue" style="width:75px;" value="更新数据" onclick="refreshData();"/>
				</s:if>
			</th>
		</tr>
		<tr>
			<td class="td_title_border">MALL面积：</td>
			<td>
			<s:if test="totalInfo.square != null">
			${totalInfo.square}㎡
			</s:if>
			</td>
			<td class="td_title_border">租约率：</td>
			<td>
			<s:if test="totalInfo.rentRate != null">
			${totalInfo.rentRate}%
			</s:if>&nbsp;
			</td>
			<td class="td_title_border">平均租金单价：</td>
			<td>
			<s:if test="totalInfo.rentUnitPrice != null">
			${totalInfo.rentUnitPrice}元/平方.月
			</s:if>&nbsp;
			</td>
		</tr>
		<tr>
			<td class="td_title_border">自留物业：</td>
			<td>
			<s:if test="totalInfo.propLeave != null">
			${totalInfo.propLeave}㎡
			</s:if>&nbsp;
			</td>
			<td class="td_title_border">出售物业：</td>
			<td>
			<s:if test="totalInfo.propSell != null">
			${totalInfo.propSell}㎡
			</s:if>&nbsp;
			</td>
			<td class="td_title_border">平均物业单价：</td>
			<td>
			<s:if test="totalInfo.propUnitPrice != null">
			${totalInfo.propUnitPrice}元/平方.月
			</s:if>&nbsp;
			</td>
		</tr>
		<tr>
			<td class="td_title_border">返租率：</td>
			<td>
			<s:if test="totalInfo.backRate != null">
			${totalInfo.backRate}%
			</s:if>&nbsp;
			</td>
			<td class="td_title_border">返租租金单价：</td>
			<td>
			<s:if test="totalInfo.backUnitPrice != null">
			${totalInfo.backUnitPrice}元/平方.月
			</s:if>&nbsp;
			</td>
			<td class="td_title_border">返租收支平均差额：</td>
			<td>
			<s:if test="totalInfo.backPayBalance != null">
			${totalInfo.backPayBalance}
			</s:if>&nbsp;
			</td>
		</tr>
		<tr>
			<td class="td_title_border">租金收款率：</td>
			<td>
			<s:if test="totalInfo.rentCollectRate != null">
			${totalInfo.rentCollectRate}%
			</s:if>&nbsp;
			</td>
			<td class="td_title_border">能耗收款率：</td>
			<td>
			<s:if test="totalInfo.energyCollectRate != null">
			${totalInfo.energyCollectRate}%
			</s:if>&nbsp;
			</td>
			<td class="td_title_border">物业收款率：</td>
			<td>
			<s:if test="totalInfo.propCollectRate != null">
			${totalInfo.propCollectRate}%
			</s:if>&nbsp;
			</td>
		</tr>
		<tr>
			<td class="td_title" style="font-weight:bolder;">项目业态：</td>
			<td colspan="5">${totalInfo.projectLayout}&nbsp; </td>
		</tr>
	</table>
</div>

<div>
<div id="floorDiv" style="float:left; width:360px; padding-left:8px;">
	<%@ include file="bis-manage-overviewMall.jsp" %>
</div>
<div id="baseInfoDiv" style="float:left; margin-left:8px; padding-right:8px;">
	<div style="border-bottom: 1px solid #cccccc; padding:10px 0 5px;">
	<table class="tb_content">
		<col width="10"/>
		<col width="70"/>
		<col width="120"/>
		<col width="80"/>
		<col />
		<tr>
			<td colspan="5" class="td_header link" <security:authorize ifAnyGranted="A_REPO_DETA_QUERY">onclick="toReport('1');" title="查看主力店报表"</security:authorize> >
				<font style="font-weight:bolder;">主力店</font>&nbsp;
				(面积：${mainStoreInfo.square}㎡&nbsp;&nbsp; 商家总数：${mainStoreInfo.storeCount}&nbsp;&nbsp;)
			</td>
		</tr>
		<tr>
			<td></td>
			<td class="td_title">租约率：</td>
			<td >
				<s:if test="mainStoreInfo.rentRate != null">
				${mainStoreInfo.rentRate}%
				</s:if>&nbsp;
			</td>
			<td class="td_title"> 租金收款率：</td>
			<td >
				<s:if test="mainStoreInfo.rentCollectRate != null">
				${mainStoreInfo.rentCollectRate}%
				</s:if>&nbsp;
			</td>
		</tr>
		<tr>
			<td></td>
			<td class="td_title">返租率：</td>
			<td >
				<s:if test="mainStoreInfo.backRate != null">
				${mainStoreInfo.backRate}%
				</s:if>&nbsp;
			</td>
			<td class="td_title">能耗收款率：</td>
			<td >
				<s:if test="mainStoreInfo.energyCollectRate != null">
				${mainStoreInfo.energyCollectRate}%
				</s:if>&nbsp;
			</td>
		</tr>
		<tr>
			<td></td>
			<td class="td_title">租金单价：</td>
			<td >
				<s:if test="mainStoreInfo.rentUnitPrice != null">
				${mainStoreInfo.rentUnitPrice}元/平方.月
				</s:if>&nbsp;
			</td>
			<td class="td_title">物业收款率：</td>
			<td >
				<s:if test="mainStoreInfo.propCollectRate != null">
				${mainStoreInfo.propCollectRate}%
				</s:if>&nbsp;
			</td>
		</tr>
	</table>
	</div>
	<div style="border-bottom: 1px solid #cccccc; padding:10px 0 5px;">
	<table class="tb_content">
		<col width="10"/>
		<col width="70"/>
		<col width="120"/>
		<col width="80"/>
		<col />
		<tr>
			<td colspan="5" class="td_header link" <security:authorize ifAnyGranted="A_REPO_DETA_QUERY">onclick="toReport('2');" title="查看次主力店报表"</security:authorize> >
				<font style="font-weight:bolder;">次主力店</font>&nbsp;
				(面积：${minorStoreInfo.square}㎡&nbsp;&nbsp; 商家总数：${minorStoreInfo.storeCount}&nbsp;&nbsp;)
			</td>
		</tr>
		<tr>
			<td></td>
			<td class="td_title">租约率：</td>
			<td >
				<s:if test="minorStoreInfo.rentRate != null">
				${minorStoreInfo.rentRate}%
				</s:if>&nbsp;
			</td>
			<td class="td_title"> 租金收款率：</td>
			<td >
				<s:if test="minorStoreInfo.rentCollectRate != null">
				${minorStoreInfo.rentCollectRate}%
				</s:if>&nbsp;
			</td>
		</tr>
		<tr>
			<td></td>
			<td class="td_title">返租率：</td>
			<td >
				<s:if test="minorStoreInfo.backRate != null">
				${minorStoreInfo.backRate}%
				</s:if>&nbsp;
			</td>
			<td class="td_title">能耗收款率：</td>
			<td >
				<s:if test="minorStoreInfo.energyCollectRate != null">
				${minorStoreInfo.energyCollectRate}%
				</s:if>&nbsp;
			</td>
		</tr>
		<tr>
			<td></td>
			<td class="td_title">租金单价：</td>
			<td >
				<s:if test="minorStoreInfo.rentUnitPrice != null">
				${minorStoreInfo.rentUnitPrice}元/平方.月
				</s:if>&nbsp;
			</td>
			<td class="td_title">物业收款率：</td>
			<td >
				<s:if test="minorStoreInfo.propCollectRate != null">
				${minorStoreInfo.propCollectRate}%
				</s:if>&nbsp;
			</td>
		</tr>
	</table>
	</div>
	<div style="border-bottom: 1px solid #cccccc; padding:10px 0 5px;">
	<table class="tb_content">
		<col width="10"/>
		<col width="70"/>
		<col width="120"/>
		<col width="80"/>
		<col />
		<tr>
			<td colspan="5" class="td_header link" <security:authorize ifAnyGranted="A_REPO_DETA_QUERY">onclick="toReport('3');" title="查看小商铺报表"</security:authorize> >
				<font style="font-weight:bolder;">小商铺</font>&nbsp;
				(面积：${smallStoreInfo.square}㎡&nbsp;&nbsp; 商家总数：${smallStoreInfo.storeCount}&nbsp;&nbsp;)
			</td>
		</tr>
		<tr>
			<td></td>
			<td class="td_title">租约率：</td>
			<td >
				<s:if test="smallStoreInfo.rentRate != null">
				${smallStoreInfo.rentRate}%
				</s:if>&nbsp;
			</td>
			<td class="td_title"> 租金收款率：</td>
			<td >
				<s:if test="smallStoreInfo.rentCollectRate != null">
				${smallStoreInfo.rentCollectRate}%
				</s:if>&nbsp;
			</td>
		</tr>
		<tr>
			<td></td>
			<td class="td_title">返租率：</td>
			<td >
				<s:if test="smallStoreInfo.backRate != null">
				${smallStoreInfo.backRate}%
				</s:if>&nbsp;
			</td>
			<td class="td_title">能耗收款率：</td>
			<td >
				<s:if test="smallStoreInfo.energyCollectRate != null">
				${smallStoreInfo.energyCollectRate}%
				</s:if>&nbsp;
			</td>
		</tr>
		<tr>
			<td></td>
			<td class="td_title">租金单价：</td>
			<td >
				<s:if test="smallStoreInfo.rentUnitPrice != null">
				${smallStoreInfo.rentUnitPrice}元/平方.月
				</s:if>&nbsp;
			</td>
			<td class="td_title">物业收款率：</td>
			<td >
				<s:if test="smallStoreInfo.propCollectRate != null">
				${smallStoreInfo.propCollectRate}%
				</s:if>&nbsp;
			</td>
		</tr>
	</table>
	</div>
	<div id="layoutDiv" style="padding:10px 0 5px;">
	<security:authorize ifAnyGranted="A_TOTAL_DATA_QUERY">
	<table class="tb_content">
		<col width="10"/>
		<col />
		<tr>
			<td colspan="5" class="td_header"><font style="font-weight:bolder;">资料下载</font></td>
		</tr>
		<tr>
			<td></td>
			<td class="td_title" colspan="2">业态图</td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2">
				<div id="show_layoutId"></div>
			</td>
		</tr>
		<tr>
			<td></td>
			<td class="td_title" colspan="2">品牌落位图</td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2">
				<div id="show_brandLoadId"></div>
			</td>
		</tr>
		<!--<tr>
			<td></td>
			<td class="td_title" colspan="2">价格审批表</td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2">
				<div id="show_priceApproveId"></div>
			</td>
		</tr>
		-->
	</table>
	</security:authorize>
	</div>
</div>
</div>
</div>

<%@ include file="bis-manage-footer.jsp" %>

<script type="text/javascript">
isEmpty = function (str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
};

function toggleBottomType(dom,index) {
	$("#bottom-nav li").removeClass("bottom-nav-click");
	$(dom).addClass("bottom-nav-click");
	var url;
	var data = {
		bisProjectId : $("#bisProjectId").val(),
		reportDate : $("#reportDate").val()
	};
	if(index == 1) { //MALL
		url = "${ctx}/bis/bis-manage!overviewMall.action";
	} else if (index == 2) { //广告
		url = "${ctx}/bis/bis-manage!overviewMulti.action";
	}
	$.post(url, data, function(result) {
		$("#floorDiv").html(result);
	});
}

function toBisTenant(bisFloorId,bisFloorVirId) {
	var url="${ctx}/bis/bis-tenant!main.action?bisFloorId="+bisFloorId+"&bisProjectId="+$("#bisProjectId").val();
	if(bisFloorVirId){
		url += "&bisFloorVirId="+bisFloorVirId;
	}
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab("bisTenant","商业租户",url,true);
}

function toBisAD(bisFloorId) {
	var url="${ctx}/bis/bis-tenant!loadAdMain.action?bisFloorId="+bisFloorId+"&bisProjectId="+$("#bisProjectId").val();
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab("bisTenant","商业租户",url,true);
}

function adjustHeight() {
	var height = $(window).height()-225;
	//alert($("#floorDiv").height());
	//alert($("#baseInfoDiv").height());
	$("#mainDiv").height($(window).height()-38);
	if($("#floorDiv").height() > height || $("#baseInfoDiv").height() > height) {
		//if($("#footerDiv").hasClass("bottom_div")) {
		//	$("#footerDiv").removeClass("bottom_div");
		//}
		$("#footerDiv").addClass("followed_div");
	} else {
		//$("#footerDiv").removeClass("followed_div");
		$("#footerDiv").addClass("bottom_div");
	}
}

function clkFullScreen() {
	var bisProjectId = $("#bisProjectId").val();
	var url = "${ctx}/bis/bis-manage!toProjectOverview.action?bisProjectId="+$("#bisProjectId").val()+"&reportDate="+$("#reportDate").val();
	window.open(url);
}

function showLayoutFile(id,fieldId) {
	if (isEmpty(id)){
		return;
	}
	var url = _ctx+ "/app/app-attachment!bisShow.action";
	var data = {
		bizEntityId : id,
		bizModuleCd : "bisProjectLayout"
	};
	$.post(url, data, function(result) {
		$("#show_"+fieldId).html(result);
	});
}

function toReport(manageCd) {
	
	var url = "${ctx}/bis/bis-manage!layout.action?module=2&bisProjectId="+$("#bisProjectId").val()+"&reportType=store&manageCd="+manageCd;
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab("bisProjectOverview","项目报表",url,true);
}

/**
 * 更新总况数据
 */
function refreshData() {
	TB_showMaskLayer("正在更新...");	
	var url = "${ctx}/bis/bis-manage!refreshData.action";
	$.post(url, {bisProjectId: $("#bisProjectId").val(), reportDate:$("#reportDate").val()}, function(result) {
		TB_removeMaskLayer();
		TB_showMaskLayer("更新完成",1000);
	});
}

function reloadOverview() {
	
	var conditionType = $("#conditionType").val();
	if(!isEmpty(conditionType) && conditionType == 1 && (isEmpty($("#startDate").val()) || isEmpty($("#endDate").val()))) {
		alert("请选择搜索时间段");
		return;
	}
	
	var url = "${ctx}/bis/bis-manage!toProjectOverview.action?bisProjectId="+$("#bisProjectId").val()
				+"&conditionType="+$("#conditionType").val()
				+"&reportDate="+$("#reportDate").val()
				+"&startDate="+$("#startDate").val()+"&endDate="+$("#endDate").val();
	window.location = url;
}

function changeCondition(conditionType) {
	$("#conditionType").val(conditionType);
	if(conditionType==0) {
		$("#byMonth").show();
		$("#byTimePeriod").hide();
	} else {
		$("#byMonth").hide();
		$("#byTimePeriod").show();
	}
}

$(function(){

	$('#btn_changeProject').onSelect({
		muti:false,
		callback:function(project){
			if(project.projectName == '宝龙商业') {
				alert("请选择具体商业公司");
				return;
			}
			$("#bisProjectId").val(project.bisProjectId);
			reloadOverview();
		}
	});
	
});

</script>

<security:authorize ifAnyGranted="A_REPO_DETA_QUERY">
<script type="text/javascript">
	$(".link").removeClass("link").addClass("link_bis");
</script>
</security:authorize>
<security:authorize ifAnyGranted="A_TOTAL_DATA_QUERY">
<script type="text/javascript">
	showLayoutFile('${layoutId}','layoutId');
	showLayoutFile('${brandLoadId}','brandLoadId');
	//showLayoutFile('${priceApproveId}','priceApproveId');
</script>
</security:authorize>
</body>
</html>