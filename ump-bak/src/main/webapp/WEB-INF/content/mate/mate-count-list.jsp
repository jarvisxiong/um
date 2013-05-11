<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<script type="text/javascript">
/**function creatReport(){
	var registerNumber =$("#registerNumber").val();
	$.post("${ctx}/mate/mate-count!list.action",
			{
			  contTypes:arrCheck,
		      registerNumber:  registerNumber
		    },
			function(result) {
				if (result) {
					$("#mailRight").html(result);
				}
			}
		);
}
$(function(){
	var useStatus=$('#useStatus').val();
	
	if(useStatus==1){
		alert(useStatus);
		$("button[name='delBtn']").attr('disabled',true);
	}
});
*/
function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	$("#mateDetail").ajaxSubmit(function(result) {
		$("#mateDetailList").html(result);
	});
}
function jumpPageTo() {
		var index = $("#pageTo").val();
		index = parseInt(index);
		if (index > 0) {
			jumpPage(index);
		}
}
function getDetailMate(id,basicId){
	var useStatus = $('#useStatus').val();
	var ownerMate=$('#ownerMaterialTypeName').val();
	var instalBl=$("input[type=radio][name='instalBl']:checked").val();
	var url="${ctx}/mate/mate-count!input.action?id="+id+'&mateCountBasicId='+basicId+'&useStatus='+useStatus+'&ownerMate='+ownerMate+'&instalBl='+instalBl;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("mate-count-inputs","供料详细信息",url,true);
}
function doDetailDelete(id,mateCountBasicId){
	if(id!=null){
		if(confirm("确定要删除该条记录吗？")){
			var url="${ctx}/mate/mate-count!delete.action";
			$.post(url,{id:id,mateCountBasicId:mateCountBasicId},
					function(result) {
						if('success' == result){
							
							doQueryMateDetail();
						}else{
							alert("删除失败");
							return false;
						}
					});
		}
	}
}
function updateClear(id,clearAudit){
	var url="${ctx}/mate/mate-count!updateClear.action";
	$.post(url,{id:id,clearAudit:clearAudit},
			function(result) {
				if('success' == result){
					
					doQueryMateDetail();
				}else{
					alert("取消失败");
					return false;
				}
			});
}
</script>

<div id="TableDiv"  style="height: 100%;width:100% padding-left: 5px; overflow: hidden;">
	<input type="hidden" name="mateCountBasicId" value ="${mateCountBasicId}" />
	<table width="100%" id="table1" class="content_table">
	  <thead>
	        <tr>
	        	<th style="width:26px;background: none;">序号</th>
	            <th scope="col" align="center"  class="pd-chill-tip" title='公司名称'><div class="ellipsisDiv_full" >公司名称</div></th>
	            <th scope="col" align="center"  class="pd-chill-tip" title='领货单位'><div class="ellipsisDiv_full" >领货单位</th>
	            <th scope="col" align="center"  class="pd-chill-tip" title='品名'><div class="ellipsisDiv_full" >品名</th>
	            <th scope="col" align="center"  class="pd-chill-tip" title='进退货日期'><div class="ellipsisDiv_full" >进退货日期</th>
	            <th scope="col" align="center"  class="pd-chill-tip" title='品牌'><div class="ellipsisDiv_full" >品牌</th>
	            <th scope="col" align="center"  class="pd-chill-tip" title='施工单位实际领用量'><div class="ellipsisDiv_full" >施工实际领用</th>
	            <th scope="col" align="center"  class="pd-chill-tip" title='材质'><div class="ellipsisDiv_full" >材质</th>
	            <th scope="col" align="center"  class="pd-chill-tip" title='规格'><div class="ellipsisDiv_full" >规格</th>
	            <th scope="col" align="center"  class="pd-chill-tip" title='单位'><div class="ellipsisDiv_full" >单位</th>
	            <th scope="col" align="center"  class="pd-chill-tip" title='采购合同单价'><div class="ellipsisDiv_full" >采购合同单价</th>
	            <th scope="col" align="center"  class="pd-chill-tip" title='实际供应量'><div class="ellipsisDiv_full" >实际供应量</th>
	            <th scope="col" align="center"  class="pd-chill-tip" title='库存量'><div class="ellipsisDiv_full" >库存量</th>
	            <th scope="col" align="center"  class="pd-chill-tip" title='清算'><div class="ellipsisDiv_full" >清算</th>
	          	<th scope="col" align="center"  class="pd-chill-tip" title='操作'><div class="ellipsisDiv_full" >操作</th>
			<!-- 	<td>品名</td>
				<td>品牌</td>
				<td><input type="text" id="mateBrand" name ="mateBrand" value="${mateBrand}"/></td>  
				<td>材质</td>
				<td><input type="text" id="mateChar" name ="mateChar" value="${mateChar}"/></td> 
				<td>规格</td>
				<td><input type="text" id="mateStan" name ="mateStan" value="${mateStan}"/></td>  
				<td>型号</td>
				<td><input type="text" id="mateType" name ="mateType" value="${mateType}"/></td>  
				<td>单位</td>
				<td><input type="text" id="unit" name ="unit" value="${unit}"/></td>  -->
	        </tr>
	  </thead>
	  <s:iterator value="Page.result" status="status">
	  		<tr class="mainTr">
	  			<td style="text-align: right;padding-right:5px;"><s:property value="#status.index+1"/></td>
  				<td align="center" onclick="getDetailMate('${mateCountDetailId}','${mateCountBasic.mateCountBasicId}');" class="pd-chill-tip" title='<p:code2name mapCodeName="mateDetailMap" value="projectCd" />'><div class="ellipsisDiv_full" ><p:code2name mapCodeName="mateDetailMap" value="projectCd" /></div></td>
  	    		<td align="center" onclick="getDetailMate('${mateCountDetailId}','${mateCountBasic.mateCountBasicId}');" class="pd-chill-tip" title='${useUnitName}'><div class="ellipsisDiv_full" >${useUnitName}</div></td>
  	    		<td align="center" onclick="getDetailMate('${mateCountDetailId}','${mateCountBasic.mateCountBasicId}');" class="pd-chill-tip" title='${mateTasname}'><div class="ellipsisDiv_full" >${mateTasname}</div></td>
  	    		<!--<td align="center" onclick="getDetailMate('${mateCountDetailId}','${mateCountBasic.mateCountBasicId}');" class="pd-chill-tip" title='${useSpace}'><div class="ellipsisDiv_full" >${useSpace}</div></td>-->
  	    		<td align="center" onclick="getDetailMate('${mateCountDetailId}','${mateCountBasic.mateCountBasicId}');" class="pd-chill-tip" title='<s:date name ="insDate" format="yyyy-MM-dd"/>'><s:date name ="insDate" format="yy-MM-dd"/></td>
  	    		<td align="center" onclick="getDetailMate('${mateCountDetailId}','${mateCountBasic.mateCountBasicId}');" class="pd-chill-tip" title='${mateBrand}'><div class="ellipsisDiv_full" >${mateBrand}</div></td>
  	    		<td align="center" onclick="getDetailMate('${mateCountDetailId}','${mateCountBasic.mateCountBasicId}');" class="pd-chill-tip" title='${exeUnitRealUse}'><div class="ellipsisDiv_full" >${exeUnitRealUse}</div></td>
  	    		<td align="center" onclick="getDetailMate('${mateCountDetailId}','${mateCountBasic.mateCountBasicId}');" class="pd-chill-tip" title='${mateChar}'><div class="ellipsisDiv_full" >${mateChar}</div></td>
  	    		<td align="center" onclick="getDetailMate('${mateCountDetailId}','${mateCountBasic.mateCountBasicId}');" class="pd-chill-tip" title='${mateStan}'><div class="ellipsisDiv_full" >${mateStan}</div></td>
  	    		<td align="center" onclick="getDetailMate('${mateCountDetailId}','${mateCountBasic.mateCountBasicId}');" class="pd-chill-tip" title='${unit}'><div class="ellipsisDiv_full" >${unit}</div></td>
  	    		<td align="center" onclick="getDetailMate('${mateCountDetailId}','${mateCountBasic.mateCountBasicId}');" class="pd-chill-tip" title='${buyCoutSingle}'>${buyCoutSingle}</td>
  	    		<td align="center" onclick="getDetailMate('${mateCountDetailId}','${mateCountBasic.mateCountBasicId}');" class="pd-chill-tip" title='${realSupply}'>${realSupply}</td>
  	    		<td align="center" onclick="getDetailMate('${mateCountDetailId}','${mateCountBasic.mateCountBasicId}');" class="pd-chill-tip" title='${stockAll}'>${stockAll}</td>
  	    		<td align="center" class="pd-chill-tip" title='<s:if test="clearAudit==1">已清算</s:if><s:else>未清算</s:else>'>
	  	    		<div class="ellipsisDiv_full" >
	  	    		<s:if test="clearAudit==1"><font  color=darkgreen >已清算</font></s:if>
	  	    		<s:else><font  color=red >未清算</font></s:else></div>
  	    		</td>
             	<td style="text-align: center;" class="pd-chill-tip" title='本明细为 <s:if test="importTypeFlg == true ">批量导入</s:if><s:else>手工录入</s:else>'>
             		<s:if test="importTypeFlg == true ">导</s:if><s:else>录</s:else>&nbsp;
	  	    		<s:if test="useStatus==1">
		             	<div class="ellipsisDiv_full" > <font  color=darkgray >已提交</font></div>
		            </s:if>
		            <s:elseif test="clearAudit==1">
		            	<security:authorize ifAnyGranted="A_MATE_ADMIN">
		           		<input id="cancelClear" class="btn_new btn_cancel_new" style="width:40px;" onclick="updateClear('${mateCountDetailId}','0');" type="button" value="取消"/>
		           		</security:authorize>
		           		
		            	<security:authorize ifAnyGranted="A_MATE_BASICDEL">
		           		<button id="DelBtn" class="btn_new btn_del_new" onclick="doDetailDelete('${mateCountDetailId}','${mateCountBasic.mateCountBasicId}');" type="button">删除</button>
		           		</security:authorize>
		            </s:elseif>
		            <s:else>
		            	<security:authorize ifAnyGranted="A_MATE_BASICDEL">
		           		<button id="DelBtn" class="btn_new btn_del_new" onclick="doDetailDelete('${mateCountDetailId}','${mateCountBasic.mateCountBasicId}');" type="button">删除</button>
		           		</security:authorize>
		            </s:else>
           		</td>
	 		 </tr>
	  </s:iterator>
	</table>
	<div class="table_pager" style="margin-top:5px;">
		<p:page />
	</div>
</div>
