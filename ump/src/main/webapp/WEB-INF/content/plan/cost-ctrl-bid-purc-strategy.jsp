<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<!-- 战略页面 -->
<div id="new_templet" style="padding:5px;display: none; ">
	<fieldset style="background-color:#f7f7f7;">
	<legend>新增</legend>
	<form action="${ctx}/plan/cost-ctrl-bid-purc!save.action" method="post" id="new_templet_form">
	<s:hidden name="pageType"></s:hidden>
	<table style="width:100%;line-height:25px;">
		<col width="70px"/>
		<col />
		<tr>
			<td>类型</td>
			<td>
				<select name="dataTypeCd">
					<option value="0">招标</option>
					<option value="1">采购</option>
					<option value="2">战略</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>所属项目</td>
			<td >
				<input type="text" id="projectNameId" style="width:120px;cursor: pointer;" class="projSelect" onclick="initProjectSelect(this)"/>
				<input type="hidden" id="projectCdId" name="projectCd"/>
			</td>
			<!-- 
			<td >计划类型</td>
			<td>
				<select name="planTypeCd" style="width:90px;">
					<option value="0">计划内</option>
					<option value="1">计划外</option>
				</select>
			</td>
			 -->
		</tr>
		<tr>
			<td>重要程度</td>
			<td><select name="importantTypeCd">
					<option value="0">低</option>
					<option value="1" selected="selected">普通</option>
					<option value="2">重要</option>
				</select>
				<span style="padding-left:5px;">序号:</span>
				<span style="padding-left:5px;color:red">由系统自动生成</span>
			</td>
		</tr>
		<tr>
			<td>事项</td>
			<td>
				<textarea name="planContentDesc" id="new_planContentDesc" style="width:80%;height:40px;" ></textarea>
			</td>
		</tr>
		<tr>
			<td>定标时间</td>
			<td>
				<input name="bidDate" id="new_bidDate" style="width:120px;" onfocus="WdatePicker()" class="Wdate"/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td style="line-height: 30px;height: 30px;" valign="middle">
			 <input type="button" class="button_blue" onclick="saveNewCost();" value="保存"/>
			    <input type="button" class="button_red" onclick="cancelAdd();" value="取消"/>
			</td>
		</tr>
	</table>
	</form>
	</fieldset>
</div>
<div >
<table class="content_table" id="result_table">
	<thead>
		<tr class="header">
			<th width="80" style="background: none;"></th>
			<th width="85" align="left">序号</th>
		<!--	<th width="60" align="left">计划</th>  -->
			<th width="60" align="left">类型</th>
			<th align="left">事项</th>
			<th width="60" align="left">负责人</th>
			<th width="60" align="left">进度</th>
			<th width="60" align="left">状态</th>
			<th width="70" align="left">定标时间</th>
			<th width="80" align="left">等待</th>
			<th width="40" align="left" style="cursor:pointer;" title="按更新时间排序" onclick="updateByDate();">
				更新<img src="${ctx}/images/plan/btn_down_10_10.gif">
			</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dataMap" id="column" status="status">   
			<tr id="group_<s:property value='#column.key'/>" class="group"> 
				<td colspan="10" class="group-title">
					<%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("#column.key")) %>(<s:property value='#column.value.size' />)
				</td>
			</tr>
			<s:iterator value="#column.value" status="s">
			<!-- 主信息 -->
			<tr class="mainTr" group="group_<s:property value='#column.key'/>" id="main_${costCtrlBidPurcId}" 
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
				<security:authorize ifAnyGranted="A_N_BP_EDIT">
					<s:if test="scheduleStatusCd == 2">
						permEdit="false"
					</s:if>
					<s:else>
						permEdit="true"
					</s:else>
				</security:authorize>
				<security:authorize ifNotGranted="A_N_BP_EDIT">
					<s:if test="currentUser == creator">
						permEdit="true"
					</s:if>
					<s:else>
						permEdit="false"
					</s:else>
				</security:authorize>
				<security:authorize ifAnyGranted="A_N_BP_CONFIRM">
					<s:if test="scheduleStatusCd == 2">
						permConfirm="false"
					</s:if>
					<s:else>
						permConfirm="true"
					</s:else>
				</security:authorize>
				<security:authorize ifNotGranted="A_N_BP_CONFIRM">
					permConfirm="false"
				</security:authorize>
				
				scheCd = "${scheduleStatusCd}"
				
			>	
				<td click2expand="true">
					<img style="float:left;padding-right:5px;" src="${ctx}/resources/images/common/right_grey.gif">
					<div style="padding-left:10px;" class="cost-read" style="height:20px;line-height:20px;">
						<div class="pd-chill-tip" title="重要" style="color:#ff0100;float:left;
							<s:if test="importantTypeCd != 2"> display:none; </s:if>"
						>！</div>
						<%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("projectCd"))%>
					</div>
					<div class="cost-edit" style="display:none;">
						<select style="float:left;" name="importantTypeCd" onchange="changeImporType(this,'${costCtrlBidPurcId}')">
							<option value="0" style="color:#ffb401" <s:if test="importantTypeCd == 0">selected</s:if>>低</option>
							<option value="1" style="color:#f36511" <s:if test="importantTypeCd == 1">selected</s:if>>普通</option>
							<option value="2"  style="color:#ff0100" <s:if test="importantTypeCd == 2">selected</s:if>>重要</option>
						</select>
					</div>
				</td>
				<td click2expand="true" class="pd-chill-tip" title="${serialType}${serialNo}">${serialType}${serialNo}</td>
				<!-- 
				<td click2expand="true">
					<div class="cost-read" dataEdit="false"><p:code2name mapCodeName="planTypeMap" value="planTypeCd"/> </div>
					<div class="cost-edit" dataEdit="false" style="display:none;">
						<select name="planTypeCd" style="width:60px;" onchange="changePlanType(this,'${costCtrlBidPurcId}')">
							<option value="0" <s:if test="planTypeCd == 0">selected</s:if>>计划内</option>
							<option value="1" <s:if test="planTypeCd == 1">selected</s:if>>计划外</option>
						</select>
					</div>
				</td>
				 -->
				<td click2expand="true">
					<s:if test="dataTypeCd == 0">
						招标
					</s:if>
					<s:if test="dataTypeCd == 1">
						采购
					</s:if>
					<s:if test="dataTypeCd == 2">
						战略
					</s:if>
				</td>
				<td click2expand="true">
					<div class="ellipsisDiv_full cost-read pd-chill-tip" dataEdit="false" title="${planContentDesc}" >${planContentDesc}</div>
					<div class="cost-edit" dataEdit="false" style="display:none;" >
						<textarea style="width:98%" onchange="changeDesc(this,'${costCtrlBidPurcId}')">${planContentDesc}</textarea>
					</div>
				</td>
				<%-- 负责人--%>
				<td click2expand="true">
					<div class="cost-read" confirmEdit="true">${ownerNames}</div>
					<div class="cost-edit" confirmEdit="true" style="display:none;">
						<textarea id="${costCtrlBidPurcId}UserNames" style="width:55px;cursor: pointer;" onclick="changeOwner(this,'${costCtrlBidPurcId}')">${ownerNames}</textarea>
						<input type="hidden" id="${costCtrlBidPurcId}UserCds" value="${ownerCds}"/>
					</div>
				</td>
				<%-- 进度--%>
				<td click2expand="true" 
					<s:if test="dataTypeCd == 0 && bidStatusCd == 7">
						style="background-color:#FFC125"
					</s:if>
					<s:if test="dataTypeCd == 1 && bidStatusCd == 5">
						style="background-color:#FFC125"
					</s:if>
				>
					<s:if test="bidStatusCd != null && bidStatusCd != ''">
					<s:if test="dataTypeCd == 0">
						<p:code2name mapCodeName="bidScheMap" value="bidStatusCd"/>
					</s:if>
					<s:if test="dataTypeCd == 1">
						<p:code2name mapCodeName="purScheMap" value="bidStatusCd"/>
					</s:if>
					</s:if>
				</td>
				<%-- 状态 --%>
				<td click2expand="true">
					<s:if test="scheduleStatusCd == 0">新增</s:if>
					<s:if test="scheduleStatusCd == 1">进行中</s:if>
					<s:if test="scheduleStatusCd == 2">已完成</s:if>
					<s:else>
						<%if(JspUtil.compareCurDate("bidDate")){ %>
						<s:if test="contractStatusCd == 0">
							<img title="延期" src="${ctx}/resources/images/plan/pic_delay.gif">
						</s:if>
					    <%} %>
				    </s:else>
				</td>
				<td click2expand="true" 
					<s:if test="scheduleStatusCd != 2">
						<%if(JspUtil.compareCurDate("bidDate")){ %>
						<s:if test="contractStatusCd == 0">
							style="color:red" title="已延期" class="pd-chill-tip" 
						</s:if>
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
				<td click2expand="true" class="pd-chill-tip" title='<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("updator")) %>(<s:date name="updatedDate" format="yyyy-MM-dd HH:mm"/>)'>
					<s:date name="updatedDate" format="MM-dd"/>
				</td>
			</tr>
			
			<!-- 详细信息，包含留言、进度信息 -->
			<tr class="detailTr" group="group_<s:property value='#column.key'/>" id="detail_${costCtrlBidPurcId}" valign="top" style="display: none;">
				<td colspan="10" style="border-bottom:1px solid #AAABB0;" >
					<form action="${ctx}/plan/cost-ctrl-bid-purc!save.action" id="form_${costCtrlBidPurcId}" method="post">
					<input type="hidden" name="id" value="${costCtrlBidPurcId}"/>
					<input type="hidden" name="pageType" value="${pageType}"/>
					<input type="hidden" name="confirmFlg" id="confirmFlg_${costCtrlBidPurcId}"/>
					<input type="hidden" name="conStatusChangeFlg" id="conStatusChangeFlg_${costCtrlBidPurcId}" />
					<input type="hidden" name="bidStatusCd" id="bidStatusCd_${costCtrlBidPurcId}" value="${bidStatusCd}"/>
					<input type="hidden" name="bidDate" id="bidDate_${costCtrlBidPurcId}" value='<s:date name="bidDate" format="yyyy-MM-dd"/>'/>
					<input type="hidden" name="resApproveCd" id="resNo_${costCtrlBidPurcId}" value="${resApproveCd}"/>
					<input type="hidden" name="resApproveId" id="resId_${costCtrlBidPurcId}" value="${resApproveId}"/>
					<input type="hidden" id="dataTypeCd_${costCtrlBidPurcId}" value="${dataTypeCd}"/>
					<!-- 审核日期 -->
					<input type="hidden" name="chiefDate" id="chiefDate_${costCtrlBidPurcId}"/>
					
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
									<!-- 招标权限 -->
									<s:if test="scheduleStatusCd == 0">
										<security:authorize ifAnyGranted="A_ST_BP_CONFIRM,A_N_BP_EDIT">
										<input type="button" class="btn_blue" onclick="confirmCost('${costCtrlBidPurcId}')" title="确认负责人及完成时间"  value="确认" />
										</security:authorize>
									</s:if>
									<s:elseif test="scheduleStatusCd == 1">
										<security:authorize ifAnyGranted="A_N_BP_CONFIRM,A_N_BP_EDIT,A_N_BP_SCHE">
										<input type="button" class="btn_blue" onclick="saveCost('${costCtrlBidPurcId}')" value="保存" />
										</security:authorize>
									</s:elseif>
									
									<!-- 未完成的计划可以进行删除 -->
									<s:if test="scheduleStatusCd == 0 ||scheduleStatusCd == 1">
										<security:authorize ifAnyGranted="A_ST_BP_DELETE">
										<input type="button" class="btn_red" onclick="deleteData('${costCtrlBidPurcId}')" value="删除" />
										</security:authorize>
									</s:if>
								<span id="spanShowRes_${costCtrlBidPurcId}"> </span>
								    <s:if test="resApproveId!=null">
							            <input type="button" class="btn_red" style="width:80px;" onclick="parent.showAll('${ctx}/res/res-approve-info.action?id=${resApproveId}','resApprove');" value="定标审批表" />
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
									<!--<col width="40">
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
													<security:authorize ifNotGranted="A_N_BP_EDIT,A_N_BP_SCHE">
														disabled=disabled
													</security:authorize>
												/>
												<input type="hidden" value="${isWaitProjectFlag}" name="isWaitProjectFlag" />
											</td>
											<td align="left">项目</td>
											<td><input type="text" id="waitProjectDesc_${costCtrlBidPurcId}" name="waitProjectDesc" value="${waitProjectDesc}" style="width:185px;" readonly="readonly"/></td>
										</tr>
										<tr>
											<td style="padding-left:10px;">
												<input type="checkbox" onclick="changeWaitValue(this,'waitTechDesc_${costCtrlBidPurcId}');" 
													<s:if test="isWaitTechFlag == 1">
														checked=checked
													</s:if>
													<security:authorize ifNotGranted="A_N_BP_EDIT,A_N_BP_SCHE">
														disabled=disabled
													</security:authorize>
												/>
												<input type="hidden" value="${isWaitTechFlag}" name="isWaitTechFlag" />
											</td>
											<td align="left">技术研发中心</td>
											<td><input type="text" id="waitTechDesc_${costCtrlBidPurcId}" name="waitTechDesc" value="${waitTechDesc}" style="width:185px;" readonly="readonly"/></td>
										</tr>
										-->
										<tr>
											<td style="padding-left:10px;padding-top:5px;">
												<input type="checkbox" onclick="changeWaitValue(this,'waitDesc_${costCtrlBidPurcId}&waitDeptName_${costCtrlBidPurcId}&waitDeptCd_${costCtrlBidPurcId}');" 
													<s:if test="isWaitOtherFlag == 1">checked=checked</s:if>
													<security:authorize ifNotGranted="A_N_BP_EDIT,A_N_BP_SCHE">
														disabled=disabled
													</security:authorize>
												/>
												<input type="hidden" value="${isWaitOtherFlag}" name="isWaitOtherFlag" />
											</td>
											<td align="left" style="padding-top:5px;">
												<input type="text" id="waitDeptName_${costCtrlBidPurcId}" onfocus="showWaitDept(this,'${costCtrlBidPurcId}')" name="waitDeptName" value="${waitDeptName}" style="width:100px;cursor:pointer;" readonly="readonly"/>
												<input type="hidden" id="waitDeptCd_${costCtrlBidPurcId}" name="waitDeptCd" value="${waitDeptCd}"/>
											</td>
											<td style="padding-top:5px;"><input type="text" id="waitDesc_${costCtrlBidPurcId}" name="waitDesc" value="${waitDesc}" style="width:185px;" readonly="readonly"/></td>
										</tr>
										<tr style="height:5px;line-height:5px;">
											<td colspan="11">&nbsp;</td>
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
								<%-- 右边: 序号列表 --%>
								<div style="line-height:30px;float:left;margin:10px 0;" id="sche_${costCtrlBidPurcId}">
									<table style="border:1px solid #DDDBDC;width:100%;">
										<col width="30"/>
										<col width="80"/>
										<col width="90"/>
										<col width="30"/>
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
										<c:set var="trLength" value="1"/>
											<s:iterator value="costCtrlSchedules" status="s" >  
											  <s:if test="!(dataTypeCd == 0 && (scheduleTypeCd == 4||scheduleTypeCd ==5))" >
											  <%-- 0-招标 --%>
											  <%-- 7-定标节点(确认完，不可编辑) --%>
												<tr 
													<s:if test="dataTypeCd == 0">
													 	<s:if test="scheduleTypeCd != 0">
													 		<s:if test="scheduleTypeName == '定标'">
																class="mustTrBid"
															</s:if>
															<s:else>
																class="mustTrColor"
															</s:else>
														</s:if>
													</s:if>
													<%-- 1-采购--%>
													<s:elseif test="dataTypeCd == 1">
													  <s:if test="cheduleTypeCd != 0 && scheduleTypeCd != 2">
													 		<%-- 5-定标节点(确认完，不可编辑) --%>
													 		<s:if test="scheduleTypeName == '定标'">
																class="mustTrBid"
															</s:if>
															<s:else>
																class="mustTrColor"
															</s:else>
													  </s:if>
													</s:elseif>
													<%-- 2-战略--%>
													<s:elseif test="dataTypeCd == 2">
													 	<%-- 2-定标节点(确认完，不可编辑) --%>
													   	<s:if test="scheduleTypeName == '定标'">
															class="mustTrBid"
													  	</s:if>
													</s:elseif>
													>
													<td align="center">${trLength}</td>
													<td>${scheduleTypeName}</td>
													<td>
														<input type="text" 
															name="costCtrlSchedules[<s:property value="#s.index"/>].targetDate" 
															value='<s:date name="targetDate" format="yyyy-MM-dd"/>' 
															style="width:80px;" onfocus="processFocusBidDate(this,'<%= SpringSecurityUtils.getCurrentUiid() %>','${costCtrlBidPurcId}','${dataTypeCd}','${scheduleTypeCd}','${scheduleTypeName}')" 
															

															<%-- 计划进行确认后，无法对‘定标’节点的目标时间进行调整 --%>
															<s:if test="scheduleTypeName == '定标'">
																id = "bid_date_${costCtrlBidPurcId}";
																<s:if test="scheduleStatusCd == 1"><%-- 1-计划已确认 --%>
																	disabled=disabled
																</s:if>
																<s:else>
																	<security:authorize ifAnyGranted="A_N_BP_SCHE">
																		disabled=disabled
																	</security:authorize>
																</s:else>
															</s:if>
															<s:else> 
																<security:authorize ifNotGranted="A_N_BP_EDIT,A_N_BP_CONFIRM,A_N_BP_SCHE">
																	disabled=disabled
																</security:authorize>
															</s:else>
														/>
													</td>
													<td style="padding-left:5px;">
														<input type="checkbox" id="${costCtrlScheduleId}_sche_${serialNo}" onclick="changeScheValue(this,'completeDate_${costCtrlScheduleId}',${scheduleTypeCd},${dataTypeCd},'${costCtrlBidPurcId}');" 
														<s:if test="completeStatusCd == 1">
															checked=checked 
														</s:if>
														<security:authorize ifAnyGranted="A_N_BP_EDIT,A_N_BP_SCHE">
															<s:if test="#s.last">
																disabled=diabled
															</s:if>
														</security:authorize>
														<security:authorize ifNotGranted="A_N_BP_EDIT,A_N_BP_SCHE">
															disabled=diabled
														</security:authorize>
														
														 value="${scheduleTypeCd}"
														/>
														<input type="hidden" value="${completeStatusCd}" value="0" name="costCtrlSchedules[<s:property value="#s.index"/>].completeStatusCd" />
													</td>
													<td>
														<span style="margin-left:5px;"><s:date name="completeDate" format="M-dd"/></span>
														<input type="hidden" id="completeDate_${costCtrlScheduleId}" value="${completeDate}" name="costCtrlSchedules[<s:property value="#s.index"/>].completeDate"/>
													
														<%-- 注意顺序,在completeDate之后 --%>
														<s:if test="(dataTypeCd == 1 && scheduleTypeCd == 5)||(dataTypeCd == 0 && scheduleTypeCd == 7)">
														<span id="span_chiefDate_${costCtrlBidPurcId}" style="color:red;"></span>
														</s:if>
														
													    <span id="spanSup_${costCtrlBidPurcId}">
															<!-- 
															<s:if test="scheduleTypeName=='投标单位确认'">
															<s:if test="isSuppCompleteFlag==1">
														        <img src="${ctx}/images/plan/pic_confirm.gif" title="已提供需求" style="cursor:pointer;"
														        <security:authorize ifAnyGranted="A_N_BP_EDIT,A_N_BP_SCHE">
														        onclick="selectSuppliers('${costCtrlBidPurcId}','bid')"
														        </security:authorize>
														        />
															</s:if>
															<s:elseif test="isSuppCompleteFlag==2">
																<img src="${ctx}/images/plan/pic_finish.gif" title="需求已完成"/>
															</s:elseif>
															<s:else>
															 	<security:authorize ifAnyGranted="A_N_BP_EDIT,A_N_BP_SCHE">
															 		<span id="sup_sel_${costCtrlBidPurcId}">
															        <button class="btn_green_55_20" type="button" onclick="selectSuppliers('${costCtrlBidPurcId}','bid')">选择</button>
															        </span>
															 	</security:authorize>
														        <span id="sup_have_${costCtrlBidPurcId}" style="display:none">
														          	<img src="${ctx}/images/plan/pic_confirm.gif" title="已提供需求" style="cursor:pointer;"
															          onclick="selectSuppliers('${costCtrlBidPurcId}','bid')"
															        />
															 	</span>
															</s:else>
															</s:if>
															 -->
														</span>
													</td>
												</tr>
												<c:set var="trLength" value="${trLength+1}"/>
												</s:if>
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
<script type="text/javascript">
	<security:authorize ifAnyGranted="A_BP_ADD">
		$('#btn_add_cost').show();
	</security:authorize>
	<security:authorize ifNotGranted="A_BP_ADD">
	$('#btn_add_cost').hide();
	</security:authorize>
</script>