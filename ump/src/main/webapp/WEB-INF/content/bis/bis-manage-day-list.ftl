<#assign p=JspTaglibs["/WEB-INF/power-tags.tld"]>
<#assign security=JspTaglibs["/WEB-INF/security.tld"]>
<style>
    #pdChilltip {
        background: red;
    }
.table_pager {
	float: right;
	margin: 5px 0;
}
.table_pager input{width:24px;height:24px;border:1px solid #ccc;}
</style>
<div id="bisManageDayList" style="overflow: auto;overflow-x: hidden;">
    <table class="stat_table">
        <colgroup>
            <col width="9%">
            <col width="5%">
            <col width="5%">
            <col width="5%">
            <col width="9%">
            <col width="9%">
            <col width="9%">
            <col width="9%">
            <col width="9%">
            <col width="13%">
            <col width="13%">
            <col width="5%">
        </colgroup>
        <thead>

        <#if Parameters.bisProjectId?exists&&(Parameters.bisProjectId!"")!="">
        <tr>
            <th colspan="12" style="line-height:30px;">
                <div class="stat_title">
                ${project.projectName!}-经营日数据回顾
                </div>
            </th>
        </tr>
        </#if>
        <tr style="display: table-row;">
            <th>日期</th>
            <th>超市</th>
            <th>百货</th>
            <th>影院</th>
            <th>主次力店合计</th>
            <th>小商户营业合计</th>
            <th>广场销售合计数</th>
            <th>客流(万人次)</th>
            <th>车流量(辆次)</th>
            <th>重大营运事件</th>
            <th>重大策划活动</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody style="display: table-row-group;">
        <#assign currentDate=helper.currentDate/>
        <#list  page.result as value>
        <tr rel="${value.bisManageDayId!}">
            <td style="text-align: center;">
            ${value.authDate?string("yyyy-MM-dd")}
            </td>
            <td style="padding-right: 10px;">${value.superMarket?string("0.00")}</td>
            <td style="padding-right: 10px;">${value.departmentStore?string("0.00")}</td>
            <td style="padding-right: 10px;">${value.cinema?string("0.00")}</td>
            <td style="padding-right: 10px;">${value.mainTotal?string("0.00")}</td>
            <td style="padding-right: 10px;">${value.storeTotal?string("0.00")}</td>
            <td style="padding-right: 10px;">${value.plazaTotal?string("0.00")}</td>
            <td style="padding-right: 10px;">${value.customerTotal?string("0.00")}</td>
            <td style="padding-right: 10px;">${value.carTotal?string("0.00")}</td>
            <td>
                <div class="span_show pd-chill-tip" title='${(value.manageEvent!"")?replace("\n","<br>")}'>
                    <#if (value.manageEvent!"")=="">
                        无数据
                    <#elseif (value.manageEvent?length>4)>
                    <a href="javascript://">${value.manageEvent?substring(0,4)}...
                    <#else>
                    ${value.manageEvent!}
                    </#if>
                </div>
            </td>
            <td>
                <div class="span_show pd-chill-tip" style="padding-right: 10px;"
                     title="${(value.planActiviteEvent!"")?replace("\n","<br>")}">
                    <#if (value.planActiviteEvent!"")=="">
                        无数据
                    <#elseif (value.planActiviteEvent?length>4)>
                    ${value.planActiviteEvent?substring(0,4)}...
                    <#else>
                    ${value.planActiviteEvent!}
                    </#if>
                </div>
            </td>
            <td style="text-align: center;">
            <#--${boundaryDate?string("yyyy-MM-dd HH:mm:ss")}<br/>${value.authDate?string("yyyy-MM-dd HH:mm:ss")}<br/>-->
                <#if (boundaryDate.getTime()>value.authDate.getTime())>
                    确认
                <#else>
                    新增
                </#if>
            </td>
        </tr>
        </#list>

        <tr class="no">
            <td colspan="12" style="text-align:left;">
            <#if page.result?size==0>
                <div align="center" style="font-weight:bold;height:80px;line-height:80px;">没有相关记录！</div>
            <#else>
                <div class="table_pager">
					<@p.page pageInfo="page"/>
				</div>
            </#if>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    $(".stat_table tbody tr:not(.no)").click(
            function () {
                TB_showMaskLayer("正在搜索请稍后...", 5000);
                $("#publish").load("bis-manage-day!publish.action", {id:$(this).attr("rel")}, function ($html) {
                    var containerHeight = ($.browser.msie ? $("html") : $(window)).height();
                    containerHeight = (containerHeight - $(".publish").height()) / 2;
                    $(".publish").jqm().jqmShow().css("top", containerHeight).jqDrag(".title");
                    TB_removeMaskLayer();
                });
            }).hover(function () {
                $(this).addClass("hover");
            }, function () {
                $(this).removeClass("hover");
            });

    //分页事件
    window.jumpPage = function (index) {
        //出发父窗口 分页方法，到 指定的某一页
        window.gotoPager({index:index});
    }
    window.jumpPageTo = function () {
        window.jumpPage($("#pageTo").val());
    }
    doChilltipFunc();

    //高度自适应
    function resize() {
        var container = $.browser.msie ? $("html") : $(window);
        $("#bisManageDayList").height(container.height() - 100);
    }
    //高度设置
    setInterval(resize, 1000);
    resize();
</script>