<%@page import="com.hhz.ump.util.JspUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
suppliersChange();
function suppliersChange(){
	var type=$("#suppliersType").val();
	if(type==1){
		$('.sup_material').hide();
		$('.sup_project').show();
	}else if(type==2){
		$('.sup_project').hide();
		$('.sup_material').show();
	}
}
</script>
<div style="bgcolor:#E4E7EC;">
<form action="${ctx}/plan/cost-ctrl-bid-purc!save.action" id="suppliersForm" method="post">
<input type="hidden" name="id" value="${costCtrlBidPurcId}"/>
<input type="hidden" name="recordVersion" value="${recordVersion}"/>
<input type="hidden" name="isSuppCompleteFlag" id="suppliers_comFlg" value="${isSuppCompleteFlag}"/>
<input type="hidden" id="suppliersId" name="costCtrlSupplierses[0].costctrlsuppliersId" value="${costCtrlSupplierses[0].costctrlsuppliersId}">
 <table style="width:100%;" class="supp_table">
  <col width="70">
  <col width="240">
  <tr style="font-weight:bold;color:#0167a2;">
   <td><span>请选择类型</span></td>
   <td>
    <s:if test="costCtrlSupplierses[0].costctrlsuppliersId!=''">
    <input type="hidden" id="suppliersType" name="costCtrlSupplierses[0].supTypeCd" value="${costCtrlSupplierses[0].supTypeCd}"/>
	    <s:if test="costCtrlSupplierses[0].supTypeCd==1">工程类</s:if>
	    <s:elseif test="costCtrlSupplierses[0].supTypeCd==2">材料设备类</s:elseif>
    </s:if>
    <s:else>
     <select id="suppliersType" name="costCtrlSupplierses[0].supTypeCd" onchange="suppliersChange();">
     <option value=""></option>
     <option value="1" <c:if test="${costCtrlSupplierses[0].supTypeCd==1}">selected</c:if>>工程类</option>
	 <option value="2" <c:if test="${costCtrlSupplierses[0].supTypeCd==2}">selected</c:if>>材料设备类</option>
    </select>
    </s:else>
   </td>
  </tr>
  <tr style="display:none;" class="sup_project sup_material">
    <td><span>招标项目名称</span></td>
    <td><input type="text" name="costCtrlSupplierses[0].bidProjectName" value="${costCtrlSupplierses[0].bidProjectName}" maxlength="50"/></td>
  </tr>
  <tr style="display:none;" class="sup_project sup_material">
    <td><span>招标范围</span></td>
    <td><input type="text" name="costCtrlSupplierses[0].projectRangeDesc" value="${costCtrlSupplierses[0].projectRangeDesc}" maxlength="100"/></td>
  </tr>
  <tr style="display:none;" class="sup_material">
    <td><span>供货期</span></td>
    <td><input type="text" name="costCtrlSupplierses[0].supMaterAreaDesc" value="${costCtrlSupplierses[0].supMaterAreaDesc}" maxlength="25"/></td>
  </tr>
  <tr style="display:none;" class="sup_project sup_material">
    <td><span>质量标准</span></td>
    <td><input type="text" name="costCtrlSupplierses[0].qualityStandardDesc" value="${costCtrlSupplierses[0].qualityStandardDesc}"maxlength="25"/></td>
  </tr>
  <tr style="display:none;" class="sup_material">
    <td><span>技术标准</span></td>
    <td><input type="text" name="costCtrlSupplierses[0].techStandardDesc" value="${costCtrlSupplierses[0].techStandardDesc}" maxlength="25"/></td>
  </tr>
  <tr style="display:none;" class="sup_material">
    <td><span>规格/型号</span></td>
    <td><input type="text" name="costCtrlSupplierses[0].materialSerialNoDesc" value="${costCtrlSupplierses[0].materialSerialNoDesc}" maxlength="25"/></td>
  </tr>
  <tr style="display:none;" class="sup_material">
    <td><span>数量</span></td>
    <td><input type="text" name="costCtrlSupplierses[0].materialQuantityDesc" value="${costCtrlSupplierses[0].materialQuantityDesc}" maxlength="25"/></td>
  </tr>
  
  <tr style="display:none;" class="sup_project">
    <td><span>工期</span></td>
    <td><input type="text" name="costCtrlSupplierses[0].projectPeriodDesc" value="${costCtrlSupplierses[0].projectPeriodDesc}" maxlength="25"/></td>
  </tr>
  <tr style="display:none;" class="sup_project">
    <td><span>计价方式</span></td>
    <td><input type="text" name="costCtrlSupplierses[0].priceMethodDesc" value="${costCtrlSupplierses[0].priceMethodDesc}" maxlength="50"/></td>
  </tr>
  <tr style="display:none;" class="sup_project sup_material">
    <td><span>投标保证金</span></td>
    <td><input type="text" name="costCtrlSupplierses[0].bidSecurityDesc" value="${costCtrlSupplierses[0].bidSecurityDesc}" maxlength="25"/></td>
   </tr>
  <tr style="display:none;" class="sup_project sup_material">
    <td><span>付款条件</span></td>
    <td><input type="text" name="costCtrlSupplierses[0].payConditionDesc" value="${costCtrlSupplierses[0].payConditionDesc}" maxlength="25"/></td>
  </tr>
  <tr style="display:none;" class="sup_project">
    <td><span>图纸情况</span></td>
    <td><input type="text" name="costCtrlSupplierses[0].drawingProgressDesc" value="${costCtrlSupplierses[0].drawingProgressDesc}" maxlength="50"/></td>
  </tr>
  
  <tr style="display:none;" class="sup_project sup_material">
    <td><span>其他</span></td>
    <td><input type="text" name="costCtrlSupplierses[0].otherProgressDesc" value="${costCtrlSupplierses[0].otherProgressDesc}" maxlength="50"/></td>
  </tr>
  <tr style="display:none;" class="sup_project sup_material">
    <td><span>经办人</span></td>
    <td><input type="text" name="costCtrlSupplierses[0].handlerName" value="${costCtrlSupplierses[0].handlerName}" maxlength="25"/></td>
  </tr>
  <tr style="display:none;" class="sup_project sup_material">
    <td><span>负责人</span></td>
    <td><input type="text" name="costCtrlSupplierses[0].supOwnerName" value="${costCtrlSupplierses[0].supOwnerName}" maxlength="25"/></td>
  </tr>
  
 </table>
 </form>
 
</div>