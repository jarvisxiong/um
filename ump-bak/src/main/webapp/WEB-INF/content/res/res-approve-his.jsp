<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>网上审批历史记录</title>
		<%@ include file="/common/global.jsp"%>
		<link href="${ctx}/css/desk/desk-oa.css" rel="stylesheet" type="text/css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/res/resCss.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"  />
		<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	</head>
	
	<body style="line-height: 25px;">
		<div class="moduleTtile">
			<div style="float:left; height:29px;color:#5a5a5a;font-weight: bold;">&nbsp;网上审批历史记录</div>
		</div>
		<div style="height:2px;background-color: #5a5a5a;height:1px;margin-bottom:2px;margin-bottom: 8px;"></div>
		<div style="margin:2px">
		<fieldset>
			<form action="${ctx}/res/res-approve-his!list.action" method="post" id="searchForm">
			<input type="hidden" name="selectedOrderBy" id="selectedOrderBy" value="${selectedOrderBy}"/>
			<input type="hidden" name="selectedOrder" id="selectedOrder" value="${selectedOrder}"/>
			<div style="height: 35px;line-height:35px;background-color: #eeeeee;padding-left: 10px;">
				<div style="float:left;font-weight: bold;margin-right: 10px;font-size: 14px;color:#316685;">记录列表</div>
				<div style="float: left; ">
					<span style="margin-left:15px;margin-right: 5px;">审批开始时间</span><input class="date" type="text" style="color:red;width:100px;" name="preDateBegin" value="${preDateBegin}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(dp){ajaxSearch();}})"/>
					<span style="margin-left:5px;margin-right: 5px;">至</span><input class="date" type="text" style="color:red;width:100px;" name="preDateEnd" value="${preDateEnd}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(dp){ajaxSearch();}})"/>
				</div>
				<div style="float: left; ">
					<span style="margin-left:10px;margin-right: 5px;">审批人</span>
					<input id="approveUserName" type="text" name="userName" value="${userName}" readonly="readonly" style="cursor: pointer; width: 120px;" />
					<input id="approveUserCd" type="hidden" name="userCd" value="${userCd}" />
				</div>
				<div style="float:left;font-weight: bold;padding-left: 10px;cursor: pointer;" >
					<s:checkbox id="isDelay" name="isDelay" onclick="checkType(this);" ></s:checkbox>只显示过期
				</div>
				<div style="float:left;font-weight: bold;padding-left: 10px;cursor: pointer;" >
					<s:checkbox id="isApproach" name="isApproach" onclick="checkType(this);" ></s:checkbox>接近过期
				</div>
			</div>
			<div style="margin:0px 2px;clear: both;min-height: 50px;background-color: #DBDBDB">
				<div style="background-color: #fff;" >
				<div id="resultDiv">
					<%@include file="res-approve-his-list.jsp"%>
				</div>
				</div>
			</div>
			</form>
		</fieldset>
		</div>

<script type="text/javascript">
	function ajaxSearch(){
		TB_showMaskLayer('正在加载...');
		$('#searchForm').ajaxSubmit(function(r){
			TB_removeMaskLayer();
			$('#resultDiv').html(r);
		});
	}
	
	function jumpPage(pageNo) {
		$("#pageNo").val(pageNo);
		ajaxSearch();
	}
	$('#approveUserName').userSelect({
		muti:false,
		callback:function(map){
			ajaxSearch();
		}
	});
	function jumpPageTo() {
		var index = $("#pageTo").val();
		index = parseInt(index);
		if (index > 0) {
			jumpPage(index);
		}
	}
	
	function sortBy(element,property,oldOrder){
		var sortStr ;
		var old = oldOrder;
		if(typeof(oldOrder)==='undefined'){
			old = ''; 
		}
		if(old=='asc'){
			sortStr = 'desc'; 
		}else if (old=='desc'){
			sortStr = 'asc';
		}else{
			sortStr = 'asc';
		}
		
		$("#selectedOrderBy").val(property);
		$("#selectedOrder").val(sortStr);
		
		ajaxSearch();
	}
	
	function openDetail(id) {
		var url = _ctx+'/res/res-approve-info.action?id='+id;
		parent.showAll(url,'resApprove');
	}
	
	function checkType(ele) {
		
		if(ele.name == "isDelay" && ele.checked) {
			$("#isApproach").attr('checked', false);
		} else if(ele.name == "isApproach" && ele.checked) {
			$("#isDelay").attr('checked', false);
		} else {
			
		}
		
		ajaxSearch();
	}
	
	function notifyThisUser(userCd,id,typeCd,typeName,titleName,reduce,approveCd) {
		if (isEmpty(id)){
			return;
		}
		var url = _ctx+ "/res/res-approve-his!notifyUser.action";
		var data = {
			userCd : userCd,
			id : id,
			typeCd : typeCd,
			typeName : typeName,
			titleName : titleName,
			reduce : reduce,
			approveCd : approveCd
		};
		$.post(url, data, function(result) {
			if(result == "success") {
				alert("提醒成功");
			} else {
				alert("失败");
			}
		});
	}
</script>
</body>
</html>


