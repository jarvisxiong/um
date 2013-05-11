<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<s:if test="priceIndexVotwos!=null">
<div style="overflow-x: auto;" >

	<table class="tab1" class="tab1" cellspacing="0" cellpadding="0" bordercolor="#83B3DE" border="1" align="center" width="800px;" >	
		<tbody>
			<tr>
				<td colspan="10">
					<div style="margin-left: 10px;clear: both;color: red;">	
						<div style="float: left;">业态：<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" value="bussinessCd"/></div>  
						<div style="float: left;margin-left: 20px;">时间：${ym}</div>				
					</div>
				</td>
			</tr>
			<s:iterator var="w" value="priceIndexVotwos" status="status">
			<%--单位消耗量,基准工料价格 行需要加底色 --%>
			<s:if test="#status.index==1||#status.index==2">
				<tr style="background-color: #e4e4e4;">
			</s:if>
			<s:else>
				<tr >
			</s:else>
					<%--如果是基础的前3行 --%>
					<s:if test="#status.index==1||#status.index==2||#status.index==0">
						<td colspan="2" align="center">${rowName }</td>
					</s:if>
					<%--如果非基础行，开始合并则合并 --%>
					<s:else>
						<%--合并标记 --%>						
						<s:if test="startMerge!=null">
							<td rowspan="5" style="background-color: #e4e4e4;" width="3%">
							<div >
								<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdAreaCd()" value="areaCd"/>
							</div>
							</td>
						</s:if>		
						<td align="left" ><div style="padding-left: 5px;">${rowName }</div></td>
					</s:else>
						<%--如果是第一行 ,循环打印业态--%>					
						<s:if test="#status.index==0">
							<s:iterator var="i" value="itemVos" status="status">
									<td nowrap="nowrap" <%--人工 --%>
									<s:if test="itemVal==1">title='(工日)'</s:if>
									<%--钢筋 --%>
									<s:elseif test="itemVal==2">title='(kg)'</s:elseif>
									<%--砼 --%>
									<s:elseif test="itemVal==3">title='(m3)'</s:elseif>
									<%--砌块 --%>
									<s:elseif test="itemVal==4">title='(m3)'</s:elseif>
									>
								<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdMaterialZoneCd()" value="itemVal"/>								
								</td>
							</s:iterator>
						</s:if>
						<%--如果不是第一行 ,循环打印业态值--%>	
						<s:else>
							<s:iterator var="i" value="itemVos" status="status2">
							<%--如果是年差且数值为0 --%>
							<s:if test="rowName=='同比差(年差)'&&itemVal<=0">
								<td width="8%">/</td>
							</s:if>
							<s:else>
								<td width="8%">${itemVal}</td>
							</s:else>
								
							
							</s:iterator>
						</s:else>
					<%--如果是第一行 ,其他4个字段--%>			
					<s:if test="#status.index==0">
						<td width="13%" title="计算公式：∑(消耗量*工料价格)">工料加权价(元)</td>
						<td width="13%" title="计算公式：月工料加权价-基准工料加权价">工料加权差额</td>
						<td  width="13%"  title="计算公式：工料加权差额+基准建安单价造价">建安单价造价(元)</td>
						<td  width="13%" title="计算公式：月工料加权价/基准工料加权价">工料价格指数</td>							
					</s:if>	
					<%--如果不是第一行 ,其他4个字段的值--%>		
					<s:else>
						<%--如果是年差且工料加权价(元)数值为0 --%>
						<s:if test="rowName=='同比差(年差)'&&mateTotalPrice<=0">
							<td>/</td>
						</s:if>
						<s:else>
							<td>${mateTotalPrice}</td>
						</s:else>
						
						<td>${mateTotalDifference}</td>
						
						
						<%--如果是年差且建安单价造价(元)数值为0 --%>
						<s:if test="rowName=='同比差(年差)'&&constructUnitPrice<=0">
							<td>/</td>
						</s:if>
						<s:else>
							<td>${constructUnitPrice}</td>
						</s:else>
						
						<td>${priceIndex}</td>		
					</s:else>	
				</tr>
			</s:iterator>			
		</tbody>	
	</table>	
</div>
</s:if>
<s:else>
 <div style="clear: left;font-weight: lighter;margin-left: 15px;">抱歉,暂无数据！</div>
</s:else>

<div style="clear: both;margin-left: 5px;color: red;margin-top: 10px;margin-bottom: 15px;">
	说明：如果表格中存在为"0"的数据，表示在系统中还未录入或录入时为"0",请联系维护数据的相关人员及时维护数据进入系统;
	<pre/>备注：工料价格指数基准价：733元/m2 
</div>

