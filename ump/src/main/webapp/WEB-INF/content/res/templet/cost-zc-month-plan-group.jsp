<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--成本月招采计划(集团)-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	
	<!-- 设计管理中心/项目管理中心nodeCd==153||nodeCd==154 -->
	<!-- 成本控制中心总经理(nodeCd==13)/成本控制中心综合部(nodeCd==89)/成本控制中心造价部(nodeCd==92)/成本控制中心经营采购部(商业、酒店)(nodeCd==93)
	/成本控制中心(nodeCd==156)/成本控制中心招采负责人(nodeCd==250)/成本控制中心招标部(nodeCd==259)/成本控制中心经营采购部(nodeCd==267)/成本控制中心采购部(nodeCd==275) -->
	
	<!-- 设计管理中心、项目管理中心、成本控制中心相关负责人，都可以编辑 -->
	<s:set var="canEdit" value="((nodeCd == 13 || nodeCd == 89 || nodeCd == 92 || nodeCd == 93
		 || nodeCd == 156 || nodeCd == 250 || nodeCd == 259 || nodeCd == 167 || nodeCd == 175)
		 || nodeCd==153 || nodeCd==154)" />
		 
	<!-- 各成本控制中心负责人 -->
	<s:if test="(((nodeCd == 13 || nodeCd == 89 || nodeCd == 92 || nodeCd == 93
		 || nodeCd == 156 || nodeCd == 250 || nodeCd == 259 || nodeCd == 167 || nodeCd == 175)
		 || nodeCd==153 || nodeCd==154) || nodeCd == 129) && isMyApprove==1">
		<s:hidden id="isEdit" value="true"/>
	</s:if>
	
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
 
		<!-- 总记录数 -->
		<s:set var="conItemCount"><s:property value="templateBean.planProperties.size()"/></s:set>
		
		<table class="mainTable">
		 <col width="130px">
			<col />
		 <tr>
		  <td class="td_title">类别</td>
			<td class="chkGroup" align="left"  validate="required">
				<div style="float:left;">
					<s:checkbox name="templateBean.bidType"  cssClass="group"></s:checkbox>招标
					<s:checkbox name="templateBean.purcType"  cssClass="group"></s:checkbox>采购
					<s:checkbox name="templateBean.marketType"  cssClass="group"></s:checkbox>营销
				</div>
			</td>
		 </tr>
		</table>
		<!-- 模板 -->
		<s:if test="statusCd==0 || statusCd==3">
		<table class="mainTable" id="trItem" style="display:none;margin-top: 10px;">
			<col width="130px">
			<col />
			<col width="120px">
			<col />
			<tr>
				<td  class="td_title">项目</td>
				<td colspan="2">
					<input class="inputBorderNoTip projectName" 
					validate="required" 
					type="text" name="templateBean.planProperties[0].projectName" 
					onclick="initSelectFlag($(this))"
					readonly="readonly"
					/>
					<input type="hidden" name="templateBean.planProperties[0].projectCd"  />
				</td>
				<td  class="chkGroup" align="left">
				
				
					<img style="float:right;cursor:pointer;" 
						 title="删除本项目" 
						 class="deleteItemImg"
						 onclick="delItem(this)" 
						 imgorder='0'
						 src="${ctx}/resources/images/common/del_22_22.gif" 
						 border="0"
						 />
				</td>
			</tr>
			<tr>
				<td  class="td_title">工程名称</td>
				<td >
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[0].projectDesc"/>
				</td>
				<td  class="td_title">招标范围/采购内容</td>
				<td >
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[0].invPurDesc"/>
				</td>
			</tr>
			<tr>
				<td  class="td_title">质量标准/技术要求</td>
				<td  colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[0].qualityTechDesc"/>
				</td>
			</tr>
			<tr>
				<td  class="td_title">合同性质</td>
				<td  class="chkGroup" align="left"  validate="required">
					<s:checkbox name="templateBean.planProperties[0].contactCharacterCd1"  cssClass="group"></s:checkbox>总价包干
					<s:checkbox name="templateBean.planProperties[0].contactCharacterCd2"  cssClass="group"></s:checkbox>单价包干
					<s:checkbox name="templateBean.planProperties[0].contactCharacterCd3"  cssClass="group"></s:checkbox>费率合同
				</td>
				<td  class="td_title">预计金额(元)</td>
				<td >
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[0].totalPrice" onblur="zcMontyValidate($(this))" title="招标金额在300,000元以内；采购金额在100,000元以内"/>
				</td>
			</tr>
			<tr>
				<td  class="td_title">*工期要求</td>
				<td >
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[0].timeLimitDesc"
					/>
				</td>
				<td  class="td_title">进场时间</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.planProperties[0].enterDate"/>
				</td>
			</tr>
			<tr>
				<td  class="td_title">*技术管理部(出图时间)</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text"  name="templateBean.planProperties[0].pictureDate"/>
				</td>
				<td  class="td_title">*技术管理部(负责人)</td>
				<td >
					<input id="responserName0" style="cursor:pointer;" class="inputBorder responserName" type="text" name="templateBean.planProperties[0].responserName" 
					onclick="initResponserNameFlag($(this))"/>
					<input type="hidden" name="templateBean.planProperties[0].responserCd"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">*定标完成时间</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.planProperties[0].bidCompleteDate"/>
				</td>
				<td class="td_title">垄断</td>
				<td  class="chkGroup" align="left"  validate="required">
					<s:checkbox name="templateBean.planProperties[0].isForestall"  cssClass="group"></s:checkbox>是
					<s:checkbox name="templateBean.planProperties[0].notForestall"  cssClass="group"></s:checkbox>否
				</td>
			</tr>
			
			<%-- 定样完成时间、方案审批时间 -Add by liuzhihui 2012-04-12 --%>
			<tr>
			   <td  class="td_title">定样完成时间</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.planProperties[0].sampleCompleteDate"/>
				</td>
				<td  class="td_title">方案审批时间</td>
				<td>
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.planProperties[0].programApprovalDate"/>
				</td>
			</tr>
			
			<tr>
				<td  class="td_title">备注</td>
				<td  colspan="3">
					<textarea class="inputBorder contentTextArea" name="templateBean.planProperties[0].remark"></textarea>
				</td>
			</tr>
			<%--
			<tr>
				<td  class="td_title">*区域成本意见</td>
				<td  colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.planProperties[0].areaCostOptionDesc"></textarea>
				</td>
			</tr>
			 --%>
		</table>
		</s:if>
		
		
		<!-- 列表 -->
		<s:iterator value="templateBean.planProperties" var="item" status="s">
		<table class="mainTable" style="margin-top: 10px;" id="listItem<s:property value="#s.index" />">
			<col width="130px">
			<col />
			<col width="120px">
			<col />
 			<tr>
				<td class="td_title">项目</td>
				<td colspan="2">
					<input validate="required" type="text" 
					name="templateBean.planProperties[<s:property value="#s.index" />].projectName" 
					value="${item.projectName}" 
					readonly="readonly" 
					class="inputBorderNoTip" 
					<s:if test="#isMy==1"> 
					onclick="initSelectFlag($(this))"
					style="cursor: pointer;" 
					</s:if >
					/>
					<input type="hidden" name="templateBean.planProperties[<s:property value="#s.index" />].projectCd" value="${item.projectCd}"  />
				</td>
				<td  class="chkGroup" align="left">
					<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
						<img style="float:right;cursor:pointer;" 
							 class="deleteItemImg"
							 onclick="delItem(this)" 
							 imgorder='<s:property value="#s.index" />'
							 src="${ctx}/resources/images/common/del_22_22.gif" 
							 border="0"
							 />
					</s:if>
					
				</td>
			</tr>
			<tr>
				<td  class="td_title">工程名称</td>
				<td >
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].projectDesc" value="${item.projectDesc}"/>
				</td>
				<td  class="td_title">招标范围/采购内容</td>
				<td >
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].invPurDesc" value="${item.invPurDesc}"/>
				</td>
			</tr>
			<tr>
				<td  class="td_title">质量标准/技术要求</td>
				<td  colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].qualityTechDesc" value="${item.qualityTechDesc}"/>
				</td>
			</tr>
			<tr>
				<td  class="td_title">合同性质</td>
				<td  class="chkGroup" align="left"  validate="required">
					<s:checkbox name="templateBean.planProperties[%{#s.index}].contactCharacterCd1"  cssClass="group"></s:checkbox>总价包干
					<s:checkbox name="templateBean.planProperties[%{#s.index}].contactCharacterCd2"  cssClass="group"></s:checkbox>单价包干
					<s:checkbox name="templateBean.planProperties[%{#s.index}].contactCharacterCd3"  cssClass="group"></s:checkbox>费率合同
				</td>
				<td  class="td_title">预计金额(元)</td>
				<td >
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].totalPrice" onblur="zcMontyValidate($(this));" value="${item.totalPrice}" title="招标金额在300,000元以内；采购金额在100,000元以内"/>
				</td>
			</tr>
			<tr>
				<td  class="td_title">*工期要求</td>
				<td >
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].timeLimitDesc" value="${item.timeLimitDesc}"
						<s:if test="(((nodeCd == 13 || nodeCd == 89 || nodeCd == 92 || nodeCd == 93
							 || nodeCd == 156 || nodeCd == 250 || nodeCd == 259 || nodeCd == 167 || nodeCd == 175)
							 || nodeCd==153 || nodeCd==154) || nodeCd == 129) && isMyApprove==1">
							edit="true"
						</s:if>
					/>
				</td>
				<td  class="td_title">进场时间</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.planProperties[<s:property value="#s.index" />].enterDate" value="${item.enterDate}"/>
				</td>
			</tr>
			<tr>
				<td  class="td_title">*技术中心(出图时间)</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].pictureDate" value="${item.pictureDate}"
						<s:if test="(((nodeCd == 13 || nodeCd == 89 || nodeCd == 92 || nodeCd == 93
							 || nodeCd == 156 || nodeCd == 250 || nodeCd == 259 || nodeCd == 167 || nodeCd == 175)
							 || nodeCd==153 || nodeCd==154) || nodeCd == 129) && isMyApprove==1">
							edit="true"
						</s:if>
					/>
				</td>
				<td  class="td_title">*技术中心(负责人)</td>
				<td >
					<input id="responserName<s:property value="#s.index" />" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].responserName" value="${item.responserName}" 
							<s:if test="(((nodeCd == 13 || nodeCd == 89 || nodeCd == 92 || nodeCd == 93
								 || nodeCd == 156 || nodeCd == 250 || nodeCd == 259 || nodeCd == 167 || nodeCd == 175)
								 || nodeCd==153 || nodeCd==154) || nodeCd == 129) && isMyApprove==1"> 
								class="inputBorderNoTip" style="cursor: pointer;" 
								onclick="initResponserNameFlag($(this))"
							</s:if>
							<s:else> class="inputBorderNoTip" </s:else>
					/>
					<input type="hidden" name="templateBean.planProperties[<s:property value="#s.index" />].responserCd" value="${item.responserCd}"/>
				</td>
			</tr>
			<tr>
				<td  class="td_title">*定标完成时间</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.planProperties[<s:property value="#s.index" />].bidCompleteDate" value="${item.bidCompleteDate}"
						<s:if test="(((nodeCd == 13 || nodeCd == 89 || nodeCd == 92 || nodeCd == 93
							 || nodeCd == 156 || nodeCd == 250 || nodeCd == 259 || nodeCd == 167 || nodeCd == 175)
							 || nodeCd==153 || nodeCd==154) || nodeCd == 129) && isMyApprove==1">
							edit="true"
						</s:if>
					/>
				</td>
				<td class="td_title">垄断</td>
				<td  class="chkGroup" align="left" >
					<s:checkbox name="templateBean.planProperties[%{#s.index}].isForestall"  cssClass="group"></s:checkbox>是
					<s:checkbox name="templateBean.planProperties[%{#s.index}].notForestall"  cssClass="group"></s:checkbox>否
				</td>
			</tr>
			
			<%-- 定样完成时间、方案审批时间 -Add by liuzhihui 2012-04-12 --%>
			<tr>
			   <td  class="td_title">定样完成时间</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].sampleCompleteDate" value="${item.sampleCompleteDate}"/>
				</td>
				<td  class="td_title">方案审批时间</td>
				<td>
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].programApprovalDate" value="${item.programApprovalDate}"/>
				</td>
			</tr>
			
			<tr>
				<td  class="td_title">备注</td>
				<td  colspan="3">
					<textarea class="inputBorder contentTextArea" name="templateBean.planProperties[<s:property value="#s.index" />].remark">${item.remark}</textarea>
				</td>
			</tr>
			<s:if test="statusCd!=0">
			<tr>
				<td class="td_title">*成本意见</td>
				<td colspan="3">
				<s:if test="((nodeCd == 13 || nodeCd == 89 || nodeCd == 92 || nodeCd == 93
					 || nodeCd == 156 || nodeCd == 250 || nodeCd == 259 || nodeCd == 167 || nodeCd == 175)
					 || nodeCd==153 || nodeCd==154) && isMyApprove==1">
					<textarea class="inputBorder contentTextArea" name="templateBean.planProperties[<s:property value="#s.index" />].areaCostOptionDesc" edit="true">${item.areaCostOptionDesc}</textarea>
				</s:if>
				<s:else>
					<textarea class="inputBorder contentTextArea" readonly="readonly" name="templateBean.planProperties[<s:property value="#s.index" />].areaCostOptionDesc">${item.areaCostOptionDesc}</textarea>
				</s:else>
				</td>
			</tr>
			</s:if>
		</table>
		</s:iterator>
		<div id="listTable">
		</div>
		<s:if test="statusCd==0||statusCd==3">
		<input type="button" class="btn_blue_75_55" value="增加项目" onclick="addItem();" />
		</s:if>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
	
	function initSelectFlag(jDom){
		if( jDom.attr('initselectflg') != '1'){
			jDom.attr('initselectflg','1'); 
			jDom.orgSelect({
				showProjectOrg:true
			});
			jDom.trigger('click');
		}
	}
	function initResponserNameFlag(jDom){
		if( jDom.attr('initselectflg') != '1'){
			jDom.attr('initselectflg','1'); 
			jDom.userSelect({
				muti:false
			});
			jDom.trigger('click');
		}
	}

	var trApprover=$("#trItem").clone();
	$("#trItem").remove();
	var cloneCount = 0;
	var index=${conItemCount};
	
	function addItem(){
		var trApprover_new = trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();

		$(trApprover_new).attr("id",('listItem'+index));
		$(trApprover_new).find(":input").each(function(i){
			this.name=this.name.replace('0',index);
		});
		$(trApprover_new).find("img").each(function(i){
			$(this).attr("imgorder",index);
		});
		
		var projectNameDom = $(trApprover_new).find(".projectName");
		projectNameDom.attr("id","projectName"+index);
		
		var responserNameDom = $(trApprover_new).find(".responserName");
		responserNameDom.attr("id","responserName"+index);
		
		index++;
		$("#listTable").append(trApprover_new);
		addClickAction(trApprover_new);
		refreshImg();
	}
	function delItem(dom){
		var imgorder = $(dom).attr("imgorder");
		$('#listItem'+imgorder).remove();
		refreshImg();
	}
	function refreshImg(){
		if( $('.deleteItemImg').length == 1){
			$('.deleteItemImg').hide();
		}else{
			$('.deleteItemImg').show();
		}
	}
	
	function zcMontyValidate(jDom){
		formatVal(jDom);
		//不限制金额
		//validateMoney(jDom);
	}
</script>

<!-- 默认1条申请记录 -->
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>