<%@page contentType="text/html;charset=UTF-8" %>
<%@page
        import="com.hhz.ump.util.DateUtil" %>
<%@page import="java.util.Calendar" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" style="overflow: auto">
<head>
    <%@ include file="/common/taglibs.jsp" %>
    <%@ include file="/common/nocache.jsp" %>
    <%@ include file="/common/global.jsp" %>

    <link href="${ctx}/resources/css/bis/bis-main-shop-report.css"
          media="screen" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/resources/js/loadMask/jquery.loadmask.css"
          media="screen" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/resources/css/bis/ymPrompt.css" media="screen"
          rel="stylesheet" type="text/css"/>
    <link href="${ctx}/resources/css/mes/thickbox.css" media="screen"
          rel="stylesheet" type="text/css"/>
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
    <link href="${ctx}/resources/css/bis/bis-rental-collection-rate.css"
        media="screen" rel="stylesheet" type="text/css"/>
    <title>宝龙商业 租费收缴率</title>
</head>
<body>
<%
    Calendar calendar = DateUtil.getCalendar(-7);
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
    int curDate = calendar.getTime().getDate();
    weekOfMonth = calendar.get(Calendar.DAY_OF_WEEK) == 1 ? weekOfMonth - 1 : weekOfMonth;
    if (curDate <= 15) {
        weekOfMonth = 1;
    } else {
        weekOfMonth = 2;
    }
%>
<div id="warp">
    <div id="header" class="none">
        <div class="title1 clearfix">
            <h2>租费收缴率周报</h2>

            <div class="btns">
                <button class="green qiehuan" type="button"
                        style="float: right; margin-top: 7px; margin-left: 10px; width: 120px;">主力店招商周报
                </button>
                <button class="green qiehuan" type="button"
                        style="float: right; margin-top: 7px; margin-left: 10px; width: 100px;">经营日报表
                </button>
                <button class="green qiehuan" style="width: 100px;" type="button">项目列表</button>
            </div>
        </div>
    </div>
    <div class="form_body condition_panel clearfix"
         style="background: #FFF;border:none; height: 29px; padding: 0px;">
        <form id="queryFrom" method="post"><select id="year"
                                                   class="box date" style="width: 65px;">
            <c:forEach begin="2011" end="<%=year+3%>" step="1" var="b">
                <option value="${b}">${b}</option>
            </c:forEach>
        </select> <label>年</label> <select id="month" class="box date"
                                           style="width: 50px; margin-left: 10px;">
            <c:forEach begin="1" end="12" step="1" var="b">
                <option value="${b}">${b}</option>
            </c:forEach>
        </select> <label>月</label> <label style="margin-left: 10px;">第</label> <select
                id="weekOfMonth" class="box date" style="width: 40px; OVERFLOW: yes">
            <c:forEach begin="1" end="2" step="1" var="b">
                <option value="${b}">${b}</option>
            </c:forEach>
        </select> <label>个双周</label> <label id="dataText" class="max"
                                            style="margin-left: 10px;display:none"></label> <input type="hidden"
                                                                                                   id="startDay"
                                                                                                   name="startDay"/>
            <input type="hidden" id="endDay" name="endDay"/>

            <div
                    style="text-align: right; margin-top: 0px; border: 0px solid #cccccc; float: right;">
<span id="status"
      style="color: red; margin-right: 10px; font-size: 14px; height: 26px; padding-left: 5px; line-height: 24px; font-style: normal;"></span>
                <security:authorize ifAllGranted="A_RENTAL_C_RATE_REF">
                    <button id="rejectReportBut" class="red"
                            style="width: 76px; display: none; float: right; margin-right: 5px;"
                            type="button">驳回周报
                    </button>
                </security:authorize> <security:authorize ifAllGranted="A_RENTAL_C_RATE_CFM">
                <button id="affirmReportBut" class="green"
                        style="width: 76px; display: none; float: right; margin-right: 5px;"
                        type="button">确认周报
                </button>
            </security:authorize> <security:authorize ifAllGranted="A_RENTAL_C_RATE_SUB">
                <button id="submitReportBut" class="blue"
                        style="width: 76px; display: none; float: right; margin-right: 5px;"
                        type="button">提交周报
                </button>
            </security:authorize> <security:authorize ifAllGranted="A_RENTAL_C_RATE_NEW">
                <button id="addReportBut" class="blue"
                        style="width: 76px; display: none; float: right; margin-right: 5px;"
                        type="button">新增周报
                </button>
            </security:authorize></div>
        </form>
    </div>
    <div id="body" style="margin: 0px;padding-right:10px;"></div>
</div>
<script type="text/javascript">

var A_BIS_MAIN_NEW = false;
<security:authorize ifAllGranted="A_BIS_MAIN_NEW">
A_BIS_MAIN_NEW = true;
</security:authorize>
var default_height = 100;
var _ctx = "${ctx}";
var affirmBut = true;
var statusTxt = "已提交";
var status = -1;
function setStatus(owner) {
    if (status === -1) {
        affirmBut = true;
        $("#status").text("当前状态：待新增");
        $("#addReportBut").show();
        $("#affirmReportBut").hide();
        $("#rejectReportBut").hide();
        $("#submitReportBut").hide();
    }
    if (status === 0) {
        affirmBut = true;
        $("#status").text("当前状态：新增中");
        $("#submitReportBut").show();
        $("#addReportBut").hide();
        $("#affirmReportBut").hide();
        $("#rejectReportBut").hide();
    }
    if (status === 1) {
        affirmBut = false;
        statusTxt = "已提交";
        $("#status").text("当前状态：已提交");
        $("#affirmReportBut").show();
        $("#rejectReportBut").show();
        $("#submitReportBut").hide();
        $("#addReportBut").hide();
    }
    if (status === 2) {
        affirmBut = false;
        statusTxt = "已确认";
        $("#status").text("当前状态：已确认");
        $("#addReportBut").hide();
        $("#submitReportBut").hide();
        $("#affirmReportBut").hide();
        $("#rejectReportBut").show();
        updateUpdator();
    }
    if (status === 3) {
        affirmBut = true;
        $("#status").text("当前状态：被驳回");
        $("#submitReportBut").show();
        $("#addReportBut").hide();
        $("#affirmReportBut").hide();
        $("#rejectReportBut").hide();
    }
    /**每次计算*/
    calcModiList();
}
$(document).ready(function () {
    $.ajaxSetup({cache:false});
    init();
    $("#queryFrom select[class=box date]").change(function () {
        setDateText($("#year").val(), $("#month").val(), $("#weekOfMonth").val(), true);
    });

    $("#queryBut").click(function () {
        loadBody();
    });

    $("#addReportBut").click(function () {
        setReportStatusFlgNew("新增时会将上双周数据作为本双周的初始数据，是否继续？", "生成周报", "add");

    });

    $("#submitReportBut").click(function () {
        setReportStatusFlg("您确定要提交本期周报吗？", "提交周报", "submit");
    });

    $("#affirmReportBut").click(function () {
        setReportStatusFlg("您确定固化本期周报吗？<br/>固化后本期周报不能再修改！", "固化周报", "affirm");
    });

    $("#rejectReportBut").click(function () {
        setReportStatusFlg("您确定要驳回本期周报吗？<br/>驳回后本期周报可以再修改！", "驳回周报", "reject");
    });
});
/**填充背景颜色*/
function fillBackGroundColor() {
    $.each($(".tred"), function (i, j) {
        $(j).prev().addClass("divred");
    })
    $.each($(".tyellow"), function (i, j) {
        $(j).prev().addClass("divyellow");
    })
    $.each($(".tgreen"), function (i, j) {
        $(j).prev().addClass("divgreen");
    }) 
    $.each($(".text"), function (i, j) {
        $(j).prev().addClass("divtext");
    })
}
function setReportStatusFlg(message1, message2, func) {

    ymPrompt.confirmInfo({
        message:"<p style='margin-top:-25px;display:block;line-height: 20px;'>" + message1 + "</p>",
        width:350,
        height:160,
		winPos:[($("body").width()-350)/2,50],
        title:"消息提示！",
        closeBtn:true,
        handler:function (btn) {
            if (btn == 'ok') {
                TB_showMaskLayer("正在" + message2 + "请稍后...");
                var saveParam = {
                    'startDay':$("#queryFrom input[name=startDay]").val(),
                    'endDay':$("#queryFrom input[name=endDay]").val()
                };
                $.post("${ctx}/bis/bis-rental-collection-rate!" + func + ".action", saveParam, function (result) {

                    $("#body").html(result);
                    TB_removeMaskLayer();
                    setStatus();
                });
            }
            ymPrompt.close();
        }
    });
}
/**新增周报专用*/
function setReportStatusFlgNew(message1, message2, func) {
    var cationMSG = "本次周报：" + $("#year").val() + "年" + $("#month").val() + "月,第" + $("#weekOfMonth").val() + "个双周";
    ymPrompt.confirmInfo({
        message:"<p style='margin-top:-25px;display:block;line-height: 20px;'>" + message1 + "</p><br/>" + cationMSG,
        width:350,
        height:200,
		winPos:[($("body").width()-350)/2,50],
        title:"消息提示！",
        closeBtn:true,
        handler:function (btn) {
            if (btn == 'ok') {
                TB_showMaskLayer("正在" + message2 + "请稍后...");
                var saveParam = {
                    'startDay':$("#queryFrom input[name=startDay]").val(),
                    'endDay':$("#queryFrom input[name=endDay]").val()
                };
                $.post("${ctx}/bis/bis-rental-collection-rate!" + func + ".action", saveParam, function (result) {

                    $("#body").html(result);
                    TB_removeMaskLayer();
                    setStatus();
                });
            }
            ymPrompt.close();
        }
    });
}
/**初始化相关信息*/
function init() {
	<%
	if(curDate>15){
		weekOfMonth = 2;
	}else{
		weekOfMonth = 1;
		if(month<1){
			month = 12+month;
			year = year -1;
		}
	}
	%>
    $("#year").val("<%=year%>");
    $("#month").val("<%=month%>");
    $("#weekOfMonth").val("<%=weekOfMonth%>");
    setDateText("<%=year%>", "<%=month%>", "<%=weekOfMonth%>", true);
}

function loadBody() {

    TB_showMaskLayer("正在搜索请稍后...");
    var queryParam = {
        'startDay':$("#queryFrom input[name=startDay]").val(),
        'endDay':$("#queryFrom input[name=endDay]").val()
    };
    $.post("${ctx}/bis/bis-rental-collection-rate!list.action", queryParam, function (result) {
        $("#body").html(result);
        TB_removeMaskLayer();
        setStatus();

        /**填充背景颜色*/
        fillBackGroundColor();
        calcModiList();
    });
}
/**更新审核人 单独方法*/
function updateUpdator() {
    var params = {
        'startDay':$("#queryFrom input[name=startDay]").val(),
        'endDay':$("#queryFrom input[name=endDay]").val()
    };
    $.get("${ctx}/bis/bis-rental-collection-rate!reGetUpdator.action", params, function (data) {
        if (data == null || data == "" || data.length < 1) {
            return;
        } else {
            $("#status").text($("#status").text() + "（" + data + "）");
        }
    });
}
function isEdit() {
    return "<%=year%>" === $("#year").val() && "<%=month%>" === $("#month").val() && "<%=weekOfMonth%>" == $("#weekOfMonth").val();
}
/**显示时间*/
function setDateText(year, month, weekOfMonth, isload) {
    var queryParam = {
        'year':year,
        'month':month,
        'weekOfMonth':weekOfMonth
    };
    $.post("${ctx}/bis/bis-rental-collection-rate!getStartEndDay.action", queryParam, function (result) {

        if (result) {
            var dates = result.split("~");
            if (dates.lenght = 2) {
                var html = '<font color="red">' + dates[0] + '</font>&nbsp;&nbsp;至&nbsp;&nbsp;<font color="red">' + dates[1] + '</font>';
                $("#queryFrom input[name=startDay]").val(dates[0]);
                $("#queryFrom input[name=endDay]").val(dates[1]);
                $("#dataText").html(html);
                if (isload) {
                    loadBody();
                }
            }
        }

    });
}
/**验证文本长度50，并且只能输入数字和小数点**/
function valiKeyUp(demo) {
    if (!demo.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/)) {
        demo.value = demo.o_value;
    } else {
        if (demo.value.match(/^\.\d+$/))
            demo.value = 0 + demo.value;
        if (demo.value.match(/^\.$/))
            demo.value = 0;
        demo.o_value = demo.value;
    }
    ;
    $(demo).val($(demo).val().substr(0, 50));
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
//项目切换
var qiehuans = $("button.qiehuan").click(function () {
    var index = qiehuans.index(this);
    var tab = parent.TabUtils;
    if (index == 2) {
        var url = "${ctx}/bis/bis-manage.action?report=false";
        if (tab == null) {
            window.open(url);
        } else {
            if ($("#195_tab", parent.document).size() != 0) {
                tab.closeTab({data:{tabId:"195"}});
            }
            tab.newTab("195", "项目列表", url, true);
        }
    } else if (index == 1) {
        var url = "${ctx}/bis/bis-manage.action";
        if (tab == null)
            window.open(url);
        else {
            if ($("#195_tab", parent.document).size() != 0) {
                tab.closeTab({data:{tabId:"195"}});
            }
            tab.newTab("195", "经营报表", url, true);
        }
    } else {
        var url = "${ctx}/bis/bis-main-shop-report.action";
        if (tab == null)
            window.open(url);
        else {
            if ($("#195_tab", parent.document).size() != 0) {
                tab.closeTab({data:{tabId:"195"}});
            }
            tab.newTab("195", "主力店招商周报", url, true);
        }

    }
});
/**计算值*/
function calcModiList() {
    var sums = [0, 0, 0, 0, 0, 0, 0];
    var trs = $(".editReport").each(function () {
        var tds = $("td", this).each(function (index) {
            if ($(this).text() !== "" && $(this).text() !== "/" && $(this).text() !== "0" && $(this).text() !== "%") {
                //sums[index]=sums[index]+filterNaN($(this).attr("sum").trim());
                sums[index] = sums[index] + filterNaN($(this).text().trim());
            }
        });
    });
    $("#body .report tr:last td:eq(0)").text(changeTwoDecimal_f(sums[1]));
    $("#body .report tr:last td:eq(1)").text(changeTwoDecimal_f(getColRateRentForCalc(1) * 100 / (sums[1]==0?1:sums[1])) + "%");
    $("#body .report tr:last td:eq(2)").text(changeTwoDecimal_f(sums[3]));
    $("#body .report tr:last td:eq(3)").text(changeTwoDecimal_f(getColRateRentForCalc(2) * 100 / (sums[3]==0?1:sums[3])) + "%");
    setGoodBadPoint();
    fillBackGroundColor();
}
/**累计实收额*/
function getColRateRentForCalc(demo) {
    var allColRateRentForCalc = 0;
    if (demo == 1) {//租金累计实收额
        $.each($(".ColRateRentForCalc1"), function (i, j) {
            if ($(j).val() != "") {
                allColRateRentForCalc += parseFloat($(j).val());
            }
        });
    } else {    //物管累计实收额
        $.each($(".ColRateRentForCalc2"), function (i, j) {
            if ($(j).val() != "") {
                allColRateRentForCalc += parseFloat($(j).val());
            }
        });
    }
    return allColRateRentForCalc;
}
/**获取最好和最差的收缴率并赋予相应样式*/
function setGoodBadPoint() {
    var arrayObj1 = new Array();
    var arrayObj2 = new Array();
    $.each($(".CollPointRent"), function (i, j) {
        if ($(j).val() != "" && $(j).val() != "/") {
            arrayObj1[arrayObj1.length] = parseFloat($(j).val());
        }
    });
    $.each($(".CollPointProperty"), function (i, j) {
        if ($(j).val() != "" && $(j).val() != "/") {
            arrayObj2[arrayObj2.length] = parseFloat($(j).val());
        }
    });
    var t1 = Math.max.apply(Math, arrayObj1);
    var t2 = Math.min.apply(Math, arrayObj1);
    var t3 = Math.max.apply(Math, arrayObj2);
    var t4 = Math.min.apply(Math, arrayObj2);
    $("input:hidden[class='CollPointRent'][value=" + changeTwoDecimal_f(t1) + "]").parent("td").css({"background-image":"url(${ctx}/resources/images/bis/good.png)", "background-repeat":"no-repeat", "background-position":"center"});
    $("input:hidden[class='CollPointRent'][value=" + changeTwoDecimal_f(t2) + "]").parent("td").css({"background-image":"url(${ctx}/resources/images/bis/bad.png)", "background-repeat":"no-repeat", "background-position":"center"});
    $("input:hidden[class='CollPointProperty'][value=" + changeTwoDecimal_f(t3) + "]").parent("td").css({"background-image":"url(${ctx}/resources/images/bis/good.png)", "background-repeat":"no-repeat", "background-position":"center"});
    $("input:hidden[class='CollPointProperty'][value=" + changeTwoDecimal_f(t4) + "]").parent("td").css({"background-image":"url(${ctx}/resources/images/bis/bad.png)", "background-repeat":"no-repeat", "background-position":"center"});
}
/**处理空字符特殊情况*/
function filterNaN(demo, type) {
    if (type == 1) {
        if (isNaN(demo)) {
            return "";
        } else {
            return demo;
        }
    }
    if (demo == null || demo == undefined || demo == null || demo == "" || isNaN(demo)) {
        return 0;
    } else {
        return parseFloat(demo);
    }
}

/**租费收缴率按钮单击*/
$("button.zufei").bind("click", function () {
    return;
});
function resize() {
    var container = $.browser.msie ? $("html") : $(window);
    $("#body").height(container.height() - 100);
}

//高度设置
//setInterval(resize, 1000);
//resize();
</script>
</body>

</html>