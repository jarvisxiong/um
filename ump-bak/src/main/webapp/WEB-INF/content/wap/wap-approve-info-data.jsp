<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<%@page import="com.hhz.ump.util.DictContants"%>
	
<tr id="main_${resApproveInfoId}" style="cursor: pointer;" class="mainTr"  onclick="openDetail('${resApproveInfoId}','${page.pageNo}');">
	
	<td class="pd-chill-tip" id="templateLandProject">
		<div>
		<s:set var="timeReduce" value="%{getTimeReduce(timeLimit,lastApproveDate)}"></s:set>
			<s:if test="#timeReduce!=null">
			<s:if test="#timeReduce>0 && #timeReduce<=3"><span><img src="${ctx}/resources/images/res/nDelay.gif"/></span></s:if>
			<s:elseif test="#timeReduce<=0"><span><img src="${ctx}/resources/images/res/delay.gif"/></span></s:elseif>
			</s:if>
		<span style="color:#000000;font-size:24px;"><%=CodeNameUtil.getResAuthTypeNameByCd(JspUtil.findString("authTypeCd")) %></span>
		&nbsp;&nbsp;
		<span>
		<%-- 开始时间或完成时间 --%>
		<s:if test="listMode==1">
			<s:date name="lastApproveDate" format="MM/dd"/>
		</s:if>
		<s:else>
			<s:if test="filter_LIKES_statusCd == 2">
			<s:date name="completeDate" format="MM/dd"/>
			</s:if>
			<s:else>
			<s:date name="lastApproveDate" format="MM/dd"/>
			</s:else>
		</s:else>
		</span>	
		</div>
		<div>
		<span style="color:#aaaaaa ">${landProject}-</span><span style="color:#1ba0e1 ">${userName}</span><c:if test="${not empty titleName}"><span style="color:#aaaaaa ">-【${titleName}】</span></c:if>
		</div>
	</td>
	
</tr>
