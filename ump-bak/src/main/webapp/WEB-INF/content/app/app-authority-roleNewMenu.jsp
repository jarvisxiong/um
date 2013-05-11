<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.hhz.ump.util.Constants"%>
<%@ include file="/common/taglibs.jsp" %>
<script language="javascript" src="${ctx}/js/jquery.js"></script>
	<style type="text/css">
		.menu{
			float:left;width:80px;height:74px;margin:3px;cursor: pointer;border:1px solid #fff;
		}
		.menu:hover{
			border:1px solid #8C8F94;
		}
		.second_menu{
			color:#363636;
			cursor: pointer;
		}
		.second_menu:hover{
			color:#000000;
			text-decoration: underline;
		}
		.menuShow:hover{
			background-color: #cbcbcb;
			cursor: pointer;
		}
		.show_pic{
			width:36px;
			height:36px;
			background-color: #5fbcea;
			background-position: center;
			background-repeat: no-repeat;
		}
	</style>	
	
		<s:iterator value="appRoleModules" status="mod" var="module">
		<div class="font_left green_hover" oi="nohide" id="${moduleCd}" onclick="doClickBook(this,'${ctx}/app/app-authority!loadMenuByNewModule.action?moduleCd=${moduleCd}');" >${moduleName}</div>
		<div id="child_${moduleCd}" type="moduleChild" style="background-color:#fff;margin-left:0px;width: 108px;display:none;">
			<div style="margin-left: 0px;" >
				<s:iterator value="#module.appModuleMenuRels">
					<s:if test="appMenu.parentMenuCd == null || appMenu.parentMenuCd == ''">
						<div style="width:254px;" class="menuShow" >
							<!-- 弹出新窗口 -->
							<s:if test="appMenu.sameWinFlg == 1">
								<s:set var="imgUrl">${ctx}/resources/images/desk2/menu/menu_manage.png</s:set>
								<s:if test="appMenu.iconPath2==null||appMenu.iconPath2==''">
									<s:set var="imgUrl">${ctx}/resources/images/desk2/menu/menu_manage.png</s:set>
								</s:if>
								<s:else>
									<s:set var="imgUrl">${ctx}/resources/images/desk2/menu/${appMenu.iconPath2}.png</s:set>
								</s:else>
								<div onclick="window.open('${ctx}${appMenu.appPage.pagePath}');" align="center">
									<div class="show_pic" style="margin:7px 0 7px 20px;float:left;background-image: url('${imgUrl }');">
										
									</div>
									<div style="text-align: center;float:left;padding-left:4px;padding-top: 18px;font-size: 12px;" title="${appMenu.menuTip }">
										${appMenu.menuName}
									</div>
									<div style="clear: both;"></div>
								</div>
							</s:if>
							<s:else>
								<s:set var="imgUrl">${ctx}/resources/images/desk2/menu/menu_manage.png</s:set>
								<s:if test="appMenu.iconPath2==null||appMenu.iconPath2==''">
									<s:set var="imgUrl">${ctx}/resources/images/desk2/menu/menu_manage.png</s:set>
								</s:if>
								<s:else>
									<s:set var="imgUrl">${ctx}/resources/images/desk2/menu/${appMenu.iconPath2}.png</s:set>
								</s:else>
								<div onclick="parent.TabUtils.newTab('${appMenu.menuCd}','${appMenu.menuName}','${ctx}${appMenu.appPage.pagePath}');closeBook();" align="center">
									<div style="margin:7px 0 7px 20px;float:left;background-image: url('${imgUrl }');" class="show_pic">
										
									</div>
									<div style="text-align: center;float:left;padding-left:4px;padding-top: 18px;font-size: 12px;" title="${appMenu.menuTip }">
										${appMenu.menuName}
									</div>
									<div style="clear: both;"></div>
								</div>
							</s:else>
							
							<!-- 特殊处理 -->
							<s:if test="appMenu.menuCd == 140">
								<div style="position: absolute;top:100px;left:200px;display: none;" id="secondMenu">
									<div style="margin-left:35px;margin-bottom:5px;"><img src="${ctx}/resources/images/treePanel/triangle_down.gif"/></div>
									<div style="width:74px;border:1px solid #8C8F94;line-height: 25px;">
										<s:iterator value="#request.appModuleMenuRels">
											<s:if test="appMenu.parentMenuCd == 140">
												<script type="text/javascript">
													$("#secondMenu").show();
												</script>
												<div class="second_menu" title="${appMenu.menuTip }" onclick="parent.TabUtils.newTab('${appMenu.menuCd}','${appMenu.menuName}','${ctx}${appMenu.appPage.pagePath}');">${appMenu.menuName}</div>
											</s:if>
										</s:iterator>
									</div>
								</div>
							</s:if>
						</div>
					</s:if>
				</s:iterator>
			</div>
		</div>
		</s:iterator>
<script language="javascript">
	$(function(){
		var _off = $("#secondMenu").parent().offset();
		$("#secondMenu").css({'left':_off.left,'top':_off.top+82});
	});
</script>
