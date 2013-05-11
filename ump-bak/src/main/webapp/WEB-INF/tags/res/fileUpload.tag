<%--
网批表单中附件上传功能
 --%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" description="附件标题" required="true"%>
<%@ attribute name="fileId" description="字段ID" required="true"%>
<%@ attribute name="index" description="列表序号" required="false"%>
<%@ attribute name="fileValue" description="文件ID" required="false"%>
<%@ attribute name="bizEntityId" description="bizEntityId" required="true"%>
<%@ attribute name="canEdit" description="是否可上传" required="true"%>
<%@ attribute name="subProp" description="子列表属性" required="false"%>
<%@ attribute name="isTemplate" description="如果是模板，不给按钮添加事件，将在循环中添加，默认false" required="false"%>
<%@ attribute name="isRequired" description="是否必输条件,默认false" required="false"%>
<%@ attribute name="isRele2Contract" description="是否关联合同库,默认false" required="false"%>
<%@ attribute name="custAttr" description="自定义属性,如:resattachname='dd'" required="false"%>
<div id="div_${fileId}" style="float: left;width:100%;padding: 5px 0;border-bottom:#BFC4C8 1px solid; ">
<div id="div_vali_${fileId}" ${custAttr} class="td_title" style="float:left;width:160px; margin: 0 10px;"  <% if (isRequired==null || isRequired.equals("true")){ %>validate="required" <%} %> value="${fileValue}">${title}</div>
<% if (canEdit.equals("true")){ %>
<div style="float:left; margin: 0 10px;">
<input type="button" id="btn${fileId}" value="上传附件" class="btn_green_65_20" style="border:none;" <% if (isTemplate==null || isTemplate.equals("false")){ %> onclick="showUploadSingleAttachDialog('${title}','${bizEntityId}','resCustomFile','${index}${fileId}',event);" <%} %> />
<% if (index!=null){ %>
<input type="hidden" name="templateBean.${subProp}[${index}].${fileId}" id="${index}${fileId}" value="${fileValue}"/>
<%}else{ %>
<input type="hidden" name="templateBean.${fileId}" id="${fileId}" value="${fileValue}"/>
<%} %>
</div>
<%} %>
<div id="show_${index}${fileId}" style="float:left;"  <% if (isRele2Contract!=null && isRele2Contract.equals("true")){ %> resattachname="${title}" <%}%>></div>
<script type="text/javascript">
showUploadedFile('${fileValue}','${index}${fileId}','${canEdit}');
</script>
</div>