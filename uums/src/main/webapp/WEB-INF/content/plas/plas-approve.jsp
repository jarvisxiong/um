<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>用户管理</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common/treePanel.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/ymPrompt.css" />
	
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common/common.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/approve/approve.css">
	<script type="text/javascript" src="${ctx}/js/approve/plas-approve.js"></script>
	<script type="text/javascript" src="${ctx}/js/approve/approve.js"></script>
	
	<%--注意：jquery1.4以上，不适用eval构造json,直接(result)即可 --%>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js" ></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
	
	<script type="text/javascript" src="${ctx}/js/common/treePanel.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.quickSearch.js"></script>
	<script type="text/javascript" src="${ctx}/js/biz/app/app-attachment.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.select.js"></script>
</head>
<body class="easyui-layout">

	<div region="north" border="false" style="margin-left:5px;height: 35px;line-height: 35px;overflow-y:hidden;">
		<%-- 搜索人员 --%>
		<div style="float:left;width:240px;font-weight:bolder;font-size:14px;margin:5px 0 0 5px;">人员管理</div>
		<div style="float:left;">
			在职人员搜索:
			<input 	style="width:150px;margin-right:5px;" 
					type="text" 
					name="quickSearchUserName" 
					id="quickSearchUserName" 
					title="支持账号或姓名查找" 
					class="auto_search_init" 
					value="搜索账号或姓名..."
					onmouseover="processCondOver(this)"
					onmouseout="processCondOut(this)"/>
			<input type="hidden" name="quickSearchUserId" id="quickSearchUserId"/>
			<%--
			<font color="red">(支持精确账号或模糊姓名查找)</font>
			 --%>
		</div>
		<div style="float:left;margin: 5px 0 0 20px;">
			<input type="button" class="plbtn plblue" id="btnLoadApprove" onclick="loadApproveList()" value="审批列表" style="float:right;"/>
			<input type="button" class="plbtn plgreen"  onclick="leaveUser()" value="停用人员" style="float:right;"/>
			<input type="button" class="plbtn plgreen"  onclick="showTranIn()" value="调入人员" style="float:right;"/>
			<input type="button" class="plbtn plblue"  onclick="addUser()" value="新增人员" style="float:right;"/>
		</div>
	</div>
	
	<div id="leftPanel" reltype='user' region="west" title='
	<%--
		<span id="user_tree_check" style="display:none;"><input type="checkbox" id="load_pos_flag" style="margin-right:5px;"  onchange="reloadUserTree()"/>含职位</span>
	--%>
		<ul class="nav_tree" style="list-style-type:none;">
			<li class="nav_tree_click" onclick="reloadPosTree(this)">职位树</li>
			<li onclick="reloadUserTree(this)">职务树</li>
		</ul>	
	' split="true"  style="width:240px;">
		<%-- 搜索条件  --%>
		<div>
		<div></div>
		<%--
		<div style="margin: 5px 0 5px 5px;">
			查找：
			<input style="width:120px;margin-top: 2px;" type="text" onkeyup="focusTree($(this),'leftScrollPanel')" onclick="focusTree($(this),'leftScrollPanel')" class="auto_search" title="您可以输入账号或姓名进行定位">
			<span class="find" style="color:red;display: none;">(找到)</span>
		</div>
		 --%>
		<div id="leftScrollPanel">
			<div id="leftTree" style="margin-top:5px;">
			<%-- 机构人员树 --%>
			
			</div>
			<div id="leftTreePos" style="margin-top:5px;">
			<%--职位树 --%>
			
			</div>
		</div>
		</div>
	</div> 
	
	<div id="rightPanel" region="center" split="true" style="padding:5px;+position: relative;overflow-x:hidden;height:100%;">

		<div>
	 		
			<%-- 选择停用人员 --%>
			<div id="leavePanel" style="display: none;margin-left:5px;">
				请选择已停用人员:
				<input type="text" name="leaveAcctName" id="quickSearchLeaveAcctName" class="auto_search_init" style="width:150px;"/>
				<input type="hidden" name="leaveAcctId" id="quickSearchLeaveAcctId"/>
	 		</div>
			
			<!-- 申请明细 -->
			<div id="userDetailPanel" style="clear: both;min-height: 100px;min-width: 95%;padding:10px;margin-top:10px;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
			
			<%-- 申请列表 --%>
			<div id="approvePanel" style="clear: both;display:none;">
				<div class="approve_panel">
					<%@ include file="approve-panel.jsp" %>
				</div>
			</div>
		</div>
	</div>
	
	<%-- 以下是弹出框 --%>
	<div id="wUploadFile" title="上传图片" class="easyui-window" minimizable="false" maximizable="false" closed="true" style="width:450px;height:300px;padding:5px;background: #fafafa;">
	</div>
	<div id="wParentOrg"  title="请选择上级机构" class="easyui-window" minimizable="false" maximizable="false" closed="true" style="width:450px;height:400px;padding:5px;background: #fafafa;">
		<div style="width: 100%;text-align: left;">
			<input type="button" style="cursor:pointer" class="plbtn plgreen" onclick="saveParentOrg()" value="保存" />
			<input type="button" style="cursor:pointer" class="plbtn plred" onclick="closeParentOrg()" value="取消" />
		</div>
		<div id="parentOrgTree" style="margin-top:10px;">
			<!-- 这里加载物理机构视图 -->
		</div>
	</div> 
	
	<div id="wConfigPos" title="配置职位" class="easyui-window" closed="true" style="width:450px;height:300px;padding:5px;">
		<div style="width: 100%;text-align: left; line-height: 30px; ">
			<input type="button" style="cursor:pointer" class="plbtn plgreen" onclick="savePopPosConfirm()" value="保存" />
			<input type="button" style="cursor:pointer" class="plbtn plred" onclick="closePopPos()" value="取消" />
			<input type="hidden" id="tmpPosIdPop" />
			<br/>
			<span style="color:red;">
			<%-- 您选择的部门下没有空余的岗位编制，请核对编制落位情况。　 --%>请选择对应部门下编制；如需增加编制，请联系营运管理中心 陈景晶。
			</span>
		</div>
		<div id="posTreePanel"  style="height:200px;overflow-y:auto;+position: relative;">
		</div>
	</div>
	
	<div id="wConfigPosConfirm" title="确认" class="easyui-window" closed="true" style="width:450px;height:300px;padding:5px;">
		<div style="width: 100%;text-align: left; line-height: 30px; ">
			<input type="button" style="cursor:pointer" class="plbtn plgreen" onclick="savePopPosConfirm()" value="保存" />
			<input type="button" style="cursor:pointer" class="plbtn plred" onclick="closePopPosConfirm()" value="取消" />
		</div>
		<div id="posRangePanelDiv"  style="height:250;overflow-y:auto;+position: relative;">
		</div>
	</div>
	
	<div id="wConfigChange" title="调整内容如下:" class="easyui-window" closed="true" style="width:450px;height:300px;padding:5px;">
		<div id="changePanelDiv"  style="+position: relative;">
		</div>
		
		<div style="width: 100%;text-align: center; line-height: 30px; ">
			<input type="button" style="cursor:pointer" class="plbtn plgreen" onclick="savePopChange()" value="确定" />
			<input type="button" style="cursor:pointer" class="plbtn plred" onclick="closePopChange()" value="取消" />
		</div>
	</div>
	
	<div id="wTranIn" title="调动人员" class="easyui-window" closed="true" style="width:450px;height:300px;padding:5px;">
		<div style="width: 100%;text-align: left; line-height: 30px; ">
			<input type="button" style="cursor:pointer" class="plbtn plgreen" onclick="refrshTranIn()" value="刷新" />
			<input style="width:120px;margin-top: 2px;" type="text" onkeyup="focusTree($(this),'tranInTreePanel')" onclick="focusTree($(this),'tranInTreePanel')" class="auto_search" title="您可以输入账号或姓名进行定位">
			<span class="find" style="color:red;display: none;">(找到)</span>
			<span style="color:red;">
				请点击人员进行选择
			</span>
			<%--
			<input type="button" style="cursor:pointer" class="buttom" onclick="closePopTranIn()" value="取消" />
			 --%>
		</div>
		<div id="tranInTreePanel"  style="height:220px;overflow-y:auto;+position: relative;">
		</div>
	</div>
	
<script type="text/javascript">

	$(function(){  
		//加载职务树
		//reloadUserTree();
		//加载职位树
		reloadPosTree();
		
		//审批列表
		loadApproveList();

		//搜索人员
		$('#quickSearchUserName').quickSearch(
			'${ctx}/plas/plas-user!quickUserList.action',
			['uiid','userName','orgName','centerOrgName','serviceStatusCd'],
			{userId:'quickSearchUserId',userName:'quickSearchUserName'},
			'',
			function(result){
				refreshUserArea(result.attr('plasUserId'));
			}
		);
		
		//注册快速搜索(模糊匹配:uiid,userName)
		$('#quickSearchLeaveAcctName').quickSearch(
			'${ctx}/plas/plas-acct!quickSearchLeaveAcctList.action',
			['uiid','userName','orgName','centerOrgName','statusCd'],
			{plasAcctId:'quickSearchLeaveAcctId',userName:'quickSearchLeaveAcctName'},
			'',
			function(result){
				refreshAcctArea(result.attr('plasAcctId'));
			}
		);
	}); 

</script>

</body>
</html>