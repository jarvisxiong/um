<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/global.jsp"%>
<title>商业计划平台</title>

<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/cost-ctrl.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/planWork.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/plan-target.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/exec-plan.css"/>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/plan/plan-target.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plan/exec-plan.js"></script>
<script>
    var orgID="${projectCd}";
	var str="${ctx}/plan/bis-target!bis.action";
	var now_year = "${now_year}";
	var now_month = "${now_month}";
	var orderBy = "";
	var order = "";
	var _PATH="${ctx}";

	function isLoad(action){
		var orgText=$("#"+orgID).text();
		if(orgText!=null && orgText!=""){
			$("#iframe_body").attr("orgName",orgText);
			$("#iframe_body").attr('src',action);
			$("#iframe_body").attr("orgID",orgID);
			$("#"+orgID).css("background-color","#0072bb");
			$("#hint").hide();
		}else{
			orgText = "153";
			$("#iframe_body").attr("orgName",orgText);
			$("#iframe_body").attr('src',action);
			$("#153").css("background-color","#0072bb");
			$("#hint").show();
		}
	}
	/* jQuery(function($) {
		$(".cost-nav li").click(function(){
			if($(this).hasClass("cost-nav-click")===true){
				return true;
			}
			$(this).parent().children().removeClass("cost-nav-click").css("background-color","#cbcbcb");
			$(this).addClass("cost-nav-click").css("background-color","#0072bb");
			if($(this).parent().attr("id")==="meumTable"){
				if($(this).text()==="商业四级计划"){
					window.location.href="${ctx}/plan/exec-plan!portalforward.action?planTypeCd=24&if_bis=true";
				}else{
					$("#divOrgTab").show();
				}
				str=$(this).attr("href")+"?now_year="+now_year+"&now_month="+now_month;
			}
			if($(this).parent().attr("id")==="orgTab"){
				orgID=$(this).attr("value");
				$("#iframe_body").attr('orgID',$(this).attr("value"));
				$("#iframe_body").attr('orgName',$(this).text());
			}
			isLoad(str);
		});
		$("#"+orgID).addClass("cost-nav-click");
		if(getURLType('type')=="2"){
			isLoad("${ctx}/plan/bis-plan!center.action");
			$("#centerTask").css("background-color","#0072bb");
			$("#meumTable").children().removeClass("cost-nav-click");
			$("#meumTable").children()[1].className="cost-nav-click";
		}else{
			$("#centerWork").css("background-color","#0072bb");
			isLoad("${ctx}/plan/bis-plan!work.action");
		}
	}); */
	$(function() {
		$(".cost-nav li").click(function(){
			if($(this).hasClass("cost-nav-click")===true){
				return true;
			}
			$(this).parent().children().removeClass("cost-nav-click").css("background-color","#cbcbcb");
			$(this).addClass("cost-nav-click").css("background-color","#0072bb");
			if($(this).parent().attr("id")==="meumTable"){
				if($(this).text()==="商业四级计划"){
					window.location.href="${ctx}/plan/exec-plan!portalforward.action?planTypeCd=24&if_bis=true";
				}else{
					$("#divOrgTab").show();
					if("centerWork"==$(this).attr("id")){
						//如果中心工作计划
						showPlanTarget();
					}else{
						//中心内部任务
						showCenterTarget();
					}
				}
				
			}else{
				orgID=$(this).attr("id");
				if("cost-nav-click"==$("#centerWork").attr("class")){
					showPlanTarget();
				}else{
					showCenterTarget();
				}
				
			}
			
		});
		$("#"+orgID).click();
	});
	//展示商业月计划功能
    function showPlanTarget(){
    	if(orgID){
    		param={currentCenterCd:orgID,currentPlanYear:now_year,currentPlanMonth:now_month,orderBy:orderBy,order:order};
			$.post("${ctx}/plan/plan-target!bis.action",param,function(r){
				$("#work_body").html(r);
				autoHeight();
				//初始化initMonthListByBis();
				// 排序处理
				var tds = $(".mainTable >thead >tr >td");
				// 中心计划类型选择
				$(".selectType").hide();
				$(".editPlanType").bind("click", typeShow);
				$(".editPlanType").bind("blur", typeHide);
				$(".selectType").bind("mouseover", function() {$(".editPlanType").unbind("blur");});
				$(".selectType").bind("mouseout", function() {$(".editPlanType").bind("blur", typeHide);});
				var lis = $(".selectType >ul >li");
				//lis.bind("click", function() {});
				lis.bind("mouseover", function() {$(this).css("background-color", "#CBCBCB");$(this).css("color", "#FFFFFF");});
				lis.bind("mouseout", function() {$(this).css("background-color", "#FFFFFF");$(this).css("color", "#000000");});
				//初始化结束
				var subPlanNumber = $(".mainTable >tbody >tr td span[class=subPlanNumber]");
				for(var i=0; i<subPlanNumber.size(); i++) {
					var sub = subPlanNumber.eq(i).html();
					subPlanNumber.eq(i).html(sub.split("-")[sub.split("-").length - 1]);
				}
				//中心工作计划的年月
				$("#year").val(now_year);
				$("#month").val(now_month);
				TB_removeMaskLayer();
				var queryParam = {
						centerCd :orgID,
						now_year : now_year,
						now_month : now_month,
						org_name:"",
						if_in_weight : -1,
						if_goto_cost : 0,
						nowResOrgNames:$("#"+orgID).text(),
						"page.pageNo" : 1,
						"page.pageSize" : 999
				};
				var url ="${ctx}/plan/exec-plan!levelFour.action";
				$.post(url,queryParam,function(result){
					$("#levelFour_body").html(result);
					$("#levelFour_body").show();
					autoHeight();
				});
			});
    	}
    	
    }
	function showCenterTarget(){
		var param={projectCd:orgID};
		$.post("${ctx}/plan/bis-plan!center.action",param,function(r){
			$("#work_body").html(r);
			$("#levelFour_body").html("");
			$("#levelFour_body").hide();
		});
	}
	function setIframeHeigh(iframeID){
		var iframe = document.getElementById(iframeID) ;
		if(iframe)
		try{
			var iframebody=iframe.contentWindow.document.body;
			var bHeight = 0;
			if(iframebody) bHeight = iframe.contentWindow.document.body.scrollHeight;
			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
			var height = Math.min(bHeight, dHeight);
			iframe.height =  Math.max(height,500);
		}catch (e){}
	}
	//window.setInterval("setIframeHeigh('iframe_body')", 500);
	
	var getURLType=function(type){
		var reg=new RegExp("(^|&)"+type+"=([^&]*)(&|$)","i");
		var r=window.location.search.substr(1).match(reg);
		if(r!=null) return unescape(r[2]);
		return null;
	}
</script>
</head>
<body>
	<div class="title_bar" style="font-weight: bold; padding-left: 5px; font-size: 14px;" >
	商业计划平台
	<div style="float: right;padding-top:5px;">
	  <input type="button" class="btn_green" onclick="window.open('${ctx}/plan/bis-plan!portal.action')" value="全屏" />
	</div>
	</div>
	<div style="clear: left;">
		<ul id="meumTable" class="cost-nav" style="float: left; margin-left: 5px;margin-top:5px;">
			<li class="cost-nav-click" id="centerWork"  href="${ctx}/plan/bis-target!bis.action" style="background-color:">中心工作计划</li>
			<li id="centerTask" href="${ctx}/plan/bis-plan!center.action" style="background-color:#cbcbcb;">中心内部任务</li>
			<li href="${ctx}/plan/exec-plan!portal.action?planTypeCd=24&if_bis=true" style="background-color:#cbcbcb;">商业四级计划</li>
		</ul>
		
	</div>
	<div id="divOrgTab" style="clear: left;">
		<ul id="orgTab" class="cost-nav" style="float: left; margin-left: 5px; font-size: 12px;">
		<li id="153" value="153" style="background-color:#cbcbcb;">商业集团</li>

			<c:forEach items="${orgMap}" var="map" >
				<c:forEach var="org" items="${map.value}">
					<c:if test="${org.orgCd!=512}">
						<li id="${org.orgCd}" value="${org.orgCd}" style="background-color:#cbcbcb;">${org.orgName}</li>
					</c:if>
				</c:forEach>
			</c:forEach>
		</ul>
		
	</div>
	<div id="hint" style="padding: 10px; color: red;">&nbsp;请选择中心</div>
<%-- 
	<iframe id="iframe_body"
		name="iframe_work_body" marginheight='0' marginwidth='0' orgName="" orgID=""
		scrolling='no' frameborder="no" border="0" framespacing="0" year="0" month="0" orderBy="0" order="0" orderColMark="0"
		width="100%" height="100%" src="">
	</iframe>
--%>
 <div id="work_body"> </div>
 <div style="padding-top:20px;">&nbsp;&nbsp;</div>
 <div id="levelFour_body"></div>

</body>
</html>

