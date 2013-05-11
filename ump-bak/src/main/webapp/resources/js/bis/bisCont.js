isEmpty = function (str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
};
// JS检测中英文字符串长度
function fucCheckLength(strTemp) {  
	var i,sum;
	sum=0;
	for(i=0;i<strTemp.length;i++) {
		if ((strTemp.charCodeAt(i)>=0) && (strTemp.charCodeAt(i)<=255)) {
			sum=sum+1;
		} else {
			sum=sum+2;
		}
	}
	return sum;
}
/**
 * 绑定表格事件
 */
function bindTblEv(tblId){
	$('#'+tblId+' tbody tr td[click2expand=true]').click(function(){
		var $parent = $(this).parent();
		var $read = $($parent).find('.bis-read');
		var $edit = $($parent).find('.bis-edit');
		if($edit.css('display') == 'none'){
			$parent.addClass('click');
			$read.hide();
			$edit.show();
			// $parent.find("#greyImg").attr('src',_ctx+'/resources/images/common/down_grey.gif');
			// 将其他显示的detail tr隐藏
			$parent.siblings('.click').find('[click2expand=true]:eq(1)').trigger('click');
		}else{
			$parent.removeClass('click');
			$edit.each(function(i, dom) {
				var newVal = $(dom).children().val();
				$(dom).siblings().filter(".pd-chill-tip").attr("title", newVal);
				$(dom).siblings().text(newVal);
			});
			$read.show();
			$edit.hide();
			// $parent.find("#greyImg").attr('src',_ctx+'/resources/images/common/right_grey.gif');
		}
	});
	
	$('#'+tblId+' tbody tr td div.bis-edit :input').click(function(e){
		// 阻止事件向上冒泡
		e.stopPropagation(); 
	});
}

function bindChargeEv() {
	$("#cost-nav li").click(function() {
		changeChargeType(this.value, $(this).text());
	});
}
function unbindChargeEv() {
	$("#cost-nav li").unbind("click");
}
/**
 * 绑定搜索框事件
 */
function bindSearchEv() {
	$("input").filter(".search").each(function(i,dom){
		// var searchImg = _ctx+'/resources/images/common/search_12.png';
		// var searchImg = _ctx+'/resources/images/desk/wab/search.gif';
		// , border: "#999 1px solid", background: "#fff url("+searchImg+")
		// no-repeat right"
		// $(dom).attr("searchtext")
		if($(dom).val().trim() == '') {
			$(dom).val($(dom).attr("searchtext"));
			$(dom).css({color: "#909090"});
		}
	});
	
	$("input").filter(".search").click(function(){
		if($(this).val() == $(this).attr("searchtext")) {
			$(this).val('');
			$(this).css({color:"#000000"});
		}
	});
	
	$("input").filter(".search").blur(function(){
		if($(this).val().trim() == '') {
			$(this).val($(this).attr("searchtext"));
			$(this).css({color: "#909090"});
		} else {
			$(this).css({color:"#000000"});
		}
	});
}
/**
 * 绑定金额输入框事件
 */
function bindMoneyEv() {
	$("input").filter(".money").each(function(i,dom){
		// $(dom).css({"text-align": "right"});
		if($(dom).val().trim() == '') {
			$(dom).val('0.00');
			$(dom).css({color: "#909090"});
		} else {
			formatVal($(dom));
			$(dom).val($(dom).val().replace("-,","-"));
		}
	});
	
	$("input").filter(".money").click(function(){
		if($(this).val() == '0.00') {
			$(this).val('');
			$(this).css({color:"#000000"});
		}
	});
	
	$("input").filter(".money").blur(function(){
		clearMoney(this);
		formatVal($(this));
		$(this).val($(this).val().replace("-,","-"));
		if($(this).val().trim() == '') {
			$(this).val('0.00');
			$(this).css({color: "#909090"});
		} else {
			$(this).css({color:"#000000"});
		}
	});
}
function clearMoney(dom) {
	$(dom).val($(dom).val().replace(/,|\s/g,''));
}
/**
 * 清除金额格式
 */
function clearMoneySt() {
	$("input").filter(".money").each(function(i,dom){
		if($(dom).val() == '0.00') {
			$(dom).val('');
		}
		clearMoney(dom);
	});
}
/**
 * 清除金额格式
 */
function clearMoneySt2() {
	$("input[name$='money']").each(function(i,dom){
		if($(dom).val() == '待计算') {
			$(dom).val('');
		}
		clearMoney(dom);
	});
	$("input[name$='turnover']").each(function(i,dom){
		clearMoney(dom);
	});
}
/**
 * 清除搜索框
 */
function clearSearchtext() {
	$("input").filter(".search").each(function(i,dom){
		if($(dom).val().trim() == $(dom).attr("searchtext")) {
			$(dom).val('');
		}
	});
// $(".wolegequ").val('');
}
/**
 * 改变业态
 */
function changeLayout(dom) {
	if(dom.id=='li_4') {
		window.location = _ctx+"/bis/bis-cont.action";
	} else if(dom.id=='li_2') {
		window.location = _ctx+"/bis/bis-project-report.action";
	} else if(dom.id=='li_1') {
		window.location = _ctx+"/bis/bis-manage-report.action";
	} else if(dom.id=='li_3') {
		window.location = _ctx+"/bis/bis-fact.action";
	}
}
function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	TB_showMaskLayer("正在搜索...");
	$("#mainFormSearch").ajaxSubmit(function(result) {
		$("#contContent").html(result);
		TB_removeMaskLayer();
	});
}
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
/**
 * 新增
 */
function addBisCont(bisTenantId){
	if(isEmpty(bisTenantId)) {
		bisTenantId = '';
	}
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='selectTypeDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 330,
		height : 270,
		title : "合同类型选择",
		afterShow : function() {
			var url = _ctx+"/bis/bis-cont!typeSelect.action";
			$.post(url, {bisTenantId:bisTenantId,bisProjectId:$("#bisProjectId").val()}, function(result) {
				$("#selectTypeDiv").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				var bigTypeId = $('#bigTypeId').val();
				var smallTypeId = $('#smallTypeId').val();
				var bisTenantId = $('#bisTenantId').val();
				var bisProjectId = $('#bisProjectHideId').val();
				if(isEmpty(bigTypeId) || isEmpty(smallTypeId)){
					alert('请选择完整!');
					return false;
				}
				var url;
				if(isEmpty(bisTenantId)) {
					url = _ctx+"/bis/bis-cont!input.action?contBigTypeCd="+bigTypeId+"&contSmallTypeCd="+smallTypeId+"&bisProjectId="+bisProjectId;
				} else {
					url = _ctx+"/bis/bis-cont!addTenantCont.action?contBigTypeCd="+bigTypeId+"&contSmallTypeCd="+smallTypeId+"&bisTenantId="+bisTenantId;
				}
				openContTab(url);
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["退出",'cancel']]
	});
}
/**
 * 合同明细
 */
function goDetail(id) {
	var url = _ctx+"/bis/bis-cont!input.action?id="+id;
	openContTab(url);
}

/**
 * 搜索
 */
function submitSearch() {
	if($("#bisProjectName").hasClass("error")) {
		$("#bisProjectName").removeClass("error");
	}
	$("#pageNo").val("1");
	TB_showMaskLayer("正在搜索...");
	$("#mainFormSearch").ajaxSubmit(function(result) {
		$("#contContent").html(result); 
		TB_removeMaskLayer();
		
		var notSearchStatus = false;	// 是否不搜索近期合同状态。如果搜索了商铺或者商家，就自动不搜索近期合同状态
		if(""!=$("#filter_LIKES_storeNo").val() || ""!=$("#bisShopName").val()){
			notSearchStatus = true;
		}
		if(notSearchStatus){
			if("10"==$("#mainFormSearch select[name='filter_EQ_statusCd']").find("option:selected").val()){
				$("#mainFormSearch select[name='filter_EQ_statusCd']").get(0).selectedIndex=0;
			}
		}
	});
}
/**
 * 导出（根据搜索条件）
 */
function exportExcel() {
	
	if(isEmpty($("#filter_EQ_contBigTypeCd").val())) {
		alert("导出时需先选择合同大类");
		$("#filter_EQ_contBigTypeCd").focus();
		return false;
	}
	$("#mainFormSearch").attr("action", _ctx+"/bis/bis-cont!exportExcel.action");
	$("#mainFormSearch").submit();
	$("#mainFormSearch").attr("action", _ctx+"/bis/bis-cont!list.action");
}

function importExcel() {
	var url = _ctx+"/bis/bis-cont!importExcel.action";
	$.post(url, function(result) {
		alert("success");
	});
}

/**
 * 导出合同条款
 */
function exportContent() {
	location.href = _ctx+"/bis/bis-cont!exportContent.action?bisProjectId="+$("#bisProjectId").val();
}
function openImportPanel() {
	$("#contImportPanel").toggle();
}
function closeImportPanel() {
	$("#contImportPanel").toggle();
}
/**
 * 导入合同条款
 */
function importContent() {
	
	if(isEmpty($("#importFile").val())) {
		alert("请先选择要导入的文件");
		$("#importFile").focus();
		return false;
	}
	TB_showMaskLayer("正在导入...");
	$("#importForm").ajaxSubmit(function(result){
		TB_removeMaskLayer();
		var msg = result.split(",");
		if(msg[1] == "success") {
		    alert("导入成功：共更新"+msg[2]+"条记录，耗时"+msg[3]+"秒");
		    $("#importFile").val("");
		} else {
			alert("导入失败："+msg[2]);
		}
		closeImportPanel();
	});
}

/**
 * 加载楼层
 */
function loadFloor() {
	var bisProjectId = $("#bisProjectId").val();
	var floorType = $("#filter_EQ_contBigTypeCd").val();
	if(isEmpty(bisProjectId) || isEmpty(floorType)) {
		return false;
	}
	if(floorType == "1" || floorType == "2") {
		$("#tdFloorText").show();
		$("#tdFloorCont").show();
		$("#tdHidden").hide();
	} else if(floorType == "3") {
		$("#tdFloorText").hide();
		$("#tdFloorCont").hide();
		$("#tdHidden").show();
	}
	
	var subSele = $("#bisFloorId");
	subSele.empty();
	subSele.append('<option value="">--选择--</option>');
	
	var url = _ctx+"/bis/bis-project!getFloorNo.action";
	$.post(url,{bisProjectId:bisProjectId, floorType:floorType},function(data){
		var data = eval('('+data+')');
		$.each(data,function(i,n){
			var option = '<option value="'+i+'">'+n+'</option>';
			subSele.append(option);
		});
	});
}
/**
 * 高级搜索
 */
function searchSenior() {
	ymPrompt.win( {
		icoCls : "",
		autoClose:false,
		message : "<div id='searchSeniorDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 500,
		height : 180,
		title : "高级搜索",
		closeBtn:false,
		afterShow : function() {
			var url = _ctx+"/bis/bis-cont!toSearchSenior.action";
			var data = {
				filter_LIKES_contName:$("#filter_LIKES_contName").val(),
				filter_LIKES_contNo:$("#filter_LIKES_contNo").val(),
				contBigTypeCd:$("#contBigTypeCd").val(),
				contSmallTypeCd:$("#contSmallTypeCd").val(),
				filter_GED_contStartDate:$("#filter_GED_contStartDate").val(),
				filter_LTD_contStartDate:$("#filter_LTD_contStartDate").val(),
				filter_GED_contEndDate:$("#filter_GED_contEndDate").val(),
				filter_LTD_contEndDate:$("#filter_LTD_contEndDate").val(),
				filter_EQ_statusCd:$("#filter_EQ_statusCd").val()
			};
			$.post(url, data, function(result) {
				$("#searchSeniorDiv").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				$("#filter_LIKES_contName").val($("#search_LIKES_contName").val());
				$("#filter_LIKES_contNo").val($("#search_LIKES_contNo").val());
				$("#contBigTypeCd").val($("#search_EQ_contBigTypeCd").val());
				$("#contSmallTypeCd").val($("#search_EQ_contSmallTypeCd").val());
				$("#filter_GED_contStartDate").val($("#search_GED_contStartDate").val());
				$("#filter_LTD_contStartDate").val($("#search_LTD_contStartDate").val());
				$("#filter_GED_contEndDate").val($("#search_GED_contEndDate").val());
				$("#filter_LTD_contEndDate").val($("#search_LTD_contEndDate").val());
				$("#filter_EQ_statusCd").val($("#search_EQ_statusCd").val());
				
				$("#mainFormSearch").ajaxSubmit(function(result) {
					$("#contContent").html(result); 
				});
			}
			ymPrompt.close();
		},
		btn:[["搜索",'ok'],["返回",'cancel']]
	});
}
/**
 * 清空搜索条件
 */
function clearSearch() {
	$("#bisProjectId").val("");
	$("#bisProjectName").val("");
// $("#bisStoreIds").val("");
// $("#bisStoreNos").val("");
	$("#bisShopId").val("");
	$("#bisShopName").val("");
	$("#filter_LIKES_contNo").val("");
	$("#filter_EQ_contBigTypeCd").val("");
	$("#filter_EQ_contSmallTypeCd").val("");
	$("#filter_GED_contStartDate").val("");
	$("#filter_LTD_contStartDate").val("");
	$("#filter_GED_contEndDate").val("");
	$("#filter_LTD_contEndDate").val("");
	$("#filter_EQ_statusCd").val("");
	$("#filter_LIKES_storeNo").val("");
	$("#bisFloorId").val("");
	
	// getSmallType('contBigTypeCd','contSmallTypeCd');
}
/**
 * 检查合同名是否存在
 */
function checkContName() {
	var bisProjectId = $("#bisProjectId").val();
	var contName = $("#contName").val();
	
	if(bisProjectId && contName) {
		var url = _ctx+"/bis/bis-cont!checkContName.action";
		$.post(url, {bisProjectId:bisProjectId ,contName:contName, id: $("#bisContId").val()}, function(result) {
			if(result=='exists') {
				alert("合同名称已存在");
				$("#contName").val('');
				return false;
			}
		});
	}
}

function showContentReference() {

// var msg = "<div id='contentReference' style='padding: 10px; font-size:14px;
// color:#5A5A5A; line-height:26px;'>";
//	
// msg += "<font color='red' style='font-weight:bolder;
// font-size:16px;'>重要说明</font><br>";
// msg += "<font
// color='red'>&nbsp;&nbsp;&nbsp;&nbsp;“关键合同条款”中，应主要列出合同中明确规定的几项费用，其中包含：合作方式、付款方式、公摊方案及其他特殊条款，目的为说明该合同的应收/应付款项如何产生。<br><br></font>";
//	
// msg += "<font style='font-weight:bolder; font-size:16px;'>例1<br></font>";
// msg += "<div style='padding-left: 8px;'>1、固定月租金<br>";
// msg +=
// "2、第一个租赁年度月租金标准为每平方米人民币____元/月<br>&nbsp;&nbsp;&nbsp;第二个租赁年度月租金标准为每平方米人民币____元/月<br>&nbsp;&nbsp;&nbsp;第三个租赁年度月租金标准为每平方米人民币____元/月<br>&nbsp;&nbsp;&nbsp;……<br>";
// msg += "3、综合管理费的标准为每平方米____元/月。<br>";
// msg += "4、约定的第一个租赁年度的
// 个月按正常租金标准计算的租金及____个月综合管理费的款项（共计人民币____元）作为履约保证金。<br><br></div>";
//	
// msg += "<font style='font-weight:bolder; font-size:16px;'>例2<br></font>";
// msg += "<div style='padding-left: 8px;'>1、扣点租金<br>";
// msg +=
// "2、第一个租赁年度：年抽成租金按租赁场所年营业额的____%计算；年保底租金为人民币____元<br>&nbsp;&nbsp;&nbsp;第二个租赁年度：年抽成租金按租赁场所年营业额的____%计算；年保底租金为人民币____元<br>&nbsp;&nbsp;&nbsp;第三个租赁年度：年抽成租金按租赁场所年营业额的____%计算；年保底租金为人民币____元<br>&nbsp;&nbsp;&nbsp;……<br>";
// msg += "3、综合管理费的标准为每平方米____元/月。<br>";
// msg += "4、履约保证金共计人民币____元。<br><br></div>";
//	
// msg += "<font style='font-weight:bolder; font-size:16px;'>例3<br></font>";
// msg += "<div style='padding-left: 8px;'>1、双方约定租赁期限为3个租赁年度；<br>";
// msg += "2、租赁期限内，乙方应向甲方支付的租赁场所的租金按 “月抽成租金”与“月保底租金”二者取其高的方式计算支付；<br>";
// msg +=
// "3、租金递增：双方同意自第五个租赁年度起（含第五个租赁年度），月租金在前一个租赁年度月租金的基础上逐年递增5%，直至合同租期结束；<br>";
// msg += "4、针对押金、保证金、违约金、装修费用的特殊条款。</div>";
//	
// msg += "</div>";
	
	ymPrompt.win( {
		icoCls : "",
		autoClose:true,
		allowSelect:true,
		allowRightMenu:true,
		message : "<div id='contentRefDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width  : 490,
		height : 410,
		title : "合同条款参考",
		closeBtn:true,
		afterShow : function() {
			var url = _ctx+"/bis/bis-cont!showContentRef.action";
			$.post(url, {contSmallTypeCd:$("#contSmallTypeCd").val()}, function(result) {
				$("#contentRefDiv").html(result);
			});
		},
		winPos:[($("#resultDiv").width()-490)/2,$("#resultDiv").height()-450],
		btn:[["关闭",'ok']]
	});
}
var validNode=null;
function validateForm() {
	
// $.ajaxSettings.async = false;
// checkContName();
// $.ajaxSettings.async = true;
	validNode=null;
	var pdv = new Validate("processForm");	
	//!pdv.validate()
	if (!pdv.validate()) {
		// alert("请填写完整");
		var dom = $("#processForm :input,textarea").filter("[validate=required]").filter("[value='']").eq(0);
		validNode=dom;
		var altname = $(dom).attr("altname");
		if(isEmpty(altname)) {		
			if($(dom).parent().attr("nodeName").toLowerCase()!="td"){				
				altname = $(dom).parent().parent().prev().text().split("：")[0];
			}else{
				
				altname = $(dom).parent().prev().text().split("：")[0];
			}
			
		}
		if(!isEmpty(altname)) {
			if(dom){
				$(dom).focus();		
				}else{
				// alert($("#processForm
				// :input,textarea").filter("[validate=required]").filter("[value='']").eq(0).attr("name"));
				 $("#processForm :input,textarea").filter("[validate=required]").filter("[value='']").eq(0).focus();
				}		
			if(altname.indexOf("关键合同条款")>-1){
				
				altname="关键合同条款";
			}
			alert(altname+"不能为空！");
			return false;
		}
		// if(isEmpty($("#contContentId").attr("value"))) {
		// $("#bisContAttachIdA").focus();
		// alert("请上传合同条款附件");
		// return false;
		// }
	}
	var errorflag=false;
	$("#processForm :input,textarea").filter("[maxlength]").each(function(i,dom){
		if(fucCheckLength($(dom).val()) > $(dom).attr("maxlength")) {
			var alt = $(dom).attr("altname");
			if(isEmpty(alt)) {
				if($(dom).parent().attr("nodeName").toLowerCase()!="td"){				
					alt = $(dom).parent().parent().prev().text().split("：")[0];
				}else{
					
					alt = $(dom).parent().prev().text().split("：")[0];
				}
				if(alt.indexOf("关键合同条款")>-1){
					
					alt="关键合同条款";
				}
			}
			alert(alt+"过长");
			errorflag = true;
			return false;
		}
	});
// $("#processForm :input").filter(".money").each(function(i,dom){
//		
// var money = $(dom).val();
// var integer = money.split(".")[0];
// var maxlength = 8;
// if(!isEmpty($(dom).attr("maxlength")) && $(dom).attr("maxlength") != -1) {
// maxlength = $(dom).attr("maxlength");
// }
// if(integer.length > maxlength) {
// var alt = $(dom).attr("altname");
// if(isEmpty(alt)) {
// alt = $(dom).parent().prev().text();
// }
// alert(alt+"过长");
// errorflag = true;
// return false;
// }
// });
	if(errorflag) {
		return false;
	}

	return true;
}

function saveValidate() {
	
	var required_fields = '';
	$("#processForm :input,textarea").filter(".must").filter("[value='']").each(function(i,dom) {
		
		var altname = $(dom).attr("altname");
		if(isEmpty(altname)) {
			altname = $(dom).parent().prev().text().split("：")[0];
		}
		required_fields = required_fields + "," + altname;
	});
	if(required_fields.length > 0) {
		required_fields = required_fields.substring(1, required_fields.length);
	}
	if(!isEmpty(required_fields)) {
		alert(required_fields+" 为必填项");
		$("#processForm :input,textarea").filter(".must").filter("[value='']").eq(0).focus();
		return false;
	}
	return true;
}

function reload(id) {
	TB_showMaskLayer("重新加载...");
	window.location = _ctx+"/bis/bis-cont!input.action?id="+id;
	TB_removeMaskLayer();
}
/**
 * 保存
 */
function doSave() {
	
	TB_showMaskLayer("正在保存...");
	clearMoneySt();
	clearSearchtext();
	//saveValidate()
	if(!validateForm()) {
		TB_removeMaskLayer();
		return false;
	}
	
	$("#processForm").attr("action", "bis-cont!save.action").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		var msg = result.split('&');
		if(msg[0]=='0') {
			ymPrompt.errorInfo({message:'系统错误',title:"提示",closeBtn:false,width:220,height:180,winPos:[($("#resultDiv").width()-220)/2,150]});
		} else if(msg[0]=='2') {
			loadContInfo(msg[1]);
		} else {
			ymPrompt.succeedInfo({message:'操作成功',title:"提示",closeBtn:false,width:220,height:180,winPos:[($("#resultDiv").width()-220)/2,150],handler:function(){
				reload(msg[1]);
				// window.location =
				// _ctx+"/bis/bis-cont!input.action?id="+msg[1];
			}});
		}
	});
}
/**
 * 提交
 */
function doSubmit() {
	
	$("select").filter(".inputBorder").attr("disabled","");
	clearMoneySt();
	clearSearchtext();
	
	if(!validateForm()) {
		if(validNode){
	
			$(validNode).focus();
		}
		return false;
	}
	
	ymPrompt.confirmInfo({message:'提交后将无法修改，确认提交？',title:'提交',width:280,height:180,winPos:[($("#resultDiv").width()-280)/2,150],handler:function (tp){
		if (tp=='ok'){
			TB_showMaskLayer("正在提交...");
			$("#processForm").attr("action", "bis-cont!submit.action").ajaxSubmit(function(result) {
				TB_removeMaskLayer();
				var msg = result.split('&');
				if(msg[0]=='0') {
					ymPrompt.errorInfo({message:'系统错误',title:"提示",closeBtn:false,width:220,height:180,winPos:[($("#resultDiv").width()-220)/2,150]});
				} else if(msg[0]=='2') {
					loadContInfo(msg[1]);
				}else {
					ymPrompt.succeedInfo({message:'操作成功',title:"提示",closeBtn:false,width:220,height:180,winPos:[($("#resultDiv").width()-220)/2,150],handler:function(){
						reload(msg[1]);
						// window.location =
						// _ctx+"/bis/bis-cont!input.action?id="+msg[1];
					}});
				}
			});
		}
	}});
	
}
/**
 * 提示合同冲突信息
 */
function loadContInfo(id) {
	ymPrompt.errorInfo({
		message : "<div id='contInfoDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		title:"保存失败：合同冲突",
		closeBtn:false,
		width:390,
		height:280,
		afterShow : function() {
			var url = _ctx + "/bis/bis-cont!loadContInfo.action";
			$.post(url, {id:id}, function(result) {
				$("#contInfoDiv").html(result);
			});
		},
		btn:[["关闭",'close']]
	});
}
/**
 * 删除
 */
function doDelete() {
	var id = $("#bisContId").val();
	if(isEmpty(id)) {
		return;
	}
	ymPrompt.confirmInfo({message:'确认删除？',title:'删除',width:200,height:150,handler:function (tp){
		if (tp=='ok'){
			$.post(_ctx+"/bis/bis-cont!delete.action",{id:id},function(result) {
				ymPrompt.succeedInfo({message:'操作成功',title:"提示",closeBtn:false,width:220,height:180,winPos:[($("#resultDiv").width()-220)/2,$("#resultDiv").height()-300],handler:function(){
					closeWindow('bisContMenu');
				}});
			});
		}
	}});
}
/**
 * 失效
 */
function doInvalid() {
	var id = $("#bisContId").val();
	if(isEmpty(id)) {
		return;
	}
	ymPrompt.win( {
		icoCls : "",
		autoClose:false,
		message : "<div id='invalidDiv' style='padding: 20px; 8px;'>失效日期：<input type='text' id='failDate' class='Wdate' onfocus='WdatePicker()' ></div>",
		width : 260,
		height : 130,
		title : "合同失效",
		winPos:[($("#resultDiv").width()-260)/2,150],
		// afterShow : function() {},
		handler : function(btn){
			if(btn=='ok'){
				var failDate = $("#failDate").val();
				$.post(_ctx+"/bis/bis-cont!invalid.action",{id:id,failDate:failDate},function(result) {
					if(result == "success") {
						alert("操作成功");
					} else {
						alert("操作失败："+result.split(",")[1]);
					}
				});
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["退出",'close']]
	});
}
function cancelInvalid() {
	var id = $("#bisContId").val();
	if(isEmpty(id)) {
		return;
	}
	$.post(_ctx+"/bis/bis-cont!cancelInvalid.action",{id:id},function(result) {
		if(result == "success") {
			alert("操作成功");
		} else {
			alert("操作失败："+result.split(",")[1]);
		}
	});
}
/**
 * 返回
 */
function goBack() {
	var isModified= eval($("#isModified").val());
	if (isModified){
		alert("内容已更改，请先保存");
		return false;
	}
	closeWindow('bisContMenu');
}

/**
 * 返回(NOW)
 */
function goBackHis() {
	closeWindow('bisContHis');
}
/**
 * 返回(NOW)
 */
function goBackHisVersion() {
	closeWindow('bisContHisVersion');
}
/**
 * 返回(NOW)
 */
function goBackCompare() {
	closeWindow('bisContCompare');
	// parent.TabUtils.showTab("bisContMenu","合同管理",url,true);
	// submitSearch();
}
/**
 * 合同审核
 */
function doApproveCont() {
	var bisContId = $('#bisContId').val();
	ymPrompt.confirmInfo({message:'确认审核？',title:'审核',width:200,height:180,winPos:[($("#resultDiv").width()-220)/2,150],handler:function (tp){
		if (tp=='ok'){
			$.post(_ctx+"/bis/bis-cont!approveCont.action", {id: bisContId,statusCd:$('#statusCd').val()}, function(result) {
				if(result=="error") {
					alert("失败,系统错误!");
				} else {
					ymPrompt.succeedInfo({message:'操作成功',title:"提示",closeBtn:false,width:220,height:180,winPos:[($("#resultDiv").width()-220)/2,150],handler:function(){
						reload(bisContId);
						// window.location =
						// _ctx+"/bis/bis-cont!input.action?id="+bisContId;
					}});
				}
			});
		}
	}});
}
/**
 * 取消变更
 * 
 * @Deprecated
 */
// function doCancelChange() {
// var bisContId = $('#bisContId').val();
// ymPrompt.confirmInfo({message:'确认取消变更？',title:'取消变更',width:200,height:180,handler:function
// (tp){
// if (tp=='ok'){
// $.post(_ctx+"/bis/bis-cont!cancelChange.action", {id:bisContId},
// function(result) {
// if(result=="error") {
// alert("失败,系统错误!");
// } else {
// ymPrompt.succeedInfo({message:'操作成功',title:"提示",closeBtn:false,width:220,height:180,handler:function(){
// reload(bisContId);
// //window.location = _ctx+"/bis/bis-cont!input.action?id="+bisContId;
// }});
// }
// });
// }
// }});
// }
/**
 * 合同驳回
 */
function doBackCont() {
	var bisContId = $('#bisContId').val();
	var status = $('#statusCd').val();
	var msg;
	var wid;
	if(status == '2' || status == '3') {
		msg = "确认驳回？";
		wid = 200;
	} else {
		msg = "驳回将回到变更前，确认驳回？";
		wid = 280;
	}
	ymPrompt.confirmInfo({message:msg,title:'驳回',width:wid,height:180,winPos:[($("#resultDiv").width()-wid)/2,150],handler:function (tp){
		if (tp=='ok'){
			$.post(_ctx+"/bis/bis-cont!backCont.action", {id:bisContId,statusCd:status}, function(result) {
				if(result=="error") {
					alert("失败,系统错误!");
				} else {
					ymPrompt.succeedInfo({message:'操作成功',title:"提示",closeBtn:false,width:220,height:180,winPos:[($("#resultDiv").width()-220)/2,150],handler:function(){
						reload(bisContId);
						// window.location =
						// _ctx+"/bis/bis-cont!input.action?id="+bisContId;
					}});
				}
			});
		}
	}});
}
/**
 * 审核变更合同(NOW)
 */
function doApproveChange() {
	var bisContId = $('#bisContId').val();
	ymPrompt.confirmInfo({message:'确认审核？',title:'审核',width:200,height:180,winPos:[($("#resultDiv").width()-200)/2,$("#resultDiv").height()-300],handler:function (tp){
		if (tp=='ok'){
			$.post(_ctx+"/bis/bis-cont!approveChange.action", {id: bisContId}, function(result) {
				if(result=="error") {
					alert("失败,系统错误!");
				} else {
					ymPrompt.succeedInfo({message:'操作成功',title:"提示",closeBtn:false,width:220,height:180,winPos:[($("#resultDiv").width()-220)/2,$("#resultDiv").height()-300],handler:function(){
						goDetail(bisContId);
						closeWindow('bisContHis');
					}});
				}
			});
		}
	}});
}
/**
 * 驳回变更合同(NOW)
 */
function doBackChange() {
	var bisContId = $('#bisContId').val();
	ymPrompt.confirmInfo({message:'确认驳回？',title:'驳回',width:200,height:180,winPos:[($("#resultDiv").width()-200)/2,$("#resultDiv").height()-300],handler:function (tp){
		if (tp=='ok'){
			$.post(_ctx+"/bis/bis-cont!backChange.action", {id:bisContId}, function(result) {
				if(result=="error") {
					alert("失败,系统错误!");
				} else {
					ymPrompt.succeedInfo({message:'操作成功',title:"提示",closeBtn:false,width:220,height:180,winPos:[($("#resultDiv").width()-220)/2,$("#resultDiv").height()-300],handler:function(){
						var url = _ctx+"/bis/bis-cont!input.action?id="+bisContId;
						openContTab(url);
						closeWindow('bisContHis');
					}});
				}
			});
		}
	}});
}
/**
 * 审核后变更(NOW)
 */
function doChangeCont(){
	var id = $('#bisContId').val();
	if(isEmpty(id)) {
		return false;
	}
	ymPrompt.confirmInfo({message:'确认变更？',title:'变更',width:200,height:150,winPos:[($("#resultDiv").width()-200)/2,150],handler:function (tp){
		if (tp=='ok'){
// reload(msg[1]);
			window.location = _ctx+"/bis/bis-cont!input.action?id="+id+"&changeBl=true";
		}
	}});
}
/**
 * 审核后变更
 * 
 * @Deprecated
 */
// function doChange(){
// var id = $('#bisContId').val();
// if(isEmpty(id)) {
// return false;
// }
// ymPrompt.confirmInfo({message:'确认变更？',title:'变更',width:200,height:150,handler:function
// (tp){
// if (tp=='ok'){
// var data = {
// id : id,
// shopId : $("#hiddenShopId").val(),
// storeIds : $("#hiddenStoreIds").val(),
// flatIds : $("#hiddenFlatIds").val()
// };
// $.post(_ctx+"/bis/bis-cont!changeCont.action", data, function(result) {
// if(result=="error") {
// alert("失败,系统错误!");
// } else {
// ymPrompt.succeedInfo({message:'操作成功',title:"提示",closeBtn:false,width:220,height:180,handler:function(){
// reload(id);
// //window.location = _ctx+"/bis/bis-cont!input.action?id="+id;
// }});
// }
// });
// }
// }});
// }
/**
 * 查看变更前历史(NOW)
 */
function doShowHis() {
	var id = $('#bisContId').val();
	if(isEmpty(id)) {
		return false;
	}
	url=_ctx+"/bis/bis-cont!showHis.action?id="+id;
	openWindow("bisContHis", "变更合同", url);
}
/**
 * 回到变更时
 */
function doBackHis() {
	closeWindow('bisContHis');
}
/**
 * 查看历史版本
 */
function doShowHisVersion() {
	var id = $('#bisContId').val();
	if(isEmpty(id)) {
		return false;
	}
	
	ymPrompt.win( {
		icoCls : "",
		autoClose:false,
		message : "<div id='hislistDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 580,
		height : 350,
		winPos:[($("#resultDiv").width()-580)/2,150],
		title : "合同变更历史",
		afterShow : function() {
			var url = _ctx+"/bis/bis-cont!showHisList.action";
			$.post(url, {id:id}, function(result) {
				$("#hislistDiv").html(result);
			});
		},
		handler : function(btn){
			if(btn=='view'){
				var id = $("#bisContHisId").val();
				var url = _ctx+"/bis/bis-cont!showHisVersion.action?id="+id;
				openWindow("bisContHisVersion", "变更历史", url);
				ymPrompt.close();
			} else if(btn=='compare'){
				var compareIds = $("#compareIds").val();
				if(isEmpty(compareIds)){
					alert("至少选择两项");
				} else {
					var url = _ctx+"/bis/bis-cont!compareCont.action?compareIds="+compareIds;
					openWindow("bisContCompare", "合同对比", url);
					// ymPrompt.close();
				}
			} else {
				ymPrompt.close();
			}
		},
		btn:[["关闭",'close']]
	});
	
}
/**
 * 显示资信资料框
 */
function addContCredit() {
	$("#btnBisContCredit").hide();
	$("#trBisContCredit").show();
	addCreditItem();
	$("#sequenceNo_0").focus();
}
/**
 * 显示补充合同框
 */
function addContAdditional() {
	$("#btnBisContAddi").hide();
	$("#trBisContAddi").show();
	addItemAdd();
	$("#contAdditionalName_0").focus();
}
/**
 * 显示成功信息
 */
// function showSuccesedMsg(id,text){
// $("#"+id).text(text);
// setTimeout(function(){$("#"+id).text("");}, 10000);
// }
/**
 * 联动搜索合同小类
 */
function getSmallType(parentId,childId,emptyOption){
	
	var bigType = $('#'+parentId).val();
	var subSele = $('#'+childId);
	subSele.empty();
	if(emptyOption) {
		subSele.append('<option value="">--选择--</option>');
	}
	var url = _ctx+"/bis/bis-cont!getSmallType.action";
	$.post(url,{bigType:bigType},function(data){
		var data = eval('('+data+')');
		$.each(data,function(i,n){
			var option = '<option value="'+i+'">'+n+'</option>';
			subSele.append(option);
		});
		// _hs.val(_hs.attr('tempValue'));
	});
};
/**
 * 联动搜索经销商
 */
function getShopConn(){
	var shopId = $('#bisShopId').val();
	var subSele = $('#bisShopConnId');
	subSele.empty();
	
	var url = _ctx+"/bis/bis-shop!getMapShopConn.action";
	$.post(url,{id:shopId},function(data){
		var data = eval('('+data+')');
		var len = 0;
		if(Boolean(data)){
			for(i in data)len++;
		}
		if(len != 1) {
			subSele.append('<option value="">--选择--</option>');
		}
		$.each(data,function(i,n){
			var option = '<option value="'+i+'">'+n+'</option>';
			subSele.append(option);
		});
		// _hs.val(_hs.attr('tempValue'));
	});
};

function addRentOneItem() {
	$("#tbRentOneItem").find(".trRentOne").eq(0).show();
	$("#tbRentOneItem").find(".trRentOne").eq(0).removeClass("trRentOne");
}
function delRentOneItem(dom) {
	$(dom).parent().parent().addClass("trRentOne");
	$(dom).parent().parent().find("input").val("");
	$(dom).parent().parent().hide();
}
function addRentTwoItem() {
	$("#tbRentTwoItem").find(".trRentTwo").eq(0).show();
	$("#tbRentTwoItem").find(".trRentTwo").eq(0).removeClass("trRentTwo");
}
function delRentTwoItem(dom) {
	$(dom).parent().parent().addClass("trRentTwo");
	$(dom).parent().parent().find("input").val("");
	$(dom).parent().parent().hide();
}
/**
 * 查看附件
 */
function showAttachment(entityId){
	ymPrompt.win({
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=bisCont&filterType=image|office&onlyShow=true",
		width:500,
		height:300,
		title: '附件查看',
		afterShow:function(){},
		iframe:true,
		btn:[["完成",'close']]
	});
}
/**
 * 管理附件
 */
function openAttachment(title,entityId,domId,attachFlgId){
	ymPrompt.win({
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=bisCont&filterType=image|office",
		width:500,
		height:300,
		title:title,
		iframe:true,
		afterShow : function() {},
		handler : function(btn) {
			if(btn=='close') {
				showAttach(entityId,domId,attachFlgId);
			}
		},
		btn:[["完成",'close']]
	 });
}
/**
 * 判断是否有附件
 */
function showAttach(entityId,domId,attachFlgId) {
	$.post(_ctx+"/app/app-attachment!hasAttachment.action",
		{bizEntityId:entityId,bizModuleCd:'bisCont'},
		function(result){
			if(result == "true") {
				$("#"+domId).attr("src",_ctx+"/resources/images/common/atta_y.gif");
				if($("#"+attachFlgId).val()=='0' && domId.indexOf('bisMustAttachId_') == -1) {
					$("#isModified").val(true);
				}
				$("#"+attachFlgId).val("1");
				$("#"+domId).parent().parent("[validate]").attr("value","1");
			} else {
				$("#"+domId).attr("src",_ctx+"/resources/images/common/atta.gif");
				if($("#"+attachFlgId).val()=='1' && domId.indexOf('bisMustAttachId_') == -1) {
					$("#isModified").val(true);
				}
				$("#"+attachFlgId).val("0");
				$("#"+domId).parent().parent("[validate]").removeAttr("value");
			}
	});
}
/**
 * 搜索应收款
 */
// var clickBl = false;
function loadBisMustList(newSelectType, statusCd) {
	if(isEmpty($("#bisContId").val())) {
		return;
	}
	var data = {
		id:$("#bisContId").val(),
		statusCd:statusCd,
		seleChargeType:newSelectType,
		contBigTypeCd:$("#contBigTypeCd").val(),
		contSmallTypeCd:$("#contSmallTypeCd").val()
	};
	TB_showMaskLayer("正在搜索...");
	$.post(_ctx+"/bis/bis-cont!loadMustList.action", data, function(result) {
		$("#cost-nav li").removeClass("cost-nav-click");
		$("#bisMustPage").html(result);
		$("#div_"+newSelectType).addClass("cost-nav-click");
		TB_removeMaskLayer();
		autoHeight();
// clickBl = false;
	});
}

/**
 * 应收非空验证
 */
function validateMust() {
	var errorflag = false;
	$("#tbConItem :input").filter("[validate=required]").removeClass("error");
	$("#tbConItem :input").filter("[validate=required]").attr("style","");
	$("#tbConItem :input").filter("[validate=required]").filter("[value='']").each(function(i, dom){
		if($(dom).hasClass("Wdate")) {
			$(dom).css({color: "red", border: "1px solid red"});
		} else {
			$(dom).addClass("error");
		}
		errorflag = true;
	});
	return !errorflag;
}

/**
 * 改变费用类别
 */
function changeChargeType(chargeType, statusCd) {
	
	loadBisMustList(chargeType, statusCd);
	reloadChargeType();
/*
 * TB_showMaskLayer("正在搜索...");
 * 
 * clearMoneySt2();
 * 
 * if(!validateMust()) { TB_removeMaskLayer(); alert("年、月、应收日期为必填项"); return
 * false; }
 * 
 * $("#processForm").attr("action",
 * "bis-cont!saveMust.action").ajaxSubmit(function(result) { var msg =
 * result.split('&'); if(msg[0]=='0') {
 * ymPrompt.errorInfo({message:msg[1],title:"错误",closeBtn:false,width:220,height:180});
 * TB_removeMaskLayer(); }else { //$("#recordVersion").val(msg[1]); var oldtype =
 * $("#seleChargeType").val(); $("#div_"+oldtype).text(msg[1]);
 * loadBisMustList(chargeType, statusCd); } });
 */	

}

/**
 * 保存应收款
 */
function doSaveMust() {
	
	TB_showMaskLayer("正在保存...");
	
	clearMoneySt2();

	if(!validateMust()) {
		TB_removeMaskLayer();
		alert("年、月、应收日期为必填项");
		return false;
	}
	$("#processForm").attr("action", "bis-cont!saveMust.action").ajaxSubmit(function(result) {
		var msg = result.split('&');
		if(msg[0]=='0') {
			ymPrompt.errorInfo({message:msg[1],title:"错误",closeBtn:false,width:220,height:180});
			TB_removeMaskLayer();
		}else {
			ymPrompt.succeedInfo({message:'操作成功',title:"提示",closeBtn:false,width:220,height:180,winPos:[($("#resultDiv").width()-250)/2,$("#resultDiv").height()-300],handler:function(){
				// $("#recordVersion").val(msg[1]);
				reloadChargeType();
				loadBisMustList($("#seleChargeType").val(), $("#statusCd").val());
			}});
		}
	});
// TB_removeMaskLayer();
}
/**
 * 添加费用类别
 */
function doAddChargeType() {
	ymPrompt.win( {
		icoCls : "",
		autoClose:false,
		message : "<div id='addChargeTypeDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width  : 280,
		height : 130,
		title : "添加费用类别",
		closeBtn:false,
		winPos:[($("#resultDiv").width()-280)/2,$("#resultDiv").height()-250],
		afterShow : function() {
			var url = _ctx+"/bis/bis-cont!toAddChargeType.action";
			$.post(url, {id:$("#bisContId").val()}, function(result) {
				$("#addChargeTypeDiv").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				var cd = $('#sele_chargeType').val();
				var name = $("#sele_chargeType").find("option:selected").text();
				if(!isEmpty($("#div_"+cd).text())) {
					alert("此费用类别已存在");
				} else {
					$("#cost-nav").append("<li id='div_"+cd+"'>&nbsp;"+name+"&nbsp;</li>");
					$("#div_"+cd).bind("click", function(){
						changeChargeType(cd,$("#statusCd").val());
					});
					setNavBarStyle();
					ymPrompt.close();
				}
			} else {
				ymPrompt.close();
			}
		},
		btn:[["确定",'ok'],["退出",'cancel']]
	});
}
/**
 * 点击全选按钮
 */
function checkedAll(flag){
	// $("#tbConItem
	// :input[type=checkbox]").not(".approved").attr("checked",flag);
	$("#tbConItem :input[type=checkbox]").attr("checked",flag);
	$("#operate_all_div :input[type=checkbox]").attr("checked",flag);
}
/**
 * 应收款审核
 */
function doApproveMust(){
	var bisContId = $('#bisContId').val();
	if(isEmpty(bisContId)) {
		return false;
	}
	
	// doSaveMust();//保存
	
	var checkbox_ids = new Array();
	var checkbox_chkIds = new Array();
	var errorflag = false;
	$("#tbConItem :input[type=checkbox]:checked").not(".chk_all").each(function(i, dom) {
		// if(isEmpty($(dom).attr("status"))) {
		// if(!errorflag) {
		// alert("请先保存");
		// errorflag = true;
		// }
		// $(dom).attr("checked",false);
		// return true;
		// }
// if(isEmpty($(dom).attr("money"))) {
// if(!errorflag) {
// alert("不能审核金额为空的记录");
// errorflag = true;
// }
// $(dom).attr("checked",false);
// return true;
// }
		if("tempId"==$(dom).val()){
			alert("您的记录还未保存，请先保存");
			errorflag =true;
			return false;
		}
		checkbox_ids.push($(dom).val());
		checkbox_chkIds.push("chkIds=" + $(dom).val());
	});
	if(errorflag) {
		return false;
	}
	if(checkbox_ids.length == 0){
		alert("请勾选需要操作的记录");
		return false;
	}
	
	ymPrompt.confirmInfo({message:'确认审核？',title:'应收款审核',width:200,height:180,winPos:[($("#resultDiv").width()-200)/2,$("#resultDiv").height()-300],handler:function (tp){
		if (tp=='ok'){
			
			var param = checkbox_chkIds.join("&");
			param = param+"&statusCd=2";
			
			$.post(_ctx+"/bis/bis-cont!approveMust.action", param, function() {
				ymPrompt.succeedInfo({message:'操作成功',title:"提示",closeBtn:false,width:220,height:180,winPos:[($("#resultDiv").width()-220)/2,$("#resultDiv").height()-300],handler:function(){
					reloadChargeType();
					loadBisMustList($("#seleChargeType").val(), $("#statusCd").val());
				}});
			});
		}
	}});
	
}
/**
 * 应收款驳回
 */
function doBackMust() {
	var bisContId = $('#bisContId').val();
	if(isEmpty(bisContId)) {
		return false;
	}
	var checkbox_ids = new Array();
	var checkbox_chkIds = new Array();
	var errorflag = false;
	$("#tbConItem :input[type=checkbox]:checked").not(".chk_all").each(function(i, dom) {
		if(isEmpty($(dom).attr("status"))) {
			if(!errorflag) {
				alert("请先保存");
				errorflag = true;
			}
			$(dom).attr("checked",false);
			return true;
		}
// if(isEmpty($(dom).attr("money"))) {
// if(!errorflag) {
// alert("不能驳回金额为空的记录");
// errorflag = true;
// }
// $(dom).attr("checked",false);
// return true;
// }
		checkbox_ids.push($(dom).val());
		checkbox_chkIds.push("chkIds=" + $(dom).val());
	});
	if(errorflag) {
		return false;
	}
	if(checkbox_ids.length == 0){
		alert("请勾选需要操作的记录");
		return false;
	}
	
	ymPrompt.confirmInfo({message:'确认驳回？',title:'应收款驳回',width:200,height:180,winPos:[($("#resultDiv").width()-200)/2,$("#resultDiv").height()-300],handler:function (tp){
		if (tp=='ok'){
			var param = checkbox_chkIds.join("&");
			param = param+"&statusCd=3";
			
			$.post(_ctx+"/bis/bis-cont!approveMust.action", param, function(result) {
				ymPrompt.succeedInfo({message:'操作成功',title:"提示",closeBtn:false,width:220,height:180,winPos:[($("#resultDiv").width()-220)/2,$("#resultDiv").height()-300],handler:function(){
					reloadChargeType();
					loadBisMustList($("#seleChargeType").val(), $("#statusCd").val());
				}});
			});
		}
	}});
}
/**
 * 批量删除
 */
function doBatchDel() {
	ymPrompt.confirmInfo({message:'确认删除？',title:'删除',width:200,height:150,winPos:[($("#resultDiv").width()-200)/2,$("#resultDiv").height()-300],handler:function (tp){
		if (tp=='ok'){
			var checkbox_chkIds = new Array();
			var errorflag = false;
			$("#tbConItem :input[type=checkbox]:checked").each(function(i, dom) {
				if($(dom).attr("status") == '2') {
					if(!errorflag) {
						alert("请先驳回再删除");
						errorflag = true;
					}
					// $(dom).attr("checked",false);
				} else {
					if(!isEmpty($(dom).val())) {
						checkbox_chkIds.push("chkIds=" + $(dom).val());
					}
					$(dom).parent().parent().remove();
				}
			});
			if(errorflag) {
				return false;
			}
			if(checkbox_chkIds.length > 0){
				var param = checkbox_chkIds.join("&");
				$.post(_ctx+"/bis/bis-cont!deleteMust.action", param, function(result) {
					reloadChargeType();
					// loadBisMustList($("#seleChargeType").val(),
					// $("#statusCd").val());
				});
			}
		}
	}});
}

/**
 * 重载费用类别
 */
function reloadChargeType() {
	var seleChargeType = $("#seleChargeType").val();
	var data = {
		id : $('#bisContId').val(),
		seleChargeType : seleChargeType
	};
	$.post(_ctx+"/bis/bis-cont!reloadChargeType.action", data, function(result) {
		$("#div_"+seleChargeType).text(result);
	});
}
/**
 * 设置费用类别滚动按钮是否显示
 */
function setNavBarStyle() {
	var typeWidth = 0;
	var totleWidth = $(".nav_bar").width()-$(".nav_hidden").width()-3;
	$("li[id^=div_]").each(function(i,dom){
		typeWidth += 20+$(dom).width();
	});
	if(typeWidth > totleWidth) {
		$(".nav_hidden").css("z-index","2");
	} else {
		$(".nav_hidden").css("z-index","4");
	}
}
/**
 * 滚动费用类别
 */
var scrollWidth = 200;
var isScrolling = false;
function scrollBtnOperation(direction) {
	
	if (isScrolling) {
		return;
	}
	var mgLeft = parseInt($("#cost-nav").css("margin-left"));
	if (direction == "left") {
		mgLeft = mgLeft - scrollWidth;
	} else if (direction == "right") {
		// if (mgLeft < 0) {
			mgLeft = mgLeft + scrollWidth;
		// }
	}
	isScrolling = true;
	$("#cost-nav").animate({marginLeft: mgLeft}, 300, function() {
		isScrolling = false;
	});
}
/**
 * 搜索租户下的合同
 */
function loadTenantConts(bisTenantId) {
	// 商铺未签合同，清空合同列表
	// if(isEmpty(bisTenantId)) { return; }
	TB_showMaskLayer("正在搜索...");
	$.post(_ctx+"/bis/bis-cont!loadTenantConts.action", {bisTenantId:bisTenantId}, function(result) {
		$("#tenantContsPage").html(result);
		try{
			TB_removeMaskLayer();
		}catch(e){}
		try{
			// $("#result_table").css("width",Number($(document).width()-32)+"px");
		}catch(e){}
	});
}

/**
 * 打开网批
 */
function openResTask(id){
	var url = _ctx+'/res/res-approve-info.action?id='+id+'&resAuthTypeCd=TEST_248';
	openWindow('resApprove', '网上审批', url);
}

function openContTab(url) {
	openWindow("bisContMenu", "合同明细", url);
}

function openWindow(tabid, title, url) {
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab(tabid,title,url,true);
}
function closeWindow(tabId) {
	var cfg = {};
	cfg.data = {tabId:tabId};
	parent.TabUtils.closeTab(cfg);
}

function invalidAuto() {

	TB_showMaskLayer("正在执行...");
	$.post(_ctx+"/bis/bis-cont!invalidAuto.action", function(result) {
		TB_removeMaskLayer();
	});
}

// ///////////以下为验证相关///////////////
/**
 * 按键时若存在默认字符,清空
 */
function clearSearchInput(dom) {
	if( $(dom).val() == '待计算'){
		$(dom).val('');
		$(dom).css({color:"#000000"});
	}
}
function resetSearchInput(dom) {
	if( $(dom).val().trim() == ''){
		$(dom).val('待计算');
		$(dom).css({color: "#909090"});
	}else{
		$(dom).css({color:"#000000"});
	}
}
/**
 * 验证应收款日期
 */
function checkMustDate(dom, type) {
	var contStartDate = $("#contStartDate").val();
	var contEndDate = $("#contEndDate").val();
	var index = dom.id.split("_")[1];
	var my = $("#mustYear_"+index).val();
	var mm = $("#mustMonth_"+index).val();
	if(isEmpty(my) || isEmpty(mm)) {
		return false;
	}
	var date = my+"-"+mm;
	
	if(checkDateExist(date, index)) {
		alert(date+"的记录已存在");
		$("#mustYear_"+index).val("");
		$("#mustMonth_"+index).val("");
		return false;
	}
	$(dom).parent().parent().find(":checkbox").attr("date", date);
}
function checkDateExist(date, index) {
	var exists = false;
	$("#tbConItem :input[type=checkbox]").not("#chk_"+index).each(function(i, dom) {
		var od = $(dom).attr("date");
		if(!isEmpty(od) && od==date) {
			exists = true;
			return false;
		}
	});
	return exists;
}

/**
 * 商铺选择(NEW)
 */ 
function doStoreSelect(ids,nos,inFloorType){
	if($('#'+nos).hasClass("search") && $('#'+nos).val().trim() == $('#'+nos).attr("searchtext")) {
		$('#'+nos).val('');
	}
	var title ="选择商铺";
// var isTenant = true;
	var width = 560;
// if(isEmpty(inFloorType)) {
// isTenant = false;
		var floorType = $('#contBigTypeCd').val();
// } else {
// var floorType = inFloorTypel;
// }
	if(floorType=='2'){
		title = "选择公寓";
	} else if(floorType=='3') {
		title = "选择多经";
		width = 390;
	}
	var bisProjectId=$('#bisProjectId').val();
	if(isEmpty(bisProjectId) || bisProjectId == 'undefined') {
		// alert("请先选择项目");
		$('#bisProjectName').addClass("error");
		$('#bisProjectName').focus();
		return false;
	}
	var idsTemp = $('#'+ids).val();
	var nosTemp = $('#'+nos).val();
	if(!isEmpty(idsTemp)) {
		idsTemp += ",";
	}
	if(!isEmpty(nosTemp)) {
		nosTemp += ",";
	}
	
	ymPrompt.win( {
		icoCls : "",
		autoClose:false,
		message : "<div id='divStoreSelect'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : width,
		height : 480,
		title : title,
		closeBtn:true,
		afterShow : function() {
			var url = _ctx+"/bis/bis-project!doStoreSelect.action";
			var data = {
				floorType:floorType,
				bisProjectId:bisProjectId,
				bisStoreIdsTemp:idsTemp,
				bisStoreNosTemp:nosTemp
			};
			$.post(url, data, function(result) {
				$("#divStoreSelect").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				$('#'+ids).val($('#bisStoreIdsTemp').val().substring(0, $('#bisStoreIdsTemp').val().length-1));
				$('#'+nos).val($('#bisStoreNosTemp').val().substring(0, $('#bisStoreNosTemp').val().length-1));
				
				if(floorType == "1") {
					var url = _ctx+"/bis/bis-cont!getStoreInfo.action";
					$.post(url,{bisStoreIds:$('#'+ids).val()}, function(data) {
						var data = eval('('+data+')');
						$.each(data,function(i,n){
							if($("#"+i)) {
								$("#"+i).val(n);
							}
							// 计算公式中的面积
							if($("#contSmallTypeCd").val()=="6" && i=="rentSquare") {
								$("#formulaSquare").val(n);
								calculatePrice();
							}
						});
						if($("#contSmallTypeCd").val()=='1') {
							calculateMonthRent();
						}
					});
				} else if(floorType == "2") {
					var url = _ctx+"/bis/bis-cont!getFlatInfo.action";
					$.post(url,{bisFlatIds:$('#'+ids).val()}, function(data) {
						var data = eval('('+data+')');
						$.each(data,function(i,n){
							if($("#"+i)) {
								$("#"+i).val(n);
							}
						});
					});
				}
				
				$('#'+nos).filter(".search").trigger("blur");
				ymPrompt.close();
			} else if(btn == 'clear') {
				$('#bisStoreIdsTemp').val('');
				$('#bisStoreNosTemp').val('');
				$("#ulStore li").removeClass("ctslt-nav-click");
				$("#ulStoreSelected li").remove();
			} else {
				$('#'+nos).filter(".search").trigger("blur");
				ymPrompt.close();
			}
		},
		btn:[['确定','ok'],['退出','cancel'],['清空','clear']]
	});
}

// 商铺选择
// @Deprecated
function doSelectStore(ids,nos){
	clearSearchtext();
	initData();
	var title ="商铺选择";
	var contBigTypeCd=$('#contBigTypeCd').val();
	if(contBigTypeCd=='2'){
		title = "公寓选择";
	}
	if(isEmpty(contBigTypeCd)) {
		contBigTypeCd = "1";// 默认商铺
	}
	var bisProjectId=$('#bisProjectId').val();
	if(isEmpty(bisProjectId)) {
		// alert("请先选择项目");
		$('#bisProjectName').addClass("error");
		$('#bisProjectName').focus();
		return false;
	}
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='storeNoDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 350,
		height : 414,
		title : title,
		closeBtn:true,
		afterShow : function() {
			var url = _ctx+"/bis/bis-project!selectFloorNum.action";
			$.post(url,{contBigTypeCd:contBigTypeCd,bisProjectId:bisProjectId}, function(result) {
				$("#storeNoDiv").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				$('#bisStoreIdTemps').val(bisStoreIdTemp);
				$('#bisStoreNoTemps').val(bisStoreNoTemp);
				$('#'+ids).val($('#bisStoreIdTemps').val().substring(0, $('#bisStoreIdTemps').val().length-1));
				$('#'+nos).val($('#bisStoreNoTemps').val().substring(0, $('#bisStoreNoTemps').val().length-1));
				
				if(contBigTypeCd == "1") {
					var url = _ctx+"/bis/bis-cont!getStoreInfo.action";
					$.post(url,{bisStoreIds:$('#'+ids).val()}, function(data) {
						var data = eval('('+data+')');
						$.each(data,function(i,n){
							if($("#"+i)) {
								$("#"+i).val(n);
							}
						});
					});
				}
// $('#square').val(squareAll);
// $('#rentSquare').val(rentSquareAll);
// $('#innerSquare').val(innerSquareAll);
			}
			if(btn=='cancel'){
				 bisStoreIdTemp ='';
				 bisStoreNoTemp ='';
// squareAll=0;
// rentSquareAll=0;
// innerSquareAll=0;
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["退出",'cancel']]
	});
}
/**
 * 商铺、公寓选取列表
 * 
 * @param bisFloorId
 * @Deprecated
 */
function selectFloorNum(bisFloorId){
	var contBigTypeCd=$('#contBigTypeCd').val();
	if(isEmpty(contBigTypeCd)) {
		contBigTypeCd = "1";// 默认商铺
	}
	var bisProjectId=$('#bisProjectId').val();
	$.post(_ctx+"/bis/bis-project!selectList.action",
			{
			  bisProjectId:bisProjectId,
			  contBigTypeCd:contBigTypeCd,
			  bisFloorId:bisFloorId
		    },
			function(result) {
				if (result) {
					$("#selectList").html(result);
					boxChecked();
				}
			}
		);
}
var bisStoreIdTemp ='';
var bisStoreNoTemp ='';
// var squareAll=0;
// var rentSquareAll=0;
// var innerSquareAll=0;

// @Deprecated
function initData(){
	var bisStoreIds=$('#bisStoreIds').val();
	var bisStoreNos=$('#bisStoreNos').val();
	var bisFlatIds=$('#bisFlatIds').val();
	var bisFlatNos=$('#bisFlatNos').val();
	var bisMultiIds=$('#bisMultiIds').val();
	var bisMultiNos=$('#bisMultiNos').val();
	var square=$('#square').val();
	var rentSquare=$('#rentSquare').val();
	var innerSquare=$('#innerSquare').val();
	if(bisStoreIds!=""&&bisStoreIds!=null){
		bisStoreIdTemp = bisStoreIds+",";
	}
	if(bisStoreNos!=""&&bisStoreNos!=null){
		bisStoreNoTemp = bisStoreNos+",";
	}
	if(bisFlatIds!=""&&bisFlatIds!=null){
		bisStoreIdTemp = bisFlatIds+",";
	}
	if(bisFlatNos!=""&&bisFlatNos!=null){
		bisStoreNoTemp = bisFlatNos+",";
	}
	if(bisMultiIds!=""&&bisMultiIds!=null){
		bisStoreNoTemp = bisMultiIds+",";
	}
	if(bisMultiNos!=""&&bisMultiNos!=null){
		bisStoreNoTemp = bisMultiNos+",";
	}
// if(square!=""&&square!=null){
// squareAll = square;
// }
// if(rentSquare!=""&&rentSquare!=null){
// rentSquareAll = rentSquare;
// }
// if(innerSquare!=""&&innerSquare!=null){
// innerSquareAll = innerSquare;
// }
}
/**
 * 选择公寓
 * 
 * @Deprecated
 */
function saveTempIdFlat(bisStoreIds,bisStoreNos){
	var valus =new Array();
	if($("#storeBox_"+bisStoreIds).attr("checked")==true){
		bisStoreIdTemp=bisStoreIdTemp+bisStoreIds+",";
		bisStoreNoTemp=bisStoreNoTemp+bisStoreNos+",";
	}else{
		bisStoreIdTemp=bisStoreIdTemp.replace(bisStoreIds+",","");
		bisStoreNoTemp=bisStoreNoTemp.replace(bisStoreNos+",","");
	}
	$('#bisStoreIdTemps').val(bisStoreIdTemp);
	$('#bisStoreNoTemps').val(bisStoreNoTemp);
}
/**
 * 选择商铺
 * 
 * @Deprecated
 */
function saveTempId(values,bisStoreIds,bisStoreNos){
	var valus =new Array();
	if($("#storeBox_"+bisStoreIds).attr("checked")==true){
		bisStoreIdTemp=bisStoreIdTemp+bisStoreIds+",";
		bisStoreNoTemp=bisStoreNoTemp+bisStoreNos+",";

// valus = values.split("`");
// squareAll=((squareAll*1000000000000+Number(valus[0])*1000000000000)/1000000000000).toFixed(2);
// innerSquareAll=((innerSquareAll*1000000000000+Number(valus[1])*1000000000000)/1000000000000).toFixed(2);
// rentSquareAll=((rentSquareAll*1000000000000+Number(valus[2])*1000000000000)/1000000000000).toFixed(2);
	}else{
		bisStoreIdTemp=bisStoreIdTemp.replace(bisStoreIds+",","");
		bisStoreNoTemp=bisStoreNoTemp.replace(bisStoreNos+",","");
		
// valus = values.split("`");
// squareAll=((squareAll*1000000000000-Number(valus[0])*1000000000000)/1000000000000).toFixed(2);
// innerSquareAll=((innerSquareAll*1000000000000-Number(valus[1])*1000000000000)/1000000000000).toFixed(2);
// rentSquareAll=((rentSquareAll*1000000000000-Number(valus[2])*1000000000000)/1000000000000).toFixed(2);
	}
// var bisStoreId=new Array();
// var bisStoreNo=new Array();
// var bisStoreIdTemps =$('#bisStoreIdTemps').val();
// var bisStoreNoTemps =$('#bisStoreNoTemps').val();
// bisStoreId =bisStoreIdTemps.substring(0,
// bisStoreIdTemps.length-1).split(",");
// bisStoreNo =bisStoreNoTemps.substring(0,
// bisStoreNoTemps.length-1).split(",");
// for(i=0;i<bisStoreId.length;i++){
// if(bisStoreId[i]==bisStoreIds){
// bisStoreIdTemp=bisStoreIdTemp.replace(bisStoreIds+",","");
// bisStoreIdTemp=bisStoreIdTemp.replace(bisStoreIds+",","");
// alert(bisStoreIdTemp);
// }
// }
// for(i=0;i<bisStoreNo.length;i++){
// if(bisStoreNo[i]==bisStoreNos){
// bisStoreNoTemp=bisStoreNoTemp.replace(bisStoreNos+",","");
// bisStoreNoTemp=bisStoreNoTemp.replace(bisStoreNos+",","");
// alert(bisStoreNoTemp);
// }
// }
// for(i=0;i<bisStoreId.length-1;i++){
// bisStoreIdTemp=bisStoreIdTemp+bisStoreId[i]+",";
// }
// for(i=0;i<bisStoreNo.length-1;i++){
// bisStoreNoTemp=bisStoreNoTemp+bisStoreNo[i]+",";
// }
// $('#bisStoreIdTemps').val('');
// $('#bisStoreNoTemps').val('');
	$('#bisStoreIdTemps').val(bisStoreIdTemp);
	$('#bisStoreNoTemps').val(bisStoreNoTemp);
}
/**
 * 初始化已选复选框
 * 
 * @Deprecated
 */
function boxChecked(){
	var bisStoreId=new Array();
	var bisStoreIdCart=$('#bisStoreIds').val()+',';
	if(bisStoreIdCart!=","){
		bisStoreId = bisStoreIdCart.split(",");
	}else{
		bisStoreId =bisStoreIdTemp.split(",");
	}
	for(i=0;i<bisStoreId.length-1;i++){
		var s=bisStoreId[i];
		 $("#storeBox_"+bisStoreId[i]).attr("checked",true);
	}
}
/**
 * 清空复选框
 * 
 * @Deprecated
 */
function clearAll(){
// squareAll=0;
// rentSquareAll=0;
// innerSquareAll=0;
	 bisStoreIdTemp ='';
	 bisStoreNoTemp ='';
	$('#bisStoreIdTemps').val('');
	$('#bisStoreNoTemps').val('');
	$("input:checked").each(function(){
		$(this).attr("checked",false);
	});
}
/**
 * 多经选择框与商铺公寓有个性差异，此处单独拿出
 * 
 * @param ids
 * @param nos
 * @returns {Boolean}
 * @Deprecated
 */
function selectMulti(ids,nos){
	clearSearchtext();
	initData();
	var contBigTypeCd=$('#contBigTypeCd').val();
	if(isEmpty(contBigTypeCd)) {
		contBigTypeCd = "1";// 默认商铺
	}
	var bisProjectId=$('#bisProjectId').val();
	if(isEmpty(bisProjectId)) {
		alert("请先选择项目");
		return false;
	}
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='multiDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 350,
		height : 414,
		title : "多经选择",
		closeBtn:false,
		afterShow : function() {
			var url = _ctx+"/bis/bis-project!selectList.action";
			$.post(url,{contBigTypeCd:contBigTypeCd,bisProjectId:bisProjectId}, function(result) {
				$("#multiDiv").html(result);
				boxCheckedMulti();
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				$('#'+ids).val(bisStoreIdTemp.substring(0, bisStoreIdTemp.length-1));
				$('#'+nos).val(bisStoreNoTemp.substring(0, bisStoreNoTemp.length-1));
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["退出",'cancel']]
	});
}
/**
 * 保存多经信息
 * 
 * @param bisMultiId
 * @param multiName
 * @Deprecated
 */
function saveMultiId(bisMultiId,multiName){
	if($("#multiBox_"+bisMultiId).attr("checked")==true){
		bisStoreIdTemp=bisStoreIdTemp+bisMultiId+",";
		bisStoreNoTemp=bisStoreNoTemp+multiName+",";
	}else{
		bisStoreIdTemp=bisStoreIdTemp.replace(bisMultiId+",","");
		bisStoreNoTemp=bisStoreNoTemp.replace(multiName+",","");
	}
}
/**
 * 初始化多经已选复选框
 * 
 * @Deprecated
 */
function boxCheckedMulti(){
	var bisMultiId=new Array();
	var bisMultiIdCard=$('#bisMultiIds').val()+',';
	if(bisMultiIdCard!=","){
		bisMultiId = bisMultiIdCard.split(",");
	}else{
		bisMultiId =bisStoreIdTemp.split(",");
	}
	for(i=0;i<bisMultiId.length-1;i++){
		var s=bisMultiId[i];
		 $("#multiBox_"+bisMultiId[i]).attr("checked",true);
	}
}


