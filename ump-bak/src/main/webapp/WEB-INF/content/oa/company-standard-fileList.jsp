<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="java.util.List"%>
<%@page import="com.hhz.ump.web.oa.CompanyViFileVO"%>
<%@page import="com.hhz.ump.entity.oa.OaFilingFile"%>
<%@page import="com.hhz.ump.entity.app.AppAttachFile"%>
<script type="text/javascript">
function viewDetails(fileName,path,title){
	var url = '${ctx}'+path;
	window.parent.TabUtils.newTab("viFile",title,url,true);
	
}
</script>
<!-- 分页信息 -->
<div class="right_page_bar">
	<security:authorize ifAnyGranted="A_COMPANYFILE_ADMIN">
	
		<input type="button" class="btn_new btn_add_new" onclick="addFile('${folderId}');" value="增加文件" style="width:80px;"/>
	
	</security:authorize>
	
	 <div class="fRight" >
		<input type="text" id="pageNo" style="height: 15px; width: 20px; text-align: center;"/> 
	 	<a href="javascript: jumpTo('${folderId}', $('#pageNo').val());">GO</a>
	 </div>
	<s:if test="filePage.hasNext">
		<div style="float:right;" class="jump_down" onclick="jumpTo('${folderId}', '${filePage.nextPage}');"></div>
	</s:if>
	<s:else>
	 	<div style="float:right;" class="jump_down_none" ></div>
	</s:else>
	<s:if test="filePage.hasPre">
		<div style="float:right;" class="jump_up" onclick="jumpTo('${folderId}', '${filePage.prePage}');"></div>
	</s:if>
	<s:else>
		<div style="float:right;" class="jump_up_none" ></div>
	</s:else>
	 
	<div style="float: right;">
	共有&nbsp;${filePage.totalCount}&nbsp;条记录&nbsp;&nbsp;&nbsp;&nbsp;
	 当前第 ${filePage.pageNo}/${filePage.totalPages}&nbsp;页
	</div>
</div>

<!-- 列表信息 -->
<div style=" overflow-y: auto; overflow-x: hidden;">
	<s:if test="filePage.result.size == 0">
		<div class="inform_div">
			请选择子目录 
		</div>
	</s:if>
	<s:else>
		<s:form id="fileListForm" action="/oa/comapny-vi!fileList.action" method="post">
			<s:hidden name="folderId" id="curFolderId" />
			<input type="hidden" name="filePage.pageNo" id="filePageNo" value="${filePage.pageNo}" />
			<input type="hidden" name="totalPageSize" id="totalPageSize" value="${filePage.totalPages}" />
		
			<div>
				<table class="file_list_table" cellpadding="0" cellspacing="0">
					<thead>
						<tr class="header">
								<td style="back	ground-image: none;">文件名称</td>
							<td>附件</td>
							<%--
							<td style="width:75px;">创建时间</td>
							<td style="width:55px;">创建人</td>
							 --%>
							<security:authorize ifAnyGranted="A_COMPANYFILE_ADMIN">
							<td style="width:100px;margin: 0; padding-left:5px;" colspan="2">操作</td>
							</security:authorize>
						</tr>
					</thead>
					
					<tbody>
						
						<%
							 List<CompanyViFileVO> fileVoLists = (List)request.getAttribute("fileVoList");
							 if (fileVoLists.size()!=0){
								 for(CompanyViFileVO vo :fileVoLists){
									 String tem  = vo.getFile().getRemark();
									 
								 %>
									 <tr class="data">
									 <%
									 if(tem!=null){
										 String[] ids = tem.split(",");
										 String tempId = null;
										 String aHref = "";
										 if (ids.length==1){
											 aHref = ids[0];
											 tempId = "";
										 }
										 else if (ids.length>1){
											 aHref = ids[0];
											 tempId = ids[1];
										 }%>
										 <td title="<%=vo.getFile().getFileName() %>">
											<div style="color:#5A5A5A;">
												<a style="color:blue;font-weight: bold;text-decoration: underline;" href="javascript:viewDetails('<%=vo.getFile().getFileName() %>','<%=aHref %>','<%=tempId %>');"><%=vo.getFile().getFileName() %></a>
											</div>
										</td>
									 <%}else{%>
									 <td title="<%=vo.getFile().getFileName() %>">
										<div style="color:#5A5A5A;">
											<%=vo.getFile().getFileName() %>
										</div>
									 </td>
									 <%}
									 %>
										<td title="附件列表" class="attach_file_list">
											<%
											 if (vo.getAttachList().size() > 0){%>
												 <ul style ="list-style: none outside none;">
													<%
													for(AppAttachFile af : vo.getAttachList()){%>
														<li>
															<s:url id="attUrl" action="show" namespace="/app">
																<s:param name="realFileName"><%=af.getRealFileName() %></s:param>
																<s:param name="fileName"><%=af.getFileName() %></s:param>
																<s:param name="bizModuleCd">companyVi</s:param>
																<s:param name="id"><%=af.getAppAttachFileId() %></s:param>
																<s:param name="operator">inline;</s:param>
															</s:url>
															<div style="float: left;max-width: 87%;"  class="ellipsisDiv" >
																<a href="javascript:void(0)" title="<%=af.getRealFileName() %> " onclick="openAtta('${attUrl}','<%=af.getRealFileName() %>')"><%=af.getRealFileName() %></a>
															</div>
															<div style="float: left; padding-right: 10px;">
																<a href="javascript:void(0)" title="<%=af.getRealFileName() %>" onclick="openAtta('${attUrl}','<%=af.getRealFileName() %>')"></a>
															</div>
															<br/>
														</li>
													<%}
													%>
												</ul>
											<%}else{%>
												 &nbsp;
											<% }	
											%>
										
										</td>

										<security:authorize ifAnyGranted="A_COMPANYFILE_ADMIN">
										<td style="text-align: center;margin: 0; padding:0;" colspan="2">
											<div class="btn_edt_file_func" style="float:left;" title="编辑文件" onclick="editFile('<%=vo.getFile().getOaFilingFileId() %>');" >编辑</div>
											&nbsp;
											<div class="btn_del_file_func" style="float:left;" title="删除文件" onclick="delFile('<%=vo.getFile().getOaFilingFileId() %>', '${folderId}');" >删除</div>
										</td>
										</security:authorize>
									</tr>
								 <%}
							 }
							%>

					</tbody>
				</table>
			</div>
		</s:form>
	</s:else>
</div>
