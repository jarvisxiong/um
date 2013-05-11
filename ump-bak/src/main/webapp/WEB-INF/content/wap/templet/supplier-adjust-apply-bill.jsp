<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--供方评级表（ 原单位级别评定）-->
<div class="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">供方名称:</span>
			<span class="wap_value">${templateBean.supName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">供应类别:</span>
			<span class="wap_value">${templateBean.supType}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">信息来源:</span>
			<span class="wap_value">${templateBean.infoSource}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">更新时间:</span>
			<span class="wap_value">${templateBean.updatedDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">注册资金(万):</span>
			<span class="wap_value">${templateBean.registerFund}</span>
			<input type="hidden" name="templateBean.registerFundFileId" id="registerFundFileId" value="${templateBean.registerFundFileId}"/>
			<div id="show_registerFundFileId"></div>
		</div>
		<div class="div_row">
			<span class="wap_title">现有供方级别:</span>
			<span class="wap_value">${templateBean.oriGrade}</span>
			<input type="hidden" name="templateBean.oriGradeFileId" id="oriGradeFileId" value="${templateBean.oriGradeFileId}"/>
			<div id="show_oriGradeFileId"></div>
		</div>
		<div class="div_row">
			<span class="wap_title">主营:</span>
			<span class="wap_value">${templateBean.mainBusiness}</span>
			<input type="hidden" name="templateBean.mainBusinessFileId" id="mainBusinessFileId" value="${templateBean.mainBusinessFileId}"/>
			<div id="show_mainBusinessFileId"></div>
		</div>
		<div class="div_row">
			<span class="wap_title">企业资质等级:</span>
			<span class="wap_value">${templateBean.qualiGrade}</span>
			<input type="hidden" name="templateBean.qualiGradeFileId" id="qualiGradeFileId" value="${templateBean.qualiGradeFileId}"/>
			<div id="show_qualiGradeFileId"></div>
		</div>
		<div class="div_row">
			<span class="wap_title">企业或产品主要荣誉:</span>
			<span class="wap_value">${templateBean.honour}</span>
			<input type="hidden" name="templateBean.honourFileId" id="honourFileId" value="${templateBean.honourFileId}"/>
			<div id="show_honourFileId"></div>
		</div>
		<div class="div_row">
			<span class="wap_title">类似工程业绩:</span>
			<span class="wap_value">${templateBean.yeji}</span>
			<input type="hidden" name="templateBean.yejiFileId" id="yejiFileId" value="${templateBean.yejiFileId}"/>
			<div id="show_yejiFileId"></div>
		</div>
		<div class="div_row">
			<span class="wap_title">宝龙业绩:</span>
			<span class="wap_value">${templateBean.baolongYeji}</span>
			<input type="hidden" name="templateBean.baolongYejiFileId" id="baolongYejiFileId" value="${templateBean.baolongYejiFileId}"/>
			<div id="show_baolongYejiFileId"></div>
		</div>
		<div class="div_row">
			<span class="wap_title">现场考察结果:</span>
			<span class="wap_value">${templateBean.examResult}</span>
			<input type="hidden" name="templateBean.examResultFileId" id="examResultFileId" value="${templateBean.examResultFileId}"/>
			<div id="show_examResultFileId"></div>
		</div>
		<div class="div_row">
			<span class="wap_title">履约评估:</span>
			<span class="wap_value">${templateBean.supEvaluate}</span>
			<input type="hidden" name="templateBean.evalFileId" id="evalFileId" value="${templateBean.evalFileId}"/>
			<div id="show_evalFileId"></div>
		</div>
		<div class="div_row">
			<span class="wap_title">本次评审结果:</span>
			<div><s:checkbox name="templateBean.grade1" cssClass="group"></s:checkbox><span>战略</span></div>
			<div><s:checkbox name="templateBean.grade2" cssClass="group"></s:checkbox><span>合格</span></div>
			<div><s:checkbox name="templateBean.grade6" cssClass="group"></s:checkbox><span>合格★</span></div>
			<div><s:checkbox name="templateBean.grade7" cssClass="group"></s:checkbox><span>合格★★</span></div>
			<div><s:checkbox name="templateBean.grade3" cssClass="group"></s:checkbox><span>试用</span></div>
			<div><s:checkbox name="templateBean.grade9" cssClass="group"></s:checkbox><span>试用(待考察)</span></div>
			<div><s:checkbox name="templateBean.grade4" cssClass="group"></s:checkbox><span>停用</span></div>
			<div><s:checkbox name="templateBean.grade5" cssClass="group"></s:checkbox><span>禁用</span></div>
			<div><s:checkbox name="templateBean.grade8" cssClass="group"></s:checkbox><span>待定</span></div>
		</div>
</div>


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
</script>