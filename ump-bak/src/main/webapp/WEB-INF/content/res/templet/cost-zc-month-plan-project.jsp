<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--成本月招采计划(项目)-->
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
	<s:if test="((nodeCd == 13 || nodeCd == 89 || nodeCd == 92 || nodeCd == 93
		 || nodeCd == 156 || nodeCd == 250 || nodeCd == 259 || nodeCd == 167 || nodeCd == 175)
		 || nodeCd==153 || nodeCd==154) && isMyApprove==1">
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
		<table class="mainTable" id="trItemTmplate" style="display:none;margin-top: 10px;">
			<col width="130px">
			<col />
			<col width="120px">
			<col />
			<tr>
				<td class="td_title">项目</td>
				<td style="border-top: none;" colspan="2">
					<input class="inputBorderNoTip projectName" 
					validate="required" 
					type="text" name="templateBean.planProperties[0].projectName" 
					onclick="initSelectFlag($(this))"
					readonly="readonly"
					/>
					<input type="hidden" name="templateBean.planProperties[0].projectCd"  />
				</td>
				<td class="chkGroup" align="left">
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
				<td class="td_title">工程名称</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[0].projectDesc"/>
				</td>
				<td class="td_title">招标范围/采购内容</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[0].invPurDesc"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">计算指标(面积/数量)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[0].calcIndexSquare" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">计算指标(*单价)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[0].calcIndexPrice" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">预计金额(*元)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[0].totalPrice" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">进场时间</td>
				<td>
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.planProperties[0].enterDate"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">方案上报</td>
				<td class="chkGroup" align="left"  validate="required">
					<s:checkbox name="templateBean.planProperties[0].isUp1"  cssClass="group"></s:checkbox>是
					<s:checkbox name="templateBean.planProperties[0].isUp2"  cssClass="group"></s:checkbox>否
				</td>
				<td class="td_title">提供时间</td>
				<td>
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.planProperties[0].provideDate"/>
				</td>
			</tr>
			<%-- Add by liuzhihui 2012-04-11 --%>
			<tr>
				<td class="td_title">出图时间</td>
				<td>
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.planProperties[0].plotDate"/>
				</td>
				<td class="td_title">
				<%--
				 定标时间
				 --%>
				 </td>
				<td>
				<%--
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.planProperties[0].calibrationDate"/>
				 --%>
				</td>
			</tr>
			<tr>
				<td class="td_title">定标完成时间</td>
				<td>
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.planProperties[0].calibrationCompleteDate"/>
				</td>
				<td class="td_title">定样完成时间</td>
				<td>
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text"  name="templateBean.planProperties[0].sampleCompleteDate"/>
				</td>
			</tr>
			<tr>
			   <td  class="td_title">方案审批时间</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.planProperties[0].programApprovalDate"/>
				</td>
				<td class="td_title">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td class="td_title">备注</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" name="templateBean.planProperties[0].remark"></textarea>
				</td>
			</tr>
			<%--
			<tr>
				<td class="td_title">*区域成本意见</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.planProperties[0].areaCostOptionDesc"></textarea>
				</td>
			</tr>
			--%>
		</table>
		</s:if>
		
		
		<!-- 列表 -->
		<s:set name="itemCount" value="templateBean.planProperties.size()"></s:set>
		<s:iterator value="templateBean.planProperties" var="item" status="s">
		<table class="mainTable" style="margin-top: 10px;" id="listItem<s:property value="#s.index" />">
			<col width="130px">
			<col />
			<col width="120px">
			<col />
 			<tr>
				<td class="td_title">项目</td>
				<td style="border-top: none;" colspan="2">
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
				<td class="chkGroup" align="left" >
					<%--
					<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
					--%>
					<%-- 各成本控制中心的负责人可删除不合理项目 --%>
					<s:if test="((nodeCd == 13 || nodeCd == 89 || nodeCd == 92 || nodeCd == 93
						 || nodeCd == 156 || nodeCd == 250 || nodeCd == 259 || nodeCd == 167 || nodeCd == 175)
						 || nodeCd==153 || nodeCd==154) && isMyApprove==1 && #itemCount>1)||#isMy==1">
						<img style="float:right;cursor:pointer;" 
							 class="deleteItemImg"
							 onclick="delItemConfirm(this)" 
							 imgorder='<s:property value="#s.index" />'
							 src="${ctx}/resources/images/common/del_22_22.gif" 
							 border="0"
							 />
					</s:if>
					
				</td>
			</tr>
			<tr>
				<td class="td_title">工程名称</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].projectDesc" value="${item.projectDesc}"/>
				</td>
				<td class="td_title">招标范围/采购内容</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].invPurDesc" value="${item.invPurDesc}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">计算指标(面积/数量)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].calcIndexSquare" value="${item.calcIndexSquare}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">*计算指标(单价)</td>
				<td>
					<s:if test="canEdit">
						<input class="inputBorder" validate="required" type="text" edit="true" name="templateBean.planProperties[<s:property value="#s.index" />].calcIndexPrice"  value="${item.calcIndexPrice}" onblur="formatVal($(this));"/>
					</s:if>
					<s:else>
						<input class="inputBorder" validate="required" type="text" readonly="readonly" name="templateBean.planProperties[<s:property value="#s.index" />].calcIndexPrice"  value="${item.calcIndexPrice}" onblur="formatVal($(this));"/>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="td_title">*预计金额(元)</td>
				<td>
					<s:if test="canEdit">
						<input class="inputBorder" validate="required" type="text" edit="true" name="templateBean.planProperties[<s:property value="#s.index" />].totalPrice" value="${item.totalPrice}" onblur="zcMontyValidate($(this));" title="招标金额在300,000元以内；采购金额在100,000元以内"/>
					</s:if>
					<s:else>
						<input class="inputBorder" validate="required" type="text" readonly="readonly" name="templateBean.planProperties[<s:property value="#s.index" />].totalPrice" value="${item.totalPrice}" onblur="zcMontyValidate($(this));" title="招标金额在300,000元以内；采购金额在100,000元以内"/>
					</s:else>
				</td>
				<td class="td_title">进场时间</td>
				<td>
					<s:if test="(nodeCd == 154) && isMyApprove==1">
						<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" edit="true" validate="required" name="templateBean.planProperties[<s:property value="#s.index" />].enterDate" value="${item.enterDate}" />
					</s:if>
					<s:else>
						<input onfocus="WdatePicker()" class="Wdate inputBorder" readonly="readonly" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].enterDate" value="${item.enterDate}" />
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="td_title">方案上报</td>
				<td class="chkGroup" align="left"  validate="required">
					<s:checkbox name="templateBean.planProperties[%{#s.index}].isUp1"  cssClass="group"></s:checkbox>是
					<s:checkbox name="templateBean.planProperties[%{#s.index}].isUp2"  cssClass="group"></s:checkbox>否
				</td>
				<td class="td_title">提供时间</td>
				<td>
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].provideDate" value="${item.provideDate}" />
				</td>
			</tr>
			<%-- Add by liuzhihui 2012-04-11 --%>
			<tr>
				<td class="td_title">出图时间</td>
				<td>
					<s:if test="((nodeCd == 13 || nodeCd == 89 || nodeCd == 92 || nodeCd == 93
						 || nodeCd == 156 || nodeCd == 250 || nodeCd == 259 || nodeCd == 167 || nodeCd == 175)
						 || nodeCd==153 || nodeCd==154) && isMyApprove==1">
						<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" edit="true" validate="required" name="templateBean.planProperties[<s:property value="#s.index" />].plotDate" value="${item.plotDate}"/>
					</s:if>
					<s:else>
						<input onfocus="WdatePicker()" class="Wdate inputBorder" readonly="readonly" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].plotDate" value="${item.plotDate}"/>
					</s:else>
				</td>
				<td class="td_title">定标时间</td>
				<td>
					<s:if test="((nodeCd == 13 || nodeCd == 89 || nodeCd == 92 || nodeCd == 93
						 || nodeCd == 156 || nodeCd == 250 || nodeCd == 259 || nodeCd == 167 || nodeCd == 175)
						 || nodeCd==153 || nodeCd==154) && isMyApprove==1">
						<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" edit="true" validate="required" name="templateBean.planProperties[<s:property value="#s.index" />].calibrationDate" value="${item.calibrationDate}"/>
					</s:if>
					<s:else>
						<input onfocus="WdatePicker()" class="Wdate inputBorder" readonly="readonly" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].calibrationDate" value="${item.calibrationDate}"/>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="td_title">定标完成时间</td>
				<td>
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.planProperties[<s:property value="#s.index" />].calibrationCompleteDate" value="${item.calibrationCompleteDate}"/>
				</td>
				<td class="td_title">定样完成时间</td>
				<td>
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.planProperties[<s:property value="#s.index" />].sampleCompleteDate" value="${item.sampleCompleteDate}"/>
				</td>
			</tr>
			<tr>
			   <td  class="td_title">方案审批时间</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].programApprovalDate" value="${item.programApprovalDate}"/>
				</td>
				<td class="td_title">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td class="td_title">备注</td>
				<td colspan="3">
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
					<textarea class="inputBorder contentTextArea" edit="true" name="templateBean.planProperties[<s:property value="#s.index" />].areaCostOptionDesc">${item.areaCostOptionDesc}</textarea>
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

	var trApprover=$("#trItemTmplate").clone();
	$("#trItemTmplate").remove();
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
		index++;
		$("#listTable").append(trApprover_new);
		addClickAction(trApprover_new);
		refreshImg();
	}

	//列表页删除项目带确认删除
	function delItemConfirm(dom){
		if (confirm("确认删除该项目?")) {
			delItem(dom);
		}
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
		validateMoney(jDom);
	}

	//如果选择招标则金额锁死在300,000元以内；
	//如果选择采购则金额锁死在100,000元以内
	function validateMoney(jDom){
		var tmp = jDom.val().replace(/,/ig,''); 
		var tName = jDom.parents('.mainTable').find('.zbcg').find(':checkbox[checked=true]').attr('name');
		var enableFlag = '1';
		if( tName){
			enableFlag = true;
			if(tName.indexOf('typeCd1') > 0 && 300000 < parseFloat(tmp)){
				enableFlag = '0';
				alert('招标:预计金额限在30万以内');
				jDom.focus();
			}
			if(tName.indexOf('typeCd2') > 0 && 100000 < parseFloat(tmp)){
				enableFlag = '0';
				alert('采购:预计金额限在10万以内');
				jDom.focus();
			}
		}else{
			enableFlag = '0';
		};
		$('#resApproveDoApply').attr('disabled',(('1'==enableFlag)?'':'disabled'));
		$('#resApproveDoApply').attr('title',(('1'==enableFlag)?'发起申请':'请填写完整表单!'));
	}
</script>

<!-- 默认1条申请记录 -->
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>