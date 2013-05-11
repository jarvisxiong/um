<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>回标分析</title>
	
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bid/bid.css"  />	
	<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/util/hashmap.js"></script>
	<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
</head>
<body style="overflow: hidden;">
	<%-- 弹出选择项目 --%>
	<div id="popWinTree" class="popWinTree">
		<div id="winTree" class="content"></div>
		<div class="tools">
			<div class="plblue" onclick="reloadSectionPanel(this)">确定</div>
			<div class="plred" onclick="closePopWin('popWinTree')">取消</div>
			<div class="tip">您未选择项目!</div>
		</div>
	</div>

	<div class="bodyHead">
		<div class="headTitle"><font style="font-size: 14px;">回标分析</font></div>
		
		<div class="headContent">
			<div><font style="font-size: 14px;color: red;">正在查看:</font>
				${bidLedger.orgName}项目 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标段：
				<select  id="curSelBidLedgerId" name="bidLedgerId">
				    <s:iterator value="listBidLedgers" var="bl" status="status">
				    		<option value="${bidLedgerId}" 
						    	<s:if test = "bidLedger.bidLedgerId == bidLedgerId">
						    		selected="selected"
						    	</s:if>
				    		>${bidSectionName}</option>
				    	
				    </s:iterator>
				</select>
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;工程：
			<select  id="bidProjectId" style="width: 90px;" name="bidProjectId">
			    <s:iterator value="bidLedger.bidProjects" var="bl" status="status">
			    	<option value="${bidProjectId}">${projectName}</option>
			    </s:iterator>
			</select>
			<font style="font-size: 14px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态:<span style="color:red;">
				<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBidStatus()" value="bidLedger.bidStatusCd"/></span>
			</font>
		</div>
		
	</div>
	
	<div class="mainAnalysis">
		<div class="tools">
			<%-- 分析类型 --%>
			<div style="clear: both;"></div>
			
			<div class="bidPanel" style="margin-left: 5px;">			
				
				<ul id="bid-nav" class="bid-nav">
					<li class="nosel">分析模块:</li>				  
					<li typeCd="1" enablesupflg="yes" nodeValue="quantityAnalysis" style="width: 120px;text-align: center;">量/价比较</li>
					<li typeCd="1" enablesupflg="yes" nodeValue="quantityAnalysis" style="width: 120px;text-align: center;">清单轮次差异分析</li>
				</ul>
			</div>
			
			<%-- 选择投标单位 --%>
			<div style="clear: both;"></div>
			<div class="supPanel" style="display:none;margin-left: 5px;">
				<ul class="sup-nav" id="sup-nav">
					<li class="nosel" style="padding-right:2px;padding-left: 2px;">投标单位:</li>
					<s:iterator value="bss" var="sup" status="index">
					<s:if test="typeCd!=2">
					<li supId="${bidSupId}" style="height: 20px;margin-left: 5px;width: 120px;padding-left: 2px;padding-right: 2px;" title="<s:if test="bidLedger.visableFlg==1">
							${supName}
						</s:if>
						<s:else>
							<%=StringUtils.leftPad(JspUtil.findString("dispOrderNo"), 3, "0") %>
						</s:else>">
					<div class='ellipsisDiv' style="height: 20px;padding-top: 0px;vertical-align: top;margin: 5px;margin-top: 0px;margin-bottom: 0px;line-height: 20px;">
						<s:if test="bidLedger.visableFlg==1">
							${supName}
						</s:if>
						<s:else>
						<%--因为暗标也需要显示，注销 --%>
						<%--<%=StringUtils.leftPad(JspUtil.findString("dispOrderNo"), 3, "0") %> --%>
							${supName}
						</s:else>
						
					</div>
					</li>
					</s:if>
					</s:iterator>
				</ul>
				<div class="compareFieldsPanel" style="float:left;clear:left;margin-left: 0px;">
					<font  style="padding-left: 5px;font-size: 14px;">可选择列:
						<input type="checkbox" name="unitPrice"  id="unitPrice_check"/>单价
						<input type="checkbox" name="totalPrice" id="totalPrice_check"/>合价
						<input type="hidden" id="itemIdsTemp"/>
						<input type="text" style="height: 20px;" readonly="readonly" id="itemNamesTemp" onclick="selectBidCountry();"/>国标选取
					</font>
				</div>
			</div>
			
			
			<%-- 选择报价轮次 --%>
			<div style="clear:both;"></div>
			<div class="batchNoPanel">
			
					<ul class="batch-nav" id="batch-nav">
						<li class="nosel" style="height:20px;padding: 5px;padding-top: 0px;padding-bottom: 0px;vertical-align: middle;margin-bottom: 1px;">投标轮次:</li>
						<s:if test="bidLedger!= null && bidLedger.batchNo!= null ">
						<s:iterator var="counter" begin="1" end="bidLedger.batchNo">		
							<li batchNo="<s:property value="#counter"/>" style="height:20px;padding: 5px;padding-top: 0px;padding-bottom: 0px;vertical-align: middle;margin-bottom: 1px;">第<s:property value="#counter" />轮</li>
						</s:iterator>
						</s:if>
						<%-- 
						<s:if test="bidLedger.batchNo>1">
						<li batchNo="-1"  style="height:20px;padding: 5px;padding-top: 0px;padding-bottom: 0px;">&nbsp末轮&nbsp</li>
						</s:if>
						--%>
					</ul>
					
					<div style="margin-top:1px;">
						<div class="plblue" onclick="submitSearch()">搜索</div>
						<div id="searchTip" class="resultTip"></div>
						
						<%--颜色说明 --%>
						
					</div>
					
					<hr style="clear: both;color: #aaabb0;padding-left: 5px;padding-right: 5px;"></hr>
						
					
					<s:if test="bidLedger.bidSatusCd == 0 || bidLedger.bidSatusCd == 1">
						<div style="display: block;padding:5px;color:red;">提示:本标段未开始投标</div>
					</s:if>
			</div>
			<div style="clear:both;"></div>
		</div>
		<input type="hidden" id="curDocWidth" name="curDocWidth"/>
		<input type="hidden" id="curleftDocWidth" name="curleftDocWidth"/>
		<div style="clear:both;margin-left: 450px;" id="moduleName"></div>
		<%-- 动态刷新 --%>
		<div id="compareResultPanel" class="compareResultPanel" style="foat:left;margin-left: 10px;margin-right: 10px;">
		
		</div>
	</div>
	<div style="display: none;">
		<div  style="display: none;" id="quantityHeight"></div>
	</div>
	
<script language="javascript">

	$(function() {		
		//分析类型
		$('.bid-nav li').click(function(){
			if( 'nosel' != $(this).attr('class')){ 
				$(this).addClass('bid-nav-click').siblings().removeClass('bid-nav-click');
				//切换时先隐藏结果集
				$('#compareResultPanel').hide();
				if('yes' == $(this).attr('enablesupflg')){
					$('.supPanel').show();
				}else{
					$('.supPanel').hide();
				}
				//控制yanse
				if('quantityAnalysis'==$(this).attr('nodevalue')){
					$(".tipHide").removeClass("tipHide").addClass("tipShow");
					}
				else{
					$(".tipShow").removeClass("tipShow").addClass("tipHide");
					}
				//在量价分析时,添加高度和滑扭
				//if('quantityAnalysis'==$(this).attr('nodevalue')){
				//		$("#compareResultPanel").attr('style','overflow: scroll;min-height: 330px;');
				//	}
				//else{
				//		$("#compareResultPanel").attr('style','');
				//	}	
				$("#moduleName").html("");		
			}
		});
		
		//若传入typeCd,则选中(默认1)
		if('${typeCd}' == ''){
			$('.bid-nav>li:first-child').addClass('bid-nav-click');
		}else{
			$('.bid-nav>li[typeCd=${typeCd}]').click();
		}
	
		//投标单位
		$('.sup-nav li').toggle(
			function() {
				if( 'nosel' != $(this).attr('class')){ 
					$(this).addClass('sup-nav-click');
				}
			}, function() {			
				if( 'nosel' != $(this).attr('class')){ 
					$(this).removeClass('sup-nav-click');
				}
			}
		);
		//投标轮次
		$('.batch-nav li').toggle(
			function() {
				if( 'nosel' != $(this).attr('class')){ 
					//如果点击末轮,则将其他的全部设置为未选中
					if("-1"==$(this).attr('batchno')){
						$('.batch-nav li').each(function(){
							if('batch-nav-click'==$(this).attr('class'))							
								$(this).click();
							});
						}else{
							if('batch-nav-click'==$('.batch-nav li:last-child').attr('class')){
								 $('.batch-nav li:last-child').click();
								}					
							}
					$(this).addClass('batch-nav-click');					
				}
			}, function() {		
				if( 'nosel' != $(this).attr('class')){ 
					$(this).removeClass('batch-nav-click');
				}	
			}
		);
		//var width=$(document).width();		
		var width=document.body.clientWidth;			
		$("#curDocWidth").attr('value',width);
		var leftwidth=$("#left_address_div").width();		
		$("#curleftDocWidth").attr('value',leftwidth);
	});

	//颜色标注比较
	function colorCompare(){
		decorateCol('totalPrice');
	}

	//private 
	/**
	 * @param nodetype: 字段属性  eg: totalPrice
	 */
	function decorateCol(nodetype){
		$(".rightDetail tbody tr").each(function(){
			var jqRow = $(this);

			var toppest = null;//dom
			var lowest = null;//dom
			var totalVal = 0;//值
			var avgVal = 0;//值

			var count = 0;
			jqRow.find('td[nodetype*='+ nodetype +']').each(function(){
				//不是第一行
				if(count != 0){ 					
					var tmpDomVal  = $.trim($(this).text());	
					//总数
					if($.trim(tmpDomVal) != ''){
						totalVal += parseFloat(tmpDomVal);
					}
					
					//最大值		
					if(toppest){
						var toppestVal = $.trim(toppest.text());
						if( toppestVal ==''){
							if(tmpDomVal != ''){
								toppest = $(this);
							}
						}else{
							if(tmpDomVal != ''){
								if(parseFloat(toppestVal) < parseFloat(tmpDomVal)){
									toppest = $(this);
								}
							}
						}
						
					}else{
						toppest = $(this);
					}
					
					//最小值	
					if(lowest){
						var lowestVal = $.trim(lowest.text());
						if( lowestVal ==''){
							if(tmpDomVal != ''){
								lowest = $(this);
							}
						}else{
							if(tmpDomVal != ''){
								if(parseFloat(lowestVal) > parseFloat(tmpDomVal)){
									lowest = $(this);
								}
							}
						}
						
					}else{
						lowest = $(this);
					}
				}
				count++;
			});
			
			if(toppest && lowest){
				//alert(toppest.html()+"-"+lowest.html());			
				//循环比较 
				if((count-1) >= 6){
					avgVal = (totalVal - parseFloat(toppest.text()) - parseFloat(lowest.text()))/(count - 2);
				}else{
					avgVal = totalVal/(count-1);
				}
				//合价平均计算回填
				//$(this).find('td[nodetype*='+ nodetype +']:first').text(avgVal.toFixed(1));//.attr("style","color:red;");
				
				var leftVal  = parseFloat(avgVal) * 0.85;
				var rightVal = parseFloat(avgVal) * 1.15;

				//考虑到必须有个最高和最低，所以最后标记红色和绿色
				//红色:最高
				//toppest.attr('class','');
				toppest.addClass("top");
				var top=toppest.html();
				toppest.html("<font color='red'>"+top+"</font>");
				//alert("red:"+top);
				//蓝色:最低
				//lowest.attr('class','');
				lowest.addClass("low");
				var min=lowest.html();
				lowest.html("<font color='blue'>"+min+"</font>");
				//绿色:+/- 15%
				var tIndex = 0;
				jqRow.find('td[nodetype*='+ nodetype +']').each(function(){		
					if(tIndex != 0){
						if($(this).hasClass('top') || $(this).hasClass('low')){
							//ignore
						}else{
							var t  = parseFloat($.trim($(this).text()));	
							if(t<=leftVal || t>=rightVal){
								//alert($(this).attr('class '));
								//$(this).addClass('avg');
								var avgr=$(this).text();
								//alert(avgr);
								$(this).html("<font color='green'>"+avgr+"</font>");
								return false;
							}
						}
					}
					tIndex++;
				});
				
			}
	});
	}

	var gTreePanel;
	//选择项目公司
	function showPopWinTree(dom){
		var url = _ctx + '/bid/bid-ledger!getAreaProjectTree.action';
		$.post(url, {singleFlg: 'true'}, function(result){//单选
			$('#winTree').empty();
			gTreePanel = new TreePanel({
				renderTo:'winTree',
				'root' : eval('('+result+')'),
				'ctx':_ctx
			});
 			gTreePanel.render();
			gTreePanel.isExpendSelect = true;
			gTreePanel.on(function(node){
				var nodeType = node.attributes.nodeType;		
				var nodeCd = node.attributes.entityCd;		
				var nodeName = node.attributes.entityName;		
				//根节点
				if("0" == nodeType){
					$('#popWinTree').attr('selectedNodeCd','');
					$('#popWinTree').attr('selectedNodeName','');
				}
				//项目
				else if('area' == nodeType){
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
					$('#popWinTree').attr('selectedNodeCd','');
					$('#popWinTree').attr('selectedNodeName','');
				}
				//项目
				else if('project' == nodeType){
					$('#popWinTree').attr('selectedNodeCd',nodeCd);
					$('#popWinTree').attr('selectedNodeName',nodeName);
				}
			});  
			
			//弹出窗口
			var _jDom = $(dom);
			var $offset = _jDom.offset();
			$('#popWinTree').css({left:$offset.left,top:$offset.top + _jDom.height()+3}).show();
		});
	}
	//关闭弹出
	function closePopWin(id){
		$('#'+id).slideUp(50);
	}

	//刷新特定项目的标段列表(下拉)
	function reloadSectionPanel(dom){
		var nodeCd = $('#popWinTree').attr('selectedNodeCd');
		if(nodeCd == '' || !nodeCd){
			//alert('请选择项目');
			$(dom).next().next().show().fadeOut(2000);
			return;
		}else{
			$(dom).next().next().hide();
			closePopWin('popWinTree');
			var url = _ctx + '/bid/bid-analysis!listSection.action';
			var data = {bidOrgCd: nodeCd};
			$.post(url, data, function(result){
				$('#sectionPanel').html(result);
			});
		}
	}
	
	//刷新投标列表
	function loadPanelByBidId(selDom){  
		//var bidLedgerId = $("#listSection").find("option:selected").val();
		var bidLedgerId = getSelBidLedgerId();
		var typeCd = $('.bid-nav>li').filter(".bid-nav-click").html();
		document.location.href = _ctx + '/bid/bid-analysis!list.action?typeCd='+typeCd+'&bidLedgerId='+bidLedgerId;
	}

	//获取节点的激活节点数
	function getActiveCnt(selector,cssStyle){
		var cnt=0;
		$("."+selector).each(
			function(){
				var o=$(this);
				if(o.attr("class")==cssStyle){
					batchNoCnt=cnt+1;
					}
				}
		);
		return cnt;
	}	

	function getSelectedSup() {
		var selectedSupIds = "";
		$('#sup-nav li').each(function() {
			if ($(this).attr("class") == "sup-nav-click") {
				selectedSupIds += "," + $(this).attr("supid");

			}
		});
		return selectedSupIds;
	}
	//获取批号
	function getBatchNo() {
		var selectBatchNo = "";
		$('#batch-nav li').each(
				function() {
					if ($(this).attr("class") != null
							&& $(this).attr("class") == "batch-nav-click") {
						selectBatchNo += "," + $(this).attr("batchNo");
					}
				});
		return selectBatchNo;
	}

	function getCheckValue() {
		var properties = "";
		$("input[type=checkbox]").each(function() {
			if ($(this).attr("checked")) {
				properties += "," + $(this).attr('name');
			}

		});
		if (properties.length > 1) {
			properties = properties.substring(1, properties.length);
		}
		return properties;
	}

	function priceTotal() {
		var selectedSupIds = getSelectedSup();
		var selectedBatchnos = getBatchNo();

		var bidLedgerId = getSelBidLedgerId();//'${bidLedger.bidLedgerId}';
		var url = "${ctx}/bid/bid-analysis!totalPriceAnalysis.action";
		var data = {
			bidLedgerId : bidLedgerId
		};
		if (selectedBatchnos) {
			selectedBatchnos = selectedBatchnos.substring(1,
					selectedBatchnos.length);
			data = {
				bidLedgerId : bidLedgerId,
				batchNo : selectedBatchnos
			};
			TB_showMaskLayer("正在分析...");
			$.post(url, data, function(result) {
				TB_removeMaskLayer();
				$('#compareResultPanel').html(result).show();
			});
		}else{
			alert("请选择报价轮次!");
			}
		
	}

	function quantityAnalysis(pageNo) {
		var selectedSupIds = getSelectedSup();
		var selectedBatchnos = getBatchNo();
		var properties = getCheckValue();
		var bidLedgerId = getSelBidLedgerId();
		var itemIdsTemp = $('#itemIdsTemp').val();
		var bidProjectId=$('#bidProjectId').val();
		var url = "${ctx}/bid/bid-analysis!quantityAnalysis.action";

		var data = {
			bidLedgerId : bidLedgerId
		};
		if (selectedBatchnos) {
			alert("selectedBatchnos=="+selectedBatchnos);
			selectedBatchnos = selectedBatchnos.substring(1,selectedBatchnos.length);
			data = {
				bidLedgerId : bidLedgerId,
				batchNo : selectedBatchnos,
				bidProjectId:bidProjectId,
				itemIdsTemp:itemIdsTemp,
				pageNo:pageNo
			};
			if (selectedSupIds) {
				selectedSupIds = selectedSupIds.substring(1, selectedSupIds.length);
				data['supIds'] = selectedSupIds;
			}
			if (properties.length > 0) {
				data['selectProperty'] = properties;
			}
			if (selectedSupIds.split(',').length > 1) {
				data['searchType'] = 'sup';
			} else {
				if(selectedSupIds.split(',').length==1&&selectedBatchnos.split(',').length==1){
					data['searchType'] = 'sup';
				}else{
					data['searchType'] = 'batchNo';
				}
				
			}
			TB_showMaskLayer("正在分析...");
			$.post(url, data, function(result) {
				TB_removeMaskLayer();
				$('#compareResultPanel').html(result).show();
				//颜色着色比较
				colorCompare();
			});
		}else{
			alert("请选择报价轮次!");
			$('#compareResultPanel').html("<font color='red'>选择批次,无数据!</font>");
		}
		
	}

	//获取当前选中的标段ID
	function getSelBidLedgerId(){
		return $("#curSelBidLedgerId").find("option:selected").val();
	}
	//提示
	function showSuccessInfo(id,text){
		$('#'+id).text(text).show().fadeOut(1500);
	}
	function perMeterAnalysis() {
		var selectedSupIds = getSelectedSup();
		var selectedBatchnos = getBatchNo();
		var bidLedgerId = getSelBidLedgerId();		
		var url = "${ctx}/bid/bid-analysis!perMeterAnalysis.action";
		var data = {
			bidLedgerId : bidLedgerId
		};
		if (selectedBatchnos) {
			selectedBatchnos = selectedBatchnos.substring(1,
					selectedBatchnos.length);
			data = {
				bidLedgerId : bidLedgerId,
				batchNo : selectedBatchnos
			};
			TB_showMaskLayer("正在分析...");
			$.post(url, data, function(result) {
				TB_removeMaskLayer();
				$('#compareResultPanel').html(result).show();
			});
		}else{
			alert("请选择报价轮次!");
			}
		
	}

	function bidTotalAnalysis(){
		var selectedSupIds = getSelectedSup();
		var selectedBatchnos = getBatchNo();
		var bidLedgerId = getSelBidLedgerId();//'${bidLedger.bidLedgerId}';
		var url = _ctx+"/bid/bid-analysis!summaryAnalysis.action";
		var data = {
			bidLedgerId : bidLedgerId
		};
		if (selectedBatchnos) {
			selectedBatchnos = selectedBatchnos.substring(1,selectedBatchnos.length);
			data = {bidLedgerId : bidLedgerId,batchNo : selectedBatchnos};
			TB_showMaskLayer("正在分析...");
			$.post(url, data, function(result) {
				TB_removeMaskLayer();
				$('#compareResultPanel').html(result).show();
			});
		}else{
			alert("请选择报价轮次!");
			$('#compareResultPanel').html("<font color='red'>选择批次,无数据!</font>");
			}
		
	}
	
	function submitSearch(){
		var typeCd =$('#bid-nav li[class*=bid-nav-click]').attr('typeCd');
		//投标单位数量
		var supCnt = $('#sup-nav li[class*=sup-nav-click]').length;
		//投标轮次数量
		var batchNoCnt = $('#batch-nav li[class*=batch-nav-click]').length;		
		if(batchNoCnt == 0){
			showSuccessInfo("searchTip","请选择投标轮次!");
		return;
		}
		
		switch(typeCd){
			//量/价比较
			case '1':
				if(supCnt == 0){
					showSuccessInfo("searchTip","请选择投标单位!");
					return;
				}			
				
				if (supCnt > 1 && batchNoCnt > 1){
					showSuccessInfo("searchTip","投标单位与轮次不能同时多选!");
					return ;
				}
				if(batchNoCnt>1){
					showSuccessInfo("searchTip","不能进行多轮次分析!");
					return ;	
					}
				setModuleName('量/价比较');	
				quantityAnalysis(1);			
				break;
			//占总价比例分析
			case '2':
				if(batchNoCnt >1){
					showSuccessInfo("searchTip","只能选择一个投标轮次!");
					return ;
				}	
				setModuleName('占总价比例分析');	
				priceTotal();
				break;
			//平方米指标
			case '3':
				if(batchNoCnt >1){
					showSuccessInfo("searchTip","只能选择一个投标轮次!");
					return ;
				}
				//检查面积
				//alert('检查面积');	
				setModuleName('平方米指标');
				perMeterAnalysis();
				break;
			//投标总价汇总比较
			case '4':
				if(batchNoCnt >1){
					showSuccessInfo("searchTip","只能选择一个投标轮次!");
					return ;
				}
				//设置模块名
				setModuleName('投标总价汇总比较');
				bidTotalAnalysis();
				break;
		}
		initPlanContainerHeight();
	}
	
	//初始化高度
	function initPlanContainerHeight(){
		$("#compareResultPanel").height($(document).height()-300);
		//$("#compareResultPanel").width($(document).width()-660);
	}

	function setModuleName(text){
		$("#moduleName").html("<font style='font-size: 20px;font-weight: bold;'>"+text+"</font>");	
		$("#moduleName").css('margin-left',parseFloat($("#curDocWidth").val())/2-100+'px');	
		}
	
	//全屏
	function fullScreen(url){
		window.open(url);
	}

	//跳转至给定的页面,配合前台的分页搜索
	function jumpPageTo() {
		var index = $("#pageTo").val();
		index = parseInt(index);
		if (index > 0) {
			jumpPage(index);
		}
	}

	//跳转至给定的页面,配合前台的分页搜索
	function jumpPage(pgnumber) {
		quantityAnalysis(pgnumber);
	}
	function selectBidCountry(){
		var bidProjectId=$('#bidProjectId').val();
		
		ymPrompt.win( {
			icoCls : "",
			autoClose:false,
			message : "<div id='bidCountryInputDiv'><img align='absMiddle' src='"
				+ _ctx + "/images/loading.gif'></div>",
				width : 600,
				height : 500,
				title : "国标项目选取",
				closeBtn:true,
				afterShow : function() {
					var url = _ctx+"/bid/bid-country-divisit!list.action";
					$.post(url,{bidProjectId:bidProjectId}, function(result) {
						$("#bidCountryInputDiv").html(result);
					});
				},
				handler : function(btn){
					if(btn=='ok'){
						$('#itemIdsTemp').val($('#itemIdsTemp').val());
						$('#itemNamesTemp').val($('#itemNamesTemp').val());
					}
					ymPrompt.close();
				},
				btn:[["确定",'ok'],["退出",'cancel']]
		});
	}
</script>
</body>
</html>