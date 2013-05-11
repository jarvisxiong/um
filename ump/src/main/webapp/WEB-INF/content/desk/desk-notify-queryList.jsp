<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<link  href="${ctx}/resources/css/oa/oa-public-affair.css"rel="stylesheet" type="text/css" />

				<s:if test="page.result.size == 0">
					<div class="noResult">未找到符合条件的公告记录，请修改搜索条件重新检索！</div>
				</s:if>
				<s:else>
					<div style="clear:both;margin:0px 2px; overflow: auto; overflow-x: hidden;width:100%;">
						<table class="content_table">
							<tr style="cursor: default;">
							<th align="left" style="padding-left:8px;background:none;">标题</th>
							<th width="140px" align="left">发布范围</th>
							<th width="80px" align="left">发布人</th>
							<th width="160px" align="left">发布时间</th>
							</tr>
							<s:iterator value="page.result">
							<s:url id="down" action="show" namespace="/app" includeParams="none" escapeAmp="true" >
								<s:param name="bizModuleCd">oaNotify</s:param>
								<s:param name="fileName"><p:code2name mapCodeName="mapAttachFileNames" value="oaNotifyId" /></s:param>
								<s:param name="realFileName"><p:code2name mapCodeName="mapAttachFileNames" value="oaNotifyId" /></s:param>
								<s:param name="filterType">pdf</s:param>
								<s:param name="operator">inline;</s:param>
							</s:url>
							<c:set var="userName"><%=SpringSecurityUtils.getCurrentUiid()+","%></c:set>
							<c:set var="cReaders"><s:property value="readers" /></c:set>
							<c:set var="isReaded">${fn:indexOf(cReaders, userName)}</c:set>
							<tr class="mainTr" onclick="removeNewImg(this);parent.openNotify('${down}', '${oaNotifyId}', '${isReaded}');">
								<td align="left" title="${subject}">
									<div class="ellipsisDiv" style="height:25px; display:inline;">
										<c:if test="${isReaded==-1}"><img class="new_img" src="${ctx}/images/new.gif" /></c:if>
										<c:if test="${topFlg == '1'}">【置顶】</c:if>&nbsp;
										<c:out value="${subject}" />
									</div>
								</td>
								<td nowrap align="left" class="ellipsisDiv" title="<p:code2name mapCodeName="mapToDeptNames" value="oaNotifyId" />">
									<div style="overflow:hidden;height:26px;width:80px;">
										<span class="to_depts">
											<p:code2name mapCodeName="mapToDeptNames" value="oaNotifyId" />
										</span>
									</div>
								</td>							
								<td align="left"><div><p:code2name mapCodeName="mapCreatorName" value="creator"/></div></td>
								<td align="left"><div><s:date  name="sendTime" format="yyyy-MM-dd hh:mm"/></div></td>
							</tr>
							</s:iterator>
						</table>
					</div>
					<div style="margin-top:5px;">
						<div class="read_all" >
							<img style="vertical-align:middle;" src="${ctx}/resources/images/email/read.gif"/>
							<a href="#" onclick="return readAll('${ctx}/desk/desk-notify!readAll.action', 'mainForm');">
								全部置为已读
							</a>
						</div>
						<div class="table_pager">
							<p:page />
							<input id="totalPageNo" name="totalPageNo" type="hidden" value="<s:text name="page.totalPages" />" />
						</div>
					</div>
				</s:else>


