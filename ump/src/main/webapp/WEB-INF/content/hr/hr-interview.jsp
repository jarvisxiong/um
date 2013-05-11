<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery.js"></script>
<title>招聘管理2</title>
<script language="javascript">
	$(function(){
		$("#editTable tbody tr.mainTr").toggle(function(){
			$("#editTable tbody tr:odd").hide();
			$(this).siblings().attr("bgcolor","");
			$(this).children().eq(0).children().attr("src","images/up.gif")
			$(this).attr("bgcolor","#ccffcc");
			$(this).next().show();
		},function(){
			$(this).children().eq(0).children().attr("src","images/down.gif")
			$(this).attr("bgcolor","");
			$(this).next().hide();
		})
	});
</script>
</head>

<body>
<div >
<div class="search">
	<fieldset>
	    <legend><s:text name="common.search" /></legend>
	    <div>
	      <s:text name="appAppDictType.dictTypeCd"/>:<input type="text" name="dictTypeCd" id="dictTypeCd" size="18" maxlength="30" />
	      <s:text name="appAppDictType.dictTypeName"/>:<input type="text" name="dictTypeName" id="dictTypeName" size="18" maxlength="30" />
	      <input type="submit" class="buttom" value="<s:text name="common.search" />" />
	      <input type="submit" class="buttom" value="导出Excel" />
	    </div>
	</fieldset>
</div>

<s:form id="inputForm" action="app-dict-type!save.action" method="post">

<div id="tableDiv">
<table class="commonTable" id="editTable" align="left" width="99%">
	<thead>
		<tr>
		<th width="40"><s:text name="appAppDictData.dictCd"/></th>
		<th width="40"><s:text name="appAppDictData.dictName"/></th>
		<th width="40"><s:text name="appAppDictData.dispOrderNo"/></th>
		<th width="50"><s:text name="appAppDictData.remark"/></th>
		<th width="80"><s:text name="appAppDictType.creator"/></th>
		<th width="90"><s:text name="appAppDictType.createdDate"/></th>
		<th width="90"><s:text name="appAppDictType.updator"/></th>
		<th width="90"><s:text name="appAppDictType.updatedDate"/></th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="appDictDatas">
		<tr id="0" class="mainTr">
			<td>${dictCd}</td>
			<td>${dictName}</td>
			<td>${dispOrderNo}</td>
			<td>${remark}</td>
			<td>${creator}</td>
			<td>${createdDate}</td>
			<td>${updator}</td>
			<td>${updatedDate}</td>
			
		</tr>
		<tr id="det_3" class="detailTr"  style="display:none">
			<td colspan="8">
				    <fieldset>
				    <legend>详细信息</legend>
				    <div>
				      <label for="dictCd"><s:text name="appAppDictData.dictCd"/></label>
				      <s:textfield name="dictCd" id="dictCd"/>
				      <br/>
				    </div>
				    <div>
				      <label for="dictName"><s:text name="appAppDictData.dictName"/></label>
				      <s:textfield name="dictName" id="dictName"/>
				      <br/>
				    </div>
				    <div>
				      <label for="dispOrderNo"><s:text name="appAppDictData.dispOrderNo"/></label>
				      <s:textfield name="dispOrderNo" id="dispOrderNo"/>
				      <br/>
				    </div>
				    <div>
				      <label for="remark"><s:text name="appAppDictData.remark"/></label>
				      <s:textarea name="remark" id="remark"></s:textarea>
				      <br/>
				    </div>
				    <div>
				      <label for="dictCd"><s:text name="appAppDictData.dictCd"/></label>
				      <s:textfield name="dictCd" id="dictCd"/>
				      <br/>
				    </div>
				    <div>
				      <label for="creator"><s:text name="appAppDictData.creator"/></label>
				      <s:textfield name="creator" id="creator"/>
				      <br/>
				    </div>
				    <div>
				      <label for="updator"><s:text name="appAppDictData.updator"/></label>
				      <s:textfield name="updator" id="updator"/>
				      <br/>
				    </div>
				    <div>
				      <label for="updatedDate"><s:text name="appAppDictData.updatedDate"/></label>
				      <s:textfield name="updatedDate" id="updatedDate"/>
				      <br/>
				    </div>
				    </fieldset>
			</td>
		</tr>
	</s:iterator>
	</tbody>
</table>
</div>
</s:form>
</div>
</body>
</html>
