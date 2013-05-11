<%@page
	import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@ include file="/common/global.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<script type="text/javascript">
var _ctx = '${ctx}'; 
</script>
<title>支出列表</title>

<s:if test="id==null">
	<legend>
		<font style="margin-left: 10px;color:#ffa613;font-size:14px ;font-weight:bold;">新增</font>
	</legend>
</s:if>

<s:form id="save">
	<table class="main_content"  border="0">
        <input type="hidden" name="id" id="id" value="${bisPayId}" />
		<input type="hidden" name="bisPayId" id="bisPayId" value="${bisPayId}" />

		<col width="12%">
		<col width="17%">
		<col width="14%">
		<col width="17%">
		<col width="14%">
		<col width="17%">
		
			<tr>
				<td align="right">项目:</td>
				<td>
					<input type="text" validate="required" class="required" id="bisProjectNameInput" name="bisProjectName" value="${projectName}" style="cursor: pointer; font-size: 12px; color: #ff0000;" />
					<input type="hidden" id="bisProjectIdInput" name="bisProjectId" value="${bisProjectId}" />
				</td>
			<s:if test="id!=null">
				<td align="right">创建人:</td>
				<td  colspan="3"><font style="clear: both;">&nbsp;<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %></font>
					&nbsp;&nbsp;<s:date name="createdDate" format="yyyy-MM-dd " /> </font></td>
			</s:if>
			</tr>
		<tr>
			<td align="right">业态:</td>
			<td>
				<select id="operationType"  name="operationType"  class="required">
					<option value="-1">_选择_</option>
					<option value="1" <c:if test="${operationType==1}"> selected="selected" </c:if> >商铺</option>
					<option value="2" <c:if test="${operationType==2}"> selected="selected" </c:if> >公寓</option>
					<option value="3" <c:if test="${operationType==3}"> selected="selected" </c:if> >多经</option>
					<option value="4" <c:if test="${operationType==4}"> selected="selected" </c:if> >其他选项</option>
				</select>
			</td>
			<td align="right">年:</td>
			<td><s:select validate="required" cssClass="required"
					list="@com.hhz.ump.util.DictMapUtil@getMapYear()"
					listKey="key" listValue="value" name="year" id="yearInput"></s:select>
			</td>
			<td align="right">月:</td>
			<td><s:select validate="required" cssClass="required"
					list="@com.hhz.ump.util.DictMapUtil@getMapMonth()"
					listKey="key" listValue="value" name="month" id="monthInput"></s:select>
			</td>
		</tr>
		<tr>
			<td align="right">类别:</td>
			<td><s:select validate="required" cssClass="required"
					list="@com.hhz.ump.util.DictMapUtil@getMapExpenseType()"
					listKey="key" listValue="value" name="chargeTypeCd"
					id="chargeTypeCdInput"></s:select>
			</td>
			<td align="right">预算金额(元):</td>
			<td><input type="text" value='${budgetMoney }' name=budgetMoney
				id=budgetMoneyInput onblur="formatNumber1($(this));">
			</td>
			<td align="right">支出金额(元):</td>
			<td><input type="text" class="required" value='${money }'
				name=money id=moneyInput onblur="formatNumber1($(this));">
			</td>
		</tr>
		<tr>
			<td align="right">备注:</td>
			<td colspan="3">
				 <textarea id="remark" name="remark" style="font-size:13px;">${remark }</textarea>
			</td>
			<s:if test="id==11">
				<td align="right">操作日期:</td>
				<td><s:date name="payDate" format="yyyy-MM-dd" /></td>
			</s:if>
		</tr>
		<tr style="margin: 10px;">
			<td colspan="6" style="padding-left: 75px; text-align: left; height: 40px; line-height: 40px;">
					<security:authorize ifAnyGranted="A_EXPE_INSERT">
						<button type="button" class="btn_green" onclick="addPay();"style="margin-left: 14px; margin-right: 2px;">保存</button>
					</security:authorize>
				<s:if test="bisPayId!=null">
					<security:authorize ifAnyGranted="A_EXPE_DELETE">
						<button type="button" class="btn_red" onclick="deletePay('${bisPayId}');" style="margin-right: 2px;">删除</button>
					</security:authorize>
					<button type="button" class="btn_red" onclick="ymPromptclose();">取消</button>
				</s:if> 
				<s:else>
					<button type="button" class="btn_red" onclick="add();" >取消</button>
				</s:else>
			</td>
		</tr>
	</table>

</s:form>
<script type="text/javascript">
var data ;
$(function(){
	//$('#bisProjectNameInput').onSelect({muti:false});
});
function ymPromptclose(){
	ymPrompt.close();
}
</script>
