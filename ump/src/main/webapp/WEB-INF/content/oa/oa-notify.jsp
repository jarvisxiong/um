<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/nocache.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/common/meta.jsp" %>
		<%@ include file="/common/global.jsp" %>
		<title>管理区域</title>
		<meta http-equiv="Content-Type" content="text/html" />
		<link rel="stylesheet" href="${ctx}/css/desk/deskCommon.css" type="text/css" />
		<script language="javascript" src="${ctx}/js/jquery.js"></script>
		<script src="${ctx}/js/table.js" type="text/javascript"></script>
		<script src="${ctx}/js/desk/oa.js" type="text/javascript"></script>
		<script type="text/javascript">
			function addReader(notifyId, isReaded) {
				if (isReaded == "-1") {
					$.get("${ctx}/oa/oa-notify!detail.action?id=" + notifyId);
				}
			}
		</script>
	</head>

	<body>
		<div class="main">			
			<s:form id="mainForm" action="oa-notify" method="post">
				<s:hidden name="biz_module" value="notify"></s:hidden>
				<div>
					<div class="corner_top_left"></div>
					<div class="corner_top_right"></div>
					<div class="corner_top_middle"></div>
				</div>
				<div class="container">
					<div id="search">
						<span class="title">
							<s:text name="oaOaNotify.subject" />:&nbsp;&nbsp;
						</span>
						<s:textfield name="filter_LIKES_subject" id="filter_LIKES_subject" size="18" maxlength="30" />&nbsp;
						<span>
							<button class="anniu_lan_4" onclick="$('#pageNo').val(''); $('#mainForm').submit(); return false;">
								<s:text name="common.search" />
							</button>
						</span>
						<span>
							<button class="anniu_lan_4" onclick="document.getElementById('mainForm').reset(); return false;">
								<s:text name="common.reset" />
							</button>
						</span>
					</div>
					
					<div id="content">
						<table class="notifyList">
							<tr>
								<th width="5%" align="left">选择</th>
								<th width="10%" align="left"><s:text name="oaOaNotify.creator"/></th>
								<th width="40%" align="left"><s:text name="oaOaNotify.subject" /></th>
								<th width="10%" align="left"><s:text name="oaOaNotify.sendTime"/></th>
								<th width="15%" align="left"><s:text name="oaOaNotify.toDeptCd"/></th>
								<th width="20%" align="center"><s:text name="common.operate"/></th>
							</tr>
							<s:iterator value="page.result">
								<tr class="data">
									<td align="left">
										<input type="checkbox" value="${oaNotifyId}" name="ids" />
									</td>
									<td align="left">
										<p:code2name mapCodeName="mapCreatorName" value="creator"/>
									</td>
									<td align="left">
										<c:set var="userName"><%=SpringSecurityUtils.getCurrentUiid()%></c:set>
										<c:set var="cReaders"><s:property value="readers" /></c:set>
										<c:set var="isReaded">${fn:indexOf(cReaders, userName)}</c:set>
									
										<a href="oa-notify!input.action?id=${oaNotifyId}" class="subject" onclick="addReader('${oaNotifyId}', '${isReaded}');" title="${subject}">
											${subject}
										</a>
										<c:if test="${isReaded==-1}"><img src="${ctx}/images/new.gif" /></c:if>
									</td>
									<td align="left">
										<s:property value="sendTime" />
									</td>
									<td align="left" title="<p:code2name mapCodeName="mapToDeptNames" value="oaNotifyId" />">
										<span class="to_depts">
											<p:code2name mapCodeName="mapToDeptNames" value="oaNotifyId" />
										</span>
									</td>
									<td align="center">
											<p:operate idName="oaNotifyId" action="oa-notify" mapEnabled="mapEnabledFlg" enableName="enabledFlg" />
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					
					<div id="ops">
						<span class="selectAll">
							<s:checkbox name="all" id="selectAll" />&nbsp;&nbsp;全选
						</span>
						<span class="pager"><p:page /></span>
						<span>
							<button class="anniu_lan_4" onclick="window.location.href='${ctx}/oa/oa-notify!input.action'; return false;">
								<s:text name="common.create" />
							</button>
						</span>
						<span>
							<button id="delAll" class="anniu_lan_4">
								<s:text name="common.deleteSelect" />
							</button>
						</span>
						
					</div>
				</div>
				<div class="round_corner_bottom">
					<div class="corner_bottom_left"></div>
					<div class="corner_bottom_right"></div>
					<div class="corner_bottom_middle"></div>
				</div>
				
			</s:form>
		</div>
	</body>
</html>
