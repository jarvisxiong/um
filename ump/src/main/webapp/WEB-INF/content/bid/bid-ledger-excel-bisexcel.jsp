<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>商务标</title>
	
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	
	<link type="text/css" rel="stylesheet"  href="${ctx}/css/desk/mailStyle.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bid/bid.css"  />	
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
		
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bid/bid-ledger-excel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bid/bid-ledger-excel-imp.js"></script>
</head>
<body>
	<div class="title_bar">
		<div class="fLeft banTitle">商务标</div>
		<input type="button" class="fRight btn_new btn_refresh_new" onclick="window.location.href=location.href" value="刷新" />
	</div>
	
	<div class="bidExcelDiv">
			<form id="mainForm" action="${ctx}/bid/bid-excel-imp!importDataValidate.action" method="post" enctype="multipart/form-data">
			<input type="hidden" id="qingdan" name="qingdan"/>
			<input type="hidden" id="currentBatchNo" name="currentBatchNo" value="${bidLedger.batchNo}"/>
			<table class="bidTable" style="background-color: #f7f7f7;border-bottom-color: #aaabb0">
				<col width="150px"/>
				<col />
				<tr>
					<th style="border-left-color: #aaabb0;">项目名称：</th>
					<td><span>${bidLedger.orgName}</span></td>
				</tr>
				<tr>
					<th  style="border-left-color: #aaabb0;">标段名称：</th>
					<td><span>${bidLedger.bidSectionName}</span></td>
				</tr>
				<tr>
					<th style="border-left-color: #aaabb0;">请选择工程：</th>
					<td><span>
							<select style="float:left;margin-left: 5px;width: 300px;margin-top: 5px;" id="projectId" name="projectId"   onchange="changePro(this);autoSearch(this);">
								<option value="" >-</option>
								<s:iterator value="bidPros"  var="pro"  status="index">
									<option value="${bidProjectId}" >${projectName}</option>
								</s:iterator>	
							</select><font color="red" id="tipPro">请选择工程！</font>	
							<span class="resultTip" id="tipBidProject"></span>
						</span>
					</td>
				</tr>
				<tr>
					<th style="border-left-color: #aaabb0;">请选择标底或投标单位：</th>
					<td><span>
						 <select style="float:left;margin-left: 5px;width: 300px;margin-top: 5px;" id="bidSupId" name="bidSupId" onchange="changeSup(this);autoSearch(this);">
							<option value="" typeCd="" >-</option>	
							<s:iterator value="bidSups"  var="sup"  status="index">
							<%--start如果是邀标状态 ,只显示标底单位 --%>
							<s:if test="bidLedger.bidStatusCd==1">
								<%--2表示标底单位 --%>
								<s:if test="typeCd==2">
									<option value="${bidSupId}" typeCd="${typeCd}" 
									 	<s:if test="typeCd==2">
									    	style="color:red;"
									 	</s:if>
								    >
								    	<s:if test="bidLedger.visableFlg==1">
											${supName}
										</s:if>
										<s:else>
										<%--由于全部显，注销 --%>
										<%--<%=StringUtils.leftPad(JspUtil.findString("dispOrderNo"), 3, "0") %> --%>
										${supName}
										</s:else>
								    </option>
								</s:if>
								 
							</s:if>
							<%-- end 如果是邀标状态 --%>
							<%-- start 如果是投标状态,只显示投标单位 --%>
							<%-- 
							<s:elseif test="bidLedger.bidStatusCd==2">							
								<s:if test="typeCd==1">
									<option value="${bidSupId}" typeCd="${typeCd}" 
									 	<s:if test="typeCd==2">
									    	style="color:red;"
									 	</s:if>
								    >
								    	${supName}
								    	<s:if test="typeCd==2">
								    	[标底单位]
								    	</s:if>
								    </option>
								</s:if>
							</s:elseif>
							--%>
							<%-- end 如果是投标状态 --%>						
							<%--如果非要标阶段和投标阶段，则可以显示标底和供应商 --%>
							<s:else>
								<option value="${bidSupId}" typeCd="${typeCd}" 
									 	<s:if test="typeCd==2">
									    	style="color:red;"
									 	</s:if>
								    >
								    	<s:if test="bidLedger.visableFlg==1">
											${supName}
										</s:if>
										<s:else>
											<%--由于全部显，注销 --%>
											<%--<%=StringUtils.leftPad(JspUtil.findString("dispOrderNo"), 3, "0") %> --%>
											${supName}
										</s:else>
								    </option>
							</s:else>
							</s:iterator>
						</select><font color="red" id="tipSup">请选择标底或投标单位！</font>
						<span class="resultTip" id="tipBidSup"></span>
						</span>
					</td>
				</tr>
				<s:if test="bidLedger.bidStatusCd==2">
				   <security:authorize ifAnyGranted="A_BID_EDIT,A_ADMIN_BID">
						<tr>
							<th style="border-left-color: #aaabb0;">导出：</th>
							<td >
								<div style="float:left;line-height: 25px;margin-top:3px; margin-left: 5px;">
								<%--只有在邀标和投标状态下才能导入;0,导入1,邀标2、投标 --%>						
									<input type="button" class="btn_new btn_blue_new" style="width: 120px;" onclick="exportTemplate('${bidLedger.batchNo}','normal');" title="用于导出模板，填写数据后导入系统" value="导出投标文件模板"/>		
									<input type="button" class="btn_new btn_green_new" style="width: 90px;" onclick="exportTemplate('${bidLedger.batchNo}','downList');" title="仅用于导出清单，用于搜索" value="导出投标清单"/>				
									<input type="button" class="btn_new btn_green_new" style="width: 90px;" onclick="exportTemplate('${bidLedger.batchNo}','umimported');" title="仅用于比对导入数据与清单项的差异" value="未导入清单项"/>		
								</div>	
							</td>						
						</tr>
					</security:authorize>
				</s:if>
				<s:if test="bidLedger.bidStatusCd==2">
				<security:authorize ifAnyGranted="A_BID_EDIT,A_ADMIN_BID">
				<tr>
					<th style="border-left-color: #aaabb0;">导入：</th>
					<td >
						<div  class="exportExcel">
						<%--只有在邀标和投标状态下才能导入;0,导入1,邀标2、投标 --%>
							<input type="file" class="btn_new" style="line-height:22px;height:27px;cursor:pointer;width: 300px;" name="importFile" id="importFile" />
							<%--<div class="plblue"  onclick="importData();" style="padding-left:2;">导入数据</div> --%>	
							<input type="button" class="btn_new btn_blue_new" style="width: 120px;"  id="importQingdan" onclick="importQingdan2();"  title="点击此按钮，用于导入清单数据" value='导入投标文件清单'/>							
							<input type="button" class="btn_new btn_green_new" style="width: 120px;"  onclick="importDataValidate();"  title="点击此按钮，用于导入投标文件的数据"  value="导入投标文件数据"/>	
						</div>
					</td>
				</tr>
				</security:authorize>
				</s:if>
			</table>

			<br/>
			
			<div class="resultTip" id="tipBatch"></div>
			<div class="resultTip" id="tipFile"></div>
			
			<div style="width:100%;clear: both">
				<ul id="batch-nav" class="batch-nav">
	 				<li class="nosel">报价轮次:</li>
	 				<s:if test="bidLedger.batchNo != null">
		 				<!-- 从1至bidLedger.batchNo -->
		 				<!--  <li batchno="0">标底</li> -->
						<s:iterator var="counter" begin="1" end="bidLedger.batchNo">		
						<li batchno="<s:property value="#counter"/>">第<s:property value="#counter" />轮</li>
						</s:iterator>
	 				</s:if>
	 				<s:else>
	 					<li class="nosel">无数据</li>
	 				</s:else>
				</ul>
			</div>
		</form>
		
		<br/>
		
		<div class="resultTip" id="tipItem"></div>
		<div style="width:100%;clear: both;margin:10px 0;">
			<ul class="item-nav" id="item-nav">
				<!-- 这里不指定url,根据单位类型,赋相应的url -->
				<li url ="${ctx}/bid/bid-divisiton!list.action">清单列表</li>
				<li url ="${ctx}/bid/bid-division-sup-rel!list.action">分部分项</li>
				<li url="${ctx}/bid/bid-measure-sup-rel!list.action">措施项目1</li>
				<li url="${ctx}/bid/bid-measure-sup-rel2!list.action">措施项目2</li>
				<li url="${ctx}/bid/bid-other-item-rel!list.action">其他项目</li>
				<li url="${ctx}/bid/bid-sporadic-sup-rel!list.action">零星工程</li>
				<li url="${ctx}/bid/bid-fees-sup-rel!list.action">规费项目</li>
				<li url="${ctx}/bid/bid-taxs-sup-rel!list.action">税金项目</li>
				<li url="${ctx}/bid/bid-sup-other-rel!list.action">标段其他费用</li>
				<li url="${ctx}/bid/bid-divisiton!importTotal.action" >汇总</li>
				<%--屏蔽商务标中‘增添项偏差汇总’sheet --%>
				<%-- 
				<li url="${ctx}/bid/bid-divisiton-cust!totalDifference.action" >增添项偏差汇总</li>
				--%>
			</ul>			
		</div>
		
		<div style="clear:left;display: none;" class="resultTip">提示:请选择"报价轮次",点击"分部分项"等进行搜索</div>
		<%--异常未 验证信息--%>
			<div id="delayWindowDiv" 
			style="display:none;position: absolute; width: 600px; height: 270px; top: 140px; left; 0 px; display: none; background-color: #fff; border: 1px solid #000; padding: 5px;margin-left: 140px;overflow: scroll;">
			</div>
		<!-- 根据选择的表数据,动态替换 -->
		<div id="tableResult" style="clear: both">
			 
		</div>
	</div>

	
<script language="javascript">

	$(function() {
		//注册轮次事件
		$('#batch-nav li').click(function() {
			if( 'nosel' != $(this).attr('class')){ 
				$(this).addClass('batch-nav-click').siblings().removeClass('batch-nav-click');
				//触发点击事件
				$("#item-nav >li[class=item-nav-click]").click();
			}			
		});
		//注册点击搜索事件
		$('#item-nav li').each(function() {
			$(this).attr('title','点击搜索'); 
		});
		$('#item-nav li').click(function() {
			$(this).addClass('item-nav-click').siblings().removeClass('item-nav-click');
			if (validate()) {//验证下拉框
				ajaxTable(1);//默认第一页
			} 
		});
	});

</script>
</body>
</html>
