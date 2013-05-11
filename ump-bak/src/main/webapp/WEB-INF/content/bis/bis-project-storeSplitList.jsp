<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/global.jsp" %>
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
</style>
	<table width="100%" id="result_table" class="content_table" >
	  <thead>
	        <tr class="header">
	            <th scope="col" align="left"  width="4">编号</th>
	            <th scope="col" align="left"  width="4">面积㎡(图纸)</th>
	             <th scope="col" align="left"  width="4">面积㎡(实测)</th>
	            <th scope="col" align="left"  width="4"><div class="ellipsisDiv_full" >套内面积㎡(图纸)</div></th>
                <th scope="col" align="left"  width="4"><div class="ellipsisDiv_full" >套内面积㎡(实测)</div></th>
	        <!--   <th scope="col" align="left"  width="4">公摊面积㎡</th> -->       
            	<th scope="col" align="left"  width="4">开业时间</th>
            	<th scope="col" align="left"  width="4">销售单价</th>
            	<th scope="col" align="left"  width="4">计租面积</th>
	            <th scope="col" align="left"  width="3">操作</th>
	        </tr>
	        <s:if test="bisStoreLis.size==0">
	        <tr>  
       		 <td colspan="7">
			  <div id="massageTab" align="center" class="inform_div" style="color:#6bad42 ;font-size:16px;font-weight:bold;height:10px;line-height: 100px;">暂无拆分数据！</div>
			  </td>
			</tr>
			
		    </s:if>
	  </thead>
	  <tbody>
			  <s:iterator value="bisStoreLis">
				    <tr class="mainTr" >
		  	    		<td align="left" class="pd-chill-tip" title='${storeNo}'  "><div class="ellipsisDiv_full" >${storeNo}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${square}'  "><div class="ellipsisDiv_full" >${square}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${squareReal}'  "><div class="ellipsisDiv_full" >${squareReal}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${innerSquare}'  "><div class="ellipsisDiv_full" >${innerSquare}</div></td>
  	    				<td align="left" class="pd-chill-tip" title='${innerSquareReal}'  "><div class="ellipsisDiv_full" >${innerSquareReal}</div></td>
		 <!-- 	    	<td align="left" class="pd-chill-tip" title='${publicSquare}'  "><div class="ellipsisDiv_full" >${publicSquare}</div></td> -->     
		  	    		<td align="left"><s:date name ="openDate" format="yy-MM-dd" /></td>
		  	    		<td align="left" class="pd-chill-tip" title='${sellPrice}'  "><div class="ellipsisDiv_full" >${sellPrice}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${rentSquare}'  "><div class="ellipsisDiv_full" >${rentSquare}</div></td>
						<td>
						<s:if test="ifSubmit==1">
						已提交
						</s:if>
						<s:else>
						<button class="btn_red_50_20" type="button" onclick="deleteSplitStore('${bisStoreId}');">删除</button>
						</s:else>
						</td>
			    	</tr>
			  </s:iterator>

	</tbody>
	</table>