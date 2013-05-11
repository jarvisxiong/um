<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<s:if test="chiefCopyList.size > 0">
	<div style="padding-left:10px;color: #363636;"><strong>
		<s:if test="#request.bizModuleCd=='resChief'">
		总裁意见副本
		</s:if>
		<s:else>
		执行副总裁意见副本
		</s:else>
	</strong>
	</div>
	<div style="padding-left:20px;">
		<ul style ="list-style: none outside none;">
			<s:iterator value="chiefCopyList">
				<li>
					<s:url id="attUrl" action="download" namespace="/app">
						<s:param name="fileName">${fileName}</s:param>
						<s:param name="id">${appAttachFileId}</s:param>
						<s:param name="realFileName">${realFileName}</s:param><!-- 显示真实文件名 -->
						<s:param name="bizModuleCd">${request.bizModuleCd}</s:param>
					</s:url>
					<div style="float: left;max-width: 87%;background-color: #fff;"  class="ellipsisDiv" >
						<a style="color:#0167a2;" href="javascript:void(0)" title="${realFileName} " onclick="openAtta('${attUrl}','${realFileName}')">
						${realFileName}</a>
					</div>
					<div style="float: left; padding-right: 10px;background-color: #fff;">
						<a href="javascript:void(0)" title="${realFileName}" onclick="openAtta('${attUrl}','${realFileName}')"></a>
					</div>
				</li>
			</s:iterator>
		</ul>
	</div>
</s:if>