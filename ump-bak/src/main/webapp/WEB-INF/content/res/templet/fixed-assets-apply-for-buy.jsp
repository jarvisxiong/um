<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
<!--固定资产申购单-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center" id="fixedMainDiv">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
	<%@ include file="template-var.jsp"%>
	 <%--不自动设置项目编号 --%>
     <input type="hidden" id="isAutosetProject" value="0"/>
     <%--是否允许用户勾选合同台账 --%>
     <input type="hidden" id="isShowChkBox" value="0"/>
     
     <%--办公资产采购审批表（适用于地产总部）, 办公资产采购审批表（适用于地产公司） 两采购单不使用合同文本库--%>
     <s:if test="authTypeCd != 'XZRL_XZGL_140' && authTypeCd != 'XZRL_XZGL_141'">
	</s:if>
	<%--合同文本引用 end --%>
	<s:if test="authTypeCd=='XZRL_XZGL_141'">
	<table  class="mainTable" style="margin-bottom: 5px;">
					<col width="80"/>
					<col />
					<tr>
					<td></td>
					<td class="chkGroup" align="left" validate="required">
					<table class="tb_checkbox">
						<col width="130">
						<col width="130">
						<tr>
						<td><s:checkbox name="templateBean.standard" cssClass="group"></s:checkbox>标准</td>
						<td><s:checkbox name="templateBean.nostandard" cssClass="group"></s:checkbox>非标准</td>
						</tr>
					</table>
					</td>
					</tr>
		</table>
		</s:if>
		<table class="mainTable">
			<col width="80">
			<col>
			<col width="80">
			<col>
			<tr>
				<td class="td_title">申购日期</td>
				<td>
					<input validate="required" class="inputBorder Wdate" type="text" name="templateBean.applyDate" value="${templateBean.applyDate}" onfocus="WdatePicker()"/>
				</td>
				<td class="td_title">需用日期</td>
				<td>
					<input validate="required"  class="inputBorder Wdate" type="text" name="templateBean.needDate" value="${templateBean.needDate}" onfocus="WdatePicker()"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">申购总量</td>
				<td colspan="3" align="left">
					<input validate="required" id="applyNumber" class="inputBorder" style="width:100px;" type="text" name="templateBean.applyNumber" value="${templateBean.applyNumber}" onblur="formatNum($(this));validateNumber($(this));" />
					<span>其中金额≥500元的资产数量</span>
					<input validate="required" id="applyNumber2" class="inputBorder" style="width:100px;" type="text" name="templateBean.applyNumber2" value="${templateBean.applyNumber2}" onblur="formatNum($(this));validateNumber($(this));autoAddItem($(this));" />
					
				</td>
			</tr>
			<tr>
				<td class="td_title">申购部门</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.applyDept" value="${templateBean.applyDept}"/>
				</td>
				<td class="td_title">申请人</td>
				<td>
					<input validate="required"  class="inputBorder" type="text" name="templateBean.applyUser" value="${templateBean.applyUser}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">金额(元)</td>
				<td colspan="3">
					<input class="inputBorder" validate="required"  onblur="formatVal($(this));" type="text" name="templateBean.totalMoney" value="${templateBean.totalMoney}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">申购设备及<br/>配置需求</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.applyContent" rows="6" cols="30">${templateBean.applyContent}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">申购理由</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.reason" rows="6" cols="30">${templateBean.reason}</textarea>
				</td>
			</tr>
		</table>
		
		<%--‘商业总部’和‘商业公司’的表单；显示资产录入 --%>
		<s:if test="authTypeCd=='BLSY_XZRL_55' || authTypeCd=='SYGS_XZRL_50'">
		<!-- 总记录数 -->
		<s:set var="conItemCount"><s:property value="templateBean.fixedAssetsProperties.size()"/></s:set>
		
		<!-- 模板 -->
		<s:if test="statusCd==0 || statusCd==3">
		<table class="mainTable itemTable" id="trItemTmplate" style="margin-top: 20px;">
			<col width="90px">
			<col />
			<col width="100px">
			<col />
			<tr>
				<td class="td_title">商业公司</td>
				<td style="border-top: none;" colspan="3">
					<input class="inputBorder projectName" 
						   validate="required" 
						   type="text" 
						   readonly="readonly"
					       style="float:left;width: 94%;"
					       id="projectName0" 
					       name="templateBean.fixedAssetsProperties[0].projectName" 
					/>
					<input type="hidden" class="projectCd" id="projectCd0" name="templateBean.fixedAssetsProperties[0].projectCd"/>
					<%--
					<img style="float:right;cursor:pointer;" 
						 title="删除本资产" 
						 class="deleteItemImg"
						 onclick="delItem(this)" 
						 imgorder='0'
						 src="${ctx}/resources/images/common/del_22_22.gif" 
						 border="0"
					/>
					 --%>
				</td>
			</tr>
			<tr>
				<td class="td_title">资产名称</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.fixedAssetsProperties[0].assmName"/>
				</td>
				<td class="td_title">资产编码</td>
				<td>
					<input class="inputBorder code" validate="required" type="text" name="templateBean.fixedAssetsProperties[0].code"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">设备型号</td>
				<td>
					<input id="assmModelName0" 
					       name="templateBean.fixedAssetsProperties[0].assmModelName" 
						   class="inputBorder assmModelName" 
					       validate="required" 
					       type="text" 
					       readonly="readonly" 
					       style="cursor:pointer;" 
					       title="点击选择设备型号"
					 />
					 <input type="hidden" class="assmModelId" id="assmModelId0" name="templateBean.fixedAssetsProperties[0].assmModelId"/>
				</td>
				<td class="td_title">设备编码</td>
				<td>
					<input class="inputBorder assmCode" validate="required" type="text" readonly="readonly" id="assmCode0" name="templateBean.fixedAssetsProperties[0].assmCode"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">使用部门</td>
				<td>
					<input type="text" 
						   class="inputBorderNoTip useDepartName"						       
						   style="cursor: pointer;"
					       validate="required" 
						   title="点击选择部门"
						   readonly="readonly"
						   id="useDepartName0"
						   name="templateBean.fixedAssetsProperties[0].useDepartName"
					/>
					<input type="hidden" class="useDepartCd" id="useDepartCd0" name="templateBean.fixedAssetsProperties[0].useDepartCd"/>
				</td>
				<td class="td_title">使用时间</td>
				<td>
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.fixedAssetsProperties[0].useDate"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">使用情况</td>
				<td align="left" validate="required" colspan="3" id="useStatus0">
					<s:checkbox name="templateBean.fixedAssetsProperties[0].useStatus1"></s:checkbox>正常使用
					<s:checkbox name="templateBean.fixedAssetsProperties[0].useStatus2"></s:checkbox>库存
					<%--
						采购表单不要报废状态 
						<s:checkbox name="templateBean.fixedAssetsProperties[0].useStatus3"></s:checkbox>报废
					--%>
					<s:checkbox name="templateBean.fixedAssetsProperties[0].useStatus4"></s:checkbox>经营租出
					<s:checkbox name="templateBean.fixedAssetsProperties[0].useStatus5"></s:checkbox>公用
				</td>
			</tr>
			<tr>
				<td class="td_title">增加方式</td>
				<td align="left" validate="required" colspan="3" id="addWay0">
					<s:checkbox name="templateBean.fixedAssetsProperties[0].addWay1" ></s:checkbox>购入
					<s:checkbox name="templateBean.fixedAssetsProperties[0].addWay2"></s:checkbox>地产转入
					<s:checkbox name="templateBean.fixedAssetsProperties[0].addWay3"></s:checkbox>其他商业公司转入
					<s:checkbox name="templateBean.fixedAssetsProperties[0].addWay4"></s:checkbox>租入
					<s:checkbox name="templateBean.fixedAssetsProperties[0].addWay5"></s:checkbox>捐赠
					<s:checkbox name="templateBean.fixedAssetsProperties[0].addWay6"></s:checkbox>地产借入
					<s:checkbox name="templateBean.fixedAssetsProperties[0].addWay7"></s:checkbox>商业集团调拨
				</td>
			</tr>
			<tr>
				<td class="td_title">存放地点</td>
				<td align="left" validate="required" colspan="3" id="storageSites0">
					<s:checkbox name="templateBean.fixedAssetsProperties[0].storageSites1"></s:checkbox>办公区
					<s:checkbox name="templateBean.fixedAssetsProperties[0].storageSites2"></s:checkbox>仓库
					<s:checkbox name="templateBean.fixedAssetsProperties[0].storageSites3"></s:checkbox>城市广场
				</td>
			</tr>
			<tr>
				<td class="td_title">保管人员(PD)</td>
				<td>
					<input class="inputBorderNoTip" validate="required" readonly="readonly" type="text" id="keeperName0" name="templateBean.fixedAssetsProperties[0].keeperName" style="cursor: pointer;"/>
					<input type="hidden" id="keeperCd0" name="templateBean.fixedAssetsProperties[0].keeperCd"/>
					<%-- 
						如果选中设备型号的第三级父类是‘电脑’类，则一个保管人员只能保 管
					 	 一个电脑类的设备，如果超过一个设备，保存时需提示用户(但仍然可保存)
					--%>
					<input class="computerType" type="hidden" id="computerType0" name="templateBean.fixedAssetsProperties[0].computerType" value="${computerModelName}"/>
				</td>
				<td class="td_title">保管人员(非PD)</td>
				<td>
					<input class="inputBorderNoTip keeperName2" validate="" type="text" id="keeperName20" name="templateBean.fixedAssetsProperties[0].keeperName2" title="请输入无PD账号的保管人员"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">原值</td>
				<td>
					<input class="inputBorder srcValue" validate="required" type="text" id="srcValue0" name="templateBean.fixedAssetsProperties[0].srcValue" maxlength="15"/>
				</td>
				<td class="td_title">净值</td>
				<td>
					<input class="inputBorder netValue" validate="required" type="text" readonly="readonly" id="netValue0" name="templateBean.fixedAssetsProperties[0].netValue"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">折旧年限</td>
				<td>
					<input class="inputBorder" validate="required" type="text" readonly="readonly" id="depreYeadNum0" name="templateBean.fixedAssetsProperties[0].depreYeadNum"/>
				</td>
				<td class="td_title">折旧方式</td>
				<td>
					<input class="inputBorder" validate="required" type="text" readonly="readonly" id="depreWay0" name="templateBean.fixedAssetsProperties[0].depreWay"/>
				</td>
			</tr>
			<tr>
			   <td class="td_title">当前配置数</td>
				<td>
					<%--选择设备型号时带出的3级设备的ID，用于判断当前配置数 --%>
					<input class="selectThridModelId" type="hidden" id="selectThridModelId0" name="templateBean.fixedAssetsProperties[0].selectThridModelId"/>
					<input class="inputBorderNoTip currHasNum" valu="0" validate="required" type="text" readonly="readonly" id="currHasNum0" name="templateBean.fixedAssetsProperties[0].currHasNum"/>
				</td>
				<td class="td_title">标准配置数</td>
				<td>
					<input class="inputBorderNoTip currStanNum" validate="required" type="text" readonly="readonly" id="currStanNum0" name="templateBean.fixedAssetsProperties[0].currStanNum"/>
				</td>
			</tr>
			<tr id="tipsTr0" class="tipsTr" style="display: none;">
				<td></td>
				<td colspan="3" align="left"><font style="color: red;"><span id="tipSpan0" class="tipSpan"></span></font></td>
			</tr>
		</table>
		</s:if>
		
		<!-- 列表 -->
		<s:if test="templateBean.fixedAssetsProperties.size()>0">
		<s:iterator value="templateBean.fixedAssetsProperties" var="item" status="s">
		<table class="mainTable itemTable" style="margin-top: 10px;" id="listItem<s:property value="#s.index" />">
			<col width="90px">
			<col />
			<col width="100px">
			<col />
			<tr>
				<td class="td_title">商业公司</td>
				<td style="border-top: none;" colspan="3">
					<input type="text" 
						   class="inputBorder" 
						   validate="required" 
						   readonly="readonly"
						   <s:if test="statusCd==0||statusCd==3">
					     	  style="float:left;width: 93%;" 
					       </s:if>
					       <s:else>
					       	  style="float:left;width: 100%;" 
					       </s:else>
					       id="projectName<s:property value="#s.index" />"
						   name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].projectName" 
					       value="${item.projectName}"
					/>
					<input type="hidden" id="projectCd<s:property value="#s.index" />" name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].projectCd" value="${item.projectCd}"/>
					<%--
					<s:if test="statusCd==0||statusCd==3">
						<img style="float:right;cursor:pointer;" 
							 title="删除本资产" 
							 class="deleteItemImg"
							 onclick="delItemConfirm(this)" 
							 imgorder='<s:property value="#s.index" />'
							 src="${ctx}/resources/images/common/del_22_22.gif" 
							 border="0"
						/>
					</s:if>
					 --%>
				</td>
			</tr>
			<tr>
				<td class="td_title">资产名称</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].assmName" value="${item.assmName}"/>
				</td>
				<td class="td_title">资产编码</td>
				<td>
					<input class="inputBorder" 
					       validate="required" 
					       type="text" id="code" 
					       name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].code" 
					       value="${item.code}"
					       <s:if test="statusCd==0||statusCd==3">
						   		onblur="checkCode(this,'<s:property value="#s.index" />','type');"
					       </s:if>
					/>
				</td>
			</tr>
			<tr>
				<td class="td_title">设备型号</td>
				<td>
					<input class="inputBorder" 
					       validate="required" 
					       type="text" 
					       readonly="readonly" 
					       <s:if test="statusCd==0||statusCd==3">
					       		title="点击选择设备型号"
					       		style="cursor: pointer;"
					       		onclick="doAssmModel('<s:property value="#s.index" />');" 
					       </s:if>
					       id="assmModelName<s:property value="#s.index" />" 
					       name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].assmModelName" 
					       value="${item.assmModelName}"/>
					<input type="hidden" id="assmModelId<s:property value="#s.index" />" name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].assmModelId" value="${item.assmModelId}"/>
				</td>
				<td class="td_title">设备编码</td>
				<td>
					<input class="inputBorder" validate="required" type="text" id="assmCode<s:property value="#s.index" />" name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].assmCode" value="${item.assmCode}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">使用部门</td>
				<td>
					<input type="text" 
						   class="inputBorderNoTip"
					       validate="required" 
						   readonly="readonly"
						   <s:if test="statusCd==0||statusCd==3">
							   title="点击选择部门"
						       style="cursor: pointer;"
					       </s:if>
						   id="useDepartName<s:property value="#s.index" />"
						   name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].useDepartName" 
						   value="${item.useDepartName}"
					/>
					<input type="hidden" id="useDepartCd<s:property value="#s.index" />" name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].useDepartCd" value="${item.useDepartCd}"/>
				</td>
				<td class="td_title">使用时间</td>
				<td>
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].useDate" value="${item.useDate}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">使用情况</td>
				<td class="chkGroup" validate="required" colspan="3" align="left">
					<s:checkbox name="templateBean.fixedAssetsProperties[%{#s.index}].useStatus1" cssClass="group"></s:checkbox>正常使用
					<s:checkbox name="templateBean.fixedAssetsProperties[%{#s.index}].useStatus2" cssClass="group"></s:checkbox>库存
					<%--
						采购表单不要报废状态 
						<s:checkbox name="templateBean.fixedAssetsProperties[%{#s.index}].useStatus3" cssClass="group"></s:checkbox>报废
					--%>
					<s:checkbox name="templateBean.fixedAssetsProperties[%{#s.index}].useStatus4" cssClass="group"></s:checkbox>经营出租
					<s:checkbox name="templateBean.fixedAssetsProperties[%{#s.index}].useStatus5" cssClass="group"></s:checkbox>公用
				</td>
			</tr>
			<tr>
				<td class="td_title">增加方式</td>
				<td class="chkGroup" validate="required" colspan="3" align="left">
					<s:checkbox name="templateBean.fixedAssetsProperties[%{#s.index}].addWay1" cssClass="group"></s:checkbox>购入
					<s:checkbox name="templateBean.fixedAssetsProperties[%{#s.index}].addWay2" cssClass="group"></s:checkbox>地产转入
					<s:checkbox name="templateBean.fixedAssetsProperties[%{#s.index}].addWay3" cssClass="group"></s:checkbox>其他商业公司转入
					<s:checkbox name="templateBean.fixedAssetsProperties[%{#s.index}].addWay4" cssClass="group"></s:checkbox>租入
					<s:checkbox name="templateBean.fixedAssetsProperties[%{#s.index}].addWay5" cssClass="group"></s:checkbox>捐赠
					<s:checkbox name="templateBean.fixedAssetsProperties[%{#s.index}].addWay6" cssClass="group"></s:checkbox>地产借入
					<s:checkbox name="templateBean.fixedAssetsProperties[%{#s.index}].addWay7" cssClass="group"></s:checkbox>商业集团调拨
				</td>
			</tr>
			<tr>
				<td class="td_title">存放地点</td>
				<td class="chkGroup" align="left" colspan="3" validate="required">
					<s:checkbox name="templateBean.fixedAssetsProperties[%{#s.index}].storageSites1" cssClass="group"></s:checkbox>办公区
					<s:checkbox name="templateBean.fixedAssetsProperties[%{#s.index}].storageSites2" cssClass="group"></s:checkbox>仓库
					<s:checkbox name="templateBean.fixedAssetsProperties[%{#s.index}].storageSites3" cssClass="group"></s:checkbox>城市广场
				</td>
			</tr>
			<tr>
				<td class="td_title">保管人员(PD)</td>
				<td>
					<input class="inputBorderNoTip" 
					       readonly="readonly" 
					       type="text" 
					       <s:if test="statusCd==0||statusCd==3">
						       style="cursor: pointer;" 
						       title="点击选择保管人员"
					       </s:if>
					       id="keeperName<s:property value="#s.index" />" 
					       name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].keeperName" 
					       value="${item.keeperName}"
					/>
					<input type="hidden" id="keeperCd<s:property value="#s.index" />" name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].keeperCd" value="${item.keeperCd}"/>
					
					<input type="hidden" id="computerType<s:property value="#s.index" />" name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].computerType" value="${item.computerType}"/>
				</td>
				<td class="td_title">保管人员(非PD)</td>
				<td>
					<input class="inputBorderNoTip" 
					       validate="" 
					       type="text" 
					       <s:if test="statusCd==0||statusCd==3">
						       title="请输入无PD账号的保管人员"
						       onblur="clearCheckKeeper($(this),'<s:property value="#s.index" />');"
					       </s:if>
					       id="keeperName2<s:property value="#s.index" />" 
					       name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].keeperName2" 
					       value="${item.keeperName2}"
					/>
				</td>
			</tr>
			<tr>
				<td class="td_title">原值</td>
				<td>
					<input class="inputBorder" 
				           validate="required" 
				           type="text" 
				           maxlength="15"
				           <s:if test="statusCd==0||statusCd==3">
					           onblur="formatVal($(this));formatNetValue(<s:property value="#s.index" />);getNetValue(this,'<s:property value="#s.index" />');" 
					           onkeyup="getNetValue2(this,'<s:property value="#s.index" />');" 
				           </s:if>
				           id="srcValue<s:property value="#s.index" />" 
				           name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].srcValue" 
				           value="${item.srcValue}"/>
				</td>
				<td class="td_title">净值</td>
				<td>
					<input class="inputBorder" validate="required" type="text" readonly="readonly" id="netValue<s:property value="#s.index" />"  name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].netValue" value="${item.netValue}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">折旧年限</td>
				<td>
					<input class="inputBorder" validate="required" type="text" readonly="readonly" id="depreYeadNum<s:property value="#s.index" />"  name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].depreYeadNum" value="${item.depreYeadNum}"/>
				</td>
				<td class="td_title">折旧方式</td>
				<td>
					<input class="inputBorder" validate="required" type="text" readonly="readonly" id="depreWay<s:property value="#s.index" />"  name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].depreWay" value="${item.depreWay}"/>
				</td>
			</tr>
			<tr>
			   <td class="td_title">当前配置数</td>
				<td>
					<input class="selectThridModelId" type="hidden" id="selectThridModelId<s:property value="#s.index" />"  name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].selectThridModelId" value="${item.selectThridModelId}"/>
					<input class="inputBorderNoTip currHasNum" valu="<s:property value="#s.index" />" validate="required" type="text" readonly="readonly" id="currHasNum<s:property value="#s.index" />"  name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].currHasNum" value="${item.currHasNum}"/>
				</td>
				<td class="td_title">标准配置数</td>
				<td>
					<input class="inputBorderNoTip" validate="required" type="text" readonly="readonly" id="currStanNum<s:property value="#s.index" />"  name="templateBean.fixedAssetsProperties[<s:property value="#s.index" />].currStanNum" value="${item.currStanNum}"/>
				</td>
			</tr>
			<tr id="tipsTr<s:property value="#s.index" />" class="tipsTr" style="display: none;">
				<td></td>
				<td colspan="3" align="left"><font style="color: red;"><span id="tipSpan<s:property value="#s.index" />" class="tipSpan"></span></font></td>
			</tr>
		</table>
		</s:iterator>
		</s:if>
		
		<div id="listTable"></div>
		<%--
		<s:if test="statusCd==0||statusCd==3">
			<input type="button" class="btn_blue_75_55" style="border:none;margin: 10px 0 20px 0"  value="增加资产" onclick="addItem();" />
		</s:if>
		 --%>
		</s:if>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>

<%@ include file="template-footer.jsp"%>

<%--存放商业公司 --%>
<input type="hidden" id="projectName"/>
<input type="hidden" id="projectCd"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css" />

<script type="text/javascript">

var trApprover=$("#trItemTmplate").clone();
$("#trItemTmplate").remove();

var index=${conItemCount};

$(function(){
	//是否回执标准库选择回调函数
    var isCallChooseCont=true;
    if(typeof(userCont) == "function"){	//是否有选择合同库
		if($("#contlib").attr("checked") == true || $("#contlib").attr("checked") == "checked"){
			userCont('true');
		}else{
			userCont('false');
		}
    }
	reloadProject();
	<s:if test="statusCd==0||statusCd==3">
		if(index > 0){
			for(var i=0;i<index;i++){
				$("#keeperName"+i).userSelect({muti:false,nameField:"keeperName"+i,cdField:"keeperCd"+i,callfun:"checkKeeper(this,"+i+",'type')"});
				$("#useDepartName"+i).orgSelect({});
			}
		}
	</s:if>
});


function addItem(){
	var cloneCount = $('.itemTable').length;
	var trApprover_new = trApprover.clone();
	cloneCount++;
	$(trApprover_new).show();
	$(trApprover_new).attr("id",('listItem'+index));
	$(trApprover_new).find(":input").each(function(i){
		this.name=this.name.replace('0',index);
		this.id= this.id.replace('0',index);
	});
	$(trApprover_new).find("img").each(function(i){
		$(this).attr("imgorder",index);
	});
	$(trApprover_new).find("#tipsTr0").each(function(i){
		this.id= this.id.replace('0',index);
	});
	$(trApprover_new).find("#tipSpan0").each(function(i){
		this.id= this.id.replace('0',index);
	});
	$(trApprover_new).find(".currHasNum").each(function(i){
		$(this).attr('valu',index);
	});

	var projectNameDom = $(trApprover_new).find(".projectName");
	var projectCdDom = $(trApprover_new).find(".projectCd");
	projectNameDom.val($("#projectName").val());
	projectCdDom.val($("#projectCd").val());
	
	var assmModelNameDom = $(trApprover_new).find(".assmModelName");
	var indx = index;
	assmModelNameDom.bind("click",function(){
		doAssmModel(indx);
	});
	var srcValueDom = $(trApprover_new).find(".srcValue");
	srcValueDom.bind("keyup",function(){
		getNetValue2(this,indx);
	});
	srcValueDom.bind("blur",function(){
		getNetValue(this,indx);
		formatVal($(this));
		formatNetValue(indx);
	});
	
	var codeDom = $(trApprover_new).find(".code");
	codeDom.bind("blur",function(){
		checkCode(this,indx);
	});
	var keeperName = $(trApprover_new).find("#keeperName0");
	var keeperName2Dom = $(trApprover_new).find(".keeperName2");
	keeperName2Dom.bind("blur",function(){
		clearCheckKeeper($(this),indx);
	});
	
	$("#listTable").append(trApprover_new);
	
	$("#useStatus"+indx+" input[type='checkbox']").attr('checked',false);
	$("#addWay"+indx+" input[type='checkbox']").attr('checked',false);
	$("#storageSites"+indx+" input[type='checkbox']").attr('checked',false);
	
	//checkbox选择由于样式控制失效，改为脚本控制
	$("#useStatus"+indx+" input[type='checkbox']").click(function(){
	     $("#useStatus"+indx+" input[type='checkbox']").attr('checked',false);
	     $(this).attr('checked',true);
	});
	$("#addWay"+indx+" input[type='checkbox']").click(function(){
	     $("#addWay"+indx+" input[type='checkbox']").attr('checked',false);
	     $(this).attr('checked',true);
	});
	$("#storageSites"+indx+" input[type='checkbox']").click(function(){
	     $("#storageSites"+indx+" input[type='checkbox']").attr('checked',false);
	     $(this).attr('checked',true);
	});
	$("#keeperName"+indx).userSelect({muti:false,nameField:'keeperName'+indx,cdField:'keeperCd'+indx,callfun:'checkKeeper($(this),'+indx+',null)'});
	$("#useDepartName"+indx).orgSelect({});
	index++;
	refreshImg();
}

//列表页删除项目带确认删除
function delItemConfirm(dom){
	if (confirm("确认删除该资产?")) {
		delItem(dom);
	}
}
function delItem(dom){
	var imgorder = $(dom).attr("imgorder");
	var selectThridAssmId = $("#selectThridModelId"+imgorder).val();
	$("#fixedMainDiv").find(".selectThridModelId").each(function(i){
		var thridId = $(this).val();
		if(selectThridAssmId == thridId){
			var tmpNum = $(this).next().val().trim();
			if("" != tmpNum){
				var hasNum = tmpNum - 1;
				//重新为当前配置赋值
				$(this).next().val(hasNum);
			}
		}
	});
	//去除、显示提示内容
	for(var i=0;i<index;i++){
		var currHasNum = $("#currHasNum"+i).val();
		var currStanNum = $("#currStanNum"+i).val();
		if(undefined != currHasNum && undefined != currStanNum){
			if(parseInt(currHasNum) > parseInt(currStanNum)){
				$("#tipSpan"+i).html('提示：当前配置数大于标准配置数');
				$("#tipsTr"+i).show();
			}else{
				$("#tipSpan"+i).html('');
				$("#tipsTr"+i).hide();
			}
		}
	}
	$('#listItem'+imgorder).remove();
	refreshImg();
}

function refreshImg(){
	if($('.deleteItemImg').length == 1){
		$('.deleteItemImg').hide();
		$(".projectName").attr("style","float:left;width: 100%");
	}else{
		$('.deleteItemImg').show();
		$(".projectName").attr("style","float:left;width: 94%");
	}
	autoHeight();
}

//加载商业公司
function reloadProject(){
	var url = _ctx+"/assm/assm-account!getProjectCd.action";
	$.post(url,{}, function(result) {
		if(result!=null && ''!=result){
			var str = result.split(',');
			$("#projectName").val(str[0]);
			$("#projectCd").val(str[1]);
			//网批ID
			//var resApproveInfoId = "${resApproveInfoId}";
			// 默认1条记录 
// 			if(resApproveInfoId==null || ""==resApproveInfoId){
// 				addItem();
// 				autoHeight();
// 			}
		}
	});
}

//选择设备型号
function doAssmModel(inde){
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='selectTypeDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 580,
		height : 500,
		title : "设备型号选择",
		afterShow : function() {
			var url = _ctx+"/assm/assm-account!getAssmModel.action";
			$.post(url, {}, function(result) {
				$("#selectTypeDiv").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				//选中的三级设备名称(用于判断‘电脑’类的设备保管人只能有一个)
				var selectTwoAssmName = $('#selectThridAssmName').val();
				var selectThridAssmId = $('#selectThridAssmId').val();
				$("#computerType"+inde).val(selectTwoAssmName);
				
				//选中的第四级设备ID、设备名称
				var selectAssmModelId = $('#selectAssmModelId').val();
				var selectAssmModelName = $('#selectAssmModelName').val();
				var projectCd = $('#projectCd'+inde).val();
				if(isEmpty(selectAssmModelId)){
					alert('请选择四级设备');
					return false;
				}
				var data ={
					assmModelId:selectAssmModelId,
					projectCd:projectCd
				};
				//根据设备型号自动带出型号编码、当前配置、标准配置等
				var url1 = _ctx+"/assm/assm-account!getValues.action";
				$.post(url1,data, function(result) {
					$("#assmModelId"+inde).val(selectAssmModelId);
					$("#assmModelName"+inde).val(selectAssmModelName);

					//这个很重要 ：文本框值还没有改变值前的值
					var oldSelectThridAssmId = $("#selectThridModelId"+inde).val();

					$("#assmCode"+inde).val('');
					$("#currStanNum"+inde).val('');
					$("#currHasNum"+inde).val('');
					$("#depreYeadNum"+inde).val('');
					$("#depreWay"+inde).val('');
					$("#selectThridModelId"+inde).val('');
					if(result != null && "" != result){
						if("error"==result){
							alert("该商业公司未在资产台账中维护");
						}else{
							var rs=result.split(",");
							var thridCount = 0;
							$("#fixedMainDiv").find(".selectThridModelId").each(function(i){
								var thridId = $(this).val();
								if(selectThridAssmId == thridId){
									//统计选中的当前设备型号的以增加的资产数
									thridCount++;
								}
							});

							//标准配置数
							var currStanNum = parseInt(rs[2]);
							//当前配置数 = 数据库中已经的数量 + 当前本身资产 + 已在表单中增加的相同型号的资产数
							var currHasNum = parseInt(rs[3]) + parseInt(1) + parseInt(thridCount); 

							$("#selectThridModelId"+inde).val(selectThridAssmId);
							$("#assmCode"+inde).val(rs[0]);
							$("#currStanNum"+inde).val(currStanNum);
							$("#currHasNum"+inde).val(currHasNum);
							$("#depreYeadNum"+inde).val(rs[4]);
							$("#depreWay"+inde).val(rs[5]);
							$("#fixedMainDiv").find(".selectThridModelId").each(function(i){
								var thridId2 = $(this).val();
								if(selectThridAssmId == thridId2){
									$(this).next().val(currHasNum);
								}else{
									if(oldSelectThridAssmId == thridId2){
										var tmpNum = $(this).next().val().trim();
										if("" != tmpNum){
											var hasNum = tmpNum - 1;
											//重新为当前配置赋值
											$(this).next().val(hasNum);
										}
									}
								}
							});
							//if(parseInt(currHasNum) > parseInt(currStanNum)){
							for(var i=0;i<index;i++){
								var tmpCurrHasNum = $("#currHasNum"+i).val();
								var tmpCurrStanNum = $("#currStanNum"+i).val();
								if(undefined != tmpCurrHasNum && undefined != tmpCurrStanNum){
									if(parseInt(tmpCurrHasNum) > parseInt(tmpCurrStanNum)){
										$("#tipSpan"+i).html('提示：当前配置数大于标准配置数');
										$("#tipsTr"+i).show();
									}else{
										$("#tipSpan"+i).html('');
										$("#tipsTr"+i).hide();
									}
								}
							}
							//$("#tipSpan"+index).html('提示：当前配置数大于标准配置数');
							//$("#tipsTr"+index).show();
							//}
						}
					}
				});
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["退出",'cancel']]
	});
}

//校验保管人员(PD)(同一“保管人员”不能拥有两个或两个以上的‘电脑’设备)
function checkKeeper(dom,index_ind,type){
	//电脑类特殊判断
	var computerType = $("#computerType"+index_ind).val();
	var keeperCd = $("#keeperCd"+index_ind).val();
	if("电脑" == computerType){
		var url = _ctx+"/assm/assm-account!checkKeeper.action";
		$.post(url,{keeperCd:keeperCd}, function(result) {
			if(result=='true'){
				alert("提示：您选中的保管人员已经保管有电脑设备!");
			}else{
				var count = 0;
				if(type!=null && type!=''){
					//list列表总数
					count = index;
				}else{
					//新增模板列表总数
					count = index_ind;
				}	
				if(count > 0){
					for(var i=0;i<count;i++){
						var keepCd = $("input[name='templateBean.fixedAssetsProperties["+i+"].keeperCd']").val();
						if(i!=index_ind){
							if($.trim(keepCd) !='' && keeperCd==keepCd){
								setTimeout("alert('提示：您选中的保管人员已经保管有电脑设备!')",500);
								return false;
							}
						}
					}
				}
			}
		});
	}
	//去除‘非PD输入框’的必输项验证
	if(keeperCd!=null && $.trim(keeperCd)!=''){
		$("#keeperName2"+index_ind).val('');
		$("#keeperName2"+index_ind).removeAttr('validate');
		$("#keeperName2"+index_ind).attr({'class':"inputBorderNoTip"});
		$("#keeperName"+index_ind).attr({validate:"required",'class':"inputBorder"});
	}else{
		$("#keeperName"+index_ind).removeAttr('validate');
		$("#keeperName"+index_ind).attr({'class':"inputBorderNoTip"});
		$("#keeperName2"+index_ind).attr({validate:"required",'class':"inputBorder"});
	}
}

//非PD保管人
function clearCheckKeeper(dom,index_ind){
	if($.trim(dom.val())!=''){
		$("#keeperCd"+index_ind).val('');
		$("#keeperName"+index_ind).val('');
		var	_userMap = {};
		var o = {userName:'',uiid:''};
		_userMap[$("#keeperName"+index_ind).val()] = o;
		var data = $.extend(true,{},null);
		$("#keeperName"+index_ind).data('userMap',data);

		$("#keeperName"+index_ind).removeAttr('validate');
		$("#keeperName"+index_ind).attr({'class':"inputBorderNoTip"});
		$("#keeperName2"+index_ind).attr({validate:"required",'class':"inputBorder"});
	}else{
		$("#keeperName2"+index_ind).removeAttr('validate');
		$("#keeperName2"+index_ind).attr({'class':"inputBorderNoTip"});
		$("#keeperName"+index_ind).attr({validate:"required",'class':"inputBorder"});
	}
}

//校验资产编码唯一性
function checkCode(dom,index_ind,type){
	if($.trim(dom.value)!=''){
		var url = _ctx+"/assm/assm-account!checkCode.action";
		$.post(url,{code:$.trim(dom.value)}, function(result) {
			if(result=='true'){
				alert("资产编码已经存在!");
				$(dom).val('');
				return false;
			}else{
				var count = 0;
				if(type!=null && type!=''){
					//list列表总数
					count = index;
				}else{
					//新增模板列表总数
					count = index_ind;
				}
				if(count > 0){
					for(var i=0;i<count;i++){
						var code = $("input[name='templateBean.fixedAssetsProperties["+i+"].code']").val();
						if(i!=index_ind){
							if(dom.value==code){
								alert("资产编码已经存在!");
								$(dom).val('');
								return false;
							}
						}
					}
				}
			}
		});
	}
}

function getNetValue(dom,index){
	var value = dom.value;
	if(value!='' && value < 500){
		alert('原值必须大于500!');
		$(dom).val('');
		$("#netValue"+index).val('');
		$(dom).focus();
		return false;
	}else{
		$("#netValue"+index).val(value);
	}
}
function getNetValue2(dom,index){
	$("#netValue"+index).val(dom.value);
}

function formatNetValue(index){
	formatVal($("#netValue"+index));
}
//自动创建条目
function autoAddItem(dom){
	$('.itemTable').remove();
	var cnt=getValByJ(dom);
	if (cnt>30){
		alert('数值不能超过30');
		$('#applyNumber2').val('');
		return;
	}
	for(var i=0;i<cnt;i++){
		addItem();
	}
}
function validateNumber(_dom){
	var val1=$("#applyNumber").val();
	var val2=$("#applyNumber2").val();
	if (isNotEmpty(val1)&&isNotEmpty(val2)){
		if (parseFloat(val1) < parseFloat(val2)){
			alert('申购总量不能小于金额≥500元的资产数量');
			_dom.val('');
		}
	}
}
</script>
	