<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/resources/css/bis/bis-shop.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
</head>
<body>
<div id="search_condtion" class="title_bar" style="height:60px;">
搜索：年份<input type="text" id="yearQuery" onfocus="WdatePicker({dateFmt:'yyyy',onpicked:function(dp){}})" value="${yearQuery}" style="width:120px;">
&nbsp;月份<input type="text" id="monthQuery" onfocus="WdatePicker({dateFmt:'MM',onpicked:function(dp){}})" value="${monthQuery}" style="width:120px;">
项目<s:select list="mapBisProject" listKey="key" listValue="value"  id="bisProjectId"></s:select>
楼号<input type="text" id="floatQuery" style="width:120px;">
<br>
<input type="checkbox" id="typeCd_wy" value="32"/> 物业费
<input type="checkbox" id="typeCd_water" value="33"/> 水费
<input type="checkbox" id="typeCd_elec" value="34"/> 电费
<input type="checkbox" id="typeCd_publ" value="35"/> 水电公摊
<input type="checkbox" id="typeCd_aircond" value="36"/>空调费
<input type="checkbox" id="typeCd_tena" value="37"/> 电梯费
<input type="checkbox" id="typeCd_Perfbond" value="38"/> 物业履约保证费

&nbsp;<input type="button" value="搜索" onclick="tenantReportSearch();" class="btn_green_55_20 btn_blue">
</div>
<div id="resultDiv"></div>
<script type="text/javascript">
$(function(){
	var yearQuery =$("#yearQuery").val();
	var monthQuery =$("#monthQuery").val();
	var bisProjectId =$("#bisProjectId").val();
	searchReport(yearQuery,monthQuery,bisProjectId,"");
});
function tenantReportSearch(){
	var yearQuery =$("#yearQuery").val();
	var monthQuery =$("#monthQuery").val();
	var bisProjectId =$("#bisProjectId").val();
	if(""==yearQuery){
		alert("请选择年份");
		return;
	}
	if(""==monthQuery){
		alert("请选择月份");
		return;
	}
	if(""==bisProjectId){
		alert("请选择项目");
		return;
	}
	var chargeTypeCd ="";
	chargeTypeCd+=$("#typeCd_wy:checked").val()!='32'?'':'32,';
	chargeTypeCd+=$("#typeCd_water:checked").val()!='33'?'':'33,';
	chargeTypeCd+=$("#typeCd_elec:checked").val()!='34'?'':'34,';
	chargeTypeCd+=$("#typeCd_publ:checked").val()!='35'?'':'35,';
	chargeTypeCd+=$("#typeCd_aircond:checked").val()!='36'?'':'36,';
	chargeTypeCd+=$("#typeCd_tena:checked").val()!='37'?'':'37,';
	chargeTypeCd+=$("#typeCd_Perfbond:checked").val()!='38'?'':'38,';
	if(""==chargeTypeCd){
		alert("请选择类型");
		return;
	}
	chargeTypeCd =chargeTypeCd.substring(0,chargeTypeCd.length-1);
	searchReport(yearQuery,monthQuery,bisProjectId,chargeTypeCd);
}
function searchReport(yearQuery,monthQuery,bisProjectId,chargeTypeCd){
	if(chargeTypeCd==""){
		chargeTypeCd="32,33";
	}
	$.post("${ctx}/bis/bis-flat!flatList.action",
			{
		      yearQuery:yearQuery,
		      monthQuery:monthQuery,
		      bisProjectId:bisProjectId,
		      chargeTypeCd:chargeTypeCd
		    },
			function(result) {
				if (result) {
					$("#resultDiv").html(result);
					var typeStr =(chargeTypeCd.split(",")).length;
					$("#rightTable").css("width",400*typeStr+"px");
				}
			}
		);
}
</script>
</body>
</html>