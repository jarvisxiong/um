<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--服务器共享文件权限申请单-->
<%@ include file="template-var.jsp"%>
<div id="billContent">
	<div class="div_row">
			<span class="wap_title">所属中心:</span>
			<span class="wap_value">${templateBean.applyCenterOrgName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">申请人:</span>
			<span class="wap_value">${templateBean.applyUserName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">日期:</span>
			<span class="wap_value">${templateBean.applyDate}</span>
	</div>
	
	<div class="div_label">
		<span class="wap_label">【共享文件列表】</span>
		<div class="div_row">
			<span class="wap_title">文件夹名称:</span>
			<span class="wap_value">${templateBean.fileName1}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">权限:</span>
			<div><s:checkbox name="templateBean.selectFileName1Read" cssClass="group"></s:checkbox><span>只读 </span></div>
			<div><s:checkbox name="templateBean.selectFileName1Write" cssClass="group"></s:checkbox><span>读写 </span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">文件夹名称:</span>
			<span class="wap_value">${templateBean.fileName2}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">权限:</span>
			<div><s:checkbox name="templateBean.selectFileName2Read" cssClass="group"></s:checkbox><span>只读 </span></div>
			<div><s:checkbox name="templateBean.selectFileName2Write" cssClass="group"></s:checkbox><span>读写 </span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">文件夹名称:</span>
			<span class="wap_value">${templateBean.fileName3}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">权限:</span>
			<div><s:checkbox name="templateBean.selectFileName3Read" cssClass="group"></s:checkbox><span>只读 </span></div>
			<div><s:checkbox name="templateBean.selectFileName3Write" cssClass="group"></s:checkbox><span>读写 </span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">文件夹名称:</span>
			<span class="wap_value">${templateBean.fileName4}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">权限:</span>
			<div><s:checkbox name="templateBean.selectFileName4Read" cssClass="group"></s:checkbox><span>只读 </span></div>
			<div><s:checkbox name="templateBean.selectFileName4Write" cssClass="group"></s:checkbox><span>读写 </span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">文件夹名称:</span>
			<span class="wap_value">${templateBean.fileName5}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">权限:</span>
			<div><s:checkbox name="templateBean.selectFileName5Read" cssClass="group"></s:checkbox><span>只读 </span></div>
			<div><s:checkbox name="templateBean.selectFileName5Write" cssClass="group"></s:checkbox><span>读写 </span></div>
		</div>
	</div>
	<div class="div_row">
			<span class="wap_title">申请理由:</span>
			<span class="wap_value">${templateBean.contentDesc}</span>
	</div>
</div>