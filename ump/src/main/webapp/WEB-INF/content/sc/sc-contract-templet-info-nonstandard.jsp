<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>非标合同</title>
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp"%>
	<%@ include file="/common/meta.jsp"%>
	<meta http-equiv="Content-Type" content="text/html" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/sc/sc.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
 	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
  	<script charset="utf-8" src="${ctx}/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${ctx}/kindeditor/lang/zh_CN.js"></script>
<style>
form {
	margin: 0;
}
#contAttachDiv,#contAttachDiv div{
background:white;

}
</style>

</head>

<body style="background-color: #fff;">
<div id="body" style="background-color: White">
	<div class="div100">
		<div id="funcPanelDiv" class="noprint sc-btn-menu">
			<span id="showButton"> </span> 
			<span style="margin-left: 30px;"> </span> 
			&nbsp;&nbsp;
	
			<s:if test="appAttachFileList!=null && appAttachFileList.size()>0">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span class="downAttach"><a style="color: #316AA4">相关模板下载</a></span>
			</s:if>
			<span><s:url id="downUrl" action="download" namespace="/sc">
			<s:param name="fileName">hetongku.doc</s:param>
			<s:param name="realFileName">标准合同文本平台操作手册下载.doc</s:param>
			<s:param name="bizModuleCd">public</s:param>
			<s:param name="operator">inline;</s:param>
			<s:param name="id"></s:param>
			</s:url> <a href="${downUrl}" style="color:red">标准合同文本平台操作手册下载.doc</a></span>
		</div>
		<div style="float: left;"></div>
		</div>
  <form method="post" action="${ctx}/sc/sc-contract-templet-info!saveNonStandard.action" id="contForm">
		<%--是否需要自动生成合同台账 --%>
		<input type="hidden" id="ifNoContLeger" name="ifNoContLeger" value="${ifNoContLeger}"/>
  
  <div class="cont-subject-pDiv">
			<div class="cont-subject" align="left" >
			<div class="cont-sub-div">
					<div>
						<span class="txtRed">*</span>
						项目名称：	
						<input type="text" name="projectName"  id="projectName" readonly="readonly" 
						class="scName projSelect" style="cursor: pointer;"  value="${conTempletInfoEntity.projectName }"/> 
						<input type="hidden" id="projectCd" name="projectCd" value="${conTempletInfoEntity.projectCd }" />
					
					</div>
					<div>
						<span class="txtRed">*</span>
						合同编号：
						<input type="text" id="contractNo" name="contractNo" class='scName'  value="${conTempletInfoEntity.contractNo }"  onchange="isExsitConNo()" /> 
					</div>
				
					<div>
						<span class="txtRed">*</span>
						合同名称：
						<input type="text" id="contractName" name="contractName" class='scName'  value="${conTempletInfoEntity.contractName }"/> 
					</div>
					<div>
						合同总价：
						<input type="text" id="contractPrice" name="contractPrice" class='scPrice'   value="${conTempletInfoEntity.contractPrice }"  onblur="formatVal($(this));" /> 
					</div>
				</div>
				<div class="cont-sub-div" >
					<div>
						<span class="txtRed" style="margin-right:12px;">*</span>
						负责人： 
						<input type="text" style="cursor:pointer;" class="scName" id="curUserName" name="curUserName" maxlength="20" onkeydown="return false;"  value="${scContractParams.responsiblePersons}"  />
						<input type="hidden"  name="curUserCd" id="curUserCd" value="${scContractParams.responsiblePersonCds}" />
					 </div>
					<div style="padding-left:10px;">
						招标编号：
						<input type="text" id="inviteNo" name="inviteNo" class='scName' value="${conTempletInfoEntity.inviteNo}" /> 
					</div>
					<div style="padding-left:32px;">
						状态：
						<s:if test="conTempletInfoEntity.isDel==1">
							<font class="abled">已删除</font>
						</s:if>
						<s:else>
							<s:if test="conTempletInfoEntity.statusCd==10||conTempletInfoEntity.statusCd==20||conTempletInfoEntity.statusCd==30||conTempletInfoEntity.statusCd==40||conTempletInfoEntity.statusCd==45">
								<font class="abled">进行中</font>					
							</s:if>
							<s:elseif test="conTempletInfoEntity.statusCd==50">
								<font class="abled">网批中:<span	onclick="openSheet('${conTempletInfoEntity.approveId}');" style="cursor:pointer;"></span></font>
							</s:elseif>
							<s:elseif test="conTempletInfoEntity.statusCd == 60">
							 <font class="abled">网批结束</font>
							</s:elseif>
							<s:elseif test="conTempletInfoEntity.statusCd==70">
								<font class="abled">可打印</font>
							</s:elseif>
							<s:elseif test="conTempletInfoEntity.statusCd==80">
								<font class="abled">已签署</font>
							</s:elseif>
							<s:else>新增</s:else>
						</s:else>
					</div>
					<div style="display: none;">
						网批编号：
						<c:if test="${empty conTempletInfoEntity.approveId}">
							<input type="text" id="approveNo" name="approveNo"  class='resNo'  value="${conTempletInfoEntity.approveNo}" title="${conTempletInfoEntity.approveNo}" /> 
							<input type="hidden" id="approveId" name="approveId" value="${conTempletInfoEntity.approveId}"/>
						</c:if>
						<c:if test="${not empty conTempletInfoEntity.approveId}">
							<input type="text" id="approveNo" name="approveNo" readonly="readonly" 	onclick="openSheet('${conTempletInfoEntity.approveId}');";
							class='resNo' style="cursor: pointer;"  value="${conTempletInfoEntity.approveNo}"  title="${conTempletInfoEntity.approveNo}"/>
						    <input type="hidden" id="approveId" name="approveId" value="${conTempletInfoEntity.approveId}"/>
						</c:if>	
					</div>
				</div>
			</div>
  
  
		<div style="width: 100%; padding-top: 20px;">&nbsp;</div>
		<div class="sc-recuentInfo-body" align="center">
			<div class="sc-recurentInfo" id="recuentInfo"  style="display:none;">&nbsp;
			</div>
			<div id="msgInfo" class="sc-userMsg-body" style="display: block;">
				<table class="sc-userMsg-conten" cellpadding="0" cellspacing="0" >
				<tr>
					<td  class="sc-userMsg-attach" rowspan="1" >
					<%--附件列表 --%>
						<div id="attachList" >	
						</div>				
					</td>
					<td  class="sc-resNos" style="padding-top:5px;">
					<!-- 网批编号 -->
				        <div id="resNos">
				        	<table cellpadding="0" cellspacing="0" style="width: 100%; height: 94px;">
				        	<tbody>
				        		<tr>
				        			<td align="right">合同台账：</td>
				        			<td>
				        				<div>
					        			<%-- <s:if test="scContractParams.contLedgerId !=null && scContractParams.contLedgerId !=''">
										 	<span class="link" onclick="getContLedger('${scContractParams.contLedgerId}')">${scContractParams.contLedgerNo}</span>
										 	
										</s:if> --%>
										<s:if test="conTempletInfoEntity.contNo != null && conTempletInfoEntity.contNo !=''">
											<input type="text" id="contNo" name="contNo" class='resNo' onclick="getContLedger('${conTempletInfoEntity.contID}')" style="cursor:pointer;" value="${conTempletInfoEntity.contNo}" />
											<input type="hidden" id="contLedgerID" name="contID" class='resNo' value="${conTempletInfoEntity.contID}" />
										</s:if>
										<s:else>
											<input type="text" id="contNo" name="contNo" class='resNo' value="${scContractParams.contLedgerNo}" />
											<input type="hidden" id="contLedgerID" name="contID" class='resNo' value="${scContractParams.contLedgerId}" />
										</s:else>
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td align="right">商务条件/定标审批表：</td>
				        			<td>
										<div>
										 	<c:if test="${empty conTempletInfoEntity.approveId1}">
												<input type="text" id="approveNo1" name="approveNo1" class='resNo' value="${conTempletInfoEntity.approveNo1}" title="${conTempletInfoEntity.approveNo1}" /> 
												<input type="hidden" id="approveId1" name="approveId1" value="${conTempletInfoEntity.approveId1}"/>
											</c:if>
											<c:if test="${not empty conTempletInfoEntity.approveId1}">
												<input type="text" id="approveNo1" name="approveNo1" readonly="readonly" onclick="openSheet('${conTempletInfoEntity.approveId1}');"
												class='resNo' style="cursor: pointer;" value="${conTempletInfoEntity.approveNo1}" title="${conTempletInfoEntity.approveNo1}"/>
											    <input type="hidden" id="approveId1" name="approveId1" value="${conTempletInfoEntity.approveId1}"/>
											</c:if>
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td align="right">合同条款审批表：</td>
				        			<td>
										<div>
										 	<c:if test="${empty conTempletInfoEntity.approveId2}">
												<input type="text" id="approveNo2" name="approveNo2" class='resNo' value="${conTempletInfoEntity.approveNo2}" title="${conTempletInfoEntity.approveNo2}" /> 
												<input type="hidden" id="approveId2" name="approveId2" value="${conTempletInfoEntity.approveId2}"/>
											</c:if>
											<c:if test="${not empty conTempletInfoEntity.approveId2}">
												<input type="text" id="approveNo2" name="approveNo2" readonly="readonly" onclick="openSheet('${conTempletInfoEntity.approveId2}');"
												class='resNo' style="cursor: pointer;" value="${conTempletInfoEntity.approveNo2}" title="${conTempletInfoEntity.approveNo2}"/>
											    <input type="hidden" id="approveId2" name="approveId2" value="${conTempletInfoEntity.approveId2}"/>
											</c:if>
										</div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td align="right">合同评审表：</td>
				        			<td>
										<div>
										 	<c:if test="${empty conTempletInfoEntity.approveId3}">
												<input type="text" id="approveNo3" name="approveNo3" class='resNo' value="${conTempletInfoEntity.approveNo3}" title="${conTempletInfoEntity.approveNo3}" /> 
												<input type="hidden" id="approveId3" name="approveId3" value="${conTempletInfoEntity.approveId3}"/>
											</c:if>
											<c:if test="${not empty conTempletInfoEntity.approveId3}">
												<input type="text" id="approveNo3" name="approveNo3" readonly="readonly" onclick="openSheet('${conTempletInfoEntity.approveId3}');"
												class='resNo' style="cursor: pointer;" value="${conTempletInfoEntity.approveNo3}" title="${conTempletInfoEntity.approveNo3}"/>
											    <input type="hidden" id="approveId3" name="approveId3" value="${conTempletInfoEntity.approveId3}"/>
											</c:if>
										</div>
				        			</td>
				        		</tr>
				        	</tbody>
				        	</table>
						</div>
					</td>
					<!-- <td  class="sc-userMsg-HisRecord">
					历史操作记录
				        <div id="hisRecordVersion">
						</div>
					</td> -->
				</tr>
				<!-- <tr>
					<td  class="sc-userMsg-msgInfo" >
					留言信息
						<div id="msgContent" >
						</div>
					</td>
				</tr> -->
				</table>
			</div>	
		</div>
		<input type="hidden" id="docHeight" value="" /> 
		<input type="hidden" id="isCanInput" value="${conTempletInfoEntity.statusCd}" />
		<s:if test="conTempletInfoEntity.contractTempletInfoId =='' ">
		<input type="hidden" id="isAdd" value="1" />
		</s:if> 
		<s:else>
		<input type="hidden" id="isAdd" value="0" />
		</s:else>
	
			<%--合同编号 --%>
			<input type="hidden" id="scContractId" name="scContractId" value="${conTempletInfoEntity.contractTempletInfoId}"/>
			<%--合同编号2(用于判断当前合同是否是新增状态，上传附件以后会执行save方法，会生成合同ID用于关联合同，赋值给scContractId) --%>
			<input type="hidden" id="scContractId2" name="scContractId2" value="${conTempletInfoEntity.contractTempletInfoId}"/>
			<%--历史合同编号 --%>
			<input type="hidden" id="hisContId" name="hisContId" value="${maxHisContId}"/>
			<%--模板编号 --%>
			<input type="hidden" id="conTemletId" name="conTemletId" value="${conTempletInfoEntity.scContractTemplet.contractTempletId}" />
			<%--合同当前版本 --%>
			<input type="hidden" id="recordVersion" name="recordVersion" value="${conTempletInfoEntity.recordVersion}"/>
			<%--合同状态 --%>
			<input type="hidden" id="scnonstatusCd" name="scnonstatusCd" value="${conTempletInfoEntity.statusCd}"/>
			<%--填空当前版本 --%>
			<input type="hidden" id="fillRecordVersion" name="fillRecordVersion" value="0"/>
		
			<input type="hidden" id="markId" name="markId"/>
			<input type="hidden" id="isMark" name="isMark" value="false"/>
			<%--合同三级编号 --%>
			<input type="hidden" id="thirdSn" value="${scContractParams.thirdSn}"/>
			<%--合同模板类别 (20商业、地产、其他） --%>
			<input type="hidden" id="moduleCd" value="${scContractParams.templetTypeCd}"/>
			<%--是否只能查看 --%>
			<input type="hidden" id="isOnlySee" value="${scContractParams.isOnlySee}"/>
			
			<%--网批中带的字段及值 --%>
			<input type="hidden" id="resFileds"  name="resFields" value="${scContractParams.resFields}"/>
			<%--resNo：网批ID号 --%>
			<input type="hidden" id="resNo"  name="resNo" value="${scContractParams.resNo}"/>
			<%--网批中附件--%>
			<input type="hidden" id="resRela" name="resRela" value="${scContractParams.resRela}"/>
		   <%--tab Frame页面ID --%>
		   <input type="hidden" id="frameId" value="${scContractParams.frameId}"/>
		   <%-- 合同文本是否处于删除状态  --%>
		   <input type="hidden" id="isDel" name="isDel" value="${scContractParams.isDel}"/>
			</div>
		
	
	</form>
	<div style="width: 100%; padding-top: 22px;"></div>
	<div  align="center" id="curAttachmentDiv">
		<div  class="attConent">合同原始附件</div>
	         <s:url id="downUrl" action="download" namespace="/sc">
				<s:param name="fileName">${conAttachmentEntity.attachPath}</s:param>
				<s:param name="realFileName">${conAttachmentEntity.attachName}</s:param>
				<s:param name="bizModuleCd">sctemplet</s:param>
				<s:param name="operator">inline;</s:param>
				<s:param name="attchid">${conAttachmentEntity.scContractInfoAttachId}</s:param>
			</s:url>
			<span class="link" title="点击下载附件" onclick="window.open('${downUrl}'); return false;" >
				${conAttachmentEntity.attachName}
			</span> &nbsp;&nbsp;		
			<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("conAttachmentEntity.creator"),";")%>
	</div>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	
	
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch_sc.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	
	
	<script type="text/javascript" src="${ctx}/resources/js/sc/sc-contract-templet-info-common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/sc/sc-contract-templet-info-nonstandard.js"></script>
	<script type="text/javascript" src="${ctx}/kindeditor/postil.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	</div>

</body>
</html>
<script>
// 旧网批编号
var mapPrama={
	approveCd:'approveNo',
	id:'approveId'
};
$("#approveNo").quickSearch("${ctx}/res/res-approve-info!quickSearch.action",["approveCd","titleName"],mapPrama,{statusCd:'2'});
// approveNo1: 定标审批表编号, approveId1: 定标审批表ID
var mapPrama1={
		approveCd:'approveNo1',
		id:'approveId1'
	};
	// 获取编号
	$("#approveNo1").quickSearch("${ctx}/res/res-approve-info!quickSearch.action",["approveCd","titleName"],mapPrama1,{statusCd:'2'});
// approveNo2: 合同条款审批表编号, approveId2: 合同条款审批表ID
var mapPrama2={
		approveCd:'approveNo2',
		id:'approveId2'
	};
	// 获取编号
	$("#approveNo2").quickSearch("${ctx}/res/res-approve-info!quickSearch.action",["approveCd","titleName"],mapPrama2,{statusCd:'2'});
// approveNo3: 合同评审表编号, approveId3: 合同评审表ID
var mapPrama3={
		approveCd:'approveNo3',
		id:'approveId3'
	};
	// 获取编号
	$("#approveNo3").quickSearch("${ctx}/res/res-approve-info!quickSearch.action",["approveCd","titleName"],mapPrama3,{statusCd:'2'});
// contNo：合同台账编号, contName：合同台账名称
var contPrama={
		contLedgerId:'contLedgerID',
		contNo:'contNo'
};
// 获取合同台账
$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contLedgerId'],contPrama,{statusCd:'2'},showModel);
function showModel(){
	
}
</script>

<c:if test="${not empty conTempletInfoEntity.approveId1}">
	<c:if test="${empty conTempletInfoEntity.contractTempletInfoId}">
		<script language="javascript">
			isHide_bt_attachUpload = true;
		</script>
	</c:if>
</c:if>