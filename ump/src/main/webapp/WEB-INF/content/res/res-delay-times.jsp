<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>网上审批过期次数</title>
		<%@ include file="/common/global.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/desk-oa.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/res/resCss.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"  />
		<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
		<script language="javascript" src="${ctx}/js/table.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	</head>
	
	<body style="line-height: 25px;">
		<div class="moduleTtile">
			<div style="float:left; height:29px;color:#5a5a5a;font-weight: bold;">&nbsp;网上审批过期次数</div>
		</div>
		<div style="height:2px;background-color: #5a5a5a;height:1px;margin-bottom:2px;margin-bottom: 8px;"></div>
		<div style="margin:2px">
		<fieldset>
			<form action="${ctx}/res/res-delay-times!list.action" method="post" id="searchForm">
			<input type="hidden" name="selectedOrderBy" id="selectedOrderBy" value="${selectedOrderBy}"/>
			<input type="hidden" name="selectedOrder" id="selectedOrder" value="${selectedOrder}"/>
			<div style="height: 35px;line-height:35px;background-color: #eeeeee;padding-left: 10px;">
				<div style="float:left;font-weight: bold;margin-right: 10px;font-size: 14px;color:#316685;">记录列表</div>
				<div style="float: left; ">
					<span style="margin-left:15px;margin-right: 5px;">审批月份</span><input class="date" type="text" style="color:red;width:80px;" id="approveDate" name="approveDate" value="${approveDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM'})"/>
				</div>
				<div style="float: left; ">
					<span style="margin-left:10px;margin-right: 5px;">审批人</span>
					<input id="approveUserName" type="text" name="userName" value="${userName}" readonly="readonly" style="cursor: pointer; width: 120px;" />
					<input id="approveUserCd" type="hidden" name="userCd" value="${userCd}" />
				</div>
				<div style="float: left; ">
					<span style="margin-left:10px;margin-right: 5px;">过期次数</span><input type="text" style="width: 100px;" id="delayTimesSmall" name="delayTimesSmall" value="${delayTimesSmall}" />
					<span style="margin-left:5px;margin-right: 5px;">至</span><input type="text" style="width: 100px;" id="delayTimesLarge" name="delayTimesLarge" value="${delayTimesLarge}" />
				</div>
				<div style="float: left; ">
					<span style="margin-left:10px;"><input type="button" class="btn_blue_55_20" style="border:none;" value="搜索" onclick="ajaxSearch();" /></span>
				</div>
			</div>
			<div style="margin:0px 2px;clear: both;min-height: 50px;background-color: #DBDBDB">
				<div style="background-color: #fff;" >
				<div id="resultDiv">
					<%@include file="res-delay-times-list.jsp"%>
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
			showDetail();
		});
	}
	
	function jumpPage(pageNo) {
		$("#pageNo").val(pageNo);
		ajaxSearch();
	}

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
	
	function showDetail() {
		
		$("#editTable tbody tr.mainTr").toggle(function(){
			$(".foldTr").trigger("click");
			$(this).addClass("foldTr");
			var userCd = $(this).attr("name");
			var yearMonth = $(this).children().eq(1).html();
			var url = _ctx+ "/res/res-delay-times!detail.action";
			var data = {
				userCd : userCd,
				yearMonth : yearMonth
			};
			$.post(url, data, function(result) {
				$('#detail_'+userCd+' td').html(result);
			});
			$(this).next().show();
		},function(){
			$(this).removeClass("foldTr");
			$(this).next().hide();
		});
	}
	showDetail();
	
	function openDetail(id) {
		var url = _ctx+'/res/res-approve-info.action?id='+id;
		parent.showAll(url,'resApprove');
	}
	
	function getValidValue(value){
		if(typeof(value)=='undefined'){
			value='';
		}
		return value;
	}
	$('#approveUserName').userSelect({
		muti:false
	});
	function cancelDelay(detailId,detailUserCd,dom) {
		$('#detailId').val(detailId);
		$('#detailUserCd').val(detailUserCd);
		var num=$.trim($('#td_'+detailUserCd).html());
		var _tr=$(dom).parents("tr:first");
		$.post(_ctx+ "/res/res-delay-times!cancelTimeLimit.action",{
			detailId:detailId,
			detailUserCd:detailUserCd
		},function(result) {
			_tr.remove();
			$('#td_'+detailUserCd).html(num-1);
			});
	}
</script>
</body>
</html>


