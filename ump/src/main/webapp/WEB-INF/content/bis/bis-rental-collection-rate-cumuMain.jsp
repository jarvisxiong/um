<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="com.hhz.ump.util.DateUtil"%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@page import="java.util.Date"%><html xmlns="http://www.w3.org/1999/xhtml" style="overflow: auto">
<head>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@ include file="/common/global.jsp"%>

<link href="${ctx}/resources/js/loadMask/jquery.loadmask.css"
	media="screen" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/css/bis/ymPrompt.css" media="screen"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/css/mes/thickbox.css" media="screen"
	rel="stylesheet" type="text/css" />
<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/jquery/jquery.select.js"
	type="text/javascript"></script>
<script src="${ctx}/resources/js/datePicker/WdatePicker.js"
	type="text/javascript"></script>
<script src="${ctx}/resources/js/xheditor/xheditor-zh-cn.min.js"
	type="text/javascript"></script>
<script src="${ctx}/resources/js/common/MaskLayer.js"
	type="text/javascript"></script>
<script src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"
	type="text/javascript"></script>
<script src="${ctx}/resources/js/common/MaskLayer.js"
	type="text/javascript"></script>
<script src="${ctx}/resources/js/mes/jquery-ump.js"
	type="text/javascript"></script>
<script src="${ctx}/js/prompt/ymPrompt.js" type="text/javascript"></script>
<script src="${ctx}/js/jqueryplugin/chilltip.js" type="text/javascript"></script>
<link href="${ctx}/resources/css/bis/bis-main-shop-report.css" media="screen" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/css/bis/bis-rental-collection-rate.css" media="screen" rel="stylesheet" type="text/css" />
<title>宝龙商业 租费收缴率</title>
<script type="text/javascript">
/**页面作用域变量*/
var year;
var tyear;
var month;
$(document).ready(function(){
	//绑定事件
	$("#cumuPageUpId").bind("click",function(){
		modiYearVal("up");
		initData();
		$("#datePickerBox").val($("#theMonthId").val());
	});
	$("#cumuPageDownId").bind("click",function(){
		modiYearVal("down");
		initData();
		$("#datePickerBox").val($("#theMonthId").val());
	});
	/**加载数据*/
	initData();
});
/**加载页面集合数据*/
function initData(){
	var url = "${ctx}/bis/bis-rental-collection-rate!cumuList";
	var param = $("#cumuSearchForm").serialize();
	TB_showMaskLayer("正在搜索...");
	$.post(url,param,function(data){
		TB_removeMaskLayer();
		$("#body table tr:gt(2)").remove();
		$("#body table").append(data);
		/**处理合计*/
		execHeji();
	});
}
/**根据时间跳转*/
function jumpDataByDate(demo){
	var tdate = $(demo).val();
	$("#theMonthId").val(modiDate(tdate,0,'low'));
	$("#curMonthId").val(modiDate(tdate,0,'low'));
	$("#lastMonthId").val(modiDate(tdate,1,'low'));
	$("#beforeMonthId").val(modiDate(tdate,2,'low'));
	$("#theMonthspan").html(modiDate(tdate,0,'low'));
	$("#curMonthspan").html(modiDate(tdate,0,'low'));
	$("#lastMonthspan").html(modiDate(tdate,1,'low'));
	$("#beforeMonthspan").html(modiDate(tdate,2,'low'));
	/**数据更新 */
	initData();
}
/**计算合计*/
function execHeji(){
	var sums = [0,0,0,0,0,0,0,0,0];
	var sums2 = [0,0,0,0,0,0,0,0,0];
	var trs = $(".editReport").each(function () {
        var tds = $("td", this).each(function (index) {
            if ($(this).text() !== "" && $(this).text() !== "/" && $(this).text() !== "0" && $(this).text() !== "%") {
                var val1 = toFloat($(this).attr("ys"));
                var val2 = toFloat($(this).attr("ss"));
                sums[index] = sums[index] + val1;
                sums2[index] = sums2[index] + val2;
            }
            if($(this).attr("ss")!=""&&$(this).attr("ss")!=undefined){
            	$(this).attr("title","应收(万元):"+$(this).attr("ys")+",实收(万元):"+$(this).attr("ss"));
            }
        });
    });
	$.each($("#body .report tr:eq(3)").find("td"),function(i){
		var t = changeTwoDecimal_f(sums2[i+1]*100/(sums[i+1]==0?1:sums[i+1]));
		if(changeTwoDecimal_f(t)>0){
			$(this).text(t+"%");
		}else{
			$(this).html("0 &nbsp;");
		}
	});
}
/**保留2位小数*/
function changeTwoDecimal_f(x) {
    var f_x = parseFloat(x);
    if (isNaN(f_x)) {
        return 0;
    }
    var f_x = Math.round(x * 100) / 100;
    var s_x = f_x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2) {
        s_x += '0';
    }
    return s_x;
}
/**转为浮点类型*/
function toFloat(value){
	var demo = $.trim(value);
	if(demo==null||demo==undefined||demo==""||demo=="null"){
		return 0;
	}else{
		return parseFloat(demo);
	}
}
/**修改传入年份*/
function modiYearVal(demo){
	var cth = $.trim($("#curMonthId").val());
	if(demo=="up"){
		$("#theMonthId").val(modiDate(cth,1,'up'));
		$("#curMonthId").val(modiDate(cth,1,'up'));
		$("#lastMonthId").val(modiDate(cth,0,'up'));
		$("#beforeMonthId").val(modiDate(cth,-1,'up'));
		$("#theMonthspan").html(modiDate(cth,1,'up'));
		$("#curMonthspan").html(modiDate(cth,1,'up'));
		$("#lastMonthspan").html(modiDate(cth,0,'up'));
		$("#beforeMonthspan").html(modiDate(cth,-1,'up'));
	}else{
		$("#theMonthId").val(modiDate(cth,1,'low'));
		$("#curMonthId").val(modiDate(cth,1,'low'));
		$("#lastMonthId").val(modiDate(cth,2,'low'));
		$("#beforeMonthId").val(modiDate(cth,3,'low'));
		$("#theMonthspan").html(modiDate(cth,1,'low'));
		$("#curMonthspan").html(modiDate(cth,1,'low'));
		$("#lastMonthspan").html(modiDate(cth,2,'low'));
		$("#beforeMonthspan").html(modiDate(cth,3,'low'));
	}
}
/**计算时间  年月 增减数量 加减类型*/
function modiDate(date,count,type){
	var year = date.substr(0,4);
	var month = date.substr(5,2);
	var tyear = 0;
	var tmonth = 0;
	if(type=="up"){
		tmonth = parseFloat(month)+count;
		if(tmonth>12){
			tmonth = 13-tmonth;
			tyear = parseFloat(year)+1;
		}else{
			tyear =year;
		}
	}else if(type=="low"){
		tmonth = parseFloat(month) - count;
		if(tmonth<1){
			tmonth = 12+tmonth;
			tyear = parseFloat(year) - 1;
		}else{
			tyear = year;
		}
	}
	if(tmonth<10){
		tmonth = "0"+tmonth;
	}
	if("00" == tmonth){
		tmonth = "01";
	}
	return tyear +"-"+ tmonth;
}
</script>
<%
Date curDate = DateUtil.getCurrtDate();
double dyear =Double.parseDouble(DateUtil.getCurrYear());
int year = 2011;
int month = curDate.getMonth(); //去当前的上一月
String smonth =""+month;
String lastmonth =""+(month-1);
int bmonth = month -2;
String beforemonth = ""+bmonth;
if(month<10){
	smonth = "0"+month;
}
if(month==1){
	lastmonth = "12";
	dyear = dyear-1;
}
if(bmonth ==0){
	beforemonth = "12";
	dyear = dyear -1;
}
if(bmonth ==-1){
	beforemonth = "11";
	dyear = dyear -1;
}
year = (int)dyear;
if(lastmonth.length()==1){
	lastmonth = "0"+lastmonth;
}
if(beforemonth.length()==1){
	beforemonth = "0"+beforemonth;
}
%>

</head>
<body>
	<div id="warp">
	<div id="mainContent">
	<div >
	<form action="" id="cumuSearchForm">
		<input type="hidden" name="theMonth" id="theMonthId" value="<%=year %>-<%=smonth %>"/>
		<input type="hidden" name="curMonth" id="curMonthId" value="<%=year %>-<%=smonth %>"/>
		<input type="hidden" name="lastMonth" id="lastMonthId" value="<%=year %>-<%=lastmonth %>"/>
		<input type="hidden" name="beforeMonth" id="beforeMonthId" value="<%=year %>-<%=beforemonth %>"/>
	</form>
	</div>
	</div>
	<div id="body">
		<div class="searchTitle">
			<label>正在查看：<input type="text" class="datePickerBox" id="datePickerBox" readonly="readonly" onchange="jumpDataByDate(this)" onclick="WdatePicker({dateFmt:'yyyy-MM',onpicked:function(){jumpDataByDate(this);}})" value="<%=year%>-<%=smonth %>" /></label>
		   <div style="float:right;padding-right:5px;color:#FF6500;"> 无锡项目为实时取值，其余项目由总部定期录入</div>
		</div>
		<table class="report" border="0" cellpadding="0" cellspacing="0" style="width:100%;border-top: 1px solid #cccccc;border-right: 1px solid #cccccc;">
		<colgroup>
	        <col width="9%"/>
	        <col width="11%" />
	        <col width="20%"/>
	        <col width="20%"/>
	        <col width="20%"/>
	        <col width="20%"/>
	    </colgroup>
	    	<tr>
			  <th align="center" valign="middle" style="border-right: 0px;">
					<span class="pagebtn" id="cumuPageUpId">后一月</span>
			  </th>
			  <th colspan="4" align="center" style="border-left: 0px;border-right: 0px;">
			  		两费收缴率汇总
			  </th>
			  <th align="center" valign="middle" style="border-left: 0px;">
			  		<span class="pagebtn" id="cumuPageDownId" style="padding: 0px;float: right;">前一月</span>
			  </th>
			</tr>
			<tr>
			  <th rowspan="2" colspan="2" align="center">商业公司</th>
			  <th colspan="2" align="center" style="background-color: #2d8bef;color: white;">
			  		累计收缴率[<span id="theMonthspan"><%=year%>-<%=smonth %></span>]
			  </th>
			   <th colspan="2" align="center">
			  		月收缴率[<span id="curMonthspan"><%=year%>-<%=smonth %></span>]
			  </th>
			</tr>
			<tr>
			  <th align="center">租金收缴率</th>
			  <th align="center">物业收缴率</th>
			  <th align="center">租金收缴率</th>
			  <th align="center">物业收缴率</th>
			</tr>
		</table>
	</div>
	</div>
</body>
</html>