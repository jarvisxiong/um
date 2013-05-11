<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<script language="javascript" src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery.form.pack.js"  type="text/javascript"></script>
<script src="${ctx}/js/datePicker/WdatePicker.js" type="text/javascript"></script>
<script language="javascript">

</script>
<title>报销明细</title>

</head>

<body>
<input type="hidden" id="isEditDetl" value="${isEditDetl}" />
<fieldset>
	    <legend><s:text name="jbpmJbpmReimburseDetl"/></legend>
<div>
	<div id="tableDiv">
	<s:set name="details" value="jbpmReimburseDetls.size()" />
	<input type="hidden" id="detailSize" value="${details}" />
	<table class="showTable" id="showTable"  cellpadding="0" cellspacing="0" >
	<thead>
		<tr>
		<th width="35"><s:text name="jbpmJbpmReimburseDetl.startDate"/></th>
		<th width="35"><s:text name="jbpmJbpmReimburseDetl.endDate"></s:text></th>
		<th width="60"><s:text name="jbpmJbpmReimburseDetl.oriDestPlace"/></th>
		<th width="40"><s:text name="jbpmJbpmReimburseDetl.ticketFee"/></th>
		<th width="40"><s:text name="jbpmJbpmReimburseDetl.fareFee"/></th>
		<th width="30"><s:text name="jbpmJbpmReimburseDetl.lodgingStan"/></th>
		<th width="50"><s:text name="jbpmJbpmReimburseDetl.lodgingFee"/></th>
		<th width="26"><s:text name="jbpmJbpmReimburseDetl.lodgingDays"/></th>
		<th width="26"><s:text name="jbpmJbpmReimburseDetl.travelDays"/></th>
		<th width="50"><s:text name="jbpmJbpmReimburseDetl.travelFee"/></th>
		<th width="50"><s:text name="jbpmJbpmReimburseDetl.otherFee"/></th>
		<th width="55">费用小计</th>
		<th width="26" class="Vfont" ><s:text name="jbpmJbpmReimburseDetl.billCount"/></th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="jbpmReimburseDetls" >
		<tr id="main_${jbpmReimburseDetlId}" class="mainTr">
			<td id="startDate" title="<s:property value="startDate"/> "><s:date name="startDate" format="MM-dd" /></td>
			<td id="endDate" title="<s:property value="endDate"/>" ><s:date name="endDate" format="MM-dd" /></td>
			<td id="oriDestPlace" title="${oriDestPlace}" class="split">${oriDestPlace}</td>
			<td id="ticketFee"><s:property value="ticketFee"/></td>
			<td id="fareFee"><s:property value="fareFee"/></td>
			<td id="lodgingStan" ><s:property value="lodgingStan"/></td>
			<td id="lodgingFee" ><s:property value="lodgingFee"/></td>
			<td id="lodgingDays" ><s:property value="lodgingDays"/></td>
			<td id="travelDays" ><s:property value="travelDays"/></td>
			<td id="travelFee" ><s:property value="travelFee"/></td>
			<td id="otherFee" ><s:property value="otherFee"/></td>
			<td id="feeAmount"><s:property value="feeAmount"/></td>
			<td id="billCount"><s:property value="billCount"/></td>
		</tr>
		<s:if test="isEditDetl==1">
		<tr id="detail_${jbpmReimburseDetlId}" class="detailTr"  style="display:none">
			<td colspan="13">
				<fieldset>
				    <legend><s:text name="common.detail"/></legend>
				    <form class="editFormDetl" id="editFormDetl_${jbpmReimburseDetlId}" action="jbpm-reimburse-flow!saveDetail.action" method="post" >
				    	<input type="hidden" name="id" value="${id}" />
						<input type="hidden" name="isEditDetl" value="${isEditDetl}" />
				    	<input type="hidden" name="subId" value="${jbpmReimburseDetlId}" />
				      	<input type="hidden" name="jbpmReimburseDetl.recordVersion" value="${recordVersion}" />
				    <table class="editTable" cellspacing="10">
				    	<tr >
				    	<td width="12.5%" align="right"><s:text name="jbpmJbpmReimburseDetl.startDate"/>:</td>
				    	<td  width="20%" ><input validate="required"  type="text" value="<s:property value="startDate"/>"  id="startDate_${jbpmReimburseDetlId}" name="jbpmReimburseDetl.startDate" size="40"  onfocus="WdatePicker()" class="Wdate" /></td>
				    	<td  width="12.5%"  align="right"><s:text name="jbpmJbpmReimburseDetl.endDate"/>:</td>
				    	<td width="20%" ><input validate="required"  type="text" value="<s:property value="endDate"/>" id="endDate_${jbpmReimburseDetlId}" name="jbpmReimburseDetl.endDate" size="40"  onfocus="WdatePicker()" class="Wdate" /></td>
						<td  width="15%" align="right"><s:text name="jbpmJbpmReimburseDetl.oriDestPlace"/>:</td>
						<td   width="20%" ><input type="text" value="<s:property value="oriDestPlace"/>"  name="jbpmReimburseDetl.oriDestPlace" id="oriDestPlace_${jbpmReimburseDetlId}" /></td>
						</tr>
						<tr>
						<td align="right"><s:text name="jbpmJbpmReimburseDetl.ticketFee"/>:</td>
						<td><input validate="num[0,4]"  type="text" onkeyup="countAmount('${jbpmReimburseDetlId}');" value="<s:property value="ticketFee"/>"  name="jbpmReimburseDetl.ticketFee" id="ticketFee_${jbpmReimburseDetlId}" /></td>
						<td align="right"><s:text name="jbpmJbpmReimburseDetl.fareFee"/>:</td>
						<td><input validate="num[0,4,1]"  type="text" onkeyup="countAmount('${jbpmReimburseDetlId}');"  value="<s:property value="fareFee"/>"  name="jbpmReimburseDetl.fareFee" id="fareFee_${jbpmReimburseDetlId}" /></td>
						<td align="right"><s:text name="jbpmJbpmReimburseDetl.lodgingStan"/>:</td>
						<td><input validate="num[0,4]"  type="text" value="<s:property value="lodgingStan"/>"  name="jbpmReimburseDetl.lodgingStan" id="lodgingStan_${jbpmReimburseDetlId}" /></td>
						
						</tr>
						<tr>
						<td align="right"><s:text name="jbpmJbpmReimburseDetl.lodgingFee"/>:</td>
						<td><input validate="num[0,4,1]"  type="text" onkeyup="countAmount('${jbpmReimburseDetlId}');"  value="<s:property value="lodgingFee"/>"  name="jbpmReimburseDetl.lodgingFee" id="lodgingFee_${jbpmReimburseDetlId}" /></td>
						<td align="right"><s:text name="jbpmJbpmReimburseDetl.lodgingDays"/>:</td>
						<td><input validate="num[0,3]"  type="text" value="<s:property value="lodgingDays"/>"  name="jbpmReimburseDetl.lodgingDays" id="lodgingDays_${jbpmReimburseDetlId}" /></td>
						<td align="right"><s:text name="jbpmJbpmReimburseDetl.travelDays"/>:</td>
						<td><input validate="num[0,3,1]"  type="text" value="<s:property value="travelDays"/>"  name="jbpmReimburseDetl.travelDays" id="travelDays_${jbpmReimburseDetlId}" /></td>
						</tr>
						<tr>
						<td align="right"><s:text name="jbpmJbpmReimburseDetl.travelFee"/>:</td>
						<td><input validate="num[0,5]"  type="text" onkeyup="countAmount('${jbpmReimburseDetlId}');"  value="<s:property value="travelFee"/>"  name="jbpmReimburseDetl.travelFee" id="travelFee_${jbpmReimburseDetlId}" /></td>
						<td align="right"><s:text name="jbpmJbpmReimburseDetl.otherFee"/>:</td>
						<td><input validate="num[0,5,1]"  type="text" onkeyup="countAmount('${jbpmReimburseDetlId}');"  value="<s:property value="otherFee"/>"  name="jbpmReimburseDetl.otherFee" id="otherFee_${jbpmReimburseDetlId}" /></td>
						<td align="right"><s:text name="jbpmJbpmReimburseDetl.billCount"/>:</td>
						<td><input validate="num[0,3]"  type="text" value="<s:property value="billCount"/>"  name="jbpmReimburseDetl.billCount" id="billCount_${jbpmReimburseDetlId}" /></td>
						</tr>
						<tr>
						<td align="right"><s:text name="jbpmJbpmReimburseDetl.feeAmount"/>:</td>
						<td><input type="text" readonly="readonly" class="feeAmount readonly" value="<s:property value="feeAmount"/>"  name="jbpmReimburseDetl.feeAmount" id="feeAmount_${jbpmReimburseDetlId}" /></td>
						<td></td><td></td><td ></td><td></td>
						</tr>
				    	<tr><td colspan="6">
				    	<div class="toolBar" align="center" >
						<table>
						<tr><td>
						<div class="button" onclick="editDetl('${jbpmReimburseDetlId}','${id}');"><div class="btn_save"></div><span class="text"><s:text name="common.save" /></span></div>
						<div class="button" onclick="deleteDetl('${jbpmReimburseDetlId}');"><div class="btn_del_gray"></div><span class="text"><s:text name="common.delete" /></span></div>
						<div class="button" onclick="cancelEdit();"><div class="btn_cancel"></div><span class="text"><s:text name="common.cancel" /></span></div>
						</td>
						</tr>
						</table>
						</div>	
				    	</td></tr>
				    </table>
				    </form>
				 </fieldset>
			</td>
		</tr>
		</s:if>
	</s:iterator>
	</tbody>
</table>	
	</div>
<s:if test="isEditDetl==1">
<div id="divParent">
	<div id="divNew"  >
	<fieldset>
	    <legend><s:text name="common.create"/></legend>
	<div>
	<form id="createFormDetl" action="jbpm-reimburse-flow!saveDetail.action"  method="post">
		<table class="editTable" cellspacing="5px">
				<tr>
		    	<td width="12.5%" align="right">
			<input type="hidden" name="id" value="${id}" />
			<input type="hidden" name="isEditDetl" value="${isEditDetl}" />
			<s:text name="jbpmJbpmReimburseDetl.startDate"/>:</td>
		    	<td width="20%"><s:textfield validate="required" id="startDate" name="jbpmReimburseDetl.startDate" size="40"  onfocus="WdatePicker()" cssClass="Wdate" /></td>
		    	<td  width="12.5%" align="right"><s:text name="jbpmJbpmReimburseDetl.endDate"/>:</td>
		    	<td width="20%"><s:textfield validate="required"  id="endDate" name="jbpmReimburseDetl.endDate" size="40"  onfocus="WdatePicker()" cssClass="Wdate" /></td>
				<td  width="15%" align="right"><s:text name="jbpmJbpmReimburseDetl.oriDestPlace"/>:</td>
				<td  width="20%"><s:textfield name="jbpmReimburseDetl.oriDestPlace" id="oriDestPlace_" /></td>
				
				</tr>
				<tr>
				<td width="60" align="right"><s:text name="jbpmJbpmReimburseDetl.ticketFee"/>:</td>
				<td><s:textfield validate="num[0,4]"  onkeyup="countAmount('');" name="jbpmReimburseDetl.ticketFee" id="ticketFee_" /></td>
				<td width="60" align="right"><s:text name="jbpmJbpmReimburseDetl.fareFee"/>:</td>
				<td><s:textfield validate="num[0,4,1]"  onkeyup="countAmount('');" name="jbpmReimburseDetl.fareFee" id="fareFee_" /></td>
				<td width="60" align="right"><s:text name="jbpmJbpmReimburseDetl.lodgingStan"/>:</td>
				<td><s:textfield validate="num[0,4]"  name="jbpmReimburseDetl.lodgingStan" id="lodgingStan_" /></td>
				</tr>
				<tr>
				<td width="60" align="right"><s:text name="jbpmJbpmReimburseDetl.lodgingFee"/>:</td>
				<td><s:textfield validate="num[0,4,1]"  onkeyup="countAmount('');" name="jbpmReimburseDetl.lodgingFee" id="lodgingFee_" /></td>
				<td width="60" align="right"><s:text name="jbpmJbpmReimburseDetl.lodgingDays"/>:</td>
				<td><s:textfield validate="num[0,3]"  name="jbpmReimburseDetl.lodgingDays" id="lodgingDays_" /></td>
				<td width="60" align="right"><s:text name="jbpmJbpmReimburseDetl.travelDays"/>:</td>
				<td><s:textfield validate="num[0,3,1]"  name="jbpmReimburseDetl.travelDays" id="travelDays_" /></td>
				</tr>
				<tr>
				<td width="60" align="right"><s:text name="jbpmJbpmReimburseDetl.travelFee"/>:</td>
				<td><s:textfield validate="num[0,5]required"  onkeyup="countAmount('');" name="jbpmReimburseDetl.travelFee" id="travelFee_" /></td>
				<td width="60" align="right"><s:text name="jbpmJbpmReimburseDetl.otherFee"/>:</td>
				<td><s:textfield validate="num[0,5,1]"   onkeyup="countAmount('');" name="jbpmReimburseDetl.otherFee" id="otherFee_" /></td>
				<td width="60" align="right"><s:text name="jbpmJbpmReimburseDetl.billCount"/>:</td>
				<td><s:textfield validate="num[0,3]"  name="jbpmReimburseDetl.billCount" id="billCount_" /></td>
				</tr>
				<tr>
				<td width="60" align="right"><s:text name="jbpmJbpmReimburseDetl.feeAmount"/>:</td>
				<td><s:textfield readonly="true" cssClass="feeAmount readonly"  name="jbpmReimburseDetl.feeAmount" id="feeAmount_" /></td>
				<td></td><td></td><td></td><td></td>
				</tr>
		</table>	
	</form>	
	<div class="toolBar" align="center">
			<input type="button" class="btn btnSave" value="<s:text name="common.save" />" onclick="createDetl('${id}');"/>
			<input type="button" class="btn btnCancel" value="<s:text name="common.cancel" />" onclick="cancelNew();"/>
	</div>
	</div>
	</fieldset>
</div>
</div>
<div class="toolBar" id ="divToolBar" align="center" >
<table>
<tr>
<td>
<input type="button" id="btnNew" class="btn btnNew" value="<s:text name="common.create" />"/>
</td>
</tr>
</table>
</div>
</s:if>
</div>
</fieldset>
<script language="javascript">
	var isEditDetl=$("#isEditDetl").val();
	var divNew=$("#divNew").clone();
	//是否在编辑子表
	var isEditing=false;
	//主表是否发生变化
	$("#divParent").empty();
	
	$("#btnNew").bind("click", function(){
		divNew.appendTo("#divParent");
		$("#divToolBar").hide();
		cancelEdit();
		sum();
		isEditing=true;
		if(config['styleCall'])config['styleCall']();
	});
	if (isEditDetl=='1'){
	$("#showTable tbody tr.mainTr").toggle(function(){
		$(".foldTr").trigger("click");
		$(this).addClass("foldTr");
		$(this).children().eq(0).children().attr("src","${ctx}/images/up.gif");
		$(this).next().show();
		cancelNew();
		//isEditing=true;
		$("#divToolBar").hide();
	},function(){
		$(this).removeClass("foldTr");
		$(this).children().eq(0).children().attr("src","${ctx}/images/down.gif");
		$(this).next().hide();
		$("#divToolBar").show();
		//isEditing=false;
	});
	}
	$("#showTable tbody tr.detailTr :input").keyup(function(){
		var id = this.id;
		id=id.split("_")[0];
		$(this).parents(".detailTr").prev().children("[id="+id+"]").text($(this).val());
	});
	
</script>
</body>
</html>
