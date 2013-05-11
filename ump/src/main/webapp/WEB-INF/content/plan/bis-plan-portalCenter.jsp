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
<title>商业计划平台-->中心内部任务</title>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/planWork.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/userChoose.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"/>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/oa/allAttention.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plan/planWorkCenter.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plan/plan-work-centerAll.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/userChoose.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
<script type="text/javascript">
	uiid="<%=SpringSecurityUtils.getCurrentUiid()%>";
	var nowDate = "<%=DateOperator.formatDate(DateOperator.truncDate(new Date()),"yyyy-MM-dd") %>";	//更新记录后显示更新时间用
	var if_in_center = false;	//是否在中心搜索内，如果在中心内则内容前面显示部门。refreshMain方法用
	var if_chengben = false;	//是否成本，如果是要显示项目的选择框
	var opened_entityid = "${opened_entityid}";	//要打开的记录的id
	var if_in_attention = "${if_in_attention}";	//是否由关注的记录打开
	var page_size = 15; //分页大小，随列表区高度改变而改变
	var IF_DEPT_IN_SELECT = true;	//是否在部门列表内，如果不在，就不能新增
	/* jQuery(function($) {
		myTask = "${myTask}";
		now_year = "${now_year}";
		now_month = "${now_month}";
		now_month_of_year = "${now_month_of_year}";
		centerCd = "${projectCd}";
		var loading="<div style='height:100px'></div><table width='100%'><tr><td align='center'><img src='"+_ctx+"/images/loading.gif'/></td></tr></table>";
		
		var planWorkCenterParam = {
				if_in_attention : false,
				search_orgCd : '155,156,154,157,869,439,512',
				now_year : now_year,
				now_month : now_month,
				now_month_of_year : now_month,
				"page.pageNo" : 1,
				"page.pageSize" : 999,
				centerCd : ''
		};
		function list(){
			//var orgID = $('#iframe_body', parent.document).attr("orgID");
			//var orgID=orgID;
			if(centerCd!=""){
				//planWorkCenterParam.search_orgCd = orgID;
				planWorkCenterParam.centerCd = orgID;
			}
			$("#div_center").html(loading).load("${ctx}/plan/plan-work-center!list.action?fromType=bisPlan&"+decodeURIComponent($.param(planWorkCenterParam)),function(){
				autoHeight();
				parent.now_year = now_year;
				parent.now_month = now_month;
			});
		}
		list();
	}); */
	var now_year = "${now_year}";
	var now_month = "${now_month}";
	var projectCd = "${projectCd}";
	monthClickBis(now_year,now_month);
    function monthClickBis(toYear,toMonth){
    	if(""!=toYear&&null!=toYear){
    		now_year=toYear;
    	}
    	if(""!=toMonth||null!=toMonth){
    		now_month=toMonth;
    	}
    	
    	if("153"==projectCd){
    		centerCd='155,156,154,157,869,439,512';
    	}
    	var param = {
				if_in_attention : false,
				search_orgCd : projectCd,
				now_year : now_year,
				now_month : now_month,
				now_month_of_year : now_month,
				"page.pageNo" : 1,
				"page.pageSize" : 999,
				fromType:'bisPlan'
		};
		$.post("${ctx}/plan/plan-work-center!list.action",param,function(r){
			$("#div_center").html(r);
			$(".month_big").attr("class","month_small");
			$("#month"+now_month).removeClass("month_small");
			$("#month"+now_month).addClass("month_big");
			$("#month"+now_month).addClass("color_red");
		})
    }
//月份的点击
/**
function monthClickBis(toYear,toMonth){
	if(null==toMonth && toYear==now_year){
		return;
	}
	if(null==toYear && toMonth==now_month){
		return;
	}
	if(null!=toYear && 0!= toYear){
		now_year = toYear;
	}
	if(null!=toMonth && 0!= toMonth){
		now_month = toMonth;
	}
	try{
	for(var i=1;i<13;i++){
		$("#month"+i).removeClass("month_big");
		$("#month"+i).removeClass("color_red");
		$("#month"+i).addClass("month_small");
	}
	$("#month"+now_month).removeClass("month_small");
	$("#month"+now_month).addClass("month_big");
	$("#month"+now_month).addClass("color_red");
	}catch(e){}
	opened_entityid = "";
	
	var loading="<div style='height:100px'></div><table width='100%'><tr><td align='center'><img src='"+_ctx+"/images/loading.gif'/></td></tr></table>";
	var planWorkCenterParam = {
			if_in_attention : false,
			search_orgCd : '155,156,154,157,869,439,512',
			now_year : now_year,
			now_month : now_month,
			now_month_of_year : now_month,
			"page.pageNo" : 1,
			"page.pageSize" : 999,
			centerCd : ''
	};
	var orgID = $('#iframe_body', parent.document).attr("orgID");
	if(orgID!=""){
		planWorkCenterParam.search_orgCd = orgID;
		planWorkCenterParam.centerCd = orgID;
	}
	$("#div_center").html(loading).load("${ctx}/plan/plan-work-center!list.action?fromType=bisPlan&"+decodeURIComponent($.param(planWorkCenterParam)),function(){
		autoHeight();
		parent.now_year = now_year;
		parent.now_month = now_month;
	});
}
**/
</script>
</head>
<body>
<table style="width:100%;border-bottom:1px solid #E4E7EC;font-size:14px;color:red;height: 30px;line-height: 30px;" cellpadding="0" cellspacing="0">
	<tr>
		<td style="padding:0px 5px; ">
			<span class="color_red">
				日期：
			</span>
			<span class="color_black">
					<select id="select_now_year" onchange="monthClickBis($(this).val(),'');">
						<option value="2011" <s:if test="now_year==2011">selected</s:if>>2011</option>
						<option value="2012" <s:if test="now_year==2012">selected</s:if>>2012</option>
						<option value="2013" <s:if test="now_year==2013">selected</s:if>>2013</option>
						<option value="2014" <s:if test="now_year==2014">selected</s:if>>2014</option>
						<option value="2015" <s:if test="now_year==2015">selected</s:if>>2015</option>
						<option value="2016" <s:if test="now_year==2016">selected</s:if>>2016</option>
						<option value="2017" <s:if test="now_year==2017">selected</s:if>>2017</option>
						<option value="2018" <s:if test="now_year==2018">selected</s:if>>2018</option>
						<option value="2019" <s:if test="now_year==2019">selected</s:if>>2019</option>
						<option value="2020" <s:if test="now_year==2020">selected</s:if>>2020</option>
					</select>
					年
					<button id="month1" onClick="monthClickBis('',1)" <s:if test="now_month==1">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>1</button><button id="month2"  onClick="monthClickBis('',2)"  <s:if test="now_month==2">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>2</button><button id="month3"  onClick="monthClickBis('',3)"  <s:if test="now_month==3">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>3</button><button id="month4"  onClick="monthClickBis('',4)"  <s:if test="now_month==4">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>4</button><button id="month5"  onClick="monthClickBis('',5)"  <s:if test="now_month==5">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>5</button><button id="month6"  onClick="monthClickBis('',6)"  <s:if test="now_month==6">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>6</button><button id="month7"  onClick="monthClickBis('',7)"  <s:if test="now_month==7">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>7</button><button id="month8"  onClick="monthClickBis('',8)"  <s:if test="now_month==8">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>8</button><button id="month9"  onClick="monthClickBis('',9)"  <s:if test="now_month==9">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>9</button><button id="month10" onClick="monthClickBis('',10)" <s:if test="now_month==10">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>10</button><button id="month11" onClick="monthClickBis('',11)" <s:if test="now_month==11">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>11</button><button id="month12" onClick="monthClickBis('',12)" <s:if test="now_month==12">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>12</button>
					月
			 </span>
		</td>
		<td align="right"><input type="button" id="NewBtn_cutline" class="button_new" style="display:none;float:right;margin-top:0; margin-right:5px; padding-top:0;" onclick="newSchedule();" value="新建计划" /></td>
	</tr>
</table>
<div id="div_center" ></div>

</body>

</html>

