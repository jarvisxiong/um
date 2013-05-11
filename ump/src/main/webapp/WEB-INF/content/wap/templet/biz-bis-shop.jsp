<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- 商业商家资源入库 --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<div class="div_label">
		<span class="wap_label">品牌资料</span>
		<div class="div_row">
			<span class="wap_title">品牌名(中):</span>
			<span class="wap_value">${templateBean.nameCn}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">品牌名(英):</span>
			<span class="wap_value">${templateBean.nameEn}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">经营性质:</span>
			<div><s:checkbox name="templateBean.manageCd1" cssClass="group"></s:checkbox><span>主力店</span></div>
		    <div><s:checkbox name="templateBean.manageCd2" cssClass="group"></s:checkbox><span>次主力店</span></div>
			<div><s:checkbox name="templateBean.manageCd3" cssClass="group"></s:checkbox><span>小商铺</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">品牌简介:</span>
			<span class="wap_value">${templateBean.brandDesc}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">商家类别:</span>
			<span class="wap_value">${templateBean.bisSortName}</span>
		</div>
	</div>
	<div class="div_label">
		<span class="wap_label">公司信息</span>
		<div class="div_row">
			<span class="wap_title">公司名称:</span>
			<span class="wap_value">${templateBean.companyName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">创建人:</span>
			<span class="wap_value">${templateBean.founderPeople}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">创建时间:</span>
			<span class="wap_value">${templateBean.founderDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">产地:</span>
			<span class="wap_value">${templateBean.produceAddr}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">注册地:</span>
			<span class="wap_value">${templateBean.registerAddr}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">品牌发源地:</span>
			<span class="wap_value">${templateBean.brandCradleland}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">网站:</span>
			<span class="wap_value">${templateBean.webSite}</span>
		</div>
	</div>
	<s:iterator value="templateBean.bizBisShopConns" var="item" status="s">
	<div class="div_scroll">
	 <table>
	  <tr>
	   <td  class="wap_title">代理商/供应商名称</td>
	  
	   <td colspan="3">
		    <span class="wap_value">${item.partName}</span>
		  </td>
	  </tr>
	  <tr>
	   <td class="wap_title">代理商/供应商性质</td>
	   <td colspan="3">
	     <table class="tb_checkbox">
			<tr>
			<td><div><s:checkbox name="templateBean.bizBisShopConns[%{#s.index}].supplierAttributeCd1"  cssClass="group"></s:checkbox><span>直营</span></div></td>
			<td><div><s:checkbox name="templateBean.bizBisShopConns[%{#s.index}].supplierAttributeCd2"  cssClass="group"></s:checkbox><span>全国总代理</span></div></td>
			<td><div><s:checkbox name="templateBean.bizBisShopConns[%{#s.index}].supplierAttributeCd3"  cssClass="group"></s:checkbox><span>区域代理</span></div></td>
			<td><div><s:checkbox name="templateBean.bizBisShopConns[%{#s.index}].supplierAttributeCd4"  cssClass="group"></s:checkbox><span>省代理</span></div></td>
			<td><div><s:checkbox name="templateBean.bizBisShopConns[%{#s.index}].supplierAttributeCd5"  cssClass="group"></s:checkbox><span>市代理</span></div></td>
			<td><div><s:checkbox name="templateBean.bizBisShopConns[%{#s.index}].supplierAttributeCd6"  cssClass="group"></s:checkbox><span>加盟</span></div></td>
			<td><div><s:checkbox name="templateBean.bizBisShopConns[%{#s.index}].supplierAttributeCd7"  cssClass="group"></s:checkbox><span>个体自营</span></div></td>
			</tr>
		</table>
	   </td>
	  </tr>
	  <tr>
	   <td  class="wap_title">公司地址</td>
	   <td><span class="wap_value">${item.partAddress}</span></td>
	   <td  class="wap_title">公司电话</td>
	   <td><span class="wap_value">${item.principalPhone}</span></td>
	  </tr>
	  <tr>
	   <td class="wap_title">手机</td>
	   <td><span class="wap_value">${item.principalTel}</span></td>
	   <td class="wap_title">联络人</td>
	   <td><span class="wap_value">${item.principal}</span></td>
	   </tr>
	  <tr>
	   <td class="wap_title">职务</td>
	   <td><span class="wap_value">${item.principalPos}</span></td>
	    <td class="wap_title">传真</td>
	   <td><span class="wap_value">${item.fax}</span></td>
	   </tr>
	  <tr>
	   <td class="wap_title">邮编</td>
	   <td colspan="3"><span class="wap_value">${item.postCode}</span></td>
	  </tr>
	  <tr>
	   <td class="wap_title">已合作门店</td>
	   <td><span class="wap_value">${item.cooperatedShop}</span></td>
	    <td class="wap_title">可合作门店</td>
	   <td><span class="wap_value">${item.toCooperateShop}</span></td>
	  </tr>
	  <tr>
	   <td class="wap_title">经营区域/城市</td>
	   <td colspan="3"><span class="wap_value">${item.manageCity}</span></td>
	  </tr>
	  <tr>
	   <td class="wap_title">主要合作条件</td>
	   <td colspan="3"><span class="wap_value">${item.conditionForCooperation}</span></td>
	  </tr>
	  <tr>
	   <td class="wap_title">备注</td>
	   <td colspan="3"><span class="wap_value">${item.remark}</span></td>
	   </tr>
	</table>
	</div>
	</s:iterator>
</div>

