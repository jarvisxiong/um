<%@page import="com.hhz.ump.util.CodeNameUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/taglibs.jsp" %>
    <%@ include file="/common/nocache.jsp" %>
    <%@ include file="/common/global.jsp" %>

    <%@page import="com.hhz.ump.util.JspUtil" %>
    <%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>

    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/cost-ctrl.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisCont.css"/>
    <script type="text/javascript" src="${ctx}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
    <script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
    <script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
    <script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/customInput/customInput.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
    <script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
    <script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bisCont.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
    <title>商业合同详细信息</title>

</head>
<body>
<s:form id="processForm" action="bis-cont!save.action" method="post">
<input type="hidden" id="bisContId" name="bisContId" value="${bisContId}"/>
<input type="hidden" id="statusCd" name="statusCd" value="${statusCd}"/>
<input type="hidden" name="activeBl" value="${activeBl}"/>
<input type="hidden" name="resApproveInfoId" value="${resApproveInfoId}"/>
<input type="hidden" name="entityTmpId" value="${entityTmpId}"/>
<input type="hidden" id="recordVersion" name="recordVersion" value="${recordVersion}"/>
<input type="hidden" id="attachFlg" name="attachFlg" value="${attachFlg}"/>
<input type="hidden" id="approvalAttachFlg" name="approvalAttachFlg" value="${approvalAttachFlg}"/>
<input type="hidden" id="contApprovalId" name="contApprovalId" value="${contApprovalId}"/>
<input type="hidden" id="contBigTypeCd" name="contBigTypeCd" value="${contBigTypeCd}"/>
<input type="hidden" id="contSmallTypeCd" name="contSmallTypeCd" value="${contSmallTypeCd}"/>
<input type="hidden" id="bisTenantId" name="bisTenantId" value="${bisTenantId}"/>
<input type="hidden" name="hiddenShopId" value="${bisShopId}"/>
<input type="hidden" name="hiddenStoreIds" value="${bisStoreIds}"/>
<input type="hidden" name="hiddenFlatIds" value="${bisFlatIds}"/>
<%-- 
<input type="hidden" id="operator" value="${operator}"/>
<input type="hidden" id="contApprover" value="${contApprover}"/>
<input type="hidden" id="mustApprover" value="${mustApprover}"/>
<input type="hidden" id="mustDelete" value="${mustDelete}"/>
<input type="hidden" id="chargerTypeArr" name="chargerTypeArr" value="${chargerTypeArr}"/>
--%>
<s:hidden id="isModified" value="false"/>
<security:authorize ifAnyGranted="A_CONT_INSERT">
    <s:set var="contOperator">true</s:set>
</security:authorize>
<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%>
</s:set>
<s:set var="canEdit">
    <s:if test="changeBl">true</s:if>
    <s:else>
        <s:if test="!#contOperator || statusCd==2 || statusCd==3 || updateBl || (bisContId!=null && !activeBl)">
            false
        </s:if>
        <s:else>
            true
        </s:else>
    </s:else>
</s:set>
<div class="title_bar">
    <div style="font-weight:bold;padding-left:8px;font-size:14px;float:left;"><p:code2name
            mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContBigType()"
            value="contBigTypeCd"/>类<p:code2name
            mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContSmallType()" value="contSmallTypeCd"/>详细信息
    </div>
    <div style="padding-left:10px;font-size:14px;float:left;">
        <span style="color: #333333">合同状态：</span>
		<span style="color: red; ">
			<s:if test="bisContId==null">新增中</s:if>
			<s:else>
                <s:if test="activeBl==true">
                    <p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisContStatus()"
                                 value="statusCd"/>
                    <s:if test="updateBl">(变更中)</s:if>
                </s:if>
                <s:else>已失效 (失效日期:<s:date name="contToFailDate" format="yyyy-MM-dd"/>)</s:else>
            </s:else>
		</span>
    </div>
    <div style="float:right;font-size:12px;padding-right:10px;line-height:30px;">

        <s:if test="(bisContId!=null && !activeBl)">
            <security:authorize ifAnyGranted="A_CONT_INSERT">
                <input type="button" value="变更" class="btn_blue" onclick="doChangeCont();" id="btn_change"/>
            </security:authorize>
            <security:authorize ifAnyGranted="A_CONT_DELETE">
                <input type="button" value="恢复失效" class="btn_blue" onclick="cancelInvalid();"/>
                <input type="button" value="删除" class="btn_red" onclick="doDelete();"/>
            </security:authorize>
        </s:if>
        <s:else>
            <s:if test="updateBl">
                <input type="button" value="变更中合同" class="btn_blue" style="width: 85px;" onclick="doShowHis();"/>
            </s:if>
            <s:else>
                <s:if test="changeBl">
                    <input type="button" value="提交" class="btn_blue" onclick="doSubmit();" id="btn_submit"/>
                    <s:if test="bisContAdditionals==null || bisContAdditionals.size()==0">
                        <input id="btnBisContAddi" type="button" value="添加补充合同" class="btn_green" style="width: 95px;"
                               onclick="addContAdditional();"/>
                    </s:if>
                    <s:if test="bisContCredits==null || bisContCredits.size()==0">
                        <input id="btnBisContCredit" type="button" value="添加资信资料" class="btn_green" style="width: 95px;"
                               onclick="addContCredit();"/>
                    </s:if>
                </s:if>
                <s:else>
                    <security:authorize ifAnyGranted="A_CONT_INSERT">
                        <s:if test="statusCd==3 && resApproveInfoId == null">
                            <input type="button" value="变更" class="btn_blue" onclick="doChangeCont();" id="btn_change"/>
                        </s:if>
                        <s:if test="bisContId==null || statusCd==1 || statusCd==4">
                            <input type="button" value="提交" class="btn_blue" onclick="doSubmit();"/>
                            <input type="button" value="保存" class="btn_green" onclick="doSave();"/>
                            <s:if test="bisContId!=null && (bisContAdditionals==null || bisContAdditionals.size()==0)">
                                <input id="btnBisContAddi" type="button" value="添加补充合同" class="btn_green"
                                       style="width: 95px;" onclick="addContAdditional();"/>
                            </s:if>
                            <s:if test="bisContId!=null && (bisContCredits==null || bisContCredits.size()==0)">
                                <input id="btnBisContCredit" type="button" value="添加资信资料" class="btn_green"
                                       style="width: 95px;" onclick="addContCredit();"/>
                            </s:if>
                        </s:if>
                    </security:authorize>
                    <security:authorize ifAnyGranted="A_CONT_CHECK">
                        <s:if test="statusCd==2">
                            <input type="button" value="审核" class="btn_blue" onclick="doApproveCont();"/>
                        </s:if>
                    </security:authorize>
                    <security:authorize ifAnyGranted="A_CONT_REJECT">
                        <s:if test="(statusCd==2 || statusCd==3) && resApproveInfoId == null">
                            <input type="button" value="驳回" class="btn_red" onclick="doBackCont();"/>
                        </s:if>
                    </security:authorize>
                    <security:authorize ifAnyGranted="A_CONT_FAIL">
                        <s:if test="statusCd==4 || statusCd==3">
                            <input type="button" value="失效" class="btn_red" onclick="doInvalid();"/>
                        </s:if>
                    </security:authorize>
                    <s:if test="#curUserCd == 'baolm'">
                        <input type="button" value="删除" class="btn_red" onclick="doDelete();"/>
                    </s:if>
                </s:else>
            </s:else>
        </s:else>
        <s:if test="statusCd==4 || statusCd==3">
            <input type="button" value="历史版本" class="btn_green" style="width: 75px;" onclick="doShowHisVersion();"/>
        </s:if>
        <input type="button" value="返回" class="btn_green" onclick="goBack();" style="margin-top: 4px;"/>
            <%--
           <input type="button" value="历史版本" class="btn_green" style="width: 75px;" onclick="doShowHisVersion();" />
           <s:if test="updateBl">
           <input type="button" value="变更中合同" class="btn_blue" style="width: 85px;" onclick="doShowHis();" />
           </s:if>
           <s:if test="!updateBl">
           </s:if>
           <s:if test="statusCd==3 && operator && !updateBl">
               <s:if test="changeBl">
               <s:if test="bisContId!=null && (bisContAdditionals==null || bisContAdditionals.size()==0)">
               <input id="btnBisContAddi" type="button" value="添加补充合同" class="btn_green" style="width: 95px;" onclick="addContAdditional();" />
               </s:if>
               <s:if test="bisContId!=null && (bisContCredits==null || bisContCredits.size()==0)">
               <input id="btnBisContCredit" type="button" value="添加资信资料" class="btn_green" style="width: 95px;" onclick="addContCredit();" />
               </s:if>
               <input type="button" value="提交" class="btn_blue" onclick="doSubmit();" id="btn_submit" />
               </s:if>
               <s:else>
               <input type="button" value="变更" class="btn_blue" onclick="doChangeCont();" id="btn_change" />
               </s:else>
           </s:if>
           <s:if test="#canEdit=='true' && !changeBl">
               <s:if test="bisContId!=null && (bisContAdditionals==null || bisContAdditionals.size()==0)">
               <input id="btnBisContAddi" type="button" value="添加补充合同" class="btn_green" style="width: 95px;" onclick="addContAdditional();" />
               </s:if>
               <s:if test="bisContId!=null && (bisContCredits==null || bisContCredits.size()==0)">
               <input id="btnBisContCredit" type="button" value="添加资信资料" class="btn_green" style="width: 95px;" onclick="addContCredit();" />
               </s:if>
               <s:if test="statusCd==1">
               </s:if>
               <input type="button" value="保存" class="btn_green" onclick="doSave();" />
               <input type="button" value="提交" class="btn_blue" onclick="doSubmit();" />
           </s:if>
           <s:if test="#curUserCd == 'baolm'">
               <input type="button" value="删除" class="btn_red" onclick="doDelete();" />
               <input type="button" value="失效" class="btn_red" onclick="doInvalid();" />
           </s:if>
           <s:if test="contApprover">
               <s:if test="statusCd==2">
               <input type="button" value="审核" class="btn_blue" onclick="doApproveCont();" />
               <input type="button" value="驳回" class="btn_red" onclick="doBackCont();" />
               </s:if>
               <s:elseif test="statusCd==3 && !updateBl">
               <input type="button" value="驳回" class="btn_red" onclick="doBackCont();" />
               </s:elseif>
           </s:if>
           <input type="button" value="返回" class="btn_green" onclick="goBack();" style="margin-top: 4px;"/>
           --%>
    </div>
    <s:if test="resApproveInfoId != null">
        <div class="link_res" onclick="openResTask('${resApproveInfoId}');">来源于网批</div>
    </s:if>
</div>

<div id="resultDiv" style="overflow-x:hidden; width:100%;">
<table style="width:100%;" cellspacing="0" cellpadding="0">
<tr>
    <td style="padding-top: 20px; padding-left: 8px; padding-right: 8px;">
        <table class="tb_noborder" cellspacing="0" cellpadding="0" style="width:100%;">
            <col width="80"/>
            <col width="230"/>
            <col width="200"/>
            <col/>
            <tr style="height:30px; font-size: 12px; color: #464646">
                <td style="padding-left: 10px;">项目名称：</td>
                <td style="padding: 2px 0px;">
                    <input type="text" validate="required" class="inputBorder" id="bisProjectName" name="bisProjectName"
                           value="${bisProjectName}"
                           style="cursor: pointer;color: #ff0000;width:100%;border-left:2px solid red;"/>
                    <input type="hidden" id="bisProjectId" name="bisProjectId" value="${bisProjectId}"/>
                </td>
                    <%--
                         <td style="padding-left: 10px; padding-top: 2px; padding-bottom: 2px;">项目名称：</td>
                         <td>
                             <s:select validate="required" cssClass="inputBorder" cssStyle="width:100%;" list="mapBisProject" listKey="key" listValue="value" name="bisProjectId" id="bisProjectId" onchange="checkContName();"></s:select>
                         </td>
                         --%>
                <td colspan="2"></td>
            </tr>
            <tr style="height:30px; font-size: 12px; color: #464646">
                <td style="padding-left: 10px;">合同编号：</td>
                <td style="padding: 2px 0px;">
                    <input type="text" validate="required" class="inputBorder must" name="contNo" value="${contNo}"
                           style="width:100%;"/>
                </td>
                <td style="text-align: right;">合同类型：</td>
                <td align="left">
                    <p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContBigType()"
                                 value="contBigTypeCd"/>-<p:code2name
                        mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContSmallType()"
                        value="contSmallTypeCd"/>
                </td>
            </tr>
            <s:if test="creator!=null">
                <tr style="height:30px; font-size: 12px; color: #464646">
                    <td style="padding-left: 10px;">创建人：</td>
                    <td>
                        <%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %><input type="hidden"
                                                                                                name="creator"
                                                                                                value="${creator}"/>
                    </td>
                    <td style="text-align: right;">创建时间：</td>
                    <td align="left">
                        <s:date name="createdDate" format="yyyy-MM-dd"/><input type="hidden" name="createdDate"
                                                                               value="${createdDate}"/>
                    </td>
                </tr>
            </s:if>
            <s:if test="updator!=null">
                <tr style="height:30px; font-size: 12px; color: #464646">
                    <td style="padding-left: 10px;">更新人：</td>
                    <td>
                        <%=CodeNameUtil.getUserNameByCd(JspUtil.findString("updator")) %><input type="hidden"
                                                                                                name="updator"
                                                                                                value="${updator}"/>
                    </td>
                    <td style="text-align: right;">更新时间：</td>
                    <td>
                        <s:date name="updatedDate" format="yyyy-MM-dd"/><input type="hidden" name="updatedDate"
                                                                               value="${updatedDate}"/>
                    </td>
                </tr>
            </s:if>
            <s:if test="checkUserCd!=null">
                <tr style="height:30px; font-size: 12px; color: #464646">
                    <td style="padding-left: 10px;">审核人：</td>
                    <td>
                        <%=CodeNameUtil.getUserNameByCd(JspUtil.findString("checkUserCd")) %><input type="hidden"
                                                                                                    name="checkUserCd"
                                                                                                    value="${checkUserCd}"/>
                    </td>
                    <td style="text-align: right;">审核时间：</td>
                    <td>
                        <s:date name="checkDate" format="yyyy-MM-dd"/><input type="hidden" name="checkDate"
                                                                             value="${checkDate}"/>
                    </td>
                </tr>
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
        <table class="sup_table" cellspacing="0" cellpadding="0"
               style="background-color:#e4e7ec;overflow:hidden;width:100%;">
            <col/>
            <tr style="height:38px;background-color: #909090;ont-size:14px;">
                <td style="padding-left: 10px; font-weight: bold; font-size: 12px;"><span
                        style="color: #fff">合同明细信息</span></td>
            </tr>
        </table>
    </td>
</tr>

<tr>
    <td style="padding-left: 8px; padding-right: 8px;">
        <div id="detailContent">
            <s:if test="contBigTypeCd==1 && contSmallTypeCd==1">
                <jsp:include page="bis-cont-storeRent.jsp">
                    <jsp:param value="1" name="contBigTypeCd"/>
                    <jsp:param value="${canEdit}" name="canEdit"/>
                </jsp:include>
            </s:if>
            <s:elseif test="contBigTypeCd==1 && contSmallTypeCd==2">
                <jsp:include page="bis-cont-storeBack.jsp">
                    <jsp:param value="1" name="contBigTypeCd"/>
                    <jsp:param value="${canEdit}" name="canEdit"/>
                </jsp:include>
            </s:elseif>
            <s:elseif test="contBigTypeCd==1 && contSmallTypeCd==3">
                <jsp:include page="bis-cont-storeProp.jsp">
                    <jsp:param value="1" name="contBigTypeCd"/>
                    <jsp:param value="${canEdit}" name="canEdit"/>
                </jsp:include>
            </s:elseif>
            <s:elseif test="contBigTypeCd==1 && contSmallTypeCd==4">
                <jsp:include page="bis-cont-storeEntr.jsp">
                    <jsp:param value="1" name="contBigTypeCd"/>
                    <jsp:param value="${canEdit}" name="canEdit"/>
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
                    <jsp:param value="${canEdit}" name="canEdit"/>
                </jsp:include>
            </s:elseif>
            <s:else>
                <jsp:include page="bis-cont-multi.jsp">
                    <jsp:param value="3" name="contBigTypeCd"/>
                    <jsp:param value="${canEdit}" name="canEdit"/>
                </jsp:include>
            </s:else>
        </div>
    </td>
</tr>

<tr id="trBisContCredit"
    <s:if test="bisContId!=null && (bisContCredits==null || bisContCredits.size()==0)">style="display: none;"</s:if>>
    <td style="padding-top: 20px; padding-left: 8px; padding-right: 8px;">
        <div style="border: 1px solid #909090;">
            <table class="sup_table" cellspacing="0" cellpadding="0"
                   style="background-color:#e4e7ec;overflow:hidden;width:100%;">
                <col/>
                <col width="80px;"/>
                <tr style="height:10px;background-color: #909090;">
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div style="padding:10px;border-bottom: 1px solid #AAABB0;">
                            <table style="width:100%;">
                                <col width="100"/>
                                <col/>
                                <tr>
                                    <td style="font-weight: bold; color: #464646">
                                        资信资料
                                    </td>
                                    <td align="right">
                                        <s:if test="#canEdit=='true'">
                                            <input type="button" value="新增" class="btn_blue"
                                                   onclick="addCreditItem();"/>
                                        </s:if>
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
        <div style="border: 1px solid #909090;">
            <table class="sup_table" cellspacing="0" cellpadding="0"
                   style="background-color:#e4e7ec;overflow:hidden;width:100%;">
                <tr style="height:10px;background-color: #909090;">
                    <td colspan="2"></td>
                </tr>
            </table>
            <table class="mainTable" cellspacing="0" cellpadding="0" style="overflow:hidden;width:100%;border:0;">
                <col width="160"/>
                <col/>
                <col width="120"/>
                <tr style="height: 80px;">
                    <td style="padding-left: 10px;font-weight: bold; ">
                        合同内部批文
                    </td>
                    <td style="padding-top: 2px;padding-bottom: 2px;"><textarea id="contApproval"
                                                                                class="inputBorder contentTextArea"
                                                                                style="width:100%;height:80px;"
                                                                                name="contApproval"
                                                                                maxlength="4000">${contApproval}</textarea>
                    </td>
                    <td align="center">
                        <s:if test="#canEdit!='true'">
                            <a href="javascript:showAttachment('${contApprovalId}');">
                                <img id="contApprovalAttachId"
                                     <s:if test="approvalAttachFlg==1">src="${ctx}/resources/images/common/atta_y.gif"
                                </s:if>
                                     <s:else>src="${ctx}/resources/images/common/atta.gif"</s:else> />
                            </a>
                        </s:if>
                        <s:else>
                            <a id="contApprovalAttachIdA"
                               href="javascript:openAttachment('附件管理','${bisContId==null?entityTmpId:bisContId}','contApprovalAttachId','approvalAttachFlg');">
                                <img id="contApprovalAttachId"
                                     <s:if test="approvalAttachFlg==1">src="${ctx}/resources/images/common/atta_y.gif"
                                </s:if>
                                     <s:else>src="${ctx}/resources/images/common/atta.gif"</s:else> />
                            </a>
                        </s:else>
                        <br/>
                        <br/>
                        <font color="blue">请上传此附件</font>
                    </td>
                </tr>
            </table>
        </div>
    </td>
</tr>
<tr>
    <td style="padding-top: 20px; padding-left: 8px; padding-right: 8px;">
        <div style="border: 1px solid #909090;">
            <table class="sup_table" cellspacing="0" cellpadding="0"
                   style="background-color:#e4e7ec;overflow:hidden;width:100%;">
                <tr style="height:10px;background-color: #909090;">
                    <td colspan="2"></td>
                </tr>
            </table>
            <table class="mainTable" cellspacing="0" cellpadding="0" style="overflow:hidden;width:100%;border:0;">
                <col width="160"/>
                <col/>
                <col width="120"/>
                <tr style="height: 150px;">
                    <td style="padding-left: 10px;font-weight: bold; ">
                        关键合同条款<br/><br/>
                        <font style="color:red; ">具体填写内容</font>
                        <button type="button" class="btn_blue" onclick="showContentReference();">请参考</button>
                        &nbsp;&nbsp;
                    </td>
                    <td style="padding-top: 2px;padding-bottom: 2px;">
                    
                    <!-- <s:if test="contSmallTypeCd!=6">validate="required"</s:if>  --> 
                    <textarea id="contContent" validate="required"
                                                                               
                                                                                class="inputBorder contentTextArea"
                                                                                style="width:100%;height:150px;"
                                                                                name="contContent"
                                                                                maxlength="4000">${contContent}</textarea>
                    </td>
                    <td id="contContentId" align="center"
                        <s:if test="attachFlg==1">value="1"</s:if> >
                        <s:if test="#canEdit!='true'">
                            <a href="javascript:showAttachment('${bisContId}');">
                                <img id="bisContAttachId"
                                     <s:if test="attachFlg==1">src="${ctx}/resources/images/common/atta_y.gif" </s:if>
                                     <s:else>src="${ctx}/resources/images/common/atta.gif"</s:else> />
                            </a>
                        </s:if>
                        <s:else>
                            <a id="bisContAttachIdA"
                               href="javascript:openAttachment('附件管理','${bisContId==null?entityTmpId:bisContId}','bisContAttachId','attachFlg');">
                                <img id="bisContAttachId"
                                     <s:if test="attachFlg==1">src="${ctx}/resources/images/common/atta_y.gif" </s:if>
                                     <s:else>src="${ctx}/resources/images/common/atta.gif"</s:else> />
                            </a>
                        </s:else>
                        <br/>
                        <br/>
                        <font color="blue">请上传此附件</font>
                    </td>
                </tr>
            </table>
        </div>
    </td>
</tr>
<tr id="trBisContAddi"
    <s:if test="bisContId!=null && (bisContAdditionals==null || bisContAdditionals.size()==0)">style="display: none;"</s:if>>
    <td style="padding-top: 20px; padding-left: 8px; padding-right: 8px;">
        <div style="border: 1px solid #909090;">
            <table class="sup_table" cellspacing="0" cellpadding="0"
                   style="background-color:#e4e7ec;overflow:hidden;width:100%;">
                <col/>
                <col width="80px;"/>
                <tr style="height:10px;background-color: #909090;">
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div style="padding:10px;border-bottom: 1px solid #AAABB0;">
                            <table style="width:100%;">
                                <col width="100"/>
                                <col/>
                                <tr>
                                    <td style="font-weight: bold; color: #464646">
                                        补充合同
                                    </td>
                                    <td align="right">
                                        <s:if test="#canEdit=='true'">
                                            <input type="button" value="新增" class="btn_blue" onclick="addItemAdd();"/>
                                        </s:if>
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
                            <jsp:param value="${canEdit}" name="canEdit"/>
                        </jsp:include>
                    </td>
                </tr>
            </table>
        </div>
    </td>
</tr>

<s:if test="(statusCd==3 && !changeBl) || (bisContId!=null && !activeBl)">
    <tr>
        <td style="padding-top: 20px; padding-left: 8px; padding-right: 8px; ">
            <div style="border: 1px solid #909090;">
                <table class="sup_table" cellspacing="0" cellpadding="0"
                       style="background-color:#e4e7ec;overflow:hidden;width:100%; ">
                    <col/>
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
                                    <col/>
                                    <tr>
                                        <td style="font-weight: bold; color: #464646">
                                            应收款项
                                        </td>
                                        <td id="saveMustMsg"></td>
                                        <td align="right">
                                            <s:if test="tenantActiveBl">
                                                <security:authorize ifAnyGranted="A_MUST_CHECK">
                                                    <input type="button" value="审核" class="btn_blue"
                                                           onclick="doApproveMust();"/>
                                                </security:authorize>
                                                <security:authorize ifAnyGranted="A_MUST_REJECT">
                                                    <input type="button" value="驳回" class="btn_red"
                                                           onclick="doBackMust();"/>
                                                </security:authorize>
                                                <security:authorize ifAnyGranted="A_MUST_INSERT">
                                                    <s:if test="contBigTypeCd==1 && contSmallTypeCd != 2">
                                                        <input type="button" value="添加费用类别" class="btn_green"
                                                               style="width: 105px;" onclick="doAddChargeType();"/>
                                                    </s:if>
                                                    <input type="button" value="保存" class="btn_green"
                                                           onclick="doSaveMust();"/>
                                                    <input type="button" value="自动生成器" class="btn_green"
                                                           style="width: 95px;" onclick="autoAddMust();"/>
                                                    <input type="button" value="新增" class="btn_blue"
                                                           onclick="addItem();"/>
                                                </security:authorize>
                                            </s:if>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
                <table class="content_table">
                    <tr>
                        <td colspan="2">
                            <div class="nav_bar" style="height:65px;position:static; overflow: hidden;">
                                    <%--<div class="nav_hidden">&nbsp;</div>--%>
                                    <%--<div class="nav_hidden_right"><div class="nav_hidden_right_in">&nbsp;</div></div>--%>

                                <div class="nav_btns_container" style="position:static;padding-top: 2px;">
                                    <security:authorize ifAnyGranted="A_MUST_QUERY">
                                        <ul class="cost-nav" id="cost-nav" style="float:left; width: 100%;">
                                            <s:iterator value="mapChargeType"><!-- onclick="changeChargeType('${key}','${statusCd}');" -->
                                            <li id="div_${key}" value="${key}" style="width: 100px;"
                                                <s:if test='%{key==seleChargeType}'>class="cost-nav-click"</s:if>
                                                onclick="changeChargeType('${key}','${statusCd}');">
                                                    ${value}
                                            </li>
                                            </s:iterator>
                                        </ul>
                                    </security:authorize>
                                </div>

                                <div style="clear:both;"></div>
                                <div class="nav_scroll_btns"
                                     style="float:left;position: static;margin-top:-40px;width:80px; float:right;background-color: transparent;">
                                    <div style="float: right;width:80px;background:#fff;padding-left:10px;height:40px;">
                                        <div class="nav_left" style="float: left;"
                                             onclick="scrollBtnOperation('right');"
                                             title="向左">
                                            &nbsp;
                                        </div>
                                        <div class="nav_right" style="float: left;"
                                             onclick="scrollBtnOperation('left');"
                                             title="向右">
                                            &nbsp;
                                        </div>
                                    </div>
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
                                <div style="width:100%;height:26px;">
                                    <div id="operate_all_div">
                                        <div class="btn_bottom_chk_all">
                                            <div style="padding-top:5px;"><input class="chk_all" type="checkbox"
                                                                                 onclick="checkedAll($(this).attr('checked'));"
                                                                                 style="cursor:pointer;" title="全选/不选"/>
                                            </div>
                                        </div>
                                        <div class="btn_cutline_3_26"></div>
                                        <s:if test="tenantActiveBl">
                                            <security:authorize ifAnyGranted="A_MUST_CHECK">
                                                <div class="btn_bottom_bar" onclick="doApproveMust();">批量审核</div>
                                                <div class="btn_cutline_3_26"></div>
                                            </security:authorize>
                                            <security:authorize ifAnyGranted="A_MUST_REJECT">
                                                <div class="btn_bottom_bar" onclick="doBackMust();">批量驳回</div>
                                                <div class="btn_cutline_3_26"></div>
                                            </security:authorize>
                                            <security:authorize ifAnyGranted="A_MUST_DELETE">
                                                <div class="btn_bottom_bar" onclick="doBatchDel();">批量删除</div>
                                                <div class="btn_cutline_3_26"></div>
                                            </security:authorize>
                                        </s:if>
                                    </div>
                                    <div id="td_page"
                                         style="float:right; text-align: center; height:26px; margin-right:10px;"></div>
                                </div>
                                <div style="width:100%;height:1px;background-color:#dcdcde;"></div>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
            <div style="height: 10px;"></div>
        </td>
    </tr>
</s:if>
</table>
<s:else>
    <div style="height: 10px;"></div>
</s:else>
</div>

<div id="div_bottum" style="height: 10px; display: none"></div>

</s:form>

<security:authorize ifAnyGranted="A_MUST_QUERY">
    <script type="text/javascript">
        loadBisMustList('${seleChargeType}', '${statusCd}');
    </script>
</security:authorize>

<script type="text/javascript">
    //设置费用类别滚动按钮是否显示
    setNavBarStyle();

    //bindSearchEv();
    //bindMoneyEv();

    //loadProject();
    //bindProjectEv();
    //bindChargeEv();
    //unbindChargeEv();

    $(".inputBorder").filter("input").not(".noswap").each(function (i) {
        $(this).wrap("<span class='pd-chill-tip' title=" + $(this).val() + "></span>");
    });
</script>

<s:if test="#canEdit!='true'">
    <script type="text/javascript">
        try {
            $("#processForm :input").each(function (i, n) {
            });
            $("#processForm :input").filter(".inputBorder").attr("readonly", "readonly");
            $("#processForm :input").filter(".inputBorder").addClass("inputBorder_readOnly");
            $("select").filter(".inputBorder").attr("disabled", "disabled");
            $("#processForm .Wdate").filter("[edit!=true]").not(".edit-date").attr("onfocus", "");
            $("#processForm .Wdate").filter("[edit!=true]").not(".edit-date").removeClass("Wdate");
            $("#bisStoreNos").attr("onclick", "");
            $("#bisFlatNos").attr("onclick", "");
            $("#bisMultiNos").attr("onclick", "");
            $("#bisShopName").unbind();
            $(".search").unbind();
            $(".money").unbind().each(function () {
                formatVal($(this));
            });
            //$("input[type='checkbox']").click(function(){
            //	return false;
            //});
        } catch (e) {

        }
    </script>
</s:if>
<s:else>
    <script type="text/javascript">
        bindSearchEv();
        bindMoneyEv();
        $("#processForm *").filter("[validate*=required]").addClass("required");
    </script>
    <s:if test="!projAdmin">
        <script type="text/javascript">
            $('#bisProjectName').onSelect({muti:false});
        </script>
    </s:if>
</s:else>

<s:if test="changeBl">
    <script type="text/javascript">
        $("#bisShopName").attr("readonly", "readonly");
        //	$("#bisStoreNos").attr("readonly","readonly");
        $("#bisShopName").addClass("inputBorder_readOnly");
        //	$("#bisStoreNos").addClass("inputBorder_readOnly");
        //	$("#bisStoreNos").attr("onclick","");
        $("#bisShopName").unbind();
        $("#bisProjectName").attr("readonly", "readonly");
        $("#bisProjectName").addClass("inputBorder_readOnly");
        $("#bisProjectName").unbind();
    </script>
</s:if>
<script type="text/javascript">
$(function(){
	autoHeight();
});
</script>
</body>
</html>
