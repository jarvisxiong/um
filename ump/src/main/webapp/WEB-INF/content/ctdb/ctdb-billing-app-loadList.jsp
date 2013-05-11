<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<table class="ctdbTable" width="100%" style="margin-top: 5px;">
	<thead>
		<tr>
			<th title="项目名称" nowrap="nowrap"  style="background-image: url('');" width="15%">项目</th>
			<th title="合同名称" nowrap="nowrap" width="12%">合同</th>
			<th title="乙方"    nowrap="nowrap" >乙方</th>
			<th title="承包范围" nowrap="nowrap" width="3%">承包范围</th>
			<th title="合同性质" nowrap="nowrap" width="8%">合同性质</th>
			<th title="面积" 	  nowrap="nowrap" width="6%" style="text-align: right;padding-right:5px;">面积</th>
			<th title="结算价"  nowrap="nowrap" width="6%" style="text-align: right;padding-right:5px;">结算价</th>
			<th title="单位造价" nowrap="nowrap" width="6%" style="text-align: right;padding-right:5px;">单位造价</th>
			<th title="结算日期" nowrap="nowrap" width="7%" style="text-align: right;padding-right:5px;">结算日期</th>
			<th title="网批编号" nowrap="nowrap" width="5%" style="text-align: right;padding-right:5px;">网批编号</th>
			
		</tr>
	</thead>
	<tbody>
	<s:iterator value="page.result" var="billing">
		<tr>
			<td title="${projectName}" class="pd-chill-tip"><div class="partHide " style="min-width: 60px;max-width: 100px;">${projectName}</div></td>
			<td title="${ctName}" style="12%"  class="pd-chill-tip"><div class="partHide " >${ctName}</div></td>
			<td title="${impSupCd}" style="12%" class="pd-chill-tip"><div class="partHide " >${impSupCd}</div></td>
			<td title="${ctArea}"  class="pd-chill-tip"><div class="partHide"  style="width: 20px;">${ctArea}</div></td>
			<td title="${ctProperty}" width="8%"  class="pd-chill-tip"><div class="partHide " >${ctProperty}</div></td>
			<td title="${area}" class="pd-chill-tip" style="text-align: right;" ><div >${area}</div></td>
			<td title="${billingPrice}"  class="pd-chill-tip" style="text-align: right;"><div>${billingPrice}</div></td>
			<td title="${unitPrice}"  class="pd-chill-tip" style="text-align: right;"><div>${unitPrice}</div></td>
			<td title="<s:date name="#billing.billingDate" format="yy-MM-dd"/>"  class="pd-chill-tip"><div><s:date name="#billing.billingDate" format="yy-MM-dd"/></div></td>
			<td title="${approveCd}"  style="width: 40px;" class="pd-chill-tip">						
				<div  style="color:red;width: 60px;" href="javascript:void(0)" class="partHide"
					title="点击查看网批号：${approveCd}"
					class="pd-chill-tip"
					onclick="openResTask('${billingResCd}','${approveCd}','${importType}');">
					${approveCd}
				</div>
				
			</td>
		</tr>		
	</s:iterator>
	</tbody>
</table>
<div class="pageFooter">	
	<input type="hidden" name="projectName" value="<%=request.getParameter("projectName")==null?"":request.getParameter("projectName")%>"></input>
	<input type="hidden" name="ctName"  value="<%=request.getParameter("ctName")==null?"":request.getParameter("ctName")%>"></input>
	<input type="hidden" name="impSupName"  value="<%=request.getParameter("impSupName")==null?"":request.getParameter("impSupName")%>"></input>
	<input type="hidden" name="billingDate"  value="<%=request.getParameter("billingDate")==null?"":request.getParameter("billingDate")%>"></input>
	<p:page/>
</div>

