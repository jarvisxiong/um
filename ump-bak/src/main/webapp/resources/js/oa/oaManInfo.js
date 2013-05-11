function openDetail(dictCd,entityCd, oaManInfoId,pageNo) {
	var data={
		dictCd : dictCd,
		id : oaManInfoId,
		proposalCatCd:entityCd,
		'page.pageNo':pageNo
	};
	
	$.post(_ctx + "/oa/oa-man-info!input.action",data , function(result) {
		$("#content").html(result);
		resetLeftPanel();
	});
}


function listMy(toNo) {
	dictCdVal = $("#dictCd").val();
	var pageNo = $("#pageNo").val();
	if(!isEmpty(toNo)){
		pageNo=toNo;
	}
	$.post(_ctx + "/oa/oa-man-info!load.action", {
		dictCd : dictCdVal, 
		'page.pageNo':pageNo
	}, function(result) {
		$("#content").html(result);
		resetLeftPanel();
	});
}

//private
function getValidValue(value){
	if(typeof(value)=='undefined'){
		value='';
	}
	return value;
}
//搜索
function searchOaManList() {
	searchCondition={'page.pageNo':'1',
		dictCd						: getValidValue($("#manInfoCd").val()),
		filter_EQS_statusCd			: getValidValue($("#filter_EQS_statusCd").val()),
		filter_LIKES_appSerialNo	: getValidValue($("#filter_LIKES_appSerialNo").val()),
		filter_EQS_applyUserCd		: getValidValue($("#applyUserCds").val()),
		filter_LIKES_applyUserName	: getValidValue($("#applyUserNames").val()),
		//filter_EQS_approveUserCd  	: getValidValue($("#approveUserCds").val()),
		//filter_LIKES_approveUserName: getValidValue($("#approveUserNames").val()),
		filter_GED_startDate		: getValidValue($("#filter_GED_startDate").val()),
		filter_LTD_startDate		: getValidValue($("#filter_LTD_startDate").val()),
		filter_LIKES_titleName		: getValidValue($("#filter_LIKES_titleName").val())
	};
	$("#pageNo").val("1");
	TB_showMaskLayer("正在搜索...");
	$("#mainFormSearch").ajaxSubmit(function(result) {
		$("#content").html(result); 
		resetLeftPanel();
		TB_removeMaskLayer();
	});
}
//调整高度
var baseMin = 100;
var firstFlg = true;
function resetLeftPanel(){
	
	
	//初始化左边高度
	var docHeight = $(document).height();
	var titHeight = $("#idDivTitle").height();
	var rigHeight = $("#content").height();
	var minHeight = (docHeight - titHeight-5);
	if( firstFlg ){
		baseMin = minHeight;
		firstFlg= false;
	}
	
	//alert("rigHeight: " + rigHeight + ",baseMin:" + baseMin);
	
	if( rigHeight < baseMin){
		$("#divTreeP").height(baseMin);
		$("#leftPanel").height(baseMin);
	}else{
		$("#tableDiv").height(baseMin-$("#rightHeadTool").height()-3);
		$("#divTreeP").height(baseMin);
		$("#leftPanel").height(baseMin);
	}
	
	//查看明细
	if( $("#detailPanelDiv").length == 1){
		$("#divTreeP").height(baseMin);
		$("#leftPanel").height(baseMin);
		$("#divAll").height(baseMin);
		$("#detailPanelDiv").height(baseMin - $("#funcPanelDiv").height()-280);
	}

	
}

var searchTime;
function showSearchUser(dom){
	var $currentDom = $(dom);
	var $next = $(dom).siblings();
	
	if(searchTime)clearTimeout(searchTime);
	searchTime = setTimeout(function(){
		var val = $.trim($currentDom.val());
		$next.val("");
		if(val == "") {
			$("#searchUserDiv").slideUp(200);
			return;
		}
		$.post(_ctx+"/com/user-choose!getUsersByFilter.action", {value:val,maxNum:10}, function(result) {
			result = eval(result);
			var $offset = $currentDom.offset();
			$("#searchUserDiv").css({left:$offset.left,top:$offset.top+$currentDom.height() + 5}).empty().show();
			$.each(result, function(i,n) {
				var html = "<div uiid='"+n.uiid+"'><span>"+n.userName +"</span>|<span>"+ n.orgName+"</span></div>";
				$("#searchUserDiv").append(html);
			});
		
			$("#searchUserDiv div").click(function() {
				var userName = $(this).children("span:first").text();
				var uiid = $(this).attr("uiid");
				$currentDom.val(userName);
				$next.val(uiid);
				$("#searchUserDiv").slideUp(200);
			});
		});
	}, 300);
	
	$(document).bind('click', function(event) {
		var event  = window.event || event;
	    var obj = event.srcElement ? event.srcElement : event.target;
	    if(obj != dom){
	    	$("#searchUserDiv").slideUp(200);
	    	if($next.val() == ''){
	    		$currentDom.val('');
	    	}
	    }
	    $(document).unbind('click');
	});
	return false;
}