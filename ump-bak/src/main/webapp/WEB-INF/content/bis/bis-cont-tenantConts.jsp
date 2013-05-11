<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<div id="result_div">
<table class="content_table" id="result_table">
	<col />
	<col />
	<col width="120"/>
	<col width="90"/>
	<col width="90"/>
	<col width="90"/>
	<col width="70"/>
	<col width="50"/>
	<thead>
		<tr class="header">
			<th align="left" style="background: none;">合同编号</th>
			<th align="left">商铺</th>
			<th align="left">合同类别</th>
			<th align="left">合同开始</th>
			<th align="left">合同结束</th>
			<th align="left">审核时间</th>
			<th align="left">状态</th>
			<th align="left">附件</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="tenantConts">
			<tr class="mainTr" id="main_${bisContId}" >	
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
				
				<td onclick="goDetail('${bisContId}');" class="pd-chill-tip" title="${contNo}" style="padding-right: 6px;" >
					<div class="ellipsisDiv_full" >
					${contNo}
					</div>
				</td>
				<td onclick="goDetail('${bisContId}');" class="pd-chill-tip" title="${stname}" style="padding-right: 6px;" >
					<div class="ellipsisDiv_full" >
					${stname}
					</div>
				</td>
				<td onclick="goDetail('${bisContId}');"><p:code2name mapCodeName="mapContBigType" value="contBigTypeCd"/>-<p:code2name mapCodeName="mapContSmallType" value="contSmallTypeCd"/></td>
				<td onclick="goDetail('${bisContId}');"><s:date name="contStartDate" format="yy-MM-dd"/></td>
				<td onclick="goDetail('${bisContId}');"><s:date name="contEndDate" format="yy-MM-dd"/></td>
				<td class="pd-chill-tip" title='${checkTitle}' >
					<s:date name="checkDate" format="yy-MM-dd"/>
				</td>
				<td onclick="goDetail('${bisContId}');" <s:if test="statusCd==4">style="color: red"</s:if><s:elseif test="statusCd==3">style="color: blue;"</s:elseif> class="pd-chill-tip" title="${status}">
					<div class="ellipsisDiv_full" >
					${status}
					</div>
				</td>
				<td align="center">
					<a href="javascript:showAttachment('${bisContId}');" >
						<img id="bisContAttachId" <s:if test="attachFlg==1">src="${ctx}/resources/images/common/atta_y.gif"</s:if><s:else>src="${ctx}/resources/images/common/atta.gif"</s:else> />
					</a>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>
</div>
