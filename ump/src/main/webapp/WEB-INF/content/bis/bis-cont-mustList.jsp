<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>

<input type="hidden" id="seleChargeType" name="seleChargeType" value="${seleChargeType}"/>
<s:set var="contStatus">${statusCd}</s:set>
<s:set var="conItemCount"><s:property value="mustList.size()"/></s:set>
<security:authorize ifAnyGranted="A_MUST_INSERT">
	<s:set var="mustOperator">true</s:set>
</security:authorize>
<security:authorize ifAnyGranted="A_MUST_DELETE">
	<s:set var="mustDelete">true</s:set>
</security:authorize>

<table class="content_table" id="tbConItem" >
	<col width="30"/>
	<col width="40"/>
	<col width="100"/>
	<col width="50"/>
	<col width="40"/>
	<col width="80"/>
	<col width="100"/>
	<col />
	<col width="80"/>
	<col width="50"/>
	<col width="40"/>
	<col width="40"/>
	<thead>
		<tr class="header">
			<th style="background:none; text-align:center;"><input class="chk_all" type="checkbox" onclick="checkedAll($(this).attr('checked'));" style="cursor:pointer;margin-bottom:5px;" title="全选/不选" /></th>
			<th align="left">序号</th>
			<th align="left">类别</th>
			<th align="left">年份</th>
			<th align="left">月份</th>
			<th align="left">金额</th>
			<th align="left">应收日期</th>
			<th align="left">说明</th>
			<th align="left">营业额</th>
			<th align="left">状态</th>
			<th align="left">附件</th>
			<th align="left">操作</th>
		</tr>
	</thead>
	<tbody>
		<tr id="trConItem" class="mainTr" style="display: none;margin-bottom:5px;">
			<input type="hidden" id="entityTmpId_0" name="bisMusts[0].entityTmpId" />
			<input type="hidden" id="attachFlg_0" name="bisMusts[0].attachFlg" />
			<td align="center"><input id="chk_0" type="checkbox" value="tempId" /></td>
			<td click2expand="true" style="padding-right: 8px;">
				<div class="bis-read" ></div>
				<div class="bis-edit" >
				<!--<input style="width: 95%" type="text" name="bisMusts[0].sequenceNo" />-->
				</div>
			</td>
			<td click2expand="true">
				<input type="hidden" name="bisMusts[0].chargeTypeCd" value="${seleChargeType}" />
				<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapChargeType()" value="seleChargeType"/>
			</td>
			<td click2expand="true" style="padding-right: 4px;">
				<div class="bis-read" style="display:none;" ></div>
				<div class="bis-edit" >
				<input type="text" validate="required" name="bisMusts[0].mustYear" id="mustYear_0" onblur="checkMustDate(this,'year');"/>
				</div>
			</td>
			<td click2expand="true" style="padding-right: 4px;">
				<div class="bis-read" style="display:none;" ></div>
				<div class="bis-edit" >
				<input type="text" validate="required" name="bisMusts[0].mustMonth" id="mustMonth_0" onblur="checkMustDate(this,'month');"/>
				</div>
			</td>
			<td click2expand="true">
				<div class="bis-read" style="display:none;" ></div>
				<div class="bis-edit" >
				<input type="text" name="bisMusts[0].money" value="待计算" style="color: #CCCCCC;" onclick="clearSearchInput(this);" onblur="formatVal($(this)); resetSearchInput(this);" />
				</div>
			</td>
			<td click2expand="true">
				<div class="bis-read" style="display:none;" ></div>
				<div class="bis-edit" >
				<input type="text" validate="required" class="Wdate" name="bisMusts[0].collDate" onfocus="WdatePicker()"/>
				</div>
			</td>
			<td click2expand="true">
				<div class="bis-read pd-chill-tip" style="display:none;" ></div>
				<div class="bis-edit" >
				<input type="text" name="bisMusts[0].describe" />
				</div>
			</td>
			<td click2expand="true">
				<div class="bis-read" style="display:none;" ></div>
				<div class="bis-edit" >
				<input type="text" name="bisMusts[0].turnover" onblur="formatVal($(this));"/>
				</div>
			</td>
			<td></td>
			<td>
				<a style="cursor:pointer" class="attachId" >
				<img id="bisMustAttachId_0" src="${ctx}/resources/images/common/atta.gif" />
				</a>
			</td>
			<td align="center"><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a></td>
		</tr>
		<s:iterator value="mustList" var="item" status="s">
		<tr class="mainTr" id="main_${bisMustId}" value="${bisMustId}">	
			<td align="center">
				<input type="checkbox" <s:if test="statusCd==2">class="approved"</s:if> id="chk_<s:property value="#s.index" />" value="${bisMustId}" money="${money}" status="${statusCd}" date="${mustYear}-${mustMonth}"/>
				<input type="hidden" name="bisMusts[<s:property value="#s.index" />].bisMustId" value="${bisMustId}" />
				<input type="hidden" name="bisMusts[<s:property value="#s.index" />].recordVersion" value="${recordVersion}" />
				<input type="hidden" name="bisMusts[<s:property value="#s.index" />].bisProjectId" value="${bisProjectId}" />
				<input type="hidden" name="bisMusts[<s:property value="#s.index" />].statusCd" value="${statusCd}" />
				<input type="hidden" name="bisMusts[<s:property value="#s.index" />].chargeTypeCd" value="${chargeTypeCd}" />
				<input type="hidden" name="bisMusts[<s:property value="#s.index" />].typeCd" value="${typeCd}" />
				<input type="hidden" name="bisMusts[<s:property value="#s.index" />].floorType" value="${floorType}" />
				<input type="hidden" name="bisMusts[<s:property value="#s.index" />].totalFactMoney" value="${totalFactMoney}" />
				<input type="hidden" id="attachFlg_<s:property value="#s.index" />" name="bisMusts[<s:property value="#s.index" />].attachFlg" value="${attachFlg}" />
			</td>
			<td click2expand="true" style="padding-right: 8px;">
				<s:property value="#s.index+1" />
				<!--<div class="bis-read" >${sequenceNo}</div>
				<s:if test="statusCd!=2 || money==null">
				<div class="bis-edit" style="display:none;" >
				<input style="width: 98%" type="text" name="bisMusts[<s:property value="#s.index" />].sequenceNo" value="${sequenceNo}" />
				</div>
				</s:if>
				-->
			</td>
			<td click2expand="true">
				<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapChargeType()" value="seleChargeType"/>
			</td>
			<td click2expand="true" style="padding-right: 4px;">
				<div class="bis-read" >${mustYear}</div>
				<s:if test="statusCd!=2 || money==null">
				<div class="bis-edit" style="display:none;" >
				<input type="text" validate="required" name="bisMusts[<s:property value="#s.index" />].mustYear" value="${mustYear}" id="mustYear_<s:property value="#s.index" />" onblur="checkMustDate(this,'year');"/>
				</div>
				</s:if>
			</td>
			<td click2expand="true" style="padding-right: 4px;">
				<div class="bis-read" >${mustMonth}</div>
				<s:if test="statusCd!=2 || money==null">
				<div class="bis-edit" style="display:none;" >
				<input type="text" validate="required" name="bisMusts[<s:property value="#s.index" />].mustMonth" value="${mustMonth}" id="mustMonth_<s:property value="#s.index" />" onblur="checkMustDate(this,'month');"/>
				</div>
				</s:if>
			</td>
			<td click2expand="true">
				<div class="bis-read" ><s:if test="money==null">待计算</s:if><s:else>${money}</s:else></div>
				<s:if test="statusCd!=2 || money==null">
				<div class="bis-edit" style="display:none;" >
				<input type="text" name="bisMusts[<s:property value="#s.index" />].money" <s:if test="money!=null">value="${money}" style="color: #5A5A5A;"</s:if><s:else>value="待计算" style="color: #CCCCCC;"</s:else> onclick="clearSearchInput(this);" onblur="formatVal($(this)); resetSearchInput(this);" />
				</div>
				</s:if>
			</td>
			<td click2expand="true">
				<div class="bis-read" ><s:date name="collDate" format="yyyy-MM-dd"/></div>
				<s:if test="statusCd!=2 || money==null">
				<div class="bis-edit" style="display:none;" >
				<input type="text" validate="required" class="Wdate" name="bisMusts[<s:property value="#s.index" />].collDate" value='<s:date name="collDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker()" />
				</div>
				</s:if>
			</td>
			<td click2expand="true">
				<div class="bis-read pd-chill-tip" title="${describe}">
					<div class="ellipsisDiv_full" >
					${describe}
					</div>
				</div>
				<s:if test="statusCd!=2 || money==null">
				<div class="bis-edit" style="display:none;" >
				<input type="text" name="bisMusts[<s:property value="#s.index" />].describe" value="${describe}" />
				</div>
				</s:if>
			</td>
			<td click2expand="true">
				<div class="bis-read" >${turnover}</div>
				<s:if test="statusCd!=2 || money==null">
				<div class="bis-edit" style="display:none;" >
				<input type="text" name="bisMusts[<s:property value="#s.index" />].turnover" onblur="formatVal($(this));"/>
				</div>
				</s:if>
			</td>
			<td click2expand="true" <s:if test="statusCd==3">style="color: red"</s:if><s:elseif test="statusCd==2">style="color: blue;"</s:elseif> ><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisMustStatus()" value="statusCd"/></td>
			<td>
				<s:if test="#mustOperator && (statusCd!=2 || money==null) && #contStatus!=6">
					<a class="attachId" href="javascript:openAttachment('附件管理','${bisMustId}','bisMustAttachId_<s:property value="#s.index" />','attachFlg_<s:property value="#s.index" />');" >
						<img id='bisMustAttachId_<s:property value="#s.index" />' <s:if test='attachFlg==1'>src="${ctx}/resources/images/common/atta_y.gif"</s:if><s:else>src="${ctx}/resources/images/common/atta.gif"</s:else> />
					</a>
				</s:if>
				<s:else>
					<s:if test='attachFlg==1'>
					<a class="attachId" href="javascript:showAttachment('${bisMustId}');" >
						<img id='bisMustAttachId_<s:property value="#s.index" />' src="${ctx}/resources/images/common/atta_y.gif" />
					</a>
					</s:if>
				</s:else>
			</td>
			<td align="center">
				<s:if test="#contStatus!=6">
				<s:if test="#mustOperator && statusCd==1">
				<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
				</s:if>
				<s:if test="#mustDelete && statusCd==3">
				<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
				</s:if>
				</s:if>
			</td>
		</tr>
		</s:iterator>
	</tbody>
</table>

<%-- 保证金等年月去掉必填
<s:if test="seleChargeType==12 || seleChargeType==13 || seleChargeType==14 || seleChargeType==25">
<script type="text/javascript">
	$("#tbConItem :input[id^=mustYear_]").removeAttr("validate");
	$("#tbConItem :input[id^=mustMonth_]").removeAttr("validate");
</script>
</s:if>
--%>

<script type="text/javascript">
	var trApprover=$("#trConItem").clone();
	$("#trConItem").remove();
	var cloneCount = 0;
	var index=${conItemCount};
	
	function addItem(){
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();
		$(trApprover_new).find(":input").each(function(i){
			this.name=this.name.replace('0',index);
			this.id=this.id.replace('0',index);
		});
		$(trApprover_new).find(":img").each(function(i){
			this.id=this.id.replace('0',index);
		});
		bindClickCreditAtta(trApprover_new, index);
		$("#tbConItem").append(trApprover_new);
		index++;
	}
	function delItem(dom){
		ymPrompt.confirmInfo({message:'确认删除？',title:'删除',width:200,height:150,handler:function (tp){
			if (tp=='ok'){
				$(dom).parent().parent().remove();
				var id = $(dom).parent().parent().attr("value");
				if(!isEmpty(id)) {
					$.post(_ctx+"/bis/bis-cont!deleteMust.action", {chkIds:id}, function(result) {
						reloadChargeType();
						//loadBisMustList($("#seleChargeType").val(), $("#statusCd").val());
					});
				}
			}
		}});
	}
	//自动生成
	function autoAddMust() {
		ymPrompt.confirmInfo( {
			icoCls : "",
			autoClose:false,
			message : "<div id='autoAddMustDiv'><img align='absMiddle' src='"
				+ _ctx + "/images/loading.gif'></div>",
			width  : 290,
			height : 260,
			title : "自动生成应收款",
			closeBtn:true,
			winPos:[($("#resultDiv").width()-290)/2,$("#resultDiv").height()-300],
			afterShow : function() {
				var url = "${ctx}/bis/bis-cont!toAutoAdd.action";
				$.post(url, {contStartDate:$("#contStartDate").val(),contEndDate:$("#contEndDate").val()}, function(result) {
					$("#autoAddMustDiv").html(result);
				});
			},
			handler : function(btn){
				if(btn=='ok'){
					var startDate = $('#startDate').val();
					var endDate = $('#endDate').val();
					if(isEmpty(startDate)){
						alert('请输入开始时间!');
						return false;
					}
					if(isEmpty(endDate)){
						alert('请输入结束时间!');
						return false;
					}
					if(endDate < startDate) {
						alert("结束时间需大于开始时间")
						return false;
					}
					var param = {
						contStartDate : $('#contSDate').val(),
						contEndDate : $('#contEDate').val(),
						startDate : $('#startDate').val(),
						endDate : $('#endDate').val(),
						firstDay : $('#firstDay').val(),
						payMode : $('#payMode').val(),
						money : $('#money').val(),
						increaseMode : $('#increaseMode').val(),
						increaseRate : $('#increaseRate').val()
					};
					$.post("${ctx}/bis/bis-cont!doAutoAdd.action", param, function(result) {
						var jsonData = eval('('+result+')');
						var data = jsonData.data;
						$.each(data,function(i,jObj){
							var date = jObj.mustYear+"-"+jObj.mustMonth;
							//var errorflag = checkDateExist(nd);
							if(checkDateExist(date, cloneCount+1)) {
								//alert(date+"的记录已存在");
								//return false;
							} else {
								addItemVal(jObj,date);
							}
						});
					});
				}
				ymPrompt.close();
			},
			btn:[["确定",'ok'],["退出",'cancel']]
		});
	}
	function addItemVal(jObj,date){
		
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();
		$(trApprover_new).find(":input").each(function(i){
			this.name=this.name.replace('0',index);
			this.id=this.id.replace('0',index);
			if(this.name.indexOf('sequenceNo')!=-1) {
				$(this).val(jObj.sequenceNo);
			}
			if(this.name.indexOf('mustYear')!=-1) {
				$(this).val(jObj.mustYear);
			}
			if(this.name.indexOf('mustMonth')!=-1) {
				$(this).val(jObj.mustMonth);
			}
			if(this.name.indexOf('money')!=-1) {
				if(isEmpty(jObj.money) || jObj.money==0) {
					$(this).val('待计算');
					$(this).css({color: "#CCCCCC"});
				} else {
					$(this).val(jObj.money);
					$(this).css({color:"#000000"});
				}
			}
			if(this.name.indexOf('collDate')!=-1) {
				$(this).val(jObj.collDate);
			}
			if(this.name.indexOf('describe')!=-1) {
				$(this).val(jObj.describe);
			}
			if(this.name.indexOf('entityTmpId')!=-1) {
				$(this).val(jObj.entityTmpId);
			}
		});
		
		var domId='bisMustAttachId_'+index;
		var attachFlgId='attachFlg_'+index;
		$(trApprover_new).find(".attachId").bind("click", function() {
			openAttachment('附件管理',jObj.entityTmpId,domId,attachFlgId);
		});
		
		$(trApprover_new).find(":input[type=checkbox]").attr("date", date);
		$("#tbConItem").append(trApprover_new);
		index++;
	}
	
	function bindClickCreditAtta(trApprover_new, index) {
		$.post("${ctx}/bis/bis-cont!getAttachTmpId.action", function(result) {
			var domId='bisMustAttachId_'+index;
			var attachFlgId='attachFlg_'+index;
			$(trApprover_new).find(".attachId").bind("click", function() {
				openAttachment('附件管理',result,domId,attachFlgId);
			});
			$("#entityTmpId_"+index).val(result);
		});
	}
</script>

<s:if test="#mustOperator && (statusCd==3 || !activeBl)">
<script type="text/javascript">
	bindTblEv('tbConItem');
</script>
</s:if>

<script type="text/javascript">
	//$("#tbConItem :input").filter(".approved").attr("readonly","readonly");
	//$("#tbConItem :input").filter(".approved").addClass("inputBorder_readOnly");
	//$("#tbConItem .Wdate").filter(".approved").attr("onfocus","");
	//$("#tbConItem .Wdate").filter(".approved").removeClass("Wdate");
	//$("#tbConItem :input[type='checkbox']").filter(".approved").click(function(){
		//return false;
	//});
</script>