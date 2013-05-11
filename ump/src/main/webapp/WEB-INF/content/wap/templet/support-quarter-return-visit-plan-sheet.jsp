<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--供方履约情况季度回访申请计划表--%>


<%@page import="com.hhz.ump.util.DictContants"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<div id="billContent">
	
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">季度回访目的事项:</span>
			<span class="wap_value">${templateBean.quarterReturnVisitMatter}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">要求完成时间:</span>
			<span class="wap_value">${templateBean.completeTime}</span>
		</div>
		<div class=div_table_row>
		<div class="div_row">
			<span class="wap_label">【此次回访范围】</span>
		</div>
		<span class="wap_title">序号/供方类型/供方单位名称/承接项目/对接人/电话</span>	
			<s:iterator value="templateBean.supportReturnVisitScope" var="item" status="s">
			<div class="orgDiv">
				<s:property value="#s.index+1"/>.
				/<%=CodeNameUtil.getDictNameByCd(DictContants.SUPPORT_APPOINTMENT_TYPE,JspUtil.findString("templateBean.supportReturnVisitScope[0].supportType")) %>
				/${item.supportUnitName}/${item.acceptProject}/${item.joinPeople}/${item.telNo}
			</div>
			</s:iterator>
		</div>
		<div class="div_row">
			<span class="wap_title">其它说明:</span>
			<span class="wap_value">${templateBean.otherDesc}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">附件:</span>
			<div id="show_attachmentes"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.attachmentes}','attachmentes','${canEdit}');
				</script>
		</div>
</div>
