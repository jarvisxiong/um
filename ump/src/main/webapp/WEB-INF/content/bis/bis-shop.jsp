<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/base.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/thickbox.css"/>	
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-shop.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/assm/style.css"/>
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel_new.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bis/bis-shop.js"></script>
</head>
<body>
<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
<div id="warp">
	<input type="hidden" id="shopSortId" value="${shopSortId}"/>
	<div id="header">
	    <div class="title1 clearfix">
	        <h2 style="width: auto;float: left;">商家库</h2>
		 	<div class="btns">
					<button class="blue" type="button" onclick="doBisShopSort();">商家类别</button>
		 		
			    <security:authorize ifAnyGranted="A_SHOP_CONF_SORT">
					<button class="blue" type="button" onclick="shopAuthBySort();">配置商家类别</button>
				</security:authorize>
	  			<security:authorize ifAnyGranted="A_SHOP_NEW_PRO,A_SHOP_NEW_HQ">
					<button type="button" class="orange min" onclick="doBisShop('');" style="padding-left:15px;padding-top: 1px;background: #0072bb url('${ctx}/resources/images/res/res_add.png') no-repeat scroll 5px center;">新增</button>
				</security:authorize>
	            <button type="button" class="gray" onclick="doQuery();">高级搜索</button>
	            <button type="button" class="blue min" id="queryBtn" onclick="queryBisShopList();" style="margin-left: 5px;">搜索</button>
	        	<input style="float: right;margin-top:3px; padding: 2px;width: 150px;background:#FFF url(/PowerDesk/resources/images/desk2/search.png) no-repeat 136px 1px;color:#cccccc;"
					       title="支持资产台账名称、编码搜索"
					       value="快速搜索..."
					       onkeyup="bisQuickSearch(event);"
				    	   onclick="clearQuickSearch(this);"
					       onblur="resetQuickSearch(this);" 
					       id="quickQueryText" 
					       class="text"/>
	         </div>
	    </div>
	    <span style="float:right;"><%@ include file="bis-manage-version.jsp" %></span>
	</div>

	<div style="position: absolute;bottom:0;top:39px;width:100%;height100%;overflow-x:hidden;overflow-y:auto;+overflow:visible;">
	<table style="width:100%;">
		<tr>
			<td valign="top" width="190px" style="height: 100%;background-color:#e4e7ec;border-right:1px solid #8c8f94;">
				<div id="sortTotDiv" style="background-color:#e4e7ec;height:100%;width: 190px;float:left;">
					<div style="width:190px; height:100%;float:left;">
						<div id="tips_load" style="float:left;padding-top:4px;height:100%; width: 190px; overflow-y: auto;overflow-x:hidden">加载中......</div>
						<div id="sortDiv" style="display:none;float:left;padding-top:4px;height:100%; width: 190px; overflow-y: auto;overflow-x:hidden">
						</div>
					</div> 
				</div>
			</td>
			<td valign="top">
				<div id="search_condtion" style="display:none;">
					<div id="header">
						<div id="searchDiv" class="form_body condition_panel">
		          			<ul class="clearfix">
								<li>
									<label style="width: 80px;">人员：</label>
										<input type="text" class="text" id="userName"/>
										<input type="hidden" id="salesman"/>
										<input type="hidden" id="centerCd" />
									<label>品牌名(中)：</label>
										<input type="text" class="text" id="nameCn"/>
									<label>品牌名(英)：</label>
										<input type="text" class="text" id="nameEn"/>
								</li>
								<li>
									<label style="width: 80px;">经营性质：</label>
										<s:select list="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" listKey="key" listValue="value" id="selectManageCd" cssClass="box" cssStyle="width:120px;"/>
									<label>公司名称：</label>
										<input type="text" class="text" id="companyName"/>
									<label>状态：</label>
										<select id="shopAudit" class="box" style="width:120px;">
					                       <option value=""></option>
					                       <option value="0">未提交</option>
					                       <option value="1">待审核</option>
					                       <option value="2">预审核</option>
					                       <option value="3">已审核</option>
					                       <option value="4">已合并</option>
					                	</select>
								</li>
								<li>
									<label style="width: 80px;">建档时间从：</label>
										<input type="text" class="text" id="createDate1" onfocus="WdatePicker();"/>
									<label style="text-align: center;">到</label>
										<input type="text" class="text" id="createDate2" onfocus="WdatePicker();"/>
								</li>
								<li class="buttons clearfix">
									<label style="width: 20px;">&nbsp;</label>
			                        <button type="button" id="ModBtn" class="btn_blue min" onclick="queryBisShopList();">搜索</button>
			                        <security:authorize ifAnyGranted="A_SHOP_ADMIN">
			                        	<button type="button" class="green min" onclick="doExportShop();">导出</button>
			                        </security:authorize>
			                        <button type="button" class="green" onclick="cleanCondition();">清空</button>
									<button type="button" class="red min" onclick="doQuery();">取消</button>
		                  		</li>
			           		</ul>
						</div>
					</div>
				</div>
				
				<%--替换 --%>
				<div id="mailRight" style="height: 98%;width:100%;">
				 	<div class="inform_div" style=" margin-right: 330px;width:100%;">请选择左侧目录搜索</div>
				</div>
			</td>
		</tr>
	</table>
	</div>
</div>

<!-- 导出excel的搜索条件 -->
<form action="${ctx}/bis/bis-shop!doExportShop.action" method="post" id="exportForm">
	<input type="hidden" id="shopSortId_exl" name="shopSortId"/>
	<input type="hidden" id="centerCd_exl" name="centerCd"/>
	<input type="hidden" id="nameCn_exl" name="nameCn"/>
	<input type="hidden" id="nameEn_exl" name="nameEn"/>
	<input type="hidden" id="selectManageCd_exl" name="selectManageCd"/>
	<input type="hidden" id="companyName_exl" name="companyName"/>
	<input type="hidden" id="salesman_exl" name="salesman"/>
	<input type="hidden" id="shopAudit_exl" name="shopAudit"/>
	<input type="hidden" id="createDate1_exl" name="createDate1"/>
	<input type="hidden" id="createDate2_exl" name="createDate2"/>
	<input type="hidden" id="quickQueryText_exl" name="quickQueryText"/>
</form>
 <%-- <div id="searchUserDiv" class="searchUserDiv"></div>--%>
 
<script type="text/javascript">
$(function(){
	var bodyHeight = Number($(document).height()-35);
	$("#sortTotDiv").css("height",bodyHeight+"px");
	//loadSortTree(true,true,false,"sortDiv");
	var sortId=$("#shopSortId").val();
	loadTreeByTreePanelNew(true,false,true,"sortDiv",sortId);
	if(""!=sortId){
		$("#ModBtn").click();
	}
	/* $('#creatorName').ouSelect({muti:false});
	$('#centerName').onSelect({muti:false,
		callback:function(project){
			$("#centerCd").val(project.orgCd);
			$("#centerName").val(project.projectName);
			$("#deptName").val("");
			//如果选择了中心，则defaultParentOrgCd为该中心下的内容
			$("#defaultParentOrgCd").val(project.orgCd);
		}
	}); */
	//初始化页面配置
	$("#userName").ouSelect({muti:false});
	//初始化默认是宝龙商业
	//$("#centerName").val("宝龙商业");
	//$("#centerCd").val("153");
	/* $("#center2Name").val("招商中心");
	$("#center2Cd").val("154"); */
	var _this =$('#quickQueryText');
	_this.val("快速搜索...");
	_this.css("color","#909090");
	_this.live("click",function(){
		if("快速搜索..."==$(this).val()){
			$(this).val("");
			$(this).css("color","black");
		}
	});
});
function doQuery(){
	if($("#search_condtion").css("display")=="none"){
		//$("#mailRight").hide();
		//$("#search_condtion").show().animate({height:$("#sortTotDiv").css("height")}, "1000");
		$("#search_condtion").show();
		$("#queryDiv").addClass("quickSeniorHover");
		//$("#deptName").click();
	}else{
		$("#search_condtion").hide();
		$("#queryDiv").removeClass("quickSeniorHover");
		$("#mailRight").show();
		/* if($("#ym-window").css("display")!="none"){
			ymPrompt.close();
		} */
	}
	
}
function showCenter(){
	var centerCd=$("#centerCd").val();
	ymPrompt.win({
		icoCls:"",
		title:"选择中心",
		message:"<div id='showCenterDiv'><div id='690' style='padding-left:5px;font-size:14px;border: 2px solid #fff;cursor: pointer;font-weight:bold;' onclick='selectedCenter2(this);'>大客户中心</div>"+
		"<div id='154' style='padding-left:5px;font-size:14px;border: 2px solid #fff;cursor: pointer;font-weight:bold;' onclick='selectedCenter2(this);'>招商中心</div></div>",
		useSlide:true,
		winPos:"c",
		width:300,
		height:200,
		allowRightMenu:true,
		showMask:false
	});
}
function selectedCenter2(dom){
	$("#center2Name").val($(dom).text());
	$("#center2Cd").val($(dom).attr("id"));
	ymPrompt.close();
	//如果选择了中心，则defaultParentOrgCd为该中心下的内容
	$("#defaultParentOrgCd").val($(dom).attr("id"));
}
function showDept(){
	var centerCd;
	/* if(""!=$("#center2Cd").val()){
		centerCd=$("#center2Cd").val();
	}else */
	if(""!=$("#centerCd").val()){
		centerCd=$("#centerCd").val();
	}else{
		centerCd="";
	}
	var htmlL = '';
	if(""==centerCd||"153"==centerCd){
		htmlL ="<div id='showDeptDiv' style='padding-left:10px;'>"+
		"<div style='font-weight:bold;font-size:14px;border: 2px solid #fff;cursor: pointer;' onclick='selectedDept(this);' id='870'>大客户一部</div>"+
		"<div style='font-weight:bold;font-size:14px;border: 2px solid #fff;cursor: pointer;' onclick='selectedDept(this);' id='871'>大客户二部</div>"+
		"<div style='font-weight:bold;font-size:14px;border: 2px solid #fff;cursor: pointer;' onclick='selectedDept(this);' id='872'>大客户三部</div>"+
		"<div style='font-weight:bold;font-size:14px;border: 2px solid #fff;cursor: pointer;' onclick='selectedDept(this);' id='159'>市场部</div>"+
		"<div style='font-weight:bold;font-size:14px;border: 2px solid #fff;cursor: pointer;' onclick='selectedDept(this);' id='160'>招商一部</div>"+
		"<div style='font-weight:bold;font-size:14px;border: 2px solid #fff;cursor: pointer;' onclick='selectedDept(this);' id='161'>招商二部</div>"+
		"<div style='font-weight:bold;font-size:14px;border: 2px solid #fff;cursor: pointer;' onclick='selectedDept(this);' id='164'>管理部</div>";
	}
	ymPrompt.win({
		icoCls:"",
		title:"部门",
		message:"<div id='showDeptDiv'></div>",
		useSlide:true,
		winPos:"c",
		width:300,
		height:250,
		allowRightMenu:true,
		showMask:false,
		afterShow:function(){
			if(""==centerCd||"153"==centerCd){
				$("#showDeptDiv").html(htmlL);
				$("#centerName").val("宝龙商业");
				$("#centerCd").val("153");
			}else{
				$.post("${ctx}/bis/bis-shop!showDept.action",{centerCd:centerCd}, function(result){
					result = eval(result);
					
					$.each(result,function(i,n){
						var html = "<div id='"+n['orgCd']+"' onclick='selectedDept(this);' style='padding-left:10px;font-weight:bold;font-size:14px;border: 2px solid #fff;cursor: pointer;'>";
						htmlL +=html+n['orgName']+"</div>";
					});
					$("#showDeptDiv").html(htmlL);
				});
			}
	}
	});
}
function selectedDept(dom){
	$("#deptName").val($(dom).text());
	$("#defaultParentOrgCd").val($(dom).attr("id"));
	ymPrompt.close();
}
var tree;
var treeDept;
function loadTreeByTreePanelNew(haveChecked,haveExpand,haveChild,itemDiv,sortId){
	//拥有A_SHOP_ALL_VIEW(商家库-查阅全部)角色的人，左边的树可看到所有项目公司，
	//否则只能看到自己所属的项目公司，且对于项目公司没有'主力店'节点
	$.post(_ctx+"/bis/bis-shop-sort!loadSortTreeHQ.action?haveChecked="+haveChecked, function(result){
		$("#"+itemDiv).empty();
		<security:authorize ifNotGranted="A_SHOP_ALL_VIEW">
			$("#tips_load").hide();
			$("#"+itemDiv).show();
		</security:authorize>
		tree = new TreePanel_New({
			renderTo:itemDiv,
			'root' : eval('('+result+')'),
			'ctx':_ctx
		});
		tree.on(function(node){
			$("#id").val(node.attributes.id);
			$("#sortName").val(node.attributes.text);
			$("#parentId").val(node.attributes.parentId);
			$("#parentName").val(node.attributes.parentName);
			$("#sequenceNo").val(node.attributes.orderNo);
			$("#sortType").val(node.attributes.finType);
		});
		tree.addListener("check",function(node){
			var checkedIds;
			if(treeDept != null){
				if("" != treeDept.getCheckedId()){
					if("" != tree.getCheckedId()){
						checkedIds = tree.getCheckedId()+","+treeDept.getCheckedId();
					}else{
						checkedIds = treeDept.getCheckedId();
					}
				}else{
					checkedIds = tree.getCheckedId();
				}
			}else{
				checkedIds = tree.getCheckedId();
			}
			$("#shopSortId").val(checkedIds);
			//点击树搜索  -add by liuzhihui 2012-06-06
			queryBisShopList();
		});
		tree.render();
		if(haveExpand){
			doExpandTreeNode(tree.getRootNode().childNodes,sortId);
		} 
		
		//加载各商业公司树
		<security:authorize ifAnyGranted="A_SHOP_ALL_VIEW">
			$.post(_ctx+"/bis/bis-shop-sort!loadSortTreePro.action", function(result){
				$("#tips_load").hide();
				$("#"+itemDiv).show();
				treeDept =new TreePanel_New({
					renderTo:itemDiv,
					'root' : eval('('+result+')'),
					'ctx':_ctx
				});
				treeDept.on(function(node){
					var nodeId = node.attributes.id;
					if(nodeId == '0'){
						if(node.isExpand){
							node.collapse();
						}else{
							node.expand();
						}
					}
				});
				treeDept.addListener("check",function(node){
					var nodeId = node.attributes.id;
					var checkedIds;
					if(tree != null){
						if("" != tree.getCheckedId()){
							if("" != treeDept.getCheckedId()){
								checkedIds = treeDept.getCheckedId()+","+tree.getCheckedId();
							}else{
								checkedIds = tree.getCheckedId();
							}
						}else{
							checkedIds = treeDept.getCheckedId();
						}
					}else{
						checkedIds = treeDept.getCheckedId();
					}
					
					$("#shopSortId").val(checkedIds);
					//点击树搜索  -add by liuzhihui 2012-06-06
					queryBisShopList();
					if(nodeId == '0'){
						if(node.isExpand){
							node.collapse();
						}else{
							node.expand();
						}
					}
				});
				treeDept.render();
				treeDept.getRootNode().collapse();
			});
		</security:authorize>
	});
}

function queryBisShopList(){
	/*if(null!=tree&&""!=tree.getCheckedId()){
		$("#shopSortId").val(tree.getCheckedId());
	}
	if(null!=treeDept&&""!=treeDept.getCheckedId()){
		$("#centerCd").val(treeDept.getCheckedId());
	} */
	if($("#search_condtion").css("display")!="none"){
		//$("#search_condtion").hide();
		$("#mailRight").show();
	}
	jumpPage(1);
}
//清空机构数据
function cleanOrgContent(){
	$("#centerName").val("");
	$("#centerCd").val("");
	/* $("#center2Name").val("");
	$("#center2Cd").val(""); */
	$("#deptName").val("");
	$("#defaultParentOrgCd").val("");
	
}
function cleanCondition(){
	$("#userName").val("");
	$("#salesman").val("");
	$("#nameCn").val("");
	$("#nameEn").val("");
	$("#selectManageCd").val("");
	$("#companyName").val("");
	$("#shopAudit").val("");
	$("#createDate1").val("");
	$("#createDate2").val("");
}

function remindMerge() {
	
	ymPrompt.win( {
		icoCls : "",
		autoClose:false,
		allowSelect:true,
		allowRightMenu:true,
		message : "<div id='remindMergeDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 300,
		height : 500,
		title : '商家库合并提醒',
		closeBtn:true,
		afterShow : function() {
			$.post(_ctx+"/bis/bis-shop!remindMerge.action", function(result) {
				$("#remindMergeDiv").html(result);
			});
		},
		handler : function(btn){
			ymPrompt.close();
		},
		btn:[['关闭','close']]
	});
	/*
	$.post(_ctx+"/bis/bis-shop!remindMerge.action", function(result) {
		//...
	});
	*/
}
function shopAuthBySort(){
	var url ="${ctx}/bis/bis-shop-sort!authority.action";
	parent.TabUtils.newTab("bis-shop-input","配置类别",url,true);
}

var g_quicksearch='快速搜索...';
function bisQuickSearch(event){
	if (event.keyCode == 13) {
		queryBisShopList();
	}
}
function resetQuickSearch(dom){
	if($(dom).val().trim() == ''){
		$(dom).val(g_quicksearch);
		$(dom).css({color:"#cccccc"});
	}else{
		$(dom).css({color:"#5A5A5A"});
	}
}
function clearQuickSearch(dom){
	if( $(dom).val() == g_quicksearch){
		$(dom).val('');
		$(dom).css({color:"#5A5A5A"});
	}
}
</script>
</body>
</html>