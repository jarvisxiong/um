<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--名片印刷内容确认单-->
		<%@ include file="template-header.jsp"%>
		<br />
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="100"/>
					<col />
					<col width="100"/>
					<col />
					<tr>
						<td class="td_title">姓名</td>
						<td>
							<input id="applyUserName" validate="required"  type="text" name="templateBean.userName" value="${templateBean.userName}"readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else>/>
							<input id="applyUserCd" type="hidden" name="templateBean.userCd" value="${templateBean.userCd}"  />
						</td>
						<td class="td_title">英文名</td>
						<td>
						   <input class="inputBorder" validate="required" type="text" id="englishName"  name="templateBean.englishName" value="${templateBean.englishName}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">手机</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.mobilePhone" value="${templateBean.mobilePhone}" id="mobilePhone"  />
						</td>
						<td class="td_title">电话</td>
						<td>
						   <input class="inputBorder"  validate="required" type="text" id="telephone"  name="templateBean.telephone" value="${templateBean.telephone}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">传真</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.fax" value="${templateBean.fax}" id="fax"  />
						</td>
						<td class="td_title">E-mail</td>
						<td>
						   <input class="inputBorder" validate="required" type="text" id="email"  name="templateBean.email" value="${templateBean.email}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">所属公司</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.company" value="${templateBean.company}" id="company"  />
						</td>
						<td class="td_title">部门</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.department" value="${templateBean.department}" id="department"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">职位</td>
						<td>
						   <input class="inputBorder" validate="required" type="text" id="position"  name="templateBean.position" value="${templateBean.position}"  />
						</td>
						<td class="td_title">数量(盒)</td>
						<td >
							<input class="inputBorder" validate="required" type="text" name="templateBean.amount" value="${templateBean.amount}" id="amount"  onblur="formatNum($(this));"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">地址</td>
						<td colspan="3">
						   <input class="inputBorder" validate="required" type="text" id="address"  name="templateBean.address" value="${templateBean.address}"  />
						</td>
					</tr>
					
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
