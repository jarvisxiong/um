<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>

<!--
-->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/cost-ctrl.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisCont.css"  />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/customInput/customInput.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bisCont.js"></script>
<title>商业合同管理</title>

</head>
<body>
<s:form id="processForm" action="bis-cont!save.action" method="post">
<input type="hidden" id="bisContId" name="bisContId" value="${bisContId}"/>
<input type="hidden" id="statusCd" name="statusCd" value="${statusCd}"/>
<input type="hidden" name="activeBl" value="${activeBl}"/>
<input type="hidden" name="entityTmpId" value="${entityTmpId}"/>
<input type="hidden" id="recordVersion" name="recordVersion" value="${recordVersion}"/>
<input type="hidden" id="attachFlg" name="attachFlg" value="${attachFlg}"/>
<input type="hidden" id="approvalAttachFlg" name="approvalAttachFlg" value="${approvalAttachFlg}"/>
<input type="hidden" id="contApprovalId" name="contApprovalId" value="${contApprovalId}"/>
<input type="hidden" id="contBigTypeCd" name="contBigTypeCd" value="${contBigTypeCd}"/>
<input type="hidden" id="contSmallTypeCd" name="contSmallTypeCd" value="${contSmallTypeCd}"/>
<input type="hidden" name="bisTenantId" value="${bisTenantId}"/>
<input type="hidden" name="hiddenShopId" value="${bisShopId}"/>
<input type="hidden" name="hiddenStoreIds" value="${bisStoreIds}"/>
<input type="hidden" name="hiddenFlatIds" value="${bisFlatIds}"/>
<%-- 
<input type="hidden" id="operator" value="${operator}"/>
<input type="hidden" id="contApprover" value="${contApprover}"/>
<input type="hidden" id="mustApprover" value="${mustApprover}"/>
<input type="hidden" id="chargerTypeArr" name="chargerTypeArr" value="${chargerTypeArr}"/>
--%>

<div class="title_bar">
	<div style="font-weight:bold;padding-left:8px;font-size:14px;float:left;"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContBigType()" value="contBigTypeCd"/>类<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContSmallType()" value="contSmallTypeCd"/>详细信息</div>
	<div style="padding-left:10px;font-size:14px;float:left;">
		<span style="color: #333333">合同状态：</span>
		<span style="color: red; ">
			<s:if test="statusCd==3">历史版本</s:if>
			<s:else>变更待审核</s:else>
			<%-- 
			<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisContStatus()" value="statusCd"/>
			--%>
		</span>
	</div>
	<div style="float:right;font-size:12px;padding-right:10px;line-height:30px;">
		<s:if test="statusCd==6">
			<security:authorize ifAnyGranted="A_CONT_CHECK">
			<input type="button" value="审核" class="btn_blue" onclick="doApproveChange();" />
			</security:authorize>
			<security:authorize ifAnyGranted="A_CONT_REJECT">
			<input type="button" value="驳回" class="btn_red" onclick="doBackChange();" />
			</security:authorize>
			<input type="button" value="返回" class="btn_green" onclick="goBackHis();" style="margin-top: 4px;"/>
		</s:if>
		<s:else>
			<input type="button" value="返回" class="btn_green" onclick="goBackHisVersion();" style="margin-top: 4px;"/>
		</s:else>
	</div>
</div>

<div id="resultDiv" style="overflow: scroll; overflow-x:hidden; width:100%;">
<table style="width:100%;" cellspacing="0" cellpadding="0" >
	<tr>
		<td style="padding-top: 20px; padding-left: 8px; padding-right: 8px;">
			<table class="tb_noborder" cellspacing="0" cellpadding="0" style="width:100%;">
				<col width="80"/>
				<col width="200"/>
				<col width="200"/>
				<col />
				<tr style="height:30px; font-size: 12px; color: #464646">
					<td style="padding-left: 10px;">项目名称：</td>
					<td style="padding: 2px 0px;">
					<input type="text" validate="required" class="inputBorder" id="bisProjectName" name="bisProjectName" value="${bisProjectName}" style="cursor: pointer;color: #ff0000;width:100%;border-left:2px solid red;;" />
					<input type="hidden" id="bisProjectId" name="bisProjectId" value="${bisProjectId}"/>
					</td>
					<td colspan="2"></td>
				</tr>
				<tr style="height:30px; font-size: 12px; color: #464646">
					<td style="padding-left: 10px;">合同编号：</td>
					<td style="padding: 2px 0px;">
						<input type="text" validate="required" class="inputBorder" name="contNo" value="${contNo}" style="width:100%;" />
					</td>
					<td style="text-align: right;">合同类型：</td>
					<td align="left">
						<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContBigType()" value="contBigTypeCd"/>-<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContSmallType()" value="contSmallTypeCd"/>
					</td>
				</tr>
				<s:if test="creator!=null">
				<tr style="height:30px; font-size: 12px; color: #464646">
					<td style="padding-left: 10px; padding-top: 2px; padding-bottom: 2px;">创建人：</td>
					<td style="text-align: left;">
						<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %><input type="hidden" name="creator" value="${creator}" />
					</td>
					<td style="text-align: right;padding-top: 2px; padding-bottom: 2px;">创建时间：</td>
					<td align="left">
						<s:date name="createdDate" format="yyyy-MM-dd"/><input type="hidden" name="createdDate" value="${createdDate}" />
					</td>
				</tr>
				</s:if>
				<s:if test="updator!=null">
				<tr style="height:30px; font-size: 12px; color: #464646">
					<td align="right" style="padding-left: 10px; padding-top: 2px; padding-bottom: 2px;">更新人：</td>
					<td style="text-align: left;">
						<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("updator")) %><input type="hidden" name="updator" value="${updator}" />
					</td>
					<td style="text-align: right;padding-top: 2px; padding-bottom: 2px;">更新时间：</td>
					<td align="left">
						<s:date name="updatedDate" format="yyyy-MM-dd"/><input type="hidden" name="updatedDate" value="${updatedDate}" />
					</td>
				</tr>
				</s:if>
				<s:if test="checkUserCd!=null">
				<tr style="height:30px; font-size: 12px; color: #464646">
					<td align="right" style="padding-left: 10px; padding-top: 2px; padding-bottom: 2px;">审核人：</td>
					<td style="text-align: left;">
						<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("checkUserCd")) %><input type="hidden" name="checkUserCd" value="${checkUserCd}" />
					</td>
					<td style="text-align: right;padding-top: 2px; padding-bottom: 2px;">审核时间：</td>
					<td align="left">
						<s:date name="checkDate" format="yyyy-MM-dd"/><input type="hidden" name="checkDate" value="${checkDate}" />
					</td>
				</tr>
				</s:if>
				<s:if test="statusCd==3">
				<s:if test="changeUser!=null">
				<tr style="height:30px; font-size: 12px; color: #464646">
					<td align="right" style="padding-left: 10px; padding-top: 2px; padding-bottom: 2px;">变更人：</td>
					<td style="text-align: left;">
						<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("changeUser")) %><input type="hidden" name="changeUser" value="${changeUser}" />
					</td>
					<td style="text-align: right;padding-top: 2px; padding-bottom: 2px;">变更时间：</td>
					<td align="left">
						<s:date name="changeDate" format="yyyy-MM-dd"/><input type="hidden" name="changeDate" value="${changeDate}" />
					</td>
				</tr>
				</s:if>
				</s:if>
				<s:if test="passedDate!=null">
				<tr style="height:30px; font-size: 12px; color: #464646">
					<td style="padding-left: 10px;">已过时长：</td>
					<td>
						${passedDate}天
					</td>
					<td style="text-align: right;">剩余时长：</td>
					<td>
						${remainDate}天
					</td>
				</tr>
				</s:if>
			</table>
		</td>
	</tr>
	
	<tr>
		<td style="padding-top: 10px; padding-left: 8px; padding-right: 8px;">
			<table class="sup_table" cellspacing="0" cellpadding="0" style="background-color:#e4e7ec;overflow:hidden;width:100%;">
				<col />
				<tr style="height:38px;background-color: #909090;ont-size:14px;">
					<td style="padding-left: 10px; font-weight: bold; font-size: 12px;"><span style="color: #fff">合同明细信息</span></td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td style="padding-left: 8px; padding-right: 8px;">
		<div id="detailContent" >
			<s:if test="contBigTypeCd==1 && contSmallTypeCd==1">
				<jsp:include page="bis-cont-storeRent.jsp">
				<jsp:param value="1" name="contBigTypeCd"/>
				</jsp:include>
			</s:if>
			<s:elseif test="contBigTypeCd==1 && contSmallTypeCd==2">
				<jsp:include page="bis-cont-storeBack.jsp">
				<jsp:param value="1" name="contBigTypeCd"/>
				</jsp:include>
			</s:elseif>
			<s:elseif test="contBigTypeCd==1 && contSmallTypeCd==3">
				<jsp:include page="bis-cont-storeProp.jsp">
				<jsp:param value="1" name="contBigTypeCd"/>
				</jsp:include>
			</s:elseif>
			<s:elseif test="contBigTypeCd==1 && contSmallTypeCd==4">
				<jsp:include page="bis-cont-storeEntr.jsp">
				<jsp:param value="1" name="contBigTypeCd"/>
				</jsp:include>
			</s:elseif>
			<s:elseif test="contBigTypeCd==1 && contSmallTypeCd==6">
				<jsp:include page="bis-cont-storeOwnerProp.jsp">
				<jsp:param value="1" name="contBigTypeCd"/>
				<jsp:param value="${canEdit}" name="canEdit"/>
				</jsp:include>
			</s:elseif>
			<s:elseif test="contBigTypeCd==2">
				<jsp:include page="bis-cont-flatProp.jsp">
				<jsp:param value="2" name="contBigTypeCd"/>
				</jsp:include>
			</s:elseif>
			<s:else>
				<jsp:include page="bis-cont-multi.jsp">
				<jsp:param value="3" name="contBigTypeCd"/>
				</jsp:include>
			</s:else>
		</div>
		</td>
	</tr>
	
	<tr id="trBisContCredit" <s:if test="bisContId!=null && (bisContCredits==null || bisContCredits.size()==0)">style="display: none;"</s:if>>
		<td style="padding-top: 20px; padding-left: 8px; padding-right: 8px;">
			<div style="border: 1px solid #909090;">
			<table class="sup_table" cellspacing="0" cellpadding="0" style="background-color:#e4e7ec;overflow:hidden;width:100%;">
				<col />
				<col width="80px;"/>
				<tr style="height:10px;background-color: #909090;">
				<td colspan="2"></td>
				</tr>
				<tr>
					<td colspan="2">
					<div style="padding:10px;border-bottom: 1px solid #AAABB0;">
						<table style="width:100%;">
							<col width="100"/>
							<col />
							<tr>
								<td style="font-weight: bold; color: #464646">
									资信资料
								</td>
								<td align="right">
									
								</td>
							</tr>
						</table>
					</div>
					</td>
				</tr>
			</table>
			<table class="content_table">
				<tr>
					<td colspan="2" id="bisContCreditPage">
						<jsp:include page="bis-cont-creditList.jsp">
							<jsp:param value="${bisContId}" name="bisContId"/>
							<jsp:param value="${statusCd}" name="statusCd"/>
							<jsp:param value="${canEdit}" name="canEdit"/>
						</jsp:include>
					</td>
				</tr>
			</table>
			</div>
		</td>
	</tr>
	<tr>
		<td style="padding-top: 20px; padding-left: 8px; padding-right: 8px;">
			<table class="mainTable" cellspacing="0" cellpadding="0" style="overflow:hidden;width:100%;border:0;">
				<col width="100"/>
				<col />
				<col width="120"/>
				<tr style="height:10px;background-color: #909090;">
				<td colspan="3"></td>
				</tr>
				<tr style="height: 80px;">
				<td style="padding-left: 10px;font-weight: bold; ">合同内部批文</td>
				<td style="padding-top: 2px;padding-bottom: 2px;"><textarea id="contApproval" class="inputBorder contentTextArea" style="width:100%;height:80px;" name="contApproval" maxlength="4000" >${contApproval}</textarea></td>
				<td align="center">
					<a href="javascript:showAttachment('${contApprovalId}');" >
						<img id="contApprovalAttachId" <s:if test="approvalAttachFlg==1">src="${ctx}/resources/images/common/atta_y.gif"</s:if><s:else>src="${ctx}/resources/images/common/atta.gif"</s:else> />
					</a>
				</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td style="padding-top: 20px; padding-left: 8px; padding-right: 8px;">
			<table class="mainTable" cellspacing="0" cellpadding="0" style="overflow:hidden;width:100%;border: 1px solid #909090;">
				<col width="100"/>
				<col />
				<col width="120"/>
				<tr style="height:10px;background-color: #909090;">
				<td colspan="3"></td>
				</tr>
				<tr style="height: 150px;">
				<td style="padding-left: 10px;font-weight: bold;">合同条款</td>
				<td style="padding-top: 2px;padding-bottom: 2px;"><textarea class="inputBorder contentTextArea" style="width:100%;height:150px;" name="contContent">${contContent}</textarea></td>
				<td align="center">
					<a href="javascript:showAttachment('${bisContId}');" >
						<img id="bisContAttachId" <s:if test="attachFlg==1">src="${ctx}/resources/images/common/atta_y.gif"</s:if><s:else>src="${ctx}/resources/images/common/atta.gif"</s:else> />
					</a>
				</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr id="trBisContAddi" <s:if test="bisContId!=null && (bisContAdditionals==null || bisContAdditionals.size()==0)">style="display: none;"</s:if>>
		<td style="padding-top: 20px; padding-left: 8px; padding-right: 8px;">
			<div style="border: 1px solid #909090;">
			<table class="sup_table" cellspacing="0" cellpadding="0" style="background-color:#e4e7ec;overflow:hidden;width:100%;">
				<col />
				<col width="80px;"/>
				<tr style="height:10px;background-color: #909090;">
				<td colspan="2"></td>
				</tr>
				<tr>
					<td colspan="2">
					<div style="padding:10px;border-bottom: 1px solid #AAABB0;">
						<table style="width:100%;">
							<col width="100"/>
							<col />
							<tr>
								<td style="font-weight: bold; color: #464646">
									补充合同
								</td>
								<td align="right">
									
								</td>
							</tr>
						</table>
					</div>
					</td>
				</tr>
			</table>
			<table class="content_table">
				<tr>
					<td colspan="2" id="bisContAddiPage">
						<jsp:include page="bis-cont-additionalList.jsp">
							<jsp:param value="${bisContId}" name="bisContId"/>
							<jsp:param value="${statusCd}" name="statusCd"/>
							<jsp:param value="false" name="canEdit"/>
						</jsp:include>
					</td>
				</tr>
			</table>
			</div>
		</td>
	</tr>
	
	<tr>
		<td style="padding-top: 20px; padding-left: 8px; padding-right: 8px; ">
			<div style="border: 1px solid #909090;">
			<table class="sup_table" cellspacing="0" cellpadding="0" style="background-color:#e4e7ec;overflow:hidden;width:100%; ">
				<col />
				<col width="80px;"/>
				<tr style="height:10px;background-color: #909090;">
				<td colspan="2"></td>
				</tr>
				<tr>
					<td colspan="2">
						<div style="padding:10px;border-bottom: 1px solid #AAABB0;">
							<table style="width:100%;">
								<col width="100"/>
								<col width="80"/>
								<col />
								<tr>
									<td style="font-weight: bold; color: #464646">
										应收款项
									</td>
									<td id="saveMustMsg"></td>
									<td align="right">
										
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>
			<table class="content_table" >
				<tr>
					<td colspan="2">
						<div class="nav_bar">
							<div class="nav_scroll_btns">
								<div class="nav_left"  style="float: left;" onclick="scrollBtnOperation('right');" title="向左">
								 	&nbsp;
								</div>
								<div class="nav_right" style="float: left;" onclick="scrollBtnOperation('left');" title="向右">
								 	&nbsp;
								</div>
							</div>
							<div class="nav_hidden">&nbsp;</div>
							<div class="nav_hidden_right"><div class="nav_hidden_right_in">&nbsp;</div></div>
							<div class="nav_btns_container">
								<security:authorize ifAnyGranted="A_MUST_QUERY">
								<ul class="cost-nav" id="cost-nav" style="float:left; width: 100%;">
									<s:iterator value="mapChargeType">
									<li id="div_${key}" <s:if test='%{key==seleChargeType}'>class="cost-nav-click"</s:if> onclick="changeChargeType('${key}','${statusCd}');">
										${value}
									</li>
									</s:iterator>
								</ul>
								</security:authorize>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" id="bisMustPage">
					<c:if test="bisContId!=null">
					<table width='100%'>
						<tr>
							<td align='center'>
								<img src='${ctx}/images/loading.gif'/>
							</td>
						</tr>
					</table>
					</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="padding-top: 20px;">
					<div style="height: 50px;"></div>
					<div class="bottom_bar">
					
					</div>
					</td>
				</tr>
			</table>
			</div>
			<div style="height: 10px;"></div>
		</td>
	</tr>
</table>
</div>

</s:form>

<security:authorize ifAnyGranted="A_MUST_QUERY">
<script type="text/javascript">
	loadBisMustList('${seleChargeType}','${statusCd}');
</script>
</security:authorize>

<script type="text/javascript">
$(function(){
	setNavBarStyle();
	//bindSearchEv();
	//bindMoneyEv();
	$("#resultDiv").css("height",$(window).height()-27-3+"px");
});
</script>

<script type="text/javascript">
	$("#processForm :input").each(function(i,n){});
	$("#processForm :input").filter(".inputBorder").filter("[edit!=true]").attr("readonly","readonly");
	$("#processForm :input").filter(".inputBorder").filter("[edit!=true]").addClass("inputBorder_readOnly");
	$("select").filter(".inputBorder").attr("disabled","disabled");
	$("#processForm .Wdate").filter("[edit!=true]").not(".edit-date").attr("onfocus","");
	$("#processForm .Wdate").filter("[edit!=true]").not(".edit-date").removeClass("Wdate");
	$("#bisStoreNos").attr("onclick","");
	$("#bisShopName").unbind();
	$(".search").unbind();
	$(".money").unbind();
</script>

</body>
</html>
