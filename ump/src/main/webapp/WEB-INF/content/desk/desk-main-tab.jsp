<%@page contentType="text/html;charset=UTF-8"%>
<div class="memo_right_btns">
	<div style="width:100%; height:22px;background-color: #8c8f94;">
		<div onclick="rightControlBtnClick()" style="float:left;padding-left:3px;">便签</div>
		<div style="float:right">
			<img onclick="addNote();" src="${ctx}/resources/images/desk/note/add_greyback.gif" title="新增便签">
			<img id="rightControlImg" onclick="rightControlBtnClick()" src="${ctx}/resources/images/desk/note/right_greyback.gif" title="收起/展开右侧栏">
		</div>
	</div>
	<div style="width:100%; height:1px; background-color:#8c8f94;"></div>
</div>
<div id="divTab" class="tabs">
	<div id="homepage_tab">
		<div type="pageDiv" class="tab_active" onclick="TabUtils.showTab({data:{tabId:'homepage',src:''}});">桌面</div>
		<div style='float:left; width:5px; height:23px;'></div>
	</div>
</div>