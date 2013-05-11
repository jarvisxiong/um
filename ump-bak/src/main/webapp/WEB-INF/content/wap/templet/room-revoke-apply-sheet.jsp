<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--退房审批表-->
<div id="billContent">
	<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">业主姓名:</span>
			<span class="wap_value">${templateBean.owerName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">身份证/护照号:</span>
			<span class="wap_value">${templateBean.idno}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">联系电话 	:</span>
			<span class="wap_value">${templateBean.phoneNo}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">通讯地址:</span>
			<span class="wap_value">${templateBean.address}</span>
	</div>
	
	<div class="div_label">
		<span class="wap_label">【退房资料】</span>
		<div class="div_row">
			<span class="wap_title">退购单元:</span>
			<span class="wap_value">${templateBean.buildName}栋${templateBean.unitName}单元${templateBean.floorName}层${templateBean.storeName}住宅/店面</span>
		</div>
		<div class="div_row">
			<span class="wap_title">建筑面积:</span>
			<span class="wap_value">${templateBean.buildSquare}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">原建筑单价(元):</span>
			<span class="wap_value">${templateBean.oldConsUnitPrice}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">新建筑单价(元):</span>
			<span class="wap_value">${templateBean.newConsUnitPrice}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">原总价(元):</span>
			<span class="wap_value">${templateBean.oldTotalPrice}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">新总价(元):</span>
			<span class="wap_value">${templateBean.newTotalPrice}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">认购时间 	:</span>
			<span class="wap_value">${templateBean.substribeDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">调价比例(%):</span>
			<span class="wap_value">${templateBean.priceAdjustProp}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【已付款项】</span>
				<div class="div_row">
						<span class="wap_title">时间:</span>
						<span class="wap_value">${templateBean.prePayDate}</span>
				</div>
				<div class="div_row">
						<span class="wap_title">预订金(元):</span>
						<span class="wap_value">${templateBean.prePayAmt}</span>
				</div>
				<div class="div_row">
						<span class="wap_title">时间:</span>
						<span class="wap_value">${templateBean.firstPayDate}</span>
				</div>
				<div class="div_row">
						<span class="wap_title">首付款(元):</span>
						<span class="wap_value">${templateBean.firstPayAmt}</span>
				</div>
				<div class="div_row">
						<span class="wap_title">时间:</span>
						<span class="wap_value">${templateBean.repayDate}</span>
				</div>
				<div class="div_row">
						<span class="wap_title">续款(元):</span>
						<span class="wap_value">${templateBean.repayAmt}</span>
				</div>
		</div>
	</div>
	<div class="div_row">
			<span class="wap_title">物业现状:</span>
			<div><s:checkbox name="templateBean.selectReseBook" cssClass="group"></s:checkbox><span>预订书</span></div>
			<div><s:checkbox name="templateBean.selectSignContract" cssClass="group"></s:checkbox><span>签定买卖合同</span></div>
			<div><s:checkbox name="templateBean.selectMortgageLoan" cssClass="group"></s:checkbox><span>办理按揭</span></div>
	</div>
	
	<div class="div_row">
			<span class="wap_title">声明:</span>
			<span class="wap_value">本人申请上述房产的退购，并愿意支付以下费用：原房屋成交总价1％的手续费用，即 ${templateBean.procedureChangeAmt}元。业主签名：${templateBean.owerSignName}</span>
	</div>
	
	<div class="div_row">
			<span class="wap_title">备注:</span>
			<span class="wap_value">${templateBean.contentDesc}</span>
	</div>
</div>


