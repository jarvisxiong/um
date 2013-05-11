<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="overflow: hidden;">
<head>
    <%@ include file="/common/taglibs.jsp" %>
    <%@ include file="/common/nocache.jsp" %>
    <%@ include file="/common/global.jsp" %>

    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisCont.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/cost-ctrl.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisReport.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-shop.css"/>

    <script type="text/javascript" src="${ctx}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
    <script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
    <script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
    <script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bisCont.js"></script>
    <title>项目经营报表</title>
</head>

<body style="min-height: 450px;">
<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%>
</s:set>
<form id="mainFormSearch" action="${ctx}/bis/bis-project-report!load.action">
<input type="hidden" id="showmanageCdId" value="${manageCd }" />
<input type="hidden" name="reportType" id="reportType" value="${reportType}"/>
<input type="hidden" name="chargeTypeCd" id="chargeTypeCd" value="${chargeTypeCd}"/>

<s:set var="module">2</s:set>
<%--<%@ include file="bis-manage-header.jsp"%>--%>


<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css"/>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bisManageHeader.js"></script>
<div class="title_bar none" style="display:none;">
    <%--<div style="font-weight:bold;padding-left:8px;padding-right:8px; font-size:14px;float:left;">${titleName}</div>--%>
    <s:if test="#curUserCd=='baolm' || #curUserCd=='lujy' || #curUserCd=='shenjian' || #curUserCd=='jiaoxf' || #curUserCd=='dengyh'">
        &nbsp;&nbsp;<input type="button" class="btn_blue" style="width:75px;height:22px;line-height: 22px;" value="更新数据" onclick="refreshData();"/>
        <!--input type="button" class="btn_blue" style="width:75px;height:22px;line-height: 22px;" value="合同导出"
        onclick="exportExcel();"/-->
    </s:if>
</div>

<div id="div_title" style="height: 30px;">
    <div class="bis_search" id="main_search_div" style=" background: #FFF;border-bottom:0px">
        <table class="tb_search">
            <col width="37"/>
            <col width="100"/>
            <col width="50"/>
            <col width="50"/>
            <col width="4"/>
            <col width="75"/>
            <col width="4"/>
            <%--<col width="400"/>--%>
            <col/>
            <tr>
                <td>项目：</td>
                <td>
                    <input type="text" validate="required" id="bisProjectName" name="bisProjectName"
                           value="${bisProjectName}" readonly="true"
                           style="width:100%; cursor: pointer; font-size: 12px; color: #ff0000;height: 16px;line-height: 16px;"/>
                    <input type="hidden" id="bisProjectId" name="bisProjectId" value="${bisProjectId}"/>
                    <%--
                    <s:select cssStyle="height:20px;" list="mapBisProject" listKey="key" listValue="value" name="bisProjectId" id="bisProjectId"></s:select>
                    --%>
                </td>
                <td align="right">月份：</td>
                <td>
                    <input validate="required" class="Wdate text" style="width:75px;height: 16px;line-height: 16px;"
                           type="text"
                           name="reportDate"
                           id="reportDate" value="${reportDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM'})"/>
                </td>
                <td>&nbsp;</td>
                <td>
                 <%-- 
                    <s:select cssStyle="width:100%;"
                              list="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" listKey="key"
                              listValue="value" name="manageCd"></s:select>
                 --%>
                </td>
                <td>&nbsp;</td>
                <td>
                    <style type="text/css">
                        div.bts_container {
                            float: left;
                            padding-right: 10px;
                            position: relative;
                            width: 100px;
                            height: 20px;
                        }

                        div.bts_container ul {
                            overflow: hidden;
                            height: 19px;
                            padding-top: 1px;
                            position: absolute;
                            border: 1px solid #000;
                            background: #FFF;
                            margin-top: 0px;
                        }

                        div.bts_container:hover  ul {
                            height: auto;
                        }

                        ul.bis_cgt li {
                            width: 98px;
                            margin: 0px;
                            padding: 0px 5px 0px 5px;
                            margin: 1px;
                            margin-top: 0px;
                            float: none;
                        }
                    </style>
                    <div class="bts_container" >
                        <ul class="bis_cgt" id="bis_cgt_store" style="float:left; display: none;">
                            <li style="background:#0072bb;color: #FFF">费用类型</li>
                            <s:iterator value="@com.hhz.ump.util.DictMapUtil@getMapChargeTypeSrpt()">
                                <li id="li_cgt_${key}" value="${key}" class="bis_cgt_li"
                                    onclick="sltReportCharge(this);">${value}</li>
                            </s:iterator>
                        </ul>
                        <ul class="bis_cgt" id="bis_cgt_flat" style="float:left; width:100%; display: none;">
                            <li style="background:#0072bb;color: #FFF">费用类型</li>
                            <s:iterator value="@com.hhz.ump.util.DictMapUtil@getMapChargeTypeFrpt()">
                                <li id="li_cgt_${key}" value="${key}" class="bis_cgt_li"
                                    onclick="sltReportCharge(this);">${value}</li>
                            </s:iterator>
                        </ul>
                        <ul class="bis_cgt" id="bis_cgt_multi" style="float:left; width:100%; display: none;">
                            <li style="background:#0072bb;color: #FFF">费用类型</li>
                            <s:iterator value="@com.hhz.ump.util.DictMapUtil@getMapChargeTypeMrpt()">
                                <li id="li_cgt_${key}" value="${key}" class="bis_cgt_li"
                                    onclick="sltReportCharge(this);">${value}</li>
                            </s:iterator>
                        </ul>
                    </div>

                    <s:if test="#curUserCd=='baolm' || #curUserCd=='lujy' || #curUserCd=='shenjian' || #curUserCd=='jiaoxf' || #curUserCd=='dengyh'">
                        <input type="button" class="btn_blue"
                               style="width:75px;height:22px;line-height: 22px; float: right;" value="更新数据"
                               onclick="refreshData();"/>
                    </s:if>

                    <input type="button" value="搜索" class="btn_blue" onclick="ajaxSearch();"
                           style="float: left;margin-top:0px;height:22px;line-height: 22px;margin-left: 6px;"/>

                </td>
            </tr>
            <tr id="trChargeType" style="display: none;">
                <td colspan="8" style="padding-left: 10px;">
                    <ul class="bis_cgt" id="bis_cgt_store" style="float:left; width:100%; display: none;">
                        <s:iterator value="@com.hhz.ump.util.DictMapUtil@getMapChargeTypeSrpt()">
                            <li id="li_cgt_${key}" value="${key}" class="bis_cgt_li"
                                onclick="sltReportCharge(this);">${value}</li>
                        </s:iterator>
                    </ul>
                    <ul class="bis_cgt" id="bis_cgt_flat" style="float:left; width:100%; display: none;">
                        <s:iterator value="@com.hhz.ump.util.DictMapUtil@getMapChargeTypeFrpt()">
                            <li id="li_cgt_${key}" value="${key}" class="bis_cgt_li"
                                onclick="sltReportCharge(this);">${value}</li>
                        </s:iterator>
                    </ul>
                    <ul class="bis_cgt" id="bis_cgt_multi" style="float:left; width:100%; display: none;">
                        <s:iterator value="@com.hhz.ump.util.DictMapUtil@getMapChargeTypeMrpt()">
                            <li id="li_cgt_${key}" value="${key}" class="bis_cgt_li"
                                onclick="sltReportCharge(this);">${value}</li>
                        </s:iterator>
                    </ul>
                </td>
            </tr>
        </table>
    </div>
</div>

<div id="seniorSearchDiv"
     style="position:absolute; width:650px; height:130px; top:31px; left:8px; display:none; background-color:#fff; border:1px solid #000; padding:10px 20px 10px 0px; z-index:8;">
    <table class="tb_search" cellpadding="0" cellspacing="0">
        <col width="80"/>
        <col width="130"/>
        <col width="80"/>
        <col width="130"/>
        <col width="80"/>
        <col width="130"/>
    </table>
</div>

<script type="text/javascript">
    isEmpty = function (str) {
        return (typeof (str) === "undefined" || str === null || (str.length === 0));
    };
    var _MODULE = '${module}';

    $(function () {
        $("#bis-nav li").removeClass("bis-nav-click");
        $("#li_" + _MODULE).removeClass("bis-nav-border");
        $("#li_" + _MODULE).addClass("bis-nav-click");
        if ($("#bisProjectName").val() == '') {
            $("#bisProjectName").val("--选择项目--");
        }

        if ("true" != "${isBusinessCompanyUser}") {
            $('#bisProjectName').onSelect({
                top:10,
                muti:false, callback:function (obj) {
                    if (obj.bisProjectId != "1") {
                        parent.bisProjectId = obj.bisProjectId;
                        $("#bisProjectName").val(obj.projectName);
                        $("#bisProjectName").next().val(obj.bisProjectId);
                        ajaxSearch();
                    }
                }});
        }


        $('#btnLayOut').onSelect({
            muti:false,
            callback:function (project) {
                goFloor(project.bisProjectId);
            }
        });


        if (location.href.indexOf("bisProjectId") == -1) {
            $('#bisProjectName').trigger("click");
        }

    });

</script>

</form>

<div id="reportContent" style="clear:both; word-break:break-all;  text-align:center; overflow: hidden;">
    <div style="color: #6BAD42; font-size: 14px; font-weight: bold; text-align: center; line-height: 300px;">请选择搜索条件！
    </div>
</div>

<script type="text/javascript">
/**
 * 切换报表类别
 */
function changeReportType(id) {
    if (id == 'li_rpt_1') {
        $("#bis_rpt li").removeClass("bis_rpt_click");
        $("#li_rpt_1").addClass("bis_rpt_click");
        $("#reportType").val("total");
        showChargeType(false);
    } else if (id == 'li_rpt_2') {
        $("#bis_rpt li").removeClass("bis_rpt_click");
        $("#li_rpt_2").addClass("bis_rpt_click");
        $("#reportType").val("store");
        showChargeType(true, 'store');
    } else if (id == 'li_rpt_6') {
        $("#bis_rpt li").removeClass("bis_rpt_click");
        $("#li_rpt_6").addClass("bis_rpt_click");
        $("#reportType").val("walkStreet");
        showChargeType(true, 'store');
    } else if (id == 'li_rpt_3') {
        $("#bis_rpt li").removeClass("bis_rpt_click");
        $("#li_rpt_3").addClass("bis_rpt_click");
        $("#reportType").val("flat");
        showChargeType(true, 'flat');
    } else if (id == 'li_rpt_4') {
        //alert("此功能暂未开放");
        //return;
        $("#bis_rpt li").removeClass("bis_rpt_click");
        $("#li_rpt_4").addClass("bis_rpt_click");
        $("#reportType").val("multi");
        // multi...
        showChargeType(true, 'multi');
    } else if (id == 'li_rpt_5') {
        $("#bis_rpt li").removeClass("bis_rpt_click");
        $("#li_rpt_5").addClass("bis_rpt_click");
        $("#reportType").val("manage");
    }
    if (validateSearch()) {
        ajaxSearch();
    }
}
/**
 * 选择费用类别
 */
function sltReportCharge(dom) {

    if ($(dom).hasClass("bis_cgt_click")) {
        $(dom).addClass('bis_cgt_li');
        $(dom).removeClass('bis_cgt_click');
    } else {
        $(dom).removeClass('bis_cgt_li');
        $(dom).addClass('bis_cgt_click');
    }

}
/**
 * 显示费用类别
 */
function showChargeType(flag, type) {
    if (flag) {
        $("#main_search_div").css({height:"60px"});
        //$("#reportContent").css("height", $(window).height() - 27 - 30 - 30 - 12 - 2 + "px");
        $("#div_title").css("height", "60px");
        $("#trChargeType").show();
        if (type == 'store') {
            $("#bis_cgt_store").show();
            $("#bis_cgt_flat").hide();
            $("#bis_cgt_multi").hide();
        } else if (type == 'flat') {
            $("#bis_cgt_flat").show();
            $("#bis_cgt_store").hide();
            $("#bis_cgt_multi").hide();
        } else if (type == 'multi') {
            $("#bis_cgt_multi").show();
            $("#bis_cgt_store").hide();
            $("#bis_cgt_flat").hide();
        }
    } else {
        $("#main_search_div").css({height:"30px"});
        //$("#reportContent").css("height", $(window).height() - 27 - 30 - 12 + "px");
        $("#div_title").css("height", "30px");
        $("#trChargeType").hide();
    }
}

function validateSearch() {
    if ($("#bisProjectName").val() == '--选择项目--') {
        $("#bisProjectName").val('');
    }
    var pdv = new Validate("mainFormSearch");
    if (!pdv.validate()) {
        //alert("请填写完整");
        $("#mainFormSearch :input,textarea").filter("[validate=required]").filter("[value='']").eq(0).focus();
        if ($("#bisProjectName").val() == '') {
            $("#bisProjectName").val('--选择项目--');
        }
        return false;
    }
    return true;
}
/**
 * 搜索
 */
function ajaxSearch() {

    if (!validateSearch()) {
        return false;
    }

    var reportType = $("#reportType").val();

    if (reportType == 'total' || reportType == 'manage') {
        $("#mainFormSearch").attr("action", "${ctx}/bis/bis-project-report!load.action");
    } else if (reportType == 'store' || reportType == 'walkStreet') {
        var chargeTypeCd = "";
        $("#bis_cgt_store").find(".bis_cgt_click").each(function (i, dom) {
            chargeTypeCd += dom.value + ",";
        });
        $("#chargeTypeCd").val(chargeTypeCd.substring(0, chargeTypeCd.length - 1));
    } else if (reportType == 'flat') {
        var chargeTypeCd = "";
        $("#bis_cgt_flat").find(".bis_cgt_click").each(function (i, dom) {
            chargeTypeCd += dom.value + ",";
        });
        $("#chargeTypeCd").val(chargeTypeCd.substring(0, chargeTypeCd.length - 1));
    } else if (reportType == 'multi') {
        var chargeTypeCd = "";
        $("#bis_cgt_multi").find(".bis_cgt_click").each(function (i, dom) {
            chargeTypeCd += dom.value + ",";
        });
        $("#chargeTypeCd").val(chargeTypeCd.substring(0, chargeTypeCd.length - 1));
    } else {
        return;
    }

    TB_showMaskLayer("正在搜索...");
    $("#mainFormSearch").ajaxSubmit(function (result) {
        $("#reportContent").html(result);
        if (reportType == 'store' || reportType == 'flat' || reportType == 'multi') {
            var typeStr = (chargeTypeCd.split(",")).length - 1;
            if (chargeTypeCd == "")
                typeStr = 2;
            $("#rightTable").width(460 * typeStr);
            $("#rightTopTable").width(460 * typeStr);
            var contentWidth = $("#reportContent").width();
            $("#rightTopDiv").width(contentWidth - 250);
            $("#rightDiv").width(contentWidth - 250);
            //取高度
            var contentHeight = $("#reportContent").height();
            $("#rightDiv").height(contentHeight - 90);
            $("#leftDiv").height(contentHeight - 90);

            if ($("#reportContent").hasClass("scroll_y_show")) {
                $("#reportContent").removeClass("scroll_y_show");
            }
        } else {
            if (!$("#reportContent").hasClass("scroll_y_show")) {
                $("#reportContent").addClass("scroll_y_show");
            }
        }
        TB_removeMaskLayer();
    });

}

/**
 * 查看费用
 */
function viewFeeManage() {
    /*window.location = "
${ctx}/bis/bis - manage!layout.action ? bisProjectId = "+$("#bisProjectId").val()+" & module = 3";*/
    var url = "${ctx}/bis/bis-manage!layout.action?module=3&bisProjectId=" + $("#bisProjectId").val();
    if (parent.parent.TabUtils == null)
        window.open(url);
    else
        parent.parent.TabUtils.newTab("bisFee", "收费明细", url, true);
}
/**
 * 查看合同
 */
function viewContManage() {
    /*window.location = "
${ctx}/bis/bis - manage!layout.action ? bisProjectId = "+$("#bisProjectId").val()+" & module = 4";*/
    var url = "${ctx}/bis/bis-manage!layout.action?module=4&bisProjectId=" + $("#bisProjectId").val();
    if (parent.parent.TabUtils == null)
        window.open(url);
    else
        parent.parent.TabUtils.newTab("bisCont", "商业合同管理", url, true);
}

/**
 * 更新报表数据
 */
function refreshData() {
    TB_showMaskLayer("正在更新...");
    var url = "${ctx}/bis/bis-project-report!refreshData.action";
    $.post(url, {reportDate:$("#reportDate").val()}, function (result) {
        TB_removeMaskLayer();
        TB_showMaskLayer("更新完成", 1000);
    });
}

</script>

<script type="text/javascript">

    //$("#reportContent").height($(window).height() - 27 - 30 - 12 + "px");
    var reportType = $("#reportType").val();
    if (reportType == 'store') {
        changeReportType('li_rpt_2');
    }
    <security:authorize ifAnyGranted="A_REPO_PROJ_QUERY,A_REPO_ARRE_QUERY">
    if ($("#bisProjectId").val() && $("#reportDate").val() && $("#reportType").val()&&(
            location.href.indexOf("cash")!=-1||location.href.indexOf("operate")!=-1
            )) {
        if (reportType == 'total' || reportType == 'manage') {
            ajaxSearch();
        }
    }
    </security:authorize>


    $(function () {
        if (location.href.indexOf("cash") != -1) {
            changeReportType('li_rpt_1');
        } else if (location.href.indexOf("operate") != -1) {
            changeReportType('li_rpt_5');
        } else if (location.href.indexOf("store") != -1) {
            changeReportType('li_rpt_2');
        } else if (location.href.indexOf("walkStreet") != -1) {
            changeReportType('li_rpt_6');
        } else if (location.href.indexOf("flat") != -1) {
            changeReportType('li_rpt_3');
        } else if (location.href.indexOf("multi") != -1) {
            changeReportType('li_rpt_4');
        }
        dynamicChange($("#showmanageCdId").val());
    });
    /**动态选中*/
    function dynamicChange(demo){
        if(demo!=undefined&&demo!=""||demo!=null){
            demo = parseInt(demo)+1;
    		$("#manageCd option:nth-child("+demo+")").attr("selected" , "selected");
        } 
    }
</script>
</body>
</html>
