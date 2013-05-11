<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>


	
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="java.util.Date"%>
<div id="tableDiv">
	<table  id="editTable" style="width:100%;" class="content_table">
		<col width="30">
		<col width="50">
		<col width="60">
		<col >
		<tr class="header"><th rowspan="2">序号</th><th rowspan="2">商铺号</th><th rowspan="2">建筑面积</th>	<th colspan="6"  align="center">应缴</th><th colspan="5" align="center" >尚欠</th><th rowspan="2">缴费日期</th><th rowspan="2">操作</th></tr>
		<tr class="header"><th >物业费</th><th >公摊费</th><th >空调费</th><th >电费</th><th >水费</th><th >合计</th><th >物业费</th><th >公摊费</th><th >空调费</th><th >电费</th><th >水费</th></tr>
			<s:iterator value="voPayPage.result" status="st">
				<tr class="mainTr" id="tr_${rowno}" >
					<div style="display:none" ><div  class="ellipsisDiv_full" colTitle="year">${year }</div></div>
					<div style="display:none" ><div  class="ellipsisDiv_full" colTitle="month">${month }</div></div>
					<td id="chk_<s:property value="#st.count"/>" nowrap="nowrap" align="center">
						<div style="display:inline;"><div  class="ellipsisDiv_full" colTitle="rowno" style=" display:none; vertical-align: top;">${rowno }</div>
							<input type="checkbox" id="chk_all"  ids="tr_${rowno}" onClick="CAN_scheClick=false;" recordVersion="${recordVersion}"></input>
						</div>
					</td>
						<td  class="pd-chill-tip" title="${storeNos }"  onclick="" ><div class="ellipsisDiv_full" colTitle="storeNos">${storeNos }</div></td>
						<td  class="pd-chill-tip" title="${square }"  ><div  class="ellipsisDiv_full" colTitle="square">${square }</div></td>
						<td  ><div class="ellipsisDiv_full" colTitle="chargeType1Must">${chargeType1Must }</div></td>
						<td  ><div class="ellipsisDiv_full" colTitle="chargeType2Must">${chargeType2Must }</div></td>
						<td  ><div class="ellipsisDiv_full" colTitle="chargeType3Must">${chargeType3Must }</div></td>
						<td  ><div class="ellipsisDiv_full" colTitle="chargeType4Must">${chargeType4Must }</div></td>
						<td  ><div class="ellipsisDiv_full" colTitle="chargeType5Must">${chargeType5Must }</div></td>
						<td  ><div class="ellipsisDiv_full" colTitle="totalNum">${totalNum }</div></td>
						<td  class="edit_input"><div class="ellipsisDiv_full" colTitle="chargeType1Fact">${chargeType1Fact }</div><input type="text" onblur="formatNumber1($(this));updatPrevValue(this);" style="display:none" value="${chargeType1Fact }"/></td>
						<td  class="edit_input"><div class="ellipsisDiv_full" colTitle="chargeType2Fact">${chargeType2Fact }</div><input type="text" onblur="formatNumber1($(this));updatPrevValue(this);" style="display:none" value="${chargeType2Fact }"/></td>
						<td  class="edit_input"><div class="ellipsisDiv_full" colTitle="chargeType3Fact">${chargeType3Fact }</div><input type="text" onblur="formatNumber1($(this));updatPrevValue(this);" style="display:none" value="${chargeType3Fact }"/></td>
						<td  class="edit_input"><div class="ellipsisDiv_full" colTitle="chargeType4Fact">${chargeType4Fact }</div><input type="text" onblur="formatNumber1($(this));updatPrevValue(this);" style="display:none" value="${chargeType4Fact }"/></td>
						<td  class="edit_input"><div class="ellipsisDiv_full" colTitle="chargeType5Fact">${chargeType5Fact }</div><input type="text" onblur="formatNumber1($(this));updatPrevValue(this);" style="display:none" value="${chargeType5Fact }"/></td>
						<td  class="edit_input"><div class="ellipsisDiv_full" colTitle="mustDate"><s:date  name="mustDate" format="yyyy-MM-dd"/></div>
						<input class=input" type="text" 
				 onfocus="WdatePicker()"  value="<s:date  name="mustDate" format="yyyy-MM-dd"/>"
				 name="factInBegin" id="factInBegin"  onblur="updatPrevValue(this);" style="cursor: pointer;display:none"/>
						</td>
					<td align="center"><input type="button" onclick="populateData('tr_${rowno}')" class="btn_green" value="打印"/></td>
				</tr>
			</s:iterator>
	</table>
	<div id="detailPanelDiv" style="display:none"/>
</div>

<div class="bottom_bar">
		<div id="operate_all_div">
			<div class="btn_bottom_chk_all"><div style="padding-top:5px;"><input type="checkbox" onclick="checkedAll($(this).attr('checked'));" style="cursor:pointer;" title="全选/不选"/></div></div>
				<div class="span_bottom_bar">全选</div>
				<div class="btn_bottom_bar" onclick="doPrintAll(1);">打印</div>
		</div>
		<div id="td_page" style="float:right; text-align: center; height:26px; margin-right:10px;"></div>
		<div class="table_pager" style="margin-top:5px;">
			  <div style="width: 100%;">
			&nbsp;共有&nbsp;${page.totalCount}&nbsp;条记录&nbsp;&nbsp;&nbsp;&nbsp;
			当前第 ${page.pageNo}/${page.totalPages}&nbsp;页
							 <s:if test="page.hasPre">
								<img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_up.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_up_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_up.gif');" onclick="jumpPage4Pay('${page.prePage}');"/>
							 </s:if>
							 <s:else>
							 	<img align="absmiddle" src="${ctx}/images/desk2/page_up_grey.gif" />
							 </s:else>
							 <s:if test="page.hasNext">
								<img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_down.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_down_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_down.gif');" onclick="jumpPage4Pay('${page.nextPage}');"/>
							 </s:if>
							 <s:else>
							 	<img align="absmiddle" src="${ctx}/images/desk2/page_down_grey.gif" />
							 </s:else>
							 <input type="text" id="pageNo" style="height: 15px; width: 20px; text-align: center;" value="${page.pageNo}"/>
							 <a href="#" onblur="this.blur();" onclick="jumpPage4Pay($(this).prev().val()); return false;">GO</a>
					
		</div>
		</div>
</div>
<script>
$(function(){
	$('.edit_input').bind('click',function(){
		$(this).find('.ellipsisDiv_full').hide();
		$(this).find('input').show().focus();
	});
});

</script>