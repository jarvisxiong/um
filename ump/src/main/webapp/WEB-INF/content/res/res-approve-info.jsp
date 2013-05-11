<%@page import="com.hhz.ump.util.DictMapUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	<meta http-equiv="Content-Type" content="text/html" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/res/resCss.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
	<title><%=DictMapUtil.getMapModuleType().get(JspUtil.findString("moduleTypeCdSrh")) %></title>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script charset="utf-8" src="${ctx}/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${ctx}/kindeditor/lang/zh_CN.js"></script>
</head>

<body style="background-color:#fff;" id="hBody">
	<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
	<div id="idDivTitle" class="title_bar">
		<div style="font-size:14px;float:left">表单模板</div>
		<s:if test="moduleTypeCdSrh==null || moduleTypeCdSrh==0">
		<div style="float:left;font-size:12px;padding-left:10px;" class="quickItem"><a href="javascript:openResFile();" style="color:#0167A2; display:none;" >下载权责手册</a>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red;">上市公司员工请使用“地产控股”下的表单</span></div>
		</s:if>
		<div class="title_bar_right">
			<input type="button" value="刷新" class="btn_blue" onclick="window.location.href='${ctx}/res/res-approve-info.action?moduleTypeCdSrh=${moduleTypeCdSrh}'" />
			<s:if test="moduleTypeCdSrh==null || moduleTypeCdSrh==0">
			<input type="button" value="合同文本库" style="width:100px;" class="btn_green" onclick="javascript:doContLib();" />
			<security:authorize ifAnyGranted="A_QZSP_ZLSZ">
			<input type="button" value="助理设置" class="btn_green" onclick="showAssistantWin();" />
			</security:authorize>
			</s:if>
			<input type="button" value="全屏" class="btn_green" onclick="window.open('${ctx}/res/res-approve-info.action?moduleTypeCdSrh=${moduleTypeCdSrh}')" />
		</div>
	</div>
	<div id="idDivParent" class="divParent" style="width:100%;">
		<table style="width:100%;" >
			<tr>
				<td id="leftPanel" style="width:187px;height:700px;background-color: #E4E7EC;" valign="top">
					<div style="float:left;">
					<div>
					<input  value="搜索表单..." type="text" class="quicksearch" style="color: #CCCCCC;margin:5px 3px;" onkeyup="searchTreeNode(this)" onblur="resetSearchApproveInput(this);" onclick="clearSearchApproveInput(this);" id="inputSearchVal"/>
					</div>
					<div id="divTreeP" class="divTreeP" style="float:left; width:180px; border:none; overflow-x:hidden;">
						<!-- 修改 -->
						<div id ="search-div">
						</div>
						<div id ="tree-div">
						</div>
					</div>
					</div>
					<div id="noteResizeHandler" class="pd-chill-tip" title="您可以拖动,调整宽度" style="width:5px;height:100%;float:left; cursor:w-resize;">
					&nbsp;
					</div>
				</td>
				<td id="rightPanel" valign="top">
					<div id="idDivContentPanel" style="width:100%;"> 
					<div id="contentList" style="border:0px;width:100%;">
					<div id="content" style="width:100%;"> 
						<s:form id="mainFormSearch" action="res-approve-info!load.action" method="post">
							<input type="hidden" id="moduleTypeCdSrh" name="moduleTypeCdSrh" value='${moduleTypeCdSrh}'/>
						</s:form>
					</div>
					</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<security:authorize ifAnyGranted="A_JBPM_ROLE"><s:set var="hasJbpm" value="true"></s:set></security:authorize>
	 
	<!-- 缓存标志, 用于计算页面高度 ,值[0,1] -->
	<input type="hidden" id="flg_firstFlg"		value="0"/>
	<input type="hidden" id="flg_baseMinHeight"	value="100"/>
	<input type="hidden" id="flg_baseDocHeight"	value="100"/>
	<!--例外事项权限	-->
	<input type="hidden" id="flg_lwsx_p" value="<%=SpringSecurityUtils.hasRole("A_QZSP_WJSP_SEND") %>"/>
	 
	<!--隐藏搜索条件 -->
	<input class="srh_hidden" type="hidden" id="srh_listMode" name="listMode"/>
	<input class="srh_hidden" type="hidden" id="srh_qsCondition" name="qsCondition"/>
	<input class="srh_hidden" type="hidden" id="srh_selectNodeCd" name="selectNodeCd"/>
	<input class="srh_hidden" type="hidden" id="srh_selectOpinion" name="selectOpinion"/>
	<input class="srh_hidden" type="hidden" id="srh_approveCd" name="approveCd"/>
	<input class="srh_hidden" type="hidden" id="srh_filter_LIKES_statusCd" name="filter_LIKES_statusCd"/>
	<input class="srh_hidden" type="hidden" id="srh_filter_GED_startDate" name="filter_GED_startDate"/>
	<input class="srh_hidden" type="hidden" id="srh_filter_LTD_startDate" name="filter_LTD_startDate"/>
	<input class="srh_hidden" type="hidden" id="srh_filter_LIKES_landproject" name="filter_LIKES_landproject"/>
	<input class="srh_hidden" type="hidden" id="srh_filter_LIKES_titlename" name="filter_LIKES_titlename"/>
	<input class="srh_hidden" type="hidden" id="srh_auditorUserNames" name="auditorUserNames"/>
	<input class="srh_hidden" type="hidden" id="srh_auditorUserCds" name="auditorUserCds"/>
	<input class="srh_hidden" type="hidden" id="srh_creatorUserNames" name="creatorUserNames"/>
	<input class="srh_hidden" type="hidden" id="srh_creatorUserCds" name="creatorUserCds"/>
	<input class="srh_hidden" type="hidden" id="srh_filter_LIKES_authTypeName" name="filter_LIKES_authTypeName"/>
	<input class="srh_hidden" type="hidden" id="srh_filter_LIKES_authTypeCd" name="filter_LIKES_authTypeCd"/>
	<input class="srh_hidden" type="hidden" id="srh_filter_LIKES_randomNo" name="filter_LIKES_randomNo"/>
	<input class="srh_hidden" type="hidden" id="srh_filter_LIKES_displayNo" name="filter_LIKES_displayNo"/>
	<input class="srh_hidden" type="hidden" id="srh_quicksearchValue" name="quicksearchValue"/>
	<input class="srh_hidden" type="hidden" id="srh_pageNo" name="page.pageNo"/>
	<input class="srh_hidden" type="hidden" id="srh_selectedOrderBy" name="selectedOrderBy"/>
	<input class="srh_hidden" type="hidden" id="srh_selectedOrder" name="selectedOrder"/>
	<input class="srh_hidden" type="hidden" id="srh_moduleTypeCd" name="moduleTypeCdSrh" value="${moduleTypeCdSrh}" />
	
	
	
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/js/res/resApprove_0220.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript"> 
		
		
		//resApprove.js
		resetLeftPanel();
		
		//左右拖拉
	    $('#leftPanel').resizable({
	        handler: '#noteResizeHandler',
	        min: { width: 182, height: ($('#leftPanel').height()) },
	        max: { width: 400, height: ($('#leftPanel').height()) },
	        onResize: function(e) {
	        	$('#divTreeP').width($('#leftPanel').width()-5);
	        }
	    });
	    
	    $(function(){
		//载入机构树
		initTree();
		//加载数 
	    //loadTree();
		});
	    
		////////////
	    var id='${id}';
		if(!isEmpty(id)){
			openDetail(id,'1','');
		}
		$(function(){
			if(isEmpty(id)){
				searchRes();
			}
		});
		 function showAssistantWin(){
			 ymPrompt
				.win( {
					message : "${ctx}/res/res-accredit!input.action",
					width : 600,
					height : 450,
					title : '助理设置',
					iframe : true,
				//	handler : function(){},
					btn:[['关闭','close']]
				});
		 }
		 function openResFile(){
			// parent.TabUtils.newTab("151","管理体系(密)",_ctx+"/oa/company-file.action",true);
			 ymPrompt
				.win( {
					message : "${ctx}/common/res-download.jsp",
					width : 300,
					height : 210,
					title : '权责手册下载',
					iframe : true,
					btn:[['关闭','close']]
				});
		 }
			 /**
			 合同文本库
			 **/
			function doContLib() {
				var getContUrl = "${ctx}/sc/sc-contract-templet-info.action";
				if (parent.TabUtils == null)
					window.open(getContUrl);
				else
					parent.TabUtils.newTab("scconInfo_Select", "合同文本库", getContUrl, true);

			}
	</script>
	
</body>
</html>
