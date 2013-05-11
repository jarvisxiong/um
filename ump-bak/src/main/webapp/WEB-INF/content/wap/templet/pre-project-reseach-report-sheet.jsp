<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<!--项目预可研报告审批表单-->

<div id="billContent">
	
	
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">项目地理位置:</span>
			<span class="wap_value">${templateBean.projectAddr}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【城市宏观经济数据】</span>
			<div class="div_row">
				<span class="wap_title">全市国内生产总值（亿元）</span>
				<span class="wap_value">${templateBean.cityGDP}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">人均GDP（元/年）</span>
				<span class="wap_value">${templateBean.peopleGDP}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">区城市人口/区常住总人口（万人）</span>
				<span class="wap_value">${templateBean.inhabitant}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">在岗职工平均资（元）</span>
				<span class="wap_value">${templateBean.averageWage}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">城镇居民人均可支配收入（元/月）</span>
				<span class="wap_value">${templateBean.disposablePersonalIncome}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">城镇居民人均生活消费支出（元/月）</span>
				<span class="wap_value">${templateBean.consumptionPerPerson}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">社会消费品零售总额（亿元/年）</span>
				<span class="wap_value">${templateBean.consumerProductsSum}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">主要产业</span>
				<span class="wap_value">${templateBean.majorIndustry}</span>
			</div>
		</div>
		<div class="div_row">
				<span class="wap_title">项目区域概况</span>
				<span class="wap_value">${templateBean.projectAreaState}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【地块所在区域的房价情况】</span>
			<div class="div_row">
				<span class="wap_title">沿街商业售价/租金</span>
				<span class="wap_value">${templateBean.businessPrice}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">城市综合体的平均售价/租金</span>
				<span class="wap_value">${templateBean.cityAvgPrice}</span>
			</div>
		</div>
		<div class="div_row">
				<span class="wap_title">业态规划初步建议</span>
				<span class="wap_value">${templateBean.preSuggest}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">其它说明</span>
				<span class="wap_value">${templateBean.otherState}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【主要商业介绍（大型超市、百货、综合体）】</span>
			<div class=div_table_row>
			<span class="wap_title">序号/名称/规模/主要业态/开业时间</span>	
			<s:iterator value="templateBean.reseachReportBusinessIntroduce" var="item" status="s">
			<div class="orgDiv">
				<s:property value="#s.index+1"/>./${item.name}/${item.scope}/${item.primaryService}/${item.startTime}
			</div>
			</s:iterator>
			</div>
		</div>
		
</div>
