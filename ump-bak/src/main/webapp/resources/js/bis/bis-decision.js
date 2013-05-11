/**
 * 加载报表数据
 */
function loadData(dom,index){
	showYMW(index);
	var data = {};
	var year,month,startDay,endDay;
	if(dom != null && dom != undefined){
		$("#reportType").val(index);
		$("#navigation").find(".selected").removeClass("selected");
	    $(dom).addClass("selected");
		$("#spanTitleName").html($(dom).text());
	}
	if("3" == index){//主力店招商情况报表
		year = $("#year").val();
		month = $("#month").val();
		startDay = $("#startDay").val();
		endDay = $("#endDay").val();
		data = {year:year,month:month,startDay:startDay,endDay:endDay,reportType:index};
	}else{ //销售情况、主力店招商情况、商家营业业绩状况报表
		year = $("#year").val();
		month = $("#month").val();
		data = {year:year,month:month,reportType:index};
	}
	var url = _ctx+"/bis/bis-decision!list.action";
	TB_showMaskLayer("正在搜索...");
	$.post(url, data, function (result) {
		TB_removeMaskLayer();
        $("#dataDiv").html(result);
        //设置状态(bis-decision-main.jsp中)
        setStatus2();
        autoHeight();
    });
}

/**
 * 设置状态及按钮显示
 */
function setStatus(status) {
    if (status == -1) {
        $("#status").html("当前状态：待新增");
        $("#addReportBut").show();
        $("#submitReportBut").hide();
        $("#confirmReportBut").hide();
        $("#rejectReportBut").hide();
    }
    if (status == 0) {
        $("#status").html("当前状态：新增中");
        $("#confirmReportBut").hide();
        $("#rejectReportBut").hide();
        $("#addReportBut").show();
        $("#submitReportBut").show();
    }
    if (status == 1) {
        $("#status").html("当前状态：已提交");
        $("#addReportBut").hide();
        $("#submitReportBut").hide();
        $("#confirmReportBut").show();
        $("#rejectReportBut").show();
    }
    if (status == 2) {
    	var updator = $("#updator0").val(); //确认人
    	var updatedDate = $("#updatedDate0").val(); //确认时间
        $("#status").html("当前状态：已确认"+"（"+updator+"&nbsp;"+updatedDate+"）");
        $("#addReportBut").hide();
        $("#submitReportBut").hide();
        $("#confirmReportBut").hide();
        $("#rejectReportBut").show();
    }
    if (status == 3) {
        $("#status").html("当前状态：被驳回");
        $("#confirmReportBut").hide();
        $("#rejectReportBut").hide();
        $("#addReportBut").show();
        $("#submitReportBut").show();
    }
}
/**
 * 注册点击事件
 */
function initButClick(){
    $("#addReportBut, #addReportBut2").click(function () {
    	addFileReport();
    });
    $("#submitReportBut").click(function () {
        setReportStatusFlg("您确定要提交本月报吗？", "提交月报", "submit");
    });
    $("#confirmReportBut").click(function () {
        setReportStatusFlg("您确定固化本期月报吗？<br/>固化后本期月报不能再修改！", "固化月报", "confirm");
    });
    $("#rejectReportBut").click(function () {
        setReportStatusFlg("您确定要驳回本期月报吗？<br/>驳回后本期月报可以再修改！", "驳回月报", "reject");
    });
}
// 消息提示框
function setReportStatusFlg(message1, message2,statusFlg) {
    ymPrompt.confirmInfo({
        message:"<p style='margin-top:-25px;display:block;line-height: 20px;'>" + message1 + "</p>",
        width:350,
        height:160,
        title:"消息提示！",
        closeBtn:true,
        handler:function (btn) {
            if (btn == 'ok') {
                TB_showMaskLayer("正在" + message2 + "请稍后...");
                var params = {
                    year:$("#year").val(),
                    month:$("#month").val(),
                    reportType:$("#reportType").val(),
                    statusFlg:statusFlg
                };
                var url = _ctx+"/bis/bis-decision!changeReportStatus.action";
                $.post(url, params, function (result) {
                    $("#dataDiv").html(result);
                    TB_removeMaskLayer();
                    var status = $("#status0").val();
                    if(status == null || status == undefined ||status ==''){
                    	status = -1;
                    }
                    setStatus(status);
                });
            }
            ymPrompt.close();
        }
    });
}

/**
 * 控制显示年月周div和状态按钮div、上下月、上下周显示
 */
function showYMW(type){
	if("2" == type){
		$("#weekSpan").hide();
		$("#statusDiv").hide();
		$("#week_ul").hide();
		$("#statusDiv2").show();
		$("#month_ul").show();
	}else if("3" == type){
		$("#statusDiv").hide();
		$("#statusDiv2").hide();
		$("#weekSpan").show();
		$("#month_ul").hide();
		$("#week_ul").show();
	}else if("4" == type){
		$("#statusDiv").hide();
		$("#statusDiv2").hide();
		$("#weekSpan").hide();
		$("#week_ul").hide();
		$("#month_ul").show();
	}else{
		$("#statusDiv2").hide();
		$("#weekSpan").hide();
		$("#week_ul").hide();
		$("#statusDiv").show();
		$("#month_ul").show();
	}
	$("#selectYear").hide();
	$("#selectMonth").hide();
	$("#selectWeek").hide();
}

/**
 * 点击年、月、周查询
 */
function clickYMW(dom,type){
	var value = $(dom).val();
	if("year" == type){
		changeSelect(value,'year');
		$("#year").val(value);
		$("#selectYear").hide();
	}else if("month" == type){
		var tmpMonthValue ;
		if(value != 10){
			tmpMonthValue = value.replace("0","");
		}else{
			tmpMonthValue = value;
		}
		changeSelect(tmpMonthValue,'month');
		$("#month").val(tmpMonthValue);
		$("#selectMonth").hide();
	}else if("week" == type){
		changeSelect(value,'week');
		$("#week").val(value);
		$("#selectWeek").hide();
	}
	//显示周的时间段
	setDateText($("#year").val(),$("#month").val(),$("#week").val(),true);
	
}

/**
 * 选择年、月、周
 */
function selectYMW(dom,type) {
	var value = $(dom).val();
	if("year"==type){
		if(""!=value){
			changeSelect(value,'year');
		}
		$("#selectMonth").hide();
		$("#selectWeek").hide();
		$("#selectYear").show();
	}else if("month"==type){
		if(""!=value){
			changeSelect(value,'month');
		}
		$("#selectYear").hide();
		$("#selectWeek").hide();
		$("#selectMonth").show();
	}else if("week"==type){
		if(""!=value){
			changeSelect(value,'week');
		}
		$("#selectYear").hide();
		$("#selectMonth").hide();
		$("#selectWeek").show();
	}else{
		$("#selectYear").hide();
		$("#selectMonth").hide();
		$("#selectWeek").hide();
	}
}


/**
 * 更改年月周div样式
 */
function changeSelect(value,type){
	if("year" == type){
		$("#selectYear").find("input").each(function(i){
			$(this).attr('class','btnCancel');
			if(value == $(this).val()){
				$(this).attr('class','btnSelect');
			}
		});
	}else if("month" == type){
		$("#selectMonth").find("input").each(function(i){
			$(this).attr('class','btnCancel');
			var monthValue = $(this).val();
			var tmpMonthValue ;
			if(monthValue != 10){
				// 值不为10的包含‘0’字符串的去除‘0’
				tmpMonthValue = monthValue.replace("0","");
			}else{
				tmpMonthValue = monthValue;
			}
			if(value == tmpMonthValue){
				$(this).attr('class','btnSelect');
			}
		});
	}else if("week" == type){
		$("#selectWeek").find("input").each(function(i){
			$(this).attr('class','btnCancel');
			if(value == $(this).val()){
				$(this).attr('class','btnSelect');
			}
		});
	}
}

/**
 * 新增销售情况月报
 */
function addFileReport(){
	// 得到年月值
	var sellYear = $("#year").val();
	var sellMonth = $("#month").val();
	if(isEmpty(sellYear) && isEmpty(sellMonth)) {
		alert("请选择年月");
		return false;
	}
	var url = _ctx+'/bis/bis-decision!addFileReport.action';
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='sellReportDiv' style='padding-right:10px;'><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
		width : 500,
		height :260,
		title : "新增月报",
		afterShow : function() {
			var reportType = $("#reportType").val();
			$.post(url, {reportType:reportType}, function(result) {
				$("#sellReportDiv").html(result);
			});
		},
		handler : function(btn){
			ymPrompt.close();
		},
		closebtn:true,
		btn:[]
	});
}

/**
 * 上一月、下一月
 */
function getLastOrNextMonth(type){
	var sellYear = $("#year").val();
	var sellMonth = $("#month").val();
	if(isEmpty(sellYear) && isEmpty(sellMonth)) {
		alert("请选择年月");
		return false;
	}
	var year = 0;
	var month = 0;
	if("last"==type){
		if("1" == sellMonth){
			year = parseInt(sellYear) - parseInt(1);
			month = 12;
		}else{
			year = sellYear;
			month = parseInt(sellMonth) - parseInt(1);
		}
	}else if("next"==type){
		if("12" == sellMonth){
			year = parseInt(sellYear) + parseInt(1);
			month = 1;
		}else{
			year = sellYear;
			month = parseInt(sellMonth) + parseInt(1);
		}
	}
	$("#year").val(year);
	$("#month").val(month);
	setDateText(year,month,$("#week").val(),true);
	
	/*
	var data={year:year,month:month,reportType:'1'};
	var url = _ctx+"/bis/bis-decision!reports.action";
	TB_showMaskLayer("正在搜索...");
	$.post(url, data, function (result) {
		TB_removeMaskLayer();
        $("#dataDiv").html(result);
        $("#dataDiv").show();
        autoHeight();
    });
    */
}

/**
 * 上一周、下一周
 */
function getLastOrNextWeek(type){
	var sellYear = $("#year").val();
	var sellMonth = $("#month").val();
	var sellWeek = $("#week").val();
	if(isEmpty(sellYear) && isEmpty(sellMonth) && isEmpty(sellWeek)) {
		alert("请选择年月周");
		return false;
	}
	var year = 0;
	var month = 0;
	var week = 0;
	if("last"==type){
		if("1" == sellWeek){
			week = 5;
			if("1" == sellMonth){
				month = 12;
				year = parseInt(sellYear) - parseInt(1);
			}else{
				month = parseInt(sellMonth) - parseInt(1);
				year = parseInt(sellYear);
			}
		}else{
			week = parseInt(sellWeek) - parseInt(1);
			month = parseInt(sellMonth);
			year = parseInt(sellYear);
		}
	}else if("next"==type){
		if("5" == sellWeek){
			week = 1;
			if("12" == sellMonth){
				month = 1;
				year = parseInt(sellYear) + parseInt(1);
			}else{
				month = parseInt(sellMonth) + parseInt(1);
				year = parseInt(sellYear);
			}
		}else{
			week = parseInt(sellWeek) + parseInt(1);
			month = parseInt(sellMonth);
			year = parseInt(sellYear);
		}
	}
	$("#year").val(year);
	$("#month").val(month);
	$("#week").val(week);
	setDateText(year,month,$("#week").val(),true);
	
}

/**
 * 初始化年月
function initYMW(){
	/*var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var week = getMonthWeek(year, month, day);
	$("#year").val(year);
	$("#month").val(month);
	$("#week").val(week);
	setDateText(year,month,week,'true');
	
}
var getMonthWeek = function (a, b, c){
	var date = new Date(a, parseInt(b) - 1, c), w = date.getDay(), d = date.getDate(); 
	return Math.ceil((d + 6 - w) / 7); 
};
*/

/**
 * 得到每一周的时间段
 */
function setDateText(year,month,weekOfMonth,isload){
    var queryParam={
        'year':year,
        'month':month,
        'weekOfMonth':weekOfMonth
    };
    $.post(_ctx+"/bis/bis-main-shop-report!getStartEndDay.action",queryParam, function(result) {
        if(result){
            var dates =result.split("~");
            if(dates.lenght=2){
                var html =  '<font color="red">'+dates[0]+'</font>&nbsp;&nbsp;至&nbsp;&nbsp;<font color="red">'+dates[1]+'</font>';
                $("#startDay").val(dates[0]);
                $("#endDay").val(dates[1]);
                $("#dataText").html(html);
                if(isload){
                	var reportType = $("#reportType").val();
                	loadData(null,reportType);
                }
            }
        }

    });
}

/**
 * 绑定年月周input框失去焦点事件
 */
function bindInputBlur(type){
	if('over'== type){
		$("#year,#month,#week").unbind();
	}else{
		$("#year,#month,#week").bind('blur',function(){
			selectYMW(this,'cancel');
		});
	}
}
/**
 * 全屏
 */
function fullScreen(){
	var url = _ctx+"/bis/bis-decision!main.action";
	window.open(url);
}

//=========================导入、导出开始==============================//

/**
 * 导出模板
 */
function doExport(reportType){
	if('1' == reportType){
		//得到年月值
		var sellYear = $("#year").val();
		var sellMonth = $("#month").val();
		if(isEmpty(sellYear) && isEmpty(sellMonth)) {
			alert("请选择年月");
			return false;
		}
		var url = _ctx+"/bis/bis-decision!exportTemplate.action?year="+sellYear+"&month="+sellMonth+"&reportType="+reportType;
		TB_showMaskLayer("正在导出...",5000);
		location.href = url;
	}else if('2' == reportType){
		//得到年月时间段
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		if($("#timeSpan").is(":hidden")){
			if(isEmpty(startDate)) {
				alert("请选择月份");
				return false;
			}
		}else{
			if(isEmpty(startDate) || isEmpty(endDate)) {
				alert("请选择月份");
				return false;
			}
		}
		var data = {startDate:startDate,endDate:endDate};
		//检查该月的第二个双周是否有数据，有则导出，无则不导出
		$.post(_ctx+"/bis/bis-decision!checkIsExistTwoWeek.action",data, function(result) {
			if("true" == result){
				var url = _ctx+"/bis/bis-decision!exportTemplate.action?startDate="+startDate+"&endDate="+endDate+"&reportType="+reportType;
				TB_showMaskLayer("正在导出...",5000);
				location.href = url;
			}else{
				alert("您选择的 ["+result+"] 在两费收缴双周报中无数据，请至 '商业系统->营运平台->财务报表->两费收缴双周报' 添加数据！");
				return false;
			}
		});
	}else{
		return false;
	}
}

/**
 * 导入数据
 */
function doImport(reportType){
	if(isEmpty($("#importFile").val())) {
		alert("请先选择要导入的文件");
		$("#importFile").focus();
		return false;
	}
	ymPrompt.close(); //关闭弹出框
	TB_showMaskLayer("正在导入...");
 	$("#repotrForm").ajaxSubmit(function(result){
 		TB_removeMaskLayer();
 		var msg = result.split(",");
 		if(msg[1] == "success") {
 		    alert("导入成功：共新增"+msg[2]+"条记录，更新"+msg[3]+"条记录，耗时"+msg[4]+"秒");
 		    $("#importFile").val("");
 		 	loadData(null,reportType);
 		 	$("#addReportBut").hide();
 		 	$("#submitReportBut").show();
 		} else {
 			alert("导入失败："+msg[2]);
 		}
 	});
}

/**
 * 销售是否情况报表导出模板界面日期范围控制
 */
function clearEndDate(dom){
	var startDate = $(dom).val();
	var endDate = $("#endDate").val();
	if(''!=startDate && ''!=endDate){
		var s = startDate.replace('-','');
		var e = endDate.replace('-','');
		if(parseInt(s) >= parseInt(e)){
			$("#endDate").val('');
		}
	}
}
function showAllTime(){
	if($("#timeSpan").is(":hidden")){
		$("#timeSpan").show();
		$("#timeBtus").attr('value','按月');
	}else{
		$("#endDate").val('');
		$("#timeSpan").hide();
		$("#timeBtus").attr('value','按时间段');
	}
}