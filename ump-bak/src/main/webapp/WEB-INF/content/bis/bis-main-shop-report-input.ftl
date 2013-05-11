<#macro print key  value >
	<#assign len=value?length>
	<#if len gt 17 && value[0..17] =="<font color='red'>" >
		<td><textarea onblur="$(this).val($(this).val().substr(0,80));" name="mainShopReport.${key}" style='color:red' maxlength="80" cols="55" rows="2" >${value[18..]?replace('</font>','')?replace(' > ','>')?replace(' < ','<')}</textarea></td>
		<td class="checkbox" >
			<button id="${key}" class="prompt red"  type="button">取消更新</button>
			<input type="hidden" id="mainShopReport.${key}" value="1" />
		 </td>
		
	<#else>
		<td><textarea onblur="$(this).val($(this).val().substr(0,80));"  name="mainShopReport.${key}" maxlength="80" cols="55" rows="2" >${value?replace(' > ','>')?replace(' < ','<')}</textarea></td>
		<td class="checkbox" >
			<button id="${key}" class="prompt green"  type="button">更新</button>
			<input type="hidden" id="mainShopReport.${key}" value="0" />
		 </td>
	</#if>
</#macro>  
<form action="bis-main-shop-report!save.action"  id="saveReport" method="post" >
<br/>
  <input type="hidden" name="mainShopReport.bisMainShopReprotId" value="${mainShopReport.bisMainShopReprotId?if_exists}"/>
  <input type="hidden" name="mainShopReport.bisProjectId" value="${mainShopReport.bisProjectId?if_exists}"/>
 <table>
 	  <tr >
	      <td width="100" style="text-align:center;" >项目：</td>
	   	  <td  ><div class="titleDiv" >
	   	  	<#list projects.keySet() as m> 
	   	  		<#list projects.get(m) as p> 
	   	  			<#if p.bisProjectId==mainShopReport.bisProjectId>
	   	  				${p.projectName}
	   	  			</#if>
	   	  		</#list>	
			</#list></div>
	   	  </td>
	   	  <td>&nbsp;</td>
      </tr>
 	  <tr >
	      <td width="100" style="text-align: center;">周期：</td>
	   	  <td > 
	   	 	<div class="titleDiv">${mainShopReport.startDay?if_exists}&nbsp;&nbsp;至&nbsp;&nbsp;${mainShopReport.endDay?if_exists}</div>
	   	  </td>
	   	  <td>&nbsp;</td>
      </tr>
      <tr>
	      <td width="100" style="text-align: center;">超市：</td>
	      <@print key="superMarket" value="${mainShopReport.superMarket?if_exists}"/> 
      </tr>
      <tr>
	      <td style="text-align: center;">百货：</td>
	      
	      <@print key="departmentStore" value="${mainShopReport.departmentStore?if_exists}"/> 
      </tr>
      <tr>
	      <td style="text-align: center;">影院：</td>
	      <@print key="cinema" value="${mainShopReport.cinema?if_exists}"/> 
      </tr>
      <tr>
	      <td style="text-align: center;">电玩：</td>
	      <@print key="videoGames" value="${mainShopReport.videoGames?if_exists}"/> 
      </tr>
      <tr>
	      <td style="text-align: center;">KTV：</td>
	      <@print key="ktv" value="${mainShopReport.ktv?if_exists}"/> 
      </tr>
      <tr>
	      <td style="text-align: center;">电器：</td>
	      <@print key="electricEquipment" value="${mainShopReport.electricEquipment?if_exists}"/> 
      </tr>
      <tr>
	      <td style="text-align: center;">其他：</td>
	      <@print key="otherDesc" value="${mainShopReport.otherDesc?if_exists}"/> 
      </tr>
 </table>
 </form>
 <script type="text/javascript">
    	$(document).ready(function(){
    		  $(".prompt").click(function(){
				  var flg =$(this).hasClass('red');
				  var tname="mainShopReport."+this.id;
				  if(flg){
					 	$(this).removeClass('red').addClass('green');
					 	$(this).text("更新");
					 	$("#saveReport textarea[name="+tname+"]").attr("style","color:#000");
					 	$("#saveReport input[id="+tname+"]").val("0");
				  }else{
						$(this).removeClass('gray').addClass('red');
						$(this).text("取消更新");
					 	$("#saveReport textarea[name="+tname+"]").attr("style","color:red");
					 	$("#saveReport input[id="+tname+"]").val("1");
				  }
    		  });
    		 	
    	});
</script>

	