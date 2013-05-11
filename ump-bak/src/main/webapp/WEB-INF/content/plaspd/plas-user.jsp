<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理区域</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
	<script language="javascript"  src="${ctx}/js/jquery.js"></script>
	
	<!-- TreePanel机构树 -->
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" />
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>

	<!-- jQuery类库/插件/扩展库/样式库 -->
	<link type="text/css" rel="stylesheet" href="${ctx}/js/formValidator/style/validator.css"></link>
	<script type="text/javascript" src="${ctx}/js/formValidator/formValidator.js" charset="UTF-8" ></script>
	<script type="text/javascript" src="${ctx}/js/formValidator/formValidatorRegex.js" charset="UTF-8" ></script>
	
	<link rel="stylesheet" id='skin' type="text/css" href="${ctx}/js/prompt/skin/qq/ymPrompt.css" />
	<script language="javascript" src="${ctx}/js/common.js"></script>
	<!-- detail页面,选择机构使用 -->
	<script language="javascript" src="${ctx}/js/prompt/ymPrompt.js" ></script>
	<script language="javascript" src="${ctx}/js/jquery.form.pack.js"  ></script>
	
	<!-- 日历 -->
	<script language="javascript" src="${ctx}/js/datePicker/WdatePicker.js" charset="UTF-8" ></script>
</head>

<body>

	<div style="padding:5px 3px 5px 5px;">
	<table style="table-layout: fixed;width: 100%;" >
		<tr>
			<td style="width:200px;border: 1px solid #c5e3fc;" valign="top"><%--height: 470px; --%>
				<table style="margin: 5px;">
					<tr>
						<td style="font-weight: bolder;" valign="top">
							<table style="width:100%;">
							<tr>
							<td style="width:80px;" align="left"><s:text name="common.configUaapUser" /></td>
							<td align="right">
							</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td valign="top">
							<div id="org_tree_content" style="overflow-x:hidden; overflow-y: auto; height: 450px;"><%-- --%>
								<!-- 这里是ajax加载treepanel树 -->
								<div id="physical_panel" style="display: block;"></div>
							</div>
						</td>
					</tr>			
				</table>
			</td>
			<td valign="top" id="user_detail_area"><!-- 这里是编辑的机构内容 -->
				<%@ include file="plas-user-detail.jsp"%>
			</td>
		</tr>
	</table>  
	
	</div>
	
	
	<script language="javascript">
	 
		//逻辑视图 或 物理视图(首页)
		var treePhysical;
		$("#physical_panel").html('<div><image src="${ctx}/images/loading.gif"/>加载机构人员树(物理)...</div>');
 
		//物理
		$.post("${ctx}/plaspd/plas-user!loadPhysicalTreeData.action", function(result) {
			$("#physical_panel").html('');
			if (result) {
				treePhysical = new TreePanel({
					renderTo: "physical_panel",
					'root'  : eval('('+result+')'),
					'ctx'	:'${ctx}'
				}); 
				//自定义
				treePhysical.isDelegateIcon = true;
				treePhysical.delegateGetDelegateIcon = function(node){
					return node.iconPath;
				};
				treePhysical.render(); 
				//修饰所有节点
				for(var k in treePhysical.nodeHash){
					var node = treePhysical.nodeHash[k];
					var orgOrUser = node.attributes.orgOrUser;
					if( orgOrUser== "0"){
						switch(node.attributes.sexCd){
							case '1':node.iconPath = _ctx + "/images/webim/male_online.jpg"; break;
							case '2':node.iconPath = _ctx + "/images/webim/female_online.jpg"; break;
							default :node.iconPath = _ctx + "/images/webim/none_online.jpg"; break;
						}
					}
					if( orgOrUser== "1" && node.isLeaf()){
						node.iconPath = _ctx + "/images/imgTree/page.gif";
					}
				}
				treePhysical.on(function(node){
					var id  = node.attributes.entityId;
					if( $.trim(id) == '' || $.trim(id)=='0'){
						return;
					}
					
					var orgOrUser = node.attributes.orgOrUser;
					if(orgOrUser == "1"){//机构
						if(node.isExpand){
							node.collapse();
						}else{
							node.expand();
						}
					}
					if(orgOrUser == "0"){//用户
						refreshUserArea(id);
					}
				});
			}
		});

		function refreshUserArea(id){
			$("#user_detail_area").html('<div><image src="${ctx}/images/loading.gif"/>搜索人员中...</div>');
			var url = "${ctx}/plaspd/plas-user!input.action?id="+id;
			//编辑人员信息
			$.post(url, function(result) {
				$("#user_detail_area").html(result);
			});
		}
	</script>
	 
</body>
</html>
