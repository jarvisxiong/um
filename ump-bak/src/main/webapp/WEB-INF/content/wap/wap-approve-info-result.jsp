<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

		
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
		<!-- 搜素结果 -->
		<div>
			<table class="content_table" id="editTable" style="width: expression(this.parentNode.offsetHeight >this.parentNode.scrollHeight ? '100%' :parseInt(this.parentNode.offsetWidth - 18) + 'px');">
				<s:iterator value="page.result">
					<jsp:include page="wap-approve-info-data.jsp">
						<jsp:param value="0" name="listMode"/>
					</jsp:include>
				</s:iterator>
			</table>
			<div class="table_pager" style="margin-top:5px;">
				<p:page />
			</div>
		</div>