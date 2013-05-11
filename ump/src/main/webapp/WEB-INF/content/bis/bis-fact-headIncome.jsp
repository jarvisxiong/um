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
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-project.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis.fact.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"  />
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/formValidator/formValidator.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/ConvertUtil.js" ></script>
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<script type="text/javascript" charset="UTF-8" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js" ></script>

<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
</head>
<body >
	<form action="${ctx}/bis/bis-pay!search.action" id="searchForm" method="post">

		<div id="header">
			<div class="title_bar">
				<h2>商业收费明细 </h2>
				<div class="left">&nbsp;&nbsp;&nbsp;&nbsp;项目：
					 <input type="text" id="bisProjectName" readonly="true" name="bisProjectName" value="${bisProjectName}" style="cursor: pointer; color: #ff0000;" />
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
					 	<input  type="hidden" id="chargeTypeCd"  name="chargeTypeCd" value="B29" />
						<td align="right" width="30">年：</td>
						<td width="60"><s:select
								list="@com.hhz.ump.util.DictMapUtil@getMapYear()"
								listKey="key" listValue="value" name="year" id="year"></s:select>
						</td>
						<td align="right" width="30">月：</td>
						<td width="50"><s:select cssStyle="width:100%;"
								validate="required"
								list="@com.hhz.ump.util.DictMapUtil@getMapMonth()"
								listKey="key" listValue="value" name="month" id="month"></s:select>
						</td>
						<td style="padding-left: 5px;"><input id="btn_search"
							type="button" style="width: 65px" class="btn_blue"
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
								<li class="bis_fact_unclick" value="2" ctx="${ctx}"  id="must">应收明细</li>
								<li class="bis_fact_unclick" value="3" ctx="${ctx}"  id="overdue">欠费明细</li>
								<li class="bis_fact_unclick" value="1" ctx="${ctx}"   id="fact">收费历史记录</li>
								<li class="bis_fact_unclick" value="4" ctx="${ctx}"  id="advances">预收明细</li>
								<!--<li   value="5" id="payNoti" >缴费通知</li>-->
								<li class="bis_fact_unclick" value="6" ctx="${ctx}"  id="payIncome">营业外收入</li>
								<li class="bis_fact_unclick" value="7" ctx="${ctx}"  id="budget">经营预算明细</li>
								<li class="bis_fact_unclick" value="9" ctx="${ctx}"  id="gysfRecord">公寓收费记录</li>
								<input type="hidden" id="dimension" name="dimension" value="${dimension }" />
							</ul>
							<span id="pageHtml" style="margin-left:10px;"></span>
							<span class="fact_operaTip" id="factoptip"></span>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	
	<div id="addContent"
		style="display: none; clear: both;  margin: 0px; padding: 5px; border-bottom: 1px solid #dddbdc; background: #f7f7f7;">
	</div>

	<div id="welcom" style="clear: both; height: 30px; width: 80%">
		<div style="color: #6BAD42; font-size: 16px; font-weight: bold; width: auto; margin-top: 200px; text-align: center;">
			请选择搜索条件</div>
	</div>
	
	<div id="detailPanel" style="clear: both; width: 100%">
		<div id="detailFor" style="clear: both;"></div>
	</div>
	
	<script type="text/javascript" src="${ctx}/resources/js/bis/bis.fact.js"></script>
	<script type="text/javascript">

  	$.ajaxSetup({cache:false});
		var uiid = '<%=SpringSecurityUtils.getCurrentUiid()%>';
		layOutCdStore='<%=DictContants.BIS_CONT_TYPE_STORE%>';
		layOutCdFlat='<%=DictContants.BIS_CONT_TYPE_FLAT%>';
		layOutCdMulti='<%=DictContants.BIS_CONT_TYPE_MULTI%>';
		chargeType02='14';
		chargeType03='12';
		chargeType38='13';
		var currProjectId = '${bisProjectId}';
		var currProjectName = '${bisProjectName}';
		var bisTenantId = '${bisTenantId}';
		var bisTenantName = '${currDetailName}';
		var dimension = '${dimension}';
		var year = '${factYear}';
		//存储租户、公寓、多径等id的控件
		var currLayoutLabel='layOutCdList_v0';
		$(function() {
			init();
			$('#bisProjectId').val(currProjectId);

			$('#welcom').show();
			bindMenuEvent();
			//加载搜索列表
			searchPay();
		});
		function searchPay(){
			var ctx= _ctx
			$('#welcom').hide();
			$('#pageNo').val('');
			$('#addContent').hide();
			$("#searchForm").attr('action',_ctx+'/bis/bis-pay!search.action');
			$("#searchForm").ajaxSubmit(function(result) {
				$("#detailPanel").html(result).show(); 
				_ctx=ctx;
			});
		}
		
		var searchData;
		function loadSearchParam(){
			var bisProjectId = $('#bisProjectId').val();
			var chargeTypeCd = $('#chargeTypeCd').val();
			var year = $('#year').val();
			var month = $('#month').val();
			searchData = {bisProjectId:bisProjectId,chargeTypeCd:chargeTypeCd,year:year,month:month};
		}
		function add(){
			if($('#addContent').css('display')=='none'){
				var url = _ctx+'/bis/bis-pay!income.action';
				loadSearchParam();
				$.post(url,searchData,function(result){
					$('#addContent').html(result).show();
					$('#bisProjectNameInput').onSelect({muti:false});
				});
			}else{
				$('#addContent').html('').hide();
			}
			ymPrompt.close();
		}
	
		function addPay(){
			
			var bisProjectId = $('#bisProjectIdInput').val();
			var year= $('#yearInput').val();
			var month =$('#monthInput').val();
			var chargeTypeCd = $('#chargeTypeCdInput').val();
			//var budgetMoney =$('#budgetMoneyInput').val();
			var money =$('#moneyInput').val();
			if(bisProjectId==''||year==''||month==''||chargeTypeCd==''){
				alert('必填项不能为空');
				return;
			}
			if(money==''){//budgetMoney==''
				alert('实际收入不能都为空');
				return;
			}
			$("#save").attr('action',_ctx+'/bis/bis-pay!save.action');
			$("#save").ajaxSubmit(function(result) {
				ymPrompt.close();
				if(result=='success'){
					$('#tip').html('保存成功').show().fadeOut(2000);
					
					searchPay();
				}
			});
		}

		function edit(id){
			$('#addContent').html('').hide();
			var url = _ctx+'/bis/bis-pay!income.action';
			ymPrompt.confirmInfo( {
				icoCls : "",
				autoClose:false,
				message : "<div id='selectTypeDiv'><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
				width : 670,
				height :225,
				title : "修改",
				afterShow : function() {
					$.post(url, {id:id}, function(result) {
						$("#selectTypeDiv").html(result);
					});
				},
				handler : function(btn){
					ymPrompt.close();
				},
				closebtn:true,
				btn:[]
			});
			
			
		}

		var saveoptions={
		        url:_ctx+'/bis/bis-pay!save.action',
		        success:function(data){
		        	$('#tip').html('保存成功').show().fadeOut(2000);
		        }
			};
			
		function jumpPage(pageNo){
			$('#pageNo').val(pageNo);
			$("#searchForm").attr('action',_ctx+'/bis/bis-pay!search.action');
			$("#searchForm").ajaxSubmit(function(result) {
				$("#detailPanel").html(result).show(); 
			});
		}
		function deletePay(id){
			var url = _ctx+'/bis/bis-pay!delete.action';
			$.post(url,{id:id},function(result){
				if(result=='success'){
					searchPay();
				}else{
					$("#detailPanel").mask('删除失败');
				}
				setTimeout(function(){ 
					$("#detailPanel").unmask();
				},100);
				ymPrompt.close();
			});
		}
	</script>
</body>
</html>
