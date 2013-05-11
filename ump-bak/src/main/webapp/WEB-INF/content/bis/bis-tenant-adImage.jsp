<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<input type="hidden" id="bisProjectId" name="bisProjectId" value="${bisProjectId}"/>
<input type="hidden" id="bisFloorId" name="bisFloorId" value="${bisFloorId}"/>
<div style="height:8px;"></div>
<div style="border: 0px solid ;">
	<div style="padding-left: 8px; height: 30px; line-height:30px; border: 1px solid #eeeeee;font-size: 14px">
		<span class="color_red" style="padding-left: 10px;">正在查看：</span>广告位信息&nbsp;&nbsp;>&nbsp;&nbsp;${bisProjectName}&nbsp;&nbsp;&nbsp;&nbsp;
		<s:select  style="height:19px;" 
			validate="required" list="mapSubFloor" 
			listKey="key" listValue="value" name="bisFloorId" id="bisFloorIdgg" 
			onchange="onchangGG();" ></s:select>
	
		&nbsp;&nbsp;<s:if test="adNo!=''"><font color="#0693e3">${adNo}</font></s:if>
		<input type="text" style="width:120px; height:22px; color:#999;" id="adNo" onkeypress="onkeypress_adNo(event)" onfocus="onfocus_input(this)" value="搜索广告位，按回车快速搜索"/>
		<input type="button" id="btn_tenant_search" class="btn_blue" style="width:65px;" onclick="bisAdSearch();" value="快速搜索" />
	</div>
</div>

<s:if test="floorVirList.size() > 0" >
<div style="height:30px;" >
	<ul class="floorVirUl" id="floorGGUl">
	<s:iterator value="floorVirList">
		<li floorid="${bisFloorId }"  
		<s:if test="bisFloor.bisFloorId == bisFloorId">class="floorVirli_click"</s:if>
		<s:else>class="floorVirli"</s:else>
		
		>${buildingNum }</li>				
	</s:iterator>
	</ul>
</div>
</s:if>
<div style="height:8px;"></div>
<div style="border: 0px solid #cccccc;">
	<table style="background-color: #ffffff; border: 0px solid #ccc;">
	    <tr>
		    <td>
		    	<div>
					<img class="mapper noborder iradius16 iopacity80 icolor iborderred" src="${ctx}/resources/images/bis/${floorBigPicUrl}" usemap="#map_of_germany" alt="" style="cursor:crosshair;"/>
					<map name="map_of_germany" id="map_of_germany">
						<s:iterator value="listBisMultis">
						<area class="pd-chill-tip" id="${multiName}" title="广告编号：${multiName}<br/>面积：${square}m²" onclick="clickAd('${multiName}','${bisMultiId }');" shape="poly" coords="${coords}"/>
						</s:iterator>
					</map>
				</div>
		    </td>
		</tr>
	</table>
</div>
<div style="width:770px;  height:30px;  border-bottom: 1px solid #8c8f94;">
	<ul class="cur_ul">
	<%--	<li><span style="background-color:#ac2727"></span>欠费</li>
		<li><span style="background-color:#f9df88" ></span>已出租</li>
		 --%><li><span  style="background-color:#006fb6"></span>当前选中</li>
	</ul>
</div>



<script type="text/javascript">
isEmpty = function (str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
};
//初始化所有的广告
function InitAdData(){
	AllAds = new Array();
	<s:iterator value="listBisMultis">
		AllAds.push(new Ad("${bisMultiId}","${multiName}","${square}"));
	</s:iterator>
}

$().ready(function(){
	try{$("#bigPic_mask").hide();}catch(e){}
	InitAdData();
	setTimeout(function(){addMapper();},300);
	InitAdEvent();
	setTimeout(function(){PrintAds();},1000);
	var projectId = $('#bisProjectId').val();
	if(projectId!=null){
		$('#bisProjectId').val(projectId);
	}
	if(''!='${adNo}'){
		selectMulti('${adNo}');
	}
	turnGGArea();
});
</script>
