<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--更名审批表-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
		<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="110px;">
			<col>
			<col width="100px;">
			<col>
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="3">
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}" />
				</td>
			</tr> 
			<tr>
				<td class="td_title">类别</td>
				<td colspan="3" align="left" class="chkGroup" validate="required" >
					<s:checkbox name="templateBean.typeMinus" cssClass="group"></s:checkbox>减名
					<s:checkbox name="templateBean.typeAdd" cssClass="group"></s:checkbox>增名
					<s:checkbox name="templateBean.typeChange" cssClass="group"></s:checkbox>换名(转让)
				</td>
			</tr> 
			<tr>
				<td class="td_title">原业主姓名</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.ownerOri" value="${templateBean.ownerOri}"/>
				</td>
				<td class="td_title">现业主姓名</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.ownerCur" value="${templateBean.ownerCur}"/>
				</td>
			</tr> 
			<tr>
				<td class="td_title">身份证/护照号</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.idnoOri" value="${templateBean.idnoOri}"/>
				</td>
				<td class="td_title">身份证/护照号</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.idnoCur" value="${templateBean.idnoCur}"/>
				</td>
			</tr> 
			<tr>
				<td class="td_title">联系电话</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.phoneNoOri" value="${templateBean.phoneNoOri}"/>
				</td>
				<td class="td_title">联系电话</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.phoneNoCur" value="${templateBean.phoneNoCur}"/>
				</td>
			</tr> 
			<tr>
				<td class="td_title">通讯地址
				</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.addressOri" value="${templateBean.addressOri}"/>
				</td>
				<td class="td_title">通讯地址</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.addressCur" value="${templateBean.addressCur}"/>
				</td>
			</tr> 
			<tr>    
				<td class="td_title">认购单元</td>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder" >
						<tr>
							<td><input validate="required" class="inputBorder" type="text" name="templateBean.houseName" value="${templateBean.houseName}"/>
							</td>
							<td style="width:15px;">栋</td>
							<td><input validate="required" class="inputBorder" type="text" name="templateBean.floorName" value="${templateBean.floorName}"/>
							</td>
							<td style="width:15px;">层</td>
							<td><input class="inputBorder contentTextArea" type="text" name="templateBean.unitName" value="${templateBean.unitName}"/>
							</td>
							<td style="width:30px;">单元</td>
							<td><input validate="required" class="inputBorder" type="text" name="templateBean.storeName" value="${templateBean.storeName}"/></td>
							<td style="width:70px;">住宅/店面</td>
						</tr>
					</table>
				</td>
				<td class="td_title">单价(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.unitPrice" value="${templateBean.unitPrice}"/>
				</td>
			</tr> 
			<tr>
				<td class="td_title">认购时间</td>
				<td>
					<input validate="required" class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.substribeDate" value="${templateBean.substribeDate}"/>
				</td>
				<td class="td_title">总价(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.totalPrice" value="${templateBean.totalPrice}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">是否已额外申请优惠</td>
				<td colspan="3" align="left" class="chkGroup" validate="required" >
					<s:checkbox name="templateBean.isPrivilegeTrue" cssClass="group"></s:checkbox>是<input  class="inputBorder" type="text" name="templateBean.privilegeValue" value="${templateBean.privilegeValue}" style="width:100px;"/>%
					<s:checkbox name="templateBean.isPrivilegeFalse" cssClass="group"></s:checkbox>否
				</td>
			</tr> 
			<tr>
				<td rowspan="4" class="td_title">
					已付款项 
				</td>
			</tr>
			<tr>
				<td align="left">时间
					<input validate="required" style="width:100px;"  class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.prePayDate" value="${templateBean.prePayDate}"/>
				</td>
				<td class="td_title">预订金(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.prePayAmt" value="${templateBean.prePayAmt}" />
				</td>
			</tr> 
			<tr>
				<td align="left">时间
					<input style="width:100px;"  class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.firstPayDate" value="${templateBean.firstPayDate}"/>
				</td>
				<td class="td_title">首付款(元)</td>
				<td>
					<input class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.firstPayAmt" value="${templateBean.firstPayAmt}" />
				</td>
			</tr> 
			<tr>
				<td align="left">时间
					<input style="width:100px;" class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.repayDate" value="${templateBean.repayDate}"/>
				</td>
				<td class="td_title"> 续&nbsp;&nbsp; 款(元)</td>
				<td>
					<input class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.repayAmt" value="${templateBean.repayAmt}" />
				</td>
			</tr> 
			<tr>
				<td class="td_title">物业现状</td>
				<td colspan="3" align="left" class="chkGroup" validate="required" >
					<s:checkbox name="templateBean.selectReseBook" cssClass="group"></s:checkbox>预订书
					<s:checkbox name="templateBean.selectSignContract" cssClass="group"></s:checkbox>签定买卖合同
					<s:checkbox name="templateBean.selectMortgageLoan" cssClass="group"></s:checkbox>办理按揭
				</td>
			</tr> 
			<tr>
				<td class="td_title">声明</td>
				<td colspan="3" style="padding-left:5px;" valign="top" align="left">
					<div>
						本人同意上述房产为本人名字，并愿意支付以下费用：<br/>
					</div>
					<div style="float:left;">
						原成交总价1％的手续费用
					</div>
					<div style="float:left;width:100px;">
						<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.procedureChangeAmt" value="${templateBean.procedureChangeAmt}" />
					</div>
						元。上述房屋因更名产生的法律纠纷一切与贵公司无关。
					<br/>
					<div style="float:left;">
						原业主签名：
					</div>
					<div style="float:left;width:100px;">
						<input validate="required" class="inputBorder" type="text" name="templateBean.owerSignOri" value="${templateBean.owerSignOri}" />
					</div>
					<div style="float:left;">
						现业主签名：
					</div>
					<div style="float:left;width:100px;">
						<input validate="required" class="inputBorder" type="text" name="templateBean.owerSignCur" value="${templateBean.owerSignCur}" />
					</div>
				</td>
			</tr>
			<tr>
				<td class="td_title">备注</td>
				<td colspan="3" valign="top" align="left">
					<textarea class="inputBorder contentTextArea" name="templateBean.contentDesc" style="width:100%;height:150px;">${templateBean.contentDesc}</textarea>
				</td>
			</tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>


