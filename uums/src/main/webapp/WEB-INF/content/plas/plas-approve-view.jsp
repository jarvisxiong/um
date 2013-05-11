<%@page import="com.hhz.uums.utils.DictMapUtil"%>
<%@page import="com.hhz.uums.utils.CodeNameUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.hhz.uums.utils.Constants"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 查看申请(关键信息) --%>

<div class="billContent" style="margin:15px;">
	<div class="approve_title">
		关键信息
		<s:if test="approveStatusCd==1||approveStatusCd==4">
			<s:if test="creator == currentUiid">
				<span class="wap_title" onclick="goRefreshApproveArea('${model.plasApproveInfoId}')" style="color:red">编辑</span>
			</s:if>
		</s:if>
	</div>
	<table class="cust_approve">
		<col style="width:90px;"/>
		<col />
		<tr>
	    	<th>姓名：</th>
	  		<td valign="top">
   				${newName}
			</td>
		</tr>
		<tr>
	    	<th>账号：</th>
	  		<td valign="top">
   				${newUiid}
			</td>
		</tr>
		<tr>
 			<th valign="top">部门：</th>
	  		<td valign="top">${newParentName}
 				<s:if test="newCenterOrgName != '' && newCenterOrgName != null ">
 					[${newCenterOrgName}]
 				</s:if>
		 	</td>
 		</tr>
  		<tr>
		  	<th>职务：</th>
		 	<td>${newWorkDuty}</td>
	 	</tr>
 		<tr>
		 	<th>职级：</th>
		 	<td>
		 		<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapPermLevel()" value="newLevelCd"/>
		 	</td>
		</tr>
 		<tr>
		 	<th valign="top">职位(编制)：</th>
		 	<td>
				<div style="clear: both;">
		 		<%-- 已分配的职位列表 --%>
	 			<table style="width:100%;"> 
					<col width="50px"/>
					<col/>
					<col width="120px"/>
					<thead class="panel-header">
					<tr>
						<td style="text-align: center;line-height: 23px;">序号</td>
						<td style="text-align: left;">机构</td>
						<td style="text-align: left;">职位名称</td>
					</tr>
					</thead>
					<tbody>
					<s:iterator value="acctRelPosList" var="vo"  status="stat">
					<tr style="padding-left:5px;text-align: left;">
						<td style="text-align: center;">${stat.index+1}</td>
						<td style="padding:0 5px;">${vo.orgName}</td>
						<td>${vo.sysPosName}</td>
					</tr>
					</s:iterator>
					</tbody>
				</table>
				</div>
		 	</td>
		</tr>
	 	<s:if test="applyTypeCd == 1 || applyTypeCd == 6">
	 	<%--
  		<tr>
		  	<th>账号状态：</th>
		 	<td>
				<s:if test="acctStatusCd==1">正常</s:if>
				<s:if test="acctStatusCd==2">未开通</s:if>
		  	</td>
	 	</tr>
	 	 --%>
  		<tr>
		  	<th>启用日期：</th>
		 	<td><s:date name="effectDate" format="yyyy-MM-dd"/></td>
	 	</tr>
	 	</s:if>
	 	
  		<tr>
		  	<th>说明信息：</th>
		 	<td>${contentDesc}</td>
	 	</tr>
	 	
	 	<s:if test="applyTypeCd == 1 || applyTypeCd == 6">
	 	<tr style="cursor: pointer;" title="点击展开/收起">
 			<td colspan="4">
				<div id="btnShowDetailMore" class="approve_title">更多信息(点击展开)</div>
 			</td>
 		</tr>
	 	</s:if>
	</table>
	
	<div id="moreDetailDiv" style="clear: both;overflow-x:hidden;display: none;">
	<table class="cust_approve" style="width:99%;">
		<col style="width:90px;"/>
		<col style="width:120px;"/>
		<col style="width:90px;"/>
		<col style="width:130px;"/>
 		<tr>
			<th>性别：</th>
  			<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapSex()" value="sexCd"/></td>
  			<td rowspan="8" colspan="2" style="text-align: right;">
 				<div id="pics_area" title="照片">
 					 <%@ include file="plas-approve-pics.jsp"%>
				</div>
			</td>
 		</tr> 
		<tr>
		  	<th>职责说明：</th>
  			<td>${responsibility }</td>
		</tr>
		
		<tr>
		   	<th>固定电话：</th>
  			<td>${fixedPhone }</td>
		</tr>
		
		<tr> 
		 	<th>手机号码：</th>
  			<td>${mobilePhone }	</td>
		</tr>
		<tr> 
		  	<th>其他号码：</th>
  			<td>${mobilePhone2 }</td>
		</tr>
 		<tr>
 			<th>显示序号：</th>
  			<td>${sequenceNo }
 				<span style="color:red">(越大越靠前)</span>
 			</td>
		</tr>
 		<tr>	
		  	<th>用户类型：</th>
  			<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapUserType()" value="userTypeCd"/></td>
		</tr>
 		<tr>	
    		<th>证件号码：</th>
  			<td>${idno}</td>
		</tr>
 		<tr>
		 	<th valign="top">出生日期：</th>
		 	<td><s:date name="birthday" format="yyyy-MM-dd"/></td>
	 		<th>证件类型：</th>
		 	<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapIdCardType()" value="idCardTypeCd"/></td>
 		</tr>
		<tr>
	    	<th>民族：</th>
	  		<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapNation()" value="nationCd"/></td>
	    	<th>籍贯：</th>
	  		<td>${nativeProvinceDesc }</td>
	 	</tr>
		<tr>
		  	<th>户口所在地：</th>
			<td>${nativePlaceDesc}</td>
		   	<th>婚姻状况：</th>
 			<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapMarrigeStatus()" value="marrigeStatusCd"/></td>
		</tr>
		<tr>
		  	<th>毕业院校：</th>
		 	<td>${gradSchoolDesc }</td>
		   	<th>学历：</th>
			<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapSchoolRecord()" value="schoolRecordCd"/></td>
		</tr>
		<tr>
		   	<th>专业：</th>
		 	<td>${majorDesc}</td>
		   	<th>入职日期：</th>
			<td><s:date name="attendWorkDate" format="yyyy-MM-dd" /></td>
		</tr>
		<tr>
		  	<th>合同类型：</th>
			<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapMemberType()" value="memberTypeCd"/></td>
		</tr>
		<tr>
		  	<th>政治面貌：</th>
			<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapPolitics()" value="politicsCd"/></td>
			<%--
		 	<th>是否特殊用户：</th>
			<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapSpecialUserFlg()" value="specialUserFlg"/></td>
			 --%>
		</tr> 
	</table>
	</div>
	
	<div class="div_row">
		<div id="approve_his">
		
		
		</div>
	</div>
	<div class="div_row">
		<div class="list_header2"><span>历史审批意见</span></div>
		<span class="div_value">
			<div style="background-color: #EEEEEE;line-height: 22px;padding: 10px 10px 10px 20px">${model.remark}</div>
		</span>
	</div>
				
</div>
<script type="text/javascript">

$(function(){
	//注册事件
	$('#btnShowDetailMore').toggle(
		function(){
			$('#moreDetailDiv').show();
			$(this).html('更多信息(点击收起)');
		},
		function(){
			$('#moreDetailDiv').hide();
			$(this).html('更多信息(点击展开)');
		}
	);
	//照片
	reloadPics(getEntityId('${plasUserId}','${plasApproveInfoId}','${entityTmpId}'))
});

</script>