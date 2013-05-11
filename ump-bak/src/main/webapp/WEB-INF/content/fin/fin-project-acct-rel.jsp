<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.JspUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
<link href="${ctx}/resources/css/common/TreePanel.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bus/fin.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" />
<!-- link rel="stylesheet" href="${ctx}/css/desk/oa-meeting.css" type="text/css" /-->
<script language="javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script language="javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
<script src="${ctx}/resources/js/datePicker/WdatePicker.js" type="text/javascript"></script> 
<!--<script type="text/javascript" src="${ctx}/js/tab.js"></script> -->
<script type="text/javascript">
function newBank(){
	$(".add_new").each(function() { 
		var disp = $(this).css("display");
		if (disp == "none") {
			$(this).show();
		} else {
			$(this).hide();
		}
	});
}
function doSave(){
	//$("#inputForm").submit(); 
	if($("#bankName").val()==""){
		alert("请输入项目名称");
		return;
	}
	if($("#accountTypeCd").val()==""){
		alert("请输入账户类型");
		return;
	}
	if($("#enableFlg").val()==""){
		alert("请选择是否可用");
		return;
	}
	if($("#currencyCd").val()==""){
		alert("请输入币种");
		return;
	}
	if($("#totalAmount").val()==""){
		alert("请输入金额");
		return;
	}
	if($("#accountTypeCd").val()=="2"){//现金帐号
	       if($("#enableFlg").val()=="2"){
	         alert("现金帐号不能为不可动用");
	         return false;
	       }
	     }
	$("#inputForm").attr("action","${ctx}/fin/fin-project-acct-rel!save.action");
	$("#inputForm").submit(); 
}
var nowId,nowName;
function doAttent(bankId,bankName){
	nowId =bankId;
	nowName =bankName;
}
function doDeleteBank(){
	if(nowId!=null){
		if(confirm("确定要删除"+nowName+"这条记录吗？")){
			var param = {id:nowId};
			$.post("${ctx}/fin/fin-project-acct-rel!delete.action",param, function(result) {
				if(result =="ok"){
					window.location.reload();
				}else if(result =="no"){
					alert("该账号有明细记录，请先删除明细记录");
				}
			});
		}
	}	
}
function doModifyBank(){
	
	if(nowId!=null){
		//alert("divTab的HTML内容：" + $("#divTab").html());
		// TabUtils.newTab("email","邮件","${ctx}/fin/fin-project-acct-rel!input.action?id="+nowId,true); 
		self.location="${ctx}/fin/fin-project-acct-rel!input.action?id="+nowId; 
	}
}
function doOnClick(id){
	var url ="${ctx}/fin/fin-in-out-detail!list.action?bankId="+id+"&beginTime=${beginTime}&endTime=${endTime}";
	//parent.showAll(url, "inOutDetail");
	parent.TabUtils.newTab("inOutDetail","明细",url,true);
	//self.location ="${ctx}/fin/fin-in-out-detail!list.action?bankId="+id+"&beginTime=${beginTime}&endTime=${endTime}"; 
}
function doQueryBank(){
	//if($("#beginTime").val()!=""&&$("#endTime").val()!=""){
	var url = "${ctx}/fin/fin-project-acct-rel!list.action?beginTime="+$("#beginTime").val()+"&endTime="+$("#endTime").val()+"&projectCd=${projectCd}";
		self.location = url;
		//self.location ="${ctx}/fin/fin-in-out-detail!list.action?bankId="+id+"&beginTime="+${beginTime}+"&endTime="+${endTime}; 
	//}
}
function doProFinish(projectCd,status){
	if(projectCd!=""){
		param ={finished:status,projectCd:projectCd};
		$.post("${ctx}/fin/fin-project-acct-rel!doProFinsh.action", param, function(result){
			if(result=="1"){
				$("#ProFinishBtn").hide();
			}else if(result=="2"){
				$("#CheckFinished").hide();
			}else if(result=="0"){
				$("#unFinished").hide();
			}
			});
	}
}
function doPeriExcel(cd,name){
	if($("#beginTime").val()==""||$("#endTime").val()==""){
		alert("请选择时间");
		return false;
	}
	var url = "${ctx}/fin/fin-project-acct-rel!doPeriExcel.action?beginTime="+$("#beginTime").val()+"&endTime="+$("#endTime").val()+"&projectCd="+cd;
		location.href = url;
	
}
</script>
</head>
<body>
<div class="title_bar">
			<div style="float:left; height:29px; padding-left:8px; padding-top:3px;"><img src="${ctx}/images/news/pic_employeeacts.gif" style="margin-top:1px;"></img></div>
			<div style="float:left; height:29px;">&nbsp;${projectName}</div>
		</div>
		<div style="height:2px;"></div>
<div style="height:29px; padding-left:8px; padding-top:8px;">
日期：<input type="text" id="beginTime" onfocus="WdatePicker()" title="从" value="${beginTime}" style="width: 70px;"/>&nbsp;到&nbsp;<input type="text" id="endTime" onfocus="WdatePicker()" title="到" value="${endTime}" style="width: 70px;"/>
<button type="button" id="ModBtn" class="btn_green_55_20" onclick="doQueryBank();">搜索</button>&nbsp;
<security:authorize ifAnyGranted="A_RJZ_USER,A_RJZ_ADMIN">
<button type="button" id="DelBtn" class="btn_red_35_20" onclick="doDeleteBank('${finProjectAcctRelId}','${bankName}');">删除</button>&nbsp;
<s:if test="haveFinished==0">
<button type="button" id="ProFinishBtn" class="btn_green_55_20" onclick="doProFinish('${projectCd}','1');">完成</button>&nbsp;
</s:if>
</security:authorize>
<security:authorize ifAnyGranted="A_RJZ_USER,A_RJZ_ADMIN">
<button type="button" id="ModBtn" class="btn_green_55_20" onclick="doModifyBank();">修改</button>&nbsp;
</security:authorize>
<security:authorize ifAnyGranted="A_RJZ_MANAGER,A_RJZ_ADMIN">
<s:if test="haveFinished!=2">
<button type="button" id="CheckFinished" class="btn_green_65_20" onclick="doProFinish('${projectCd}','2');">确认完成</button>&nbsp;
</s:if>
<s:else>
<button type="button" id="unFinished" class="btn_green_65_20" onclick="doProFinish('${projectCd}','0');">驳回完成</button>&nbsp;
</s:else>
</security:authorize>
<button type="button" id="DelBtn" class="fin_green_75_20" onclick="doPeriExcel('${projectCd}','${projectName}');">导出日报表</button>
</div>
<div id="TableDiv">
 <table class="content_table" >
	<thead>
		<tr>
			<th align="left" width="5%">
			<security:authorize ifAnyGranted="A_RJZ_USER,A_RJZ_ADMIN">
			<button type="button" id="NewBtn" class="btn_green_55_20" onclick="newBank();">新建</button>
			</security:authorize>
			</th>
			<th align="left" width="15%" nowrap="nowrap">项目名称</th>
			<th align="left" width="16%" nowrap="nowrap">账号</th>
			<th align="left" width="5%"nowrap="nowrap">账户类型</th>
			<th align="left" width="6%" nowrap="nowrap">是否可用</th>
			<th align="left" width="5%" nowrap="nowrap">币种</th>
			<th align="left" width="12%"nowrap="nowrap">期初金额</th>
			<th align="left" width="12%"nowrap="nowrap">收入</th>
			<th align="left" width="12%" nowrap="nowrap">支出</th>
			<th align="left" width="12%" nowrap="nowrap">期末金额</th>
		</tr>
	</thead>
	<tbody>
	<s:form id="inputForm" action="fin-project-acct-rel!save.action" method="post">
	  <input type="hidden" id="finProjectAcctRelId" name="id" value="${finProjectAcctRelId}"/>
	  <input type="hidden" name="projectCd" value="${projectCd}"/>
	    <tr class="add_new" style="display:none;">
		  <td>&nbsp;</td>
		  <td><s:textfield accesskey="width: 90%;" id="bankName" name="bankName" /></td>
		  <td><s:textfield accesskey="width: 100%;" id="accountNo" name="accountNo" /></td>
		  <td>
		  <select id="accountTypeCd" name="accountTypeCd">
		   <option value="1">银行</option>
		   <option value="2">现金</option>
	     </select>
		  </td>
		 <td>
		 <select id="enableFlg" name="enableFlg" >
		  <option value="1">可动用</option>
		  <option value="2">不可动用</option>
	     </select>
	     </td>
		 <td><s:textfield  id="currencyCd" name="currencyCd" cssStyle="width: 90%" value="RMB"/></td>
		 <td colspan="4">金额：<s:textfield  id="totalAmount" name="totalAmount" /> </td>
	  </tr>
	  <tr class="add_new" style="display:none;"><td colspan="2"><button type="button" id="NewSave" class="btn_list_add"onclick="doSave();">保存</button></td>
	  </tr>
	</s:form>
	
	<s:iterator value="results">
	  <tr class="mainTr" style="cursor:pointer;height:35px">
	     <td>
	     <security:authorize ifAnyGranted="A_RJZ_USER,A_RJZ_ADMIN">
	     <button type="button" id="NewBtn" class="btn_list_add" onclick="doAttent('${finProjectAcctRelId}','${bankName}');">选中</button>
	     </security:authorize>
	     </td>
	     <td onclick="doOnClick('${finProjectAcctRelId}');">${bankName}</td>
	     <td onclick="doOnClick('${finProjectAcctRelId}');">${accountNo}</td>
	     <td onclick="doOnClick('${finProjectAcctRelId}');">
	     <s:if test="accountTypeCd==1">银行</s:if>
	     <s:else>现金</s:else>
	     </td>
	     <td onclick="doOnClick('${finProjectAcctRelId}');">
	     <s:if test="enableFlg==1">可动用</s:if>
	     <s:else>不可动用</s:else>
	     </td>
	     <td onclick="doOnClick('${finProjectAcctRelId}');">${currencyCd}</td>
	     <td onclick="doOnClick('${finProjectAcctRelId}');" align="right"><%=JspUtil.formatMoney(JspUtil.findString("befAmount"))%>
	     </td>
	     <td onclick="doOnClick('${finProjectAcctRelId}');" align="right"><%=JspUtil.formatMoney(JspUtil.findString("inAmount"))%></td>
	     <td onclick="doOnClick('${finProjectAcctRelId}');" align="right"><%=JspUtil.formatMoney(JspUtil.findString("outAmount"))%></td>
	     <td onclick="doOnClick('${finProjectAcctRelId}');" align="right"><%=JspUtil.formatMoney(JspUtil.findString("aftAmount"))%></td>
	  </tr>
	</s:iterator>
	<s:iterator value="accList">
	 <tr bgcolor="#FFFFbe" height="35px">
	   <td colspan="5"  align="center">${bankName}</td>
	   <td>${currencyCd}</td>
	   <td align="right"><%=JspUtil.formatMoney(JspUtil.findString("befAmount"))%></td>
	   <td align="right"><%=JspUtil.formatMoney(JspUtil.findString("inAmount"))%></td>
	   <td align="right"><%=JspUtil.formatMoney(JspUtil.findString("outAmount"))%></td>
	   <td align="right"><%=JspUtil.formatMoney(JspUtil.findString("aftAmount"))%></td>
	 </tr>
	</s:iterator>
	</tbody>
 </table>
</div>
</body>
</html>