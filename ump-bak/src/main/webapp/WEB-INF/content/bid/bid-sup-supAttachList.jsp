<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
 
<input type="hidden" name="bidLegerId" id="atta_bidLegerId" value="${bidLedger.bidLedgerId}"/>

<div style="background-color: #cbcbcb;line-height: 30px;">
	       		   	  	
	<div class="fLeft resultTip" id="supSuccessInfo"></div>	
</div>
<div class="fakeContainer" >
	<table class="stat_table" id="MyTable">
		<thead>
			<tr>
				<th  rowspan="2" style="background-image: url('');text-align: center;" width="40px;">序号</th>
				<th  rowspan="2" style="text-align: center;" width="120" >投标单位</th>
				<th colspan="${bidLedger.batchNo}" style="text-align: center;" class="headId">商务标</th>
				<th colspan="${bidLedger.batchNo}" style="text-align: center;"  class="headId">技术标</th>
			</tr>
			<tr>
				<s:if test="bidLedger.batchNo != null">						
					<s:iterator var="st1" begin="1"  end="bidLedger.batchNo" status="status" >
					  <s:if test="#status.index+1==bidLedger.batchNo">
					    <th style="text-align: center; font-size:16px"   width="150" nowrap="nowrap"><div style="width: 120px;">第<s:property value="#status.index+1"/>轮</div></th>
					  </s:if>
					  <s:else>
						<th style="text-align: center; color:gray"   width="150" nowrap="nowrap"><div style="width: 120px;">第<s:property value="#status.index+1"/>轮</div></th>
					  </s:else>							
					</s:iterator>	
					<s:iterator var="st2" begin="1" end="bidLedger.batchNo" status="status" >
					   <s:if test="#status.index+1==bidLedger.batchNo">
					    <th style="text-align: center; font-size:16px"  width="150" nowrap="nowrap"><div style="width: 120px;">第<s:property value="#status.index+1"/>轮</div></th>
					   </s:if>
					   <s:else>
					     <th style="text-align: center;color:gray"  width="150" nowrap="nowrap"><div style="width: 120px;">第<s:property value="#status.index+1"/>轮</div></th>
					   </s:else>							
					</s:iterator>
				</s:if>
			</tr>
			
		</thead>
		<tbody>
			<s:iterator value="listBidSupVo" status="status" var="vo">
				<tr>
					<td style="text-align: center;">
						<span>
	  			  			<s:property value="#status.index+1"/>		  			  			
	  			  		</span>
					</td>
					<td style="text-align: center;text-align: left;padding-left: 5px;"  title="${bidSupName}">
						<div style="width: 120px;" class="partHideDiv" >
							${bidSupName}
						</div>
					</td>
					<%--根据轮次展示商务标 --%>
					<s:iterator value="bussinessBids" var="bu">
					<td 
						<%--
						<security:authorize ifAnyGranted="A_BID_STRA_ADMIN">
							onclick="openAttachmentRefresh($(this),'${bidSupAttachRelId}','strategyAttaBiz','true','attaBizFlg');" 
						</security:authorize>
						--%>
							style="width: 120px;"  title="请点击单元格,上传或查看附件">
							<s:if test="bidLedger.batchNo != null">	
								<s:iterator var="st3" begin="1"  end="bidLedger.batchNo" status="status" >
									<s:if test="#status.index+1==batchNo">
										<s:set var="kkk1">
											${vo.bidSupId}-attaBizFlg-${batchNo}
										</s:set>
										<div style="float: left;text-align: left;padding: 5px 5px;width: 99%;overflow: hidden;" >
											<ul style="list-style:none;">
												<s:iterator value='attachMap' var="attach">
													<c:set var="kkk2">
														<s:property value="key"/>
													</c:set>															
													<c:if test="${kkk1 eq kkk2}">																	
														<s:iterator value="value" var="v" status="vsta">	
															<li  title="${realFileName}" class="pd-chill-tip" >																		
																<s:url id="down" action="download" namespace="/app">
															  	  	<s:param name="fileName">${fileName}</s:param>
															  	  	<s:param name="realFileName">${realFileName}</s:param>
															  	  	<s:param name="bizModuleCd">${bizModuleCd}</s:param>
															  	  	<s:param name="filterType">${filterType}</s:param>
															  	  	<s:param name="id">${appAttachFileId}</s:param>
															  	  	<s:param name="fieldName">${bizFieldName}</s:param>
															  	  	<s:param name="bizFieldName">${bizFieldName}</s:param>
															  	</s:url>
															  	
															  	<%--只有"评标"后才允许下载。 --%>
															  	<s:if test="bidLedger.bidStatusCd >= 3">
																	<a href="${down}" onclick="preventClick(event);">${realFileName}</a>
																</s:if>
															  	<s:else>
															  		<a href="javascript:void(0)">${realFileName}</a>
															  	</s:else>
															</li>																		 
														</s:iterator>
													</c:if>	
												</s:iterator>
											</ul>
										</div>
									</s:if>
								</s:iterator>
							</s:if>
						</td>
					</s:iterator>
					<%--根据轮次展示技术标 --%>
					<s:iterator value="bussinessBids" var="bu">		
						<td 
						<%--
						<security:authorize ifAnyGranted="A_BID_STRA_ADMIN">
						onclick="openAttachmentRefresh($(this),'${bidSupAttachRelId}','strategyAttaTech','true','attaTechFlg');"
						</security:authorize>									  
						--%>
							style="width: 120px;" title="请点击单元格,上传或查看附件">							
							<s:if test="bidLedger.batchNo != null">	
								<s:iterator var="st4" begin="1"  end="bidLedger.batchNo" status="status" >
										<s:if test="#status.index+1==batchNo">
											<s:set var="kkk1">
											${vo.bidSupId}-attaTechFlg-${batchNo}
										</s:set>
										<div style="float: left;text-align: left;padding: 5px 5px;width: 99%;overflow: hidden;" >
											<ul style="list-style:none;">
												<s:iterator value='attachMap' var="attach">
													<c:set var="kkk2">
														<s:property value="key"/>
													</c:set>															
													<c:if test="${kkk1 eq kkk2}">																	
														<s:iterator value="value" var="v" status="vsta">	
															<li title="${realFileName}" class="pd-chill-tip" >																		
																<s:url id="down" action="download" namespace="/app">
															  	  	<s:param name="fileName">${fileName}</s:param>
															  	  	<s:param name="realFileName">${realFileName}</s:param>
															  	  	<s:param name="bizModuleCd">${bizModuleCd}</s:param>
															  	  	<s:param name="filterType">${filterType}</s:param>
															  	  	<s:param name="id">${appAttachFileId}</s:param>
															  	  	<s:param name="fieldName">${bizFieldName}</s:param>
															  	  	<s:param name="bizFieldName">${bizFieldName}</s:param>
															  	</s:url>
														  		<%--只有"评标"后才允许下载。 --%>
															  	<s:if test="bidLedger.bidStatusCd >= 3">
																	<a href="${down}"  onclick="preventClick(event);">${realFileName}</a>
																</s:if>
															  	<s:else>
															  		<a href="javascript:void(0)">${realFileName}</a>
															  	</s:else>
															</li>																		 
														</s:iterator>
													</c:if>	
												</s:iterator>
											</ul>
										</div>													
									</s:if>
								</s:iterator>
							</s:if>
						</td>
					</s:iterator>
				</tr>
			</s:iterator>						
		</tbody>
	</table>
</div>
 <input type="hidden" name="supCnt" id="hidden_supCnts" value="<s:property value="listBidSupVo.size"/>"></input>  	
