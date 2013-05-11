<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<div id="TableDiv"  style="height: 100%;width:100% padding-left: 8px; overflow: hidden;">
	<table width="100%" id="table1" class="content_table">
	  <thead>
	        <tr><th scope="col" align="left"  width="20%" style="padding-left:5px;background: none;">供方名称</th>
	            <th scope="col" align="left"  width="24%" style="padding-left:5px;">供应类别</th>
	            <th scope="col" align="left"  width="8%"  style="padding-left:5px;">级别</th>
	            <th scope="col" align="left"  width="8%"  style="padding-left:5px;">考察</th>
	            <th scope="col" align="left"  width="9%"  style="padding-left:5px;">合作情况</th>
	            <th scope="col" align="left"  width="8%"  style="padding-left:5px;">状态</th>
	            <th scope="col" align="left"  width="15%" style="padding-left:5px;">备注</th>
	            <security:authorize ifAnyGranted="A_SUP_DEL">
	             <th scope="col" align="left"  width="8%" style="padding-left:5px;">操作</th> 
	             </security:authorize>
	        </tr>
	  </thead>
	  <s:iterator value="page.result">
	  <tr class="mainTr" style="overflow: hidden; white-space: nowrap;height:30px;">
	    	<td onclick="getSup('${supBasicId}');" style="padding-left:8px;"class="pd-chill-tip" title="${supName}">
	    		<s:if test="supManaEval==3">
	     			<font color="#FF0000">${supName}</font>
	   	 		</s:if>
	   	 		<s:elseif test="supManaEval==5">
	     			<font color="#FF0000">${supName}</font>
	    		</s:elseif>
	    		<s:else>${supName}</s:else>
	    	</td>
	    	<td class="pd-chill-tip" title="<p:code2name mapCodeName="mapToItemNames" value="supTypeSn" />" style="overflow:hidden;padding-left:8px;padding-right:8px;" onclick="getSup('${supBasicId}');">
	    		<s:if test="supManaEval==3">
	     			<font color="#FF0000"><p:code2name mapCodeName="mapToItemNames" value="supTypeSn" /></font>
	    		</s:if>
	    		<s:elseif test="supManaEval==5">
	     			<font color="#FF0000"><p:code2name mapCodeName="mapToItemNames" value="supTypeSn" /></font>
	    		</s:elseif>
	    		<s:else><p:code2name mapCodeName="mapToItemNames" value="supTypeSn" /></s:else>
	    	</td>
	    	<td onclick="getSup('${supBasicId}');"style="padding-left:8px;">
	    		<s:if test="supManaEval==3||supManaEval==5">
	    			<font color="#FF0000">
	      			<p:code2name mapCodeName="mapSupEvaluate" value="supManaEval" />
	      			</font>
	      		</s:if>
	      		<s:else>
	      			<p:code2name mapCodeName="mapSupEvaluate" value="supManaEval" />
	      		</s:else>
	    	</td>
			<td onclick="getSup('${supBasicId}');" style="padding-left:8px;">
	     		<s:if test="supManaEval==3||supManaEval==5">
	     			<font color="#FF0000"><p:code2name mapCodeName="mapSupExamResu" value="supExamResu" /></font>
	     		</s:if>
	     		<s:else><p:code2name mapCodeName="mapSupExamResu" value="supExamResu" /></s:else>
	    	</td>
	    	<td onclick="getSup('${supBasicId}');" style="padding-left:8px;">
	      		<s:if test="supManaEval==3||supManaEval==5">
	      			<font color="#FF0000">
	       				<p:code2name mapCodeName="mapCooperated" value="supDetails[0].cooperated" />
	      			</font>
	      		</s:if>
	      		<s:else>
	       			<p:code2name mapCodeName="mapCooperated" value="supDetails[0].cooperated" />
	      		</s:else>
	    	</td>
	    	<td onclick="getSup('${supBasicId}');" style="padding-left:8px;">
	    		<s:if test="supAudit!=null">
	      			<p:code2name mapCodeName="mapSupAudit" value="supAudit" />
	    		</s:if>
	     		<s:else>未提交</s:else>
	    	</td>
			<td onclick="getSup('${supBasicId}');"class="pd-chill-tip" title="${remark}">
				<s:if test="supManaEval==3">
					<font color="#FF0000">${remark}</font>
				</s:if>
				<s:elseif test="supManaEval==5">
					<font color="#FF0000">${remark}</font>
				</s:elseif>
				<s:else>${remark}</s:else>
			</td>
			<security:authorize ifAnyGranted="A_SUP_DEL">
			<td style="padding-left:8px;">
				<s:if test="supAudit!=2">
					<input type="button" id="DelBtn" class="btn_new btn_red_new" onclick="doDeleteSup('${supBasicId}');" value="删除"/>
				</s:if>
			</td>
			</security:authorize>
	  </tr>
	  </s:iterator>
	</table>
</div>
<p/>
<div style="padding:10px 10px 0 0;" class="fRight">
<p:page />
<%--
	<div style="width: 100%;">
		&nbsp;共有&nbsp;${page.totalCount}&nbsp;条记录&nbsp;&nbsp;&nbsp;&nbsp;
		当前第 ${page.pageNo}/${page.totalPages}&nbsp;页
		 <s:if test="page.hasPre">
			<img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_up.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_up_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_up.gif');" onclick="jumpPage('${page.prePage}');"/>
		 </s:if>
		 <s:else>
		 	<img align="absmiddle" src="${ctx}/images/desk2/page_up_grey.gif" />
		 </s:else>
		 <s:if test="page.hasNext">
			<img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_down.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_down_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_down.gif');" onclick="jumpPage('${page.nextPage}');"/>
		 </s:if>
		 <s:else>
		 	<img align="absmiddle" src="${ctx}/images/desk2/page_down_grey.gif" />
		 </s:else>
		 <input type="text" id="pageNo" style="height: 15px; width: 20px; text-align: center;" value="${page.pageNo}"/>
		 <a href="#" onblur="this.blur();" onclick="jumpPage($(this).prev().val()); return false;">GO</a>
	</div>
 --%>
</div>