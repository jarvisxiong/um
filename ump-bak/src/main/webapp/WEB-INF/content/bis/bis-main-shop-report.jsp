<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.hhz.ump.util.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" style="overflow: auto;">
<head>
    <%@ include file="/common/taglibs.jsp" %>
    <%@ include file="/common/nocache.jsp" %>
    <%@ include file="/common/global.jsp" %>

    <link href="${ctx}/resources/css/bis/bis-main-shop-report.css" media="screen"
          rel="stylesheet" type="text/css" />
    <link href="${ctx}/resources/js/loadMask/jquery.loadmask.css"
          media="screen" rel="stylesheet" type="text/css" />
    <link href="${ctx}/resources/css/bis/ymPrompt.css" media="screen"
          rel="stylesheet" type="text/css" />
    <link href="${ctx}/resources/css/mes/thickbox.css" media="screen" rel="stylesheet" type="text/css"/>
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
    <script src="${ctx}/resources/js/common/MaskLayer.js" type="text/javascript"></script>
    <script src="${ctx}/resources/js/mes/jquery-powerdesk.js"
            type="text/javascript"></script>
    <script src="${ctx}/js/prompt/ymPrompt.js" type="text/javascript"></script>
    <script src="${ctx}/js/jqueryplugin/chilltip.js"
            type="text/javascript" ></script>
    <title>宝龙商业主力店工作周报</title>
</head>
<body>
<%
    Calendar calendar = DateUtil.getCalendar(-7);
    int year= calendar.get(Calendar.YEAR);
    int month= calendar.get(Calendar.MONTH)+1;
    int weekOfMonth=calendar.get(Calendar.WEEK_OF_MONTH);
    weekOfMonth=calendar.get(Calendar.DAY_OF_WEEK)==1?weekOfMonth-1:weekOfMonth;

%>
<div id="warp" >
    <!--div id="header">
	        <div class="title1 clearfix">
	            <h2>宝龙商业主力店招商周报</h2>
	            <div class="btns">
                    <button class="green qiehuan" type="button"
                            style="float: right; margin-top: 7px; margin-left: 10px;width: 100px;">经营日报表</button>
					<security:authorize ifAllGranted="A_RENTAL_C_RATE_VIEW">
	            		<button class="green zufei" style="width:100px;" type="button">租费收缴率</button>
	            	</security:authorize>
                    <button class="green qiehuan" type="button"
                            style="float: right; margin-top: 7px; margin-left: 10px;width: 100px;">项目列表
                    </button>
          	    </div>
	         </div>
	     </div-->
    <div class="form_body condition_panel clearfix" style="background: #FFF;border: 0px solid #8C8F94;padding-left:0px;height:29px; ">
        <form id="queryFrom" method="post">
            <select id="year"  class="box date" style="width: 65px;">
                <c:forEach begin="2011" end="<%=year+2%>" step="1" var="b">
                    <option  value="${b}">${b}</option>
                </c:forEach>
            </select>
            <label >年</label>
            <select id="month" class="box date" style="width: 50px;margin-left: 10px;">
                <c:forEach begin="1" end="12" step="1" var="b">
                    <option  value="${b}">${b}</option>
                </c:forEach>
            </select>
            <label>月</label>
            <label style="margin-left: 10px;">第</label>
            <select id="weekOfMonth" class="box date" style="width: 40px; OVERFLOW: yes" >
                <c:forEach begin="1" end="5" step="1" var="b">
                    <option  value="${b}">${b}</option>
                </c:forEach>
            </select>
            <label>周</label>
            <label id="dataText" class="max" style="margin-left: 10px;"></label>
            <input  type="hidden" id="startDay" name="startDay"/>
            <input type="hidden" id="endDay" name="endDay"/>

            <div style="text-align:right; margin-top:0px; border: 0px solid #cccccc; float: right;">
                <span id="status" style="color:red; margin-right: 10px;font-size: 14px;height: 26px; padding-left:5px;line-height: 24px; font-style: normal;"></span>
                <security:authorize ifAllGranted="A_BIS_MAIN_REJECT">
                    <button id="rejectReportBut" class="red"  style="width:76px; display:none; float: right;margin-right: 5px;" type="button">驳回周报</button>
                </security:authorize>
                <security:authorize ifAllGranted="A_BIS_MAIN_AFFIRM">
                    <button id="affirmReportBut" class="green"  style="width:76px; display:none; float: right;margin-right: 5px;" type="button">确认周报</button>
                </security:authorize>
                <security:authorize ifAllGranted="A_BIS_MAIN_SUBMIT">
                    <button id="submitReportBut" class="blue" style="width:76px; display:none; float: right; margin-right: 5px;" type="button">提交周报</button>
                </security:authorize>
                <security:authorize ifAllGranted="A_BIS_MAIN_NEW">
                    <button id="addReportBut"   class="blue" style="width:76px; display:none; float: right;margin-right: 5px;"  type="button" >新增周报</button>
                </security:authorize>
            </div>
        </form>
    </div>

    <div id="body" style="margin:0px;"></div>
</div>
<script type="text/javascript">
var modifyAble = false;
<security:authorize ifAnyGranted="A_BIS_MAIN_SUBMIT,A_BIS_MAIN_NEW,A_BIS_MAIN_AFFIRM,A_BIS_MAIN_REJECT">
modifyAble = true;
</security:authorize>
var A_BIS_MAIN_NEW=false;
<security:authorize ifAllGranted="A_BIS_MAIN_NEW">
A_BIS_MAIN_NEW = true;
</security:authorize>
var default_height=100;
var _ctx="${ctx}";
var affirmBut=true;
var statusTxt="已提交";
var status = -1;
function setStatus(owner){
    if(status===-1){
        affirmBut=true;
        $("#status").text("当前状态：待新增");
        $("#addReportBut").show();
        $("#affirmReportBut").hide();
        $("#rejectReportBut").hide();
        $("#submitReportBut").hide();
    }
    if(status===0){
        affirmBut=true;
        $("#status").text("当前状态：新增中");
        $("#submitReportBut").show();
        $("#addReportBut").hide();
        $("#affirmReportBut").hide();
        $("#rejectReportBut").hide();
    }
    if(status===1){
        affirmBut=false;
        statusTxt="已提交";
        $("#status").text("当前状态：已提交");
        $("#affirmReportBut").show();
        $("#rejectReportBut").show();
        $("#submitReportBut").hide();
        $("#addReportBut").hide();
    }
    if(status===2){
        affirmBut=false;
        statusTxt="已确认";
        $("#status").text("当前状态：已确认");
        $("#addReportBut").hide();
        $("#submitReportBut").hide();
        $("#affirmReportBut").hide();
        $("#rejectReportBut").show();
        updateUpdator();
    }
    if(status===3){
        affirmBut=true;
        $("#status").text("当前状态：被驳回");
        $("#submitReportBut").show();
        $("#addReportBut").hide();
        $("#affirmReportBut").hide();
        $("#rejectReportBut").hide();
    }

}
$(document).ready(function(){
    $.ajaxSetup({cache:false});
    init();
    $("#queryFrom select[class=box date]").change(function(){
        setDateText($("#year").val(), $("#month").val(), $("#weekOfMonth").val(),true);
    });

    $("#queryBut").click(function(){
        loadBody();
    });

    $("#addReportBut").click(function(){
        setReportStatusFlg("新增时会将上周数据作为本周的初始数据，是否继续？", "生成周报", "add");

    });

    $("#submitReportBut").click(function(){
        setReportStatusFlg("您确定要提交本期招商周报吗？", "提交周报", "submit");
    });

    $("#affirmReportBut").click(function(){
        setReportStatusFlg("您确定固化本期招商周报吗？<br/>固化后本期招商周报不能再修改！", "固化周报", "affirm");
    });

    $("#rejectReportBut").click(function(){
        setReportStatusFlg("您确定要驳回本期招商周报吗？<br/>驳回后本期招商周报可以再修改！", "驳回周报", "reject");
    });
});


function setReportStatusFlg(message1,message2,func){

    ymPrompt.confirmInfo({
        message:"<p style='margin-top:-25px;display:block;line-height: 20px;'>"+message1+"</p>",
        width:350,
        height:160,
        winPos:[($("body").width()-350)/2,60],
        title:"消息提示！",
        closeBtn:true,
        handler:function (btn) {
            if (btn == 'ok') {
                TB_showMaskLayer("正在"+message2+"请稍后...");
                var saveParam={
                    'startDay':$("#queryFrom input[name=startDay]").val(),
                    'endDay':$("#queryFrom input[name=endDay]").val()
                };
                $.post("${ctx}/bis/bis-main-shop-report!"+func+".action",saveParam, function(result) {

                    $("#body").html(result);
                    TB_removeMaskLayer();
                    setStatus();
                });
            }
            ymPrompt.close();
        }
    });
}

function init(){
    $("#year").val("<%=year%>");
    $("#month").val("<%=month%>");
    $("#weekOfMonth").val("<%=weekOfMonth%>");
    setDateText("<%=year%>", "<%=month%>", "<%=weekOfMonth%>",true);
}

function loadBody(){

    TB_showMaskLayer("正在搜索请稍后...");
    var queryParam={
        //'projectType':$("#projectType").val(),
        //'projectId':$("#projectId").val(),
        'startDay':$("#queryFrom input[name=startDay]").val(),
        'endDay':$("#queryFrom input[name=endDay]").val()
    };
    $.post("${ctx}/bis/bis-main-shop-report!list.action",queryParam, function(result) {
        $("#body").html(result);
        TB_removeMaskLayer();
        setStatus();
    });
}
/**更新审核人 单独方法*/
function updateUpdator(){
    var params={
        'startDay':$("#queryFrom input[name=startDay]").val(),
        'endDay':$("#queryFrom input[name=endDay]").val()
    };
    $.get("${ctx}/bis/bis-main-shop-report!reGetUpdator.action",params,function(data){
        if(data==null||data==""||data.length<1){
            return;
        }else{
            $("#status").text($("#status").text()+"（"+data+"）");
        }
    });
}
function  isEdit(){
    return "<%=year%>"===$("#year").val() && "<%=month%>"===$("#month").val() && "<%=weekOfMonth%>"==$("#weekOfMonth").val() ;
}
function setDateText(year,month,weekOfMonth,isload){
    var queryParam={
        'year':year,
        'month':month,
        'weekOfMonth':weekOfMonth
    };
    $.post("${ctx}/bis/bis-main-shop-report!getStartEndDay.action",queryParam, function(result) {

        if(result){
            var dates =result.split("~");
            if(dates.lenght=2){
                var html =  '<font color="red">'+dates[0]+'</font>&nbsp;&nbsp;至&nbsp;&nbsp;<font color="red">'+dates[1]+'</font>';
                $("#queryFrom input[name=startDay]").val(dates[0]);
                $("#queryFrom input[name=endDay]").val(dates[1]);
                $("#dataText").html(html);
                if(isload){
                    loadBody();
                }
            }
        }

    });
}

//项目切换
var qiehuans = $("button.qiehuan").click(function () {
    var index = qiehuans.index(this);
    var tab = parent.TabUtils;
    if (index == 1) {
        var url = "${ctx}/bis/bis-manage.action?report=false";
        if (tab == null) {
            window.open(url);
        } else {
            if($("#195_tab",parent.document).size()!=0){
                tab.closeTab({data:{tabId:"195"}});
            }
            tab.newTab("195", "项目列表", url, true);
        }
    } else {
        var url = "${ctx}/bis/bis-manage.action";
        if (tab == null)
            window.open(url);
        else {
            if($("#195_tab",parent.document).size()!=0){
                tab.closeTab({data:{tabId:"195"}});
            }
            tab.newTab("195", "经营报表", url, true);
        }

    }
});
/**租费按钮单机事件*/
$("button.zufei").bind("click",function(){
    var tab = parent.TabUtils;
    var url = "${ctx}/bis/bis-rental-collection-rate.action";
    if (tab == null) {
        window.open(url);
    } else {
        if($("#195_tab",parent.document).size()!=0){
            tab.closeTab({data:{tabId:"195"}});
        }
        tab.newTab("195", "租费收缴率", url, true);
    }
});
function resize() {
    var container = $.browser.msie ? $("html") : $(window);
    $("#body").height(container.height() - 100) ;
}

//高度设置
//setInterval(resize, 1000);
//resize();
</script>
</body>

</html>