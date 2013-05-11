<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/taglibs.jsp" %>
    <%@ include file="/common/nocache.jsp" %>
    <%@ include file="/common/global.jsp" %>

    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"/>
    <script type="text/javascript" src="${ctx}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
    <script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
    <script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
    <script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis-batch-oper.js"></script>
    <title>能源费</title>
</head>

<body>

<div class="title_bar">
    <div style="font-weight:bold;padding-left:8px;padding-right:8px; font-size:14px;float:left;">能源费</div>

            <div style="float:right; height:26px; margin-right:5px; margin-top:6px;">
                <div class="btn_green" onclick="clkFullScreen('${bisProjectId}');">全屏</div>
            </div>
</div>

<form action="${ctx}/bis/bis-income-other!importDataPoi.action" method="post" enctype="multipart/form-data"
      id="mainForm">

    <div class="bis_search2" id="main_search_div" style="height: 30px;">
        <table class="tb_search">
            <col width="45"/>
            <col width="110"/>
            <col width="80"/>
            <col width="80"/>
            <col width="80"/>
            <col width="100"/>
            <col width="80"/>
            <col width="70"/>
            <col/>
            <tr>
                <td style="padding-left: 8px;">项目：</td>
                <td>
                    <input readonly="true" type="text" id="bisProjectName" name="bisProjectName" value="${bisProjectName}"
                           style="cursor: pointer; font-size: 12px; color: #ff0000;"/>
                    <input type="hidden" id="bisProjectId" value="${bisProjectId}"/>
                </td>
                <td align="right"><font color="red">*</font>楼宇类型：</td>
                <td>
                    <select style="width: 100%" id="floorType" name="floorType" onchange="changeFloor();">
                        <option value="">--选择--</option>
                        <option value="1">商铺</option>
                        <option value="2">公寓</option>
                        <option value="3">多经</option>
                    </select>
                </td>
                <td id="tdFloorText" align="right">楼(层)号：</td>
                <td id="tdFloorCont">
                    <select style="width: 100%" id="bisFloorId" name="bisFloorId">
                        <option>--选择--</option>
                    </select>
                </td>
                <td id="tdHidden" colspan="2" style="display: none;"></td>
                <td align="right"><font color="red">*</font>收费日期：</td>
                <td>
                    <input class="Wdate" type="text" id="feeDate" name="feeDate" value='${feeDate}'
                           onfocus="WdatePicker({dateFmt:'yyyy-MM'})"/>
                </td>
                <td style="padding-left: 10px;">
                    <security:authorize ifAnyGranted="A_ENER_QUERY">
                        <input type="button" class="btn_blue" onclick="ajaxSearch();" value="搜索"/>
                    </security:authorize>
                    <%--
                 <security:authorize ifAnyGranted="A_ENER_IMPORT">
                 <input type="button" class="btn_green" style="width:65px;" onclick="exportTemplate();" value="导出模板"/>
                 </security:authorize>
                  --%>
                </td>
            </tr>
            <%--
       <security:authorize ifAnyGranted="A_ENER_IMPORT">
       <tr>
           <td colspan="3" style="padding-left: 8px; padding-top: 5px;">
               <input type="file" id="importFile" name="importFile" style="line-height:22px;height:22px;margin-bottom:3px;"/>
           </td>
           <td colspan="6" style="padding-left: 10px;">
               <input type="button" class="btn_blue" style="width:65px;" onclick="importData();" value="导入数据"/>
           </td>
       </tr>
       </security:authorize>
       --%>
        </table>
    </div>

    <div id="contentDiv"></div>

</form>

<script type="text/javascript">
    var isProjectBusinessCompany = "${isProjectBusinessCompany}";
    if (isProjectBusinessCompany == "false") {
        $('#bisProjectName').onSelect({
            muti:false,
            callback:function (project) {
                $("#bisProjectName").val(project.projectName);
                $("#bisProjectId").val(project.bisProjectId);
                changeFloor();
            }
        });
    }
</script>

</body>
</html>
