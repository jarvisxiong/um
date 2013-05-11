<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
	<tr>
		
		<td valign="top" style="background-color:#FFFFFF">
	
			<div id="quickClickPanel" style="width:100%;line-height: 30px;">
	   			<!-- 存放,号分割的选中状态列表,eg:1,2,3,4,9 -->						   			
	   			<span class="quickBidItem spanred" typeCd="5" >全部</span>
				<span class="quickBidItem" typeCd="9" >关闭</span>
				<span class="quickBidItem" typeCd="4" >中标</span>
				<span class="quickBidItem" typeCd="3" >评标</span>
				<span class="quickBidItem" typeCd="2" >投标</span>
				<span class="quickBidItem" typeCd="1" >邀标</span>
				<span class="quickBidItem" typeCd="0" >导入</span>
				<div class="quickBidTitle"  >快速搜索:</div>
			</div>
			<div id="content"> 
		 		<div  class="clearBoth" id="newBidLedger"" style="display:none ;border:1px solid #cbcbcb;" >			
					<form action="bid-ledger!saveNewBidLedger.action" method="post" id="templetForm">		
						<table class="add_panel" cellpadding="0" cellspacing="0"> 
							<col width="100"/>
							<col/>
							<col width="150"/>
							<col width="350"/>
							<tr>
								<th>项目</th>
								<td>
									<input validate="required" type="text" name="projectName" value="${templateBean.projectName}" id="projectName" class="required" onkeyup="queryOrg(this);" style="cursor: pointer;" />
									<input type="hidden" id="projectCd"  name="projectCd" value="${templateBean.projectCd}"  />								</td>
								<th>标书编号</th>
								<td>
									<input class="required" validate="required" type="text" name="biaoShuNo" value="${templateBean.biaoShuNo}" id="biaoShuNo" />								</td>
							</tr>
							<tr>
								<th>标段</th>
								<td>
									<input class="required" validate="required" type="text" id="biaoDuan" name="biaoDuan" value="${templateBean.biaoDuan}"  />								</td>						
							    <th>招标计划编号</th>
							    <td><input class="required" validate="required" type="text" id="ccbpNo" name="ccbpNo"  />
								    <input type="hidden" name="ccbpId" id="ccbpId"/>
				 					<input type="hidden" id="dataTypeCd" name="dataTypeCd" value="0" /><!-- 0-招标 -->
	 								<span id="ccbpPlanContentDescSpan"></span>
									<script type="text/javascript">
										$('#ccbpNo').attr('title','请选择【成本系统-招标平台（工程）】中的‘招标’计划编号');
										$('#ccbpNo').quickSearch(
											'${ctx}/plan/cost-ctrl-bid-purc!quickSearchCcbp.action',
											['ccbpProjectName','ccbpDataTypeName','ccbpNo','ccbpPlanContentDesc'],
											{ccbpId:'ccbpId',ccbpNo:'ccbpNo'},
											'',
											function(jdom){
												var t = jdom.attr('ccbpPlanContentDesc');
												$('#ccbpPlanContentDescSpan').attr("title",t);
												$('#ccbpPlanContentDescSpan').html("工程："+t);
												
											},
											{dataTypeCd:'dataTypeCd'}
										);
									</script>
								</td>
							</tr>
							<tr>
								<th>总包</th>
								<td  >
									<s:checkbox name="overAllYes" id="overAllYes" cssClass="group"></s:checkbox>总包
									<s:checkbox name="overAllNo" id="overAllNo" cssClass="group"></s:checkbox>非总包								</td>
								<%-- //TODO:投标台账启用后,打开  --%>
								<th>邀请类别</th>
								<td class="chkGroup"   >
									<s:checkbox name="showFlag" id="showFlag" cssClass="group"></s:checkbox>明标
									<s:checkbox name="hideFlag" id="hideFlag" cssClass="group"></s:checkbox>暗标								</td>
							</tr>
							<tr>
								<th>预计金额(元)</th>
								<td>
									<input  validate="required"  type="text" id="planMoney" name="planMoney" value="${planMoney}"  onkeyup="clearNoNum_1(this);" onblur="formatVal($(this));" /><%--onblur="formatVal($(this));" --%>								</td>
								<th>预算</th>
								<td class="chkGroup">
									<s:checkbox name="outFlag" id="outFlag" cssClass="group"></s:checkbox>预算外
									<s:checkbox name="inFlag" id="inFlag" cssClass="group"></s:checkbox>预算内								</td>
							</tr>
							<tr>
								<td></td>
								<td colspan="3">
									<input type="button" class="btn_new btn_save_new"  onclick="saveBidLedger();" value="保存" />
									<input type="button" class="btn_new btn_close_new" onclick="resetTempForm();" value="关闭" style="margin-left:-1px" />								</td>
							</tr>
						</table>
					</form>
				</div>
				
				
				<%-- 右边内容  --%>
				<s:form id="mainFormSearch" action="bid-ledger!loadList.action" method="post">
					<input id="bidStatusCd" type="hidden" name="bidStatusCd"  value="${bidStatusCd}"/>
					<input type="hidden" id="searchPageNo" value="1" name="page.pageNo"/>
					
					<%-- 排序字段 --%>
					<input type="hidden" id="sort" name="sort" />
					<input type="hidden" id="order" name="order" value="asc"/>
					
					
					
					<%-- 选中的快速搜索类型 --%>
					<input type="hidden" id="selectBidCd" name="selectBidCd"/>
					
					<%-- 快速搜索 --%>
					<div id="seniorPanel" class="seniorPanel clearBoth" style="background-color: white;width: 100%;padding:10px 0;">
						<table cellpadding="0" cellspacing="0"> 
							<col width="100px"/>
							<col width="200px"/>
							<col width="80px"/>
							<col width="150px"/>
							<col width="80px"/>
							<col />
							<tr>
							    <th>项目名称:</th>
								<td>
								    
									<select name="bidProject" style="width:150px;height:20px;border: 1px solid;">
									    <option value=""></option>
									  <s:iterator value="orgList" status="sts" var="org">
									    <option value="${org.orgCd}">${org.orgName}</option>				   
									  </s:iterator>									  
									</select>
									<%-- 选中的项目公司 --%>
									<input type="hidden" id="projSelectCds" name="projSelectCds"/>
								</td>
								<th>标段名称:</th>
								<td>
								    <input style="width:140px;height:20px;border: 1px solid;" type="text"  name="bidSectionName" id="bidSectionName" />
								</td>
								<th>跟进人:</th>
								<td>
								    <input style="width:140px;height:20px;border: 1px solid;" type="text"  name="followNames" id="followNames" class="selectInput" readonly="readonly"/>
								    <input type="hidden"  name="followCds" id="followCds"/>
								</td>
							</tr>
							<%-- 
							<tr>
								<th>实际开标日期:</th>
								<td>
									<s:textfield cssStyle="width:120px;" name="bidOpenRealDate1" onfocus="WdatePicker()" cssClass="Wdate"/>
								</td> 
								<th>至</th>
								<td>
									<s:textfield cssStyle="width:120px;" name="bidOpenRealDate2" onfocus="WdatePicker()" cssClass="Wdate"/>
								</td> 
							</tr>
							<tr>
								<th>实际定标日期:</th>
								<td>
									<s:textfield cssStyle="width:120px;" name="bidConfirmRealDate1" onfocus="WdatePicker()" cssClass="Wdate"/>
								</td> 
								<th>至</th>
								<td>
									<s:textfield cssStyle="width:120px;" name="bidConfirmRealDate2" onfocus="WdatePicker()" cssClass="Wdate"/>
								</td>  
							</tr>
							--%>
							<tr>
								<td></td>
								<td colspan="5" align="left">
									<security:authorize ifAnyGranted="A_BID_QUERY,A_ADMIN_BID">
										<input type="button" class="btn_new btn_query_new" onclick='searchBidLedger()'  value="搜索 "  style="width:80px;"/>
										<input type="button" class="btn_new btn_clean_new" onclick='resetSeniorPanel()' value="清空条件"  style="width: 80px;"/>
										<input type="button" class="btn_new btn_close_new" onclick='resetSeniorPanel();$("#quickBidSenior").click()' value="关闭"  style="width:80px;"/>
									</security:authorize>												
								</td>
							</tr>
							
						</table>
						</div>
					 <div class="searchResultPanel" style="clear: left;" >
						
					</div>
				</s:form>
			</div>
		</td>
	</tr>
</table>

<script type="text/javascript">
	$('#bidProject').orgSelect({
		showProjectOrg:true,
		orgMuti: true
	});
	//$('#followNames').userSelect({
	//	muti:false
	//}); 
</script>
