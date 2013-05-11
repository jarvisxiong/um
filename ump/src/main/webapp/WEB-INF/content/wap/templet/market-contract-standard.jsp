<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--营销合同杨标准版本审批表-->
<div id="billContent">
	
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
	  <span class="wap_title">项目名称:</span>
	  <span class="wap_value">${templateBean.projectName}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">文件名称:</span>
	  <span class="wap_value">${templateBean.fileName}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">文件编号:</span>
	  <span class="wap_value">${templateBean.fileNum}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">文件类别:</span>
	  <div><s:checkbox name="templateBean.subscribeBook" cssClass="group"></s:checkbox><span>预定书</span></div>
	  <div><s:checkbox name="templateBean.modelContract" cssClass="group"></s:checkbox><span>商品房买卖合同范本</span></div>
	  <div><s:checkbox name="templateBean.modelContractRide" cssClass="group"></s:checkbox><span>合同范本补充条款</span></div>
      <div><s:checkbox name="templateBean.makeRoomStandard" cssClass="group"></s:checkbox><span>交房装修标准</span></div>
	  <div><s:checkbox name="templateBean.marketContract" cssClass="group"></s:checkbox><span>营销合同范本</span></div>
    </div>
    <div class="div_row">
      <span class="wap_title">编制或修订:</span>
      <div><s:checkbox name="templateBean.plait" cssClass="group"></s:checkbox><span>编制</span></div>
	  <div><s:checkbox name="templateBean.revise" cssClass="group"></s:checkbox><span>开盘方案</span>
	  (第<span class="wap_value">${templateBean.reviseNum}</span>次修订)
	  </div>
    </div>
    <div class="div_row">
	  <span class="wap_title">标准版本合同主要条款:</span>
	  <span class="wap_value">${templateBean.mainContract}</span>
    </div>
</div>
