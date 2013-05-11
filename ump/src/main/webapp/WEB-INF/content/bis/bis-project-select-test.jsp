<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
<title>商业管理</title>
</head>

<body>
	<div style="margin:10px 20px;background-color: #8DB2E3;color:#fff;height:30px;line-height:30px;font-size:14px;">
		项目选择框使用示例
	</div>
	
	<div style="margin:10px 20px;line-height: 20px;border:1px solid #ccc">
		<div style="height:20px;line-height:20px;background-color: #8DB2E3;color:#fff;padding-left:10px;font-weight:bold;">项目单选</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择项目
			<input type="text" id="select_single"/>
			<input type="hidden"/>
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：	$('#select_project_single').onSelect({
						muti:false
					});
		</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择项目
			<input class="btn_green" type="text" value="项目业态图" style="width: 90px;" id="select_project_single" />
			<input type="hidden"/>
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：	$('#select_project_single').onSelect({
						muti:false,
						callback:function(map){
							var str = new Array();
							for(var n in map){
								str.push(map[n]["bisProjectId"]);
								str.push(map[n]["projectName"]);
							}
						}
					});
		</div>
	</div>
	
	
	<div style="margin:10px 20px;line-height: 20px;border:1px solid #ccc">
		<div style="height:20px;line-height:20px;background-color: #8DB2E3;color:#fff;padding-left:10px;font-weight:bold;">机构多选</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择项目
			<input class="btn_green" type="text" style="width: 90px;" id="select_multi"  value="选择项目"/>
			<div id="select_multi_text" />
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：$('#select_multi').onSelect({
						callback:function(map){
							var str = new Array();
							for(var n in map){
								str.push(map[n]["bisProjectId"]);
								str.push(map[n]["projectName"]);
							}
							var aStr = str.join(';');
							$('#select_multi_text').html(aStr); 
						}
					});
		</div>
		
		
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择项目 ：初始化选中指定项目
			<input class="btn_green" type="text" style="width: 90px;" id="btnProjectSelect"  value="选择项目"/>
			<div id="select_multi_text" />
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：$('#btnProjectSelect').onSelect({
						muti:true,
						cdFields:'40282b8927a42dff0127a4316b830001,40282b8927a42dff0127a432c16e0049',
						callback:function(map){
							var str = new Array();
							for(var n in map){
								str.push(map[n]["bisProjectId"]);
								//str.push(map[n]["projectName"]);
							}
							var aStr = str.join(',');
							reportSearch(aStr);
						}
					});
		</div>
	</div>
		
<script type="text/javascript">

$(function(){
	$('#btnProjectSelect').onSelect({
		muti:true,
		cdFields:'40282b8927a42dff0127a4316b830001,40282b8927a42dff0127a432c16e0049',
		callback:function(map){
			var str = new Array();
			for(var n in map){
				str.push(map[n]["bisProjectId"]);
				//str.push(map[n]["projectName"]);
			}
			var aStr = str.join(',');
			reportSearch(aStr);
		}
	});
	$('#select_project_single').onSelect({
		muti:false,
		callback:function(project){
			var name=project.projectName;
			var id=project.bisProjectId;
			var orgCd=project.orgCd;
			alert(id+';'+name+';'+orgCd);
		}
	});
	$('#select_single').onSelect({
		muti:false,
	});
	$('#select_multi').onSelect({
		callback:function(map){
			var str = new Array();
			for(var n in map){
				str.push(map[n]["bisProjectId"]);
				str.push(map[n]["projectName"]);
			}
			var aStr = str.join(';');
			$('#select_multi_text').html(aStr); 
		}
	});
});

</script>

</body>
</html>
