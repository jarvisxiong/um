<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<style>
<!--
table.sup_table input{background-color: #DBE5F1;
}
table.sup_table select{background-color: #DBE5F1;
}
table.sup_table input:focus{
	background-color: #ffffe1;
}
table.sup_table select:focus{
	background-color: #ffffe1;
table.sup_table textarea{background-color: #DBE5F1;
}
table.sup_table textarea:focus{
	background-color: #ffffe1;
}
}
-->
</style>
<%@page import="com.hhz.ump.util.JspUtil"%>
<form action="${ctx}/bis/bis-factyus!save.action" id="inputForm" method="post">
<legend>
		<font style="margin-left: 10px;color:#ffa613;font-size:14px ;font-weight:bold;">新增</font>
</legend>
	<table width="90%" height="90%" class="sup_table" style="width:100%;line-height:30px;">
	<input type="hidden" id="id" name="id" value="${bisFactId}"/>
		<tr  >
			<td align="right">
				<font style="color:red;">*</font>项目：
			</td>
			<td>
				<input id="bisProjectNameInput" style="width:100px;cursor: pointer; font-size: 12px; color: #ff0000;"
				 type="text"/>
				<input type="hidden" name="bisProjectId"  value="${bisProjectId}" readonly="readonly"/>
			</td>
			<td align="right">
				<div id="currDetailLabel"><font style="color:red;">*</font>租户：</div>
			</td>
			<td><select class="select_115_20" style="width:150px;"		name="currDetail"  onclick="selectDetail2();"
					id="layOutCdListInput"></select>
			</td>
		</tr>
		<tr>
			<td align="right"><font style="color:red;">*</font>实收日期：
			</td>
			<td>
				<input type="text"
				 onfocus="WdatePicker()" value="${factDate }"
				  name="factDate" id="factDate" class="inputBorder Wdate required select_115_20" validate="required"/>
			</td>
			<td align="right"><font style="color:red;">*</font>实收金额：
			</td>
			<td>
				<input type='text'  name='money' class="easyui-numberbox select_115_20"   value="${money }" onblur="formatNumber1($(this));"/>
			</td>
		</tr>
		<tr>
			<td align="right">备注：
			</td>
			<td colspan="3">
				<textarea rows="1" cols="20" name="remark" id="remark" style="font-size:12px;"></textarea>
			</td>
		</tr>
		<tr  >
			<td colspan="1"></td>
			<td colspan="2">
				<button type="button" class="btn_green_55_20" onclick="savefactyus();" >保存</button>
				<button type="button" class="btn_red_55_20" onclick="cance();">取消</button>
			</td>
			<td colspan="2"></td>
		</tr>
	</table>

</form>

