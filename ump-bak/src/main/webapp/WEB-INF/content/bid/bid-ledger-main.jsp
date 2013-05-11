<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>成本投标平台</title>
	
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
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
	
 </head>
<body>

	
<%--Title_bar end --%>
<table cellpadding="0" cellspacing="0" border="0" style="width:100%;min-height: 450px;">
	<tr>
		<%--树 --%>
		<td id="leftPanel"
			style="width:100px;border-right: 1px solid #8c8f94; background-color: #E4E7EC;"
			valign="top">
			<table cellpadding="0" cellspacing="0" border="0" style="width: 100%;">
				<tr>
					<td>
						<div id="leftTreePanel" class="leftPanel"
							style="padding-top: 5px; float: left; overflow-y: auto; overflow-x: hidden; width: 170px; border: none;">							
						</div>
					</td>
					<td style="width: 5px;">
						<div id="noteResizeHandler" class="btn_drag">&nbsp;
						</div>
					</td>
				</tr>
			</table>
		</td>	
		<%--树 end--%>	
		<td valign="top" style="background-color:white">
			<div id="content"> 
				<%--
				
				 --%>
			</div>
		</td>
	</tr>
</table>
	  
<script type="text/javascript">

var mainUtil = {
	refreshFrame: function(){
		var ifm= document.getElementById("bidLedgerFrame");
		var subWeb = document.frames ? document.frames["bidLedgerFrame"].document : ifm.contentDocument;
		if(ifm != null && subWeb != null) {
			ifm.height = subWeb.body.scrollHeight;
		}
	}
};
$(function(){
    
	resetLeftPanel();

    loadMainTree();

  //左右拖拉
    $('#leftPanel').resizable({
        handler: '#noteResizeHandler',
        min: { width: 150, height: ($('#leftPanel').height()) },
        max: { width: 400, height: ($('#leftPanel').height()) },
        onResize: function(e) {
        	$('#leftTreePanel').width($('#leftPanel').width()-5);
        }
    });
    
	var frame = '<iframe src="${ctx}/bid/bid-ledger.action" name="bidLedgerFrame" id="bidLedgerFrame" onLoad="iFrameHeight()" style="width:100%;min-height: 550px;overflow: hidden;border:0;" frameborder=”no” border=”0″ marginwidth=”0″ marginheight=”0″ scrolling=”no” allowtransparency=”yes” scrolling="no"/>';
	$('#content').append($(frame));

});



//主页面
//加载招标类型树
var gTreePanel;
function loadMainTree(){
	var url = _ctx + '/bid/bid-ledger!getMainTree.action';
	$.post(url, {}, function(result){
		gTreePanel = new TreePanel({
			renderTo:'leftTreePanel',
			'root' : eval('('+result+')'),
			'ctx':_ctx
		});
		gTreePanel.render();
		gTreePanel.isExpendSelect = true;
		//监听事件
		gTreePanel.addListener("check",function(node){
			
		});
		gTreePanel.on(function(node){
			var nodeType = node.attributes.nodeType;
			var bidLedgerType = node.attributes.id;		
			//1-非根节点 
			if("1" != nodeType){
				if(node.isExpand){
					node.collapse();
				}else{
					node.expand();
				}
                 //工程
				if("0"== bidLedgerType){
					var frame = '<iframe src="${ctx}/bid/bid-ledger.action" name="bidLedgerFrame" id="bidLedgerFrame" onLoad="iFrameHeight()" style="width:100%;min-height: 550px;overflow-y: visable;border:0;" frameborder=”no” border=”0″ marginwidth=”0″ marginheight=”0″ scrolling=”no” allowtransparency=”yes” scrolling="no"/>';
					$('#content').html($(frame));
					}
				//战略
				else if("1" == bidLedgerType){
					var frame = '<iframe src="${ctx}/bid/strategy-bid-ledger!input.action" name="bidLedgerFrame" id="bidLedgerFrame" onLoad="iFrameHeight()" style="width:100%;min-height: 550px;overflow-y: visable;border:0;" frameborder=”no” border=”0″ marginwidth=”0″ marginheight=”0″ scrolling=”no” allowtransparency=”yes” scrolling="no"/>';
					$('#content').html($(frame));
					}
				//searchBidLedger();
			}
		}); 

		//searchBidLedger();
	});
} 




function iFrameHeight() {
	var ifm= document.getElementById("bidLedgerFrame");
	var subWeb = document.frames ? document.frames["bidLedgerFrame"].document : ifm.contentDocument;
	if(ifm != null && subWeb != null) {
		ifm.height = subWeb.body.scrollHeight;
	}
} 


</script>

</body>
</html>