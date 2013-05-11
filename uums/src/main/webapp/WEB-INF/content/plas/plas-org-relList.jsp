<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 关联各个维度的上级机构 --%>
<s:if test="plasOrgId != null && plasOrgId != ''">
<table style="width:100%;">
	<col width="100px;"/>
	<col width="200px;"/>
	<col/>
	<col/>
	<tr class="panel-header">
		<td>维度名称</td>
		<td>上级机构名称</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<s:iterator value="voList" var="vo">
		<%-- 只显示逻辑/物理 --%>
		<security:authorize ifAnyGranted="A_ADMIN">
			<tr>
				<td>${vo.dimeName}</td>
				<td>
					<div id="rel_vo_parentOrgName_${vo.dimeId}">${vo.parentOrgName}</div>
				</td>
				<td>
					<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-save" onclick='showPopOrg("${vo.dimeCd}","${vo.dimeId}")'>选择上级机构</a>
					<s:if test="#vo.relId != null">
					<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-remove" onclick='removeOrgRel("${vo.dimeCd}","${vo.dimeId}","${vo.parentId}","${vo.relId}")'>解除关系</a>
					</s:if>
				</td>
				<td>
					<s:if test="#vo.dimeCd == 1">
						<div style="color:red;">注意: 显示PD首页显示;若空,表示首页不显示.</div>
					</s:if>
					<s:if test="#vo.dimeCd == 2">
						<div style="color:red;">注意: 一般同物理机构.</div>
					</s:if>
				</td>
			</tr>
		</security:authorize>
		
		<security:authorize ifNotGranted="A_ADMIN">
			<s:if test="#vo.dimeCd == 1 || #vo.dimeCd == 2">
			<tr>
				<td>${vo.dimeName}</td>
				<td>
					<div id="rel_vo_parentOrgName_${vo.dimeId}">${vo.parentOrgName}</div>
				</td>
				<td>
					<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-save" onclick='showPopOrg("${vo.dimeCd}","${vo.dimeId}")'>选择上级机构</a>
					<s:if test="#vo.relId != null">
					<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-remove" onclick='removeOrgRel("${vo.dimeCd}","${vo.dimeId}","${vo.parentId}","${vo.relId}")'>解除关系</a>
					</s:if>
				</td>
				<td>
					<s:if test="#vo.dimeCd == 1">
						<div style="color:red;">注意: 显示PD首页显示;若空,表示首页不显示.</div>
					</s:if>
					<s:if test="#vo.dimeCd == 2">
						<div style="color:red;">注意: 一般同物理机构.</div>
					</s:if>
				</td>
			</tr>
			</s:if>
		</security:authorize>
	</s:iterator>
</table>

<div id="wParentOrg" title="请选择上级机构" class="easyui-window" closed="true" style="width:300px;height:500px;padding:5px;background: #fafafa;">
	<div style="width: 100%;text-align: left;">
		<input type="button" style="cursor:pointer" class="buttom" onclick="savePopOrg('${plasOrgId}')" value="保存" />
		<input type="button" style="cursor:pointer" class="buttom" onclick="closePopOrg()" value="取消" />
	</div>
	<div id="orgTreePopDiv" style="margin-top:10px;">
		
	</div>
</div>
<script language="javascript">
	$(function(){
		$("#wParentOrg").window({
			onOpen:function(){
				$('body').mask();
			},
			onClose:function(){
				$('body').unmask();
			}
		});
	});
</script>
</s:if>