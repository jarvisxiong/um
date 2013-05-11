<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<style type="text/css">
.btn_red_50_20{
	width: 50px;
	height: 20px;
	line-height: 22px;
	background-color:rgb(172, 39, 39);
	color: #FFF;
	cursor:pointer;
	text-align: center;
}
.table_pager {
	float: right;
	margin: 5px 0;
}
.table_pager input{width:24px;height:24px;border:1px solid #ccc;}
#pageTo{width:24px;height:24px;}
</style>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css" />
<div id="TableDiv"  style="height: 100%;width:100% padding-left: 5px; overflow: hidden;">
	<table width="100%" id="result_table" class="content_table" >
	  <thead>
	        <tr class="header">
	        <%-- 
	            <th  scope="col" align="left"  width="4">编号</th>
	           --%>
	            <th scope="col" align="left"  width="4" >多经编号</th>
	            <%-- 
	            <th scope="col" align="left"  width="4">承租方</th>
	            --%>
	            <th scope="col" align="left"  width="4">经营项目</th>
	            <th scope="col" align="left"  width="4">位置区域</th>
	            <th scope="col" align="left"  width="4">面积㎡</th>
	            <th scope="col" align="left"  width="4">操作</th>
	        </tr>
	  </thead>
		  <s:iterator value="pageMulti.result">
			    <tr class="mainTr" >
			    <%-- 
			        <td align="left" class="pd-chill-tip" title='${multiNo}' onclick="multiEdit('${bisMultiId}'); "><div class="ellipsisDiv_full" >${multiNo}</div></td>
	  	    	--%>	
	  	    		<td align="left" class="pd-chill-tip" title='${multiName}' onclick="multiEdit('${bisMultiId}'); "><div class="ellipsisDiv_full" >${multiName}</div></td>
	  	    		<%-- 
	  	    		<td align="left" class="pd-chill-tip" title='${renterName}' onclick="multiEdit('${bisMultiId}'); "><div class="ellipsisDiv_full" >${renterName}</div></td>
	  	    		--%>
	  	    		<td align="left" class="pd-chill-tip" title='${operationProjectCd}' onclick="multiEdit('${bisMultiId}'); "><div class="ellipsisDiv_full" >${operationProjectCd}</div></td>
	  	    		<td align="left" class="pd-chill-tip" title='${operationArea}' onclick="multiEdit('${bisMultiId}'); "><div class="ellipsisDiv_full" >${operationArea}</div></td>
	  	    		<td align="left" class="pd-chill-tip" title='${square}' onclick="multiEdit('${bisMultiId}'); "><div class="ellipsisDiv_full" >${square}</div></td>
					<td align="center"><button class="btn_red_50_20" type="button" onclick="deleteFloor('${bisMultiId}');">删除</button></td>
		    	</tr>
		  </s:iterator>
	</table>
		<div class="table_pager" style="margin-top:5px;">
			<p:page pageInfo="pageMulti"/>
		</div>
</div>