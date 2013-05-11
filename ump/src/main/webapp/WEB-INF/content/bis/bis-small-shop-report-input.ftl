<#macro print key  value >
	<#assign len=value?length>
	<#if len gt 17 && value[0..17] =="<font color='red'>" >
		<td><input onblur="$(this).val($(this).val().substr(0,80));" name="smallShopReport.${key}" style='color:red;' size="42" maxlength="60" value="${value[18..]?replace('</font>','')?replace(' > ','>')?replace(' < ','<')}" /></td>
		<td class="checkbox"  align="right">
			<button id="${key}" class="prompt red"  type="button">取消更新</button>
			<input type="hidden" id="smallShopReport.${key}" value="1" />
		 </td>
		
	<#else>
		<td><input onblur="$(this).val($(this).val().substr(0,80));"  name="smallShopReport.${key}" size="42" maxlength="60" value="${value?replace(' > ','>')?replace(' < ','<')}"/></td>
		<td class="checkbox"  align="right" >
			<button id="${key}" class="prompt green"  type="button">更新</button>
			<input type="hidden" id="smallShopReport.${key}" value="0" />
		 </td>
	</#if>
</#macro>
<style type="text/css">
.smallshopTable input{
	border:1px solid #999;
	height:22px;
	line-height:20px;
}

</style>
<form action="bis-small-shop-report!save.action" id="saveReport" method="post" >
<br/>
  <input type="hidden" name="smallShopReport.bisSmallShopReportId" value="${smallShopReport.bisSmallShopReportId?if_exists}"/>
  <input type="hidden" name="smallShopReport.bisProjectId" value="${smallShopReport.bisProjectId?if_exists}"/>
  <input type="hidden" name="smallShopReport.bisItemCategoryId" value="${smallShopReport.bisItemCategoryId?if_exists}"/>
  <input type="hidden" name="smallShopReport.recordVersion" value="${smallShopReport.recordVersion}"/>
 <table align="center" cellpadding="0" cellspacing="0" class="smallshopTable">
 	  <tr>
	      <td width="45%" height="25" align="right" style="padding-right:10px;">项目：</td>
	   	  <td width="45%"><strong>${stack.findValue("@com.hhz.ump.util.CodeNameUtil@getProjectName('${smallShopReport.bisProjectId}')")!}</strong></td>
	   	  <td width="10%">&nbsp;</td>
      </tr>
 	  <tr >
	      <td width="100" height="25" align="right"  style="padding-right:10px;">周期：</td>
	   	  <td > 
	   	 	${smallShopReport.startDay?if_exists}&nbsp;&nbsp;至&nbsp;&nbsp;${smallShopReport.endDay?if_exists}
	   	  </td>
	   	  <td>&nbsp;</td>
      </tr>
      <tr>
	      <td width="100" height="30" align="right" style="padding-right:10px;" >2012年计划招商区域：</td>
	      <@print key="merchantsArea2012" value="${smallShopReport.merchantsArea2012?if_exists}"/> 
      </tr>
      <tr>
	      <td width="100" height="30" align="right" style="padding-right:10px;" >该区域面积(㎡)：</td>
	      <@print key="merchantsArea" value="${smallShopReport.merchantsArea?if_exists}"/> 
      </tr>
      <tr>
	      <td align="right" height="30" style="padding-right:10px;">该区域本周新增合同签约面积(㎡)：</td>
	      <@print key="contractAreaNew" value="${smallShopReport.contractAreaNew?if_exists}"/> 
      </tr>
      <tr>
	      <td align="right" height="30" style="padding-right:10px;">该区域合同签约总面积(㎡)：</td>
	      <@print key="contractArea" value="${smallShopReport.contractArea?if_exists}"/> 
      </tr>
      <tr>
	      <td align="right" height="30" style="padding-right:10px;" >该区域签约率(%)：</td>
	      <@print key="signingRate" value="${smallShopReport.signingRate?if_exists}"/> 
      </tr>
      <tr>
	      <td align="right" height="30" style="padding-right:10px;" >本月计划签约率(%)：</td>
	      <@print key="planSigningRate" value="${smallShopReport.planSigningRate?if_exists}"/> 
      </tr>
      <tr>
	      <td align="right" height="30" style="padding-right:10px;" >小商铺开业率(%)：</td>
	      <@print key="openedRateShop" value="${smallShopReport.openedRateShop?if_exists}"/> 
      </tr>
      <tr>
	      <td align="right" height="30" style="padding-right:10px;" >全场(含大店)开业率(%)：</td>
	      <@print key="openedRate" value="${smallShopReport.openedRate?if_exists}"/> 
      </tr>
      <tr>
	      <td align="right" height="30" style="padding-right:10px;" >已收对方盖章合同即将完成招商面积(㎡)：</td>
	      <@print key="investmentArea" value="${smallShopReport.investmentArea?if_exists}"/> 
      </tr>
      <tr>
	      <td align="right" height="30" style="padding-right:10px;" >备注：</td>
	      <@print key="remark" value="${smallShopReport.remark?if_exists}"/> 
      </tr>
      <tr>
	      <td align="right" height="30" style="padding-right:10px;" >负责人签字：</td>
	      <@print key="headerSign" value="${smallShopReport.headerSign?if_exists}"/> 
      </tr>
 </table>
 </form>
 <script type="text/javascript">
    	$(document).ready(function(){
    		  $(".prompt").click(function(){
				  var flg =$(this).hasClass('red');
				  var tname="smallShopReport."+this.id;
				  if(flg){
					 	$(this).removeClass('red').addClass('green');
					 	$(this).text("更新");
					 	$("#saveReport :input[name="+tname+"]").attr("style","color:#000");
					 	$("#saveReport :input[id="+tname+"]").val("0");
				  }else{
						$(this).removeClass('gray').addClass('red');
						$(this).text("取消更新");
					 	$("#saveReport :input[name="+tname+"]").attr("style","color:red");
					 	$("#saveReport :input[id="+tname+"]").val("1");
				  }
    		  });
    	});
    	/**验证*/
    	function valiSmallShopReportForm(){
    		valiSmallShopField("smallShopReport.merchantsArea2012","2012招商面积");
    		valiSmallShopField("smallShopReport.merchantsArea","总招商面积");
    		valiSmallShopField("smallShopReport.contractAreaNew","新的签约面积");
    		valiSmallShopField("smallShopReport.contractArea","总签约面积");
    		valiSmallShopField("smallShopReport.signingRate","该区域签约率");
    		valiSmallShopField("smallShopReport.planSigningRate","本月计划签约率");
    		valiSmallShopField("smallShopReport.openedRateShop","小商铺开业率");
    		valiSmallShopField("smallShopReport.openedRate","总开业率");
    		valiSmallShopField("smallShopReport.investmentArea","已收对方盖章合同即将完成招商面积");
    		valiSmallShopField("smallShopReport.headerSign","负责人签字");
    	}
    	/**验证小商铺表单*/
		function valiSmallShopField(fieldName,cation){
			$("input[name='"+fieldName+"']").bind("blur",function(){
				if(isNaN($(this).val())){
					alert("请正确输入"+cation);
					$("input[name='"+fieldName+"']").val("0");
				}else{
					$(this).val(changeTwoDecimal_f($(this).val()));
				}
			});
		}
</script>

	