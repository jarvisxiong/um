<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<div style="border: 1px solid  #CCCCCC;margin-left: 5px;">
<span style="color:red;display:none;" id="tip_removeRel">收回成功!</span>
<table cellpadding="0" cellspacing="0" border="0" style="min-width:300px;margin: 5px 10px;">
	<col />
	<col width="60px" />
	<tr style="list-style: 32px;height: 32px">
		<td>机构名称</td>
		<td style="text-align: center;">操作</td>
	</tr>
	<s:iterator value="selOrgList" var="org">
		<tr>
			<td style="text-align: left;">
				${org.dictName}
			</td> 
			<td style="text-align: left;padding-top:3px">
				<a href="javascript:void(0)" onclick="removeRel(this,'${org.dictCd}','${org.dictName}','${curUserCd}')" class="btn_blue">收回 </a>
			</td> 
		</tr>
	</s:iterator>
</table>

