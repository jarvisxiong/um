<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
		<%-- 宝龙商业人事变动表 --%>
		<div class="div_row">
			<span class="wap_title">姓名:</span>
			<span class="wap_value">${templateBean.name}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">中心:</span>
			<span class="wap_value">${templateBean.centerName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">部门:</span>
			<span class="wap_value">${templateBean.dept}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">职位:</span>
			<span class="wap_value">${templateBean.position}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">入职日期:</span>
			<span class="wap_value">${templateBean.reportDuty}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">生效日期:</span>
			<span class="wap_value">${templateBean.effectiveDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">用工类别:</span>
			<div><s:checkbox name="templateBean.official" cssClass="group"></s:checkbox><span>正式工</span></div>
			<div><s:checkbox name="templateBean.temporaryWorkers" cssClass="group"></s:checkbox><span>临时工</span></div>
			<div><s:checkbox name="templateBean.trainee" cssClass="group"></s:checkbox><span>实习生</span></div>
			<div><s:checkbox name="templateBean.externalAuditor" cssClass="group"></s:checkbox><span>外聘</span></div>
			<div><s:checkbox name="templateBean.outAssign" cssClass="group"></s:checkbox><span>外派</span></div>
			<div><s:checkbox name="templateBean.foreignNationality" cssClass="group"></s:checkbox><span>外籍</span></div>
		</div>
		<div class="div_label">
			<span class="wap_label">【调整项目详细】</span>
			<div class="div_scroll">
			<table>
				<tr>
				 <th><span class="wap_title">调整项目</span></span></th>
				 <th><span class="wap_title">内容</span></th>
				 <th><span class="wap_title">调整前</span></th>
				 <th><span class="wap_title">调整后</span></th>
				</tr>
				<tr>
				<td rowspan="11">
					 <div><s:checkbox name="templateBean.newEmploy" cssClass="group"></s:checkbox><span>新聘</span></div>
					 <div><s:checkbox name="templateBean.passQualifyingPeriod" cssClass="group"></s:checkbox><span>通过试用期</span></div>
					 <div><s:checkbox name="templateBean.promotion" cssClass="group"></s:checkbox><span>升职</span></div>
					 <div><s:checkbox name="templateBean.demotion" cssClass="group"></s:checkbox><span>降职</span></div>
					 <div><s:checkbox name="templateBean.insideRemove" cssClass="group"></s:checkbox><span>内部调动</span></div>
					 <div><s:checkbox name="templateBean.salaryChange" cssClass="group"></s:checkbox><span>工资变动</span></div>
					 <div><s:checkbox name="templateBean.otherChange" cssClass="group"></s:checkbox><span>其它</span></div>
				</td>
				<td><span class="wap_title">中心/公司</span></td>
				<td><span class="wap_value">${templateBean.preCenter}</span></td>
				<td><span class="wap_value">${templateBean.aftCenter}</span></td>
				</tr>
				<tr>
				  <td><span class="wap_title">部门</span></td>
				  <td><span class="wap_value">${templateBean.preDept}</span></td>
				  <td><span class="wap_value">${templateBean.aftDept}</span></td>
				</tr>
				<tr>
				  <td><span class="wap_title">职位</span></td>
				  <td><span class="wap_value">${templateBean.prePosition}</span></td>
				  <td><span class="wap_value">${templateBean.aftPosition}</span></td>
				</tr>
				<tr>
				  <td><span class="wap_title">职级</span></td>
				  <td><span class="wap_value">${templateBean.preRank}</span></td>
				  <td><span class="wap_value">${templateBean.aftRank}</span></td>
				</tr>
				<tr>
				  <td><span class="wap_title">工资</span></td>
				  <td><span class="wap_value">${templateBean.preSalary}</span></td>
				  <td><span class="wap_value">${templateBean.aftSalary}</span></td>
				</tr>
				<tr>
				  <td><span class="wap_title">外派补贴</span></td>
				  <td><span class="wap_value">${templateBean.preAssginBounty}</span></td>
				  <td><span class="wap_value">${templateBean.aftAssginBounty}</span></td>
				</tr>
				<tr>
				  <td><span class="wap_title">住房补贴</span></td>
				  <td><span class="wap_value">${templateBean.preHousingBounty}</span></td>
				  <td><span class="wap_value">${templateBean.aftHousingBounty}</span></td>
				</tr>
				<tr>
				  <td><span class="wap_title">交通补贴</span></td>
				  <td><span class="wap_value">${templateBean.preTrafficBounty}</span></td>
				  <td><span class="wap_value">${templateBean.aftTrafficBounty}</span></td>
				</tr>
				<tr>
				  <td><span class="wap_title">通讯补贴</span></td>
				  <td><span class="wap_value">${templateBean.communication}</span></td>
				  <td><span class="wap_value">${templateBean.aftCommunication}</span></td>
				</tr>
				<tr>
				  <td><span class="wap_title">特殊补贴</span></td>
				  <td><span class="wap_value">${templateBean.preSpecialBounty}</span></td>
				  <td><span class="wap_value">${templateBean.aftSpecialBounty}</span></td>
				</tr>
				<tr>
				  <td><span class="wap_title">其它</span></td>
				  <td><span class="wap_value">${templateBean.preOtherBounty}</span></td>
				  <td><span class="wap_value">${templateBean.aftOtherBounty}</span></td>
				</tr>
			</table>
			</div>
		</div>
		
