<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div id="TableDiv" class="costTable" style="height: 100%;overflow: hidden;">
	<table width="100%" id="table1" class="content_table">
	  <thead>
      <tr style="height: 35px;">
          <th scope="col" align="center"  class="pd-chill-tip" title='序号 ' style="width: 35px;background: none;">序号 </th>
          <th scope="col" align="center"  class="pd-chill-tip" title='项目名称' style="width: 150px;">项目名称</th>
          <th scope="col" align="center"  class="pd-chill-tip" title='授权用户' >授权用户</th>
          <th scope="col" align="center"  class="pd-chill-tip" style="width:100px;">备注(关键字)</th>
          <th scope="col" align="center"  class="pd-chill-tip" style="width:150px;">操作</th>
          
          <%--
          <th scope="col" align="center"  class="pd-chill-tip" title='授权职位'>授权职位</th>
       	  <th scope="col" align="center"   class="pd-chill-tip" title='操作' style="width:70px;">操作</th>
           --%>
      </tr>
	  </thead>
	  <s:iterator value="costBudgetAuthVoList" status="st">
		  <tr class="mainTr" style="height: 35px;">
				<td align="center" class="pd-chill-tip">${st.index+1}<input type="hidden" id="authUiidOld${st.index}" value="${authUiids}"/></td>
				<td align="left" class="pd-chill-tip" 
					style="padding-left: 5px;width: 150px;"
					title='${sectionName}'
				>
					${sectionName}
				</td>
				<td align="left" class="pd-chill-tip" style="padding-left: 5px;" title='${authUserNames}'>
					 <input type="text" id="curUserName${st.index}" name="curUserName${st.index}" value="${authUserNames}" style="width: 99%;" readonly="readonly"/>
					 <input type="hidden" id="authUiid${st.index}" name="authUiid${st.index}" value="${authUiids}"/>
				</td>
				<td align="left" class="pd-chill-tip" style="padding-left: 5px;" title='${remark}'>
					<textarea id="remark${st.index}" name="remark${st.index}" style="border:none;width:99%;font-size: 13px; margin-top: 5px;margin-bottom: 5px;">${remark}</textarea>
				</td>
				<td align="left">
					<security:authorize ifAnyGranted="A_COST_BUD_ADMIN">
					 <input id="saveBtu" name="saveBtu" style="width: 40px;" class="btn_green" onclick="doSave('${costProjectSectionId}','${st.index}');" type="button" value ="保存"/>
					<%--
						<input id="delBtn" name="delBtn" style="width: 40px;" class="btn_red" onclick="doDelSection('${costBudgetAuthId}');" type="button" value ="收回"/>
					 --%>
					</security:authorize>
	 			 	<span id="tips${st.index}" style="color: red;"></span>
				</td>
		  </tr>
	  </s:iterator>
	</table>
	<input type="hidden" id="count" value='<s:property value="costBudgetAuthVoList.size()"/>'/>
	
	<div class="table_pager" style="margin-top:5px;">
		<p:page pageInfo="pageCostProjectSection"/>
	</div>	
</div>
<script type="text/javascript">
$(function(){
	var count = $("#count").val();
	for(var i = 0;i<count;i++){
		$("#curUserName"+i).userSelect({
		    muti:true,
		    nameField:'curUserName'+i,
		    cdField:'authUiid'+i
		});
	}
	//行点击事件
	$("tr.mainTr").click(function(){
		$(".trClicked").removeClass('trClicked');
		var o=$(this);
		o.children().eq(0).addClass('trClicked');
	});
});
function getDetail(id){
	var url="${ctx}/cost/cost-project-section!input.action?id="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cost-project-section-input","期数信息",url,true);
}
function doSave(id,index){
	clearTips();
	if(id != null){
		var authUiidOld = $("#authUiidOld"+index).val();
		var authUiid = $("#authUiid"+index).val();
		var remark = $("#remark"+index).val();
		
		var url="${ctx}/cost/cost-budget-auth!save.action";
		$.post(url,{sectionId:id,authUiidOld:authUiidOld, authUiid:authUiid, remark: remark},function(result) {
			if('success' == result){
				$('#tips'+index).html('保存成功!').show();//.fadeOut(5000)
				//doQueryAuth();
			}else{
				$('#tips'+index).html('保存失败!').show();
				return false;
			}
		});
	}
}
function doDelSection(id){
	if(id != null){
		if(confirm("确定要收回该项目的授权人员？")){
			var url="${ctx}/cost/cost-budget-auth!delete.action";
			$.post(url,{id:id},function(result) {
				if('success' == result){
					doQueryAuth();
				}else{
					alert("收回失败");
					return false;
				}
			});
		}
	}
}
function clearTips(){
	var count = $("#count").val();
	for(var i = 0;i<count;i++){
		$('#tips'+i).html('');
	}
}
</script>