<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/common/meta.jsp" %>
		<%@ include file="/common/global.jsp" %>
		<title>专项任务</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
		<link href="${ctx}/resources/css/common/TreePanel.css" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
		<link rel="stylesheet" href="${ctx}/css/desk/special-task.css" type="text/css" />
		
		<script language="javascript" src="${ctx}/js/jquery.js"></script>
		<script language="javascript" src="${ctx}/js/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
		<script type=text/javascript src="${ctx}/js/common.js"></script>
		<script type=text/javascript src="${ctx}/resources/js/common/TreePanel.js"></script>
		<script src="${ctx}/js/datePicker/WdatePicker.js" type="text/javascript"></script>
		<script type=text/javascript src="${ctx}/js/desk/special-task.js"></script>
		<script language="javascript" src="${ctx}/js/table.js"></script>
		<script type="text/javascript" src="${ctx}/js/xheditor/xheditor-zh-cn.js"></script>
		<script language="javascript" src="${ctx}/js/jqueryplugin/chilltip.js"></script>
	</head>

	<body>
	
		<div class="title_bar">
			
			<div class="title_bar_h">专项任务</div>
			<div class="succ_info">
				<span id="succInfoMsg"></span>
			</div>
			
			<div class="title_bar_ops">
				<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN,A_SPECTASK_CEOADMIN,A_SPECTASK_VICADMIN">
				<div class="split_vertial" style="float: left;">&nbsp;</div>
					<div class="title_bar_ops_add" onclick="triggerNewTaskInput();">
						新建专项任务
					</div>
				</security:authorize>
				<div class="split_vertial" style="float: left;">&nbsp;</div>
				<div class="title_bar_ops_search" onclick="triggerSearchPanel();">
					搜索
				</div>
				<div class="split_vertial" style="float: left;">&nbsp;</div>
				<div class="title_bar_ops_refresh" onclick="self.location='${ctx}/oa/special-task.action';">
					刷新
				</div>
				<div class="split_vertial" style="float: left;">&nbsp;</div>
				<div class="title_bar_ops_full" onclick="window.open('${ctx}/oa/oa-meeting.action?moduleCd=zx')">
					全屏
				</div>
			</div>
		</div>
	
		<div class="wrapper">
			<div id="searchPanel" class="search_panel">
				<s:form id="searchForm" action="special-task!search.action" method="post">
					<input type="hidden" name="page.pageNo" id="pagePageNo" value="${page.pageNo}" />
					<input type="hidden" name="totalPageSize" id="totalPageSize" value="${page.totalPages}" />
					<input type="hidden" name="orderBy" id="orderByParam" value="${orderBy}" />
					<input type="hidden" name="order" id="orderParam" value="${order}" />
					<input type="hidden" name="searchBySendPerson" id="searchBySendPersonParam" />
					<input type="hidden" name="searchByResPerson" id="searchByResPersonParam" />
					
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td width="80" align="right">
								负责人：
							</td>
							<td width="120" style="padding-left: 0;">
								<input name="searchByResPersonName" id="searchByResPersonName" style="width: 110px; cursor: pointer;" 
								onfocus="selectPerson('QUERY_RES');" />
							</td>
							<td width="80" align="right">
								配合人：
							</td>
							<td width="120" style="padding-left: 0;">
								<input name="searchBySendPersonName" id="searchBySendPersonName" style="width: 110px; cursor: pointer;"
									onfocus="selectPerson('QUERY_SEND');" />
							</td>
							<td width="80" align="right">
								状态：
							</td>
							<td width="100" style="padding-left: 0;">
								<select name="searchByStatus" id="searchByStatus" style="width: 85px;">
									<option value="" <c:if test="${searchByStatus == ''}">selected="selected"</c:if>>所有</option>
									<option value="1" <c:if test="${searchByStatus == '1'}">selected="selected"</c:if>>进行中</option>
									<option value="2" <c:if test="${searchByStatus == '2'}">selected="selected"</c:if>>预完成</option>
									<option value="3" <c:if test="${searchByStatus == '3'}">selected="selected"</c:if>>已完成</option>
								</select>
							</td>
							
							<td rowspan="2">
							<div class="func_icon" style="float: left;" onclick="searchTaskList();">
										搜索 
									</div>
									<div class="func_iconY" style="float: left; margin-left: 10px;" onclick="triggerSearchPanel();">
										隐藏
									</div>
									<s:url id="exportExl" action="special-task!exportExcel.action" namespace="/oa"  includeParams="none"  />
									<div href="${exportExl}" class="func_icon_red" style="float: left; margin-left: 10px;" onclick="exportResult($(this));">
										导出
									</div>
							</td>
						</tr>
						<tr>
							<td align="right">
								任务标题：
							</td>
							<td style="padding-left: 0;">
								<input name="searchBySubject" id="searchBySubject" style="width: 110px;" />
							</td>
							<td align="right">
								附件名称：
							</td>
							<td style="padding-left: 0;">
								<input name="searchByAttachName" id="searchByAttachName" style="width: 110px;" />
							</td>
							<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN,A_SPECTASK_CEOADMIN">
								<td align="right">
									是否显示：
								</td>
								<td style="padding-left: 0;">
									<select name="searchByHiddenFlg" id="searchByHiddenFlg" style="width:85px;">
										<option value="0" <c:if test="${searchByHiddenFlg == '0'}">selected="selected"</c:if>>显示</option>
										<option value="1" <c:if test="${searchByHiddenFlg == '1'}">selected="selected"</c:if>>隐藏</option>
									</select>
								</td>
							</security:authorize>
							
						</tr>
					</table>
				</s:form>
			</div>
			
		 	<div id="taskListDiv" >
				<%@ include file="special-task-taskList.jsp"%>
			</div>
		</div>
		
		<div>
	 		<div class="corner_bottom_left"></div>
	 		<div class="corner_bottom_right"></div>
	 		<div class="corner_bottom_center"></div>
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
			 
			 <input type="text" id="pageNo" style="height: 14px; width: 20px; text-align: center;"/>
			 <a href="javascript: jumpTo($('#mainPager #pageNo').val());">GO</a>
		</div>
	</body>
</html>
