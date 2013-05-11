<%@page import="com.hhz.ump.util.DictContants" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/global.jsp" %>
    <%@ include file="/common/taglibs.jsp" %>
    <%@ include file="/common/nocache.jsp" %>
    <%@ page language="java" %>

    <title>预收明细</title>
    <script type="text/javascript">
        var _ctx = '${ctx}';
        var isProjectBusinessCompany = "${isProjectBusinessCompany}";
    </script>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-project.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis.fact.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"/>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
    <script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
    <script type="text/javascript" src="${ctx}/js/formValidator/formValidator.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
   <script type="text/javascript" src="${ctx}/resources/js/common/ConvertUtil.js" ></script>
    <script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/base.css"/>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
    <script src="${ctx}/resources/js/mes/jquery-powerdesk.js" type="text/javascript"></script>
    <script src="${ctx}/resources/js/common/MaskLayer.js" type="text/javascript"></script>
    <link href="${ctx}/resources/css/mes/thickbox.css" media="screen" rel="stylesheet" type="text/css"/>
</head>
<body>

<form action="${ctx }/bis/bis-fact-yu-s!searchForStoreYuShou.action" id="searchForm" method="post">
    <input type="hidden" name="page.pageNo" id="pageNo"/>
    <div id="header">
        <div class="title_bar">
            <h2>商业预收明细</h2>

            <div class="left">
                <input type="button" class="btn_gray" style="width: 80px;" id="btnSeniorSearch" value="高级搜索"
                       onclick="showSeniorSearch();"/>
                &nbsp;&nbsp;&nbsp;&nbsp;项目：
                <input type="text" id="bisProjectName" name="bisProjectName" value="${bisProjectName}"
                       style="cursor: pointer; color: #ff0000;" readonly="true"/>
                <input class="search" type="hidden" id="bisProjectIdFact" name="bisProjectId" value="${bisProjectId}"/>
            </div>
            <div style="float:right; height:26px; margin-right:5px; margin-top:6px;">
                <div class="btn_green" onclick="clkFullScreen('${bisProjectId}');">全屏</div>
            </div>
        </div>

        <div class="bis_search" id="main_search_div">
            <!--  预收4 -->
            <table class="tb_search" style="margin-left: 5px;">
                <col/>
                <tr class="advances_dime">
                    <td align="right" id="detailLabel" width="70">租户：</td>
                    <td width="100">
                        <input class="search text" name="tenant"
                               style="width:100px;height:16px;<s:if test="''==shopStoreName||null==shopStoreName">color:#ccc;</s:if><s:else>color:#333;</s:else>"
                               title="" id="tenant4YuS" onfocus="clearInput(this);"
                               value="<s:if test="''==shopStoreName||null==shopStoreName||'搜索商家/商铺'==shopStoreName">搜索商家</s:if><s:else>${shopStoreName}</s:else>"
                               onchange="$('#layOutCdList_v0').val(this.val());"/>
                        <input type="hidden" name="shopStoreName" id="layOutCdList_v0"
                               value="<s:if test="''==shopStoreName||null==shopStoreName">搜索商家/商铺</s:if><s:else>${shopStoreName}</s:else>">
                    </td>
                    <td style="padding-left: 5px;">
                        <input id="btn_search" type="button" style="width:65px" class="btn_blue"
                               onclick="search4YuShou();" value="搜索"/>
                        <security:authorize ifAnyGranted="A_FACT_INSERT">
                            <input id="btn_new_fact" class="btn_blue" type="button" style="width:65px; "
                                   onclick="inputFactYuShou();" value="新增"/>
                        </security:authorize>
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
                            <li class="bis_fact_unclick" value="2" id="must">应收明细</li>
                            <li class="bis_fact_unclick" value="3" id="overdue">欠费明细</li>
                            <li class="bis_fact_unclick" value="1" id="fact">收费历史记录</li>
                            <li class="bis_fact_unclick" value="4" id="advances">预收明细</li>
                            <!--<li   value="5" id="payNoti" >缴费通知</li>-->
                            <li class="bis_fact_unclick" value="6" id="payIncome">营业外收入</li>
                            <li class="bis_fact_unclick" value="7" id="budget" >经营预算明细</li>
                            <li class="bis_fact_unclick" value="9" id="gysfRecord">公寓收费记录</li>
                            <input type="hidden" id="dimension" name="dimension" value="${dimension }"/>
                        </ul>
                        <ul class="advances_dime" style="    list-style-type: none;" id="search_fact_yu_s">
                            <li fact="true"  valu='1'>审核通过</li>
                            <li fact="true"  valu='0'>等待审核</li>
                            <li fact="true"  valu='2'>审核驳回</li>
                            <li fact="true" id="factAll" valu='' style="color:red;">全部</li>
                            <li fact="true" valu='n' style="color:#464646">快速搜索:</li>
                        </ul>
							<span id="pageHtml" style="margin-left:10px;"></span>
                        <span style="margin-top:8px;margin-left:20px;float:left ;color:red;display:none;line-height:20px;height:20px;"
                              id="factoptip"></span>
                    </td>
                </tr>
            </table>
        </div>

        <div id="seniorSearchDiv"
             style="position:absolute; width:650px; height:95px; top:31px; left:8px; display:none; background-color:#fff; border:1px solid #000; padding:10px 20px 10px 0px; z-index:8;">
            <table class="tb_search" cellpadding="0" cellspacing="0">
                	<col width="80">
                    <col width="130">
                    <col width="100">
                    <col width="130">
                    <col width="75">
                    <col width="130">

                <tr id="must_fact_senior" class="fact_dime" style="display:none;">
                    <td style="text-align: right;">预收日期：</td>
                    <td>
                        <input class=input type="text"
                        onfocus="WdatePicker()"
                        name="factInBegin" id="factInBegin" style="cursor: pointer;"/>
                    </td>
                    <td style="text-align: right;">到：</td>
                    <td>
                        <input class=input type="text"
                        onfocus="WdatePicker()"
                        name="factInEnd" id="factInEnd" style="cursor: pointer;"/>
                    </td>
                    <td style="text-align: right;">状态：</td>
                    <td>
                        <s:select list="@com.hhz.ump.util.DictMapUtil@getMapBisFactStatus()" listKey="key"
                                  listValue="value" name="statusCd" id="statusCd"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">创建人：</td>
                    <td>
                        <input name="creatorQ" id="creatorQ" type="text"/>
                        <input name="creator" id="creator" type="hidden"/>
                    </td>
                    <td style="text-align: right;">实收日从：</td>
                    <td>
                        <input onfocus="WdatePicker()" name="minMonth" id="minMonth" type="text"/>
                    </td>
                    <td style="text-align: right;">到：</td>
                    <td>
                        <input onfocus="WdatePicker()" name="maxMonth" id="maxMonth" type="text"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">审核人：</td>
                    <td>
                        <input name="checkUserCdQ" id="checkUserCdQ" type="text"/>
                        <input name="checkUserCd" id="checkUserCd" type="hidden"/>
                    </td>
                    <td style="text-align: right;">预收金额：</td>
                    <td>
                        <input class=input type="text" name="minMoney" id="minMoney" style="cursor: pointer;"/>
                    </td>
                    <td style="text-align: right;">到：</td>
                    <td>
                        <input class=input type="text" name="maxMoney" id="maxMoney" />
                    </td>
                </tr>
                <tr>
                    <td colspan="6" style="padding-top:10px; text-align:center;">
                        <input type="button" class="btn_blue" onClick="search4YuShou();closeSeniorSearch();"
                               value="搜索"/>
                        <input type="button" class="btn_red" onClick="clearSearch();" value="清空"/>
                        <input type="button" class="btn_green" onClick="closeSeniorSearch();" value="关闭"/>
                    </td>
                </tr>

            </table>
        </div>


    </div>
</form>
<div id="addContent"
     style="display:none;clear:both;height:115px;margin:0px;padding:5px;border-bottom:1px solid #dddbdc;background:#f7f7f7;">
</div>

<div id="welcom" style="clear:both;height:30px;width:80%">
    <div style="color:#6BAD42;font-size:16px;font-weight:bold;width:auto;margin-top:200px;text-align:center;">
        请选择搜索条件
    </div>
</div>
<div id="detailPanel" style="clear:both;width:100%">
    <div id="detailFor" style="clear:both;">
    </div>
</div>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.fact.js"></script>
<script type="text/javascript">
    var uiid = '<%=SpringSecurityUtils.getCurrentUiid()%>';
    layOutCdStore = '<%=DictContants.BIS_CONT_TYPE_STORE%>';
    layOutCdFlat = '<%=DictContants.BIS_CONT_TYPE_FLAT%>';
    layOutCdMulti = '<%=DictContants.BIS_CONT_TYPE_MULTI%>';
    chargeType02 = '14';
    chargeType03 = '12';
    chargeType38 = '13';
    var currProjectId = '${bisProjectId}';
    var currProjectName = '${bisProjectName}';
    var bisTenantId = '${bisTenantId}';
    var bisTenantName = '${currDetailName}';
    var dimension = '${dimension}';
    var year = '${factYear}';
    //显示租户、公寓、多径的空间id
    var currLayoutLabel = 'layOutCdList_v0';

    $(function () {
        $('#bisProjectId').val(currProjectId);

        initYuShou();


    });

    function clearSearch(){
        $("#creatorQ").val("");
        $("#creator").val("");
        $("#minMonth").val("");
        $("#maxMonth").val("");
        $("#checkUserCdQ").val("");
        $("#checkUserCd").val("");
        $("#minMoney").val("");
        $("#maxMoney").val("");
    }
</script>
</body>
</html>