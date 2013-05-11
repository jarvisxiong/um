<%@ page contentType="text/html;charset=UTF-8" %>
 
<div id="left_address_div" style="overflow-y: hidden; height:490px;">
	<div id="div_address" class="div_address">
		<div style="margin-top:1px;">
			<div style="margin-left:5px;height:24px; line-height:24px;">
				<div id="wab" class="wab_title" onclick="resetWabTreeHeight();" title="查看通讯录">通讯录</div>
				<div id="btn_direct_wab" class="address_down" title="展开/收起通讯录">&nbsp;&nbsp;</div>
				<div class="split_vertial">&nbsp;</div>
			</div>
			<div style="width: 100%; height: 1px; border-top: 1px solid #8C8F94;"></div>
			<input  type="text" 
					id="searched_user" 
					value="搜索联系人..." 
					class="search_input" 
					onclick="clearSearchInput(this);"
					onkeyup ="searchUserResult(this);" 
					onblur="resetSearchInput(this,'wabTree','searchResult');"/>
			<div id="searchResult" class="search_user_result" ></div>
			<div id="wabTree" style="width:100%; overflow-x:hidden;overflow-y:auto;" onclick="resetWabTreeHeight();"></div>
		</div>
	</div>
	
	<div style="height: 3px; border-top: 1px solid #8C8F94; border-right: 1px solid #8C8F94; border-bottom: 1px solid #8C8F94;">
		<div style="background-color: #EFF3FB; height: 1px;"></div>
		<div style="background-color: #DEE6E8; height: 2px;"></div>
	</div>
		
	<div class="notify_news">
		<div id="div_btn_notify" class="ggxw_title_active" onclick="clickGgxwChange('notify')">公告</div>
		<div id="div_btn_news"  class="ggxw_title_inactive" onclick="clickGgxwChange('news')">新闻</div>
		<div class="left_more" onclick="openGgxwMore()">更多&gt;&gt;&nbsp;</div>
		<div class="split_vertial">&nbsp;</div>
	</div>
	<div id="pn_content" class="pn_content">
		<div id="desk_front_notify_div"></div>
		<div id="desk_front_news_div" style="display:none;"></div>
	</div>
</div>
<!-- 通知栏 -->
<div id="broad_content" class="broad_content">
	<div style="height: 3px; border-top: 1px solid #8C8F94; border-right: 1px solid #8C8F94; border-bottom: 1px solid #8C8F94;">
		<div style="background-color: #EFF3FB; height: 1px;"></div>
		<div style="background-color: #DEE6E8; height: 2px;"></div>
	</div>
	<div class="broad_title">通知栏</div>
	<div style="padding-left:8px; font-weight:bolder;"><a href="${ctx}/app/download.action?fileName=PD3.0.ppsx&amp;realFileName=PD3.0_intro.ppsx&amp;bizModuleCd=public"  target="_blank" class="color_red">PD3.0新特性说明&nbsp;></a></div>
	<div style="padding-left:8px;"><a href="${ctx}/app/download.action?fileName=adobe.rar&realFileName=adobe.rar&bizModuleCd=public"  target="_blank" class="color_red">下载加密系统插件</a></div>
	<div style="padding-left:8px;display:none;"><a href="${ctx}/app/download.action?fileName=adobe.rar&realFileName=adobe.rar&bizModuleCd=public"  target="_blank" class="color_red">下载销售系统插件</a></div>
	<div style="padding-left:8px;"><a href="${ctx}/app/download.action?fileName=eas_doc_setup.rar&realFileName=eas_doc_setup.rar&bizModuleCd=public"  target="_blank" class="color_red">下载人事系统插件</a></div>
	<div style="padding-left:8px;"><a href="${ctx}/app/download.action?fileName=normal_operation.rar&realFileName=normal_operation.rar&bizModuleCd=public"  target="_blank" class="color_red">打印机、电话、电脑日常操作说明</a></div>
	<div style="padding-left:8px;"><a href="${ctx}/app/download.action?fileName=seat_and_tel.xlsx&realFileName=seat_and_tel.xlsx&bizModuleCd=public"  target="_blank" class="color_red">总部人员座位图</a></div>
</div>
