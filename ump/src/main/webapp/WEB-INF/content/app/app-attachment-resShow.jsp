<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
 
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<head>
<%@ include file="/common/meta.jsp"%>
</head>
<style type="text/css">
	.attach_group{border-width:0 0 1px 0;border-style:solid;border-color:#dddbdc;margin:5px 20px 0px 20px;}
	.attach_subtitle{padding-left:20px;padding-top:10px;color:#0167a2;font-weight:bold;clear:both;}
</style>
<s:set var="curUser" ><%=SpringSecurityUtils.getCurrentUiid() %></s:set>
<s:if test="attachFiles.size() > 0">
<div style="clear: both">
	<s:if test="contractFiles.size()>0 || otherFiles.size()>0 || shareFiles.size()>0 ">
		<div  class="list_header_img"><img src="${ctx}/resources/images/common/down_black.gif"></img></div>
		<div class="list_header2"><span>附件</span></div>
	</s:if>
	<s:hidden id="isUploaded" name="isUploaded"></s:hidden>
	<div id="attachList" style="left:0px;top:0px;">	
	</div>
	<div id="idAllAttach">
		<c:choose>
			<c:when test="${nodeCd==100 && userCd==curUser}">
			<!-- 当处于“合同发布”步骤时，合同文本附件对发起人不可见 -->
		
			</c:when>
			<c:otherwise>	
				<s:if test="contractFiles.size()>0">
					<div class="attach_subtitle" >
						<div>合同附件
							<strong style="color:red;">( *请使用WORD软件中的修订功能对合同文本进行编辑 )</strong>
							<span id="lock_user_tip" style="float:right;padding-left:20px;line-height: 24px;height:24px;color: red;"></span>
						</div>
					</div>
					<div class="attach_group" id="idContractGroup" >
						<table width="100%">
						<!-- 列表按"上传时间"降序 -->
						<s:iterator value="contractFiles" status="st">
							<tr class="mainTr">
								<td>
									<s:url id="down" action="download" namespace="/app">
										<s:param name="fileName">${fileName}</s:param>
										<s:param name="id">${appAttachFileId}</s:param>
										<s:param name="bizModuleCd">${bizModuleCd}</s:param>
										<s:param name="filterType">${filterType}</s:param>
									</s:url> 
									<div style="float: left;padding-left:5px;"  class="ellipsisDiv_full" >
										<a href="${down}" title="${realFileName}" target="_blank">${realFileName}</a>
									</div>
								</td>
								<s:set var="entityId" value="bizEntityId"></s:set>
								<s:set var="isSharedUser" value="%{isSharedUser(#entityId)}"></s:set>
		
								<td  width="130px">
									<s:if test="#st.first">
										<div style="float: left;height:24px;line-height:24px;widht:100%;">
											<div id="btnDownFile"     style="float: left;display: block;" class="download_file" title="点这里下载附件"       onclick="downFile('${down}','${fileName}')">&nbsp;</div>
											<div id="btnLockDownFile" style="float: left;display: none;"  class="lockdown_file" title="点这里锁定并下载附件" onclick="lockDownFile('${bizEntityId}','${down}','${fileName}','${myMod}','${lockUser}')">&nbsp;</div>
											<div style="float: left;display: none;">
												<div id="btnLockFile"     style="float: left;display: none;"  class="lock_file" title="点这里锁定附件"       onclick="lockFile('${bizEntityId}','${myMod}','${lockUser}')">&nbsp;</div>
											</div>
											<div id="btnUnlockFile"   style="float: left;display: none;"  class="unlock_file"   title="点这里解锁附件"       onclick="unlockFile('${bizEntityId}','${myMod}','${lockUser}')">&nbsp;</div>
											
											<c:set var="contractType">resContract</c:set>
											<c:choose>
												<c:when test="${nodeCd==95}">
													<c:set var="contractType" value="resContractFinal"/>
												</c:when>
											</c:choose>
																
											<div id="btnUploadFile"   style="float: left;display: none;" class="upload_file" title="上传修改后的合同"  onclick="showContractUpload('${bizEntityId}','${contractType}',event)">&nbsp;
											</div>
											<s:if test="#isSharedUser==true">
												<div id="btnShareUploadFile"  style="float: left;" class="upload_file" title="上传共享附件"  onclick="openAttachmentByModel('共享附件管理','${bizEntityId}','resShare',''); return false;">&nbsp;</div>
											</s:if>
											<c:choose>
												<c:when test="${resUserCd==curUser && (nodeCd==97 || nodeCd==100 || nodeCd==101)}">
													<div id="btnPrepareUploadFile"   style="float: left;" class="upload_file" title="上传整理后的合同"  onclick="showContractUpload('${bizEntityId}','resContract',event)">&nbsp;</div>
												</c:when>
											</c:choose>
										</div>
									</s:if>
								</td>
								<td width="75px" style="padding-left:5px;"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator"))%></td>
								<td width="120px" align="right" style="padding-left:5px;"><s:property value="createdDate" /></td>
							</tr>
						</s:iterator>
						</table>
					</div>
				</s:if>
			</c:otherwise>
		</c:choose>

		<s:if test="otherFiles.size()>0">
			<div class="attach_subtitle" >
				其他附件
			</div>
		     <div class="attach_group" id="idOtherGroup">
				<table width="100%">
				<!-- 列表按"上传时间"降序 -->
				<s:iterator value="otherFiles" status="st">
					<tr  class="mainTr">
						<td>
							<s:url id="down" action="download" namespace="/app">
								<s:param name="fileName">${fileName}</s:param>
								<s:param name="id">${appAttachFileId}</s:param>
								<s:param name="bizModuleCd">${bizModuleCd}</s:param>
								<s:param name="filterType">${filterType}</s:param>
								<s:param name="uiid">${creator}</s:param>
							</s:url> 
							<div style="float: left;padding-left:5px;"  class="ellipsisDiv_full" >
								<a href="${down}" title="${realFileName}" target="_blank">${realFileName}</a>
							</div>
						</td>
						<td>&nbsp;</td>
						<td width="75px" style="padding-left:5px;"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator"))%></td>
						<td width="120px" align="right"  style="padding-left:5px;"><s:property value="createdDate" /></td>
					</tr>
				</s:iterator>
				</table>
			</div>
		</s:if>
		<s:if test="jcwFiles.size()>0">
			<div class="attach_subtitle" >
				决策委员会
			</div>
		     <div class="attach_group" id="idOtherGroup">
				<table width="100%">
				<!-- 列表按"上传时间"降序 -->
				<s:iterator value="jcwFiles" status="st">
					<tr  class="mainTr">
						<td>
							<s:url id="down" action="download" namespace="/app">
								<s:param name="fileName">${fileName}</s:param>
								<s:param name="id">${appAttachFileId}</s:param>
								<s:param name="bizModuleCd">${bizModuleCd}</s:param>
								<s:param name="filterType">${filterType}</s:param>
								<s:param name="uiid">${creator}</s:param>
							</s:url> 
							<div style="float: left;padding-left:5px;"  class="ellipsisDiv_full" >
								<a href="${down}" title="${realFileName}" target="_blank">${realFileName}</a>
							</div>
						</td>
						<td>&nbsp;</td>
						<td width="75px" style="padding-left:5px;"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator"))%></td>
						<td width="120px" align="right"  style="padding-left:5px;"><s:property value="createdDate" /></td>
					</tr>
				</s:iterator>
				</table>
			</div>
		</s:if>
		
		<s:if test="shareFiles.size()>0">
			<div class="attach_subtitle">共享人附件</div>
		     <div class="attach_group" id="idShareGroup">
				<table width="100%">
				<!-- 列表按"上传时间"降序 -->
				<s:iterator value="shareFiles" status="st">
					<tr  class="mainTr">
						<td>
							<s:url id="down" action="download" namespace="/app">
								<s:param name="fileName">${fileName}</s:param>
								<s:param name="id">${appAttachFileId}</s:param>
								<s:param name="bizModuleCd">${bizModuleCd}</s:param>
								<s:param name="filterType">${filterType}</s:param>
							</s:url> 
							
							<div style="float: left;padding-left:5px;"  class="ellipsisDiv_full" >
							<!--<a href="javascript:void(0)" title="${realFileName}" onclick="downFile('${down}','${fileName}')">${realFileName}</a>-->
								<a href="${down}" title="${realFileName}" target="_blank">${realFileName}</a>
							</div>
						</td>
						<td>&nbsp;</td>
						<td width="75px" style="padding-left:5px;"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator"))%></td>
						<td width="120px" align="right"  style="padding-left:5px;"><s:property value="createdDate" /></td>
					</tr>
				</s:iterator>
				</table>
			</div>
		</s:if>
		
		<s:if test="resApproveInfoFiles.size()>0">
			<div class="attach_subtitle" >
				附件
			</div>
		     <div class="attach_group" id="idOtherGroup">
				<table width="100%">
				<!-- 列表按"上传时间"降序 -->
				<s:iterator value="resApproveInfoFiles" status="st">
					<tr  class="mainTr">
						<td>
							<s:url id="down" action="show" namespace="/app">
								<s:param name="fileName">${fileName}</s:param>
								<s:param name="id">${appAttachFileId}</s:param>
								<s:param name="bizModuleCd">${bizModuleCd}</s:param>
								<s:param name="filterType">${filterType}</s:param>
							</s:url> 
							<div style="float: left;padding-left:5px;"  class="ellipsisDiv_full" >
								<a href="${down}" title="${realFileName}" target="_blank">${realFileName}</a>
							</div>
						</td>
						<td>&nbsp;</td>
						<td width="75px" style="padding-left:5px;"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator"))%></td>
						<td width="120px" align="right"  style="padding-left:5px;"><s:property value="createdDate" /></td>
					</tr>
				</s:iterator>
				</table>
			</div>
		</s:if>

		<div style="padding-top:5px;">
			<s:set var="haveImage">false</s:set>
			<div id="imageView" class="attach_subtitle">附件预览${smallPicName}</div>
			<s:iterator value="attachFiles">
				<c:set var="split">.</c:set>
				<c:set var="suffix">${fn:substringAfter(fileName, split)}</c:set>
				<c:set var="suffix_lower">${fn:toLowerCase(suffix)}</c:set>
				<c:choose>
					<c:when test="${fn:contains(allImage, suffix_lower)}">
					<s:url id="downSmall" action="show" namespace="/app">
						<s:param name="fileName">${smallPicName}</s:param>
						<s:param name="id">${appAttachFileId}</s:param>
						<s:param name="bizModuleCd">${bizModuleCd}</s:param>
						<s:param name="filterType">${filterType}</s:param>
					</s:url>
					<s:url id="down" action="show" namespace="/app">
						<s:param name="fileName">${fileName}</s:param>
						<s:param name="id">${appAttachFileId}</s:param>
						<s:param name="bizModuleCd">${bizModuleCd}</s:param>
						<s:param name="filterType">${filterType}</s:param>
					</s:url>
					<div style="height:100px;width:140px;float:left;">
						<s:set var="haveImage">true</s:set>
						<a target="_blank" href="${down}" title="${realFileName}" style="text-decoration: border: none;" >
						<img src="${downSmall}" title="${realFileName}" onclick="openImage('${down}')" style="cursor: pointer;height:100px;width:133px;"  alt="${realFileName}" /> 
						</a>
					</div>
					</c:when>
				</c:choose>
		    </s:iterator>
		</div>
	</div>
</div>

<%--
<div class="hengxian"></div>
 --%>

<input type="hidden" id="curUser" name="curUser" value="<%=SpringSecurityUtils.getCurrentUiid() %>" />
<input type="hidden" id="lockUser" name="lockUser" value="${lockUser}" />
<input type="hidden" id="myMod" name="myMod" value="${myMod}" />

<script language="javascript"><!--
	displayCtrlPanel();
	loadLockTip('${bizEntityId}');
	var haveImage=eval('${haveImage}');
	if (!haveImage){
		$("#imageView").remove();
	}


	// 显示更新合同对话框
	function showContractUpload(bizEntityId,bizModuleCd,event) {
		jQuery.Event(event).stopPropagation();
		ymPrompt.confirmInfo({
			icoCls:"",
			title:"合同更新",
			message:"<div id='attachDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
			useSlide:true,
			winPos:"c",
			width:400,
			height:130,
			maxBtn: false,
			allowRightMenu:true,
			afterShow:function(){
				$.post(_PATH + "/app/app-attachment!contractUpload.action?bizEntityId=" + bizEntityId+"&bizModuleCd="+bizModuleCd,
						function(result){
							$("#attachDiv").html(result);
				});
			},
			handler:doContractUpload,
			autoClose:false
		});
	}

	function doContractUpload(btn){
		if(btn=='ok'){  // "确定"按钮
			var fileName = $("#uploadInput").val();
			if (!fileName) {
				alert("请选择待上传的文件!");
				return;
			}

			uploadFileForm('${bizEntityId}','${myMod}','${lockUser}');
		}else if(btn=='cancel'){ // "取消"按钮
		}else{
		}
		ymPrompt.close();
	}

	function  reloadScAttachs() {

		var scContractId = $("#contractTempletInfoId").val();

		if (isNotEmpty(scContractId)) {
			var data = {
				id : scContractId
			};
			data.scContractId = $("#contractTempletInfoId").val();
			data.hisContId = $("#contractTempletHisId").val();
			$.post(_ctx + "/sc/sc-contract-templet-info!loadResAttachList.action",
					data, function(result) {
						$("#attachList").html(result);
					});

		}

	}
	if($("#contractTempletInfoId").val()!="undefined"){
	 reloadScAttachs();
	}
	
</script>

</s:if>
<s:else>
<div style="clear: both">
	<div id="standardCont" style="display:none;">
	<div  class="list_header_img"><img src="${ctx}/resources/images/common/down_black.gif"></img></div>
	<div class="list_header2"><span>附件</span></div>
	<div id="attachList" style="height:100%;left:0px;top:0px;">	
	</div>
	</div>
</div>
<script>
/**
 * 取合同文本库附件
 */
function reloadScAttachs(){

	$("#scattachList").html("");
	$("#standardCont").show();
	var scContractId = $("#contractTempletInfoId").val();
	if (isNotEmpty(scContractId)) {
		var data = {
			id : scContractId
		};
		data.scContractId = $("#contractTempletInfoId").val();
		data.hisContId = $("#contractTempletHisId").val();
		$.post(_ctx + "/sc/sc-contract-templet-info!loadResAttachList.action",
				data, function(result) {
					$("#attachList").html(result);	
					//合同类型
					var scContText="";
					if($("#attachList").find("#scIsStandard").val() =="1"){
						scContText="";
						}
                     //增加标准｜非标 标志 是根据 样式scContractLink 确定添加位置
                    $(".scContractLink").append(scContText);			
					if($("#attachList").find("#attachSize").val() == "0"){
						$("#standardCont").hide();
						return;
					}





					 //抓取的附件个数
	                   var graspCount=0;
						//附件
						$.each($("td[resattachname]"),function(i,td){
						  var resAttachName= $(td).attr("resattachname");
						 //如果当前附件不显示，则直不需要抓取
						 if($(td).parent().css("display") != 'none' && resAttachName){
						  //清空附件中的值
						  $(td).val("");
						  $(td).next().next().html('');	
						  $(td).next().find(":hidden").val("");
						      var resAttachName= $(td).attr("resattachname");
						  	$.each($("td[attachHdName="+resAttachName+"]"),function(){
						  		$(td).val(resAttachName);
					    		$(td).next().next().append($(this).html());
					    		graspCount++;
									});
						  	if($(td).val()==""){
										  //还原始数据，否则不使用合同库,直接使用页面上传文件成功附件不显示
								   $(td).next().next().html('<div id="show_bidContractFileId"></div>');
											}
	                       //移除合同文本库附件区域中对应附件
						  	$("tr[attachHdName="+resAttachName+"]").remove();
					     	$("div[attachHdName="+resAttachName+"]").remove();
						 }
						});
						
						   //附件
						  $.each($("div[resattachname]"),function(i,div){								
							   var resAttachName= $(div).attr("resattachname");
							     if(resAttachName.indexOf("采购数量及技术参数确认单")>-1){
	                              resAttachName="采购数量及技术参数确认单";
							      }
							   //如果当前附件不显示，则直不需要抓取
							   if($(div).parent().css("display") != 'none' && resAttachName){
								   //清空当前附件列表
								   $(div).html('');
								   //清空附件中的值
								  $(div).prev().prev().val("");	
								  //清空隐藏域中的值	
								   $(div).prev().find(":hidden").val("");								    
							    	$.each($("td[attachHdName="+resAttachName+"]"),function(){ 
											    	//设置值，为空则不允许提交
										    		$(div).prev().prev().val(resAttachName);										    		
										    		$(div).append($(this).html());
										    		graspCount++;
												});
							    		if($(div).prev().prev().val()==""){
													  //还原始数据，否则不使用合同库,直接使用页面上传文件成功附件不显示
											   $(div).html('<div id="show_bidContractFileId"></div>');
												}
							        //移除合同文本库附件区域中对应附件
							    	$("tr[attachHdName="+resAttachName+"]").remove();
							       	$("div[attachHdName="+resAttachName+"]").remove();
							   }
						  });
						  $("tr[otype='attach']").find(".operuserinfo").show();
						   //如果合同文本库中所有附件都与网批附件一一对应则不显示文本库附件区域
						   if((graspCount+"") == $("#attachList").find("#attachSize").val()){
							    $("#standardCont").hide();
							   }
                            if(typeof(autoHeight)=="function"){
                                //自动调整网批高度
                            	   autoHeight();
                                }
				});
	}

}
if($("#contractTempletInfoId").val()!="undefined"){
	reloadScAttachs();
	}
</script>
</s:else> 
