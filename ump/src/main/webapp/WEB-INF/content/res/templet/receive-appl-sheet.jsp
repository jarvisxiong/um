<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<!--接待审批表-->
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="20%"/>
					<col width="30%"/>
					<col width="20%"/>
					<col width="30%"/>
					<tr>
						<td  class="td_title">申请中心</td>
						<td colspan="3">
							<input validate="required" type="text" name="templateBean.applCenter" value="${templateBean.applCenter}" id="applCenterNameId" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
							<input type="hidden"  name="templateBean.applCenterCd" value="${templateBean.applCenterCd}"  id="applCenterCdId"/>
						</td>
					</tr>
					<tr>
						<td  class="td_title">招待对象</td>
						<td >
							<input class="inputBorder" validate="required" type="text" name="templateBean.receivePartner" value="${templateBean.receivePartner}"  />
						</td>
						<td  class="td_title">招待费用承担中心</td>
						<td >
							<input validate="required" type="text" name="templateBean.amountPayCenter" value="${templateBean.amountPayCenter}" id="amountPayCenterNameId" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
							<input type="hidden"  name="templateBean.amountPayCenterCd" value="${templateBean.amountPayCenterCd}"  id="amountPayCenterCdId"/>
						</td>
					</tr>
					<tr>
						<td  class="td_title">招待事由</td>
						<td colspan="3">
							<input class="inputBorder" validate="required" type="text" name="templateBean.receiveCause" value="${templateBean.receiveCause}"  />
						</td>
					</tr>
					<tr >
						<td  class="td_title">住宿酒店</td>
						<td>
							<s:select  list="#request.hotels" id="hotelId" onchange="getHotelStandard();" listValue="dictTypeName" listKey="dictTypeCd" emptyOption="true" value="templateBean.hotel" name="templateBean.hotel"></s:select>
						</td>
						<td  class="td_title">住宿标准</td>
						<td>
							<s:select id="hotelStandardId" list="{}" tempValue="${templateBean.hotelStandard}" name="templateBean.hotelStandard"></s:select>
						</td>
					</tr>
					<tr >
						<td  class="td_title">入住时间</td>
						<td>
							<input class="Wdate inputBorder" type="text"  name="templateBean.checkInDate" value="${templateBean.checkInDate}"  onfocus="WdatePicker()"/>
						</td>
						<td colspan="2" align="center" rowspan="2">
							单人房:<input class="inputBorder" type="text" style="width:50px;" name="templateBean.singleRoomNum" value="${templateBean.singleRoomNum}"  />间
							双人房:<input class="inputBorder" type="text" style="width:50px;" name="templateBean.doubleRoomNum" value="${templateBean.doubleRoomNum}"  />间
						</td>
					</tr>
					<tr >
						<td  class="td_title">离店时间</td>
						<td>
							<input class="Wdate inputBorder" type="text"  name="templateBean.checkOutDate" value="${templateBean.checkOutDate}"  onfocus="WdatePicker()"/>
						</td>
					</tr>
					<!--<tr>
						<td  class="td_title" >用餐</td>
						<td  class="chkGroup" >
							<input class="Wdate inputBorder" style="width:90px;" type="text" name="templateBean.dinnerDate" value="${templateBean.dinnerDate}"  onfocus="WdatePicker()"/>
							<s:checkbox name="templateBean.lunch" cssClass="group"></s:checkbox>午餐
							<s:checkbox name="templateBean.dinner"  cssClass="group"></s:checkbox>晚餐
						</td>
						<td colspan="2" >
							宾客&nbsp;&nbsp;<input class="inputBorder" style="width:50px;" type="text" name="templateBean.dinnerGuestNum" value="${templateBean.dinnerGuestNum}"  /> 人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;陪同&nbsp;&nbsp;<input class="inputBorder" type="text" style="width:50px;" name="templateBean.dinnerGuideNum" value="${templateBean.dinnerGuideNum}"  /> 人
						</td>
					</tr>
					-->
					<tr>
						<td class="td_title" >(午餐)用餐时间</td>
						<td >
							<input class="Wdate inputBorder"  type="text" name="templateBean.lunchDate" value="${templateBean.lunchDate}"  onfocus="WdatePicker()"/>
						</td>
						<td colspan="2" align="center">
							宾客&nbsp;&nbsp;<input class="inputBorder" style="width:50px;" type="text" name="templateBean.lunchGuestNum" value="${templateBean.lunchGuestNum}"  /> 人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;陪同&nbsp;&nbsp;<input class="inputBorder" type="text" style="width:50px;" name="templateBean.lunchGuideNum" value="${templateBean.lunchGuideNum}"  /> 人
						</td>
					</tr>
					<tr>
						<td class="td_title" >(晚餐)用餐时间</td>
						<td >
							<input class="Wdate inputBorder"  type="text" name="templateBean.dinnerDate" value="${templateBean.dinnerDate}"  onfocus="WdatePicker()"/>
						</td>
						<td colspan="2" align="center">
							宾客&nbsp;&nbsp;<input class="inputBorder" style="width:50px;" type="text" name="templateBean.dinnerGuestNum" value="${templateBean.dinnerGuestNum}"  /> 人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;陪同&nbsp;&nbsp;<input class="inputBorder" type="text" style="width:50px;" name="templateBean.dinnerGuideNum" value="${templateBean.dinnerGuideNum}"  /> 人
						</td>
					</tr>
					<tr>
						<td  class="td_title">餐厅</td>
						<td>
							<s:select  list="#request.restaurants" listValue="value" listKey="key" emptyOption="true" 
								name="templateBean.dinnerPlace"></s:select>
						</td>
						<td  class="td_title">用餐标准</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.dinnerStandard" value="${templateBean.dinnerStandard}"  />
						</td>
					</tr>
					<!--<tr>
						<td  class="td_title">白酒</td>
						<td>
							<s:select  list="#request.wines" listValue="value" listKey="key" emptyOption="true" name="templateBean.wine"></s:select>
						</td>
						<td  class="td_title">数量</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.wineNum" value="${templateBean.wineNum}"  />
						</td>
					</tr>
					--><tr>
						<td  class="td_title">红酒</td>
						<td>
							<s:select  list="#request.redWines" listValue="value" listKey="key" emptyOption="true" name="templateBean.redWine"></s:select>
						</td>
						<td  class="td_title">数量</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.redWineNum" value="${templateBean.redWineNum}"  />
						</td>
					</tr>
					
					<tr>
						<td align="center" class="td_title">娱乐</td>
						<td colspan="3" ><textarea class="inputBorder contentTextArea"  name="templateBean.entertainment">${templateBean.entertainment}</textarea></td>
					</tr>
					<tr>
						<td align="center" class="td_title">备注</td>
						<td colspan="3" ><textarea class="inputBorder contentTextArea"  name="templateBean.remark">${templateBean.remark}</textarea></td>
					</tr>
				</table>
				
				<script type="text/javascript">
					function getHotelStandard(){
						var hotelTypeCd = $('#hotelId').val();
						var _hs = $('#hotelStandardId');
						_hs.empty();
						var url = "${ctx}/res/res-approve-info!getHotelStandard.action";
						$.post(url,{'hotelTypeCd':hotelTypeCd},function(data){
							var data = eval('('+data+')');
							$.each(data,function(i,n){
								var option = '<option value="'+i+'">'+n+'</option>';
								_hs.append(option);
							});
							_hs.val(_hs.attr('tempValue'));
						});
					};
					getHotelStandard();
				</script>
				
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
