<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<table width="100%;">
	<tr style="height:30px;">
		<td valign="top">
			<table style="width:100%;" >
				<tr>
					<td align="left" valign="middle" style="font-weight: bolder;width:100px"><s:text name="appAppMenu" /> </td>
					<td align="right" valign="middle">
							<!-- 操作提示 -->
							<span id="operate_result_tip" style="display: inline;"></span>
							<input id="btn_menuAdd" type="button" class="buttom" value='<s:text name="appAppMenu.menuAdd" />' onclick="menuAdd()"/>
							<s:if test="appMenuId != null && appMenuId != ''">
								<input id="btn_menuDelete" type="button" class="buttom" value='<s:text name="appAppMenu.menuDelete" />' onclick="menuDelete()"/>
							</s:if>
						</td>
					</tr>
			</table>
		</td>
	</tr> 
	<tr>
		<td align="left" valign="top">
		 <s:form id="inputForm" action="app-module-menu-rel!save.action" method="post">
			    <!-- very important! -->
			    <input type="hidden" name="id" value="${appMenuId}" />
			    <input type="hidden" name="menuCd" value="${menuCd}" />
			    <input type="hidden" id="entityRealId" name=appMenuId value="${appMenuId}" />
			    <s:hidden id="entityTmpId" name="entityTmpId"/>
			    
				<table class="mainTable" >
					<tr>
						<td style="width:150px;"><s:text name="appAppModule.moduleName"/>:</td>
						<td>
							<!-- very important! -->
							<!-- if appModuleId is null, display drop down list, else display name -->
							<s:if test="appMenuId == null|| appMenuId == ''">
								<%--<s:select list="mapModule" listKey="key" listValue="value" id="appModuleId" name="appModuleId"/>--%>
							</s:if>
							<s:else>
							<%-- 	<p:code2name mapCodeName="mapModule" value="appModule.appModuleId"  />--%>
							</s:else>
						</td>
						<td style="width:200px"><div id="appModuleIdTip" ></div></td>
					</tr>
					
					<tr>
						<td><s:text name="appAppMenu.menuCd"/>:</td>
						<td>${menuCd}</td>
						<td><div id="menuCdTip" ></div></td>
					</tr>
					
					<tr>
						<td><s:text name="appAppMenu.menuName"/>:</td>
						<td><s:textfield key="menuName" id="menuName" name="menuName" size="40"/></td>
						<td><div id="menuNameTip" ></div></td>
					</tr>
					
					<tr>
						<td><s:text name="appAppMenu.menuTypeCd"/>:</td>
						<td><s:radio list="@com.hhz.ump.util.DictMapUtil@getMapAppMenuType()" id="menuTypeCd" name="menuTypeCd" listKey="key" listValue="value" value="menuTypeCd==null?1:menuTypeCd"/></td>
						<td><div id="menuTypeCdTip" ></div></td>
					</tr>
					<tr>
						<td><s:text name="appAppMenu.menuTip"/>:</td>
						<td><s:textfield key="menuTip" id="menuTip" name="menuTip" size="40"/></td>
						<td><div id="menuTipTip" ></div></td>
					</tr>
					<tr>
						<td><s:text name="appAppMenu.dispOrderNo"/>:</td>
						<td><s:textfield key="dispOrderNo" id="dispOrderNo" name="dispOrderNo" size="40"/></td>
						<td><div id="dispOrderNoTip" ></div></td>
					</tr>
					<tr>
						<td><s:text name="appAppPage.pageName"/>:</td>
						<td>
							<table style="width:100%;">
							<tr>
							<td>
								<!-- very important! -->
								<!-- if appModuleId is null, display drop down list, else display name -->
								<%--<s:select list="@com.hhz.ump.util.DictMapUtil@getMapAppPage()" listKey="key" listValue="value" id="appPageId" name="appPageId" value="appPage.appPageId"  onchange="changePageValue()"/>
							--%></td>
							<%--
							<td>
								<input id="btn_sel_page" type="button" value="<s:text name="common.select" />" />
							</td>
							 --%>
							</tr>
							</table>
						</td>
						<td><div id="appPageIdTip" ></div></td>
					</tr>
					<tr>
						<td><s:text name="appAppMenu.iconPath"/>:</td>
						<td>
							<a style="text-decoration: underline;color: #0167A2;" href="javascript: void(0);" onfocus="this.blur();" onclick="showUploadDiv(); return false;">+请点击上传照片</a>
						    
						</td>
						<td>
						<s:if test="%{iconPath != ''}">
				                      <s:url id="down" action="download" namespace="/app">
								  	  <s:param name="fileName">%{userPhotoImg.fileName}</s:param>
								  	  <s:param name="realFileName">%{userPhotoImg.realFileName}</s:param>
								  	  <s:param name="bizModuleCd">appMenu</s:param>
								  	  <s:param name="filterType">image</s:param>
									</s:url>
									<img style="height:20px;" id="userPersonalPic" src="${down}" alt="${realFileName}"/>
						</s:if>			
						</td>
					</tr>
					<tr>
						<td><s:text name="appAppMenu.leafFlg"/>:</td>
						<td><s:radio list="@com.hhz.ump.util.DictMapUtil@getMapEnabledFlg()" id="leafFlg" name="leafFlg" listKey="key" listValue="value" value="leafFlg==null?0:leafFlg"/></td>
						<td><div id="leafFlgTip" ></div></td>
					</tr>
					<tr>
						<td><s:text name="appAppMenu.fixFlg"/>:</td>
						<td><s:radio list="@com.hhz.ump.util.DictMapUtil@getMapEnabledFlg()" id="fixFlg" name="fixFlg" listKey="key" listValue="value" value="fixFlg==null?0:fixFlg"/></td>
						<td><div id="fixFlgTip" ></div></td>
					</tr>
					<tr>
						<td><s:text name="appAppMenu.sameWinFlg"/>:</td>
						<td><s:radio list="@com.hhz.ump.util.DictMapUtil@getMapEnabledFlg()" id="sameWinFlg" name="sameWinFlg" listKey="key" listValue="value" value="sameWinFlg==null?0:sameWinFlg"/></td>
						<td><div id="sameWinFlgTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="appAppMenu.parentMenuCd"/>:</td>
						<td><input type="text" id="parentMenuName" name="parentMenuName" value="${parentMenuName}"  onkeyup = "getMenuList(this);"/>
							<s:hidden key="parentMenuCd" id="parentMenuCd" name="parentMenuCd"/>
						</td>
						<td><div id="parentMenuCdTip" ></div></td>
					</tr>
					<tr>
						<td valign="top">图片名称:</td>
						<td><s:textarea key="remark" id="remark" name="remark" /></td>
						<td><div id="remarkTip" ></div></td>
					</tr>	
					<tr  align="right">
						<td colspan="3">
							<s:submit cssClass="buttom" id="btnSave" name="btnSave" key="common.submit"/>
							<s:reset cssClass="buttom" id="btnReset" name="btnReset" key="common.reset"/>
						</td>
					</tr>	
				</table>
				</s:form>
		
		</td>
	</tr>
</table>


<%-- 用于ajax搜索后显示结果列表 --%>
<div id="popDiv" class="popDiv"></div>

<script type="text/javascript"> 
 
	$.formValidator.initConfig({formid:"inputForm",onerror:function(msg){alert(msg)}});
	//模块名称
	var appModuleId = $("#appModuleId");
	if( appModuleId != null){
		appModuleId.formValidator({onshow:"请选择模块名称",onfocus:"",oncorrect:"已选择",onempty:"一定要选"}).inputValidator({min:1,max:20,onerror:"长度不正确,请确认"});
	}
	//菜单名称
	$("#menuName").formValidator({onshow:"请输入菜单名称",onfocus:"至多输入25个汉字或50个字符",oncorrect:"已输入",onempty:"一定要填"}).inputValidator({min:1,max:50,onerror:"长度不正确,请确认"})
	.ajaxValidator({
	    type : "get",
		url : "${ctx}/app/app-module-menu-rel!isMenuNameExists.action?oldMenuName=" + encodeURIComponent('${menuName}'),
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
		onerror : "该菜单名称已使用，请更换",
		onwait : "正在进行 菜单名称 合法性校验，请稍候..."
	}).defaultPassed();
	
	//菜单类型
	$("input:radio[name='menuTypeCd']").formValidator({tipid:"menuTypeCdTip",onshow:"请选择菜单类型",onfocus:"请选择菜单类型",oncorrect:"已选择"}).inputValidator({min:1,max:1,onerror:"未选择菜单类型,请确认"}).defaultPassed();

	//菜单提示
	$("#menuTip").formValidator({empty:true,onshow:"",onfocus:"至多输入50个汉字或100个字符",oncorrect:"已输入",onempty:"您真的不填吗?"}).inputValidator({min:1,max:100,onerror:"长度不正确,请确认"});

	//显示序号
	$("#dispOrderNo").formValidator({onshow:"请输入整数",oncorrect:"谢谢你的合作，你的整数正确"}).regexValidator({regexp:"intege",datatype:"enum",onerror:"整数格式不正确"});

	//页面名称
	$("#pageName").formValidator({onshow:"请选择对应页面",onfocus:"请选择页面",oncorrect:"已选择",onempty:"您真的不填吗?"}).inputValidator({min:1,max:50,onerror:"长度不正确,请确认"});

	//图标路径
	$("#iconPath").formValidator({empty:true,onshow:"",onfocus:"请选择图标路径",oncorrect:"已选择",onempty:"您真的不填吗?"}).inputValidator({min:1,max:500,onerror:"长度不正确,请确认"});

	//是否叶子节点
	$("input:radio[name='leafFlg']").formValidator({empty:true,tipid:"leafFlgTip",onshow:"请选择是否叶子节点",onfocus:"请选择是否叶子节点",oncorrect:"已选择"}).inputValidator({min:1,max:20,onerror:"未选择是否叶子节点,请确认"}).defaultPassed();

	//是否允许修改
	$("input:radio[name='fixFlg']").formValidator({empty:true,tipid:"fixFlgTip",onshow:"请选择是否允许修改",onfocus:"请选择是否允许修改",oncorrect:"已选择"}).inputValidator({min:1,max:20,onerror:"未选择是否允许修改,请确认"}).defaultPassed();

	//是否在同一窗口弹出打开
	$("input:radio[name='sameWinFlg']").formValidator({empty:true,tipid:"sameWinFlgTip",onshow:"请选择是否在同一窗口弹出打开",onfocus:"请选择是否在同一窗口弹出打开",oncorrect:"已选择"}).inputValidator({min:1,max:20,onerror:"未选择是否允许修改,请确认"}).defaultPassed();

	//备注
	$("#remark").formValidator({empty:true,onshow:"可以为空",onfocus:"至多输入100个汉字或200个字符",oncorrect:"已输入"}).inputValidator({max:200,onerror:"长度不正确,请确认"});

	 
	//新增菜单
	function menuAdd(){
		$("#menu_detail_area").html('<div><image src="${ctx}/images/loading.gif"/>新增中...</div>');
		var url = '${ctx}/app/app-menu!input.action';
		$.post(url, function(result) {
			$("#menu_detail_area").html(result);
		});
	}

	//删除菜单
	function menuDelete(moduleId,menuId){
		if(window.confirm('<s:text name="common.confirmDeleteMenu" />')){
			$("#function_detail_area").html('<div><image src="${ctx}/images/loading.gif"/>删除中...</div>');
			document.location= '${ctx}/app/app-menu!delete.action?id=${appModuleMenuRelId}';
		}
	}

	//选择页面时,若菜单名称为空,则默认同页面名称
	function changePageValue(){
		//TODO
		//var txt = $("#appPageId option[selected=true]").text();
		//alert(txt);
	}

	// 选择照片
	// display a div to upload a photo after clicking the link.
	function showUploadDiv() {
		ymPrompt.confirmInfo({
			icoCls:"",
			title:"请选择一张照片上传",
			message:"<div id='photoDiv'><img align='absMiddle' src='${ctx}/images/loading.gif'></div>",
			useSlide:true,
			winPos:"t",
			width:400,
			height:150,
			maxBtn:true,
			allowRightMenu:true,
			handler: uploadUserPhoto,
			afterShow:function(){
				var entityId = "${appMenuId}";
				if (!entityId || jQuery.trim(entityId).length == 0) {
					entityId = $("#entityTmpId").val();
				}
				url = "${ctx}/app/app-menu!uploadPhoto.action?bizEntityId=" + entityId;
				$.get(url,
					function(result){
						$("#photoDiv").html(result);
				});
			}
		});
	}

	// 上传图片
	// upload user's photo and refresh the image after uploading.
	function uploadUserPhoto(tp) {
		if(tp == "ok") {
			if (jQuery.trim($("#photoFile").val()).length > 0) {
				$("#photoForm").ajaxSubmit(function(result) {
					if (result) {
						var entityId = $("#photoForm input[name='bizEntityId']").val();
						$.get("${ctx}/app/app-menu!fetchJSONPhotoInfo.action?id=" + entityId, function(json) {
							var j = eval("(" + json + ")");
							var fileName = j.fileName;
							var realFileName = j.realFileName;
							var src = "${ctx}/app/download.action?fileName=" + fileName + "&realFileName=" + realFileName + "&bizModuleCd=uaapUser&filterType=image";
							$("#userPersonalPic").hide();
							$("#userPersonalPic").attr("src", src).attr("realFileName", realFileName).fadeOut();
						});
					}
				});
			}
		}
	}


	
	var menuDivMgr;
	function getMenuList(srcElem){

		if(menuDivMgr)clearTimeout(menuDivMgr);
		menuDivMgr = setTimeout(function(){
			var menuName = $(srcElem).val();
			$(srcElem).val('');
			$("#parentMenuCd").val('');
			$("#popDiv").html('').addClass("loading");
			$.post('${ctx}/app/app-menu!searchMenuList.action',{menuName : menuName},function(result){
				$("#popDiv").removeClass("loading");
				var $offset = $(srcElem).offset();
				$("#popDiv").css({left:($offset.left),top:($offset.top+5+$(srcElem).height())}).empty().show();

				result = eval(result);
				var arr = new Array();
				$.each(result,function(i,node){
					if($("#menuCd").val() != node.menuCd){
						arr.push("<div menuCd='"+node.menuCd+"' menuName='"+ node.menuName +"'><span>"+ node.menuName +"</span></div>");
					}
				});
				if( arr.length ==0){
					$("#popDiv").append(arr.join("查无菜单!"));
				}else{
					$("#popDiv").append(arr.join(""));
				}
				$("#popDiv div").click(function(){
					var menuCd = $(this).attr("menuCd");
					var menuName = $(this).attr("menuName");
 					$(srcElem).val(menuName);
 					$("#parentMenuCd").val(menuCd);
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
