<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="easyui-panel" title='<s:if test="id == null">创建</s:if><s:else>编辑</s:else>系统职位<span id="tip_operate" style="color:red;"></span>' style="overflow-x:hidden;">
<table style="width:100%;">
	<col style="width:300px;"/>
	<col />
	<tr>
		<td valign="top">
			<div style="width:99%; overflow-x: auto;overflow-y:auto;">
			<s:form id="sysPosForm" action="plas-sys-position!save.action" method="post" theme="simple">
				<input type="hidden" name="id" value="${plasSysPositionId}" id="plasSysPositionId"/>
				<input type="hidden" name="plasOrgId" value="${plasOrg.plasOrgId}"/>
				<input type="hidden" name="plasAcctId" id="plasAcctId" value="${plasAcct.plasAcctId }"/>
						
				<table style="width: 200px;"> 
					<col width="80px;"/>
					<col/>
					<tr>
						<td valign="top">职位机构:</td>
						<td>${sysPosOrgName}<a href="#"  title="调整职位所在的机构" class="easyui-linkbutton" plain="false" onclick="showPopOrg()" iconCls="icon-edit" >调整机构</a></td>
					</tr>
					<s:if test="plasSysPositionId != '' && plasSysPositionId != null">
					<tr>
						<td>编制职位CD:</td>
						<!-- 可编辑 -->
						<td><input readonly="readonly" style="width:80%;" type="text" id="sysPosCd" name="sysPosCd" value="${sysPosCd}" class="easyui-validatebox"/><span style="color:red">*</span></td>
					</tr>
					</s:if>
					<tr>
						<td>编制职位名:</td>
						<td><input style="width:80%;" type="text" id="sysPosName" name="sysPosName" value="${sysPosName}" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
					</tr>
					<tr
						<s:if test="plasSysPositionId == null || plasSysPositionId == '' ">
							style="display:none;"
						</s:if>
					>
						<td valign="top">关联账号：</td>
						<td style="text-align: left;">
							<input style="width:80%;" type="text" id="quickSearchField" name="quickSearchField" value="${quickSearchField}" title="支持账号或姓名搜索人员列表"/>
							<input type="hidden" id="quickSearchFieldId" name="quickSearchFieldId" value="${quickSearchFieldId}"/>
							<span style="color:red">*</span>
							<br/>
							<input type="button" onclick="savePositionRelAcct('${plasSysPositionId}')" value="保存关联账号"/>
							<input type="button" onclick="cleanPositionRelAcct('${plasSysPositionId}')" value="清空关联账号"/>
							<br/>
							<span style="color:red;display:none;" id="quickSearchFieldIdTip"></span>
						</td>
					</tr>
					<tr>
						<td>显示序号:</td>
						<td><input style="width:60px" id="sequenceNo" name="sequenceNo" value='${sequenceNo}' class="easyui-numberbox" validType="length[0,50]" />
							<span style="color:red;">整数,越大越靠前!</span>
						</td>
					</tr>
					<tr>
						<td>是否可用:</td>
						<td>
							<s:checkbox id="quickActiveBl" name="activeBl" onclick="savePosEnable()"></s:checkbox> 
							<span id="tip_checkOperate" style="color:red;"></span>
						</td>
					</tr>  
					<tr>
						<td>是否临时编制:</td>
						<td>
							<s:checkbox id="tmpPosFlg" name="tmpPosFlg" onclick="savePosTmp()"></s:checkbox> 
							<span id="tip_tmpOperate" style="color:red;"></span>
						</td>
					</tr>  
					<tr>
						<td>是否超编:</td>
						<td>
							<s:checkbox id="beyondFlg" name="beyondFlg" onclick="savePosBeyond()"></s:checkbox> 
							<span id="tip_beyondOperate" style="color:red;"></span>
						</td>
					</tr>  
					<tr>
						<td>是否兼职:</td>
						<td>
							<s:checkbox id="outStatFlg" name="outStatFlg" onclick="saveOutStat()"></s:checkbox> 
							<span id="tip_outStatOperate" style="color:red;"></span>
						</td>
					</tr>  
					<tr>
						<td valign="top">到期日期:</td>
						<td>
							<input style="width:100px" id="endDate" name="endDate" value='<s:date format="yyyy-MM-dd" name="endDate"/>' class="easyui-datebox"/>
							<br/>
							(格式:yyyy-MM-dd)
						</td>
					</tr>  
					<tr>
						<td>是否显示:</td>
						<td>
							<s:checkbox id="visableFlg" name="visableFlg" onclick="saveVisableFlg()"></s:checkbox> 
							<span id="tip_visableFlgOperate" style="color:red;"></span>
						</td>
					</tr>  
					
					
					<%--
					<s:if test="plasSysPositionId != null && plasSysPositionId != ''">
					<tr>
						<td>创建人:</td>
						<td>${creator}</td>
						<td>创建日期:</td>
						<td><s:date name="createdDate" format="yyyy-MM-dd"/></td>
					</tr>  
					<tr>
						<td>更新人:</td>
						<td>${updator}</td>
						<td>更新日期:</td>
						<td><s:date name="updatedDate" format="yyyy-MM-dd"/></td>
					</tr>  
					</s:if>
					 --%>
					<tr>
						<td valign="top">备注:</td>
						<td colspan="2">
							<textarea name="remark" style="height:50px;width:100%;overflow: auto;">${remark}</textarea>
						</td>
					</tr>
					<%--
					<tr class="panel-header" style="line-height:30px;">
						<td colspan="4">相关信息</td>
					</tr>
					<tr>
						<td valign="top">关联角色:</td>
						<td colspan="3" valign="top">
							<div id="roleNameList_area" style="cursor:pointer;overflow-y:auto;height:40px; padding-bottom: 10px;border: 1px solid #99BBE8;">${roleNames}</div>
						</td>
					</tr>
					<tr>
						<td valign="top">管理机构:</td>
						<td colspan="3" valign="top">
							<div id="orgNameList_area" style="cursor:pointer;overflow-y:auto;height:40px; padding-bottom: 10px;border: 1px solid #99BBE8;">${orgNames}</div>
						</td>
					</tr>
					</s:if>
					 --%>
					<tr>
						<td colspan="2" >
							<div class="toolbar">
								<a href="#" class="easyui-linkbutton" plain="false"  iconCls="icon-save" onclick="saveSysPos('${plasSysPositionId}');">保存</a>
								<a href="#" class="easyui-linkbutton" plain="false"  iconCls="icon-undo" onclick="resetSysPos()">重置</a>
								<s:if test="plasSysPositionId != null && plasSysPositionId != ''">
									<%--
									<s:if test="activeBl == true">
									<a href="#" class="easyui-linkbutton" plain="false"  iconCls="icon-remove" onclick="savePosEnable();">停用</a>
									</s:if>
									<s:else>
									<a href="#" class="easyui-linkbutton" plain="false"  iconCls="icon-save" onclick="savePosEnable();">启用</a>
									</s:else>
									<a href="#" id="abtnEdit" class="easyui-linkbutton" iconCls="icon-edit" onclick="editSysPos();">编辑系统职位</a>
									 --%>
								</s:if>
							</div>
						</td>
					</tr>	
				</table> 
			</s:form>
			</div>
		</td>
		<td valign="top" style="padding-right:10px;">
			<div style="width:99%; overflow: auto;">
			<s:if test="plasSysPositionId != null && plasSysPositionId != ''">
				<a href="#" id="abtnCopy" class="easyui-linkbutton"  iconCls="icon-copy" onclick="copySysPos('${plasSysPositionId}')">复制职位</a>
				<a href="#" id="abtnConfR" class="easyui-linkbutton"  iconCls="icon-edit" onclick="viewRelRoleList('${plasSysPositionId}')" title="查看角色">查看角色</a>
				<a href="#" id="abtnConfR" class="easyui-linkbutton"  iconCls="icon-edit" onclick="loadRoleTree('${plasSysPositionId}')" title="配置角色">配置角色</a>
				<a href="#" id="abtnConfR" class="easyui-linkbutton"  iconCls="icon-edit" onclick="loadRolePack('${plasSysPositionId}')" title="配置打包角色">配置打包角色</a>
			</s:if>
			
			<%-- 当前职位拥有的角色列表 --%>
			<div id="relRoleListPanel" style="width:99%;margin: 10px 0;">
				<%@ include file="plas-sys-position-relRoleList.jsp"%>
			</div>
			
			<%-- 当前职位拥有的角色树 --%>
			<div id="roleTreePanel" style="width:99%;display:none;margin: 10px 0;overflow: auto;"">
				<div class="panel-header" style="line-height: 22px;">
					拥有的角色如下:
					<span id="successTip"></span>
					<a style="float:right;" href="#" id="btnSaveBatchRole" iconCls="icon-save" disabled="true" class="easyui-linkbutton" onclick="showPopConfirm('${plasSysPositionId}')" >保存配置</a>
				</div>
				<table align="left" style="width:100%;border:1px solid #99BBE8;">
					<tr>
						<td valign="top" style="width:100%;">
							<div style="height:400px;overflow: scroll;">
								<div id="roleTree">
									<%--这里有.TreePanel,强制设置高度! 
									 style="height:400px;overflow-y:auto;"
									 $('#roleTree').find('.TreePanel').height("400");
									--%>
								</div>
							</div>
						</td>
						<td valign="middle" style="vertical-align:top;">
							
						</td>
					</tr>
				</table>
			</div>
			
			<%-- 打包角色 --%>
			<div id="rolePackPanel" style="width:99%;display:none;margin: 10px 0;overflow: auto;">
			</div>
			</div>
		</td>
	</tr>
</table>
</div>
<script type="text/javascript">
$(function(){
	//注册快速搜索(模糊匹配:uiid,userName)
	$('#quickSearchField').quickSearch(
		'${ctx}/plas/plas-acct!quickSearchAcctList.action',
		['uiid','userName','orgName','centerOrgName','statusCd'],
		{plasAcctId:'quickSearchFieldId',userName:'quickSearchField'},
		'',
		function(result){
			$('#quickSearchField').val(result.attr('username')+'['+result.attr('uiid')+']'+'('+result.attr('statuscd')+')');
		}
	);
	$('#roleNameList_area').toggle(
		function(){
			$(this).css({"height":"200px"});
		},
		function(){
			$(this).css({"height":"40px"});
		}
	);
	$('#orgNameList_area').toggle(
		function(){
			$(this).css({"height":"200px"});
		},
		function(){
			$(this).css({"height":"40px"});
		}
	);
	$("#wSaveConfirm").window({
		onOpen:function(){
			$('body').mask();
		},
		onClose:function(){
			$('body').unmask();
		}
	});
});
</script>