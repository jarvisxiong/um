<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 商业合同入库审批表 --%>
<div class="billContent">
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<span class="wap_title">项目名称:</span>
		<span class="wap_value">${templateBean.bisProjectName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">合同编号:</span>
		<span class="wap_value">${templateBean.contNo}</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【合同明细信息】</span>
		<div class="div_row">
			<span class="wap_title">品牌:</span>
			<span class="wap_value">${templateBean.bisShopName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">经销商名称:</span>
			<span class="wap_value">${templateBean.bisShopConnName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">商铺:</span>
			<span class="wap_value">${templateBean.bisStoreNos}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">楼栋名称:</span>
			<span class="wap_value">${templateBean.buildingName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">建筑面积:</span>
			<span class="wap_value">${templateBean.square}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">套内面积:</span>
			<span class="wap_value">${templateBean.innerSquare}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">计租面积:</span>
			<span class="wap_value">${templateBean.showSquare}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">实际收费面积:</span>
			<span class="wap_value">${templateBean.rentSquare}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">合同开始日期:</span>
			<span class="wap_value">${templateBean.contStartDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">合同结束日期:</span>
			<span class="wap_value">${templateBean.contEndDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">签约时间:</span>
			<span class="wap_value">${templateBean.signDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">计租起始日:</span>
			<span class="wap_value">${templateBean.rentDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">合同期限:</span>
			<span class="wap_value">${templateBean.rentYears}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">产权性质:</span>
			<span class="wap_value"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPropertyRight()" value="templateBean.equityNature" /></span>
		</div>
		<div class="div_row">
			<span class="wap_title">业态:</span>
			<span class="wap_value"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapStoreLayout()" value="templateBean.layoutCd"/></span>
		</div>
		<div class="div_row">
			<span class="wap_title">经营性质:</span>
			<span class="wap_value"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" value="templateBean.manageCd"/></span>
		</div>
		<div class="div_row">
			<span class="wap_title">联系人:</span>
			<span class="wap_value">${templateBean.connPeople}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">联系电话:</span>
			<span class="wap_value">${templateBean.connTel}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">联系传真:</span>
			<span class="wap_value">${templateBean.connFax}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">联系人银行账号:</span>
			<span class="wap_value">${templateBean.connAccountNo}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">联系地址:</span>
			<span class="wap_value">${templateBean.connAddr}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">合作方式:</span>
			<span class="wap_value"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisCoopWay()" value="templateBean.coopWayCd"/></span>
		</div>
		<div class="div_row">
			<span class="wap_title">招商人员:</span>
			<span class="wap_value">${templateBean.bisPeople}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">甲方:</span>
			<span class="wap_value">${templateBean.partyA}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">乙方:</span>
			<span class="wap_value">${templateBean.partyB}</span>
		</div>
		<!-- Start Add for part C by zhuxj on 2012.3.31 -->
		<div class="div_row">
			<span class="wap_title">丙方:</span>
			<span class="wap_value">${templateBean.partyC}</span>
		</div>
		<!-- End   Add for part C by zhuxj on 2012.3.31 -->
		<div class="div_row">
			<span class="wap_title">支付方式:</span>
			<span class="wap_value"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" value="templateBean.paymentModeCd"/></span>
		</div>
		<div class="div_row">
			<span class="wap_title">租金支付周期:</span>
			<span class="wap_value"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapPayCycle()" value="templateBean.payCycleCd"/></span>
		</div>
		<div class="div_row">
			<span class="wap_title">免租期:</span>
			<span class="wap_value">${templateBean.freeRentPeriod}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">租金履约保证金:</span>
			<span class="wap_value">${templateBean.earnestMoney}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【签约租金单价(元/平方.月)】</span>
			<div class="div_row">
				<span class="wap_title">第一年:</span>
				<span class="wap_value">${templateBean.rentUnit1}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">第二年:</span>
				<span class="wap_value">${templateBean.rentUnit2}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">第三年:</span>
				<span class="wap_value">${templateBean.rentUnit3}</span>
			</div>
			<s:if test="templateBean.rentUnit4 != '' ">
			<div class="div_row">
				<span class="wap_title">第四年:</span>
				<span class="wap_value">${templateBean.rentUnit4}</span>
			</div>
			</s:if>
			<s:if test="templateBean.rentUnit5 != '' ">
			<div class="div_row">
				<span class="wap_title">第五年:</span>
				<span class="wap_value">${templateBean.rentUnit5}</span>
			</div>
			</s:if>
			<s:if test="templateBean.rentUnit6 != '' ">
			<div class="div_row">
				<span class="wap_title">第六年:</span>
				<span class="wap_value">${templateBean.rentUnit6}</span>
			</div>
			</s:if>
			<s:if test="templateBean.rentUnit7 != '' ">
			<div class="div_row">
				<span class="wap_title">第七年:</span>
				<span class="wap_value">${templateBean.rentUnit7}</span>
			</div>
			</s:if>
			<s:if test="templateBean.rentUnit8 != '' ">
			<div class="div_row">
				<span class="wap_title">第八年:</span>
				<span class="wap_value">${templateBean.rentUnit8}</span>
			</div>
			</s:if>
			<s:if test="templateBean.rentUnit9 != '' ">
			<div class="div_row">
				<span class="wap_title">第九年:</span>
				<span class="wap_value">${templateBean.rentUnit9}</span>
			</div>
			</s:if>
			<s:if test="templateBean.rentUnit10 != '' ">
			<div class="div_row">
				<span class="wap_title">第十年:</span>
				<span class="wap_value">${templateBean.rentUnit10}</span>
			</div>
			</s:if>
		</div>
		<div class="div_label">
			<span class="wap_label">【签约月租金(元/月)】</span>
			<div class="div_row">
				<span class="wap_title">第一年:</span>
				<span class="wap_value">${templateBean.rentMonth1}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">第二年:</span>
				<span class="wap_value">${templateBean.rentMonth2}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">第三年:</span>
				<span class="wap_value">${templateBean.rentMonth3}</span>
			</div>
			<s:if test="templateBean.rentMonth4 != '' ">
			<div class="div_row">
				<span class="wap_title">第四年:</span>
				<span class="wap_value">${templateBean.rentMonth4}</span>
			</div>
			</s:if>
			<s:if test="templateBean.rentMonth5 != '' ">
			<div class="div_row">
				<span class="wap_title">第五年:</span>
				<span class="wap_value">${templateBean.rentMonth5}</span>
			</div>
			</s:if>
			<s:if test="templateBean.rentMonth6 != '' ">
			<div class="div_row">
				<span class="wap_title">第六年:</span>
				<span class="wap_value">${templateBean.rentMonth6}</span>
			</div>
			</s:if>
			<s:if test="templateBean.rentMonth7 != '' ">
			<div class="div_row">
				<span class="wap_title">第七年:</span>
				<span class="wap_value">${templateBean.rentMonth7}</span>
			</div>
			</s:if>
			<s:if test="templateBean.rentMonth8 != '' ">
			<div class="div_row">
				<span class="wap_title">第八年:</span>
				<span class="wap_value">${templateBean.rentMonth8}</span>
			</div>
			</s:if>
			<s:if test="templateBean.rentMonth9 != '' ">
			<div class="div_row">
				<span class="wap_title">第九年:</span>
				<span class="wap_value">${templateBean.rentMonth9}</span>
			</div>
			</s:if>
			<s:if test="templateBean.rentMonth10 != '' ">
			<div class="div_row">
				<span class="wap_title">第十年:</span>
				<span class="wap_value">${templateBean.rentMonth10}</span>
			</div>
			</s:if>
		</div>
	</div>
	<div class="div_row">
		<span class="wap_title">合同内部批文:</span>
		<span class="wap_value">${templateBean.contApproval}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">关键合同条款:</span>
		<span class="wap_value">${templateBean.contContent}</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【资料附件】</span>
		<div class="div_row">
			<span class="wap_title">合同内部批文附件</span>
			<div id="show_contApprovalId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.contApprovalId}','contApprovalId','${canEdit}');
			</script>
		</div>
		<div class="div_row">
			<span class="wap_title">合同附件</span>
			<div id="show_contContentId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.contContentId}','contContentId','${canEdit}');
			</script>
		</div>
	</div>
	<s:iterator value="templateBean.creditProperties" var="item" status="s">
	<div class="div_label">
		<span class="wap_label">【资信资料<s:property value="#s.index+1" />】</span>
		<div class="div_row">
			<span class="wap_title">序号:</span>
			<span class="wap_value">${item.sequenceNo}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">资料名称:</span>
			<span class="wap_value">${item.infoName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">资料时限:</span>
			<span class="wap_value">${item.infoLimit}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">备注:</span>
			<span class="wap_value">${item.remark}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">资信资料附件</span>
			<div id="show_contCreditId_<s:property value="#s.index" />"></div>
			<script type="text/javascript">
			showUploadedFile('${item.contCreditId}','contCreditId_<s:property value="#s.index" />','${canEdit}');
			</script>
		</div>
	</div>
	</s:iterator>
	<s:iterator value="templateBean.addiProperties" var="item" status="s">
	<div class="div_label">
		<span class="wap_label">【补充合同<s:property value="#s.index+1" />】</span>
		<div class="div_row">
			<span class="wap_title">名称:</span>
			<span class="wap_value">${item.name}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">签订日期:</span>
			<span class="wap_value">${item.signDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">条款:</span>
			<span class="wap_value">${item.content}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">备注:</span>
			<span class="wap_value">${item.desc}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">补充合同附件</span>
			<div id="show_contAddiId_<s:property value="#s.index" />"></div>
			<script type="text/javascript">
			showUploadedFile('${item.contAddiId}','contAddiId_<s:property value="#s.index" />','${canEdit}');
			</script>
		</div>
	</div>
	</s:iterator>
</div>
