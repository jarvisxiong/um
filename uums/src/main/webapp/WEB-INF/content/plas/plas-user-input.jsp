<%@page import="com.hhz.uums.utils.DictMapUtil"%>
<%@page import="com.hhz.uums.utils.CodeNameUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.hhz.uums.utils.Constants"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="easyui-layout" style="overflow:auto;background-color: white;" fit="true" style="+position: relative">

<s:form id="userForm" action="plas-user!save.action" method="post">
	<s:hidden name="entityTmpId" id="entityTmpId"></s:hidden>
	<input type="hidden" id="plasUserId" name="id" value="${plasUserId}" /> 
	<input type="hidden" name="plasOrgId" value="${plasOrg.plasOrgId}"/>

	<security:authorize ifNotGranted="A_ADMIN">
	<input type="hidden" name="sourceTypeCd" id="sourceTypeCd" value="${sourceTypeCd}"/>
	</security:authorize>

	<table style="width:100%;border:1px solid #99BBE8;margin-top:10px;">
		<col style="width:80px;"/>
		<col />
		<col style="width:90px;"/>
		<col />
		<tr class="panel-header" style="line-height: 25px;">
			<td colspan="4" style="padding:5px;">
		  		<div style="float: left;">
					<a href="#" id="saveBtn" iconCls="icon-save" class="easyui-linkbutton" onclick="saveUser();">
						保存
					</a>
					
					<s:if test="viewMode != 'my'">
					<a href="#" id="refreshBtn" iconCls="icon-reload" class="easyui-linkbutton" onclick="refreshUserArea('${plasUserId}','${plasOrgId}');">
					<s:if test="plasUserId == null || plasUserId == ''">
						取消
					</s:if>
					<s:else>
						刷新
					</s:else>
					</a>
					</s:if>
					
					
					<s:if test="viewMode == 'my'">
						<a href="#" id="refreshBtn" iconCls="icon-reload" class="easyui-linkbutton" onclick="refreshMyEdit('${plasUserId}');">刷新</a>
					</s:if>
					<s:else>
						<s:if test="plasAccts == null || plasAccts.size == 0">
							<a href="#" id="refreshBtn" iconCls="icon-reload" class="easyui-linkbutton" onclick="delUserById('${plasUserId}','${plasOrgId}');">
								删除
							</a>
						</s:if>
					</s:else> 
					
					
					<s:if test="plasUserId!= null && plasUserId != ''">
						<s:if test="plasAccts.size == 0">
						账号未开通
						<a class="easyui-linkbutton" href="javascript: void(0);"  onfocus="this.blur();" onclick="openUserAcct('${plasUserId}')" >开通账号</a>
						</s:if>
						<s:if test="plasAccts.size > 0">
							<s:iterator value="plasAccts" var="tmpPlasAcct" status="tmpStatus">
								<s:if test="!#tmpStatus.first">,</s:if>
								<%--
								账号申请状态:<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapAcctAuditFlg()" value="#tmpPlasAcct.applyStatusCd"/>
								 --%>
								[
								${tmpPlasAcct.uiid}
								-<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapUserStatus()" value="#tmpPlasAcct.statusCd"/>
								]
							</s:iterator>
						</s:if>
					</s:if>
					
		  		</div>
		  		<%--
				<div style="float: right;">
					<s:if test="viewMode != 'my'">
					<s:if test="plasUserId!= null && plasUserId != '' && (serviceStatusCd == 0 || serviceStatusCd == 1)">
					
						<!-- 管理员 -->
				  		<security:authorize ifAnyGranted="A_ADMIN">
						<a href="#" id="btnLeaveCompany" iconCls="icon-save" class="easyui-linkbutton" onclick="leaveCompany('${plasUserId}');">离职</a>
				  		</security:authorize>
				  		
				  		<!-- 非受控用户 -->
			  			<security:authorize ifNotGranted="A_ADMIN">
					  		<security:authorize ifAnyGranted="A_ADMIN_UAAP_ORG">
					  		<s:if test="isUnderCtrlFlag != 1">
							<a href="#" id="btnLeaveCompany" iconCls="icon-save" class="easyui-linkbutton" onclick="leaveCompany('${plasUserId}');">离职</a>
					  		</s:if>
					  		</security:authorize>
				  		</security:authorize>
			  		</s:if>
			  		</s:if>
				</div>
		  		--%>
			</td> 
		</tr>
		
 		<tr>
 			<%--
 			<s:if test="plasUserId != null && plasUserId != ''">
			<td colspan="2" id="td_user_uiid" style="color:red;text-align: center;line-height: 30px;">
 			</td>
	 		</s:if>
	 		<s:else>
	 		<td colspan="2">
	 			<input type="hidden" name="uiid" value=""/>
	 		</td>
	 		</s:else>
 			 --%>
	 		
 			<td>所属机构名称</td>
 			<td>
 				<span id="span_parentOrg">
 					${plasOrg.orgName}
	 				<s:if test="parentCenterOrgName != '' && parentCenterOrgName != null ">
	 					[${parentCenterOrgName}]
	 				</s:if>
 				</span>
 				<s:if test="plasUserId != null && plasUserId != '' && viewMode != 'my'">
					<!-- 管理员 -->
					<security:authorize ifAnyGranted="A_ADMIN">
						<input type="button" style="cursor:pointer;" class="buttom" onclick="showPopOrg()" value="选择机构" />
			  		</security:authorize>
			  		
			  		<!-- 非受控用户 -->
			  		<security:authorize ifNotGranted="A_ADMIN">
				  		<security:authorize ifAnyGranted="A_ADMIN_UAAP_ORG">
				  		<s:if test="isUnderCtrlFlag != 1">
						<input type="button" style="cursor:pointer;" class="buttom" onclick="showPopOrg()" value="选择机构" />
				  		</s:if>
				  		</security:authorize>
			  		</security:authorize>
			  		
	 				<span id="saveUserParentOrgTip" style="color:red;"></span>
 				</s:if>
 			</td>
 			<td rowspan="7" colspan="2">
 				<table style="width:90%;">
					<tr>
						<td style="text-align: center;;padding:10px;">
							<div id="pics_area">
								<img id="imgUser" style="height:120px;" src="${ctx}/images/user_default_photo.jpg" title="请上传照片"/>
							</div>
							
							<div style="margin-top:5px;">
 								<s:if test="plasUserId != null && plasUserId != ''">
								<a class="easyui-linkbutton" 
									href="javascript: void(0);" 
									onfocus="this.blur();" 
									onclick="loadFile('${plasUserId}','${entityTmpId}');">
									上传照片
								</a>
								<br/>建议:112px*90px[高*宽]
								</s:if>
								<s:else>
									请在保存信息后,上传照片
								</s:else>
							</div>
						</td>
					</tr>
				</table>
			</td>
 			
 		</tr> 
		<tr>
	    	<td>
		  		<div>姓名</div>
		  	</td>
	  		<td> 
	  		
				<s:if test="viewMode != 'my'">
	    			<input style="width:80%;" type="text" id="userName" name="userName" value="${userName}"></input>
	    			<span style="color:red">*</span>
		 		</s:if>
		 		<s:else>
		 			${userName}
		 		</s:else>
		 		
			</td>
 		</tr>
 		<%--
 		<tr>
    		<td><label for="subject">工号</label></td>
			<td> 
				<input style="width:80%;" class="easyui-validatebox" validType="length[0,20]" type="text" id="userBizCd" name="userBizCd" value="${userBizCd}">
  			</td>
 		</tr>
 		 --%>
 		<tr>
			<td><label for="subject">性别</label></td>
  			<td> 
  				<s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapSex()" id="sexCd" name="sexCd" listKey="key" listValue="value"/>
  				<span style="color:red">*</span>
  			</td>
 		</tr>
 		<security:authorize ifAllGranted="A_ADMIN">
		<tr>
		  	<td><div>在职状态</div></td>
		  	<td><s:select id="serviceStatusCd" list="@com.powerlong.plas.utils.DictMapUtil@getMapServiceStatus()" name="serviceStatusCd" listKey="key" listValue="value"/>
		  		<span style="color:red;">*</span>
		  	</td>
 		</tr>
 		</security:authorize>
 		<security:authorize ifNotGranted="A_ADMIN">
 		<s:if test="plasUserId == null || plasUserId == ''">
 		<tr>
		  	<td><div>在职状态</div></td>
		  	<td>在职<input type="hidden" name="serviceStatusCd" id="serviceStatusCd" value="1"/></td>
 		</tr>
 		</s:if>
 		<s:else>
 		<tr>
		  	<td><div>在职状态</div></td>
		  	<td><input type="hidden" name="serviceStatusCd" id="serviceStatusCd" value="${serviceStatusCd}"/>
		  		<%= DictMapUtil.getMapServiceStatus().get(JspUtil.findString("serviceStatusCd")) %>
		  	</td>
 		</tr>
 		</s:else>
 		</security:authorize>
 		<tr>
		 	<td><label for="subject">职级</label></td>
		 	<td>
				<s:if test="viewMode != 'my'">
			 		<s:select id="permissionLevelCd" list="@com.powerlong.plas.utils.DictMapUtil@getMapPermLevel()" name="permissionLevelCd" listKey="key" listValue="value"/>
			 		<span style="color:red;">*</span>
		 		</s:if>
		 		<s:else>
		 			<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapPermLevel()" value="permissionLevelCd"/>
		 		</s:else>
		 	</td>
		</tr>
  		<tr>
		<%--
    		<td><label for="subject">实际职位</label></td>
  			<td>
  				<input style="width:70%;" title="支持职位名称搜索职位列表" class="easyui-validatebox" type="text" id="realPosName" name="realPosName" value="${realPosName}"></input>
  				<input type="hidden" id="realPosCd" name="realPosCd" value="${realPosCd}"/>(搜索)
  			</td>
		 --%>
		  	<td><label for="subject">职务</label></td>
		  	<td>
				<s:if test="viewMode != 'my'">
			  		<input style="width:80%;" type="text" id="workDutyDesc" name="workDutyDesc" value="${workDutyDesc}" ></input>
			  		<span style="color:red">*</span>
		 		</s:if>
		 		<s:else>
		 			${workDutyDesc}
		 		</s:else>
		 		
		  	</td>
	 	</tr>
		<tr>
		  	<td><label for="subject">权责</label></td>
		  	<td><input style="width:80%;" type="text" id="responsibility" name="responsibility" value="${responsibility}" ></input>
		  		<span style="color:red">*</span>
		  	</td>
		</tr>
		
		<tr>
		   	<td><label for="subject">固定电话</label></td>
		 	<td><input style="width:80%;" type="text" id="fixedPhone" name="fixedPhone" value="${fixedPhone}"></input></td>
		</tr>
		<tr> 
		 	<td>
		  		<div>手机号码</div>
		  	</td>
		  	<td>
				<input style="width:80%;" type="text" id="mobilePhone" name="mobilePhone" value="${mobilePhone}" title="如果不填会影响短信发送,如会议通知等"/>
				<span style="color:red">***</span>
		  	</td>
		  	<td><div>用户类型</div></td>
			<td><s:select id="userTypeCd" list="@com.powerlong.plas.utils.DictMapUtil@getMapUserType()" name="userTypeCd" listKey="key" listValue="value"/>
				<span style="color:red">*</span>
			</td>
		</tr>
		<tr> 
		  	<td>其他号码</td>
			<td valign="top">
				<input style="width:80%;" type="text" name="mobilePhone2" value="${mobilePhone2}"/>
		 	</td>
    		<td><label for="subject">证件号码</label></td>
  			<td><input style="width:80%;" type="text" id="idno" name="idno" value="${idno}" title="为了账户安全,用户第一次登录,需要验证身份证号码,不填会影响登录."></input>
  				<span style="color:red">*</span>
  			</td>
		</tr>
 		<tr>	
 			<td>显示序号</td>
 			<td colspan="3">
 				<input type="text" name="sequenceNo"  id="sequenceNo" value='${sequenceNo}' class="easyui-numberbox" required="true" validType="length[0,6]"></input>
 				<span style="color:red">*(数字:1-6位,显示越大越靠前)</span>
 			</td>
 		</tr>
		<tr>
		 	<td><label for="subject">出生日期</label></td>
		 	<td>
		 		<input type="text" id="birthday" name="birthday" class="easyui-datebox"
		 		<s:if test="birthday!= null">
		 		 value='<s:date name="birthday" format="yyyy-MM-dd"/>'
		 		</s:if>
		 		/>(格式:yyyy-MM-dd)
		 	</td>
	 		<td><label for="subject">证件类型</label></td>
		 	<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapIdCardType()" name="idCardTypeCd" listKey="key" listValue="value"/></td>
 		</tr>
		<tr class="panel-header" id="btnOtherMore" style="cursor:pointer;" title="&nbsp;点击展开/隐藏">
 			<td colspan="4" style="line-height:25px;padding-left:5px;">更多信息(点击展开/隐藏)</td>
 		</tr>
 		<security:authorize ifAnyGranted="A_ADMIN">
		<tr class="other_append">
			<td><label for="subject">用户信息来源</label></td>
		 	<td> 
		   		<s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapSourceType()" name="sourceTypeCd" listKey="key" listValue="value"/>
		 	</td>
		</tr>
		</security:authorize>
		<tr class="other_append">
	    	<td><label for="subject">民族</label></td>
	  		<td>
	  		<s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapNation()" name="nationCd" listKey="key" listValue="value"/>
	  		</td>
	    	<td><label for="subject">籍贯</label></td>
	  		<td><input style="width:80%;" type="text" name="nativeProvinceDesc" value="${nativeProvinceDesc}"></input></td>
	 	</tr>
		<tr class="other_append">
		  	<td><label for="subject">户口所在地</label></td>
			<td><input style="width:80%;" type="text" name="nativePlaceDesc" value="${nativePlaceDesc}"></input> </td>
		   	<td><label for="subject">婚姻状况</label></td>
			<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapMarrigeStatus()" name="marrigeStatusCd" listKey="key" listValue="value"/></td>
		</tr>
		<tr class="other_append">
		  	<td><label for="subject">毕业院校</label></td>
		 	<td><input style="width:80%;" type="text" name="gradSchoolDesc" value="${gradSchoolDesc}"></input></td>
		   	<td><label for="subject">学历</label></td>
			<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapSchoolRecord()" name="schoolRecordCd" listKey="key" listValue="value"/></td>
		</tr>
		<tr class="other_append">
		   	<td><label for="subject">专业</label></td>
		 	<td><input style="width:80%;" type="text" name="majorDesc" value="${majorDesc}"></input></td>
		   	<td><label for="subject">入司日期</label></td>
			<td>
				<%-- class="easyui-datebox" --%>
				<input type="text" id="attendWorkDate" name="attendWorkDate" 
				<s:if test="attendWorkDate!= null">
				 value='<s:date name="attendWorkDate"  format="yyyy-MM-dd"/>'
				</s:if>
				/>(格式:yyyy-MM-dd)
			</td>
		</tr>
		<tr class="other_append">
		  	<td><label for="subject">员工类型</label></td>
		 	<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapMemberType()" name="memberTypeCd" listKey="key" listValue="value"/></td>
		</tr>
		<tr class="other_append">
		  	<td><label for="subject">政治面貌</label></td>
		 	<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapPolitics()" name="politicsCd" listKey="key" listValue="value"/></td>
		 	<td><label for="subject">是否特殊用户</label></td>
		 	<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapSpecialUserFlg()" name="specialUserFlg" listKey="key" listValue="value"/></td>
		</tr>
		<%--
		<tr class="other_append">
		  	<td><label for="subject">邮箱地址</label></td>
		 	<td><input style="width:200px" class="easyui-validatebox"   validType="email" type="text" name="email" value="${email}" style="width:auto;"></input></td>
		</tr>
		 --%>
		<tr class="other_append">
		  	<td><label for="subject">职称</label></td>
		  	<td> 
		   		<s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapProfessionType()" name="professionTypeCd" listKey="key" listValue="value"/>
 		  	</td>
		 	<td><label for="subject">默认凭证号</label></td>
		 	<td> 
		   		<input style="with:80%;" type="text" name="defaultCredenc" value="${defaultCredenc}"></input>
		 	</td>
		</tr>
		<tr class="other_append">
			<td><label for="subject">其他说明</label></td>
		 	<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapOtherType()" name="otherTypeCd" listKey="key" listValue="value"/></td>
		</tr>
		<tr class="other_append">
			<td><label for="subject">备注</label></td>
			<td colspan="3">
				<textarea name="remark" style="height:60px;width:90%">${remark}</textarea>
			</td>
		</tr>
		
		<s:if test="plasUserId!=null">
		<tr>
			<td><label for="subject">创建日期</label></td>
			<td colspan="3"><s:date name="createdDate" format="yyyy-MM-dd hh:mm:ss"/> </td>
		</tr>
		</s:if>
		<%--
		<s:if test="viewMode != 'my'">
		<tr class="panel-header">
			<td colspan="4"  style="line-height:25px;padding-left:5px;">邮件签名</td>
		</tr>
		<tr>
		 	<td colspan="4">
		 	<div>
		 		${emailSignContent}
		 	</div>
		 		<textarea id="detailShow"  style="height:300px;width:100%">${emailSignContent}</textarea>
		 	</td>
		</tr>
		</s:if>
		 --%>
	</table>
	
</s:form>
</div>


<script type="text/javascript"> 

	$(function() {
		
		//上传照片
		$('#wUploadFile').window({
			title: '上传照片',
			modal: true,
			closed: true,
			cache: false,
			top: 10,
			iconCls:"icon-save",
			onClose:function(){
				var id = '${plasUserId}';
				var tmpId = '${entityTmpId}';
				if('' == id){
					reloadPics(tmpId);
				}else{
					reloadPics(id);
				}
			}
		});
		
		//开通账号
		$('#wOpenAcct').window({
			title: '开通账号',
			modal: true,
			closed: true,
			cache: false,
			iconCls:"icon-save",
			onOpen:function(){
				$('body').mask();
			},
			onClose:function(){
				$('body').unmask();
			}
		});
		
		//选择上级机构
		$('#wParentOrg').window({
			title: '请选择机构',
			modal: true,
			closed: true,
			cache: false,
			top: 10,
			iconCls:"icon-save",
			onOpen:function(){
				$('body').mask();
			},
			onClose:function(){
				$('body').unmask();
			}
		});
		
		/*
		//注册快速搜索(模糊匹配:uiid,userName)
		$('#realPosName').quickSearch(
			'${ctx}/plas/plas-real-position!quickSearchRealPosList.action',
			['realPosCd','realPosName'],
			{realPosCd:'realPosCd',realPosName:'realPosName'},
			'',
			function(result){}
		);
		*/
		
		//载入照片
		//alert('${plasUserId}');
		if(''!='${plasUserId}'){
			reloadPics('${plasUserId}');
		}

		//默认不显示其他
		$('.other_append').hide();
		$('#btnOtherMore').toggle(
			function(){
				$('.other_append').show();
			},
			function(){
				$('.other_append').hide();
			}
		);
		
		//签名
	  	//showEditor();
	});

	//上传图片
	//'${entityTmpId}','${plasUserId}','uaapUser'
	function loadFile(plasUserId,entityTmpId){
		var data = {
			bizEntityId : plasUserId,
			bizModuleCd : '<%= Constants.APP_ATTACH_FILE_USER%>', 
			filterType:'image',
			attachModelType: '1' //1-只允许一张
		};
		//alert('entityTmpId:${entityTmpId} \n bizEntityId:${plasUserId}');
		showUploadFile(data,entityTmpId,'wUploadFile',false);//app-attachment.js
	}
	

	//保存用户明细
	function saveUser(){

		var userName = $.trim($('#userName').val());
		if(userName == ''){
			alert('请填写姓名!');
			$('#userName').focus();
			return false;
		}
		
		var sexCd = $.trim($('#sexCd').val());
		if(sexCd == ''){
			alert('请选择性别!');
			$('#sexCd').focus();
			return false;
		}
		
		var serviceStatusCd = $.trim($('#serviceStatusCd').val());
		if(serviceStatusCd == ''){
			alert('请选择用户在职状态!');
			$('#serviceStatusCd').focus();
			return false;
		}

		var permissionLevelCd = $.trim($('#permissionLevelCd').val());
		if(permissionLevelCd == ''){
			alert('请选择职级!');
			$('#permissionLevelCd').focus();
			return false;
		}
		
		
		var workDutyDesc = $.trim($('#workDutyDesc').val());
		if(workDutyDesc == ''){
			alert('请填写职务!');
			$('#workDutyDesc').focus();
			return false;
		}
		
		var mobilePhone = $.trim($('#mobilePhone').val());
		if(mobilePhone == ''){
			alert('请填写手机号码!');
			$('#mobilePhone').focus();
			return false;
		}
		
		var idno = $.trim($('#idno').val());
		if(idno == ''){
			alert('请填写证件号码!');
			$('#idno').focus();
			return false;
		}
		
		var userTypeCd = $.trim($('#userTypeCd').val());
		if(userTypeCd == ''){
			alert('请选择用户类型!');
			$('#userTypeCd').focus();
			return false;
		}
		
		var attendDate = $('#attendWorkDate').val();
		if(!CheckDate(attendDate)){
			alert('日期格式不对!');
			return false;
		}
		
		var url = '${ctx}/plas/plas-user!validateIdno.action';
		var userId = $('#plasUserId').val();
		var idno = $('#idno').val();
		var data = {userId: userId, idno: idno};
		$.post(url, data, function(result){
			if('success' == result){
				$('#userForm').form('submit',{
					url:'${ctx}/plas/plas-user!save.action',
					onSubmit: function(){
						$('body').mask('正在校验用户信息..');
						
						var flagForm = $('#userForm').form('validate');
						if(!flagForm){
							$('body').unmask();
							return false;
						} 
						$('body').mask('正在提交用户信息,请稍候..');
						//getSignContentForSubmit();//引用plas-user.jsp
						return true;
					},
					success:function(result){
						var userId = result;
						$('body').unmask();
						if('${viewMode}' == 'my'){
							refreshMyEdit(userId);
							//$.messager.alert('提示','保存成功');
						}else{
							reloadUserTree();
							refreshUserArea(userId);
							//$.messager.alert('提示','保存成功');
						}
					}
				});
			}else{
				$.messager.alert('提示','证件号码['+idno+']<br/>已被职员 '+result+' 使用,请核对证件号码!<br/> 若有疑问请联系系统管理员,谢谢!');
			}
		});
		
	}
	
	//检查日期格式: yyyy-MM-dd
	function CheckDate(str)    
    {   
		if($.trim(str) == ''){
			return true;	
		}
        var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);    
        if(r==null)
        {
            return false;    
        }
        else
        {
            var d= new Date(r[1], r[3]-1, r[4]);    
            return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
        }   
    } 

	//搜索照片清单
	function reloadPics(id){
		$('#pics_area').mask('载入照片中...');
		var url = "${ctx}/plas/plas-user!pics.action";
		var data = {id: id};
		$.post(url,data,function(result){
			$('#pics_area').unmask();
			$("#pics_area").html(result); 
		});
	}

	//开通账号
	function openUserAcct(plasUserId){ 
		var v = '${ctx}/plas/plas-user!validateMobilePhone.action';
		$.post(v,{plasUserId: plasUserId},function(result){
			if('success' == result){
				var url = '${ctx}/plas/plas-user!openAcct.action';
				$.post(url,{plasUserId: plasUserId},function(result){
					$('#wOpenAcct').html(result);
					$('#wOpenAcct').window('open');
					$.parser.parse('#wOpenAcct');
				});
			}else{
				alert(result+'请修改保存后开通账号!');
			}
		});
	}
	//保存开通账号明细
	function saveOpenAcctForm(){
		$("#wOpenAcct").mask('校验中,请稍候...');
		var tmpPlasUserId = $('#openAcct_plasUserId').val();
		$('#openAcctForm').form('submit',{
			url: '${ctx}/plas/plas-acct!saveOpenAcct.action',
			onSubmit: function(){
				var flag = $('#openAcctForm').form('validate');
				if(flag){
					$("#wOpenAcct").mask('正在开通账号中,请稍候...');
				}else{
					$("#wOpenAcct").unmask();
				}
				return flag;
			},
			success:function(result){
				$("#wOpenAcct").unmask();
				
				if(result == 'success'){
					$('#wOpenAcct').window('close');
					refreshUserArea(tmpPlasUserId,'');
					$.messager.alert('提示','开通账号成功!');
				}else{
					$.messager.alert('提示','开通账号失败!\n'+result);
				}
			}
		});
	}
	//重置
	function resetOpenAcctForm(){
		$('#openAcctForm')[0].reset();
	}
	function initTipList(){
		$('#acctListDiv').html('提示:账号以首字全拼加其他字首字符.<br/>例如:<br/>若张三,则zhangsan,zhansan1...<br/>若欧阳振华,则ouyzh,ouyzh1...').show();
	}
 
	//提交申请
	function processOpenAcctForm(){ 
//		var acctType = $('#acctType').val();
//		if(acctType==''){
//			return;
//		}

		$('#span_openAcct_effectDate').hide();
		
		//日期格式
		var tDate = $.trim($('#openAcct_effectDate').val());
		if(tDate == '' || !validateCNDate(tDate)){//plas-user.jsp
			$('#span_openAcct_effectDate').fadeIn('2000');
			return;
		}
		
		var uiid = $.trim($('#openAcct_uiid').val());
		if( uiid == ''){
			$('#openAcct_uiid_tip').html('请输入').css({'color':'red'}).show();
			initTipList();
			$('#openAcct_uiid').focus();
			return;
		}
		
		if(isSelectMode()){
			var posId = $.trim($('#openAcct_posId').val());
			//var acctTypeCd = $('input[name="acctTypeCd"]:checked').val();
			if( posId == ''){// && acctTypeCd == ''
				if(!window.confirm('未选择职位,是否继续?')){
					$('#openAcct_posName').focus();
					return;
				}
			}
		}
		
		var url = '${ctx}/plas/plas-acct!isUiidExists.action';
		var data = {uiid:uiid};
		$.post(url,data,function(result){
			if('false' == result){
				saveOpenAcctForm();
			}
		});
	}
	
	//关闭
	function canceOpenAcctForm(){
		$('#wOpenAcct').window('close');
	}
	
	function validateUiid(){
		var uiid = $.trim($('#openAcct_uiid').val());

		if( uiid == ''){
			$('#openAcct_uiid_tip').html('请输入').css({'color':'red'}).show();
			initTipList();
			return;
		}
		
		var pattern=/^[-a-zA-Z0-9]{4,20}$/;
		if(!pattern.exec(uiid)){
			$('#openAcct_uiid_tip').html("请使用4-20位字符的英文、数字组合! 账号重复,请按先后顺序，如: limou3").css({'color':'red'}).show();
			return false;
		}
		
		var url = '${ctx}/plas/plas-acct!isUiidExists.action';
		var data = {uiid:uiid};
		$('#openAcct_uiid_tip').hide();
		$('#acctListDiv').hide();
		$.post(url,data,function(result){
			if(result=='true'){
				$('#openAcct_uiid_tip').html('已占用').css({'color':'red'}).show();
				var url2 = '${ctx}/plas/plas-acct!getLikeAcctList.action';
				$.post(url2,{uiid: uiid},function(result){
					$('#acctListDiv').html(result).show();
				});
			}else{
				$('#openAcct_uiid_tip').html('可用').css({'color':'green'}).show();
				initTipList();
			}
		});
		
	}

	//离职
	function leaveCompany(userId){
		if(!window.confirm('员工离职,若已开通账号,则自动注销,是否继续?')){
			return;
		}
		var url = '${ctx}/plas/plas-user!leaveCompany.action';
		$.post(url, {id: userId}, function(result){
			if('success' == result){
				refreshUserArea(userId);
				$.messager.alert('提示','操作成功!已将该员工设置为离职,同时注销账号!');
			}else{
				$.messager.alert('提示','保存失败!\n'+result);
			}
		});
	}
	
	function refreshMyEdit(){
		$('body').mask('刷新中...');
		document.location = '${ctx}/plas/plas-user!my.action';
	}
	function delUserById(userId, orgId){
		var url = '${ctx}/plas/plas-user!delUserById.action';
		$.post(url, {userId: userId, orgId: orgId}, function(result){
			if('success' == result){
				refreshUserArea('');
				$.messager.alert('提示','删除成功!');
			}else{
				$.messager.alert('提示','删除失败!\n'+result);
			}
		});
	}
	
	//----------
	
	//自动产生职位
	var MODE_POS_AUTO = '1';
	//选择职位
	var MODE_POS_TARGET = '2';

	function isAutoMode(){
		return (MODE_POS_AUTO == $('#openAcct_modeCd').val());
	}
	function isSelectMode(){
		return (MODE_POS_TARGET == $('#openAcct_modeCd').val());
	}
	
	function showDisplayPos(dom){
		var acctTypeCd = $(dom).val();

		if(isAutoMode()){
			//1-PD用户 2-通讯员 3-邮件用户
			if('3' == acctTypeCd){
				$('.openAcct_pos_row').hide();
			}else{
				$('.openAcct_pos_row').show();
			}
		}
	}
	
</script>