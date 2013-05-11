<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>临时表</title>
	<%@ include file="/common/global.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
</head>
<body class="easyui-layout">
	<div region="north" title="查询" icon="icon-search" style="padding: 5px; height: 90px;" border="false">
	<form id="searchForm" method="post" onsubmit="return false;" />
	<table style="width: 100%">
		<tr>
			<td>
			账户：<input type="text" name="userId" style="width: 80px !important" />
			 姓名:<input type="text" name="userName" style="width: 80px !important" /> 
			PLAS状态:
			<select name="filter_EQS_Plasstatus" id="filter_EQS_Plasstatus">
				<option value=""></option>
				<option value="0">未启用</option>
				<option value="1">正常</option>
				<option value="2">冻解</option>
				<option value="3">解冻</option>
				<option value="4">销户</option>
			</select>
			 邮箱状态: 
			 <select name="filter_EQS_Mailstatus" id="filter_EQS_Mailstatus">
				<option value=""></option>
				<option value="0">正常</option>
				<option value="4">锁定</option>
				<option value="1">停用</option>
			</select> 
			范围：
			 <select name="searchScope" id="searchScope">
				<option value="">全部</option>
				<option value="0">正常</option>
				<option value="1">非正常</option>
				
			</select> 
			
		</tr>
		<tr>
			<td>
				<a href="#" id="btnSearch" iconCls="icon-search" class="easyui-linkbutton" onclick="customSearch();">查询</a> 
				<a href="#" id="btnClean"iconCls="icon-search" class="easyui-linkbutton" onclick="cleanSearchField();">清空</a>
				<a href="#" id="btnSearch" iconCls="icon-reload" class="easyui-linkbutton" onclick="doSynMailStatus();">同步邮件用户状态</a> 
				<span style="color: red;" id="synMailStatus"></span>
			</td>
		</tr>
	</table>
	</form>
	</div>
	<div region="center" border="false">
	<table id="tt" fit="true" title="邮箱箱用户状态" singleSelect="true" rownumbers="true" idField="plasOperBatchId" url="${ctx}/bis/bis-email-acct-tmp!list.action">
	</table>
	</div>
	<script type="text/javascript">
		$(function() {
			init();
		});
		function viewDetail(id) {
			var url = '${ctx}/plas/bis-sms-tmp!input.action?id=' + id;
			parent.addTab('查看申请', url);
		}
		function init() {		
			$('#tt').datagrid( {
				onDblClickRow : function(rowIndex, row) {
					
				},
				pagination : true,
				pageSize : 30,
				pageList : [ 50, 40, 30, 20, 10 ],
				columns : [ [ {
					field : 'uiid',
					title : '账号',
					sortable : true,
					width : 100
				}, {
					field : 'userName',
					title : '姓名',
					sortable : true,
					width : 180
				}, {
					field : 'plasOrgName',
					title : 'PLAS机构',
					sortable : true,
					width : 180
				}, {
					field : 'plasStatus',
					title : 'PLAS状态',
					sortable : true,
					width : 180,
					align:"center",
					formatter : function(value, row) {
					return buildStatusText(value);
					}
				}, {
					field : 'emailFlg',
					title : '是否开启邮箱',
					sortable : true,
					width : 80,
					align:"center",
					formatter : function(value, row) {
					return buildEmailFlagText(value);
					}
				}, {
					field : 'mailOrgName',
					title : 'CoreMail邮箱机构',
					sortable : true,
					width : 180
				}, {
					field : 'mailStatus',
					title : 'CoreMail邮箱状态',
					sortable : true,
					width : 120,
					align:"center",
					formatter : function(value, row) {
						return buildEmailStatusText(value);
					}
				}
	
				] ],
				onLoadSuccess : function() {
					$('a.easyui-linkbutton').linkbutton();
				}
			});
	        //正常、非正常复选框单点事件
			$(".chbox").click(function(){
	           var selName=$(this).attr("name");
	           if(selName=="normal"){
	                $("input[name=abnormal]").attr("checked",false);
	               }else{
	            	   $("input[name=normal]").attr("checked",false);
	
	                   }
	                      
				});
		}
	
		/***
		 * PLAS状态
		 */
		function buildStatusText(value){
	
			var resultText = value;
			switch (value) {
			case "0":
				resultText="<span style='color:green'>未启用</span>";
				break;
			case "1":
				resultText="<span style='color:blue'>正常</span>";
				break;
			case "2":
				resultText="<span style='color:#FF9900'>冻结</span>";
				break;
			case "3":
				resultText="<span style='color:blue'>解冻 </span>";
				break;
			case "4":
				resultText="<span style='color:red'>销户 </span>";
				break;
			default:
				  resultText="<span>无 </span>";
				break;
	
			}
			return resultText;
			}
	
		/**
		 * 邮箱状态
		 */
		function buildEmailStatusText(value){
	
			var resultText = value;
			switch (value) {
			case "0":
				resultText="<span style='color:blue'>正常</span>";
				break;
			case "4":
				resultText="<span style='color:#FF9900'>锁定</span>";
				break;
			case "1":
				resultText="<span style='color:red'>停用 </span>";
				break;
			default:
				  resultText="<span>无 </span>";
				break;
	
			}
			return resultText;
			}
	/**
	 * 是否开启邮箱功能
	 */
		function buildEmailFlagText(value){
			var resultText = value;
			switch (value) {
			case "0":
				resultText="<span >未开启</span>";
				break;
			case "1":
				resultText="<span style='color:blue'>开启</span>";
				break;
			case "2":
				resultText="<span style='color:red'>开启已停用 </span>";
				break;
			default:
				  resultText="<span>无 </span>";
				break;
	
			}
			return resultText;
			}
		function customSearch() {
			var param = Convert.getJson4Form('searchForm');
			$('#tt').datagrid('options').queryParams = param;
			$('#tt').datagrid('reload');
		}
		//导出
		function exportExcel() {
			$('#searchForm').attr('action', 'bis-sms-tmp!exportExcel.action');
			search();
			$('#searchForm').attr('action', 'bis-sms-tmp');
		}
	/**
	 * 同步邮箱状态
	 */
		function doSynMailStatus() {
			$('body').mask('请稍等，正在同步邮箱用户状态...');
			var url = "${ctx}/bis/bis-email-acct-tmp!input.action";
			$.post(url, function(result) {
				$('body').unmask();
			});
		}
	/**
	 * 清空
	 */
		function cleanSearchField(){
			$("#searchForm input").val("");
			$("#searchForm select").val("");
			}
	
		
		
	</script>
</body>
</html>