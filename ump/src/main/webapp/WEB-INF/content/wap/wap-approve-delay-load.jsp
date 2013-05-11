<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="${ctx}/resources/js/jquery/jQuery.artTxtCount.js"></script>
	<div class="list_header2"><span>延期申请</span></div>
	<s:if test="appliedFlg == 1">
			 申请时间:<s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss"/>
			 申请延期:<s:if test="delayDay != 0">${delayDay}天</s:if>${delayHour}小时
			<br/>
			 发起人:${applyUserName}
			<s:if test="approveUserCd != null && approveUserCd != ''">
				<s:if test="approveOptionCd == null">
					-> [<%= CodeNameUtil.getUserNameByCd(JspUtil.findString("approveUserCd")) %>] 未处理
					<s:if test="approveUserCd == curUserCd">
						<s:if test="approveOptionCd == null ">
							<input class="btn_green" type="button" value="同意延期" style="width:70px;" onclick="onChiefAgree('${resApproveDelayId}');"/>
							<input class="btn_red" type="button" value="拒绝延期" style="width:70px;" onclick="onChiefDegree('${resApproveDelayId}');"/>
						</s:if> 
					</s:if>
				</s:if>
				<s:else>
					-> [<%= CodeNameUtil.getUserNameByCd(JspUtil.findString("approveUserCd")) %>] 已处理
				</s:else>
			</s:if>
			
			<div style="word-wrap:break-word;">
				申请事由： ${applyReason}
			</div>
	</s:if>
