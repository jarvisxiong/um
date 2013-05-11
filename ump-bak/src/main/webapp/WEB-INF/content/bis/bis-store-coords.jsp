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
	<fieldset>
		<legend>导入商铺坐标</legend>
		<div style="margin:10px 20px;line-height: 20px;border:1px solid #ccc">
			<div style="height:20px;line-height:20px;background-color: #8DB2E3;color:#fff;padding-left:10px;font-weight:bold;">项目单选</div>
			<div style="padding-left:10px;height:30px;line-height:30px;">
				选择项目
				<input type="text" id="select_single"/>
				<input type="hidden" id="bisProjectId"/>
			</div>
			<div style="padding-left:10px;height:30px;line-height:30px;">
				楼层
				<input type="text" id="floorNum"/>
				<input type="hidden"/>
			</div>
			<div style="padding-left:10px;height:30px;line-height:30px;">
				逻辑分区
				<input type="text" id="area"/>
				<input type="hidden"/>
			</div>
			<div style="padding-left:10px;height:30px;line-height:30px;">
				文件路径
				<input type="text" id="fileName"/>
				<input type="hidden"/>
			</div>
			<div style="padding-left:10px;height:30px;line-height:30px;">
				<input type="button" onclick="submit()" value="提交" />
			</div>
			<div id="result" style="display:none;padding-left:10px;height:30px;line-height:30px;">
				
			</div>
		</div>
	</fieldset>
	<fieldset>
		<legend>导入广告坐标</legend>
		<div style="margin:10px 20px;line-height: 20px;border:1px solid #ccc">
			<div style="height:20px;line-height:20px;background-color: #8DB2E3;color:#fff;padding-left:10px;font-weight:bold;">初始化广告位平面图</div>
			<div style="padding-left:10px;height:30px;line-height:30px;">
				选择项目
				<input type="text" id="gselect_single" />
				<input type="hidden" id="gbisProjectId"/>
			</div>
			<table>
			<col></col>
			<col></col>
			<col></col>
			<col></col>
				<tr>
				<td>平面图说明</td>
				<td>小图</td>
				<td>大图</td>
				<td>类别编号</td>
				<td>类别名称</td>
				<td>文件路径</td>
				</tr>
				<tr>
				<td><input type="text" id="remark"/></td>
				<td><input type="text" id="floorUrl"/></td>
				<td><input type="text" id="bigPicUrl"/></td>
				<td><input type="text" name="subFloorType" id="subFloorType">
					</td>
				<td><input type="text" name="subFloorTypeName" id="subFloorTypeName">
					</td>
				<td><input type="text" id="gfileName"/>
				<input type="hidden"/></td>
				</tr>
			</table>
			<div style="padding-left:10px;height:30px;line-height:30px;">
				<input type="button" style="height:30px"  onclick="gSaveFloorsubmit()" value="创建楼层以及更新多径广告坐标" />
			</div>
			<br></br><br/>
			<div style="padding-left:10px;height:30px;line-height:30px;">
				楼层
				<s:select   list="mapFloor" 
			listKey="key" listValue="value" name="mapFloor" id="bisFloorId" 
			></s:select>
			</div>
			<div style="padding-left:10px;height:30px;line-height:30px;">
				<input type="button" style="height:30px" onclick="gsubmit()" value="更新多径广告坐标" />
			</div>
			<div id="gresult" style="display:none;padding-left:10px;height:30px;line-height:30px;">
				
			</div>
			
			
			
			
			<div style="height:20px;line-height:20px;background-color: #8DB2E3;color:#fff;padding-left:10px;font-weight:bold;">初始化广告位平面图
			选择项目
				<input type="text" id="gselect_single_g" />
			</div>
			<div id="bisFloorPanel"></div>
			
		</div>
	</fieldset>
	
	
		
<script type="text/javascript">

$(function(){
	$('#select_single').onSelect({
		muti:false,
	});
	$('#gselect_single').onSelect({
		muti:false
		
	});
	$('#gselect_single_g').onSelect({
		muti:false,
		callback:function(result){
			var url = '${ctx}/bis/bis-store-coords!loadFloors.action';
			var bisProjectId= result.bisProjectId;
		
			$.post(url,{bisProjectId:bisProjectId},function(result){$('#bisFloorPanel').html(result);	$('#ggBisProjectId').val(bisProjectId);});
		}
	});
});
function updateFloor(bisFloorId){
	var url = '${ctx}/bis/bis-store-coords!updateFloor.action';
	var remark = $('#remark').val();
	var parentId = $('#parentId').val();
	var subFloorType = $('#subFloorType').val();
	$.post(url,{bisFloorId:bisFloorId,remark:remark,subFloorType:subFloorType,parentId:parentId},function(result){
		alert(result);
	});
}
function toGGPic(bisFloorId){
	var bisProjectId = $('#ggBisProjectId').val();
	var url = '${ctx}/bis/bis-tenant!loadAdMain.action?bisFloorId='+bisFloorId+'&bisProjectId='+bisProjectId;
	if(parent.TabUtils==null)
		 window.open(url);
		 else
		 parent.TabUtils.newTab("bisTenant","商业租户",url,true); 
}
function submit(){
	var bisProjectId = $('#bisProjectId').val();
	var floorNum = $('#floorNum').val();
	var fileName = $('#fileName').val();
	var area = $('#area').val();
	var data = {bisProjectId:bisProjectId,floorNum:floorNum,fileName:fileName,area:area};
	$.post('${ctx}/bis/bis-store-coords!importExl.action',data,function(result){
		$('#result').html(result).show();
	});
}
function gSaveFloorsubmit(){
	var bisProjectId = $('#gbisProjectId').val();
	var fileName = $('#gfileName').val();
	var remark = $('#remark').val();
	var floorUrl = $('#floorUrl').val();
	var bigPicUrl = $('#bigPicUrl').val();
	var subFloorType = $('#subFloorType').val();
	var subFloorTypeName = $('#subFloorTypeName').val();
	var area = $('#area').val();
	var data = {bisProjectId:bisProjectId,remark:remark,fileName:fileName,floorUrl:floorUrl,bigPicUrl:bigPicUrl,subFloorType:subFloorType,subFloorTypeName:subFloorTypeName};
	$.post('${ctx}/bis/bis-store-coords!importGuangGaoFloor.action',data,function(result){
		$('#gresult').html(result).show();
	});
}
function gsubmit(){
	var bisProjectId = $('#gbisProjectId').val();
	var fileName = $('#gfileName').val();
	var bisFloorId = $('#bisFloorId').val();
	var area = $('#area').val();
	var data = {bisProjectId:bisProjectId,fileName:fileName,bisFloorId:bisFloorId};
	$.post('${ctx}/bis/bis-store-coords!importGuangGaoExl.action',data,function(result){
		$('#gresult').html(result).show();
	});
}
</script>

</body>
</html>
