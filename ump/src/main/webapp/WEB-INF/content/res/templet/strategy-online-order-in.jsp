<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<s:if test="templateBean.strategyMatePrices.size()>0">

	<%--战略内记录总数 --%>
	<s:set name="strageInCount">0</s:set>
	
	<%-- 当前类型行数 --%>
	<s:set name="moduleRowCount">0</s:set>
	
	<s:iterator value="templateBean.strategyMatePrices" status="st">
		<s:set name="curCostMateId">${costMateId}</s:set>
		
		<s:if test="strageTypeCd == 1">
			
			<%-- 总记录数累加 --%>
			<s:set name="strageInCount">${strageInCount+1}</s:set>
			<%-- 当前类型累加 --%>
			<s:set name="moduleRowCount">${moduleRowCount+1}</s:set>
			
			
			<s:if test="#moduleRowCount == 1">
				<table costmateid='${costMateId}' class="content_table cost_mate_table" stragetable='cost_mate_table'>
			</s:if>
			<s:else>
				<s:if test="#curCostMateId == #preCostMateId">
				</s:if>
				<s:else>
					</table> 
					<s:set name="moduleRowCount">0</s:set>

					<table costmateid='${costMateId}' class="content_table cost_mate_table" stragetable='cost_mate_table'>
				</s:else>
			</s:else>
			<tr style="height: 30px;" class="mainTr" costmateid="${costMateId}" costmatepriceid="${costMatePriceId}" headFlg='${headFlg}' strageTypeCd='${strageTypeCd}' inoutflg="1">
				<td title='序号 ' style="width: 35px;background: none;text-align: center;">
					<div>序号</div>
				</td> 
				<td class="scopcol">
					<input type="hidden" fname="materialName" value="${materialName}"/><div>${materialName } </div>
					<input type="hidden" fname="costMatePriceId" value="${costMatePriceId}"/>
					<input type="hidden" fname="costMateId" value="${costMateId}"/>	
					<input type="hidden" fname="costMateModuleId" value="${costMateModuleId}"/>	
					<input type="hidden" fname="strageTypeCd" value="${strageTypeCd }"/>
					<input type="hidden" fname="headFlg" value="${headFlg }"/>
				</td>
				<td class="scopcol"><input type="hidden" fname="specName" value="${specName }"/><div>${specName } </div></td>
				<td class="scopcol"><input type="hidden" fname="modelName" value="${modelName }"/><div>${modelName }</div></td>
				<td class="scopcol"><input type="hidden" fname="price" value="${price}"/><div>${price }</div></td>
				<td class="scopcol"><input type="hidden" fname="cgPrice" value="${cgPrice }"/><div>${cgPrice }</div></td>
				 
				<s:if test="f01 != null ">
					<s:if test="headFlg ==1">
						<td>
							${h01}
							<input type="hidden" fname="f01" value="${f01}"/>
							<input type="hidden" fname="h01" value="${h01}"/>
							<input type="hidden" fname="e01" value="${e01}"/>
						</td>
						
						<s:if test="p01 != null ">
							<td style="text-align: center;">
								数量
								<input type="hidden" fname="p01" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f01}
							<input type="hidden" fname="f01" value="${f01}"/>
							<input type="hidden" fname="h01" value="${h01}"/>
							<input type="hidden" fname="e01" value="${e01}"/>
						</td>
						<s:if test="p01 != null">
							<td>
								<input type="text" fname="p01" value="${p01}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f02 != null ">
					<s:if test="headFlg ==1">
						<td>
							${h02}
							<input type="hidden" fname="f02" value="${f02}"/>
							<input type="hidden" fname="h02" value="${h02}"/>
							<input type="hidden" fname="e02" value="${e02}"/>
						</td>
						
						<s:if test="p02 != null ">
							<td>
								数量
								<input type="hidden" fname="p02" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f02}
							<input type="hidden" fname="f02" value="${f02}"/>
							<input type="hidden" fname="h02" value="${h02}"/>
							<input type="hidden" fname="e02" value="${e02}"/>
						</td>
						<s:if test="p02 != null">
							<td>
								<input type="text" fname="p02" value="${p02}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f03 != null">
					<s:if test="headFlg ==1">
						<td>
							${h03}
							<input type="hidden" fname="f03" value="${f03}"/>
							<input type="hidden" fname="h03" value="${h03}"/>
							<input type="hidden" fname="e03" value="${e03}"/>
						</td>
						
						<s:if test="p03 != null">
							<td>
								数量
								<input type="hidden" fname="p03" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f03}
							<input type="hidden" fname="f03" value="${f03}"/>
							<input type="hidden" fname="h03" value="${h03}"/>
							<input type="hidden" fname="e03" value="${e03}"/>
						</td>
						<s:if test="p03 != null">
							<td>
								<input type="text" fname="p03" value="${p03}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f04 != null ">
					<s:if test="headFlg ==1">
						<td>
							${h04}
							<input type="hidden" fname="f04" value="${f04}"/>
							<input type="hidden" fname="h04" value="${h04}"/>
							<input type="hidden" fname="e04" value="${e04}"/>
						</td>
						
						<s:if test="p04 != null">
							<td>
								数量
								<input type="hidden" fname="p04" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f04}
							<input type="hidden" fname="f04" value="${f04}"/>
							<input type="hidden" fname="h04" value="${h04}"/>
							<input type="hidden" fname="e04" value="${e04}"/>
						</td>
						<s:if test="p04 != null">
							<td>
								<input type="text" fname="p04" value="${p04}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f05 != null">
					<s:if test="headFlg ==1">
						<td>
							${h05}
							<input type="hidden" fname="f05" value="${f05}"/>
							<input type="hidden" fname="h05" value="${h05}"/>
							<input type="hidden" fname="e05" value="${e05}"/>
						</td>
						
						<s:if test="p05 != null">
							<td>
								数量
								<input type="hidden" fname="p05" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f05}
							<input type="hidden" fname="f05" value="${f05}"/>
							<input type="hidden" fname="h05" value="${h05}"/>
							<input type="hidden" fname="e05" value="${e05}"/>
						</td>
						<s:if test="p05 != null">
							<td>
								<input type="text" fname="p05" value="${p05}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f06 != null">
					<s:if test="headFlg ==1">
						<td>
							${h06}
							<input type="hidden" fname="f06" value="${f06}"/>
							<input type="hidden" fname="h06" value="${h06}"/>
							<input type="hidden" fname="e06" value="${e06}"/>
						</td>
						
						<s:if test="p06 != null">
							<td>
								数量
								<input type="hidden" fname="p06" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f06}
							<input type="hidden" fname="f06" value="${f06}"/>
							<input type="hidden" fname="h06" value="${h06}"/>
							<input type="hidden" fname="e06" value="${e06}"/>
						</td>
						<s:if test="p06 != null">
							<td>
								<input type="text" fname="p06" value="${p06}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f07 != null">
					<s:if test="headFlg ==1">
						<td>
							${h07}
							<input type="hidden" fname="f07" value="${f07}"/>
							<input type="hidden" fname="h07" value="${h07}"/>
							<input type="hidden" fname="e07" value="${e07}"/>
						</td>
						
						<s:if test="p07 != null">
							<td>
								数量
								<input type="hidden" fname="p07" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f07}
							<input type="hidden" fname="f07" value="${f07}"/>
							<input type="hidden" fname="h07" value="${h07}"/>
							<input type="hidden" fname="e07" value="${e07}"/>
						</td>
						<s:if test="p07 != null">
							<td>
								<input type="text" fname="p07" value="${p07}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f08 != null">
					<s:if test="headFlg ==1">
						<td>
							${h08}
							<input type="hidden" fname="f08" value="${f08}"/>
							<input type="hidden" fname="h08" value="${h08}"/>
							<input type="hidden" fname="e08" value="${e08}"/>
						</td>
						
						<s:if test="p08 != null">
							<td>
								数量
								<input type="hidden" fname="p08" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f08}
							<input type="hidden" fname="f08" value="${f08}"/>
							<input type="hidden" fname="h08" value="${h08}"/>
							<input type="hidden" fname="e08" value="${e08}"/>
						</td>
						<s:if test="p08 != null">
							<td>
								<input type="text" fname="p08" value="${p08}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f09 != null">
					<s:if test="headFlg ==1">
						<td>
							${h09}
							<input type="hidden" fname="f09" value="${f09}"/>
							<input type="hidden" fname="h09" value="${h09}"/>
							<input type="hidden" fname="e09" value="${e09}"/>
						</td>
						
						<s:if test="p09 != null">
							<td>
								数量
								<input type="hidden" fname="p09" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f09}
							<input type="hidden" fname="f09" value="${f09}"/>
							<input type="hidden" fname="h09" value="${h09}"/>
							<input type="hidden" fname="e09" value="${e09}"/>
						</td>
						<s:if test="p09 != null">
							<td>
								<input type="text" fname="p09" value="${p09}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f10 != null">
					<s:if test="headFlg ==1">
						<td>
							${h10}
							<input type="hidden" fname="f10" value="${f10}"/>
							<input type="hidden" fname="h10" value="${h10}"/>
							<input type="hidden" fname="e10" value="${e10}"/>
						</td>
						
						<s:if test="p10 != null">
							<td>
								数量
								<input type="hidden" fname="p10" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f10}
							<input type="hidden" fname="f10" value="${f10}"/>
							<input type="hidden" fname="h10" value="${h10}"/>
							<input type="hidden" fname="e10" value="${e10}"/>
						</td>
						<s:if test="p10 != null">
							<td>
								<input type="text" fname="p10" value="${p10}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f11 != null">
					<s:if test="headFlg ==1">
						<td>
							${h11}
							<input type="hidden" fname="f11" value="${f11}"/>
							<input type="hidden" fname="h11" value="${h11}"/>
							<input type="hidden" fname="e11" value="${e11}"/>
						</td>
						
						<s:if test="p11 != null">
							<td>
								数量
								<input type="hidden" fname="p11" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f11}
							<input type="hidden" fname="f11" value="${f11}"/>
							<input type="hidden" fname="h11" value="${h11}"/>
							<input type="hidden" fname="e11" value="${e11}"/>
						</td>
						<s:if test="p11 != null">
							<td>
								<input type="text" fname="p11" value="${p11}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f12 != null">
					<s:if test="headFlg ==1">
						<td>
							${h12}
							<input type="hidden" fname="f12" value="${f12}"/>
							<input type="hidden" fname="h12" value="${h12}"/>
							<input type="hidden" fname="e12" value="${e12}"/>
						</td>
						
						<s:if test="p12 != null">
							<td>
								数量
								<input type="hidden" fname="p12" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f12}
							<input type="hidden" fname="f12" value="${f12}"/>
							<input type="hidden" fname="h12" value="${h12}"/>
							<input type="hidden" fname="e12" value="${e12}"/>
						</td>
						<s:if test="p12 != null">
							<td>
								<input type="text" fname="p12" value="${p12}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f13 != null">
					<s:if test="headFlg ==1">
						<td>
							${h13}
							<input type="hidden" fname="f13" value="${f13}"/>
							<input type="hidden" fname="h13" value="${h13}"/>
							<input type="hidden" fname="e13" value="${e13}"/>
						</td>
						
						<s:if test="p13 != null">
							<td>
								数量
								<input type="hidden" fname="p13" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f13}
							<input type="hidden" fname="f13" value="${f13}"/>
							<input type="hidden" fname="h13" value="${h13}"/>
							<input type="hidden" fname="e13" value="${e13}"/>
						</td>
						<s:if test="p13 != null">
							<td>
								<input type="text" fname="p13" value="${p13}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f14 != null">
					<s:if test="headFlg ==1">
						<td>
							${h14}
							<input type="hidden" fname="f14" value="${f14}"/>
							<input type="hidden" fname="h14" value="${h14}"/>
							<input type="hidden" fname="e14" value="${e14}"/>
						</td>
						
						<s:if test="p14 != null">
							<td>
								数量
								<input type="hidden" fname="p14" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f14}
							<input type="hidden" fname="f14" value="${f14}"/>
							<input type="hidden" fname="h14" value="${h14}"/>
							<input type="hidden" fname="e14" value="${e14}"/>
						</td>
						<s:if test="p14 != null">
							<td>
								<input type="text" fname="p14" value="${p14}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f15 != null">
					<s:if test="headFlg ==1">
						<td>
							${h15}
							<input type="hidden" fname="f15" value="${f15}"/>
							<input type="hidden" fname="h15" value="${h15}"/>
							<input type="hidden" fname="e15" value="${e15}"/>
						</td>
						
						<s:if test="p15 != null">
							<td>
								数量
								<input type="hidden" fname="p15" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f15}
							<input type="hidden" fname="f15" value="${f15}"/>
							<input type="hidden" fname="h15" value="${h15}"/>
							<input type="hidden" fname="e15" value="${e15}"/>
						</td>
						<s:if test="p15 != null">
							<td>
								<input type="text" fname="p15" value="${p15}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f16 != null">
					<s:if test="headFlg ==1">
						<td>
							${h16}
							<input type="hidden" fname="f16" value="${f16}"/>
							<input type="hidden" fname="h16" value="${h16}"/>
							<input type="hidden" fname="e16" value="${e16}"/>
						</td>
						
						<s:if test="p16 != null">
							<td>
								数量
								<input type="hidden" fname="p16" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f16}
							<input type="hidden" fname="f16" value="${f16}"/>
							<input type="hidden" fname="h16" value="${h16}"/>
							<input type="hidden" fname="e16" value="${e16}"/>
						</td>
						<s:if test="p16 != null">
							<td>
								<input type="text" fname="p16" value="${p16}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f17 != null">
					<s:if test="headFlg ==1">
						<td>
							${h17}
							<input type="hidden" fname="f17" value="${f17}"/>
							<input type="hidden" fname="h17" value="${h17}"/>
							<input type="hidden" fname="e17" value="${e17}"/>
						</td>
						
						<s:if test="p17 != null">
							<td>
								数量
								<input type="hidden" fname="p17" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f17}
							<input type="hidden" fname="f17" value="${f17}"/>
							<input type="hidden" fname="h17" value="${h17}"/>
							<input type="hidden" fname="e17" value="${e17}"/>
						</td>
						<s:if test="p17 != null">
							<td>
								<input type="text" fname="p17" value="${p17}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f18 != null">
					<s:if test="headFlg ==1">
						<td>
							${h18}
							<input type="hidden" fname="f18" value="${f18}"/>
							<input type="hidden" fname="h18" value="${h18}"/>
							<input type="hidden" fname="e18" value="${e18}"/>
						</td>
						
						<s:if test="p18 != null">
							<td>
								数量
								<input type="hidden" fname="p18" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f18}
							<input type="hidden" fname="f18" value="${f18}"/>
							<input type="hidden" fname="h18" value="${h18}"/>
							<input type="hidden" fname="e18" value="${e18}"/>
						</td>
						<s:if test="p18 != null">
							<td>
								<input type="text" fname="p18" value="${p18}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f19 != null">
					<s:if test="headFlg ==1">
						<td>
							${h19}
							<input type="hidden" fname="f19" value="${f19}"/>
							<input type="hidden" fname="h19" value="${h19}"/>
							<input type="hidden" fname="e19" value="${e19}"/>
						</td>
						
						<s:if test="p19 != null">
							<td>
								数量
								<input type="hidden" fname="p19" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f19}
							<input type="hidden" fname="f19" value="${f19}"/>
							<input type="hidden" fname="h19" value="${h19}"/>
							<input type="hidden" fname="e19" value="${e19}"/>
						</td>
						<s:if test="p19 != null">
							<td>
								<input type="text" fname="p19" value="${p19}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f20 != null">
					<s:if test="headFlg ==1">
						<td>
							${h20}
							<input type="hidden" fname="f20" value="${f20}"/>
							<input type="hidden" fname="h20" value="${h20}"/>
							<input type="hidden" fname="e20" value="${e20}"/>
						</td>
						
						<s:if test="p20 != null">
							<td>
								数量
								<input type="hidden" fname="p20" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f20}
							<input type="hidden" fname="f20" value="${f20}"/>
							<input type="hidden" fname="h20" value="${h20}"/>
							<input type="hidden" fname="e20" value="${e20}"/>
						</td>
						<s:if test="p20 != null">
							<td>
								<input type="text" fname="p20" value="${p20}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f21 != null">
					<s:if test="headFlg ==1">
						<td>
							${h21}
							<input type="hidden" fname="f21" value="${f21}"/>
							<input type="hidden" fname="h21" value="${h21}"/>
							<input type="hidden" fname="e21" value="${e21}"/>
						</td>
						
						<s:if test="p21 != null">
							<td>
								数量
								<input type="hidden" fname="p21" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f21}
							<input type="hidden" fname="f21" value="${f21}"/>
							<input type="hidden" fname="h21" value="${h21}"/>
							<input type="hidden" fname="e21" value="${e21}"/>
						</td>
						<s:if test="p21 != null">
							<td>
								<input type="text" fname="p21" value="${p21}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f22 != null">
					<s:if test="headFlg ==1">
						<td>
							${h22}
							<input type="hidden" fname="f22" value="${f22}"/>
							<input type="hidden" fname="h22" value="${h22}"/>
							<input type="hidden" fname="e22" value="${e22}"/>
						</td>
						
						<s:if test="p22 != null">
							<td>
								数量
								<input type="hidden" fname="p22" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f22}
							<input type="hidden" fname="f22" value="${f22}"/>
							<input type="hidden" fname="h22" value="${h22}"/>
							<input type="hidden" fname="e22" value="${e22}"/>
						</td>
						<s:if test="p22 != null">
							<td>
								<input type="text" fname="p22" value="${p22}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f23 != null">
					<s:if test="headFlg ==1">
						<td>
							${h23}
							<input type="hidden" fname="f23" value="${f23}"/>
							<input type="hidden" fname="h23" value="${h23}"/>
							<input type="hidden" fname="e23" value="${e23}"/>
						</td>
						
						<s:if test="p23 != null">
							<td>
								数量
								<input type="hidden" fname="p23" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f23}
							<input type="hidden" fname="f23" value="${f23}"/>
							<input type="hidden" fname="h23" value="${h23}"/>
							<input type="hidden" fname="e23" value="${e23}"/>
						</td>
						<s:if test="p23 != null">
							<td>
								<input type="text" fname="p23" value="${p23}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f24 != null">
					<s:if test="headFlg ==1">
						<td>
							${h24}
							<input type="hidden" fname="f24" value="${f24}"/>
							<input type="hidden" fname="h24" value="${h24}"/>
							<input type="hidden" fname="e24" value="${e24}"/>
						</td>
						
						<s:if test="p24 != null">
							<td>
								数量
								<input type="hidden" fname="p24" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f24}
							<input type="hidden" fname="f24" value="${f24}"/>
							<input type="hidden" fname="h24" value="${h24}"/>
							<input type="hidden" fname="e24" value="${e24}"/>
						</td>
						<s:if test="p24 != null">
							<td>
								<input type="text" fname="p24" value="${p24}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f25 != null">
					<s:if test="headFlg ==1">
						<td>
							${h25}
							<input type="hidden" fname="f25" value="${f25}"/>
							<input type="hidden" fname="h25" value="${h25}"/>
							<input type="hidden" fname="e25" value="${e25}"/>
						</td>
						
						<s:if test="p25 != null">
							<td>
								数量
								<input type="hidden" fname="p25" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f25}
							<input type="hidden" fname="f25" value="${f25}"/>
							<input type="hidden" fname="h25" value="${h25}"/>
							<input type="hidden" fname="e25" value="${e25}"/>
						</td>
						<s:if test="p25 != null">
							<td>
								<input type="text" fname="p25" value="${p25}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f26 != null">
					<s:if test="headFlg ==1">
						<td>
							${h26}
							<input type="hidden" fname="f26" value="${f26}"/>
							<input type="hidden" fname="h26" value="${h26}"/>
							<input type="hidden" fname="e26" value="${e26}"/>
						</td>
						
						<s:if test="p26 != null">
							<td>
								数量
								<input type="hidden" fname="p26" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f26}
							<input type="hidden" fname="f26" value="${f26}"/>
							<input type="hidden" fname="h26" value="${h26}"/>
							<input type="hidden" fname="e26" value="${e26}"/>
						</td>
						<s:if test="p26 != null">
							<td>
								<input type="text" fname="p26" value="${p26}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f27 != null">
					<s:if test="headFlg ==1">
						<td>
							${h27}
							<input type="hidden" fname="f27" value="${f27}"/>
							<input type="hidden" fname="h27" value="${h27}"/>
							<input type="hidden" fname="e27" value="${e27}"/>
						</td>
						
						<s:if test="p27 != null">
							<td>
								数量
								<input type="hidden" fname="p27" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f27}
							<input type="hidden" fname="f27" value="${f27}"/>
							<input type="hidden" fname="h27" value="${h27}"/>
							<input type="hidden" fname="e27" value="${e27}"/>
						</td>
						<s:if test="p27 != null">
							<td>
								<input type="text" fname="p27" value="${p27}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f28 != null">
					<s:if test="headFlg ==1">
						<td>
							${h28}
							<input type="hidden" fname="f28" value="${f28}"/>
							<input type="hidden" fname="h28" value="${h28}"/>
							<input type="hidden" fname="e28" value="${e28}"/>
						</td>
						
						<s:if test="p28 != null">
							<td>
								数量
								<input type="hidden" fname="p28" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f28}
							<input type="hidden" fname="f28" value="${f28}"/>
							<input type="hidden" fname="h28" value="${h28}"/>
							<input type="hidden" fname="e28" value="${e28}"/>
						</td>
						<s:if test="p28 != null">
							<td>
								<input type="text" fname="p28" value="${p28}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f29 != null">
					<s:if test="headFlg ==1">
						<td>
							${h29}
							<input type="hidden" fname="f29" value="${f29}"/>
							<input type="hidden" fname="h29" value="${h29}"/>
							<input type="hidden" fname="e29" value="${e29}"/>
						</td>
						
						<s:if test="p29 != null">
							<td>
								数量
								<input type="hidden" fname="p29" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f29}
							<input type="hidden" fname="f29" value="${f29}"/>
							<input type="hidden" fname="h29" value="${h29}"/>
							<input type="hidden" fname="e29" value="${e29}"/>
						</td>
						<s:if test="p29 != null">
							<td>
								<input type="text" fname="p29" value="${p29}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				<s:if test="f30 != null">
					<s:if test="headFlg ==1">
						<td>
							${h30}
							<input type="hidden" fname="f30" value="${f30}"/>
							<input type="hidden" fname="h30" value="${h30}"/>
							<input type="hidden" fname="e30" value="${e30}"/>
						</td>
						
						<s:if test="p30 != null">
							<td>
								数量
								<input type="hidden" fname="p30" value="数量"/>
							</td>
						</s:if>
					</s:if>
					<s:else>
						<td>
							${f30}
							<input type="hidden" fname="f30" value="${f30}"/>
							<input type="hidden" fname="h30" value="${h30}"/>
							<input type="hidden" fname="e30" value="${e30}"/>
						</td>
						<s:if test="p30 != null">
							<td>
								<input type="text" fname="p30" value="${p30}"/>
							</td>
						</s:if>
					</s:else>
				</s:if>
				
				<td dianlan='1' style="display:none;">
				<input type="hidden" fname="currentPrices" value="${currentPrices }"/>
					<s:if test="headFlg ==0 ">
						<input type="text" fname="currentPrices" class="inputBorder required" validate="required"  value="${currentPrices}" style="width:100%;text-align: right;"/>
					</s:if>
					<s:else>${currentPrices}<input type="hidden" fname="currentPrices" value="${currentPrices}"/></s:else>
				</td>
				<td style="width:80px;text-align: center;">
					<s:if test="headFlg ==0 ">
						<input type="text" fname="buyNum" class="inputBorder required" validate="required"  value="${buyNum}" style="width:100%;"/>
					</s:if>
					<s:else>${buyNum}<input type="hidden" fname="buyNum" value="${buyNum}"/></s:else>
				</td>
				
				<td class="totalOption" style="width:80px;">
					<s:if test="headFlg == 1">
						<div>小计(元)</div>
						<input type="hidden" fname="totalPrice" value="小计(元)"/>
					</s:if>
					<s:else>
						<!--有先后顺序 -->
						<input type="hidden" fname="totalPrice" value="${totalPrice}"/>
						<div style="float:right;text-align: right;">${totalPrice}</div>
					</s:else>
				</td>
				<%--
		        <td style="width:60px;">
		        	<input type="hidden" fname="memoDesc" value="${memoDesc }"/>
					<div>${memoDesc}</div>
				</td> 
				 --%>
				<s:if test="#canEdit=='true'">
			 	<td align="center" valign="middle" style="width:60px;text-align: center;">
			 		<s:if test="#moduleRowCount > 1">
							<img onclick="removeCurrentRow(this)" style="cursor:pointer;" src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/>
						</s:if>
			 	</td>  
				</s:if>
			</tr>
			<s:set name="preCostMateId">${costMateId}</s:set>
			
		</s:if>
	</s:iterator>
	<s:if test="#moduleRowCount > 0">
		</table> 
	</s:if>
</s:if>
