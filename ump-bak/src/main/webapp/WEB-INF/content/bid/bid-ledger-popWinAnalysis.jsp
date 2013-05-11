<%@ page contentType="text/html;charset=UTF-8" %>

<%-- 弹出框 --%>
<div id="popWinAnalysis" class="popWinAnalysis">
	<div onclick="popWinAnalysis(1)">量/价比较</div>
	<div onclick="popWinAnalysis(2)">占总价比例分析</div>
	<div onclick="popWinAnalysis(3)">平方米指标</div>
	<div onclick="popWinAnalysis(4)">投标总价汇总比较</div>
	<div onclick="popWinAnalysis(5)">清单轮次差异</div>
	<div onclick="popWinAnalysis(6)">标段工程汇总</div>
</div>

<script language="javascript">
$(function(){
	//收起
	$(document).bind('click',function(event){
		var event  = window.event || event;
		var obj = event.srcElement ? event.srcElement : event.target;
		if( $(obj).attr("id") != 'btnShowPop'){
			$('#popWinAnalysis').slideUp(50);
		}
	});
});
</script>
