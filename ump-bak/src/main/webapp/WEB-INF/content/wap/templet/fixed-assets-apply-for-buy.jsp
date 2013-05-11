<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--固定资产申购单-->
<div class="billContent" >
	
	<%@ include file="template-var.jsp"%>
	
	<%--合同文本库引用 start --%>
	<%--是否允许用户勾选合同台账 --%>
    <input type="hidden" id="isShowChkBox" value="0"/>
	<%--办公资产采购审批表（适用于地产总部）, 办公资产采购审批表（适用于地产公司） 两采购单不使用合同文本库--%>
    <s:if test="authTypeCd != 'XZRL_XZGL_140' && authTypeCd != 'XZRL_XZGL_141'">

	</s:if>
	<%--合同文本引用 end --%>
	
	<div class="div_label">
		<span class="wap_label">【申购部门】</span>
		<div class="div_row">
			<span class="wap_title">申购日期:</span>
			<span class="wap_value">${templateBean.applyDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">需用日期:</span>
			<span class="wap_value">${templateBean.needDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">申购数量:</span>
			<span class="wap_value">${templateBean.applyNumber}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">其中金额≥500元的资产数量:</span>
			<span class="wap_value">${templateBean.applyNumber2}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">申购部门:</span>
			<span class="wap_value">${templateBean.applyDept}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">申请人:</span>
			<span class="wap_value">${templateBean.applyUser}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">金额(元):</span>
			<span class="wap_value">${templateBean.totalMoney}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">申购设备及配置需求:</span>
			<span class="wap_value">${templateBean.applyContent}</span>
		</div>
	</div>
	<div class="div_row">
		<span class="wap_title">申购理由:</span>
		<span class="wap_value">${templateBean.reason}</span>
	</div>
	
	<s:if test="authTypeCd=='BLSY_XZRL_55' || authTypeCd=='SYGS_XZRL_50'">
		<s:if test="templateBean.fixedAssetsProperties.size()>0">
		<div class="div_label">
			<span class="wap_label">【资产列表】</span>
			<s:iterator value="templateBean.fixedAssetsProperties" var="item" status="s">
				<div class="div_row">
					<span class="wap_title">商业公司:</span>
					<span class="wap_value">${item.projectName}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">资产名称:</span>
					<span class="wap_value">${item.assmName}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">资产编码:</span>
					<span class="wap_value">${item.code}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">设备型号:</span>
					<span class="wap_value">${item.assmModelName}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">资产编码:</span>
					<span class="wap_value">${item.assmCode}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">使用部门:</span>
					<span class="wap_value">${item.useDepartName}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">使用时间:</span>
					<span class="wap_value">${item.useDate}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">使用情况:</span>
					<span class="wap_value"><s:if test="useStatus1!=null && useStatus1=='true'">正常使用</s:if><s:if test="useStatus2!=null && useStatus2=='true'">库存</s:if><s:if test="useStatus4!=null && useStatus4=='true'">经营出租</s:if><s:if test="useStatus5!=null && useStatus5=='true'">公用</s:if></span>
				</div>
				<div class="div_row">
					<span class="wap_title">增加方式:</span>
					<span class="wap_value"><s:if test="addWay1!=null && addWay1=='true'">购入</s:if><s:if test="addWay2!=null && addWay2=='true'">地产转入</s:if><s:if test="addWay3!=null && addWay3=='true'">其他商业公司转入</s:if><s:if test="addWay4!=null && addWay4=='true'">租入</s:if><s:if test="addWay5!=null && addWay5=='true'">捐赠</s:if></span>
				</div>
				<div class="div_row">
					<span class="wap_title">存放地点:</span>
					<span class="wap_value"><s:if test="storageSites1!=null && storageSites1=='true'">办公区</s:if><s:if test="storageSites2!=null && storageSites2=='true'">仓库</s:if><s:if test="storageSites3!=null && storageSites3=='true'">城市广场</s:if></span>
				</div>
				<div class="div_row">
					<span class="wap_title">保管人员:</span>
					<span class="wap_value">${item.keeperName}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">原值(元):</span>
					<span class="wap_value">${item.srcValue}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">净值(元):</span>
					<span class="wap_value">${item.netValue}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">折旧年限:</span>
					<span class="wap_value">${item.depreYeadNum}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">折旧方式:</span>
					<span class="wap_value">${item.depreWay}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">当前配置数:</span>
					<span class="wap_value">${item.currHasNum}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">标准配置数:</span>
					<span class="wap_value">${item.currStanNum}</span>
				</div>
				<br/>
			</s:iterator>
		</div>
		</s:if>
	</s:if>
</div>
