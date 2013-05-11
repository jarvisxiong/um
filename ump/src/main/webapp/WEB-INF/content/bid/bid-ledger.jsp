<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>成本投标平台（工程）</title>
	
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bid/bid.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/css/resApprove.css" />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bid/bid-ledger-pop.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bid/bid-ledger.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js" ></script>
	
	<style type="text/css">
		.add_panel th{
			text-align: right;
			line-height: 25px;
			padding-left: 5px;
			width:60px;
			padding:3px;
		}
		.add_panel td{
			text-align: left;
			line-height: 25px;
			padding-left: 5px;
			width:150px;
			padding:3px;
		}
		
		table .required {
    -moz-border-bottom-colors: none;
    -moz-border-image: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-color: #999999 #999999 #999999 red;
    border-style: solid;
    border-width: 1px 1px 1px 2px;
    height: 20px;
    }
     
     #newBidLedger{
    /* background: none repeat scroll 0 0 #E4E7EC;*/
    border: 1px solid #CBCBCB;
    display: block;
    margin: 10px 0;
    padding: 10px 0;
     } 
	</style>
</head>
<body>

	<%-- 弹出框 --%>
	<%@ include file="bid-ledger-popWinAnalysis.jsp"%>

	<div class="title_bar">
		<div class="fLeft">
			<div class="fLeft banTitle">投标平台（工程）</div>
			<div class="fLeft">
				<s:url id="bidLedgerManual" action="download" namespace="/app"  includeParams="none"  >
			  	  	<s:param name="fileName">bidLedgerManual.doc</s:param>
			  	  	<s:param name="realFileName">招标平台(非战略)操作手册.doc</s:param>
			  	  	<s:param name="bizModuleCd">public</s:param>
				</s:url>
				<a href="${bidLedgerManual}" title="点击下载" style="color:blue;">招标操作手册</a>
			</div>
			<div class="fLeft">
				<s:url id="supbidVedio" action="download" namespace="/app"  includeParams="none"  >
			  	  <s:param name="fileName">supbidVedio.rar</s:param>
			  	  <s:param name="realFileName">供应商操作视频(官网).rar</s:param>
			  	  <s:param name="bizModuleCd">public</s:param>
				</s:url>
				<a href="${supbidVedio}" title="点击下载" style="color:blue;">供应商操作视频</a>
			</div>
		</div>
		
		<div class="fRight">
			<input id="quickBidSenior" type="button" class="btn_new btn_senior_new" value="高级搜索" style="width:80px;"/>
			<input id="newBnt" type="button" class="btn_new btn_add_new" value="新增" style="width:80px;"/>
			<security:authorize ifAnyGranted="A_ADMIN_BID">
			<input type="button" class="btn_new btn_blue_new" style="width:80px" onclick='manageRole();' style="width:80px" value="权限管理"/>
			</security:authorize>		
			<input type="button" class="btn_new btn_full_new" onclick="window.open(location.href)" style="width:80px;margin-left:-0.2px" value="全屏"/>
			<%-- <input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href" style="width:80px;margin-left:-0.2px" value="刷新"/>--%>
		</div>
	</div>
	
	<%@ include file="bid-ledger-mainProject.jsp"%>
	  
<script type="text/javascript">
	$(function(){
	//项目
		$('#projectName').orgSelect({showProjectOrg:true});
	//注册事件
		$('#quickBidSenior').toggle(
			function(){
				$('#seniorPanel').show();
				$('#quickBidSenior').attr('cl','1');
				if($("#newBnt").attr('cl')=='1')
					$("#newBnt").trigger('click');
				$("#newBidLedger").hide();			
				$(this).addClass('quickSeniorExt').removeClass('quickSenior');
			},
			function(){
				$('#seniorPanel').hide();
				//('#quickBidSenior').attr('cl','2');
				$(this).addClass('quickSenior').removeClass('quickSeniorExt');
			}
		);
	//选择跟进人
		$('.selectInput').userSelect({
			muti: false
		});
		//新增标段台帐
		$("#newBnt").toggle(
				function(){
					$("#newBidLedger").show();
					$("#newBnt").attr('cl','1');
					if($('#quickBidSenior').attr('cl')=='1')
						$('#quickBidSenior').trigger('click');
					$('#seniorPanel').hide();
					},
				function(){
						$("#projectName").attr("value",'');//清空内容 
						$("#biaoShuNo").attr("value",'');
						$("#biaoDuan").attr("value",''); 
						$(":checkbox").attr("checked","");
						$("#planMoney").attr("value",'');
						$("#newBnt").attr('cl','2');
						$("#newBidLedger").hide();
						}
				);
		
	
	
	//快速搜索
		$('.quickBidItem').click(
			function(){
				$(this).addClass('quickBidItem-click').siblings().removeClass('quickBidItem-click');
				$(this).addClass('spanred').siblings().removeClass('spanred');
				jumpPage();
				}
		);
		resetLeftPanel();
		//左右拖拉
	    $('#leftPanel').resizable({
	        handler: '#noteResizeHandler',
	        min: { width: 150, height: ($('#leftPanel').height()) },
	        max: { width: 400, height: ($('#leftPanel').height()) },
	        onResize: function(e) {
	        	$('#leftTreePanel').width($('#leftPanel').width()-5);
	        }
	    });
		//loadAreaProjectTree();
	    searchBidLedger();

		
	});

	function addClickAction(parentDom){
		var chks;
		if (parentDom){
			chks=parentDom.find(".group");
		}else{
			chks=$(".group");
		}
		chks.click(function(){
			if($(this).attr('checked')){
				$(this).siblings().attr('checked',false);
				var curName=$(this).attr("name");
				$(this).parents(".chkGroup").find(".group").each(function(i){
					var tmpName=this.name;
					if (tmpName!=curName){
						$(this).attr('checked',false);
					}
				});
			}
		});
	}
	addClickAction();
	$("#templetForm *").filter("[validate*=required]").addClass("required");
	$(".require").each(function (i){
		$(this).html("<span style='color:red'>*</span>"+$(this).html());
	});
	
	
</script>

</body>
</html>