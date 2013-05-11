<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<div id="result_div">
<s:set var="colTitle2">
	<s:if test="contBigTypeCd == 1">商家名称</s:if>
	<s:elseif test="contBigTypeCd == 2">业主</s:elseif>
	<s:elseif test="contBigTypeCd == 3">多经名称</s:elseif>
	<s:else>商家/业主/多经</s:else>
</s:set>
<s:set var="colTitle3">
	<s:if test="contBigTypeCd == 1">商铺</s:if>
	<s:elseif test="contBigTypeCd == 2">公寓</s:elseif>
	<s:elseif test="contBigTypeCd == 3">位置区域</s:elseif>
	<s:else>商铺/公寓/位置</s:else>
</s:set>
<table class="content_table" style="line-height: 35px;" id="result_table">
	<col />
	<col />
	<col width="130"/>
	<col width="80"/>
	<col width="80"/>
	<col width="80"/>
	<col width="80"/>
	<col width="80"/>
	<thead>
		<tr class="header">
			<th align="left" style="background: none;">合同编号</th>
			<th align="left">${colTitle2}</th>
			<th align="left">${colTitle3}</th>
			<th align="left">合同类别</th>
			<th align="left">合同开始</th>
			<th align="left">合同结束</th>
			<th align="left">审核时间</th>
			<th align="left">状态</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="page.result">
			<s:set var="timeStatus">
				<s:if test="contEndDate != null">
				<s:property value="%{getTimeStatus(contEndDate)}"/>
				</s:if>
				<s:else>0</s:else>
			</s:set>
			<tr class="mainTr" id="main_${bisContId}" onclick="goDetail('${bisContId}');" 
				<s:if test="#timeStatus==1">style="color:#FF0000;"</s:if>
				<s:if test="#timeStatus==2">style="color:#909090;"</s:if>
			>
				<s:set var="shname">
					<s:if test="contBigTypeCd == 1 && contSmallTypeCd != 2 && contSmallTypeCd != 6">
					<s:property value="%{getShopName(bisTenantId)}"/>&nbsp;
					</s:if>
					<s:elseif test="contBigTypeCd == 1 && contSmallTypeCd == 2">
					${bisContShopBacks[0].owner}&nbsp;
					</s:elseif>
					<s:elseif test="contBigTypeCd == 1 && contSmallTypeCd == 6">
					${owner}&nbsp;
					</s:elseif>
					<s:elseif test="contBigTypeCd == 2">
					${bisContFlatProps[0].owner}&nbsp;
					</s:elseif>
					<s:elseif test="contBigTypeCd == 3">
					<s:property value="%{getStoreNos(bisContId)}"/>&nbsp;
					</s:elseif>
					<s:else>&nbsp;</s:else>
				</s:set>
				
				<s:set var="stname">
					<s:if test="contBigTypeCd == 3">
					${bisContMultis[0].operationArea}&nbsp;
					</s:if>
					<s:else>
					<s:property value="%{getStoreNos(bisContId)}"/>&nbsp;
					</s:else>
				</s:set>
				<s:set var="status">
					<s:if test="activeBl">
					<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisContStatus()" value="statusCd"/>
					<s:if test="updateBl">(变更中)</s:if>
					</s:if>
					<s:else>已失效</s:else>
				</s:set>
				<s:set var="contType">
					<p:code2name mapCodeName="mapContBigType" value="contBigTypeCd"/>-<p:code2name mapCodeName="mapContSmallType" value="contSmallTypeCd"/>
				</s:set>
				<s:set var="checkTitle">
					<s:if test="resApproveInfoId != null">
						网批审核
						<s:if test="checkDate != null">
						(<s:date name="checkDate" format="yy-MM-dd"/>)
						</s:if>&nbsp;
					</s:if>
					<s:else>
						<s:property value="@com.hhz.ump.util.CodeNameUtil@getUserNameByCd(checkUserCd)"/>
						<s:if test="checkDate != null">
						(<s:date name="checkDate" format="yy-MM-dd"/>)
						</s:if>&nbsp;
					</s:else>
				</s:set>
				
				<td class="pd-chill-tip" style="padding-right: 6px;" title="${contNo}">
					<div class="ellipsisDiv_full" >
					${contNo}
					</div>
				</td>
				<td class="pd-chill-tip" style="padding-right: 6px;" title="${shname}">
					<div class="ellipsisDiv_full" >
					${shname}
					</div>
				</td>
				<td class="pd-chill-tip" style="padding-right: 6px;" title="${stname}">
					<div class="ellipsisDiv_full" >
					${stname}
					</div>
				</td>
				<td class="pd-chill-tip" style="padding-right: 6px;" title="${contType}">
					<div class="ellipsisDiv_full" >
					<p:code2name mapCodeName="mapContSmallType" value="contSmallTypeCd"/>
					</div>
				</td>
				<td><s:date name="contStartDate" format="yy-MM-dd"/></td>
				<td><s:date name="contEndDate" format="yy-MM-dd"/></td>
				<td class="pd-chill-tip" title='${checkTitle}' >
					<s:date name="checkDate" format="yy-MM-dd"/>
				</td>
				<td <s:if test="!activeBl || statusCd==4">style="color: red"</s:if><s:elseif test="statusCd==3">style="color: blue;"</s:elseif> class="pd-chill-tip" title="${status} <s:date name="contToFailDate" format="yy-MM-dd"/>">
					<div class="ellipsisDiv_full" >
					${status}
					</div>
				</td>
			</tr>
		</s:iterator>
		
	</tbody>
	<tr class="pd-chill-tip" style="padding-right: 6px;">
			<td></td>
			<td ><div class="ellipsisDiv_full">建筑面积:${totalSquare}㎡</div></td>
			<td colspan='2'><div class="ellipsisDiv_full" >计租面积:${showSquareTotal}㎡</div></td>

			<td colspan='2'><div class="ellipsisDiv_full">套内面积:${innerSquareTotal}㎡</div></td>
			<td colspan='2'><div class="ellipsisDiv_full">实际收费面积:${rentSquareTotal}㎡</div></td>
		</tr>
</table>
</div>
<style type="text/css">
.table_pager {
	float: right;
	margin: 5px 0;
}
.table_pager input{width:24px;height:24px;border:1px solid #ccc;}
#pageTo{width:24px;height:22px;}
</style>
<div class="table_pager">
	<p:page />
</div>
