<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<style type="text/css">
	.hasAttachFile {
	    background-image: url("${ctx}/resources/images/common/atta_y.gif");
	    background-position: center center;
	    background-repeat: no-repeat;
	    cursor: pointer;
	    padding-left: 15px;
	}
	.hasAttachFile1 {
	    background-image: url("${ctx}/resources/images/common/atta.gif");
	    background-position: center center;
	    background-repeat: no-repeat;
	    cursor: pointer;
	    padding-left: 15px;
	}
</style>

<div id="TableDiv"  style="height: auto; width: auto; overflow: hidden;margin-right:10px; margin-left: 10px;">
	<table width="100%" id="table1" class="assmTable">
	  <thead>
	      <tr style="height: 35px;">
	      	  <th nowrap="nowrap" style="background-image: url('');width: 20px;">
				<input type="checkbox" onclick="checkAll(this);" style="vertical-align:middle;"/>
			  </th>
	          <th scope="col" align="center" nowrap="nowrap" class="pd-chill-tip" title='主题活动' ><div class="ellipsisDiv_full" >主题活动 </div></th>
	          <th scope="col" align="center" nowrap="nowrap" class="pd-chill-tip" title='时间周期' style="width:20%">时间周期</th>
	          <th scope="col" align="center" nowrap="nowrap" class="pd-chill-tip" title='预算费用' style="width:15%">预算费用(元)</th>
	          <th scope="col" align="center" nowrap="nowrap" class="pd-chill-tip" title='预期效果' style="width:20%">预期效果</th>
	          <th scope="col" align="center" nowrap="nowrap" class="pd-chill-tip" title='参考照片及方案' style="width:10%">参考照片及方案</th>
	      </tr>
	  </thead>
	  <s:if test="page.result!=null">
		  <s:iterator value="page.result" var="st">
			  <tr class="mainTr" style="cursor:pointer;height: 35px;">
			  		<td class="pd-chill-tip"><input type="checkbox" style="margin-left:5px;" mateEntryId="${st.pmMateEntryId }" id="pmMateEntryId" name="pmMateEntryId" value="${st.pmMateEntryId }"/></td>
					<td align="center" class="pd-chill-tip" title="${st.activeTitle }" onclick="updateMateEntry('${st.pmMateEntryId}');"><div class="short_div" style="width:100px;">${st.activeTitle }</div></td>
					<td align="center" class="pd-chill-tip" title="${st.activePeriod }" onclick="updateMateEntry('${st.pmMateEntryId}');"><div class="short_div" style="width:100px;">${st.activePeriod }</div></td>
					<td align="center" class="pd-chill-tip" title="${st.expensesBudget }" onclick="updateMateEntry('${st.pmMateEntryId}');"><div class="short_div" style="width:100px;">${st.expensesBudget }</div></td>
					<td align="center" class="pd-chill-tip" title="${st.expectedResults }" onclick="updateMateEntry('${st.pmMateEntryId}');"><div class="short_div" style="width:100px;">${st.expectedResults }</div></td>
					<td align="center" class="pd-chill-tip" onclick="showAttachment('${st.pmMateEntryId}');"><c:if test="${st.uploadPicFlg==1}"><div class="pd-chill-tip hasAttachFile" title="已上传附件"> &nbsp;&nbsp; </div></c:if><c:if test="${st.uploadPicFlg!=1}"><div class="pd-chill-tip hasAttachFile1" title="未上传附件"> &nbsp;&nbsp; </div></c:if></td>
			  </tr>
		  </s:iterator>
	  </s:if>
	  <s:else>
	  	<tr style="height: 35px;">
			<td style="text-align: center;" colspan="8">对不起,请先新增记录.</td>
		</tr>
	  </s:else>
	</table>
	<input type="hidden" id="pageTotal" value="${page.totalCount}" >
	<div class="table_pager" style="margin-top:5px;">
		<p:page/>
	</div>
</div>
<script type="text/javascript">
//全选，反选
function checkAll(dom){
	var jdom=$(dom);
	if(jdom.attr('checked')==true){
		$("input[type='checkbox'][mateEntryId]").each(function(){
			$(this).attr('checked',true);
		});
	}else{
		$("input[type='checkbox'][mateEntryId]").each(function(){
			$(this).attr('checked',false);
		});
	}
}

/**
 * 查看附件
 */
function showAttachment(entityId){
	ymPrompt.win({
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=projectManager&filterType=image|office&onlyShow=true",
		width:500,
		height:300,
		title: '附件查看',
		afterShow:function(){},
		iframe:true,
		btn:[["完成",'close']]
	});
}

</script>