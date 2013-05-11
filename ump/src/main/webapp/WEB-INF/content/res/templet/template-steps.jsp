<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<!--文件审批单-手动增加步骤-->
<style>
<!--
table.addstep{
	width: 100%;
}
table.addstep td {
	border: none;
	padding:5px 0;
	text-align: left;
}
table.addstep input[type="button"]{
	height: 26px;
}
table.addstep input[type="text"]{
	width: 95%;
}
.auditPerson{
	border:1px solid #ccc;
	height:22px;
	line-height: 22px;
}
-->
</style>
<s:if test="#isMy==1">
<div align="left">
	<input type="button"  class="btn_blue_75_55" value="增加审批人" onclick="addApprover();" />
</div>
<div style="width:400px;margin:auto;" class="noprint">
	<div style="text-align:left;">
		<div  style="display: none;" >
		</div>
		<s:set var="approveUserCount"><s:property value="approverUsers.size()"/></s:set>
		<table  id="tbApprover" style="width:100%;border:none;" class="addstep noprint" <s:if test="#approveUserCount==0">style="display: none;"</s:if>>
			<col width="30">
			<col width="150">
			<col width="20">
			<col width="30">
			<col width="30">
			<tr id="trApprover" class="noprint trApprover" style="display: none;margin-bottom:5px;" >
				<td align="right"  class="lable" >步骤<span class="lableIndex">1</span>:</td>
				<td>
					<input class="auditPerson singleSelect tr_input" id="cloneUserNames" index="0" readonly="readonly" type="text" name="templateBean.approverUsers[0].approverUserName"/>
					<input class="tr_input" type="hidden" id="cloneUserCds" index="0" name="templateBean.approverUsers[0].approverUserCd"/>
				</td>
				<td><a href="javascript:void(0)" onclick="delApprover(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a></td>
				<td><input type="button" onclick="moveUp(this);" class="btn_blue_auto" value="上移"/></td>
				<td><input type="button" onclick="moveDown(this);" class="btn_blue_auto" value="下移"/></td>
			</tr>
			
			<s:iterator value="approverUsers" status="s">
			<tr class="noprint trApprover" style="margin-bottom:5px;">
				<td align="right"  class="lable" >步骤<span class="lableIndex">${s.index+1}</span>:</td>
				<td>
					<input class="auditPerson tr_input <s:if test="fixed != 1">singleSelect</s:if>" id="step${s.index}UserNames" index="${s.index}" readonly="readonly" type="text" name="templateBean.approverUsers[<s:property value="#s.index" />].approverUserName" value="${approverUserName}"/>
					<input class="tr_input" type="hidden" id="step${s.index}UserCds" index="${s.index}" name="templateBean.approverUsers[<s:property value="#s.index" />].approverUserCd"  value="${approverUserCd}" />
					<input class="tr_input" type="hidden" index="${s.index}" name="templateBean.approverUsers[<s:property value="#s.index" />].fixed"  value="${fixed}" />
					<input class="tr_input" type="hidden" index="${s.index}" name="templateBean.approverUsers[<s:property value="#s.index" />].nodeCd"  value="${nodeCd}" />
				</td>
				<td class="lable" >
				<s:if test="fixed != 1">
				<a href="javascript:void(0)" onclick="delApprover(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
				</s:if>
				</td>
				<td><input type="button" onclick="moveUp(this);" class="btn_blue_auto" value="上移"/></td>
				<td><input type="button"  onclick="moveDown(this);" class="btn_blue_auto" value="下移"/></td>
			</tr>
			</s:iterator>
		</table>
	</div>
</div>
<div class="clear" ></div>
</s:if>
<script type="text/javascript">
	var trApprover=$("#trApprover").clone();
	$("#trApprover").remove();
	function addApprover(){
		var trApprover_new=trApprover.clone();
		//$(trApprover).show();
		//$("#allApprover").append(trApprover);
		
		index=$(".lableIndex").length;
		$(trApprover_new).show();
		$(trApprover_new).find("#cloneUserNames").attr("id",index+"cloneUserNames").userSelect({
			muti:false
		});
		$(trApprover_new).find("input[type='text']").each(function(i){
			this.name=this.name.replace('0',index);;
			this.id=this.id.replace('0',index);
			$(this).attr("index",index);
		});
		$(trApprover_new).find("input[type='hidden']").each(function(i){
			this.name=this.name.replace('0',index);
			this.id=this.id.replace('0',index);
			$(this).attr("index",index);
		});
		$(trApprover_new).find("#cloneUserCds").attr("id",index+"cloneUserCds");
		$("#tbApprover").show();
		$(trApprover_new).children(".lable").children(".lableIndex").html(index+1);
		$("#tbApprover").append(trApprover_new);
	}
	function delApprover(dom){
		$(dom).parent().parent().remove();
		refreshIndex();
	}
	
	function moveUp(dom){
		var _tr=$(dom).parents("tr:first");
		var _trPre=_tr.prev();
		if (_trPre.css("display")!='none'){
			_tr.insertBefore(_trPre);
			refreshIndex();
		}
	}
	function moveDown(dom){
		var _tr=$(dom).parents("tr:first");
		var _trNext=_tr.next();
		_tr.insertAfter(_trNext);
		refreshIndex();
	}
	function refreshIndex(){
		$(".lableIndex").each(function(i){
			$(this).html(i+1);
		});
		$(".trApprover").each(function(i){
			$(this).find(".tr_input").each(function(j){
				var cur_index=$(this).attr("index");
				$(this).attr("index",i);
				this.name=this.name.replace(cur_index,i);
				this.id=this.id.replace(cur_index,i);
			});
		});
	}
		
</script>
