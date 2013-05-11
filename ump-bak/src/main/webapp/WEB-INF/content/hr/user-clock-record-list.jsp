<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
		
<table class="showTable">
	<thead>
		<tr class="header">
			<th  style="padding-left:8px;">姓名</th>
			<th  >日期</th>
			<th  >周</th>
			<th >上午上班</th>
			<th >上午下班</th>
			<th >下午上班</th>
			<th >下午下班</th>
			<th >状态</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator id="id" value="result">
			<tr
				<s:if test="#id[8] == 1">style="color:red;font-weight:bold;background-color:#E6E6E6;"</s:if>
			>
				<td>${id[1]}</td>
				<td><s:date name="#id[3]" format="yyyy-MM-dd"/></td>
				<td><s:date name="#id[3]" format="EE"/></td>
				<td><s:date name="#id[4]" format="HH:mm"/></td>
				<td><s:date name="#id[5]" format="HH:mm"/></td>
				<td><s:date name="#id[6]" format="HH:mm"/></td>
				<td><s:date name="#id[7]" format="HH:mm"/></td>
				<td>
					<s:if test="#id[8] == 1">异常</s:if>
					<s:if test="#id[8] == 0">正常</s:if>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>
<s:if test="result.size() == 0">
	<div align="center" style="font-weight: bold;color:#5B6B83;font-size: 14px; margin-top:10px;">
		没有相关的数据！
	</div>
</s:if>


