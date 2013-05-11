<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%
	response.setHeader("Expires","0");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<form id="photoForm"  method="post" enctype="multipart/form-data">
	<input type="hidden" name="bizModuleCd" value="plasUser" />
    <input type="hidden" name="bizEntityId" value="${bizEntityId}" />
    <input type="hidden" name="filterType" value="image" />
    <input type="hidden" name="id" value="${userPhotoId}" />
    <input type="hidden" name="bizMainEnableFlg" value="1" />
   	<div style="margin-top: 20px;">请选择一张要上传的照片：<input type="file" name="upload" id="photoFile" /></div>
</form>
<div>
 <table> 
  <tr>
   <td><button onclick="doOnclick();">确定</button> </td>
   <td></td>
  </tr>
 </table>
</div>

<script type="text/javascript">
function doOnclick(){
	if($("#photoFile").val()){
		$('#photoForm').submit();
		$('#photoForm').form('submit', {
			url:'${ctx}/app/app-attachment!upload.action',
			success:function(data){
				$('#dd').window("close");
			}
		});
	}
}
</script>