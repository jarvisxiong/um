<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>查看用户明细</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common/treePanel.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/treePanel.js"></script>
	<%--
	<style type="text/css">
		.mainTable td{
			border-left:1px solid #ccc;
			border-bottom:1px solid #ccc;
		}
	</style>
	 --%>
</head>
<body>
<div style="overflow:auto;background-color: white;line-height: 23px;" fit="true" style="+position: relative">
	<table class="mainTable" style="height:100%;width:100%;" cellpadding="0" cellspacing="0">
		<tr class="panel-header" style="line-height:30px;border: 1px solid #99BBE8;">
			<td style="padding-left:5px;width:120px;"><div style="font-weight:bolder;cursor: default;" href="#" onclick="return false;" onfocus="this.blur();"><font color="#15428B">基本信息</font></div></td>
			<td style="width:35%;"></td>
			<td style="width:120px"></td>
			<td></td>
		</tr>
		<tr>
			<td style="padding-left:5px;">统一登录账号:</td>
			<td>${plasUser.uiid}</td> 
			<td>工号:</td>
			<td>${plasUser.userBizCd}</td> 
		</tr> 
		<tr>
			<td style="padding-left:5px;">用户姓名:</td>
			<td><b style="color: red;">${plasUser.userName}</b></td> 
			<td>性别:</td>
			<td>
			<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapSex()" value="plasUser.sexCd"/>
			
		</tr> 
		<tr>
			<td style="padding-left:5px;">上级机构名称(逻辑):</td>
			<td>${logicalOrgName}</td>
			<td>上级机构名称(物理):</td>
			<td>${physicalOrgName}</td>
		</tr> 
		<tr id="trCenterOrgCd">
			<td style="padding-left:5px;">所属中心:</td>
				<td><span style="display:none;"><s:textfield id="centerOrgCd" name="plasUser.centerOrgCd" key="centerOrgCd"></s:textfield></span>
				<span id="centerOrgName"></span>
				<td>账号状态:</td>
				<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapAcctStatus()" value="statusCd"/></td>
			</td>
		</tr> 
		<tr>
			<td style="padding-left:5px;">职位:</td>
			<td>
				<b style="color: red;">
					${realPosName}
				</b>
			</td>
			<td>固定电话号码:</td>
			<td>${plasUser.fixedPhone}</td> 
		</tr>
		<tr>
			<td style="padding-left:5px;">职务:</td>
			<td>${plasUser.workDutyDesc}</td> 
			<td>常用手机号码:</td>
			<td>${plasUser.mobilePhone}</td> 
		</tr> 
		<tr>
			<td style="padding-left:5px;">用户信息来源类型:</td>
			<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapSourceType()" value="sourceTypeCd"/></td>
			<td>身份证号码:</td>
			<td>${plasUser.idno}</td> 
		</tr> 
		<tr>
			<td style="padding-left:5px;">生效日期:</td>
			<td><s:date name="effectDate" format="yyyy-MM-dd"/></td> 
			<td>失效日期:</td>
			<td><s:date name="effectDate" format="yyyy-MM-dd"/></td>
		</tr>
		<tr>
			<td style="padding-left:5px;">是否锁定MAC地址:</td>
			<td><b style="color: red;">
					<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapMacLockedFlg()" value="macLockedFlg"/>
				</b>
			</td>
			<td>MAC地址:</td>
			<td>${macAddress}</td> 
		</tr>
		<tr>
			<td style="padding-left:5px;">密码连续错误次数:</td>
			<td>${failureTimes}</td> 
			<td>显示序号:</td>
			<td>${sequenceNo}</td> 
		</tr>
		<tr>
			<td style="padding-left:5px;">上次登录时间:</td>
			<td>${lastLoginDate}</td>
			<td>上次退出时间:</td>
			<td>${lastLogoutDate}</td>
		</tr>
		<tr class="panel-header" style="line-height:30px;border: 1px solid #99BBE8;">
			<td colspan="4" style="padding-left:5px;">
				<div style="font-weight:bolder;cursor: default;" onclick="return false;" >
					<font color="#15428b">其他信息</font>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-left:5px;">在职状态:</td>
			<td >
				<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapServiceStatus()" value="plasUser.serviceStatusCd"/>
			</td>
			<td>职称:</td>
			<td>
				<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapProfessionType()" value="plasUser.professionTypeCd"/>
			</td>
		</tr>
		<tr>
			<td style="padding-left:5px;">民族:</td>
			<td>
				<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapNation()" value="plasUser.nationCd"/>
			</td>
			<td>出生日期:</td>
			<td><s:date name="plasUser.birthday" format="yyyy-MM-dd"/></td> 
		</tr> 
		<tr>
			<td style="padding-left:5px;">籍贯:</td>
			<td>${plasUser.nativeProvinceDesc}</td> 
			<td>户口所在地:</td>
			<td>${plasUser.nativePlaceDesc}</td> 
		</tr> 
		<tr>
			<td style="padding-left:5px;">婚姻状况:</td>
			<td>
				<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapMarrigeStatus()" value="plasUser.marrigeStatusCd"/>
			</td>
			<td>学历:</td>
			<td>
				<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapSchoolRecord()" value="plasUser.schoolRecordCd"/>
			</td>
		</tr> 
		<tr>
			<td style="padding-left:5px;">毕业院校:</td>
			<td>${plasUser.gradSchoolDesc}</td> 
			<td>专业:</td>
			<td>${plasUser.majorDesc}</td> 
		</tr> 
		<tr>
			<td style="padding-left:5px;">加入单位时间:</td>
			<td><s:date name="plasUser.attendWorkDate" format="yyyy-MM-dd"/></td> 
			<td>员工类型:</td>
			<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapMemberType()" value="plasUser.memberTypeCd"/></td>
		</tr> 

		<tr>
			<td style="padding-left:5px;">政治面貌:</td>
			<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapPolitics()" value="politicsCd"/></td>
			<td>其他:</td>
			<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapOtherType()" value="otherTypeCd"/></td>
		</tr> 
		<tr>
			<td style="padding-left:5px;">备注:</td>
			<td colspan="3">${remark}</td> 
		</tr> 
		<tr class="panel-header" style="line-height:30px;border: 1px solid #99BBE8;">
			<td colspan="4" style="padding-left:5px;"><div style="font-weight:bolder;cursor: default;" href="#" onclick="return false;" onfocus="this.blur();"><font color="#15428b">外部邮箱</font></div></td>
		</tr>
		<tr>
			<td style="padding-left:5px;">邮箱地址:</td>
			<td>
				<b style="color: red;">
					<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapEmailFlg()" value="emailFlg"/>
				</b>
				<b style="color: red;" id="emailDesc">${email}</b>
			</td>
			<td>是否同步邮箱密码</td>
			<td>
				<b style="color: red;">
					<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapEmailPasswordSetFlg()" value="emailPasswordSetFlg"/></td>
				</b>
			</td>
		</tr>
		<tr class="panel-header" style="line-height:30px;border: 1px solid #99BBE8;">
			<td colspan="4" style="padding-left:5px;"><div style="font-weight:bolder;cursor: default;" href="#" onclick="return false;" onfocus="this.blur();"><font color="#15428b">财务系统</font></div></td>
		</tr>
		<tr>
			<td style="padding-left:5px;">金蝶EAS:</td>
			<td>
				<b style="color: red;" id="easDesc">
					<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapEasFlg()" value="easFlg"/>
				</b>
			</td>
			<td>是否同步EAS密码</td>
			<td>
				<b style="color: red;">
					<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapEasPasswordSetFlg()" value="easPasswordSetFlg"/>
				</b>
			</td>
		</tr>
		<%-- 逻辑机构 --%>
		<tr class="panel-header" style="line-height:30px;border: 1px solid #99BBE8;">
			<td style="padding-left:5px;"><div style="font-weight:bolder;cursor: default;" ><font color="#15428b">逻辑机构</font></div></td>
			<td>机构名称</td>
			<td>对应管理员</td>
			<td></td>
		</tr>
		<s:iterator value="bubbleLogicalOrgs" id="bubbleOrg" status="st">
		<tr>
			<td></td>
			<td><s:property value='#st.index+1'/>.${bubbleOrg.orgName}</td>
			<td colspan="2"><p:code2name mapCodeName="orgMangerMap" value="#bubbleOrg.plasOrgId"/> </td>
		</tr>
		</s:iterator>
		
		<%-- 物理机构 --%>
		<tr class="panel-header" style="line-height:30px;border: 1px solid #99BBE8;">
			<td style="padding-left:5px;"><div style="font-weight:bolder;cursor: default;"  onclick="return false;" ><font color="#15428b">物理机构</font></div></td>
			<td>机构名称</td>
			<td>对应管理员</td>
			<td></td>
		</tr>
		<s:iterator value="bubblePhysicalOrgs" id="bubbleOrg" status="st">
		<tr>
			<td></td>
			<td><s:property value='#st.index+1'/>.${bubbleOrg.orgName}</td>
			<td colspan="2"><p:code2name mapCodeName="orgMangerMap" value="#bubbleOrg.plasOrgId"/> </td>
		</tr>
		</s:iterator>
		<tr class="panel-header" style="line-height:30px;border: 1px solid #99BBE8;">
			<td colspan="4" style="padding-left:5px;"><div style="font-weight:bolder;cursor: default;" href="#" onclick="return false;" onfocus="this.blur();"><font color="#15428b">用户角色关系</font></div></td>
		</tr>
		<tr>
			<td colspan="4" style="padding:5px;"> 
				<!-- 这里是应用角色树 
				<ul id="treeRole"/>
				--> 
				
				<div id="treeRole"></div>
			</td>
		</tr>
		
		<%-- 负责部门 --%>
		<tr class="panel-header" style="line-height:30px;border: 1px solid #99BBE8;">
			<td style="padding-left:5px;"><div style="font-weight:bolder;cursor: default;" href="#" onclick="return false;" onfocus="this.blur();"><font color="#15428b">负责机构如下:</font></div></td>
			<td colspan="3"></td>
		</tr>
		<s:iterator value="responseOrgs" id="responseOrg" status="st">
		<tr>
			<td></td>
			<td colspan="3"><s:property value='#st.index+1'/>.${responseOrg.orgName}</td>
		</tr>
		</s:iterator>
		
		<%-- 管理部门 --%>
		<tr class="panel-header" style="line-height:30px;border: 1px solid #99BBE8;">
			<td style="padding-left:5px;"><div style="font-weight:bolder;cursor: default;" href="#" onclick="return false;" onfocus="this.blur();"><font color="#15428b">管理机构如下:</font></div></td>
			<td colspan="3"></td>
		</tr>
		<s:iterator value="managerOrgs" id="managerOrg" status="st">
		<tr>
			<td></td>
			<td colspan="3"><s:property value='#st.index+1'/>.${managerOrg.orgName}</td>
		</tr>
		</s:iterator>
		<tr>
			<td colspan="4" ></td>
		</tr>
	</table>
		
	<table style="width:100%;">	
		<tr class="panel-header" style="line-height:30px;border: 1px solid #99BBE8;">
			<td style="padding-left:5px;" colspan="4"><font color="#15428b">账号审计信息</font></td></tr>
	</table>
		
	<table style="width:100%">
		<tr>
			<td style="padding-left:5px;width:120px">创建时间:</td>
			<td style="width:20%;">${createdDate}</td>
			<td style="width:120px;">创建人员:</td>
			<td style="width:20%;">${creator}</td>
			<td style="width:120px;">创建部门:</td>
			<td>${createdDeptCd}</td>
		</tr>
		<tr>
			<td style="padding-left:5px;">最近更新时间:</td>
			<td>${updatedDate}</td>
			<td>最近更新人员:</td>
			<td>${updator}</td>
			<td>最近更新部门:</td>
			<td>${updatedDeptCd}</td>
		</tr>
	</table>
	<table style="width:100%;">	
		<tr class="panel-header" style="line-height:30px;border: 1px solid #99BBE8;">
			<td colspan="4" style="padding-left:5px;"><font color="#15428b">人员审计信息</font></td></tr>
		</table>
		
		<table style="width:100%">
		<tr>
			<td style="padding-left:5px;width:120px">创建时间:</td>
			<td style="width:20%;">${plasUser.createdDate}</td>
			<td style="width:120px;">创建人员:</td>
			<td style="width:20%;">${plasUser.creator}</td>
			<td style="width:120px;">创建部门:</td>
			<td>${plasUser.createdDeptCd}</td>
		</tr>
		<tr>
			<td style="padding-left:5px;">最近更新时间:</td>
			<td>${plasUser.updatedDate}</td>
			<td>最近更新人员:</td>
			<td>${plasUser.updator}</td>
			<td>最近更新部门:</td>
			<td>${plasUser.updatedDeptCd}</td>
		</tr>
	</table>
		
	<table style="width:100%;">			
		<tr style="background-color: #dfdfdf;">
			<td colspan="4" ></td>
		</tr>
		<%--
		<tr>
			<td colspan="3" style="width:100%;text-align: center;">
				<a class="easyui-linkbutton" href="javascript:window.history.go(-1);">关闭</a>
				<a class="easyui-linkbutton" id="btnBack" href="javascript:plas-user-search.action">返回</a>
			</td>
		</tr>	
		 --%>
	</table>
</div>

<script type="text/javascript">
$(function(){
	//加载应用角色树
	/*
	$('#treeRole').tree({
		url:'${ctx}/plas/plas-search-user!loadUserTreeRole.action?id=${plasAcctId}',
		dnd:true
		onLoadSuccess:function(){
			$('#treeRole').tree("collapseAll");
			var root = $('#treeRole').tree("getRoot");
			if(root){
				$('#treeRole').tree('expand',root.target);
			}
		}
	}); 
	*/
	$("#treeRole").mask('正在搜索...')
	var url = '${ctx}/plas/plas-search-user!ajaxLoadUserRoleTree.action';
	$.post(url, {uiid: '${plasUser.uiid}'}, function(result) {
		$("#treeRole").html('').unmask();
		if (result) {
			var treePanel = new TreePanel({
				renderTo: "treeRole",
				'root'  : eval('('+result+')'),
				'ctx'	:'${ctx}'
			}); 
			//自定义
			treePanel.isDelegateIcon = true;
			treePanel.delegateGetDelegateIcon = function(node){
				return node.iconPath;
			};
			treePanel.delegateOnMouseOverNode = function(node){
				return '';
			};
			//修饰所有节点
			for(var k in treePanel.nodeHash){
				var node = treePanel.nodeHash[k];
				var nodeType = node.attributes.orgOrUser;
				if( nodeType == "1"){
					if(node.isLeaf()){
						node.iconPath = _ctx + "/images/imgTree/page.gif";
					}else{
						node.iconPath = _ctx + "/images/imgTree/folder.gif"; 
					}
				}
			}
			treePanel.render(); 
			//单击事件
			treePanel.on(function(node){
				if( nodeType == '1' || nodeType == '2'){
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
				}
			});
		}
	});
});
</script>
</body>
</html>