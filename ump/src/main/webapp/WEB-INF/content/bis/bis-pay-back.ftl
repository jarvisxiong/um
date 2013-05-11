<#include "/WEB-INF/content/com/template/autoimport.ftl"/>
<#assign security=JspTaglibs["/WEB-INF/security.tld"]>
<!DOCTYPE HTML>
<html style="overflow: auto;">
<head>
    <meta http-equiv="content-Type" content="text/html; charset=utf-8"/>
    <title>支出明细</title>
    <link href="${url.ctx}/resources/css/bis/bis-pay.css" media="screen" rel="stylesheet" type="text/css"/>
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
</head>
<body>

<div id="warp">
    <div id="header">
        <div class="form_body condition_panel clearfix">
            <form method="post" id="query">
                <input type="hidden" id='pageNo' name=pageNo />
                <label>项目：</label>
                <input type="text" class="text" id="bisProjectName" readonly="true" value="${bisProjectName!}"
                       style="padding-left: 5px;width: 120px;"/>
                <input type="hidden" name="filter_EQB_bisProjectId" id="bisProjectId"  value="${bisProjectId}"/>
                <label>类别：</label>
                <select class="box" name="filter_EQB_chargeTypeCd" style="width:100px;">
                    <option value="1">请选择</option>
                    <#list expenseTypes?keys as type>
                        <option value="${type}">${expenseTypes.get(type)}</option>
                    </#list>
                </select>
                <label>年份：</label>
                <select class="box" name="startYear"style="width:100px;">

                </select>


                <label>月份：</label>
                <select class="box" name="startMonth"style="width:100px;">
                    <option value="">请选择</option>
                </select>
                <button class="green min" type="button">搜索</button>
                <button class="orange min" type="button">新增</button>

                <span style="float: right; line-height: 25px">单位：元</span>
            </form>
        </div>
    </div>
    <div id="publish" class="none">
        <div class="form_body condition_panel" style="padding-left: 6px;">
            <ul class="clearfix">
                <form id="input" method="post" action="bis-fact!saveBatchs.action">
                    <input type="hidden" id="id" name="id" />
                    <input type="hidden" id="bisContIdInput" name="bisContIdInput"/>
                    <input type="hidden" id="bisContId" name="bisContId"/>
                    <li style="padding-bottom: 10px;" class="informations none"></li>
                    <li>
                        <label>项目：</label><input id="bisProjectNameInput" type="text" class="text required"style="width: 120px;" readonly="true"/>
                        <input type="hidden" id="bisProjectIdInput" name="bisProjectId" class="required"
                               readonly="readonly"/>

                        <label style="width:46px;">年份：</label>
                        <select class="box required" style="width:100px; padding-left: 2px;" name="factYear" >
                            <option value="">请选择</option>
                        </select>
                        <label style="width:46px;">月份：</label>
                        <select class="box required" style="width:100px; padding-left: 2px;" name="factYear" >
                            <option value="">请选择</option>
                        </select>
                        <label style="width:46px;">预算：</label><input type="text" class="text required"style="width: 88px;" readonly="true"/>
                    </li>
                    <li>
                        <label>类别：</label>
                        <select class="box required" style="width:131px; padding-left: 2px;" name="factYear" >
                            <option value="">请选择</option>
                        </select>
                        <label style="width:46px;">业态：</label>
                        <select class="box required" style="width:100px; padding-left: 2px;" name="factYear" >
                            <option value="">请选择</option>
                        </select>
                        <label style="width:46px;">类别：</label>
                        <select class="box required" style="width:100px; padding-left: 2px;" name="factYear" >
                            <option value="">请选择</option>
                        </select>
                        <label style="width:46px;">支出：</label><input type="text" class="text required"style="width: 88px;"/>
                    </li>
                    <li class="clearfix" style="height: auto; padding-bottom: 3px;">
                        <label>备注：</label>
                        <textarea name="remark" style="width:637px; height: 32px"></textarea>
                    </li>
                    <li class="buttons clearfix" style="padding-left: 41px;margin-top: 5px; margin-bottom: 5px;">
                        <button class="green min " type="button" style="float: left;width:60px;">保存</button>
                        <button class="red min" type="button" style="float:left; margin-left: 10px;width:60px;">取消</button>
                    </li>
                </form>
            </ul>
        </div>
    </div>
    <div class="body">


        <div id="bisManageDayList" style="overflow: auto;overflow-x: hidden; margin: 10px;">
            <table class="stat_table">
                <colgroup>
                    <col width="10%">
                    <col width="10%">
                    <col width="10%">
                    <col width="10%">
                    <col width="5%">
                    <col width="5%">
                    <col width="10%">
                    <col width="10%">
                    <col width="10%">
                </colgroup>
                <thead>


                <tr style="display: table-row;">
                    <th>项目</th>
                    <th>类型</th>
                    <th>业态</th>
                    <th>年份</th>
                    <th>月份</th>
                    <th>预算</th>
                    <th>支出</th>
                    <th>创建人</th>
                    <th>操作日期</th>
                </tr>
                </thead>
                <tbody style="display: table-row-group;">
                <tr>
                    <td>2</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="no">
                    <td colspan="9">
                    <#if page.result?size==0>
                        <div align="center" style="font-weight:bold;height:80px;line-height:80px;">没有相关记录！</div>
                    <#else>
                        <div style="padding:5px;">
                            <@p.page pageInfo="page"/>
                        </div>
                    </#if>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>


    </div>
</div>

<div id="test">
    1
                              <hr/>
    <br/>
    <br/>
    <br/><br/>
    <hr/>
    111
</div>
<script>
    alert($("#test").height());
</script>
</body>
</html>