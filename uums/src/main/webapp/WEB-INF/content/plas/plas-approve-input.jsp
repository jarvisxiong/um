<%@page import="com.hhz.uums.utils.DictMapUtil"%>
<%@page import="com.hhz.uums.utils.CodeNameUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.hhz.uums.utils.Constants"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="easyui-layout" style="overflow:auto;background-color: white;" fit="true" style="+position: relative">
<s:if test="plasUserId == null">
	<s:if test="applyTypeCd == 1">
		<span class="span_h3">人员新增</span>
	</s:if>
	<s:else>
		<span class="span_h3">人员新增-<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapApproveStatus()" value="approveStatusCd"/></span>
	</s:else>
</s:if>
<s:else>
	<span class="span_h3">人员信息 </span>
	<span class="span_h2"> [${newUiid}-<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapAcctStatus()" value="acctStatusCd"/>]</span>
</s:else>

<%-- 提示操作结果 --%>
<span id="tip_operate_result" style="color:red;margin-left:10px;"></span>

<s:form id="userForm" action="plas-approve!save.action" method="post">
	
<div id="keyFieldsDiv" style="overflow-x:hidden;">
	<%-- 必须的 --%>
	<input type="hidden" name="id" value="${plasApproveInfoId}" />
	<input type="hidden" name="plasApproveInfoId" id="plasApproveInfoId" value="${plasApproveInfoId}" />
	
	<input keyfield="true" type="hidden" id="entityTmpId" name="entityTmpId" value="${entityTmpId}" />
	<input keyfield="true" type="hidden" id="applyTypeCd" name="applyTypeCd" value="${applyTypeCd}" />
	<input keyfield="true" type="hidden" id="approveStatusCd" name="approveStatusCd" value="${approveStatusCd}" />
	<input keyfield="true" type="hidden" id="acctStatusCd" name="acctStatusCd" value="${acctStatusCd}" />
	
	
	<%-- 1暂存/2提交 --%>
	<input keyfield="true" type="hidden" id="saveTypeCd"  name="saveTypeCd"  value="${saveTypeCd}" />
	
	<input keyfield="true" type="hidden" id="oldParentId" name="oldParentId" value="${oldParentId}" />
	<input keyfield="true" type="hidden" id="oldLevelCd"  name="oldLevelCd"  value="${oldLevelCd}" />
	<input keyfield="true" type="hidden" id="oldSysPosIds" name="oldSysPosIds" value="${oldSysPosIds}" />
	<input keyfield="true" type="hidden" id="oldWorkDuty" name="oldWorkDuty" value="${oldWorkDuty}" />
	
	
	<input keyfield="true" type="hidden" id="oldPosNames" name="oldPosNames" value="${oldPosNames}"/>
	<input keyfield="true" type="hidden" id="oldParentName" name="oldParentName" value="${oldParentName}"/>
	<input keyfield="true" type="hidden" id="oldLevelName" name="oldLevelName" value='<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapPermLevel()" value="oldLevelCd"/>'>
	
	
	<input comfield="true" type="hidden" id="plasUserId" name="plasUserId" value='${plasUserId}'>
	<input comfield="true" type="hidden" id="plasAcctId" name="plasAcctId" value='${plasAcctId}'>
	
	<input type="hidden" id="isUnderCtrlFlg" value="${isUnderCtrlFlg}"/>
	
	<s:if test="processingCount>0">
	<div class="res_tip" style="margin:10px 5px;color:red;">
		提示: 当前人员在审批中的流程有
		<a style="font-weight: bolder;margin:0 10px;" 
		title="点击查看申请记录" href="javascript: gotoHis('${newName}')" 
		id="btnProcessCount">
		${processingCount}
		</a>个
		<%-- 
		<div id="processingDiv">
			 <ul style="list-style-type: square;">
			 <s:iterator value="processingList" var="t">
				<li > </li>	
			 </s:iterator>
			 </ul>
		</div>
		--%>
	</div>
	</s:if>
	
	<%-- 关键信息 --%>
	<div class="approve_title">
		<span style="margin-right:50px;">关键信息</span><span id="tip_apply" class="result_tip"></span>
		<s:if test="plasApproveInfoId!= null">
			<input type="button" class="plbtn plgreen" onclick="refreshApproveArea('${plasApproveInfoId}')" value="刷新" style="float:right;"/>
		</s:if>
		<s:else>
			<s:if test="plasUserId!= null">
				<input type="button" class="plbtn plgreen" onclick="refreshUserArea('${plasUserId}')" value="刷新" style="float:right;"/>
			</s:if>
		</s:else>
	</div>
	
	<%--旧信息(若新增,则不显示) --%>
	<table class="cust_approve" id="cust_key_info_old">
		<col style="width:90px;"/>
		<col />
		<tr>
	    	<th>姓名：</th>
	  		<td valign="top">${newName}</td>
		</tr>
		<tr>
	    	<th>账号：</th>
	  		<td valign="top">${newUiid}</td>
		</tr>
		<tr>
 			<th>部门：</th>
	  		<td valign="top">${oldParentName}
 				<s:if test="oldCenterOrgName != '' && oldCenterOrgName != null ">
 					[${oldCenterOrgName}]
 				</s:if>
		 	</td>
 		</tr>
  		<tr>
		  	<th>职务：</th>
		 	<td>${newWorkDuty}</td>
	 	</tr>
 		<tr>
		 	<th>职级：</th>
		 	<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapPermLevel()" value="oldLevelCd"/></td>
		</tr>
 		<tr>
		 	<th valign="top">职位(编制)：</th>
		 	<td>
				<div style="float:left;">
	 			<%@ include file="plas-approve-positionListOld.jsp"%>
				</div>
	 		</td>
		</tr>
	</table>
	<%-- 新信息,若新增,则已进入立即显示 --%>
	<table class="cust_approve" id="cust_key_info_new">
		<col style="width:90px;"/>
		<col />
		<tr>
	    	<th>姓名：</th>
	  		<td valign="top">
	  			<s:if test="plasUserId == null || plasUserId == ''">
	   				<input comfield="true" keyfield="true" style="width:120px;" type="text" id="userName" name="newName" value="${newName}" ></input>
		   			<span style="color:red">* </span>
		   			<span>若姓名重复,请备注机构，如: 李某(天津)</span>
	   			</s:if>
	   			<s:else>
	   				<input comfield="true" keyfield="true" style="width:120px;" type="hidden" id="userName" name="newName" value="${newName}" ></input>
	   				${newName}
	   			</s:else>
			</td>
		</tr>
		<tr>
	    	<th valign="top">账号：</th>
	  		<td valign="top">
	  			<s:if test="applyTypeCd == 1">
	   				<input keyfield="true" style="width:120px;" type="text" id="openAcct_uiid" name="newUiid" value="${newUiid}" onkeyup="$(this).val($(this).val().toLowerCase());validateUiid()"  title="自动校验账号"></input>
		   			<span style="color:red">* </span>
					<span id="openAcct_uiid_tip"></span>
					<div style="color: red;line-height: 25px;"> 规则: 请使用4-20位字符的英文、数字组合! 账号重复,请按先后顺序，如: limou3</div>
   				</s:if>
	   			<s:else>
	   				<input keyfield="true" style="width:120px;" type="hidden" id="openAcct_uiid" name="newUiid" value="${newUiid}"></input>
	   				${newUiid}
	   			</s:else>
			</td>
		</tr>
		<tr>
			<s:if test="isUnderCtrlFlg == 0">
 			<th style="color:red;">调入部门：</th>
 			</s:if>
 			<s:else>
 			<th>部门：</th>
 			</s:else>
 			
	  		<td valign="top">
 				<span id="span_parentOrg" style="float:left;margin-top:5px;margin-right:10px;">
 					${newParentName} 
	 				<s:if test="newCenterOrgName != '' && newCenterOrgName != null ">
	 					[${newCenterOrgName}]
	 				</s:if>
 				</span>
 				<span style="color:red">*</span>
				<span>
					<input type="button" reenable='true' keybutton="true" class="plbtn plgreen" onclick="showParentOrg()" value="选择机构"/>
				</span>
				
				<input keyfield="true" type="hidden" id="newParentId" name="newParentId" value="${newParentId}"/>
				<input keyfield="true" type="hidden" id="newParentName" name="newParentName" value="${newParentName}"/>
		  		
 				<span id="saveUserParentOrgTip" style="color:red;"></span>
 			</td>
 			<%--
			<td  valign="top" rowspan="3">
				<div id="acctListDiv" style="width:100%;color:red;height:100px;overflow-y:auto;+position: relative;line-height:18px;" >
				</div>
			</td>
 			 --%>
 		</tr>
  		<tr>
		  	<th>职务：</th>
		 	<td>
		  		<input keyfield="true" style="width:80%;" type="text" id="newWorkDuty" name="newWorkDuty" value="${newWorkDuty}" ></input>
		  		<span style="color:red">*</span>
		  	</td>
	 	</tr>
 		<tr>
		 	<th>职级：</th>
		 	<td>
		 		<s:select keyfield="true" id="newLevelCd" list="@com.powerlong.plas.utils.DictMapUtil@getMapPermLevel()" name="newLevelCd" listKey="key" listValue="value"/>
		 		<span style="color:red;">*</span>
		 	</td>
		</tr>
 		<tr>
		 	<th valign="top">职位(编制)：</th>
		 	<td>
		 	
		 		<div id="tip_sel_position" style="color:red;"></div>
				<%-- 隐藏职位 --%>
		 		<input keyfield="true" type="hidden" id="newSysPosIds" name="newSysPosIds" value="${newSysPosIds}"/>
			
				<div style="float:left;">
		 		<%-- 已分配的职位列表 --%>
	 			<%@ include file="plas-approve-positionList.jsp"%>
				</div>
				
				
				<span style="float:left;">
					<input type="button" reenable='true' keybutton="true" class="plbtn plgreen" value="选择职位" onclick="showEmptyPosTree('${plasAcctId}')"/>
					<span id="tip_operatePos" style="color:red;"></span>
				</span>
		 		<span style="color:red;float:left;">*</span>
		 	</td>
		</tr>
	 	<s:if test="(applyTypeCd == 1 || applyTypeCd == 6) && (approveStatusCd == 1 || approveStatusCd == 4)">
	 	<%-- 
  		<tr>
		  	<th valign="top">账号状态：</th>
		 	<td>
				<select keyfield="true" name="acctStatusCd" id="acctStatusCd">
					<option value="1">正常</option>
					<option value="0">未开通</option>
				</select>
		  		<span style="color:red">*</span>
		  	</td>
	 	</tr>
	 	--%>
  		<tr>
		  	<th valign="top">启用日期：</th>
		 	<td>
		 		<input type="text" id="effectDate" name="effectDate" class="easyui-datebox" 
		 		<s:if test="effectDate!= null">
		 		value='<s:date name="effectDate"  format="yyyy-MM-dd"/>'
		 		</s:if>
		 		/>(格式:yyyy-MM-dd)
		  		<span style="color:red">*</span>
				<span id="span_openAcct_effectDate" style="display:none;color:red;">请确认启用日期！</span>
		  	</td>
	 	</tr>
	 	</s:if>
  		<tr>
		  	<th>说明信息：</th>
		 	<td>
		  		<input keyfield="true" style="width:80%;" type="text" id="contentDesc" name="contentDesc" value="${contentDesc}" ></input>
		  		<span style="color:red">*</span>
		  	</td>
	 	</tr>
	</table>
</div>



<div style="maring-top:10px;clear: left;">
	<br/>
	<%-- 填写员工资料 --%>
	<s:if test="isUnderCtrlFlg != 0 && isForbidModifyMoreFlg != 1">
		<%--启用，不显示这个按钮 --%>
		<s:if test="acctStatusCd != 4"> 
			<input daily='true' type="button" class="fLeft plbtn plgreen" style="width:90px;" value="填写员工资料" id="btnAppendUserInfo" title="修改员工资料,提交后立即生效"/>
		</s:if>
	</s:if>
	
	<%--提交 --%>
	<div class="fLeft" btnpanel='true'>

		<s:if test="isForbidModifyMoreFlg != 1">
		<%-- 新增或启用，不显示这个按钮 --%>
		<s:if test="applyTypeCd != 1 && acctStatusCd != 4 &&(approveStatusCd == 1 || approveStatusCd == 4)">
			<input type="button" keybutton="true" class="plbtn plgreen"  onclick="saveKeyFields('1')" value="暂存" id="btnSave" />
			<input type="button" keybutton="true" class="plbtn plblue"  onclick="saveKeyFields('2')" value="提交" id="btnSubmit" />
			<input type="button" class="plbtn plblue"  value="调整" id="btnMoveUser"/>
		</s:if>

		<s:if test="plasApproveId == null">
		
			<!-- 操作提示 -->
			<span id="operate_result_tip" class="result_tip" style="display: inline;"></span>
			
			<s:if test="plasAcctId != null && plasAcctId != ''">
				<s:if test="isUnderCtrlFlg != 0">
				<%-- 重置密码 , 已注销的账号，不需要重置密码 --%>
				<s:if test="acctStatusCd!=4">
				<input type="button" daily='true' class="plbtn plgreen"  onclick="processAcct('${plasAcctId}','acctResetPassword','重置密码')" value="重置密码" title="由系统发送随机密码至手机"/>
				</s:if>
			
				<!-- 用户状态:0-未启用 1-正常 2-冻结 (3-解冻) 4.离职 -->
				<%-- 未启用 to 离职 --%>
				<s:if test="acctStatusCd == 0">
					<input type="button" daily='true' class="plbtn plgreen"  onclick="processAcct('${plasAcctId}','acctEnable','启用账号')" value="启用" />
					<input type="button" daily='true' class="plbtn plred"  onclick="processAcct('${plasAcctId}','acctClose','离职')" value="离职" title="禁止该用户所有操作权限"/>
				</s:if>
				
				<%-- 正常   to 冻结/离职 --%>
				<s:if test="acctStatusCd == 1">
					<input type="button" daily='true' class="plbtn plori"  onclick="processAcct('${plasAcctId}','acctFreeze','冻结账号')" value="冻结" title="禁止该用户登录"/>
					<input type="button" daily='true' class="plbtn plred"  onclick="processAcct('${plasAcctId}','acctClose','离职')" value="离职" title="禁止该用户所有操作权限"/>
				</s:if>
				
				<%-- 冻结  to 解冻/离职 --%>
				<s:if test="acctStatusCd == 2">
					<input type="button" daily='true' class="plbtn plgreen"  onclick="processAcct('${plasAcctId}','acctUnFreeze','解冻账号')" value="解冻"/>
					<input type="button" daily='true' class="plbtn plred"  onclick="processAcct('${plasAcctId}','acctClose','离职')" value="离职" title="禁止该用户所有操作权限"/>
				</s:if>
				
				<%-- 解冻  to 冻结/离职 --%>
				<s:if test="acctStatusCd == 3">
					<input type="button" daily='true' class="plbtn plori"  onclick="processAcct('${plasAcctId}','acctFreeze','冻结账号')" value="冻结" title="禁止该用户登录"/>
					<input type="button" daily='true' class="plbtn plred"  onclick="processAcct('${plasAcctId}','acctClose','离职')" value="离职" title="禁止该用户所有操作权限"/>
				</s:if>
				
				<%-- 离职   to 正常 
				<s:if test="acctStatusCd == 4">
					<input type="button" daily='true' class="plbtn plgreen"  onclick="processAcct('${plasAcctId}','acctEnable','启用账号')" value="启用" />
				</s:if>
				--%>
			 	</s:if>
			</s:if>
		</s:if>
		</s:if>
	</div>
</div>

<s:if test="isForbidModifyMoreFlg == 1">
	<div style="margin-left:20px;color:red;">副总裁以上用户的其他信息，请联系总部人事/人事管理员/总部人事 进行调整，谢谢!</div>
</s:if>
<s:if test="isForbidModifyMoreFlg != 1">
<div id="comFieldsDiv" style="clear: both;overflow-x:hidden;display: none;">
	<div class="approve_title">
		员工资料
		<s:if test="plasUserId !=null && plasUserId != ''">
		<span id="tip_enableEffect" style="margin:0 10px;color:blue;">(注意: 以下信息修改后立即生效)</span>
		</s:if>
		<span id="tip_save_com" class="result_tip"></span>
	</div>
	<table class="cust_approve">
		<col style="width:90px;"/>
		<col style="min-width:180px;"/>
		<col style="width:100px;"/>
		<col />
 		<tr>
			<th>性别：</th>
  			<td colspan="2"> 
  				<s:select comfield="true" list="@com.powerlong.plas.utils.DictMapUtil@getMapSex()" id="sexCd" name="sexCd" listKey="key" listValue="value"/>
  				<span style="color:red">*</span>
  			</td>
  			<td rowspan="8" style="text-align: center;">
 				<div id="pics_area">
 					 <%@ include file="plas-approve-pics.jsp"%>
				</div>
				<div>
					
					<s:if test=" creator == null || creator == currentUiid ">
					<s:if test=" (approveStatusCd == 1|| approveStatusCd == 4)">
						<input type="button" class="plbtn plgreen" style="float:none;margin-top:5px;" value="上传照片" onclick="loadFile('${plasUserId}','${plasApproveInfoId}','${entityTmpId}')" />
					</s:if>
					</s:if>
				</div>
  				<div style="color:red;clear: left;">
  					jpg格式, 高112px * 宽90px
  				</div>
			</td>
 		</tr> 
		<tr>
		  	<th>职责说明：</th>
  			<td colspan="2"> 
  				<input comfield="true" style="width:80%;" type="text" id="responsibility" name="responsibility" value="${responsibility}" ></input>
		  	</td>
		</tr>
		
		<tr>
		   	<th>固定电话：</th>
  			<td colspan="2"> 
  				<input comfield="true" style="width:80%;" type="text" id="fixedPhone" name="fixedPhone" value="${fixedPhone}"></input>
  			</td>
		</tr>
		<tr> 
		 	<th>手机号码：</th>
  			<td colspan="2"> 
				<input comfield="true" style="width:80%;" type="text" id="mobilePhone" name="mobilePhone" value="${mobilePhone}" title="如果不填会影响短信发送,如会议通知等"/>
				<span style="color:red">***</span>
		  	</td>
		</tr>
		<tr> 
		  	<th>其他号码：</th>
  			<td colspan="2"> 
				<input comfield="true" style="width:80%;" type="text" name="mobilePhone2" value="${mobilePhone2}"/>
		 	</td>
		</tr>
 		<tr style="display:none;">
 			<th>显示序号：</th>
  			<td colspan="2"> 
  				<input comfield="true" style="width:100px;" type="text" name="sequenceNo"  id="sequenceNo" value='${sequenceNo}' class="easyui-numberbox"  validType="length[0,6]"></input>
 				<span style="color:red">*(数字:1-6位,显示越大越靠前)</span>
 			</td>
		</tr>
 		<tr>	
		  	<th>用户类型：</th>
  			<td colspan="2"> 
 			<security:authorize ifNotGranted="A_ADMIN"> 
 				<input comfield="true" type="hidden" name="userTypeCd" id="userTypeCd" value="1"/><%-- 1-PD用户 --%>
 				PD用户
 			</security:authorize>
 			<security:authorize ifAnyGranted="A_ADMIN">
  				<s:select comfield="true" id="userTypeCd" list="@com.powerlong.plas.utils.DictMapUtil@getMapUserType()" name="userTypeCd" listKey="key" listValue="value"/>
 			</security:authorize>
				<span style="color:red">*</span>
			</td>
		</tr>
 		<tr>	
    		<th>证件号码：</th>
  			<td colspan="2"> 
  				<input comfield="true" style="width:80%;" type="text" id="idno" name="idno" value="${idno}" title="为了账户安全,用户第一次登录,需要验证身份证号码,不填会影响登录."></input>
  				<span style="color:red">*</span>
  			</td>
		</tr>
		
		
		<tr id="btnOtherMore" style="cursor: pointer;" title="点击展开/隐藏">
 			<td colspan="4">
				<div class="approve_title">
					更多信息(点击展开/隐藏)<span id="tip_save_user" style="color:red;"></span>
				</div>
 			</td>
 		</tr>
		<tr class="other_append">
		 	<th>出生日期：</th>
		 	<td>
		 		<input comfield="true" type="text" id="birthday" name="birthday" class="easyui-datebox"
		 		<s:if test="birthday!= null">
		 		 value='<s:date name="birthday" format="yyyy-MM-dd"/>'
		 		</s:if>
		 		/>(格式:yyyy-MM-dd)
		 	</td>
	 		<th>证件类型：</th>
		 	<td><s:select comfield="true" list="@com.powerlong.plas.utils.DictMapUtil@getMapIdCardType()" name="idCardTypeCd" listKey="key" listValue="value"/></td>
 		</tr>
 		<security:authorize ifAnyGranted="A_ADMIN">
		<tr class="other_append">
			<th>用户信息来源：</th>
		 	<td> 
		   		<s:select comfield="true" list="@com.powerlong.plas.utils.DictMapUtil@getMapSourceType()" name="sourceTypeCd" listKey="key" listValue="value"/>
		 	</td>
		</tr>
		</security:authorize>
		<tr class="other_append">
	    	<th>民族：</th>
	  		<td>
	  		<s:select comfield="true" list="@com.powerlong.plas.utils.DictMapUtil@getMapNation()" name="nationCd" listKey="key" listValue="value"/>
	  		</td>
	    	<th>籍贯：</th>
	  		<td><input comfield="true" style="width:80%;" type="text" name="nativeProvinceDesc" value="${nativeProvinceDesc}"></input></td>
	 	</tr>
		<tr class="other_append">
		  	<th>户口所在地：</th>
			<td><input comfield="true" style="width:80%;" type="text" name="nativePlaceDesc" value="${nativePlaceDesc}"></input> </td>
		   	<th>婚姻状况：</th>
			<td><s:select comfield="true" list="@com.powerlong.plas.utils.DictMapUtil@getMapMarrigeStatus()" name="marrigeStatusCd" listKey="key" listValue="value"/></td>
		</tr>
		<tr class="other_append">
		  	<th>毕业院校：</th>
		 	<td><input comfield="true" style="width:80%;" type="text" name="gradSchoolDesc" value="${gradSchoolDesc}"></input></td>
		   	<th>学历：</th>
			<td><s:select comfield="true" list="@com.powerlong.plas.utils.DictMapUtil@getMapSchoolRecord()" name="schoolRecordCd" listKey="key" listValue="value"/></td>
		</tr>
		<tr class="other_append">
		   	<th>专业：</th>
		 	<td><input comfield="true" style="width:80%;" type="text" name="majorDesc" value="${majorDesc}"></input></td>
		   	<th>入职日期：</th>
			<td>
				<input comfield="true" type="text" id="attendWorkDate" name="attendWorkDate" class="easyui-datebox" 
				<s:if test="attendWorkDate!= null">
				value='<s:date name="attendWorkDate" format="yyyy-MM-dd"/>'
				</s:if>
				/>(格式:yyyy-MM-dd)
			</td>
		</tr>
		<tr class="other_append">
		  	<th>合同类型：</th>
		 	<td><s:select comfield="true" list="@com.powerlong.plas.utils.DictMapUtil@getMapMemberType()" name="memberTypeCd" listKey="key" listValue="value"/></td>
		</tr>
		<tr class="other_append">
		  	<th>政治面貌：</th>
		 	<td><s:select comfield="true" list="@com.powerlong.plas.utils.DictMapUtil@getMapPolitics()" name="politicsCd" listKey="key" listValue="value"/></td>
		</tr> 
	  	<%--
		 	<th>是否特殊用户：</th>
		 	<td><s:select comfield="true" list="@com.powerlong.plas.utils.DictMapUtil@getMapSpecialUserFlg()" name="specialUserFlg" listKey="key" listValue="value"/></td>
		<tr class="other_append">
		  	<th>职称：</th>//newLevelCd
		  	<td><s:select comfield="true" list="@com.powerlong.plas.utils.DictMapUtil@getMapProfessionType()" name="professionTypeCd" listKey="key" listValue="value"/></td>
		 	<th>默认凭证号：</td>
		 	<td><input comfield="true" style="with:80%;" type="text" name="defaultCredenc" value="${defaultCredenc}"></input></td>
		</tr>
		<tr class="other_append">
			<th>其他说明：</th>
		 	<td><s:select comfield="true" list="@com.powerlong.plas.utils.DictMapUtil@getMapOtherType()" name="otherTypeCd" listKey="key" listValue="value"/></td>
		</tr>
		<tr class="other_append">
			<th>备注：</th>
			<td colspan="3">
				<textarea comfield="true" name="remark" id="remark" style="height:60px;width:90%">${remark}</textarea>
			</td>
		</tr> 
 		<tr>	
 			<td></td>
			<td colspan="3" style="padding:5px;">
				<input type="button" class="plbtn plred"  value="删除" onclick="delUserById('${plasUserId}','${plasOrgId}');" />
			</td> 
		</tr>
		 --%>
	</table>
	<div style="margin-left:93px;">
	
		<%-- 启用 --%>
		<s:if test=" creator == null  || creator == currentUiid ">
		<s:if test=" plasUserId != null && applyTypeCd == 6 && (approveStatusCd == 1 || approveStatusCd == 4) && isForbidModifyMoreFlg != 1">
			<input type="button" reenable='true' class="plbtn plgreen"  onclick="$('#applyTypeCd').val('6'); saveAllFields(2,'启用')" value="启用"/>
		</s:if>
		</s:if>
		
		<%-- 新增用户（未提交/驳回）--%>
		<s:if test=" creator == null || creator == currentUiid ">
		<s:if test=" applyTypeCd == 1 && (approveStatusCd == 1|| approveStatusCd == 4)">
			<div btnpanel='true'>
				<input type="button" class="plbtn plgreen"  onclick="saveAllFields(1,'暂存')" value="暂存"/>
				<input type="button" class="plbtn plblue"  onclick="saveAllFields(2,'提交')" value="提交"/>
			</div>
		</s:if>
		</s:if>
 		<s:if test="plasUserId != null && isUnderCtrlFlg != 0 && isForbidModifyMoreFlg != 1">
			<input type="button" daily='true' class="plbtn plblue"  onclick="saveComFields()" value="立即更新"/>
		</s:if>
	</div>
</div>
</s:if>
</s:form>
<script type="text/javascript">
	
	$(function(){
		//上传照片
		$('#wUploadFile').window({
			title: '上传照片',
			modal: true,
			closed: true,
			cache: false,
			top: 10,
			iconCls:"icon-save",
			onClose:function(){
				reloadPics(getEntityId('${plasUserId}','${plasApproveInfoId}','${entityTmpId}'));
			}
		});
		
		//默认不显示其他
		$('#btnOtherMore').toggle(
			function(){
				$('.other_append').show();
				$('#btnSaveUserAppend').focus();
			},
			function(){
				$('.other_append').hide();
			}
		);

		$('#btnAppendUserInfo').toggle(
			function(){
				$('#comFieldsDiv').show();
			},
			function(){
				$('#comFieldsDiv').hide();
			}
		);
		$('#btnMoveUser').toggle(
			function(){
				$('#cust_key_info_old').hide();
				$('#cust_key_info_new').show();
				$(this).removeClass('plgreen').addClass('plred').attr("value","取消");
				showKeyButtons();
				$('input[daily=true]').hide();
			},
			function(){
				$('#cust_key_info_old').show();
				$('#cust_key_info_new').hide();
				$(this).removeClass('plred').addClass('plgreen').attr("value","调整");
				hideKeyButtons();
				$('input[daily=true]').show();
			}
		);
	
		//新增
		if('1' == '${applyTypeCd}'){
			$('#cust_key_info_old').hide();
			$('#cust_key_info_new').show();
			$('#btnAppendUserInfo').click();
			$('#btnAppendUserInfo').hide();
		}else{
			if('${plasApproveInfoId}' == ''){
				$('#cust_key_info_old').show();
				$('#cust_key_info_new').hide();
			}else{
				$('#cust_key_info_old').hide();
				$('#cust_key_info_new').show();
			} 
			hideKeyButtons();
		}


		//若有正在走流程,则按钮不可用（btnpanel=true)
		if('${processingCount}' != 0){
			$('div[btnpanel=true]').each(function(){
				$(this).hide();
			});
		}else{
			if('4' == '${acctStatusCd}'){
				$('#cust_key_info_old').hide();
				$('#cust_key_info_new').show();
				$('#comFieldsDiv').show();
				$('input[reenable=true]').show();
				$('#tip_enableEffect').hide();
			}
		}
		
		//新增编辑或更新
		if('${newUiid}'!=''){
			reloadPics(getEntityId('${plasUserId}','${plasApproveInfoId}','${entityTmpId}'));
		}

		//对于账号重复的需要进行校验
		if('${newUiid}'!='' && '${applyTypeCd}' == 1){
			validateUiid();
		}
	});
	

	//上传图片
	//'${entityTmpId}','${plasUserId}','uaapUser'
	function loadFile(plasUserId, appApproveId, entityTmpId){
		var bizEntityId = '';
		if( '' != plasUserId){
			bizEntityId = plasUserId;
		}
		else if( '' != appApproveId){
			bizEntityId = appApproveId;
		}
		else if( '' != entityTmpId){
			bizEntityId = entityTmpId;
		}
		var data = {
			bizEntityId : bizEntityId,
			bizModuleCd : '<%= Constants.APP_ATTACH_FILE_USER%>', 
			filterType:'image',
			attachModelType: '1' //1-只允许一张
		};
		//alert('entityTmpId:${entityTmpId} \n bizEntityId:${plasUserId}');
		showUploadFile(data,entityTmpId,'wUploadFile',false);//app-attachment.js
	}
</script>