<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/global.jsp" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil" %>
<%@page import="com.hhz.ump.util.DictMapUtil" %>
<%@page import="com.hhz.ump.util.JspUtil" %>
<%@page import="java.util.Map;" %>
<title>支出列表</title>
<style>
    .mustSelet {
        color: red;
    }

    table.content_table {
        bord: 1px solid;
        width: 100%
    }

    table.content_table th {
        text-align: left;
        padding-left: 15px;
    }

    table.content_table td {
        text-align: left;
    }
	.table_pager {
	float: right;
	margin: 5px 0;
}
.table_pager input{width:24px;height:24px;border:1px solid #ccc;}
#pageTo{width:24px;height:22px;}
</style>
<table class="content_table" style="">
    <col width="100">
    <col width="80">
    <col width="80">
    <col width="50">
    <col width="50">
    <col width="100">
    <col width="100">
    <col width="80">
    <col width="100">
    <tr>
        <th style="font-weight:bold;">项目</th>
        <th style="font-weight:bold;">业态</th>
        <th style="font-weight:bold;">费用类别</th>
        <th style="font-weight:bold;">年</th>
        <th style="font-weight:bold;">月</th>
        <th style="font-weight:bold;" id="money1">预算</th>
        <th style="font-weight:bold;" id="money2">支出金额(元)</th>
        <th style="font-weight:bold; ">创建人</th>
        <th style="font-weight:bold; ">操作日期</th>
    </tr>
    <s:iterator value="payList" status="pay">
        <tr id="'${bisPayId}'" onclick="edit('${bisPayId}')" class="mainTr">
            <td>${projectName }</td>
            <td>
                <c:if test='${remark==1}'>商铺</c:if>
                <c:if test='${remark==2}'>公寓</c:if>
                <c:if test='${remark==3}'>多经</c:if>
                <c:if test='${remark==4}'>其他选项</c:if>
            </td>
            <td>
            <%
            Map map=DictMapUtil.getMapExpenseType();
            map.put("B29","营业外收入"); 
            out.print(map.get(JspUtil.findString("chargeTypeCd")));
            %>
            </td>
            <td>${year}</td>
            <td>${month}</td>
            <td style="text-align: right;">${budgetMoney}</td>
            <td style="text-align: right;">${money}</td>
            <td><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>
            </td>
            <td><s:date name="opearateDate" format="yy-MM-dd HH:mm"/></td>
        </tr>
    </s:iterator>
    <tr>
        <td></td>
        <td colspan="4" style="text-align: center ;">合计：</td>
        <td style="text-align: right;">${budgetMoney}</td>
        <td style="text-align: right;">${money}</td>
        <td colspan="2"></td>
    </tr>
</table>
<div class="table_pager">
	<p:page />
</div>
<!--<div class="table_pager" style="margin-top:5px;display: none;">
    <div style="width: 100%;">
        &nbsp;共有&nbsp;${page.totalCount}&nbsp;条记录&nbsp;&nbsp;&nbsp;&nbsp;
        当前第 ${page.pageNo}/${page.totalPages}&nbsp;页
        <s:if test="page.hasPre">
            <img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_up.gif"
                 onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_up_hover.gif');"
                 onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_up.gif');"
                 onclick="jumpPage('${page.prePage}');"/>
        </s:if>
        <s:else>
            <img align="absmiddle" src="${ctx}/images/desk2/page_up_grey.gif"/>
        </s:else>
        <s:if test="page.hasNext">
            <img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_down.gif"
                 onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_down_hover.gif');"
                 onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_down.gif');"
                 onclick="jumpPage('${page.nextPage}');"/>
        </s:if>
        <s:else>
            <img align="absmiddle" src="${ctx}/images/desk2/page_down_grey.gif"/>
        </s:else>
        <input type="text" id="pageNo" style="height: 15px; width: 20px; text-align: center;" value="${page.pageNo}"/>
        <a href="#" onblur="this.blur();" onclick="jumpPage($(this).prev().val()); return false;">GO</a>

    </div>

</div>-->

<script type="text/javascript">
    $(function () {
        var type = $('#chargeTypeCd').val();
        if (type === "B29") {
            $("#money1").text("预计收入(元)");
            $("#money2").text("实际收入(元)");
        }
    });
</script>
