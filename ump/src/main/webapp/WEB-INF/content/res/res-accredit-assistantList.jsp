<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
	<script language="javascript" src="${ctx}/js/jquery.js"></script>
	<script language="javascript" src="${ctx}/js/table.js"></script>
	<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<title>授权审批</title>
</head>

<body>
	
	<div id="tableDiv" style="display: block;">
		<table class="commonTable" id="editTable" width="99%">
			<thead>
				<tr>
					<th style="width:20%;">用户名称</th>
					<th style="width:20%;">授权人名称</th>
					<th style="width:60%;">权责</th>
				</tr>
			</thead>
			
			<tbody>
				<s:iterator value="page.result">
					<tr id="main_${resAccreditInfoId}" class="mainTr">
						<td>${userName}</td>
						<td>${accUserName}</td>
						<td>
							<s:if test="isJbpm"><div style="float:left;padding-right:15px;">出差报销</div></s:if>
							<s:if test="isRes"><div style="float:left;padding-right:15px;">网上审批</div></s:if>
						</td>
					</tr>
					<tr id="detail_${resAccreditInfoId}" class="detailTr"  style="display:none">
						<td colspan="4">
							<fieldset>
							    <legend><s:text name="common.detail"/></legend>
							    <form  id="editForm_${resAccreditInfoId}" action="res-accredit!saveAssistant.action" method="post">
							    	<input type="hidden" name="id" value="<s:property value="resAccreditInfoId" />" />
							    	
								    <table class="innerTable" >
								    	<tr >
									    	<td width="20%" align="right">用户名称:</td>
									    	<td width="30%">
									    		<input type="text" name="userName" value="${userName}" readonly="readonly" />
									    		<s:hidden name="userCd" />
									    	</td>
									    	<td width="20%" align="right">授权人名称: </td>
									    	<td width="30%">
									    		<input type="text" name="accUserName" value="${accUserName}" readonly="readonly"  />
									    		<s:hidden name="accUserCd" />
									    	</td>
										</tr>
										<tr>
											<td align="right">权责分配: </td>
											<td colspan="6"> 
										    	<span style="padding-right:10px;"><s:checkbox  name="isJbpm" ></s:checkbox>出差报销</span> 
										    	<span style="padding-right:10px;"><s:checkbox  name="isRes" ></s:checkbox>网上审批</span>
								    		</td>
										</tr>
								    	<tr>
								    		<td colspan="6" align="center"> 
										    	<input type="button" class="buttom" id="btnEdit" onclick="save('${resAccreditInfoId}');" value="<s:text name="common.save"/>" />
										    	<input type="button" class="buttom" id="btnEdit" onclick="delAccreditInfo('${resAccreditInfoId}');" value="<s:text name="common.delete"/>" />
										    	<input type="button" class="buttom" id="btnCancel" onclick="cancelEdit();" value="<s:text name="common.cancel"/>" />
								    		</td>
								    	</tr>
								    </table>
							    </form>
							 </fieldset>
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="5" align="right"><p:page/></td>
				</tr>
				
			</tbody>
		</table>
	</div>
	
	
		
	<script language="javascript">
		$("#btnNew").bind("click", function(){
			$("#divNew").show();
			$("#divToolBar").hide();
			cancelEdit();
		});
		
		$("#editTable tbody tr.mainTr").toggle(function(){
			$(".foldTr").trigger("click");
			$(this).addClass("foldTr");
			$(this).children().eq(0).children().attr("src","${ctx}/images/up.gif");
			$(this).next().show();
			cancelNew();
		},function(){
			$(this).removeClass("foldTr");
			$(this).children().eq(0).children().attr("src","${ctx}/images/down.gif");
			$(this).next().hide();
		});
		
		function cancelNew(){
			$("#divNew").hide();
			$("#divToolBar").show();
		}
		
		function cancelEdit(){
			$(".foldTr").trigger("click");
			$(this).addClass("foldTr");
			$(this).children().eq(0).children().attr("src","${ctx}/images/up.gif");
			$(this).next().show();
		}
		
		function save(resAccreditInfoId) {
			$("#editForm_" + resAccreditInfoId).ajaxSubmit(function(result) {
				 $("#idAssistantList").html(result);
				 //$("#idAssistantForm").resetForm();
			});	
			//$("#editForm_" + resAccreditInfoId).submit();
		}
		
		function delAccreditInfo(resAccreditInfoId) {
			$.post("${ctx}/res/res-accredit!delete.action",
					{ id : resAccreditInfoId },
					 function(result) {
						 if (result) {
							 $("#detail_"+resAccreditInfoId).remove();
							 $("#main_"+resAccreditInfoId).remove();
						 }
			});
		}
		var searchTime;
		function showSearchUser(dom){
			var $currentDom = $(dom);
			var $next = $(dom).siblings();
			
			if(searchTime)clearTimeout(searchTime);
			searchTime = setTimeout(function(){
				var val = $.trim($currentDom.val());
				$next.val("");
				if(val == "") {
					$("#searchUserDiv").slideUp(200);
					return;
				}
				$.post("${ctx}/oa/oa-email!getUsersByFilter.action", {value:val,maxNum:10}, function(result) {
					result = eval(result);
					var $offset = $currentDom.offset();
					$("#searchUserDiv").css({left:$offset.left,top:$offset.top+$currentDom.height() + 5}).empty().show();
					$.each(result, function(i,n) {
						var html = "<div uiid='"+n.uiid+"'><span>"+n.userName +"</span>|<span>"+ n.orgName+"</span></div>";
						$("#searchUserDiv").append(html);
					});
				
					$("#searchUserDiv div").click(function() {
						var userName = $(this).children("span:first").text();
						var uiid = $(this).attr("uiid");
						$currentDom.val(userName);
						$next.val(uiid);
						$("#searchUserDiv").slideUp(200);
					});
				});
			}, 300);
			
			$(document).bind('click', function(event) {
				var event  = window.event || event;
			    var obj = event.srcElement ? event.srcElement : event.target;
			    if(obj != dom){
			    	$("#searchUserDiv").slideUp(200);
			    	if($next.val() == ''){
			    		$currentDom.val('');
			    	}
			    }
			    $(document).unbind('click');
			});
			return false;
		}
	</script>
</body>
</html>
