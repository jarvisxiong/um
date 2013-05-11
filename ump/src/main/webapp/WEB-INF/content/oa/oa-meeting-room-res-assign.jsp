<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%><html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>会议室预定确认</title>
		<link href="${ctx}/css/desk/desk-oa.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/desk/thickbox.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/resources/css/common/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/table.js"></script>
		<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	</head>
	
	<body>
		<div style="background-color: #5a5a5a;height:1px;margin-bottom:5px;"></div>
		<div style="padding-left: 10px;">
			<form action="${ctx}/oa/oa-meeting-room-res!save.action" method="post" id="roomForm">
				<input type="hidden" name="id" value="${oaMeetingRoomResId}" />
				<s:hidden name="recordVersion"/>
				<s:if test="status==0">
					<s:hidden name="status" value="1"/>
				</s:if>
				<s:else>
					<s:hidden name="status"/>
				</s:else>
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
								<span style="font-weight: bolder;">&nbsp;&nbsp;6F:</span>
								<input type="checkbox" name="roomIds" id="roomIds-9" value="9"/>
								<label class="checkboxLabel" for="roomIds-9">会议室4</label>
								<input type="checkbox" name="roomIds" id="roomIds-10" value="10"/>
								<label class="checkboxLabel" for="roomIds-10">洽谈室6</label>
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
								<input type="checkbox" name="roomIds" id="roomIds-S6" value="S6"/>
								<label class="checkboxLabel" for="roomIds-S6">洽谈室4</label>
							</div>
						</td>
					</tr>
					</s:if>
				<tr>
					<td width="80px">会议主题</td>
					<td>
						<s:textfield name="subject" cssStyle="width:400px" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td>会议日期</td>
					<td >
						<s:textfield cssClass="red date" name="currDay" id="meetingDateId" onfocus="WdatePicker()"/>
						<input type="text" name="meetingBegin" id="meetingDateBeginId" class="red time" style="width:80px;" value="<s:date name="beginTime" format='HH:mm' />" onfocus="WdatePicker({dateFmt:'HH:mm'})"/>
						到
						<input type="text" name="meetingEnd" id="meetingDateEndId" class="red time" style="width:80px;" value="<s:date name="endTime" format='HH:mm' />"  onfocus="WdatePicker({dateFmt:'HH:mm'})"/></td>
				</tr>
				<tr>
					<td>主持人</td>
					<td>
						<input type="text" value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("compere")) %>" readonly="readonly"/>
						<s:hidden name="compere" id="compere" />
					</td>
				</tr>
				<tr>
					<td>记录人</td>
					<td>
					<input type="text" value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("recorder")) %>" readonly="readonly"/>
					<s:hidden name="recorder" id="recorder"/>
					</td>
				</tr>
				<tr>
					<td>召集人</td>
					<td>
					<input type="text" value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("applicant")) %>" readonly="readonly"/>
					<s:hidden name="applicant" id="applicant" />
					</td>
				</tr>
				<tr>
					<td>参会人员</td>
					<td>
						<textarea style="width:400px;" rows="2" readonly="readonly"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("participators"),";") %></textarea>
						<s:hidden id="otherUserCds" name="participators"></s:hidden>
					</td>
				</tr>
				<tr>
					<td>参会人数</td>
					<td>约&nbsp;&nbsp;<s:textfield name="partNum" cssStyle="width:30px" readonly="true"/>&nbsp;&nbsp;人</td>
				</tr>
				<tr>
					<td>涉及部门</td>
					<td><s:textfield name="relatedDept" cssStyle="width:400px" readonly="true"/></td>
				</tr>
				<tr>
					<td>注意事项</td>
					<td><s:textarea name="attention" cssStyle="width:400px" rows="3" readonly="true"/></td>
				</tr>
				<tr>
					<td>备注</td>
					<td><s:textarea name="remark" cssStyle="width:400px" rows="3" readonly="true"/></td>
				</tr>
				<tr style="height:50px;">
					<td colspan="2" align="center">
						<input type="button" id="btn_save" class="btn_blue" onclick="saveRes();" value="确定" />
						<input type="button" id="btn_close" class="btn_red" onclick="cancel();" value="关闭" />
					</td>
				</tr>
			</table>	
			</form>
		</div>
			<div id="send_overlay" class="send_overlay">
			<s:if test="status == 0">
				<div class="send_loadImg"></div><div style="float:left">正在通知参会人员...</div>
			</s:if>
			<s:else>
				<div class="send_loadImg"></div><div style="float:left">正在通知会议室预定管理员...</div>
			</s:else>
			</div>
		<script type="text/javascript">
		function saveRes(){
			var confStr = "请确认会议室信息：\r\r";
			var roomName= [];
			$("#roomForm div input[name='roomIds']:checked").each(function(){
				roomName.push($(this).next().text());
			});
			confStr += roomName.join(",")+"\r";
			var dateStr = $("#meetingDateId").val()+"   "+$("#meetingDateBeginId").val()+" ~ "+$("#meetingDateEndId").val();
			
			if(!confirm(confStr+dateStr))return;

			$("#send_overlay").show();
			$("#btn_save").attr("disabled",true);
			$("#btn_close").attr("disabled",true);
			$("#roomForm").ajaxSubmit(function(result){
				if(result == "success"){
					window.parent.ymPrompt.close();
					window.parent.refreshRes();
					window.parent.refreshResInfo();
				}else{
					window.parent.ymPrompt.close();
				}
			});
		}
		function cancel(){
			window.parent.ymPrompt.close();
		}
		$(function(){
			$("#roomForm div input[name='roomIds']").click(function(){
				if($(this).attr("checked")==true){
					$(this).next().addClass("red");
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
			
		});
		</script>
	</body>
</html>


