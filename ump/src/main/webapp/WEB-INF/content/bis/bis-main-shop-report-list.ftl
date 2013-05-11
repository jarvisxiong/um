<#macro replace value >
	<#assign len=value?length>
	<#if len gt 1 >
		<#if len gt 17 && value[0..17] =="<font color='red'>" >
			<font color='red'> ${value[18..]?replace('\n','<br>')?replace(' ','&nbsp;')?replace('"','&quot;')?replace('&nbsp;<&nbsp;','&lt;&nbsp;')?replace('&nbsp;>&nbsp;','&nbsp;&gt;')}
		<#else>
			${value?replace('\n','<br>')?replace(' ','&nbsp;')?replace('"','&quot;')?replace('&nbsp;<&nbsp;','&lt;&nbsp;')?replace('&nbsp;>&nbsp;','&nbsp;&gt;')}
		</#if>
	<#else>
		${value}
	</#if>
</#macro>
<#macro replaceNoStyle value >
		${helper.clearHtml(value)}
</#macro>
<#if projects.isEmpty()>
	<div class="promptDiv" > 你还没有添加本期的工作周报！</div>
<script type="text/javascript">
 	status = -1;
 </script>
<#else>
<table  class="report" border="0" cellpadding="0" cellspacing="0" >
 	<colgroup>
        <col width="9%"><col width="11%"><col width="11%"> <col width="11%"><col width="11%"><col width="11%"> <col width="11%"><col width="11%"><col width="11%">
    </colgroup>
    <tr>
		<th colspan="2">项目\业态</th><th>超市</th><th>百货</th><th>影院</th><th>电玩</th><th>KTV</th><th>电器</th><th class="borderRight">其他</th>
	</tr>
	<#list projects.keySet() as m>
		<#if m=="0000">
			<#assign tdhtml="<td class='beijing' rowspan='${projects.get(m).size()}'>老项目</td>" >
		<#else>
			<#assign tdhtml="<td class='beijing'  rowspan='${projects.get(m).size()}'>${m}开业</td>" >
		</#if>
		<#list projects.get(m) as p>
			<#assign shopReports=mainShopReports.get(p.bisProjectId)>
        <#if mainShopReports.get(p.bisProjectId)?exists>


			<tr class="editReport">
				${tdhtml}
				<td class="beijing pd-chill-tip" pId=${p.bisProjectId} rId="${shopReports.bisMainShopReprotId?if_exists}" style="text-align: left;" width="200px" title="${p.city}"><nobr>${p.city}</nobr></td>
				<#if shopReports?exists>
                    <td pId="${p.bisProjectId}" rId="${shopReports.bisMainShopReprotId?if_exists}" class="pd-chill-tip" title="<@replaceNoStyle  value='${shopReports.superMarket}'/>"><@replace value="${shopReports.superMarket}"/></td>
                    <td pId="${p.bisProjectId}" rId="${shopReports.bisMainShopReprotId?if_exists}" class="pd-chill-tip" title="<@replaceNoStyle  value='${shopReports.departmentStore}'/>"><@replace value="${shopReports.departmentStore}"/></td>
                    <td pId="${p.bisProjectId}" rId="${shopReports.bisMainShopReprotId?if_exists}" class="pd-chill-tip" title="<@replaceNoStyle  value='${shopReports.cinema}'/>"><@replace value="${shopReports.cinema}"/></td>
                    <td pId="${p.bisProjectId}" rId="${shopReports.bisMainShopReprotId?if_exists}" class="pd-chill-tip" title="<@replaceNoStyle  value='${shopReports.videoGames}'/>"><@replace value="${shopReports.videoGames}"/></td>
                    <td pId="${p.bisProjectId}" rId="${shopReports.bisMainShopReprotId?if_exists}" class="pd-chill-tip" title="<@replaceNoStyle  value='${shopReports.ktv}'/>"><@replace value="${shopReports.ktv}"/></td>
                    <td pId="${p.bisProjectId}" rId="${shopReports.bisMainShopReprotId?if_exists}" class="pd-chill-tip" title="<@replaceNoStyle  value='${shopReports.electricEquipment}'/>"><@replace value="${shopReports.electricEquipment}"/></td>
                    <td pId="${p.bisProjectId}" rId="${shopReports.bisMainShopReprotId?if_exists}" class="borderRight pd-chill-tip"  title="<@replaceNoStyle  value='${shopReports.otherDesc}'/>"><@replace value="${shopReports.otherDesc}"/></td>
              	</#if>
			</tr>
			<script type="text/javascript">
					status = ${shopReports.statusFlg?if_exists};
			</script>
			<#assign tdhtml="" >
        </#if>
		</#list>
	</#list>
</table>
<script type="text/javascript">
    $(function () {
       $.ajaxSetup({cache:false});
   		if(modifyAble){
        $(".pd-chill-tip").click(function(){
      	 
        	 var pId=$(this).attr("pId");
        	 var id=$(this).attr("rId");
        	 if(!(pId && id)) return ;
        	if(!affirmBut){
        	    ymPrompt.alert({
                    message:"<p style='margin-top:-25px;display:block;line-height: 20px;'>本期招商周报"+statusTxt+",不能再修改！</p>", width:350, height:150, winPos:[($("body").width()-350)/2,60], title:"消息提示！", closeBtn:true
                });
        		return;
        	}
       		var okTxt="保存",title="填写工作周报";
        
        	 ymPrompt.confirmInfo({msgCls:'',
        	 	icoCls : "",
				autoClose:false,
				message : "<div id='selectTypeDiv' ><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
				width : 600,
				height : 480,
                winPos:[($("body").width()-690)/2,60],
                title:title,
                closeBtn:true,
                okTxt:okTxt,
                cancelTxt:"取消",
                handler:function (btn) {
                    if (btn == 'ok') {
					  var saveUrl=$("#saveReport").attr('action');
                  	  var saveParam=$.map($("#saveReport").serializeArray(),function(val){
                  			//alert($("#saveReport input[id="+val.name+"]").val()+"===="+val.name);
                  			val.value=val.value.replace('>',' > ').replace('<',' < ');
              			if($("#saveReport input[id="+val.name+"]").val()==="1"){
              			  	 val.value="<font color='red'>"+val.value+"</font>"
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
					$.post('bis-main-shop-report!input.action', {'id':id,'pId':pId},
						function(result) {
							$("#selectTypeDiv").html(result);
						
					});
				}
            });
        });
       }
    });
</script>
</#if>
	