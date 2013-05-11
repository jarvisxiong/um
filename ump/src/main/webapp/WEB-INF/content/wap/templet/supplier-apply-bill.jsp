<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--供方评级表（ 新单位级别评定）-->
<div class="billContent">
		<%@ include file="template-var.jsp"%>
			<s:set var="canEdit">false</s:set>
			<div class="div_row">
			<span class="wap_title">供方名称:</span>
			<span class="wap_value">${templateBean.supName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">供应类别:</span>
			<span class="wap_value">${templateBean.supType}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">适用地区:</span>
			<span class="wap_value">${templateBean.suitArea}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">实力评价:</span>
			<span class="wap_value">${templateBean.strengthAss}</span>
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
			<span class="wap_title">官网地址:</span>
			<span class="wap_value">${templateBean.webSite}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">注册资金(万):</span>
			<span class="wap_value">${templateBean.registerFund}</span>
			<input type="hidden" name="templateBean.registerFundFileId" id="registerFundFileId" value="${templateBean.registerFundFileId}"/>
			<div id="show_registerFundFileId"></div>
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
			<div><s:checkbox name="templateBean.examType1" id="examType1" cssClass="group"></s:checkbox><span>已考察(合格)</span></div>
			<div><s:checkbox name="templateBean.examType3" id="examType2" cssClass="group"></s:checkbox><span>已考察(不合格)</span></div>
			<div><s:checkbox name="templateBean.examType2" id="examType3" cssClass="group"></s:checkbox><span>免考察</span></div>
			<div><s:checkbox name="templateBean.examType4" id="examType4" cssClass="group"></s:checkbox><span>未考察</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">考察人员:</span>
			<span class="wap_value">${templateBean.examUser}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">考察内容:</span>
			<span class="wap_value">${templateBean.examResult}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">考察附件:</span>
			<div id="show_examFileId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.examFileId}','examFileId','${canEdit}');
			</script>
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
		
		<div class="div_row">
			<span class="wap_title">供方类型:</span>
			<span class="wap_value">${templateBean.supplierTypeMapCd}</span>
			<input type="hidden" name="templateBean.costProvCondRefId" id="costProvCondRefId" value="${templateBean.costProvCondRefId}"/>
			<div id="show_evalFileId"></div>
	   </div>
		<div class=div_table_row>
			<div class="div_row">
				<span class="wap_label">【入库试用条件审核】</span>
			</div>
			<span class="wap_title">评价维度/入库试用条件/待评定供方情况</span>	
			<%-- 专业资质 --%>
			<s:if test="templateBean.importLibCond0!=null">
				<div class="orgDiv">					
				专业资质/${templateBean.importLibCond0}/${templateBean.assessSuppilerStatus0}
				</div>	
			</s:if>	
	        <%-- 行业排名 --%>
			<s:if test="templateBean.importLibCond1!=null">
				<div class="orgDiv">					
				行业排名/${templateBean.importLibCond1}/${templateBean.assessSuppilerStatus1}
				</div>
			</s:if>
		   <%-- 企业业绩 --%>
			<s:if test="templateBean.importLibCond2!=null">
				<div class="orgDiv">					
				企业业绩/${templateBean.importLibCond2}/${templateBean.assessSuppilerStatus2}
				</div>
			</s:if>
		  <%-- 体系认证 --%>
			<s:if test="templateBean.importLibCond3!=null">
				<div class="orgDiv">					
				体系认证/${templateBean.importLibCond3}/${templateBean.assessSuppilerStatus3}
				</div>
			</s:if>
		</div>

    	<div class="div_row">
			<span class="wap_title">综合评定:</span>
			<div><s:checkbox name="templateBean.matchCondition" cssClass="group"></s:checkbox><span>符合条件</span></div>
			<div><s:checkbox name="templateBean.noMatchCondition" cssClass="group"></s:checkbox><span>不符合条件</span></div>
		
		</div>
		
		<div class="div_row">
			<span class="wap_title">备注:</span>
			<span class="wap_value">${templateBean.remark}</span>
		</div>
</div>

<script type="text/javascript">
		function showFiles() {
			var zczj=$("#registerFundFileId").val();//注册资金
			var zy=$("#mainBusinessFileId").val();//主营
			var zzdj=$("#qualiGradeFileId").val();//资质等级
			var ry=$("#honourFileId").val();//荣誉
			var yj=$("#yejiFileId").val();//业绩
			var exam=$("#examFileId").val();//现场考察
			showFile(zczj,'registerFundFileId');
			showFile(zy,'mainBusinessFileId');
			showFile(zzdj,'qualiGradeFileId');
			showFile(ry,'honourFileId');
			showFile(yj,'yejiFileId');
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
		function changeExam(){
			if($('#examType1').attr('checked')||$('#examType3').attr('checked')){
				$('#trExamUser').show();
				$('#trFile').show();
				$('#examUser').attr("validate","required");
				$('#tdExamFileId').attr("validate","required");
			}else{
				$('#trExamUser').hide();
				$('#trFile').hide();
				$('#examUser').removeAttr("validate");
				$('#tdExamFileId').removeAttr("validate");
			}
		}
		showFiles();
		changeExam();
</script>

