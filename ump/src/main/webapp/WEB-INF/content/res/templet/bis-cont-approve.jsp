<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>

<%-- 商业合同入库审批表 (租赁)--%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">

<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>

<form action="res-approve-info!save.action" method="post" id="templetForm" class="bisContApprove">
	<%@ include file="template-var.jsp"%>
	<s:set var="creditItemCount"><s:property value="templateBean.creditProperties.size()"/></s:set>
	<s:set var="addiItemCount"><s:property value="templateBean.addiProperties.size()"/></s:set>
	
	<div style="background-color: #FEE3CD; color: #5A5A5A; line-height: 22px; margin: 10px 0; padding: 8px 0; text-align: left; width: 95%;">
	<div style="padding-left: 10px;">标准合同范本下载：</div>
	<table class="tb_noborder">
		<col />
		<tr>
		<td>
		<ul style="list-style:none; padding-left: 60px;">
			<li style="float:left; padding-right:20px; color: #0693e3; width:250px;">
				<s:url id="downBidQuestionnaire" action="download" namespace="/app" includeParams="none" >
			  	  <s:param name="fileName">BisContTemplate1.doc</s:param>
			  	  <s:param name="realFileName">租赁合同（开发商使用-固定租金-2011版）.doc</s:param>
			  	  <s:param name="bizModuleCd">resDownload</s:param>
				</s:url>
				<a href="${downBidQuestionnaire}">租赁合同（开发商使用-固定租金-2011版）</a>
			</li>
			<li style="float:left; padding-right:20px; color: #0693e3;">
				<s:url id="downBidQuestionnaire" action="download" namespace="/app" includeParams="none" >
			  	  <s:param name="fileName">BisContTemplate3.doc</s:param>
			  	  <s:param name="realFileName">宝龙商业集团主力店物业服务合同(签批版100715PL).doc</s:param>
			  	  <s:param name="bizModuleCd">resDownload</s:param>
				</s:url>
				<a href="${downBidQuestionnaire}">宝龙商业集团主力店物业服务合同(签批版100715PL)</a>
			</li>
			<li style="float:left; padding-right:20px; color: #0693e3; width:250px;">
				<s:url id="downBidQuestionnaire" action="download" namespace="/app" includeParams="none" >
			  	  <s:param name="fileName">BisContTemplate2.doc</s:param>
			  	  <s:param name="realFileName">租赁合同（开发商使用-扣点租金-2011版）.doc</s:param>
			  	  <s:param name="bizModuleCd">resDownload</s:param>
				</s:url>
				<a href="${downBidQuestionnaire}">租赁合同（开发商使用-扣点租金-2011版）</a>
			</li>
			<li style="float:left; padding-right:20px; color: #0693e3;">
				<s:url id="downBidQuestionnaire" action="download" namespace="/app" includeParams="none" >
			  	  <s:param name="fileName">BisContTemplate4.doc</s:param>
			  	  <s:param name="realFileName">自持物业小商户物业管理服务协议（签批版）.doc</s:param>
			  	  <s:param name="bizModuleCd">resDownload</s:param>
				</s:url>
				<a href="${downBidQuestionnaire}">自持物业小商户物业管理服务协议（签批版）</a>
			</li>
		</ul>
		</td>
		</tr>
	</table>
	</div>
	<table class="mainTable">
		<col width="100"/>
		<col width="100"/>
		<col />
		<col width="100"/>
		<col />
		<%-- 
		<tr>
			<td class="td_title">附件下载</td>
			<td colspan="4">
			<ul style="list-style:none; padding-left:5px;">
				<li style="float:left; padding-right:20px; color: #0693e3; width:250px;">
					<s:url id="downBidQuestionnaire" action="download" namespace="/app" includeParams="none" >
				  	  <s:param name="fileName">BisContTemplate1.doc</s:param>
				  	  <s:param name="realFileName">租赁合同（开发商使用-固定租金-2011版）.doc</s:param>
				  	  <s:param name="bizModuleCd">resDownload</s:param>
					</s:url>
					<a href="${downBidQuestionnaire}">租赁合同（开发商使用-固定租金-2011版）</a>
				</li>
				<li style="float:left; padding-right:20px; color: #0693e3;">
					<s:url id="downBidQuestionnaire" action="download" namespace="/app" includeParams="none" >
				  	  <s:param name="fileName">BisContTemplate3.doc</s:param>
				  	  <s:param name="realFileName">宝龙商业集团主力店物业服务合同(签批版100715PL).doc</s:param>
				  	  <s:param name="bizModuleCd">resDownload</s:param>
					</s:url>
					<a href="${downBidQuestionnaire}">宝龙商业集团主力店物业服务合同(签批版100715PL)</a>
				</li>
				<li style="float:left; padding-right:20px; color: #0693e3; width:250px;">
					<s:url id="downBidQuestionnaire" action="download" namespace="/app" includeParams="none" >
				  	  <s:param name="fileName">BisContTemplate2.doc</s:param>
				  	  <s:param name="realFileName">租赁合同（开发商使用-扣点租金-2011版）.doc</s:param>
				  	  <s:param name="bizModuleCd">resDownload</s:param>
					</s:url>
					<a href="${downBidQuestionnaire}">租赁合同（开发商使用-扣点租金-2011版）</a>
				</li>
				<li style="float:left; padding-right:20px; color: #0693e3;">
					<s:url id="downBidQuestionnaire" action="download" namespace="/app" includeParams="none" >
				  	  <s:param name="fileName">BisContTemplate4.doc</s:param>
				  	  <s:param name="realFileName">自持物业小商户物业管理服务协议（签批版）.doc</s:param>
				  	  <s:param name="bizModuleCd">resDownload</s:param>
					</s:url>
					<a href="${downBidQuestionnaire}">自持物业小商户物业管理服务协议（签批版）</a>
				</li>
			</ul>
			</td>
		</tr>
		--%>
		<tr>
			<td class="td_title">项目名称</td>
			<td colspan="4">
				<input id="bisProjectName" class="inputBorder" validate="required" type="text" name="templateBean.bisProjectName" value="${templateBean.bisProjectName}" />
				<input id="bisProjectId" type="hidden" name="templateBean.bisProjectId" value="${templateBean.bisProjectId}" />
			</td>
		</tr>
		<tr>
			<td class="td_title">合同编号</td>
			<td colspan="4">
				<s:if test="statusCd==2">
					<span class="link" onclick="openContTask('${resApproveInfoId}');" title="查看商业合同">${templateBean.contNo}</span>
				</s:if>
				<s:else>
				<input class="inputBorder" validate="required" type="text" name="templateBean.contNo" value="${templateBean.contNo}" />
				</s:else>
			</td>
		</tr>
		<tr>
			<td rowspan="19" class="td_title">合同明细信息</td>
			<td class="td_title">品牌</td>
			<td>
				<input id="bisShopName" class="inputBorder" validate="required" type="text" name="templateBean.bisShopName" value="${templateBean.bisShopName}" />
				<input id="bisShopId" type="hidden" name="templateBean.bisShopId" value="${templateBean.bisShopId}" />
			</td>
			<td class="td_title">经销商名称</td>
			<td>
				<s:select id="bisShopConnId" cssClass="inputBorder" validate="required" list="{}" listKey="key" listValue="value" name="templateBean.bisShopConnId" ></s:select>
				<input id="bisShopConnName" type="hidden" name="templateBean.bisShopConnName" value="${templateBean.bisShopConnName}" />
			</td>
		</tr>
		<tr>
			<td class="td_title">商铺</td>
			<td colspan="3">
				<input id="bisStoreNos" class="inputBorder" validate="required" type="text" name="templateBean.bisStoreNos" value="${templateBean.bisStoreNos}" />
				<input id="bisStoreIds" type="hidden" name="templateBean.bisStoreIds" value="${templateBean.bisStoreIds}" />
			</td>
		</tr>
		<tr>
			<td class="td_title">楼栋名称</td>
			<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.buildingName" value="${templateBean.buildingName}" />
			</td>
			<td class="td_title">建筑面积</td>
			<td>
				<input id="square" class="inputBorder" type="text" name="templateBean.square" value="${templateBean.square}" readonly="readonly" />
			</td>
		</tr>
		<tr>
			<td class="td_title">套内面积</td>
			<td>
				<input id="innerSquare" class="inputBorder" type="text" name="templateBean.innerSquare" value="${templateBean.innerSquare}" readonly="readonly" />
			</td>
			<td class="td_title">计租面积</td>
			<td>
				<input id="showSquare" class="inputBorder" type="text" name="templateBean.showSquare" value="${templateBean.showSquare}" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">实际收费面积</td>
			<td>
				<input id="rentSquare" class="inputBorder" validate="required" type="text" name="templateBean.rentSquare" value="${templateBean.rentSquare}" onblur="formatVal($(this));"/>
			</td>
			<td class="td_title">产权性质</td>
			<td>
				<s:select cssClass="inputBorder" validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapPropertyRight()" listKey="key" listValue="value" name="templateBean.equityNature"></s:select>
			</td>
		</tr>
		<tr>
			<td class="td_title">合同开始日期</td>
			<td>
				<input id="contStartDate" class="inputBorder Wdate" validate="required" type="text" name="templateBean.contStartDate" value='${templateBean.contStartDate}' onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'contEndDate\')}'})"/>
			</td>
			<td class="td_title">合同结束日期</td>
			<td>
				<input id="contEndDate" class="inputBorder Wdate" validate="required" type="text" name="templateBean.contEndDate" value='${templateBean.contEndDate}' onfocus="WdatePicker({minDate:'#F{$dp.$D(\'contStartDate\')}'})"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">签约时间</td>
			<td>
				<input class="inputBorder Wdate" validate="required" type="text" name="templateBean.signDate" value='${templateBean.signDate}' onfocus="WdatePicker()"/>
			</td>
			<td class="td_title">计租起始日</td>
			<td>
				<input id="rentDate" class="inputBorder Wdate" validate="required" type="text" name="templateBean.rentDate" value='${templateBean.rentDate}' onfocus="WdatePicker({minDate:'#F{$dp.$D(\'contStartDate\')}', maxDate:'#F{$dp.$D(\'contEndDate\')}'})"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">合同期限</td>
			<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.rentYears" value="${templateBean.rentYears}" />
			</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td class="td_title">业态</td>
			<td>
				<s:select cssClass="inputBorder" validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapStoreLayout()" listKey="key" listValue="value" name="templateBean.layoutCd"></s:select>
			</td>
			<td class="td_title">经营性质</td>
			<td>
				<s:select id="manageCd" cssClass="inputBorder" validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapShopManageType()" listKey="key" listValue="value" name="templateBean.manageCd"></s:select>
			</td>
		</tr>
		<tr>
			<td class="td_title">联系人</td>
			<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.connPeople" value="${templateBean.connPeople}" />
			</td>
			<td class="td_title">联系电话</td>
			<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.connTel" value="${templateBean.connTel}" />
			</td>
		</tr>
		<tr>
			<td class="td_title">联系传真</td>
			<td>
				<input class="inputBorder" type="text" name="templateBean.connFax" value="${templateBean.connFax}" />
			</td>
			<td class="td_title">联系人银行账号</td>
			<td>
				<input class="inputBorder" type="text" name="templateBean.connAccountNo" value="${templateBean.connAccountNo}" />
			</td>
		</tr>
		<tr>
			<td class="td_title">联系地址</td>
			<td colspan="3">
				<input class="inputBorder" type="text" name="templateBean.connAddr" value="${templateBean.connAddr}" />
			</td>
		</tr>
		<tr>
			<td class="td_title">合作方式</td>
			<td>
				<s:select cssClass="inputBorder" validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapBisCoopWay()" listKey="key" listValue="value" name="templateBean.coopWayCd"></s:select>
			</td>
			<td class="td_title">招商人员</td>
			<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.bisPeople" value="${templateBean.bisPeople}" />
			</td>
		</tr>
		<tr>
			<td class="td_title">甲方</td>
			<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.partyA" value="${templateBean.partyA}" />
			</td>
			<td class="td_title">乙方</td>
			<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.partyB" value="${templateBean.partyB}" />
			</td>
		</tr>
		<!-- Start Add for part C by zhuxj on 2012.3.31 -->
		<tr>
			<td class="td_title">丙方</td>
			<td colspan="3">
				<input class="inputBorder" type="text" name="templateBean.partyC" value="${templateBean.partyC}" />
			</td>
		</tr>
		<!-- End   Add for part C by zhuxj on 2012.3.31 -->
		<tr>
			<td class="td_title">支付方式</td>
			<td>
				<s:select cssClass="inputBorder" validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapPaymentMode()" listKey="key" listValue="value" name="templateBean.paymentModeCd"></s:select>
			</td>
			<td class="td_title">租金支付周期</td>
			<td>
				<s:select cssClass="inputBorder" validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapPayCycle()" listKey="key" listValue="value" name="templateBean.payCycleCd"></s:select>
			</td>
		</tr>
		<tr>
			<td class="td_title">免租期</td>
			<td>
				<input class="inputBorder" type="text" name="templateBean.freeRentPeriod" value="${templateBean.freeRentPeriod}" />
			</td>
			<td class="td_title">租金履约保证金</td>
			<td>
				<input class="inputBorder" type="text" name="templateBean.earnestMoney" value="${templateBean.earnestMoney}" onblur="formatVal($(this));"/>
			</td>
		</tr>
		<tr>
			<td class="td_title">
				签约租金单价&nbsp;&nbsp;<br>(元/平方.月)&nbsp;&nbsp;<br>
				<s:if test="#canEdit=='true'">
				<button type="button" class="btn_orange" onclick="addRentOneItem();" >新增</button>&nbsp;&nbsp;
				</s:if>
			</td>
			<td colspan="3">
				<table class="tb_noborder" id="tbRentOneItem" >
					<col width="80"/>
					<col />
					<col width="40"/>
					<tr>
						<td class="td_title">第一年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentUnit1" value="${templateBean.rentUnit1}" onblur="formatVal($(this));"/>
						</td>
						<td align="center"></td>
					</tr>
					<tr>
						<td class="td_title">第二年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentUnit2" value="${templateBean.rentUnit2}" onblur="formatVal($(this));"/>
						</td>
						<td align="center"></td>
					</tr>
					<tr>
						<td class="td_title">第三年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentUnit3" value="${templateBean.rentUnit3}" onblur="formatVal($(this));"/>
						</td>
						<td align="center"></td>
					</tr>
					<tr class="trRentOne" <s:if test="templateBean.rentUnit4==null || templateBean.rentUnit4=='' "> style="display: none;" </s:if> >
						<td class="td_title">第四年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentUnit4" value="${templateBean.rentUnit4}" onblur="formatVal($(this));"/>
						</td>
						<td align="center">
							<s:if test="#canEdit=='true'">
							<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
							</s:if>
						</td>
					</tr>
					<tr class="trRentOne" <s:if test="templateBean.rentUnit5==null || templateBean.rentUnit5=='' ">style="display: none;"</s:if> >
						<td class="td_title">第五年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentUnit5" value="${templateBean.rentUnit5}" onblur="formatVal($(this));"/>
						</td>
						<td align="center">
							<s:if test="#canEdit=='true'">
							<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
							</s:if>
						</td>
					</tr>
					<tr class="trRentOne" <s:if test="templateBean.rentUnit6==null || templateBean.rentUnit6=='' ">style="display: none;"</s:if> >
						<td class="td_title">第六年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentUnit6" value="${templateBean.rentUnit6}" onblur="formatVal($(this));"/>
						</td>
						<td align="center">
							<s:if test="#canEdit=='true'">
							<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
							</s:if>
						</td>
					</tr>
					<tr class="trRentOne" <s:if test="templateBean.rentUnit7==null || templateBean.rentUnit7=='' ">style="display: none;"</s:if> >
						<td class="td_title">第七年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentUnit7" value="${templateBean.rentUnit7}" onblur="formatVal($(this));"/>
						</td>
						<td align="center">
							<s:if test="#canEdit=='true'">
							<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
							</s:if>
						</td>
					</tr>
					<tr class="trRentOne" <s:if test="templateBean.rentUnit8==null || templateBean.rentUnit8=='' ">style="display: none;"</s:if> >
						<td class="td_title">第八年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentUnit8" value="${templateBean.rentUnit8}" onblur="formatVal($(this));"/>
						</td>
						<td align="center">
							<s:if test="#canEdit=='true'">
							<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
							</s:if>
						</td>
					</tr>
					<tr class="trRentOne" <s:if test="templateBean.rentUnit9==null || templateBean.rentUnit9=='' ">style="display: none;"</s:if> >
						<td class="td_title">第九年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentUnit9" value="${templateBean.rentUnit9}" onblur="formatVal($(this));"/>
						</td>
						<td align="center">
							<s:if test="#canEdit=='true'">
							<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
							</s:if>
						</td>
					</tr>
					<tr class="trRentOne" <s:if test="templateBean.rentUnit10==null || templateBean.rentUnit10=='' ">style="display: none;"</s:if> >
						<td class="td_title">第十年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentUnit10" value="${templateBean.rentUnit10}" onblur="formatVal($(this));"/>
						</td>
						<td align="center">
							<s:if test="#canEdit=='true'">
							<a href="javascript:void(0)" onclick="delRentOneItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
							</s:if>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="td_title">
				签约月租金&nbsp;&nbsp;<br>(元/月)&nbsp;&nbsp;<br>
				<s:if test="#canEdit=='true'">
				<button type="button" class="btn_orange" onclick="addRentTwoItem();" >新增</button>&nbsp;&nbsp;
				</s:if>
			</td>
			<td colspan="3">
				<table class="tb_noborder" id="tbRentTwoItem" >
					<col width="80"/>
					<col />
					<col width="40"/>
					<tr>
						<td class="td_title">第一年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentMonth1" value="${templateBean.rentMonth1}" onblur="formatVal($(this));"/>
						</td>
						<td align="center"></td>
					</tr>
					<tr>
						<td class="td_title">第二年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentMonth2" value="${templateBean.rentMonth2}" onblur="formatVal($(this));"/>
						</td>
						<td align="center"></td>
					</tr>
					<tr>
						<td class="td_title">第三年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentMonth3" value="${templateBean.rentMonth3}" onblur="formatVal($(this));"/>
						</td>
						<td align="center"></td>
					</tr>
					<tr class="trRentTwo" <s:if test="templateBean.rentMonth4==null || templateBean.rentMonth4=='' ">style="display: none;"</s:if> >
						<td class="td_title">第四年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentMonth4" value="${templateBean.rentMonth4}" onblur="formatVal($(this));"/>
						</td>
						<td align="center">
							<s:if test="#canEdit=='true'">
							<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
							</s:if>
						</td>
					</tr>
					<tr class="trRentTwo" <s:if test="templateBean.rentMonth5==null || templateBean.rentMonth5=='' ">style="display: none;"</s:if> >
						<td class="td_title">第五年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentMonth5" value="${templateBean.rentMonth5}" onblur="formatVal($(this));"/>
						</td>
						<td align="center">
							<s:if test="#canEdit=='true'">
							<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
							</s:if>
						</td>
					</tr>
					<tr class="trRentTwo" <s:if test="templateBean.rentMonth6==null || templateBean.rentMonth6=='' ">style="display: none;"</s:if> >
						<td class="td_title">第六年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentMonth6" value="${templateBean.rentMonth6}" onblur="formatVal($(this));"/>
						</td>
						<td align="center">
							<s:if test="#canEdit=='true'">
							<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
							</s:if>
						</td>
					</tr>
					<tr class="trRentTwo" <s:if test="templateBean.rentMonth7==null || templateBean.rentMonth7=='' ">style="display: none;"</s:if> >
						<td class="td_title">第七年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentMonth7" value="${templateBean.rentMonth7}" onblur="formatVal($(this));"/>
						</td>
						<td align="center">
							<s:if test="#canEdit=='true'">
							<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
							</s:if>
						</td>
					</tr>
					<tr class="trRentTwo" <s:if test="templateBean.rentMonth8==null || templateBean.rentMonth8=='' ">style="display: none;"</s:if> >
						<td class="td_title">第八年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentMonth8" value="${templateBean.rentMonth8}" />
						</td>
						<td align="center">
							<s:if test="#canEdit=='true'">
							<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
							</s:if>
						</td>
					</tr>
					<tr class="trRentTwo" <s:if test="templateBean.rentMonth9==null || templateBean.rentMonth9=='' ">style="display: none;"</s:if> >
						<td class="td_title">第九年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentMonth9" value="${templateBean.rentMonth9}" />
						</td>
						<td align="center">
							<s:if test="#canEdit=='true'">
							<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
							</s:if>
						</td>
					</tr>
					<tr class="trRentTwo" <s:if test="templateBean.rentMonth10==null || templateBean.rentMonth10=='' ">style="display: none;"</s:if> >
						<td class="td_title">第十年：</td>
						<td>
							<input class="inputBorder" type="text" name="templateBean.rentMonth10" value="${templateBean.rentMonth10}" />
						</td>
						<td align="center">
							<s:if test="#canEdit=='true'">
							<a href="javascript:void(0)" onclick="delRentTwoItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
							</s:if>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="td_title">
				合同内部批文
			</td>
			<td align="left" colspan="4">
				<textarea class="inputBorder contentTextArea" name="templateBean.contApproval">${templateBean.contApproval}</textarea>
			</td>
		</tr>
		<tr>
			<td class="td_title">
				关键合同条款
				<s:if test="#canEdit=='true'">
				<br/>
				<font style="color:red; ">具体填写内容</font>
				<button type="button" class="btn_blue" onclick="showContentReference();" >请参考</button>&nbsp;&nbsp;
				</s:if>
			</td>
			<td align="left" colspan="4">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contContent">${templateBean.contContent}</textarea>
			</td>
		</tr>
		<tr>
			<td class="td_title" rowspan="2">资料附件<br/>(请作为附件上传)</td>
			<td style="height:40px;" value="${templateBean.contApprovalId}">内部批文附件</td>
			<td>
			<s:if test="#canEdit=='true'">
			<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('合同内部批文附件','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','contApprovalId',event);"/>
			<input type="hidden" name="templateBean.contApprovalId" id="contApprovalId" value="${templateBean.contApprovalId}"/>
			</s:if>
			</td>
			<td colspan="2">
			<div id="show_contApprovalId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.contApprovalId}','contApprovalId','${canEdit}');
			</script>
			</td>
		</tr>
		<tr>
			<td style="height:40px;" validate="required" value="${templateBean.contContentId}">合同附件</td>
			<td>
			<s:if test="#canEdit=='true'">
			<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('合同附件','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','contContentId',event);"/>
			<input type="hidden" name="templateBean.contContentId" id="contContentId" value="${templateBean.contContentId}"/>
			</s:if>
			</td>
			<td colspan="2">
			<div id="show_contContentId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.contContentId}','contContentId','${canEdit}');
			</script>
			</td>
		</tr>
	</table>
	
	<table id="tableCreditItem" class="mainTable" style="display:none;margin-top:5px;">
		<col width="100"/>
		<col width="100"/>
		<col width="100"/>
		<col/>
		<col width="40"/>
		<tr>
			<td class="td_title" rowspan="5">资信资料</td>
			<td class="td_title">序号</td>
			<td colspan="2"><input class="inputBorder" type="text" name="templateBean.creditProperties[0].sequenceNo"/></td>
			<td><a href="javascript:void(0)" onclick="delCreditItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a></td>
		</tr>
		<tr>
			<td class="td_title">资料名称</td>
			<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.creditProperties[0].infoName"/></td>
		</tr>
		<tr>
			<td class="td_title">资料时限</td>
			<td colspan="3"><input class="inputBorder" type="text" name="templateBean.creditProperties[0].infoLimit"/></td>
		</tr>
		<tr>
			<td class="td_title">备注</td>
			<td colspan="3"><textarea class="inputBorder contentTextArea" name="templateBean.creditProperties[0].remark"></textarea></td>
		</tr>
		<tr>
			<td class="td_title" style="height:40px;">资信资料附件</td>
			<td>
			<input id="btn_contCreditId_0" type="button" value="上传附件" class="btn_green_65_20" style="border:none;" />
			<input id="contCreditId_0" type="hidden" name="templateBean.creditProperties[0].contCreditId" />
			</td>
			<td colspan="2">
			<div id="show_contCreditId_0"></div>
			</td>
		</tr>
	</table>
	<div id="creditItemDiv">
	<s:iterator value="templateBean.creditProperties" var="item" status="s">
	<table class="mainTable" style="margin-top: 5px;">
		<col width="100"/>
		<col width="100"/>
		<col width="100"/>
		<col/>
		<col width="40"/>
		<tr>
			<td rowspan="5">资信资料</td>
			<td>序号</td>
			<td colspan="2"><input class="inputBorder" type="text" name="templateBean.creditProperties[<s:property value="#s.index" />].sequenceNo" value="${item.sequenceNo}"/></td>
			<td>
				<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
				<a href="javascript:void(0)" onclick="delCreditItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
				</s:if>
			</td>
		</tr>
		<tr>
			<td>资料名称</td>
			<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.creditProperties[<s:property value="#s.index" />].infoName" value="${item.infoName}"/></td>
		</tr>
		<tr>
			<td>资料时限</td>
			<td colspan="3"><input class="inputBorder" type="text" name="templateBean.creditProperties[<s:property value="#s.index" />].infoLimit" value="${item.infoLimit}"/></td>
		</tr>
		<tr>
			<td>备注</td>
			<td colspan="3"><textarea class="inputBorder contentTextArea" name="templateBean.creditProperties[<s:property value="#s.index" />].remark">${item.remark}</textarea></td>			  
		</tr>
		<tr>
			<td class="td_title" style="height:40px;" value="${item.contCreditId}">资信资料附件</td>
			<td>
			<s:if test="#canEdit=='true'">
			<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('标准合同','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','contCreditId_<s:property value="#s.index" />',event);"/>
			<input id="contCreditId_<s:property value="#s.index" />" type="hidden" name="templateBean.creditProperties[<s:property value="#s.index" />].contCreditId" value="${item.contCreditId}"/>
			</s:if>
			</td>
			<td colspan="2">
			<div id="show_contCreditId_<s:property value="#s.index" />"></div>
			<script type="text/javascript">
			showUploadedFile('${item.contCreditId}','contCreditId_<s:property value="#s.index" />','${canEdit}');
			</script>
			</td>
		</tr>
	</table>
	</s:iterator>
	</div>
	
	<table id="tableAddiItem" class="mainTable" style="display:none;margin-top:5px;">
		<col width="100"/>
		<col width="100"/>
		<col width="100"/>
		<col/>
		<col width="40"/>
		<tr>
			<td class="td_title" rowspan="5">补充合同</td>
			<td class="td_title">名称</td>
			<td colspan="2"><input class="inputBorder" validate="required" type="text" name="templateBean.addiProperties[0].name"/></td>
			<td><a href="javascript:void(0)" onclick="delAddiItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a></td>
		</tr>
		<tr>
			<td class="td_title">签订日期</td>
			<td colspan="3"><input class="inputBorder Wdate" validate="required" type="text" name="templateBean.addiProperties[0].signDate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'contStartDate\')}'})"/></td>
		</tr>
		<tr>
			<td class="td_title">条款</td>
			<td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.addiProperties[0].content"></textarea></td>
		</tr>
		<tr>
			<td class="td_title">备注</td>
			<td colspan="3"><textarea class="inputBorder contentTextArea" name="templateBean.addiProperties[0].desc"></textarea></td>
		</tr>
		<tr>
			<td class="td_title" style="height:40px;" >补充合同附件</td>
			<td>
			<s:if test="#canEdit=='true'">
			<input id="btn_contAddiId_0" type="button" value="上传附件" class="btn_green_65_20" style="border:none;" />
			<input id="contAddiId_0" type="hidden" name="templateBean.addiProperties[0].contAddiId" />
			</s:if>
			</td>
			<td colspan="2">
			<div id="show_contAddiId_0"></div>
			</td>
		</tr>
	</table>
	<div id="addiItemDiv">
	<s:iterator value="templateBean.addiProperties" var="item" status="s">
	<table class="mainTable" style="margin-top: 5px;">
		<col width="100"/>
		<col width="100"/>
		<col width="100"/>
		<col/>
		<col width="40"/>
		<tr>
			<td rowspan="5">补充合同</td>
			<td>名称</td>
			<td colspan="2"><input class="inputBorder" validate="required" type="text" name="templateBean.addiProperties[<s:property value="#s.index" />].name" value="${item.name}"/></td>
			<td>
				<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
				<a href="javascript:void(0)" onclick="delAddiItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
				</s:if>
			</td>
		</tr>
		<tr>
			<td>签订日期</td>
			<td colspan="3"><input class="inputBorder Wdate" validate="required" type="text" name="templateBean.addiProperties[<s:property value="#s.index" />].signDate" value="${item.signDate}" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'contStartDate\')}'})"/></td>
		</tr>
		<tr>
			<td>条款</td>
			<td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.addiProperties[<s:property value="#s.index" />].content">${item.content}</textarea></td>
		</tr>
		<tr>
			<td>备注</td>
			<td colspan="3"><textarea class="inputBorder contentTextArea" name="templateBean.addiProperties[<s:property value="#s.index" />].desc">${item.desc}</textarea></td>			  
		</tr>
		<tr>
			<td class="td_title" style="height:40px;" value="${item.contAddiId}">补充合同附件</td>
			<td>
			<s:if test="#canEdit=='true'">
			<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('标准合同','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','contAddiId_<s:property value="#s.index" />',event);"/>
			<input id="contAddiId_<s:property value="#s.index" />" type="hidden" name="templateBean.addiProperties[<s:property value="#s.index" />].contAddiId" value="${item.contAddiId}"/>
			</s:if>
			</td>
			<td colspan="2">
			<div id="show_contAddiId_<s:property value="#s.index" />"></div>
			<script type="text/javascript">
			showUploadedFile('${item.contAddiId}','contAddiId_<s:property value="#s.index" />','${canEdit}');
			</script>
			</td>
		</tr>
	</table>
	</s:iterator>
	</div>
	
	<div style="height: 5px;"></div>
	
	<s:if test="statusCd==0 || statusCd==3">
	<input type="button" class="btn_blue" style="width:95px;height:55px;" value="增加资信资料" onclick="addCreditItem();" />
	<input type="button" class="btn_blue" style="width:95px;height:55px;" value="增加补充合同" onclick="addAddiItem();" />
	</s:if>
	<%@ include file="template-approver.jsp"%>
</form>

</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
	var creditTbApprover=$("#tableCreditItem").clone();
	$("#tableCreditItem").remove();
	var creditCloneCount = 0;
	var creditIndex=${creditItemCount};
	function addCreditItem(){
		var creditTbApprover_new=creditTbApprover.clone();
		creditCloneCount++;
		$(creditTbApprover_new).show();
		$(creditTbApprover_new).find(".inputBorder").each(function(i){
			this.name=this.name.replace('0',creditIndex);
			if(!isEmpty(this.id)) {
				this.id=this.id.replace('0',creditIndex);
			}
		});
		$(creditTbApprover_new).find(":input").each(function(i){
			this.name=this.name.replace('0',creditIndex);
			if(!isEmpty(this.id)) {
				this.id=this.id.replace('0',creditIndex);
			}
		});
		$(creditTbApprover_new).find(":div").each(function(i){
			if(!isEmpty(this.id)) {
				this.id=this.id.replace('0',creditIndex);
			}
		});
		$("#creditItemDiv").append(creditTbApprover_new);
		var domId='contCreditId_'+creditIndex;
		$("#btn_contCreditId_"+creditIndex).click(function() {
			showUploadSingleAttachDialog('资信资料','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile',domId);
		});
		creditIndex++;
	}
	function delCreditItem(dom){
		$(dom).parent().parent().parent().parent().remove();
	}
</script>
<script type="text/javascript">
	var addiTbApprover=$("#tableAddiItem").clone();
	$("#tableAddiItem").remove();
	var addiCloneCount = 0;
	var addiIndex=${addiItemCount};
	function addAddiItem(){
		var addiTbApprover_new=addiTbApprover.clone();
		addiCloneCount++;
		$(addiTbApprover_new).show();
		$(addiTbApprover_new).find(".inputBorder").each(function(i){
			this.name=this.name.replace('0',addiIndex);
			if(!isEmpty(this.id)) {
				this.id=this.id.replace('0',addiIndex);
			}
		});
		$(addiTbApprover_new).find(":input").each(function(i){
			this.name=this.name.replace('0',addiIndex);
			if(!isEmpty(this.id)) {
				this.id=this.id.replace('0',addiIndex);
			}
		});
		$(addiTbApprover_new).find(":div").each(function(i){
			if(!isEmpty(this.id)) {
				this.id=this.id.replace('0',addiIndex);
			}
		});
		$("#addiItemDiv").append(addiTbApprover_new);
		var domId='contAddiId_'+addiIndex;
		$("#btn_contAddiId_"+addiIndex).click(function() {
			showUploadSingleAttachDialog('补充合同','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile',domId);
		});
		addiIndex++;
	}
	function delAddiItem(dom){
		$(dom).parent().parent().parent().parent().remove();
	}
</script>

<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addCreditItem();
	addAddiItem();
</script>
</s:if>

<script type="text/javascript">
/**
 * 联动搜索经销商
 */
function getShopConn(){
	var shopId = $('#bisShopId').val();
	var subSele = $('#bisShopConnId');
	var shopConnId = '${templateBean.bisShopConnId}';
	subSele.empty();
	
	var url = _ctx+"/bis/bis-shop!getMapShopConn.action";
	$.post(url,{id:shopId},function(data){
		var data = eval('('+data+')');
		var len = 0;
		if(Boolean(data)){
			for(i in data)len++;
		}
		if(len != 1) {
			subSele.append('<option value="">--选择--</option>');
		}
		$.each(data,function(i,n){
			if(!isEmpty(shopConnId) && i == shopConnId) {
				var option = '<option value="'+i+'" selected>'+n+'</option>';
			} else {
				var option = '<option value="'+i+'">'+n+'</option>';
			}
			subSele.append(option);
		});
	});
};

/**
 *商铺选择(NEW)
 */ 
function doStoreSelect(ids,nos,inFloorType){
	if($('#'+nos).hasClass("search") && $('#'+nos).val().trim() == $('#'+nos).attr("searchtext")) {
		$('#'+nos).val('');
	}
	var title ="选择商铺";
	var width = 560;
	var floorType = '1';
	if(floorType=='2'){
		title = "选择公寓";
	} else if(floorType=='3') {
		title = "选择多经";
		width = 390;
	}
	var bisProjectId=$('#bisProjectId').val();
	if(isEmpty(bisProjectId) || bisProjectId == 'undefined') {
		alert("请先选择项目");
		//$('#bisProjectName').addClass("error");
		//$('#bisProjectName').focus();
		return false;
	}
	var idsTemp = $('#'+ids).val();
	var nosTemp = $('#'+nos).val();
	if(!isEmpty(idsTemp)) {
		idsTemp += ",";
	}
	if(!isEmpty(nosTemp)) {
		nosTemp += ",";
	}
	
	ymPrompt.win( {
		icoCls : "",
		autoClose:false,
		message : "<div id='divStoreSelect'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : width,
		height : 480,
		title : title,
		closeBtn:true,
		afterShow : function() {
			var url = _ctx+"/bis/bis-project!doStoreSelect.action";
			var data = {
				floorType:floorType,
				bisProjectId:bisProjectId,
				bisStoreIdsTemp:idsTemp,
				bisStoreNosTemp:nosTemp
			};
			$.post(url, data, function(result) {
				$("#divStoreSelect").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				$('#'+ids).val($('#bisStoreIdsTemp').val().substring(0, $('#bisStoreIdsTemp').val().length-1));
				$('#'+nos).val($('#bisStoreNosTemp').val().substring(0, $('#bisStoreNosTemp').val().length-1));
				
				if(floorType == "1") {
					var url = _ctx+"/bis/bis-cont!getStoreInfo.action";
					$.post(url,{bisStoreIds:$('#'+ids).val()}, function(data) {
						var data = eval('('+data+')');
						$.each(data,function(i,n){
							if($("#"+i)) {
								$("#"+i).val(n);
							}
							//计算公式中的面积
							//if($("#contSmallTypeCd").val()=="6" && i=="rentSquare") {
							//	$("#formulaSquare").val(n);
							//	calculatePrice();
							//}
						});
					});
				}
				
				//$('#'+nos).filter(".search").trigger("blur");
				ymPrompt.close();
			} else if(btn == 'clear') {
				$('#bisStoreIdsTemp').val('');
				$('#bisStoreNosTemp').val('');
				$("#ulStore li").removeClass("ctslt-nav-click");
				$("#ulStoreSelected li").remove();
			} else {
				//$('#'+nos).filter(".search").trigger("blur");
				ymPrompt.close();
			}
		},
		btn:[['确定','ok'],['退出','cancel'],['清空','clear']]
	});
}

/**
 * 合同条款参考
 */
function showContentReference() {
	ymPrompt.win( {
		icoCls : "",
		autoClose:true,
		allowSelect:true,
		allowRightMenu:true,
		message : "<div id='contentRefDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width  : 490,
		height : 410,
		title : "合同条款参考",
		closeBtn:true,
		afterShow : function() {
			var url = _ctx+"/bis/bis-cont!showContentRef.action";
			$.post(url, {contSmallTypeCd:1}, function(result) {
				$("#contentRefDiv").html(result);
			});
		},
		winPos : 'c',
		btn:[["关闭",'ok']]
	});
}

/**
 * 签约租金单价 
 */
function addRentOneItem() {
	if($("#tbRentOneItem").find(".trRentOne").size()==0) {
		alert("最多十年");
		return;
	}
	$("#tbRentOneItem").find(".trRentOne").eq(0).show();
	$("#tbRentOneItem").find(".trRentOne").eq(0).removeClass("trRentOne");
}
function delRentOneItem(dom) {
	$(dom).parent().parent().addClass("trRentOne");
	$(dom).parent().parent().find("input").val("");
	$(dom).parent().parent().hide();
}
/**
 * 签约月租金
 */
function addRentTwoItem() {
	if($("#tbRentTwoItem").find(".trRentTwo").size()==0) {
		alert("最多十年");
		return;
	}
	$("#tbRentTwoItem").find(".trRentTwo").eq(0).show();
	$("#tbRentTwoItem").find(".trRentTwo").eq(0).removeClass("trRentTwo");
}
function delRentTwoItem(dom) {
	$(dom).parent().parent().addClass("trRentTwo");
	$(dom).parent().parent().find("input").val("");
	$(dom).parent().parent().hide();
}

/**
 * 提示合同冲突信息
 */
function loadContInfo(id) {
	ymPrompt.errorInfo({
		message : "<div id='contInfoDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		title:"保存失败：合同冲突",
		closeBtn:false,
		width:390,
		height:280,
		afterShow : function() {
			var url = _ctx + "/bis/bis-cont!loadContInfo.action";
			$.post(url, {id:id}, function(result) {
				$("#contInfoDiv").html(result);
			});
		},
		btn:[["关闭",'close']]
	});
}

/**
 * 申请前验证合同是否冲突
 */
function beforeApply() {
	var flag = '1';
	var url = _ctx+"/bis/bis-cont!getExistsCont.action";
	var data = {
		bisProjectId:$("#bisProjectId").val(),
		contBigTypeCd:'1',
		contSmallTypeCd:'1',
		contStartDate:$("#contStartDate").val(),
		contEndDate:$("#contEndDate").val(),
		bisStoreIds:$("#bisStoreIds").val(),
		bisShopId:$("#bisShopId").val(),
		bisShopConnId:$("#bisShopConnId").val()
	};
	$("#bisShopConnName").val($("#bisShopConnId").find("option:selected").text());
	$.post(url,data,function(result){
		if(result != "success") {
			flag = '0';
			loadContInfo(result);
		}
	});
	return flag;
}
/**
 * 打开合同
 */
function openContTask(id){
	var url = _ctx+'/bis/bis-cont!input.action?resId='+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab("bisContMenu","合同明细",url,true);
}
</script>

<s:if test="templateBean.bisShopConnId != null">
<script type="text/javascript">
	getShopConn();
</script>
</s:if>

<s:if test="#canEdit=='true'">
<script type="text/javascript">
	$('#bisProjectName').onSelect({
		muti:false,
		callback:function(project){
			$("#bisProjectName").val(project.projectName);
			$("#bisProjectId").val(project.bisProjectId);
		}
	});
	$("#bisShopName").quickSearch("${ctx}/bis/bis-shop!quickSearch.action",['shopName','companyName','salesman','hqAddr'],{bisShopId:'bisShopId',shopName:'bisShopName',manageCd:'manageCd'},{},getShopConn);
	$("#bisStoreNos").click(function() {
		doStoreSelect('bisStoreIds','bisStoreNos');
	});
</script>
</s:if>
