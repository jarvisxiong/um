<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<table class="bidTable" cellpadding="0" cellspacing="0" style="border-collapse:separate;margin-top: 5px;">
    <col> 
    <col> 
	<col>
	<col width="58">
	<col width="80">
	<col width="100">
	<%-- <col width="60">--%>
	<col width="75">
	<col width="75">
	<col width="50">
	<col width="70">
	<thead>
		<tr>
		   	<th title="招标计划编号" class="first sortField" ><div class="decorate">招标计划编号</div></th>
		  	<th sort="org_name" title="项目名称" class="sortField" style="padding-left:10px;"><div class="decorate">项目名称</div></th>
		   	<th sort="bid_section_name" title="标段名称" class="sortField" style="padding-left:10px;"><div class="decorate">标段名称 </div></th>
		   	<th title="工程数"><div class="decorate">工程数</div></th>
		  	<th title="面积(万㎡)"><div class="decorate">面积(万㎡)</div></th>
		 	<th title="投标单位数量"><div class="decorate">投标单位</div></th>
		  	<%--<th title="跟进人"><div class="decorate">跟进人</div></th>	 --%>										  
		   	<th sort="bid_open_real_date" title="实际开标日期" class="sortField"><div class="decorate">实际开标</div> </th>
		   	<th sort="bid_confirm_real_date" title="实际定标日期" class="sortField"><div class="decorate">实际定标</div></th>
		   	<th sort="bid_status_cd" title="投标状态" class="sortField"><div class="decorate">状态</div></th>
		   	<th sort="created_date" title="创建日期" class="sortField"><div class="decorate">创建日期</div></th>
		</tr>
	</thead>
	<tbody id="projectVoList">
		<s:iterator value="bidLedProjectVoList" var="vo"  status="statu">
		<%-- 概要信息 --%>
		<tr id="brief_<s:property value='#vo.bidLedgerId'/>" class="commonTr" onclick="showLedgerDetail('${bidLedgerId}')" style="height:36px" >
		<%--
			<td>
				<div class="arrowDirectDrop">
					&nbsp;&nbsp;
				</div>
			</td>
		 --%>
		 	<td>
				<div class="decorate" style="margin-left: 5px;">
				${ccbpNo}
				</div>
		 	</td>
			<%-- 项目名称 --%>
			<td>
				<div class="decorate" style="margin-left: 5px;height: 30px;">
					<%= CodeNameUtil.getDeptNameByCd(JspUtil.findString("orgCd")) %>
				</div>							
			</td>
			<%-- 项目标段 --%>
			<td title="<s:property value="#vo.bidSectionName"/>">
				<div class="decorate" style="margin-left: 5px;">
					<s:property value="#vo.bidSectionName"/>
				</div>
			</td>
			<%-- 工程数量  TODO--%>
			<td title="<s:property value="vo.projectNum"/>" >
				<div class="decorate" style="margin-left: 3px;">
					<s:property value="#vo.projectNum"/>
				</div>
			</td>
			<td>
				<div class="decorate" style="margin-left: 3px;">
			  	 	<s:property value="#vo.sectionTotalArea"/>
			    </div>
			</td>  
			<%-- 应标/总数  TODO --%>
			<td title="<s:property value="vo.bidSupNum"/>">
				<div class="decorate" style="margin-left: 3px;">
					<s:property value="#vo.bidSupNum"/>
				</div>
			</td>
			<%--
			<td title="<s:property value="vo.followNames"/>">
				<div >${vo.followNames}</div>
				<div style="display:none;">
					<textarea id='followNames<s:property value="#vo.bidLedgerId"/>' onclick="changeOwnerCheck(this,'<s:property value="#vo.bidLedgerId"/>')"><s:property value="#vo.followNames"/></textarea>
					<input type="hidden" id='followCds<s:property value="#vo.bidLedgerId"/>' value='<s:property value="#vo.followCds"/>'/>
				</div>
			</td>
			 --%>
			<td title="<s:date name="#vo.bidOpenRealDate" format="yyyy-MM-dd"/>">
				<div class="decorate" style="margin-left: 3px;">
					<s:date name="#vo.bidOpenRealDate" format="yy-MM-dd"/>
				</div>
			</td>
			<td title="<s:date name="#vo.bidConfirmRealDate" format="yyyy-MM-dd"/>">
				<div class="decorate" style="margin-left: 3px;">
					<s:date name="#vo.bidConfirmRealDate" format="yy-MM-dd"/>
				</div>
			</td>
			<td>
				<div class="decorate" style="margin-left: 3px;">
					 <p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBidStatus()" value="#vo.bidStatusCd"/>
				</div>	 
			</td>
			<td title="<s:date name="#vo.createdDate" format="yyyy-MM-dd"/>">
				<div class="decorate" style="margin-left: 3px;">
					<s:date name="#vo.createdDate" format="yy-MM-dd"/>
				</div>
			</td>
		</tr>
	</s:iterator>
	<%-- 
	<tr>
		<td colspan="10" class="pageFooter">
	  		<div style="float:right; padding-righe:10px; margin-right:10px;" id="sorucePager"><p:page  pageInfo="voPage"/></div>
		</td>
	</tr>
	--%>
		</tbody>
	</table>
	<script type="text/javascript">
	
	$(function(){
		//排序
		$('.sortField').toggle(
			function(){
				$('.sortFieldAsc').removeClass('sortFieldDesc').addClass('sortFieldAsc');
				$('#sort').val($(this).attr('sort'));
				if($('#order').val()=='asc'){
					$('#order').val('desc');
					}else{
						$('#order').val('asc');	
					}
				
				searchBidLedger();
			},
			function(){
				$('.sortFieldAsc').removeClass('sortFieldAsc').addClass('sortFieldDesc');
				$('#sort').val($(this).attr('sort'));
				if($('#order').val()=='desc'){
					$('#order').val('asc');
					}else{
						$('#order').val('desc');	
					}
				searchBidLedger();
			}
		);
	});
	</script>
	<div class="pagerRight" id="sorucePager"><p:page  pageInfo="voPage"/></div>
