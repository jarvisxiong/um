<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 用于ajax搜索后显示结果列表 --%>
<div id="searchResultDiv" class="searchUserDiv"></div>

<table width="100%;">
	<tr style="height:30px;">
	<td valign="top">
		<table style="width:98%;">
			<tr>
				<td align="left" valign="middle" style="font-weight: bolder;width:100px"><s:text name="uaapUaapUser" /> </td>
				<td align="right">
					<!-- 操作提示 -->
					<span id="operate_result_tip" style="display: inline;"></span>
					<!-- 用户状态:0-未启用 1-正常 2-冻结 3-解冻 4.注销 -->
					<s:if test="plasUserId != null && plasUserId != ''">
						<!-- 未启用  to 正常 -->
						<s:if test="userStatusCd == 0">
							<%--
							<s:if test="enableOpenFlg == 1">
							</s:if>
							 --%>
								<input style="float:right;"  id="btn_userOpen" type="button" class="buttom" value='<s:text name="uaapUaapUser.userOpen" />' onclick="userOpen()"/>
							<%--
							<s:if test="enableOpenFlg == 0">
							</s:if>
								<input style="float:right;"  id="btn_userOpen" type="button" value='<s:text name="uaapUaapUser.userOpen" />' title="需集团管理员开通" disabled="disabled" />
							 --%>
						</s:if>
						
						<!-- 正常      to 冻结/注销 -->
						<s:if test="userStatusCd == 1">
							<input style="float:right;"  id="btn_userFreeze" type="button" class="buttom" value='<s:text name="uaapUaapUser.userFreeze" />' onclick="userFreeze()"/>
							<input style="float:right;"  id="btn_userClose" type="button" class="buttom" value='<s:text name="uaapUaapUser.userClose" />' onclick="userClose()"/>
						</s:if>
						
						<!-- 冻结      to 解冻/注销 -->
						<s:if test="userStatusCd == 2">
							<input style="float:right;"  id="btn_userUnFreeze" type="button" class="buttom" value='<s:text name="uaapUaapUser.userUnFreeze" />' onclick="userUnFreeze()"/>
							<input style="float:right;"  id="btn_userClose" type="button" class="buttom" value='<s:text name="uaapUaapUser.userClose" />' onclick="userClose()"/>
						</s:if>
						
						<!-- 解冻      to 冻结/注销 -->
						<s:if test="userStatusCd == 3">
							<input style="float:right;"  id="btn_userFreeze" type="button" class="buttom" value='<s:text name="uaapUaapUser.userFreeze" />' onclick="userFreeze()"/>
							<input style="float:right;"  id="btn_userClose" type="button" class="buttom" value='<s:text name="uaapUaapUser.userClose" />' onclick="userClose()"/>
						</s:if>
						
						<!-- 注销      to 正常 -->
						<s:if test="userStatusCd == 4">
							<input style="float:right;"  id="btn_userOpen" type="button" class="buttom" value='<s:text name="uaapUaapUser.userOpen" />' onclick="userOpen()"/>
						</s:if>
						
						<!-- 重置密码 -->
						<input style="float:right;" id="btn_resetPassword" type="button" class="buttom" value='<s:text name="uaapUaapUser.resetPassword" />' onclick="resetPassword()"/>
					</s:if>
					
					<%--新增 --%>
					<input style="float:right;" id="btn_userAdd" type="button" class="buttom" value='<s:text name="uaapUaapUser.userAdd" />' onclick="userAdd()"/>
				</td>
			</tr>
		</table>
		</td>
		</tr> 
		<tr>
			<td align="left" valign="top">
			    <s:form id="inputForm" action="uaap-user!save.action" method="post">
			    <!-- very important! -->
			    <input type="hidden" name="id" id="plasUserIdHidden" value="${plasUserId}" />
			    <input type="hidden" id="entityRealId" name="plasUserId" value="${plasUserId}" />
			    <s:hidden id="entityTmpId" name="entityTmpId"/>
			    <input type="hidden" name="userCd" value="${userCd}" />
			    
			    <s:if test="plasUserId == null || plasUserId == ''">
			    <%-- 提示输入规则 --%>
			    <div class="detail_browser_tips">
			    	账号命名提示：<br/><font color="red">若姓名2个字,则为拼音全名;若超过2个字,则为首字全拼+之后各个字的拼音打头<br/>若已使用,则加1,2,3...以此类推 <br/>例如张三:zhangsan,zhangsan1或张国华:zhanggh,zhanggh1欧阳正华:ouyzh,ouyzh1</font>
			    </div>
			    </s:if>
			    
				<table class="mainTable">
					<tr>
						<td style="width:120px"><s:text name="uaapUaapUser.uiid"/>:</td>
						<td>
							<s:if test="plasUserId != null && plasUserId != ''">
								<s:textfield key="uiid" id="uiid" name="uiid" size="40" cssStyle="cursor:pointer;" readonly="true"/>
							</s:if>
							<s:else>
								<s:textfield key="uiid" id="uiid" name="uiid" size="40" title="输入统一登录号或姓名,可以快速模糊搜索用户" onkeyup="this.value=this.value.toLocaleLowerCase();getFindUserList('userName','uiid',this.value);"/>
							</s:else>
						</td>
						<td><div id="uiidTip"></div></td>
						<td rowspan="5" style="width:110px" valign="middle" align="center">
							<div id="photo_preview_div" style="height: 114;width:100px; background-color: #dfdfdf; line-height: 114px">
								<s:if test="%{userPhotoId != ''}">
									<s:url id="down" action="download" namespace="/app">
								  	  <s:param name="fileName">%{userPhotoImg.fileName}</s:param>
								  	  <s:param name="realFileName">%{userPhotoImg.realFileName}</s:param>
								  	  <s:param name="bizModuleCd">uaapUser</s:param>
								  	  <s:param name="filterType">image</s:param>
									</s:url>
									<img style="height:100px;" id="userPersonalPic" src="${down}" alt="${realFileName}"/>
								</s:if>
								<s:else>
									<img style="height:100px;" id="userPersonalPic" src="${ctx}/images/user_default_photo.jpg" title="请上传照片"/>
								</s:else>
							</div>
						</td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.userBizCd"/>:</td>
						<td><s:textfield key="userBizCd" id="userBizCd" name="userBizCd" size="40"/></td>
						<td><div id="userBizCdTip"></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.userName"/>:</td>
						<td align="left">
							<s:textfield key="userName" id="userName" name="userName" size="40"/>
						</td>
						<td><div id="userNameTip"></div></td>
					</tr>
					<tr>
						<td><s:text name="uaapUaapUser.sexCd"/>:</td>
						<td><s:radio list="@com.hhz.ump.util.DictMapUtil@getMapSexCd()" name="sexCd" listKey="key" listValue="value" value="sexCd==null?0:sexCd" id="sexCd"/></td>
						<td><div id="sexCdTip" ></div></td>
					</tr> 
					<tr>
						<td>上传照片:</td>
						<td colspan="2">
							<a style="text-decoration: underline;color: #0167A2;" href="javascript: void(0);" onfocus="this.blur();" onclick="showUploadDiv(); return false;">+请点击上传照片</a>
							<font color="red">建议大小:90px * 112px (宽度*高度)</font> 
						</td>
					</tr> 
				</table>
				<table class="mainTable">
					<tr>
						<td style="width:120px"><s:text name="uaapUaapUser.email"/>:</td>
						<td style="wdith:200px;"><s:textfield key="email" id="email" name="email" size="40"/></td>
						<td style="width:160px"><div id="emailTip"></div></td>
					</tr>
					<s:if test="emailFlg!= null && emailFlg != ''">
					<tr>
						<td><s:text name="uaapUaapUser.emailFlg"/>:</td>
						<td>
							<p:code2name mapCodeName="mapEmailFlg" value="emailFlg"/>
						</td>
						<td><div id="emailFlgTip" ></div></td>
					</tr> 
					</s:if>
					<s:else>
					<tr style="display: none;">
						<td><s:text name="uaapUaapUser.emailFlg"/>:</td>
						<td><s:textfield key="emailFlg" id="emailFlg" name="emailFlg" size="40"/></td>
						<td><div id="emailFlgTip"></div></td>
					</tr>
					</s:else>
					<%--上级物理机构 --%>
					<tr>
						<td>上级机构名称:</td>
						<td>
							<table style="width:100%;">
							<tr>
								<td>
									<span style="display:none;"><s:textfield id="physicalOrgCd" name="physicalOrgCd" key="physicalOrgCd"></s:textfield></span>
									<span id="physicalOrgName"></span>
								</td>
								<td style="width:120px;" align="right">
									<input type="button" value="<s:text name="common.select" />" onclick="onSelectPhysicOrg()"/>
									<input type="button" value="<s:text name="common.clean" />" onclick="cleanUaapPhysicalOrgCd()"/>
								</td>
							</tr>
							</table>
						</td>
						<td><div id="physicalOrgCdTip" ></div></td>
					</tr> 
					
					<tr>
						<td><s:text name="uaapUaapUser.positionCd"/>:</td>
						<td>
							<input type="text" name="positionName" id="positionName" value="${positionName}" onkeyup="getPositionList('positionName','positionCd',this.value);"/>
							<input type="hidden" name="positionCd" id="positionCd" value="${positionCd}"/>
							<%--
							<s:select list="mapPosition" listKey="key" listValue="value" id="positionCd" name="positionCd" value="positionCd==null?0:positionCd"/>
							 --%>
						</td>
						<td><div id="positionCdTip"></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.workDutyDesc"/>:</td>
						<td><s:textfield key="workDutyDesc" id="workDutyDesc" name="workDutyDesc" size="40"/></td>
						<td><div id="workDutyDescTip" ></div></td>
					</tr>
					<tr>
						<td><s:text name="uaapUaapUser.permissionLevelCd"/>:</td>
						<td>
							<s:select list="@com.hhz.ump.util.DictMapUtil@getMapPermLevel()" listKey="key" listValue="value" id="permissionLevelCd" name="permissionLevelCd" value="permissionLevelCd==null?0:permissionLevelCd"/>
						</td>
						<td><div id="permissionLevelCdTip" ></div></td>
					</tr>
					<tr>
						<td><s:text name="uaapUaapUser.userStatusCd"/>:</td>
						<s:if test="plasUserId != null && plasUserId != ''">
							<td>
							
							<b style="color: red;"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapUserStatus()" value="userStatusCd"/></b></td>
						</s:if>
						<s:else>
							<!-- 默认1-未启用 -->
							<td><s:radio list="@com.hhz.ump.util.DictMapUtil@getMapUserStatus()" id="userStatusCd" name="userStatusCd" listKey="key" listValue="value" value="userStatusCd==null?0:userStatusCd"/></td>
						</s:else>
						<td><div id="userStatusCdTip" ></div></td>		
					</tr>
					<tr>
						<td><s:text name="uaapUaapUser.fixedPhone"/>:</td>
						<td><s:textfield key="fixedPhone" id="fixedPhone" name="fixedPhone" size="40"/></td>
						<td><div id="fixedPhoneTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.mobilePhone"/>:</td>
						<td><s:textfield key="mobilePhone" id="mobilePhone" name="mobilePhone" size="40"/></td>
						<td><div id="mobilePhoneTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.mobilePhone2"/>:</td>
						<td><s:textfield key="mobilePhone2" id="mobilePhone2" name="mobilePhone2" size="40"/></td>
						<td><div id="mobilePhone2Tip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.sourceTypeCd"/>:</td>
						<td>
							<%--
							<s:radio list="mapSourceType" id="sourceTypeCd" name="sourceTypeCd" listKey="key" listValue="value" value="sourceTypeCd==null?0:sourceTypeCd"/>
							 --%>
							<s:select list="@com.hhz.ump.util.DictMapUtil@getMapSourceType()" listKey="key" listValue="value" id="sourceTypeCd" name="sourceTypeCd" value="sourceTypeCd==null?0:sourceTypeCd"/>
							</td>
						<td><div id="sourceTypeCdTip" ></div></td>
					</tr> 
					<%-- 
					<tr>
						<td><s:text name="uaapUaapUser.idCardTypeCd"/>:</td>
						<td><s:radio list="mapIdCardType" id="idCardTypeCd" name="idCardTypeCd" listKey="key" listValue="value" value="idCardTypeCd==null?0:idCardTypeCd"/></td>
						<td><div id="idCardTypeCdTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.specialUserFlg"/>:</td>
						<td><s:radio list="mapSpecialUserFlg" id="specialUserFlg"  name="specialUserFlg" listKey="key" listValue="value" value="specialUserFlg==null?0:specialUserFlg"/></td>
						<td><div id="specialUserFlgTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.userTypeCd"/>:</td>
						<td><s:radio list="mapUserType" id="userTypeCd"  name="userTypeCd" listKey="key" listValue="value" value="userTypeCd==null?0:userTypeCd"/></td>
						<td><div id="userTypeCdTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.defaultCredenc"/>:</td>
						<td><s:textfield key="defaultCredenc" id="defaultCredenc" name="defaultCredenc" size="40"/></td>
						<td><div id="defaultCredencTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.authenticTypeCd"/>:</td>
						<td><s:radio list="mapAuthenticType" name="authenticTypeCd" listKey="key" listValue="value" value="authenticTypeCd==null?0:authenticTypeCd"/></td>
						<td><div id="authenticTypeCdTip" ></div></td>
					</tr>
					--%>
					<tr>
						<td><s:text name="uaapUaapUser.idno"/>:</td>
						<td><s:textfield key="idno" id="idno" name="idno" size="40"/></td>
						<td><div id="idnoTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.effectDate"/>:</td>
						<td><s:textfield key="effectDate" id="effectDate" name="effectDate"  onfocus="WdatePicker()" cssClass="Wdate" /></td>
						<td><div id="effectDateTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.invalidDate"/>:</td>
						<td><s:textfield key="invalidDate" id="invalidDate" name="invalidDate"  onfocus="WdatePicker()" cssClass="Wdate" /></td>
						<td><div id="invalidDateTip" ></div></td>
					</tr>
					<s:if test="plasUserId != null && plasUserId != ''">
					<tr>
						<td><s:text name="uaapUaapUser.macAddress"/>:</td>
						<td><s:textarea key="macAddress" id="macAddress" name="macAddress" readonly="true" title="不可编辑" onfocus="this.blur()"/></td>
						<td><div id="macAddressTip" ></div></td>
					</tr>
					</s:if>
					<%-- 
					<tr>
						<td><s:text name="uaapUaapUser.failureTimes"/>:</td>
						<td><s:textfield key="failureTimes" id="failureTimes" name="failureTimes" size="40"/></td>
						<td><div id="failureTimesTip" ></div></td>
					</tr>
					--%>
					<s:if test="dispOrderNo == null||dispOrderNo == ''">
					<tr  align="left">
						<td><s:text name="uaapUaapUser.dispOrderNo"/>:</td>
						<td><s:textfield key="dispOrderNo" id="dispOrderNo" name="dispOrderNo" size="10" value="0"/></td>
						<td><div id="dispOrderNoTip"></div></td>
					</tr> 
					</s:if>
					<s:else>
					<tr  align="left">
						<td><s:text name="uaapUaapUser.dispOrderNo"/>:</td>
						<td><s:textfield key="dispOrderNo" id="dispOrderNo" name="dispOrderNo" size="10"/></td>
						<td><div id="dispOrderNoTip"></div></td>
					</tr> 
					</s:else>
					
					<tr style="background-color: #dfdfdf;">
						<td colspan="3"  id="a_user_more_info" style="cursor:pointer;"><a style="font-weight:bolder;" href="#">更多&gt;&gt;</a></td>
					</tr>
				</table>
				<table class="mainTable" style="display: none;" id="user_more_info"> 
					<tr>
						<td style="width:105px"><s:text name="uaapUaapUser.serviceStatusCd"/>:</td>
						<td><s:radio list="@com.hhz.ump.util.DictMapUtil@getMapServiceStatus()" id="serviceStatusCd" name="serviceStatusCd" listKey="key" listValue="value" value="serviceStatusCd==null?1:serviceStatusCd"/></td>
						<td style="width:160px"><div id="serviceStatusCdTip" ></div></td>
					</tr>
					<tr>
						<td><s:text name="uaapUaapUser.nationCd"/>:</td>
						<td><s:select list="@com.hhz.ump.util.DictMapUtil@getMapNation()" listKey="key" listValue="value"  id="nationCd" name="nationCd" /></td>
						<td><div id="nationCdTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.birthday"/>:</td>
						<td><s:textfield key="birthday" id="birthday" name="birthday" size="40"  onfocus="WdatePicker()" cssClass="Wdate" /></td>
						<td><div id="birthdayTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.nativeProvinceDesc"/>:</td>
						<td><s:textfield key="nativeProvinceDesc" id="nativeProvinceDesc" name="nativeProvinceDesc" size="40"/></td>
						<td><div id="nativeProvinceDescTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.nativePlaceDesc"/>:</td>
						<td><s:textfield key="nativePlaceDesc" id="nativePlaceDesc" name="nativePlaceDesc" size="40"/></td>
						<td><div id="nativePlaceDescTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.marrigeStatusCd"/>:</td>
						<td>
							<%--
							<s:radio list="mapMarrigeStatus" id="marrigeStatusCd" name="marrigeStatusCd" listKey="key" listValue="value" value="marrigeStatusCd==null?0:marrigeStatusCd"/>
							--%>
							<s:select list="@com.hhz.ump.util.DictMapUtil@getMapMarrigeStatus()" listKey="key" listValue="value" id="marrigeStatusCd" name="marrigeStatusCd" value="marrigeStatusCd==null?0:marrigeStatusCd"/>
							</td>
						<td><div id="marrigeStatusCdTip" ></div></td>
					</tr>
					<tr>	
						<td><s:text name="uaapUaapUser.schoolRecordCd"/>:</td>
						<td>
							<%--
							<s:radio list="mapSchoolRecord" id="schoolRecordCd" name="schoolRecordCd" listKey="key" listValue="value" value="schoolRecordCd==null?0:schoolRecordCd"/>
							 --%>
							 <s:select list="@com.hhz.ump.util.DictMapUtil@getMapSchoolRecord()" listKey="key" listValue="value" id="schoolRecordCd" name="schoolRecordCd" value="schoolRecordCd==null?0:schoolRecordCd"/>
						</td>
						<td><div id="schoolRecordCdTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.gradSchoolDesc"/>:</td>
						<td><s:textfield key="gradSchoolDesc" id="gradSchoolDesc" name="gradSchoolDesc" size="40"/></td>
						<td><div id="gradSchoolDescTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.majorDesc"/>:</td>
						<td><s:textfield key="majorDesc" id="majorDesc" name="majorDesc" size="40"/></td>
						<td><div id="majorDescTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.attendWorkDate"/>:</td>
						<td><s:textfield key="attendWorkDate" id="attendWorkDate" name="attendWorkDate" size="40" onfocus="WdatePicker()" cssClass="Wdate" /></td>
						<td><div id="attendWorkDateTip" ></div></td>
					</tr> 
					<tr>
						<td><s:text name="uaapUaapUser.memberTypeCd"/>:</td>
						<td><s:radio list="@com.hhz.ump.util.DictMapUtil@getMapMemberType()" id="memberTypeCd" name="memberTypeCd" listKey="key" listValue="value" value="memberTypeCd==null?0:memberTypeCd"/></td>
						<td><div id="memberTypeCdTip" ></div></td>
					</tr> 
					<!-- //TODO: -->
					<tr>
						<td><s:text name="uaapUaapUser.professionTypeCd"/>:</td>
						<td><s:radio list="@com.hhz.ump.util.DictMapUtil@getMapProfessionType()" id="professionTypeCd"  name="professionTypeCd" listKey="key" listValue="value" value="professionTypeCd==null?0:professionTypeCd"/></td>
						<td><div id="professionTypeCdTip" ></div></td>
					</tr> 
					<tr>
						<td valign="top"><s:text name="uaapUaapUser.politicsCd"/>:</td>
						<td>

							<s:select list="@com.hhz.ump.util.DictMapUtil@getMapPolitics()" listKey="key" listValue="value" name="politicsCd" id="politicsCd" value="politicsCd==null?0:politicsCd"/>
							</td>
						<td><div id="politicsCdTip" ></div></td>
					</tr>
					<tr>
						<td valign="top"><s:text name="uaapUaapUser.otherTypeCd"/>:</td>
						<td><s:select list="@com.hhz.ump.util.DictMapUtil@getMapOtherType()" listKey="key" listValue="value" id="otherTypeCd" name="otherTypeCd" value="otherTypeCd==null?0:otherTypeCd"/></td>
						<td><div id="otherTypeCdTip" ></div></td>
					</tr> 
					<%-- 
					<tr>
						<td><s:text name="uaapUaapUser.extField1"/>:</td>
						<td><s:textfield key="extField1" id="extField1" name="extField1" size="40"/></td>
						<td><s:text name="uaapUaapUser.extField2"/>:</td>
						<td><s:textfield key="extField2" id="extField2" name="extField2" size="40"/></td>
					</tr>
					<tr>
						<td><s:text name="uaapUaapUser.extField3"/>:</td>
						<td><s:textfield key="extField3" id="extField3" name="extField3" size="40"/></td>
						<td><s:text name="uaapUaapUser.extField4"/>:</td>
						<td><s:textfield key="extField4" id="extField4" name="extField4" size="40"/></td>
					</tr>
					<tr>
						<td><s:text name="uaapUaapUser.extField5"/>:</td>
						<td><s:textfield key="extField5" id="extField5" name="extField5" size="40"/></td>
						<td><s:text name="uaapUaapUser.extField6"/>:</td>
						<td><s:textfield key="extField6" id="extField6" name="extField6" size="40"/></td>
					</tr>
					<tr>
						<td><s:text name="uaapUaapUser.extField7"/>:</td>
						<td><s:textfield key="extField7" id="extField7" name="extField7" size="40"/></td>
						<td><s:text name="uaapUaapUser.extField8"/>:</td>
						<td><s:textfield key="extField8" id="extField8" name="extField8" size="40"/></td>
					</tr>
					<tr>
						<td><s:text name="uaapUaapUser.extField9"/>:</td>
						<td><s:textfield key="extField9" id="extField9" name="extField9" size="40"/></td>
						<td><s:text name="uaapUaapUser.extField10"/>:</td>
						<td><s:textfield key="extField10" id="extField10" name="extField10" size="40"/></td>
					</tr>
					--%>
					<tr>
						<td valign="top"><s:text name="uaapUaapUser.remark"/>:</td>
						<td><s:textarea key="remark" id="remark" name="remark"></s:textarea></td>
						<td><div id="remarkTip" ></div></td>
					</tr> 
				</table>
				<table style="width:100%;">
					<tr  align="right">
						<td colspan="3" style="text-align: center;">
							<s:submit cssClass="button" name="btnSave" id="btnSave" key="common.submit" />
							<s:reset cssClass="button" name="btnReset" id="btnReset" key="common.reset"/>
						</td>
					</tr>	
				</table>  
				</s:form>
			</td>
		</tr>
	</table>
	
	<%--隐藏域: 存放弹出框选中的逻辑/物理机构ID --%>
	<div style="display:none;">
		<input type="hidden" id="selectedOrgType" name="selectedOrgType"></input>
		<input type="hidden" id="selectedOrgId" name="selectedOrgId"></input>
		<input type="hidden" id="selectedOrgCd" name="selectedOrgCd"></input>
		<input type="hidden" id="selectedOrgName" name="selectedOrgName"></input>
	</div>

	<script type="text/javascript"> 
	
		$.formValidator.initConfig({formid:"inputForm",onerror:function(msg){alert(msg);}});
		//统一登录账号
		$("#uiid").formValidator({onshow:"请输入登录账号",onfocus:"至多50个字符",oncorrect:"已输入",onempty:"一定要填"}).inputValidator({min:1,max:50,onerror:"长度不正确"})
		.ajaxValidator({
		    type : "get",
			url : "${ctx}/plaspd/plas-user!isUiidExists.action?oldUiid=" + encodeURIComponent('${uiid}'),
			datatype : "text",
			success : function(data){	
				//alert("data:["+data+"]");
				if("true" == data){
					return true;
				}else{
					return false;
				}
			},
			buttons: $("#btnSave"),
			error: function(){alert("服务器没有返回数据，可能服务器忙，请重试");},		
			onerror : "该统一登录账号已使用，请更换",
			onwait : "正在进行 统一登录账号 合法性校验，请稍候..."
		})
		.defaultPassed();
		
		//用户姓名
		$("#userName").formValidator({onshow:"请输入姓名",onfocus:"至多25个汉字",oncorrect:"已输入",onempty:"一定要填"}).inputValidator({min:1,max:50,onerror:"长度不正确"});
	 
		//所属部门
		$("#physicalOrgCd").formValidator({onshow:"请选择",onfocus:"请选择",oncorrect:"已选择",onempty:"一定要选"}).inputValidator({min:1,max:20,onerror:"未选择所属机构,请确认"});
		//职位
		//$("#positionCd").formValidator({onshow:"请选择职位",onfocus:"职位必须选择",oncorrect:"谢谢你的配合"}).inputValidator({min:1,onerror: "你是不是忘记选择了!"});
		
		//职级 
		$("#permissionLevelCd").formValidator({empty:false,onshow:"请选择职级",onfocus:"请选择职级",oncorrect:""}).inputValidator({min:1,onerror: "请选择"});
		
		//用户状态
		$("input:radio[name='userStatusCd']").formValidator({tipid:"userStatusCdTip",onshow:"请选择用户状态",onfocus:"请选择用户状态",oncorrect:"已选择"}).inputValidator({min:1,max:1,onerror:"未选择用户状态,请确认"}).defaultPassed();
		//固定电话号码
		$("#fixedPhone").formValidator({empty:true,onshow:"请输入",onfocus:"至多200个字符",oncorrect:"",onempty:""}).inputValidator({min:1,max:200,onerror:"长度不正确,请确认"});
		//移动电话号码
		$("#mobilePhone").formValidator({empty:true,onshow:"请输入",onfocus:"必须11位",oncorrect:"",onempty:"提醒:若空将影响短信接收"}).inputValidator({min:11,max:11,onerror:" 手机号码必须是11位的,请确认"});//.regexValidator({regexp:"mobile",datatype:"enum",onerror:"你输入的手机号码格式不正确"});
		$("#mobilePhone2").formValidator({empty:true,onshow:"请输入",onfocus:"最多200个字",oncorrect:"",onempty:""}).inputValidator({min:0,max:200,onerror:"长度不正确,请确认"});
		//用户信息来源类型
		$("#sourceTypeCd").formValidator({onshow:"请选择用户信息来源类型",onfocus:"请选择用户信息来源类型",oncorrect:"谢谢你的配合"}).inputValidator({min:1,onerror: "请选择!"}).defaultPassed();
		//生效日期
		$("#effectDate").focus(function(){WdatePicker({skin:'whyGreen',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}});})
						.formValidator({empty:true,onshow:"",onfocus:"不能全部是0",oncorrect:"日期合法"}).inputValidator({min:"1900-01-01",type:"value",onerror:"日期必须大于\"1900-01-01\""});
		//失效日期
		$("#invalidDate").focus(function(){WdatePicker({skin:'whyGreen',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}});})	
						.formValidator({empty:true,onshow:"",onfocus:"不能全部是0",oncorrect:"日期合法"}).inputValidator({min:"1900-01-01",type:"value",onerror:"日期必须大于\"1900-01-01\""});
		//显示序号
		//$("#dispOrderNo").formValidator({onshow:"请输入整数",onfocus:"越大越靠前",oncorrect:"整数正确"}).regexValidator({regexp:"intege",datatype:"enum",onerror:"整数格式不正确"});


		//在职状态
		$("input:radio[name='serviceStatusCd']").formValidator({tipid:"serviceStatusCdTip",onshow:"请选择在职状态",onfocus:"请选择在职状态",oncorrect:"已选择"}).inputValidator({min:1,max:20,onerror:"未选择在职状态,请确认"}).defaultPassed();
		//性别
		$("input:radio[name='sexCd']").formValidator({empty:true,tipid:"sexCdTip",onshow:"请选择性别",onfocus:"请选择性别",oncorrect:"已选择"}).inputValidator({min:1,max:1,onerror:"未选择性别,请确认"}).defaultPassed();
		//民族
		$("#nationCd").formValidator({empty:true,tipid:"nationCdTip",onshow:"",onfocus:"请选择民族",oncorrect:"已选择",onempty:""}).inputValidator({min:1,max:20,onerror:"长度不正确,请确认"});
		//身份证号码(不允许为空)
		$("#idno").formValidator({empty:false,onshow:"请输入身份证",onfocus:"请输入身份证",oncorrect:"已输入",onempty:"请输入身份证"});//.functionValidator({fun:isCardID});
		//出生日期
		$("#birthday").focus(function(){WdatePicker({skin:'whyGreen',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}});})	
						.formValidator({empty:true,onshow:"出生日期",onfocus:"出生日期，不能全部是0",oncorrect:"日期合法"}).inputValidator({min:"1900-01-01",type:"value",onerror:"日期必须大于\"1900-01-01\""}).defaultPassed();
		//籍贯
		$("#nativeProvinceDesc").formValidator({empty:true,onshow:"",onfocus:"至多10个汉字或20个字符",oncorrect:"",onempty:""}).inputValidator({min:1,max:20,onerror:"长度不正确,请确认"});
		//户口所在地
		$("#nativePlaceDesc").formValidator({empty:true,onshow:"",onfocus:"至多250个汉字或500个字符",oncorrect:"",onempty:""}).inputValidator({min:1,max:500,onerror:"长度不正确,请确认"});
		//婚姻状况
		$("#marrigeStatusCd").formValidator({empty:true,tipid:"marrigeStatusCdTip",onshow:"",onfocus:"请选择婚姻状况",oncorrect:"已选择",onempty:""}).inputValidator({min:1,max:20,onerror:"长度不正确,请确认"});
		//学历
		$("#schoolRecordCd").formValidator({empty:true,tipid:"schoolRecordCdTip",onshow:"",onfocus:"请选择学历",oncorrect:"已选择",onempty:""}).inputValidator({min:1,max:20,onerror:"长度不正确,请确认"});
		//毕业院校
		$("#gradSchoolDesc").formValidator({empty:true,onshow:"",onfocus:"至多100个汉字或200个字符",oncorrect:"",onempty:""}).inputValidator({min:1,max:200,onerror:"长度不正确,请确认"});
		//专业
		$("#majorDesc").formValidator({empty:true,onshow:"",onfocus:"至多100个汉字或200个字符",oncorrect:"",onempty:""}).inputValidator({min:1,max:200,onerror:"长度不正确,请确认"});
		//加入本单位时间
		$("#attendWorkDate").focus(function(){WdatePicker({skin:'whyGreen',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}});})	
						.formValidator({empty:true,onshow:"",onfocus:"请选择入司日期，不能全部是0",oncorrect:"日期合法"}).inputValidator({min:"1900-01-01",type:"value",onerror:"日期必须大于\"1900-01-01\""});
		//员工类型
		$("input:radio[name='memberTypeCd']").formValidator({empty:true,tipid:"memberTypeCdTip",onshow:"请选择员工类型",onfocus:"请选择员工类型",oncorrect:"已选择"}).inputValidator({min:1,max:20,onerror:"未选择员工类型,请确认"}).defaultPassed();
		//职务
		$("#workDutyDesc").formValidator({empty:true,onshow:"",onfocus:"至多10个汉字或20个字符",oncorrect:"",onempty:""}).inputValidator({min:1,max:200,onerror:"长度不正确,请确认"});
		//职称
		$("input:radio[name='professionTypeCd']").formValidator({empty:true,tipid:"professionTypeCdTip",onshow:"请选择职称",onfocus:"请选择职称",oncorrect:"已选择"}).inputValidator({min:1,max:20,onerror:"未选择职称,请确认"}).defaultPassed();
		//政治面貌
		$("#politicsCd").formValidator({empty:true,tipid:"politicsCdTip",onshow:"",onfocus:"请选择政治面貌",oncorrect:"已选择",onempty:""}).inputValidator({min:1,max:20,onerror:"长度不正确,请确认"});
		//其他
		$("#otherTypeCd").formValidator({empty:true,onshow:"可以为空",onfocus:"",oncorrect:"已选择"}).inputValidator({max:20,onerror:"长度不正确,请确认"});
		//备注
		$("#remark").formValidator({empty:true,onshow:"可以为空",onfocus:"至多100个汉字或200个字符",oncorrect:"已输入"}).inputValidator({max:200,onerror:"长度不正确,请确认"});

		//MAc地址
		//$("#macAddress").formValidator({empty:true,onshow:"可以为空",onfocus:"至多200个字符",oncorrect:"已输入"}).inputValidator({max:200,onerror:"长度不正确,请确认"});
		
		//新增
		function userAdd(){
			$("#user_detail_area").html('<div><image src="${ctx}/images/loading.gif"/>新增中...</div>');
			var url = '${ctx}/plaspd/plas-user!input.action';
			$.post(url, function(result) {
				$("#user_detail_area").html(result);
			});
		}
		//冻结
		function userFreeze(){
			if(window.confirm('<s:text name="common.confirmFreezeUser" />')){
				$("#operate_result_tip").html('<div><image src="${ctx}/images/loading.gif"/>冻结中...</div>');
				var url = '${ctx}/plaspd/plas-user!freeze.action?id=${plasUserId}';
				$.post(url, function(result) {
					$("#operate_result_tip").html("<font color='green'>用户已冻结!</font>").show().fadeOut(2000);
					$("#user_detail_area").html(result);
				});
			}
		}
		//解冻
		function userUnFreeze(){
			if(window.confirm('<s:text name="common.confirmUnFreezeUser" />')){
				$("#operate_result_tip").html('<div><image src="${ctx}/images/loading.gif"/>解冻中...</div>');
				var url = '${ctx}/plaspd/plas-user!unFreeze.action?id=${plasUserId}';
				$.post(url, function(result) { 
					$("#operate_result_tip").html("<font color='green'>用户已解冻!</font>").show().fadeOut(2000);
					$("#user_detail_area").html(result);
				});
			}
		}

		//删除
		function userDelete(){
			if(window.confirm('<s:text name="common.confirmDeleteUser" />')){
				$("#operate_result_tip").html('<div><image src="${ctx}/images/loading.gif"/>删除中...</div>');
				document.location = '${ctx}/plaspd/plas-user!delete.action?id=${plasUserId}' ;
			}
		}
 
		//注销用户
		function userClose(){
			if(window.confirm('<s:text name="common.confirmCloseUser" />')){
				$("#operate_result_tip").html('<div><image src="${ctx}/images/loading.gif"/>注销中...</div>');
				var url = '${ctx}/plaspd/plas-user!close.action?id=${plasUserId}';
				$.post(url, function(result) {
					$("#operate_result_tip").html("<font color='green'>用户已注销!</font>").show().fadeOut(2000);
					$("#user_detail_area").html(result);
				});
			}
		}

		//启用用户
		function userOpen(){
			if(window.confirm('<s:text name="common.confirmOpenUser" />')){
				$("#operate_result_tip").html('<div><image src="${ctx}/images/loading.gif"/>启用中...</div>');
				var url = '${ctx}/plaspd/plas-user!open.action?id=${plasUserId}';
				$.post(url, function(result) {
					$("#operate_result_tip").html("<font color='green'>用户已启用!</font>").show().fadeOut(2000);
					$("#user_detail_area").html(result);
				});
			}
		}

		//重置密码
		function resetPassword(){
			if(window.confirm('<s:text name="common.confirmResetPassword" />')){
				$("#operate_result_tip").html('<div><image src="${ctx}/images/loading.gif"/>重置中...</div>');
				var url = '${ctx}/plaspd/plas-user!resetPassword.action?id=${plasUserId}' ;
				$.post(url, function(result) {
					if('true' == result){
						$("#operate_result_tip").html("<font color='green'>密码已重置!</font>").show().fadeOut(2000);
					}else{
						$("#operate_result_tip").html("<font color='red'>重置有异常!</font>").show().fadeOut(2000);
					}
				});
			}
		}
		

		// 选择照片
		// display a div to upload a photo after clicking the link.
		function showUploadDiv() {
			ymPrompt.confirmInfo({
				icoCls:"",
				title:"请选择一张照片上传",
				message:"<div id='photoDiv'><img align='absMiddle' src='${ctx}/images/loading.gif'></div>",
				useSlide:true,
				winPos:"t",
				width:400,
				height:150,
				maxBtn:true,
				allowRightMenu:true,
				handler: uploadUserPhoto,
				afterShow:function(){
					var entityId = $("#plasUserIdHidden").val();
					if (!entityId || jQuery.trim(entityId).length == 0) {
						entityId = $("#entityTmpId").val();
					}
					url = "${ctx}/plaspd/plas-user!uploadPhoto.action?bizEntityId=" + entityId;
					$.get(url,
						function(result){
							$("#photoDiv").html(result);
					});
				}
			});
		}

		// 上传照片
		// upload user's photo and refresh the image after uploading.
		function uploadUserPhoto(tp) {
			if(tp == "ok") {
				if (jQuery.trim($("#photoFile").val()).length > 0) {
					$("#photoForm").ajaxSubmit(function(result) {
						if (result) {
							var isRealEntity = "Y";
							var userId = $("#plasUserIdHidden").val();
							if (!userId || jQuery.trim(userId).length == 0) {
								isRealEntity = "N";
							}
							var entityId = $("#photoForm input[name='bizEntityId']").val();
							$.post("${ctx}/plaspd/plas-user!fetchJSONPhotoInfo.action", 
									{
										id : entityId,
										isRealEntity : isRealEntity
									},
									function(json) {
										var j = eval("(" + json + ")");
										var fileName = j.fileName;
										var realFileName = j.realFileName;
										var src = "${ctx}/app/download.action?fileName=" + fileName + "&realFileName=" + realFileName + "&bizModuleCd=uaapUser&filterType=image";
										$("#userPersonalPic").hide();
										$("#userPersonalPic").attr("src", src).attr("realFileName", realFileName).fadeIn();
										alert("照片上传成功!");
							});
						}
					});
				}
			}
		}
		
		//机构类型 1-逻辑  2-物理
		var ORG_TYPE_LOGIC  = '1';
		var ORG_TYPE_PHYSIC = '2';
		
		//选择逻辑机构
		function onSelectLogicOrg(){
			showOrgTree(ORG_TYPE_LOGIC);
		}

		//选择物理机构
		function onSelectPhysicOrg(){
			showOrgTree(ORG_TYPE_PHYSIC);
		}

		// 声明机构树
		var orgTreePanel;

		// 弹出机构窗口(1-逻辑 2-物理)
		function showOrgTree(orgType) {
			ymPrompt.confirmInfo({
				icoCls:"",
				title:"选择机构",
				message:"<div id='orgTreeDiv'><img align='absMiddle' src='${ctx}/images/loading.gif'>加载机构树...</div>",
				useSlide:true,
				winPos:"t",
				width:360,
				height:400,
				maxBtn: false,
				allowRightMenu:true,
				handler:getSelectedOrg,
				showMask:false,
				afterShow:function(){ 
 
					var url = "";
					switch(orgType){
						case ORG_TYPE_LOGIC : url = "${ctx}/plaspd/plas-org!loadLogicalTreeData.action" ;break;
						case ORG_TYPE_PHYSIC: url = "${ctx}/plaspd/plas-org!loadPhysicalTreeData.action";break;
						default: alert('请传入机构类型(1-逻辑机构,2-物理机构)');
						return;
					}
					$.post(url, function(result) {
						
						$("#selectedOrgType").val(orgType);
						$("#selectedOrgId").val('');
						$("#selectedOrgCd").val('');
						$("#selectedOrgName").val('');
						if (result) {
							$("#orgTreeDiv").empty();
							treePanel = new TreePanel({
								renderTo: "orgTreeDiv",
								'root'  : eval('('+result+')'),
								'ctx'	:'${ctx}'
							}); 
							treePanel.render();
							treePanel.on(function(node){
								
								var id = node.attributes.entityId;
								if( $.trim(id) == '' || $.trim(id)=='0'){
									//alert('根节点');
									return;
								} else{
									if(node.isExpand){
										node.collapse();
									}else{
										node.expand();
									}
									$("#selectedOrgId").val(node.attributes.entityId);
									$("#selectedOrgCd").val(node.attributes.entityCd);
									$("#selectedOrgName").val(node.attributes.entityName);
								}
							});
						}
					});
				}
			});
		}

		//获取返回选中结果
		function getSelectedOrg(tp){
			if("ok" == tp){

				var orgType = $("#selectedOrgType").val();
				var orgId   = $("#selectedOrgId").val();
				var orgCd   = $("#selectedOrgCd").val();
				var orgName = $("#selectedOrgName").val();

				if(orgId == ''){
					return;
				}

				switch(orgType){
					case ORG_TYPE_LOGIC : 
						 $("#uaapOrgId").val(orgId);
						 $("#uaapOrgName").val(orgName);
						 break;
					case ORG_TYPE_PHYSIC: 
						 $("#physicalOrgCd").val(orgCd);
						 $("#physicalOrgName").html(orgName);
						 break;
					default: alert('请传入机构类型(1-逻辑机构,2-物理机构)');
				}
			} 
		}

		//加载 物理机构名称
		$.post("${ctx}/plaspd/plas-user!loadParentOrgName.action?orgCd="+$("#physicalOrgCd").val(), function(result) {
			if(result){
				$("#physicalOrgName").html(result);
			}
		});

		//清空物理机构
		function cleanUaapPhysicalOrgCd(){
			 $("#physicalOrgCd").val('');
			 $("#physicalOrgName").html('');
		}

		//更多
		$(function(){
			$("#a_user_more_info").toggle(
				function(){
					$("#user_more_info").show();
					$("#a_user_more_info").html("简要&lt;&lt;");
				},function(){
					$("#user_more_info").hide();
					$("#a_user_more_info").html("更多&gt;&gt;");
				}
			);
		})

		//-------------------------------------- ajax检索 ------------------------------------------------------
		//ajax 搜索职位列表
		var searchMgr;
		function getPositionList(idDomName,idDomCd,paramName){
			//清空
			$("#"+idDomCd).val('');
			if(searchMgr)clearTimeout(searchMgr);
				searchMgr = setTimeout(function(){
				$.post("${ctx}/plaspd/plas-user!getPositionList.action",{positionName: paramName,maxNum:15},function(result){
					var $offset = $("#"+idDomName).offset();
					$("#searchResultDiv").css({left:$offset.left,top:$offset.top+$("#"+idDomName).height()+5}).empty().show();
					result = eval(result);
					
					var arr = new Array();
					$.each(result,function(i,node){
						arr.push("<div positionCd='"+node.positionCd+"' positionName='"+node.positionName+"'><span>"+ node.positionName+"</span></div>");//<span>"+node.positionCd +"</span>|
					});
					$("#searchResultDiv").append(arr.join(""));
					$("#searchResultDiv div").click(function(){
						var positionName = $(this).attr("positionName");
						var positionCd = $(this).attr("positionCd");
						$("#"+idDomName).val(positionName);
						$("#"+idDomCd).val(positionCd);
						$("#searchResultDiv").slideUp(200);
					});
				});
			}, 300);
				
			$(document).bind('click',function(event){
				var event  = window.event || event;
			    var obj = event.srcElement ? event.srcElement : event.target;
			    if( obj != $("#"+idDomName)[0] && obj != $("#searchResultDiv")[0]){
			    	$("#searchResultDiv").slideUp(200);
			    	if( $("#"+idDomCd).val() == ''){
			    		$("#"+idDomName).val('');
			    	}
			    }
			    $(document).unbind('click');
			});
		}

		//ajax 搜索用户列表
		var searchUserMgr;
		function getFindUserList(idDomName,idDomCd,paramName){
			//清空
			//$("#"+idDomCd).val('');
			if(searchUserMgr)clearTimeout(searchUserMgr);
			
			searchUserMgr = setTimeout(function(){
				$.post("${ctx}/plaspd/plas-user!getFindUserList.action",{uiid: paramName,maxNum:15},function(result){
					var $offset = $("#"+idDomCd).offset();
					$("#searchResultDiv").css({left:$offset.left,top:$offset.top+$("#"+idDomCd).height()+5}).empty().show();
					result = eval(result);
					
					var arr = new Array();
					$.each(result,function(i,node){
						arr.push("<div plasUserId='"+node.plasUserId+"' uiid='"+node.uiid+"' userName='"+node.userName+"'><span>"+node.uiid +"</span>|<span>"+ node.userName+"</span>|<span>"+ node.orgName+"</span></div>");
					});
					$("#searchResultDiv").append(arr.join(""));
					$("#searchResultDiv div").click(function(){
						$("#searchResultDiv").slideUp(200);
						var id = $(this).attr("plasUserId");
						var uiid = $(this).attr("uiid");
						var userName = $(this).attr("userName");
						refreshUserArea(id);//方法在 uaap-user.jsp页面
					});
				});
			}, 800);
				
			$(document).bind('click',function(event){
				var event  = window.event || event;
			    var obj = event.srcElement ? event.srcElement : event.target;
			    if( obj != $("#"+idDomCd)[0] && obj != $("#searchResultDiv")[0]){
			    	$("#searchResultDiv").slideUp(200);
			    	if( $("#"+idDomCd).val() == ''){
			    		//$("#"+idDomName).val('');//姓名允许重复
			    	}
			    }
			    $(document).unbind('click');
			});
		}
	</script> 