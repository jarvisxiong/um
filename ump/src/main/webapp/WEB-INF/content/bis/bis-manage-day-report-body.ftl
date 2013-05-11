<#assign p=JspTaglibs["/WEB-INF/power-tags.tld"]>
<style type="text/css">
        /*th{padding:0px; height: 100px; line-height: 120px;}*/
    table th {
        height: 25px;
    }
</style>
<div id="bisManageDayList">
    <table class="stat_table">
        <colgroup>
            <col width="15%">
            <col width="5%">
            <col width="5%">
            <col width="5%">
            <col width="10%">
            <col width="8%">
            <col width="8%">
            <col width="5%">
            <col width="7%">
            <col width="12%">
            <col width="12%">
        </colgroup>
        <thead>
        <tr>
            <th colspan="11" style="line-height:30px;">
            <#if isMonth?exists>
                <div class="stat_bar" style="float:left;margin-left: 5px;">
                    <#if isMonth!false>上个月<#else>上一天</#if>
                </div>
                <div class="stat_bar" style="float:right;margin-right: 5px;">
                    <#if isMonth!false>下个月<#else>下一天</#if>
                </div>
            </#if>
                <div class="stat_title">
                <#if startDate?exists>
                ${startDate}
                    <#if startDate?split("-")?size==3>
                        日
                    <#else>
                        月
                    </#if>
                    <script type="text/javascript">
                        $(function () {
                            var queryInput = $(".condition_panel input:gt(2)");
                            $(".condition_panel input[name=startDate]").val("${startDate}");
                            <#if startDate?split("-")?size==3>
                                queryInput.eq(1).val("${startDate}");
                            <#else>
                                queryInput.eq(0).val("${startDate}");
                            </#if>
                        });
                    </script>
                </#if>
                    经营报表
                </div>
            </th>
        </tr>
        <tr style="display: table-row;">
            <th rowspan="2">项目</th>
            <th rowspan="1" colspan="4">主次力店营业额（万元）</th>
            <th rowspan="2">小商户营业合计（万元）</th>
            <th rowspan="2">广场销售合计数)（万元）</th>
            <th rowspan="2">客流(万人次)</th>
            <th rowspan="2">车流量(辆次)</th>
            <th rowspan="2">重大营运事件</th>
            <th rowspan="2">重大策划活动</th>
        </tr>
        <tr style="display: table-row;">
            <th>超市</th>
            <th>百货</th>
            <th>影院</th>
            <th>主次力店合计</th>
        </tr>
        </thead>
        <tbody style="display: table-row-group;">
        <#assign currentDate=helper.currentDate/>
        <#list values as value>
        <tr key=".index_${value_index}" <#if isMonth?exists&&!isMonth&&value[12]?exists&&value[12]?is_collection>
            class="child"
            <#list value[12] as child>
                <#if child.authDate?string("yyyy-MM-dd")==startDate>
            rel="${child.bisManageDayId!}"
                </#if>
            </#list>
        </#if>>
            <#if !value_has_next>
                <td style="text-align: right;">合计：</td>
            <#else>
            	<#if hasDay==1>
	                <td style="text-align: center;<#if value[13]==0>color:red</#if>">
	                ${value[1]}
	                </td>
                <#elseif hasDay==0>
                	<td style="text-align: center;<#if value[12]?size!=monthOfDays>color:red</#if>">
	                ${value[1]}
	                </td>
	            </#if>
            </#if>
            <td style="padding-right: 10px;">${value[2].toString()?number?string("0.00")}</td>
            <td style="padding-right: 10px;">${value[3].toString()?number?string("0.00")}</td>
            <td style="padding-right: 10px;">${value[4].toString()?number?string("0.00")}</td>
            <td style="padding-right: 10px;">${value[5].toString()?number?string("0.00")}</td>
            <td style="padding-right: 10px;">${value[6].toString()?number?string("0.00")}</td>
            <td style="padding-right: 10px;">${value[7].toString()?number?string("0.00")}</td>
            <td style="padding-right: 10px;">${value[8].toString()?number?string("0.00")}</td>
            <td style="padding-right: 10px;">${value[9].toString()?number?string("0.00")}</td>
            <td style="padding-right: 10px;">
                <#if isMonth?exists&&!isMonth&&value[12]?exists&&value[12]?is_collection>
                    <#list value[12] as child>
                        <#if child.authDate?string("yyyy-MM-dd")==startDate>
                            <div class="pd-chill-tip" title="${(child.manageEvent!"")?replace("\n","")}">
                                <#if (child.manageEvent!"")=="">
                                    无数据
                                <#elseif (child.manageEvent?length>4)>
                                ${child.manageEvent?substring(0,4)}...
                                <#else>
                                ${child.manageEvent!}
                                </#if>
                            </div>
                        </#if>
                    </#list>
                <#else>
                ${value[10]?number}条
                </#if>
            </td>
            <td style="padding-right: 10px;">
                <#if isMonth?exists&&!isMonth&&value[12]?exists&&value[12]?is_collection>
                    <#list value[12] as child>
                        <#if child.authDate?string("yyyy-MM-dd")==startDate>
                            <div class="pd-chill-tip"
                                 title="${(child.planActiviteEvent!"")?replace("\n","")}">
                                <#if (child.planActiviteEvent!"")=="">
                                    无数据
                                <#elseif (child.planActiviteEvent?length>4)>
                                ${child.planActiviteEvent?substring(0,4)}...
                                <#else>
                                ${child.planActiviteEvent!}
                                </#if>
                            </div>
                        </#if>
                    </#list>
                <#else>
                ${value[11]?number}条
                </#if>
            </td>
        </tr>
            <#if isMonth!false&&value[12]?exists&&value[12]?string!="0">
                <#list value[12] as child>
                <tr rel="${child.bisManageDayId!}" class="index_${value_index} none child">
                    <td>${child.authDate?string("yyyy-MM-dd")}</td>
                    <td>${(child.superMarket!"")?string("0.00")}</td>
                    <td>${child.departmentStore?string("0.00")}</td>
                    <td>${child.cinema?string("0.00")}</td>
                    <td>${child.mainTotal?string("0.00")}</td>
                    <td>${child.storeTotal?string("0.00")}</td>
                    <td>${child.plazaTotal?string("0.00")}</td>
                    <td>${child.customerTotal?string("0.00")}</td>
                    <td>${child.carTotal?string("0.00")}</td>
                    <td>
                        <div class="pd-chill-tip" title="${(child.manageEvent!"")?replace("\n","")}">
                            <#if (child.manageEvent!"")=="">
                                无数据
                            <#elseif (child.manageEvent?length>4)>
                            ${child.manageEvent?substring(0,4)}...
                            <#else>
                            ${child.manageEvent!}
                            </#if>
                        </div>
                    </td>
                    <td>
                        <div class="pd-chill-tip"
                             title="${(child.planActiviteEvent!"")?replace("\n","")}">
                            <#if (child.planActiviteEvent!"")=="">
                                无数据
                            <#elseif (child.planActiviteEvent?length>4)>
                            ${child.planActiviteEvent?substring(0,4)}...
                            <#else>
                            ${child.planActiviteEvent!}
                            </#if>
                        </div>
                    </td>
                </tr>
                </#list>
            </#if>
        </#list>
        <#if values?size==0>
        <tr class="no">
            <td colspan="11">
                <div align="center" style="font-weight:bold;height:80px;line-height:80px;">没有相关记录！</div>
            </td>
        </tr>
        </#if>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    var trs = $(".stat_table tbody tr:not(.no,.child,:last)").click(function () {
        $(".stat_table tbody tr.child").hide();
        if ($(this).hasClass("hover")) {
            trs.removeClass("hover");
        } else {
            trs.removeClass("hover");
            $(this).addClass("hover");
            $($(this).attr("key"), ".stat_table").show();
        }
    });
    $(".stat_table tbody tr.child").click(function () {
        /*$("#publish").load("bis-manage-day!publish.action", {id:$(this).attr("rel"), "href":"report"}, function ($html) {
            var containerHeight = ($.browser.msie ? $("html") : $(window)).height();
            containerHeight = (containerHeight - $(".publish").height()) / 2;
            $(".publish").jqm().jqmShow().css("top", containerHeight).jqDrag(".title");
        });*/
    });
    var bars = $(".stat_bar").click(function () {
        window.gotoPager({bar:bars.index(this)});
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
    //setInterval(resize, 1000);
    //resize();
    <#if isMonth!false>
        parent.report.header.t2.html("(&nbsp;共计${values?size-1}个项目&nbsp;)");
    <#else>
        <#assign index=0/>
        <#list values as value>
               <#if value[13]==0><#assign index=index+1/></#if>
        </#list>
    parent.report.header.t2.html("(&nbsp;共计${values?size-1}个项目，其中有${index}个项目未提交数据&nbsp;)");
    </#if>
</script>