<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<table style="width:100%" >
	<tr style=" height:36px">
		<td class="rowItem" colspan="6">
			<div class="headTitle">投标单位信息(<s:property value="listBidSupVo.size"/>)</div>
				<security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">
					<s:if test="bidLedger.bidStatusCd<1">	       		   	  	
						<input id="btnAddSup" class="fRight btn_new btn_blue_new" type="button" value="新增"  >
					</s:if>
				</security:authorize>	       		   	  	
			<div class="resultTip" id="supSuccessInfo"></div>			
		</td>
   	</tr>
   	<tr id="trAddSup" style="display:none;background-color: #f7f7f7;">
   		<td>
   			<div>
   				<%--新增投标单位表单 --%>
   				<form id="newSupForm" action="${ctx}/bid/bid-sup!saveNewSup.action" method="post">
   					<%-- 很重要,标段ID --%>
	        	    <input type="hidden" name="bidLedgerId" value="${bidLedger.bidLedgerId}"/>
	        	    <div style="float: left;">
	        	    	<table class="editProTable" cellpadding="0" cellspacing="0" border="0">
	        	    		<col width="100px"/>
							<col/>
							<tr>
	        	            	<th>供应商:</th> 
	        	            	<td>	        	            		
	        	            		<input type="hidden" id="sid" name="supBasicId" value="${supBasicId}"></input>
	        	            		<input class="inputBorder" validate="required" type="text" name="bidUnit" id="bidUnit" value="${templateBean.bidUnit}" style="width:500px;"/>
	        	            	</td>
				         	</tr>
							<tr>
	        	            	<th>信用级别:</th> 
	        	            	<td>
	        	            		<input class="inputBorder" name="grade" id="grade" readonly="readonly" style="margin-top: 5px;"></input>     	            		
	        	            	</td>
				         	</tr>
							<tr>
	        	            	<th>注册资金:</th> 
	        	            	<td>
	        	            		<input class="inputBorder" name="comRegMoney" id="comRegMoney" readonly="readonly" style="margin-top: 5px;"></input>     	            		
	        	            	</td>
				         	</tr>
				         	<security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">
	         	            <tr>
	         	            	<td></td>
	         	            	<td>
	         	            		<div class="btnBlue" style="width:50px" onclick="saveNewBidSup('${bidLedger.bidLedgerId}')">保存</div>
	         	            		<div class="btnBlue" style="width:50px" onclick="$('#btnAddSup').trigger('click');">取消</div>
	         	            	</td>
	         	            </tr>
				         	</security:authorize> 
	        	    	</table>
	        	    </div>
   				</form>
   			</div>
   		</td>
   	</tr> 
   	<tbody>
	<tr>
		<td>
			<table class="commonTable" cellpadding="0" cellspacing="0" border="0">
			 	<col width="40"/>
			 	<col />
				<col />
				<col width="80"/>				
				<col width="120"/>
				<s:if test="bidLedger.needGuarFlg==1">
					<col width="120"/>
					<col width="50"/>
				</s:if>						
				<col width="60"/>
				<s:if test="bidLedger.bidStatusCd<1">
				<col width="60"/>
				</s:if>
			<thead>
			 	<tr>
			 		<th class="first">序号</th>
			 		<th title="投标单位名称"><div class="partHide  ellipsisDiv_full">投标单位</div></th>
			 		<th title="联系人" nowrap="nowrap">联系人</th>
			 		<th title="供方级别">级别</th>
			 		<th title="应标状态">应标情况</th>			 		
			 		<s:if test="bidLedger.needGuarFlg==1">
			 			<th title="上传保证金(附件)">保证金</th>				 		
			 			<th title="退款">退款</th>	
			 		</s:if>
			 		<th title="官网帐号">官网</th>
			 		<s:if test="bidLedger.bidStatusCd<1">
			 			<th title="操作">操作</th>
			 		</s:if>
			 	</tr>
				</thead>
				<tbody>				
				<s:iterator value="listBidSupVo" var="vo" status="status" >
					<!-- 显示概要 -->
					<tr class="supBrief" id="supBrief_${bidSupId}">
						<%--序号 --%>
		  			  	<td style="text-align: center;" <s:if test="#status.index==listBidSupVo.size()-1"> class='lastRowTd' </s:if>>
		  			  		<div ><%--class="arrowDirectDrop" --%>
		  			  			<s:if test="typeCd==2"><s:property value="#status.index+1"/></s:if>
		  			  		<s:else>
		  			  			<s:property value="#status.index+1"/>
		  			  		</s:else>		  			  			
		  			  		</div>
		  			  	</td>
		  			  	
		  			  	<%--如果是暗标则不显示供应商名称 --%>
						<td  <s:if test="#status.index==listBidSupVo.size()-1"> class='lastRowTd' </s:if> title='${bidSupName}'>
							<div class="partHide ellipsisDiv_full" 
								<s:if test="typeCd==2">
								style='color:red;'
								</s:if>
								>
								<%--如果是暗标则不显示供应商名称 --%>
									<s:if test="bidVisibleFlg==1">
										${bidSupName}
									</s:if>
									<s:else>
									<%--如果是标底单位 --%>
										<s:if test="typeCd==2">
											${bidSupName}
										</s:if>
										<s:else>
											${bidSupName}
										</s:else>
									</s:else>						
								
							</div>
						</td>
						
						<%--联系人 --%>
						<td  <s:if test="#status.index==listBidSupVo.size()-1"> class='lastRowTd' </s:if>><div class="partHide ellipsisDiv_full">
							<s:if test="bidVisibleFlg == 1">								
								<%--如果在邀标阶段前 --%>
								
									<%--供应商 --%>
									<s:if test="typeCd==1">
										<div id="linkman_${bidSupId}" <s:if test="bidLedger.bidStatusCd<1"><security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">onclick="changeCont(this,'${refBidSupId}','${bidSupId}');"</security:authorize></s:if>>								
											<s:if test="linkMan==null">
												<font style="color: red;">选择联系人</font>
											</s:if>
											<s:else>
												${linkMan}
											</s:else>
										</div>
									</s:if>
									<s:else>
										<div>&nbsp;</div>
									</s:else>
								
							</s:if>	
							<s:else>
								<%--如果在邀标阶段前 --%>
									
									<%--供应商 --%>
									<s:if test="typeCd==1">									
											<div id="linkman_${bidSupId}" <s:if test="bidLedger.bidStatusCd<1">onclick="changeCont(this,'${refBidSupId}','${bidSupId}');"</s:if>>								
												<s:if test="linkMan==null">
													<font style="color: red;">选择联系人</font>
												</s:if>
												<s:else>
													<div style="color: red;">${linkMan}</div>
												</s:else>
											</div>
									</s:if>
									<s:else>
										<div>&nbsp;</div>
									</s:else>
									
								
							</s:else>
							</div>
						</td>
						
						<%--级别 --%>
						<td  <s:if test="#status.index==listBidSupVo.size()-1"> class='lastRowTd' </s:if>>
							<span>
								<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapSupBasicEvaluate()" value="supLevelCd"/>
							</span>
						</td>	
						
						
						
						<%--状态--%>
						<td nowrap="nowrap" 
							class="hidden_sub_panel <s:if test="#status.index==listBidSupVo.size()-1"> lastRowTd </s:if>">
						
								<%-- 是否接受(接受/拒绝）--%>
								<div style="float:left;" >
									
									<s:if test="receiveStatusCd == 1">
										<s:if test="supBidStatusCd == 1">
											<span style="color:red;">已中标</span>
										</s:if>
										<s:else>
											已应标 <s:date name="receiveDate" format="yyyy-MM-dd"/>
										</s:else>
									</s:if>
									<s:if test="receiveStatusCd == null">
										<font color="gray">未应标</font>
									</s:if>
									<s:if test="receiveStatusCd == 0">
										<font color="gray">已拒绝</font>
									</s:if>	
									
									<s:if test="guranteeStatusCd == 1">
									<br/>
										
											已上传保证金			
										
									</s:if>	
									
									 <s:if test="supBidStatusCd ==2">
									  <br/>
							             <font color="green"><strong>已推荐中标</strong></font>
							        </s:if>								
								</div>
						</td>
						
						
						<s:if test="bidLedger.needGuarFlg==1">
						<%--保证金 --%>
						<td class="hidden_sub_panel <s:if test="#status.index==listBidSupVo.size()-1"> lastRowTd </s:if>">
							<%--附件由官网上传--%>
							<div 
								<security:authorize ifAnyGranted="A_BID_STRA_ADMIN">
									<s:if test="receiveStatusCd == 1">
										onclick="openAttachmentGurantee($(this),'${bidSupId}','bidSupGurantee','true');"
									</s:if>
									<s:elseif test="typeCd==2">
										onclick="openAttachmentGurantee($(this),'${bidSupId}','bidSupGurantee','true');"
									</s:elseif>
									<s:else>
										onclick="openAttachmentGurantee($(this),'${bidSupId}','bidSupGurantee','disable');"
									</s:else>
								</security:authorize>
								<security:authorize ifNotGranted="A_BID_STRA_ADMIN">
									onclick="openAttachmentGurantee($(this),'${bidSupId}','bidSupGurantee','disable');"
								</security:authorize>							
								<s:if test="guranteeStatusCd == 1">
									class="hasAttachFile"
									title="已上传附件"
								</s:if>
								<s:else>
									class="noAttachFile"
									title="查看附件"
								</s:else>
							>
							&nbsp;&nbsp;
							</div>
							<s:if test="guranteeStatusCd == 1&&bidLedger.bidStatusCd==1">	
							 <security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">
								<s:if test="guarAttaConfFlg!=1">								
									<div onclick="checkGurantee('${bidSupId}','1');" 
										style="margin-left:2px;height: 25px;width:50px"
										class="btnBlue">确认</div>
								</s:if>
								<s:if test="guarAttaConfFlg==1">								
									<div onclick="checkGurantee('${bidSupId}','0');" 
										style="margin-left:2px;height: 25px;width:50px "
										class="btnBlue">取消</div>
								</s:if>
								</security:authorize>
							</s:if>
							<%--如果非邀标以上状态且应标，但是未确认保证金，也可确认保证金 --%>
							<s:elseif test="guranteeStatusCd == 1&&bidLedger.bidStatusCd>1">
								<security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">
									<s:if test="guarAttaConfFlg!=1">
										<security:authorize ifAnyGranted="A_BID_STRA_ADMIN">
											<div onclick="checkGurantee('${bidSupId}','1');" 
											style="margin-left:2px;height: 20px;width: 60px;"
											class="plgreen">确认</div>
										</security:authorize>
									</s:if>
								</security:authorize>
							</s:elseif>
							
							<s:if test="receiveStatusCd == 1&& bidLedger.needGuarFlg==1&& guranteeStatusCd==1">
								<s:if test="guarAttaConfFlg==1">
									<font color="green">已确认<br/><s:date name="guarAttaConfDate" format="yyyy-MM-dd"/></font>
								</s:if>
								<s:else>									
									<font color="red">未确认</font>									
								</s:else>
							</s:if>			
						</td>
						
						<%--退款 --%>
						<td>
							<s:if test="guranteeStatusCd == 1">
								<%--如果已经定标,且没有中标就可以退款 --%>
								<security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">
								<s:if test="bidLedger.bidStatusCd==4&&(supBidStatusCd==0||supBidStatusCd==null)">
									<a href="javascript:void(0)"  
									   class="sup"
										<s:if test="refundStatusCd != 1">
											style="display: block;"
										</s:if>
										<s:else>
											style="display: none;"
										</s:else>
										onclick="supRefund(this,'${bidSupId}','${bidLedgerId}')" title="点击退款">																	
										<font style="color: green;text-decoration: none;">退款</font>
									</a>
								</s:if>	
								</security:authorize>
								<div
									<s:if test="refundStatusCd == 1">
										style="display: block;"
									</s:if>
									<s:else>
										style="display: none;"
									</s:else>
									title="已退回保证金"
								>已退
								</div>
							</s:if>
						</td>
						</s:if>
						
						 
						<%--官网 帐号--%> 
						<td  <s:if test="#status.index==listBidSupVo.size()-1"> class='lastRowTd' </s:if> title='官网账号:&nbsp;<s:if test="supUserCd == null || supUserCd == ''"></s:if><s:else>${supUserCd }</s:else>'>
							<s:if test="supUserCd == null || supUserCd == ''">
								<a href="javascript:void(0)" onclick="openPlacct('${bidSupId}','${bidLedgerId}')"></a>
							</s:if>
							<s:else>
								<div id="openUserCd">${supUserCd }</div>
							</s:else>
						</td>
						
						<%--如果为导入阶段,则可进行删除操作 --%>
						<security:authorize ifAnyGranted="A_BID_STRA_USER,A_BID_STRA_ADMIN">
						<s:if test="bidLedger.bidStatusCd<1&&typeCd==1">
							<td>
								<div class="btn_new btn_del_new"  onclick="doDeleteSup('${bidSupId}','${bidLedger.bidLedgerId}');">删除</div>
							</td>
						</s:if>
						</security:authorize>
					</tr>
					<%-- 显示编辑表单部分 --%>
					<tr class="supDetail" id="supDetail_${bidSupId}" style="display:none;background-color: #f7f7f7;" >
						<td></td>
						<td colspan="2">
							<table class="editSupTable" >
								<tbody style="border: 0px solid;">
									<tr>	
									<td>
										<s:if test="bidLedger.visableFlg == 1">
										<form action="${ctx}/bid/bid-sup!save.action" method="post" id="form_${bidSupId}">
											<input type="hidden" name="id" value="${bidSupId}" />
											<table class="bidTable" border="0" cellpadding="0" cellspacing="0">
												<col width="120px"/>
												<col width="200px"/>
												<col />
												<tr>
													<th><span>联系人:</span></th>
													<td><input name="entity.linkMan" value="${linkMan}" width="80" id="input_linkman_${bidSupId}"  readonly="readonly" disabled="disabled"></input></td>
													
												</tr>
												<tr>
													<th><span>联系方式:</span></th>
													<td><input name="entity.linkDesc" value="${linkDesc}" width="80"  readonly="readonly" disabled="disabled"></input></td>
												</tr>
												<tr>
													<th><span>电子邮件:</span></th>
													<td><input name="entity.email" value="${email}" width="80" readonly="readonly" disabled="disabled"></input></td>
													
												</tr>
											</table>
										</form>
										</s:if>
										<s:elseif test="bidLedger.visableFlg == 2">
											<div class="resultTip" style="display:block;">${displayNo}(此邀标为暗标)</div>
										</s:elseif>
										<s:else>
											<div class="resultTip" style="display:block;">邀标类型未知!</div>
										</s:else>
									</td>
								</tr>
								</tbody>
							</table>
					</td>
					
					<%--商务标数据及技术标数据 --%>					
					<td colspan="<s:if test="bidLedger.needGuarFlg==1">4</s:if><s:else>2</s:else>">
					</td>
					<td class="hidden_sub_panel" style="vertical-align: top;">
						 <%--应标 --%>
						 <s:if test="receiveStatusCd == 1">
							
							<s:if test="typeCd==1">
								<s:if test="bidLedger.bidStatusCd == 3&&supBidStatusCd == 2">
										<input class="btn_new btn_blue_new" type="button" value="取消推荐" style="width:80px;" onclick="cancelBidSucc('${bidSupId}','${bidLedger.bidLedgerId }')">
								</s:if>
								<s:if test="bidLedger.bidStatusCd == 3&&supBidStatusCd != 2">
									<%--需要保证金 --%>
									<s:if test="bidLedger.needGuarFlg==1">
										<%--上传保证金 --%>
										<s:if test="guranteeStatusCd == 1">
											<%--已确认 --%>
											<s:if test="guarAttaConfFlg==1">
												<input class="btn_new btn_blue_new" type="button" value="推荐中标" style="width:80px;" onclick="setBidSucc('${bidSupId}','${bidLedger.bidLedgerId }')">
											</s:if>
										</s:if>
									</s:if>									
									<s:else>
										<input class="btn_new btn_blue_new" type="button" value="推荐中标" style="width:80px;" onclick="setBidSucc('${bidSupId}','${bidLedger.bidLedgerId }')">
									</s:else>
										
								</s:if>
							</s:if>
						</s:if>
					</td>
					<td colspan="2"></td>
				</tr> 
				</s:iterator>
				</tbody>
			</table>
		</td>
	</tr>
	</tbody>
</table>
 <input type="hidden" name="supCnt" id="hidden_supCnts" value="<s:property value="listBidSupVo.size"/>"></input>  	

<script type="text/javascript">

//点击"新增"行时事件，显示/隐藏新增表单
$('#btnAddSup').toggle(
	function(){
		$("#trAddSup").show();
	},function(){
		$("#trAddSup").hide();
	}
);
$(function(){
	//选择供应商
	$("#bidUnit").val('');
		$("#bidUnit").quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],{sname:'bidUnit',sid:'sid',grade:'grade',comRegMoney:'comRegMoney'},{supStatus:'0'});
//展开明细		
 $("#supListPanel tr[class*='supBrief']").each(function(){
	$(this).attr("title","点击展开明细");
 	$(this).toggle(
		function(){
			$("#supListPanel tr[open=yes]").each(function(){
				$(this).trigger('click').attr("open","");
			});
			$(this).attr("title","点击收起明细");
			$(this).addClass('trChoosed');
			$(this).next().show();
			$(this).find('.arrowDirectDrop').removeClass().addClass('arrowDirect');
			$(this).attr("open","yes");
		},
		function(){
			$(this).attr("title","点击展开明细");
			$(this).removeClass('trChoosed');
			$(this).next().hide();
			$(this).find('.arrowDirect').removeClass().addClass('arrowDirectDrop');
			$(this).attr("open","");
		}
	);
 });
});	

//编辑联系人信息
 function editSup(id){
 	TB_showMaskLayer("正在保存...");
 	var linkman=$("#input_linkman_"+id).val();
	$('#form_'+id).ajaxSubmit(function(r){
		//更新联系人信息
		$("#linkman_"+id).html(linkman);
		$("#tlinkman_"+id).html(linkman);						 
		TB_removeMaskLayer();
		showSuccessInfo('supSuccessInfo','保存成功！');
	});
}
//切换联系人【暂时不用】
function changeContact(dom,refSupId,bidSupId){	
	var jdom=$(dom);
	var position = jdom.position();	
	var top=position.top-500;
	var url=_ctx+"/bid/bid-sup!loadSupContactor.action";
	$.post(url, {supBasicId : refSupId,bidSupId:bidSupId}, function(result) {
		TB_removeMaskLayer();
		$("#contactWin").attr('top',top+'px');
		$("#contactWin").attr('left',position.left+100+'px');
		$("#contactWin").html(result).show();
	});	
}
//保存投标单位
function saveNewBidSup(bidLedgerId){
	var sid=$("#sid").val();
	if(sid){
			$("#newSupForm").ajaxSubmit(function(result){
				var rs=result.split(',');
				if('success'==rs[0]){
					loadStrategyBidSup(bidLedgerId);
					}
				else{
					alert(rs[1]);
					}
			});
		}else{
				alert("请选择供应商！");
			}
	
}
//删除投标单位
function doDeleteSup(bidSupId,bidLedgerId){
	if(window.confirm('确认删除此投标单位?')){
		var url = _ctx+"/bid/bid-sup!delete.action";
		$.post(url,{bidSupId:bidSupId,bidLedgerId:bidLedgerId}, function(result) {
			var rs=result.split(',');
			if('success'==rs[0]){
				alert('删除成功');
				loadStrategyBidSup(rs[1]);
				}
		});
	}
	}
//切换联系人【暂时使用】
function changeCont(dom,refSupId,bidSupId){
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='bizPurchaseDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 540,
		height : 330,
		title : "选择标段投标联系人",
		closeBtn:true,
		afterShow : function() {
			var url = _ctx+"/bid/bid-sup!loadSupContactor.action";
			$.post(url,{supBasicId : refSupId,bidSupId:bidSupId}, function(result) {
				$("#bizPurchaseDiv").html(result);

			});
		},
		handler : function(btn){
			if(btn=='ok'){
				var url=_ctx+"/bid/bid-sup!chooseContactor.action";
				var supContactorId=$("div[condiv='condiv'][class*='choosed']").attr('contactor');
				if(typeof(supContactorId)=='undefine'||supContactorId==''){
						alert('请选择一位联系人！');
						return false;
					}
				$.post(url, {bidSupId : bidSupId,supContactorId:supContactorId}, function(result) {
					var rs=result.split(",");
					if(rs[0]=='success'){
							cancelChoose();
							loadStrategyBidSup(rs[1]);
						}
					
				});	
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["取消",'cancel']]
	});
}
 </script>

