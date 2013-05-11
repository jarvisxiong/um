<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<table width="100%" id="result_table" class="content_table">
	<thead>
		<tr class="header">
			<th style="background: none;" width="30px;">序号</th>
			<th width="60px;">标段划分</th>
			<th class="pd-chill-tip" title="合同主体">
			<div class="ellipsisDiv_full">合同主体</div>
			</th>
			<th class="pd-chill-tip" title="合同内容">
			<div class="ellipsisDiv_full">合同内容</div>
			</th>
			<th class="pd-chill-tip" title="合同属性">
			<div class="ellipsisDiv_full">合同属性</div>
			</th>
			<th width="90px;">合同界面简要</th>
			<th width="90px;">招标前置条件</th>
			<th width="70px;">施工面积㎡</th>
			<th width="30px;">备注</th>
			<th width="60px;">查看详细</th>
			<security:authorize ifAnyGranted="A_CT_ADMIN">
			<th width="55px;">删除</th>
			</security:authorize>
		</tr>
	</thead>
	<tbody style="font-size: 12px;">
		<s:iterator value="conPlanList" status="stat">
			<tr class="mainTr">
				<td class="pd-chill-tip">${stat.index+1}</td>
				<%-- 标段划分--%>
				<td class="pd-chill-tip" title="">
				<div class="ellipsisDiv_full">${ledgerTypeCd}</div>
				</td>
				<%-- 合同主体--%>
				<td class="pd-chill-tip" title="${contMainCd}">
				<div class="ellipsisDiv_full">${contMainCd}</div>
				</td>

				<%-- 合同内容 --%>
				<td class="pd-chill-tip" title="${contContentDesc}">
				<div class="ellipsisDiv_full">${contContentDesc}</div>
				</td>
				<%-- 合同属性 --%>
				<td class="pd-chill-tip" title="${contPropCd}">
				<div class="ellipsisDiv_full">${contPropCd}</div>
				</td>
				<%-- 合同界面简要 --%>
				<td class="pd-chill-tip" title="${ontFaceCd}">
				<div class="ellipsisDiv_full">${contFaceCd}</div>
				</td>
				<%-- 招标前置条件 --%>
				<td class="pd-chill-tip" title="${contInviPreCd}">
				<div class="ellipsisDiv_full">${contInviPreCd}</div>
				</td>
				<%-- 施工面积㎡ --%>
				<td class="pd-chill-tip" title="${consructArea}">
				<div class="ellipsisDiv_full">${consructArea}</div>
				</td>
				<%--备注--%>
				<td class="pd-chill-tip" title="${remark}">
				<div class="ellipsisDiv_full">${remark}</div>
				</td>
				<%--查看详细--%>
				<td class="pd-chill-tip" ctr="n${updatedPositionCd}"
					onclick="openItemPlanDetail('${ctContPlanId}','${ctPlan.ctPlanId}','${updatedPositionCd}');">
				<div class="ellipsisDiv_full">查看详细</div>
				</td>
				<%--删除按钮--%>
				<security:authorize ifAnyGranted="A_CT_ADMIN">
				<td>
				<input type="button" class="btn_red" style="width: 50px;" value="删除" onclick="deleteCtCont('${ctContPlanId}','${ctPlan.ctPlanId}','${updatedPositionCd}');">
				</td>
				</security:authorize>
			</tr>
		</s:iterator>
	</tbody>
</table>
<div style="padding-right: 5px; padding-bottom: 5px; float: right; text-align: center;">
<security:authorize ifAnyGranted="A_CT_RESOLVE">
<input type="button" class="btn_green" value="新增合约" id="btn_addCont" onclick="addCtCont('${ctPlanId}');">
</security:authorize>
</div>

<script type="text/javascript">
function addCtCont(ctPlanId){
	if(ctPlanId == "undefined"){
		alert("请先选择合约大类！");
		return;
		}
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='ctPlanNewAdd'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 370,
		height : 420,
		title : "编辑详细信息",
		closeBtn:true,
		afterShow : function() {
			var url ="${ctx}/ct/ct-cont-plan!planDetail.action";
			$.post(url,{ctPlanId:ctPlanId}, function(result) {
				$("#ctPlanNewAdd").html(result);
			});
		},
		handler : function(btn){
         
			if(btn=='ok'){
			     var _consructArea=$.trim($("input[name=entity.consructArea]").val());
					if(!isNum(_consructArea))
					{
						alert('施工面积输入的数据不合法！');
						$("input[name=entity.consructArea]").focus();
						return;
					}
					$('#ctContAdd').ajaxSubmit(function(result) { 
						
						if(result){
							queryCtContPlan(result,$('#btn_'+result));
						}
					});
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["取消",'cancel']]
	});
}
function deleteCtCont(ctContPlanId,ctPlanId,relationItems){
	if(!isBindEvent()){
		    alert("不能删除合约规划,当前台账信息正在审核中或者已审核通过!");
			return;
		}
	if(ctContPlanId!=null){
		if(relationItems>0){
            alert("该合约规划已与科目关联无法删除!");
            return;
			}
		if(confirm("确定要删除该合约细项吗？")){
			var url="${ctx}/ct/ct-cont-plan!delete.action";
			$.post(url,{ctContPlanId:ctContPlanId},
					function(result) {
						if("success" == result){
							alert("删除成功");
							queryCtContPlan(ctPlanId,$('#btn_'+ctPlanId));
						}else{
							alert("删除失败");
							return false;
						}
					});
		}
	}
}

/**
 * 查看详情
 */
function openItemPlanDetail(ctContPlanId,ctPlanId,relationItemCount){
	if(relationItemCount == "0"){
		return;
		}
	
	ymPrompt.confirmInfo({
		icoCls : "",
		autoClose : false,
		message : "<div id='ctItemConPlanRelation'><img align='absMiddle' src='" + _ctx
				+ "/images/loading.gif'></div>",
				width : 510,
				height : 460,
		title : "查看【目标成本科目】详情",
		closeBtn : true,
		afterShow : function() {
					var _url="${ctx}/ct/ct-cont-plan!relationDetail.action";
			$.post(_url, {ctContPlanId:ctContPlanId}, function(result) {
				$("#ctItemConPlanRelation").html(result);
			});
		},
		handler : function(btn) {
			if (btn == 'ok') {

              if(!isBindEvent()){
                  alert("不能取消分解,当前台账信息正在审核中或者已审核通过！");
                  return;
                  }				
				$('#cancelItemPlanConn').ajaxSubmit(function(result) {
					ymPrompt.close();
					 alert(result);
					 queryCtContPlan(ctPlanId,$('#btn_'+ctPlanId));
				});
			} else {
				ymPrompt.close();
			}

		},
		btn : [["取消分解", 'ok'], ["取消", 'cancel']]
	});	
}
removeEventAndText();
function removeEventAndText(){

          $("td[ctr=n0]").unbind("click");
          
          $("td[ctr=n0]").html("");

	}
</script>