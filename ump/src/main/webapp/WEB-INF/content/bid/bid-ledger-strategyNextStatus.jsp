<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<div>
	<div style="display: block;" 
		 class="resultTip" 
		 id="bidStatusDesc"
		 class="pd-chill-tip">
		<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBidStatus()" value="entity.bidStatusCd"/>&nbsp;
	</div>
	<security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN" >
		<%-- 评标时,允许进入下一轮 --%>
		<s:if test="entity.bidStatusCd == 3">
			<div  style="padding-top: 5px;">
				<input type="button" class="btn_new btn_green_new" style="width:70px;" onclick="gotoNextBatchNo(${entity.batchNo},'${bidLedgerId}')" value="下一轮投标" />
			   <%--  	<input type="button" class="btn_new btn_blue_new" style="width:70px;" onclick="gotoPop('${bidLedgerId}')" value="推荐中标" />--%>
			</div>
		</s:if>
		<s:if test="entity.bidStatusCd == 0 || entity.bidStatusCd == 1">
			<div style="float:left;">
				<input type="button"
					class="btn_new btn_blue_new" 
					id="nextStatusBtn"
					unAccountedSup="${unAccountedSup}" 
					bidStatusCd="${entity.bidStatusCd}" 
					bidContAttachId="${bidContAttachId}" 
					onclick="gotoNextStatus($(this),'${bidLedgerId}','${hasSupNotReceive}')" 
					style="width:70px;" 
					value='进入<s:if test="entity.bidStatusCd == 0">邀标</s:if><s:if test="entity.bidStatusCd == 1">投标</s:if>'
				/>
			</div>
		</s:if>
	</security:authorize>
	
	
	<%--只有特定权限，才能进入评标 --%>
	<s:if test="entity.bidStatusCd == 2">
		<security:authorize ifAnyGranted="A_BID_STRA_OPEN,A_BID_STRA_ADMIN" >
			<input type="button"
				class="btn_new btn_blue_new" 
				id="btnOpen"
				unAccountedSup="${unAccountedSup}" 
				bidStatusCd="${entity.bidStatusCd}" 
				bidContAttachId="${bidContAttachId}" 
				onclick="showOpenRecord('${bidLedgerId}')" 
				style="width:70px;" 
				value='开标'
			/>
			<input type="hidden" id="hdden_bidLedgerId" value="${bidLedgerId}" />
	        <input type="hidden" id="hdden_hasSupNotReceive" value="${hasSupNotReceive}" />
		</security:authorize>
		<security:authorize ifNotGranted="A_BID_STRA_OPEN" >
			<input type="button" value="等待开标" class="btn_new resultTip" style="width:100px;display:inline;"/>
		</security:authorize>
	</s:if>
	
	

	<div style="color: red;float:left;" id="hasGuarNotConfirmedTip">
		<s:if test="hasGuarNotConfirmed==1">	 
			存在投标单位的保证金未确认
		</s:if>
	</div>
	
</div>