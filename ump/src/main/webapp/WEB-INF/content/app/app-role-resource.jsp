<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery.js"></script>
<script src="${ctx}/js/jquery.form.pack.js"  type="text/javascript"></script>
<script src="${ctx}/js/table.js" type="text/javascript"></script>
<title>角色资源关系</title>
<script language="javascript">
</script>
</head>

<body>
<div class="fieldsetdiv">
	<fieldset>
	    <legend><s:text name="appAppRoelResourceRel"/></legend>
	<div>
	<table class="commonTable" id="editTable" align="left" width="99%">
	<thead>
		<tr>
		<th width="90"><s:text name="appAppRoelResourceRel.roleCd"/></th>
		<th width="90"><s:text name="appAppRoelResourceRel.resourceTypeCd"></s:text></th>
		<th width="90"><s:text name="appAppRoelResourceRel.resourceValue"/></th>
		<th width="90"><s:text name="appAppRoelResourceRel.remark"/></th>
		<th width="90"><s:text name="common.operate"/></th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="page.result">
		<tr  id="main_${appRoelResourceRelId}" class="mainTr">
			<td id="roleCd">${roleCd}</td>
			<td id="resourceTypeCd"><p:code2name mapCodeName="mapResourceType" value="resourceTypeCd"/></td>
			<td id="resourceValue">${resourceValue}</td>
			<td id="remark">${remark}</td>
			<td>
			<a href="app-authority!deleteResRel.action?appRoelResourceRelId=${appRoelResourceRelId}" ><s:text name="common.delete"/></a>
			</td>
		</tr>
		<tr  id="detail_${appRoelResourceRelId}" class="detailTr"  style="display:none">
			<td colspan="9">
				<fieldset>
				    <legend><s:text name="common.detail"/></legend>
				    <table class="innerTable" >
				    	<tr>
				    	<td width="80"><s:text name="appAppRoelResourceRel.roleCd"/>
				    	</td>
				    	<td  width="100"><input type="text" name="roleCd" id="roleCd"  value="${roleCd}" />
				    	<input type="hidden" name="id" value="${appRoelResourceRelId}" />
				      	<input type="hidden" name="recordVersion" value="<s:property value="recordVersion" />" />
				    	</td>
						<td width="90">
						<s:text name="appAppRoelResourceRel.resourceTypeCd"/>
				      	</td>
				      	<td  width="100"><s:select list="mapResourceType" listKey="key" listValue="value" name="resourceTypeCd" id="resourceTypeCd"></s:select>
						</td>
						<td width="80">
						<s:text name="appAppRoelResourceRel.resourceValue"/>
				     	</td>
				     	<td  width="100"><input type="text"  name="resourceValue" id="resourceValue" value="${resourceValue}" />
						</td>
						<td width="40">
						<s:text name="appAppRoelResourceRel.remark"/>
				      	</td>
				      	<td width="200"><textarea name="remark" id="remark" >${remark}</textarea>
						</td >
						<td width="40"><a href="app-authority!deleteResRel.action?appRoelResourceRelId=${appRoelResourceRelId}"><s:text name="common.delete"/></a></td>
				    	</tr>
				    </table>
				 </fieldset>
			</td>
		</tr>
	</s:iterator>
	</tbody>
</table>	
	</div>
	</fieldset>
</div>
<div class="divNew">
	<fieldset>
	    <legend><s:text name="common.create"/></legend>
	<div>
	<s:form id="inputForm" action="app-authority!save.action" >
		<table class="mainTable">
			<tr><td><s:text name="appAppRoelResourceRel.resourceTypeCd"/></td>
			<td><s:select name="resourceTypeCd" list="mapResourceType" listKey="key" listValue="value"/></td>
			</tr>
			<tr><td><s:text name="appAppRoelResourceRel.resourceValue"/></td>
			<td><s:textfield name="resourceValue"/></td>
			</tr>
			<tr><td><s:text name="appAppRoelResourceRel.remark"/></td>
			<td><s:textarea  id="remark" name="remark" /></td>
			</tr>
		</table>	
	</s:form>	
	</div>
	</fieldset>
</div>
<div>
<input type="button" class="buttom" id="btnSave" onclick="doSave();" value="<s:text name="common.save"/>" />
</div>
</body>
</html>
