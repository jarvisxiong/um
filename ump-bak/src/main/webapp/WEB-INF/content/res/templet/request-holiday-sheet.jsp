<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--请假单-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="80px"/>
			<col width="100px"/>
			<col width="100px"/>
			<col width="75px;"/>
			<col width="65px"/>
			<col width="65px"/>
			<col />
			<tr>
				<td class="td_title">中心/公司</td>
				<td colspan="6">
					<input validate="required" type="text" name="templateBean.center" value="${templateBean.center}" id="centerNameId" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden"  name="templateBean.centerCd" value="${templateBean.centerCd}" id="centerCdId"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">部门</td>
				<td colspan="6">
					<input class="inputBorder" validate="required" type="text" name="templateBean.dept" value="${templateBean.dept}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">姓名</td>
				<td colspan="3">
					<input id="slotUserName" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else>/>
					<input id="slotUserCd" type="hidden" name="templateBean.userCd" value="${templateBean.userCd}"  />
				</td>
				<td class="td_title">职位</td>
				<td colspan="2">
					<input class="inputBorder" validate="required" type="text" name="templateBean.position" value="${templateBean.position}" />
				</td>
				
			</tr>
			<tr>
				<td class="td_title">职级</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.positionLevel" value="${templateBean.positionLevel}"  />
				</td>
				<td class="td_title">入职日期</td>
				<td colspan="2">
					<input class="inputBorder Wdate" validate="required" type="text" name="templateBean.joinDate" value="${templateBean.joinDate}" onfocus="WdatePicker()"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">假期类别</td>
				<td style="text-align: center;">由</td>
				<td style="text-align: center;">至</td>
				<td style="text-align: center;">合计请假天数(天)</td>
				<td style="text-align: center;">法定假日(天)</td>
				<td style="text-align: center;">本休日(天)</td>
				<td style="text-align: center;">注</td>
			</tr>
			<tr>
				<td class="td_title">年假</td>
				<td class="td_title">
					<input class="Wdate inputBorder"  type="text" name="templateBean.beginDate1" value="${templateBean.beginDate1}"  onfocus="WdatePicker()" id="beginDate1" onchange="onchange_countDays();" />
				</td>
				<td class="td_title">
					<input class="Wdate inputBorder"  type="text" name="templateBean.endDate1" value="${templateBean.endDate1}" onfocus="WdatePicker()" id="endDate1" onchange="onchange_countDays();" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.totalDays1" value="${templateBean.totalDays1}" onblur="formatVal($(this));" id="totalDays1" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.offiHolidays1" value="${templateBean.offiHolidays1}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.holidays1" value="${templateBean.holidays1}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.remark1" value="${templateBean.remark1}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">婚假</td>
				<td class="td_title">
					<input class="Wdate inputBorder"  type="text" name="templateBean.beginDate2" value="${templateBean.beginDate2}" onfocus="WdatePicker()" id="beginDate2" onchange="onchange_countDays();" />
				</td>
				<td class="td_title">
					<input class="Wdate inputBorder"  type="text" name="templateBean.endDate2" value="${templateBean.endDate2}" onfocus="WdatePicker()" id="endDate2" onchange="onchange_countDays();" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.totalDays2" value="${templateBean.totalDays2}"  onblur="formatVal($(this));" id="totalDays2" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.offiHolidays2" value="${templateBean.offiHolidays2}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.holidays2" value="${templateBean.holidays2}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.remark2" value="${templateBean.remark2}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">产假</td>
				<td class="td_title">
					<input class="Wdate inputBorder"  type="text" name="templateBean.beginDate3" value="${templateBean.beginDate3}" onfocus="WdatePicker()" id="beginDate3" onchange="onchange_countDays();" />
				</td>
				<td class="td_title">
					<input class="Wdate inputBorder"  type="text" name="templateBean.endDate3" value="${templateBean.endDate3}" onfocus="WdatePicker()" id="endDate3" onchange="onchange_countDays();" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.totalDays3" value="${templateBean.totalDays3}" onblur="formatVal($(this));" id="totalDays3" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.offiHolidays3" value="${templateBean.offiHolidays3}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.holidays3" value="${templateBean.holidays3}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.remark3" value="${templateBean.remark3}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">丧假</td>
				<td class="td_title">
					<input class="Wdate inputBorder"  type="text" name="templateBean.beginDate4" value="${templateBean.beginDate4}" onfocus="WdatePicker()" id="beginDate4" onchange="onchange_countDays();" />
				</td>
				<td class="td_title">
					<input class="Wdate inputBorder"  type="text" name="templateBean.endDate4" value="${templateBean.endDate4}" onfocus="WdatePicker()" id="endDate4" onchange="onchange_countDays();" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.totalDays4" value="${templateBean.totalDays4}" onblur="formatVal($(this));" id="totalDays4" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.offiHolidays4" value="${templateBean.offiHolidays4}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.holidays4" value="${templateBean.holidays4}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.remark4" value="${templateBean.remark4}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">病假</td>
				<td class="td_title">
					<input class="Wdate inputBorder"  type="text" name="templateBean.beginDate5" value="${templateBean.beginDate5}" onfocus="WdatePicker()" id="beginDate5" onchange="onchange_countDays();" />
				</td>
				<td class="td_title">
					<input class="Wdate inputBorder"  type="text" name="templateBean.endDate5" value="${templateBean.endDate5}" onfocus="WdatePicker()" id="endDate5" onchange="onchange_countDays();" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.totalDays5" value="${templateBean.totalDays5}"  onblur="formatVal($(this));" id="totalDays5" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.offiHolidays5" value="${templateBean.offiHolidays5}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.holidays5" value="${templateBean.holidays5}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.remark5" value="${templateBean.remark5}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">事假</td>
				<td class="td_title">
					<input class="Wdate inputBorder"  type="text" name="templateBean.beginDate6" value="${templateBean.beginDate6}" onfocus="WdatePicker()" id="beginDate6" onchange="onchange_countDays();" />
				</td>
				<td class="td_title">
					<input class="Wdate inputBorder"  type="text" name="templateBean.endDate6" value="${templateBean.endDate6}" onfocus="WdatePicker()" id="endDate6" onchange="onchange_countDays();" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.totalDays6" value="${templateBean.totalDays6}"  onblur="formatVal($(this));" id="totalDays6" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.offiHolidays6" value="${templateBean.offiHolidays6}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.holidays6" value="${templateBean.holidays6}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.remark6" value="${templateBean.remark6}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">其他</td>
				<td class="td_title">
					<input class="Wdate inputBorder"  type="text" name="templateBean.beginDate7" value="${templateBean.beginDate7}" onfocus="WdatePicker()" id="beginDate7" onchange="onchange_countDays();" />
				</td>
				<td class="td_title">
					<input class="Wdate inputBorder"  type="text" name="templateBean.endDate7" value="${templateBean.endDate7}" onfocus="WdatePicker()" id="endDate7" onchange="onchange_countDays();" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.totalDays7" value="${templateBean.totalDays7}"  onblur="formatVal($(this));" id="totalDays7" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.offiHolidays7" value="${templateBean.offiHolidays7}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.holidays7" value="${templateBean.holidays7}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">
					<input class="inputBorder"  type="text" name="templateBean.remark7" value="${templateBean.remark7}" />
				</td>
			</tr>
			<tr>
				<td class="td_title" style="font-weight: bold;">
					请假总天数
				</td>
				<td colspan="2">
					<input class="inputBorder Wdate" style="width:40%" validate="required"  type="text" name="templateBean.endDate" value="${templateBean.endDate}"  onfocus="WdatePicker()"/>
					至
					<input class="inputBorder Wdate" style="width:40%" validate="required"  type="text" name="templateBean.beginDate" value="${templateBean.beginDate}"  onfocus="WdatePicker()"/>
				</td>
				<td class="td_title" colspan="2">
					<div style="float:left;font-weight: bold;">共</div>
					<div style="float:left;"><input class="inputBorder" validate="required" type="text" style="width:35px" name="templateBean.totalDays" value="${templateBean.totalDays}" onblur="formatVal($(this));"/></div>
					<div style="float:left;font-weight: bold;">天</div>
				</td>
				<td class="td_title" style="font-weight: bold;" colspan="2">
					<div style="float:left;">上班时间</div>
					<div style="float:left;">
					<input class="Wdate inputBorder" validate="required" type="text" name="templateBean.workDate" value="${templateBean.workDate}"  onfocus="WdatePicker()"/>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="7">请假期间联系地址/电话 </td>
			</tr>
			<tr>
				<td colspan="7" style="padding:5px 0;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.contactWay" value="${templateBean.contactWay}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">应得年假 </td>
				<td align="center">
					<input class="inputBorder" validate="required" type="text" name="templateBean.shouldHoliday" value="${templateBean.shouldHoliday}" onblur="formatVal($(this));"/>
				</td>	
				<td class="td_title">已休年假</td>
				<td align="center">
					<input class="inputBorder" validate="required" type="text" name="templateBean.pastHoliday" value="${templateBean.pastHoliday}" onblur="formatVal($(this));"/>
				</td>	
				<td class="td_title">剩余年假</td>
				<td colspan="2" align="center">
					<input class="inputBorder" validate="required" type="text" name="templateBean.leftHoliday" value="${templateBean.leftHoliday}" onblur="formatVal($(this));"/>
				</td>	
			</tr>
			<tr>
				<td class="td_title">其他说明 </td>
				<td colspan="6">
					<textarea class="inputBorder contentTextArea" name="templateBean.otherRemark">${templateBean.otherRemark}</textarea>
				</td>	
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>

<!-- 若为空,初始化当期中心 -->
<s:if test="resApproveInfoId==null">
	<script type="text/javascript">
		$("#centerNameId").val('<%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %>');
		$("#centerCdId").val('<%=SpringSecurityUtils.getCurrentCenterCd() %>');
	</script>
</s:if>
<script type="text/javascript">
	function countDays(startDateId, endDateId, daysId) {
		var fromDate = $("#"+startDateId).val();
		var toDate = $("#"+endDateId).val();
		if(isEmpty(fromDate) || isEmpty(toDate)) {
			return;
		}
		var fArray = fromDate.split("-");
		var tArray = toDate.split("-");
		var fDate = new Date(fArray[0],fArray[1]-1,fArray[2]);
		var tDate = new Date(tArray[0],tArray[1]-1,tArray[2]);
		if(tDate.getTime()<fDate.getTime()) {
			//alert("结束时间不能小于开始时间");
			$("#"+daysId).val("");
			return;
		}
		var day = Math.abs(tDate.getTime()-fDate.getTime())/1000/60/60/24+1;
		$("#"+daysId).val(day);
	}
	function onchange_countDays() {
		countDays("beginDate1","endDate1","totalDays1");
		countDays("beginDate2","endDate2","totalDays2");
		countDays("beginDate3","endDate3","totalDays3");
		countDays("beginDate4","endDate4","totalDays4");
		countDays("beginDate5","endDate5","totalDays5");
		countDays("beginDate6","endDate6","totalDays6");
		countDays("beginDate7","endDate7","totalDays7");
	}
</script>