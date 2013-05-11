<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<html>
<head>
<title></title>
<script type="text/javascript">
	$(document).ready(function(){
		/**获得焦点显示*/
		$("#equipTypeCd").bind("focus",function(){
			var t = $("#equipTypeCd").position().left;
			$("#typeCdChoiseId").position().left = t;
			$("#typeCdChoiseId").show();
		});
		$("#equipStatus").bind("focus",function(){
			$("#statusChoiceId").show();
		});
		/**失去焦点隐藏*/
		$("#equipTypeCd").bind("blur",function(){
			$("#typeCdChoiseId").hide();
		});
		$("#equipStatus").bind("blur",function(){
			$("#statusChoiceId").hide();
		});
		/**隐藏新增按钮*/
		if($.trim($("#methodId").val())=="update"){
			$("#btnEquipAdd").hide();
			$("#btnEquipEdit").show();
		}else{
			$("#btnEquipEdit").hide();
			$("#btnEquipAdd").show();
		}
	});
	/**li单击事件*/
	function choiseLi(demo,val){
		$("#typeCdChoiseId ul li").removeClass("selected");
		$(demo).addClass("selected");
		$("#equipTypeCd").val($(demo).html());
		$("#equipTypeCdVal").val(val);
		$("#typeCdChoiseId").hide();
	}
	/**状态选择*/
	function statusChoiseLi(demo,val){
		if(val==2){
			$("#equipExceptionId").addClass("required");
		}else{
			$("#equipExceptionId").removeClass("required");
		}
		$("#statusChoiceId ul li").removeClass("selected");
		$(demo).addClass("selected");
		$("#equipStatus").val($(demo).html());
		$("#equipStatusVal").val(val);
		$("#statusChoiceId").hide();
	}
</script>
<style type="text/css">
#typeCdChoiseId{
	padding-top:0px;
	position:absolute;
	display:none;
	background:rgb(255, 255, 255);
	font-size:12px;
	width:390px;
	height:60px;
	top: 196px;
	left:282px;
	border:0px;
}
#statusChoiceId{
	padding-top:0px;
	position:absolute;
	display:none;
	background:rgb(255, 255, 255);
	font-size:12px;
	border:0px;
	height:26px;
	width:250px;
	left:282px;
	top:370px;
}
</style>
</head>
<body>
<div id="body" class="publish" style="margin-left: 10px;">
	<div class="title" style="margin: 5px;">
	    <h2>新增工程设备</h2>
	</div>
	<div class="res_tip" style="display: none;">
		<span>注意：</span>
	</div>
	<div>
		<div class="btns clearfix" style="margin-top: 10px;">
			<security:authorize ifAnyGranted="A_ENG_EQUIP_ADD">
				<button type="button" id="btnEquipAdd" class="green min" onclick="saveAccount();" style="margin-right: 5px;height: 25px;">保存</button>
			</security:authorize>
			<security:authorize ifAnyGranted="A_ENG_EQUIP_EDIT">
				<button type="button" id="btnEquipEdit" class="green min" onclick="saveAccount();" style="margin-right: 5px;height: 25px;">更新</button>
			</security:authorize>
			<button type="button" class="red min" id="btnCancelId" style="margin: 0px;height: 25px;" onclick="backAccount();">返回</button>
		</div>
		<div class="body_warp">
			<div style="margin-top: 10px;margin-bottom: 10px;" class="form_body">
				<form name="saveForm" id="saveFormId" action="${ctx}/engineer/engineer-equip-account!save.action" method="post">
				<input type="hidden" id="onlyViewId" value="
					<security:authorize ifAnyGranted="A_ENG_EQUIP_ADD,A_ENG_EQUIP_EDIT">notOnlyView</security:authorize>
				"/>
				<input type="hidden" name="method" id="methodId" value="${method}"/>
				<input type="hidden" name="entityTmpId" id="entityTmpId" value="${entityTmpId}"/>
				<input type="hidden" name="id" id="id" value="${entity.engineerEquipAccountId }"/>
				<input type="hidden" name="engineerEquipAccountId" id="newengineerEquipAccountId" value="${entity.engineerEquipAccountId }"/>
					<ul class="clearfix">
						<li>
							<label>项目公司：</label><s:property value="@com.hhz.ump.util.CodeNameUtil@getProjectName(entity.bisProjectId)" /><input type="text" name="bisProjectId" readonly="readonly" onfocus="blur()" style="display:none; width: 496px;" value="${entity.bisProjectId }"/>
						</li>
						<li>				
							<label>设备业态：</label><label style="text-align:left;margin-right:125px;"><s:property value="@com.hhz.ump.util.CodeNameUtil@getEquipLayoutName(entity.layoutCd)" /></label><input type="hidden" name="layoutCd" class="text required" readonly="readonly" style="width: 200px;" value="${entity.layoutCd}"/>
							<label>所属系统：</label><s:property value="@com.hhz.ump.util.CodeNameUtil@getEquipBelongToName(entity.equipBelongtoCd)" /><input type="hidden" name="equipBelongtoCd" class="text max required" style="width: 496px;" value="${entity.equipBelongtoCd}"/>
							</li>
						<li>				
							<label>设备分类：</label><input type="hidden" name="equipTypeCd" class="required" id="equipTypeCdVal" value="${entity.equipTypeCd }"/>
							<input type="text" id="equipTypeCd" readonly="readonly" class="text required" style="width: 496px;" value="<p:code2name mapCodeName="mapEquipTypeCd" value="entity.equipTypeCd" />"/>
						</li>
						<li>				
							<label>设备编号：</label><input type="text" name="equipCd" id="equipCdId" class="text required" style="width: 200px;" value="${entity.equipCd }" onblur="valiEquipCdOnBlur()"/>
							<label>设备名称：</label><input type="text" name="equipName" class="text required" style="width: 200px;" value="${entity.equipName }"/>
						</li>
						<li>				
							<label>型号规格：</label><input type="text" name="equipModels" class="text max required" style="width: 200px;" value="${entity.equipModels }"/>
							<label>出厂日期：</label><input type="text" name="equipSerialDate" class="text required" style="width: 200px;" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${entity.equipSerialDate }"/>
						</li>
						<li>				
							<label>制造厂名：</label><input type="text" name="equipMaker" class="text required" style="width: 200px;" value="${entity.equipMaker }"/>
							<label>出厂编号：</label><input type="text" name="equipSerialNum" class="text required" style="width: 200px;" value="${entity.equipSerialNum }"/>
						</li>
						<li>
							<label>设备位置：</label><input type="text" name="equipPosition" class="text required" style="width: 200px;" value="${entity.equipPosition }"/>
							<label>设备数量：</label><input type="text" name="equipAmount" class="text required" style="width: 200px;" value="${entity.equipAmount }"/>
						</li>
						<li>
							<label>设备状态：</label><input type="hidden" class="required" name="equipStatus" id="equipStatusVal" value="${entity.equipStatus }"/>
							<input id="equipStatus" value="<p:code2name mapCodeName="mapequipStatus" value="entity.equipStatus"/> " style="width: 200px;" class="text required" readonly="readonly" />
							
							<label>异常情况：</label><input type="text" name="equipException" id="equipExceptionId" class="text" style="width: 200px;" value="${entity.equipException}"/>
						</li>
						<li>				
							<label title="上传">文件资料：</label>
							<a href="javascript:openAttachment('${engineerEquipAccountId==null?entityTmpId:engineerEquipAccountId}');" >
							<img id="attachFlgImgId" <s:if test="entity.attachFlg==1">src="${ctx}/resources/images/common/atta_y.gif"</s:if><s:else>src="${ctx}/resources/images/common/atta.gif"</s:else> />
							</a>
							<input type="hidden" id="attachFlgId" name="attachFlg" class="text max" style="width: 496px;" value="${entity.attachFlg}"/>
						</li>
					</ul>
				</form>
			</div>
		</div>
	</div>
	<div id="typeCdChoiseId" onmouseover="typeCdChoiseMouseOver()" onmouseout="typeCdChoiseMouseOut()">
		<div id="navigationInput"  style="margin:0px;">
			<ul class="clearfix" style="width:390px;">
				<li class="selected" onclick="choiseLi(this,1)" style="width:95px;margin: 1px">升降设备DT</li>
				<li class="" onclick="choiseLi(this,2)" style="width:95px;margin: 1px">消防设备XF</li>
				<li class="" onclick="choiseLi(this,3)" style="width:95px;margin: 1px">给排水设备GPS</li>
				<li class="" onclick="choiseLi(this,4)" style="width:95px;margin: 1px">暖通空调设备NT</li>
				<li class="" onclick="choiseLi(this,5)" style="width:95px;margin: 1px">弱电设备RD</li>
				<li class="" onclick="choiseLi(this,6)" style="width:95px;margin: 1px">强电设备QD</li>
				<li class="" onclick="choiseLi(this,7)" style="width:95px;margin: 1px">主力店设备ZLD</li>
				<li class="" onclick="choiseLi(this,8)" style="width:95px;margin: 1px">其他设备QT</li>
			</ul>
		</div>
	</div>
	<div id="statusChoiceId" onmouseover="statusChoiceMouseOver()" onmouseout="statusChoiceMouseOut()">
		<div id="navigationInput" style="margin:0px;height: 26px;">
			<ul class="clearfix"  style="height: 26px;">
				<li class="selected" onclick="statusChoiseLi(this,1)" style="margin: 1px">正常</li>
				<li class="" onclick="statusChoiseLi(this,2)" style="margin: 1px">异常</li>
				<li class="" onclick="statusChoiseLi(this,3)" style="margin: 1px">报废</li>
			</ul>
		</div>
	</div>
</div>
</body>
</html>