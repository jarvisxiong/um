<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<table class="content_table" id="editTable" cellspace="0"
	cellpadding="0"
	style="width: expression(this.parentNode.offsetHeight &gt; this.parentNode.scrollHeight ?   '100%' : parseInt(this.parentNode.offsetWidth -   18)+  'px' );">
	<%--
	<s:if test='searchTypeCd == 2'>
	<col width="20">
	</s:if>
	 --%>
	<col>
	<col width="130">

	<s:iterator value="page.result">
		<tr id="main_${plasApproveInfoId}"
			style="cursor: pointer; height: 45px; padding-bottom: 15px; padding-top: 5px;"
			class="mainTr">
			<%--
			<s:if test='searchTypeCd == 2'>
			<td valign="middle" nowrap="nowrap">
				<div style="margin-top:5px;" class="ck_hide"  >
					<input type="checkbox" id="<s:property value="#st.count"/>_ch" relStatusCd='${approveStatusCd}' relId="${plasApproveInfoId}" />
				</div>
			</td>
			</s:if>
			 --%>
			<td valign="middle" style="padding-top:10px;" onclick="openDetail('${plasApproveInfoId}','${applyTypeCd }','${approveStatusCd }','${creator}', '${currentUiid}');">
				<%-- 第一行 --%>
				<div class="div_row">
					<span class="wap_title">
						<span class="font_18"><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapApproveApplyType()" value="applyTypeCd"/></span>
					</span>
					<span class="wap_title">
						[<span class="font_14" style="font-weight:bold">${fn:replace(newParentName, "<br/>", "-")}</span>]
					</span>
					<span class="wap_title">
						-
					</span>
					<span class="wap_title">
						<span class="font_14 font_color_1">${newName}</span>
					</span>
					<span class="wap_title">
						-
					</span>
					<span class="wap_title">
						<s:if test="approveStatusCd==1"><span class="font_14">未提交</span></s:if>
						<s:if test="approveStatusCd==2"><span class="font_14">审批中</span></s:if>
						<s:if test="approveStatusCd==3"><span class="font_14">完成</span></s:if>
						<s:if test="approveStatusCd==4"><span class="font_14">驳回</span></s:if>
					</span>
				</div> 
				
				<%-- 第二行 --%>
				<div style="margin-bottom: 15px;">
					
					<s:if test="applyTypeCd!=2">
						<div class="div_row">
							<span class="wap_sec_title">${newParentName }</span> 
							<span class="wap_sec_title">
								<p:code2name
									mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapPermLevel()"
									value="newLevelCd" /></span> <span class="wap_sec_title">${newSysPosName}
							</span> 
							<span class="wap_sec_title">${newWorkDuty}</span>
						</div>
					</s:if>
					<%-- 变动信息 --%>
					<s:else>
						<s:if test="oldParentName!=newParentName">
							<%--
							<span class="wap_sec_title">${oldParentName}->${newParentName } </span>
							 --%>
							 <%--替换字符 --%>
							<span class="wap_sec_title">${fn:replace(oldParentName, "<br/>", "-")} -> ${fn:replace(newParentName, "<br/>", "-")}</span>
						</s:if>
						<s:if test="oldLevelCd!=newLevelCd">
							<span class="wap_sec_title">
								<p/> 
								<p:code2name
									mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapPermLevel()"
									value="oldLevelCd" />-> <p:code2name
									mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapPermLevel()"
									value="newLevelCd" /> </span>
						</s:if>
						<s:if test="oldSysPosName!=newSysPosName">
							<span class="wap_sec_title"> <p/> ${oldSysPosName} -> ${newSysPosName } </span>
						</s:if>
						<s:if test="oldWorkDuty!=newWorkDuty">
							<span class="wap_sec_title"> <p/> ${oldWorkDuty } -> ${newWorkDuty} </span>
						</s:if>
					</s:else>
				</div>
			</td>
			<td valign="middle">
				<s:if test="approveStatusCd == 2 && searchTypeCd == 2">
					<%-- 操作按钮与checkbox相对位置变化，需要修改代码有关系! --%>
					<span class="wap_title"> <span style="display: inline-block"
						class="btn_blue font_14"
						onclick="pass('${plasApproveInfoId}',this)">同意</span> <span
						style="display: inline-block" class="btn_red font_14"
						onclick="reject('${plasApproveInfoId}',this)">驳回</span>
					</span>
				<br />
				</s:if> 
				
				<s:if test="approveStatusCd == 4 && searchTypeCd == 1">
					<span class="wap_title"> <span style="display: inline-block"
						class="btn_blue font_14"
						onclick="reApprove('${plasApproveInfoId}',this)">提交</span>
					</span>
				<br />
				</s:if>
				
				<span class="wap_title" title="发起时间:<s:date name="updatedDate" format="yyyy-MM-dd HH:mm"/>">
					<s:date name="updatedDate" format="yyyy-MM-dd HH:mm" />
				</span> 
			</td>
		</tr>
	</s:iterator>
</table>

<s:if test="page.hasPre || page.hasNext">
	<div class="table_pager" style="margin-top: 5px;">
		<p:page />
	</div>
</s:if>

<s:if test="page.result.size == 0">
	<div style="line-height: 40px;margin-left:10px;font-weight: bold;font-size: 14px;">搜索结果: 无记录!</div>
</s:if>