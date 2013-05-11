<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<div id="conAttachOutputList" style="padding:2px 5px;margin:0px;text-align:left;">

							<s:if test="conAttachOutputList==null || conAttachOutputList.size == 0">
							   <div style="color: #6BAD42; font-weight: bold; text-align: center;">
									没有可以查看的附件
								</div>
							</s:if>
							<s:else>						
								<table cellpadding="0" cellspacing="2" class="" style="overflow-x:hidden;font-size:12px; text-decoration:none; " border="0">
									<s:iterator value="conAttachOutputList">
										  <tr>
			                              <td width="55%">
												<s:url id="downUrl" action="download" namespace="/sc">
													<s:param name="fileName">${attachName}</s:param>
													<s:param name="realFileName">${realFileName}</s:param>
													<s:param name="bizModuleCd">sctemplet</s:param>
													<s:param name="operator">inline;</s:param>
													<s:param name="attchid">${scContractInfoAttachId}</s:param>
												</s:url>
												<span  style="color:#5A5A5A; cursor:pointer;" title="点击下载附件" onclick="window.open('${downUrl}'); return false;" 
													>${realFileName}</span> &nbsp;&nbsp;
											</td>
											 <td width="35%">
									    		<s:property value="createdDate" />&nbsp;&nbsp;<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";") %>
									    		
										    </td>										   
											<s:if test="creator==curUser">
											 <td width="10%">
													<!--<img style="cursor: pointer;" title="删除附件"  src="${ctx}/pics/note/note_del.gif"  
														onclick="deleteAttachment('${scContractInfoAttachId}');" />
												--></td>
											</s:if>
										</tr>											
									</s:iterator>	
									<tr>
									<td style="padding:0;margin:0;width:auto;height:auto;" colspan="3">									
									</td>											
								</tr>
								</table>
							</s:else>
						</div>
						
						
						
						
						
						
						
				