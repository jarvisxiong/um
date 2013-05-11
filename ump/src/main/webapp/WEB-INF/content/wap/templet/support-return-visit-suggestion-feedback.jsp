<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--供方履约情况回访意见反馈汇总表--%>

<%@page import="com.hhz.ump.util.DictContants"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<div class="billContent">
	<s:set var="canEdit">false</s:set>
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<span class="wap_title">提交区域:</span>
		<div><s:checkbox name="templateBean.southArea" cssClass="group"></s:checkbox><span>南方区域</span></div>
		<div><s:checkbox name="templateBean.northArea" cssClass="group"></s:checkbox><span>北方区域</span></div>
	</div>
	<div class=div_table_row>
	<span class="wap_title">序号/供方类型/供方单位名称/承接项目/对接人/手机</span>	
	<s:iterator value="templateBean.supportReturnVisitScope" var="item" status="s">
	<div class="orgDiv">
		<s:property value="#s.index+1"/>.
		/<%=CodeNameUtil.getDictNameByCd(DictContants.SUPPORT_APPOINTMENT_TYPE,JspUtil.findString("templateBean.supportReturnVisitScope[0].supportType")) %>
		/${item.supportUnitName}/${item.acceptProject}/${item.joinPeople}/${item.telNo}
	</div>
	</s:iterator>
	</div>
	<div class="div_row">
		<span class="wap_title">反馈问题简述:</span>
		<span class="wap_value">${templateBean.feedbackDesc}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">回访意见反馈表:</span>
		<div id="show_attachmentes"></div>
		<script type="text/javascript">
		showUploadedFile('${templateBean.attachmentes}','attachmentes','${canEdit}');
		</script>
	</div>
	
</div>