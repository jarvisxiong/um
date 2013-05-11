<#assign security=JspTaglibs["/WEB-INF/security.tld"]>
<#macro replace value >
	<#assign len=value?length>
	<#if len gt 1 >
		<#if len gt 17 && value[0..17] =="<font color='red'>" >
			<font color='red'> ${helper.clearHtml(value)}</font>
		<#else>
			${helper.clearHtml(value)}
		</#if>
	<#else>
		${value}
	</#if>
</#macro>
<#macro modival value="1">
	<#local val1=helper.clearHtml(value!"")?trim/>
	<#if (val1 == "")>
		/
	<#else>
		${value}%
	</#if>
</#macro>
<#if rateProjects.isEmpty()>
	<div class="promptDiv" > 你还没有添加本周的租费收缴信息！</div>
<script type="text/javascript">
 	status = -1;
 </script>
<#else>
<table  class="report" border="0" cellpadding="0" cellspacing="0">
 	<colgroup>
        <col width="9%"><col width="18%"><col width="18%"> <col width="18%"><col width="18%"><col width="20%">
    </colgroup>
    <tr>
    	<th rowspan="2">商业公司</th><th colspan="2">租金</th><th colspan="2">物管费</th><th rowspan="2">备注</th>
    </tr>
	<tr>
		<th>累计应收额(万元)</th><th>累计收缴率(%)</th><th>累计应收额(万元)</th><th>累计收缴率(%)</th>
	</tr>
	
	<#list rateProjects.keySet() as m>
		<#list rateProjects.get(m) as p>
			<#assign rentalRates=rentalCollectionRates.get(p.bisProjectId)>
        <#if rentalCollectionRates.get(p.bisProjectId)?exists>
			<tr class="editReport">
				${tdhtml}
				<td class="beijing pd-chill-tip" pId=${p.bisProjectId} rId="${rentalRates.bisRentalCollectionRatId?if_exists}" align="center" width="200px" title="${p.city}"><nobr>${p.city}</nobr></td>
				<#if rentalRates?exists>
                    <td align="right" pId="${p.bisProjectId}" rId="${rentalRates.bisRentalCollectionRatId?if_exists}" class="pd-chill-tip" title="<@replace  value='累计应收额(万元)：${rentalRates.cumulativeRecAmountRent}'/>"><div style="width:5px; height:36px;float:left; margin-left:-3px; margin-top:-4px; margin-bottom:-4px;"></div><@replace value="${rentalRates.cumulativeRecAmountRent}"/><input type="hidden" class="ColRateRentForCalc1" value="${rentalRates.cumulativeColRateRent}" /></td>
                    <td align="right" pId="${p.bisProjectId}" rId="${rentalRates.bisRentalCollectionRatId?if_exists}" class="pd-chill-tip" title="<@replace value='累计实收额(万元)：${rentalRates.cumulativeColRateRent}'/>"><div style="width:5px; height:36px;float:left; margin-left:-3px; margin-top:-4px; margin-bottom:-4px;"></div><@modival value="${rentalRates.rentalCollPointRent}" /><input type="hidden" class="CollPointRent" value="${helper.clearHtml(rentalRates.rentalCollPointRent!"")}" /></td>
                    <td align="right" pId="${p.bisProjectId}" rId="${rentalRates.bisRentalCollectionRatId?if_exists}" class="pd-chill-tip" title="<@replace  value='累计应收额(万元)：${rentalRates.cumulativeRecAmountProperty}'/>"><div style="width:5px; height:36px;float:left; margin-left:-3px; margin-top:-4px; margin-bottom:-4px;"></div><@replace value="${rentalRates.cumulativeRecAmountProperty}"/><input type="hidden" class="ColRateRentForCalc2" value="${rentalRates.cumulativeColRateProperty}" /></td>
                    <td align="right" pId="${p.bisProjectId}" rId="${rentalRates.bisRentalCollectionRatId?if_exists}" class="pd-chill-tip" title="<@replace value='累计实收额(万元)：${rentalRates.cumulativeColRateProperty}'/>"><div style="width:5px; height:36px;float:left; margin-left:-3px; margin-top:-4px; margin-bottom:-4px;"></div><@modival value="${rentalRates.rentalCollPointProperty}" /><input type="hidden" class="CollPointProperty" value="${helper.clearHtml(rentalRates.rentalCollPointProperty!"")}" /></td>
                    <td pId="${p.bisProjectId}" rId="${rentalRates.bisRentalCollectionRatId?if_exists}" class="pd-chill-tip" title="<@replace  value='${rentalRates.remark}'/>"><@replace value="${rentalRates.remark}"/></td>
              	</#if>
			</tr>
			<script type="text/javascript">
					status = ${rentalRates.statusFlg?if_exists};
			</script>
			<#assign tdhtml="" >
        </#if>
		</#list>
	</#list>
		<tr>
			<th class="beijing pd-chill-tip" align="center" width="200px">合计</th>
			<td align="right">&nbsp;</td>
			<td align="right">&nbsp;</td>
			<td align="right">&nbsp;</td>
			<td align="right">&nbsp;</td>
			<td align="right">&nbsp;</td>
			<td align="right">&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
</table>
<script type="text/javascript">
    $(function () {
       $.ajaxSetup({cache:false});
	    <@security.authorize ifAnyGranted="A_RENTAL_C_RATE_NEW,A_RENTAL_C_RATE_SUB,A_RENTAL_C_RATE_CFM,A_RENTAL_C_RATE_REF">
	       initClicks();
		</@security.authorize>
		
		
    });
    //初始化单击事件
    function initClicks(){
       $(".pd-chill-tip").click(function(){
        	 var pId=$(this).attr("pId");
        	 var id=$(this).attr("rId");
        	 if(!(pId && id)) return ;
        	if(!affirmBut){
        	    ymPrompt.alert({
                    message:"<p style='margin-top:-25px;display:block;line-height: 20px;'>本期费率收缴"+statusTxt+",不能再修改！</p>", width:350, height:150, title:"消息提示！",winPos:[($("body").width()-350)/2,50], closeBtn:true
                });
        		return;
        	}
       		var okTxt="保存",title="填写租费收缴";
        
        	 ymPrompt.confirmInfo({msgCls:'',
        	 	icoCls : "",
				autoClose:false,
				message : "<div id='selectTypeDiv' ><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
				width : 690,
				height : 400,
				winPos:[($("body").width()-690)/2,5],
                title:title,
                closeBtn:true,
                okTxt:okTxt,
                cancelTxt:"取消",
                handler:function (btn) {
                    if (btn == 'ok') {
					  var saveUrl=$("#saveReport").attr('action');
                  	  var saveParam=$.map($("#saveReport").serializeArray(),function(val){
                  			val.value=val.value.replace('>',' > ').replace('<',' < ');
          				if($("#saveReport input[id="+val.name+"]").val()==="1"){
              			  	 val.value="<font color='red'>"+val.value+"</font>"
              			}
          				if($("#saveReport input[id="+val.name+"]").val()==="2"){
              			  	 val.value="<font class='tred'>"+val.value+"</font>"
              			}
          				if($("#saveReport input[id="+val.name+"]").val()==="3"){
              			  	 val.value="<font class='tyellow'>"+val.value+"</font>"
              			}
          				if($("#saveReport input[id="+val.name+"]").val()==="4"){
              			  	 val.value="<font class='tgreen'>"+val.value+"</font>"
              			}
          				if($("#saveReport input[id="+val.name+"]").val()==="5"){
              			  	 val.value="<font class='text'>"+val.value+"</font>"
              			}
						  return val;
					  });
					  $.post(saveUrl,eval(saveParam), function(result) {
					  	if(result==="true"){
					 		loadBody();
					  	}
					  });

                    }
					ymPrompt.close();
                },
                afterShow : function() {
					$.post('bis-rental-collection-rate!input.action', {'id':id,'pId':pId},
						function(result) {
							$("#selectTypeDiv").html(result);
					});
				}
            });
        });
    }
</script>
</#if>
	