<%@page import="com.hhz.ump.util.DateUtil"%>
<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/taglibs.jsp" %>
	<%@ include file="/common/nocache.jsp" %>
	<%@ include file="/common/global.jsp" %>
	
    <title>决策平台</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/base.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-decision.css"/>
	
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
    <script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
    <script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
    <script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
    <script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bis/bis-decision.js"></script>
    
</head>

<body>
<%
    Calendar calendar = DateUtil.getCalendar(-7);
    int year= calendar.get(Calendar.YEAR);
    int month= calendar.get(Calendar.MONTH)+1;
    int weekOfMonth=calendar.get(Calendar.WEEK_OF_MONTH);
    weekOfMonth=calendar.get(Calendar.DAY_OF_WEEK)==1?weekOfMonth-1:weekOfMonth;
%>
<div id="warp">
    <div id="header">
        <div class="title1 clearfix">
            <h2 style="width: auto;margin-right: 10px;" id="spanTitleName">销售</h2>
            <div style="float:right; height:26px; margin-right:5px; margin-top:6px;">
         		<div class="btn_green" onclick="fullScreen();">全屏</div>
    		</div>
        </div>
    </div>
    <div id="navigation">
   		<ul class="clearfix">
         	 <security:authorize ifAnyGranted="A_DEC_YYYJ_VIEW">
	         	<li onclick="loadData(this,'4');">商场经营</li>
         	 </security:authorize>
         	 <security:authorize ifAnyGranted="A_DEC_SHOP_VIEW">
	         	<li onclick="loadData(this,'3');">招商</li>
         	 </security:authorize>
         	 <security:authorize ifAnyGranted="A_DEC_CHARGE_VIEW,A_DEC_CHARGE_NEW">
	         	<li onclick="loadData(this,'2');">商业收费</li>
         	 </security:authorize>
         	 <security:authorize ifAnyGranted="A_DEC_SELL_NEW,A_DEC_SELL_COMMIT,A_DEC_SELL_CONFIRM,A_DEC_SELL_VIEW,A_DEC_SELL_QUERY,A_DEC_SELL_REJECT">
         	 	<li class="selected" onclick="loadData(this,'1');">销售</li>
         	 </security:authorize>
    	</ul>
        <input type="hidden" id="reportType" value="1"/>
    </div>
    
    
   <div class="form_body condition_panel clearfix" style="border:none; height: 29px;margin-left: 10px;">
        <form id="queryFrom" method="post">
        	<div id="topBis" style="margin-top:5px;float:left;font-weight: bolder;font-size: 15px;">
				<span>
					<input id="year" onclick="selectYMW(this,'year');" onmouseover="bindInputBlur('over');" onmouseout="bindInputBlur('out');" readonly="readonly" style="width: 50px;text-align: center;"/>
					年
				</span>
				<div id="selectYear" style="display:none;" onmouseover="bindInputBlur('over');" onmouseout="bindInputBlur('out');">
					<div class="divTitle">请选择年份</div>
					<input type="button" class="btnCancel" value="2010" onclick="clickYMW(this,'year');"/>
					<input type="button" class="btnCancel" value="2011" onclick="clickYMW(this,'year');"/>
					<input type="button" class="btnCancel" value="2012" onclick="clickYMW(this,'year');"/>
					<div style="clear: both;"></div>
					<input type="button" class="btnCancel" value="2013" onclick="clickYMW(this,'year');"/>
					<input type="button" class="btnCancel" value="2014" onclick="clickYMW(this,'year');"/>
					<input type="button" class="btnCancel" value="2015" onclick="clickYMW(this,'year');"/>
					<div style="clear: both;"></div>
					<input type="button" class="btnCancel" value="2016" onclick="clickYMW(this,'year');"/>
					<input type="button" class="btnCancel" value="2017" onclick="clickYMW(this,'year');"/>
					<input type="button" class="btnCancel" value="2018" onclick="clickYMW(this,'year');"/>
				</div>
				<span style="margin-left: 10px;">
					<input id="month" onclick="selectYMW(this,'month');" onmouseover="bindInputBlur('over');" onmouseout="bindInputBlur('out');" readonly="readonly" style="width: 50px;text-align: center;"/>
					月
				</span>
				<div id="selectMonth" style="display:none;" onmouseover="bindInputBlur('over');" onmouseout="bindInputBlur('out');">
					<div class="divTitle">请选择月份</div>
					<input type="button" class="btnCancel" value="01" onclick="clickYMW(this,'month');"/>
					<input type="button" class="btnCancel" value="02" onclick="clickYMW(this,'month');"/>
					<input type="button" class="btnCancel" value="03" onclick="clickYMW(this,'month');"/>
					<div style="clear: both;"></div>
					<input type="button" class="btnCancel" value="04" onclick="clickYMW(this,'month');"/>
					<input type="button" class="btnCancel" value="05" onclick="clickYMW(this,'month');"/>
					<input type="button" class="btnCancel" value="06" onclick="clickYMW(this,'month');"/>
					<div style="clear: both;"></div>
					<input type="button" class="btnCancel" value="07" onclick="clickYMW(this,'month');"/>
					<input type="button" class="btnCancel" value="08" onclick="clickYMW(this,'month');"/>
					<input type="button" class="btnCancel" value="09" onclick="clickYMW(this,'month');"/>
					<div style="clear: both;"></div>
					<input type="button" class="btnCancel" value="10" onclick="clickYMW(this,'month');"/>
					<input type="button" class="btnCancel" value="11" onclick="clickYMW(this,'month');"/>
					<input type="button" class="btnCancel" value="12" onclick="clickYMW(this,'month');"/>
				</div>
				<span id="weekSpan" style="margin-left: 10px;">
					第&nbsp;
					<input id="week" onclick="selectYMW(this,'week');" onmouseover="bindInputBlur('over');" onmouseout="bindInputBlur('out');" readonly="readonly" style="width: 50px;text-align: center;"/>
					周
					<%-- 一周的时间段显示 --%>
		            <label id="dataText" class="max" style="margin-left: 10px;"></label>
		            <input  type="hidden" id="startDay" name="startDay"/>
		            <input type="hidden" id="endDay" name="endDay"/>
				</span>
				<div id="selectWeek" style="display:none;" onmouseover="bindInputBlur('over');" onmouseout="bindInputBlur('out');">
					<div class="divTitle">请选择周</div>
					<input type="button" class="btnCancel" value="1" onclick="clickYMW(this,'week');"/>
					<input type="button" class="btnCancel" value="2" onclick="clickYMW(this,'week');"/>
					<input type="button" class="btnCancel" value="3" onclick="clickYMW(this,'week');"/>
					<div style="clear: both;"></div>
					<input type="button" class="btnCancel" value="4" onclick="clickYMW(this,'week');"/>
					<input type="button" class="btnCancel" value="5" onclick="clickYMW(this,'week');"/>
				</div>
				
				<span id="sellCountSpan" style="margin-left: 20px;font-weight: normal;font-size: 15px;">
					本月签约&nbsp;<span id="sell_month_qy_count" style="font-weight: bolder;color:#B4BDEE;"></span>&nbsp;万元，
					年累计&nbsp;<span id="sell_year_count" style="font-weight: bolder;color:#B4BDEE;"></span>&nbsp;万元
				</span>
			</div>
			
        	<%-- 销售情况 --%>
			<div id="statusDiv" style="text-align: right;margin-top:0px;float: right;margin-right: 10px;">
				<span id="status" style="color: red; margin-right: 10px; font-size: 14px;padding-left: 5px; line-height: 28px; font-style: normal;"></span>
            	<security:authorize ifAllGranted="A_DEC_SELL_REJECT">
                    <button id="rejectReportBut" class="red"
                            style="width: 76px; display: none; float: right; margin-right: 5px;"
                            type="button">驳回月报
                    </button>
                </security:authorize>
				<security:authorize ifAllGranted="A_DEC_SELL_COMMIT">
                	<button id="submitReportBut" class="blue"
                        style="width: 76px; display: none; float: right; margin-right: 5px;"
                        type="button" onclick="">提交月报
                	</button>
            	</security:authorize>
                <security:authorize ifAllGranted="A_DEC_SELL_CONFIRM">
                	<button id="confirmReportBut" class="green"
                        style="width: 76px; display: none; float: right; margin-right: 5px;"
                        type="button">确认月报
                	</button>
           	 	</security:authorize>
            	<security:authorize ifAllGranted="A_DEC_SELL_NEW">
					<button type="button" class="green" 
							style="width: 76px; display: none; float: right; margin-right: 5px;"
					        id="addReportBut">新增月报
				    </button>
				</security:authorize>
        	</div>
        	<%-- 商业收费情况 --%>
        	<div id="statusDiv2" style="width:500px;text-align: right;margin-top:0px;float: right;display: none;">
        		<security:authorize ifAllGranted="A_DEC_CHARGE_NEW">
					<button type="button" class="green" 
							style="width: 76px;float: right; margin-right: 5px;"
					        id="addReportBut2">新增月报
				    </button>
				</security:authorize>
        	</div>
        </form>
    </div>
    
    <%-- 结果列表div --%>
    <div id="dataDiv" style="width:100%;height:100%;margin-top: 10px;"><!-- style="position: relative;height:500px;" -->
        <div style="background: #FFF;fong-size:14px;font-weight:bold;width:100%;height:300px; position: absolute;top:0px; text-align:center;line-height: 300px;overflow: hidden;">
			请选择查看的报表！
        </div>
    </div>
    <div id="navigation" style="float: right;margin-right: 10px;">
		<ul class="clearfix" id="month_ul">
		     <li onclick="getLastOrNextMonth('last');" style="cursor: pointer;width: 90px;">上一月</li>
		     <li onclick="getLastOrNextMonth('next');" style="cursor: pointer;width: 90px;">下一月</li>
		</ul>
		<ul class="clearfix" id="week_ul" style="display: none;">
		     <li onclick="getLastOrNextWeek('last');" style="cursor: pointer;width: 90px;">上一周</li>
		     <li onclick="getLastOrNextWeek('next');" style="cursor: pointer;width: 90px;">下一周</li>
		</ul>
	</div>
</div>

<script type="text/javascript">
$(function(){
	//初始化年月
	initYMW();
	//注册按钮点击事件
	initButClick();
	//默认加载销售情况报表
	loadData(null,1);
});

/**
 * 初始化年月周
 */
function initYMW(){
    $("#year").val("<%=year%>");
    $("#month").val("<%=month%>");
    $("#week").val("<%=weekOfMonth%>");
    setDateText("<%=year%>", "<%=month%>", "<%=weekOfMonth%>",true);
}

/**
 * 具有新增、确认、提交、驳回权限的人可以看到状态和按钮
 */
function setStatus2(){
	// status0在bis-decision-sellCondition.jsp中
    var status = $("#status0").val(); 
    if(status == null || status == undefined || status == ''){
    	status = -1;
    }
	<security:authorize ifAnyGranted="A_DEC_SELL_NEW,A_DEC_SELL_REJECT,A_DEC_SELL_CONFIRM,A_DEC_SELL_COMMIT">
		setStatus(status);
	</security:authorize>
}
</script>
</body>
</html>