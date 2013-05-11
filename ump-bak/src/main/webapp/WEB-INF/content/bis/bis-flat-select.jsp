<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
</head>
<body>
<input type="hidden" id="bisFlatId" >
<input type="hidden" id="bisContId" >
<input type="hidden" id="FlatName" >
<div id="selectFlatDiv" style="margin:5px;">
</div>
<div id="selectFloorDiv"  style="margin:5px;overflow: auto;height:280px;border:1px solid #DBDBDB">
</div>
<script type="text/javascript">
   $.post("${ctx}/bis/bis-fact!searchFlatNo.action",{bisProjectId:'${bisProjectId}'},function(result){
	   var data = eval(result);
		//$("#selectFlatDiv").html('');
		var content ="<select onchange=flatDetail(this.value)><option value='' >请选择</option> ";
		$.each(data,function(i,n){
			if(n.text){
				content+='<option value="'+n.id+'" >'+n.text+'</option>';
			}
		});
		content +="</select><div style='padding:3px;'></div>";
		//新增标记
		content +="<span style='padding-left:5px;font-weight:bold;'>公寓编号</span>&nbsp;<span style='padding-left:60px;font-weight:bold;'>合同编号</span>";
		$("#selectFlatDiv").append(content);
	});
function flatDetail(floorId){
 $.post("${ctx}/bis/bis-fact!searchFloorNo.action",{bisProjectId:'${bisProjectId}',floorId:floorId},function(result){
	 var data = eval(result);
	 $("#selectFloorDiv").html('');
	 var content ="<table id='mainTable' class='currtr'> <col width='100px'/><col/>";
		$.each(data,function(i,n){
			if(n.text){
				content+="<tr id="+n.id+"_TD onclick=setFlat('"+n.id+"','"+n.text+"','"+n.key+"')>";
				if("cont"==n.key){
					content+="<td><div style='overflow:hidden;'>"+n.textAttach+"</div></td><td>"+n.text+"</td>";
				}else{
					content+="<td>"+n.text+"</td><td>"+n.textAttach+"</td>";
				}
				content+="</tr>";
			}
		});
		$("#selectFloorDiv").append(content);
 });
}


function getFlatNo(){
	 return $('#FlatName').val();
}
function getContId(){
	return $('#bisContId').val();
}
function  getFlatId(){
	 return $('#bisFlatId').val();
}
function setFlat(bisFlatId,FlatName,key){
//	alert($('#'+uniqueId).val());
    if("cont"==key){
    	$("#bisContId").val(bisFlatId);
    }else{
    	  $('#bisFlatId').val(bisFlatId);
    }
    $('#FlatName').val(FlatName);
	$('#mainTable  tr').removeClass("addFirst");
	$('#'+bisFlatId+'_TD').addClass("addFirst");
	
//	$('#nameCn').html(values[0]); 
	
// alert($('#valueColl').val());
}
</script>
</body>