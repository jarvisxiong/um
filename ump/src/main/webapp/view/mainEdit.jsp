<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/IndexUtils.js"></script>
<title>管理区域</title>
<script language="javascript">
	var ctx4js = "${ctx}";
	$(function(){
		$("#editTable tbody tr.mainTr").toggle(function(){
			$(".foldTr").trigger("click");
			$(this).addClass("foldTr");
			$(this).children().eq(0).children().attr("src","${ctx}/images/up.gif")
			$(this).next().show();
		},function(){
			$(this).removeClass("foldTr");
			$(this).children().eq(0).children().attr("src","${ctx}/images/down.gif")
			$(this).next().hide();
		});

		//test
		$("div.list_detail li").mouseover(function(){
			IndexUtils.showUserInfo(this);
		});
		$("div.list_detail").mouseleave(function(){
			$("div.userInfo").hide();
		});
		$("#fullScreenBtn").click(function(){
			  var WshShell = new ActiveXObject('WScript.Shell');
		      WshShell.SendKeys('{F11}');
		});
	});
</script>
</head>

<body>
<div class="deptBanner">
<div class="list_tilte"><span>快捷搜索</span></div>
	<div  class="list_detail">
		<ul>
			<li id="aa"><a href="javascript:void(0)">总裁办</a></li>
			<li id="ab"><a href="javascript:void(0)">投资管理中心</a></li>
			<li id="ac"><a href="javascript:void(0)">企业发展部</a></li>
			<li id="ad"><a href="javascript:void(0)">业务分析中心</a></li>
			<li id="ae"><a href="javascript:void(0)">技术中心</a></li>
		</ul>
	</div>
</div>
<div class="deptContent">

<div class="search">
	<fieldset>
	    <legend>搜索</legend>
	    <div>
	      编号：<input type="text" name="Name" id="Name" size="18" maxlength="30" />
	      执行人：<input type="password" name="password" id="password" size="18" maxlength="30" />
	      <input type="button" class="buttom" value="搜索" />
	      <input type="button" class="buttom" value="导出Excel" />
	      <input type="button" class="buttom" id="fullScreenBtn" value="全屏" />
	    </div>
	</fieldset>
</div>

<div id="tableDiv">
<table class="commonTable" id="editTable" align="left" width="99%">
	<thead>
		<tr>
		<th width="10"><img src="${ctx}/images/down.gif" title="展开所有"/></th>
		<th width="40">编号</th>
		<th >任务内容</th>
		<th width="40">中心</th>
		<th width="50">执行人</th>
		<th width="80">状态</th>
		<th width="90">开始时间</th>
		<th width="90">目标时间</th>
		<th width="90">更新</th>
		</tr>
	</thead>
	<tbody>
		<tr id="0" class="mainTr">
			<td><img src="${ctx}/images/down.gif"/></td>
			<td>dh-1</td>
			<td>测试测试测试</td>
			<td>审</td>
			<td>shixy</td>
			<td>进行中</td>
			<td>2009-11-11</td>
			<td>2009-12-12</td>
			<td>2009-12-1</td>
		</tr>
		<tr id="det_3" class="detailTr"  style="display:none">
			<td colspan="9">
				    <fieldset>
				    <legend>详细信息</legend>
				    <div>
				      <label for="Name">任务内容</label>
				      <input type="text" name="Name" id="Name" size="18" maxlength="30" />
				      <br/>
				    </div>
				    <div>
				      <label for="password">执行人</label>
				      <input type="password" name="password" id="password" size="18" maxlength="30" />
				      <br/>
				    </div>
				    <div>
				      <label for="password">开始时间</label>
				      <input type="password" name="password" id="password" size="18" maxlength="30" />
				      <br/>
				    </div>
				    <div>
				      <label for="password">目标时间</label>
				      <input type="password" name="password" id="password" size="18" maxlength="30" />
				      <br/>
				    </div>
				    <div class="cookiechk">
				      <input type="submit" class="buttom" value="保存" />
				    </div>
				    </fieldset>
			</td>
		</tr>
		<tr id="1" class="mainTr">
			<td><img src="${ctx}/images/down.gif"/></td>
			<td>dh-1</td>
			<td>测试测试测试</td>
			<td>审</td>
			<td>shixy</td>
			<td>进行中</td>
			<td>2009-11-11</td>
			<td>2009-12-12</td>
			<td>2009-12-1</td>
		</tr>
		<tr id="det_1" class="detailTr"  style="display:none">
			<td colspan="9">
				    <fieldset>
				    <legend>详细信息</legend>
				    <div>
				      <label for="Name"></label>
				      <input type="text" name="Name" id="Name" size="18" maxlength="30" />
				      <br/>
				    </div>
				    <div>
				      <label for="password">密码</label>
				      <input type="password" name="password" id="password" size="18" maxlength="30" />
				      <br/>
				    </div>
				    <div class="cookiechk">
				      <label>
				      <input type="checkbox" name="CookieYN" id="CookieYN" value="1" />
				      <a href="#" title="选择是否记录您的信息">记住我</a></label>
				      <input name="login791" type="submit" class="buttom" value="登录" />
				    </div>
				    <div class="forgotpass"><a href="#">您忘记密码?</a></div>
				    </fieldset>
			</td>
		</tr>
		<tr id="2" class="mainTr">
			<td><img src="${ctx}/images/down.gif"/></td>
			<td>dh-1</td>
			<td>测试测试测试</td>
			<td>审</td>
			<td>shixy</td>
			<td>进行中</td>
			<td>2009-11-11</td>
			<td>2009-12-12</td>
			<td>2009-12-1</td>
		</tr>
		<tr id="det_2" class="detailTr"  style="display:none">
			<td colspan="9">
				    <fieldset>
				    <legend>详细信息</legend>
				    <div>
				      <label for="Name">用户名</label>
				      <input type="text" name="Name" id="Name" size="18" maxlength="30" />
				      <br/>
				    </div>
				    <div>
				      <label for="password">密码</label>
				      <input type="password" name="password" id="password" size="18" maxlength="30" />
				      <br/>
				    </div>
				    <div class="cookiechk">
				      <label>
				      <input type="checkbox" name="CookieYN" id="CookieYN" value="1" />
				      <a href="#" title="选择是否记录您的信息">记住我</a></label>
				      <input name="login791" type="submit" class="buttom" value="登录" />
				    </div>
				    <div class="forgotpass"><a href="#">您忘记密码?</a></div>
				    </fieldset>
			</td>
		</tr>
	</tbody>
</table>
</div>

</div>
</body>
</html>
