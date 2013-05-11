<table style="width: 100%; border:none; padding-left: 10px" cellSpacing=0 cellPadding=0>
    <tr>
        <td colspan="4" class="ti">
                            <span style="float:right;width:335px;">
                                <label>会议日期&nbsp;&nbsp;</label>
                                <input id="date" class="text"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:window.searchMeetings})"
                                       value="${date?string("yyyy-MM-dd")}" readonly="true"/>
                                <span style="padding-left: 10px;">
                                    <a href="javascript://" class="parent"
                                       rel="${helper.getCurrentDate(date,-1)?string("yyyy-MM-dd")}">前一天</a>
                                    <a href="javascript://" class="next" style="margin-left:10px;"
                                       rel="${helper.getCurrentDate(date,1)?string("yyyy-MM-dd")}">后一天</a>
                                </span>
                           </span>

            <h2 style="color: #306786;height:25px; line-height: 30px;">已预订会议室列表</h2>
        </td>
    </tr>
    <tr class="old">
        <td class="start table_title" style=" width: 120px;">会议室</td>
        <td class="table_title" style="width: 60px;">开始时间</td>
        <td class="table_title" style="width: 60px;">结束时间</td>
        <td class="table_title">会议主题</td>
    </tr>
    <tr>
        <td colspan="4" style="padding:0px;border-left: 1px solid #fff;">
            <div style="overflow:auto; overflow-x: hidden; height: 329px; " class="trs">
                <table style="width: 100%; border:none;" cellSpacing=0 cellPadding=0>
                <#list results?keys as result>
                    <tr class="<#if (result_index%2)==0>old <#elseif !result_has_next> end</#if>">
                        <td class="start" style="border-left: 0px; width: 120px;">${result}</td>
                        <td style='padding:0px;'>
                            <ul>
                                <#list results.get(result) as items>
                                    <li class="<#if !items_has_next>end</#if>"
                                        room="${result}"
                                        applicant="${items[3]}"
                                        applicant_name="${helper.dataDictionary.getUserNameByCd(items[3])}"
                                        compere="${items[4]}"
                                        compere_name="${helper.dataDictionary.getUserNameByCd(items[4])}"
                                        recorder="${items[5]}"
                                        recorder_name="${helper.dataDictionary.getUserNameByCd(items[5])}"
                                        subject="${items[6]}"
                                        participators="${items[8]}"
                                        participators_name="${helper.dataDictionary.getUserNamesByUiids(items[8],";")}"
                                        part_num="${items[9]}"
                                        date="${date?string("yyyy-MM-dd")}" start="${items[7]}" end="${items[10]}">
                                        <span class="start">${items[7]}</span>
                                        <span class="end">${items[10]}</span>
                                        <span class="topic">${items[11]}</span>
                                    </li>
                                </#list>
                            </ul>
                        </td>
                    </tr>
                </#list>
                <#if  results?size==0>
                    <tr>
                        <td class="start" style="text-align: center;">暂无会议信息....</td>
                    </tr>
                </#if>
                </table>
            </div>
        </td>
    </tr>
</table>
<script type="text/javascript">
    var lis2 = $(".rooms .trs table ul li").click(function () {
        lis2.removeClass("selected");
        $(this).addClass("selected");
    });
</script>