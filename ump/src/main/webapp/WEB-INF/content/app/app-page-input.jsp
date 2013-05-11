<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理区域</title>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />

	<!-- //加载插件  -->
	<script type="text/javascript" src="${ctx}/js/formValidator/formValidator.js" charset="UTF-8" ></script>
	<!-- //加载扩展库 -->
	<script type="text/javascript" src="${ctx}/js/formValidator/formValidatorRegex.js" charset="UTF-8" ></script>
	
	<!-- //加载插件的样式库，如果你是自动构建提示层，请加载validatorAuto.css -->
	<link type="text/css" rel="stylesheet" href="${ctx}/js/formValidator/style/validator.css"></link>
</head>

<body>
	<s:form id="inputForm" action="app-page!save.action" method="post" theme="simple">
	<div id="content" align="center">
		<!-- <h4><s:if test="id == null"><s:text name="common.create"/></s:if>
			<s:else><s:text name="common.modify"/></s:else>
			<s:text name="appAppPage"/>
		</h4> -->
		
		<input type="hidden" name="id" value="${appPageId}" />
		<input type="hidden" name="pageCd" value="${pageCd}" />
		
		<table style="width:100%;" class="mainTable">
			<!-- 
			<tr  align="left">
				<td><s:text name="appAppPage.pageCd"/>:</td>
				<td><s:textfield name="pageCd" size="40" id="pageCd" /></td>
				<td><div id="pageCdTip" ></div></td>
			</tr>
			 -->
			 <tr>
				<td style="text-align: right;width:140px;">菜单名称:</td>
				<td>
					<input title="模糊检索菜单名称 " type="text" id="searchmenu" name="searchmenu" value="${menuName }" size="40" style="width:100%;" class="easyui-validatebox"  validType="length[1,50]" />
				</td>
			
			<!-- ??? -->
					<input id="menuIdSearch" name = "menuId" type="hidden" type="text" value="${menuId }"/>
				
				<td style="width:250px;">
					<%--
					(模糊搜索)
					<div id="searchmenuTip" ></div>
					 --%>
				
				</td>
				
			</tr>
			<tr>
				<td style="text-align: right;width:140px;"><s:text name="appAppPage.pageName"/>:</td>
				<td><s:textfield key="pageName" id="pageName" name="pageName" size="40" cssStyle="width:100%;"/></td>
				<td style="width:250px;"><div id="pageNameTip" ></div></td>
			</tr>
			<tr>
				<td style="text-align: right;"><s:text name="appAppPage.pagePath"/>:</td>
				<td><s:textfield key="pagePath" id="pagePath" name="pagePath" size="40" cssStyle="width:100%;"/></td>
				<td><div id="pagePathTip" ></div></td>
			</tr>
			<tr>
				<td style="text-align: right;"><s:text name="appAppPage.pageStatusCd"/>:</td>
				<!-- 
				<td><s:select list="mapEnabledFlg" listKey="key" listValue="value" id="pageStatusCd" name="pageStatusCd"/></td>
				 -->
				<td>
					<s:radio list="mapEnabledFlg" id="pageStatusCd" name="pageStatusCd" listKey="key" listValue="value" value="pageStatusCd==null?0:pageStatusCd"/>
				</td>
				<td><div id="pageStatusCdTip" ></div></td>
			</tr>
			<tr>
				<td style="text-align: right;"><s:text name="appAppPage.remark"/>:</td>
				<td><s:textarea key="remark" id="remark" name="remark" cssStyle="width:98%;"/></td>
				<td><div id="remarkTip" ></div></td>
			</tr>	
			<tr>
				<td colspan="1"  style="text-align: center;"></td>
				<td colspan="2"  style="text-align: left;">
					<s:submit cssClass="buttom" name="btnSave" id="btnSave" key="common.submit"/>
					<s:submit cssClass="buttom" name="btnReset" id="btnReset" key="common.reset"/>
					<s:if test="appPageId!=null">
					<input class="button" type="button"  onclick="delPage('${appPageId}');" value="删除" />
					</s:if>
					<input class="button" type="button"  onclick="window.history.back()" value="<s:text name="common.return"/>" />
					<%--
					<security:authorize ifAnyGranted="A_ADMIN"></security:authorize>
					 --%>
				</td>
			</tr>	
		</table>
	</div>
		</s:form>
	

	<script language="javascript"> 
	//快速搜索账号
	$('#searchmenu').quickSearch(
			'${ctx}/app/app-menu!searchMenu.action',
			['menuName','moduleName'],
			{menuId:'menuIdSearch',menuName:'searchmenu'},
			'',function(result){
				
			});
	
			$(document).ready(function() {
				//注册验证
				loadValidators();
				
				//聚焦第一个输入框
				$("#pageName").focus();
				//为inputForm注册validate函数
				$("#inputForm").validate({
					rules: {
						pageCd: {
							required: true,
							remote: "app-page!isTypeExists.action?oldPageCd=" + encodeURIComponent('${pageCd}')
						},
						pageName: "required"
						
					},
					messages: {
						pageCd: {
							remote: "该类型已经存在"
						}
						
					}
				});
			}); 




			function loadValidators(){
				
				$.formValidator.initConfig({formid:"inputForm",onerror:function(msg){}});
				//菜单名称
			//	$("#searchmenu").formValidator({onshow:"请选择菜单路径",oncorrect:"已输入",onempty:"一定要填哦"}).inputValidator({min:1,max:500,onerror:"一定要填哦"});

				//页面名称
				$("#pageName").formValidator({onshow:"请输入页面名称",onfocus:"至多输入25个汉字或50个字符",oncorrect:"已输入",onempty:"一定要填哦"}).inputValidator({min:1,max:50,onerror:"长度不正确,请确认"});

				//页面路径
				$("#pagePath").formValidator({onshow:"请输入页面路径",onfocus:"至多输入10个汉字或20个字符",oncorrect:"已输入",onempty:"一定要填哦"}).inputValidator({min:1,max:500,onerror:"长度不正确,请确认"});

				//显示顺序
				$("#dispOrderNo").formValidator({onshow:"请输入整数",oncorrect:"谢谢你的合作，你的整数正确"}).regexValidator({regexp:"intege",datatype:"enum",onerror:"整数格式不正确"});
				
				//页面是否可用
				$("input:radio[name='pageStatusCd']").formValidator({empty:true,tipid:"pageStatusCdTip",onshow:"请选择是否可用",onfocus:"请选择是否可用",oncorrect:"已选择"}).inputValidator({min:1,max:1,onerror:"未选择是否可用,请确认"}).defaultPassed();

				//备注
				$("#remark").formValidator({empty:true,onshow:"可以为空哦",onfocus:"至多输入100个汉字或200个字符",oncorrect:"已输入"}).inputValidator({max:200,onerror:"长度不正确,请确认"});
			}
	</script>
</body>
</html>