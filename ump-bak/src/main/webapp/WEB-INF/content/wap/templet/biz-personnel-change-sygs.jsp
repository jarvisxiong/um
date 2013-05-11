<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--人事变动表(商业公司)-->
<div id="billContent">
	
		<%@ include file="template-var.jsp"%>
		<r:wapCheckGroup title="选择">
			<r:wapCheck isCheck="${templateBean.positionLevel1}" title="总经理"/>
			<r:wapCheck isCheck="${templateBean.positionLevel2}" title="副总经理"/>
			<r:wapCheck isCheck="${templateBean.positionLevel3}" title="高级经理级(除垂直管理人员外)及以上"/>
			<r:wapCheck isCheck="${templateBean.positionLevel4}" title="双线管理部门负责人(财务、成本)"/>
			<r:wapCheck isCheck="${templateBean.positionLevel7}" title="双线管理部门负责人(人资)"/>
			<r:wapCheck isCheck="${templateBean.positionLevel5}" title="其他人员"/>
		</r:wapCheckGroup>
		<r:wapCheckGroup title="类别">
			<r:wapCheck isCheck="${templateBean.changeType1}" title="薪资、岗位确定及调整"/>
			<r:wapCheck isCheck="${templateBean.changeType2}" title="仅政策内补贴申请"/>
		</r:wapCheckGroup>
		<%@ include file="biz-personnel-change-base.jsp"%>
</div>
