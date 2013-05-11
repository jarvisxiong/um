<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/base.css"/>
	<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />	
	<link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
	<link rel="stylesheet" href="${ctx}/resources/css/bis/bis-shop.css" type="text/css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/assm/style.css"/>
		
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
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

<table width="100%;">
 	<tr>
  		<td width="190px;" valign="top" style="height: 100%;background-color:#e4e7ec;border-right:1px solid #8c8f94;">
			<div id="sortTotDiv" style="height:100%;background-color:#e4e7ec;float:left;">
				<div id="tips_load" style="float:left;padding-top:4px;height:100%; width: 190px; overflow-y: auto;overflow-x:hidden">加载中......</div>
				<div id="sortMergeDiv" style="width:190px;height:100%;padding-top:4px;overflow-y: auto;overflow-x:hidden;float:left;">
				</div>
			</div>
 		</td>
	  	<td valign="top">
		  	<div id="search_condtion">
			<div id="header">
				<div id="searchDiv" class="form_body condition_panel">
		        	<ul class="clearfix">
						<li>
							<input type="hidden" id="mergeId" value="${mergeId}" />
							<input type="hidden" id="shopSortId" value="${shopSortId}"/>
							<input type="hidden" id="centerCd" />
							
							<label style="width: 80px;">人员：</label>
								<input type="text" class="text" id="userName"/>
     							<input type="hidden" id="creator"/>
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
		                		</select>
						</li>
						<li>
							<label style="width: 80px;">建档时间从：</label>
								<input type="text" class="text" id="createDate1" onfocus="WdatePicker();"/>
							<label style="text-align: center;">到</label>
								<input type="text" class="text" id="createDate2" onfocus="WdatePicker();"/>
							<label>
								<button type="button" id="mergeButton" class="btn_blue min" onclick="jumpPage(1);">搜索</button>
							</label>
						</li>
		          	</ul>
				</div>
			</div>
			</div>
			
  			<div id="mailRight" style="height: 98%;width:100%;">
	 			<div class="inform_div" style=" margin-right: 330px;width:100%;">请选择左侧目录搜索</div>
			</div>
		</td>
	</tr>
</table>


<script type="text/javascript">
loadSortTree();
//新增商家类别
function loadSortTree(){
	$.post("${ctx}/bis/bis-shop-sort!loadSortTreeHQ.action?haveChecked=true", function(result){
		$("#sortMergeDiv").empty();
		<security:authorize ifNotGranted="A_SHOP_ALL_VIEW">
			$("#tips_load").hide();
			$("#sortMergeDiv").show();
		</security:authorize>
		var tree = new TreePanel_New({
			renderTo:"sortMergeDiv",
			'root' : eval('('+result+')'),
			'ctx':'${ctx}'
		});
		tree.on(function(node){
			$("#id").val(node.attributes.id);
			$("#sortName").val(node.attributes.text);
		});
		tree.addListener("check",function(node){
			var checkedIds;
			if("" != treeDept.getCheckedId()){
				if("" != tree.getCheckedId()){
					checkedIds = tree.getCheckedId()+","+treeDept.getCheckedId();
				}else{
					checkedIds = treeDept.getCheckedId();
				}
			}else{
				checkedIds = tree.getCheckedId();
			}
			$("#shopSortId").val(checkedIds);
		});
		tree.render();
		
		//加载各商业公司树
		<security:authorize ifAnyGranted="A_SHOP_ALL_VIEW">
			$.post(_ctx+"/bis/bis-shop-sort!loadSortTreePro.action", function(result){
				$("#tips_load").hide();
				$("#sortMergeDiv").show();
				treeDept =new TreePanel_New({
					renderTo:"sortMergeDiv",
					'root' : eval('('+result+')'),
					'ctx':_ctx
				});
				treeDept.addListener("check",function(node){
					var checkedIds;
					if("" != tree.getCheckedId()){
						if("" != treeDept.getCheckedId()){
							checkedIds = treeDept.getCheckedId()+","+tree.getCheckedId();
						}else{
							checkedIds = tree.getCheckedId();
						}
					}else{
						checkedIds = treeDept.getCheckedId();
					}
					$("#shopSortId").val(checkedIds);
				});
				treeDept.render();
			});
		</security:authorize>
		/*
		//新增部门
		$.post("${ctx}/bis/bis-shop!loadDeptTree.action", function(result){
			var treeDept =new TreePanel_New({
				renderTo:"sortMergeDiv",
				'root' : eval('('+result+')'),
				'ctx':'${ctx}'
			});
			treeDept.addListener("check",function(node){
				$("#centerCd").val(treeDept.getCheckedId());
			});
			treeDept.render();
		});
		*/
	});
	var bodyHeight = Number($(document).height()-6);
	$("#sortTotDiv").css("height",bodyHeight+"px");
	$("#seaTable").css("height",bodyHeight+"px");
	//初始化页面配置
	$("#userName").ouSelect({muti:false});
}
function jumpPage(pageNo){
	TB_showMaskLayer("正在搜索");
	var nameCn =$("#nameCn").val();
	var nameEn =$("#nameEn").val();
	var selectManageCd =$("#selectManageCd").val();
	//var selectShopType =$("#selectShopType").val();selectShopType:selectShopType,
	var companyName =$("#companyName").val();
	var arrCheck=$("#shopSortId").val();
	var salesman =$("#creator").val();
	var shopAudit =$("#shopAudit").val();
	var centerCd =$("#centerCd").val();
	var createDate1=$("#createDate1").val();
	var createDate2=$("#createDate2").val();
	var mergeId =$("#mergeId").val();
	/* var quickQueryText;
	if("搜索关键字"==$("#quickQueryText").val()){
		quickQueryText="";
	}else{
		quickQueryText=$("#quickQueryText").val();
	} */
	var param = {shopSortId:arrCheck,currentPageNo:pageNo,nameCn:nameCn,nameEn:nameEn,selectManageCd:selectManageCd,mergeId:mergeId,
			companyName:companyName,salesman:salesman,shopAudit:shopAudit,centerCd:centerCd,createDate1:createDate1,createDate2:createDate2};
	$.post("${ctx}/bis/bis-shop!mergeList.action",
			param,
		function(result){
			if(result){
				TB_removeMaskLayer();
				$("#mailRight").html(result);
			}
	});
}
function checkAll(src) {
	$("#mailRight input[type=checkbox]").attr("checked", $(src).attr("checked"));
} 
//合并商家库功能
function merge(id){
	if(""!=id){
		var obj=$("#mailRight input:checkbox[checked=true]");
		if (obj.length == 0) {
			alert("请选要合并的记录");
			return; 
		}
		var merges = "";
		for (var i = 0; i < obj.length; i ++) {
		    var id = obj.eq(i).val(); 
		    merges=merges+id+",";
		}
		$.post("${ctx}/bis/bis-shop!merge.action",
				{batchData:merges,mergeId:$("#mergeId").val()},
			function(result){
				if(result){
					if("ok"==result){
						alert("合并成功");
						$("#mergeButton").click();
						inputUrl =_ctx+"/bis/bis-shop!input.action?id="+$("#mergeId").val();
						parent.TabUtils.newTab("bis-shop-input","商家信息",inputUrl,true);
					}else if("haveMergeId"==result){
						alert("被合并的商家含有源商家记录，请重选商家信息");
					}else{
						alert(result);
					}
				}
		});
	}
}
function doMergeShop(id){
	if(id){
		inputUrl =_ctx+"/bis/bis-shop!input.action?mergeId=mergeId&id="+id;
		parent.TabUtils.newTab("bis-shop-mergeInput","合并查看",inputUrl,true);
	}
}
</script>
</body>
</html>