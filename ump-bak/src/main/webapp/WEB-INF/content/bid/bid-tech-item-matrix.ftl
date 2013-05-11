<#setting number_format="#">

<#assign leftWidth=360 /><!-- (宽度)评审内容-->
<#assign fieldWidth1=80 /><!-- (宽度)得分 -->
<#assign fieldWidth2=130 /><!-- (宽度)情况说明 -->
<#assign rightWidth=(bidSupList.size()*(fieldWidth1+fieldWidth2))/>
<#assign totalWidth=leftWidth+rightWidth/>
<#assign visableFlg=bidLedger.visableFlg/>
<div>
<table class="freemarkTable1" style='width: expression(this.parentNode.offsetHeight >this.parentNode.scrollHeight ? "100%" :parseInt(this.parentNode.offsetWidth - 18) + "px");border:0px;'>
	<col width="${leftWidth}px"/>
	<col />
	<tr>
	<!-- 左上 -->
	<td style="text-align:center;background-color:#E4E7EC;border:1px solid #aaabb0;border-right:0px;height:48px;border-bottom:0px;">
		<div  height="48"  style="border-bottom:0px;">
			 <table height="48" style="border-bottom:0px;">
			 	<tr>
			 		<td height="48"  style="border-bottom:0px;">评审内容</td>
			 	</tr>
			 </table>
		</div>
	</td>
	<!-- 右上 -->
	<td valign="top" style="padding:0;border-bottom:0px;height:48px;">
		<div id="matrixUpRightDiv" style="border:0px;">
		<table id="matrixUpRightTable" class="freemarkTable" style="width:${rightWidth}px;">
		<#if (bidSupList.size() > 0)>
		<tr>
		 	<#list bidSupList as sup>
		 	<td colspan="2">
		 	 	<div onclick="openAttachmentRefresh($(this),'${sup.bidSupId}','bidSupTech')"
			 	 	 <#if sup.techAttachStatusCd !=null && sup.techAttachStatusCd !=0 >
			  			class="hasAttachFile"
			  			title="已上传附件"
			  		 <#else> 
			  			class="noAttachFile"
			  			title="请上传附件"
		   			 </#if>
		  		>&nbsp;
		  		</div>
		  		<#assign dispOrderNo=sup.dispOrderNo/>
		 		<div title="${sup.supName}" class="partHide ellipsisDiv_full">		 		
			 		<#if visableFlg==1>
			 			${sup.supName}
			 		<#else>
			 			${'${dispOrderNo}'?left_pad(3, "00")}
			 		</#if>		 		
		 		</div>
		 	</td>
			</#list>
		 </tr>
		 <tr>
			 <#list bidSupList as sup>
			 <td style="width:${fieldWidth1}px;text-align:center;border-bottom:0px;"><span>得分</span></td>
			 <td style="width:${fieldWidth2}px;text-align:center;border-bottom:0px;"><span>情况说明</span></td>
			 </#list>
		 </tr>
		</#if>
		</table>
		</div>
	</th>
</tr>
<tr>
	<!-- 左下 -->
	<td valign="top" style="border-bottom:none;background-color: #eee;border:0px;width:360px;">
		 <div id="matrixDownLeftDiv"  >
		 <table id="matrixDownLeftTable" class="freemarkTable" id="itemTable" style="border-right:0px;">
		 	<col width="90px"/>
		 	<col width="30px"/>
		 	<col width="240px"/>
			<#assign i=1 />
		 	<#list page.result as item>
		   	<tr class="techItemRow_${item.bidTechItemId}"  height="14">
		     	<th class="module" >${item.moduleName}</th>
		     	<th class="itemName" style="border-right:0px;">${i}</th>
			 	<th class="itemName"  style="border-right:0px;">${item.itemName}</th>
			</tr>
			<#assign i=i+1 />
			</#list>
			<tr>
			 	<th colspan="3" class="itemName totalRankTitle"  style="border-right:0px;">
			   	 	综合排名&nbsp;
			 	</th>
			</tr>
		 </table>
		 </div>
	</td>
	<!-- 右下 -->
	<td valign="top">
		<div id="matrixDownRightDiv" onscroll="resetMaxtricLayout($(this));" style="border:0px;height:372;">
	   	<table class="freemarkTable" id="rightDownTable" style="width:${rightWidth}px;height:372;border-top:0px;">
			<#list page.result as item>
			<tr class="techItemRow_${item.bidTechItemId}" height="14">
			   	<#list item.getBidItemSupRels() as rel>
			 	<#assign i=0 />
			 	<#if rel.bidSup.typeCd==1>
				    <td style="width: ${fieldWidth1}px;height:25px;" title="${item.moduleName}/${item.itemName}[ 得分 ]" class="tdHeight">
				    	<input style="margin-top:0px;text-align:center;margin-bottom:0px;" type="text" limit="${item.itemName}" class="scoreInput scor textStyle" name="score" oldval="${rel.score}" alt="amount" value="${rel.score}" onblur="updateSupScore($(this),'${rel.bidItemSupRelId}');"/>
				    </td>
				    <td style="width: ${fieldWidth2}px;height:25px;" title="${item.moduleName}/${item.itemName}[ 情况说明 ]"  class="tdHeight">
				    	<input  style="margin-top:0px;text-align:center;margin-bottom:0px;" type="text" class="scoreDesc desc textStyle" oldval="${rel.contentDesc}" name="contentDesc" value="${rel.contentDesc}" onblur="updateSupScore($(this),'${rel.bidItemSupRelId}');"/>
				    </td>
			    </#if>
			   	</#list>
			   	<#assign i=i+1 />
			</tr>
			</#list>
			
			<#if (bidSupList.size() > 0)>
			<tr>
			   <td colspan="${bidSupList.size()*2}" height="49">
			   		<textarea type="text" class="totalRankDesc textStyle" onchange="bidLedgerSave($(this),'${bidLedgerId}');" style="height:49px !important;">${totalRankDesc}</textarea>
			   </td>
			</tr>
			</#if>
	   </table>
	   </div>
	</td>
</tr>
</table>
</div>

<script language="javascript">
	$(function(){
		var freemarkWidth = $('#freemarkPanel').width();
		var realRightWidth = freemarkWidth - ${leftWidth} - 20;
		//alert(realRightWidth);
		$('#matrixUpRightDiv').width(realRightWidth);
		$('#matrixDownRightDiv').width(realRightWidth);
		
		//给滚动条设置预留高度
		if(realRightWidth < ${rightWidth}){
			//$('#matrixDownRightDiv').height($('#matrixDownRightDiv').height()+17);
			//$('#freemarkPanel').height($('#freemarkPanel').height()+17);
		}
		//判断IE
		if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){		
		$('td.tdHeight').each(function(){$(this).css('height','24px');});
		}
		
	});
	
	function resetMaxtricLayout(jDom){
		
		var t = parseInt(jDom.scrollTop());
		var l = parseInt(jDom.scrollLeft());
		
		//$("#matrixDownLeftDiv").css("margin-top", -t + "px");
		$("#matrixUpRightTable").css("margin-left", -l + "px");
	}
</script>