<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>



<%-- 宝龙商业人员申请表 --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
 
	<div class="div_row">
		<span class="wap_title">中心:</span>
		<span class="wap_value">${templateBean.centerName}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">职位:</span>
		<span class="wap_value">${templateBean.positionName}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">级别:</span>
		<span class="wap_value">${templateBean.positionLevelName}</span>
	</div>
		 
		 
		 
	<div class="div_row">
		<span class="wap_title">薪金(元):</span>
		<span class="wap_value">${templateBean.salary}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">性别:</span>
		<span class="wap_value">
			<div><s:checkbox name="templateBean.sexCd1" cssClass="group"></s:checkbox><span>男</span></div>
			<div><s:checkbox name="templateBean.sexCd2" cssClass="group"></s:checkbox><span>女</span></div>
		</span>
	</div>
		  
	<div class="div_row">
		<span class="wap_title">年龄:</span>
		<span class="wap_value">${templateBean.age}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">婚否:</span>
		<span class="wap_value">
			<div><s:checkbox name="templateBean.marryCd1" cssClass="group"></s:checkbox><span>是</span></div>
			<div><s:checkbox name="templateBean.marryCd2" cssClass="group"></s:checkbox><span>否</span></div>
		</span>
	</div> 
	<div class="div_row">
		<span class="wap_title">到岗日期:</span>
		<span class="wap_value">${templateBean.planWorkDate}</span>
	</div>
	
	
	<div class="div_row">
		<span class="wap_title">申请人数:</span>
		<span class="wap_value">${templateBean.applyCount}</span>
		<span class="wap_value">
			<div><s:checkbox name="templateBean.applyTypeCd1" cssClass="group"></s:checkbox><span>增加</span></div>
			<div><s:checkbox name="templateBean.applyTypeCd2" cssClass="group"></s:checkbox><span>补缺</span></div>
		</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">员工类别:</span>
		<span class="wap_value">
			<div><s:checkbox name="templateBean.employeeTypeCd1" cssClass="group"></s:checkbox><span>正式工</span></div>
			<div><s:checkbox name="templateBean.employeeTypeCd2" cssClass="group"></s:checkbox><span>临时工</span></div>
		</span>
	</div>
			    
	<div class="div_row">
		<span class="wap_title">工作职责简述:</span>
		<span class="wap_value">${templateBean.workDutyDesc}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">任职要求:</span>
		<span class="wap_value">${templateBean.workRequireDesc}</span>
	</div>
	
	
	<div class="div_row">
		<span class="wap_title">人员申请理由:</span>
		<span class="wap_value">${templateBean.applyReasonDesc}</span>
	</div> 
	
	<div class="div_row">
		<span class="wap_title">招聘渠道:</span>
		<span class="wap_value">
			<div><s:checkbox name="templateBean.recruitDitchCd1" cssClass="group"></s:checkbox><span>公司内部提升</span></div>
			<div><s:checkbox name="templateBean.recruitDitchCd2" cssClass="group"></s:checkbox><span>公司内部调动</span></div>
			<div><s:checkbox name="templateBean.recruitDitchCd3" cssClass="group"></s:checkbox><span>从外部招聘</span></div>
		</span>
	</div>   
	
	<div class="div_row">
		<span class="wap_title">招聘费用:</span>
		<span class="wap_value">${templateBean.recruitFeeAmt}</span>
	</div> 
	
	<div class="div_row">
		<span class="wap_title">实际到岗时间:</span>
		<span class="wap_value">${templateBean.realWorkDate}</span>
	</div> 
</div>