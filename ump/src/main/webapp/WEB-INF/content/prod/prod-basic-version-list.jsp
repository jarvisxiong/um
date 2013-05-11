<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>版本号管理</title>
	
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />  	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/prod/prod.css"  />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>		
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prod/prod-basic-version.js"></script>
</head>
<body>
	<div class="title_bar">
			<div>
				<div class="fLeft banTitle" >版本号管理</div>
				<div class="fLeft">当前激活版本号：<input id="activeVersionNo" value="<s:if test="entity!=null">${entity.versionNo}</s:if>"/></div>
			</div>
			<div class="fRight">
				<input type="button" class="btn_new btn_add_new" value="新增版本号" id="newVersion" style="width: 80px;"/>			
	  			<input type="button" class="btn_new btn_full_new" onclick="window.open(location.href)" value="全屏" />
	  			<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href" value="刷新" />				
			</div>
	</div>
	<div id="divBody">
		<form id="pageForm" action="${ctx}/prod/prod-basic-version!loadList.action" method="post" accept-charset="utf-8">
			<%--结果替换符 --%>
			<div id="rsTable" class="rsTable"></div>
		</form>			
	</div>
			
<script type="text/javascript">

$(function(){
	//新增版本号监听
	$("#newVersion").click(function(){
			basicVersionWin();
		});
	//表格行点击监听
	$(".prodTable tbody tr").live('click',function(){
			var basicVersionId=$(this).attr('basicVersionId');
			basicVersionWin(basicVersionId);				
		});
	//加载结果集
	loadProdBasicVersion();
});

//版本维护弹出框
function basicVersionWin(basicVersionId){
	var btn=[["保存",'ok'],["取消",'cancel']];
	if(basicVersionId)
		btn[2]=["删除",'del'];
	ymPrompt.confirmInfo({
		icoCls : "",
		autoClose:false,
		message : "<div id='basicVersion'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 350,
		height : 250,
		title : "版本号维护",
		closeBtn:true,
		afterShow : function() {
				var url=_ctx+"/prod/prod-version-detail!getForm.action";
				var data={formType:'basicVersionForm'};
				if(basicVersionId){
						data['basicVersionId']=basicVersionId;
					}
				$.post(url,data,function(result){		
					$("#basicVersion").html(result);
					autoHeight();	
					});
		},
		handler : function(btn){
			//叉叉的关闭事件
			$("div.ymPrompt_close").click(function(){					
				ymPrompt.close();
				});
			//其他事件
			if(btn=='ok'){
				if(validate()){
					if(basicVersionId){						
						saveBasicVersion();
						ymPrompt.close();	
						}
					else{
						//判断是否已经存在
						var url=_ctx+"/prod/prod-basic-version!hasExsitVersion.action";
						var data={};
						var versionTime=$("#versionTime").val();
						data={versionTime:versionTime};
						$.post(url,data,function(result){	
								var rs=result.split(",");
								if('false'==rs[0]){
									if(window.confirm(rs[1])){										
										saveBasicVersion();
										ymPrompt.close();	
										}
									}
								else{										
									saveBasicVersion();
									ymPrompt.close();
									}
							});
						}
					
				}
			}
			if(btn=='del'){
				//判断能否删除,如果可执行删除，则删除				
				canDelBasicVersion(basicVersionId);
				ymPrompt.close();		
				}
			if('cancel'==btn){
				ymPrompt.close();	
				}
		},			
		btn:btn
	});
	}
function validate(){
	//校验空
	if($("input#versionTime").val().trim().length<1){
		alert('时间必填！');
		return false;
		}
	//判断空字符
	var vn=$("#inp_versionNo").val().trim();
		vn=vn.replace(/(^\s*)|(\s*$)/g, "").replace(/(^　*)|(　*$)/g, "");
		 
	if(vn.length<1){
		alert('版本号必填！');
		return false;
		} 
	//描述判空
	vn=$("#inp_versionNo").val().trim();
	var length = vn.replace(/[^\x00-\xff]/g,"**").length; 	
	if(length>20){
		alert('版本号 不能超出20个字符！');
		return false;
		}
	return true;
}

//保存版本号
function saveBasicVersion(){
	TB_showMaskLayer("正在保存...");
	$('#newBasicVersionForm').ajaxSubmit(function(result){
		var rs=result.split(",");
		TB_removeMaskLayer();
		if('success'==rs[0]){
			//设置激活版本
			$("#activeVersionNo").val(rs[3]);
			loadProdBasicVersion();								
		}else{
			alert('提交数据存在异常！'+rs[1]);	
			}
	});	
}
function canDelBasicVersion(basicVersionId){
	var url=_ctx+"/prod/prod-basic-version!canDelete.action";
	var data={basicVersionId:basicVersionId};
	$.post(url,data,function(result){
		var rs=result.split(';');
		//如果可以删除,则执行删除
		if(rs[0]=='success'){
			if(window.confirm('确认删除？'))
				doDelBasicVersion(basicVersionId);				
			}
		//如果不能删除,则弹出提示
		else{
			alert(rs[1]);
			}	
		});
}

function doDelBasicVersion(basicVersionId){
	var url=_ctx+"/prod/prod-basic-version!delete.action";
	var data={basicVersionId:basicVersionId};
	$.post(url,data,function(result){
		var rs=result.split(',');
			if("success"==rs[0]){
				//设置激活版本
				$("#activeVersionNo").val(rs[1]);
				//alert('成功删除！');
				//加载结果集
				loadProdBasicVersion();
				}
			else{
				alert('未成功删除!');
				}
		});
}
</script>
</body>
</html>
