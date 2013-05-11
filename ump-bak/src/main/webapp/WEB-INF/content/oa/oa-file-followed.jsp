<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="java.util.Date"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%><html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/meta.jsp" %>
		<%@ include file="/common/global.jsp" %>
		<title>文件跟踪</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/file-track.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
		
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/desk/file-track.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/oa/allAttention.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
		<script type="text/javascript">
		$(function(){
			var bodyHeight = Number($(document).height()-100);
			$("#filelistDiv").css("height",bodyHeight+"px");
			$(".receiver").ouSelect({
				muti:false
			});
			$('.sendOrg').ouSelect({type:'org'});
		});
		</script>
	</head>

	<body>
		<div>
			<div class="title_bar">
				<div class="title_bar_h">文件跟踪</div>
				
				<div style="float:right; height:25px; margin-top: 3px;">
					<div class="btn_addNewFile" style="margin-left: 10px; float: left;" onclick="triggerNewTaskInput();">
						新建
					</div>
						
					<div style="margin-left: 10px; float: left;" class="btn_searchBt" onclick="showSearch();">
						搜索
					</div>
					<div style="margin-left: 10px; float: left;" class="btn_refreshMeeting" onclick="refreshPage();">
						刷新
					</div>
					<div style="margin-left: 10px; margin-right: 5px; float: left;" class="btn_fullScreen" onclick="window.open('${ctx}/oa/oa-file-followed.action')">
						全屏
					</div>
				</div>
			</div>
			
			<div class="wrapper" style="width:100%;">
				<s:form id="searchForm" action="oa-file-followed!fileList.action" method="post">
					<input type="hidden" name="totalPageSize" id="totalPageSize" value="${page.totalPages}" />
					<input type="hidden" name="page.pageNo" id="pagePageNo" value="${page.pageNo}" />
					
					<div id="searchPanel" class="search_panel">
						<table cellpadding="0" cellspacing="0" style="table-layout: fixed; width: 100%;">
							<tr>
								<td width="50" align="right">状态:</td>
								<td width="110">
									<select name="searchByStatus" style="width: 90px;" onchange="searchStatus();">
										<option value="1">进行中</option>
										<option value="2">已完成</option>
										<option value="3">已处理</option>
										<option value="4">全部文件</option>
									</select>
								</td>
								<td width="50" align="right">来源:</td>
								<td width="110">
									<input type="text" id="searchByProjName" name="searchByProjName" style="width: 100px; cursor: pointer;" 
										class="sendOrg" />
									<input type="hidden" id="searchByProjCd" name="searchByProjCd" />
								</td>
								<td width="50" align="right">中心:</td>
								<td width="110">
										<input type="text" id="searchByDeptName" name="searchByDeptName" style="width: 100px; cursor: pointer;" 
											onfocus="enableOrgSel('dept');"/>
										<input type="hidden" id="searchByDeptCd" name="searchByDeptCd"/>
								</td>
								<td width="60" align="right">开始日期:</td>
								<td>
									<s:textfield name="searchByGECreateDate" onfocus="WdatePicker()" cssStyle="width:80px;"></s:textfield>&nbsp;至&nbsp;
									<s:textfield name="searchByLECreateDate" onfocus="WdatePicker()" cssStyle="width:80px;"></s:textfield>
								</td>
							</tr>							
							<tr>
								<td width="50" align="right">编号:</td>
								<td width="110"><input type="text" name="searchBySerialNum" style="width: 100px;" /></td>
								<td width="50" align="right">文件名:</td>
								<td width="110"><input type="text" name="searchByContent" style="width: 100px;" /></td>
								<security:authorize ifAnyGranted="A_FILE_TRACK_ADMIN">
									<td width="50" align="right">人员:</td>
									<td width="110">
										<input type="text" id="searchByUserName" name="searchByUserName" style="width: 100px;" onkeyup="showSearchUser(this)" />
										<input type="hidden" id="searchByUser" name="searchByUser" />
									</td>
								</security:authorize>
								<td colspan="2">
									<div class="func_icon" style="margin-left: 20px; margin-right: 10px; float: left;" onclick="searchFileList();">搜索</div>
									<div class="func_icon" style="margin-right: 10px; float: left;" onclick="clearSearch();">清空</div>
									<div class="func_icon" style="float: left; margin-right: 10px;" onclick="showSearch();">隐藏</div>
								</td>
							</tr>
						</table>
					</div>
				</s:form>
			 	<div id="fileListDiv" style="padding: 0 5px;">
			 		<div id="filelistHeadDiv">
						<table class="content_table" style="width:100%;" cellpadding="0" cellspacing="0">
							<thead>
								<tr class="header">
									<th width="45">&nbsp;</th>
									<th width="80">编号</th>
									<th width="90">来源</th>
									<th>文件名</th>
									<th width="70">
										部门
									</th>
									<th width="150">
										状态
									</th>
									<th width="50">
										开始
									</th>
									<th width="60">
										更新
									</th>
									<th width="40" style="background-image: none;">附件</th>
									<td width="18" style="background-image: none; padding: 0; border-bottom: 0 none;"></td>
								</tr>
							</thead>
						</table>
					</div>
					
					<div id="addNewFileDiv" style="display: none;">
						<s:form id="inputForm" action="oa-file-followed!save.action" method="post">
							<s:hidden id="entityTmpId" name="entityTmpId" />
							<input type="hidden" name="serialNumber" value="${serialNum}"/>
							<table class="content_table" style="width:100%" cellpadding="0" cellspacing="0">
								<tr class="add_new">
									<td width="20">&nbsp;</td>
									<td valign="middle" width="80">
										${serialNum}
									</td>
									<td align="left" width="90">
										<textarea readonly="readonly" id="projNameInput" name="projNameInput" style="overflow: auto; font-size: 12px; line-height: 20px; width: 78px; height: 48px; cursor: pointer;"
											 title="点击选择文件来源" class="pd-chill-tip sendOrg">${projectName}</textarea>
										<input type="hidden" id="projectSnHidden" name="projectSn" value="${projectSn }"/>
									</td>
									<td>
										<textarea id="contentInputArea" name="content" rows="3" style="overflow: auto; width: 95%;"></textarea>
									</td>
									<td valign="middle" width="70">
										<%=SpringSecurityUtils.getCurrentDeptName() %>
									</td>
									<td valign="middle" width="150">
										<span style="color: green;">新建中...</span>
									</td>
									<td width="50">
										<%=DateOperator.formatDate(new Date(), "MM-dd") %>
									</td>
									<td width="60"></td>
									<td width="40" align="center">
										<div onclick="attachManage('${entityTmpId}', event);" style="width: 30px; cursor: pointer; text-align: center; text-decoration: underline; color: blue;">
											<p>添加</p><p>附件</p>
										</div>
									</td>
									<td width="18" style="background: none; padding: 0; border-bottom: 0 none;"></td>
								</tr>
								<tr class="add_new">
									<td colspan="3" align="center" valign="middle" style="font-size: 14px; font-weight: bold; color: red;">
										进度
									</td>
									<td align="left">
										<textarea name="message" rows="3" style="overflow: auto; width: 95%;"></textarea>
									</td>
									<td colspan="5">
										<div class="buttonStyle" style="float: left;margin-left: 20px;" onclick="createNewFile();">创建</div>
										<div class="buttonStyle" style="float: left;margin-left: 10px;" onclick="triggerNewTaskInput();">取消</div>
									</td>
									<td width="18" style="background: none; padding: 0; border-bottom: 0 none;"></td>
								</tr>
							</table>
						</s:form>
					</div>
					
					<div id="filelistDiv" style="height: 300px; overflow: auto;">
						<%@ include file="oa-file-followed-fileList.jsp" %>
					</div>
				</div>
			</div>
			
			<div style="clear: both;">
		 		<div class="corner_bottom_left"></div>
		 		<div class="corner_bottom_right"></div>
		 		<div class="corner_bottom_center"></div>
		 	</div>
			
			<div class="pager_style" id="mainPager">
				<div style="float: left;">
					<div style="margin-right: 6px; float: left; margin-left: 5px;">
						<input type="checkbox" name="allFilesCheckBox" style="margin-bottom: 2px;" id="allFilesCheckBox" onclick="checkAll(this);" />
					</div>
					<div class="func_icon" style="margin-right: 10px; float: left; margin-left: 5px;" onclick="batchSend();">送出</div>
					<div class="func_icon" style="margin-right: 10px; float: left;" onclick="batchConfirm();">确认</div>
					<s:url id="exportExl" action="oa-file-followed!exportExcel.action" namespace="/oa"  includeParams="none"  />
					<div class="func_icon_red" style="float: left; margin-right: 10px;" onclick="exportResult('${exportExl}');">导出</div>
					<div class="func_icon_red" style="margin-right: 10px; float: left;" onclick="batchDel();">删除</div>
				</div>
				
				<div style="float: right; padding-right: 10px;">
					共有&nbsp;${page.totalCount}&nbsp;条记录&nbsp;&nbsp;&nbsp;&nbsp;
					 当前第 ${page.pageNo}/${page.totalPages}&nbsp;页
					 <s:if test="page.hasPre">
						<img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_up.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_up_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_up.gif');" onclick="jumpTo('${page.prePage}');"/>
					 </s:if>
					 <s:else>
					 	<img align="absmiddle" src="${ctx}/images/desk2/page_up_grey.gif" />
					 </s:else>
					 <s:if test="page.hasNext">
						<img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_down.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_down_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_down.gif');" onclick="jumpTo('${page.nextPage}');"/>
					 </s:if>
					 <s:else>
					 	<img align="absmiddle" src="${ctx}/images/desk2/page_down_grey.gif" />
					 </s:else>
					 <input type="text" id="pageNo" style="height: 15px; width: 20px; text-align: center;" value="${page.pageNo}"/>
					 <a href="#" onblur="this.blur();" onclick="jumpTo($(this).prev().val()); return false;">GO</a>
				 </div>
			</div>
		</div>
		<div id="searchUserDiv" class="searchUserDiv"></div>
	</body>
</html>
