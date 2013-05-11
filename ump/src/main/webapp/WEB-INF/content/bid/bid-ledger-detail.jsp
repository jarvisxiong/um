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
	<script type="text/javascript" src="${ctx}/resources/js/bid/bid-ledger-pop.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bid/bid-ledger-detail.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bid/bidSingleUpload.js"></script>
</head>
<style>
  table .required{
    
    border: 1px solid #999999;height:20px;
    border-left: 2px solid red;
  }
  table input[type="text"]{
     height:20px;
     border: 1px solid #999999;
  }

</style>
<body>
	<div class="title_bar">
		<div class="fLeft banTitle">招标平台(工程)明细</div>
		<!--  <div class="fLeft ">正在查看：${orgName}&nbsp;/&nbsp;${bidSectionName}</div>-->
		
		<div class="fRight">
			<%--投标阶段以后才能操作商务标和技术标 --%>
			<s:if test="bidStatusCd>2">							
				<security:authorize ifAnyGranted="A_BID_VIEW">
					<!-- <input type="button" class="btn_new btn_blue_new" onclick='viewBizItem("${bidLedgerId}")'      style="width:80px;" value="查看商务标" />
					<input type="button" class="btn_new btn_blue_new" onclick='viewTechItemList("${bidLedgerId}")' style="width:80px;" value="查看技术标" />-->
					<input  type="button"  class="btn_new btn_blue_new" onclick="supStrategyAttach('${bidLedgerId}');" style="width:100px;" value="查看投标情况"/>
				</security:authorize>
			</s:if>
			<div class="resultTip" id="bidLedgerSuccInfoId" style="display: none;"></div>
			<%--评标,中标状态 才能显示 回标分析 
			contented by huangibjin 2012-06-15
			<s:if test="bidStatusCd==3||bidStatusCd==4">
				<security:authorize ifAnyGranted="A_BID_QUERY,A_ADMIN_BID">
					<input type="button" class="btn_new btn_green_new" onclick="showLedgerAnalysis(this,'${bidLedgerId}','${orgCd}')" id="btnShowPop" style="width:60px;" value="回标分析" />
				</security:authorize>								
			</s:if>	
			 --%>
			
	 		<input type="button" class="btn_new btn_full_new" onclick="fullScreen('${ctx}/bid/bid-ledger!detail.action?id=${bidLedgerId}');" value="全屏" style="margin-left:-0.2px"/>	
	 		<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href" style="margin-left:-0.2px" value="刷新" />
		</div>
	</div>
	 
	<div class="mainContent">
    	<input type="hidden" id="bidLedgerId" value="${bidLedgerId }"/>
		<div id="bidLedgerDetailPanel" class="ledgerDetailPanel">
				<form id="submitBidLedgerForm" method="post" action="${ctx}/bid/bid-ledger!save.action">
					<input type="hidden" name="id" value="${bidLedgerId}"/>
					<!-- 保存变更值的字段名称 -->
					<input type="hidden" name="changeFieldName"/>
					<input type="hidden" name="changeFieldValue"/>
					<s:hidden name="hasEvalFiles"/>
			
		  		<table class="bidTable" cellpadding="0" cellspacing="0"> 
		       		<col width="100px"/>
		       		<col />
		       		<col width="115px"/>
		       		<col />
		       		<col width="90px"/>
		       		<col />
		       		<tr>
		       		   	<td  style="border-left-color: #aaabb0;" class="rowItem" colspan="6">
							<div class="fLeft headTitle">招标信息</div>
							<div class="fLeft">							</div>		       		   	</td>
		       		</tr>				       	
		       		<tr>
		       			<th  style="border-left-color: #aaabb0;">项目名称</th>
		       			<td><span> ${orgName}</span></td>
		       		   	<th>标书编号</th>
		       		   	<td><span>${sectionNo}</span></td>
		       			<th>招标计划编号</th>
		       			<td><span>${ccbpNo} </span></td>
		       		</tr>
		       		<tr>
		       		   	<th  style="border-left-color: #aaabb0;">标段名称</th>
		       		   	<td colspan="3"><span>${bidSectionName}</span></td>
		       		   	<th>邀标类型</th>
		       		   	<td><span>
				       		   	<s:if test="visableFlg==1">明标</s:if>
				       		   	<s:elseif test="visableFlg==2">暗标</s:elseif>
				       		   	<s:else>${visableFlg}</s:else>
			       		   	</span>		</td>
		       		</tr>
		       		
		       		<tr>
		       			<th style="line-height: 35px;border-left-color: #aaabb0;">计划开标日期</th> 
						<td>
							<input 
		       		   			 <s:if test="bidStatusCd<1">		      		   				
		      		   			</s:if><s:else>
		      		   			disabled="true"
		      		   			</s:else> 
		      		   			type="text" id="bidOpenPlanDate" name="bidOpenPlanDate" onchange="verificationTime('bidOpenPlanDate')" onfocus="WdatePicker()" class="Wdate"  value='<s:property  value="bidOpenPlanDate" />' style="width: 90%;margin-left: 5px;"  />						</td>
						<th>实际开标日期</th>
						<td><input 
		       		   			<s:if test="bidStatusCd<1">		      		   				
		      		   			</s:if><s:else>
		      		   			disabled="true"
		      		   			</s:else>
		      		   			type="text" id="bidOpenRealDate" name="bidOpenRealDate" onchange="verificationTime('bidOpenRealDate')" onfocus="WdatePicker()" class="Wdate" value='<s:property  value="bidOpenRealDate" />' style="width: 90%;margin-left: 5px;" /></td>
						<th>预计金额(元)</th>
		       		    <td ><input  class="required" validate="required"  name="targetAmtAlias" value="${targetAmt}" readonly="readonly" onblur="formatVal($(this));"  style="width: 91%;margin-left: 5px;" ></input></td>
		       		</tr>
		       		<tr>
		       			<th  style="border-left-color: #aaabb0;">计划定标日期</th> 
				      	<td><input 
		       		   			<s:if test="bidStatusCd<1">		      		   				
		      		   			</s:if><s:else>
		      		   			disabled="true"
		      		   			</s:else>
		      		   			type="text" id="bidConfirmPlanDate" name="bidConfirmPlanDate" onchange="verificationTime('bidConfirmPlanDate')" onfocus="WdatePicker()" class="Wdate"  value='<s:property  value="bidConfirmPlanDate" />'  style="width: 90%;margin-left: 5px;" /></td>
					    <th>实际定标日期</th>
					    <td><input 
					    
		       		   			<s:if test="bidStatusCd<1">		      		   				
		      		   			</s:if><s:else>
		      		   			disabled="true"
		      		   			</s:else>
		      		   			type="text" id="bidConfirmRealDate" name="bidConfirmRealDate" onchange="verificationTime('bidConfirmRealDate')" onfocus="WdatePicker()" class="Wdate"  value='<s:property  value="bidConfirmRealDate" />'  style="width: 90%;margin-left: 5px;" /></td>
					   	<th>预算类型</th>
	    		        <td valign="top"><span>
		    				<s:if test="budgetInFlg == 1">预算内</s:if>
							<s:if test="budgetOutFlg == 1">预算外</s:if>
							</span>	</td>
		       		</tr>
		       		<tr>
		       		   	<th  style="border-left-color: #aaabb0;">标段总面积(㎡)</th>
		       		   	<td>
		       		   	<input type="hidden" id="hidden_sectionTotalArea" name="hidesectionTotalArea" value="${sectionTotalArea}"></input>
		       		   		<input class="required"
		       		   			<s:if test="bidStatusCd<1">		      		   				
		      		   			</s:if><s:else>
		      		   			disabled="true"
		      		   			</s:else>
		      		   			name="sectionTotalArea"  title="${sectionTotalArea}" value="${sectionTotalArea}" onkeyup="clearNoNum_1(this)"  class="input_require" style="width: 91%;margin-left: 5px;"  title="回标分析使用" id="input_sectionTotalArea"/>		       		   	</td>
		       		   	<th>商业面积(㎡)</th>
		       		   	<td>
		       		   		<input type="text"
		       		   		
		       		   			<s:if test="bidStatusCd<1">		      		   				
		      		   			</s:if><s:else>
		      		   			disabled="true"
		      		   			</s:else>
		      		   			 name="bizArea" id="bizArea"  title="${bizArea}" value="${bizArea}" onkeyup="clearNoNum_1(this)" style="width: 91%;margin-left: 5px;"  /></td>
		       		   	<th>住宅面积(㎡)</th>
		       		   	<td>
		       		   		<input type="text"
		       		   			<s:if test="bidStatusCd<1">		      		   				
		      		   			</s:if><s:else>
		      		   			disabled="true"
		      		   			</s:else>
		      		   			<%-- onchange="updateBidLedger($(this))"  onblur="updateBidLedger($(this))"--%>
		       		   		name="houseArea" id="houseArea"  title="${houseArea}" value="${houseArea}"  onkeyup="clearNoNum_1(this)" style="margin-left: 5px;width: 91%;"/>		       		   	</td>
		       		</tr>
		       		<%-- 
		       		<tr>
		       		   	<th>业态规划</th>
		       		   	<td colspan="5" valign="top">		      		   
		      		   		<textarea 
		      		   			<s:if test="bidStatusCd==0||bidStatusCd==1||bidStatusCd==2">
		      		   			
		      		   			</s:if><s:else>
		      		   			disabled="true"
		      		   			</s:else>
		      		   		name="operDesc" rows="3" s>${operDesc}</textarea>		      		   		
		       		   	</td>
		       		</tr>
		       		--%>
		       		<tr>		       		
		       			<th  style="border-left-color: #aaabb0;">招标范围</th>
		       			<td><input class="required" <s:if test="bidStatusCd<1">		      		   				
		      		   			</s:if><s:else>
		      		   			disabled="true"
		      		   			</s:else> type="text" value="${bidAreaDesc}" name="bidAreaDesc" style="width: 91%;margin-left: 5px;" ></input></td>
		       			<th>质量要求</th>
		       			<td><input class="required"  <s:if test="bidStatusCd<1">	      		   				
		      		   			</s:if><s:else>
		      		   			disabled="true"
		      		   			</s:else> type="text" value="${quanDemandDesc}" name="quanDemandDesc" style="width: 91%;margin-left: 5px;" ></input></td>
		       			<th>付款方式</th>
		       			<td><input class="required"  <s:if test="bidStatusCd<1">		      		   				
		      		   			</s:if><s:else>
		      		   			disabled="true"
		      		   			</s:else> type="text" class="required" value="${payTypeDesc}" name="payTypeDesc" style="width: 91%;margin-left: 5px;" ></input></td>
		       		</tr>
		       		<tr >
		       		    <%--取之于“招标文件/合同评审表(工程)”表单 --%>
		       			<th style="border-left-color: #aaabb0;">招标模式</th>
		       			<td >
		       		     		<select class="required" <s:if test="bidStatusCd<1">		      		   				
		      		   			</s:if><s:else>
		      		   			disabled="true"
		      		   			</s:else> style="width: 92%;margin-left: 5px;" id="bidModeCd" name="bidModeCd" >		       		     			
				       				<option value="1" <s:if test="bidModeCd==1">selected='true' </s:if>>工程量清单</option>
				       				<option value="2" <s:if test="bidModeCd==2">selected='true' </s:if>>费率</option>
				       				<option value="3" <s:if test="bidModeCd==3">selected='true' </s:if>>模拟清单(出图后1个月完成工程量核对)</option>
				       			</select>				       			</td>
		       			<th>计价模式</th>
		       			<td >
			       			<select class="required"  <s:if test="bidStatusCd<1">		      		   				
		      		   			</s:if><s:else>
		      		   			disabled="true"
		      		   			</s:else> id="calcModeCd" name="calcModeCd" style="width: 92%;margin-left: 5px;">
			       				<option value="1" <s:if test="calcModeCd==1">selected='true' </s:if>>总价包干</option>
			       				<option value="2" <s:if test="calcModeCd==2">selected='true' </s:if>>单价包干（出图一个月完成总价包干）</option>
			       				<option value="3" <s:if test="calcModeCd==3">selected='true' </s:if>>按实结算</option>
			       			</select>			       			</td>
		       			<th>模拟清单</th>
		       			<td >
			       			<div >
				       			<input type="checkbox" style="width:auto;margin-left:5px" id="changeSupType"  onclick="loadsupType(this);" bidLedgerId="${bidLedgerId }" 
					       			<s:if test="consultFlg==0">					       				
					       			</s:if>
					       			<s:else>
					       				 checked="true"					       			</s:else>
					       			<%-- 投标之时才能切换咨询公司和模拟清单--%>
				       				<s:if test="bidStatusCd<1">			      		   			</s:if><s:else>
			      		   				disabled="true"
			      		   			</s:else>
			      		   			<%-- 如果不是编辑和管理员也不能切换--%>
			      		   			<security:authorize ifNotGranted="A_BID_BASIC_EDIT,A_ADMIN_BID"> disabled="true" </security:authorize>
			      		   			
					       			 name="changeSupType" >
				       			</input>
			       			 </div>			       		</td>
		       		</tr>
		       		
		       		<tr>
		       		<th style="border-left-color: #aaabb0;">施工工期</th>
		       		<td><input  <s:if test="bidStatusCd<2">		      		   				
		      		   			</s:if><s:else>
		      		   				disabled="true"
		      		   			</s:else> type="text" name="startDate" id="startDate" validate="required" class="Wdate inputBorder required" onchange="onchange_DesignTotalDay('startDate')" onfocus="WdatePicker()"  value='<s:property  value="startDate" />' style="width: 90%;margin-left: 5px;" ></input></td>
		       		<th style="text-align:center;">至</th>
		       		<td><input <s:if test="bidStatusCd<1">		      		   				
		      		   			</s:if><s:else>
		      		   				disabled="true"
		      		   			</s:else>  type="text" name="endDate" id="endDate" validate="required" class="Wdate inputBorder required" onchange="onchange_DesignTotalDay('endDate')" onfocus="WdatePicker()"  value='<s:property  value="endDate" />' style="width: 90%;margin-left: 5px;" ></input></td>
		       		<th>共</th>
		       		<td style="vertical-align: middle;">
			       		<div style="margin-top: 4px;padding-bottom: 2px;">
			       		<div style="float: left;"><input style="width:40px;float: left;margin-left: 5px;" type="text"  value="${rankDateNum}"  name="rankDateNum" id="rankDateNum" class="inputBorder required" readonly="readonly"></input>
		       		</div>
		       		<div style="float: left;vertical-align: middle;">&nbsp;天</div></div></td>
	       		</tr>
		       		<tr>
		       		  <th style="border-left-color: #aaabb0;">邀标网批查询号</th>
		       		  <td><span>
			       				<s:if test="resApproveInfoId!= null && resApproveInfoId!= ''">
			       					<a   href="javascript:void(0)" 
			       						title="点击查看网批单"
			       						onclick="openResTask('${resApproveInfo.resApproveInfoId}','${resApproveInfo.approveCd}${resApproveInfo.serialNo}','${resApproveInfo.authTypeCd}');">
			       						<%--${resApproveInfo.approveCd}${resApproveInfo.serialNo} --%>
			       						${resApproveInfo.displayNo}			       					</a>			       				</s:if>
		       				</span>		</td>
		       		  <th style="text-align:center;">招标文件网批查询号</th>
		       		  <td> <span>
				       				<s:if test="entity.callResId!= null && callResId!= ''">
				       					<a  href="javascript:void(0)" 
				       						title="点击查看网批记录"
				       						onclick="openResTask('${callResApproveInfo.resApproveInfoId}','${callResApproveInfo.approveCd}${callResApproveInfo.serialNo}','${callResApproveInfo.authTypeCd}');">
				       						${callResApproveInfo.displayNo}	</a>				       				</s:if>
				       				<s:else>
				       					<span style="width: 100px;margin-left: 10px;">-</span>				       				</s:else>
			       				</span></td>
		       		  <th>定标网批查询号</th>
		       		  <td style="vertical-align: middle;"><span>
				       				<s:if test="entity.bidResId!= null && bidResId!= ''">
				       					<a href="javascript:void(0)" 
				       						title="点击查看网批记录"
				       						onclick="openResTask('${bidResApproveInfo.resApproveInfoId}','${bidResApproveInfo.approveCd}${bidResApproveInfo.serialNo}','${bidResApproveInfo.authTypeCd}');">
				       						${bidResApproveInfo.displayNo}	</a>				       				</s:if>
				       				<s:else>
				       					<span style="width: 100px;margin-left: 10px;">-</span>				       				</s:else>
			       				</span></td>
	       		  </tr>
	       		<tr>
	       			<th style="text-align: right;padding-right:5px;">
	       				招标文件	       			</th>
		       		<td colspan="5" style="border-left: 2px solid red;" >
					<s:if test="entity.bidStatusCd==0">
				       				 <security:authorize ifAnyGranted="A_BID_EDIT,A_ADMIN_BID">
					       				<div style="float: left;display: inline;line-height: 30px;margin-left:5px">
					       					<input type="button" 
					       					   value="上传附件" 
					       					   class="btn_new btn_upload_new" 
					       					   style="width: 80px;" 
					       					   onclick="showUploadSingleAttachDialog('请选择招标文件','${bidLedgerId}','bidLedgerGurantee','attaTendFileFlg','BidLedger','attaTendFileFlg',event);"/>	
					       				</div>	
					       			 </security:authorize>
				       				</s:if>
						<%-- 招标网批单 --%>
						
						<s:if test="entity.callResAttachId != null">
							<a title="点击下載招标文件" href="${ctx}/app/download.action?fileName=${callAttachFile.fileName}&amp;realFileName=${callAttachFile.realFileName}&amp;bizModuleCd=${callAttachFile.bizModuleCd}&amp;filterType=&amp;id=${callAttachFile.appAttachFileId}" 
							style="text-decoration:underline;color: #0167A2;">${callAttachFile.realFileName}</a>						</s:if>
						
						<!-- 历史附件(非网批) -->
						 <div id="show_attaTendFileFlg"  style="border:none;float: left;clear: none;margin-left: 5px;"> &nbsp;
				       				
				       				
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
										 <security:authorize ifAnyGranted="A_BID_EDIT,A_ADMIN_BID">										
											<font onclick="deleteFile('${attaTendFile.appAttachFileId}','attaTendFileFlg','${attaTendFile.bizEntityId}');" 
												style='color:red;font-weight:bold;cursor:pointer;'>×										    </font>										 </security:authorize>
										</s:if>
			       					</s:if>
			       				 </div>						       			</td>
	       		</tr>
	       		<tr>
	       		  <th style="text-align: right;padding-right:5px;">当前状态</th>
	       		  <td colspan="5"><div id="prevNextPanel" style="float:left">
								<%--todo --%>
								<%@ include file="bid-ledger-prevNextStatus.jsp"%>
							</div>
							<security:authorize ifAnyGranted="A_BID_EDIT,A_ADMIN_BID">
	       				<s:if test="bidStatusCd<1">				
				       			<input type="button" onclick="doSaveLedger(this);" bidstatuscd="0" unaccountedsup="" class="btn_new btn_save_new" style="width:80px;margin-left:5px;float:left" value="保存" /><div style="margin-left:5px;float:left" id="saveok"></div>
			       		</s:if>   
		       			<s:if test="entity.bidStatusCd==0">
			       			<div id="closeBidBtn" style="float:right;margin: 0 5px;">
				       			<span style="float:left;">若要关闭本次投标，请点击</span>
				       			<input type="button" onclick="closeBid('${bidLedgerId}');"   class="btn_new btn_del_new" style="width:80px;" value="关闭投标" />
			       			</div>
		       			</s:if>
					</security:authorize>							</td>
	       		  </tr>
	       		</table>
       		</form>
       	</div>
       	<div id="delayWindowDiv" 
			 style="display:none;position: absolute; width: 500px; height: 270px; top: 140px; left; 0 px; display: none; background-color: #fff; border: 1px solid #000; padding: 5px;margin-left: 140px;overflow: scroll;"></div>
       	
	
		<%--工程列表 --%>
        <div id="projectListPanel" class="project">
			<%@ include file="bid-project-list.jsp"%>
       	</div>    	

		<%--投标单位 --%>
		<div id="supListPanel" class="supList">
			<%@ include file="bid-sup-list.jsp"%>
		</div>
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
	<form action="${ctx}/bid/upload.action" id="formAttaUpload" method="post" enctype="multipart/form-data">
	<table class="main_content" align="left" border="0" cellpadding="0" cellspacing="0">
		<input type="hidden" name="bizModuleCd" value="bidLedgerGurantee"/>
		<input type="hidden" name="bidLedgerId"  value="${bidLedgerId}"/>
		<input type="hidden" name="bizEntityId" id="bizEntityIdGen" />
		<div id="attaInput"></div>
	</table>
	</form>
<script language="javascript">
$(function(){
	loadProjectList('${bidLedgerId}');
	loadBidSup('${bidLedgerId}');	
	
	 //投标状态
     var bidStatus = ${entity.bidStatusCd}
	//注册轮次事件（评标文件）
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

//加载评标文件
	function strategyEval(bidLedgerId, batchNo){
		var url = '${ctx}/bid/strategy-bid-ledger!loadEval.action';
		var data = {id: bidLedgerId, batchNo: batchNo};
		TB_showMaskLayer("正在加载...");	
		$.post(url, data, function(result){
			TB_removeMaskLayer();
			$('#evalListPanel').html(result);
		});
	}
	
function fileChange(dom){
	var bidLedgerId=$('#bidLedgerId').val();
	var _parent = $(dom).parent();
	var sName,o;
    sName=dom.value.replace(/\\/g,"/").replace(/(.*\/)(.*)/,"$2");
   	if(!checkUploadFile(sName))return;
	
   var o =$('<div class="attaContainer"><div class="atta_in"></div><div class="attaFileName" title="'
	+sName+'"><a href="javascript:void(0)" style="text-decoration:none">'+sName+'</a>'+
	'</div><div><img src="${ctx}/images/com/progess.gif"/></div><div  class="attaFileDel" onclick="removeFile(this)" ><font>删除</font></div>');
    dom.style.display="none";
    $(".atta_con",_parent).append(o);
    
    var attaBindId = _parent.attr('attaBindId');
    if(attaBindId == ''){
    	attaBindId = bidLedgerId;
    	_parent.attr('attaBindId',attaBindId);
    	$(dom).next().next().val(attaBindId);
    	
    }
	$('#bizEntityIdGen').val(attaBindId);
	
    //实现附件上传
    $("#attaInput").empty().append(dom);
	
    $('.attaFileDel',o).hide();
    $('#formAttaUpload').ajaxSubmit(function(r){
    	r = eval('('+r+')');
    	if(r.success){
	    	$('img',o).hide();
	    	$('.attaFileDel',o).show();
	    	$('.atta_in',o).attr('class','atta_complete');
	    	o.attr('dataId',r.success);
    	}else{
    		alert(r.error);
    		o.remove();
    	}
    });
    
    createFile(_parent);
}
function createFile(d){
	$('.addAttaLinkOther',d).before("<input onchange=fileChange(this) name='upload' hidefocus=true type=file size=1 class=addAttaOpac>");
}
function removeFile(dom){
	if(confirm("确定要删除该附件吗？")){
		var bidLedgerId=$('#bidLedgerId').val();
		var _parent = $(dom).closest('.attaContainer');
		$.post('${ctx}/bid/upload!deleteAtta.action',{'attaId':_parent.attr('dataId'),bizEntityId:bidLedgerId},function(){
			_parent.remove();
		});
	}
}
/**
 * 检查附件格式以及附件是否已经存在于上传列表
 * @param fileName  附件名称
 */
function checkUploadFile(fileName){
	var uploadFileSuffix = 'bat,exe,dll,';
	var suffix = fileName.substring(fileName.lastIndexOf('.')+1,fileName.length).toLowerCase();
	if(uploadFileSuffix.indexOf(suffix+',')>-1){
		alert('对不起,系统暂不支持'+suffix+'格式的文件上传!');
		return false;
	};
	return true;
}

//解决弹出窗口的链接问题,改造 parent.showAll()
function showPageLink(url, type){
	if(parent && parent.showAll){
		parent.showAll(url, type);
	}else{
		window.open(url);
	}
}
</script>
</body>		
</html>

