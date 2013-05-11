var ID_SEARCH_RESULT = 'searchResult';// 搜索面板ID
var DEFAULT_SEARCH_TIP = '搜索...';
var userSearchMgr;
var method = "POST";
//为String类型增加trim方法
String.prototype.Trim = function()
{
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

//屏蔽回车
document.keydown=function(event) { 
	  if (event.keyCode == 13) {
	   // $("#mainFormSearch").each(function() { 
	    //  event.preventDefault(); 
	   // }); 
	  }
	}; 
function loadResult(srcElem){
	var resultElemId = ID_SEARCH_RESULT;
	var url = _ctx+"/wap/wap-approve-info!load.action";
	var param = {};
	
	if (userSearchMgr)
		clearTimeout(userSearchMgr);
	
	userSearchMgr = setTimeout(function() {
		var val = srcElem.value;
		if (val == "") {
			//$(resultElemId).style.display = "none";
			return;
		} else {
			//判断是否为历史数据
			param = {filter_LIKES_titlename:val,'page.pageNo':'1',selectOpinion:$("srh_selectOpinion").value,qsCondition:$('srh_qsCondition').value};
			$(resultElemId).style.display = "";
			srcElem.style.color = "#5A5A5A";
			
			ajax.update(function(obj) {
				$("searchResult").innerHTML= obj.responseText;
				},
				url,	// get data from getdata.asp
				1,	// 更新时间(豪秒)
				 1,	// 更新次数
				 param,	// param
				 method
				 );
		}
	}, 300);
}

function resetSearchInput(srcElem){
	var resultElemId = ID_SEARCH_RESULT;
	var val = srcElem.value.Trim();
	if( val == '' || val == DEFAULT_SEARCH_TIP){
		srcElem.value = DEFAULT_SEARCH_TIP;
		srcElem.style.color = "#909090";
	}
}

function clearSearchInput(srcElem) {
	var resultElemId = ID_SEARCH_RESULT;
	var val = srcElem.value.Trim();
	if (val == DEFAULT_SEARCH_TIP) {
		srcElem.value = "";
		srcElem.style.color = "#5A5A5A";
	}
};


