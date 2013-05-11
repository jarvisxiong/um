<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 出差申请计划单 --%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
	<%@ include file="template-var.jsp"%>
	<table class="mainTable" id="tableTripPlan">
		<col width="100px"/>
		<col/>
		<col width="80px"/>
		<col/>
		<col width="100px"/>
		<col/>
		<tr>
			<td class="td_title">中心/公司</td>
			<td colspan="3">
				<s:if test="resApproveInfoId==null">
				<c:set var="sendCenterName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </c:set>
				<c:set var="sendCenterCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></c:set>
				</s:if>
				<s:else>
				<c:set var="sendCenterName">${templateBean.centerOrgName}</c:set>
				<c:set var="sendCenterCd">${templateBean.centerOrgCd}</c:set>
				</s:else>
				<input validate="required" type="text" name="templateBean.centerOrgName" value="${sendCenterName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
				<input type="hidden" id="centerCd" name="templateBean.centerOrgCd" value="${sendCenterCd}"  />
			</td>
			<td class="td_title">职位</td>
			<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.position" value="${templateBean.position}"  />
			</td>
		</tr>
		<tr>
			<td class="td_title">申请人</td>
			<td>
				<input id="applyUserName" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else> />
				<input id="applyUserCd" type="hidden" name="templateBean.userCd" value="${templateBean.userCd}"  />
			</td>
			<td class="td_title">部门</td>
			<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.dept" value="${templateBean.dept}"  />
			</td>
			<td class="td_title">出差地点</td>
			<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.tripPlace" value="${templateBean.tripPlace}"  />
			</td>
		</tr>
		<tr>
		<td class="td_title">开始日期</td>
			<td>
				<input class="Wdate inputBorder" validate="required" type="text" name="templateBean.tripTimeBegin" value="${templateBean.tripTimeBegin}" <s:if test="statusCd==0 || statusCd==3">onclick="WdatePicker();" onblur="calcDate(this);"</s:if> calcType="startDate"/>
			</td>
			<td class="td_title">结束日期</td>
			<td>
				<input class="Wdate inputBorder" validate="required" type="text" name="templateBean.tripaTimeEnd" value="${templateBean.tripaTimeEnd}" <s:if test="statusCd==0 || statusCd==3">onclick="WdatePicker();" onblur="calcDate(this);"</s:if> calcType="endDate"/>
			</td>
			<td class="td_title">合计出差时间</td>
			<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.tripDay" value="${templateBean.tripDay}" readonly="readonly" />
			</td>
		</tr>
		<tr>
			<td class="td_title">随行人员</td>
			<td colspan="3">
				<input readonly="readonly"  type="text" name="templateBean.entourageUserName" value="${templateBean.entourageUserName}" id="entourageUserName" <s:if test="#isMy==1"> class="inputBorderNoTip mutiSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else> />
				<input id="entourageUserCd" type="hidden" name="templateBean.entourageUserCd" value="${templateBean.entourageUserCd}"  />
			</td>
			<td class="td_title">交通方式</td>
			<td>
			<s:if test="#isMy!=1">
				<input class="inputBorderNoTip" id="templateBean_travelWay" type="text" value="<%=CodeNameUtil.getDictNameByCd(DictContants.JBPM_TRAVEL_WAY,JspUtil.findString("templateBean.travelWay")) %>"  />
			</s:if>
			<s:else>
				<s:select cssClass="inputBorder" validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapTravelWay()" listKey="key" listValue="value" name="templateBean.travelWay" ></s:select>
			</s:else>
			</td>
		</tr>
		<tr>
			<td class="td_title">出差事由</td>
			<td colspan="5">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.tripReason">${templateBean.tripReason}</textarea>
			</td>
		</tr>
		<tr>
			<td class="td_title">预计差旅借款</td>
			<td colspan="5" style="text-align:left;">
				<input class="inputBorder" validate="required" type="text" style="width:100px" name="templateBean.lendMoneyAmt" value="${templateBean.lendMoneyAmt}"  onblur="formatVal($(this));" />元
			</td>
		</tr>
		<tr>
			<td class="td_title">行程计划安排</td>
			<td colspan="5">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.tripDesc">${templateBean.tripDesc}</textarea>
			</td>
		</tr>
		<tr id="trDayPlan">
			<td class="td_title">日程计划安排</td>
			<td colspan="5">
				<table style="margin:2px 0px 2px 0px;width:100%;">
					<col width="50"/>
					<col width="50"/>
					<col/>
					<col width="50"/>
					<col/>
					<tbody>
						<tr>
							<td align="center">第<label id="dayIndex">1</label>天</td>
							<td align="center">上午</td>
							<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.tripDayPlan[0].forenoonPlan" />
							</td>
							<td align="center">下午</td>
							<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.tripDayPlan[0].afternoonPlan" />
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<s:set var="dayPlanCount"><s:property value="templateBean.tripDayPlan.size()"/></s:set>
		<s:if test="templateBean.tripDayPlan.size()>0">
		<tr id="trDayPlan">
			<td class="td_title">日程计划安排</td>
			<td colspan="5">
				<table style="margin:2px 0px 2px 0px;width:100%;">
					<col width="50"/>
					<col width="50"/>
					<col/>
					<col width="50"/>
					<col/>
					<tbody>
						<s:iterator value="templateBean.tripDayPlan" var="dayPlan" status="st">
						<tr>
							<td align="center">第<label id="dayIndex">${st.index+1}</label>天</td>
							<td align="center">上午</td>
							<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.tripDayPlan[<s:property value="#s.index" />].forenoonPlan" value="${dayPlan.forenoonPlan}"  />
							</td>
							<td align="center">下午</td>
							<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.tripDayPlan[<s:property value="#s.index" />].afternoonPlan" value="${dayPlan.afternoonPlan}"  />
							</td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
			</td>
		</tr>
		</s:if>
	</table>
	
	<%--机票订购申请单 --%>
	<div style="display:none" id="spanConItem">
	<table class="mainTable" id="tbConItem" style="margin-top: 5px;" width="100%">
				<col width="60">
				<col width="90">
				<col>
				<col width="90">
				<col width="90">
				<col width="40">
				<tr>
					<td align="center">出行人员</td>
					<td align="center">起迄地</td>
					<td align="center">身份证号</td>
					<td align="center">建议航班时间</td>
					<td align="center">费用承担部门</td>
					<td align="center">操作</td>
				</tr>
				<s:if test="statusCd==0 || statusCd==3">
				<tr id="trConItem" style="display: none;margin-bottom:5px;">
					<td class="tdGroupNodes">
						<input validate="required" class="inputBorder" type="text" name="templateBean.airTicketsBookingSheet[0].userName"/>
					</td>
					<td class="tdGroupNodes">
						<input validate="required" class="inputBorder" type="text" name="templateBean.airTicketsBookingSheet[0].beginEndPlace"/>
					</td>
					<td class="tdGroupNodes">
						<input validate="required" class="inputBorder" type="text" name="templateBean.airTicketsBookingSheet[0].idCardNo"/>
					</td>
					<td>
						<input class="inputBorder" type="text"  name="templateBean.airTicketsBookingSheet[0].tripDate"/>
					</td>
					<td class="tdGroupNodes">
						<input validate="required" class="inputBorder" type="text" name="templateBean.airTicketsBookingSheet[0].costDept"/>
					</td>
					<td><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/></a></td>
				</tr>
				</s:if>
				<s:set var="conItemCount"><s:property value="templateBean.airTicketsBookingSheet.size()"/></s:set>
				<s:iterator value="templateBean.airTicketsBookingSheet" var="item" status="s">
					<tr style="margin-bottom:5px;">
						<td class="tdGroupNodes">
						<input validate="required" class="inputBorder" type="text" name="templateBean.airTicketsBookingSheet[<s:property value="#s.index" />].userName" value="${item.userName}"/>
					</td>
					<td class="tdGroupNodes">
						<input validate="required" class="inputBorder" type="text" name="templateBean.airTicketsBookingSheet[<s:property value="#s.index" />].beginEndPlace" value="${item.beginEndPlace}"/>
					</td>
					<td class="tdGroupNodes">
						<input validate="required" class="inputBorder" type="text" name="templateBean.airTicketsBookingSheet[<s:property value="#s.index" />].idCardNo" value="${item.idCardNo}"/>
					</td>
					<td>
						<input class="inputBorder Wdate" type="text"  name="templateBean.airTicketsBookingSheet[<s:property value="#s.index" />].tripDate" onfocus="WdatePicker()" value="${item.tripDate}"/>
					</td>
					<td class="tdGroupNodes">
						<input validate="required" class="inputBorder" type="text" name="templateBean.airTicketsBookingSheet[<s:property value="#s.index" />].costDept" value="${item.costDept}"/>
					</td>
					<td>
						<s:if test="#isMy==1">
						<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/></a>
						</s:if>
					</td>
					</tr>
				</s:iterator>
	</table>
	
	<s:if test="statusCd==0 || statusCd==3">
			<input type="button"  class="btn_blue_75_55" value="增加条款" onclick="addItem();" />
	</s:if>
	</div>
	
<s:if test="#isMy!=1">
	<script type="text/javascript">
		addRequireCss(false);
	</script>
</s:if>
<s:else>
	<script type="text/javascript">
		addRequireCss(true);
	</script>
</s:else>
				
<script type="text/javascript">
	var trApprover=$("#trConItem").clone();
	$("#trConItem").remove();
	var cloneCount = 0;
	var index=0;
	
	try{
		index = Number("${conItemCount}");
	}catch(e){}
	initItem();
	function addItem(){
		
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();
		$(trApprover_new).find(".inputBorder").each(function(i){
			this.name=this.name.replace('0',index);
		});
		//$(trApprover_new).find("#cloneUserCds").attr("id",cloneCount+"cloneUserCds");
		$("#tbConItem").append(trApprover_new);
		index++;
	}
	
	function delItem(dom){
		$(dom).parent().parent().remove();
	}

	//初始化交通方式
	function initItem(){
		if(($('#templateBean_travelWay').val()==0  && $('#templateBean_travelWay').val() != '') || $('#templateBean_travelWay').val() == '飞机' ){
			$('#spanConItem').show();
			$('#spanConItem').find(':input').attr("validate",'required');
		}
		else{
			$('#spanConItem').hide();
			$('#spanConItem').find(':input').attr("validate",'');
		}
	}


	$('#templateBean_travelWay').click(function(){
		if($(this).val() == 0 && $(this).val() != ''){
			$('#spanConItem').show();
			//如果选择是飞机则将验证加上
			$('#spanConItem').find(':input').attr("validate",'required');
		}
		else{
			//如果选择不是飞机则将验证去掉
			$('#spanConItem').find(':input').attr("validate",'');
			$('#spanConItem').hide();
			}
	});
	
	/*** 添加日程计划安排 ***/
	
	function showDayPlan(obj) {
		if($(obj).val()) {
			dayIndex = parseInt($(obj).val());
		}
		addDayPlanItem();
	}
	
	var trDayPlan = $("#trDayPlan").clone();
	$("#trDayPlan").remove();
	
	var dayIndex = 0;
	// 添加日行程计划
	function addDayPlanItem() {
		// 先清除表格
		$("#trDayPlan").remove();
		var tr_item = $(trDayPlan).clone();
		var subTable = $(tr_item).children().eq(1).children().clone();
		var tr_subItem = $(subTable).find("tbody");
		for(var i=0; i<dayIndex; i++) {
			var tr_new = $(tr_subItem).clone();
			$(tr_subItem).remove();
			$(tr_new).find("label[id='dayIndex']").html(i+1);
			$.each($(tr_new).find("input[type='text']"), function() {
				$(this).attr("name", $(this).attr("name").replace("0", i));
			});
			$(subTable).append(tr_new);
		}
		if(dayIndex && dayIndex > 0) {
			$(tr_item).children().eq(1).children().replaceWith(subTable);
			$("#tableTripPlan").append(tr_item);
		}
	}
	
	// 计算出差时间
	function calcDate(obj) {
		var startDate; // 开始日期
		var endDate; // 结束日期
		var tripDay; // 合计出差时间
		if($(obj).attr("calcType") == "startDate") { // 点击开始日期
			startDate = $(obj);
			endDate = $(obj).parent().parent().next().next().find("input[type='text']");
			tripDay = $(endDate).parent().parent().next().next().find("input[type='text']");
			// 设置结束日期最小值
			if($(startDate).val()) {
				$(endDate).unbind("focus").bind("focus", function() {
					WdatePicker({minDate : $(startDate).val()});
				});
			}
		} else if($(obj).attr("calcType") == "endDate") { // 点击结束日期
			startDate = $(obj).parent().parent().prev().prev().find("input[type='text']");
			endDate = $(obj);
			tripDay = $(obj).parent().parent().next().next().find("input[type='text']");
			// 设置开始日期日期最大值
			if($(endDate).val()) {
				$(startDate).unbind("focus").bind("focus", function() {
					WdatePicker({maxDate: $(endDate).val()});
				});
			}
		}
		var day = calcDatePass($(startDate).val(), $(endDate).val());
		if(day) {
			$(tripDay).val(day);
			// 显示日程计划安排
			showDayPlan(tripDay);
		}
	}
	
	/***
	 * 计算日期差
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 */
	function calcDatePass(startDate, endDate) {
		var day = "";
		if(startDate && endDate) {
			var msec = new Date(endDate.split("-")[0], endDate.split("-")[1], endDate.split("-")[2]).getTime() - new Date(startDate.split("-")[0], startDate.split("-")[1], startDate.split("-")[2]).getTime();
			if(msec == 0) {
				day = "1";
			} else {
				day = (msec / 1000 / 60 / 60 / 24); // 经过天数
				day = parseInt(day) + 1;
			}
		}
		return day;
	}
</script>
<!-- 默认1条申请记录 -->
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
	$(function() {
		addDayPlanItem();
	});
</script>
</s:if>
