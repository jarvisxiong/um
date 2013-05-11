<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%--是否拥有审核权限 --%>
<s:set var="isApprove" value="1"/>
<security:authorize ifAnyGranted="A_CONT_APPROVE">
	<s:set var="isApprove" value="1"/>
</security:authorize>
<%--是否拥有合同完成权限 --%>
<s:set var="isPrimissionCompl" value="0"/>
<security:authorize ifAnyGranted="A_CONT_STAN_COMPL">
	<s:set var="isPrimissionCompl" value="1"/>
	<%--是否拥有管理员权限 --%>
	<s:set var="isPrimissionAdmin" value="0"/>
	<security:authorize ifAnyGranted="A_CONT_MOULD_ADMIN">
		<s:set var="isPrimissionAdmin" value="1"/>
	</security:authorize>
</security:authorize>

<input type="button" name="bt_attachUpload" id="bt_attachUpload" value="上传附件" class="btn_blue"  onclick="showUploadConAttachDialog('上传合同附件','','')"/>
<%--10新增中、20审核中、30进行中、40审核通过、45被驳回 --%>
<s:if test="conTempletInfoEntity.statusCd==10||conTempletInfoEntity.statusCd==20||conTempletInfoEntity.statusCd==30||conTempletInfoEntity.statusCd==40||conTempletInfoEntity.statusCd==45">
<!-- 非审核人、创建人、合同负责人只能查看历史版本、修订版 -->
	<s:if test="scContractParams.isOnlySee != 1">
		<span class="hideIfDel">
		<input class="btn_blue" type="button" id="bt_submit" onclick="updateScStatus('保存',30);" value="保存" />
		</span>
		<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
		<span class="hideIfDel">
		<input class="btn_red" type="button" onclick="doDelete();" id="btDel" value="删除"/>
		</span>
	</s:if>
	<s:else>
		<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
	</s:else>
	<!-- 不过是不需要合同评审表的合同类别， 财务可直接结束合同  -->
    <s:if test="(#isPrimissionCompl == 1 && conTempletInfoEntity.scContractTemplet.scContractTempletType.ifNotNeedRes==true) || #isPrimissionAdmin == 1">
		<span class="hideIfDel">
		<input type="button" name="finish" id="bt_finish" value="合同完成" onclick="updateScStatus('要完成当前合同',70);" style="width:100px" class="btn_red" /> 
		</span>
	</s:if>
</s:if>
<%--50网批中--%>
<s:elseif test="conTempletInfoEntity.statusCd==50">
	<s:if test="scContractParams.isOnlySee != 1">
		<%-- <input type="button" name="bt_revise" id="bt_revise" onclick="loadFlow('${conTempletInfoEntity.resApproveInfoId}');" value="流程查看" class="btn_green" /> --%>
	</s:if>
	<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
</s:elseif>
<%--60网批结束--%>
<s:elseif test="conTempletInfoEntity.statusCd==60">
	<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
    <s:if test="#isPrimissionCompl == 1">
    	<input type="button" name="finish" id="bt_finish" value="合同完成" onclick="updateScStatus('${conTempletInfoEntity.contractTempletInfoId}','70');" class="btn_red" /> 
    </s:if>
</s:elseif>
<%--70合同完成 --%>
<s:elseif test="conTempletInfoEntity.statusCd==70">
	<s:if test="scContractParams.isOnlySee != 1">
		<input type="button" name="finish" id="bt_finish" value="合同签署" onclick="updateScStatus('已签署当前合同','80');" class="btn_red" /> 
	</s:if>
</s:elseif>
<%--80合同签署 --%>
<s:elseif test="conTempletInfoEntity.statusCd==80">
	<input type="button" name="bt_revise" id="bt_flow" onclick="showFlow();" value="历史版本" class="btn_green" />
	<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
</s:elseif>
<s:else>
	<!-- 非审核人、创建人、合同负责人只能查看历史版本、修订版 -->
	<s:if test="scContractParams.isOnlySee != 1">
		<input  class="btn_blue" type="button" id="bt_submit" onclick="updateScStatus('保存',30);" value="保存" />
		<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
		<input class="btn_red" type="button" onclick="doDelete();" id="btDel"  style="display:none" value="删除"/>
	</s:if>
	<s:else>
		<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
	</s:else>
</s:else>

<script type="text/javascript">
// 如果当前合同文本记录为删除状态(1:删除)，隐藏功能按钮
try{
	if($("#isDel").val() == "1") {
		$(".hideIfDel").hide();
	}
}catch(e){}
</script>
