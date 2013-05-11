<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
	租赁合同/意向书审批表(eg:租赁合同/意向书审批表（由总部发起的申请）)	
 --%>
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

	
	<%-- 
		1、面积＜2000平方米，政策内全部满足政策的情况下，宝龙商业招商中心/宝龙商业大客户中心。
		2．、面积＜2000平方米，政策内，如品牌变更，宝龙商业招商中心/宝龙商业大客户中心->宝龙商业招商副总经理审批。
		3、面积＜2000平方米如业态变更，宝龙商业招商中心/宝龙商业大客户中心->地产区域公司->宝龙商业招商副总级管理->宝龙商业总经理审批。
	 --%>
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<%--不自动设置项目编号 --%>
	     <input type="hidden" id="isAutosetProject" value="0"/>
	     <%--是否判断合库文件编号和合同台账编号一致 --%>
	     <input type="hidden" id="isJudageCtLedgerCons" value="1"/>
		 <%--合同库文本库引用 start --%>
		 <%@ include file="bid-contract-base.jsp"%>
		<s:if test="statusCd!=0">
		<%@ include file="bid-contract-mark.jsp" %>
		</s:if>
		 <%--是否从合同库中自动抓取附件 --%>
		<input type="hidden" id="isGraspAttach" value="1"/>
		<%--合同库文本库引用 end --%>
		<table  class="mainTable">
			<col width="120px"/>
			<col/>
			<col width="80px"/>
			<col/>
			<col width="80px"/>
			<col/>
			<tr>
				<td style="border-top:0 none;" class="td_title">项目名称</td>
				<td style="border-top: none;" colspan="5">
					<input id="projectName" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" name="templateBean.projectCd" value="${templateBean.projectCd}" />
				</td>
			</tr>
			<%--去掉意向书、合同选项，确保旧数据可以显示 --%>
			<s:if test="templateBean.rentTypeCd2!=null || templateBean.rentTypeCd1!=null">
			<tr>
				<td style="border-top:0 none;" class="td_title">类别</td>
				<td style="border-top:0 none;" class="chkGroup" align="left"  validate="required" colspan="5">
					<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
						<td><s:checkbox name="templateBean.rentTypeCd2"  cssClass="group"></s:checkbox>意向书</td>
						<td><s:checkbox name="templateBean.rentTypeCd1"  cssClass="group"></s:checkbox>合同</td>
					</tr>
					</table>
				</td> 
			</tr>
			</s:if>
			<tr>
				<td style="border-top:0 none;" class="td_title">面积</td>
				<td style="border-top:0 none;" class="chkGroup" align="left"  validate="required" colspan="5">
					<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
						<td><s:checkbox name="templateBean.areaCd1"  cssClass="group"></s:checkbox>面积＜2000平方米</td>
						<td><s:checkbox name="templateBean.areaCd2"  cssClass="group"></s:checkbox>面积≥2000平方米</td>
					</tr>
					</table>
				</td> 
			</tr>
			<tr>
				<td style="border-top:0 none;" class="td_title">标准</td>
				<td style="border-top:0 none;" class="chkGroup" align="left"  validate="required" colspan="5">
					<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
						<td><s:checkbox name="templateBean.standardCd1"  cssClass="group"></s:checkbox>标准</td>
						<td><s:checkbox name="templateBean.standardCd2"  cssClass="group"></s:checkbox>非标准</td>
					</tr>
					</table>
				</td> 
			</tr>
			<tr>
				<td style="border-top:0 none;" class="td_title">文件标题</td>
				<td style="border-top: none;" colspan="5">
					<input class="inputBorder" validate="required" type="text" name="templateBean.fileTitle" value="${templateBean.fileTitle}" />
				</td>
			</tr> 
			<tr style="display:none;">
				<td style="border-top:0 none;" class="td_title">密级</td>
				<td style="border-top:0 none;" class="chkGroup" align="left" colspan="5">
					<s:checkbox name="templateBean.securityCd1"  cssClass="group"></s:checkbox>绝密
					<s:checkbox name="templateBean.securityCd2"  cssClass="group"></s:checkbox>机密
					<s:checkbox name="templateBean.securityCd3"  cssClass="group"></s:checkbox>保密
					<s:checkbox name="templateBean.securityCd4"  cssClass="group"></s:checkbox>内部公开
				</td> 
			</tr>
			<tr style="display:none;">
				<td style="border-top:0 none;" class="td_title">紧急</td>
				<td style="border-top:0 none;" class="chkGroup" align="left" colspan="5">
					<s:checkbox name="templateBean.urgencyCd1"  cssClass="group"></s:checkbox>是
					<s:checkbox name="templateBean.urgencyCd2"  cssClass="group"></s:checkbox>否
				</td> 
			</tr>
			<tr>
				<td style="border-top:0 none;" class="td_title">业态类别</td>
				<td style="border-top:0 none;" class="chkGroup" align="left"  validate="required" colspan="5">
					<s:checkbox name="templateBean.operTypeCd1"  cssClass="group"></s:checkbox>大主力店（百货、超市）
					<s:checkbox name="templateBean.operTypeCd2"  cssClass="group"></s:checkbox>次主力店
					<s:checkbox name="templateBean.operTypeCd3"  cssClass="group"></s:checkbox>品牌店
					<s:checkbox name="templateBean.operTypeCd4"  cssClass="group"></s:checkbox>其他
					<input class="inputBorder" style="width:150px;" type="text" name="templateBean.opertypeCd4Desc" value="${templateBean.opertypeCd4Desc}" />
				</td> 
			</tr> 
			<tr style="display:none;">
				<td style="border-top:0 none;" class="td_title">拟稿部门</td>
				<td style="border-top: none;">
					<input id="deptName" type="text" name="templateBean.deptName" value="${templateBean.deptName}"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" name="templateBean.deptCd" value="${templateBean.deptCd}" />
				</td>
				<td style="border-top:0 none;" class="td_title">招商经办人</td>
				<td style="border-top: none;">
					<input id="operatorName" type="text" name="templateBean.operatorName" value="${templateBean.operatorName}"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" name="templateBean.operatorCd" value="${templateBean.operatorCd}" />
				</td>
				<td style="border-top:0 none;" class="td_title">校核人</td>
				<td style="border-top: none;">
					<input id="checkerName" name="templateBean.checkerName" value="${templateBean.checkerName}" type="text" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" name="templateBean.checkerCd" value="${templateBean.checkerCd}" />
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">意向商户</td>
				<td style="border-top: none;" colspan="5">
					<input class="inputBorder" validate="required" id="purposeStoreDesc" type="text" name="templateBean.purposeStoreDesc" value="${templateBean.purposeStoreDesc}" />
					<input type="hidden" id="bisShopId" name="templateBean.bisShopId" value="${templateBean.bisShopId}" />
				</td>
			</tr> 
			<tr>
				<td class="td_title">工程改造预算</td>
				<td style="border-top: none;" colspan="5">
					<input class="inputBorder" id="projectBudget" validate="required" type="text" name="templateBean.projectBudget" value="${templateBean.projectBudget}" />
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">商务条件<br/>说   明<br/>（具体内容附后）</td>
				<td style="border-top: none;" colspan="5">
					<table class="mainTable" style="width:100%;border: 0;">
						<col width="30px"/>
						<col width="80px"/>
						<col width="45%"/>
						<col width="35%"/>
						<col width="20%"/>
						<tr>
							<td style="border-top:0 none;text-align: left;" class="td_title" colspan="5">一、商务条件</td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title"></td>
							<td style="border-top:0 none;" class="td_title"></td>
							<td style="border-top:0 none;" class="td_title">标准（已批条件）</td>
							<td style="border-top:0 none;" class="td_title">申请条件</td>
							<td style="border-top:0 none;" class="td_title">批注</td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">1</td>
							<td style="border-top:0 none;" class="td_title">业态</td>
							<td style="border-top:0 none;"><input name="templateBean.oper1" value="${templateBean.oper1}" class="inputBorder" type="text"  /></td>
							<td style="border-top:0 none;"><input name="templateBean.oper2" value="${templateBean.oper2}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.oper3" value="${templateBean.oper3}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">2</td>
							<td style="border-top:0 none;" class="td_title">租赁区域</td>
							<td style="border-top:0 none;"><input name="templateBean.rentArea1" value="${templateBean.rentArea1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.rentArea2" value="${templateBean.rentArea2}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.rentArea3" value="${templateBean.rentArea3}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">3</td>
							<td style="border-top:0 none;" class="td_title">计租面积</td>
							<td style="border-top:0 none;"><input name="templateBean.calcArea1" value="${templateBean.calcArea1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.calcArea2" value="${templateBean.calcArea2}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.calcArea3" value="${templateBean.calcArea3}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">4</td>
							<td style="border-top:0 none;" class="td_title">租期</td>
							<td style="border-top:0 none;"><input name="templateBean.rentRank1" value="${templateBean.rentRank1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.rentRank2" value="${templateBean.rentRank2}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.rentRank3" value="${templateBean.rentRank3}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">5</td>
							<td style="border-top:0 none;" class="td_title">交付时间</td>
							<td style="border-top:0 none;"><input name="templateBean.handDate1" value="${templateBean.handDate1}" onfocus="WdatePicker({dateFmt:'yyyy年M月dd日'})" class="Wdate inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.handDate2" value="${templateBean.handDate2}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.handDate3" value="${templateBean.handDate3}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">6</td>
							<td style="border-top:0 none;" class="td_title">开业时间</td>
							<td style="border-top:0 none;"><input name="templateBean.openDate1" value="${templateBean.openDate1}" onfocus="WdatePicker({dateFmt:'yyyy年M月dd日'})" class="Wdate inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.openDate2" value="${templateBean.openDate2}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.openDate3" value="${templateBean.openDate3}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">7</td>
							<td style="border-top:0 none;" class="td_title">租金</td>
							<td style="border-top:0 none;">
							<textarea class="inputBorder contentTextArea" name="templateBean.rentFeeAmt1">${templateBean.rentFeeAmt1}</textarea>
							</td>
							<td style="border-top:0 none;">
							<textarea class="inputBorder contentTextArea" name="templateBean.rentFeeAmt2">${templateBean.rentFeeAmt2}</textarea>
							</td>
							<td style="border-top:0 none;">
							<table class="tb_checkbox">
							<col width="30">
							<col>
							<tr>
								<td>折扣:</td>
								<td>
								<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.rentFeeAmt3">${templateBean.rentFeeAmt3}</textarea>
								</td>
							</tr>
							</table>
							</td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">8</td>
							<td style="border-top:0 none;" class="td_title">递增率</td>
							<td style="border-top:0 none;"><input name="templateBean.incrRate1" value="${templateBean.incrRate1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.incrRate2" value="${templateBean.incrRate2}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.incrRate3" value="${templateBean.incrRate3}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">9</td>
							<td style="border-top:0 none;" class="td_title">物业管理费</td>
							<td style="border-top:0 none;"><input name="templateBean.propMgrAmt1" value="${templateBean.propMgrAmt1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.propMgrAmt2" value="${templateBean.propMgrAmt2}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.propMgrAmt3" value="${templateBean.propMgrAmt3}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">10</td>
							<td style="border-top:0 none;" class="td_title">综合管理费</td>
							<td style="border-top:0 none;"><input name="templateBean.compMgrAmt1" value="${templateBean.compMgrAmt1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.compMgrAmt2" value="${templateBean.compMgrAmt2}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.compMgrAmt3" value="${templateBean.compMgrAmt3}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">11</td>
							<td style="border-top:0 none;" class="td_title">免租期</td>
							<td style="border-top:0 none;"><input name="templateBean.rentDateRank1" value="${templateBean.rentDateRank1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.rentDateRank2" value="${templateBean.rentDateRank2}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.rentDateRank3" value="${templateBean.rentDateRank3}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">12</td>
							<td style="border-top:0 none;" class="td_title">租赁保证金</td>
							<td style="border-top:0 none;"><input name="templateBean.rentGaraAmt1" value="${templateBean.rentGaraAmt1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.rentGaraAmt2" value="${templateBean.rentGaraAmt2}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.rentGaraAmt3" value="${templateBean.rentGaraAmt3}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">13</td>
							<td style="border-top:0 none;" class="td_title">违约金</td>
							<td style="border-top:0 none;"><input name="templateBean.defaultAmt1" value="${templateBean.defaultAmt1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.defaultAmt2" value="${templateBean.defaultAmt2}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.defaultAmt3" value="${templateBean.defaultAmt3}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">14</td>
							<td style="border-top:0 none;" class="td_title">广告位</td>
							<td style="border-top:0 none;"><input name="templateBean.adverPos1" value="${templateBean.adverPos1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.adverPos2" value="${templateBean.adverPos2}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.adverPos3" value="${templateBean.adverPos3}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">15</td>
							<td style="border-top:0 none;" class="td_title">停车</td>
							<td style="border-top:0 none;"><input name="templateBean.parkDesc1" value="${templateBean.parkDesc1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.parkDesc2" value="${templateBean.parkDesc2}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.parkDesc3" value="${templateBean.parkDesc3}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">16</td>
							<td style="border-top:0 none;" class="td_title">其他事项</td>
							<td style="border-top:0 none;">
							<textarea class="inputBorder contentTextArea" name="templateBean.otherItem1">${templateBean.otherItem1}</textarea>
							</td>
							<td style="border-top:0 none;">
							<textarea class="inputBorder contentTextArea" name="templateBean.otherItem2">${templateBean.otherItem2}</textarea>
							</td>
							<td style="border-top:0 none;">
							<textarea class="inputBorder contentTextArea" name="templateBean.otherItem3">${templateBean.otherItem3}</textarea>
							</td>
						</tr> 
					</table>
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">工程条件<br/>说   明<br/>（具体内容附后）</td>
				<td style="border-top: none;" colspan="5">
					<table class="mainTable" style="width:100%;border: 0;">
						<col width="30px"/>
						<col width="80px"/>
						<col width="80%"/>
						<col width="20%"/>
						<tr>
							<td style="border-top:0 none;text-align: left;" class="td_title" colspan="4">二、主要工程条件（注：1 工程交付提要见附件    2 如与现场交付条件相符，则填写“按标准”，如不符，须详细说明）</td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title"></td>
							<td style="border-top:0 none;" class="td_title">事项</td>
							<td style="border-top:0 none;" class="td_title">要求</td>
							<td style="border-top:0 none;" class="td_title">备注</td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">1</td>
							<td style="border-top:0 none;" class="td_title">给排水</td>
							<td style="border-top:0 none;"><input name="templateBean.waterSupply1" value="${templateBean.waterSupply1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.waterSupply2" value="${templateBean.waterSupply2}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">2</td>
							<td style="border-top:0 none;" class="td_title">天然气</td>
							<td style="border-top:0 none;"><input name="templateBean.gas1" value="${templateBean.gas1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.gas2" value="${templateBean.gas2}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">3</td>
							<td style="border-top:0 none;" class="td_title">排油、排风</td>
							<td style="border-top:0 none;"><input name="templateBean.airExhaust1" value="${templateBean.airExhaust1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.airExhaust2" value="${templateBean.airExhaust2}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">4</td>
							<td style="border-top:0 none;" class="td_title">空调</td>
							<td style="border-top:0 none;"><input name="templateBean.airCond1" value="${templateBean.airCond1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.airCond2" value="${templateBean.airCond2}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">5</td>
							<td style="border-top:0 none;" class="td_title">设备机房</td>
							<td style="border-top:0 none;"><input name="templateBean.equiment1" value="${templateBean.equiment1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.equiment2" value="${templateBean.equiment2}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">6</td>
							<td style="border-top:0 none;" class="td_title">隔油池</td>
							<td style="border-top:0 none;"><input name="templateBean.oilSeparator1" value="${templateBean.oilSeparator1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.oilSeparator2" value="${templateBean.oilSeparator2}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">7</td>
							<td style="border-top:0 none;" class="td_title">消防报验</td>
							<td style="border-top:0 none;"><input name="templateBean.secuCheck1" value="${templateBean.secuCheck1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.secuCheck2" value="${templateBean.secuCheck2}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">8</td>
							<td style="border-top:0 none;" class="td_title">装修</td>
							<td style="border-top:0 none;"><input name="templateBean.decorate1" value="${templateBean.decorate1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.decorate2" value="${templateBean.decorate2}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">9</td>
							<td style="border-top:0 none;" class="td_title">配电</td>
							<td style="border-top:0 none;"><input name="templateBean.elecDist1" value="${templateBean.elecDist1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.elecDist2" value="${templateBean.elecDist2}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">10</td>
							<td style="border-top:0 none;" class="td_title">用电量</td>
							<td style="border-top:0 none;"><input name="templateBean.powerCons1" value="${templateBean.powerCons1}" class="inputBorder" type="text"/></td>
							<td style="border-top:0 none;"><input name="templateBean.powerCons2" value="${templateBean.powerCons2}" class="inputBorder" type="text"/></td>
						</tr> 
						<tr>
							<td style="border-top:0 none;" class="td_title">11</td>
							<td style="border-top:0 none;" class="td_title">其他事项</td>
							<td style="border-top:0 none;">
							<textarea class="inputBorder contentTextArea" name="templateBean.other1">${templateBean.other1}</textarea>
							</td>
							<td style="border-top:0 none;">
							<textarea class="inputBorder contentTextArea" name="templateBean.other2">${templateBean.other2}</textarea>
							</td>
						</tr> 
					</table>
				</td>
			</tr> 
		</table>
		
		
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
	var ids={shopName:'purposeStoreDesc',bisShopId:'bisShopId'};
	$("#purposeStoreDesc").quickSearch("${ctx}/bis/bis-shop!quickSearch.action",['shopName','companyName'],ids,{bisShopAudit:'2'});
</script>
<script type="text/javascript">
//是否回执标准库选择回调函数
var isCallChooseCont=true;
//是否有选择合同库
if($("#contlib").attr("checked") == true || $("#contlib").attr("checked") == "checked"){
	userCont('true');
}else{
	userCont('false');
}
</script>