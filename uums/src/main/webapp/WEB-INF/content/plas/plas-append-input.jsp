<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" import="com.hhz.uums.utils.DictContants;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>机构管理</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div class="easyui-panel" title="基本信息" style="overflow-x:hidden;overflow: auto;">
	<s:form id="easForm" action="" method="post">
	<div class="easyui-tabs" border="false" style="height:390;">
		<div title="个人信息" style="overflow: auto;"> 
			<table class="edit_table">
			<col style="width:90px;"/>
			<col style="width:200px;"/>
			<col style="width:90px;"/>
			<col />
			<tr align="left">
				<td>员工编码</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>性别</td>
				<td>
					<select name="sex" style="width:40%;">
						<option value="0"></option>
						<option value="1">男</option>
						<option value="2">女</option>
					</select>
					<span style="color:red">*</span>
				</td>
			</tr>
			<tr align="left">
				<td>身份照号码</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>员工状态</td>
				<td>
					<select name="state" style="width:40%;">
						<option value="0"></option>
						<option value="1">是</option>
						<option value="2">否</option>
					</select>
					<span style="color:red">*</span>
				</td>
			</tr>
			<tr align="left">
				<td>籍贯</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>国籍</td>
				<td>
					<select name="sex" style="width:40%;">
						<option value="0"></option>
						<option value="1"></option>
						<option value="2"></option>
					</select>
					<span style="color:red">*</span>
				</td>
			</tr>
			<tr align="left">
				<td>姓名</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>出生日期</td>
				<td><input style="width:90px" class="easyui-datebox" id="date1" name="birthdate" type="text"></input></td>
			</tr>
			<tr align="left">
				<td>护照号码</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>血型</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" validType="length[1,50]"/></td>
			</tr>
			<tr align="left">
				<td>身高</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>人员类型</td>
				<td><select name="sex" style="width:40%;">
						<option value="0"></option>
						<option value="1"></option>
						<option value="2"></option>
					</select>
					<span style="color:red">*</span></td>
			</tr>
			<tr align="left">
				<td>健康状况</td>
				<td><select name="sex" style="width:40%;">
						<option value="0"></option>
						<option value="1"></option>
						<option value="2"></option>
					</select>
					<span style="color:red">*</span></td>
				<td>婚姻状况</td>
				<td><select name="sex" style="width:40%;">
						<option value="0"></option>
						<option value="1"></option>
						<option value="2"></option>
					</select>
					<span style="color:red">*</span></td>
			</tr>
			<tr align="left">
				<td>政治面貌</td>
				<td><select name="sex" style="width:40%;">
						<option value="0"></option>
						<option value="1"></option>
						<option value="2"></option>
					</select>
					<span style="color:red">*</span></td>
				<td>民族</td>
				<td><select name="sex" style="width:40%;">
						<option value="0"></option>
						<option value="1"></option>
						<option value="2"></option>
					</select>
					<span style="color:red">*</span></td>
			</tr>
			<tr align="left">
				<td>最高职称</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>最高学历</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" validType="length[1,50]"/></td>
			</tr>
			<tr align="left">
				<td>居住证有效期</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" validType="length[1,50]"/></td>
				<td>居住证签发日期</td>
				<td><input style="width:90px" class="easyui-datebox" id="date2" name="" type="text"></input><span style="color:red">*</span></td>
			</tr>
			<tr align="left">
				<td>居住证所在地</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>居住证到期日</td>
				<td><input style="width:90px" class="easyui-datebox" id="date3" name="" type="text"></input></td>
			</tr>
			<tr align="left">
				<td>居住证证件号</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>就业证签发日期</td>
				<td><input style="width:90px" class="easyui-datebox" id="date4" name="" type="text"></input></td>
			</tr>
			<tr align="left">
				<td>就业证有效期</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>就业证所在地</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" validType="length[1,50]"/></td>
			</tr>
			<tr align="left">
				<td>就业证到期日</td>
				<td><input style="width:90px" class="easyui-datebox" id="date5" name="" type="text"></input><span style="color:red">*</span></td>
				<td>就业证证件号</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" validType="length[1,50]"/></td>
			</tr>
			<tr align="left">
				<td>就业证签发日期</td>
				<td><input style="width:90px" class="easyui-datebox" id="date6" name="" type="text"></input><span style="color:red">*</span></td>
				<td>就业证有效期</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" validType="length[1,50]"/></td>
			</tr>
			<tr align="left">
				<td>就业证所在地</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>就业证到期日</td>
				<td><input style="width:90px" class="easyui-datebox" id="date7" name="" type="text"></input></td>
			</tr>
			<tr align="left">
				<td>职称证书证件号</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		</div>
		<div title="联系方式" style="overflow:auto;"> 
		<table class="edit_table">
			<col style="width:90px;"/>
			<col style="width:200px;"/>
			<col style="width:90px;"/>
			<col />
			<tr align="left">
				<td>住宅电话</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>手机号码</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
			</tr>
			<tr align="left">
				<td>E-Mail</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>家庭住址</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" validType="length[1,50]"/></td>
			</tr>
			<tr align="left">
				<td>通信地址</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>婚姻状况</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" validType="length[1,50]"/></td>
			</tr>
			<tr align="left">
				<td>健康状况</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>身份证地址</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" validType="length[1,50]"/></td>
			</tr>
			<tr align="left">
				<td>户口类型</td>
				<td><select name="sex" style="width:40%;">
						<option value="0"></option>
						<option value="1"></option>
						<option value="2"></option>
					</select>
					<span style="color:red">*</span></td>
				<td>户口所在地</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" validType="length[1,50]"/></td>
			</tr>
		</table>
		</div>
		<div title="职业信息" style="overflow:auto;">
		<table class="edit_table">
			<col style="width:90px;"/>
			<col style="width:200px;"/>
			<col style="width:90px;"/>
			<col />
			<tr align="left">
				<td>参加工作日期</td>
				<td><input style="width:90px" class="easyui-datebox" id="date8" name="" type="text"></input><span style="color:red">*</span></td>
				<td>加入公司日期</td>
				<td><input style="width:90px" class="easyui-datebox" id="date9" name="" type="text"></input></td>
			</tr>
			<tr align="left">
				<td>加入集团日期</td>
				<td><input style="width:90px" class="easyui-datebox" id="date10" name="" type="text"></input><span style="color:red">*</span></td>
				<td>入职日期</td>
				<td><input style="width:90px" class="easyui-datebox" id="date11" name="" type="text"></input></td>
			</tr>
			<tr align="left">
				<td>转正日期</td>
				<td><input style="width:90px" class="easyui-datebox" id="date12" name="" type="text"></input><span style="color:red">*</span></td>
				<td>离职原因</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/></td>
			</tr>
			<tr align="left">
				<td>离职日期</td>
				<td><input style="width:90px" class="easyui-datebox" id="date13" name="" type="text"></input><span style="color:red">*</span></td>
				<td>职级</td>
				<td><select name="sex" style="width:40%;">
						<option value="0"></option>
						<option value="1"></option>
						<option value="2"></option>
					</select>
					<span style="color:red">*</span></td>
			</tr>
			<tr align="left">
				<td>入职来源</td>
				<td><select name="sex" style="width:40%;">
						<option value="0"></option>
						<option value="1"></option>
						<option value="2"></option>
					</select>
					<span style="color:red">*</span></td>
				<td>试用期</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/></td>
			</tr>
			<tr align="left">
				<td>外职工龄</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>最后职等调整日期</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/></td>
			</tr>
			<tr align="left">
				<td>离退休日期</td>
				<td><input style="width:70%;" type="text" id="" name="" value="" class="easyui-validatebox" required="true" validType="length[1,50]"/><span style="color:red">*</span></td>
				<td>职等</td>
				<td><select name="sex" style="width:40%;">
						<option value="0"></option>
						<option value="1"></option>
						<option value="2"></option>
					</select>
					<span style="color:red">*</span></td>
			</tr>
		</table>
		</div>
		</div>
		<table>
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
</div>

<script type="text/javascript">
$(function(){
	for(var i=1;i<=13;i++){
	$('#date'+i).datebox({
		formatter: function(date){
			return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
		},   
		parser: function(date){
			return new Date(Date.parse(date.replace(/-/g,"/"))); 
		}
	}); 
	}
});
</script>
</body>
</html>