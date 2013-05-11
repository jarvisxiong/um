<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisCont.css" />

<div id='divContentRef' style='padding: 10px; font-size:14px; color:#5A5A5A; line-height:26px;'>
<table class="tb_noborder" style="margin-top:10px; line-height:26px;">
	<col width="20"/>
	<col />
	<s:if test="contSmallTypeCd==1">
		<tr>
			<td colspan="2" style="color: red;">租赁合同关键条款填写参考：</td>
		</tr>
		<tr>
			<td></td>
			<td>1、填写月租金方式（保底、扣点、取高）</td>
		</tr>
		<tr>
			<td></td>
			<td>2、填写租金支付周期（月付、季付、年付）</td>
		</tr>
		<tr>
			<td></td>
			<td>3、填写租金单价、计租面积、租金总价</td>
		</tr>
		<tr>
			<td></td>
			<td>4、填写每月应收日期 </td>
		</tr>
		<tr>
			<td></td>
			<td>5、填写有否特殊条款</td>
		</tr>
		
		<tr>
			<td colspan="2" style="color: red;">例如：</td>
		</tr>
		<tr>
			<td></td>
			<td>1、月租金采用保底取高方式</td>
		</tr>
		<tr>
			<td></td>
			<td>2、租金支付周期为年付</td>
		</tr>
		<tr>
			<td></td>
			<td>3、租金单价=30元/m2，计租面积=100m2，租金总价=3000元/月</td>
		</tr>
		<tr>
			<td></td>
			<td>4、每月最晚缴款日为15号</td>
		</tr>
		<tr>
			<td></td>
			<td>5、前三年租金不变，从第四年开始租金每年上涨8%</td>
		</tr>
	</s:if>
	<s:elseif test="contSmallTypeCd==3 || contSmallTypeCd==6">
		<tr>
			<td colspan="2" style="color: red;">物业合同关键条款填写参考：</td>
		</tr>
		<tr>
			<td></td>
			<td>1、填写物业费用是按物业费或综合管理费进行收取</td>
		</tr>
		<tr>
			<td></td>
			<td>2、填写物业单价、计价面积、物业总价</td>
		</tr>
		<tr>
			<td></td>
			<td>3、填写每月应收日期</td>
		</tr>
		<tr>
			<td></td>
			<td>4、填写有否特殊条款</td>
		</tr>
		
		<tr>
			<td colspan="2" style="color: red;">例如：</td>
		</tr>
		<tr>
			<td></td>
			<td>1、物业费用按综合管理费进行收取</td>
		</tr>
		<tr>
			<td></td>
			<td>2、综合管理费单价=18元/m2，计租面积=100m2，租金总价=1800元/月</td>
		</tr>
		<tr>
			<td></td>
			<td>3、每月最晚缴款日为25号</td>
		</tr>
		<tr>
			<td></td>
			<td>4、不参与水电公摊，该费用已包含在综合管理费中</td>
		</tr>
	</s:elseif>
	<s:else>
		<tr>
			<td colspan="2" style="color: red;">合同条款填写说明：</td>
		</tr>
		<tr>
			<td></td>
			<td>应填写与费用产生相关的合同条款信息，以便于审核应收款</td>
		</tr>
	</s:else>
</table>
</div>

<script type="text/javascript">
//div_content_ref
</script>
