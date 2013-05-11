<%@page import="com.hhz.ump.util.DateUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ page language="java" import="com.hhz.ump.util.DictContants;" %>

<title>营业外收入</title>

<script type="text/javascript">
var _ctx = '${ctx}';
var isProjectBusinessCompany = "${isProjectBusinessCompany}";
</script>

 <link href="${ctx}/resources/css/mes/thickbox.css" media="screen" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-project.css" />

<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis.fact.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"  />
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/ConvertUtil.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/formValidator/formValidator.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
<script type="text/javascript" charset="UTF-8" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.fact.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<script src="${ctx}/resources/js/mes/jquery-powerdesk.js" type="text/javascript"></script>

<script src="${ctx}/resources/js/common/MaskLayer.js" type="text/javascript"></script>

<style>
/*
body{

overflow-x: auto !important;
}*/
</style>
</head>
<body >	
<form action="${ctx}/bis/bis-pay!search.action" id="searchForm" method="post">
		<div id="header">
			<div class="title_bar">
				<h2>商业收费明细 </h2>
				<div class="left">&nbsp;&nbsp;&nbsp;&nbsp;项目：
					 <input type="text" readonly="true" id="bisProjectName" name="bisProjectName" value="${bisProjectName}" style="cursor: pointer; color: #ff0000;" />
						 <input class="search" type="hidden" id="bisProjectId" name="bisProjectId" value="${bisProjectId}" />
				</div>
            <div style="float:right; height:26px; margin-right:5px; margin-top:6px;">
                <div class="btn_green" onclick="clkFullScreen('${bisProjectId}');">全屏</div>
            </div>
			</div>
			<div class="bis_search" id="main_search_div">
				<!-- //营业外收入 -->
				<table class="tb_search" style="margin-left: 5px;">
					
					<tr class="no_advances_dime">
					 	
						<td align="right" width="60" style="font-size:13px;font-weight:bold;">月份：</td>
						<td width="50">
							<input  type="hidden" id="curMonthStr"  name="curMonthStr"/>
							
							<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM'});" id="monthStrQuery" style="width: 70px;margin-bottom:2px;" class="Wdate"></input>
						</td>
						<td style="padding-left: 5px;"><input id="btn_search"
							type="button" style="width: 65px;margin-top:2px;" class="btn_blue"
							onclick="searchPay();" value="搜索" />
							<input type="button" style="width: 65px" class="btn_blue" onclick="add();" value="新增" /></td>
						</tr>
				</table>
			</div>
			<div class="bis_search" id="main_search_div1"
				style="height: 30px; background: white; border: 0px; margin-bottom: 9px;">
				<table style="width: 100%">
					<tr>
						<td colspan="12">
							<ul id="bis_rpt" style="margin-left: 4px; list-style-type: none;">							
								<li class="bis_fact_unclick" value="2"  id="must">应收明细</li>
								<li class="bis_fact_unclick" value="3" ctx="${ctx}"  id="overdue">欠费明细</li>
								<li class="bis_fact_unclick" value="1" ctx="${ctx}"   id="fact">收费历史记录</li>
								<li class="bis_fact_unclick" value="4" ctx="${ctx}"  id="advances">预收明细</li>
								<!--<li   value="5" id="payNoti" >缴费通知</li>-->
								<li class="bis_fact_unclick" value="6" ctx="${ctx}"  id="payIncome">营业外收入</li>
								<li class="bis_fact_unclick" value="7" ctx="${ctx}" class="bis_fact_click"  id="budget">经营预算明细</li>
								<li class="bis_fact_unclick" value="9" ctx="${ctx}" class="bis_fact_click"  id="gysfRecord">公寓收费记录</li>
								<input type="hidden" id="dimension" name="dimension" value="${dimension }" />
							</ul> <span class="fact_operaTip" id="factoptip"></span>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	
	<div id="addContent"
		style="display: none; clear: both;  margin: 0px; padding: 5px; border-bottom: 1px solid #dddbdc; border-top: 2px solid #dddbdc; ">
	</div>

	<div id="welcom" style="clear: both; height: 10px; width: 80%;">
		<div style="color: #6BAD42; font-size: 16px; font-weight: bold; width: auto;text-align: center;">
			&nbsp;&nbsp;</div>
	</div>
	
	<div id="detailPanel" style="padding: 5px;">
		<div id="detailFor" style="clear: both;"></div>
	</div>
	
<script type="text/javascript">
	var uiid = '<%=SpringSecurityUtils.getCurrentUiid()%>';
	var currProjectId = '${bisProjectId}';
	var currProjectName = '${bisProjectName}';
	var dimension = '${dimension}';
	var year = '${factYear}';
	var searchData;
	$(function() {
		init();
		searchPay();	
		bindMenuEvent();
		var date = new Date(); //日期对象
		var month=(date.getMonth()+1);
		var now = "";
		now = date.getFullYear()+"-"; //读英文就行了
		now = now + (month>9?month:"0"+month);
		$('#monthStrQuery').val(now);
	});
	//新增预算
	function add(){
        //需要把之前弹出窗体中的数据清掉，否则统计数据会出错
		$("#modBisBudget").html("");
		var currentMonth = $("#currentMonth").val().replace("-","");
		var monthStr = $("#monthStrQuery").val().replace("-","");
		//alert("currentMonth: "+currentMonth +" ,monthStr: "+monthStr);
		//alert(parseInt(monthStr)==parseInt(currentMonth));
		//已经存在当前月份数据,则不能新增操作
		if(parseInt(monthStr)==parseInt(currentMonth)){
			alert("您的列表中已经存在当前月份数据!");
			return;
		}
		if($('#addContent').css('display')=='none'){
			var url = _ctx+'/bis/bis-budget!input.action';
			loadSearchParam();
			$.post(url,{},function(result){
				$('#addContent').html(result).show();
                $("#monthStr").val($('#monthStrQuery').val());
				$("#bisBudgetInputForm").find("input").change(function(){
					formatVal($(this));	
					counterMoney($(this));
				});
			});
		}else{
			$('#addContent').html('').hide();
		}
	}

/***
 * 判断当前日期预是否已创建
 */
function isExistBisBudget(){

	if($('#monthStrQuery').val()==""){
        alert("请先选择要创建月度预算的月份！");
        $('#monthStrQuery').focus();
        return;
		}
	var url = _ctx+'/bis/bis-budget!isExistBisBudget.action';
	loadSearchParam();
	$.post(url,searchData,function(result){
		if(result!="true"){
			saveBudget();
			}else{
              alert("当月月度预算已被创建，如需修改请点击下列搜索记录进行编辑！");
				}
	});
	
}
    /**
           初始化参数
    **/
	function loadSearchParam(){
		var bisProjectId = $('#bisProjectId').val();	
		var monthStr = $('#monthStrQuery').val();
		searchData = {bisProjectId:bisProjectId,monthStr:monthStr};
	}
	//保存预算
	function saveBudget(){
		if($('#id').val()==""){//新增
			$("#monthStr").val($("#monthStrQuery").val());
			 $("input[name=entity.bisProjectId]").val($("#bisProjectId").val());
		}		

          //提交前先去掉，
		$("#bisBudgetInputForm").find("input").each(function(i,inputDom){	    
             var _inputVal=$.trim($(inputDom).val().replace(/\,/ig,"")); 
             $(inputDom).val(_inputVal);
             
		   });
		$("#bisBudgetInputForm").ajaxSubmit(function(result) {
			  if(result.indexOf("true")>-1){
				  alert("操作成功！");

				  var curMonth=$("#monthStrQuery").val();
					$("#monthStrQuery").val("");				
					  resetAndHide();
					  searchPay();
					  $("#monthStrQuery").val(curMonth);				
				  }else{					
					  alert("操作失败");
			       }

			});
	}


	/**
	 * 分页搜索
	 * @param pageNo
	 */
	function jumpPage(pageNo){

		if (typeof(pageNo) == 'undefined' || (pageNo == '')) {
			$("#pageNo").val(1);
		} else {
			$("#pageNo").val(pageNo);
		}
		searchPay();
		
	}

/***
 * 搜索预算
 */
function searchPay(){
	
	//标志位：等待加载页面之后，再调整页面高度
	var su = '0';
	var currUrl=_ctx+"/bis/bis-budget!list.action";
	var _pageNo=$("#pageNo").val();
	if(_pageNo=="undefined" || _pageNo==undefined){
		_pageNo=1;
}
    data={ bisProjectId:$('#bisProjectId').val(),monthStr: $('#monthStrQuery').val(),pageNo:_pageNo}
	$.post(currUrl,data,function(result){
		if(result.indexOf('rror')>0){
			result.replace('error','');
			alert("搜索条件不正确");
			return;
		} 
		$('#detailFor').html(result).show().unmask();
		$('#main_search_div1').unmask();
		su='1';
		if(su=='1')
			adjustHeight();
	});
	
}
	/**
	 * 自动统计计算资金明细
	    dom:输入的值
	 */
   function	counterMoney(dom){

	   var _saveDom=$(dom).attr("countField");
	   if(!_saveDom){
             return;
		   }	 

	   var _totalMoney=0.0;
	   //计算方式，+:求和,-:求差
	   var _counterMethod="+";
	   	  _counterMethod=$("input[name="+_saveDom+"]").attr("counterMethod");
	   if(_counterMethod=="-"){//求差
		   //利润计算公式:收入-支出
		   	var _increaseFTotal=$("input[name=entity.incomeTotal]").val();//收入
		   if(String.isNotEmpty(_increaseFTotal)){
			   _totalMoney=parseFloat(_increaseFTotal);
			   }
           var _decreaseF=$("input[name=entity.payTotal]").val();//支出
		   if(String.isNotEmpty(_decreaseF)){
			   _totalMoney-=parseFloat(_decreaseF);//收-支
			   }
		   if(_totalMoney<0){
			   $("span[name=entity.profit]").removeClass().addClass("overspend");

			   $("input[name="+_saveDom+"]").addClass("overspend");
			   }else{
				   $("span[name=entity.profit]").removeClass();
				   $("input[name="+_saveDom+"]").removeClass("overspend");
				   }
		   
		   }else{//求和
		   $("input[countField="+_saveDom+"]").each(function(i,inputDom){	    
	             var _inputVal=$.trim($(inputDom).val().replace(/\,/ig,"")); 
	             if(String.isNotEmpty(_inputVal)){
	            	 _totalMoney+=parseFloat(_inputVal);
	                 }
	             
			   });
	   }
	   $("input[name="+_saveDom+"]").val((_totalMoney).toFixed(2));	 
	   counterMoney($("input[name="+_saveDom+"]"));
		
}
/**
 * 修改预算名细
 @param monthStr:日期
 @param bisBudgetId:预算ID
 */
function modBisBudget(monthStr,bisBudgetId){
	//修改ymPrompt控件中的按钮颜色和间隔
	//$("#ymPrompt_btn_0").removeClass("btnStyle handler");
	//$("#ymPrompt_btn_1").removeClass("btnStyle handler");
	//$("#ymPrompt_btn_0").addClass("handler btn_blue");
	//$("#ymPrompt_btn_1").addClass("handler btn_red");
	resetAndHide();
	var url = _ctx+'/bis/bis-budget!input.action';
	ymPrompt.params={};
	ymPrompt.params.monthStr=monthStr;
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:true,
		message : "<div id='modBisBudget'><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
		width : 790,
		height :500,
		title : "修改月度预算(万元)",
		afterShow : function() {
			var bisProjectId = $('#bisProjectId').val();	
			var monthStr =ymPrompt.params.monthStr;
			searchData = {bisProjectId:bisProjectId,monthStr:monthStr};
			$.post(url, searchData, function(result) {
				$("#modBisBudget").html(result);
				$($(".ym-btn .btnStyle")[1]).before("<input type='hidden' value='删除' class='handler btn_red' style='cursor:pointer' onclick='delBudget()'>&nbsp;&nbsp;");
				
				$("#tip").remove();
				$("#modBisBudget .btn_red").remove();	  
				$("#modBisBudget .btn_green").remove();	    
				$("#bisBudgetInputForm").find("input").change(function(){
					formatVal($(this));	
					counterMoney($(this));
				});
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				saveBudget();
			}
			ymPrompt.close();
		},
		closeBtn:true
		
	});
    
}
/**
 * 取消新增
 */
function resetAndHide(){
	$('#addContent').html('').hide();
}

try{
    (1).toFixed(1);   //   ie5不支持此方法
}
catch(e)   {
    Number.prototype.toFixed   =   function(dot)   {   //   所以要在这里定义
        with(Math){
            var   m=pow(10,Number(dot));
            var   s   =   (round(this*m)/m).toString();
        }
        if(s.indexOf( '. ')   <   0)
              s   +=   ". ";
        s   +=   "000000000000 ";
        return   s.substr(0,s.indexOf( '. ')+dot+1)+ "a ";
    }
}

/**
 * 删除月度预算
 */
function delBudget (){

if(!confirm("你确定要删除当前月度预算吗？")){

return;
	
}
var delData={id:$("#id").val()};
var url= _ctx+'/bis/bis-budget!delete.action';
$.post(url, delData, function(result) {

	var resultMsg=""+result;
	if(resultMsg.indexOf("true")>-1){
		var curMonth=$("#monthStrQuery").val();
		$("#monthStrQuery").val("");
		  ymPrompt.close();
		  resetAndHide();
		  searchPay();
		  $("#monthStrQuery").val(curMonth);
		}else{
			alert("删除失败!");
		}
	
});

	
}

</script>
</body>
</html>
