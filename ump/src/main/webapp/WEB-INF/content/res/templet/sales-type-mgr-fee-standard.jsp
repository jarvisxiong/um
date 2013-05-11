<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
	销售型管理费标准审批表(eg: 销售型管理费标准审批表) 
--%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">

	
	
	<!-- 
	注：1、此项工作必须上会决策，请在上会前三天，将上会资料发至总裁办企管部陈美馨处。
    2、此单审批完成后，应向宝龙商业财务部备案。
	
	 -->
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="120px">
			<col />
			
			<tr>
				<td style="border-top:0 none;" class="td_title">项目名称</td>
				<td style="border-top: none;">
					<input id="projectName" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" name="templateBean.projectCd" value="${templateBean.projectCd}" />
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">物业形态</td>
				<td style="border-top:0 none;" class="chkGroup" align="left"  validate="required">
					<s:checkbox name="templateBean.typeCd1"  cssClass="group"></s:checkbox>室外商铺
					<s:checkbox name="templateBean.typeCd2"  cssClass="group"></s:checkbox>住宅底商
					<s:checkbox name="templateBean.typeCd3"  cssClass="group"></s:checkbox>办公楼
					<s:checkbox name="templateBean.typeCd4"  cssClass="group"></s:checkbox>独栋别墅
					<s:checkbox name="templateBean.typeCd5"  cssClass="group"></s:checkbox>联排别墅
					<br/>
					<s:checkbox name="templateBean.typeCd6"  cssClass="group"></s:checkbox>叠加别墅 
					<s:checkbox name="templateBean.typeCd7"  cssClass="group"></s:checkbox>多层
					<s:checkbox name="templateBean.typeCd8"  cssClass="group"></s:checkbox>小高层及高层
					<s:checkbox name="templateBean.typeCd9"  cssClass="group"></s:checkbox>酒店式公寓
					<s:checkbox name="templateBean.typeCd10"  cssClass="group"></s:checkbox>地下停车位
				</td> 
			</tr>
			<tr>
				<td style="border-top:0 none;" class="td_title" rowspan="2">物业管理费标准</td>
				<td style="border-top:0 none;">
					注：（文字说明价格依据）
				</td>
			</tr>
			<tr>
				<td style="border-top:0 none;">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.standardDesc">${templateBean.standardDesc}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
