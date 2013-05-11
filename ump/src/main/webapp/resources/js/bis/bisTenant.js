var AllTenants = new Array();		//所有的租户对象列表
var AllStores = new Array();		//所有的商铺对象列表
/*
 * 状态：未出租
 */
var STATUS_NONE = "0";				
/*
 * 状态：出租
 */
var STATUS_YELLOW = "1";			
/*
 * 状态：欠费
 */
var STATUS_RED = "2";				
/*
 * 状态：选中
 */
var STATUS_BLUE = "3";				
var now_bisTenantId = "";			//当前查看的租户的Id
var now_bisStoreId = "";			//当前查看的商铺的Id(未出租)
var if_clicking_store = false;		//是否正在点击搜索平面图的商铺
var YELLOW_COLOR_VM = '#f9df88';
var YELLOW_COLOR_CV = '249,223,136';
var RED_COLOR_VM = '#ac2727';
var RED_COLOR_CV = '172,39,39';
var BLUE_COLOR_VM = '#006fb6';
var BLUE_COLOR_CV = '0,111,182';
var DEF_COLOR_VM = '#eee';
var DEF_COLOR_CV = '240,240,240';
var YELLOW_COLOR ;
var RED_COLOR ;
var BLUE_COLOR ;
var DEF_COLOR ;
var op='0.8';
if(isVM){
	//ie
	YELLOW_COLOR = YELLOW_COLOR_VM;
	RED_COLOR = RED_COLOR_VM;
	BLUE_COLOR = BLUE_COLOR_VM;
	DEF_COLOR = DEF_COLOR_VM;
}else if(isCV){
	YELLOW_COLOR = YELLOW_COLOR_CV;
	RED_COLOR = RED_COLOR_CV;
	BLUE_COLOR = BLUE_COLOR_CV;
	DEF_COLOR = DEF_COLOR_CV;
}
//初始化所有的租户信息，显示图片
function PrintTenants(){
	var status_str;
	for(var i=0;i<AllStores.length;i++){
		var myStore = AllStores[i];
		if(false==myStore.ifHasTenant){
			status_str = getStatusStr(myStore.statusCd);
			initAreaOver($("#"+myStore.storeNo).get(0),'gmipam_0_image',status_str,status_str,'0.8',1,0,0);
		}
	}
	for(var i=0;i<AllTenants.length;i++){
		try{
			var myTenant = AllTenants[i];
			status_str = getStatusStr(myTenant.statusCd);
			for(var j=0;j<myTenant.arrStores.length;j++){
				var myStore = myTenant.arrStores[j];
				if(null!=myStore){
					initAreaOver($("#"+myStore.storeNo).get(0),'gmipam_0_image',status_str,status_str,'0.8',1,0,0);
					//setAreaOver($("#"+myStore.storeNo).get(0),'gmipam_0_image','255,84,0','255,84,0','1',1,0,0);	//red:172,39,39  ye:249,223,136 blue:0,111,182
				}
			}
		}catch(e){
			//alert("PrintTenants["+i+"]"+e);
		}
	}
//	printByClickTenant(now_bisTenantId);
	
	if(isVM){
		canvas=document.getElementById('gmipam_0_image');
		canvas.innerHTML+=iniHtml;
	}
	try{$("#bigPic_mask").hide();}catch(e){}
}
//点击某租户，改变该租户的在平面图中的显示
function printByClickTenant(bisTenantId){
	if(null!=bisTenantId && now_bisTenantId!=bisTenantId){
		for(var i=0;i<AllTenants.length;i++){
			try{
				var myTenant = AllTenants[i];
				if(bisTenantId==myTenant.tenantId){
					setAreaOut('gmipam_0_canvas_select','gmipam_0_canvas_select');
					for(var j=0;j<myTenant.arrStores.length;j++){
						var myStore = myTenant.arrStores[j];
						if(null!=myStore){
							setAreaOver($("#"+myStore.storeNo).get(0),'gmipam_0_canvas_select',BLUE_COLOR,BLUE_COLOR,'0.8',1,0,0);
						}
					}
				}
			}catch(e){}
		}
		now_bisTenantId = bisTenantId;
	}
}
//点击某商铺，改变改商铺在平面图中的显示
function printByClickStore(bisStoreId){
	if(null!=bisStoreId && bisStoreId!=now_bisStoreId){
		for(var i=0;i<AllStores.length;i++){
			try{
				var myStore = AllStores[i];
				if(bisStoreId==myStore.storeId){
					setAreaOut('gmipam_0_canvas_select','gmipam_0_canvas_select');
					setAreaOver($("#"+myStore.storeNo).get(0),'gmipam_0_canvas_select',BLUE_COLOR,BLUE_COLOR,'0.8',1,0,0);
				}
			}catch(e){}
		}
		now_bisTenantId = bisTenantId;
	}
}
//初始化所有的商铺的title事件
function InitStoreEvent(){
	for(var i=0;i<AllStores.length;i++){
		try{
			var myStore = AllStores[i];
			var myTenant = getTenantByStoreNo(myStore.storeNo);
			if(myTenant!=null){
                $("#"+myStore.storeNo).attr("title",$("#"+myStore.storeNo).attr("title")+"<br/>所属商家："+myTenant.tenantName);
			}
            $("#"+myStore.storeNo).attr("title",$("#"+myStore.storeNo).attr("title")+"<br/>所属性质："+(myStore.equityNature==1?"自留":"出售"));
		}catch(e){
			//alert("InitStoreEvent["+i+"]"+e);
			//break;
		}
	}
}
//从statusCd获得图片的后缀,没用
function getStatusStr(fromStr){
	var returnStr = "";
	if(STATUS_YELLOW==fromStr){
		//returnStr = "_yellow";
		returnStr = YELLOW_COLOR;
	}else if(STATUS_RED==fromStr){
		//returnStr = "_red";
		returnStr = RED_COLOR;
	}else if(STATUS_BLUE==fromStr){
		//returnStr = "_blue";
		returnStr = BLUE_COLOR;
	}else if(STATUS_NONE==fromStr){
		//returnStr = "_blue";
		returnStr = DEF_COLOR;
		op='0.8';
	}
	return returnStr;
}
//由单个商铺号得到商铺对象
function getStoreByNo(fromNo){
	var returnStore;
	for(var i=0;i<AllStores.length;i++){
		var tempStore = AllStores[i];
		if(tempStore.storeNo==fromNo){
			returnStore = tempStore;
			break;
		}
	}
	return returnStore;
}
//由商铺号的字符串得到商铺对象数组
function getStoresByNos(fromNos){
	var returnStores = new Array();
	var arrNos = fromNos.split(",");
	for(var i=0;null!=arrNos&&i<arrNos.length;i++){
		if(""!=arrNos[i]){
			returnStores.push(getStoreByNo(arrNos[i]));
		}
	}
	return returnStores;
}
//由商铺号的字符串得到租户对象
function getTenantByStoreNo(fromNo){
	var returnTenant;
	var myStore;
	for(var i=0;i<AllStores.length;i++){
		var tempStore = AllStores[i];
		if(tempStore.storeNo==fromNo){
			myStore = tempStore;
			break;
		}
	}
	for(var i=0;i<AllTenants.length;i++){
		var tempTenant = AllTenants[i];
		for(var j=0;j<tempTenant.arrStores.length;j++){
			if(tempTenant.arrStores[j]==myStore){
				returnTenant = tempTenant;
				break;
			}
		}
		if(null!=returnTenant){
			break;
		}
	}
	return returnTenant;
}
//租户对象
function Tenant(tenantId,tenantName,storeNos,statusCd,layoutCd,square){
	this.tenantId = tenantId;
	this.tenantName = tenantName;
	this.storeNos = storeNos;
	this.statusCd = statusCd;
	this.layoutCd = layoutCd;
	try{
		this.arrStores = getStoresByNos(storeNos);
	}catch(e){}
	for(var i=0;i<this.arrStores.length;i++){
		var myStore = (this.arrStores)[i];
		if(null!=myStore){
			myStore.ifHasTenant = true;
		}
	}
	this.square = square;
}
//商铺对象
function Store(storeId,storeNo,square,equityNature1,ifPractice1){
	this.storeId = storeId;
	this.storeNo = storeNo;
	this.square = square;
	//如果商铺产权性质为出售且已开业：则状态为1 ：标示一出租
	if(ifPractice1=='1'&&equityNature1=="2"){
		this.statusCd = '1';
	}	else{
		this.statusCd = '0';
	}
	this.ifHasTenant = false;
    this.equityNature=equityNature1;
}

//////////////////////////////////////////////////////////////////////////
//点击某商铺，转换成点击某租户事件
function clickStore(myStoreId){
	$("#bigPic_mask").show();
	var myTenant = getTenantByStoreNo(myStoreId);
	if(null!=myTenant){
		selShopStore(myTenant.tenantId,myTenant.statusCd);
		$('#bisStoreId').val('');
		$("#bigPic_mask").hide();
	}else{
		//如果没有租户，就显示商铺信息
		$("#"+myStoreId).mouseover();
		var tempStore;
		for(var i=0;i<AllStores.length;i++){
			var myStore = AllStores[i];
			if(myStoreId==myStore.storeNo){
				tempStore = myStore;
				setAreaOut('gmipam_0_canvas_select','gmipam_0_canvas_select');
				setAreaOver($("#"+myStore.storeNo).get(0),'gmipam_0_canvas_select',BLUE_COLOR,BLUE_COLOR,'0.8',1,0,0);
				break;
			}
		}
		$("#"+myStoreId).attr("title","商铺号："+myStoreId+"<br/>面积："+tempStore.square+"m²");
		$('#bisStoreId').val(myStore.storeId);
		$('#bisTenantId').val('');
		getStoreInfo(tempStore.storeId,tempStore.storeNo,tempStore.square,tempStore.powerSquare);
		$("#bigPic_mask").hide();
		$("#feeOverDuePanel").hide();
		
	}
}
//搜索商铺信息
function getStoreInfo(storeId,storeNo,square,powerSquare){
	var url = _ctx+'/bis/bis-tenant!feeSearch.action';
	$.post(url,{bisStoreIds:storeId},function(result){
		$("#bisTenantFee").html(result);
		if_clicking_store = false;
		$("#td_storeNo").html(storeNo);
		$("#td_storeNo1").html(storeNo);
		$("#td_powerSquare").html(square+"m²");
		loadTenantConts();
	});
}
//查看费用明细
function clickFeeDetail(){
	var bisTenantId = $('#bisTenantId').val();
	if(bisTenantId==""){
		alert("请选择租户");
		return false;
	}
	var bisProjectId=$('#projectId').val();
	var factYear=$('#factYear').val();
	var tenantName =  $('#td_storeNo').html();
	//var url=_ctx+"/bis/bis-manage!layout.action?module=3"+"&bisTenantId="+bisTenantId+"&bisProjectId="+bisProjectId+"&factYear="+factYear+'&currDetailName='+tenantName+'&dimension=3';
	//parent.TabUtils.newTab("bis-manage-layout","费用明细",url,true);
	//跳转至收费明细页面：欠费维度
	var url = _ctx+"/bis/bis-manage!layout.action?ifFromReport=true&module=3&bisTenantId="+bisTenantId+'&currDetailName='+tenantName+'&dimension=3'+'&bisProjectId='+bisProjectId;
	if(null!=parent.TabUtils){
		parent.TabUtils.newTab("bis-manage-layout","费用明细",url,true);
	}else{
		window.open(url);
	}
}

//转换某租户
function selShopStore(bisTenantId,statusCd){
	if(bisTenantId=="" || true==if_clicking_store) {
		return false;
	}
	if($("#viewType").val()==1) {
		$("#tbConItem tr").removeClass("tr_tenant_click");
		$("#main_"+bisTenantId).addClass("tr_tenant_click");
	}
    $('#bisTenantId').val(bisTenantId);
    $('#statusCd').val(statusCd);
    //加载收费明细
	bisTenantFeeSec();
	//加载欠费列表
	//LoadFeeOverDue();
	//加载合同
	loadTenantConts(bisTenantId);
	if($("#viewType").val()==0||$("#viewType").val()=='') {
		printByClickTenant(bisTenantId);
	}
}
/**
 * 解析该租户产生年份
 */
function selyearFilter(){
	var factYear=document.getElementById('factYear');
	factYear.options.length=0;
	var beginTime =$('#rentStartDate').val();
	var endTime=$('#rentEndDate').val();
	var beginYear=beginTime.substr(0,4);
	var endYear=endTime.substr(0,4);
	var length = endYear-beginYear+1;
	for(i=0;i<length;i++ ){
		var option = document.createElement("option");
		option.text=beginYear-0+i;
		option.value=beginYear-0+i;
		factYear.options[factYear.options.length] =option;
	}
}
/**
 * 租户费用类型
 */
function bisTenantFeeSec(){
	if_clicking_store = true;
	var factYear=$("#factYear").val();
	var bisTenantId=$("#bisTenantId").val();
	$.post(_ctx+"/bis/bis-tenant!feeSearch.action",
			{
				bisTenantId:bisTenantId,
				factYear:factYear
		    },
			function(result) {
				if (result) {
					$("#bisTenantFee").html(result);
					if_clicking_store = false;
				}
			}
		);
	
}
/**
 * 欠费列表  feeOverDue
 */
function LoadFeeOverDue(){
	var factYear=$("#factYear").val();
	var bisTenantId=$("#bisTenantId").val();
	var statusCd=$("#statusCd").val();
	if(statusCd=='2'){
		$.post(_ctx+"/bis/bis-tenant!feeOverDue.action",
				{
			bisTenantId:bisTenantId,
			factYear:factYear
				},
				function(result) {
					if (result) {
						$("#feeOverDue").html(result);
						$("#feeOverDuePanel").show();
					}
				}
		);
	} {
		$("#feeOverDuePanel").hide();
	}
	
}
/**
 * 租户选择列表
 */
function bisTenantSearch(){
	$("#bigPic_mask").show();
	var storeShopNo = $('#storeShopNo').val();
	if(null==storeShopNo || ""==storeShopNo){
		return;
	}
	
	//如果直接输入已有的商铺号，就直接搜索了
	for(var i=0;i<AllStores.length;i++){
		var myStore = AllStores[i];
		if(storeShopNo==myStore.storeNo){
			clickStore(storeShopNo);
			return;
		}
	}
	
	var bisProjectId = $('#projectId').val();
	var bisFloorId = $('#floorId').val();

	var url = _ctx+"/bis/bis-tenant!storeShopQuickSe.action";
	$.post(url,{storeShopNo:storeShopNo,bisFloorId:bisFloorId,bisProjectId:bisProjectId}, function(result) {
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
				title : "商家商铺列表",
				closeBtn:true,
				afterShow : function() {
					$("#storeShopQuickSe").html(result);
					$("#bigPic_mask").hide();
					$('.mainTr').bind('click',function(){
						$('.mainTr').removeClass('addFirst');
						$(this).addClass('addFirst');
						selShopStore($(this).id);
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

//按回车直接快速搜索
function onkeypress_storeShopNo(e){
	var keyCode;
	if($.browser.msie){
		keyCode = event.keyCode;
	}else{
		keyCode = e.which;
	}
	if(keyCode==13){
		bisTenantSearch();
	}
}
//按回车直接快速搜索
function onfocus_storeShopNo(){
	$("#storeShopNo").val("");
	$("#storeShopNo").css("color","#000");
	$("#storeShopNo").select();
}
//按回车直接快速搜索
function onfocus_input(_this){
	$(_this).val("");
	$(_this).css("color","#000");
	$(_this).select();
}
function buildingFloorChange(){
	TB_showMaskLayer("正在切换楼层...");
	var toBisFloorId = $("#selectBuildingFloor option:selected'").val();
	self.location = "bis-tenant!main.action?bisFloorId="+toBisFloorId+"&bisProjectId="+$('#projectId').val();
}
