<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>



<textarea rows="" cols="" id="hasSelItemJson" class="chkbxHide">${ctDismantleJson}</textarea>
<div style='margin-right: 5px; margin-left: 5px;'>
<div style="height: 3x;">&nbsp;&nbsp;</div>
<div class='txtRed' style='vertical-align: bottom'>*红色表示"已分解金额"超出"目标成本"。</div>
<div style="height: 3x;">&nbsp;&nbsp;</div>
<div style='vertical-align: middle; height: 30px;'>所有关联的【目标成本科目】</div>
<form action="${ctx}/ct/ct-cont-plan!cancelItemPlanConn.action"
	method="post" id="cancelItemPlanConn">
<div style="width: 498px; height: 320px; border: 1px gray solid;"><input type="hidden" id="itemSelec" value="${itemSelec}" name="itemSelec">
<input type="hidden" value="${ctContPlanSelect}" name="ctContPlanSelect">
<div id="ctSelItemTreePanel"
	style="width: 200px; height: 100%; float: left; overflow: auto; border-right: 1px gray solid;"></div>

<div
	style="width: 280px; float: left; height: 100%; overflow: auto; border: 1px soild #0167A2; margin-left: 5px;">
<div id="ctContPlan"><br />
	<div style="padding-bottom: 5px;">
	合约规则大类： 
		<select style='width: 160px'
			onchange="btRelContPlanDetails(this)" id="ctplanSel" _name='ctplanSel'
			disabled='true'>
			<option value=""></option>
			<s:iterator value="ctPlanList" var="stat">
				<option value="${stat.ctPlanId}">${stat.planName}</option>
			</s:iterator>
		</select>
	</div>
	<div style="padding-bottom: 5px;">
	关联合约规划：
	 <select style='width: 160px' id="ctContPlanSeled" _name="ctContPlanSeled" disabled='true'>
		<option value=""></option>
	</select>
	</div>
</div>
<div id="contPlanDetail"></div>
<input type="hidden" id="ctContPlanId" value="${ctContPlanSelect}" />
 <input type="hidden" id="ctPlanId" value="${ctPlanId}" />
 </div>
</div>
</form>
</div>


<script>
	//主页面
	//加载区域项目树
	var gTreePanel;
	loadAreaProjectTree();
	function loadAreaProjectTree() {

		var _jsontext = $("#hasSelItemJson").val();
		_jsontext = _jsontext.substring(1, _jsontext.length);
		_jsontext = _jsontext.substring(0, _jsontext.length - 1);

		gTreePanel = new TreePanel( {
			renderTo : 'ctSelItemTreePanel',
			'root' : eval('(' + _jsontext + ')'),
			'ctx' : _ctx
		});
		gTreePanel.render();
		gTreePanel.isExpendSelect = true;
		gTreePanel.on(function(node) {
			var nodeType = node.attributes.nodeType;
			//若非根节点 
				if ("0" != nodeType) {
					if (node.isExpand) {
						node.collapse();
					} else {
						node.expand();
					}
				}
			});		

		$("#ctplanSel").attr("value",$("#ctPlanId").val());
		btRelContPlanDetails($("#ctplanSel"));
	}

	function btRelContPlanDetails(selectEle) {

    
    var _ctPlanId = $(selectEle).val();

    if(isEmptyOrNull(_ctPlanId)){
         return;
        }
	var _url =_ctx + "/ct/ct-cont-plan!getCtContPlan.action";
	$.ajax({
				url : _url,
				type : "POST",
				data : "ctPlanId=" + _ctPlanId+"&actType=selectAll" ,
				success : function(responseText) {
		       $("#ctContPlanSeled").empty();
					var _jsonData = toJson(responseText);

					if (_jsonData != undefined && _jsonData.status != undefined) {

						if (_jsonData.status != true) {
							alert("error");
							return;
						}
					} else {
						var _contName="";
                     $.each(_jsonData,function(i,data){
                    		 $("#ctContPlanSeled").append("<option value='0'>"+"请选择"+"</option>");
                    	     $("#ctContPlanSeled").append("<option value='"+data.ctContPlanId+"'>"+data.ledgerTypeCd+"</option>");
                    	    if(data.ctContPlanId==$("#ctContPlanId").val()){
                    	    	_contName=data.ledgerTypeCd;
                        	    }
                         });
                   
                       $("#ctContPlanSeled").val($("#ctContPlanId").val());
                       $("#ctContPlanSeled").attr("title",_contName);
                       
                       contPlanDetail($("#ctContPlanSeled").val());
           
					}
				},
				error : function(responseText) {

				}
			});
    
	}
	function contPlanDetail(ctContPlanId){
		var data={ctContPlanId:ctContPlanId};
		$.post(_ctx+"/ct/ct-cont-plan!contPlanDetail.action",
				data,
				function(result) {
					TB_removeMaskLayer();
					if (result) {
						$("#contPlanDetail").html(result);
					}
					 
				}
			);
}
</script>
