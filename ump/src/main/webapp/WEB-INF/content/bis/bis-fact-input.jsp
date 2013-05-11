<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%@page import="com.hhz.ump.util.JspUtil"%>
<form action="${ctx}/bis/bis-fact!save.action" id="inputForm" method="post">
<legend>
	<s:if test="inputStatus==0">
		<font style="margin-left: 10px;color:#ffa613;font-size:14px ;font-weight:bold;">新增</font>
	</s:if>
	<s:if test="inputStatus==1">
		<span style="height:10px;width:90%;display:block"></span>
	</s:if>
</legend>
	<table  class="sup_table main_content" >
	<input type="hidden" id="id" name="id" value="${bisFactId}"/>
	<input type="hidden" id="currDetail" name="currDetail" value="${currDetail}"/>
	<input type="hidden" id="currDetailName" name="currDetailName" value="${currDetailName}"/>
	<input type="hidden" id="bisContIdInput" name="bisContIdInput"/> 
	<input type="hidden" id="bisContId" name="bisContId"/> 
	<col width="8%">
	<col width="12%">
	<col width="8%">
	<col width="12%">
	<col width="12%">
	<col width="12%">
	<col width="12%">
	<col width="12%">
		<tr  >
			<td align="right">
				项目：
			</td>
			<td>
				<input id="bisProjectNameInput" class="required" style="cursor: pointer; font-size: 12px; color: #ff0000;"
				 type="text"/>
				<input type="hidden" id="bisProjectIdInput" name="bisProjectId" class="required"  value="${bisProjectId}" readonly="readonly"/>
			</td>
			<td align="right">
				业态：
			</td>
			<td>
				<input id="layOutCdInput0" value=""  class="required"  readonly="readonly"/>
				<input	name="layOutCd" id="layOutCdInput" class="required"  value="" type="hidden"/>
			</td>
			<td align="right">
				<div id="currDetailLabel">租户：</div>
			</td>
			<td><select class="select_115_20 required" 		name="currDetail"  onclick="selectDetail2();"
					id="layOutCdListInput"></select>
			</td>
			<td align="right">
				类别：
			</td>
			<td><s:select cssClass="select_115_20 required" validate="required" 
			list="@com.hhz.ump.util.DictMapUtil@getMapChargeType()" listKey="key" listValue="value" 
			name="chargeTypeCd" id="chargeTypeCdInput"></s:select>
			</td>
		</tr>
		<tr>
			<td align="right">
				年：
			</td>
			<td>
                    <select class="box required" style="width:141px; padding-left: 2px;" name="factYear" id="factYear">
                        <option value="">请选择</option>
                        <option value="2009">2009</option>
                        <option value="2010">2010</option>
                        <option value="2011">2011</option>
                        <option value="2012" selected="selected">2012</option>
                        <option value="2013">2013</option>
                        <option value="2014">2014</option>
                        <option value="2015">2015</option>
                    </select>
			</td>
			<td align="right">
				月：
			</td>
			<td><s:select cssClass="select_115_20" validate="required" 
				list="@com.hhz.ump.util.DictMapUtil@getMapMonth()" listKey="key" listValue="value" 
				name="factMonth" id="factMonth"></s:select>
			</td>
			<td align="right">实收日期：
			</td>
			<td>
				<input type="text"
				 onfocus="WdatePicker()" value="${factDate }"
				  name="factDate" id="factDate" class="inputBorder Wdate required select_115_20" validate="required"/>
			</td>
			<td align="right">实收金额：
			</td>
			<td>
				<input type='text' id='money' name='money' class="easyui-numberbox required select_115_20"   value="${money }" onblur="formatNumber1($(this));"/>
			</td>
		</tr>
		<tr>
			<td align="right">备注：
			</td>
			<td colspan="4">
				<textarea rows="1" cols="20" name="remark" id="remark" style="font-size:12px;"></textarea>
			</td>
		</tr>
		<s:if test="inputStatus==0">
		<tr  >
			<td></td>
			<td colspan="7">
				<button type="button" class="btn_green" onclick="save('inputForm');" >保存</button>
				<button type="button" class="btn_red" onclick="cance();">取消</button>
			</td>
		</tr>
		</s:if>
	</table>

</form>

