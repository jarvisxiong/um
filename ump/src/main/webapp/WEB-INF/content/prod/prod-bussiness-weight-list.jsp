<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>产品业态权重管理</title>
	
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
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/formValidator/formValidator.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/prod/prod-weight.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prod/UtilValidate.js"></script>
</head>
<body>
	<div id="container"  >
		<div class="bodyHead">
			<div style="padding-left: 5px;">
				<font style="font-size: 14px;font-weight: bold;padding-left: 15p;" >产品业态权重管理</font>
				<div style="float: right;margin-right: 5px;">
				<%--<input type="button" value="搜索" onclick="changeLayout(2);" style="height:26px; line-height:26px;" class="btn_green"></input> --%>					
				<input type="button" value="新增" id="new" style="height:22px; line-height:22px;margin-top: 3px;" class="btn_orange"></input>	
				</div>
			</div>
		</div>
		<div id="nav">			
		</div>		
		<div id="rsTable" class="rsTable">								
		</div>
			
			<div class="weightForm" style="display:none;position: absolute; width: 400px; height: 270px; top: 140px; left; 0 px; display: none; background-color: #fff; border: 1px solid  #AAABB0; padding: 5px;margin-left: 300px;">
				<div class="winHeader">
					<div style="float: left;margin-left: 5px;clear: left;">
						<font style="float: left;font-size: 14px;font-weight: bold;">业态权重</font>						
					</div>
					<div class="close"></div>
					<div class="clear" style="height: 2px;line-height: 2px;"></div>
					<div class="line"></div>
					<div class="clear" ></div>
				</div>
			</div>
		</div>
	
	  
<script type="text/javascript">
	$(function(){
		//加载列表
		loadProdWeight();
		//新增按钮监听
		$("#new").click(function(){
				mateWeightWin('','保存');
			});
		//关闭弹出窗
		$(".close").click(function(){
			$("div.weightForm").fadeOut("slow");
			});
		//显示表格的点击事件绑定
		$(".prodTable tbody tr").live('click',function(){
				var l=$(this).children();			
				var bussinessCd=l.eq(1).attr('bussinessCd');
				mateWeightWin(bussinessCd,'修改');
			});
		});
	//保存编辑弹出窗
	function mateWeightWin(bussinessCd,btn1){
		var btn=[[btn1,'ok'],["取消",'cancel']];
		if('修改'==btn1){
				btn[2]=["删除",'del'];
			}
		ymPrompt.confirmInfo({
			icoCls : "",
			autoClose:false,
			message : "<div id='mateWeight'><img align='absMiddle' src='"
				+ _ctx + "/images/loading.gif'></div>",
			width : 300,
			height : 200,
			title : "业态权重维护",
			closeBtn:true,
			afterShow : function() {
					var url=_ctx+"/prod/prod-bussiness-weight!weightForm.action";
					var data;
					if(bussinessCd)
						data={bussinessCd:bussinessCd};
					$.post(url,data,function(result){		
						$("#mateWeight").html(result);	
						autoHeight();
						});
			},
			handler : function(btn){
				//叉叉关闭事件
				$("div.ymPrompt_close").click(function(){					
					ymPrompt.close();
					});
				//其他事件
				if(btn=='ok'){
					//验证表单
					if(validateWeightForm()){
						//如果是新增					
						if('保存'==btn1){
							//判断是否已经存在数据
							var url1=_ctx+"/prod/prod-bussiness-weight!hasWeight.action";
							if($("select option:selected").val()){
								bussinessCd=$("select option:selected").val();
								}
							$.post(url1,{bussinessCd:bussinessCd},function(result){		
								  var rs=result.split(',');						  
								  if('false'==rs[0]){
									  //已经存在此业态的数据，需用户确认保存
									  if(window.confirm('已经存在此业态的数据,是否继续保存覆盖原有数据?')){
										  	saveWeight();
										  }
									  }
								  else{
									  //不存在此业态数据，直接保存
									 	 saveWeight();
									  }
								});
						}else{
							//编辑数据，确认直接保存
							 if(window.confirm('确认修改原有数据?')){
								saveWeight();
							 }
							}					
						ymPrompt.close();
					}
				}
				if('cancel'==btn){
					ymPrompt.close();
					}
				//删除
				if('del'==btn){
					if(window.confirm('确认删除？')){
							var url1=_ctx+"/prod/prod-bussiness-weight!delete.action";
							$.post(url1,{bussinessCd:bussinessCd},function(result){
								   ymPrompt.close();
									//加载列表		
									loadProdWeight();
								});
						}
					}
				
			},
			btn:btn
		});
		}
	//保存权重
	function saveWeight(){
		TB_showMaskLayer("正在保存...");
		 $('#newProdWeightForm').ajaxSubmit(function(result){
				TB_removeMaskLayer();
				if('success'==result){														
					loadProdWeight();
				}else{
					alert('提交数据存在异常！');	
					}
			});
		}
	//权重表单验证
	function validateWeightForm(){
		//业态判空		
		if($("#input_bussiness").val().trim().length<1){
			alert('业态必选！');
			return false;
			}
		var wt=$("#input_weight").val().trim();
		if(!floatValidate(wt,'权重',7,6)){
			return false;
			}
		//描述判空
		var weightDesc=$("#input_weightDesc").val().trim();				
		if(!validateLenth(weightDesc,46,'描述 ')){
			//alert('描述 不能超过46个字符！');
			return false;
			}
				
				
		return true;
		}
	function round_decimals(original_number , decimals)  
	{  
	    var result1 = original_number * Math.pow(10 , decimals);  
	    var result2 = Math.round(result1);  
	    var result3 = result2 / Math.pow(10 , decimals);  
	  
	    return(result3);  
	}
</script>
</body>
</html>