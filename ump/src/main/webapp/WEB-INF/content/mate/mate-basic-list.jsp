<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<div id="TableDiv"  style="height: 100%;width:99%">
	<table width="100%" id="table1" class="content_table">
	  <thead>
      <tr style="height: 35px;">
          <th scope="col" align="center"  class="pd-chill-tip" title='公司名称'   	style="background: none;width:120px">  <div class="ellipsisDiv_full" >公司名称</div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='供货单位名称'>  <div class="ellipsisDiv_full" >供货单位名称</div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='合同编号'>  <div class="ellipsisDiv_full" >合同编号</div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='合同名称'>  <div class="ellipsisDiv_full" >合同名称</div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='库存总量'  style="width:80px;">  <div class="ellipsisDiv_full" >库存总量</div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='施工单位实际领用总量' style="width:120px;">  <div class="ellipsisDiv_full" >施工单位实际领用总量</div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='已清算'  style="width:80px;"> <div class="ellipsisDiv_full" >已清算</div></th>
          <th scope="col" align="center"  class="pd-chill-tip" title='未清算'  style="width:80px;"> <div class="ellipsisDiv_full" >未清算</div></th>
          
         <security:authorize ifNotGranted="A_MATE_BASICDEL">
         <th scope="col" align="center"   class="pd-chill-tip" title='操作' style="width:40px;">&nbsp;</th>
         </security:authorize>
		 <security:authorize ifAnyGranted="A_MATE_BASICDEL">
         <th scope="col" align="center"   class="pd-chill-tip" title='操作' style="width:40px;"> <div class="ellipsisDiv_full" >操作</div></th>
         </security:authorize>
      </tr>
	  </thead>
	  <s:iterator value="page.result">
	  <tr class="mainTr" style="height: 35px;">
			<td align="left" onclick="getDetail('${mateCountBasicId}');" class="pd-chill-tip" title='<p:code2name mapCodeName="mateBasicMap" value="projectCd" />'><p:code2name mapCodeName="mateBasicMap" value="projectCd" /> </td>
			<td align="left" onclick="getDetail('${mateCountBasicId}');" class="pd-chill-tip" title='${suppUnitName}'>${suppUnitName}</td>
			<td align="left" onclick="getDetail('${mateCountBasicId}');" class="pd-chill-tip" title='${contNo}'>${contNo} </td>
			<td align="left" onclick="getDetail('${mateCountBasicId}');" class="pd-chill-tip" title='${contName}'>${contName}</td>
			<%--<td align="center" onclick="getDetail('${mateCountBasicId}');"><s:if test="instalBl==true">是</s:if><s:else>否</s:else></td>--%>
			<td align="right" onclick="getDetail('${mateCountBasicId}');" class="pd-chill-tip" title='${stockAll}'>${stockAll}</td>
			<td align="right" onclick="getDetail('${mateCountBasicId}');" class="pd-chill-tip" title='${exeUnitRealUse}'>${exeUnitRealUse}</td>
			<td align="right" onclick="getDetail('${mateCountBasicId}');" class="pd-chill-tip" title='${clearAudit}'>${clearAudit}</td>
			<td align="right" onclick="getDetail('${mateCountBasicId}');" class="pd-chill-tip" title='${noClearAudit}'>${noClearAudit}</td>
			
			
			<td align="left">
				<s:if test="useStatus ==1">
					<font  color='darkgray' >已提交</font>
				</s:if>
				<s:else>
					<security:authorize ifAnyGranted="A_MATE_BASICDEL">
						<input id="delBtn" name="delBtn" style="width: 40px;" class="btn_red" onclick="doBasicDelete('${mateCountBasicId}');" type="button" value ="删除"/>
					</security:authorize>
				</s:else>
			</td>
	  </tr>
	  </s:iterator>
	</table>
	<input type="hidden" id="pageTotal" value="${page.totalCount}" >
	<div class="table_pager" style="margin-top:5px;">
		<p:page />
	</div>	
</div>
<script type="text/javascript">
function doBasicDelete(id){
	if(id!=null){
		if(confirm("确定要删除该条记录吗？")){
			var url="${ctx}/mate/mate-basic!delete.action";
			$.post(url,{id:id},
					function(result) {
				if('success' == result){
					doQueryCont();
				}else{
					alert("删除失败");
					return false;
				}
			});
		}
	}
}
</script>