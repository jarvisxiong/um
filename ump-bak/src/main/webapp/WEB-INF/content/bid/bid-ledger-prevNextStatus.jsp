<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	<span style="float:left;" id="batchNoTip">
			
		
<s:if test="batchNo == null || batchNo == ''">第1轮</s:if><s:else>第${batchNo}轮</s:else> /
	</span>
<%--
<s:select name="bidStatusCd" onchange="updateBidLedger($(this))" list="@com.hhz.ump.util.DictMapUtil@getMapBidStatus()" id="bidStatusCd" listKey="key" listValue="value"/>
--%>
<div style="display: block;" class="resultTip" 
	 id="bidStatusDesc"
	 class="pd-chill-tip"
	 title='
		<s:if test="bidStatusCd == 3">
			提示: 投标单位中标时,系统自动将设为定标.
		</s:if>
	'
>
	<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBidStatus()" value="bidStatusCd"/>&nbsp;
</div>

<%--
<s:if test="bidStatusCd == 1 || bidStatusCd == 2 || bidStatusCd == 3 || bidStatusCd == 4">
<div class="plgreen" onclick="gotoPrevStatus($(this),'${bidLedgerId}')">退至<s:if test="bidStatusCd == 1">导入</s:if><s:if test="bidStatusCd == 2">邀标</s:if><s:if test="bidStatusCd == 3">投标</s:if><s:if test="bidStatusCd == 4">评标</s:if></div>
</s:if>
 --%>
<security:authorize ifAnyGranted="A_BID_EDIT,A_ADMIN_BID" >
<s:if test="bidStatusCd == 0 || bidStatusCd == 1">
	<input type="button" 
		class="btn_new btn_blue_new" 
		style="width:80px;"
		unAccountedSup="${unAccountedSup}" 
		bidStatusCd="${bidStatusCd}" 
		bidContAttachId="${bidContAttachId}" 
		onclick="gotoNextStatus($(this),'${bidLedgerId}','${hasSupNotReceive}')" 
		value='进入<s:if test="bidStatusCd == 0">邀标</s:if><s:if test="bidStatusCd == 1">投标</s:if>' 
	/>
</s:if>

<s:if test="bidStatusCd == 2">
	
	<security:authorize ifAnyGranted="A_BID_OPEN" >
	 
	<input type="button" 
	    id="btnOpen"
		class="btn_new btn_blue_new" 
		style="width:80px;"
		unAccountedSup="${unAccountedSup}" 
		bidStatusCd="${bidStatusCd}" 
		bidContAttachId="${bidContAttachId}" 
		onclick="showOpenRecord('${bidLedgerId}')" 
		value='开标' 
	/>
	<input type="hidden" id="hdden_bidLedgerId" value="${bidLedgerId}" />
	<input type="hidden" id="hdden_hasSupNotReceive" value="${hasSupNotReceive}" />
	</security:authorize>
	<security:authorize ifNotGranted="A_BID_OPEN" >
		<input type="button" value="等待开标" class="btn_new resultTip" style="width:100px;display:inline;"/>
	</security:authorize>
	
</s:if>




	<div style="float:left">
		<s:if test="bidStatusCd == 0 || bidStatusCd == 1">
			<span> </span>
		</s:if>
		<s:else>
		
			
			<%--
			<span class="plgreen" onclick="backBatchNo(${batchNo},'${bidLedgerId}')"
				<s:if test="batchNo == 0||batchNo == ''|| batchNo == null">style="display:none;"</s:if>
			>上一轮
			</span>
			<span class="plgreen" onclick="addBatchNo(${batchNo},'${bidLedgerId}')">
				<s:if test="batchNo == 0||batchNo == ''|| batchNo == null">第一轮</s:if>
				<s:else>下一轮</s:else>
			</span>
			--%>
			
			<%-- 评标时,允许进入下一轮 --%>
			<s:if test="bidStatusCd == 3">
				<input type="button" style="width:80px" class="btn_new btn_green_new" onclick="gotoNextBatchNo(${batchNo},'${bidLedgerId}')" value='下一轮投标' />
				
			</s:if>
			
		</s:else>
	</div>
	</security:authorize>
	<div style="color: red;float:left;" id="hasGuarNotConfirmedTip">
		<s:if test="hasGuarNotConfirmed==1&&(bidStatusCd == 0 || bidStatusCd == 1)">	 
			存在投标单位的保证金未确认
		</s:if>
	</div>

