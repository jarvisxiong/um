<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>材料设备价格详细信息</title>
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"  />
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.example.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
</head>

<body>
	<form action="${ctx}/cost/cost-mate-price!save.action" method="post" id="matePriceForm">
		<input type="hidden" name="recordVersion" id="recordVersion" value = "${recordVersion}" />
		<input type="hidden" name="costMateId" id="costMateId" value="${costMateId}"/>
		<input type="hidden" name="costMatePriceId" id="costMatePriceId" value="${costMatePriceId}"/>
		<input type="hidden" name="createdDate" id="createdDate" value="${createdDate}"/>
		
		<div class="title_bar">
			<div class="fLeft banTitle">	
				设备名称：<font style="font-weight: bolder;">${costMateName}</font> 
				<font color="red">注意：若规格、型号为空，则默认以"-"代替。</font>
			</div>
			<div class="fRight">
				<s:if test="costMatePriceId != null && costMatePriceId != ''">
					<input type="button"  class="btn_green"  value="继续新增" onclick="addCostMatePrice();"/>
				</s:if>
				<input type="button" class="btn_new btn_full_new" onclick="window.open(location.href)" value="全屏" />
				<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href" value="刷新" />
			</div>
		</div>
		<div>
				<div id="cont_basic">
				<table class="cont-show-table" style="width:100%;">
				<colgroup>
					<col/>
					<col/>
					<col/>
					<col/>
				</colgroup>
					<tr>
						<td align="right">*设备名称：</td>
						<td><input type="text" id="materialName" name ="materialName" value="${materialName}" maxlength='50'"/></td>
					</tr>
					<tr>
						<td align="right">*规格：</td>
						<td><input type="text" id="specName" name ="specName" value="${specName}" maxlength='50' onclick="clearValue(this.value,'specName');" onblur="setValue(this.value,'specName');"/></td>  
						<td align="right">*型号：</td>
						<td><input type="text" id="modelName" name="modelName" value="${modelName}" maxlength="50" onclick="clearValue(this.value,'modelName');" onblur="setValue(this.value,'modelName');"/></td>
					</tr>
					<tr>
						<td align="right">*价格：</td>
						<td><input type="text" id="price" name ="price" value="${price}" maxlength='15' onkeyup="clearNoNum_1(this);"/></td>  
						<td align="right">是否启用：</td>
						<td>&nbsp;&nbsp;
							<c:if test="${enableFlg != null && enableFlg!=''}">
								<s:radio list="#{'1':'是','0':'否'}" name="enableFlg" id="enableFlg"></s:radio>
							</c:if>
							<c:if test="${enableFlg == null || enableFlg==''}">
								<s:radio list="#{'1':'是','0':'否'}" name="enableFlg" id="enableFlg" value="1"></s:radio>
							</c:if>
						</td>  
					</tr>
					<tr>
						<td align="right">显示序号</td>
						<td><input type="text" name="sequenceNo" id="sequenceNo" value="${sequenceNo}"/> </td>
						<td></td>
						<td></td>
					</tr>
					<s:if test="costMateColList.size()>0">
						<s:iterator value="costMateColList" status="st">
					        <s:if test="#st.odd||#st.first">
					        	<tr>
					        </s:if>
					      		<td align="right">${colName}：</td>
					      		<td>
					      		<s:if test="colField=='f01'">
					      			<input type="text" id="${colField}" name ="${colField}" value="${f01}" maxlength='40'/>
					      		</s:if>
								<s:elseif test="colField=='f02'">
									<input type="text" id="${colField}" name ="${colField}" value="${f02}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f03'">
									<input type="text" id="${colField}" name ="${colField}" value="${f03}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f04'">
									<input type="text" id="${colField}" name ="${colField}" value="${f04}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f05'">
									<input type="text" id="${colField}" name ="${colField}" value="${f05}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f06'">
									<input type="text" id="${colField}" name ="${colField}" value="${f06}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f07'">
									<input type="text" id="${colField}" name ="${colField}" value="${f07}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f08'">
									<input type="text" id="${colField}" name ="${colField}" value="${f08}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f09'">
									<input type="text" id="${colField}" name ="${colField}" value="${f09}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f10'">
									<input type="text" id="${colField}" name ="${colField}" value="${f10}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f11'">
									<input type="text" id="${colField}" name ="${colField}" value="${f11}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f12'">
									<input type="text" id="${colField}" name ="${colField}" value="${f12}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f13'">
									<input type="text" id="${colField}" name ="${colField}" value="${f13}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f14'">
									<input type="text" id="${colField}" name ="${colField}" value="${f14}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f15'">
									<input type="text" id="${colField}" name ="${colField}" value="${f15}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f16'">
									<input type="text" id="${colField}" name ="${colField}" value="${f16}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f17'">
									<input type="text" id="${colField}" name ="${colField}" value="${f17}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f18'">
									<input type="text" id="${colField}" name ="${colField}" value="${f18}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f19'">
									<input type="text" id="${colField}" name ="${colField}" value="${f19}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f20'">
									<input type="text" id="${colField}" name ="${colField}" value="${f20}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f21'">
									<input type="text" id="${colField}" name ="${colField}" value="${f21}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f22'">
									<input type="text" id="${colField}" name ="${colField}" value="${f22}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f23'">
									<input type="text" id="${colField}" name ="${colField}" value="${f23}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f24'">
									<input type="text" id="${colField}" name ="${colField}" value="${f24}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f25'">
									<input type="text" id="${colField}" name ="${colField}" value="${f25}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f26'">
									<input type="text" id="${colField}" name ="${colField}" value="${f26}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f27'">
									<input type="text" id="${colField}" name ="${colField}" value="${f27}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f28'">
									<input type="text" id="${colField}" name ="${colField}" value="${f28}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f29'">
									<input type="text" id="${colField}" name ="${colField}" value="${f29}" maxlength='40'/>
								</s:elseif>
								<s:elseif test="colField=='f30'">
									<input type="text" id="${colField}" name ="${colField}" value="${f30}" maxlength='40'/>
								</s:elseif>
					      		</td>
					        <s:if test="#st.even||#st.last">
					           </tr>  
					        </s:if>
					    </s:iterator>
					</s:if>
					<tr valign="top">
						<td align="right">备注：</td>
						<td colspan="3">&nbsp;
							<textarea id="remark" name="remark" style="width:93%;height:80px;border:1px solid #ccc;color: #316685;font-size: 13px;">${remark}</textarea>
						</td>
					</tr>
					<tr valign="top">
						<td>&nbsp;</td>
						<td colspan="3">&nbsp;
							<security:authorize ifAnyGranted="A_COST_STRAGE_ADMIN">
								<input type="button"  class="btn_new btn_save_new"  value="保存" onclick="matePriceSave();"/>
							</security:authorize>
							<input type="button"  class="btn_new btn_green_new"  value="返回" onclick="back();"/>
						</td>  
					</tr>
				</table>
				</div>
		</div>
	</form>
<script type="text/javascript">
function matePriceSave(){
	var costMateId = $("#costMateId").val();
	var costMatePriceId = $("#costMatePriceId").val();
	var modelName = $("#modelName").val();
	var specName = $("#specName").val();
	var price = $("#price").val();
	if(modelName == null || $.trim(modelName) == ''){
		$("#modelName").val('-');
	}
	if(specName == null || $.trim(specName) == ''){
		$("#specName").val('-');
	}
	if(price == null || price==""){
		alert("请填价格");
		return false;
	}
	var data ={
				costMateId: costMateId,
				id: costMatePriceId, 
				modelName: $.trim(modelName),
				specName: $.trim(specName)
			};
	var url = "${ctx}/cost/cost-mate-price!checkMatePrice.action";
	$.post(url,data,function(result) {
		if("fail" == result){
			alert("型号已存在，请重新填写!");
			return false;
		}else{
			$('#matePriceForm').submit();
		}
	});
}
function addCostMatePrice(id){
	var costMateId = $('#costMateId').val();
	if(costMateId == "" || costMateId == null){
		alert("请先保存材料设备基本信息");
		return false;
	}
	var url="${ctx}/cost/cost-mate-price!input.action?costMateId="+costMateId;
	location.href = url;
}
//规格型号为空则默认设为”-“
function setValue(value,item){
	if(value == null || $.trim(value) == ''){
		$("#"+item).val("-");
	}
}
function clearValue(value,item){
	if(value != null && $.trim(value) == '-'){
		$("#"+item).val("");
	}
}

function refush(){
	var costMateId = $('#costMateId').val();
	var costMatePriceId = $('#costMatePriceId').val();
	var url="${ctx}/cost/cost-mate-price!input.action?id="+costMatePriceId+"&costMateId="+costMateId;
	location.href=url;
}

function back(){
	var url="${ctx}/cost/cost-mate!input.action?id="+$("#costMateId").val();
	location.href=url;	
}
</script>
</body>
</html>
