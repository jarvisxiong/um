<#include "/WEB-INF/content/com/template/autoimport.ftl"/>
<#assign security=JspTaglibs["/WEB-INF/security.tld"]>

<@security.authorize ifAnyGranted="A_MES_ADMIN">
    <#assign isAdmin=true/>
</@security.authorize>
<@security.authorize ifAnyGranted="A_MES_READ_ALL">
    <#assign isReadAll=true/>
</@security.authorize>

<!--纪要填写 Form表单-->
<div class="publish_write">

<div class="informations none"></div> <#--        ${mesMeetingInfo.statusCd+"_"+currentUiid +"_"+mesMeetingInfo.userCd}-->


<#if mesMeetingInfo.statusCd=="3"||(mesMeetingInfo.statusCd=="2"&&((mesMeetingInfo.userCd!"")!=currentUiid)|| (mesMeetingInfo.statusCd=="1"&&mesMeetingInfo.sumUserCd!=currentUiid))>
<#--只读：完成状态，或 审批与 纪要人 都不是 当前登录用户时进入-->
<div class="btns clearfix readonly">
<#--if mesMeetingInfo.statusCd=="3">
    <span class="rstResolutionsNew">
        <button class="orange" type="button">相关决议</button>
        <div class="rstResolutionss">
            <div class="content">
                <h2>相关决议</h2>
                <#if !mesMeetingInfo.mesRstResolutions?size==0>
                    <div class="box">
                        <ul>
                            <#list mesMeetingInfo.mesRstResolutions as rstResolutions>
                                <#if (rstResolutions.statusCd=="1")||rstResolutions.creator=currentUiid>
                                    <li><a href="javascript:;"
                                           rel="${rstResolutions.mesRstResolutionId!}"
                                           style="<#if (rstResolutions.statusCd!)=="0">color: red</#if>">${rstResolutions.rstSubject!}</a>
                                    </li>
                                </#if>
                            </#list>
                        </ul>
                    </div>
                </#if>
                <div class="bar clearfix">
                    <@security.authorize ifAnyGranted="A_MES_PUBLISH">
                        <button class="green " style="float:left;margin-right:5px;">新增决议</button>
                    </@security.authorize>
                    <button class="red min" style="float:left; margin-left: 5px;">取消</button>
                </div>
            </div>
        </div>
    </span>
</#if-->
    <#if mesMeetingInfo.statusCd=="3"&&mesMeetingInfo.creator=currentUiid>

        <span class="pushMeetingNew">
            <button class="green min" type="button">推送</button>
            <input type="hidden" id="pushMeetingNewNames"/>
            <input type="hidden" id="pushMeetingNew"/>
        <#--div class="pushMeetings">
            <div class="content">
                <h2>接收人员</h2>
                <div class="box">
                    <textarea class="text" id="pushMeetingNew" style="width:194px;height:102px;margin-top: 5px;"/>
                </div>
                <div class="bar clearfix" style="margin-top:8px;">
                     <button class="green " style="float:left;margin-right:5px;">确定推送</button>
                    <button class="red min" style="float:left; margin-left: 5px;">取消</button>
                </div>
            </div>
        </div-->
        </span>
    </#if>
<#--在没有完成的状态下，都是可以追回的：发起人追回(从纪要填写->到新增状态)，纪要填写人追回->(从审批中-》纪要填写状态),审批人(A)追回(从审批人B->返回至审批人A,审批状态)-->
    <#if mesMeetingInfo.statusCd!="3"&&mesMeetingInfoManager.isRollbackMesMeetingPermission(mesMeetingInfo.mesMeetingInfoId!"")!=0>
        <button class="red min" type="button">追回</button>
    </#if>
    <#if mesMeetingInfo.creator==currentUiid||(isReadAll!false)||(isAdmin!false)>
        <button class="green min" type="button">导出</button>
    </#if>
    <#if mesMeetingInfo.statusCd=="3"&&mesMeetingInfo.creator==currentUiid>
        <button class="green" type="button">发送邮件</button>
    </#if>
    <#if mesMeetingInfo.mesApproveHises?size!=0>
        <button class="orange" type="button">审批历史</button>
    </#if>
    <#if mesMeetingInfo.statusCd=="3"&&(mesMeetingInfo.creator==currentUiid||isAdmin)>
        <button class="orange" type="button"><#if mesMeetingInfo.isRst>编辑决议<#else>上传决议</#if></button>
    </#if>

    <button class="green min" type="button">返回</button>
    <p>
        状态：<span class="blue">${helper.getStatus(mesMeetingInfo.statusCd!)}</span>
        <#if mesMeetingInfo.statusCd=="3">
            <span style="color:${((mesMeetingInfo.emailSendCnt!0)!=0)?string("#000","red")}">邮件${((mesMeetingInfo.emailSendCnt!0)!=0)?string("已","未")}<#t/>
                <#t/>发送</span>
        </#if>
    </p>
</div>
<#elseif mesMeetingInfo.statusCd=="1"||mesMeetingInfo.statusCd=="4">
<#--纪要填写-->
<div class="btns clearfix write">
    <#if (mesMeetingInfo.statusCd?matches("1|4"))&&mesMeetingInfo.sumUserCd==currentUiid>
        <button class="blue min">提交</button>
        <button class="green min">保存</button>
    </#if>
    <#if mesMeetingInfo.statusCd!="3"&&mesMeetingInfoManager.isRollbackMesMeetingPermission(mesMeetingInfo.mesMeetingInfoId!"")!=0>
        <button class="red min" type="button">追回</button>
    </#if>
    <#if mesMeetingInfo.creator==currentUiid||(isReadAll!false)||(isAdmin!false)>
        <button class="green min">导出</button>
    </#if>
    <#if mesMeetingInfo.mesApproveHises?size!=0>
        <button class="orange">审批历史</button>
    </#if>
    <button class="green min">返回</button>
    <p>
        状态：<span class="blue">${helper.getStatus(mesMeetingInfo.statusCd!)}</span>
    <#--<#if mesMeetingInfo.statusCd!="3"&&mesMeetingInfo.startDate?exists><span
        class="red">${helper.expirationDate(mesMeetingInfo.startDate)}
    小时后到期</span></#if>-->
    </p>
</div>
<#elseif mesMeetingInfo.statusCd=="2"&&(mesMeetingInfo.userCd!"")==currentUiid>
<#--审核-->
<div class="btns clearfix shenhe">
<#--<button class="blue min">编辑</button>-->
<#--button class="red ">申请延期</button-->
    <#if mesMeetingInfo.statusCd!="3"&&mesMeetingInfoManager.isRollbackMesMeetingPermission(mesMeetingInfo.mesMeetingInfoId!"")!=0>
        <button class="red min" type="button">追回</button>
    </#if>
    <#if mesMeetingInfo.creator==currentUiid||(isReadAll!false)||(isAdmin!false)>
        <button class="green min">导出</button>
    </#if>
    <#if mesMeetingInfo.mesApproveHises?size!=0>
        <button class="orange ">审批历史</button>
    </#if>
    <button class="green min">返回</button>
    <p>
        状态：<span class="blue">${helper.getStatus(mesMeetingInfo.statusCd!)}</span>
    </p>
</div>
</#if>
<div class="body_warp" style="margin-top: 10px;overflow-y:auto;">
<div class="title_2 ">
    <h2>${mesMeetingInfo.subject!}</h2>

    <p>
        <span><strong>纪</strong>【#{mesMeetingInfo.mesYear}】</span>
        <span>${helper.categorys.get(mesMeetingInfo.mesTypeCd!"")}</span>
        <span>${mesMeetingInfo.serialNo!}<strong>号</strong></span>
    </p>
</div>

<div class="form_body" style="padding-bottom: 5px; ">
    <ul class="clearfix">
        <li>
            <label>纪要标题：</label><input type="text" class="text max disabled" value="${mesMeetingInfo.mesTitle!""}"/>
        </li>

        <li>
            <label>主持人：</label><input type="text" class="text disabled"
                                                              value="${helper.dataDictionary.getUserNamesByUiids(mesMeetingInfo.compere!"",";")}"
                                                              readonly="readonly"/>
            <label class="max">召集人：</label><input type="text"
                                                                          class="text disabled"
                                                                          value="${helper.dataDictionary.getUserNamesByUiids(mesMeetingInfo.applicant!"",";")}"
                                                                          readonly="readonly"/>
            <label class="max">纪要人：</label>
            <input type="text" class="text disabled"
                   value="${helper.dataDictionary.getUserNamesByUiids(mesMeetingInfo.sumUserCd!"",";")}"
                   readonly="readonly"/>
        </li>

        <li>
            <label>会议室：</label><input type="text" class="text disabled"
                                                              value="${mesMeetingInfo.mesRoom!}"
                                                              readonly="readonly"/>
            <label class="max">会议日期：</label><input type="text" class="text disabled"
                                                   value="${mesMeetingInfo.fromDate?string("yyyy-MM-dd")}"
                                                   readonly="readonly"/><input type="text"
                                                                               class="text min disabled"
                                                                               value="${mesMeetingInfo.fromDate?string("HH:mm")}"
                                                                               readonly="readonly"
                                                                               style="margin-left: 10px;"/>
            <label style="width: 32px;text-align: center">到</label><input type="text"
                                                                          class="text min disabled"
                                                                          value="${mesMeetingInfo.toDate?string("HH:mm")}"
                                                                          readonly="readonly"/>
        </li>
        <li style="height: 42px;">
            <label>参会人员：</label>
            <textarea class="disabled" value="test" style="height:33px;overflow: auto;"
                      readonly="readonly">${helper.dataDictionary.getUserNamesByUiids(mesMeetingInfo.participators!"",";")}</textarea>
        </li>
        <li>
            <label>参会人数：</label><input type="text" class="text min disabled"
                                       value="${mesMeetingInfo.partNum!}" readonly="readonly"/><span
                style="float: left;width: 20px; text-align: right; ">人</span>
        </li>

        <li style="height: 38px;">
            <label>抄送人：</label>
            <textarea class="disabled" style="height:33px;overflow: auto;"
                      readonly="true">${helper.dataDictionary.getUserNamesByUiids(mesMeetingInfo.ccUsers!"",";")}</textarea>
        </li>

        <li class="spbz">
            <label>审批步骤：</label>

            <p>
            <#list mesMeetingInfo.mesApproveNodes as node>
                <span class="<#if node.approveLevel==mesMeetingInfo.approveLevel>s<#else>r</#if>">${node_index+1}
                    .${helper.dataDictionary.getUserNameByCd(node.userCd!)}</span><#if node_has_next>
                <span>-></span></#if></#list>
            </p>
        </li>
    </ul>
</div>


<div class="form_body rst <#if !mesMeetingInfo.isRst>none</#if>" style="padding-bottom: 5px;">
    <form method="post" action="mes-meeting-info!uploadRst.action" id="rst" method="post" target="iframe"
          enctype="multipart/form-data">
        <input type="hidden" name="mesMeetingInfoId" value="${mesMeetingInfo.mesMeetingInfoId!}"/>
        <input type="hidden" name="start" value="0"/>
        <ul class="clearfix">
            <li>
                <label>决议标题：</label><input type="text"
                                           class="text max <#if !mesMeetingInfo.isRst>required<#else>disabled</#if>"
                                           name="rstSubject" value="${mesMeetingInfo.rstSubject!}"/>
            </li>
            <li>
                <label>决议编号：</label>
                <span style="float: left;width: 48px; ">决策委【</span>
                <input name="rstYear" type="text"
                       class="text min  <#if !mesMeetingInfo.isRst>required<#else>disabled</#if> number"
                       id="mesYear" maxlength="4"
                       style="width:32px" value="#{mesMeetingInfo.rstYear!helper.currentDate?string("yyyy")?number}"
                       numberMsg="决议年份必须为数字！" requiredMsg="请填写决议年份!"
                        />
                <span style="float: left;width: 14px;">】</span>
                <input name="rstNo" type="text"
                       class="text min  <#if !mesMeetingInfo.isRst>required<#else>disabled</#if> number"
                       style="margin-right: 5px;" numberMsg="决议编号必须为数字！" requiredMsg="请填写决议编号!"
                       value="<#if (mesMeetingInfo.rstNo!0)!=0>#{mesMeetingInfo.rstNo}</#if>" id="serialNo"
                       maxlength="5"/>
                号
            </li>

            <li class="fujian" style="height:auto;">
                <label>决议附件：</label>

                <div>

                    <p class="bt<#if mesMeetingInfo.isRst||(mesMeetingInfo.creator!=currentUiid&&isAdmin)> none</#if>">
                        <a href="javascript://" class="add_fj">添加附件</a>&nbsp;&nbsp;|&nbsp;&nbsp;可上传小于20MB的附件
                        <input type="file" name="file" class="fileuploader" hidefocus="true"
                               style="margin-left: -238px;"
                               onchange="fujianManager.change(this)"/>
                    </p>

                    <p class="none">
                        <span>第1个附件</span><img src="${url.ctx}/resources/images/mes/fujian.gif"
                                               style="margin-left: 10px;"><a
                            href="javascript:void(0)" style="margin-left: 10px;"
                            class="add_fj"></a><#if mesMeetingInfo.creator==currentUiid><a style="margin-left:10px;"
                                                                                             href="javascript://"
                                                                                             class="delete"
                                                                                             onclick="fujianManager.del(this)">删除</a></#if>
                    </p>
                <#if rstAttachment?exists>
                    <#list [rstAttachment!] as file>
                        <p>
                            <span>第${file_index+1}个附件</span><img src="${url.ctx}/resources/images/mes/fujian.gif"
                                                                 style="margin-left: 10px;"><a
                                href="mes-meeting-info!download.action?filename=${file.fileName}&filerealname=${file.realFileName?url("utf-8")};"
                                target="_blank"
                                style="margin-left: 10px;"
                                class="add_fj">${file.realFileName!""}</a>&nbsp;&nbsp;(${helper.dataDictionary.getUserNameByCd(file.creator!)}
                            )<#if mesMeetingInfo.creator==currentUiid>
                            <a style="margin-left:10px;"
                               href="javascript://"
                               class="delete"
                               onclick="fujianManager.del(this)"
                               rel="${file.appAttachFileId!}">删除</a></#if>
                        </p>
                    </#list>
                <#elseif  mesMeetingInfo.isRst||(mesMeetingInfo.creator!=currentUiid&&isAdmin)>
                    <p>无附件</p>
                </#if>
                </div>
            </li>

        </ul>
    </form>
</div>


<iframe id="iframe" name="iframe" class="none"></iframe>

<form method="post" action="mes-summary!save.action" id="input" method="post" target="iframe"
      enctype="multipart/form-data">
    <input type="hidden" name="id" value="${mesSummaryId!}"/>
    <input type="hidden" name="entity.recordVersion" value="${recordVersion!}"/>
    <input type="hidden" name="mesMeetingInfoId"
           value="${mesMeetingInfo.mesMeetingInfoId!}"/>
    <input type="hidden" name="start" value="0"/>

    <div class="form_body"
         style="padding-bottom: 5px;margin: 0px;border: none;border-left:2px solid #fff; padding-top: 10px; ">
        <ul class="clearfix">
        <#if mesMeetingInfo.statusCd!="1"&&mesMeetingInfo.mesSummaries?size!=0>
            <li class="summaryVersion">
                <label>纪要版本：</label>
                <#list mesMeetingInfo.mesSummaries?sort_by("createdDate")?reverse as summary>
                    <a class="btn<#if summary.mesSummaryId==entity.mesSummaryId> selected</#if>"
                       rel="${summary.mesSummaryId}"
                       style="width: auto;padding:0 10px;"
                       href="javascript:void(0)">第${(mesMeetingInfo.mesSummaries?size)-summary_index!}
                        版(${helper.dataDictionary.getUserNamesByUiids(summary.creator!"",";")?split(";")[0]})</a>
                </#list>
            </li>
        </#if>
            <li class="fujian" style="height:auto;">
                <label>附件：</label>

                <div>
                <#if (isAdmin!false||mesMeetingInfo.statusCd!="3")&&(mesMeetingInfo.userCd==""||mesMeetingInfo.userCd==currentUiid||(mesMeetingInfo.sumUserCd==currentUiid&&mesMeetingInfo.statusCd=="1")||isAdmin!false)>
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
                            class="add_fj">${file.realFileName!""}</a>&nbsp;&nbsp;(${helper.dataDictionary.getUserNameByCd(file.creator!)}
                        )<#if (mesMeetingInfo.statusCd!="3"&&(file.creator==currentUiid&&(mesMeetingInfo.userCd==currentUiid||mesMeetingInfo.sumUserCd==currentUiid)))||isAdmin!false>
                        <a style="margin-left:10px;"
                           href="javascript://"
                           class="delete"
                           onclick="fujianManager.del(this)"
                           rel="${file.appAttachFileId!}">删除</a></#if>
                    </p>
                </#list>
                </div>
            <#if isAdmin!false>
                <div style="padding-left:58px;display: none;">
                    <button class="green min uploader" style="height:20px;line-height: 15px;width:59px;"
                            type="button">上传
                    </button>
                </div>
            </#if>
            </li>

        </ul>
    </div>

    <div style="padding:0 0 20px;">
    <#if mesMeetingInfo.statusCd=="1"||mesMeetingInfo.statusCd=="4">
        <div id="editor" class="write">
            <textarea id="xheditor" style="height: 300px;">${content!}</textarea>
        </div>
    <#else>

        <#if ((mesMeetingInfo.userCd!"")==currentUiid&&mesMeetingInfo.statusCd=="2")||(mesMeetingInfo.statusCd=="3"&&mesMeetingInfo.creator==currentUiid)>
            <p style="padding-bottom: 10px;">
                <button class="blue min" type="button">编辑</button>
            </p>
        </#if>
        <div id="content_temp" style="border: 1px solid #C5C5C5; padding: 10px; background:#eaeaea">
        ${content!}
        </div>
        <div id="editor" class="none">
            <textarea id="xheditor" style="height: 300px;">${content!}</textarea>
        </div>
    </#if>

        <textarea name="content" style=" display:none;">${content!}</textarea>
    </div>

    <div class="publish_auditing_list clearfix"
         style="margin-bottom: 0px; padding-bottom: 0px;<#if mesMeetingInfo.mesMeetingPushs?size==0>display:none;</#if>">
        <h2>已推送给</h2>

        <div style="padding:10px;padding-left:15px;color:#0167a2;" id="mesMeetingPushs">
        <#list mesMeetingInfo.mesMeetingPushs as mesMeetingPushs>
        ${helper.dataDictionary.getUserNameByCd(mesMeetingPushs.userCd)}<#if mesMeetingPushs_has_next>;</#if>
        </#list>
        </div>
    </div>

    <div class="publish_auditing_list clearfix">
        <h2>审批记录</h2>
        <ul class="clearfix">
        <#list mesMeetingInfo.mesApproveNodes as node>
            <li class="clearfix">
                <span class="box<#if mesMeetingInfo.approveLevel==node.approveLevel> orange</#if>">${node_index+1}</span>
                <span class="author">${helper.dataDictionary.getUserNameByCd(node.userCd!)}</span>
                    <span class="status">(<#if node.approveDate?exists>
                        <font>${node.approveDate?string("yy-MM-dd HH:mm")}</font></#if> <#if (node.approveOptionCd!"")=="3">
                        已审批<#elseif  (node.approveOptionCd!"")=="4">已驳回<#else>未审批</#if>)</span>
                <span class="role">${node.workDutyDesc!}</span>

                <div class="clearfix">
                    <#if node.approveOptionCd!=""&&node.approveRemark!="">
                        <p class="clearfix">${node.approveRemark}</p>
                    </#if>
                </div>
            </li>
        </#list>
        </ul>
    <#if mesMeetingInfo.statusCd=="2"&&mesMeetingInfo.userCd==currentUiid>
        <div class="spyj">
            <h3>审核意见<span style="color: red;">(请在这里填写有效的审核/审批意见)</span>：</h3>
            <textarea></textarea>
            <button class="blue" type="button">完成</button>
            <button class="red" type="button">驳回</button>
        </div>
    </#if>
    </div>

</form>

</div>

<form action="mes-meeting-info!export.action" class="none" id="export" target="iframe">
    <input type="hidden" name="mesMeetingInfoId" value="${mesMeetingInfo.mesMeetingInfoId!}"/>
</form>

</div>
<script type="text/javascript">

$(function () {
    var xheditorFunction = function () {
        return $("#xheditor").xheditor({width:"100%",
            tools:'full', forcePtag:false, showBlocktag:false, html5Upload:false, upMultiple:'1',
            upLinkUrl:"${url.ctx}/mes/mes-meeting-info!fileuploader.action", upLinkExt:"zip,rar,txt",
            upImgUrl:"${url.ctx}/mes/mes-meeting-info!fileuploader.action", upImgExt:"jpg,jpeg,gif,png",
            upFlashUrl:"${url.ctx}/mes/mes-meeting-info!fileuploader.action", upFlashExt:"swf",
            upMediaUrl:"${url.ctx}/mes/mes-meeting-info!fileuploader.action", upMediaExt:"avi"
        });
    }
    var xheditor = xheditorFunction();
    $("form#input").submit(function () {
        $("textarea[name=content]").val(xheditor.getSource());
        $('#xheditor').parent().hide();
        $('#xheditor').xheditor(false);
    });
    /**
     * 只读表单事件注册
     */
    $(".publish_write .readonly button").click(function () {
        var text = $(this).text();
        if (text == "返回") {
            gotoPager({index:window.pagerindex});
        } else if (text == "发送邮件") {
            ymPrompt.confirmInfo({
                autoClose:false,
                message:"<p >请选择纪要接收对象！</p>", width:300, height:150,
                title:"确认对话框！",
                btn:[
                    ['自己', 'me'],
                    ['参会人', 'all'],
                    ['取消', 'close']
                ], closeBtn:true, handler:function (btn) {
                    if (btn == 'close') {
                        ymPrompt.close();
                    } else {
                        var params = {action:1008, id:"${mesMeetingInfo.mesMeetingInfoId!}"};
                        if (btn == "me") {
                            params.sendme = true;
                        }
                        $.getJSON("mes-meeting-info!ajax.action", params, function ($input) {
                            alert($input.message);
                            ymPrompt.close();
                        });
                    }
                }
            });
        } else if (text == "新增决议") {
            $("#body").load("mes-rst-resolution!publish.action", {mesMeetingInfoId:"${mesMeetingInfo.mesMeetingInfoId!""}"});
        } else if (text == "取消") {
            $(".rstResolutionss").hide();
        } else if (/编辑决议|上传决议|保存决议/.test(text)) {
            var rstPanel = $(".rst");
            if ("上传决议" == text) {
                rstPanel.show().find("input[name=rstSubject]").trigger("focus");
                $(".body_warp").animate({scrollTop:379}, 800);
                $(this).text("保存决议");
            } else if ("编辑决议" == text) {
                rstPanel.show().find("input[name=rstSubject]").trigger("focus");
                $(".body_warp").animate({scrollTop:379}, 800);
                $(this).text("保存决议");
                rstPanel.find("input").removeClass("disabled").not(":hidden,:file").addClass("required");
                rstPanel.find("p.bt").show();
                rstPanel.find("p:contains(无附件)").remove()
            } else if ("保存决议" == text) {
                TB_removeMaskLayer();
                var errorMessage = powerdesk.helper.validation(".rst .required");
                if (errorMessage && errorMessage != "") {
                    powerdesk.ui.prompt.error({message:errorMessage});
                } else {
                    TB_showMaskLayer("正在发送数据...", 5000);
                    $("form#rst").trigger("submit");
                }
            }
        }
    });
    $(".rstResolutionsNew").hover(
            function () {
                $(".rstResolutionss").show();
            },
            function () {
                $(".rstResolutionss").hide();
            }).find("button:contains(相关决议)").click(function () {
                $(".rstResolutionss").hide().slideDown();
            });
    $(".rstResolutionss ul li a").click(function () {
        $("#body").load("mes-rst-resolution!publish.action", {id:$(this).attr("rel")});
    });

    $("#pushMeetingNewNames").ouSelect({
        showGroupFlg:true, callback:function (users) {
            var i = 0;
            for (var user in users) {
                i++
            }
            if (i == 0) {
                powerdesk.ui.prompt.error({message:"推送失败，必须选择接收人员!", time:2000});
            } else {
                $.post("mes-meeting-info!push.action", {pushUserCds:$("#pushMeetingNew").val(), id:"${mesMeetingInfo.mesMeetingInfoId!}"}, function ($data) {
                    powerdesk.ui.prompt.success({message:"推送成功!", time:2000});
                    $("#mesMeetingPushs").append(";" + $("#pushMeetingNewNames").val()).parent().show();
                    ;
                });
            }
        }
    });
    $(".pushMeetingNew").find("button:contains(推送)").click(function () {
        $("#pushMeetingNewNames").trigger("click");
    });


    //纪要填写
    $(".publish_write .write button").click(function () {
        TB_removeMaskLayer();
        var text = $(this).text();
        if (text == "提交") {
            $("input[name=start]").val("1");
            TB_showMaskLayer("正在发送数据...", 5000);
            $("form#input").trigger("submit");
        } else if (text == "返回") {
            gotoPager({index:window.pagerindex});
        } else if (text == "保存") {
            $("input[name=start]").val("0");
            $("form#input").trigger("submit");
        } else if (text == "审批历史") {
        }
    });

    //纪要审核
    $(".publish_write .shenhe button,.publish_write button:contains(编辑)").click(function () {
        TB_removeMaskLayer();
        var loader = $(this);
        var text = $(this).text();
        if (text == "保存") {
            $('#xheditor').parent().hide();
            $('#xheditor').xheditor(false);
            $("input[name=start]").val("0");
            $("form#input").trigger("submit");
        } else if (text == "编辑") {
            loader.text("保存");
            $("#content_temp").hide();
            $("#xheditor").val($("#content_temp").html());
            //编辑器
            $('#xheditor').xheditor(false);
            xheditor = xheditorFunction();
            $("#editor").show();
            //$("div.publish_write .body_warp").animate({scrollTop:493}, 0);
            xheditor.focus();
        } else if (text == "返回") {
            gotoPager({index:window.pagerindex});
        } else if (text == "申请延期") {
        } else if (text == "审批历史") {
        }
    });

    $(".publish_write  button:contains(审批历史)").click(function () {
        ymPrompt.win({
            message:"mes-approve-his!list.action?mesMeetingInfoId=${mesMeetingInfo.mesMeetingInfoId!""}",
            titleBar:false,
            btn:[
                ['关闭', 'close']
            ],
            fixPosition:true,
            winPos:'c',
            width:800,
            height:500,
            iframe:true
        });
    });

    $("button:contains(追回)").click(function () {
        /*TB_showMaskLayer("正在发送数据...", 5000);*/
        ymPrompt.confirmInfo({
            autoClose:true, msgCls:'', message:"<p style='height:70px;line-height: 70px;'>确定要追回该条记录，重新处理吗？</p>", width:350, height:150,
            title:"确认对话框！", closeBtn:true, handler:function (btn) {
                if (btn == 'ok') {
                    $.getJSON("mes-meeting-info!ajax.action", {action:1009, id:"${mesMeetingInfo.mesMeetingInfoId!}"}, function ($data) {
                        if ($data.success) {
                            $("form#query input[name=recodeType]").val("0");
                            gotoPager({});
                        } else {
                            alert("发生异常....")
                        }
                    });
                }
            }
        });
    });

    $("button:contains(导出)").click(function () {
        $("form#export").trigger("submit");
        TB_showMaskLayer("正在导出请稍后...", 5000);
    });

    $(".fujian .uploader").click(function () {
        TB_showMaskLayer("正在发送数据...", 5000);
        $("form#input").attr("action", "mes-meeting-info!saveFileuploader.action");
        $("input[name=start]").val("0");
        $("form#input").trigger("submit");
    });

    //纪要版本加载事件
    $("li.summaryVersion a").click(function () {
        var loader = this;
        if ($(this).hasClass("selected"))return;

        $.getJSON("mes-meeting-info!ajax.action", {action:1005, sid:$(this).attr("rel")}, function ($input) {
            if ($input.success) {
                $("li.summaryVersion a").removeClass("selected");
                $(loader).addClass("selected");
                $(".publish_write .shenhe button:contains(保存)").text("编辑");
            <#if mesMeetingInfo.statusCd=="4">
                xheditor.setSource($input.message);
            <#else>
                $("#editor").hide();
                $("#content_temp").html($input.message).show();
            </#if>
            }
        })
    });

    //附件上传
    var fujianManager = window.fujianManager = {
        change:function ($element) {
            var fujian = $($element).parent().parent().parent();
            var newp = fujian.find("p:eq(1)").clone().show();
            newp.find("span").html("第" + (fujian.find("p:gt(1)").size() + 1) + "个附件");
            newp.find("a:first").text($($element).val());
            newp.append($($element).attr("name", "fileuploader"));
            fujian.find("div:eq(0)").append(newp);
            newp.show();
            fujian.find("p:first").append(' <input type="file" name="file" class="fileuploader" hidefocus="true" onchange="fujianManager.change(this)"/>')
            fujian.find("div:eq(1)").show();
        }, del:function ($element) {
            var element = $($element);
            var fujian = element.parent().parent().parent();
            ymPrompt.confirmInfo({
                autoClose:false, msgCls:'', message:"<p style='height:70px;line-height: 70px;'>该操作不可恢复，确定删除？</p>", width:350, height:150,
                title:"确认对话框！", closeBtn:true, handler:function (btn) {
                    if (btn == 'ok') {
                        if (pd.helper.trim(element.attr("rel")) != "") {
                            $.getJSON("mes-meeting-info!ajax.action", {action:1001, id:element.attr("rel")}, function ($data) {
                                if ($data.success) {
                                    element.parent().remove();
                                    fujian.find("p:gt(1)").each(function ($index) {
                                        $("span", this).html("第" + ($index + 1) + "个附件");
                                    });
                                } else {
                                    powerdesk.ui.prompt.error({message:"Sorry 您只能删除自己上传的附件!", time:3000});
                                }
                                ymPrompt.close();
                            });
                            //远程删除
                        } else {
                            element.parent().remove();
                            fujian.find("p:gt(1)").each(function ($index) {
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
        $("#header input[name=recodeType]").val("0");
        $("#header input[name=status]").val("0");
        setTimeout(TB_removeMaskLayer, 500);
        if ($data.success) {
            $("#body").load("mes-summary!input.action", {mesMeetingInfoId:$data.id}, function () {
                var timer = new Date();
                if ($data.start && $data.start == "1") {
                    //纪要填写完成，开始进入 纪要审核步骤
                    $.getJSON("mes-meeting-info!ajax.action", {action:1004, "id":"${mesMeetingInfo.mesMeetingInfoId!""}"}, function ($input) {
                        if ($input.success) {
                            gotoPager({index:window.pagerindex});
                        } else {
                            powerdesk.ui.prompt.error({message:$input.message});
                        }
                    });
                } else if ($data.start && $data.start == "2") {
                    //审核步骤 进入下一环节
                    window.workProcessing(window.spyj_status, window.spyj_approveRemark);
                } else {
                    powerdesk.ui.prompt.success({message:"操作成功，已于" + timer.getHours() + ":" + timer.getMinutes() + ":" + timer.getSeconds() + " 秒保存 。"});
                }
            });
        } else {
            powerdesk.ui.prompt.error({message:$data.message});
            xheditor = xheditorFunction();
            $('#xheditor').parent().show();
        }
    }


    //流程处理
    window.workProcessing = function (status, approveRemark) {
        $.getJSON("mes-meeting-info!ajax.action", {action:1006, id:"${mesMeetingInfo.mesMeetingInfoId!}", status:status, approveRemark:approveRemark}, function ($input) {
            if ($input.success) {
                gotoPager({index:window.pagerindex});
            } else {
                powerdesk.ui.prompt.error({message:$input.message});
            }
        })
    }

    /**
     * 审批区域事件注册
     */
    $("div.spyj").each(function () {
        var input = $("textarea", this);
        var btns = $("button", this).click(function () {
            $("input[name=start]").val("2");
            var index = btns.index(this) + 3;
            var approveRemark = pd.helper.trim(input.val());
            window.spyj_status = index;
            window.spyj_approveRemark = encodeURI(approveRemark);
            if ("" != approveRemark && index == 4) {
                ymPrompt.confirmInfo({msgCls:'', message:"<p style='height:70px;line-height: 70px;'>确定驳回？</p>", width:350, height:150,
                    title:"确认对话框！", closeBtn:true, okTxt:"同意", cancelTxt:"取消", handler:function (btn) {
                        if (btn == 'ok') {
                            $("form#input").trigger("submit");
                        }
                    }
                });
            } else if ("" != approveRemark) {
                $("form#input").trigger("submit");
            } else {
                if (index == 4) {
                    ymPrompt.errorInfo({
                        message:"必须输入驳回原因。", width:350, height:150, title:"消息提示！", closeBtn:true
                    })
                } else {
                    ymPrompt.confirmInfo({msgCls:'', message:"<p style='height:70px;line-height: 70px;'>意见为空，默认填写同意，请问是否继续？</p>", width:350, height:150,
                        title:"确认对话框！", closeBtn:true, okTxt:"同意", cancelTxt:"取消", handler:function (btn) {
                            if (btn == 'ok') {
                                input.val("同意");
                                window.spyj_approveRemark = encodeURI(pd.helper.trim(input.val()));
                                $("form#input").trigger("submit");
                            }
                        }
                    });
                }
            }
        });
    });

    function resize() {
        var container = $.browser.msie ? $("html") : $(window);
        var panelTop = $(".condition_panel").is(":hidden") ? 0 : $(".condition_panel").height() + 20;
        var informationsTop = $(".informations").is(":hidden") ? 0 : $(".informations").height();
        var height = parseInt(container.height()) - (97 + panelTop + informationsTop);
        $("div.publish_write .body_warp").height(height);
    }

    //高度设置
    //setInterval(resize, 1000);
    //resize();
    autoHeight();
});
</script>