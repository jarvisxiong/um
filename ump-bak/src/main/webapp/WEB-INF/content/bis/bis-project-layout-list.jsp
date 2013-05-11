<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<table class="content_table" id="result_table">
	<col width="150"/>
	<col width="100"/>
	<col />
	<col width="80"/>
	<col width="50"/>
	<col width="50"/>
	<thead>
		<tr class="header">
			<th align="left" style="font-weight:bolder;">项目</th>
			<th align="left" style="font-weight:bolder;">签批日期</th>
			<th align="left" style="font-weight:bolder;">备注</th>
			<th align="left" style="font-weight:bolder;">附件类型</th>
			<th align="left" style="font-weight:bolder;">附件</th>
			<th align="left" style="font-weight:bolder;">操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="page.result">
			<tr class="mainTr" id="main_${bisProjectLayoutId}" >	
				<td onclick="doEditLayout('${bisProjectLayoutId}');">
					${bisProject.projectName}
				</td>
				<td onclick="doEditLayout('${bisProjectLayoutId}');"><s:date name="imgDate" format="yy-MM-dd"/></td>
				<td class="pd-chill-tip" title="${remark}" onclick="doEditLayout('${bisProjectLayoutId}');">
					<div class="ellipsisDiv_full" >
					${remark}
					</div>
				</td>
				<td onclick="doEditLayout('${bisProjectLayoutId}');">
					<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisImgType()" value="imgType" />
				</td>
				<td align="center">
					<s:if test="attachFlg==1">
					<a href="javascript:showAttachment('${bisProjectLayoutId}');" >
					<img id="projectLayoutAttachId" src="${ctx}/resources/images/common/atta_y.gif" />
					</a>
					</s:if>
				</td>
				<td align="center">
					<input class="btn_red" style="width:35px;" type="button" onclick="doDeleteLayout('${bisProjectLayoutId}');" value="删除"/>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>

<style type="text/css">
.table_pager {
	float: right;
	margin: 5px 0;
}
.table_pager input{width:24px;height:24px;border:1px solid #ccc;}
#pageTo{width:24px;height:22px;}
</style>
<div class="table_pager">
	<p:page />
</div>
		