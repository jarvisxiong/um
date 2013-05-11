<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<!--邀标单位审批表-->

<div align="center" class="billContent">
			<c:set var="isSy">false</c:set>
			<c:if test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
			<c:set var="isSy">true</c:set>
			</c:if>
			<s:set var="canEdit">
			<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
			true
			</s:if>
			<s:else>
			false
			</s:else>
			</s:set>
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
			<%--删除商业集团公司发起 --%>
				<table  class="mainTable">
					<col width="100"/>
					<col/>
					<col width="100"/>
					<col/>
					<tr>
						<td class="td_title">项目</td>
						<td>
							<s:if test="authTypeCd == 'CBGFGL_YBDW_60'">
								<input class="inputBorder" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="centerName" />
							</s:if>
							<s:else>
								<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
								<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
							</s:else>
						</td>
						<td class="td_title">标书编号</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.biaoShuNo" value="${templateBean.biaoShuNo}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">标段</td>
						<td align="left">
							<input class="inputBorder" validate="required" type="text" name="templateBean.biaoDuan" value="${templateBean.biaoDuan}"  />
						</td>			
						<s:if test="authTypeCd == 'CBGFGL_YBDW_10' ">
						<td class="td_title">招标计划编号</td>
						<td align="left">	
							<input class="inputBorder" style="width:100px;" validate="required" type="text" 
							name="templateBean.ccbpNo" value="${templateBean.ccbpNo}"  id="ccbpNo"
							
							<s:if test="#canEdit=='true'">
							onblur="leaveCcbpNo();"
							</s:if>
							
							/>
							<input type="hidden" name="templateBean.ccbpId" value="${templateBean.ccbpId}"  id="ccbpId"/>
							<input type="hidden" name="templateBean.ccbpPlanContentDesc" id="ccbpPlanContentDesc" value="${templateBean.ccbpPlanContentDesc}" />
							<div id="ccbpPlanContentDescSpan">${templateBean.ccbpPlanContentDesc}</div>
							<input type="hidden" id="dataTypeCd" name="dataTypeCd" value="0" /><!-- 0-招标 -->
							
							<s:if test="#canEdit=='true'">
							<script type="text/javascript">
								$('#ccbpNo').attr('title','请选择【成本系统-计划平台】中的‘招标’计划编号');	
								$('#ccbpNo').quickSearch(
									'${ctx}/plan/cost-ctrl-bid-purc!quickSearchCcbp.action',
									['ccbpProjectName','ccbpDataTypeName','ccbpNo','ccbpPlanContentDesc'],
									{ccbpId:'ccbpId',ccbpNo:'ccbpNo'},//,ccbpProjectCd: 'projectCd',ccbpProjectName: 'projectName'
									'',
									function(jdom){
										var arr = new Array();
										arr.push(jdom.attr('ccbpPlanContentDesc'));
										var t = arr.join('');
										$('#ccbpPlanContentDesc').val("工程："+t);
										$('#ccbpPlanContentDescSpan').attr("title",t);
										$('#ccbpPlanContentDescSpan').html("工程："+t);
									},
									{projectCd:'projectCd',dataTypeCd:'dataTypeCd'}
								);

								function leaveCcbpNo(){
									var ccbpNo = $('#ccbpNo').val();
									if( ccbpNo == ''){
										$('#ccbpPlanContentDescSpan').html('');
									}
								}
							</script>
							</s:if>
						</td>	
						</s:if>	
					</tr>
					<c:choose>
						<c:when test="${isSy eq 'true'}">
							 
						</c:when>
						<c:otherwise>
							<s:if test="authTypeCd == 'CBGFGL_YBDW_10'">
							<tr>
								<td class="td_title">清单类型</td>
								<td  class="chkGroup"  validate="required" colspan="3" align="left">
									<s:checkbox name="templateBean.gbqdFlg" id="gbqdFlg" cssClass="group"></s:checkbox>国标清单
									<s:checkbox name="templateBean.gsqdFlg" id="gsqdFlg" cssClass="group"></s:checkbox>港式清单
									<s:checkbox name="templateBean.qitaFlg" id="qitaFlg" cssClass="group"></s:checkbox>其他
								</td>
							</tr>
							</s:if>
						</c:otherwise>
					</c:choose>
					
					<tr>
						<td class="td_title">预计金额(元)</td>
						<td>
							<input class="inputBorder" validate="required" onblur="formatVal($(this));" type="text" name="templateBean.planMoney" value="${templateBean.planMoney}"  />
						</td>
						<td class="td_title">预算</td>
						<td validate="required" class="chkGroup" align="left">
							<s:checkbox name="templateBean.outFlag" id="outFlag" cssClass="group"></s:checkbox>预算外
							<s:checkbox name="templateBean.inFlag" id="inFlag" cssClass="group"></s:checkbox>预算内
						</td>
					</tr>
					<tr>
						<td class="td_title">备注</td>
						<td colspan="3">
						<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark">${templateBean.remark}</textarea>
						</td>
					</tr>
				</table>
				<table  class="mainTable" id="giftTable" style="margin-top:5px;">
					<col/>
					<col width="90"/>
					<col width="60"/>
					<col width="30"/>
					<tr>
						<td align="center">单位名称</td>
						<td align="center">供方级别</td>
						<td align="center" colspan="2">操作</td>
					</tr>
					<s:if test="statusCd==0 || statusCd==3">
					<tr id="trConItem" style="display: none;margin-bottom:5px;">
						<td >
						<input type="hidden" class="sid" id="supBasicId" name="templateBean.otherProperties[0].supBasicId">
						<input class="inputBorder supName" validate="required" id="unitName" type="text" name="templateBean.otherProperties[0].unitName"/>
						</td>
						<td>
						<input class="inputBorder" readonly="readonly" type="text" id="unitLevel" name="templateBean.otherProperties[0].unitLevel"/>
						</td>
						<td>
						<span  class="link" onclick="getSup(this);">打开</span>
						</td>
						<td>
						<a href="javascript:void(0)" onclick="delItem(this)"><img title="删除" src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
						</td>
					</tr>
					</s:if>
					<s:iterator value="templateBean.otherProperties" var="item" status="s">
					<tr>
						<td>
						<input type="hidden" class="sid" id="supBasicId<s:property value="#s.index" />" name="templateBean.otherProperties[<s:property value="#s.index" />].supBasicId" value="${item.supBasicId}">
						<input class="inputBorder supName" validate="required" id="unitName<s:property value="#s.index" />" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].unitName" value="${item.unitName}"/>
						</td>
						<td>
						<input class="inputBorder" readonly="readonly"   type="text" id="unitLevel<s:property value="#s.index" />" name="templateBean.otherProperties[<s:property value="#s.index" />].unitLevel" value="${item.unitLevel}"/>
						</td>
						<td>
						<span  class="link" onclick="getSup(this);">打开</span>
						</td>
						<td>
						<s:if test="#canEdit=='true'">
						<a href="javascript:void(0)" onclick="delItem(this)"><img title="删除" src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
						</s:if>
						</td>
					</tr>
					<s:if test="#isMy==1">
					<script type="text/javascript">
					var indexTmp='${s.index}';
					var ids={sid:'supBasicId'+indexTmp,sname:'unitName'+indexTmp,grade:'unitLevel'+indexTmp};
					$("#unitName"+indexTmp).quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],ids);
					</script>
					</s:if>
					</s:iterator>
				</table>
				<s:if test="#canEdit=='true'">
				<input type="button"  class="btn_blue_75_55" value="增加单位" onclick="addItem();" />
				</s:if>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
			
	<script type="text/javascript">
		var trApprover=$("#trConItem").clone();
		$("#trConItem").remove();
		var cloneCount = 0;
		var index=${conItemCount};
		
		function addItem(){
			var trApprover_new=trApprover.clone();
			cloneCount++;
			$(trApprover_new).show();
			$(trApprover_new).find(".inputBorder").each(function(i){
				if (this.name){
					this.name=this.name.replace('0',index);
				}
				if (this.id){
					this.id=this.id+index;
				}
			});
			var domSidId=$(trApprover_new).find(".sid").attr('id');
			$(trApprover_new).find(".sid").attr('id',domSidId+index);
			var domSidName=$(trApprover_new).find(".sid").attr('name');
			$(trApprover_new).find(".sid").attr('name',domSidName.replace('0',index));
			var ids={sid:"supBasicId"+index,sname:'unitName'+index,grade:'unitLevel'+index};
			$("#giftTable").append(trApprover_new);
			//var params={isNew:true};
			$("#unitName"+index).quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],ids,{supStatus:'0'});
			index++;
		}
		function delItem(dom){
			$(dom).parent().parent().remove();
		}
		function getSup(dom){
			var sid=$(dom).parent().parent().find(".sid").val();
			var supName=$(dom).parent().parent().find(".supName").val();
			var url='';
			if(isNotEmpty(sid)){
				url="${ctx}/sup/sup-basic!input.action?id="+sid;
			}else if(isNotEmpty(supName)){
				url="${ctx}/sup/sup-basic!input.action?sName="+supName;
			}
			if (url!=''){
				if(parent.TabUtils==null)
					window.open(url);
				else
				  parent.TabUtils.newTab("supQuery","明细",url,true);
			}
		}
	</script>
	<!-- 默认1条申请记录 -->
	<s:if test="resApproveInfoId==null">
	<script type="text/javascript">
		addItem();
	</script>
	</s:if>
	