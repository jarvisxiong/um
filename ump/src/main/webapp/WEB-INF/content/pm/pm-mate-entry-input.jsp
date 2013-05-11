<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/sup/sup.css" />
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
<style>
	#body .btns button {
	    float: left;
	    margin-right: 5px;
	}
	
	table.sup_table{
		border: 0 solid #8C8F94;
    	height: 100%;
    	width: 100%;
	}
	
</style>

<div id="body" class="publish" style="margin-left: 10px;">
	<div class="res_tip" style="margin-top:10px;">
		<span id="titleSpan" style="margin-left: 10px;font-size: 16px;font-weight: bold;">新增企划案例</span>
	</div>
	<div class="btns clearfix" style="margin-top: 10px;">
		<button type="button" class="green min" onclick="returnPmMateEntry();">返回</button>
		<security:authorize ifAnyGranted="A_PM_ENTRY_NEW,A_PM_ENTRY_EDIT">
			<button type="button" class="green min" onclick="savePmMateEntry();">保存</button>
		</security:authorize>
		<span id="operateTip" style="color:red;margin-left:30px;"></span>
	</div>
	<%-- 操作提示 --%>
	
	<div class="body_warp">
	    <div class="form_body" style="margin-top: 10px;margin-bottom: 10px;">
           <form action="${ctx}/pm/pm-mate-entry!save.action" id="pmMateEntryFrom" name="pmMateEntryFrom" method="post">
		          <table style="width:100%;">
		          	<tr>
		          		<td align="right" style="width:15%;"><label>企划案例编号：</label></td>
		          		<td colspan="3">
		          			<s:if test="null==pmMateEntryId">
		                   	<input type="text" style="width:88%;margin-left:5px;padding-right:10px;" class="text max required" name="entity.pmMateEntryNo" pmEntryNo_U="${entity.pmMateEntryNo }" id="pmMateEntryNo"  maxlength="50" />
		                   </s:if>
		                   <s:else>
		                   	<input type="text" style="width:88%;margin-left:5px;padding-right:10px;" class="text max required" name="entity.pmMateEntryNo" id="pmMateEntryNo"  maxlength="50" pmEntryNo_U="${entity.pmMateEntryNo }" value="${entity.pmMateEntryNo }"/>
		                   </s:else>
		          		</td>
		          	</tr>
		          	<tr>
		          		<s:if test="null==pmMateEntryId">
		          			<input type="hidden" name="id" id="pmEntryId"/>
		          			<input type="hidden" name="bizEntityTempId" id="bizEntityTempId"/>
		          			<input type="hidden" name="entity.pmMateEntryId" id="pmMateEntryId" value="${entity.pmMateEntryId }"/>
		       				<input type="hidden" name="entity.pmMateModu.pmMateModuId" id="pmMateModuId" value="${pmMateModuId }"/>
		       				<input type="hidden" name="fileSn_pic" id="fileSn_pic"/>
		       				<input type="hidden" id="flag" name="flag" value="${flag}"/>
			        	</s:if>
			        	<s:else>
			        		<input type="hidden" name="id" id="pmEntryId" value="${entity.pmMateEntryId }"/>
			        		<input type="hidden" name="bizEntityTempId" id="bizEntityTempId" value="${entity.pmMateEntryId }"/>
			        		<input type="hidden" name="entity.pmMateEntryId" id="pmMateEntryId" value="${entity.pmMateEntryId }"/>
			        		<input type="hidden" name="entity.pmMateModu.pmMateModuId" id="pmMateModuId" value="${entity.pmMateModu.pmMateModuId }"/>
			        		<input type="hidden" name="fileSn_pic" id="fileSn_pic"/>
			        		<input type="hidden" id="flag" name="flag" value="${flag}"/>
			        	</s:else>
		          		<td align="right" style="width:10%;"><label>活动主题：</label></td>
		          		<td colspan="3">
		          			<s:if test="null==pmMateEntryId">
		                   	<input type="text" style="width:88%;margin-left:5px;padding-right:10px;margin-top: 10px;" class="text max required" name="entity.activeTitle" id="activeTitle"  maxlength="50" />
		                   </s:if>
		                   <s:else>
		                   	<input type="text" style="width:88%;margin-left:5px;padding-right:10px;margin-top: 10px;" class="text max required" name="entity.activeTitle" id="activeTitle"  maxlength="50" value="${entity.activeTitle }"/>
		                   </s:else>
		          		</td>
		          	</tr>
		          	<tr>
		          		<td align="right" style="width:15%;"><label>活动内容简述：</label></td>
		          		<td colspan="3">
		          			<s:if test="null==pmMateEntryId">
		                   	<textarea rows="" cols="" class="max required" style="border:1px solid black; border-left: 2px solid red;min-height: 80px;margin-bottom:10px;margin-top:10px;font-size:12px;width:88%;padding-right:13px;margin-left:5px;" id="activeContent" name="entity.activeContent" maxlength="1000"></textarea>
		                   </s:if>
		                   <s:else>
		                   	<textarea rows="" cols="" class="max required" style="border:1px solid black; border-left: 2px solid red;min-height: 80px;margin-bottom:10px;margin-top:10px;font-size:12px;width:88%;padding-right:13px;margin-left:5px;" id="activeContent" name="entity.activeContent" value="${entity.activeContent }" maxlength="1000">${entity.activeContent }</textarea>
		                   </s:else>
		          		</td>
		          	</tr>
		          	
		          	<tr>
		          		<td align="right" style="width:10%;"><label>时间周期：</label></td>
		          		<td>
		          			<s:if test="null==pmMateEntryId">
		             			<input type="text" class="text max required"  name="entity.activePeriod" id="activePeriod" style="width: 70%;margin-left:5px;" maxlength="50"/>
		             		</s:if>
		             		<s:else>
		             			<input type="text" class="text max required"  name="entity.activePeriod" id="activePeriod" value="${entity.activePeriod }" style="width: 70%;margin-left:5px;" maxlength="50"/>
		             		</s:else>
		          		</td>
		          		<td align="right" style="width:25%;"><label>费用预算(元)：</label></td>
		          		<td>
		          			<s:if test="null==pmMateEntryId">
		             			<input type="text" class="text max required" alt="expensesBudget" name="entity.expensesBudget" id="expensesBudget" style="width: 70%;margin-left:5px;padding-right:1px;" maxlength="15" onkeyup="clearNoNum_1(this);"/>
		             		</s:if>
		             		<s:else>
		             			<input type="text" class="text max required" alt="expensesBudget" name="entity.expensesBudget" id="expensesBudget" value="${entity.expensesBudget }" style="width: 70%;margin-left:5px;padding-right:1px;" maxlength="15" onkeyup="clearNoNum_1(this);"/>
		             		</s:else>
		          		</td>
		          	</tr>
		          	
		          	<tr>
		          		<td align="right" style="width:10%;"><label>道具：</label></td>
		          		<td><s:if test="null==pmMateEntryId">
		               		<input type="text" class="text max required"  name="entity.properties" id="properties" style="width: 70%;margin-top:10px;margin-left:5px;" maxlength="100"/>
		               	</s:if>
		             		<s:else>
		             			<input type="text" class="text max required"  name="entity.properties" id="properties" value="${entity.properties }" style="width: 70%;margin-top:10px;margin-left:5px;" maxlength="100"/>
		             		</s:else>
		             	</td>
		          		<td align="right" style="width:25%;"><label>美陈：</label></td>
		          		<td>
		          			<s:if test="null==pmMateEntryId">
		             			<input type="text" class="text max required" name="entity.meichen" id="meiChen"  style="width: 70%;margin-top:10px;margin-left:5px;padding-right:1px;" maxlength="100"/>
		             		</s:if>
		             		<s:else>
		             			<input type="text" class="text max required" name="entity.meichen" id="meiChen" value="${entity.meichen }" style="width: 70%;margin-top:10px;margin-left:5px;padding-right:1px;" maxlength="100"/>
		             		</s:else>
		          		</td>
		          	</tr>
		          	
		          	<tr>
		          		<td align="right" style="width:10%;"><label>赠品：</label></td>
		          		<td>
		          			<s:if test="null==pmMateEntryId">
		               		<input type="text" class="text max required"  name="entity.present" id="present" style="width: 70%;margin-top:10px;margin-left:5px;" maxlength="100"/>
		               	</s:if>
		             		<s:else>
		             			<input type="text" class="text max required"  name="entity.present" id="present" value="${entity.present }" style="width: 70%;margin-top:10px;margin-left:5px;" maxlength="100"/>
		             		</s:else>
		          		</td>
		          		<td align="right" style="width:25%;"><label>媒体：</label></td>
		          		<td>
		          			<s:if test="null==pmMateEntryId">
		             			<input type="text" class="text max required" name="entity.medium" id="medium" style="width: 70%;padding-right:1px;margin-top:10px;margin-left:5px;" maxlength="100"/>
		             		</s:if>
						<s:else>
							<input type="text" class="text max required" name="entity.medium" id="medium" value="${entity.medium }" style="width: 70%;padding-right:1px;margin-top:10px;margin-left:5px;" maxlength="100"/>
						</s:else>
		          		</td>
		          	</tr>
		          	
		          	<tr>
		          		<td align="right" style="width:10%;"><label>操作指引：</label></td>
		          		<td colspan="3">
		          			<s:if test="null==pmMateEntryId">
		                   	<textarea rows="" cols="" class="max required" style="border:1px solid black; border-left: 2px solid red;min-height: 80px;margin-bottom:10px;font-size:12px;width:88%;margin-top:10px;padding-right:13px;margin-left:5px;" id="operatorGuide" name="entity.operatorGuide" maxlength="1000"></textarea>
		                   </s:if>
		                   <s:else>
		                   	<textarea rows="" cols="" class="max required" style="border:1px solid black; border-left: 2px solid red;min-height: 80px;margin-bottom:10px;font-size:12px;width:88%;margin-top:10px;padding-right:13px;margin-left:5px;" id="operatorGuide" name="entity.operatorGuide" maxlength="1000" value="${entity.operatorGuide }">${entity.operatorGuide }</textarea>
		                   </s:else>
		          		</td>
		          	</tr>
		          	
		          	<tr>
		          		<td align="right" style="width:10%;"><label>预期效果：</label></td>
		          		<td colspan="3">
		          			<s:if test="null==pmMateEntryId">
							<textarea rows="" cols="" class="max required" style="border:1px solid black; border-left: 2px solid red;margin-bottom:10px;font-size:12px;min-height: 80px;width:88%;padding-right:13px;margin-left:5px;" id="expectedResults" name="entity.expectedResults" maxlength="1000"></textarea>
		                   </s:if>
		                   <s:else>
		                   	<textarea rows="" cols="" class="max required" style="border:1px solid black; border-left: 2px solid red;margin-bottom:10px;font-size:12px;min-height: 80px;width:88%;padding-right:13px;margin-left:5px;" id="expectedResults" name="entity.expectedResults" value="${entity.expectedResults }" maxlength="1000">${entity.expectedResults }</textarea>
		                   </s:else>
		          		</td>
		          	</tr>
		          	
		          	<tr>
		          		<td align="right" style="width:10%;">
		          			<label>参考照片及方案：</label><br/>
			          		<security:authorize ifAnyGranted="A_PM_ENTRY_NEW,A_PM_ENTRY_EDIT">
			          			<input class="btn_green" type="button" isAttach="true" value="上传附件" style="float:left;margin-left:45px;margin-top:2px;" onclick="openAttachmentByModel('上传附件管理','projectManager','<%=SpringSecurityUtils.getCurrentUiid()%>'); return false;"/></span>
							</security:authorize>		
		          		</td>
		          		<td colspan="3">
		          			<table style="width:88%;" cellpadding="0" cellspacing="0">
		          				<col/>
		          				<tr>
		          					<td colspan="2">
		          						<div style="float: left;border:1px solid black; border-left: 2px solid red;min-height: 80px; width:100%;margin-left:4px;padding-left:5px;padding-right:9px;">
						                   	<div id="attaFilesContainer">
						                   		
						                   	</div>
										</div>
					                  	<div id="uploadAttaContainers" style="float:left;">
					                  		<input id="fileSn_pm" type="hidden" name="fileSn_pm"/>
					                  	</div>
		          					</td>
		          				</tr>
		          			</table>
		          		</td>
		          	</tr>
		          </table>
           
           </form>
	    </div>
	    <s:if test="pmEntryResRelationList.size()>0">
		    <div id="div_audit" class="form_body" style="padding-top:15px;">
		    	<table id="form_body_table" class="sup_table" cellspacing="0" cellpadding="0"style="background-color:#e4e7ec;overflow:hidden;">
					  <thead>
					  <tr>
					   <th>类型</th>
					   <th>时间</th>
					   <th>项目名称</th>
					   <th>合同编号</th>
					   <th>相关网批</th>
					  </tr>
					  </thead>
					  <tbody>
					  <s:iterator value="pmEntryResRelationList" status="st">
					  <s:set var="preID">${pmMateEntryId}</s:set>
					  <tr>
					  <s:if test="#preID != #preID2">
					   <td align="center">${displayName}&nbsp;(${displayNameSize }条)</td>
					  </s:if>
					   <td align="center"><s:date name ="approveEndDate" format="yyyy-MM-dd"/></td>
					   <td align="center">${projectName}</td>
					   <td align="center">
					   	  <input type="hidden" name="contLedgerId" id="contLedgerId" value="${contLedgerId}"/>
					   	  <div onclick="getContById();" style="cursor: pointer; text-decoration: underline;">${contractNo}</div>
					   </td>
					   <td align="center"><div onclick="parent.showAll('${ctx}/res/res-approve-info.action?id=${resApproveId}','resApprove');" style="cursor: pointer; text-decoration: underline;">审批内容</div></td>
					  </tr>
					  <s:set var="preID2">${pmMateEntryId}</s:set>
					  </s:iterator>
					  </tbody>
				</table>
		    </div>
	    </s:if>
	</div>
</div>

<script type="text/javascript">
$(function(){
	$('#pdChilltip').remove();
	//行数
	var rowspan = $('#form_body_table tbody tr').length;
	//设置跨行
	$('td:eq(0)','#form_body_table tbody tr:eq(0)').attr('rowspan',rowspan);
	//$('#form_body_table tbody tr').eq(0).find('td').eq(0).attr('rowspan',rowspan);
});
</script>