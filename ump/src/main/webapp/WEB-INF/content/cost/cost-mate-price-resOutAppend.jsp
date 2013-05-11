<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
 
  <s:iterator value="page.result"  var="voItem" status="st">
	<tr class="mainTr" headFlg='0' costmatepriceid="${resCostMatePriceId}" costmateid="strageout" inoutflg="2">
		<td>1</td>
		<td class="scopcol pd-chill-tip">
			<input type="text"  class="inputBorder required" validate="required" fname="materialName"/>
			<input type="hidden" fname="costMatePriceId" />
			<input type="hidden" fname="costMateId" value="strageout"/>
			<input type="hidden" fname="strageTypeCd" value="2"/>
			<input type="hidden" fname="headFlg" value="0"/>
		</td> 
		<td class="scopcol pd-chill-tip"><input type="text" fname="specName"   /></td>
		<td class="scopcol pd-chill-tip"><input type="text" fname="modelName" /></td>
		<td class="scopcol pd-chill-tip"><input type="text" fname="price"    /></td>
		
		<%--自定义列头的值 --%>
		<s:iterator value="costMateColList" var="cl" status="st">
		<s:if test="colField=='f01'">
			<td>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f01"/><%--记住第一个位置,计算有用 --%>
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
			<td>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f02"/>
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
			<td>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f03"/>
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
			<td>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f04"/>
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
			<td>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f05"/>
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
			<td>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f06"/>
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
			<td>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f07"/>
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
			<td>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f08"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f09"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f10"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f11"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f12"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f13"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f14"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f15"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f16"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f17"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f18"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f19"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f20"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f21"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f22"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f23"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f24" />
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f25"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f26"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f27"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f28"/>
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
			<td>
			    
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f29"/>
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
			<td>
				<%--f:扩展价格,h:列头,e:扩展字段,p:扩展价格的数量,默认为1 --%>
				<input type="text"  fname="f30"/>
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
		<td><input type="text" style="text-align: right;" fname="cgPrice"/></td>
		<td><input type="text" style="text-align: right;" fname="buyNum" validate="required"  value="${buyNum}"/></td>
		<td><input type="hidden" fname="totalPrice"  value="${totalPrice}"/><div style="float:right;text-align: right;">${totalPrice}</div></td>
		<%--
		<td title='${memoDesc}'><div class="ellipsisDiv_full"><input type="hidden" fname="memoDesc"  value="${memoDesc}"/><div>${memoDesc} </div></div></td>
		--%>
		<td style="text-align: center;" valign="middle">
			<img onclick="removeCurrentRow(this)" style="cursor:pointer;" src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/>
	 	</td>  
	</tr>
  </s:iterator>
	