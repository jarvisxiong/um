<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--请假单(新)-->
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.LoginUtil"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="120px"/>
			<col />
			<tr>
				<s:if test="resApproveInfoId==null">
					<c:set var="sendCenterName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </c:set>
					<c:set var="sendCenterCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></c:set>
					<c:set var="defaultOrgName"><%=SpringSecurityUtils.getCurrentDeptName() %></c:set>
					<c:set var="defaultPositionName"><%=SpringSecurityUtils.getRealPositonName() %></c:set>
					<c:set var="defaultUserName"><%=SpringSecurityUtils.getCurrentUserName() %></c:set>
					<c:set var="defaultUserCd"><%=SpringSecurityUtils.getCurrentUiid() %></c:set>
					<c:set var="defaultPositionLevel"><%=SpringSecurityUtils.getCurrentPermissionLevelName() %></c:set>
					<c:set var="defaultAttendWorkDate"><%=DateOperator.formatDate(SpringSecurityUtils.getAttendWorkDate()) %></c:set>
				</s:if>
				<s:else>
					<c:set var="sendCenterName">${templateBean.center}</c:set>
					<c:set var="sendCenterCd">${templateBean.centerCd}</c:set>
					<c:set var="defaultOrgName">${templateBean.dept}</c:set>
					<c:set var="defaultPositionName">${templateBean.position}</c:set>
					<c:set var="defaultUserName">${templateBean.userName}</c:set>
					<c:set var="defaultUserCd">${templateBean.userCd}</c:set>
					<c:set var="defaultPositionLevel">${templateBean.positionLevel}</c:set>
					<c:set var="defaultAttendWorkDate">${templateBean.joinDate}</c:set>
				</s:else>
				<td class="td_title">中心/公司</td>
				<td colspan="2">
					<input validate="required" type="text" name="templateBean.center" value="${sendCenterName}" id="centerNameId" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden"  name="templateBean.centerCd" value="${sendCenterCd}" id="centerCdId"/>
				</td>
				<td class="td_title">部门</td>
				<td colspan="2">
					<input class="inputBorder" validate="required" type="text" name="templateBean.dept" value="${defaultOrgName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">姓名</td>
				<td colspan="2">
					<input id="slotUserName" validate="required" type="text" name="templateBean.userName" value="${defaultUserName}" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else>/>
					<input id="slotUserCd" type="hidden" name="templateBean.userCd" value="${defaultUserCd}"  />
				</td>
				<td class="td_title">职位</td>
				<td colspan="2">
					<input class="inputBorder" validate="required" type="text" name="templateBean.position" value="${defaultPositionName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">职级</td>
				<td colspan="2">
					<input class="inputBorder" validate="required" type="text" name="templateBean.positionLevel" value="${defaultPositionLevel}"  />
				</td>
				<td class="td_title">入职日期</td>
				<td colspan="2">
					<input class="inputBorder Wdate" validate="required" type="text" name="templateBean.joinDate" value="${defaultAttendWorkDate}" onfocus="WdatePicker()"/>
				</td>
			</tr>
			<tr>
				<td class="td_title" style="font-weight: bold;">
					上班时间
				</td>
				<td class="td_title" colspan="5" style="text-align: left;">
					<input class="Wdate inputBorder" style="width: 150px;" validate="required" type="text" name="templateBean.workDate" value="${templateBean.workDate}" onfocus="WdatePicker()"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="6" style="padding-right: 0px; padding-left: 0px; ">
					<table id="tbConItem" border="1px solid #8c8f94;" cellpadding="0" cellspacing="0" frame="void" style="width: 100%; border-collapse:collapse;" >
						<col width="80"/>
						<col width="40"/>
						<col width="110"/>
						<col width="110"/>
						<col/>
						<col/>
						<col/>
						<col width="40"/>
						<tr class="thConItem" align="center">
							<th >假期类别</th>
							<th >单位</th>
							<th >由</th>
							<th >至</th>
							<th >合计请假时间</th>
							<th >法定假日</th>
							<th >本休日</th>
							<th >操作</th>
						</tr>
						<s:if test="statusCd==0 || statusCd==3">
						<tr style="display:none;" id="holidayType0_show_tr" >
							<td id="holidayType0_show_td" colspan="8">&nbsp;</td>
						</tr>
						<tr id="trConItem" style="display: none;margin-bottom:5px;">
							<td>
							<s:select cssClass="inputBorder" validate="required" list="@com.powerlong.org.pd.util.DictMapUtil@getMapHolidayType()" listKey="key" listValue="value" name="templateBean.otherProperties[0].holidayType" id="holidayType0" onchange="selectHolidayType(this);"></s:select>
							</td>
							<td>
							<input class="inputBorder" validate="required" type="text" id="unitName0" readonly="readonly">
							<input class="inputBorder" type="hidden" name="templateBean.otherProperties[0].unit" id="unit0" />
							</td>
							<td><input validate="required"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH'})" class="Wdate inputBorder" type="text" name="templateBean.otherProperties[0].beginDate" id="beginDate0" onchange="countTotalDays(this);" /></td>
							<td><input validate="required"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH'})" class="Wdate inputBorder" type="text" name="templateBean.otherProperties[0].endDate" id="endDate0" onchange="countTotalDays(this);" /></td>
							<td><input class="inputBorder" validate="required" type="text" name="templateBean.otherProperties[0].totalDays" id="totalDays0" onblur="formatVal($(this));"/></td>
							<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[0].offiHolidays" onblur="formatNum($(this));"/></td>
							<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[0].holidays" onblur="formatNum($(this));"/></td>
							<td width="15px" align="center"><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a></td>
						</tr>
						</s:if>
						<s:iterator value="templateBean.otherProperties" var="item" status="s">
						<tr style="margin-bottom:5px;">
							<td>
							<s:if test="#isMy!=1">
								<input class="inputBorder" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].holidayType" value="<%=CodeNameUtil.getDictNameByCd(DictContants.RES_HOLIDAY_TYPE,JspUtil.findString("#item.holidayType")) %>" id="holidayType<s:property value="#s.index" />" />
							</s:if>
							<s:else>
								<s:select cssClass="inputBorder" validate="required" list="@com.powerlong.org.pd.util.DictMapUtil@getMapHolidayType()" listKey="key" listValue="value" name="templateBean.otherProperties[%{#s.index}].holidayType" id="holidayType%{#s.index}" onchange="selectHolidayType(this);"></s:select>
							</s:else>
							</td>
							<td>
							<input class="inputBorder" validate="required" type="text" id="unitName<s:property value="#s.index" />" readonly="readonly" value="<%=CodeNameUtil.getDictNameByCd(DictContants.RES_HOLIDAY_UNIT,JspUtil.findString("#item.unit")) %>">
							<input class="inputBorder" type="hidden" id="unit<s:property value="#s.index" />" name="templateBean.otherProperties[<s:property value="#s.index" />].unit" value="${item.unit}" />
							</td>
							<td><input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH'})" class="Wdate inputBorder" validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].beginDate" id="beginDate<s:property value="#s.index" />" value="${item.beginDate}" onchange="countTotalDays(this);" /></td>
							<td><input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH'})" class="Wdate inputBorder" validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].endDate" id="endDate<s:property value="#s.index" />" value="${item.endDate}" onchange="countTotalDays(this);" /></td>
							<td><input class="inputBorder" validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].totalDays" id="totalDays<s:property value="#s.index" />" value="${item.totalDays}" onblur="formatVal($(this));"/></td>
							<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].offiHolidays" value="${item.offiHolidays}" onblur="formatNum($(this));"/></td>
							<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].holidays" value="${item.holidays}" onblur="formatNum($(this));"/></td>
							<td width="15px" align="center">
							<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
							<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
							</s:if>
							</td>
						</tr>
						</s:iterator>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="6" align="center">
				<s:if test="statusCd==0 || statusCd==3">
				<input type="button"  class="btn_blue_55_20" style="border:none;"  value="增加假期" onclick="addItem();" />
				</s:if>
				</td>
			</tr>
			<tr>
				<td>请假期间联系地址/电话 </td>
				<td colspan="5">
					<input class="inputBorder" validate="required" type="text" name="templateBean.contactWay" value="${templateBean.contactWay}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">请假原因</td>
				<td colspan="5">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.holidayReason">${templateBean.holidayReason}</textarea>
				</td>	
			</tr>
			<tr>
				<td class="td_title">其他说明 </td>
				<td colspan="5">
					<textarea class="inputBorder contentTextArea" name="templateBean.otherRemark">${templateBean.otherRemark}</textarea>
				</td>	
			</tr>
		</table>

<script type="text/javascript">
	var trApprover=$("#trConItem").clone();
	//新增一个div层用来显示一些提示
	var trAlert = $("#holidayType0_show_tr").clone();

	$("#holidayType0_show_tr").remove();
	$("#trConItem").remove();
	var cloneCount = 0;
	var index=${conItemCount};
	
	function addItem(){
		var trAlert_new = trAlert.clone();
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();
		$(trApprover_new).find(".inputBorder").each(function(i){
			this.name=this.name.replace('0',index);
			this.id=this.id.replace('0',index);
		});

		
		$(trAlert_new).each(function(i){
			this.id=this.id.replace('0',index);
		}).find(":td").each(function(i){
			this.id=this.id.replace('0',index);
		});
		$("#tbConItem").append(trAlert_new);
		
		//$(trApprover_new).find("#cloneUserCds").attr("id",cloneCount+"cloneUserCds");
		$("#tbConItem").append(trApprover_new);
		$("#holidayType"+index).val("");

		
		index++;
	}
	function delItem(dom){
		if($("#tbConItem tr").size() > 2) {
			$(dom).parent().parent().remove();

			//alert($(dom).parent().parent().find(".inputBorder:first").attr("id"));
			var t = $(dom).parent().parent().find(".inputBorder:first").attr("id");
			$("#"+t+"_show_tr").remove();
		} else {
			alert("至少要有一条记录");
		}
	}
</script>
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>
<script type="text/javascript">
	function selectHolidayType(ele) {
		var $obj =  $(ele);
		var showValue = "";
			switch(ele.value){
			//空值
			case "":
				showValue = "";
				break;
			//年假
			case "1":
				showValue = "连续工龄10年以上员工第一次请年假，请上传“工龄证明”，如社保证明、劳动手册等";
				break;
			//婚假
			case "2":
				showValue = "请上传“结婚证明”";
				break;
			//产假
			case "3":
				showValue = "请上传由计生部门出具的相关证明，如准生证，再生育子女告知书";
				break;
			//丧假
			case "4":
				showValue = "请上传相关证明，具体可与人事部门沟通";
				break;
			//事假
			case "6":
				showValue = "";
				break;
			//其它
			case "7":
				showValue = "晚育护理假，请上传由计生部门出具的相关证明，如准生证，再生育子女告知书等及子女的“出生医学证明”";
				break;
			//全薪病假
			case "8":
				showValue = "请上传病历卡和区县区以上医院开具的“建议休息证明”";
				break;
			//超额病假
			case "9":
				showValue = "请上传病历卡和区县区以上医院开具的“建议休息证明”";
				break;
			//产检
			case "10":
				showValue = "产检请上传区县级以上医院开具的“建议休息证明”和病历卡哺乳假请上传子女的“出生医学证明”";
				break;
			default:
				showValue = "";
				
			}
			if(showValue != ""){
				$("#"+(ele.id)+"_show_td").html(showValue).css("color","red");
				$("#"+(ele.id)+"_show_td").parent().show();
			}else{
				$("#"+(ele.id)+"_show_td").html("");
				$("#"+(ele.id)+"_show_td").parent().hide();
			}
			
		//$obj.parent().children("div:first").show().html("请上传附件").css("color","red");
		//$("#holidayType0_show").show();
		var unitId=ele.id.replace('holidayType','unit');
		var unitNameId=ele.id.replace('holidayType','unitName');
		var beginDateId=ele.id.replace('holidayType','beginDate');
		var endDateId=ele.id.replace('holidayType','endDate');
		var totalDaysId=ele.id.replace('holidayType','totalDays');
		
		if(!ele.value) {
			//$("#"+beginDateId).val("");
			//$("#"+endDateId).val("");
			//$("#"+totalDaysId).val("");
			//$("#"+beginDateId).attr("disabled", "disabled");
			//$("#"+endDateId).attr("disabled", "disabled");
		} else {
			//$("#"+beginDateId).removeAttr("disabled");
			//$("#"+endDateId).removeAttr("disabled");
			
			
			
	    	
			if(ele.value==6 || ele.value==8 || ele.value==9 || ele.value==10) {
				alert(showValue+"\n当前计算单位为：小时");
				var unitName = '<%=CodeNameUtil.getDictNameByCd(DictContants.RES_HOLIDAY_UNIT,JspUtil.findString("1")) %>';
				$("#"+unitId).val("1");
				$("#"+unitNameId).val(unitName);
				//$("#"+beginDateId).val("");
				//$("#"+endDateId).val("");
				$("#"+totalDaysId).val("");
				
				//var fmtH = function(){
				//	WdatePicker({dateFmt:'yyyy-MM-dd HH'});
				//};
				//$("#"+beginDateId).unbind("click");
				//$("#"+endDateId).unbind("click");
				//$("#"+beginDateId).bind("click",fmtH);
				//$("#"+endDateId).bind("click",fmtH);
				//$("#"+beginDateId).unbind("focus");
				//$("#"+endDateId).unbind("focus");
				//$("#"+beginDateId).bind("focus",fmtH);
				//$("#"+endDateId).bind("focus",fmtH);
			} else {
				
				
				alert(showValue+"\n当前计算单位为：天");
				$("#"+unitId).val("0");
				var unitName = '<%=CodeNameUtil.getDictNameByCd(DictContants.RES_HOLIDAY_UNIT,JspUtil.findString("0")) %>';
				$("#"+unitNameId).val(unitName);
				//$("#"+beginDateId).val("");
				//$("#"+endDateId).val("");
				
				//var fmtD = function(){
				//	WdatePicker();
				//};
				//$("#"+beginDateId).unbind("click");
				//$("#"+endDateId).unbind("click");
				//$("#"+beginDateId).bind("click",fmtD);
				//$("#"+endDateId).bind("click",fmtD);
				//$("#"+beginDateId).unbind("focus");
				//$("#"+endDateId).unbind("focus");
				//$("#"+beginDateId).bind("focus",fmtD);
				//$("#"+endDateId).bind("focus",fmtD);
			}
		}
		
	}
	
	function countTotalDays(ele) {
		var index = ele.id.charAt(ele.id.length-1);
		var holidayTypeVal = $("#holidayType"+index).val();
		if(holidayTypeVal==6 || holidayTypeVal==8 || holidayTypeVal==9 || holidayTypeVal==10) {
			//countHours("beginDate"+index,"endDate"+index,"totalDays"+index);
		} else {
			countDays("beginDate"+index,"endDate"+index,"totalDays"+index);
		}
		
	}

	function countDays(startDateId, endDateId, daysId) {
		var fromDate = $("#"+startDateId).val();
		var toDate = $("#"+endDateId).val();
		if(isEmpty(fromDate) || isEmpty(toDate)) {
			return;
		}
		var fArray = fromDate.split(" ")[0].split("-");
		var tArray = toDate.split(" ")[0].split("-");
		var fDate = new Date(fArray[0],fArray[1]-1,fArray[2]);
		var tDate = new Date(tArray[0],tArray[1]-1,tArray[2]);
		if(tDate.getTime()<fDate.getTime()) {
			return;
		}
		var day = Math.abs(tDate.getTime()-fDate.getTime())/1000/60/60/24+1;
		$("#"+daysId).val(day);
	}
	
	function countHours(startDateId, endDateId, daysId) {
		var fromDate = $("#"+startDateId).val();
		var toDate = $("#"+endDateId).val();
		if(isEmpty(fromDate) || isEmpty(toDate)) {
			return;
		}
		var fHour = fromDate.split(" ");
		var tHour = toDate.split(" ");
		var fArray = fHour[0].split("-");
		var tArray = tHour[0].split("-");
		var fDate = new Date(fArray[0],fArray[1]-1,fArray[2]);
		var tDate = new Date(tArray[0],tArray[1]-1,tArray[2]);
		if(tDate.getTime()<fDate.getTime()) {
			return;
		}
		var hour = Math.abs(tDate.getTime()-fDate.getTime())/1000/60/60/24;
		hour = hour*8+(tHour[1]-fHour[1]);
		$("#"+daysId).val(hour);
	}
</script>
