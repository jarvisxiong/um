<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>

<%@ include file="/common/meta.jsp"%>
<script type="text/javascript"> 
function say(){
	var content= $("#comment").val();
	if($.trim(content)==''){
		$("#comment").css("border","1px solid red");
	}else{
		$.post("${ctx}/oa/oa-man-info!say.action",{content:content,id:'${oaManInfoId}'},
			 function(result) {
			 $("#msgContent").html(result);
		});
		$("#comment").css("border","0px solid red");
	}
}
</script>
		<div>
			<table class="showTable content_table" id="showTable" width="100%" style="">
				<s:iterator value="oaManInfoMessages">
					<tr class="mainTr">
						<td style="padding-left:5px;width:50px;background-color:#F7F7F7;" id="creator"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %></td>
						<td style="padding-left:5px;background-color:#F7F7F7;" id="msgContent">${msgContent}</td>
						<td style="padding-left:5px;width:120px;background-color:#F7F7F7;" id="createdDate" ><s:date name="createdDate" format="yyyy-MM-dd HH:mm" /></td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div style="width:98%; padding:5px;">
			<table class="showTable" id="showTable" style="width:90%;table-layout: auto;">
				<tr class="detailTr">
					<td>
						发表留言<span style="color:red;"></span>
					</td>
					<td style="width:130px;">&nbsp;</td>
				</tr>
				<tr class="detailTr">
					<td style="padding:5px;">
						<s:textarea cssStyle="width:100%;height:51px;" name="comment" id="comment" />	
					</td>
					<td style="width:130px;padding-left:10px;">	
						<input type="button" value="发言" class="btn_blue_55_55" style="border:none;" onclick="say();" />
					</td>
				</tr>
			</table>
		</div>
