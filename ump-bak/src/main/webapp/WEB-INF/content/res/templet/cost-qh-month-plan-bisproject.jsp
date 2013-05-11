<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--月度企划计划审批表(商业公司)-->
<%@ include file="template-header.jsp"%>
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
<c:set var="bizEntityId" value="${resApproveInfoId==null?entityTmpId:resApproveInfoId}"/>
<input type="hidden" id="bizEntityId" value="${resApproveInfoId==null?entityTmpId:resApproveInfoId}">
<div align="center" class="billContent">

	<!-- 商业公司财务负责人173/成本控制中心招采副总93 -->
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
 
		<!-- 总记录数 -->
		<s:set var="conItemCount"><s:property value="templateBean.planProperties.size()"/></s:set>
		
		<!-- 模板 -->
		<s:if test="statusCd==0 || statusCd==3">
		<table class="mainTable" id="trItem" style="display:none;margin-top: 10px;">
			<col width="100px">
			<col />
			<col width="120px">
			<col />
			<tr>
				<td  class="td_title">商业公司</td>
				<td colspan="2">
					<input class="inputBorderNoTip projectName" 
					validate="required" 
					type="text" name="templateBean.planProperties[0].projectName" 
					readonly="readonly"
					style="cursor: pointer;"
					onclick="initSelectFlag($(this))"
					/>
					<input type="hidden" name="templateBean.planProperties[0].projectCd"  />
				</td>
				<td>
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
				<td  class="td_title">活动主题</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[0].actionSubject"/>
				</td>
			</tr>
			<tr>
				<td  class="td_title">活动时间</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" validate="required" type="text" name="templateBean.planProperties[0].actionDate"/>
				</td>
				<td  class="td_title">活动地点</td>
				<td >
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[0].actionPlace"/>
				</td>
			</tr>
			<tr>
				<td  class="td_title">活动组织</td>
				<td colspan="3" class="chkGroup" align="left"  validate="required">
					<table class="tb_checkbox">
					<col>
					<tr>
					<td><s:checkbox name="templateBean.planProperties[0].typeCd1"  cssClass="group" ></s:checkbox>宝龙自行组织</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.planProperties[0].typeCd2"  cssClass="group" ></s:checkbox>联合商户组织，参与商户:
					<input class="inputBorder" type="text" id="typeCd2Info" name="templateBean.planProperties[0].typeCd2Info" style="width:100px;" value="${item.typeCd2Info}"/>
					</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.planProperties[0].typeCd3"  cssClass="group" ></s:checkbox>其他:
					<input class="inputBorder" type="text" id="typeCd3Info" name="templateBean.planProperties[0].typeCd3Info" style="width:100px;" value="${item.typeCd3Info}"/>
					</td>
					</tr>
					</table>
					
				</td>
			</tr>
			<tr>
				<td  class="td_title">预计金额(元)</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[0].totalPrice" onblur="formatVal($(this))"/>
				</td>
			</tr>
			
			<tr>
				<td  class="td_title">活动内容</td>
				<td  colspan="3">
					<textarea class="inputBorder contentTextArea" name="templateBean.planProperties[0].remark"></textarea>
				</td>
			</tr>
			
			<tr id="tr_attachment">
				<td id="td_attachment" class="td_title" >附件</td>
				<td colspan="3">
				<r:fileUpload canEdit="${canEdit}" bizEntityId="${bizEntityId}" fileId="lxfaFileId" title="活动方案说明" index="0" subProp="planProperties" isRequired="false" isTemplate="true"/>
				<r:fileUpload canEdit="${canEdit}" bizEntityId="${bizEntityId}" fileId="ysfyFileId" title="预算费用审批表" index="0" subProp="planProperties" isTemplate="true" isRequired="false"/>
				<r:fileUpload canEdit="${canEdit}" bizEntityId="${bizEntityId}" fileId="zbfaFileId" title="活动组织分工及费用明细" index="0" subProp="planProperties" isRequired="false" isTemplate="true"/>
				</td>
			</tr>
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
				<td class="td_title">商业公司</td>
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
				<td>
				<s:if test="#isMy==1">
				<img style="float:right;cursor:pointer;" 
						 title="删除本项目" 
						 class="deleteItemImg"
						 onclick="delItem(this)" 
						 imgorder='0'
						 src="${ctx}/resources/images/common/del_22_22.gif" 
						 border="0"
						 />
						 	</s:if>
						 </td>
			</tr>
			<tr>
				<td  class="td_title">活动主题</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].actionSubject" value="${item.actionSubject}"/>
				</td>
			</tr>
			<tr>
				<td  class="td_title">活动时间</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" validate="required" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].actionDate" value="${item.actionDate}"/>
				</td>
				<td  class="td_title">活动地点</td>
				<td >
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].actionPlace" value="${item.actionPlace}"/>
				</td>
			</tr>
			
			<tr>
				<td  class="td_title">活动组织</td>
				<td colspan="3" class="chkGroup" align="left"  validate="required">
					<table class="tb_checkbox">
					<col>
					<tr>
					<td><s:checkbox name="templateBean.planProperties[%{#s.index}].typeCd1"  cssClass="group" ></s:checkbox>宝龙自行组织</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.planProperties[%{#s.index}].typeCd2"  cssClass="group" ></s:checkbox>联合商户组织，参与商户:
					<input class="inputBorder" type="text" id="typeCd2Info" name="templateBean.planProperties[%{#s.index}].typeCd2Info" style="width:100px;" value="${item.typeCd2Info}"/>
					</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.planProperties[%{#s.index}].typeCd3"  cssClass="group" ></s:checkbox>其他:
					<input class="inputBorder" type="text" id="typeCd3Info" name="templateBean.planProperties[%{#s.index}].typeCd3Info" style="width:100px;" value="${item.typeCd3Info}"/>
					</td>
					</tr>
					</table>
					
				</td>
			</tr>
			<tr>
				<td  class="td_title">预计金额(元)</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text"  value="${item.totalPrice}" name="templateBean.planProperties[<s:property value="#s.index" />].totalPrice" onblur="formatVal($(this))"/>
				</td>
			</tr>
			
			<tr>
				<td  class="td_title">活动内容</td>
				<td  colspan="3">
					<textarea class="inputBorder contentTextArea" name="templateBean.planProperties[<s:property value="#s.index" />].remark">${item.remark}</textarea>
				</td>
			</tr>
			<tr id="tr_attachment">
				<td id="td_attachment" class="td_title">附件</td>
				<td colspan="3">
				<r:fileUpload canEdit="${canEdit}" bizEntityId="${bizEntityId}" fileId="lxfaFileId" title="活动方案说明" index="${s.index}" fileValue="${item.lxfaFileId}" isRequired="false" subProp="planProperties"/>
				<r:fileUpload canEdit="${canEdit}" bizEntityId="${bizEntityId}" fileId="ysfyFileId" title="预算费用审批表" index="${s.index}" fileValue="${item.ysfyFileId}" subProp="planProperties" isRequired="false"/>
				<r:fileUpload canEdit="${canEdit}" bizEntityId="${bizEntityId}" fileId="zbfaFileId" title="活动组织分工及费用明细" index="${s.index}" fileValue="${item.zbfaFileId}" subProp="planProperties" isRequired="false"/>
				</td>
			</tr>
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
			this.id=this.id.replace('0',index);
			if (this.type=='button'){
				var _divDom=$(this).parent().next();
				_divDom.attr("id",_divDom.attr("id").replace('0',index));
				var fileTileName=$(this).parent().prev("td").html();
				var fileId=$(this).next().attr("id");
				fileId=fileId.replace('0',index);
				$(this).bind("click",function(){
					showUploadSingleAttachDialog(fileTileName,$("#bizEntityId").val(),'resCustomFile',fileId);
				});
			}
		});
		$(trApprover_new).find("img").each(function(i){
			$(this).attr("imgorder",index);
		});
		
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
</script>

<!-- 默认1条申请记录 -->
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>