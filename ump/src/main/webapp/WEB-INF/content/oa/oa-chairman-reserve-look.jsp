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
		<title>预约总裁</title>
		<link href="${ctx}/css/desk/desk-oa.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/desk/thickbox.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/table.js"></script>
		<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	</head>
	
	<body>
		<div style="background-color: #5a5a5a;height:1px;margin-bottom:5px;"></div>
		<div style="padding-left: 10px;">
			<form action="${ctx}/oa/oa-chairman-reserve!save.action" method="post" id="roomForm">
				<input type="hidden" name="id" value="${oaChairmanReserveId}" />
				<s:hidden name="recordVersion"/>
				<s:hidden name="status" value="1"/>
			<table width="100%" cellpadding="0" cellspacing="0" class="editTable">
				
				<tr>
					<td width="80px">预约</td>
					<security:authorize ifAnyGranted="A_CHAIRMAN_RES_ADMIN,A_CEO_RES_ADMIN">
						<td>
							<div>
								<input type="checkbox" name="chairmanType" id="roomIds-0" value="0"/>
								<label class="checkboxLabel" for="roomIds-0">总裁</label>
								<input type="checkbox" name="chairmanType" id="roomIds-1" value="1"/>
								<label class="checkboxLabel" for="roomIds-1">执行总裁</label>
							</div>
						</td>
					</security:authorize>
					<security:authorize ifNotGranted="A_CHAIRMAN_RES_ADMIN,A_CEO_RES_ADMIN">
						<td>
							<div>
								<input type="checkbox" name="chairmanType" id="roomIds-0" value="0" readonly="readonly"/>
								<label class="checkboxLabel" for="roomIds-0">总裁</label>
								<input type="checkbox" name="chairmanType" id="roomIds-1" value="1" readonly="readonly"/>
								<label class="checkboxLabel" for="roomIds-1">执行总裁</label>
							</div>
						</td>
					</security:authorize>
				</tr>
				<tr>
					<td width="80px">会议主题</td>
					<td>
						<s:textfield name="subject" cssStyle="width:400px" readonly="true"/>
					</td>
				</tr>
				<security:authorize ifAnyGranted="A_CHAIRMAN_RES_ADMIN,A_CEO_RES_ADMIN">
				<tr>
					<td>会议日期</td>
					<td >
						<s:textfield cssClass="red" name="currDay" id="meetingDateId" onfocus="WdatePicker()"/>
						<input type="text" name="meetingBegin" id="meetingDateBeginId" class="red" style="width:50px;" value="<s:date name="beginTime" format='HH:mm' />" onfocus="WdatePicker({dateFmt:'HH:mm'})"/>
						到
						<input type="text" name="meetingEnd" id="meetingDateEndId" class="red" style="width:50px;" value="<s:date name="endTime" format='HH:mm' />"  onfocus="WdatePicker({dateFmt:'HH:mm'})"/></td>
				</tr>
				</security:authorize>
				<security:authorize ifNotGranted="A_CHAIRMAN_RES_ADMIN,A_CEO_RES_ADMIN">
					<tr>
						<td>会议日期</td>
						<td >
							<s:textfield cssClass="red" name="currDay" id="meetingDateId" readonly="true"/>
							<input readonly="readonly" type="text" name="meetingBegin" id="meetingDateBeginId" class="red" style="width:50px;" value="<s:date name="beginTime" format='HH:mm' />"/>
							到
							<input readonly="readonly" type="text" name="meetingEnd" id="meetingDateEndId" class="red" style="width:50px;" value="<s:date name="endTime" format='HH:mm' />"/></td>
					</tr>
				</security:authorize>
				<tr>
					<td>主持人</td>
					<td>
						<input type="text" name="compere" value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("compere")) %>" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>记录人</td>
					<td><input type="text" name="recorder" value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("recorder")) %>" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>召集人</td>
					<td><input type="text" name="applicant" value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("applicant")) %>" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>参会人员</td>
					<td>
						<textarea style="width:400px;height:40px;" readonly="readonly"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("participators"),";") %></textarea>
					</td>
				</tr>
				<tr>
					<td>参会人数</td>
					<td>约<s:textfield name="partNum" cssStyle="width:30px" readonly="true"/>人</td>
				</tr>
				<tr>
					<td>注意事项</td>
					<td><s:textarea name="attention" cssStyle="width:400px;height:60px;" readonly="true"/></td>
				</tr>
				<tr>
					<td>备注</td>
					<td><s:textarea name="remark" cssStyle="width:400px;height:60px;" readonly="true"/></td>
				</tr>
				<security:authorize ifAnyGranted="A_CHAIRMAN_RES_ADMIN,A_CEO_RES_ADMIN">
					<tr style="height:50px;">
						<td colspan="2" align="center">
							<input type="hidden" name="editFlg" value="1"/>
							<input type="button" onclick="saveRes();" style="width:60px;height:25px;line-height: 20px;" value="确定" />
							<input type="button" onclick="cancel('${oaChairmanReserveId}');" style="width:60px;height:25px;line-height: 20px;color:red;" value="取消预约" />
							<input type="button" onclick="closeWin();" style="width:60px;height:25px;line-height: 20px;" value="关闭" />
						</td>
					</tr>
				</security:authorize>
			</table>	
			</form>
		</div>
		<script type="text/javascript">
		
		function saveRes(){
			var confStr = "请确认预约信息修改为：\r\r";
			var roomName= [];
			$("#roomForm div input[name='chairmanType']:checked").each(function(){
				roomName.push($(this).next().text());
			});
			confStr += roomName.join(",")+"\r";
			var dateStr = $("#meetingDateId").val()+"   "+$("#meetingDateBeginId").val()+" ~ "+$("#meetingDateEndId").val();
			
			if(!confirm(confStr+dateStr))return;

			//TB_showMaskLayer("正在通知参会人员...");
			$("#roomForm").ajaxSubmit(function(result){
				//TB_removeMaskLayer();
				if(result == "success"){
					window.parent.ymPrompt.close();
					window.parent.refreshRes();
					window.parent.refreshResInfo();
				}else{
					window.parent.ymPrompt.close();
				}
			});
		}
		function closeWin(){
			window.parent.ymPrompt.close();
		}
		function cancel(id){
			if(!confirm("确认要取消该约会吗？"))return;
			$.post("${ctx}/oa/oa-chairman-reserve!cancel.action",{id:id},function(){
				alert("约会已经取消");
				window.parent.ymPrompt.close();
				window.parent.refreshResInfo();
			});
		}

		$(function(){
			$("#roomIds-0").click(function(){
				if($(this).attr("checked")==true){
					$("#roomIds-1").attr("checked",false).next().removeClass("red");
					$(this).next().addClass("red");
				}else{
					$(this).next().removeClass("red");
				}
			});
			$("#roomIds-1").click(function(){
				if($(this).attr("checked")==true){
					$("#roomIds-0").attr("checked",false).next().removeClass("red");
					$(this).next().addClass("red");
				}else{
					$(this).next().removeClass("red");
				}
			});
			var chairmanType = "<s:property value='chairmanType'/>";
			$("#roomIds-"+chairmanType).attr("checked",true).next().addClass("red");
		});
		</script>
	</body>
</html>


