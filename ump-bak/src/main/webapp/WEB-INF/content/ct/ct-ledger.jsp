<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>目标成本台账</title>
<meta http-equiv="Content-Type" content="text/html" />
<%@ include file="/common/global.jsp"%>
<%@ include file="/common/meta.jsp"%>
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/res/resCss.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/resources/css/common/select.css"></link>
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/ct/ct.css" />

<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/ct/ct-ledger.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js">
	
</script>

<script src="${ctx}/resources/js/jquery/jquery.select.js" type="text/javascript"></script>
</head>
<body>
<div id="bodyHead" class="bodyHead">
<div class="headTitle" style="margin-top: 0px;width: 100%">
 <span style="font-weight: bolder;font-size:14px;color:#333333; padding-right: 10px;">目标成本台账</span>
 下载:
				<s:url id="ct" action="download" namespace="/app" includeParams="none" >
			  	  <s:param name="fileName">ctLedgerdownTemplet1.doc</s:param>
			  	  <s:param name="realFileName">项目成本分类作业指引.doc</s:param>
			  	  <s:param name="bizModuleCd">ctDownload</s:param>
				</s:url>
<a href="${ct}" style="color: #0167A2; ">项目成本分类作业指引</a> &nbsp;
				<s:url id="ct" action="download" namespace="/app" includeParams="none" >
			  	  <s:param name="fileName">ctLedgerdownTemplet2.xls</s:param>
			  	  <s:param name="realFileName">合约规划作业指引.xls</s:param>
			  	  <s:param name="bizModuleCd">ctDownload</s:param>
				</s:url>
<a href="${ct}" style="color: #0167A2;">合约规划作业指引</a>
<div style="float: right;padding-right: 14px">
	<security:authorize ifAnyGranted="A_CT_ADMIN">
	<!-- 配置业态、分配权限 -->
		<input class="btn_green" style="line-height: 20px ;height:20px;" type="button" onclick="loadCtOperation()" value="配置业态" />
		<input class="btn_green" style="margin-left: 1px;height: 20px;line-height: 20px ;"  type="button" onclick='manageRole();' value="权限管理"/>
	</security:authorize>
			
	
</div>
</div>
</div>
<table cellpadding="0" cellspacing="0" border="0" style="width: 100%;">
	<col style="width: 200px;" />
	<col />
	<tr>
		<td valign="top">
		<div id="leftTreePanel" class="leftPanel"><%--这里是项目树,初始化页面加载 --%></div>
		</td>
		<td valign="top">
		<div class="mainContent">
		<div id="content">
		<div id="quickClickPanel" style="margin-bottom: 10px; padding-bottom: 20px; height: 15px; line-height: 20px; border-bottom: 1px solid #aaabb0; vertical-align: top;">
		<div style="float: left; margin-top: 10px; margin-left: 5px;">
		<security:authorize ifAnyGranted="A_CT_ADMIN,A_CT_ADD">
		<!--增加	-->
		<input id="newBnt" class="btn_orange" type="button" style="height:20px;line-height: 20px ;" value="新增" />
		</security:authorize>
		</div>
		<security:authorize ifAnyGranted="A_CT_READ">
		<!-- 查看-->
		<div id="quickBidSenior" class="quickSenior" onclick="searchSenior()" style="float: left; width: 150px; padding-top: 10px;">&nbsp;&nbsp;</div>		
		</security:authorize>
	   </div>
		<div style="clear: left; display: block;" id="billContentDiv">
		<div align="center" class="billContent">
		<form action="ct-ledger!save.action" method="post" id="templetForm" class="contractPaymentBill"><input type="hidden" name="resAuthTypeCd" id="resAuthTypeCd" value="CBCGGL_QQCB_40" />
		 <input type="hidden" name="id" id="resApproveInfoId" value="" /> 
		 <input type="hidden" name="recordVersion" id="recordVersion" value="0" /> 
		 <input type="hidden" name="entityTmpId" id="entityTmpId" value="nHruMsjABJ" />
		<table class="mainTable">
			<col width="110" />
			<col />
			<col width="125" />
			<col />
			<tr>
				<td class="td_title">审批权限</td>
				<td colspan="3">
				<input id="approvePrivFlg" class="group" type="checkbox" value="1" name="approvePrivFlg">与酒店有关
				</td>
			</tr>
			<tr>
				<td class="td_title"><span class="txtRed">*</span>项目名称</td>
				<td colspan="3">
				<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
					<col />
					<col width="20" />
					<col width="40" />
					<col width="20" />
					<tr>
						<td>
						<input validate="required" type="text" name="projectName"  id="projectName" readonly="readonly"
							class="orderNoTip projSelect" style="cursor: pointer;" /> 
							<input type="hidden" id="projectCd" name="projectCd" value="" /></td>
						<td style="text-align: right;">(</td>
						<td><input class="inputBorder" type="text" name="periods" value="" /></td>
						<td>)期</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">开工时间</td>
				<td><input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="startDate" value="" /></td>
				<td class="td_title">交房时间</td>
				<td><input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="handDate" value="" /></td>
			</tr>

			<tr>
				<td class="td_title">总建筑面积(㎡)</td>
				<td><input class="inputBorder" validate="required" type="text"  value="" onblur="formatVal($(this));" ctrType='num' index='1'/>
					<input type="hidden" name="totalConsArea" ctrNum='num1' /></td>
				<td class="td_title">计入容积率建筑面积(㎡)</td>
				<td><input class="inputBorder" validate="required" type="text" value="" onblur="formatVal($(this));" ctrType='num' index='2'/>
				<input type="hidden" name="plotRateArea" ctrNum='num2' /></td>
			</tr>
			<tr>
				<td class="td_title"><span class="txtRed">*</span>目标成本总额(元)</td>
				<td><input class="inputBorder" validate="required" type="text"  value="" onblur="formatVal($(this));" ctrType='num' index='3'/>
				<input type="hidden" name="costTargetTotalAmt" ctrNum='num3' />
				</td>
				<td class="td_title">容积率面积单方造价(元/㎡)</td>
				<td><input class="inputBorder" validate="required" type="text" value="" onblur="formatVal($(this));" ctrType='num' index='4' />
				<input type="hidden"  name="plotRateUnitAmt" ctrNum='num4' /></td>
			</tr>
			<tr>
				<td class="td_title">主要内容及说明</td>
				<td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" name="mainContentDesc"></textarea></td>
			</tr>
			<tr>
				<td colspan="4">
				<div style="float: left; padding-left: 85px;">
				<div style="float: left;" class="execSearch plblue" id="btSave">保存</div>
				<div style="float: left;" class="execSearch plblue"  id="btCollapse">收起</div>
				</div>
				</td>
			</tr>

		</table>





		</form>
		</div>

		</div>
		<%-- 右边内容  --%> <s:form id="mainFormSearch" action="ct-ledger!loadList.action" method="post">

			<input type="hidden" id="searchPageNo" value="1" name="page.pageNo" />

			<%-- 排序字段 --%>
			<input type="hidden" id="sort" name="sort" />
			<input type="hidden" id="order" name="order" />

			<%-- 选中的项目公司 --%>
			<input type="hidden" id="selectItemCds" name="selectItemCds" />


			<%-- 快速搜索 --%>
			<div id="seniorPanel" class="seniorPanel">


			<table class="" cellpadding="0" cellspacing="0" style="width: 100%;">
				<col width="100px" />
				<col width="20%px" />
				<col width="100px" />
				<col />

				<tr>
					<th>总面积:</th>
					<td><input style="width: 123px;" type="text" name="ctTotalArea1" id="ctTotalArea1" maxLength='9' /></td>
					<th>至</th>
					<td><input style="width: 123px;" type="text" name="ctTotalArea2" id="ctTotalArea2" class="selectInput" maxLength='9' /></td>
				</tr>


				<tr>
					<th>开工日期:</th>
					<td><s:textfield cssStyle="width:120px;" name="ctStartDate1" onfocus="WdatePicker()" cssClass="Wdate selectInput" /></td>
					<th>至</th>
					<td><s:textfield cssStyle="width:120px;" name="ctStartDate2" onfocus="WdatePicker()" cssClass="Wdate" /></td>
				</tr>
				<tr>
					<th>交房日期:</th>
					<td><s:textfield cssStyle="width:120px;" name="ctHandDate1" onfocus="WdatePicker()" cssClass="Wdate" /></td>
					<th>至</th>
					<td><s:textfield cssStyle="width:120px;" name="ctHandDate2" onfocus="WdatePicker()" cssClass="Wdate" /></td>
				</tr>
				<tr>
					<th>目标总额:</th>
					<td><input style="width: 123px;" type="text" name="ctTargetTotalAmt" id="ctTargetTotalAmt" class="selectInput" maxLength='9' /></td>
					<th>至</th>
					<td><input style="width: 123px;" type="text" name="ctTargetTotalAmt1" id="ctTargetTotalAmt1" class="selectInput" maxLength='9' /></td>
				</tr>

				<tr>
					<th>分解总额:</th>
					<td><input style="width: 123px;" type="text" name="ctRateUnitAmt" id="ctRateUnitAmt" class="selectInput" maxlength='9' /></td>
					<th>至</th>
					<td><input style="width: 123px;" type="text" name="ctRateUnitAmt1" id="ctRateUnitAmt1" class="selectInput" maxlength='9' /></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2"><security:authorize ifAnyGranted="A_BID_QUERY,A_ADMIN_BID">
						<div style="float: left; margin: 5px; background-color: #6EB1CF; color: #FFFFFF; cursor: pointer; line-height: 20px; text-align: center; width: 65px; border: 1px solid #45738d;"
							class="plblue" onclick="searchBidLedger();">搜索</div>
						<div style="float: left; margin: 5px; background-color: #6EB1CF; color: #FFFFFF; cursor: pointer; line-height: 20px; text-align: center; width: 65px; border: 1px solid #45738d;"
							class="plblue" onclick="resetSeniorPanel();">清空</div>
					</security:authorize></td>
					<td></td>
				</tr>

			</table>
			</div>

			<div id="searchResultPanel" style="clear: left;"></div>
		</s:form></div>
		</div>
		</td>
	</tr>
</table>
<script type="text/javascript">
//项目权限管理
function manageRole(){
	openWindow('ctProjectRole','项目权限管理', '${ctx}/ct/ct-ledger-role!list.action');
}
</script> 
</body>
</html>