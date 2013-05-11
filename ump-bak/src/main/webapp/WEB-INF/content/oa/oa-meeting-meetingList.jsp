<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 
	Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.Date"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
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
				<td
						<s:if test="oaMeetingAdmin == true">
							onclick="enableResponsiblePersonInput('${oaMeetingId}', event);"
						</s:if> title="点击管理负责人"
					>
					<span id="${oaMeetingId}responsiblePerson"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("responsiblePerson"),";") %></span>
					<span id="${oaMeetingId}responsiblePersonInput" style="display:none;">
						<textarea id="${oaMeetingId}responsiblePersonNames" style="width:100px;" rows="4" readonly="readonly"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("responsiblePerson"),";")%></textarea>
						<input type="hidden" id="${oaMeetingId}responsiblePersonCds" value="${responsiblePerson}">
					</span>
				</td>
				
				<td  
					<s:if test="oaMeetingAdmin == true">
						onclick="enableCoordinatePersonInput('${oaMeetingId}', event);"
					</s:if> title="点击管理配合人"
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
						<img id="${oaMeetingId}StatusFlg" src="${ctx}/images/plan/pic_confirm.gif" />
					</c:if>
					<c:if test="${status == '2'}">
						<img id="${oaMeetingId}StatusFlg" src="${ctx}/images/plan/pic_preFinish.gif" />
					</c:if>
					<c:if test="${status == '3'}">
						<img id="${oaMeetingId}StatusFlg" src="${ctx}/images/plan/pic_finish.gif" />
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
									<pre><strong><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>(<s:date name="createdDate" format="MM-dd HH:mm"/>):</strong><s:property value="content" escape="true"/><s:if test="oaMeetingAdmin == true">            <input type="button" id="${oaMeetingCommentId}comment" onclick="deleteComment('${oaMeetingCommentId}','${oaMeetingId}');" value="删除" /></s:if></pre>
								</li>
							</s:iterator>
						</ul>
					</div>

					<div style="float:left;">
						<textarea id="${oaMeetingId}commentArea" rows="3" style="width: 400px; overflow: auto;"></textarea>
					</div>
					<div class="func_icon_publish" style="float:left;" onclick="saveComment('${oaMeetingId}');">发表</div>
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
							<div class="func_icon" style="float: left; margin-left: 10px;" onclick="saveMeetingV5('${oaMeetingId}');">保存</div>
							<s:if test="status != 3">
								<div class="func_icon" style="float: left; margin-left: 4px;" onclick="updateMeeting('${oaMeetingId}', 'complete', '3',this);">
									完成
								</div>
							</s:if>
							
							<s:if test="status != 1">
								<div class="func_icon" style="float: left; margin-left: 4px;" onclick="updateMeeting('${oaMeetingId}', 'revert', '1',this);">
									恢复
								</div>
							</s:if>
							
							<s:if test="status != 3 && false">
								<div class="func_icon" style="float: left; margin-left: 4px;" onclick="remind('${oaMeetingId}');">
									提醒
								</div>
							</s:if>
							
							<s:if test="hiddenFlg == 0">
								<div class="func_icon" style="float: left; margin-left: 4px;" onclick="updateMeeting('${oaMeetingId}', 'hide', '1',this);">
									隐藏
								</div>
							</s:if>
							
							<s:if test="hiddenFlg == 1">
								<div class="func_icon" style="float: left; margin-left: 4px;" onclick="updateMeeting('${oaMeetingId}', 'show', '0',this);">
									显示
								</div>
							</s:if>
							
							<s:if test="isDeleted == 0">
								<div class="func_icon_red" style="float: left; margin-left: 4px;" onclick="updateMeeting('${oaMeetingId}', 'delete', '1',this);">
									删除
								</div>
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