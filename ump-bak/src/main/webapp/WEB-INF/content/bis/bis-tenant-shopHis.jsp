<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<div style="padding: 10px;">
<div style="padding-left: 10px; height: 30px; line-height:30px; border: 1px solid #eeeeee; overflow: hidden; white-space: nowrap;">
	<table style="overflow: hidden; white-space: nowrap; width: 100%;">
		<tr>
			<td width="70px;"><span style="font-weight: bold;">当前有效：</span></td>
			<td width="40px;">商家：</td>
			<td width="100px;" ><span style="color: blue;" class="link_bis" onclick="openShopTab('${bisShopId}');" title="查看商家">${nameCn}</span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td width="40px;">商铺：</td>
			<td class="pd-chill-tip" title="${bisStoreNos}"><div style="color: blue; max-width: 92%;" class="ellipsisDiv">${bisStoreNos}</div></td>
		</tr>
	</table>
</div>

<div style="margin-top: 10px;">
	<div class="title_bar"  style="background-color:#e4e7ec;font-weight:bold ; padding-left: 8px;font-size: 12px;color: #464646;">
		商家历史
	</div>
	<div id="tenantContsPage" style="overflow: auto;">
	<table class="content_table" >
		<col width="360"/>
		<col width="140"/>
		<col width="70"/>
		<col width="100"/>
		<thead>
		<tr class="header">
			<th align="left" style="background: none; padding-left: 10px;">商铺</th>
			<th align="left">租期</th>
			<th align="left">经营性质</th>
			<th align="left">业态</th>
		</tr>
		</thead>
		<tbody>
		<s:if test="tenantHisList.size()==0">
			<tr>
			<td colspan="6"><div style="color: #6BAD42; font-size: 14px; font-weight: bold; text-align: center; line-height: 200px;">暂无记录</div></td>
			</tr>
		</s:if>
		<s:else>
			<s:iterator value="tenantHisList">
			<tr class="mainTr" >	
				<td class="pd-chill-tip" style="padding-left: 10px; padding-right: 6px;" title="${bisStoreNos}" >
					<div class="ellipsisDiv_full" >
					${bisStoreNos}
					</div>
				</td>
				<td>
					<s:date name="bisTenant.rentStartDate" format="yy-MM-dd"/>至
					<s:if test="bisTenant.backDate != null">
					<s:date name="bisTenant.backDate" format="yy-MM-dd"/>
					</s:if>
					<s:else>
					<s:date name="bisTenant.rentEndDate" format="yy-MM-dd"/>
					</s:else>
				</td>
				<td><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" value="bisTenant.manageCd"/></td>
				<td><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapStoreLayout()" value="bisTenant.layoutCd"/></td>
				<%-- 
				<td><s:date name="backDate" format="yy-MM-dd"/></td>
				<td><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisBackReason()" value="backReason"/>-<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContSmallType()" value="contSmallTypeCd"/></td>
				--%>
			</tr>
			</s:iterator>
		</s:else>
		</tbody>
	</table>
	</div>
</div>
</div>

