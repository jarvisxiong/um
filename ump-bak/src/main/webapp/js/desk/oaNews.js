function deleteComment(newsCommentId, newsId, detailFlag, pageSize) {
	$.post(_ctx+"/oa/oa-news-comment!delete.action", {
		id : newsCommentId,
		oaNewsId : newsId,
		detailFlag : detailFlag,
		pageSize : pageSize
	}, function(result) {
		if (result) {
			$("#article_comment").html(result);
		}
	});
}
function saveComment() {
	var value = $("#commentInputArea").val();
	if (jQuery.trim(value).length == 0) {
		alert("请输入评论内容!");
		return;
	}
	
	// 用AJAX的方式异步提交表单
	var postUrl = _ctx+"/oa/oa-news-comment!save.action";
	var oaNewsId = $("#inputFromComment input:hidden[name='oaNewsId']").val();
	var pageSize = $("#inputFromComment input:hidden[name='pageSize']").val();
	var detailFlag = $("#inputFromComment input:hidden[name='detailFlag']").val();
	var parentId = $("#inputFromComment input:hidden[name='parentId']").val();
	
	$.post(postUrl,
		{
		oaNewsId: oaNewsId,
		pageSize: pageSize,
		detailFlag: detailFlag,
		parentId: parentId,
		content: value
		},
		function(result) {
			$("#article_comment").html(result);
		}
	);
}
function showAllComments(oaNewsId) {
	$.post(_ctx+"/oa/oa-news-comment.action?oaNewsId=" + oaNewsId,
		function(result) {
			if (result) {
				$("#article_comment").html(result);
			}
		}
	);
	return false;
}
function hideArticle(speed, srcEle) {
	if ($("#_article").css("display") == "none") {
		$("#_article").slideDown(speed);
		$("#" + srcEle).html("隐藏正文>>>");
	} else {
		$("#_article").slideUp(speed);
		$("#" + srcEle).html("显示正文>>>");
	}
}
function closeWindow() {
  window.close();
}
function jumpCommentPageTo(oaNewsId, pageNo) {
	$.get(
		_ctx+"/oa/oa-news-comment.action?oaNewsId=" + oaNewsId + "&page.pageNo=" + pageNo,
		function(data) {
			$("#article_comment").html(data);
		}
	);
}
function replyComment(commentId) {
	$.post(_ctx+"/oa/oa-news-comment!replyComment.action?id=" + commentId,
		function(result) {
			if (result) {
				$("#commentInputArea").val(result);
				$("#commentInputArea").focus();
			}
		}
	);
}
$(function() {
	$.get(
		$("#news_comment_div").attr("href"),
		function(data) {
			$("#news_comment_div").html(data);
		}
	);
	
	$.get(
		$("#attach_files_div").attr("href"),
		function(data) {
			$("#attach_files_div").html(data);
		}
	);
});