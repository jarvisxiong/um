<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<s:set var="isApprove" value="1"/>
<security:authorize ifAnyGranted="A_CONT_APPROVE">
	<s:set var="isApprove" value="1"/>
</security:authorize>
<%--是否拥有合同完成权限 --%>
<s:set var="isPrimissionCompl" value="0"/>
<security:authorize ifAnyGranted="A_CONT_STAN_COMPL">
	<s:set var="isPrimissionCompl" value="1"/>
</security:authorize>
<%--是否拥有管理员权限 --%>
<s:set var="isPrimissionAdmin" value="0"/>
<security:authorize ifAnyGranted="A_CONT_MOULD_ADMIN">
	<s:set var="isPrimissionAdmin" value="1"/>
</security:authorize>
<input type="button" name="bt_attachUpload" id="bt_attachUpload" value="上传附件" class="btn_blue"  onclick="showUploadSingleAttachDialog('上传合同附件','','')"/>
<%--10新增中、20审核中、30进行中、40审核通过、45被驳回 --%>
<s:if test="conTempletInfoEntity.statusCd==10||conTempletInfoEntity.statusCd==20||conTempletInfoEntity.statusCd==30||conTempletInfoEntity.statusCd==40||conTempletInfoEntity.statusCd==45">
	<!-- 非审核人、创建人、合同负责人只能查看历史版本、修订版 -->
	<%--isOnlySee：若是合同负责人、合同审批人员则isonlySee值为0; 默认其值为1 --%>
	<s:if test="scContractParams.isOnlySee != 1">
		<span class="hideIfDel">
		<input  class="btn_blue" type="button" id="bt_submit" onclick="doScSubmit();" value="保存" />
		<!-- <input type="button" name="bt_merge" id="bt_merge" value="确定版" onclick="mergeCont()"  class="btn_blue"/> 
		<input type="button" name="bt_revise" id="bt_revise" onclick="reviseCont();" value="修订版" class="btn_blue" /> -->
		<input type="button" name="bt_print" id="bt_print" value="非正式打印" class="btn_green"   onclick="doScPrint()" style="width:120px;"/>
		</span>
		<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
		<span class="hideIfDel">
		<input class="btn_red" type="button" onclick="doDelete();" id="btDel" value="删除"/>
		</span>
	</s:if>
	<s:else>
		<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
	</s:else>
    <s:if test="(#isPrimissionCompl == 1 && conTempletInfoEntity.scContractTemplet.scContractTempletType.ifNotNeedRes==true) || #isPrimissionAdmin == 1">
 		<span class="hideIfDel">
    	<input type="button" name="finish" id="bt_finish" value="合同完成" onclick="updateScStatus('${conTempletInfoEntity.contractTempletInfoId}','70');" class="btn_red" style="width:80px" /> 
   		</span>
    </s:if>
</s:if>
<%--50网批中 --%>
<s:elseif test="conTempletInfoEntity.statusCd==50">
	<!-- <input type="button" name="bt_merge" id="bt_merge" value="确定版" onclick="mergeCont()"  class="btn_blue"/> 
	<input type="button" name="bt_revise" id="bt_revise" onclick="reviseCont();" value="修订版" class="btn_blue" /> -->
	<s:if test="scContractParams.isOnlySee != 1">
		<!-- <input type="button" name="btB" id="btShowMark" value="标记版" onclick="showBookMark();" class="btn_green" /> -->
		<input type="button" name="bt_print" id="bt_print" value="非正式打印" class="btn_green"   onclick="doScPrint()" style="width:100px;"/>
		<%-- <input type="button" name="bt_revise" id="bt_revise" onclick="loadFlow('${conTempletInfoEntity.resApproveInfoId}');" value="流程查看" class="btn_green" /> --%>
		<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
	</s:if>
	<s:else>
		<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
	</s:else>
</s:elseif>
<%--60网批结束--%>
<s:elseif test="conTempletInfoEntity.statusCd==60">
	<!-- <input type="button" name="bt_merge" id="bt_merge" value="确定版" onclick="mergeCont()"  class="btn_blue"/> 
	<input type="button" name="bt_revise" id="bt_revise" onclick="reviseCont();" value="修订版" class="btn_blue" /> -->
	<s:if test="scContractParams.isOnlySee != 1">
		<!-- <input type="button" name="btB" id="btShowMark" value="标记版" onclick="showBookMark();" class="btn_green" /> -->
		<input type="button" name="bt_print" id="bt_print" value="非正式打印" class="btn_green"   onclick="doScPrint()" style="width:100px;"//>
		<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
	</s:if>
	<s:else>
		<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
	</s:else>
	<s:if test="#isPrimissionCompl == 1">
		<input type="button" name="finish" id="bt_finish" value="合同完成" onclick="updateScStatus('${conTempletInfoEntity.contractTempletInfoId}','70');" class="btn_red" /> 
	</s:if>
</s:elseif>
<%--70合同完成 --%>
<s:elseif test="conTempletInfoEntity.statusCd==70">
	<!-- <input type="button" name="bt_revise" id="bt_revise" onclick="reviseCont();" value="修订版" class="btn_blue" /> -->
	<s:if test="scContractParams.isOnlySee != 1">
		<!-- <input type="button" name="bt_merge" id="bt_merge" value="确定版" onclick="mergeCont()"  class="btn_blue"/> -->
		<input type="button" name="bt_revise" id="bt_flow" onclick="showFlow();" value="历史版本" class="btn_green" />	
		<input type="button" name="bt_print" id="bt_print" value="打印" class="btn_green"   onclick="doScPrint()"/>
		<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
		<input type="button" name="finish" id="bt_finish" value="合同签署" onclick="doSignatureCon('${conTempletInfoEntity.contractTempletInfoId}','80');" class="btn_red" /> 
	</s:if>
	<s:else>
		<input type="button" name="bt_revise" id="bt_flow" onclick="showFlow();" value="历史版本" class="btn_green" />	
		<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
	</s:else>
	<s:if test="#isApprove == 1">
		<input type="button" name="finish" id="bt_finish" value="驳回" onclick="updateScStatus('${conTempletInfoEntity.contractTempletInfoId}','45')" class="btn_red" />
	</s:if>
</s:elseif>
<%--80合同签署 --%>
<s:elseif test="conTempletInfoEntity.statusCd==80">
	<!-- <input type="button" name="bt_revise" id="bt_revise" onclick="reviseCont();" value="修订版" class="btn_blue" /> -->
	<s:if test="scContractParams.isOnlySee != 1"> 
		<!-- <input type="button" name="bt_merge" id="bt_merge" value="确定版" onclick="mergeCont()"  class="btn_blue"/>  -->
	</s:if>
	<input type="button" name="bt_revise" id="bt_flow" onclick="showFlow();" value="历史版本" class="btn_green" />
</s:elseif>
<s:else>
	<input  class="btn_blue" type="button" id="bt_submit" onclick="doScSubmit();" value="保存" />
	<input class="btn_green" type="button" onclick="doReturn();" value="返回"/>
</s:else>

<script type="text/javascript">
// 如果当前合同文本记录为删除状态(1:删除)，隐藏功能按钮
if($("#isDel").val() == "1") {
	$(".hideIfDel").hide();
}
</script>
