<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<!--战略采购订单审批表-->
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>

<c:set var="isSy">false</c:set>
<c:if test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
<c:set var="isSy">true</c:set>
</c:if>

<style>
.contTable {
	width: 100%;
	height: auto;
	white-space: nowrap;
}
.contTable .thead_title {
	text-align: center;
	padding: 0px;
}
</style>

<%-- <script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script> --%>

<div align="center" class="billContent">

	
	<form action="res-approve-info!save.action" method="post" id="templetForm" >
		
		<!-- 出差补贴记录数 -->
		<s:set var="onBisItemCount"><s:property value="templateBean.onBusiness.size()"/></s:set>
		<!-- 住宿费记录数 -->
		<s:set var="stopAtItemCount"><s:property value="templateBean.stopAt.size()"/></s:set>
		<!-- 差旅费记录数 -->
		<s:set var="travelItemCount"><s:property value="templateBean.travel.size()"/></s:set>
		<!-- 其它费用记录数 -->
		<s:set var="otherItemCount"><s:property value="templateBean.other.size()"/></s:set>
		
		<input type="hidden" id="companyCdMark" name="templateBean.companyCd" value="${templateBean.companyCd}"/>
		
		<%@ include file="template-var.jsp"%>
		
		<table id="trItem" class="mainTable">
			<col width="100"/>
			<col width="30%"/>
			<col width="7%"/>
			<col width="12%"/>
			<col width="16%"/>
			<col width="7%"/>
			<col/>
			<tr>
				<td class="td_title">发起公司</td>
				<td>				
				<input validate="required"
						type="text"
						name="templateBean.companyName"
						id="companyName" 
						class="inputBorderNoTip" 
						readonly="readonly" 
						<s:if test="#isMy==1">
						onclick="initSelectFlag($(this));"
						style="cursor:pointer;"
						</s:if>
						/>
				<input type="hidden" name="templateBean.companyCd" id="companyCd" class="inputBorderNoTip"/>
				</td>
				<td class="chkGroup" align="left"><s:checkbox id="haierFlg" name="templateBean.isUrgency" cssClass="group" onclick=""></s:checkbox>急</td>
				<td class="td_title">总金额(元)</td>
				<td>
					<input validate="required" class="inputBorder"  type="text" name="templateBean.totalAmount" id="totalAmount" onchange="isNumber(this);" readonly="readonly"/>
				</td>
				<td class="td_title">职级</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.positionGrade"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">出差补贴<input class="btn_blue_75_55" type="button" onclick="addOnBisItem();" value="增加条款"></td>
				<td colspan="6" style="padding:5px;" clearBorder="true">
					<table id="on_bis_table" class="contTable" cellpadding="0px" cellspacing="0px">
						<col width="18%"/>
						<col width="18%"/>
						<col width="9%"/>
						<col/>
						<col width="16%"/>
						<col width="16%"/>
						<col width="30"/>
						<thead>
							<tr>
								<td class="thead_title">
									开始日期
								</td>
								<td class="thead_title">
									结束日期
								</td>
								<td class="thead_title">
									共(天)
								</td>
								<td class="thead_title">
									出差地点
								</td>
								<td class="thead_title">
									日补助(元)
								</td>
								<td class="thead_title">
									共计(元)
								</td>
								<td class="thead_title">操作</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input validate="required" class="inputBorder Wdate"  type="text" name="templateBean.onBusiness[0].startDate" onclick="WdatePicker();" onblur="calcDate(this);" calcType="startDate"/>
								</td>
								<td>
									<input validate="required" class="inputBorder Wdate"  type="text" name="templateBean.onBusiness[0].endDate" onclick="WdatePicker();" onblur="calcDate(this);" calcType="endDate"/>
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.onBusiness[0].sumDays" onchange="isNumber(this),calcSubsidy(this),calcTotal();" calcType="sumDays" />
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.onBusiness[0].place" />
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.onBusiness[0].daySubsidy" onchange="isNumber(this),calcSubsidy(this),calcTotal();" calcType="daySubsidy" />
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.onBusiness[0].sumSubsidy" onchange="isNumber(this),calcTotal();" calcType="sumSubsidy" readonly="readonly"/>
								</td>
								<td>
								<a onclick="delOnBisItem(this);" href="javascript:void(0)">
									<img border="0" title="删除" src="${ctx}/resources/images/common/del_22_22.gif">
								</a>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">住宿费<input class="btn_blue_75_55" type="button" onclick="addStopAtItem();" value="增加条款"></td>
				<td colspan="6" style="padding:5px;" clearBorder="true">
					<table id="stop_at_table" class="contTable" cellpadding="0px" cellspacing="0px">
						<col width="18%"/>
						<col width="18%"/>
						<col width="9%"/>
						<col/>
						<col width="16%"/>
						<col width="16%"/>
						<col width="30"/>
						<thead>
							<tr>
								<td class="thead_title">
									开始日期
								</td>
								<td class="thead_title">
									结束日期
								</td>
								<td class="thead_title">
									共(天)
								</td>
								<td class="thead_title">
									住宿地点
								</td>
								<td class="thead_title">
									平均每日(元)
								</td>
								<td class="thead_title">
									共计(元)
								</td>
								<td class="thead_title">操作</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input validate="required" class="inputBorder Wdate"  type="text" name="templateBean.stopAt[0].startDate" onclick="WdatePicker();" onblur="calcDate(this);" calcType="startDate"/>
								</td>
								<td>
									<input validate="required" class="inputBorder Wdate"  type="text" name="templateBean.stopAt[0].endDate" onclick="WdatePicker();" onblur="calcDate(this);" calcType="endDate"/>
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.stopAt[0].sumDays" onchange="isNumber(this),calcStopAtCost(this);" calcType="sumDays"/>
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.stopAt[0].place"/>
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.stopAt[0].dayMoney" onchange="isNumber(this);" readonly="readonly"/>
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.stopAt[0].sumMoney" onchange="isNumber(this),calcStopAtCost(this),calcTotal();" calcType="sumMoney"/>
								</td>
								<td rowspan="2">
								<a onclick="delStopAtItem(this);" href="javascript:void(0)">
									<img border="0" title="删除" src="${ctx}/resources/images/common/del_22_22.gif">
								</a>
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<%-- 住宿费 --%>
									<r:fileUpload canEdit="${canEdit}" bizEntityId="${resApproveInfoId==null?entityTmpId:resApproveInfoId}" fileId="stopAtAttach" index="0" subProp="stopAt" title="附件"/>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">差旅费<input class="btn_blue_75_55" type="button" onclick="addTravelItem();" value="增加条款"></td>
				<td colspan="6" style="padding:5px;" clearBorder="true">
					<table id="travel_table" class="contTable" cellpadding="0px" cellspacing="0px">
						<col width="25%"/>
						<col width="25%"/>
						<col/>
						<col width="25%"/>
						<col width="30"/>
						<thead>
							<tr>
								<td class="thead_title">
									从
								</td>
								<td class="thead_title">
									到
								</td>
								<td class="thead_title">
									交通方式
								</td>
								<td class="thead_title">费用</td>
								<td class="thead_title">操作</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.travel[0].travelFrom" />
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.travel[0].travelTo" />
								</td>
								<td>
									<s:select cssClass="inputBorder" validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapTravelWay()" listKey="key" listValue="value" name="templateBean.travel[0].trafficWay" ></s:select>
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.travel[0].costAmount" onchange="isNumber(this),calcTotal();" calcType="travelCostAmount" />
								</td>
								<td rowspan="2">
								<a onclick="delTravelItem(this);" href="javascript:void(0)">
									<img border="0" title="删除" src="${ctx}/resources/images/common/del_22_22.gif">
								</a>
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<%-- 差旅费 --%>
									<r:fileUpload canEdit="${canEdit}" bizEntityId="${resApproveInfoId==null?entityTmpId:resApproveInfoId}" fileId="travelAttach" index="0" subProp="travel" title="附件"/>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">其它费用<input class="btn_blue_75_55" type="button" onclick="addOtherItem();" value="增加条款"></td>
				<td colspan="6" style="padding:5px;" clearBorder="true">
					<table id="other_table" class="contTable" cellpadding="0px" cellspacing="0px">
						<col/>
						<col width="25%"/>
						<col width="30"/>
						<thead>
							<tr>
								<td class="thead_title">
									费用说明
								</td>
								<td class="thead_title">
									费用金额
								</td>
								<td class="thead_title">
									操作
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<textarea class="inputBorder contentTextArea" name="templateBean.other[0].costDirection" style="height: 60px;"></textarea>
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.other[0].costAmount" onchange="isNumber(this),calcTotal();" calcType="otherCostAmount" />
								</td>
								<td rowspan="2">
								<a onclick="delOtherItem(this);" href="javascript:void(0)">
									<img border="0" title="删除" src="${ctx}/resources/images/common/del_22_22.gif">
								</a>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<%-- 其它费用 --%>
									<r:fileUpload canEdit="${canEdit}" bizEntityId="${resApproveInfoId==null?entityTmpId:resApproveInfoId}" fileId="otherAttach" index="0" subProp="other" title="附件"/>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
		
		<div id="new_panel" style="display: none;"></div>
		
		<div id="list_panel" style="display: none;">
		<table class="mainTable">
			<col width="100"/>
			<col width="30%"/>
			<col width="7%"/>
			<col width="12%"/>
			<col width="16%"/>
			<col width="7%"/>
			<col/>
			<tr>
				<td class="td_title">发起公司</td>
				<td>
				<input validate="required"
						type="text"
						name="templateBean.companyName" 
						value="${templateBean.companyName}" 
						id="companyName" 
						class="inputBorderNoTip" 
						readonly="readonly" 
						<s:if test="#isMy==1">
						onclick="initSelectFlag($(this));"
						style="cursor:pointer;"
						</s:if>
						/>
				<input type="hidden" name="templateBean.companyCd" value="${templateBean.companyCd}" id="companyCd" class="inputBorderNoTip"/>
				</td>
				<td class="chkGroup" align="left"><s:checkbox id="haierFlg" name="templateBean.isUrgency" cssClass="group" onclick=""></s:checkbox>急</td>
				<td class="td_title">总金额(元)</td>
				<td>
					<input validate="required" class="inputBorder"  type="text" name="templateBean.totalAmount" value="${templateBean.totalAmount}" id="totalAmount" onchange="isNumber(this);" readonly="readonly"/>
				</td>
				<td class="td_title">职级</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.positionGrade" value="${templateBean.positionGrade}" />
				</td>
			</tr>
			<s:if test="templateBean.onBusiness.size()>0">
			<tr>
				<td class="td_title">出差补贴
				<s:if test="statusCd==0 || statusCd==3">
					<input class="btn_blue_75_55" type="button" onclick="addOnBisItem();" value="增加条款">
				</s:if>
				</td>
				<td colspan="6" style="padding:5px;" clearBorder="true">
					<table id="on_bis_table" class="contTable" cellpadding="0px" cellspacing="0px">
						<col width="18%"/>
						<col width="18%"/>
						<col width="9%"/>
						<col/>
						<col width="16%"/>
						<col width="16%"/>
						<col width="30"/>
						<thead>
							<tr>
								<td class="thead_title">
									开始日期
								</td>
								<td class="thead_title">
									结束日期
								</td>
								<td class="thead_title">
									共(天)
								</td>
								<td class="thead_title">
									出差地点
								</td>
								<td class="thead_title">
									日补助(元)
								</td>
								<td class="thead_title">
									共计(元)
								</td>
								<td class="thead_title">操作</td>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="templateBean.onBusiness" var="onBis" status="st">
							<tr>
								<td>
									<input validate="required" class="inputBorder Wdate"  type="text" name="templateBean.onBusiness[<s:property value='#st.index'/>].startDate" value="${onBis.startDate}" onclick="WdatePicker();" onblur="calcDate(this);" calcType="startDate"/>
								</td>
								<td>
									<input validate="required" class="inputBorder Wdate"  type="text" name="templateBean.onBusiness[<s:property value='#st.index'/>].endDate" value="${onBis.endDate}" onclick="WdatePicker();" onblur="calcDate(this);" calcType="endDate"/>
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.onBusiness[<s:property value='#st.index'/>].sumDays" value="${onBis.sumDays}" onchange="isNumber(this),calcSubsidy(this),calcTotal();" calcType="sumDays" />
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.onBusiness[<s:property value='#st.index'/>].place" value="${onBis.place}" />
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.onBusiness[<s:property value='#st.index'/>].daySubsidy" value="${onBis.daySubsidy}" onchange="isNumber(this),calcSubsidy(this),calcTotal();" calcType="daySubsidy" />
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.onBusiness[<s:property value='#st.index'/>].sumSubsidy" value="${onBis.sumSubsidy}" onchange="isNumber(this),calcTotal();" calcType="sumSubsidy" readonly="readonly"/>
								</td>
								<td>
									<s:if test="#isMy==1">
									<a onclick="delOnBisItem(this);" href="javascript:void(0)">
										<img border="0" title="删除" src="${ctx}/resources/images/common/del_22_22.gif">
									</a>
									</s:if>
								</td>
							</tr>
							</s:iterator>
						</tbody>
					</table>
				</td>
			</tr>
			</s:if>
			<s:if test="templateBean.stopAt.size()>0">
			<tr>
				<td class="td_title">住宿费
				<s:if test="statusCd==0 || statusCd==3">
					<input class="btn_blue_75_55" type="button" onclick="addStopAtItem();" value="增加条款">
				</s:if>
				</td>
				<td colspan="6" style="padding:5px;" clearBorder="true">
					<table id="stop_at_table" class="contTable" cellpadding="0px" cellspacing="0px">
						<col width="18%"/>
						<col width="18%"/>
						<col width="9%"/>
						<col/>
						<col width="16%"/>
						<col width="16%"/>
						<col width="30"/>
						<thead>
							<tr>
								<td class="thead_title">
									开始日期
								</td>
								<td class="thead_title">
									结束日期
								</td>
								<td class="thead_title">
									共(天)
								</td>
								<td class="thead_title">
									住宿地点
								</td>
								<td class="thead_title">
									平均每日(元)
								</td>
								<td class="thead_title">
									共计(元)
								</td>
								<td class="thead_title">操作</td>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="templateBean.stopAt" var="stopAt" status="st">
							<tr>
								<td>
									<input validate="required" class="inputBorder Wdate"  type="text" name="templateBean.stopAt[<s:property value='#st.index'/>].startDate" value="${stopAt.startDate}" onclick="WdatePicker();" onblur="calcDate(this);" calcType="startDate"/>
								</td>
								<td>
									<input validate="required" class="inputBorder Wdate"  type="text" name="templateBean.stopAt[<s:property value='#st.index'/>].endDate" value="${stopAt.endDate}" onclick="WdatePicker();" onblur="calcDate(this);" calcType="endDate"/>
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.stopAt[<s:property value='#st.index'/>].sumDays" value="${stopAt.sumDays}" onchange="isNumber(this),calcStopAtCost(this);" calcType="sumDays"/>
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.stopAt[<s:property value='#st.index'/>].place" value="${stopAt.place}" />
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.stopAt[<s:property value='#st.index'/>].dayMoney" value="${stopAt.dayMoney}" onchange="isNumber(this);" readonly="readonly"/>
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.stopAt[<s:property value='#st.index'/>].sumMoney" value="${stopAt.sumMoney}" onchange="isNumber(this),calcStopAtCost(this),calcTotal();" calcType="sumMoney"/>
								</td>
								<td rowspan="2">
									<s:if test="#isMy==1">
									<a onclick="delStopAtItem(this);" href="javascript:void(0)">
										<img border="0" title="删除" src="${ctx}/resources/images/common/del_22_22.gif">
									</a>
									</s:if>
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<%-- 住宿费 --%>
									<r:fileUpload canEdit="${canEdit}" bizEntityId="${resApproveInfoId==null?entityTmpId:resApproveInfoId}" fileId="stopAtAttach" index="${st.index}" subProp="stopAt" title="附件" fileValue="${stopAt.stopAtAttach}"/>
								</td>
							</tr>
							</s:iterator>
						</tbody>
					</table>
				</td>
			</tr>
			</s:if>
			<s:if test="templateBean.travel.size()>0">
			<tr>
				<td class="td_title">差旅费
				<s:if test="statusCd==0 || statusCd==3">
					<input class="btn_blue_75_55" type="button" onclick="addTravelItem();" value="增加条款">
				</s:if>
				</td>
				<td colspan="6" style="padding:5px;" clearBorder="true">
					<table id="travel_table" class="contTable" cellpadding="0px" cellspacing="0px">
						<col width="25%"/>
						<col width="25%"/>
						<col/>
						<col width="25%"/>
						<col width="30"/>
						<thead>
							<tr>
								<td class="thead_title">
									从
								</td>
								<td class="thead_title">
									到
								</td>
								<td class="thead_title">
									交通方式
								</td>
								<td class="thead_title">费用</td>
								<td class="thead_title">操作</td>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="templateBean.travel" var="travel" status="st">
							<tr>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.travel[<s:property value='#st.index'/>].travelFrom" value="${travel.travelFrom}" />
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.travel[<s:property value='#st.index'/>].travelTo" value="${travel.travelTo}" />
								</td>
								<td>
									<s:if test="#isMy!=1">
<%-- 										<s:set var="trafficWay"><s:property value="templateBean.travel[%{#st.index}].trafficWay"/></s:set> --%>
										<input class="inputBorderNoTip" id="templateBean_trafficWay" type="text" value="<%=CodeNameUtil.getDictNameByCd(DictContants.JBPM_TRAVEL_WAY,JspUtil.findString("trafficWay")) %>"  />
									</s:if>
									<s:else>
										<s:select cssClass="inputBorder" validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapTravelWay()" listKey="key" listValue="value" name="templateBean.travel[%{#st.index}].trafficWay" ></s:select>
									</s:else>
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.travel[<s:property value='#st.index'/>].costAmount" value="${travel.costAmount}" onchange="isNumber(this),calcTotal();" calcType="travelCostAmount" />
								</td>
								<td rowspan="2">
									<s:if test="#isMy==1">
									<a onclick="delTravelItem(this);" href="javascript:void(0)">
										<img border="0" title="删除" src="${ctx}/resources/images/common/del_22_22.gif">
									</a>
									</s:if>
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<%-- 差旅费 --%>
									<r:fileUpload canEdit="${canEdit}" bizEntityId="${resApproveInfoId==null?entityTmpId:resApproveInfoId}" fileId="travelAttach" index="${st.index}" subProp="travel" title="附件" fileValue="${travel.travelAttach}"/>
								</td>
							</tr>
							</s:iterator>
						</tbody>
					</table>
				</td>
			</tr>
			</s:if>
			<s:if test="templateBean.other.size()>0">
			<tr>
				<td class="td_title">其它费用
				<s:if test="statusCd==0 || statusCd==3">
					<input class="btn_blue_75_55" type="button" onclick="addOtherItem();" value="增加条款">
				</s:if>
				</td>
				<td colspan="6" style="padding:5px;" clearBorder="true">
					<table id="other_table" class="contTable" cellpadding="0px" cellspacing="0px">
						<col/>
						<col width="150"/>
						<col width="30"/>
						<thead>
							<tr>
								<td class="thead_title">
									费用说明
								</td>
								<td class="thead_title">
									费用金额
								</td>
								<td class="thead_title">
									操作
								</td>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="templateBean.other" var="other" status="st">
							<tr>
								<td>
									<textarea class="inputBorder contentTextArea" name="templateBean.other[<s:property value='#st.index'/>].costDirection" style="height: 60px;">${other.costDirection}</textarea>
								</td>
								<td>
									<input validate="required" class="inputBorder"  type="text" name="templateBean.other[<s:property value='#st.index'/>].costAmount" value="${other.costAmount}"  onchange="isNumber(this),calcTotal();" calcType="otherCostAmount" />
								</td>
								<td rowspan="2">
									<s:if test="#isMy==1">
									<a onclick="delOtherItem(this);" href="javascript:void(0)">
										<img border="0" title="删除" src="${ctx}/resources/images/common/del_22_22.gif">
									</a>
									</s:if>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<%-- 其它费用 --%>
									<r:fileUpload canEdit="${canEdit}" bizEntityId="${resApproveInfoId==null?entityTmpId:resApproveInfoId}" fileId="otherAttach" index="${st.index}" subProp="other" title="附件" fileValue="${other.otherAttach}"/>
								</td>
							</tr>
							</s:iterator>
						</tbody>
					</table>
				</td>
			</tr>
			</s:if>
		</table>
		</div>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>

<%@ include file="template-footer.jsp"%>

<script type="text/javascript">
	//$("#partBName").quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],{sname:'partBName',sid:'partB'});
	//$("#contactNoId").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contName','contNo'],{contNo:"contactNoId",contName:"contactNameId"});
//$("#changeCrash").quickSearch("${ctx}/cont/cont-project-code!quickSearch.action",['changeCrash'],{projectCd:'projectCd',changeCrash:'changeCrash'},{codeType:$('#codeType').val()});

//合同编号唯一性校验
function contNoValidate(dom){
	if($(dom).val()){
		$.post("${ctx}/cont/cont-ledger!contNoValidate.action?contNo=" + $(dom).val(),
			function(result){
				//合同新增，不是0条记录
				if(result!="0"){
					alert("该编号已存在，请重新输入");
					$(dom).val("");
				}
	     });
	}
}
function chooseMonopoly() {
	var isProjectAuth=$("#isProjectAuth").attr("checked"); 
	var isMonopoly =$("#isMonopoly").attr("checked"); 
	if(isProjectAuth||isMonopoly){
		$("#unitName").unbind();
		$("#remark").attr("validate","required");
	}else{
		$("#unitName").val('');
		$("#unitName").quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],{sname:'unitName',sid:'sid'},'','','');
		$("#remark").attr("class","inputBorder");
		$("#remark").removeAttr("validate");
		$("#remark").removeAttr("title");
	}
}


//总的新建表格
var table = $("#trItem");
var table_new = $(table).clone();
$(table).remove();


// 记录每个条款项
function recordItems() {
	tbody_on_bis = $(table_new).find("table[id='on_bis_table']").find("tbody").html();
	tbody_stop_at = $(table_new).find("table[id='stop_at_table']").find("tbody").html();
	tbody_travel = $(table_new).find("table[id='travel_table']").find("tbody").html();
	tbody_other = $(table_new).find("table[id='other_table']").find("tbody").html();
// 	tbody_on_bis = $("#on_bis_table >tbody").html();
// 	tbody_stop_at = $("#stop_at_table >tbody").html();
// 	tbody_travel = $("#travel_table >tbody").html();
// 	tbody_other = $("#other_table >tbody").html();
}

function initIndex() {
	// 各项条款索引
	on_bis_index = stop_at_index = travel_index = other_index = 1;
	if(${onBisItemCount} > 1) {
		on_bis_index = ${onBisItemCount};
	}
	if(${stopAtItemCount} > 1) {
		stop_at_index = ${stopAtItemCount};
	}
	if(${travelItemCount} > 1) {
		travel_index = ${travelItemCount};
	}
	if(${otherItemCount} > 1) {
		other_index = ${otherItemCount};
	}
}

/***********    新增条款   start   ************/

var on_bis_index, stop_at_index, travel_index, other_index;

// 出差补贴条款项， 住宿费条款项，差旅费用条款项，其它费用条款项
var tbody_on_bis, tbody_stop_at, tbody_travel, tbody_other;

//出差补贴，增加条款
function addOnBisItem() {
	var tbody_new = $(tbody_on_bis).clone();
	assignIndex(tbody_new, on_bis_index);
	$("#on_bis_table >tbody").append(tbody_new);
	// 上传附件按钮样式调整
	adjustUploadBtnCss();
	on_bis_index++;
}
// 住宿费，增加条款
function addStopAtItem() {
	var tbody_new = $(tbody_stop_at).clone();
	assignIndex(tbody_new, stop_at_index);
	$("#stop_at_table >tbody").append(tbody_new);
	// 上传附件按钮样式调整
	adjustUploadBtnCss();
	stop_at_index++;
}
// 差旅费，增加条款
function addTravelItem() {
	var tbody_new = $(tbody_travel).clone();
	assignIndex(tbody_new, travel_index);
	$("#travel_table >tbody").append(tbody_new);
	// 上传附件按钮样式调整
	adjustUploadBtnCss();
	travel_index++;
}
// 其它费用，增加条款
function addOtherItem() {
	var tbody_new = $(tbody_other).clone();
	assignIndex(tbody_new, other_index);
	$("#other_table >tbody").append(tbody_new);
	// 上传附件按钮样式调整
	adjustUploadBtnCss();
	other_index++;
}
// 给新增条款索引赋值
function assignIndex(tbody_new, index) {
	// 修改所有输入框集合属性索引值
	$.each($(tbody_new).find("input[type='text']"), function(k, v) {
		$(this).attr("name", $(this).attr("name").replace("0", index));
	});
	// 新增条款时，附件上传按钮集合属性索引值赋值
	$.each($(tbody_new).find("input[type='button']"), function(k, v) {
		var h_id = $(tbody_new).find("input[type='hidden']").attr("id");
		var btn_html=$(this).parent().html().replace(h_id, h_id.replace("0", index));
		$(this).parent().html(btn_html);
	});
	// 新增条款时，附件上传隐藏按钮及相关元素的集合属性索引值赋值
	$.each($(tbody_new).find("input[type='hidden']"), function(k, v) {
		$(this).attr("name", $(this).attr("name").replace("0", index));
		$(this).attr("id", $(this).attr("id").replace("0", index));
		$(this).parent().next().attr("id", $(this).parent().next().attr("id").replace("0", index));
	});
	// 新增条款时，下拉选框的集合属性索引值赋值
	$.each($(tbody_new).find("select"), function(k, v) {
		$(this).attr("name", $(this).attr("name").replace("0", index));
	});
	// 新增条款时，文本域的集合属性索引值赋值
	$.each($(tbody_new).find("textarea"), function(k, v) {
		$(this).attr("name", $(this).attr("name").replace("0", index));
	});
	
}
/***********    新增条款  end   ************/

/***********    删除条款  start   ************/
// 出差补贴条款项
function delOnBisItem(obj) {
	$(obj).parent().parent().remove();
	calcTotal();
}
// 住宿费条款项
function delStopAtItem(obj) {
	$(obj).parent().parent().next().remove();
	$(obj).parent().parent().remove();
	calcTotal();
}
// 差旅费用条款项
function delTravelItem(obj) {
	$(obj).parent().parent().next().remove();
	$(obj).parent().parent().remove();
	calcTotal();
}
// 其它费用条款项
function delOtherItem(obj) {
	$(obj).parent().parent().next().remove();
	$(obj).parent().parent().remove();
	calcTotal();
}
/***********    删除条款  end   ************/


function initSelectFlag(jDom){
	if( jDom.attr('initselectflg') != '1'){
		jDom.attr('initselectflg','1'); 
		jDom.orgSelect({
			showProjectOrg:true
		});
		jDom.trigger('click');
	}
}

// 计算天数
function calcDate(obj) {
	var startDate; // 开始日期
	var endDate; // 结束日期
	var day; // 天数
	var type = $(obj).attr("calcType");
	if(type == "startDate") { // 点击开始日期时的计算
		startDate = $(obj);
		endDate = $(obj).parent().parent().next().find("input[type='text']");
		day = $(obj).parent().parent().next().next().find("input[type='text']");
		$(endDate).unbind("focus").bind("focus", function() {
			WdatePicker({minDate : $(startDate).val()});
		});
	} else if(type == "endDate") { // 点击结束日期时的计算
		startDate = $(obj).parent().parent().prev().find("input[type='text']");
		endDate = $(obj);
		day = $(obj).parent().parent().next().find("input[type='text']");
		$(startDate).unbind("focus").bind("focus", function() {
			WdatePicker({maxDate : $(endDate).val()});
		});
	}
	var result = calcDatePass($(startDate).val(), $(endDate).val()) + "";
	if(result) {
		$(day).val(result);
		// 费用类型标识（daySubsidy: (日补助)出差补贴类型，sumMoney: (共计)住宿费类型）
		var typeTag = $(endDate).parent().parent().next().next().next().find("input[type='text']").attr("calcType");
		if(typeTag == "daySubsidy") {
			calcSubsidy(day);
			calcTotal();
		} else if(typeTag == "sumMoney") {
			calcStopAtCost(day);
		}
	}
}

/***
 * 计算日期差
 * @param startDate 开始日期
 * @param endDate 结束日期
 */
function calcDatePass(startDate, endDate) {
	var day = "";
	if(startDate && endDate) {
		var msec = new Date(endDate.split("-")[0], endDate.split("-")[1], endDate.split("-")[2]).getTime() - new Date(startDate.split("-")[0], startDate.split("-")[1], startDate.split("-")[2]).getTime();
		if(msec == 0) {
			day = "1";
		} else {
			day = (msec / 1000 / 60 / 60 / 24); // 经过天数
			day = parseInt(day) + 1;
		}
	}
	return day;
}

// 计算共计补助
function calcSubsidy(obj) {
	var day; // 天数
	var daySubsidy; // 日补助(元)
	var sumSubsidy; // 共计补助(元)
	var type = $(obj).attr("calcType");
	if(type == "sumDays") { // 点击天数计算
		day = $(obj);
		daySubsidy = $(obj).parent().parent().next().next().find("input[type='text']");
		sumSubsidy = $(daySubsidy).parent().parent().next().find("input[type='text']");
	} else { // 点击日补助计算
		day = $(obj).parent().parent().prev().prev().find("input[type='text']");
		daySubsidy = $(obj);
		sumSubsidy = $(obj).parent().parent().next().find("input[type='text']");
	}
	if($(daySubsidy).val()) {
		$(daySubsidy).val(parseFloat($(daySubsidy).val()).toFixed(2));
	}
	if($(day).val() && $(daySubsidy).val()) {
		$(sumSubsidy).val((parseFloat($(day).val()) * parseFloat($(daySubsidy).val())).toFixed(2));
	}
}

// 计算每日住宿费
function calcStopAtCost(obj) {
	var day; // 天数
	var dayMoney; // 平均每日(元)
	var sumMoney; // 共计费用(元)
	var type = $(obj).attr("calcType");
	if(type == "sumDays") { // 点击天数计算
		day = $(obj);
		dayMoney = $(obj).parent().parent().next().next().find("input[type='text']");
		sumMoney = $(dayMoney).parent().parent().next().find("input[type='text']");
	} else if("sumMoney") { // 点击共计计算
		day = $(obj).parent().parent().prev().prev().prev().find("input[type='text']");
		dayMoney = $(obj).parent().parent().prev().find("input[type='text']");
		sumMoney = $(obj);
	}
	if($(day).val() && $(sumMoney).val()) {
		$(dayMoney).val((parseFloat($(sumMoney).val()) / parseFloat($(day).val())).toFixed(2));
	}
}

// 计算总金额
function calcTotal() {
	var sumSubsidy = 0; // 共计补贴
	$.each($("input[calcType='sumSubsidy']"), function() {
		if($(this).val()) {
			sumSubsidy = (parseFloat(sumSubsidy) + parseFloat($(this).val())).toFixed(2);
		}
	});
	var sumMoney = 0; // 共计住宿费
	$.each($("input[calcType='sumMoney']"), function() {
		if($(this).val()) {
			sumMoney = (parseFloat(sumMoney) + parseFloat($(this).val())).toFixed(2);
		}
	});
	var travelCostAmount = 0; // 差旅费金额
	$.each($("input[calcType='travelCostAmount']"), function() {
		if($(this).val()) {
			travelCostAmount = (parseFloat(travelCostAmount) + parseFloat($(this).val())).toFixed(2);
		}
	});
	var otherCostAmount = 0; // 其它费用金额
	$.each($("input[calcType='otherCostAmount']"), function() {
		if($(this).val()) {
			otherCostAmount = (parseFloat(otherCostAmount) + parseFloat($(this).val())).toFixed(2);
		}
	});
	
	var total = 0; // 总金额
	if(sumSubsidy) {
		total = (parseFloat(total) + parseFloat(sumSubsidy)).toFixed(2);
	}
	if(sumMoney) {
		total = (parseFloat(total) + parseFloat(sumMoney)).toFixed(2);
	}
	if(travelCostAmount) {
		total = (parseFloat(total) + parseFloat(travelCostAmount)).toFixed(2);
	}
	if(otherCostAmount) {
		total = (parseFloat(total) + parseFloat(otherCostAmount)).toFixed(2);
	}
	if(total != 0) {
		$("#totalAmount").val(parseFloat(total).toFixed(2));
	} else {
		$("#totalAmount").val("");
	}
}

/***
 * 是否是数字
 */
function isNumber(obj) {
	var r = parseFloat($(obj).val()).toFixed(2);
	if(r == "NaN") {
		alert("请输入数字！");
		$(obj).val("");
		return;
	}
	$(obj).val(r);
}

$(function() {
	
	recordItems();

	initIndex();
	
	if($("#companyCdMark").val()) {
		$("#new_panel").remove();
		$("#list_panel").show();
	} else {
		$("#new_panel").append(table_new);
		$("#new_panel").show();
		$("#list_panel").remove();
	}

	// 上传附件按钮样式调整
	adjustUploadBtnCss();
	
});

//上传附件按钮样式调整
function adjustUploadBtnCss() {
	$("div[id=div_stopAtAttach], div[id=div_travelAttach], div[id=div_otherAttach]").css("height", "26px");
	$("div[id=div_vali_stopAtAttach], div[id=div_vali_travelAttach], div[id=div_vali_otherAttach]").css({"width": "40px", "height": "26px", "margin": "0px 5px 0px 0px"});
	$("div[id=div_vali_stopAtAttach], div[id=div_vali_travelAttach], div[id=div_vali_otherAttach]").next().css({"margin": "0px 5px"});
}


</script>

<s:if test="#isMy==1">
<script type="text/javascript">
// 组织选择
// $('#companyName').orgSelect({
// 							showProjectOrg : true
// 						});
						
</script>
</s:if>
