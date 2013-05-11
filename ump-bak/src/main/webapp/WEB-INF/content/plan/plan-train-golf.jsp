<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/global.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />	
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/plangolf.css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
<script language="javascript" src="${ctx}/js/table.js"></script>
<script type="text/javascript" src="${ctx}/js/home/golf.js"></script>


<title>高尔夫管理</title>
</head>

<body>
<div class="title_bar" style="overflow:hidden;">
	<div style="float:left; height:29px;">
		&nbsp;高尔夫管理
	</div>
</div>

			<s:form id="mainForm" action="plan-train-golf.action" method="post">
			<div style=" background-color: #E6E6E6; padding-left: 20px;">
			
			<table style=" padding-left: 20px;">
			   <tr>
			    <td>
			   <div style="float:left;padding-top: 20px;">     
			        第几期：<s:textfield name="filter_EQL_trainPeriod" id="filter_EQL_trainPeriod" />
			        姓名：<s:textfield  id="filter_LIKES_creator" onkeyup="showSearchUser(this)"/> <input type="hidden" name="filter_LIKES_creator"/>
			        日期 : <s:textfield id="filter_LED_createdDate" name="filter_LED_createdDate" onfocus="WdatePicker()" cssClass="Wdate" size="12" /> 
		          </div>
		          <div class="btn_blue_golf" style="float:left;margin-left: 5px;"   onclick="search();" ><s:text name="common.search" /> </div>
				  <div class="btn_green_golf" style="float:left;margin-left: 5px;"   onclick="resetToEmpty();" ><s:text name="common.reset" /></div>
				  <div class="btn_green_golf" style="float:left;margin-left: 5px;" onclick="exportExcel();" >导出</div>
		      </td>
			   </tr>
			</table>
			</div>
			<div>
			<table class="content_table" style="width:98%" align="center">
				<tr align="left">
					<th align="left">第几期</th>
					<th align="left">姓名</th>
					<th align="left">第几题</th>
					<th align="left">答案</th>
					<th align="left">日期</th>
					<th align="left">备注</th>
				</tr>
				<s:iterator value="page.result">
					<tr class="mainTr">
						<td><s:property value="trainPeriod"/> </td>
						<td><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %></td>  
						<td><s:property value="questionNo"/></td>
						<td><s:property value="answer"/></td>
						<td><s:property value="createdDate"/></td>
						<td><s:property value="remark"/></td>
					</tr>
				</s:iterator>       
			</table>
			</div>
			<div style="padding:10px 0 20px 0;" align="right">
		         <s:form id="mainForm" action="plan-train-golf.action" method="post">
          				<!-- 分页信息 -->
			 		<s:if test="page.hasPre || page.hasNext">
					<tr class="row_epage"><td  style="width:100%;text-align: center;" > <p:page pageInfo="page" /></td></tr>
						<script language="javascript">
							//重新调整第n页input样式
							$("#pageTo").css("height", "18px").css("width", "30px");
						</script>
					</s:if>
			    </s:form>
		   </div>
			</s:form>

<div id="searchUserDiv" class="searchUserDiv"></div>	
</body>
</html>
