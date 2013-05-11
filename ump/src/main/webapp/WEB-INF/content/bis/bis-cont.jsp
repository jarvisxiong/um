<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/taglibs.jsp" %>
    <%@ include file="/common/nocache.jsp" %>
    <%@ include file="/common/global.jsp" %>
    <%--
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"  />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"  />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"  />
    <script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
    <script type="text/javascript" src="${ctx}/resources/js/customInput/customInput.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.corner.js"></script>
    --%>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/cost-ctrl.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisCont.css"/>
    <script type="text/javascript" src="${ctx}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
    <script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
    <script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
    <script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bisCont.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css"/>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bisManageHeader.js"></script>
    <title>合同管理</title>
    <script language="javascript">
        var _MODULE = "4";
        $(function () {
            try {
                $("#bis-nav li").removeClass("bis-nav-click");
                $("#li_" + _MODULE).removeClass("bis-nav-border");
                $("#li_" + _MODULE).addClass("bis-nav-click");
                if ($("#bisProjectName").val() == '') {
                    $("#bisProjectName").val("--选择项目--");
                }
            } catch (e) {
            }
            $('#btnLayOut').onSelect({
                muti:false,
                callback:function (project) {
                    goFloor(project.bisProjectId);
                }
            });
        });
    </script>
</head>

<body>

<form action="${ctx}/bis/bis-cont!list.action" id="mainFormSearch" method="post">
    <s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%>
    </s:set>
    <s:set var="module">4</s:set>
    <div class="title_bar">
        <div style="font-weight:bold; margin-left:8px; margin-right:8px; font-size:14px;float:left;">商业合同管理</div>
        <div style="float:left;">
            <input type="button" id="btnSeniorSearch" value="高级搜索" style="width: 80px;" class="btn_gray" onclick="showSeniorSearch();"/>
            <input type="button" value="批量减免" class="btn_blue" style="width:80px;margin-left: 2px;" onclick="factReduce();"/>

            <security:authorize ifAnyGranted="A_BIS_CONT_EXPORT">
                <input type="button" value="导 出" class="btn_green" style="width:80px;margin-left: 2px;"
                       onclick="exportContent();"/>
                <%--	<input type="button" value="导入" class="btn_blue" onclick="openImportPanel();" /> --%>
            </security:authorize>

        </div>
        <div style="float:right; height:26px; margin-right:0px;	padding-top:6px;">
            <div style="float:left;">
                <%@ include file="bis-manage-version.jsp" %>
            </div>
            <div style="float:right; height:26px; margin-right:5px;">
                <div class="btn_green" onclick="clkFullScreen('${bisProjectId}');">全屏</div>
            </div>
        </div>
    </div>
    <div id="div_title" style="height: 30px;">
        <div style="margin:0 0px;">
            <div class="bis_search" id="main_search_div">
                <table class="tb_search">
                    <col width="45"/>
                    <col width="100"/>
                    <col width="50"/>
                    <col width="80"/>
                    <col width="50"/>
                    <col width="100"/>
                    <col width="50"/>
                    <col width="100"/>
                    <col/>
                    <tr>
                        <td style="padding-left: 8px;" align="right">项目：</td>
                        <td>
                            <input type="text" id="bisProjectName" readonly="true" name="bisProjectName" value="${bisProjectName}"
                                   style="width:100%; cursor: pointer; font-size: 12px; color: #ff0000;"/>
                            <input type="hidden" id="bisProjectId" name="bisProjectId" value="${bisProjectId}"/>
                        </td>
                        <td align="right">商铺：</td>
                        <td>
                            <input class="enterQuery" type="text" id="filter_LIKES_storeNo"
                                   name="filter_LIKES_storeNo"/>
                            <%--
                           <input type="hidden" id="bisStoreIds" name="bisStoreIds" value="${bisStoreIds}"/>
                           <input style="cursor:pointer;" type="text" class="wolegequ" id="bisStoreNos" onclick="doSelectStore('bisStoreIds','bisStoreNos');"/>
                           --%>
                        </td>
                        <td align="right">商家：</td>
                        <td>
                            <input class="enterQuery" type="text" id="bisShopName" name="bisShopName"/>
                            <%--
                           <input type="hidden" id="bisShopId" name="bisShopId" value="${bisShopId}" />
                           <input class="search enterQuery" searchtext="搜索..." type="text" id="bisShopName" name="bisShopName" />
                           --%>
                        </td>
                        <td align="right">状态：</td>
                        <td>
                            <s:select cssClass="enterQuery"
                                      list="@com.hhz.ump.util.DictMapUtil@getMapBisContStatus()" listKey="key"
                                      listValue="value" name="filter_EQ_statusCd"></s:select>
                        </td>
                        <td style="padding-left:10px; line-height:20px;">
                            <input type="button" value="搜索" class="btn_blue" style="" onclick="submitSearch();"/>
                            <security:authorize ifAnyGranted="A_CONT_INSERT">
                                <input type="button" value="新增" class="btn_blue" style="" onclick="addBisCont();"/>
                            </security:authorize>
                            <input type="button" value="清空" class="btn_green" style="margin-left:1px;" onclick="clearSearch();"/>
                        </td>
                    </tr>
                </table>
            </div>
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
            <tr>
                <td style="text-align: right;">合同编号：</td>
                <td><input class="enterQuery" type="text" id="filter_LIKES_contNo" name="filter_LIKES_contNo"
                           value="${filter_LIKES_contNo}"/></td>
                <td style="text-align: right;">合同大类：</td>
                <td>
                    <s:select list="mapContBigType" listKey="key" listValue="value" name="contBigTypeCd"
                              id="filter_EQ_contBigTypeCd"
                              onchange="getSmallType('filter_EQ_contBigTypeCd','filter_EQ_contSmallTypeCd',true); loadFloor();"></s:select>
                </td>
                <td style="text-align: right;">合同小类：</td>
                <td>
                    <s:select list="mapContSmallType" listKey="key" listValue="value" name="contSmallTypeCd"
                              id="filter_EQ_contSmallTypeCd"></s:select>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">开始时间：</td>
                <td>
                    <input type="text" class="Wdate" name="filter_GED_contStartDate" onfocus="WdatePicker()"
                           value='${filter_GED_contStartDate}'/>
                </td>
                <td style="text-align: right;">到：</td>
                <td>
                    <input type="text" class="Wdate" name="filter_LTD_contStartDate" onfocus="WdatePicker()"
                           value='${filter_LTD_contStartDate}'/>
                </td>
                <td id="tdFloorText" align="right">楼(层)号：</td>
                <td id="tdFloorCont">
                    <select style="width: 100%" id="bisFloorId" name="bisFloorId">
                        <option value="">--全部--</option>
                    </select>
                </td>
                <td id="tdHidden" colspan="2" style="display: none;"></td>
            </tr>
            <tr>
                <td style="text-align: right;">结束时间：</td>
                <td>
                    <input type="text" class="Wdate" name="filter_GED_contEndDate" onfocus="WdatePicker()"
                           value='${filter_GED_contEndDate}'/>
                </td>
                <td style="text-align: right;">到：</td>
                <td>
                    <input type="text" class="Wdate" name="filter_LTD_contEndDate" onfocus="WdatePicker()"
                           value='${filter_LTD_contEndDate}'/>
                </td>
                <!--
                 -->
                <td style="text-align: right;">经营性质：</td>
                <td>
                    <s:select list="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" listKey="key"
                              listValue="value" name="filter_EQ_manageCd"></s:select>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">应收状态：</td>
                <td>
                    <s:select list="#{'':'--全部--','0':'无应收','1':'有应收'}" listKey="key" listValue="value"
                              name="filter_EQ_mustStatus"></s:select>
                </td>
                <td style="text-align: right;">应收月份：</td>
                <td>
                    <input type="text" class="Wdate" name="filter_EQ_mustDate"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM'})" value='${filter_EQ_mustDate}'/>
                </td>
                <td colspan="2"></td>
            </tr>
            <tr>
                <td colspan="6" style="padding-top:10px; text-align:center;">
                    <input type="button" class="btn_blue" onClick="submitSearch();closeSeniorSearch();" value="搜索"/>
                    <input type="button" class="btn_green" onClick="closeSeniorSearch();" value="关闭"/>
                </td>
            </tr>
        </table>
    </div>


    <div id="contContent" style="clear:both; padding:10px 8px 0px; text-align:center;">
        <div style="color: #6BAD42; font-size: 14px; font-weight: bold; text-align: center; line-height: 300px;">
            请选择搜索条件！
        </div>
    </div>

</form>

<div id="contImportPanel"
     style="position:absolute; width:230px; height:60px; top:31px; left:280px; display:none; background-color:#fff; border:1px solid #000; padding:10px; z-index:99;">
    <form action="${ctx}/bis/bis-cont!importContent.action" method="post" enctype="multipart/form-data" id="importForm">
        <input type="file" id="importFile" name="importFile" style="line-height:22px;height:22px;margin-bottom:3px;"/>

        <div style="height: 20px;"></div>
        <input type="button" class="btn_blue" style="width:65px;" onclick="importContent();" value="导入"/>
        <input type="button" class="btn_blue" style="width:65px;" onclick="closeImportPanel();" value="关闭"/>
    </form>
</div>

<s:if test="!projAdmin">
    <script type="text/javascript">
        var isProjectBusinessCompany = "${isProjectBusinessCompany}";
        if (isProjectBusinessCompany == "false") {
            $('#bisProjectName').onSelect({
                muti:false,
                callback:function (project) {
                    $("#bisProjectName").val(project.projectName);
                    $("#bisProjectId").val(project.bisProjectId);
                    loadFloor();
                }
            });
        }
    </script>
</s:if>

<script type="text/javascript">
    $(".enterQuery").keydown(function (event) {
        if (event.keyCode == 13) {
            submitSearch();
            closeSeniorSearch();
        }
    });

    bindSearchEv();

    if ($("#bisStoreNos").val() == '') {
        $("#bisStoreNos").val("--选择--");
    }

</script>

<security:authorize ifAnyGranted="A_CONT_QUERY_PERS,A_CONT_QUERY_ALL">
    <script type="text/javascript">
        //if($("#bisProjectId").val()) {
        submitSearch();
        //}
    </script>
</security:authorize>

</body>
</html>
