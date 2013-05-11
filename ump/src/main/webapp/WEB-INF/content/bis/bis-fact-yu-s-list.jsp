
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<style type="text/css">
    .content_table td {
        cursor: default
    }
    .table_pager{
		float: right;
		height: 24px;
		line-height: 24px;
		margin-right: 40px;
	}
	.table_pager a{
		color: #000;
		text-decoration: none;
	}
	.pagesNav .fp,.pagesNav .pp,.pagesNav .np,.pagesNav .lp,.pagesNav input {
	    float: left;
	    height: 22px;
	    margin: 0 5px 0 0;
	    width: 22px;
	}
	.pagesNav input{
		border: 1px solid #ccc;
		height:18px;
	}
	.pagesNav span.tc,.pagesNav span.ni {
		float: left;
	    margin-right: 5px;
	}
	.table_pager input{width:24px;height:24px;border:1px solid #ccc;}
	#pageTo{width:24px;height:22px;}
</style>
<div id="tableDiv">
    <div id="result_div">
        <table class="content_table" style="line-height: 35px;" id="editTable">
            <col width="30">
            <col/>
            <col width="130"/>
            <col width="100"/>
            <col width="100"/>
            <col width="100"/>
            <col width="70"/>
            <col width="100"/>
            <thead>
            <tr class="header">
                <th style="width:100px;"></th>
                <th align="left">商家</th>
                <th align="left">预收金额</th>
                <th align="left">剩余金额</th>
                <th align="left">收款日期</th>
                <th align="left">审核时间</th>
                <th align="left">状态</th>
                <th align="left">操作</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="page.result">
                <!--onclick="loadAdvancesDetail('${bisFactId}');"-->
                <tr class="mainTr" id="main_${bisContId}">
                    <s:set var="shname">
                        <s:property value="%{getShopName(bisTenantId)}"/>&nbsp;
                    </s:set>
                    <s:set var="status">
                    </s:set>
                    <td id="chk_${bisFactId}" nowrap="nowrap" onclick='scheClick("${bisFactId}");' align="center">
                        <div style="display:none;" id="attention_status_${bisFactId}">${statusCd}</div>
                        <div style="display:inline;">
                            <input type="checkbox" id="chk_all" statusCd="${statusCd }" ids="${bisFactId}"
                                   onClick="CAN_scheClick=false;" recordVersion="${recordVersion}"/>
                        </div>
                    </td>
                    <td class="pd-chill-tip" style="padding-right: 6px;" title="${contNo}">
                        <div class="ellipsisDiv_full">
                                ${shname}
                            <p:code2name mapCodeName="tenantMap" value="bisTenantId"/>
                        </div>
                    </td>
                    <td style="padding-right: 6px; padding-left: 10px;" title="${money}">
                            ${money}
                    </td>
                    <td style="padding-right: 6px; padding-left: 10px;" title="${surplusMoney}">
                        <s:if test="surplusMoney==null||surplusMoney==0">0</s:if>
                        <s:if test="surplusMoney!=null&&surplusMoney!=0">
                            ${surplusMoney}
                        </s:if>
                    </td>
                    <td style="padding-right: 6px;text-align: center;"
                        title="<s:date name="factDate" format="yy-MM-dd"/>">
                        <s:date name="factDate" format="yy-MM-dd"/>

                    </td>
                    <td style="padding-right: 6px;text-align: center;"
                        title="<s:date name="checkDate" format="yy-MM-dd"/>">

                        <s:date name="checkDate" format="yy-MM-dd"/>
                    </td>

                    <td title="<p:code2name
                                mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisFactStatus()"
                                value="statusCd"/>" style="text-align: center;">
                        <s:if test="statusCd==1"><span class="color_blue"><p:code2name
                                mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisFactStatus()"
                                value="statusCd"/></span> </s:if>
                        <s:if test="statusCd==2"><span class="color_red"><p:code2name
                                mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisFactStatus()"
                                value="statusCd"/></span></s:if>
                        <s:if test="statusCd==0"><p:code2name
                                mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisFactStatus()"
                                value="statusCd"/></s:if>
                    </td>
                    <td style="text-align: center;"><a href="javascript:;"
                                                       onclick="loadAdvancesDetail('${bisFactId}');">编辑</a><s:if
                            test="surplusMoney>=1&&statusCd==1"> / <a
                            href="javascript:;" onclick="yufuDichong('${bisFactId}')">抵充</a></s:if></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>

    <div class="table_pager" style="margin-top:5px;">
		<p:page />
	</div>

</div>
<div class="bottom_bar" style="maring-top:10px;">
    <div id="operate_all_div" style="display:none;">
        <div class="btn_bottom_chk_all">
            <div style="padding-top:5px;">
            <input type="checkbox" onclick="checkedAll($(this).attr('checked'));" style="cursor:pointer;" title="全选/不选"/>
            </div>
        </div>
        <security:authorize ifAnyGranted="A_FACT_CHECK">
            <div class="btn_bottom_bar" onclick="doUpdateYuSAll(1);">批量审核</div>
        </security:authorize>
    </div>
    <div id="td_page" style="float:right; text-align: center; height:26px; margin-right:10px;"></div>
</div>

<script type="text/javascript">
    var jumpPage = function (index) {
        $("#pageNo").val(index);
        search4YuShou();
    }
    var jumpPageTo = function () {
        $("#pageNo").val($("#pageTo").val());
        search4YuShou();
    }

    var yufuDichong = function (bisFactId) {
        var url = _ctx + '/bis/bis-fact-yu-s!dichong.action';
        var bisProjectId = $('#bisProjectIdFact').val();
        ymPrompt.confirmInfo({
            icoCls:"",
            autoClose:false,
            message:"<div id='selectTypeDiv' style='padding:10px 0;'><img align='absMiddle' src='" + _ctx + "/images/loading.gif'></div>",
            width:460,
            height:260,
            title:"抵充",
            afterShow:function () {
                $.post(url, {id:bisFactId, bisProjectId:bisProjectId}, function (result) {
                    $("#selectTypeDiv").html(result);
                    quickSTenant4YuSHou(bisProjectId);
                });
            },
            handler:function (btn) {
                ymPrompt.close();
            },
            closeBtn:true,
            btn:[]
        });
    }
</script>