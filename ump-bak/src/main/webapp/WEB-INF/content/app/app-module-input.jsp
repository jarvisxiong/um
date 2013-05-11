<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理区域</title>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css" />

	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/app/app.css"></link>
	

	<!-- //加载插件  -->
	<script type="text/javascript" src="${ctx}/js/formValidator/formValidator.js" charset="UTF-8" ></script>
	<!-- //加载扩展库 -->
	<script type="text/javascript" src="${ctx}/js/formValidator/formValidatorRegex.js" charset="UTF-8" ></script>
	
	<!-- //加载插件的样式库，如果你是自动构建提示层，请加载validatorAuto.css -->
	<link type="text/css" rel="stylesheet" href="${ctx}/js/formValidator/style/validator.css"></link>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
</head>

<body>

<s:form id="inputForm" action="app-module!save.action" method="post">
<div id="content" align="center" >
	<h4><s:if test="id == null"><s:text name="common.create"/></s:if>
		<s:else><s:text name="common.modify"/></s:else>
		<s:text name="appAppModule"/>
	</h4>
	
	
	<!-- 很重要! -->
	<input type="hidden" name="id" value="${appModuleId}" />
	<input type="hidden" id="moduleCd" name="moduleCd" value="${moduleCd}" />
	
	<table align="center" width="100%">
		<colgroup>
			<col width="80px"></col>
			<col></col>
			<col width="250px"></col>
		</colgroup>
		<!-- 
		<tr align="left">
			<td><s:text name="appAppModule.moduleCd"/>:</td>
			<td><s:textfield name="moduleCd" size="40" id="moduleCd" /></td>
		</tr>
		 -->
		<tr align="left">
			<td valign="top" style="text-align: right;padding-right:5px;"><s:text name="appAppModule.moduleName"/>:</td>
			<td><s:textfield key="moduleName" id="moduleName" name="moduleName" /></td>
			<td><div id="moduleNameTip" ></div></td>
		</tr>
		<tr align="left">
			<td valign="top" style="text-align: right;padding-right:5px;"><s:text name="appAppModule.moduleTip"/>:</td>
			<td><s:textarea key="moduleTip" id="moduleTip" name="moduleTip"  cssStyle="width:98%;overflow:auto;"/></td>
			<td><div id="moduleTipTip" ></div></td>
		</tr>	
		<!-- 页面名称 -->
		<tr align="left">
			<td valign="top" style="text-align: right;padding-right:5px;"><s:text name="appAppPage.pageName"/>:</td>
			<td><input type="text" id="pageName" name="pageName" value="${pageName}"  />
				<input value="${pageCd }" id="pageCd" name="pageCd" type="hidden" cssStyle="width:98%;"/>
				<span>(请选择)</span>
			</td>
			<td><div id="pageCdTip" ></div></td>
		</tr>	
		<!-- 模块名称 -->
		<tr align="left">
			<td valign="top" style="text-align: right;padding-right:5px;"><s:text name="appAppModule.parentModuleCd"/>:</td>
			<td><input type="text" id="parentModuleName" name="parentModuleName" value="${parentModuleName}"  onkeyup ="getModuleList(this);"/>
				<s:hidden key="parentModuleCd" id="parentModuleCd" name="parentModuleCd"  cssStyle="width:98%;"/>
				<span>(请选择)</span>
			</td>
			<td><div id="parentModuleCdTip" ></div></td>
		</tr>	
		<tr align="left">
			<td valign="top" style="text-align: right;padding-right:5px;"><s:text name="appAppModule.dispOrderNo"/>:</td>
			<td><s:textfield key="dispOrderNo" id="dispOrderNo" name="dispOrderNo" /></td>
			<td><div id="dispOrderNoTip" ></div></td>
		</tr>
		<tr align="left">
			<td valign="top" style="text-align: right;padding-right:5px;">图片名称:</td>
			<td><s:textarea key="remark" id="remark" name="remark" cssStyle="width:98%;overflow:auto;"/></td>
			<td><div id="remarkTip" ></div></td>
		</tr>	
		<tr align="center">
			<td colspan="3">
				<s:submit cssClass="buttom" name="btnSave" id="btnSave" key="common.submit"/>
				<s:reset cssClass="buttom" name="btnReset" id="btnReset" key="common.reset"/>
				<input type="button" class="buttom" name="btnGoBack" onclick="document.location='${ctx}/app/app-module.action'" value="返回" />
				<%--
				<input class="button" type="button"  onclick="history.back()" value="<s:text name="common.return"/>" />
				<security:authorize ifAnyGranted="A_ADMIN"></security:authorize>
				 --%>
			</td>
		</tr>	
	</table>
</div>
</s:form>


	<%-- 用于ajax搜索后显示结果列表 --%>
	<div id="popDiv" class="popDiv"></div>
	
<script type="text/javascript"> 

	$(document).ready(function(){
		loadValidators();
		//注册快速搜索(pageName)
		$('#pageName').quickSearch(
			'${ctx}/app/app-page!searchPageList.action',
			['pageName','pageCd'],
			{pageName:'pageName',pageCd:'pageCd'},
			'',
			function(result){
				//refreshUserArea(result.attr('pageCd'));
			}
		);
	}); 


	function loadValidators(){

		$.formValidator.initConfig({formid:"inputForm",onerror:function(msg){alert(msg)}})
		//模块名称
		$("#moduleName").formValidator({onshow:"请输入模块名称",onfocus:"至多输入25个汉字或50个字符",oncorrect:"已输入",onempty:"一定要填"}).inputValidator({min:1,max:50,onerror:"长度不正确,请确认"})
		.ajaxValidator({
		    type : "get",
			url : "${ctx}/app/app-module!isModuleNameExists.action?oldModuleName=" + encodeURIComponent('${moduleName}'),
			datatype : "text",
			success : function(data){	
				//alert("data:["+data+"]");
				if("true" == data){
					return true;
				}else{
					return false;
				}
			},
			buttons: $("#btnSave"),
			error: function(){alert("服务器没有返回数据，可能服务器忙，请重试");},		
			onerror : "该模块名称已使用，请更换",
			onwait : "正在进行 模块名称 合法性校验，请稍候..."
		})
		.defaultPassed();
		//模块提示
		$("#moduleTip").formValidator({empty:true,onshow:"可以为空",onfocus:"至多输入10个汉字或20个字符",oncorrect:"已输入"}).inputValidator({max:100,onerror:"长度不正确,请确认"});
		//显示序号
		$("#dispOrderNo").formValidator({onshow:"请输入整数",oncorrect:"谢谢你的合作，你的整数正确"}).regexValidator({regexp:"intege",datatype:"enum",onerror:"整数格式不正确"});
		//备注
		$("#remark").formValidator({empty:true,onshow:"可以为空",onfocus:"至多输入100个汉字或200个字符",oncorrect:"已输入"}).inputValidator({max:200,onerror:"长度不正确,请确认"});
		
	}

	var pageDivMgr;
	function getPageList(srcElem){

		if(pageDivMgr)clearTimeout(pageDivMgr);
		pageDivMgr = setTimeout(function(){
			var pageName = $(srcElem).val();
			$(srcElem).val('');
			$("#pageCd").val('');
			$("#popDiv").html('').addClass("loading");
			$.post('${ctx}/app/app-page!searchPageList.action',{pageName : pageName},function(result){
				$("#popDiv").removeClass("loading");
				var $offset = $(srcElem).offset();
				$("#popDiv").css({left:($offset.left),top:($offset.top+5+$(srcElem).height())}).empty().show();

				result = eval(result);
				var arr = new Array();
				$.each(result,function(i,node){
					arr.push("<div pageCd='"+node.pageCd+"' pageName='"+ node.pageName +"'><span>"+ node.pageName +"</span></div>");
				});
				if( arr.length ==0){
					$("#popDiv").append(arr.join("查无菜单!"));
				}else{
					$("#popDiv").append(arr.join(""));
				}
				$("#popDiv div").click(function(){
					var pageCd = $(this).attr("pageCd");
					var pageName = $(this).attr("pageName");
 					$(srcElem).val(pageName);
 					$("#pageCd").val(pageCd);
					$("#popDiv").slideUp(200);
				});
				/*
				$("#popDiv").mouseleave(function(){
					$("#popDiv").slideUp(200);
				});
				*/
			});
		}, 300);
			
		$(document).bind('click',function(event){
			var event  = window.event || event;
		    var obj = event.srcElement ? event.srcElement : event.target;
		    //非自己或浮出框
		    if( obj != srcElem && obj != $("#popDiv")){
		    	$("#popDiv").slideUp(200); 
		    }
		    $(document).unbind('click');
		});
	}

	var moduleDivMgr;
	function getModuleList(srcElem){

		if(moduleDivMgr)clearTimeout(moduleDivMgr);
		moduleDivMgr = setTimeout(function(){
			var moduleName = $(srcElem).val();
			$(srcElem).val('');
			$("#parentModuleCd").val('');
			$("#popDiv").html('').addClass("loading");
			$.post('${ctx}/app/app-module!searchModuleList.action',{moduleName : moduleName},function(result){
				$("#popDiv").html('').removeClass("loading");
				var $offset = $(srcElem).offset();
				$("#popDiv").css({left:($offset.left),top:($offset.top+5+$(srcElem).height())}).empty().show();

				result = eval(result);
				var arr = new Array();
				$.each(result,function(i,node){
					if($("#moduleCd").val() != node.moduleCd){
						arr.push("<div moduleCd='"+node.moduleCd+"' moduleName='"+ node.moduleName +"'><span>"+ node.moduleName +"</span></div>");
					}
				});
				if( arr.length ==0){
					$("#popDiv").append(arr.join("查无模块!"));
				}else{
					$("#popDiv").append(arr.join(""));
				}
				$("#popDiv div").click(function(){
					var moduleCd = $(this).attr("moduleCd");
					var moduleName = $(this).attr("moduleName");
 					$(srcElem).val(moduleName);
 					$("#parentModuleCd").val(moduleCd);
					$("#popDiv").slideUp(200);
				});
				/*
				$("#popDiv").mouseleave(function(){
					$("#popDiv").slideUp(200);
				});
				*/
			});
		}, 300);
			
		$(document).bind('click',function(event){
			var event  = window.event || event;
		    var obj = event.srcElement ? event.srcElement : event.target;
		    //非自己或浮出框
		    if( obj != srcElem && obj != $("#popDiv")){
		    	$("#popDiv").slideUp(200); 
		    }
		    $(document).unbind('click');
		});
	}
</script>
</body>
</html>