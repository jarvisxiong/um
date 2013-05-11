<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div id="TableDiv"  style="height: 100%;width:100%; overflow: hidden;">
	<%--搜索参数类型ID --%>
	<s:iterator value="parentIdList">
		<input type="hidden" id="parentId" name="parentModuleId" value='${entityId}'/>
	</s:iterator>
	<table width="100%" id="table1" class="content_table">
	  <thead>
      <tr style="height: 35px;">
          <th scope="col" align="center"  class="pd-chill-tip" title='序号 ' style="width: 35px;background: none;"><div class="ellipsisDiv_full" >序号 </div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='材料/设备类型' ><div class="ellipsisDiv_full" >材料/设备类型</div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='材料/设备名称'><div class="ellipsisDiv_full">材料/设备名称</div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='材料/设备类型编号' style="width:120px;"><div class="ellipsisDiv_full">材料/设备类型编号</div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='价格计算方式' style="width:100px"><div class="ellipsisDiv_full">价格计算方式</div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='是否启用 ' style="width:60px"><div class="ellipsisDiv_full">是否启用 </div></th>
       	  <th scope="col" align="center"  class="pd-chill-tip" title='操作' style="width:120px;"> <div class="ellipsisDiv_full" >操作</div></th>
      </tr>
	  </thead>
	  <s:iterator value="Page.result" status="st">
		  <tr class="mainTr" style="height: 35px;">
				<td align="center" onclick="getDetail('${costMateId}');" class="pd-chill-tip">${st.index+1}</td>
				<td align="center" onclick="getDetail('${costMateId}');" class="pd-chill-tip" 
						title='<p:code2name mapCodeName="mapCostMateModule" value="parentModuleId" />'>
					<p:code2name mapCodeName="mapCostMateModule" value="parentModuleId" />
				</td>
				<td align="left" onclick="getDetail('${costMateId}');" class="pd-chill-tip" title='${mateName}'>${mateName}</td>
				<td align="center" onclick="getDetail('${costMateId}');" class="pd-chill-tip" title='${mateBizCd}'>${mateBizCd} </td>
				<td align="center" onclick="getDetail('${costMateId}');" class="pd-chill-tip">
					<s:if test="calcTypeCd==1">定量</s:if>
					<s:elseif test="calcTypeCd==2">公式</s:elseif>
					<s:elseif test="calcTypeCd==3">浮动</s:elseif>
				</td>
				<td align="center" onclick="getDetail('${costMateId}');"><s:if test="enableFlg==1">是</s:if><s:else>否</s:else></td>
				
				<td align="center">
					<security:authorize ifAnyGranted="A_COST_STRAGE_ADMIN">
						<input id="priceBtn" name="priceBtn" style="width: 60px;" class="btn_new btn_set_new" onclick="doMateParam('${costMateId}');" type="button" value ="配置属性"/>
						<input id="delBtn" name="delBtn" style="width: 40px;" class="btn_new btn_del_new" onclick="doMateDelete('${costMateId}');" type="button" value ="删除"/>
					</security:authorize>
				</td>
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
	var url="${ctx}/cost/cost-mate!input.action?id="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cost-mate-input","设备信息",url,true);
}

function doMateDelete(id){
	var param ="";
	var parentId = $("#parentId").val();
	if(parentId != null && parentId != ""){
		param = {id:id,parentId:parentId};
	}else{
		param = {id:id,parentModuleId:arrCheck};
	}
	if(id!=null){
		if(confirm("确定要删除该条记录吗？")){
			var url="${ctx}/cost/cost-mate!delete.action";
			$.post(url,param,function(result) {
				if('success' == result){
					doQueryMate();
				}else if('fail' == result){
					alert("该设备有关联的型号或属性,不能删除!");
					return false;
				}else{
					alert("删除失败");
					return false;
				}
			});
		}
	}
}

function doMateParam(id){
	var url="${ctx}/cost/cost-mate-col!list.action?costMateId="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cost-mate-col-list","配置属性",url,true);	
}
</script>