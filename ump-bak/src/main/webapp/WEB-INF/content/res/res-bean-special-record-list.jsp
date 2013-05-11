<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<table style="width:100%;height:100%;" cellpadding="0px" cellspacing="0px">
	<thead>
		<tr class="headTR">
			<th class="th_first">申请机构</th>
			<th class="th">申请人</th>
			<th style="text-align: center;" class="th">批准额度（元）</th>
			<th style="text-align: center;" class="th" width="100">批准时间</th>
			<th style="text-align: center;" class="th" width="60">是否考核</th>
			<th class="th" width="100">状态</th>
			<th class="th" width="150">操作</th>
		</tr>
	</thead>
	<tbody id="recordTableBody">
		<s:iterator value="page.result" status="s">
		<tr class="bodyTR">
			<td class="td">${applyOrgName}</td>
			<td class="td">${userName}</td>
			<td style="text-align:  right;" class="td money pd-chill-tip">${approveMoney}</td>
			<td style="text-align: center;" class="td" title="${approveDate}" ><s:date name="approveDate" format="yy-MM-dd"/></td>
			<td style="text-align: center;" class="td"><s:if test="1 == checkFlg">是</s:if><s:else>否</s:else></td>
			<td class="td">
				<%=CodeNameUtil.getDictNameByCd(DictContants.RES_SPECIAL_RECORD_STATUS, JspUtil.findString("statusCd")) %>
			</td>
			<td class="td">
		
				 <s:if test="payApproveInfoId != null">
					<a href="javascript: showLink('${payApproveInfoId }','查看付款申请')" title="点击查看付款申请">付款申请</a>
				 </s:if>
				 
				<security:authorize ifAnyGranted="A_RES_SPEC_ADMIN">
					<%--
					<s:if test="statusCd==1">
						<input type="button" onclick="confirmRecord('${resBeanSpecialRecordId}');" class="btn btn_blue" value="确认付款"/>
					</s:if>
					 <s:if test="resApproveInfoId != null">
					 	<a href="javascript: showLink('${resApproveInfoId }','查看特别申请')" title="点击查看特别申请">特别申请</a>
					 </s:if>
					 --%>

					<s:if test="statusCd!=2">
						<input type="button" style="float:right;" onclick="deleteId(this,'${resBeanSpecialRecordId}');" class="btn btn_red" value="删除"/>
					</s:if>
				</security:authorize>
			</td>
		</tr>
		</s:iterator>
	</tbody>
</table>

<div class="table_pager" style="margin-top:5px;">
	<p:page pageInfo="page"/>
</div>

<script type="text/javascript">
	$(function(){
		//格式化金额
		$('#recordTableBody td.money').each(function(){
			var money = $(this).html();
			if($.trim(money) == ''){
				money = '0';
			}
			var fm = formatNumberic(money);
			$(this).attr('title','大写：'+i2c(fm));
			$(this).html(formatMoney(fm));
			/*
			$(this).bind('hover',function(){
				$(this).html($(this).attr(title));
			});
			*/
		});
	});
</script>