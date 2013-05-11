<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/resources/css/bis/bis-TreePanel.css" type="text/css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />	
<link rel="stylesheet" href="${ctx}/resources/css/bis/bis-shop.css" type="text/css"/>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel_new.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>

<script type="text/javascript">
var _ctx="${ctx}";
$(function(){
	$(".member").ouSelect({muti:true});
	var bodyHeight = Number($(document).height());
	$("#shopDeptTotDiv").css("height",(bodyHeight-10)+"px");
	$("#sortTotDiv").css("height",bodyHeight+"px");
	//loadDeptTree();
});
//是否有点击商家类别
var haveCheckSort ="0";
/**
 * 重新载入
 */
function loadSortDiv(deptCd,authId){
	//若shopSortId有数据,则保存上一条记录
	var userId =$("#UserId").val();
	//$("#shopSortId").val(sortId);
	if(""!=userId&&userId!=authId){
		var usersCd =$("#UserCds_"+userId).val();
		if(""!=usersCd&&""!=$("#shopSortId").val()){
			if(confirm("是否要保存上一条记录？")){
				sortAuthSave();
			}
		}
		$("#td_"+userId).css("backgroundColor","#e4e7ec");
	}
	//上一条记录保存结束，将上一条记录置为灰
	$("#td_"+authId).css("backgroundColor","#6fb0ce");
	if(deptCd&&userId!=authId){
		haveCheckSort ="0";
		$("#sortDiv").html("");
		var param={haveChecked:true,deptCd:deptCd};
		$.post(_ctx+"/bis/bis-shop-sort!loadSortTree.action",param,function(result){
			var treeSort =new TreePanel_New({
				renderTo:"sortDiv",
				'root' : eval('('+result+')'),
				'ctx':_ctx
			});
			treeSort.addListener("check",function(node){
				haveCheckSort ="1";
				$("#shopSortId").val(treeSort.getCheckedId());
			});
			treeSort.render();
			doExpandTreeNode(treeSort.getRootNode().childNodes);
		});
		if(authId){
			$("#UserId").val(authId);
			$("#shopSortId").val("");
		}
	}
}
//展开结点
function doExpandTreeNode(children){
	if(children!=null&&children.length>0){
		for(var i=0;i<children.length;i++){
			var node =children[i];
			node.expand();
			doExpandTreeNode(node.childNodes);
		}
	}
}
function sortAuthSave(){
	var userId =$("#UserId").val();
	if(""!=userId){
		var shopSortId =$("#shopSortId").val();
		var userCd =$("#UserCds_"+userId).val();
		//若无点击商家类别
		if("0"==haveCheckSort){
			//用户没变化
			if(userCd==$("#oldUserCd_"+userId).val()){
				return false;
			}else if(""==userCd){//若有商家类别，无用户，则不允许保存
				alert("请选择用户");
			    return false;
			}else{
				shopSortId=$("#SortIds_"+userId).val();
			}
		}else{
			//有点击商家类别,若无用户且有商家类别
			if(""==userCd&&""!=shopSortId){
				alert("请选择用户");
				return false;
			}else if(""!=userCd&&""==shopSortId){
				//有用户无商家类别
				alert("请选择商家类别");
				return false;
			}
		}
		if(""==shopSortId&&""==userCd){
			if (confirm("请确定无用户对应商家类别？")){
				TB_showMaskLayer();
				var param={userCd:'',userName:'',userId:userId,shopSortId:''};
				$.post(_ctx+"/bis/bis-shop-sort!saveAuth.action",param,function(result){
					if(result=="0"){
						TB_removeMaskLayer();
						alert("保存成功");
						$("#oldUserCd_"+userId).val(userCd);
						$("#shopSortId").val("");
					}
				});
			}
		}else if(""==shopSortId||""==userCd){
			alert("请选择商家类别或用户");
			return false;
		}
		else{
			var userName =$("#UserNames_"+userId).val();
			var param={userCd:userCd,userName:userName,userId:userId,shopSortId:shopSortId};
			TB_showMaskLayer();
			$.post(_ctx+"/bis/bis-shop-sort!saveAuth.action",param,function(result){
				if(result=="0"){
					TB_removeMaskLayer();
					alert("保存成功");
					$("#oldUserCd_"+userId).val(userCd);
					$("#shopSortId").val("");
				}
			});
		}
	}
}
</script>
</head>
<body>
<input type="hidden" id="shopSortId"/>
<input type="hidden" id="UserId"/>
<table style="width:100%;">
<tr>
<td valign="top" width="300px">
	<div id="shopDeptTotDiv" style="background-color:#e4e7ec;height:94%;width: 400px;padding-top:10px; padding-left:10px;border-right:1px solid #8c8f94;">
	 <table class="dept-table">
	  <s:iterator value="authList">
	    <tr>
	     <td id="td_${bisShopSortAuthId}" onclick="loadSortDiv('${deptCd}','${bisShopSortAuthId}')" style="width:50%;padding-left:10px;font-size:14px;">${deptName}</td>
	     <td>
	       <input type="text" class="member" id="UserNames_${bisShopSortAuthId}" value="${usersName}" />
		   <input type="hidden" id="UserCds_${bisShopSortAuthId}" value="${usersCd}" />
		   <input type="hidden" id="SortIds_${bisShopSortAuthId}" value="${shopSortId}"/>
		   <input type="hidden" id="oldUserCd_${bisShopSortAuthId}" value="${usersCd}"/>
		   </td>
	    </tr>
	  </s:iterator>
	 </table>
	 
	</div>
</td>
<td valign="top">
	<div id="sortTotDiv" style="background-color:#e4e7ec;height:94%;width:100%;border-right:1px solid #8c8f94;">
	 <div id="sortDiv" style="height:90%;overflow-y: auto;overflow-x:hidden;border-bottom:1px solid #8c8f94;"></div>
	 <div style="float:center;padding:10px;">
	 <input type="button" value="保存" class="button_blue" onclick="sortAuthSave();"/>
	</div>
	</div>
</td>
</tr>
</table>
</body>
</html>