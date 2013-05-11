<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%--
<div style="vertical-align: bottom;margin-right: 0px;float: right;margin-bottom: 5px;margin-top: 0px;">
	  <input onclick="cancelMessage();" value="关闭" id="btn_save" class="btn_green"></input>
</div>
<div style="clear: both;margin-bottom: 5px;" align="center"><font style="font-size: 14px;font-weight: bold;" color="red">错误信息列表</font></div>
 --%>
 <form id="validateTbForm" action="${ctx}/bid/bid-excel-imp!unvalidateData.action" method="post" accept-charset="utf-8">
	<div style="overflow: scroll;">
		<div style="padding-left: 10px;">
			<pre><font color="red">仅提供前50条未验证通过提示，以供参考修改！</font></pre>
		</div>
		<table class="bidTable" cellpadding="0" cellspacing="0" border="0">
			<colgroup>
				<col width="35"/>
				<col/>
				<col width="180"/>
				<col/>			
			</colgroup>
			<TR>
			<td style="text-align: left;padding-left: 5px;">序号</td>
			<Th style="text-align: left;padding-left: 5px;">表名</Th>
			<th style="text-align: left;padding-left: 5px;">信息定位</th>
			<th style="text-align: left;padding-left: 5px;">错误信息</th>
			</TR>
			<s:iterator status="status" value="voPage.result" var="rs">
			<tr>
			<td align="center" style="text-align: center;"><s:property value="#status.index+1"/></td>
			<td nowrap="nowrap">
			<s:if test="tableName=='BidDivisiton'">
			分部分项工程量清单与计价表
			</s:if>
			<s:elseif test="tableName=='BidDivisionSupRel'">
			分部分项工程量清单与计价表
			</s:elseif>
			<s:elseif test="tableName=='BidMeasureSupRel'">
			措施项目清单与计价表(一)
			</s:elseif>
			<s:elseif test="tableName=='BidMeasureSupRel2'">
			措施项目清单与计价表(二)
			</s:elseif>
			<s:elseif test="tableName=='BidOtherItemRel'">
			其他项目清单与计价汇总表
			</s:elseif>
			<s:elseif test="tableName=='BidSporadicSupRel'">
			零星工程费用表
			</s:elseif>
			<s:elseif test="tableName=='BidFeesSupRel'">
			规费项目清单与计价表
			</s:elseif>
			<s:elseif test="tableName=='BidTaxsSupRel'">
			税金项目清单与计价表
			</s:elseif>
			<s:elseif test="tableName=='BidSupOtherRel'">
			标段其他费用
			</s:elseif>
			<s:else>
			增添项表
			</s:else>
			</td>
			<td nowrap="nowrap" style="text-align: center;">			
				<s:if test="tableName=='BidDivisiton'">
					项目编号:${f2}
				</s:if>
				<s:elseif test="tableName=='BidDivisionSupRel'">
					项目编号:${f2}
				</s:elseif>	
				<s:else>
					EXCEL序号:${rowNo}
				</s:else>
			</td>
			
			<td nowrap="nowrap" title="${validDesc}">
			${validDesc}
			</td>	
			</tr>
			</s:iterator>
		</table>
		
	</div>
 </form> 