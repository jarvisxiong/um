<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<div id="TableDiv"  style="height: 100%;width:100%; overflow: hidden;">
	<input type="hidden" name="parentId" value="${parentId}"/>
	<input type="hidden" name="moduleName" value="${moduleName}"/>
	<input type="hidden" name="enableFlg" value="${enableFlg}"/>
		<table width="100%" id="table1" class="content_table">
		  <thead>
	      <tr style="height: 35px;" align="center">
	          <th scope="col" class="pd-chill-tip" title='序号' style="width: 45px;background: none;"><div class="ellipsisDiv_full">序号</div></th>
	          <th scope="col" class="pd-chill-tip" title='大类' style="width: 100px;"><div class="ellipsisDiv_full">大类</div></th>
	          <th scope="col" class="pd-chill-tip" title='设备类型名称' style="width:80%;text-align: left;"><div class="ellipsisDiv_full">设备类型名称</div></th>
	          <th scope="col" class="pd-chill-tip" title='是否启用 ' style="width: 80px;"><div class="ellipsisDiv_full">是否启用 </div></th>
	       	  <th scope="col" class="pd-chill-tip" title='操作' style="width: 60px"><div class="ellipsisDiv_full" >操作</div></th>
	      </tr>
		  </thead>
		 
		  <s:iterator value="Page.result" status="st" var="vo">
		  	  <s:set name="curTypeName">${parentId}</s:set>
			  <tr class="mainTr" style="height: 35px;" align="center">
					<td onclick="getDetail('${costMateModuleId}');" class="pd-chill-tip">${st.index+1}</td>
					 	<s:if test="#curTypeName == #preTypeName">
					 		<td style="text-align:left;" onclick="getDetail('${costMateModuleId}');" class="pd-chill-tip">&nbsp;</td>
						</s:if>
						<s:else>
							<td style="text-align:left;" onclick="getDetail('${costMateModuleId}');" class="pd-chill-tip" title='<p:code2name mapCodeName="mapCostMateModule" value="parentId" />'>
								<p:code2name mapCodeName="mapCostMateModule" value="parentId" />
							</td>
						</s:else>
					<td style="text-align:left;" onclick="getDetail('${costMateModuleId}');" class="pd-chill-tip" title='${moduleName}'>${moduleName}</td>
					<td onclick="getDetail('${costMateModuleId}');"><s:if test="enableFlg==1">是</s:if><s:else>否</s:else></td>
					<td>
					<security:authorize ifAnyGranted="A_COST_STRAGE_ADMIN">
						<input id="delBtn" name="delBtn" style="width: 40px;" class="btn_new btn_del_new" onclick="doMateDelete('${costMateModuleId}','${parentId}');" type="button" value ="删除"/>
					</security:authorize>
					</td>
			  </tr>
		  	  <s:set name="preTypeName">${parentId}</s:set>
		  </s:iterator>
		</table>
		<input type="hidden" id="pageTotal" value="${page.totalCount}"/>
		<div class="table_pager" style="margin-top:5px;">
			<p:page/>
		</div>	
</div>
