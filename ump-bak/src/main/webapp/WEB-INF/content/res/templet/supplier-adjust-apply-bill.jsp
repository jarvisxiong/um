<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--供方评级表（ 原单位级别评定）-->
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" >
		<%@ include file="template-var.jsp"%>
			<s:set var="canEdit">
			<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
			true
			</s:if>
			<s:else>
			false
			</s:else>
			</s:set>
		<table  class="mainTable">
			<col width="90"/>
			<col/>
			<col width="80"/>
			<col/>
			<tr>
				<td class="td_title">供方名称</td>
				<td align="left">
					<input type="hidden" id="supBasicId" name="templateBean.supBasicId" value="${templateBean.supBasicId}" >
					
					<s:if test="!templateBean.supBasicId.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getSup('${templateBean.supBasicId}');">${templateBean.supName}</span>
						<input id="supName" type="hidden" name="templateBean.supName" value="${templateBean.supName}" />
					</s:if>
					<s:else>
					<input validate="required" id="supName" class="inputBorder" type="text" name="templateBean.supName" value="${templateBean.supName}" />
					</s:else>
				</td>
				<td class="td_title">供应类别</td>
				<td>
					<input id="supType" readonly="readonly" class="inputBorder" type="text" name="templateBean.supType" value="${templateBean.supType}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">信息来源</td>
				<td>
					<input class="inputBorder" readonly="readonly" type="text" id="infoSource" name="templateBean.infoSource" value="${templateBean.infoSource}" />
				</td>
				<td class="td_title">更新时间</td>
				<td>
					<input class="inputBorder" readonly="readonly" type="text" id="updatedDate" name="templateBean.updatedDate" value="${templateBean.updatedDate}" />
				</td>
			</tr>
			
			<tr>
				<td class="td_title">注册资金(万)</td>
				<td colspan="2">
					<input class="inputBorder" type="text" readonly="readonly" id="registerFund" name="templateBean.registerFund" value="${templateBean.registerFund}"/>
				</td>
				<td>
					<input type="hidden" name="templateBean.registerFundFileId" id="registerFundFileId" value="${templateBean.registerFundFileId}"/>
					<div id="show_registerFundFileId"></div>
				</td>
			</tr>
			<tr>
				<td class="td_title">现有供方级别</td>
				<td colspan="2">
					<input class="inputBorder" type="text" readonly="readonly" id="oriGrade" name="templateBean.oriGrade" value="${templateBean.oriGrade}"/>
				</td>
				<td>
					<input type="hidden" name="templateBean.oriGradeFileId" id="oriGradeFileId" value="${templateBean.oriGradeFileId}"/>
					<div id="show_oriGradeFileId"></div>
				</td>
			</tr>
			<tr>
				<td class="td_title">主营</td>
				<td colspan="2">
					<input class="inputBorder" type="text" readonly="readonly" id="mainBusiness" name="templateBean.mainBusiness" value="${templateBean.mainBusiness}"/>
				</td>
				<td>
					<input type="hidden" name="templateBean.mainBusinessFileId" id="mainBusinessFileId" value="${templateBean.mainBusinessFileId}"/>
					<div id="show_mainBusinessFileId"></div>
				</td>
			</tr>
			<tr>
				<td class="td_title">企业资质等级</td>
				<td colspan="2">
					<textarea class="inputBorder contentTextArea" readonly="readonly" id="qualiGrade" name="templateBean.qualiGrade">${templateBean.qualiGrade}</textarea>
				</td>
				<td>
					<input type="hidden" name="templateBean.qualiGradeFileId" id="qualiGradeFileId" value="${templateBean.qualiGradeFileId}"/>
					<div id="show_qualiGradeFileId"></div>
				</td>
			</tr>
			<tr>
				<td class="td_title">企业或产品主要荣誉</td>
				<td colspan="2">
					<textarea class="inputBorder contentTextArea" readonly="readonly" id="honour" name="templateBean.honour">${templateBean.honour}</textarea>
				</td>
				<td>
					<input type="hidden" name="templateBean.honourFileId" id="honourFileId" value="${templateBean.honourFileId}"/>
					<div id="show_honourFileId"></div>
				</td>
			</tr>
			<tr>
				<td class="td_title">类似工程业绩</td>
				<td colspan="2">
					<textarea class="inputBorder contentTextArea" readonly="readonly" id="yeji" name="templateBean.yeji" >${templateBean.yeji}</textarea>
				</td>
				<td>
					<input type="hidden" name="templateBean.yejiFileId" id="yejiFileId" value="${templateBean.yejiFileId}"/>
					<div id="show_yejiFileId"></div>
				</td>
			</tr>
			<tr>
				<td class="td_title">宝龙业绩</td>
				<td colspan="2" ><textarea class="inputBorder contentTextArea" readonly="readonly"  id="baolongYeji" name="templateBean.baolongYeji">${templateBean.baolongYeji}</textarea></td>
				<td>
					<input type="hidden" name="templateBean.baolongYejiFileId" id="baolongYejiFileId" value="${templateBean.baolongYejiFileId}"/>
					<div id="show_baolongYejiFileId"></div>
				</td>
			</tr>
			<tr>
				<td class="td_title">现场考察结果</td>
				<td colspan="2" ><textarea class="inputBorder contentTextArea" readonly="readonly"  id="examResult" name="templateBean.examResult">${templateBean.examResult}</textarea></td>
				<td>
					<input type="hidden" name="templateBean.examResultFileId" id="examResultFileId" value="${templateBean.examResultFileId}"/>
					<div id="show_examResultFileId"></div>
				</td>
			</tr>
			<tr>
				<td class="td_title">履约评估</td>
				<td colspan="2" ><textarea class="inputBorder contentTextArea" readonly="readonly"  id="supEvaluate" name="templateBean.supEvaluate">${templateBean.supEvaluate}</textarea></td>
				<td>
					<input type="hidden" name="templateBean.evalFileId" id="evalFileId" value="${templateBean.evalFileId}"/>
					<div id="show_evalFileId"></div>
				</td>
			</tr>
			<tr>
				<td class="td_title">本次评审结果</td>
				<td class="chkGroup" align="left" validate="required" colspan="3">
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<col width="100">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.grade1" cssClass="group"></s:checkbox>战略</td>
					<td><s:checkbox name="templateBean.grade2" cssClass="group"></s:checkbox>合格</td>
					<td><s:checkbox name="templateBean.grade6" cssClass="group"></s:checkbox>合格★</td>
					<td><s:checkbox name="templateBean.grade7" cssClass="group"></s:checkbox>合格★★</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.grade3" cssClass="group"></s:checkbox>试用</td>
					<td><s:checkbox name="templateBean.grade9" cssClass="group"></s:checkbox>试用(待考察)</td>
					<td><s:checkbox name="templateBean.grade4" cssClass="group"></s:checkbox>停用</td>
					<td><s:checkbox name="templateBean.grade5" cssClass="group"></s:checkbox>禁用</td>
					<td><s:checkbox name="templateBean.grade8" cssClass="group"></s:checkbox>待定</td>
					<td></td>
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
		function showFiles() {
			var zczj=$("#registerFundFileId").val();//注册资金
			var zy=$("#mainBusinessFileId").val();//主营
			var zzdj=$("#qualiGradeFileId").val();//资质等级
			var ry=$("#honourFileId").val();//荣誉
			var yj=$("#yejiFileId").val();//业绩
			var oriGradeFileId=$("#oriGradeFileId").val();//原供方级别
			var baolongYejiFileId=$("#baolongYejiFileId").val();//宝龙业绩
			var evalFileId=$("#evalFileId").val();//履约评估
			var examResultFileId=$("#examResultFileId").val();//现场考察结果
			showFile(zczj,'registerFundFileId');
			showFile(zy,'mainBusinessFileId');
			showFile(zzdj,'qualiGradeFileId');
			showFile(ry,'honourFileId');
			showFile(yj,'yejiFileId');
			showFile(oriGradeFileId,'oriGradeFileId');
			showFile(baolongYejiFileId,'baolongYejiFileId');
			showFile(evalFileId,'evalFileId');
			showFile(examResultFileId,'examResultFileId');
		}
		function showFile(bizEntityId,fieldId) {
			if (isEmpty(bizEntityId)){
				return;
			}
			var url = _ctx+ "/app/app-attachment!list.action";
			var data = {
				bizEntityId : bizEntityId,
				domId : fieldId,
				single:'1'
			};
			$.post(url, data, function(result) {
					$("#show_"+fieldId).html(result);
				});
		}
		showFiles();
		function getSup(sid){
			var url='';
			if(isNotEmpty(sid)){
				url="${ctx}/sup/sup-basic!input.action?id="+sid;
			}else if(isNotEmpty(supName)){
				url="${ctx}/sup/sup-basic!input.action?sName="+supName;
			}
			if (url!=''){
				if(parent.TabUtils==null)
					window.open(url);
				else
				  parent.TabUtils.newTab("supQuery","明细",url,true);
			}
		}
</script>

<s:if test="#canEdit=='true'">
	<script type="text/javascript">
		var mapPrama={
				sid:'supBasicId',
				sname:'supName',
				supType:'supType',
				supComeFrom:'infoSource',
				updatedDate:'updatedDate',
				comRegMoney:'registerFund',
				comMainPro:'mainBusiness',
				comZzGrade:'qualiGrade',
				comHonor:'honour',
				comProPerf:'yeji',
				comBasFileSn:'registerFundFileId',
				comRangFileSn:'mainBusinessFileId',
				comZzFileSn:'qualiGradeFileId',
				comHonoFileSn:'honourFileId',
				comProFileSn:'yejiFileId',
				supEvaluate:'supEvaluate',
				cooperatedSn:'baolongYejiFileId',
				comExamFileSn:'examResultFileId',
				comMainFileSn:'oriGradeFileId',
				supExamResu:'examResult',
				baolongYeji:'baolongYeji',
				supEvaluate:'supEvaluate',
				grade:'oriGrade'
		};
		$("#supName").quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],mapPrama,{isDetail:true,supStatus:'1'},showFiles);
	</script>
</s:if>