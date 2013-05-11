<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>菜单</title>
	<meta http-equiv="Content-Type" content="text/html" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
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
			color:#0167a2;
			text-decoration: underline;
		}
	</style>
</head>
<body>
<div style="padding-left:3px;">
	<s:iterator value="#request.appModuleMenuRels">
		<s:if test="appMenu.parentMenuCd == null || appMenu.parentMenuCd == ''">
			<div class="menu" >
				<!-- 弹出新窗口 -->
				<s:if test="appMenu.sameWinFlg == 1">
					<div onclick="window.open('${ctx}${appMenu.appPage.pagePath}');">
						<div style="margin:7px 0 7px 20px;">
							<s:if test="appMenu.remark==null||appMenu.remark==''">
								<img src="${ctx}/resources/images/desk/menu/default.gif"/>
							</s:if>
							<s:else>
								<img src="${ctx}/resources/images/desk/menu/${appMenu.remark}.gif"/>
							</s:else>
						</div>
						<div style="text-align: center;" title="${appMenu.menuTip }">
							${appMenu.menuName}
						</div>
					</div>
				</s:if>
				<s:else>
					<div onclick="parent.TabUtils.newTab('${appMenu.menuCd}','${appMenu.menuName}','${ctx}${appMenu.appPage.pagePath}');">
						<div style="margin:7px 0 7px 20px;">
							<s:if test="appMenu.remark==null||appMenu.remark==''">
								<img src="${ctx}/resources/images/desk/menu/default.gif"/>
							</s:if>
							<s:else>
								<img src="${ctx}/resources/images/desk/menu/${appMenu.remark}.gif"/>
							</s:else>
						</div>
						<div style="text-align: center;" title="${appMenu.menuTip }">
							${appMenu.menuName}
						</div>
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
<script language="javascript">
	$(function(){
		var _off = $("#secondMenu").parent().offset();
		$("#secondMenu").css({'left':_off.left,'top':_off.top+82});
	});
</script>
</body>
</html>
