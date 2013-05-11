<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 商业合同条款审批表  -->
<%@ include file="template-header.jsp"%>

<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
<c:set var="isSy">false</c:set>
<c:if test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
<c:set var="isSy">true</c:set>
</c:if>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<%--合同文本库引用 start --%>
		<%--是否允许用户勾选合同台账 --%>
	     <input type="hidden" id="isShowChkBox" value="0"/>
		<%@ include file="bid-contract-base.jsp"%>
		<s:if test="statusCd!=0">
		<%@ include file="bid-contract-mark.jsp" %>
		</s:if>
		
		<%--合同文本引用 end --%>
		<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
		<%--删除商业集团公司发起 --%>
		<table  class="mainTable">
			<col width="100"/>
			<col/>
			<col width="120"/>
			<col/>
			<tr>
				<td class="td_title">项目名称</td>
				<td>
				<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
				<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
				<td class="td_title">工程名称</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.engineeringName" value="${templateBean.engineeringName}" />
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
						<input class="inputBorder" validate="required" type="text" name="templateBean.sideA" value="${templateBean.sideA}" />
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
						<input class="inputBorder" validate="required" type="text" name="templateBean.sideB" value="${templateBean.sideB}" />
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
						<input class="inputBorder" type="text" name="templateBean.sideC" value="${templateBean.sideC}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- End   Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td class="td_title" rowspan="5" valign="middle" align="center">招  标<br/>主  要<br/>内  容</td>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>招标范围:</td>
						<td>
						<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.bidRange">${templateBean.bidRange}</textarea>
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
						<td>施工工期:</td>
						<td>
						<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.constructionTimeLimit">${templateBean.constructionTimeLimit}</textarea>
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
						<td>质量要求:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.qualityRequirement" value="${templateBean.qualityRequirement}" />
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
						<td>预算金额(元):</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.budgetPrice" value="${templateBean.budgetPrice}" onblur="formatVal($(this));" />
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
						<td>付款方式:</td>
						<td>
						<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.payMode">${templateBean.payMode}</textarea>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr otype="attach">
				<td class="td_title" rowspan="1">资料附件<br/>(请作为附件上传)</td>
				<td style="height:40px;" validate="required" value="${templateBean.criterionContractId}"  resattachname="合同文本">标准合同文本</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('标准合同','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','criterionContractId',event);"/>
				<input type="hidden" name="templateBean.criterionContractId" id="criterionContractId" value="${templateBean.criterionContractId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_criterionContractId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.criterionContractId}','criterionContractId','${canEdit}');
				</script>
				</td>
			</tr>
		</table>
		
		<table  id="tableConItem"  class="mainTable itemTable" style="display:none;margin-top: 5px;">
			 <col width="120"/>
			 <col width="60"/>
			 <col/>
			 <tr>
			  <td rowspan="2">标准合同条款</td>
			  <td>条款项</td>
			  <td><input class="inputBorder" validate="required" type="text" name="templateBean.otherProperties[0].itemNo"/></td>
			 </tr>
			 <tr>
			  <td>条款内容</td>
			  <td>
			  <textarea class="inputBorder contentTextArea" validate="required" name="templateBean.otherProperties[0].itemContent"></textarea>
			  </td>
			 </tr>
			 <tr>
			  <td>乙方反馈意见</td>
			  <td colspan="2">
			  <textarea class="inputBorder contentTextArea" validate="required" name="templateBean.otherProperties[0].sideBFeedback"></textarea>
			  </td>
			 </tr>
			 <tr>
			  <td>责任部门</td>
			  <td colspan="2"><s:select list="mapGroupNodes" listKey="key" listValue="value" validate="required" name="templateBean.otherProperties[0].resDeptCd" cssClass="inputBorder" emptyOption="true"></s:select></td>
			 </tr>
			 <tr>
			  <td>责任部门意见</td>
			  <td colspan="2"></td>
			 </tr>
			 <tr>
			  <td>成本中心意见</td>
			  <td colspan="2"></td>
			 </tr>
			 <tr>
			  <td>最终意见商业总经理</td>
			  <td colspan="2"></td>
			 </tr>
			 <tr>
			  <td>操作</td>
			  <td colspan="2" align="left"><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a></td>
			 </tr>
		</table>
		
		<div id="contItemDiv">
		<s:iterator value="templateBean.otherProperties" var="item" status="s">
		 <table class="mainTable" style="margin-top: 5px;">
		 <col width="120"/>
		 <col width="60"/>
		 <col/>
		 <tr>
		  <td rowspan="2">标准合同条款</td>
		  <td>条款项</td>
		  <td>
		  <input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].itemNo" value="${item.itemNo}"/></td>
		 </tr>
		 <tr>
		  <td>条款内容</td>
		  <td>
		  <textarea class="inputBorder contentTextArea" name="templateBean.otherProperties[<s:property value="#s.index" />].itemContent">${item.itemContent}</textarea>
		  </td>
		 </tr>
		 <tr>
		  <td>乙方反馈意见</td>
		  <td colspan="2">
		  	<textarea class="inputBorder contentTextArea" name="templateBean.otherProperties[<s:property value="#s.index" />].sideBFeedback">${item.sideBFeedback}</textarea>
		  </td>
		 </tr>
		 <tr>
		  <td>责任部门</td>
		  <td colspan="2" class="tdGroupNodes">
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
		 </tr>
		 <tr>
		  <td>责任部门意见</td>
		    <c:set var="curDeptCd" >;${item.resDeptCd};</c:set>
			<c:if test="${!(fn:contains(myNode, curDeptCd)&&isMyApprove==1)}">
			<td colspan="2">
			<textarea class="inputBorder contentTextArea" readonly="readonly" name="templateBean.otherProperties[<s:property value="#s.index" />].resDeptOption">${item.resDeptOption}</textarea>
			</td>
			</c:if>
			<c:if test="${(fn:contains(myNode, curDeptCd) && isMyApprove==1)}">
			<s:hidden id="isEdit" value="true"/>
			<td colspan="2">
			<textarea class="inputBorder contentTextArea" edit='true' validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].resDeptOption">${item.resDeptOption}</textarea>
			</td>
			</c:if>
		 </tr>
		 <tr>
		  <td>成本中心意见</td>
		    <s:if test="#curNodeCd==13 && isMyApprove==1">
			<s:hidden id="isEdit" value="true"/>
			<td colspan="2">
			<textarea class="inputBorder contentTextArea" edit='true' validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].costCenterOption">${item.costCenterOption}</textarea>
			</td>
			</s:if>
			<s:else>
			<td colspan="2">
			<textarea class="inputBorder contentTextArea" readonly="readonly" name="templateBean.otherProperties[<s:property value="#s.index" />].costCenterOption">${item.costCenterOption}</textarea>
			</td>
			</s:else>
		 </tr>
		 <tr>
		  <td>最终意见商业总经理</td>
		  <!-- 最终意见：宝龙商业副总经理(招商) -->
			<s:if test="#curNodeCd==204 && isMyApprove==1">
			<s:hidden id="isEdit" value="true"/>
			<td colspan="2">
			<textarea class="inputBorder contentTextArea" edit='true' validate="required" name="templateBean.otherProperties[<s:property value="#s.index" />].vicePresidentsOption">${item.vicePresidentsOption}</textarea>
			</td>
			</s:if>
			<s:else>
			<td colspan="2">
			<textarea class="inputBorder contentTextArea" readonly="readonly" name="templateBean.otherProperties[<s:property value="#s.index" />].vicePresidentsOption">${item.vicePresidentsOption}</textarea>
			</td>
			</s:else>
		 </tr>
		 <s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
		 <tr>
		 	<td>操作</td>
		  	<td width="15px" align="left" colspan="2">
				<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
		  	</td>
		 </tr>
		 </s:if>
		</table>
		</s:iterator>
		</div>
		<s:if test="statusCd==0 || statusCd==3">
		<input type="button"  class="btn_blue_75_55" value="增加条款" onclick="addItem();" />
		</s:if>
		
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
	var trApprover=$("#tableConItem").clone();
	$("#tableConItem").remove();
	var cloneCount = 0;
	var index=${conItemCount};
	
	function addItem(){
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();
		$(trApprover_new).find(".inputBorder").each(function(i){
			this.name=this.name.replace('0',index);
		});
		//$(trApprover_new).find("#cloneUserCds").attr("id",cloneCount+"cloneUserCds");
		$("#contItemDiv").append(trApprover_new);
		index++;
	}
	function delItem(dom){
		if($("#contItemDiv table").size() > 1) {
			$(dom).parent().parent().parent().parent().remove();
		}else{
			alert("必须至少有一条条款！");
		}
	}

	//是否回执标准库选择回调函数
	var isCallChooseCont=true;
	//是否有选择合同库
	if($("#contlib").attr("checked") == true || $("#contlib").attr("checked") == "checked"){
		$("#contlibno").show();
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