<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>


	
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="java.util.Date"%>
<style>
<!--
tr.mainTr input{background-color: #DBE5F1;border:1px solid #909090;
}
tr.mainTr select{background-color: #DBE5F1;
}
tr.mainTr input:focus{
	background-color: #ffffe1;border:1px solid #909090;
}
tr.mainTr select:focus{
	background-color: #ffffe1;
}
-->
</style>
<script type="text/javascript">
var trApprover=$("#tr_n").clone();

var cloneCount = 0;



function delItem(dom){
	ymPrompt.confirmInfo({message:'确认删除？',title:'删除',width:200,height:150,handler:function (tp){
		if (tp=='ok'){
			$(dom).parent().parent().remove();
		}
	}});
}
	</script>
<div id="tableDiv">
	<table class="content_table" id="editTable" style="width: expression(this.parentNode.offsetHeight >this.parentNode.scrollHeight ? '100%' :parseInt(this.parentNode.offsetWidth - 18) + 'px');">
		<col width="14">
		<col width="50">
		<col width="40">
		<col width="50">
		<col width="50">
		<col width="20">
		<col width="20">
		<col width="50">
		<col width="50">
		<col width="45">
			<tr class="header">
				<th style="width:100px;"><input type="checkbox" onclick="checkedAll($(this).attr('checked'));" style="cursor:pointer;" title="全选/不选"/></th>
				<th nowrap="nowrap" colspan="2" align="left" style="cursor: pointer;" onclick="">
					楼层-商铺编号
				</th>
				<th nowrap="nowrap" align="left" onclick="">
					商家名称
				</th>
				<th nowrap="nowrap" align="left" onclick="sortBy(this,'titleName','${selectedOrder}')">
					费用类别
				</th>				
				<th nowrap="nowrap" align="left" class="pd-chill-tip" >年份</th>
				<th nowrap="nowrap" align="left" >月份</th>
				<th nowrap="nowrap" align="left" >应收(元)</th>
				<th nowrap="nowrap" align="left" >实收(元)</th>
				<th nowrap="nowrap" align="left" >实收日期</th>
			</tr>
			<s:iterator value="voPage.result" status="st">
			<tr class="insertr mainTr" id="tr_n${st.count}"  >
				<input type="hidden" name="bisTenantId" value="${bisTenantId }">
				<input type="hidden" name="bisContId" value="${bisContId }">
				<input type="hidden" name="bisShopId" value="${bisShopId }">
				<input type="hidden" name="bisStoreId" value="${bisStoreId }">
				<input type="hidden" name="bisProjectId" value="${bisProjectId }">
				<input type="hidden" name="chargeTypeCd" value="${ chargeTypeCd}">
				<input type="hidden" name="factYear" value="${factYear }">
				<input type="hidden" name="factMonth" value="${ factMonth}">
				<td id="chk_${bisFactId}" nowrap="nowrap" onclick='scheClick("${st.count}");' align="center">
					<div style="display:inline;">
						<input type="checkbox" id="chk_all"  checked="checked" ids="st.count" onClick="CAN_scheClick=false;"  ></input>
					</div>
				</td>
				<td nowrap="nowrap" class="pd-chill-tip" title="${storeNo }"     colspan="2">
					<div class="ellipsisDiv_full" name="storeNo">${storeNo }</div> 
				</td>
				<td nowrap="nowrap" class="pd-chill-tip" title="${facts }"	 >
				<input type="hidden" name="bisShopId" value="${bisShopId}"/>				<div class="ellipsisDiv_full" name="connName">${connName }</div></td>
				<td nowrap="nowrap" class="pd-chill-tip" title="${facts }"	 >
					<div class="ellipsisDiv_full" name="chargeTypeCdName">${chargeTypeCdName }</div>
				</td>
				<td nowrap="nowrap" class="pd-chill-tip" title="${facts }"  >
					<div class="ellipsisDiv_full"  name="factYear">${factYear }</div>
				</td>
				<td nowrap="nowrap" class="pd-chill-tip" title="${facts }" onclick="">
					<div class="ellipsisDiv_full"  name="factMonth"> ${factMonth }</div>
				</td>
				<td nowrap="nowrap" class="pd-chill-tip" title="${ facts }"  ><div class="ellipsisDiv_full" name="mustMoney">${mustMoney }<input type="hidden" value="${mustMoney }" name="totalMustMoney"></input></div></td>
				<td nowrap="nowrap" class="pd-chill-tip" title="${ facts}" >
					<div class="" ><input type="text" name="money" style="width:80px" value="${money }" onblur="formatNumber1($(this))"/></div>
				</td>
				<td nowrap="nowrap" class="pd-chill-tip" title="${facts }" >
					<div class="" ><input type="text" name="factDate" value="${factDate }" style="width:80px" option="Date"  onfocus="WdatePicker({dateFmt:'yy-MM-dd'});"/></div>
				</td>
			</tr>
				</s:iterator>
	</table> 
</div><%--

<div class="bottom_bar">
	<div id="operate_all_div">
		<div class="btn_bottom_chk_all"><div style="padding-top:5px;"><input type="checkbox" onclick="checkedAll($(this).attr('checked'));" style="cursor:pointer;" title="全选/不选"/></div></div>
			<div class="btn_bottom_bar" onclick="doSaveAll(1);">批量保存</div>
	</div>
	<div id="td_page" style="float:right; text-align: center; height:26px; margin-right:10px;"></div>
</div>
--%>