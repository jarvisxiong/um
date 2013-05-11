<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/global.jsp" %>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 	
	<link rel="stylesheet" type="text/css" media="screen" href="/PowerDesk/resources/css/common/select.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="/PowerDesk/resources/js/jqueryplugin/jqModal/jqModal.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cost/cost.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/superTables.css"  />	
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jqueryplugin/jqModal/jqModal.css"/>
	
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/superTables.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/cost/cost-cont-plan-tpl-stat.js"></script>
	<style type="text/css">
	table{ border-collapse:collapse; }
	th{line-height:22px; padding:0px; border:0px;}
	.intable td{ border:#cccccc solid 1px; line-height:20px; padding-left:5px; padding-top:5px; }
	.intable td input{ border:0px; border:solid 0px #999999; height:20px; width:90%;}
	.margindiv{ height:450px; min-height:200px; padding: 0px; overflow:hidden;}
	.clickCheck{background-color: #cccccc;color:white;}
	<!--
	#addContentformDiv{width:650px; height:125px; font-size:12px; background:#FFFFFF; border:1px solid #0167A2;}
	#addContentformDiv .contentinput{margin-left:5px;margin-top:3px;}
	-->
	
	.fakeContainer { /* The parent container */
   	 	padding: 0px;
    	border: none;
    	width: 100%; /* Required to set */
    	height: 70%; /* Required to set */
    	overflow: hidden; /* Required to set */
	}
	#MyTable .input1 {
		height: 25px;
		border: 0 none;
		padding-right: 1px;
		text-align: right;
		width: 100%;
		text-align: left;
		background: #F7EDE4;
		border: 0 none;
	}
	#MyTable .input1:hover {
	
		border: 0 none;
		padding-right: 1px;
		width: 100%;
		background: #FFFFFF;
		border: 0 none;
	}
	</style>
	
<title>合约规划模板</title></head>
<body>
	<div id="mailMianContainer" >
	<div class="title_bar">
		<div style="float:left;margin-right: 5px;">
			<img src="${ctx}/images/fin/pic_Supplier.gif" style="margin-top:3px;"/>
		</div>
		<div style="float:left;">
			合约规划成本汇总
		</div>
		<%-- 操作提示 --%>
		<span id="operateResultTip" style="color:red;margin-left:30px;"></span>
	</div>
	<!-- mailInfo end -->
	<div id="maiMainBottom" style="width:100%; height:70%;">
		<form id="planForm" name="planForm" method="post" action="${ctx}/cost/cost-cont-plan-tpl-stat!save.action">
			<input id="trcount" type="hidden" value="0"/>
		    <div class="margindiv">
			      <table id="MyTable" width="100%" height="70%" border="1" cellpadding="0" cellspacing="0" class="intable">
			        <tr style=" border:#cccccc solid 1px;  " >
					  <th width="5%" height="30" scope="col">序号</th>
					  <th width="40%" scope="col">标段/合同名称</th>
					  <th width="15%" align="center" scope="col">合约子目标成本</th>
					  <!-- <th width="15%" align="center" scope="col">定标差异（仅动态版）</th> -->
					  <th width="40%" align="center" scope="col">备注</th>
					</tr>	
		
					<s:if test="costContPlanTplStatList_03!=null">
						<s:iterator value="costContPlanTplStatList_03" var="contractPlan" status="L" >
							<s:if test="#contractPlan.subjectCd==1||#contractPlan.subjectCd==2||#contractPlan.subjectCd==3||#contractPlan.subjectCd==4||#contractPlan.subjectCd==5||#contractPlan.subjectCd==6||#contractPlan.subjectCd==7||#contractPlan.subjectCd==8||#contractPlan.subjectCd==9">
								<tr id="${contractPlan.subjectCd}_${contractPlan.rowTypeCd}" rowNo="${contractPlan.subjectCd}" rownum="${contractPlan.costContPlanTplStatId }" num="${contractPlan.costContPlanTpl.costContPlanTplId}" onmouseover="changeBg(this);" onmouseout="changeBgOut(this);">
									<td height="25" align="center">${dispOrderDesc}</td>
									<td><input type="hidden" name="rowTypeCd${L.index }" value="${rowTypeCd }" />${statName}</td>
									<s:if test="#contractPlan.rowTypeCd==2||#contractPlan.rowTypeCd==3||#contractPlan.rowTypeCd==4">
										<td><input identity="${subjectCd}_${rowTypeCd}" pid="contSubTargAmt_${contractPlan.subjectCd}" name="contSubTargAmt" rowTypeCd="contSubTargAmt_${rowTypeCd}" type="text" id="contSubTargAmt" value="${contSubTargAmt }" title="${contSubTargAmt }" maxlength="18" onblur="contractSave(this);toDecimal(this);"/></td>
										<!--<td><input name="plan${L.index}h" type="text" id="plan${L.index}h" value="${subdiff}" title="${subdiff}"/>${subdiff}</td>-->
									</s:if>
									<s:elseif test="#contractPlan.rowTypeCd!=2||#contractPlan.rowTypeCd!=3||#contractPlan.rowTypeCd!=4">
										<td><input identity="${subjectCd}_${rowTypeCd}" pid="contSubTargAmt_${contractPlan.subjectCd}" name="contSubTargAmt" type="hidden" id="contSubTargAmt" value="${contSubTargAmt }"/><span>${contSubTargAmt }</span></td>
										<!--<td>${subdiff}</td>-->
									</s:elseif>
									<td><input class="icls${contractPlan.costContPlanTplStatId }" name="remark" type="text" id="remark" value="${remark }" title="${remark }" maxlength="200" onblur="contractSave(this);"/></td>
								</tr>
							</s:if>
							<s:else>
								<tr rowNo="${contractPlan.subjectCd}" rownum="${contractPlan.costContPlanTplStatId }" rowsub="${contractPlan.costContPlanTpl.costContPlanTplId }" subcd="${contractPlan.subjectCd}" rowcd="${contractPlan.rowTypeCd }" markId="${contractPlan.subjectCd}_${contractPlan.rowTypeCd }" onmouseover="changeBg(this);" onmouseout="changeBgOut(this);">
									  <td height="25" align="center">${dispOrderDesc}</td>
									  <s:if test="#contractPlan.rowTypeCd=='1030'">
									  	<td style="height:25px;">
									  		<div style="float:right;width:10%;height:25px;" class="btn_refresh jqModal" id="addRowOne">增加行</div>
									  		<div style="float:left;width:80%">${statName}</div>
									  		<td><input identity="${subjectCd}_${rowTypeCd}" pid="contSubTargAmt_${contractPlan.subjectCd}" name="contSubTargAmt" type="hidden" id="contSubTargAmt"  value="${contSubTargAmt }"/><span>${contSubTargAmt }</span></td>
									  	</td>
									  </s:if>
									  <s:if test="#contractPlan.rowTypeCd=='1050'">
									  	<td><input tid="text" name="statName" class="input1" type="text" id="statName"  value="${statName}" maxlength="100" onblur="contractSave(this);"/></td>
									  	<td><input identity="${subjectCd}_${rowTypeCd}" pid="contSubTargAmt_${contractPlan.subjectCd}" name="contSubTargAmt" type="text" id="contSubTargAmt"  value="${contSubTargAmt }" onblur="contractSave(this);toDecimal(this);"/> </td>
									  </s:if>
									  <s:if test="#contractPlan.rowTypeCd=='1031'||#contractPlan.rowTypeCd=='1051'">
											<td style="height:25px;">
												<div style="float:right;width:10%;height:25px;" class="btn_refresh jqModal delRowOne" >删除行</div>
												<div style="float:left;width:80%">
													<input name="statName" class="input1" type="text" id="statName"  value="${statName}" maxlength="100" onblur="contractSave(this);"/>
												</div>
											</td>
											<td><input identity="${subjectCd}_${rowTypeCd}" pid="contSubTargAmt_${contractPlan.subjectCd}" name="contSubTargAmt" type="text" id="contSubTargAmt"  value="${contSubTargAmt }" onblur="contractSave(this);toDecimal(this);"/> </td>
											<!--<td><input name="subdiff" type="text" id="text" value="${subdiff}" /></td>-->
											<td><input class="icls${contractPlan.costContPlanTplStatId }" name="remark" type="text" id="remark" value="${remark }"  title="${remark }" maxlength="200" onblur="contractSave(this);"/></td>
									  </s:if>
									  <s:else>
									  	  <s:if test="#contractPlan.rowTypeCd!='1030'&&#contractPlan.rowTypeCd!='1050'">
										  	<td>${statName}</td>
										  	<s:if test="#contractPlan.contSubTargAmt>='0' && #contractPlan.rowTypeCd=='1090'">
										  		<td><input identity="${subjectCd}_${rowTypeCd}" pid="contSubTargAmt_${contractPlan.subjectCd}" name="contSubTargAmt" type="hidden" id="contSubTargAmt" value="${contSubTargAmt }"/><span>未超过目标成本</span></td>
										   	</s:if>
										
										   	<s:elseif test="#contractPlan.contSubTargAmt<'0'&&#contractPlan.rowTypeCd=='1090'">
										   		<td><input identity="${subjectCd}_${rowTypeCd}" pid="contSubTargAmt_${contractPlan.subjectCd}" name="contSubTargAmt" type="hidden" id="contSubTargAmt" value="${contSubTargAmt }"/><span style="color:red;">超过目标成本</span></td>
										   	</s:elseif>
										   	<s:else>
										   		<td><input identity="${subjectCd}_${rowTypeCd}" pid="contSubTargAmt_${contractPlan.subjectCd}" name="contSubTargAmt" type="hidden" id="contSubTargAmt" value="${contSubTargAmt }"/><span>${contSubTargAmt }</span></td>
										   	</s:else>
										    <!--<td><input name="plan${L.index}h" type="hidden" id="plan${L.index}h" value="${subdiff}" />${subdiff}</td>-->
										  </s:if>
										  <!--<td><input name="subdiff" type="text" id="text" value="${subdiff}" /></td>-->
										  <td><input class="icls${contractPlan.costContPlanTplStatId }" name="remark" type="text" id="remark" value="${remark }" title="${remark }" maxlength="200" onblur="contractSave(this);"/></td>	 		
									  </s:else>
									  
								</tr>
							</s:else>
						</s:iterator>
					</s:if>
					<s:else>
						<script type="text/javascript">
							$(function(){
								$(".sData").removeAttr("style");
							});
						</script>
						<tr>
							<td colspan="4" style="text-align: center;" >查无合约计划，请联系管理员授权项目权限！</td>
						</tr>
					</s:else>
			      </table>
		      </div>
	    </form>
	</div>
</div>
</body>
</html>

