<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/global.jsp" %>
<style>
<!--
.month_big{
	width:24px;
	cursor:normal;
	font-size:20px;
	font-weight:bolder;
	background:none;
	border:none;
}
.month_small{
	width:16px;
	cursor:pointer;
	font-size:14px;
	font-weight:normal;
	background:none;
	border:none;
}
-->
</style>

<script type="text/javascript">
//月份的点击
var now_year = 0;
var now_month = 0;
function monthPlanSearch(toMonth){
	if(now_month != toMonth){
		now_month = toMonth;
	}
	for(var i=1;i<13;i++){
		$("#month"+i).removeClass("month_big");
		$("#month"+i).removeClass("color_red");
		$("#month"+i).addClass("month_small");
	}
	$("#month"+toMonth).removeClass("month_small");
	$("#month"+toMonth).addClass("month_big");
	$("#month"+toMonth).addClass("color_red");
	yearPlanSearch();
}

function yearPlanSearch(){
	var year  = now_year;
	var month = now_month;
	$.get("${ctx}/plan/plan-work2!costCtrl.action?if_in_weight=0&centerCd=17&now_year="+year+"&now_month="+month,function(r){
		$('#cost-container').html(r);
		$.get("${ctx}/plan/exec-plan!getCostCtrl.action",function(result) {
				$("#cost-plan-exec").html(result);
				$('#btn_add_cost').hide();
			}
		);
	});
}
function planMonthClick(id){
	var _this=$("#planWork_"+id);
	_this.siblings('.click').trigger('click');
	if(_this.next().css('display') == 'none'){
		_this.addClass('click').next().show();
		autoHeight();
	}else{
		_this.removeClass('click').next().hide();
	}
}
//保存留言
function saveMessage(planWork2Id,isAttentioned){
	//var isAttentioned = $("#scheForm_"+planWork2Id+" input[name='isAttentioned']").val();
	var content =$("#"+planWork2Id+"_message").val();
	if($.trim(content).length>0){
		$.post("${ctx}/plan/plan-work2!saveMessageByCtrl.action",
				{
			     id:planWork2Id,
			     content:content,
			     isAttentioned:isAttentioned
				},
				 function(result) {
					$("#div_message_"+planWork2Id).html(result);
					$("#" + planWork2Id + "_message").val("");
				 });
	}
}
//对成本任务里的任务进行收缩
function divOnClick(divId){
	if(divId){
		if($("#div_task_detail_"+divId).css("display") == "none"){
		    $("#div_task_detail_"+divId).show();
		    $("#div_task_"+divId).removeClass("big_drop_up").addClass("big_drop_down");
		}else{
			$("#div_task_detail_"+divId).hide();
			$("#div_task_"+divId).removeClass("big_drop_down").addClass("big_drop_up");
		}	
	}
}

function updateStatusCd(planWork2Id,statusCd) {
	var sourcePlanType = $("#scheForm_"+planWork2Id+" input[name='planType']").val();
	var sourceStatusCd = $("#scheForm_"+planWork2Id+" input[name='statusCd']").val();
	var targetStatusCd = 0;
	if(null!=statusCd){
		targetStatusCd = statusCd;
		updateRecord(planWork2Id, "statusCd", targetStatusCd);
		updateTdStatusHtml(planWork2Id, targetStatusCd);
		$("#scheForm_"+planWork2Id+" input[name='statusCd']").val(targetStatusCd);
	}else{
		//记录列里直接点击图标进行确定/未确定
		if (0==sourceStatusCd || 1==sourceStatusCd) {
			if(0==sourceStatusCd){
				targetStatusCd = 1;
			}else if(1==sourceStatusCd){
				targetStatusCd = 0;
			}
			updateRecord(planWork2Id, "statusCd", targetStatusCd);
			updateTdStatusHtml(planWork2Id, targetStatusCd);
			$("#scheForm_"+planWork2Id+" input[name='statusCd']").val(targetStatusCd);
		} else {
			scheClick(planWork2Id);
		}
	}
}

var now_year = "${now_year}";	//当前的年份
var now_month = "${now_month}";	//当先的月份
//异步保存记录信息
function updateRecord(planWork2Id, type, newVal) {
	var record_planYear = $("#scheForm_"+planWork2Id+" input[name='planYear']").val();
	var record_planMonth = $("#scheForm_"+planWork2Id+" input[name='planMonth']").val();
	var param = null;
	var msg = "";
	var oldVal = "";
	try{
		oldVal = $("#scheForm_"+planWork2Id+" input[name='"+type+"']").val();
	}catch(e){}
	if(oldVal==newVal && !(type=="statusCd" && newVal==8)){
		return;
	}
	if("targetDate"==type && (""==newVal||null==newVal)){
		return;
	}
	var myWeightPoint = new Number(0);
	switch (type) {
		case "statusCd" :
			var tempMessage = "";
			switch(newVal){
				case 0:
					msg = "未确认成功";
					tempMessage = "未确认";
					break;
				case 1:
					msg = "确认成功";
					tempMessage = "确认";
					break;
				case 2:
					msg = "预完成成功";
					tempMessage = "预完成";
					break;
				case 3:
					msg = "申请删除成功";
					tempMessage = "申请删除";
					break;
				case 4:
					msg = "完成成功";
					tempMessage = "完成";
					break;
				case 5:
					msg = "删除成功";
					tempMessage = "删除";
					break;
				case 6:
					msg = "隐藏成功";
					tempMessage = "隐藏";
					break;
				case 7:
					msg = "非本月";
					tempMessage = "非本月";
					break;
				case 8:
					msg = "过期成功";
					record_planMonth++;
					tempMessage = "过期到"+record_planMonth+"月";
					break;
				case 9:
					msg = "非考核性过期成功";
					record_planMonth++;
					if(13==record_planMonth){
						record_planMonth=1;
					}
					tempMessage = "非考核性过期到"+record_planMonth+"月";
					break;
			}
			param = {"id" : planWork2Id,"planYear":now_year,"planMonth":now_month, "statusCd" : newVal, "newMessage":"【修改了状态为"+tempMessage+"】"};
			break;
		case "delete" :
			if (confirm("确认删除？")) {
				param = {"id" : planWork2Id, "isDeleted" : newVal};
				msg = "已删除";
				$("#" + planWork2Id + "item").next().remove();
				$("#" + planWork2Id + "item").remove();
			}
			break;
	}
	if (param) {
		$.post(_ctx+"/plan/plan-work2!save.action", param, function(result) {
			if("failure"==result){
				return;
			}
			if("statusCd"==type){
				switch(newVal){
					case 0:
						$("#statusCd_"+planWork2Id).html('<img src="${ctx}/resources/images/common/status_unconfirm.gif" title="未确认">');
						break;
					case 1:
						$("#statusCd_"+planWork2Id).html('<img src="${ctx}/resources/images/common/status_confirm.gif" title="进行中">');
						break;
					case 2:
						$("#statusCd_"+planWork2Id).html('<img src="${ctx}/resources/images/common/status_prefinish.gif" title="预完成">');
						break;
					case 3:
						$("#statusCd_"+planWork2Id).html('<img src="${ctx}/resources/images/common/status_predel.gif" title="申请删除">');
						break;
					case 4:
						$("#statusCd_"+planWork2Id).html('<img src="${ctx}/resources/images/common/status_finish.gif" title="已完成">');
						break;
					case 5:
						$("#statusCd_"+planWork2Id).html('<img src="${ctx}/resources/images/common/status_del.gif" title="已删除">');
						break;
					case 6:
						$("#statusCd_"+planWork2Id).html('<img src="${ctx}/resources/images/common/status_hidden.gif" title="已隐藏">');
						break;
					case 7:
						$("#statusCd_"+planWork2Id).html('非本月');
						break;
					case 8:
						$("#statusCd_"+planWork2Id).html('<img src="${ctx}/resources/images/common/status_suspend.gif" title="已过期">');
						break;
					case 9:
						$("#statusCd_"+planWork2Id).html('非考核性过期');
						break;
				}
			}
		});
	}
}
</script>

<div>
<div class="title_bar">
	<div class="title_bar_h big_drop_down project_row_item" id="div_task_center"><span onclick="divOnClick('center');">成本控制中心</span>&nbsp;&nbsp;
		<span class="color_black">
		<select id="select_now_year" onchange="now_year=$(this).val();yearPlanSearch();">
			<option value="2011" <s:if test="now_year==2011"> selected="selected"</s:if>>2011</option>
			<option value="2012" <s:if test="now_year==2012"> selected="selected"</s:if>>2012</option>
			<option value="2013" <s:if test="now_year==2013"> selected="selected"</s:if>>2013</option>
			<option value="2014" <s:if test="now_year==2014"> selected="selected"</s:if>>2014</option>
			<option value="2015" <s:if test="now_year==2015"> selected="selected"</s:if>>2015</option>
			<option value="2016" <s:if test="now_year==2016"> selected="selected"</s:if>>2016</option>
			<option value="2017" <s:if test="now_year==2017"> selected="selected"</s:if>>2017</option>
			<option value="2018" <s:if test="now_year==2018"> selected="selected"</s:if>>2018</option>
			<option value="2019" <s:if test="now_year==2019"> selected="selected"</s:if>>2019</option>
			<option value="2020" <s:if test="now_year==2020"> selected="selected"</s:if>>2020</option>
		</select>
		年
		<button id="month1"  onClick="monthPlanSearch(1)"  <s:if test="now_month==1">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>1</button>
		<button id="month2"  onClick="monthPlanSearch(2)"  <s:if test="now_month==2">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>2</button>
		<button id="month3"  onClick="monthPlanSearch(3)"  <s:if test="now_month==3">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>3</button>
		<button id="month4"  onClick="monthPlanSearch(4)"  <s:if test="now_month==4">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>4</button>
		<button id="month5"  onClick="monthPlanSearch(5)"  <s:if test="now_month==5">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>5</button>
		<button id="month6"  onClick="monthPlanSearch(6)"  <s:if test="now_month==6">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>6</button>
		<button id="month7"  onClick="monthPlanSearch(7)"  <s:if test="now_month==7">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>7</button>
		<button id="month8"  onClick="monthPlanSearch(8)"  <s:if test="now_month==8">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>8</button>
		<button id="month9"  onClick="monthPlanSearch(9)"  <s:if test="now_month==9">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>9</button>
		<button id="month10" onClick="monthPlanSearch(10)" <s:if test="now_month==10">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>10</button>
		<button id="month11" onClick="monthPlanSearch(11)" <s:if test="now_month==11">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>11</button>
		<button id="month12" onClick="monthPlanSearch(12)" <s:if test="now_month==12">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>12</button>
		月工作计划
		</span>
	</div>
</div>
<div id="div_task_detail_center">
<table class="content_table" cellpadding="0" cellspacing="0" width="100%">
	<tr class="header">
		<th style="width:10%;background: none;padding-left:5px;" class="first_col" align="left">编号</th>
		<th style="width:10%;"align="left">类型</th>
		<th style="width:40%;"align="left">工作内容</th>
		<th style="width:10%;"align="left">目标时间</th>
		<th style="width:10%;"align="left">状态</th>
		<th style="width:10%;"align="left">更新时间</th>
		<th style="width:10%;"align="left">附件</th>
	</tr>
<s:iterator value="page.result">
  <tr class="mainTr" id="planWork_${planWork2Id}" onclick="planMonthClick('${planWork2Id}');">
   <td>${planMonth}月-${serialOrder}</td>
   <td>
	  <s:if test="planType==1">年计划</s:if>
		<s:if test="planType==2">月会工作</s:if>
		<s:if test="planType==3">项目管理</s:if>
		<s:if test="planType==4">指令单</s:if>
		<s:if test="planType==5">综合</s:if>
   </td>
   <td>${content}</td>
   <td><s:date name="targetDate" format="MM-dd"></s:date></td>
   <td id="statusCd_${planWork2Id}">
				<s:if test="statusCd==0"><img src="${ctx}/resources/images/common/status_unconfirm.gif" title="未确认"></s:if>
				<s:elseif test="statusCd==1"><img src="${ctx}/resources/images/common/status_confirm.gif" title="进行中"></s:elseif>
				<s:elseif test="statusCd==2"><img src="${ctx}/resources/images/common/status_prefinish.gif" title="预完成"></s:elseif>
				<s:elseif test="statusCd==3"><img src="${ctx}/resources/images/common/status_predel.gif" title="申请删除"></s:elseif>
				<s:elseif test="statusCd==4"><img src="${ctx}/resources/images/common/status_finish.gif" title="已完成"></s:elseif>
				<s:elseif test="statusCd==10"><img src="${ctx}/resources/images/common/status_completeDely.gif" title="完成但曾过期"></s:elseif>
				<s:elseif test="statusCd==5"><img src="${ctx}/resources/images/common/status_del.gif" title="已删除"></s:elseif>
				<s:elseif test="statusCd==6"><img src="${ctx}/resources/images/common/status_hidden.gif" title="已隐藏"></s:elseif>
				<s:elseif test="statusCd==7">非本月</s:elseif>
				<s:elseif test="statusCd==8"><img src="${ctx}/resources/images/common/status_suspend.gif" title="已过期"></s:elseif>
				<s:elseif test="statusCd==9">非考核性过期</s:elseif>
	</td>
   <td><s:date name="updatedDate" format="MM-dd"></s:date></td>
   <td>
     <span onclick="openAttachmentPlanWork2('附件管理','${planWork2Id==null?entityTmpId_New:planWork2Id}'); return false;" >
		<s:if test="attachFlg == 1"><img src="${ctx}/resources/images/common/atta_y.gif" /></s:if>
		<s:else><img src="${ctx}/resources/images/common/atta.gif" /></s:else>
	</span>
   </td>
  </tr>
  <tr id="newScheTemplate2" class="detailTr" style="display:none; height:60px;">
		    <td colspan="7">
				<div style="float: left;">
				<span style="padding-left: 10px;">留言：</span>
		        <table>
					<tr>
						<td align="left" style="padding-left: 14px; padding-bottom:4px; padding-top:4px;">
							<table>
								<tr>
									<td colspan="2" id="${planWork2Id}_messageDiv">
									   <div id="div_message_${planWork2Id}">
										<s:iterator value="planWork2Messages">
												<pre><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>(<s:date name="createdDate" format="MM-dd HH:mm"/>):<s:property value="content" escape="true"/></pre>
										</s:iterator>
										</div>
									</td>
								</tr>
								<tr><td colspan="2" style="height:4px;"></td></tr>
								<tr>
									<td width="20%" style="padding-bottom:8px;">
										<textarea id="${planWork2Id}_message" name="newMessage" rows="3" cols="36" style="height:53px;"></textarea>
									</td>
									<td width="80%" style="padding-bottom:8px;" align="left">
										<input type="button" style="height:53px;" class="button_green" onclick="saveMessage('${planWork2Id}','${isAttentioned}');" value="留言"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				</div>
					<div style="float:left;margin-left:10px; margin-top:50px;">
			<security:authorize ifAnyGranted="A_PLAN_WORK2_CENTER,A_PLAN_WORK2_VICE,A_PLAN_WORK2_ADMIN">
					<input class="save2Btn button_blue" type="button" style="margin-left:2px;" onclick="updateStatusCd('${planWork2Id}',2);" value="预完成"/>
			</security:authorize>
			 <security:authorize ifAnyGranted="A_PLAN_WORK2_VICE,A_PLAN_WORK2_ADMIN">
					<input class="save4Btn button_blue" type="button" style="margin-left:2px;" onclick="updateStatusCd('${planWork2Id}',4);" value="完成"/>
			</security:authorize>
			<security:authorize ifAnyGranted="A_PLAN_WORK2_CENTER,A_PLAN_WORK2_VICE,A_PLAN_WORK2_ADMIN">
					<input class="save3Btn button_red" type="button" style="margin-left:2px;" onclick="updateStatusCd('${planWork2Id}',3);" value="申请删除"/>
			</security:authorize>
		    <security:authorize ifAnyGranted="A_PLAN_WORK2_VICE,A_PLAN_WORK2_ADMIN">
					<input class="save5Btn button_red" type="button" style="margin-left:2px;" onclick="updateStatusCd('${planWork2Id}',5);" value="删除"/>
			</security:authorize>
				</div>
		    </td>
  </tr>
</s:iterator>
</table>
</div>
 </div>
 <div id="cost-plan-exec">
   <!-- %@include file="${ctx}/plan/plan-exec-plan!getCenterPlanRel.action"% -->
 </div>