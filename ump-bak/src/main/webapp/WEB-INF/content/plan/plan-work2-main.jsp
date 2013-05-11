<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
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
			<td id="td_planType_${planWork2Id}" onclick='scheClick("${planWork2Id}");toggleDetail(this,"${planWork2Id}");'>
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
					<select class="planType_class" id="planType_input_${planWork2Id}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="updateRecord('${planWork2Id}','planType',$(this).val());">
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
				<div id="content_show_${planWork2Id}" class="span_show ellipsisDiv_full pd-chill-tip" title="<p:code2name mapCodeName="mapContentTips" value="planWork2Id" />" style="word-break:break-all; word-wrap:break-word; white-space:normal;">${content}</div>
				<div id="content_hide_${planWork2Id}" class="span_hide" style="display:none;">
					<textarea id="content_input_${planWork2Id}" rows="5" style="width:100%;" onblur="updateRecord('${planWork2Id}','content',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;">${content}</textarea>
				</div>
			</td>
			<td onclick='scheClick("${planWork2Id}");' nowrap="nowrap">${myTask}</td>
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
				<s:elseif test="statusCd==10">完成但曾过期</s:elseif>
				<s:elseif test="statusCd==5"><img src="${ctx}/resources/images/common/status_del.gif" title="已删除"></s:elseif>
				<s:elseif test="statusCd==6"><img src="${ctx}/resources/images/common/status_hidden.gif" title="已隐藏"></s:elseif>
				<s:elseif test="statusCd==7">非本月</s:elseif>
				<s:elseif test="statusCd==8"><img src="${ctx}/resources/images/common/status_suspend.gif" title="已过期"></s:elseif>
				<s:elseif test="statusCd==9">非考核性过期</s:elseif>
			</td>
			<td onclick='scheClick("${planWork2Id}");' id="td_updateDate_${planWork2Id}" nowrap="nowrap" title="<s:date name='updatedDate'/>&#13更新人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("updator"),";") %>" onclick='scheClick("${planWork2Id}");'><s:date name="updatedDate" format="MM-dd"></s:date></td>
			<td>
				<span onclick="openAttachment('附件管理','${planWork2Id==null?entityTmpId_New:planWork2Id}'<s:if test='aOnlyCreator'>,'onlyCreator'</s:if>); return false;" >
					<s:if test="attachFlg == 1"><img src="${ctx}/resources/images/common/atta_y.gif" /></s:if>
					<s:else><img src="${ctx}/resources/images/common/atta.gif" /></s:else>
				</span>
			</td>
			<s:if test="4==quarter_status||5==quarter_status||6==quarter_status||7==quarter_status||8==quarter_status||9==quarter_status">
				<td id="td_weightPoint_${planWork2Id}" nowrap="nowrap" title="" onclick='toggleDetailPoint(this,"${planWork2Id}","weightPoint");'>
					<div id="weightPoint_show_${planWork2Id}" class="span_show"><s:if test="1==if_in_weight&&4==quarter_status">${bogusWeightPoint}</s:if><s:else>${weightPoint}</s:else></div>
					<div id="weightPoint_hide_${planWork2Id}" class="span_hide" style="display:none;">
						<input type="text" id="bogusWeightPoint_input_${planWork2Id}" value="${bogusWeightPoint}" onblur="updateRecord('${planWork2Id}','bogusWeightPoint',$(this).val());" onkeypress="onkeypressPoint(event,'${planWork2Id}','bogusWeightPoint',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" style="width:30px;"/>
						<input type="text" id="weightPoint_input_${planWork2Id}" value="${weightPoint}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" style="display:none"/>
					</div>
				</td>
			</s:if>
			<s:if test="5==quarter_status||6==quarter_status||7==quarter_status||8==quarter_status||9==quarter_status">
				<td id="td_somePoint_${planWork2Id}" nowrap="nowrap" title="${somePoint}" onclick='toggleDetailPoint(this,"${planWork2Id}","<s:if test="5==quarter_status">selfPoint</s:if><s:if test="6==quarter_status">selfCheckPoint</s:if><s:if test="7==quarter_status">evaluatePoint</s:if><s:if test="8==quarter_status">finalPoint</s:if>","${planType}");'>
					<div style="float:left;" id="somePoint_show_${planWork2Id}" class="span_show pd-chill-tip" title="<p:code2name mapCodeName="mapPointTips" value="planWork2Id" />">
						<div style="float:left;width:29px;" id="span_selfPoint_${planWork2Id}"><s:if test="selfPoint>0">${selfPoint}</s:if><s:else>&nbsp;</s:else></div>
						<div style="float:left;width:29px;" id="span_selfCheckPoint_${planWork2Id}"><s:if test="selfCheckPoint>0">${selfCheckPoint}</s:if><s:else>&nbsp;</s:else></div>
						<div style="float:left;width:29px;" id="span_evaluatePoint_${planWork2Id}"><s:if test="evaluatePoint>0">${evaluatePoint}</s:if><s:else>&nbsp;</s:else></div>
						<div style="float:left;width:29px;" id="span_finalPoint_${planWork2Id}"><s:if test="finalPoint>0">${finalPoint}</s:if><s:else>&nbsp;</s:else></div>
					</div>
					<div style="float:left; display:none;" id="somePoint_hide_${planWork2Id}" class="span_hide">
						<input type='text' id="selfPoint_input_${planWork2Id}" value="${selfPoint}" onblur="updateRecord('${planWork2Id}','selfPoint',$(this).val());" onkeypress="onkeypressPoint(event,'${planWork2Id}','selfPoint',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" style="width:30px;<s:if test="5!=quarter_status"> display:none;</s:if>"/>
						<input type='text' id="selfCheckPoint_input_${planWork2Id}" value="${selfCheckPoint}" onblur="updateRecord('${planWork2Id}','selfCheckPoint',$(this).val());" onkeypress="onkeypressPoint(event,'${planWork2Id}','selfCheckPoint',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" style="width:30px;<s:if test="6!=quarter_status"> display:none;</s:if>"/>
						<input type='text' id="evaluatePoint_input_${planWork2Id}" value="${evaluatePoint}" onblur="updateRecord('${planWork2Id}','evaluatePoint',$(this).val());" onkeypress="onkeypressPoint(event,'${planWork2Id}','evaluatePoint',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" style="width:30px;<s:if test="7!=quarter_status"> display:none;</s:if>"/>
						<input type='text' id="finalPoint_input_${planWork2Id}" value="${finalPoint}" onblur="updateRecord('${planWork2Id}','finalPoint',$(this).val());" onkeypress="onkeypressPoint(event,'${planWork2Id}','finalPoint',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" style="width:30px;<s:if test="8!=quarter_status"> display:none;</s:if>"/>
					</div>
				</td>
			</s:if>
		<script language="javascript">
			<s:if test="isAttentioned == 'attention'">
				$("#attention_${planWork2Id}").show();
			</s:if>
			<s:if test="!(isAttentioned == 'attention')">
				$("#attention_cancel_${planWork2Id}").show();
			</s:if>
		</script>
