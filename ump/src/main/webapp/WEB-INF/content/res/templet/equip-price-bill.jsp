<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--设备材料批定价格审批表-->
<%@ include file="template-header.jsp"%>
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" >
		<%@ include file="template-var.jsp"%>
		
		<table  class="mainTable">
			<col width="120"/>
			<col/>
			<col width="100"/>
			<col/>
			<tr>
				<td class="td_title">项目名称</td>
				<td>
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
				<td class="td_title">合同名称<br/>(合同编号)</td>
				<td><input class="inputBorder" type="text" validate="required" name="templateBean.contactName" value="${templateBean.contactName}"  /></td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3">合同双方</td>
				<td colspan="3" style="padding-top:3px;">
					<div style="float: left;margin-right:5px;">甲方:</div>
					<div style="float: left;">
						<input class="inputBorder" style="width:300px" validate="required" type="text" name="templateBean.partA" value="${templateBean.partA}" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<div style="float: left;margin-right:5px;">乙方:</div>
					<div style="float: left;">
						<input class="inputBorderNoTip" id="partBName" style="width:300px;" validate="required" type="text" name="templateBean.partBName" value="${templateBean.partBName}" />
						<input type="hidden" id="partB" name="templateBean.partB" value="${templateBean.partB}" />
					</div>
				</td>
			</tr>
			<!-- Start Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<div style="float: left;margin-right:5px;">丙方:</div>
					<div style="float: left;">
						<input class="inputBorder" style="width:300px" type="text" name="templateBean.partC" value="${templateBean.partC}" />
					</div>
				</td>
			</tr>
			<!-- End  Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td class="td_title">批价类型</td>
				<td colspan="3"  class="chkGroup"  validate="required">
				<s:checkbox name="templateBean.priceKind1" cssClass="group"></s:checkbox>甲定乙供
				<s:checkbox name="templateBean.priceKind2" cssClass="group"></s:checkbox>甲供合同增补
				</td>
			</tr>
			<tr>
				<td class="td_title">材料名称</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.materialName" value="${templateBean.materialName}"/></td>
				<td class="td_title">使用区域</td>
				<td><input  class="inputBorder" validate="required" type="text" name="templateBean.useCoverage" value="${templateBean.useCoverage}"/></td>
			</tr>
			<tr>
				<td class="td_title">采购周期</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.purchasePreiod" value="${templateBean.purchasePreiod}"/></td>
				<td class="td_title">进场时间</td>
				<td align="left"><input onfocus="WdatePicker()" class="Wdate inputBorder" style="width:100px;" validate="required" type="text" name="templateBean.enterDate" value="${templateBean.enterDate}"/></td>
			</tr>
			<tr>
				<td class="td_title">原合同约定的材料品牌</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.oriMaterialBrand" value="${templateBean.oriMaterialBrand}"/></td>
				<td class="td_title">原合同暂定价(元)</td>
				<td align="left"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.oriPrice" value="${templateBean.oriPrice}"/></td>
			</tr>
			<tr>
				<td class="td_title">推荐品牌</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.recommendProduct" value="${templateBean.recommendProduct}"/></td>
				<td class="td_title">现报价(总价)(元)</td>
				<td align="left"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.curPrice" value="${templateBean.curPrice}"/></td>
			</tr>
			<tr>
				<td class="td_title">规格/数量</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.standardCount" value="${templateBean.standardCount}"/></td>
				<td class="td_title">付款方式</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.payType" value="${templateBean.payType}"/></td>
			</tr>
			
			<tr>
				<td class="td_title">备注</td>
				<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark">${templateBean.remark}</textarea></td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3">应提供细项<br/>(请作为附件上传)</td>
				<td style="height:40px;" validate="required" value="${templateBean.materialListFileId}">材料清单或图纸</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('材料清单或图纸','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','materialListFileId',event);"/>
				<input  validate="required" type="hidden" name="templateBean.materialListFileId" id="materialListFileId" value="${templateBean.materialListFileId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_materialListFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.materialListFileId}','materialListFileId','${canEdit}');
				</script>
				</td>
				
			</tr>
			<tr>
				<td style="height:40px;" validate="required" value="${templateBean.techParaFileId}">相关技术参数要求</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('相关技术参数要求','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','techParaFileId',event);"/>
				<input  validate="required" type="hidden" name="templateBean.techParaFileId" id="techParaFileId" value="${templateBean.techParaFileId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_techParaFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.techParaFileId}','techParaFileId','${canEdit}');
				</script>
				</td>
			</tr> 
			<tr>
				<td style="height:40px;" validate="required" value="${templateBean.offerPriceFileId}">乙方报价清单</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('乙方报价清单','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','offerPriceFileId',event);"/>
				<input  validate="required" type="hidden" name="templateBean.offerPriceFileId" id="offerPriceFileId" value="${templateBean.offerPriceFileId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_offerPriceFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.offerPriceFileId}','offerPriceFileId','${canEdit}');
				</script>
				</td>
			</tr> 
		
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>

<%@ include file="template-footer.jsp"%>

