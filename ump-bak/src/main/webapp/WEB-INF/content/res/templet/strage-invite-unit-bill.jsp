<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<!--战略邀标审批表-->

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
						<td class="td_title">战略名称</td>
						<td colspan="3">
							<input class="inputBorder" validate="required" type="text" name="templateBean.strageName" value="${templateBean.strageName}" id="strageName" />
						</td>
					</tr>
					<tr>
						<td class="td_title">项目名称</td>
						<td>
							<table style="border: none;width:100%;">
							<tr>
								<td>
									<input style="width:99%;" validate="required"  type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" maxlength="100" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
									<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
								</td>
								<td style="width:80px;">
									（<input class="inputBorder" style="width:30px;text-align:center;" type="text" name="templateBean.periodNum" value="${templateBean.periodNum}" id="periodNum" maxlength="20"/>）期
								</td>
							</tr>
							</table>
								
						</td>
						<td class="td_title">工程</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.construction" value="${templateBean.construction}" id="construction" maxlength="200"/></td>
					</tr>
					<c:choose>
						<c:when test="${isSy eq 'true'}">
							<tr>
								<td class="td_title">邀请类别</td>
								<td colspan="3" class="chkGroup" align="left" validate="required" >
									<s:checkbox name="templateBean.showFlag" id="showFlag" cssClass="group"></s:checkbox>明标
									<s:checkbox name="templateBean.hideFlag" id="hideFlag" cssClass="group"></s:checkbox>暗标
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="td_title">邀请类别</td>
								<td class="chkGroup" align="left" validate="required" >
									<s:checkbox name="templateBean.showFlag" id="showFlag" cssClass="group"></s:checkbox>明标
									<s:checkbox name="templateBean.hideFlag" id="hideFlag" cssClass="group"></s:checkbox>暗标
								</td>
								<td></td>
								<td></td>
							</tr>
						</c:otherwise>
					</c:choose>
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
					<tr id="trConItem"  style="display: none;margin-bottom:5px;">
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
				<input type="button"  class="btn_blue_75_55" value="增加单位" onclick="addItem();"/>
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
			var excludeSupBasicIds="";
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
			//获取supBasicId值
			$(".sid").each(function(i){
				if(i==$(".sid").length-1){
					excludeSupBasicIds+=$(this).attr("value");
				}else{
					excludeSupBasicIds+=$(this).attr("value")+",";
				}
				});
			//var params={isNew:true};
			$("#unitName"+index).quickSearch(
				"${ctx}/sup/sup-basic!quickSearch.action",
				['sname'],
				ids,
				{supStatus:'0',excludeSupBasicIds:excludeSupBasicIds}
			);
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
	