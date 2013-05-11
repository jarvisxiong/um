<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>网上招标（战略）明细</title>
	
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/mailStyle.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bid/bid.css"  />	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bid/strategy.css"  />	
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
	<script type="text/javascript" src="${ctx}/resources/js/bid/strategy-bid-ledger.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bid/bidSingleUpload.js"></script>
</head>
<style>
  table .required{
    border-left: 2px solid red;
  }
</style>
<body>
	<div class="title_bar">
		<div class="fLeft banTitle" style="margin-left:10px;">招标平台（战略）明细</div>
		
		
		<div class="fRight">
		
			<%--只有存在投标轮次(>0)且在评标(>=3)之后，才允许查看 --%>
			<s:if test=" entity.batchNo > 0 && entity.bidStatusCd >= 3"> 
			   <security:authorize ifAnyGranted="A_BID_STRA_EVAL">
				<input  type="button"  class="btn_new btn_blue_new" onclick="supStrategyAttach('${bidLedgerId}');" style="width:100px;" value="查看投标情况"/>
			   </security:authorize>
			<!--
				<input  type="button"  class="btn_new btn_blue_new" onclick="strategyEval('${bidLedgerId}','${entity.batchNo}');" style="width:100px;" value="评标文件汇总"/>-->
			</s:if>
			<s:else>
			
				<security:authorize ifNotGranted="A_BID_STRA_OPEN">
					
				</security:authorize>
				
			</s:else>
			
		  	<input type="button" class="btn_new btn_full_new " onclick="window.open(location.href)" style="margin-left:-0.2px" value="全屏"/>
			<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href" style="margin-left:-0.2px" value="刷新"/>
		</div>
	</div>
	
   	<%--台账明细 --%>
	<div class="mainContent">
	
    	<input type="hidden" id="bidLedgerId" value="${bidLedgerId }"/>
		<div id="bidLedgerDetailPanel" class="subPanel" style="margin-bottom:10px">
			<div class="subTitle">招标信息
				<div class="resultTip" id="bidLedgerSuccInfoId" style="display: none;"></div>
			</div>
			
			<form id="submitBidLedgerForm" method="post" action="${ctx}/bid/bid-ledger!save.action">	
				<input type="hidden" name="id" id="bidLedgerId" value="${bidLedgerId}"/>				
				<!-- 保存变更值的字段名称 -->
				<input type="hidden" name="changeFieldName"/>
				<input type="hidden" name="changeFieldValue" />
				<input type="hidden" name="hasEvalFiles" value="${hasevalFiles}"/>	
		  		<table class="bidTable" cellpadding="0" cellspacing="0"> 
 					<col width="120px"/>
		       		<col />
		       		<col width="115px"/>
		       		<col />
		       		<col width="100px"/>
		       		<col width="140px"/>
			       		<tr>
			       			<th >战略名称</th>
			       			<td>
			       				<span> ${entity.bidSectionName}</span>			       			</td>
			       			
			       		   	<th>战略计划编号</th>
			       		   	<td ><span>${entity.serialType}${entity.serialNo}</span> </td>
			       		   	
			       			<th>保证金</th>
				       		<td>
					       		<div style="float: left;margin-left: 0px; ">
					       		<s:if test="entity.bidStatusCd==0">
					       		<input type="checkbox" 
					       			   id="needGuarFlg" 
					       			   name="needGuarFlg" 
					       			   bidLedgerId="${bidLedgerId}" 
					       			   onchange="changeNeedGuarFlg(this);"
					       			   style="float: left;line-height: 30px;width: 20px;margin-top: 10px;" 	
					       			   disabled="disabled"  
					       			   checked="true"
						       		   <%--<s:if test="entity.needGuarFlg==1">
						       			checked="true"			       				
						       			</s:if>	
						       			<s:else>   				
						       			</s:else>			--%>			       			
					       			/>		      		   				
		      		   			</s:if>
		      		   				<div id="needGuarFlgTip" style="float: left;">
		      		   				需要	
		      		   					<%--<s:if test="entity.needGuarFlg==1">
						       				需要					       				
						       			</s:if>
						       			<s:else>	
						       				不需要					       				
						       			</s:else>--%>
					       			</div>
					       		</div>				       		</td>
			       		</tr>
			       		
			       		<tr>
			       			<th >项目名称</th>
			       			<td>
			       				<span> ${entity.orgName}</span>			       			</td>
			       			<th>期数</th>
			       		   	<td >
				       		   	<span> ${entity.periodNum}</span>			       		   	</td>
			       		   	<th>工程</th>
			       		   	<td >
				       		   	<span> ${entity.construction}</span>			       		   	</td>
			       		</tr>
			       		
			       		<tr>
				       		<th>保证金截止日期</th>
				       		<td style="padding-left: 5px;">
		       					<s:if test="entity.bidStatusCd==0">		      		   				
					       			<input 
				      		   			type="text" 
				      		   			name="lastGuarDate" 
				      		   			id="lastGuarDate"  
				      		   			<%-- onchange="verificationTime('lastGuarDate')"--%>
				      		   			value='<s:date name="entity.lastGuarDate" format="yyyy-MM-dd" />' 
				      		   			validate="required" 
				      		   			class="Wdate inputBorder required validate[required]"  
				      		   			onfocus="WdatePicker({minDate:new Date('yyyy-MM-dd')})" 
				      		   			style="width: 120px;" />
		      		   			</s:if>
		      		   			<s:else>
		      		   				<s:date name="entity.lastGuarDate" format="yyyy-MM-dd" />
		      		   			</s:else>				      		</td>
				      		
				       		<th>应标截止日期</th>
				       		<td style="padding-left: 5px;">
	       					  <s:if test="entity.bidStatusCd==0">		  
			       					<input 
				      		   			type="text" 
				      		   			name="lastReceDate" 
				      		   			id="lastReceDate" 
				      		   			<%--onchange="verificationTime('lastReceDate')"--%>
				      		   			value='<s:date name="entity.lastReceDate" format="yyyy-MM-dd" />' 
				      		   			validate="required" 
				      		   			class="Wdate inputBorder required" 
				      		   			onfocus="WdatePicker({minDate:new Date('yyyy-MM-dd')})" 
				      		   			style="width: 120px;" />
		       					</s:if>
		      		   			<s:else>
		      		   				<s:date name="entity.lastReceDate" format="yyyy-MM-dd" />
		      		   			</s:else>				      		</td>
				       		
				       		<th>邀标类型</th>
			       			<td><span>
					       		   	<s:if test="entity.visableFlg==1">明标</s:if>
					       		   	<s:elseif test="entity.visableFlg==2">暗标</s:elseif>
					       		   	<s:else>${entity.visableFlg}</s:else>
				       		   	</span>				       				</td>
			       		</tr>
			       		<tr>
			       		  <th>邀标网批查询号</th>
			       		  <td style="padding-left: 5px;">
			       		        <span>
				       				<s:if test="entity.resApproveInfoId!= null && entity.resApproveInfoId!= ''">
				       					<a  style="color:red;" href="javascript:void(0)" 
				       						title="点击查看网批记录"
				       						onclick="openResTask('${resApproveInfo.resApproveInfoId}','${resApproveInfo.approveCd}${resApproveInfo.serialNo}','${resApproveInfo.authTypeCd}');">
				       						${resApproveInfo.displayNo}	</a>				       				
				       				</s:if>
				       				<s:else>
				       					<span style="width: 100px;margin-left: 10px;">-</span>
				       				</s:else>
			       				</span>
			       			</td>
			       		  <th>招标文件网批查询号</th>
			       		  <td style="padding-left: 5px;">
			       		        <span>
				       				<s:if test="entity.callResId!= null && callResId!= ''">
				       					<a  style="color:red;" href="javascript:void(0)" 
				       						title="点击查看网批记录"
				       						onclick="openResTask('${callResApproveInfo.resApproveInfoId}','${callResApproveInfo.approveCd}${callResApproveInfo.serialNo}','${callResApproveInfo.authTypeCd}');">
				       						${callResApproveInfo.displayNo}	</a>				       				
				       				</s:if>
				       				<s:else>
				       					<span style="width: 100px;margin-left: 10px;">-</span>
				       				</s:else>
			       				</span>
			       			</td>
			       		  <th>定标网批查询号</th>
			       		  <td>  <span>
				       				<s:if test="entity.bidResId!= null && bidResId!= ''">
				       					<a  style="color:red;" href="javascript:void(0)" 
				       						title="点击查看网批记录"
				       						onclick="openResTask('${bidResApproveInfo.resApproveInfoId}','${bidResApproveInfo.approveCd}${bidResApproveInfo.serialNo}','${bidResApproveInfo.authTypeCd}');">
				       						${bidResApproveInfo.displayNo}	</a>				       				
				       				</s:if>
				       				<s:else>
				       					<span style="width: 100px;margin-left: 10px;">-</span>
				       				</s:else>
			       				</span>
			       				</td>
	       		  </tr>
			       		<%--上传附件 --%>
			       		<tr >
			       			<th>
			       				招标文件			       			</th>
			       			<td colspan="3" style="padding-left:5px" class="required">
					       			 
								<%-- 招标网批单
								<s:if test="entity.callResId != null">
									<input type="button" class="btn_new btn_green_new" style="width:110px;" value="查看招标网批单" onclick="showPageLink('${ctx}/res/res-approve-info.action?id=${entity.callResId}','resApprove');"/>
								</s:if> --%>
								<s:if test="entity.bidStatusCd==0">
				       				 <security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">
					       				<div style="float: left;display: inline;line-height: 30px;">
					       					<input type="button" 
					       					   value="上传附件" 
					       					   class="btn_new btn_upload_new" 
					       					   style="width: 80px;" 
					       					   onclick="showUploadSingleAttachDialog('请选择招标文件','${bidLedgerId}','bidLedgerGurantee','attaTendFileFlg','BidLedger','attaTendFileFlg',event);"/>	
					       				</div>	
					       			 </security:authorize>
				       				</s:if>
						 		 <%-- 历史附件 --%>
			       				 <div id="show_attaTendFileFlg"  style="border:none;float: left;clear: none;margin-left: 5px;"> &nbsp;
				       				 <s:if test="entity.callResAttachId != null">
										<a title="点击下載招标文件" href="${ctx}/app/download.action?fileName=${callAttachFile.fileName}&amp;realFileName=${callAttachFile.realFileName}&amp;bizModuleCd=${callAttachFile.bizModuleCd}&amp;filterType=&amp;id=${callAttachFile.appAttachFileId}" 
										style="text-decoration:underline;color: #0167A2;">${callAttachFile.realFileName}</a>								</s:if>
									
				       				
			       					<s:if test="attaTendFile!=null">			       						
						    			<s:url id="down" action="download" namespace="/app">
									  	  <s:param name="fileName">${attaTendFile.fileName}</s:param>
									  	  <s:param name="realFileName">${attaTendFile.realFileName}</s:param>
									  	  <s:param name="bizModuleCd">${attaTendFile.bizModuleCd}</s:param>
									  	  <s:param name="filterType">${filterType}</s:param>
									  	  <s:param name="id">${attaTendFile.appAttachFileId}</s:param>
									  	  <s:param name="fieldName">${attaTendFile.bizFieldName}</s:param>
									  	  <s:param name="bizFieldName">${attaTendFile.bizFieldName}</s:param>
										</s:url>
										<a href="${down}">${attaTendFile.realFileName}</a>
										<s:if test="entity.bidStatusCd==0">	
										 <security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">										
											<font onclick="deleteFile('${attaTendFile.appAttachFileId}','attaTendFileFlg','${attaTendFile.bizEntityId}');" 
												style='color:red;font-weight:bold;cursor:pointer;'>×										    </font>										 </security:authorize>
										</s:if>
			       					</s:if>
			       				 </div>			       			</td>
			       			<th>&nbsp;</th>
			       			<td></td>
			       		</tr>
			       		
			       		
			       		
			       		<%--清单(必须上传)  --%>
			       		<tr >
			       			<th>
			       				清单			       			</th>
			       			<td colspan="3" style="padding-left:5px" class="required">
			       			<s:if test="entity.bidStatusCd==0">	
			       			   <security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">
			       				<div style="float: left;display: inline;">
			       					<input type="button" 
			       					   value="上传附件" 
			       					   class="btn_new btn_upload_new" 
			       					   style="width: 80px;" 
			       					   onclick="showUploadSingleAttachDialog('请选择清单(必传)','${bidLedgerId}','bidLedgerGurantee','attaListFlg','BidLedger','attaListFlg',event);"/>	
			       				</div>
			       				</security:authorize>	
			       			</s:if>
			       				<div id="show_attaListFlg"  style="border:none;float: left;clear: none;margin-left: 5px;">&nbsp;
				       				<s:if test="attaList!=null">			       						
						    			<s:url id="down" action="download" namespace="/app">
									  	  <s:param name="fileName">${attaList.fileName}</s:param>
									  	  <s:param name="realFileName">${attaList.realFileName}</s:param>
									  	  <s:param name="bizModuleCd">${attaList.bizModuleCd}</s:param>
									  	  <s:param name="filterType">${filterType}</s:param>
									  	  <s:param name="id">${attaList.appAttachFileId}</s:param>
									  	  <s:param name="fieldName">${attaList.bizFieldName}</s:param>
									  	  <s:param name="bizFieldName">${attaList.bizFieldName}</s:param>
										</s:url>
										<a href="${down}">${attaList.realFileName}</a>	
										<s:if test="entity.bidStatusCd==0">		
										 <security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">									
										<font 
											onclick="deleteFile('${attaList.appAttachFileId}','attaListFlg','${attaList.bizEntityId}');" 
											style='color:red;font-weight:bold;cursor:pointer;'>×</font>										 </security:authorize>
										</s:if>
				       				</s:if>
			       				</div>			       			</td>
			       			<th></th>
			       			<td></td>
			       		</tr>
			       		
			       		<%--技术标准(必须上传) --%>
			       		<tr >
			       			<th>
			       				技术标准			       			</th>
			       			<td colspan="3" style="padding-left:5px" class="required">
				       			<s:if test="entity.bidStatusCd==0">	
				       			  <security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">
				       				<div style="float: left;display: inline;">
				       					<input type="button" 
				       					   value="上传附件" 
				       					   class="btn_new btn_upload_new" 
				       					   style="width: 80px;" 
				       					   onclick="showUploadSingleAttachDialog('请选择技术标准(必传)','${bidLedgerId}','bidLedgerGurantee','attaTechStanFlg','BidLedger','attaTechStanFlg',event);"/>	
				       				</div>
				       				</security:authorize>	
				       			</s:if>
			       				<div id="show_attaTechStanFlg"  style="border:none;float: left;clear: none;margin-left: 5px;">&nbsp;
			       					<s:if test="attaTechStan!=null">			       						
						    			<s:url id="down" action="download" namespace="/app">
									  	  <s:param name="fileName">${attaTechStan.fileName}</s:param>
									  	  <s:param name="realFileName">${attaTechStan.realFileName}</s:param>
									  	  <s:param name="bizModuleCd">${attaTechStan.bizModuleCd}</s:param>
									  	  <s:param name="filterType">${filterType}</s:param>
									  	  <s:param name="id">${attaTechStan.appAttachFileId}</s:param>
									  	  <s:param name="fieldName">${attaTechStan.bizFieldName}</s:param>
									  	  <s:param name="bizFieldName">${attaTechStan.bizFieldName}</s:param>
										</s:url>
										<a href="${down}">${attaTechStan.realFileName}</a>	
										<s:if test="entity.bidStatusCd==0">
										  <security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN"> 											
											<font 
												onclick="deleteFile('${attaTechStan.appAttachFileId}','attaTechStanFlg','${attaTechStan.bizEntityId}');" 
												style='color:red;font-weight:bold;cursor:pointer;'>×</font>										  </security:authorize>
										</s:if>
				       				</s:if>
			       				</div>			       			</td>
			       			<th></th>
			       			<td></td>
			       		</tr>
			       		<%--图纸(可选)  --%>
			       		<tr >
			       			<th>
			       				图纸			       			</th>
			       			<td  colspan="3" style="padding-left:5px">
				       			<s:if test="entity.bidStatusCd==0">
				       			 <security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">
				       				<div style="float: left;display: inline;line-height: 30px;">
				       					<input type="button" 
				       					   value="上传附件" 
				       					   class="btn_new btn_upload_new" 
				       					   style="width: 80px;" 
				       					   onclick="showUploadSingleAttachDialog('请选择图纸(可选)','${bidLedgerId}','bidLedgerGurantee','attaDrawFileFlg','BidLedger','attaDrawFileFlg',event);"/>	
				       				</div>	
				       			  </security:authorize>
				       			</s:if>
			       				<div id="show_attaDrawFileFlg"  style="border:none;float: left;clear: none;margin-left: 5px;">&nbsp;
				       				<s:if test="attaDrawFile!=null">			       						
								    			<s:url id="down" action="download" namespace="/app">
											  	  <s:param name="fileName">${attaDrawFile.fileName}</s:param>
											  	  <s:param name="realFileName">${attaDrawFile.realFileName}</s:param>
											  	  <s:param name="bizModuleCd">${attaDrawFile.bizModuleCd}</s:param>
											  	  <s:param name="filterType">${filterType}</s:param>
											  	  <s:param name="id">${attaDrawFile.appAttachFileId}</s:param>
											  	  <s:param name="fieldName">${attaDrawFile.bizFieldName}</s:param>
											  	  <s:param name="bizFieldName">${attaDrawFile.bizFieldName}</s:param>
												</s:url>
												<a href="${down}">${attaDrawFile.realFileName}</a>	
												<s:if test="entity.bidStatusCd==0">	
												 <security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">									
													<font 
														onclick="deleteFile('${attaDrawFile.appAttachFileId}','attaDrawFileFlg','${attaDrawFile.bizEntityId}');" 
														style='color:red;font-weight:bold;cursor:pointer;'>×</font>												 </security:authorize>
												</s:if>
				       				</s:if>
			       				</div>			       			</td>
			       			<th></th>
			       			<td></td>
			       		</tr>
			       		<%--投标状态--%>
			       		
			       		<%--项目介绍--%>
			       		<tr >
			       			<th>
			       				项目介绍			       			</th>
			       			<td colspan="5">
		       					<textarea 
		       					  name="projIntrDesc" 
		       					  id="projIntrDesc" 
	       					 	  <s:if test="entity.bidStatusCd==0">		      		   				
		      		   			  </s:if><s:else>
		      		   				disabled="true"
		      		   				readonly="readonly"
		      		   			  </s:else>
		       					  style="width: 98%;height:100px;margin-left: 5px;padding:5px; overflow: auto;" >${entity.projIntrDesc}</textarea>			       			</td>
			       		</tr>
			       		<tr >
			       		  <th>当前状态 </th>
			       		  <td colspan="5" style="height:36px">
						  <div  class="fLeft" >
									
										<span style="float:left;" id="batchNoTip">
										<s:if test="entity.batchNo == null || entity.batchNo == ''">第1轮</s:if><s:else>第${entity.batchNo}轮</s:else>
										</span>
								/	</div>	
						        <div id="prevNextPanel">									
									<%@ include file="bid-ledger-strategyNextStatus.jsp"%>
								</div>		<%--保存信息/关闭投标 --%>
       	<div style="vertical-align: middle;padding:0px 0;">
       		<s:if test="entity.bidStatusCd == 0">				
		      	<security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">
       			<input type="button" style="width:80px;" class="fLeft btn_new btn_save_new" onclick="uopdateBidLedger(this);" id="saveBidBtn" value="保存" />
       			<%-- 保存提示! --%>
       			<div class="fLeft" id="bid_detail_tip"></div>
	       		<s:else>
	       		</s:else>  			
	       		</security:authorize>	 
       		</s:if> 
			<s:if test="entity.bidStatusCd == 0">
		    	<security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">
       			<div id="closeBidBtn" class="fRight" style="line-height: 30px;height: 30px;">
	       			<div style="float:left;">若要关闭本次投标，请点击</div>
	       			<input type="button" class="fLeft btn_new btn_cancel_new" onclick="deleteBidLedger(this);" style="width: 80px;" value="关闭投标" />
       			</div>
				</security:authorize>
      		</s:if>
			</td>
	       		  </tr>
			       		<%--备注
			       		<tr >
			       			<th>
			       				备注
			       			</th>
			       			<td colspan="5" style="height: 80px;">
			       				<div style="padding-left: 5px;">
			       					<textarea rows="4"
			       					  name="remark" 
			       					  id="remark"
			       					  style="width: 97%;padding: 5px 5px;" >${entity.remark}</textarea>
			       				</div>	       						       				
			       			</td>
			       		</tr>
			       		--%>
       		</table>       		
      		</form>
       	</div>
       	
       	
        
     		
      						     
       	</div>
       	
       	
      	<%--投标单位 --%>
		<div id="supListPanel" class="supList">
			<%@ include file="bid-sup-strategyList.jsp"%>
		</div>
		
		<%-- 上传评标文件 --%>
	  <s:if test="entity.bidStatusCd >= 3">
			<table style="width:100%" >
				<tr >
					<td class="rowItem" colspan="6">
							<div class="headTitle">招标评审资料</div>	
					</td>
				</tr>
		    </table>
			
			<ul id="evalChangeTabs" class="ui-tab-nav">
 				<li class="nosel">评标轮次:</li>
 				
 				<s:if test="entity.batchNo != null&&entity.batchNo!=0">
					<s:iterator var="tmpBatchNo" begin="1" end="entity.batchNo">		
						<li batchno="<s:property value="#tmpBatchNo"/>">第<s:property value="#tmpBatchNo" />轮</li>
					</s:iterator>
 				</s:if>
 				<s:else>
 					<li class="nosel">无数据</li>
 				</s:else>
			</ul>
			
			
			<div id="evalListPanel"  class="subPanel" style="clear:left;">
			</div>
		</s:if>
   	</div>
   <!--	
	<form action="${ctx}/bid/upload.action" id="formAttaUpload" method="post" enctype="multipart/form-data">
		<input type="hidden" name="bizModuleCd" value="bidLedgerGurantee"/>
		<input type="hidden" name="bidLedgerId"  value="${bidLedgerId}"/>
		<input type="hidden" name="bizEntityId" id="bizEntityIdGen" />
		<div id="attaInput"></div>
	</form>
	-->
	
		
<script language="javascript">
    //投标状态
    var bidStatus = ${entity.bidStatusCd}
	$(function(){
	   
		//加载投标单位
		loadStrategyBidSup('${bidLedgerId}');	
		

		//注册轮次事件
		$('#evalChangeTabs li').click(function() {
		
			if( 'nosel' != $(this).attr('class')&&bidStatus>=3){ 
				$(this).siblings().removeClass('ui-tab-nav-click');
				$(this).addClass('ui-tab-nav-click');

				//触发查询
				var tmpBatchNo = $(this).attr('batchno');
				if(tmpBatchNo){
					strategyEval('${bidLedgerId}',tmpBatchNo);
				}
			}			
		});
		
		//触发事件
		$("li[batchno='${entity.batchNo}']").click();
	});

	function strategyEval(bidLedgerId, batchNo){
		var url = '${ctx}/bid/strategy-bid-ledger!loadEval.action';
		var data = {id: bidLedgerId, batchNo: batchNo};
		TB_showMaskLayer("正在加载...");	
		$.post(url, data, function(result){
			TB_removeMaskLayer();
			$('#evalListPanel').html(result);
		});
	}

	//解决弹出窗口的链接问题,改造 parent.showAll()
	function showPageLink(url, type){
		if(parent && parent.showAll){
			parent.showAll(url, type);
		}else{
			window.open(url);
		}
	}

	function reload(){
		window.location.href=location.href;
		//openWindow('strategyDetail','战略投标明细',_ctx + '/bid/strategy-bid-ledger!detail.action?id=' + '${id}');
		}
</script>
</body>		
</html>

