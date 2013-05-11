<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@page import="java.math.*"%>

<div class="res_tip">
	<div style="padding-left:60px; line-height:30px; font-weight:bold; font-size:16px; float:left;">
        欠费提醒（权责维度）&nbsp;&nbsp;&nbsp;&nbsp;总计：欠费${totalMoney}万元
	</div>
</div>
	
<table class="content_table" id="result_table" style="width: 100%">
	<col />
	<col />
	<col />
	<col />
	<col width="80"/>
	<col width="100"/>
	<col width="80"/>
	<col width="100"/>
	<col width="100"/>
	<thead>
		<tr class="header">
			<th style="background: rgb(237, 239, 243);padding-left: 20px;">商家</th>
			<th >面积</th>
			<th >累计应收</th>
			<th >累计实收</th>
			<th title="累计实收/累计应收">累计比例</th>
			<th >累计欠费</th>
			<th >欠费提醒</th>
			<th >欠费账龄分析</th>
			<th >保证金额度</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td colspan="9" style="font-size: 14px; cursor: default; font-weight: bold; background-color: #E4E7EC;height: 24px; line-height:24px;">
			<div style="float:left;padding:7px 5px 0 5px ;"><img src="${ctx}/resources/images/common/down_black.gif"></img></div>
			<div style="float:left;">商家</div>
			</td>
		</tr>
		<s:iterator value="storeMap" id="column" status="status">
			<s:if test="#column.value.size > 0">
			<tr id="group_<s:property value='#column.key'/>" class="group"> 
				<td colspan="9" class="group-title" style="padding-left: 20px;">
					<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisShopType()" value="key"/>(<s:property value='#column.value.size' />)
				</td>
			</tr>
			<s:iterator value="#column.value" status="s">
				<tr class="mainTr" group="group_<s:property value='#column.key'/>" bisTenantId="${id}" shopName="${name}">
					<td style="padding-left: 20px;">
						<div class="ellipsisDiv_full" >
						${name}
						</div>
					</td>
					<td>&nbsp;${area}</td>
					<td>&nbsp;${accumulateMust}</td>
					<td>&nbsp;${accumulateFact}</td>
					<td>&nbsp;
                        <%
                            try {
                                BigDecimal accumulateMust = new BigDecimal(request.getAttribute("accumulateMust") == null ? "0" : request.getAttribute("accumulateMust").toString().trim());
                                BigDecimal accumulateFact = new BigDecimal(request.getAttribute("accumulateFact") == null ? "0" : request.getAttribute("accumulateFact").toString().trim());
                                BigDecimal accumulateCount= accumulateMust.subtract(accumulateFact).multiply(new BigDecimal("100")).divide(accumulateMust,2, RoundingMode.HALF_UP);
                                request.setAttribute("accumulateCount", accumulateCount.toString());
								%>${accumulateCount}%<%
                            } catch (Exception ex) {
                                request.setAttribute("accumulateCount", 0);
                            }
 
                        %><!--${accumulateRate}-->
                    </td>
					<td>&nbsp;${accumulateMust-accumulateFact}</td>
					<td>
						<s:if test="#remindLevel == one">
						<span style="color: red;">一级提醒</span>
						</s:if>
						<s:elseif test="#remindLevel == two">
						<span style="color: yellow;">二级提醒</span>
						</s:elseif>
						<s:else>
						<span>三级提醒</span>
						</s:else>
					</td>
					<td>&nbsp;${arrearsAge}</td>
					<td>&nbsp;${earnestMoney}</td>
				</tr>
			</s:iterator>
			</s:if>
		</s:iterator>
		
		<tr>
			<td colspan="9" style="font-size: 14px; cursor: default; font-weight: bold; background-color: #E4E7EC;height: 24px; line-height:24px;">
			<div style="float:left;padding:7px 5px 0 5px ;"><img src="${ctx}/resources/images/common/down_black.gif"/></div>
			<div style="float:left;">公寓</div>
			</td>
		</tr>
		<s:iterator value="flatList" status="s">
		<tr class="mainTr" >
			<td style="padding-left: 20px;">&nbsp;${name}</td>
			<td>&nbsp;${area}</td>
			<td>&nbsp;${accumulateMust}</td>
			<td>&nbsp;${accumulateFact}</td>
			<td>&nbsp;${accumulateRate}%</td>
			<td>&nbsp;${accumulateArrears}</td>
			<td>
				<s:if test="#remindLevel == one">
				<span style="color: red;">一级提醒</span>
				</s:if>
				<s:elseif test="#remindLevel == two">
				<span style="color: yellow;">二级提醒</span>
				</s:elseif>
				<s:else>
				<span>三级提醒</span>
				</s:else>
			</td>
			<td>&nbsp;${arrearsAge}</td>
			<td>&nbsp;${earnestMoney}</td>
		</tr>
		</s:iterator>
		<tr>
			<td colspan="9" style="font-size: 14px; cursor: default; font-weight: bold; background-color: #E4E7EC;height: 24px; line-height:24px;">
			<div style="float:left;padding:7px 5px 0 5px ;"><img src="${ctx}/resources/images/common/down_black.gif"/></div>
			<div style="float:left;">多经</div>
			</td>
		</tr>
	</tbody>
</table>

<script type="text/javascript">
$(function(){
	bindTblEv();
});

function bindTblEv() {
	$('#result_table tbody tr.group').toggle(function(){
		var id = $(this).attr('id');
		$('.mainTr[group="'+id+'"]').hide();
	},function(){
		var id = $(this).attr('id');
		$('.mainTr[group="'+id+'"]').show();
	});
	$('#result_table tbody tr.mainTr').bind('click',function(){
		var id = $(this).attr('bisTenantId');
		var name = $(this).attr('shopName');
		//跳转至收费明细页面：欠费维度
		var url="${ctx}/bis/bis-manage!layout.action?ifFromReport=true&module=3&bisTenantId="+id+'&currDetailName='+name+'&dimension=3'+'&bisProjectId='+$("#bisProjectId").val();
		if(null!=parent.TabUtils){
			parent.TabUtils.newTab("bis-manage-layout","费用明细",url,true);
		}else{
			window.open(url);
		}
	});
}
</script>