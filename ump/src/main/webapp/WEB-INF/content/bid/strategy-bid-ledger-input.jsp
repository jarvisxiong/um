<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>战略投标(主界面)</title>
	
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bid/bid.css"  />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bid/strategy-bid-ledger.js"></script>
	
	<style type="text/css">
		.add_panel th{
			text-align: right;
			line-height: 25px;
			padding-left: 5px;
			width:60px;
			padding:3px;
			font-weight: normal;
		}
		.add_panel td{
			text-align: left;
			line-height: 25px;
			padding-left: 5px;
			width:150px;
			padding:3px;
		}
		
		.btn_new {
		    border: medium none;
		    cursor: pointer;
		    height: 26px;
		    line-height: 26px;
		   /* margin: 2px;*/
		    text-align: center;
		    width: 60px;
		}
		
		table .required {
    -moz-border-bottom-colors: none;
    -moz-border-image: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-color: #999999 #999999 #999999 red;
    border-style: solid;
    border-width: 1px 1px 1px 2px;
    height: 20px;
}
	</style>
</head>
<body>

	<div id="bodyHead"  class="title_bar">
		<div class="fLeft banTitle">
			招标平台（战略）
			<%--暂时不启用文档下载导航 --%>
			<%--
				<font style="font-weight: lighter;">
					<div style="float: left;margin-left: 15px;">
					<font style="font-weight: lighter;float: left;">下载:</font>
					<div style="float: left;margin-left: 5px;">
					<ul style="list-style:none; padding-left: 10px;">
					<li style="float:left; padding-right:20px; color: #0693e3; ">
						<s:url id="bidLedgerManual" action="download" namespace="/app"  includeParams="none"  >
						  	  <s:param name="fileName">bidLedgerManual.doc</s:param>
						  	  <s:param name="realFileName">系统操作手册.doc</s:param>
						  	  <s:param name="bizModuleCd">public</s:param>
							</s:url>
							<a href="${bidLedgerManual}">系统操作手册.doc</a>
							</li>
							<li style="float:left; padding-right:20px; color: #0693e3; ">
							<s:url id="supbidVedio" action="download" namespace="/app"  includeParams="none"  >
							  	  <s:param name="fileName">supbidVedio.rar</s:param>
							  	  <s:param name="realFileName">供应商官网操作视频.rar</s:param>
							  	  <s:param name="bizModuleCd">public</s:param>
								</s:url>
							<a href="${supbidVedio}">供应商官网操作视频.rar</a></li>
					</ul>
					</div>				
					</div>
				</font> 
			 --%>
		</div>	
		
		<div class="fRight">
			<input id="newBnt" type="button" class="btn_new btn_add_new" onclick="addNewStrategy();" value="新增"/> 
			<input id="quickBidSenior" type="button" class="btn_new btn_senior_new"  style="width:80px;" value="高级搜索"/>
			<input type="button" class="btn_new btn_full_new" onclick="window.open(location.href);" style="width:80px;margin-left:-0.2px" value="全屏"/>
			<%-- 
			<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href;" style="width:80px;margin-left:-0.2px" value="刷新"/>--%>
		</div>
	</div>
	<div style="clear:left;">
		<%-- 快速搜索 --%>
		<div class="fRight" id="quickClickPanel" style="line-height: 30px;">
			<security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN,A_BID_STRA_VIEW">
	   			<!-- 存放,号分割的选中状态列表,eg:1,2,3,4 -->						   			
	   			<span class="quickBidItem" typeCd="99" >全部</span>
	   			<span class="quickBidItem" typeCd="9" titie="点击查看关闭状态的招标记录">关闭</span>
				<span class="quickBidItem" typeCd="4" titie="点击查看中标状态的招标记录">中标</span>
				<span class="quickBidItem" typeCd="3" titie="点击查看评标状态的招标记录">评标</span>
				<span class="quickBidItem" typeCd="2" titie="点击查看投标状态的招标记录">投标</span>
				<span class="quickBidItem" typeCd="1" titie="点击查看邀标状态的招标记录">邀标</span>
				<span class="quickBidItem" typeCd="0" titie="点击查看导入状态的招标记录">导入</span>
				<span class="quickBidTitle"  style="float:right;padding-right: 5px;">快速搜索:</span>																													
			</security:authorize>
			<security:authorize ifNotGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN,A_BID_STRA_VIEW">
				<security:authorize ifAnyGranted="A_BID_STRA_EVAL">	
					<span class="quickBidItem" typeCd="3" titie="点击查看评标状态的招标记录">评标</span>
				</security:authorize>
			</security:authorize>
		</div>
	</div>
	<div class="mainContent" id = "content" style="clear:right;">

   		<%-- 右边内容  --%>
		<div id="newBidLedger" style="display:none ;width:100%;border:1px solid #cbcbcb;" >
			<form action="strategy-bid-ledger!save.action" method="post">		
				<table id="addNewbidLedger"  class="add_panel" style="width:700px;">
					<tr>
						<th align="right" style="width:70px;">战略名称</th>
						<td >
							<input type="text" class="required" name="entity.bidSectionName" id="inp_bidSectionName" class="required" validate="required" title="保存后不可修改"/>						</td>
						<th align="right" style="width:100px;">邀请类别</th>
						<td style="width:200px;" class="chkGroup"  validate="required" >
							<s:checkbox name="showFlag" id="showFlag" cssClass="group"></s:checkbox>明标
							<s:checkbox name="hideFlag" id="hideFlag" cssClass="group"></s:checkbox>暗标						
						</td>
				  </tr>
				  <tr>
					  <th align="right" style="width:70px;">项目</th>
					  <td>
					  <input type="text" style="cursor: pointer;"  class="required" id="projectName" name="projectName" validate="required" readonly="readonly"/>
					  <input type="hidden" name="projectCd" id="projectCd" />
					  </td>
					  <th align="right" style="width:100px;">期数</th>
					  <td style="width:200px;" class="chkGroup"  validate="required" >
					  <input type="text"  name="entity.periodNum" id="inp_periodNum" style="border:1px solid;height:20px" validate="required" title="保存后不可修改"/>
					  </td>
				  </tr>
					<tr>
					  <th align="right" style="width:70px;">工程</th>
					  <td ><input type="text" name="entity.construction" class="required" id="inp_construction" validate="required" title="保存后不可修改"/></td>
					  <th align="right" style="width:100px;">&nbsp;</th>
					  <td style="width:200px;" class="chkGroup"  validate="required" >&nbsp;</td>
				  </tr>
					<tr>
						<th align="right">备注</th>
						<td colspan="3">
							<textarea rows="3" cols="15" name="entity.remark" id="inp_remark" style="border: 1px solid gray;width:99%;"></textarea>						</td>						
					</tr>											
					<tr>
						<td></td>
						<td colspan="3">
							<input type="button" class="btn_new btn_save_new" onclick="saveBidLedger();" value="保存"/>
							<input type="button" class="btn_new btn_green_new" onclick="resetTempForm();" value="关闭" />						</td>
					</tr>
			  </table>
			</form>
		 </div>
	
   		<div id="seniorPanel" style="display: none;width:100%;border:1px solid #cbcbcb;">
			<s:form id="mainFormSearch" action="strategy-bid-ledger!loadList.action" method="post">
				<input id="bidStatusCd" type="hidden" name="bidStatusCd"  value="${bidStatusCd}"/>
				<input type="hidden" id="searchPageNo" value="1" name="page.pageNo"/>
				
				<%-- 排序字段 --%>
				<input type="hidden" id="sort" name="sort" />
				<input type="hidden" id="order" name="order" value="asc"/>
				
				<%-- 选中的项目公司 --%>
				<input type="hidden" id="selectItemCds" name="selectItemCds"/>
				
				<%-- 选中的快速搜索类型 --%>
				<input type="hidden" id="selectBidCd" name="selectBidCd"/>
				
				<input type="hidden" id="pageNo" name="pageNo"/>
				
				<table class="add_panel">
					<colgroup>
						<col style="width:140px;" />
						<col />
						<col style="width: 80px;" />
						<col />
						<col style="width: 80px;" />
						<col />
					</colgroup>
					<tr>
						<th>战略名称</th>
						<td><input type="text"  name="bidSectionName" id="bidSectionName"  class="edit"/></td>
						<th>网批编号</th>
						<td><input type="text" name="resCd" id="resCd"  class="edit"/></td>
					</tr>
					<%-- 
					<tr>
						<th >保证金截止日期</th>
						<td><s:textfield name="lastGuarDate1" id="lastGuarDate1"  onfocus="WdatePicker()" cssClass="Wdate edit"/></td> 
						<th style="text-align: center;">至</th>
						<td><s:textfield name="lastGuarDate2" id="lastGuarDate2" onfocus="WdatePicker()" cssClass="Wdate edit"/></td> 
					</tr>
					<tr>
						<th >应标截止日期 	</th>
						<td><s:textfield name="lastReceDate1" id="lastReceDate1" onfocus="WdatePicker()" cssClass="Wdate edit"/></td> 
						<th style="text-align: center;">至</th>
						<td><s:textfield name="lastReceDate2" id="lastReceDate2" onfocus="WdatePicker()" cssClass="Wdate edit"/></td>  
					</tr>
					--%>
					<tr>
						<th >创建日期 	</th>
						<td><s:textfield name="createdDate1" id="createdDate1" onfocus="WdatePicker()" cssClass="Wdate edit"/></td> 
						<th style="text-align: center;">至</th>
						<td><s:textfield name="createdDate2" id="createdDate2" onfocus="WdatePicker()" cssClass="Wdate edit"/></td>  
					</tr>
					<tr>
					     <td></td>
						<td colspan="3">
							<security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">
								<input type="button" class="btn_new btn_query_new" onclick='searchBidLedger()'  value="搜索"/>
								<input type="button" class="btn_new btn_clean_new" onclick='resetSeniorPanel()' value="清空条件" style="width: 70px;"/>
								<input type="button" class="btn_new btn_close_new" onclick='hideSeniorQuery()'  value="关闭" />
							</security:authorize>												
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		
		 <div class="searchResultPanel" class="clearBoth" >								
		</div>
</div>
	  
<script type="text/javascript">
	$(function(){
		
		//选择项目
		$('#projectName').orgSelect({showProjectOrg:false});
		
		//选择跟进人
		$('.selectInput').userSelect({
			muti: false
		});
		
		//快速搜索
		$('.quickBidItem').click(
			function(){
				$(this).addClass('quickBidItem-click').siblings().removeClass('quickBidItem-click');
				$(this).addClass('spanred').siblings().removeClass('spanred');
				jumpPage();
			}
		);

		$("#quickBidSenior").toggle(
			function(){
				seniorQuery();
			}
			,
			function(){
				hideSeniorQuery();
			}
		);
		
		$('.quickBidItem').eq(0).click();
	});
	
	function addClickAction(parentDom){
		var chks;
		if (parentDom){
			chks=parentDom.find(".group");
		}else{
			chks=$(".group");
		}
		chks.click(function(){
			if($(this).attr('checked')){
				$(this).siblings().attr('checked',false);
				var curName=$(this).attr("name");
				$(this).parents(".chkGroup").find(".group").each(function(i){
					var tmpName=this.name;
					if (tmpName!=curName){
						$(this).attr('checked',false);
					}
				});
			}
		});
	}
	addClickAction();
	$("#templetForm *").filter("[validate*=required]").addClass("required");
	$(".require").each(function (i){
		$(this).html("<span style='color:red'>*</span>"+$(this).html());
	});



</script>

</body>
</html>