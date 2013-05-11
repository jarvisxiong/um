<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%><html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/global.jsp" %>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>会议室预定</title>
		<link href="${ctx}/resources/css/common/common.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/desk/desk-oa.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	</head>
	
	<body>
		<div class="title_bar">
			<div style="float:left; margin-left:8px; font-size:18px; font-weight:bolder;">会议室预定</div>
		</div>
		<div style="background-color: #5a5a5a;height:1px;margin-bottom:8px;"></div>
		<div>
			<fieldset style="margin:0 5px;padding: 5px;">
			<div style="float:left;padding-left: 10px;width:490px;border-right: 1px solid #BFC4C8;">
				<form action="${ctx}/oa/oa-meeting-room-res!save.action" method="post" id="roomForm">
					<s:hidden name="status" value="0"/>
					<s:hidden name="addrType" id="addrType"></s:hidden>
				<div style="font-weight:bold;margin-bottom: 10px;"><span style="margin-right:5px;">预定会议室</span><img src="${ctx}/images/meetingRoom/pic_down_blue.gif"/></div>
				<table width="100%" cellpadding="0" cellspacing="0" class="editTable">
					<s:if test="addrType == 'GB'">
					<tr>
						<td width="80px">会议室<font color="red">*</font></td>
						<td>
							<div>
								<span style="font-weight: bolder;">15F:</span>
								<input type="checkbox" name="roomIds" id="roomIds-1" value="1"/>
								<label class="checkboxLabel" for="roomIds-1">会议室1</label>
								<input type="checkbox" name="roomIds" id="roomIds-2" value="2"/>
								<label class="checkboxLabel" for="roomIds-2">洽谈室1</label>
								<input type="checkbox" name="roomIds" id="roomIds-3" value="3"/>
								<label class="checkboxLabel" for="roomIds-3">洽谈室2</label>
								<input type="checkbox" name="roomIds" id="roomIds-4" value="4"/>
								<label class="checkboxLabel" for="roomIds-4">洽谈室3</label>
							</div>
							<div>
								<span style="font-weight: bolder;">12F:</span>
								<input type="checkbox" name="roomIds" id="roomIds-5" value="5"/>
								<label class="checkboxLabel" for="roomIds-5">会议室2</label>
								<input type="checkbox" name="roomIds" id="roomIds-6" value="6"/>
								<label class="checkboxLabel" for="roomIds-6">会议室3</label>
								<input type="checkbox" name="roomIds" id="roomIds-7" value="7"/>
								<label class="checkboxLabel" for="roomIds-7">洽谈室4</label>
								<input type="checkbox" name="roomIds" id="roomIds-8" value="8"/>
								<label class="checkboxLabel" for="roomIds-8">洽谈室5</label>
							</div>
							<div>
								<span style="font-weight: bolder;">&nbsp;6F:</span>
								<input type="checkbox" name="roomIds" id="roomIds-9" value="9"/>
								<label class="checkboxLabel" for="roomIds-9">会议室4</label>
							</div>
						</td>
					</tr>
					</s:if>
					<s:if test="addrType == 'SHC'">
					<tr>
						<td width="80px">上海城会议室<font color="red">*</font></td>
						<td>
							<div>
								<span style="font-weight: bolder;">会议室</span>
								<input type="checkbox" name="roomIds" id="roomIds-S1" value="S1"/>
								<label class="checkboxLabel" for="roomIds-S1">会议室1</label>
								<input type="checkbox" name="roomIds" id="roomIds-S2" value="S2"/>
								<label class="checkboxLabel" for="roomIds-S2">会议室2</label>
							</div>
							<div>
								<span style="font-weight: bolder;">洽谈室</span>
								<input type="checkbox" name="roomIds" id="roomIds-S3" value="S3"/>
								<label class="checkboxLabel" for="roomIds-S3">洽谈室1</label>
								<input type="checkbox" name="roomIds" id="roomIds-S4" value="S4"/>
								<label class="checkboxLabel" for="roomIds-S4">洽谈室2</label>
								<input type="checkbox" name="roomIds" id="roomIds-S5" value="S5"/>
								<label class="checkboxLabel" for="roomIds-S5">洽谈室3</label>
							</div>
						</td>
					</tr>
					</s:if>
					<tr>
						<td width="80px">会议主题<font color="red">*</font></td>
						<td><s:textfield name="subject" id="subject" cssStyle="width:350px"/></td>
					</tr>
					<tr>
						<td>会议日期 <font color="red">*</font></td>
						<td >
							<s:textfield cssStyle="color:red" cssClass="date" id="meetingDateId" name="currDay" onfocus="WdatePicker({minDate:'%y-%M-%d'})"/>
							<input type="text"  value="<s:date name="beginTime" format='HH:mm' />" name="meetingBegin" id="meetingDateBeginId" class="time" style="width:80px;color:red;" onfocus="WdatePicker({dateFmt:'HH:mm'})"/>
							到
							<input type="text" value="<s:date name="endTime" format='HH:mm' />" name="meetingEnd" id="meetingDateEndId" class="time" style="width:80px;color:red;" onfocus="WdatePicker({dateFmt:'HH:mm'})"/></td>
					</tr>
					<tr>
						<td>主持人 <font color="red">*</font></td>
						<td>
							<input type="text" name="compereName" value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("compere")) %>" onkeyup="showSearchUser(this)"/>
							<s:hidden name="compere" id="compere" />
						</td>
					</tr>
					<tr>
						<td>召集人 <font color="red">*</font></td>
						<td>
							<input type="text" name="applicantName" value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("applicant")) %>" onkeyup="showSearchUser(this)"/>
							<s:hidden name="applicant" id="applicant" />
						</td>
					</tr>
					<tr>
						<td>记录人<font color="red">*</font></td>
						<td>
							<input type="text" name="recorderName" value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("recorder")) %>" onkeyup="showSearchUser(this)"/>
							<s:hidden name="recorder" id="recorder"/>
						</td>
					</tr>
					<tr>
						<td>参会人员<font color="red">*</font></td>
						<td>
							<textarea id="otherUserNames" name="participatorNames" style="width:350px;" rows="2" readonly="readonly"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("participators"),";") %></textarea>
							<s:hidden id="otherUserCds" name="participators"></s:hidden>
							<span onclick="$('#otherUserNames').trigger('click')" style="font-weight: bolder;cursor: pointer;text-decoration: underline">选择</span>
						</td>
					</tr>
					<tr>
						<td>参会人数<font color="red">*</font></td>
						<td>约&nbsp;&nbsp;<s:textfield name="partNum"  cssStyle="width:30px" id="otherNum" />&nbsp;&nbsp;人</td>
					</tr>
					<tr>
						<td>涉及部门</td>
						<td><s:textfield name="relatedDept" cssStyle="width:350px"/></td>
					</tr>
					<tr>
						<td>隐藏主题</td>
						<td>
							<input type="checkbox" name="encryptFlg" value="1" />
							<span class="red">注:隐藏主题后,只有参会人员才能看到主题</span>
							<!--<s:radio list="#{'0':'普通会议','1':'重要会议'}" name="encryptFlg" value="0"></s:radio> -->
						</td>
					</tr>
					<tr >
						<td>注意事项</td>
						<td><s:textarea name="attention" cssStyle="width:350px" rows="3"/></td>
					</tr>
					<tr>
						<td>备注</td>
						<td><s:textarea name="remark" cssStyle="width:350px" rows="3"/></td>
					</tr>
					<tr style="height:10px;">
					<td colspan="2">&nbsp;</td>
					</tr>
					<tr style="height:50px;">
						<td colspan="2" align="center">
							<input type="button" class="btn_blue" onclick="saveRes();" value="预定" />
							<input type="button" class="btn_red" onclick="cancel();" value="取消" />
						</td>
					</tr>
				</table>	
				</form>
			</div>
			<div style="margin-left:510px;max-width: 300px;">
					<div style="font-weight:bold;">
					<span style="margin-right:5px;">查看会议室预定情况</span><img src="${ctx}/images/meetingRoom/pic_down_blue.gif"/>
					</div>
					<div style="margin-top: 10px;margin-bottom: 10px;"><span style="margin-right:5px;">选择日期</span>
					<input type="text" value="${currDay}" style="width:70px;" onfocus="WdatePicker({minDate:'%y-%M-%d',onpicked:function(dp){changeMeetingDate(dp);}})"/>
					</div>
					<div id="meetingRoomResCon"></div>
			</div>
			</fieldset>
		</div>
		<div id="searchUserDiv" class="searchUserDiv"></div>
		<script type="text/javascript">
			var cfg = {};
			cfg.data = {tabId:'resMeeting'};
			function saveRes(){
				if(!checkForm())return;
				var confStr = "请确认会议室信息：\r\r";
				var roomName= [];
				$("#roomForm div input[name='roomIds']:checked").each(function(){
					roomName.push($(this).next().text());
				});
				confStr += roomName.join(",")+"。   \r";
				var dateStr = $("#meetingDateId").val()+"   "+$("#meetingDateBeginId").val()+" ~ "+$("#meetingDateEndId").val();
				
				if(!confirm(confStr+dateStr))return;
				
				$("#roomForm").ajaxSubmit(function(result){
					if(result == "success"){
						alert("申请已提交成功,等待管理员审批！");
						window.parent.TabUtils.closeTab(cfg);
					}else{
						window.parent.TabUtils.closeTab(cfg);
					}
				});
			}
			function checkForm(){
				var str = [];
				if($("#roomForm div input[name='roomIds']:checked").length == 0){
					str.push("请选择会议室");
				}
				if($.trim($("#subject").val()) == ""){
					str.push("主题不能为空");
				}
				if($("#meetingDateId").val() == ""||$("#meetingDateBeginId").val() == ""||$("#meetingDateEndId").val() == ""){
					str.push("会议日期不能为空");
				}
				if($.trim($("#compere").val()) == ""){
					str.push("请选择主持人");
				}
				if($.trim($("#applicant").val()) == ""){
					str.push("请选择召集人");
				}
				if($.trim($("#recorder").val()) == ""){
					str.push("请选择记录人");
				}
				if($.trim($("#otherUserCds").val()) == ""){
					str.push("请选择参会人员");
				}
				if($.trim($("#otherNum").val()) == ""){
					str.push("请填写参会人数");
				}
				if(str.length>0){
					alert(str.join("\r"));
					return false;
				}
				return true;
			}
			function cancel(){
				window.parent.TabUtils.closeTab(cfg);
			}

			var searchTime;
			function showSearchUser(dom){
				var $currentDom = $(dom);
				var $next = $(dom).next();
				
				if(searchTime)clearTimeout(searchTime);
				searchTime = setTimeout(function(){
					var val = $.trim($currentDom.val());
					$next.val("");
					if(val == ""){
						$("#searchUserDiv").slideUp(200);
						return;
					}
					$.post("${ctx}/oa/oa-email!getUsersByFilter.action",{value:val,maxNum:10},function(result){
						result = eval(result);
						var $offset = $currentDom.offset();
						$("#searchUserDiv").css({left:$offset.left,top:$offset.top+$currentDom.height()}).empty().show();
						$.each(result,function(i,n){
							var html = "<div uiid='"+n.uiid+"'><span>"+n.userName +"</span>|<span>"+ n.orgName+"</span></div>";
							$("#searchUserDiv").append(html);
						});
					
						$("#searchUserDiv div").click(function(){
							var userName = $(this).children("span:first").text();
							var uiid = $(this).attr("uiid");
							$currentDom.val(userName);
							$next.val(uiid);
							$("#searchUserDiv").slideUp(200);
						});
						//$("#searchUserDiv").focus();
						//$(dom).focus();
					});
				}, 300);
				
				$(document).bind('click',function(event){
					var event  = window.event || event;
				    var obj = event.srcElement ? event.srcElement : event.target;
				    if(obj != dom){
				    	$("#searchUserDiv").slideUp(200);
				    	if($next.val() == ''){
				    		$currentDom.val('');
				    	}
				    }
				    $(document).unbind('click');
				});
				return false;
			}


			function changeMeetingDate(dp){
				refreshResInfo(dp.cal.getDateStr());
			}
			
			function refreshResInfo(meetingDate){
				$("#meetingRoomResCon").addClass("waiting");
				var param = {"simple":"1"};
				param.addrType = '${addrType}';
				if(meetingDate){
					param.currDay = meetingDate;
				}
				$.post("${ctx}/oa/oa-meeting-room-res!res.action",param,function(result){
					$("#meetingRoomResCon").html(result).removeClass("waiting");
					autoHeight();
				});
			}
			
			/*
			*旧的人员选择框
			function chooseUser(){
				getMember('other','','0');
			}*/
		
			
			$(function(){
				//人员选择框初始化
				$('#otherUserNames').ouSelect({
					showGroupFlg : true
				});
				
				$("#roomForm div input[name='roomIds']").click(function(){
					if($(this).attr("checked")==true){
						$(this).next().addClass("red");
						if($(this).attr('id') == 'roomIds-5'){
							alert('提示：\r该会议室使用投影仪请将电脑屏幕分辨率调整至1024*768或以下\r如有问题,请联系万记方(13761460105/2338)!');
						}
					}else{
						$(this).next().removeClass("red");
					}
				});
				var roomIds = "<s:property value='roomIds'/>";
				roomIds = roomIds.split(",");
				$.each(roomIds,function(i,v){
					v = $.trim(v);
					$("#roomIds-"+v).attr("checked",true).next().addClass("red");
				});
				refreshResInfo();
			});
		</script>
	</body>
</html>


