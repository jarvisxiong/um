<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>

<form action="${ctx}/bis/bis-fact!save.action" id="inputForm">
	<input type="hidden" id="id" name="id" value="${voFact.bisFactId}"/>
	<input type="hidden" id="chargeTypeEdit" name="chargeTypeEdit" value="${voFact.chargeTypeCd}"/>
	<input type="hidden" id="bisContIdEdit" name="bisContIdInput"/> 
	<table class="main_content" style="width: 95%;margin-top: 20px;" border="0" >
		<col width="20%">
		<col width="30%">
		<col width="20%">
		<col width="30%">
		<tr> 
			<td align="right" >费用类别：</td>
			<td>
				 <s:if test="voFact.statusCd==1">
					<input id="chargeTypeCdInput" type="text"  disabled="disabled" value="<p:code2name mapCodeName='mapChargeType' value='voFact.chargeTypeCd'/>" />
				</s:if> 
				<s:else>
					<s:select cssClass="select_115_20 required"  validate="required" 
					list="@com.hhz.ump.util.DictMapUtil@getMapChargeType()" listKey="key" listValue="value" 
					name="chargeTypeCd" id="chargeTypeCdInput" value="voFact.chargeTypeCd"></s:select>
				</s:else>
			</td>
			<td align="right" >&nbsp;</td>
			<td>&nbsp;</td>
		</tr> 
		<tr> 
			<td  align="right" >年：</td>
			<td>
				 <s:if test="voFact.statusCd==1">
					<input id="eyear" type="text"  disabled="disabled" value="${voFact.factYear }"/>
				</s:if> 
				<s:else>
					<s:select id="eyear" list="@com.hhz.ump.util.DictMapUtil@getMapYear()" listKey="key" listValue="value" name="voFact.factYear" ></s:select>
				</s:else>
			</td>
			
			<td  align="right" >月：</td>
			<td>
				<s:if test="voFact.statusCd==1">
					<input  type="text"  id="emonth" disabled="disabled" value="${ voFact.factMonth}"  />
				</s:if> 
				<s:else>
					<s:select id="emonth" list="@com.hhz.ump.util.DictMapUtil@getMapMonth()" listKey="key" listValue="value" name="voFact.factMonth"  ></s:select>
				</s:else>
			</td>
		</tr> 
		<tr > 
			<td  align="right">实收金额：</td>
			<td>
				<input type="text"    <s:if test="voFact.statusCd==1">disabled="disabled"</s:if> id="newmoney" onblur="formatNumber1($(this));" value="${voFact.money}" />
				<input type="hidden" id="oldmoney" value="${voFact.money }"   />
			</td>
			
			<td  align="right">应收金额：</td>
			<td><input type="text" id="mustmoney" value="${voFact.mustMoney }" disabled="disabled"></td>
		</tr>
		<tr> 
			<td  align="right">实收日期：</td>
			<td>
				<input type="text"  <s:if test="voFact.statusCd==1">disabled="disabled"</s:if>
				 onfocus="WdatePicker()" value="<s:if test="voFact.factDate!=null">20${voFact.factDate}</s:if>"
				  name="factEDate" id="factEDate" class="inputBorder Wdate required " validate="required"/>
			</td>
			<td  align="right">状态：</td>
			<td>
				<input type="text"   disabled="disabled"  id=statusCd value="${voFact.statusCdName }" disabled="disabled">
			</td>
		</tr>
		<tr>
		<s:if test="voFact.contLayOutCd==1">
			<td align="right">经营性质：</td>
			<td >${voFact.manageCd}</td >
			<td align="right"><%--租户性质：--%></td>
			<td><%--${voFact.shopTypeCd}--%>
			</td>
		</s:if>
		<s:if test="voFact.contLayOutCd==2">
			<td  align="right">业态：</td>
			<td>${voFact.layoutCd}</td>
			<td align="right">房屋结构：</td>
			<td>${voFact.houseStruCd}</td>
		</s:if>
		<s:if test="voFact.contLayOutCd==3">
			<td  align="right">位置区域：</td>
			<td><p style="font-size:12px;line-height:15px;">${voFact.operationArea}</p>	</td>
			<td  align="right">面积：</td>
			<td>${voFact.square}</td>
		</s:if>
		</tr>
		<tr >
			<td  align="right">创建人：</td>
			<td><s:property value="voFact.creator"/>	</td>
			<td  align="right">审核人：	</td>
			<td> <s:property value="voFact.checkUserCd"/></td>
		</tr>
		<tr >
			<td  align="right">创建时间：</td> 
			<td>${voFact.createDate }</td>
			<td  align="right">审核时间：</td>
			<td>${voFact.checkDate }</td>
		</tr>
		<s:if test="voFact.bisContId!=null">
			<tr >
				<td  align="right">合同编号：</td> 
				<td>${voFact.contNo } </td>
				<td  align="right">合同类别：</td>
				<td>${voFact.contBigTypeCd }</td>
			</tr>
		</s:if>
		<tr>
			<td  align="right">备注：</td> 
			<td colspan="3" class="pd-chill-tip" title="${voFact.remark }">
				 <s:if test="voFact.statusCd==1">
					<input type="hidden" id="eremark" value="${voFact.remark }"/>
					<div class="ellipsisDiv_full"   style="width:240px">${voFact.remark }</div> 
				</s:if> 
				<s:else>
					<textarea rows="2"  style="font-size:12px;" id="eremark" title="${voFact.remark }">${voFact.remark }</textarea>
				</s:else>
			</td>
		</tr> 
		<tr>
			<td colspan="4" align="center"> 
			<s:if test="voFact.statusCd==0||voFact.statusCd==2">
				<security:authorize ifAnyGranted="A_FACT_CHECK">
					<input type="button" class='btn_blue' id="btn_pass" value='审核' onclick='factPass("${voFact.bisFactId}");'/>
				</security:authorize>	
				<security:authorize ifAnyGranted="A_FACT_INSERT">
					<input type="button" class='btn_green' id="btn_save" value='保存' onclick='factModify("${voFact.bisFactId}");'/>
				</security:authorize>	
				<security:authorize ifAnyGranted="A_FACT_DELETE">
					<input type="button" class='btn_red' id="btn_del" value="删除" onclick='factRemove("${voFact.bisFactId}");'/>
				</security:authorize>	
			</s:if>
			<s:if test="voFact.statusCd!=2">
				<input type="hidden" name="hasDichongFlag" id="hasDichongFlag" value="${hasDichongFlag }"/>
				<security:authorize ifAnyGranted="A_FACT_CHECK">
					<input type="button" class='btn_red' id="btn_reject" value="驳回" onclick='factReject("${voFact.bisFactId}");'/>
				</security:authorize>	
				<security:authorize ifAnyGranted="A_FACT_INSERT">
					<input type="button" class='btn_green' style="display:none" id="btn_save" value='抵扣' onclick='toFactDeduct("${voFact.bisFactId}","${voFact.contLayOutCd}");'/>
				</security:authorize>	
			</s:if>
			
			<input type="button" class='btn_red' id="btn_red" value="取消" onclick='ymPromptClose();'/>
			</td>
		</tr>
	</table>

</form>
<script>
$(function(){
	if( typeof checkDel=='boolean'){
		if(checkDel){
			$('#btn_pass').show();
			$('#btn_reject').show();
		}else{
			$('#btn_pass').hide();
			$('#btn_reject').hide();
		}
	}
});
</script>
