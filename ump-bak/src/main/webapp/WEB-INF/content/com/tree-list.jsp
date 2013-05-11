<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/com/tree-list.css"/>
</head>
<body>
	<c:set var="createDivFlg"></c:set>
	<c:set var="preLevelCd"></c:set>
	
	<s:iterator value="rowList" var="vo">
		
		<c:if test="'start' == vo.level">
			<div>
		</c:if>
			 
		<div class="treerow" levelcd="${vo.level}" nodecd="${vo.nodeCd}" nodename="${vo.nodeName}">
			${vo.level}${vo.nodeName}[${vo.nodeCd}]
		</div>
		
		<c:if test="'start' == vo.level">
			</div>
		</c:if>
		
	</s:iterator>
	
	<script type="text/javascript">
		$(function(){
			for(var i=1; i<=10; i++){
				$('div[levelcd='+i+']').each(function(){
					$(this).css({"padding-left": ((i*30)+"px")});
					if(i > 1){
						$(this).css({"display": "none"});
					}
					$(this).toggle(
						function(){
							var tmpLevel = $(this).attr('levelcd');
							$('div[levelcd='+(parseInt(tmpLevel)+1)+']').show();
						},
						function(){
							var tmpLevel = $(this).attr('levelcd');
							$('div[levelcd='+(parseInt(tmpLevel)+1)+']').hide();
						}
					);
				});
			}
		});
	</script>
</body>
</html>