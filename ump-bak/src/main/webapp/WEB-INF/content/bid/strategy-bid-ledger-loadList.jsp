<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<table class="bidTable" cellpadding="0" cellspacing="0" border="0">		
<colgroup>
    <col width="120"/> 
    <col /> 
	<col />
	<col width="90" />
	<col /> 
<%--	<col /> 
	<col /> --%>
	<col width="110" />
	<col width="110" />
	<col width="50" />
	<col width="70" />
</colgroup>
<thead>
	<tr>
	  	<th title="战略平台编号"  style="background: none;"><div style="margin-left:5px;" class="ellipsisDiv_full">战略平台编号</div></th>
	  	<th title="战略名称"><div style="margin-left:5px;" class="ellipsisDiv_full">战略名称</div></th>
	  	<th title="项目名称"><div class="ellipsisDiv_full">项目名称</div></th>
	  	<th title="期数"><div class="ellipsisDiv_full">期数</div></th>
	  	<th title="工程"><div class="ellipsisDiv_full">工程</div></th>
<%--	   	<th title="邀请类别" class="sortField"><div class="ellipsisDiv_full">邀请类别 </div></th>
	   	<th title="需要保证金"><div class="ellipsisDiv_full">需要保证金</div></th>--%>
	  	<th title="保证金截止日期"><div class="ellipsisDiv_full">保证金截止日期</div></th>
	   	<th title="应标截止日期" class="sortField"><div class="ellipsisDiv_full">应标截止日期</div> </th>
	   	<th title="投标状态" class="sortField"><div class="ellipsisDiv_full">状态</div></th>
	   	<th title="创建日期" class="sortField"><div class="ellipsisDiv_full">创建日期</div></th>
	</tr>
</thead>
<tbody id="projectVoList">
	<s:iterator value="voPage.result" var="vo"  status="statu">
		<%-- 概要信息 --%>
		<tr id="brief_<s:property value='#vo.bidLedgerId'/>" class="commonTr" style="height:36px" onclick="showLedgerDetail('${bidLedgerId}')" >	
			<td style="padding-left:5px;background: none;">
				<div>${serialType}${serialNo}</div>
			</td>				
			<%-- 材料设备名称 --%>
			<td style="padding-left:5px;">
				<div class="ellipsisDiv" title="${bidSectionName}">
					${bidSectionName}
				</div>							
			</td>
			<%-- 项目名称 --%>
			<td>
				<div class="ellipsisDiv" title="${orgName}">
					${orgName}
				</div>							
			</td>
			<%-- 期数 --%>
			<td>
				<div class="ellipsisDiv" title="${periodNum}">
					${periodNum}
				</div>							
			</td>
			<%-- 工程 --%>
			<td>
				<div class="ellipsisDiv" title="${construction}">
					${construction}
				</div>							
			</td>
			<%-- 邀请类别 
			<td title="<s:property value="#vo.bidSectionName"/>">
				<div class="ellipsisDiv" style="margin-left: 5px;">
					<s:if test="visableFlg==1">
					 	明标
					</s:if>
					<s:else>
						暗标
					</s:else>
				</div>
			</td>--%>
			<%-- 是否有保证金
			<td  >
				<div class="ellipsisDiv" style="margin-left: 3px;">
					<s:if test="needGuarFlg==1">
						是
					</s:if>
					<s:else>
						否
					</s:else>
					是
				</div>
			</td>--%>
			<%-- 保证金截止日期 --%>
			<td>
				<div class="ellipsisDiv" style="margin-left: 3px;">
			  	 	<s:date  name="lastGuarDate" format="yyyy-MM-dd"/>
			    </div>
			</td>  
			<%--投标单位 --%>
			<%--
			<td >
				<div style="padding-left: 5px;">
					<s:set name="cnt" value="0" var="cnt"></s:set>
					<s:iterator value="#vo.bidSups" var="vd" >
						<s:if test="#vd.receiveStatusCd==1">
							<s:property value="cnt++"/>
						</s:if>
					</s:iterator>
				</div>
			</td>
			 --%>						
			<td title="<s:date name="lastReceDate" format="yyyy-MM-dd"/>">
				<div class="ellipsisDiv" style="margin-left: 3px;">
					<s:date name="lastReceDate" format="yy-MM-dd"/>
				</div>
			</td>
			<td>
				<div class="ellipsisDiv" style="margin-left: 3px;">
					 <p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBidStatus()" value="#vo.bidStatusCd"/>
				</div>	 
			</td>
			<td title="<s:date name="#vo.createdDate" format="yyyy-MM-dd"/>">
				<div class="ellipsisDiv" style="margin-left: 3px;">
					<s:date name="#vo.createdDate" format="yy-MM-dd"/>
				</div>
			</td>
		</tr>
	</s:iterator>
</tbody>
</table>

<script type="text/javascript">
	/*
$(function(){
	//排序
	$('.sortField').toggle(
		function(){
			$('.sortFieldAsc').removeClass('sortFieldDesc').addClass('sortFieldAsc');
			$('#sort').val($(this).attr('sort'));
			if($('#order').val()=='asc'){
				$('#order').val('desc');
				}else{
					$('#order').val('asc');	
				}
			
			searchBidLedger();
		},
		function(){
			$('.sortFieldAsc').removeClass('sortFieldAsc').addClass('sortFieldDesc');
			$('#sort').val($(this).attr('sort'));
			if($('#order').val()=='desc'){
				$('#order').val('asc');
				}else{
					$('#order').val('desc');	
				}
			searchBidLedger();
		}
	);
});
*/
</script>
<div class="pagerRight" id="sourcePager"><p:page  pageInfo="voPage"/></div>