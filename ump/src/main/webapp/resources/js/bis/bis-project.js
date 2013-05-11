/**
 * 商业项目使用
 */
isEmpty = function (str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
};
/**
 * 3级菜单关联1、楼宇类型2、商业项目 1、2过滤3、楼号与楼层号
 * @param filterType
 */
function filterFloor(filterType){
	var floorType =$("#floorType").val();
	if('1'==floorType||'2'==floorType){
		var bisHid;
		if('1'==floorType){
			bisHid ='bisFloorHid';
		}else{
			bisHid ='bisBuildingHid';
		}
		var option1="<option>--选择--</option>";
		$("#"+bisHid).find("option").each(function(i){
			var text =$(this).text();
			var value=$(this).val();
			if(value.length>0){
				var strPorjectId=new Array();
				strPorjectId =value.split("`");
				if(strPorjectId[1]==filterType){
					option1+="<option value='"+value+"' title='"+text+"'>"+text+"</option>";
				}
			}
		});
		$("#bisFloorId").find("option").remove().end().append(option1);
	}
//	bisFloorSearch();
}
/**
 * converName 
 * 阻止搜索覆盖新增内容
 */
function convertName(){
	$("input[name='bisProjectId']").val($('#bisProjectIds').val());
	$("input[name='bisFloorId']").val($('#bisFloorIds').val());
	$("input[name='floorType']").val($('#floorTypes').val());
}
function convertNames(){
	$("input[name='bisProjectId']").val($('#bisProjectId').val());
	$("input[name='bisFloorId']").val($('#bisFloorId').val());
	$("input[name='floorType']").val($('#floorType').val());
}
function clearData(){
	if($('#layoutCd')) {
		$('#layoutCd').val('');
	}
	if($('#equityNature')) {
		$('#equityNature').val('');
	}
	if($('#flat_houseStruCd')) {
		$('#flat_houseStruCd').val('');
	}
	if($('#flat_layoutCd')) {
		$('#flat_layoutCd').val('');
	}
	if($('#bisStore_shopPosition')){
		$('#bisStore_shopPosition').val('');
	}
	//商铺
	$("#new_Store").find(':text,textarea').each(function(i, dom) {
		 $(dom).val('');
	});
	//公寓
	$("#new_Flat").find(':text,textarea').each(function(i, dom) {
		 $(dom).val('');
	});
	//多经
	$("#new_Multi").find(':text,textarea').each(function(i, dom) {
		 $(dom).val('');
	});
}
function saveNewFloor(){
	
	convertName();
	var nos;
	var floorType = $('#floorTypes').val();
	var bisProjectId = $('#bisProjectIds').val();
	var bisFloorId = $('#bisFloorIds').val();
	var rentStandard = $('#rentStandard').val();
	var tenemStandard = $('#tenemStandard').val();
	var equityNature = $('#equityNature').val();
	if(floorType==''){
		alert("请选择楼宇类型");
		return false;
	}
	if(floorType=='1'){
		if(bisProjectId==""){
			alert("请选择商业项目");
			return false;
		}
		if(bisFloorId=="--选择--"||bisFloorId==""){
			alert("请选择楼层号");
			return false;
		}
		var req=0;
		$("#new_Store .required").each(function(i){
			if(""==$(this).val()){
				req=1;
				return false;
			}
		});
		if(req==1){
			alert("请输入红色标识的必选项");
			return false;
		}
		/*if(rentStandard==""){
			alert("请填写租金标准");
			return false;
		}
		if(tenemStandard==""){
			alert("请填写物业标准");
			return false;
		}*/
		if(equityNature==""){
			alert("请填写产权性质");
			return false;
		}else if("2"==equityNature){
			if(""==$("#manageStatus").val()){
				alert("请输入经营现状");
				return false;
			}
			if(""==$("#ifPractice").val()){
				alert("请输入是否开业");
				return false;
			}
		}
		if($('#storeNo').val()==""){
			alert("请填写编号");
			return false;
		}else{
			nos=$('#storeNo').val();
		}
		
	}else if(floorType=='2'){
		if(bisProjectId==""){
			alert("请选择商业项目");
			return false;
		}
		if(bisFloorId==""||bisFloorId=="--选择--"){
			alert("请选择楼号");
			return false;
		}
		//对增加的字段进行验证 -add by liuzhihui 2012-05-28
		//--start--//  
		var flatNo = $('#flatNo').val();
		if(""!=$.trim(flatNo)){
			nos=$('#flatNo').val();
		}
		var req=0;
		$("#new_Flat .required").each(function(i){
			if(""==$(this).val()){
				req=1;
				return false;
			}
		});
		if(req==1){
			alert("请输入红色标识的必选项");
			return false;
		}
		//--end--//
		
	}else{
		if(bisProjectId==""){
			alert("请选择商业项目");
			return false;
		}
		if($('#multiName').val()==""){
			alert("请填写多经编号");
			return false;
		}else{
			nos=$('#multiName').val();
		}
		if($('#multi_type').val()==""){
			alert("请选择多经类型");
			return false;
		}else if($('#multi_type').val()=="1"){
			if($('#multiGrade').val()==""){
				alert("请输入等级");
				return false;
			}
		}else if($('#multiFloor').val()==""){
			alert("请输入楼层");
			return false;
		}
		if($('#multiPrice').val()==""){
			alert("请输入执行价格");
			return false;
		}
		if($('#multiPolicy').val()==""){
			alert("请输入执行政策");
			return false;
		}else if($('#multiPolicy').val().length>100){
			alert("执行政策不允许最大长度100");
			return false;
		}
	}
	if(floorType!=""&&bisProjectId!=""){
		$.post(_ctx+"/bis/bis-project!uniqueExist.action",
				{
				  bisProjectId:bisProjectId,
				  floorType:floorType,
				  nos:nos,
				  bisFloorId:bisFloorId
			    },
				function(result) {
					if (result=='success') {
						TB_showMaskLayer("正在保存...");
						$('#seaFloorForm').ajaxSubmit(function(result) {
							if(result!=null){
								clearData();
								floorSearchNew();
							}
							TB_removeMaskLayer();
						});
						return true;
					}else{
						if(floorType=='1'){
							$('#storeNo').val('');
							alert("编号重复");
						}else if(floorType=='2'){
							$('#flatNo').val('');
							alert("编号重复");
						}else{
							$('#multiName').val('');
							alert("多经名称重复");
						}
						return false;
					}
				}
			);
	}
//	$('#seaFloorForm').submit();
////	resetSearch();
}
/**
 * 编号唯一效验请求
 * @returns
 */
//function uniquePost(nos){
//	$.post(_ctx+"/bis/bis-project!uniqueExist.action",
//			{
//			  bisProjectId:$('#bisProjectIds').val(),
//			  floorType:$('#floorTypes').val(),
//			  nos:nos,
//			  bisFloorId:$('#bisFloorIds').val()
//		    },
//			function(result) {
//				if (result=='success') {
//					return true;
//				}else{
//					$('#flatNo').val('');
//					return false;
//				}
//			}
//		);
//}
function floorSearchNew(){
	$('#massageTab').hide();
	var bisProjectId =$("#bisProjectIds").val();
	var floorType =$("#floorTypes").val();
	var bisFloorId =$("#bisFloorIds").val();
	if(floorType=="3"){
		$.post(_ctx+"/bis/bis-project!multiList.action",
				{
				  bisProjectId:bisProjectId,
				  floorType:floorType,
				  bisFloorId:bisFloorId
			    },
				function(result) {
					if (result) {
						$("#bisProjectFloor").html(result);
					}
				}
			);
	}else{
		$.post(_ctx+"/bis/bis-project!list.action",
				{
				  bisProjectId:bisProjectId,
				  floorType:floorType,
				  bisFloorId:bisFloorId
			    },
				function(result) {
					if (result) {
						$("#bisProjectFloor").html(result);
					}
				}
			);
	}
}
function cancelAdd(){
	$('#titleAdd').hide();
}
function bisFloorAdd(){
	$('#titleAdd').show();
	if($("#bisProjectName").val()=='') {
		$("#bisProjectNames").val("--选择项目--");
	}else{
		$("#bisProjectNames").val($("#bisProjectName").val());
	}
	var projectId = $('#bisProjectId').val();
	$('#bisProjectIds').val(projectId);
	if($('#floorType').val()!=''){
		$('#floorTypes').val($('#floorType').val());
		floTypes($('#floorTypes').val());
	}
//	if($('#bisProjectId').val()!=''){
//		$("input[name='bisProjectId']").val($('#bisProjectIds').val());
//	}
//	if($('#bisFloorId').val()!=''){
		$('#bisFloorIds').val($('#bisFloorId').val());
//		$("input[name='bisFloorId']").val($('#bisFloorIds').val());
//	}
		//洛阳一期商铺说明
	
	if(projectId=='40282b8927a42dff0127a435d5c30127'){
		$('#luoyanginfo').show();
	}
}
//function filterFloors(filterType){
//	var s_root=document.getElementById('bisFloorId');
//		s_root.options.length=0;
//		for(i=0;i<3;i++){
//			var option = document.createElement("option"); 
//			option.text='sss';  
//	        option.value='ssss';
//	        s_root.options[s_root.options.length] =option;
//		}
//}
function bindTblEv(){

}

function bisFloorSearch(pageNo){
	if(typeof (pageNo) === "undefined" || pageNo === null || (pageNo.length === 0)){
		pageNo=1;
	}
	var floorType = $('#floorType').val();
	var bisProjectId = $('#bisProjectId').val();
	if(bisProjectId==""||floorType==""){
		alert("请选择搜索条件后进行选择");
		return false;
	}
	TB_showMaskLayer("正在搜索...");
	convertNames();
	$('#massageTab').hide();
//	var bisProjectId =$("#bisProjectId").val();
//	var floorType =$("#floorType").val();
	var bisFloorId =$("#bisFloorId").val();
	var num =$("#num").val();
	$("input[name='bisFloorId']").val(bisFloorId);
	if(floorType=="3"){
		$.post(_ctx+"/bis/bis-project!multiList.action",
				{
				  bisProjectId:bisProjectId,
				  floorType:floorType,
				  num:num,
				  bisFloorId:bisFloorId
			    },
				function(result) {
					if (result) {
						$("#bisProjectFloor").html(result);
						TB_removeMaskLayer();
					}
				}
			);
	}else{
		$.post(_ctx+"/bis/bis-project!list.action",
				{
				  bisProjectId:bisProjectId,
				  floorType:floorType,
				  num:num,
				  bisFloorId:bisFloorId,
				  currentPageNo:pageNo
			    },
				function(result) {
					if (result) {
						$("#bisProjectFloor").html(result);
						TB_removeMaskLayer();
					}
				}
			);
	}
}
function changeFloorType(floorType){
//	$("input[name='floorType']").val(floorType);
	var bisProjectId =$("#bisProjectId").val();
	if(floorType=='2'){
		$("#building").show();
		$("#bisFloorTd").show();
		$("#building").html("*楼号：");
		$("#numName").html("编号：");
		filterFloor(bisProjectId);
//		bisFloorSearch();
	}else if(floorType=='1'){
		$("#building").show();
		$("#bisFloorTd").show();
		$("#building").html("*楼层号：");
		$("#numName").html("编号：");
		filterFloor(bisProjectId);
//		bisFloorSearch();
	}else{
		$("#building").hide();
		$("#bisFloorTd").hide();
		$("#numName").html("名称：");
//		bisFloorSearch();
	}
}
function bisStoreEdit(id){
	var _this=$("#bisWork_"+id);
	if(_this.css('display') == 'none'){
		_this.show();
	}else{
		_this.hide();
	}
}
function deleteFloor(id){
	var floorType =$("#floorType").val();
	if(id!=null){
		if(confirm("确定要删除该条记录吗？")){
			var url=_ctx+"/bis/bis-project!delete.action";
			$.post(url,{
						id:id,
						floorType:floorType
					   },
					function(result) {
						if('success' == result){
							bisFloorSearch();
						}else{
							alert("删除失败");
							return false;
						}
					});
		}
	}
}
//$("td").hover(
//		  function () {
//		    $(this).find(".bis-read").hide();
//		    $(this).find(".bis-edit").show();
//		  },
//		  function () {
//		    $(this).removeClass("hover");
//		  }
//		);
function floorEdit(id){
	$('#'+id).show();
	
}
//function floorEdits(id,floorType){
//	if(id!=null){
//			var url=_ctx+"/bis/bis-project!search.action";
//			$.post(url,{
//						id:id,
//						floorType:floorType
//					   },
//					function(result) {
//				alert(result);
//						if('success' == result){
//							bisFloorSearch();
//						}else{
//							alert("删除失败");
//							return false;
//						}
//					});
//		}
function flatEdit(id){
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div style='padding:5px;padding-right:15px;' id='flatDetailDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 850,
		height : 300,
		title : "编辑公寓信息",
		closeBtn:true,
		afterShow : function() {
				var url = _ctx+"/bis/bis-project!flatDetail.action";
				$.post(url,{id:id}, function(result) {
					$("#flatDetailDiv").html(result);
				});
		},
		handler : function(btn){
			if(btn=='ok'){
				//数据 验证 -add by liuzhihui 2012-05-29
				//---start---//bis-project-flatDetail.jsp
				var req=0;
				$("#flatDetaForm .required").each(function(i){
					var value = $(this).val();
					if(""==$.trim(value)){
						req=1;
						return false;
					}
				});
				if(req==1){
					alert("请输入红色标识的必选项");
					return false;
				}
				//---end---//
				
				$('#flatDetaForm').ajaxSubmit(function(result) { 
					bisFloorSearch();
				});					
			}
//				doInput(bigTypeId, smallTypeId);
//			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["取消",'cancel']]
	});
}
function multiEdit(id){
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div style='padding:5px;padding-right:15px;' id='multiDetailDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
			width : 790,
			height : 250,
			title : "编辑多经信息",
			closeBtn:true,
			afterShow : function() {
				var url = _ctx+"/bis/bis-project!multiDetail.action";
				$.post(url,{id:id}, function(result) {
					$("#multiDetailDiv").html(result);
				});
			},
			handler : function(btn){
				if(btn=='ok'){
					$('#multiDetaForm').ajaxSubmit(function(result) {
						bisFloorSearch();
					});					
				}
//				doInput(bigTypeId, smallTypeId);
//			}
				ymPrompt.close();
			},
			btn:[["确定",'ok'],["取消",'cancel']]
	});
}
function floTypes(floorType){
//	$("input[name='floorType']").val(floorType);
	if(floorType=='1'){
//		$("#buildings").html("*楼层号：");
//		$("#buildings").show();
		$("#storeBuildings").show();
		$("#bisFloorTds").show();
		$("#flatBuildings").hide();
		$('#new_Flat').hide();
		$('#new_Multi').hide();
		$('#new_Store').show();
//		filterFloors($("input[name='bisProjectId']").val());
		filterFloors($('#bisProjectIds').val());
	}else if(floorType=='2'){
//		$("#buildings").html("*楼号：");
		$("#flatBuildings").show();
		$("#bisFloorTds").show();
		$("#storeBuildings").hide();
		$("#buildings").show();
		$("#bisFloorTds").show();
		$('#new_Store').hide();
		$('#new_Multi').hide();
		$('#new_Flat').show();
//		filterFloors($("input[name='bisProjectId']").val());
		filterFloors($('#bisProjectIds').val());
	}else if(floorType=='3'){
//		$("#buildings").show();
//		$("#bisFloorTds").show();
		$("#storeBuildings").hide();
		$("#flatBuildings").hide();
		$("#bisFloorTds").hide();
		$('#new_Store').hide();
		$('#new_Flat').hide();
		$('#new_Multi').show();
	} else {
		//do nothing...
	}
}
function filterFloors(filterType){
//	$("input[name='bisProjectId']").val(filterType);
//	var floorTypes =$("input[name='floorType']").val();
	var floorTypes =$('#floorTypes').val();
	if(floorTypes=='1'){
		var bisFloors=document.getElementById('bisFloorIds');
		var floorHid=document.getElementById('bisFloorHid');
		bisFloors.options.length=1;
		for(i=1;i<floorHid.options.length;i++ ){
			var option = document.createElement("option");
			var options = floorHid.options[i].value;
			var strPorjectId=new Array();
			strPorjectId =options.split("`");
			if(strPorjectId[1]==filterType){
				var floorText =floorHid.options[i].text;
				var floorValue =floorHid.options[i].value;
				option.text=floorText;
				option.value=floorValue;
				bisFloors.options[bisFloors.options.length] =option;
			}
		}
	}
	if(floorTypes=='2'){
		var buildings=document.getElementById('bisFloorIds');
		var buildingHid=document.getElementById('bisBuildingHid');
		buildings.options.length=1;
		for(i=1;i<buildingHid.options.length;i++ ){
			var option = document.createElement("option");
			var options = buildingHid.options[i].value;
			var strPorjectId=new Array();
			strPorjectId =options.split("`");
			if(strPorjectId[1]==filterType){
				var floorText =buildingHid.options[i].text;
				var floorValue =buildingHid.options[i].value;
				option.text=floorText;
				option.value=floorValue;
				buildings.options[buildings.options.length] =option;
			}
		}
	}
//	$("input[name='bisFloorId']").val($('#bisFloorIds').val());
}
//function bisFloorVal(bisFloorId){
//	$("input[name='bisFloorId']").val(bisFloorId);
//}
function splitFloor(bisStoreId){
	var url=_ctx+"/bis/bis-project!storeSplit.action?bisStoreId="+bisStoreId;

	parent.TabUtils.newTab("bis-project-storeSplit","商铺拆分",url,true);
}

function doAddLayoutImg() {
	
	var url = _ctx+"/bis/bis-manage!layout.action?module=8&bisProjectId="+$('#bisProjectId').val();
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab("bisProjectLayout","项目资料管理",url,true);
}

function openFloorTab() {
	
	var url = _ctx+"/bis/bis-floor.action?bisProjectId="+$('#bisProjectId').val();
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab("bisFloor","楼层管理",url,true);
}

function editProjectInfo() {
	var bisProjectId = $('#bisProjectId').val();
	if(isEmpty(bisProjectId)) {
		alert("请先选择项目");
		return;
	}
	
	ymPrompt.win( {
		icoCls : "",
		autoClose:false,
		message : "<div id='projectInputDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
			width : 790,
			height : 220,
			title : "项目信息维护",
			closeBtn:true,
			afterShow : function() {
				var url = _ctx+"/bis/bis-project!input.action";
				$.post(url,{id:bisProjectId}, function(result) {
					$("#projectInputDiv").html(result);
				});
			},
			handler : function(btn){
				if(btn=='ok'){
					$('#saveProjectForm').ajaxSubmit(function(result) {
						//bisFloorSearch();
						alert("保存成功");
					});
				}
				ymPrompt.close();
			},
			btn:[["确定",'ok'],["退出",'cancel']]
	});
}
//多经选择
function changeMulti(dom){
	if("1"==$(dom).val()){
		$('.multFloor').hide();
		$('.multGrade').show();
		$("#multiFloor").val("");
	}else if("2"==$(dom).val()){
		$('.multFloor').show();
		$('.multGrade').hide();
		$("#multiGrade").val("");
	}
}
