<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/common/TreePanel.css" rel="stylesheet"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cont/cont.css" />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/highcharts/exporting.js"></script>	
</head>
<body>
<div class="title_bar">
	<input type="hidden" id="ledgerType" name="ledgerType" value="${ledgerType}"/>
	<div class="fLeft">
		请选择项目：
		<s:select list="mapContProject" listKey="key" listValue="value" id="selecProjectCd" cssStyle="cursor: pointer; font-size: 12px;" onchange="changeProject();"/>
	</div>
	<div class="fLeft">
		<input type="button"  onclick="changeContType(this);" id="statiContProc"    class="fLeft btn_new btn_green_new" style="width:80px;" id="li_cgt_32" value="合同进度"/>
		<input type="button"  onclick="changeContType(this);" id="statiContStatus"  class="fLeft btn_new btn_green_new" style="width:80px;" id="li_cgt_31" value="合同状态"/>
		<input type="button"  onclick="changeContType(this);" id="statiContTotal"   class="fLeft btn_new btn_green_new" style="width:80px;" id="li_cgt_11" value="合同金额"/>
		<input type="button"  onclick="changeContType(this);" id="statiContType"    class="fLeft btn_new btn_green_new" style="width:80px;" id="li_cgt_01" value="合同份数"/>
	</div>
	 <input type="button" class="fRight btn_new btn_refresh_new" onclick="window.location.href=location.href" value="刷新" />
</div>
<div id="container" style="width: 100%; height: 400px; margin-top:40px;">

</div>

<script type="text/javascript">
var chart;
var nowClickBtn;
$(function(){
	$("#statiContProc").click();
	//$("#statiContProc").click();
	//nowClickBtn ="statiContProc";
});
function changeProject(dom){
	$(".btn_blue_new").click();
}
function changeContType(dom){
	
	$(dom).removeClass('btn_green_new').addClass('btn_blue_new');
	$(dom).siblings().removeClass('btn_blue_new').addClass('btn_green_new');
	
	var actiName=$(dom).attr("id");
	var showName =$(dom).text();
	var projectCd=$("#selecProjectCd").val();
	var ledgerType=$("#ledgerType").val();
	//$(dom).attr('class','cont_cgt_click');
	//if(actiName!=nowClickBtn){
		//$("#"+nowClickBtn).attr('class','cont_cgt_li');
		//nowClickBtn=actiName;
	//}
	var url ="${ctx}/cont/cont-ledger!"+actiName+".action"+"?projectCd="+projectCd+"&ledgerType="+ledgerType;
	$.post(url, function(result){
		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container',
				plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow: false
			},
			title: {
				text: showName,
				style: {font: 'normal 20px 宋体'}
			},
			tooltip: {
				formatter: function() {
					return '<b>'+ this.point.name +'</b>: '+ this.y +' %';
				}
			},
			plotOptions: {
				pie: {
					allowPointSelect: true,
					cursor: 'pointer',
					dataLabels: {
						enabled: true,
						color: '#000000',
						style: {font: 'normal 12px 宋体'},
						connectorColor: '#000000',
						formatter: function() {
							return '<b>'+ this.point.name +'</b>: '+ this.y +' %';
						}
					}
				}
			},
		    series:[{
		    	type: 'pie',
				name: 'Browser share',
		    	data:eval(result)
		    }]
		    	
		});
	});
}
</script>
</body>
</html>