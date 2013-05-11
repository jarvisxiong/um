<%@page import="com.hhz.ump.util.DictContants" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/global.jsp" %>
    <%@ include file="/common/taglibs.jsp" %>
    <%@ include file="/common/nocache.jsp" %>
    <%@ page language="java" %>
    <title>公寓收费记录汇总</title>
    <script type="text/javascript">
        var _ctx = '${ctx}';
        var isProjectBusinessCompany = "${isProjectBusinessCompany}";
    </script>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-project.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/page.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis.fact.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"/>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/ConvertUtil.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
    <script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
    <script type="text/javascript" src="${ctx}/js/formValidator/formValidator.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
    <script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
    <script src="${ctx}/resources/js/mes/jquery-ump.js" type="text/javascript"></script>
    <script type="text/javascript" charset="UTF-8" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/base.css"/>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
    <script src="${ctx}/resources/js/common/MaskLayer.js" type="text/javascript"></script>
    <link href="${ctx}/resources/css/mes/thickbox.css" media="screen" rel="stylesheet" type="text/css"/>
</head>
<body>
<form action="${ctx}/bis/bis-fact!list.action" id="searchForm" method="post">
    <div id="header">
        <div class="title_bar">
            <h2>公寓收费记录</h2>

            <div class="left">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目：
                <input type="text" id="bisProjectName"  name="bisProjectName" value="${bisProjectName}" readonly="true"
                       style="cursor: pointer; color: #ff0000;border:1px solid #333;height:16px;"/>
                <input class="search" type="hidden" id="bisProjectIdFact" name="bisProjectId" value="${bisProjectId}"/>
            </div>
            <div style="float:right; height:26px; margin-right:5px; margin-top:6px;">
                <div class="btn_green" onclick="clkFullScreen('${bisProjectId}');">全屏</div>
            </div>
        </div>

        <div class="bis_search" id="main_search_div">
            <table class="tb_search" style="margin-left: 5px;" align="left">
                <tr class="no_advances_dime">
                    <td align="right" width="50">楼号：</td>
                    <td width="100">
						<input type="hidden" id="bisFloorId1"/>
						<s:select cssStyle="display:none;" list="mapBuilding" listKey="key" listValue="value" id="bisBuildingHid"></s:select>
	                    <input type="text" class="pd-chill-tip" readonly="readonly" style="width:100px;height:16px;" id="bisFloorName1" onclick="showDiv(true,1);" onmouseout="bindInputBlur('out',1);"/>
						<div id="selectFloor1" style="display:none;" onmouseover="bindInputBlur('over',1);" onmouseout="bindInputBlur('out',1);">
						</div>
                    </td>
                    <td align="right" id="detailLabel" width="70">公寓编号：</td>
                    <td width="100">
                        <input type="text"  style="width:100px;height:16px;" id="flatNo"/>
                    </td>
                    <td style="padding-left: 30px;">
                        <input id="btn_search" type="button" style="width:65px" class="btn_blue" onclick="loadDate();" value="搜索"/>
                        <input id="btn_imp_fact" class="btn_green" type="button" style="width:65px;" onclick="batchOper();" value="批量操作"/>
                        <input id="btn_fact_count" class="btn_green" type="button" style="width:128px;" onclick="queryRecordCount();" value="查看公寓收费记录总计"/>
                    </td>
                </tr>
            </table>
        </div>
        <div class="bis_search" id="main_search_div1"
             style="height:30px;background:white;border:0px;margin-bottom: 9px;">
            <table style="width:100%">
                <tr>
                    <td colspan="12">
                        <ul id="bis_rpt" style="margin-left: 4px;list-style-type:none;">
                            <li value="2" class="bis_fact_unclick" id="must">应收明细</li>
                            <li value="3" class="bis_fact_unclick" id="overdue">欠费明细</li>
                            <li value="1" class="bis_fact_unclick" id="fact">收费历史记录</li>
                            <li value="4" class="bis_fact_unclick" id="advances">预收明细</li>
                            <!--<li   value="5" id="payNoti" >缴费通知</li>-->
                            <li value="6" class="bis_fact_unclick" id="payIncome">营业外收入</li>
                            <li value="7" class="bis_fact_unclick" id="budget">经营预算明细</li>
                            <li value="9" class="bis_fact_unclick" id="gysfRecord">公寓收费记录</li>
                        </ul>
                        <input type="hidden" id="dimension" name="dimension" value="${dimension}"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>
<div align="left" style="margin: 10px 10px 10px 10px;" id="falt_record_div">
    <span valu='n' style="color:#464646">&nbsp;</span>
    <span valu='4' style="color:#FF6500;cursor: pointer;padding-right: 8px;">物管费</span>|
    <span valu='5' style="color:#666666;cursor: pointer;padding-left: 8px;padding-right: 8px;">水费</span>|
    <span valu='6' style="color:#666666;cursor: pointer;padding-left: 8px;">电费</span>
	<font style="font-weight:bolder;float: right;margin-right: 5px;">单位：元</font>
</div>

<%-- 模板导出、数据导入 --%>
<div id="batchOper" style="display:none;clear:both;margin:5px;padding:10px;border-bottom:1px solid #dddbdc;background:#f7f7f7;">
     <table class="tb_search">
     		<col width="100"/>
     		<col width="50"/>
     		<col width="120"/>
     		<col width="80"/>
     		<col width="130"/>
     		<col width="50"/>
     		<col width="120"/>
     		<col width="45"/>
     		<col width="120"/>
     		<col/>
		 <tr style="margin-bottom: 10px;">
		 	<td style="font-weight: bolder;">1、导出模板：</td>
		 	<td class="td_title"><font color="red">*</font>项目：</td>
		 	<td>
				<input type="text" id="bisProjectName2" value="${bisProjectName}" style="width:120px;height:20px;" readonly="readonly"/>
				<input type="hidden" id="bisProjectId2" value="${bisProjectId}"/>
			</td>
			<td class="td_title"><font color="red">*</font>费用类别：</td>
			<td>
				<s:select cssStyle="width:120px;"
						  list="@com.hhz.ump.util.DictMapUtil@getMapGyCostType()" 
						  listKey="key" 
						  listValue="value" 
						  name="costType2" 
						  id="costType2"></s:select>
			</td>
			<td class="td_title"><font color="red">*</font>年月：</td>
			<td>
				<input class="Wdate" type="text" id="yearMonth" name="yearMonth" style="width:120px;" onfocus="WdatePicker({dateFmt:'yyyy-MM'})"/>
			</td>
			<td class="td_title">楼号：</td>
			<td>
				<input type="hidden" id="bisFloorId2"/>
				<input type="text" class="pd-chill-tip" id="bisFloorName2" readonly="readonly" style="width:120px;" onclick="showDiv(true,2);" onmouseout="bindInputBlur('out',2);"/>
				<div id="selectFloor2" style="display: none;" onmouseover="bindInputBlur('over',2);" onmouseout="bindInputBlur('out',2);">
					
				</div>
			</td>
			<td><input type="button" class="btn_blue" style="margin-left:10px;width:65px;" onclick="gysfExportTemplate();" value="导出模板"/></td>
		</tr>
		<tr>
		 	<td valign="top" style="font-weight: bolder;">2、导入数据：</td>
			<td colspan="9">
				&nbsp;&nbsp;&nbsp;&nbsp;1、请用上面导出模板导入数据；<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;2、每月可随时导入费用数据， 后一次导入的数据会自动覆盖前一次的数据；<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;3、模板中黄色项为必填项，请注意数据的填写格式；<br/>
			</td>
		</tr>
		<tr>
		 	<td>&nbsp;</td>
			<td colspan="6">
			   <form id="bisFlatRecordForm" action="${ctx}/bis/bis-flat-record!importDataPoi.action" method="post" enctype="multipart/form-data">
					&nbsp;&nbsp;&nbsp;&nbsp;4、选择文件：
					<input type="file" id="importFile" name="importFile" style="line-height:22px;height:22px;"/>
					&nbsp;
					<input type="button" class="btn_green fact_dime" style="width:65px;margin-bottom: 5px;" onclick="gysfImportData();" value="导入数据"/>
				</form>
			</td>
		</tr>
	</table>
</div>

<div id="welcom" style="clear:both;width:100%">
    <div style="color:#6BAD42;font-size:16px;font-weight:bold;width:auto;margin-top:200px;text-align:center;color: white;">
    </div>
</div>

<script type="text/javascript" src="${ctx}/resources/js/bis/bis.fact.js"></script>
<script type="text/javascript">
    var currProjectId = '${bisProjectId}';
    var currProjectName = '${bisProjectName}';
    var dimension = '${dimension}';

    $(function(){
		adjustHeight();
        $('#bisProjectId').val(currProjectId);
        //注册维度切换事件以及渲染样式，注册快速搜索事件
        init();
        gysfInit();
        initFloor('1'); //初始化查询界面楼号
        initFloor('2'); //初始化导出界面楼号
        loadDate();
        
    });
    
    //查询数据
    function loadDate(costType){
    	$("#selectFloor1").hide();
    	$("#selectFloor2").hide();
        if(costType == null || ""==costType){
			costType="4"; //4代表物管费(默认)
        }
        $("#costType2").val(costType);
        var flatNo = $("#flatNo").val();
        var bisFloorId = $("#bisFloorId1").val();
        var data = {
       		bisProjectId:currProjectId,
       		flatNo:flatNo,
			bisFloorId:bisFloorId,
			costType:costType
		};
        var url = "${ctx}/bis/bis-flat-record!list.action";
        TB_showMaskLayer("正在搜索,请稍候...");
    	$.post(url,data,function(result){
    		TB_removeMaskLayer();
    		$('#welcom').html(result);
    		//等待加载页面之后，再调整页面高度
    		adjustHeight();
    	});	
    }

   	//导出模板
    function gysfExportTemplate(){
    	var bisProjectId = $("#bisProjectId2").val();
    	var bisProjectName = $("#bisProjectName2").val();
    	var costType = $("#costType2").val();
    	var yearMonth = $("#yearMonth").val();
        var bisFloorId = $("#bisFloorId2").val();
    	if(isEmpty(bisProjectId) && isEmpty(bisProjectName)) {
    		alert("请选择项目");
    		return false;
    	}
    	if(isEmpty(costType)) {
    		alert("请选择费用类别");
    		return false;
    	}
    	if(isEmpty(yearMonth)) {
    		alert("请选择年月");
    		return false;
    	}
    	var url = "${ctx}/bis/bis-flat-record!exportTemplate.action?bisProjectId="+bisProjectId
    				+"&costType="+costType+"&yearMonth="+yearMonth+"&bisFloorId="+bisFloorId;
    	TB_showMaskLayer("正在导出...",5000);
    	location.href = url;
    }

   	//导入数据模板
    function gysfImportData() {
    	if(isEmpty($("#importFile").val())) {
    		alert("请先选择要导入的文件");
    		$("#importFile").focus();
    		return false;
    	}
    	if(confirm("确定导入？")){
    		TB_showMaskLayer("正在导入...");
	    	$("#bisFlatRecordForm").ajaxSubmit(function(result){
	    		TB_removeMaskLayer();
	    		var msg = result.split(",");
	    		if(msg[1] == "success") {
	    		    alert("导入成功：共新增"+msg[2]+"条记录，更新"+msg[3]+"条记录，耗时"+msg[4]+"秒");
	    		    $("#importFile").val("");
	    		    loadDate();
	    		} else {
	    			alert("导入失败："+msg[2]);
	    		}
	    	});
    	}
   	}
       	
    
    //批量导入导出公寓收费记录
    function batchOper() {
    	$("#selectFloor1").hide();
    	$("#selectFloor2").hide();
        if($("#batchOper").is(":hidden")){
     		$("#batchOper").show();
        }else{
     		$("#batchOper").hide();
        }
    	//等待加载页面之后，再调整页面高度
 		adjustHeight();
    }
    
	//公寓收费记录总计
    function queryRecordCount(){
    	ymPrompt.confirmInfo( {
    		icoCls : "",
    		autoClose:false,
    		message : "<div style='padding:5px;padding-right:15px;' id='flatRecordCountDiv'><img align='absMiddle' src='"
    			+ _ctx + "/images/loading.gif'></div>",
    		width : 850,
    		height : 310,
    		title : "查看公寓收费记录总计",
    		closeBtn:true,
    		afterShow : function() {
    	    	var costType = $("#costType2").val();
    	        var bisFloorId = $("#bisFloorId1").val();
    	        var bisFloorName = $("#bisFloorName1").val();
    	        var flatNo = $("#flatNo").val();
    	        var data = {
    	       		bisProjectId:currProjectId,
    	       		bisProjectName:currProjectName,
    	       		flatNo:flatNo,
    				bisFloorId:bisFloorId,
    				bisFloorName:bisFloorName,
    				costType:costType
    			};
    			var url = _ctx +"/bis/bis-flat-record!loadRecordCount.action";
    			$.post(url,data,function(result){
    				$("#flatRecordCountDiv").html(result);
    			});
    		},
    		handler : function(btn){
    			ymPrompt.close();
    		},
    		btn:[["确定",'ok'],["取消",'cancel']]
    	});
    }

    //注册选择费用类别事件：  物管费   水费   电费 
    function gysfInit(){
    	$('#falt_record_div').find('span').bind('click',
    			function(){
    		if($(this).attr('valu')!='n'){
    			$('#falt_record_div span').each(function(){
    				if($(this).attr('valu')!='n'){
    					$(this).css('color','#666666');
    				}
    			});
    			$(this).css('color','#FF6500');
    			loadDate($(this).attr('valu'));
    		} 
    	}).bind('mouseover',function(){	if($(this).attr('valu')!='n'){	$(this).css('text-decoration','none');	}}).bind('mouseout',function(){	if($(this).attr('valu')!='n')	$(this).css('text-decoration','none');});	
    	
   	}
    
	//初始化楼号div值
    function initFloor(type){
   		var inputs="";
   		var count=0;
   		var bisFloorId="";
   		$("#bisBuildingHid").find("option").each(function(i){
   			var text =$(this).text();
   			var value=$(this).val();
   			var vla = value.split("`");
   			if(value.length>0){
   				var strPorjectId=new Array();
   				strPorjectId =value.split("`");
   				if(strPorjectId[1]==currProjectId){
   					bisFloorId += vla[0]+","; 
   			   		if("1" == type){
	   					inputs+="<input type='button' class='pd-chill-tip btnSelect' value='"+text+"' title='"+text+"' val='"+vla[0]+"' onclick='getFloorId(this,1);'/>";
   			   		}else if("2" == type){
	   					inputs+="<input type='button' class='pd-chill-tip btnSelect' value='"+text+"' title='"+text+"' val='"+vla[0]+"' onclick='getFloorId(this,2);'/>";
   			   		}else if("3" == type){
   			   			var className = "";
   			   			var bisFloorName3 = $("#bisFloorName3").val();
   			   			if(bisFloorName3 != null && "" != bisFloorName3){
	   			   			var bisFloorId3 = $("#bisFloorId3").val();
   	   			   			if(bisFloorId3 != null && "" != bisFloorId3){
	   			   				var bisFloorIds = bisFloorId3.split(",");
   	   			   				var flag = false;
	   			   				for(var j=0;j<bisFloorIds.length;j++){
	   			   					if(vla[0] == bisFloorIds[j]){
	   			   						flag = true;
	   			   						break;
	   			   					}
	   			   				}
	   			   				if(flag){
	   			   					className ="pd-chill-tip btnSelect";
	   			   				}else{
	   			   					className ="pd-chill-tip btnCancel";
	   			   				}
   	   			   			}else{
   	   			   				className ="pd-chill-tip btnSelect";
   	   			   			}
   			   			}else{
   			   				className ="pd-chill-tip btnSelect";
   			   			}
				   		inputs+="<input type='button' class='"+className+"' value='"+text+"' title='"+text+"' val='"+vla[0]+"' onclick='getFloorId(this,3);'/>";
   			   		}
   					count++;
   					//每一行放5个
   					if(count == 5){
   						inputs+="<div style='clear: both;'></div>";
   						count=0;
   					}
   				}
   			}
   		});
   		inputs+="<div style='clear: both;'></div>";
   		if("1" == type){
	   		inputs+="<input type='button' class='btnGreen' value='清空' id='clearBtu1' onclick='clearFloor(1);'/>";
	   		inputs+="<input type='button' class='btnGreen' value='全选' id='selectAllBtu1' onclick='selectAllFloor(1);' style='display:none' />";
	   		inputs+="<input type='button' class='btnGreen' value='反选' id='selectNoBtu1' onclick='selectNoFloor(1);'/>";
	   		inputs+="<input type='button' class='btnGreen' value='确定' onclick='queryData(1);'/>";
   		}else if("2" == type){
	   		inputs+="<input type='button' class='btnGreen' value='清空' id='clearBtu2' onclick='clearFloor(2);'/>";
	   		inputs+="<input type='button' class='btnGreen' value='全选' id='selectAllBtu2' onclick='selectAllFloor(2);' style='display:none' />";
	   		inputs+="<input type='button' class='btnGreen' value='反选' id='selectNoBtu2' onclick='selectNoFloor(2);'/>";
	   		inputs+="<input type='button' class='btnGreen' value='确定' onclick='queryData(2);'/>";
   		}else if("3" == type){
	   		inputs+="<input type='button' class='btnGreen' value='清空' id='clearBtu3' onclick='clearFloor(3);'/>";
	   		inputs+="<input type='button' class='btnGreen' value='全选' id='selectAllBtu3' onclick='selectAllFloor(3);' style='display:none' />";
	   		inputs+="<input type='button' class='btnGreen' value='反选' id='selectNoBtu3' onclick='selectNoFloor(3);'/>";
	   		inputs+="<input type='button' class='btnGreen' value='确定' onclick='queryData(3);'/>";
   		}
   		if("3" != type){
   			$("#bisFloorId"+type).val('');
   	    	$("#bisFloorId"+type).val(bisFloorId);
   		}
   		$("#selectFloor"+type).append(inputs);
    }
	
	//点击查询按钮
	function queryData(type){
		var tmpFloorName = "";
    	$("#selectFloor"+type).find(":input").each(function(i){
    		var className = $(this).attr("class");
    		if('pd-chill-tip btnSelect' == className){
    			tmpFloorName += $(this).val()+",";
    		}
    	});
    	$("#bisFloorName"+type).val(tmpFloorName);
  		$("#bisFloorName"+type).attr('title',tmpFloorName);
  		if("1" == type){
	  		loadDate();
  		}else if("2" == type){
  			$("#selectFloor2").hide();
  		}else if("3" == type){
  			$("#selectFloor3").hide();
  		}
	}
    
    //点击楼号选择
    function getFloorId(dom,type){
    	var tmpFloorId = "";
    	var bisFloorId = $("#bisFloorId"+type).val();
    	var floorId = $(dom).attr("val");
    	if("pd-chill-tip btnSelect" == $(dom).attr("class")){
    		$(dom).removeClass('btnSelect').addClass('btnCancel');
    		if(bisFloorId != null && ""!=bisFloorId){
	    		if(bisFloorId.indexOf(floorId)>=0){
	    			tmpFloorId = bisFloorId.replace(floorId+",", "");
	    			$("#bisFloorId"+type).val(tmpFloorId);
	    		}
    		}
    	}else if("pd-chill-tip btnCancel" == $(dom).attr("class")){
    		$(dom).removeClass('btnCancel').addClass('btnSelect');
    		if(bisFloorId != null && "" != bisFloorId){
	        	$("#bisFloorId"+type).val(bisFloorId+floorId+",");
    		}else{
    			$("#bisFloorId"+type).val(floorId+",");
    		}
    	}
    	showAllOrClear(type);
    }
    
    //显示影藏楼号div
    function showDiv(isShow,type){
    	if(isShow){
    		$("#selectFloor"+type).show();
    	}else{
    		$("#selectFloor"+type).hide();
    	}
    }
    //全选
    function selectAllFloor(type){
    	var floorId = "";
    	$("#selectFloor"+type).find(":input").each(function(i){
    		if("btnGreen" != $(this).attr("class")){
	    		$(this).removeClass('btnCancel').addClass('btnSelect');
	    		floorId += $(this).attr("val")+",";
    		}
    	});
		$("#bisFloorId"+type).val("");
		$("#bisFloorId"+type).val(floorId);
    	$("#selectAllBtu"+type).hide();
    	$("#clearBtu"+type).show();
    }
    //反选
    function selectNoFloor(type){
   		var bisFloorId = $("#bisFloorId"+type).val();
    	$("#selectFloor"+type).find(":input").each(function(i){
	    	var tmpFloorId="";
    		var floorId = $(this).attr("val");
    		if("btnGreen" != $(this).attr("class")){
	    		if("pd-chill-tip btnSelect" == $(this).attr("class")){
	        		$(this).removeClass('btnSelect').addClass('btnCancel');
	        		if(bisFloorId != null && ""!=bisFloorId){
	    	    		if(bisFloorId.indexOf(floorId) >= 0){
	    	    			tmpFloorId = bisFloorId.replace(floorId+",", "");
	    	    			bisFloorId = tmpFloorId;
	    	    		}
	        		}
	    		}else if("pd-chill-tip btnCancel" == $(this).attr("class")){
	        		$(this).removeClass('btnCancel').addClass('btnSelect');
	        		if(bisFloorId != null && "" != bisFloorId){
	        			bisFloorId = bisFloorId+floorId+",";
	        		}else{
	        			bisFloorId = floorId+",";
	        		}
	    		}
    		}
    	});
       	$("#bisFloorId"+type).val(bisFloorId);
    	showAllOrClear(type);
    }
    //清空
    function clearFloor(type){
    	$("#selectFloor"+type).find(":input").each(function(i){
    		if("btnGreen" != $(this).attr("class")){
    			$(this).removeClass('btnSelect').addClass('btnCancel');
    		}
    	});
   		$("#bisFloorId"+type).val('');
    	$("#clearBtu"+type).hide();
    	$("#selectAllBtu"+type).show();
    }
    //控制显示‘清空’、‘全选’按钮
    function showAllOrClear(type){
    	var count = 0;
    	var count2 = 0;
    	$("#selectFloor"+type).find(":input").each(function(i){
    		count2 ++;
    		if("pd-chill-tip btnSelect" == $(this).attr("class")){
    			count++;
    		}
    	});
    	if(count == (count2-4)){
        	$("#selectAllBtu"+type).hide();
        	$("#clearBtu"+type).show();
    	}else{
        	$("#clearBtu"+type).hide();
        	$("#selectAllBtu"+type).show();
    	}
    }
    
  	//绑定楼号input框事件
    function bindInputBlur(mouse,type){
    	if('over'== mouse){
    		$("#bisFloorName"+type).unbind();
    	}else if("out" == mouse){
    		$("#bisFloorName"+type).bind('blur',function(){
    			showDiv(false,type);
    		});
    	}
    }

</script>
</body>
</html>