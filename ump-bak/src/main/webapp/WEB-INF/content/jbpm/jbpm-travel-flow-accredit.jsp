<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<script type="text/javascript">
	function listAttendee() {
		var type = "${attendeeType}";
		$.each(attendee, function(i, n) {
			bindAttendeeEvents(n, "userDisplay", type);
		});
	}
</script>
<span class="bizEntityId" style="display: none;"><s:text name="bizEntityId" /></span>
<div style="text-align: center; margin-top: 10px;">
	<div style="overflow: auto; width: 180px; margin: 0 auto; padding: 1px; border: 1px solid #dadada; text-align: center; background-color: #e7e7e7;">
		<div style="height: 3px; overflow: hidden; background-color: #5a6a82"></div>
		<div id="userRight">
			<div style="margin: 0 auto; height: 320px; text-align: left;">
				<div style="height: 20px; line-height: 20px; border-bottom: 1px solid #FFF;">
					请选择用户：
				</div>
				<ul id="userDisplay">
					<s:if test="accreditInfos.size == 0">
						<script type="text/javascript">
							listAttendee();
						</script>
					</s:if>
					<s:else>
						<s:iterator value="accreditInfos" id="userInfo">
							<li id="${userInfo.userCd}" class="userUnSelected">
								<div class='userStatus_online'>${userInfo.userName}</div>
							</li>
						</s:iterator>
					</s:else>
				</ul>
			</div>
		</div>
	</div>
</div>