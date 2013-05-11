<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/fin.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/mailStyle.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css" />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/js/jqueryplugin/chilltip.js"></script>
	
	<style type="text/css">
		#search_condtion th{
			padding-right:5px;
			text-align: right;
			font-weight: normal;
		}
		#search_condtion td{
			padding-left:5px;
			text-align: left;
			font-weight: normal;
		}
	
	</style>
</head>
<body>
	
	<form action="${ctx}/sup/sup-basic!list.action" method="post" id="searchForm">
		<input type="hidden" id="suppTypes" name="suppTypes"/>
		<input type="hidden" id="supName_page" name="supName"/>
		<input type="hidden" id="supManaEval_page" name="supManaEval"/>
		<input type="hidden" id="supExamResu_page" name="supExamResu"/>
		<input type="hidden" id="supCooperated_page" name="supCooperated"/>
		<input type="hidden" id="currentPageNo" name="page.pageNo"/>
		<input type="hidden" id="isOfficalWeb_page" name="isOfficalWeb"/>
		<input type="hidden" id="isBid_page" name="isBid"/>
		<input type="hidden" id="appCompTime1_page" name="appCompTime1"/>
		<input type="hidden" id="appCompTime2_page" name="appCompTime2"/>
	</form>
	
	<input type="hidden" id="currentPageNo" value="1" />
	<div class="title_bar">
		<div class="fLeft banTitle">
			供方库
		</div>
		<div class="fRight">
		   	<input type="button" class="btn_new btn_senior_new" style="width:70px;" onclick="doQuery();" value="高级搜索"/>
			<security:authorize ifAnyGranted="A_SUP_NEW">
			<input type="button" class="btn_new btn_add_new" style="width:60px;" onclick="newSup();" value="新增" />
			</security:authorize>
			<input type="button" class="btn_new btn_full_new" onclick="window.open('${ctx}/sup/sup-basic!main.action');" value="全屏 " />
			<input type="button" class="btn_new btn_refresh_new" onclick="refreshMain()" value="刷新" />
		 </div>
	 </div>
	<!-- mailInfo end -->
	<div id="maiMainBottom" style="width:100%; height:100%;">
		<table style="width:100%;">
		<tr>
			<td style="width:180px; border:1px solid #80AB73;padding:5px;" valign="top">
			  	<div style="min-height: 400px;">
			  		<div id="itemDiv" style="height: 100%; width: 180px; overflow: auto;"></div>
			    </div>
			    <%--
			    <input type="button" class="btn_new btn_query_new" onclick="doQuerySup()" value="搜索" />
			     --%>
			</td>
			<td valign="top">
				<div id="search_condtion" style="background-color:white;font-weight: normal;width:100%;padding:5px;line-height: 24px;border-bottom:1px solid #cbcbcb;">
				<table style="width: 100%;">
					<col width="60px;"/>
					<col />
					<col width="10%"/>
					<col width="23%"/>
					<col width="80px;"/>
					<col/>
					<tr>
						<th>供方名称</th>
						<td colspan="3"><input type="text" id="supName" style="width:100%;" /></td>
						<th>来自官网 </th>
						<td>
							<span><input type="checkbox" id="isOfficalWeb" value="1"/></span>
						</td>
					</tr>
					<tr>
 						<td>合作情况  
 						</td>
 						<td colspan="5">
						 	<select id="supCooperated">
		                         <option value="0"></option>
		                         <option value="1">已合作</option>
		                         <option value="2">未合作</option>
		                     </select>
							 供方级别 
							<s:select list="mapSupEvaluate" listValue="value" id="supManaEval"/>	
							考察
						 	<select id="supExamResu"  style="width:100px">
		                       <option value="0"></option>
		                       <option value="1">考察合格</option>
		                       <option value="2">考察不合格</option>
		                       <option value="3">未考察</option>
		                	</select>
		                	审核状态
						 	<select id="supAudit"  style="width:100px">
						 	   <option value=""></option>
		                       <option value="0">未提交</option>
		                       <option value="1">未审核</option>
		                       <option value="2">已审核</option>
		                	</select>
		                </td>
					</tr>
					<tr>
						 <th>定标时间</th>
						 <td colspan="3">
						 	<span>
						 		<input type="checkbox" id="isBid" value="1" title="是否定标"/>
						 		&nbsp;&nbsp;从 <input style="width:100px;padding-left:5px;" type="text" id="appCompTime1" onfocus="WdatePicker();" class="Wdate"/>&nbsp;到&nbsp;
						  		<input style="width:100px;padding-left:5px;" type="text" id="appCompTime2" onfocus="WdatePicker();"  class="Wdate"/>
						 	</span>
						 </td>
					</tr>
					<tr>
						<td>适用地区</td>
						<td colspan="5" id="tag1List">
							<s:iterator value="{'上海','天津','重庆','河南','山东','江苏','浙江','福建','其他地区','未设置'}" id='tagCd'>
								<input 	type="checkbox" 
										tagcd='<s:property value='tagCd'/>' 
										tagName="<s:property value='tagCd'/>" 
										style="margin-right:3px;"/>
								<s:property value='tagCd'/>
							</s:iterator>
						</td>
					</tr>
					<tr>
						<td>实力评价</td>
						<td colspan="4" id="tag2List">
							<s:iterator value="{'一线品牌','二线品牌','未设置'}" id='tagCd'>
								<input 	type="checkbox" 
										tagcd='<s:property value='tagCd'/>' 
										tagName="<s:property value='tagCd'/>" 
										style="margin-right:3px;"/>
								<s:property value='tagCd'/>
							</s:iterator>
						</td>
						<td><input type="button" id="ModBtn" class="btn_new btn_query_new fLeft" onclick="doQuerySup();" value="搜索"></td>
					</tr>
					
					<%-- 以下是自定义标签 
					<tr>
						<td id="supTags" colspan="5" style="line-height: 24px;text-align: left;" valign="top">
							<%@include file="sup-basic-tags.jsp" %>
						 
						</td>
						<td valign="middle">
						 	<input type="button" id="ModBtn" class="btn_new btn_query_new fLeft" onclick="doQuerySup();" value="搜索">
						</td>
					</tr>
					--%>
				</table>
			  	</div>
			  	
			  	<%--搜索列表 --%>
			  	<div id="mailRight">
		  		</div>
			</td>
		</tr>
		</table>
	</div>
</div>
<div id="searchUserDiv" class="searchUserDiv"></div>

<script type="text/javascript">
$(function(){
	//加载供应商类型树
	getSupTree("itemDiv");
	
	//默认查询
	jumpPage(1);
});
function newSup(){
	var url="${ctx}/sup/sup-basic!input.action";
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("supInput","新增",url,true);
}
/**
 * 根据boxId高亮邮件左侧导航栏
 * @param boxId
 */
function hilightLeft(boxId){
	var box;
	if(boxId == 0)box="newEmailId";
	if(boxId == 1)box="inBoxId";
	if(boxId == 2)box="draftBoxId";
	if(boxId == 3)box="outBoxId";
	if(boxId == 4)box="newProject";
	if(boxId == 5)box="unReadBoxId";
	if(boxId == 6)box="searchBoxId";
	$("#"+box).addClass("menuClick").siblings().removeClass("menuClick");
	
}
var arrCheck ="";
function getSupTree(itemDiv){
	$.post("${ctx}/sup/sup-basic!getSupTree.action", function(result){
		var tree = new TreePanel({
			renderTo:itemDiv,
			'root' : eval('('+result+')'),
			'ctx':'${ctx}'
		});
		tree.haveClickText =true;
		tree.on(function(node){
			$("#id").val(node.attributes.trueId);
			$("#itemName").val(node.attributes.text);
			$("#parentCd").val(node.attributes.parentId);
			$("#parentName").val(node.attributes.parentName);
			$("#orderNo").val(node.attributes.orderNo);
			$("#itemType").val(node.attributes.finType);
			$("#itemCd").val(node.attributes.finItemCd);

			if(node.isExpand){
				node.collapse();
			}else{
				node.expand();
			} 
		});
		//监听事件
		tree.addListener("check",function(node){
			arrCheck = tree.getCheckedId();
			finType=tree.getCheckedType();
			
			//当前节点ID
			//var id = node.attributes.trueId;
			//loadSupTags(id);
			
			//触发搜索
			//doQuerySup();
		});
		tree.render();
	});
}
//加载标签
function loadSupTags(itemTypeCd){
	var url = '${ctx}/sup/sup-basic!loadSupTags.action';
	var data = {itemTypeCd: itemTypeCd};
	$.post(url, data, function(result){
		$('#supTags').html(result);
	});
}
//搜索供应商
function doQuerySup(){
	jumpPage(1);
}

//获取选中的值，以,隔开
function getTagCds(tagListId){
	var arr = new Array();
	var i=0;
	$('#'+tagListId+' input:checked ').each(function(){
		if(i>0){
			arr.push(",");
		}
		arr.push($(this).attr('tagcd'));
		i++;
	});
	var rtn = arr.join('');
	return rtn;
}
function getSup(id){
	if(id!=""){
		//self.location="${ctx}/sup/sup-basic!query.action?id="+id;
		var url="${ctx}/sup/sup-basic!input.action?id="+id;
		if(parent.TabUtils==null)
			window.open(url);
		else
		  parent.TabUtils.newTab("supQuery","供方明细",url,true);
	}
}
function jumpPage(pageNo) {
	
	if(!pageNo){
		pageNo = 1;
	}
	var supName =$("#supName").val();
	var supManaEval =$("#supManaEval").val();
	var supExamResu =$("#supExamResu").val();
	var supCooperated =$("#supCooperated").val();
	var isOfficalWeb =($("#isOfficalWeb:checked").val()!=1?0:1);
	var isBid =($("#isBid:checked").val()!=1?0:1);
	var appCompTime1=$("#appCompTime1").val();
	var appCompTime2=$("#appCompTime2").val();
	var supAudit=$("#supAudit").val();
	
	if(arrCheck==null||arrCheck=="")
		arrCheck ="";
	
	var data = {
		suppTypes:arrCheck,
		supName:supName,
		supManaEval:supManaEval,
		supExamResu:supExamResu,
		supCooperated:supCooperated,
		isOfficalWeb:isOfficalWeb,
		isBid:isBid,
		appCompTime1:appCompTime1,
		appCompTime2:appCompTime2,
		supAudit:supAudit,
		tag1Cds: getTagCds('tag1List'),
		tag2Cds: getTagCds('tag2List'),
		pageNo: pageNo
	};
	TB_showMaskLayer("正在搜索...");
	$.post("${ctx}/sup/sup-basic!list.action", data,function(result) {
		TB_removeMaskLayer();
		if (result) {
			$("#mailRight").html(result);
		}
	});
}
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
function doQuery(){
	if($("#search_condtion").css("display")=="none"){
		$("#search_condtion").show();
	}else{
		$("#search_condtion").hide();
	}
}
function doDeleteSup(id){
	if(id){
		if(confirm("确定要删除该记录吗？")){
		  var param={id:id};
		  $.post("${ctx}/sup/sup-basic!supDeleteFlg.action",param,function(result){
			if(result==1){
				//alert("删除成功");
				doQuerySup();
			}
		});
		}
	}
}
//刷新
function refreshMain(){
	window.location.href = '${ctx}/sup/sup-basic!main.action';
}
</script>
</body>
</html>
