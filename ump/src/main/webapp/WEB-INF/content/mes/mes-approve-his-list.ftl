<#include "/WEB-INF/content/com/template/autoimport.ftl"/>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="content-Type" content="text/html; charset=utf-8"/>
    <title>会议纪要管理</title>
    <link href="${url.ctx}/resources/css/mes/style.css" media="screen" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        var _ctx = "${url.ctx}";
    </script>
    <style type="text/css">
        html, body {
            overflow: hidden;
            padding: 10px;
        }
    </style>
</head>
<body>
<div class="publish_auditing_list clearfix">
    <h2>审批记录</h2>
    <ul class="clearfix publish_auditing_index">

    <#list mesApproveHis as node>
        <li class="clearfix">
            <span class="box">${node_index+1}</span>
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
</div>
</body>
</html>