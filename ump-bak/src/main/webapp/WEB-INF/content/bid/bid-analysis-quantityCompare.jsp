<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %> 
<div id="colorPanel" style="clear:both;padding-bottom: 10px;margin-left: 400px;margin-top: 10px;line-height: 20px;">
						<div title="红色" class="demoTip top_bk" style="float: left;"></div><div class="demoText" style="float: left;">表示最高;</div>
						<div title="蓝色" class="demoTip low_bk" style="float: left;"></div><div class="demoText" style="float: left;">表示最低;</div>
						<div title="绿色" class="demoTip avg_bk" style="float: left;"></div><div class="demoText" style="float: left;">表示超过正负15%;</div>
</div>
<div id="resultList"  style="clear: both;padding-top: 5px;" >
	<table id="quantityCompare" style="padding-left:0px; float:left;">
		<tr>
			<td>
				<div >
					<table  class="analysisHeaderOne" width="405">
						<col width="36"/>
						<col width="89"/>
						<col width="79"/>
						<col width="88"/>
						<col width="58"/>
						<col width="58"/>
						<tr height="48px">
							<td rowspan="2" style="vertical-align: middle;" nowrap="nowrap" >序号</td>
							<td rowspan="2" nowrap="nowrap" style="vertical-align: middle;padding-left: 2px;" title="项目编号">项目编号</td>
							<td rowspan="2" nowrap="nowrap" style="vertical-align: middle;padding-left: 2px;" title="项目名称">项目名称</td>							
							<td rowspan="2" nowrap="nowrap" style="vertical-align: middle;padding-left: 2px;" title="项目特征描述">项目特征描述</td>
							<td rowspan="2" nowrap="nowrap" style="vertical-align: middle;padding-left: 2px;border-right: 0px;" title="计量单位">计量单位</td>	
							<td rowspan="2" nowrap="nowrap" style="vertical-align: middle;padding-left: 2px;border-right: 0px;" title="工程量">工程量</td>	
						</tr>
					</table>
				</div>
			</td>
			<td>
				<div id="headerCols" style="float: left; border-bottom: 0px solid rgb(170, 171, 176); overflow: hidden; width: 690px;height:49px;">
					<table id="topRightTitle" class="topRightTitle" style="width: ${colNumber*actCouNum*80}px;float:left;" >					
							<tr>${tableHeader1}</tr>
							<tr>${tableHeader2}</tr>					
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td  valign="top" id="hightTd">
				<div style="overflow: hidden;border-right: 0px;" id="hightDiv">
					<table id="bottomLeftTitle" style="border-top: 0pt none; margin-top: 0px;height: 220px;border-right: 0px;" class="downLeft" width="405" border="1">
					<colgroup>
					<col width="35"/>
					<col width="85"/>
					<col width="75"/>
					<col width="85"/>
					<col width="56"/>
					<col width="55"/>
					</colgroup>
					<s:iterator value="divisionVoList" var="divisionVoList"  status="index">
						<tr style="height: 15px;"  class="commonTr">
							<td align="center" ><s:property value="#index.index+1"/></td>
							<td   title="${itemNo}"  nowrap="nowrap" style="overflow:hidden;height: 15px;padding-left: 2px;">${itemNo}</td>
							<td   title="${itemName}"  nowrap="nowrap" style="overflow:hidden;height: 15px;padding-left: 2px;"><div  style="width:70px;overflow:hidden;padding-left: 2px;" class="leftTableCell">${itemName}</div></td>
							<td   title="${itemDesc}" nowrap="nowrap" style="overflow:hidden;height: 15px;padding-left: 2px;"><div  style="width:70px;overflow:hidden;padding-left: 2px;" class="leftTableCell">${itemDesc}</div></td>	
							<td   title="${measurement}" nowrap="nowrap" style="overflow:hidden;height: 15px;padding-left: 2px;"><div  style="width:40px;overflow:hidden;padding-left: 2px;" class="leftTableCell">${measurement}</div></td>		
							<td   title="${quantitie}" nowrap="nowrap" style="overflow:hidden;height: 15px;padding-left: 2px;border-right: 0px;"><div  style="width:40px;overflow:hidden;padding-left: 2px;" class="leftTableCell">${quantitie}</div></td>		
						</tr>
					</s:iterator>	
					</table>
				</div>
			</td>
			<td style="font-size: 14px; " valign="top" id="hightTd2">
				<div id="comDiv" style="overflow-x: auto; overflow-y: scroll; width: 690px; padding-top:0px;margin-top:0px;" onscroll="resetLayout($(this));">
					<table class="rightDetail" width="${colNumber*actCouNum*80+1}px" style="float:left;height: 220px;">
					<s:iterator value="divisionVoList" var="divisionVoList"  status="index">
						<tr style="height: 15px;"  class="commonTr">
							<s:iterator  value="bidSupCompareVos"  var="sup"  status="stat" >	
								<s:if test='fieldCount>0'>
									<%--
									
									<s:if test='selectProperty.indexOf("quantitie")>-1'>
										<td  class='qtyCompare'  nodetype="quantitie" nowrap="nowrap"  title="${quantitie}" 
										<s:if test="headType==10"> pingjun="pingjun" </s:if>
										>${quantitie}</td>
									</s:if>
									 --%>
									<s:if test='selectProperty.indexOf("unitPrice")>-1'>
										<td  class='qtyCompare'  nodetype="unitPrice" nowrap="nowrap"  title="${unitPrice}" 
										<s:if test="headType==10"> pingjun="pingjun"  unitPriceAvg="1" </s:if>
										>${unitPrice}</td>
									</s:if>
									<s:if test='selectProperty.indexOf("totalPrice")>-1'>
										<td  class='qtyCompare' nodetype="totalPrice" nowrap="nowrap"  title="${totalPrice}" 
										<s:if test="headType==10"> pingjun="pingjun"  totalPriceAvg="1" </s:if>
										>${totalPrice}</td>
									</s:if>				
								</s:if>
								<s:else>
									<%--
									<td align="center" class='qtyCompare'  nodetype="quantitie" nowrap="nowrap" title="${quantitie}" 
									<s:if test="headType==10"> pingjun="pingjun" </s:if>
									 >${quantitie}</td>
									 --%>
									<td  align="center" class='qtyCompare'   nodetype="unitPrice"  nowrap="nowrap" title="${unitPrice}" 
									<s:if test="headType==10"> pingjun="pingjun" unitPriceAvg="1" </s:if>
									>${unitPrice}</td>
									<td  align="center" class='qtyCompare'   nodetype="totalPrice" nowrap="nowrap" title="${totalPrice}" 
									<s:if test="headType==10"> pingjun="pingjun" totalPriceAvg="1" </s:if>
									>${totalPrice}</td>
								</s:else>	
							</s:iterator>
						</tr>
					</s:iterator>
				</table>
				</div>
			</td>
		</tr>
		
	</table>
	<div class="table_pager" style="margin-top:5px;" url="${ctx}/bid/bid-division-sup-rel!list.action">
					<p:page  pageInfo="voPage"/>
			</div>	
	<script type="text/javascript">
	$(function() {
		var width=parseFloat($("#curDocWidth").val());
		var leftTable=325;		
		var rswidth=width-leftTable-100;		
		$("#comDiv").css('width',rswidth+'px');
		$("#headerCols").css('width',rswidth+'px');		
		$("#colorPanel").css('margin-left',width/2-150+'px');

		//如果URL中含有&new,则重新获取高度	
		var url = location.href;
		var indexer=url.indexOf('&new');
		if(indexer>0){
			if($("#quantityHeight").val()==null||$("#quantityHeight").val()=='')
			$("#quantityHeight").val(document.body.clientHeight+110);
		}
		else{
			if($("#quantityHeight").val()==null||$("#quantityHeight").val()=='')
				$("#quantityHeight").val(document.body.clientHeight);
		}
		
		//alert($("#quantityHeight").val());
		$("#hightTd").css('height',$("#quantityHeight").val()+'px');		
		$("#hightDiv").css('height',$("#quantityHeight").val()+'px');
		$("#hightTd2").css('height',$("#quantityHeight").val()+'px');
		$("#comDiv").css('height',$("#quantityHeight").val()+'px');
		
		
	});	
	//滚动时,自动保持左下/右上/右下布局对齐.
	function resetLayout(obj) {
		var t = parseInt(obj.scrollTop());
		var l = parseInt(obj.scrollLeft());
		
		$("#bottomLeftTitle").css("margin-top", -t + "px");
		$("#topRightTitle").css("margin-left", -l + "px");
	}
	</script>
</div>