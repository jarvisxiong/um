<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

		<%@ include file="template-header.jsp"%>
	<!--人事调动申请表-->
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="15%"/>
					<col width="35%"/>
					<col width="15%"/>
					<col width="35%"/>
					<tr>
						<td class="td_title">申请公司</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.applyOfficeName" value="${templateBean.applyOfficeName}"  />
						</td>
						<td class="td_title">姓名</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">原职位</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.positionBefore" value="${templateBean.positionBefore}"  />
						</td>
						<td class="td_title">调整后职位</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.positionAfter" value="${templateBean.positionAfter}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">原职级</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.levelBefore" value="${templateBean.levelBefore}"  />
						</td>
						<td class="td_title">调整后职级</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.levelAfter" value="${templateBean.levelAfter}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">原薪资(元)</td>
						<td>
						<input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));"  name="templateBean.salaryBefore" value="${templateBean.salaryBefore}"  />
						</td>
						<td class="td_title">调整后薪资(元)</td>
						<td>
						<input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));"  name="templateBean.salaryAfter" value="${templateBean.salaryAfter}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">入职日期</td>
						<td>
						<input onfocus="WdatePicker()" class="Wdate" type="text" validate="required" name="templateBean.enterDate" value="${templateBean.enterDate}"  />
						</td>
						<td class="td_title">生效日期</td>
						<td>
						<input onfocus="WdatePicker()" class="Wdate" type="text" validate="required" name="templateBean.effectDate" value="${templateBean.effectDate}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">事由</td>
						<td colspan="3">
						<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark">${templateBean.remark}</textarea>
						</td>
					</tr>
					<tr>
					<td class="td_title">集团与子公司</td>
					<td align="left" colspan="3" class="chkGroup">
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<col width="200">
					<tr>
					<td><s:checkbox name="templateBean.gUp"  cssClass="group"></s:checkbox>晋升晋级</td>
					<td><s:checkbox name="templateBean.gDown"  cssClass="group"></s:checkbox>降职降级</td>
					<td><s:checkbox name="templateBean.gFlat"  cssClass="group"></s:checkbox>岗位平调</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.gReturn"  cssClass="group"></s:checkbox>工作轮岗</td>
					<td><s:checkbox name="templateBean.gTemp"  cssClass="group"></s:checkbox>临时借调</td>
					<td><s:checkbox name="templateBean.gOther"  cssClass="group"></s:checkbox>其它
					<input class="inputBorder" type="text" name="templateBean.gOtherContent" value="${templateBean.gOtherContent}" style="width:100px;"/>
					</td>
					</tr>
					</table>
					</td>
					</tr>
					<tr>
					<td class="td_title">子公司与子公司</td>
					<td align="left" colspan="3" class="chkGroup">
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<col width="200">
					<tr>
					<td><s:checkbox name="templateBean.sUp"  cssClass="group"></s:checkbox>晋升晋级</td>
					<td><s:checkbox name="templateBean.sDown"  cssClass="group"></s:checkbox>降职降级</td>
					<td><s:checkbox name="templateBean.sFlat"  cssClass="group"></s:checkbox>岗位平调</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.sReturn"  cssClass="group"></s:checkbox>工作轮岗</td>
					<td><s:checkbox name="templateBean.sTemp"  cssClass="group"></s:checkbox>临时借调</td>
					<td><s:checkbox name="templateBean.sOther"  cssClass="group"></s:checkbox>其它
					<input class="inputBorder" type="text" name="templateBean.sOtherContent" value="${templateBean.sOtherContent}" style="width:100px;"/>
					</td>
					</tr>
					</table>
					</td>
					</tr>
					
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
