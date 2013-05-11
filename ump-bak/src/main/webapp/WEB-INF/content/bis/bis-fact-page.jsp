<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<style type="text/css">
.table_pager {
	float: right;
	margin: 5px 0;
}
.table_pager input{width:24px;height:24px;border:1px solid #ccc;}
#pageTo{width:24px;height:22px;}
</style>
		  <div style="width: 100%;" id="pageDiv">
			<div class="table_pager">
				<p:page />
			</div>
		  <!--
			&nbsp;共有&nbsp;${voPage.totalCount}&nbsp;条记录&nbsp;&nbsp;&nbsp;&nbsp;
			当前第 ${voPage.pageNo}/${voPage.totalPages}&nbsp;页
							 <s:if test="page.hasPre">
								<img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_up.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_up_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_up.gif');" onclick="jumpPage('${voPage.prePage}');"/>
							 </s:if>
							 <s:else>
							 	<img align="absmiddle" src="${ctx}/images/desk2/page_up_grey.gif" />
							 </s:else>
							 <s:if test="page.hasNext">
								<img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_down.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_down_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_down.gif');" onclick="jumpPage('${voPage.nextPage}');"/>
							 </s:if>
							 <s:else>
							 	<img align="absmiddle" src="${ctx}/images/desk2/page_down_grey.gif" />
							 </s:else>
							 <input type="text" id="pageNo" style="height: 15px; width: 20px; text-align: center;" value="${voPage.pageNo}"/>
							 <a href="#" onblur="this.blur();" onclick="jumpPage($(this).prev().val()); return false;">GO</a>
					
			-->
			</div>
<script language="javascript">
	try{
		$("#pageDivTo").html($("#pageDivFrom").html());
		$("#pageDivFrom").html("");
	}catch(e){}
	try{
		//$("#pageHtml").html($("#pageDiv").html());
		//$("#pageDiv").html("");
	}catch(e){}
</script>
