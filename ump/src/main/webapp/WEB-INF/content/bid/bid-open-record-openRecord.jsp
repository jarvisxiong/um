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
	 border:1px solid #999999;
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
    	<ul id="openRecordChangeTabs" class="ui-tab-nav">
 				<li class="nosel">投标轮次:</li>
 				
 				<s:if test="inputBatchNo != null && inputBatchNo!=0">
					<s:iterator var="tmpBatchNo" begin="1" end="inputBatchNo">		
						<li batchno="<s:property value="#tmpBatchNo"/>">第<s:property value="#tmpBatchNo" />轮</li>
					</s:iterator>
 				</s:if>
 				<s:else>
 					<li class="nosel">无数据</li>
 				</s:else>
			</ul>
			
			
			
		<div id="bidLedgerDetailPanel" class="ledgerDetailPanel" style="clear:left">
       	</div>
       	</div>
	
<script language="javascript">
var _ctx = '${ctx}';
$(function(){

	//注册轮次事件
	$('#openRecordChangeTabs li').click(function() {
	
		if( 'nosel' != $(this).attr('class')){ 
			$(this).siblings().removeClass('ui-tab-nav-click');
			$(this).addClass('ui-tab-nav-click');

			//触发查询
			var tmpBatchNo = $(this).attr('batchno');
			if(tmpBatchNo){
				loadBidOpenRecord('${bidLedgerId}',tmpBatchNo);
			}
		}			
	});
	
	//触发事件
	$("li[batchno='${inputBatchNo}']").click();
});


//加载开标信息
function loadBidOpenRecord(bidLedgerId,batchNo){
	TB_showMaskLayer("正在载入...");
	var url=_ctx+"/bid/bid-open-record!openRecordView.action";
	$.post(url, {bidLedgerId : bidLedgerId,inputBatchNo:batchNo}, function(result) {
		TB_removeMaskLayer();
		$('#bidLedgerDetailPanel').html(result);
		$("input").attr("readonly","readonly");	
	});	
}

</script>
</body>		
</html>

