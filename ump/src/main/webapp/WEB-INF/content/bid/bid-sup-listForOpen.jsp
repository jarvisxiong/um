<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<table class="commonTable" cellpadding="0" cellspacing="0" border="0">
			 	<col width="40"/>
			 	<col/>
				<col width="110"/>
				<col width="110"/>
				<col width="110"/>
				<col width="110"/>
				<col width="110"/>
				<col width="110"/>
				<col width="110"/>

			<thead>
			 	<tr>
			 		<th class="first">序号</th>
			 		<th title="投标单位名称"><div class="partHide  ellipsisDiv_full">投标单位</div></th>
			 		<th >报价总金额</th>
			 		<th >百分比</th>
			 		<th >排序</th>			 		
			 		<th >投标工期</th>
			 		<th >前一轮报价金额</th>	
			 		<th >两轮报价差额</th>		 		
			 		<th >备注</th>
			 	</tr>
				</thead>
				<tbody>				
				<s:iterator value="listBidSupVo" var="vo" status="status" >
					<!-- 显示概要 -->
					<%-- onmouseover="$(this).click();" --%>
					<tr class="supBrief" id="supBrief_${bidSupId}" >
		  			  	<td style="text-align: center;" <s:if test="#status.index==listBidSupVo.size()-1"> class='lastRowTd' </s:if>>
		  			  		<div ><%--class="arrowDirectDrop" --%>
		  			  			<s:if test="typeCd==2"></s:if>
		  			  		<s:else>
		  			  			<s:property value="#status.index+1"/>
		  			  		</s:else>	
		  			  		<input type="hidden" name="bidOpenRecordSups[${status.index}].bidSup.bidSupId" value="${bidSupId}"/>	  			  			
		  			  		</div>
		  			  	</td>
						<td  <s:if test="#status.index==listBidSupVo.size()-1"> class='lastRowTd' </s:if> title='${bidSupName}'>
							<div class="partHide ellipsisDiv_full" >
											${bidSupName}
											<input type="hidden" name="bidOpenRecordSups[${status.index}].supName" value="${bidSupName}">
							</div>
						</td>
						<td><input name="bidOpenRecordSups[${status.index}].totalPrice" value="" width="100" type="text"  /></td>
						<td><input name="bidOpenRecordSups[${status.index}].percent" value="" width="100" type="text" /></td>
						<td><input name="bidOpenRecordSups[${status.index}].sequence" value="" width="100" type="text" /></td>
						<td><input name="bidOpenRecordSups[${status.index}].timeLimit" value="" width="100" type="text"  /></td>
						<td><input name="bidOpenRecordSups[${status.index}].prePriece" value="" width="100" type="text"/></td>
						<td><input name="bidOpenRecordSups[${status.index}].difference" value="" width="100" type="text"/></td>
						<td><textarea name="bidOpenRecordSups[${status.index}].contentDesc" style="border:1px solid #999999;height:25px;" ></textarea></td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
 <input type="hidden" name="supCnt" id="hidden_supCnts" value="<s:property value="listBidSupVo.size"/>"></input>  	

<script type="text/javascript">

//点击"新增"行时事件，显示/隐藏新增表单
$('#btnAddSup').toggle(
	function(){
		$("#trAddSup").show();
	},function(){
		$("#trAddSup").hide();
	}
);
$(function(){
	
});	

//编辑联系人信息
 function editSup(id){
 	TB_showMaskLayer("正在保存...");
 	var linkman=$("#input_linkman_"+id).val();
	$('#form_'+id).ajaxSubmit(function(r){
		//更新联系人信息
		$("#linkman_"+id).html(linkman);
		$("#tlinkman_"+id).html(linkman);						 
		TB_removeMaskLayer();
		showSuccessInfo('supSuccessInfo','保存成功！');
	});
}
//切换联系人【暂时不用】
function changeContact(dom,refSupId,bidSupId){	
	var jdom=$(dom);
	var position = jdom.position();	
	var top=position.top-500;
	var url=_ctx+"/bid/bid-sup!loadSupContactor.action";
	$.post(url, {supBasicId : refSupId,bidSupId:bidSupId}, function(result) {
		TB_removeMaskLayer();
		$("#contactWin").attr('top',top+'px');
		$("#contactWin").attr('left',position.left+100+'px');
		$("#contactWin").html(result).show();
	});	
}
//保存投标单位
function saveNewBidSup(bidLedgerId){
	var sid=$("#sid").val();
	if(sid){
			$("#newSupForm").ajaxSubmit(function(result){
				var rs=result.split(',');
				if('success'==rs[0]){
					loadBidSup(bidLedgerId);
					}
				else{
					alert(rs[1]);
					}
			});
		}else{
				alert("请选择供应商！");
			}
	
}
//删除投标单位
function doDeleteSup(bidSupId,bidLedgerId){
	if(window.confirm('确认删除此投标单位?')){
		var url = _ctx+"/bid/bid-sup!delete.action";
		$.post(url,{bidSupId:bidSupId,bidLedgerId:bidLedgerId}, function(result) {
			var rs=result.split(',');
			if('success'==rs[0]){
				alert('删除成功');
				loadBidSup(rs[1]);
				}
		});
	}
	}
//切换联系人【暂时使用】
function changeCont(dom,refSupId,bidSupId){
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='bizPurchaseDiv' style='overflow:hidden;'><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
		width : 540,
		height : 330,
		title : "请选择单位联系人",
		closeBtn:true,
		afterShow : function() {
			var url = _ctx+"/bid/bid-sup!loadSupContactor.action";
			$.post(url,{supBasicId : refSupId,bidSupId:bidSupId}, function(result) {
				$("#bizPurchaseDiv").html(result);

			});
		},
		handler : function(btn){
			if(btn=='ok'){
				var url=_ctx+"/bid/bid-sup!chooseContactor.action";
				var supContactorId=$("div[condiv='condiv'][class*='choosed']").attr('contactor');
				if(typeof(supContactorId)=='undefine'||supContactorId==''){
						alert('请选择一位联系人！');
						return false;
					}
				$.post(url, {bidSupId : bidSupId,supContactorId:supContactorId}, function(result) {
					var rs=result.split(",");
					if(rs[0]=='success'){
							cancelChoose();
							loadBidSup(rs[1]);
						}
					
				});	
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["取消",'cancel']]
	});
}
 </script>

