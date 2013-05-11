<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="toolBar">
	<input type="button" value="返回" class="btn_green" onclick="openDetail();" />
</div>
<div class="list_header"><span style="font-size:14px;">审批历史<s:if test="approveHises.size==0">&nbsp;&nbsp;(无记录)</s:if></span></div>

<div style="padding:0px 5px;margin:5px;">
	<table class="content_table showTable" id="showTableApproveRec" width="100%" style="font-size:16px;">
		<s:iterator value="approveHises" var="ra">
			<tr class="mainTr">
				<td style="padding:10px 0px;">
					<div style="width:80px;float:left;color:#0167a2;padding-left:5px;word-wrap:break-word;">
						<div style="width:80px;float:left;">${userName}</div>
					</div>
					<div style="width:80px;float:left;"> 
						<s:if test="groupNodeCd==39 || nodeCd==97">
							<p:code2name mapCodeName="mapOptionZyps" value="approveOptionCd"/>
						</s:if>
						<s:else>
							<p:code2name mapCodeName="mapOption" value="approveOptionCd"/>
						</s:else>
					</div>
					<s:if test="approveDate!=null">
					<div style="float:right;"><s:date name="approveDate" format="yyyy-MM-dd HH:mm" />
					</div>
					</s:if>
					<div style="width:90px;float:right;">
						<p:code2name mapCodeName="mapNode" value="groupNodeCd"/>
					</div>
					<s:if test="remark!=null">
					<div style="clear:both;padding:5px 0 5px 5px;"><pre style="white-space:pre-wrap;" class="approveOption">${remark}</pre></div>
					</s:if>
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
