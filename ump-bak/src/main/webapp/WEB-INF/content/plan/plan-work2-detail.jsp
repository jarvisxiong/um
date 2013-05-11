<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
			<td <s:if test="4==quarter_status"> colspan="11"</s:if><s:if test="5==quarter_status||6==quarter_status||7==quarter_status||8==quarter_status||9==quarter_status"> colspan="12"</s:if> colspan="10">
				<form action="${ctx}/plan/plan-work2!save.action" id="scheForm_${planWork2Id}" method="post">
				<input type="hidden" name="id" value="${planWork2Id}"/>
				<input type="hidden" name="planWork2Id" value="${planWork2Id}"/>
				<input type="hidden" name="planYear" value="${planYear}"/>
				<input type="hidden" name="planMonth" value="${planMonth}"/>
				<input type="hidden" name="planType" value="${planType}"/>
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
				<input type="hidden" name="weightPoint" value="${weightPoint}"/>
				<input type="hidden" name="selfPoint" value="${selfPoint}"/>
				<input type="hidden" name="selfCheckPoint" value="${selfCheckPoint}"/>
				<input type="hidden" name="evaluatePoint" value="${evaluatePoint}"/>
				<input type="hidden" name="finalPoint" value="${finalPoint}"/>
				<input type="hidden" name="isEditOrg" value="${isEditOrg}"/>
				<input type="hidden" name="isAttentioned" value="${isAttentioned}"/>
				<input type="hidden" name="suspendId" value="${suspendId}"/>
				<input type="hidden" name="ifSuspend" value="${ifSuspend}"/>
				<input type="hidden" name="ifSuspendCreated" value="${ifSuspendCreated}"/>
				<input type="hidden" name="if_in_weight"/>
				<input type="hidden" name="newMessage"/>
				</form>
				<table id="table_${planWork2Id}" width="100%">
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
								&nbsp;&nbsp;完成时间:<s:date name="endDate" format="MM-dd"></s:date>&nbsp;
							</s:if>
							<div> 
								<input type="button" class="save2Btn btn_blue" style="float:left; margin-left:2px; margin-top:1px; display:none;" onclick="updateStatusCd('${planWork2Id}',2);" value="预完成"/>
								<input type="button" class="save1Btn btn_blue" style="float:left; margin-left:2px; margin-top:1px; display:none;" onclick="updateStatusCd('${planWork2Id}',1);" value="进行中"/>
								<s:if test="if_bis_cannot">
									<s:if test="'shisn'!=myUiid">
										<s:if test="planType==1">
											<input type="button" class="save4Btn btn_blue" style="float:left; margin-left:2px; margin-top:1px; display:none;" onclick="updateStatusCd('${planWork2Id}',4);" value="完成"/>
											<input type="button" class="save10Btn btn_green" style="float:left; margin-left:2px; margin-top:1px; display:none; width:85px;" onclick="updateStatusCd('${planWork2Id}',10);" value="完成但曾过期"/>
										</s:if>
									</s:if>
								</s:if>
								<s:else>
									<s:if test="'shisn'==myUiid">
										<input type="button" class="save4Btn btn_blue" style="float:left; margin-left:2px; margin-top:1px; display:none;" onclick="updateStatusCd('${planWork2Id}',4);" value="完成"/>
										<input type="button" class="save10Btn btn_green" style="float:left; margin-left:2px; margin-top:1px; display:none; width:85px;" onclick="updateStatusCd('${planWork2Id}',10);" value="完成但曾过期"/>
									</s:if>
									<s:else>
										<input type="button" class="save4Btn btn_blue" style="float:left; margin-left:2px; margin-top:1px; display:none;" onclick="updateStatusCd('${planWork2Id}',4);" value="完成"/>
										<input type="button" class="save10Btn btn_green" style="float:left; margin-left:2px; margin-top:1px; display:none;width:85px;" onclick="updateStatusCd('${planWork2Id}',10);" value="完成但曾过期"/>
									</s:else>
								</s:else>
								<input type="button" class="saveNoneBtn btn_green" style="float:left; margin-left:2px; margin-top:1px; display:none;" onclick="savePlanWork('${planWork2Id}');" value="保存"/>
								<input type="button" class="save7Btn btn_green"  style="float:left; margin-left:2px; margin-top:1px; display:none;" onclick="updateStatusCd('${planWork2Id}',7);" value="非本月"/>
								<input type="button" class="save9Btn btn_green" style="float:left; margin-left:2px; margin-top:1px; display:none;width:85px;" onclick="updateStatusCd('${planWork2Id}',9);" value="非考核性过期"/>
								<input type="button" class="save8Btn btn_red" style="float:left; margin-left:2px; margin-top:1px; display:none;" onclick="updateStatusCd('${planWork2Id}',8);" value="过期"/>
								<input type="button" class="save3Btn btn_red" style="float:left; margin-left:2px; margin-top:1px; display:none;" onclick="updateStatusCd('${planWork2Id}',3);" value="申请删除"/>
								<input type="button" class="save5Btn btn_red" style="float:left; margin-left:2px; margin-top:1px; display:none;" onclick="updateStatusCd('${planWork2Id}',5);" value="删除"/>
								<span style="display:none;"><input type="button" class="save6Btn btn_green" style="float:left; margin-left:2px; margin-top:1px; display:none;" onclick="updateStatusCd('${planWork2Id}',6);" value="隐藏"/></span>
								<span style="display:none;"><input type="button" class="save0Btn btn_red" style="float:left; margin-left:2px; margin-top:1px; display:none;" onclick="updateStatusCd('${planWork2Id}',0);" value="未确认"/></span>
								<input type="button" class="saveCallbackBtn btn_red" style="float:left; margin-left:2px; margin-top:1px; display:none;" onclick="doCallback('${planWork2Id}');" value="驳回"/>
							</div>
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
										<table width="700px">
											<tr>
												<td colspan="2" id="${planWork2Id}_messageDiv">
													<s:iterator value="planWork2Messages">
														<div class="detail_message_div">
															<pre><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>(<s:date name="createdDate" format="MM-dd HH:mm"/>):<s:property value="content" escape="true"/></pre>
														</div>
													</s:iterator>
												</td>
											</tr>
											<tr><td colspan="2" style="height:4px;"></td></tr>
											<tr>
												<td width="20%" style="padding-bottom:8px;">
													<textarea id="${planWork2Id}_message" name="newMessage" rows="3" cols="36" style="height:53px;"></textarea>
												</td>
												<td width="80%" style="padding-bottom:8px;" align="left">
													<button type="button" style="margin-left:2px;" class="btn_blue_55_55" onclick="saveMessage('${planWork2Id}');">发表</button>
												</td>
											</tr>
											<tr><td colspan="2" style="height:10px;"><div class="color_blue" style="cursor:pointer" onClick="scheClick('${planWork2Id}');"><img src="${ctx}/resources/images/common/up_blue.gif"/>收起</div></td></tr>
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
			