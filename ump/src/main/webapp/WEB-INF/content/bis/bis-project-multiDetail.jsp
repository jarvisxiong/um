<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<script type="text/javascript">
<!--
if('dengyh'=='<%= SpringSecurityUtils.getCurrentUiid()%>'||'zhengym'=='<%= SpringSecurityUtils.getCurrentUiid()%>'){
	$('.virtualArea1').show();
}
//-->
</script>
<div class="list_header2" style="height:10px;line-height:10px;margin-left: 8px;"><span style="font-size:14px;"></span></div>
<form action="${ctx}/bis/bis-project!saveMulit.action" method="post" id="multiDetaForm">
	<input type="hidden" name="bisMulti.recordVersion" id="recordVersion" value="${bisMulti.recordVersion}" />
	<input type="hidden" name="bisMulti.bisMultiId" id="bisMultiId" value="${bisMulti.bisMultiId}" />
	<input type="hidden" name="bisMulti.bisProjectId"  id="bisProjectId" value="${bisMulti.bisProjectId}" />
	<input type="hidden" name="bisMulti.bisFloorId" id="bisFloorId" value="${bisMulti.bisFloorId}" />
	<table class="main_content" style="line-height:30px;">
		<col width="100"/>
		<col />
		<col width="100"/>
		<col />
		<col width="100"/>
		<col />
		<tr>
			<td align="right">多经编号：</td>
			<td>
				<input type="text" class="required" id="multiName" name="bisMulti.multiName" value="${bisMulti.multiName}" />
			</td>
			<%-- 
			<td align="right">承租方：</td>
			<td>
				<input type="text" id="renterName" name="bisMulti.renterName" value="${bisMulti.renterName}"/>
			</td>
			--%>
			<td align="right">面积：</td>
			<td>
				<input type="text" id="square" alt="amount" name="bisMulti.square" value="${bisMulti.square}"/>
			</td>
			<td align="right">位置区域：</td>
			<td>
				<input type="text" id="operationArea" name="bisMulti.operationArea" value="${bisMulti.operationArea}"/>
			</td>
		
		</tr>
		<tr>
			<td align="right">经营项目：</td>
			<td>
				<input type="text" id="operationProjectCd" name="bisMulti.operationProjectCd" value="${bisMulti.operationProjectCd}"/>
			</td>
			
			<%--
			<td align="right">编号：</td>
			<td><input type="text" name="bisMulti.multiNo" value="${bisMulti.multiNo}"/></td>
			 --%>
			<td align="right">多经类型：</td>
			<td>
				<select class="required" id="multi_type1" name="bisMulti.multiType" onChange="changeMulti(this);">
                       <option value=""></option>
                       <option value="1" <s:if test="bisMulti.multiType==1">selected</s:if> >广告位</option>
                       <option value="2" <s:if test="bisMulti.multiType==2">selected</s:if>>多经点位</option>
                 </select>
			</td>
			<td align="right" class="multFloor" style="display:none">楼层：</td>
			<td class="multFloor"  style="display:none">
			 <input type="text" class="required" id="multiFloor" name="bisMulti.multiFloor" value="${bisMulti.multiFloor}"/>
			</td>
			<td align="right" class="multGrade"  style="display:none">等级：</td>
			<td  class="multGrade" style="display:none">
			 <input type="text" class="required" id="multiGrade" name="bisMulti.multiGrade" value="${bisMulti.multiGrade}"/>
			</td>
		</tr>
		<tr>
		</tr>
		<tr>
		 <td align="right">执行价格：</td>
			<td>
			 <input type="text" class="required" id="multiPrice" name="bisMulti.multiPrice" value="${bisMulti.multiPrice}"/>
			</td>
			<td align="right">执行政策：</td>
			<td colspan="3">
			 <textarea  class="required" name="bisMulti.multiPolicy">${bisMulti.multiPolicy}</textarea>
			</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td align="right" >备注：</td>
			<td colspan="3">
			<textarea  name="bisMulti.remark">${bisMulti.remark}</textarea>
			</td>
			<td align="right" class="virtualArea1" style="display:none">所在平面图坐标：</td>
			<td style="display:none" class="virtualArea1">
				<input type="text" id="coords" name="bisMulti.coords" value="${bisMulti.coords}"/>
			</td>
			<td colspan="1"></td>
		</tr>
	</table>
</form>
<script type="text/javascript">
$(function(){
	var value=$("#multi_type1").val();
	if(value=="1"){
		$('.multGrade').show();
	}else{
		$('.multFloor').show();
	}
});
</script>
