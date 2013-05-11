<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<script type="text/javascript">
var _ctx = '${ctx}'; 
</script>
<title>支出列表</title>
<style >
.mustSelet{color:red;}
</style>
	<s:if test="id==null">
	<legend>
		<font style="margin-left: 10px;color:#ffa613;font-size:14px ;font-weight:bold;">新增</font>
	</legend>
	</s:if>
	
<s:form id="save">
	<table class="main_content" style="width:640px;" >
		<input type="hidden" name="id" id="id" value="${bisPayId}" />
		<input type="hidden" name="bisPayId" id="bisPayId" value="${bisPayId}"/>
		<input type="hidden" name="chargeTypeCd" id="chargeTypeCdInput" value="B29"/>
		<col width="20%">
		<col width="25%">
		<col width="25%">
		<col width="25%">
		<tr>
			<td align="right">项目:</td>
			<td>
				<input  type="text" validate="required" class="required" id="bisProjectNameInput" name="bisProjectName" value="${projectName}" 
				style=" cursor: pointer; font-size: 12px; color: #ff0000;"/>
				<input type="hidden" id="bisProjectIdInput" name="bisProjectId" value="${bisProjectId}"/>
			</td>
			<s:if test="id!=null">
					<td align="right" >创建人:</td>
					<td colspan="2"><font style="clear:both;">&nbsp;<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("updator")) %></font>
						&nbsp;&nbsp;<s:date name="updatedDate" format="yyyy-MM-dd "/>   </font>
					</td>
			</s:if>
		</tr>
		<tr>
			<td align="right">年:</td>
			<td>
				<s:select   validate="required"  
					list="@com.hhz.ump.util.DictMapUtil@getMapYear()" cssClass="required"  listKey="key" listValue="value" 
					name="year" id="yearInput" ></s:select>
			</td>
			<td align="right">月:</td>
			<td >
				<s:select validate="required"  
				list="@com.hhz.ump.util.DictMapUtil@getMapMonth()" cssClass="required"   listKey="key" listValue="value" 
				name="month" id="monthInput"></s:select>
			</td>
		</tr>
		<tr>
			<td align="right">预计收入(元):</td>		
			<td>
				<input type="text" value='${budgetMoney}'   name=budgetMoney id=budgetMoneyInput   onblur="formatNumber1($(this));">
			</td>
			<td align="right">实际收入(元):</td>
			<td>
				<input type="text" value='${money}'  class="required" name=money id=moneyInput  onblur="formatNumber1($(this));">
			</td>
		</tr>
		<tr>
			<td align="right"><font style="margin-right:4px;">备注:</font></td>
			<td colspan="3" ><textarea id="remark" name="remark" style="font-size:13px;">${remark}</textarea></td>
		</tr>
		<tr>
			<td ></td>
			<td colspan="3">
				<security:authorize ifAnyGranted="A_EXPE_INSERT">
				<button type="button" class="btn_green" onclick="addPay();" style="margin-left:14px;margin-right:8px;">保存</button>
				</security:authorize>
				<s:if test="bisPayId!=null">
					<security:authorize ifAnyGranted="A_EXPE_DELETE">
					<button type="button" class="btn_red" onclick="deletePay('${bisPayId}');"  style="margin-right:8px;">删除</button>
					</security:authorize>
					<button type="button" class="btn_red" onclick="ymPromptclose();" >取消</button>
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
	  $.ajaxSetup({cache:false});
	//$('#bisProjectNameInput').onSelect({muti:false});
});
function ymPromptclose(){
	ymPrompt.close();
}
</script>
