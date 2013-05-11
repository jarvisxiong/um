<#include "/WEB-INF/content/com/template/autoimport.ftl"/>
<#assign security=JspTaglibs["/WEB-INF/security.tld"]>
<@security.authorize ifAnyGranted="A_BIS_DAY_WRITE">
    <#assign  A_BIS_DAY_WRITE=true/>
</@security.authorize>
<@security.authorize ifAnyGranted="A_BIS_DAY_MANAGE">
    <#assign  A_BIS_DAY_MANAGE=true/>
</@security.authorize>
<div class="publish">
    <div class="title">
        <a href="javascript://" class="close"
           style="float:right; margin-right: 10px; width: 20px; text-align: center;">
            <img src="${url.ctx}/resources/images/mes/x.gif">
        </a>

        <h2>
        <#if id?exists>
        ${bisProject.projectName!} ${authDate?string("yyyy年MM月dd日")} 经营日数据
        <#else>
        ${project.projectName!} ${boundaryDate?string("yyyy年MM月dd日")} 经营日数据
        </#if>
        </h2>
    </div>
    <div class="form_body">
        <ul class="clearfix">
            <iframe id="iframe" name="iframe" class="none"></iframe>
            <form action="bis-manage-day!save.action" id="input">
                <input type="hidden" id="id" name="id" value="${bisManageDayId!}"/>
                <input type="hidden" id="bisProjectId" name="bisProjectId" value="${project.bisProjectId!}"/>
                <li>
                    <label style="width:85px">超市收入：</label><input type="text" class="text required number"
                                                                  name="superMarket" regex="^\d{1,8}(\.\d{0,2})?$"
                                                                  regexMsg="超市收入：格式错误～"
                                                                  value="<#if superMarket?exists>${superMarket.toString()}</#if>"/>
                    <label class="max">百货收入：</label><input type="text" class="text required number"
                                                           regex="^\d{1,8}(\.\d{0,2})?$" regexMsg="百货收入：格式错误～"
                                                           name="departmentStore"
                                                           value="<#if departmentStore?exists>${departmentStore.toString()}</#if>"/>
                    <label class="max">影院收入：</label><input type="text" class="text required number" name="cinema"
                                                           regex="^\d{1,8}(\.\d{0,2})?$" regexMsg="影院收入：格式错误～"
                                                           value="<#if cinema?exists>${cinema.toString()}</#if>"/>
                </li>
                <li>
                    <label style="width:85px">主次力店合计：</label><input type="text" class="text required number"
                                                                    name="mainTotal" regex="^\d{1,8}(\.\d{0,2})?$"
                                                                    regexMsg="主次力店合计：格式错误～"
                                                                    value="<#if mainTotal?exists>${mainTotal.toString()}</#if>"/>
                    <label class="max">小商户营业合计：</label><input type="text" class="text required number"
                                                              name="storeTotal" regex="^\d{1,8}(\.\d{0,2})?$"
                                                              regexMsg="小商户营业合计：格式错误～"
                                                              value="<#if storeTotal?exists>${storeTotal.toString()}</#if>"/>
                    <label class="max">广场销售合计：</label><input type="text" class="text required number"
                                                             name="plazaTotal" regex="^\d{1,8}(\.\d{0,2})?$"
                                                             regexMsg="广场销售合计：格式错误～"
                                                             value="<#if plazaTotal?exists>${plazaTotal.toString()}</#if>"/>
                </li>
                <li>
                    <label style="width:85px">客流量：</label><input type="text" class="text required number"
                                                                 name="customerTotal" regex="^\d{1,8}(\.\d{0,2})?$"
                                                                 regexMsg="客流量：格式错误～"
                                                                 value="<#if customerTotal?exists>${customerTotal.toString()}</#if>"/>
                    <label class="max">车流量：</label><input type="text" class="text required number" name="carTotal"
                                                          regex="^\d{1,8}(\.\d{0,2})?$" regexMsg="车流量：格式错误～"
                                                          value="<#if carTotal?exists>${carTotal.toString()}</#if>"/>
                    <label class="max">经营日期：</label><input type="text" class="text" readonly="true"
                                                                  value="<#if authDate?exists>${authDate?string("yyyy-MM-dd")}<#else>${boundaryDate?string("yyyy-MM-dd")}</#if>"
                                                                  <#if A_BIS_DAY_MANAGE!false>name="authDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"<#else> disabled="true" </#if>/>
                </li>
                <!-- add by liuzhihui 2012-06-05 -->
                <li>
                    <label style="width:85px">宝莱(自营)：</label><input type="text" class="text required number"
                                                                    name="baolai" regex="^\d{1,8}(\.\d{0,2})?$"
                                                                    regexMsg="宝莱(自营)：格式错误～"
                                                                    value="<#if baolai?exists>${baolai.toString()}</#if>"/>
                    <label class="max">龙麦(自营)：</label><input type="text" class="text required number"
                                                              name="longmai" regex="^\d{1,8}(\.\d{0,2})?$"
                                                              regexMsg="龙麦(自营)：格式错误～"
                                                              value="<#if longmai?exists>${longmai.toString()}</#if>"/>
                    <label class="max">草上飞：</label><input type="text" class="text required number"
                                                             name="caoshangfei" regex="^\d{1,8}(\.\d{0,2})?$"
                                                             regexMsg="草上飞：格式错误～"
                                                             value="<#if caoshangfei?exists>${caoshangfei.toString()}</#if>"/>
                </li>
                <li style="height: 60px;">
                    <label style="width:85px">重大事件：</label>
                    <textarea name="manageEvent" style="overflow: auto;width:485px;">${manageEvent!"无"}</textarea>
                </li>
                <li style="height: 40px;">
                    <label style="width:85px">重大策划活动：</label>
                    <textarea name="planActiviteEvent"
                              style="overflow: auto;width:485px;">${planActiviteEvent!"无"}</textarea>
                </li>
                <li>
                    <label style="width:85px">&nbsp;</label>
                    <span style="color:red">注：若宝莱(自营)、龙麦(自营)、草上飞无数据请填写0。</span>
                </li>
                <li>
                    <div class="informations none"></div>
                    <div style="padding-left:85px;">

                    <#if ((A_BIS_DAY_WRITE!false)&&((!authDate?exists)||boundaryDate.getTime()<authDate.getTime()))||(A_BIS_DAY_MANAGE!false)>
                        <button class="green submit" style="float: left;margin-right:10px;" type="button">
                            提交
                        </button>
                    </#if>
                        <button class="green close" style="float: left;margin-right:10px;" type="button">关 闭</button>
                    <@security.authorize ifAnyGranted="A_BIS_DAY_DROP">
                        <#if id?exists>
                            <button class="red drop" style="float: left;" type="button" rel="${id!""}">
                                删除
                            </button>
                        </#if>
                    </@security.authorize>
                    </div>
                </li>
            </form>
        </ul>
    </div>
</div>
<script type="text/javascript">
    /**
     * 新增发布窗口
     */
    $(".publish").each(function () {
        $(".close", this).click(function () {
            $(".publish").jqmHide();
        });
        $(".submit", this).click(function () {
            $(".informations").hide();
            var errorMessage = powerdesk.helper.validation(".required");
            if (errorMessage && errorMessage != "") {
                powerdesk.ui.prompt.error({message:errorMessage});
                return;
            } else {
                //
                var inputs = $("form#input input");
                var count = parseInt(inputs.eq(2).val()) + parseInt(inputs.eq(3).val()) + parseInt(inputs.eq(4).val());
                if (!(parseInt(inputs.eq(5).val()) >= count)) {
                    inputs.eq(5).trigger("focus");
                    powerdesk.ui.prompt.error({message:"主次力店合计必须大于超市、百货、影院三项收入之和"});
                    return;
                }
            }
            var param = $("form#input").serializeArray();
            param[param.length] = {name:"action", value:"1001"};
            $.ajax({type:'POST', url:"bis-manage-day!ajax.action", data:$.param(param), dataType:"json", success:function ($result) {
                if ($result.success) {
                    powerdesk.ui.prompt.success({message:$result.message});
                    $(".publish").jqmHide();
                    window.gotoPager({});
                } else {
                    powerdesk.ui.prompt.error({message:$result.message});
                }
            }});
        });
        $(".drop", this).click(function () {
            if (confirm("该操作不可恢复,确定删除？")) {
                $.getJSON("bis-manage-day!ajax.action", {action:1002, id:$(this).attr("rel")}, function ($data) {
                    if ($data.success) {
                        $(".publish").jqmHide();
                        window.gotoPager({});
                    } else {
                        alert($data.message);
                    }
                });
            }
        });

    });
</script>