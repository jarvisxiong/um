<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看投标单位上传附件情况</title>
	
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	<%--<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/superTables.css"  />--%>		
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/jqueryplugin/fixedheadertable/css/myTheme.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bid/bid.css"  />	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bid/strategy.css"  />	
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<%-- <script type="text/javascript" src="${ctx}/resources/js/common/superTables.js"></script>--%>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/fixedheadertable/jquery.fixedheadertable.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/fixedheadertable/jquery.fixedheadertable.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bid/strategy-bid-ledger.js"></script>
	
	<%--自然伸展，不控制高度，同时屏蔽 superTable.css 里的 sData.{overflow:hidden}
	<style>
		.fakeContainer { /* The parent container */
		    padding: 0px;
		    border: none;
		    width: 800px; /* Required to set */
		    height: 383px; /* Required to set */
		    overflow: hidden; /* Required to set */
		}
	</style>
	--%>
</head>

<body>
 	
	<div class="title_bar">
	
		<div class="fLeft banTitle">战略投标明细</div>
		<div class="fLeft ">正在查看:${bidLedger.orgName}&nbsp;/&nbsp;${bidLedger.bidSectionName}</div>
		
 		<div class="fRight">
 		    <input type="button"  class="btn_new btn_blue_new"  style="width:100px"  onclick="showBidOpenRecord()"  value="查看开标记录" />
	 		<input type="button"  class="btn_new btn_full_new"    onclick="fullScreen('${ctx}/bid/bid-sup!attachList.action?bidLedgerId=${bidLedger.bidLedgerId}');"  value="全屏" />
	 		<input type="button"  class="btn_new btn_refresh_new" onclick="window.location.href=location.href" value="刷新" />
 		</div>
	</div>
	
	<div class="mainContent">
    	<%--台账明细 --%>
    	<input type="hidden" id="bidLedgerId" value="${bidLedgerId }"/>
    	
    	<%--投标总价 --%>
        <div>
          <table id="totalPriceTable" class="stat_table" style="margin-bottom:15px">
		   <thead>
			<tr>
				<th width="40" style="background-image: url('');text-align: center;" rowspan="2">序号</th>
				<th width="120" style="text-align: center;" rowspan="2">投标单位</th>
				<th class="headId" style="text-align: center;" colspan="${bidLedger.batchNo}">投标总价(元)</th>
			 </tr>
			<tr>
			<s:iterator var="st1" begin="1"  end="bidLedger.batchNo" status="status" >
			   <s:if test="#st1==bidLedger.batchNo">
			    <th width="150" nowrap="nowrap" style="text-align: center; font-size:16px">第<s:property value="#st1"/>轮</th>
			   </s:if>
			   <s:else>
			    <th width="150" nowrap="nowrap" style="text-align: center; color:gray">第<s:property value="#st1"/>轮</th>
			   </s:else>
			</s:iterator>
		     </tr>
		</thead>
		<tbody>
		 <s:iterator value="bidSups" var="bidSup" status="stas">	
				<tr>
					<td style="text-align: center;">
						<span><s:property value="#stas.index+1"/>	</span>			
		     		</td>
					<td title="${bidSup.supName}" style="text-align: center;text-align: left;padding-left: 5px;">
						<div class="partHideDiv" style="width: 120px;">${bidSup.supName}</div>					
					</td>
				  <s:iterator value="#bidSup.bidSupAttachRels" var="attachRel" status="stas">
					<td style="width: 120px;text-align: center">
							${attachRel.totalPrice}
					</td>
				  </s:iterator>
				</tr>
		  </s:iterator>
		</tbody>
	</table>
        </div>
		<%--投标单位 --%>
		<div id="supListPanel">
		<%--
			<%@ include file="bid-sup-supAttachList.jsp"%>
		 --%>
		</div>
   	</div>
   	
   	
	<script language="javascript">
		$(function(){
			loadStrategyAttachList('${bidLedgerId}');
		});

		//打开开标记录信息新页面
		function showBidOpenRecord(bidLedgerId){
		 openWindow('bidOpenRecord','开标记录',_ctx + '/bid/bid-open-record!openRecord.action?bidLedgerId=${bidLedgerId}');
		} 
	</script>
</body>		
</html>

