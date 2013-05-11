<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta charset="utf-8" />
<title>合同合并</title>
<%@ include file="/common/global.jsp"%>
<%@ include file="/common/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/sc/sc.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/jquery-easyui/themes/icon.css"></link>
<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/jquery-easyui/themes/default/easyui.css" />
<script charset="utf-8" type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
<script charset="utf-8" src="${ctx}/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${ctx}/kindeditor/lang/zh_CN.js"></script>

</head>
<body style="background-color: White !important">

<%-- 从合同评审表跳转到合同文本库时,高亮定位到指定批注位置 --%>
<input type="hidden" id="highMarkId" name="highMarkId" value="${highMarkId}" />
<%-- 用来控制是否显示 "批注"按钮,"填空列表","批注列表"上的按钮和事件 --%>
<input type="hidden" id="displayMarkBtn" name="displayMarkBtn" value="false" />
<%-- 用来判断当前登录人是否 合同评审表的最后审批人 --%>
<input type="hidden" id="isLastUser" name="isLastUser" value="${isLastUser}" />

<%-- 如果登录人是管理员 --%>
<security:authorize ifAnyGranted="A_CONT_MOULD_ADMIN">
	<script>
	//alert("666");
	$("#displayMarkBtn").val('true');
	</script>
</security:authorize>

<%-- 合同状态为进行中,并且当前登录人是责任人 --%>
<s:if test="(conTempletInfoEntity.statusCd==10||conTempletInfoEntity.statusCd==20||conTempletInfoEntity.statusCd==30
	||conTempletInfoEntity.statusCd==40||conTempletInfoEntity.statusCd==45)">
	<c:if test="${fn:contains(scContractParams.responsiblePersonCds,curUser)}">
		<script>
		//alert("555");
		$("#displayMarkBtn").val('true');
		</script>
	</c:if>
</s:if>

<%-- 合同状态为网批中,并且当前登录人是合同评审表最后审批人 --%>
<s:if test="(conTempletInfoEntity.statusCd==50)">
	<c:if test="${isLastUser=='1'}">
		<script>
		//alert("444");
		$("#displayMarkBtn").val('true');
		</script>
	</c:if>
</s:if>





<form method="post" action="${ctx}/sc/sc-contract-templet-info!save.action" id="contForm">
<div id="body" style="background-color: White">
	
	<div style="width: 100%;">
		<div id="funcPanelDiv" class="noprint" style="padding: 5px; border-bottom: 1px solid #8C8F94;line-height: 30px;overflow: hidden;">
		<span id="showButton">
		</span>
		&nbsp;&nbsp;
		<span id="contractStatus"></span>
		<span>
			 <s:url id="downUrl" action="download" namespace="/sc">
				<s:param name="fileName">hetongku.doc</s:param>
				<s:param name="realFileName">标准合同文本平台操作手册下载.doc</s:param>
				<s:param name="bizModuleCd">public</s:param>
				<s:param name="operator">inline;</s:param>
				<s:param name="id"></s:param>
		     </s:url> <a href="${downUrl}" class="txtRed">标准合同文本平台操作手册下载.doc</a>
		</span>
		</div>
	</div>

	<div id="conTitle" style="background-color: White;padding-left:10px;">
	
		<div id="printInfo" style="width:100%;padding-left:0px;"></div>
		<div style="float:left;width:100%;padding-top:5px;padding-left:0px;">
			<div style="float:left;">
				<font class="txtRed">*</font>
				项目名称：	
				<input type="text" name="projectName"  id="projectName" readonly="readonly" style="width:120px !important;"
				class="scName projSelect" style="cursor: pointer;"  value="${conTempletInfoEntity.projectName }"/> 
				<input type="hidden" id="projectCd" name="projectCd" value="${conTempletInfoEntity.projectCd }" />
			
			</div>
			<div style="float:left;">
				<font class="txtRed">*</font>
				合同编号：
				<input type="text" id="contractNo" class='scName' style="width:120px !important;"  value="${conTempletInfoEntity.contractNo }"  onchange="isExsitConNo()"/>
				<input type="hidden" name="contractNo" value="${conTempletInfoEntity.contractNo }" value="${conTempletInfoEntity.contractNo }" /> 
			</div>
			<div style="float:left;">
			         合同总价：
				<input type="text" id="contractPrice" name="contractPrice" class='scName' style="width:120px !important;"  value="${conTempletInfoEntity.contractPrice }"  onblur="formatVal($(this));" /> 
			</div>
			
			<div style="float:left;">
				<font class="txtRed">*</font>
				合同名称：
				<input type="text" id="contractName" name="contractName" class='scName'  style="width:120px !important;" value="${conTempletInfoEntity.contractName }"/> 
			</div >
			
		</div>
		<div style="float:left;width:100%;padding-bottom:5px;padding-left:0px;">
			<div style="float:left;">
				<span class="txtRed" style="margin-right: 12px;">*</span>
				负责人： 
				  <input type="text" style="cursor:pointer;" class="scName" id="curUserName" maxlength="20"  style="width:120px !important;" onkeydown="return false;"  value="${scContractParams.responsiblePersons}"  />
				  <input type="hidden" name="curUserName" value="${scContractParams.responsiblePersons}"  />
				  <input type="hidden"  name="curUserCd" id="curUserCd" value="${scContractParams.responsiblePersonCds}" />
			
			 </div>
			<div style="float:left;padding-left:10px;">
				招标编号：
				<input type="text" id="inviteNo" name="inviteNo" class='scName' style="width:120px !important;"  value="${conTempletInfoEntity.inviteNo}" /> 
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
			<div style="float:left;display: none;">
				网批编号：
				<c:if test="${empty conTempletInfoEntity.approveId}">
				<input type="text" id="approveNo" name="approveNo"  class='scName'  value="${conTempletInfoEntity.approveNo}" /> 
				<input type="hidden" id="approveId" name="approveId" value="${conTempletInfoEntity.approveId}"/>
				</c:if>
				<c:if test="${not empty conTempletInfoEntity.approveId}">
				<input type="text" id="approveNo" name="approveNo" readonly="readonly" 
				onclick="openSheet('${conTempletInfoEntity.approveId}');";
				class='scName' style="width:120px !important;cursor: pointer;"  value="${conTempletInfoEntity.approveNo}" />
				</c:if>
			</div>
		</div>
	</div>

	<div style="border: 0px solid #CCC;width:99%;background-color: #FFFFFF;clear:both;margin-left: auto;margin-right: auto; align="center">
		<div style="clear:both;align:center;width:100%;height:60px;display: none;" id="recuentInfo">
		</div>
		<div id="msgInfo" style="float:left;width:100%;background-color: #FFFFFF;">
			<table style="border: 1px solid #DDDBDC;background-color: #FFFFFF;width:100%" cellpadding="0" cellspacing="0" >
			<tr>
				<td style="width:73%;border-right: 1px solid #DDDBDC; vertical-align: top;" rowspan="2" >
				<div id="attachList" style="height:100%;left:0px;top:0px;">	
				</div>
			
				</td>
				<td style="width:28%;border-bottom: 0px solid #DDDBDC;vertical-align: top; padding-top:5px;">
				<!-- 网批编号 -->
			        <div id="resNos" style="width:100%;height:100%;overflow:auto;">
			        	<table cellpadding="0" cellspacing="0" style="width: 100%; height: 94px;">
			        	<tbody>
			        		<tr>
			        			<td align="right">合同台账：</td>
			        			<td>
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
			        			</td>
			        		</tr>
			        		<tr>
			        			<td align="right">商务条件/定标审批表：</td>
			        			<td>
									<div>
										<c:if test="${empty conTempletInfoEntity.approveId1}">
										<input type="text" id="approveNo1" name="approveNo1"  class='scName'  value="${conTempletInfoEntity.approveNo1}" /> 
										</c:if>
										<c:if test="${not empty conTempletInfoEntity.approveId1}">
										<input type="text" id="approveNo1" name="approveNo1" readonly="readonly" 
										onclick="openSheet('${conTempletInfoEntity.approveId1}');";
										class='scName' style="width:120px !important;cursor: pointer;"  value="${conTempletInfoEntity.approveNo1}" />
										</c:if>
										<input type="hidden" id="approveId1" name="approveId1" value="${conTempletInfoEntity.approveId1}"/>
									</div>
			        			</td>
			        		</tr>
			        		<tr>
			        			<td align="right">合同条款审批表：</td>
			        			<td>
									<div>
										<c:if test="${empty conTempletInfoEntity.approveId2}">
										<input type="text" id="approveNo2" name="approveNo2"  class='scName'  value="${conTempletInfoEntity.approveNo2}" /> 
										</c:if>
										<c:if test="${not empty conTempletInfoEntity.approveId2}">
										<input type="text" id="approveNo2" name="approveNo2" readonly="readonly" 
										onclick="openSheet('${conTempletInfoEntity.approveId2}');";
										class='scName' style="width:120px !important;cursor: pointer;"  value="${conTempletInfoEntity.approveNo2}" />
										</c:if>
										<input type="hidden" id="approveId2" name="approveId2" value="${conTempletInfoEntity.approveId2}"/>
									</div>
			        			</td>
			        		</tr>
			        		<tr>
			        			<td align="right">合同评审表：</td>
			        			<td>
									<div>
										<c:if test="${empty conTempletInfoEntity.approveId3}">
										<input type="text" id="approveNo3" name="approveNo3"  class='scName'  value="${conTempletInfoEntity.approveNo3}" /> 
										</c:if>
										<c:if test="${not empty conTempletInfoEntity.approveId3}">
										<input type="text" id="approveNo3" name="approveNo3" readonly="readonly" 
										onclick="openSheet('${conTempletInfoEntity.approveId3}');";
										class='scName' style="width:120px !important;cursor: pointer;"  value="${conTempletInfoEntity.approveNo3}" />
										</c:if>
										<input type="hidden" id="approveId3" name="approveId3" value="${conTempletInfoEntity.approveId3}"/>
									</div>
			        			</td>
			        		</tr>
			        	</tbody>
			        	</table>
					</div>
				</td>
				<!-- <td style="width:25%;height:100px;border-bottom: 2px solid #DDDBDC;">
				历史记录
			        <div id="hisRecordVersion" style="width:100%;height:100%;overflow:auto;">
					</div>
				</td> -->
			</tr>
			<!-- <tr>
				<td style="width:50%;border-bottom: 1px solid #DDDBDC;">
				留言
					<div id="msgContent" style="width:100%;height:190px;">
					</div>
				</td>
			</tr> -->
			</table>
		</div>
	</div>

	<div style="background-color: #FFFFFF;border: 1px solid #CCC;margin-left: auto;margin-right: auto;width:99%;border-bottom: none;" id="markFill" align="left">
		<div style="width: 60%; float: left;">
			<div style="background-color: #FFFFFF;margin-left: auto;
			margin-right: auto;width:100%;border-bottom: none;border-top: none;height:26px;padding-top: 2px;padding-bottom: 2px;" align="left">
				<c:if test="${empty conTempletInfoEntity.statusCd} && scContractParams.isOnlySee != 1 ">
				<input class="btn_green" type="button" onclick="doNext();" id="btNext" value="下一个"/>
				</c:if>
				<s:if test="conTempletInfoEntity.statusCd==10 && scContractParams.isOnlySee != 1 ">
				<input class="btn_green" type="button" onclick="doNext();" id="btNext" value="下一个"/>
				</s:if>
				<!-- conTempletInfoEntity.statusCd==10 || conTempletInfoEntity.statusCd==20 ||  -->
				<s:if test="((conTempletInfoEntity.statusCd==30 || conTempletInfoEntity.statusCd==40 || conTempletInfoEntity.statusCd==45) && scContractParams.isOnlySee!=1 && scContractParams.isDel!=1) || #attr.displayMarkBtn!='false'">
				<%-- <c:if test="${displayMarkBtn!='false'}"> --%>
				<input type="button" name="mark" id="bt_mark" value="批注" onclick="doMark()" class="btn_blue" />
				<input type="button" value="提示" onclick="doShowTip();" class="btn_grey" />
				</s:if>
				<%--
				<input type="button" name="clear_css" id="clear_css" value="清除样式" onclick="clearEditorCss()" style="height:26px;line-height:22px;text-align:center;" class="btn_blue" />
				--%>
			</div>
		</div>
		<div style="width: 40%;float:left;padding-top:0px;background-color: #FFFFFF;">
			<div style="text-align: left;width:100%;padding-top: 2px;padding-bottom: 2px;">
		 		<input type="button" id="fillList" name="fillList" value="填空列表" class="btn_nav1"/>
				<input type="button" id="oldContent" name="oldContent" value="批注原内容" class="btn_nav1" style="width:80px;"/>
				<input type="button" value="返回网批" class="btn_nav1" onclick="parent.TabUtils.showTab({data:{tabId:'156'}});"/>
		 	</div>
		</div>
	</div>

	<div style="background-color: #FFFFFF;border: 1px solid #CCC;margin-left: auto;margin-right: auto;width:99%;border-bottom: none;" align="left">
		<div style="width: 60%; float: left;" id="contDivLeft">
		
			<%--合同文本内容 --%>
			<textarea name="content" class='content' id="content" style="display: none;">${scContractParams.scContractHtml }</textarea>
			<input type="hidden" id="docHeight" value=""/>
			<%--合同文本填空内容 --%>
			<textarea id="fillJson" style="display:none;" name="fillJson">${scContractParams.conFillJson}</textarea>
			<input type="hidden" id="isCanInput" value="${conTempletInfoEntity.statusCd}" />
			<s:if test="conTempletInfoEntity.contractTempletInfoId =='' ">
			<input type="hidden" id="isAdd" value="1"/>
			</s:if>
			<s:else>
			<input type="hidden" id="isAdd" value="0"/>
			</s:else>
			
			<%--合同编号 --%>
			<input type="hidden" name="scContractId" id="scContractId" value="${conTempletInfoEntity.contractTempletInfoId}"/>
			<%--历史合同编号 --%>
			<input type="hidden" id="hisContId" name="hisContId" value="${maxHisContId}"/>
			<%--模板编号 --%>
			<input type="hidden" id="conTemletId" name="conTemletId" value="${conTempletInfoEntity.scContractTemplet.contractTempletId}" />
			<%--合同当前版本 --%>
			<input type="hidden" id="recordVersion" name="recordVersion" value="${conTempletInfoEntity.recordVersion}"/>
			<%--合同状态 --%>
			<input type="hidden" id="conStatusCd" name="conStatusCd" value="${conTempletInfoEntity.statusCd}"/>
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
			<input type="hidden" id="resRela"  name="resRela" value="${scContractParams.resRela}"/>
			<%--是否是删除状态--%>
			<input type="hidden" id="isDel"  name="isDel" value="${scContractParams.isDel}"/>
			
			<s:if test="conTempletInfoEntity.statusCd==70">
				<input type="hidden" id="isAccomplishNeedUpload" value="1"/>
			</s:if>
			<input type="hidden" id="isSignatureUpload" ></input>
			<%--tab Frame页面ID --%>
		    <input type="hidden" id="frameId" value="${scContractParams.frameId}"/>
    
		</div>
			
		<div style="width: 40%;float:left;padding-top:0px;" id="markTop">
		 	<div class="list" id="markList">
		 	
		 	</div>
		 	<div class="filllist" style="display:none;" id="markFillList" align="left">
		 	
		 	</div>
		</div>
	</div>

	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>

	<script type="text/javascript" src="${ctx}/resources/js/sc/sc-contract-templet-info-common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/sc/sc-contract-templet-info-merge.js"></script>
	<script type="text/javascript" src="${ctx}/kindeditor/postil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch_sc.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>

</div>
   
</form>

</body>
</html>
<script>
var resultCollection = "";
</script>
<c:if test="${not empty scContractParams.conFillJson}">
<script>
resultCollection = ${scContractParams.conFillJson};
</script>
</c:if>
<script>
init();
	 
//初始化
function init(){
	//如果非合并状态
	getContButton();

}
$("#oldContent").click(function(){
	//还原填空样式
	 $.each(resultCollection, function (index, item){
			var fillId = item.contractTempletFillId;
			$("#keEditIframe").contents().find("span[id=" + fillId + "]").each(function(){
			});
		 });
	 $("#markList").show();
	 $("#markFillList").hide();
	 fillOldChange("oldContent");
});
//获取合同填空值
$("#fillList").click(function(){
	fillList();
});

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
	contNo:'contNo',
	contLedgerID:'contLedgerID'
};
// 获取合同台账
$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],contPrama,{statusCd:'2'});


</script>

