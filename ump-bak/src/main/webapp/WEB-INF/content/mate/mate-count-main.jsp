<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<link type="text/css" href="${ctx}/resources/css/common/TreePanel.css" rel="stylesheet"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cont/cont.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.css"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	
	<style type="text/css">
		#search_condtion{
			font-weight: normal;
			/*background-color:#D7DAD9;*/
			width:100%;
			/*border-bottom:1px solid #cbcbcb;*/
		}
		#search_condtion th{
			text-align: right;
			font-weight: normal;
			padding-left:5px;
			padding-right:5px;
		}
	</style>
</head>
<body>
<form action="${ctx}/mate/mate-basic!list.action" method="post" id="searchForm">
	<div id="mailMianContainer" >
	<input type="hidden" id="currentPageNo" value="1" />
	<input type="hidden" id="contTypes" name="contTypes" />
	<input type="hidden" id="clearAudit" name="clearAudit" value="${clearAudit}"/>
	<input type="hidden" id="clearStatus" name="clearStatus" value=""/>
	<input type="hidden" id="useUnitName" name="useUnitName" value=""/>
	<div class="title_bar">
		<div class="fLeft">
			<div class="fLeft banTitle">甲供料台账</div>
			<div class="fLeft" style="margin-left:10px;">显示
				<input type="checkbox" id="chkExtFields" onclick="showExtFields()" checked="checked"/>
			</div>
		</div>
		<div style="float:right;">
			<input type="button" class="btn_new btn_senior_new" onclick="doQuery();" value="高级搜索" style="width:70px;"/>
			
			<security:authorize ifAnyGranted="A_MATE_COMM">
			<input type="button" class="btn_new btn_add_new" onclick="doAddMateBasic();" style="width:90px;" value="新增基本信息" />
			</security:authorize>
			
			<security:authorize ifAnyGranted="A_MATE_ADMIN">
			<input type="button" class="btn_new btn_blue_new" style="width:80px;" onclick="doProjAuthority();" value="分配权限" />
			</security:authorize>
			
			<security:authorize ifAnyGranted="A_MATE_COMM,A_MATE_TYPEDEL">
			<input type="button" class="btn_new btn_blue_new" style="width:80px;" onclick="doMateType();" value="甲供料类型"/>
			</security:authorize>
			
			<input type="button" class="btn_new  btn_full_new" onclick="window.open(location.href);" value="全屏" />
			<input type="button" class="btn_new  btn_refresh_new" onclick="window.location.href=location.href;" value="刷新" />
		</div>
	</div>
	<!-- mailInfo end -->
	<div id="maiMainBottom" style="width:100%; height:100%;">
		<table style="width:100%;">
		<tr>
		<td id="leftPanel" style="width:150px;border-bottom:1px solid #8C8F94; border-right:1px solid #8c8f94;background-color: #E4E7EC;" valign="top">
			<table cellpadding="0" cellspacing="0" border="0" style="width:100%;" id="leftItemDiv">
				<tr>
					<td>
			  			<div id="itemDiv" style="min-height:500px;padding-top:5px; min-width:10px;overflow-x: hidden; overflow-y:auto;">加载中...</div>
					</td>
					<td style="width:5px;">
						<div id="noteResizeHandler" class="pd-chill-tip" title="您可以拖动,调整宽度" style="float:right; width:5px;height:100%;background: #eee url('${ctx}/resources/images/common/width_resize.gif') left center no-repeat;cursor: w-resize;">&nbsp;</div>
					</td>
				</tr>
			</table>
		</td>
		<td id="rightPanel" valign="top" rowspan="2">
	    	<div style="padding:10px 0;border-bottom: 1px solid #CBCBCB;" id="search_condtion">
				<table cellpadding="0" cellspacing="0" border="0" style="line-height: 22px;">
				<tr>
					<th>合同编号</th>
					<td><input type="text" id="contNo" name="contNo" style="width:120px" /></td>
					<th>合同名称</th>
					<td><input type="text" id="contName" name="contName" style="width:120px" /></td>
				</tr>
				<tr>
					<th>供货单位名称</th>
					<td><input type="text" id="suppUnitName" name="suppUnitName" style="width:120px" /></td>
					<th>是否已提交</th>
					<td><select style="width: 120px;" id="useStatus" name="useStatus" >
							 <option value="">请选择</option>
							 <option value="0">否</option>
							 <option value="1">是</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>施工合同编号</th>
					<td><input type="text" id="exeContNo" name="exeContNo" style="width:120px" /></td>
					<th>施工单位名称</th>
					<td><input type="text" id="exeUnitName" name="exeUnitName" style="width:120px" /></td>
					<td>
						<input type="button" class="btn_new  btn_query_new" onclick="doQueryCont();" value="搜索" />
					</td>
				</tr>
				</table>
			</div>
		 	<div id="mateBasicList" style="padding-left: 5px;">	  
		 		<%-- 搜索结果列表 --%>
			</div>
			
			<div id="searchUserDiv" class="searchUserDiv">
				<div class="inform_div" >请选择左侧目录搜索</div>
			</div>
	    </td>
	    </tr>
	    </table>
	</div>
</div>
</form>


<script type="text/javascript">
	var arrCheck ="";//存储树形结构节点值
	
	$(function(){
		getContTree("itemDiv");//初始化树形结构
		//左右拖拉
	    $('#leftPanel').resizable({
	        handler: '#noteResizeHandler',
	        min: { width: 150, height: ($('#leftPanel').height()) },
	        max: { width: 400, height: ($('#leftPanel').height()) },
	        onResize: function(e) {
	        	//$('#divTreeP').width($('#leftPanel').width()-5);
	        }
	    });
	})

	function doProjAuthority(){
		var url ="${ctx}/mate/mate-project-code!list.action";
		if(parent.TabUtils==null)
			window.open(url);
		else
		  parent.TabUtils.newTab("mateProjectType","供料权限",url,true);
	}
	/**
	 * doMateType
	 * 新增甲供料类型
	 */
	function doMateType(){
		var url="${ctx}/mate/mate-type!input.action";
		if(parent.TabUtils==null)
			window.open(url);
		else
		  parent.TabUtils.newTab("mateType","甲供料类型",url,true);
	}
	/**
	 *getContTree
	 *初始化甲供料类别树
	 * itemDiv 所在页面位置 
	 */
	function getContTree(itemDiv){
		$.post("${ctx}/mate/mate-type!getMateTypeTree.action", function(result){
			
			$('#'+itemDiv).empty();
			var tree = new TreePanel({
				renderTo:itemDiv,
				'root' : eval('('+result+')'),
				'ctx':'${ctx}'
			});
			tree.haveClickText =true;
			tree.addListener("check",function(node){
				arrCheck =tree.getCheckedId();
				finType=tree.getCheckedType();
			});
			tree.render();
			tree.on(function(node){
				$("#id").val(node.attributes.trueId);//节点ID
				$("#itemName").val(node.attributes.text);//节点名
				$("#parentCd").val(node.attributes.parentId);//公司CD
				$("#parentName").val(node.attributes.parentName);
				$("#itemType").val(node.attributes.finType);
				$("#itemCd").val(node.attributes.finItemCd);
			});
		});
	}
	function doQueryCont(){
		var contNo =$("#contNo").val();
		var suppUnitName =$("#suppUnitName").val();
		var useStatus =$("#useStatus").val();
		var useUnitName =$("#useUnitName").val();
		var clearStatus = $("#clearStatus").val();
		var contName = $("#contName").val();
		var exeContNo = $("#exeContNo").val();
		var exeUnitName = $("#exeUnitName").val();
	//	var clearStatus =$("input[type=radio][name='clearStatus']:checked").val()==null?null:$("input[type=radio][name='clearStatus']:checked").val();
		if(clearStatus==2){
			$("#clearAudit").val('1');
		}else{
			$("#clearAudit").val('');
		}
		$('#searchUserDiv').hide();
		$('#mateBasicList').empty();
		$('#mateBasicList').mask('正在搜索,请稍候...');
		$.post("${ctx}/mate/mate-basic!list.action",
			{
				contTypes:arrCheck,
				suppUnitName:suppUnitName,
				useUnitName:useUnitName,
				useStatus:useStatus,
				clearStatus:clearStatus,
				contName:contName,
				exeContNo:exeContNo,
				exeUnitName:exeUnitName,
				contNo:contNo
		    },
			function(result) {
				$('#mateBasicList').unmask();
				if (result) {
					$("#mateBasicList").html(result);
					//$('#itemDiv').height($('#rightPanel').height()-$('#btnLeftQuery').height());
				}
			}
	    );
	}
	function doExportExcel(){
		if(!window.confirm('确认导出?')){
			return;
		}
		$("#contTypes").val(arrCheck);
		var pageTotal =$("#pageTotal").val();
		if(typeof(pageTotal)=="undefined"||pageTotal!=1){
			alert("一次只能导出一条数据");
			return false;
		}
		$("#searchForm").attr("action", "${ctx}/mate/mate-basic!exportExcel.action");
		$("#searchForm").submit();
	}
	function getDetail(id){
		var url="${ctx}/mate/mate-basic!input.action?id="+id;
		if(parent.TabUtils==null)
			window.open(url);
		else
		  parent.TabUtils.newTab("mate-basic-input","基本信息",url,true);
	}
	function doAddMateType(){
		var url="${ctx}/mate/mate-count!input.action";
		if(parent.TabUtils==null)
			window.open(url);
		else
		  parent.TabUtils.newTab("mate-count-input","新增详细信息",url,true);
	}
	function doAddMateBasic(){
		var url="${ctx}/mate/mate-basic!input.action";
		if(parent.TabUtils==null)
			window.open(url);
		else
		  parent.TabUtils.newTab("mate-basic-input","新增基本信息",url,true);
	}
	
	function doQuery(){
		if($("#search_condtion").css("display")=="none"){
			$("#search_condtion").show();
		}else{
			$('#search_condtion').hide();
		}
	}
	function jumpPage(pageNo) {
		if(!pageNo){
			pageNo = 1;
		}

		$("#contTypes").val(arrCheck);
		
		$("#pageNo").val(pageNo);
		$('#mateBasicList').mask('搜索中...');
		$("#searchForm").ajaxSubmit(function(result) {
			$('#mateBasicList').unmask();
			$("#mateBasicList").html(result);
		});
	}
	function jumpPageTo() {
		var index = $("#pageTo").val();
		index = parseInt(index);
		if (index > 0) {
			jumpPage(index);
		}
	}
	
	function showExtFields(){
		var chk = $('#chkExtFields').attr("checked");
		if(chk){
			$('#leftPanel').show();
			$('#leftItemDiv').show();
		}else{
			$('#leftPanel').hide();
			$('#leftItemDiv').hide();
		}
	}
</script>
</body>
</html>
