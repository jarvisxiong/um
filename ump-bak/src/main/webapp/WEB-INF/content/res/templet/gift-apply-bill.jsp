<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

	<%@ include file="template-header.jsp"%>
	<!--礼品申请表-->
		
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%><div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
				<table  class="mainTable">
					<col width="15%"/>
					<col width="35%"/>
					<col width="15%"/>
					<col width="35%"/>
					<tr>
						<td  class="td_title">公司/中心</td>
						<td>
						<s:if test="resApproveInfoId==null">
						<s:set var="centerName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </s:set>
						<s:set var="centerCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></s:set>
						</s:if>
						<s:else>
						<s:set var="centerName">${templateBean.centerName}</s:set>
						<s:set var="centerCd">${templateBean.centerCd}</s:set>
						</s:else>
						<input validate="required" type="text" name="templateBean.centerName" value="${centerName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
						<input type="hidden" id="centerCd" name="templateBean.centerCd" value="${centerCd}"  />
						</td>
						<td class="td_title">费用承担单位</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.feeUnit" value="${templateBean.feeUnit}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">赠送对象</td>
						<td colspan="3">
						<input class="inputBorder" validate="required" type="text" name="templateBean.givingObject" value="${templateBean.givingObject}"  />
						</td>
					</tr>
				</table>
				<table  class="mainTable" id="giftTable" style="margin-top:5px;">
					<col width="20%"/>
					<col width="40%"/>
					<col width="15%"/>
					<col width="15%"/>
					<col width="10%"/>
					<tr>
						<td align="center">礼品清单</td>
						<td align="center">内容</td>
						<td align="center">数量/单位</td>
						<td align="center">单价(元)</td>
						<td align="center">操作</td>
					</tr>
					<s:if test="statusCd==0 || statusCd==3">
					<tr id="trConItem" style="display: none;margin-bottom:5px;">
						<td >
						<input class="inputBorder" validate="required" type="text" name="templateBean.otherProperties[0].giftList"/>
						</td>
						<td>
						<input class="inputBorder"  type="text" name="templateBean.otherProperties[0].giftContent"/>
						</td>
						<td>
						<input class="inputBorder"  type="text"  name="templateBean.otherProperties[0].quantity"/>
						</td>
						<td>
						<input class="inputBorder"  type="text" onblur="formatVal($(this));" name="templateBean.otherProperties[0].unitPrice"/>
						</td>
						<td>
						<a href="javascript:void(0)" onclick="delItem(this)"><img title="删除" src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
						</td>
					</tr>
					</s:if>
					<s:iterator value="templateBean.otherProperties" var="item" status="s">
					<tr>
						<td >
						<input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].giftList" value="${item.giftList}"/>
						</td>
						<td>
						<input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].giftContent" value="${item.giftContent}"/>
						</td>
						<td>
						<input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].quantity" value="${item.quantity}"/>
						</td>
						<td>
						<input class="inputBorder"  type="text"  onblur="formatVal($(this));" name="templateBean.otherProperties[<s:property value="#s.index" />].unitPrice" value="${item.unitPrice}"/>
						</td>
						<td>
						<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
						<a href="javascript:void(0)" onclick="delItem(this)"><img title="删除" src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
						</s:if>
						</td>
					</tr>
					</s:iterator>
				</table>
				<s:if test="statusCd==0 || statusCd==3">
				<input type="button"  class="btn_blue_75_55" value="增加条款" onclick="addItem();" />
				</s:if>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
		<script type="text/javascript">
			var trApprover=$("#trConItem").clone();
			$("#trConItem").remove();
			var cloneCount = 0;
			var index=${conItemCount};
			function addItem(){
				
				var trApprover_new=trApprover.clone();
				cloneCount++;
				$(trApprover_new).show();
				$(trApprover_new).find(".inputBorder").each(function(i){
					this.name=this.name.replace('0',index);
				});
				$("#giftTable").append(trApprover_new);
				index++;
			}
			function delItem(dom){
				$(dom).parent().parent().remove();
			}
		</script>
		<!-- 默认1条申请记录 -->
		<s:if test="resApproveInfoId==null">
		<script type="text/javascript">
			addItem();
		</script>
		</s:if>