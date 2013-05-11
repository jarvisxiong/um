<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

	<table cellpadding="0" cellspacing="0" class="subBody" cellspace="0">
		<colgroup>
			<col style="width:30px;" />
			<col style="width:200px;" />
			<col />
		</colgroup>
		<tr>
			<td class="seq" style="text-align: center;">1</td>
			<td class="required" validate="required">邀标单位资质审批表</td>
			<td>
			<span style="margin-left:10px">查询号&nbsp;
				<s:if test="entity.resApproveInfoId!= null && entity.resApproveInfoId!= ''">
				<a  href="javascript:void(0)" 
					title="点击查看网批记录"
					onclick="openResTask('${resApproveInfo.resApproveInfoId}','${resApproveInfo.approveCd}${resApproveInfo.serialNo}','${resApproveInfo.authTypeCd}');">
				${resApproveInfo.displayNo}	</a>				       				
				</s:if>
				<s:else>
				<span style="width: 100px;margin-left: 10px;">-</span>
				</s:else>
				</span>
			<%-- 
				<div id="show_bidAttaEval1Flg"  >
				<s:if test="attaEval1s!=null">
				<s:iterator value="attaEval1s" status="sts" var="attaEval1">  			       						
				<s:url id="down" action="download" namespace="/app">
				<s:param name="fileName">${attaEval1.fileName}</s:param>
				<s:param name="realFileName">${attaEval1.realFileName}</s:param>
				<s:param name="bizModuleCd">${attaEval1.bizModuleCd}</s:param>
				<s:param name="filterType">${filterType}</s:param>
				<s:param name="id">${attaEval1.appAttachFileId}</s:param>
				<s:param name="fieldName">${attaEval1.bizFieldName}</s:param>
				<s:param name="bizFieldName">${attaEval1.bizFieldName}</s:param>
				</s:url>
				<div attaId="${attaEval1.appAttachFileId}"  class="attaDiv">
				<a href="${down}" title="点击下载">${attaEval1.realFileName}</a>
				<s:if test="batchNo==entity.batchNo&&entity.bidStatusCd==3">		
				<security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN"></security:authorize>						
				</s:if>
				</div>
				</s:iterator>
				</s:if>
				</div>	
				--%>	
			</td>
		</tr>
		<tr>
			<td class="seq" style="text-align: center;">2</td>
			<td class="required" validate="required">中标单位报价清单</td>
			<td>
			  <div id="show_bidAttaEval2Flg" >
					<s:if test="attaEval2s!=null">	
					<s:iterator value="attaEval2s" var="attaEval2" status="stas">		       						
					<s:url id="down" action="download" namespace="/app">
					<s:param name="fileName">${attaEval2.fileName}</s:param>
					<s:param name="realFileName">${attaEval2.realFileName}</s:param>
					<s:param name="bizModuleCd">${attaEval2.bizModuleCd}</s:param>
					<s:param name="filterType">${filterType}</s:param>
					<s:param name="id">${attaEval2.appAttachFileId}</s:param>
					<s:param name="fieldName">${attaEval2.bizFieldName}</s:param>
					<s:param name="bizFieldName">${attaEval2.bizFieldName}</s:param>
					</s:url>
					<div  attaId="${attaEval2.appAttachFileId}" class="attaDiv">
					<a title="点击下载" href="${down}">${attaEval2.realFileName}</a>
					<s:if test="batchNo==entity.batchNo&&entity.bidStatusCd==3">		
					       <security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN"></security:authorize>											
					</s:if>
					</div>
					</s:iterator>
					</s:if>
				</div>		  
			</td>
		</tr>
		<tr>
			<td class="seq" style="text-align: center;">3</td>
			<td class="required" validate="required">投标报价汇总表</td>
			<td>
			  <div id="show_bidAttaEval3Flg" >
					<s:if test="attaEval3s!=null">	
					<s:iterator value="attaEval3s" var="attaEval3" status="stas">		       						
					<s:url id="down" action="download" namespace="/app">
					<s:param name="fileName">${attaEval3.fileName}</s:param>
					<s:param name="realFileName">${attaEval3.realFileName}</s:param>
					<s:param name="bizModuleCd">${attaEval3.bizModuleCd}</s:param>
					<s:param name="filterType">${filterType}</s:param>
					<s:param name="id">${attaEval3.appAttachFileId}</s:param>
					<s:param name="fieldName">${attaEval3.bizFieldName}</s:param>
					<s:param name="bizFieldName">${attaEval3.bizFieldName}</s:param>
					</s:url>
					<div attaId="${attaEval3.appAttachFileId}" class="attaDiv">
					<a title="点击下载" href="${down}">${attaEval3.realFileName}</a>
					<s:if test="batchNo==entity.batchNo&&entity.bidStatusCd==3">		
					<security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN"></security:authorize>
					</s:if>
					</div>
					</s:iterator>
					</s:if>
				</div>			
			</td>
		</tr>
		<tr>
			<td class="seq" style="text-align: center;">4</td>
			<td>预算和批准文件</td>
			<td>
			  <div id="show_bidAttaEval4Flg" >
					<s:if test="attaEval4s!=null">
					<s:iterator value="attaEval4s" var="attaEval4" status="stas">			       						
					<s:url id="down" action="download" namespace="/app">
					<s:param name="fileName">${attaEval4.fileName}</s:param>
					<s:param name="realFileName">${attaEval4.realFileName}</s:param>
					<s:param name="bizModuleCd">${attaEval4.bizModuleCd}</s:param>
					<s:param name="filterType">${filterType}</s:param>
					<s:param name="id">${attaEval4.appAttachFileId}</s:param>
					<s:param name="fieldName">${attaEval4.bizFieldName}</s:param>
					<s:param name="bizFieldName">${attaEval4.bizFieldName}</s:param>
					</s:url>
					<div attaId="${attaEval4.appAttachFileId}" class="attaDiv">
					<a title="点击下载" href="${down}">${attaEval4.realFileName}</a>
					<s:if test="batchNo==entity.batchNo&&entity.bidStatusCd==3"><security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN"></security:authorize>												
					</s:if>
					</div>
					</s:iterator>
					</s:if>
				</div>			
			</td>
		</tr>
		<tr>
			<td class="seq" style="text-align: center;">5</td>
			<td>招标文件、投标文件</td>
			<td>
			  <div id="show_bidAttaEval5Flg"  >
				<s:if test="attaEval5s!=null">	
				<s:iterator value="attaEval5s" var="attaEval5" status="stas">		       						
				<s:url id="down" action="download" namespace="/app">
				<s:param name="fileName">${attaEval5.fileName}</s:param>
				<s:param name="realFileName">${attaEval5.realFileName}</s:param>
				<s:param name="bizModuleCd">${attaEval5.bizModuleCd}</s:param>
				<s:param name="filterType">${filterType}</s:param>
				<s:param name="id">${attaEval5.appAttachFileId}</s:param>
				<s:param name="fieldName">${attaEval5.bizFieldName}</s:param>
				<s:param name="bizFieldName">${attaEval5.bizFieldName}</s:param>
				</s:url>
				<div attaId="${attaEval5.appAttachFileId}" class="attaDiv">
				<a title="点击下载" href="${down}">${attaEval5.realFileName}</a>
				<s:if test="batchNo==entity.batchNo&&entity.bidStatusCd==3">		
				<security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN"></security:authorize>											
				</s:if>
				</div>
				</s:iterator>
				</s:if>
				</div>			
			</td>
		</tr>
		<tr>
			<td class="seq" style="text-align: center;">6</td>
			<td>其他文件</td>
			<td>
			  <div id="show_bidAttaEval9Flg" >
					<s:if test="attaEval9s!=null">	
					<s:iterator value="attaEval9s" var="attaEval9" status="stas">	
					       						
					<s:url id="down" action="download" namespace="/app">
					<s:param name="fileName">${attaEval9.fileName}</s:param>
					<s:param name="realFileName">${attaEval9.realFileName}</s:param>
					<s:param name="bizModuleCd">${attaEval9.bizModuleCd}</s:param>
					<s:param name="filterType">${filterType}</s:param>
					<s:param name="id">${attaEval9.appAttachFileId}</s:param>
					<s:param name="fieldName">${attaEval9.bizFieldName}</s:param>
					<s:param name="bizFieldName">${attaEval9.bizFieldName}</s:param>
					</s:url>
					<div attaId="${attaEval9.appAttachFileId}" class="attaDiv">
					<a title="点击下载" href="${down}">${attaEval9.realFileName}</a>
					<s:if test="batchNo==entity.batchNo&&entity.bidStatusCd==3">		
					        <security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN"></security:authorize>										
					</s:if>
					</div>
					</s:iterator>
					</s:if>
				</div>			  
			</td>
		</tr>
</table>