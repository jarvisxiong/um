<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>合同模块</title>
<meta http-equiv="Content-Type" content="text/html" />
<%@ include file="/common/global.jsp"%>
<%@ include file="/common/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/res/resCss.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/sc/sc.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
<link rel="stylesheet" type="text/css" media="screen" href="/PowerDesk/resources/css/common/select.css" />
<link rel="stylesheet" type="text/css" media="screen" href="/PowerDesk/resources/css/common/TreePanel.css" />
<link rel="stylesheet" type="text/css" media="screen" href="/PowerDesk/resources/js/jqueryplugin/jqModal/jqModal.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common3.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
</head>
<body>
<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
<table id="idDivTitle" class="title_bar div100" cellpadding="0" cellspacing="0">
	<tr>
		<td class="hd-left">
		<input type="hidden" value="<%=SpringSecurityUtils.getCurrentUiid()%>" id="curUserId" />
		合同文本库
		</td>
		<td>
		 <s:url id="downUrl" action="download" namespace="/sc">
			<s:param name="fileName">hetongku.doc</s:param>
			<s:param name="realFileName">标准合同文本平台操作手册下载.doc</s:param>
			<s:param name="bizModuleCd">public</s:param>
			<s:param name="operator">inline;</s:param>
			<s:param name="id"></s:param>
	     </s:url> <a href="${downUrl}" class="txtRed">标准合同文本平台操作手册下载.doc</a>
	     &nbsp;&nbsp;如有疑问请联系营运管理中心 卢俊云(13636662709)
		<div style="float: right; color: white;" class="btn_green" onmousemove="underline(this);" onmouseout="removeUnderline(this);" onclick="window.open('${ctx}/sc/sc-contract-templet-info.action')">全屏</div>
		</td>
	</tr>
</table>
<div id="idDivParent" class="divParent div100" >
<table class="div100">
	<tr>
		<td id="leftPanel"  valign="top">
		<table cellpadding="0" cellspacing="0" border="0" class="div100">
			<tr>
				<td>
				<div id="searchApproveFix">
				<table border="0" cellpadding="0" cellspacing="0" class="div100">
					<tr>
						<td>
						<input value="搜索模板..." class="search-scTemplet" onkeyup="searchTreeNode(this)" onblur="resetSearchApproveInput(this);" onclick="clearSearchApproveInput(this);"id="inputSearchVal" />
						</td>
						<td style="width: 5px;"></td>
					</tr>
				</table>
				</div>
				</td>
			</tr>
			<tr>
				<td>
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td>
						<div id="divTreeP" class="divTreeP scTreePanel" ><!-- 修改 -->
						<div id="search-div"></div>
						<div id="tree-div"></div>
						</div>
						</td>
						<td style="width: 5px;">
						<div id="noteResizeHandler"	class="sc-resize" title="您可以拖动,调整宽度" >&nbsp;</div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
		<td id="rightPanel" valign="top">
			<div id="contentList"  class="div100">
			<div id="content" class="div100"><!-- 同res-approve-info-load.jsp一样,开始... -->
			<div id="rightHeadQuick" >
			<div class="title1 clearfix div100" >
				<table class="div100" id="quickSearchPanel">
					<tr>
						<td class="h-search">
						 <input type="button" id="bt_search" onclick="doQuickSeniorSearch();" class="btn_gray_65_20"  value="高级搜索" /></td>
						<td ></td>
						<td style="width: 140px;">
						<s:form id="scConInfoSearchForm" action="contract-templet-info!list.action" method="post">
							<div class="quickTitle">快速搜索:</div>
							<div class="quickItem" id="btnMyReco" onclick="searchMyReco();">我的合同</div>
						
						</s:form>
						</td>
					</tr>
				</table>
			</div>
			
		</div>
		<form id="mainFormSearch" action="sc-contract-templet-info!list.action" method="post">
			<div id="rightSearchHeadAdd">
			<div id="rightHeadTool" style="background-color: #EAEAEA; display: none;">
	
			<div class="condition_panel" style="padding-left: 10px;">
				<div>
				  项目名称: <input type="text" name="projectName" id="projectName" readonly="readonly" class="text" style="cursor: pointer;" />
				         <input type="hidden" id="projectCd" name="projectCd" value="" />
				 </div>
				<div>
					合同名称: <s:textfield cssClass="text" name="conName" id="conName" />
				</div>
				<div >
					合同编号: <input type="text" class="text" name="conNo" id="conNo" /></div>
				<div >
					创建人:<s:textfield cssStyle="cursor:pointer;" cssClass="text" id="curUserName" name="curUserName" maxlength="20" onkeyup="showSearchUser(this)" />
					       <input type="hidden" name="curUserCd" id="curUserCd" />
		        </div>
				<div style="display:none;">
				  	创建时间: <s:textfield name="startDate" onfocus="WdatePicker()" cssClass="text" />
				</div>
				<div style="display:none;">
					到: <s:textfield name="endDate" onfocus="WdatePicker()" cssClass="text" />
				</div>
				<div>
					合同状态:
					<select id="conStatusCd" name="conStatusCd" class="box" style="width: 137px">
						<option value="">--选择状态--</option>				
						<option value="10,20,30,40,45">进行中</option>					
						<option value="50">网批中</option>
						<option value="60">网批结束</option>
						<option value="70">可打印</option>
						<option value="80">已签署</option>
						<option value="1">已删除</option>
					</select>
					 <input type="hidden" id="divStatusCd" name="divStatusCd" />
				 </div>
				<div style="width: 100%">
				<div>
					<input type="button" class="btn_green" id="btn_search" onclick="doSearch();" value="<s:text name="common.search" />" /> 
					&nbsp;&nbsp; 
					<input type="button" id="bt_collapse" onclick="doClear();" class="btn_blue" value="清空" />
				</div>
					<%--搜索标准合同 --%> 
					<input type="hidden" id="isSearchByStanCon" name="isSearchByStanCon" value="true" /> 
					<%--合同模板类型 （商业、地产 ）--%> 
					<input type="hidden" id="moduleCd" value="${scContractParams.templetTypeCd}" />
				</div>
			</div>
		 </div>
			<table class="sc-create" cellpadding="0" cellspacing="0">
				<tr>
					<td class="sc-choose">
						<div >当前选择：</div>
						<div  id="idCurTempletName"></div>
					</td>
					<td class="sc-create-btnGround">
						<input type="button" class="addApprove" value="  创建标准合同" id="btnNewContract"	style="display: none;" /> 
					   	<span>&nbsp;&nbsp;</span>
						<input type="button" class="addApprove" value="  创建非标合同" id="btnNewNonContract" style="display: none;" />
					</td>
				</tr>
			</table>
			</div>
			<input type="hidden" id="frameId" value="${scContractParams.frameId}" />
			<input type="hidden" id="sctempletTypeId" name="sctempletTypeId" />
			<input type="hidden" id="isView" name="isView" value="1" /> 
			<input type="hidden" id="pageNo" name="pageNo" /> 
			<input type="hidden" id="isstandard"name="isstandard" /> 
			<input type="hidden" id="sn" name="sn" value="${scContractParams.sn}" />
			<%--是否需要自动生成合同台账 --%>
			<input type="hidden" id="ifNoContLeger" name="ifNoContLeger" value="${ifNoContLeger}"/>
		</form>
		</div>
		</div>
	
	
			<div id="tableDiv" style="width: 100%">
			<div class="titleBar">
			<ul class="cost-nav" id="cost-nav">
			</ul>
			</div>
	
			</div>
			<div id="searchResultPanel" style="float: left; width: 100%;"></div>
		</td>
	</tr>
</table>
</div>

<security:authorize ifAnyGranted="A_JBPM_ROLE">
	<s:set var="hasJbpm" value="true"></s:set>
</security:authorize>

<!-- 缓存标志, 用于计算页面高度 ,值[0,1] -->
<input type="hidden" id="flg_firstFlg" value="0" />
<input type="hidden" id="flg_baseMinHeight" value="100" />
<input type="hidden" id="flg_baseDocHeight" value="100" />




<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sc/sc-contract-templet-info-select.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
<script type="text/javascript">
	//resApprove.js
	resetLeftPanel();

	//左右拖拉
	$('#leftPanel').resizable( {
		handler : '#noteResizeHandler',
		min : {
			width : 182,
			height : ($('#leftPanel').height())
		},
		max : {
			width : 400,
			height : ($('#leftPanel').height())
		},
		onResize : function(e) {
			$('#divTreeP').width($('#leftPanel').width() - 10);
		}
	});

	$(function() {
		//载入机构树
		initTree();
		//加载数 
		//loadTree();
	});

	////////////
	var id = '${id}';
	if (!isEmpty(id)) {
		openDetail(id, '1', '');
	}
	$(function() {
		if (isEmpty(id)) {
			$('#btn_search').trigger('click');
		}
	});
</script>

</body>
</html>