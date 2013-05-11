<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
		<div id="div_main">
			
		    <div id="tableDiv" style="overflow:auto;">
				<div class="titleBar" style="overflow:auto;" >
				<ul class="wap-cost-nav" id="cost-nav" style="float:left;">
					<s:iterator value="Opinions" status="u">
					<li id="div_${key}" <s:if test='%{key==selectOpinion}'>class="wap-cost-nav-click"</s:if>  onclick="seleOp('${key}',true);">
						&nbsp;${value}&nbsp;
					</li>
					<s:if test="!#u.last">
						<li style="border-left:1px solid #0167a2;">&nbsp;</li>
					</s:if>
					</s:iterator>
				</ul>
				</div>
				<jsp:include page="wap-approve-info-result.jsp"></jsp:include>
		    </div>
			
			</div> 
			<input type="hidden" id="wap_selectOpinion" value="${selectOpinion }" />
