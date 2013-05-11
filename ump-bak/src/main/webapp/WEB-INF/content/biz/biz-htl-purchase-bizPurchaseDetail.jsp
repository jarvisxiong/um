<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<form action="${ctx}/biz/biz-htl-purchase!save.action" method="post" id="bizPurchaseDataForm" >
	<input type="hidden" id="d_hotelCd" name="hotelCd" value="${entity.hotelCd}" />
	<input type="hidden" id="id" name="id" value="${entity.bizHtlPurchaseId}"/>
	<input type="hidden" id="recordVersion" name="recordVersion" value="${entity.recordVersion}"/>
			 <table class="shop-table">
		          <col width="100"/>
			      <col />
				  <col width="100"/>
				  <col />
				<tr>
			     <td><font style="color:red;">*</font>酒店名称</td>
			     <td><input type="text" id="d_hotelName" name="hotelName" value="${entity.hotelName}" validate="required" /></td>			
			  	 <td><font style="color:red;">*</font>细项类</td>
			  	 <td style="text-align: left;">
				      <security:authorize ifAnyGranted="A_BIZ_PURCHASE_ADMIN">
				      <s:select style="width:100%;" name="entity.itemTypeCd"  list="@com.hhz.ump.util.DictMapUtil@getMapPurchaseItemType()" listKey="key" listValue="value" ></s:select>
				      </security:authorize>
				      <security:authorize ifNotGranted="A_BIZ_PURCHASE_ADMIN">
				      <p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPurchaseItemType()" value="entity.itemTypeCd"/>
				      </security:authorize>
		         </td>
				</tr>
				<tr>
				   <td><font style="color:red;">*</font>单位名称</td>
				   <td><input type="text"    name="unitName" value="${entity.unitName}" validate="required" /></td>
				   <td><font style="color:red;">*</font>市场价(元)</td>
			       <td><input type="text" name="marketPrice" value="${entity.marketPrice}" validate="required" /> </td>
				</tr>
				<tr>
					<td><font style="color:red;">*</font>细项名称</td>
					<td colspan="3"><input  type="text" name="itemName" value="${entity.itemName}" validate="required"/></td>
				</tr>
				<tr>
				   <td>上次定价(元)</td>
			       <td><input type="text" name="lastPrice" alt="amount" value="${entity.lastPrice}"  maxlength="9" /> </td>
				   <td>本月定价(元)</td>
			       <td><input type="text"   name="price" alt="amount" value="${entity.price}"  maxlength="9"/> </td>
				 </tr>
				 <tr>
			  	   <td>开始日期</td>
			 	   <td><s:textfield name="entity.fromDate" onfocus="WdatePicker()" onblur="updateContStatus('plan');" /></td>
				   <td>结束日期</td>
				   <td><s:textfield name="entity.toDate" onfocus="WdatePicker()" onblur="updateContStatus('plan');" /></td>
				 </tr>
				  <tr>
				   <td>说明</td>
			       <td colspan="3" rowspan="3"><textarea id="remark"  style="width:100%;height:100px;background-color: #DBE5F1;border: none;" name="remark">${entity.remark}</textarea></td>
				 </tr>
			 </table>
</form>

<script type="text/javascript">
<security:authorize ifNotGranted="A_BIZ_PURCHASE_ADMIN">
$("#bizPurchaseDetaForm").find(":text,textarea").css('backgroundColor','#dddbdc');
$("#bizPurchaseDetaForm").find(":text,textarea").attr('readOnly',"readOnly");
</security:authorize>
$("#d_hotelName").quickSearch("${ctx}/biz/biz-htl-hoe!quickSearch.action",['dictName'],{dictCd:'d_hotelCd',dictName:"d_hotelName"});
</script>
