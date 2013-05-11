<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>新闻详情</title>
		<meta http-equiv="Content-Type" content="text/html" />
		<link rel="stylesheet" href="${ctx}/css/desk/article.css" type="text/css" />
		<script language="javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/desk/oaNews.js"></script>
	</head>

	<body>
		<div id="container">
			<div id="container_outer">
				<div class="border corner_top_left"></div>
				<div class="border corner_top_middle"></div>
				<div class="border corner_top_right"></div>
				<div class="border corner_middle_left"></div>
				<div class="border corner_middle_right"></div>
				<div class="border corner_bottom_right"></div>
				<div class="border corner_bottom_middle"></div>
				<div class="border corner_bottom_left"></div>
				
				<div id="container_inner">
					<div id="article_header">
						<h1>${news.subject}</h1>
					</div>
					
					<div id="_article">
						<div class="hengxian"></div>
						<div id="newsInfo">
							发布于<s:property value="news.newsTime" />
						</div>
					
						<div id="article_content">
							<s:property value="news.content" escape="false" />
						</div>
						
						<div class="hengxian"></div>
						
						<div id="article_attachment">
							<s:iterator value="mapAtta">
								<s:url id="showAttachment" action="downOld" namespace="/old" includeParams="none" escapeAmp="true">
									<s:param name="bizModuleCd">oldOaNews</s:param>
									<s:param name="attaName"><s:property value="value" escape="false"/></s:param>
									<s:param name="attaId"><s:property value="key"/></s:param>
								</s:url>
								<div><a href="${showAttachment}"><s:property value="value" escape="false"/></a></div>
							</s:iterator>
						</div>
					</div>
				</div>
				<div style="height: 15px; position: absolute; top: -15px; left: 0px; width: 100%;"></div>
				<div style="width: 15px; position: absolute; right: -15px; top: 0px; height: 100%;"></div>
				<div style="height: 15px; position: absolute; bottom: -15px; left: 0px; width: 100%;"></div>
				<div style="width: 15px; position: absolute; left: -15px; top: 0px; height: 100%;"></div>
			</div>
		</div>
	</body>
</html>
