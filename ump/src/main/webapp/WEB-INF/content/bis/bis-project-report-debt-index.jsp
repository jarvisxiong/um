<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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

<body>

<s:set var="curUserCd">
    <%=SpringSecurityUtils.getCurrentUiid()%>
</s:set>
<form id="mainFormSearch" action="${ctx}/bis/bis-project-report!load.action">
    <input type="hidden" name="reportType" id="reportType" value="${reportType}"/>
    <input type="hidden" name="chargeTypeCd" id="chargeTypeCd" value="${chargeTypeCd}"/>
    <s:set var="module">2</s:set>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css"/>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bisManageHeader.js"></script>
    <div class="title_bar none" style="display:none;">
        <%--<div style="font-weight:bold;padding-left:8px;padding-right:8px; font-size:14px;float:left;">${titleName}</div>--%>
        <s:if test="#curUserCd=='baolm' || #curUserCd=='lujy' || #curUserCd=='shenjian' || #curUserCd=='jiaoxf' || #curUserCd=='dengyh'">
            &nbsp;&nbsp;<input type="button" class="btn_blue" style="width:75px;height:22px;line-height: 22px;" value="更新数据" onclick="refreshData();"/>
            <input type="button" class="btn_green" style="width:75px;height:22px;line-height: 22px;" value="合同导出"
                   onclick="exportExcel();"/>
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
                <%--<col width="420"/>--%>
                <col/>
                <tr>
                    <td>项目：</td>
                    <td>
                        <input type="text" validate="required" id="bisProjectName" name="bisProjectName"
                               value="${bisProjectName}"
                               style="width:100%; cursor: pointer; font-size: 12px; color: #ff0000;height: 16px;line-height: 16px;"/>
                        <input type="hidden" id="bisProjectId" name="bisProjectId" value="${bisProjectId}"/>
                        <%--
                        <s:select cssStyle="height:20px;" list="mapBisProject" listKey="key" listValue="value" name="bisProjectId" id="bisProjectId"></s:select>
                        --%>
                    </td>
                    <td align="right">月份：</td>
                    <td>
                        <input validate="required" class="Wdate" style="width:75px;height: 16px;line-height: 16px;"
                               type="text"
                               name="reportDate"
                               id="reportDate" value="${reportDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM'})"/>
                    </td>
                    <td>&nbsp;</td>
                    <td>
                        <s:select cssStyle="width:100%;"
                                  list="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" listKey="key"
                                  listValue="value" name="manageCd"></s:select>
                    </td>
                    <td>&nbsp;</td>
                    <td>
                        <!--ul class="bis_rpt" id="bis_rpt" style="float:left; width: 100%;">
                                <security:authorize ifAnyGranted="A_REPO_PROJ_QUERY,A_REPO_ARRE_QUERY">
                                    <li id="li_rpt_1" onclick="changeReportType('li_rpt_1');"
                                        <s:if test="reportType=='total'">class="bis_rpt_click"</s:if> >项目资金流量表
                                    </li>
                                    <li id="li_rpt_5" onclick="changeReportType('li_rpt_5');"
                                        <s:if test="reportType=='manage'">class="bis_rpt_click"</s:if> >项目经营情况表
                                    </li>
                                </security:authorize>
                                <security:authorize ifAnyGranted="A_REPO_DETA_QUERY">
                                    <li id="li_rpt_2" onclick="changeReportType('li_rpt_2');"
                                        <s:if test="reportType=='store'">class="bis_rpt_click"</s:if>>商铺
                                    </li>
                                    <li id="li_rpt_6" onclick="changeReportType('li_rpt_6');"
                                        <s:if test="reportType=='walkStreet'">class="bis_rpt_click"</s:if>>步行街
                                    </li>
                                    <li id="li_rpt_3" onclick="changeReportType('li_rpt_3');"
                                        <s:if test="reportType=='flat'">class="bis_rpt_click"</s:if>>公寓
                                    </li>
                                    <li id="li_rpt_4" onclick="changeReportType('li_rpt_4');"
                                        <s:if test="reportType=='multi'">class="bis_rpt_click"</s:if>>多经
                                    </li>
                                </security:authorize>
                            </ul-->
                    </td>
                    <td>
                            <span style="float:right;">
                            <s:if test="#curUserCd=='baolm' || #curUserCd=='lujy' || #curUserCd=='shenjian' || #curUserCd=='jiaoxf' || #curUserCd=='dengyh'">
                                &nbsp;&nbsp;<input type="button" class="btn_blue" style="width:75px;height:22px;line-height: 22px;" value="更新数据" onclick="refreshData();"/>
                                <input type="button" class="btn_blue" style="width:75px;height:22px;line-height: 22px;"
                                       value="合同导出"
                                       onclick="exportExcel();"/>
                            </s:if>
                                </span>
                        <input type="button" value="搜索" class="btn_blue" onclick="ajaxSearch();"
                               style="float: left;margin-top:4px;height:22px;line-height: 22px;"/>

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

</form>

<div id="reportContent" style="clear:both; word-break:break-all;  text-align:center; overflow: auto;">
    <div style="color: #6BAD42; font-size: 14px; font-weight: bold; text-align: center; line-height: 300px;">请选择搜索条件！
    </div>
</div>


</body>
</html>
