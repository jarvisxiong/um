<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div id="orgPanel" class="easyui-panel" title="<s:if test="id == null">创建</s:if><s:else>编辑</s:else>机构信息" style="overflow-x:hidden;">
	
	<div style="width:100%;text-align:right;">
		<s:if test="plasOrgId != null || plasOrgId != ''">
			<a style="float:right;" href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="totalEmployees('${plasOrgId}');" id="btnEmployees" disabled="true">查看员工数</a>
			<div id="totalEmplyeeNum" style="float:right;color:red;padding:5px 10px;"></div>
			<div id="totalEmplyeeNumTip" style="float:right;"></div>
		</s:if>
	</div>
	
	<s:form id="orgForm" action="plas-org!save.action" method="post">
	
		<%-- 很重要 --%>
		<input type="hidden" id="plasOrgId" value="${plasOrgId}"/>
		<input type="hidden" name="id" value="${plasOrgId}"/>
		<input type="hidden" name="orgCd" value="${orgCd}"/>
		
		<!-- 很重要 -->
		<input type="hidden" id="formDimeTypeId" name="dimeTypeCd" value="${dimeTypeCd}"/>
		
		<!-- 机构业务编号校验唯一性 -->
		<input type="hidden" id="oldOrgBizCd" name="oldOrgBizCd" value="${orgBizCd}"/>
		
		<table class="edit_table"> 
			<col style="width:120px;"/>
			<col style="width:200px;"/>
			<col style="width:100px;"/>
			<col />
			<tr align="left">
				<td>上级机构名称</td>
				<td colspan="3">
					<div id="appendParentOrgName">${appendParentOrgName}</div>
					<input type="hidden" id="appendParentOrgId" name="appendParentOrgId" value="${appendParentOrgId}"/>
				</td>
			</tr>
			<security:authorize ifAnyGranted="A_ADMIN">
			<tr align="left">
				<td>机构内部编码</td>
				<td>${orgCd}</td>
			</tr>
			</security:authorize>
			<tr align="left">
				<td>机构名称</td>
				<td><input style="width:70%;" type="text" id="orgName" name="orgName" value="${orgName}" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>机构简称</td>
				<td><input style="width:70%;" type="text" id="shortOrgName" name="shortOrgName" value="${shortOrgName}" class="easyui-validatebox" validType="length[1,50]"/></td>
			</tr>
			<tr align="left">
				<td>机构类型</td>
				<td>
					<security:authorize ifAnyGranted="A_ADMIN">
						<s:select cssStyle="width:70%;" list="@com.powerlong.plas.utils.DictMapUtil@getMapOrgType()" name="orgTypeCd" listKey="key" listValue="value" required="true"/>
					</security:authorize>
					<security:authorize ifNotGranted="A_ADMIN">
						<select name="orgTypeCd">
							<option value="1">部门</option>
							<option value="2">中心</option>
						</select>
					</security:authorize>
					<span style="color:red">*</span>
				</td>
				<td>机构编号</td>
				<td >
					<input onkeyup="validateOrgExists($(this).val(), '${orgBizCd}')" style="width:70%;" type="text" id="orgBizCd" name="orgBizCd" value="${orgBizCd}" class="easyui-validatebox" required="true" validType="length[1,50]"/>
					<span style="color:red">*</span>
					<span id="bizCdExist" style="color:red;font-weight: bold;display: none;">机构编号已经存在！</span>
				</td>
			</tr>
			<tr align="left">
				<td>传真号码</td>
				<td><input style="width:70%;" type="text" id="faxDesc" name="faxDesc" value="${faxDesc}" class="easyui-validatebox" validType="length[0,50]"/></td>
				<td>电话号码</td>
				<td><input style="width:70%;" type="text" id="phoneDesc" name="phoneDesc" value="${phoneDesc}" class="easyui-validatebox" validType="length[0,50]"/></td>
	
			</tr>
			<tr align="left">
				
				<td>显示顺序</td>
				<td colspan="3"><input style="width:100px;" id="sequenceNo" name="sequenceNo" value='<s:if test="sequenceNo == null">0</s:if><s:else>${sequenceNo}</s:else>' required="true" class="easyui-validatebox" validType="length[1,6]" />
					<span style="color:red">*(数字:1-6位,越小显示越靠前)</span>
				</td>
			</tr>  
			<tr align="left">
				<td>机构负责人-系统职位</td>
				<td colspan="3">
					<input type="text" id="orgMgrSysPosName" value="${sysPositionName}" onclick="showPositionTree();" title="点击选择系统职位" style="cursor: pointer;" readonly="readonly" class="easyui-validatebox"/>
					<input type="hidden" id="orgMgrSysPosId" name="orgMgrSysPosId" value="${orgMgrSysPosId}"/>(请选择)
					<span id="sysPosOrgName">${sysPosOrgName}</span>
					<input type="hidden" id="plasUserId" value="${sysPosOrgMgrId}"/>
				</td>
			</tr> 
			<tr align="left">
				<td>机构负责人</td>
				<td colspan="3">
					<input type="text" id="orgMgrName" title="模糊检索账号或用户名" name="orgMgrName" value="${orgMgrName}" />
					<input type="hidden" id="orgMgrId" name="orgMgrId" value="${orgMgrId}" />(搜索)
					<span style="font-weight:bold;color: red;" id="tips"></span>
				</td>
			</tr>  
			<tr>
				<td>是否显示:</td>
				<td colspan="3">
					<s:checkbox id="visableFlg" name="visableFlg" onclick="saveVisableFlg()"></s:checkbox> 
					<span id="tip_visableFlgOperate" style="color:red;"></span>
				</td>
			</tr>  
			<tr>
				<td><label for="subject">备注</label></td>
				<td colspan="3">
					<textarea name="remark" style="height:60px;width:90%">${remark}</textarea>
				</td>
			</tr>
			<security:authorize ifAnyGranted="A_ADMIN">
			<tr align="left">
				<td colspan="4" style="color:red;">以下是网批专用字段</td>
			</tr>
			<tr align="left">
				<td>机构节点类型</td>
				<td><s:select cssStyle="width:70%;" list="@com.powerlong.plas.utils.DictMapUtil@getMapNodeLevelCd()" name="nodeLevelCd" listKey="key" listValue="value" required="true"/></td>
				<td>机构种类</td>
				<td><s:select cssStyle="width:70%;" list="@com.powerlong.plas.utils.DictMapUtil@getMapOrgKindCd()" name="orgKindCd" listKey="key" listValue="value" required="true"/></td>
			</tr>  
			</security:authorize>
			<tr align="left" style="display: none;">
				<td>是否可用</td>
				<td colspan="3"><s:checkbox name="activeBl" value="activeBl"/></td>
			</tr>  
			<s:if test="plasOrgId != null && plasOrgId != '' ">
			<tr align="left" style="display: none;">
				<td>创建人</td>
				<td>${creator}</td>
				<td>创建日期</td>
				<td><s:date format="yyyy-MM-dd hh:mm:ss" name="createdDate"/></td>
			</tr>  
			<tr align="left" style="display: none;">
				<td>更新人</td>
				<td>${updator}</td>
				<td>更新日期</td>
				<td><s:date format="yyyy-MM-dd hh:mm:ss" name="updatedDate"/></td>
			</tr>  
			</s:if> 
			<tr>
				<td colspan="4" style="height:60px;" valign="middle">
					<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveOrg();">保存</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="refreshOrgArea('${plasOrgId}');">重置</a>
					<s:if test="plasOrgId != null && plasOrgId != '' && activeBl">
					<security:authorize ifAnyGranted="A_ADMIN_UAAP_ORG,A_ADMIN">
					
					<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="orgDelete('${plasOrgId}');" id="btnDeleteOrg">删除</a>
					</security:authorize>
					</s:if>
				</td>
			</tr>	
		</table>
	</s:form>
	<div id="org_rel_list">
		<%@ include file="plas-org-relList.jsp"%>
	</div>
</div> 
<script type="text/javascript">
 
	$(function(){
		//快速搜索(模糊匹配:uiid,userName)
		$('#orgMgrName').quickSearch(
			'${ctx}/plas/plas-acct!quickSearchAcctList.action',
			['uiid','userName','orgName'],
			{uiid:'orgMgrId',userName:'orgMgrName'},
			'',
			function(result){
				var orgMgrId = $("#orgMgrId").val();
				var plasUserId = $("#plasUserId").val();
				if(plasUserId != ""){
					if(orgMgrId != plasUserId){
						$("#tips").html("机构负责人与所选系统职位负责人不一致!");
					}else{
						$("#tips").html("");
					}
				}
			}
		);
		
	});
	


	//是否可见
	function saveVisableFlg(){
		var plasOrgId = $('#plasOrgId').val();
		var visableFlg = $('#visableFlg').attr("checked");
		if(plasOrgId == ''){
			return;
		}
		$('#tip_visableFlgOperate').hide();
		var tip = '';
		if(visableFlg){
			tip = '设置可见';
		}else{
			tip = '设置隐藏';
		}
		var url = '${ctx}/plas/plas-org!saveVisableFlg.action';
		$.post(url, {plasOrgId: plasOrgId, visableFlg: visableFlg}, function(result){
			if('success' == result){
				if(isEnableTipVisable()){
					$('#tip_visableFlgOperate').html(tip + '成功!').show();
				}else{
					enableTipVisable();
				}
			}else{
				disableTipVisable();
				$.messager.alert('提示',tip + '失败! \n' + result);
				$('#visableFlg').click();
			}
		});
	}
	
	//visableFlg: ''-可见 'false'-不可见
	function disableTipVisable(){
		$('#visableFlg').attr('enableTipFlg','false')
	}
	function enableTipVisable(){
		$('#visableFlg').attr('enableTipFlg','')
	}
	function isEnableTipVisable(){
		var flg = $('#visableFlg').attr('enableTipFlg')
		if('false' == flg){
			return false;
		}else{
			return true;
		}
	}
</script>
