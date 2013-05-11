<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="easyui-layout" fit="true" style="padding:0 5px;">	
	<div region="center"  icon="icon-insert" style="padding-top:5px;overflow:auto;" border="false">	
	<s:if test="id!=null&&id==1">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>学历:</td>
				<td>
					<s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
				<td>学位:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
					<!--<span style="color:red">*</span>
					<div id="appEngName" style="display:none"></div>
				--></td>
			</tr>
			<tr>
				<td>专业:</td>
				<td>
					<input value="${appChnName}" onchange="validateAppChnName()" class="easyui-validatebox"  required="true" validType="length[0,100]" id="appChnName" name="appChnName" size="32"/>
					<!--<span style="color:red">*</span>
					<div id="appChnNameTip" style="display:none"></div>
				--></td>
				<td>入学时间:</td>
				<td><s:textfield id="admissionDate" name="" cssClass="easyui-datebox" /></td>
			</tr>
			<tr >
				<td >毕业学校:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>

				<td >学习形式:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr>
				<td>教育类型:</td>
				<td>
				<s:select cssStyle="text-align:left;width:95%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
				<td>学制(年):</td>
				<td>
				<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr>
				<td>学位授予日期:</td>
				<td><s:textfield id="degreeAwardingDate" name="" cssClass="easyui-datebox" /></td>
				<td>毕业日期:</td>
				<td><s:textfield id="graduationDate" name="" cssClass="easyui-datebox" /></td>
			</tr>
			<tr >
				<td>最高学位:</td>
				<td><s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
				<td>最高学历:</td>
				<td><s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>	
			</tr>
		</table>
	</s:form>
	</s:if>
	<!-- 职业资格信息 -->
	<s:if test="id!=null&&id==2">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>职业资格名称:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>职业资格级别:</td>
				<td>
					<s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
			</tr>
			<tr>
				<td>获取时间:</td>
				<td>
					<s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>授予单位:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr >
				<td >聘任或注册单位:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>

				<td >聘任或注册年月:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
		</table>
	</s:form>
	</s:if>
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