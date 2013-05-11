<%@page import="java.util.Date"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<!--合同管理页面 -->
<div >
<table class="content_table" id="result_table">
	<thead>
		<tr class="header">
			<th width="80" style="background: none;"></th>
			<th width="85" align="left">序号</th>
		<!--<th width="60" align="left">计划</th>  -->	
			<th align="left">事项</th>
			<th width="60" align="left">负责人</th>
			<th width="60" align="left">进度</th>
			<th width="60" align="left">状态</th>
			<th width="70" align="left">定标时间</th>
			<th width="80" align="left">等待</th>
			<th width="40" align="left">更新
			<span title="按更新时间排序" onclick="updateByDate();"><img src="${ctx}/images/plan/btn_down_10_10.gif"></span>
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
			<tr class="mainTr" id="main_${costCtrlBidPurcId}" 
				dataEdit="false" 
				permEdit="false" 
				permConfirm="false" 
				scheCd = "${scheduleStatusCd}"
				group="group_<s:property value='#column.key'/>"
			>	
				<td click2expand="true">
					<img style="float:left;padding-right:5px;" src="${ctx}/resources/images/common/right_grey.gif">
					<div style="padding-left:10px;" style="height:20px;line-height:20px;">
						<!--<div class="pd-chill-tip" title="低" style="color:#ffb401;float:left;
							<s:if test="importantTypeCd != 0"> display:none; </s:if>"
						>！</div>
						<div class="pd-chill-tip" title="普通" style="color:#f36511;float:left;
							<s:if test="importantTypeCd != 1"> display:none; </s:if>"
						>！</div>
						--><div class="pd-chill-tip" title="重要" style="color:#ff0100;float:left;
							<s:if test="importantTypeCd != 2"> display:none; </s:if>"
						>！</div>
					</div>
					<%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("projectCd"))%>
				</td>
				<td click2expand="true" class="pd-chill-tip" title="${serialType}${serialNo}">${serialType}${serialNo}</td>
		<!-- 	<td click2expand="true">
					<div><p:code2name mapCodeName="planTypeMap" value="planTypeCd"/> </div>
				</td> -->	
				<td click2expand="true">
					<div class="ellipsisDiv_full pd-chill-tip" title="${planContentDesc }" >${planContentDesc }</div>
				</td>
				<td click2expand="true">
					<div>${ownerNames}</div>
				</td>
				<td click2expand="true" 
					<s:if test="(dataTypeCd == 0 && bidStatusCd == 7)||(dataTypeCd == 1 && bidStatusCd == 5)||(dataTypeCd == 2 && bidStatusCd == 2)">
						style="background-color:#FFC125"
					</s:if>
				>
					<s:if test="bidStatusCd != null && bidStatusCd != ''">
					<s:if test="dataTypeCd == 0">
						<p:code2name mapCodeName="bidScheMap" value="bidStatusCd"/>
					</s:if>
					<s:elseif test="dataTypeCd == 1">
					 <p:code2name mapCodeName="purScheMap" value="bidStatusCd"/>
					</s:elseif>
					<s:else>
					     定标
					</s:else>
					</s:if>
				</td>
				<td click2expand="true">
					<s:if test="scheduleStatusCd == 0">新增</s:if>
					<s:if test="scheduleStatusCd == 1">进行中</s:if>
					<s:if test="scheduleStatusCd == 2">已完成</s:if>
				</td>
				<td click2expand="true">
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
				<td click2expand="true" class="pd-chill-tip" title='<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("updator")) %>(<s:date name="updatedDate" format="yyyy-MM-dd HH:mm"/>)'>
					<s:date name="updatedDate" format="MM-dd"/>
				</td>
			</tr>
			
			<!-- 详细信息，包含留言、进度信息 -->
			<tr class="detailTr" group="group_<s:property value='#column.key'/>" id="detail_${costCtrlBidPurcId}" valign="top" style="display: none;">
				<td colspan="9" style="border-bottom:1px solid #AAABB0;" >
					<form action="${ctx}/plan/cost-ctrl-bid-purc!save.action" id="form_${costCtrlBidPurcId}" method="post">
					<input type="hidden" name="id" value="${costCtrlBidPurcId}"/>
					<input type="hidden" name="pageType" value="${pageType}"/>
					<input type="hidden" name="confirmFlg" id="confirmFlg_${costCtrlBidPurcId}"/>
					<input type="hidden" name="conStatusChangeFlg" id="conStatusChangeFlg_${costCtrlBidPurcId}" />
					<input type="hidden" name="bidStatusCd" id="bidStatusCd_${costCtrlBidPurcId}" value="${bidStatusCd}"/>
					<input type="hidden" name="bidDate" id="bidDate_${costCtrlBidPurcId}" value='<s:date name="bidDate" format="yyyy-MM-dd"/>'/>
					
					<!-- 审核日期 -->
					<input type="hidden" name="chiefDate" id="chiefDate_${costCtrlBidPurcId}"/>
					
					<table style="width:100%;border-top:1px solid #AAABB0;" >
						<tr>
							<td align="center" style="border-right:1px solid #DCDCDE;border-bottom:1px solid #DCDCDE;background-color: #E4E7EC;">开始时间</td>
							<td  style="border-bottom:1px solid #DCDCDE;">
								<div style="height:28px;padding:5px;">
								 <div style="float:left;padding-top:10px;">
									<span><s:date name="createdDate" format="yyyy-MM-dd hh:mm"/></span>
									<span>创建人:</span>
									<span><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %></span>
								 </div>
									<!-- 合同管理确认完成权限 -->
									<security:authorize ifAnyGranted="A_CON_CONFIRM">
										<s:if test="scheduleStatusCd == 1">
										 <input type="button" class="button_blue" onclick="conComplete('${costCtrlBidPurcId}');" value="完成"/>
										<%--
										<button class="btn_green_55_20" style="margin-right:5px;" type="button" onclick="conReturn('${costCtrlBidPurcId}')" title="实际招标/采购的定标未完成，可点击退回!">退回</button>
										 --%>
										</s:if>
									</security:authorize>
									<s:if test="scheduleStatusCd == 0 ||scheduleStatusCd == 1">
										<security:authorize ifAnyGranted="A_BID_DELETE">
										<button class="btn_red_35_20" style="margin-right:5px;" type="button" onclick="deleteData('${costCtrlBidPurcId}')">删除</button>
										</security:authorize>
									</s:if>
								</div>
								<s:if test="resApproveId!=null">
								  <input type="button" class="button_green" style="width:80px;" onclick="parent.showAll('${ctx}/res/res-approve-info.action?id=${resApproveId}','resApprove');" value="定标审批表" />
							     </s:if>
							</td>
						</tr>
						<tr>
							<td align="center" class="shortcutsArea" style="border-bottom:1px solid #DCDCDE;border-right:1px solid #DCDCDE;background-color: #E4E7EC;">留言</td>
							<td valign="top" style="border-bottom:1px solid #DCDCDE;">
								<div style="line-height:30px;float:left;margin:10px;width:330px;" >
									<table style="width:100%;border:1px solid #DDDBDC;">
								<!--    <col width="40">
										<col width="80">  -->		
										<col width="200">
										<tr>
											<td colspan="3" class="bg-title" style="padding-left:10px;">等待</td>
										</tr>
								<!-- 
										<tr>
											<td style="padding-left:10px;">
												<input type="checkbox" onclick="changeWaitValue(this,'waitProjectDesc_${costCtrlBidPurcId}');" 
													<s:if test="isWaitProjectFlag == 1">
														checked=checked
													</s:if>
													
													disabled=disabled
												/>
												<input type="hidden" value="${isWaitProjectFlag}" name="isWaitProjectFlag" disabled=disabled/>
											</td>
											<td align="left">项目</td>
											<td><input type="text" id="waitProjectDesc_${costCtrlBidPurcId}" name="waitProjectDesc" value="${waitProjectDesc}" style="width:185px;" disabled=disabled/></td>
										</tr>
										<tr>
											<td style="padding-left:10px;">
												<input type="checkbox" onclick="changeWaitValue(this,'waitTechDesc_${costCtrlBidPurcId}');" 
														<s:if test="isWaitTechFlag == 1">
															checked=checked
														</s:if>
														disabled=disabled
												/>
												<input type="hidden" value="${isWaitTechFlag}" name="isWaitTechFlag" />
											</td>
											<td align="left">技术研发中心</td>
											<td><input type="text" id="waitTechDesc_${costCtrlBidPurcId}" name="waitTechDesc" 
												value="${waitTechDesc}" style="width:185px;" disabled=disabled/></td>
										</tr>
										 -->
										<tr>
											<td style="padding-left:10px;">
												<input type="checkbox" onclick="changeWaitValue(this,'waitDesc_${costCtrlBidPurcId}&waitDeptName_${costCtrlBidPurcId}&waitDeptCd_${costCtrlBidPurcId}');" 
														<s:if test="isWaitOtherFlag == 1">
															checked=checked
														</s:if>
														disabled=disabled
												/>
												<input type="hidden" value="${isWaitOtherFlag}" name="isWaitOtherFlag" />
											</td>
											<td align="left">
												<input type="text" id="waitDeptName_${costCtrlBidPurcId}" name="waitDeptName" 
												value="${waitDeptName}" style="width:100px;" disabled=disabled/>
												<input type="hidden" id="waitDeptCd_${costCtrlBidPurcId}" name="waitDeptCd" 
												value="${waitDeptCd}"/>
											</td>
											<td><input type="text" id="waitDesc_${costCtrlBidPurcId}" name="waitDesc" 
											value="${waitDesc}" style="width:185px;" disabled=disabled/></td>
										</tr>
										<tr style="height:5px;line-height:5px;">
											<td colspan="10">&nbsp;</td>
										</tr>
									</table>
									
									<!-- 留言区域 -->
									<div id="msg_container_${costCtrlBidPurcId}" style="line-height:20px;padding-top:15px;">
										<s:iterator	value="costCtrlMessages">
											<div class="detail_message_div">
												<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>
												(<s:date name="createdDate" format="MM-dd HH:mm"/>):${msgContentDesc}</div>
										</s:iterator>
									</div>
									<div style="margin-top:5px;">
										<textarea style="height: 53px;float:left;" cols="36" rows="3" id="message_${costCtrlBidPurcId}"></textarea>
										<input type="button" class="button_green" style="height:53px;" onclick="saveMsg('${costCtrlBidPurcId}');" value="留言" />
									</div>
								</div>
								<div style="line-height:30px;float:left;margin:10px 10px 10px 10px;" id="sche_${costCtrlBidPurcId}">
									<table style="border:1px solid #DDDBDC;width: 100%;">
										<col width="30"></col>
										<col width="80"></col>
										<col width="90"></col>
										<col width="30"></col>
										<col width="95"></col>
										<thead>
											<tr class="bg-title">
												<td align="center">序号</td>
												<td>进度</td>
												<td>目标时间</td>
												<td colspan="2">完成状况</td>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="costCtrlSchedules" status="s" >   
												<tr>
													<td align="center">${serialNo}</td>
													<td>${scheduleTypeName }</td>
													<td>
														<input type="text" 
															name="costCtrlSchedules[<s:property value="#s.index"/>].targetDate" 
															value='<s:date name="targetDate" format="yyyy-MM-dd"/>' 
															style="width:80px;" onfocus="WdatePicker()" 
															disabled=disabled
															<s:if test="scheduleTypeName == '定标'">
																id = "bid_date_${costCtrlBidPurcId}";
															</s:if>
														/>
													</td>
													<td style="padding-left:5px;">
														<input type="checkbox" id="${costCtrlScheduleId}_sche_${serialNo}"  
														<s:if test="completeStatusCd == 1">
															checked=checked 
														</s:if>
														<security:authorize ifAnyGranted="A_CON_CONFIRM">
														<s:if test="!#s.last">
															disabled=diabled
														</s:if>
														</security:authorize>
														<security:authorize ifNotGranted="A_CON_CONFIRM">
															disabled=diabled
														</security:authorize>
														
														 value="${scheduleTypeCd}"
														/>
														<input type="hidden" value="${completeStatusCd}" value="0" name="costCtrlSchedules[<s:property value="#s.index"/>].completeStatusCd" />
													</td>
													<td>
														<span style="margin-left:5px;"><s:date name="completeDate" format="M-dd"/></span>
														<input type="hidden" id="completeDate_${costCtrlScheduleId}" value="${completeDate}"
															name="costCtrlSchedules[<s:property value="#s.index"/>].completeDate"/>
													    <span id="spanSup_${costCtrlBidPurcId}">
													      <!-- 
															<s:if test="scheduleTypeName=='投标单位确认'">
															 <s:if test="isSuppCompleteFlag==1">
															     <img src="${ctx}/images/plan/pic_confirm.gif" title="已提供需求" style="cursor:pointer;" onclick="selectSuppliers('${costCtrlBidPurcId}','con')"/>
															  </s:if>
															 <s:elseif test="isSuppCompleteFlag==2">
																  <img src="${ctx}/images/plan/pic_finish.gif" title="需求已完成"/>
															 </s:elseif>
															 <s:else>
															 	未提供
															 </s:else>
															</s:if>
															 -->
														</span>
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
 <div style="float:right;">
		<p:page />
  </div>
</div>