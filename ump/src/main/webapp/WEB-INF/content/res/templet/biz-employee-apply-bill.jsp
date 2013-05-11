<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 宝龙商业人员申请表 --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">

	
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="90px"/>
			<col />
			<col width="60px"/>
			<col width="80px"/>
			<col width="60px"/>
			<col />
			<col width="85px"/>
			<col />
			<tr>
				<td style="border-top:0 none;" class="td_title">中心</td>
				<td style="border-top: none;" colspan="3">
					<input id="centerName" validate="required" type="text" name="templateBean.centerName" value="${templateBean.centerName}" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" name="templateBean.centerCd" value="${templateBean.centerCd}" />
				</td>
				<td style="border-top:0 none;" class="td_title">职位</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.positionName" value="${templateBean.positionName}" />
					<input type="hidden" name="templateBean.positionCd" value="${templateBean.positionCd}" />
				</td>
				<td style="border-top:0 none;" class="td_title">级别</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.positionLevelName" value="${templateBean.positionLevelName}" />
					<input type="hidden" name="templateBean.positionLevelCd" value="${templateBean.positionLevelCd}" />
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">薪金(元)</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.salary" value="${templateBean.salary}"  onblur="formatVal($(this));" />
				</td>
				
				<td style="border-top:0 none;" class="td_title">性别</td>
				<td style="border-top:0 none;" class="chkGroup" align="left"  validate="required">
					<s:checkbox name="templateBean.sexCd1"  cssClass="group"></s:checkbox>男
					<s:checkbox name="templateBean.sexCd2"  cssClass="group"></s:checkbox>女
				</td>
				<td style="border-top:0 none;" class="td_title">年龄</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.age" value="${templateBean.age}" />
				</td>
				<td style="border-top:0 none;" class="td_title">婚否</td>
				<td style="border-top:0 none;" class="chkGroup" align="left"  validate="required">
					<s:checkbox name="templateBean.marryCd1"  cssClass="group"></s:checkbox>是
					<s:checkbox name="templateBean.marryCd2"  cssClass="group"></s:checkbox>否
				</td>
			</tr> 
			
			 
			<tr>
				<td style="border-top:0 none;" class="td_title">到岗日期</td>
				<td style="border-top: none;">
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.planWorkDate" value="${templateBean.planWorkDate}" />
				</td>
				<td style="border-top:0 none;" class="td_title">申请人数</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.applyCount" value="${templateBean.applyCount}"  onblur="formatVal($(this));" />
				</td>
				<td style="border-top:0 none;text-align: center;" class="chkGroup" align="left"  validate="required" colspan="2">
					<s:checkbox name="templateBean.applyTypeCd1"  cssClass="group"></s:checkbox>增加
					<s:checkbox name="templateBean.applyTypeCd2"  cssClass="group"></s:checkbox>补缺
				</td>
				<td style="border-top:0 none;" class="td_title">员工类别</td>
				<td style="border-top:0 none;" class="chkGroup" align="left"  validate="required">
					<s:checkbox name="templateBean.employeeTypeCd1"  cssClass="group"></s:checkbox>正式工
					<s:checkbox name="templateBean.employeeTypeCd2"  cssClass="group"></s:checkbox>临时工
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">工作职责简述</td>
				<td style="border-top:0 none;" colspan="7">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.workDutyDesc">${templateBean.workDutyDesc}</textarea>
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">任职要求</td>
				<td style="border-top:0 none;" colspan="7">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.workRequireDesc">${templateBean.workRequireDesc}</textarea>
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">人员申请理由</td>
				<td style="border-top:0 none;" colspan="7">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.applyReasonDesc">${templateBean.applyReasonDesc}</textarea>
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">招聘渠道</td>
				<td style="border-top:0 none;" class="chkGroup" align="left"  validate="required" colspan="3">
					<s:checkbox name="templateBean.recruitDitchCd1" ></s:checkbox>公司内部提升
					<s:checkbox name="templateBean.recruitDitchCd2" ></s:checkbox>公司内部调动
					<s:checkbox name="templateBean.recruitDitchCd3" ></s:checkbox>从外部招聘
				</td>
				<td style="border-top:0 none;" class="td_title">招聘费用</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.recruitFeeAmt" value="${templateBean.recruitFeeAmt}"  onblur="formatVal($(this));" />
				</td>
				<td style="border-top:0 none;" class="td_title">实际到岗时间</td>
				<td style="border-top: none;">
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.realWorkDate" value="${templateBean.realWorkDate}" />
				</td>
			</tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
