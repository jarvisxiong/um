<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css"/>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>

<title>退铺发起</title>
</head>

<body>
<div class="title_bar">
	<div style="font-weight:bold;padding-left:8px;font-size:14px;float:left;">退铺发起</div>
	<%-- 
	<div style="float:right;font-size:12px;padding-right:10px;line-height:30px;">
		<input type="button" value="返回" class="btn_green" onclick="closeWindow();" style="margin-top: 4px;"/>
	</div>
	--%>
</div>

<s:form id="processForm" action="bis-tenant!shopBackComplete.action" method="post">
<div style="padding: 10px 10px 0 10px;">
<div style="padding-left: 10px; height: 30px; line-height:30px; border: 1px solid #eeeeee;">
	商家：<span style="color: blue;">${nameCn}</span>&nbsp;&nbsp;&nbsp;&nbsp;商铺：<span style="color: blue;">${bisStoreNos}</span>
	<input type="hidden" id="bisProjectId" name="bisProjectId" value="${bisProjectId}"/>
	<input type="hidden" id="bisTenantId" name="bisTenantId" value="${bisTenantId}"/>
	<input type="hidden" id="step" value="1"/>
</div>
</div>

<div id="shopBackPanel" style="padding: 10px;">
	
</div>
</s:form>

<script type="text/javascript">
$(function(){
	goStep(1);
});

// 查看合同明细
function viewContDetail(id) {
	if(isEmpty(id)) {
		return;
	}
	var url = _ctx+"/bis/bis-cont!input.action?id="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab("bisContMenu","合同明细",url,true);
}

// 查看费用明细
function viewFeeDetail(){
	var bisTenantId = $('#bisTenantId').val();
	var bisProjectId = $('#bisProjectId').val();
	if(isEmpty(bisTenantId) || isEmpty(bisProjectId)) {
		return;
	}
	//var url = _ctx+"/bis/bis-fact!list.action?dimension=2"+"&bisTenantId="+bisTenantId+"&bisProjectId="+bisProjectId;
	//parent.TabUtils.newTab("bisFee","收费明细",url,true);
	//跳转至收费明细页面：欠费维度
	var url="${ctx}/bis/bis-manage!layout.action?ifFromReport=true&module=3&dimension=3&bisTenantId="+bisTenantId+'&bisProjectId='+bisProjectId;
	if(null!=parent.TabUtils){
		parent.TabUtils.newTab("bis-manage-layout","费用明细",url,true);
	}else{
		window.open(url);
	}
}

//关闭标签
function closeWindow() {
	var cfg = {};
	cfg.data = {tabId:"shopBackMenu"};
	parent.TabUtils.closeTab(cfg);
}

// 管理附件
function openAttachment(title,entityId,domId,attachFlgId){
	ymPrompt.win({
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=bisProjectLayout&filterType=image|office",
		width:500,
		height:300,
		title:title,
		iframe:true,
		afterShow : function() {},
		handler : function(btn) {
			if(btn=='close') {
				showAttach(entityId,domId,attachFlgId);
			}
		},
		btn:[["完成",'close']]
	 });
}
// 判断是否有附件
function showAttach(entityId,domId,attachFlgId) {
	$.post(_ctx+"/app/app-attachment!hasAttachment.action",
		{bizEntityId:entityId,bizModuleCd:'bisProjectLayout'},
		function(result){
			if(result == "true") {
				$("#"+domId).attr("src",_ctx+"/resources/images/common/atta_y.gif");
				$("#"+attachFlgId).val("1");
			} else {
				$("#"+domId).attr("src",_ctx+"/resources/images/common/atta.gif");
				$("#"+attachFlgId).val("0");
			}
	});
}

// 下一步
function goNextStep() {
	var step = Number($('#step').val());
	var url = "${ctx}/bis/bis-tenant!validateStep.action";
	$.post(url,{bisTenantId:'${bisTenantId}', step:step},function(result){
		if(result=='success') {
			goStep(step+1);
		} else {
			ymPrompt.confirmInfo({message:result,title:'退铺',width:280,height:180,handler:function (tp){
				if (tp=='ok'){
					goStep(step+1);
				}
			}});
		}
	});
}
// 上一步
function goPrevStep() {
	var step = Number($('#step').val());
	goStep(step-1);
}

function goStep(step) {
	$('#step').val(step);
	var data = {
		bisTenantId:'${bisTenantId}',
		step:step
	};
	if(!isEmpty($('#backReason').val())) {
		data.backReason = $('#backReason').val();
	}
	if(!isEmpty($('#backDate').val())) {
		data.backDate = $('#backDate').val();
	}
	if(!isEmpty($('#attachFlg').val())) {
		data.attachFlg = $('#attachFlg').val();
	}
	if(!isEmpty($('#remark').val())) {
		data.remark = $('#remark').val();
	}
	var url = "${ctx}/bis/bis-tenant!goStep.action";
	$.post(url,data,function(result){
		$("#shopBackPanel").html(result);
	});
}

function doComplete() {
	if(isEmpty($('#backDate').val())) {
		alert("请填写退铺日期");
		return false;
	}
	if(fucCheckLength($('#remark').val()) > 200) {
		alert("备注过长");
		return false;
	}
	TB_showMaskLayer("正在提交...");
	$("#processForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		alert("操作成功");
		closeWindow();
	});
}

//JS检测中英文字符串长度
function fucCheckLength(strTemp) {  
	var i,sum;
	sum=0;
	for(i=0;i<strTemp.length;i++) {
		if ((strTemp.charCodeAt(i)>=0) && (strTemp.charCodeAt(i)<=255)) {
			sum=sum+1;
		} else {
			sum=sum+2;
		}
	}
	return sum;
}
isEmpty = function (str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
};
</script>

</body>
</html>