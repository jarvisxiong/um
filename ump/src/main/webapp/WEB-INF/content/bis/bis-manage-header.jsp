<%@ page contentType="text/html;charset=UTF-8" %>


<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css"/>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bisManageHeader.js"></script>

<s:set var="titleName">
    <s:if test="#module==1">集团报表</s:if>
    <s:elseif test="#module==2">项目汇总表</s:elseif>
    <s:elseif test="#module==3">商业收费明细</s:elseif>
    <s:elseif test="#module==4">商业合同管理</s:elseif>
    <s:elseif test="#module==5">租户详细信息</s:elseif>
    <s:elseif test="#module==7">基础数据维护</s:elseif>
    <s:elseif test="#module==8">项目资料管理</s:elseif>
    <s:else>商业管理系统</s:else>
</s:set>

<div class="title_bar">
    <div style="font-weight:bold;padding-left:8px;padding-right:8px; font-size:14px;float:left;">${titleName}</div>
    <s:if test="#module==2 ">
        <s:if test="#curUserCd=='baolm' || #curUserCd=='lujy' || #curUserCd=='shenjian' || #curUserCd=='jiaoxf' || #curUserCd=='dengyh'">
            &nbsp;&nbsp;<input type="button" class="btn_blue" style="width:80px" value="更新数据" onclick="refreshData();"/>
            <input type="button" class="btn_green" style="width:80px;margin-left: 5px;" value="合同导出" onclick="exportExcel();"/>
        </s:if>
    </s:if>
    <s:if test="#module==3 ">
        <div style="float:left;padding:3px;">
            <input type="button" id="btnSeniorSearch" value="高级搜索" class="btn_gray senior_search" onclick="showSeniorSearch();"/>
        </div>
        <div style="font-size:12px;width: 37px; height:16px;margin-left: 8px; padding: 0pt 0pt 0pt 0px; float: left;">
            项目：
        </div>
        <div style="width: 100px; margin-top: 4px; padding: 0pt 0pt 0pt 0px; float: left; 	">
            <input type="text" id="bisProjectName" name="bisProjectName" value="${bisProjectName}"
                   style="  height: 16px;width:100%; cursor: pointer; font-size: 12px; color: #ff0000;"/>
            <input type="hidden" id="bisProjectIdFact" name="bisProjectIdFact" value="${bisProjectId}"/>
        </div>
        <div style="font-size:12px;width: 37px; margin-left: 28px; padding: 0pt 0pt 0pt 0px; float: left;">业态：</div>
        <div style="width: 70px; margin-top: 4px;height:16px; padding: 0pt 0pt 0pt 0px; float: left; 	">
            <s:select cssStyle="width:100%;" style="height:19px;" validate="required"
                      list="@com.hhz.ump.util.DictMapUtil@getMapContBigTypeNew()" listKey="key"
                      listValue="value" name="layOutCd" id="layOutCd" onchange="changlayOutDetail1();"
                      required="true"></s:select>
        </div>
    </s:if>
    <s:if test="#module==7">
        <security:authorize ifAnyGranted="A_PROJ_PROP_MODI">
            <input type="button" class="btn_blue" style="width:95px; " onclick="editProjectInfo();"
                   value="项目信息维护"/>
        </security:authorize>
        <security:authorize ifAnyGranted="A_PROJ_DATA_MODI">
            <input type="button" class="btn_blue" style="width:65px;" onclick="doAddLayoutImg();" value="资料管理"/>
        </security:authorize>
        <s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%>
        </s:set>
        <s:if test="#curUserCd=='baolm' || #curUserCd=='lujy' || #curUserCd=='shenjian' || #curUserCd=='jiaoxf' || #curUserCd=='dengyh' || #curUserCd=='zhengym'">
            <input type="button" class="btn_blue" style="width:65px;" onclick="openFloorTab();" value="楼层管理"/>
        </s:if>
        <s:if test="#curUserCd == 'jiaoxf'||#curUserCd == 'zhengym'">
            <input type="button" class="btn_blue" style="width:100px; " onclick="exportAlertInfo();" value="商铺空坐标提醒"/>
        </s:if>
    </s:if>
    <s:if test="#module==1">
        <div style="float:left;">
            <input id="topBtn_1" type="button" class="btn_grey" style="width:125px; height:24px; line-height:24px;"
                   onclick="onClickTop(1)" value="集团资金流量汇总表"/>
            <input id="topBtn_2" type="button" class="btn_grey" style="width:125px; height:24px; line-height:24px;"
                   onclick="onClickTop(2)" value="集团经营情况汇总表"/>
            <script language="javascript">
                function onClickTop(clickNo) {
                    if (1 == clickNo) {
                        self.location = "bis-manage-report.action?reportTypeStr=";
                    } else if (2 == clickNo) {
                        self.location = "bis-manage-report.action?reportTypeStr=manage";
                    }
                }
                if ("manage" == "${reportTypeStr}") {
                    $("#topBtn_1").removeClass();
                    $("#topBtn_1").addClass("btn_grey");
                    $("#topBtn_2").removeClass();
                    $("#topBtn_2").addClass("btn_orange");
                } else {
                    $("#topBtn_1").removeClass();
                    $("#topBtn_1").addClass("btn_orange");
                    $("#topBtn_2").removeClass();
                    $("#topBtn_2").addClass("btn_grey");
                }
            </script>
        </div>
    </s:if>
            <div style="float:right; height:26px; margin-right:5px; margin-top:6px;">
                <div class="btn_green" onclick="clkFullScreen('${bisProjectId}');">全屏</div>
            </div>
</div>
<div id="div_title" style="height: 30px;">
<div style="margin:0 0px;">
<s:if test="#module==1">
    <input type="hidden" id="bisProjectId" name="bisProjectId" value="${bisProjectId}"/>
</s:if>
<s:elseif test="#module==2">
    <div class="bis_search" id="main_search_div">
        <table class="tb_search">
            <col width="45"/>
            <col width="100"/>
            <col width="45"/>
            <col width="50"/>
            <col width="4"/>
            <col width="75"/>
            <col width="4"/>
            <col width="420"/>
            <col/>
            <tr>
                <td style="padding-left: 8px;" align="right">项目：</td>
                <td>
                    <input type="text" validate="required" id="bisProjectName" name="bisProjectName"
                           value="${bisProjectName}"
                           style="width:100%; cursor: pointer; font-size: 12px; color: #ff0000;"/>
                    <input type="hidden" id="bisProjectId" name="bisProjectId" value="${bisProjectId}"/>
                        <%--
                              <s:select cssStyle="height:20px;" list="mapBisProject" listKey="key" listValue="value" name="bisProjectId" id="bisProjectId"></s:select>
                              --%>
                </td>
                <td align="right">月份：</td>
                <td>
                    <input validate="required" class="Wdate" style="width:50px;" type="text" name="reportDate"
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
                    <ul class="bis_rpt" id="bis_rpt" style="float:left; width: 100%;">
                        <security:authorize ifAnyGranted="A_REPO_PROJ_QUERY,A_REPO_ARRE_QUERY">
                            <li id="li_rpt_1" onclick="changeReportType('li_rpt_1');"
                                <s:if test="reportType=='total'">class="bis_rpt_click"</s:if> >项目资金流量表
                            </li>
                            <li id="li_rpt_5" onclick="changeReportType('li_rpt_5');"
                                <s:if test="reportType=='manage'">class="bis_rpt_click"</s:if> >项目经营情况表
                            </li>
                            <!--<li id="li_rpt_7" onclick="changeReportType('li_rpt_7');"
                            <s:if test="reportType=='manaRept'">class="bis_rpt_click"</s:if> >经营情况表</li> -->
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
                    </ul>
                </td>
                <td>
                    <input type="button" value="搜索" class="btn_blue" onclick="ajaxSearch();"/>
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
</s:elseif>

<s:elseif test="#module==3">
    <div class="bis_search" id="main_search_div" style="height:30px">
        <table class="tb_search" style="    margin-left: 5px;">
            <col/>
            <tr>

                <td align="right" class="flat_layout" style="display:none;" layout="flat" width="40">楼号：</td>
                <td layout="flat" class="flat_layout" style="display:none;" width="70">
                    <input style="width:100%;height:16px;" id="flatBuding"/>
                </td>
                <td align="right" id="detailLabel" width="40">租户：</td>
                <td width="100">
                    <input style="width:100px;height:16px;color:#ccc" title="" id="layOutCdList_v0" required="true"
                           onfocus="clearInput(this);" value="搜索商家/商铺"/>
                    <select style="width:150px;height:20px;display:none;" title="" id="layOutCdList_v1" required="true"
                            onClick="selectDetail1();"></select>
                </td>
                <td align="right" width="40">类别：</td>
                <td width="100">
                    <s:select onchange="setThisTitle('chargeTypeCd');" cssStyle="width:100%" validate="required"
                              list="@com.hhz.ump.util.DictMapUtil@getMapChargeType()" listKey="key"
                              listValue="value" name="chargeTypeCd" id="chargeTypeCd"></s:select>
                </td>
                <td align="right" width="30">年：</td>
                <td width="50">
                    <s:select cssStyle="width:100%;" validate="required"
                              list="@com.hhz.ump.util.DictMapUtil@getMapYear()" listKey="key" listValue="value"
                              name="year" id="year"></s:select>
                </td>
                <td align="right" width="30">月：</td>
                <td width="50">
                    <s:select cssStyle="width:100%;" validate="required"
                              list="@com.hhz.ump.util.DictMapUtil@getMapMonth()" listKey="key"
                              listValue="value" name="month" id="month"></s:select>
                </td>
                <td align="right" class="must_dime" must="true" style="display:none" width="70">应收日期：</td>

                <td align="right" class="must_dime" id="must_1" must="true" style="display:none" width="80px">
                    <input class=input" type="text"
                    onfocus="WdatePicker()"
                    name="mustInBegin" id="mustInBegin" style="cursor: pointer;width:70px;height:16px; margin-top:
                    2px;"/>
                </td>
                <td align="right" class="must_dime" must="true" style="display:none" width="30">至 &nbsp;  </td>
                <td align="right" class="must_dime" id="must_1" must="true" style="display:none" width="80px">

                    <input class=input" type="text"
                    onfocus="WdatePicker()"
                    name="mustInEnd" id="mustInEnd" style="cursor: pointer;width:70px;height:16px; margin-top: 2px;"/>
                </td>
                <td style="padding-left: 5px;">
                    <input id="btn_search" type="button" style="width:65px;margin-right: 5px;" class="btn_blue" onclick="submitSearch1();"
                           value="搜索"/>
                    <input id="btn_blue" class="btn_blue fact_dime" type="button" style="width:65px; "
                           onclick="appendFact();" value="新增"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="bis_search" id="main_search_div1"
         style="height:30px;background:white;border:0px;    margin-bottom: 9px;">
        <table style="width:100%">
            <tr>
                <td colspan="12">
                    <ul id="bis_rpt" style="margin-left: 4px;list-style-type:none;">
                        <li class="" id="must" value="2" style="margin-top:8px;height:24px;line-height:24px;">应收明细</li>
                        <li class="" id="overdue" value="3" style="margin-top:8px;height:24px;line-height:24px; ">欠费明细
                        </li>
                        <li class="" id="fact" value="1" style="margin-top:8px;height:24px;line-height:24px;">收费历史记录
                        </li>
                    </ul>
                    <ul class="fact_dime" style="    list-style-type: none;" id="search_fact">
                        <li class="" fact="true" id="" valu='1'
                            style="color:#006FB6;float:right;margin:8px 14px 0 4px;height:18px;line-height:18px;">审核通过
                        </li>
                        <li class="" fact="true" id="" valu='0'
                            style="color:#006FB6;float:right;margin:8px 4px 0;height:18px;line-height:18px;">等待审核
                        </li>
                        <li class="" fact="true" id="" valu='2'
                            style="color:#006FB6;float:right;margin:8px 4px 0;height:18px;line-height:18px;">审核驳回
                        </li>
                        <li class="" fact="true" id="factAll" valu=''
                            style="color:red;float:right;margin:8px 4px 0;height:18px;line-height:18px;">全部
                        </li>
                        <li class="" fact="true" id="" valu='n'
                            style="float:right;margin:8px 4px 0;height:18px;line-height:18px;">快速搜索:
                        </li>
                    </ul>
                    <ul class="must_dime" style="    list-style-type: none;" id="search_due">
                        <li class="" id="dueli" valu='1' style="color:red">未收齐</li>
                        <li class="" id="nodueli" valu='0'>已收齐</li>
                        <li class="" id="allnodueli" valu='2'>全部</li>
                        <li class="" id="" valu='n' style="color:#000">快速搜索:</li>
                    </ul>
                    <!-- <div id="divoverdue" style="line-height:18px;text-align:center;float:left; " >
                                     <input id="inputoverdue" checked="checked" style="float:left;    margin:8px 1px 8px 4px " type="checkbox"/>
                                     </div>
                                      -->
                    <span style="margin-top:8px;margin-left:20px;float:left ;color:red;display:none;line-height:20px;height:20px;"
                          id="factoptip"></span>
                </td>
            </tr>
        </table>
    </div>
    <div class="bis_search" id="notPublic"
         style="height:30px;background:white;border:0px;    margin-bottom: 9px;display:none">
        <table style="width:100%">
            <tr>
                <td>
                        <%-- 导入--%>
                    <form id="importFactForm" enctype="multipart/form-data" method="post"
                          action="${ctx}/bis/bis-fact!importFact.action">
                        <input id="importBisProjectId" name="bisProjectId" type="hidden"/>
                        <table>
                            <tbody>
                            <tr>
                            <tr>
                                <td style="padding-left: 8px; padding-top: 5px;" colspan="3">
                                    <input type="file" style="line-height: 22px; height: 22px; margin-bottom: 3px;"
                                           name="importFact" id="importFact">
                                </td>
                                <td style="padding-left: 10px;" colspan="6">
                                    <input type="button" value="导入数据" onclick="importFactFile();" style="width: 65px; margin-right: 5px;"
                                           class="btn_blue">
                                    <input id="btn_blue" type="button" value="批量新增" onclick="appendFacts();"
                                           style="width: 65px;" class="btn_blue">
                                </td>
                            </tr>
                            </tbody>
                        </table>

                    </form>
                </td>
            </tr>
        </table>
    </div>
</s:elseif>
<s:elseif test="#module==7">
    <div class="bis_search" id="main_search_div">
        <table class="tb_search">
            <col width="45"/>
            <col width="120"/>
            <col width="80"/>
            <col width="70"/>
            <col width="60"/>
            <col width="90"/>
            <col/>
            <tr>
                <td style="padding-left: 8px;" align="right">项目：</td>
                <td>
                    <input type="text" readonly="true" class="projectSelect" id="bisProjectName" value="${bisProjectName}"
                           style="width:100%; cursor: pointer; font-size: 12px; color: #ff0000;"/>
                    <input type="hidden" id="bisProjectId" value="${bisProjectId}"/>
                </td>
                <td align="right">楼宇类型：</td>
                <td>
                    <select style="width: 100%" id="floorType" onchange="changeFloorType(this.value);">
                        <option value="">--选择--</option>
                        <option value="1">商铺</option>
                        <option value="2">公寓</option>
                        <option value="3">多经</option>
                    </select>
                </td>
                <td id="building" align="right">楼号：</td>
                <td id="bisFloorTd">
                    <select style="width: 100%" id="bisFloorId" onchange="bisFloorSearch(1)">
                        <option>--选择--</option>
                    </select>
                </td>
                <td id="numName" align="right">编号：</td>
                <td style="width: 90px;"><input type="text" id="num" style="width: 80px;"></input></td>
                <td style="padding-left: 5px;">
                    <input type="button" class="btn_blue" onclick="bisFloorSearch(1);" value="搜索"/>
                    <security:authorize ifAnyGranted="A_PROJ_PROP_MODI">
                        <input type="button" class="btn_blue" style="width:65px;" onclick="bisFloorAdd();" value="新增"/>
                    </security:authorize>
                    <input type="button" class="btn_blue" style="width:65px; display:none;" onclick="doImport();"
                           value="导入"/>
                    <input type="button" class="btn_green" style="width:65px;" onclick="doExport();" value="导出"/>

                </td>
            </tr>
        </table>
    </div>
</s:elseif>
<s:elseif test="#module==8">
    <div class="bis_search" id="main_search_div">
        <table class="tb_search">
            <col width="45"/>
            <col width="120"/>
            <col/>
            <tr>
                <td style="padding-left: 8px;" align="right">项目：</td>
                <td>
                    <input type="text" id="bisProjectName" value="${bisProjectName}"
                           style="width:100%; cursor: pointer; font-size: 12px; color: #ff0000;"/>
                    <input type="hidden" id="bisProjectId" value="${bisProjectId}"/>
                </td>
                <td style="padding-left: 20px;">
                    <input type="button" class="btn_blue" style="width: 65px;" onclick="submitSearch();" value="搜索"/>
                    <input type="button" class="btn_blue" style="width:65px;" onclick="doAddLayout();" value="新增"/>
                </td>
            </tr>
        </table>
    </div>
</s:elseif>
<s:elseif test="#module==9">
    <div class="bis_search" id="main_search_div">
        <table class="tb_search">
            <col width="45"/>
            <col width="100"/>
            <col width="50"/>
            <col width="80"/>
            <col width="50"/>
            <col width="100"/>
            <col/>
            <tr>
                <td style="padding-left: 8px;" align="right">项目：</td>
                <td>
                    <input type="text" id="filter_EQ_bisProjectName" name="filter_EQ_bisProjectName"
                           value="${filter_EQ_bisProjectName}"
                           style="width:100%; cursor: pointer; font-size: 12px; color: #ff0000;"/>
                    <input type="hidden" id="filter_EQ_bisProjectId" name="filter_EQ_bisProjectId"
                           value="${filter_EQ_bisProjectId}"/>
                </td>
                <td align="right">商铺：</td>
                <td>
                    <input class="enterQuery" type="text" id="filter_LIKES_storeNo" name="filter_LIKES_storeNo"/>
                </td>
                <td align="right">商家：</td>
                <td>
                    <input class="enterQuery" type="text" id="filter_LIKES_shopName" name="filter_LIKES_shopName"/>
                </td>
                <td style="padding-left:10px; line-height:20px;">
                    <input type="button" value="搜索" class="btn_blue" onclick="submitSearch();"/>
                        <%--
                          <input type="button" value="新增" class="btn_blue" onclick="doAddTenant();" />
                          --%>
                    <s:if test="#curUserCd == 'baolm'">
                        <input type="button" value="合并" class="btn_blue" onclick="toMergeTenant();"/>
                    </s:if>
                </td>
            </tr>
        </table>
    </div>
</s:elseif>
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
        <s:if test="#module==3">
            <tr id="must_fact_senior" class="fact_dime" style="display:none;">
                <td align="right">实收日期：</td>
                <td>
                    <input class=input" type="text"
                    onfocus="WdatePicker()"
                    name="factInBegin" id="factInBegin" style="cursor: pointer;"/>
                </td>
                <td align="right">到：</td>
                <td>
                    <input class=input" type="text"
                    onfocus="WdatePicker()"
                    name="factInEnd" id="factInEnd" style="cursor: pointer;"/>
                </td>
                <td align="right">状态：</td>
                <td>
                    <s:select list="@com.hhz.ump.util.DictMapUtil@getMapBisFactStatus()" listKey="key"
                              listValue="value" name="statusCd" id="statusCd"/>
                </td>
            </tr>
            <tr>
                <td align="right">创建人：</td>
                <td>
                    <input name="creatorQ" id="creatorQ"/>
                    <input name="creator" id="creator" type="hidden"/>
                </td>
                <td align="right">年月从：</td>
                <td>
                    <input onfocus="WdatePicker({dateFmt:'yyyy-MM'})"
                           name="minMonth" id="minMonth"/>
                </td>
                <td align="right">到：</td>
                <td>
                    <input onfocus="WdatePicker({dateFmt:'yyyy-MM'})" name="maxMonth" id="maxMonth"/>
                </td>
            </tr>
            <tr>
                <td align="right">审核人：</td>
                <td>
                    <input name="checkUserCdQ" id="checkUserCdQ"/>
                    <input name="checkUserCd" id="checkUserCd" type="hidden"/>
                </td>
                <td align="right">实收金额：</td>
                <td>
                    <input class=input" type="text" name="minMoney" id="minMoney" style="cursor: pointer;"/>
                </td>
                <td align="right">到：</td>
                <td>
                    <input class=input" type="text" name="maxMoney" id="maxMoney" />
                </td>
            </tr>
            <tr>
                <td colspan="6" style="padding-top:10px; text-align:center;">
                    <input type="button" class="btn_blue" onClick="submitSearch2();closeSeniorSearch();" value="搜索"/>
                    <input type="button" class="btn_green" onClick="closeSeniorSearch();" value="关闭"/>
                </td>
            </tr>
        </s:if>
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
        if (_MODULE == 5) {
            $('#bisProjectName').onSelect({
                muti:false,
                callback:function (project) {
                    $("#bisProjectName").val(project.projectName);
                    $("#bisProjectId").val(project.bisProjectId);
                    filterFloor(project.bisProjectId);
                }
            });
        } else if (_MODULE == 7) {
            var projAdmin = $('#projAdmin').val();
            if (projAdmin == 'true') {
                if (isProjectBusinessCompany == "false") {
                    $('#bisProjectName').onSelect({
                        muti:false,
                        callback:function (project) {
                            $("#bisProjectName").val(project.projectName);
                            //if($("#bisProjectNames").val()=='') {
                            //	$("#bisProjectNames").val(project.bisProjectName);
                            //}
                            $("#bisProjectId").val(project.bisProjectId);
                            filterFloor(project.bisProjectId);
                        }
                    });
                }
                $('#bisProjectNames').onSelect({
                    muti:false,
                    callback:function (project) {
                        $("#bisProjectNames").val(project.projectName);
                        $("#bisProjectIds").val(project.bisProjectId);
                        filterFloors(project.bisProjectId);
                        if (project.bisProjectId == '40282b8927a42dff0127a435d5c30127') {
                            $('#luoyanginfo').show();
                        } else {
                            $('#luoyanginfo').hide();

                        }
                    }
                });
            }
        } else if (_MODULE == 9) {
            //租户
            $('#filter_EQ_bisProjectName').onSelect({muti:false});
        } else {
            $('#bisProjectName').onSelect({muti:false});
        }
        $('#btnLayOut').onSelect({
            muti:false,
            callback:function (project) {
                goFloor(project.bisProjectId);
            }
        });
    });

</script>

<s:if test="#module==0 || #module==1 || #module==5">
    <script type="text/javascript">
        //$("#div_title").css("height","43px");
        $("#div_title").css("height", "0px");

    </script>
</s:if>

