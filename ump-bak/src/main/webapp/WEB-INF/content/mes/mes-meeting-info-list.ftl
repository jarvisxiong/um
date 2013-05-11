<#assign p=JspTaglibs["/WEB-INF/power-tags.tld"]>
<#include "/WEB-INF/content/com/template/autoimport.ftl"/>
<#assign security=JspTaglibs["/WEB-INF/security.tld"]>
<#assign input={"recodeType":Parameters.recodeType!"","status":Parameters.status!"0"}/>
<@security.authorize ifAnyGranted="A_MES_ADMIN">
    <#assign isAdmin=true/>
</@security.authorize>
<@security.authorize ifAnyGranted="A_MES_READ_ALL">
    <#assign isReadAll=true/>
</@security.authorize>
<!--List列表-->
<div class="meetings_list">
    <div class="title1  none">
        <h2>纪要列表</h2>
    </div>
    <div class="quickSearch">
        <div class="clearfix">
            <ul class="navtabs">
            <#if (input.recodeType=="0")>
                <li<#if input.status=="0"> class="selected"</#if> style="margin-left:0px;"><a
                        href="javascript://">填写/审批</a></li>
                <li<#if input.status=="1"> class="selected"</#if>><a href="javascript://">审批中</a></li>
                <li<#if input.status=="2"> class="selected"</#if> style="margin-right: 15px;"><a
                        href="javascript://">完成</a></li>
                </li>
            </#if>
                <li class="child">
                    <div class="box clearfix">
                        <ul>
                            <li class="lc">
                                类型选择
                                <div class="category none">
                                <#list helper.categorys?keys as key>
                                    <#if key_index!=0>
                                        <a href="javascript://" rel="${key}">${helper.categorys.get(key)}</a>
                                    </#if>
                                </#list>
                                    <div style="clear:both;width:394px; padding:1px;display: block; padding-top: 0px;"
                                         class="clearfix">
                                        <button class="green" style="float: left;width:78px;margin-right: 1px;">清空
                                        </button>
                                        <button class="green "
                                                style="float: left;width:78px;margin-right: 1px;display: none;">全选
                                        </button>
                                        <button class="green" style="float: left;width:78px;margin-right: 1px;">反选
                                        </button>
                                        <button class="green" style="float: left;width:78px; margin-right: 1px;">确定
                                        </button>
                                    </div>
                                </div>
                            </li>
                            <li style="width: 157px; padding:0 0px;">
                                <input class="text" name="subject_like" value="${Parameters.subject!}"
                                       style="float: left; height: 14px; margin-top: 2px; margin-left: 2px; padding: 2px; margin-right: 2px; width: 147px;background:#FFF url(${url.ctx}/resources/images/desk/wab/search.gif) no-repeat 134px 1px"/>
                            </li>
                            <li style="width: 58px;">高级搜索</li>
                            <li style="border-right: none;" class="lc">
                            <#if (page.getOrderBy()!"")=="mesTypeCd">
                                纪要类型
                            <#elseif (page.getOrderBy()!"")=="startDate">
                                完成时间
                            </#if>

                                <div class="sort none">
                                    <a href="javascript://"<#if (page.getOrderBy()!"")=="mesTypeCd">
                                       class="selected"</#if> rel="mesTypeCd">纪要类型</a>
                                    <a href="javascript://"<#if (page.getOrderBy()!"")=="startDate">
                                       class="selected"</#if> rel="startDate">完成时间</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="pager">
                <@p.page pageInfo="page"/>
                </li>
            </ul>
        </div>
    </div>
    <div class="meetings_list_body clearfix" style="padding-top: 3px;">
        <ul class="clearfix">
        <#list  page.result as value>

            <li rel="${value.mesMeetingInfoId!""}" status="${value.statusCd!""}"
                <#if ((value.creator==currentUiid && value.statusCd==0)||
                (value.sumUserCd==currentUiid&&value.statusCd==1)||
                (value.userCd==currentUiid&&value.statusCd==2)||
                (value.sumUserCd==currentUiid&&value.statusCd==4))&&(isAdmin!false||isReadAll!false)>style="background: #FFFF99"</#if>>
                <h2>
                    <#if (value.userCd!"")!="">
                        <span style="float:right">${helper.dataDictionary.getUserNameByCd(value.userCd!"")!""}</span>
                    </#if>
                    <span style="font-weight:bold;"><#if input.recodeType!="2">${value.mesTitle!}<#else>${value.rstSubject!}</#if></span>
                    <span class="people">
                    ${helper.dataDictionary.getUserNameByCd(value.sumUserCd!"")!""}
                    </span>
                    <span class="status" style="font-weight:bold;">【${helper.getStatus(value.statusCd!"")}】</span>
                </h2>

                <p>
                    <span style="float:right; text-align: right; width:150px;">
                        <#if ((value.emailSendCnt!0)!=0)><img
                                src="${url.ctx}/resources/images/mes/alreadysend.png?_"
                                style="margin-right: 5px; margin-top: 2px;"
                                align="absmiddle"/>
                        </#if>
                        <#if value.isRst!false><img
                                src="${url.ctx}/resources/images/mes/rst.gif" style="margin-right: 5px;"
                                align="absmiddle"/></#if>
                        <#if (value.attachFlg!"")=="1">
                        <img src="${url.ctx}/resources/images/bid/atta.gif" style="margin-right: 5px;" align="absmiddle"/>
                        </#if>
                        <#if value.startDate?exists>${value.startDate?string("MM-dd HH:mm")}</#if></span>
                    <span>纪【#{value.mesYear}】</span>
                    <span>${helper.categorys.get(value.mesTypeCd!"")}</span>
                    <span>${value.serialNo!}号</span>
                    - ${helper.dataDictionary.getUserNameByCd(value.compere!"")!""}
                    -【${value.fromDate?string("MM-dd HH:mm")}-${value.toDate?string("HH:mm")}】


                <#--<span class="clearfix">编号：纪【#{value.mesYear}】${helper.categorys.get(value.mesTypeCd!"")} ${value.serialNo!}号</span>
            <span >创建人：<b style="color:#333;font-weight: normal;">${helper.dataDictionary.getUserNameByCd(value.creator!"")}</b></span>
            <span style="margin-left:10px;">纪要人：<b style="color:#333;font-weight: normal;">${helper.dataDictionary.getUserNameByCd(value.sumUserCd!"")}</b></span>
            <span style="margin-left:10px;">主持人：<b style="color:#333;font-weight: normal;">${helper.dataDictionary.getUserNameByCd(value.compere!"")}</b></span>
            <#if value.statusCd=="2">
                <span style="margin-left:10px;">当前审批人：<b style="color:#333;font-weight: normal;">${helper.dataDictionary.getUserNameByCd(value.userCd!"")}</b></span>
            </#if>
            <span style="margin-left:10px;">结束日期：<b style="color:#333;font-weight: normal;">【${value.fromDate?string("MM-dd HH:mm")}-${value.toDate?string("HH:mm")}】</b></span>-->
                </p>

                <div class="clear"></div>
            </li>
        </#list>
        </ul>
    </div>
</div>
<script type="text/javascript">
    $(function () {

        //高级搜索 纪要分类集合 引用
        var categorys = $("div.quickSearch div.category a");
        //快速搜索分类框
        var boxs = $(".condition_panel li.category input");

        /**标签 事件注册*/
        $("div.quickSearch ul.navtabs li").click(function () {
            switch ($(this).text()) {
                case "填写/审批":
                    $("form#query input[name=status]").val(0);
                    window.gotoPager({});
                    break;
                    break;
                case "审批中":
                    $("form#query input[name=status]").val(1);
                    window.gotoPager({});
                    break;
                case "完成":
                    $("form#query input[name=status]").val(2);
                    window.gotoPager({});
                    break;
                case "高级搜索":
                    $(".condition_panel").toggle();
                    break;
                default:
                    break;
            }
        });
        //设置分类信息
        var setCategorys = function () {
            categorys.each(function () {
                boxs.filter("[value=" + $(this).attr("rel") + "]").attr("checked", $(this).hasClass("selected"));
            });
        }
        categorys.click(function () {
            if ($(this).hasClass("selected")) {
                $(this).removeClass("selected");
            } else {
                $(this).addClass("selected");
            }
            if (!$(this).parent().hasClass("sort")) {
                setCategorys();
                cbtns.filter(":contains(清空),:contains(全选)").hide();
                var tempCategory = $("div.quickSearch ul.navtabs li.child div li.lc:first a");
                if (tempCategory.size() != tempCategory.filter(".selected").size()) {
                    cbtns.filter(":contains(全选)").show();
                } else {
                    cbtns.filter(":contains(清空)").show();
                }
            } else {

            }
        });

        $("div.quickSearch div.sort a").click(function () {
            $("#header input:[name=page.orderBy]").val($(this).attr("rel"));
            window.gotoPager({});
        });

        var cbtns = $("div.quickSearch ul.navtabs li.child .category button").click(function () {
            var text = pd.helper.trim($(this).text());
            if (text == "全选") {
                categorys.addClass("selected");
                $(this).hide();
                cbtns.filter(":contains(清空)").show();
            } else if (text == "反选") {
                categorys.each(function () {
                    if ($(this).hasClass("selected")) {
                        $(this).removeClass("selected");
                    } else {
                        $(this).addClass("selected");
                    }
                });

                cbtns.filter(":contains(清空),:contains(全选)").hide();
                var tempCategory = $("div.quickSearch ul.navtabs li.child div li.lc:first a");
                if (tempCategory.size() != tempCategory.filter(".selected").size()) {
                    cbtns.filter(":contains(全选)").show();
                } else {
                    cbtns.filter(":contains(清空)").show();
                }

            } else if (text == "清空") {
                categorys.removeClass("selected");
                $(this).hide();
                cbtns.filter(":contains(全选)").show();
            } else if (text == "确定") {
                window.gotoPager({});
            }
            setCategorys();
        });
        $("div.quickSearch ul.navtabs li.child li:lc:first").hover(function () {
            categorys.removeClass("selected");
            boxs.filter(":checked").each(function () {
                categorys.filter("[rel=" + $(this).val() + "]").addClass("selected");
            });

            cbtns.filter(":contains(清空),:contains(全选)").hide();
            var tempCategory = $("div.quickSearch ul.navtabs li.child div li.lc:first a");
            if (tempCategory.size() != tempCategory.filter(".selected").size()) {
                cbtns.filter(":contains(全选)").show();
            } else {
                cbtns.filter(":contains(清空)").show();
            }
        });
        //纪要列表  点击 加载详细 内容，根据状态加载不同 页面
        $(".meetings_list_body li").click(function () {
            var index = parseInt($(this).attr("status"));
            switch (index) {
                case 0:
                    $("#body").load("mes-meeting-info!input.action?id=" + $(this).attr("rel"));
                    break;
                default:
                    $("#body").load("mes-summary!input.action?mesMeetingInfoId=" + $(this).attr("rel"));
                    break;
            }
            window.pagerindex = $("#pageNo").val();
        });
        //鼠标经过 改变背景色
        var lis = $(".meetings_list li").hover(function () {
            $(".meetings_list li.hover").removeClass("hover");
            $(this).addClass("hover");
        }, function () {
            $(this).removeClass("hover");
        });
        //快速搜索 标题输入框 事件注册
        var subject_like = $(".meetings_list input[name=subject_like]").bind("keydown", function (event) {
            if (event.keyCode == 13 || event.keyCode == 32) {
                $("div.condition_panel input[name=subject]").val(subject_like.val());
                window.gotoPager({});
                subject_like.trigger("focus");
            }
        });
        //快速搜索框 默认获取 焦点
        setTimeout(function () {
            subject_like.trigger("focus");
            subject_like.val(subject_like.val());
        }, 30);
        //高度自适应
        function resize() {
            var container = $.browser.msie ? $("html") : $(window);
            var panelTop = $(".condition_panel").is(":hidden") ? 0 : $(".condition_panel").height() + 21;
            $("div.meetings_list .meetings_list_body").height(container.height() - 100 - panelTop);
        }

        //高度设置
        //setInterval(resize, 1000);
        //resize();
    })
</script>