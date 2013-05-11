<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 宝龙商业人事变动表 --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="60px"/>
			<col />
			<col width="60px"/>
			<col/>
			<col width="60px"/>
			<col />
			<tr>
			<td class="td_title">级别</td>
			<td colspan="5" class="chkGroup" align="left" validate="required">
				<table class="tb_checkbox">
					<col width="120">
					<col width="120">
					<col>
					<tr>
					<td><s:checkbox name="templateBean.positionLevel1"  cssClass="group"></s:checkbox>总经理</td>
					<td><s:checkbox name="templateBean.positionLevel2"  cssClass="group"></s:checkbox>副总经理</td>
					<td><s:checkbox name="templateBean.positionLevel3"  cssClass="group"></s:checkbox>高级经理级(除垂直管理人员外)</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.positionLevel4"  cssClass="group"></s:checkbox>垂直管理人员</td>
					<td><s:checkbox name="templateBean.positionLevel5"  cssClass="group"></s:checkbox>其他人员</td>
					<td></td>
					</tr>
				</table>
			</td>
		</tr>
			<tr>
				<td style="border-top:0 none;" class="td_title">姓名</td>
				<td style="border-top: none;">
				 <input validate="required" class="inputBorder" type="text" name="templateBean.name" value="${templateBean.name}"/>
				</td>
				<td style="border-top:0 none;" class="td_title">中心</td>
				<td style="border-top: none;">
				 <input id="centerName" validate="required" type="text" name="templateBean.centerName" value="${templateBean.centerName}" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
				 <input type="hidden" name="templateBean.centerCd" value="${templateBean.centerCd}" />
				</td>
				<td style="border-top:0 none;" class="td_title">部门</td>
				<td style="border-top: none;">
				  <input validate="required" class="inputBorder" type="text" name="templateBean.dept" value="${templateBean.dept}"/>
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">职位</td>
				<td style="border-top: none;">
				 <input validate="required" class="inputBorder" type="text" name="templateBean.position" value="${templateBean.position}"/>
				</td>
				<td style="border-top:0 none;" class="td_title">入职日期</td>
				<td style="border-top:0 none;" class="chkGroup" align="left">
				 <input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.reportDuty"  validate="required" value="${templateBean.reportDuty}" />
				</td>
				<td style="border-top:0 none;" class="td_title">生效日期</td>
				<td style="border-top: none;">
				 <input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.effectiveDate" value="${templateBean.effectiveDate}" />
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">用工类别</td>
				<td style="border-top: none;" colspan="5"  class="chkGroup">
				 <table class="tb_checkbox">
					<col width="80">
					<col width="80">
					<col width="80">
					<col width="80">
					<col width="80">
					<col/>
					<tr>
					<td><s:checkbox name="templateBean.official"  cssClass="group"></s:checkbox>正式工</td>
					<td><s:checkbox name="templateBean.temporaryWorkers"  cssClass="group"></s:checkbox>临时工</td>
					<td><s:checkbox name="templateBean.trainee"  cssClass="group"></s:checkbox>实习生</td>
					<td><s:checkbox name="templateBean.externalAuditor"  cssClass="group"></s:checkbox>外聘</td>
					<td><s:checkbox name="templateBean.outAssign"  cssClass="group"></s:checkbox>外派</td>
					<td><s:checkbox name="templateBean.foreignNationality"  cssClass="group"></s:checkbox>外籍</td>
					</tr>
				</table>
				</td>
			</tr> 
		   <tr>
			<td style="border-top:0 none;" class="td_title" colspan="6">
			 <table width="100%;">
				<col width="150">
				<col width="100">
				<col/>
				<col/>
				<tr>
				 <th>调整项目</th>
				 <th>内容</th>
				 <th>调整前</th>
				 <th>调整后</th>
				</tr>
				<tr>
				<td rowspan="11"  class="chkGroup">
				 <table class="tb_checkbox">
					<tr>
						<td><s:checkbox name="templateBean.newEmploy"  cssClass="group"></s:checkbox>新聘</td>
					</tr>
					<tr>
					 <td><s:checkbox name="templateBean.passQualifyingPeriod"  cssClass="group"></s:checkbox>通过试用期</td>
					</tr>
					<tr>
					 <td><s:checkbox name="templateBean.promotion"  cssClass="group"></s:checkbox>升职</td>
					</tr>
					<tr>
					 <td><s:checkbox name="templateBean.demotion"  cssClass="group"></s:checkbox>降职</td>
					</tr>
					<tr>
					 <td><s:checkbox name="templateBean.insideRemove"  cssClass="group"></s:checkbox>内部调动</td>
					</tr>
					<tr>
						<td><s:checkbox name="templateBean.salaryChange"  cssClass="group"></s:checkbox>工资变动</td>
					</tr>
					<tr>
						<td><s:checkbox name="templateBean.otherChange"  cssClass="group"></s:checkbox>其它</td>
					</tr>
				 </table>
				</td>
				<td>中心/公司</td>
				<td><input class="inputBorder" type="text" name="templateBean.preCenter" value="${templateBean.preCenter}"/></td>
				<td><input class="inputBorder" type="text" name="templateBean.aftCenter" value="${templateBean.aftCenter}"/></td>
				</tr>
				<tr>
				  <td>部门</td>
				  <td><input class="inputBorder" type="text" name="templateBean.preDept" value="${templateBean.preDept}"/></td>
				  <td><input class="inputBorder" type="text" name="templateBean.aftDept" value="${templateBean.aftDept}"/></td>
				</tr>
				<tr>
				  <td>职位</td>
				  <td><input class="inputBorder" type="text" name="templateBean.prePosition" value="${templateBean.prePosition}"/></td>
				  <td><input class="inputBorder" type="text" name="templateBean.aftPosition" value="${templateBean.aftPosition}"/></td>
				</tr>
				<tr>
				  <td>职级</td>
				  <td><input class="inputBorder" type="text" name="templateBean.preRank" value="${templateBean.preRank}"/></td>
				  <td><input class="inputBorder" type="text" name="templateBean.aftRank" value="${templateBean.aftRank}"/></td>
				</tr>
				<tr>
				  <td>工资</td>
				  <td><input class="inputBorder" type="text" name="templateBean.preSalary" value="${templateBean.preSalary}"/></td>
				  <td><input class="inputBorder" type="text" name="templateBean.aftSalary" value="${templateBean.aftSalary}"/></td>
				</tr>
				<tr>
				  <td>外派补贴</td>
				  <td><input class="inputBorder" type="text" name="templateBean.preAssginBounty" value="${templateBean.preAssginBounty}"/></td>
				  <td><input class="inputBorder" type="text" name="templateBean.aftAssginBounty" value="${templateBean.aftAssginBounty}"/></td>
				</tr>
				<tr>
				  <td>住房补贴</td>
				  <td><input class="inputBorder" type="text" name="templateBean.preHousingBounty" value="${templateBean.preHousingBounty}"/></td>
				  <td><input class="inputBorder" type="text" name="templateBean.aftHousingBounty" value="${templateBean.aftHousingBounty}"/></td>
				</tr>
				<tr>
				  <td>交通补贴</td>
				  <td><input class="inputBorder" type="text" name="templateBean.preTrafficBounty" value="${templateBean.preTrafficBounty}"/></td>
				  <td><input class="inputBorder" type="text" name="templateBean.aftTrafficBounty" value="${templateBean.aftTrafficBounty}"/></td>
				</tr>
				<tr>
				  <td>通讯补贴</td>
				  <td><input class="inputBorder" type="text" name="templateBean.communication" value="${templateBean.communication}"/></td>
				  <td><input class="inputBorder" type="text" name="templateBean.aftCommunication" value="${templateBean.aftCommunication}"/></td>
				</tr>
				<tr>
				  <td>特殊补贴</td>
				  <td><input class="inputBorder" type="text" name="templateBean.preSpecialBounty" value="${templateBean.preSpecialBounty}"/></td>
				  <td><input class="inputBorder" type="text" name="templateBean.aftSpecialBounty" value="${templateBean.aftSpecialBounty}"/></td>
				</tr>
				<tr>
				  <td>其它</td>
				  <td><input class="inputBorder" type="text" name="templateBean.preOtherBounty" value="${templateBean.preOtherBounty}"/></td>
				  <td><input class="inputBorder" type="text" name="templateBean.aftOtherBounty" value="${templateBean.aftOtherBounty}"/></td>
				</tr>
			</table>
			</td>
		  </tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
