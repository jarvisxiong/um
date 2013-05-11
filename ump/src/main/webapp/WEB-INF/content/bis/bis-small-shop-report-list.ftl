<#macro show value >
	<#if value?length <1 >
	<#else>
	${value} %
	</#if>
</#macro>
<#macro mtwo value >
	<#if value?length <1 >
	<#else>
	${value} ㎡
	</#if>
</#macro>
<#if voProjects.isEmpty()>
	<div class="promptDiv" > 你还没有添加本期的小商铺周报！</div>
<script type="text/javascript">
 	status = -1;
 </script>
<#else>
<table id="smallReportTableId" class="report" border="0" cellpadding="0" cellspacing="0" >
	<colgroup>
        <col width="10%"><col width="10%"><col width="7%"><col width="7%"><col width="7%"><col width="7%"><col width="7%"><col width="7%"><col width="7%"><col width="7%"><col width="7%"><col width="7%"><col width="7%">
    </colgroup>
    <tr>
		<th>项目分类</th>
		<th>项目名称</th>
		<th>2012年计划招商区域</th>
		<th>该区域面积(㎡)</th>
		<th class="pd-chill-tip" title="合同双方盖章并且全额交齐合同保证金">该区域本周新增合同签约面积(㎡)</th>
		<th class="pd-chill-tip" title="合同双方盖章并且全额交齐合同保证金">该区域合同签约总面积(㎡)</th>
		<th class="pd-chill-tip" title="合同双方盖章并且全额交齐合同保证金">该区域签约率(%)</th>
		<th>本月计划签约率(%)</th>
		<th>小商铺开业率(%)</th>
		<th>全场(含大店)开业率(%)</th>
		<th>已收对方盖章合同即将完成招商面积(㎡)</th>
		<th>备注</th>
		<th>商业公司第一负责人签字确认</th>
	</tr>
  <#list voProjects as rpt>
		<#if rpt?exists>
			<tr class="editReport" pId="${rpt.bisProjectId}" cId="${rpt.bisItemCategoryId}" rId="${rpt.bisSmallShopReportId}">
			  <td class="beijing " title="${rpt.bisItemCategoryName}"><nobr>${rpt.bisItemCategoryName}</nobr></td>
			  <td align="left" class="pd-chill-tip" title="${rpt.projectName}">${rpt.city}</td>
			  <td align="left" class="pd-chill-tip" title="${rpt.merchantsArea2012}">${rpt.merchantsArea2012}</td>
			  <td align="center" class="pd-chill-tip" title="${rpt.merchantsArea}">${rpt.merchantsArea}</td>
			  <td align="center" class="pd-chill-tip" title="${rpt.contractAreaNew}">${rpt.contractAreaNew}</td>
			  <td align="center" class="pd-chill-tip" title="${rpt.contractArea}">${rpt.contractArea}</td>
			  <td align="center" class="pd-chill-tip" title="${rpt.signingRate}">${rpt.signingRate}</td>
			  <td align="center" class="pd-chill-tip" title="${rpt.planSigningRate}">${rpt.planSigningRate}</td>
			  <td align="center" class="pd-chill-tip" title="${rpt.openedRateShop}">${rpt.openedRateShop}</td>
			  <td align="center" class="pd-chill-tip" title="${rpt.openedRate}">${rpt.openedRate}</td>
			  <td align="center" class="pd-chill-tip" title="${rpt.investmentArea}">${rpt.investmentArea}</td>
			  <td align="center" class="pd-chill-tip" title="${rpt.remark}">${rpt.remark}</td>
			  <td align="center" class="pd-chill-tip" title="${rpt.headerSign}">${rpt.headerSign}</td>
			</tr>
		</#if>
          <script type="text/javascript">
              status = ${rpt.smallShopStatus?if_exists};
          </script>
    </#list>
</table>
<script type="text/javascript">
    $(document).ready(function () {
       $.ajaxSetup({cache:false});
       /**判断是否有权限点开修改*/
       if(modifyAble){
        $(".pd-chill-tip").click(function(){
        	var pId=$(this).parent().attr("pId");
        	var rId=$(this).parent().attr("rId");
        	var cId=$(this).parent().attr("cId");
        	if(!(pId && rId && cId)) return ;
        	if(!affirmBut){
        	    ymPrompt.alert({
                    message:"<p style='margin-top:-25px;display:block;line-height: 20px;'>本期小商铺周报"+statusTxt+",不能再修改！</p>", width:350, height:150, winPos:[($("body").width()-350)/2,60], title:"消息提示！", closeBtn:true
                });
        		return;
        	}
       		var okTxt="保存",title="填写小商铺周报";
        
        	 ymPrompt.confirmInfo({msgCls:'',
        	 	icoCls : "",
				autoClose:false,
				message : "<div id='selectTypeDiv' ><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
				width : 600,
				height : 480,
                winPos:[($("body").width()-500)/2,60],
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
					$.post('bis-small-shop-report!input.action', {'reportId':rId,'proId':pId,'cId':cId},
						function(result) {
							$("#selectTypeDiv").html(result);
					});
				}
            });
        });
       }
       /**合并表格*/
   		$('#smallReportTableId').mergeCell({cols: [0, 1]}); 		
    });
</script>
</#if>
