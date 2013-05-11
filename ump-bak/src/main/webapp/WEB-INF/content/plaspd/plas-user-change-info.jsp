<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理区域</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />
	<script language="javascript"  src="${ctx}/js/jquery.js"></script>
	<script language="javascript"  src="${ctx}/js/datePicker/WdatePicker.js" charset="UTF-8" ></script>
	<script language="javascript"  src="${ctx}/js/jquery.form.pack.js"></script>
	
	<!-- //加载jQuery类库 -->
	<script type="text/javascript" src="${ctx}/js/formValidator/jquery_last.js" charset="UTF-8" ></script>
	<!-- //加载插件  -->
	<script type="text/javascript" src="${ctx}/js/formValidator/formValidator.js" charset="UTF-8" ></script>
	<!-- //加载扩展库 -->
	<script type="text/javascript" src="${ctx}/js/formValidator/formValidatorRegex.js" charset="UTF-8" ></script>
	
	<!-- //加载插件的样式库，如果你是自动构建提示层，请加载validatorAuto.css -->
	<link type="text/css" rel="stylesheet" href="${ctx}/js/formValidator/style/validator.css"></link>
	
</head>

<body style="line-height: 22px;">
	<div style="padding-top:50px;">
		<%-- 
		<div>
			修改信息 
			<div><s:text name="uaapUaapUser.userInfoChange" /></div>
		</div>
		--%>
		<div class="userInfoChange" >
			<%-- 修改信息 --%>
			<s:form id="inputForm" action="plas-user-change!saveUserInfoChange.action" method="post">	
			<%-- 很重要 --%>
			<s:hidden id="uiid" name="uiid" key="uiid" ></s:hidden>
			<s:hidden id="userName" name="entityUser.userName" key="userName" ></s:hidden>
			
			<table style="margin-left: 100px;"> 
				<tr>
					<td style="width:100px">账号:</td>
					<td style="width:180px;">${entityUser.uiid}</td>
					<td style="width:220px"><div id="uiidTip" ></div></td>
				</tr>  
				<tr>
					<td>姓名:</td>
					<td align="left">${entityUser.userName}</td>
					<td><div id="userNameTip" ></div></td>
				</tr>				
				<tr>
					<td><s:text name="uaapUaapUser.email"/>:</td>
					<td><s:textfield key="email" id="email" name="entityUser.email" size="40" readonly="readonly"/></td>
					<td><div id="emailTip" ></div></td>
				</tr> 
				
				<tr>
					<td>固定电话:</td>
					<td><s:textfield key="fixedPhone" id="fixedPhone" name="entityUser.fixedPhone" size="40"/></td>
					<td><div id="fixedPhoneTip" ></div></td>
				</tr> 
				<tr>
					<td>手机号码:</td>
					<td><s:textfield key="mobilePhone" id="mobilePhone" name="entityUser.mobilePhone" size="40"/></td>
					<td>
						<div id="mobilePhoneTip" style="float:left;"></div>
						<div style="float:left;">只能填写一个最常用的号码</div>
					</td>
				</tr>  
				<tr>
					<td>其他电话:</td>
					<td><s:textfield key="mobilePhone2" id="mobilePhone2" name="entityUser.mobilePhone2" size="40"/></td>
					<td><div id="mobilePhone2Tip" ></div></td>
				</tr>  
				<%--
				<tr>
					<td><s:text name="uaapUaapUser.sexCd"/>:</td>
					<td>
						<select name="sexCd" id="sexCd">
							<option value="0" <s:if test="sexCd == 0 || sexCd == null"></s:if>>未知</option>
							<option value="1" <s:if test="sexCd == 1"></s:if>>男</option>
							<option value="2" <s:if test="sexCd == 2"></s:if>>女</option>
						</select>
					</td>
					<td><div id="sexCdTip" ></div></td>
				</tr> 
				<tr>
					<td><s:text name="uaapUaapUser.nationCd"/>:</td>
					<td><s:select list="@com.hhz.ump.util.DictMapUtil@getMapNation()" listKey="key" listValue="value"  id="nationCd" name="entityUser.nationCd" /></td>
					<td><div id="nationCdTip" ></div></td>
				</tr> 
				<tr>
					<td><s:text name="uaapUaapUser.idno"/>:</td>
					<td><s:textfield key="idno" id="idno" name="entityUser.idno" size="40" disabled="true"/></td>
					<td><div id="idnoTip" ></div></td>
				</tr> 
				 --%>
				<tr>
					<td><s:text name="uaapUaapUser.birthday"/>:</td>
					<td><s:textfield key="birthday" id="birthday" name="entityUser.birthday" size="40"  onfocus="WdatePicker()" cssClass="Wdate" /></td>
					<td><div id="birthdayTip" ></div></td>
				</tr> 
				<%--
				<tr>
					<td><s:text name="uaapUaapUser.nativeProvinceDesc"/>:</td>
					<td><s:textfield key="nativeProvinceDesc" id="nativeProvinceDesc" name="entityUser.nativeProvinceDesc" size="40"/></td>
					<td><div id="nativeProvinceDescTip" ></div></td>
				</tr> 
				<tr>
					<td><s:text name="uaapUaapUser.nativePlaceDesc"/>:</td>
					<td><s:textfield key="nativePlaceDesc" id="nativePlaceDesc" name="entityUser.nativePlaceDesc" size="40"/></td>
					<td><div id="nativePlaceDescTip" ></div></td>
				</tr> 
				<tr>
					<td><s:text name="uaapUaapUser.marrigeStatusCd"/>:</td>
					<td><s:select list="@com.hhz.ump.util.DictMapUtil@getMapMarrigeStatus()" listKey="key" listValue="value" id="marrigeStatusCd" name="entityUser.marrigeStatusCd" value="marrigeStatusCd==null?0:marrigeStatusCd"/></td>
					<td><div id="marrigeStatusCdTip" ></div></td>
				</tr>
				
				<tr>
					<td><s:text name="uaapUaapUser.gradSchoolDesc"/>:</td>
					<td><s:textfield key="gradSchoolDesc" id="gradSchoolDesc" name="entityUser.gradSchoolDesc" size="40"/></td>
					<td><div id="gradSchoolDescTip" ></div></td>
				</tr> 
				<tr>
					<td><s:text name="uaapUaapUser.majorDesc"/>:</td>
					<td><s:textfield key="majorDesc" id="majorDesc" name="entityUser.majorDesc" size="40"/></td>
					<td><div id="majorDescTip" ></div></td>
				</tr> 
				<tr>
					<td><s:text name="uaapUaapUser.attendWorkDate"/>:</td>
					<td><s:textfield key="attendWorkDate" id="attendWorkDate" name="entityUser.attendWorkDate" size="40" onfocus="WdatePicker()" cssClass="Wdate" /></td>
					<td><div id="attendWorkDateTip" ></div></td>
				</tr> 

				<tr>
					<td valign="top"><s:text name="uaapUaapUser.politicsCd"/>:</td>
					<td><s:select list="@com.hhz.ump.util.DictMapUtil@getMapPolitics()" listKey="key" listValue="value" name="entityUser.politicsCd" id="politicsCd" value="politicsCd==null?0:politicsCd"/></td>
					<td><div id="politicsCdTip" ></div></td>
				</tr>
				<tr>
					<td valign="top"><s:text name="uaapUaapUser.otherTypeCd"/>:</td>
					<td><s:select list="@com.hhz.ump.util.DictMapUtil@getMapOtherType()" listKey="key" listValue="value" id="otherTypeCd" name="entityUser.otherTypeCd" value="otherTypeCd==null?0:otherTypeCd"/></td>
					<td><div id="otherTypeCdTip" ></div></td>
				</tr>  
				<tr>
					<td valign="top"><s:text name="uaapUaapUser.remark"/>:</td>
					<td><s:textarea key="remark" id="remark" name="entityUser.remark" cssStyle="width:98%; overflow:auto;"></s:textarea></td>
					<td><div id="remarkTip" ></div></td>
				</tr> 
				--%>
					<tr align="center" height="50px">
						<td align="center" colspan="3">
							<input type="submit"  class="btn_blue" id="mySubmit" onclick="mysubmit()" value="提交" />
						</td>
					</tr>	
			</table>
			</s:form>
		</div>
	</div>
			
  

<script type="text/javascript"> 

	$.formValidator.initConfig({formid:"inputForm",onerror:function(msg){alert(msg);}});

	//固定电话号码
	$("#fixedPhone").formValidator({empty:true,onshow:"请输入固定电话号码",onfocus:"至多输入200个字符",oncorrect:"",onempty:"一定要填"}).inputValidator({min:0,max:200,onerror:"长度不正确,请确认"});
	//移动电话号码
	$("#mobilePhone").formValidator({empty:true,onshow:"请输入",onfocus:"必须11位",oncorrect:"",onempty:"提醒:若空将影响短信接收"}).inputValidator({min:11,max:11,onerror:" 手机号码必须是11位的,请确认"});//.regexValidator({regexp:"mobile",datatype:"enum",onerror:"你输入的手机号码格式不正确"});
	$("#mobilePhone2").formValidator({empty:true,onshow:"请输入",onfocus:"最多200个字",oncorrect:"",onempty:""}).inputValidator({min:0,max:200,onerror:"长度不正确,请确认"});
	//性别
	//$("input:radio[name='sexCd']").formValidator({empty:true,tipid:"sexCdTip",onshow:"请选择性别",onfocus:"请选择性别",oncorrect:"已选择"}).inputValidator({min:1,max:1,onerror:"未选择性别,请确认"}).defaultPassed();
	//民族
	//$("#nationCd").formValidator({empty:true,tipid:"nationCdTip",onshow:"",onfocus:"请选择民族",oncorrect:"已选择",onempty:""}).inputValidator({min:1,max:20,onerror:"长度不正确,请确认"});
	//身份证号码
	//$("#idno").formValidator({empty:false,onshow:"请输入身份证",onfocus:"请输入身份证",oncorrect:"已输入",onempty:"请输入身份证"});//.functionValidator({fun:isCardID});
	//$("#idno").formValidator({empty:false,onshow:"输入15或18位的身份证",onfocus:"输入15或18位的身份证",oncorrect:"已输入",onempty:"输入15或18位的身份证"}).functionValidator({fun:isCardID});
	//出生日期
	//$("#birthday").focus(function(){WdatePicker({skin:'whyGreen',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}});})	
	//				.formValidator({empty:true,onshow:"出生日期",onfocus:"出生日期,不能全部是0",oncorrect:"日期合法"}).inputValidator({min:"1900-01-01",type:"value",onerror:"日期必须大于\"1900-01-01\""}).defaultPassed();
	//籍贯
	//$("#nativeProvinceDesc").formValidator({empty:true,onshow:"",onfocus:"至多输入10个汉字或20个字符",oncorrect:"",onempty:""}).inputValidator({min:1,max:20,onerror:"长度不正确,请确认"});
	//户口所在地
	//$("#nativePlaceDesc").formValidator({empty:true,onshow:"",onfocus:"至多输入10个汉字或20个字符",oncorrect:"",onempty:""}).inputValidator({min:1,max:20,onerror:"长度不正确,请确认"});
	//婚姻状况
	//$("#marrigeStatusCd").formValidator({empty:true,tipid:"marrigeStatusCdTip",onshow:"",onfocus:"请选择婚姻状况",oncorrect:"已选择",onempty:""}).inputValidator({min:1,max:20,onerror:"长度不正确,请确认"});
	//专业
	//$("#majorDesc").formValidator({empty:true,onshow:"",onfocus:"至多输入100个汉字或200个字符",oncorrect:"",onempty:""}).inputValidator({min:1,max:200,onerror:"长度不正确,请确认"});
	//加入本单位时间
	//$("#attendWorkDate").focus(function(){WdatePicker({skin:'whyGreen',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}});})	
	//.formValidator({empty:true,onshow:"",onfocus:"请选择入司日期，不能全部是0",oncorrect:"日期合法"}).inputValidator({min:"1900-01-01",type:"value",onerror:"日期必须大于\"1900-01-01\""});
	
	//政治面貌
	//$("#politicsCd").formValidator({empty:true,tipid:"politicsCdTip",onshow:"",onfocus:"请选择政治面貌",oncorrect:"已选择",onempty:""}).inputValidator({min:1,max:20,onerror:"长度不正确,请确认"});
	//其他
	//$("#otherTypeCd").formValidator({empty:true,onshow:"可空",onfocus:"",oncorrect:"已选择"}).inputValidator({max:20,onerror:"长度不正确,请确认"});
	//备注
	//$("#remark").formValidator({empty:true,onshow:"可空",onfocus:"至多输入100个汉字或200个字符",oncorrect:"已输入"}).inputValidator({max:200,onerror:"长度不正确,请确认"});

	//MAc地址
	//$("#macAddress").formValidator({empty:true,onshow:"可以为空",onfocus:"至多200个字符",oncorrect:"已输入"}).inputValidator({max:200,onerror:"长度不正确,请确认"});
	
</script>
</body>
</html>
