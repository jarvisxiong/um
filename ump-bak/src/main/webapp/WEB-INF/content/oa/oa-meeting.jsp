<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="java.util.Date"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/global.jsp" %>
		<title>指令单跟踪</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/oa-meeting.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"/>
		
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
		<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/desk/oa-meeting.js"></script>
		<script type="text/javascript" src="${ctx}/js/table.js"></script>
		<script type="text/javascript" src="${ctx}/js/jqueryplugin/chilltip.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/oa/allAttention.js"></script>
	<script type="text/javascript">
		var _ctx = '${ctx}';
		_strModule = "${strModule}";	//模块
		$(function(){
			//$('#select_manager').ouSelect();
			//$('#select_personal').ouSelect();
			autoHeight();
		});
	</script>
	</head>

	<body style="margin:0px; padding:0px;">
	    <!--  
		<div id="module_tabs">
			<ul>
				<li>
					<div <s:if test="moduleCd == 'zc'">class="active"</s:if> onclick="$('div.active').removeClass('active'); $(this).addClass('active'); $('#oaMeetingContainer').show(); $('#specialTaskContainer').hide();">高管层</div>
				</li>
				<li>
					<div <s:if test="moduleCd == 'zx'">class="active"</s:if> onclick="$('div.active').removeClass('active'); $(this).addClass('active'); $('#oaMeetingContainer').hide(); $('#specialTaskContainer').show();">其他</div>
				</li>
			</ul>
		</div>
		-->
		<div id="oaMeetingContainer" <s:if test="moduleCd == 'zx'">style="display: none;"</s:if>>
			<div class="title_bar" style="padding-right:5px; padding-left:8px;">
				<div style="float:left;font-size: 18px; height:38px; line-height:38px; font-weight: bolder;">&nbsp;指令单跟踪</div>
				<div style="float:left; height:38px; line-height: 38px; text-align: center; margin-left: 20px; font-size: 12px;">
					<span style="color: red;" id="succInfoMsg"></span>
				</div>
				<div style="float:right; padding-top:2px;">
					<s:if test="oaMeetingAdmin == true || oaMeetingGenren==true">
						<s:if test="if_in_attention">
					   	<div class="split_vertial" style="float: left;">&nbsp;</div>
					   	<div style="float: left; cursor:pointer;" onclick="setAttentionRead('oaMeeting','chk_all');">
							设置为已读
						</div>
						</s:if>
					   	<s:if test="oaMeetingAdmin == true || oaMeetingGenren==true">
					   	<input type="button" class="btn_blue" onclick="exportExecOMeeting(0);" value="导出明细"/>
					   	<input type="button" class="btn_blue" style="display:none;" onclick="exportExecOMeeting(1);" value="导出明细"/>
					   
					   	</s:if>
					   	<s:if test="oaMeetingAdmin == true">
					   		<input type="button" class="btn_add3" onclick="addNewSummary();" value="新建"/>
						</s:if>
					</s:if>
					<input type="button"  class="btn_green" onclick="showSearch();" value="搜索 "/>
					<input type="button"  class="btn_green" onclick="searchMeetingList();" value="刷新 "/>
					<input type="button"  class="btn_green" onclick="window.open('${ctx}/oa/oa-meeting.action?moduleCd=zc');" value="全屏 "/>
				</div>
			</div>
			
			<div class="wrapper">
				<form name="searchForm" id="searchForm" action="oa-meeting!meetingList.action" method="post">
					<input type="hidden" name="page.pageNo" id="pagePageNo" value="${page.pageNo}" />
					<input type="hidden" name="totalPageSize" id="totalPageSize" value="${page.totalPages}" />
					<input type="hidden" name="orderBy" id="orderByParam" value="${orderBy}" />
					<input type="hidden" name="order" id="orderParam" value="${order}" />
					<input type="hidden" name="strModule" id="strModule" value="${strModule}" />
					<input type="hidden" name="myTask" id="myTask" value="${myTask}" />
					
					<div id="searchPanel" class="search_panel">
						<table cellpadding="0" cellspacing="0">
							<tr style="height: 30px;">
								<td width="300">
									录入时间:
									<s:textfield name="filter_GED_createdDate" onfocus="WdatePicker()" cssStyle="width:85px;"></s:textfield>&nbsp;至&nbsp;
									<s:textfield name="filter_LED_createdDate" onfocus="WdatePicker()" cssStyle="width:85px;"></s:textfield>
								</td>
								<td>
								         决议编号:
									<s:textfield name="filter_LIKES_waterNumber" cssStyle="width:80px;"></s:textfield>
								</td>
								
								<td>
									<s:if test="oaMeetingAdmin == true || currentUiid == 'lihang' || currentUiid == 'cuilin'">
										是否显示:
										<select name="filter_EQS_hiddenFlg" style="width:85px;">
											<option value="0" <c:if test="${filter_EQS_hiddenFlg == '0'}">selected="selected"</c:if>>显示</option>
											<option value="1" <c:if test="${filter_EQS_hiddenFlg == '1'}">selected="selected"</c:if>>隐藏</option>
										</select>
									</s:if>
								</td>
								<td rowspan="2">
									<div class="buttonStyle" onmousemove="$(this).addClass('buttonStyle_hover');" onmouseout="$(this).removeClass('buttonStyle_hover');" style="margin-left:5px; margin-right: 10px; float: left;" onclick="searchMeetingList();">搜索</div>
									<div class="buttonStyleY" onmousemove="$(this).addClass('buttonStyleY_hover');" onmouseout="$(this).removeClass('buttonStyleY_hover');" style="float: left;" onclick="$('#searchPanel').hide();$('#btnExportExec1').hide();$('#btnExportExec').show();">隐藏</div>
								</td>
							</tr>
							<tr style="height: 30px;">
								<td>
									目标时间:
									<s:textfield name="filter_GED_targetDate" onfocus="WdatePicker()" cssStyle="width:85px;"></s:textfield>&nbsp;至&nbsp;
									<s:textfield name="filter_LED_targetDate" onfocus="WdatePicker()" cssStyle="width:85px;"></s:textfield>
								</td>
								<td>
									状态:&nbsp;&nbsp;&nbsp;&nbsp;
									<select name="filter_EQS_status" style="width: 80px;">
										<option value="" <c:if test="${filter_EQS_status == ''}">selected="selected"</c:if>>所有</option>
										<option value="1" <c:if test="${filter_EQS_status == '1'}">selected="selected"</c:if>>进行中</option>
										<option value="2" <c:if test="${filter_EQS_status == '2'}">selected="selected"</c:if>>预完成</option>
										<option value="3" <c:if test="${filter_EQS_status == '3'}">selected="selected"</c:if>>已完成</option>
									</select>
								</td>
								
							</tr>
						</table>
					</div>
				</form>
			 	<div id="meetingListDiv" >
					<table class="meeting_list">
	<thead>
		<tr class="header">
			<th width="15px"  style="background-image: none;"></th>
			<th width="115px">编号</th>
			<th>事项</th>
			<th width="100px" onclick="order('responsiblePerson');" style="cursor: pointer;" title="点击按负责人排序">
				负责人
				<s:if test="orderBy == 'responsiblePerson'">
					<s:if test="order == 'asc'"><img src="${ctx}/images/up.gif" /></s:if>
					<s:if test="order == 'desc'"><img src="${ctx}/images/down.gif" /></s:if>
				</s:if>
			</th>
			<th width="100px" onclick="order('coordinatePerson');" style="cursor: pointer;" title="点击按配合人排序">
				配合人
				<s:if test="orderBy == 'coordinatePerson'">
					<s:if test="order == 'asc'"><img src="${ctx}/images/up.gif" /></s:if>
					<s:if test="order == 'desc'"><img src="${ctx}/images/down.gif" /></s:if>
				</s:if>
			</th>
			<th width="50px">
				开始
			</th>
			<th width="50px" onclick="order('targetDate');" style="cursor: pointer;" title="点击按目标时间排序">
				目标
				<s:if test="orderBy == 'targetDate'">
					<s:if test="order == 'asc'"><img src="${ctx}/images/up.gif" /></s:if>
					<s:if test="order == 'desc'"><img src="${ctx}/images/down.gif" /></s:if>
				</s:if>
			</th>
			<th width="50px" onclick="order('updatedDate');" style="cursor: pointer;" title="点击按更新时间排序">
				更新
				<s:if test="orderBy == 'updatedDate'">
					<s:if test="order == 'asc'"><img src="${ctx}/images/up.gif" /></s:if>
					<s:if test="order == 'desc'"><img src="${ctx}/images/down.gif" /></s:if>
				</s:if>
			</th>
			<th width="40">附件</th>
			<th width="30">状态</th>
		</tr>
	</thead>
	
	<tbody>
		<s:form id="inputForm" action="oa-meeting!input.action" method="post">
			<s:hidden id="entityTempId" name="entityTempId" />
			<input type="hidden" name="attendeeIds" id="attendeeIds" value=""/>
			<input type="hidden" name="strModule" id="strModule" value="${strModule}"/>
			<tr class="add_new">
				<td colspan="2" align="right" valign="top">
					<span id="inputWaterNum" style="display:none;"></span>
					<input type="text" style="width:100px;" name="waterNumber" id="waterNumberInput" value="${inputWaterNum}"/>
				</td>
				
				<td align="center">
					<textarea id="addNew_Business" name="business" rows="4" style="overflow: auto; width: 90%;"></textarea>
				</td>
			
				<td>
					<textarea id="new_responsiblePersonNames" rows="4" style="overflow:hidden; width: 100px;  cursor: pointer;" readonly="readonly"></textarea>
					<input id="new_responsiblePersonCds" name="responsiblePerson" type="hidden"/>
				</td>
				
				<td>
					<textarea id="new_coordinatePersonNames" rows="4" style="overflow:hidden; width: 100px;  cursor: pointer;" readonly="readonly"></textarea>
					<input id="new_coordinatePersonCds" name="coordinatePerson" type="hidden"/>
				</td>
				
				<td>
					<%=DateOperator.formatDate(new Date(), "MM-dd") %>
				</td>
				<td align="center">
					<s:textfield id="targetDate" name="targetDate" onfocus="WdatePicker()" cssStyle="width: 35px; font-size: 12px;" />
				</td>
				<td align="center">
					<%=DateOperator.formatDate(new Date(), "MM-dd") %>
				</td>
				<td colspan="2" align="center">
					<a href="javascript: attachManage('${entityTempId}', event);"><img src="${ctx}/resources/images/common/atta.gif" />&nbsp;添加附件</a>
				</td>
			</tr>
			
			<tr class="add_new">
				<td colspan="2" align="center" valign="top">
					留言：
				</td>
				<td align="center">
					<textarea name="comment" rows="2" style="overflow: auto; width: 90%;"></textarea>
				</td>
				<td colspan="1" align="right" valign="top">
					可见用户：
				</td>
				<td colspan="3" align="center">
					<textarea id="new_visibleToUsersNames" rows="2" style="overflow:hidden; width: 100px;  cursor: pointer;"></textarea>
					<input id="new_visibleToUsersCds" name="visibleToUsers" type="hidden"/>
				</td>
				<td colspan="3" align="center">
					<div class="buttonStyle" onmousemove="$(this).addClass('buttonStyle_hover');" onmouseout="$(this).removeClass('buttonStyle_hover');" style="float: left;margin-right: 10px;" onclick="submitMeeting();">创建</div>
					<div class="buttonStyle" onmousemove="$(this).addClass('buttonStyle_hover');" onmouseout="$(this).removeClass('buttonStyle_hover');" style="float: left;margin-right: 10px;" onclick="cancelInput();">取消</div>
				</td>
			</tr>
		</s:form>
		
		<s:iterator value="page.result">
			<tr class="item" id="${oaMeetingId}item" myid="${oaMeetingId}">
				<td align="center" title="点击打开会议详情">
					<div style="display:inline;"><input style="display:none;" checked="true" type="checkbox" id="chk_all" value="${oaMeetingId}" recordVersion="${recordVersion}"></input></div>
					<img class="down_arrow" src="${ctx}/images/plan/right_grey.gif" />
					<img class="up_arrow" src="${ctx}/images/plan/down_blue.gif" />
				</td>
				
				<td <s:if test="oaMeetingAdmin == true">
						onclick="enableWaterNumInput('${oaMeetingId}', event);" 
						title="点击编辑编号"
					</s:if>>
					<span id="${oaMeetingId}waterNum"><s:property value='waterNumber'/></span>
					<input type="text" id="${oaMeetingId}waterNumInput" style="width:100px; display:none;" value="<s:property value='waterNumber'/>" onblur="updateWaterNumber('${oaMeetingId}');">
				</td>
				
				<td
					<s:if test="oaMeetingAdmin == true">
						onclick="enableBusinessInput('${oaMeetingId}', event);" 
						title="点击编辑事项"
					</s:if>
				>
					<div class="splitWord  pd-chill-tip" style="width: 95%;" id="${oaMeetingId}business" title="<p:code2name mapCodeName="mapMeetingTips" value="oaMeetingId" />"><s:property value="business"/></div>
					<textarea id="${oaMeetingId}businessInput" rows="4" style="overflow: auto; width: 98%; display: none;" 
						onblur="updateBusiness('${oaMeetingId}');"><s:property value="business"/></textarea>
				</td> 
				<td <s:if test="oaMeetingAdmin == true">
						onclick="enableResponsiblePersonInput('${oaMeetingId}', event);" title="点击管理负责人"
					</s:if>
					<s:else> title="<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("responsiblePerson"),";")%>"</s:else>
				>
					<span id="${oaMeetingId}responsiblePerson"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("responsiblePerson"),";")%></span>
					<span id="${oaMeetingId}responsiblePersonInput" style="display:none;">
						<textarea id="${oaMeetingId}responsiblePersonNames" style="width:100px;" rows="4" readonly="readonly"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("responsiblePerson"),";")%></textarea>
						<input type="hidden" id="${oaMeetingId}responsiblePersonCds" value="${responsiblePerson}">
					</span>
				</td>
				
				<td <s:if test="oaMeetingAdmin == true">
						onclick="enableCoordinatePersonInput('${oaMeetingId}', event);" title="点击管理配合人"
					</s:if>
					<s:else> title="<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("coordinatePerson"),";")%>"</s:else>
				>
					<span id="${oaMeetingId}coordinatePerson"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("coordinatePerson"),";") %></span>
					<span id="${oaMeetingId}coordinatePersonInput" style="display:none;">
						<textarea id="${oaMeetingId}coordinatePersonNames" style="width:100px;" rows="4" readonly="readonly"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("coordinatePerson"),";")%></textarea>
						<input type="hidden" id="${oaMeetingId}coordinatePersonCds" value="${coordinatePerson}">
					</span>
				</td>
				
				<td align="center" title="开始时间">
					<s:date name="createdDate" format="MM-dd" />
				</td>
				
				<td align="center"
					<security:authorize ifAnyGranted="A_OAMEET_ADMIN,A_GENREN_ADMIN">
						onclick="enableTargDateInput('${oaMeetingId}', event);"
						title="点击更改目标时间" 
					</security:authorize>
				>
					<c:set var="curDate"><%=DateOperator.formatDate(new Date(), "yyyy-MM-dd") %></c:set>
					<c:set var="tarDate"><s:date name="targetDate" format="yyyy-MM-dd" /></c:set>
					<span id="${oaMeetingId}targDate" 
						<c:if test="${tarDate < curDate}">style="color: red;"</c:if>
						<c:if test="${tarDate >= curDate}">style="color: blue;"</c:if> title="<s:date name="targetDate" format="yyyy-MM-dd" />">
						<s:date name="targetDate" format="MM-dd" />
					</span>
					
					<security:authorize ifAnyGranted="A_OAMEET_ADMIN">
						<input type="text" 
							onclick="WdatePicker();"
							id="${oaMeetingId}targetDateInput" 
							name="${oaMeetingId}targetDateInput" 
							style="width: 37px; font-size: 12px; display: none;" />
					</security:authorize>
				</td>
				
				<td align="center" title="点击打开会议详情">
					<span id="${oaMeetingId}updatedDateSpan" title="<s:date name="updatedDate" format="yyyy-MM-dd" />"><s:date name="updatedDate" format="MM-dd" /></span>
				</td>
				
				<td align="center" onclick="attachManage('${oaMeetingId}', event);" title="查看附件">
				   <span id="${oaMeetingId}fileSpan">
				    <c:if test="${fileFlg == '1'}">
				    <img src="${ctx}/resources/images/common/atta_y.gif" />
				    </c:if>
				    <c:if test="${fileFlg == '0' || fileFlg == null || fileFlg == ''}">
				    <img src="${ctx}/resources/images/common/atta.gif" />
				    </c:if>
				   </span>
				</td>
				
				<td id="${oaMeetingId}StatusTd" title="<p:code2name mapCodeName="mapStatus" value="status"/>">
					<c:if test="${status == '1'}">
						<img id="${oaMeetingId}StatusFlg" src="${ctx}/resources/images/common/status_confirm.gif" />
					</c:if>
					<c:if test="${status == '2'}">
						<img id="${oaMeetingId}StatusFlg" src="${ctx}/resources/images/common/status_prefinish.gif" />
					</c:if>
					<c:if test="${status == '3'}">
						<img id="${oaMeetingId}StatusFlg" src="${ctx}/resources/images/common/status_finish.gif" />
					</c:if>
				</td>
			</tr>
			
			<tr class="detail" id="${oaMeetingId}_detalTr">
				<td colspan="2" align="center">
					留言：
				</td>
				
				<td style="padding-bottom: 10px;" colspan="2">
					<div id="${oaMeetingId}commentDiv">
						<ul class="commentList">
							<s:iterator value="oaMeetingComments">
								<li>
									<div style="float: left;"><strong><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>(<s:date name="createdDate" format="MM-dd HH:mm"/>):</strong><s:property value="content" escape="true"/></div>
								<div style="float: left;">	<s:if test="oaMeetingAdmin == true">   
								 <input type="button" class="button_org" onclick="deleteComment('${oaMeetingCommentId}','${oaMeetingId}');" value="删除"/>
									  </s:if></div><br></br>
								</li>
							</s:iterator>
						</ul>
					</div>

					<div style="float:left;">
						<textarea id="${oaMeetingId}commentArea" rows="3" style="width:300px; height:53px;"></textarea>
					</div>
					<div style="float:left;">
					  <input type="button" class="button_blue" style="height:53px;"  onclick="saveComment('${oaMeetingId}');" value="发表"/>
					</div>
				</td>
				<s:if test="oaMeetingAdmin == true">
				<td colspan="2" align="right" valign="bottom">
					可见用户:
					<textarea id="${oaMeetingId}visibleToUsersNames" rows="2" style="width:80px; overflow:hidden; cursor: pointer;" readonly="readonly"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("visibleToUsers"),";")%></textarea>
					<input id="${oaMeetingId}visibleToUsersCds" type="hidden" value="${visibleToUsers}"/>
					<div style="height:10px;">&nbsp;</div>
				</td>
				</s:if>
				<td <s:if test="oaMeetingAdmin == true">colspan="4"</s:if><s:else>colspan="6"</s:else> align="center" valign="bottom">
					<div style="height: 45px;">
						<%-- 负责人 
						<s:if test="status == 1 && (responsiblePerson.indexOf(currentUiid) != -1)">
							<div class="func_icon" style="float: left; margin-left: 10px;" onclick="saveMeeting('${oaMeetingId}');">保存</div>
							<div class="func_icon" style="float: left; margin-left: 10px;" onclick="updateMeeting('${oaMeetingId}', 'preComplete', '2',this);">
								预完成
							</div>
						</s:if>--%>
						
						<s:if test="oaMeetingAdmin == true">
						   <input type="button" class="button_blue" onclick="saveMeetingV5('${oaMeetingId}');" value="保存"/>
							<s:if test="status != 3">
							 <input type="button" class="button_blue" onclick="updateMeeting('${oaMeetingId}', 'complete', '3',this);" value="完成"/>
							</s:if>
							
							<s:if test="status != 1">
							   <input type="button" class="button_blue" onclick="updateMeeting('${oaMeetingId}', 'revert', '1',this);" value="恢复"/>
							</s:if>
							<s:if test="status != 3 && false">
							   <input type="button" class="button_blue" onclick="remind('${oaMeetingId}');" value="提醒"/>
							</s:if>
							<s:if test="hiddenFlg == 0">
							   <input type="button" class="button_green" onclick="updateMeeting('${oaMeetingId}', 'hide', '1',this);" value="隐藏"/>
								
							</s:if>
							
							<s:if test="hiddenFlg == 1">
							  <input type="button" class="button_blue" onclick="updateMeeting('${oaMeetingId}', 'show', '0',this);" value="显示"/>
							</s:if>
							<s:if test="isDeleted == 0">
							   <input type="button" class="button_org" onclick="updateMeeting('${oaMeetingId}', 'delete', '1',this);" value="删除"/>
							</s:if>
						</s:if>
					</div>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>

<div style="display: none;" id="hiddenPager">
	共有&nbsp;${page.totalCount}&nbsp;条记录&nbsp;&nbsp;&nbsp;&nbsp;
	 当前第 ${page.pageNo}/${page.totalPages}&nbsp;页
	 <s:if test="page.hasPre">
		<img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_up.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_up_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_up.gif');" onclick="jumpTo('${page.prePage}');"/>
	 </s:if>
	 <s:else>
	 	<img align="absmiddle" src="${ctx}/images/desk2/page_up_grey.gif" />
	 </s:else>
	 
	 <s:if test="page.hasNext">
		<img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_down.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_down_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_down.gif');" onclick="jumpTo('${page.nextPage}');"/>
	 </s:if>
	 <s:else>
	 	<img align="absmiddle" src="${ctx}/images/desk2/page_down_grey.gif" />
	 </s:else>
	 
	 <input type="text" id="pageNo" style="height: 15px; width: 20px; text-align: center;"/> <a href="javascript: jumpTo($('#pageNo').val());">GO</a>
</div>

				</div>
			</div>
			
		
			
			<div class="pager_style" id="mainPager">
				共有&nbsp;${page.totalCount}&nbsp;条记录&nbsp;&nbsp;&nbsp;&nbsp;
				 当前第 ${page.pageNo}/${page.totalPages}&nbsp;页
				 <s:if test="page.hasPre">
					<img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_up.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_up_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_up.gif');" onclick="jumpTo('${page.prePage}');"/>
				 </s:if>
				 <s:else>
				 	<img align="absmiddle" src="${ctx}/images/desk2/page_up_grey.gif" />
				 </s:else>
				 
				 <s:if test="page.hasNext">
					<img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_down.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_down_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_down.gif');" onclick="jumpTo('${page.nextPage}');"/>
				 </s:if>
				 <s:else>
				 	<img align="absmiddle" src="${ctx}/images/desk2/page_down_grey.gif" />
				 </s:else>
				 
				 <input type="text" id="pageNo" style="height: 15px; width: 20px; text-align: center;"/> <a href="javascript: jumpTo($('#mainPager #pageNo').val());">GO</a>
			</div>
		</div>
		<!-- 
		<div id="specialTaskContainer" <s:if test="moduleCd == 'zc'">style="display: none; overflow: hidden;"</s:if><s:else>style="overflow: hidden;"</s:else>>
			<iframe frameborder="no" src="${ctx}/oa/special-task.action" width="100%" height="100%" 
				style="border: 0px; width: 100%; overflow: hidden; background-color: #fff; padding: 0; margin: 0;"></iframe>
		</div>
		 -->
		 
<script language="javascript">
$("tr.item").click(function() {
	try{
	var detail = $(this).next();
	if (detail.css("display") == "none") {
		if (openItem != null) {
			openItem.next().hide();
			openItem.removeClass("selected");
			openItem.find("div").addClass("splitWord");
			openItem.find("td").css("color", "");
			switchArrow(openItem, "down");
		}
		$(this).addClass("selected");
		$(this).find("td").css("color", "#40a3de");
		$(this).find("div").removeClass("splitWord");
		detail.show();
		switchArrow($(this), "up");
		openItem = $(this);
	} else {
		$(this).removeClass("selected");
		$(this).find("div").addClass("splitWord");
		$(this).find("td").css("color", "");
		switchArrow($(this), "down");
		detail.hide();
		openItem.next().hide();
		openItem = null;
	}
	}catch(e){}
});
$("tr.item").each(function(){
	if($(this).attr("myid")=="${opened_entityid}"){
		$(this).click();
	}
});
<s:iterator value="page.result">
	<s:if test="oaMeetingAdmin == true">
		try{$('#${oaMeetingId}responsiblePersonNames').ouSelect({showGroupFlg : true});}catch(e){}
		try{$('#${oaMeetingId}coordinatePersonNames').ouSelect({showGroupFlg : true});}catch(e){}
		try{$('#${oaMeetingId}visibleToUsersNames').ouSelect({showGroupFlg : true});}catch(e){}
	</s:if>
</s:iterator>
<s:if test="id != ''">
	$("#${id}item").trigger("click");
</s:if>
	try{$('#new_responsiblePersonNames').ouSelect({showGroupFlg : true});}catch(e){}
	try{$('#new_coordinatePersonNames').ouSelect({showGroupFlg : true});}catch(e){}
	try{$('#new_visibleToUsersNames').ouSelect({showGroupFlg : true});}catch(e){}
</script>
	</body>
</html>