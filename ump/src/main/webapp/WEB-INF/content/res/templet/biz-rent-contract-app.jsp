<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 合同条款审批表  -->
<%@ include file="template-header.jsp"%>

<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<%--Add by zhuxj on 2012.4.12 start --%>
			<%@ include file="bid-contract-base.jsp"%>
		<s:if test="statusCd!=0">
		<%@ include file="bid-contract-mark.jsp" %>
		</s:if>
			<%--Add for import contract by zhuxj on 2012.4.12 end --%>
		<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
		<table  class="mainTable">
			<col width="100"/>
			<col/>
			<col width="120"/>
			<col/>
			<tr>
				<td class="td_title">文件标题</td>
				<td>
				<input  class="inputBorder" type="text" name="templateBean.fileName" value="${templateBean.fileName}"  />
				</td>
				<td class="td_title">所在项目</td>
				<td>
				<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
				<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3" valign="middle" align="center">合  同<br/>双  方</td>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>甲方:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.partA" value="${templateBean.partA}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>乙方:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.partB" value="${templateBean.partB}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- Start Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>丙方:</td>
						<td>
						<input class="inputBorder" type="text" name="templateBean.partC" value="${templateBean.partC}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- End   Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td class="td_title" rowspan="12" valign="middle" align="center">合 同<br/>主  要<br/>内  容</td>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>经营品牌</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.manageBrand" value="${templateBean.manageBrand}"/>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>经营业态</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.manageBusiness" value="${templateBean.manageBusiness}"/>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>租凭位置</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.rentPosition" value="${templateBean.rentPosition}"/>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>计租面积</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.rentArea" value="${templateBean.rentArea}"/>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>租期</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.rentPeriod" value="${templateBean.rentPeriod}"/>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>租金</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.rentMoney" value="${templateBean.rentMoney}"/>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>物业管理费或综合管理费</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.manageMoney" value="${templateBean.manageMoney}"/>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>交付时间</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.payTime" value="${templateBean.payTime}" onfocus="WdatePicker()"/>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>开业时间</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.startTime" value="${templateBean.startTime}" onfocus="WdatePicker()"/>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>装修期</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.fitPeriod" value="${templateBean.fitPeriod}"/>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>租金优惠期</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.rentDownPeriod" value="${templateBean.rentDownPeriod}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>其它事项</td>
						<td>
						<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.otherContent">${templateBean.otherContent}</textarea>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr otype="attach">
				<td class="td_title" rowspan="1">资料附件<br/>(请作为附件上传)</td>
				<td style="height:40px;" validate="required" value="${templateBean.standardContId}" resattachname="合同文本 ">标准合同</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('标准合同','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','standardContId',event);"/>
				<input type="hidden" name="templateBean.standardContId" id="standardContId" value="${templateBean.standardContId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_standardContId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.standardContId}','standardContId','${canEdit}');
				</script>
				</td>
			</tr>
		</table>
		<table id="tbConItem" class="mainTable" style="margin-top: 5px;">
			<tr class="thConItem" >
				<th colspan="2" width="200">标准合同条款</th>
				<th rowspan="2">乙方反馈意见</th>
				<th rowspan="2">责任部门</th>
				<th rowspan="2">责任部门意见</th>
				<th rowspan="2">法务意见</th>
				<th rowspan="2">宝龙商业副总经理</th>
				<th rowspan="2" width="40">操作</th>
			</tr>
			<tr class="thConItem" >
				<th>条款项</th>
				<th>条款内容</th>
			</tr>
			<s:if test="statusCd==0 || statusCd==3">
			<tr id="trConItem" style="display: none;margin-bottom:5px;">
				<td><input class="inputBorder" validate="required"  type="text" name="templateBean.otherProperties[0].itemNo"  /></td>
				<td><input class="inputBorder" validate="required"  type="text" name="templateBean.otherProperties[0].itemContent"  /></td>
				<td><input class="inputBorder" validate="required"  type="text" name="templateBean.otherProperties[0].sideBFeedback"  /></td>
				<td class="tdGroupNodes"><s:select list="mapGroupNodes" listKey="key" listValue="value" validate="required" name="templateBean.otherProperties[0].resDeptCd" cssClass="inputBorder" emptyOption="true" ></s:select></td>
				<td></td>
				<td></td>
				<td></td>
				<td width="15px" align="center"><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a></td>
			</tr>
			</s:if>
			<s:iterator value="templateBean.otherProperties" var="item" status="s">
				<tr style="margin-bottom:5px;">
					<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].itemNo" value="${item.itemNo}" /></td>
					<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].itemContent" value="${item.itemContent}"  /></td>
					<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].sideBFeedback" value="${item.sideBFeedback}"  /></td>
					<td class="tdGroupNodes">
					<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
					<select class="inputBorder" validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].resDeptCd">
					    <option value=""></option>
					    <s:iterator value="mapGroupNodes" var="m" >
					    <option value="${m.key}" <c:if test="${item.resDeptCd==m.key}">selected="selected"</c:if>>${m.value}</option>
					    </s:iterator>
					</select>
					</s:if>
					<s:else>
					<input class="inputBorder"  type="hidden" name="templateBean.otherProperties[<s:property value="#s.index" />].resDeptCd" value="${item.resDeptCd}"  />
					<input class="inputBorder"  type="text" value="<%=CodeNameUtil.getResNodeNameByCd(JspUtil.findString("#item.resDeptCd")) %>"  />
					</s:else>
					</td>
					<c:set var="curDeptCd" >;${item.resDeptCd};</c:set>
					<c:if test="${!(fn:contains(myNode, curDeptCd)&&isMyApprove==1)}">
					<td><input class="inputBorder" readonly="readonly" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].resDeptOption" value="${item.resDeptOption}"/></td>
					</c:if>
					<c:if test="${(fn:contains(myNode, curDeptCd) && isMyApprove==1)}">
					<s:hidden id="isEdit" value="true"/>
					<td><input class="inputBorder" edit='true' validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].resDeptOption" value="${item.resDeptOption}"/></td>
					</c:if>
					<!-- 法务意见 -->
					<s:if test="#curNodeCd==3 && isMyApprove==1">
					<s:hidden id="isEdit" value="true"/>
					<td><input class="inputBorder" edit='true' validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].lawCenterOption" value="${item.lawCenterOption}"  /></td>
					</s:if>
					<s:else>
					<td><input class="inputBorder" readonly="readonly" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].lawCenterOption" value="${item.lawCenterOption}"  /></td>
					</s:else>
					<!-- 最终意见：宝龙商业副总经理(招商) -->
					<s:if test="#curNodeCd==204 && isMyApprove==1">
					<s:hidden id="isEdit" value="true"/>
					<td><input class="inputBorder" edit='true' validate="required" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].vicePresidentsOption" value="${item.vicePresidentsOption}"  /></td>
					</s:if>
					<s:else>
					<td><input class="inputBorder" readonly="readonly" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].vicePresidentsOption" value="${item.vicePresidentsOption}"  /></td>
					</s:else>
					
					<td width="15px" align="center">
					<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
					<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
					</s:if>
					</td>
				</tr>
			</s:iterator>
		</table>
		<s:if test="statusCd==0 || statusCd==3">
		<input type="button"  class="btn_blue_75_55"  value="增加条款" onclick="addItem();" />
		</s:if>
		
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
	var trApprover=$("#trConItem").clone();
	$("#trConItem").remove();
	var cloneCount = 0;
	var index='${conItemCount}';
	
	function addItem(){
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();
		$(trApprover_new).find(".inputBorder").each(function(i){
			this.name=this.name.replace('0',index);
		});
		//$(trApprover_new).find("#cloneUserCds").attr("id",cloneCount+"cloneUserCds");
		$("#tbConItem").append(trApprover_new);
		index++;
	}
	function delItem(dom){
		if($("#tbConItem tr").size() > 3) {
			$(dom).parent().parent().remove();
		}else{
			alert("必须至少有一条条款！");
			}
	}
	//是否回执标准库选择回调函数
	var isCallChooseCont=true;
	//是否有选择合同库
	if($("#contlib").attr("checked") == true || $("#contlib").attr("checked") == "checked"){
		userCont('true');
	}else{
		userCont('false');
	}
</script>
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>