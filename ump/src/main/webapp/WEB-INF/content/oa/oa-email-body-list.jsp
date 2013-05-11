<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%><div id="emailSearchDiv" class="emailSearchDiv">
<form action="${ctx}/oa/oa-email-body!list.action" method="post" id="mailAdminSearchForm">
	<table cellpadding="1" border="0" cellspacing="0" >
		<tr>
			<td id="creatorText" align="center" width="50px">发件人</td>
			<td id="creatorValue">
				<s:textfield onkeyup="showSearchUser(this)" id="creatorNameId" ></s:textfield>
				<input type="hidden" name="filter_LIKES_creator" id="creatorCdId"/>
			</td>
			<td align="left" style="padding-left:8px;">日期</td>
			<td><s:textfield id="filter_GED_sendTime" name="filter_GED_sendTime" onfocus="WdatePicker()" size="20" /></td>
			<td align="center">到</td>
			<td><s:textfield id="filter_LED_sendTime" name="filter_LED_sendTime" onfocus="WdatePicker()" size="20" /></td>
			
		</tr>
		<tr>
			<td width="50px" align="center">主题</td>
			<td colspan="5"><s:textfield name="filter_LIKES_subject" cssStyle="width:350px"></s:textfield></td>
			
			<td colspan="2" align="right" style="padding-left: 10px;">
				<security:authorize ifAnyGranted="A_EMAIL_AMDIN_READ">
					<div class="btn_green_55_20" id="emailSearchBtn" onclick="searchEmailAdmin('A_EMAIL_AMDIN_READ');">搜索</div>
				</security:authorize>
				<security:authorize ifAnyGranted="A_EMAIL_AMDIN_DEL">
					<div class="btn_green_55_20" id="emailSearchBtn" onclick="searchEmailAdmin('A_EMAIL_AMDIN_DEL');">搜索</div>
				</security:authorize>
			</td>
		</tr>
	</table>
</form>
</div>
<div id="searchResult">
<div class="mailDivBorder">
<div class="mailTableDiv">
    <table id="mailBoxTable" class="dataTable" cellpadding="0" cellspacing="0">
      <thead>
		<tr>
			<td width="90">发件人</td>
			<td >主题</td>
			<td width="40">附件</td>
			<td width="80">时间</td>
			<td width="100">操作</td>
		</tr>
	  </thead>
	<s:if test="page.result.size()>0">
      <tbody>
      <s:iterator value="page.result">
       <tr class="mainTr" id="info_${oaEmailBodyId}" isLoaded=false  oaEmailId="${oaEmailId}" oaEmailBodyId="${oaEmailBodyId}" boxId="${boxId}">
          <% String emailUserName = CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")); %>
          <td align="left"  click2expand=true class="ellipsisDiv" title="<%=emailUserName %>" >
         	 <%=emailUserName %>
          </td>
          <td align="left" click2expand=true>
          	<div class="ellipsisDiv" style="float:left;">
	          	<s:if test="importLevelCd == 2">
		          	<span style="color:#FF6600">[重要]</span>
	          	</s:if>
	          	<s:elseif test="importLevelCd == 3">
		          	<span style="color:#FF0000">[非常重要]</span>
	          	</s:elseif>
	          	<span title="${subject}">
			          ${subject}
		        </span>
	        </div>
          </td>
          <td align="left" click2expand=true>
          	&nbsp;
	          <s:if test="uploadFlg == 1">
	           	<img  title="该含有附件" src="${ctx}/pics/email/atta_y.gif">
	           </s:if>
          </td>
          <td align="left" click2expand=true title="<s:date name='sendTime' format='yyyy-MM-dd HH:mm'></s:date>">
          	<s:date name="sendTime" format="MM-dd HH:mm"></s:date>
          </td>
          <td >
          		<security:authorize ifAnyGranted="A_EMAIL_AMDIN_READ">
          			<span onclick="readMail('','${oaEmailBodyId}',2,'edit');">编辑</span>
          		</security:authorize>
        		<span onclick="deleteEmailAdmin('${oaEmailBodyId}');">删除</span>
          </td>
        </tr>
        <tr class="detailTr" id="detail_${oaEmailBodyId}" style="display: none;" title="双击关闭预览">
        	<td colspan="5">
        	</td>
        </tr>
      </s:iterator>
      </tbody>
      </s:if>
      </table>
    <s:if test="page.result.size()==0">
	<div class="noResult">没有相关记录！</div>
	</s:if>
</div>
</div>
</div>
<div id="searchUserDiv" class="searchUserDiv">
</div>
<script language="javascript">
	var searchTime;
	function showSearchUser(dom){
		if(searchTime)clearTimeout(searchTime);
		searchTime = setTimeout(function(){
			$("#creatorCdId").val("");
			var val = $.trim($(dom).val());
			$(dom).next().val("");
			if(val == ""){
				$("#searchUserDiv").slideUp(200);
				return;
			}
			
			$.post("${ctx}/oa/oa-email!getUsersByFilter.action",{value:val,maxNum:10},function(result){
				var $offset = $(dom).offset();
				$("#searchUserDiv").css({left:$offset.left,top:$offset.top+$(dom).height()}).empty().show();
				result = eval(result);
				$.each(result,function(i,n){
					var html = "<div uiid='"+n.uiid+"'><span>"+n.userName +"</span>|<span>"+ n.orgName+"</span></div>";
					$("#searchUserDiv").append(html);
				});
				$("#searchUserDiv div").click(function(){
					var userName = $(this).children("span:first").text();
					var uiid = $(this).attr("uiid");
					$(dom).val(userName);
					$(dom).next().val(uiid);
					$("#searchUserDiv").slideUp(200);
				});
			});
		}, 300);
	}
	function resetSearchForm(){
		$("#mailSearchForm").reset();
	}
	 
	$(document).click(function(event){
		var event  = window.event || event;
	    var obj = event.srcElement ? event.srcElement : event.target;
	    if(obj!=$("#creatorNameId")[0] && obj!=$("#searchResult")[0] ){
	    	$("#searchUserDiv").slideUp(200);
			if($("#creatorCdId").val()==""){
				$("#creatorNameId").val("");
			};
	    }
	});

</script>