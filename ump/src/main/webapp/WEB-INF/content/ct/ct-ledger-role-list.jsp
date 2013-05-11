<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>项目权限管理</title>
	
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bid/bid.css"  />
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
</head>
<body>

	<div id="bodyHead" class="bodyHead">
		<div class="headTitle"><font style="font-size: 14px;">项目权限管理</font></div>
	</div>
	<div id="projectList" style="clear: left;">
		<div id="rsTable">
			<table  class="bidTable " cellpadding="0" cellspacing="0" style="border-collapse:separate;margin-top: 5px;margin-left: 5px;">
				<colgroup>
					<col width="5%"/>
					<col width="10%"/>
					<col width="27%"/>
					<col width="50%"/>
					<col />
					
				</colgroup>
				<thead>
					<tr>
						<th class="first">序号</th>
						<th>大区</th>
						<th>项目名称</th>
						<th>用户</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<s:iterator var="p" value="bovos" status="st">
				<tr>
					<td><s:property value="#st.index+1"></s:property></td>
					<td>${areaName }</td>
					<%--<%= CodeNameUtil.getDeptNameByCd(JspUtil.findString("projectCd")) %>取不到"蚌埠地产公司" --%>
					<td>${projectName}</td>
					<td>
					   <input type="hidden"  id="id_${projectCd}" name="id" value="${projectCd}"/> 
					   <input id="${projectCd}UserNames" type="text" class="member" style="width:85%;" readonly="readonly" value="<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("userCds"),";") %>"/>
					   <input id="${projectCd}UserCds" type="hidden" name="authority" value="${userCds}"/>					
					</td>
					<td><button onclick="doProjectSave('${projectCd}');">保存</button></td>
				</tr>
				</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
	  
<script type="text/javascript">
	$(function(){
		$(".member").ouSelect();
	});
	//保存项目权限
	function doProjectSave(projectCd){
		var pcd=$("#id_"+projectCd).val();
		var userCds =$("#"+projectCd+"UserCds").val();
		$.post("${ctx}/ct/ct-ledger-role!save.action",{projectCd:pcd,userCds:userCds},
				function(result){
				var rs=result.split(',');
			      if("success"==rs[0]){
			    	  alert("保存成功");
			      }else{
			    	  alert("保存失败");
			      }
		});
	}
	
</script>

</body>
</html>