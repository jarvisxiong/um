<%@ page contentType="text/html;charset=UTF-8" %>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"  />
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
<div class="title_bar" >
	<div style="font-weight:bold;padding-left:8px;padding-right:8px; font-size:14px;float:left;">商业收费明细</div>
	<div style="width:27px; padding:0 0 0 70px;float:left;">
	 <div id="btnSeniorSearch" class="senior_search" onclick="showSeniorSearch();">&nbsp;</div>
	</div>
	<div style="font-size:12px;width: 37px; height:16px;margin-left: 8px; padding: 0pt; float: left;">项目：</div>
	<div style="width: 100px; margin-top: 4px; padding: 0pt ; float: left; 	">
		<input type="text" id="bisProjectName" name="bisProjectName" value="${bisProjectName}" style="  height: 16px;width:100%; cursor: pointer; font-size: 12px; color: #ff0000;"/>
		<input class="search" type="hidden" id="bisProjectIdFact" name="bisProjectId" value="${bisProjectId}"/>
	</div>
	<div style="font-size:12px;width: 37px; margin-left: 28px; padding: 0pt 0pt 0pt 0px; float: left;">业态：</div>
	<div style="width: 70px; margin-top: 4px;height:16px; padding: 0pt 0pt 0pt 0px; float: left; 	">
			<s:select cssStyle="width:100%;" style="height:19px;" 
			validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapContBigTypeNew()" 
			listKey="key" listValue="value" name="layOutCd" id="layOutCd" 
			onchange="changlayOutDetail1();" required="true"></s:select>
	</div>
            <div style="float:right; height:26px; margin-right:5px; margin-top:6px;">
                <div class="btn_green" onclick="clkFullScreen('${bisProjectId}');">全屏</div>
            </div>
</div>

<div class="bis_search" id="main_search_div"  >
<!-- //应收1-欠费2-实收3-预收4 -->
	<table class="tb_search" style="    margin-left: 5px;">
		<col />
		<tr class="no_advances_dime">
			
			<td align="right" class="flat_layout" style="display:none;" layout="flat" width="40">楼号：</td>
			<td layout="flat" class="flat_layout" style="display:none;" width="70">
				<input style="width:100px;height:16px;"  id="flatBuding" />
			</td>
			<td align="right" id="detailLabel" width="40">租户/商铺：</td>
			<td  width="100"  >
				<input style="width:100px;height:16px;<s:if test="''==shopStoreName||null==shopStoreName">color:#ccc;</s:if><s:else>color:#333;</s:else>" title="" name="shopStoreName" id="layOutCdList_v0" required="true" onfocus="clearInput(this);" value="<s:if test="''==shopStoreName">搜索商家/商铺</s:if><s:else>${shopStoreName}</s:else>"/>
				<select style="width:150px;height:20px;display:none;"  title="" id="layOutCdList_v1" required="true" onClick="selectDetail1();"></select>
			</td>
			<td align="right" width="40">类别：</td>
			<td width="100">
				<s:select onchange="setThisTitle('chargeTypeCd');"  list="@com.hhz.ump.util.DictMapUtil@getMapChargeType()" listKey="key" listValue="value" name="chargeTypeCd" id="chargeTypeCd"></s:select>
			</td>
			<td align="right" width="30">年：</td>
			<td width="60">
				<s:select   list="@com.hhz.ump.util.DictMapUtil@getMapYear()" listKey="key" listValue="value" name="year" id="year" ></s:select>
			</td>
			<td align="right" width="30">月：</td>
			<td width="50">
				<s:select cssStyle="width:100%;" validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapMonth()" listKey="key" listValue="value" name="month" id="month"></s:select>
			</td>
			<td align="right" class="must_dime" must="true" style="display:none" width="70">应收日期：</td>
				
			<td align="right" class="must_dime" id="must_1" must="true" style="display:none" width="80px">
				<input class=input" type="text"
				 onfocus="WdatePicker()"  
				 name="mustInBegin" id="mustInBegin" style="cursor: pointer;width:70px;height:16px;    margin-top: 2px;"/>
		   </td>
			<td align="right" class="must_dime" must="true" style="display:none" width="30">至 &nbsp;  </td>
			<td align="right" class="must_dime" id="must_1" must="true" style="display:none" width="80px">
				
				<input class=input" type="text"  
				onfocus="WdatePicker()"  
				 name="mustInEnd" id="mustInEnd" style="cursor: pointer;width:70px;height:16px;    margin-top: 2px;"/>
			</td>
			<td style="padding-left: 5px;">
				<input id="btn_search" type="button" style="width:65px;margin-right:5px;" class="btn_blue" onclick="submitSearch1();" value="搜索"/>
				<security:authorize ifAnyGranted="A_FACT_INSERT">
				<input id="btn_new_fact" class="fact_dime btn_blue" type="button" style="width:65px; "  onclick="appendFact();" value="新增"/>
				</security:authorize>
			</td>
			
		
		</tr>
		<tr class="advances_dime" >
			<td align="right" id="detailLabel" width="40">租户：</td>
			<td  width="100"  >
				<input class="search" name="tenant4YuS" style="width:100px;height:16px;color:#ccc" title="" id="tenant4YuS" required="true" onfocus="clearInput(this);" value="搜索商家/商铺"/>
			</td>
			<td style="padding-left: 5px;">
				<input id="btn_search" type="button" style="width:65px" class="btn_blue" onclick="toFactYuShou();" value="搜索"/>
				<input id="btn_new_fact" class="btn_blue" type="button" style="width:65px; "  onclick="inputFactYuShou();" value="新增"/>
			</td>
		</tr>
	</table>
</div>
<div class="bis_search" id="main_search_div1" style="height:30px;background:white;border:0px;    margin-bottom: 9px;">
	<table style="width:100%">
		<tr>
			<td colspan="12">
				<ul id="bis_rpt"  style="margin-left: 4px;list-style-type:none;">
					<li class="bis_fact_unclick" value="2" id="must"  >应收明细</li>
					<li class="bis_fact_unclick" value="3" id="overdue"  >欠费明细</li>
					<li class="bis_fact_unclick" value="1" id="fact" >收费历史记录</li>
					<li class="bis_fact_unclick"value="4" id="advances" >预收明细</li>
				</ul>
				<ul class="fact_dime"  style="    list-style-type: none;" id="search_fact">
					<li class="bis_fact_unclick" fact="true" id="" valu='1' >审核通过</li>
					<li class="bis_fact_unclick" fact="true" id=""  valu='0' >等待审核</li>
					<li class="bis_fact_unclick" fact="true" id=""  valu='2' >审核驳回</li>
					<li class="bis_fact_unclick" fact="true" id="factAll"  valu='' style="color:red;">全部</li>
					<li class="bis_fact_unclick" fact="true" id=""  valu='n' style="color:#464646">快速搜索:</li>
				</ul>
				<ul class="advances_dime"  style="    list-style-type: none;" id="search_fact_yu_s">
					<li class="bis_fact_unclick" fact="true" id="" valu='1' >审核通过</li>
					<li class="bis_fact_unclick" fact="true" id=""  valu='0' >等待审核</li>
					<li class="bis_fact_unclick" fact="true" id=""  valu='2' >审核驳回</li>
					<li class="bis_fact_unclick" fact="true" id="factAll"  valu='' style="color:red;">全部</li>
					<li class="bis_fact_unclick" fact="true" id=""  valu='n' style="color:#464646">快速搜索:</li>
				</ul>
				<ul class="must_dime"  style="    list-style-type: none;" id="search_due">
					<li class="bis_fact_unclick" id="dueli"  valu='1' style="color:red">未收齐</li>
					<li class="bis_fact_unclick" id="nodueli"  valu='0' >已收齐</li>
					<li class="bis_fact_unclick" id="allnodueli"  valu='2' >全部</li>
					<li class="bis_fact_unclick" id=""  valu='n' style="color:#000">快速搜索:</li>
				</ul>
				<span style="margin-top:8px;margin-left:20px;float:left ;color:red;display:none;line-height:20px;height:20px;" id="factoptip"></span>	
			</td>
		</tr>
	</table>
</div>
<div class="bis_search" id="notPublic" style="height:30px;background:white;border:0px;    margin-bottom: 9px;display:none">
	<table style="width:100%">
		<tr>
			<td  >
				<%-- 导入--%>
				<form id="importFactForm" enctype="multipart/form-data" method="post" action="${ctx}/bis/bis-fact!importFact.action">
						<input id="importBisProjectId"  name="bisProjectId" type="hidden" />
					<table>
						<tbody><tr>
						<tr>
							<td style="padding-left: 8px; padding-top: 5px;" colspan="3">
								<input type="file" style="line-height: 22px; height: 22px; margin-bottom: 3px;" name="importFact" id="importFact">
							</td>
							<td style="padding-left: 10px;" colspan="6">
								<input type="button" value="导入数据" onclick="importFactFile();" style="width: 65px;" class="btn_blue">
								<input id="btn_new_facts" type="button" value="批量新增" onclick="appendFacts();" style="width: 65px;" class="btn_blue">
							</td>
						</tr>
					</tbody></table>
				
				</form>
			</td>
		</tr>
	</table>
</div>				

<div id="seniorSearchDiv" style="position:absolute; width:650px; height:130px; top:31px; left:8px; display:none; background-color:#fff; border:1px solid #000; padding:10px 20px 10px 0px; z-index:8;">
	<table class="tb_search" cellpadding="0" cellspacing="0">
		<col width="80"/>
		<col width="130"/>
		<col width="80"/>
		<col width="130"/>
		<col width="80"/>
		<col width="130"/>
		
		<tr id="must_fact_senior" class="fact_dime" style="display:none;"> 
			<td align="right">实收日期：</td>
			<td>
				<input class=input" type="text"
				 onfocus="WdatePicker()"  
				 name="factInBegin" id="factInBegin" style="cursor: pointer;"/>
			</td>
			<td align="right">到：</td>
			<td>
				<input class=input" type="text"  
				onfocus="WdatePicker()"  
				 name="factInEnd" id="factInEnd" style="cursor: pointer;"/>
			</td>
			<td  align="right">状态：</td>
			<td>	
				<s:select list="@com.hhz.ump.util.DictMapUtil@getMapBisFactStatus()" listKey="key" listValue="value" name="statusCd" id="statusCd" />
			</td>
		</tr>
		<tr>
			<td align="right">创建人：</td>
			<td>
				<input name="creatorQ" id="creatorQ" />
				<input name="creator" id="creator" type="hidden"/>
			</td>
			<td  align="right">年月从：</td>
			<td>
			<input onfocus="WdatePicker({dateFmt:'yyyy-MM'})"
					name="minMonth" id="minMonth" />
			</td>
			<td  align="right">到：</td>
			<td>
			<input	onfocus="WdatePicker({dateFmt:'yyyy-MM'})"	name="maxMonth" id="maxMonth" />
			</td>
		</tr>
		<tr>
			<td align="right">审核人：</td>
			<td>
				<input name="checkUserCdQ" id="checkUserCdQ" />
				<input name="checkUserCd" id="checkUserCd" type="hidden"/>
			</td>
			<td align="right"><span>实收</span>金额：</td>
			<td>
				<input class=input" type="text" name="minMoney" id="minMoney" style="cursor: pointer;"/>
			</td>
			<td align="right">到：</td>
			<td>
				<input class=input" type="text" name="maxMoney" id="maxMoney" />
			</td>
		</tr>
		<tr>
			<td colspan="6" style="padding-top:10px; text-align:center;">
				<input type="button" class="btn_blue" onClick="submitSearch2();closeSeniorSearch();" value="搜索" />
				<input type="button" class="btn_green" onClick="closeSeniorSearch();" value="关闭" />
			</td>
		</tr>
		
	</table>
</div>

<script type="text/javascript">
isEmpty = function (str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
};

var _MODULE = '${module}';

$(function(){
	
	if($("#bisProjectName").val()=='') {
		$("#bisProjectName").val("--选择项目--");
	}
	$('#bisProjectName').onSelect({muti:false});
	
	
});


function showSeniorSearch() {
	if($("#seniorSearchDiv").css("display")=="none"){
		$("#btnSeniorSearch").addClass("quickSeniorHover");
		if(_MODULE==3) {
			$("#seniorSearchDiv").height(150);
		} else if(_MODULE==4) {
			$("#seniorSearchDiv").height(120);
		}
		$("#seniorSearchDiv").show();
	}else{
		$("#btnSeniorSearch").removeClass("quickSeniorHover");
		$("#seniorSearchDiv").hide();
	}
}

function clkFullScreen() {
	var bisProjectId = $("#bisProjectId").val();
	var url;
	if(isEmpty(bisProjectId)) {
		url = "${ctx}/bis/bis-manage!layout.action?module=";
	} else {
		url = "${ctx}/bis/bis-manage!layout.action?bisProjectId="+$("#bisProjectId").val()+"&module=";
	}
	
	window.open(url+"3");
}
function doExport(){
	if (confirm("导出仅用于核对，确定要导出么？")){
		var bisProjectId = $('#bisProjectId').val();
		var url ="${ctx}/bis/bis-store-coords!exportTemplate.action?bisProjectId="+bisProjectId;
		location.href = url;
	}
}
function closeSeniorSearch() {
	$("#btnSeniorSearch").removeClass("quickSeniorHover");
	$("#seniorSearchDiv").hide();
}

</script>


