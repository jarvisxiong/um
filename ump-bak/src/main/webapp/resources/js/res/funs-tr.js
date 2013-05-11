//该文件是所有权责表节点下所有创建表单的方法，固定创建表单到上下文最后一个".stepBody"下的"Table"中
/**
 * 增加步骤元素 确定按钮
 * @param step
 * @param text
 */
function addItemOK(option1,option2,step){
	var obj = $(".stepBody:last table");
	var items = "<tr><td class='td_title' width='110' colspan='2'>"
	+"<input type='button' class='btn_blue ' style='margin-left:10px;line-height:26px;' onclick=\"doNextStep(\'"+option1+"\',\'"+option2+"\',\'"+step+"\'),finishOK(\'"+step+"\')\" value='确定'/>&nbsp;"
	+"<input type='button' class='btn_red' id='btn_cancel"+step+"' value='取消' onclick='removeStep(\""+step+"\")'/>"
	+"</td></tr>";
	$(obj).append(items);
}
/**
 * 点击确定按钮
 */
function finishOK(step){
}
/**
 * 复选框实现单选框的功能
 * 3层父级
 * @param demo
 */
function ckboxck(demo){
	var tmp = $(demo).parent().parent().parent().find("input");
	$.each(tmp, function(i,j){
		if($(demo).attr('name')!=$(j).attr('name')){
			$(j).attr("checked",false);
		}				 
	})
}
/**
 * 复选框实现单选框的功能
 * 2层父级
 * @param demo
 */
function ckboxck2(demo){
	var tmp = $(demo).parent().parent().find("input");
	$.each(tmp, function(i,j){
		if($(demo).attr('name')!=$(j).attr('name')){
			$(j).attr("checked",false);
		}				 
	})
}
/**
 * 评审类别(招标文件/)
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func1_1(){
	var obj = $(".stepBody:last table");
	var items1 = '<input type="hidden" name="templateBean.noncontlib" value="true" />';
	var items2 = '<input type="hidden" name="templateBean.contlib" value="false" />';
	$(obj).append(items1);
	$(obj).append(items2);
}
/**
 * 合同
 * @return
 */
function func1_2(){
	var obj = $(".stepBody:last table");
	var items1 = '<input type="hidden" name="templateBean.noncontlib" value="false" />';
	var items2 = '<input type="hidden" name="templateBean.contlib" value="true" />';
	$(obj).append(items1);
	$(obj).append(items2);
	//调用合同文本框编号
	//func3();
}
/**
 * 招标 /合同
 * @return
 */
function func1(){
	var t1 = $(":input[resCd!='']").filter(".bg_blue");
	$.each(t1,function(i,j){
		if($.trim($(j).val())=="招标文件"){
			func1_1();
			return;
		}
		if($.trim($(j).val())=="合同"){
			func1_2();
			func3();
			return;
		}
	});
}
/**
 * 合同类别(标准/非标准)
 * @return
 */
function func2() {
	var t1 = $(":input[resCd!='']").filter(".bg_blue");
	$.each(t1,function(i,j){
		if($.trim($(j).val())=="标准"){
			func2_1();
		}else if($.trim($(j).val())=="非标准"){
			func2_2();
		}
	});
}
/**
 * 标准
 * @return
 */
function func2_1(){
	var obj = $(".stepBody:last table");
	var trstr = "<input type='hidden' id='templateBean_businessCompany' name='templateBean.businessCompany' value='true' />";
	trstr += "<input type='hidden' id='templateBean_businessGroup' name='templateBean.businessGroup' value='false' />";
	$(obj).append(trstr);
}
/**
 * 非标准
 * @return
 */
function func2_2(){
	var obj = $(".stepBody:last table");
	var trstr = "<input type='hidden' id='templateBean_businessCompany' name='templateBean.businessCompany' value='false' />";
	trstr += "<input type='hidden' id='templateBean_businessGroup' name='templateBean.businessGroup' value='true' />";
	$(obj).append(trstr);
}

/**
 * 合同文本库编号
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func3(){
	var num = $("#contlibno").length;
	if(num<1){//如果没产生过
		var obj = $(".stepBody:last table");
		var trstr = "";
		trstr += '<tr id="contlibno">';
		trstr += '  <td class="td_title">合同文本库编号:</td>';
		trstr += '  <td align="left">';
		trstr += '    <input type="hidden" id="standard" name="templateBean.standard" value=""/>';
		trstr += '    <input type="hidden" id="nonstandard" name="templateBean.nonstandard" value=""/>';
		trstr += '    <input type="text" validate="required" id="contractNo" name="templateBean.contractNo" value="" class="inputBorder" style="width:200px;" />';
		trstr += '    <input type="hidden" id="contractTempletInfoId" name="templateBean.contractTempletInfoId" value="" />';
		trstr += '    <input type="hidden" id="contractTempletHisId" name="templateBean.contractTempletHisId" value="" />';
		trstr += '    <input type="button" id="doCont" name="doCont" value="合同文本库"  class="btn_green" onclick="doContTemplet();"  style="width:90px;"/>';
		trstr += '  </td>';
		trstr += '</tr>';
		$(obj).append(trstr);
		//quickSearch_contractNo();
	}
}
/**
 * 与酒店有关
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func4(){
	var obj = $(".stepBody:last table");
	var trstr = "";
	trstr += '<tr>';
	trstr += '  <td class="td_title">审批权限:</td>';
	trstr += '  <td>';
	trstr += '    <input type="checkbox" name="templateBean.hotel"  class="group"/>与酒店有关';
	trstr += '  </td>';
	trstr += '</tr>';
	$(obj).append(trstr);
}
/**
 * 预算内	隐藏域
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func5_1(){
	var obj = $(".stepBody:last table");
	var items1 = '<input type="hidden" name="templateBean.outFlag" id="outFlag" value="false"/>';
	items1 += '<input type="hidden" name="templateBean.inFlag" id="inFlag" value="true"/>';
	$(obj).append(items1);
}
/**
 * 预算外 添加隐藏
 * @return
 */
function func5_2(){
	var obj = $(".stepBody:last table");
	var items1 = '<input type="hidden" name="templateBean.outFlag" id="outFlag"  value="true"/>';
	items1 += '<input type="hidden" name="templateBean.inFlag" id="inFlag" value="false"/>';
	$(obj).append(items1);
}
function func5(){
	
}
/**
 * 项目名称
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func6(){
	var obj = $(".stepBody:last table");
	var trstr = '';
	trstr += '<tr>';
	trstr += '  <td class="td_title" width="115">项目名称:</td>';
	trstr += '  <td>';
	trstr += '    <input type="hidden" id="isAutosetProject"  value="0"/>';
	trstr += '    <input type="hidden" id="isSearchDesignCon" value="QQ"/>';
	trstr += '    <input type="text" validate="required" name="templateBean.projectName" value="" id="projectName" readonly="readonly" class="inputBorderNoTip projSelect" style="width:200px;" />';
	trstr += '    <input type="hidden" id="projectCd"  name="templateBean.projectCd" value="" />';
	trstr += '  </td>';
	trstr += '</tr>';
	$(obj).append(trstr);
	$('.projSelect').orgSelect({
		orgMuti:false
	});
}
/**
 * 工程名称
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func7(){
	var trstr = '';
	trstr += '<tr>';
	trstr += '  <td class="td_title" width="110">工程名称:</td>';
	trstr += '  <td>';
	trstr += '    <input type="text" validate="required" name="templateBean.engineeringName" value="" class="inputBorder" style="width:200px;" />';
	trstr += '  </td>';
	trstr += '</tr>';
	var obj = $(".stepBody:last table");
	$(obj).append(trstr);
}
/**
 * 评审内容 —— 招标文件(招标文件编号)
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func8(){
	var obj1 = $(".stepBody:last table");
	var trstr = '';
	trstr += '<tr>';
	trstr += '  <td class="td_title" width="110">评审内容:</td>';
	trstr += '  <td class="chkGroup" validate="required">';
	trstr += '    <input type="checkbox" name="templateBean.approveContent1" cssClass="group" />招标文件(招标文件编号：';
	trstr += '    <input type="text" validate="required" name="templateBean.oriBidFileCd" value="" class="inputBorder" style="width:200px;" />)';
	trstr += '  </td>';
	trstr += '</tr>';
	$(obj1).append(trstr);
}
/**
 * 招标计划编号
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func9(){
	var trstr = '';
	trstr +='<tr>';
	trstr +='  <td class="td_title" width="110">招标计划编号:</td>';
	trstr +='  <td align="left">';
	trstr +='  <span class="pd-chill-tip" chilltip="" title="">';
	trstr +='    <input class="inputBorder" style="width:200px;" validate="required" type="text" name="templateBean.ccbpNo" value=""  id="ccbpNo"/>';
	trstr +='  </span>';
	trstr +='    <input type="hidden" name="templateBean.ccbpId" id="ccbpId" value="" />';
	trstr +='    <input type="hidden" id="dataTypeCd" name="dataTypeCd" value="0" />';
	trstr +='    <input type="hidden" style="width:" name="templateBean.ccbpPlanContentDesc" id="ccbpPlanContentDesc" value="" />';
	trstr +='    <div id="ccbpPlanContentDescSpan"></div>';
	trstr +='  </td>';
	trstr +='</tr>';
	var obj1 = $(".stepBody:last table");
	$(obj1).append(trstr);
	//quickSearch_ccbpNo();
}
/**
 * 战略平台编号
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func10(){
	var trstr = '';
	trstr +='<tr>';
	trstr +='  <td class="td_title" width="110">战略计划编号:</td>';
	trstr +='  <td align="left">';
	trstr +='  <span class="pd-chill-tip" chilltip="" title="">';
	trstr +='    <input type="text" class="inputBorder"  validate="required" name="templateBean.bidSerialTypeNo" id="bidSerialTypeNo" value="" style="width:200px;" onblur="leaveBidSerialTypeNo();">';
	trstr +='  </span>';
	trstr +='    <input type="hidden" name="templateBean.bidLedgerId" id="bidLedgerId" value="" >';
	trstr +='    <input type="hidden" name="templateBean.bidSectionName" id="bidSectionName"  value="" >';
	trstr +='    <div id="bidSectionNameSpan"></div>';
	trstr +='  </td>';
	trstr +='</tr>';
	var obj1 = $(".stepBody:last table");
	$(obj1).append(trstr);
	//quickSearch_bidSerialTypeNo();
}
/**
 * 招标范围
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func11(){
	var obj1 = $(".stepBody:last table");
	var trstr = "";
	trstr += '<tr>';
	trstr += '  <td class="td_title" width="110">招标范围:</td>';
	trstr += '  <td>';
	trstr += '    <input type="text" validate="required" name="templateBean.bidRange" class="inputBorder" style="width:200px;" />';
	trstr += '  </td>';
	trstr += '</tr>';
	$(obj1).append(trstr);
}
/**
 * 施工工期
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func12(){
	var obj1 = $(".stepBody:last table");
	var trstr = '';
	trstr += '<tr>';
	trstr += '  <td class="td_title" width="110">施工工期:</td>';
	trstr += '  <td>';
	trstr += '    <input type="text" validate="required" id="fromDate" name="templateBean.fromDate" onfocus="WdatePicker()" onchange="onchange_DesignTotalDay()" class="Wdate inputBorder" style="width:200px;" />';
	trstr += '    至';
	trstr += '    <input type="text" validate="required" id="toDate" name="templateBean.toDate" onfocus="WdatePicker()" onchange="onchange_DesignTotalDay()" class="Wdate inputBorder" style="width:200px;" />';
	trstr += '    共';
	trstr += '    <input type="text" validate="required" id="totalDay" name="templateBean.totalDay" class="inputBorder" style="width:200px;" />';
	trstr += '    天';
	trstr += '  </td>';
	trstr += '</tr>';
	$(obj1).append(trstr);
}
/**
 * 质量要求
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func13(){
	var obj1 = $(".stepBody:last table");
	var trstr = '';
	trstr += '<tr>';
	trstr += '  <td class="td_title" width="110">质量要求:</td>';
	trstr += '  <td>';
	trstr += '    <input type="text" validate="required" name="templateBean.qualityRequirement"  class="inputBorder" />';
	trstr += '  </td>';
	trstr += '</tr>';
	$(obj1).append(trstr);
}
/**
 * 招标模式
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func14(){
	var obj = $(".stepBody:last table");
	var trstr = '';
	trstr += '<tr>';
	trstr += '  <td class="td_title" width="110">招标模式:</td>';
	trstr += '  <td class="chkGroup" validate="required">';
	trstr += '    <input type="checkbox" name="templateBean.inviteBidModel1"  class="group">工程量清单';
	trstr += '    <br/>';
	trstr += '    <input type="checkbox" name="templateBean.inviteBidModel2"  class="group">费率';
	trstr += '    <br/>';
	trstr += '    <input type="checkbox" name="templateBean.inviteBidModel3"  class="group">模拟清单(出图后1个月完成工程量核对)';
	trstr += '  </td>';
	trstr += '</tr>';
	$(obj).append(trstr);
}
/**
 * 计价模式
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func15(){
	var obj1 = $(".stepBody:last table");
	var trstr = '';
	trstr += '<tr>';
	trstr += '  <td class="td_title" width="110">计价模式:</td>';
	trstr += '  <td class="chkGroup" validate="required">';
	trstr += '    <input type="checkbox" name="templateBean.pricingModel1" class="group">总价包干';
	trstr += '    <br/>';
	trstr += '    <input type="checkbox" name="templateBean.pricingModel2" class="group">单价包干（出图一个月完成总价包干）';
	trstr += '    <br/>';
	trstr += '    <input type="checkbox" name="templateBean.pricingModel3" class="group">按实结算';
	trstr += '  </td>';
	trstr += '</tr>';
	$(obj1).append(trstr);	
}
/**
 * 预算金额(元)
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func16(){
	var obj = $(".stepBody:last table");
	var trstr ="";
	trstr += '<tr>';
	trstr += '  <td class="td_title" width="110" colspan="1">预算金额(元):</td>';
	trstr += '  <td>';
	trstr += '    <input type="text" validate="required" name="templateBean.budgetAmount" value="" onblur="formatVal($(this));" class="inputBorder" style="width:200px;" />';
	trstr += '  </td>';
	trstr += '</tr>';
	$(obj).append(trstr);
}
/**
 * 付款方式
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func17(){
	var obj = $(".stepBody:last table");
	var trstr = '';
	trstr += '<tr>';
	trstr += '  <td class="td_title" width="110">付款方式:</td>';
	trstr += '  <td>';
	trstr += '    <input type="text" validate="required" name="templateBean.paymentType" class="inputBorder" style="width:200px;" />';
	trstr += '  </td>';
	trstr += '</tr>';
	$(obj).append(trstr);
}
/**
 * 招标合同文件
 * @param resRightId
 * @param resAuthTypeId
 * @return
 */
function func18(){
	var trstr = '';
	trstr += '<tr otype="attach">';
	trstr += '    <td class="td_title" rowspan="1" width="110">评审附件<br/>(请作为附件上传)</td>';
	trstr += '    <td style="height:40px;" value="${templateBean.bidContractFileId}" id="bidAttachment" resattachname="招标合同文件">招标合同文件';
	trstr += '        <input type="button" id="uploadBtnId" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog(\'招标合同文件\',$(\'#entityTmpId\').val(),\'resCustomFile\',\'bidContractFileId\',event);"/>';
	trstr += '        <input type="hidden" name="templateBean.bidContractFileId" id="bidContractFileId" value="${templateBean.bidContractFileId}"/>';
	trstr += '        <div id="show_bidContractFileId"></div>';
	trstr += '    </td>';
	trstr += '</tr>';

	var obj1 = $(".stepBody:last table");
	$(obj1).append(trstr);
}
