<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div id="batchOper" style="clear:both;margin:5px;padding:10px;">
 <form id="repotrForm" action="${ctx}/bis/bis-decision!importTemplate.action" method="post" enctype="multipart/form-data">
     <table style="overflow: hidden;width: 100%;height: 100%">
     	<col width="250px"/>
     	<col />
		<tr valign="middle">
			<td colspan="2">
				<span style="font-weight: bolder;font-size: 15px;">导入数据</span>
				<input type="hidden" value="${reportType}" name="reportType"/>
			</td>
		</tr>
		
		<tr height="35px;" valign="middle">
			<%--销售情况报表 --%>
			<s:if test="reportType!=null && reportType==1">
				<td>1、请在导出模板中填写数据，再导入系统；</td>
				<td align="left">
					<input type="button" class="btn_blue" style="width:65px;" onclick="doExport(${reportType});" value="导出模板"/>
				</td>
			</s:if>
			<%--商业收费情况报表 --%>
			<s:elseif test="reportType!=null && reportType==2">
				<td align="left" colspan="2">
					选择月份：
					<input class="input" type="text" id="startDate" name="startDate" style="width:100px;" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" onblur="clearEndDate(this);" readonly="readonly"/>
					<span id="timeSpan" style="display: none;">
						- <input class="input" type="text" id="endDate" name="endDate" style="width:100px;" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{M:1})}',dateFmt:'yyyy-MM'})" readonly="readonly"/>
					</span>
					<input type="button" class="btn_blue" style="width:65px;margin-left: 11px;" id="timeBtus" onclick="showAllTime();" value="按时间段"/>
				</td>
			</s:elseif>
		</tr>
		<%--商业收费情况报表 --%>
		<s:if test="reportType!=null && reportType==2">
			<tr height="35px;" valign="middle">
				<td colspan="1">1、请在导出模板中填写数据，再导入系统；</td>
				<td align="left">
					<input type="button" class="btn_blue" style="width:65px;" onclick="doExport(${reportType});" value="导出模板"/>
				</td>
			</tr>
		</s:if>
		<tr height="35px;" valign="middle">
			<td colspan="2">2、每月可随时导入数据， 后一次导入的数据会自动覆盖前一次的数据；</td>
		</tr>
		<tr height="35px;" valign="middle">
			<td colspan="2">3、模板中黄色项为必填项，请注意数据的填写格式；</td>
		</tr>
		<tr height="35px;" valign="middle">
	  		<td>4、选择文件：<input type="file" id="importFile" name="importFile" style="cursor: pointer;width:200px;"/></td>
			<td align="left">
				<input type="button" class="btn_green" style="width:65px;" onclick="doImport(${reportType});" value="导入数据"/>
			</td>
		</tr>
	</table>
</form>
</div>
