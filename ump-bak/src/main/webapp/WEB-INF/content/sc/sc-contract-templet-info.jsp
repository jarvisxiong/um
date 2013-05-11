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
	<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/select.css" media="screen" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" media="screen"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/jqueryplugin/jqModal/jqModal.css" media="screen"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common3.css" media="screen" />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
	
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/sc/sc-contract-templet-info.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
</head>

<body>
<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
<div class="title_bar">
	<div class="div_inner" style="margin:0px;">
		<div class="title_bar_left" style="width:176px; margin:0px;">合同文本库<input type="hidden" value="<%=SpringSecurityUtils.getCurrentUiid()%>" id="curUserId" /></div>
		<div style="float:left; font-size:12px; margin:0px;">
			<s:url id="downUrl" action="download" namespace="/sc">
				<s:param name="fileName">hetongku.doc</s:param>
				<s:param name="realFileName">标准合同文本平台操作手册下载.doc</s:param>
				<s:param name="bizModuleCd">public</s:param>
				<s:param name="operator">inline;</s:param>
				<s:param name="id"></s:param>
		    </s:url>
		    <a href="${downUrl}" target="_blank" class="color_blue">标准合同文本平台操作手册下载.doc</a>
		    &nbsp;&nbsp;如有疑问请联系营运管理中心 卢俊云(13636662709)
		</div>
		<div class="title_bar_right" style="margin:0px;"><input type="button" class="btn_green" onclick="window.open('${ctx}/sc/sc-contract-templet-info.action')" value="全屏"/></div>
	</div>
</div>
<div id="idDivParent">
<table class="content_table_tree">
	<tr>
		<td id="leftPanel" class="div_tree_left">
			<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
				<tr>
					<td>
						<div id="searchApproveFix">
							<table border="0" cellpadding="0" cellspacing="0" style="width:100%;">
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
									<div id="divTreeP" class="div_tree_left_panel" >
										<div id="search-div"></div>
										<div id="tree-div"></div>
									</div>
								</td>
								<td style="width: 5px;">
									<div id="noteResizeHandler"	class="tree_resize" title="您可以拖动,调整宽度" >&nbsp;</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
		<td id="rightPanel">
			<div id="content" class="search_bar" style="width:100%;">
				<div class="div_inner">
					<div style="float:left;">合同编号：<input type="text" id="contractNo"/></div>
					<div style="float:left;"><input type="button" id="bt_search_contNo" class="btn_green" onclick="searchByContractNo();" value="搜索" /></div>
					<div style="float:left;"><input type="button" id="bt_search" class="btn_grey" onclick="doQuickSeniorSearch();" value="高级搜索" /></div>
					<div style="float:right;">
						<s:form id="scConInfoSearchForm" action="contract-templet-info!list.action" method="post">
							<span>快速搜索：</span>
							<span id="btnMyReco" style="cursor:pointer;" onclick="searchMyReco();">我的合同</span>
						</s:form>
					</div>
				</div>
			</div>
			<div id="rightHeadTool" class="search_senior_div" style="height:64px; display:none;">
				<div class="div_inner">
					<form id="mainFormSearch" action="sc-contract-templet-info!list.action" method="post">
					<div class="search_senior_input_div">
						项目名称: <input type="text" name="projectName" id="projectName" readonly="readonly" class="text" style="cursor: pointer;" />
					         <input type="hidden" id="projectCd" name="projectCd" value="" />
					</div>
					<div class="search_senior_input_div">
						合同名称: <s:textfield cssClass="text" name="conName" id="conName" />
					</div>
					<div class="search_senior_input_div">
						合同编号: <input type="text" class="text" name="conNo" id="conNo" /></div>
					<div class="search_senior_input_div">
						创建人:<s:textfield cssStyle="cursor:pointer;" cssClass="text" id="curUserName" name="curUserName" maxlength="20" onkeyup="showSearchUser(this)" />
						       <input type="hidden" name="curUserCd" id="curUserCd" />
			        </div>
					<div class="search_senior_input_div" style="display:none;">
					  	创建时间: <s:textfield name="startDate" onfocus="WdatePicker()" cssClass="text" />
					</div>
					<div class="search_senior_input_div" style="display:none;">
						到: <s:textfield name="endDate" onfocus="WdatePicker()" cssClass="text" />
					</div>
					<div class="search_senior_input_div">
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
					<div class="search_senior_input_div">
						<input type="button" class="btn_green" id="btn_search" onclick="doSearch();" value="<s:text name="common.search" />" /> 
						&nbsp;&nbsp; 
						<input type="button" id="bt_collapse" onclick="doClear();" class="btn_blue" value="清空" />
						<%--搜索标准合同 --%> 
						<input type="hidden" id="isSearchByStanCon" name="isSearchByStanCon" value="true" /> 
						<%--合同模板类型 （商业、地产 ）--%> 
						<input type="hidden" id="moduleCd" value="${scContractParams.templetTypeCd}" />
						<input type="hidden" id="divStatusCd" name="divStatusCd"/>
						<%--搜索标准合同 --%>
						<input type="hidden" id="isSearchByStanCon"  name="isSearchByStanCon" value="true"/>
						<input type="hidden" id="sctempletTypeId" name="sctempletTypeId"/>
						<input type="hidden" id="isView" name="isView" value="1"/>							
						<input type="hidden" id="pageNo" name="pageNo"/>
						<input type="hidden" id="isstandard" name="isstandard" />
						<%--网批中带的字段及值 --%>
						<input type="hidden" id="resFileds"  class="res" name="resFields" value="${scContractParams.resFields}"/>
						<%--网批编号 --%>
						<input type="hidden" id="resNo"  class="res" name="resNo" value="${scContractParams.resNo}"/>
						<%--网批中附件--%>
						<input type="hidden" id="resRela"  class="res" name="resRela" value="${scContractParams.resRela}"/>
						<%--从定标中导入合同台账字段 --%>
						<input type="hidden" id="creteContLedFields" class="res" name="creteContLedFields" value="${scContractParams.resContLedgerParams}"/>
						<%--是否需要自动生成合同台账 --%>
						<input type="hidden" id="ifNoContLeger" name="ifNoContLeger" value="${ifNoContLeger}"/>
						<%--tab Frame页面ID --%>
						<input type="hidden" id="frameId" value="${scContractParams.frameId}"/>
					</div>
					</form>
				</div>
			</div>
		 	<div class="search_info_bar">
		 		<div class="div_inner">
					<table style="width:100%; padding-left:6px;">
						<tr>
							<td>
								<span>当前选择：</span><span id="idCurTempletName"></span>
							</td>
							<td align="right">
								<input type="button" id="btnNewContract" class="btn_add3" style="width:110px; display: none;" value="创建标准合同"/>
								<input type="button" id="btnNewNonContract" class="btn_add3" style="width:110px; display: none;" value="创建非标合同"/>
							</td>
						</tr>
					</table>
				</div>
			</div>
			
			<div class="search_info_bar">
				<div id="cost-nav" style="padding:2px;"></div>
		   	</div>
		   	<div id="searchResultPanel" style="float:left;width:100%;"></div>
		</td>
	</tr>
</table>
</div>
<security:authorize ifAnyGranted="A_JBPM_ROLE"><s:set var="hasJbpm" value="true"></s:set></security:authorize>
<!-- 缓存标志, 用于计算页面高度 ,值[0,1] -->
<input type="hidden" id="flg_firstFlg"		value="0"/>
<input type="hidden" id="flg_baseMinHeight"	value="100"/>
<input type="hidden" id="flg_baseDocHeight"	value="100"/>

<script type="text/javascript">
	var id='${id}';
    $(function(){
		//resetLeftPanel();
		//左右拖拉
	    $('#leftPanel').resizable({
	        handler: '#noteResizeHandler',
	        min: { width: 100, height: ($('#leftPanel').height()) },
	        max: { width: 400, height: ($('#leftPanel').height()) },
	        onResize: function(e) {
	        	$('#divTreeP').width($('#leftPanel').width()-6);
	        }
	    });
		//载入机构树
		initTree();
    	//loadTree();
		if(!isEmpty(id)){
			openDetail(id,'1','');
		}
		if(isEmpty(id)){
			$('#btn_search').trigger('click');
		}
		
		$('#contractNo').bind('keyup', function(event){
			   if (event.keyCode=="13"){
				   searchByContractNo();
			   }
			});
					
	});
</script>
</body>
</html>