<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<%@ include file="template-header.jsp"%>

<!-- 对外品牌形象推广审批表 -->
<div class="billContent" align="center">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="">
		<%@ include file="template-var.jsp"%>		
			<table  class="mainTable">
				<col width="16%"/>
				<col width="28%"/>
				<col width="28%"/>
				<col width="28%"/>
				<tr>
					<td class="td_title">标题</td>
					<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.title" value="${templateBean.title}" /></td>
				</tr>
				<tr>
					<td class="td_title">单位/部门</td>
					<td colspan="3"><input class="inputBorder orgSelect" validate="required" type="text" name="templateBean.units" readonly="readonly" /></td>
				</tr>
				<tr class="chkGroup">
					<td class="td_title">推广类型</td>
					<td><s:checkbox name="templateBean.expandType1" cssClass="group"></s:checkbox>参加论坛</td>
					<td><s:checkbox name="templateBean.expandType2" cssClass="group"></s:checkbox>申报奖项</td>
					<td><s:checkbox name="templateBean.expandType3" cssClass="group"></s:checkbox>媒体采访</td>
				</tr>
				<tr>
					<td class="td_title">费用</td>
					<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.cost" value="${templateBean.cost}" /></td>
				</tr>
			</table>
			
			<table  class="mainTable" style="margin-top:5px;">
				<col width="16%"/>
				<col width="28%"/>
				<col width="28%"/>
				<col width="28%"/>
				<tr>
					<td colspan="4" align="left"><strong>论坛/奖项情况</strong></td>
				</tr>
				<tr>
					<td class="td_title">日期</td>
					<td><input class="inputBorder Wdate" validate="required" type="text" name="templateBean.date" value="${templateBean.date}" onfocus="WdatePicker()"/></td>
					<td class="td_title">地点</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.place" value="${templateBean.place}" /></td>
				</tr>
				<tr>
					<td class="td_title">主办方</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.orgnizer" value="${templateBean.orgnizer}" /></td>
					<td class="td_title">出席人</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.attendee" value="${templateBean.attendee}" /></td>
				</tr>
				<tr>
					<td class="td_title">活动介绍</td>
					<td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" type="text" name="templateBean.activityIntroduce">${templateBean.activityIntroduce}</textarea></td>
				</tr>
				<tr>
					<td class="td_title">奖项名称</td>
					<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.awardsName" value="${templateBean.awardsName}" /></td>
				</tr>
				<tr>
					<td class="td_title">获奖对象</td>
					<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.awardsTarget" value="${templateBean.awardsTarget}" /></td>
				</tr>
			</table>
			
			<table class="mainTable" style="margin-top:5px;">
				<col width="16%"/>
				<col width="28%"/>
				<col width="28%"/>
				<col width="28%"/>
				<tr>
					<td colspan="4" align="left"><strong>媒体采访情况</strong></td>
				</tr>
				<tr>
					<td class="td_title">媒体名称</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.mediaName" value="${templateBean.mediaName}" /></td>
					<td class="td_title">采访对象</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.coverTarget" value="${templateBean.coverTarget}" /></td>
				</tr>
				<span class="chkGroup">
				<tr>
					<td class="td_title">刊登栏目</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.publishColumn" value="${templateBean.publishColumn}" /></td>
					<td class="td_title" rowspan="3">采访形式</td>
					<td align="left"><s:checkbox name="templateBean.covertMode1" cssClass="group"></s:checkbox>面访</td>
				</tr>
				<tr>
					<td class="td_title">媒体联系人</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.mediaLinkMan" value="${templateBean.mediaLinkMan}" /></td>
					<td align="left"><s:checkbox name="templateBean.covertMode2" cssClass="group"></s:checkbox>电话</td>
				</tr>
				<tr>
					<td class="td_title">职务</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.service" value="${templateBean.service}" /></td>
					<td align="left"><s:checkbox name="templateBean.covertMode3" cssClass="group"></s:checkbox>邮件</td>
				</tr>
				</span>
				<tr>
					<td class="td_title">手机</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.phone" value="${templateBean.phone}" /></td>
					<td class="td_title">采访日期</td>
					<td><input class="inputBorder Wdate" validate="required" type="text" name="templateBean.coverDate" value="${templateBean.coverDate}" onfocus="WdatePicker()"/></td>
				</tr>
				<tr>
					<td class="td_title">座机</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.telephone" value="${templateBean.telephone}" /></td>
					<td class="td_title">采访耗时</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.coverTime" value="${templateBean.coverTime}" /></td>
				</tr>
				<tr>
					<td class="td_title">Email</td>
					<td><input class="inputBorder" validate="required" type="text" name="templateBean.email" value="${templateBean.email}" /></td>
					<td class="td_title">刊登日期</td>
					<td><input class="inputBorder Wdate" validate="required" type="text" name="templateBean.publishDate" value="${templateBean.publishDate}" onfocus="WdatePicker()"/></td>
				</tr>
				<tr>
					<td class="td_title">采访主题</td>
					<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.coverSubject" value="${templateBean.coverSubject}" /></td>
				</tr>
				<tr>
					<td class="td_title">采访提纲</td>
					<td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" type="text" name="templateBean.coverOutLine">${templateBean.coverOutLine}</textarea></td>
				</tr>
				<tr class="chkGroup">
					<td class="td_title">拍摄需求</td>
					<td align="left"><s:checkbox name="templateBean.shootNeed1" cssClass="group"></s:checkbox>有</td>
					<td colspan="2" align="left"><s:checkbox name="templateBean.shootNeed2" cssClass="group"></s:checkbox>无</td>
				</tr>
				<tr>
					<td class="td_title">媒体介绍</td>
					<td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" type="text" name="templateBean.mediaIntroduce">${templateBean.mediaIntroduce}</textarea></td>
				</tr>
			</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>