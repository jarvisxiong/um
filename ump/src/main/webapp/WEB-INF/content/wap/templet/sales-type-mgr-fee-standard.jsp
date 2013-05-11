<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
	销售型管理费标准审批表(eg: 销售型管理费标准审批表) 
--%>
<!-- 
	注：1、此项工作必须上会决策，请在上会前三天，将上会资料发至总裁办企管部陈美馨处。
    2、此单审批完成后，应向宝龙商业财务部备案。
	
	 -->
<%@ include file="template-var.jsp"%>	 
<div id="billContent">
	<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">物业形态:</span>
			<div><s:checkbox name="templateBean.typeCd1" cssClass="group"></s:checkbox><span>室外商铺</span></div>
			<div><s:checkbox name="templateBean.typeCd2" cssClass="group"></s:checkbox><span>住宅底商</span></div>
			<div><s:checkbox name="templateBean.typeCd3" cssClass="group"></s:checkbox><span>办公楼</span></div>
			<div><s:checkbox name="templateBean.typeCd4" cssClass="group"></s:checkbox><span>独栋别墅</span></div>
			<div><s:checkbox name="templateBean.typeCd5" cssClass="group"></s:checkbox><span>联排别墅</span></div>
			<div><s:checkbox name="templateBean.typeCd6" cssClass="group"></s:checkbox><span>叠加别墅</span></div>
			<div><s:checkbox name="templateBean.typeCd7" cssClass="group"></s:checkbox><span>多层</span></div>
			<div><s:checkbox name="templateBean.typeCd8" cssClass="group"></s:checkbox><span>小高层及高层</span></div>
			<div><s:checkbox name="templateBean.typeCd9" cssClass="group"></s:checkbox><span>酒店式公寓</span></div>
			<div><s:checkbox name="templateBean.typeCd10" cssClass="group"></s:checkbox><span>地下停车位</span></div>
	</div>
	<div class="div_row">
			<span class="wap_title">物业管理费标准:</span>
			<span class="wap_value">${templateBean.standardDesc}</span>
	</div>
</div>


