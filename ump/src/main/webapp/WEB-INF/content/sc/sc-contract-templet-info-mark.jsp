<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta charset="utf-8" />
<title>合同查看</title>
<%@ include file="/common/global.jsp"%>
<%@ include file="/common/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html" />

<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/sc/sc.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/jquery-easyui/themes/icon.css"></link>
<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/jquery-easyui/themes/default/easyui.css" />
<script charset="utf-8" type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>

<script charset="utf-8" src="${ctx}/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${ctx}/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sc/sc-contract-templet-info-common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sc/sc-contract-templet-info-bookmark.js">
</script>
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>

<script type="text/javascript" src="${ctx}/kindeditor/postil.js"></script>
</head>
<body style="background-color: #FFFFFF;">
<div id="body" style="background-color: #FFFFFF;">

<div style="width: 100%;">
<div id="funcPanelDiv" class="noprint" style="padding: 5px; border-bottom: 1px solid #8C8F94;line-height: 30px;">
<%--标记只显示在非完成网批中  2代表完成--%>
<%--

<input class="btn_green" type="button" onclick="doBookMark();" id="btBookMark" value="标记"/>
<input class="btn_green" type="button" onclick="clearBookMark();" id="btClearBookMark" value="清除标记"/> 

--%>
<input type="button" name="bt_merge" id="bt_merge" value="确定版" onclick="mergeCont()"  class="btn_blue"/> 
<input type="button" name="bt_revise" id="bt_revise" onclick="reviseCont();" value="修订版" class="btn_blue" />
<input type="button" name="btB" id="btShowMark" value="标记版" onclick="showBookMark();" class="btn_green" />
<c:if test=" ${conTempletInfoEntity.statusCd} ==50">
<input type="button" name="bt_revise" id="bt_revise" onclick="loadFlow('${conTempletInfoEntity.resApproveInfoId}');" value="流程查看" class="btn_green" />
</c:if>
<input type="button" name="bt_attachUpload" id="bt_attachUpload" value="上传附件" class="btn_green"  onclick="showUploadSingleAttachDialog('上传合同附件','','')"/>
<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
&nbsp;&nbsp;
	<s:if test="scContractParams.contLedgerId !=null && scContractParams.contLedgerId !=''">
	 	 合同台账:<span class="link" onclick="getContLedger('${scContractParams.contLedgerId}')">${scContractParams.contLedgerNo}</span>
	</s:if>
	&nbsp;&nbsp
</div>
<div style="float:left;"></div>
</div>
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
					</div >
					<div>
						合同总价：
						<input type="text" id="contractPrice" name="contractPrice" class='scPrice'   value="${conTempletInfoEntity.contractPrice }"  onblur="formatVal($(this));" /> 
					</div>
				</div>
				<div class="cont-sub-div" >
					<div >
						招标编号：
						<input type="text" id="inviteNo" name="inviteNo" class='scName' value="${conTempletInfoEntity.inviteNo}" /> 
					</div>
					<div>
						<span class="txtRed">*</span>
						责任人： 
						<input type="text" style="cursor:pointer;" class="scName" id="curUserName" name="curUserName" maxlength="20" onkeydown="return false;"  value="${scContractParams.responsiblePersons}"  readonly/>
						<input type="hidden"  name="curUserCd" id="curUserCd" value="${scContractParams.responsiblePersonCds}" />
					 </div>
					 <div >
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
					<%--
						<input type="text" id="approveNo" name="approveNo"  class='resNo' value="${conTempletInfoEntity.approveNo}"  title="${conTempletInfoEntity.approveNo}" /> 
						<input type="hidden" id="approveId" name="approveId" value="${conTempletInfoEntity.approveId}"/>--%>
					</div>
				</div>
			</div>
		  <%-- 合同最近操作信息 --%>
			<div class="sc-recuentInfo-body" align="center"  style="display:none">		
					<div class="sc-recurentInfo" id="recuentInfo">
					</div>
					<div id="msgInfo" class="sc-userMsg-body">
						<table class="sc-userMsg-conten" cellpadding="0" cellspacing="0" >
						<tr>
							<td  class="sc-userMsg-attach" rowspan="2" >
							<div id="attachList" >	
							</div>
						
							</td>
							<td  class="sc-userMsg-HisRecord ">
						        <div id="hisRecordVersion">
								</div>
										
							</td>
						</tr>
						<tr>
							<td  class="sc-userMsg-msgInfo" >
								<div id="msgContent" >
								</div>
										
							</td>
						</tr>
						</table>
					</div>	
				</div>
        	<div class="sc-markFill-body" id="markFill">
			<div class="sc-markFill-right">
					<c:if test="${not empty conTempletInfoEntity.statusCd}">
				 		<input type="button" id="fillList" name="fillList" value="填空列表" style="font-size:12px;height:26px;line-height:22px;"  class="btn_blue"/>
						<input type="button" id="oldContent" name="oldContent" value="批注原内容" style="font-size:12px;height:26px;line-height:22px;width:75px;"  class="btn_blue"/>
				 	</c:if>
				</div>
				<div class="sc-markFill-left">
				&nbsp;
				</div>
			</div>
<div style="background-color: #FFFFFF;border: 1px solid #CCC;margin-left: auto;margin-right: auto;width:99%;border-bottom: none;"  align="left">
	<div style="width: 60%; float: left;" id="contDivLeft">
	<form>
	<div style="width:100%;"></div>
	<textarea name="content" class='content'>${scContractParams.scContractHtml }</textarea>
	<input type="hidden" id="docHeight" value=""/>
	<textarea id="fillJson" style="display:none;">${scContractParams.conFillJson}</textarea>
	<input type="hidden" id="isCanInput" value="${conTempletInfoEntity.statusCd}" />
	<s:if test="conTempletInfoEntity.contractTempletInfoId =='' ">
	<input type="hidden" id="isAdd" value="1"/>
	</s:if>
	<s:else>
	<input type="hidden" id="isAdd" value="0"/>
	</s:else>
	
	<%--合同状态 --%>
	<input type="hidden" id="conStatusCd" name="conStatusCd" value="${conTempletInfoEntity.statusCd}"/>
		
	<input type="hidden" id="scContractId" value="${conTempletInfoEntity.contractTempletInfoId}"/>
	<input type="hidden" id="conTemletId" value="${conTempletInfoEntity.scContractTemplet.contractTempletId}"/>
	<input type="hidden" id="recordVersion" name="recordVersion" value="${conTempletInfoEntity.recordVersion}"/>
	<input type="hidden" id="resApproveInfoId" value="${conTempletInfoEntity.resApproveInfoId}"/>
	<input type="hidden" id="fillRecordVersion" name="fillRecordVersion" value="0"/>
	<input type="hidden" id="hisContId" name="hisContId" value="${maxHisContId}"/>
	<input type="hidden" id="markId" name="markId"/>
	<input type="hidden" id="isMark" name="isMark" value="false"/>
	<input type="hidden" id="resStatusCd" value="${statusCd}"/>
		<%--是否只能查看 --%>
	<input type="hidden" id="isOnlySee" value="${scContractParams.isOnlySee}"/>
	<input type="hidden" id="isCanClose" value="0"/>
	</form></div>
	<div style="width: 40%;float:left;padding-top:0px;background-color: #FFFFFF;" id="markTop">
	 	<div class="list" id="markList">
	 	</div>
	 	<div class="filllist" style="display:none;" id="markFillList" align="left"></div>
	</div>
</div>


</div>

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
	//fillList();
	//getContButton();
	
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
	$(this).removeClass("changeList").addClass("select_changeList");
	$(this).siblings().removeClass("select_changeList").addClass("changeList");
	
});
//获取合同填空值
$("#fillList").click(function(){
	fillList();
});

function openSheet(id){
	var url="${ctx}/res/res-approve-info!detail.action?id="+id+"&statusCd=2";	
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("156","网上审批",url,true);
	
}

</script>


