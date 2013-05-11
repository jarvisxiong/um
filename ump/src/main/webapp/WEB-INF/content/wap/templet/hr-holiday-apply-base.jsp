<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--请假单(新)-->
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<%@ include file="template-var.jsp"%>
		<div class="div_row">
		<span class="wap_title">中心/公司:</span>
		<span class="wap_value">${templateBean.center}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">部门:</span>
		<span class="wap_value">${templateBean.dept}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">姓名:</span>
		<span class="wap_value">${templateBean.userName}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">职位:</span>
		<span class="wap_value">${templateBean.position}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">职级:</span>
		<span class="wap_value">${templateBean.positionLevel}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">入职日期:</span>
		<span class="wap_value">${templateBean.joinDate}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">上班时间:</span>
		<span class="wap_value">${templateBean.workDate}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">请假期间联系地址/电话:</span>
		<span class="wap_value">${templateBean.contactWay}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">请假原因:</span>
		<span class="wap_value">${templateBean.holidayReason}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">其他说明:</span>
		<span class="wap_value">${templateBean.otherRemark}</span>
		</div>
		<div class=div_table_row>
		<span class="wap_title">序号/假期类别/单位/由/至/合计请假时间/法定假日/本休日</span>	
		<s:iterator value="templateBean.otherProperties" var="item" status="s">
		<div class="orgDiv">
			<s:property value="#s.index+1"/>./<%=CodeNameUtil.getDictNameByCd(DictContants.RES_HOLIDAY_TYPE,JspUtil.findString("#item.holidayType")) %>/<%=CodeNameUtil.getDictNameByCd(DictContants.RES_HOLIDAY_UNIT,JspUtil.findString("#item.unit")) %>/${item.beginDate}/${item.endDate}/${item.totalDays}/${item.offiHolidays}/${item.holidays}
		</div>
		</s:iterator>
		</div>

