<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<head>
<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	<meta http-equiv="Content-Type" content="text/html" />
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/res/res.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/css/resApprove.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/res/fileSuffixes.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
	
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/js/res/resApprove.js"></script>
	<script type="text/javascript" src="${ctx}/js/res/resApproveInput.js"></script>
	<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	
	<script type="text/javascript" src="${ctx}/resources/js/res/resShow.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/res/resSingleUpload.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/xheditor/xheditor-zh-cn.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	
	<%--搜索供应商 --%>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jQuery.artTxtCount.js"></script>
</head>

<!--邀标单位审批表-->
<body>

<div align="center" class="billContent">			
			
			<form action="bid-ledger!saveNewBidLedger.action" method="post" id="templetForm" class="contractPaymentBill">		
				<table  class="mainTable">
					<col width="100"/>
					<col/>
					<col width="100"/>
					<col/>
					<tr>
						<td class="td_title">项目</td>
						<td>
								<input validate="required" type="text" name="projectName" value="${templateBean.projectName}" id="projectName" class="required" onkeyup="queryOrg(this);" style="cursor: pointer;" />
								<input type="hidden" id="projectCd"  name="projectCd" value="${templateBean.projectCd}"  />
						
						</td>
						<td class="td_title">标书编号</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="biaoShuNo" value="${templateBean.biaoShuNo}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">标段</td>
						<td colspan="3">
							<input class="inputBorder" validate="required" type="text" name="biaoDuan" value="${templateBean.biaoDuan}"  />
						</td>						
					</tr>
					<tr>
						<td class="td_title">总包</td>
						<td  class="chkGroup"  validate="required" >
							<s:checkbox name="overAllYes" id="overAllYes" cssClass="group"></s:checkbox>总包
							<s:checkbox name="overAllNo" id="overAllNo" cssClass="group"></s:checkbox>非总包
						</td>
						<%-- //TODO:投标台账启用后,打开  --%>
						<td class="td_title">邀请类别</td>
						<td class="chkGroup"  validate="required" >
							<s:checkbox name="showFlag" id="showFlag" cssClass="group"></s:checkbox>明标
							<s:checkbox name="hideFlag" id="hideFlag" cssClass="group"></s:checkbox>暗标
						</td>
					</tr>
					<tr>
						<td class="td_title">预计金额(元)</td>
						<td>
							<input class="inputBorder" validate="required" onblur="formatVal($(this));" type="text" name="planMoney" value="${templateBean.planMoney}"  />
						</td>
						<td class="td_title">预算</td>
						<td validate="required" class="chkGroup">
							<s:checkbox name="outFlag" id="outFlag" cssClass="group"></s:checkbox>预算外
							<s:checkbox name="inFlag" id="inFlag" cssClass="group"></s:checkbox>预算内
						</td>
					</tr>
				</table>				
			</form>
		</div>
	
	<script language="javascript">
		$(function(){			
			alert(1);
			$('#projectName').orgSelect({showProjectOrg:true});
			//触发一个增加单位
			addItem();
		});
		var trApprover=$("#trConItem[removable=true]").clone();
		$("#trConItem[removable=true]").remove();
		var cloneCount = 0;
		var index=0;//${conItemCount};
		
		
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

		function saveBidLedger(){
			var url=_ctx+"/bid/bid-ledger!newBidLedger.action";
			$('#templetForm').ajaxSubmit(function(result){
				});		
			}
	</script>
</body>