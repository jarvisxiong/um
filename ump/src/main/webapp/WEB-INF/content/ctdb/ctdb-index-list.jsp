<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>成本数据库</title>
	
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/ctdb/ctdb.css"  />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>	
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>	
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>		
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/ctdb/ctdb-index.js"></script>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"  />
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>

</head>
<body>
<div id="ctdbContainer" >
	<div id="bodyHead" class="bodyHead">
		<div class="fLeft banTitle">成本数据库</div>
		
		<div class="fRight">
			<%--导出权限 --%>		
			<security:authorize ifAnyGranted="A_CTDB_EXPORT">
				<input type="button" class="btn_new btn_export_new" id="exportToExcel"  onclick="exportToExcel();" value="导出"/>
			</security:authorize>
			<input type="button" class="btn_new btn_full_new" onclick="window.open(location.href)" value="全屏"/>
			<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href" value="刷新"/>
		</div>
		<div id="listBnt" class="fRight" style="display: none;">
			<%--新增权限 --%>	
			<security:authorize  ifAnyGranted="A_COST_CTDB_EDIT">
				<input type="button" class="btn_new btn_add_new" id="new"  onclick="newListForm();" value="新增" />
				<input type="button" class="btn_new btn_green_new" id="import" onclick="impWin('1');"  value="导入"/>
			</security:authorize>			
			<s:url id="ctdbContentListTmp" action="download" namespace="/app"  includeParams="none"  >
				<s:param name="fileName">ctdbContenListtpm.xls</s:param>
				<s:param name="realFileName">清单数据库模板</s:param>
				<s:param name="bizModuleCd">resDownload</s:param>
			</s:url>
			<!-- <input type="button" value="导出Excel" id="exportExcel" style="height:22px; line-height:22px;margin-top: 3px;background-color: #6eb1cf;border: 1px solid #45738d;color: #fff;width: 68px;cursor: pointer;" onclick="downExcelTemp('${ctdbContentListTmp}');"></input> -->
			<%--导出权限 --%>	
			<security:authorize ifAnyGranted="A_CTDB_EXPORT">
				<input type="button" class="btn_new btn_export_new" value="导出模板" id="exportExcel" onclick="impWin('2');" style="width:70px;"/>
			</security:authorize>
		</div>
		<div class="fRight">
			<%--搜索权限 --%>		
			<security:authorize ifAnyGranted="A_COST_CTDB_QUERY">
				<input type="button" class="btn_new btn_senior_new"  onclick="getSearchForm();" style="width:80px;" value="高级搜索" />
				<input type="button" class="btn_new btn_query_new"  id="leftsearchBnt" onclick="leftsearch();"  value="搜索"/>
			</security:authorize>
		</div>
	</div>
	<div style="clear: left;">
			<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
				<tr>
					<td id="leftPanel"
						style="width:150px;border-right: 1px solid #8c8f94; background-color: #E4E7EC;"
						valign="top">
						<div id="treeDetail" style="height: 100%;min-height: 610px;">
							<table cellpadding="0" cellspacing="0" border="0" style="width: 100%;overflow-y:scroll;">
								<tr>
									<td>
										<div id="leftTreePanel" style="padding-top: 5px; float: left; overflow-y: auto;overflow-x: hidden; border: none;">
											<div id="tree1" class="leftPanel" rel="bidded"></div>
											<div id="tree2" class="leftPanel" rel="billing"></div>
											<div id="tree3" class="leftPanel" rel="list"></div>
										</div>
									</td>
									<td style="width: 5px;">
										<div id="noteResizeHandler" class="pd-chill-tip" title="您可以拖动，调整宽度"
											style="float:right; width:5px;height:100%;background: #eee url('${ctx}/resources/images/common/width_resize.gif') left center no-repeat;cursor: w-resize;">&nbsp;
										</div>
									</td>
								</tr>
							</table>
						</div>					
					</td>		
					<!-- 记录第一次加载树的状态 -->
					<td align="center" valign="top">
						<div id="rightPanel" style="height: 100%;height: 100%;line-height: 100%;text-align: center;">							
							<div id="lefTNav" class="leftNav">
								<ul class="tab">
									<li rel = "bidded">定标数据库</li>
									<li rel = "billing">结算数据库</li>
									<li rel = "list">清单数据库</li> <%--onclick="showListDown(this);" --%>
								</ul>
								<%-- 弹出框 --%>
								<%@ include file="ctdb-index-pop.jsp"%>
							</div>			 
							<div id="newListForm" class="default"></div>
							<div id="searchForm" class="default"></div> 
							<div id="tableView" style="clear: left;">
								<div id="searchPanel">
									<div id="search1" style="display:none;" rel="bidded">
										<%@ include file="ctdb-index-fixedBidForm.jsp"%>
									</div>
									<div id="search2" style="display:none;" rel="billing">
										<%@ include file="ctdb-index-billingAppForm.jsp"%>
									</div>
									<div id="search3" style="display:none;" rel="list">
										<%@ include file="ctdb-index-listContentForm.jsp"%>
									</div>
								</div>
								<%-- 以下是数据列表、分页信息 --%>
								<div id="rsTable">
								
								</div>
							</div>			
						</div>
					</td>
			   </tr>
			</table>
	</div> 
</div>

<script type="text/javascript">
//加载区域项目树
$(function(){ 

	//标签点击事件
	$("ul.tab li").click(function(){
		$(this).addClass('li-click').siblings().removeClass('li-click');
		closeTabSearch();
		searchByModuleId($(this).attr('rel'));
	});

	//默认第一个选中
	$("ul.tab li").eq(0).click();
	
	//左右拖拉
    $('#leftPanel').resizable({
        handler: '#noteResizeHandler',
        min: { width: 150, height: ($('#leftPanel').height()) },
        max: { width: 400, height: ($('#leftPanel').height()) },
        onResize: function(e) {
        	$('#treeDetail').width($('#leftPanel').width()-5);
        	$('#tree1').width($('#leftPanel').width()-5);
        	$('#tree2').width($('#leftPanel').width()-5);
        	$('#tree3').width($('#leftPanel').width()-5);
        }
    });
	
	//判断浏览器,根据情况设置菜单树高度
	if($.browser.msie) {
	  	$("#treeDetail").css({height:'446px'});
    }
    else if($.browser.safari){
    	$("#treeDetail").css({height:'500px'});
    }else if($.browser.mozilla){
    	$("#treeDetail").css({height:'525px'});
    }else if($.browser.opera) {
       // alert("this is opera");
    }else {
       // alert("i don't konw!");
    }	

	//加載三顆樹
	loadTree('tree1','bidded');
	loadTree('tree2','billing');
	loadTree('tree3','list');
	
}); 

</script>
</body>
</html>