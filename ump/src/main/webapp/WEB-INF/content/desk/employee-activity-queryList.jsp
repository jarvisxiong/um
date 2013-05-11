<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<link  href="${ctx}/resources/css/oa/oa-public-affair.css"rel="stylesheet" type="text/css" />

				<s:if test="page.result.size == 0">
					<div class="noResult">未找到符合条件的记录, 请修改搜索条件重新检索！</div>
				</s:if>
				<s:else>
					<div style="float:left;margin:0px 2px;overflow: auto; overflow-x: hidden;width:100%;">
						<table class="content_table">
							<tr style="cursor: default;">
								<th align="left" style="padding-left:8px;background:none;">标题</th>
								<th width="60px" align="left">发布人</th>
								<th width="140px" align="left">发布时间</th>
								<th width="80px" align="left">查看次数</th>
								<th width="80px" align="left">评论</th>
							</tr>
							<s:iterator value="page.result">
							<tr class="mainTr" onclick="removeNewImg(this); window.open('${ctx}/oa/oa-news!detail.action?id=${oaNewsId}'); return false;">
								<td align="left" title="${subject}">
									<div class="ellipsisDiv_full" style="height:26px; display:inline;">
										<c:out value="${subject}" />
									</div>
									<c:set var="userName"><%=SpringSecurityUtils.getCurrentUiid()%></c:set>
									<c:set var="cReaders"><s:property value="readers" /></c:set>
									<c:set var="isReaded">${fn:indexOf(cReaders, userName)}</c:set>
									<c:if test="${isReaded==-1}"><img class="new_img" src="${ctx}/images/new.gif" /></c:if>
								</td>
								<td align="left"><div><p:code2name mapCodeName="mapCreatorName" value="creator"/></div></td>
								<td align="left"><div><s:date name="newsTime" format="yyyy-MM-dd hh:mm"/></div></td>
								<td align="left"><div><s:property value="clickCount" />&nbsp;</div></td>
								<td align="left"><div>共 <s:text name="%{oaNewsComments.size}" />&nbsp;条</div></td>
							</tr>
							</s:iterator>
						</table>
					</div>
					<div>
						<div class="read_all" style="padding:5px;">
							<img style="vertical-align:middle;" src="${ctx}/resources/images/email/read.gif"/>
							<a href="#" onclick="return readAll('${ctx}/desk/employee-activity!readAll.action', 'mainForm');">
								全部置为已读
							</a>
						</div>
						
						<div class="table_pager">
							<p:page />
							<input id="totalPageNo" name="totalPageNo" type="hidden" value="<s:text name="page.totalPages" />" />
						</div>
					</div>
				</s:else>


