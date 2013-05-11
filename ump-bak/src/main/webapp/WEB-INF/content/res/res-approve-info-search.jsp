<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%--搜索条件（公用） --%>
	<input type="hidden" name="listMode" id="listMode" value="${listMode}"/>
	<input type="hidden" name="qsCondition" id="qsCondition" value="${qsCondition}"/>
	<input type="hidden" name="resAuthTypeCd" id="resAuthTypeCd" value="${resAuthTypeCd}"/>
	<input type="hidden" name="selectedOrderBy" id="selectedOrderBy" value="${selectedOrderBy}"/>
	<input type="hidden" name="selectedOrder" id="selectedOrder" value="${selectedOrder}"/>
	<input type="hidden" name="selectOpinion" id="selectOpinion" value="${selectOpinion}"/>
	<input type="hidden" name="selectNodeCd" id="selectNodeCd" value="${selectNodeCd}"/>
	<input type="hidden" name="moduleTypeCdSrh" id="moduleTypeCdSrh" value='${moduleTypeCdSrh}'/>
	<div id="rightHeadQuick" style="background-color: #E4E7EC;border-bottom: 1px solid #AAABB0;">
			<div id="quickSearchPanel">
			<div class="quick_left">
			<input title="同时搜索‘查询号’,‘标题’,‘编号’" class="quicksearch" name="quicksearchValue" id="quicksearch" value="${quicksearchValue}" onclick="clearQuickSearch(this);" onblur="resetQuickSearch(this);" onkeyup="resQuickSearch(event)"/>
			<input type="button" class="btn_gray_65_20" class="quicksearch" value="高级搜索" onclick="searchSenior();"/>
			<s:if test="listMode==1">
			<input type="button" class="btn_blue_65_20" style="border:none;" value="常规模式" onclick="goRegularPattern();" />
			</s:if>
			<s:else>
			<input type="button" class="btn_blue_65_20" style="border:none;" value="审批模式" onclick="goApprovePattern();" />
			</s:else>
			</div>
			<div id="srh_bar_right">
			<div class="quickItem" <s:if test="3==qsCondition"> style="color:#ff0012;"</s:if> id="btnMyDeal"  onclick="searchMyDeal()">我的历史</div>
			<div class="quickItem" <s:if test="2==qsCondition"> style="color:#ff0012;"</s:if> id="btnMyDuty"  onclick="searchMyDuty()">我的审批</div>
			<div class="quickItem" <s:if test="1==qsCondition"> style="color:#ff0012;"</s:if> id="btnMyReco"  onclick="searchMyReco()">我的记录</div>
			<div class="quickTitle" >快速搜索:</div>
			</div>
			</div>
	</div>
	<jsp:include page="res-approve-info-searchdiv.jsp"/>