<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%@page import="com.hhz.ump.util.JspUtil"%>
<style type="text/css">
<!--
.addFirst {
	background-color: #61becf;
	color:#ffffff;
}
-->
</style>
<div style="border: 1px solid #cccccc; margin-top:20px; margin-left: 8px; margin-right: 8px;">
   	<div style="cursor:hand ;overflow-y:scroll;word-break:break-all; height:210px;" id ="abcd" >
	    <table class="content_table" border="0" cellpadding="0" cellspacing="0" style="margin-top: 0px;">
		    <tr>
			    <th align="center">多经编号</th>
			    <th align="center">经营项目</th>
		    </tr>
		    <s:iterator value="listBisMultis">
			    <tr floorId="${bisFloorId}" multiName="${multiName }" class="mainTr">
					<td align="left" title='${multiName}' style="padding-left: 8px;"><div class="ellipsisDiv_full" >${multiName}</div></td>
					<td align="left" title='${operationProjectCd}' style="padding-left: 8px;"><div class="ellipsisDiv_full" >${operationProjectCd}</div></td>
			    </tr>
		    </s:iterator>
		    <s:if test="listBisMultis.size == 0">
		    	<tr>
		    		<td colspan="2"><span class="color_red">没有搜索到相关的广告位或者承租方家。</span></td>
		    	</tr>
		    </s:if>
	    </table>
	</div>
</div>
