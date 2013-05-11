<#include "/WEB-INF/content/com/template/autoimport.ftl"/>
<#assign security=JspTaglibs["/WEB-INF/security.tld"]>
<!DOCTYPE HTML>
<html style="overflow:auto;">
<head>
    <meta http-equiv="content-Type" content="text/html; charset=utf-8"/>
    <title>会议纪要管理</title>
    <link href="${url.ctx}/resources/css/mes/style.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="${url.ctx}/resources/css/mes/thickbox.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="${url.ctx}/js/prompt/skin/custom_1/ymPrompt.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="${url.ctx}/resources/js/loadMask/jquery.loadmask.css" media="screen" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        var _ctx = "${url.ctx}";
    </script>
    <script src="${url.ctx}/js/jquery.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/jquery/jquery.select.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/datePicker/WdatePicker.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/xheditor/xheditor-zh-cn.min.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/common/MaskLayer.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/loadMask/jquery.loadmask.min.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/mes/jquery-ump.js" type="text/javascript"></script>
    <script src="${url.ctx}/js/prompt/ymPrompt.js" type="text/javascript"></script>
    <script src="${url.ctx}/resources/js/desk2/autoHeight.js" type="text/javascript"></script>
    <style type="text/css">
        .ym-ttc {
            height: 0px
        }
    </style>
</head>
<body style="overflow:auto;">
<div id="warp">
<div id="body">
<@security.authorize ifAnyGranted="A_MES_ADMIN">
    <#assign isAdmin=true/>
</@security.authorize>
<@security.authorize ifAnyGranted="A_MES_READ_ALL">
    <#assign isReadAll=true/>
</@security.authorize>

<!--纪要填写 Form表单-->
<div class="publish_write" style=" height: auto;width:800px;background: #fff;">
    <div class="informations none"></div>
    <div class="body_warp" style="margin-top: 10px;height: auto;overflow-y:hidden;border-top:none; ">
        <div class="title_2 " style="border-top:none;">
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
                    <label>纪要标题：</label><input type="text" class="text max disabled"
                                               value="${mesMeetingInfo.mesTitle!""}"/>
                </li>

                <li>
                    <label>主&nbsp;&nbsp;持&nbsp;&nbsp;人：</label><input type="text" class="text disabled"
                                                                      value="${helper.dataDictionary.getUserNamesByUiids(mesMeetingInfo.compere!"",";")}"
                                                                      readonly="readonly"/>
                    <label class="max">召&nbsp;&nbsp;集&nbsp;&nbsp;人：</label><input type="text"
                                                                                  class="text disabled"
                                                                                  value="${helper.dataDictionary.getUserNamesByUiids(mesMeetingInfo.applicant!"",";")}"
                                                                                  readonly="readonly"/>
                    <label class="max">纪&nbsp;&nbsp;要&nbsp;&nbsp;人：</label>
                    <input type="text" class="text disabled"
                           value="${helper.dataDictionary.getUserNamesByUiids(mesMeetingInfo.sumUserCd!"",";")}"
                           readonly="readonly"/>
                </li>

                <li>
                    <label>会&nbsp;&nbsp;议&nbsp;&nbsp;室：</label><input type="text" class="text disabled"
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
                                版(${helper.dataDictionary.getUserNamesByUiids(summary.creator!"",";")?split(";")[0]}
                                )</a>
                        </#list>
                    </li>
                </#if>
                    <li class="fujian" style="height:auto;">
                        <label>附&nbsp;&nbsp;件：</label>
                        <div>
                        <#if appAttachFiles?size==0>
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
                                )
                            </p>
                        </#list>
                        </div>
                    </li>
                </ul>
            </div>

            <div style="padding:0 0 20px;">
                <div id="content_temp" style="border: 1px solid #C5C5C5; padding: 10px; background:#eaeaea">
                ${content!}
                </div>
            </div>

        <#if mesMeetingInfo.statusCd!="1">
            <div class="publish_auditing_list clearfix">
                <h2>审批记录</h2>
                <ul class="clearfix">
                    <#list mesMeetingInfo.mesApproveNodes as node>
                        <li class="clearfix">
                            <span class="box<#if mesMeetingInfo.approveLevel==node.approveLevel> orange</#if>">${node_index+1}</span>
                            <span class="author">${helper.dataDictionary.getUserNameByCd(node.userCd!)}</span>
                    <span class="status">(<#if node.approveDate?exists>
                        <font>${node.approveDate?string("yy-MM-dd hh:mm")}</font></#if> <#if (node.approveOptionCd!"")=="3">
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

            </div>
        </#if>

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
        //只读表单事件注册
        $(".publish_write .readonly button").click(function () {
            var text = $(this).text();
            if (text == "返回") {
                gotoPager({index:window.pagerindex});
            } else if (text == "导出") {
                $("form#export").trigger("submit");
                TB_showMaskLayer("正在导出请稍后...", 5000);
            } else if (text == "审批历史") {
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
            }
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
                    $("#editor").hide();
                    //$("#xheditor").val($input.message);
                    $("#content_temp").html($input.message).show();
                }
            })
        });

		autoHeight();
    });
</script>


</div>
</div>
</body>
</html>
