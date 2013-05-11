<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@ include file="/common/global.jsp"%>
<style type="text/css">
.back_gray1{
	background-color: #D9D9D9;
}
.back_gray2{
	background-color: #F2F2F2;
}
.back_blue{
	background-color: #C6D9F1;
}
.back_none{
	background-color: none;
}
.del_btu_h{
	width:55px;height: 30px;margin-left: 5px;visibility: hidden;
}
</style>

<input type="hidden" id="year" value="${year}"/>
<input type="hidden" id="month" value="${month}"/>
<table class="decTable" style="width: 98%;height: 100%; margin-left: 10px;" border="1">
	<col width="1%" />
	<col width="9%" />
	<col width="10%" />
	<col width="10%" />
	<col width="10%" />
	<col width="10%" />
	<col width="10%" />
	<col width="10%" />
	<col width="10%" />
	<col width="10%" />
	<col width="10%" />
	<thead>
		<tr class="back_gray1">
			<th colspan="2" style="background-color: #FFFFFF;border-width: 2px;border-right-style: solid;">&nbsp;</th>
			<th colspan="2">月度</th>
			<th colspan="2">年度</th>
			<th colspan="2">存货</th>
			<th>认购未签约</th>
			<th colspan="2">欠款</th>
		</tr>
		<tr style="border-bottom-style: solid;border-width: 2px;" class="back_gray2">
			<th colspan="2" style="background-color: #FFFFFF;border-width: 2px;border-right-style: solid;border-top-style: hidden;">
				<div align="right">
				   	<button id="sell_addPro" type="button" class="blue" onclick="showProDiv();" 
				   	 		onmouseover="bindBlur('over');" onmouseout="bindBlur('out');"
				   	        style="width:101px;height:30px;float: right;background-color: #2D8BEF;">加入项目</button>
			 		<div id="selectPro" style="display: none;float:right;" align="right" onmouseover="bindBlur('over');" onmouseout="bindBlur('out');">
						<div id="pro_input" style='clear: both;'></div>
					</div>
				</div>
			</th>
			<th>签约</th>
			<th>回款</th>
			<th>签约</th>
			<th>回款</th>
			<th>可售</th>
			<th>销控</th>
			<th>&nbsp;</th>
			<th>按揭</th>
			<th>其他</th>
		</tr>
	</thead>
	<tbody id="_tbody">
		<s:iterator value="bisSellConditionVoList" status="sta">
			<tr id="list_tr<s:property value="#sta.index"/>" 
			    class="edit_tr" 
			    value="${bisProjectId}"
			    onmouseover="mouseOver('<s:property value="#sta.index"/>');" 
			    onmouseout="mouseOut('<s:property value="#sta.index"/>');">
			    
				<td style="border-right-style:hidden;" class="del_td">
					<button id="delBtu<s:property value="#sta.index"/>" 
					        onclick="romoveTr('<s:property value="#sta.index"/>','<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId"))%>');" 
					        class="red min del_btu_h" 
					        type="button" >删除</button>
				</td>
				<td class="pd-chill-tip" style="text-align: right;font-weight:bolder; padding-right: 8px;border-right-style:solid;border-right-width: 2px;"
					title="<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId"))%>">
					<input type="hidden" id="status<s:property value="#sta.index"/>" value="${statusFlg}" />
					<input type="hidden" id="updator<s:property value="#sta.index"/>" value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("updator"))%>" />
					<input type="hidden" id="updatedDate<s:property value="#sta.index"/>" value="<s:date name="updatedDate" format="yyyy-MM-dd"/>" />
					<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId"))%>
				</td>
				<td class="pd-chill-tip back_gray2 click_td" 
				    title="<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId"))%>_本月_签约<br/>
				    		${signMoneySjMonth}万/计划${signMoneyJhMonth}万(${monthQyPercent})" >
					${signMoneySjMonth }
				</td>
				<td class="pd-chill-tip click_td" 
				    title="<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId"))%>_本月_回款<br/>
				          	${returnMoneySjMonth}万/计划${returnMoneyJhMonth}万(${monthHkPercent})">
					${returnMoneySjMonth }
				</td>
				<td class="pd-chill-tip back_gray2 click_td" 
				    title="<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId"))%>_年度_签约<br/>
				           ${signMoneySjYear}万/计划${signMoneyJhYear}万(${yearQyPercent})" >
					${signMoneySjYear }
				</td>
				<td class="pd-chill-tip click_td" 
				    title="<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId"))%>_年度_签约<br/>
				           ${returnMoneySjYear}万/计划${returnMoneyJhYear}万(${yearHkPercent})">
					${returnMoneySjYear }
				</td>
				<td class="pd-chill-tip click_td" 
				    title="<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId"))%>_存货_可售<br/>
				           ${dsMoney}万/${dsSuiteNum}套/${dsArea}平米">
					${dsMoney }
				</td>
				<td class="pd-chill-tip click_td" 
				    title="<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId"))%>_存货_销控<br/>
				           ${xkMoney}万/${xkSuiteNum}套/${xkArea}平米">
					${xkMoney }
				</td>
				<td class="pd-chill-tip click_td" 
				    title="<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId"))%>_认购未签约<br/>${notSignMoney}万/${notSignSuiteNum}套">
					${notSignMoney }
				</td>
				<td class="pd-chill-tip click_td" 
				    title="<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId"))%>_欠款_按揭<br/>${mortgageArrears}万">
					${mortgageArrears }
				</td>
				<td class="pd-chill-tip click_td" 
				    title="<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId"))%>_欠款_其他<br/>${otherArrears}万">
					${otherArrears }
				</td>
			</tr>
		</s:iterator>
		
	</tbody>
</table>
<script type="text/javascript">
var _style;
var ind = 0;
$(function(){
	if(ind==0){
		loadCountTr();
	}
	ind++;
});
//清空
function clearPro(type){
	$("#pro_input"+type).find(":input").each(function(i){
		if("btnGreen" != $(this).attr("class")){
			$(this).removeClass('btnSelect').addClass('btnCancel');
		}
	});
	$("#bisFloorId"+type).val('');
	$("#clearBtu"+type).hide();
	$("#selectAllBtu"+type).show();
}
function showProDiv(){
	if($("#selectPro").find("input").length > 0){
		if($("#selectPro").is(":hidden")){
			$("#selectPro").show();
		}else{
			$("#selectPro").hide();
		}
	}
}
//添加项目
function addPro(index){
	$("#pro_input #pri_input_"+index).remove();
	$("#list_tr"+index).show();
	$("#list_tr"+index).addClass("edit_tr");
	//重新排列项目
	$("#pro_input").find("input").each(function(i){
		var tmpInput = $(this).clone();
		$(this).remove();
		if(!(i % 3)){
			$("#pro_input").append("<div style='clear: both;'></div>");
		}
		$("#pro_input").append(tmpInput);
	});
	$("#selectPro").hide();
	loadCountTr();
}
//删除项目
function romoveTr(index,bisProjectName){
	$("#list_tr"+index).hide();
	$("#list_tr"+index).removeClass("edit_tr");
	var input_count = 0;
	$("#pro_input").find("input").each(function(i){
		//重新排列项目
		var tmpInput = $(this).clone();
		$(this).remove();
		if(!(i % 3)){
			$("#pro_input").append("<div style='clear: both;'></div>");
		}
		$("#pro_input").append(tmpInput);
		input_count++;
	});
	if(!(input_count % 3)){
		$("#pro_input").append("<div style='clear: both;'></div>");
	}
	var _input = "<input type='button' class='btnSelect' id='pri_input_"+index+"' value='"+bisProjectName+"' onClick='addPro("+index+")'/>";
	$("#pro_input").append(_input);
	loadCountTr();
}
//加载合计行
function loadCountTr(){
	var projectIds = "";
	$("#_tbody").find(".edit_tr").each(function(i){
		var pId = $(this).attr("value");
		if(pId != null && "" != pId){
			projectIds = projectIds+pId+",";
		}
	});
	var year = $("#year").val();
	var month = $("#month").val();
	var url = _ctx+"/bis/bis-decision!loadCount.action";
	var data = {year:year,month:month,projectIds:projectIds};
	$.post(url,data,function(result){
		$("#_tbody #list_tr_count").remove();
		$("#_tbody").append(result);
		$("#sell_month_qy_count").html($("#month_qy").val());
		$("#sell_year_count").html($("#year_qy").val());
        initTrClick();
        autoHeight();
	});
}

//鼠标进入tr事件
function mouseOver(index){
	$("#list_tr"+index).find("td").each(function(){
		var clas = $(this).attr("class");
		if(clas.indexOf("del_td") > 0){
			$(this).attr("style","border-right-style:hidden;");
		}
		$(this).removeClass("back_none").addClass("back_blue");
	});
	$("#delBtu"+index).attr("style","width:50px;height: 28px;margin-left: 10px;visibility:visible;");
}

//鼠标离开tr事件
function mouseOut(index){
	$("#list_tr"+index).find("td").each(function(){
		var clas = $(this).attr("class");
		if(clas.indexOf("del_td") > 0){
			$(this).attr("style","border-right-style:hidden;");
			$(this).removeClass("back_blue").addClass("back_none");
		}else{
			if(clas.indexOf("back_gray2") > 0){
				$(this).removeClass("back_blue").addClass("back_gray2");
			}else{
				$(this).removeClass("back_blue").addClass("back_none");
			}
		}
	});
	$("#delBtu"+index).attr("style","width:50px;height: 28px;margin-left: 10px;visibility:hidden;");
}

//初始化每个td的点击效果
function initTrClick(){
	$("#_tbody").find(".click_td").click(function(){
		$(this).attr("style","background-color: #548DD4;");
	});
	$("#_tbody").find(".click_td").blur(function(){
		var blurClass = $(this).attr("class");
		if(blurClass != null && "" != blurClass){
			if(blurClass.indexOf("blue_color") > 0){
				$(this).attr("style","background-color: #F2F2F2;");
			}else{
				$(this).attr("style","background-color: none;");
			}
		}else{
			$(this).attr("style","background-color: none;");
		}
	});
}
//控制添加的项目div的显示
function bindBlur(type){
	if('over'== type){
		$("#sell_addPro").unbind();
	}else{
		$("#sell_addPro").bind('blur',function(){
			$("#selectPro").hide();
		});
	}
}
/* function mouseOver2(dom){
	$(dom).attr("style","background-color: #C6D9F1;");
}
function mouseOut2(dom){
	$(dom).attr("style","background-color: #F2F2F2;");
} */

</script>
