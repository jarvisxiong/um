<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.JspUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bus/fin.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" />
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script> 
</head>
<body>
	<form action="fin-project!doCashExcel.action" method="post" id="searchForm">
	      <input type="hidden" id="search_begin" name="beginTime"/>
	      <input type="hidden" id="search_end" name="endTime"/>
	     </form>
	<div class="title_bar">
		<div style="float:left; height:29px; padding-left:8px; padding-top:8px;"><img src="${ctx}/images/news/pic_employeeacts.gif" style="margin-top:1px;"></img></div>
		<div style="float:left; height:29px;">&nbsp;日记账</div>
	</div>
	<div style="height:2px;"></div>
	<div>
		<div style="height:29px; padding-top:8px;padding-left:100px;">
		日期：<input type="text" id="beginTime" onfocus="WdatePicker()" title="从" value="${beginTime}"/>&nbsp;到&nbsp;<input type="text" id="endTime" onfocus="WdatePicker()" title="到" value="${endTime}"/>
		<button type="button" id="ModBtn" class="btn_green_55_20" onclick="doQueryBank();">搜索</button>
		<security:authorize ifAnyGranted="A_RJZ_MANAGER,A_RJZ_ADMIN,A_RJZ_VIEW">
		<s:if test="results!=null&&results.size()>0">
		<button class="fin_green_75_20" onclick="exportResult('true');">上市报表</button>
		<button class="fin_green_75_20" onclick="exportResult('false');">非上市报表</button>
		</s:if>
		<s:url id="exportExl" action="fin-project!doCashExcel.action" namespace="/fin"  includeParams="none"  />
		
		</security:authorize>
	</div>
	</div>
	<div id="TableDiv" style="overflow: hidden;">
	 	<table class="content_table">
			<thead>
				<tr>
				    <th align="left" width="6%" nowrap="nowrap">是否上市</th>
					<th align="left" width="19%" nowrap="nowrap">公司名称</th>
					<th align="left" width="10%" nowrap="nowrap">收入</th>
					<th align="left" width="10%" nowrap="nowrap">支出</th>
					<th align="left" width="17%" nowrap="nowrap">可动用银行存款及现金小计</th>
					<th align="left" width="18%" nowrap="nowrap">不可动用银行存款及现金小计</th>
					<th align="left" width="6%" nowrap="nowrap">本日状态</th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="results">
			<s:if test="statusCd==finish">
			     <tr class="mainTr" style="cursor:pointer;height:35px" onclick="doOnClick('${finProjectCd}');" bgcolor="#dbebee">
			     <td><s:if test="publicFlg=='true'">是</s:if><s:else>否</s:else> </td>
			     <td>${projectName}</td>
			     <td align="right" ><%=JspUtil.formatMoney(JspUtil.findString("inAmount"))%></td>
			     <td align="right"><%=JspUtil.formatMoney(JspUtil.findString("outAmount"))%></td>
			     <td  align="right"><%=JspUtil.formatMoney(JspUtil.findString("useableAmount"))%></td>
			     <td  align="right"><%=JspUtil.formatMoney(JspUtil.findString("unUseableAmount"))%></td>
			     <td align="right">已完成</td>
			     </tr>
		     </s:if>
		     <s:elseif test="statusCd==finished">
			     <tr class="mainTr" style="cursor:pointer;height:35px" onclick="doOnClick('${finProjectCd}');" bgcolor="#FFFFbe">
			     <td><s:if test="publicFlg=='true'">是</s:if><s:else>否</s:else> </td>
			     <td>${projectName}</td>
			     <td  align="right"><%=JspUtil.formatMoney(JspUtil.findString("inAmount"))%></td>
			     <td  align="right"><%=JspUtil.formatMoney(JspUtil.findString("outAmount"))%></td>
			     <td  align="right"><%=JspUtil.formatMoney(JspUtil.findString("useableAmount"))%></td>
			     <td  align="right"><%=JspUtil.formatMoney(JspUtil.findString("unUseableAmount"))%></td>
			     <td  align="right">
			                  确认完成
			     </td>
			     </tr>
		     </s:elseif>
		     <s:else>
			     <tr class="mainTr" style="cursor:pointer;height:35px" onclick="doOnClick('${finProjectCd}');">
			      <td><s:if test="publicFlg=='true'">是</s:if><s:else>否</s:else> </td>
			     <td>${projectName}</td>
			     <td  align="right"><%=JspUtil.formatMoney(JspUtil.findString("inAmount"))%></td>
			     <td  align="right"><%=JspUtil.formatMoney(JspUtil.findString("outAmount"))%></td>
			     <td  align="right"><%=JspUtil.formatMoney(JspUtil.findString("useableAmount"))%></td>
			     <td  align="right"><%=JspUtil.formatMoney(JspUtil.findString("unUseableAmount"))%></td>
			     <td align="right">未完成</td>
			     </tr>
		     </s:else>
			 </s:iterator>
			 </tbody>
	 	</table>
	</div>
	<div style="height:8px;"></div>
	<div style="height: 1px; background-color: rgb(255, 255, 255);"></div>
	<div style="height: 1px;"></div>
	
	<script type="text/javascript">
		function doOnClick(projectCd){
			if(projectCd!=""){
				var url ="${ctx}/fin/fin-project-acct-rel!list.action?projectCd="+projectCd+"&beginTime="+$("#beginTime").val()+"&endTime="+$("#endTime").val();
				//TabUtils.newTab("projectAcc","项目",url,true);
				parent.TabUtils.newTab("projectAcc","公司",url,true);
			}
		}
		function doQueryBank(){
			if($("#beginTime").val()!=null&&$("#endTime").val()!=null){
			var url = "${ctx}/fin/fin-project!getProject.action?beginTime="+$("#beginTime").val()+"&endTime="+$("#endTime").val()+"&projectCd=${projectCd}";
			self.location = url;
			}else{
				alert("请输入搜索日期");
			}
		}
		function doCashExcel(){
			if($("#beginTime").val==""||$("#endTime").val()==""){
				alert("请选择搜索时间");
				return;
			}
			var url = "${ctx}/fin/doCashExcel.action?beginTime="+$("#beginTime").val()+"&endTime="+$("#endTime").val();
			location.href = url;
		}
		//更新余额
		function updateBalance(){
			var url = "${ctx}/fin/fin-project!updateBalance.action";
			location.href = url;
		}
		function exportResult(type){
			if($("#beginTime").val==""||$("#endTime").val()==""){
				alert("请选择搜索时间");
				return;
			}
			$("#search_begin").val($("#beginTime").val());
			$("#search_end").val($("#endTime").val());
			$("#searchForm").attr("action","${ctx}/fin/fin-project!doCashExcel.action?publicFlg="+type);
			$("#searchForm").submit();
		}
	</script>
</body>
</html>