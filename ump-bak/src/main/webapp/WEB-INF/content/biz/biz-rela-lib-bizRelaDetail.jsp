<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<form action="${ctx}/biz/biz-rela-lib!save.action" method="post" id="bizRelaDetaForm" >
<input type="hidden" id="recordVersion" name="recordVersion" value="${entity.recordVersion}"/>
<input type="hidden" id="id" name="id" value="${entity.bizRelaLibId}"/>
<input type="hidden"  id="dictNo"  name="relaProvince" value="${entity.relaProvince}" />
<input type="hidden"  id="d_submitCenterCd"  name="submitCenterCd" value="${entity.submitCenterCd}" />
<input type="hidden"  id="d_submitPersionCd"/>
<div id="bizRelaDetail" style="margin-left: 0px;">
		 <table class="shop-table">
	          <col width="100"/>
		      <col/>
			  <col width="100"/>
			  <col />
			<tr>
		      <td ><font style="color:red;">*</font>关系类别</td>
		      <td style="text-align: left;">
			      <security:authorize ifAnyGranted="A_BIZ_RELA_ADMIN">
			      <s:select style="width:100%;" list="@com.hhz.ump.util.DictMapUtil@getMapRelaType()" listKey="key" listValue="value" id="relaTypeCd" name="entity.relaTypeCd"  ></s:select>
			      </security:authorize>
			      <security:authorize ifNotGranted="A_BIZ_RELA_ADMIN">
			      <p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapRelaType()" value="entity.relaTypeCd"/>
			      </security:authorize>
		      </td>
			  <td><font style="color:red;">*</font>省份</td>
		      <td><input type="text" id="d_relaProvinceName" value="${entity.relaProvinceName}"   /> </td>
			</tr>
			<tr>
			   <td><font style="color:red;">*</font>姓名</td>
			   <td><input type="text"  name="relaName"   value="${entity.relaName}"  /></td>
		       <td><font style="color:red;">*</font>资料级别</td>
		       <td style="text-align: left;">
		       <security:authorize ifAnyGranted="A_BIZ_RELA_ADMIN">
		       <s:select style="width:100%;" id="relaLevelCd" name="entity.relaLevelCd"   validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapRelaLevel()" listKey="key" listValue="value"  ></s:select>
		       </security:authorize>
		       <security:authorize ifNotGranted="A_BIZ_RELA_ADMIN">
		       <p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapRelaLevel()" value="entity.relaLevelCd"/>
		       </security:authorize>
		       </td>
			</tr>
			<tr>
			   <td><font style="color:red;">*</font>通讯地址</td>
		       <td colspan="3"><input type="text" id="relaAddress" name="relaAddress"   value="${entity.relaAddress}"  /> </td>
			 </tr>
			 <tr>
			   <td>单位</td>
		       <td colspan="3"><input type="text" name="relaUnit"  value="${entity.relaUnit}"  /> </td>
			 </tr>
			 <tr>
			   <td><font style="color:red;">*</font>职位</td>
			   <td><input type="text" id="relaPos" name="relaPos"    value="${entity.relaPos}"  /></td>
			   <td>电话</td>
			   <td><input type="text" name="relaPhone" name="relaPhone" value="${entity.relaPhone}"  /></td>
			 </tr>
			 <tr>
				 <td><font style='color:red;'>*</font>传真</td>
				 <td><input type="text" name="relaFax"  name="relaFax"  value="${entity.relaFax}"  /> </td>
				 <td>&nbsp;&nbsp;手机</td>
				 <td><input type="text" name="relaMobile" name="relaMobile" value="${entity.relaMobile}"  /></td>
			  </tr>
			  <tr>
			   <td><font style="color:red;">*</font>地区</td>
		       <td><input type="text"  name="relaArea"  value="${entity.relaArea}"    /> </td>
		       <td><font style="color:red;">*</font>序号</td>
		       <td><input type="text"  name="sequenceNo" validate="required" alt="amount" id ="sequenceNo" value="${entity.sequenceNo}" maxlength="9"/> </td>
			 </tr>
			 <tr>
			  <td><font style='color:red;'>*</font>提交公司/中心</td>
			  <td><input type="text" name="submitCenterName" id="d_submitCenterName" value="${entity.submitCenterName}"   />
			  </td>
			  <td>提交人</td>
			  <td><input type="text" name="submitPersion" id="d_submitPersion" value="${entity.submitPersion}"/> </td>
			 </tr>
			 <tr>
			   <td><font style="color:red;">*</font>说明</td>
		       <td colspan="3" rowspan="3"><textarea id="remark"  style=" width:100%;height:80px;background-color: #DBE5F1;border: none;" name="remark"   >${entity.remark}</textarea></td>
			  </tr>
		 </table>
		 	 <div id="searchUserDiv" class="searchUserDiv"></div>
</div>
</form>

<script type="text/javascript">
<security:authorize ifNotGranted="A_BIZ_RELA_ADMIN,A_BIZ_RELA_SUP">
$("#bizRelaDetail").find(":text,textarea").css('backgroundColor','#dddbdc');
$("#bizRelaDetail").find(":text,textarea").attr('readOnly',"readOnly");
</security:authorize>
$("#d_relaProvinceName").quickSearch("${ctx}/app/app-dict-type!quickSearch.action",['dictName'],{dictCd:'dictNo',dictName:"d_relaProvinceName"},{dictTypeCd:'CITY_PROVINCE_TYPE'});
$('#d_submitCenterName').quickSearch("${ctx}/biz/biz-rela-lib!quickSearchOrg.action",['dictName'],{dictCd:'d_submitCenterCd',dictName:"d_submitCenterName"});
$('#d_submitPersion').quickSearch("${ctx}/com/user-choose!quickSearch.action",['userName','uiid','orgName'],{userName:'d_submitPersion',uiid:"d_submitPersionCd"});
$(function(){
	//$('tbody tr td').removeAttr('onclick');
});
</script>
