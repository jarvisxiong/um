<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%> 

<style>
	.trClicked{
		background-color: orange;
	}
	table.content_table tr th{
	   font-weight:bold;
	}
	table.content_table tbody td{
	  border-bottom: 1px solid #E4E7EC;
	  height:40px;
	  padding-left:5px;
	}
	table input[type="text"]{
	    height:20px;
        border: 1px solid #999999;
	}
</style>
<table class="content_table" style="width:100%;">
	<colgroup>
		<col width="40px"/>
		<col width="50px"/>
		<col width="150px"/>
		<col />
		<col width="130px"/>
		<col width="150px"/>
	</colgroup>
	<thead>
	<tr>
		<td class="first" style="border-bottom:1px solid #AAABB0">&nbsp;</td>
		<th>大区</th>
		<th>项目名称</th>
		<th>用户</th>
		<th>备注（关键字）</th>
		<th><%-- 操作 --%></th>
	</tr>
	</thead>
	<tbody>
	<s:if test="bovos.size() == 0">
	<tr>
		<td colspan="6" style="text-align: center;">搜索无记录!</td>
	</tr>
	</s:if>
	<s:iterator var="p" value="bovos" status="st">
	<tr class="rowitem">
		<td align="center" style="text-align: center;"><s:property value="#st.index+1"></s:property></td>
		<td>${areaName}</td>
		<td>${projectName}</td>
		<td>
		   <input type="hidden"  id="id_${projectCd}" name="id" value="${projectCd}"/> 
		   <input id="${projectCd}UserNames" 
		   		  type="text" 
		   		  class="member" 
				  style="width:99%;cursor: pointer;" 
				  readonly="readonly" 
				  value="<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("userCds"),";") %>"
		   />
		   <input id="${projectCd}_UserCds" type="hidden" name="userCds" value="${userCds}"/>					
		</td>
		<td><textarea name="remark" id="${projectCd}_remark" style="width: 100px; height: 30px;border: 1px solid #999999; " rows="1">${remark }</textarea></td>
		<td valign="middle">
			<input class="btn_new btn_green_new" type="button" line="<s:property value="#st.index+1"></s:property>" onclick="doProjectSave(this,'${projectCd}');" value="保存"/>
			<span class="tip" style="float: left;display: none;"  id="tip_<s:property value="#st.index+1"></s:property>"></span>
		</td>
	</tr>
	</s:iterator>
	</tbody>
</table> 
	  
<script type="text/javascript">
	$(function(){ 
		$(".member").ouSelect();
		
		//每行增加焦点
		$(".rowitem").click(function(){
			$(".trClicked").removeClass('trClicked');
			$(this).children().eq(0).addClass('trClicked');
		});
	}); 
</script>

</body>
</html>