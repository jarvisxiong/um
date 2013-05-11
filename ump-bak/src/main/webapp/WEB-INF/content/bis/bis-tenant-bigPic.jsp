<%@ page contentType="text/html;charset=UTF-8" %>

<style type="text/css">
.div_img_hidden{
	position:absolute;
	cursor:pointer;
	opacity:0;
	-moz-opacity:0;
	filter:alpha(opacity=0);
}
.div_img_show{
	position:absolute;
	cursor:pointer;
	opacity:0.7;
	-moz-opacity:0.7;
	filter:alpha(opacity=70);
}
</style>
<script language="javascript">
$(function(){
	InitStoreData();
	InitTenantData();
	PrintTenants();
	InitStoreEvent();
});

//初始化所有的租户对象
function InitTenantData(){
	AllTenants.push(new Tenant("1","KFC","1022,1026","1"));
	AllTenants.push(new Tenant("2","玉兰油","1023,1024","1"));
	AllTenants.push(new Tenant("1024","COCO都可茶饮","1024","1"));
	AllTenants.push(new Tenant("1060","泡芙工房","1025","2"));
	AllTenants.push(new Tenant("","奇妮","1103","1"));
	AllTenants.push(new Tenant("","黛安芬","1104","1"));
	AllTenants.push(new Tenant("","杰克琼斯","1105,1106,1107","1"));
	AllTenants.push(new Tenant("","美梦","1108","2"));
	AllTenants.push(new Tenant("","爱美丽","1109","1"));
	AllTenants.push(new Tenant("","威可多","1110","2"));
	AllTenants.push(new Tenant("","罗蒙","1111,1112","1"));
	AllTenants.push(new Tenant("","卡迪乐鳄鱼","1113","2"));
	AllTenants.push(new Tenant("","周生生超市","1114,1115","1"));
	AllTenants.push(new Tenant("","卡帕","1116,1117a","2"));
	AllTenants.push(new Tenant("","耐克","1117b,1118b,1119b","1"));
	AllTenants.push(new Tenant("","匡威","1118a","1"));
	AllTenants.push(new Tenant("","FILA","1120","2"));
	AllTenants.push(new Tenant("","沃德","1121a","1"));
	AllTenants.push(new Tenant("","始祖鸟","1121b","2"));
	AllTenants.push(new Tenant("","鄂尔多斯","1122a,1123a","1"));
	AllTenants.push(new Tenant("","鄂尔多斯","1123a","2"));
	AllTenants.push(new Tenant("","开开","1122b","2"));
	AllTenants.push(new Tenant("","帝高","1123b","2"));
	AllTenants.push(new Tenant("","麦当劳","1124,1126","2"));
	AllTenants.push(new Tenant("","吉野家","1125","2"));
	AllTenants.push(new Tenant("","七公主","1127","2"));
	AllTenants.push(new Tenant("","有恒头巾","1130","2"));
	AllTenants.push(new Tenant("","星期天","1133","2"));
	AllTenants.push(new Tenant("","爱美丽","1134","2"));
	AllTenants.push(new Tenant("","家乐福","1160","1"));
}
//初始化所有的商铺
function InitStoreData(){
	AllStores.push(new Store("1022","101.32"));
	AllStores.push(new Store("1023","201.6"));
	AllStores.push(new Store("1024","104"));
	AllStores.push(new Store("1025","98.34"));
	AllStores.push(new Store("1026","102"));
	AllStores.push(new Store("1103","19.05"));
	AllStores.push(new Store("1104","102"));
	AllStores.push(new Store("1105","102"));
	AllStores.push(new Store("1106","102"));
	AllStores.push(new Store("1107","102"));
	AllStores.push(new Store("1108","102"));
	AllStores.push(new Store("1109","102"));
	AllStores.push(new Store("1110","102"));
	AllStores.push(new Store("1111","102"));
	AllStores.push(new Store("1112","102"));
	AllStores.push(new Store("1113","102"));
	AllStores.push(new Store("1114","102"));
	AllStores.push(new Store("1115","99.7"));
	AllStores.push(new Store("1116","102"));
	AllStores.push(new Store("1117a","102"));
	AllStores.push(new Store("1117b","102"));
	AllStores.push(new Store("1118a","102"));
	AllStores.push(new Store("1118b","102"));
	AllStores.push(new Store("1119a","102"));
	AllStores.push(new Store("1119b","102"));
	AllStores.push(new Store("1120","102"));
	AllStores.push(new Store("1121a","102"));
	AllStores.push(new Store("1121b","102"));
	AllStores.push(new Store("1122a","102"));
	AllStores.push(new Store("1122b","102"));
	AllStores.push(new Store("1123a","102"));
	AllStores.push(new Store("1123b","102"));
	AllStores.push(new Store("1124","102"));
	AllStores.push(new Store("1125","102"));
	AllStores.push(new Store("1126","102"));
	AllStores.push(new Store("1127","102"));
	AllStores.push(new Store("1128","88.58"));
	AllStores.push(new Store("1129","88.68"));
	AllStores.push(new Store("1130","85.93"));
	AllStores.push(new Store("1130","97.59"));
	AllStores.push(new Store("1131","105.24"));
	AllStores.push(new Store("1132","114.23"));
	AllStores.push(new Store("1133","39.52"));
	AllStores.push(new Store("1134","49.85"));
	AllStores.push(new Store("1160","8148.32"));
}

var AllTenants = new Array();		//所有的租户对象列表
var AllStores = new Array();		//所有的商铺对象列表
var STATUS_NONE = "0";				//状态：未出租
var STATUS_YELLOW = "1";			//状态：出租
var STATUS_RED = "2";				//状态：欠费
var STATUS_BLUE = "3";				//状态：选中
//初始化所有的租户信息，显示图片
function PrintTenants(){
	for(var i=0;i<AllTenants.length;i++){
		try{
			var myTenant = AllTenants[i];
			var status_str = getStatusStr(myTenant.statusCd);
			for(var j=0;j<myTenant.arrStores.length;j++){
				var myStore = myTenant.arrStores[j];
				$("#"+myStore.storeNo).attr("src","${ctx}/resources/images/bis/yancheng_1f/"+myStore.storeNo+status_str+".png");
				$("#"+myStore.storeNo).attr("title",myTenant.tenantName+",面积"+myTenant.square+"m2.");
				$("#"+myStore.storeNo).removeClass();
				$("#"+myStore.storeNo).addClass("div_img_show");
			}
		}catch(e){
			alert("PrintTenants["+i+"]"+e);
			break;
		}
	}
}
//初始化所有的商铺的鼠标事件
function InitStoreEvent(){
	for(var i=0;i<AllStores.length;i++){
		try{
			var myStore = AllStores[i];
			$("#"+myStore.storeNo).bind('click', function(){
				//alert($(this).attr("id"));
				if($(this).attr("id")=='1115'){
					var bisTenantId="4028293d30f874cd0130f8b58f060005";
					$("#bisTenantId").val(bisTenantId);
					$('#tenantForm').submit();
				}
				if($(this).attr("id")=='1023'){
					var bisTenantId="402829aa314001bc01314035a7ec0017";
					$("#bisTenantId").val(bisTenantId);
					$('#tenantForm').submit();
				}
			});
			$("#"+myStore.storeNo).hover(function(){
				var myStore = getStoreByNo($(this).attr("id"));
				var myTenant = getTenantByNo($(this).attr("id"));
				if(null!=myTenant){
					for(var i=0;i<myTenant.arrStores.length;i++){
						$("#"+myTenant.arrStores[i].storeNo).attr("src","${ctx}/resources/images/bis/yancheng_1f/"+myTenant.arrStores[i].storeNo+"_blue.png");
					}
				}else{
					if($('#bisTenantId').val()=="4028293d30f874cd0130f8b58f060005"){
						$('#1115').removeClass();
						$('#1115').addClass("div_img_show");
					}else{
					$("#"+myStore.storeNo).removeClass();
					$("#"+myStore.storeNo).addClass("div_img_show");
					}
				}
			},function(){
				var myStore = getStoreByNo($(this).attr("id"));
				var myTenant = getTenantByNo($(this).attr("id"));
				if(null!=myTenant){
					var status_str = getStatusStr(myTenant.statusCd);
					for(var i=0;i<myTenant.arrStores.length;i++){
						$("#"+myTenant.arrStores[i].storeNo).attr("src","${ctx}/resources/images/bis/yancheng_1f/"+myTenant.arrStores[i].storeNo+status_str+".png");
					}
				}else{
					var bisTenantId=$('#bisTenantId').val();
					if($('#bisTenantId').val()=="4028293d30f874cd0130f8b58f060005"){
						$('#1115').removeClass();
						$('#1115').addClass("div_img_show");
					}else{
					$("#"+myStore.storeNo).removeClass();
					$("#"+myStore.storeNo).addClass("div_img_hidden");
					}
				}
			});
		}catch(e){
			alert("InitStoreEvent["+i+"]"+e);
			break;
		}
	}
}
//从statusCd获得图片的后缀
function getStatusStr(fromStr){
	var returnStr = "";
	if(STATUS_YELLOW==fromStr){
		returnStr = "_yellow";
	}else if(STATUS_RED==fromStr){
		returnStr = "_red";
	}else if(STATUS_BLUE==fromStr){
		returnStr = "_blue";
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
function getTenantByNo(fromNo){
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
function Tenant(tenantId,tenantName,storeNos,statusCd,layoutCd){
	this.tenantId = tenantId;
	this.tenantName = tenantName;
	this.storeNos = storeNos;
	this.statusCd = statusCd;
	this.layoutCd = layoutCd;
	try{
		this.arrStores = getStoresByNos(storeNos);
	}catch(e){}
	try{
		var tempSquare = Number(0);
		for(var i=0;i<this.arrStores.length;i++){
			tempSquare += Number(this.arrStores[i].square);
		}
		this.square = tempSquare;
	}catch(e){}
}
//商铺对象
function Store(storeNo,square){
	this.storeNo = storeNo;
	this.square = square;
}
</script>

<div style="background-image:url('${ctx}/resources/images/bis/yancheng_1f/1f.png'); background-repeat:no-repeat; background-position:left top; width:100%; height:420px;">
	<img id="1022" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1022_blue.png" style="left:679px; top:124px;"/>
	<img id="1023" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1023_blue.png" style="left:705px; top:139px;"/>
	<img id="1024" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1024_blue.png" style="left:704px; top:175px;"/>
	<img id="1025" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1025_blue.png" style="left:673px; top:174px;"/>
	<img id="1026" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1026_blue.png" style="left:673px; top:145px;"/>
	<img id="1103" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1103_blue.png" style="left:470px; top:370px;"/>
	<img id="1104" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1104_blue.png" style="left:469px; top:328px;"/>
	<img id="1105" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1105_blue.png" style="left:469px; top:305px;"/>
	<img id="1106" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1106_blue.png" style="left:469px; top:284px;"/>
	<img id="1107" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1107_blue.png" style="left:469px; top:268px;"/>
	<img id="1108" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1108_blue.png" style="left:468px; top:246px;"/>
	<img id="1109" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1109_blue.png" style="left:469px; top:220px;"/>
	<img id="1110" class="div_img_show" src="${ctx}/resources/images/bis/yancheng_1f/1110_blue.png" style="left:490px; top:220px;"/>
	<img id="1111" class="div_img_show" src="${ctx}/resources/images/bis/yancheng_1f/1111_blue.png" style="left:502px; top:220px;"/>
	<img id="1112" class="div_img_show" src="${ctx}/resources/images/bis/yancheng_1f/1112_blue.png" style="left:523px; top:220px;"/>
	<img id="1113" class="div_img_show" src="${ctx}/resources/images/bis/yancheng_1f/1113_blue.png" style="left:545px; top:220px;"/>
	<img id="1114" class="div_img_show" src="${ctx}/resources/images/bis/yancheng_1f/1114_blue.png" style="left:567px; top:219px;"/>
	<img id="1115" class="div_img_show" src="${ctx}/resources/images/bis/yancheng_1f/1115_blue.png" style="left:589px; top:219px;"/>
	<img id="1116" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1116_blue.png" style="left:673px; top:197px;"/>
	<img id="1117a" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1117a_blue.png" style="left:704px; top:198px;"/>
	<img id="1117b" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1117b_blue.png" style="left:738px; top:198px;"/>
	<img id="1118a" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1118a_blue.png" style="left:729px; top:242px;"/>
	<img id="1118b" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1118b_blue.png" style="left:740px; top:241px;"/>
	<img id="1119a" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1119a_blue.png" style="left:729px; top:264px;"/>
	<img id="1119b" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1119b_blue.png" style="left:740px; top:263px;"/>
	<img id="1120" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1120_blue.png" style="left:752px; top:305px;"/>
	<img id="1121a" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1121a_blue.png" style="left:729px; top:306px;"/>
	<img id="1121b" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1121b_blue.png" style="left:742px; top:335px;"/>
	<img id="1122a" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1122a_blue.png" style="left:729px; top:349px;"/>
	<img id="1122b" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1122b_blue.png" style="left:741px; top:347px;"/>
	<img id="1123a" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1123a_blue.png" style="left:728px; top:373px;"/>
	<img id="1123b" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1123b_blue.png" style="left:742px; top:371px;"/>
	<img id="1124" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1124_blue.png" style="left:740px; top:406px;"/>
	<img id="1125" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1125_blue.png" style="left:719px; top:406px;"/>
	<img id="1126" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1126_blue.png" style="left:704px; top:406px;"/>
	<img id="1127" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1127_blue.png" style="left:632px; top:406px;"/>
	<img id="1128" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1128_blue.png" style="left:610px; top:405px;"/>
	<img id="1129" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1129_blue.png" style="left:588px; top:406px;"/>
	<img id="1130" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1130_blue.png" style="left:566px; top:406px;"/>
	<img id="1131" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1131_blue.png" style="left:545px; top:406px;"/>
	<img id="1132" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1132_blue.png" style="left:502px; top:407px;"/>
	<img id="1133" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1133_blue.png" style="left:491px; top:417px;"/>
	<img id="1134" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1134_blue.png" style="left:471px; top:418px;"/>
	<img id="1160" class="div_img_hidden" src="${ctx}/resources/images/bis/yancheng_1f/1160_blue.png" style="left:480px; top:219px; z-index:-1;"/>
</div>
<table>
    <td>
    	<div style="width: 10px;height: 10px;background-color: red;"></div>欠费商铺
    </td>
    <td>
    <div style="width: 10px;height: 10px;background-color: yellow;"></div>欠费商铺
    </td>
    <td>
    <div style="width: 10px;height: 10px;background-color: blue;"></div>欠费商铺
    </td>
</table>