<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<script type="text/javascript">

function doBizRelaDelete(id){
	if(id!=null){
		if(confirm("确定要删除该条记录吗？")){
			var url="${ctx}/biz/biz-rela-lib!delete.action";
			$.post(url,{id:id},
					function(result) {
				if('success' == result){
					bizRelaSearch();
				}else{
					alert("删除失败");
					return false;
				}
					});
		}
	}
}
function bizRelaEdit(id){

	ymPrompt.confirmInfo( {
			icoCls : "",
			autoClose:false,
			message : "<div id='bizRelaDetailDiv'><img align='absMiddle' src='"
				+ _ctx + "/images/loading.gif'></div>",
			width : 550,
			height : 400,
			title : "编辑详细信息",
			closeBtn:true,
			afterShow : function() {
				var url = _ctx+"/biz/biz-rela-lib!bizRelaDetail.action";
				$.post(url,{id:id}, function(result) {
					$("#bizRelaDetailDiv").html(result);

				});
			},
			handler : function(btn){
				if(btn=='ok'){
						$('#bizRelaDetaForm').ajaxSubmit(function(result) { 
							bizRelaSearch($("#pageNo").val());
						});
				}
				ymPrompt.close();
			},
			btn:[["确定",'ok'],["取消",'cancel']]
		});
	
}

</script>
<div id="TableDiv"  style="height: 100%;width:100% padding-left: 5px; overflow: hidden;">
	<table width="100%" id="result_table" class="content_table" >
	  <thead>
	        <tr class="header">
	            <th scope="col" width="50px;" align="left" style="background: none;">级别</th>
	            <th scope="col" align="left" width="50px;"  >省份</th>
	            <th scope="col" align="left" >地区</th>
	            <th scope="col" align="left" width="50px;" >姓名</th>
	            <th scope="col" align="left" >单位</th>
	            <th scope="col" align="left" >职位</th>
	            <th scope="col" align="left" >中心</th>
	            <th scope="col" align="left" width="50px;">类别</th>
	            <th scope="col" align="left" width="35px;">序号</th>
	            <security:authorize ifAnyGranted="A_BIZ_RELA_SUP,A_BIZ_RELA_ADMIN">
	            <th scope="col" align="left" width="50px;" >操作</th>
	            </security:authorize>
	        </tr>
	  </thead>
	  <tbody>
			  <s:iterator value="page.result">
				    <tr class="mainTr" >
		  	    		<td align="left" class="pd-chill-tip" title='<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapRelaLevel()" value="relaLevelCd"/>'  onclick="bizRelaEdit('${bizRelaLibId}');"  ><div class="ellipsisDiv_full" ><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapRelaLevel()" value="relaLevelCd"/></div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${relaProvinceName}'  onclick="bizRelaEdit('${bizRelaLibId}');"  ><div class="ellipsisDiv_full" >${relaProvinceName}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${relaArea}' onclick="bizRelaEdit('${bizRelaLibId}');"  ><div class="ellipsisDiv_full" >${relaArea}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${relaName}' onclick="bizRelaEdit('${bizRelaLibId}');"  ><div class="ellipsisDiv_full" >${relaName}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${relaUnit}' onclick="bizRelaEdit('${bizRelaLibId}');"  ><div class="ellipsisDiv_full" >${relaUnit}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${relaPos}'  onclick="bizRelaEdit('${bizRelaLibId}');"  ><div class="ellipsisDiv_full" >${relaPos}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${submitCenterName}'  onclick="bizRelaEdit('${bizRelaLibId}');"  ><div class="ellipsisDiv_full" >${submitCenterName}</div></td>
		  	    		<td align="left" class="pd-chill-tip" title='<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapRelaType()" value="relaTypeCd"/>'  onclick="bizRelaEdit('${bizRelaLibId}');"  ><div class="ellipsisDiv_full" ><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapRelaType()" value="relaTypeCd"/></div></td>
		  	    		<td align="left" class="pd-chill-tip" title='${sequenceNo}'  onclick="bizRelaEdit('${bizRelaLibId}');"  ><div class="ellipsisDiv_full" >${sequenceNo}</div></td>
		  	    		<security:authorize ifAnyGranted="A_BIZ_RELA_SUP,A_BIZ_RELA_ADMIN">
		  	    		<td><input id="delBtn" name="delBtn"  class="btn_red" onclick="doBizRelaDelete('${bizRelaLibId}');" type="button" value ="删除"/></td>
		  	    		</security:authorize>
			    	</tr>
			  </s:iterator>
		</tbody>
	</table>
		<div class="table_pager" style="margin-top:5px;">
			<p:page pageInfo="page"/>
		</div>
		 <div style="float:left;font-size:12px;padding-right:20px;line-height:30px;width:100%;">
		<table  class="main_content" border="0" cellpadding="0" cellspacing="0"  style="width:100%;height:100%;table-layout:fixed">
			<col width="11"/>
			<tr>
				<td  align="left" style="padding-left:10px;">
					<security:authorize ifAnyGranted="A_BIZ_RELA_QUERY">
					<s:if test="page.result.size>0">
						<input  class="btn_green"  type="button" onclick="doExportExcel();" value="导出"/>
					</s:if>
					</security:authorize>
					<security:authorize ifAnyGranted="A_BIZ_RELA_SUP">
					 <input   class="btn_green" type="button" onclick="bizRelaReduct();" value="还原"/>
					</security:authorize>
				</td>
			</tr>
		</table>
	</div>
</div>
<script language="javascript">
//$(function(){
//	<security:authorize ifAnyGranted="A_BIZ_RELA_QUERY">
	//$('tbody tr td').removeAttr('onclick');
//	</security:authorize>
//});
</script>