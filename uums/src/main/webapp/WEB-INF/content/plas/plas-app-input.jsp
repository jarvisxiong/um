<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="easyui-layout" fit="true" style="padding:0 5px;">	
	<div region="center"  icon="icon-insert" style="padding-top:5px;overflow:auto;" border="false">	
	<s:form id="ffApp" method="post">
	<input type="hidden" name="id" value="${plasAppId}" />
	<input type="hidden" name="appCd" value="${appCd}" />	
	<input type="hidden" id="oldAppBizCd" value="${appBizCd}"/>
	<input type="hidden" id="oldAppEngName" value="${appEngName}"/>
	<input type="hidden" id="oldAppChnName" value="${appChnName}"/>
		<table>
			<tr >
				<td style="width:150px;">接入应用业务编号:</td>
				<td>
					<input key="appBizCd" onchange="validateAppBizCd()" class="easyui-validatebox" required="true" validType="length[0,20]"  size="32"  id="appBizCd" type="text"/>
					<span style="color:red">*</span>
					<div id="appBizCdTip" style="display:none">应用业务编号已存在</div>
					
				</td>
				<td style="width:150px;">接入应用英文缩写:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
					<span style="color:red">*</span>
					<div id="appEngName" style="display:none">应用英文缩写已存在</div>
					
				</td>
			</tr>
			<tr >
				<td >接入应用中文名称:</td>
				<td>
					<input value="${appChnName}" onchange="validateAppChnName()" class="easyui-validatebox"  required="true" validType="length[0,100]" id="appChnName" name="appChnName" size="32"/>
					<span style="color:red">*</span>
					<div id="appChnNameTip" style="display:none">应用中文名称已存在</div>
				</td>

				<td>通信密码:</td>
				<td><input value="${securityPassword}" type="password" class="easyui-validatebox" validType="length[0,50]" id="securityPassword" name="securityPassword" size="32"/></td>
			</tr>
			<tr >
				<td >接入批准立项或上线文号:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>

				<td >接入应用主管部门名称:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr>
				<td>接入应用开发部门名称:</td>
				<td><input value="${appDevDeptName}" class="easyui-validatebox"  validType="length[0,100]" id="appDevDeptName" name="appDevDeptName" size="32"/></td>

				<td>接入应用类型:</td>
				<td><s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/></td>
				
			</tr>
			<tr>
				<td>接入应用用户量:</td>
				<td><input value="${appUserCount}" class="easyui-numberbox" min="0" max="999999999" precision="0" id="appUserCount" name="appUserCount" size="32"/></td>
			</tr>
			<tr>
				<td>接入应用项目经理中文名称:</td>
				<td><input value="${appPpChnName}" class="easyui-validatebox"  validType="length[0,50]" id="appPpChnName" name="appPpChnName" size="32"/></td>
			</tr>
			<tr >
				<td>项目经理统一登录名:</td>
				<td><input value="${appPpUiid}" class="easyui-validatebox"  validType="length[0,50]" id="appPpUiid" name="appPpUiid" size="32"/></td>
		
				<td>接入应用项目经理联系电话:</td>
				<td><input value="${appPpTelphone}" class="easyui-validatebox"  validType="length[0,50]" id="appPpTelphone" name="appPpTelphone" size="32"/></td>
			</tr>
			<tr>
				<td>接入应用联系人姓名:</td>
				<td><input value="${ appLinkName}" class="easyui-validatebox"  validType="length[0,50]" id="appLinkName" name="appLinkName" size="32"/></td>
		
				<td>接入应用联系电话:</td>
				<td><input value="${appLinkTelphone}" class="easyui-validatebox"  validType="length[0,50]" id="appLinkTelphone" name="appLinkTelphone" size="32"/></td>
			</tr>
			<tr>
				<td>试运行日期:</td>
				<td><s:textfield id="prepareDate" name="prepareDate" cssClass="easyui-datebox" /></td>
			
				<td>上线日期:</td>
				<td><s:textfield id="productDate" name="productDate" cssClass="easyui-datebox" /></td>
			</tr>	
			<tr>
				<td>操作系统平台:</td>
				<td><s:select cssStyle="text-align:left;width:100%;"lass="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapOsPlatform()" listKey="key" listValue="value" name="osPlatformCd" id="osPlatformCd" value="osPlatformCd==null?0:osPlatformCd"/></td>			
				<td>WEB服务器:</td>				
				<td><s:select cssStyle="text-align:left;width:100%;" cssClass="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapWebAppServerType()" listKey="key" listValue="value" name="webAppServerTypeCd" id="webAppServerTypeCd" value="webAppServerTypeCd==null?0:webAppServerTypeCd"/></td>			
			</tr>
			<tr>
				<td>支持LDAP(多选)(按住ctrl键多选):</td>
				<td><s:select cssStyle="text-align:left;width:100%;" cssClass="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapSupportLdapType()" listKey="key" listValue="value" name="supportLdapCds" id="supportLdapCds" value="supportLdapCds==null?0:supportLdapCds" multiple="true" /></td>			
				<td>J2EE服务器:</td>				
				<td><s:select cssStyle="text-align:left;width:100%;" cssClass="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapJ2eeServerType()" listKey="key" listValue="value" name="j2eeServerTypeCd" id="j2eeServerTypeCd" value="j2eeServerTypeCd==null?0:j2eeServerTypeCd"/></td>	
			

			</tr>
			<tr>
				<td>是否加入登录域:</td>
				<td><s:radio list="@com.powerlong.plas.utils.DictMapUtil@getMapJoinLoginDomainFlg()" listKey="key" listValue="value" id="joinLoginDomainFlg" name="joinLoginDomainFlg"  value="joinLoginDomainFlg==null?0:joinLoginDomainFlg"/></td>
						
				<td>是否支持LDAP:</td>
				<td><s:radio list="@com.powerlong.plas.utils.DictMapUtil@getMapSupportLdapFlg()" listKey="key" listValue="value" id="supportLdapFlg" name="supportLdapFlg" value="supportLdapFlg==null?0:supportLdapFlg"/></td>			
			</tr>
			<tr>
				<td>是否基于某产品:</td>
				
				<td><s:radio list="@com.powerlong.plas.utils.DictMapUtil@getMapBaseProdFlg()" listKey="key" listValue="value" id="baseProdFlg" name="baseProdFlg" value="baseProdFlg==null?0:baseProdFlg" /></td>
			
				<td>是否实现单点登录:</td>
				<td><s:radio list="@com.powerlong.plas.utils.DictMapUtil@getMapSupportSsoFlg()" listKey="key" listValue="value" id="supportSsoFlg" name="supportSsoFlg" value="supportSsoFlg==null?0:supportSsoFlg"/></td>
			</tr>	
			<tr>
				<td>产品备注:</td>
				<td><s:textarea key="prodDesc" cssClass="easyui-validatebox"  validType="length[0,500]" id="prodDesc" name="prodDesc" cssStyle="width:98%;"/></td>			
				
				<td>备注:</td>
				<td><s:textarea key="remark" cssClass="easyui-validatebox"  validType="length[0,200]" id="remark" name="remark"  cssStyle="width:98%;"/></td>
			</tr>
		</table>
	</s:form> 
	</div>
	
	<div region="south" border="true" style="height:35px;padding-left:10px;padding-top:3px;">
		<div class="toolbar">
			<s:if test="plasAppId != null">
				<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="editrow('${plasAppId}');">刷新</a>
			</s:if>
			<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-save" onclick="saveApp();">保存</a>
			<s:if test="plasAppId != null">
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteOneRow('${plasAppId}');">删除</a>
			</s:if>
			<a id="add" href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="editrow('');">新增</a>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$('#prepareDate').datebox({
			formatter: function(date){ 
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
			},   
			parser: function(date){ 
				return new Date(Date.parse(date.replace(/-/g,"/"))); 
			}
		});  
		$('#productDate').datebox({
			formatter: function(date){
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
			},   
			parser: function(date){
				return new Date(Date.parse(date.replace(/-/g,"/"))); 
			}
		});  
	});

	function resetApp(){
		$('#ffApp')[0].reset();
	}
</script>