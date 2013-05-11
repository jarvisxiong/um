<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div id="divisionList"   >
<div style="clear: both;overflow-x:auto;">
	<table class="bidTable">
		<thead>
				<tr >
					<th  style="background: none;padding-left: 2px;padding-right: 2px;width: 12.5%;">工程名称</th>
					<th style="padding-left: 2px;padding-right: 2px;width: 12.5%;">分部分项</th>
					<th style="padding-left: 2px;padding-right: 2px;width: 12.5%;">措施项目1</th>
					<th style="padding-left: 2px;padding-right: 0px;width: 12.5%;">措施项目2</th>
					<th style="padding-left: 2px;padding-right: 0px;width: 12.5%;">其他项目</th>
					<th style="padding-left: 2px;padding-right: 0px;width: 12.5%;">零星工程</th>
					<th style="padding-left: 2px;padding-right: 0px;width: 12.5%;">规费项目</th>
					<th style="padding-left: 2px;padding-right: 0px;width: 12.5%;">税金项目</th>
				</tr>
		</thead>
	</table>
</div>
<div style="overflow-y:scroll; height: 300px;">
	<table  class="bidTable" style="border: 0px none; border-collapse: separate;" cellspacing="0" cellpadding="0">
		<tbody >
		 	<s:iterator  value="bidProjectAllVoList"   status="index" >
		 		<tr>
		 			<td style="padding-left: 2px;padding-right: 2px;width: 65px;" title="${projectName}"><div  class='ellipsisDiv analysisTd'  style="width: 60px;">${projectName}</div></td>
		 			<td  style="padding-left: 2px;padding-right: 2px;width: 65px;" title="${amtOne}"><div  class='ellipsisDiv analysisTd'  style="width: 60px;">${amtOne}</div>
		 				<input type="hidden" class="amtOne" value="${amtOne}">
		 			</td>
		 			<td  style="padding-left: 2px;padding-right: 2px;width: 65px;" title="${amtTwo}"><div  class='ellipsisDiv analysisTd'  style="width: 60px;">${amtTwo}</div>
		 				<input type="hidden" class="amtTwo" value="${amtTwo}">
		 			</td>
		 			<td  style="padding-left: 2px;padding-right: 2px;width: 65px;" title="${amtThree}"><div  class='ellipsisDiv analysisTd'  style="width: 60px;margin-left: 8px;">${amtThree}</div>
		 				<input type="hidden" class="amtThree" value="${amtThree}">
		 			</td>
		 			<td  style="padding-left: 2px;padding-right: 2px;width: 65px;" title="${amtFour}"><div  class='ellipsisDiv analysisTd'  style="width: 60px;margin-left: 8px;">${amtFour}</div>
		 				<input type="hidden" class="amtFour" value="${amtFour}">
		 			</td>
		 			<td  style="padding-left: 2px;padding-right: 2px;width: 65px;" title="${amtFive}"><div  class='ellipsisDiv analysisTd'  style="width: 60px;margin-left: 8px;">${amtFive}</div>
		 				<input type="hidden" class="amtFive" value="${amtFive}">
		 			</td>
		 			<td  style="padding-left: 2px;padding-right: 2px;width: 65px;" title="${amtSix}"><div  class='ellipsisDiv analysisTd' style="width: 60px;margin-left: 8px;" >${amtSix}</div>
		 				<input type="hidden" class="amtSix" value="${amtSix}">
		 			</td>
		 			<td  style="padding-left: 2px;padding-right: 2px;width: 65px;" title="${amtSeven}"><div  class='ellipsisDiv analysisTd'  style="width: 60px;margin-left: 8px;">${amtSeven}</div>
		 				<input type="hidden" class="amtSeven" value="${amtSeven}">
		 			</td>
		 		</tr>
		 	</s:iterator>
		 		<tr>
		 			<td  style="padding-left: 2px;padding-right: 2px;width: 65px;" title="汇总"><div style="width: 60px;" class='ellipsisDiv analysisTd'>汇总</div></td>
		 			<td  style="padding-left: 2px;padding-right: 2px;width: 65px;" id="amtOneTotal" title="0"><div style="width: 60px;margin-left: 8px;"   id="amtOne" class='ellipsisDiv analysisTd' >0</div></td>
		 			<td  style="padding-left: 2px;padding-right: 2px;width: 65px;" id="amtTwoTotal" title="0}"><div  style="width: 60px;margin-left: 8px;" id="amtTwo"  class='ellipsisDiv analysisTd' >0</div></td>
		 			<td  style="padding-left: 2px;padding-right: 2px;width: 65px;" id="amtThreeTotal" title="0"><div  style="width: 60px;margin-left: 8px;" id="amtThree"  class='ellipsisDiv analysisTd' >0</div></td>
		 			<td  style="padding-left: 2px;padding-right: 2px;width: 65px;" id="amtFourTotal" title="0"><div  style="width: 60px;margin-left: 8px;" id="amtFour"  class='ellipsisDiv analysisTd' >0</div></td>
		 			<td  style="padding-left: 2px;padding-right: 2px;width: 65px;"id="amtFiveTotal" title="0"><div  style="width: 60px;margin-left: 8px;" id="amtFive"  class='ellipsisDiv analysisTd' >0</div></td>
		 			<td  style="padding-left: 2px;padding-right: 2px;width: 65px;" id="amtSixTotal" title="0"><div  style="width: 60px;margin-left: 8px;" id="amtSix"  class='ellipsisDiv analysisTd' >0</div></td>
		 			<td style="padding-left: 2px;padding-right: 2px;width: 65px;"  id="amtSevenTotal" title="0"><div  style="width: 60px;margin-left: 8px;" id="amtSeven"  class='ellipsisDiv analysisTd' >0</div></td>
		 		</tr>
		</tbody>
	</table>
</div>

</div>
<script type="text/javascript">
$(function() {
	var total1=0;
	var total2=0;
	var total3=0;
	var total4=0;
	var total5=0;
	var total6=0;
	var total7=0;
	$(".amtOne").each(function(i,dom){
		total1+=parseFloat($(dom).val().trim());
	});
	$(".amtTwo").each(function(i,dom){
		total2+=parseFloat($(dom).val().trim());
	});
	$(".amtThree").each(function(i,dom){
		total3+=parseFloat($(dom).val().trim());
	});
	$(".amtFour").each(function(i,dom){
		total4+=parseFloat($(dom).val().trim());
	});
	$(".amtFive").each(function(i,dom){
		total5+=parseFloat($(dom).val().trim());
	});
	$(".amtSix").each(function(i,dom){
		total6+=parseFloat($(dom).val().trim());
	});
	$(".amtSeven").each(function(i,dom){
		total7+=parseFloat($(dom).val().trim());
	});
	
	$("#amtOne").html(total1);
	$("#amtOneTotal").attr("title",total1);
	
	$("#amtTwo").html(total2);
	$("#amtTwoTotal").attr("title",total2);
	
	$("#amtThree").html(total3);
	$("#amtThreeTotal").attr("title",total3);
	
	$("#amtFour").html(total4);
	$("#amtFourTotal").attr("title",total4);
	
	$("#amtFive").html(total5);
	$("#amtFiveTotal").attr("title",total5);
	
	$("#amtSix").html(total6);
	$("#amtSixTotal").attr("title",total6);
	
	$("#amtSeven").html(total7);
	$("#amtSevenTotal").attr("title",total7);
	
	});

//-->
</script>