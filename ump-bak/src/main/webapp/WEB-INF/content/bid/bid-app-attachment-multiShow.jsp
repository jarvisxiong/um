<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>


    <s:iterator value="attachFiles">
    			
    			<s:url id="down" action="download" namespace="/app">
			  	  <s:param name="fileName">${fileName}</s:param>
			  	  <s:param name="realFileName">${realFileName}</s:param>
			  	  <s:param name="bizModuleCd">${bizModuleCd}</s:param>
			  	  <s:param name="filterType">${filterType}</s:param>
			  	  <s:param name="id">${appAttachFileId}</s:param>
			  	  <s:param name="fieldName">${fieldName}</s:param>
				</s:url>
				
			   <div attaId="${appAttachFileId}" style="border:none; float:left; clear:none"> &nbsp;
				<a href="${down}">${realFileName}</a>
				<s:if test="canEdit==true">
				<font onclick="deleteFile('${appAttachFileId}','${domId}','${bizEntityId}','BidLedgAttaRel');" style='color:red;font-weight:bold;cursor:pointer;'>×</font>
				</s:if>
			   </div>
				
    </s:iterator>
