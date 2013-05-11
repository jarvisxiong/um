<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>员工考勤记录</title>
		<link href="${ctx}/css/desk/desk-oa.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"  />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	</head>
	
	<body style="line-height: 25px;">
		<div class="moduleTtile">
			<div style="float:left; height:29px; padding-left:8px; padding-top:3px;"><img src="${ctx}/images/meetingRoom/pic_chairman_res.gif" style="margin-top:1px;"></img></div>
			<div style="float:left; height:29px;color:#5a5a5a;font-weight: bold;">&nbsp;员工考勤记录</div>
		</div>
		<div style="height:2px;background-color: #5a5a5a;height:1px;margin-bottom:2px;margin-bottom: 8px;"></div>
		<div style="margin:2px">
			<fieldset>
			<div style="height: 35px;line-height:35px;background-color: #eeeeee;padding-left: 10px;">
				<div style="float:left;font-weight: bold;margin-right: 10px;font-size: 14px;color:#316685;">记录列表</div>
				<form action="${ctx}/hr/user-clock-record!list.action" method="post" id="searchForm">
					<div style="float: left; display:inline;">
						<span style="margin-left:15px;margin-right: 5px;">考勤月份</span><input class="date" type="text" style="color:red;width:120px;" name="slotMonth" value="${slotMonth}" onfocus="WdatePicker({dateFmt:'yyyy-MM',onpicked:function(dp){ajaxSearch();}})"/>
					</div>
					<div style="float:left;font-weight: bold;padding-left: 30px;cursor: pointer;">
						<s:checkbox name="isUnusual" onclick="ajaxSearch();"></s:checkbox>只显示异常
					</div>
					<security:authorize ifAnyGranted="A_USER_CLOCK_ADMIN">
					<div style="float: right; display:inline;">
						<a href="javascript:ajaxSearch('all');" style="text-decoration: underline;font-weight: bold;color:#316685;">查看所有</a>
						当前:<span style="padding-right:10px;color:red;font-weight: bold;" id="curUserName"> 
							<s:if test="userCd == null || userCd == ''">
								中心全体人员
							</s:if>
							<s:else>
								<%= CodeNameUtil.getUserNameByCd(JspUtil.findString("userCd")) %>
							</s:else>
						</span>
					</div>
					<input type="hidden" name="userCd" value="${userCd}" id="curUserCd"/>
					</security:authorize>
					
				</form>
			</div>
			<div style="margin:0px 2px;clear: both;min-height: 50px;background-color: #DBDBDB">
				<security:authorize  ifAnyGranted="A_USER_CLOCK_ADMIN">
					<div style="float:left;width:149px;background-color: #fff;" id="centerUserTree" >
						中心人员树
					</div>
				</security:authorize>
				<div 
					<security:authorize ifAnyGranted="A_USER_CLOCK_ADMIN">
					style="margin-left:150px;background-color: #fff;"
					</security:authorize>
					<security:authorize ifNotGranted="A_USER_CLOCK_ADMIN">
					style="background-color: #fff;"
					</security:authorize>
				>
				<div id="resultDiv">
					<%@include file="user-clock-record-list.jsp"%>
				</div>
				</div>
			</div>
	</fieldset>
	</div>
	<script type="text/javascript">
	<security:authorize ifAnyGranted="A_USER_CLOCK_ADMIN">
		var treeId = 'centerUserTree';
		$(function(){
			$("#"+treeId).html('<div style="padding-left:8px;"><image src="${ctx}/images/loading.gif"></div>');
			$.post("${ctx}/hr/user-clock-record!buildCenterTree.action", function(result){
				$("#"+treeId).html('');
				if (result) {
					var orgUserTree = new TreePanel({
						renderTo : treeId,
						'root'   : eval('('+result+')'),
						'ctx'    : '${ctx}'
					});
					//自定义
					orgUserTree.isDelegateIcon = true;
					orgUserTree.delegateGetDelegateIcon = function(node){
						return node.iconPath;
					};
					//修饰所有节点
					for(var k in orgUserTree.nodeHash){
						var node = orgUserTree.nodeHash[k];
						if(node.attributes.entityId == 'usertreenode_293'){
							alert(node.attributes.text);
						}
						if(node.attributes.orgOrUser == "0"){
							switch(node.attributes.sexCd){
								case '1':node.iconPath = "${ctx}/images/webim/male_online.jpg"; break;
								case '2':node.iconPath = "${ctx}/images/webim/female_online.jpg"; break;
								default :node.iconPath = "${ctx}/images/webim/none_online.jpg"; break;
							}
						}
						if(node.iconPath == ''){
							node.iconPath = "${ctx}/images/imgTree/page.gif";
						}
					}
					//放在修饰后面渲染,以防人员icon无法显示
					orgUserTree.render(); 
					orgUserTree.on(function(node){
						var id  = node.attributes.entityId;
						var name  = node.attributes.text;
						if( $.trim(id) == '' || $.trim(id)=='0'){
							return;
						}
						var nodeType = node.attributes.nodeType;
						
						//点击机构,发送请求,人员列表
						if( nodeType == "1"){
							if(node.isExpand){
								node.collapse();
							}else{
								node.expand();
							}
						}
						
						//点击人员,发送请求,机构列表
						if( nodeType == "0"){
							$('#curUserCd').val(node.attributes.extParam);
							$('#curUserName').text(node.attributes.text);
							ajaxSearch();
						} 
					});
				}
			});
		});
		</security:authorize>
		function ajaxSearch(all){
			if(all){
				$('#curUserName').text('中心全体人员');
				$('#curUserCd').val('');
			}
			TB_showMaskLayer('正在加载...');
			$('#searchForm').ajaxSubmit(function(r){
				TB_removeMaskLayer();
				$('#resultDiv').html(r);
			});
		}
	</script>
</body>
</html>


