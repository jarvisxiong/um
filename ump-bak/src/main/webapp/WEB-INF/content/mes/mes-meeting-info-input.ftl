<#include "/WEB-INF/content/com/template/autoimport.ftl"/>
<!--新增纪要 Form表单-->
<#include "/WEB-INF/content/mes/mes-meeting-info-history-directory.ftl"/>
<div class="publish">
<div class="title1 none">
    <h2>新增纪要</h2>
</div>
<div class="informations none"></div>
<div class="btns clearfix">
    <button class="blue min">开始</button>
    <button class="green min">保存</button>
    <button class="red min" style="<#if (mesMeetingInfoId!"")=="">display:none;</#if>">删除</button>
    <button class="green min">返回</button>
</div>
<div class="body_warp">
    <div class="form_body" style="margin-top: 12px;">
        <ul class="clearfix">
            <iframe id="iframe" name="iframe" class="none"></iframe>
            <form action="mes-meeting-info!save.action" id="input" method="post" target="iframe"
                  enctype="multipart/form-data">
                <input type="hidden" name="id" value="${mesMeetingInfoId!}"/>
                <input type="hidden" name="entity.recordVersion" value="${recordVersion!}"/>
                <input type="hidden" name="start" value="0"/>
                <li>
                    <label>纪要标题：</label><input name="mesTitle" type="text" class="text max required" regex="^.{1,40}$"
                                               regexMsg="纪要标题不能大于40字符!"
                                               style="margin-right: 10px;width: 612px;" value="${mesTitle!""}"/>
                </li>
                <li>
                    <label>会议主题：</label><input name="subject" type="text" class="text max required" regex="^.{1,40}$"
                                               regexMessage="会议主题不能大于40字符!"
                                               style="margin-right: 10px;width: 532px;" value="${subject!""}"/>
                    <button class="blue" style="height: 24px;line-height: 24px;width: 70px;" type="button">选择会议</button>
                </li>
                <li>
                    <label>主持人：</label><input type="text" class="text required" readonly="true"
                                                                      value="${helper.dataDictionary.getUserNamesByUiids(compere!"",";")!}"
                                                                      id="compereNames"/><input
                        name="compere" type="hidden" value="${compere!""}"/>
                <#-- <label class="max">记录人：</label><input type="text"
                                                                        class="text required"
                                                                        value="${helper.dataDictionary.getUserNamesByUiids(recorder!"",";")}"
                                                                        id="recorderNames"/ readonly="true"><input
              name="recorder" type="hidden" value="${recorder!""}"/>-->


                    <label class="max">召集人：</label><input type="text"
                                                                                  class="text required" readonly="true"
                                                                                  value="${helper.dataDictionary.getUserNamesByUiids(applicant!"",";")}"
                                                                                  id="applicantNames"/><input
                        name="applicant" type="hidden" value="${applicant!""}"/>

                    <label class="max">纪要人：</label>
                    <input type="text" class="text required" readonly="true" id="sumUserCds"
                           value="${helper.dataDictionary.getUserNamesByUiids(sumUserCd!"",";")}"><input
                        name="sumUserCd" type="hidden"
                        value="${sumUserCd!}"/>
                </li>


                <li>
                    <label>会议室：</label><input name="mesRoom" type="text" class="text required"
                                                                      value="${mesRoom!""}"/>
                    <label class="max">会议日期：</label><input id="fromDate" name="fromDate" type="text"
                                                           class="text required"
                                                           value="<#if fromDate?exists>${fromDate?string("yyyy-MM-dd")}<#else><#--${helper.currentDate?string("yyyy-MM-dd")}--></#if>"
                                                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:pickedFunc})"/><input
                        name="startTime"
                        type="text"
                        class="text min required"
                        style="margin-left: 10px;" id="d4311" requiredMsg="会议开始时间必须选择"
                        value="<#if fromDate?exists>${fromDate?string("HH:mm")}<#else><#--12:35--></#if>"
                        onclick="WdatePicker({dateFmt:'HH:mm',maxDate:'#F{$dp.$D(\'d4312\')}'})"/>
                    <label style="width: 32px;text-align: center">到</label><input name="endTime" type="text"
                                                                                  requiredMsg="会议结束时间必须选择"
                                                                                  class="text min required"
                                                                                  id="d4312"
                                                                                  value="<#if toDate?exists>${toDate?string("HH:mm")}<#else><#--16:51--></#if>"
                                                                                  onclick="WdatePicker({dateFmt:'HH:mm',minDate:'#F{$dp.$D(\'d4311\')}'})"/>
                </li>
                <li style="height: 42px;">
                    <label>参会人员：</label>
                    <textarea class="required" id="participatorsName" style=""
                              readonly="true">${helper.dataDictionary.getUserNamesByUiids(participators!"",";")}</textarea>
                    <input name="participators" type="hidden" value="${participators!""}">
                </li>

                <li>
                    <label>参会人数：</label><input name="partNum" type="text" class="text min"
                                               readonly="true"
                                               value="${partNum!""}" id="partNum"/><span
                        style="float: left;width: 20px; text-align: right; ">人</span>
                </li>

                <li style="height: 42px;">
                    <label>抄送人员：</label>
                    <textarea class="" id="ccUsersName"
                              style="overflow: auto;height:33px">${helper.dataDictionary.getUserNamesByUiids(ccUsers!"",";")}</textarea>
                    <input name="ccUsers" type="hidden" value="${ccUsers!""}">
                </li>


                <li>
                    <label>涉及部门：</label>
                    <input name="relatedDept" type="text" class="text max" value="${relatedDept!""}"/>
                </li>

                <li>
                    <label>会议类型：</label>
                    <span style="float: left;width: 24px; ">纪【</span>
                    <input name="mesYear" type="text" class="text min" readonly="true" id="mesYear"
                           value="${helper.currentDate?string("yyyy")}"/>
                    <span style="float: left;width: 20px; ">】</span>
                    <select name="mesTypeCd" class="box required" style="width: 150px;" requiredMsg="会议类型必须选择！">
                    <#if helper.categorys?exists>
                        <#list helper.categorys?keys as key>
                            <option value="${key}"
                                    <#if (mesTypeCd!"")==key>selected="selected" </#if>>${helper.categorys.get(key)}</option>
                        </#list>
                    </#if>
                    </select>
                    <input name="serialNo" type="text" class="text min" style="margin-left: 10px;margin-right: 10px;"
                           readonly="true" value="<#if (serialNo!0)!=0>${serialNo}</#if>" id="serialNo"/>号
                </li>
                <li class="fujian" style="height:auto;">
                    <label>附件：</label>

                    <div>
                        <p class="bt">
                            <a href="javascript://" class="add_fj">添加附件</a>&nbsp;&nbsp;|&nbsp;&nbsp;可上传小于20MB的附件 <span
                                style="color:red;">【纪要内容请直接填写进下方输入区，附件纪要无效】</span>
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
                <li class="nodes" style="height:auto;">
                    <label>审批步骤：</label>
                    <button class="blue" type="button">添加审批人</button>
                    <div>
                        <p class="none">
                            <label>步骤1：</label>
                            <input type="text" class="text" readonly="true"/>
                            <input type="hidden"/><img
                                src="${url.ctx}/resources/images/mes/delete.gif"
                                style="margin-left: 10px;"
                                onclick="nodeManager.del(this)"/>
                        </p>
                    <#list mesApproveNodes![] as approve>
                        <p>
                            <label>步骤${approve_index+1}：</label>
                            <input type="text" class="text" readonly="true"
                                   value="${helper.dataDictionary.getUserNamesByUiids(approve.userCd!"",";")}"/>
                            <input type="hidden" name="approves" value="${approve.userCd!""}"/><img
                                src="${url.ctx}/resources/images/mes/delete.gif"
                                style="margin-left: 10px;"
                                onclick="nodeManager.del(this)"/>
                        </p>
                    </#list>
                    </div>
                </li>
            </form>

        </ul>
    </div>
    
    
    
  
</div>


<script type="text/javascript">
$(function () {
    window.isSave = true;
    window.setSerial = function () {
        var fromDate = pd.helper.trim($(".publish input[name=fromDate]").val());
        var mesTypeCd = pd.helper.trim($(".publish select[name=mesTypeCd] option:selected").val());
        if (fromDate != "" && mesTypeCd != "") {
            $.getJSON("mes-meeting-info!ajax.action", {action:1002, cid:$.trim($("input[name=id]").val()), mesTypeCd:mesTypeCd, mesYear:fromDate.split("-")[0]}, function ($data) {
                $("#serialNo").val($data.serial);
            });
        }
    }
    //会议日期选择，同时 设置 年份
    window.pickedFunc = function () {
        $dp.$('mesYear').value = $dp.cal.getP('y');
        window.setSerial();
    }
    $("select[name=mesTypeCd]").change(function () {
        window.setSerial();
    });
    //审核步骤 生成
    $(".nodes button:first").click(function () {
        var psize = $("li.nodes p:gt(0)").size();
        var newp = $("li.nodes p:first").clone();
        newp.css("display", "block");
        newp.find("label").html("步骤" + (psize + 1) + "：");
        $(".nodes div").append(newp);
        var input = newp.find("input:first");
        input.attr("id", "approves_" + new Date().getTime());
        input.ouSelect({
            showGroupFlg:true,
            muti:false
        });
        newp.find("input:last").attr("name", "approves");
    });
    //审核步骤添加 删除操作
    var nodeManager = window.nodeManager = {del:function ($element) {
        $($element).parent().remove();
        var ps = $("li.nodes p:gt(0)").each(function ($index) {
            $("label", this).html("步骤" + ($index + 1) + "：");
        });
    }};


    //会议室选择
    window.seetingsInfos = function (info) {
        $("div.publish input[name=subject]").val(info.subject);
        if (info.recorder_name != info.recorder) {
            $("div.publish input#sumUserCds").val(info.recorder_name).next().val(info.recorder);
        } else {
            $("div.publish input#sumUserCds").val('').next().val('');
        }
        if (info.compere_name != info.compere) {
            $("div.publish input#compereNames").val(info.compere_name).next().val(info.compere);
        } else {
            $("div.publish input#compereNames").val('').next().val('');
        }
        if (info.applicant_name != info.applicant) {
            $("div.publish input#applicantNames").val(info.applicant_name).next().val(info.applicant);
        } else {
            $("div.publish input#applicantNames").val('').next().val('');
        }
        var tmpRoom=info.room;
        if (tmpRoom == '总裁' || tmpRoom == '执行总裁'){
       		tmpRoom = tmpRoom+'办公室';
        }
        $("div.publish input[name=mesRoom]").val(tmpRoom);
        $("div.publish #fromDate").val(info.date);
        $("div.publish #d4311").val(info.start);
        $("div.publish #d4312").val(info.end);
        $("div.publish textarea#participatorsName").val(info.participators_name).next().val(info.participators);
        $("div.publish input#partNum").val(info.part_num);
        $("#mesYear").val(info.date.split("-")[0]);
        window.setSerial();
    }
    $(".publish .form_body button:first").click(function () {
        var containerHeight = ($.browser.msie ? $("html") : $(window)).height();
        containerHeight = (containerHeight - $("#roomsHistoryDirectory").height()) / 2;
        $("#roomsHistoryDirectory").jqm().jqmShow().css("top", containerHeight).jqDrag(".title");

        /*ymPrompt.win({
            message:"mes-meeting-info!historyDirectory.action",
            titleBar:false,
            fixPosition:true,
            winPos:'c',
            width:752,
            height:488,
            iframe:true,
            msgCls:"ym-content"
        });*/
    });

    //头部按钮事件注册
    var btns = $(".publish .btns button").click(function () {
        TB_removeMaskLayer();
        var text = $(this).text();
        if (text == "开始") {
            //标识保存完成后 需要 直接 发送/开始 流程
            $("input[name=start]").val(1);
            btns.filter(":contains(保存)").trigger("click");
        } else if (text == "保存") {
            var errorMessage = powerdesk.helper.validation(".required");
            if (errorMessage && errorMessage != "") {
                window.isSave = false;
                powerdesk.ui.prompt.error({message:errorMessage});
            } else {
                window.isSave = true;
                TB_showMaskLayer("正在发送数据...", 5000);
                $("form#input").trigger("submit");
            }
        } else if (text == "返回") {
            gotoPager({index:window.pagerindex});
        } else if (text == "删除") {
            ymPrompt.confirmInfo({
                autoClose:true, msgCls:'', message:"<p style='height:70px;line-height: 70px;'>该操作不可恢复，确定删除？</p>", width:350, height:150,
                title:"确认对话框！", closeBtn:true, handler:function (btn) {
                    if (btn == 'ok') {
                        $.getJSON("mes-meeting-info!ajax.action", {action:1007, id:"${mesMeetingInfoId!}"}, function ($data) {
                            if ($data.success) {
                                gotoPager({index:window.pagerindex});
                            } else {
                                alert($data.message);
                            }
                            ymPrompt.close();
                        });
                    }
                }
            });
        }
    });

    //参会人员
    $("#participatorsName").ouSelect({
        showGroupFlg:true,
        callback:function (users) {
            var i = 0;
            for (var user in users) {
                i++
            }
            $("#partNum").val(i);
        }
    });
    $("#ccUsersName").ouSelect({showGroupFlg:true});

    //人员选择框初始化
    $("#recorderNames,#applicantNames,#compereNames,#sumUserCds").ouSelect({
        showGroupFlg:true,
        muti:false
    });

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

    //表单提交结束 回调函数
    var execute = window.execute = function ($data) {
        setTimeout(TB_removeMaskLayer, 500);
        if ($data.success) {
            $("#body").load("mes-meeting-info!input.action", {id:$data.id}, function () {
                var timer = new Date();
                if ($data.start && $data.start == "1") {
                    var input = $(".publish .form_body input[name=id]");
                    $.getJSON("mes-meeting-info!ajax.action", {action:1003, id:pd.helper.trim(input.val())}, function ($data) {
                        if (!$data.success) {
                            powerdesk.ui.prompt.error({message:$data.message});
                            $(".nodes button:first").trigger("focus");
                        } else {
                            powerdesk.ui.prompt.success({message:$data.message});
                            $("#header input[name=recodeType]").val("0");
                            $("#header input[name=status]").val("0");
                            gotoPager({index:window.pagerindex});
                        }
                    });
                } else {
                    powerdesk.ui.prompt.success({message:"操作成功，已于" + timer.getHours() + ":" + timer.getMinutes() + ":" + timer.getSeconds() + " 秒保存到 “<a href='mes-meeting-info!index.action'>我的纪要</a>” 。"});
                }

            });
        } else {
            powerdesk.ui.prompt.error({message:$data.message});
        }
    }

    function resize() {
        var container = $.browser.msie ? $("html") : $(window);
        var panelTop = $(".condition_panel").is(":hidden") ? 0 : $(".condition_panel").height() + 20;
        var informationsTop = $(".informations").is(":hidden") ? 0 : $(".informations").height();
        var height = parseInt(container.height()) - (97 + panelTop + informationsTop);
        $("div.publish .body_warp").height(height);
    }

    //高度设置
    //setInterval(resize, 1000);
    //resize();
    autoHeight();
});
</script>
</div>

