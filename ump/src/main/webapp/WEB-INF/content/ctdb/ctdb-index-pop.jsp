<%@ page contentType="text/html;charset=UTF-8" %>

<div id="listMenu" class="hideDiv">
	<div id="newList" onclick="doNewList();" class="newList"><font style="vertical-align: middle;padding-top: 5px;">新增</font></div>
	<div id="listList"  onclick="doListList();" class="listList"><font style="vertical-align: middle;">搜索</font></div>
</div>

<script language="javascript">
$(function(){
	//收起
	$(document).bind('click',function(event){
		var event  = window.event || event;
		var obj = event.srcElement ? event.srcElement : event.target;
		if( $(obj).attr("id") != 'list'){
			$('#listMenu').slideUp(50);		
		}
	});
});
</script>