<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@ include file="template-header.jsp"%>
<%-- 出差申请计划单 --%>
		
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.DictContants"%><br />
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="100px"/>
					<col/>
					<col width="80px"/>
					<col/>
					<col width="80px"/>
					<col/>
					<tr>
						<td class="td_title">中心/公司</td>
						<td colspan="5">
							<s:if test="resApproveInfoId==null">
							<s:set var="sendCenterName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </s:set>
							<s:set var="sendCenterCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></s:set>
							</s:if>
							<s:else>
							<s:set var="sendCenterName">${templateBean.centerOrgName}</s:set>
							<s:set var="sendCenterCd">${templateBean.centerOrgCd}</s:set>
							</s:else>
							<input validate="required" type="text" name="templateBean.centerOrgName" value="${sendCenterName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
							<input type="hidden" id="centerCd" name="templateBean.centerOrgCd" value="${sendCenterCd}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">申请人</td>
						<td>
							<input id="applyUserName" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else> />
							<input id="applyUserCd" type="hidden" name="templateBean.userCd" value="${templateBean.userCd}"  />
						</td>
						<td class="td_title">部门</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.dept" value="${templateBean.dept}"  />
						</td>
						<td class="td_title">职位</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.position" value="${templateBean.position}"  />
						</td>
					</tr>
					<tr>
					<td class="td_title">开始日期</td>
						<td>
							<input class="Wdate inputBorder" validate="required" type="text" name="templateBean.tripTimeBegin" value="${templateBean.tripTimeBegin}" onfocus="WdatePicker()" />
						</td>
						<td class="td_title">结束日期</td>
						<td>
							<input class="Wdate inputBorder" validate="required" type="text" name="templateBean.tripaTimeEnd" value="${templateBean.tripaTimeEnd}"  onfocus="WdatePicker()"/>
						</td>
						<td class="td_title">出差地点</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.tripPlace" value="${templateBean.tripPlace}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">随行人员</td>
						<td colspan="3">
							<input readonly="readonly"  type="text" name="templateBean.entourageUserName" value="${templateBean.entourageUserName}" id="entourageUserName" <s:if test="#isMy==1"> class="inputBorderNoTip mutiSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else> />
							<input id="entourageUserCd" type="hidden" name="templateBean.entourageUserCd" value="${templateBean.entourageUserCd}"  />
						</td>
						<td class="td_title">交通方式</td>
						<td>
						<s:if test="#isMy!=1">
							<input class="inputBorderNoTip" type="text" value="<%=CodeNameUtil.getDictNameByCd(DictContants.JBPM_TRAVEL_WAY,JspUtil.findString("templateBean.travelWay")) %>"  />
						</s:if>
						<s:else>
							<s:select cssClass="inputBorder" validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapTravelWay()" listKey="key" listValue="value" name="templateBean.travelWay" ></s:select>
						</s:else>
						</td>
					</tr>
					<tr>
						<td class="td_title">出差事由</td>
						<td colspan="5">
							<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.tripReason">${templateBean.tripReason}</textarea>
						</td>
					</tr>
					<tr>
						<td class="td_title">预计差旅借款</td>
						<td colspan="5" style="text-align:left;">
							<input class="inputBorder" validate="required" type="text" style="width:100px" name="templateBean.lendMoneyAmt" value="${templateBean.lendMoneyAmt}"  onblur="formatVal($(this));" />元
						</td>
					</tr>
					<tr>
						<td class="td_title">行程计划安排</td>
						<td colspan="5">
							<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.tripDesc">${templateBean.tripDesc}</textarea>
						</td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
