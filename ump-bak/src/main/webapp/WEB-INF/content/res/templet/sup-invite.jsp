<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<!--招标公告意向供应商入库邀请-->

<div align="center" class="billContent">
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
		<s:hidden id="isEdit" value="true"/>
	<%--删除商业集团公司发起 --%>
		<table  class="mainTable">
			<col width="100"/>
			<col/>
			<col width="100"/>
			<col/>
			<tr>
			 <td class="td_title">标题</td>
			<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.inviteTitle" value="${templateBean.inviteTitle}" />
			</td>
			</tr>
			<tr>
				<td class="td_title">招标范围</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.inviteScope" value="${templateBean.inviteScope}" />
				</td>
				<td class="td_title">招标项目</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.inviteProject" value="${templateBean.inviteProject}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">标段面积</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.bidArea" value="${templateBean.bidArea}"  />
				</td>		
				<td class="td_title">计价模式</td>	
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.valuationModule" value="${templateBean.valuationModule}"/></td>			
			</tr>
			<tr>
				<td class="td_title">施工工期</td>
				<td colspan="3">
					<input class="inputBorder" type="text" onfocus="WdatePicker();" style="width:45%;" name="templateBean.beginDate" value="${templateBean.beginDate}"/>
					到
					<input class="inputBorder" type="text" onfocus="WdatePicker();" style="width:45%;" name="templateBean.endDate" value="${templateBean.endDate}"/>
				</td>
			</tr>
		</table>
		<table  class="mainTable" id="unitTable" style="margin-top:5px;">
			<tr>
				<td align="center">意向投标公司</td>
				<td align="center">注册资金（万）</td>
				<td align="center">联系人</td>
				<td align="center">联系电话</td>
				<td align="center">电子邮件</td>
				<td align="center">备注</td>
				<td align="center">操作</td>
			</tr>
			<s:if test="statusCd==0 || statusCd==3">
			<tr id="trConItem" style="display: none;margin-bottom:5px;">
				<td >
				<input class="inputBorder" validate="required" id="signProject" type="text" name="templateBean.otherProperties[0].signProject"/>
				</td>
				<td>
				<input class="inputBorder"  type="text" id="signMoney" name="templateBean.otherProperties[0].signMoney"/>
				</td>
				<td>
				<input class="inputBorder"  type="text" id="signPeople" name="templateBean.otherProperties[0].signPeople"/>
				</td>
				<td>
				<input class="inputBorder"  type="text" id="signPhone" name="templateBean.otherProperties[0].signPhone"/>
				</td>
				<td>
				<input class="inputBorder"  type="text" id="signMail" name="templateBean.otherProperties[0].signMail"/>
				</td>
				<td>
				<input class="inputBorder"  type="text" id="remark" name="templateBean.otherProperties[0].remark"/>
				</td>
				<td>
				 <s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
					<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
				 </s:if>
				</td>
			</tr>
			</s:if>
			<s:iterator value="templateBean.otherProperties" var="item" status="s">
			<tr>
			   <input type="hidden" id="deleteBl<s:property value="#s.index"/>" edit='' name="templateBean.otherProperties[<s:property value="#s.index" />].deleteBl" value="${item.deleteBl}"/>
				<td>
				<input class="inputBorder" validate="required" id="signProject<s:property value="#s.index" />" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].signProject" value="${item.signProject}"
				<c:if test="${item.deleteBl=='1'}">style="color:#FF0000;text-decoration:line-through"</c:if>
				  />
				</td>
				<td>
				<input class="inputBorder" type="text" id="signMoney<s:property value="#s.index" />" name="templateBean.otherProperties[<s:property value="#s.index" />].signMoney" value="${item.signMoney}"
				<c:if test="${item.deleteBl=='1'}">style="color:#FF0000;text-decoration:line-through"</c:if>
				/>
				</td>
				<td>
				<input class="inputBorder" type="text" id="signPeople<s:property value="#s.index" />" name="templateBean.otherProperties[<s:property value="#s.index" />].signPeople" value="${item.signPeople}"
				<c:if test="${item.deleteBl=='1'}">style="color:#FF0000;text-decoration:line-through"</c:if>
				/>
				</td>
				<td>
				<input class="inputBorder" type="text" id="signPhone<s:property value="#s.index" />" name="templateBean.otherProperties[<s:property value="#s.index" />].signPhone" value="${item.signPhone}"
				<c:if test="${item.deleteBl=='1'}">style="color:#FF0000;text-decoration:line-through"</c:if>
				/>
				</td>
				<td>
				<input class="inputBorder" type="text" id="signMail<s:property value="#s.index" />" name="templateBean.otherProperties[<s:property value="#s.index" />].signMail" value="${item.signMail}"
				<c:if test="${item.deleteBl=='1'}">style="color:#FF0000;text-decoration:line-through"</c:if>
				/>
				</td>
				<td>
				<input class="inputBorder" type="text" id="remark<s:property value="#s.index" />" name="templateBean.otherProperties[<s:property value="#s.index" />].remark" value="${item.remark}"
				<c:if test="${item.deleteBl=='1'}">style="color:#FF0000;text-decoration:line-through"</c:if>
				/>
				</td>
				<td>
				<s:if test="#curNodeCd==89">
				<a href="javascript:void(0)" onclick="delItem(this,'<s:property value="#s.index" />')"><img title="删除" src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
				</s:if>
				</td>
			</tr>
			</s:iterator>
		</table>
		<%-- 
		<s:if test="#canEdit=='true'">
		<input type="button"  class="btn_blue_75_55" style="margin-top: 5px;" value="增加单位" onclick="addItem();" />
		</s:if>
		--%>
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
		//var domSidId=$(trApprover_new).find(".sid").attr('id');
		//$(trApprover_new).find(".sid").attr('id',domSidId+index);
		//var domSidName=$(trApprover_new).find(".sid").attr('name');
		//$(trApprover_new).find(".sid").attr('name',domSidName.replace('0',index));
		//var ids={sid:"supBasicId"+index,sname:'unitName'+index,grade:'unitLevel'+index};
		$("#unitTable").append(trApprover_new);
		//var params={isNew:true};
		//$("#unitName"+index).quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],ids,{supStatus:'0'});
		index++;
	}
	/**
	*删除当前投标意向公司
	*/
	function delItem(dom,bl){
		var deleteBl=$("#deleteBl"+bl).val();
		if("1"!=deleteBl){
			 $(dom).parent().parent().find(".inputBorder").each(function(i){
					var vid =this.id;
					$("#"+vid).css("color","#FF0000");
					$("#"+vid).css("text-decoration","line-through");
				});
				$("#deleteBl"+bl).val("1");
				$("#deleteBl"+bl).attr("edit","true");
		}else{
			 $(dom).parent().parent().find(".inputBorder").each(function(i){
					var vid =this.id;
					$("#"+vid).removeAttr("style");
				});
				$("#deleteBl"+bl).val("0");
				$("#deleteBl"+bl).attr("edit","");
		}
		
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
	