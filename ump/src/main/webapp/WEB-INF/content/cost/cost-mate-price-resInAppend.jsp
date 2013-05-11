<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
 
 <%--
 templateBean.strategyMatePrices[<s:property value="#s1.index" />].
  --%>
  <s:iterator value="page.result"  var="voItem" status="st">
	<%-- 2-数据 --%>
	<tr class="mainTr" headFlg='0' costmatepriceid='${resCostMatePriceId}' inoutflg="1">
		<td align="center">${st.index+1}</td>
		<td class="scopcol" title='${materialName}' >
			<input type="hidden" fname="materialName"value="${materialName}"/>
			<div>${materialName}</div>
			<input type="hidden" fname="costMatePriceId" value="${resCostMatePriceId}"/>
			<input type="hidden" fname="costMateId" value="${costMate.costMateId}"/>
			<input type="hidden" fname="strageTypeCd" value="1"/>
			<input type="hidden" fname="headFlg" value="0"/>
		</td>
		<td class="scopcol" title='${specName}' ><input type="hidden" fname="specName"    value="${specName}"/><div>${specName}</div></td>
		<td class="scopcol" title='${modelName}'><input type="hidden" fname="modelName"   value="${modelName}"/><div>${modelName}</div></td>
		<td class="scopcol" title='${price}'><input type="hidden" fname="price"       value="${price}"/><div>${price}</div></td>
		<td class="scopcol" title='${cgprice}'>
			<s:if test="cgPrice == ''||cgPrice == null">
				<input type="hidden" fname="cgPrice" value="${price}"/>
				<div>${price}</div>
			</s:if>
			<s:else>
				<input type="hidden" fname="cgPrice" value="${cgPrice}"/>
				<div>${cgPrice}</div>
			</s:else>
		</td>
		
		<%--自定义列头的值 --%>
		<s:iterator value="costMateColList" var="cl" status="st">
		<s:if test="colField=='f01'">
			<td title="${voItem.f01}">
				<div>${voItem.f01}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f01" value="${voItem.f01}"/><%--记住第一个位置,计算有用 --%>
				<input type="hidden"  fname="h01" value="${colName}"/>
				<input type="hidden"  fname="e01" value="${extFlg}"/>
		    </td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p01" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:if>
		<s:elseif test="colField=='f02'">
			<td title="${voItem.f02}">
			    <div>${voItem.f02}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f02" value="${voItem.f02}"/>
				<input type="hidden"  fname="h02" value="${colName}"/>
				<input type="hidden"  fname="e02" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p02" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f03'">
			<td title="${voItem.f03}">
			    <div>${voItem.f03}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f03" value="${voItem.f03}"/>
				<input type="hidden"  fname="h03" value="${colName}"/>
				<input type="hidden"  fname="e03" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p03" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f04'">
			<td title="${voItem.f04}">
			    <div>${voItem.f04}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f04" value="${voItem.f04}"/>
				<input type="hidden"  fname="h04" value="${colName}"/>
				<input type="hidden"  fname="e04" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p04" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f05'">
			<td title="${voItem.f05}">
			    <div>${voItem.f05}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f05" value="${voItem.f05}"/>
				<input type="hidden"  fname="h05" value="${colName}"/>
				<input type="hidden"  fname="e05" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p05" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f06'">
			<td title="${voItem.f06}">
			    <div>${voItem.f06}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f06" value="${voItem.f06}"/>
				<input type="hidden"  fname="h06" value="${colName}"/>
				<input type="hidden"  fname="e06" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p06" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f07'">
			<td title="${voItem.f07}">
			    <div>${voItem.f07}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f07" value="${voItem.f07}"/>
				<input type="hidden"  fname="h07" value="${colName}"/>
				<input type="hidden"  fname="e07" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p07" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f08'">
			<td title="${voItem.f08}">
			    <div>${voItem.f08}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f08" value="${voItem.f08}"/>
				<input type="hidden"  fname="h08" value="${colName}"/>
				<input type="hidden"  fname="e08" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p08" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f09'">
			<td title="${voItem.f09}">
			    <div>${voItem.f09}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f09" value="${voItem.f09}"/>
				<input type="hidden"  fname="h09" value="${colName}"/>
				<input type="hidden"  fname="e09" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p09" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f10'">
			<td title="${voItem.f10}">
			    <div>${voItem.f10}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f10" value="${voItem.f10}"/>
				<input type="hidden"  fname="h10" value="${colName}"/>
				<input type="hidden"  fname="e10" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p10" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f11'">
			<td title="${voItem.f11}">
			    <div>${voItem.f11}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f11" value="${voItem.f11}"/>
				<input type="hidden"  fname="h11" value="${colName}"/>
				<input type="hidden"  fname="e11" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p11" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f12'">
			<td title="${voItem.f12}">
			    <div>${voItem.f12}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f12" value="${voItem.f12}"/>
				<input type="hidden"  fname="h12" value="${colName}"/>
				<input type="hidden"  fname="e12" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p12" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f13'">
			<td title="${voItem.f13}">
			    <div>${voItem.f13}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f13" value="${voItem.f13}"/>
				<input type="hidden"  fname="h13" value="${colName}"/>
				<input type="hidden"  fname="e13" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p13" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f14'">
			<td title="${voItem.f14}">
			    <div>${voItem.f14}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f14" value="${voItem.f14}"/>
				<input type="hidden"  fname="h14" value="${colName}"/>
				<input type="hidden"  fname="e14" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p14" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f15'">
			<td title="${voItem.f15}">
			    <div>${voItem.f15}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f15" value="${voItem.f15}"/>
				<input type="hidden"  fname="h15" value="${colName}"/>
				<input type="hidden"  fname="e15" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p15" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f16'">
			<td title="${voItem.f16}">
			    <div>${voItem.f16}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f16" value="${voItem.f16}"/>
				<input type="hidden"  fname="h16" value="${colName}"/>
				<input type="hidden"  fname="e16" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p16" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f17'">
			<td title="${voItem.f17}">
			    <div>${voItem.f17}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f17" value="${voItem.f17}"/>
				<input type="hidden"  fname="h17" value="${colName}"/>
				<input type="hidden"  fname="e17" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p17" value="0" extprice='1'/> 
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f18'">
			<td title="${voItem.f18}">
			    <div>${voItem.f18}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f18" value="${voItem.f18}"/>
				<input type="hidden"  fname="h18" value="${colName}"/>
				<input type="hidden"  fname="e18" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p18" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f19'">
			<td title="${voItem.f19}">
			    <div>${voItem.f19}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f19" value="${voItem.f19}"/>
				<input type="hidden"  fname="h19" value="${colName}"/>
				<input type="hidden"  fname="e19" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p19" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f20'">
			<td title="${voItem.f20}">
			    <div>${voItem.f20}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f20" value="${voItem.f20}"/>
				<input type="hidden"  fname="h20" value="${colName}"/>
				<input type="hidden"  fname="e20" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p20" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f21'">
			<td title="${voItem.f21}">
			    <div>${voItem.f21}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f21" value="${voItem.f21}"/>
				<input type="hidden"  fname="h21" value="${colName}"/>
				<input type="hidden"  fname="e21" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p21" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f22'">
			<td title="${voItem.f22}">
			    <div>${voItem.f22}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f22" value="${voItem.f22}"/>
				<input type="hidden"  fname="h22" value="${colName}"/>
				<input type="hidden"  fname="e22" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p22" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f23'">
			<td title="${voItem.f23}">
			    <div>${voItem.f23}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f23" value="${voItem.f23}"/>
				<input type="hidden"  fname="h23" value="${colName}"/>
				<input type="hidden"  fname="e23" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p23" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f24'">
			<td title="${voItem.f24}">
			    <div>${voItem.f24}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f24" value="${voItem.f24}"/>
				<input type="hidden"  fname="h24" value="${colName}"/>
				<input type="hidden"  fname="e24" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p24" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f25'">
			<td title="${voItem.f25}">
			    <div>${voItem.f25}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f25" value="${voItem.f25}"/>
				<input type="hidden"  fname="h25" value="${colName}"/>
				<input type="hidden"  fname="e25" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p25" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f26'">
			<td title="${voItem.f26}">
			    <div>${voItem.f26}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f26" value="${voItem.f26}"/>
				<input type="hidden"  fname="h26" value="${colName}"/>
				<input type="hidden"  fname="e26" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p26" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f27'">
			<td title="${voItem.f27}">
			    <div>${voItem.f27}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f27" value="${voItem.f27}"/>
				<input type="hidden"  fname="h27" value="${colName}"/>
				<input type="hidden"  fname="e27" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p27" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f28'">
			<td title="${voItem.f28}">
			    <div>${voItem.f28}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f28" value="${voItem.f28}"/>
				<input type="hidden"  fname="h28" value="${colName}"/>
				<input type="hidden"  fname="e28" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p28" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f29'">
			<td title="${voItem.f29}">
			    <div>${voItem.f29}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f29" value="${voItem.f29}"/>
				<input type="hidden"  fname="h29" value="${colName}"/>
				<input type="hidden"  fname="e29" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p29" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<s:elseif test="colField=='f30'">
			<td title="${voItem.f30}">
			    <div>${voItem.f30}</div>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="hidden"  fname="f30" value="${voItem.f30}"/>
				<input type="hidden"  fname="h30" value="${colName}"/>
				<input type="hidden"  fname="e30" value="${extFlg}"/>
			</td>
			<s:if test='extPrice == 1 '>
			<td>
				<input type="text"  fname="p30" value="0" extprice='1'/>
			</td>
			</s:if>
		</s:elseif>
		<%--
		<s:else>
			<td><div>${colField }</div>
			</td>
		</s:else>
		 --%>
		</s:iterator>
		<td dianlan='1' style="display:none;"><input type="text"   fname="currentPrices"   value="${currentPrices}"/></td>
		<td ><input type="text" fname="buyNum" class="required" validate="required"  value="${buyNum}"/></td>
		<td style="text-align: right;" align="right"><input type="hidden" fname="totalPrice"  value="${totalPrice}"/><div style="float:right;text-align: right;">${totalPrice}</div></td>
		<%--
		<td title='${memoDesc}'><div class="ellipsisDiv_full"><input type="hidden" fname="memoDesc"  value="${memoDesc}"/><div>${memoDesc} </div></div></td>
		--%>
		<td style="text-align: center;" valign="middle" align="center">
			<img onclick="removeCurrentRow(this)" style="cursor:pointer;" src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/>
	 	</td>  
	</tr>
  </s:iterator>
