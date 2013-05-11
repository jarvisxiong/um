<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<div id="body" class="publish" style="margin-left: 10px;">
	<div class="title1 none">
	    <h2>新增资产</h2>
	</div>
	<div class="informations none"></div>
	<div class="title" style="margin-bottom: 5px;margin-left: 3px;">
		<s:if test="assmAccountId!=null && assmAccountId!=''">修改</s:if><s:else>新增</s:else>固定资产
	</div>
	<div class="res_tip" style="height: 45px;">
		<span style="margin-left: 10px;">注意：1、“原值” 须大于或者等于500元；</span><br/>
		<span style="margin-left: 46px;">2、同一 “保管人员” 不能拥有两个或两个以上的电脑设备；</span><br/>
		<span style="margin-left: 46px;">3、“保管人员(PD)”和“保管人员(非PD)” 只能填写一个。</span>
	</div>
	
	<div class="btns clearfix" style="margin-top: 10px;">
	    <s:if test="assmAccountId!=null && assmAccountId!=''">
			 <button type="button" class="green min" onclick="loadAccount();">返回</button>
		</s:if>
		<security:authorize ifAnyGranted="A_ASSM_ACC_EDIT">
			 <button type="button" class="green min" onclick="saveAccount();">保存</button>
		</security:authorize>
		<s:if test="assmAccountId!=null && assmAccountId!=''">
			<security:authorize ifAnyGranted="A_ASSM_ACC_DEL">
				<button type="button" class="red min" onclick="deleteAccount('${assmAccountId}');">删除</button>
			</security:authorize>
		</s:if>
		<s:else>
			 <button type="button" class="red min" onclick="canleAccount();">取消</button>
		</s:else>
	</div>
	
	<div class="body_warp">
	    <div class="form_body" style="margin-top: 10px;margin-bottom: 10px;">
           <form action="${ctx}/assm/assm-account!save.action" id="accountFrom" method="post">
           		<input type="hidden" name="recordVersion" value="${recordVersion}"/>
				<input type="hidden" name="assmAccountId" id="assmAccountId" value="${assmAccountId}"/>
	        	<ul class="clearfix">
	                <li>
	                    <label style="width: 90px;">商业公司：</label>
	                    <s:if test="assmAccountId!=null && assmAccountId!=''">
							<input type="text" id="projectName" value="<p:code2name mapCodeName="mapBisProject" value="projectCd"/>" class="text max required" style="width: 451px;background-color:#EDEFF3;"/>
						</s:if>
						<s:else>
							<input type="text" id="projectName" value="${deptName}" readonly="readonly" class="text max required" style="width: 451px;background-color:#EDEFF3;"/>
						</s:else>
						<input type="hidden" name="projectCd" id="projectCd" value="${projectCd}"/>
	                </li>
	                <li>
	                    <label style="width: 90px;">设备型号：</label>
	                    <input type="text" 
	                    	   class="text required"
					           name="assmModelName" 
					           id="assmModelName" 
					           readonly="readonly"
						       <s:if test="assmAccountId!=null && assmAccountId!=''">
							       value="<p:code2name mapCodeName="mapAssmModelAll" value="assmModel.assmModelId"/>" 
							       style="width: 170px;background-color:#EDEFF3;"
						       </s:if>
						       <s:else>
						       	   style="width: 170px;cursor:pointer;"
							       onclick="doAssmModel();" 
							       title="点击选择设备型号" 
						       </s:else>
					   	   />
						<input type="hidden" id="assmModelId2" name="assmModelId2" value="${assmModel.assmModelId}"/>
						<input type="hidden" id="assmModelStandardId" name="assmModelStandardId" value="${assmModelStandard.assmModelStandardId}"/>
						
						<label style="width: 100px;">型号编码：</label>
	              		<input type="text" class="text required" name="assmCode" id="assmCode" value="${assmCode}" readonly="readonly" style="width: 170px;background-color:#EDEFF3;"/>
	                </li>
	                <li>
	                	<label style="width: 90px;">资产名称：</label>
	              		<input type="text" class="text max required"  name="assmName" id="assmName" value="${assmName}" style="width: 170px;" maxlength="50"/>
	              		<label style="width: 100px;">资产编码：</label>
	              		<input type="text" class="text max required" name="code" id="code" value="${code}" onblur="checkCode(this.value);" style="width: 170px;" maxlength="25"/>
	                </li>
	                <li>
	              		<label style="width: 90px;">使用部门：</label>
	              		<input type="text" class="text max required" name="useDepartmentName" id="useDepartmentName" value="<%= CodeNameUtil.getDeptNameByCd(JspUtil.findString("useDepartment"))%>" readonly="readonly" style="width: 451px;cursor: pointer;" title="点击选择部门"/>
						<input type="hidden" id="useDepartment" name="useDepartment" value="${useDepartment}"/>
	                </li>
	                <li>
	              		<label style="width: 90px;">保管人员(PD)：</label>
	              		<input type="text" class="text max required" name="keeperName" id="keeperName" value="${keeperName}" readonly="readonly" style="width: 170px;cursor: pointer;" title="点击选择用户"/> 
						<input type="hidden" id="keeperCd" name="keeperCd" value="${keeperCd}"/>
						<%-- 
							如果选中设备型号的第三级父类是‘电脑’类，则一个保管人员只能保 管
						 	 一个电脑类的设备，如果超过一个设备，保存时需提示用户(但仍然可保存)
						--%>
						<input type="hidden" id="computerType" value="${computerModelName}"/>
						<input type="hidden" id="selectThridModelId"/>
						
						<label style="width: 100px;">保管人员(非PD)：</label>
	              		<input type="text" class="text max required" name="keeperName2" id="keeperName2" value="${keeperName2}" onblur="clearKeeperName(this);" style="width: 170px;" title="请输入无PD账号的保管人员"/> 
	                </li>
	                <li>
	                	<label style="width: 90px;">使用情况：</label>
	              		<s:select list="mapUseStatus" listKey="key" listValue="value" name="useStatus" id="useStatus" onchange="netValueChange(this.value);" cssClass="box required" cssStyle="width:180px;"/>
	              		<label style="width: 100px;">购入时间：</label>
	              		<input type="text" 
	              			   class="text max required"
						       name="useDate" 
						       id="useDate" 
						       readonly="readonly" 
						       value="<s:date name="useDate" format="yyyy-MM-dd"/>" 
					       	   style="width: 170px;"
					       	   onfocus="WdatePicker()"
						/>
	                </li>
	                <li>
	              		<label style="width: 90px;">存放地点：</label>
	              		<s:select list="mapStorageSites" listKey="key" listValue="value" name="storageSites" id="storageSites" cssClass="box" cssStyle="width:180px;"/>
	                	<label style="width: 100px;">增加方式：</label>
	              		<s:select list="mapAddWays" listKey="key" listValue="value" name="addWay" id="addWay" cssClass="box" cssStyle="width:180px;"/>
	                </li>
	                <li>
	              		<label style="width: 90px;">原值&nbsp;(元)：</label>
	              		<input type="text" 
	              			   class="text max required"
					           name="srcValue" 
					           id="srcValue" 
					       	   value="${srcValue}" 
					       	   style="width: 170px;"
					       	   <s:if test="assmAccountId!=null && assmAccountId!=''">
						      	 	onkeyup="clearNoNum_1(this);" 
						       </s:if>
						       <s:else>
						       		onkeyup="clearNoNum_1(this);getRemain(this.value);" 
						       </s:else>
						       maxlength="10"
					           title="原值须大于等于500"
					    />
					    <label style="width: 100px;">净值&nbsp;(元)：</label>
	              		<input type="text" class="text max required" name="remainVal" id="remainVal" value="${remainVal}" readonly="readonly" style="width: 170px;background-color:#EDEFF3;"/>
	                </li>
	                <li style="display: none;" id="netValueLi">
	              		<label style="width: 90px;">残值&nbsp;(元)：</label>
	              		<input type="text" id="netValue" name="netValue" value="${netValue}" readonly="readonly" onkeyup="clearNoNum_1(this);" maxlength="10" class="text max" style="width: 451px;background-color:#EDEFF3;" title="只有当净值为0或报废状态才能填写"/>
	                </li>
	                <li>
	              		<label style="width: 90px;">折旧年限：</label>
	              		<input type="text" class="text max required" name="depreYeadNum" id="depreYeadNum" value="${depreYeadNum}" readonly="readonly" style="width: 170px;background-color:#EDEFF3;"/>
	                	<label style="width: 100px;">折旧方式：</label>
	              		<input type="text" class="text max required" name="depreWay" id="depreWay" value="${depreWay}" readonly="readonly" style="width: 170px;background-color:#EDEFF3;"/>
	                </li>
	                <li>
	              		<label style="width: 90px;">当前配置：</label>
	              		<input type="text" class="text max required" id="currHasNum" readonly="readonly" value="${currNum}" style="width: 170px;background-color:#EDEFF3;"/>
	                	<label style="width: 100px;">标准配置：</label>
	              		<input type="text" class="text max required" id="currStanNum" readonly="readonly" value="${stanNum}" style="width: 170px;background-color:#EDEFF3;"/>
	                
	                </li>
	        	</ul>
           </form>
	    </div>
	    <div class="form_body" style="margin-top: 10px;margin-bottom: 10px;">
		<s:iterator value="res" var="r">
		<ul>
		<li>
		<span>${r.authTypeName}</span>
		<span><s:date name="#r.completeDate" format="yyyy-MM-dd"/></span>
		<span>${r.displayNo}</span>
		<span style="cursor: pointer; text-decoration: underline;" onclick="parent.showAll('${ctx}/res/res-approve-info.action?id=${r.resApproveInfoId}','resApprove');">审批内容</span>
		</li>
		</ul>
		</s:iterator>
	    </div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	netValueChange(null);
	//选择保管人员
	$("#keeperName").userSelect({
        muti:false,
        nameField:'keeperName',
        cdField:'keeperCd',
        callfun:"clearKeeperName2()"
	});
	//选择使用部门
	$('#useDepartmentName').orgSelect({});
});

//选择设备型号
function doAssmModel(){
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='selectTypeDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 580,
		height : 500,
		title : "设备型号选择",
		afterShow : function() {
			var url = _ctx+"/assm/assm-account!getAssmModel.action";
			$.post(url, {}, function(result) {
				$("#selectTypeDiv").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				//选中的三级设备名称(用于判断‘电脑’类的设备保管人只能有一个)
				var selectTwoAssmName = $('#selectThridAssmName').val();
				$("#computerType").val(selectTwoAssmName);
				$("#selectThridModelId").val($('#selectThridAssmId').val());
				//选中的第四级设备的ID
				var selectAssmModelId = $('#selectAssmModelId').val();
				//选中的第四级设备的名称
				var selectAssmModelName = $('#selectAssmModelName').val();
				var projectCd = $('#projectCd').val();
				if(selectAssmModelId == null || selectAssmModelId ==''){
					alert('请选择四级设备');
					return false;
				}
				var data ={
					assmModelId:selectAssmModelId,
					projectCd:projectCd
				};
				//根据设备型号自动带出型号编码、残值、当前配置、标准配置等
				var url1 = _ctx+"/assm/assm-account!getValues.action";
				$.post(url1,data, function(result) {
					$("#assmModelId2").val(selectAssmModelId);
					$("#assmModelName").val(selectAssmModelName);
					if(result != null && "" != result){
						if("error"==result){
							alert("该商业公司未在资产台账中维护");
						}else{
							var rs=result.split(",");
							$("#assmCode").val(rs[0]);
							$("#assmModelStandardId").val(rs[1]);
							$("#currStanNum").val(rs[2]);
							$("#currHasNum").val(rs[3]);
							$("#depreYeadNum").val(rs[4]);
							$("#depreWay").val(rs[5]);
						}
					}
//					$("#selectTypeDiv").html(result);
				});
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["退出",'cancel']]
	});
}

//校验资产编码唯一性
function checkCode(value){
	var id = $("#assmAccountId").val();
	var url = _ctx+"/assm/assm-account!checkCode.action";
	$.post(url,{code:$.trim(value),id:id}, function(result) {
		if(result=='true'){
			alert("资产编码已经存在");
			//$("#code").focus();
			return false;
		}
	});
}

//校验保管人员(同一“保管人员”不能拥有两个或两个以上的设备)
function checkKeeper(){
	var keeperCd = $("#keeperCd").val();
	var id = $("#assmAccountId").val();
	var url = _ctx+"/assm/assm-account!checkKeeper.action";
	$.post(url,{keeperCd:$.trim(keeperCd),id:id}, function(result) {
		if(result=='true'){
			alert("该保管人员已经保管有设备");
			return false;
		}else{
			return true;
		}
	});
}

//当净值为0活使用情况为报废时残值可编辑
function netValueChange(useState){
	var assmAccountId = $("#assmAccountId").val();
	var remainVal = $("#remainVal").val();
	var value;
	if(useState != null && useState != ''){
		value = useState;
	}else{
		value = $("#useStatus").val();
	}
	if(value=='3'){
		$("#netValueLi").show();
		$("#netValue").attr("readonly","");
		$("#netValue").attr("style","width: 451px;background-color:#FFFFFF");
	}else{
		if(assmAccountId != null && assmAccountId != ''){
			if(remainVal != null && remainVal == '0'){
				$("#netValueLi").show();
				$("#netValue").attr("readonly","");
				$("#netValue").attr("style","width: 451px;background-color:#FFFFFF");
			}else{
				$("#netValueLi").hide();
				$("#netValue").val('');
				$("#netValue").attr("readonly","readonly");
				$("#netValue").attr("style","width: 451px;background-color:#EDEFF3");
			}
		}else{
			$("#netValueLi").hide();
			$("#netValue").val('');
			$("#netValue").attr("readonly","readonly");
			$("#netValue").attr("style","width: 451px;background-color:#EDEFF3");
		}
	}
}

function getRemain(value){
	$("#remainVal").val(value);
}

function clearKeeperName(dom){
	if($.trim(dom.value) != ''){
		$("#keeperName").val('');
		$("#keeperCd").val('');
		var	_userMap = {};
		var o = {userName:'',uiid:''};
		_userMap[$("#keeperName").val()] = o;
		var data = $.extend(true,{},null);
		$("#keeperName").data('userMap',data);
	}
}
function clearKeeperName2(){
	var keepName = $("#keeperName").val();
	if($.trim(keepName) != ''){
		$("#keeperName2").val('');
	}
}
</script>