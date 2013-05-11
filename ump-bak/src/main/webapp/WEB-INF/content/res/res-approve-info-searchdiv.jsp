<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
	
	<div id="rightHeadTool">
		<ul class="search">
			<li>
			<label>标题：</label><s:textfield cssClass="input" name="filter_LIKES_titlename" id="filter_LIKES_titlename" />
			<label>查询号：</label><s:textfield cssClass="input" name="filter_LIKES_displayNo" id="filter_LIKES_displayNo" />
			<label>随机码：</label><s:textfield cssClass="input" name="filter_LIKES_randomNo" id="filter_LIKES_randomNo" />
			</li>
			<li>
			<label>审批人：</label><input style="cursor:pointer;" id="auditorUserNames" type="text" class="singleSelect" name="filter_LIKES_approveUserName" value="${filter_LIKES_approveUserName}"/>
					<input id="auditorUserCds" type="hidden" name="filter_LIKES_approveUserCd"  value="${filter_LIKES_approveUserCd}" />
			<label>发起人：</label><input style="cursor:pointer;" id="creatorUserNames" type="text" class="singleSelect" name="filter_LIKES_userName" value="${filter_LIKES_userName}"/>
					<input id="creatorUserCds" type="hidden" name="filter_LIKES_userCd"  value="${filter_LIKES_userCd}" />
			<label>编号：</label><s:textfield cssClass="input" name="approveCdSrh" id="approveCd" />
			</li>
			<li>
			<label>表单分类：</label><input id="filter_LIKES_authTypeName" type="text" name="filter_LIKES_authTypeName"  value="${filter_LIKES_authTypeName}" />
				<input id="filter_LIKES_authTypeCd" type="hidden" name="filter_LIKES_authTypeCd" value="${filter_LIKES_authTypeCd}" />
			<label>审批状态：</label><s:select cssClass="input" list="mapStatus" listKey="key" listValue="value" id="filter_LIKES_statusCd" name="filter_LIKES_statusCd" ></s:select>
			<label>项目：</label><input type="text" name="filter_LIKES_landproject" id="filter_LIKES_landproject" value="${filter_LIKES_landproject}"/>
			</li>
			<li class="button">
			<input type="button" class="btn_blue" value="<s:text name="common.search" />" onclick="commonSearch();" />
			<security:authorize ifAnyGranted="A_QZSP_ADMIN">
			<input type="button" class="btn_green" value="导出Excel" onclick="exportExcel();" />
			</security:authorize>
			<s:if test="filter_LIKES_statusCd == 2">
				<span style="float:right;color:green;">提示:当前搜索结果按"完成时间"降序排列</span>
			</s:if>
			</li>
		</ul>
	</div>
	<div id="rightHeadAdd">
		<%--
		<s:set var="resAuthTypeName"><%=CodeNameUtil.getResAuthTypeNameByCd(JspUtil.findString("resAuthTypeCd"))%></s:set>
		liwei3 modify  --%>
		 <s:set var="resAuthTypeName"><%=CodeNameUtil.getResAuthTypeNameByCd(JspUtil.findString("resAuthTypeCd"))%></s:set>
		 <s:set var="resModuleName"><%=CodeNameUtil.getResModuleNameByCd(JspUtil.findString("resModuleCd"))%></s:set>
		<div style="float:left;">当前选择：</div><div style="float:left;" id="idCurAuthType">
		${resAuthTypeName=='null'?'':resAuthTypeName}${resModuleName=='null'?'':resModuleName}
		</div>
		<input type="button" class="addApprove" id="btnNewApply" value="新建审批"  />
	</div>
<script type="text/javascript">
	$(function(){
		//searchdiv
		var id='${id}';
		if(!isEmpty(id)){
			openDetail('${authTypeCd}',id,'myApprove','1','');
		}
		$('.singleSelect').userSelect({
			muti:false
		});
		initQuickSearch();
		//注册快速搜索事件
		$("#filter_LIKES_authTypeName").quickSearch("${ctx}/res/res-module!quickSearch.action",['modulePath','authTypeName'],{authTypeName:'filter_LIKES_authTypeName',authTypeCd:'filter_LIKES_authTypeCd'});
	});
	
	if (curAuthTypeCd){
		//liwei3 add
		//alert(curAuthTypeCd);
		if(curAuthTypeCd.indexOf(",")>=0){
			//alert("xx:"+curModuleCd);
			$('#btnNewApplyDiv').css("color","white");
			$('#btnNewApply').bind('click', function(){
				//alert("module new");
				openSelectDetail();
			});
		}else{
			//alert("yy:"+curAuthTypeCd);
			$('#btnNewApplyDiv').css("color","white");
			$('#btnNewApply').bind('click', function(){
				//alert("authType new");
				openDetail('','1');
			});
		}
	}else{
		$('#btnNewApplyDiv').css("color","grey");
		$('#btnNewApply').bind('click', function(){
			alert("请先在左边菜单里选择表单模板。");
		});
	}
</script>