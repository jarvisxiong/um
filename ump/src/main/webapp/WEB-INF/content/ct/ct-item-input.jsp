<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

						<form id="ctItemForm" action="${ctx}/ct/ct-item!addCostItem.action" method="post">	
						<br/>		
									<table class="" cellpadding="0" cellspacing="0" style="width: 100%;"> 		
										<tr>
											<th style="text-align:right;width:100px;"><span class="txtRed">*</span>序号:</th>
												<td style='text-align:left'>
											  <input style="width:123px;color:gray;height:20px;" type="text"   id="sequenceNoText"  readonly="readonly"/>											  
											 
											    <input style="width:123px;" type="hidden"  name="sequenceNo" id="sequenceNo"  readonly="readonly"/>											  
											    <input type="hidden" id="parentId" value="" name="parentId"/>
											    <input type="hidden" id="itemLevel"  value="" name="itemLevel"/>
											    <input type="hidden" id="ctDimeCd" value="" name="ctDimeCd"/>
											    <input type="hidden" id="ctLedgerId" value="ctLedgerId" name="ctLedgerId"/>
											    <input type="hidden" id="ctLandId" value="" name="ctLandId"/>
											    <input type="hidden" id="ctOperationId" value="" name="ctOperationId"/>
										        <input type="hidden" id="ctRecordVersion"  name="ctRecordVersion"/>
</td>
										
										</tr>
											
										
										<tr>
											<th style="text-align:right"><span class="txtRed">*</span>成本科目:</th>
											<td style='text-align:left'>
												<input type="text"  style='height:20px;' name="itemName"  id="itemName" class="selectInput"/>
											</td> 
											
										</tr>
										
								</table>
						
							
						
						</form>

