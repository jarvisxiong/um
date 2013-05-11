<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/meta.jsp" %> 
<%@ include file="/common/global.jsp"%>
<title>商业计划平台-->中心工作计划中心内部任务</title>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/plan-target.css">
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />

<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>

<script type="text/javascript" src="${ctx}/resources/js/oa/allAttention.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plan/planWork2.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plan/plan-target.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>


<script type="text/javascript">
	var opened_entityid = "${opened_entityid}";	//要打开的记录的id
	var if_in_attention = "${if_in_attention}";	//是否由关注的记录打开
	var page_size = 15; //分页大小，随列表区高度改变而改变
	var IF_DEPT_IN_SELECT = false;	//是否在部门列表内，如果不在，就不能新增
	var loading="<div style='height:100px'></div><table width='100%'><tr><td align='center'><img src='"+_ctx+"/images/loading.gif'/></td></tr></table>";
	var _queryParam;
	
	jQuery(function($) {
		// 年份
		now_year = "${now_year}";
		//月份
		now_month = "${now_month}";
		orderBy="";
		order="";
		col="";
		var centerCd ="";

		if($('#iframe_body', parent.document).attr("month")>0){
			now_month= $('#iframe_body', parent.document).attr("month");
		}
		if($('#iframe_body', parent.document).attr("year")>0){
			now_year= $('#iframe_body', parent.document).attr("year");
		}
		if($('#iframe_body', parent.document).attr("orgId")>0){
			centerCd=$('#iframe_body', parent.document).attr("orgId");
		}
		if($('#iframe_body', parent.document).attr("orderBy")!=0){
			orderBy=$('#iframe_body', parent.document).attr("orderBy");
		}
		if($('#iframe_body', parent.document).attr("order")!=0){
			order=$('#iframe_body', parent.document).attr("order");
		}
		if($('#iframe_body', parent.document).attr("ordercolmark")!=0){
			col=$('#iframe_body', parent.document).attr("ordercolmark");
		}
		//中心内部任务的年月
		$("#select_now_year").val(now_year); 
		$("#month"+Number(now_month)).removeClass("month_small").addClass("month_big color_red");
		
		//搜索条件
		var queryParam = {
				centerCd : centerCd,
				now_year : now_year,
				now_month : now_month,
				org_name:"",
				if_in_weight : -1,
				if_goto_cost : 0,
				nowResOrgNames:"",
				"page.pageNo" : 1,
				"page.pageSize" : 999
		};
		
		$(".monrh_query").click(function(){
			if($(this).hasClass("color_red")===true)
				return  true;
			$(".monrh_query").removeClass("month_big color_red").addClass("month_small");
			$(this).removeClass("month_small").addClass("month_big color_red")
			now_month=$(this).text();
			queryParam.now_month=now_month;
			_queryParam = queryParam;
			$('#iframe_body', parent.document).attr("month",now_month);
			list();
		});
		$("#select_now_year").change(function(){
			now_year=$(this).val()
			queryParam.now_year=now_year;
			_queryParam = queryParam;
			$('#iframe_body', parent.document).attr("year",now_year)
			list();
		});
		function list(){
			TB_showMaskLayer("正在搜索...",5000);
			var orgID = $('#iframe_body', parent.document).attr("orgID");
			if(orgID!=""){
				queryParam.centerCd=orgID;
			}
			var orgName = $('#iframe_body', parent.document).attr("orgName");
			if(orgName!=""){
				queryParam.nowResOrgNames=orgName;
			}
			_queryParam = queryParam;
			param={currentCenterCd:centerCd,currentPlanYear:now_year,currentPlanMonth:now_month,orderBy:orderBy,order:order};
			$.post("${ctx}/plan/plan-target!bis.action",param,function(r){
				$("#cost-container").html(r);
				autoHeight();
				//初始化initMonthListByBis();
				// 排序处理
				var tds = $(".mainTable >thead >tr >td");
				if(""!=col){
					for(var i=0; i<tds.size(); i++) {
						if(tds.eq(i).attr("sortCol") == col) {
							tds.eq(i).children().attr("class", order);
						}
					}
					
				}
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
				var url ="${ctx}/plan/exec-plan!levelFour.action";
				$.post(url,queryParam,function(result){
					$("#levelFour_body").html(result);
					autoHeight();	
				});
			});
    		//$("#iframe_levelFour_body").attr("queryParam",queryParam);
 	//		$("#div_work_list").html(loading).load("${ctx}/plan/plan-target!bis.action",queryParam,function(){
// 				parent.autoHeight();
// 				parent.now_year = now_year;
// 				parent.now_month = now_month;
// 			});
			
			//$("#iframe_levelFour_body").attr("src","${ctx}/plan/bis-plan!levelFour.action");
		};
		list();
	});

	function setIframeHeigh(iframeID){
		var iframe = document.getElementById(iframeID) ;
		if(iframe)
		try{
			var bHeight = iframe.contentWindow.document.body.scrollHeight;
			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
			var height = Math.min(bHeight, dHeight);
			if(iframe.height != Math.max(height,500)){
				iframe.height = Math.max(height,500);
			}
		}catch (ex){}
		parent.autoHeight();
	}
	//window.setInterval("setIframeHeigh('iframe_levelFour_body')", 500);
</script>

</head>
<body>
<div id="cost-container"></div>
<%-- 
<iframe id="iframe_levelFour_body"
		name="iframe_levelFour_body" marginheight='0' marginwidth='0' queryParam=""
		scrolling='no' frameborder="no" border="0" framespacing="0" year="0" month="0"
		width="100%" src="">
	</iframe>
--%>
<div id="levelFour_body"></div>
</body>
</html>

