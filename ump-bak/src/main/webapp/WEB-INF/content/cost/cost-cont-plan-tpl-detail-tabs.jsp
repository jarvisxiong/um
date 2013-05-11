<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/meta.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>合约规划模版</title>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/jquery-easyui/themes/icon.css"></link>
	<link type="text/css" rel="stylesheet"	href="${ctx}/resources/js/jquery-easyui/themes/gray/easyui.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 	
	<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/resources/css/common/select.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/superTables.css"  />	
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jqueryplugin/jqModal/jqModal.css"/>
	
	<script type="text/javascript">
		var _ctx = '${ctx}';
	</script>
	
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui/jquery.easyui.min.js"></script>
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/superTables.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/cost/cost-cont-plan-tpl-detail.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/cost/cost-cont-plan-tpl-detail-tabs.js"></script>
		
	
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<style type="text/css">
		.tabs{ list-style:none; width:400px; line-height:30px;}
		.tabs  a {
			border: 1px solid #CBCBCB;
		    cursor: pointer;
		    float: left;
		    font-size: 12px;
		    height: 26px;
		    line-height: 26px;
		    margin-bottom: 6px;
		    margin-left: 8px;
		    text-align: center;
		    width: 80px;
		}       
		.tabs  a.current{background-color: #0072BB;color: #FFFFFF;}       
		.tab{ display:block; width:400px; height:300px; border:1px solid #CBCBCB;}  
		
		.fakeContainer {
			border: medium none;
			height: 383px;
			overflow: hidden;
			padding: 0;
			width: 770px;
		}
		
		#MyTable .input1 {
			height: 25px;
			border: 0 none;
			padding-right: 1px;
			width: 100%;
			background: #F7EDE4;
			border: 0 none;
		}
		
		#MyTable select{
			height: 25px;
			border: 0 none;
			padding-right: 1px;
			width: 100%;
		}
		#MyTable input {
		    font-size:13px;
			height: 25px;
			border: 0 none;
			padding-right: 1px;
			text-align: right;
			width: 100%;
		}
		.txtLeft{
			font-size:13px;
			text-align:left!important;
		}
		.txtCenter{
			text-align:center !important;
		}
		.txtRight{
			text-align:right!important;
		}
		.trClicked{
			background:#FFA613;
		}
		.trSelectd{
			background:#EDEFF3
		}
		.operInfo{
		margin-left: 20px; display: inline; color: green;
		}
	</style>
	<title>合约规划模板</title>

</head>
<body>
	<div class="title_bar">
		<div class="fLeft banTitle">	
			合约规划模版
		</div>
		<div class="fRight"> 
			<input type="button" class="btn_new btn_full_new" onclick="window.open(location.href)" value="全屏" />
			<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=window.location.href" value="刷新" />
		</div>
	</div>
	<div class="container">
		<form class="well form-inline clearfix">
			<table >
			   <tr>
				<th><label>模板名称${flag}</label></th>
				<td><input id="tplName" type="text" name="tplName" value="${tplName }" class="input-medium"/></td>
				<th><label>版本</label></th>
				<td><input id="tplVersion" type="text" name="tplVersion" value="${tplVersion }" class="input-medium"/></td>
				<td><input type="button" name="btn" id="btn" title="保存模版" value="保存" onclick="saveTemplate();"/></td>
			  	<td><div id="isSuccess" style="display:none;"></div></td>
			  </tr>
			</table>
		</form>
	</div>
	<input type="hidden" name="costContPlanTplId" id="costContPlanTplId" value="${costContPlanTplId }"/>
	<s:if test="flag==false">
	</s:if>
	<s:else>
		<div class="tabs" id="contractPlan" style="width:100%;">  
		    <a href="#">合约规划模版</a>
		    <a href="#">成本汇总模版</a>
		    <%--操作信息 --%>
			<span style="align:center;" class="operInfo"></span>
		</div>
		<div id="tabs_1" class="tab" title="合约规划模版" style="padding:20px;width:100%;height:70%;">  
	    	<%@ include file="cost-cont-plan-tpl-detail.jsp" %>
	    </div>  
	    <div id="tabs_2" class="tab" title="成本汇总模版" style="overflow:auto;padding:20px;width:100%;height:70%;display:none;">  
	    	<iframe src="${ctx }/cost/cost-cont-plan-tpl-stat!list.action?id=${costContPlanTplId }" width="100%;" height="100%;" scrolling="auto" frameborder="0" name="costContPlanTplStatIframe" id="costContPlanTplStatIframe">
			</iframe>
	    </div> 
	</s:else>
	
</body>
</html>

