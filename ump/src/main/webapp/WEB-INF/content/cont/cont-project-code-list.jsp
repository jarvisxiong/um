<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %> 
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%> 
 
<table class="content_table" style="width:100%;">
<thead>
  	<tr>
		<th align="center" width="40px" style="background: none;">序号</th>
		<th align="center" width="80px">机构编号</th>
		<th width="150px">项目名称</th>
		<th>用户</th>
		<th width="100px">备注(关键字)</th>
		<th width="200px">操作</th>
	</tr>
</thead>
<tbody>
	<s:iterator value="page.result" status="st">
	<tr class="mainTr" style="cursor:pointer;">
  		<td align="center" >${st.index+1} </td>
  		<td align="center" >${projectCd}</td>
   		<td>${projectName}</td>
	    <td>
	       <input  type="hidden"  id="id_${projectCd}" name="id" value="${contProjectCodeId}"/> 
		   <input  id="${projectCd}UserNames" 
				   type="text" 
				   class="member" 
				   style="width:99%;cursor:pointer;" 
				   readonly="readonly"
				   title="请点击选择人员" 
				   value="<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("authority"),";") %>"/>
		   <input id="${projectCd}UserCds" type="hidden" name="authority" value="${authority}"/>
	    </td>
	    <td>
	    	<textarea id="${projectCd}remark" name="remark" style="border:none;width:99%;font-size: 13px; margin-top: 5px;margin-bottom: 5px;">${remark}</textarea>
	    </td>
	    <td>
	    	<%-- 合同台账超级管理员 --%>
			<security:authorize ifAnyGranted="A_CONTRACT_ADMIN">
	    	<button onclick="doProjectSave('${projectCd}','${st.index}');" class="btn_green" >保存</button>
	 		<span id="tips${st.index}" style="color: red;"></span>
	 		</security:authorize>
	 	</td>
  	 </tr>
	</s:iterator>
</tbody>
</table>

<input type="hidden" id="pageTotal" value="${page.totalCount}" >
<div class="table_pager" style="margin-top:5px;">
	<p:page pageInfo="page"/>
</div>	

<script type="text/javascript">
	$(function(){
		$(".member").ouSelect();
		//行点击事件
		$("tr.mainTr").click(function(){
			$(".trClicked").removeClass('trClicked');
			var o=$(this);
			o.children().eq(0).addClass('trClicked');
		});
	});
	function doProjectSave(projectCd,index){
		//clearTips();
		$('#tips'+index).hide();
		var id = $("#id_"+projectCd).val();
		var userCds = $("#"+projectCd+"UserCds").val();
		var remark = $("#"+projectCd+"remark").val();
		var data = {id : id, userCds : userCds, remark : remark};
		$.post("${ctx}/cont/cont-project-code!save.action", data, function(result){
		      if("ok" == result){
		    	  $('#tips'+index).html('保存成功!').show();//.fadeOut(5000)
		      }else{
		    	  alert("保存失败");
		      }
		});
	}
	function clearTips(){
		var count = $("#count").val();
		for(var i = 0;i<count;i++){
			$('#tips'+i).html('');
		}
	}
</script>