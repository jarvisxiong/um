<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--成本月招采计划(集团)-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<!--
	<s:if test="(nodeCd == 129) && isMyApprove==1">
		<s:hidden id="isEdit" value="true"/>
	</s:if>
	-->
	
	<!-- 设计管理中心/项目管理中心nodeCd==153||nodeCd==154 -->
	<!-- 成本控制中心总经理(nodeCd==13)/成本控制中心综合部(nodeCd==89)/成本控制中心造价部(nodeCd==92)/成本控制中心经营采购部(商业、酒店)(nodeCd==93)
	/成本控制中心(nodeCd==156)/成本控制中心招采负责人(nodeCd==250)/成本控制中心招标部(nodeCd==259)/成本控制中心经营采购部(nodeCd==267)/成本控制中心采购部(nodeCd==275) -->
	
	<!-- 设计管理中心、项目管理中心、成本控制中心相关负责人，都可以编辑 -->
	<s:set var="canEdit" value="((nodeCd == 13 || nodeCd == 89 || nodeCd == 92 || nodeCd == 93
		 || nodeCd == 156 || nodeCd == 250 || nodeCd == 259 || nodeCd == 167 || nodeCd == 175)
		 || nodeCd==153 || nodeCd==154)" />
		 
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
 
		<!-- 总记录数 -->
		<s:set var="conItemCount"><s:property value="templateBean.planProperties.size()"/></s:set>
		
		<!-- 模板 -->
		<s:if test="statusCd==0 || statusCd==3">
		<table class="mainTable" id="trItem" style="display:none;margin-top: 10px;">
			<col width="130px">
			<col />
			<col width="120px">
			<col />
			<tr>
				<td class="td_title">项目</td>
				<td align="left">
					<input class="inputBorderNoTip projectName" 
					validate="required" 
					type="text" name="templateBean.planProperties[0].projectName" 
					onclick="initSelectFlag($(this))"
					readonly="readonly"
					/>
					<input type="hidden" name="templateBean.planProperties[0].projectCd"  />
					<s:if test="statusCd==0||statusCd==3">
					<img border="0" src="../resources/images/common/del_22_22.gif" imgorder="1" onclick="delItem(this)" class="deleteItemImg" title="删除本项目" style="float: right; cursor: pointer;">
				    </s:if>
				</td>
				<td colspan="2"></td>
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
				<td  class="td_title">预计金额(元)</td>
				<td >
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[0].totalPrice" onblur="zcMontyValidate($(this))" title="招标金额在300,000元以内；采购金额在100,000元以内"/>
				</td>
				<td  class="td_title">进场时间</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.planProperties[0].enterDate"/>
				</td>
			</tr>
			<tr>
				<td  class="td_title">预计上单时间</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" validate="required" type="text" name="templateBean.planProperties[0].preUploadDate"/>
				</td>
				<td class="td_title">*定标完成时间</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.planProperties[0].bidCompleteDate"/>
				</td>
			</tr>
			</tr>
			<tr>
				<td  class="td_title">备注</td>
				<td  colspan="3">
					<textarea class="inputBorder contentTextArea" name="templateBean.planProperties[0].remark"></textarea>
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
				<td class="td_title">项目</td>
				<td colspan="3">
					<input validate="required" type="text" 
					name="templateBean.planProperties[<s:property value="#s.index" />].projectName" 
					value="${item.projectName}" 
					readonly="readonly" 
					class="inputBorderNoTip" 
					<s:if test="#isMy==1"> 
					onclick="initSelectFlag($(this))"
					style="cursor: pointer;width:90%;" 
					</s:if >
					/>
					<input type="hidden" name="templateBean.planProperties[<s:property value="#s.index" />].projectCd" value="${item.projectCd}"  />
				<s:if test="statusCd==0||statusCd==3">
				<img border="0" src="../resources/images/common/del_22_22.gif" imgorder="1" onclick="delItem(this)" class="deleteItemImg" title="删除本项目" style="float: right; cursor: pointer;">
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
				<td  class="td_title">预计金额(元)</td>
				<td >
					<input class="inputBorder" validate="required" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].totalPrice" onblur="zcMontyValidate($(this));" value="${item.totalPrice}" title="招标金额在300,000元以内；采购金额在100,000元以内"/>
				</td>
				<td  class="td_title">进场时间</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.planProperties[<s:property value="#s.index" />].enterDate" value="${item.enterDate}"/>
				</td>
			</tr>
			<tr>
			   <td  class="td_title">预计上单时间</td>
				<td >
					<input class="Wdate inputBorder" validate="required" type="text" name="templateBean.planProperties[<s:property value="#s.index" />].preUploadDate" value="${item.preUploadDate}"/>
				</td>
				<td  class="td_title">*定标完成时间</td>
				<td>
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.planProperties[<s:property value="#s.index" />].bidCompleteDate" value="${item.bidCompleteDate}"
						<s:if test="(((nodeCd == 13 || nodeCd == 89 || nodeCd == 92 || nodeCd == 93
							 || nodeCd == 156 || nodeCd == 250 || nodeCd == 259 || nodeCd == 167 || nodeCd == 175)
							 || nodeCd==153 || nodeCd==154) || nodeCd == 129) && isMyApprove==1">
							edit="true"
						</s:if>
					/>
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
				<td  class="td_title">*成本意见</td>
				<td  colspan="3">
					<textarea class="inputBorder contentTextArea"  name="templateBean.planProperties[<s:property value="#s.index" />].areaCostOptionDesc"
						<s:if test="(((nodeCd == 13 || nodeCd == 89 || nodeCd == 92 || nodeCd == 93
							 || nodeCd == 156 || nodeCd == 250 || nodeCd == 259 || nodeCd == 167 || nodeCd == 175)
							 || nodeCd==153 || nodeCd==154) || nodeCd == 129) && isMyApprove==1">
							edit="true"
						</s:if>
					>${item.areaCostOptionDesc}</textarea>
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