<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
	商务条件审批表(eg:商务条件审批表（由总部发起的申请）)	
 --%>

<div id="billContent">
		
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
		</div>
		<s:if test="authTypeCd == 'BLSY_ZSGL_SWSP_10'">
		<div class="div_row">
			<span class="wap_title">发起方:</span>
			<div><s:checkbox name="templateBean.sendOrg1" cssClass="group"></s:checkbox><span>招商中心</span></div>
			<div><s:checkbox name="templateBean.sendOrg2" cssClass="group"></s:checkbox><span>大客户中心</span></div>
		</div>
		</s:if>
		<div class="div_row">
			<span class="wap_title">政策类型:</span>
			<div><s:checkbox name="templateBean.policyCd1" cssClass="group"></s:checkbox><span>政策内</span></div>
			<div><s:checkbox name="templateBean.policyCd2" cssClass="group"></s:checkbox><span>政策外</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">面积:</span>
			<div><s:checkbox name="templateBean.areaCd1" cssClass="group"></s:checkbox><span>面积＜2000平方米</span></div>
			<div><s:checkbox name="templateBean.areaCd2" cssClass="group"></s:checkbox><span>面积≥2000平方米</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">标准:</span>
			<div><s:checkbox name="templateBean.brandCd1" cssClass="group"></s:checkbox><span>品牌满足</span></div>
			<div><s:checkbox name="templateBean.brandCd2" cssClass="group"></s:checkbox><span>品牌不满足</span></div>
			<div><s:checkbox name="templateBean.yetaiCd1" cssClass="group"></s:checkbox><span>业态满足</span></div>
			<div><s:checkbox name="templateBean.yetaiCd2" cssClass="group"></s:checkbox><span>业态不满足</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">文件标题:</span>
			<span class="wap_value">${templateBean.fileTitle}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">业态类别</span>
			<div><s:checkbox name="templateBean.operTypeCd1" cssClass="group"></s:checkbox><span>大主力店（百货、超市）</span></div>
			<div><s:checkbox name="templateBean.operTypeCd2" cssClass="group"></s:checkbox><span>次主力店</span></div>
			<div><s:checkbox name="templateBean.operTypeCd3" cssClass="group"></s:checkbox><span>品牌店</span></div>
			<div><s:checkbox name="templateBean.operTypeCd4" cssClass="group"></s:checkbox>
			<span>其他</span>
			<span class="wap_value">${templateBean.opertypeCd4Desc}</span>
			</div>
		</div>
		<div class="div_row">
			<span class="wap_title">意向商户:</span>
			<span class="wap_value">${templateBean.purposeStoreDesc}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">工程改造预算:</span>
			<span class="wap_value">${templateBean.projectBudget}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【商务条件说明(具体内容附后)】<red>(可左右拖动查看)</red></span>
			<div class="div_scroll">
			<table>
						<tr>
							<td colspan="5">一、商务条件</td>
						</tr> 
						<tr>
							<td ></td>
							<td ></td>
							<td ><span class="wap_title">标准（已批条件）</span></td>
							<td ><span class="wap_title">申请条件</span></td>
							<td ><span class="wap_title">批注</span></td>
						</tr> 
						<tr>
							<td >1</td>
							<td ><span class="wap_title">业态</span></td>
							<td ><span class="wap_value">${templateBean.oper1}</span></td>
							<td ><span class="wap_value">${templateBean.oper2}</span></td>
							<td ><span class="wap_value">${templateBean.oper3}</span></td>
						</tr> 
						<tr>
							<td >2</td>
							<td ><span class="wap_title">租赁区域</span></td>
							<td ><span class="wap_value">${templateBean.rentArea1}</span></td>
							<td ><span class="wap_value">${templateBean.rentArea2}</span></td>
							<td ><span class="wap_value">${templateBean.rentArea3}</span></td>
						</tr> 
						<tr>
							<td >3</td>
							<td ><span class="wap_title">计租面积</span></td>
							<td ><span class="wap_value">${templateBean.calcArea1}</span></td>
							<td ><span class="wap_value">${templateBean.calcArea2}</span></td>
							<td ><span class="wap_value">${templateBean.calcArea3}</span></td>
						</tr> 
						<tr>
							<td >4</td>
							<td ><span class="wap_title">租期</span></td>
							<td ><span class="wap_value">${templateBean.rentRank1}</span></td>
							<td ><span class="wap_value">${templateBean.rentRank2}</span></td>
							<td ><span class="wap_value">${templateBean.rentRank3}</span></td>
						</tr> 
						<tr>
							<td >5</td>
							<td ><span class="wap_title">交付时间</span></td>
							<td ><span class="wap_value">${templateBean.handDate1}</span></td>
							<td ><span class="wap_value">${templateBean.handDate2}</span></td>
							<td ><span class="wap_value">${templateBean.handDate3}</span></td>
						</tr> 
						<tr>
							<td >6</td>
							<td ><span class="wap_title">开业时间</span></td>
							<td ><span class="wap_value">${templateBean.openDate1}</span></td>
							<td ><span class="wap_value">${templateBean.openDate2}</span></td>
							<td ><span class="wap_value">${templateBean.openDate3}</span></td>
						</tr> 
						<tr>
							<td >7</td>
							<td ><span class="wap_title">租金</span></td>
							<td >
							<span class="wap_value">${templateBean.rentFeeAmt1}</span>
							</td>
							<td >
							<span class="wap_value">${templateBean.rentFeeAmt2}</span>
							</td>
							<td >
							<span class="wap_title">折扣:</span>
							<span class="wap_value">${templateBean.rentFeeAmt3}</span>
							</td>
						</tr> 
						<tr>
							<td >8</td>
							<td ><span class="wap_title">递增率</span></td>
							<td ><span class="wap_value">${templateBean.incrRate1}</span></td>
							<td ><span class="wap_value">${templateBean.incrRate2}</span></td>
							<td ><span class="wap_value">${templateBean.incrRate3}</span></td>
						</tr> 
						<tr>
							<td >9</td>
							<td ><span class="wap_title">物业管理费</span></td>
							<td ><span class="wap_value">${templateBean.propMgrAmt1}</span></td>
							<td ><span class="wap_value">${templateBean.propMgrAmt2}</span></td>
							<td ><span class="wap_value">${templateBean.propMgrAmt3}</span></td>
						</tr> 
						<tr>
							<td >10</td>
							<td ><span class="wap_title">综合管理费</span></td>
							<td ><span class="wap_value">${templateBean.compMgrAmt1}</span></td>
							<td ><span class="wap_value">${templateBean.compMgrAmt2}</span></td>
							<td ><span class="wap_value">${templateBean.compMgrAmt3}</span></td>
						</tr> 
						<!-- 
						<tr>
							<td >11</td>
							<td ><span class="wap_title">装修期</span></td>
							<td ><span class="wap_value">${templateBean.decoDateRank1}</span></td>
							<td ><span class="wap_value">${templateBean.decoDateRank2}</span></td>
							<td ><span class="wap_value">${templateBean.decoDateRank3}</span></td>
						</tr>  -->
						<tr>
							<td >11</td>
							<td ><span class="wap_title">免租期</span></td>
							<td ><span class="wap_value">${templateBean.rentDateRank1}</span></td>
							<td ><span class="wap_value">${templateBean.rentDateRank2}</span></td>
							<td ><span class="wap_value">${templateBean.rentDateRank3}</span></td>
						</tr> 
						<tr>
							<td >12</td>
							<td ><span class="wap_title">租赁保证金</span></td>
							<td ><span class="wap_value">${templateBean.rentGaraAmt1}</span></td>
							<td ><span class="wap_value">${templateBean.rentGaraAmt2}</span></td>
							<td ><span class="wap_value">${templateBean.rentGaraAmt3}</span></td>
						</tr> 
						<tr>
							<td >13</td>
							<td ><span class="wap_title">违约金</span></td>
							<td ><span class="wap_value">${templateBean.defaultAmt1}</span></td>
							<td ><span class="wap_value">${templateBean.defaultAmt2}</span></td>
							<td ><span class="wap_value">${templateBean.defaultAmt3}</span></td>
						</tr> 
						<tr>
							<td >14</td>
							<td ><span class="wap_title">广告位</span></td>
							<td ><span class="wap_value">${templateBean.adverPos1}</span></td>
							<td ><span class="wap_value">${templateBean.adverPos2}</span></td>
							<td ><span class="wap_value">${templateBean.adverPos3}</span></td>
						</tr> 
						<tr>
							<td >15</td>
							<td ><span class="wap_title">停车</span></td>
							<td ><span class="wap_value">${templateBean.parkDesc1}</span></td>
							<td ><span class="wap_value">${templateBean.parkDesc2}</span></td>
							<td ><span class="wap_value">${templateBean.parkDesc3}</span></td>
						</tr> 
						<tr>
							<td >16</td>
							<td ><span class="wap_title">其他事项</span></td>
							<td >
							<span class="wap_value">${templateBean.otherItem1}</span>
							</td>
							<td >
							<span class="wap_value">${templateBean.otherItem2}</span>
							</td>
							<td >
							<span class="wap_value">${templateBean.otherItem3}</span>
							</td>
						</tr> 
					</table>
			</div>
		</div>
		
		<div class="div_label">
			<span class="wap_label">【工程条件说明(具体内容附后)】<red>(可左右拖动查看)</red></span>
			<div class="div_scroll">
			<table>
						<tr>
							<td  colspan="4">二、主要工程条件（注：1 工程交付提要见附件    2 如与现场交付条件相符，则填写“按标准”，如不符，须详细说明）</td>
						</tr> 
						<tr>
							<td ></td>
							<td ><span class="wap_title">事项</span></td>
							<td ><span class="wap_title">要求</span></td>
							<td ><span class="wap_title">备注</span></td>
						</tr> 
						<tr>
							<td >1</td>
							<td ><span class="wap_title">给排水</span></td>
							<td ><span class="wap_value">${templateBean.waterSupply1}</span></td>
							<td ><span class="wap_value">${templateBean.waterSupply2}</span></td>
						</tr> 
						<tr>
							<td >2</td>
							<td ><span class="wap_title">天然气</span></td>
							<td ><span class="wap_value">${templateBean.gas1}</span></td>
							<td ><span class="wap_value">${templateBean.gas2}</span></td>
						</tr> 
						<tr>
							<td >3</td>
							<td ><span class="wap_title">排油、排风</span></td>
							<td ><span class="wap_value">${templateBean.airExhaust1}</span></td>
							<td ><span class="wap_value">${templateBean.airExhaust2}</span></td>
						</tr> 
						<tr>
							<td >4</td>
							<td ><span class="wap_title">空调</span></td>
							<td ><span class="wap_value">${templateBean.airCond1}</span></td>
							<td ><span class="wap_value">${templateBean.airCond2}</span></td>
						</tr> 
						<tr>
							<td >5</td>
							<td ><span class="wap_title">设备机房</span></td>
							<td ><span class="wap_value">${templateBean.equiment1}</span></td>
							<td ><span class="wap_value">${templateBean.equiment2}</span></td>
						</tr> 
						<tr>
							<td >6</td>
							<td ><span class="wap_title">隔油池</span></td>
							<td ><span class="wap_value">${templateBean.oilSeparator1}</span></td>
							<td ><span class="wap_value">${templateBean.oilSeparator2}</span></td>
						</tr> 
						<tr>
							<td >7</td>
							<td ><span class="wap_title">消防报验</span></td>
							<td ><span class="wap_value">${templateBean.secuCheck1}</span></td>
							<td ><span class="wap_value">${templateBean.secuCheck2}</span></td>
						</tr> 
						<tr>
							<td >8</td>
							<td ><span class="wap_title">装修</span></td>
							<td ><span class="wap_value">${templateBean.decorate1}</span></td>
							<td ><span class="wap_value">${templateBean.decorate2}</span></td>
						</tr> 
						<tr>
							<td >9</td>
							<td ><span class="wap_title">配电</span></td>
							<td ><span class="wap_value">${templateBean.elecDist1}</span></td>
							<td ><span class="wap_value">${templateBean.elecDist2}</span></td>
						</tr> 
						<tr>
							<td >10</td>
							<td ><span class="wap_title">用电量</span></td>
							<td ><span class="wap_value">${templateBean.powerCons1}</span></td>
							<td ><span class="wap_value">${templateBean.powerCons2}</span></td>
						</tr> 
						<tr>
							<td >11</td>
							<td ><span class="wap_title">其他事项</span></td>
							<td >
							<span class="wap_value">${templateBean.other1}</span>
							</td>
							<td >
							<span class="wap_value">${templateBean.other2}</span>
							</td>
						</tr> 
					</table>
			</div>
		</div>
		
</div>
