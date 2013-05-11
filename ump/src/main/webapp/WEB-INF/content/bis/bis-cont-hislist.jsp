<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<div id="result_div" style="padding: 8px;">
<input type="hidden" id="compareIds" >
<input type="hidden" id="bisContHisId" >
<table class="content_table" id="result_table">
	<col width="30"/>
	<col />
	<col width="65"/>
	<col width="65"/>
	<col width="65"/>
	<col width="65"/>
	<thead>
		<tr class="header">
			<th align="left" style="background: none;"></th>
			<th align="left">合同编号</th>
			<th align="left">合同开始</th>
			<th align="left">合同结束</th>
			<th align="left">审核时间</th>
			<th align="left">变更时间</th>
		</tr>
	</thead>
	<tbody>
		<tr class="mainTr" >
			<td colspan="6" style="font-weight: bold; background-color: #E4E7EC;">当前版本</td>
		</tr>
		<tr class="mainTr" id="main_${bisContId}" >
			<td align="center"><input id="chk_0_${bisContId}" type="checkbox" onclick="chooseCont(this,'0_${bisContId}');" /></td>
			<td title="${contNo}">
				<div class="ellipsisDiv_full" >
				${contNo}
				</div>
			</td>
			<td><s:date name="contStartDate" format="yy-MM-dd"/></td>
			<td><s:date name="contEndDate" format="yy-MM-dd"/></td>
			<td <s:if test="checkDate!=null">title='<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("checkUserCd")) %>(<s:date name="checkDate" format="yy-MM-dd"/>)'</s:if> >
				<s:date name="checkDate" format="yy-MM-dd"/>
			</td>
			<td></td>
		</tr>
		<tr class="mainTr" >
			<td colspan="6" style="font-weight: bold; background-color: #E4E7EC;">历史版本</td>
		</tr>
		<s:iterator value="bisContHisList">
		<tr class="mainTr" id="main_${bisContHisId}" >
			<td align="center"><input type="checkbox" id="chk_1_${bisContHisId}" onclick="chooseCont(this,'1_${bisContHisId}');"/></td>
			<td onclick="viewContHis('${bisContHisId}');" title="${contNo}" >
				<div class="ellipsisDiv_full" >
				${contNo}
				</div>
			</td>
			<td onclick="viewContHis('${bisContHisId}');" ><s:date name="contStartDate" format="yy-MM-dd"/></td>
			<td onclick="viewContHis('${bisContHisId}');" ><s:date name="contEndDate" format="yy-MM-dd"/></td>
			<td <s:if test="checkDate!=null">title='<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("checkUserCd")) %>(<s:date name="checkDate" format="yy-MM-dd"/>)'</s:if> >
				<s:date name="checkDate" format="yy-MM-dd"/>
			</td>
			<td onclick="viewContHis('${bisContHisId}');" ><s:date name="updatedDate" format="yy-MM-dd"/></td>
			<%-- 
			<td><input type="button" value="查看" class="btn_blue" style="width:40px;" onclick="viewContHis('${bisContHisId}');" /></td>
			--%>
		</tr>
		</s:iterator>
		<%-- 
		--%>
		<tr>
			<td colspan="6"  style="padding-top: 10px;">
				<input type="button" value="对比" class="btn_blue" onclick="ymPrompt.doHandler('compare',false);" />
			</td>
		</tr>
	</tbody>
</table>
</div>

<script type="text/javascript">
isEmpty = function (str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
};
//选中合同
var contIdArr = new Array();
function selectCont(dom,value) {
	if($(dom).hasClass("tr_click")) {
		$(dom).removeClass("tr_click");
		if(value == contIdArr[0]) {
			contIdArr[0] = null;
		} else {
			contIdArr[1] = contIdArr[0];
			contIdArr[0] = null;
		}
	} else {
		$(dom).addClass("tr_click");
		if(!isEmpty(contIdArr[0])) {
			$("#main_"+contIdArr[0].substring(2, contIdArr[0].length)).removeClass("tr_click");
		}
		contIdArr[0] = contIdArr[1];
		contIdArr[1] = value;
	}
	if(!isEmpty(contIdArr[0]) && !isEmpty(contIdArr[1])) {
		$("#compareIds").val(contIdArr[0]+','+contIdArr[1]);
	}
	//alert(contIdArr[0]+','+contIdArr[1]);
}

function chooseCont(dom,value) {
	if(!$(dom).attr("checked")) {
		if(value == contIdArr[0]) {
			contIdArr[0] = null;
		} else {
			contIdArr[1] = contIdArr[0];
			contIdArr[0] = null;
		}
	} else {
		if(!isEmpty(contIdArr[0])) {
			$("#chk_"+contIdArr[0]).attr("checked", false);
		}
		contIdArr[0] = contIdArr[1];
		contIdArr[1] = value;
	}
	if(!isEmpty(contIdArr[0]) && !isEmpty(contIdArr[1])) {
		$("#compareIds").val(contIdArr[0]+','+contIdArr[1]);
	}
	//alert(contIdArr[0]+','+contIdArr[1]);
}

function viewContHis(id) {
	$("#bisContHisId").val(id);
	ymPrompt.doHandler('view',false);
}
</script>