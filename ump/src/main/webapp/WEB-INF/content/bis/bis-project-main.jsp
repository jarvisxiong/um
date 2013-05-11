<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/global.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-project.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/cost-ctrl.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"/>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
    <script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis-project.js"></script>
    <title>项目基础信息</title>
    <script type="text/javascript">
        var isProjectBusinessCompany = "${isProjectBusinessCompany}";
    </script>
</head>
<body>
<form action="${ctx}/bis/bis-project!save.action" method="post" id="seaFloorForm">
<input type="hidden" name="recordVersion" id="recordVersion" value="${recordVersion}"/>
<input type="hidden" name="floorType"/>
<input type="hidden" id="projAdmin" value="${projAdmin}"/>
<input type="hidden" name="bisProjectId"/>
<input type="hidden" id="floorTypeSplit" value="${floorType}"/>
<input type="hidden" id="floorNumSplit" value="${bisStore.bisFloor.bisFloorId}`${bisStore.bisProjectId}"/>
<input type="hidden" name="bisFloorId"/>
<s:select cssStyle="display:none;" list="mapBuilding" listKey="key" listValue="value" id="bisBuildingHid"></s:select>
<s:select cssStyle="display:none;" list="mapFloor" listKey="key" listValue="value" id="bisFloorHid"></s:select>

<s:set var="module">7</s:set>
<%@ include file="bis-manage-header.jsp" %>

<%--
    <div class="title_bar"  style="float:left;font-size:12px;padding-right:20px;line-height:30px;width:100%;">
        <table class="main_content" style="table-layout:fixed;" align="left">
            <!-- begin 公用信息部分 -->
            <tr>
              <td><div style="font-weight:bold;padding-left:10px;font-size:14px;float:left;">商业项目</div></td>
                <td>&nbsp;&nbsp;&nbsp;商业项目:
                    <s:select list="mapBisProject" listKey="key" listValue="value"  id="bisProjectId" onchange="filterFloor(this.value);"></s:select>
                    楼宇类型:
                    <select  id="floorType" onchange="changeFloorType(this.value);">
                        <option value="">--选择--</option>
                        <option value="1">商铺</option>
                        <option value="2">公寓</option>
                        <option value="3">多经</option>
                    </select>
                </td>
                <td id="building" style="padding-left: 6px;">
                    楼号：
                </td>
                <td id="bisFloorTd">
                <select  id="bisFloorId" onchange="bisFloorSearch()"><option>--选择--</option></select>
                </td>
                <td id="numName" style="padding-left: 6px;">
                    编号：
                </td>
                <td><input type="text" id="num" style="width: 110px;"></input></td>

                <td id="buildingHide" style="display: none;">楼号:<s:select list="mapBuilding" listKey="key" listValue="value" id="bisBuildingHid" ></s:select></td>
                <td id="floorHide" style="display: none;">楼层号:<s:select list="mapFloor" listKey="key" listValue="value" id="bisFloorHid" ></s:select></td>
                <td style="padding-left: 4px;">
                     <input type="button" class="btn_blue" onclick="bisFloorSearch();" value="搜索" />
                     <input type="button"  class="btn_blue" onclick="bisFloorAdd();" value="新增"/>
                </td>
            </tr>
            <!-- end 公用信息部分 -->
        </table>
    </div>
    --%>

<div style="height:100%;padding-top:10px;border-bottom:1px solid #DDDBDC;background-color:#F7F7F7;display:none;"
     id="titleAdd">
<fieldset style="border:0;">
<legend style="padding-left:10px;background-color: #F7F7F7;"><font
        style="font-weight:bolder; font-size:14px; color:#fb9032;">新增</font>
    <font id="luoyanginfo" style="display:none;font-weight: lighter; color: rgb(0, 0, 0);"></font>
</legend>
<div>
    <table class="main_content" border="0">
        <col width="160px">
        <col width="150px">
        <col width="160px">
        <col width="150px">
        <col width="160px">
        <col width="150px">
        <col width="30px">
        <tr>
            <td align="right">楼宇类型：</td>
            <td>
                <select class="required" id="floorTypes" onchange="floTypes(this.value);">
                    <option value="">--选择--</option>
                    <option value="1">商铺</option>
                    <option value="2">公寓</option>
                    <option value="3">多经</option>
                </select>
            </td>
            <td align="right">项目：</td>
            <td>
                <input class="required" type="text" readonly="true" id="bisProjectNames" name="bisProjectName" value="${bisProjectName}"
                       style="cursor: pointer; font-size: 12px; color: #ff0000;"/>
                <input type="hidden" id="bisProjectIds" value="${bisProjectId}"/>
            </td>
            <td align="right">
                <span id="storeBuildings">楼层号：</span>
                <span id="flatBuildings" style="display:none;">楼号：</span>
            </td>
            <td>
                <span id="bisFloorTds"><select class="required" id="bisFloorIds">
                    <option>--选择--</option>
                </select></span>

            </td>
            <td></td>
        </tr>
    </table>
</div>
<div id="new_Store" style="display: none;">
    <table class="main_content" border="0">
        <col width="160px">
        <col width="150px">
        <col width="160px">
        <col width="150px">
        <col width="160px">
        <col width="150px">
        <col width="30px">
        <tr>
            <td align="right">编号：</td>
            <td>
                <input type="text" id="storeNo" class="required" name="bisStore.storeNo"
                       onkeyup="storeValidate(this);"/>
            </td>
            <td align="right">建筑面积㎡(图纸)：</td>
            <td>
                <input type="text" id="square" class="required" alt="amount" name="bisStore.square"/>
            </td>
            <td align="right">套内面积㎡(图纸)：</td>
            <td>
                <input type="text" id="innerSquare" class="required" alt="amount" name="bisStore.innerSquare"/>
            </td>
            <td></td>
        </tr>
        <tr>
            <td align="right">建筑面积㎡(实测)：</td>
            <td>
                <input type="text" id="square" alt="amount" name="bisStore.squareReal"/>
            </td>
            <td align="right">套内面积㎡(实测)：</td>
            <td>
                <input type="text" id="innerSquare" alt="amount" name="bisStore.innerSquareReal"/>
            </td>
            <td align="right">计租面积㎡：</td>
            <td>
                <input type="text" id="rentSquare" class="required" alt="amount" name="bisStore.rentSquare"/>
            </td>
            <td></td>
        </tr>
        <tr>
            <td align="right">销售单价(元/㎡)：</td>
            <td>
                <input type="text" id="sellPrice" alt="amount" name="bisStore.sellPrice"/>
            </td>
            <td align="right">开业日期：</td>
            <td>
                <%--onblur="updateContStatus('plan');" --%>
                <input type="text" name="bisStore.openDate" id="openDate" onfocus="WdatePicker()"/>
            </td>
            <td align="right">规划业态：</td>
            <td>
                <s:select cssStyle="width:100%;" class="required"
                          list="@com.hhz.ump.util.DictMapUtil@getMapStoreLayout()" listKey="key"
                          listValue="value" name="bisStore.layoutCd" id="layoutCd"></s:select>
            </td>
            <td></td>
        </tr>
        <tr>
            <td align="right">租金标准(元/㎡)：</td>
            <td>
                <input type="text" id="rentStandard" class="required" alt="amount" name="bisStore.rentStandard"/>
            </td>
            <td align="right">物业标准(元/㎡)：</td>
            <td>
                <input type="text" id="tenemStandard" class="required" alt="amount" name="bisStore.tenemStandard"/>
            </td>
            <td align="right">产权性质：</td>
            <td>
                <s:select class="required" list="@com.hhz.ump.util.DictMapUtil@getMapPropertyRight()"
                          listKey="key" listValue="value" name="bisStore.equityNature" id="equityNature"
                          onchange="showStatus(this);"></s:select>
            </td>
            <td></td>
        </tr>
        <tr>
            <td align="right">
                <span class="propRight" style="display:none">业主：</span>
            </td>
            <td>
                <input type="text" id="owner" class="propRight" style="display:none" name="bisStore.owner"/>
            </td>
            <td align="right">
                <span class="propRight" style="display:none"> 经营现状：</span>
            </td>
            <td>
                <s:select id="manageStatus" cssClass="propRight" cssStyle="display:none"
                          list="@com.hhz.ump.util.DictMapUtil@getBisManagementStatus()" listKey="key"
                          listValue="value" name="bisStore.managementStatus"></s:select>

            </td>
            <td align="right">
                <span class="propRight" style="display:none">是否开业：</span>
            </td>
            <td>
                <select id="ifPractice" name="bisStore.ifPractice" class="propRight" style="display:none">
                    <option value="">--选择--</option>
                    <option value="1">已开业</option>
                    <option value="2">未开业</option>
                </select>
            </td>
            <td></td>
        </tr>
        <tr>
            <td align="right">商铺定位：</td>
            <td>
                <s:select list="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" listKey="key"
                          listValue="value" name="bisStore.shopPosition"></s:select>
            </td>
        </tr>
        <tr>
            <td align="right">备注：</td>
            <td colspan="5"><textarea name="bisStore.storeRemark" id="remark"></textarea></td>
            <td></td>
        </tr>
        <tr>
            <td align="right"><font color="red">注意：</font></td>
            <td colspan="5"><font color="red">请填写商铺单元如：1001，保存后再填写1002 ；禁止填写商铺集合 如：1001~1005 。</font></td>
            <td></td>
        </tr>
    </table>
</div>
<div id="new_Flat" style="display:none;">
    <table class="main_content" border="0" cellpadding="0" cellspacing="0" style="width:100%;height:100%;">
        <col width="160px"/>
        <col width="150px"/>
        <col width="160px"/>
        <col width="150px"/>
        <col width="160px"/>
        <col width="150px"/>
        <col width="30px"/>
        <tr>
            <td align="right">编号：</td>
            <td>
                <input class="required" type="text" id="flatNo" name="bisFlat.flatNo"/>
            </td>
            <td align="right">客户名称：</td>
            <td>
                <input class="required" type="text" id="flat_customerName" name="bisFlat.customerName"/>
            </td>
            <td align="right">住宅底商：</td>
            <td>
            	<s:select cssClass="required" id="flat_houseShopFlag" name="bisFlat.houseShopFlag" list="@com.hhz.ump.util.DictMapUtil@getMapEnableFlgNum()" listKey="key" listValue="value"></s:select>
            </td>
            <td></td>
        </tr>
        <tr>
        	<td align="right">建筑面积㎡：</td>
            <td>
                <input class="required" type="text" id="flat_square" alt="amount" name="bisFlat.square"/>
            </td>
            <td align="right">套内面积㎡：</td>
            <td>
                <input class="required" type="text" id="flat_innerSquare" alt="amount" name="bisFlat.innerSquare"/>
            </td>
             <td align="right">实测建筑面积㎡：</td>
            <td>
                <input class="required" type="text" id="flat_actualSquare" alt="amount" name="bisFlat.actualSquare"/>
            </td>
            <td></td>
        </tr>
        <tr>
        	<td align="right">实测套内面积㎡：</td>
            <td>
                <input class="required" type="text" id="flat_actualInnerSquare" alt="amount" name="bisFlat.actualInnerSquare"/>
            </td>
            <td align="right">月物管费单价(元/月)：</td>
            <td>
                <input class="required" type="text" id="flat_monPromanfeePrice" alt="amount" name="bisFlat.monPromanfeePrice"/>
            </td>
           	<td align="right">出售日期：</td>
            <td>
                <s:textfield name="bisFlat.openDate" id="flat_openDate" onfocus="WdatePicker()" onblur="updateContStatus('plan');"/>
            </td>
            <td></td>
            <%--
            <td align="right">以前年度累计应收(物管费)：</td>
            <td>
                <input class="required" type="text" id="flat_annualYsMan" alt="amount" name="bisFlat.annualYsMan"/>
            </td>
            <td></td>
             --%>
        </tr>
        <%--
        <tr>
        	<td align="right">以前年度累计实收(物管费)：</td>
            <td>
                <input class="required" type="text" id="flat_annualSsMan" alt="amount" name="bisFlat.annualSsMan"/>
            </td>
            <td align="right">以前年度累计预收(物管费)：</td>
            <td>
                <input class="required" type="text" id="flat_annualYusMan" alt="amount" name="bisFlat.annualYusMan"/>
            </td>
            <td align="right">以前年度累计欠收(物管费)：</td>
            <td>
                <input class="required" type="text" id="flat_annualQsMan" alt="amount" name="bisFlat.annualQsMan"/>
            </td>
            <td></td>
        </tr>
        <tr>
        	<td align="right">以前年度累计应收(水费)：</td>
            <td>
                <input class="required" type="text" id="flat_annualYsWater" alt="amount" name="bisFlat.annualYsWater"/>
            </td>
            <td align="right">以前年度累计实收(水费)：</td>
            <td>
                <input class="required" type="text" id="flat_annualSsWater" alt="amount" name="bisFlat.annualSsWater"/>
            </td>
            <td align="right">以前年度累计预收(水费)：</td>
            <td>
                <input class="required" type="text" id="flat_annualYusWater" alt="amount" name="bisFlat.annualYusWater"/>
            </td>
            <td></td>
        </tr>
        <tr>
        	<td align="right">以前年度累计欠收(水费)：</td>
            <td>
                <input class="required" type="text" id="flat_annualQsWater" alt="amount" name="bisFlat.annualQsWater"/>
            </td>
            <td align="right">以前年度累计应收(电费)：</td>
            <td>
                <input class="required" type="text" id="flat_annualYsElec" alt="amount" name="bisFlat.annualYsElec"/>
            </td>
            <td align="right">以前年度累计实收(电费)：</td>
            <td>
                <input class="required" type="text" id="flat_annualSsElec" alt="amount" name="bisFlat.annualSsElec"/>
            </td>
            <td></td>
        </tr>
        <tr>
        	<td align="right">以前年度累计预收(电费)：</td>
            <td>
                <input class="required" type="text" id="flat_annualYusElec" alt="amount" name="bisFlat.annualYusElec"/>
            </td>
            <td align="right">以前年度累计欠收(电费)：</td>
            <td>
                <input class="required" type="text" id="flat_annualQsElec" alt="amount" name="bisFlat.annualQsElec"/>
            </td>
           	<td align="right">出售日期：</td>
            <td>
                <s:textfield name="bisFlat.openDate" id="flat_openDate" onfocus="WdatePicker()" onblur="updateContStatus('plan');"/>
            </td>
            <td></td>
        </tr>
         --%>
        <tr>
            <!--<td align="right">公摊面积㎡：</td>
                   <td width="150">
                       <input type="text" id="publicSquare" alt="amount" name="bisFlat.publicSquare" />
                   </td>
                   -->
            <td align="right">房屋结构：</td>
            <td>
                <s:select list="@com.hhz.ump.util.DictMapUtil@getMapHouseStru()" listKey="key"
                          listValue="value" name="bisFlat.houseStruCd" id="flat_houseStruCd"
                          cssStyle="width:100%;"></s:select>
            </td>
            <td align="right">规划业态：</td>
            <td>
                <s:select list="@com.hhz.ump.util.DictMapUtil@getMapFlatLayout()" listKey="key"
                          listValue="value" name="bisFlat.layoutCd" id="flat_layoutCd"
                          cssStyle="width:100%;"></s:select>
            </td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <td align="right">备注：</td>
            <td colspan="3"><textarea name="bisFlat.remark" id="flat_remark"></textarea></td>
            <td colspan="3"></td>
        </tr>
    </table>
</div>
<div id="new_Multi" style="display:none;">
    <table class="main_content" border="0" cellpadding="0" cellspacing="0" style="width:100%;height:100%;">
        <col width="160px">
        <col width="150px">
        <col width="160px">
        <col width="150px">
        <col width="160px">
        <col width="150px">
        <col width="30px">
        <tr>
            <td align="right">多经编号：</td>
            <td>
                <input type="text" id="multiName" name="bisMulti.multiName"/>
            </td>
            <td align="right">经营项目：</td>
            <td>
                <input type="text" id="operationProjectCd" name="bisMulti.operationProjectCd"/>
            </td>
            <%--
                   <td align="right">承租方：</td>
                   <td>
                       <input type="text" id="renterName" name="bisMulti.renterName" />
                   </td>
                   --%>
            <td colspan="3"></td>
        </tr>
        <tr>
            <td align="right">执行价格：</td>
            <td>
                <input type="text" id="multiPrice" name="bisMulti.multiPrice"/>
            </td>
            <td align="right">面积㎡：</td>
            <td>
                <input type="text" id="multi_square" alt="amount" name="bisMulti.square"/>
            </td>
            <td colspan="3"></td>
        </tr>
        <tr>
            <td align="right">多经类型：</td>
            <td>
                <select id="multi_type" name="bisMulti.multiType" onChange="changeMulti(this);">
                    <option value=""></option>
                    <option value="1">广告位</option>
                    <option value="2">多经点位</option>
                </select>
            </td>
            <td align="right" class="multFloor" style="display:none">楼层：</td>
            <td class="multFloor" style="display:none">
                <input type="text" id="multiFloor" name="bisMulti.multiFloor"/>
            </td>
            <td align="right" class="multGrade" style="display:none">等级：</td>
            <td class="multGrade" style="display:none">
                <input type="text" id="multiGrade" name="bisMulti.multiGrade"/>
            </td>
            <td colspan="3"></td>
        </tr>
        <tr>
            <td align="right">位置区域：</td>
            <td>
                <input type="text" id="operationArea" name="bisMulti.operationArea"/>
            </td>
            <td colspan="3"></td>
        </tr>
        <tr>
            <td align="right">执行政策：</td>
            <td colspan="3">
                <textarea name="bisMulti.multiPolicy" id="multiPolicy"></textarea>
            </td>
            <td colspan="3"></td>
        </tr>
        <tr>
            <td align="right">备注：</td>
            <td colspan="3"><textarea name="bisMulti.remark" id="multi_remark"></textarea></td>
            <td colspan="3"></td>
        </tr>
    </table>
</div>
<div style="padding-top:10px;">
    <table class="main_content" border="0">
        <col width="120"/>
        <col/>
        <tr>
            <td></td>
            <td>
                <input class="btn_green" type="button" onclick="saveNewFloor();" value="保存"/>
                <input class="btn_red" type="button" onclick="cancelAdd();" value="取消"/>
            </td>
        </tr>
    </table>
</div>
</fieldset>
</div>
<div id="bisProjectFloor" style="padding: 10px 8px 0px;"></div>
<div id="massageTab" align="center" style="color:#6BAD42;font-size:16px;font-weight:bold;padding-top:100px;">
    请选择要搜索的内容！
</div>

</form>
<script type="text/javascript">
    $(function () {
        var floorTypeSplit = $('#floorTypeSplit').val();

        var floorNumSplit = $('#floorNumSplit').val();
        var bisProjectId = $('#bisProjectId').val();

        if (floorTypeSplit != "" && floorNumSplit != "") {
            $('#floorType').val(floorTypeSplit);
            filterFloor(bisProjectId);
            $('#bisFloorId').val(floorNumSplit);
            bisFloorSearch();
        }
        //bisFloorSearch();
        $('input[alt="amount"]').live('keyup', function () {
            clearNoNum(this);
            if ($('.percent').val() > 100) {
                this.value = 0;
            }
        });
    });
    function jumpPage(pageNo) {
        var floorType = $("#floorType").val();
        if (floorType == "3") {
            $("#pageNo").val(pageNo);
            $("#seaFloorForm").attr("action", "${ctx}/bis/bis-project!multiList.action");
            $("#seaFloorForm").ajaxSubmit(function (result) {
                $("#bisProjectFloor").html(result);
            });
            $("#seaFloorForm").attr("action", "${ctx}/bis/bis-project!save.action");
        } else {
            $("#pageNo").val(pageNo);
            $("#seaFloorForm").attr("action", "${ctx}/bis/bis-project!list.action");
            $("#seaFloorForm").ajaxSubmit(function (result) {
                $("#bisProjectFloor").html(result);
            });
            $("#seaFloorForm").attr("action", "${ctx}/bis/bis-project!save.action");
        }
    }
    function jumpPageTo() {
        var index = $("#pageTo").val();
        index = parseInt(index);
        if (index > 0) {
            jumpPage(index);
        }
    }
    function storeValidate(obj) {
        obj.value = obj.value.replace(/[^\a-zA-Z\d\_\-]/g, "");
    }
    function showStatus(dom) {
        if ("2" == $(dom).val()) {
            $(".propRight").show();
        } else {
            $(".propRight").val("").hide();
        }
    }
</script>
</body>
</html>
