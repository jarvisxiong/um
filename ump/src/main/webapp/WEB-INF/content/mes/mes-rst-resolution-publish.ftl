<#include "/WEB-INF/content/com/template/autoimport.ftl"/>
<#assign security=JspTaglibs["/WEB-INF/security.tld"]>
<@security.authorize ifAnyGranted="A_MES_ADMIN">
    <#assign isAdmin=true/>
</@security.authorize>
<!--新增纪要 Form表单-->
<style type="text/css">
    .publish div.form_body  li label {
        width: 75px;
    }

    .publish div.form_body span {
        height: 25px;
    }
</style>
<#assign isEdit=((statusCd!"")!="1")/>
<@security.authorize ifAnyGranted="A_MES_ADMIN">
    <#assign isAdmin=true/>
</@security.authorize>
<div class="publish">
    <div class="informations none"></div>
    <div class="btns clearfix">

    <#if isEdit>
        <button class="blue min">完成</button>
        <button class="green min">保存</button>
    <#elseif isAdmin!false>
        <button class="blue min">编辑</button>
    </#if>
        <button class="green min">导出</button>
    <#if ((creator!)==currentUiid)&&statusCd=="1">
        <button class="green max">发送邮件</button>
    </#if>
        <button class="green min">返回</button>
    <#if (statusCd!"")!="">
        <p>
            状态：
            <span class="blue">${((statusCd!"")=="1")?string("完成","草稿")}</span>
            <#if (statusCd!"")=="1">
                <span style="color:${((emailSendCnt!0)!=0)?string("#000","red")}">邮件${((emailSendCnt!0)!=0)?string("已","未")}<#t/>
                    <#t/>发送</span>
            </#if>
        </p>
    </#if>
    </div>

    <div class="body_warp">
        <div class="title_2 ">
            <h2>${mesMeetingInfo.subject!} [决策委决议单]</h2>

            <p>
                <span><strong>纪</strong>【#{mesMeetingInfo.mesYear}】</span>
                <span>${helper.categorys.get(mesMeetingInfo.mesTypeCd!"")}</span>
                <span>${mesMeetingInfo.serialNo!}<strong>号</strong></span>
            </p>
        </div>

        <div class="form_body" style=" padding-bottom: 10px;">
            <ul class="clearfix">
                <iframe id="iframe" name="iframe" class="none"></iframe>
                <form action="mes-rst-resolution!save.action" id="input" method="post" target="iframe"
                      enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${id!}"/>
                    <input type="hidden" name="start" value="0"/>
                    <input type="hidden" name="mesMeetingInfoId" value="${mesMeetingInfo.mesMeetingInfoId!}"/>
                    <input type="hidden" name="recordVersion" value="${recordVersion!}"/>
                <#if isEdit>
                    <li>
                        <label>决策主题：</label><input type="text" regex="^.{1,40}$"
                                                   regexErrorMessage="决策主题不能大于40字符!" name="rstSubject"
                                                   style="margin-right: 10px;width: 612px;" value="${rstSubject!}"
                                                   class="text max required"/>
                    </li>
                    <li style="height: 60px;">
                        <label>决策组成员：</label>
                        <textarea class="required" readonly="true"
                                  id="groupUsersNames">${helper.dataDictionary.getUserNamesByUiids(groupUsers!"",";")}</textarea>
                        <input name="groupUsers" type="hidden" value="${groupUsers}"/>
                    </li>
                    <li>
                        <label>决策日期：</label>
                        <input type="text" class="text required" name="rstDate" readonly="true"
                               value="<#if rstDate?exists>${rstDate?string("yyyy-MM-dd")}</#if>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:pickedFunc})"/>
                        <label>编号：</label>
                        <span style="float: left;width: 50px; ">决策委【</span>
                        <input type="text" class="text min" readonly="true" value="${helper.currentDate?string("yyyy")}"
                               name="rstYear" id="rstYear"/>
                        <span style="float: left;width: 20px; ">】</span>
                        <input type="text" class="text min" style="margin-right: 10px;" readonly="true" name="serialNo"
                               value="<#if (serialNo!0)!=0>${serialNo}</#if>" id="serialNo"/>号
                    </li>
                    <li class="clearfix" style="height:300px;">
                        <label>决策内容：</label>

                        <div style="padding:0px 0 20px; float:left;">
                            <textarea id="xheditor" style="height: 300px; width: 100%"
                                      name="rstContet">${rstContet_!}</textarea>
                        </div>
                    </li>
                <#else>
                    <li>
                        <label>决策主题：</label><input type="text" regex="^.{1,40}$"
                                                   regexErrorMessage="决策主题不能大于40字符!" name="rstSubject"
                                                   style="margin-right: 10px;width: 612px;" value="${rstSubject!}"
                                                   class="text max disabled"  readonly="true"/>
                    </li>
                    <li style="height: 60px;">
                        <label>决策组成员：</label>
                        <textarea  class="disabled"
                                  readonly="true">${helper.dataDictionary.getUserNamesByUiids(groupUsers!"",";")}</textarea>
                    </li>
                    <li>
                        <label>决策日期：</label>
                        <input type="text" class="text disabled" name="rstDate" readonly="true"
                               value="<#if rstDate?exists>${rstDate?string("yyyy-MM-dd")}</#if>"/>
                        <label>编号：</label>
                        <span style="float: left;width: 50px; ">决策委【</span>
                        <input type="text" class="text min disabled" readonly="true" value="${helper.currentDate?string("yyyy")}"
                               name="rstYear" readonly="true"/>
                        <span style="float: left;width: 20px; ">】</span>
                        <input type="text" class="text min disabled" style="margin-right: 10px;" readonly="true" name="serialNo"
                               value="<#if (serialNo!0)!=0>${serialNo}</#if>" readonly="true"/>号
                    </li>
                    <li class="clearfix" style="clear: both;height: auto;">
                        <label>决策内容：</label>

                        <div id="content_temp"
                             style="float:left;border: 1px solid #C5C5C5; padding: 10px; background:#eaeaea;width:600px;">
                        ${rstContet_!}
                        </div>
                    </li>
                </#if>

                    <li class="fujian" style="height:auto;clear:both;">
                        <label>附&nbsp;&nbsp;件：</label>

                        <div>
                        <#if isEdit>
                            <p class="bt">
                                <a href="javascript://" class="add_fj">添加附件</a>&nbsp;&nbsp;|&nbsp;&nbsp;可上传小于20MB的附件
                                <span style="color:red;visibility: hidden;">【纪要内容请直接填写进下方输入区，附件纪要无效】</span>
                                <input type="file" name="file" class="fileuploader" hidefocus="true"
                                       onchange="fujianManager.change(this)"/>
                            </p>

                            <p class="none">
                                <span>第1个附件</span><img src="${url.ctx}/resources/images/mes/fujian.gif"
                                                       style="margin-left: 10px;"><a
                                    href="javascript:void(0)" style="margin-left: 10px;"
                                    class="add_fj"></a><a style="margin-left:10px;"
                                                          href="javascript://" class="delete"
                                                          onclick="fujianManager.del(this)">删除</a>
                            </p>
                        <#elseif appAttachFiles?size==0>
                            <p>无附件</p>
                        </#if>
                        <#list appAttachFiles as file>
                            <p>
                                <span>第${file_index+1}个附件</span><img src="${url.ctx}/resources/images/mes/fujian.gif"
                                                                     style="margin-left: 10px;"><a
                                    href="mes-meeting-info!download.action?filename=${file.fileName}&filerealname=${file.realFileName?url("utf-8")};"
                                    target="_blank"
                                    style="margin-left: 10px;"
                                    class="add_fj">${file.realFileName!""}</a><a style="margin-left:10px;"
                                                                                 href="javascript://" class="delete"
                                                                                 onclick="fujianManager.del(this)"
                                                                                 rel="${file.appAttachFileId!}">删除</a>
                            </p>
                        </#list>

                        </div>
                    </li>

                </form>
            </ul>
        </div>

    </div>
</div>
<form action="mes-rst-resolution!export.action" class="none" id="export" target="iframe">
    <input type="hidden" name="mesRstResolutionId" value="${id!}"/>
</form>
<script type="text/javascript">
    $(function () {
        window.pickedFunc = function () {
            $dp.$('rstYear').value = $dp.cal.getP('y');
            var rstDate = pd.helper.trim($(".publish input[name=rstDate]").val());
            $.getJSON("mes-rst-resolution!ajax.action", {action:1002, mesRstResolutionId:$.trim($("input[name=id]").val()), mesYear:rstDate.split("-")[0]}, function ($data) {
                $("#serialNo").val($data.serial);
            });
        }
        var xheditorFunction = function () {
            return $("#xheditor").xheditor({width:624,
                tools:'simple', forcePtag:false, showBlocktag:false, html5Upload:false, upMultiple:'1',
                upLinkUrl:"${url.ctx}/mes/mes-meeting-info!fileuploader.action", upLinkExt:"zip,rar,txt",
                upImgUrl:"${url.ctx}/mes/mes-meeting-info!fileuploader.action", upImgExt:"jpg,jpeg,gif,png",
                upFlashUrl:"${url.ctx}/mes/mes-meeting-info!fileuploader.action", upFlashExt:"swf",
                upMediaUrl:"${url.ctx}/mes/mes-meeting-info!fileuploader.action", upMediaExt:"avi"
            });
        }
        xheditorFunction();

        $("form#input").submit(function () {
            $(".informations").hide();
            var errorMessage = ump.helper.validation(".required");
            if (errorMessage && errorMessage != "") {
                ump.ui.prompt.error({message:errorMessage});
                TB_removeMaskLayer()
                return false;
            }
            return true;
        });
        //参会人员
        $("#groupUsersNames").ouSelect({
            showGroupFlg:true
        });
        var btns = $("div.publish .btns button").click(function () {
            var text = $(this).text();
            if (text == "编辑") {
                TB_showMaskLayer("正在发送数据...", 5000);
                $("#body").load("mes-rst-resolution!ajax.action", {id:"${id!}", action:1004}, function () {
                    TB_removeMaskLayer();
                });
            } else if (text == "完成") {
                $("input[name=start]").val("1");
                TB_showMaskLayer("正在发送数据...", 5000);
                $("form#input").trigger("submit");
            } else if (text == "保存") {
                TB_showMaskLayer("正在发送数据...", 5000);
                $("form#input").trigger("submit");
            } else if (text == "导出") {
                $("form#export").trigger("submit");
                TB_showMaskLayer("正在导出请稍后...", 5000);
            } else if (text == "发送邮件") {
                ymPrompt.confirmInfo({
                    autoClose:false,
                    message:"<p >请选决议要接收对象！</p>", width:300, height:150,
                    title:"确认对话框！",
                    btn:[
                        ['自己', 'me'],
                        ['决策成员', 'all'],
                        ['取消', 'close']
                    ], closeBtn:true, handler:function (btn) {
                        if (btn == 'close') {
                            ymPrompt.close();
                        } else {
                            var params = {action:1003, id:"${id!}"};
                            if (btn == "me") {
                                params.sendme = true;
                            }
                            $.getJSON("mes-rst-resolution!ajax.action", params, function ($input) {
                                alert($input.message);
                                ymPrompt.close();
                            });
                        }
                    }
                });
            } else if (text == "返回") {
                $("#body").load("mes-summary!input.action?mesMeetingInfoId=${mesMeetingInfo.mesMeetingInfoId!}");
            }
        });


        //表单提交结束 回调函数
        var execute = window.execute = function ($data) {
            setTimeout(TB_removeMaskLayer, 500);
            if ($data.success) {
                $("#body").load("mes-rst-resolution!publish.action", {id:$data.id}, function () {
                    var timer = new Date();
                    if ($data.start && $data.start == "1") {
                        var input = $(".publish .form_body input[name=id]");
                        $.getJSON("mes-rst-resolution!ajax.action", {action:1001, id:pd.helper.trim(input.val())}, function ($data) {
                            if (!$data.success) {
                                ump.ui.prompt.error({message:$data.message});
                                TB_removeMaskLayer()
                            } else {
                                $("#body").load("mes-rst-resolution!publish.action?id=" + input.val(), function () {
                                    ump.ui.prompt.success({message:$data.message});
                                    TB_removeMaskLayer()
                                });
                            }
                        });
                    } else {
                        ump.ui.prompt.success({message:"操作成功，已于" + timer.getHours() + ":" + timer.getMinutes() + ":" + timer.getSeconds() + " 秒保存成功!"});
                        TB_removeMaskLayer()
                    }
                });
            } else {
                ump.ui.prompt.error({message:$data.message});
                TB_removeMaskLayer()
            }
        }

        //添加附件
        var fujianManager = window.fujianManager = {
            change:function ($element) {
                var newp = $("li.fujian p:eq(1)").clone().show();
                newp.find("span").html("第" + ($("li.fujian p:gt(1)").size() + 1) + "个附件");
                newp.find("a:first").text($($element).val());
                newp.append($($element).attr("name", "fileuploader"));
                $("li.fujian div").append(newp);
                newp.show();
                $("li.fujian p:first").append(' <input type="file" name="file" class="fileuploader" hidefocus="true" onchange="fujianManager.change(this)"/>')
            }, del:function ($element) {
                var element = $($element);
                ymPrompt.confirmInfo({
                    autoClose:false, msgCls:'', message:"<p style='height:70px;line-height: 70px;'>该操作不可恢复，确定删除？</p>", width:350, height:150,
                    title:"确认对话框！", closeBtn:true, handler:function (btn) {
                        if (btn == 'ok') {
                            if (pd.helper.trim(element.attr("rel")) != "") {
                                $.getJSON("mes-meeting-info!ajax.action", {action:1001, id:element.attr("rel")}, function ($data) {
                                    if ($data.success) {
                                        element.parent().remove();
                                        $("li.fujian p:gt(1)").each(function ($index) {
                                            $("span", this).html("第" + ($index + 1) + "个附件");
                                        });
                                    }
                                    ymPrompt.close();
                                });
                                //远程删除
                            } else {
                                element.parent().remove();
                                $("li.fujian p:gt(1)").each(function ($index) {
                                    $("span", this).html("第" + ($index + 1) + "个附件");
                                });
                                ymPrompt.close();
                            }
                            return;
                        } else {
                            ymPrompt.close();
                        }
                    }
                });
            }
        };

        function resize() {
            var container = $.browser.msie ? $("html") : $(window);
            var panelTop = $(".condition_panel").is(":hidden") ? 0 : $(".condition_panel").height() + 20;
            var informationsTop = $(".informations").is(":hidden") ? 0 : $(".informations").height();
            var height = parseInt(container.height()) - (97 + panelTop + informationsTop);
            $("div.publish .body_warp").height(height);
        }

        //高度设置
        setInterval(resize, 1000);
        resize();

    });
</script>