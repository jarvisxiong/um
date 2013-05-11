<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<div style="border: 1px solid #cccccc; height: 430px;">
	<div class="title_bar"  style="background-color:#e4e7ec;font-weight:bold ; padding-left: 8px;font-size: 12px;color: #464646;">
		退铺确认
	</div>
	<div id="tenantContsPage" style="padding: 10px;">
	<table style="width:100%; line-height:30px;">
		<col width="120"/>
		<col width="350"/>
		<col />
		<tr>
			<td style="text-align: right;">退铺原因：</td>
			<td>
				<s:select id="backReason" list="@com.hhz.ump.util.DictMapUtil@getMapBisBackReason()" listKey="key" listValue="value" name="backReason" ></s:select>
			</td>
			<td></td>
		</tr>
		<tr>
			<td style="text-align: right;">退铺日期：</td>
			<td>
				<input id="backDate" validate="required" class="Wdate" type="text" name="backDate" value="${backDate}" onfocus="WdatePicker()"/>
			</td>
			<td></td>
		</tr>
		<tr>
			<td style="text-align: right;">退铺资料上传：</td>
			<td>
				<input type="hidden" id="attachFlg" name="attachFlg" value="${attachFlg}"/>
				<a href="javascript:openAttachment('退铺资料','${bisTenantId}','bisTenantAttachId','attachFlg');" >
				<img id="bisTenantAttachId" style="padding-top: 12px;" <s:if test="attachFlg==1">src="${ctx}/resources/images/common/atta_y.gif"</s:if><s:else>src="${ctx}/resources/images/common/atta.gif"</s:else> />
				</a>
			</td>
			<td></td>
		</tr>
		<tr>
			<td style="text-align: right;">备注：</td>
			<td>
				<textarea id="remark" style="width:100%;height:80px;font-size:12px;" name="remark" >${remark}</textarea>
			</td>
			<td></td>
		</tr>
	</table>
	</div>
</div>
<div style="float: right; padding-top: 10px;">
<input type="button" class="btn_blue" value="上一步" onclick="goPrevStep();"/>
<input type="button" class="btn_blue" value="完成" onclick="doComplete();"/>
</div>