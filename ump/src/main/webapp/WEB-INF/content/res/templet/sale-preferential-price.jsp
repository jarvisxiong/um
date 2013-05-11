<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<!--销售价格优惠审批表-->
	
		<div align="center" class="billContent">
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="80"/>
					<col/>
					<col width="80"/>
					<col/>
					<tr>
						<td class="td_title">项目名称</td>
						<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"  /></td>
					</tr>
					<tr>
					  <td class="td_title">客户姓名</td>	
					  <td><input class="inputBorder" validate="required" type="text" name="templateBean.customerName" value="${templateBean.customerName}"/>
					  </td>
					  <td class="td_title">联系人</td>
					  <td><input class="inputBorder" validate="required" type="text" name="templateBean.linkman" value="${templateBean.linkman}"/>
					  </td>
					</tr>
					<tr>
					  <td class="td_title">联系地址</td>
					  <td><input class="inputBorder" validate="required" type="text" name="templateBean.contactAddr" value="${templateBean.contactAddr}"/></td>
					  <td class="td_title">联系电话</td>
					  <td><input class="inputBorder" validate="required" type="text" name="templateBean.contactTel" value="${templateBean.contactTel}"/></td>
					</tr>
					<tr>
					  <td class="td_title">物业范围</td>
					  <td class="chkGroup" validate="required"  align="left">
					   <table class="tb_checkbox">
					    <col width="100"/>
					    <col/>
					    <tr>
					     <td><s:checkbox name="templateBean.commerceSale" cssClass="group"></s:checkbox>商业销售</td>
					     <td><s:checkbox name="templateBean.houseSale" cssClass="group"></s:checkbox>住宅销售</td>
					    </tr>
					    <tr>
					     <td colspan="2"><s:checkbox name="templateBean.flatSale" cssClass="group"></s:checkbox>酒店式公寓</td>
					    </tr>
					   </table>
					  </td>
					  <td class="td_title">客户类别</td>
					  <td class="chkGroup" align="left" validate="required" >
					   <table class="tb_checkbox">
					    <col width="100"/>
					    <col/>
					    <tr>
					     <td><s:checkbox name="templateBean.relation" cssClass="group"></s:checkbox>关系户</td>
					     <td><s:checkbox name="templateBean.heavyBuyer" cssClass="group"></s:checkbox>大客户</td>
					    </tr>
					   </table>
					  </td>
					</tr>
					<tr>
					  <td class="td_title">面积</td>
					  <td class="chkGroup" align="left">
					  <input class="inputBorder" validate="required" type="text" name="templateBean.area" value="${templateBean.area}"/>
					  </td>
					  <td class="td_title">申请折扣</td>
					  <td><input class="inputBorder" validate="required" type="text" name="templateBean.discount" value="${templateBean.discount}"/></td>
					</tr>
					<tr>
						<td class="td_title">调整前</td>
						<td class="chkGroup" align="left">
						   <table class="tb_noborder">
						    <col width="40" />
						    <col/>
						    <tr>
						    <td class="td_title" style="padding-right: 5px;">单价</td>
						    <td><input class="inputBorder" validate="required" type="text" name="templateBean.price1" value="${templateBean.price1}"  onblur="formatVal($(this));" /></td>
						    </tr>
						    <tr>
						    <td class="td_title" style="padding-right: 5px;">总价</td>
						    <td><input class="inputBorder" validate="required" type="text" name="templateBean.totalPrice1" value="${templateBean.totalPrice1}"  onblur="formatVal($(this));" /></td>
						    </tr>
						   </table>
						</td>
						<td class="td_title">调整后</td>
						<td class="chkGroup" align="left">
						   <table class="tb_noborder">
						    <col width="40"/>
						    <col/>
						    <tr>
						    <td class="td_title" style="padding-right: 5px;">单价</td>
						    <td><input class="inputBorder" validate="required" type="text" name="templateBean.price2" value="${templateBean.price2}"  onblur="formatVal($(this));" /></td>
						    </tr>
						    <tr>
						    <td class="td_title" style="padding-right: 5px;">总价</td>
						    <td><input class="inputBorder" validate="required" type="text" name="templateBean.totalPrice2" value="${templateBean.totalPrice2}"  onblur="formatVal($(this));" /></td>
						    </tr>
						   </table>
						</td>
					</tr>
					<tr>
					  <td class="td_title">付款方式</td>
					  <td class="chkGroup" validate="required" colspan="3" align="left">
					    <table class="tb_checkbox">
					    <col width="100"/>
					    <col width="100"/>
					    <col width="100"/>
					    <tr>
					     <td><s:checkbox name="templateBean.payForOne" cssClass="group"></s:checkbox>一次性付款</td>
					     <td><s:checkbox name="templateBean.mortgagePay" cssClass="group"></s:checkbox>按揭付款</td>
					     <td colspan="2"><s:checkbox name="templateBean.instalment" cssClass="group"></s:checkbox>分期付款</td>
					    </tr>
					   </table>
					  </td>
					</tr>
					<tr>
					  <td class="td_title">销售状态</td>
					  <td class="chkGroup" validate="required"  align="left">
					    <table class="tb_checkbox">
					    <col width="100"/>
					    <col/>
					    <tr>
					     <td><s:checkbox name="templateBean.saleControl" cssClass="group"></s:checkbox>销控</td>
					     <td><s:checkbox name="templateBean.willSale" cssClass="group"></s:checkbox>未售</td>
					    </tr>
					    <tr>
					     <td colspan="2"><s:checkbox name="templateBean.subscribe" cssClass="group"></s:checkbox>认购</td>
					   </tr>
					   </table>
					  </td>
					  <td class="td_title">购买用途</td>
					  <td class="chkGroup" validate="required" align="left">
					    <table class="tb_checkbox">
					    <col width="100"/>
					    <col/>
					    <tr>
					     <td><s:checkbox name="templateBean.selfSupport" cssClass="group"></s:checkbox>自营</td>
					     <td><s:checkbox name="templateBean.investment" cssClass="group"></s:checkbox>投资</td>
					    </tr>
					   </table>
					  </td>
					</tr>
					<tr>
					  <td class="td_title">申购房源明细</td>
					  <td colspan="3">
					  <textarea class="inputBorder contentTextArea" validate="required" name="templateBean.saleDetail">${templateBean.saleDetail}</textarea>
					  </td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
