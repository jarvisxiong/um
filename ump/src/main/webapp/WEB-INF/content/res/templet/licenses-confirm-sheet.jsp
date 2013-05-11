<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

		<%@ include file="template-header.jsp"%>
	<%-- 证照办理确认单 --%>
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="90"/>
					<col width="90"/>
					<col/>
					<col width="80"/>
					<col/>
					<tr>
						<td class="td_title" colspan="2">标题</td>
						<td colspan="3">
							<input validate="required" class="inputBorder" type="text" name="templateBean.titleName" value="${templateBean.titleName}" />
						</td>
					</tr>
					<tr>
						<td class="td_title" colspan="2">项目</td>
						<td colspan="3"  class="chkGroup" align="left" validate="required"> 
							<div><s:checkbox name="templateBean.matters1" cssClass="group"></s:checkbox>设立</div>
							<div><s:checkbox name="templateBean.matters2" cssClass="group"></s:checkbox>基本信息变更（名称、地址、法人或负责人、类型、注册资金、经营范围、其他）</div>
							<div><s:checkbox name="templateBean.matters3" cssClass="group"></s:checkbox>注销</div>
							<div><s:checkbox name="templateBean.matters4" cssClass="group"></s:checkbox>任职变更（董事长、总经理、股东、董事、监事）</div>
							<div><s:checkbox name="templateBean.matters5" cssClass="group"></s:checkbox>资质（新办、年检、升级）</div>
							<div><s:checkbox name="templateBean.matters6" cssClass="group"></s:checkbox>项目手册（新办、年检）</div>
						</td>
					</tr>
					<tr>
						<td class="td_title" colspan="2">申请内容（详细内容附后）</td>
						<td colspan="3">
							<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.applContent">${templateBean.applContent}</textarea>
						</td>
					</tr>
					<tr>
						<td rowspan="8" class="td_title">需明确的事项</td>
						<td class="td_title">公司名称</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.companyName" value="${templateBean.companyName}"  />
						</td>
						<td class="td_title">公司性质</td>
						<td class="chkGroup" align="left">
						<table class="tb_checkbox">
						<col width="65">
						<col width="65">
						<tr class="chkGroup" validate="required">
						<td><s:checkbox name="templateBean.companyNatureN" cssClass="group"></s:checkbox>内资</td>
						<td><s:checkbox name="templateBean.companyNatureW" cssClass="group"></s:checkbox>外资</td></tr>
						</table>
						</td>
					</tr>
					<tr>
						<td class="td_title">备用名称1</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.backupName1" value="${templateBean.backupName1}"  />
						</td>
						<td class="td_title">注册资本</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.registCapital" value="${templateBean.registCapital}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">备用名称2</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.backupName2" value="${templateBean.backupName2}"  />
						</td>
						<td class="td_title" >法定代表人</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.legalPerson" value="${templateBean.legalPerson}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">经营范围</td>
						<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.businessScope">${templateBean.businessScope}</textarea></td>
					</tr>
					<tr>
						<td class="td_title">股东及持股比例</td>
						<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.shareProportion">${templateBean.shareProportion}</textarea></td>
					</tr>
					<tr>
						<td class="td_title">董事会成员或董事成员</td>
						<td><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.bds">${templateBean.bds}</textarea></td>
						<td class="td_title">监事</td>
						<td>
							<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.supervisors">${templateBean.supervisors}</textarea>
						</td>
					</tr>
					<tr>
						<td class="td_title">注册地址</td>
						<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.registAddress">${templateBean.registAddress}</textarea></td>
					</tr>
					<tr>
						<td class="td_title">其他要求</td>
						<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.otherRequirements">${templateBean.otherRequirements}</textarea></td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
