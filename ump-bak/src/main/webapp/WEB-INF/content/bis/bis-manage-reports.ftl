<#include "/WEB-INF/content/com/template/autoimport.ftl"/>
<#assign security=JspTaglibs["/WEB-INF/security.tld"]>
<!DOCTYPE HTML>
<html style="overflow:auto ;">
<head>
    <meta http-equiv="content-Type" content="text/html; charset=utf-8"/>
    <title>商业系统首页</title>
    <link href="${url.ctx}/resources/css/common/base.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="/static/css/thickbox.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="${url.ctx}/resources/css/mes/thickbox.css" media="screen" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        var _ctx = "${url.ctx}";
    </script>
    <script src="${url.ctx}/resources/js/jquery/jquery.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/mes/jquery-powerdesk.js" type="text/javascript"></script>
    <style type="text/css">
        #header .title1 span {
            height: 45px;
            line-height: 40px;
        }

        #navigation {
            margin: 10px;
        }

        #navigation li {
            background: #cbcbcb;
            position: relative;
            float: left;
            width: 100px;
            cursor: pointer;
            height: 26px;
            line-height: 26px;
            width: 80px;
            text-align: center;
            margin-right: 5px;
            color: #333333;
        }

        #navigation li a:hover {
            text-decoration: none;
        }

        #navigation li.selected {
            background: #2D8BEF;
            color: #fff;
        }

        #navigation li.project_item {
            float: right;
            background: #00922D;
            color: #FFF;
            border: 1px solid #597125;
            margin-right: 0px;
            border: none;
        }

        #navigation  div.body {
            border: 1px solid /*#2D8BEF*/ #FFF;
            border-top-width: 2px;
            height: 30px;
            line-height: 30px;
            padding-left: 10px;
        }

        #navigation  div.body div {
            display: none;
        }

        #navigation  div.body div a {
            color: #666;
            margin: 0 10px;
            float: left;
        }

        #navigation  div.body div span {
            color: #cecece
        }

        #navigation  div.body div a.start {
            margin-left: 0px;
        }

        #navigation  div.body div span {
            float: left;
        }

        #navigation  div.body div a:hover {
            text-decoration: none;
        }

        #navigation  div.body div a.selected {
            color: #FF6500;
            background: #FFF
        }

        #navigation  div.body div a.disabled {
            color: #cecece;
            cursor: default;
        }

        #warp #body {

            margin: 10px;
            margin-top: 0px;

            /*
           New 版本
           height: auto;
           overflow: auto;
           overflow-x: hidden;*/

            /*Old 版本*/
            height: auto;
            overflow: auto;
            overflow-x: hidden;
        }

        a, area {
            outline: none;
            blr: eXPression(this.onFocus = this.blur())
        }

        :focus {
            -moz-outline-style: none;
        }
    </style>
</head>
<body>
<div id="warp">
    <div id="header">
        <div class="title1 clearfix">
            <h2 style="width: auto;margin-right: 10px;"></h2>

            <div class="btns">
                <button class="green" type="button"
                        id="project_item">项目列表
                </button>
            </div>

        </div>
    </div>
    <div id="navigation">
        <ul class="clearfix">
            <li body="finance_report" class="selected">财务报表</li>
            <li body="operate_report">经营报表</li>
            <li body="canvass_business_orders_report">招商报表</li>
            <li body="layout_report">企划报表</li>
            <li body="other_report">其他</li>
        <#--<li class="project_item">项目列表</li>-->
        </ul>
        <div class="body">
            <div id="finance_report" style="display: block;">
            <#--a href="javascript:;" class="start"
               rel="bis-manage-report.action?cash=true">集团现金流报表</a>
            <span>|</span>
            <a href="javascript:;" rel="bis-manage-report.action?operate=true">集团经营情况表</a>
            <span>|</span-->
                <a href="javascript:;" rel="bis-rental-collection-rate!cumulative.action?1=1" id="lfsj_a"
                   class='selected <@security.authorize ifNotGranted="A_RENTAL_C_R_T_VIEW"> disabled</@security.authorize>'>两费收缴率汇总</a>
                <span>|</span>
                <a href="javascript:;" rel="bis-rental-collection-rate.action?1=1"
                   class="<@security.authorize ifNotGranted="A_RENTAL_C_RATE_VIEW"> disabled</@security.authorize>">两费收缴率双周报</a>
                <span>|</span>
                <a href="javascript:;" class="disabled">能耗收支表</a>
                <span>|</span> <a href="javascript:;"
                <#--A_REPO_PROJ_QUERY,A_REPO_ARRE_QUERY,A_REPO_DETA_QUERY-->
                   class="<@security.authorize ifNotGranted="A_REPO_PROJ_QUERY"> disabled</@security.authorize>"
                   rel="bis-project-report.action?budget=true">预算执行情况表</a>
                <span>|</span>
               <a href="javascript:;"
                   class="<@security.authorize ifNotGranted="A_REPO_PROJ_QUERY"> disabled</@security.authorize>"
                   rel="bis-project-report.action?reportType=manage">经营情况表</a>
                <span>|</span>
                <a href="javascript:;"
                   class="<@security.authorize ifNotGranted="A_REPO_ARRE_QUERY"> disabled</@security.authorize>"
                   rel="bis-project-report.action?debt=true">欠费情况</a>
                <span>|</span>
                <a href="javascript:;"
                   class="<@security.authorize ifNotGranted="A_REPO_DETA_QUERY"> disabled</@security.authorize>"
                   rel="bis-project-report.action?store=true">商铺</a>
            <#--span>|</span>
         <a href="javascript:;"
            class="<@security.authorize ifNotGranted="A_REPO_DETA_QUERY"> disabled</@security.authorize>"
            rel="bis-project-report.action?walkStreet=true">步行街</a-->
                <span>|</span>
                <a href="javascript:;"
                   class="<@security.authorize ifNotGranted="A_REPO_DETA_QUERY"> disabled</@security.authorize>"
                   rel="bis-project-report.action?flat=true">公寓</a>
                <span>|</span>
                <a href="javascript:;"
                   class="<@security.authorize ifNotGranted="A_REPO_DETA_QUERY"> disabled</@security.authorize>"
                   rel="bis-project-report.action?multi=true">多经</a>
            </div>
            <div id="operate_report">
                <a href="javascript:;" rel="bis-manage-day!report.action?_=true" id="jyrb_a"
                   class="start <@security.authorize ifNotGranted="A_BIS_DAY_REPORT"> disabled</@security.authorize>">经营日报表</a>
                <span>|</span>
                <a href="javascript:;" class="disabled">商户经营分析报表</a>
            </div>
            <div id="canvass_business_orders_report">
                <a href="javascript:;" id="zld_a"
                   class="start <@security.authorize ifNotGranted="A_BIS_MAIN_QUERY"> disabled</@security.authorize>"
                   rel="bis-main-shop-report.action?_=true">主力店招商周报</a>
                <span>|</span>
                <a href="javascript:;" class="<@security.authorize ifNotGranted="A_BIS_SMALL_QUERY"> disabled</@security.authorize>"
                	rel="bis-small-shop-report.action?_=true">小商铺招商周报</a>
            </div>
            <div id="layout_report">
                <a href="javascript:;" class="start disabled">企划活动周报</a>
            </div>
            <div id="other_report">
                <a href="javascript:;" class="start disabled">资产管理台账</a>
                <span>|</span>
                <a href="javascript:;" class="disabled">设备台账</a>
            </div>
        </div>
    </div>
    <div id="body" style="position: relative;height:0px;">
        <iframe name="runtest" width="100%"
                id="debugiframe" align="center" scrolling="no" frameborder="0" leftmargin="0"
                topmargin="0"
                marginwidth="0" marginheight="0"></iframe>
        <div style="background: #FFF;fong-size:14px;font-weight:bold;width:100%;height:300px; position: absolute;top:0px; text-align:center;line-height: 300px;overflow: hidden;color:#">
            请选择查看的报表！
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        /*报表逻辑封装*/
        window.report = report = {
            /*头部元素*/
            header:{
                t1:$("#header h2"),
                t2:$("#header span")
            }, navigation:{
                /*导航区域元素*/
                container:$("#navigation"),
                tabs:$("#navigation li"),
                body:$("#navigation div.body div")
            },
            /*主体区域元素*/
            body:$("#body"),
            /*标题设置*/
            title:function (msg) {
                if (msg) {
                    $("#header .title1 span").html(msg);
                } else {
                    $("#header .title1 span").html();
                }
            },
            /*高自适应*/
            resize:function () {
                /*
            New 版本
            try {
                var windowHeight = $(window).height();
                var iframe = report.body.find("iframe");
                var iframeHeight = $(iframe.get(0).contentWindow.document.body).height();
                iframe.height(iframeHeight);
            } catch (ex) {
                alert(ex.message)
            }    */

                /**
                 * Old版本
                 */
                try {
                    var container = $.browser.msie ? $("html") : $(window);
                    var iframe = report.body.find("iframe");
                    $("#body").height(parseInt(container.height()) - 132);
                    if ($("#body").height() > iframe.get(0).contentWindow.document.body.scrollHeight) {
                        iframe.height($("#body").height());
                    } else {
                        iframe.height(iframe.get(0).contentWindow.document.body.scrollHeight);
                    }
                } catch (ex) {
                }

            },
            /*初始化导航条*/
            init:function () {
                report.navigation.tabs.filter(":not(.project_item)").click(function () {
                    report.navigation.tabs.filter(".selected").removeClass("selected");
                    $(this).addClass("selected");
                    report.navigation.body.filter(":visible").hide();
                    var _body = $(this).attr("body");
                    var href = "";
                    //去除所有a标签选中样式
                    aLink.filter(".selected").removeClass("selected");
            		if("finance_report" == _body){//财务报表
            			var dom = $("#lfsj_a");
            			if(dom.attr("class").indexOf("disabled") <= 0){
            				href = dom.attr("rel");
	            			dom.addClass("selected");
            			}
            		}else if("operate_report" == _body){//经营报表
            			var dom = $("#jyrb_a");
            			if(dom.attr("class").indexOf("disabled") <= 0){
            				href = dom.attr("rel");
	            			dom.addClass("selected");
            			}
            		}else if("canvass_business_orders_report" == _body){//招商报表
            			var dom = $("#zld_a");
            			if(dom.attr("class").indexOf("disabled") <= 0){
            				href = dom.attr("rel");
	            			dom.addClass("selected");
            			}
            		}else if("layout_report" == _body){//企划报表
            			//报表暂时未实现
            		}else if("other_report" == _body){//其他
            			//报表暂时未实现
            		}
                    report.navigation.body.filter("#" + _body).show();
                    if("" != href){
                    	//点击大报表默认查询第一个小报表
                   		report.body.find("iframe").attr("src", href); 
                    }
                });
                var aLink = report.navigation.body.find("a:not(.disabled)").click(function () {
                    var href = $(this).attr("rel");
                    var text = $(this).text();
                    if (href && href != "") {
                        report.header.t1.html(text);
                        if (text != "经营日报表") {
                            report.header.t2.html("");
                        }
                        aLink.filter(".selected").removeClass("selected");
                        $(this).addClass("selected");
                        var href = $(this).attr("rel");
                        if (text == "经营日报表") {
                        }else if (window.bisProjectId) {
                            href += "&bisProjectId=" + bisProjectId;
                        } else if (location.href.toString().indexOf("bisProjectId") != -1) {
                            href += "&bisProjectId=" + location.href.toString().split("bisProjectId=")[1];
                        } else if ("${bisProjectId}" != "") {
                            href += "&bisProjectId=${bisProjectId}";
                        }
                        $("div#body div").hide();
                        report.body.find("iframe").attr("src", href);
                    } else {
                        alert("报表URL，未完善。");
                    }
                });

                if (location.href.toString().indexOf("store=true") != -1) {
                    $("#finance_report a:eq(6)").trigger("click");
                } else {
                    $("#finance_report a:eq(0)").trigger("click");
                <@security.authorize ifAnyGranted="A_RENTAL_C_RATE_VIEW">
                    $("div#body div").hide();
                </@security.authorize>
                }
                $("#project_item").click(function () {
                    var tab = parent.TabUtils;
                    var url = "${url.ctx}/bis/bis-manage.action?report=false";
                    if (tab == null) {
                        window.open(url);
                    } else {
                        parent.TabUtils.newTab("19870422", "项目列表", url, true);
                        /*
                        if ($("#195_tab", parent.document).size() != 0) {
                            tab.closeTab({data:{tabId:"195"}});
                        }
                        tab.newTab("195", "项目列表", url, true);*/
                    }
                });

            }
        }
        report.init();
        //高度设置
        setInterval(report.resize, 400);
    });
</script>
</body>
</html>
