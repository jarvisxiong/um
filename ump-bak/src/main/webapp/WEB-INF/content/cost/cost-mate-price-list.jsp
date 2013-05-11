<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<style>
	#listOfPrice thead tr th{
		text-align: center;
	}
	#listOfPrice tbody tr td{
		text-align: center;
	}
	.syscol{
		display: none;
	}
</style>

<div id="TableDiv"  style="height: 100%;width:100%; overflow: auto;">
	<table id="listOfPrice" class="content_table" style="width:100%;" >
	  <thead>
      <tr style="height: 35px;">
          <th scope="col" align="left"    class="pd-chill-tip" title='序号 ' style="width: 35px;background: none;"><div class="ellipsisDiv_full">序号 </div></th>
          <th class="syscol pd-chill-tip keyOption" title='关键字1'><div class="ellipsisDiv_full" >[关键字1]</div></th>
          <th class="syscol pd-chill-tip keyOption" title='关键字2'><div class="ellipsisDiv_full" >[关键字2]</div></th>
          <th class="syscol pd-chill-tip keyOption" title='关键字3'><div class="ellipsisDiv_full" >[关键字3]</div></th>
          <th class="syscol pd-chill-tip keyOption" title='关键字4'><div class="ellipsisDiv_full" >[关键字4]</div></th>
          <th class="syscol pd-chill-tip keyOption" title='材料/设备名称'><div class="ellipsisDiv_full" >${groupTypeName}</div></th>
          <th class="syscol pd-chill-tip keyOption" title='规格 '><div class="ellipsisDiv_full" >[规格] </div></th>
          <th class="syscol pd-chill-tip keyOption" title='型号'><div class="ellipsisDiv_full">[型号]</div></th>
          <th class="syscol pd-chill-tip keyOption" title='单价(元)'><div class="ellipsisDiv_full">[单价(元)]</div></th>
          <s:iterator value="costMateColList" var="cl">
         	 <th scope="col" class="pd-chill-tip 
         	 	<s:if test='extFlg == 1'>
         	      extOption
         	 	</s:if>
         	  " title='${colName}' 
         	 ><div class="ellipsisDiv_full">${colName} </div></th>
          </s:iterator>
          <%--
          <th scope="col" class="pd-chill-tip" title='备注'><div class="ellipsisDiv_full">备注</div></th>
          <th scope="col" class="pd-chill-tip" title='是否启用 '><div class="ellipsisDiv_full">是否启用 </div></th>
          <th scope="col" class="pd-chill-tip" title='创建日期 ' style="width: 80px;"><div class="ellipsisDiv_full">创建日期 </div></th>
           --%>
          <th scope="col" class="pd-chill-tip" title='操作 '><div class="ellipsisDiv_full">操作</div></th>
      </tr>
	  </thead>
	  <tbody>
	  <s:iterator value="Page.result"  var="voItem" status="st">
		  <tr class="mainTr" style="height: 35px;" title="可用状态:<s:if test="enableFlg==1">是</s:if><s:elseif test="enableFlg==0">否</s:elseif>，创建日期:<s:date name="createdDate" format="yyyy-MM-dd"/>">
				<td class="pd-chill-tip" id="rowId_${st.index+1}">${st.index+1}</td>
				<td class="syscol pd-chill-tip" title='${groupName}'   style="text-align: left;" item="groupName">${groupName}</td>
				<td class="syscol pd-chill-tip" title='${groupName2}'  style="text-align: left;" item="groupName2">${groupName2}</td>
				<td class="syscol pd-chill-tip" title='${groupName3}'  style="text-align: left;" item="groupName3">${groupName3}</td>
				<td class="syscol pd-chill-tip" title='${groupName4}'  style="text-align: left;" item="groupName4">${groupName4}</td>
				<td class="syscol pd-chill-tip" title='${materialName}'  style="text-align: left;" item="materialName" id="rowId_${st.index+1}">${materialName}</td>
				<td class="syscol pd-chill-tip" title='${specName}'  style="text-align: left;">${specName}</td>
				<td class="syscol pd-chill-tip" title='${modelName}' style="text-align: left;">${modelName}</td>
				<td class="syscol pd-chill-tip" title='${price}'     style="text-align: center;">${price} </td>
				<%--自定义列头的值 --%>
				<s:iterator value="costMateColList" var="cl" status="st">
					<s:if test="colField=='f01'">
						<td class="pd-chill-tip" title="${voItem.f01}" style="text-align: left;">${voItem.f01}</td>
					</s:if>
					<s:elseif test="colField=='f02'">
						<td class="pd-chill-tip" title="${voItem.f02}" style="text-align: left;">${voItem.f02}</td>
					</s:elseif>
					<s:elseif test="colField=='f03'">
						<td class="pd-chill-tip" title="${voItem.f03}" style="text-align: left;">${voItem.f03}</td>
					</s:elseif>
					<s:elseif test="colField=='f04'">
						<td class="pd-chill-tip" title="${voItem.f04}" style="text-align: left;">${voItem.f04}</td>
					</s:elseif>
					<s:elseif test="colField=='f05'">
						<td class="pd-chill-tip" title="${voItem.f05}" style="text-align: left;">${voItem.f05}</td>
					</s:elseif>
					<s:elseif test="colField=='f06'">
						<td class="pd-chill-tip" title="${voItem.f06}" style="text-align: left;">${voItem.f06}</td>
					</s:elseif>
					<s:elseif test="colField=='f07'">
						<td class="pd-chill-tip" title="${voItem.f07}" style="text-align: left;">${voItem.f07}</td>
					</s:elseif>
					<s:elseif test="colField=='f08'">
						<td class="pd-chill-tip" title="${voItem.f08}" style="text-align: left;">${voItem.f08}</td>
					</s:elseif>
					<s:elseif test="colField=='f09'">
						<td class="pd-chill-tip" title="${voItem.f09}" style="text-align: left;">${voItem.f09}</td>
					</s:elseif>
					<s:elseif test="colField=='f10'">
						<td class="pd-chill-tip" title="${voItem.f10}" style="text-align: left;">${voItem.f10}</td>
					</s:elseif>
					<s:elseif test="colField=='f11'">
						<td class="pd-chill-tip" title="${voItem.f11}" style="text-align: left;">${voItem.f11}</td>
					</s:elseif>
					<s:elseif test="colField=='f12'">
						<td class="pd-chill-tip" title="${voItem.f12}" style="text-align: left;">${voItem.f12}</td>
					</s:elseif>
					<s:elseif test="colField=='f13'">
						<td class="pd-chill-tip" title="${voItem.f13}" style="text-align: left;">${voItem.f13}</td>
					</s:elseif>
					<s:elseif test="colField=='f14'">
						<td class="pd-chill-tip" title="${voItem.f14}" style="text-align: left;">${voItem.f14}</td>
					</s:elseif>
					<s:elseif test="colField=='f15'">
						<td class="pd-chill-tip" title="${voItem.f15}" style="text-align: left;">${voItem.f15}</td>
					</s:elseif>
					<s:elseif test="colField=='f16'">
						<td class="pd-chill-tip" title="${voItem.f16}" style="text-align: left;">${voItem.f16}</td>
					</s:elseif>
					<s:elseif test="colField=='f17'">
						<td class="pd-chill-tip" title="${voItem.f17}" style="text-align: left;">${voItem.f17}</td>
					</s:elseif>
					<s:elseif test="colField=='f18'">
						<td class="pd-chill-tip" title="${voItem.f18}" style="text-align: left;">${voItem.f18}</td>
					</s:elseif>
					<s:elseif test="colField=='f19'">
						<td class="pd-chill-tip" title="${voItem.f19}" style="text-align: left;">${voItem.f19}</td>
					</s:elseif>
					<s:elseif test="colField=='f20'">
						<td class="pd-chill-tip" title="${voItem.f20}" style="text-align: left;">${voItem.f20}</td>
					</s:elseif>
					<s:elseif test="colField=='f21'">
						<td class="pd-chill-tip" title="${voItem.f21}" style="text-align: left;">${voItem.f21}</td>
					</s:elseif>
					<s:elseif test="colField=='f22'">
						<td class="pd-chill-tip" title="${voItem.f22}" style="text-align: left;">${voItem.f22}</td>
					</s:elseif>
					<s:elseif test="colField=='f23'">
						<td class="pd-chill-tip" title="${voItem.f23}" style="text-align: left;">${voItem.f23}</td>
					</s:elseif>
					<s:elseif test="colField=='f24'">
						<td class="pd-chill-tip" title="${voItem.f24}" style="text-align: left;">${voItem.f24}</td>
					</s:elseif>
					<s:elseif test="colField=='f25'">
						<td class="pd-chill-tip" title="${voItem.f25}" style="text-align: left;">${voItem.f25}</td>
					</s:elseif>
					<s:elseif test="colField=='f26'">
						<td class="pd-chill-tip" title="${voItem.f26}" style="text-align: left;">${voItem.f26}</td>
					</s:elseif>
					<s:elseif test="colField=='f27'">
						<td class="pd-chill-tip" title="${voItem.f27}" style="text-align: left;">${voItem.f27}</td>
					</s:elseif>
					<s:elseif test="colField=='f28'">
						<td class="pd-chill-tip" title="${voItem.f28}" style="text-align: left;">${voItem.f28}</td>
					</s:elseif>
					<s:elseif test="colField=='f29'">
						<td class="pd-chill-tip" title="${voItem.f29}" style="text-align: left;">${voItem.f29}</td>
					</s:elseif>
					<s:elseif test="colField=='f30'">
						<td class="pd-chill-tip" title="${voItem.f30}" style="text-align: left;">${voItem.f30}</td>
					</s:elseif>
				</s:iterator>
				<%--
				<td class="pd-chill-tip" title='${memoDesc}'     style="text-align: left;"><div class="ellipsisDiv_full">${memoDesc} </div></td>
				<td class="pd-chill-tip" title='<s:if test="enableFlg==1">是</s:if><s:elseif test="enableFlg==0">否</s:elseif>'>
					<s:if test="enableFlg==1">是</s:if><s:elseif test="enableFlg==0">否</s:elseif>
				</td>
				<td class="pd-chill-tip" title='<s:date name="createdDate" format="yyyy-MM-dd"/>'><s:date name="createdDate" format="yyyy-MM-dd"/> </td>
				 --%>
				<td align="center">
					<security:authorize ifAnyGranted="A_COST_STRAGE_ADMIN">
						<input id="btnEdt" name="btnEdt" style="width: 40px;" class="btn_new btn_blue_new" onclick="getDetail('${costMatePriceId}');"  type="button" value ="编辑"/>
						<input id="delBtn" name="delBtn" style="width: 40px;" class="btn_red" onclick="doMatePriceDelete('${costMatePriceId}');" type="button" value ="删除"/>
					</security:authorize>
				</td>
		  </tr>
	  </s:iterator>
	  </tbody>
	</table>
	<div class="table_pager" style="margin-top:5px;">
		<p:page/>
	</div>	
</div>
<script type="text/javascript">

function getDetail(id){
	var costMateId = $('#costMateId').val();
	var url="${ctx}/cost/cost-mate-price!input.action?id="+id+"&costMateId="+costMateId;
	location.href=url;
}

function doMatePriceDelete(id){
	if(id!=null){
		if(confirm("确定要删除该条记录吗？")){
			var url="${ctx}/cost/cost-mate-price!delete.action";
			$.post(url,{id:id},function(result) {
				if('success' == result){
					doQueryPrice();
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
	  parent.TabUtils.newTab("cost-mate-col-list","设备属性信息",url,true);	
}
</script>