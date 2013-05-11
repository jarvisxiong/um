<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--成本月招采计划(商业公司)-->
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<!-- 列表 -->
	<s:iterator value="templateBean.planProperties" var="item" status="s">
	<r:wapRowLabel title="${item.projectName}">
	<r:wapRow value="${item.projectName}" title="商业公司"></r:wapRow>
	<r:wapRow value="${item.actionSubject}" title="活动主题"></r:wapRow>
	<r:wapRow value="${item.actionDate}" title="活动时间"></r:wapRow>
	<r:wapRow value="${item.actionPlace}" title="活动地点"></r:wapRow>
	<r:wapCheckGroup title="活动组织">
		<r:wapCheck isCheck="${item.typeCd1}" title="宝龙自行组织"></r:wapCheck>
		<r:wapCheck isCheck="${item.typeCd2}" title="联合商户组织"></r:wapCheck>
		<r:wapCheck isCheck="${item.typeCd3}" title="其他"></r:wapCheck>
	</r:wapCheckGroup>
	<r:wapRow value="${item.typeCd2Info}" title="参与商户"/>
	<r:wapRow value="${item.typeCd3Info}" title="其他"/>
	<r:wapRow value="${item.totalPrice}" title="预计金额(元)"/>
	<r:wapRow value="${item.remark}" title="活动内容"/>
	<r:wapRowLabel title="附件">
		<r:wapFile fileId="lxfaFileId" title="活动方案说明" fileValue="${item.lxfaFileId}" index="${s.index}"/>
		<r:wapFile fileId="ysfyFileId" title="预算费用审批表" fileValue="${item.ysfyFileId}" index="${s.index}"/>
		<r:wapFile fileId="zbfaFileId" title="活动组织分工及费用明细" fileValue="${item.zbfaFileId}" index="${s.index}"/>
	</r:wapRowLabel>
	</r:wapRowLabel>
	</s:iterator>
</div>

