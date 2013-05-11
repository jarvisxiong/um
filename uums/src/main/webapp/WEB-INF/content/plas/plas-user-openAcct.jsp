<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<form id="openAcctForm" action="plas-acct!saveOpenAcct.action" method="post">
	
	<%-- 1-自动生成系统职位(默认) 2-选择职位,可选项 --%>
	<input type="hidden" id="openAcct_modeCd" name="modeCd" value="2"/>
	
	<input type="hidden" id="openAcct_plasUserId" name="plasUserId" value="${plasUserId}"/>

	<%--默认0 --%>
	<input type="hidden" name="sequenceNo" value="0"/>

	<%-- 账号类型 --%>
	<security:authorize ifNotGranted="A_ADMIN">
		<input type="hidden" name="acctTypeCd" id="openAcct_acctTypeCd" value="1"  checked="checked"/> PD账号
	</security:authorize>
		
	<table style="width:100%;">
		<col style="width:70px;"/>
		<col />
		<col style="width:220px;"/>
		
		<tr style="align:left">
			<td>账号:</td>
			<td>
				<input style="80%" type="text" id="openAcct_uiid" name="uiid" value="${uiid}" required="true" onkeyup="validateUiid()" title="光标移开输入框,自动校验账号"/>
				
				<span id="openAcct_uiid_tip" style="width:50px;color:red;">请输入</span>
			</td>
			<td rowspan="6" style="vertical-align:top">
				<div id="acctListDiv" style="width:100%;height:120px;overflow-y:auto;+position: relative;line-height:18px;" >
					提示:账号以首字全拼加其他字首字符.<br/>例如:<br/>若张三,则zhangsan,zhansan1...<br/>若欧阳振华,则ouyzh,ouyzh1...
				</div>
			</td>
		</tr> 
		<security:authorize ifAnyGranted="A_ADMIN">
		<tr>
			<td>类型:</td>
			<td>
				<input type="radio" name="acctTypeCd" id="openAcct_acctTypeCd" value="1" onchange="showDisplayPos(this)" checked="checked"/> PD账号
				<input type="radio" name="acctTypeCd" id="openAcct_acctTypeCd" value="2" onchange="showDisplayPos(this)"/> PD通讯员
				<input type="radio" name="acctTypeCd" id="openAcct_acctTypeCd" value="3" onchange="showDisplayPos(this)"/> 邮箱账号
			</td>
		</tr>
		</security:authorize>
		<tr>
			<td>状态:</td>
			<td>
				<div style="float:left;">
					<%-- 默认：1-启用 
					<s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapAcctStatus()" listKey="key" listValue="value" value="1" id="openAcct_statusCd" name="statusCd"/>
					--%>
					<select name="statusCd" id="openAcct_statusCd">
						<option value="1">正常</option>
						<option value="0">未开通</option>
					</select>
				</div>
				<div style="float:left;">
					<div style="color:red;">*</div>
				</div>
			</td>
		</tr>
		<tr>
			<td valign="top">开通日期:</td>
			<td>
				<div style="float:left;">
					<input title="格式:yyyy-MM-dd"  type="text" id="openAcct_effectDate" name="effectDate" value='${todayYyyymmdd}'/>
					<span style="color:red;">*</span>(格式:yyyy-MM-dd)
					<br/>
					<div id="span_openAcct_effectDate" style="display:none;color:red;">请确认启用日期！</div>
				</div>
			</td>
		</tr>
		<tr class="openAcct_pos_row">
			<td style="padding-left:25px;">职位:</td>
			<td>
				<div style="float:left;">
					<input type="text" name="posName" id="openAcct_posName" style="width:120px;" title="检索职位名称"/>
					<input type="hidden" name="posId" id="openAcct_posId" />
				</div>
				<div style="float:left;">
					<span id="openAcct_posId_tip" style="width:50px">(自动检索,可不选)</span>
				</div>
			</td>
		</tr>
		<tr class="openAcct_pos_row">
			<td></td>
			<td><div style="color:red;display:none;" id="tip_selectPos">注:只能授权未在岗的职位</div></td>
		</tr>
		<tr >
			<td colspan="2"  style="text-align: center; padding-top:10px;">
				<div class="toolbar">
					<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-save" onclick="processOpenAcctForm()">确认</a>
					<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-undo" onclick="canceOpenAcctForm()">取消</a>
				</div>
			</td>
			<td></td>
		</tr>
	</table>
</form>

<script type="text/javascript">

$(function(){
	if(isAutoMode()){
		$('.openAcct_pos_row').hide();
	}
	else if(isSelectMode()){
		$('.openAcct_pos_row').show();
		//快速搜索职位
		$('#openAcct_posName').quickSearch(
			'${ctx}/plas/plas-sys-position!quickSearchSysPosList.action',
			['centerOrgName','orgName','sysPosName','userName'],//显示字段
			{sysPosName:'openAcct_posName',plasSysPostionId:'openAcct_posId'},//返回赋值给界面
			{needCenterFlg:'1',emptyPosFlg:'1'},
			function(result){
				/*
				var t_userName = result.attr('userName');
				var t_plasSysPostionId = result.attr('plasSysPostionId');
				var t_sysPosName = result.attr('sysPosName');
				
				alert(t_sysPosName+'/'+t_plasSysPostionId+'/'+t_userName);
				if('(暂无)' == t_userName){
					$('#openAcct_posId').val(t_plasSysPostionId);
					$('#openAcct_posName').val(t_sysPosName);
				}else{
					$('#openAcct_posId').val('');
					$('#openAcct_posName').val('');
				}
				alert($('#openAcct_posName').val()+'//'+$('#openAcct_posId').val());
				*/
			}
		);
	} 
});

</script>
