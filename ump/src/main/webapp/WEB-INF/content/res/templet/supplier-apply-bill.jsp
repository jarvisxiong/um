<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--供方评级表（ 新单位级别评定）-->
<div align="center" class="billContent">

<form action="res-approve-info!save.action" method="post" id="templetForm"><%@ include file="template-var.jsp"%> <s:set
	var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
			true
			</s:if>
	<s:else>
			false
			</s:else>
</s:set>
<table class="mainTable">
	<col width="90" />
	<col />
	<col width="80" />
	<col />
	<tr>
		<td class="td_title require">供方名称</td>
		<td align="left"><input type="hidden" id="supBasicId" name="templateBean.supBasicId" value="${templateBean.supBasicId}"> <s:if
			test="!templateBean.supBasicId.isEmpty() && #canEdit == 'false'">
			<span class="link" onclick="getSup('${templateBean.supBasicId}');">${templateBean.supName}</span>
			<input id="supName" type="hidden" name="templateBean.supName" value="${templateBean.supName}" />
		</s:if> <s:else>
			<input validate="required" id="supName" class="inputBorder" type="text" name="templateBean.supName" value="${templateBean.supName}" />
		</s:else></td>
		<td class="td_title">供应类别</td>
		<td><input id="supType" readonly="readonly" class="inputBorder" type="text" name="templateBean.supType" value="${templateBean.supType}" /></td>
	</tr>
	<tr>
		<td class="td_title require">适用地区</td>
		<td align="left">
			<input validate="required" class="inputBorder" type="text" name="templateBean.suitArea" value="${templateBean.suitArea}" />
		</td>
		<td class="td_title require">实力评价</td>
		<td><input validate="required" class="inputBorder" type="text" name="templateBean.strengthAss" value="${templateBean.strengthAss}" /></td>
	</tr>
	<tr>
		<td class="td_title">信息来源</td>
		<td><input class="inputBorder" readonly="readonly" type="text" id="infoSource" name="templateBean.infoSource" value="${templateBean.infoSource}" /></td>
		<td class="td_title">更新时间</td>
		<td><input class="inputBorder" readonly="readonly" type="text" id="updatedDate" name="templateBean.updatedDate" value="${templateBean.updatedDate}" /></td>
	</tr>
	<tr>
		<td class="td_title">官网地址</td>
		<td colspan="3" align="left"><input class="inputBorder" readonly="readonly" type="hidden" id="webSite" name="templateBean.webSite"
			value="${templateBean.webSite}" /> <a href="#" id="webSite_link" onclick="openWebSite('${templateBean.webSite}')">${templateBean.webSite}</a></td>
	</tr>
	<tr>
		<td class="td_title">注册资金(万)</td>
		<td colspan="2"><input class="inputBorder" type="text" readonly="readonly" id="registerFund" name="templateBean.registerFund"
			value="${templateBean.registerFund}" /></td>
		<td><input type="hidden" name="templateBean.registerFundFileId" id="registerFundFileId" value="${templateBean.registerFundFileId}" />
		<div id="show_registerFundFileId"></div>
		</td>
	</tr>
	<tr>
		<td class="td_title">主营</td>
		<td colspan="2"><textarea class="inputBorder contentTextArea" readonly="readonly" id="mainBusiness" name="templateBean.mainBusiness">${templateBean.mainBusiness}</textarea>
		</td>
		<td><input type="hidden" name="templateBean.mainBusinessFileId" id="mainBusinessFileId" value="${templateBean.mainBusinessFileId}" />
		<div id="show_mainBusinessFileId"></div>
		</td>
	</tr>
	<tr>
		<td class="td_title">企业资质等级</td>
		<td colspan="2"><textarea class="inputBorder contentTextArea" readonly="readonly" id="qualiGrade" name="templateBean.qualiGrade">${templateBean.qualiGrade}</textarea>
		</td>
		<td><input type="hidden" name="templateBean.qualiGradeFileId" id="qualiGradeFileId" value="${templateBean.qualiGradeFileId}" />
		<div id="show_qualiGradeFileId"></div>
		</td>
	</tr>
	<tr>
		<td class="td_title">企业或产品主要荣誉</td>
		<td colspan="2"><textarea class="inputBorder contentTextArea" readonly="readonly" id="honour" name="templateBean.honour">${templateBean.honour}</textarea>
		</td>
		<td><input type="hidden" name="templateBean.honourFileId" id="honourFileId" value="${templateBean.honourFileId}" />
		<div id="show_honourFileId"></div>
		</td>
	</tr>
	<tr>
		<td class="td_title">类似工程业绩</td>
		<td colspan="2"><textarea class="inputBorder contentTextArea" readonly="readonly" id="yeji" name="templateBean.yeji">${templateBean.yeji}</textarea></td>
		<td><input type="hidden" name="templateBean.yejiFileId" id="yejiFileId" value="${templateBean.yejiFileId}" />
		<div id="show_yejiFileId"></div>
		</td>
	</tr>
	<tr>
		<td class="td_title require"></td>
		<td colspan="3" class="chkGroup" align="left" validate="required">
		<table class="tb_checkbox">
			<col width="120">
			<col width="120">
			<col width="100">
			<col width="100">
			<tr>
				<td><input type="hidden" name="templateBean.supExamResu" id="supExamResu" value="${templateBean.supExamResu}" /> <s:checkbox
					name="templateBean.examType1" id="examType1" cssClass="group" onchange="changeExam();"></s:checkbox>已考察(合格)</td>
				<td><s:checkbox name="templateBean.examType3" id="examType3" cssClass="group" onchange="changeExam();"></s:checkbox>已考察(不合格)</td>
				<td><s:checkbox name="templateBean.examType2" id="examType2" cssClass="group" onchange="changeExam();"></s:checkbox>免考察</td>
				<td><s:checkbox name="templateBean.examType4" id="examType4" cssClass="group" onchange="changeExam();"></s:checkbox>未考察</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr id="trExamUser">
		<td class="td_title">考察人员</td>
		<td colspan="3"><input class="inputBorder" validate="required" type="text" id="examUser" name="templateBean.examUser" value="${templateBean.examUser}" />
		</td>
	</tr>
	<tr>
		<td class="td_title require">考察内容</td>
		<td colspan="3"><textarea class="inputBorder contentTextArea" id="examResult" validate="required" name="templateBean.examResult">${templateBean.examResult}</textarea></td>
	</tr>
	<tr id="trFile">
		<td align="center" id="tdExamFileId" validate="required" colspan="2" value="${templateBean.examFileId}">考察附件</td>
		<td align="center"><input type="hidden" name="templateBean.examFileId" id="examFileId" value="${templateBean.examFileId}" /> <s:if
			test="#canEdit=='true'">
			<input type="button" value="上传附件" class="btn_green_65_20" style="border: none;"
				onclick="showUploadSingleAttachDialog('考察附件','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','examFileId',event);" />
		</s:if></td>
		<td>
		<div id="show_examFileId"></div>
		<script type="text/javascript">
	showUploadedFile('${templateBean.examFileId}', 'examFileId', '${canEdit}');
</script></td>
	</tr>
	<tr>
		<td class="td_title require">本次评审结果</td>
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
	<tr>
	 	<td>供方类型:</td>
		<td colspan="3" style="text-align:left">
		<input type="text" id="supplierTypeMapCd" name="templateBean.supplierTypeMapCd" style="width:200px" class="inputBorder" validate="required"  value="${templateBean.supplierTypeMapCd}"/>
		<input type="hidden" id="costProvCondRefId" name="templateBean.costProvCondRefId"  value="${templateBean.costProvCondRefId}"/>
		</td>
	</tr>
	<tr id="conditionRevi">
		<td>评价维度</td>
		<td colspan="2">入库试用条件</td>
		<td>待评定供方情况</td>
	</tr>
	<%--专业资质 默认隐藏 --%> 
 	<tr id="profQualDesc" <s:if test="templateBean.importLibCond0==null">style="display:none"</s:if>  optype="assessDim">
		<td>专业资质</td>
		<td colspan="2"><input type='text' id='importLibCond0' name='templateBean.importLibCond0' value='${templateBean.importLibCond0}' readonly='readonly'/></td>
		<td><input type='text'  id='assessSuppilerStatus0' name='templateBean.assessSuppilerStatus0' value='${templateBean.assessSuppilerStatus0}' class="inputBorder"/></td>
	</tr>
	<%--行业排名 默认隐藏 --%>
	<tr id="induRankDesc" <s:if test="templateBean.importLibCond1==null">style="display:none"</s:if> optype="assessDim">
		<td>行业排名</td>
		<td colspan="2"><input type='text' id='importLibCond1' name='templateBean.importLibCond1' value='${templateBean.importLibCond1}' readonly='readonly'/></td>
		<td><input type='text'  id='assessSuppilerStatus1' name='templateBean.assessSuppilerStatus1' value='${templateBean.assessSuppilerStatus1}' class="inputBorder"/></td>
	</tr>
	<%-- 企业业绩  默认隐藏 --%>
	<tr id="entePerfDesc" <s:if test="templateBean.importLibCond2==null">style="display:none"</s:if>  optype="assessDim">
		<td>企业业绩</td>
		<td colspan="2"><input type='text' id='importLibCond2' name='templateBean.importLibCond2' value='${templateBean.importLibCond2}' readonly='readonly'/></td>
		<td><input type='text' id='assessSuppilerStatus2' name='templateBean.assessSuppilerStatus2' value='${templateBean.assessSuppilerStatus2}' class="inputBorder"/></td>
	</tr>
   <%-- 体系认证  默认隐藏 --%>
	<tr id="systCertDesc" <s:if test="templateBean.importLibCond3==null">style="display:none"</s:if>  optype="assessDim">
		<td>体系认证</td>
		<td colspan="2"><input type='text' id='importLibCond3' name='templateBean.importLibCond3' value='${templateBean.importLibCond3}' readonly='readonly'/></td>
		<td><input type='text'  id='assessSuppilerStatus3' name='templateBean.assessSuppilerStatus3' value='${templateBean.assessSuppilerStatus3}' class="inputBorder"/></td>
	</tr>
	<tr>
		<td>综合评定</td>
		<td colspan="3" class="chkGroup" align="left" validate="required"><s:checkbox cssClass="group" id="matchCondition" name="templateBean.matchCondition"></s:checkbox>符合条件
		<s:checkbox cssClass="group" id="noMatchCondition" name="templateBean.noMatchCondition"></s:checkbox>不符合条件</td>
	</tr>
	<tr>
		<td>备注</td>
		<td colspan="3"><textarea rows="5" cols="10" class="inputBorder" validate="required" id="remark" name="templateBean.remark">${templateBean.remark}</textarea></td>
	</tr>
</table>
<%@ include file="template-approver.jsp"%></form>
</div>

<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
	function showFiles() {
		var zczj = $("#registerFundFileId").val();//注册资金
		var zy = $("#mainBusinessFileId").val();//主营
		var zzdj = $("#qualiGradeFileId").val();//资质等级
		var ry = $("#honourFileId").val();//荣誉
		var yj = $("#yejiFileId").val();//业绩
		var exam = $("#examFileId").val();//现场考察
		showFile(zczj, 'registerFundFileId');
		showFile(zy, 'mainBusinessFileId');
		showFile(zzdj, 'qualiGradeFileId');
		showFile(ry, 'honourFileId');
		showFile(yj, 'yejiFileId');
		showFile(exam, 'examFileId');
		var webSite = $('#webSite').val();
		if (isNotEmpty(webSite)) {
			$('#webSite_link').html(webSite);
		}
		var resu = $("#supExamResu").val();
		if (isNotEmpty(resu)) {
			if ("1" == resu) {
				$("#examType1").attr("checked", true);
				$("#trExamUser").show();
				$("#trFile").show();
				//若有现场考察附件，则带出附件
				showFile(exam, 'examFileId');
			} else if ("2" == resu) {
				$("#examType3").attr("checked", true);
				$("#trExamUser").show();
				$("#trFile").show();
				//若有现场考察附件，则带出附件
				showFile(exam, 'examFileId');
			} else if ("3" == resu) {
				$("#examType4").attr("checked", true);
			} else if ("4" == resu) {
				$("#examType2").attr("checked", true);
			}
		}
	}
	function showFile(bizEntityId, fieldId) {
		if (isEmpty(bizEntityId)) {
			return;
		}
		var url = _ctx + "/app/app-attachment!list.action";
		var data = {
			bizEntityId : bizEntityId,
			domId : fieldId,
			single : '1'
		};
		$.post(url, data, function(result) {
			$("#show_" + fieldId).html(result);
		});
	}
	showFiles();
	function openWebSite(webSite) {
		if (webSite != "") {
			if (webSite.indexOf('http://') >= 0)
				window.open(webSite);
			else {
				window.open('http://' + webSite);
			}
		}
	}
	function changeExam() {
		if ($('#examType1').attr('checked') || $('#examType3').attr('checked')) {
			$('#trExamUser').show();
			$('#trFile').show();
			$('#examUser').attr("validate", "required");
			$('#tdExamFileId').attr("validate", "required");
		} else {
			$('#trExamUser').hide();
			$('#trFile').hide();
			$('#examUser').removeAttr("validate");
			$('#tdExamFileId').removeAttr("validate");
		}
	}
	changeExam();

	function getSup(sid) {
		var url = '';
		if (isNotEmpty(sid)) {
			url = "${ctx}/sup/sup-basic!input.action?id=" + sid;
		} else if (isNotEmpty(supName)) {
			url = "${ctx}/sup/sup-basic!input.action?sName=" + supName;
		}
		if (url != '') {
			if (parent.TabUtils == null)
				window.open(url);
			else
				parent.TabUtils.newTab("supQuery", "明细", url, true);
		}
	}


	
    //编辑后系统的方法不知道为什么会显示已隐藏的TR，所以需要再次判断隐藏
	for(var i=0;i<4;i++){
	if($("#importLibCond"+i).val()!=""){		
		$("#importLibCond"+i).parent().parent().show();
	}else{
		$("#importLibCond"+i).parent().parent().hide();
		
	}
		
	}
</script>

<s:if test="#canEdit=='true'">
	<script type="text/javascript">
	var mapPrama = {
		sid : 'supBasicId',
		sname : 'supName',
		supType : 'supType',
		supComeFrom : 'infoSource',
		updatedDate : 'updatedDate',
		companyWebSite : 'webSite',
		comRegMoney : 'registerFund',
		comMainPro : 'mainBusiness',
		comZzGrade : 'qualiGrade',
		comHonor : 'honour',
		comProPerf : 'yeji',
		comBasFileSn : 'registerFundFileId',
		comRangFileSn : 'mainBusinessFileId',
		comZzFileSn : 'qualiGradeFileId',
		comHonoFileSn : 'honourFileId',
		comProFileSn : 'yejiFileId',
		supExamResuByCd : 'supExamResu',
		examResuPerson : 'examUser',
		examResuContent : 'examResult',
		comExamFileSn : 'examFileId'
	};

	$("#supName").quickSearch("${ctx}/sup/sup-basic!quickSearch.action", [ 'sname' ], mapPrama, {
		isDetail : true,
		isNew : true
	}, showFiles);

  
	
	var ids={
			provDesc:'supplierTypeMapCd',
			costProvCondRefId:'costProvCondRefId'		
		};
	$("#supplierTypeMapCd").quickSearch("${ctx}/cost/cost-prov-cond-ref!quickSearch.action",['typeName','provDesc'],ids,{},function(result){
		var jDom = $(result);
        //重新选择时需要先隐藏控件及清除其子输入控件中的值
        $("tr[optype=assessDim]").hide().find("input").val("").attr("validate","");
        //专业资质
        if(jDom.attr("profQualDesc")!=undefined &&
           jDom.attr("profQualDesc")!="/" && 
           jDom.attr("profQualDesc")!=""){
            //初始化值
        	$("#importLibCond0").val(jDom.attr("profQualDesc")).attr("validate","required");
        	//设置待评定情况为必填项
         	$("#assessSuppilerStatus0").attr("validate","required");
    		$("#profQualDesc").show();
        
        }
        //行业排名
        if(jDom.attr("induRankDesc")!=undefined &&
           jDom.attr("induRankDesc")!="/" && 
           jDom.attr("induRankDesc")!=""){
        	 //初始化值
        	$("#importLibCond1").val(jDom.attr("induRankDesc")).attr("validate","required");
        	$("#assessSuppilerStatus1").attr("validate","required");
    		$("#induRankDesc").show();
        }
        //企业业绩
        if(jDom.attr("entePerfDesc")!=undefined &&
                jDom.attr("entePerfDesc")!="/" && 
                jDom.attr("entePerfDesc")!=""){
	        	 //初始化值
	        	$("#importLibCond2").val(jDom.attr("entePerfDesc")).attr("validate","required");
	        	$("#assessSuppilerStatus2").attr("validate","required");
	    		$("#entePerfDesc").show();
             }
        //体系认证
        if(jDom.attr("systCertDesc")!=undefined &&
                jDom.attr("systCertDesc")!="/" && 
                jDom.attr("systCertDesc")!=""){
        	    //初始化值
	        	$("#importLibCond3").val(jDom.attr("systCertDesc")).attr("validate","required");
	        	$("#assessSuppilerStatus3").attr("validate","required");
	    		$("#systCertDesc").show();
             }             
	});
	
</script>
</s:if>

