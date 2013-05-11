<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="easyui-layout" fit="true" style="padding:5px;">	
	<div region="center"  icon="icon-insert" style="padding-top:5px;overflow:auto;" border="false">
	<!--新员工入职-->
	<s:if test="id!=null&&id==1">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>员工编码:</td>
				<td>
					<s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
				<td>姓名:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				<!--<span style="color:red">*</span>
					<div id="appEngName" style="display:none"></div>
				--></td>
			</tr>
			<tr>
				<td>身份证号码:</td>
				<td>
					<input value="${appChnName}" onchange="validateAppChnName()" class="easyui-validatebox"  required="true" validType="length[0,100]" id="appChnName" name="appChnName" size="32"/>
					<!--<span style="color:red">*</span>
					<div id="appChnNameTip" style="display:none"></div>
				--></td>
				<td>护照号码:</td>
				<td><s:textfield id="admissionDate" name="" cssClass="easyui-datebox" /></td>
			</tr>
			<tr >
				<td >性别:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>

				<td >出生日期:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr>
				<td>职位:</td>
				<td>
				<s:select cssStyle="text-align:left;width:95%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
				<td>行政组织:</td>
				<td>
				<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr>
				<td>职务:</td>
				<td><s:textfield id="degreeAwardingDate" name="" cssClass="easyui-datebox" /></td>
				<td>职级:</td>
				<td><s:textfield id="graduationDate" name="" cssClass="easyui-datebox" /></td>
			</tr>
			<tr >
				<td>职等:</td>
				<td><s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
				<td>入职来源:</td>
				<td><s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>	
			</tr>
			<tr >
				<td>入职日期:</td>
				<td><s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
				<td>生效日期:</td>
				<td><s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>	
			</tr>
			<tr >
				<td>员工状态:</td>
				<td><s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
				<td>试用期:</td>
				<td><s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>	
			</tr>
		</table>
	</s:form>
	</s:if>
	<!-- 离职回岗 -->
	<s:if test="id!=null&&id==2">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>员工编码:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>姓名:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr>
				<td>身份证号码:</td>
				<td>
					<s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>原职位:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr >
				<td >原所属行政组织:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>

				<td >原任职类型:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >入职日期:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>

				<td >离职日期:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >离职原因类型:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>

				<td >员工状态:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >任职类型:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>

				<td >回岗原因:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
		</table>
	</s:form>
	</s:if>
	<!--新员工转正-->
	<s:if test="id!=null&&id==3">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>员工编码:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>姓名:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr>
				<td>职位:</td>
				<td>
					<s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>所属行政组织:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr >
				<td >职级:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>

				<td >职等:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >入职日期:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>

				<td >试用期:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >转正日期:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</s:form>
	</s:if>
	<!--内部异动-->
	<s:if test="id!=null&&id==4">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>员工编码:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>姓名:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr>
				<td>职位:</td>
				<td>
					<s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>所属行政组织:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr >
				<td >当前职务:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >当前职级:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >当前职等:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>

				<td >目标职位:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >目标行政组织:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>

				<td >目标职务:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >目标职级:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >目标职等:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >变更原因:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >生效日期:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >原职位作为兼职:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</s:form>
	</s:if>
	<!--兼职入职-->
	<s:if test="id!=null&&id==5">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>员工编码:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>姓名:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr>
				<td>员工状态:</td>
				<td>
					<s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>职位:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr >
				<td >所属行政组织:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >兼职职位:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >兼职所属行政组织:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >兼职开始日期:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >兼职结束日期:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >兼职任职类型:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
		</table>
	</s:form>
	</s:if>
	<!--兼职免职-->
	<s:if test="id!=null&&id==6">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>员工编码:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>姓名:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr>
				<td>员工状态:</td>
				<td>
					<s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>职位:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr >
				<td >所属行政组织:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >兼职职位:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >兼职所属行政组织:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >兼职开始日期:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >兼职结束日期:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >兼职任职类型:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
		</table>
	</s:form>
	</s:if>
	<!--兼职变动-->
	<s:if test="id!=null&&id==7">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>员工编码:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>姓名:</td>
				<td>
					<s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
			</tr>
			<tr>
				<td>员工状态:</td>
				<td>
					<s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>职位:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr >
				<td >所属行政组织:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >目标职位:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >目标行政组织:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >变更原因:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >生效日期:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >&nbsp;</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
		</table>
	</s:form>
	</s:if>
	<!--借调调出-->
	<s:if test="id!=null&&id==8">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>员工编码:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>姓名:</td>
				<td>
					<s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
			</tr>
			<tr>
				<td>借调调出前职位:</td>
				<td>
					<s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>借调调出前所属行政组织:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr >
				<td >借调调出前职务:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >借调调出前职级:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >借调调出前职等:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >目标员工池:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >变更原因:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >借调后归属:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >拟借调结束日期:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >占调入方编制:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >占调入方组织单元人数:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >占调出方编制:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >占调出方组织单元人数:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >&nbsp;</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
		</table>
	</s:form>
	</s:if>
	<!--借调调入-->
	<s:if test="id!=null&&id==9">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>员工编码:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>姓名:</td>
				<td>
					<s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
			</tr>
			<tr>
				<td>借调调入前职位:</td>
				<td>
					<s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>借调调入前所属行政组织:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr >
				<td >借调调入前职务:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >借调调入前职级:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >借调调入前职等:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >目标员工池:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >变更原因:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >借调后归属:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >	
				<td >拟借调结束日期:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >占调出方编制:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >占调出方组织单元人数:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >占调入方编制:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >占调入方组织单元人数:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >&nbsp;</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
		</table>
	</s:form>
	</s:if>
	<!-- 借调调回 -->
	<s:if test="id!=null&&id==10">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>员工编码:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>姓名:</td>
				<td>
					<s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
			</tr>
			<tr>
				<td>借调调出后职位:</td>
				<td>
					<s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>借调调出后所属行政组织:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr >
				<td >借调调出后职务:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >借调调出后职级:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >借调调回后职位:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >借调调回后所属行政组织:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >借调调回后职务:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >借调调回后职级:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >	
				<td >借调调回后职等:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >变更原因:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >生效日期:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >&nbsp;</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
		</table>
	</s:form>
	</s:if>
	<!-- 借调处理 -->
	<s:if test="id!=null&&id==11">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>员工编码:</td>
				<td>
					<input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>姓名:</td>
				<td>
					<s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
			</tr>
			<tr>
				<td>借调调出前职位:</td>
				<td>
					<s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>借调调出前所属行政组织:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr >
				<td >借调调出前职务:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >借调调出前职级:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >借调调回前职等:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >借调调出后职位:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >借调调出后所属行政组织:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >借调调出后职务:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >	
				<td >借调调出后职级:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >借调调出后职等:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >变更原因:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >借调后归属:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >占调出方编制:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >占调出方编制:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >占调出方组织单元人数:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >占调入方编制:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >占调入方组织单元人数:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >生效日期:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
		</table>
	</s:form>
	</s:if>
	<!-- 借调调回延时处理 -->
	<s:if test="id!=null&&id==12">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>员工编码:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>姓名:</td>
				<td><s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
			</tr>
			<tr>
				<td>借调调出前职位:</td>
				<td><s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>借调调出前所属行政组织:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr >
				<td >借调调出后职位:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >借调调出后所属行政组织:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >到位日期:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >原拟调回日期:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >拟调回日期:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >生效日期:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
		</table>
	</s:form>
	</s:if>
	<!-- 离职处理 -->
	<s:if test="id!=null&&id==13">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>姓名:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>离职原因类型:</td>
				<td><s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
			</tr>
			<tr>
				<td>离职原因:</td>
				<td><s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>生效日期:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
		</table>
	</s:form>
	</s:if>
	<!-- 不在职处理 -->
	<s:if test="id!=null&&id==14">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>员工编码:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>姓名:</td>
				<td><s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
			</tr>
			<tr>
				<td>员工状态:</td>
				<td><s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>职位:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr >
				<td >所属行政组织:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >目标员工状态:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >目标行政组织:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >变更原因:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >生效日期:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >&nbsp;</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
		</table>
	</s:form>
	</s:if>
	<!-- 离退休处理 -->
	<s:if test="id!=null&&id==15">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>员工编码:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>姓名:</td>
				<td><s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
			</tr>
			<tr>
				<td>出生日期:</td>
				<td><s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>性别:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr >
				<td >员工状态:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >离退休日期:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >职位:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >所属行政组织:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >目标员工状态:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >目标行政组织:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >变更原因:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >生效日期:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
		</table>
	</s:form>
	</s:if>
	<!-- 返聘处理 -->
	<s:if test="id!=null&&id==16">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>员工编码:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>姓名:</td>
				<td><s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
			</tr>
			<tr>
				<td>员工状态:</td>
				<td><s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>职位:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr >
				<td >所属行政组织:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >目标员工状态:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >目标职位:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >目标行政组织:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >变更原因:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >入职日期:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >生效日期:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >&nbsp;</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
		</table>
	</s:form>
	</s:if>
	<!-- 返聘解聘处理 -->
	<s:if test="id!=null&&id==17">
	<s:form id="ffApp" method="post">
		<table>
			<tr >
				<td>员工编码:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
				<td>姓名:</td>
				<td><s:select cssStyle="text-align:left;width:100%;" class="easyui-combobox" panelHeight="auto" list="@com.powerlong.plas.utils.DictMapUtil@getMapAppType()" listKey="key" listValue="value" id="appTypeCd" name="appTypeCd"/>
				</td>
			</tr>
			<tr>
				<td>员工状态:</td>
				<td><s:textfield id="admissionDate" name="" cssClass="easyui-datebox" />
				</td>
				<td>离退休时间:</td>
				<td><input class="easyui-validatebox" onchange="validateAppEngName()" required="true" validType="length[0,100]" id="appEngName" name="appEngName" value="${appEngName}" size="32"/>
				</td>
			</tr>
			<tr >
				<td >职位:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >所属行政组织:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >目标员工状态:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >目标行政组织:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >解聘日期:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >变更原因:</td>
				<td><input value="${appMgrDeptName}" class="easyui-validatebox"  validType="length[0,100]"  id="appMgrDeptName" name="appMgrDeptName" size="32"/></td>
			</tr>
			<tr >
				<td >生效日期:</td>
				<td><input value="${chiefDocDesc}" class="easyui-validatebox" validType="length[0,100]" id="chiefDocDesc" name="chiefDocDesc" size="32"/></td>
				<td >&nbsp;</td>
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