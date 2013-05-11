<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<div id="recurentMsgInfo" style="clear:both;width:99%;align:center;text-align: left;">
<div style="float:left;width:23%; overflow:hidden">
	<div style="font-size:12px;color:#0693E3;font-weight:bold;text-align: left;">最近合同附件</div>
	<s:if test="conAttachOutputList==null || conAttachOutputList.size == 0">
							   <div style="color: #6BAD42; font-weight: bold; text-align: left;">
						无
								</div>
		</s:if>
		<s:else>
			<s:iterator value="conAttachOutputList">		
						<s:url id="downUrl" action="download" namespace="/sc">
							<s:param name="fileName">${attachName}</s:param>
							<s:param name="realFileName">${realFileName}</s:param>
							<s:param name="bizModuleCd">sctemplet</s:param>
							<s:param name="operator">inline;</s:param>
							<s:param name="attchid">${scContractInfoAttachId}</s:param>
						</s:url>
						<span <s:if test="aOnlyCreator && creator==myUiid || !aOnlyCreator"> style="text-decoration:underline; color:#5A5A5A; cursor:pointer;" title="点击下载附件" onclick="window.open('${downUrl}'); return false;"</s:if> 
							<s:else> title="您不是上传者，无法下载附件"</s:else>
							>${realFileName}								
							</span> &nbsp;&nbsp;			
				
						<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";")%>	
						&nbsp;	
						<%-- 上传日期--%>
					<span title="<s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss" />"><s:date name="createdDate" format="MM-dd" /></span>
			
			</s:iterator>	
		</s:else>
</div>
<div style="float:left;width:50%;">
<div style="font-size:12px;color:#0693E3;font-weight:bold;text-align: left;" >最新版本</div>
	<s:if test="(resApproveInfoList == null || resApproveInfoList.size == 0) &&
		 (scContractApproveHisList == null || scContractApproveHisList.size()== 0) &&
		 (scContractPrintList == null || scContractPrintList.size() == 0)">
							   <div style="color: #6BAD42; font-weight: bold; text-align: left;">
									无
								</div>
		</s:if>
		<s:else>
<s:if test="scContractPrintList!=null && scContractPrintList.size()>0">
<div style="background-color: #ffffee;padding:0px 5px;margin:2px;" id="printList">
	<table class="content_table" id="showTableApproveRec" width="100%" style="font-size:12px;"  cellpadding="0" cellspacing="0">
	
		<s:iterator value="scContractPrintList" var="ra">
			<tr class="mainTr" >
				<td style="padding:1px 0px;height:100%;" class="clswrap">
					<div style="float:left;">
						流水号：${contractPrintNo}
					</div>
					<div style="margin-right: 10px;float:left;"> 
						打印人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";")%>
					</div>
					<div style="float:left;margin-right: 10px;">
					打印时间：<s:date name="createdDate" format="yyyy-MM-dd HH:mm" />
					</div>
					
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
</s:if>
<s:elseif test="scContractApproveHisList!=null && scContractApproveHisList.size()>0">
<div style="background-color: #ffffee;padding:0px 5px;margin:2px;" id="operationList" class="clswrap">
	<table class="content_table" id="showTableApproveRec" width="100%" style="font-size:12px;">
	
		<s:iterator value="scContractApproveHisList" var="ra">
			<tr class="mainTr" >
				<td style="padding:1px 0px;height:100%; overflow:hidden" class="clswrap">
					<div style="float:left;">
						操作人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";")%>
					</div>
					<div style="margin-right: 10px;float:left;"> 
						操作内容：<s:if test="recordTypeCd == 40">审核通过</s:if>
						         <s:elseif test="recordTypeCd == 70">合同编写完成</s:elseif>
						         <s:elseif test="recordTypeCd == 80">合同签署完成</s:elseif>
					</div>
					<div style="float:left;margin-right: 10px;">
					操作时间：<s:date name="createdDate" format="yyyy-MM-dd HH:mm" />
					</div>
					
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
</s:elseif>
<s:elseif test="resApproveInfoList!=null && resApproveInfoList()>0">
<div style="background-color: #ffffee;padding:0px 5px;margin:2px;" id="flowList">
	<table class="content_table" id="showTableApproveRec" width="100%" style="font-size:12px;">
		<s:iterator value="resApproveInfoList" var="ra">
			<tr class="mainTr" onclick="openDetail('${resApproveInfoId}')">
				<td style="padding:1px 0px;height:100%;">
					<div style="float:left;">
						编号：${approveCd}${serialNo }
					</div>
					<div style="margin-right: 10px;float:left;"> 
						发起人：${userName}
					</div>
					<div style="float:left;margin-right: 10px;">
						发起时间：<s:date name="startDate" format="yyyy-MM-dd HH:mm" />
					</div>
					<div style="float:left;">
						标题：${titleName }
					</div>
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
</s:elseif>
</s:else>

</div>
<div style="float:left;width:20%;">
	<div  style="font-size:12px;color:#0693E3;font-weight:bold;text-align: left;">最近留言</div>
	<s:if test="messages==null || messages.size == 0">
		 <div style="color: #6BAD42; font-weight: bold; text-align: left;">无</div>
		</s:if><s:else>
								<s:iterator value="messages">
										<div class="divMessContent0"  style="overflow: hidden;white-space: nowrap;max-width: 100%;text-overflow:ellipsis;text-align:left;" title="${msgContent }" >
											<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>(<s:date name="createdDate" format="MM-dd HH:mm"/>):
											<s:property value="msgContent" escape="true"/>
										</div>
						      </s:iterator>
						      
						      </s:else>
</div>
<div style="float:left;width:7%;cursor:pointer;padding-top: 2px;" id="infoMore" title="查看更多信息">
	<input type="button" value="更多>>" onclick="showMsgDetail()" class="btn_green" style="width:60px;line-height: 22px;"></input>
</div>
</div>