<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<!-- 分页信息 -->
<div class="right_page_bar">
	<security:authorize ifAnyGranted="A_COMPANYVI_ADMIN">
		<input type="button" class="fLeft btn_new btn_add_new" onclick="addFile('${folderId}');" value="增加文件" style="width:80px"/>
	</security:authorize>
	
	 <div class="fRight">
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
							<td style="width:75px;">创建时间</td>
							<security:authorize ifAnyGranted="A_COMPANYVI_ADMIN">
							<td style="width:55px;">创建人</td>
							<td style="width:100px;margin: 0; padding-left:5px;" colspan="2">操作</td>
							</security:authorize>
						</tr>
					</thead>
					
					<tbody>
						<s:iterator value="fileVoList">
							<tr class="data">
								<td title="<s:property value="file.fileName" />" >
									<div style="color:#5A5A5A;"><s:property value="file.fileName"/></div>
								</td>
								
								<td title="附件列表" class="attach_file_list">
									<s:if test="attachList.size > 0">
										<ul style ="list-style: none outside none;">
											<s:iterator value="attachList">
												<li>
													<s:url id="attUrl" action="show" namespace="/app">
														<s:param name="realFileName">${realFileName}</s:param>
														<s:param name="fileName">${fileName}</s:param>
														<s:param name="bizModuleCd">companyVi</s:param>
														<s:param name="id">${appAttachFileId}</s:param>
														<s:param name="operator">inline;</s:param>
													</s:url>
													<div style="float: left;max-width: 87%;"  class="ellipsisDiv" >
														<a href="javascript:void(0)" title="${realFileName} " onclick="openAtta('${attUrl}','${realFileName}')">${realFileName}</a>
													</div>
													<div style="float: left; padding-right: 10px;">
														<a href="javascript:void(0)" title="${realFileName}" onclick="openAtta('${attUrl}','${realFileName}')"></a>
													</div>
													<br/>
												</li>
											</s:iterator>
										</ul>
									</s:if>
									<s:else>
										&nbsp;
									</s:else>
								</td>
								
								<td><s:date name="file.createdDate" format="yyyy-MM-dd" /></td>
								
								<security:authorize ifAnyGranted="A_COMPANYVI_ADMIN">
								<td><p:code2name mapCodeName="mapCreatorName" value="file.creator"/></td>
								<td style="text-align: center;margin: 0; padding:0;">
									<div class="btn_edt_file_func" style="float:left;" title="编辑文件" onclick="editFile('${file.oaFilingFileId}');" >编辑</div>
									<div class="btn_del_file_func" style="float:left;" title="删除文件" onclick="delFile('${file.oaFilingFileId}', '${folderId}');" >删除</div>
								</td>
								</security:authorize>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</s:form>
	</s:else>
</div>
