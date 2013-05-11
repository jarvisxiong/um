<#include "/WEB-INF/content/com/template/autoimport.ftl"/>
<style type="text/css">
    div#dichong {
        padding: 10px;
    }

    div#dichong li select {
        padding-left: 0px;
    }

    div#dichong div.form_body {
        border: 0px solid #cccccc;
        padding: 19px 20px;
        padding-bottom: 10px;
        padding-top: 0px;
    }

    div#dichong div.form_body li {
        clear: both;
        height: 25px;
        line-height: 25px;
        margin-bottom: 5px;
        border-bottom: 1px dashed #E4E7EC;
        margin-bottom: 0px;
        padding: 5px 0;
    }

    div#dichong div.form_body li label {
        width: 60px;
        border: 0px solid red;
        display: block;
        float: left;
        text-align: right
    }

    div#dichong div.form_body li label.max {
        width: 75px;
    }

    div#dichong div.form_body li label.max2 {
        width: 75px;
    }

    div#dichong div.form_body li input, div.form_body li textarea, div.form_body li select {
        float: left;
        width: 100px;
        padding-left: 5px
    }

    div#dichong div.form_body li input.max {
        width: 611px;
    }

    div#dichong div.form_body li input.min {
        width: 47px;
    }

    div#dichong div.form_body li input.disabled, div.form_body li textarea.disabled {
        background: #dedede;
    }

    div#dichong div.form_body li.hover {
        padding: 5px 5px 4px 5px;
    }

    div#dichong div.form_body li.hover button {
        display: none;
    }

    div#dichong div.form_body li.hover:hover {
        border-color: gray;
    }

    div#dichong div.form_body li.hover:hover button {
        display: block;
    }

</style>

<div id="dichong">
    <div class="form_body condition_panel  ">
        <ul class="clearfix">
            <form id="frmAddAdvance2" method="post" action="bis-fact-yu-s!dichongSave.action">
                <input type="hidden" name="id" value="${model.bisFactId}"/>
                <input type="hidden" name="dichongStatus" id="dichongStatus" />
                <li style="padding-bottom: 10px;" class="informations none"></li>
                <li style="text-align:center;"><h2>可用金额：<span style="color:red;">#{model.surplusMoney}</span></h2></li>
                <li>
                    <label class="max2">抵充金额：</label><input maxlength="8" type="text" class="text required number"
                                                            style="width:100px;" id='money' name='useMoney'
                                                            value="#{model.surplusMoney}"/>
                    <label class="max2">类别：</label>
                    <select class="box required" style="width:111px; padding-left: 2px;" name="chargeTypeCd"
                            id="chargeTypeCdInput" class="box">
                        <option value="">--请选择--</option>
                    <#list chargeTypes?keys as key>
                        <#if key_index!=0>
                            <option value="${key}">${chargeTypes.get(key)}</option>
                        </#if>
                    </#list>
                    </select>
                </li>
                <li>
                    <label class="max2">年份：</label>
                    <select class="box required" style="width:111px; padding-left: 2px;" name="factYear" id="factYear">
                        <option value="">--请选择--</option>
                    <#list 2008..helper.currentDate?string("yyyy")?number as year>
                        <option value="#{year}"
                                <#if year==helper.currentDate?string("yyyy")?number>selected="selected" </#if>>#{year}</option>
                    </#list>
                    </select>
                    <label class="max">月：</label>
                    <select class="box required" style="width:111px;  padding-left: 2px;" name="factMonth"
                            id="factMonth">
                        <option value="">--请选择--</option>
                    <#list 1..12 as month>
                        <option value="#{month}"
                                <#if month==helper.currentDate?string("MM")?number>selected="selected" </#if>>#{month}</option>
                    </#list>
                    </select>
                </li>
                <li>
                    <label class="max2">备注：</label><input type="text" class="text" name="remark" style="width:288px"
                                                          name="t10"/>
                </li>
                <li class="buttons clearfix" style="padding-left: 75px; border-bottom: none; padding-bottom: 0px;">
                    <button class="green min " type="button" style="float: left;width:66px;">保存</button>
                    <button class="red min" type="button" style="float:left; margin-left: 10px;width:65px;"
                            onclick="ymPromptClose();">取消
                    </button>
                </li>
            </form>
        </ul>
    </div>
    <script type="text/javascript">
        $("#bisProjectNameInput").val($("#bisProjectName").val());
        $("form#frmAddAdvance2 button:contains(保存)").click(function () {
            $("li.informations").hide();
            var errorMessage = powerdesk.helper.validation("#dichong .required");
            if (errorMessage && errorMessage != "") {
                powerdesk.ui.prompt.error({message:errorMessage});
            } else {
                var money = $("#frmAddAdvance2 #money");
                if (parseFloat(money.val()) > parseFloat("#{model.surplusMoney}")) {
                    powerdesk.ui.prompt.error({message:"抵充金额不能大于可用金额！"});
                    money.trigger("focus");
                    return;
                } else if (parseFloat("#{model.surplusMoney}") < 1) {
                    powerdesk.ui.prompt.error({message:"可用金额为 “#{model.surplusMoney}”，不能进行抵充！"});
                    money.trigger("focus");
                    return;
                }
                try {
                    TB_showMaskLayer("正在发送数据...", 5000);
                    $("form#frmAddAdvance2").ajaxSubmit(function (input) {
                        TB_removeMaskLayer()
                        if (input == 'success') {
                            alert('抵充成功!');
                            ymPromptClose();
                        } else {
                            alert('抵充失败!');
                        }
                        toFactYuShou();
                    })
                } catch (ex) {
                    alert(ex.message)
                }
            }
        });
        $("form#query button:contains(取消)").click(function () {
            $("div#publish").parent().hide();
        });
    </script>
</div>

