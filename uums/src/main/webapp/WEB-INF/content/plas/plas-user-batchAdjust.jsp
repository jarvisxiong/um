<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
	<col width="50%;">
	<col width="50%;">
	<tr class="panel-header">
		<td colspan="2" style="line-height: 30px;text-align: left;vertical-align: middle;padding-left:5px;border: 1px solid #99BBE8;">
			请选择调整的人员列表
		</td>
	</tr>
	<tr>
		<td valign="top">
			<div id="srcUserTree">
			
			</div>
		</td>
		<td valign="top" id="destOrgBtnsPanel" style="display:none;">
			<div style="margin:5px;">
			<%--
				<input type="button" style="cursor:pointer;" class="buttom" onclick="loadSrcUserTree()" value="刷新职员树" />
			 --%>
				<input type="button" style="cursor:pointer;" class="buttom" onclick="refreshSrcUserDiv()" value="查看选中职员" />
			</div>
			<div style="margin:5px;" id="srcUserDiv">
			
			</div>
			<div id="destOrgDiv">
				<fieldset style="margin:30px 0;padding:10px 5px;">
					<legend>调动人员</legend>
					<input type="button" style="cursor:pointer;" class="buttom" onclick="loadDestOrgTree()" value="选择目标机构" /><p/>
					<span id="span_destOrg" style="color:red;"></span><p/>
					<span id="saveDestOrgTip" style="color:red;"></span><p/><p/>
					<input id="btnSavePopDestOrg" type="button" style="cursor:pointer;" disabled="disabled" class="buttom" onclick="savePopDestOrg()" value="立即调动" />
				</fieldset>
			</div>
			<div>
				<fieldset style="margin:30px 0;padding:10px 5px;">
					<legend>设置密码策略</legend>
					<s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapPwdStrategyCd()" listKey="key" listValue="value" id="pwdStrategyCd" name="pwdStrategyCd"/>
					<span id="saveDestStraTip" style="color:red;"></span><p/><p/>
					<input id="btnChgPwd" type="button" style="cursor:pointer;" class="buttom" onclick="saveChgPwdStrategy()" value="立即更改密码" />
				</fieldset>
			</div>
		</td>
	</tr>
</table>