<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/common/common.css" />
<script type="text/javascript">
var _ctx = '${ctx}'; 
</script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.select.js"></script>
</head>
<body>
	<div style="margin:10px 20px;background-color: #8DB2E3;color:#fff;height:30px;line-height:30px;font-size:14px;">
		人员选择框使用示例
	</div>
	
	<div style="margin:10px 20px;line-height: 20px;border:1px solid #ccc">
		<div style="height:20px;line-height:20px;background-color: #8DB2E3;color:#fff;padding-left:10px;font-weight:bold;">默认调用</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择人员
			<input type="text" id="select_test1"/>
			<input type="hidden"/>
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：$('#select_test1').userSelect();
		</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择机构
			<input type="text" id="select_test2"/>
			<input type="hidden"/>
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：$('#select_test2').orgSelect();
		</div>
	</div>
	
	<div style="margin:10px 20px;line-height: 20px;border:1px solid #ccc">
		<div style="height:20px;line-height:20px;background-color: #8DB2E3;color:#fff;padding-left:10px;font-weight:bold;">人员单选</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择人员
			<input type="text" id="select_test3"/>
			<input type="hidden"/>
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：$('#select_test3').userSelect({
				muti:false,opacity: 100
			});
		</div>
	</div>
	
	
	<div style="margin:10px 20px;line-height: 20px;border:1px solid #ccc">
		<div style="height:20px;line-height:20px;background-color: #8DB2E3;color:#fff;padding-left:10px;font-weight:bold;">机构多选</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择人员
			<input type="text" id="select_test4"/>
			<input type="hidden"/>
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：$('#select_test4').userSelect({orgMuti:true,maxSelectedUser:10,showRank:true});
		</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择机构
			<input type="text" id="select_test5"/>
			<input type="hidden"/>
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：$('#select_test5').orgSelect({orgMuti:true});
		</div>
	</div>
	
	<div style="margin:10px 20px;line-height: 20px;border:1px solid #ccc">
		<div style="height:20px;line-height:20px;background-color: #8DB2E3;color:#fff;padding-left:10px;font-weight:bold;">系统职位选择</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择人员
			<input type="text" id="select_test6"/>
			<input type="hidden"/>
			<input type="text" id="sysPosId1"/>
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：$('#select_test6').userSelect({sysPosEId:'sysPosId'});
		</div>
	</div>
	
	<div style="margin:10px 20px;line-height: 20px;border:1px solid #ccc">
		<div style="height:20px;line-height:20px;background-color: #8DB2E3;color:#fff;padding-left:10px;font-weight:bold;">不同的机构树</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择机构
			<input type="text" id="select_test7"/>
			<input type="hidden"/>全部机构树
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：$('#select_test7').orgSelect({orgMuti:true});
		</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择机构
			<input type="text" id="select_test8"/>
			<input type="hidden"/>中心机构树
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：$('#select_test8').orgSelect({showCenterOrg:true});
		</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择机构
			<input type="text" id="select_test20"/>
			<input type="hidden"/>管辖机构树
			<input type="text" id="sysPosId"/>
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：$('#select_test12').userSelect({showCenterOrg:true,sysPosEId:'select_test20'});
		</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择机构
			<input type="text" id="select_test9"/>
			<input type="hidden"/>项目机构树(审批中常见)
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：$('#select_test9').orgSelect({showProjectOrg:true});
		</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择机构
			<input type="text" id="select_test10"/>
			<input type="hidden"/>指定某一个父节点的树 比如 宝龙商业
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：$('#select_test10').orgSelect({parentOrgCd:'100'});
		</div>
	</div>
	
	<div style="margin:10px 20px;line-height: 20px;border:1px solid #ccc">
		<div style="height:20px;line-height:20px;background-color: #8DB2E3;color:#fff;padding-left:10px;font-weight:bold;">自定义回调函数(回调目前只支持人员选择框)</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择人员
			<input type="text" id="select_test11"/>
			<input type="hidden"/>
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：$('#select_test11').userSelect({
				callback:function(map){
					var str;
					for(var n in map){
						str.push(map[n]+'-'+n);
					}
					alert(str.join(';'));
				}
			});
		</div>
	</div>
	
	<div style="margin:10px 20px;line-height: 20px;border:1px solid #ccc">
		<div style="height:20px;line-height:20px;background-color: #8DB2E3;color:#fff;padding-left:10px;font-weight:bold;">多个元素同时渲染</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择人员1
			<input type="text" class="test" id="select_test12"/>
			<input type="hidden"/>
		</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择人员2
			<input type="text" class="test" id="select_test13"/>
			<input type="hidden"/>
		</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择人员3
			<input type="text" class="test" id="select_test14"/>
			<input type="hidden"/>
		</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择人员4
			<input type="text" class="test" id="select_test15"/>
			<input type="hidden"/>
		</div>
		<div style="padding-left:10px;height:30px;line-height:30px;">
			选择人员5
			<input type="text" class="test" id="select_test16"/>
			<input type="hidden"/>
		</div>
		<div style="background-color: #eee;padding-left:10px;height:30px;line-height:30px;">
			js代码：$('.test').userSelect({
				muti:false
			});
		</div>
	</div>
	<div style="margin:10px 20px;height:20px;line-height:20px;background-color: #ccc;padding-left:10px;font-weight:bold;color:red;">更多使用技巧,请自行挖掘....</div>
	
	<script type="text/javascript">
		$(function(){
			$('#select_test1').userSelect();
			$('#select_test2').orgSelect();
			$('#select_test3').userSelect({
				muti:false,
				opacity: 100
			});
			$('#select_test4').userSelect({
				orgMuti:true,
				maxSelectedUser:10,
				showRank:true,
				showGroupFlg : true
			});
			$('#select_test5').orgSelect({
				orgMuti:true
			});
			$('#select_test6').userSelect({
				sysPosEId:'sysPosId'
			});
			$('#select_test7').orgSelect({
				orgMuti:true
			});
			$('#select_test8').orgSelect({
				showCenterOrg:true
			});
			$('#select_test20').userSelect({
				 sysPosEId:'select_test20',showCenterOrg:true,muti:false
			});
			$('#select_test9').orgSelect({
				showProjectOrg:true
			});
			$('#select_test10').orgSelect({
				parentOrgCd:'153'//宝龙商业
			});
			$('#select_test11').userSelect({
				callback:function(map){
					var str = new Array();
					for(var n in map){
						str.push(map[n]["userName"]+'-'+n);
					}
					var aStr = str.join(';');
					alert(aStr);
					$('#select_test11').val(aStr);
				}
			});
			$('.test').userSelect({
				muti:false
			});
		});
		
	</script>
</body>
</html>