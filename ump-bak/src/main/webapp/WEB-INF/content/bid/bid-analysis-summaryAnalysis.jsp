<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="/common/taglibs.jsp" %>

<div id="resultList"  style="width:100%;clear: both;overflow-x:auto;" >

<table  class="bidTable" style="border: 0px none; border-collapse: separate;" cellspacing="0" cellpadding="0">
	
	<s:iterator  value="summaryVoList"  var="summaryAnalysisVo"  status="status" >
	 		<s:if test="#status.index==0">
	 			<thead>
					<tr>
						<th align="center" style="background-image:url('');vertical-align: middle;width: 2%;" nowrap="nowrap">序号</th>
						<th nowrap="nowrap"  style="width:8%;"  ><div  class='ellipsisDiv' style="width:150px;overflow:hidden;" >${itemName}</div></th>
						<th nowrap="nowrap" style="width:8%;" ><div  class='ellipsisDiv' style="width:100px;overflow:hidden;" >${supTotalPrice}(元)</div></th>
						<s:iterator  value="totalValues"  var="col"  status="status" >
							<th  nowrap="nowrap"  col="<s:property value="#status.index+3"/>" style="width:8%;" title="<s:property/>">
								<div  class='ellipsisDiv' style='width:100px;overflow:hidden;' >
									<s:property/>
								</div>
							</th>
						</s:iterator>		
					</tr>
				</thead>
	 		</s:if>
	 		<s:else>
	 			
	 				<tr  line="<s:property value="#status.index"/>" >
	 				<td  style="text-align: center;border-left-color: white;"><s:property value="#status.index"/></td>
	 					<td nowrap="nowrap"  style="width:18%;height:24px;" col="1">${itemName}</td>
						<td nowrap="nowrap" col="2"  style="padding-left: 5px;" >${supTotalPrice}</td>
						<s:iterator  value="totalValues"  var="col"  status="status" >
							<td style="padding-left: 5px;" col="<s:property value="#status.index+3"/>" value="<s:property/>"><s:property/></td>
						</s:iterator>		
	 				</tr>
				
	 		</s:else>
	 		<s:if test="#status.index==summaryVoList.size()-1">
	 			<tr >	 				
	 					<td nowrap="nowrap"  style="height:24px;text-align: right;"  align="right" colspan="2"><font color="red">汇总价：</font></td>
						<td id="diaodiTotal">${biaodiTotal}</td>
						<s:iterator  value="totalValues"  var="col"  status="status" >
							<td totalTd="total" id="col_<s:property value="#status.index+3"/>" col="<s:property value="#status.index+3"/>">0</td>
						</s:iterator>		
	 			</tr>
	 		</s:if>
	 </s:iterator>
		
</table>
<div id="cloned"></div>
</div>
<script language="javascript">
$(function() {
	var i=0;
	var dom=$(".bidTable > tbody tr");
	var headerTds=$(".bidTable > thead tr th").length;
	var dom_size=$(".bidTable > tbody tr").length;

	//填充最后一行(合计)
	for(var t = 2; t < headerTds; t++){
		var total = 0;
		dom.each(function (){
		 	if(i == headerTds-1){
				//最后一行,忽略
			}
		 	else{
			 	//累加列值
				$(this).children().each(function (){
					 var o = $(this).attr("col");
					 if(o == t){
						 total += parseFloat($(this).html());
					 }
				 });
			 }
			i++;
		});
		//alert(total);
		$("#col_"+t).html(total.toFixed(2));
		$("#col_"+t).attr('value',total.toFixed(2));
	}
	
	//克隆表
	$("table.bidTable").clone().prependTo("#cloned").removeClass('bidTable').addClass('add');

	var data=[];	
	var map=new HashMap();
	for(var t=3;t<headerTds;t++){
		data[t-3]=$("#cloned td[id='col_"+t+"']").attr("value");
	}	
	var tmp;	
	for(var i=0;i<data.length;i++){
			for(var j=0;j<data.length-i-1;j++){
					if(parseFloat(data[j])>parseFloat(data[j+1])){
						tmp=data[j];
						data[j]=data[j+1];
						data[j+1]=tmp;
						}
				}
		}
	//清除数据
	$("#cloned tr ").each(function(){
		for(var t=3;t<headerTds;t++){
			$(this).find("td[col='"+t+"']").empty();
			$(this).find("th[col='"+t+"']").empty();
		}
		});
	//获取列
	for(var i=0;i<data.length;i++){
		var val=data[i];
		var col='';
		//处理数值相同的问题
		if(map.get(val)!=null){
			var num=map.get(val);
			map.remove(val);
			map.put(val,num+1);
			var cnt=1;
			$(".bidTable > tbody tr td[value='"+val+"'][totalTd='total']").each(function(){
				if(num+1==cnt){
					col=$(this).attr("col");
					}					
					cnt++;
				});	
				
			}
		else{
			map.put(val,1);
			 col=$(".bidTable > tbody tr td[value='"+val+"']").attr("col");
			}	
		var thVal=$(".bidTable > thead tr th[col='"+col+"'] div").html();			
		var recol=i+3;
		$("#cloned th[col='"+recol+"']").html("<div  class='ellipsisDiv' style='width:100px;overflow:hidden;' >"+thVal+"</div>");
		//$("#cloned th[col='"+recol+"']").apend('<div  class='ellipsisDiv' style="width:100px;overflow:hidden;" >'+thVal+'</div>');
		$("#cloned th[col='"+recol+"']").attr('title',thVal);
				var k = 0; 		
				$("#cloned tr").each(function(){
					var tr_two = $(this);
					if(tr_two.attr("line")){
						var l = tr_two.attr('line');
						var tr_one = $(".bidTable > tbody tr[line='"+l+"']");									
						var val_one = tr_one.find("td[col='"+col+"']").html();
						tr_two.find("td[col='"+recol+"']").html(val_one);
						tr_two.find("td[col='"+recol+"']").attr('title',val_one);
					}
					else{
						if(k>0){
								var total = $(".bidTable > tbody td[id='col_"+col+"'][totalTd='total']").html();								
								tr_two.find("td[id='col_"+recol+"']").html(total);
								tr_two.find("td[id='col_"+recol+"']").attr('title',total);
							}						
						}	
					k++;
					});
		}
	//替换表格
	$("#cloned .add").removeClass('add').addClass('bidTable');
	$(".bidTable").replaceWith($("#cloned .bidTable"));
	

	
});	
</script>