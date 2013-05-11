<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<form rel="list" id="listForm" action="ctdb-list-content!loadList.action" method="post" accept-charset="utf-8">

	<input type="hidden" name="selectedItems" id="selectedItems"/>
	<input type="hidden" name="pageNo" />
	
	
	<div  style="clear: left;float: left;margin-left: 20px;margin-top: 15px;margin-bottom: 10px;">
		<font style="font-size: 14px;float: left;font-weight: bold;">按条件搜索</font>
	</div>
	<div class="firstPart" style="clear: left;float: left;margin-left: 30px;">
		<table style="width: 100%;">
			<tr style="margin-bottom: 10px;margin-top: 10px;padding-bottom: 10px;line-height: 28px;">
				<td align="right"  style="padding-right: 5px;">清单编号：</td>
				<td><input name="listCd"  id="list_listCd"></input></td>		
				<td></td>				
				<td></td>		
			</tr>
			<tr style="margin-bottom: 10px;margin-top: 10px;padding-bottom: 10px;line-height: 28px;">
				<td align="right"  style="padding-right: 5px;">清单名称：</td>
				<td><input name="listName" id="list_listName"></input></td>	
				<td></td>				
				<td></td>			
			</tr>
			<tr style="margin-bottom: 10px;margin-top: 10px;padding-bottom: 10px;line-height: 28px;">
				<td align="right"  style="padding-right: 5px;">状态：</td>
				<td align="left">
					<select name="statusCd" id="list_statusCd">
						<option value="">-</option>
						<option value="0">暂存</option>
						<option value="1">待确认</option>
						<option value="2">已确认</option>
						<option value="3">驳回</option>
					</select>
				</td>	
				<td></td>				
				<td></td>			
			</tr>
			<tr style="margin-bottom: 10px;margin-top: 10px;padding-bottom: 10px;line-height: 28px;">
				<td align="right"  style="padding-right: 5px;" >录入时间：</td>
				<td><input name="createdDate" id="list_createdDate"   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(dp){}})" class="Wdate" /></td>
				<td width="30px;" style="padding-left: 5px;">至：</td>	
				<td><input name="createdDateTo" id="list_createdDateTo"   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(dp){}})" class="Wdate" /></td>				
			</tr>
			<tr>				
				<td></td>
				<td colspan="3" style="text-align:left;">
					<input type="button" class="btn_new btn_query_new" onclick="searchByModuleId();" value="搜索"/>
					<input type="button" class="btn_new btn_clean_new" onclick="clearTabSearch();" value="清空条件"  style="width:70px;"/>
					<input type="button" class="btn_new btn_cancel_new" onclick="closeTabSearch();"id="clearBnt" value="关闭"  style="width:70px;" />
				</td>
			</tr>
		</table>
	</div>	
</form>