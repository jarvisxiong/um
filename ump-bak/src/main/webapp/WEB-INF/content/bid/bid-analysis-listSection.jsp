<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>


<%--
<input type="text" name="bidSectionName" id="bidSectionName" value="${bidLedger.bidSectionName}" readonly="readonly"/>
<input type="hidden" name="bidLedgerId" id="bidLedgerId" value="${bidLedger.bidLedgerId}"/>
<s:select 
	id="listSection" 
	name="bidLedgerId"
	list="@com.hhz.ump.web.bid.BidAnalysisAction@getMapSection()" 
	listKey="key" 
	listValue="value"
	onchange="loadPanelByBidId()" 
	value="bidLedger.bidLedgerId"
/>
 --%>
<select onchange="loadPanelByBidId(this)" id="curSelBidLedgerId" name="bidLedgerId">
    <option value=""></option>
    <s:iterator value="listBidLedgers" var="bl" status="status">
    		<option value="${bidLedgerId}" 
		    	<s:if test = "bidLedger.bidLedgerId == bidLedgerId">
		    		selected="selected"
		    	</s:if>
    		>${bidSectionName}</option>
    	
    </s:iterator>
</select><font style="font-size: 14px; padding-left: 5px;">标段</font>
