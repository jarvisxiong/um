<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>


<style type="text/css">
.table_pager {
	float: right;
	margin: 5px 0;
}
.table_pager input{width:24px;height:24px;border:1px solid #ccc;}
#pageTo{width:24px;height:22px;}
</style>
	
<script type="text/javascript">

	</script>
<div id="tableDiv">
	<table class="content_table" id="editTable" style="width: expression(this.parentNode.offsetHeight >this.parentNode.scrollHeight ? '100%' :parseInt(this.parentNode.offsetWidth - 18) + 'px');">
			<tr class="header">
				<th nowrap="nowrap" align="left" style="cursor: pointer;" onclick="">
					网批编号
				</th>
				<th nowrap="nowrap" align="left" >
					商家名称
				</th>
				<th nowrap="nowrap" align="left" >
					合同编号
				</th>				
				<th nowrap="nowrap" align="left" class="pd-chill-tip" >说明</th>
			</tr>
			<s:iterator value="page.result" status="#st">
			<tr class="mainTr">
				<td>${ resApproveInfo.approveCd }${resApproveInfo.serialNo }</td>
				<td><s:property value="%{getShopName(bisCont.bisContId)}"/></td>
				<td>${bisCont.contNo }</td>
				<td>${remark }</td>
			</tr>
			</s:iterator>
	</table> 
	<div class="table_pager">
		<p:page />
	</div>
</div>

