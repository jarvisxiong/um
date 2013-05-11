<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>假期管理添加</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
	<script language="javascript"  src="${ctx}/js/jquery.js"></script>

	<!-- jQuery类库/插件/扩展库/样式库 -->
	<link type="text/css" rel="stylesheet" href="${ctx}/js/formValidator/style/validator.css"></link>
	<script type="text/javascript" src="${ctx}/js/formValidator/formValidator.js" charset="UTF-8" ></script>
	<script type="text/javascript" src="${ctx}/js/formValidator/formValidatorRegex.js" charset="UTF-8" ></script>
	
	<link rel="stylesheet" id='skin' type="text/css" href="${ctx}/js/prompt/skin/qq/ymPrompt.css" />
	<script language="javascript" src="${ctx}/js/common.js"></script>
	<!-- detail页面,选择机构使用 -->
	<script language="javascript" src="${ctx}/js/prompt/ymPrompt.js" ></script>
	<script src="${ctx}/js/jquery.form.pack.js"  type="text/javascript"></script>
</head>

<body>

	<s:form id="inputForm" action="app-param!save.action" method="post" theme="simple">
	<div id="content" align="center">
		<h4><s:if test="id == null"><s:text name="common.create"/></s:if>
			<s:else><s:text name="common.modify"/></s:else>
			<s:text name="appAppParam"/>
			<s:hidden id="id" name="appParamId"></s:hidden>
		</h4>
		
		<table>
			<tr  align="left">
				<td style="width:140px;"><s:text name="appAppParam.paramCd"/>:</td>
				<td><s:textfield name="paramCd" size="40" id="paramCd" /></td>
				<td style="width:250px;"><div id="paramCdTip" ></div></td>
			</tr>
			<tr  align="left">
				<td><s:text name="appAppParam.paramName"/>:</td>
				<td><s:textfield key="paramName" id="paramName" name="paramName" size="40"/></td>
				<td><div id="paramNameTip" ></div></td>
			</tr>
			<tr  align="left">
				<td><s:text name="appAppParam.paramValue"/>:</td>
				<td><s:textfield key="paramValue" id="paramValue" name="paramValue" size="40"/></td>
				<td><div id="paramValueTip" ></div></td>
			</tr> 
			<tr  align="left">
				<td><s:text name="appAppParam.dispOrderNo"/>:</td>
				<td><s:textfield key="dispOrderNo" id="dispOrderNo" name="dispOrderNo" size="40"/></td>
				<td><div id="dispOrderNoTip" ></div></td>
			</tr> 
			<tr  align="left">
				<td><s:text name="appAppParam.defaultFlg"/>:</td>
				<td>
				<s:radio list="mapDefaultFlg" id="defaultFlg" name="defaultFlg" listKey="key" listValue="value" value="defaultFlg==null?0:defaultFlg"/>
				</td>
				<td><div id="defaultFlgTip" ></div></td>
			</tr> 
			<tr  align="left">
				<td><s:text name="appAppParam.remark"/>:</td>
				<td><s:textarea key="remark" id="remark" name="remark" cssStyle="width:98%;"/></td>
				<td><div id="remarkTip" ></div></td>
			</tr>	
			<tr  align="right">
				<td colspan="3">
					<input class="button" type="button"  onclick="history.back()" value="<s:text name="common.return"/>" />
						<s:submit cssClass="button" id="btnSave" name="btnSave" key="common.submit"/>
					<security:authorize ifAnyGranted="A_ADMIN">
					</security:authorize>
				</td>
			</tr>	
		</table>
	</div>
		</s:form>
	
	
	<script language="javascript"> 
		$.formValidator.initConfig({formid:"inputForm",onerror:function(msg){alert(msg);}});
		//参数cd
		$("#paramCd").formValidator({onshow:"请输入参数代码",onfocus:"唯一,至多输入25个汉字或50个字符",oncorrect:"已输入",onempty:"不能为空"}).inputValidator({min:1,max:30,onerror:"长度不正确,请确认"})
		.ajaxValidator({
		    type : "get",
			url : "${ctx}/app/app-param!isParamCdExists.action?oldParamCd=" + encodeURIComponent('${paramCd}'),
			datatype : "text",
			success : function(data){	
				if("true" == data){
					return true;
				}else{
					return false;
				}
			},
			buttons: $("#btnSave"),
			error: function(){alert("服务器没有返回数据，可能服务器忙，请重试");},		
			onerror : "该参数代码已使用，请更换",
			onwait : "正在进行参数代码合法性校验，请稍候..."
		})
		.defaultPassed();
		//参数名称
		$("#paramName").formValidator({onshow:"请输入参数名称",onfocus:"至多输入25个汉字或50个字符",oncorrect:"已输入",onempty:"一定要填哦"}).inputValidator({min:1,max:50,onerror:"长度不正确,请确认"})
		.ajaxValidator({
		    type : "get",
			url : "${ctx}/app/app-param!isParamNameExists.action?oldParamName=" + encodeURIComponent('${paramName}'),
			datatype : "text",
			success : function(data){	
				if("true" == data){
					return true;
				}else{
					return false;
				}
			},
			buttons: $("#btnSave"),
			error: function(){alert("服务器没有返回数据，可能服务器忙，请重试");},		
			onerror : "该参数名称已使用，请更换",
			onwait : "正在进行参数名称合法性校验，请稍候..."
		})
		.defaultPassed();
		
		//参数值
		$("#paramValue").formValidator({onshow:"请输入参数值",onfocus:"至多输入25个汉字或50个字符",oncorrect:"已输入",onempty:"一定要填哦"}).inputValidator({min:1,max:50,onerror:"长度不正确,请确认"});
	
		//显示序号
		$("#dispOrderNo").formValidator({empty:true,onshow:"请输入显示序号",onfocus:"请输入数字",oncorrect:"已输入",onempty:""}).inputValidator({min:1,max:50,onerror:"长度不正确,请确认"});
	
		//参数是否可用
		$("input:radio[name='defaultFlg']").formValidator({empty:true,tipid:"defaultFlgTip",onshow:"请选择是否可用",onfocus:"请选择是否可用",oncorrect:"已选择"}).inputValidator({min:1,max:1,onerror:"未选择是否可用,请确认"}).defaultPassed();
	
		//备注
		$("#remark").formValidator({empty:true,onshow:"可以为空哦",onfocus:"至多输入100个汉字或200个字符",oncorrect:"已输入"}).inputValidator({max:200,onerror:"长度不正确,请确认"});
	</script>
</body>
</html>