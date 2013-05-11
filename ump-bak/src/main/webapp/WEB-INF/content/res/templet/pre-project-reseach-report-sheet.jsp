<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<!--项目预可研报告审批表单-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	
	<form action="res-approve-info!save.action" method="post" id="templetForm">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="100">
			<col width="220">
			<col>
			<col width="100">
			<col>
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="4">
					<input validate="required" class="inputBorder" type="text" name="templateBean.projectName" value="${templateBean.projectName}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">项目地理位置</td>
				<td colspan="4">
					<input validate="required" class="inputBorder" type="text" name="templateBean.projectAddr" value="${templateBean.projectAddr}"/>
				</td>
			</tr>
			<tr>
				<td rowspan="8" class="td_title">城市宏观经济数据</td>
				<td class="td_title">全市国内生产总值（亿元）</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.cityGDP" value="${templateBean.cityGDP}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">人均GDP（元/年） </td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.peopleGDP" value="${templateBean.peopleGDP}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">区城市人口/区常住总人口（万人） </td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.inhabitant" value="${templateBean.inhabitant}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">在岗职工平均资（元） </td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.averageWage" value="${templateBean.averageWage}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">城镇居民人均可支配收入（元/月）</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.disposablePersonalIncome" value="${templateBean.disposablePersonalIncome}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">城镇居民人均生活消费支出（元/月）</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.consumptionPerPerson" value="${templateBean.consumptionPerPerson}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">社会消费品零售总额（亿元/年）</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.consumerProductsSum" value="${templateBean.consumerProductsSum}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">主要产业</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.majorIndustry" value="${templateBean.majorIndustry}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">项目区域概况</td>
				<td colspan="4">
					<input validate="required" class="inputBorder" type="text" name="templateBean.projectAreaState" value="${templateBean.projectAreaState}"/>
				</td>
			</tr>
			<tr>
				<td rowspan="2" class="td_title">地块所在区域的房价情况</td>
				<td  class="td_title">沿街商业售价/租金</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.businessPrice" value="${templateBean.businessPrice}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">城市综合体的平均售价/租金</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.cityAvgPrice" value="${templateBean.cityAvgPrice}"/>
				</td>
			</tr>
			
			<tr>
				<td class="td_title">业态规划初步建议</td>
				<td colspan="4">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.preSuggest" rows="6" cols="30">${templateBean.preSuggest}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">其它说明</td>
				<td colspan="4">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.otherState" rows="6" cols="30">${templateBean.otherState}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">主要商业介绍（大型超市、百货、综合体）</td>
				<td colspan="4">
					<table id="tbConItem" style="margin-top: 5px;" width="100%">
							<col>
							<col>
							<col>
							<col>
							<col width="40">
							<tr>
								<td align="center">名称</td>
								<td align="center">规模</td>
								<td align="center">主要业态</td>
								<td align="center">开业时间</td>
								<td align="center">操作</td>
							</tr>
							<s:if test="statusCd==0 || statusCd==3">
							<tr id="trConItem" style="display: none;margin-bottom:5px;">
								<td class="tdGroupNodes">
									<input validate="required" class="inputBorder" type="text" name="templateBean.reseachReportBusinessIntroduce[0].name"/>
								</td>
								<td class="tdGroupNodes">
									<input validate="required" class="inputBorder" type="text" name="templateBean.reseachReportBusinessIntroduce[0].scope"/>
								</td>
								<td class="tdGroupNodes">
									<input validate="required" class="inputBorder" type="text" name="templateBean.reseachReportBusinessIntroduce[0].primaryService"/>
								</td>
								<td>
									<input class="inputBorder Wdate" type="text"  name="templateBean.reseachReportBusinessIntroduce[0].startTime" onfocus="WdatePicker()"/>
								</td>
								<td><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/></a></td>
							</tr>
							</s:if>
							<s:set var="conItemCount"><s:property value="templateBean.reseachReportBusinessIntroduce.size()"/></s:set>
							<s:iterator value="templateBean.reseachReportBusinessIntroduce" var="item" status="s">
								<tr style="margin-bottom:5px;">
									<td class="tdGroupNodes">
									<input validate="required" class="inputBorder" type="text" name="templateBean.reseachReportBusinessIntroduce[<s:property value="#s.index" />].name" value="${item.name}"/>
								</td>
								<td class="tdGroupNodes">
									<input validate="required" class="inputBorder" type="text" name="templateBean.reseachReportBusinessIntroduce[<s:property value="#s.index" />].scope" value="${item.scope}"/>
								</td>
								<td class="tdGroupNodes">
									<input validate="required" class="inputBorder" type="text" name="templateBean.reseachReportBusinessIntroduce[<s:property value="#s.index" />].primaryService" value="${item.primaryService}"/>
								</td>
								<td>
									<input class="inputBorder Wdate" type="text"  name="templateBean.reseachReportBusinessIntroduce[<s:property value="#s.index" />].startTime" onfocus="WdatePicker()" value="${item.startTime}"/>
								</td>
									
								<td>	
									<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
									<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/></a>
									</s:if>
									</td>
								</tr>
							</s:iterator>
						</table>
						
				
								</td>
							</tr>
					</table>
					<s:if test="statusCd==0 || statusCd==3">
						<input type="button"  class="btn_blue_75_55" style="margin-top: 5px;" value="增加条款" onclick="addItem();" />
					</s:if>
		
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
	var trApprover=$("#trConItem").clone();
	$("#trConItem").remove();
	var cloneCount = 0;
	var index=0;
	try{
		index = Number("${conItemCount}");
	}catch(e){}
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
		$(dom).parent().parent().remove();
	}
</script>
<!-- 默认1条申请记录 -->
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>