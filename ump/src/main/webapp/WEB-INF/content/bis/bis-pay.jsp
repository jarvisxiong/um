<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/taglibs.jsp" %>
    <%@ include file="/common/nocache.jsp" %>
    <%@ include file="/common/global.jsp" %>


    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-project.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css"/>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
    <script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/customInput/customInput.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
    <script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
    <title>支出列表</title>
    <script type="text/javascript">
        var _ctx = '${ctx}';
    </script>
    <style>
        .scrollDetail {
            overflow: auto;
        }

            /*必选控件样式*/
        .mustSelet {
            color: red;
        }

        table.content_table {
            bord: 1px solid;
            width: 100%
        }

        table.content_table th {
            text-align: left;
            line-height: 30px;
            height: 30px;
        }

        table.content_table td {
            text-align: left;
            line-height: 30px;
            height: 30px;
        }
    </style>
</head>
<body>
<div class="title_bar">
    <s:form id="searchForm" action="">

        <input type="hidden" name="id" id="id" value="${bisPayId}"/>
        <input type="hidden" name="bisPayId" id="bisPayId" value="${bisPayId}"/>
        <input type="hidden" id="pageNo" name="pageNo" value="${pageNo }" />
        <table class="tb_search">
            <col/>
            <tr>
                <td style="padding-left: 8px;" align="right">项目：</td>
                <td>
                    <input readonly="true" type="text" validate="required" id="bisProjectName" name="bisProjectName"
                           value="${bisProjectName}"
                           style="width:100%; cursor: pointer; font-size: 12px; color: #ff0000;"/>
                    <input type="hidden" id="bisProjectId" name="filter_EQB_bisProjectId" value="${bisProjectId}"/>
                </td>
                <td style="padding-left: 8px;" align="right">类别：</td>
                <td><s:select cssClass="select_115_20" validate="required"
                              list="@com.hhz.ump.util.DictMapUtil@getMapExpenseType()" listKey="key"
                              listValue="value" style="width:170px;"
                              name="filter_EQB_chargeTypeCd" id="chargeTypeCd"></s:select></td>
                <td style="padding-left: 8px;" align="right">年：</td>
                <td><s:select validate="required"
                              list="@com.hhz.ump.util.DictMapUtil@getMapYear()" listKey="key" listValue="value"
                              style="width:60px;"
                              name="filter_EQB_year" id="year"></s:select></td>
                <td style="padding-left: 8px;" align="right">月：</td>
                <td><s:select validate="required"
                              list="@com.hhz.ump.util.DictMapUtil@getMapMonth()" listKey="key"
                              listValue="value" style="width:40px;"
                              name="filter_EQB_month" id="month"></s:select></td>
                <td><input type="button" onclick="searchPay();" value="搜索" class="btn_blue" style="margin-left:5px;width:65px;"/>
                </td>
                <security:authorize ifAnyGranted="A_EXPE_INSERT">
                    <td><input type="button" onclick="add();" value="新增" class="btn_blue" style="margin-left:5px;width:65px;"/>
                    </td>
                </security:authorize>
                 <td>
                 	<input type="button" onclick="exportFile();" value="导出" class="btn_green" style="margin-left:5px;width:65px;"/>
                 </td>
            </tr>
        </table>
    </s:form>
</div>
<div id="addContent"
     style="display:none;clear:both;margin:0px;padding:5px;border-bottom:1px solid #dddbdc;background:#f7f7f7;">
</div>
<div id="tip" style="display: none;color:red"></div>
<div id="welcom" style="clear:both;height:30px;margin:10px;">
    <div style="color:#6BAD42;font-size:16px;font-weight:bold;width:auto;margin-top:200px;text-align:center;">
        请选择搜索条件
    </div>
</div>
<div id="detailPanel" style="clear:both;margin:10px;">
</div>
<script type="text/javascript">
    var currProjectId = '${bisProjectId}';
    var currProjectName = '${projectName}';
    var isProjectBusinessCompany = "${isProjectBusinessCompany}";
    $(function () {
        $('#bisProjectName').val(currProjectName);
        $('#bisProjectId').val(currProjectId);
        if (isProjectBusinessCompany == "false") {
            $('#bisProjectName').onSelect({muti:false});
        }
        searchPay();
    });
    function searchPay() {
        $('#welcom').hide();
        $('#pageNo').val('');
        $('#addContent').hide();
        $("#searchForm").attr('action', '${ctx }/bis/bis-pay!search.action');
        $("#searchForm").ajaxSubmit(function (result) {
            $("#detailPanel").html(result).show();
        });
    }
    var searchData;
    function loadSearchParam() {
        var bisProjectId = $('#bisProjectId').val();
        var chargeTypeCd = $('#chargeTypeCd').val();
        var year = $('#year').val();
        var month = $('#month').val();
        searchData = {bisProjectId:bisProjectId, chargeTypeCd:chargeTypeCd, year:year, month:month};
    }
    function add() {
        if ($('#addContent').css('display') == 'none') {
            var url = '${ctx}/bis/bis-pay!input.action';
            loadSearchParam();
            $.post(url, searchData, function (result) {
                $('#addContent').html(result).show();
                $('#bisProjectNameInput').onSelect({muti:false});
            });
        } else {
            $('#addContent').html('').hide();
        }

    }

    function addPay() {
        var bisProjectId = $('#bisProjectIdInput').val();
        var year = $('#yearInput').val();
        var month = $('#monthInput').val();
        var chargeTypeCd = $('#chargeTypeCdInput').val();
        var operationType = $('#operationType').val();
        var money = $('#moneyInput').val();
        if (bisProjectId == '' || year == '' || month == '' || chargeTypeCd == '' || operationType === '-1') {
            alert('必填项不能为空');
            return;
        }
        if (money == '') {//budgetMoney==''
            alert('支出金额不能都为空');
            return;
        }
        $("#save").attr('action', '${ctx }/bis/bis-pay!save.action');
        $("#save").ajaxSubmit(function (result) {
            ymPrompt.close();
            if (result == 'success') {
                alert('保存成功');
                //$('#tip').html('保存成功').show().fadeOut(2000);
                searchPay();
            }
        });
    }
    function addPay2(bisProjectId, chargeTypeCd, year, month, budgetMoney, money) {
        var data = {bisProjectId:bisProjectId, chargeTypeCd:chargeTypeCd, year:year, month:month, budgetMoney:budgetMoney, money:money};
        var url = '${ctx}/bis/bis-pay!save.action';
        $.post(url, data, function (result) {
            if (result == 'success') {
                alert('保存成功');
                //$('#tip').html().show().fadeOut(2000);
            }
            $('#addContent').hide();
        });
    }

    function edit(id) {
        $('#addContent').hide().html("");
        var url = '${ctx}/bis/bis-pay!input.action';
        ymPrompt.confirmInfo({
            icoCls:"",
            autoClose:false,
            message:"<div id='selectTypeDiv' style='padding-right:10px;'><img align='absMiddle' src='" + _ctx + "/images/loading.gif'></div>",
            width:700,
            height:235,
            title:"修改",
            afterShow:function () {
                $.post(url, {id:id}, function (result) {
                    $("#selectTypeDiv").html(result);
                });
            },
            handler:function (btn) {
                ymPrompt.close();
            },
            closebtn:true,
            btn:[]
        });
    }

    var saveoptions = {
        url:'${ctx }/bis/bis-pay!save.action',
        success:function (data) {
            $('#tip').html('保存成功').show().fadeOut(2000);
        }
    };

    function jumpPage(pageNo) {
        $('#pageNo').val(pageNo);
        $("#searchForm").attr('action', '${ctx }/bis/bis-pay!search.action');
        $("#searchForm").ajaxSubmit(function (result) {
            $("#detailPanel").html(result).show();
        });
    }
    function deletePay(id) {
        var url = '${ctx}/bis/bis-pay!delete.action';
        if (confirm("确定删除？")) {
            $.post(url, {id:id}, function (result) {
                if (result == 'success') {
                    //$("#detailPanel").mask('删除成功');
                    searchPay();
                } else {
                    //$("#detailPanel").mask('删除失败');
                }
                ymPrompt.close();
            });
        }
    }

    /**支出明细报表导出*/
    function exportFile() {
        $('#welcom').hide();
        $('#pageNo').val('');
        $('#addContent').hide();
        $("#searchForm").attr('action', '${ctx }/bis/bis-pay!search.action');

        var url = '${ctx}/bis/bis-pay!search.action?isExcel=isExcel&' + $("#searchForm").serialize();
        window.location.href = url;
    }
</script>

</body>
</html>
