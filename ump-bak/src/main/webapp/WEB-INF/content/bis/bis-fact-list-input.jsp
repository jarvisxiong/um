<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.DictMapUtil"%>

<%@page import="com.hhz.ump.util.JspUtil"%>

<style>
<!--
table.detail{margin:0 auto;width:98%;border: 1px solid #DBDBDB;}
table.must{margin:0 auto;width:98%;}
table.must td{height:26px;line-height: 26px;}
table.must th{height:26px;line-height: 26px;}
table.detail th{text-align:left;padding:4px;border: 1px solid #DBDBDB;background: #dbdbdb;font-weight:lighter;}
table.detail td{text-align:left;padding:4px;border: 1px solid #DBDBDB;}
-->
</style>
<form action="${ctx}/bis/bis-fact!save.action" id="inputForm" method="post">
	<input type="hidden" id="id" name="id" value="${bisFactId}"/>
	<input type="hidden" id="currDetail" name="currDetail" value="${currDetail}"/>
	<input type="hidden" id="currDetailName" name="currDetailName" value="${currDetailName}"/>
	<input type="hidden" id="bisContIdInput" name="bisContIdInput" value="${voFact.bisContId }"/> 
	<table class="must" border="0">
		<tr>
			<td style="text-align:right;padding-right:4px;">名称：</td>
			<td >${currDetailName}</td>
			<td style="text-align:right;padding-right:4px;">合同类别：</td>
			<td>${voFact.contBigTypeCd}</td>
		</tr>
		<s:if test="voFact.bisContId !=null">
		<tr>
			<td style="text-align:right;padding-right:4px;">合同编号：</td>
			<td colspan="3"><a href="javascript:openCont('${voFact.bisContId}');">${voFact.contNo}</a></td>
		</tr>
		</s:if>
		<tr>
			<td style="text-align:right;padding-right:4px;">权责年月：</td>
			<td><strong>${voFact.factYear}年${voFact.factMonth}月</strong></td>
			<td style="text-align:right;padding-right:4px;">最晚应收日期：</td>
			<td><strong>${voFact.factDate}</strong></td>
		</tr>
		<tr>
			<td style="text-align:right;padding-right:4px;">应收金额：</td>
			<td><strong>${voFact.mustMoney}</strong></td>
			<td style="text-align:right;padding-right:4px;">费用类别：</td>
			<td><strong>${voFact.chargeTypeCdName}</strong></td>
		</tr>
	</table>
	<table class="detail content_table" style="border:0px;" id="detail">
		<col width="18%">
		<col width="18%">
		<col width="34%">
		<col width="20%">
		<tr>
			<th style="border-right:1px solid #fff;">实收日期</th>
			<th style="border-right:1px solid #fff;">实收金额</th>
			<th >备注</th>
			<th style="border-right:1px solid #fff;">
				<div style="float:left  ;  line-height: 26px;">状态</div>
				<input type="button" onclick="addItem();" style="float:left;  margin-top: 2px; margin-left: 10px;" class="btn_blue" id="newFact" value='新增'/>
			</th>
		</tr>
		<s:iterator value="factList" status="status">
		<tr>
			<td style="text-align:center;"><s:date name="factDate" format="yyyy-MM-dd"/></td>
			<td style="text-align:right;">${money }</td>
			<td  title="${remark }" ><div class="ellipsisDiv_full" style="width:50px;">${remark }</div></td>
			<td>
				<font  <s:if test="statusCd==0">style="color:red"		</s:if>>
					<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisFactStatus()" 
					value="statusCd"/>
				</font>
			</td>
		</tr>
		</s:iterator>
		<tr id="factTr" style="display:none;">
			<td>
				<input id="factListDate" style="width: 95%" type="text" name="money[0]" 
				onfocus="WdatePicker()" class="inputBorder Wdate required select_115_20" />
			</td>
			<td>
				<input id="newMoney" style="width: 95%" type="text" name="money[0]" 
				onblur="formatNumber1($(this));"/>
			</td>
			<td>
				<textarea rows="2" name="remark" id="remark" style="font-size:12px;"></textarea>
				<%--应收 --%>
				<input type="hidden" name="totalMustMoney" value="${voFact.mustMoney}">
			</td>
			<td align="right" style="text-align: center;">
				新增&nbsp;
				<font style="color: red; font-weight: bold; cursor: pointer;" onclick="delItem(this);">×</font>
			</td>
		</tr>
	</table>
	<div id='tip' style="margin-left:20px;display:none;color:red">建议单条录入</div>
</form>
<s:set var="conItemCount"><s:property value="factList.size()"/></s:set>
<script type="text/javascript">
var layOutCd = '${layOutCd}';
/*
switch(layOutCd){
	case '1':$('#detailTitle').html('租户：');break;
	case '2':$('#detailTitle').html('公寓：');break;
	case '3':$('#detailTitle').html('多经：');break;
}
*/
var factListData = {
	bisProjectId:'${voFact.bisProjectId}',
	layOutCd:'${layOutCd}',
	chargeTypeCd:'${voFact.chargeTypeCd}',
	factYear:'${voFact.factYear}',
	factMonth:'${voFact.factMonth}',
	currDetail:'${currDetail}',
	storeId:'${voFact.bisStoreId}',
	remark:'${remark}',
	bisContId:'${voFact.bisContId}',
	totalMustMoney:'${voFact.mustMoney}'
};
function newFact(){
	ymPrompt.close();
	appendBisFact(
		factListData.bisProjectId,
		factListData.layOutCd,
		factListData.chargeTypeCd,
		factListData.factYear,
		factListData.factMonth,
		factListData.remark);
}

var trApprover=$("#factTr").clone();
$("#factTr").remove();
var cloneCount = 0;
var index=${conItemCount};

function addItem(){
	if(cloneCount>0){
		$('#tip').show().fadeOut(2000);
		return;
	}
	var trApprover_new=trApprover.clone();
	cloneCount++;
	$(trApprover_new).show();
	$(trApprover_new).find(":input").each(function(i){
		this.name=this.name.replace('0',index);
		this.id=this.id.replace('0',index);
	});
	$("#detail").append(trApprover_new);
	index++;
}
function delItem(dom){
	$(dom).parent().parent().remove();
	cloneCount--;
}
function addItemVal(jObj,date){
	var trApprover_new=trApprover.clone();
	cloneCount++;
	$(trApprover_new).show();
	$(trApprover_new).find(":input").each(function(i){
		if(this.name.indexOf('describe')!=-1) {
			$(this).val(jObj.describe);
		}
	});
	$(trApprover_new).find(":input[type=checkbox]").attr("date", date);
	$("#tbConItem").append(trApprover_new);
	index++;
}
function openCont(contId){
	var url = "${ctx}/bis/bis-cont!input.action?id="+contId;
	if(parent.TabUtils==null){
		window.open(url);
	}else{
		parent.TabUtils.newTab("bisContMenu","合同明细",url,true);
	}
}
</script>