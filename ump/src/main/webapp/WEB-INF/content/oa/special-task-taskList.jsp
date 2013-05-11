<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 
	Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="java.util.Date"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<table class="task_list" cellpadding="0" cellspacing="0">
	<thead>
		<tr class="header">
			<th width="20"></th>
			<th width="80">编号</th>
			<th>标题</th>
			<th width="120" onclick="order('responsiblePerson');" style="cursor: pointer;" title="点击按负责人排序">
				负责人
				<s:if test="orderBy == 'responsiblePerson'">
					<s:if test="order == 'asc'"><img src="${ctx}/images/up.gif" /></s:if>
					<s:if test="order == 'desc'"><img src="${ctx}/images/down.gif" /></s:if>
				</s:if>
			</th>
			<th width="120">配合人</th>
			<th width="60" onclick="order('targetDate');" style="cursor: pointer;" title="点击按目标时间排序">
				目标
				<s:if test="orderBy == 'targetDate'">
					<s:if test="order == 'asc'"><img src="${ctx}/images/up.gif" /></s:if>
					<s:if test="order == 'desc'"><img src="${ctx}/images/down.gif" /></s:if>
				</s:if>
			</th>
			<th width="60" onclick="order('updatedDate');" style="cursor: pointer;" title="点击按更新时间排序">
				更新
				<s:if test="orderBy == 'updatedDate'">
					<s:if test="order == 'asc'"><img src="${ctx}/images/up.gif" /></s:if>
					<s:if test="order == 'desc'"><img src="${ctx}/images/down.gif" /></s:if>
				</s:if>
			</th>
			<th width="60">附件</th>
			<th width="60" style="background-image: none;">状态</th>
		</tr>
	</thead>
	
	<tbody>
		<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN,A_SPECTASK_CEOADMIN,A_SPECTASK_VICADMIN">
			<s:form id="inputForm" action="special-task!input.action" method="post">
				<s:hidden id="entityTempId" name="entityTempId" />
				
				<security:authorize ifAnyGranted="A_SPECTASK_CEOADMIN,A_SPECTASK_VICADMIN">
					<input id="sendPersonIds" name="sendPerson" type="hidden" value="${currentUiid}" />
				</security:authorize>
				<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN">
					<input id="sendPersonIds" name="sendPerson" type="hidden" />
				</security:authorize>
				<input id="resPersonIds" name="responsiblePerson" type="hidden" />
				<input id="corPersonIds" name="coordinatePerson" type="hidden"/>
				
				
				<tr class="add_new">
					<td>&nbsp;</td>
					<td align="center" valign="middle">
						<input type="text" name="waterNum" style="width: 75px;" />
					</td>
					
					<td align="left">
						<textarea name="subject" rows="4" style="overflow: auto; width: 95%;"></textarea>
					</td>
				
					<td>
						<textarea id="resPersonNames" onfocus="selectPerson('RES');" 
							rows="4" style="overflow: auto; width: 90%; cursor: pointer;"></textarea>
					</td>
					
					<td>
						<textarea id="corPersonNames" onfocus="selectPerson('COR');" rows="4" style="overflow: auto; width: 90%; cursor: pointer;"></textarea>
					</td>
					
					<td align="center">
						<s:textfield id="targetDate" name="targetDate" onfocus="WdatePicker()" cssStyle="width: 35px; font-size: 12px;" />
					</td>
					<td align="center">
						<%=DateOperator.formatDate(new Date(), "MM-dd") %>
					</td>
					<td colspan="2" align="center">
						<a onfocus="this.blur();" href="#" onclick="attachManage('${entityTempId}', event); return false;"><img src="${ctx}/pics/email/atta.gif" />&nbsp;添加附件</a>
					</td>
				</tr>
				
				<tr class="add_new">
					<td colspan="2" align="center" valign="middle">
						详细内容：
					</td>
					<td align="left" colspan="3">
						<div style="height: 25px; line-height: 25px;">开始时间：<%=DateOperator.formatDate(new Date(), "MM-dd") %></div>
						<textarea name="detail" rows="8" style="overflow: auto; width: 99%;"></textarea>
					</td>
					<td colspan="4" align="center">&nbsp;</td>
				</tr>
				
				<tr class="add_new">
					<td colspan="2" align="center" valign="middle">
						留言：
					</td>
					<td align="left" colspan="3">
						<textarea name="comment" rows="2" style="overflow: auto; width: 99%;"></textarea>
					</td>
					<td colspan="4">
						<div class="buttonStyle" style="float: left;margin-left: 20px;" onclick="createNewTask();">创建</div>
						<div class="buttonStyle" style="float: left;margin-left: 10px;" onclick="triggerNewTaskInput();">取消</div>
					</td>
				</tr>
			</s:form>
		</security:authorize>
		
		<s:iterator value="page.result">
			<tr class="item" id="${specialTaskId}item">
				<td align="center" title="点击打开任务详情">
					<img class="down_arrow" src="${ctx}/images/plan/right_grey.gif" />
					<img class="up_arrow" src="${ctx}/images/plan/down_blue.gif" />
				</td>
				
				<td 
					<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN,A_SPECTASK_CEOADMIN,A_SPECTASK_VICADMIN">
						onclick="enableWatNumEdit('${specialTaskId}', event);"
						title="点击编辑任务编号"
					</security:authorize> 
					>
					<span id="${specialTaskId}waterNum" title="<s:property value="waterNum"/>"><s:property value="waterNum"/></span>
					<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN,A_SPECTASK_CEOADMIN,A_SPECTASK_VICADMIN">
						<input value="${waterNum}" type="text" id="${specialTaskId}waterNumInput"  name="waterNum" style="width: 75px; display: none;" 
							onblur="updateWaterNum('${specialTaskId}', this.value);" />
					</security:authorize>						
				</td>
				
				<td
					<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN,A_SPECTASK_CEOADMIN,A_SPECTASK_VICADMIN">
						onclick="enableSubEdit('${specialTaskId}', event);" 
						title="点击编辑事项"
					</security:authorize>
				>
					<div style="width: 95%;" class="splitWord pd-chill-tip" id="${specialTaskId}subject" title="<s:property value="subject" />"><s:property value="subject"/></div>
					<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN,A_SPECTASK_CEOADMIN,A_SPECTASK_VICADMIN">
						<textarea id="${specialTaskId}subjectInput" rows="4" style="overflow: auto; width: 98%; display: none;" 
							onblur="updateSubject('${specialTaskId}');"><s:property value="subject"/></textarea>
					</security:authorize>
				</td>
				
				<td
					<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN,A_SPECTASK_CEOADMIN,A_SPECTASK_VICADMIN">
						onclick="managePerson('${specialTaskId}', 'EXIST_RES', event);"
						title="点击管理负责人" 
					</security:authorize>
				>
					<div style="width: 90%;" class="splitWord pd-chill-tip" id="${specialTaskId}resPerson" title="<p:code2name mapCodeName="mapResPersonNames" value="specialTaskId" />"><p:code2name mapCodeName="mapResPersonNames" value="specialTaskId" /></div>
				</td>
				
				<td 
					<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN,A_SPECTASK_CEOADMIN,A_SPECTASK_VICADMIN">
						onclick="managePerson('${specialTaskId}', 'EXIST_COR', event);"
						title="点击管理配合人"
					</security:authorize>
				>
					<div style="width: 95%;" class="splitWord pd-chill-tip" id="${specialTaskId}corPerson" title="<p:code2name mapCodeName="mapCorPersonNames" value="specialTaskId" />"><p:code2name mapCodeName="mapCorPersonNames" value="specialTaskId" /></div>
				</td>
								
				<td align="center"
					<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN,A_SPECTASK_CEOADMIN,A_SPECTASK_VICADMIN">
						onclick="enableTargDateInput('${specialTaskId}', event);"
						title="点击更改目标时间" 
					</security:authorize>
				>
					<c:set var="curDate"><%=DateOperator.formatDate(new Date(), "yyyy-MM-dd") %></c:set>
					<c:set var="tarDate"><s:date name="targetDate" format="yyyy-MM-dd" /></c:set>
					<span id="${specialTaskId}targDate" 
						<c:if test="${tarDate < curDate}">style="color: red;"</c:if>
						<c:if test="${tarDate >= curDate}">style="color: blue;"</c:if> title="<s:date name="targetDate" format="yyyy-MM-dd" />">
						<s:date name="targetDate" format="MM-dd" />
					</span>
					<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN,A_SPECTASK_CEOADMIN,A_SPECTASK_VICADMIN">
						<input type="text" 
							onblur="saveTargDate('${specialTaskId}');" 
							onclick="WdatePicker();"
							id="${specialTaskId}targetDateInput" 
							name="${specialTaskId}targetDateInput" 
							style="width: 37px; font-size: 12px; display: none;" />
					</security:authorize>
				</td>
				
				<td align="center" title="点击打开会议详情">
					<span id="${specialTaskId}updatedDateSpan" title="<s:date name="updatedDate" format="yyyy-MM-dd" />"><s:date name="updatedDate" format="MM-dd" /></span>
				</td>
				
				<td align="center" onclick="attachManage('${specialTaskId}', event);" title="查看附件">
					<img src="${ctx}/pics/email/atta.gif" />
				</td>
				
				<td id="${specialTaskId}StatusTd" title="<p:code2name mapCodeName="mapStatus" value="status"/>">
					<c:if test="${status == '1'}">
						<img id="${specialTaskId}StatusFlg" src="${ctx}/images/plan/pic_confirm.gif" />
					</c:if>
					<c:if test="${status == '2'}">
						<img id="${specialTaskId}StatusFlg" src="${ctx}/images/plan/pic_preFinish.gif" />
					</c:if>
					<c:if test="${status == '3'}">
						<img id="${specialTaskId}StatusFlg" src="${ctx}/images/plan/pic_finish.gif" />
					</c:if>
				</td>
			</tr>
			<tr class="detail" style="height: 40px; border-bottom: 1px dashed #E3E3E3;">
				<td colspan="2" align="center" style="padding-top: 50px;">
					详细内容：
				</td>
				<td style="padding-bottom: 10px; padding-top: 10px; cursor: pointer;" colspan="3"
					<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN,A_SPECTASK_CEOADMIN,A_SPECTASK_VICADMIN">
						onclick="enableDetailEdit('${specialTaskId}', event);" 
						title="点击编辑详细信息"
					</security:authorize>
				>
					<div style="line-height: 30px; margin-bottom: 10px; border-bottom: 1px solid #E3E3E3;">
						<span style="color: #108AC6"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %></span> 创建于 <span style="color: #108AC6"><s:date name="createdDate" format="yyyy-MM-dd" /></span>
					</div>
					
					
					<div style="width: 98%;" id="${specialTaskId}detail" title="<s:property value="detail" />">
						<pre><s:property value="detail" escape="false"/></pre>
					</div>
					
					<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN,A_SPECTASK_CEOADMIN,A_SPECTASK_VICADMIN">
						<textarea id="${specialTaskId}detailInput" rows="8" onblur="updateDetail('${specialTaskId}');" style="overflow: auto; width: 99%; display: none;"><s:property value="detail"/></textarea>
					</security:authorize>
				</td>
				
				<td colspan="4">

				</td>
			</tr>
			<tr class="detail">
				<td colspan="2" align="center" valign="middle">
					留言：
				</td>
				
				<td style="padding-bottom: 10px;">
					<div id="${specialTaskId}commentDiv">
						<ul class="commentList">
							<s:iterator value="specialTaskComments">
								<li>
									<strong>
									<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>
									(<s:date name="createdDate" format="MM-dd"/>):
									</strong>
									<s:property value="content" escape="true"/>
								</li>
							</s:iterator>
						</ul>
					</div>

					<div>
						<textarea id="${specialTaskId}commentArea" rows="3" style="width: 99%; overflow: auto;"></textarea>
					</div>
				</td>
				
				<td valign="bottom" style="padding-bottom: 10px; padding-left: 10px;">
					<div class="func_icon_publish" onclick="saveComment('${specialTaskId}');">发表</div>
				</td>
				
				<td colspan="5" align="center" valign="bottom">
					
					<div style="height: 45px;">
						<%-- 负责人 --%>
						<s:if test="status == 1">
							<s:if test="responsiblePerson.indexOf(currentUiid) != -1">
								<div class="func_icon" style="float: left; margin-left: 10px;" onclick="updateTask('${specialTaskId}', 'preComplete', '2',this);">
									预完成
								</div>
							</s:if>
							<s:else>
								<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN,A_SPECTASK_CEOADMIN,A_SPECTASK_VICADMIN">
									<div class="func_icon" style="float: left; margin-left: 10px;" onclick="updateTask('${specialTaskId}', 'preComplete', '2',this);">
										预完成
									</div>
								</security:authorize>
							</s:else>
						</s:if>
						
						<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN,A_SPECTASK_CEOADMIN,A_SPECTASK_VICADMIN">
							<s:if test="status != 3">
								<div class="func_icon" style="float: left; margin-left: 10px;" onclick="updateTask('${specialTaskId}', 'complete', '3',this);">
									完成
								</div>
							</s:if>
							
							<s:if test="status != 1">
								<div class="func_icon" style="float: left; margin-left: 10px;" onclick="updateTask('${specialTaskId}', 'revert', '1',this);">
									恢复
								</div>
							</s:if>
							
							<s:if test="status != 3">
								<div class="func_icon" style="float: left; margin-left: 10px;" onclick="remind('${specialTaskId}');">
									提醒
								</div>
							</s:if>
						</security:authorize>
						
						<security:authorize ifAnyGranted="A_SPECTASK_SUPADMIN,A_SPECTASK_CEOADMIN">
							<s:if test="hiddenFlg == 0">
								<div class="func_icon" style="float: left; margin-left: 10px;" onclick="updateTask('${specialTaskId}', 'hide', '1',this);">
									隐藏
								</div>
							</s:if>
							
							<s:if test="hiddenFlg == 1">
								<div class="func_icon" style="float: left; margin-left: 10px;" onclick="updateTask('${specialTaskId}', 'show', '0',this);">
									显示
								</div>
							</s:if>
							
							<s:if test="deletedFlg == 0">
								<div class="func_icon_red" style="float: left; margin-left: 10px;" onclick="updateTask('${specialTaskId}', 'delete', '1',this);">
									删除
								</div>
							</s:if>
						</security:authorize>
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
	var next = $(this).next();
	if (next.css("display") == "none") {
		if (openItem != null) {
			openItem.nextAll(".detail").hide();
			openItem.removeClass("selected");
			openItem.find("div").addClass("splitWord");
			openItem.find("td").css("color", "");
			switchArrow(openItem, "down");
		}
		$(this).addClass("selected");
		$(this).find("td").css("color", "#40a3de");
		$(this).find("div").removeClass("splitWord");
		$(this).next().show();
		$(this).next().next().show();
		switchArrow($(this), "up");
		openItem = $(this);
	} else {
		$(this).removeClass("selected");
		$(this).find("div").addClass("splitWord");
		$(this).find("td").css("color", "");
		switchArrow($(this), "down");
		$(this).next().hide();
		$(this).next().next().hide();
		openItem = null;
	}
});
</script>

<s:if test="id != ''">
	<script language="javascript">
		$("#${id}item").trigger("click");
	</script>
</s:if>