<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/global.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="java.util.Date"%>
<%--<script type="text/javascript" src="${ctx}/resources/js/common/ConverUtil.js"></script>--%>
<script type="text/javascript">
initFactMust();
function initFactMust(){
	AllFactMust = new Array();
	<s:iterator value="voPage.result" status="st">
		 var obj = "<s:property value='#st.count'/>";
		 id = obj;
		AllFactMust.push(new FactMust(obj,"${bisTenantId}","${storeNo}","${bisContId}",
				"${bisProjectId}","${chargeTypeCd}","${chargeTypeCdName}","${mustMoney}","${factYear}","${factMonth}","${connName}","${factDate}","${bisShopId}"));
	</s:iterator>
}

</script>
<div id="tableDiv">
	<table class="content_table" id="editTable"  style="width: expression(this.parentNode.offsetHeight >this.parentNode.scrollHeight ? '100%' :parseInt(this.parentNode.offsetWidth - 18) + 'px');">
			<tr class="header">
				<th style="width:20px;"><input type="checkbox" onclick="checkedAll($(this).attr('checked'));" style="cursor:pointer;" title="全选/不选"/> </th>
				<th nowrap="nowrap" colspan="2" align="left" style="cursor: pointer;width:20%;" >
					楼层	   商铺编号
				</th>
				<th nowrap="nowrap" align="left" style="width:20%;">
					商家名称
				</th>
				<th nowrap="nowrap" align="left" style="width:10%;">
					费用类别
				</th>				
				<th nowrap="nowrap" align="left" class="pd-chill-tip" style="width:5%;">年份</th>
				<th nowrap="nowrap" align="left" style="width:5%;">月份</th>
				<th align="right" nowrap="nowrap" align="left" style="width:15%;">应收(元)</th>
				<th align="right" nowrap="nowrap" align="left" style="width:15%;">实收(元)</th>
				<th nowrap="nowrap" align="left" style="width:10%;">应收日期</th>
			</tr>
			<s:iterator value="voPage.result" status="st">
				<s:if test="chargeTypeCd==000">
					<tr class="mainTr" id="main_<s:property value="#st.index"/>">
						<td colspan="4"></td>
						<td nowrap="nowrap" class="pd-chill-tip" title=""	 onclick="datagridOnClick('${bisFactId}');"><div class="ellipsisDiv_full" >${chargeTypeCdName }</div></td>
						<td colspan="2"></td>
						<td align="right" colspan="1" nowrap="nowrap" class="pd-chill-tip" title="${ mustMoney}" onclick="datagridOnClick('${bisFactId}');">
							<div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if><s:if test='overdue==2'>style="color:green"</s:if>>${mustMoney }</font></div></td>
						
						<td align="right" colspan="1" nowrap="nowrap" class="pd-chill-tip" title="${money}" onclick="datagridOnClick('${bisFactId}');">
							<div class="ellipsisDiv_full" ><font <s:if test='overdue==1'>style="color:red"</s:if><s:if test='overdue==2'>style="color:green"</s:if>>${money }</font></div></td>
						<td colspan="2"></td>
				</tr>
				</s:if>
				<s:if test="chargeTypeCd!=000">
					<tr class="mainTr" id="main_<s:property value="#st.index"/>">
						<td id="chk_<s:property value="#st.count"/>" nowrap="nowrap" onclick='scheClick("${bisFactId}");' align="center">
						<input type="hidden" name="bisTenantId" value="${bisTenantId }" class="property"/>
						<input type="hidden" name="bisTenantId" value="${storeNo }" class="property"/>
						<input type="hidden" name="bisTenantId" value="${bisShopId}" class="property"/>
						<input type="hidden" name="bisTenantId" value="${connName }" class="property"/>
						<input type="hidden" name="bisTenantId" value="${bisProjectId }" class="property"/>
						<input type="hidden" name="bisTenantId" value="${chargeTypeCd }" class="property"/>
						<input type="hidden" name="bisTenantId" value="${chargeTypeCdName }" class="property"/>
						<input type="hidden" name="bisTenantId" value="${factYear }" class="property"/>
						<input type="hidden" name="bisTenantId" value="${factMonth }" class="property"/>
						<input type="hidden" name="bisTenantId" value="${mustMoney }" class="property"/>
						<input type="hidden" name="bisTenantId" value="${bisTenantId }" class="property"/>
						<input type="hidden" name="bisTenantId" value="${factDate }" class="property"/>
						<input type="hidden" name="bisTenantId" value="${bisContId }" class="property"/>
							<div style="display:inline;">
								<input type="checkbox" id="chk_all" statusCd="${statusCd }" ids="<s:property value="#st.count"/>" onClick="CAN_scheClick=false;" recordVersion="${recordVersion}"></input>
							</div>
						</td>
						<td nowrap="nowrap" class="pd-chill-tip" colspan="2" title="${storeNo }"  onclick="appendBisFact1('${bisProjectId}','1','${bisTenantId }','${connName }:${storeNo }','${ chargeTypeCd}','${ factYear}','${ factMonth}','${mustMoney}','${money}','${factDate }','${ connName}','${storeNo }','${bisContId }')"><div class="ellipsisDiv_full" > ${storeNo} </div></td>
						<td nowrap="nowrap" class="pd-chill-tip" title="${connName }"><div class="ellipsisDiv_full"  onclick="appendBisFact1('${bisProjectId}','1','${bisTenantId }','${connName }:${storeNo }','${ chargeTypeCd}','${ factYear}','${ factMonth}','${mustMoney}','${money}','${factDate }','${ connName}','${storeNo }','${bisContId }')">${connName }</div></td>
						<td nowrap="nowrap" class="pd-chill-tip" title="" onclick="appendBisFact1('${bisProjectId}','1','${bisTenantId }','${connName }:${storeNo }','${ chargeTypeCd}','${ factYear}','${ factMonth}','${mustMoney}','${money}','${factDate }','${ connName}','${storeNo }','${bisContId }')"><div class="ellipsisDiv_full" >${chargeTypeCdName }</div></td>
						<td nowrap="nowrap" class="pd-chill-tip" title="" onclick="appendBisFact1('${bisProjectId}','1','${bisTenantId }','${connName }:${storeNo }','${ chargeTypeCd}','${ factYear}','${ factMonth}','${mustMoney}','${money}','${factDate }','${ connName}','${storeNo }','${bisContId }')"><div class="ellipsisDiv_full" >${factYear }</div></td>
						<td nowrap="nowrap" class="pd-chill-tip" title="" onclick="appendBisFact1('${bisProjectId}','1','${bisTenantId }','${connName }:${storeNo }','${ chargeTypeCd}','${ factYear}','${ factMonth}','${mustMoney}','${money}','${factDate }','${ connName}','${storeNo }','${bisContId }')"><div class="ellipsisDiv_full" >${factMonth }</div></td>
						<td align="right" wrap="nowrap" class="pd-chill-tip" title="${mustMoney }" onclick="appendBisFact1('${bisProjectId}','1','${bisTenantId }','${connName }:${storeNo }','${ chargeTypeCd}','${ factYear}','${ factMonth}','${mustMoney}','${money}','${factDate }','${ connName}','${storeNo }','${bisContId }')" ><div class="ellipsisDiv_full" >
						<span <s:if test='overdue==1'>class="color_red"</s:if><s:if test='overdue==2'>class="color_blue"</s:if>><s:if test="null==mustMoney"></s:if><s:else>${mustMoney}</s:else></span>
						</div></td>
						<td align="right" nowrap="nowrap" class="pd-chill-tip" title="${money }" onclick="appendBisFact1('${bisProjectId}','1','${bisTenantId }','${connName }:${storeNo }','${ chargeTypeCd}','${ factYear}','${ factMonth}','${mustMoney}','${money}','${factDate }','${ connName}','${storeNo }','${bisContId }')"><div class="ellipsisDiv_full" >
						<span <s:if test='overdue==1'>class="color_red"</s:if><s:if test='overdue==2'>class="color_blue"</s:if>>${money }</span></div></td>
						<td nowrap="nowrap" class="pd-chill-tip" title="" onclick="appendBisFact1('${bisProjectId}','1','${bisTenantId }','${connName }:${storeNo }','${ chargeTypeCd}','${ factYear}','${ factMonth}','${mustMoney}','${money}','${factDate }','${ connName}','${storeNo }','${bisContId }')"><div class="ellipsisDiv_full" >${factDate }</div></td>
						
					
					</tr>
				</s:if>
			</s:iterator>
	</table>
	<div class="table_pager" style="margin-top:5px;">
		<%@ include file="./bis-fact-page.jsp"%>
	</div>
</div>
	<%-- 
	<div id="footerDiv" class="bottom_bar">
	 
		<div id="operate_all_div">
		  
			<div class="btn_bottom_chk_all"><div style="padding-top:5px;"><input type="checkbox" onclick="checkedAll($(this).attr('checked'));" style="cursor:pointer;" title="全选/不选"/></div></div>
			
				<div class="btn_bottom_bar" onclick="doInsertBatch(1);" style="width:85px;"><div class="pd-chill-tip" title="默认应收数额为实收金额，当前系统日期为实收日期" >自动生成实收</div></div>
				
				<div class="btn_cutline_3_26"></div>
				<div class="btn_cutline_3_26"></div>
				<security:authorize ifAnyGranted="A_BIS_PROJ_VI_FINA,A_BIS_PROJ_FINA">
					<div class="btn_cutline_3_26">11111</div>
				</security:authorize> 
		</div>
--%>
<div id="td_page" style="float:right; text-align: center; height:26px; margin-right:10px;"></div>
