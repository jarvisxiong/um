<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div>
<table class="content_table" id="sup_table" style="width:100%;">
<thead>
	<tr class="header">
		<th width="80" style="background: none;"></th>
		<th width="80" align="left">序号</th>
	<!--<th width="60" align="left">计划</th>  -->	
		<th align="left">事项</th>
		<th width="60" align="left">负责人</th>
		<th width="60" align="left">进度</th>
		<th width="60" align="left">状态</th>
		<th width="70" align="left">定标时间</th>
		<th width="80" align="left">等待</th>
		<th width="40" align="left">更新
		<span title="按更新时间排序" onclick="updateByDate();"><img src="${ctx}/images/plan/btn_down_10_10.gif"></span>
		</th>
	</tr>
</thead>
<tbody>
 <s:iterator value="dataMap" id="column" status="status">  
	<tr>
		<td colspan="9" style="padding-left:10px;" class="group-title">
			<%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("#column.key")) %>(<s:property value='#column.value.size' />)
		</td>
	</tr>
	<div>
	<s:iterator value="#column.value" status="s">
	<input type="hidden" id="supType_${costCtrlBidPurcId}" name="costCtrlSupplierses[0].supTypeCd" value="${costCtrlSupplierses[0].supTypeCd}"/>
	<!-- 主信息 -->
	<tr class="mainTr" id="main_${costCtrlBidPurcId}"  onclick="supplyDetail('${costCtrlBidPurcId}');"
	<%if(JspUtil.getCompareDate("costCtrlSupplierses[0].createdDate",20)){ %>
	 <s:if test="scheduleStatusCd == 1">
		style="color:red" title="已过期" class="pd-chill-tip" 
	 </s:if>			
	 <%} %>
	>	
	    <td>
			<img style="float:left;padding-right:5px;" src="${ctx}/resources/images/common/right_grey.gif">
			<div style="padding-left:10px;" class="cost-read" style="height:20px;line-height:20px;">
				<!--<div class="pd-chill-tip" title="低" style="color:#ffb401;float:left;
					<s:if test="importantTypeCd != 0"> display:none; </s:if>"
				>！</div>
				<div class="pd-chill-tip" title="普通" style="color:#f36511;float:left;
					<s:if test="importantTypeCd != 1"> display:none; </s:if>"
				>！</div>
				--><div class="pd-chill-tip" title="重要" style="color:#ff0100;float:left;
					<s:if test="importantTypeCd != 2"> display:none; </s:if>"
				>！</div>
				<%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("projectCd")) %>
			</div>
		</td>
		<td>${serialType}${serialNo}</td>
		<!-- 
		<td>
			<div class="cost-read"><p:code2name mapCodeName="planTypeMap" value="planTypeCd"/> </div>
		</td>
		 -->
		<td>
			<div class="cost-read pd-chill-tip" title="${planContentDesc }">${planContentDesc }</div>
		</td>
		<td onclick="changeOwner(this,'${costCtrlBidPurcId}')">
			<div>${ownerNames}</div>
		</td>
		<td 
		   <s:if test="dataTypeCd == 0 && bidStatusCd == 7">
				style="background-color:#FFC125"
			</s:if>
			<s:if test="dataTypeCd == 1 && bidStatusCd == 5">
				style="background-color:#FFC125"
			</s:if>
			  >
		  <s:if test="bidStatusCd != null && bidStatusCd != ''">
			<s:if test="dataTypeCd == 0">
				<p:code2name mapCodeName="bidScheMap" value="bidStatusCd"/>
			</s:if>
			<s:if test="dataTypeCd == 1">
				<p:code2name mapCodeName="purScheMap" value="bidStatusCd"/>
			</s:if>
			</s:if>
		</td>
		<td>
		  <s:if test="scheduleStatusCd == 0">新增</s:if>
			<s:if test="scheduleStatusCd == 1">进行中</s:if>
			<s:if test="scheduleStatusCd == 2">已完成</s:if>
				<%if(JspUtil.getCompareDate("costCtrlSupplierses[0].createdDate",20)){ %>
		 <s:if test="scheduleStatusCd == 1">
			<img title="创建供应商起超20天算为过期" src="${ctx}/resources/images/plan/pic_delay.gif">
		 </s:if>			
	 <%} %>
			
		</td>
		<td><s:date name="bidDate" format="yy-MM-dd"/></td>
		<td>
			<s:if test="isWaitProjectFlag == 1">
				<div title="${waitProjectDesc }">项目</div>
			</s:if>
			<s:if test="isWaitTechFlag == 1">
				<div title="${waitTechDesc }">技术研发中心</div>
			</s:if>
			<s:if test="isWaitOtherFlag == 1">
				<div title="${waitDesc }">${waitDeptName}</div>
			</s:if>
		 </td>
		<td class="pd-chill-tip" title='<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("updator")) %>(<s:date name="updatedDate" format="yyyy-MM-dd HH:mm"/>)'><s:date name="updatedDate" format="MM-dd"/></td>
	</tr>
	
	<!-- 详细信息，包含留言、进度信息 -->
	<tr class="detailTr" id="detail_${costCtrlBidPurcId}" valign="top" style="display: none;">
		<td colspan="9" style="border-bottom:1px solid #AAABB0;">
			<form action="${ctx}/plan/cost-ctrl-bid-purc!save.action" id="form_${costCtrlBidPurcId}" method="post">
			<input type="hidden" name="id" value="${costCtrlBidPurcId}"/>
			<table style="width:100%;border-top:1px solid #AAABB0;">
				<tr>
					<td align="center" style="border-right:1px solid #DCDCDE;border-bottom:1px solid #DCDCDE;background-color: #E4E7EC;">开始时间</td>
					<td  style="border-bottom:1px solid #DCDCDE;">
						<div style="height:28px;padding-top:5px;">
							<span><s:date name="createdDate" format="MM-dd hh:mm"/></span>
							<span>创建人:</span>
							<span><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %></span>&nbsp;&nbsp;状态：
							<span id="supSpan_${costCtrlBidPurcId}">
							<s:if test="isSuppCompleteFlag==1">未完成&nbsp;
							<security:authorize ifAnyGranted="A_COST_SUPCOMP">
							 <button class="btn_green_55_20" type="button" onclick="updateSupStatus('${costCtrlBidPurcId}')">完成</button>
							 </security:authorize>
							</s:if>
							<s:elseif test="isSuppCompleteFlag==2">已完成</s:elseif>
							</span>
						</div>
					</td>
				</tr>
				<tr>
				<td align="center" style="border-bottom:1px solid #DCDCDE;border-right:1px solid #DCDCDE;background-color: #E4E7EC;">留言</td>
				<td valign="top" style="border-bottom:1px solid #DCDCDE;">
					<div style="line-height:30px;float:left;margin:10px;" >
						<table style="width:100%;border:1px solid #DDDBDC;">
							<col width="400">
							<tr>
								<td class="bg-title" style="padding-left:10px;">招标信息表(
								<s:if test="costCtrlSupplierses[0].supTypeCd==1">工程类</s:if>
								<s:elseif test="costCtrlSupplierses[0].supTypeCd==2">材料设备类</s:elseif>)
								</td>
							</tr>
							<tr>
							<td style="padding-left:10px;">
							  <table>
							     <col width="100">
								 <col width="300">
							     <tr style="display:none;" class="sup_project sup_material">
								   <td>招标项目名称</td>
								   <td><input type="text" name="costCtrlSupplierses[0].bidProjectName" value="${costCtrlSupplierses[0].bidProjectName}"/></td>
								 </tr>
								 <tr style="display:none;" class="sup_project sup_material">
								   <td>招标范围</td>
								   <td><input type="text" name="costCtrlSupplierses[0].projectRangeDesc" value="${costCtrlSupplierses[0].projectRangeDesc}"/></td>
								 </tr>
								 <tr style="display:none;" class="sup_material">
								   <td>供货期</td>
								   <td><input type="text" name="costCtrlSupplierses[0].supMaterAreaDesc" value="${costCtrlSupplierses[0].supMaterAreaDesc}"/></td>
								 </tr>
								 <tr style="display:none;" class="sup_project sup_material">
								   <td>质量标准</td>
								   <td><input type="text" name="costCtrlSupplierses[0].qualityStandardDesc" value="${costCtrlSupplierses[0].qualityStandardDesc}"/></td>
								 </tr>
								 <tr style="display:none;" class="sup_material">
								   <td>技术标准</td>
								   <td><input type="text" name="costCtrlSupplierses[0].techStandardDesc" value="${costCtrlSupplierses[0].techStandardDesc}"/></td>
								 </tr>
								 <tr style="display:none;" class="sup_material">
								   <td>规格/型号</td>
								   <td><input type="text" name="costCtrlSupplierses[0].materialSerialNoDesc" value="${costCtrlSupplierses[0].materialSerialNoDesc}"/></td>
								 </tr>
								 <tr style="display:none;" class="sup_material">
								   <td>数量</td>
								   <td><input type="text" name="costCtrlSupplierses[0].materialQuantityDesc" value="${costCtrlSupplierses[0].materialQuantityDesc}"/></td>
								 </tr>
								 
								 <tr style="display:none;" class="sup_project">
								   <td>工期</td>
								   <td><input type="text" name="costCtrlSupplierses[0].projectPeriodDesc" value="${costCtrlSupplierses[0].projectPeriodDesc}"/></td>
								 </tr>
								 <tr style="display:none;" class="sup_project">
								   <td>计价方式</td>
								   <td><input type="text" name="costCtrlSupplierses[0].priceMethodDesc" value="${costCtrlSupplierses[0].priceMethodDesc}"/></td>
								 </tr>
								 <tr style="display:none;" class="sup_project sup_material">
								   <td>投标保证金</td>
								   <td><input type="text" name="costCtrlSupplierses[0].bidSecurityDesc" value="${costCtrlSupplierses[0].bidSecurityDesc}"/></td>
								  </tr>
								 <tr style="display:none;" class="sup_project sup_material">
								   <td>付款条件</td>
								   <td><input type="text" name="costCtrlSupplierses[0].payConditionDesc" value="${costCtrlSupplierses[0].payConditionDesc}"/></td>
								 </tr>
								 <tr style="display:none;" class="sup_project">
								   <td>图纸情况</td>
								   <td><input type="text" name="costCtrlSupplierses[0].drawingProgressDesc" value="${costCtrlSupplierses[0].drawingProgressDesc}"/></td>
								 </tr>
								 
								 <tr style="display:none;" class="sup_project sup_material">
								   <td>其他</td>
								   <td><input type="text" name="costCtrlSupplierses[0].otherProgressDesc" value="${costCtrlSupplierses[0].otherProgressDesc}"/></td>
								 </tr>
								 <tr style="display:none;" class="sup_project sup_material">
								   <td>经办人</td>
								   <td><input type="text" name="costCtrlSupplierses[0].handlerName" value="${costCtrlSupplierses[0].handlerName}"/></td>
								 </tr>
								 <tr style="display:none;" class="sup_project sup_material">
								   <td>负责人</td>
								   <td><input type="text" name="costCtrlSupplierses[0].supOwnerName" value="${costCtrlSupplierses[0].supOwnerName}"/></td>
								 </tr>
							  </table>
							</td>
							</tr>
							
						</table>
					</div>
					<!-- 留言区域 -->
					<div id="msg_container_${costCtrlBidPurcId}" style="line-height:25px;float:left;margin:10px 10px 10px 50px;">
						<s:iterator	value="costCtrlMessages">
							<div class="detail_message_div">
								<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>
								(<s:date name="createdDate" format="MM-dd HH:mm"/>):${msgContentDesc}</div>
						</s:iterator>
						<div style="margin-top:5px;">
						<textarea style="height: 53px;float:left;" cols="36" rows="3" id="message_${costCtrlBidPurcId}"></textarea>
						<button class="btn_blue_55_55" style="margin-left: 2px;float:left;" type="button" onclick="saveMsg('${costCtrlBidPurcId}')">发表</button>
					  </div>
					</div>
				</td>
				</tr>
			</table>
			</form>
		</td>
	</tr>
	</s:iterator>
	</div>
</s:iterator>
</tbody>
</table>
<div align="right">
<form id="pageForm" method="post" pageType="${pageType }" action="">
		<p:page />
	</form>
</div>
</div>