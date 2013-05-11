<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<%@ include file="/common/nocache.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cont/cont.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.css"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	
	<style type="text/css">
		#search_condtion{
			padding:5px 10px;
			font-weight: normal;
			background-color:#D7DAD9;
			width:100%;
			padding:5px;
		}
		#search_condtion th{
			padding:2px 5px;
			text-align: right;
			font-weight: normal;
		}
	</style>
</head>
<body> 


<div id="mailMianContainer">
	<div class="title_bar">
		<div class="fLeft">
			<div class="fLeft banTitle">合同台账</div>
		</div>
		
		<div class="fRight">
			<input type="button" class="btn_new btn_senior_new" style="width:80px;" onclick="doQuery();" value="高级搜索" />
			<input type="button"  class="btn_new btn_query_new" onclick="doQueryCont();" value="搜索" style="margin-top:8px;"/>
			
			
			<security:authorize ifAnyGranted="A_CONTRACT_INPUT,A_CONT_MANA,A_CONTRACT_ADMIN,A_CONT_BIS_INPUT,A_CONT_HOTEL_INPUT">
			<s:if test="ledgerType!=1">
			<input type="button" class="btn_new btn_add_new" style="width:60px;" onclick="doAddContType();" value="新增"/>
		 	</s:if>
		 	</security:authorize>
		 	
		 	
		 	<security:authorize ifAnyGranted="A_CONT_MANA">
			<s:if test="ledgerType==1">
			<input type="button" class="btn_new btn_add_new" style="width:60px;" onclick="doAddContType();" value="新增"/>
			</s:if>
			</security:authorize>
			
		
			<input type="button" class="btn_new btn_stat_new" style="width:60px;" onclick="doCaptain();" value="统计" />
			 
			<security:authorize ifAnyGranted="A_CONTRACT_ADMIN">
		 	<input type="button" class="btn_new btn_blue_new" style="width:80px;" onclick="doProjAuthority();" value="分配权限" />
			</security:authorize>
		
		 	<security:authorize ifAnyGranted="A_CONTRACT_ADMIN">
		 	<input type="button" class="btn_new btn_set_new" style="width:70px;" onclick="doContType();" value="合同类型"/>
		 	</security:authorize>
		 	
			<input type="button" class="btn_new btn_full_new"  onclick="window.open(location.href);" title="全屏" value="全屏"/>
			<input type="button" class="btn_new btn_refresh_new"  onclick="window.location.href= location.href;"  title="刷新" value="刷新"/>
		</div>
		
	</div>
	<!-- mailInfo end -->
	<div id="maiMainBottom" style="width:100%; height:100%;">
		<table style="width:100%;">
		<tr>
		<td id="leftPanel" style="width:80px;border-bottom:1px solid #8C8F94; border-right:1px solid #8c8f94;background-color: #white;" valign="top">
			<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
				<tr>
					<td>
	  					<div id="itemDiv" style="padding-top:5px;min-height:450px; min-width:100px; overflow-x: hidden; overflow-y:auto;">加载中...</div>
	  				</td>
					<td style="width:5px;">
						<div id="noteResizeHandler" class="pd-chill-tip" title="您可以拖动,调整宽度" style="float:right; width:5px;height:100%;background: #eee url('${ctx}/resources/images/common/width_resize.gif') left center no-repeat;cursor: w-resize;">&nbsp;</div>
					</td>
				</tr>
			</table>
		</td>
		<td id="rightPanel" valign="top">
		
	   	 	<div style="height: 100%;background-color: white;line-height: 22px;border-bottom:1px solid #cbcbcb;" id="search_condtion">
				<form action="${ctx}/cont/cont-ledger!list.action" method="post" id="searchForm">
					<%-- 注意： 分页，这里很重要  --%>
					<input type="hidden" id="ledgerType" name="ledgerType" value="${ledgerType}"/>
					<%-- 注意： 分页，这里很重要  --%>
					<input type="hidden" name="pageNo" id="currentPageNo" value="${pageNo}"/>
					<%-- 注意： 选择的项目 --%>
					<input type="hidden" name="contTypes" id="contTypes"/>
					
			 		<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th>科目类型</th>
						<td><s:if test="ledgerType==1">
								<s:select cssStyle="width:120px" list="mapTypeByEstate" listKey="key" listValue="value" style="width:120px" name="typeByCd"/>
							</s:if>
							<s:elseif test="ledgerType==2">
							 	<s:select cssStyle="width:120px" list="mapTypeByBis" listKey="key" listValue="value" style="width:120px" name="typeByCd"/>
							</s:elseif>
							<s:else>
								<s:select cssStyle="width:120px" list="mapTypeByHotel" listKey="key" listValue="value" style="width:120px" name="typeByCd"/>
							</s:else>
						</td>
						<th>合同编号</th>
						<td><input style="width:120px;" type="text" name="contNo"/></td>
						<th>合同名称</th>
						<td><input style="width:120px;" type="text" name="contName"/></td>
					</tr>
					<tr>
						<th>合同状态</th>
						<td><select style="width:120px;" name="contStatus">
						       <option value=""></option>
		                       <option value="0">未完未结</option>
		                       <option value="1">已完未结</option>
		                       <option value="2">已完已结</option>
			                 </select>
			            </td>
			            <th>进度状态</th>
						<td><select style="width:120px;" name="contProcess">
		                       <option value=""></option>
		                       <option value="0">未开工</option>
		                       <option value="1">按期</option>
		                       <option value="2">滞后</option>
		                       <option value="3">延期竣工</option>
		                       <option value="4">已竣工</option>
			                </select>
			            </td>
			            <th>当前状态</th>
			           	<td><select style="width:120px;" name="auditStatus">
		                       <option value=""></option>
		                       <option value="0">未提交</option>
		                       <option value="1">待审核</option>
		                       <option value="2">已审核</option>
		                 	</select>
						</td>
					</tr>
					<tr>
						<th>乙方</th>
						<td><input style="width:120px" type="text" name="partB"/></td>
				 		<th>实际供方</th>
				 		<td><input style="width:120px" type="text" name="realProvName"/></td>
				 		<th>是否战略</th>
				 		<td>
				 			<select style="width:120px;" name="strageFlg">
				 				<option value=""></option>
				 				<option value="1">是</option>
				 				<option value="0">否</option>
				 			</select>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td colspan="4" style="padding-top: 5px;line-height: 40px;" >
					   		<input type="button" class="btn_new btn_query_new" onclick="doQueryCont();" value="搜索"/>
						 	<security:authorize ifAnyGranted="A_CONTRACT_QUERYEXP,A_CONTRACT_INPUT,A_CONTRACT_AUDIT,A_CONTRACT_ADMIN">
					   		<input type="button" class="btn_new btn_export_new" onclick="doExportExcel();" value="导出" />
						 	</security:authorize>
						 	<security:authorize ifAnyGranted="A_CONTRACT_FIN,A_CONTRACT_ADMIN">
						   	<input type="button" class="btn_new btn_export_new" onclick="doExportByFin();" value="财务导出" style="width:70px;"/>
						   	<input type="button" class="btn_new btn_export_new" onclick="doImportByFin();" value="财务导入" style="width:70px;"/>
						 	</security:authorize>
						</td>
						<td>
							<input type="checkbox" name="onlyCompanyFlg" id="onlyCompanyFlg" />查看挂在项目公司下的合同
						</td>
					</tr>
				</table>
				</form>
		  	</div>
		  	
		  	
		  	<div id="mailRight">
			  <%-- 搜索结果列表 --%>
	  		</div>
	  		
	  		
			<div id="searchUserDiv" class="searchUserDiv">
			 	<div class="inform_div" >请选择左侧目录搜索</div>
			</div>
	    </div>
	    </td>
	    </tr>
	    <%--
	    <tr>
	    	<td style="padding:5px;" valign="top" id="btnLeftQuery">
		  		
		  	</td>
	    </tr>
	     --%>
	    </table>
	</div>
</div>
	    

<script type="text/javascript">

var arrCheck ="";//存储树形结构节点值

$(function(){
	getContTree("itemDiv");//初始化树形结构
	//左右拖拉
    $('#leftPanel').resizable({
        handler: '#noteResizeHandler',
        min: { width: 100, height: ($('#leftPanel').height()) },
        max: { width: 400, height: ($('#leftPanel').height()) },
        onResize: function(e) {
        	//$('#divTreeP').width($('#leftPanel').width()-5);
        }
    });
	
	//默认查询
    jumpPage(1);
})

function doContType(){
	var url="${ctx}/cont/cont-contract-type!input.action";
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("contType","合同类型",url,true);
}
function doProjAuthority(){
	var url ="${ctx}/cont/cont-project-code!main.action";
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("contProjectType","合同权限",url,true);
}
function getContTree(itemDiv){
	
	var data = {ledgerType: $("#ledgerType").val()};
	$.post("${ctx}/cont/cont-contract-type!getContTypeTree.action", data, function(result){

		$('#'+itemDiv).empty();
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
		});
		tree.addListener("check",function(node){
			arrCheck =tree.getAllCheckedId();
			finType=tree.getCheckedType();
			//搜索台账
			doQueryCont();
		});
		tree.render();
	});
}
//查看明细
function getDetail(id){
	
	var ledgerType = $("#ledgerType").val();
	var url="${ctx}/cont/cont-ledger!input.action?id="+id+"&ledgerType="+ledgerType;
	var showName;
	if("1"==ledgerType){
		showName="合同台账查看/新增";
	}else{
		showName="商业台账查看/新增";
	}
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cont-ledger-input",showName,url,true);
}
//新增合同
function doAddContType(){
	var ledgerType=$("#ledgerType").val();
	var url="${ctx}/cont/cont-ledger!input.action?ledgerType="+ledgerType;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cont-ledger-input","新增",url,true);
}
//删除合同
function doDeleteCont(id){
	if(id!=null){
		if(confirm("确定要删除该条记录吗？")){
			var url ="${ctx}/cont/cont-ledger!delete.action";
			$.post(url,{id:id}, function(result) {
				doQueryCont();
			});
		}
	}
}

/**
 * 驾驶舱
 */
function doCaptain(){
	var ledgerType=$("#ledgerType").val();
	var url="${ctx}/cont/cont-ledger!captain.action?ledgerType="+ledgerType;
	if(parent.TabUtils==null)
	   window.open(url);
	else
	   parent.TabUtils.newTab("cont-ledger-captain","统计",url,true); 
}
//暂不使用
function doUpdateData(){
	var url ="${ctx}/cont/cont-ledger!updateData.action";
	$.post(url,function(result) {
		
	});
}
//工具栏搜索
function doQuery(){
	if($("#search_condtion").css("display")=="none"){
		$("#search_condtion").show();
	}else{
		$("#search_condtion").hide();
	}
}
//翻页
function jumpPage(pageNo) {

	if(!pageNo){
		pageNo = 1;
	}
	
	$("#currentPageNo").val(pageNo);
	
	if(arrCheck == null || arrCheck == ""){
		arrCheck = "";
	}
	$("#contTypes").val(arrCheck);
	$("#searchForm").attr("action", "${ctx}/cont/cont-ledger!list.action");
	
	TB_showMaskLayer("正在搜索...");
	$("#searchForm").ajaxSubmit(function(result){
		TB_removeMaskLayer();
		$('#searchUserDiv').hide();
		$("#mailRight").html(result);
	});
}
//点击搜索
function doQueryCont(){  
	jumpPage();
}
//导出合同
function doExportExcel(){
	if("" == arrCheck){
		alert("请选择合同类别(项目公司)");
		return false;
	}
	TB_showMaskLayer("正在导出");
	
	$("#searchForm").attr("action", "${ctx}/cont/cont-ledger!exportExcel.action");
	
	$("#searchForm").submit();
	setTimeout(function(){
		$('#TB_overlay,#TB_HideSelect,#TB_load').remove();
	},2000);
}
//财务导出
function doExportByFin(){
	if(""== arrCheck){
		alert("请选择合同类别树(项目公司)");
		return false;
	}
	TB_showMaskLayer("正在导出");
	$("#searchForm").attr("action", "${ctx}/cont/cont-ledger!exportExcel.action?excelByFin=true");
	$("#searchForm").submit();
	setTimeout(function(){
		$('#TB_overlay,#TB_HideSelect,#TB_load').remove();
	},2000);
}
//财务导入
function doImportByFin(){
	var html='<form id="importContForm" enctype="multipart/form-data" method="post" action="${ctx}/cont/cont-ledger!importxcelByFin.action"><input id="importBisProjectId" name="bisProjectId" type="hidden" /><table><tbody><tr> <td style="padding-left: 8px; padding-top: 5px;" colspan="3"> <input type="file" style="line-height: 22px; height: 22px; margin-bottom: 3px;" name="importCont" id="importCont"> </td> </tr></tbody></table></form>';
	ymPrompt.confirmInfo({
		icoCls : "",
		autoClose:false,
		message : "<div id='selectTypeDiv'><img align='absMiddle' src='${ctx}/images/loading.gif'></div>",
		width : 300,
		height :140,
		title : "导入数据",
		afterShow : function() {
		  $("#selectTypeDiv").html(html);
		 // $('#importBisProjectId').val($('#bisProjectIdFact').val()); 
		},
		handler : function(btn){
			if(btn=='ok'){
				if(isEmpty($("#importCont").val())) {
					 alert("请先选择要导入的文件"); 
					 $("#importCont").focus();
		             return false;
		         }
				ymPrompt.close();
				TB_showMaskLayer("正在导入");
				$("#importContForm").ajaxSubmit(function(result){
				TB_removeMaskLayer();
				result ='<div style="margin:8px">'+result+'</div';
				ymPrompt.alert({title:"操作结果",icoCls:"",message:result});
			});
			}else{
				ymPrompt.close();
				}
		},
		btn:[["保存",'ok'],["取消",'cancel']],
		closeBtn:true
	});
}
isEmpty = function (str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
}; 

//刷新
function refreshMain(){
	window.location.href="${ctx}/cont/cont-ledger!main.action";
} 
</script>
</body>
</html>
