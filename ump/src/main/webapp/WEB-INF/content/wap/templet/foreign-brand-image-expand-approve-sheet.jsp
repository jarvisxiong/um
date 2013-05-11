<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 对外品牌形象推广审批表 --%>
<div class="billContent">
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<span class="wap_title">标题:</span>
		<span class="wap_value">${templateBean.title}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">单位/部门:</span>
		<span class="wap_value">${templateBean.units}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">推广类型:</span>
		<div><s:checkbox name="templateBean.delayMonth1" cssClass="group"></s:checkbox><span>参加论坛</span></div>
		<div><s:checkbox name="templateBean.delayMonth2" cssClass="group"></s:checkbox><span>申报奖项</span></div>
		<div><s:checkbox name="templateBean.delayMonth3" cssClass="group"></s:checkbox><span>媒体采访</span></div>
	</div>
	<div class="div_row">
		<span class="wap_title">费用:</span>
		<span class="wap_value">${templateBean.cost}</span>
	</div>
	
	<!-- 论坛/奖项情况 -->
	<div class="div_row">
		<span class="wap_title">论坛/奖项情况</span>
	</div>
	<div class="div_row">
		<span class="wap_title">日期:</span>
		<span class="wap_value">${templateBean.date}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">地点:</span>
		<span class="wap_value">${templateBean.place}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">主办方:</span>
		<span class="wap_value">${templateBean.orgnizer}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">出席人:</span>
		<span class="wap_value">${templateBean.attendee}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">活动介绍:</span>
		<span class="wap_value">${templateBean.activityIntroduce}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">奖项名称:</span>
		<span class="wap_value">${templateBean.awardsName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">获奖对象:</span>
		<span class="wap_value">${templateBean.awardsTarget}</span>
	</div>
	
	<!-- 媒体采访情况 -->
	<div class="div_row">
		<span class="wap_title">媒体采访情况</span>
	</div>
	<div class="div_row">
		<span class="wap_title">媒体名称:</span>
		<span class="wap_value">${templateBean.mediaName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">采访对象:</span>
		<span class="wap_value">${templateBean.coverTarget}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">刊登栏目:</span>
		<span class="wap_value">${templateBean.publishColumn}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">采访形式:</span>
		<div><s:checkbox name="templateBean.covertMode1" cssClass="group"></s:checkbox><span>面访</span></div>
		<div><s:checkbox name="templateBean.covertMode2" cssClass="group"></s:checkbox><span>电话</span></div>
		<div><s:checkbox name="templateBean.covertMode3" cssClass="group"></s:checkbox><span>邮件</span></div>
	</div>
	<div class="div_row">
		<span class="wap_title">媒体联系人:</span>
		<span class="wap_value">${templateBean.mediaLinkMan}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">职务:</span>
		<span class="wap_value">${templateBean.position}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">手机:</span>
		<span class="wap_value">${templateBean.phone}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">采访日期:</span>
		<span class="wap_value">${templateBean.coverDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">座机:</span>
		<span class="wap_value">${templateBean.telephone}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">采访耗时:</span>
		<span class="wap_value">${templateBean.coverTime}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">Email:</span>
		<span class="wap_value">${templateBean.email}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">刊登日期:</span>
		<span class="wap_value">${templateBean.publishDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">采访主题:</span>
		<span class="wap_value">${templateBean.coverSubject}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">采访提纲:</span>
		<span class="wap_value">${templateBean.coverOutLine}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">拍摄需求:</span>
		<div><s:checkbox name="templateBean.shootNeed1" cssClass="group"></s:checkbox><span>有</span></div>
		<div><s:checkbox name="templateBean.shootNeed2" cssClass="group"></s:checkbox><span>无</span></div>
	</div>
	<div class="div_row">
		<span class="wap_title">媒体介绍:</span>
		<span class="wap_value">${templateBean.mediaIntroduce}</span>
	</div>
</div>