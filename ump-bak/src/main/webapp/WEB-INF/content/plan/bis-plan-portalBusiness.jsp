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
<title>商业计划平台-->商业四级计划(近期)</title>
<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/plan.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/exec-plan-stat.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/exec-plan.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/userSelection.css" />

<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/js/oa/userSelection.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plan/exec-plan.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>

<script type="text/javascript"><!--
var loading="<tr style='height:1000px; background-color: #ffffff;' ><td style='padding-top: 100px;' valign='top' colspan='8' align='center'><img src='"+_ctx+"/images/loading.gif'/></td></tr>";
var url="${ctx}/plan/exec-plan!portalBusinessList.action";
var	now_year = "<%=DateOperator.formatDate(DateOperator.truncDate(new Date()),"yyyy") %>";
var	now_month = "<%=DateOperator.formatDate(DateOperator.truncDate(new Date()),"MM") %>";
var iframe=$('#iframe_body', parent.document);
function loadData(div_id){
	var orgName = iframe.attr("orgName");
	if(orgName!=""){
		data.nowResOrgNames=orgName;
	}
	data.year=now_year;
	data.month=now_month;

	$(div_id).html(loading).load(url,data,function(){autoHeight();});
}
var data = {
		ifProjectChange : true,
		year:now_year,
		month:now_month,
		nowResOrgNames:"",
		if_bis : true,
		projectCd : 294,
		nowPointLevel: 1,
		nowViewStyle: 2,
		planTypeCd: 24,
		search_status:999
	};


jQuery(function($) {

	if(iframe.attr("month")>0){
		now_month= iframe.attr("month");
	}
	if(iframe.attr("year")>0){
		now_year= iframe.attr("year");
	}

	$("#select_now_year").val(now_year); 
	$("#month"+Number(now_month)).removeClass("month_small").addClass("month_big color_red");

	$(".monrh_query").click(function(){
		if($(this).hasClass("color_red")===true)
			return  true;
		$(".monrh_query").removeClass("month_big color_red").addClass("month_small");
		$(this).removeClass("month_small").addClass("month_big color_red")
		now_month=$(this).text();
		iframe.attr("month",now_month);
		iframe.attr("src","${ctx}/plan/bis-plan!business.action");
		
	});
	$("#select_now_year").change(function(){
		now_year=$(this).val()
		iframe.attr("year",now_year)
		iframe.attr("src","${ctx}/plan/bis-plan!business.action");
		
	});
});
--></script>
</head>
<body>
<table style="width:100%;border-bottom:1px solid #E4E7EC;font-size:14px;color:red;height: 30px;line-height: 30px;" cellpadding="0" cellspacing="0">
	<tr>
		<td style="padding:0px 5px; ">
			<span class="color_red">
				日期：
			</span>
			<span class="color_black">
				<select id="select_now_year" onchange="now_year=$(this).val();centerClick();">
				<c:forEach begin="2011" end="2020" step="1" var="b">
					<option  value="${b}">${b}</option>
				</c:forEach>
				</select>
				年
				<c:forEach begin="1" end="12" step="1" var="b">
				<button id="month${b}" class="monrh_query month_small" >${b}</button>
				</c:forEach>
				月
			 </span>
		</td>
	</tr>
</table>
<div id="list" style="display:block; padding-bottom: 1px solid #aaabb0;background-color: #E4E7EC;">
<table  cellpadding="0" cellspacing="0" style="border-top: 0 none;"  class="table_list bottom_content">
	<thead>
		<tr  style="background-color: #E4E7EC;">
			<td width="30px" >序号</td>
			<td width="120px">项目名称</td>
			<td width="300px">节点名称</td>
			<td width="150px">主责方</td>
			<td width="80px">开始时间</td>
			<td width="80px">完成时间</td>
			<td width="80px">状态</td>
			<td width="80px">实际完成时间</td>
		</tr>
	</thead>
		<c:forEach items="${fetchAllProjects}"  var="fap" varStatus="f" >
		<tbody id="portalBusiness${f.index}"></tbody>
			<script type="text/javascript">
				jQuery(function($) {
					//if("${f.index}"==="1"){
						data.projectCd=${fap.orgCd};
						loadData("#portalBusiness${f.index}");
					//}
				});
			</script>
		</c:forEach>
	</table>
</div>
</body>
</html>

	