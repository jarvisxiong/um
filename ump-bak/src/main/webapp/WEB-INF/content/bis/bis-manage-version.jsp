<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>版本更新</title>
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<style type="text/css">
#btn_version{cursor: pointer;float:left;    text-decoration:underline;;color:#0167A2; font-size:12px;}
.div_span{width:1px;height:4px;color:#0167a2;display:list-item;float:left;margin-left:25px;}
.div_info{display:none;}
.div_date{float:left;width:70px;}
.div_title{height:23px;line-height:23px;width:70px;margin-right:10px;}
.div_content{height:23x;line-height:23px;}
.div_title font{ margin-right:5px;}

.tab{margin:15px;}
.tab tr{ border-bottom: 1px solid #DDDBDC;}
.tab tr td:hover{border-bottom: 1px solid #DDDBDC;background:#DDDBDC;color:#40a4be; }

.div_ul{list-style:outside none;margin-bottom:15px;}
.div_ul li{list-style:outside none;}
.div_li{height:20px;line-height:20px;}
</style>
<script type="text/javascript">
function ver(){
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='selectTypeDiv'><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
		width : 500,
		height :400,
		title : "功能更新提醒",
		closeBtn:true,
		afterShow : function() {
			$("#selectTypeDiv").html($('.div_info').html());
		},
		handler : function(btn){
			if(btn=='ok'){
			}else{
				ymPrompt.close();
			}
		},
		btn:[["关闭",'cancel']]
	});
}
</script>
</head>
<body>
<div id="btn_version" onclick="ver();" style="display:none;"><span class="div_span"></span>1月10日系统更新提示:租赁合同自动生成应收。</div>
<div class="div_info">
	<table class="tab">
		<tr><td>
			<ul class="div_ul">
				<li class="div_li"><span class="div_title"><font>1月10日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
				<li><span class="div_content">租赁合同增加自动生成应收功能。</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
				<li class="div_li"><span class="div_title"><font>1月8日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
				<li><span class="div_content">项目总况增加累计搜索功能。</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
				<li class="div_li"><span class="div_title"><font>1月8日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
				<li><span class="div_content">平面图增加欠费列表功能。</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
				<li class="div_li"><span class="div_title"><font>12月16日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
				<li><span class="div_content">新增租户退铺功能。</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
				<li class="div_li"><span class="div_title"><font>12月8日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
				<li><span class="div_content">合同模块增加有无应收的搜索。</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
				<li class="div_li"><span class="div_title"><font>12月5日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
				<li><span class="div_content">平面图模块商家商铺历史记录搜索功能。</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
				<li class="div_li"><span class="div_title"><font>12月2日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
				<li><span class="div_content">项目模块增加广告图功能。</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
				<li class="div_li"><span class="div_title"><font>12月2日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
				<li><span class="div_content">合同增加内部批文。</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
				<li class="div_li"><span class="div_title"><font>12月2日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
				<li><span class="div_content">费用模块增加导入导出功能。</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
				<li class="div_li"><span class="div_title"><font>11月27日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
				<li><span class="div_content">收费明细新增预收功能。</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
				<li class="div_li"><span class="div_title"><font>11月14日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
				<li><span class="div_content">商家库新增商家资源时，商家类别只允许对‘末级节点’进行勾选。</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
					<li class="div_li"><span class="div_title"><font>11月7日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
					<li><span class="div_content">增加商家库评级功能。</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
					<li class="div_li"><span class="div_title"><font>11月7日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
					<li><span class="div_content">增加商家库品牌名重复判断功能。</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
					<li class="div_li"><span class="div_title"><font>10月31日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
					<li><span class="div_content">增加商家合并功能，总部可进行商家合并。</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
				<li class="div_li"><span class="div_title"><font>10月22日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
				<li><span class="div_content">增加业主物业合同。已售商铺的业主物业合同可录入系统。</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
				<li class="div_li"><span class="div_title"><font>10月18日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
				<li><span class="div_content">增加租户台账功能。在平面图可切换至租户台账。</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
				<li class="div_li"><span class="div_title"><font>10月12日</font><img src="${ctx }/resources/images/common/new.gif" class="new_img"></span></li>
				<li><span class="div_content">实收款项批量自动生成功能。在“收费明细”-->“应收明细”列表下方，新增“自动生成实收”按钮，可批量自动生成实收</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
				<li class="div_li"><span class="div_title"><font>10月9日</font></span></li>
				<li><span class="div_content">能源费应收导入功能完善。能源费应收款项每月批量导入系统，实收在“收费明细”中实际录入</span></li>
			</ul>
		</td></tr>
		<tr><td>
			<ul class="div_ul">
				<li class="div_li"><span class="div_title"><font>9月29日</font></span></li>
				<li><span class="div_content">增加实收批量审核功能</span></li>
			</ul>
		</td></tr>
	</table>
</div>
</body>
</html>
