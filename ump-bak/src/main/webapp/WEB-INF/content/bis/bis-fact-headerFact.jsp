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
    <title>实收明细</title>
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
    <script type="text/javascript" src="${ctx}/resources/js/common/ConvertUtil.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
    <script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
    <script type="text/javascript" src="${ctx}/js/formValidator/formValidator.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
    <script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
    <script src="${ctx}/resources/js/mes/jquery-powerdesk.js" type="text/javascript"></script>
    <script type="text/javascript" charset="UTF-8" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/base.css"/>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
    <script src="${ctx}/resources/js/common/MaskLayer.js" type="text/javascript"></script>
    <link href="${ctx}/resources/css/mes/thickbox.css" media="screen" rel="stylesheet" type="text/css"/>
    <style type="text/css">
    	#finance_report{
		border-width:0px;
		color:#000;
		display:block;
		font-size:12px;
		margin:auto;
		margin-bottom:5px;
		padding:0px;
		padding-left:10px;
		line-height:25px;
		}
		.linkmenu_A{
		background-attachment:scroll;
		background-colr:rgb(255,255,255);
		background-image:none;
		border-color:#026eb6;
		cursor:pointer;
		float:left;
		font-size:12px;
		margin:-5px 10px 5px 10px;
		text-decoration: none;
		}
		.linkmenu_S{
		border-style:none;
		border-width:0px;
		color:#cecece;
		float:left;
		font-size:12px;
		margin:0px;
		padding:0px;
		}
    </style>
</head>
<body>
<form action="${ctx}/bis/bis-fact!list.action" id="searchForm" method="post">
	<input type="hidden" id="mustclickId" name="mustclick" value="${mustclick }" />
    <div id="header">
        <div class="title_bar">
            <h2>商业历史明细</h2>

            <div class="left">
                <input type="button" class="btn_gray" style="width: 80px;" id="btnSeniorSearch" value="高级搜索"
                       onclick="showSeniorSearch();"/>
                &nbsp;&nbsp;&nbsp;&nbsp;项目：
                <input type="text" id="bisProjectName"  name="bisProjectName" value="${bisProjectName}" readonly="true"
                       style="cursor: pointer; color: #ff0000;border:1px solid #333;height:16px;"/>
                <input class="search" type="hidden" id="bisProjectIdFact" name="bisProjectId" value="${bisProjectId}"/>
                &nbsp;&nbsp;&nbsp;&nbsp;业态：
                <s:select cssStyle="height:24px;line-height:20px;"
                          validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapContBigTypeNew()"
                          listKey="key" listValue="value" name="layOutCd" id="layOutCd"
                          onchange="changlayOutDetail1();" required="true">
                </s:select>
            </div>
            <div style="float:right; height:26px; margin-right:5px; margin-top:6px;">
                <div class="btn_green" onclick="clkFullScreen('${bisProjectId}');">全屏</div>
            </div>
        </div>

        <div class="bis_search" id="main_search_div">
            <!-- 3实收-->
            <table class="tb_search" style="margin-left: 5px;">
                <col/>
                <tr class="no_advances_dime">
                    <td align="right" class="flat_layout" style="display:none;" layout="flat" width="40">楼号：</td>
                    <td layout="flat" class="flat_layout" style="display:none;" width="70">
                        <input style="width:100px;height:16px;" id="flatBuding"/>
                    </td>
                    <td align="right" id="detailLabel" width="70">租户/商铺：</td>
                    <td width="100">
                        <input type="text"
                               style="width:100px;height:16px;<s:if test="''==shopStoreName||null==shopStoreName">color:#ccc;</s:if><s:else>color:#333;</s:else>"
                               title="" name="shopStoreName" id="layOutCdList_v0" required="true"
                               onfocus="clearInput(this);"
                               value="<s:if test="''==shopStoreName||null==shopStoreName">搜索商家/商铺</s:if><s:else>${shopStoreName}</s:else>"/>
                        <select style="display:none;" title="" id="layOutCdList_v1" required="true"
                                onClick="selectDetail1();"></select>
                    </td>
                    <td align="right" width="40">类别：</td>
                    <td width="100">
                        <s:select onchange="setThisTitle('chargeTypeCd');"
                                  list="@com.hhz.ump.util.DictMapUtil@getMapChargeType()" listKey="key"
                                  listValue="value" name="chargeTypeCd" id="chargeTypeCd"></s:select>
                    </td>
                   <td align="right" width="40">年月：</td>
						<td width="60" >
                    		<input style="width:60px;height:16px;" type="text" name="reportDateStart"
                           id="reportDateStart"  value="<s:if test="''==reportDateStart||null==reportDateStart"></s:if><s:else>${reportDateStart}</s:else>"
                            onfocus="WdatePicker({dateFmt:'yyyy-MM'})"/>
               			 </td>
               	     	<td align="center" width="40">至</td>
                		<td width="60" >
                    		<input validate="required" style="width:60px;height:16px;" type="text" name="reportDateEnd"
                           id="reportDateEnd" value="<s:if test="''==reportDateEnd||null==reportDateEnd"></s:if><s:else>${reportDateEnd}</s:else>"
                            onfocus="WdatePicker({dateFmt:'yyyy-MM'})"/>
                		</td>
                   
                    <td style="padding-left: 5px;">
                        <input id="btn_search" type="button" style="width:65px" class="btn_blue"
                               onclick="chargeDetailQuery();" value="搜索"/>
                        <security:authorize ifAnyGranted="A_FACT_INSERT">
                            <input id="btn_new_fact" class="btn_blue fact_dime" type="button" style="width:65px; "
                                   onclick="appendFact();" value="新增实收"/>

                            <input id="btn_imp_fact" class="btn_green" type="button" style="width:65px;"
                                   onclick="batchOper();" value="批量操作"/>
                            <input type="button" class="btn_green" onclick="doExport4Search('fact');" value="导出"/>
                        </security:authorize>
                        <security:authorize ifAnyGranted="A_FACT_CHECK">
                            <input type="button" class="btn_green" onclick="doUpdateAll(1);" value="批量审核"/>
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
                            <li value="2" class="bis_fact_unclick" id="must">应收明细</li>
                            <li value="3" class="bis_fact_unclick" id="overdue">欠费明细</li>
                            <li value="1" class="bis_fact_unclick" id="fact">收费历史记录</li>
                            <li value="4" class="bis_fact_unclick" id="advances">预收明细</li>
                            <!--<li   value="5" id="payNoti" >缴费通知</li>-->
                            <li value="6" class="bis_fact_unclick" id="payIncome">营业外收入</li>
                            <li value="7" class="bis_fact_unclick" id="budget">经营预算明细</li>
                            <li value="8" class="bis_fact_unclick" id="actInCome" style="display: none;">实收明细</li>
                            <li value="9" class="bis_fact_unclick" id="gysfRecord">公寓收费记录</li>
                            <input type="hidden" id="dimension" name="dimension" value="${dimension }"/>
                        </ul>
                        <ul class="fact_dime" style="list-style-type: none;" id="search_fact">
                            <li fact="true" id="" valu='1'>审核通过</li>
                            <li fact="true" id="" valu='0'>等待审核</li>
                            <li fact="true" id="" valu='2'>审核驳回</li>
                            <li fact="true" id="factAll" valu='' style="color:red;">全部</li>
                            <li fact="true" id="" valu='n' style="color:#464646">快速搜索:</li>
                        </ul>
                        <span id="pageHtml" style="margin-left:10px;"></span>
                        <span style="margin-top:8px;margin-left:20px;float:left ;color:red;display:none;line-height:20px;height:20px;"
                              id="factoptip"></span>
                    </td>
                </tr>
            </table>
        </div>
        <div id="finance_report">
        	<a class="linkmenu_A " style="color:#ff6500;text-decoration: none;" onclick="actIncomeJumper(1);">实收明细</a><span class="linkmenu_S ">|</span>
        	<a class="linkmenu_A " style="text-decoration: none;" onclick="actIncomeJumper(2);">实收汇总</a>
        </div>
        <!-- 页面高级检索隐藏域 -->
        <div id="seniorSearchDiv"
             style="position: absolute; width: 650px; height: 95px; top: 31px; left: 8px; background-color: rgb(255, 255, 255); border: 1px solid rgb(0, 0, 0); padding: 10px 20px 10px 0px; z-index: 8; display: block; display:none; ">
            <table cellspacing="0" cellpadding="0" class="tb_search">
                <colgroup>
                    <col width="80">
                    <col width="130">
                    <col width="100">
                    <col width="130">
                    <col width="75">
                    <col width="130">
                </colgroup>
                <tbody>
                <tr id="must_fact_senior" class="fact_dime" style="display:none;">
                    <td style="text-align: right;">预收日期：</td>
                    <td>
                        <input class=input" type="text"
                        onfocus="WdatePicker()"
                        name="factInBegin" id="factInBegin" style="cursor: pointer;"/>
                    </td>
                    <td style="text-align: right;">到：</td>
                    <td>
                        <input class=input" type="text"
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
                        <input class="Wdate" type="text" onfocus="WdatePicker()" name="minMonth" id="minMonth"/>
                    </td>
                    <td style="text-align: right;">到：</td>
                    <td>
                        <input class="Wdate" type="text" onfocus="WdatePicker()" name="maxMonth" id="maxMonth"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">审核人：</td>
                    <td>
                        <input name="checkUserCdQ" id="checkUserCdQ" type="text"/>
                        <input name="checkUserCd" id="checkUserCd" type="hidden"/>
                    </td>
                    <td style="text-align: right;">实收金额：</td>
                    <td>
                        <input class=enterQuery" type="text" name="minMoney" id="minMoney" />
                    </td>
                    <td style="text-align: right;">到：</td>
                    <td>
                        <input class=enterQuery" type="text" name="maxMoney" id="maxMoney" />
                    </td>
                </tr>
                <tr>
                    <td colspan="6" style="padding-top:10px; text-align:center;">
                        <input type="button" class="btn_blue" onClick="chargeDetailQuery();closeSeniorSearch();"
                               value="搜索"/>
                        <input type="button" class="btn_red" onClick="clearSearch();" value="清空"/>
                        <input type="button" class="btn_green" onClick="closeSeniorSearch();" value="关闭"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</form>

<div id="addContent"
     style="display:none;clear:both;margin:0px;padding:5px;border-bottom:1px solid #dddbdc;background:#f7f7f7;">
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
    //存储租户、公寓、多径等id的控件
    var currLayoutLabel = 'layOutCdList_v0';

    $(function () {
        //注册维度切换事件以及渲染样式，注册快速搜索事件
        init();
        $('#bisProjectId').val(currProjectId);
        $('#welcom').show();
        changlayOutDetail1();
        loadDefault();
        initClick();
    });
    /**跳到实收*/
    function actIncomeJumper(demo){
        if(demo==2){
			$("#actInCome").click();
        }else{
			$("#fact").click();
        }
    }
    //批量导入导出应收实收数据
    function batchOper() {
        parent.TabUtils.newTab("supInput", "批量操作", '${ctx}/bis/bis-fact!batchOper.action?bisProjectId=' + currProjectId, true);
    }
    /**初始化页面(应收)传过来的点击初始化事件*/
    function initClick(){
		if("1" == $("#mustclickId").val()){
			$("#btn_new_fact").click();
		}
    }

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