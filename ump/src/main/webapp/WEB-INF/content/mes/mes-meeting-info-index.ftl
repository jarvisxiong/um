<#include "/WEB-INF/content/com/template/autoimport.ftl"/>
<#assign security=JspTaglibs["/WEB-INF/security.tld"]>
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<!DOCTYPE HTML>
<html>
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
<body>


    
<div id="warp">
    <div id="header">
        <div class="title1 clearfix">
            <h2>纪要管理系统</h2>
            <div class="btns">
            <@security.authorize ifAnyGranted="A_MES_ADMIN,A_MES_READ_ALL">
                <button class="blue" type="button">决议归档</button>
                <button class="blue" type="button">纪要归档</button>
            </@security.authorize>
                <button class="blue" type="button">我的纪要</button>
            <@security.authorize ifAnyGranted="A_MES_PUBLISH">
                <button class="orange" type="button">新增纪要</button>
            </@security.authorize>
            </div>
        </div>
        <div class="form_body condition_panel none ">
            <ul class="clearfix">
                <form id="query" method="post">
                    <input type="hidden" name="recodeType" value="0"/>
                    <input type="hidden" name="status" value="0"/>
                    <input type="hidden" name="page.orderBy" value="startDate"/>
                    <input type="hidden" name="page.order" value="desc"/>
                    <li><label>主题：</label><input type="text" class="text max" name="subject"/></li>
                    <li>
                        <label>主持人：</label><input type="text" class="text"
                                                                          id="query_compere"/><input type="hidden"
                                                                                                     class="text"
                                                                                                     name="compere"/>
                        <label class="max">纪要人：</label><input type="text" class="text"
                                                                                      id="query_sumUserCd"/><input
                            type="hidden" class="text" name="sumUserCd"/>
                        <label class="max">召集人：</label><input type="text" class="text"
                                                                                      id="query_applicant"/><input
                            type="hidden" class="text" name="applicant"/>
                    </li>
                    <li>
                        <label>会议室：</label><input type="text" class="text" name="mesRoom"/>
                        <label class="max">会议日期：</label><input type="text" class="text c_red" name="fromDate"
                                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                        <label class="max">参会人员：</label><input type="text" class="text" id="query_participators"/><input
                            type="hidden" class="text" name="participators"/>
                    </li>
                    <li class="category clearfix" style="height:auto;">
                        <label>会议类型：</label>

                        <div class="box">
                        <#list helper.categorys?keys as key>
                            <#if key_index!=0>
                                <span><input type="checkbox" checked="true" id="category-${key_index}"
                                             name="mesTypeCd"
                                             value="${key}"/><label
                                        for="category-${key_index}">${helper.categorys.get(key)}</label></span>
                            </#if>
                        </#list>
                        </div>
                    </li>
                    <li class="clearfix status" style="display: none;">
                        <label>会议状态：</label>
                        <select name="statusTemp" class="box">
                        <#list helper.status?keys?sort as key>
                            <option value="${key}">${helper.status.get(key)}</option>
                        </#list>
                        </select>
                    </li>
                    <li class="buttons clearfix">
                        <button type="button" class="blue min">搜索</button>
                        <button type="button" class="green">取消</button>
                    </li>
                </form>
            </ul>
        </div>
    </div>
    <div id="body"></div>
</div>


<script type="text/javascript">
    $(function () {

        $.ajaxSetup({cache:false});
        //高级搜索 人员选择 事件注册
        $("#query_compere,#query_recorder,#query_applicant,#query_participators,#query_sumUserCd").ouSelect({
            showGroupFlg:true,
            muti:false
        });
        //列表加载方法/分页方法
        window.gotoPager = function ($input) {
            TB_showMaskLayer("正在搜索请稍后...", 5000);
            var params = $("form#query").serializeArray();
            if ($input.index) {
                params[params.length] = {name:"page.pageNo", value:$input.index}
            }
            $.post("mes-meeting-info!list.action", $.param(params), function ($html) {
                $("#body").html($html);
                TB_removeMaskLayer();
                var status = $(".condition_panel input[name=status]").val();
        		autoHeight();
            });
        }

        //会议状态
        $("select[name=statusTemp]").change(function () {
            var value = $(this).val();
            $("#header input[name=status]").val("" == value ? "-1" : value);
        });

        var btns = window.headerButtons = $("#header  button").click(function (event, index) {

            var text = $(this).text();
            if (text == "新增纪要") {
                $("#body").load("mes-meeting-info!input.action", function () {
                    $(".condition_panel").hide(0);
                });
                $(".condition_panel .status").hide();
            } else if (text == "我的纪要") {
                $(".condition_panel .status").hide();
                $("#header input:not(:checkbox,[name=page.orderBy],[name=page.order])").val("");
                $("#header input[name=recodeType]").val("0");
                $("#header input[name=status]").val("0");
                $("#header select option:first").attr("selected", true);
                gotoPager({});
            } else if (text == "纪要归档") {
                $("#header input[name=recodeType]").val("1");
                $("#header input[name=status]").val("-1");
                $(".condition_panel .status").show()
                $("#header input:checkbox").attr("checked", true);
                gotoPager({});
            } else if (text == "决议归档") {
                $("#header input[name=recodeType]").val("2");
                $("#header input[name=status]").val("-1");
                $(".condition_panel .status").show()
                gotoPager({});
            } else if (text == "搜索") {
                gotoPager({});
            } else if (text == "取消") {
                $(".condition_panel").hide(0);
            }
        });
        //分页事件
        window.jumpPage = function (index) {
            //出发父窗口 分页方法，到 指定的某一页
            window.gotoPager({index:index});
        }
        window.jumpPageTo = function () {
            window.jumpPage($("#pageTo").val());
        }
        var href = location.href.toString();
        if (!/reload|id/.test(href)) {
            gotoPager({});
            //$("#body").load("mes-rst-resolution!publish.action", {mesMeetingInfoId:"4028292c35a8bcfb0135a8eb3e510007"});
            //$("#body").load("../test.html");
        } else if (/reload=/.test(href)) {
            location.href = "?id=" + href.split("reload=")[1];
        } else if (/id=/.test(href)) {
            $("#body").load("mes-summary!input.action?mesMeetingInfoId=" + href.split("id=")[1]);
        }
    });
</script>
</body>
</html>
