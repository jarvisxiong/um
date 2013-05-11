<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>规则检查</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common/treePanel.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js" ></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
	
	<script type="text/javascript" src="${ctx}/js/common/treePanel.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.quickSearch.js"></script>
</head>
<body class="easyui-layout">

	<%--
	<div region="west" split="true"  style="width:240px;">
		<div id="leftPanel" style="height:100%;">
			<div id="leftTree"></div>
		</div>
	</div>
	 --%>
	<div region="center" split="true" style="+position: relative;overflow-x:hidden; padding:0 5px;">
		<div style="padding:5px 0;">
			
		</div>
		<div>
			<div>
				<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="searchUnMachAcctList()">刷新</a>
				<span style="margin:0 10px;">
				说明:检查持有职位与职员所属的机构不一致的账号
				</span>
			</div>  
			
			<div style="height:520px;">
		 	<table id="tt" 
			 	fit="true"
				title="匹配" singleSelect="true" rownumbers="true"
				idField="acctId" url="${ctx}/plas/plas-validate!unMatchAcctList.action">
			</table>
			</div>
		</div>
	</div> 
	 
	 <script type="text/javascript">

	 	$(function(){
			init();
		});
		function init(){
			$('#tt').datagrid({
				pagination:true,
				pageSize:30,
				pageList:[50,40,30,20,10],
				columns:[[ 
					{field:'uiid',title:'账号', sortable:true, width:80,
						formatter:function(value,row,index){
							return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
						}
					},
					{field:'statusCd',title:'账号状态', sortable:true, width:80,
						formatter:function(value,row,index){
							var t = '';
							switch(value){
								case '0': t='未启用';break;
								case '1': t='正常';break;
								case '2': t='冻结';break;
								case '4': t='注销';break;
								default: t= value || '-其他';break;
							}
							return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+t+'">'+t+'</div>';
						}
					},
					{field:'userName',title:'姓名', sortable:true, width:60,
						formatter:function(value,row,index){
							return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
						}
					},
					/*
					{field:'sexCd',title:'性别', sortable:true, width:50,
						formatter:function(value,row,index){
							var t = '';
							switch(value){
								case '0': t='未知';break;
								case '1': t='男';break;
								case '2': t='女';break;
								default: t= value || '-其他';break;
							}
							return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+t+'">'+t+'</div>';
						}
					},
					*/
					{field:'serviceStatusCd',title:'是否在职', sortable:true, width:60,
						formatter:function(value,row,index){
							var t = '';
							switch(value){
								case '1': t='在职';break;
								case '2': t='辞退';break;
								case '4': t='离职';break;
								default: t= value || '-其他';break;
							}
							return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+t+'">'+t+'</div>';
						}
					},
					/*
					{field:'mobilePhone',title:'联系电话', sortable:true, width:80,
						formatter:function(value,row,index){
							return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
						}
					},
					*/
					{field:'applyStatusCd',title:'账号审核', sortable:true, width:60,
						formatter:function(value,row,index){
							var t = '';
							switch(value){
								case '0': t='待审批';break;
								case '1': t='通过';break;
								case '2': t='驳回';break;
								default: t= value || '-其他';break;
							}
							return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+t+'">'+t+'</div>';
						}
					},
					{field:'sysPosName',title:'职位名称', sortable:false, width:100,
						formatter:function(value,row,index){
							return '<div id="div_org_'+row.sysPositionId+'" style="color:red;overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
						}
					},
					{field:'orgName',title:'职员所在机构', sortable:false, width:100,
						formatter:function(value,row,index){
							var t = value+'('+row.posOrgCd+')';
							return '<div id="div_org_'+row.sysPositionId+'" style="color:red;overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+t+'">'+t+'</div>';
						}
					},
					{field:'posOrgName',title:'职位所在机构', sortable:true, width:90,
						formatter:function(value,row,index){
							var t = value+'('+row.orgCd+')';
							return '<div id="div_pos_org_'+row.sysPositionId+'" style="color:red;overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+t+'">'+t+'</div>';
						}
					},
					{field:'posOrgId',title:'调动操作', sortable:true, width:140,
						formatter:function(value,row,index){
							var t = '';
							if(row.parentOrgId != row.posOrgId){
								if(row.posOrgId){
									t = '<input style="cursor:pointer" type="button" value="调到职位的机构" onclick="moveToPosOrg(\''+row.sysPositionId+'\',\''+row.userId+'\',\''+row.uiid+'\',\''+row.userName+'\',\''+row.posOrgId+'\',\''+row.posOrgName+'\',\''+row.parentOrgId+'\',\''+row.orgName+'\')"/>';
								}
							}
							return '<div id="div_btn_'+row.sysPositionId+'" style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="调动">'+t+'</div>';
						}
					}
				]],		
				onLoadSuccess:function(){
					//$('.datagrid-view').css({"height":"400"});
					//$('.datagrid-view').css({"height":"400"});
				},
				onDblClickRow:function(rowIndex,row){
					//$.parser.parse('#tt');
				}
			});
		}
	 	function searchUnMachAcctList(){
	 		$('#tt').datagrid('reload');
	 	}
	 	function moveToPosOrg(posId,userId,uiid,acctName, posOrgId, posOrgName, orgId, orgName){
	 		if(window.confirm('将 '+acctName+' 从 '+orgName+' 调动到 '+posOrgName+' ,确认吗?')){
	 			var url = '${ctx}/plas/plas-validate!moveToOrg.action';
	 			var data = {userId: userId, orgId: orgId, orgName: orgName, posOrgId:posOrgId, posOrgName: posOrgName};
	 			$.post(url, data, function(result){
	 				if('success' == result){
	 					$('#div_org_'+posId).html('<font style="color:red">'+posOrgName+'</font>');
	 					$('#div_btn_'+posId).html('<font style="color:red">成功调至( '+posOrgName+' )</font>');
	 				}else{
	 					$.messager.alert('提示','调动失败!'+result);
	 				}
	 				
	 			});
	 		}
	 	}
	 </script>
</body>
</html>