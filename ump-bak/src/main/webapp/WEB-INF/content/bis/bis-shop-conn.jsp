 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/resources/css/bis/bis-shop.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<form action="${ctx}/bis/bis-shop!saveConn.action" method="post" id="shop-conn-form">
<input type="hidden" id="bisShopId" name="id" value="${id}"/>
 <table id="conn_new" class="shop-table">
	    <col width="20%" />
		  <col width="30%"/>
		  <col width="20%" />
		  <col width="30%"/>
		 <tr>
		 <td>代理商/供应商名称</td>
		 <td ><input type="text" name="bisShopConn.partName"/> </td>
		 <td>代理商/供应商性质</td>
		 <td>
		   <input type="hidden" name="bisShopConn.supplierAttributeCd"/>
		 <s:select list="mapBisSupplier" listKey="key" listValue="value" onchange="doAttributeCd(this);" cssClass="shop_edit" style="width:100%;"/>
		 </td>
		 </tr>
		<tr>
		  <td>公司地址</td>
		 <td><input type="text" name="bisShopConn.partAddress"/></td>
		 <td>公司电话</td>
		 <td><input type="text" name="bisShopConn.principalPhone"/></td>
		</tr>
		<tr>
		  <td>&nbsp;&nbsp;手机</td>
		 <td><input type="text" name="bisShopConn.principalTel"/></td>
		 <td>联络人</td>
		 <td><input type="text" name="bisShopConn.principal"/></td>
		</tr>
		<tr>
		 <td>职务</td>
		 <td><input type="text" name="bisShopConn.principalPos"/></td>
		 <td>传真</td>
		 <td><input type="text" name="bisShopConn.fax"/></td>
		</tr>
		<tr>
		 <td>&nbsp;&nbsp;邮编</td>
		 <td colspan="3"><input type="text" name="bisShopConn.postCode"/></td>
		</tr>
		<tr>
		 <td>已合作门店</td>
		 <td><input type="text" name="bisShopConn.cooperatedShop"/> </td>
		 <td>可合作门店</td>
		 <td><input type="text" name="bisShopConn.toCooperateShop" /> </td>
		</tr>
		<tr>
		 <td>经营区域/城市</td>
		 <td colspan="3"><input type="text" name="bisShopConn.manageCity"/> </td>
		</tr>
		<tr>
		 <td>主要合作条件</td>
		 <td colspan="3"><input type="text" name="bisShopConn.conditionForCooperation"/></td>
		</tr>
		<tr>
		 <td>备注</td>
		 <td colspan="3"><input type="text" name="bisShopConn.remark" /></td>
		</tr>
	 </table>
</form>
	 <div>
	 <input type="button" title="保存" class="btn_green_55_20" value="保存" onclick="doSaveConn();" />
	 <input type="button" title="取消" class="btn_red_55_20" value="取消" onclick="cancelNewConn(this);"/>
</div>
<script type="text/javascript">
function doSaveConn(){
	$("#shop-conn-form").ajaxSubmit(function(result) {
		
	});
}
function doAttributeCd(dom){
	$(dom).prev().val($(dom).val());
}
</script>
	