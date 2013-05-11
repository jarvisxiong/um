
	var tree;
	
	//加载左边机构列表,右边2级机构(分管/中心)
	function loadMainPanel(){
		//$("#main_panel").html('<div><image src="'+_ctx+'/images/loading.gif"/>加载中心列表...</div>').show();
		$.post(_ctx+'/plan/work-plan-main!panel.action', function(result) {
			$("#main_panel").html(result);
			//resetMatrixHeight();
		});
	}
	
	//加载右边的机构树,如 宝龙 地产 - 分管 - 中心
	function loadBranchCenterOrgList(){
		$("#branchCenterOrgList").html('<div><image src="'+_ctx+'/images/loading.gif"/>加载中心列表...</div>').show();
		$.post(_ctx+'/plan/work-plan-main!getEstateTreeNode.action', function(result) {
			$("#branchCenterOrgList").html('');
			//alert(result);
			if (result) {
				tree = new TreePanel({
					renderTo: "branchCenterOrgList",
					'root'  : eval('('+result+')'),
					'ctx'	:''+_ctx
				}); 
				//自定义
				tree.delegateOnMouseOutNode = function(elem, node){
					$("#popDiv").slideUp(200);
				};
				tree.delegateOnMouseOverNode = function(elem, node){
					var flag = $(elem).attr("initOnMouseOverFlg");
					//if( flag == "false"){
					//	return;
					//}
					
					if(node.attributes.nodeType != "1"){ 
						return;
					}
					
					var orgTypeCd ;
					var param =  node.attributes.extParam;
					try{
						orgTypeCd = param.split(",")[0];
					}catch(e){ 
						alert(e);
						return;
					}
					
					//2-中心 3-分管:显示2个连接
					if("2" == orgTypeCd){
						loadPopDiv(elem,node.attributes.entityId,node.attributes.entityName,0,150);
						$(elem).attr("initOnMouseOverFlg","true");
					} else{
						$(elem).attr("initOnMouseOverFlg","false");
					}
				}; 
				tree.render(); 
					 
				tree.on(function(node){
					var nodeType = node.attributes.nodeType;
					var orgCd = node.attributes.entityCd;
					var orgName = node.attributes.entityName;
					var orgId = node.attributes.entityId;
	 
					if( $.trim(orgCd) == '' || $.trim(orgCd)=='23'){
					return;
					}
					
					var orgTypeCd ;
					var param =  node.attributes.extParam;
					try{
						orgTypeCd = param.split(",")[0];
					}catch(e){ 
						return;
					}
					
					//机构 2-中心 3-分管
					if(orgTypeCd == "3"){
						if(node.isExpand){
							node.collapse();
						}else{
							node.expand();
						}
					} else if(nodeType == "2"){
					}
				});
			}
		});
	}

	//查看"执行计划总表"
	function gotoExecList(){
		var url = _ctx + "/plan/plan-exec-plan!portal.action";
		window.parent.TabUtils.newTab("work_plan_exec_list","执行总表",url,true);
	}

	//查看"月计划总表"
	function gotoDoubleList(){
		var url = _ctx + "/plan/plan-work2!getAllPlan.action";
		window.parent.TabUtils.newTab("work_plan_double_list","双周总表",url,true);
		 
	}
	
	// 公司的执行计划总表(暂不使用)
	function gotoOrgExec(orgCd){
		var url = _ctx + "/plan/plan-exec-plan!portal.action?projectCd=" + orgCd;
		window.parent.TabUtils.newTab("work_plan_exec_list","执行总表",url,true);
	}
	
	//左边: 查看相关执行计划(地产公司)
	function gotoRelExecPlanEstateOrg(orgCd){
		var url = _ctx + "/plan/plan-exec-plan!getCenterPlanRel.action?projectCd=" + orgCd;
		window.parent.TabUtils.newTab("work_plan_exec_rel_list","地产计划",url,true);
	}

	//右边: 查看相关执行计划(中心)
	function gotoRelExecPlanCenter(centerCd){
		var url = _ctx + "/plan/plan-exec-plan!getCenterPlanRel.action?centerCd=" + centerCd;
		window.parent.TabUtils.newTab("work_plan_exec_rel_list","中心计划",url,true);
	}
	//右边: 查看月计划
	function gotoRelDoublePlan(centerCd){
		var url = _ctx + "/plan/plan-work2!getAllPlan.action?centerCd="+centerCd;
		window.parent.TabUtils.newTab("work_plan_double_list","月计划",url,true);
	}
	
	
	/**
	 * 
	 * @param domId  当前节点ID
	 * @param orgId  当前机构ID
	 * @param offsetHeight 偏移当前节点-纵向
	 * @param offsetWidth  偏移当前节点-横向
	 * @return
	 */
	var popDivMgr;
	function showPopDiv(srcElem,centerId,centerCd,orgName,offsetHeight,offsetWidth){
		
		//$("#popDiv").hide();
		if(popDivMgr)clearTimeout(popDivMgr);
		popDivMgr = setTimeout(function(){
			$.post(_ctx + "/plan/work-plan-main!getTaskList.action",{centerId: centerId,centerCd: centerCd},function(result){
				var $offset = $(srcElem).offset();
				$("#popDiv").css({left:($offset.left+offsetWidth),top:($offset.top+offsetHeight)}).empty().show();//+$(srcElem).height()
				//result = eval(result);
				
				var arr = new Array();
				
				//$.each(result,function(i,node){
				//	arr.push("<div execCd='"+node.execCd+"' execName='"+node.execName+"' execType='1'><span>相关执行计划</span></div>");
				//	arr.push("<div execCd='"+node.execCd+"' execName='"+node.execName+"' execType='2'><span>月计划</span></div>");
				//});
				
				//arr.push("<span>"+orgName+"</span>");
				arr.push("<div execCd='' execName='' execType='1'><span>相关执行计划</span></div>");
				arr.push("<div execCd='' execName='' execType='2'><span>月计划</span></div>");
				
				$("#popDiv").append(arr.join(""));
				
				$("#popDiv div").click(function(){
					var execName = $(this).attr("execName");
					var execCd = $(this).attr("execCd");
					var execType = $(this).attr("execType");
					
					//1-执行计划 2-月计划
					if("1" == execType){ 
						gotoRelExecPlanCenter(centerCd);
					}else if("2" == execType){
						gotoRelDoublePlan(centerCd);
					}
					
					$("#popDiv").slideUp(200);
				});
				$("#popDiv").mouseleave(function(){
					$("#popDiv").slideUp(200);
				});
			});
		}, 300);
			
		$(document).bind('click',function(event){
			var event  = window.event || event;
		    var obj = event.srcElement ? event.srcElement : event.target;
		    //非自己或浮出框
		    if( obj != srcElem && obj != $("#popDiv")){
		    	$("#popDiv").slideUp(200); 
		    }
		    $(document).unbind('click');
		});
	}
	function hiddenPopDiv(){
		//$("#popDiv").hide();
	}
	
	

	//自适应大小
	function resetMatrixHeight() {
		var totalHeight = parent.$("#workplanmain_frame").height(); 
		var bannerHeight = $("#banner").height();
		if (totalHeight) {
			$("#planContainerDiv").height(totalHeight - bannerHeight- 105);
		} else {
			totalHeight = $(document).height();
			$("#planContainerDiv").height(totalHeight - bannerHeight- 70);
		}
	}

	
	
	