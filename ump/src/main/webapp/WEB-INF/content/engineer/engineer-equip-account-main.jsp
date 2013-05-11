<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>工程资产台账</title>
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/base.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/assm/style.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/engineer/equip.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"/>
		
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>	
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/engineer/engineer-equip.js?v=new Date()"></script>
	<style type="text/css">
	.short_div{
		 overflow:hidden;
		 text-overflow:ellipsis;
		 word-break:keep-all;
		 white-space:nowrap;
		 margin-left:3px;
		 width: 100px;
	}
	</style>
</head>

<body>

<div id="warp">
<%-- 查看标示，用于左边树点击搜索判断值 --%>
<input type="hidden" id="accountView" value="accountView"/>
<input type="hidden" id="modelView" value="modelView"/>
<%--父节点ID，隐藏显示 --%>
<input type="hidden" name="pratentId" id="gloab_hide_pratentId"/>

<%--顶部栏 --%>
<div id="header">
    <div class="title1 clearfix" >
        <h2 style="width: auto;float: left;">工程资产台帐</h2>
	 	<div class="btns">
	 	<input style="margin-top:3px; padding: 2px;width: 150px;background:#FFF url(/PowerDesk/resources/images/desk2/search.png) no-repeat 136px 1px;color:#cccccc;"
					       title="支持设备编号、名称查询"
					       value="快速搜索..."
					       onkeyup="quickSearchAccount();"
				    	   onclick="clearQuickSearch(this);"
					       onblur="resetQuickSearch(this);" 
					       name="words" 
					       id="words" 
					       class="text"/>
					<input type="hidden" id="account_quick_id"/>
		<button class="gray" id="accountSearchBtu" style="margin-left: 5px;" onclick="showAccountSearchDiv();" type="button">
		高级查询</button>
         </div>
    </div>
</div>

<div style="position: absolute;bottom:0;top:39px;width:100%;overflow:auto;+overflow:visible;">
  	<table style="width:100%;height: 100%;">	
	<tr>
		<td id="leftPanel" style="width:160px;border-right: 1px solid #8c8f94;background-color:#e4e7ec;" valign="top">
			<%-- 左边设备树  --%>
			<table cellpadding="0" cellspacing="0" border="0" style="width: 100%;">
				<tr>
					<td>
						<div id="leftTreePanel" style="height:100%;width:160px;padding-top: 5px; float: left; overflow-y: auto; overflow-x: hidden;border: none;">
						项目加载中							
						</div>
					</td>
					<td style="width:5px;">
						<div id="noteResizeHandler" title="您可以拖动,调整宽度" style="float:right; width:5px;height:100%;background: #eee url('${ctx}/resources/images/common/width_resize.gif') left center no-repeat;cursor: w-resize;">&nbsp;</div>
					</td>
				</tr>
			</table>
		</td>
		<td valign="top">
			<div id="mainDiv">
			  <form id="mainForm" name="mainForm" action="${ctx}/engineer/engineer-equip-account!loadList.action" method="post">
				<input type="hidden" name="searchPosition" id="searchPosition" value="1"/>
				<input type="hidden" name="equipBelongtoCd" id="equipBelongtoCd" value=""/>
				<input type="hidden" name="bisProjectId" id="bisProjectId" value="${bisProjectId }"/> 
				<input type="hidden" name="layoutCd" id="layoutCd" value=""/> 
				<input type="hidden" name="engineerEquipAccountId" id="engineerEquipAccountId" value=""/>
				<div id="header">
					<div id="searchDiv" class="form_body condition_panel none" >
		        		<%--设备搜索 --%>
		        		<ul class="clearfix" id="searchAccountDiv" style="display: none;">
							<li>
								<label style="width: 60px;">设备编号：</label><input type="text" class="text" id="equipCd" name="equipCd"/>
								<label>设备名称：</label><input type="text" class="text" id="equipName" name="equipName"/>
							</li>
							<li>
								<label style="width: 60px;">制造厂名：</label><input type="text" class="text" id="equipMaker" name="equipMaker"/>
								<label>出厂编号：</label><input type="text" class="text" id="equipSerialNum" name="equipSerialNum"/>
							</li>
							<li>
								<label style="width: 60px;">型号规格：</label><input type="text" class="text" id="equipModels" name="equipModels"/>
								<label >出厂日期：</label><input type="text" class="text" id="equipSerialDate" name="equipSerialDate" onfocus="WdatePicker()"/>
							</li>
							<li class="buttons clearfix">
		                        <button type="button" class="blue min" onclick="doQuery();" style="margin-right:5px; ">搜索</button>
		                        <security:authorize ifAnyGranted="A_ENG_EQUIP_EXPORT">
		                        	<button type="button" class="green min" onclick="doExportAccount();" style="margin-right:5px; ">导出</button>
		                        </security:authorize>
		                        <button type="button" class="green min" onclick="doClear();" style="margin-right:5px; ">清空</button>
								<button type="button" class="red min" onclick="showAccountSearchDiv();">取消</button>
	                  		</li>
		           		</ul>
					</div>
				</div>
				
				<%--右边顶部显示div --%>
				<div style="margin: 10px 5px 0px 10px;" id="body">
					<div style="margin-left:0px;overflow:hidden;min-width:550px;height: 90px;">
						<div id="navigation">
							<ul class="clearfix">
					            <li class="" value="1" style="width:90px;">通风系统</li>
					            <li class="" value="2" style="width:90px;">消防系统</li>
					            <li class="" value="3" style="width:90px;">供暖系统</li>
					            <li class="" value="4" style="width:90px;">制冷系统</li>
					            <li class="" value="5" style="width:90px;">供冷系统</li>
					            <li class="" value="6" style="width:90px;">冷却系统</li>
					            <li class="" value="7" style="width:90px;">排水系统</li>
					            <li class="" value="8" style="width:90px;">水处理</li>
					            <li class="" value="9" style="width:90px;">空调暖通</li>
					            <li class="" value="10" style="width:90px;">电梯系统</li>
					            <li class="" value="11" style="width:90px;">强电系统</li>
					            <li class="" value="12" style="width:90px;">弱电系统</li>
					            <li class="" value="13" style="width:90px;">高压系统</li>
					            <li class="" value="14" style="width:90px;">低压系统</li>
					            <li class="" value="15" style="width:90px;">冷冻站系统</li>
					            <li class="" value="16" style="width:90px;">中央控制系统</li>
					            <li class="" value="17" style="width:170px;display: none;">新风、空调机组、风机盘管</li>
					            <li class="" value="18" style="width:90px;">送排风系统</li>
					            <li class="" value="19" style="width:90px;">给排水系统</li>
					            <li class="" value="20" style="width:90px;">照明系统</li>
					            <li class="" value="21" style="width:90px;">道闸系统</li>
					            <li class="" value="22" style="width:90px;">闭路监控系统</li>
					            <li class="" value="23" style="width:90px;">公共广播系统</li>
					        </ul>
						</div>
					</div>
						<div style="min-width:550px;margin-left:0px;text-align: right;display: none;">
							<div class="scrollBtn nav_right" onmousedown="scl()" onmouseup="stopSc(1)"></div>
							<div class="scrollBtn nav_left" onmousedown="scr()" onmouseup="stopSc(2)"></div>
							<div style="clear: both;"></div>
						</div>
					<div class="res_tip" style="padding-top: 5px; margin-top: 5px;">
						<span id="titleSpan" style="margin-left: 10px;font-size: 16px;font-weight: bold;width:800px">工程资产台帐</span>
					</div>
					<%--搜索结果Div --%>
	     			<div id="resultTable"></div>
	     			
					<%--资产台账新增、删除按钮 --%>
					<div style="padding: 8px 0;" id="assmAccountBtu">
						<div class="btns clearfix" style="">
							<security:authorize ifAnyGranted="A_ENG_EQUIP_ADD">
								<button type="button" class="blue min" onclick="addEngineerEquip();" style="margin-right: 5px;padding-left:15px;padding-top: 1px;background: #0072bb url('${ctx}/resources/images/res/res_add.png') no-repeat scroll 5px center;">新增</button>
							</security:authorize>
							<security:authorize ifAnyGranted="A_ENG_EQUIP_DEL">
								<button type="button" class="red min" onclick="deleteBatchAccount();" style="padding-top: 1px;margin: 0px;">删除</button>
							</security:authorize>
							<span id="accountDelTip" style="float: left;margin-top: 10px;margin-bottom:0px;text-align: center;"></span>
						</div>
					</div>
        			
	     		</div>
			  </form>
         	</div>
       		<%--资产台账增加DIV --%>
			<div id="accountAddDiv" class="default"></div>
			<%--资产折旧DIV --%>
			<div id="depreciationDiv" class="default"></div>
		</td>
	</tr>
</table>
</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	dragbar();
	loadEngineerProjectTree('','');
	$("#navigation ul li").bind("click",function(){
		$("#navigation ul li").removeClass("selected");
		$(this).addClass("selected");
		$("#equipBelongtoCd").val($(this).attr("value"));
		belongToCd = $(this).attr("value");
		loadAccount();
	});
	//加载所有数据
	loadAccount();
});
</script>
</body>
</html>