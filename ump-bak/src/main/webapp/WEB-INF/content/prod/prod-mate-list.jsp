<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>工料价格指数管理</title>
	
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/prod/prod.css"  />
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>		
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prod/prod.js"></script>
</head>
<body>
	<div id="prodcontainer">
		<div class="title_bar">
			<div class="fLeft banTitle">供料价格指数管理</div>
			
			<div class="fRight">
			
				<security:authorize ifAnyGranted="A_PROD_ADMIN,A_PROD_BASIC">
					<%--暂时移出 产品业态权重2011-12-27--%>
					<%--<li value="业态产品权重" onclick="bussinessWeight();" class="common_btn" style="background-color: #6eb1cf;border-color: #45738d;"><font style="font-size: 12;color: white;">产品业态权重</font></li> --%>					
					<input type="button" style="width:90px;" value="工料基准版本" onclick="versionDetail();" class="btn_new btn_blue_new" />
				</security:authorize>	
				<security:authorize ifAnyGranted="A_PROD_ADMIN,A_PROD_PRICE">		
					<input type="button" style="width:90px;" value="工料价格维护" onclick="matePrice();" class="btn_new btn_blue_new" />
				</security:authorize>
					
				<input type="button" class="btn_new btn_full_new" onclick="window.open(location.href);" value="全屏" />
				<input type="button" class="btn_new btn_green_new" onclick="window.location.href=location.href" value="刷新"/>
			</div>
		</div>
		<div id="nav">
			<security:authorize ifAnyGranted="A_PROD_ADMIN,A_PROD_VIEW">
				<div id="menuDiv" class="menuDiv">
					<ul class="prodMenu">
						<li  onclick="priceIndex();" id="bntShowPriceIndex" title="点击以显示工料价格指数">工料价格指数</li>				
						<li  onclick="priceIndexChart2('0');" id="priceIndexChart" style="width: 100px;" title="点击以显示工料指数曲线">工料指数曲线</li>
						<li  onclick="constructPrice('2');" id="constructPrice" style="width: 100px;" title="点击以显示建安单价造价">建安单价造价</li>
						<li  onclick="priceIndexChart2('1');" id="matePriceChart" style="width: 100px;" title="点击以显示工料价格曲线">工料价格曲线</li>
					</ul>
					<div style="float: right;display: none;margin-right: 10px;" id="pisearchBtn">
						<input type="button" value="搜索" id="btn_search" onclick="priceIndexForm();" class="btn_new btn_query_new"/>
					</div>		
				</div>
			</security:authorize>
		    
		</div>
		
		<div id="divBody" style="clear: both;margin-top: 30px;">
			<div id="reportRs" style="margin-left: 10px;margin-right: 10px;"></div>
		</div>
	</div>
	  
<script type="text/javascript">
	$(function(){		
		//价格指数表单控制
		//menuHover();	
		//控制菜单颜色
		$("li").click(function(){
				$("#pisearchBtn").show();
				$(this).addClass('prodMenu-click').siblings().removeClass('prodMenu-click');
				//工料指数曲线					
				if('priceIndexChart'==$(this).attr('id')){
					$("#btn_search").attr('onclick','priceIndexChartForm("priceIndexChart");');
					}
				//工料价格指数
				if('bntShowPriceIndex'==$(this).attr('id')){
					$("#btn_search").attr('onclick','priceIndexForm();');
					}
				//工料价格曲线						
				if('matePriceChart'==$(this).attr('id')){
					$("#btn_search").attr('onclick','matePriceChartForm();');
					}
				//建安单价造价（与工料指数曲线共用同一个方法）						
				if('constructPrice'==$(this).attr('id')){
					$("#btn_search").attr('onclick','priceIndexChartForm("constructPrice");');
					}						
			});
		});
	//搜索价格指数
	function priceIndex(){				
		var url=_ctx+"/prod/prod-report!priceIndexTwo.action";
		var data={};
		$.post(url,data,function(result){		
			$("#reportRs").html(result);
			autoHeight();
			});
	}
	//价格指数表格搜索弹出窗
	function priceIndexForm(){		
		ymPrompt.confirmInfo({
			icoCls : "",
			autoClose:false,
			message : "<div id='searchFormDiv'><img align='absMiddle' src='"
				+ _ctx + "/images/loading.gif' style='border-radius: 5px 5px 5px 5px;'></div>",
			width : 300,
			height : 200,
			title : "工料价格指数搜索",
			closeBtn:true,
			afterShow : function() {
					var url=_ctx+"/prod/prod-report!searchForm.action";
					var data={formType:'priceIndex'};
					$.post(url,data,function(result){		
						$("#searchFormDiv").html(result);	
						autoHeight();
						});
			},
			handler : function(btn){
				if(btn=='ok'){
					$.getScript('${ctx}/resources/js/jquery/jquery.form.pack.js',function(){
					$('#pisearchForm').ajaxSubmit(function(result){
						$('#reportRs').html(result);	
						autoHeight();
					});
					});
				}
				ymPrompt.close();
			},
			btn:[["搜索",'ok'],["取消",'cancel']]
		});
	}
	//价格指数表格搜索弹出窗
	//建安单价造价曲线搜索弹出窗共用
	function priceIndexChartForm(formType){
		var title='';
		if("priceIndexChart"==formType){
			title='工料指数曲线搜索';
			}
		else{
			title='建安单价造价曲线搜索';
			}		
		ymPrompt.confirmInfo({
			icoCls : "",
			autoClose:false,
			message : "<div id='searchFormDiv'><img align='absMiddle' src='"
				+ _ctx + "/images/loading.gif' style='border-radius: 5px 5px 5px 5px;'></div>",
			width : 300,
			height : 200,
			title : "工料指数曲线搜索",
			closeBtn:true,
			afterShow : function() {
					var url=_ctx+"/prod/prod-report!searchForm.action";
					var data={formType:'priceIndex'};
					$.post(url,data,function(result){		
						$("#searchFormDiv").html(result);
						autoHeight();
						});
			},
			handler : function(btn){
				if(btn=='ok'){
					$.getScript('${ctx}/resources/js/jquery/jquery.form.pack.js',function(){ 
						var dt=$("#inp_ym").val();
						var bussinessCd=$("select#sele_bussinessCd :selected").val();
						var bussinessCdTxt='19-32层住宅';
						if(bussinessCd)
							bussinessCdTxt=$("select#sele_bussinessCd :selected").html();
						//工料指数曲线搜索
						if("priceIndexChart"==formType){
							viewChart('0',dt,bussinessCd,'',bussinessCdTxt);
							}
						//建安单价造价曲线搜索
						else{
							viewChart('2',dt,bussinessCd,'',bussinessCdTxt);
							}	
						
					} );
				}
				ymPrompt.close();
			},
			btn:[["搜索",'ok'],["取消",'cancel']]
		});
	}

	//工料价格曲线搜索弹出窗
	function matePriceChartForm(){		
		ymPrompt.confirmInfo({
			icoCls : "",
			autoClose:false,
			message : "<div id='searchFormDiv'><img align='absMiddle' src='"
				+ _ctx + "/images/loading.gif' style='border-radius: 5px 5px 5px 5px;'></div>",
			width : 300,
			height : 200,
			title : "工料价格曲线搜索",
			closeBtn:true,
			afterShow : function() {
					var url=_ctx+"/prod/prod-report!searchForm.action";
					var data={formType:'matePriceChart'};
					$.post(url,data,function(result){		
						$("#searchFormDiv").html(result);	
						autoHeight();
						});
			},
			handler : function(btn){
				if(btn=='ok'){
					$.getScript('${ctx}/resources/js/jquery/jquery.form.pack.js',function(){ 
						var dt=$("#inp_ym").val();
						var bussinessCd=$("select#sele_bussinessCd :selected").val();
						var mateZoneCd=$("select#sele_mateZoneCd :selected").val();
						//工料范围
						var mateZoneCdTxt='(人工)';
						if(mateZoneCd)
							mateZoneCdTxt='('+$("select#sele_mateZoneCd :selected").html()+')';
						//业态
						var bussinessCdTxt='19-32层住宅';
						if(bussinessCd)
							bussinessCdTxt=$("select#sele_bussinessCd :selected").html();
						bussinessCdTxt=bussinessCdTxt+mateZoneCdTxt
						viewChart('1',dt,bussinessCd,mateZoneCd,bussinessCdTxt);
					} );
				}
				ymPrompt.close();
			},
			btn:[["搜索",'ok'],["取消",'cancel']]
		});
	}
	//价格指数曲线图
	function priceIndexChart2(mateZoneCdFlg){
		var url=_ctx+"/prod/prod-report!priceIndexChart.action";
		//var data={year:2011,month:02,areaCd:1};
		var data;
		$.post(url,data,function(result){		
				$('#reportRs').html(result);
				priceIndexChart2Data(mateZoneCdFlg);
				autoHeight();		
			});
	}
	//建安单价造价曲线图
	function constructPrice(mateZoneCdFlg){
		var url=_ctx+"/prod/prod-report!priceIndexChart.action";
		var data;
		$.post(url,data,function(result){		
			$('#reportRs').html(result);
			priceIndexChart2Data(mateZoneCdFlg);	
			autoHeight();	
		});
		}
	//获取工料价格指数数据
	function priceIndexChart2Data(mateZoneCdFlg){
		//默认业态为"1"-19-32层住宅,工料为"1"-人工
		if('0'==mateZoneCdFlg)
			viewChart(mateZoneCdFlg,"","1","1",'19-32层住宅');
		else if('2'==mateZoneCdFlg)
			viewChart(mateZoneCdFlg,"","1","1",'19-32层住宅');
		else
			viewChart(mateZoneCdFlg,"","1","1",'19-32层住宅(人工)');
		}
	
	//暂时不启用
	function priceIndexChart(){
		ymPrompt.confirmInfo({
			icoCls : "",
			autoClose:false,
			message : "<div id='searchFormDiv'><img align='absMiddle' src='"
				+ _ctx + "/images/loading.gif'></div>",
			width : 300,
			height : 220,
			title : "价格指数曲线图搜索",
			closeBtn:true,
			afterShow : function() {
					var url=_ctx+"/prod/prod-report!searchForm.action";
					var data={formType:'priceIndexChart'};
					$.post(url,data,function(result){		
						$("#searchFormDiv").html(result);	
						autoHeight();
						});
			},
			handler : function(btn){
				//叉叉关闭事件
				$("div.ymPrompt_close").click(function(){					
					ymPrompt.close();
					});
				if(btn=='ok'){					
					//验证搜索表单
					if(validateSearchForm2()){
						//验证是否存在激活版本号
						findNewVersion();				
						viewChart();
						ymPrompt.close();
					}
				}
				if(btn=='cancel'){
					ymPrompt.close();
					}
				
			},
			btn:[["搜索",'ok'],["取消",'cancel']]
		});
		}
	
	//收缩表单
	function toggleForm(){
		$("#searchForm").slideToggle();
		}
	
	//点击表单显示控制
	function menuHover(){
		$("#priceIndexChart").toggle(function(){
			$("#searchForm").slideDown();
		}
		,function(){
			$("#searchForm").slideToggle();
		}
		);			
	}

	//验证空
	function  isEmpty(str) {
		return (typeof (str) === "undefined" || str === null || (str.length === 0));
	}
	//寻找最新版本
	function findNewVersion(){
		//验证是否存在激活的当前比对版本
		var url=_ctx+"/prod/prod-basic-version!findNewVersion.action";
		$.post(url,{},function(result){
			var rs=result.split(",");		
			if('success'==rs[0]){
				return true;	
				}
			else{
				alert('无激活版本号,请激活一个版本号,才能搜索相应数据和图表!');
				return false;
				}	
			});
		}
	//权重表单验证
	function validateSearchForm2(){
		//判空		
		if(($("#input_areaCd").val().trim().length<1)&&($("#input_bussiness").val().trim().length<1)){
			alert('地区与产品业态必选其一！');
			return false;
			}
		//验证时间
		var fromDate = $("#input_dateFrom_3").val();
		var toDate = $("#input_dateTo_3").val();
		//判断时间是否为空
		if(!isEmpty(fromDate) && !isEmpty(toDate)) {
			var fArray = fromDate.split("-");
			var tArray = toDate.split("-");
			var fDate = new Date(fArray[0],fArray[1]-1,'01');
			var tDate = new Date(tArray[0],tArray[1]-1,'01');
			if(tDate.getTime()<fDate.getTime()) {
				alert("结束时间不能小于开始时间");
				$("#input_dateTo_3").val("");
				return false;
			}
		}
		return true;
		}
	
	//屏操作
	function openFullWindow(){
		window.open(_ctx+'/prod/prod-mate.action');
	}	
</script>
</body>
</html>