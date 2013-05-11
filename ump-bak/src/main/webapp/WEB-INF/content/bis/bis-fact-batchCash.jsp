<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div style="display: block; clear: both; margin: 0px; padding: 5px; border:1px solid #0167A2;background: none repeat scroll 0% 0% rgb(247, 247, 247);">
	 <table class="tb_search">
		    <col width="70"/>
			<col width="200"/>
			<col width="120"/>
			<col/>
		 <tr>
		    <td class="td_title"><font color="red">*</font>项目：</td>
		 	<td>
				<input type="text" id="bisProjectName" name="bisProjectName" value="${bisProjectName}" style="width:120px;height:20px;" />
				<input type="hidden" id="bisProjectId" value="${bisProjectId}"/>
			</td>
			<td class="td_title"><font color="red">*</font>业态：</td>
			<td><select style="width:120px;" id="floorType" name="floorType" onchange="changeFloor();">
					<option value="">--选择--</option>
					<option value="1">商铺</option>
                	<option value="2">公寓</option>
				   <option value="3">多经</option>
				</select>
			</td>
		</tr>
		<tr>
		  <td id="tdFloorTd"  class="td_title">楼(层)号：</td>
		  <td><select style="width:120px;" id="bisFloorId" name="bisFloorId" ><option>--全部楼层--</option></select></td>
		  <td  class="td_title"><font color="red">*</font>现金流日期：</td>
		  <td><input class="Wdate" type="text" id="feeDate" name="feeDate" value='${feeDate}' style="width:120px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
		</tr>
		<tr>
		 <td  class="td_title"> <font color="red">*</font>权责年月：</td>
		 <td> <input class="Wdate" type="text" id="authDate1" value='${authDate1}' style="width:80px;" onfocus="WdatePicker({dateFmt:'yyyy-MM'})"/>
		     到&nbsp;<input class="Wdate" type="text" id="authDate2" value='${authDate2}' style="width:80px;" onfocus="WdatePicker({dateFmt:'yyyy-MM'})"/></td>
		</tr>
		<tr>
		<td  class="td_title"><font color="red">*</font>费用类别：</td>
		  <td colspan="3">
		   <input type="text" id="feeTypeName" style="width:180px;height:20px;" onclick="selectFeeType();"/>
		   <input type="hidden" id="feeTypeId" />
		  </td>
		</tr>
		<tr><td colspan="4">&nbsp;</td></tr>
		<tr>
		  <td colspan="4"><input id="btn_exp_fact" class="btn_green" type="button" style="width:60px;" onclick="exportCashExcel();" value="导出模板"/></td>
		</tr>
		<tr>
		 <td colspan="4">
		  <form action="${ctx}/bis/bis-fact!importCashPoi.action" method="post" enctype="multipart/form-data" id="cashForm">
			<input type="file" id="importCashFile" name="importFile" style="line-height:22px;height:22px;"/>
			&nbsp;
			<s:radio id="importFactType" list="#{'1':'新增','2':'覆盖'}" name="importFactType"></s:radio>
			  <input type="button" class="btn_green fact_dime" style="width:65px;" onclick="importCashFactFile();" value="导入数据"/>
			</form>
		 </td>
		</tr>
	 </table>
	</div>