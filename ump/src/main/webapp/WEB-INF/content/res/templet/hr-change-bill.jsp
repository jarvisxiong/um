<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

		<%@ include file="template-header.jsp"%>
	<!--人事变动表-->
	
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="80"/>
					<col/>
					<col width="80"/>
					<col/>
					<col width="80"/>
					<col/>
					<tr>
						<td class="td_title">中心</td>
						<td colspan="5">
						<s:if test="resApproveInfoId==null">
						<s:set var="centerName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </s:set>
						<s:set var="centerCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></s:set>
						</s:if>
						<s:else>
						<s:set var="centerName">${templateBean.centerName}</s:set>
						<s:set var="centerCd">${templateBean.centerCd}</s:set>
						</s:else>
						<input validate="required" type="text" name="templateBean.centerName" value="${centerName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
						<input type="hidden" id="centerCd" name="templateBean.centerCd" value="${centerCd}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">姓名</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}"  />
						</td>
						<td class="td_title">部门</td>
						<td colspan="3">
						<input class="inputBorder" validate="required" type="text" name="templateBean.deptName" value="${templateBean.deptName}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">职位</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.positionName" value="${templateBean.positionName}"  />
						</td>
						<td class="td_title">入职日期</td>
						<td>
						<input onfocus="WdatePicker()" class="inputBorder Wdate" type="text" validate="required" name="templateBean.enterDate" value="${templateBean.enterDate}"  />
						</td>
						<td class="td_title">生效日期</td>
						<td>
						<input onfocus="WdatePicker()" class="inputBorder Wdate" type="text" validate="required" name="templateBean.effectDate" value="${templateBean.effectDate}"  />
						</td>
					</tr>
					<tr>
					<td class="td_title">用工类别</td>
					<td colspan="5" class="chkGroup" align="left" validate="required">
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.userKind1" cssClass="group"></s:checkbox>正式工</td>
					<td><s:checkbox name="templateBean.userKind2" cssClass="group"></s:checkbox>临时工</td>
					<td><s:checkbox name="templateBean.userKind3" cssClass="group"></s:checkbox>实习生</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.userKind4" cssClass="group"></s:checkbox>外聘</td>
					<td><s:checkbox name="templateBean.userKind5" cssClass="group"></s:checkbox>外派</td>
					<td><s:checkbox name="templateBean.userKind6" cssClass="group"></s:checkbox>外籍</td>
					</tr>
					</table>
					</td>
					</tr>
					<tr>
					<td class="td_title">调整项目</td>
					<td colspan="5" class="chkGroup"align="left">
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<col width="100">
					<col width="200">
					<tr>
					<td><s:checkbox name="templateBean.adjustItem4" cssClass="group"></s:checkbox>降职</td>
					<td><s:checkbox name="templateBean.adjustItem2" cssClass="group"></s:checkbox>通过试用期</td>
					<td><s:checkbox name="templateBean.adjustItem3" cssClass="group"></s:checkbox>升职</td>
					<td></td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.adjustItem5" cssClass="group"></s:checkbox>内部调动</td>
					<td><s:checkbox name="templateBean.adjustItem6" cssClass="group"></s:checkbox>工资变动</td>
					<td><s:checkbox name="templateBean.adjustItem8" cssClass="group"></s:checkbox>临时借调</td>
					<td><s:checkbox name="templateBean.adjustItem7" cssClass="group"></s:checkbox>其他
					<input class="inputBorder" type="text" name="templateBean.adjustOther" value="${templateBean.adjustOther}" style="width:100px;"/>
					</td>
					</tr>
					</table>
					</td>
					</tr>
				</table>
				<table  class="mainTable" cellpadding="0" cellspacing="0" style="border-top: none; ">
					<col width="100px" />	
					<col/>	
					<col/>	
					<tr>
						<td style="border-top: none;text-align: center;font-weight: bold;">调整内容</td>
						<td style="border-top: none;text-align: center;font-weight: bold;">调整前</td>
						<td style="border-top: none;text-align: center;font-weight: bold;">调整后</td>
					</tr>
					<tr>
					<td class="td_title">中心/公司</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.centerBefore" value="${templateBean.centerBefore}"/></td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.centerAfter" value="${templateBean.centerAfter}"/></td>
					</tr>
					<tr>
					<td class="td_title">部门</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.deptBefore" value="${templateBean.deptBefore}"/></td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.deptAfter" value="${templateBean.deptAfter}"/></td>
					</tr>
					<tr>
					<td class="td_title">职位</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.positionBefore" value="${templateBean.positionBefore}"/></td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.positionAfter" value="${templateBean.positionAfter}"/></td>
					</tr>
					<tr>
					<td class="td_title">职级</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.levelBefore" value="${templateBean.levelBefore}"/></td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.levelAfter" value="${templateBean.levelAfter}"/></td>
					</tr>
					<tr>
					<td class="td_title">工资</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.salaryBefore" value="${templateBean.salaryBefore}"/></td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.salaryAfter" value="${templateBean.salaryAfter}"/></td>
					</tr>
					<tr>
					<td class="td_title">外派补贴(元)</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.foreSubsidyBefore" onblur="formatVal($(this));" value="${templateBean.foreSubsidyBefore}"/></td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.foreSubsidyAfter" onblur="formatVal($(this));" value="${templateBean.foreSubsidyAfter}"/></td>
					</tr>
					<tr>
					<td class="td_title">住房补贴(元)</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.houseSubsidyBefore" onblur="formatVal($(this));" value="${templateBean.houseSubsidyBefore}"/></td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.houseSubsidyAfter" onblur="formatVal($(this));" value="${templateBean.houseSubsidyAfter}"/></td>
					</tr>
					<tr>
					<td class="td_title">交通补贴(元)</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.trafficSubsidyBefore" onblur="formatVal($(this));" value="${templateBean.trafficSubsidyBefore}"/></td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.trafficSubsidyAfter" onblur="formatVal($(this));" value="${templateBean.trafficSubsidyAfter}"/></td>
					</tr>
					<tr>
					<td class="td_title">通讯补贴(元)</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.commuSubsidyBefore" onblur="formatVal($(this));" value="${templateBean.commuSubsidyBefore}"/></td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.commuSubsidyAfter" onblur="formatVal($(this));" value="${templateBean.commuSubsidyAfter}"/></td>
					</tr>
					<tr>
					<td class="td_title">特殊补贴(元)</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.specialSubsidyBefore" onblur="formatVal($(this));" value="${templateBean.specialSubsidyBefore}"/></td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.specialSubsidyAfter" onblur="formatVal($(this));" value="${templateBean.specialSubsidyAfter}"/></td>
					</tr>
					<tr>
					<td class="td_title">其他</td>
					<td><input class="inputBorder" type="text" name="templateBean.otherBefore" value="${templateBean.otherBefore}"/></td>
					<td><input class="inputBorder" type="text" name="templateBean.otherAfter" value="${templateBean.otherAfter}"/></td>
					</tr>
					<tr>
					<td class="td_title">备注</td>
					<td colspan="2">
					<textarea class="inputBorder contentTextArea" name="templateBean.remark">${templateBean.remark}</textarea>
					</td></tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
