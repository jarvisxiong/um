var can_edit_record = false;	//能否对记录进行编辑。如果是管理员，则能编辑；如果是普通用户，则在自己中心内的才能编辑.
var opened_td_id = "";	//当前打开的记录的ID，如果点了其他行，那么关闭当前打开的行
function newSchedule(){
	$("#newScheTemplate1").show();
	$("#newScheTemplate2").show();
	$("#monthScheSave").show();
}
function cancelSchedule(){
	$("#monthScheSave").hide();
	$("#newScheTemplate1").hide();
	$("#newScheTemplate2").hide();
}
var role =1;
function addRole(planId){
	var role_number =$("#wp_role_list_"+planId).find("tr").length;
	var addHtml = ""+
	"<tr id='"+planId+"_tr_role_"+role_number+"'>"+
		"<td width='50px' align='right'>中心：</td>"+
		"<td width='150px'>"+
			"<input id='"+planId+"_center_"+role_number+"' onclick='openSelectCenters(this.id);'/>"+
			"<input type='hidden' name='lstWorkplanRoles["+role_number+"].workplanRoleId'/>"+
			"<input type='hidden' name='lstWorkplanRoles["+role_number+"].recordVersion' value='1'/>"+
			"<input type='hidden' id='"+planId+"_center_"+role_number+"_cd' name='lstWorkplanRoles["+role_number+"].deptCd'/>"+
		"</td>"+
		"<td width='50px' align='right'>角色：</td>"+
		"<td width='50px'>"+
			"<select id='"+planId+"_role_"+role_number+"' name='lstWorkplanRoles["+role_number+"].responsibilityCd'>"+
				"<option value='1'>负责</option>"+
				"<option value='2'>配合</option>"+
				"<option value='3'>协调</option>"+
				"<option value='4'>监督</option>"+
			"</select>"+
		"</td>"+
		"<td style='padding-left:8px;'>"+
			"<button type='button' class='btn_list_detail_del' onmouseout='this.className=\"btn_list_detail_del\"' onmouseover='this.className=\"btn_list_detail_del_hover\"' onclick='$(\"#"+planId+"_tr_role_"+role_number+"\").remove();'>删除</button>"+
		"</td>";
	"</tr>";
	$("#wp_role_list_"+planId).html($("#wp_role_list_"+planId).html()+addHtml);
}
function addResult(planId){
	var result_number =$("#wp_result_list_"+planId).find("tr").length;
	var addHtml = ""+
	"<tr id='"+planId+"_result_"+result_number+"'>"+
		"<td width='50px' align='right' nowrap='nowrap'>成果：</td>"+
	    "<td>"+
	    	"<input type='hidden' name='lstWorkplanResults["+result_number+"].workplanRoleId'/>"+
	    	"<input type='hidden' name='lstWorkplanResults["+result_number+"].recordVersion' value='1'/>"+
	    	"<input type='hidden' name='lstWorkplanResults["+result_number+"].statusCd' value='0'/>"+
	    	"<textarea name='lstWorkplanResults["+result_number+"].content' rows='3' cols='30'></textarea>"+
	    "</td>"+
		"<td style='padding-left:8px;'>";
	if("A_WORKPLAN_ADMIN"==USER_ROLE){
		addHtml += ""+
			"<input type='checkbox' name='lstWorkplanResults["+result_number+"].ifConfirmed' value='1'/>"+
			"<a href='javascript: attachManage(\'${workplanResultId}\', event);'>添加附件</a>";
	}
	addHtml += ""+
	    	"<button type='button' class='btn_list_detail_del' onmouseout='this.className=\"btn_list_detail_del\"' onmouseover='this.className=\"btn_list_detail_del_hover\"' onclick='$(\"#"+planId+"_result_"+result_number+"\").remove();'>删除</button>"+
	    "</td>"+
	"</tr>";
	$("#wp_result_list_"+planId).html($("#wp_result_list_"+planId).html()+addHtml);
}

//打开选择部门窗口的回调函数
function getSelectedDept(){
	if(""!=$("#select_bigSmallCenterOrgPanel").html()){
		$("#select_bigSmallCenterOrgPanel_source").empty().html($("#select_bigSmallCenterOrgPanel").html());
		$("#select_bigSmallCenterOrgPanel").empty();
	}
}
//选择部门的事件
function selectCenter(orgCd,orgName){
	centerValue = PLAN_ID;
	if(centerValue!=""){
		$("#"+centerValue).val(orgName);
		$("#"+centerValue+"_cd").val(orgCd);
	}
	ymPrompt.close();
	if(""!=$("#select_bigSmallCenterOrgPanel").html()){
		$("#select_bigSmallCenterOrgPanel_source").empty().html($("#select_bigSmallCenterOrgPanel").html());
		$("#select_bigSmallCenterOrgPanel").empty();
	}
}
//初始化弹出窗口的部门选择框
function initOpenedSelectCenters(){
	try{
		$("#select_bigSmallCenterOrgPanel #select_big_panel button").click(function() {
			$("#select_bigSmallCenterOrgPanel #select_big_panel button").css("color","");
			$(this).css("color","blue");
			
			var bigSortId = $(this).attr("id");
			var bigSortOrder = bigSortId.split("_")[2];
			var smallSortPanelId = "select_smalls_"+bigSortOrder;

			$("#select_bigSmallCenterOrgPanel #select_small_panel div").hide();
			//document.getElementById(smallSortPanelId).style.display = "block";
			$("#"+smallSortPanelId).show();

			//若没有小中心,返回本大中心
			var size = $("#"+smallSortPanelId).find("button").length;
			if( size == 0){
				selectCenter($(this).attr("orgCd"),$(this).attr("orgName"));
			}
		}); 
	}catch(e){}
}
//
//function saveRole(workplanId){
//	$("#scheForm_"+workplanId+" input[name='content']").val($("#content_input_"+workplanId).val());
//	$("#scheForm_"+workplanId+" input[name='levelCd']").val($("#levelCd_input_"+workplanId).val());
//	$("#scheForm_"+workplanId+" input[name='targetDate']").val($("#targetDate_input_"+workplanId).val());
//	$("#scheForm_"+workplanId).ajaxSubmit(function(result) {
//		$("#succInfoMsg").html("保存成功").show().fadeOut(2000);
//		$("#detail_"+workplanId).hide();
//		$("#scheduleTaskDiv").html(result);
//	});
//}
//新增的保存
function addSaveSchedule(dataId){
	if($("#new_content").val()==""){
		alert("请输入内容 ");
		return false;
	}
	if($("#new_targetDate").val()==""){
		//alert("请输入目标时间  ");
		//return false;
	}
	try{
		$("#newScheForm input[name='targetPointCd']").val($("#new_targetPointCd").val());
		$("#newScheForm input[name='area']").val($("#new_area").val());
	}catch(e){}
	$("#newScheForm input[name='serialNumber']").val($("#new_serialNumber").val());
	$("#newScheForm input[name='serialOrder']").val($("#new_serialOrder").val());
	$("#newScheForm input[name='content']").val($("#new_content").val());
	$("#newScheForm input[name='responCd']").val($("#new_responCd").val());
	$("#newScheForm input[name='targetDate']").val($("#new_targetDate").val());
	$("#newScheForm input[name='newMessage']").val($("#new_newMessage").val());
	//$("#newScheForm input[name='levelCd']").val($("#new_levelCd").val());
	var now_serialOrder = Number($("#new_serialOrder").val());	//现在的序列号
	
	var param = {deptCd:dataId,respDeptCds:dataId};
	$("#newScheForm").attr("action",_ctx+"/plan/workplan!save.action");
	$("#newScheForm").ajaxSubmit(function(result) {
		if (result) {
			now_serialOrder++;	//新增成功，序列号加1
			var new_record_html = '<tr id="main_'+result+'" class="mainTr" style="cursor:pointer;"><td colspan="8"></td></tr>'
				+'<tr id="detail_'+result+'" class="detailTr" style="display:none;"><td colspan="8"></td></tr>';
			$("#newScheTemplate2").after(new_record_html);
			refreshMain(result);
			refreshDetail(result);
			$("#newScheForm input[name='serialOrder']").val(now_serialOrder);
			$("#newScheForm input[name='content']").val("");
			$("#newScheForm input[name='responCd']").val("");
			$("#newScheForm input[name='targetDate']").val("");
			$("#newScheForm input[name='newMessage']").val("");
			$("#new_serialOrder").val(now_serialOrder);
			$("#new_content").val("");
			$("#new_responCd").val("1");
			$("#new_targetDate").val("");
			$("#new_newMessage").val("");
			cancelSchedule();
		}
	});
	$("#pop_bg").show();
}
//修改记录
function saveWorkplan(workplanId,statusCd){
	try{
		$("#scheForm_"+workplanId+" input[name='targetPointCd']").val($("#targetPointCd_hide_"+workplanId).children(0).val());
		$("#scheForm_"+workplanId+" input[name='area']").val($("#area_input_"+workplanId).val());
	}catch(e){}
	$("#scheForm_"+workplanId+" input[name='serialOrder']").val($("#serialOrder_input_"+workplanId).val());
	$("#scheForm_"+workplanId+" input[name='content']").val($("#content_input_"+workplanId).val());
	//$("#scheForm_"+workplanId+" input[name='levelCd']").val($("#levelCd_input_"+workplanId).val());
	if(null!=$("#targetDate_input_"+workplanId).val() && ""!=$("#targetDate_input_"+workplanId).val()){
		$("#scheForm_"+workplanId+" input[name='targetDate']").val($("#targetDate_input_"+workplanId).val());
	}
	if(null!=statusCd && ""!=statusCd){
		$("#scheForm_"+workplanId+" input[name='statusCd']").val(statusCd);
	}
	$("#scheForm_"+workplanId).ajaxSubmit(function(result) {
		if (null!=result && ""!=result) {
			var succMsg = "保存成功";
			switch(statusCd){
				case 0:
					succMsg = "取消成功";
					break;
				case 1:
					succMsg = "确认成功";
					break;
				case 2:
					succMsg = "预完成成功";
					break;
				case 3:
					succMsg = "完成成功";
					break;
				case 4:
					succMsg = "删除成功";
					break;
				case 5:
					succMsg = "隐藏成功";
					break;
			}
			//$("#scheduleTaskDiv").html(result);
			$("#succInfoMsg").html(succMsg).show().fadeOut(2000);
			updateTdStatusHtml(workplanId,statusCd);
			//updateTdrecordVersions(workplanId,result);
			//centerClick();
			refreshMain(workplanId);
			refreshDetail(workplanId);
		}
	});
	$("#pop_bg").show();
}
function refreshMain(planId){
	var target=$("#main_"+planId);
	$.get(_ctx+"/plan/workplan!fetchMain.action?id=" + planId+"&now_orgCd="+NOW_ORGCD, function(result) {
		if (result) {
			target.html(result);
		}
	}); 
}
function refreshDetail(planId){
	var target=$("#detail_"+planId);
	$.get(_ctx+"/plan/workplan!fetchDetail.action?id=" + planId+"&now_orgCd="+NOW_ORGCD, function(result) {
		if (result) {
			target.html(result);
			$("#pop_bg").hide();
		}
	}); 
}
/*
//根据点击的大中心取得中心的按钮
function getlistCenters(orgBizCd){
	var param = {fgCenterCd:orgBizCd};
	$.post("${ctx}/plan/workplan!getCenterListByOrgBizCd.action", param, function(result) { 
		$("#div_listCenters").html(result);
	});
}
//根据点击的大中心取得中心的按钮json方法，参考
function getlistCentersJson(orgBizCd){
	var param = {fgCenterCd:orgBizCd};
	$.post("${ctx}/plan/workplan!getCenterListByOrgBizCdJson.action", param, function(data) { 
		if(!data) return;
		for(var i=0;i<data.length; i++){
			var org = data[i];
			var orgName = org.orgName;
		}
	});
}
*/
function getAllSmallOrgs(){
	var array = $("#small_panel").find("button");
	for(var i=0;i<array.length; i++){
		var btnOrg = array[i];
		var orgCdName = btnOrg.attr("orgCd");
	}
}
//初始化协调的中心名称
function buildDropDownList(id,deptCd){
	var array = $("#small_panel").find("button");
	for(var i=0;i<array.length; i++){
		var btnOrg = array[i];
		var orgCd = $(btnOrg).attr("orgCd");
		var orgName = $(btnOrg).attr("orgName");
		if(deptCd!=null && deptCd!="" && deptCd==orgCd){
			$("#"+id).val(orgName);
			return;
		}
	}
	array = $("#big_panel").find("button");
	for(var i=0;i<array.length; i++){
		var btnOrg = array[i];
		var orgCd = $(btnOrg).attr("orgCd");
		var orgName = $(btnOrg).attr("orgName");
		if(deptCd!=null && deptCd!="" && deptCd==orgCd){
			$("#"+id).val(orgName);
			return;
		}
	}
	$("#"+id+"_button_0").hide();
}
//搜索到用户所处的部门，点击之
function clickUserCenter(){
	var array = $("#small_panel").find("button");
	for(var i=0;i<array.length; i++){
		var btnOrg = array[i];
		var orgCd = $(btnOrg).attr("orgCd");
		var parentOrgCd = $(btnOrg).attr("parentOrgCd");
		if(NOW_ORGCD==orgCd){
			$(btnOrg).click();
			var array2 = $("#big_panel").find("button");
			for(var i=0;i<array2.length; i++){
				var btnOrg2 = array2[i];
				var orgCd2 = $(btnOrg2).attr("orgCd");
				if(parentOrgCd==orgCd2){
					$(btnOrg2).click();
					return;
				}
			}
			return;
		}
	}
	array = $("#big_panel").find("button");
	for(var i=0;i<array.length; i++){
		var btnOrg = array[i];
		var orgCd = $(btnOrg).attr("orgCd");
		if(NOW_ORGCD==orgCd){
			$(btnOrg).click();
			return;
		}
	}
}
function updateSerialOrder(itemId) {
	var inputEle = $("#serialOrder_show_" + itemId);
	var inputHide = $("#serialOrder_hide_" + itemId);
	var inputInp = $("#serialOrder_input_" + itemId);
	var inputSpan = $("#serialOrder_span_" + itemId);
	
	var oldVal = $.trim(inputSpan.text());
	var newVal = $.trim(inputInp.val());
	if (oldVal != newVal) {
		updateRecord(itemId, "serialOrder", newVal, 'workplan!saveSerialOrder.action');
		inputSpan.html(newVal);
		inputEle.show();
		inputHide.hide();
	} else {
		inputEle.show();
		inputHide.hide();
	}
}
function updateArea(itemId) {
	var inputEle = $("#area_show_" + itemId);
	var inputHide = $("#area_hide_" + itemId);
	var inputInp = $("#area_input_" + itemId);
	
	var oldVal = $.trim(inputEle.text());
	var newVal = $.trim(inputInp.val());
	if (oldVal != newVal) {
		updateRecord(itemId, "area", newVal, 'workplan!saveArea.action');
		inputEle.html(newVal).show();
		inputHide.hide();
	} else {
		inputEle.show();
		inputHide.hide();
	}
}
function updateContent(itemId) {
	var inputEle = $("#content_show_" + itemId);
	var inputHide = $("#content_hide_" + itemId);
	var inputInp = $("#content_input_" + itemId);
	
	var oldVal = $.trim(inputEle.text());
	var newVal = $.trim(inputInp.val());
	if (oldVal != newVal) {
		updateRecord(itemId, "content", newVal, 'workplan!saveContent.action');
		inputEle.html(newVal).show();
		inputHide.hide();
	} else {
		inputEle.show();
		inputHide.hide();
	}
}
/*
function updateLevelCd(itemId) {
	var inputEle = $("#levelCd_show_" + itemId);
	var inputHide = $("#levelCd_hide_" + itemId);
	var inputInp = $("#levelCd_input_" + itemId);
	
	var oldVal = $.trim(inputEle.text());
	var newVal = $.trim(inputInp.val());
	
	if (oldVal != newVal) {
		updateRecord(itemId, "levelCd", newVal, 'workplan!saveLevelCd.action');
		var newVal_text = "";
		if(1==newVal){
			newVal_text = "低";
		}else if(2==newVal){
			newVal_text = "普通";
		}else if(3==newVal){
			newVal_text = "重要";
		}
		inputEle.html(newVal_text).show();
		inputHide.hide();
	} else {
		inputEle.show();
		inputHide.hide();
	}
}
*/
function updateTargetDate(itemId) {
	var inputEle = $("#targetDate_show_" + itemId);
	var inputHide = $("#targetDate_hide_" + itemId);
	var inputInp = $("#targetDate_input_" + itemId);
	
	var oldVal = $.trim(inputEle.text());
	var newVal = $.trim(inputInp.val());

	if (newVal != "") {
		inputInp.val("");
		inputHide.hide();
		inputEle.html(newVal.substr(newVal.indexOf("-") + 1)).show();
		updateRecord(itemId, "targetDate", newVal, 'workplan!saveTargetDate.action');
	}
}
function updateStatusCd(itemId,statusCd) {
	var sourceStatusCd = $("#scheForm_"+itemId+" input[name='statusCd']").val();
	var targetStatusCd = 0;
	if(null!=statusCd){
		targetStatusCd = statusCd;
		updateRecord(itemId, "statusCd", targetStatusCd, 'workplan!saveStatusCd.action');
		updateTdStatusHtml(itemId, targetStatusCd);
		$("#scheForm_"+itemId+" input[name='statusCd']").val(targetStatusCd);
	}else{
		//记录列里直接点击图标进行确定/未确定
		if (0==sourceStatusCd || 1==sourceStatusCd) {
			if(0==sourceStatusCd){
				targetStatusCd = 1;
			}else if(1==sourceStatusCd){
				targetStatusCd = 0;
			}
			updateRecord(itemId, "statusCd", targetStatusCd, 'workplan!saveStatusCd.action');
			updateTdStatusHtml(itemId, targetStatusCd);
			$("#scheForm_"+itemId+" input[name='statusCd']").val(targetStatusCd);
		} else {
			scheClick(itemId);
		}
	}
}
//更新记录的statusCd和显示出来的图标
function updateTdStatusHtml(itemId,statusCd){
	var html_str = "";
	if(null!=statusCd){
		switch(statusCd){
			case 0:
				html_str = "<img src='"+_ctx+"/images/plan/pic_noConfirm.gif' title='未确认'>";
				break;
			case 1:
				html_str = "<img src='"+_ctx+"/images/plan/pic_confirm.gif' title='已确认'>";
				break;
			case 2:
				html_str = "<img src='"+_ctx+"/images/plan/pic_preFinish.gif' title='预完成'>";
				break;
			case 3:
				html_str = "<img src='"+_ctx+"/images/plan/pic_finish.gif' title='已完成'>";
				break;
			case 4:
				html_str = "已删除";
				break;
			case 5:
				html_str = "<img src='"+_ctx+"/images/plan/pic_hide.gif' title='已隐藏'>";
				break;
		}
		$("#scheForm_"+itemId+" input[name='statusCd']").val(statusCd);
		$("#td_statusCd_"+itemId).html(html_str);
	}
}

function saveMessage(planId){
	var content =$("#"+planId+"_message").val();
	if($.trim(content).length>0){
		$.post(_ctx+"/plan/workplan!saveMessage.action",
				{
			     id:planId,
			     content:content
				},
				 function(result) {
					 if (result == "ok") {
					 refreshContent(planId);
					 $("#"+planId+"_message").val("");
					 }else if(result =="no"){
					 }
				 });
	}
}
function refreshContent(planId){
	var target=$("#"+planId+"_messageDiv");
	$.get(_ctx+"/plan/workplan!fetchContent.action?id=" + planId, function(result) {
		if (result) {
			target.html(result);
			$("#pop_bg").hide();
		}
	}); 
}
function deleteRole(workplanId,roleId,userDeptCd){
	if(roleId!=null){
		if(confirm("确定要删除这条记录吗？")){
			var param = {id:roleId,centerCd:userDeptCd};
			$.post(_ctx+"/plan/workplan!deleteRole.action",param, function(result) {
				refreshDetail(workplanId);
			});
			$("#pop_bg").show();
		}
	}
}
function deleteResult(workplanId,resultId,userDeptCd){
	if(resultId!=null){
		if(confirm("确定要删除这条记录吗？")){
			var param = {id:resultId,centerCd:userDeptCd};
			$("#scheForm").addClass("waiting");
			$.post(_ctx+"/plan/workplan!deleteResult.action",param, function(result) {
				refreshDetail(workplanId);
			});
			$("#pop_bg").show();
		}
	}
}

var CAN_toggleDetail = true;	//是否能toggleDetail,用来屏蔽输入框的父对象的点击事件
//显示/隐藏细项
function toggleDetail(detail_obj,workplanId){
	if(can_edit_record){
		if(CAN_toggleDetail){
			$(detail_obj).find('.span_show').toggle();
			$(detail_obj).find('.span_hide').toggle();
		}else{
			CAN_toggleDetail = true;
		}
		if(null!=workplanId){
			closePrevDetail(workplanId);
		}
	}
}
function toggleDetail2(toggleName,workplanId){
	if(can_edit_record){
		if(CAN_toggleDetail){
			$("#"+toggleName).find('.span_show').toggle();
			$("#"+toggleName).find('.span_hide').toggle();
		}else{
			CAN_toggleDetail = true;
		}
		if(null!=workplanId){
			closePrevDetail(workplanId);
		}
	}
}
function hideDetail(toggleName){
	if(can_edit_record){
		if(CAN_toggleDetail){
			$("#"+toggleName).find('.span_show').show();
			$("#"+toggleName).find('.span_hide').hide();
		}else{
			CAN_toggleDetail = true;
		}
	}
}
//关闭上一个打开的内容的详细条目
function closePrevDetail(scheId){
	try{
		if(""!=closePrevDetail && opened_td_id!=scheId){
			hideDetail("td_serialOrder_"+opened_td_id);
			hideDetail("td_targetPointCd_"+opened_td_id);
			hideDetail("td_area_"+opened_td_id);
			hideDetail("td_content_"+opened_td_id);
			hideDetail("td_targetDate_"+opened_td_id);
			opened_td_id = scheId;
		}
	}catch(e){}
}
var oldId="";
var CAN_scheClick = true;	//是否能scheClick,用来屏蔽输入框的父对象的点击事件
function scheClick(scheId){
	if(CAN_scheClick){
		var detailObj = document.getElementById("detail_"+scheId);
		if(detailObj.style.display == "none"){
			if(oldId!="" && oldId!=scheId){
				try{
					$("#detail_"+oldId).hide();
					$("#main_"+oldId).css("backgroundColor","#fff");
					$("#main_"+oldId+" td").css("color","#161616");
				}catch(e){}
			}
			$(detailObj).show();
			$("#down_arrow_"+scheId).hide();
			$("#up_arrow_"+scheId).show();
			$("#main_"+scheId).css("backgroundColor","#eee");
			$("#main_"+scheId+" td").css("color","#40a3de");
			$("#content_show_"+scheId).removeClass("ellipsisDiv_full");
			$("#content_show_"+scheId).css("height","auto");
		}else{
			$(detailObj).hide();
			$("#down_arrow_"+scheId).show();
			$("#up_arrow_"+scheId).hide();
			/*
			if("none"!=document.getElementById("levelCd_hide_"+scheId).style.display){
				document.getElementById("levelCd_hide_"+scheId).style.display = "none";
				document.getElementById("levelCd_show_"+scheId).style.display = "block";
			}
			*/
			if("none"!=document.getElementById("content_hide_"+scheId).style.display){
				document.getElementById("content_hide_"+scheId).style.display = "none";
				document.getElementById("content_show_"+scheId).style.display = "block";
			}
			try{
				if("none"!=document.getElementById("targetDate_hide_"+scheId).style.display){
					document.getElementById("targetDate_hide_"+scheId).style.display = "none";
					document.getElementById("targetDate_show_"+scheId).style.display = "block";
				}
			}catch(e){}
			try{
				hideDetail("td_serialOrder_"+scheId);
			}catch(e){}
			try{
				hideDetail("td_targetPointCd_"+scheId);
			}catch(e){}
			try{
				hideDetail("td_area_"+scheId);
			}catch(e){}
			try{
				hideDetail("td_content_"+scheId);
			}catch(e){}
			try{
				hideDetail("td_targetDate_"+scheId);
			}catch(e){}
			$("#main_"+scheId).css("backgroundColor","#fff");
			$("#main_"+scheId+" td").css("color","#161616");
			$("#content_show_"+scheId).addClass("ellipsisDiv_full");
			$("#content_show_"+scheId).css("height","35px");
		}
		oldId =scheId;
	}else{
		CAN_scheClick = true;
	}
}

var PLAN_ID = "";	//选择中心的PLANID
function openSelectCenters(planId){
	PLAN_ID = planId;
	ymPrompt.win({
		icoCls:"",
		title:"请选择部门",
		message:"<div id='select_bigSmallCenterOrgPanel'></div>",
		useSlide:true,
		winPos:"c",
		width:700,
		height:200,
		allowRightMenu:true,
		handler:getSelectedDept,
		afterShow:function(){
			if(""!=$("#select_bigSmallCenterOrgPanel_source").html()){
				//如果弹出窗口html的source没有清空，就转移弹出窗口的html
				$("#select_bigSmallCenterOrgPanel").empty().html($("#select_bigSmallCenterOrgPanel_source").html());
				$("#select_bigSmallCenterOrgPanel_source").empty();
				initOpenedSelectCenters();
			}
	}
	});
}
//根据点击的部门，刷新数据列表
var NOW_ORGNAME = "";
var NOW_ORGCD = "";
var NOW_SHORTORGNAME = "";
var NOW_PARENT_ORGCD = "";
var NOW_PARENT_SHORTORGNAME = "";
var IF_IN_LOADING = false;	//是否正在部门的切换中，如果是，屏蔽切换部门的动作
function centerClick(orgCd,parentOrgCd,shortOrgName,parentShortOrgName,orgName){
	if(!IF_IN_LOADING){
		IF_IN_LOADING = true;
		if(null!=orgCd && ""!=orgCd){
			NOW_ORGCD = orgCd;
		}
		if(null!=orgName && ""!=orgName){
			NOW_ORGNAME = orgName;
		}
		if(null!=parentOrgCd && ""!=parentOrgCd){
			NOW_PARENT_ORGCD = parentOrgCd;
		}
		if(null!=shortOrgName){
			NOW_SHORTORGNAME = shortOrgName;
		}
		if(null!=parentShortOrgName){
			NOW_PARENT_SHORTORGNAME = parentShortOrgName;
		}
		var page_no = 1;
		try{
//			if(1==$("#if_search_all").val()){
//				$("#td_page").show();
//				if($("#pageNo")){
//					page_no = Number($("#pageNo").val());
//				}
//			}else{
//				$("#td_page").hide();
//			}
			if($("#pageNo") && undefined!=$("#pageNo").val()){
				page_no = Number($("#pageNo").val());
			}
			if(0==page_no){
				page_no = 1;
			}
		}catch(e){page_no=1}
		var data = {
				now_orgCd : NOW_ORGCD,
				now_parent_orgCd : NOW_PARENT_ORGCD,
				now_shortOrgName : NOW_SHORTORGNAME,
				now_parent_shortOrgName : NOW_PARENT_SHORTORGNAME,
				"page.pageNo" : page_no,
				filter_LIKES_serialNumber : $("#filter_LIKES_serialNumber").val(),
				filter_LIKES_content : $("#filter_LIKES_content").val(),
				search_statusCd : $("#search_statusCd").val(),
				filter_GED_createdDate : $("#filter_GED_createdDate").val(),
				filter_LTD_createdDate : $("#filter_LTD_createdDate").val(),
				filter_GED_targetDate : $("#filter_GED_targetDate").val(),
				filter_LTD_targetDate : $("#filter_LTD_targetDate").val(),
				filter_GED_endDate : $("#filter_GED_endDate").val(),
				filter_LTD_endDate : $("#filter_LTD_endDate").val(),
				if_search_all : $("#if_search_all").val(),
				orderStr1 : $("#orderStr1").val(),
				orderStr2 : $("#orderStr2").val(),
				orderDir1 : $("#orderDir1").val(),
				orderDir2 : $("#orderDir2").val()
		};
		$("#scheduleTaskDiv").html("<div style='height:100px'></div><table width='100%'><tr><td align='center'><img src='"+_ctx+"/images/loading.gif'/></td></tr></table>");
		$.post(_ctx+"/plan/workplan!list.action", data, function(result) {
			$("#scheduleTaskDiv").html(result);
			IF_IN_LOADING = false;
		});
	}
}
//页面跳转函数的覆盖
function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	centerClick();
}
//清空所有搜索选项
function clearAllSearch(){
	$("#filter_LIKES_content").val("");
	$("#search_statusCd").val("");
	$("#filter_GED_createdDate").val("");
	$("#filter_LTD_createdDate").val("");
	$("#filter_GED_targetDate").val("");
	$("#filter_LTD_targetDate").val("");
	$("#filter_GED_endDate").val("");
	$("#filter_LTD_endDate").val("");
}
// 异步保存记录信息
function updateRecord(itemId, type, newVal, url, dom) {
	var param = null;
	var msg = "";
	
	switch (type) {
		case "serialOrder" :
			param = {"id" : itemId, "serialOrder" : newVal};
			msg = "顺序修改成功";
			break;
		case "content" :
			param = {"id" : itemId, "content" : newVal};
			msg = "内容修改成功";
			break;
		case "levelCd" :
			param = {"id" : itemId, "levelCd" : newVal};
			msg = "重要性修改成功";
			break;
		case "targetDate" :
			param = {"id" : itemId, "targetDate" : newVal};
			msg = "目标日期修改成功";
			break;
		case "area" :
			param = {"id" : itemId, "area" : newVal};
			msg = "地区修改成功";
			break;
		case "statusCd" :
			param = {"id" : itemId, "statusCd" : newVal};
			switch(newVal){
				case 0:
					msg = "未确认成功";
					break;
				case 1:
					msg = "确认成功";
					break;
				case 2:
					msg = "预完成成功";
					break;
				case 3:
					msg = "完成成功";
					break;
				case 4:
					msg = "删除成功";
					break;
				case 5:
					msg = "隐藏成功";
					break;
			}
			break;
		case "preComplete" :
			param = {"id" : itemId, "status" : newVal};
			msg = "已设置为预完成状态";
			break;
		case "complete" :
			param = {"id" : itemId, "status" : newVal};
			msg = "已设置为完成状态";
			break;
		case "delete" :
			if (confirm("确认删除？")) {
				param = {"id" : itemId, "isDeleted" : newVal};
				msg = "已删除";
				$("#" + itemId + "item").next().remove();
				$("#" + itemId + "item").remove();
			}
			break;
		case "hide" :
			param = {"id" : itemId, "hiddenFlg" : newVal};
			msg = "已设置为隐藏";
			$("#" + itemId + "item").next().remove();
			$("#" + itemId + "item").remove();
			break;
		case "show" :
			param = {"id" : itemId, "hiddenFlg" : newVal};
			msg = "已设置为被显示";
			break;
	}
	if (param) {
		$.post(url, param, function(result) {
			$("#succInfoMsg").html(msg).show().fadeOut(2000);
			$("#scheForm_"+itemId+" input[name='recordVersion']").val(result);
			$("#td_updateDate_"+itemId).html(nowDate.substring(5,nowDate.length));
//			if (result == "done") {
//				$("#succInfoMsg").html(msg).show().fadeOut(2000);
//				if(type == "preComplete" || type == "complete"){
//					//refreshMeetingList();
//				}
//			}
		});
	}
}
/*
function doClickOrder1(){
	var order_dir1 = $("#orderDir1").val();
	switch (order_dir1) {
		case "" :
			$("#orderStr1").val("levelCd");
			$("#orderDir1").val("ASC");
			$("#order_levelCd").html("<img src='"+_ctx+"/images/plan/btn_up_10_10.gif'/>");
			break;
		case "ASC" :
			$("#orderStr1").val("levelCd");
			$("#orderDir1").val("DESC");
			$("#order_levelCd").html("<img src='"+_ctx+"/images/plan/btn_down_10_10.gif'/>");
			break;
		case "DESC" :
			$("#orderStr1").val("");
			$("#orderDir1").val("");
			$("#order_levelCd").html("");
			break;
	}
	if(!IF_IN_LOADING){
		setTimeout("IF_IN_LOADING=false;centerClick();",1000);
		IF_IN_LOADING = true;
	}
}
*/
function doClickOrder2(orderStr2_id){
	var order_str2 = $("#orderStr2").val();
	if(order_str2!=orderStr2_id){
		$("#orderStr2").val(orderStr2_id);
		$("#orderDir2").val("ASC");
		$("#order_"+orderStr2_id).html("<img src='"+_ctx+"/images/plan/btn_up_10_10.gif'/>");
		switch (orderStr2_id) {
			case "targetDate" :
				$("#order_updatedDate").html("");
				break;
			case "updatedDate" :
				$("#order_targetDate").html("");
				break;
			case "statusCd" :
				$("#order_statusCd").html("");
				break;
		}
	}else{
		var order_dir2 = $("#orderDir2").val();
		switch (order_dir2) {
			case "" :
				$("#orderStr2").val(orderStr2_id);
				$("#orderDir2").val("ASC");
				$("#order_"+orderStr2_id).html("<img src='"+_ctx+"/images/plan/btn_up_10_10.gif'/>");
				break;
			case "ASC" :
				$("#orderStr2").val(orderStr2_id);
				$("#orderDir2").val("DESC");
				$("#order_"+orderStr2_id).html("<img src='"+_ctx+"/images/plan/btn_down_10_10.gif'/>");
				break;
			case "DESC" :
				$("#orderStr2").val("");
				$("#orderDir2").val("");
				$("#order_"+orderStr2_id).html("");
				break;
		}
	}
	if(!IF_IN_LOADING){
		setTimeout("IF_IN_LOADING=false;centerClick();",1000);
		IF_IN_LOADING = true;
	}
}
//批量操作
function doUpdateStatusAll(statusCd){
	var checkbox_ids = new Array();
	var checkbox_chkIds = new Array();
	$("input[type=checkbox][id='chk_all']:checked").each(function(i, dom) {
		checkbox_ids.push($(dom).val());
		checkbox_chkIds.push("chkIds=" + $(dom).val());
	});
	if(checkbox_ids.length == 0){
		alert("请勾选需要操作的记录");
		return false;
	}
	var param = checkbox_chkIds.join("&");
	param = param +"&statusCd="+statusCd;
	$.post(_ctx+"/plan/workplan!doUpdateStatusAll.action", param, function(result) {
		if(result == "error"){
			alert("操作失败！");
			return;
		}
		if (result == "done") {
			$("#succInfoMsg").html("操作成功").show().fadeOut(2000);
			for(var i=0;null!=checkbox_ids && i<checkbox_ids.length;i++){
				updateTdStatusHtml(checkbox_ids[i],statusCd);
			}
			centerClick();
		}
	});
}
//交换顺序
function doExchangeOrder(){
	var checkbox_ids = new Array();
	var checkbox_chkIds_ids = new Array();
	var checkbox_orders = new Array();
	var checkbox_chkIds_orders = new Array();
	$("input[type=checkbox][id='chk_all']:checked").each(function(i, dom) {
		checkbox_ids.push($(dom).val());
		checkbox_chkIds_ids.push("chkIds=" + $(dom).val());
		checkbox_orders.push($("#serialOrder_input_"+$(dom).val()).val());
		checkbox_chkIds_orders.push("chkOrders=" + $("#serialOrder_input_"+$(dom).val()).val());
	});
	if(checkbox_ids.length != 2){
		alert("请选择2条记录进行交换顺序");
		return false;
	}
	//交换顺序
	var changeTemp = checkbox_orders[0];
	checkbox_orders[0] = checkbox_orders[1];
	checkbox_orders[1] = changeTemp;
	changeTemp = checkbox_chkIds_orders[0];
	checkbox_chkIds_orders[0] = checkbox_chkIds_orders[1];
	checkbox_chkIds_orders[1] = changeTemp;
	
	var param = checkbox_chkIds_ids.join("&");
	var param2 = checkbox_chkIds_orders.join("&");
	$.post(_ctx+"/plan/workplan!doExchangeOrder.action", param+"&"+param2, function(result) {
		if(result == "error"){
			alert("交换顺序失败！");
			return;
		}
		if (result == "done") {
			$("#succInfoMsg").html("交换顺序成功").show().fadeOut(2000);
			$("#serialOrder_input_"+checkbox_ids[0]).val(checkbox_orders[0]);
			$("#serialOrder_input_"+checkbox_ids[1]).val(checkbox_orders[1]);
			//$("#serialOrder_show_"+checkbox_ids[0]).html($("#scheForm_"+checkbox_ids[0]+" input[name='serialNumber']").val()+"-"+checkbox_orders[0]);
			//$("#serialOrder_show_"+checkbox_ids[1]).html($("#scheForm_"+checkbox_ids[1]+" input[name='serialNumber']").val()+"-"+checkbox_orders[1]);
			$("#serialOrder_show_"+checkbox_ids[0]).html(checkbox_orders[0]);
			$("#serialOrder_show_"+checkbox_ids[1]).html(checkbox_orders[1]);
		}
	});
}
//点击全选按钮
function checkedAll(flag){
	$("input[id='chk_all']").attr("checked",flag);
}

//选择月计划
function monthSelect(workplanId) {
	jQuery.Event(event).stopPropagation();
	var data = {
			workplanId : workplanId,
			NOW_ORGCD : NOW_ORGCD,
			NOW_ORGNAME : NOW_ORGNAME
	};
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"选择月计划",
		message:"<div id='monthSelectDiv'><img align='absMiddle' src='" + _ctx + "/images/loading.gif'></div>",
		useSlide:true,
		winPos:"c",
		width:800,
		height:600,
		maxBtn: false,
		allowRightMenu:true,
		afterShow:function(){
			$.post(_ctx+"/plan/workplan-month!getAllPlanSelect.action", data, function(result) {
				$("#monthSelectDiv").html(result);
			});
		},
		handler:function(e){
			if("ok"==e){
				var selectedId = $("input[id='radio_select_workplanMonthId']:checked").val();
				if(undefined!=$("input[id='radio_select_workplanMonthId']:checked").val()){
					var param = {"workplanId":workplanId, "deptCd":NOW_ORGCD, "workplanMonthId":selectedId};
					if (param) {
						$.post(_ctx+"/plan/workplan!addRelation.action", param, function(result) {
							if("success"==result){
								$("#succInfoMsg").html("绑定月计划成功").show().fadeOut(2000);
								refreshDetail(workplanId);
							}
						});
					}
				}
			}
		}
	});
}

//附件管理
function attachManage(bizEntityId, event) {
	jQuery.Event(event).stopPropagation();
	/*
	ymPrompt.win({
		message:"${ctx}/app/app-attachment!list.action?bizEntityId="+bizEntityId+"&bizModuleCd=workplan&filterType=image|office&bizEntityName=Workplan",
		width:500,
		height:300,
		title:'附件管理',
		iframe:true
		});
	*/
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"附件管理",
		message:"<div id='attachDiv'><img align='absMiddle' src='" + _ctx + "/images/loading.gif'></div>",
		useSlide:true,
		winPos:"t",
		width:400,
		height:400,
		maxBtn: false,
		allowRightMenu:true,
		afterShow:function(){
			$.get(_ctx + "/oa/oa-meeting!attachMents.action?bizEntityId=" + bizEntityId,
					function(result){
						$("#attachDiv").html(result);
			});
		}
	});
	
}

//上传附件，上传完毕后更新附件区域信息
function uploadAttachment() {
	var uploadedAttach = $("#uploadInput").val();
	if (jQuery.trim(uploadedAttach).length == 0) {
		return false;
	}
	var bizEntityId = $("#attachForm input:hidden[name='bizEntityId']").val();
	$("#attachForm").ajaxSubmit(function(result) {
		if (result) {
			refreshAttachment(bizEntityId);
		}
	});
}
//删除附件，删除完毕后更新附件管理页面
function deleteAttachment(fileId, bizModuleCd, bizEntityId) {
	var url = _ctx + "/app/app-attachment!delete.action";
	$.post(url,
		{
			id:				fileId,
			bizModuleCd: 	"oaMeeting",
			bizEntityId:	bizEntityId
		},
		function(result) {
			if (result) {
				refreshAttachment(bizEntityId);
			}
		}
	);
	return false;
}
//更新附件管理页面信息
function refreshAttachment(bizEntityId) {
	var url = _ctx + "/oa/oa-meeting!attachMents.action?bizEntityId=" + bizEntityId;
	$.get(url,
		function(result) {
			$("#attachDiv").html(result);
		}
	);
}
//菜单的移动方法
function doMenuDivMove(div_id,move_num){
	var num_source = $("#"+div_id).css("marginLeft");
	num_source = Number(num_source.substring(0,num_source.length-2));
	var num_target = num_source + move_num;
	if(num_target<=0){
		$("#"+div_id).animate({"marginLeft":num_target+"px"},200);
	}
}
