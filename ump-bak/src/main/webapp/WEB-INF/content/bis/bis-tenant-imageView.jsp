<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<input type="hidden" id="projectId" name="bisProjectId" value="${bisProjectId}"/>
<input type="hidden" id="floorId" name="bisFloorId" value="${bisFloorId}"/>

<div style="height: 30px; line-height:30px; border: 1px solid #eeeeee;font-size: 14px; padding-left: 10px;font-size:12px">
    <span style="float:left;">
    <span class="color_red">正在查看：</span>商家信息&nbsp;&nbsp;>&nbsp;&nbsp;${bisProjectName}&nbsp;&nbsp;&nbsp;&nbsp;<font
            color="#0693e3">${buildingFloor}&nbsp;&nbsp;层&nbsp;&nbsp;</font>&nbsp;&nbsp;<s:if test="nameCn!=''"><font
            color="#0693e3">${nameCn}</font></s:if>
    <input type="text" class="text" style="width:200px;color:#999;" id="storeShopNo"
           onkeypress="onkeypress_storeShopNo(event)" onfocus="onfocus_storeShopNo()" value="搜索商家/商铺，按回车快速搜索"/>
        </span>
    <button type="button" id="btn_tenant_search" class="green"
            style="float:left;height:24px; margin-left: 5px; line-height: 24px;margin-top: 3px;"
            onclick="bisTenantSearch();">快速搜索
    </button>
</div>


<s:if test="floorVirList.size() > 0">
    <div style="height:30px;" id="floorVirUl">
        <ul class="floorVirUl">
            <s:iterator value="floorVirList">
                <li floorid="${bisFloorId}"
                    <s:if test="bisFloorVirId == bisFloorId">class="floorVirli_click"</s:if>
                    <s:if test="bisFloorVirId != bisFloorId">class="floorVirli"</s:if>
                    onClick="changeVirArea('${bisFloorId}')"
				>${remark }</li>
            </s:iterator>
        </ul>
    </div>
</s:if>
<div style="border: 0px solid #cccccc;float: left;">
    <table style="background-color: #ffffff; border: 0px solid #ccc;">
        <tr>
            <td>
                <div>
                    <img class="mapper noborder iradius16 iopacity80 icolor iborderred"
                         src="${ctx}/resources/images/bis/${floorBigPicUrl}" usemap="#map_of_germany" alt=""
                         style="cursor:crosshair;"/>
                    <map name="map_of_germany" id="map_of_germany">
                        <s:iterator value="listBisStores">
                            <area class="pd-chill-tip" id="${storeNo}" title="商铺号：${storeNo}<br/>面积：${square}m²"
                                  onclick="clickStore('${storeNo}');" shape="poly" coords="${coords}"/>
                        </s:iterator>
                    </map>
                </div>
            </td>
        </tr>
    </table>
</div>
<div style="width:770px;  height:30px;  border-bottom: 1px solid #8c8f94; margin-left: 0px;">
    <ul class="cur_ul">
        <li><span style="background-color:#ac2727"></span>欠费商铺</li>
        <li><span style="background-color:#f9df88"></span>已出租商铺</li>
        <li><span style="background-color:#006fb6"></span>当前选中商铺</li>
    </ul>
</div>

<div id="bigPic_mask"
     style="position:absolute; width:774px; height:412px; left:8px; top:95px; background-color:#eee; filter:alpha(opacity=80); -moz-opacity:0.8;">
    <div style="margin-left:60px; margin-top:180px; font-size:16px; font-weight:bolder; text-align:center;">
        正在初始化平面图中，约需要5-8秒，请稍等。。。
        <br/>
        提示：IE8及以下版本显示平面图效率有限。建议使用IE9，苹果系统，火狐浏览器。
        <br/>
        <img src="${ctx}/resources/images/common/loading.gif"/>
    </div>
</div>

<script type="text/javascript">
    isEmpty = function (str) {
        return (typeof (str) === "undefined" || str === null || (str.length === 0));
    };
    //初始化所有的商铺
    function InitStoreData() {
        AllStores = new Array();
    <s:iterator value="listBisStores">
        AllStores.push(new Store("${bisStoreId}", "${storeNo}", "${square}", "${equityNature}", "${ifPractice}"));
    </s:iterator>
    }
    //初始化所有的租户对象
    function InitTenantData() {
        var tenants = '';
        AllTenants = new Array();
    <s:iterator value="shopStoreList">
        if (tenants.indexOf("${storeNo}") < 0) {
            tenants += "${storeNo}" + ',';
            AllTenants.push(new Tenant("${bisTenantId}", "${nameCn}", "${storeNo}", "${statusCd}"));
        }
    </s:iterator>
    }

    $().ready(function () {
        InitStoreData();
        InitTenantData();
        if (!isEmpty($("#viewType").val())) {
            //addMapper();
            setTimeout(function () {
                addMapper();
            }, 500);
        }
        //初始化所有的商铺的title事件
        InitStoreEvent();
        now_bisTenantId = $('#bisTenantId').val();
        setTimeout(function () {
            PrintTenants();
        }, 1000);
        var projectId = $('#bisProjectId').val();
        if (projectId != null) {
            $('#bisProjectId').val(projectId);
            filterFloor(projectId);
        }
        //turnVirArea();
        var bisTenantId = $('#bisTenantId').val();
        now_bisTenantId = "";
        //selShopStore(bisTenantId);
        setTimeout(function () {
            selShopStore(bisTenantId);
        }, 2000);
        //click_on_return('btn_tenant_search');
    });
</script>
