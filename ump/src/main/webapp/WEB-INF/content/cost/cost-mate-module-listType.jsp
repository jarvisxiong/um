<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div id="TableDiv"  style="height: 100%;width:100%; overflow: hidden;">
	<input type="hidden" name="parentId" value="${parentId}"/>
	<input type="hidden" name="moduleName" value="${moduleName}"/>
	<input type="hidden" name="enableFlg" value="${enableFlg}"/>
		<table width="100%" id="table1" class="content_table">
		  <thead>
	      <tr style="height: 35px;" align="center">
	          <th scope="col" class="pd-chill-tip" title='序号' style="width: 5%;background: none;"><div class="ellipsisDiv_full">序号</div></th>
	          <th scope="col" class="pd-chill-tip" title='设备类型名称' style="width: 75%;"><div class="ellipsisDiv_full">设备类型名称</div></th>
	          <th scope="col" class="pd-chill-tip" title='是否启用 ' style="width: 10%;"><div class="ellipsisDiv_full">是否启用 </div></th>
	       	  <th scope="col" class="pd-chill-tip" title='操作' style="width:10%;"><div class="ellipsisDiv_full" >操作</div></th>
	      </tr>
		  </thead>
		 
		  <s:iterator value="Page.result" status="st" var="vo">
			  <tr class="mainTr" style="height: 35px;" align="center">
					<td onclick="getDetail('${costMateModuleId}');" class="pd-chill-tip">${st.index+1}</td>
					<td style="text-align:left;" onclick="getDetail('${costMateModuleId}');" class="pd-chill-tip" title='${moduleName}'>${moduleName}</td>
					<td onclick="getDetail('${costMateModuleId}');"><s:if test="enableFlg==1">是</s:if><s:else>否</s:else></td>
					<td>
						<security:authorize ifAnyGranted="A_COST_STRAGE_ADMIN">
							<input type="button" id="delBtn" name="delBtn" class="btn_new btn_del_new" onclick="doMateDelete('${costMateModuleId}','${parentId}');" value ="删除"/>
				  		</security:authorize>
			  		</td>
			  </tr>
		  </s:iterator>
		</table>
		<input type="hidden" id="pageTotal" value="${page.totalCount}"/>
		<div class="table_pager" style="margin-top:5px;">
			<p:page/>
		</div>	
</div>
