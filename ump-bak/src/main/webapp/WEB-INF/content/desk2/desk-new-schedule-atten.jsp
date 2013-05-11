<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp" %>

<div style="margin-top:4px;margin-left:0px;border-bottom:0;">
			<div class="div_clear"></div>
			<div id="atten_1" class="content_left_page_selected" onclick="refreshOaAttention('1')">1</div>
			<div id="atten_2" class="content_left_page" onclick="refreshOaAttention('2')">2</div>
			<div id="atten_3" class="content_left_page" onclick="refreshOaAttention('3')">3</div>
			<div id="atten_all" class="content_left_page"  onclick="openPlanTarget();">...</div>
			
		</div>
<div>
<div id="atten"  style="float:left; width:100%;margin-top: 4px; padding: 3px 0px 7px 5px;cursor:pointer;">
   <s:iterator value="attenList" status="s">
   <div class="content_left" style="margin-bottom:8px;" title="${content}" onclick="openTask('1','${entityId}','','planTarget','${centerCd}');">
	   	<div>
	   	 <span class="font_title">${subContent}
	   	 </span>
	   	  <s:if test="newa==1"><img src="${ctx}/resources/images/common/new.gif" class="new_img"/></s:if>
	    </div>
	   	<div class="font_name" style="margin-top:2px;">${center}&nbsp;${date}</div>
	   	<div class="div_clear"></div>
   	</div>
   </s:iterator>
</div>
</div>