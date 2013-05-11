<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<s:form id="operationLogSearchForm" action="/planold/plan-operation-log!list.action" method="post">
	<input type="hidden" name="page.pageNo" id="operationLogPageNo" value="" />
	<input type="hidden" name="totalPageNo" id="totalPageNoHid" value="${page.totalPages}" />

	<div class="search_bar" style="height: 28px;">
		项目:
		<select style="width:80px;" name="projectCd">
			<s:iterator value="projects">
				<option value="${orgCd}" <s:if test="projectCd == orgCd">selected="selected"</s:if>><s:property value="orgName" /></option>
			</s:iterator>
		</select>&nbsp;
		操作:
		<select style="width:80px;" name="operationType">
			<option value="">-全部-</option>
			<option value="1" <s:if test="operationType == 1">selected="selected"</s:if>>新增</option>
			<option value="2" <s:if test="operationType == 2">selected="selected"</s:if>>更新</option>
			<option value="3" <s:if test="operationType == 3">selected="selected"</s:if>>上传附件</option>
			<option value="4" <s:if test="operationType == 4">selected="selected"</s:if>>删除附件</option>
		</select>&nbsp;
		操作时间:<s:textfield name="operationStartDate" onclick="WdatePicker()" size="10" />
				至
				<s:textfield name="operationEndDate" onclick="WdatePicker()" size="10" />&nbsp;
		<button class="btn_search" onfocus="this.blur();" style="padding-left: 20px;" onclick="searchOpLogs(); return false;">搜索</button>
		&nbsp;
		<button class="btn_cancel" onfocus="this.blur();" style="padding-left: 20px;" onclick="backToProjPlan(); return false;">返回</button>
	</div>
</s:form>
<div style="height: 360px; overflow: auto; border-bottom: 1px solid #DBDBDB;">
	<table class="table_list" cellpadding="0" cellspacing="0">
		<thead>
			<tr class="header">
				<th width="80">操作类型</th>
				<th width="70">操作人</th>
				<th width="120">操作对象</th>
				<th width="150">操作时间</th>
				<th style="background: none;">操作记录</th>
			</tr>
		</thead>
		
		<tbody>
			<s:iterator value="page.result">
				<tr class="data">
					<td>
						<p:code2name mapCodeName="operationTypeMap" value="operationType"/>
					</td>
					<td>
						<p:code2name mapCodeName="operatorMap" value="creator"/>
					</td>
					<td title="<s:property value="modifiedObject"/>">
						<div style="width: 120px; height: 20px; line-height: 20px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"><s:property value="modifiedObject"/></div>
					</td>
					<td>
						<s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<s:property value="operationLog"/>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</div>

<div class="pager_style">
	共有&nbsp;${page.totalCount}&nbsp;条记录&nbsp;&nbsp;&nbsp;&nbsp;
	 当前第 ${page.pageNo}/${page.totalPages}&nbsp;页
	 <s:if test="page.hasPre">
		<img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_up.gif" 
			onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_up_hover.gif');" 
			onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_up.gif');" 
			onclick="jumpToPlanOpLogPage('${page.prePage}');"/>
	 </s:if>
	 <s:else>
	 	<img align="absmiddle" src="${ctx}/images/desk2/page_up_grey.gif" />
	 </s:else>
	 
	 <s:if test="page.hasNext">
		<img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_down.gif" 
			onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_down_hover.gif');" 
			onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_down.gif');" 
			onclick="jumpToPlanOpLogPage('${page.nextPage}');"/>
	 </s:if>
	 <s:else>
	 	<img align="absmiddle" src="${ctx}/images/desk2/page_down_grey.gif" />
	 </s:else>
	 
	 <input type="text" id="pageNo" style="height: 15px; width: 20px; text-align: center;"/>
	 <a onfocus="this.blur();" href="javascript: jumpToPlanOpLogPage($('#pageNo').val());">GO</a>
</div>