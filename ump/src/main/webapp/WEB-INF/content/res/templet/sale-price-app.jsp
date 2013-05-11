<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<!--销售价格审批表-->
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="80"/>
					<col/>
					<tr>
						<td class="td_title">项目名称</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"  /></td>
					</tr>
					<tr>
					  <td class="td_title">物业范围</td>	
					  <td><input class="inputBorder" validate="required" type="text" name="templateBean.tenementRange" value="${templateBean.tenementRange}"/>
					  </td>
					</tr>
					<tr>
					 <td class="td_title">价格类别</td>
					 <td class="chkGroup" validate="required" align="left">
					  <table class="tb_checkbox" >
					   <col width="100"/>
					   <col width="100"/>
					   <col width="120"/>
					   <col width="100"/>
					   <tr>
					    <td><s:checkbox name="templateBean.pricTypeHouse" cssClass="group"></s:checkbox>住宅</td>
					    <td> <s:checkbox name="templateBean.pricTypeFlat" cssClass="group"></s:checkbox>酒店式公寓</td>
					    <td><s:checkbox name="templateBean.pricTypeBusiness" cssClass="group"></s:checkbox>城市广场商业</td>
					    <td><s:checkbox name="templateBean.pricTypeMultiple" cssClass="group"></s:checkbox>住宅底商</td>
					   </tr>
					   </table>
					 </td>
					</tr>
					<s:if test="authTypeCd=='YXGL_XSJG_25' || authTypeCd=='YXGL_XSJG_26'">
					<tr>
					  <td class="td_title">定价阶段</td>
					  <td class="chkGroup" validate="required" align="left">
					   <table class="tb_checkbox">
					   <col width="125"/>
					   <col/>
					   <tr>
					    <td> <s:checkbox name="templateBean.priceMomentFirst"  cssClass="group"></s:checkbox>初次定价</td>
					    <td><s:checkbox name="templateBean.priceMomentSeveral"  cssClass="group"></s:checkbox>后期销售调价
					   (第 
					   <input class="inputBorder" type="text" style="width:5%;" name="templateBean.several" value="${templateBean.several}" cssClass="group"/>
					    次修订)</td>
					   </tr>
					   <tr>
					    <td><s:checkbox name="templateBean.priceMomentResell"  cssClass="group"></s:checkbox>房源再售
					     (价格
					     </td>
					     <td class="chkGroup" align="left">
					       <table class="tb_checkbox">
					         <col width="65"/>
					         <col/>
					         <tr>
					         <td>
					          <s:checkbox name="templateBean.upPrice"  cssClass="innerGroup"></s:checkbox>上调
					         </td>
					         <td>
					          <s:checkbox name="templateBean.samePrice" cssClass="innerGroup"></s:checkbox>不变)
					         </td>
					         </tr>
					         </table>
					     </td>
					   </tr>
					    </table> 
					  </td>
					</tr>
					</s:if>
					<tr>
					  <td class="td_title">价格标准</td>
					  <td>
					  <textarea class="inputBorder contentTextArea" validate="required" name="templateBean.priceStandard">${templateBean.priceStandard}</textarea>
					  </td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
$(".innerGroup").click(function(){
	if($(this).attr('checked')){
		$(this).siblings().attr('checked',false);
		var curName=$(this).attr("name");
		$(this).parents(".chkGroup").find(".innerGroup[name!='"+curName+"']").attr('checked',false);
	}
});
$(".chkGroup").each(function(i){
	$(i).children(".innerGroup").attr('checked',false);
});
</script>
