<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
			<td <s:if test="centerCd=='11'">colspan="12"</s:if> colspan="10">
				<form action="${ctx}/plan/plan-work!save.action" id="scheForm_${planWorkId}" method="post">
				<input type="hidden" name="id" value="${planWorkId}"/>
				<input type="hidden" name="planWorkId" value="${planWorkId}"/>
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
				<input type="hidden" name="isAttentioned" value="${isAttentioned}"/>
				<input type="hidden" name="newMessage"/>
				</form>
				<table id="table_${planWorkId}" width="100%">
					<tr><td colspan="3" style="width:100%; height:1px; background-color:#8c8f94;"></td></tr>
					<tr><td colspan="3" style="width:100%; height:1px; background-color:#dcdcde;"></td></tr>
					<tr>
						<td align="left" nowrap="nowrap" style="width:70px; height:30px; background-color:#e4e7ec;">
							<div style="padding-left:4px;">开始时间:</div>
						</td>
						<td align="left" nowrap="nowrap" style="width:5px; height:30px; background-color:#e4e7ec; border-right:1px solid #dcdcde;"></td>
						<td align="left" nowrap="nowrap" style="width:1200px; padding-left:14px;">
							<s:date name="createdDate" format="MM-dd HH:mm"></s:date>
							&nbsp;&nbsp;创建人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";") %>&nbsp;
							<s:if test="(statusCd==3 || statusCd==4 || statusCd==5) && null!=endDate">
								完成时间:<s:date name="endDate" format="MM-dd"></s:date>&nbsp;
							</s:if>
							<div id="saveDelete_btn_${planWorkId}" style="display:none;">
							<security:authorize ifAnyGranted="A_PLAN_WORK_ADMIN,A_PLAN_WORK_MANAGER">
								<button type="button" style="margin-left:2px;" class="btn_green_55_20" onclick="savePlanWork('${planWorkId}');">保存</button>
								<button type="button" style="margin-left:2px;" class="btn_green_55_20" onclick="updateStatusCd('${planWorkId}',1);">退回</button>
								<button type="button" style="margin-left:2px;" class="btn_green_55_20" onclick="updateStatusCd('${planWorkId}',3);">完成</button>
								<button type="button" style="margin-left:2px;" class="btn_red_35_20" onclick="updateStatusCd('${planWorkId}',4);">删除</button>
								<button type="button" style="margin-left:2px;" class="btn_red_35_20"  onclick="updateStatusCd('${planWorkId}',5);">隐藏</button>
								<button type="button" style="margin-left:2px;" class="btn_green_55_20" onclick="remind('${planWorkId}');">提醒</button>
							</security:authorize>
							<security:authorize ifAnyGranted="A_PLAN_WORK_USER">
								<button type="button" style="margin-left:2px;" class="btn_green_55_20" onclick="savePlanWork('${planWorkId}');">保存</button>
								<button type="button" style="margin-left:2px;" class="btn_green_55_20" onclick="updateStatusCd('${planWorkId}',1);">退回</button>
								<button type="button" style="margin-left:2px;" class="btn_green_65_20" onclick="updateStatusCd('${planWorkId}',2);">申请删除</button>
								<button type="button" style="margin-left:2px;" class="btn_green_65_20" onclick="updateStatusCd('${planWorkId}',6);">预完成</button>
								<button type="button" style="margin-left:2px;" class="btn_green_55_20" onclick="remind('${planWorkId}');">提醒</button>
							</security:authorize>
							</div>
							<security:authorize ifAnyGranted="A_PLAN_WORK_ADMIN">
									<script type="text/javascript">document.getElementById("saveDelete_btn_${planWorkId}").style.display = "inline";</script>
							</security:authorize>
							<security:authorize ifAnyGranted="A_PLAN_WORK_MANAGER,A_PLAN_WORK_USER">
								<c:if test="${1==isEditOrg}">
									<script type="text/javascript">document.getElementById("saveDelete_btn_${planWorkId}").style.display = "inline";</script>
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
												<td colspan="2" id="${planWorkId}_messageDiv">
													<s:iterator value="planWorkMessages">
														<div class="detail_message_div">
															<pre><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>(<s:date name="createdDate" format="MM-dd HH:mm"/>):<s:property value="content" escape="true"/></pre>
														</div>
													</s:iterator>
												</td>
											</tr>
											<tr><td colspan="2" style="height:4px;"></td></tr>
											<tr>
												<td width="20%" style="padding-bottom:8px;">
													<textarea id="${planWorkId}_message" name="newMessage" rows="3" cols="36" style="height:53px;"></textarea>
												</td>
												<td width="80%" style="padding-bottom:8px;" align="left">
													<button type="button" style="margin-left:2px;" class="btn_blue_55_55" onclick="saveMessage('${planWorkId}');">发表</button>
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