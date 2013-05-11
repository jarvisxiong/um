<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>新闻详情</title>
		<meta http-equiv="Content-Type" content="text/html" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/article.css" />
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
						<h1>【<p:code2name mapCodeName="mapNewsType" value="typeCd"/>】${subject}</h1>
						
						<div style="">
							<a href="#" id="hideArticle" style="position: absolute; top: 15px; right: 15px; text-decoration none; color: #000; font-weight: bold;" onclick="return hideArticle(200, this.id);">
								隐藏正文&gt;&gt;&gt;
							</a>
						</div>
					</div>
					
					<div id="_article">
						<div class="hengxian"></div>
						<div id="newsInfo" style="margin-top:5px;">
							发布人：<s:property value="creatorName"/>&nbsp;&nbsp;&nbsp;&nbsp;
							点击<s:property value="clickCount"/>次&nbsp;&nbsp;&nbsp;&nbsp;
							评论<s:property value="oaNewsComments.size"/>条&nbsp;&nbsp;&nbsp;&nbsp;
							发布于<s:property value="newsTime" />
						</div>
					
						<div id="article_content">
							<s:property value="content" escape="false" />
						</div>
						
						<div class="hengxian"></div>
						
						<div id="article_attachment">
							<s:url id="showAttachment" action="app-attachment!show.action" namespace="/app">
								<s:param name="bizModuleCd">oaNews</s:param>
								<s:param name="bizEntityId">${oaNewsId}</s:param>
								<s:param name="filterType">image</s:param>
							</s:url>
							
							<div id="attach_files_div" href="${showAttachment}">
								附件加载中...
							</div>
						</div>
					</div>
					
					<div id="article_comment">
						<s:url id="newsComm" value="oa-news-comment.action" namespace="/oa">
							<s:param name="oaNewsId">${oaNewsId}</s:param>
							<s:param name="page.pageSize">30</s:param>
							<s:param name="detailFlag">1</s:param>
						</s:url>
						
						<div id="news_comment_div" href="${newsComm}">
							评论加载中...
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
