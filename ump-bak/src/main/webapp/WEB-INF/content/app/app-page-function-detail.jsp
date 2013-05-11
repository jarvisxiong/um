<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<table width="100%;" >
	<tr style="height:30px;">
		<td valign="top">
			<table style="width:100%;" >
				<tr>
					<td align="left" valign="middle" style="font-weight: bolder;width:150px"><s:text name="appAppFunction" />
						<s:if test="appFunctionId == null || appFunctionId == ''">
								<s:text name="appAppFunction.functionAdd" />
							</s:if>
					 </td>
					<td align="right" valign="middle">
							<!-- 操作提示 -->
							<span id="operate_result_tip" style="display: inline;"></span>
							<input id="btn_functionAdd" type="button" class="buttom" value='<s:text name="appAppFunction.functionAdd" />' onclick="functionAdd()"/>
							<s:if test="appFunctionId != null && appFunctionId != ''">
								<input id="btn_functionDelete" type="button" class="buttom" value='<s:text name="appAppFunction.functionDelete" />' onclick="functionDelete()"/>
							</s:if>
						</td>
					</tr>
			</table>
		</td>
	</tr> 
	<tr>
		<td align="left" valign="top">
	    <s:form id="inputForm" action="app-page-function!save.action" method="post">
	    <!-- very important! -->
	    <input type="hidden" name="id" value="${appFunctionId}" />
	   
		<table class="mainTable">
		
			<tr>
				<td style="width:150px;"><s:text name="appAppPage.pageName"/>:</td>
				<td>
					<!-- very important! -->
					<!-- if appPageId is null, display drop down list, else display name -->
					<input style="width:150px;" type="text" id="pageName" name="pageName" value="${pageName}" title="模糊检索菜单名称或页面名称"/>
					<input type="hidden" id="appPageId" name="appPageId" value="${appPageId}"/>
				</td>
				<td style="width:220px"><div id="appModuleIdTip" ></div></td>
			</tr>
			<tr  align="left">
				<td><s:text name="appAppFunction.functionBizCd"/>:</td>
				<td><s:textfield key="functionCd" name="functionCd" size="40" id="functionCd"/></td>
				<td><div id="functionCdTip"></div></td>
			</tr>
			<tr  align="left">
				<td><s:text name="appAppFunction.functionName"/>:</td>
				<td><s:textfield key="functionName" id="functionName" name="functionName" size="40"/></td>
				<td><div id="functionNameTip"></div></td>
			</tr>
			<tr  align="left">
				<td>资源路径:</td>
				<td><s:textfield key="functionPath" id="functionPath" name="functionPath" size="40"/></td>
				<td><div id="functionPathTip"></div></td>
			</tr>
			<tr  align="left">
				<td><s:text name="appAppFunction.functionTip"/>:</td>
				<td><s:textfield key="functionTip" id="functionTip" name="functionTip" size="40"/></td>
				<td><div id="functionTipTip"></div></td>
			</tr>
			<tr  align="left">
				<td><s:text name="appAppFunction.functionTypeCd"/>:</td>
				<td><s:radio list="@com.hhz.ump.util.DictMapUtil@getMapAppFunctionType()" listKey="key" listValue="value" name="functionTypeCd" id="functionTypeCd"/></td>
				<td><div id="functionTypeCdTip"></div></td>
			</tr>
			<tr  align="left">
				<td><s:text name="appAppFunction.dispOrderNo"/>:</td>
				<td><s:textfield key="dispOrderNo" id="dispOrderNo" name="dispOrderNo" size="40"/></td>
				<td><div id="dispOrderNoTip"></div></td>
			</tr> 
			<tr  align="left">
				<td><s:text name="appAppFunction.remark"/>:</td>
				<td><s:textarea key="remark" id="remark" name="remark" cssStyle="width:98%;"/></td>
				<td><div id="remarkTip"></div></td>
			</tr>	
			<tr  align="right">
				<td colspan="3">
					<s:submit cssClass="buttom" name="btnSave" id="btnSave" key="common.submit"/>
					<s:reset  cssClass="buttom" name="btnReset" id="btnReset" key="common.reset"/>
				</td>
			</tr>	
		</table>
		</s:form>
		</td>
	</tr>
</table>

<script type="text/javascript"> 
$(function(){
	//快速搜索(模糊匹配:orgBizCd,orgName,orgMgrId)
	$('#pageName').quickSearch(
		'${ctx}/app/app-page-function!quickSearchPageList.action',
		['moduleName','menuName','pageName','pageId'],
		{appPageId:'pageId',pageName:'pageName'},
		'',
		function(result){
			$('#appPageId').val(result.attr('pageId'));
		},
		{}
	);
	
 
	$.formValidator.initConfig({formid:"inputForm",onerror:function(msg){alert(msg);}});
	
	//页面名称
	//$("#appPageId").formValidator({onshow:"请选择对应页面",onfocus:"请选择对应页面",oncorrect:"已选择",onempty:"一定要选"}).inputValidator({min:1,max:50,onerror:"请选择页面"});
	
	//功能业务编号
	$("#functionCd").formValidator({onshow:"请输入功能业务编号",onfocus:"唯一,至多输入10个汉字或20个字符",oncorrect:"已输入",onempty:"不能填空"}).inputValidator({min:1,max:20,onerror:"长度不正确,请确认"})
	.ajaxValidator({
	    type : "get",
		url : "${ctx}/app/app-page-function!isFunctionCdExists.action?oldFunctionCd=" + encodeURIComponent('${functionCd}'),
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
		onerror : "该功能业务编号已使用，请更换",
		onwait : "正在进行  合法性校验，请稍候..."
	})
	.defaultPassed();

	//显示顺序
	$("#dispOrderNo").formValidator({onshow:"请输入整数",oncorrect:"谢谢你的合作，你的整数正确"}).regexValidator({regexp:"intege",datatype:"enum",onerror:"整数格式不正确"});
	
	//功能名称
	$("#functionName").formValidator({onshow:"请输入功能名称",onfocus:"唯一,至多输入25个汉字或50个字符",oncorrect:"已输入",onempty:"不能填空"}).inputValidator({min:1,max:20,onerror:"长度不正确,请确认"});

	//备注
	$("#remark").formValidator({empty:true,onshow:"可以填空",onfocus:"至多输入100个汉字或200个字符",oncorrect:"已输入",defaultvalue:""}).inputValidator({max:200,onerror:"长度不正确,请确认"});


});	 	
	//新增功能
	function functionAdd(){
		$("#function_detail_area").html('<div><image src="${ctx}/images/loading.gif"/>新增中...</div>');
		var url = '${ctx}/app/app-page-function!input.action';
		$.post(url, function(result) {
			$("#function_detail_area").html(result);
		});
	}
	//删除功能
	function functionDelete(){
		if(window.confirm('<s:text name="common.confirmDeleteFunction" />')){
			$("#function_detail_area").html('<div><image src="${ctx}/images/loading.gif"/>删除中...</div>');
			document.location= '${ctx}/app/app-page-function!delete.action?id=${appFunctionId}';
		}
	}

  	//TODO:
	//选择页面
	$("#btn_sel_page").bind("click", function(){
		var url = '${ctx}/app/sel-app-page.action';
		var sFeatures = 'dialogHeight:500px; dialogWidth:800px;center:yes;help:no;status:yes;scroll:yes;resizable=yes;';
		var returnValue = window.showModalDialog( url, 'newWindow', sFeatures); 
		//alert(returnValue);
		if(typeof returnValue == "undefined"){
			return;
		}
		var returnVal = returnValue.split(',');
		var appPageId = returnVal[0];
		var pageCd    = returnVal[1];
		var pageName  = returnVal[2];
 
		$('#appPageId').val(appPageId);
	});

</script>
