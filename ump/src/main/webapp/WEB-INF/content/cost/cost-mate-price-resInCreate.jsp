<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<table costmateid='${costMate.costMateId}' class="content_table cost_mate_table" stragetable='cost_mate_table' style="table-layout:none;">
	<%-- 2-标题 --%>
	<tr style="height: 30px;" headFlg="1" inoutflg="1">
		<td class="pd-chill-tip" title='序号 ' style="width: 35px;background: none;">
			序号
		</td>
		<td class="scopcol pd-chill-tip" title='材料或设备名称' style="min-width:100px;">
			<input type="hidden" fname="groupTypeName" value="${groupTypeName}"/>
			<div>${groupTypeName}</div>
			<input type="hidden" fname="costMatePriceId"/>	<%-- js设置 --%>
			<input type="hidden" fname="costMateId"/>		<%-- js设置 --%>
			<input type="hidden" fname="materialName" value="${groupTypeName}"/>
			<input type="hidden" fname="strageTypeCd" value="1"/>
			<input type="hidden" fname="headFlg" value="1"/>
		</td>
		<td class="scopcol pd-chill-tip" title='规格 '><input type="hidden" fname="specName" value="规格"/><div>规格 </div></td>
		<td class="scopcol pd-chill-tip" title='型号'><input type="hidden" fname="modelName" value="型号"/><div>型号</div></td>
		<td class="scopcol pd-chill-tip"><input type="hidden" fname="price" value="基准单价(元)"/><div>基准单价(元)</div></td>
		<%-- 自定义列 --%>
        <s:iterator value="costMateColList" var="cl">
	        <td class="pd-chill-tip <s:if test='extFlg == 1 || extPrice == 1 '>extOption</s:if>" title='${colName}'>
		        <div>${colName} </div>
				<input type="hidden" fname="f<s:if test='sequenceNo < 10'>0${sequenceNo}</s:if><s:else>${sequenceNo}</s:else>" value="f<s:if test='sequenceNo < 10'>0${sequenceNo}</s:if><s:else>${sequenceNo}</s:else>"/>
				<input type="hidden" fname="h<s:if test='sequenceNo < 10'>0${sequenceNo}</s:if><s:else>${sequenceNo}</s:else>" value="${colName}"/>
				<input type="hidden" fname="e<s:if test='sequenceNo < 10'>0${sequenceNo}</s:if><s:else>${sequenceNo}</s:else>" value="${extFlg}"/>
	        </td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="hidden" fname="p<s:if test='sequenceNo < 10'>0${sequenceNo}</s:if><s:else>${sequenceNo}</s:else>" value="数量"/>
				数量
			</td>
			</s:if>
        </s:iterator>
		<td dianlan='1' style="display:none;"><input type="hidden" fname="currentPrices" value="铜价(元)"/>铜价(元)</td>
		<td class="scopcol pd-chill-tip"><input type="hidden" fname="cgPrice" value="采购单价(元)"/><div>采购单价(元)</div></td>
        <td style="width:50px;text-align: center;"><input type="hidden" fname="buyNum" value="数量"/><div>数量</div></td>
		<td style="width:60px;" class="pd-chill-tip totalOption" ><input type="hidden" fname="totalPrice" value="小计(元)"/><div>小计(元)</div></td>
		<%--
        <td class="pd-chill-tip" title='备注' style="width:60px;"><input type="hidden" fname="memoDesc" value="备注"/><div>备注</div></td> 
		 --%>
        <td style="width:40px;text-align: center;"valign="middle"></td>
	</tr> 
	<%@ include file="cost-mate-price-resInAppend.jsp" %>
</table>