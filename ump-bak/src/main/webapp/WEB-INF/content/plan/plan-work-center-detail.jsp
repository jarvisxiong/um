<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
			<td <s:if test="centerCd=='11'">colspan="12"</s:if><c:if test="${if_chengben=='true'}">colspan="11"</c:if><s:if test="'planWorkCenterBis'==centerCd">colspan="12"</s:if> colspan="10">
				<form action="${ctx}/plan/plan-work-center!save.action" id="scheForm_${planWorkCenterId}" method="post">
				<input type="hidden" name="id" value="${planWorkCenterId}"/>
				<input type="hidden" name="planWorkCenterId" value="${planWorkCenterId}"/>
				<input type="hidden" name="userDeptCd" value ="${userDeptCd}" />
				<input type="hidden" name="recordVersion" value ="${recordVersion}"/>
				<input type="hidden" name="serialOrder" value ="${serialOrder}"/>
				<input type="hidden" name="targetPointCd" value ="${targetPointCd}"/>
				<input type="hidden" name="area" value ="${area}"/>
				<input type="hidden" name="content" value ="${content}"/>
				<input type="hidden" name="levelCd" value ="${levelCd}"/>
				<input type="hidden" name="targetDate" value ="${targetDate}"/>
				<input type="hidden" name="page.pageNo" value="1"/>
				<input type="hidden" name="statusCd" value="${statusCd}"/>
				<input type="hidden" name="isEditOrg" value="${isEditOrg}"/>
				<input type="hidden" name="principal" value="${principal}"/>
				<input type="hidden" name="workType" value="${workType}"/>
				<input type="hidden" name="corUser" value="${corUser}"/>
				<input type="hidden" name="isAttentioned" value="${isAttentioned}"/>
				<input type="hidden" name="newMessage"/>
				</form>
				<table id="table_${planWorkCenterId}" width="100%">
					<tr><td colspan="3" style="width:100%; height:1px; background-color:#8c8f94;"></td></tr>
					<tr><td colspan="3" style="width:100%; height:1px; background-color:#dcdcde;"></td></tr>
					<tr>
						<td align="left" nowrap="nowrap" style="width:70px; height:30px; background-color:#e4e7ec;">
							<div style="padding-left:4px;">开始时间:</div>
						</td>
						<td align="left" nowrap="nowrap" style="width:5px; height:30px; background-color:#e4e7ec; border-right:1px solid #dcdcde;"></td>
						<td align="left" nowrap="nowrap" style="width:1200px; padding-left:14px;">
							<div style="float:left; margin-top:6px;">
								<s:date name="createdDate" format="MM-dd hh:mm"></s:date>
								&nbsp;&nbsp;创建人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";") %>&nbsp;
								<s:if test="(statusCd==3 || statusCd==4 || statusCd==5) && null!=endDate">
									完成时间:<s:date name="endDate" format="MM-dd"></s:date>&nbsp;
								</s:if>
							</div>
							<s:if test="statusCd!=2">
								<input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_blue" onclick="updateStatusCd('${planWorkCenterId}',2);" value="预完成"/>
							</s:if>
							<span id="saveDelete_btn_${planWorkCenterId}">
							<security:authorize ifAnyGranted="A_PLAN_CENTER_ADMIN,A_PLAN_CENTER_USER">
								<input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_blue" onclick="updateStatusCd('${planWorkCenterId}',1);" value="进行中"/>
								<input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_blue" onclick="updateStatusCd('${planWorkCenterId}',3);" value="完成"/>
								<input type="button" style="float:left; margin-left:2px; margin-top:1px; width:85px" class="btn_green" onclick="updateStatusCd('${planWorkCenterId}',10);" value="完成但曾过期"/>
								<input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_green" onclick="savePlanWorkCenter('${planWorkCenterId}');"value="保存"/>
								<input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_green" onclick="updateStatusCd('${planWorkCenterId}',7);" value="非本月"/>
								<input type="button" style="float:left; margin-left:2px; margin-top:1px; width:85px" class="btn_green" onclick="updateStatusCd('${planWorkCenterId}',9);" value="非考核性延期"/>
								<input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_red" onclick="updateStatusCd('${planWorkCenterId}',8);" value="延期"/>
								<input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_red" onclick="updateStatusCd('${planWorkCenterId}',4);" value="删除"/>
								<!-- <input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_green" onclick="remind('${planWorkCenterId}');" value="提醒"/> -->
								<!-- <input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_green"  onclick="updateStatusCd('${planWorkCenterId}',5);" value="隐藏"/> -->
								<input type="button" style="margin-left:2px;" class="btn_red" onclick="doCallback('${planWorkCenterId}');" value="驳回"/>
							</security:authorize>
							</span>
							<s:if test="statusCd==2">
								<input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_red" onclick="updateStatusCd('${planWorkCenterId}',1);" value="取消预完成" />
							</s:if>
							<security:authorize ifAnyGranted="A_PLAN_CENTER_PRE,A_PLAN_CENTER_USER">
								<c:if test="${1==isEditOrg}">
									<script type="text/javascript">document.getElementById("saveDelete_btn_${planWorkCenterId}").style.display = "";</script>
								</c:if>
								<c:if test="${1!=isEditOrg || '-1'==statusCd}">
									<script type="text/javascript">document.getElementById("saveDelete_btn_${planWorkCenterId}").style.display = "none";</script>
								</c:if>
							</security:authorize>
						</td>
					</tr>
					<tr><td colspan="3" style="width:100%; height:1px; background-color:#dcdcde;"></td></tr>
					<tr>
						<td colspan="2" style="background-color:#e4e7ec; border-right:1px solid #dcdcde;">
							<div style="padding-left:4px;">留言:</div>
						</td>
						<td>
							<table>
								<tr>
									<td align="left" style="padding-left: 14px; padding-bottom:4px; padding-top:4px;">
										<table>
											<tr>
												<td colspan="2" id="${planWorkCenterId}_messageDiv">
													<s:iterator value="planWorkCenterMessages">
														<div class="detail_message_div">
															<pre><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>(<s:date name="createdDate" format="MM-dd HH:mm"/>):<s:property value="content" escape="true"/></pre>
														</div>
													</s:iterator>
												</td>
											</tr>
											<tr><td colspan="2" style="height:4px;"></td></tr>
											<tr>
												<td width="20%" style="padding-bottom:8px;">
													<textarea id="${planWorkCenterId}_message" name="newMessage" rows="3" cols="36" style="height:53px;"></textarea>
												</td>
												<td width="80%" style="padding-bottom:8px;" align="left">
													<button type="button" style="margin-left:2px;" class="btn_blue_55_55" onclick="saveMessage('${planWorkCenterId}');">发表</button>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr><td colspan="3" style="width:100%; height:1px; background-color:#dcdcde;"></td></tr>
					<tr><td colspan="3" style="width:100%; height:1px; background-color:#8c8f94;"></td></tr>
				</table>
			</td>