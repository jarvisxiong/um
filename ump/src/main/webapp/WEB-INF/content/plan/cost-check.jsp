<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<!--标后核对管理页面 -->

<!-- 模板 -->
<div id="new_templet_check" style="padding:5px;display: none; ">
	<fieldset style="background-color:#f7f7f7;">
	<legend>[新增]标后核对</legend>
	
	<form action="${ctx}/plan/cost-check!save.action" method="post" id="new_templet_form_check">
		<table style="width:100%;line-height:30px;">
			<col width="60px"/>
			<col />
			<tr>
				<td>所属项目</td>
				<td>
					<input type="text" id="projectNameId" style="width:150px;cursor:pointer;" class="projSelect" onclick="initProjectSelectCheck(this)"/>
					<input type="hidden" id="projectCdId" name="projectCd"/>
				</td>
			</tr>
			<tr>
				<td>事项</td>
				<td><textarea name="planContentDesc" id="new_planContentDesc" style="width:80%;height:40px;" ></textarea></td>
			</tr>
			<tr>
				<td>审批时间</td>
				<td><input name="bidDate" id="new_bidDate" style="width:100px;" onfocus="WdatePicker()" class="Wdate"/></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align:left;">
				   <input type="button" class="btn_blue" onclick="saveNewCostCheck();" value="保存"/>
			       <input type="button" class="btn_red" onclick="cancelNewcostCheck();" value="取消"/>
				</td>
			</tr>
		</table>
	</form>
	</fieldset>
</div>

<!-- 标后核对列表 -->
<div >
<table class="content_table" id="result_table">
	<thead>
		<tr class="header">
			<th width="80" style="padding-left:10px;background: none;"></th>
			<th width="85" align="left">序号</th>
			<th align="left">事项</th>
			<th width="60" align="left">负责人</th>
			<th width="60" align="left">进度</th>
			<th width="60" align="left">状态</th>
			<th width="70" align="left">审批时间</th>
			<th width="80" align="left">等待</th>
			<th width="40" align="left" style="cursor:pointer;" title="按更新时间排序" onclick="quickSearchByUpdate();">
				更新<img src="${ctx}/images/plan/btn_down_10_10.gif">
			</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dataMap" id="column" status="status">   
			<tr id="group_<s:property value='#column.key'/>" class="group"> 
				<td colspan="9" class="group-title">
					<%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("#column.key")) %>(<s:property value='#column.value.size' />)
				</td>
			</tr>
			<s:iterator value="#column.value" status="s">
			<!-- 主信息 -->
			<tr class="mainTr" group="group_<s:property value='#column.key'/>" id="main_${costCheckId}" 
				<%--
				target = "true"
				dataEdit="true"
				permEdit="true"
				permConfirm="true"
				--%>
				
				<s:if test="targetId != ''">
					target = "true"
				</s:if>
				<s:else>
					target="false"
				</s:else>
				<s:if test="dataSrcTypeCd == 0">
					dataEdit="true"
				</s:if>
				<s:else>
					dataEdit="false"
				</s:else>
				<security:authorize ifAnyGranted="A_CCHECK_EDIT">
					<s:if test="scheduleStatusCd == 2">
						permEdit="false"
					</s:if>
					<s:else>
						permEdit="true"
					</s:else>
				</security:authorize>
				<security:authorize ifNotGranted="A_CCHECK_EDIT">
					<s:if test="currentUser == creator">
						permEdit="true"
					</s:if>
					<s:else>
						permEdit="false"
					</s:else>
				</security:authorize>
				<security:authorize ifAnyGranted="A_CCHECK_CONFIRM">
					<s:if test="scheduleStatusCd == 2">
						permConfirm="false"
					</s:if>
					<s:else>
						permConfirm="true"
					</s:else>
				</security:authorize>
				<security:authorize ifNotGranted="A_CCHECK_CONFIRM">
					permConfirm="false"
				</security:authorize>
				
				scheCd = "${scheduleStatusCd}"
			>	
				<td click2expand="true" style="padding-left:10px;">
					<%--
					<s:if test="dataSrcTypeCd == 0">新增</s:if>
					<s:if test="dataSrcTypeCd == 1">招采</s:if>
					 --%>
					 <%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("projectCd")) %>
				</td>
				<td click2expand="true" class="pd-chill-tip" title="${serialType}${serialNo}">${serialType}${serialNo}</td>
				<td click2expand="true">
					<div class="cost-read ellipsisDiv_full pd-chill-tip" dataEdit="false" title="${planContentDesc}" >${planContentDesc}</div>
					<div class="cost-edit" dataEdit="false" style="display:none;">
						<textarea style="width:98%" onchange="changeDescCheck(this,'${costCheckId}')">${planContentDesc}</textarea>
					</div>
				</td>
				<%-- 负责人--%>
				<td click2expand="true">
					<div class="cost-read" confirmEdit="true">${ownerNames}</div>
					<div class="cost-edit" confirmEdit="true" style="display:none;">
						<textarea id="${costCheckId}UserNames" style="width:55px;cursor: pointer;" onclick="changeOwnerCheck(this,'${costCheckId}')">${ownerNames}</textarea>
						<input type="hidden" id="${costCheckId}UserCds" value="${ownerCds}"/>
					</div>
				</td>
				<%-- 进度--%>
				<td click2expand="true" 
					<s:if test="bidStatusCd == 4">
						style="background-color:#FFC125"
					</s:if>
					>
					<s:if test="bidStatusCd != null && bidStatusCd != ''">
						<p:code2name mapCodeName="scheChkMap" value="bidStatusCd"/>
					</s:if>
				</td>
				<%-- 状态 --%>
				<td click2expand="true">
					<s:if test="scheduleStatusCd == 0">新增</s:if>
					<s:if test="scheduleStatusCd == 1">进行中</s:if>
					<s:if test="scheduleStatusCd == 2">已完成</s:if>
					<s:else>
					<%if(JspUtil.compareCurDate("bidDate")){ %>
						<img title="过期" src="${ctx}/resources/images/plan/pic_delay.gif">
					<%--
					<s:if test="contractStatusCd == 0">
					</s:if>
					--%>
				    <%} %>
				    </s:else>
				</td>
				<td click2expand="true" 
					<s:if test="scheduleStatusCd != 2">
						<%if(JspUtil.compareCurDate("bidDate")){ %>
							style="color:red" title="已过期" class="pd-chill-tip" 
						<%--
						<s:if test="contractStatusCd == 0">
						</s:if>
						--%>
						<%} %>
					</s:if>
				>
					<s:date name="bidDate" format="yy-MM-dd"/>
				</td>
				<td click2expand="true">
					<s:if test="isWaitProjectFlag == 1">
						<div class="pd-chill-tip" title="${waitProjectDesc }">项目</div>
					</s:if>
					<s:if test="isWaitTechFlag == 1">
						<div class="pd-chill-tip" title="${waitTechDesc }">技术研发中心</div>
					</s:if>
					<s:if test="isWaitOtherFlag == 1">
						<div class="pd-chill-tip" title="${waitDesc }">${waitDeptName}</div>
					</s:if>
				
				</td>
				<td click2expand="true" class="pd-chill-tip" 
					title='<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("updator")) %>(<s:date name="updatedDate" format="yyyy-MM-dd HH:mm"/>)'
				>
					<s:date name="updatedDate" format="MM-dd"/>
				</td>
			</tr>
			
			<!-- 详细信息，包含留言、进度信息 -->
			<tr class="detailTr" group="group_<s:property value='#column.key'/>" id="detail_${costCheckId}" valign="top" style="display: none;">
				<td colspan="9" style="border-bottom:1px solid #AAABB0;" >
					<form action="${ctx}/plan/cost-check!save.action" id="form_${costCheckId}" method="post">
						
						<input type="hidden" name="id" value="${costCheckId}"/>
						<input type="hidden" name="pageType" value="${pageType}"/>
						<input type="hidden" name="confirmFlg" id="confirmFlg_${costCheckId}"/>
						<input type="hidden" name="conStatusChangeFlg" id="conStatusChangeFlg_${costCheckId}" />
						<input type="hidden" name="bidStatusCd" id="bidStatusCd_${costCheckId}" value="${bidStatusCd}"/>
						<input type="hidden" name="bidDate" id="bidDate_${costCheckId}" value='<s:date name="bidDate" format="yyyy-MM-dd"/>'/>
						
						<table style="width:100%;border-top:1px solid #AAABB0;" >
							<tr>
								<td align="center" style="border-right:1px solid #DCDCDE;border-bottom:1px solid #DCDCDE;background-color: #E4E7EC;">
									开始时间
								</td>
								<td  style="border-bottom:1px solid #DCDCDE;">
									<div style="height:28px;padding:5px;">
								       <div style="float:left;padding-top:10px;">
										<span><s:date name="createdDate" format="yyyy-MM-dd hh:mm"/></span>
										<span>创建人:</span>
										<span><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %></span>
										</div>
										<!-- 核对权限 -->
										<s:if test="scheduleStatusCd == 0">
											<security:authorize ifAnyGranted="A_CCHECK_CONFIRM,A_CCHECK_EDIT">
											 <input type="button" class="btn_blue" onclick="confirmCostCheck('${costCheckId}')" title="确认负责人及完成时间"  value="确认" />
										</security:authorize>
										</s:if>
										<s:elseif test="scheduleStatusCd == 1">
											<security:authorize ifAnyGranted="A_CCHECK_CONFIRM,A_CCHECK_EDIT,A_CCHECK_SCHE">
											<input type="button" class="btn_blue" onclick="saveCostCheck('${costCheckId}')" value="保存" />
											</security:authorize>
										</s:elseif>
										<%--
										<s:if test="scheduleStatusCd == 1">
											<security:authorize ifAnyGranted="A_CCHECK_CONFIRM">
											<button class="btn_green_55_20" style="margin-right:5px;" type="button" onclick="costCheckComplete('${costCheckId}')">完成</button>
											</security:authorize>
										</s:if>
										 --%>
										
										<!-- 未完成的计划可以进行删除 -->
										<s:if test="scheduleStatusCd == 0 ||scheduleStatusCd == 1">
											<security:authorize ifAnyGranted="A_CCHECK_DELETE">
											<input type="button" class="btn_red" onclick="deleteDataCheck('${costCheckId}')" value="删除" />
											</security:authorize>
										</s:if>
										
										<s:if test="scheduleStatusCd == 2">
											<security:authorize ifAnyGranted="A_CCHECK_CONFIRM">
											<input type="button" class="btn_red" onclick="costCheckReturn('${costCheckId}')" value="退回" title="实际未完成,可点击退回!" />
											</security:authorize>
										</s:if>
									
									</div>
								</td>
							</tr>
							<tr>
								<td align="center" class="shortcutsArea" style="border-bottom:1px solid #DCDCDE;border-right:1px solid #DCDCDE;background-color: #E4E7EC;">
									留言
								</td>
								<td valign="top" style="border-bottom:1px solid #DCDCDE;">
									<%--左边 --%>
									<div style="line-height:30px;float:left;margin:10px;width:330px;" >
										<table style="width:100%;border:1px solid #DDDBDC;">
											<!--
											<col width="40">
											<col width="80">  
											-->	
											<col width="200">
											<col />
											<col />
											<tr>
												<td colspan="3" class="bg-title" style="padding-left:10px;">等待</td>
											</tr> 
											<tr>
												<td style="padding-left:10px;padding-top:5px;">
													<input type="checkbox" onclick="changeWaitValueCheck(this,'waitDesc_${costCheckId}&waitDeptName_${costCheckId}&waitDeptCd_${costCheckId}');" 
														<s:if test="isWaitOtherFlag == 1">checked=checked</s:if>
														<security:authorize ifNotGranted="A_CCHECK_SCHE,A_CCHECK_EDIT,A_CCHECK_CONFIRM">
															disabled=disabled
														</security:authorize>
													/>
													<input type="hidden" value="${isWaitOtherFlag}" name="isWaitOtherFlag" />
												</td>
												<td align="left" style="padding-top:5px;">
													<input type="text" id="waitDeptName_${costCheckId}" name="waitDeptName" value="${waitDeptName}" 
													readonly="readonly"
													<security:authorize ifAnyGranted="A_CCHECK_SCHE,A_CCHECK_EDIT,A_CCHECK_CONFIRM"> 
													onclick="showWaitDept(this,'${costCheckId}')"
													style="width:100px;cursor:pointer;" 
													</security:authorize>
													<security:authorize ifNotGranted="A_CCHECK_SCHE,A_CCHECK_EDIT,A_CCHECK_CONFIRM">
													style="width:100px;" 
													onclick="$(this).trigger('blur');"
													</security:authorize>
													/>
													<input type="hidden" id="waitDeptCd_${costCheckId}" name="waitDeptCd" value="${waitDeptCd}"/>
												</td>
												<td style="padding-top:5px;">
													<input type="text" id="waitDesc_${costCheckId}" name="waitDesc" value="${waitDesc}" 
													style="width:185px;" 
													<security:authorize ifNotGranted="A_CCHECK_SCHE,A_CCHECK_EDIT,A_CCHECK_CONFIRM">
													onclick="$(this).trigger('blur');"
													</security:authorize>
													readonly="readonly"/>
												</td>
											</tr>
											<tr style="height:5px;line-height:5px;">
												<td colspan="3">&nbsp;</td>
											</tr>
										</table>
										
										<!-- 留言区域 -->
										<div id="msg_container_${costCheckId}" style="line-height:20px;padding-top:15px;">
											<s:iterator	value="costCheckMessages">
												<div class="detail_message_div">
													<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>
													(<s:date name="createdDate" format="MM-dd HH:mm"/>):${msgContentDesc}</div>
											</s:iterator>
										</div>
										<div style="margin-top:5px;">
											<textarea style="height: 53px;float:left;" cols="36" rows="3" id="message_${costCheckId}"></textarea>
											 <input type="button" class="button_green" style="height:53px;" onclick="saveMsgCheck('${costCheckId}')" value="留言" />
										</div>
									</div>
									<%-- 右边: 序号列表 --%>
									<div style="line-height:30px;float:left;margin:10px 0;" id="sche_${costCheckId}">
										<table style="border:1px solid #DDDBDC;width:100%;">
											<col width="30"/>
											<col width="80"/>
											<col width="90"/>
											<col width="60"/>
											<col/>
											<thead>
												<tr class="bg-title">
													<td align="center">序号</td>
													<td>进度</td>
													<td>目标时间</td>
													<td colspan="2">完成状况</td>
												</tr>
											</thead>
											<tbody>
												<s:iterator value="costCheckSchedules" status="s" >   
													<tr <s:if test="scheduleTypeCd == 4">class="mustTrColor"</s:if>><!-- 必填提示 -->
														<td align="center">${serialNo}</td>
														<td>${scheduleTypeName}</td>
														<!-- 目标时间 -->
														<td>
															<input type="text" 
																class="Wdate"
																name="costCheckSchedules[<s:property value="#s.index"/>].targetDate" 
																value='<s:date name="targetDate" format="yyyy-MM-dd"/>' 
																style="width:100px;" onfocus="WdatePicker()" 
																<security:authorize ifNotGranted="A_CCHECK_EDIT,A_CCHECK_CONFIRM">
																disabled=disabled
																</security:authorize>
																<s:if test="scheduleTypeName == '审批'">
																	id="bid_date_${costCheckId}";
 																</s:if>
															/>
														</td>
														<!-- 完成情况 -->
														<td style="padding-left:5px;" colspan="2">
															<input type="checkbox" id="${costCheckScheduleId}_sche_${serialNo}" onclick="changeScheValueCheck(this,'completeDate_${costCheckScheduleId}',${scheduleTypeCd},'${dataTypeCd}','${costCheckId}');" 
															<s:if test="completeStatusCd == 1">
																checked=checked 
															</s:if>
															<security:authorize ifNotGranted="A_CCHECK_EDIT,A_CCHECK_SCHE">
																disabled=diabled
															</security:authorize>
															
															 value="${scheduleTypeCd}"
															/>
															<input type="hidden" value="${completeStatusCd}" value="0" name="costCheckSchedules[<s:property value="#s.index"/>].completeStatusCd" />
															<span style="margin-left:5px;"><s:date name="completeDate" format="M-dd"/></span>
															<input type="hidden" id="completeDate_${costCheckScheduleId}" value="${completeDate}" name="costCheckSchedules[<s:property value="#s.index"/>].completeDate"/>
														</td>
													</tr>
												</s:iterator>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			</s:iterator>
		</s:iterator>
	</tbody>
</table>
</div>
<div style="margin-top:10px;height:20px;width:100%;">
 <div style="float: right;">
	<p:page />
  </div>
</div> 