<%@page import="com.hhz.ump.util.DateUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ page language="java" import="com.hhz.ump.util.DictContants;" %>

<title>收费历史记录</title> 

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-project.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis.fact.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/page.css"/>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/ConvertUtil.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/formValidator/formValidator.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/ConverUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<script type="text/javascript" charset="UTF-8" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js" ></script>

<style >

.select_125_20{height:20px;width:125px;}
.select_115_20{height:20px;width:85px;}
.scrollDetail{}
/*必选控件样式*/
.mustSelet{color:red;}
table.content_table .header th{   font-weight: bold;}
/*
.quickSenior { 
    background-image: url("../resources/images/res/search_senior.gif");
    background-position: 5px 5px;
    background-repeat: no-repeat;
    color: white;
    cursor: pointer;
    padding: 5px;
}
.search_tr{padding-left:8px;line-height:27px;height:27px;border-bottom: 1px solid #8C8F94;}
*/

#search_due li{color:#006FB6;float:right;margin:8px 4px 0;height:18px;line-height:18px;cursor:pointer;}
#bis_rpt li{float:left;	height:24px;	line-height:24px;	padding:0 8px;	margin: 4px 2.5px;border:1px solid #ccc;cursor:pointer;}
#bis_rpt li:hover{	background-color:#006FB6;	color:#fff;}
.bis_fact_click {	background-color:#006FB6;	color:#fff;border:1px solid #006FB6;}  
#search_fact{padding-right:10px;}
#search_fact_yu_s{padding-right:10px;}
#search_fact li{cursor:pointer;color:#006FB6;float:right;margin:8px 4px 0;height:18px;line-height:18px;}
#search_fact_yu_s li{cursor:pointer;color:#006FB6;float:right;margin:8px 4px 0;height:18px;line-height:18px;}
</style>
</head>
<body >
<div id="header">
<%@ include file="bis-fact-header.jsp"%>
</div>
<div id="addContent" style="display:none;clear:both;height:150px;margin:0px;padding:5px;border-bottom:1px solid #dddbdc;background:#f7f7f7;">
</div>
			
<div id="welcom" style="clear:both;height:30px;width:80%">
	<div style="color:#6BAD42;font-size:16px;font-weight:bold;width:auto;margin-top:200px;text-align:center;">
		请选择搜索条件
	</div>
</div>
<div id="detailPanel" style="clear:both;width:100%">
	<div id="detailFor" style="clear:both;">
	</div>
</div>
	
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.fact.js" ></script>
<script type="text/javascript"><!--
/**
 * 应收维度显示控件	must_dime
 * 实收维度显示控件	fact_dime
 * 商铺维度显示控件	store_layout
 * 公寓维度显示控件	flat_layout
 * 多径维度显示控件	multi_layout
 */
//应收列表
var AllFactMust = new Array();
var TmpFactMust = new Array();
function adjustHeight() {
	var height = $(window).height()-128;
	//alert($("#floorDiv").height());
	//alert($("#baseInfoDiv").height());
	//$("#tableDiv").height($(window).height()-38);
	$("#tableDiv").css('height',height+'px');
	if($("#floorDiv").height() > height || $("#baseInfoDiv").height() > height) {
		//if($("#footerDiv").hasClass("bottom_div")) {
		//	$("#footerDiv").removeClass("bottom_div");
		//}
		$("#footerDiv").addClass("followed_div");
	} else {
		//$("#footerDiv").removeClass("followed_div");
		$("#footerDiv").addClass("bottom_bar");
	}
}
var uiid = '<%=SpringSecurityUtils.getCurrentUiid()%>';
layOutCdStore='<%=DictContants.BIS_CONT_TYPE_STORE%>';
layOutCdFlat='<%=DictContants.BIS_CONT_TYPE_FLAT%>';
layOutCdMulti='<%=DictContants.BIS_CONT_TYPE_MULTI%>';
chargeType02='14';
chargeType03='12';
chargeType38='13';
search=${search};
searchAll=${searchAll};
checkDel=${checkDel};
newUpdateStore=${newUpdateStore};
newUpdateFlat=${newUpdateFlat};
newUpdateMulti=${newUpdateMulti};
var currProjectId = '${bisProjectId}';
var currProjectName = '${bisProjectName}';
var bisTenantId = '${bisTenantId}';
var bisTenantName = '${currDetailName}';
var dimension = '${dimension}';
var year = '${factYear}';
//显示租户、公寓、多径的空间id
var currLayoutLabel='layOutCdList_v0';
$(function(){
	if(uiid=='jiaoxf'){
		//$('#notPublic').show();
	}else{
		$('#notPublic').hide();
		
	}
	
	iniForMustFact();
	$("#seniorHeadTool").hide();
	$('#bisProjectId').val(currProjectId);

	$('#welcom').show();
	//权限处理
	processRole();
	//loadDetail();
	
	iniDetail();

	//单击实收记录
	
	$('#creatorQ').userSelect({ muti:false  }); 
	$('#checkUserCdQ').userSelect({ muti:false }); 
	//回车搜索
	click_on_return('btn_search');
});

//注册选中应收或实收维度方法  注册只看欠费事件
function iniForMustFact(){
	//设置默认应收维度应收截止日期
	$('#mustInEnd').val(getNextMonth());
	
	if(dimension==''){dimension=3;}
	if(dimension=='1'){
 		//应收维度
		toggleShowHide({'.must_dime':'show','.fact_dime':'hide','.advances_dime':'hide'});
		$('#must').addClass('bis_fact_click');
	}else 	if(dimension=='2'){
		toggleShowHide({'.must_dime':'show','.fact_dime':'hide','.advances_dime':'hide'});
		$('#overdue').addClass('bis_fact_click');
	}else 	if(dimension=='3'){
		//实收维度
		toggleShowHide({'.must_dime':'hide','.fact_dime':'show','.advances_dime':'hide'});
		$('#fact').addClass('bis_fact_click');
	}else if(dimension=='4'){
		$('#fact').addClass('bis_fact_click');
	}else if(dimension=='8'){
		$('#actIncome').addClass('bis_fact_click');
	}else if(dimension=='9'){
		$('#gysfRecord').addClass('bis_fact_click');
	}
	$('#bis_rpt').find('li').bind('click',
		function(){
			$('.must_dime').show();
			$(this).addClass('bis_fact_click').siblings().removeClass('bis_fact_click');
			if((this).id=='fact'){
				toggleShowHide({'.no_advances_dime':'show','#search_due':'hide','.must_dime':'hide','.fact_dime':'show','.advances_dime':'hide'});
			}	else if((this).id=='must'){
				toggleShowHide({'.no_advances_dime':'show','.must_dime':'show','.fact_dime':'hide','#search_due':'show','.advances_dime':'hide'});
				$('#mustInEnd').val(getNextMonth());
			}	else if((this).id=='overdue'){
				toggleShowHide({'.no_advances_dime':'show','.must_dime':'show','.fact_dime':'hide','#search_due':'hide','.advances_dime':'hide'});
				$('#mustInEnd').val(getCurDate());
			}	else if((this).id=='advances'){//预收维度
				toggleShowHide({'.advances_dime':'show','.no_advances_dime':'hide','.must_dime':'hide','.fact_dime':'hide',});
				toFactYuShou();
				return;
			} 
			initSearchFactCss();
			submitSearch1();
	});
	$('#search_fact').find('li').bind('click',
			function(){
		if($(this).attr('valu')!='n'){
			$('#search_fact li').each(function(){
				if($(this).attr('valu')!='n'){
					$(this).css('color','#006FB6');
				}
			});
			$(this).css('color','red');
			$('#statusCd').val($(this).attr('valu'));
			submitSearch3();
		} 
	}).bind('mouseover',function(){	if($(this).attr('valu')!='n'){	$(this).css('text-decoration','underline');	}}).bind('mouseout',function(){	if($(this).attr('valu')!='n')	$(this).css('text-decoration','none');});	
	$('#search_due').find('li').bind('click',
			function(){
		if($(this).attr('valu')!='n'){
			$('#search_due li').each(function(){
				if($(this).attr('valu')!='n'){
					$(this).css('color','#006FB6');
				}
			});
			$(this).css('color','red');
			submitSearch3();
		} 
	}).bind('mouseover',function(){	if($(this).attr('valu')!='n'){	$(this).css('text-decoration','underline');	}}).bind('mouseout',function(){	if($(this).attr('valu')!='n')	$(this).css('text-decoration','none');});	

}
function initSearchFactCss(){
	$('#statusCd').val('');
	$('#search_fact').find('li').each(
		function(){
			if($(this).attr('valu')!='n'){
					if($(this).attr('valu')!='n'){
						$(this).css('color','#006FB6');
					}
			}
			$('#factAll').css('color','red');
		});
}
//高级搜索事件
function seniorSearch(){
	if($("#seniorHeadTool").css("display")=="none"){
		$("#btnQuickSenior").addClass("quickSeniorHover");
		$("#seniorHeadTool").show();
		$("#tableDiv").height($("#tableDiv").height()-$("#seniorHeadTool").height()-15);
		loadSeniorSearch();
		$("#normalHeadTool").hide();
	}else{
		$("#btnQuickSenior").removeClass("quickSeniorHover");
		$("#seniorHeadTool").hide();
		$("#tableDiv").height($("#tableDiv").height()+$("#seniorHeadTool").height()+15);
		$("#normalHeadTool").show();
	}
}
//渲染高级搜索内容
function loadSeniorSearch(){
	var bisProjectId=$('#bisProjectId').val();
	var localLayOutCd = getCurrlayOutCd();
	if(bisProjectId=='')return;
	if(localLayOutCd){}else return;
	
}
function appendSelect(selectField,data){
	$("#"+selectField).html('');
	appendOption(selectField);
	$.each(data,function(i,n){
		var option = '<option value="'+n.id+'">'+n.text+'</option>';
		 $('#'+selectField).append(option);
	});
}
function datagridOnClick(bisFactField,factVo,s){
	var data = {bisFactId:bisFactField,contLayOutCd:getCurrlayOutCd()};
	var url ;
	switch(getCurrlayOutCd()){
		case '1':url = _ctx+'/bis/bis-fact!editForStore.action';break;
		case '2':url = _ctx +'/bis/bis-fact!editForFlat.action';break;
		case '3':url = _ctx + '/bis/bis-fact!editForMulti.action';break;
	}
	
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='selectTypeDiv'><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
		width : 450,
		height :380,
		title : "编辑",
		afterShow : function() {
			$.post(url, data, function(result) {
				$("#selectTypeDiv").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				
			}
			ymPrompt.close();
		},
		closeBtn:true,
		btn:[]
	});
}
function toFactDeduct(bisFactId,layOutCd){
	ymPrompt.close();
	var data = {id:bisFactId,layOutCd:layOutCd};
	var url = '${ctx}/bis/bis-fact!toDeduct.action';
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='selectTypeDiv'><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
		width : 650,
		height :200,
		title : "抵扣",
		closeBtn:true,
		afterShow : function() {
			$.post(url, data, function(result) {
				$("#selectTypeDiv").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				
			}
			ymPrompt.close();
		},
		btn:[]
	});
}
function factDeduct(parentId,bisProjectId,chargeTypeCd,factYear,factMonth,money,contLayOutCd,currDetail,bisShopId,bisStoreId){
	var data = {id:parentId,bisProjectId:bisProjectId,chargeTypeCd:chargeTypeCd,factYear:factYear,factMonth:factMonth,money:money,layOutCd:contLayOutCd,currDetail:currDetail,bisShopId:bisShopId,bisStoreId:bisStoreId};
	var url = '${ctx}/bis/bis-fact!deductMoney.action';
	$.post(url,data,function(result){
		var data = result;
		if(data=='error'){
			alert('操作失败');
		}
	});
	ymPrompt.close();
}
function ymPromptClose(){
	ymPrompt.close();
}
/*
 *预加载搜索信息：优先顺序商铺、公寓、多经营
 *btnId	input输入框
 * 
 */
function iniDetail(){
	
	//从租户跳转到当前页面，初始化租户名称
	if(bisTenantId){
		 $('#layOutCdList_v0').val(bisTenantName);
		 //应收页面快速搜索 设置全部
		// $('#allnodueli').trigger('click');
	}
	
	var obj = new FactVo();
	obj.bisProjectId = currProjectId;
	obj.overdue = '2';
	if(currProjectId!=''&&bisTenantId!=''){
		obj.bisTenantId = bisTenantId;
		if(year!='')
		obj.year = year;
		obj.contLayOutCd = layOutCdStore;
		getDetail(obj);
		return;
	}
	if(newUpdateStore){
		obj.contLayOutCd = layOutCdStore;
		getDetail(obj);
		return ;
	}
	if(newUpdateFlat){
		obj.contLayOutCd = layOutCdFlat;
		getDetail(obj);
		return ;
	}
	if(newUpdateMulti){
		obj.contLayOutCd = layOutCdMulti;
		getDetail(obj);
		return ;
	}
}
function  FactVo(){
	this.bisProjectId='';
	this.contLayOutCd='';
	this.chargeTypeCd='';
	this.factYear='';
	this.factMonth='';
	this.bisTenantId='';
	this.bisFlatId='';
	this.bisMultiId='';
	this.bisShopId='';
	this.statusCd='';
	this.creator='';
	this.checkUserCd='';
	this.minMonth='';
	this.maxMonth='';
	this.minMoney='';
	this.maxMoney='';
	this.mustInBegin='';
	this.mustInEnd='';
	this.factInBegin='';
	this.factInEnd='';
	this.buildingNum='';
	this.overdue='';//欠费状态
	this.mustOrFact='';//搜索维度 应收2 ； 实收 1 ； 欠费 3 ;预收 4
}
function getDetail(obj){
	$('#welcom').hide();
	$('#addContent').hide();
	$('#detailPanel').show();
	if(obj.contLayOutCd){
		setCurrlayOutCd(obj.contLayOutCd);
	}

	var	pageNo=1;
	//应收 实收维度
	var mustOrFact = $('li.bis_fact_click').attr('value');
	obj.mustOrFact = mustOrFact;
	//只看欠费
/*	var overDue ;
	if($('#overdue').hasClass('bis_fact_click')){
		
		overDue= $('#overdue').attr('value');
		//以应收维度看
		mustOrFact='2';
	}*/
	var data = obj
	currData= obj;
	switch(currlayOutCd){
		case layOutCdStore:{
			currUrl=_ctx+'/bis/bis-fact!searchMustForStore.action';
			break;}
		case layOutCdFlat:{
			currUrl=_ctx+'/bis/bis-fact!searchMustForFlat.action';
			break;}
		case layOutCdMulti:{
			currUrl=_ctx+'/bis/bis-fact!searchForMulti.action';
			break;}
	}
	$('#detailFor').mask('正在加载数据');
	$('#main_search_div1').mask();
	var su = '0';
	$.post(currUrl,data,function(result){
		if(result.indexOf('rror')>0){
			result.replace('error','');
			alert("请精确商家或商铺编号搜索条件");
		} 
		$('#detailFor').html(result).show().unmask();
		$('#main_search_div1').unmask();
		//setTimeout(function(){$('#detailFor').unmask();},500); //不然会提示参数未定义. 
		su='1'
		if(su=='1')
			adjustHeight();
	});
}
function queryBisShopList(){
	jumpPage(1);
}
var currData={};
var currUrl;
var currPanel;
function jumpPage(pageNo){
	currData.pageNo = pageNo;
	$.post(currUrl,currData,function(result){
			if(result){
				$("#"+currPanel).html(result);
			}
	});
}
function submitSearch1(){
	submitSearch('','bisProjectIdFact','chargeTypeCd','layOutCd',currLayoutLabel,'year','month','statusCd');
}
function submitSearch4(){
	submitSearch('0','bisProjectIdFact','chargeTypeCd','layOutCd',currLayoutLabel,'year','month','statusCd');
}
function submitSearch2(){
	submitSearch('','bisProjectIdFact','chargeTypeCd','layOutCd',currLayoutLabel,'year','month',
			'statusCd','creator','checkUserCd','minMonth','maxMonth','minMoney','maxMoney','inDateBegin','inDateEnd');
}
function submitSearch3(){
	submitSearch('','bisProjectIdFact','chargeTypeCd','layOutCd',currLayoutLabel,'year','month',
			'statusCd');
}
function submitSearch(overdue,bisProjectId,chargeTypeCd,layOutCd,layOutCdList,year,month,
		statusCdV,creator,checkUserCd,minMonth,maxMonth,minMoney,maxMoney,inDateBegin,inDateEnd){
	$('#factoptip').fadeOut();
	var obj = new FactVo();
	obj.bisProjectId=$('#'+bisProjectId).val();;
	obj.contLayOutCd=$('#'+layOutCd).val();;
	obj.chargeTypeCd=$('#'+chargeTypeCd).val();;
	obj.factYear=$('#'+year).val();
	obj.factMonth=$('#'+month).val();
	obj.statusCd=$('#'+statusCdV).val();
	obj.creator=$('#'+creator).val();
	obj.checkUserCd=$('#'+checkUserCd).val();
	obj.minMonth=$('#'+minMonth).val();
	obj.maxMonth=$('#'+maxMonth).val();
	obj.minMoney=$('#'+minMoney).val();
	obj.maxMoney=$('#'+maxMoney).val();
	
	obj.factInBegin = $('#factInBegin').val();
	obj.factInEnd = $('#factInEnd').val();
	obj.mustInBegin = $('#mustInBegin').val();
	obj.mustInEnd = $('#mustInEnd').val();
	obj.buildingNum = $('#flatBuding').val();
	$('#search_due').find('li').each(function(result){
		var _this = this;
		if($(_this).css('color')=='red'){
			obj.overdue =$(_this).attr('valu');
		}
	});
	
	setCurrlayOutCd(obj.contLayOutCd);
	var layOutCdValue  = $("#"+layOutCdList).val();//租户、公寓、多径id
	if(layOutCdValue){
	}else layOutCdValue='';
	obj.bisShopId='';
	var url ;
	switch(currlayOutCd){
		case layOutCdStore:{
			if(layOutCdValue!='搜索商家/商铺')
			obj.bisTenantId=layOutCdValue;
			break;}
		case layOutCdFlat:
			if(layOutCdValue!='搜索公寓编号')
			obj.bisFlatId=layOutCdValue;
			break;
		case layOutCdMulti:
			obj.bisMultiId=layOutCdValue;
			break;
	}
	var localchargeTypeCd  = obj.chargeTypeCd;
	//url+="&bisProjectId="+bisProjectId+"&chargeTypeCd="+chargeTypeCd;
	//如果费用类型为：[03:经营保证金][38:物业履约保证金][02:租金履约保证金]等保证金 可以不填年月
	if(localchargeTypeCd !=chargeType02
		&&localchargeTypeCd !=chargeType03
		&&localchargeTypeCd !=chargeType38){
	} 
	getDetail(obj);
	
}
//校验年月是否可为空
function validateCharge(chargeTypeCd,year,month){
	if(chargeTypeCd !=chargeType02
			&&chargeTypeCd !=chargeType03
			&&chargeTypeCd !=chargeType38){
		if(year==''||year==null){
			return false;
		}
		if(month==''||month==null){
			return false;
		}
		return true;
	} 
	return true;
}
//选择租户
var bisTenantId;
//触发按钮：搜索页面明细控件
function selectDetail1(){
	switch(currlayOutCd){
//		case '1':selectTenant('layOutCd','bisProjectIdFact','layOutCdList');break;
		case '1':break;
		case '2':selectFlat('layOutCd','bisProjectIdFact',currLayoutLabel);break;
//		default:selectTenant('layOutCd','bisProjectIdFact','layOutCdList');break;
		default:break;
	}
}
//触发按钮：搜索页面高级搜索-明细控件
function selectDetail3(){
	switch(currlayOutCd){
//		case '1':selectTenant('layOutCdSenior','bisProjectId','layOutCdListSenior');break;
		case '1':break;
		case '2':selectFlat('layOutCdSenior','bisProjectId','layOutCdListSenior');break;
	}
}
//触发按钮：新增页面明细控件
function selectDetail2(){
		switch(currlayOutCd){
		case '1':selectTenant('layOutCdInput','bisProjectIdFact','layOutCdListInput');break;
		case '2':selectFlat('layOutCdIdInput','bisProjectIdFact','layOutCdListInput');break;
	}
}
function selectFlat(layOutCdId,bisProjectIdFact,detailLabel,layOutCdList){
	var bisProjectId = $('#'+bisProjectIdFact).val();
	 currLayoutLabel = detailLabel;
	if(bisProjectId){
		if(bisProjectId =='')
			alert('请选择项目');
		var url = '${ctx}/bis/bis-flat!select.action?bisProjectId='+bisProjectId;
		ymPrompt.confirmInfo( {
			icoCls : "",
			autoClose:false,
			message : "<div id='selectTypeDiv' ><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
			width : 200,
			height : 400,
			title : "请选择公寓",
			closeBtn:true,
			afterShow : function() {
				var url = '${ctx}/bis/bis-flat!select.action';
				$.post(url, {bisProjectId:bisProjectId}, function(result) {
					$("#selectTypeDiv").html(result);
				});
			},
			handler : function(btn){
				if(btn=='ok'){
					setFlatNo(getFlatId(),getFlatNo());
				}else{
					ymPrompt.close();
				}
			},
			btn:[["确定",'ok'],["取消",'cancel']]
		});
	}
}

function setFlatNo(flatId,flatNo){
	$("#"+currLayoutLabel).empty();
	$("#"+currLayoutLabel).append("<option value='"+flatId+"'>"+flatNo+"</option>"); 
	$("#"+currLayoutLabel+" option[value="+flatNo+"]").attr("selected", true); 
	setTitle(currLayoutLabel,flatNo);
	ymPrompt.close();
}
function selectTenant(layOutCdId,bisProjectIdFact,layOutCdList){
	var bisProjectId = $('#'+bisProjectIdFact).val();
	var layOutCd = $('#'+layOutCdId).val();
	if(bisProjectId){
		if(layOutCd!='1')return;
		if(bisProjectId =='')
			alert('请选择项目');
		var url = '${ctx}/bis/bis-tenant!searchTenant.action?bisProjectId='+bisProjectId;
		ymPrompt.confirmInfo( {
			icoCls : "",
			autoClose:false,
			message : "<div id='selectTypeDiv' ><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
			width : 410,
			height : 300,
			top:20,
			title : "请选择租户",
			closeBtn:true,
			afterShow : function() {
				var url = '${ctx}/bis/bis-tenant!searchTenant.action';
				$.post(url, {bisProjectId:bisProjectId}, function(result) {
					$("#selectTypeDiv").html(result);
					quich_search_tenant(bisProjectIdFact,layOutCdList);
				});
			},
			handler : function(btn){
				if(btn=='ok'){
					bisTenantId =  getTenantId();
					var tenantName = getTenantName();
					$("#"+layOutCdList).empty();
					$("#"+layOutCdList).append("<option value='"+bisTenantId+"'>"+tenantName+"</option>"); 
					$("#"+layOutCdList+" option[value="+bisTenantId+"]").attr("selected", true); 
					setTitle(layOutCdList,tenantName);
					
				}
				ymPrompt.close();
			},
			btn:[["取消",'cancel']]
		});
	}
}
function getContList(bisTenantId,ulId,layOutCdList,tenantName){
	var url = '${ctx}/bis/bis-cont!loadTenantCont4.action';
	$.post(url,{bisTenantId:bisTenantId},function(result){
		var data = eval(result);
		var lis ='';
		for(var i=0;i<data.length;i++){
			var til = data[i].contNo+'['+data[i].contBigTypeCd+'-'+data[i].contSmallTypeCd+']';
			lis += '<li id="' +data[i].bisContId+'"  title="'+til+'">'+til+'</li>';
		}
		$('#'+ulId).html(lis);
		$('#setContUl li').bind('click',function(){
			var _this = this;
			$(_this).addClass('addFirst');
			$("#"+layOutCdList).empty();
			$("#"+layOutCdList).append("<option value='"+bisTenantId+"'>"+tenantName+"</option>"); 
			$("#"+layOutCdList+" option[value="+bisTenantId+"]").attr("selected", true); 
			$('#bisContId').val($(_this).attr('id'));
			setTitle(layOutCdList,tenantName);
			ymPrompt.close();
		});
		$('#setContUl').height($('#setContDiv').height()-40);
	});
}
//注册快速搜索(模糊匹配:uiid,userName)
function quich_search_tenant(bis_projec_input,layOutCdList){
	var bis_project_id = $('#'+bis_projec_input).val();
	$('#quickSearchShop').quickSearch(
		'${ctx}/bis/bis-tenant!quickSearchTenant.action?type=shop',
		['nameCn','storeNo'],
		{bisTenantId:'quickSearchFieldId',nameCn:'quickSearchField'},
		{bisProjectId:bis_project_id},
		function(result){
			//refreshShopStoreTable(result);
			var bisTenantId =  result.attr('bisTenantId');
			var tenantName = result.attr('nameCn')+'-'+result.attr('storeNo');
			
			getContList(bisTenantId,'setContUl',layOutCdList,tenantName);
		}
	);
	$('#quickSearchStore').quickSearch(
		'${ctx}/bis/bis-tenant!quickSearchTenant.action?type=store',
		['nameCn','storeNo'],
		{bisTenantId:'quickSearchFieldId',nameCn:'quickSearchField'},
		{bisProjectId:bis_project_id},
		function(result){
			//refreshShopStoreTable(result);
			var bisTenantId =  result.attr('bisTenantId');
			var tenantName = result.attr('nameCn')+'-'+result.attr('storeNo');
			getContList(bisTenantId,'setContUl',layOutCdList,tenantName);
		}
	);
	
	bisTenantId =  getTenantId();
	var tenantName = getTenantName();
	$("#"+layOutCdList).empty();
	$("#"+layOutCdList).append("<option value='"+bisTenantId+"'>"+tenantName+"</option>"); 
	$("#"+layOutCdList+" option[value="+bisTenantId+"]").attr("selected", true); 
	setTitle(layOutCdList,tenantName);
}
function refreshShopStoreTable(result){
	var resultHtml = 
		'<table id="mainTable" border="0" cellpadding="0" cellspacing="0" ><col width="250">   	<col width="250"><input type="hidden" id="bisTenantId" ><input type="hidden" id="tenantName" >'+
			'<tr> <th align="left">商家名称</th>	<th style="padding-left: 5px;" align="left">商铺号&nbsp;</th></tr>'+
			'<tr id="'+result.attr('bisTenantId')+'_TD" >'+
				'<td align="left" class="pd-chill-tip"'+"  title='"+result.attr('nameCn')+"'  >"+result.attr('nameCn')+'</td>'+
				'<td align="left" class="pd-chill-tip"'+"  title='"+result.attr('storeNo')+"'  >"+result.attr('storeNo')+'</td>'+
			'</tr>'+
		'</table>';
	$('#shopStoreTable').html(resultHtml);
	$('.pd-chill-tip').bind('click',function (){
		selShopStore(result.attr('bisTenantId'),result.attr('nameCn')+'-'+result.attr('storeNo'));
	});
}
//设置空间title值
function setTitle(control,title){
	$("#"+control).attr("title", title); 
}
//设置控件title
function setThisTitle(con){
	var value = $("#"+con).val();
	$('#'+con).attr("title", $("#"+con).find("option[value="+value+"]").text()); 
}
//触发按钮：高级搜索页面项目、业态控件
function changlayOutDetail3(){
	changlayOutDetail('layOutCdSenior','bisProjectIdFact','detailLabelSenior','layOutCdListSenior');
	loadSeniorSearch();
}
//触发按钮：搜索页面项目、业态控件
function changlayOutDetail1(){
	changlayOutDetail('layOutCd','bisProjectIdFact','detailLabel',currLayoutLabel);
	loadSeniorSearch();
	switch(currlayOutCd){
	case '1':{
		$('td[layout=flat]').hide();
		return ;
	}
	case '2':
		$('td[layout=flat]').show();
		
		break;
	case '3':{
		$('td[layout=flat]').hide();
		break;
	}
	}
}
//触发按钮：新增页面项目、业态控件
function changlayOutDetail2(){
	
	//changlayOutDetail('layOutCdInput','bisProjectIdInput','currDetailLabel','layOutCdListInput','1');
	var project = $('#bisProjectIdInput').val();
	var s='<font style="color:red;">*</font>';
	var currDetailId ;

	currPanel='detailFor';
	switch(currlayOutCd){
		case '1':{
			$('#layOutCdInput').val('1');
			$('#layOutCdInput0').val('商铺');
			$('#currDetailLabel').html(s+'商铺'+'：');
			return ;
		}
		case '2':
			currDetailId= $('#layOutCdList_v1').val()
			$('#layOutCdInput').val('2');
			$('#layOutCdInput0').val('公寓');
			$('#currDetailLabel').html(s+'公寓'+'：');
//			currPanel='detailFor';
			return;
		case '3':
			currDetailId= $('#layOutCdList_v1').val()
			$('#layOutCdInput').val('3');
			$('#layOutCdInput0').val('多经');
			$('#currDetailLabel').html(s+'多经'+'：');
//			currPanel='detailFor';
			url=_ctx+'/bis/bis-fact!searchMulti.action';break;
	}
	currProjectId = project;
	var  layOutCdListInput='layOutCdListInput';
	$.post(url,{bisProjectId:project},function(result){
		var data = eval(result);
		 $("#"+layOutCdListInput).html('');
		 appendOption(layOutCdListInput);
		 var option ='';
		 setTitle('layOutCdList','');
		$.each(data,function(i,n){
			//无法在初始化完成之后，设置某个值选中，所有在初始化时设置选中
			if(n.id==currDetailId){
				option+= '<option onclick=setTitle("'+layOutCdListInput+'","'+n.text+'") value="'+n.id+'" selected="selected">'+n.text+'</option>';
				
			}else
				option+= '<option onclick=setTitle("'+layOutCdListInput+'","'+n.text+'") value="'+n.id+'">'+n.text+'</option>';
		});
		$("#"+layOutCdListInput).append(option);
		adjustHeight();
	});
}
/**
 * 触发事件：业态切换
 * 执行动作：若项目不为空，更新明细(租户、公寓、多经)信息
 *			
 */
 var currLayoutLabel;//当前商铺，公寓，多径控件的名称
 var currUrl;
function changlayOutDetail(layOutCdId,bisProjectId,detailLabel,layOutCdList,s){
	var project = $('#'+bisProjectId).val();
	$('#'+layOutCdList).html('');
	if(project==''){
		//若项目为空，清空明细信息,提示必须选
		$('#'+bisProjectId).addClass('mustSelet');
		return ;
	}
	var locallayOutCd = $('#'+layOutCdId).val();
	if(locallayOutCd==''){
		//若业态为空，清空明细信息，提示必选
		$('#'+layOutCdList).html('');
		$('#'+layOutCdId).addClass('mustSelet');
		return ;
	}
	setCurrlayOutCd(locallayOutCd);
	var url;
	var currDetailId = $('#layOutCdList').val();
	if(s=='1'){
		s='<font style="color:red;">*</font>';
	}else{
		s='';
	}
	switch(currlayOutCd){
		case '1':{
			//selectTenant();
			//url=_ctx+'/bis/bis-fact!searchTenant.action';break;
			$('#'+detailLabel).html(s+'租户：');
			//$("#"+layOutCdList).html('');
			//var currDetailName = $('#layOutCdList').text();
			//var option = '<option value="'+currDetailId+'">'+currDetailName+'</option>';
			//$("#"+layOutCdList).html('-请选择-');
			$('#layOutCdList_v1').hide();$('#layOutCdList_v0').show();
			currLayoutLabel='layOutCdList_v0';
			$('#'+currLayoutLabel).val('搜索商家/商铺');
			$('#'+currLayoutLabel).css('color','#ccc');
			currUrl= _ctx+'/bis/bis-fact!searchMustForStore.action';
			return ;
		}
		case '2':
			$('#layOutCdList_v1').hide();$('#layOutCdList_v0').show();
			currLayoutLabel='layOutCdList_v0';
			$('#'+currLayoutLabel).val('搜索公寓编号');
			$('#'+currLayoutLabel).css('color','#ccc');
			currUrl= _ctx+'/bis/bis-fact!searchMustForFlat.action';
			url=_ctx+'/bis/bis-fact!searchFlat.action';$('#'+detailLabel).html(s+'公寓：');return;
		case '3':
			$('#layOutCdList_v1').show();$('#layOutCdList_v0').hide();
			currLayoutLabel='layOutCdList_v1';
			
			currUrl= _ctx+'/bis/bis-fact!searchForMulti.action';
			url=_ctx+'/bis/bis-fact!searchMulti.action';$('#'+detailLabel).html(s+'多经：');setTitle(currLayoutLabel,'');break;
	}
	currProjectId = project;
	$.post(url,{bisProjectId:project},function(result){
		var data = eval(result);
		 $("#"+currLayoutLabel).html('');
		 appendOption(currLayoutLabel);
		 var option ='';
		 setTitle('layOutCdList','');
		$.each(data,function(i,n){
			//无法在初始化完成之后，设置某个值选中，所有在初始化时设置选中
			if(n.id==currDetailId){
				option+= '<option onclick=setTitle("'+currLayoutLabel+'","'+n.text+'") value="'+n.id+'" selected="selected">'+n.text+'</option>';
				
			}else
				option+= '<option onclick=setTitle("'+currLayoutLabel+'","'+n.text+'") value="'+n.id+'">'+n.text+'</option>';
		});
		$("#"+currLayoutLabel).append(option);
		adjustHeight();
	});
}
function appendOption(inputId){
	var option = '<option value="">-请选择-</option>';
	 $("#"+inputId).append(option);
}
/**
 * 用途：搜索实收内容
 * 设置当前业态
 * @param layOutCd  
 */
function setCurrlayOutCd(layOutCd){
	currlayOutCd=layOutCd;
}
//新增 录入实收
function appendFact(){
	var bisProjectId = $('#bisProjectId').val();
	var layOutCd = $('#layOutCd').val();
	var layOutCdList =  $("#layOutCdList").val()
	var currDetailName =  $("#layOutCdList").text(); 
	var chargeTypeCd = $('#chargeTypeCd').val();
	var factYear = $('#year').val();
	var factMonth = $('#month').val();
	
	appendBisFact(bisProjectId,layOutCd,chargeTypeCd,factYear,factMonth);
}

function appendBisFact(bisProjectId,layOutCd,chargeTypeCd,factYear,factMonth){
	$('#welcom').hide();
	var data = {bisProjectId:bisProjectId,layOutCd:layOutCd,chargeTypeCd:chargeTypeCd,factYear:factYear,factMonth:factMonth};
	if($("#addContent").css("display")=="none"){
		var url = _ctx+'/bis/bis-fact!input.action';
		$.post(url,data,function(result){
			$('#addContent').html(result).show();
			initInputInfo();
			
		});
		//$('#detailPanel').hide();
	}else{
		$("#addContent").html('').hide();
	}
}
//搜索应收，录入实收
/**
 * layOutCd:业态编号
 * currDetailId:租户、公寓、多径信息id
 * currDetailName:租户、公寓、多径 标签
 * chargeTypeCd:费用类别
 */
function appendBisFact1(bisProjectId,layOutCd,currDetailId,currDetailName,chargeTypeCd,year,month,mustMoney,money,collDate,currDetailName,storeId,bisContId){
	var data = {bisProjectId:bisProjectId,layOutCd:layOutCd,chargeTypeCd:chargeTypeCd,factYear:year,factMonth:month,mustMoney:mustMoney,currDetail:currDetailId,factDate:collDate,currDetailName:currDetailName,storeId:storeId,bisContId:bisContId};
	var url = _ctx+'/bis/bis-fact!toFactList.action';
			
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='selectTypeDiv'><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
		width : 500,
		height :400,
		title : "查看明细",
		closeBtn:true,
		afterShow : function() {
			$.post(url, data, function(result) {
				$("#selectTypeDiv").html(result);
				//记忆所选项目
				$('#bisProjectNameInput').val($('#bisProjectName').val());
				var option = '<option value="'+currDetailId+'">'+currDetailName+'</option>';
				$("#layOutCdListInput").append(option);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				
				var money = $('#newMoney').val();
				var remark = $('#remark').val();
				var factListDate = $('#factListDate').val();
				saveFact(factListData.layOutCd,factListData.bisProjectId,factListData.currDetail,factListData.storeId,factListData.chargeTypeCd,factListData.factYear,factListData.factMonth,money,factListDate,remark,factListData.bisContId);
				
			}else{
				ymPrompt.close();
			}
		},
		btn:[["保存",'ok'],["取消",'cancel']]
	});
	
}
function appendFacts(){
	var bisProjectId = $('#bisProjectId').val();
	var layOutCd = $('#layOutCd').val();
	var layOutCdList =  $("#layOutCdList").val()
	var currDetailName =  $("#layOutCdList").text(); 
	var chargeTypeCd = $('#chargeTypeCd').val();
	var factYear = $('#year').val();
	var factMonth = $('#month').val();
	
	$('#welcom').hide();
	var data = {bisProjectId:bisProjectId,layOutCd:layOutCd,chargeTypeCd:chargeTypeCd,factYear:factYear,factMonth:factMonth};
	if($("#addContent").css("display")=="none"){
		var url = _ctx+'/bis/bis-fact!inputs.action';
		$.post(url,data,function(result){
			$('#addContent').html(result).show();
			initInputInfo();
			
		});
		//currLayoutLabel = 'layOutCdListInput';
		//$('#detailPanel').hide();
	}else{
		$("#addContent").html('').hide();
	}
}
//bisProjectId,layOutCdList,bisStoreId,chargeTypeCd,factYear,factMonth,money};
function saveFact(layOutCd,bisProjectId,layOutCdList,bisStore,chargeTypeCd,factYear,factMonth,money,factDate,remark,bisContId){
	var url ;
	switch(layOutCd){
	case layOutCdStore:{
		url = _ctx+'/bis/bis-fact!entering2Store.action';
		break;}
	case layOutCdFlat:{
		url=_ctx+'/bis/bis-fact!entering2Flat.action';
		break;}
	case layOutCdMulti:{
		url=_ctx+'/bis/bis-fact!entering2Multi.action';
		break;}
	}
	var flag  = validateCharge(chargeTypeCd,factYear,factMonth);
	if(!flag){
		alert('年、月必填');
		return;
	}
	var data = {bisProjectId:bisProjectId,layOutCdList:layOutCdList,bisStoreId:bisStore,chargeTypeCd:chargeTypeCd,factYear:factYear,factMonth:factMonth,money:money,factDate:factDate,remark:remark,bisContId:bisContId};
	if(data.factDate==''||data.factDate=='null'){
		alert('实收日期必填');
		return;
	}
	if(data.money==''||data.money=='null'){
		alert('实收金额必填');
		return;
	}
	$.post(url,data,function(result){
		ymPrompt.close();
			alert('保存成功');
	       	$('#addContent').hide();
	});
	
}
function initInputInfo(){
	//费用类别、业态、年、月由struts自动加载
	//记忆搜索所选业态对应的明细标签
	//$('#currDetailLabel').html($('#detailLabel').html());

	//记忆所选项目
	$('#bisProjectNameInput').val($('#bisProjectName').val());
	$('#bisProjectIdInput').val($('#bisProjectIdFact').val());
	//注册项目选择按钮
	//$('#bisProjectNameInput').onSelect({muti:false});
	changlayOutDetail2();
	//记忆所选业态明细
	//$("#layOutCdListInput").find("option[value='"+currDetail+"']").attr("selected","true");
	loadValidate();
}

function factPass(bisFactId){
	var url = _ctx+"/bis/bis-fact!pass.action";
	$.post(url,{id:bisFactId},function(result){
		if(result!='success'){
			alert('操作失败');
		}else{
			alert('操作成功');
			reloadDetail();
		}
	});
	ymPromptClose();
}
function factReject(bisFactId){
	if (confirm("确定要驳回吗？")){
		var url = _ctx+"/bis/bis-fact!reject.action";
		$.post(url,{id:bisFactId},function(result){
			if(result!='success'){
				alert('操作失败');
			}else{
				alert('操作成功');
				reloadDetail();
			}
			ymPromptClose();
		});
	}else {
		return false;
	}
}
function factRemove(bisFactId){
	if (confirm("确定要删除吗？")){
	var url = _ctx+"/bis/bis-fact!delete.action";
	$.post(url,{id:bisFactId},function(result){
		if(result!='success'){
			alert('操作失败');
		}else{
			reloadDetail();
		}
		ymPromptClose();
	});
	}else {
		return false;
	}
}
function reloadDetail(){
	$('#factoptip').html('').fadeOut('slow');
	$('#detailPanel').show();
	$.post(currUrl,currData,function(result){
		$('#detailFor').html(result).show();
		adjustHeight();
	});
}
/**
 * 修改实收
 */
function factModify(bisFactId){
	var url = _ctx+"/bis/bis-fact!update.action";
	var oldmoney = $('#oldmoney').val();
	var chargeTypeCd = $('#chargeTypeEdit').val();
	var newmoney = $('#newmoney').val();
	var year = $('#eyear').val();
	var month = $('#emonth').val();
	var factDate = $('#factEDate').val();
	var remark = $('#eremark').val();
	var flag  = validateCharge(chargeTypeCd,year,month);
	if(!flag){
		alert('年、月必填');
		return;
	}
	var reg = /^([0-9]{0,9})?(.[0-9]{0,2})?$/;
	if(!reg.test(newmoney)){
		alert('金额需少于10位');
		return ;
	}
	if(factDate==''||factDate=='null'){
		alert('实收日期必填');
		return;
	}
	$.post(url,{id:bisFactId,money:newmoney,factYear:year,factMonth:month,factDate:factDate,remark:remark},function(result){
		reloadDetail();
		ymPromptClose();
	});
	 $('#newmoney').val('');
}
function fact2Update(bisFactId,money){
	$('#bisFactId').val(bisFactId);
	$('#oldmoney').val(money);
	
	$('#dd').dialog('open');
}
//加载校验
function loadValidate(){
		$.formValidator.initConfig({formid:"inputForm",onerror:function(){alert('请输入必填项');}});
		//实收金额
		$("#money").formValidator({onshow:"请输入金额(如：10.00)"}).regexValidator({regexp:"money",datatype:"enum",onerror:"金额格式不正确"});
}
//附件管理
function openAttachment(title,entityId){
	 if("新增附件管理"==title){
		 cur_entityId = null;
	 }else{
		 cur_entityId = entityId;
	 }
	 ymPrompt.win({
		 message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=bisFact&filterType=image|office&bizEntityName=BisFact",
		 width:500,
		 height:300,
		 title:title,
		 iframe:true,
		 afterShow : function() {},
		 handler : function(btn) {
			if(btn=='close') {
				//validateFlat(entityId,domId,attachFlgId);
			}
		 },
		 btn:[["完成",'close']]
	 });
} 

function getCurDate(){

	var curData = new Date();
	return curData.format("yyyy-MM-dd");
}
function getNextMonth(){

	var curData = new Date();
	return setKEEPENDDATE(curData.format("yyyy-MM-dd"),1);
}
function setKEEPENDDATE(str,p){
	var s;
	var days=0;
	var date = new Date(str.substring(0,4),
	str.substring(5,7)-1,
	str.substring(8,10));
	for(var i=0;i<p;i++){
	days=GetMonthDayCount(date);
	date.setDate(date.getDate()+days);
	}
	s = date.getFullYear() + "-";            //取年份
	s += date.getMonth()+1 + "-"; //取月份
	s += date.getDate();  //取日期
	return s;
	}
//   返回某个日期对应的月份的天数
function   GetMonthDayCount(date)
	{
	var m=date.getMonth();
	if(m==12){
	m=1;
	}else{
	m+=1;
	}
	switch(m)
	{
	case   1:case   3:case   5:case   7:case   8:case   10:case   12:
	return   31;
	case   4:case   6:case   9:case   11:
	return   30;
	}
	//feb:
	date=new   Date(date);
	var   lastd=28;
	date.setDate(29);
	while(date.getMonth()==1)
	{
	lastd++;
	AddDays(date,1);
	}
	return   lastd;
}

//   增加天
function   AddDays(date,value)
{
	date.setDate(date.getDate()+value);
}
//点击全选按钮
function checkedAll(flag){
	$("#editTable :input[type=checkbox]").attr("checked",flag);
}
//打开详细内容
function scheClick(scheId){
}
function doUpdateAll(op){
	var url ;
	var param='';
	var success=0;
	if(op='1'){
		//pass
		url = "${ctx}/bis/bis-fact!passAll.action";
		$("#editTable :input[type=checkbox]").each(function (){
			var statusCd= $(this).attr("statusCd");
			var id=$(this).attr("ids");
			var check = $(this).attr("checked");
			if(check){
				if(statusCd == '0'){success++;	param =param+ id+',';	}
			}
		});
	} 
	if(success>0){
	
		if (confirm("确定要通过"+success+"条实收?")){
			$.post(url,{bisFactIds:param},function(result){
				reloadDetail();
			});
		}else {
			return false;
		}
	}
}

function doInsertMul(op){
	var url ;
	var param='';
	var success=0;
	var tmp = new Array();
	$(':tr[class=mainTr]').each(function(){
		var ch = $(this).find(':input[type=checkbox]').attr("checked");
		if(ch){
			var id = $(this).attr('id').replace('main_','');
			tmp.push(AllFactMust[id]);
		}else{
			
		}
	});
	TmpFactMust = tmp;
	if(op='1'){
		//pass
		url = "${ctx}/bis/bis-fact!inputs.action";
		$('#detailFor').mask('自动生成实收..');
		var param = {'_inserted':TmpFactMust};
		$.post(url,{_easy_grid:Convert.ToJSONString(param)},function(result){
			$('#detailFor').html(result).unmask();
			adjustHeight();
			//提示
			$('#factoptip').html('正在自动生成实收').show();
		});
	} 
}
function doSaveAll(op){
	var url ;
	TmpFactMust = new Array();
	$('#editTable').find('.insertr').each(function(){
		if($(this).find(':input[type=checkbox]').attr("checked") ){
			var factv = new FactVo();
			$(this).find(':input').each(function(){
				var name = $(this).attr('name');
				if(name!='')
				factv[name]= $(this).val();
			});
			TmpFactMust.push(factv);
		}
	});
	var result={'_inserted':TmpFactMust};
	var param={'_easy_grid':Convert.ToJSONString(result)};
	if(op='1'){
		//pass
		url = "${ctx}/bis/bis-fact!saveBatch.action";
		if (confirm("确定要新增"+TmpFactMust.length+"条实收?")){
			$.post(url,param,function(result){
				if(result=='success')
					$('#factoptip').html('保存成功').fadeOut('slow');
					reloadDetail();
			});
		}
	} 
}
function addItem(){

	for(var i =0;i<TmpFactMust.length;i++){
		var jObj = TmpFactMust[i];
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).addClass('insertr');
		$(trApprover_new).show();
		$(trApprover_new).find(":div[class=ellipsisDiv_full]").each(function(i){
			var _this = this;
			for(var o in jObj){
				if($(_this).attr("name")){
					if($(_this).attr("name").indexOf(o)!=-1) {
						$(_this).html(jObj[o]);
					}
				}
			}
		});
		$(trApprover_new).find(":input").each(function(i){
			var _this = this;
			for(var o in jObj){
				if($(_this).attr("name")){
					if($(_this).attr("name").indexOf(o)!=-1) {
						$(_this).val(jObj[o]);
					}
				}
			}
			if($(_this).attr("name").indexOf("money")!=-1) {
				$(_this).val(jObj.mustMoney);
				$(_this).attr('onblur',"formatNumber1($(this))");
			}
			if($(_this).attr("name").indexOf("factDate")!=-1) {
				$(_this).val(getCurDate());
				$(_this).attr('onfocus',"WdatePicker()");
			}
		});
		$(".header").after(trApprover_new);
	}
	//$("#tr_n").remove();
}
//按键时若存在默认字符,清空
function clearInput(_this){
		$(_this).val('');
		$(_this).css('color','#333333');
}
//导入实收
function importFactFile(){
$('#importBisProjectId').val($('#bisProjectIdFact').val());
if(isEmpty($("#importFact").val())) {
		 alert("请先选择要导入的文件");
		 $("#importFact").focus();
		 return false;
	 }
	// TB_showMaskLayer("正在导入...");
	 $("#importFactForm").ajaxSubmit(function(result){
		 alert('导入成功');
	 }); 
}


--></script>

</body>
</html>

