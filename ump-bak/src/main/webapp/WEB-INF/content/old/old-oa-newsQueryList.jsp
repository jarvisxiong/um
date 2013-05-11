<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<link  href="${ctx}/resources/css/oa/oa-public-affair.css"rel="stylesheet" type="text/css" />

				<s:if test="pageNews.result.size == 0">
					<div class="noResult">未找到符合条件的新闻记录, 请修改搜索条件重新检索！</div>
				</s:if>
				<s:else>
				<div style="clear:both;margin:0px 2px; overflow: auto; overflow-x: hidden;width:100%;">
					<table class="content_table">
						<tr style="cursor: default;">
							<th align="left" style="padding-left:8px;background-image:none;">标题</th>
							<th width="80px" align="left">类型</th>
							<th width="200px" align="left">发布时间</th>
						</tr>
						<s:iterator value="pageNews.result">
						<tr class="mainTr">
							<td align="left" class="ellipsisDiv" onclick="window.open('${ctx}/old/old-oa!newsDetail.action?id=${newsId}'); return false;">
								<div><s:property value="subject"/></div>
							</td>
							<td align="left"><div><s:property value="typeId"/></div></td>
							<td align="left"><div><s:property value="newsTime"/></div></td>
						</tr>
						</s:iterator>
					</table>
				</div>
				<div class="table_pager" align="center" style="padding-top:2px;margin-top:5px;"><p:page pageInfo="pageNews"/></div>
				</s:else>


