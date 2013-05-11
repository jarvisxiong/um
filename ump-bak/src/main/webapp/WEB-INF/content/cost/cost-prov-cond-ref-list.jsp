<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>


<div id="TableDiv" class="costTable" style="height: 100%;overflow: hidden;border:1px solid #cbcbcb;" >
	<table width="100%" border="0" cellspacing="0" cellpadding="0"  id="table1">
		<tr>
			<th width="35" scope="col">序号</th>
			<th scope="col" width="60">分类</th>
			<th scope="col" width="100">供方内容</th>
			<th width="125" scope="col">专业资质</th>
			<th width="125" scope="col">行业排名</th>
			<th width="125" scope="col">企业业绩</th>
			<th width="125" scope="col">体系认证</th>
		</tr>
		  <s:iterator value="Page.result" status="st">
			  <tr class="mainTr" style="height: 35px;" onclick="getDetail('${costProvCondRefId}');">
					<td align="center" class="pd-chill-tip">${st.index+1}</td>
					<td align="left" class="pd-chill-tip" title='${typeName}' style="padding-left: 5px;" valign="middle">${typeName}</td>
					<td align="left" class="pd-chill-tip" title='${provDesc}'   style="padding-left: 5px;" valign="middle"><div class="tabletdsdiv">${provDesc} </div></td>
					<td align="left" class="pd-chill-tip" title='${profQualDesc}' style="padding-left: 5px;" valign="middle"><div class="tabletdldiv">${profQualDesc} </div></td>
					<td align="left" class="pd-chill-tip" title='${induRankDesc}' style="padding-left: 5px;" valign="middle"><div class="tabletdldiv">${induRankDesc} </div></td>
					<td align="left" class="pd-chill-tip" title='${entePerfDesc}' style="padding-left: 5px;" valign="middle"><div class="tabletdldiv">${entePerfDesc} </div></td>
					<td align="left" class="pd-chill-tip" title='${systCertDesc}' style="padding-left: 5px;" valign="middle"><div class="tabletdldiv">${systCertDesc} </div></td>
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
	var url="${ctx}/cost/cost-prov-cond-ref!input.action?id="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cost-prov-cond-ref-detail","详细信息",url,true);
}
function doDelSection(id){
	if(id != null){
		if(confirm("确定要删除该条记录吗？")){
			var url="${ctx}/cost/cost-prov-cond-ref!delete.action";
			$.post(url,{id:id},function(result) {
				if('success' == result){
					doQuerySection();
				}else if("realy" == result){
					alert("该条记录已经被使用,无法删除");
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