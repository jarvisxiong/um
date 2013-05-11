<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--人事变动表(宝龙商业)-->
<div id="billContent">
	
		<%@ include file="template-var.jsp"%>
		<r:wapCheckGroup title="选择">
			<r:wapCheck isCheck="${templateBean.positionLevel1}" title="中心总经理及以上人员"/>
			<r:wapCheck isCheck="${templateBean.positionLevel2}" title="中心副总经理"/>
			<r:wapCheck isCheck="${templateBean.positionLevel4}" title="双线管理人员(财务)"/>
			<r:wapCheck isCheck="${templateBean.positionLevel3}" title="其他"/>
		</r:wapCheckGroup>
		<r:wapCheckGroup title="类别">
			<r:wapCheck isCheck="${templateBean.changeType1}" title="薪资、岗位确定及调整"/>
			<r:wapCheck isCheck="${templateBean.changeType2}" title="仅政策内补贴申请"/>
		</r:wapCheckGroup>
		<%@ include file="biz-personnel-change-base.jsp"%>
</div>
