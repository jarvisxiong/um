<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-var.jsp"%>
<!--更名审批表-->
<div id="billContent">
	<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">类别:</span>
			<div><s:checkbox name="templateBean.typeMinus" cssClass="group"></s:checkbox><span>减名</span></div>
			<div><s:checkbox name="templateBean.typeAdd" cssClass="group"></s:checkbox><span>增名</span></div>
			<div><s:checkbox name="templateBean.typeChange" cssClass="group"></s:checkbox><span>换名(转让)</span></div>
	</div>
	<div class="div_row">
			<span class="wap_title">原业主姓名:</span>
			<span class="wap_value">${templateBean.ownerOri}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">身份证/护照号:</span>
			<span class="wap_value">${templateBean.idnoOri}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">联系电话:</span>
			<span class="wap_value"> ${templateBean.phoneNoOri}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">通讯地址:</span>
			<span class="wap_value">${templateBean.addressOri} </span>
	</div>
	<div class="div_row">
			<span class="wap_title">认购单元:</span>
			<span class="wap_value"> ${templateBean.houseName}栋${templateBean.floorName}层${templateBean.unitName}单元${templateBean.storeName}住宅/店面</span>
	</div>
	<div class="div_row">
			<span class="wap_title">认购时间:</span>
			<span class="wap_value"> ${templateBean.substribeDate}</span>
	</div>
	
	
	
	
	
	<div class="div_row">
			<span class="wap_title">现业主姓名:</span>
			<span class="wap_value">${templateBean.ownerCur}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">身份证/护照号:</span>
			<span class="wap_value">${templateBean.idnoCur}</span>
	</div>	
	<div class="div_row">
			<span class="wap_title">联系电话:</span>
			<span class="wap_value">${templateBean.phoneNoCur} </span>
	</div>
	<div class="div_row">
			<span class="wap_title">通讯地址:</span>
			<span class="wap_value"> ${templateBean.addressCur}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">单价(元):</span>
			<span class="wap_value">${templateBean.unitPrice} </span>
	</div>
	<div class="div_row">
			<span class="wap_title">总价(元):</span>
			<span class="wap_value"> ${templateBean.totalPrice}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">是否已额外申请优惠:</span>
			<div><s:checkbox name="templateBean.isPrivilegeTrue" cssClass="group"></s:checkbox><span>是${templateBean.privilegeValue}%</span></div>
			<div><s:checkbox name="templateBean.isPrivilegeFalse" cssClass="group"></s:checkbox><span>否</span></div>
	</div>
	
	<div class="div_label">
	<span class="wap_label">【已付款项 】</span>
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
				<span class="wap_title">续   款(元):</span>
				<span class="wap_value">${templateBean.repayAmt}</span>
		</div>
	</div>
	
	<div class="div_row">
			<span class="wap_title">物业现状</span>
			<div><s:checkbox name="templateBean.selectReseBook" cssClass="group"></s:checkbox><span>预订书</span></div>
			<div><s:checkbox name="templateBean.selectSignContract" cssClass="group"></s:checkbox><span>签定买卖合同</span></div>
			<div><s:checkbox name="templateBean.selectMortgageLoan" cssClass="group"></s:checkbox><span>办理按揭</span></div>
	</div>	
	
	<div class="div_row">
			<span class="wap_title">声明:</span>
			<span class="wap_value">
			本人同意上述房产为本人名字，并愿意支付以下费用：原成交总价1％的手续费用${templateBean.procedureChangeAmt}元。上述房屋因更名产生的法律纠纷一切与贵公司无关。
			原业主签名：${templateBean.owerSignOri},&nbsp;&nbsp;&nbsp;&nbsp;现业主签名：${templateBean.owerSignCur}
			</span>
	</div>
	<div class="div_row">
			<span class="wap_title">备注:</span>
			<span class="wap_value">${templateBean.contentDesc}</span>
	</div>
		
</div>



