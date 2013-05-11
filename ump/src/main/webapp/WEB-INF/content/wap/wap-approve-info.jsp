<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>宝龙管理平台</title>
<%@ include file="/common/global.jsp" %>
	<meta name="HandHeldFriendly" content="true">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=2.0" />
	<%-- wap网上审批样式 --%>
	<link rel="stylesheet" href="${ctx}/resources/css/wap/wap_approve_info.css" />
	<script src="${ctx}/resources/js/wap/ajaxrequest.js"></script>
	<script src="${ctx}/resources/js/wap/wap_approve_info.js"></script>
	
</head>
<body>
	<div id="content" >
		<div id="div_logo">
		<span style="margin:auto 5px;font-size:16px;color: #333;">宝龙管理系统</span>
		<div class="btn_exit_home" style="float:right;" id="toExit" onclick="logout(this);">
		退出
		</div>
		<div style="clear: both;">
		</div>
		</div>
		<div id="div_main">
			<div>
				<div class="channel_title">
				手机审批
				</div>
				<input type="text" onblur="resetSearchInput(this);"  onkeyup="loadResult(this);" onclick="clearSearchInput(this);"   class="wap_search_input" value="搜索..." name="filter_LIKES_titlename" id="filter_LIKES_titlename">
		    </div>
		    <div style="margin-left: 5px;">
				<input  type="button" value="审批" id="myRes" class="wap_btn_label"  onclick="myRes('1',true);" />
				<input  type="button" value="历史" id="myHis" class="wap_btn_label" onclick="myHis('1',true);"  />
		    </div>
		    <%--listMode:0 直接搜索所有待审批---%>
		  	<input class="srh_hidden" type="hidden"  id="srh_selectOpinion" name="selectOpinion" value="${selectOpinion}"/>
		    <input class="srh_hidden" type="hidden" id="srh_qsCondition" name="qsCondition" value="${qsCondition}"/>
		    <div id="searchResult" >
		    </div>
		</div>
	</div>
	
</body>
</html>
<script>
	var SEARCH_VALUE = '搜索...';
	//初始化
	if($("srh_qsCondition").value=='3'){
		myHis();
	}else{
		myRes();
	}
	function myRes(pageNo,flag){
		$("myRes").className = "wap_btn_label_click";
		$("myHis").className = "wap_btn_label";
		load(pageNo,flag,'2');
	}
	function myHis(pageNo,flag){
		$("myRes").className = "wap_btn_label";
		$("myHis").className = "wap_btn_label_click";
		load(pageNo,flag,'3');
	}
	function load(pageNo,flag,qsCondition){
		var pNo = "${page.pageNo}";
		if(pageNo != undefined){
			pNo = pageNo;
		}
		$("filter_LIKES_titlename").value = "";
		if(flag != undefined && flag == true){
			//alert($("srh_selectDealOpinion").value);
			window.location.href=_ctx+"/wap/wap-approve-info.action?qsCondition="+qsCondition;
			return false;
		}
		ajax.update(function(obj) {
			$("searchResult").innerHTML = obj.responseText;
			//因为ajax返回请求中js无法执行的，所以在对应页面中加入隐藏字段
			$("filter_LIKES_titlename").value = SEARCH_VALUE;
			},
			"${ctx}/wap/wap-approve-info!load.action",	// get data from getdata.asp
			1,	// 更新时间(豪秒)
			1,	// 更新次数
			{filter_LIKES_titlename:$("filter_LIKES_titlename").value,'page.pageNo':pNo,selectOpinion:$("srh_selectOpinion").value,qsCondition:$('srh_qsCondition').value},
			 method
			);
	}
function jumpPage(pageNo) {
	var url = "${ctx}/wap/wap-approve-info!load.action";
	var param = {};
	$("pageNo").value = pageNo;
	if($("filter_LIKES_titlename").value == SEARCH_VALUE){
		$("filter_LIKES_titlename").value = "";
		}
	param = {filter_LIKES_titlename:$("filter_LIKES_titlename").value,
			'page.pageNo':pageNo,selectOpinion:$("srh_selectOpinion").value,qsCondition:$('srh_qsCondition').value};
	ajax.update(function(obj) {
		$("searchResult").innerHTML= obj.responseText;
		$("filter_LIKES_titlename").value = SEARCH_VALUE;
		},
		url,	// get data from getdata.asp
		1,	// 更新时间(豪秒)
		1,	// 更新次数
		param,
		method);
}
function jumpPageTo() {
	var index = $("pageTo").value;
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
function openDetail(resApproveInfoId,pageNo,listMode) {
	window.location.href="${ctx}/wap/wap-approve-info!reinput.action?"
	+"id="+resApproveInfoId+"&selectOpinion="+$("srh_selectOpinion").value
	+"&qsCondition="+$("srh_qsCondition").value
	+"&page.pageNo="+pageNo
	;
}
//选项
function seleOp(dealOpinion,flag) {
	$("pageNo").value = "1";
	$("srh_selectOpinion").value = dealOpinion ;
	if($("filter_LIKES_titlename").value == SEARCH_VALUE){
		$("filter_LIKES_titlename").value = "";
	}
	ajax.update(function(obj) {
		$("searchResult").innerHTML= obj.responseText;
		if($("filter_LIKES_titlename").value == ''){
			$("filter_LIKES_titlename").value = SEARCH_VALUE;
		}
		},
		"${ctx}/wap/wap-approve-info!load.action",	// get data from getdata.asp
		1,	// 更新时间(豪秒)
		1,	// 更新次数
		 {filter_LIKES_titlename:$("filter_LIKES_titlename").value,'page.pageNo':'1',selectOpinion:$("srh_selectOpinion").value,qsCondition:$('srh_qsCondition').value},
		 method
		);
}
function logout(doj){
	if(window.confirm("确认退出系统？")){
		ajax.update(function(obj) {
			},
			"${ctx}/login!reduceUser.action",	// get data from getdata.asp
			1,	// 更新时间(豪秒)
			 1,	// 更新次数
			 method
			 );
		window.location.href=_ctx+'/login!logout.action'; 
	}
}
</script>
