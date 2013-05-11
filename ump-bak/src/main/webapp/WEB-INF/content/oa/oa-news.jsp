<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/common/meta.jsp" %>
		<%@ include file="/common/global.jsp" %>
		<title>新闻管理页面</title>
		<meta http-equiv="Content-Type" content="text/html" />
		<link rel="stylesheet" href="${ctx}/css/desk/deskCommon.css" type="text/css" />
		<script language="javascript" src="${ctx}/js/jquery.js"></script>
		<script src="${ctx}/js/table.js" type="text/javascript"></script>
		<script src="${ctx}/js/desk/oa.js" type="text/javascript"></script>
	</head>

	<body>
		<div class="main">
			<s:form id="mainForm" action="oa-news" method="post">
			<s:hidden name="biz_module" value="news"></s:hidden>
			<div class="round_corner_top">
				<div class="corner_top_left"></div>
				<div class="corner_top_right"></div>
				<div class="corner_top_middle"></div>
			</div>
			<div class="container">
				<div id="search">
					<span class="title">
						<s:text name="oaOaNews.typeCd" />:&nbsp;&nbsp;
					</span>
					<s:select list="mapNewsType" cssStyle="width: 90px;" listValue="value" name="filter_EQS_typeCd" id="filter_EQS_typeCd" onchange="$('#pageNo').val(''); $('#mainForm').submit();" />
					<span class="title">
						<s:text name="oaOaNews.subject" />:&nbsp;&nbsp;
					</span>
					<s:textfield name="filter_LIKES_subject" id="filter_LIKES_subject" size="18" maxlength="50" />&nbsp;
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
					<table class="newsList" cellpadding="0" cellspacing="0">
						<tr>
							<th width="5%" align="left">选择</th>
							<th width="10%" align="left"><s:text name="oaOaNews.typeCd" /></th>
							<th width="35%" align="left"><s:text name="oaOaNews.subject" /></th>
							<th width="10%" align="left"><s:text name="oaOaNews.newsTime" /></th>
							<th width="20%" align="left"><s:text name="oaOaNews.toDeptCd" /></th>
							<th width="20%" align="center"><s:text name="common.operate"/></th>
						</tr>
						<s:iterator value="page.result">
							<tr class="data">
								<td>
									<input type="checkbox" name="ids" value="${oaNewsId}" />
								</td>
								<td>
									<p:code2name mapCodeName="mapNewsType" value="typeCd" />
								</td>
								<td>
									<span>
										<a href="oa-news!detail.action?id=${oaNewsId}" class="subject" target="blank" title="${subject}">
											${subject}
										</a>
										<c:set var="userName"><%=SpringSecurityUtils.getCurrentUiid()%></c:set>
										<c:set var="cReaders"><s:property value="readers" /></c:set>
										<c:set var="isReaded">${fn:indexOf(cReaders, userName)}</c:set>
										<c:if test="${isReaded==-1}"><img src="${ctx}/images/new.gif" /></c:if>
									</span>
								</td>
								<td>
									<s:property value="newsTime" />
								</td>
								<td title="<p:code2name mapCodeName="mapToDeptNames" value="oaNewsId" />">
									<span class="to_depts">
										<p:code2name mapCodeName="mapToDeptNames" value="oaNewsId" />
									</span>
								</td>
								<td align="center">
										<p:operate idName="oaNewsId" action="oa-news" mapEnabled="mapEnabledFlg" enableName="enabledFlg" />
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
						<button class="anniu_lan_4" onclick="window.location.href='${ctx}/oa/oa-news!input.action'; return false;">
							<s:text name="common.create" /><s:text name="oaOaNews" />
						</button>
					</span>
					<span>
						<button id="delAll" class="anniu_lan_6">
							<s:text name="common.deleteSelect" /><s:text name="oaOaNews" />
						</button>
					</span>
					
					<br class="clear" />
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
