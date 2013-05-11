<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--换房审批表-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="70">
			<col>
			<col width="70">
			<col>
			 <tr>
				<td class="td_title">项目名称</td>
				<td colspan="3">
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}" />
				</td>
			</tr> 
			<tr>
				<td class="td_title">业主姓名</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.owner" value="${templateBean.owner}"/>
				</td>
				<td class="td_title">身份证<br/>/护照号
				</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.idno" value="${templateBean.idno}"/>
				</td>
			</tr> 
			<tr>
				<td class="td_title">联系电话
				</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.phoneNo" value="${templateBean.phoneNo}"/>
				</td>
				<td class="td_title">通讯地址
				</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.address" value="${templateBean.address}"/>
				</td>
			</tr> 
			<tr>
				<td colspan="2" style="text-align: center;">
					认购资料
				</td>
				<td colspan="2" style="text-align: center;">
					换购资料
				</td>
			</tr> 
			<tr>    
				<td class="td_title">认购单元</td>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder" >
						<tr>
							<td><input validate="required" class="inputBorder" type="text" name="templateBean.houseNameOri" value="${templateBean.houseNameOri}"/>
							</td>
							<td style="width:15px;">栋</td>
							<td><input validate="required" class="inputBorder" type="text" name="templateBean.buildNameOri" value="${templateBean.buildNameOri}"/></td>
							<td style="width:15px;">层</td>
							<td><input class="inputBorder contentTextArea" type="text" name="templateBean.unitNameOri" value="${templateBean.unitNameOri}"/></td>
							<td style="width:30px;">单元</td>
							<td><input validate="required" class="inputBorder" type="text" name="templateBean.storeNameOri" value="${templateBean.storeNameOri}"/></td>
							<td style="width:55px;">住宅/店面</td>
						</tr>
					</table>
				</td>
				<td class="td_title">换购单元</td>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder" >
						<tr>
							<td><input validate="required" class="inputBorder" type="text" name="templateBean.houseNameCur" value="${templateBean.houseNameCur}"/>
							</td>
							<td style="width:15px;">栋</td>
							<td ><input validate="required" class="inputBorder" type="text" name="templateBean.buildNameCur" value="${templateBean.buildNameCur}"/>
							</td>
							<td style="width:15px;">层</td>
							<td><input class="inputBorder contentTextArea" type="text" name="templateBean.unitNameCur" value="${templateBean.unitNameCur}"/>
							</td>
							<td style="width:30px;">单元</td>
							<td><input validate="required" class="inputBorder" type="text" name="templateBean.storeNameCur" value="${templateBean.storeNameCur}"/></td>
							<td style="width:55px;">住宅/店面</td>
						</tr>
					</table>
				</td>
			</tr> 
			<tr>
				<td class="td_title">建筑面积</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));" name="templateBean.buildSquareOri" value="${templateBean.buildSquareOri}"/>
				</td>
				<td class="td_title">建筑面积</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));" name="templateBean.buildSquareCur" value="${templateBean.buildSquareCur}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">建筑单价(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.unitPriceOri" value="${templateBean.unitPriceOri}"/>
				</td>
				<td class="td_title">建筑单价(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.unitPriceCur" value="${templateBean.unitPriceCur}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">总价(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.totalPriceOri" value="${templateBean.totalPriceOri}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">总价(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.totalPriceCur" value="${templateBean.totalPriceCur}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">认购时间</td>
				<td>
					<input validate="required" class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.substribeDate" value="${templateBean.substribeDate}"/>
				</td>
				<td class="td_title">差价(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.differPrice" value="${templateBean.differPrice}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td rowspan="3" class="td_title">
					已付款项 
				</td>
				<td>
					<table class="tb_noborder"><tr>
					<td class="td_title" width="60">付款时间</td>
					<td>
						<input validate="required" class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.prePayDate" value="${templateBean.prePayDate}"/>
					</td>
					</tr>
					</table>
				</td>
				<td class="td_title">预订金(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.prePayAmt" value="${templateBean.prePayAmt}" />
				</td>
			</tr> 
			<tr>
			<td>
				<table class="tb_noborder"><tr>
				<td class="td_title" width="60">付款时间</td>
				<td>
					<input  class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.firstPayDate" value="${templateBean.firstPayDate}"/>
				</td>
				</tr>
				</table>
			</td>
				<td class="td_title">首付款(元)</td>
				<td>
					<input class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.firstPayAmt" value="${templateBean.firstPayAmt}" />
				</td>
			</tr> 
			<tr>
			<td>
					<table class="tb_noborder"><tr>
					<td class="td_title" width="60">付款时间</td>
					<td>
						<input class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.repayDate" value="${templateBean.repayDate}"/>
					</td>
					</tr>
					</table>
				</td>
				<td class="td_title"> 续 款(元)</td>
				<td>
					<input class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.repayAmt" value="${templateBean.repayAmt}" />
				</td>
			</tr> 
			<tr>
				<td class="td_title">物业现状</td>
				<td colspan="3" align="left">
				<table class="tb_checkbox">
					<col width="100">
					<col width="120">
					<col width="100">
					<tr class="chkGroup"   validate="required" >
						<td><s:checkbox name="templateBean.selectReseBook" cssClass="group"></s:checkbox>预订书</td>
						<td><s:checkbox name="templateBean.selectSignContract" cssClass="group"></s:checkbox>签定买卖合同</td>
						<td><s:checkbox name="templateBean.selectMortgageLoan" cssClass="group"></s:checkbox>办理按揭</td>
					</tr>
				</table>
				</td>
			</tr> 
			<tr>
				<td class="td_title">声明</td>
				<td align="left" colspan="3" >
					<div>
						本人申请上述房产的换购，并愿意支付以下费用：
					</div>
					<div style="float:left;">
						原房屋成交总价1％的手续费用，即
					</div>
					<div style="float:left;width:100px;">
						<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.procedureChangeAmt" value="${templateBean.procedureChangeAmt}" />
					</div>
						元。
					<br/>
					<div style="float:left;">
						业主签名：
					</div>
					<div style="float:left;width:100px;">
						<input class="inputBorder" type="text" name="templateBean.owerSign" value="${templateBean.owerSign}" />
					</div>
				</td>
			</tr>
			<tr>
			<td class="td_title">备注</td>
			<td colspan="3" ><textarea class="inputBorder contentTextArea" name="templateBean.contentDesc" style="width:100%;height:100px;">${templateBean.contentDesc}</textarea>
			</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>


