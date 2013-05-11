<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div id="TableDiv" class="costTable" style="height: 100%;overflow: hidden;">
	<table width="100%" id="table1">
	  <thead>
      <tr style="height: 35px;">
          <th scope="col" align="center" class="pd-chill-tip" title='序号 ' style="width: 35px;background: none;">序号</th>
          <th scope="col" align="center" class="pd-chill-tip" style="width:160px;" title='项目公司'>项目公司</th>
          <th scope="col" align="center" class="pd-chill-tip" style="width:150px;" title='简称'>简称</th>
          <th scope="col" align="center" class="pd-chill-tip" title='项目名称'>项目全称</th>
          <th scope="col" align="center" class="pd-chill-tip" title='项目名称'>备注</th>
          <%--
          <th scope="col" align="center" class="pd-chill-tip" title='项目编号' style="width: 100px;">项目编号</th>
          <th scope="col" align="center" class="pd-chill-tip" title='显示序号' style="width: 60px;">显示序号</th>
       	  <th scope="col" align="center" class="pd-chill-tip" title='操作' style="width:70px;">操作</th>
           --%>
      </tr>
	  </thead>
	  <s:iterator value="Page.result" status="st">
		  <tr class="mainTr" style="height: 35px;" onclick="doAddSection('${costProjectSectionId}');">
				<td align="center" class="pd-chill-tip">${st.index+1}</td>
				<td align="left" class="pd-chill-tip" title='${projectName}' style="padding-left: 5px;">${projectName}</td>
				<td align="left" class="pd-chill-tip" title='${briefName}'   style="padding-left: 5px;">${briefName} </td>
				<td align="left" class="pd-chill-tip" title='${sectionName}' style="padding-left: 5px;">${sectionName} </td>
				<td align="left" class="pd-chill-tip" title='${sectionName}' style="padding-left: 5px;">${remark} </td>
				  <%--
				<td align="center" onclick="doAddSection('${costProjectSectionId}');" class="pd-chill-tip" title='${sectionBizCd}'>${sectionBizCd}</td>
				<td align="center" onclick="doAddSection('${costProjectSectionId}');" class="pd-chill-tip" title='${sequenceNo}'>${sequenceNo}</td>
				<td align="center">
					<security:authorize ifAnyGranted="A_COST_BUD_ADMIN">
						<input id="delBtn" name="delBtn" style="width: 40px;" class="btn_red" onclick="doDelSection('${costProjectSectionId}');" type="button" value ="删除"/>
					</security:authorize>
				</td>
				--%>
		  </tr>
	  </s:iterator>
	</table>
	<input type="hidden" id="pageTotal" value="${page.totalCount}" >
	<div class="table_pager" style="margin-top:5px;">
		<p:page/>
	</div>
</div>
<script type="text/javascript">
function getDetail(id){
	var url="${ctx}/cost/cost-project-section!input.action?id="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cost-project-section-input","期数信息",url,true);
}
function doDelSection(id){
	if(id != null){
		if(confirm("确定要删除该条记录吗？")){
			var url="${ctx}/cost/cost-project-section!delete.action";
			$.post(url,{id:id},function(result) {
				if('success' == result){
					doQuerySection();
				}else if("realy" == result){
					alert("项目已经被使用,无法删除");
					return false;
				}else{
					alert("删除失败");
					return false;
				}
			});
		}
	}
}
</script>