<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.hhz.ump.util.Constants"%>
<%@ include file="/common/taglibs.jsp" %>
	<script language="javascript">
	$(function(){
		var menuTimer;
		$(".menubar ul li").hover(
			function(){
				if(menuTimer){
					clearTimeout(menuTimer);
				}
				var $this = $(this);
				$this.find(".header_btn_main").addClass("header_btn_main_hover");
				$this.find(".header_main_pic").hide();
				$this.find(".header_main_pic_hover").show();
				$("#xialaOuter").css("height","500px");
				menuTimer = setTimeout(function(){
					$this.children(".childrenIframe").show();
					$this.children("ul").show();
				}, 300);
			},
			function(){
				if(menuTimer){
					clearTimeout(menuTimer);
				}
				$(this).find(".header_btn_main").removeClass("header_btn_main_hover");
				$(this).find(".header_main_pic").show();
				$(this).find(".header_main_pic_hover").hide();
				$(this).children(".childrenIframe").hide();
				$(this).children("ul").hide();
				menuTimer = setTimeout(function(){
					$("#xialaOuter").css("height","63px");
				}, 300);
			}
		);
		$("ul.second_menu li").hover(function(){
			$("#xialaOuter").css("height","500px");
			var height = $(this).offset().top-60;
			$(this).children(".third_menu").css({"top":height}).show();
		},
		function(){
			$(this).children(".third_menu").hide();
		});
		$(".header_btn_main").click(function(){
			var clickFunc = $(this).attr("clickFunc");
			var _children = $(this).parent().siblings(".second_menu").children();
			if(_children.length > 1){
				eval(clickFunc);
			}else{
				eval(_children.find(":first").attr("href"));
			}
		});
	});
	function hideAllMenus(){
		//隐藏所有的菜单，点击某菜单时触发。解决IPAD平台点击菜单后下拉列表不消失的问题
		$("ul.second_menu").hide();
		$(".childrenIframe").hide();
	}
	</script>
<div style="width:2000px; height:63px;" id="xialaOuter">
<div class="header_shu"></div>
<div style="float:left;" class="menubar">
	<ul style="display: block;" class="menus">
		<s:iterator value="appRoleModules" status="mod" var="module">
		<li>
			<div style="float:left" >
				<div class="header_btn_main" clickFunc="TabUtils.newTab('module_${moduleCd}','${moduleName}','${ctx}/app/app-authority!loadMenuByModule.action?moduleCd=${moduleCd}');">
			 		<div style="margin-top:28px; height:20px;">
				 		<div style="float:left; margin-left:5px; height:20px; line-height:20px;">
				 		    <img class="header_main_pic" src="${ctx}/resources/images/desk/${remark}.jpg"/>
		                  	<img class="header_main_pic_hover" style="display:none;" src="${ctx}/resources/images/desk/${remark}_hover.jpg"/>
						</div>
						<div style="float:left; height:20px; line-height:20px;">
							&nbsp;${moduleName}
						</div>
					</div>
			 	</div>
			 	<div class="header_shu"></div>
			</div>
			<iframe class="childrenIframe"></iframe>
			<ul class="second_menu">
			<s:iterator value="#module.appModuleMenuRels">
				<s:if test="appMenu.parentMenuCd == null || appMenu.parentMenuCd == ''">
					<s:if test="appMenu.menuCd == 140">
						<li id="children_${appMenu.menuCd}" style="<s:if test="1==appMenu.fixFlag">background-color:#fbd5b5;</s:if>">
							<a href="javascript:TabUtils.newTab('${appMenu.menuCd}','${appMenu.menuName}','${ctx}${appMenu.appPage.pagePath}');">${appMenu.menuName}</a>
							<div class="third_menu" style="display: none;">
								<ul>
									<s:iterator value="#module.appModuleMenuRels">
										<s:if test="appMenu.parentMenuCd == 140">
											<script type="text/javascript">
												$("#children_"+"${appMenu.parentMenuCd}").attr("className","muti_menu");
											</script>
											<s:if test="appMenu.sameWinFlg == 1">
												<a href="javascript:hideAllMenus();void(window.open('${ctx}${appMenu.appPage.pagePath}'))">${appMenu.menuName}</a>
											</s:if>
											<s:else>
												<a href="javascript:hideAllMenus();TabUtils.newTab('${appMenu.menuCd}','${appMenu.menuName}','${ctx}${appMenu.appPage.pagePath}');">${appMenu.menuName}</a>
											</s:else>
										</s:if>
									</s:iterator>
								</ul>
							</div>
						</li>
					</s:if>
					<s:else>
						<li <s:if test="1==appMenu.fixFlg">style="background-color:#fbd5b5;"</s:if>>
							<s:if test="appMenu.sameWinFlg == 1">
								<a href="javascript:hideAllMenus();void(window.open('${ctx}${appMenu.appPage.pagePath}'))">${appMenu.menuName}</a>
							</s:if>
							<s:else>
								<a title="${appMenu.menuTip }" href="javascript:hideAllMenus();TabUtils.newTab('${appMenu.menuCd}','${appMenu.menuName}','${ctx}${appMenu.appPage.pagePath}');">${appMenu.menuName}</a>
							</s:else>
						</li>
					</s:else>
				</s:if>
			</s:iterator>
			</ul>
		</li>
</s:iterator>
</ul>
</div>
</div>