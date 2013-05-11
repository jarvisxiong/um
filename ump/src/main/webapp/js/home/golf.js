
var searchTime;//搜索邮件 --人员搜索定时器
function showSearchUser(dom){
	var $currentDom = $(dom);
	var $next = $(dom).next();
	
	if(searchTime)clearTimeout(searchTime);
	searchTime = setTimeout(function(){
		$("#creatorCdId,#toUserCdId").val("");
		var val = $.trim($currentDom.val());
		//$next.val("");
		if(val == ""){
			$("#searchUserDiv").slideUp(200);
			return;
		}
		$.post(_ctx+"/plan/plan-train-golf!getUsersByFilter.action",{value:val,maxNum:10},function(result){
			var $offset = $currentDom.offset();
			$("#searchUserDiv").css({left:$offset.left,top:$offset.top+$currentDom.height()}).empty().show();
			result = eval(result);
			$.each(result,function(i,n){
				var html = "<div uiid='"+n.uiid+"'><span>"+n.userName +"</span>|<span>"+ n.orgName+"</span></div>";
				$("#searchUserDiv").append(html);
			});
			$("#searchUserDiv div").click(function(){
				var userName = $(this).children("span:first").text();
				var uiid = $(this).attr("uiid");
				$currentDom.val(userName);
				$next.val(uiid);
				$("#searchUserDiv").slideUp(200);
			});
		});
	}, 300);
	$(document).bind('click',function(event){
		var event  = window.event || event;
	    var obj = event.srcElement ? event.srcElement : event.target;
	    if(obj != dom && obj != $("#searchResult")[0]){
	    	$("#searchUserDiv").slideUp(200);
	    	if($next.val() == ''){
	    		$currentDom.val('');
	    	}
	    }
	    $(document).unbind('click');
	});
}

function exportExcel(){
	$('#mainForm').attr('action','plan-train-golf!exportExcel.action');
	search();
	$('#mainForm').attr('action','plan-train-golf');
}