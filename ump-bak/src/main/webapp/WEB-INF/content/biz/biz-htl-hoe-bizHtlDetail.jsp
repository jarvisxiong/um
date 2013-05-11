<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<form action="${ctx}/biz/biz-htl-hoe!save.action" method="post" id="bizHtlDetaForm" >
<input type="hidden" id="recordVersion" name="recordVersion" value="${entity.recordVersion}"/>
<input type="hidden" id="id" name="id" value="${entity.bizHtlHoeId}"/>
<input type="hidden" id="d_hoeTypeCd" name="hoeTypeCd" value="${entity.hoeTypeCd}"/>
<input type="hidden" id="d_hotelCd" name="hotelCd" value="${entity.hotelCd}"/>
<input type="hidden" id="bizHoeAttaId" name="bizHoeAttaId" value="${entity.bizHoeAttaId}" />
<div id="bizHtlDetail" style="margin-left: 0px;">
		 <table class="shop-table">
	          <col width="100"/>
		      <col />
			  <col width="100"/>
			  <col />
			<tr>
			     <td><font style="color:red;">*</font>酒店名称</td>
			     <td><input type="text" name="entity.hotelName" id="d_hotelName" validate="required" value="${entity.hotelName}"/></td>			
			  	<td><font style="color:red;">*</font>品名类别</td>
		      	<td><input type="text" name="entity.hoeTypeName" id="d_hoeTypeName" validate="required" value="${entity.hoeTypeName}" /></td>
			</tr>
			<tr>
			   <td><font style="color:red;">*</font>供应商名称</td>
			   <td colspan="3"><input type="text"    name="entity.supName" validate="required" value="${entity.supName}"/></td>
			</tr>
			<tr>
			   <td><font style="color:red;">*</font>HOE名称</td>
		       <td><input type="text" name="entity.hoeName" validate="required" value="${entity.hoeName}" /> </td>
			   <td>规格型号</td>
		       <td><input type="text" name="entity.hoeModel" value="${entity.hoeModel}"/> </td>
			 </tr>
			 <tr>
			   <td>数量</td>
		       <td><input type="text"   name="entity.hoeAmount" alt="amount"  maxlength="9" value="${entity.hoeAmount}"/> </td>
		  	   <td>序号</td>
		 	   <td><input type="text" name="entity.sequenceNo"  alt="amount" maxlength="9" value="${entity.sequenceNo}"/></td>
			 </tr>
			 <tr>
			   <td>单价</td>
			   <td><input type="text"  name="entity.singelPrice" alt="amount"  maxlength="9" value="${entity.singelPrice}"/></td>
			   <td>总价</td>
			   <td><input type="text" name="entity.totalPrice"  alt="amount"  maxlength="9" value="${entity.totalPrice}"/></td>
			 </tr>
			  <tr>
			   <td>说明</td>
		       <td colspan="3" rowspan="3"><textarea  style="width:100%;height:100px;background-color: #DBE5F1;border: none;" name="entity.remark" >${entity.remark}</textarea></td>
			 </tr>
			 <tr></tr>
			 <tr></tr>
			 <tr>
	 			<td align="right">酒店Hoe附件:</td>
				<td colspan="3">
				  <div class="atta_readOnly" style="float:left;cursor: pointer;display: none;"  onclick="showAttachment('${entity.bizHoeAttaId}')">
					   <s:if test="bizHoeAttaList.get(0)==0">
						<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
						</s:if>
						<s:else>
						<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" />
						</s:else>
						查看附件
				 </div>
				 <div class="atta_edit" style="float:left;cursor: pointer;"  onclick="openAttachment(this,'${entity.bizHoeAttaId}','bizHoeAttaId')">
					   <s:if test="bizHoeAttaList.get(0)==0">
						<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta.gif" />
						</s:if>
						<s:else>
						<img align="top"  id="agree_atta_id" style="margin-top:5px;" src="${ctx}/resources/images/common/atta_y.gif" />
						</s:else>
						添加附件
				  </div>
				</td>
			 </tr>
		 </table>
</form>

<script type="text/javascript">
<security:authorize ifNotGranted="A_BIZ_HOTEL_ADMIN">
$("#bizHtlDetaForm").find(":text,textarea").css('backgroundColor','#dddbdc');
$("#bizHtlDetaForm").find(":text,textarea").attr('readOnly',"readOnly");
$(".atta_edit").hide();
$(".atta_readOnly").show();
</security:authorize>
$("#d_hotelName").quickSearch("${ctx}/biz/biz-htl-hoe!quickSearch.action",['dictName'],{dictCd:'d_hotelCd',dictName:"d_hotelName"});
$("#d_hoeTypeName").quickSearch("${ctx}/biz/biz-htl-hoe!quickSearchHoe.action",['dictName'],{dictCd:'d_hoeTypeCd',dictName:"d_hoeTypeName"});$(function(){
	//$('tbody tr td').removeAttr('onclick');
});
function showAttachment(entityId){
	ymPrompt.win({
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=bizHtlHoe&filterType=image|office&onlyShow=true",
		width:500,
		height:300,
		title: '附件查看',
		afterShow:function(){},
		iframe:true
	});
}
function openAttachment(dom,entityId,hiddenId,title){
	ymPrompt.close();
	var _title = title||'上传附件';
	if(!entityId||entityId == ''){
		//var hiddenId = $(dom).children(":hidden");
		if($.trim($('#'+hiddenId).val()) == ''){
			entityId = 'agree_'+curr_user_cont+'_'+String((new Date().getTime()) ^ Math.random());
			$('#'+hiddenId).val(entityId);
		}else{
			entityId = $('#'+hiddenId).val();
		}
	}
	ymPrompt.win({
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=bizHtlHoe&filterType=image|office",
		width:500,
		height:300,
		title: _title,
		afterShow:function(){},
		iframe:true
		});
}
</script>
