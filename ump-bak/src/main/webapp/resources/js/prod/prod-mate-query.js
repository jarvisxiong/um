//工料价格搜索弹出窗
function queryMatePrice(){
		ymPrompt.confirmInfo({
			icoCls : "",
			autoClose:false,
			message : "<div id='queryMatePrice'><img align='absMiddle' src='"
				+ _ctx + "/images/loading.gif'></div>",
			width : 380,
			height : 180,
			title : "工料价格搜索",
			closeBtn:true,
			afterShow : function() {
				var data={formType:'query'};
				//如果导航上面选择了值,获取导航值地区的值
				var areaCd=$("select#nav_areaCd").find('option:selected').val();
				//获取导航值时间
				var ym=$("#nav_ym").val();
				if(areaCd){
					data['areaCd']=areaCd;
				}
				if(ym){
					data['ym']=ym;
				}
				//加载搜索表单
				var url=_ctx+"/prod/prod-material-price!getForm.action";
				$.post(url,data,function(result){		
					$("#queryMatePrice").html(result);	
				});
			},
			handler : function(btn){
				$("div.ymPrompt_close").click(function(){					
					ymPrompt.close();
					});
				if(btn=='query'){
					//执行搜索
					//将搜索条件赋予搜索表单
					//业态
					var bussiness=$("select#q_bussiness").find('option:selected').val();					
					if(bussiness)
						$("input#pmp_input_bussinessCd").val(bussiness);
					else
						$("input#pmp_input_bussinessCd").val('');
					//工料范围
					var materialZone=$("select#q_materialZone").find('option:selected').val();
					if(materialZone)
						$("input#pmp_input_materialzonecd").val(materialZone);
					else
						$("input#pmp_input_materialzonecd").val('');
					//地区
					var areaCd=$("select#q_areaCd").find('option:selected').val();
					if(areaCd)
						$("input#pmp_input_areacd").val(areaCd);
					else
						$("input#pmp_input_areacd").val('');
					//时间
					var ym=$("input#q_ym").val();
					if(ym)
						$("input#pmp_input_monthandyear").val(ym);
					else
						$("input#pmp_input_monthandyear").val('');
					//执行搜索
					jumpPage(1);
					//清空搜索条件
					//clearCriteria();
					//关闭弹出搜索窗口
					ymPrompt.close();
				}
				if(btn=='cancel'){
					ymPrompt.close();	
					}
				
			},
			btn:[["搜索",'query'],["取消",'cancel']]
		});				
	}
//新增、编辑工料价格弹出窗
function matePriceWin(prodMaterialPriceId){
	var wd=380;
	var ht=250;
	var btn=[["保存",'ok']];
	//如果是编辑
	if(prodMaterialPriceId){
		btn[1]=["删除",'del'];
		btn[2]=["取消",'cancel'];
		wd=500;
		ht=350;
	}
	//如果是新增
	else{
		btn[1]=["保存继续",'continue'];
		btn[2]=["取消",'cancel'];
		wd=500;
		ht=350;
	}		
	ymPrompt.confirmInfo({
		icoCls : "",
		autoClose:false,
		message : "<div id='matePrice'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : wd,
		height : ht,
		title : "工料价格维护",
		closeBtn:true,
		afterShow : function() {
				//加载新增、编辑表单
				var url=_ctx+"/prod/prod-material-price!getForm.action";
				var data={formType:'priceForm'};
				//如果是编辑
				if(prodMaterialPriceId){
					data['prodMaterialPriceId']=prodMaterialPriceId;
				}else{
					//如果导航上面选择了值,获取导航值地区的值
					var areaCd=$("select#nav_areaCd").find('option:selected').val();
					//获取导航值时间
					var ym=$("#nav_ym").val();
					if(areaCd){
						data['areaCd']=areaCd;
					}
					if(ym){
						data['ym']=ym;
					}
				}
					
				$.post(url,data,function(result){		
					$("#matePrice").html(result);	
				});
		},
		handler : function(btn){
			$("div.ymPrompt_close").click(function(){					
				ymPrompt.close();
				});
			if(btn=='ok'){
				//验证表单
				if(validateMatePriceForm()){
					//如果是新增,判断是否存在相同的数据
					if(prodMaterialPriceId.length<1){
						var url=_ctx+"/prod/prod-material-price!hasExsitMaterial.action";
						var data={};
						data['monthAndYear']=$("#input_ym").val();
						data['areaCd']=$("#input_areaCd").val();
						data['bussinessCd']=$("#input_bussiness").val();
						data['materialZoneCd']=$("#input_materialZone").val();				
						$.post(url,data,function(result){		
							var rs=result.split(";");
							//如果存在相同数据，则需要用户确认是否覆盖
							if('false'==rs[0]){
								if(window.confirm(rs[1])){
									//保存工料价格
									saveMaterail();
								}
							//如果不存在相同数据	
							}else{
								//直接保存工料价格
								saveMaterail();
								
							}
						});
					//编辑数据
					}else{
						//保存工料价格
						saveMaterail();	
					}	
					ymPrompt.close();
				}
				
			}
			if('continue'==btn){
				//验证表单
				if(validateMatePriceForm()){
					//如果是新增,判断是否存在相同的数据
					if(prodMaterialPriceId.length<1){
						var url=_ctx+"/prod/prod-material-price!hasExsitMaterial.action";
						var data={};
						data['monthAndYear']=$("#input_ym").val();
						data['areaCd']=$("#input_areaCd").val();
						data['bussinessCd']=$("#input_bussiness").val();
						data['materialZoneCd']=$("#input_materialZone").val();				
						$.post(url,data,function(result){		
							var rs=result.split(";");
							//如果存在相同数据，则需要用户确认是否覆盖
							if('false'==rs[0]){
								if(window.confirm(rs[1])){
									//保存工料价格
									saveMaterail();
								}
							//如果不存在相同数据	
							}else{
								//直接保存工料价格
								saveMaterail();
								
							}
						});
					//编辑数据
					}else{
						//保存工料价格
						saveMaterail();	
					}						
				}
			}
			if(btn=='cancel'){
				ymPrompt.close();	
				}
			if('del'==btn){
				doDeleteMatePrice(prodMaterialPriceId);
				ymPrompt.close();	
			}
			
		},
		btn:btn
	});
	}

//保存工料价格
function saveMaterail(){
	TB_showMaskLayer("正在保存...");
	$('#newPriceForm').ajaxSubmit(function(result){
		var rs=result.split(',');
		TB_removeMaskLayer();
		if('success'==rs[0]){														
			//加载列表
			jumpPage(1);
			//ymPrompt.close();
		}else{
			alert('提交数据存在异常！'+rs[1]);	
			}
	});
}
//删除工料价格
function doDeleteMatePrice(matePriceId){	
	if(window.confirm("确认要删除 本数据吗？")){
		var url=_ctx+"/prod/prod-material-price!delete.action";
		var data={matePriceId:matePriceId};
		$.post(url,data,function(result){		
			jumpPage(1);		
		});
	}
	return false;
}
//清除搜索表单的搜索条件
function clearCriteria(){
	$("input#pmp_input_bussinessCd").val('');
	$("input#pmp_input_materialzonecd").val('');
	$("input#pmp_input_areacd").val('');
	$("input#pmp_input_monthandyear").val('');
}
//获取基准工料价格数据 	
function getBasicDetailPrice(){
	if($("#input_ym").val()){
		if($("#input_bussiness").val()){
			if($("#input_materialZone").val()){
				var url=_ctx+"/prod/prod-version-detail!getBasicDetailPrice.action";
				var data={};
				data['monthAndYear']=$("#input_ym").val();
				data['bussinessCd']=$("#input_bussiness").val();
				data['materialZoneCd']=$("#input_materialZone").val();
				data['areaCd']=$("#input_areaCd").val();
				$.post(url,data,function(result){	
					var rs=result.split(',');
					if('success'==rs[0]){
						$("input#inp_basicPrice").val(rs[1]);
					}
				});
			}
		}
	}	
}
//工料当月价格表单验证
function validateMatePriceForm(){
	//校验空
	if($("#input_ym").val().trim().length<1){
		alert('时间必须填！');
		return false;
		}
	if($("#input_areaCd").val().trim().length<1){
		alert('地区必选！');
		return false;
		}
	if($("#input_bussiness").val().trim().length<1){
		alert('产品业态必选！');
		return false;
		}
	if($("#input_materialZone").val().trim().length<1){
		alert('工料范围必选！');
		return false;
		}
	if($("#input_currentMonthPrice").val().trim().length<1){
		alert('工料价格必填！');
		return false;
	}else{
		//判断空
		if(eval($("#input_currentMonthPrice").val().trim())<=0){
			alert('工料价格不能小于0');
			return false;
			}
	}
	
	return true;
	}
//工料价格维护，地区，时间自动搜索
function navAutoQuery(){
	//导航搜索前搜索条件的准备
	prepareNavAutoQuery();
	//执行搜索
	jumpPage(1);
}
//导航搜索前搜索条件的准备
function prepareNavAutoQuery(){
	//获取导航值地区
	var areaCd=$("select#nav_areaCd").find('option:selected').val();
	//获取导航值时间
	var ym=$("#nav_ym").val();
	if(areaCd){
		$("input#pmp_input_areacd").val(areaCd);
	}else{
		$("input#pmp_input_areacd").val('');
	}
	if(ym){
		$("input#pmp_input_monthandyear").val(ym);
	}else{
		$("input#pmp_input_monthandyear").val('');
	}
}