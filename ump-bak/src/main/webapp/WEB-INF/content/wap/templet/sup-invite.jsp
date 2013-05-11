<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--招标公告意向供应商入库邀请-->

<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
	</s:set>
	<div class="div_row">
		<span class="wap_title">标题:</span>
		<span class="wap_value">${templateBean.inviteTitle}</span>
	</div>  
	<div class="div_row">
		<span class="wap_title">招标范围:</span>
		<span class="wap_value">${templateBean.inviteScope}</span>
	</div>  
	<div class="div_row">
		<span class="wap_title">招标项目:</span>
		<span class="wap_value">${templateBean.inviteProject}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">标段面积:</span>
		<span class="wap_value">${templateBean.bidArea}</span>
	</div>  
	<div class="div_row">
		<span class="wap_title">计价模式:</span>
		<span class="wap_value">${templateBean.valuationModule}</span>
	</div> 
	<div class="div_row">
		<span class="wap_title">施工工期:</span>
		<span class="wap_value">${templateBean.beginDate}</span>
		<span class="wap_title">到</span>
		<span class="wap_value">${templateBean.endDate}</span>
	</div>
	<s:hidden id="isEdit" value="true"/>
	<s:iterator value="templateBean.otherProperties" var="item" status="s">
	  <input type="hidden" id="deleteBl<s:property value="#s.index"/>" edit='' name="templateBean.otherProperties[<s:property value="#s.index" />].deleteBl" value="${item.deleteBl}"/>
		<div>
			<div class="div_label">
			   <span class="wap_title">意向投标单位</span>
			</div>
			<div class="div_row">
				<span class="wap_title">意向投标公司:</span>
				<span class="wap_value" <c:if test="${item.deleteBl=='1'}">style="color:#FF0000;text-decoration:line-through"</c:if>>${item.signProject}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">注册资金（万）:</span>
				<span class="wap_value" <c:if test="${item.deleteBl=='1'}">style="color:#FF0000;text-decoration:line-through"</c:if>>${item.signMoney}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">联系人:</span>
				<span class="wap_value" <c:if test="${item.deleteBl=='1'}">style="color:#FF0000;text-decoration:line-through"</c:if>>${titem.signPeople}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">联系电话:</span>
				<span class="wap_value" <c:if test="${item.deleteBl=='1'}">style="color:#FF0000;text-decoration:line-through"</c:if>>${item.signPhone}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">电子邮件:</span>
				<span class="wap_value" <c:if test="${item.deleteBl=='1'}">style="color:#FF0000;text-decoration:line-through"</c:if>>${item.signMail}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">备注:</span>
				<span class="wap_value" <c:if test="${item.deleteBl=='1'}">style="color:#FF0000;text-decoration:line-through"</c:if>>${item.remark}</span>
			</div>
			<div>
			<s:if test="#curNodeCd==89">
			  <a href="javascript:void(0)" onclick="delItem(this,'<s:property value="#s.index" />')"><img title="删除" src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
			</s:if>
			</div>
		</div>
	</s:iterator>
</div>							
<script type="text/javascript">
/**
*删除当前投标意向公司
*/
function delItem(dom,bl){
	var deleteBl=$("#deleteBl"+bl).val();
	if("1"!=deleteBl){
		 $(dom).parent().parent().find(".wap_value").each(function(i){
				$(this).css("color","#FF0000");
				$(this).css("text-decoration","line-through"); 
			});
			$("#deleteBl"+bl).val("1");
			$("#deleteBl"+bl).attr("edit","true");
	}else{
		 $(dom).parent().parent().find(".wap_value").each(function(i){
				$(this).removeAttr("style");
			});
			$("#deleteBl"+bl).val("0");
			$("#deleteBl"+bl).attr("edit","");
	}
	
}
</script>
	