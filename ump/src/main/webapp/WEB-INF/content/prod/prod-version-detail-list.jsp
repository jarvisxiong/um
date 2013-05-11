<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>工料基准价版本</title>
	
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
	<script type="text/javascript" src="${ctx}/resources/js/prod/prod.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prod/prod-version-detail.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prod/UtilValidate.js"></script>
</head>
<body>
<%--此form的尾部在另一个页面,在结果列表的JSP页面的table结束点 --%>
<form action="${ctx}/prod/prod-version-detail!loadList.action" id="pageForm" method="post" accept-charset="UTF-8">
	<div id="container">
		<div class="title_bar">
				<div class="fLeft banTitle">工料基准价版本</div>
				<div class="fLeft"> 
					<font style="color: red;font-size: 14px;">当前版本号：</font><%--${currentVersion} --%>
					<select name="versionNo" id="curtVersionNo" onchange="changerVersion(this);" title="${prodBasicVersion.yearCd}-${prodBasicVersion.monthCd}">
						<option value="" >-</option>
						<s:iterator value="versionList" var="v">
							<option ym="${yearCd}-${monthCd}" value="${versionNo}" versionid="${prodBasicVersionId}"
							<s:if test="versionNo==currentVersion">
							selected='true'
							</s:if>
							>${versionNo}</option>
						</s:iterator>						
					</select>	
				</div>				
				<div id="yearAndMonthNav" style="float: left;color: red;margin-left: 5px;" ><s:if test="prodBasicVersion!=null">${prodBasicVersion.yearCd}-${prodBasicVersion.monthCd}</s:if></div>
				<%--取消地区 --%>
				<%--
				<div style="float: left;color: red;margin-left: 5px;" >
					<font style="color: red;font-size: 14px;">地区：</font>
					<select name="areaCd" id="curtAreaCd" onchange="changerVersion(this);">
					<option value="" >-</option>
						<s:iterator value="@com.hhz.ump.util.DictMapUtil@getMapProdAreaCd()" var="v" status="status">
							<s:if test="#status.index==0"></s:if>
							<s:else>
								<option  value="<s:property value="key" />" ovalue="<s:property value="value"/>" ><s:property value="value"/></option>
							</s:else>
							
						</s:iterator>						
					</select>
					
				</div>
				 --%>
				<div class="fRight">				
					<input type="button" class="btn_new btn_add_new" value="新增" id="new"/>						
					<input type="button" class="btn_new btn_blue_new" value="版本号管理" id="versionManage" style="width: 80px;" />
						
		  			<input type="button" class="btn_new btn_full_new" onclick="window.open(location.href)" value="全屏" />
		  			<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href" value="刷新" />				
				</div>
		</div>
		<input type="hidden" name="currentbasicversion" id="currentbasicversion" value="${prodBasicVersion.versionNo}"/>
		<input type="hidden" name="currentbasicversionid" id="currentbasicversionid" value="${prodBasicVersion.prodBasicVersionId}"/>
		
		<div id="divBody">
			<%--结果替换符 --%>
			<div id="rsTable" class="rsTable"></div>
		</div>
	</div>
	  
<script type="text/javascript">
	$(function(){

		//表格行点击监听
		$(".prodTable tbody tr").live('click',function(){				
				var l=$(this).children(); 
				var prodVersionDetailId=$(this).attr('detailId');
				versionDetailWin(prodVersionDetailId);				
			});
		$("#versionManage").live('click',function(){
			versionManage();
			});		
		//新增工料版本
		$("#new").click(function(){
			versionDetailWin('');
		});
		
		//加载列表
		loadVersionDetail('');
		//排序
		$('.sortField').toggle(
			function(){
				$('.sortFieldAsc').removeClass('sortFieldDesc').addClass('sortFieldAsc');
				$('#sort').val($(this).attr('sort'));
				if($('#order').val()=='asc'){
					$('#order').val('desc');
					}else{
						$('#order').val('asc');	
					}
				loadVersionDetail('');	
			},
			function(){
				$('.sortFieldAsc').removeClass('sortFieldAsc').addClass('sortFieldDesc');
				$('#sort').val($(this).attr('sort'));
				if($('#order').val()=='desc'){
					$('#order').val('asc');
					}else{
						$('#order').val('desc');	
					}
				loadVersionDetail('');	
			}
		);
	});

	

	//切换版本号
	function changerVersion(dom){
			var jdom=$(dom);
			var svalue=jdom.find("option:selected").val();
			if(''==svalue||svalue==null||typeof (svalue) == 'undefined'){	
				$("#yearAndMonthNav").html('');
				jdom.attr('title','');
				loadVersionDetail('');			
			}else{
				$("#yearAndMonthNav").html(jdom.find("option:selected").attr('ym'));
				jdom.attr('title',jdom.find("option:selected").attr('ym'));
				loadVersionDetail('');
			}
			
		}
	



	//工料基准维护弹出框
	function versionDetailWin(prodVersionDetailId){
		var btn=[["保存",'ok'],["取消",'cancel']];
		//如果prodVersionDetailId不为空,是编辑,则有删除按钮
		if(prodVersionDetailId)
			btn[2]=["删除",'del'];
		ymPrompt.confirmInfo({
			icoCls : "",
			autoClose:false,
			message : "<div id='versionDetail'><img align='absMiddle' src='"
				+ _ctx + "/images/loading.gif'></div>",
			width : 380,
			height : 250,
			title : "工料基准价版本维护",
			closeBtn:true,
			afterShow : function() {
					var url=_ctx+"/prod/prod-version-detail!getForm.action";
					var data={formType:'newVersionDetailForm'};
					if(prodVersionDetailId)
						data['prodVersionDetailId']=prodVersionDetailId;
					$.post(url,data,function(result){		
						$("#versionDetail").html(result);
						autoHeight();
						//如果为新增,则获取当前选择的版本号
						if(prodVersionDetailId){
							//无操作
							}
						else{
							//版本号
							$("#input_versionno").val($("#curtVersionNo").val());
							if($("#curtVersionNo").val()){
								$("#hidden_versionno").val($("#curtVersionNo").find('option:selected').attr('versionid'));								
								}
							else{
								$("#hidden_versionno").val('');
								}
							//--取消地区
							//$("#hidden_areaCd").val($("#curtAreaCd").val());
							//if($("#curtVersionNo").val()){
							//	$("#input_areaCd").val($("#curtAreaCd").find('option:selected').attr('ovalue'));								
							//	}
							//else{
							//	$("#input_areaCd").val('');
							//	}
						}						
						});
			},
			handler : function(btn){
				//叉叉的关闭事件
				$("div.ymPrompt_close").click(function(){					
					ymPrompt.close();
					});
				//其他事件
				if(btn=='ok'){
					//验证表单
					if(validateVerDetailForm()){
						//如果是新增,判断是否已经存在重复数据
						if(prodVersionDetailId.length<1){
								var data={};
								data['bussinessCd']=$("#input_bussiness").val();
								data['materialZoneCd']=$("#input_materialZone").val();
								data['prodBasicVersionId']=$("#hidden_versionno").val();
								//取消地区
								//data['areaCd']=$("#hidden_areaCd").val();
								var url=_ctx+"/prod/prod-version-detail!hasExsitProdDetail.action";
								$.post(url,data,function(result){	
									var rs=result.split(',');
									//如果存在重复数据	
									if('false'==rs[0]){
										//如果同意继续保存
										if(window.confirm(rs[1])){
											saveProdDetail();
											}
									//如果不存在重复数据直接保存,直接保存
									}else{								
										saveProdDetail();
									}	
								});
							//编辑数据
							}else{
								//执行编辑保存
								saveProdDetail();
						}
						ymPrompt.close();
					}					
				}
				if(btn=='del'){
					 if(window.confirm('确认删除此数据?')){
							//执行删除
						 deleteProdDetail(prodVersionDetailId);
						 }
					 ymPrompt.close();
					}
				if('cancel'==btn){
					ymPrompt.close();
					}
			},
			btn:btn
		});
		}

	//删除工料基准版本数据
	function deleteProdDetail(prodVersionDetailId){
		var url=_ctx+"/prod/prod-version-detail!delete.action";
		var data={};
		if(prodVersionDetailId)
			data['prodVersionDetailId']=prodVersionDetailId;
		$.post(url,data,function(result){
			//加载列表
			loadVersionDetail('');
			});
		}
	
	//保存工料基准版本数据
	function saveProdDetail(){
			//if(window.confirm('确认保存?')){
				TB_showMaskLayer("正在保存...");
				$('#newVersionDetailForm').ajaxSubmit(function(result){
					var rs=result.split(",");
					TB_removeMaskLayer();
					if('success'==rs[0]){
						//加载列表														
						loadVersionDetail('');
					}else{
						alert('提交数据存在异常！'+rs[1].replace('success',''));	
						}
				});
			//}
		}
	
	//跳转到版本管理界面
	function versionManage(){
		openWindow('versionManage','版本号管理',_ctx + '/prod/prod-basic-version!list.action');
		}
	
	//工料基准版本表单验证
	function validateVerDetailForm(){
		//校验空
		if($("#hidden_versionno").val().trim().length<1){
			alert('版本号必选！');
			return false;
			}
		//if($("#hidden_areaCd").val().trim().length<1){
		//	alert('地区必选！');
		//	return false;
		//	}
		if($("#input_bussiness").val().trim().length<1){
			alert('产品业态必选！');
			return false;
			}
		if($("#input_materialZone").val().trim().length<1){
			alert('工料范围必选！');
			return false;
			}
		if($("#input_estimatePrice").val().trim().length<1){
			alert('基准单位估算价格必填！');
			return false;
			}		
		
		//校验大小
		if(eval($("#input_permeterQuantity").val().trim())<=0){
			alert('消耗量(/m2)不能小于等于0');
			return false;
			}
		if(eval($("#input_price").val().trim())<=0){
			alert('工料价格不能小于等于0');
			return false;
			}
		if(eval($("#input_estimatePrice").val().trim())<=0){
			alert('基准单位估算价格不能小于等于0');
			return false;
			}
			
		return true;
		}

	function loadEstimatePrice(){
		var versionNo=$("#hidden_versionno").val();
		//取消地区
		//var areaCd=$("#hidden_areaCd").val();
		var bussinessCd=$("#input_bussiness").val();
		//var materialZoneCd=$("#input_materialZone").val();
		//如果都不为空
			if(versionNo&&bussinessCd){//&&areaCd	  
				var data = {bussinessCd:bussinessCd,versionNo:versionNo};//areaCd:areaCd,							
				var url = _ctx+"/prod/prod-version-detail!loadEstimatePrice.action";
				$.post(url,data,function(result){
					//获取值
					var rs = result.split(',');
					if('success'==rs[0]){
						$("#input_estimatePrice").val(rs[1]);
						}
					else{
						$("#input_estimatePrice").val('');
						}
		
					});
				
				}
		}
</script>
</body>
</html>