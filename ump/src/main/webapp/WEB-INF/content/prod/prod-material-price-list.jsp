<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>工料价格维护</title>
	
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/prod/prod.css"  />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>			
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prod/prod-mate-price.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prod/UtilValidate.js"></script>
</head>
<body>
	<div id="container">
		<div class="bodyHead">
			<div style="padding-left: 5px;">
				<font style="float:left;font-size: 14px;font-weight: bold;padding-left: 15p;" >工料价格维护</font>
				<div style="float: left;margin-left: 5px;">
					<font style="font-weight: lighter;color: red;font-size: 14px;">地区：</font>
					<s:select onchange="navAutoQuery();" name="areaCd" id="nav_areaCd" list="@com.hhz.ump.util.DictMapUtil@getMapProdAreaCd()" listKey="key" listValue="value" cssStyle="width:100px;"></s:select>
					<font style="font-weight: lighter;color: red;font-size: 14px;margin-left: 10px;">时间：</font>
					<input  id="nav_ym"  class="date ipt" type="text" style="color:red;width:80px;margin-left: 0px;" name="monthAndYear" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM'});" onchange="navAutoQuery();"/>
				</div>
				<div style="float: right;margin-right: 5px;">
					<input type="button" value="高级搜索" onclick="queryMatePrice();" class="btn_new btn_senior_new" />					
					<input type="button" value="新增" id="new" class="btn_new btn_add_new" />					
				</div>
			</div>
		</div>
		
		<div id="divBody">
				<%--结果替换符 --%>
				<form action="${ctx}/prod/prod-material-price!loadList.action" id="pageForm" method="post" accept-charset="UTF-8">	
					<div id="rsTable" class="rsTable"></div>
					<input type="hidden" name="bussinessCd" id="pmp_input_bussinessCd"></input>
					<input type="hidden" name="materialZoneCd" id="pmp_input_materialzonecd"></input>
					<input type="hidden" name="areaCd" id="pmp_input_areacd"></input>
					<input type="hidden" name="monthAndYear" id="pmp_input_monthandyear"></input>
									
				</form>				
		</div>
	</div>
	  
<script type="text/javascript">
	$(function(){
		//表格行绑定点击事件
		$(".prodTable tbody tr").live('click',function(e){				
				var l=$(this).children();				
				var prodMaterialPriceId=$(this).attr('prodmaterialpriceId');
				matePriceWin(prodMaterialPriceId);
							
			});
		//新增工料价格监听
		$("#new").click(function(){
			$("input#prodMaterialPriceId").val('');
			matePriceWin('');
		});
		$(".plred").live('click',function(event){
				event.stopPropagation();
				var matePriceId=$(this).attr('matepriceid');
				if(window.confirm("确认要删除 本数据吗？")){
					var url=_ctx+"/prod/prod-material-price!delete.action";
					var data={matePriceId:matePriceId};
					$.post(url,data,function(result){		
						loadMatePrice();		
					});
				}
			 return false;		
		});
		
		//加载列表
		loadMatePrice();
	});



</script>
<script type="text/javascript" src="${ctx}/resources/js/prod/prod-mate-query.js"></script>
</body>
</html>