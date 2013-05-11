<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%--选择的项目所对应的业态 --%>
<%-- 之前生成合同编号时选项目需要项目对应的业态，（项目+业态=作为生成合同编号前缀）,现在的版本中暂是不需要选择业态--%>
<div id="execPlanLayoutDiv" style="padding:2px 5px;margin:0px;text-align:left;">

							<s:if test="execPlanLayouts==null || execPlanLayouts.size() == 0">
							   <div style="color: #6BAD42; font-weight: bold; text-align: center;">
								该合同暂无业态！
								</div>
							</s:if>
							<s:else>
								<table class="content_table"  width="100%" style="font-size:12px;"  cellpadding="0" cellspacing="0">
									<s:iterator value="execPlanLayouts">																	
											<tr class="mainTr layout" onclick="selectedPlanLayOut(this,'${remark}')" >
											<td title="${executionPlanName}" >
											<div style="width:300px" class="clswrap" >${executionPlanName}</div>
											</td>
										</tr>
									</s:iterator>	
									
								</table>
							</s:else>
						</div>