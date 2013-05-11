<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>

<div id="TableDiv"  style="height: 100%;">
	<table width="100%" class="content_table">
	  <thead>
	        <tr>
	            <th scope="col" align="center" class="pd-chill-tip" title='公司名称'  style="width:80px; background: none;"><div class="ellipsisDiv_full" >公司名称</div></th>
	            <th scope="col" align="center" class="pd-chill-tip" title='合同编号' ><div class="ellipsisDiv_full" >合同编号</div></th>
	            <th scope="col" align="center" class="pd-chill-tip" title='合同名称' ><div class="ellipsisDiv_full" >合同名称</div></th>
	  <%--      <th scope="col" align="center" class="pd-chill-tip" title='合同总价' ><div class="ellipsisDiv_full" >合同总价</div></th> --%> 
	            <th scope="col" align="center" class="pd-chill-tip" title='变更后总价'><div class="ellipsisDiv_full" >变更后总价</div></th>
	            <th scope="col" align="center" class="pd-chill-tip" title='已完成产值'><div class="ellipsisDiv_full" >已完成产值</div></th>
	            <th scope="col" align="center" class="pd-chill-tip" title='累实际支付'><div class="ellipsisDiv_full" >累实际支付</div></th>
	            <th scope="col" align="center" class="pd-chill-tip" title='结算价'  ><div class="ellipsisDiv_full" >结算价</div></th>
	            <th scope="col" align="center" class="pd-chill-tip" title='合同状态' style="width:55px;"><div class="ellipsisDiv_full" >合同状态</div></th>
	            <th scope="col" align="center" class="pd-chill-tip" title='进度状态' style="width:55px;"><div class="ellipsisDiv_full" >进度状态</div></th>
	            <th scope="col" align="center" class="pd-chill-tip" title='当前状态' style="width:55px;"><div class="ellipsisDiv_full" >当前状态</div></th>
	<%--        <th scope="col" align="center"  width="7%">更新</th> --%>  
	            <security:authorize ifAnyGranted="A_CONTRACT_ADMIN">
	               <th scope="col" align="center"  width="60px">操作</th> 
               </security:authorize>
	        </tr>
	  </thead>
	  <s:iterator value="page.result">
	  <tr class="mainTr" >
	    <td onclick="getDetail('${contLedgerId}');">
	    	<p:code2name mapCodeName="projectMap" value="projectCd" />
	    </td>
	    <td onclick="getDetail('${contLedgerId}');" class="pd-chill-tip" title="${contNo}">${contNo} </td>
	    <td onclick="getDetail('${contLedgerId}');" class="pd-chill-tip" title='${contName}'>${contName}</td>
	<%--<td onclick="getDetail('${contLedgerId}');" class="pd-chill-tip" title='><%=JspUtil.formatMoney(JspUtil.findString("totalPrice"))%>' align="right"><%=JspUtil.formatMoney(JspUtil.findString("totalPrice"))%></td> --%>    
	    <td onclick="getDetail('${contLedgerId}');" class="pd-chill-tip" title='<%=JspUtil.formatMoney(JspUtil.findString("updateTotal"))%>' align="right"><%=JspUtil.formatMoney(JspUtil.findString("updateTotal"))%></td>
	    <td onclick="getDetail('${contLedgerId}');" class="pd-chill-tip" title='${completeNum}' align="right">${completeNum}</td>
	    <td onclick="getDetail('${contLedgerId}');" class="pd-chill-tip" title='${payNum}' align="right">${payNum}</td>
	    <td onclick="getDetail('${contLedgerId}');" class="pd-chill-tip" title='${clearPrice}' align="right">${clearPrice}</td>
	    <td align="center" onclick="getDetail('${contLedgerId}');">
		    <s:if test="contStatus==0">未完未结</s:if>
		    <s:elseif test="contStatus==1">
			    <s:set var ="haveDelay"><s:property value="%{getPlanEndFlg(realEndDate)}"/></s:set>
			    <s:if test="#haveDelay=='true'"><font color="#FF0000">已完未结</font></s:if>
			    <s:else><font color="#0167A2">已完未结</font></s:else>
		    </s:elseif>
		    <s:elseif test="contStatus==2">已完已结</s:elseif>
	    </td>
	    <td align="center" onclick="getDetail('${contLedgerId}');">
	     	<s:if test="procStatus==0">未开工</s:if>
	     	<s:elseif test="procStatus==1">按期</s:elseif>
	     	<s:elseif test="procStatus==2">
	        	<font color="#FF0000">滞后</font>
	     	</s:elseif>
	     	<s:elseif test="procStatus==3"><font color="#f46614">延期竣工</font></s:elseif>
	     	<s:elseif test="procStatus==4">已竣工</s:elseif>
	    </td>
	    <td align="center">
		    <s:if test="contAuditStatus==0">未提交</s:if>
		    <s:elseif test="contAuditStatus==1">待审核</s:elseif>
		    <s:elseif test="contAuditStatus==2">已审核</s:elseif>
	    </td>
		<%--    
		<td>
	     	<s:date name ="updatedDate" format="yy-MM-dd"/>
	    </td>--%>	
	    <security:authorize ifAnyGranted="A_CONTRACT_ADMIN">
	    <td> 
	    <%--
	    <s:if test="contAuditStatus!=2&&creator==nowUiid">
	    	<button type="button" class="btn_red_35_20" onclick="doDeleteCont('${contLedgerId}');">删除</button>
	    </s:if>
	    <s:else>
	     --%>
		    <s:if test="contAuditStatus!=2">
		    	<input type="button" class="btn_new btn_del_new" onclick="doDeleteCont('${contLedgerId}');" value="删除"/>
		    </s:if>
		<%-- 
	    </s:else>
	    --%>
	    </td> 
	    </security:authorize>
	  </tr>
	  </s:iterator>
	</table>
</div>
<div style="float: right;padding-top:10px;padding-right:10px;">
	<div style="width: 100%; margin-right:5px;">
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
</div>