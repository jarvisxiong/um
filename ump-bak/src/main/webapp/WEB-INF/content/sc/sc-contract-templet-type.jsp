<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>合同模板</title>
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp"%>
	<%@ include file="/common/meta.jsp"%>
	<meta http-equiv="Content-Type" content="text/html" />
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/res/resCss.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/sc/sc.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
</head>
<style>
.cost-nav{
	list-style-type:none;
	height:30px;
	padding-left:5px;
	font-size:12px;
}
.cost-nav li{
	float:left;
	height:26px;
	line-height:26px;
	margin:5px 5px 5px 0;
	border:1px solid #DDDBDC;
	cursor:pointer;
	padding:0 10px;
	text-align: center;
}
</style>

<body style="background-color:#fff;">
	<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>	
	<table id="idDivTitle" class="title_bar" style="width:100%;" cellpadding="0" cellspacing="0">
	<tr>
		<td style="width:177px;padding:0 5px;">
			<div style="font-size:14px;float:left">
		合同模板库
			</div>
			</td>
		<td style="text-align:left;">
			<div style="float:right;color:white;" class="full_screen" onmousemove="underline(this);" onmouseout="removeUnderline(this);" onclick="window.open('${ctx}/sc/sc-contract-templet-type.action')">
				全屏
			</div>
			<div style="float:right;" class="btn_cutline_3_26"></div>
			
			
		</td>
	</tr>
	</table>
	<div id="idDivParent" class="divParent" style="width:100%;">
		<table style="width:100%;">
			<tr>
				<td id="leftPanel" style="width:182px;border-right:1px solid #8c8f94;background-color: #E4E7EC;" valign="top">
					<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
						<tr>
							<td>
								<div id="searchApproveFix" style="border-bottom:1px solid #BFBFBF;">
									<table border="0" cellpadding="0" cellspacing="0" style="width:100%;">
										<tr>
											<td>
												<input  value="搜索模板..." 
														type="text" 
														style="padding:2px;border:0;font-size: 12px;color: #CCCCCC;width:100%;"
														onkeyup="searchTreeNode(this)"
														onblur="resetSearchApproveInput(this);"
														onclick="clearSearchApproveInput(this);"
														id="inputSearchVal"
												/>
											</td>
											<td style="width:5px;">
												<!-- div id="inputSearchOperate" class="searchNextNoActive" onclick="searchTreeNode(document.getElementById('inputSearchVal'))">&nbsp;</div> -->
											</td>
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
										<div id="divTreeP" class="divTreeP" style="padding-top:5px;float:left;overflow-y:auto;overflow-x:hidden;width:177px;border:none;">
											<!-- 修改 -->
											<div id ="search-div">
											</div>
											<div id ="tree-div">
											</div>
										</div>
									</td>
									<td style="width:5px;">
										<div id="noteResizeHandler" style="float:right; width:5px;height:100%;background: #eee url('${ctx}/resources/images/common/width_resize.gif') left center no-repeat;cursor: w-resize;">&nbsp;</div>
									</td>
								</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
				<td id="rightPanel" valign="top">
					<div id="idDivContentPanel" style="width:100%;"> 
					<div id="contentList" style="border:0px;width:100%;">
					<div id="content" style="overflow-y:auto; width:100%;"> 
					
						<!-- 同res-approve-info-load.jsp一样,开始... -->
							<div id="rightHeadQuick" style="background-color: #E4E7EC;">
								<table style="width:100%;background-color:white;" id="quickSearchPanel">
									<tr>
										<td style="width:120px;height:30px;verical-align:bottom;">
										&nbsp;<input type="button" class="btn_green" style="width:100px;text-align:center;" value="增加模板类别" id="bt_addTempletType" />
								
										</td>
										
										<td style="width:120px;">
											<input type="button" class="btn_green" style="width:100px" value="编辑模板类别"   onclick='modTempletType();' />
											<%--<input type="hidden" id="typeId" name="typeId" value="" /> --%>
										</td>
										<td style="width:90px;"> 
											<input type="button" class="btn_green" style="width:70px" value="增加模板"   onclick='showUploadSingleAttachDialog("创建合同模板","add","")' />
										  	<input type="hidden" id="templetTypeMaxSeq" name="templetTypeMaxSeq" value="${maxSequence}"/>
                                          	<input type="hidden" id="templetMaxSeqence" name="templetMaxSeqence" />
										</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</div>
				       <s:form id="scTempletTypeAddForm" action="sc-contract-templet-type!save.action" method="post">
							
					
							<div id="rightHeadTool" style="width:100%;background-color: #E4E7EC;display:none;border-bottom: 1px solid #AAABB0;">

								<table class="search"> 
								<tr>
										<td width="160"  style="padding: 5px 0 0 5px;text-align:right;">类型名称:</td>
										<td  style="padding: 5px 0 0 0;">										
									        <input type="text" class="input" name="typeName" id="typeName"  maxlength="50" style="width:200px"/>
										</td>		
									
									</tr>
									<tr><td width="160" style="padding: 5px 0 0 5px;text-align:right;">类别：</td>
									<td><s:select list="mapmoduleTypeCd" listKey="key" listValue="value" id="moduleTypeCd" name="moduleTypeCd"  style="width:200px"></s:select></td></tr>
							<tr>
									<td width="160" style="padding: 5px 0 0 5px;text-align:right;">描述:</td>
										<td style="padding: 5px 0 0 0;">
											<textarea rows="5" cols="6"  maxlength="200" style="margin-left: 0px; margin-right: 0px; width: 204px;" name="remark" id="remark"></textarea> 
											<input type="hidden" name="parentId" id="parentId"/>
											<input type="hidden" name="sequenceNo" id="sequenceNo" value="1"/>
											<input type="hidden" id="pStandard"/>
											</td>
										
										
							</tr>
							<tr>
								<td colspan="2">
								<div style="float: left; padding-left: 200px;">
								<div style="float: left;" class="buttom" id="btSave">保存</div>
								<div style="float: left;" class="buttom"  id="btCollapse">收起</div>
								</div>
								</td>
							</tr>
								</table>
							</div>
							
							<!-- 同res-approve-info-load.jsp一样,结束! -->
						</s:form>
							<form id="mainFormSearch" action="sc-contract-templet!loadList.action" method="post">
							<input type="hidden" id="sctempletTypeId" name="sctempletTypeId"/>
							<input type="hidden" id="pageNo" name="pageNo"/>
							<%/* 模板类别，所属标准类别*/ %>
							<input type="hidden" id="typeStandard"/>
							<input type="hidden" id="divisstandard" name="divisstandard"/>
							</form>
						<div class="titleBar" style="overflow:auto;height:46px;line-height:46px;" >
						<ul class="cost-nav" id="cost-nav" style="float:left;">
						
						</ul>
						</div>
                      <div id="searchResultPanel" style="clear: left;"></div>
                      
					</div>
					</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div id="searchUserDiv" class="searchUserDiv"></div>
	<security:authorize ifAnyGranted="A_JBPM_ROLE"><s:set var="hasJbpm" value="true"></s:set></security:authorize>
	 
	<!-- 缓存标志, 用于计算页面高度 ,值[0,1] -->
	<input type="hidden" id="flg_firstFlg"		value="0"/>
	<input type="hidden" id="flg_baseMinHeight"	value="100"/>
	<input type="hidden" id="flg_baseDocHeight"	value="100"/>
	
	
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/sc/sc-contract-templet-type.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript"> 
		resetLeftPanel();	
		//左右拖拉
	    $('#leftPanel').resizable({
	        handler: '#noteResizeHandler',
	        min: { width: 182, height: ($('#leftPanel').height()) },
	        max: { width: 400, height: ($('#leftPanel').height()) },
	        onResize: function(e) {
	        $('#divTreeP').width($('#leftPanel').width()-10);
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
				$('#btn_search').trigger('click');
			}
		});
		
	</script>
	
</body>
</html>