<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--销售价格优惠审批表-->
	
		<div id="billContent">
				<%@ include file="template-var.jsp"%>
				<div class="div_row">
					<span class="wap_title">项目名称:</span>
					<span class="wap_value">${templateBean.projectName}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">客户姓名:</span>
					<span class="wap_value">${templateBean.customerName}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">联系人:</span>
					<span class="wap_value">${templateBean.linkman}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">联系地址:</span>
					<span class="wap_value">${templateBean.contactAddr}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">联系电话:</span>
					<span class="wap_value">${templateBean.contactTel}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">物业范围:</span>
					<div><s:checkbox name="templateBean.selectReseBook" cssClass="group"></s:checkbox><span>商业销售</span></div>
					<div><s:checkbox name="templateBean.houseSale" cssClass="group"></s:checkbox><span>住宅销售</span></div>
					<div><s:checkbox name="templateBean.flatSale" cssClass="group"></s:checkbox><span>酒店式公寓</span></div>
				</div>
				<div class="div_row">
					<span class="wap_title">客户类别:</span>
					<div><s:checkbox name="templateBean.relation" cssClass="group"></s:checkbox><span>关系户</span></div>
					<div><s:checkbox name="templateBean.heavyBuyer" cssClass="group"></s:checkbox><span>大客户</span></div>
				</div>
				<div class="div_row">
					<span class="wap_title">付款方式:</span>
					<div><s:checkbox name="templateBean.payForOne" cssClass="group"></s:checkbox><span>一次性付款</span></div>
					<div><s:checkbox name="templateBean.heavyBuyer" cssClass="group"></s:checkbox><span>按揭付款</span></div>
					<div><s:checkbox name="templateBean.instalment" cssClass="group"></s:checkbox><span>分期付款</span></div>
				</div>
				<div class="div_row">
					<span class="wap_title">申请折扣:</span>
					<span class="wap_value">${templateBean.discount}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">面积:</span>
					<span class="wap_value">${templateBean.area}</span>
				</div>
				<div class="div_label">
					<span class="wap_label">【调整前】</span>
					<div class="div_row">
						<span class="wap_title">单价:</span>
						<span class="wap_value">${templateBean.price1}</span>
					</div>
					<div class="div_row">
						<span class="wap_title">总价:</span>
						<span class="wap_value">${templateBean.totalPrice1}</span>
					</div>
				</div>
				<div class="div_label">
					<span class="wap_label">【调整后】</span>
					<div class="div_row">
						<span class="wap_title">单价:</span>
						<span class="wap_value">${templateBean.price2}</span>
					</div>
					<div class="div_row">
						<span class="wap_title">总价:</span>
						<span class="wap_value">${templateBean.totalPrice2}</span>
					</div>
				</div>
				<div class="div_row">
					<span class="wap_title">销售状态:</span>
					<div><s:checkbox name="templateBean.saleControl" cssClass="group"></s:checkbox><span>销控</span></div>
					<div><s:checkbox name="templateBean.willSale" cssClass="group"></s:checkbox><span>未售</span></div>
					<div><s:checkbox name="templateBean.subscribe" cssClass="group"></s:checkbox><span>认购</span></div>
				</div>
				<div class="div_row">
					<span class="wap_title">购买用途:</span>
					<div><s:checkbox name="templateBean.selfSupport" cssClass="group"></s:checkbox><span>自营</span></div>
					<div><s:checkbox name="templateBean.investment" cssClass="group"></s:checkbox><span>投资</span></div>
				</div>
				<div class="div_row">
					<span class="wap_title">申购房源明细:</span>
					<span class="wap_value">${templateBean.saleDetail}</span>
				</div>
		</div>
