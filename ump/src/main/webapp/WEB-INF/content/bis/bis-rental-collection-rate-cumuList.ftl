<#assign security=JspTaglibs["/WEB-INF/security.tld"]>
<#macro modival val1 val2>
	<#if (val2 == null||val2 == ""||val2 == 0||val1==0)>
		0
	<#else>
		<#assign p = val1?number*100/val2?number />
		${p?string("0.00")}%
	</#if>
</#macro>
<#if rateProjects.isEmpty()>
	<div class="promptDiv" > 你还没有添加本月的租费收缴信息！</div>
<script type="text/javascript">
 	status = -1;
 </script>
<#else>
	<tr>
		<th class="pd-chill-tip" align="center" colspan="2">宝龙商业</th>
		<td class="pd-chill-tip" align="right">&nbsp;</td>
		<td class="pd-chill-tip" align="right">&nbsp;</td>
		<td class="pd-chill-tip" align="right">&nbsp;</td>
		<td class="pd-chill-tip" align="right">&nbsp;</td>
	</tr>
	<#list rateProjects.keySet() as m>
		<#if m=="0000">
			<#assign tdhtml="<td class='beijing' align='center' style='vertical-align:middle;' rowspan='${rateProjects.get(m).size()}'>老项目</td>" >
		<#else>
			<#assign tdhtml="<td class='beijing' align='center' style='vertical-align:middle;' rowspan='${rateProjects.get(m).size()}'>${m}开业</td>" >
		</#if>
		
		<#list rateProjects.get(m) as p>
			<#assign rentalRates=rentalCollectionRateUtils.get(p.bisProjectId)>
        <#if rentalCollectionRateUtils.get(p.bisProjectId)?exists>
			<tr class="editReport">
				${tdhtml}
				<td class="beijing pd-chill-tip" pId=${p.bisProjectId} align="left" title="${p.city}"><nobr>${p.city}</nobr></td>
				<#if rentalRates?exists>
				    <td class="pd-chill-tip" align="right" ys="${rentalRates.rentalRentYs}" ss="${rentalRates.rentalRentSs}"><@modival val1="${rentalRates.rentalRentSs}" val2="${rentalRates.rentalRentYs}" /></td>
				    <td class="pd-chill-tip" align="right" ys="${rentalRates.rentalPropertyYs}" ss="${rentalRates.rentalPropertySs}"><@modival val1="${rentalRates.rentalPropertySs}" val2="${rentalRates.rentalPropertyYs}" /></td>
				    <td class="pd-chill-tip" align="right" ys="${rentalRates.rentalRentYsCur}" ss="${rentalRates.rentalRentSsCur}"><@modival val1="${rentalRates.rentalRentSsCur}" val2="${rentalRates.rentalRentYsCur}" /></td>
				    <td class="pd-chill-tip" align="right" ys="${rentalRates.rentalPropertyYsCur}" ss="${rentalRates.rentalPropertySsCur}"><@modival val1="${rentalRates.rentalPropertySsCur}" val2="${rentalRates.rentalPropertyYsCur}" /></td>
                   	</#if>
			</tr>
			<#assign tdhtml="">
        </#if>
		</#list>
	</#list>
		
</#if>
	