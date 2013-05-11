<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 工程设计结算审批表 --%>

<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	<c:set var="isSy">false</c:set>
	<c:if test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
	<c:set var="isSy">true</c:set>
	</c:if>
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
	</s:set>
	
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="120"/>
			<col/>
			<col width="135"/>
			<col/>
			
			<tr>
				<td class="td_title">合同编号</td>
				<td align="left">
				<input type="hidden" id="contLedgerId"  name="templateBean.contLedgerId" value="${templateBean.contLedgerId}" />
			
				<s:if test="!templateBean.contLedgerId.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getCont('${templateBean.contLedgerId}');">${templateBean.contNo}</span>
						<input id="contNo" type="hidden" name="templateBean.contNo" value="${templateBean.contNo}" />
					</s:if>
					<s:else>
					<input id="contNo" class="inputBorder" validate="required" type="text" name="templateBean.contNo" value="${templateBean.contNo}"/>
					</s:else>
				</td>
				
				<td class="td_title">编号</td>
				<td >
				<input id="seriNo" class="inputBorder"  readonly="" validate="required" type="text" name="templateBean.seriNo" value="${templateBean.seriNo}" />
					</td>
				
			</tr>
		<tr>
		<td class="td_title">合同名称</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="contName" name="templateBean.contName" value="${templateBean.contName}" />
				</td>
				
				<td class="td_title">乙方</td>
				<td >
				<input class="inputBorder" readonly="readonly" type="text" id="partB" name="templateBean.partB" value="${templateBean.partB}" />
				</td>
	    </tr>
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="3">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col/>
						<col width="20"/>
						<col width="40"/>
						<col width="20"/>
						<tr>
						<td>
						
						<input class="inputBorder" readonly="readonly" type="text" id="projectName" name="templateBean.projectName" value="${templateBean.projectName}" />
						<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
						</td>
						<td style="text-align: right;">(</td>
						<td>
						<input class="inputBorder" type="text" name="templateBean.projectPeriod" value="${templateBean.projectPeriod}" />
						</td>
						<td>)期</td>
						</tr>
					</table>
				</td>				
			</tr>
			
			
				<tr>
				<td class="td_title">原合同总价</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text"  onblur="formatVal($(this));"  id="totalPrice" name="templateBean.totalPrice" value="${templateBean.totalPrice}" />
				</td>
				<td class="td_title">合同性质</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="contProperty" name="templateBean.contProperty" value="${templateBean.contProperty}" />
				</td>
			</tr>
			
	<tr>
				<td class="td_title">乙方报审金额（元）</td>
				<td>
				<input class="inputBorder" validate="required" type="text" id="partBReviewAmount" onblur="formatVal($(this));"  name="templateBean.partBReviewAmount" value="${templateBean.partBReviewAmount}" />
				</td>
				<td class="td_title">地产公司审核费用（元）</td>
				<td>
				<input class="inputBorder" type="text"  validate="required"id="landEstateCorpApproCost"  onblur="formatVal($(this));"  name="templateBean.landEstateCorpApproCost" value="${templateBean.landEstateCorpApproCost}" />
				</td>
			</tr>

			<tr>
		
			<td class="td_title">设计原因引起的变更造价</td>	
			<td colspan="3">
			<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder" ><tr>
		
				<td>
				<input class="inputBorder"  validate="required" otype="input" type="text"  onblur="formatVal($(this));"  id="designChangeCost" name="templateBean.designChangeCost" value="${templateBean.designChangeCost}" />
				</td>
				<td class="td_title">对应工程造价</td>
				<td>
				<input class="inputBorder"  validate="required" otype="input" type="text"  onblur="formatVal($(this));"  id="projectCost" name="templateBean.projectCost" value="${templateBean.projectCost}" />
				</td>
				
				<td class="td_title">设计变更率</td>
				<td>
				<input class="inputBorder"  type="text" readonly="" id="designChangeRate"  onblur="formatVal($(this));"  name="templateBean.designChangeRate" value="${templateBean.designChangeRate}" />
				</td>
				
				</tr></table></td>
			</tr>
		
			<tr>
				<td class="td_title">违约扣款</td>
				<td colspan="3">				
				<input class="inputBorder" type="text"  validate="required" id="defaultDeductCost"  onblur="formatVal($(this));"  name="templateBean.defaultDeductCost" value="${templateBean.defaultDeductCost}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">设计成果验收、现场服务说明</td>
				<td colspan="3">
				
					<textarea rows="5" cols="10" class="inputBorder" validate="required"  name="templateBean.designResultCheck" >${templateBean.designResultCheck}</textarea>
		

				</td>
				
			</tr>
			<tr>
			<td colspan="4">附件上传</td>
			</tr>
			<tr>
		
				<td align="left" style="height:40px;" validate="required" value="${templateBean.designResultApproveId}">设计成果审批表</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('设计成果审批表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','designResultApproveId',event);"/>
				<input type="hidden" name="templateBean.designResultApproveId" id="designResultApproveId" value="${templateBean.designResultApproveId}"/>
				</s:if>
				</td>
				<td colspan="2">
				<div id="show_designResultApproveId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.designResultApproveId}','designResultApproveId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td align="left" style="height:40px;"  validate="required" value="${templateBean.designChangeTotalId}">设计变更统计表</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('设计变更统计表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','designChangeTotalId',event);"/>
				<input type="hidden" name="templateBean.designChangeTotalId" id="designChangeTotalId" value="${templateBean.designChangeTotalId}"/>
				</s:if>
				</td>
				<td colspan="2">
				<div id="show_designChangeTotalId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.designChangeTotalId}','designChangeTotalId','${canEdit}');
				</script>
				</td>
			</tr>
			<s:if test="statusCd==2">
			<tr>
				<td class="td_title">集团核定费用（元）</td>
				<td >
				<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' validate="required"</s:if> type="text" id="settlementPrice" name="templateBean.settlementPrice" value="${templateBean.settlementPrice}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">其中含甲供料价格（元） </td>
				<td>
				<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' validate="required"</s:if> type="text" id=clearPriceParta name="templateBean.clearPriceParta" value="${templateBean.clearPriceParta}" onblur="formatVal($(this));"/>
			
				</td>
			
			</tr>
			</s:if>
		
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
function getCont(id){
	var url="${ctx}/cont/cont-ledger!input.action?id="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cont-ledger-input","合同台账查看",url,true);
	}
	var trApprover=$("#trConItem").clone();
	$("#trConItem").remove();
	var cloneCount = 0;
	
	function doTotalRate(dom){
		if($("#payMoney").val()!=0){
			var totalPrice = formatFloat($("#totalPrice").val());
			$("#tatalRate").val(((getValByJ($(dom))/totalPrice)*100).toFixed(2));
		}
	}
</script>

<s:if test="#canEdit=='true'">
<script type="text/javascript">

	var mapPrama={
		contLedgerId:'contLedgerId',
		contNo:'contNo',
		contName:'contName',
		projectCd:'projectCd',
		projectName:'projectName',
		partB:'partB',
		totalPrice:'totalPrice',
		contProperty:'contProperty'
	
	};

</script>
<s:if test="authTypeCd=='BLSY_CBGL_HTJS_10' || authTypeCd=='BLSY_CBGL_HTJS_30' || authTypeCd=='BLSY_CBGL_HTJS_40' 
|| authTypeCd=='SYGS_CBGL_HTJS_10'|| authTypeCd=='SYGS_CBGL_HTJS_30'|| authTypeCd=='SYGS_CBGL_HTJS_40'
">
<script type="text/javascript">

$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama,{codeType:'2'},function(e){

});


</script>
</s:if>
<s:else>
<script type="text/javascript">

try{
    (1).toFixed(1);   //   ie5不支持此方法
}
catch(e)   {
    Number.prototype.toFixed   =   function(dot)   {   //   所以要在这里定义
        with(Math){
            var   m=pow(10,Number(dot));
            var   s   =   (round(this*m)/m).toString();
        }
        if(s.indexOf( '. ')   <   0)
              s   +=   ". ";
        s   +=   "000000000000 ";
        return   s.substr(0,s.indexOf( '. ')+dot+1)+ "a ";
    }
}




	$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama,{},function(e){
	
		var $s = $(e);
		if($s.attr("contStatus")=="0"){
			 alert("只有'已完未结'合同状态才可以发起!");
			 clearRelationVal();
			autoBuildSerNo();
			$("#contNo").focus();
             return;
			}else if($s.attr("contStatus")=="2"){
				alert("当前合同已处于'已完已结' 状态，不需要结算！");
				clearRelationVal();						
				autoBuildSerNo();
				$("#contNo").focus();
				return;
			}
		
	});
	function clearRelationVal(){
		$("#contName").val("");
		$("#contNo").val("");	
		$("#projectName").val("");
			$("#projectCd").val("");
			$("#partB").val("");
			$("#totalPrice").val("");
			$("#contProperty").val("");
		}
	$("#contNo").bind("change",function(){
		window.setTimeout("autoBuildSerNo();",500);
		});
	$("input[otype=input]").change(function(){
		var designChangeCostVal=$("#designChangeCost").val().replace(/\,/ig,"");
		var projectCostVal=$("#projectCost").val().replace(/\,/ig,"");
		if($.trim(designChangeCostVal)=="" || (Number(designChangeCostVal)+"" == "NaN")){
			designChangeCostVal="0";
			}
	
		if($.trim(projectCostVal)=="" || (Number(projectCostVal)+"" == "NaN") || parseFloat(projectCostVal)== 0 ){
			designChangeCostVal="0";
			 projectCostVal="1";
			}   
			var rate=parseFloat(designChangeCostVal)/parseFloat(projectCostVal);
			if(rate<1 && rate>0){
				rate=(rate).toFixed(2);
				}
		   $("#designChangeRate").val(rate);
		});
	function autoBuildSerNo(dom){
		if($("#contName").val()==""){
			$("#contNo").val("");
			}
		if($("#contNo").val()==""){
			 $("#seriNo").val("");
			}else{
               $("#seriNo").val($("#contNo").val()+"-JS");
			}
		}

</script>
</s:else>
</s:if>