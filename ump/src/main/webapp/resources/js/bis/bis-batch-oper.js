/**
 * 应收实收批量导入导出操作
 */
//根据项目ID得到楼层号
function changeFloor() {
	var bisProjectId = $("#bisProjectId").val();
	var floorType = $("#floorType").val();
	if(isEmpty(bisProjectId) || isEmpty(floorType)) {
		return false;
	}
	if(floorType == "1" || floorType == "2") {
		$(".tdFloorTd").show();
	} else if(floorType == "3") {
		$(".tdFloorTd").hide();
	}
	
	var subSele = $("#bisFloorId");
	subSele.empty();
	subSele.append('<option value="">--选择--</option>');
	
	var url = _ctx+"/bis/bis-project!getFloorNo.action";
	$.post(url,{bisProjectId:bisProjectId, floorType:floorType},function(data){
		var data = eval('('+data+')');
		$.each(data,function(i,n){
			var option = '<option value="'+i+'">'+n+'</option>';
			subSele.append(option);
		});
	});
}

function ajaxSearch() {
	
	var bisProjectId = $("#bisProjectId").val();
	var bisProjectName = $("#bisProjectName").val();
	var floorType = $("#floorType").val();
	var bisFloorId = $("#bisFloorId").val();
	var feeDate = $("#feeDate").val();
	if(isEmpty(bisProjectId)) {
		alert("请选择项目");
		return false;
	}
	if(isEmpty(floorType)) {
		alert("请选择楼宇类型");
		return false;
	}
	if(floorType!="3" && isEmpty(bisFloorId)) {
		//alert("请选择楼(层)号");
		//return false;
	}
	if(isEmpty(feeDate)) {
		alert("请选择收费日期");
		return false;
	}
	
	var url = _ctx+"/bis/bis-income-other!list.action";
	TB_showMaskLayer("正在搜索...");
	$.post(url, {bisProjectId:bisProjectId ,floorType:floorType, bisFloorId:bisFloorId, feeDate:feeDate}, function(result) {
		TB_removeMaskLayer();
		$("#contentDiv").html(result);
	});
}

var flag = false;
function exportTemplate() {
	
	//alert("暂停使用，功能维护中...");
	//return ;
	
	var bisProjectId = $("#bisProjectId").val();
	var bisProjectName = $("#bisProjectName").val();
	var floorType = $("#floorType").val();
	var bisFloorId = $("#bisFloorId").val();
	var feeDate = $("#feeDate").val();
	if(isEmpty(bisProjectId)) {
		alert("请选择项目");
		return false;
	}
	if(isEmpty(floorType)) {
		alert("请选择业态");
		return false;
	}
	if(floorType!="3" && isEmpty(bisFloorId)) {
		//alert("请选择楼(层)号");
		//return false;
	}
	if(isEmpty(feeDate)) {
		alert("请选择收费日期");
		return false;
	}
	flag = true;
	var url = _ctx+"/bis/bis-income-other!exportTemplate.action?bisProjectId="+bisProjectId+"&moduleType='1'"
				+"&floorType="+floorType+"&bisFloorId="+bisFloorId+"&feeDate="+feeDate;
	TB_showMaskLayer("正在导出...",5000);
	location.href = url;
}

function importData() {

	//alert("暂停使用，功能维护中...");
	//return ;
	
	if(isEmpty($("#importFile").val())) {
		alert("请先选择要导入的文件");
		$("#importFile").focus();
		return false;
	}
	TB_showMaskLayer("正在导入...");
	var importFile =$("#importFile").val();
	$("#mustForm").ajaxSubmit(function(result){
		TB_removeMaskLayer();
		var msg = result.split(",");
		if(msg[1] == "success") {
		    alert("导入成功：共新增"+msg[2]+"条记录，更新"+msg[3]+"条记录，耗时"+msg[4]+"秒");
		    $("#importFile").val("");
		} else {
			alert("导入失败："+msg[2]+"出错位置："+msg[3]);
		}
	});
}

isEmpty = function (str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
};
/**
 * 导出实收批量录入模板信息
 * @returns {Boolean}
 */
function doExport(){
	var bisProjectId = $("#bisProjectId").val();
	var bisProjectName = $("#bisProjectName").val();
	var floorType = $("#floorType").val();
	var bisFloorId = $("#bisFloorId").val();
	var feeDate = $("#feeDate").val();
	
	var layOutCd = $('#layOutCd').val();
	
	if(isEmpty(bisProjectId)) {
		alert("请选择项目");
		return false;
	}
	if(isEmpty(floorType)) {
		alert("请选择业态");
		return false;
	}
	if(isEmpty(feeDate)) {
		alert("请选择权责年月");
		return false;
	}
	var strArr =feeDate.split("-");
	var factYear=strArr[0];
	var factMonth=strArr[1];
	
	/*if(isEmpty(factMonth)) {
		alert("请选择收费年月");
		return false;
	}
	if(isEmpty(factYear)) {
		alert("请选择收费年月");
		return false;
	}*/
	//flag = true;
	var url = _ctx+"/bis/bis-income-other!exportTemplate.action?bisProjectId="+bisProjectId+"&moduleType=2"
				+"&floorType="+floorType+"&bisFloorId="+bisFloorId+"&feeDate="+feeDate;
	TB_showMaskLayer("正在导出...",5000);
	location.href = url;
	/*if (confirm("为避免现金流问题，系统仅导出：已存在应收，且无对应实收的租户记录")){*/	
	//}
}
//导入实收数据
function importFactFile(){
	var importFactType =$("input[type=radio][name='importFactType']:checked").val();
	if(isEmpty(importFactType)){
		alert("请选择导入类型");
		return false;
	}
	if(isEmpty($("#importFile2").val())) {
		alert("请先选择要导入的文件");
		$("#importFile").focus();
		return false;
	}
	$("#factForm").ajaxSubmit(function(result){
		TB_removeMaskLayer();
		var msg = result.split(",");
		if(msg[1] == "success") {
		    alert("导入成功：共新增"+msg[2]+"条记录，更新"+msg[3]+"条记录，耗时"+msg[4]+"秒");
		    $("#importFile").val("");
		} else {
			alert("导入失败："+msg[2]+"出错位置："+msg[3]);
		}
	});
}
function importCashFactFile(){
	var importFactType =$("input[type=radio][name='importFactType']:checked").val();
	if(isEmpty(importFactType)){
		alert("请选择导入类型");
		return false;
	}
	if(isEmpty($("#importCashFile").val())) {
		alert("请先选择要导入的文件");
		$("#importCashFile").focus();
		return false;
	}
	
	$("#cashForm").ajaxSubmit(function(result){
		TB_removeMaskLayer();
		var msg = result.split(",");
		if(msg[1] == "success") {
		    alert("导入成功：共新增"+msg[2]+"条记录，更新"+msg[3]+"条记录，耗时"+msg[4]+"秒");
		    $("#importFile").val("");
		} else {
			alert("导入失败："+msg[2]+"出错位置："+msg[3]);
		}
	});
}
//
function initOperMoudel(){
	$('#bis_rpt').find('li').bind('click',
		function(){
			$('.must_dime').show();
			$(this).addClass('bis_fact_clicked').siblings().removeClass('bis_fact_clicked');
			if((this).id=='must'){
				var url =_ctx+"/bis/bis-fact!batchMust.action?bisProjectId="+currProjectId;
			}else if((this).id=='factAuth'){
				var url =_ctx+"/bis/bis-fact!batchAuth.action?bisProjectId="+currProjectId;
			}else{
				var url =_ctx+"/bis/bis-fact!batchCash.action?bisProjectId="+currProjectId;
			}
			$.post(url,  function(result) {
				$("#showDiv").html(result);
			});
		});
}
/**
 * 现金流报表页面
 */
function toCash(dom,currProjectId){
	$("#bottom-oper li").removeClass("bottom-nav-click");
	$(dom).addClass("bottom-nav-click"); 
	var url =_ctx+"/bis/bis-fact!toCash.action?bisProjectId="+currProjectId;
	$.post(url,  function(result) {
		$("#batchAuthority").html(result);
	});
}
/**
 * 权责报表页面
 */
function toAuthority(dom,currProjectId){
	parent.TabUtils.newTab("supInput","批量操作",_ctx+'/bis/bis-fact!batchOper.action?bisProjectId='+currProjectId,true);
}
/**
*导出现金流报表
*/
function exportCashExcel(){
	var bisProjectId = $("#bisProjectId").val();
	var bisProjectName = $("#bisProjectName").val();
	var floorType = $("#floorType").val();
	var bisFloorId = $("#bisFloorId").val();
	var feeDate = $("#feeDate").val();
	var authDate1 = $("#authDate1").val();
	var authDate2 = $("#authDate2").val();
	var feeTypeId = $("#feeTypeId").val();
	if(isEmpty(bisProjectId)) {
		alert("请选择项目");
		return false;
	}
	if(isEmpty(floorType)) {
		alert("请选择业态");
		return false;
	}
	if(floorType!="3" && isEmpty(bisFloorId)) {
		//alert("请选择楼(层)号");
		//return false;
	}
	if(isEmpty(feeDate)) {
		alert("请选择现金流日期");
		return false;
	}
	if(isEmpty(authDate1)||isEmpty(authDate2)){
		alert("请选择权责年月");
		return false;
	}
	if(isEmpty(feeTypeId)){
		alert("请选择费用类别");
		return false;
	}
	if(","==feeTypeId.substring(0,1)){
		feeTypeId=feeTypeId.substring(1,feeTypeId.length);
	}
	flag = true;
	var url = _ctx+"/bis/bis-income-other!exportCashExcel.action?bisProjectId="+bisProjectId+"&moduleType='1'"+"&feeTypeId="+feeTypeId
				+"&floorType="+floorType+"&bisFloorId="+bisFloorId+"&feeDate="+feeDate+"&authDate1="+authDate1+"&authDate2="+authDate2;
	TB_showMaskLayer("正在导出...",5000);
	location.href = url;
}
var selectFeeId=",";
var selectFeeName="";
function selectFeeType(){
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"费用类型",
		message:"<div id='feeType_Div'></div>",
		useSlide:true,
		winPos:"c",
		width:300,
		height:480,
		allowRightMenu:true,
		afterShow:function(){
			$.post(_ctx+"/bis/bis-fact!getMapFeeType.action",function(result){
				var data = eval(result);
				 $("#feeType_Div").html('');
				 var content ="<table id='mainTable' class='currtr'><col/>";
					$.each(data,function(i,n){
						if(n.text){
							if(selectFeeId.indexOf(","+n.id+",")<0){
							  content+="<tr id="+n.id+"_TD onclick=setFeeTypeId('"+n.id+"','"+n.text+"')>";
							  content+="<td>"+n.text+"</td></tr>";
							}else{
								content+="<tr id="+n.id+"_TD class='addFirst' onclick=setFeeTypeId('"+n.id+"','"+n.text+"')>";
								content+="<td>"+n.text+"</td></tr>";
							}
						}
					});
					$("#feeType_Div").append(content);
			});
		},
		handler:function(e){
			if("ok"==e){
				if(selectFeeId.length>0){
					$("#feeTypeId").val(selectFeeId.substring(0, selectFeeId.length-1));
					$("#feeTypeName").val(selectFeeName.substring(0, selectFeeName.length-1));
				}
			}else if("cancel"==e){
				selectFeeId=",";
				selectFeeName="";
				$("#feeTypeId").val("");
				$("#feeTypeName").val("");
			}
		}
	});
}
function setFeeTypeId(feeId,feeName){
	if(selectFeeId.indexOf(","+feeId+",")<0){
		//若没有该费用，则新增
		$('#'+feeId+'_TD').addClass("addFirst");
		selectFeeId=selectFeeId+feeId+",";
		selectFeeName =selectFeeName+feeName+",";
	}else{
		$('#'+feeId+'_TD').removeClass("addFirst");
		selectFeeId=selectFeeId.replace(feeId+",", "");
		selectFeeName = selectFeeName.replace(feeName+",", "");
	}
}
