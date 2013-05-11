<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>合同台账</title>
	<%@ include file="/common/nocache.jsp" %>
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />

	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.example.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js" ></script>
	<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/customInput/customInput.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/cont/cont-ledger.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>

</head>
<body>
	<form action="${ctx}/cont/cont-ledger!save.action" method="post" id="cont-ledger-form">
	<div class="title_bar">
		<div>
			<div class="fLeft banTitle">合同台账<s:if test="enableFlg == 0"><span style="color:red">[合同已删除]</span></s:if></div>
			<div class="fLeft">
				<span>当前状态:</span>
				<span id="tipStatus" style="margin-left:5px;">新增</span>
				<span id="auditStat" style="margin-left:5px;"></span>
			</div>
		</div>
		
		<div class="fRight" id="operatePanel" <s:if test="enableFlg == 0"> style="display:none;" </s:if>>
		    <security:authorize ifAnyGranted="A_CONTRACT_INPUT,A_CONTRACT_ADMIN,A_CONTRACT_AUDITMOD,A_CONTRACT_FIN,A_CONT_BIS_INPUT,A_CONT_BIS_AUDIT,A_CONTRACT_AUDIT,A_CONT_HOTEL_INPUT,A_CONT_HOTEL_AUDIT,A_CONT_HOTEL_MANA,A_CONT_HOTEL_QUERY">
			<input type="button" id="btn_edit" class="btn_new btn_blue_new" onclick="editCont();" value="编辑"/>
			<s:if test="contLedgerId == null">
			<input type="button" id="btn_save" class="btn_new btn_green_new" onclick="saveCont('0')" value="保存"  style="display:none;"/>
			</s:if>
			<s:else>
			<input type="button" id="btn_save" class="btn_new btn_blue_new" onclick="saveCont('0')" value="保存"  style="display:none;"/>
			</s:else>
			<s:if test="contAuditStatus !=2">
			<s:if test="contLedgerId == null">
		  	<input type="button" id="btn_submit" class="btn_new btn_blue_new" onclick="saveCont('1')" value="提交"  style="display:none;"/>
		  	</s:if>
		  	<s:else>
		  	<input type="button" id="btn_submit" class="btn_new btn_green_new" onclick="saveCont('1')" value="提交"  style="display:none;"/>
		  	</s:else>
			</s:if>
			<input type="button" id="btn_cancel" class="btn_new btn_cancel_new" onclick="cancelCont();" value="取消"  style="display:none;"/>
			</security:authorize>
			<security:authorize ifAnyGranted="A_CONTRACT_AUDIT,A_CONT_BIS_AUDIT,A_CONTRACT_ADMIN,A_CONT_MANA,A_CONT_HOTEL_AUDIT">
			<s:if test="contAuditStatus==1">
			<input type="button" id="btn_audit" class="btn_new btn_blue_new" onclick="saveCont('2')" value="审核"/>
			</s:if>
			<s:if test="contAuditStatus==2">
			<input type="button" id="btn_reback" class="btn_new btn_red_new" onclick="saveCont('1')" value="驳回"/>
			</s:if>
			</security:authorize>
			
			<security:authorize ifAnyGranted="A_ADMIN">
			<s:if test="contLedgerId!=null">
			<input type="button" class="btn_new btn_red_new" onclick="delContLedger(this,'${contLedgerId}')" value="删除"/>
			<script type="text/javascript">
				function delContLedger(dom,id){
					if(!window.confirm('确认删除合同吗?')){
						return;
					}
					var url = '${ctx}/cont/cont-ledger!deleteCont.action';
					$.post(url, {contId: id}, function(result){
						if('success'== result){
							alert('删除成功!');
							$('#operatePanel').hide();
							$('input').attr('readonly','readonly');
						}else{
							alert(result);
						}
					});
				}
			</script>
			</s:if>
			</security:authorize>
			
			<input type="button" " id="btn_max" class="btn_new btn_green_new" onclick="window.open(location.href);" value="全屏"/>
			<input type="button" " id="btn_max" class="btn_new btn_green_new" onclick="window.location.href=location.href;" value="刷新"/>
		</div>
	</div>
	<div id="contDetailDiv" style="overflow:visible;">
		<input type="hidden" name="id" id="contLedgerId" value="${contLedgerId}"/>
		<input type="hidden" name="recordVersion" value="${recordVersion}"/>
		<input type="hidden" id="clear_atta_id" name="clearAttaId" value="${clearAttaId}"/>
		<input type="hidden" id="agree_atta_id" name="agreeAttaId" value="${agreeAttaId}"/>
		<input type="hidden" id="special_atta_id" name="specialAttaId" value="${specialAttaId}"/>
		<input type="hidden" id="repair_atta_id" name="repairAttaId" value="${repairAttaId}"/>
		<input type="hidden" id="ledgerType" name="ledgerType" value="${ledgerType}"/>
		<input type="hidden" id="contAudit_status"  name="contAuditStatus" value="${contAuditStatus}"/>
		<input type="hidden" id="projectCd"  name="projectCd" value="${projectCd}"/>
		<input type="hidden" id="contPay_length" value ="${fn:length(contPaies)}"/>
		<div style="padding:5px;color:#316685;">
			<div style="float:right;" title="${contNo}" class="pd-chill-tip">
			合同编号:<s:textfield name="contNo" id="cont-no" onblur="contNoValidate();" cssStyle="border:0 none;color:red;"/>
			</div>
			<div align="center" style="font-size: 14px;font-weight: bold;margin-right:200px;">
				<span id="project_name_label"> <p:code2name mapCodeName="projectMap" value="projectCd" /></span>
				&nbsp;/&nbsp;
				<span id="cont_type_label"><p:code2name mapCodeName="contTypeMap" value="contTypeCd" />项目&nbsp;/&nbsp;
				<s:if test="ledgerType==2">
					<p:code2name mapCodeName="mapTypeByBis" value="contTypeCd2" />
				</s:if>
				<s:elseif test="ledgerType==3">
					<p:code2name mapCodeName="mapTypeByHotel" value="contTypeCd2" />
				</s:elseif>
				<s:else>
					<p:code2name mapCodeName="mapTypeByEstate" value="contTypeCd2" />
				</s:else>
				</span>
				<span id="cont_type_input" style="display:none;">
					<s:if test="contTypeCd==''||(contLedgerId==null||contLedgerId=='')">
						<input type="text" id="cont_type_name"  onclick="selectContType();" value="<p:code2name mapCodeName="contTypeMap" value="contTypeCd"/>"/>
					</s:if>
						项目&nbsp;&nbsp;
					<s:hidden id="cont_type_cd" name="contTypeCd"/>
					<s:if test="ledgerType==2">
					<s:select list="mapTypeByBis" listKey="key" listValue="value" style="width:90px" name="contTypeCd2"/>
					</s:if>
					<s:elseif test="ledgerType==3">
					<s:select list="mapTypeByHotel" listKey="key" listValue="value" style="width:90px" name="contTypeCd2"/>
					</s:elseif>
					<s:else>
					<s:select list="mapTypeByEstate" listKey="key" listValue="value" name="contTypeCd2" style="width:90px"/>
					</s:else>
				</span>
				<span>类型</span>&nbsp;/&nbsp;
				<span id="cont_name_label">${contName}</span>
				<span id="cont_name_input" style="display:none;"><s:textfield id="cont_name" name="contName" cssStyle="width:200px;border:none;font-size: 14px;font-weight: bold;color:#316685;"/></span>
				<span>合同</span>
			</div>
		</div>
		<div style="float:left;width:240px;padding:5px;">
			<fieldset>
				<legend>合同价动态<img src="${ctx}/images/meetingRoom/pic_down_blue.gif"/></legend>
				<table class="cont-show-table">
					<tr>
						<td width="90">合同状态
						</td>
						<td width="140">
						   
							<div>
								<input type="radio"  id="contStatus_1" class="visa_1" name="contStatus" value="0" onclick="$('#status_date,#cont_status_date').val('');" 
									<c:if test="${contStatus == 0}">checked="checked"</c:if>
								/>
								<label for="contStatus_1">未完未结</label>
							</div>
							<div style="clear:both;">
								<input type="radio" id="contStatus_2" class="visa_1" name="contStatus" value="1" onclick="$('#status_date').val('');" 
									<c:if test="${contStatus == 1}">checked="checked"</c:if>
								/>
								<label for="contStatus_2">已完未结</label>
								<s:textfield id="status_date" cssStyle="width:80px;margin-left:25px;" onfocus="WdatePicker()" name="statusDate" />
							</div>
							<div style="clear:both;">
								<input type="radio" id="contStatus_3"  name="contStatus" class="visa_1" value="2" onclick="$('#cont_status_date').val('');" 
									<c:if test="${contStatus == 2}">checked="checked"</c:if>
								/>
								<label for="contStatus_3">已完已结</label>
								<s:textfield id="cont_status_date" cssStyle="width:80px;margin-left:25px;" onfocus="WdatePicker()" name="contStatusDate"/>
							</div>
						</td>
					</tr>
					<tr>
						<td>签证变更价累计</td>
						<td><s:textfield name="visaTotal" id="visa_total"  cssClass="visa_1" style="margin-left:10px;" readonly="readonly"/></td>
					</tr>
					<tr>
						<td>补充协议价累计(或标后核对)</td>
						<td><s:textfield name="agreeTotal" id="agree_total" cssClass="visa_1" style="margin-left:10px;"/></td>
					</tr>
					<tr>
						<td>已确认合同总价</td>
						<td><s:textfield name="updateTotal" id="update_total"  cssClass="visa_1" style="margin-left:10px;" /></td>
					</tr>
					<tr>
						<td>变更比率</td>
						<td><s:textfield name="updateRate" id="update_rate" cssClass="visa_1" style="margin-left:10px;"/>%</td>
					</tr>
					<tr>
						<td>结算价</td>
						<td><s:textfield name="clearPrice" style="margin-left:10px;" alt="amount"  onblur="updateContStatus('clear');" /></td>
					</tr>
					<tr>
						<td>含甲供料</td>
						<td><s:textfield name="clearPriceParta" style="margin-left:10px;" alt="amount" /></td>
					</tr>
					<tr>
						<td>结算附件</td>
						<td>
						 <div class="atta_readOnly" style="float:left;cursor: pointer;"  onclick="showAttachment('${clearAttaId}')">
							<s:if test="contAttaList.get(2)==0">
						      <img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
						    </s:if>
						    <s:else>
						      <img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" />
						     </s:else>	
								结算附件
						  </div>
						  <div class="atta_edit" style="float:left;cursor: pointer;display: none;"  onclick="alert('附件请上传至合同文本库中。');">
						  <s:if test="contAttaList.get(2)==0">
						    <img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
						   </s:if>
						   <s:else>
						     <img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" />
						   </s:else>
								结算附件
						  </div>
						  <s:if test="clearResApproveId!=null">
							<div onclick="showPageLink('${ctx}/res/res-approve-info.action?id=${clearResApproveId}','resApprove');" style="float:left;padding-left:10px;cursor: pointer; text-decoration: underline;">结算审批表</div>
					      </s:if>
						</td>
					</tr>
				</table>
				<div class="foot-ellipsis">......</div>
			</fieldset>
			<fieldset>
				<legend>工期动态<img src="${ctx}/images/meetingRoom/pic_down_blue.gif"/></legend>
				<table class="cont-show-table">
					<tr>
						<td width="90">计划开工日期</td>
						<td width="140"><s:textfield name="planBeginDate" onfocus="WdatePicker()" onblur="updateContStatus('plan');" />
						</td>
					</tr>
					<tr>
						<td>实际开工日期</td>
						<td><s:textfield name="realBeginDate" onfocus="WdatePicker()"  onblur="updateContStatus('real');" /></td>
					</tr>
					<tr>
						<td>计划竣工日期</td>
						<td><s:textfield name="planEndDate" cssClass="visa_1"/></td>
					</tr>
					<tr>
						<td>实际竣工日期</td>
						<td><s:textfield name="realEndDate" onfocus="WdatePicker()"  onblur="updateContStatus('realEnd');" /></td>
					</tr>
					<tr>
						<td>保修期起止日期</td>
						<td><s:textfield name="guarBeginDate" onfocus="WdatePicker()"/>至<br/> <s:textfield name="guarEndDate" onfocus="WdatePicker()"/></td>
					</tr>
					<tr>
						<td>保修协议</td>
						<td>
							<div class="atta_readOnly" align="center" style="float:left;cursor: pointer;"  onclick="showAttachment('${repairAttaId}')">
							<s:if test="contAttaList.get(3)==0">
						      <img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
						    </s:if>
						    <s:else>
						      <img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" />
						     </s:else>	
							</div>
							<div class="atta_edit" align="center" style="float:left;cursor: pointer;display: none;"  onclick="alert('附件请上传至合同文本库中。');">
							 <s:if test="contAttaList.get(3)==0">
						      <img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
						      </s:if>
						      <s:else>
						       <img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" />
						     </s:else>	
							</div>
						</td>
					</tr>
					<tr>
						<td>进度状态</td>
						<td>
							<div>
								<input type="radio" class="visa_1" id="procStatus_1" name="procStatus" value="0" onclick="$('#delay_day,#delay_desc').val('')" 
									<c:if test="${procStatus == 0}">checked="checked"</c:if>
								/>
								<label for="procStatus_1">未开工</label>
							</div>
							<div style="clear:both;">
								<input type="radio" id="procStatus_2" name="procStatus" class="visa_1" value="1" onclick="$('#delay_day,#delay_desc').val('');" 
									<c:if test="${procStatus == 1}">checked="checked"</c:if>
								/>
								<label for="procStatus_2">按期&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							</div>
							<div style="clear:both;">
								<input type="radio" id="procStatus_3" name="procStatus" class="visa_1" value="2" 
									<c:if test="${procStatus == 2}">checked="checked"</c:if>
								/>
								<label for="procStatus_3">滞后</label>
								<div style="position: relative;"><s:textfield id="delay_day" cssClass="visa_1" cssStyle="width:45px;margin-left:0;" name="delayDay"/>天</div>
								
							</div>
							<div style="clear:both;">
								<input type="radio" id="procStatus_4" name="procStatus"  class="visa_1" value="3" onclick="$('#delay_day').val('')" 
									<c:if test="${procStatus == 3}">checked="checked"</c:if>
								/>
								<label for="procStatus_4">延期竣工</label>
							</div>
							<div style="clear:both;">
								<input type="radio" id="procStatus_5" name="procStatus"  class="visa_1" value="4" onclick="$('#delay_day,#delay_desc').val('')" 
									<c:if test="${procStatus == 4}">checked="checked"</c:if>
								/>
								<label for="procStatus_5">已竣工</label>
							</div>
						</td>
					</tr>
					<tr id="delayDesc_tr">
						<td colspan="2">
							<div>延期/滞后说明</div>
						</td>
					</tr>
					<s:iterator  value="contDelaies">
					<tr>
					<td colspan="2">
					${remark }
					</td>
					</tr>	
					</s:iterator>
					<tr>
					<td colspan="2">
					<textarea id="delay_desc" style="width:225px;height:50px;border:1px solid #ccc;" name="contDelaies[${fn:length(contDelaies)}].remark"></textarea>
					</td>
					</tr>
				</table>
				<div class="foot-ellipsis">......</div>
			</fieldset>
		</div>
		<div id="rightDiv" style="margin-left:255px;padding:5px;">
			<fieldset>
				<legend >合同基本信息<img src="${ctx}/images/meetingRoom/pic_down_blue.gif"/></legend>
				<div id="cont_basic">
				<table class="cont-show-table">
					<tr>
						<td  style="width:90px;+width:15%;" >甲方</td>
						<td style="+width:35%;"><s:textfield name="partA"/></td>
						<td style="width:100px;+width:10%;">签约人/电话</td>
						<td style="+width:35%;"><s:textfield name="partATel"/></td>
					</tr>
					<tr>
						<td>乙方</td>
						<td><s:textfield name="partB"/></td>
						<td>签约人/电话</td>
						<td><s:textfield name="partBTel"/></td>
					</tr>
					<tr>
						<td>丙方</td>
						<td><s:textfield name="partC"/></td>
						<td>签约人/电话</td>
						<td><s:textfield name="partCTel"/></td>
					</tr>
					<tr>
						<td>实际供方</td>
						<td><s:textfield name="realProvName"/></td>
						<td>是否战略</td>
						<td><s:radio list="#{'1':'是','0':'否'}" name="strageFlg"></s:radio></td>
					</tr>
					<tr>
						<td>标段：</td>
						<td><s:textfield name="section"/></td>
						<td>工期(日历天)</td>
						<td><s:textfield name="period" alt="amount_2"/></td>
					</tr>
					<tr>
						<td>范围/数量</td>
						<td colspan="3"><s:textfield name="rangeNum" maxlength="50"/></td>
					</tr>
					<tr>
						<td>合同性质</td>
						<td colspan="3">
							<s:radio list="#{'0':'总价包干','1':'可调值总价包干','2':' 单价包干','3':'定额计价'}" name="contProperty"></s:radio>
						</td>
					</tr>
					<tr>
					 <td>目标成本(元)</td>
					 <td colspan="3"><s:textfield name="targetCost" title="单位为元" alt="amount"/></td>
					</tr>
					<tr>
						<td>合同总价(元)</td>
						<td colspan="3"><s:textfield name="totalPrice" id="total_price" onkeyup="calcUpdateTotal();" title="单位为元" alt="amount"/></td>
					</tr>
					<tr>
						<td>含甲供料(元)</td>
						<td colspan="3"><s:textfield name="totalPriceParta" id="total_price_parta"  title="单位为元" alt="amount"/></td>
					</tr>
					<tr>
						<td>合同约定下浮率</td>
						<td colspan="3">
							<input type="radio" name="downRateCheck" id="downRate-1" onclick="changeRate('downRate-1')" class="visa_1"
								<c:if test="${downRate==0}">checked="checked"</c:if>
							/>
							<label for="downRate-1"
							>无</label>
							<input type="radio" name="downRateCheck" id="downRate-2" onclick="changeRate('downRate-2')" class="visa_1"
								<c:if test="${downRate!=0}">checked="checked"</c:if>
							/>
							<label for="downRate-2"
							>有</label>，
							<input type="text" style="width:50px;padding-right:3px;text-align: right;" alt="amount" name="downRate" id="down_rate" value="${downRate}"/>  %
							&nbsp;(提示：系统默认勾选，若为0%则为无下浮率，否则为有下浮率)
						</td>
					</tr>
					<tr>
						<td>付款条件</td>
						<td colspan="3"><s:textfield name="payWay" maxlength="100"/></td>
					</tr>
					<tr>
						<td>进度付款比例</td>
						<td><s:textfield name="payPercent" cssClass="percent" alt="amount" style="width:80px;"/>&nbsp;%</td>
						<td>保修金比例</td>
						<td><s:textfield name="repairPercent" cssClass="percent" alt="amount" style="width:80px;"/>&nbsp;%</td>
					</tr>
					<tr>
						<td>履约保证金(元)</td>
						<td><s:textfield name="perfBond" alt="amount"/></td>
						<td>履约银行保函(元)</td>
						<td><s:textfield name="perfBank" alt="amount"/></td>
					</tr>
					<tr>
						<td>附件</td>
						<td colspan="3">
							<div class="atta_readOnly atta_audit" style="float:left;cursor: pointer;"  onclick="showAttachment('${agreeAttaId}')">
							   <s:if test="contAttaList.get(0)==0">
								<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
								</s:if>
								<s:else>
								<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" />
								</s:else>
								协议书
							</div>
							<div class="atta_edit atta_audit" style="float:left;cursor: pointer;display: none;"  onclick="alert('附件请上传至合同文本库中。');">
							   <s:if test="contAttaList.get(0)==0">
								<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
								</s:if>
								<s:else>
								<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" />
								</s:else>
								协议书
							</div>
							<div class="atta_readOnly atta_audit" style="float:left;margin-left:20px;cursor: pointer;" onclick="showAttachment('${specialAttaId}')">
								<s:if test="contAttaList.get(1)==0">
								<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
								</s:if>
								<s:else>
								<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" />
								</s:else>
								专用条款
							</div>
							<div class="atta_edit atta_audit" style="float:left;margin-left:20px;cursor: pointer;display: none;" onclick="alert('附件请上传至合同文本库中。');">
								<s:if test="contAttaList.get(1)==0">
								<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
								</s:if>
								<s:else>
								<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" />
								</s:else>
								专用条款
							</div>
					    	<div style="float:left;margin-left:50px;">
					    		<span style="display:none;">合同文本库:<input id="contractNo"  name="contractNo"  type="text"  value="${contractNo}" style="width: 100px;cursor: pointer;" onclick="getContById('${isstandard}','${contractTempletInfoId}','${contractTempletHisId}')"/></span>
						    	<span id="con_ctrl" <s:if test="contractNo!=null"> style="display:none"</s:if>>
						    		<span style="display:none;">合同库名称:</span>
						    		<input type="text" id="contractName" name="contractName" value="${contractName}" style="width: 100px; display:none;"/>
			    	                <input type="button" class="btn_new btn_green_new"  value="合同库"  onclick="doContTemplet();" style="width:60px; display:none;" title="点击查看合同库"/>
						    	</span>
					    		<s:if test="contractTempletInfoId!=null">合同文本库:
					    			<a id="contLink" class='link' href="javascript:void(0)" onclick="getContById('${isstandard}','${contractTempletInfoId}','${contractTempletHisId}')">
					    			${contractNo}<s:if test="isstandard==1"></s:if><s:else></s:else></a></s:if>
					    		<input type="hidden" id="contractTempletInfoId" name="contractTempletInfoId"  value="${contractTempletInfoId}"/>
					    		<input type="hidden" id="contractTempletHisId" name="contractTempletHisId"  value="${contractTempletHisId}"/>
					    	</div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="3">
							<s:if test="resApproveId!=null">
								<a 	title="点击打开定标审批表" 
									href="javascript:showPageLink('${ctx}/res/res-approve-info.action?id=${resApproveId}','resApprove');" 
									style="cursor: pointer;color:blue;font-weight:bolder;font-size: 15px;line-height: 25px;margin-top:5px;">
									定标审批表(网批查询号:&nbsp;${resDiplayNo}&nbsp;)
								</a>
					    	</s:if>
						</td>
					</tr>
				</table>
				</div>
				<div class="foot-ellipsis">......</div>
			</fieldset>
			<fieldset>
				<legend>签证变更动态<img src="${ctx}/images/meetingRoom/pic_down_blue.gif"/></legend>
				<div>
					<s:if test="contLedgerId != null">
						<security:authorize ifAnyGranted="A_CONTRACT_QUERYEXP,A_CONTRACT_INPUT,A_CONTRACT_AUDIT,A_CONTRACT_ADMIN">
						<div>
							<input type="button" class="fRight btn_new btn_green_new" value="导出签证变更历史" onclick="exportContVisaList('${contLedgerId}')" style="width:120px;"/>
						</div>
						</security:authorize>
					</s:if>
					
					<div style="margin-bottom: 3px;display: none;" id="addVisa_btn">
						<s:if test="contLedgerId == null">
							<input type="button" class="btn_new btn_blue_new" onclick="addVisa()" value="增加" />
						</s:if>
						<s:else>
							<input type="button" class="btn_new btn_green_new" onclick="addVisa()" value="增加" />
						</s:else>
					</div>
					<table class="cont-table" id="visaTable">
						<thead>
							<tr>
								<td width="35px;">序号</td>
								<td width="10%">签证编号</td>
								<td>变更内容</td>
								<td width="15%">变更原因</td>
								<td width="10%">预估费用(元)</td>
								<td width="15%">变更增减金额(元)</td>
								<td width="10%">附件(费用核定表)</td>
								<td>操作</td>
							</tr>
						</thead>
						<s:if test="contVisaList.size()>0">
							<tbody>
							<s:iterator value="contVisaList" status="s">
								<s:set name="index" value="#s.index"/>
								<tr class="pd-chill-tip" title='<s:date name="createdDate" format="yyyy-MM-dd HH:mm"/>'>
									<td align="center" <%-- 若网批"变更费用核定单"链接进入,则显示背景色 --%>
										<s:if test="contVisaUpdateId == contVisaId">
										style="background-color:#2D8BEF;color:white;"
										</s:if>
									>${s.index+1 }</td>
									<td class="pd-chill-tip" title="${visaUpdateNo}">
										<input type="hidden" name="contVisaUpdates[${index}].contVisaUpdateId" value="${contVisaUpdateId}"/>
										<input type="hidden" name="contVisaUpdates[${index}].recordVersion" value="${recordVersion}"/>
										<input type="text" name="contVisaUpdates[${index}].visaUpdateNo" value="${visaUpdateNo}" />
									</td>
									<td class="pd-chill-tip" title="${content}">
										<input type="text" name="contVisaUpdates[${index}].content" value="${content}"/>
									</td>
									<td>
										<span class="span_ready">
										 <p:code2name mapCodeName="mapVisaCause" value="cause" />
										</span>
										<span class="span_eidt" style="display:none;">
										  <select id="processStatus_${index}" name="contVisaUpdates[${index}].cause" style="width:80px">
						                       <option value=""></option>
						                       <option value="1" <c:if test="${contVisaUpdates[index].cause==1}">selected</c:if>>设计错漏</option>
						                       <option value="2" <c:if test="${contVisaUpdates[index].cause==2}">selected</c:if>>招商原因</option>
						                       <option value="3" <c:if test="${contVisaUpdates[index].cause==3}">selected</c:if>>施工原因</option>
						                       <option value="4" <c:if test="${contVisaUpdates[index].cause==4}">selected</c:if>>甲方原因</option>
						                       <option value="5" <c:if test="${contVisaUpdates[index].cause==5}">selected</c:if>>其它</option>
						                    </select>
										</span>
									</td>
									<td class="pd-chill-tip" title="${prepareFee}"><input type="text" name="contVisaUpdates[${index}].prepareFee"  alt="amount"  value="${prepareFee}" /></td>
									<td class="pd-chill-tip" title="${amountUpdate}"><input type="text" name="contVisaUpdates[${index}].amountUpdate" alt="amount"  value="${amountUpdate}" onkeyup="sumVisaAmout()"/></td>
									<td>
									  <s:if test="resApproveId!=null||approveCheckId!=null">
									     <s:if test="resApproveId!=null">
									      <div class="approve" onclick="showPageLink('${ctx}/res/res-approve-info.action?id=${resApproveId}','resApprove');" style="cursor: pointer; text-decoration: underline;">审批内容</div>
									     </s:if>
										  <s:if test="approveCheckId!=null">
										   <div class="approve" onclick="showPageLink('${ctx}/res/res-approve-info.action?id=${approveCheckId}','resApprove');" style="cursor: pointer; text-decoration: underline;">核定网批</div>
										  </s:if>
									   </s:if>
									   <s:else>
									    <s:if test="attaBizId!=null&&attaBizId!=''">
									    <img  class="atta_readOnly"  align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" onclick="showAttachment('${attaBizId}')"/>
									    <img  class="atta_edit" style="display: none;" align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" onclick="openAttachment(this,'${attaBizId}','atta_biz_id_${index}')"/>
									   </s:if>
								    	<s:else>
									     <img class="atta_readOnly" src="${ctx}/resources/images/common/atta.gif" onclick="showAttachment('${attaBizId}')"/>
									     <img class="atta_edit" style="display: none;" src="${ctx}/resources/images/common/atta.gif" onclick="openAttachment(this,'${attaBizId}','atta_biz_id_${index}')"/>
									   </s:else>
										<input type="hidden" id="atta_biz_id_${index}" name="contVisaUpdates[${index}].attaBizId" value="${attaBizId}" />
									  </s:else>
									</td>
									<td>
									 <s:if test="resApproveId==null||approveCheckId==null">
										 <security:authorize ifAnyGranted="A_CONTRACT_ADMIN">
										   <span onclick="delVisa(this,'${contVisaUpdateId}','${contLedgerId}')">删除</span>
										</security:authorize>
									</s:if>
									</td>
								</tr>
							</s:iterator>
							<tr>
							 <td colspan="5" align="center">合计</td>
							 <td>${visaTotal}</td>
							 <td colspan="2"></td>
							</tr>
							</tbody>
						</s:if>
					</table>
				</div>
				<div class="foot-ellipsis">......</div>
			</fieldset>
			<fieldset>
				<legend>补充协议动态(或标后核对)<img src="${ctx}/images/meetingRoom/pic_down_blue.gif"/></legend>
				<div>
					<div style="margin-bottom: 3px;display: none;" id="addAgree_btn">
						<s:if test="contLedgerId == null">
							<input type="button" class="btn_new btn_blue_new" onclick="addAgree()" value="增加" />
						</s:if>
						<s:else>
							<input type="button" class="btn_new btn_green_new" onclick="addAgree()" value="增加" />
						</s:else>
					</div>
					<table class="cont-table" id="addAgreementTable">
						<thead>
							<tr>
								<td width="35px;">序号</td>
								<td width="10%">编号</td><%--标后核对/补充协议 --%>
								<td >原因</td>
								<td width="15%">核定费用</td>
								<td width="15%">合同暂定价</td>
								<td width="15%">协议增减金额(元)</td>
								<td width="80">附件</td>
								<td width="60">操作</td>
							</tr>
						</thead>
						<s:if test="contAddList.size()>0">
						<tbody>
						<s:iterator value="contAddList" status="s">
							<s:set name="index" value="#s.index"/>
							<tr>
								<td align="center" <%-- 若网批"补充协议"链接进入,则显示背景色 --%>
									<s:if test="contAddAgreementId == contAddId">
									style="background-color:#2D8BEF;color:white;"
									</s:if>
								>${s.index+1 }</td>
								<td class="pd-chill-tip" title="${addAgreementNo}">
									<input type="hidden" name="contAddAgreements[${index}].contAddAgreementId" value="${contAddAgreementId}"/>
									<input type="hidden" name="contAddAgreements[${index}].recordVersion" value="${recordVersion}"/>
									<input type="text" name="contAddAgreements[${index}].addAgreementNo" value="${addAgreementNo}" />
								</td>
								<td class="pd-chill-tip" title="${content}"><input type="text" name="contAddAgreements[${index}].content"  value="${content}"/></td>
								<td class="pd-chill-tip" title="${groupCheckFee}"><input type="text" name="contAddAgreements[${index}].groupCheckFee" alt="amount"  value="${groupCheckFee}"/></td>
								<td class="pd-chill-tip" title="${contTempMoney}"><input type="text" name="contAddAgreements[${index}].contTempMoney" alt="amount"  value="${contTempMoney}"/></td>
								<td class="pd-chill-tip" title="${amountUpdate}"><input type="text" name="contAddAgreements[${index}].amountUpdate" alt="amount" value="${amountUpdate}" onkeyup="sumAgreeAmout()"/></td>
								<td>
								  <s:if test="resApproveId!=null">
								  <div onclick="showPageLink('${ctx}/res/res-approve-info.action?id=${resApproveId}','resApprove');" style="cursor: pointer; text-decoration: underline;">审批内容</div>
								  </s:if>
								  <s:else>
								   <s:if test="attaBizId!=null&&attaBizId!=''">
								    <img  class="atta_readOnly"  align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" onclick="showAttachment('${attaBizId}')"/>
								    <img  class="atta_edit" style="display: none;" align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" onclick="openAttachment(this,'${attaBizId}','atta_biz_id_${index}')"/>
								   </s:if>
							    	<s:else>
								     <img class="atta_readOnly" src="${ctx}/resources/images/common/atta.gif" onclick="showAttachment('${attaBizId}')"/>
								     <img class="atta_edit" style="display: none;" src="${ctx}/resources/images/common/atta.gif" onclick="openAttachment(this,'${attaBizId}','atta_biz_id_${index}')"/>
								   </s:else>
									<input type="hidden" name="contAddAgreements[${index}].attaBizId" value="${attaBizId}" />
									</s:else>
								</td>
								<td >
								 <s:if test="resApproveId==null">
									 <security:authorize ifAnyGranted="A_CONTRACT_ADMIN">
									   <span onclick="delAgree(this,'${contAddAgreementId}','${contLedgerId }')">删除</span>
									</security:authorize>
								  </s:if>
								</td>
							</tr>
						</s:iterator>
						</tbody>
						</s:if>
					</table>
				</div>
				<div class="foot-ellipsis">......</div>
			</fieldset>
			<fieldset>
				<legend>进度付款动态<img src="${ctx}/images/meetingRoom/pic_down_blue.gif"/></legend>
				<div>
					<div style="margin-bottom: 3px;display: none;" id="addPay_btn">
						<s:if test="contLedgerId == null">
							<input type="button" class="btn_new btn_blue_new" onclick="addPay()" value="增加"/>
						</s:if>
						<s:else>
							<input type="button" class="btn_new btn_green_new" onclick="addPay()" value="增加"/>
						</s:else>
					</div>
					<table class="cont-table" id="addPayTable">
						<thead>
							<tr>
								<td width="10%">付款时间</td>
								<td width="15%">当期已完成产值(元)</td>
								<td  width="10%">其中甲供材料当期产值(元)</td>
								<td>当期应付款(元)</td>
								<td width="8%">当期增加款或扣款</td>
								<td width="8%">付款性质</td>
								<td width="8%">小计</td>
					<%--			<td>当期实际支付(元)</td> --%>
								<td width="8%">比率(%)</td>
								<td width="8%">操作</td>
							</tr>
						</thead>
						<s:if test="contPaies.size()>0">
						<tbody>
						<s:iterator value="contPaies" status="s">
							<s:set name="index" value="#s.index"/>
							<tr>
								<td class="pd-chill-tip" title="<s:date name='payTime' format='yyyy-MM-dd' />">
									<input type="hidden" name="contPaies[${index}].contPayId" value="${contPayId}"/>
									<input type="hidden" name="contPaies[${index}].recordVersion" value="${recordVersion}"/>
									<input type="text" name="contPaies[${index}].payTime" value='<s:date name="payTime" format="yyyy-MM-dd" />' onfocus="WdatePicker()"/>
								</td>
								<td class="pd-chill-tip" title="${completeNum}"><input type="text" id="completeNum_${index}" name="contPaies[${index}].completeNum" alt="amount"  value="${completeNum}" onkeyup="sumPay(1);sumAmount(${index});"/></td>
								<td class="pd-chill-tip" title="${matieralNum}"><input type="text" id="matieralNum_${index}" name="contPaies[${index}].matieralNum" alt="amount"  value="${matieralNum}" onkeyup="sumPay(2);sumAmount(${index});"/></td>
								<td class="pd-chill-tip" title="${currentPay}"><input type="text" id="currentPay_${index}" name="contPaies[${index}].currentPay"  alt="amount" value="${currentPay}" onkeyup="sumPay(3);sumAmount(${index});"/></td>
								<td class="pd-chill-tip" title="${currentAdd}"><input type="text" id="currentAdd_${index}" name="contPaies[${index}].currentAdd"  alt="amount" value="${currentAdd}" onkeyup="sumPay(4);sumAmount(${index});"/></td>
								<td>
								<!-- 付款性质 单选 -->
								<span class="span_ready">
									 <c:if test="${contPaies[index].payProperty==1}">预付款</c:if>
									 <c:if test="${contPaies[index].payProperty==2}">进度款</c:if>
									 <c:if test="${contPaies[index].payProperty==3}">结算款</c:if>
									 <c:if test="${contPaies[index].payProperty==4}">质保金</c:if>
									 <c:if test="${contPaies[index].payProperty==5}">其他尾款</c:if>
									</span>
									<span class="span_eidt" style="display:none;">
									    <select id="processStatus_${index}" name="contPaies[${index}].payProperty" style="width:80px">
					                       <option value=""></option>
					                       <option value="1" <c:if test="${contPaies[index].payProperty==1}">selected</c:if>>预付款</option>
					                       <option value="2" <c:if test="${contPaies[index].payProperty==2}">selected</c:if>>进度款</option>
					                       <option value="3" <c:if test="${contPaies[index].payProperty==3}">selected</c:if>>结算款</option>
					                       <option value="4" <c:if test="${contPaies[index].payProperty==4}">selected</c:if>>质保金</option>
					                       <option value="5" <c:if test="${contPaies[index].payProperty==5}">selected</c:if>>其它尾款</option>
					                    </select>
									</span>
								</td>
								<td class="pd-chill-tip" title="${payMoney}"><input type="text" id="payMoney_${index}" name="contPaies[${index}].payMoney" value="${payMoney}" class="visa_1" /></td>
						<%--	<td class="pd-chill-tip" title="${currentRealPay}"><input type="text"  name="contPaies[${index}].currentRealPay" value="${currentRealPay}" class="visa_1" readonly="readonly" /></td> --%>
								<td class="pd-chill-tip" title="${payRate}"><input type="text" id="payRate_${index}" name="contPaies[${index}].payRate" value="${payRate}" class="visa_1" /></td>
								<td>
							     <s:if test="resApproveId!=null">
							      <div class="approve" onclick="showPageLink('${ctx}/res/res-approve-info.action?id=${resApproveId}','resApprove');" style="cursor: pointer; text-decoration: underline;">审批内容</div>
							      <security:authorize ifAnyGranted="A_CONTRACT_ADMIN">
									   <span onclick="delPay(this,'${contPayId}')">删除</span>
									</security:authorize>
							      </s:if>
							      <s:else>
									 <security:authorize ifAnyGranted="A_CONTRACT_ADMIN">
									   <span onclick="delPay(this,'${contPayId}')">删除</span>
									</security:authorize>
									</s:else>
								</td>
							</tr>
						</s:iterator>
						</tbody>
						</s:if>
						<tfoot>
							<tr>
								<td>合计</td>
								<td>0</td>
								<td>0</td>
								<td>0</td>
								<td>0</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr class="fin">
							 <td colspan="2">累计实际支付款(元)</td>
							 <td colspan="7"><input type="text" id="payNum" name="payNum" value="${payNum}" alt="amount" readonly="readonly" /></td>
							</tr>
						</tfoot>
					</table>
				</div>
				<div class="foot-ellipsis">......</div>
			</fieldset>
			<fieldset>
			 <legend>结算款付款进度<img src="${ctx}/images/meetingRoom/pic_down_blue.gif"/></legend>
			 <div id="div_cont_sett" class="cont-show-table">
			  <table class="cont-table" id="settTable">
				<thead>
					<tr>
						<td width="10%">建立时间</td>
						<td width="15%">结算总价</td>
						<td  width="15%">预留质保金</td>
						<td>扣已付款 </td>
						<td width="15%">扣其他应扣款</td>
						<td width="10%">本次付款申请</td>
						<td width="10%">操作</td>
					</tr>
				</thead>
				<s:iterator value="contSettlements" status="s">
				<tr>
				 <td  class="pd-chill-tip" title="<s:date name='createdDate' format='yy-MM-dd' />"><s:date name="createdDate" format="yy-MM-dd" /></td>
				 <td  class="pd-chill-tip" title="${clearPrice}">${clearPrice}</td>
				 <td  class="pd-chill-tip" title="${guaranteeMoney}">${guaranteeMoney}</td>
				 <td  class="pd-chill-tip" title="${currentPay}">${currentPay}</td>
				 <td  class="pd-chill-tip" title="${currentAdd}">${currentAdd}</td>
				 <td  class="pd-chill-tip" title="${payMoney}">${payMoney}</td>
				 <td>
				  <s:if test="resApproveId!=null">
				    <div onclick="showPageLink('${ctx}/res/res-approve-info.action?id=${resApproveId}','resApprove');" style="cursor: pointer; text-decoration: underline;">审批内容</div>
				  </s:if>
				 </td>
				</tr>
				</s:iterator>
			  </table>
			 </div>
			</fieldset>
			<fieldset>
			 <legend>工程、材料设备质保金付款动态<img src="${ctx}/images/meetingRoom/pic_down_blue.gif"/></legend>
			 <div id="div_cont_guar" class="cont-show-table">
			  <table class="cont-table" id="guarTable">
				<thead>
					<tr>
						<td width="20%">实际预留质保金</td>
						<td  width="15%">保修应扣款</td>
						<td>预留保修金 </td>
						<td width="20%">本次应付质保金</td>
						<td width="10%">操作</td>
					</tr>
				</thead>
				<s:iterator value="contGuarantees" status="s">
				<tr>
				 <td  class="pd-chill-tip" title="${realGuarantee}">${realGuarantee}</td>
				 <td  class="pd-chill-tip" title="${cutPayment}">${cutPayment}</td>
				 <td  class="pd-chill-tip" title="${leaveGuarantee}">${leaveGuarantee}</td>
				 <td  class="pd-chill-tip" title="${payMoney}">${payMoeny}</td>
				 <td>
				  <s:if test="resApproveId!=null">
				    <div onclick="showPageLink('${ctx}/res/res-approve-info.action?id=${resApproveId}','resApprove');" style="cursor: pointer; text-decoration: underline;">审批内容</div>
				  </s:if>
				 </td>
				</tr>
				</s:iterator>
			  </table>
			 </div>
			</fieldset>
			<fieldset>
				<legend>备注（与合同履约有关的必要说明）<img src="${ctx}/images/meetingRoom/pic_down_blue.gif"/></legend>
				<div id="div_cont_remark" class="cont-show-table">
				<table style="width:100%;">
				<s:iterator value="contRemarks">
				<tr>
				 <td width="90%">${remark }</td>
				 <td>
					 <s:if test="creator==nowUiid">
						<input type="button" id="DelBtn" class="btn_new btn_red_new" style="display:none;" onclick="doDeleteRemark(this,'${contremarkId}');" value="删除"/>
					</s:if>
					<s:else>
						<security:authorize ifAnyGranted="A_CONTRACT_ADMIN">
						<input type="button" id="DelBtn" class="btn_new btn_red_new" style="display:none;" onclick="doDeleteRemark(this,'${contremarkId}');"  value="删除"/>
						</security:authorize>
					</s:else>
				 </td>
				</tr>
				</s:iterator>
				</table>
				</div>
				<div>
				<textarea style="width:90%;height:80px;border:1px solid #ccc;" name="contRemarks[${fn:length(contRemarks)}].remark" maxlength="100"></textarea>
				</div>
				<div class="foot-ellipsis">
				</div>
			</fieldset> 
		
		</div>
	</div>
	</form>
	<script type="text/javascript">
	var visaIndex = '${fn:length(contVisaUpdates)}';
	var payIndex = '${fn:length(contPaies)}';
	var addIndex = '${fn:length(contAddAgreements)}';
	var curr_user_cont = '<%= SpringSecurityUtils.getCurrentUiid()%>'; 
$(function(){
	initCompent();
	$('legend').toggle(function(){
		$(this).next().hide().next().show();
	},function(){
		$(this).next().show().next().hide();
	});
	$('.foot-ellipsis').click(function(){
		$(this).prev().prev().click();
	});
});
/**
 * 将页面各个控件设置为可编辑状态
 */
function editCont(){
	$('#tipStatus').text('可编辑');
	//显示合同库按钮链接
	$("#con_ctrl").show();
	$("#contLink").hide();
	//$('#project_name_label').hide().next().show();
	$('#cont_name_label').hide().next().show();
	$('#cont_type_label').hide().next().show();
	$('#cont_name,#cont-no').css('borderBottom','1px solid #ccc');
	$(':text,textarea').css('backgroundColor','#ccc');
	var status =$('#contAudit_status').val();
	//审批内容不做控制
	$('.approve').parent().parent().find(':text').each(function(i, dom) {
		 $(dom).attr('class','visa_1');
	});
	setTimeout(function(){
		//如果是审核修改，则可以修改审核数据
	<security:authorize ifNotGranted="A_CONTRACT_AUDITMOD">
		if(status=='2'){
			//已审核，则基础数据不能修改
		 $('#cont_basic').find(':text,textarea').each(function(i, dom) {
			 $(dom).attr('class','visa_1');
		 });
		 $('.atta_readOnly:not(.atta_audit)').hide();
		 $('.atta_edit:not(.atta_audit)').show();
		}else{
			$('.atta_readOnly').hide();
			$('.atta_edit').show();
		}
	</security:authorize>
	<security:authorize ifAnyGranted="A_CONTRACT_AUDITMOD">
	   $('.atta_readOnly').hide();
	   $('.atta_edit').show();
	</security:authorize>
	$(':text:not(.visa_1),textarea:not(.visa_1)').css('backgroundColor','#f1f1f1').removeAttr('readOnly');	
	$(':radio:not(.visa_1)').removeAttr('disabled');
	//如果只有财务权限，那么只能修改财务数据
	<security:authorize ifNotGranted="A_CONTRACT_INPUT,A_CONT_MANA">
	<security:authorize ifAnyGranted="A_CONTRACT_FIN">
	  $(':text,textarea').attr('readOnly','true');
	  $(':text,textarea').css('backgroundColor','#ccc');
	  $(':radio').attr('disabled',true).customInput();
	  $("#payNum").removeAttr('readOnly').css('backgroundColor','#f1f1f1');	
	</security:authorize>
	</security:authorize>
	<security:authorize ifNotGranted="A_CONTRACT_FIN">
	$("#payNum").attr('readOnly','true').css('backgroundColor','#ccc');
	</security:authorize>
	}, 200);
	$('.cont-table').addClass('edit-table');
	$('#addVisa_btn,#addAgree_btn,#addPay_btn').show();
	$('#btn_edit').hide();
	$('#btn_save,#btn_cancel,#btn_submit,#btn_reback,#btn_audit,#btn_reback').show();
	$("#visa_total").attr('readonly','readonly');
	$(".span_ready").hide();
	$(".span_eidt").show();
}


function doContTemplet(){
	 var getContUrl="${ctx}/sc/sc-contract-templet-info!conSelect.action?frameId=cont-ledger-input";
	if(parent == null ||parent.TabUtils==null)
		window.open(getContUrl);
	else
	  	parent.TabUtils.newTab("scconInfo_Select","合同库",getContUrl,true);
}



//标准合同查看
function getContById(istandard,id,hisId){
    if(istandard=="0" || istandard==""){
  	  var getContUrl="${ctx}/sc/sc-contract-templet-info!readNonStandardCon.action?scContId="+ id ;
		  	if(parent == null || parent.TabUtils==null)
		  		window.open(getContUrl);
		  	else
		  		parent.TabUtils.newTab("sc-contract-templet-info","非标准合同查看",getContUrl,true);
    }else{
  	  var getContUrl="${ctx}/sc/sc-contract-templet-info!markContract.action?scContId="
			+ id 
			+ "&contractTempletHisId=" + hisId
			+ "&statusCd=${statusCd}";
			
  		if(parent == null || parent.TabUtils==null)
  			window.open(getContUrl);
  		else
  		  	parent.TabUtils.newTab("sc-contract-templet-info","标准合同查看",getContUrl,true);
 	}
}

//解决弹出窗口的链接问题,改造 parent.showAll()
function showPageLink(url, type){
	if(parent && parent.showAll){
		parent.showAll(url, type);
	}else{
		window.open(url);
	}
}


//导出签证变更 2012-06-06 add by huangbijin
function exportContVisaList(bidLedgerId){
	var url = '${ctx}/cont/cont-ledger!exportContVisaList.action?bidLedgerId=' + bidLedgerId;
	window.open(url);
}
</script>
</body>
</html>
