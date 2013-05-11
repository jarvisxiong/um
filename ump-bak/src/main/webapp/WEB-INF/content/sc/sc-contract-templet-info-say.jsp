<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>

<%@ include file="/common/meta.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%><script type="text/javascript"> 
function say(){
	var content= $("#comment").val();
	if($.trim(content)==''){
		$("#comment").css("border","1px solid red");
	}else{
		reloadContractMsg(content);
	}
	$('#idReplyDiv').html('');
	$('#idReplyDiv').hide();
	$('#idReplyMsg').val('');
}


function replayPerson(person,content,date,replayMsgId){
	var msg = '===============引用===============';
	msg += '<br/> ' + person + ' ' + date;
	msg += '<br/> ' + content ;
	$('#idReplyDiv').html(msg);
	$('#idReplyDiv').show();
	$('#idReplyMsg').val(replayMsgId);
	$("#comment").focus();
}

</script>

<style type="text/css">
<!--
.shared_msg div{
	background-color:#e1f1f9;
}
.shared_msg{
	background-color:#e1f1f9;
}
.pre{
 word-wrap: break-word; word-break: normal;width:100%;word-break:break-all;text-overflow:ellipsis;width:300px
}
-->
</style>
		<div style="padding:0px 0px;height:110px;overflow-y:scroll;" id="conMsgDiv">
			<table class="msgTable">
			<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
				<s:hidden id="isShare" name="isShare"/>
			
					<s:set var="canSee" value="true"/>
					<s:set var="isPrivateMsg" value="false"/>
					<s:if test="toUserCds!=null&&toUserCds!=''">
						<s:set var="isPrivateMsg" value="true"/>
						<s:set var="canSee" value="false"/>
						<c:if test="${fn:contains(toUserCds, curUserCd)}">
							<s:set var="canSee" value="true"/>
						</c:if>
						<c:if test="${creator==curUserCd}">
							<s:set var="canSee" value="true"/>
						</c:if>
						<security:authorize ifAnyGranted="A_QZSP_ADMIN,A_QZSP_MSG">
							<s:set var="canSee" value="true"/>
						</security:authorize>
					</s:if>
					<s:if test="canSee">
						<tr class="mainTr">
							<td  style="text-align:left">
														
								<s:iterator value="messages">
										<div class="divMessContent0">
											<div style="padding-left:2px;" class="pre" id="pre">
											<label  style="font-size:11px;color:#014769;">
											<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>(<s:date name="createdDate" format="MM-dd HH:mm"/>)
											</label>:
											<LABEL style="color:#333333;">
											<s:property value="msgContent" escape="true"/>
											</LABEL>
											</div>
											
										</div>
						      </s:iterator>
						
							</td>
					</s:if>
				
			</table>
		</div>
		<div style="width:100%;padding:0px 0px;">
			<table class="showTable" id="showTable" style="width:100%;table-layout: auto;">		
				<tr style="display:none">
					<td colspan="2">
						<div id="idReplyDiv" style="padding-left:10px;padding-top:10px;background-color:#ffffee;display:none;white-space:pre-wrap;"></div>
						<input type="hidden" id="idReplyMsg" name="replyMsgId"></input>
					</td>
				</tr>
				<tr class="detailTr">
					<td style="padding:10px;">
						<s:textarea cssStyle="width:100%;height:51px; border: 1px solid #AAC0D5;" name="comment" id="comment" />	
					</td>
					<td style="width:130px;">	
						<input type="button" value="发言" class="btn_blue_55_55" style="border:none;" onclick="say();" />
				
			      	<input type="button" value="收起" class="btn_blue_55_55" style="border:none;" onclick="doCollapse()" />
					</td>
				</tr>
			</table>
		</div>
