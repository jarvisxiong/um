<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<input type="hidden" id="backReason" name="backReason" value="${backReason}"/>
<input type="hidden" id="attachFlg" name="attachFlg" value="${attachFlg}"/>
<input type="hidden" id="backDate" name="backDate" value="${backDate}"/>
<input type="hidden" id="remark" name="remark" value="${remark}"/>
<div style="border: 1px solid #cccccc; height: 430px;">
	<div class="title_bar" style="font-weight:bold;padding-left:8px;font-size:12px;color:#464646;">
		相关费用&nbsp;&nbsp;&nbsp;&nbsp;
		<input class="btn_green" type="button" onclick="viewFeeDetail();" value="查看收费明细" style="width:90px; margin-top:4px;">
	</div>
	<div id="tenantContsPage">
	<table class="content_table" >
		<col width="150"/>
		<col width="120"/>
		<col width="120"/>
		<col />
		<thead>
		<tr class="header">
			<th align="left" style="background: none; padding-left: 10px;">费用类别</th>
			<th align="left">应收总计</th>
			<th align="left" colspan="2">实收总计</th>
		</tr>
		</thead>
		<tbody>
		<s:iterator value="tenantFees">
		<tr class="mainTr" onclick="viewFeeDetail();" >	
			<td style="padding-left: 10px;">
				<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapChargeType()" value="chargeTypeCd"/>
			</td>
			<td <s:if test="mustTotal > factTotal">style="color:red;"</s:if> >${mustTotal}</td>
			<td <s:if test="mustTotal > factTotal">style="color:red;"</s:if> >${factTotal}</td>
			<td></td>
		</tr>
		</s:iterator>
		<tr class="mainTr" onclick="viewFeeDetail();" >	
			<td style="padding-left: 10px; color: red;">
				总计：
			</td>
			<td <s:if test="totalFeeVo.mustTotal > totalFeeVo.factTotal">style="color:red;"</s:if> >${totalFeeVo.mustTotal}</td>
			<td <s:if test="totalFeeVo.mustTotal > totalFeeVo.factTotal">style="color:red;"</s:if> >${totalFeeVo.factTotal}</td>
			<td></td>
		</tr>
		</tbody>
	</table>
	</div>
</div>
<div style="float: right; padding-top: 10px;">
<input type="button" class="btn_blue" value="上一步" onclick="goPrevStep();"/>
<input type="button" class="btn_blue" value="继续" onclick="goNextStep();"/>
</div>