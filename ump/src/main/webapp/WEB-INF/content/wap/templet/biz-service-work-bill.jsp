<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- 
	商业公司营运工作审批表(eg: 商业公司营运工作审批表) 
 --%>
<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<r:wapRow value="${templateBean.projectName}" title="项目名称"></r:wapRow>
		<r:wapCheckGroup title="审批内容">
			<r:wapCheck isCheck="${templateBean.typeCd1}" title="进退场"></r:wapCheck>
			<r:wapCheck isCheck="${templateBean.typeCd2}" title="停开业"></r:wapCheck>
			<r:wapCheck isCheck="${templateBean.typeCd3}" title="装修"></r:wapCheck>
			<r:wapCheck isCheck="${templateBean.typeCd4}" title="验收"></r:wapCheck>
			<r:wapCheck isCheck="${templateBean.typeCd5}" title="商户营业时间"></r:wapCheck>
			<r:wapCheck isCheck="${templateBean.typeCd6}" title="商户营业人员聘用"></r:wapCheck>
			<r:wapCheck isCheck="${templateBean.typeCd7}" title="商户货品价格"></r:wapCheck>
			<r:wapCheck isCheck="${templateBean.typeCd8}" title="商户进出货"></r:wapCheck>
			<r:wapCheck isCheck="${templateBean.typeCd9}" title="商户经营规范责任书"></r:wapCheck>
			<r:wapCheck isCheck="${templateBean.typeCd10}" title="其他"></r:wapCheck>
		</r:wapCheckGroup>
		<r:wapRow value="${templateBean.contentDesc}" title="内容简述(详细内容附后)"></r:wapRow>
</div>
