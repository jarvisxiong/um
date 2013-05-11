<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
 
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<head>
<%@ include file="/common/meta.jsp"%>
</head>

<s:set var="curUser" ><%=SpringSecurityUtils.getCurrentUiid() %></s:set>
<s:if test="attachFiles.size > 0">

<div style="clear: both">
	<s:if test="contractFiles.size()>0 || otherFiles.size()>0 || shareFiles.size()>0 ">
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
					<div class="noprint" style="height: 4px;background-color: #CCCCCC;"></div>
					<div style="padding-top:10px;padding-bottom: 15px;;line-height: 30px;">
						合同附件
				     <div id="idContractGroup">
						<s:iterator value="contractFiles" status="st">
						<s:url id="down" action="show" namespace="/wap">
										<s:param name="fileName">${fileName}</s:param>
										<s:param name="id">${appAttachFileId}</s:param>
										<s:param name="bizModuleCd">${bizModuleCd}</s:param>
										<s:param name="filterType">${filterType}</s:param>
									</s:url> 
						<div style="float:left;line-height: 30px;"><a href="${down}" title="${realFileName}" target="_blank">${realFileName}</a>&nbsp;</div>
						<div style="float:left;line-height: 30px;"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator"))%>&nbsp;</div>
						<div style="float:left;line-height: 30px;"><s:date name="createdDate" format="MM/dd"/>&nbsp;</div>
						<div style="clear: both;"></div>
						</s:iterator>
					</div>
					</div>	
					
				</s:if>
			</c:otherwise>
		</c:choose>

		<s:if test="otherFiles.size()>0">
			<div class="noprint" style="height: 4px;background-color: #CCCCCC;"></div>
			<div style="padding-top:10px;padding-bottom: 15px;;line-height: 30px;">
				其他附件
		     <div id="idOtherGroup">
				<s:iterator value="otherFiles" status="st">
				<s:url id="down" action="show" namespace="/wap">
								<s:param name="fileName">${fileName}</s:param>
								<s:param name="id">${appAttachFileId}</s:param>
								<s:param name="bizModuleCd">${bizModuleCd}</s:param>
								<s:param name="filterType">${filterType}</s:param>
							</s:url> 
				<div style="float:left;line-height: 30px;"><a href="${down}" title="${realFileName}" target="_blank">${realFileName}</a>&nbsp;</div>
				<div style="float:left;line-height: 30px;"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator"))%>&nbsp;</div>
				<div style="float:left;line-height: 30px;"><s:date name="createdDate" format="MM/dd"/>&nbsp;</div>
				<div style="clear: both;"></div>
				</s:iterator>
			</div>
			</div>
		</s:if>
		<s:if test="jcwFiles.size()>0">
			<div class="noprint" style="height: 4px;background-color: #CCCCCC;"></div>
			<div style="padding-top:10px;padding-bottom: 15px;;line-height: 30px;">
				决策委员会
		     <div id="idOtherGroup">
				<s:iterator value="jcwFiles" status="st">
				<s:url id="down" action="show" namespace="/wap">
								<s:param name="fileName">${fileName}</s:param>
								<s:param name="id">${appAttachFileId}</s:param>
								<s:param name="bizModuleCd">${bizModuleCd}</s:param>
								<s:param name="filterType">${filterType}</s:param>
							</s:url> 
				<div style="float:left;line-height: 30px;"><a href="${down}" title="${realFileName}" target="_blank">${realFileName}</a>&nbsp;</div>
				<div style="float:left;line-height: 30px;"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator"))%>&nbsp;</div>
				<div style="float:left;line-height: 30px;"><s:date name="createdDate" format="MM/dd"/>&nbsp;</div>
				<div style="clear: both;"></div>
				</s:iterator>
			</div>
			</div>
		</s:if>
		
		<s:if test="shareFiles.size()>0">
			<div class="noprint" style="height: 4px;background-color: #CCCCCC;"></div>
			<div style="padding-top:10px;padding-bottom: 15px;;line-height: 30px;">
				共享人附件
			
		     <div id="idShareGroup">
		     				
				<!-- 列表按"上传时间"降序 -->
				<s:iterator value="shareFiles" status="st">
				<s:url id="down" action="download" namespace="/wap">
								<s:param name="fileName">${fileName}</s:param>
								<s:param name="id">${appAttachFileId}</s:param>
								<s:param name="bizModuleCd">${bizModuleCd}</s:param>
								<s:param name="filterType">${filterType}</s:param>
				</s:url>
				<div style="float:left;line-height: 30px;"><a href="${down}" title="${realFileName}" target="_blank">${realFileName}</a>&nbsp;</div>
				<div style="float:left;line-height: 30px;"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator"))%>&nbsp;</div>
				<div style="float:left;line-height: 30px;"><s:date name="createdDate" format="MM/dd"/>&nbsp;</div>
				<div style="clear: both;"></div>
				</s:iterator>
			</div>
			</div>
			
			
		</s:if>
		
		<s:if test="resApproveInfoFiles.size()>0">
			<div class="noprint" style="height: 4px;background-color: #CCCCCC;"></div>
			<div style="padding-top:10px;padding-bottom: 15px;;line-height: 30px;">
				附件
		     <div id="idOtherGroup">
				<s:iterator value="resApproveInfoFiles" status="st">
				<s:url id="down" action="show" namespace="/wap">
								<s:param name="fileName">${fileName}</s:param>
								<s:param name="id">${appAttachFileId}</s:param>
								<s:param name="bizModuleCd">${bizModuleCd}</s:param>
								<s:param name="filterType">${filterType}</s:param>
							</s:url> 
				<div style="float:left;line-height: 30px;"><a href="${down}" title="${realFileName}" target="_blank">${realFileName}</a>&nbsp;</div>
				<div style="float:left;line-height: 30px;"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator"))%>&nbsp;</div>
				<div style="float:left;line-height: 30px;"><s:date name="createdDate" format="MM/dd"/>&nbsp;</div>
				<div style="clear: both;"></div>
				</s:iterator>
			</div>
			</div>
		</s:if>
		
	</div>
</div>
<script>
/**
 * 取合同文本库附件
 */
function reloadAttachs() {
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
 reloadAttachs();
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
function reloadAttachs() {
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
					if($("#attachList").find("#scIsStandard").val() == "1"){
						scContText="";
						}
					if($("#attachList").find("#attachSize").val() == "0"){
						$("#standardCont").hide();
						return;
					}
                     //增加标准｜非标 标志 是根据 样式scContractLink 确定添加位置
                    $(".scContractLink").append(scContText);
				       //抓取的附件个数
	                 var graspCount=0;
				   // 针对网批手机版附件抓取 
					$.each($("div[scresattachname]"),function(i,div){
						 //清空附件中的值
						   $(div).val("");
						   $(div).html('');
					        var resAttachName=$(div).attr("scresattachname");
					    	$.each($("td[attachHdName="+resAttachName+"]"),function(){
					    		$(div).val(resAttachName);
					    		$(div).append($(this).html().replace("float: left;",""));
					    		graspCount++;
   							});
                              //移除合同文本库附件区域中对应附件
					    	$("tr[attachHdName="+resAttachName+"]").remove();
					       	$("div[attachHdName="+resAttachName+"]").remove();
					})
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
 reloadAttachs();
</script>
</s:else> 
