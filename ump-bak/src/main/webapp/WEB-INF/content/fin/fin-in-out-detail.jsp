<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ page language="java" import="java.text.SimpleDateFormat"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="java.util.Date"%><html xmlns="http://www.w3.org/1999/xhtml">
<%
 int orderNo =1;
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
%>
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
<link href="${ctx}/resources/css/common/TreePanel.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bus/fin.css" />
<link href="${ctx}/css/desk/thickbox.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/desk/oa-meeting.css" type="text/css" />
<script src="${ctx}/resources/js/datePicker/WdatePicker.js" type="text/javascript"></script> 
<script language="javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script language="javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
<!-- script type="text/javascript" src="${ctx}/js/tab.js"></script -->
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type=text/javascript src="${ctx}/resources/js/common/common.js"></script>
<script src="${ctx}/resources/js/common/TreePanel.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript">
var inOutId ="";
function doItem(modId){
	var selectId ="";
	var selectName ="";
	var finType ="";
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"现金流量项目",
		message:"<div id='parentDiv'></div>",
		useSlide:true,
		winPos:"c",
		width:300,
		height:400,
		allowRightMenu:true,
		showMask:false,
		afterShow:function(){
			$.post("${ctx}/fin/fin-item!getItemTree.action", function(result){
				var tree = new TreePanel({
					renderTo:"parentDiv",
					'root' : eval('('+result+')'),
					'ctx':'${ctx}'
				});
				tree.on(function(node){
					if(node.attributes.children==null||node.attributes.children==""){
					  selectId =node.attributes.finItemCd;
					  selectName =node.attributes.text;
					  finType =node.attributes.finType;
					}
				});
				tree.render();
			});
	},
	handler:function(e){
		if("ok"==e){
			$("#itemCd").val(selectId);
			if(modId!=""){
			    $("#itemName_"+modId).val(selectName);
			}else{
				$("#itemName").val(selectName);
			}
			if(finType=="1"){
				if(modId!=""){
					$("#detl_in_"+modId).show();
					$("#span_in_"+modId).hide();
					$("#detl_out_"+modId).hide();
					$("#span_out_"+modId).show();
					$("#out_"+modId).val("");
				}else{
			  	   $("#in_amount").attr("readonly","");
				   $("#out_amount").attr("readonly","readonly");
				   $("#out_amount").val("");
				}
			}else if(finType=="2"){
				if(modId!=""){
					$("#detl_out_"+modId).show();
					$("#span_out_"+modId).hide();
					$("#detl_in_"+modId).hide();
					$("#span_in_"+modId).show();
					$("#in_"+modId).val("");
				}else{
				  $("#in_amount").attr("readonly","readonly");
				  $("#out_amount").attr("readonly","");
				  $("#in_amount").val("");
				}
			}
		}
	}
	});
}
function newDetail(){
	$(".add_new").each(function() { 
		var disp = $(this).css("display");
		if (disp == "none") {
			$(this).show();
		} else {
			$(this).hide();
		}
	});
}
function doSave(){
	if($("#summaryDesc").val()==""){
		alert("请输入摘要");
		return;
	}
	if($("#itemCd").val()==""){
		alert("请选择现金流量项目");
		return;
	}
	if($("#in_amount").val()==""&&$("#out_amount").val()==""){
		alert("请输入金额");
		return;
	}else if($("#out_amount").val()==""){
		$("#out_amount").val(0);
	}else if($("#in_amount").val()==""){
		$("#in_amount").val(0);
	}
	//$("#inputForm").attr("action","${ctx}/fin/fin-in-out-detail!save.action");
	TB_showMaskLayer("正在保存...");
	$("#inputForm").submit(); 
}
function doDeleteDetail(bankId){
	if(inOutId!=""){
		if(confirm("确定要删除这条记录吗？")){
			var param = {id:inOutId,bankId:bankId};
			$.post("${ctx}/fin/fin-in-out-detail!delete.action",param, function(result) {
				if(result == "ok"){
					var url = "${ctx}/fin/fin-in-out-detail!list.action?bankId="+bankId+"&beginTime="+$("#beginTime").val()+"&endTime="+$("#endTime").val();
					self.location=url;
				}
			});
		}
	}
}
function doAttent(id){
	if(id!=""){
		if(inOutId !=id){
			if(inOutId!=""){
			  $("#detl_sum_"+inOutId).hide();
			  $("#sum_"+inOutId).show();
			  $("#detl_item_"+inOutId).hide();
			  $("#item_"+inOutId).show();
			  if($("#in_"+inOutId).val()!=0){
					$("#span_in_"+inOutId).show();
					$("#detl_in_"+inOutId).hide();
					
				}else if($("#out_"+inOutId).val()!=0){
					$("#span_out_"+inOutId).show();
					$("#detl_out_"+inOutId).hide();
				}
			}
			$("#detl_sum_"+id).show();
			$("#sum_"+id).hide();
			$("#detl_item_"+id).show();
			$("#item_"+id).hide();
			$("#itemCd").val($("#itemCd_"+id).val());
			if($("#in_"+id).val()!=0){
				$("#span_in_"+id).hide();
				$("#detl_in_"+id).show();
				
			}else if($("#out_"+id).val()!=0){
				$("#span_out_"+id).hide();
				$("#detl_out_"+id).show();
			}
			inOutId =id;
		}else{
			$("#detl_sum_"+inOutId).hide();
			$("#sum_"+inOutId).show();
			$("#detl_item_"+inOutId).hide();
			$("#item_"+inOutId).show();
			if($("#in_"+inOutId).val()!=0){
				$("#span_in_"+inOutId).show();
				$("#detl_in_"+inOutId).hide();
				
			}else if($("#out_"+inOutId).val()!=0){
				$("#span_out_"+inOutId).show();
				$("#detl_out_"+inOutId).hide();
			}
			inOutId ="";
		}
	}
}
function doCheckBank(){
	if($("#beginTime").val()!=""&&$("#endTime").val()!=""){
	  var url = "${ctx}/fin/fin-in-out-detail!list.action?beginTime="+$("#beginTime").val()+"&endTime="+$("#endTime").val()+"&bankId=${bankId}";
	  self.location = url;
	}else{
		alert("请选择搜索日期");
	}
}
function doModifyBank(){
	if(inOutId!=""){
		$("#id").val(inOutId);
		$("#summaryDesc").val($("#summary_"+inOutId).val());
		if($("#in_"+inOutId).val()!=""){
			$("#in_amount").val($("#in_"+inOutId).val());
		}
		if($("#out_"+inOutId).val()!=""){
			$("#out_amount").val($("#out_"+inOutId).val());
		}
		doSave();
		
	}else{
		alert("请选中要修改的数据");
	}
}
function doKeyPress(){
	 if((event.keyCode<45 || event.keyCode>57)&&event.keyCode!=46)
	    event.returnValue=false;
  }
function doUpdateDetail(bankId){
	if(confirm("确定要更新余额么？")){
	  var url = "${ctx}/fin/fin-in-out-detail!updateBalance.action?beginTime="+$("#beginTime").val()+"&bankId=${bankId}";
	  location.href = url;
	}
}
</script>
</head>
<body>
<div class="plan_title_bar">
	<img src="${ctx}/images/news/pic_employeeacts.gif"></img>
	<span style="height:29px;padding-left:15px;padding-top:3px;">&nbsp;账号:${accNo}</span>
	<span  style="height:29px;padding-left:60px;padding-top:3px;">项目名称:${bankName}</span>
	<span  style="height:29px;padding-left:100px;padding-top:3px;">&nbsp;&nbsp;币别:${curCode}</span>
</div>
<div style="height:2px;"></div>
<div style="height:30px; padding-left:8px; padding-top:13px;overflow: hidden;">
日期：<input type="text" id="beginTime" onfocus="WdatePicker()" title="从" value="${beginTime}"/>&nbsp;到&nbsp;<input type="text" id="endTime" onfocus="WdatePicker()" title="到" value="${endTime}"/>
<button type="button" id="ModBtn" class="btn_green_55_20" onclick="doCheckBank();">搜索</button>&nbsp;
<security:authorize ifAnyGranted="A_RJZ_USER,A_RJZ_MANAGER,A_RJZ_ADMIN">
<button type="button" id="ModBtn" class="btn_green_55_20" onclick="doModifyBank();">修改</button>&nbsp;
<button type="button" id="DelBtn" class="btn_red_35_20" onclick="doDeleteDetail('${bankId}');">删除</button>&nbsp;
<button type="button" id="DelBtn" class="btn_green_65_20"  onclick="doUpdateDetail('${bankId}');">更新余额</button>
</security:authorize>
<!--button type="button" id="ModBtn" class="btn_list_add" onclick="window.location.href='${ctx}/fin/fin-project-acct-rel!list.action'; return false;">返回</button-->

</div>
<div id="TableDiv"  style="height: 100%; padding-left: 8px; padding-top: 20px; overflow: hidden;">
 <table class="content_table">
	<thead>
		<tr>
			<th align="left" width="6%">
			<security:authorize ifAnyGranted="A_RJZ_ADMIN">
			<button type="button" id="NewBtn" class="btn_green_55_20" onclick="newDetail();">新建</button>
			</security:authorize>
			<security:authorize ifAnyGranted="A_RJZ_USER,A_RJZ_MANAGER">
	         <s:if test="haveChecked==1">
	         <button type="button" id="NewBtn" class="btn_green_55_20" onclick="newDetail();">新建</button>
	         </s:if>
			</security:authorize>
			</th>
			<th align="left" width="10%" nowrap="nowrap">日期</th>
			<th align="left" width="5%" nowrap="nowrap">序号</th>
			<th align="left" width="20%" nowrap="nowrap">摘要</th>
			<th align="left" width="20%" nowrap="nowrap">现金流量项目</th>
			<th align="left" width="12%" nowrap="nowrap">收入</th>
			<th align="left" width="12%" nowrap="nowrap">支出</th>
			<th align="left" width="15%" nowrap="nowrap">余额</th>
		</tr>
	</thead>
	<tbody>
	<s:form id="inputForm" action="fin-in-out-detail!save.action" method="post">
	 <input type="hidden" id="id" name="id"/>
	 <input type="hidden" id="bankId" name="bankId" value="${bankId}"/>
	 <input type="hidden"  name="balance" value="${balance}"/>
	 <tr class="add_new" style="display:none;">
	    <td width="55px;"></td>
		  <td>${nowDate }</td>
		  <td></td>
		  <td><s:textfield  id="summaryDesc" name="summaryDesc"  maxlength="30" cssStyle="width:90%"/></td>
		 <td><input type="text" id="itemName" style="width:90%" onclick ="doItem('');"/> <s:hidden id ="itemCd"  name="finItemCd" /></td>
		 <td><input type="text" id="in_amount" name="inAmount" style="width:90%" onKeyUp="if(event.keyCode!=189){if((isNaN(value)))execCommand('undo')};" onKeyPress="doKeyPress();"  readonly="readonly"/></td>
		 <td><input type="text" id="out_amount" name ="outAmount" style="width:90%" onKeyUp="if(event.keyCode!=189){if((isNaN(value)))execCommand('undo')};" onKeyPress="doKeyPress();" readonly="readonly"/></td>
		 <td>${balance }</td>
	  </tr>
	 <tr  class="add_new" style="display:none;"><td colspan="2"><button type="button" id="NewSave" class="btn_list_add"onclick="doSave();">保存</button>
	    </td>
	</tr>
	</s:form>
	<s:iterator value="results">
	  <tr class="mainTr"  style="cursor:pointer;height:35px">
	     <td>
	      <s:if test="colspanNum==1">
	       <security:authorize ifAnyGranted="A_RJZ_ADMIN">
	        <button type="button"  class="btn_list_add" onclick="doAttent('${finInOutDetailId}');">选中</button>
	       </security:authorize>
	        <security:authorize ifAnyGranted="A_RJZ_USER,A_RJZ_MANAGER">
	        <s:set var="vdate"><%=DateOperator.formatDate((Date)JspUtil.findValue("createdDate"))%></s:set>
	        <s:if test="haveNowDate ==#vdate">
	         <s:if test="haveChecked==1">
	          <button type="button"  class="btn_list_add" onclick="doAttent('${finInOutDetailId}');">选中</button>
	          </s:if>
	        </s:if>
	        </security:authorize>
	     </s:if>
	     </td>
	     <s:if test="colspanNum==5">
	         <td><s:date name ="createdDate" format="yyyy-MM-dd"/></td>
	         <td colspan="5" align="center">
	         ${summaryDesc }
	         </td>
	         <td align="right"><%=JspUtil.formatMoney(JspUtil.findString("balance"))%></td>
	     </s:if>
	     <s:elseif test="colspanNum==3">
	        <td bgcolor="#FFFFbe"><s:date name ="createdDate" format="yyyy-MM-dd"/></td>
	        <td colspan="3" align="center" bgcolor="#FFFFbe">
	         ${summaryDesc }
	         </td>
	         <td bgcolor="#FFFFbe" align="right"><%=JspUtil.formatMoney(JspUtil.findString("inAmount"))%></td>
	         <td bgcolor="#FFFFbe" align="right"><%=JspUtil.formatMoney(JspUtil.findString("outAmount"))%></td>
	         <td bgcolor="#FFFFbe" align="right"><%=JspUtil.formatMoney(JspUtil.findString("balance"))%></td>
	     </s:elseif>
	     <s:else>
	     <td>
	     <s:date name ="createdDate" format="yyyy-MM-dd"/>
	     </td>
	     <td><%=orderNo%></td>
	     <td>
	     <span id="sum_${finInOutDetailId}">${summaryDesc}</span>
	     <span id="detl_sum_${finInOutDetailId}" style="display:none">
	     	<input type="text" id="summary_<s:property value ="finInOutDetailId"/>" value="${summaryDesc}" style="width:90%"/>
	     </span>
	     </td>
	     <td>
	     <span id="item_${finInOutDetailId}"><p:code2name mapCodeName="mapToItemNames" value="finItemCd" /></span>
	     <span id="detl_item_${finInOutDetailId}" style="display:none">
	     	<input type="text" id="itemName_${finInOutDetailId}" onclick="doItem('${finInOutDetailId}');" value="<p:code2name mapCodeName="mapToItemNames" value="finItemCd" />" />
	     	<input type="hidden" id ="itemCd_${finInOutDetailId}" value="${finItemCd}"/>
	     </span>
	     </td>
	     <td align="right">
	     <span id="span_in_${finInOutDetailId}">
	     <s:if test="inAmount!=0" ><%=JspUtil.formatMoney(JspUtil.findString("inAmount"))%></s:if>
	     </span>
	     <span id="detl_in_${finInOutDetailId}" style="display:none">
	     <input type="text" id="in_${finInOutDetailId}" value="<%=JspUtil.formatMoney2(JspUtil.findString("inAmount"))%>" />
	     </span>
	     </td>
	     <td align="right">
	     <span id="span_out_${finInOutDetailId}">
	     <s:if test="outAmount!=0"><%=JspUtil.formatMoney(JspUtil.findString("outAmount"))%></s:if>
	     </span>
	     <span id="detl_out_${finInOutDetailId}" style="display:none">
	     <input type="text" id="out_${finInOutDetailId}" value="<%=JspUtil.formatMoney2(JspUtil.findString("outAmount"))%>" />
	     </span>
	     </td>
	     <td align="right"><%=JspUtil.formatMoney(JspUtil.findString("balance"))%></td>
	     <%orderNo++; %>
	     </s:else>
	  </tr>
	</s:iterator>
	<tr>
	<td>&nbsp;</td>
	</tr>
	<tr>
	<td>&nbsp;</td>
	</tr>
	</tbody>
 </table>
</div>
</body>
</html>