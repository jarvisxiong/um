<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<div>
<input type="checkbox" onclick="checkedAll($(this).attr('checked'));" style="cursor:pointer;" title="全选/不选"/>&nbsp;
用户：<input type="text" id="ProUserName" readonly="readonly" />&nbsp;
<button type="button" id="SaveUserBtn" class="btn_list_add" onclick="saveProjectUser();">保存</button>
<input type="text" id="ProUserCd" readonly="readonly" />&nbsp;
<button type="button" id="ReturnBtn" class="btn_list_add" onclick="self.location='${ctx}/fin/fin-project!list.action'">返回</button>
</div>
<div id="TableDiv">
<table  class="content_table">
<tbody>
<s:iterator value="page.result">
	  <tr class="mainTr" style="cursor:pointer;">
	   <td><input type="checkbox" class="chkClass" id="chk_all_${projectCd}" value="${projectCd}"></input></td>
	   <td>${projectName}</td>
	   <td>${projectCd}</td>
	  </tr>
	</s:iterator>
	<tr>
	<td>&nbsp;</td>
	</tr>
	<tr>
	<td>&nbsp;</td>
	</tr>
	<tr>
	<td>&nbsp;</td>
	</tr>
</tbody>
</table>
</div>