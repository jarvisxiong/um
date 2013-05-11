<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form id="impForm" action="ctdb-list-content!importList.action" method="post" accept-charset="utf-8" enctype="multipart/form-data">
	<div style="clear: left;">
		<table style="width: 100%;" id="impTable">
		     <tbody>
     			<tr style="margin-bottom: 10px;margin-top: 10px;padding-bottom: 10px;line-height: 28px;">
					<td  align="right" style="padding-right: 5px;width: 30%;"><font color="red">*</font>数据库大类:</td>
					<td  align="left">
						<%--<input name="1" style="width: 90%;"></input> --%>
						<select name="firstCat" style="width: 92%;" onchange="firstCatChange2(this);" id="firstCat">
							<option value="">-</option>
							<option value="CTDB_CONSTRUCTION_CATE">土建</option>
							<option value="CTDB_INSTALLATION_CATE">安装</option>
						</select>
					</td>
				</tr>
				<tr style="margin-bottom: 10px;margin-top: 10px;padding-bottom: 10px;line-height: 28px;">
					<td  align="right" style="padding-right: 5px;"><font color="red">*</font>小类:</td>
					<td colspan="3" align="left">
						<select name="secCat" style="width: 92%;" id="secCat2">
							<option>-</option>
							<option></option>
						</select>
					</td>
				</tr>
				<tr style="margin-bottom: 10px;margin-top: 10px;padding-bottom: 10px;line-height: 28px;">
					<td align="right"  style="padding-right: 5px;">导入文件:</td>
					<td><input type="file" name="importFile" style="width: 92%;height: 22px;line-height: 22px;" id="importFile"></input></td>
				</tr>
		     </tbody>
		</table>
	</div>

</form>

