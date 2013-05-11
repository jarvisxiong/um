<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--退房审批表-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="30px;">
			<col width="100px;">
			<col>
			<col width="100px;">
			<col>
			<tr>
				<td colspan="2" class="td_title">项目名称</td>
				<td colspan="3">
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}" />
				</td>
			</tr> 
			<tr>
				<td colspan="2" class="td_title">业主姓名</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.owerName" value="${templateBean.owerName}"/>
				</td>
				<td class="td_title">身份证/护照号</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.idno" value="${templateBean.idno}"/>
				</td>
			</tr> 
			<tr>
				<td colspan="2" class="td_title">联系电话</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.phoneNo" value="${templateBean.phoneNo}"/>
				</td>
				<td class="td_title">通讯地址</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.address" value="${templateBean.address}"/>
				</td>
			</tr> 
			<tr>    
				<td rowspan="8" style="text-align: center;">退房资料</td>
				<td class="td_title">退购单元</td>
				<td style="padding-left:5px;" colspan="3">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<tr>
							<td style="width:60px;"><input style="width:60px;" validate="required" class="inputBorder" type="text" name="templateBean.buildName" value="${templateBean.buildName}"/></td>
							<td style="width:15px;">栋</td>
							<td style="width:60px;"><input style="width:60px;" class="inputBorder contentTextArea" type="text" name="templateBean.unitName" value="${templateBean.unitName}"/></td>
							<td style="width:30px;">单元</td>
							<td style="width:60px;"><input style="width:60px;" validate="required" class="inputBorder contentTextArea" type="text" name="templateBean.floorName" value="${templateBean.floorName}"/></td>
							<td style="width:15px;">层</td>
							<td><input validate="required" class="inputBorder" type="text" name="templateBean.storeName" value="${templateBean.storeName}"/></td>
							<td style="width:80px;">住宅/店面</td>
						</tr>
					</table>
				</td>
			</tr> 
			<tr>
				<td class="td_title">建筑面积</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.buildSquare" value="${templateBean.buildSquare}" onblur="formatVal($(this));"/>
				</td>
			</tr> 
			<tr>
				<td class="td_title">原建筑单价(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.oldConsUnitPrice" value="${templateBean.oldConsUnitPrice}" onblur="formatFloat(this)"/>
				</td>
				<td class="td_title">新建筑单价(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.newConsUnitPrice" value="${templateBean.newConsUnitPrice}" onblur="formatFloat(this)"/>
				</td>
			</tr> 
			<tr>
				<td class="td_title">原总价(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.oldTotalPrice" value="${templateBean.oldTotalPrice}" onblur="formatFloat(this)"/>
				</td>
				<td class="td_title">新总价(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.newTotalPrice" value="${templateBean.newTotalPrice}" onblur="formatFloat(this)"/>
				</td>
			</tr> 
			<tr>
				<td class="td_title">认购时间</td>
				<td>
					<input validate="required" class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.substribeDate" value="${templateBean.substribeDate}"/>
				</td>
				<td class="td_title">调价比例(%)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.priceAdjustProp" value="${templateBean.priceAdjustProp}" onblur="formatVal($(this));"/>
				</td>
			</tr> 
			<tr>
				<td rowspan="3" class="td_title">
					已付款项 
				</td>
				<td class="td_title">时间
					<input validate="required" style="width:90px;"  class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.prePayDate" value="${templateBean.prePayDate}"/>
				</td>
				<td class="td_title">预订金(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.prePayAmt" value="${templateBean.prePayAmt}"  onblur="formatVal($(this));"/>
				</td>
			</tr> 
			<tr>
				<td class="td_title">时间
					<input style="width:90px;"  class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.firstPayDate" value="${templateBean.firstPayDate}"/>
				</td>
				<td class="td_title">首付款(元)</td>
				<td>
					<input class="inputBorder" type="text" name="templateBean.firstPayAmt" value="${templateBean.firstPayAmt}"   onblur="formatVal($(this));" />
				</td>
			</tr> 
			<tr>
				<td class="td_title">时间
					<input style="width:90px;"  class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.repayDate" value="${templateBean.repayDate}"/>
				</td>
				<td class="td_title">续款(元)</td>
				<td>
					<input class="inputBorder" type="text" name="templateBean.repayAmt" value="${templateBean.repayAmt}"  onblur="formatVal($(this));"/>
				</td>
			</tr> 
			<tr>
				<td class="td_title"  colspan="2" >物业现状</td>
				<td colspan="3" align="left" class="chkGroup" validate="required" >
					<s:checkbox name="templateBean.selectReseBook" cssClass="group"></s:checkbox>预订书
					<s:checkbox name="templateBean.selectSignContract" cssClass="group"></s:checkbox>签定买卖合同
					<s:checkbox name="templateBean.selectMortgageLoan" cssClass="group"></s:checkbox>办理按揭
				</td>
			</tr> 
			<tr>
				<td colspan="2" class="td_title">声明</td>
				<td colspan="3" style="padding-left:5px;" valign="top" align="left">
					<div style="float:left;">
						本人申请上述房产的退购，并愿意支付以下费用：
					</div>
					<br/>
					<div style="float:left;">
						原房屋成交总价1％的手续费用，即
					</div>
					<div style="float:left;width:60px;">
						<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.procedureChangeAmt" value="${templateBean.procedureChangeAmt}" />
					</div>
					<div style="float:left;">
						元。
					</div>
					<div  style="clear: both;"></div>
					<div style="float:left;">
						业主签名：
					</div>
					<div style="float:left;width:100px;">
						<input class="inputBorder contentTextArea" type="text" name="templateBean.owerSignName" value="${templateBean.owerSignName}" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="td_title">备注</td>
				<td colspan="3" valign="top"  align="left">
					<textarea class="inputBorder contentTextArea" name="templateBean.contentDesc" style="width:100%;height:100px;">${templateBean.contentDesc}</textarea>
				</td>
			</tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>


