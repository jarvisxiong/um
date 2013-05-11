<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>成本投标台账</title>
	
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bid/bid.css"  />	
	<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/mailStyle.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
		
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bid/bid-ledger-tech.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
</head>
<body>
	<div class="title_bar">
		<div class="fLeft banTitle">技术标</div>
		<input type="button" class="fRight btn_new btn_refresh_new" onclick="window.location.href=location.href" value="刷新" />
	</div>
	<div style="text-align: center;line-height: 40px;margin-top: 10px;">
		<font style="font-size: 26px;font-weight: bold;color: #333;">${bidLedger.orgName}/</font>
		<font style="font-size: 26px;font-weight: bold;color: #0167a2;">${bidLedger.bidSectionName}</font>
	</div>
	
	<div id="headLine2" style="padding:3px;"  > 		
 		<div style="clear: both;text-align: center">
		<form id="submitJudgeForm" method="post" action="${ctx}/bid/bid-tech-item!saveJudge.action">
			<input type="hidden" name="bidLedgerId" value="${bidLedger.bidLedgerId}"/>
			评审人：
			<!-- 这里input的onchange方法无效 -->
			<input type="text" name="judgeName" id="judgeName" value="${bidLedger.judgeName}" class="selectInput"/>
			<input type="hidden" name="judgeCd" value="${bidLedger.judgeCd}"/>
			评审时间：
			<input type="text" name="judgeDate" id="judgeDate" onfocus="WdatePicker()" value='<s:date name="bidLedger.judgeDate" format="yyyy-MM-dd"/>' class="Wdate" 
			<security:authorize ifAnyGranted="A_BID_EDIT,A_ADMIN_BID">
				onchange="saveJudgeForm()"
			</security:authorize> />
		</form>		
 		</div>
	</div>
	<div style="text-align: center;height: 20px;float:none;" align="center"><div id="succInfoId" class="resultTip"  style="text-align: center;height: 20px;float:none;" >保存成功！</div></div>
	<%-- 构造表格 --%>
 	<div id="freemarkPanel" style="overflow: auto;clear: both;margin-left: 10px;">
  		<%-- 这里由freemark产生 --%>
 	</div>
 	
	<form id="submitForm" method="post" action="${ctx}/bid/bid-item-sup-rel!save.action">
		<input type="hidden" id="bidItemSupRelId" name="id"/>
		<input type="hidden" id="score" name="score"/>
		<input type="hidden" id="contentDesc" name="contentDesc"/>
		<input type="hidden" id="bidLedgerId" name="bidLedgerId" value="${bidLedgerId}"/>
	</form>
<script>

$(function(){

	//为了使页面不会出现大滚动条
	$('#freemarkPanel').height($(document).height() - $('#headLine1').height() - $('#headLine2').height());

	<security:authorize ifAnyGranted="A_BID_EDIT,A_ADMIN_BID">
	//选择评审人员
    $('.selectInput').userSelect({
		muti: true,
		callback:function(map){
			saveJudgeForm();
		}
	}); 
	</security:authorize>
	
	var url = _ctx+"/bid/bid-tech-item!matrix.action?bidLedgerId="+$("#bidLedgerId").val();//+bidLedgerId;40282996323d6ea301323d71d4ec002b
	$.post(url,function(result){		
		$('#freemarkPanel').html(result);
		//合并单元格
		var size = 0;
		 $('#matrixDownLeftTable tr').find("th:eq(0)").each(function(i,n){
			if($(this).text()=="施工部分"){
				size++;
				$(this).html('施工组织部分<br/>|<br/>90分');
			}
		}); 
		$('#matrixDownLeftTable tr').find("th:eq(0)").each(function(i,n){
			if(i>0 && i <=size){
				if(i==1)
				$(this).attr("rowspan",size);
				else
				$(this).remove();
			}
		});
		$('input[alt="amount"]').live('keyup',function(){
			clearNoNum_1(this);
			if($('.percent').val()>100){
				this.value=0;
			}
		});
		//calculateLength();
	});	 
});
//onchange
function updateSupScore(dom,relId){
	<security:authorize ifAnyGranted="A_BID_EDIT,A_ADMIN_BID">
	var name=$(dom).attr('name');
	//如果是分值，才进行大小判断
	if('score'==name){		
		//判断数值大小
		var start=$(dom).attr('limit').indexOf('（');
		var end=$(dom).attr('limit').indexOf('）');
		var limit=$(dom).attr('limit').substring(start+1,end-1);
		if(''!=limit&&limit.length>0){	
			if(parseFloat($(dom).val())>parseFloat(limit)||parseFloat($(dom).val())<0){
				$(dom).val($(dom).attr('oldval'));
				alert('不能大于'+limit);
				return ;	
			}		
		}
	}
	var value = $(dom).val().trim();	
	var data={id:relId};
	if(name=='score'){
		data['score']=value;
	}if(name=='contentDesc'){
		data['contentDesc']=value;
	}
	var oldVal=$(dom).attr('oldval');
	if(oldVal!=value){
		var url = _ctx + '/bid/bid-item-sup-rel!modify.action';
		$.post(url, data, function(result){
			if ("success" == result) {
				$(dom).attr('oldval',value);
				$('#succInfoId').show().fadeOut(3000);
			} else {
				alert(result);
			}
		});
	}
	</security:authorize>
}
</script>		
</body>
</html>