<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>成本投标台账</title>
	
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	
	<link type="text/css" rel="stylesheet"  href="${ctx}/css/desk/mailStyle.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bid/bid.css"  />	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bis/ymPrompt.css" /> 
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
</head>
<style>
  table .required{
    
    border: 1px solid #999999;height:20px;
    border-left: 2px solid red;
  }
  table input[type="text"]{
     height:20px;
	 background-color: transparent;
    border-color: -moz-use-text-color -moz-use-text-color #999999;
    border-style: none none solid;
    border-width: 0 0 1px;
  }
  .bidOpenTable td{
    line-height:35px;
  }

  .opennerTable{
    border-collapse :  collapse;
	}
  .opennerTable td{
    border:#DDDBDC 1px solid;
	padding-left:2px;
	padding-bottom:5px;
  } 
  
  .opennerTable tr{
	line-height:35px;

  } 
  .opennerTable input[type="text"]{
     height:20px;
	/* border:1px solid #999999;*/
	 width:95%;
	}

   .commonTable input[type="text"]{
     height:20px;
	 /*border:1px solid #999999;*/
	 width:95%;
	}
</style>
<body>
	
	 
	<div class="mainContent">
    	
		<div id="bidLedgerDetailPanel" class="ledgerDetailPanel">
				<form id="bidOpenRecordForm" method="post" action="${ctx}/bid/bid-open-record!save.action">
				<input type="hidden" name="id" value="${bidOpenRecordId}" />
				<input type="hidden" name="bidLedger.bidLedgerId" id="bidLedgerId" value="${bidLedgerId }"/>
				<input type="hidden" name="batchNo" id="batchNo" value="${batchNo}"/>
			   <table width="100%" border="0" class="bidOpenTable">
			        <col width="100px"/>
		       		<col />
		       		<col width="115px"/>
		       		<col />
		       		<col width="120px"/>
		       		<col />
		       		<tr>
		       		   	<td  style="border-left-color: #aaabb0;" class="rowItem" colspan="6">
							<div class="fLeft headTitle">开标信息(第${batchNo}轮)</div>
							<div class="fLeft">		</div>						</td>
		       		</tr>	
				    <tr>
				      <td align="right"><img src="${ctx}/resources/images/desk/logo_powerlong.jpg" width="95"  /></td>
				      <td colspan="4" align="center"><span style="font-size:16px;font-weight: bold;" >开 标 情 况 汇 总 表</span><br />
			          (第${batchNo}轮)</td>
				      <td><table width="95%" border="0">
					  <col width="60px"/>
		       		   <col />
                        <tr>
                          <td >编号：</td>
                          <td><input name="itemNo" type="text" id="itemNo" style="width:100px"/></td>
                        </tr>
                        <tr>
                          <td>版号：</td>
                          <td><input name="versionNo" type="text" id="versionNo" style="width:100px"/></td>
                        </tr>
                      </table></td>
		         </tr>
				    <tr>
				      <td align="right">&nbsp;</td>
				      <td>&nbsp;</td>
				      <td align="right">&nbsp;</td>
				      <td>&nbsp;</td>
				      <td align="right">&nbsp;</td>
				      <td>&nbsp;</td>
				    </tr>
			      <tr>
				    <td align="right">招标项目名称：</td>
				    <td><input name="bidSectionName" type="text" id="bidSectionName" value="${bidSectionName}"/></td>
				    <td align="right">工程名称：</td>
				    <td><input name="construction" type="text" id="construction"  value="${construction}"/></td>
				    <td align="right">执行董事审阅后签名：</td>
				    <td><input name="sign" type="text" id="sign" /></td>
				  </tr>
				  <tr>
				    <td align="right">回标截止时间：</td>
				    <td><input name="lastReceDate" type="text" id="lastReceDate"  onfocus="WdatePicker()" class="Wdate"  value='<s:property  value="lastReceDate" />' style="width:143px" /></td>
				    <td align="right">开标时间：</td>
				    <td><input name="openDate" type="text" id="openDate"  onfocus="WdatePicker()" class="Wdate" style="width:143px"/></td>
				    <td align="right"> 邀请单位数量：</td>
				    <td><input name="inviteSupNum" type="text" id="inviteSupNum" value="${inviteSupNum}"/>
			        个</td>
				  </tr>
				  <tr>
				    <td align="right">项目目标成本：</td>
				    <td><input name="projectCost" type="text" id="projectCost" />
			        元</td>
				    <td align="right">建筑面积：</td>
				    <td><input name="builtArea" type="text" value="${sectionTotalArea}" />
			        ㎡</td>
				    <td align="right">投标单位数量： </td>
				    <td><input name="receiveSupNum" type="text" id="receiveSupNum"  value="${receiveSupNum}"/>
			        个</td>
				  </tr>
				  <tr>
				    <td align="right">标                底：</td>
				    <td><input name="basePrice" type="text" id="basePrice" />
				    元</td>
				    <td align="right">&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td colspan="6">&nbsp;</td>
			      </tr>
				</table>
                
       		   <table width="100%" border="0" class="opennerTable">
			     <col width="100px"/>
				 <col />
				 <col />
				 <col />
				 <col/>
			     <col />
			     <col/>
				 <col/>
                 <tr style="line-height:20px">
                   <td>&nbsp;</td>
                   <td align="center">1</td>
                   <td align="center">2</td>
                   <td align="center">3</td>
                   <td align="center">4</td>
                   <td align="center">5</td>
                   <td align="center">6</td>
                   <td align="center">7</td>
                 </tr>
                 <tr>
                   <td>参加开标的部门</td>
                   <td><input name="openDep1" type="text" id="openDep1" /></td>
                   <td><input name="openDep2" type="text" id="openDep2" /></td>
                   <td><input name="openDep3" type="text" id="openDep3" /></td>
                   <td><input name="openDep4" type="text" id="openDep4" /></td>
                   <td><input name="openDep5" type="text" id="openDep5" /></td>
                   <td><input name="openDep6" type="text" id="openDep6" /></td>
                   <td><input name="openDep7" type="text" id="openDep7" /></td>
                 </tr>
                 <tr>
                   <td>参加开标的人员</td>
                   <td><input name="openner1" type="text" id="openner1" /></td>
                   <td><input name="openner2" type="text" id="openner2" /></td>
                   <td><input name="openner3" type="text" id="openner3" /></td>
                   <td><input name="openner4" type="text" id="openner4" /></td>
                   <td><input name="openner5" type="text" id="openner5" /></td>
                   <td><input name="openner6" type="text" id="openner6" /></td>
                   <td><input name="openner7" type="text" id="openner7" /></td>
                 </tr>
               </table>
               
               <%--投标单位 --%>
		<div id="supListPanel" class="supList"></div>
		<div style="padding:5px">说明：如密封、技术标、商务标、光盘等不满足投标要求，请在备注栏表明。</div>
		<div align="center" style="margin-bottom:5px"> 
		  <input id="btnSave" class=" btn_new btn_add_new " type="button" value="保存"/>
		  <input id="btnCancel" class=" btn_new btn_add_new " type="button" value="取消"/>
		</div>
		</form>
       	</div>
       	</div>
	
<script language="javascript">

$(function(){

	loadBidSup('${bidLedgerId}');

	$("#btnSave").click(function(){
		$("#bidOpenRecordForm").ajaxSubmit(function(result){
			if("success"!= result){
                  alert("保存不成功！\r\n" + result);
                  return;
				}
			var info ={};
			info.result = result ;
			window.parent.ymPrompt.doHandler(info, true);
			});
		});

	$("#btnCancel").click(function(){
		
			window.parent.ymPrompt.doHandler('', true);
			
		});
});


//加载投标单位信息
function loadBidSup(bidLedgerId){
	TB_showMaskLayer("正在载入...");
	var url=_ctx+"/bid/bid-sup!list.action";
	$.post(url, {bidLedgerId : bidLedgerId,flag:'open'}, function(result) {
		TB_removeMaskLayer();
		$('#supListPanel').html(result);		
	});	
}

</script>
</body>		
</html>

