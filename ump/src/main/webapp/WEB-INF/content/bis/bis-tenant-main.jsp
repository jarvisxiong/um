<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/global.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/base.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/cost-ctrl.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bis/bis-manage.css"/>
    <style type="text/css">
        <!--
        html{overflow:auto; background: #fff;overflow-x: hidden; }
        .addFirst {
            background-color: #6eb1ce;
            color: #fff;
        }

        .li_store_layoutcd {
            list-style: none outside none;
            text-decoration: underline;
            cursor: pointer;
        }

        area {
            cursor: inherit;
        }

        .cur_ul {
            text-align: center;
            margin: 10px auto 0;
            width: 360px;
            font-size: 14px;
        }

        .cur_ul li {
            float: left;
            list-style: none;
            width: 120px;
        }

        .cur_ul li span {
            height: 15px;
            width: 15px;
            display: block;
            float: left;
        }

        .floorVirUl {
            text-align: left;
            margin: 10px auto 0;
            font-size: 14px;
        }

        .floorVirli_click {
            background-color: #006FB6;
            color: #fff;
            border: 1px solid #006FB6;
        }

        .floorVirli {
            color: #363636;
            border: 1px solid #006FB6;
        }

        .floorVirUl li {
            list-style: none;
            border: 1px solid #ccc;
        }

        .floorVirUl li:hover {
            background-color: #006FB6;
            color: #fff;
        }

        .floorVirli_click, .floorVirUl li {
            float: left;
            height: 22px;
            line-height: 22px;
            padding: 0 8px;
            margin: 4px 2.5px;
            cursor: pointer;
        }

        -->
    </style>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/js/userChoose.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis-project.js"></script>
    <script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bisCont.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/resources/js/jqueryplugin/mapper/mapper.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bisTenant.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
    <script type="text/javascript">

		function changeVirArea(myfloorid){
			//$(this).removeClass('floorVirli_click').addClass('floorVirli');
			//$(this).removeClass('floorVirli').addClass('floorVirli_click');
			toBisTenant($('#floorId').val(), myfloorid);
		}
        //绑定切换逻辑分区事件
        function turnVirArea() {
            $('.floorVirUl li').bind('click', function () {
                $('.floorVirli_click').removeClass('floorVirli_click').addClass('floorVirli');
                $(this).removeClass('floorVirli').addClass('floorVirli_click');
                toBisTenant($('#floorId').val(), $(this).attr('floorid'));
            });
        }
        function toBisTenant(bisFloorId, bisFloorVirId) {
            var url = "${ctx}/bis/bis-tenant!main.action?bisFloorId=" + bisFloorId + "&bisProjectId=" + $("#projectId").val();
            if (bisFloorVirId) {
                url += "&bisFloorVirId=" + bisFloorVirId;
            }
            TB_showMaskLayer("正在切换楼层...");
            self.location = url;
        }
        $().ready(function () {
//	InitStoreData();
//	InitTenantData();
//	now_bisTenantId = $('#bisTenantId').val();
//	InitStoreEvent();
//	setTimeout(function(){PrintTenants();},500);

//	var projectId = $('#bisProjectId').val();
//	var floorId = $('#floorId').val();
//	if(projectId!=null){
//		$('#bisProjectId').val(projectId);
//		filterFloor(projectId);
//	}
//	if(floorId!=null){
//		$('#bisFloorId').val(floorId);
//	}
//	turnVirArea();

//	click_on_return('btn_tenant_search');
    autoTenantHeight();
        });
      function autoTenantHeight(){
   		var endHeight=$("#bisTenantMain").height();//多出来的高度
		$(window.top.document).find("#bodyLoad").height(endHeight+51);
		$(window.top.document).find("#div_left_b").height(endHeight+51);
		//$(window.top.document).find('#bisTenant_frame').contents().find("body").height(endHeight-50);
		$(window.top.document).find('#bisTenant_frame').height(endHeight);
      }	

        //切换视图
        function changeView() {
            if ($("#viewType").val() == 1) {
                $("#viewType").val("0");
                $("#btnChangeView").val("列表视图");
            } else {
                $("#viewType").val("1");
                $("#btnChangeView").val("平面图视图");
            }
            var url = _ctx + "/bis/bis-tenant!changeView.action";
            var data = {
                bisProjectId:$("#projectId").val(),
                bisFloorId:$("#floorId").val(),
                bisTenantId:$("#bisTenantId").val(),
                rentStartDate:$("#rentStartDate").val(),
                rentEndDate:$("#rentEndDate").val(),
                viewType:$("#viewType").val()
            };
            TB_showMaskLayer("正在加载...");
            $.post(url, data, function (result) {
                TB_removeMaskLayer();
                $("#viewPanel").html(result);
            });
        }

        //查看商家历史
        function viewShopHis() {
            var bisTenantId = $('#bisTenantId').val();
            if (isEmpty(bisTenantId)) {
                return;
            }

            ymPrompt.win({
                icoCls:"",
                autoClose:true,
                message:"<div id='shopHisDiv'><img align='absMiddle' src='"
                        + _ctx + "/images/loading.gif'></div>",
                width:690,
                height:500,
                title:"商家历史",
                closeBtn:true,
                afterShow:function () {
                    var url = _ctx + "/bis/bis-tenant!viewShopHis.action";
                    $.post(url, {bisTenantId:bisTenantId}, function (result) {
                        $("#shopHisDiv").html(result);
                    });
                },
                winPos:'c',
                btn:[
                    ["关闭", 'close']
                ]
            });
        }

        //查看商铺历史
        function viewStoreHis() {
            var bisTenantId = $('#bisTenantId').val();
            var bisStoreId = $('#bisStoreId').val();
            if (isEmpty(bisTenantId) && isEmpty(bisStoreId)) {
                return;
            }

            ymPrompt.win({
                icoCls:"",
                autoClose:true,
                message:"<div id='storeHisDiv'><img align='absMiddle' src='"
                        + _ctx + "/images/loading.gif'></div>",
                width:690,
                height:500,
                title:"商铺历史",
                closeBtn:true,
                afterShow:function () {
                    var url = _ctx + "/bis/bis-tenant!viewStoreHis.action";
                    $.post(url, {bisTenantId:bisTenantId, bisStoreIds:bisStoreId}, function (result) {
                        $("#storeHisDiv").html(result);
                    });
                },
                winPos:'c',
                btn:[
                    ["关闭", 'close']
                ]
            });
        }

        //查看商铺历史
        function openShopTab(id) {
            if (isEmpty(id)) {
                id = $('#bisShopId').val();
                if (isEmpty(id)) {
                    return;
                }
            }

            var url = _ctx + "/bis/bis-shop!input.action?id=" + id;
            if (parent.TabUtils == null)
                window.open(url);
            else
                parent.TabUtils.newTab("bis-shop-input", "商家信息", url, true);
        }

    </script>

</head>
<body id="bisTenantMain">
<div class="title_bar" style="width:100%;background-color:#e4e7ec;font-weight: normal;padding-left: 8px;">
    <font style="font-size: 14px;font-weight: bolder; color:#333333; padding-right: 10px; float: left;">租户详细信息</font>
    <button class="blue" type="button" onclick="changeView();" style="float:left;height:22px;line-height: 22px;margin-top: 3px;">列表视图</button>
    <%--<input type="button" id="btnChangeView" class="btn_blue" onclick="changeView();"
           value="列表视图"/>--%>
</div>
<div id="teneBodyDiv" style="margin:10px;">

    <input type="hidden" id="bisTenantId" name="bisTenantId" value="${bisTenantId}"/>
    <input type="hidden" id="statusCd" value="${statusCd}"/>
    <input type="hidden" id="bisShopId" name="bisShopId" value="${bisShopId}"/>
    <input type="hidden" id="rentStartDate" name="rentStartDate" value="${rentStartDate}"/>
    <input type="hidden" id="rentEndDate" name="rentEndDate" value="${rentEndDate}"/>
    <input type="hidden" id="viewType" name="viewType" value="${viewType}"/>

    <div id="viewPanel">
        <%@ include file="bis-tenant-imageView.jsp" %>
    </div>

    <div style="width:870px; height:178px; margin-left: 0px; float:left;">
        <div style="float:left; height:178px;width:180px; border-right: 1px solid #ccc; margin:10px; ">
         <table height="168px" border="0" width="100%">
              <tr height="40px;">
                  <td align="left" class="tdclass" style="font-weight: bold;color: #0167a2;">业主信息
                  </td>
               </tr>
               <tr valign="top" height="45px;">
                  <td>商铺编号：<div id="td_storeNo1" title="" class="ellipsisDiv pd-chill-tip" style="width:150px;"></div></td>
                </tr>
               <tr valign="top">
                 <td>业主名称：<div id="td_owner" title="" class="ellipsisDiv pd-chill-tip" style="width:150px;"></div></td>
               </tr>
         </table>
        </div>
        <div style="float:left; height:178px; border-right: 1px solid #ccc; margin:10px; ">
            <table height="168px" border="0">
                <col width="60px"/>
                <col width="150px"/>
                <tr height="28px;">
                    <td align="left" class="tdclass" style="font-weight: bold;color: #0167a2;padding-left: 8px;">商家信息
                    </td>
                    <td>
                        <input type="button" style="width: 90px;" value="查看商家历史" onclick="viewShopHis();"
                               class="btn_green"/>
                    </td>
                </tr>
                <tr height="28px;">
                    <td nowrap="nowrap" align="left" class="tdclass" style="padding-left: 8px;">商家名称：</td>
                    <td nowrap="nowrap" id="td_nameCn" class="tdclass link_bis" onclick="openShopTab();"
                        title="查看商家"></td>
                </tr>
                <tr height="28px;">
                    <td nowrap="nowrap" align="left" class="tdclass" style="padding-left: 8px;">产权性质：</td>
                    <td nowrap="nowrap" id="td_equityNature" class="tdclass"></td>
                </tr>
                <tr height="28px;">
                    <td nowrap="nowrap" align="left" class="tdclass" style="padding-left: 8px;">经营性质：</td>
                    <td nowrap="nowrap" id="td_manageCd" class="tdclass"></td>
                </tr>
                <tr height="28px;">
                    <td nowrap="nowrap" align="left" class="tdclass" style="padding-left: 8px;">业态：</td>
                    <td nowrap="nowrap" id="td_layoutCd" class="tdclass"></td>
                </tr>
                <tr height="28px;">
                    <td nowrap="nowrap" align="left" class="tdclass" style="padding-left: 8px;">租期：</td>
                    <td nowrap="nowrap" id="td_rentDate" class="tdclass"></td>
                </tr>
            </table>
        </div>
        <div style="float:left; height:150px; width:300px; border: 0px solid #ccc; margin:10px;">
            <input type="hidden" id="bisStoreId"/>
            <table height="140px" border="0" width="350px; overflow:hidden;">
                <col width="60px"/>
                <col width="150px"/>
                <tr height="28px;">
                    <td align="left" class="tdclass" style="font-weight: bold;color: #0167a2;padding-left: 8px;">商铺信息
                    </td>
                    <td>
                        <input type="button" style="width: 90px;" value="查看商铺历史" onclick="viewStoreHis();"
                               class="btn_green"/>
                    </td>
                </tr>
                <tr height="28px;">
                    <td nowrap="nowrap" align="left" class="tdclass" style="padding-left: 8px;">商铺编号：</td>
                    <td nowrap="nowrap" class="tdclass" align="left">
                        <div id="td_storeNo" title="" class="ellipsisDiv pd-chill-tip" style="width:230px;"></div>
                    </td>
                </tr>
                <tr height="28px;">
                    <td nowrap="nowrap" align="left" class="tdclass" style="padding-left: 8px;">产权面积：</td>
                    <td nowrap="nowrap" id="td_powerSquare" class="tdclass"></td>
                </tr>
                <tr height="28px;">
                    <td nowrap="nowrap" align="left" class="tdclass" style="padding-left: 8px;">实际租赁面积：</td>
                    <td nowrap="nowrap" id="td_factSquare" class="tdclass"></td>
                </tr>
                <tr height="28px;">
                    <td nowrap="nowrap" align="left" class="tdclass" style="padding-left: 8px;">规划业态：</td>
                    <td nowrap="nowrap" id="" class="tdclass">
                        <div id="td_storelayoutCds" style="width:230px;"></div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div style="height:8px;"></div>
    <div style="border: 1px solid #cccccc;display:none;clear:both" id="feeOverDuePanel">
        <div class="title_bar"
             style="background-color:#e4e7ec;font-weight:bolder ; padding-left: 8px;font-size: 12px;color: #464646;">
            <span style="padding-left: 8px;">欠费列表</span>
        </div>
        <div id="feeOverDue"></div>
    </div>
    <div style="height:8px;"></div>

    <div id="shopWidthDivs" style="border: 1px solid #cccccc;clear:both">
        <div class="title_bar" style="background-color:#e4e7ec;font-weight: bolder; font-size: 12px;color: #464646; ">
            <span style="padding-left: 8px;">商家收支详情</span>
            年份：<select style="width: 55px;" name="factYear" id="factYear" onchange="bisTenantFeeSec();">
            <option value="2006" <s:if test="nowYear==2006">selected</s:if>>2006</option>
            <option value="2007" <s:if test="nowYear==2007">selected</s:if>>2007</option>
            <option value="2008" <s:if test="nowYear==2008">selected</s:if>>2008</option>
            <option value="2009" <s:if test="nowYear==2009">selected</s:if>>2009</option>
            <option value="2010" <s:if test="nowYear==2010">selected</s:if>>2010</option>
            <option value="2011" <s:if test="nowYear==2011">selected</s:if>>2011</option>
            <option value="2012" <s:if test="nowYear==2012">selected</s:if>>2012</option>
            <option value="2013" <s:if test="nowYear==2013">selected</s:if>>2013</option>
            <option value="2014" <s:if test="nowYear==2014">selected</s:if>>2014</option>
            <option value="2015" <s:if test="nowYear==2015">selected</s:if>>2015</option>
            <option value="2016" <s:if test="nowYear==2016">selected</s:if>>2016</option>
            <option value="2017" <s:if test="nowYear==2017">selected</s:if>>2017</option>
            <option value="2018" <s:if test="nowYear==2018">selected</s:if>>2018</option>
            <option value="2019" <s:if test="nowYear==2019">selected</s:if>>2019</option>
            <option value="2020" <s:if test="nowYear==2020">selected</s:if>>2020</option>
        </select>
            <input type="button" style="width: 90px;" value="查看费用明细" onclick="clickFeeDetail();" class="btn_green"/>
        </div>
    </div>
    <div id="bisTenantFee" style="position:relative; width:100%; overflow:auto; height:230px;margin-top: -2px;">

    </div>
    <div style="height:8px;"></div>
    <div style="border: 1px solid #cccccc;">
        <div class="title_bar"
             style="background-color:#e4e7ec;font-weight:bold ; padding-left: 8px;font-size: 12px;color: #464646;">
            合同附件<input style="margin-top: 4px;margin-left: 8px;" type="button" value="新增" class="btn_blue"
                       onclick="addBisCont('${bisTenantId}');"/>
        </div>
        <div id="tenantContsPage"></div>
    </div>
    <div style="height:30px;"></div>
</div>

</body>
</html>
