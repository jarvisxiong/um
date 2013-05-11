<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>

<div id="resultList"  style="margin-left:10px;width:100%;clear: both;" >
	<div id="comDiv" style="overflow-x: auto; overflow-y: scroll;">
		<table  class="dataTable">
			<thead>
				<tr>
					<th rowspan="2"  width="35"  align="center"  style="background-image:url('');vertical-align: middle;" nowrap="nowrap" >序号</th>
					<th rowspan="2" nowrap="nowrap" style="vertical-align: middle;" title="项目编号"><div class='ellipsisDiv'>项目编号</div></th>
					<th rowspan="2" nowrap="nowrap" style="vertical-align: middle;" title="项目名称"><div class='ellipsisDiv'>项目名称</div></th>
					
					<th rowspan="2" nowrap="nowrap" style="vertical-align: middle;" title="项目特征描述"><div class='ellipsisDiv'>项目特征描述</div></th>
					<th rowspan="2" nowrap="nowrap" style="vertical-align: middle;" title="计量单位"><div class='ellipsisDiv'>计量单位</div></th>
					<th colspan='
							<s:if test="fieldCount >0 ">
								${fieldCount}
							</s:if>
							<s:else>3
							</s:else>
						' nowrap="nowrap" style="width:240px;overflow:hidden;"><div class="ellipsisDiv">算术平均值<s:property value="#propertySize"/></div></th>
						
					<s:iterator  value="batchNos"  var="bn"  status="index" >
						<th  style="width:240px;overflow:hidden;" colspan='
							<s:if test="fieldCount >0 ">
								${fieldCount}
							</s:if>
							<s:else>3
							</s:else>
						' nowrap="nowrap" title="${bibSupName}第${bn}次">
							<div class="ellipsisDiv">${bibSupName}第${bn}次</div>		
						</th>	
					</s:iterator>	
					
				</tr>
				<tr>
					<!-- 标底公司的HEAD -->
					<s:if test='fieldCount>0'>
						<s:if test='selectProperty.indexOf("quantitie")>-1'>
							<th style="width:80px;" nowrap="nowrap" title="工程量"><div class="ellipsisDiv">工程量</div></th>
						</s:if>
						<s:if test='selectProperty.indexOf("unitPrice")>-1'>
							<th style="width:80px;" nowrap="nowrap" title="单价"><div class="ellipsisDiv">单价</div></th>
						</s:if>
						<s:if test='selectProperty.indexOf("totalPrice")>-1'>
							<th style="width:80px;" nowrap="nowrap" title="合价"><div class="ellipsisDiv">合价</div></th>
						</s:if>	
					</s:if>
					<!-- 默认表头 -->
					<s:else>
						<th style="width:80px;" nowrap="nowrap" title="工程量"><div class="ellipsisDiv">工程量</div></th>
						<th style="width:80px;" nowrap="nowrap" title="单价"><div class="ellipsisDiv">单价</div></th>
						<th style="width:80px;" nowrap="nowrap" title="合价"><div class="ellipsisDiv">合价</div></th>
					</s:else>	
					
					
					<!-- 供应商公司的HEAD -->		
					<s:iterator  value="batchNos"  var="batchNo"  status="index" >
						<s:if test='fieldCount>0'>
							<s:if test='selectProperty.indexOf("quantitie")>-1'>
								<th style="width:80px;" nowrap="nowrap" title="工程量"><div class="ellipsisDiv">工程量</div></th>
							</s:if>
							<s:if test='selectProperty.indexOf("unitPrice")>-1'>
								<th style="width:80px;" nowrap="nowrap" title="单价"><div class="ellipsisDiv">单价</div></th>
							</s:if>
							<s:if test='selectProperty.indexOf("totalPrice")>-1'>
								<th style="width:80px;" nowrap="nowrap" title="合价"><div class="ellipsisDiv">合价</div></th>
							</s:if>	
						</s:if>
						<!-- 默认表头 -->
						<s:else>
							<th style="width:80px;" nowrap="nowrap" title="工程量"><div class="ellipsisDiv">工程量</div></th>
							<th style="width:80px;" nowrap="nowrap" title="单价"><div class="ellipsisDiv">单价</div></th>
							<th style="width:80px;" nowrap="nowrap" title="合价"><div class="ellipsisDiv">合价</div></th>
						</s:else>			
					</s:iterator>	
					
				</tr>
			</thead>
			<tbody>
				<s:iterator value="divisionVoList" var="divisionVoList"  status="index">
					<tr>
					<td  align="center" style="text-align: center;"><s:property value="#index.index+1"/></td>
					<td style="width:40px;overflow:hidden;" title="${itemNo}"><div class="ellipsisDiv" style="width:80px;overflow:hidden;">${itemNo}</div></td>
					<td style="width:40px;overflow:hidden;" title="${itemName}"><div class="ellipsisDiv" style="width:80px;overflow:hidden;">${itemName}</div></td>
					<td style="width:40px;overflow:hidden;" title="${itemDesc}"><div class="ellipsisDiv" style="width:80px;overflow:hidden;">${itemDesc}</div></td>	
					<td style="width:40px;overflow:hidden;" title="${measurement}"><div class="ellipsisDiv" style="width:40px;overflow:hidden;">${measurement}</div></td>			
						<s:iterator  value="bidSupCompareVos"  var="sup"  status="stat" >
						   
							<s:if test='fieldCount>0'>
								<s:if test='selectProperty.indexOf("quantitie")>-1'>
									<td title="${quantitie}"><div class="ellipsisDiv">${quantitie}</div></td>
								</s:if>
								<s:if test='selectProperty.indexOf("unitPrice")>-1'>
									<td title="${unitPrice}"><div class="ellipsisDiv">${unitPrice}</div></td>
								</s:if>
								<s:if test='selectProperty.indexOf("totalPrice")>-1'>
									<td title="${totalPrice}"><div class="ellipsisDiv">${totalPrice}</div></td>
								</s:if>				
							</s:if>
							<s:else>
								<td title="${quantitie}"><div class="ellipsisDiv">${quantitie}</div></td>
								<td title="${unitPrice}"><div class="ellipsisDiv">${unitPrice}</div></td>
								<td title="${totalPrice}"><div class="ellipsisDiv">${totalPrice}</div></td>
							</s:else>
						</s:iterator>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
	$(function() {
		var width=parseFloat($("#curDocWidth").val());	
		var rswidth=width-40;
		var height=document.body.clientHeight;
		height=height+80;
		$("#comDiv").css('width',rswidth+'px');
		$("#comDiv").css('height',height+'px');
		
		
	});	
	</script>
</div>