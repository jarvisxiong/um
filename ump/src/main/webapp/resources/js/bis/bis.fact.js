

//显示租户、公寓、多径的空间id
var currLayoutLabel='layOutCdList_v0';

var ifFactInputSaving = false;

// 根据权限，处理项目 、费用业态数据
var userProjectId;// 所在项目
var layOutCdStore ;
var layOutCdFlat ;
var layOutCdMulti ;
id = '<%=SpringSecurityUtils.getCurrentUiid()%>';
layOutCdStore='<%=DictContants.BIS_CONT_TYPE_STORE%>';
layOutCdFlat='<%=DictContants.BIS_CONT_TYPE_FLAT%>';
layOutCdmULTI='<%=DictContants.BIS_CONT_TYPE_MULTI%>';
var chargeType02;
var chargeType03;
var chargeType38;
var search;
var searchAll;
var checkDel;
var newUpdateStore;
var newUpdateFlat;
var newUpdateMulti;
var reportDateStart;
var reportDateEnd;

/**
 * 封装批量生产应收的信息：根据页面checkbox选中的应收新增实收
 * 
 * @param ids
 * @param bisTenantId
 * @param storeNos
 * @param bisContId
 * @param bisProjectId
 * @param chargeTypeCd
 * @param chargeTypeCdName
 * @param mustMoney
 * @param year
 * @param month
 * @param connName
 * @param factDate
 * @param bisShopId
 * @returns {FactMust}
 */
function FactMust(ids,bisTenantId,storeNos,bisContId,bisProjectId,chargeTypeCd,chargeTypeCdName,mustMoney,year,month,connName,factDate,bisShopId){
	this.id = ids;
	this.ids = ids;
	this.bisTenantId	= bisTenantId;
	this.storeNo	= storeNos;
	this.bisStoreId	= storeNos;
	this.bisProjectId	= bisProjectId;
	this.bisContId	= bisContId;
	this.chargeTypeCd	= chargeTypeCd;
	this.bisProjectId	= bisProjectId;
	this.chargeTypeCdName	= chargeTypeCdName;
	this.mustMoney	= mustMoney;
	this.factYear	= year;
	this.factMonth	= month;
	this.connName	= connName;
	this.factDate	= factDate;
	this.bisShopId=bisShopId;
}
/**
 * 封住实收搜索条件
 * 
 * @returns {FactVo}
 */
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
	this.bisContId='';
	this.overdue='';// 欠费状态
	this.mustOrFact='';// 搜索维度 应收2 ； 实收 1 ； 欠费 3 ;预收 4
}
$(function(){
	
	if($("#bisProjectName").val()=='') {
		$("#bisProjectName").val("--选择项目--");
	}
 if (isProjectBusinessCompany == "false") {
        $('#bisProjectName').onSelect({
            muti:false
        });
    }
});

/**
 * 显示高级搜索界面
 */
function showSeniorSearch() {
	if($("#seniorSearchDiv").css("display")=="none"){
		$("#btnSeniorSearch").addClass("quickSeniorHover");
		$("#seniorSearchDiv").show();
	}else{
		$("#btnSeniorSearch").removeClass("quickSeniorHover");
		$("#seniorSearchDiv").hide();
	}
	
	//更新状态
	$("#btn_autoIncome").attr("name","save");
	autoChange();
}
/**
 * 关闭高级搜索界面
 */
function closeSeniorSearch() {
	$("#btnSeniorSearch").removeClass("quickSeniorHover");
	$("#seniorSearchDiv").hide();
}
/**
 * 高级搜索事件
 */
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
// 全屏
function clkFullScreen(fromBisProjectId) {
	var bisProjectId = $("#bisProjectId").val();
	if(isEmpty(bisProjectId)){
		bisProjectId = fromBisProjectId;
	}
	var url;
	if(isEmpty(bisProjectId)) {
		url = _ctx+"/bis/bis-manage!layout.action?module=";
	} else {
		url = _ctx+"/bis/bis-manage!layout.action?bisProjectId="+bisProjectId+"&module=";
	}
	
	window.open(url+"3");
}
isEmpty = function (str) {
	return (typeof (str) === "undefined" || str === null || str ==='undefined' || (str.length === 0));
};
// 业态变量：商铺、公寓、多径
var currlayOutCd;
function getCurrlayOutCd(){
	currlayOutCd = $('#layOutCd').val();
	if(currlayOutCd==''){
		currlayOutCd = layOutCdStore;
		
	}
	return currlayOutCd;
}
/**
 * 用途：搜索实收内容 设置当前业态：商铺、公寓、多径
 * 
 * @param layOutCd
 */
function setCurrlayOutCd(layOutCd){
	$('#layOutCd').val(layOutCd);
	currlayOutCd=layOutCd;
}
/**
 * 导出实收批量录入模板信息
 * 
 * @returns {Boolean}
 */
function doExport(){
	var factMonth = $('#month').val();
	var factYear = $('#year').val();
	var layOutCd = $('#layOutCd').val();
	if(isEmpty(factMonth)) {
		alert("请选择收费年月");
		return false;
	}
	if(isEmpty(factYear)) {
		alert("请选择收费年月");
		return false;
	}
	if (confirm("为避免现金流问题，系统仅导出：已存在应收，且无对应实收的租户记录")){
		var bisProjectId = $('#bisProjectIdFact').val();
		var url =_ctx+"/bis/bis-fact!exportFact.action?bisProjectId="+bisProjectId+"&mustOrFact=2&contLayOutCd="+layOutCd+"&maxMoney=0&factYear="+factYear+"&factMonth="+factMonth;
		location.href = url;
	}
}

/**
 * 删除实收信息
 */
function delFact(id){
	var url = _ctx+'/bis/bis-fact!delete.action';
	$.post(url,{id:id},function(result){
		
	});
}
/**
 * 保存实收
 */
function save(form){
	var url ;
	var detail;
	var bisStore;
	var layOutCd =$('#layOutCdInput').val();
	// layOutCd ：商铺、公寓、多径
	switch(layOutCd){
	case layOutCdStore:{
		url = _ctx+'/bis/bis-fact!entering2Store.action';
		detail='租户';
		bisStore = $("#layOutCdListInput").text();
		break;}
	case layOutCdFlat:{
		detail='公寓';
		url=_ctx+'/bis/bis-fact!entering2Flat.action';
		break;}
	case layOutCdMulti:{
		detail='多径项目';
		url=_ctx+'/bis/bis-fact!entering2Multi.action';
		break;}
	}
	var bisProjectId = $('#bisProjectIdInput').val();
	// 根据layOutCd的不同，记录租户、公寓或多径等id信息
	var layOutCdList = $("#layOutCdListInput").val();
	var bisContId = $("#bisContId").val();
	
	var chargeTypeCd = $('#chargeTypeCdInput').val();
	var factYear = $('#factYear').val();
	var factMonth = $('#factMonth').val();
	var money = $('#money').val();
	var factDate = $('#factDate').val();
	var remark = $('#remark').val();
	var data = {bisProjectId:bisProjectId,layOutCdList:layOutCdList,bisStoreId:bisStore,chargeTypeCd:chargeTypeCd,factYear:factYear,factMonth:factMonth,money:money,factDate:factDate,bisContId:bisContId,remark:remark};
	if(money==''){
		alert('实收金额不能为空');
		return;
	}
	if(layOutCd==layOutCdFlat){// 公寓
		if(""==bisContId){
			if(layOutCdList==''||layOutCdList=='null'){
				alert(detail+'必填');
				return;
			}
		}
	}else if(layOutCd==layOutCdMulti){// 多经
		if(""==bisContId){
			if(layOutCdList==''||layOutCdList=='null'){
				alert(detail+'必填');
				return;
			}
		}
	}else{
		if(layOutCdList==''||layOutCdList=='null'){
			alert(detail+'必填');
			return;
		}
	}
	if(factDate==''||factDate=='null'){
		alert('实收日期必填');
		return;
	}
	if(chargeTypeCd==''||chargeTypeCd=='null'){
		alert('费用类别必填');
		return;
	}
	var flag  = validateCharge(chargeTypeCd,factYear,factMonth);
	if(!flag){
		alert('年、月必填');
		return;
	}
	$.post(url,data,function(result){
		if(result=='success'){
	       	$('#addContent').hide();
       	}else{
       		alert('保存失败');
       	}
	});
	reloadDetail();
}
/**
 * 隐藏新增内容
 */
function cance(){
	$('#addContent').hide();
}

/**
 * 将搜索条件初始入新增页面
 */
function initaLayoutCdList(){
	var currDetail = $('#currDetail').val();
	if(currDetail){
		var currDetailName = $('#currDetailName').val();
		var d = '<option value="'+currDetail+'">'+currDetailName+'<option>';
		$('#layOutCdListInput').append(d);
	}
}


/**
 * 营业外收入
 */
function toPayIncome(){
	
	$('#dimension').val('6');
	var url  = _ctx+'/bis/bis-fact!list.action';
	var year = getCurDate().substr(0,4);
	var month = getCurDate().substr(5,2);
	$('#year').val(year);
	$('#month').val(month);
	$('#searchForm').attr('action',url);
	$('#searchForm').submit();

	adjustHeight();
}
/**
 * 预算明细
 */
function toBudget(){
	$('#dimension').val('7');
	var url= _ctx+'/bis/bis-fact!list.action';
	var year = getCurDate().substr(0,4);
	var month = getCurDate().substr(5,2);
	$('#year').val(year);
	$('#month').val(month);
	$('#searchForm').attr('action',url);
	$('#searchForm').submit();

	adjustHeight();
}

/**
 * 公寓收费记录 add by liuzhihui 2012-05-29
 */
function toGysfRecord(){
	var url  = _ctx+'/bis/bis-fact!list.action';
	$('#dimension').val('9');
	$('#searchForm').attr('action',url);
	$('#searchForm').submit();
	adjustHeight();
}

/**
 * 实收明细
 */
function actInCome(){
	$('#dimension').val('8');
	var url= _ctx+'/bis/bis-fact!list.action';
	var year = getCurDate().substr(0,4);
	var month = getCurDate().substr(5,2);
	$('#year').val(year);
	$('#month').val(month);
	$('#searchForm').attr('action',url);
	$('#searchForm').submit();
	
	adjustHeight();
}
/**
 * 初始化预收页面信息
 */
function initYuShou(){
	bindMenuEvent();
	$('#advances').addClass('bis_fact_click');
	$('#welcom').hide();
	$("#seniorHeadTool").hide();
	// 单击实收记录
	
	$('#creatorQ').userSelect({ muti:false  }); 
	$('#checkUserCdQ').userSelect({ muti:false }); 
	// 回车搜索
	click_on_return('btn_search');
	search4YuShou();
	
	$('#search_fact_yu_s').find('li').bind('click',
			function(){
		if($(this).attr('valu')!='n'){
			
			$(this).css('color','red').siblings().css('color','#006FB6');
			$('#statusCd').val($(this).attr('valu'));
			search4YuShou();
		} 
	}).bind('mouseover',function(){	if($(this).attr('valu')!='n'){	$(this).css('text-decoration','underline');	}}).bind('mouseout',function(){	if($(this).attr('valu')!='n')	$(this).css('text-decoration','none');});
	
}
/**
 * 绑定页面菜单事件
 * 
 * @return
 */
function bindMenuEvent(){
	$('#bis_rpt').find('li').bind('click',function(){
				$('.must_dime').show();
				$(this).addClass('bis_fact_click').siblings().removeClass('bis_fact_click');
				if((this).id=='fact'){
                    // toggleShowHide({'.no_advances_dime':'show','#search_due':'hide','.must_dime':'hide','.fact_dime':'show','.advances_dime':'hide'});
					$('#dimension').val('1');
				}	else if((this).id=='must'){
					// toggleShowHide({'.no_advances_dime':'show','.must_dime':'show','.fact_dime':'hide','#search_due':'show','.advances_dime':'hide'});
					// 去掉默认应收截止日期$('#mustInEnd').val(getNextMonth());
					$('#dimension').val('2');
				}	else if((this).id=='overdue'){
					// toggleShowHide({'.no_advances_dime':'show','.must_dime':'show','.fact_dime':'hide','#search_due':'hide','.advances_dime':'hide'});
					// 去掉默认应收截止日期$('#mustInEnd').val(getCurDate());
					$('#dimension').val('3');
				}	else if((this).id=='advances'){// 预收维度
					// toggleShowHide({'.advances_dime':'show','.no_advances_dime':'hide','.must_dime':'hide','.fact_dime':'hide',});
					search4YuShou();
					return;
				}	else if((this).id=='payNoti'){// 缴费通知
					// toggleShowHide({'.advances_dime':'show','.no_advances_dime':'hide','.must_dime':'hide','.fact_dime':'hide',});
					toPayNoti();
					return;
				} else if((this).id=='payIncome'){// 营业外收入
				
					toPayIncome();
					return;
				} else if((this).id=='budget'){// 预算明细
					toBudget();
					return;
				}else if((this).id=='actInCome'){// 新 实收明细
					actInCome();
					return;
				}else if((this).id=='gysfRecord'){// 公寓收费记录 -add by liuzhihui
					toGysfRecord();
					return;
				}
				initSearchFactCss();
				var url = _ctx+'/bis/bis-fact!list.action';
				$('#searchForm').attr('action',url).submit();
			}		
		);
	
}
/**
 * 预收搜索
 */
function search4YuShou(){
	var param = new FactVo();
	$('.search').each(function(){
			var name = $(this).attr('name');
			if(name!='')
			param.name=$(this).val();
	});
	param.bisProjectId = $('#bisProjectIdFact').val();
	param.tenant = $('#tenant4YuS').val();
	param.statusCd = $('#statusCd').val();
	var url = _ctx+'/bis/bis-fact-yu-s!searchForStoreYuShou.action';
	$('#searchForm').ajaxSubmit(function(result){
		$('#detailFor').html(result).show().unmask();
		autoHeight();
	});
}

/**
 * 切换至 预收维度
 */
function toFactYuShou(){
	var url  = _ctx+'/bis/bis-fact!list.action';
	$('#dimension').val('4');
	$('#searchForm').attr('action',url);
	$('#searchForm').submit();
	adjustHeight();
}


/**
 * 新增预收
 */
function inputFactYuShou(){
	var url = _ctx+'/bis/bis-fact!inputYuShou.action';
	if($("#addContent").css("display")=="none"){
		$.post(url,{bisProjectId:currProjectId},function(result){
			$('#addContent').html(result).show();
			var bis_project_id = $('#bisProjectIdFact').val();
			quickSTenant4YuSHou(bis_project_id);
		});
	}else{
		$("#addContent").html('').hide();
	}
}

/**
 * 预收审核
 */
function doUpdateYuSAll(op){
	var url ;
	var param='';
	var success=0;
	if(op='1'){
		// pass
		url = _ctx+"/bis/bis-fact-yu-s!passAll.action";
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
// =============================================================================
// 商业系统-商业管理系统-收费明细-预售明细
// =============================================================================

/**
 * 新增预售明细保存操作
 * 
 * @author xuzb
 * @date 2012-3-16
 * @return
 */
function saveAdvancesDetail() {
	if (validatorAdvances()) {
		$("#frmAddAdvance").ajaxSubmit({
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			success : function(result) {
				if(result=='success'){
					alert('新增预收明细成功!');
				}else {
					alert('新增预收明细失败!');
				}
				toFactYuShou();
			}
		});
	}
	return false;
}
/**
 * 新增预售明细保存操作-数据校验
 * 
 * @author xuzb
 * @date 2012-3-16
 * @return
 */
function validatorAdvances() {
	var bisTenant4Ys = $("#bisTenant4Ys").val();
	if (isEmpty(bisTenant4Ys)) {
		alert("租户/商铺不能为空!");
		return false;
	}
	var factDate = $("#factDate").val();
	if (isEmpty(factDate)) {
		alert("实收日期不能为空!");
		return false;
	}
	var money = $("#money").val();
	if (isEmpty(money)) {
		alert("实收金额必能为空!");
		return false;
	}
	return true;
}
/**
 * 加载预收明细详细信息
 * 
 * @author xuzb
 * @date 2012-3-16
 * @param id
 * @return
 */
function loadAdvancesDetail(bisFactId){
	var url=_ctx+'/bis/bis-fact-yu-s!edit.action';
	var bisProjectId = $('#bisProjectIdFact').val();
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='selectTypeDiv'><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
		width : 450,
		height :255,
		title : "编辑",
		afterShow : function() {
			$.post(url, {id:bisFactId,bisProjectId:bisProjectId}, function(result) {
				$("#selectTypeDiv").html(result);
				quickSTenant4YuSHou(bisProjectId);
			});
		},
		handler : function(btn){
			ymPrompt.close();
		},
		closeBtn:true,
		btn:[]
	});
}
/**
 * 预收模块：搜索租户
 * 
 * @param bis_project_id
 */
function quickSTenant4YuSHou(bis_project_id){
	$('#bisTenant4Ys').quickSearch(
			_ctx+'/bis/bis-tenant!quickSearchTenant.action?type=shop',
			['nameCn'],
			{bisTenantId:'quickSearchFieldId',nameCn:'quickSearchField'},
			{bisProjectId:bis_project_id},
			function(result){
				// refreshShopStoreTable(result);
				var bisTenantId =  result.attr('bisTenantId');
				var tenantName = result.attr('nameCn');
				$('#bisTenantId').val(bisTenantId);
				$('#bisTenant4Ys').val(tenantName);
			}
		);
}
/**
 * 更新预收
 */
function updateAdvances(){
	$('#frmUpdteAdvances').ajaxSubmit(function(result) {
		if(result=='success'){
		}else {
			alert('操作不成功');
		}
		search4YuShou();
	});
	ymPromptClose();
}
/**
 * 删除预收
 */
function removeYuShou(id){
	if (confirm("确定要删除吗？")){
		var url = _ctx+'/bis/bis-fact-yu-s!delete.action';
		$.post(url,{id:id},function(result){
			search4YuShou();
		});
		ymPromptClose();
	}else{
		return false;
	}
}
/**
 * 预收单条审核
 * 
 * @param id
 */
function passYuShou(id){
	var url = _ctx+'/bis/bis-fact-yu-s!pass.action';
	$.post(url,{id:id},function(result){
		search4YuShou();
	});
	ymPromptClose();
}
/**
 * 预收驳回
 * 
 * @param id
 */
function rejectYuShou(id){
	var hasDichong = $('#hasDichong').val()==''?0:parseInt($('#hasDichong').val());
	if(hasDichong>0){
		alert("对不起，抵充后不允许作‘驳回’操作");
		//ymPromptClose();
		return ;
	}
	var url = _ctx+'/bis/bis-fact-yu-s!reject.action';
	$.post(url,{id:id},function(result){
		search4YuShou();
	});
	ymPromptClose();
}
/**
 * 选择租户
 * 
 * @param layOutCdId
 *            业态select控件名称：商铺、公寓、多径
 * @param bisProjectIdFact
 *            项目id
 * @param tenantInfo
 *            租户搜索条件控件名称
 */
function selectTenant(layOutCdId,bisProjectIdFact,tenantInfo){
	var bisProjectId = $('#'+bisProjectIdFact).val();
	var layOutCd = $('#'+layOutCdId).val();
	if(bisProjectId){
		if(layOutCd!='1')return;
		if(bisProjectId =='')
			alert('请选择项目');
		var url = _ctx+'/bis/bis-tenant!searchTenant.action?bisProjectId='+bisProjectId;
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
				var url = _ctx+'/bis/bis-tenant!searchTenant.action';
				$.post(url, {bisProjectId:bisProjectId}, function(result) {
					$("#selectTypeDiv").html(result);
					quich_search_tenant(bisProjectIdFact,tenantInfo);
				});
			},
			handler : function(btn){
				if(btn=='ok'){
					bisTenantId =  getTenantId();
					var tenantName = getTenantName();
					$("#"+tenantInfo).empty();
					$("#"+tenantInfo).append("<option value='"+bisTenantId+"'>"+tenantName+"</option>"); 
					$("#"+tenantInfo+" option[value="+bisTenantId+"]").attr("selected", true); 
					setTitle(tenantInfo,tenantName);
					
				}
				ymPrompt.close();
			},
			btn:[["取消",'cancel']]
		});
	}
}
/**
 * 显示或隐藏控件
 * 
 * @param op
 *            例如：{'.no_advances_dime':'show'}
 *            ，执行效果：显示class名为".no_advances_dime“的div等容器
 * @returns
 */
function toggleShowHide(op){
	 switch(typeof(op)) 
    {
        case 'object':
          	 if (op instanceof Array)  {
          		 for (var i = 0, len = op.length; i < len; i++) {
           		 toggleShowHide(op[i]);
                   }
          	 }else{
           		 
	             for (var a in op){
	            	 switch(op[a]){
	            	 case 	'show'	: 	$(a).show();break;
	            	 case	'hide'	:	$(a).hide(); break;
           		 }
            	}
            }
            return ;
        default:
            return op.toString();
        
    }
}
/**
 * 调整高度 ：计算搜索列表容器高度，设置滚动条
 */
function adjustHeight() {
	autoHeight();
	/*
	var height = $(window).height()-128-14;
	$("#tableDiv").css('height',height+'px');
	if($("#floorDiv").height() > height || $("#baseInfoDiv").height() > height) {
		$("#footerDiv").addClass("followed_div");
	} else {
		$("#footerDiv").addClass("bottom_bar");
	}
	*/
}
/**
 * 初始化应收、欠费、实收、预收维度切换
 */
function layOut(){
	$('#bis_rpt').find('li').bind('click',
			function(){

				// $('.must_dime').show();
				// 如果点击维度与当前维度相同，退出
				if($(this).attr('class')=='bis_fact_click'){
					return;
				}
				// 渲染切换维度标签样式
				$(this).addClass('bis_fact_click').siblings().removeClass('bis_fact_click');
				// 根据当前li标签id信息切换维度
				if($(this).attr('id')=='fact'){// 实收维度
					$('#dimension').val('1');
				}	else if($(this).attr('id')=='actInCome'){// 新实收明细
					$('#dimension').val('8');
				}	else if($(this).attr('id')=='must'){// 应收维度
					$('#dimension').val('2');
				}	else if($(this).attr('id')=='overdue'){// 欠费维度
					$('#dimension').val('3');
				}	else if($(this).attr('id')=='advances'){// 预收维度
					toFactYuShou();
					return;
				}	else if($(this).attr('id')=='payNoti'){// 缴费通知
					toPayNoti();
					return;
				} 	else if($(this).attr('id')=='payIncome'){// 营业外收入
					toPayIncome();
					return;
				}   else if($(this).attr('id')=='budget'){// 预算
					toBudget();
					return;
				}   else if($(this).attr('id')=='gysfRecord'){// 公寓收费记录 add by liuzhihui 2012-05-29
					toGysfRecord();
					return;
				}
				initSearchFactCss();
				var url = _ctx+'/bis/bis-fact!list.action?shopStoreName=';
				$('#searchForm').submit();
			}		
		);
}
/**
 * 注册选中应收或实收维度方法 注册只看欠费事件，渲染样式
 */
function init(){
	// 设置默认应收维度应收截止日期
	
	// 去掉：默认应收截止日期$('#mustInEnd').val(getNextMonth());
	layOut();
	// 默认进入实收维度
	if(dimension==''){dimension=3;}
	
	if(dimension=='2'){
 		// 应收维度
		$('#must').addClass('bis_fact_click');
	}else 	if(dimension=='3'){
		$('#overdue').addClass('bis_fact_click');
	}else 	if(dimension=='1'){
		// 实收维度
		$('#year').val(getCurYear());
		$('#month').val(getCurMonth());
		$('#fact').addClass('bis_fact_click');
	}else if(dimension=='4'){
		$('#advances').addClass('bis_fact_click');
	}else if(dimension=='6'){
		$('#payIncome').addClass('bis_fact_click');
	}else if(dimension=='9'){
		$('#gysfRecord').addClass('bis_fact_click');
	}
	
	// 注册实收快捷搜索单击事件：全部、审核驳回、等待审核、审核通过
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
	
	// 注册应收快捷搜索单击事件 ： 全部 、已收齐、未收齐
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
	
	// 隐藏高级搜索
	$("#seniorHeadTool").hide();
	// 注册高级搜索录入人、审核人事件
	$('#creatorQ').userSelect({ muti:false  }); 
	$('#checkUserCdQ').userSelect({ muti:false }); 
	// 注册点击回车，执行搜索事件
	click_on_return('btn_search');
}
/**
 * 初始化 样式
 */
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

/**
 * 渲染高级搜索内容
 */
function loadSeniorSearch(){
	var bisProjectId=$('#bisProjectId').val();
	var localLayOutCd = getCurrlayOutCd();
	if(bisProjectId=='')return;
	if(localLayOutCd){}else return;
	
}
/**
 * 为select控件追加option信息
 * 
 * @param selectField
 * @param data
 */
function appendSelect(selectField,data){
	$("#"+selectField).html('');
	appendOption(selectField);
	$.each(data,function(i,n){
		var option = '<option value="'+n.id+'">'+n.text+'</option>';
		 $('#'+selectField).append(option);
	});
}
/**
 * 搜索应收或实收明细信息
 */
function datagridOnClick(bisFactField,bisFactYuSId){

	if(bisFactField==''){
		return ;
	}
	var data = {bisFactId:bisFactField,bisFactYuSId:bisFactYuSId,contLayOutCd:getCurrlayOutCd()};
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
		width : 550,
		height :420,
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
/**
 * 打开抵充页面：废弃
 */
function toFactDeduct(bisFactId,layOutCd){
	ymPrompt.close();
	var data = {id:bisFactId,layOutCd:layOutCd};
	var url = _ctx+'/bis/bis-fact!toDeduct.action';
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
/**
 * 抵充：废弃
 */
function factDeduct(parentId,bisProjectId,chargeTypeCd,factYear,factMonth,money,contLayOutCd,currDetail,bisShopId,bisStoreId){
	var data = {id:parentId,bisProjectId:bisProjectId,chargeTypeCd:chargeTypeCd,factYear:factYear,factMonth:factMonth,money:money,layOutCd:contLayOutCd,currDetail:currDetail,bisShopId:bisShopId,bisStoreId:bisStoreId};
	var url = _ctx+'/bis/bis-fact!deductMoney.action';
	$.post(url,data,function(result){
		var data = result;
		if(data=='error'){
			alert('操作失败');
		}else{
			alert('操作成功，审核抵扣记录之后扣除抵扣值。');
		}
	});
	ymPrompt.close();
}
function ymPromptClose(){
	ymPrompt.close();
}
/**
 * 预加载搜索信息：优先顺序商铺、公寓、多经营 btnId input输入框
 */
function loadDefault(){
	// 从租户跳转到当前页面，初始化租户名称
	if(bisTenantId){
		 $('#layOutCdList_v0').val(bisTenantName);
		 // 应收页面快速搜索 设置全部
		// $('#allnodueli').trigger('click');
	}
	// 根据业态切换搜索条件;
	changlayOutDetail1();
	var year = $('#year').val();
	var month = $('#month').val();
	var reportDateStart = $('#reportDateStart').val();
	var reportDateEnd = $('#reportDateEnd').val();
	var chargeTypeCd = $('#chargeTypeCd').val();
	var obj = new FactVo();
	obj.bisProjectId = currProjectId;
	obj.overdue = '2';
	if(year!='')
		obj.factYear = year;
	if(month!='')
		obj.factMonth = month;
	if(reportDateStart!='')
		obj.reportDateStart = reportDateStart;
	if(reportDateEnd!='')
		obj.reportDateEnd = reportDateEnd;
	if(chargeTypeCd!='')
		obj.chargeTypeCd = chargeTypeCd;
	if(currProjectId!=''&&bisTenantId!=''){
		obj.bisTenantId = bisTenantId;
		obj.contLayOutCd = layOutCdStore;
		getDetail(obj);
		return;
	}
	if(isEmpty($("#layOutCd").val()))
    	obj.contLayOutCd = layOutCdStore;
	else
		obj.contLayOutCd =$("#layOutCd").val();
	if (!isEmpty($("#statusCd").val())) {
		obj.statusCd =$("#statusCd").val();
	}
	getDetail(obj);
}

/**
 * 搜索 ： 应收明细、欠费明细、收费历史记录 3个维度的搜索公用这个搜索方法。 mustOrFact 2 3 1
 * 
 * @param obj
 *            封装搜索条件
 */
function getDetail(obj){
	
	$('#welcom').hide();
	$('#addContent').hide();
	$('#detailPanel').show();
	if(obj.contLayOutCd){
		setCurrlayOutCd(obj.contLayOutCd);
	}
	var	pageNo=1;
	// 应收 实收维度
	var mustOrFact = $('li.bis_fact_click').attr('value');
	obj.mustOrFact = mustOrFact;
	var data = obj;
	currData= obj;
	switch(currlayOutCd){
		case layOutCdStore:{// 商铺
			currUrl=_ctx+'/bis/bis-fact!searchMustForStore.action';
			break;}
		case layOutCdFlat:{// 公寓
			currUrl=_ctx+'/bis/bis-fact!searchMustForFlat.action';
			break;}
		case layOutCdMulti:{// 多经
			currUrl=_ctx+'/bis/bis-fact!searchForMulti.action';
			break;}
	}
	// $('#detailFor').css('height','400px').mask('正在加载数据');
	$('#detailFor').mask('正在加载数据');
	$('#main_search_div1').mask();
	try{
		data.shopStoreName = $("#layOutCdList_v0").val();
		if("搜索商家/商铺"==data.shopStoreName){
			data.shopStoreName = "";
		}
	}catch(e){}
	try{
		if("3"==dimension){
			data.overdue="1";
			try{
				if(!isEmpty(ifFromReport)){
					data.ifFromReport = ifFromReport;
				}
			}catch(e){alert(e);}
		}
	}catch(e){}
	
	// 标志位：等待加载页面之后，再调整页面高度
	var su = '0';
    data.timer=new Date().getTime();
	$.post(currUrl,data,function(result){
		if(result.indexOf('rror')>0){
			result.replace('error','');
			alert("请精确商家或商铺编号搜索条件");
		} 
		$('#detailFor').html(result).show().unmask();
		$('#main_search_div1').unmask();
		adjustHeight();
	});
}

var currData={};
var currUrl;
var currPanel='detailFor';
/**
 * 分页搜索
 * 
 * @param pageNo
 */
function jumpPage(pageNo){
	currData.pageNo = pageNo;
	$.post(currUrl,currData,function(result){
			if(result){
				$("#"+currPanel).html(result).show().unmask();
				$('#main_search_div1').unmask();
				adjustHeight();
			}
	});
}
function submitSearch1(){
	submitSearch('','bisProjectIdFact','chargeTypeCd','layOutCd',currLayoutLabel,'year','month','statusCd');
}
function submitSearch2(){
	submitSearch('','bisProjectIdFact','chargeTypeCd','layOutCd',currLayoutLabel,'year','month',
			'statusCd','creator','checkUserCd','minMonth','maxMonth','minMoney','maxMoney','inDateBegin','inDateEnd','','');
}
// 点击应收、欠费、收费明细等tab的触发事件
function submitSearch3(){
	submitSearch('','bisProjectIdFact','chargeTypeCd','layOutCd',currLayoutLabel,'year','month','statusCd',
			'creator','checkUserCd','minMonth','maxMonth','minMoney','maxMoney','inDateBegin','inDateEnd',
			'reportDateStart','reportDateEnd');
}
function submitSearch4(){
	submitSearch('0','bisProjectIdFact','chargeTypeCd','layOutCd',currLayoutLabel,'year','month','statusCd');
}

/**
 * 商业系统-收费明细搜索|检索
 * 
 * @author xuzb
 * @date 2012-3-15
 * @return
 */
function chargeDetailQuery() {
	
	//alert(reportDateStart);
	//alert('aaaa');
	submitSearch('', 'bisProjectIdFact', 'chargeTypeCd', 'layOutCd', currLayoutLabel, 
			'year', 'month', 'statusCd', 'creator', 'checkUserCd', 'minMonth', 'maxMonth', 
			'minMoney', 'maxMoney', 'inDateBegin', 'inDateEnd','reportDateStart','reportDateEnd');
	//更新状态
	$("#btn_autoIncome").attr("name","save");
	autoChange();
}

/**
 * 搜索
 * 
 * @param overdue
 *            已收齐：0；未收齐 ：1 ；全部 2
 * @param bisProjectId
 * @param chargeTypeCd
 *            费用类别
 * @param layOutCd
 *            业态：商铺 【1】，公寓 【2】，多径 【3】
 * @param layOutCdList
 *            id信息：租户id ，公寓id， 多径id
 * @param year
 *            月
 * @param month
 *            年
 * @param statusCdV
 *            审核状态
 * @param creator
 * @param checkUserCd
 *            审核人
 * @param minMonth
 * @param maxMonth
 * @param minMoney
 * @param maxMoney
 *            应收或实收最大金额
 * @param inDateBegin
 *            应收或实收开始日期
 * @param inDateEnd
 *            应收或实收结束日期
 * @param reportDateStart 实收年月开始
 * @param reportDateEnd 实收年月结束
 */
function submitSearch(overdue,bisProjectId,chargeTypeCd,layOutCd,layOutCdList,year,month,
		statusCdV,creator,checkUserCd,minMonth,maxMonth,minMoney,maxMoney,inDateBegin,inDateEnd,reportDateStart,reportDateEnd){
	var obj = processSearchConditions(overdue,bisProjectId,chargeTypeCd,layOutCd,layOutCdList,year,month,
			statusCdV,creator,checkUserCd,minMonth,maxMonth,minMoney,maxMoney,inDateBegin,inDateEnd,reportDateStart,reportDateEnd);
	
	getDetail(obj);
}
/**
 * 处理搜索条件
 */
function processSearchConditions(overdue,bisProjectId,chargeTypeCd,layOutCd,layOutCdList,year,month,
		statusCdV,creator,checkUserCd,minMonth,maxMonth,minMoney,maxMoney,inDateBegin,inDateEnd,reportDateStart,reportDateEnd){
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
	obj.reportDateStart=$('#'+reportDateStart).val();
	obj.reportDateEnd=$('#'+reportDateEnd).val();
	
	obj.factInBegin = $('#factInBegin').val();
	obj.factInEnd = $('#factInEnd').val();
	obj.mustInBegin = $('#mustInBegin').val();
	obj.mustInEnd = $('#mustInEnd').val();
	obj.buildingNum = $('#flatBuding').val();
	obj.bisContId =$('#bisContId').val();
	$('#search_due').find('li').each(function(result){
		var _this = this;
		if($(_this).css('color')=='red'){
			obj.overdue =$(_this).attr('valu');
		}
	});
	
	setCurrlayOutCd(obj.contLayOutCd);
	var layOutCdValue  = $("#"+layOutCdList).val();// 租户、公寓、多径id
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
	try{
		if("3"==dimension){
			obj.overdue="1";
		}
	}catch(e){}
	return obj;
	
}
// 校验年月是否可为空
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
// 选择租户
var bisTenantId;
// 触发按钮：搜索页面明细控件
function selectDetail1(){
	switch(currlayOutCd){
// case '1':selectTenant('layOutCd','bisProjectIdFact','layOutCdList');break;
		case '1':break;
		case '2':selectFlat('layOutCd','bisProjectIdFact',currLayoutLabel);break;
		case '3':selectMulti('layOutCdList_v1','bisProjectIdFact','layOutCdList_v1');break;
// default:selectTenant('layOutCd','bisProjectIdFact','layOutCdList');break;
		default:break;
	}
}
// 触发按钮：搜索页面高级搜索-明细控件
function selectDetail3(){
	switch(currlayOutCd){
// case
// '1':selectTenant('layOutCdSenior','bisProjectId','layOutCdListSenior');break;
		case '1':break;
		case '2':selectFlat('layOutCdSenior','bisProjectId','layOutCdListSenior');break;
	}
}
// 触发按钮：新增页面明细控件
function selectDetail2(){
		switch(currlayOutCd){
		case '1':selectTenant('layOutCdInput','bisProjectIdFact','layOutCdListInput');break;
		case '2':selectFlat('layOutCdIdInput','bisProjectIdFact','layOutCdListInput');break;
		case '3':selectMulti('layOutCdIdInput','bisProjectIdFact','layOutCdListInput');break;
	}
}
function selectFlat(layOutCdId,bisProjectIdFact,detailLabel,layOutCdList){
	var bisProjectId = $('#'+bisProjectIdFact).val();
	 currLayoutLabel = detailLabel;
	if(bisProjectId){
		if(bisProjectId =='')
			alert('请选择项目');
		var url = _ctx+'/bis/bis-flat!select.action?bisProjectId='+bisProjectId;
		ymPrompt.confirmInfo( {
			icoCls : "",
			autoClose:false,
			message : "<div id='selectTypeDiv' ><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
			width : 300,
			height : 400,
			title : "请选择公寓",
			closeBtn:true,
			afterShow : function() {
				var url = _ctx+'/bis/bis-flat!select.action';
				$.post(url, {bisProjectId:bisProjectId}, function(result) {
					$("#selectTypeDiv").html(result);
				});
			},
			handler : function(btn){
				if(btn=='ok'){
					setFlatNo(getFlatId(),getContId(),getFlatNo());
				}else{
					ymPrompt.close();
				}
			},
			btn:[["确定",'ok'],["取消",'cancel']]
		});
	}
}
//
function selectMulti(layOutCdId,bisProjectIdFact,detailLabel,layOutCdList){
	var bisProjectId = $('#'+bisProjectIdFact).val();
	 currLayoutLabel = detailLabel;
	if(bisProjectId){
		if(bisProjectId =='')
			alert('请选择项目');
		var url = _ctx+'/bis/bis-flat!selectMultiByPrompt.action?bisProjectId='+bisProjectId;
		ymPrompt.confirmInfo( {
			icoCls : "",
			autoClose:false,
			message : "<div id='selectTypeDiv' ><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
			width : 300,
			height : 400,
			title : "请选择多经",
			closeBtn:true,
			afterShow : function() {
				var url = _ctx+'/bis/bis-flat!selectMultiByPrompt.action';
				$.post(url, {bisProjectId:bisProjectId}, function(result) {
					$("#selectTypeDiv").html(result);
				});
			},
			handler : function(btn){
				if(btn=='ok'){
					// setMultiNo(,,getMultiName);
					$("#"+currLayoutLabel).empty();
					if(""!=getContId()){
						$("#bisContId").val(getContId());
						$("#"+currLayoutLabel).append("<option value='"+getContId()+"'>"+getMultiName()+"</option>"); 
						$("#"+currLayoutLabel+" option[value="+getContId()+"]").attr("selected",true); 
						
					}else if(""!=getMultiId()){
						$("#"+currLayoutLabel).append("<option value='"+getMultiId()+"'>"+getMultiName()+"</option>"); 
						$("#"+currLayoutLabel+" option[value="+getMultiId()+"]").attr("selected",true); 
					}else{
						$("#bisContId").val("");
					}
					
					
					// $("#"+currLayoutLabel).append("<option
					// value='"+flatId+"'>"+flatNo+"</option>");
					/*
					 * if(contId){ $("#bisContId").val(contId);
					 * $("#"+currLayoutLabel).append("<option
					 * value=''>"+flatNo+"</option>"); $("#"+currLayoutLabel+"
					 * option[value="+flatNo+"]").attr("selected", true); }else{
					 * $("#"+currLayoutLabel).append("<option
					 * value='"+flatId+"'>"+flatNo+"</option>"); }
					 * 
					 * setTitle(currLayoutLabel,flatNo);
					 */
				
				}
				ymPrompt.close($("#"+layOutCdId).val());
			},
			btn:[["确定",'ok'],["取消",'cancel']]
		});
	}
}

function setFlatNo(flatId,contId,flatNo){
	$("#"+currLayoutLabel).empty();
	// $("#"+currLayoutLabel).append("<option
	// value='"+flatId+"'>"+flatNo+"</option>");


	if(contId){
		$("#bisContId").val(contId);
		$("#"+currLayoutLabel).append("<option value='0'>"+flatNo+"</option>");
	}else{
		$("#"+currLayoutLabel).append("<option value='"+flatId+"'>"+flatNo+"</option>"); 
	}
	$("#"+currLayoutLabel+" option[value="+flatNo+"]").attr("selected", true); 
	setTitle(currLayoutLabel,flatNo);
	ymPrompt.close();
}
function setMultiNo(multiId,contId,multiName){
	
}
function selectTenant(layOutCdId,bisProjectIdFact,layOutCdList){
	var bisProjectId = $('#'+bisProjectIdFact).val();
	var layOutCd = $('#'+layOutCdId).val();
	if(bisProjectId){
		if(layOutCd!='1')return;
		if(bisProjectId =='')
			alert('请选择项目');
		var url = _ctx+'/bis/bis-tenant!searchTenant.action?bisProjectId='+bisProjectId;
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
				var url = _ctx+'/bis/bis-tenant!searchTenant.action';
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
	var url = _ctx+'/bis/bis-cont!loadTenantCont4.action';
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
// 注册快速搜索(模糊匹配:uiid,userName)
function quich_search_tenant(bis_projec_input,layOutCdList){
	var bis_project_id = $('#'+bis_projec_input).val();
	$('#quickSearchShop').quickSearch(
		_ctx+'/bis/bis-tenant!quickSearchTenant.action?type=shop',
		['nameCn','storeNo'],
		{bisTenantId:'quickSearchFieldId',nameCn:'quickSearchField'},
		{bisProjectId:bis_project_id},
		function(result){
			// refreshShopStoreTable(result);
			var bisTenantId =  result.attr('bisTenantId');
			var tenantName = result.attr('nameCn')+'-'+result.attr('storeNo');
			
			getContList(bisTenantId,'setContUl',layOutCdList,tenantName);
		}
	);
	$('#quickSearchStore').quickSearch(
		_ctx+'/bis/bis-tenant!quickSearchTenant.action?type=store',
		['nameCn','storeNo'],
		{bisTenantId:'quickSearchFieldId',nameCn:'quickSearchField'},
		{bisProjectId:bis_project_id},
		function(result){
			// refreshShopStoreTable(result);
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
// 设置空间title值
function setTitle(control,title){
	$("#"+control).attr("title", title); 
}
// 设置控件title
function setThisTitle(con){
	var value = $("#"+con).val();
	$('#'+con).attr("title", $("#"+con).find("option[value="+value+"]").text()); 
}
// 触发按钮：高级搜索页面项目、业态控件
function changlayOutDetail3(){
	changlayOutDetail('layOutCdSenior','bisProjectIdFact','detailLabelSenior','layOutCdListSenior');
	loadSeniorSearch();
}
// 触发按钮：搜索页面项目、业态控件
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
// 触发按钮：新增页面项目、业态控件
function changlayOutDetail2(){
	
	// changlayOutDetail('layOutCdInput','bisProjectIdInput','currDetailLabel','layOutCdListInput','1');
	var project = $('#bisProjectIdInput').val();
	var s="";// '<font style="color:red;">*</font>';
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
			currDetailId= $('#layOutCdList_v1').val();
			$('#layOutCdInput').val('2');
			$('#layOutCdInput0').val('公寓');
			$('#currDetailLabel').html(s+'公寓'+'：');
// currPanel='detailFor';
			return;
		case '3':
			currDetailId= $('#layOutCdList_v1').val();
			$('#layOutCdInput').val('3');
			$('#layOutCdInput0').val('多经');
			$('#currDetailLabel').html(s+'多经'+'：');
// currPanel='detailFor';
			// url=_ctx+'/bis/bis-fact!searchMulti.action';break;
			return;
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
			// 无法在初始化完成之后，设置某个值选中，所有在初始化时设置选中
			if(n.id==currDetailId){
				option+= '<option onclick=setTitleByMulti("'+currLayoutLabel+'","'+n.text+'","'+n.key+'","'+n.id+'") value="'+n.id+'" selected="selected">'+n.text+'</option>';
				
			}else
				option+= '<option onclick=setTitleByMulti("'+currLayoutLabel+'","'+n.text+'","'+n.key+'","'+n.id+'") value="'+n.id+'">'+n.text+'</option>';
		/*
		 * //无法在初始化完成之后，设置某个值选中，所有在初始化时设置选中 if(n.id==currDetailId){ option+= '<option
		 * onclick=setTitle("'+layOutCdListInput+'","'+n.text+'")
		 * value="'+n.id+'" selected="selected">'+n.text+'</option>';
		 * 
		 * }else option+= '<option
		 * onclick=setTitle("'+layOutCdListInput+'","'+n.text+'")
		 * value="'+n.id+'">'+n.text+'</option>';
		 */});
		$("#"+layOutCdListInput).append(option);
		adjustHeight();
	});
}
/**
 * 触发事件：业态切换 执行动作：若项目不为空，更新明细(租户、公寓、多经)信息
 * 
 */
 var currLayoutLabel;// 当前商铺，公寓，多径控件的名称
 var currUrl;
 // 根据业态切换，搜索条件切换事件
function changlayOutDetail(layOutCdId,bisProjectId,detailLabel,layOutCdList,s){
	var project = $('#'+bisProjectId).val();
	$('#'+layOutCdList).html('');
	if(project==''){
		// 若项目为空，清空明细信息,提示必须选
		$('#'+bisProjectId).addClass('mustSelet');
		return ;
	}
	var locallayOutCd = $('#'+layOutCdId).val();
	if(locallayOutCd==''){
		// 若业态为空，清空明细信息，提示必选
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
			// selectTenant();
			// url=_ctx+'/bis/bis-fact!searchTenant.action';break;
			$('#'+detailLabel).html(s+'租户/商铺：');
			// $("#"+layOutCdList).html('');
			// var currDetailName = $('#layOutCdList').text();
			// var option = '<option
			// value="'+currDetailId+'">'+currDetailName+'</option>';
			// $("#"+layOutCdList).html('-请选择-');
			$('#layOutCdList_v1').hide();$('#layOutCdList_v0').show();
			currLayoutLabel='layOutCdList_v0';
			// $('#'+currLayoutLabel).val('搜索商家/商铺');
			// $('#'+currLayoutLabel).css('color','#ccc');
			currUrl= _ctx+'/bis/bis-fact!searchMustForStore.action';
			return ;
		}
		case '2':
			$('#layOutCdList_v1').hide();$('#layOutCdList_v0').show();
			currLayoutLabel='layOutCdList_v0';
			$('#'+currLayoutLabel).val('搜索公寓编号');
			$('#'+currLayoutLabel).css('color','#ccc');
			currUrl= _ctx+'/bis/bis-fact!searchMustForFlat.action';
			url=_ctx+'/bis/bis-fact!searchFlat.action';
			$('#'+detailLabel).html(s+'公寓：');
			return;
		case '3':
			$('#layOutCdList_v1').show();$('#layOutCdList_v0').hide();
			currLayoutLabel='layOutCdList_v1';
			
			currUrl= _ctx+'/bis/bis-fact!searchForMulti.action';
			// url=_ctx+'/bis/bis-fact!searchMulti.action';
			$('#'+detailLabel).html(s+'多经：');
			setTitle(currLayoutLabel,'');
			/*
			 * currProjectId = project;
			 * $.post(url,{bisProjectId:project},function(result){ var data =
			 * eval(result); $("#"+currLayoutLabel).html('');
			 * appendOption(currLayoutLabel); var option ='';
			 * setTitle('layOutCdList',''); $.each(data,function(i,n){
			 * //无法在初始化完成之后，设置某个值选中，所有在初始化时设置选中 if(n.id==currDetailId){ option+= '<option
			 * onclick=setTitleByMulti("'+currLayoutLabel+'","'+n.text+'","'+n.key+'","'+n.id+'")
			 * value="'+n.id+'" selected="selected">'+n.text+'</option>';
			 * 
			 * }else option+= '<option
			 * onclick=setTitleByMulti("'+currLayoutLabel+'","'+n.text+'","'+n.key+'","'+n.id+'")
			 * value="'+n.id+'">'+n.text+'</option>'; });
			 * $("#"+currLayoutLabel).append(option); adjustHeight(); });
			 */
			return;
	}
}
function setTitleByMulti(control,text,key,id){
	if("cont"==key){
		$("#bisContId").val(id);
	}else{
		$("#"+control).attr("title", title); 
	}
}
function appendOption(inputId){
	var option = '<option value="">-请选择-</option>';
	 $("#"+inputId).append(option);
}
/**
 * 用途：搜索实收内容 设置当前业态
 * 
 * @param layOutCd
 */
function setCurrlayOutCd(layOutCd){
	currlayOutCd=layOutCd;
}
// 新增 录入实收
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
		// $('#detailPanel').hide();
	}else{
		$("#addContent").html('').hide();
	}
}
// 搜索应收，录入实收
/**
 * layOutCd:业态编号 currDetailId:租户、公寓、多径信息id currDetailName:租户、公寓、多径 标签
 * chargeTypeCd:费用类别
 */
function appendBisFact1(bisProjectId,layOutCd,currDetailId,currDetailName,chargeTypeCd,year,month,mustMoney,money,collDate,currDetailName,storeId,bisContId){
	var data = {
			bisProjectId:bisProjectId,
			layOutCd:layOutCd,
			chargeTypeCd:chargeTypeCd,
			factYear:year,
			factMonth:month,
			mustMoney:mustMoney,
			currDetail:currDetailId,
			factDate:collDate,
			currDetailName:currDetailName,
			storeId:storeId,
			bisContId:bisContId
		};
	var url = _ctx+'/bis/bis-fact!toFactList.action';
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='selectTypeDiv'><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
		width : 600,
		height :400,
		title : "查看明细",
		closeBtn:true,
		winPos:[($("#detailPanel").width()-600)/2,150],
		afterShow : function() {
			$.post(url, data, function(result) {
				$("#selectTypeDiv").html(result);
				// 记忆所选项目
				$('#bisProjectNameInput').val($('#bisProjectName').val());
				var option = '<option value="'+currDetailId+'">'+currDetailName+'</option>';
				$("#layOutCdListInput").append(option);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				var money = $('#newMoney').val();
				var remark = $('#remark').val();//应收
				var totalMustMoney=$("input[name=totalMustMoney]").val();
				var factListDate = $('#factListDate').val();
				if(!ifFactInputSaving){
					saveFact(factListData.layOutCd,factListData.bisProjectId,factListData.currDetail,factListData.storeId,factListData.chargeTypeCd,factListData.factYear,factListData.factMonth,money,factListDate,remark,factListData.bisContId,totalMustMoney);
				}
				ifFactInputSaving = true;
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
		// currLayoutLabel = 'layOutCdListInput';
		// $('#detailPanel').hide();
	}else{
		$("#addContent").html('').hide();
	}
}
// bisProjectId,layOutCdList,bisStoreId,chargeTypeCd,factYear,factMonth,money};
function saveFact(layOutCd,bisProjectId,layOutCdList,bisStore,chargeTypeCd,factYear,factMonth,money,factDate,remark,bisContId,totalMustMoney){
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
		ifFactInputSaving = false;
		return;
	}
	var data = {bisProjectId:bisProjectId,layOutCdList:layOutCdList,bisStoreId:bisStore,chargeTypeCd:chargeTypeCd,factYear:factYear,factMonth:factMonth,money:money,factDate:factDate,remark:remark,bisContId:bisContId,totalMustMoney:totalMustMoney};
	if(data.factDate==''||data.factDate=='null'){
		alert('实收日期必填');
		ifFactInputSaving = false;
		return;
	}
	if(data.money==''||data.money=='null'){
		alert('实收金额必填');
		ifFactInputSaving = false;
		return;
	}
	$.post(url,data,function(result){
		ifFactInputSaving = false;
		ymPrompt.close();
		alert('保存成功');
	    $('#addContent').hide();
	});
	
}
function initInputInfo(){
	// 费用类别、业态、年、月由struts自动加载
	// 记忆搜索所选业态对应的明细标签
	// $('#currDetailLabel').html($('#detailLabel').html());

	// 记忆所选项目
	$('#bisProjectNameInput').val($('#bisProjectName').val());
	$('#bisProjectIdInput').val($('#bisProjectIdFact').val());
	// 注册项目选择按钮
	// $('#bisProjectNameInput').onSelect({muti:false});
	changlayOutDetail2();
	// 记忆所选业态明细
	// $("#layOutCdListInput").find("option[value='"+currDetail+"']").attr("selected","true");
	// loadValidate();
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
	//获取抵充状态（分别处理预约明细和应付明细两入口添加的不同 数据）
	var hasDichongFlag = $("#hasDichongFlag").val();
	if(hasDichongFlag!=''){
		alert("对不起，抵充后不允许作‘驳回’操作");
		//ymPromptClose();
		return;
	}else{
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
/**
 * 重新加载收费明细搜索页面
 */
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
// 附件管理
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
				// validateFlat(entityId,domId,attachFlgId);
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
function getCurYear(){
	var curData = new Date();
	return curData.format("yyyy");
}
function getCurMonth(){
	var curData = new Date();
	return curData.format("MM");
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
	s = date.getFullYear() + "-";            // 取年份
	s += date.getMonth()+1 + "-"; // 取月份
	s += date.getDate();  // 取日期
	return s;
	}
// 返回某个日期对应的月份的天数
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
	// feb:
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

// 增加天
function   AddDays(date,value)
{
	date.setDate(date.getDate()+value);
}
// 点击全选按钮
function checkedAll(flag){
	$("#editTable :input[type=checkbox]").attr("checked",flag);
}
/**
 * 置孔td的onclick方法
 */

function scheClick(scheId){
}
function doUpdateAll(op){
	var url ;
	var param='';
	var success=0;
	if(op='1'){
		// pass
		url = _ctx+"/bis/bis-fact!passAll.action";
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
var isChangeMustBtn;
/**
 * 自动生成因收实例
 * 
 * @return
 */
function doOperateType(dom){
	isChangeMustBtn=true;
	var actType=$(dom).attr("name");
	if(actType=="insert"){
		doInsertBatch(1);		
	}else{		
		doSaveAll(1);
	}
}
/**
 * 更改应收按钮
 * @return
 */
function autoChange(){
	var actType=$("#btn_autoIncome").attr("name");
	if(actType=="insert"){
		$("#btn_autoIncome").attr("name","save");
		$("#btn_autoIncome").val("批量保存");
	}else{
		
		$("#btn_autoIncome").attr("name","insert").attr("title","默认应收数额为实收金额，当前系统日期为实收日期");
		$("#btn_autoIncome").val("自动生成实收");
	
	}
	
}
/**
 * 批量新增实收：只针对商铺维度 将来可能废弃
 */
function doInsertBatch(op){
	var url ;
	var param='';
	var success=0;
	var tmp = new Array();
	$(':tr[class=mainTr]').each(function(){
		var ch = $(this).find(':input[type=checkbox]').attr("checked");
		if(ch){
			var id = $(this).attr('id').replace('main_','');
			alert(id);
			tmp.push(AllFactMust[id]);
		}else{
			
		}
	});
	TmpFactMust = tmp;
	if((""+tmp)==""){
		alert("请选择要生成实收的商家信息！");
		return;
	}
	if(op='1'){
		// pass
		url = _ctx+"/bis/bis-fact!inputs.action";
		$('#detailFor').mask('自动生成实收..');
		var param = {'_inserted':TmpFactMust};
		alert(param.toString());
		$.post(url,{_easy_grid:Convert.ToJSONString(param)},function(result){
			$('#detailFor').html(result).unmask();
			adjustHeight();
			// 提示
			$('#factoptip').html('正在自动生成实收').show();
			if(isChangeMustBtn){
				autoChange();
				
			}
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
				{
					if(name=="factDate"){//实收日期
						var _val= $(this).val();
						  if($.trim(_val)!=""){
							  var _dateArray=_val.split("-");
							  if(_dateArray[0].length<4){//如果使用12-04-26 则转到后台需在加上年份前两位20
								  _val="20"+_val;  
							  }				  
						  }
						  factv[name]= _val;
					}else{
						factv[name]= $(this).val();
					}
				  
				}
			});
			TmpFactMust.push(factv);
		}
	});
	var result={'_inserted':TmpFactMust};
	var param={'_easy_grid':Convert.ToJSONString(result)};
	if(op='1'){
		// pass
		url = _ctx+'/bis/bis-fact!saveBatch.action';
		if (confirm("确定要新增"+TmpFactMust.length+"条实收?")){
			$.post(url,param,function(result){
				if(result=='success'){
					isSuccess=1;
					$('#factoptip').html('保存成功').fadeOut('slow',function(){reloadDetail();});
					if(isChangeMustBtn){
						autoChange();
						
					}
				}
					
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
	// $("#tr_n").remove();
}
// 按键时若存在默认字符,清空
function clearInput(_this){
	if("搜索商家/商铺"==$(_this).val()
			||"搜索公寓编号"==$(_this).val()
			||"搜索商家"==$(_this).val()){
		$(_this).val('');
	}
	$(_this).css('color','#333');
}
// 导入实收
function importFactFile(){
	var html='<form id="importFactForm" enctype="multipart/form-data" method="post" action="'+_ctx+'/bis/bis-fact!importFact.action"><input id="importBisProjectId"  name="bisProjectId" type="hidden" /><table><tbody><tr>	<td style="padding-left: 8px; padding-top: 5px;" colspan="3">		<input type="file" style="line-height: 22px; height: 22px; margin-bottom: 3px;" name="importFact" id="importFact">	</td>	</tr></tbody></table></form>';
	
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='selectTypeDiv'><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
		width : 300,
		height :140,
		title : "导入数据",
		afterShow : function() {
			$("#selectTypeDiv").html(html);
			$('#importBisProjectId').val($('#bisProjectIdFact').val());
		},
		handler : function(btn){
			if(btn=='ok'){
				
				if(isEmpty($("#importFact").val())) {
					 alert("请先选择要导入的文件");
					 $("#importFact").focus();
					 return false;
				 }
				// TB_showMaskLayer("正在导入...");
				 $("#importFactForm").ajaxSubmit(function(result){
					// result = Convert.specialChar2Html(result);
					 ymPrompt.close();
					 result ='<div style="margin:8px">'+result+'</div';
					 ymPrompt.alert({title:"操作结果",icoCls:"",message:result});
				 }); 
			}else{
				ymPrompt.close();
			}
		},
		btn:[["保存",'ok'],["取消",'cancel']],
		closeBtn:true
	});

}
// 保存抵扣记录集
function saveDeduct(){
	var parentId = $('#bisFactId').val();
	var bisProjectId = $('#bisProjectIdInput').val();
	var chargeTypeCd = $('#chargeTypeCdInput').val();
	var factYear = $('#factYear').val();
	var factMonth = $('#factMonth').val();
	var balance = $('#balance').val();// 余额
	var money = $('#money').val();
	if(Number(money)>Number(balance)){
		alert('扣款金额大于余额');
		return;
	}
	var contLayOutCd = $('#layOutCdInput').val();
	var currDetail = $('#currDetail').val();
	var bisShopId = $('#bisShopId').val();
	var bisStoreId = $('#bisStoreId').val();
	factDeduct(parentId,bisProjectId,chargeTypeCd,factYear,factMonth,money,contLayOutCd,currDetail,bisShopId,bisStoreId);
}
// 打印缴费通知单
function printPay(tableId){
	
	var	html=$('#'+tableId).html();
	$("#detailPanelDiv").html('');
	$("#detailPanelDiv").append(html);
    window.open(_ctx+'/common/print4bis.jsp','print', 'toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
}
// 打印缴费通知单
function populateData(dom){
	var url = _ctx+'/bis/bis-fact!getPrintTemplat.action';
	$.post(url,{bisProjectId:$('#bisProjectIdFact').val()},function(result){
		$("#detailPanelDiv").html('');
		populateTable(result, dom);
	});
	window.open(_ctx+'/common/print4bis.jsp','print', 'toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
}
/**
 * 生成单个缴费单html
 * 
 * @param html
 *            项目公司缴费单末班
 * @param dom
 *            当前要打印的缴费单信息
 */
function populateTable(html,dom){
	
	$("#detailPanelDiv").append(''+html+'');
	var lhtml;
	$('.mainTr div.ellipsisDiv_full').each(function(i,dom){
		var colTitle = $(dom).attr('colTitle');
		$('#temp_tb span[colTitle='+colTitle+']').html($(dom).html().replace(' ',""));
	});
	$('#temp_tb').attr('id',dom.substring(3)+'_tb');
}
// 提交搜索缴费通知的form
function submitPayNoti(){
	if($('#year').val==''&&$('#month').val()==''){
		alert('请先选择年月');
		return ;
	}
	if($('#storeNo').val()=='搜索商铺'){
		$('#storeNo').val('');
	}
	if($('#nameShop').val()=='搜索商家'){
		$('#nameShop').val('');
	}
	$('#main_search_div').mask('正在加载数据');
	$('#searchForm').ajaxSubmit(function(result){
		$('#welcom').hide();
		$('#detailFor').html(result).show().unmask();
		$('#main_search_div').unmask();
		autoHeight();
	});
}
function jumpPage4Pay(pageNo){
	if($('#year').val==''&&$('#month').val()==''){
		alert('请先选择年月');
		return ;
	}
	if($('#storeNo').val()=='搜索商铺'){
		$('#storeNo').val('');
	}
	if($('#nameShop').val()=='搜索商家'){
		$('#nameShop').val('');
	}
	$('#pageNo').val(pageNo);
	$('#main_search_div').mask('正在加载数据');
	$('#searchForm').ajaxSubmit(function(result){
		$('#welcom').hide();
		$('#detailFor').html(result).show().unmask();
		$('#main_search_div').unmask();
		autoHeight();
	});
}
function exportPayNoti(){
	var factYear = $('#year').val();
	var factMonth = $('#month').val();
	
	if($('#year').val==''&&$('#month').val()==''){
		alert('请先选择年月');
		return ;
	}
	if($('#storeNo').val()=='搜索商铺'){
		$('#storeNo').val('');
	}
	if($('#nameShop').val()=='搜索商家'){
		$('#nameShop').val('');
	}
	if (confirm("导出数据")){
		var bisProjectId = $('#bisProjectIdFact').val();
		var url =_ctx+"/bis/bis-pay-requisition!exportPayRequisitionFile.action?bisProjectId="+bisProjectId+"&factYear="+factYear+"&factMonth="+factMonth;
		location.href = url;
	}
}
function toPayNoti(){

	$('#dimension').val('5');
	var url  = _ctx+'/bis/bis-fact!list.action';
	var year = getCurDate().substr(0,4);
	var month = getCurDate().substr(5,2);
	$('#year').val(year);
	$('#month').val(month);
	$('#searchForm').attr('action',url);
	$('#searchForm').submit();

	adjustHeight();
}
// 打印选择缴费通知单
function doPrintAll(op){
	var	html='';
	$("#detailPanelDiv").html('');
	if(op='1'){
		var url = _ctx+'/bis/bis-fact!getPrintTemplat.action';
		$.post(url,{bisProjectId:$('#bisProjectIdFact').val()},function(result){
			$("#detailPanelDiv").html('');
			html = result;
			if(html!=''){
				$('#editTable').find(':input[type=checkbox]').each(function(){
					if($(this).attr("checked") ){
						var dom = $(this).attr("ids");
						populateTable(html, dom);
						// html+=$('#tb_'+id).html();
					}
				});
			}
		});
		window.open(_ctx+'/common/print4bis.jsp','print', 'toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
	}
}
function updatPrevValue(dom){
	var val = $(dom).val();
	if(val==''){
		alert('请填写值');$(dom).focus();return;
	}else{
		
		$(dom).prev().html(val);
		$(dom).hide();
		$(dom).prev().show();
	}
}

function doExport4Search(tableType){
	var url;
	if("fact"==tableType){
		url= _ctx+'/bis/bis-fact!exportFactResult.action';
	}else{
		url= _ctx+'/bis/bis-fact!exportMustResult.action';
	}

	var obj = processSearchConditions('','bisProjectIdFact','chargeTypeCd','layOutCd',currLayoutLabel,'year','month',
			'statusCd','creator','checkUserCd','minMonth','maxMonth','minMoney','maxMoney','inDateBegin','inDateEnd');
	
	if (confirm("导出搜索结果")){
/*
 * var recursiveEncoded = $.param(obj); var recursiveDecoded =
 * decodeURIComponent($.param(obj));
 */
		location.href=url+"?module="+$('#layOutCd').val()+"&"+$.param(obj);
		// href2Post(url,obj);
	}
}

function href2Post(url,data){
	$('body').append("<form action='"+url+"' method='post' name='href2Post' id='href2Post' style='display:none'><form>");
	for (var a in data){
		$('#href2Post').append("<input type='hidden' name='"+a+"' value='"+data[a]+"'");
	}
	$('#href2Post').append("<input type='hidden' name='module' value='"+$('#layOutCd').val()+"'");

	$('#href2Post').submit();
	$('#href2Post').remove();
}
// 搜索实公寓数据
function subSeaByFact(){
	var obj = processSearchConditions('','bisProjectIdFact','chargeTypeCd','layOutCd',currLayoutLabel,'year','month','statusCd');
	$('#welcom').hide();
	$('#addContent').hide();
	$('#detailPanel').show();
	if(obj.contLayOutCd){
		setCurrlayOutCd(obj.contLayOutCd);
	}
	var	pageNo=1;
	// 应收 实收维度
	var mustOrFact = $('li.bis_fact_click').attr('value');
	obj.mustOrFact = mustOrFact;
	var data = obj;
	currData= obj;
    currUrl=_ctx+'/bis/bis-fact!searchFactForFlat.action';

	// $('#detailFor').css('height','400px').mask('正在加载数据');
	$('#detailFor').mask('正在加载数据');
	$('#main_search_div1').mask();
	// 标志位：等待加载页面之后，再调整页面高度
	var su = '0';
	$.post(currUrl,data,function(result){
		if(result.indexOf('rror')>0){
			result.replace('error','');
			alert("请精确商家或商铺编号搜索条件");
		} 
		$('#detailFor').html(result).show().unmask();
		$('#main_search_div1').unmask();
		adjustHeight();
	});
}