<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div id="TableDiv"  style="height: 100%;width:100%; overflow: hidden;">
	<table width="100%" id="table1" class="content_table">
	  <thead>
      <tr style="height: 35px;">
          <th scope="col" align="center"  class="pd-chill-tip" title='序号 ' style="width: 35px;background: none;"><div class="ellipsisDiv_full" >序号 </div></th>
          <%--
          <th scope="col" align="center"  class="pd-chill-tip" title='标题'><div class="ellipsisDiv_full" >标题</div></th>
           --%>
          <th scope="col" align="center"  class="pd-chill-tip" title='战略合同名称'><div class="ellipsisDiv_full">采购合同编号</div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='项目名称'><div class="ellipsisDiv_full">项目名称</div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='设备名称'><div class="ellipsisDiv_full">材料设备名称</div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='战略合同名称'><div class="ellipsisDiv_full">领用单位合同号</div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='领用单位 '><div class="ellipsisDiv_full">领用单位 </div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='产生日期 '><div class="ellipsisDiv_full">产生日期 </div></th>
      	  <th scope="col" align="center"   class="pd-chill-tip" title='操作' style="width:120px;"> <div class="ellipsisDiv_full" >操作</div></th>
      </tr>
	  </thead>
	  <s:iterator value="page.result" status="st" var="tmp">
		  <tr class="mainTr" style="height: 35px;">
				<td align="center" onclick="getDetail('${costStrageMateId}');" class="pd-chill-tip">${st.index+1}</td>
				<td align="left" onclick="getDetail('${costStrageMateId}');" class="pd-chill-tip" >${tmp.contNo}</td>
				<td align="left" onclick="getDetail('${costStrageMateId}');" class="pd-chill-tip" title='${tmp.projectName}'>${tmp.projectName} </td>
				<td align="left" onclick="getDetail('${costStrageMateId}');" class="pd-chill-tip" title='${tmp.mateName}'>${tmp.mateName}</td>
				<td align="left" onclick="getDetail('${costStrageMateId}');" class="pd-chill-tip" title="${tmp.takeUnitContNo}">${tmp.takeUnitContNo}</td>
				<td align="left" onclick="getDetail('${costStrageMateId}');" class="pd-chill-tip" title="${tmp.takeUnitName}">${tmp.takeUnitName}</td>
				<td align="center">${tmp.createdDate}</td>
				<td align="center">
					<input id="delBtn" name="delBtn" style="width: 40px;" class="btn_red" onclick="doMateDelete('${costStrageMateId}');" type="button" value ="删除"/>
				</td>
		  </tr>
	  </s:iterator>
	</table>
	<div class="table_pager" style="margin-top:5px;">
		<p:page/>
	</div>	
</div>
