<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>网批向导</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>

	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/base.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common3.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/assm/style.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/res/resRightsAdd.css"/>
	
    <script type="text/javascript" src="${ctx}/resources/js/res/resAddSelect.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/res/funs-tr.js"></script>

<style type="text/css">
.dis_none{display:none}
#container{ min-width:450px; margin:auto}
#maincontent{font-size:12px; margin:0px; padding:0px;}
.btn_step_item{
	width:125px;
	min-height: 40px;
	line-height: 22px;
	background-color: #cbcbcb;
	color: #333;
	cursor:pointer;
	padding:0px 12px;
	white-space: nowrap;
	text-overflow: ellipsis
}
.bg_blue{background-color: #0072bb;	color: #FFF;}
.bg_gray{background-color: #ccc; color:#fff;}
.step{ border:0px #999 solid; padding:0px; margin:5px 0px 5px 0px;}
.step ul li{ list-style-type:none; float:left; margin-top:2px;}
.margin_5px{margin: 5px 0px 5px 5px;}
.step_tip{ height:25px; margin:auto;}
.step_tip lable{line-height:22px;}

.short_div{
	 overflow:hidden;
	 text-overflow:ellipsis;
	 word-break:keep-all;
	 white-space:nowrap;
	 width: 120px;
}
.next_step_tip{
	height: 77px;
	background-image: url("../css/next_step.png");
	background-position:center;
	background-repeat:no-repeat;
	text-align: center;
}
.lab_step_item{
margin-right:5px;
margin-left:5px;
}
.txt_step_item{
	width:100px;
	min-height: 24px;
	line-height: 20px;
	padding:0px 5px;
}
.stepBody{width:80%; float:left;}
.cancelDiv{width:100; height:35px; vertical-align:bottom; line-height:30px;text-align: center;;}
.cancelDiv input{ height:30px;line-height:35px;}
.lab_step_ckbx{font-size:12px; line-height:18px; margin:auto 5px auto 5px;}
.lab_step_ckbx input{ vertical-align:middle;}
.stepBody .mainTable td{border-width:0px;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	//初始化第一级选项
	getNextStepData(0,$("#resRightsId").val());
});
</script>
</head>
<body>
<%--
<div id="container">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
<input type="hidden" name="resAuthTypeCd" id="resAuthTypeCd" value="ZB_ZBWJ_03"/>
<input type="hidden" name="entityTmpId" id="entityTmpId" value="${resApproveInfoId==null?entityTmpId:resApproveInfoId}"/>
<table class="mainTable">
    <tr>
        <td class="td_title">评审类别:</td>
        <td>
            <s:checkbox name="templateBean.noncontlib" id="noncontlib"  cssClass="group"></s:checkbox>招标文件
            <s:checkbox name="templateBean.contlib"  id="contlib"  cssClass="group"></s:checkbox>合同
        </td>
    </tr>
    
    <tr id="contlibno">
		<td class="td_title">合同文本库编号:</td>
		<td align="left">
			<input type="hidden" id="standard" name="templateBean.standard" value="${templateBean.standard}"/>
			<input type="hidden" id="nonstandard" name="templateBean.nonstandard" value="${templateBean.nonstandard}"/>
			<input type="hidden" id="contractTempletInfoId" name="templateBean.contractTempletInfoId" value="${templateBean.contractTempletInfoId}" />
			<input type="hidden" id="contractTempletHisId" name="templateBean.contractTempletHisId" value="${templateBean.contractTempletHisId}" />
			<input type="text" validate="required" id="contractNo" name="templateBean.contractNo" value="${templateBean.contractNo}" class="inputBorder" style="width:200px;" />
			<input type="button" id="doCont" name="doCont" value="合同文本库"  class="btn_green" onclick="doContTemplet();"  style="width:90px;"/>
		</td>
	</tr>
    
    <tr>
        <td class="td_title">审批权限:</td>
        <td>
            <s:checkbox name="templateBean.hotel"  cssClass="group"></s:checkbox>与酒店有关
        </td>
    </tr>
    
    <tr>
        <td class="td_title">项目名称:</td>
        <td>
            <input type="hidden" id="isAutosetProject"  value="0"/>
            <input type="hidden" id="isSearchDesignCon" value="QQ"/>
		    <input type="text" validate="required" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" readonly="readonly" class="inputBorderNoTip projSelect" style="width:200px;" />
		    <input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}" />
        </td>
    </tr>
    
    <tr>
        <td class="td_title">工程名称:</td>
        <td>
            <input type="text" validate="required" name="templateBean.engineeringName" value="${templateBean.engineeringName}" class="inputBorder" style="width:200px;" />
        </td>
    </tr>

    <tr>
        <td class="td_title">评审内容:</td>
        <td class="chkGroup" validate="required">
            <s:checkbox name="templateBean.approveContent1" cssClass="group" ></s:checkbox>招标文件(招标文件编号：
			<input type="text" validate="required" name="templateBean.oriBidFileCd" value="${templateBean.oriBidFileCd}" class="inputBorder" style="width:200px;" />
			)
        </td>
    </tr>
    
    <tr>
		<td class="td_title">招标计划编号:</td>
		<td align="left">	
			<input class="inputBorder" style="width:200px;" validate="required" type="text" name="templateBean.ccbpNo" value="${templateBean.ccbpNo}"  id="ccbpNo"/>
			<input type="hidden" name="templateBean.ccbpId" value="${templateBean.ccbpId}"  id="ccbpId"/>
			<input type="hidden" id="dataTypeCd" name="dataTypeCd" value="0" /><!-- 0-招标 -->
 			<input type="hidden" style="width:" name="templateBean.ccbpPlanContentDesc" id="ccbpPlanContentDesc" value="${templateBean.ccbpPlanContentDesc}" />
			<div id="ccbpPlanContentDescSpan">${templateBean.ccbpPlanContentDesc}</div>
			<script type="text/javascript">
				$('#ccbpNo').attr('title','请选择【成本系统-招标平台（工程）】中的‘招标’计划编号');
				$('#ccbpNo').quickSearch(
					'${ctx}/plan/cost-ctrl-bid-purc!quickSearchCcbp.action',
					['ccbpProjectName','ccbpDataTypeName','ccbpNo','ccbpPlanContentDesc'],
					{ccbpId:'ccbpId',ccbpNo:'ccbpNo'},
					'',
					function(jdom){
						var t = jdom.attr('ccbpPlanContentDesc');
						$('#ccbpPlanContentDesc').val("工程："+t);
						$('#ccbpPlanContentDescSpan').attr("title",t);
						$('#ccbpPlanContentDescSpan').html("工程："+t);
					},
					{projectCd:'projectCd',dataTypeCd:'dataTypeCd'}
				);
			</script>
		</td>
    </tr>
			
    <tr>
        <td class="td_title">招标范围:</td>
		<td>
			<input type="text" validate="required" name="templateBean.bidRange" value="${templateBean.bidRange}" class="inputBorder" style="width:200px;" />
		</td>
	</tr>
				
	<tr>
        <td class="td_title">施工工期:</td>
        <td>
            <input type="text" validate="required" id="fromDate" name="templateBean.fromDate" value="${templateBean.fromDate}" onfocus="WdatePicker()" onchange="onchange_DesignTotalDay()" class="Wdate inputBorder" style="width:200px;" />
            至
            <input type="text" validate="required" id="toDate" name="templateBean.toDate" value="${templateBean.toDate}"onfocus="WdatePicker()" onchange="onchange_DesignTotalDay()" class="Wdate inputBorder" style="width:200px;" />
            共
			<input type="text" validate="required" id="totalDay" name="templateBean.totalDay" value="${templateBean.totalDay}" class="inputBorder" style="width:200px;" />
            天
        </td>
    </tr>

    <tr>
        <td class="td_title">质量要求:</td>
        <td>
            <input type="text" validate="required" name="templateBean.qualityRequirement" value="${templateBean.qualityRequirement}"  class="inputBorder" />
        </td>
    </tr>
    
    <tr>
        <td class="td_title">招标模式:</td>
		<td validate="required">
            <s:checkbox name="templateBean.inviteBidModel1"  cssClass="group"></s:checkbox>工程量清单
            <br/>
            <s:checkbox name="templateBean.inviteBidModel2"  cssClass="group"></s:checkbox>费率
            <br/>
            <s:checkbox name="templateBean.inviteBidModel3"  cssClass="group"></s:checkbox>模拟清单(出图后1个月完成工程量核对)
        </td>
    </tr>

    <tr>
        <td class="td_title">计价模式:</td>
        <td validate="required">
            <s:checkbox name="templateBean.pricingModel1"  cssClass="group"></s:checkbox>总价包干
            <br/>
            <s:checkbox name="templateBean.pricingModel2"  cssClass="group"></s:checkbox>单价包干（出图一个月完成总价包干）
            <br/>
            <s:checkbox name="templateBean.pricingModel3"  cssClass="group"></s:checkbox>按实结算
		</td>
	</tr>

    <tr>
        <td class="td_title">预算金额(元):</td>
        <td>
            <input type="text" validate="required" name="templateBean.budgetAmount" value="${templateBean.budgetAmount}" onblur="formatVal($(this));" class="inputBorder" style="width:200px;" />
        </td>
    </tr>
				
    <tr>
        <td class="td_title">付款方式:</td>
        <td>
            <input type="text" validate="required" name="templateBean.paymentType" value="${templateBean.paymentType}" class="inputBorder" style="width:200px;" />
        </td>
    </tr>
					
    <tr otype="attach">
        <td class="td_title" rowspan="1">评审附件<br/>(请作为附件上传)</td>
        <td style="height:40px;" value="${templateBean.bidContractFileId}" id="bidAttachment" resattachname="招标合同文件">招标合同文件
            <input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('招标合同文件','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','bidContractFileId',event);"/>
            <input type="hidden" name="templateBean.bidContractFileId" id="bidContractFileId" value="${templateBean.bidContractFileId}"/>
            <div id="show_bidContractFileId"></div>
            <script type="text/javascript">
                showUploadedFile('${templateBean.bidContractFileId}','bidContractFileId','1');
            </script>
        </td>
    </tr>
</table>

	</form>
</div>
--%>

<div id="container">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<input name="resRightsId" id="resRightsId" type="hidden" value="${id}" />
		<input type="hidden" name="resAuthTypeCd" id="resAuthTypeCd" value=""/>
		<input type="hidden" name="entityTmpId" id="entityTmpId" value="${resApproveInfoId==null?entityTmpId:resApproveInfoId}"/>
		<input type="hidden" name="id" id="id" value=""/>
		<div id="maincontent">
		</div>
	</form>
</div>

<div style="margin-top: 0px;padding: 5px; border-bottom: 1px solid #8C8F94;display:none;" id="approverDiv">
	<input type="button" value="填写审批人" class="btn_blue_75_55"  id="btn_loadUser" onclick="doLoadUser();"/>
	<input type="button" value="编辑" class="btn_blue" id="btn_edit" onclick="backspringForm(),doDeleteUser();"/>
	<input type="button" value="发起" class="btn_blue" id="btn_apply" onclick="doSubmit();"/>
</div>
<div id="div_approve_user"></div>

</body>
</html>

<script type="text/javascript">
	
	
	//$('.projSelect').orgSelect({
	//	orgMuti:false
	//});
	//addClickAction('',true);
</script>