<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<s:form id="mainFormSearch" action="oa-man-info!load.action" method="post">
	<input type="hidden" name="dictCd" id="dictCd" value="${dictCd}"/>
	<input type="hidden" name="proposalCatCd" id="proposalCatCd" value="${proposalCatCd}"/>
	<div id="rightHeadTool" style="width:100%;border-bottom: 1px solid #AAABB0;">
	<table style="width:100%;background-color: #E4E7EC;" > 
		<tr>
			<td>
				<table class="search_cond_panel"> 
					<tr>
						<th style="width:80px;">建议编号:</th>
						<td><input class="input" name="filter_LIKES_appSerialNo" id="filter_LIKES_appSerialNo" value="${filter_LIKES_appSerialNo}"/></td>
						<th style="width:80px;">发起人:</th>
						<td><input class="input" id="applyUserNames" type="text" name="filter_LIKES_applyUserName" value="${filter_LIKES_applyUserName}"  onclick="getMember('apply','','0','','','','1');"  onchange="onChangeAppVal(this)"/>
							<input id="applyUserCds" type="hidden" name="filter_EQS_applyUserCd"   value="${filter_EQS_applyUserCd}" />
						</td>
						<th style="width:80px;">申请状态:</th>
						<td>
							<select id="filter_EQS_statusCd" name="filter_EQS_statusCd" class="input">
								<option value=""></option>
								<option value="0">新增</option>
								<option value="1">审批</option>
								<option value="2">完成</option>
								<option value="3">驳回</option>
								<option value="6">我审批(同意/驳回)</option>
								<option value="7">我经手(我发起/我审批)</option>
							</select>
						</td>
					</tr>
					<tr >
						<th style="width:80px;">发起时间从:</th>
						<td style="padding-top: 5px;"><s:textfield cssClass="Wdate" name="filter_GED_startDate" onfocus="WdatePicker()" style="width:120px;"/></td>
						<th style="width:80px;">到:</th>   
						<td><s:textfield cssClass="Wdate" name="filter_LTD_startDate" onfocus="WdatePicker()" style="width:120px;"/></td>
						<th style="width:80px;">标题</th>  
						<td><input class="input" name="filter_LIKES_titleName" id="filter_LIKES_titleName" value="${filter_LIKES_titleName}"/></td>
					</tr>
						<%--
							<th style="width:80px;">审批人:</th>
							<td><input id="approveUserNames" type="text" name="filter_LIKES_approveUserName" value="${filter_LIKES_approveUserName}"  onclick="getMember('approve','','0','','','','1');"  />
								<input id="approveUserCds" type="hidden" name="filter_EQS_approveUserCd"   value="${filter_EQS_approveUserCd}" />
							</td> 
						--%>
					<tr>	
						<td colspan="5" style="padding-top: 5px;">
							<input type="button" class="btn_green_65_20" style="border:none;" value="我要建议" onclick="openDetail('${dictCd}','${proposalCatCd}','','');" id="btnNewApply"   <s:if test="dictCd==null||dictCd==''"> disabled="disabled"  </s:if>/>
						    <input type="button" class="btn_blue_55_20" style="border:none;" value="<s:text name="common.search" />" onclick="searchOaManList();" />
						</td>
					</tr>
				</table>
			</td>
			
		</tr>
	</table>
	</div>
	
	
	<!-- 搜素结果 -->
	<div id="tableDiv" style="overflow:auto;border-left:1px solid #dddbdc; border-bottom:1px solid #dddbdc; border-right:1px solid #dddbdc;">
		<table class="content_table" id="editTable">
			<tr>
				<th style="width:80px;background: none;">编号</th>
				
				<th style="line-height:20px;width:126px;" onclick="sortBy(this,'titleName','${selectedOrder}')">
					<div style="float:left;cursor: pointer;">标题</div>
					<s:if test="selectedOrderBy=='titleName'">
						<s:if test="selectedOrder=='desc'">
							<div class="sort_desc"></div>
						</s:if>
						<s:if test="selectedOrder=='asc'">
							<div class="sort_asc"></div>
						</s:if>
					</s:if>
				</th>
				<th style="line-height:20px;width:70px;" onclick="sortBy(this,'authTypeCd','${selectedOrder}')">
					<div style="float:left;cursor: pointer;">类型</div>
					<s:if test="selectedOrderBy=='authTypeCd'">
						<s:if test="selectedOrder=='desc'">
							<div class="sort_desc"></div>
						</s:if>
						<s:if test="selectedOrder=='asc'">
							<div class="sort_asc"></div>
						</s:if>
					</s:if>
				</th>
				<th style="line-height:20px;width:48px;cursor: pointer;" onclick="sortBy(this,'userCd','${selectedOrder}')">
					<div style="float:left;">发起人</div>
					<s:if test="selectedOrderBy=='userCd'">
						<s:if test="selectedOrder=='desc'">
							<div class="sort_desc"></div>
						</s:if>
						<s:if test="selectedOrder=='asc'">
							<div class="sort_asc"></div>
						</s:if>
					</s:if>
				</th> 
				<th style="line-height:20px;width:48px;cursor: pointer;" onclick="sortBy(this,'approveCd','${selectedOrder}')">
					<div style="float:left;">审批人</div>
					<s:if test="selectedOrderBy=='approveCd'">
						<s:if test="selectedOrder=='desc'">
							<div class="sort_desc"></div>
						</s:if>
						<s:if test="selectedOrder=='asc'">
							<div class="sort_asc"></div>
						</s:if>
					</s:if>
				</th>
				<th style="line-height:20px;width:48px;">状态</th>
				<th style="width:64px;cursor: pointer;" onclick="sortBy(this,'nodeCd','${selectedOrder}')">
					<div style="float:left;">评级</div>
					<s:if test="selectedOrderBy=='nodeCd'">
						<s:if test="selectedOrder=='desc'">
							<div class="sort_desc"></div>
						</s:if>
						<s:if test="selectedOrder=='asc'">
							<div class="sort_asc"></div>
						</s:if>
					</s:if>
				</th>
				<th style="line-height:20px;width:32px;">附件</th>
				<th style="line-height:20px;width:64px;">发起时间</th>
			</tr>
				<s:iterator value="page.result" >
				<tr id="main_${oaManInfoId}" style="cursor: pointer;" class="mainTr"  onclick="$('#pdChilltip').remove();openDetail('${manInfoCd}','${proposalCatCd}','${oaManInfoId}','${page.pageNo}');">
					<td class="pd-chill-tip" style="padding-left:5px;" id="approveCd" title="${manInfoCd}_${seriaNo}">
						<div class="ellipsisDiv_full" >${manInfoCd}_${seriaNo}</div> 
					</td>
					<td class="pd-chill-tip"  id="templateLandProject" title='${remark}'>
						<div class="ellipsisDiv_full" >
							${remark}
						</div>
					</td>
					
					<td class="pd-chill-tip" title='<p:code2name mapCodeName="mapDictCdType" value="proposalCatCd"/>'>
						<div class="ellipsisDiv_full">
							<p:code2name mapCodeName="mapDictCdType" value="proposalCatCd"/> 
						</div>
					</td>
					<td id="creator" class="pd-chill-tip" title='<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";") %>'>
						<div class="ellipsisDiv_full"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";") %></div>
					</td>
					 <td id="approveUserCd" class="pd-chill-tip" title='<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("approveUserCd"),";") %>'>
						<div class="ellipsisDiv_full"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("approveUserCd"),";") %></div>
					</td>
					
					
					
					<td id="statusCd"><p:code2name mapCodeName="mapStatusCd" value="statusCd"/></td>
					<td id="nodeCd" class="pd-chill-tip" title="<p:code2name mapCodeName="mapNode" value="nodeCd"/>">
						<div class="ellipsisDiv_full">
							<p:code2name mapCodeName="mapRatingCd" value="rating"/>
						</div>
					</td>
					<td>
					 	<s:if test="attachFlg== '1'">
		          	 	<img title="该记录含有附件" src="${ctx}/pics/email/atta.gif"/>
		          	 	</s:if>
					</td>
					<td title="<s:property value="createdDate" />" class="ellipsisDiv"><div class="ellipsisDiv_full" ><s:date name="createdDate" format="yyyy-MM-dd"/></div></td>
				</tr>
			</s:iterator>
		</table>
		<div class="table_pager" style="margin-top:5px;">
			<p:page />
		</div>
	</div>
</s:form>
<script type="text/javascript">
	$(function(){
		var id='${id}';
		if(!isEmpty(id)){
			openDetail('${dictCd}',id,'myApprove','1','');
		}
	});
	
	function jumpPage(pageNo) {
		$("#pageNo").val(pageNo);
		$("#mainFormSearch").ajaxSubmit(
			function(result){
				$("#content").html(result);
				resetLeftPanel();
			});
	}
	
	function jumpPageTo() {
		var index = $("#pageTo").val();
		index = parseInt(index);
		if (index > 0) {
			jumpPage(index);
		}
	}

	function myshowSearchUser(){
		showSearchUser();
	}

	function sortBy(element,property,oldOrder){
		var sortStr ;
		var old = oldOrder;
		if(typeof(oldOrder)==='undefined'){
			old = ''; 
		}
		if(old=='asc'){
			sortStr = 'desc'; 
		}else if (old=='desc'){
			sortStr = 'asc';
		}else{
			sortStr = 'asc';
		}
		
		$("#selectedOrderBy").val(property);
		$("#selectedOrder").val(sortStr);
		
		$('#mainFormSearch').ajaxSubmit(
			function(result){
				$("#content").html(result);	
				resetLeftPanel();
			}
		);
	}
	
	function onChangeAppVal(dom){
		if($.trim($(dom).val()) == ''){
			$(dom).next().val('');
		};
	}
</script>