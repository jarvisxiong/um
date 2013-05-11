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
	<title>授权审批</title>
</head>

<body>
	<div class="search">
		<s:form id="mainForm" action="res-accredit" method="post">
			<fieldset>
			    <legend><s:text name="common.search" /></legend>
			    <div>
			    	用户名称:<s:textfield name="filter_EQS_userCd" id="filter_EQS_userCd" size="18" maxlength="30" />
			    	授权人名称:<s:textfield name="filter_LIKES_userName" id="filter_LIKES_userName" size="18" maxlength="30" />
			      <input type="submit" class="buttom" value="<s:text name="common.search" />" />
			    </div>
			</fieldset>
		&nbsp;<p:page/>
	</s:form>
	</div>
	<div id="tableDiv" style="display: block;">
		<table class="commonTable" id="editTable" width="99%">
			<thead>
				<tr>
					<th style="width:160px;">用户名称</th>
					<th style="width:160px;">授权人名称</th>
					<th style="width:160px;">启用日期</th>
					<th style="width:160px;">停用日期</th>
				</tr>
			</thead>
			
			<tbody>
				<s:iterator value="page.result">
					<tr id="main_${resAccreditInfoId}" class="mainTr">
						<td>${userName}</td>
						<td>${accUserName}</td>
						<td><s:property value="startDate" /></td>
						<td><s:property value="endDate" /></td>
					</tr>
					<tr  id="detail_${resAccreditInfoId}" class="detailTr"  style="display:none">
						<td colspan="4">
							<fieldset>
							    <legend><s:text name="common.detail"/></legend>
							    <form  id="editForm_${resAccreditInfoId}" action="res-accredit!save.action" method="post">
							    	<input type="hidden" name="id" value="<s:property value="resAccreditInfoId" />" />
							    	
								    <table class="innerTable" >
								    	<tr >
									    	<td width="100" align="right">用户名称:</td>
									    	<td width="200">
									    		<input type="text" name="userName" value="${userName}" onkeyup="showSearchUser(this)" />
									    		<s:hidden name="userCd" />
									    	</td>
									    	<td width="100" align="right">授权人名称: </td>
									    	<td>
									    		<input type="text" name="accUserName" value="${accUserName}" onkeyup="showSearchUser(this)" />
									    		<s:hidden name="accUserCd" />
									    	</td>
										</tr>
										<tr >
									    	<td align="right">启用日期:</td>
									    	<td width="20%">
									    		<input type="text" name="startDate" value="<s:property value="startDate" />" onfocus="WdatePicker({minDate:'%y-%M-%d'})"/>
									    	</td>
									    	<td align="right">停用日期: </td>
									    	<td width="20%">
									    	
									    		<input type="text" name="endDate"
									    			value="<s:property value="endDate" />" onfocus="WdatePicker({minDate:'%y-%M-%d'})"/>
									    	</td>
									    </tr>
									    <tr>
									    	<td align="right">是否有效: </td>
											<td colspan="3">
												<input type="radio" name="activeFlg" value="1" <s:if test="activeFlg == 1">checked="checked"</s:if> />&nbsp;可用&nbsp;&nbsp;
												<input type="radio" name="activeFlg" value="0" <s:if test="activeFlg == 0">checked="checked"</s:if> />&nbsp;不可用
											</td>
										</tr>
								    	<tr>
								    		<td colspan="6">
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
			</tbody>
		</table>
	</div>
		
	<div id="divNew" style="display: none;" class="clear">
		<fieldset>
		    <legend><s:text name="common.create"/></legend>
			<div>
				<s:form id="createForm" action="res-accredit!save.action" >
					<s:hidden name="isJbpm" value="true"/>
					<table class="mainTable">
						<tr>
					    	<td width="100" align="right">用户名称:</td>
					    	<td width="200">
					    		<input type="text" name="userName" value="" onkeyup="showSearchUser(this);"/>
					    		<s:hidden name="userCd" />
					    	</td>
					    	<td width="100" align="right">授权人名称: </td>
					    	<td>
					    		<input type="text" style="width: 200px;" name="accUserName" value="" onkeyup="showSearchUser(this);"/>
					    		<s:hidden name="accUserCd" />
					    	</td>
						</tr>
						<tr>
					    	<td align="right">启用日期:</td>
					    	<td>
					    		<input type="text" name="startDate" style="width: 200px;" onfocus="WdatePicker({minDate:'%y-%M-%d'})" />
					    	</td>
					    	<td align="right">停用日期: </td>
					    	<td>
					    		<input type="text" name="endDate" style="width: 200px;" onfocus="WdatePicker({minDate:'%y-%M-%d'})" />
					    	</td>
						</tr>
						<tr>
							<td align="right">是否有效: </td>
							<td colspan="3">
								<input type="radio" name="activeFlg" value="1"/>&nbsp;可用&nbsp;&nbsp;
								<input type="radio" name="activeFlg" value="0"/>&nbsp;不可用
							</td>
						</tr>
				    	<tr>
				    		<td colspan="4">
						    	<input type="button" style="margin-left: 250px;" class="buttom" id="btnEdit" onclick="$('#createForm').submit();" value="<s:text name="common.save"/>" />
						    	<input type="button" class="buttom" id="btnCancel" onclick="cancelNew();" value="<s:text name="common.cancel"/>" />
				    		</td>
				    	</tr>
					</table>	
				</s:form>
			</div>
		</fieldset>
	</div>
	
	<div id="divToolBar">
		<input type="button"  class="buttom" id="btnNew" type="button" value="<s:text name="common.create"/>" />
	</div>
	
	<div id="searchUserDiv" class="searchUserDiv"></div>
	
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
			$("#editForm_" + resAccreditInfoId).submit();
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
