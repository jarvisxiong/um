<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div id="searchUserDiv" class="searchUserDiv"></div>
<s:if test="#isMy!=1">
	<script type="text/javascript">
		disapledAll();
		addClickAction('',false);
		addRequireCss(false);
	</script>
</s:if>
<s:else>
	<script type="text/javascript">
		addClickAction('',true);
		addRequireCss(true);
	</script>
</s:else>

<s:if test="(statusCd!=0) && (statusCd!=3)">
</s:if>
<s:else>

</s:else>
<script type="text/javascript">
	$(".inputBorder").filter("input").each(function(i){
	  	$(this).wrap("<span class='pd-chill-tip' title="+$(this).val()+"></span>");
	});
</script>

<s:if test="(statusCd!=0) && (statusCd!=3)">
	<script type="text/javascript">
		// for printing , replace textarea with div	
		$('.contentTextArea').filter("[edit!=true]").each(function(i){
			var str = $(this)[0].value;
			str = str.replace(/\r\n/g,'<br/>');
			$(this).replaceWith('<div class='+'"inputBorder_readOnly"'+'><pre style="white-space:pre-wrap;">'+str+'</pre></div>');	 	
		});
		$('.xheditor-simple').filter("[edit!=true]").each(function(i){
			var str = $(this)[0].value;
			$(this).replaceWith('<div class="orgDiv"><pre style="white-space:pre-wrap;">'+str+'</pre></div>');	 	
		});
		<%--
		//--------------------------//
		$("#templetForm :input").filter(".inputBorder").filter("[edit!=true]").each(function(i){
			var str = $(this)[0].value;
			var width=$(this).css('width');
			str = str.replace(/\r\n/g,'<br/>');
			if (width){
			$(this).replaceWith('<div class='+'"inputBorder_readOnly"'+' style="width:'+width+'">'+str+'</div>');	 	
			}else
			$(this).replaceWith('<div class='+'"inputBorder_readOnly"'+'>'+str+'</div>');	 	
		});
		$("#templetForm :input").filter(".inputBorderNoTip").filter("[edit!=true]").each(function(i){
			var str = $(this)[0].value;
			str = str.replace(/\r\n/g,'<br/>');
			$(this).replaceWith('<span class='+'"inputBorder_readOnly"'+'>'+str+'</span>');	 	
		});
		$("#templetForm :input").filter(".Wdate").filter("[edit!=true]").each(function(i){
			var str = $(this)[0].value;
			str = str.replace(/\r\n/g,'<br/>');
			$(this).replaceWith('<div class='+'"inputBorder_readOnly"'+'>'+str+'</div>');	 	
		});
		--%>
	</script>
</s:if>
<s:else>
<script type="text/javascript">
parseTextArea();
</script>
</s:else>

<script type="text/javascript">
	try{
		$('.singleSelect').userSelect({
			muti:false
		});
		$('.mutiSelect').userSelect({
			muti:true
		});
		$('.orgSelect').orgSelect({
			orgMuti:false
		});
		$('.projSelect').orgSelect({
// 			showProjectOrg:true
			orgMuti:false
		});
	}catch(e){

	}
</script>