<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<input type="hidden" id="backReason" name="backReason" value="${backReason}"/>
<input type="hidden" id="attachFlg" name="attachFlg" value="${attachFlg}"/>
<input type="hidden" id="backDate" name="backDate" value="${backDate}"/>
<input type="hidden" id="remark" name="remark" value="${remark}"/>
<div style="border: 1px solid #cccccc; height: 430px;">
	<div class="title_bar"  style="background-color:#e4e7ec;font-weight:bold ; padding-left: 8px;font-size: 12px;color: #464646;">
		相关合同
	</div>
	<div id="tenantContsPage" style="overflow: auto;">
	<table class="content_table" >
		<col width="180"/>
		<col width="180"/>
		<col width="100"/>
		<col width="70"/>
		<col width="70"/>
		<col width="80"/>
		<col />
		<thead>
		<tr class="header">
			<th align="left" style="background: none; padding-left: 10px;">合同编号</th>
			<th align="left">商铺</th>
			<th align="left">合同类别</th>
			<th align="left">合同开始</th>
			<th align="left">合同结束</th>
			<th align="left">状态</th>
			<th align="left">操作</th>
		</tr>
		</thead>
		<tbody>
		<s:iterator value="tenantConts">
			<tr class="mainTr" onclick="viewContDetail('${bisContId}');" >	
				<s:set var="stname">
					<s:property value="%{getContStoreNos(bisContId)}"/>&nbsp;
				</s:set>
				<s:set var="status">
					<s:if test="activeBl">
					<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisContStatus()" value="statusCd"/>
					<s:if test="updateBl">(变更中)</s:if>
					</s:if>
					<s:else>已失效</s:else>
				</s:set>
				<td class="pd-chill-tip" style="padding-left: 10px; padding-right: 6px;" title="${contNo}" >
					<div class="ellipsisDiv_full" >
					${contNo}
					</div>
				</td>
				<td class="pd-chill-tip" style="padding-right: 6px;" title="${stname}" >
					<div class="ellipsisDiv_full" >
					${stname}
					</div>
				</td>
				<td><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContBigType()" value="contBigTypeCd"/>-<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContSmallType()" value="contSmallTypeCd"/></td>
				<td><s:date name="contStartDate" format="yy-MM-dd"/></td>
				<td><s:date name="contEndDate" format="yy-MM-dd"/></td>
				<td <s:if test="statusCd==4">style="color: red"</s:if><s:elseif test="statusCd==3">style="color: blue;"</s:elseif> >
					${status}
				</td>
				<td><input class="btn_green" type="button" value="查看合同明细" style="width:85px;"></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	</div>
</div>
<div style="float: right; padding-top: 10px;">
<input type="button" class="btn_blue" value="继续" onclick="goNextStep();"/>
</div>