<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<form id="ctContPlanAdd" action="${ctx}/ct/ct-cont-plan!save.action" method="post">
	<div style="border-bottom:1px gray solid;">
			<input type="hidden" name="ctLedgerId" value="${ctLedgerId}">
	  		 <table class="shop-table" style="width: 300px;"  >
	          <col width="100"/>
		      <col />
			<tr>
			     <td>
			     	<font style="color:red;">*</font>合约规划名称：
			     </td>
			     <td style="width:180px">
			   		  <input type="text" name="ctPlan.planName"  value="${ctPlan.planName}" validate="required"  style="width:180px"/>
			     </td>			
			</tr>
		 </table>
		 </div>
</form>
<script type="text/javascript">

</script>