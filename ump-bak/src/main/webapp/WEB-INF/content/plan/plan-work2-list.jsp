<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/common/taglibs.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.springside.modules.web.struts2.Struts2Utils"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="java.util.Date"%>
<%@page import="com.hhz.core.utils.CollectionHelper"%>
<%@page import="java.util.List"%>
<%@page import="com.hhz.core.utils.RandomUtils"%>
<%
	Date nowDate = DateOperator.truncDate(new Date());
	Date nextDate = DateOperator.addDays(nowDate, 1);
%>
<s:set var="nextDate" ><%=DateOperator.formatDate(nextDate,"yyyy-MM-dd")%></s:set>
<script type="text/javascript">
	$("#orderStr1").val("${orderStr1}");
	$("#orderStr2").val("${orderStr2}");
	$("#orderDir1").val("${orderDir1}");
	$("#orderDir2").val("${orderDir2}");
	if_in_attention = "${if_in_attention}";
	now_year = "${now_year}";
	now_month = "${now_month}";
	centerCd = "${centerCd}";
	isEditOrg = "${isEditOrg}";
	if(1==isEditOrg){
		isEditOrg = true;
	}else{
		isEditOrg = false;
	}
	$("#countAll").html("${countAll}");
	$("#countComplete").html("${countComplete}");
	$("#countFromPrev").html("${countFromPrev}");
	$("#countFromPrevCompleted").html("${countFromPrevCompleted}");
	$("#completedRate").html("${completedRate}");
	arr_ids = new Array();
	temp_if_arr_ids_hasId = false;
</script>
<s:set var="entityTmpId_New" ><%=RandomUtils.generateTmpEntityId() %></s:set>
<form id="newScheForm" method="post">
	<input type="hidden" name="page.pageNo" value="1"/>
	<input type ="hidden" name="planYear"/>
	<input type ="hidden" name="planMonth"/>
	<input type ="hidden" name="planType"/>
	<input type ="hidden" name="statusCd" value="0"/>
	<input type ="hidden" name="centerCd"/>
	<input type ="hidden" name="serialNumber" value="${newSerialNumber}"/>
	<input type ="hidden" name="serialOrder" value="${newSerialOrder}"/>
	<input type ="hidden" name="targetPointCd"/>
	<input type ="hidden" name="area"/>
	<input type ="hidden" name="content"/>
	<input type ="hidden" name="principal"/>
	<input type ="hidden" name="targetDate"/>
	<input type ="hidden" name="newMessage"/>
	<input type="hidden" name="entityTmpId" value="${entityTmpId_New}" />
	<input type="hidden" name="isEditOrg" value="${isEditOrg}"/>
</form>
<table class="content_table" id="scheTable">
	<thead>
		<tr>
			<th align="left" width="71px;" colspan="2" style="background:none;">
			<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_VICE,A_PLAN_WORK2_OFFICE">
			 <input type="button" class="btn_green" onclick="doExchangeOrder();" value="交换顺序"/>
			</security:authorize>
			</th>
			<th align="left" width="64px" nowrap="nowrap" onClick="doClickOrder2('serialOrder')" style="cursor:pointer; word-break: break-all;">编号<span id="order_serialOrder"><c:if test="${orderStr2 eq 'serialOrder'}"><c:if test="${orderDir2 eq 'ASC'}"><img src='${ctx}/images/plan/btn_up_10_10.gif'/></c:if><c:if test="${orderDir2 eq 'DESC'}"><img src='${ctx}/images/plan/btn_down_10_10.gif'/></c:if></c:if></span></th>
			<th align="left" width="80px;">类型</th>
			<th align="left" nowrap="nowrap">工作内容</th>
			<th align="left" width="80px" nowrap="nowrap">机构</th>
			<th align="left" width="50px" nowrap="nowrap" onClick="doClickOrder2('targetDate')" style="cursor:pointer; word-break: break-all;">目标<span id="order_targetDate"><c:if test="${orderStr2 eq 'targetDate'}"><c:if test="${orderDir2 eq 'ASC'}"><img src='${ctx}/images/plan/btn_up_10_10.gif'/></c:if><c:if test="${orderDir2 eq 'DESC'}"><img src='${ctx}/images/plan/btn_down_10_10.gif'/></c:if></c:if></span></th>
			<th align="left" width="50px" nowrap="nowrap">状态</th>
			<th align="left" width="50px" nowrap="nowrap" onClick="doClickOrder2('updatedDate')" style="cursor:pointer; word-break: break-all;">更新<span id="order_updatedDate"><c:if test="${orderStr2 eq 'updatedDate'}"><c:if test="${orderDir2 eq 'ASC'}"><img src='${ctx}/images/plan/btn_up_10_10.gif'/></c:if><c:if test="${orderDir2 eq 'DESC'}"><img src='${ctx}/images/plan/btn_down_10_10.gif'/></c:if></c:if></span></th>
			<th align="left" width="50px" nowrap="nowrap">附件</th>
			<s:if test="4==quarter_status||5==quarter_status||6==quarter_status||7==quarter_status||8==quarter_status||9==quarter_status">
			</s:if>
			<s:if test="5==quarter_status||6==quarter_status||7==quarter_status||8==quarter_status||9==quarter_status">
				<th align="left" width="120px" nowrap="nowrap">考评分<span class="color_red" id="pointTitleMsg2" style="display:none;">(点击此列评分)</span></th>
			</s:if>
		</tr>
	</thead>
	<tbody>
		<tr id="newScheTemplate1" class="detailTr"  style="display:none; height:60px;">
			<td colspan="3" align="center">${newSerialNumber}${newSerialOrder}</td>
			<td>
				<select class="planType_class" id="new_planType">
					<option value="2">月会工作</option>
					<option value="3">项目管理</option>
					<option value="4">指令单</option>
					<option value="5">综合</option>
					<option value="6">季度KPI</option>
					<option value="7">四级计划</option>
					<option value="8">中心工作</option>
				</select>
			</td>
			<td colspan="3"><textarea id="new_content" rows="3" cols="24"></textarea></td>
			<td colspan="2">
				<s:textfield id="new_targetDate" onfocus="WdatePicker()" cssClass="Wdate" cssStyle="width:80px"/>
			</td>
		    <td <s:if test="4==quarter_status"> colspan="2"</s:if><s:if test="5==quarter_status||6==quarter_status||7==quarter_status||8==quarter_status||9==quarter_status"> colspan="3"</s:if>><a href="javascript: attachManage('', event);" onclick="openAttachment('附件管理','${planWork2Id==null?entityTmpId_New:planWork2Id}'); return false;" ><img id="new_img_atta" src="${ctx}/resources/images/common/atta.gif" /></a></td>
		</tr>
		
		<tr id="newScheTemplate2" class="detailTr" style="display:none; height:60px;">
		    <td colspan="4" align="right">
		    	留言：
		    </td>
		    <td colspan="3">
		    	<div style="float:left;"><textarea style="display:inline" id="new_newMessage" rows="3" cols="24"></textarea></div>
		    </td>
		    <td <s:if test="4==quarter_status"> colspan="4"</s:if><s:if test="5==quarter_status||6==quarter_status||7==quarter_status||8==quarter_status||9==quarter_status"> colspan="5"</s:if> colspan="3" valign="middle">
				<div style="float:left; padding-left:12px;" id="monthScheSave">
					<input type="button" class="btn_blue" onclick="addSaveSchedule('${centerCd}');" value="保存"/>
					<input type="button" class="btn_red" onclick="cancelSchedule();" value="取消"/>
				</div>
		    </td>
		</tr>
		<s:if test="page.result.size == 0">
		<tr>
			<td id="noResult_td" <s:if test="4==quarter_status"> colspan="11"</s:if><s:if test="5==quarter_status||6==quarter_status||7==quarter_status||8==quarter_status||9==quarter_status"> colspan="12"</s:if> colspan="10" align="center" style="height:200px;">没有搜索到的记录</td>
		</tr>
		</s:if>
		<s:iterator value="page.result" var="pagePlanWork">
		<tr id="main_${planWork2Id}" class="mainTr" style="cursor:pointer;<s:if test="statusCd==7"> color:grey;</s:if>">
			<td id="chk_${planWork2Id}" nowrap="nowrap" colspan="2" onclick='scheClick("${planWork2Id}");' align="center">
				<div style="display:none;" id="attention_recordVersion_${planWork2Id}"></div>
				<div style="display:none;" id="attention_status_${planWork2Id}">${statusCd}</div>
				<div style="display:inline;"><img id="attention_${planWork2Id}" title="点击取消关注" attentionFlg="1" src="${ctx}/pics/email/attention.gif" onclick='CAN_scheClick=false;doDeleteAttention("planWork2","${planWork2Id}");' style="display:none;"/><img id="attention_cancel_${planWork2Id}" title="点击关注,该条将在您的首页自动提示是否有更新" attentionFlg="0" src="${ctx}/pics/email/attention_cancel.gif" onclick='CAN_scheClick=false;doAddAttention("planWork2","${planWork2Id}","${recordVersion}");' style="display:none;"/></div>
				<div style="display:none;" id="attention_unread_img_${planWork2Id}"><img class="new_img" src="${ctx}/resources/images/common/new.gif" /></div>
				<div style="display:inline;"><input type="checkbox" id="chk_all" value="${planWork2Id}" onClick="CAN_scheClick=false;" recordVersion="${recordVersion}"></input></div>
				<div style="display:inline;"><img id="down_arrow_${planWork2Id}" src="${ctx}/resources/images/common/right_grey.gif"/><img id="up_arrow_${planWork2Id}" src="${ctx}/resources/images/common/down_grey.gif" style="display:none;"/></div>
			</td>
			<td id="td_serialOrder_${planWork2Id}" nowrap="nowrap" onclick='scheClick("${planWork2Id}");toggleDetail(this);'>
				<div id="serialOrder_show_${planWork2Id}" class="span_show"><s:if test="1==if_in_weight">${planMonth}月-</s:if>${serialOrder}</div>
				<div id="serialOrder_hide_${planWork2Id}" class="span_hide" style="display:none;">
					<input type='text' id="serialOrder_input_${planWork2Id}" onblur="updateRecord('${planWork2Id}','serialOrder',$(this).val());" value="${serialOrder}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;"/>
				</div>
			</td>
			<td id="td_planType_${planWork2Id}" val="${planType}" onclick='scheClick("${planWork2Id}");toggleDetail(this,"${planWork2Id}");'>
				<div id="planType_show_${planWork2Id}" class="span_show">
					<s:if test="planType==1">年计划</s:if>
					<s:if test="planType==2">月会工作</s:if>
					<s:if test="planType==3">项目管理</s:if>
					<s:if test="planType==4">指令单</s:if>
					<s:if test="planType==5">综合</s:if>
					<s:if test="planType==6">季度KPI</s:if>
					<s:if test="planType==7">四级计划</s:if>
					<s:if test="planType==8">中心工作</s:if>
				</div>
				<div id="planType_hide_${planWork2Id}" class="span_hide" style="display:none;">
					<select class="planType_class" id="planType_input_${planWork2Id}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="updateRecord('${planWork2Id}','planType',$(this).val());" >
						<option value="1" <s:if test="planType==1">selected</s:if> disabled>年计划</option>
						<option value="2" <s:if test="planType==2">selected</s:if>>月会工作</option>
						<option value="3" <s:if test="planType==3">selected</s:if>>项目管理</option>
						<option value="4" <s:if test="planType==4">selected</s:if>>指令单</option>
						<option value="5" <s:if test="planType==5">selected</s:if>>综合</option>
						<option value="6" <s:if test="planType==6">selected</s:if>>季度KPI</option>
						<option value="7" <s:if test="planType==7">selected</s:if>>四级计划</option>
						<option value="8" <s:if test="planType==8">selected</s:if>>中心工作</option>
					</select>
				</div>
			</td>
			<td id="td_content_${planWork2Id}" onclick='scheClick("${planWork2Id}");toggleDetail(this,"${planWork2Id}");'>
				<div id="content_show_${planWork2Id}" class="span_show ellipsisDiv pd-chill-tip" title="<p:code2name mapCodeName="mapContentTips" value="planWork2Id" />" style="word-break:break-all; word-wrap:break-word; white-space:normal;">${content}</div>
				<div id="content_hide_${planWork2Id}" class="span_hide" style="display:none;">
					<textarea id="content_input_${planWork2Id}" rows="5" style="width:100%;" onblur="updateRecord('${planWork2Id}','content',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;">${content}</textarea>
				</div>
			</td>
			<td onclick='scheClick("${planWork2Id}");' nowrap="nowrap"><%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("centerCd"))%></td>
			<td id="td_targetDate_${planWork2Id}" nowrap="nowrap" title="<s:date name='targetDate' format='yyyy-MM-dd'/>" onclick='scheClick("${planWork2Id}");toggleDetail(this,"${planWork2Id}");'>
				<div id="targetDate_show_${planWork2Id}" class="span_show">
					<s:date name="targetDate" format="MM-dd"></s:date>
				</div>
				<div id="targetDate_hide_${planWork2Id}" class="span_hide" style="display:none;">
					<input type='text' id="targetDate_input_${planWork2Id}" class="Wdate" onblur="updateRecord('${planWork2Id}','targetDate',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;WdatePicker();"/>
				</div>
			</td>
			<td id="td_statusCd_${planWork2Id}" nowrap="nowrap" onclick='scheClick("${planWork2Id}");'>
				<s:if test="statusCd==0"><img src="${ctx}/resources/images/common/status_unconfirm.gif" title="未确认"></s:if>
				<s:elseif test="statusCd==1"><img src="${ctx}/resources/images/common/status_confirm.gif" title="进行中"></s:elseif>
				<s:elseif test="statusCd==2"><img src="${ctx}/resources/images/common/status_prefinish.gif" title="预完成"></s:elseif>
				<s:elseif test="statusCd==3"><img src="${ctx}/resources/images/common/status_predel.gif" title="申请删除"></s:elseif>
				<s:elseif test="statusCd==4"><img src="${ctx}/resources/images/common/status_finish.gif" title="已完成"></s:elseif>
				<s:elseif test="statusCd==5"><img src="${ctx}/resources/images/common/status_del.gif" title="已删除"></s:elseif>
				<s:elseif test="statusCd==6"><img src="${ctx}/resources/images/common/status_hidden.gif" title="已隐藏"></s:elseif>
				<s:elseif test="statusCd==7">非本月</s:elseif>
				<s:elseif test="statusCd==8"><img src="${ctx}/resources/images/common/status_suspend.gif" title="已过期"></s:elseif>
				<s:elseif test="statusCd==9">非考核性过期</s:elseif>
				<s:elseif test="statusCd==10"><img src="${ctx}/resources/images/common/status_completeDely.gif" title="完成但曾过期"></s:elseif>
			</td>
			<td onclick='scheClick("${planWork2Id}");' id="td_updateDate_${planWork2Id}" nowrap="nowrap" title="<s:date name='updatedDate'/>&#13更新人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("updator"),";") %>" onclick='scheClick("${planWork2Id}");'><s:date name="updatedDate" format="MM-dd"></s:date></td>
			<td>
				<span onclick="openAttachment('附件管理','${planWork2Id==null?entityTmpId_New:planWork2Id}'<s:if test='aOnlyCreator'>,'onlyCreator'</s:if>); return false;" >
					<s:if test="attachFlg == 1"><img src="${ctx}/resources/images/common/atta_y.gif" /></s:if>
					<s:else><img src="${ctx}/resources/images/common/atta.gif" /></s:else>
				</span>
			</td>
			<s:if test="4==quarter_status||5==quarter_status||6==quarter_status||7==quarter_status||8==quarter_status||9==quarter_status">
				<td class="td_point" id="td_weightPoint_${planWork2Id}" nowrap="nowrap" title="" onclick='toggleDetailPoint(this,"${planWork2Id}","weightPoint");'>
					<div id="weightPoint_show_${planWork2Id}" class="span_show"><s:if test="1==if_in_weight&&4==quarter_status">${bogusWeightPoint}</s:if><s:else>${weightPoint}</s:else></div>
					<div id="weightPoint_hide_${planWork2Id}" class="span_hide" style="display:none;">
						<input type="text" id="bogusWeightPoint_input_${planWork2Id}" value="${bogusWeightPoint}" onblur="updateRecord('${planWork2Id}','bogusWeightPoint',$(this).val());" onkeypress="onkeypressPoint(event,'${planWork2Id}','bogusWeightPoint',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" style="width:30px;"/>
						<input type="text" id="weightPoint_input_${planWork2Id}" value="${weightPoint}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" style="display:none"/>
					</div>
				</td>
			</s:if>
			<s:if test="5==quarter_status||6==quarter_status||7==quarter_status||8==quarter_status||9==quarter_status">
				<td nowrap="nowrap" onclick='toggleDetailPoint(this,"${planWork2Id}","<s:if test="5==quarter_status">selfPoint</s:if><s:if test="6==quarter_status">selfCheckPoint</s:if><s:if test="7==quarter_status">evaluatePoint</s:if><s:if test="8==quarter_status">finalPoint</s:if>","${planType}");' class="td_point pd-chill-tip" title="<p:code2name mapCodeName="mapPointTips" value="planWork2Id" />">
					<div style="float:left;width:29px;" id="selfPoint_show_${planWork2Id}"><s:if test="selfPoint>=0">${selfPoint}</s:if><s:else>&nbsp;</s:else></div>
					<div style="float:left;width:29px;display:none;" id="selfPoint_hide_${planWork2Id}"><input type='text' id="selfPoint_input_${planWork2Id}" value="${selfPoint}" onblur="updateRecord('${planWork2Id}','selfPoint',$(this).val());" onkeypress="onkeypressPoint(event,'${planWork2Id}','selfPoint',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" style="width:30px;<s:if test="5!=quarter_status"> display:none;</s:if>"/></div>
					<div style="float:left;width:29px;" id="selfCheckPoint_show_${planWork2Id}"><s:if test="selfCheckPoint>=0">${selfCheckPoint}</s:if><s:else>&nbsp;</s:else></div>
					<div style="float:left;width:29px;display:none;" id="selfCheckPoint_hide_${planWork2Id}"><input type='text' id="selfCheckPoint_input_${planWork2Id}" value="${selfCheckPoint}" onblur="updateRecord('${planWork2Id}','selfCheckPoint',$(this).val());" onkeypress="onkeypressPoint(event,'${planWork2Id}','selfCheckPoint',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" style="width:30px;<s:if test="6!=quarter_status"> display:none;</s:if>"/></div>
					<div style="float:left;width:29px;" id="evaluatePoint_show_${planWork2Id}"><s:if test="evaluatePoint>=0">${evaluatePoint}</s:if><s:else>&nbsp;</s:else></div>
					<div style="float:left;width:29px;display:none;" id="evaluatePoint_hide_${planWork2Id}"><input type='text' id="evaluatePoint_input_${planWork2Id}" value="${evaluatePoint}" onblur="updateRecord('${planWork2Id}','evaluatePoint',$(this).val());" onkeypress="onkeypressPoint(event,'${planWork2Id}','evaluatePoint',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" style="width:30px;<s:if test="7!=quarter_status"> display:none;</s:if>"/></div>
					<div style="float:left;width:29px;" id="finalPoint_show_${planWork2Id}"><s:if test="finalPoint>=0">${finalPoint}</s:if><s:else>&nbsp;</s:else></div>
					<div style="float:left;width:29px;display:none;" id="finalPoint_hide_${planWork2Id}"><input type='text' id="finalPoint_input_${planWork2Id}" value="${finalPoint}" onblur="updateRecord('${planWork2Id}','finalPoint',$(this).val());" onkeypress="onkeypressPoint(event,'${planWork2Id}','finalPoint',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" style="width:30px;<s:if test="8!=quarter_status"> display:none;</s:if>"/></div>
				</td>
			</s:if>
		</tr>
		<tr id="detail_${planWork2Id}" class="detailTr" style="display:none;" ondblclick="scheClick('${planWork2Id}');">
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
						<td align="left" nowrap="nowrap" style="width:70px; height:40px; background-color:#e4e7ec;">
							<div style="padding-left:4px;">开始时间:</div>
						</td>
						<td align="left" nowrap="nowrap" style="width:5px; height:30px; background-color:#e4e7ec; border-right:1px solid #dcdcde;"></td>
						<td align="left" nowrap="nowrap" style="width:1200px; padding-left:14px;">
							<s:date name="createdDate" format="MM-dd HH:mm"></s:date>
							&nbsp;&nbsp;创建人 ：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";") %>&nbsp;
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
										<input type="button" class="save10Btn btn_green" style="float:left; margin-left:2px; margin-top:1px; display:none; width:85px;" onclick="updateStatusCd('${planWork2Id}',10);" value="完成但曾过期"/>
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
													<input type="button" style="margin-left:2px;height:53px;" class="btn_green" onclick="saveMessage('${planWork2Id}');" value="留言"/>
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
		</tr>
		<script language="javascript">
		//加关注时候用
			temp_if_arr_ids_hasId = false;
			for(var i=0;i<arr_ids.length;i++){
				if("${planWork2Id}"==arr_ids[i]){
					temp_if_arr_ids_hasId = true;
					break;
				}
			}
			if(!temp_if_arr_ids_hasId){
				arr_ids.push("${planWork2Id}");
			}
			<s:if test="attentionMap[planWork2Id] == 'attention'">
				$("#attention_${planWork2Id}").show();
				$("#scheForm_${planWork2Id} input[name='isAttentioned']").val("attention");
			</s:if>
			<s:if test="!(attentionMap[planWork2Id] == 'attention')">
				$("#attention_cancel_${planWork2Id}").show();
			</s:if>
			<s:if test="attentionMapUnread[planWork2Id] == 'unread'">
				$("#attention_recordVersion_${planWork2Id}").html("${recordVersion}");
				$("#attention_unread_img_${planWork2Id}").css("display","inline");
			</s:if>
		</script>
		</s:iterator>
	</tbody>
</table>
<table cellpadding="0" cellspacing="0" border="0" width="100%" style="display:none;">
	<tr>
		<td align="center" id="td_page_source"><p:page/></td>
	</tr>
</table>
<script type="text/javascript">
//if(1==$("#if_search_all").val()){
	$("#td_page").html($("#td_page_source").html());
	$("#td_page_source").html("");
//}
</script>
<form action="${ctx}/plan/plan-work2!updateStatus.action" id="statusForm" method="post">
	<input type="hidden" id="status_id" name="id"/>
	<input type="hidden" name="userDeptCd" value="${userDeptCd}"/>
	<input type="hidden" id="status_cd" name="statusCd"/>
	<input type="hidden" id="status_version" name="recordVersion"/>
</form>
<div id="pop_bg" class="pop_bg" style="display:none;">
	<div style='height:200px'></div>
	<table width='100%'>
		<tr>
			<td align='center'>
				<img src='${ctx}/resources/images/common/loading.gif'/>
			</td>
		</tr>
	</table>
</div>
<script language="javascript">
	myUiid = "${myUiid}";
	$("#pop_bg").css("height",Number($(document).height()-20)+"px");
	if(""!=opened_entityid){
		scheClick(opened_entityid);
	}
	
	<security:authorize ifAnyGranted="A_PLAN_WORK2_VIEW">
		myRoles.push("A_PLAN_WORK2_VIEW");
	</security:authorize>
	<security:authorize ifAnyGranted="A_PLAN_WORK2_CENTER">
		myRoles.push("A_PLAN_WORK2_CENTER");
	</security:authorize>
	<security:authorize ifAnyGranted="A_PLAN_WORK2_PROJECT">
		myRoles.push("A_PLAN_WORK2_PROJECT");
	</security:authorize>
	<security:authorize ifAnyGranted="A_PLAN_WORK2_OFFICE">
		myRoles.push("A_PLAN_WORK2_OFFICE");
	</security:authorize>
	<security:authorize ifAnyGranted="A_PLAN_WORK2_VICE">
		myRoles.push("A_PLAN_WORK2_VICE");
	</security:authorize>
	<security:authorize ifAnyGranted="A_PLAN_WORK2_CEO">
		myRoles.push("A_PLAN_WORK2_CEO");
	</security:authorize>
	<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN">
		myRoles.push("A_PLAN_WORK2_ADMIN");
	</security:authorize>
	
	center_status = "${center_status}";
	quarter_status = "${quarter_status}";
	now_year = "${now_year}";
	now_month = "${now_month}";
	now_quarter = "${now_quarter}";
	refreshMyOper();	//控制操作按钮的显示
	if(1==if_in_weight && 9==quarter_status){
		$("#finalPointSumMsg").html("&nbsp;&nbsp;最终得分:${finalPointSum}");
	}else{
		$("#finalPointSumMsg").html("");
	}

	if(4==quarter_status && judgeIfHasSomeRole("A_PLAN_WORK2_CEO")){
		$("#pointTitleMsg1").show();
	}else{
		$("#pointTitleMsg1").hide();
	}
	if(5==quarter_status && (judgeIfHasSomeRole("A_PLAN_WORK2_CENTER")||judgeIfHasSomeRole("A_PLAN_WORK2_PROJECT"))){
		$("#pointTitleMsg2").show();
	}else if(6==quarter_status && (judgeIfHasSomeRole("A_PLAN_WORK2_VICE")||judgeIfHasSomeRole("A_PLAN_WORK2_PROJECT"))){
		$("#pointTitleMsg2").show();
	}else if(7==quarter_status && (judgeIfHasSomeRole("A_PLAN_WORK2_OFFICE")||judgeIfHasSomeRole("A_PLAN_WORK2_PROJECT"))){
		$("#pointTitleMsg2").show();
	}else if(8==quarter_status && judgeIfHasSomeRole("A_PLAN_WORK2_CEO")){
		$("#pointTitleMsg2").show();
	}else{
		$("#pointTitleMsg2").hide();
	}
	<s:if test="1==if_in_weight">
		$("#td_point_count").html("权重总分:${weightPointSum}&nbsp;&nbsp;&nbsp;&nbsp;自评总分:${selfPointSum}&nbsp;&nbsp;&nbsp;&nbsp;复评总分:${selfCheckPointSum}&nbsp;&nbsp;&nbsp;&nbsp;考评总分:${evaluatePointSum}&nbsp;&nbsp;&nbsp;&nbsp;最终总分:${finalPointSum}");
	</s:if>
	if_in_weight = "${if_in_weight}";
</script>
