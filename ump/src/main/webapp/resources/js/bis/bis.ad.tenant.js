var AllAds = new Array();//所有广告图
var DEF_COLOR_AD ;
var DEF_COLOR_VM = '#a4c45f';
var DEF_COLOR_CV = '20,134,23';
var op4ad='1';
var now_multiNam;
var if_clicking_ad = false;
if(isVM){
	//ie
	YELLOW_COLOR = YELLOW_COLOR_VM;
	RED_COLOR = RED_COLOR_VM;
	BLUE_COLOR = BLUE_COLOR_VM;
	DEF_COLOR_AD = DEF_COLOR_VM;
}else if(isCV){
	YELLOW_COLOR = YELLOW_COLOR_CV;
	RED_COLOR = RED_COLOR_CV;
	BLUE_COLOR = BLUE_COLOR_CV;
	DEF_COLOR_AD = DEF_COLOR_CV;
}
//商铺对象
function Ad(multiId,multiName,square){
	this.multiId = multiId;
	this.multiName = multiName;
	this.square = square;
}
//////////////////////////////////////////////////////////////////////////
//初始化所有的广告信息，显示图片
function PrintAds(){
	for(var i=0;i<AllAds.length;i++){
		var myMulti = AllAds[i];
		var obj =$("#"+myMulti.multiName).get(0);
		if(obj)
		setAreaOver($("#"+myMulti.multiName).get(0),'gmipam_0_image',DEF_COLOR_AD,DEF_COLOR_AD,0.5,1,0,0);
	}
	
	
	//try{$("#bigPic_mask").hide();}catch(e){}
}
//初始化所有的广告的title事件
function InitAdEvent(){
	for(var i=0;i<AllAds.length;i++){
		try{
			var myMulti = AllAds[i];
			$("#"+myMulti.multiName).attr("title",$("#"+myMulti.multiName).attr("title")+"<br/>承租方：");
		}catch(e){
			//alert("InitStoreEvent["+i+"]"+e);
			//break;
		}
	}
}
//按回车直接快速搜索
function onkeypress_adNo(e){
	var keyCode;
	if($.browser.msie){
		keyCode = event.keyCode;
	}else{
		keyCode = e.which;
	}
	if(keyCode==13){
		bisAdSearch();
	}
}
/**
 * 广告位选择列表
 */
function bisAdSearch(){
	$("#bigPic_mask").show();
	var adNo = $('#adNo').val();
	if(null==adNo || ""==adNo){
		return;
	}
	
	//如果直接输入已有的商铺号，就直接搜索了
	for(var i=0;i<AllAds.length;i++){
		var myMulti = AllAds[i];
		if(adNo==myMulti.multiName){
			clickAd(adNo,multiId);
			return;
		}
	}
	
	var bisProjectId = $('#projectId').val();
	var bisFloorId = $('#floorId').val();

	var url = _ctx+"/bis/bis-tenant!quickAd.action";
	$.post(url,{adNo:adNo,bisFloorId:bisFloorId,bisProjectId:bisProjectId}, function(result) {
		if(null!=result && result.length<51){
			selShopStore(result);
			$("#bigPic_mask").hide();
		}else{
			ymPrompt.confirmInfo( {
				icoCls : "",
				autoClose:false,
				message : "<div id='storeShopQuickSe'><img align='absMiddle' src='"
					+ _ctx + "/images/loading.gif'></div>",
				width : 280,
				height : 325,
				title : "广告位列表",
				closeBtn:true,
				afterShow : function() {
					$("#storeShopQuickSe").html(result);
					$("#bigPic_mask").hide();
					$('.mainTr').bind('click',function(){
						$('.mainTr').removeClass('addFirst');
						$(this).addClass('addFirst');
						loadAdImage($(this).attr('multiName'),$(this).attr('floorId'));
					});
				},
				handler : function(btn){
					if(btn=='ok'){
//							$('#storeDetaForm').ajaxSubmit(function(result) { 
//								bisFloorSearch();
//							});
					}
//						doInput(bigTypeId, smallTypeId);
//					}
					ymPrompt.close();
				},
				btn:[["确定",'ok'],["取消",'cancel']]
			});
		}
	});
//	$('#tenantForm').submit();
	
}
//载入广告位平面图 首页
function loadAdImage(multiName,bisFloorId){
	var curBisFloorId = $('#bisFloorId').val();
	if(bisFloorId==curBisFloorId){
		selectMulti(multiName);
	}else{
		var bisProjectId = $('#bisProjectId').val();
		var url = _ctx+'/bis/bis-tenant!loadAdMain.action';
		TB_showMaskLayer("正在加载...");
		$.post(url,{bisProjectId:bisProjectId,bisFloorId:bisFloorId,adNo:multiName},function(result){
			TB_removeMaskLayer();
			$('#adPanel').html(result);
		});
	}
}
//点击某广告，转换成点击某广告事件
function clickAd(multiName,multiId){
	$("#bigPic_mask").show();
	if(null!=multiName){
		selectMulti(multiName,multiId);
		$("#bigPic_mask").hide();
	}else{
		//如果没有租户，就显示多经信息
		$("#"+multiName).mouseover();
		var tempMulti;
		for(var i=0;i<AllStores.length;i++){
			var myMulti = AllMulti[i];
			if(multiName==myMulti.multiName){
				tempMulti = myMulti;
				setAreaOut('gmipam_0_canvas_select','gmipam_0_canvas_select');
				setAreaOver($("#"+myMulti.multiName).get(0),'gmipam_0_canvas_select',BLUE_COLOR,BLUE_COLOR,'0.8',1,0,0);
				break;
			}
		}
		$("#"+multiName).attr("title","广告编号："+myMultid+"<br/>面积："+tempMulti.square+"m²");
		
		getMultiInfo(tempMulti.multiId,tempStore.multiName,tempStore.square,tempStore.powerSquare);
		$("#bigPic_mask").hide();
		
	}
}
//搜索广告信息
function getMultiInfo(multiId,renterName,square){
	if_clicking_ad = true;
	var url = _ctx+'/bis/bis-tenant!feeSearch.action';
	$.post(url,{bisMultiIds:multiId},function(result){
		$("#bisTenantFee").html(result);
		if_clicking_ad = false;
		$("#td_storeNo").html(multiName);
		$("#td_powerSquare").html(square+"m²");
		loadTenantConts();
	});
}
//转换某租户
function selectMulti(multiName,multiId){
	if(multiName=="" || true==if_clicking_ad) {
		return false;
	}
    $('#bisMultiId').val(multiId);
    loadMultiFeeSec();
    loadMultiDetail(multiId);
	loadMultiConts(multiId);
    printByClickMulti(multiName);
}
//点击某租户，改变该租户的在平面图中的显示
function printByClickMulti(multiName){
	setAreaOut('gmipam_0_canvas_select','gmipam_0_canvas_select');
	setAreaOver($("#"+multiName).get(0),'gmipam_0_canvas_select',BLUE_COLOR,BLUE_COLOR,'0.8',1,0,0);
	now_multiNam = multiName;
}
function loadMultiDetail(multiId){
	var url = _ctx+'/bis/bis-tenant!loadMulti.action';
	$.post(url,{bisMultiId:multiId},function(result){
		$('#adDetailPanel').html(result);
	});
}
/**
 * 多经费用类型
 */
function loadMultiFeeSec(){
	if_clicking_ad = true;
	var factYear=$("#factYear").val();
	var bisMultiId=$("#bisMultiId").val();
	$.post(_ctx+"/bis/bis-tenant!adFeeSearch.action",
			{
				bisMultiId:bisMultiId,
				factYear:factYear
		    },
			function(result) {
				if (result) {
					if_clicking_ad = false;
					$("#bisAdFee").html(result);
					//$("#tenantFeeDiv").css("width",Number($(document).width()-116)+"px");
					//$("#tenantFeeDiv").css("scrollLeft","500px");
				}
			}
		);
	
}
/**
 * 搜索多经下的合同
 */
function loadMultiConts(bisMultiId) {
	//商铺未签合同，清空合同列表
	//if(isEmpty(bisTenantId)) {		return;	}
	TB_showMaskLayer("正在搜索...");
	$.post(_ctx+"/bis/bis-cont!loadMultiConts.action", {bisMultiId:bisMultiId}, function(result) {
		$("#tenantContsPage").html(result);
		try{
			TB_removeMaskLayer();
		}catch(e){}
		try{
			//$("#result_table").css("width",Number($(document).width()-32)+"px");
		}catch(e){}
	});
}
//绑定切换广告图事件
function turnGGArea(){
	$('#floorGGUl li').bind('click',function(){
		//$(this).addClass('floorVirli').siblings().removeClass('floorVirli_click');
		var bisProjectId = $('#bisProjectId').val();
		var bisFloorId = $(this).attr('floorid');
		loadGGImage(bisProjectId,bisFloorId);
	});
}
function onchangGG(){
	loadGGImage($('#bisProjectId').val(),$('#bisFloorIdgg').val());
}
function loadGGImage(bisProjectId,bisFloorId){
	var url = _ctx+'/bis/bis-tenant!loadAdImage.action';
	TB_showMaskLayer("正在加载...");
	$.post(url,{bisProjectId:bisProjectId,bisFloorId:bisFloorId},function(result){
		TB_removeMaskLayer();
		$('#adPanel').html(result);
	});
}
//查看费用明细
function clickGGFeeDetail(){
	var bisMultiId = $('#bisMultiId').val();
	if(bisMultiId==""){
		alert("请选择广告位");
		return false;
	}
	var bisProjectId=$('#bisProjectId').val();
	var factYear=$('#factYear').val();
	var multiName =  $('#td_multiName').html();
	var url=_ctx+"/bis/bis-fact!list.action?bisMultiId="+bisMultiId+"&bisProjectId="+bisProjectId+"&factYear="+factYear+'&currDetailName='+tenantName+'&dimension=1'+'&layOutCd=3';
	if(null!=parent.TabUtils){
		parent.TabUtils.newTab("bis-manage-layout","费用明细",url,true);
	}else{
		window.open(url);
	}
}