<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--商业公司返租合同费用付款审批表--%>
<%@ include file="template-header.jsp"%>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>

<%@page import="com.hhz.ump.util.DictContants"%>
<div align="center" class="billContent">
<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
</s:set> 

<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
<%@ include file="template-var.jsp"%>
<table class="mainTable">
	<col style="width: 100px; text-align: center;" />
	<col />
	<col />
	<col />
	<col />

	<col style="width: 40px" />
	<tr>
		<td class="td_title require"></td>
		<td colspan="5" class="chkGroup" align="center"  validate="required">
			<table class="tb_checkbox">
			<col width="150">
			<col width="150">
			<tr>
			<td><s:checkbox name="templateBean.outFlag" id="outFlag" cssClass="group"></s:checkbox>预算外</td>
			<td><s:checkbox name="templateBean.inFlag" id="inFlag" cssClass="group"></s:checkbox>预算内</td>
			</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="td_title" style="text-align: center;">项目名称</td>
		<td style="text-align: left" colspan="5">
		<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"
			id="projectName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if >
			<s:else> class="inputBorderNoTip" </s:else> /> 
			<input type="hidden" id="projectCd" name="templateBean.projectCd" value="${templateBean.projectCd}" /></td>

	</tr>
	
	<tr>
		<td class="td_title" style="text-align: center;">本次支付总额</td>
		<td colspan="5" style="text-align: left">
		 <input class="inputBorder" validate="required" id="paymentTotalAmt" name="templateBean.paymentTotalAmt" onblur="formatVal($(this));"	value="${templateBean.paymentTotalAmt}" readonly="readonly" ></input>
		</td>
	</tr>
	<tr>
		<td class="td_title" style="text-align: center;">内容简述 （详细内容附后）</td>
		<td colspan="5" style="text-align: left">
		<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark">${templateBean.remark}</textarea>
		</td>
	</tr>		
	<tr id="trHeader">
		<td align="center">合同编号</td>
		<td align="center">账户名(业主)</td>
		<td align="center">商铺位置</td>
		<td align="center" width="40px">租金所属期</td>
		<td align="center">实付租金</td>
		<td align="center" width="20px">操作</td>
	</tr>
	<s:if test="statusCd==0 || statusCd==3">
		<tr id="trBackRentCont" style="margin-bottom: 5px;" ctr='trBackRent'>
			<td style="text-align: left">
				<input class="inputBorder supName" validate="required" id="contNo" type="text" name="templateBean.businessBackRentCons[0].contNo" />
			</td>
			<td style="text-align: left">
				<input validate="required" class="inputBorder" readonly="readonly" type="text" id="owner" name="templateBean.businessBackRentCons[0].owner" />
			</td>
			<td style="text-align: left">
				<input validate="required" class="inputBorder" readonly="readonly" type="text" id="shopPosition"
				name="templateBean.businessBackRentCons[0].shopPosition" />
			</td>

			<td style="text-align: left">
				<input validate="required" class="inputBorder" type="text" id="blongPeriod"
				name="templateBean.businessBackRentCons[0].blongPeriod" />
			</td>
			<td style="text-align: left">
				<input validate="required" class="inputBorder" type="text" id="downPayment"
				name="templateBean.businessBackRentCons[0].downPayment"    ctrType="number" maxlength="15" onblur="formatAndCalc($(this));"/>
			</td>
			<td style="text-align: center;">
				<a href="javascript:void(0)" onclick="delArrItem(this);"> <img src="${ctx}/resources/images/common/del_22_22.gif"
				title="删除" border="0" /> </a>
			</td>
		</tr>
	</s:if>
	<s:set var="backRentCount">
		<s:property value="templateBean.businessBackRentCons.size()" />
	</s:set>
	<s:iterator value="templateBean.businessBackRentCons" var="arrItem" status="s1">
		<tr style="margin-bottom: 5px;" id="trBackRentCont<s:property value="#s1.index" />" style="margin-bottom: 5px;" ctr='trBackRent'>
			<td class="tdGroupNodes"  style="text-align: left">
			<input class="inputBorder supName" validate="required"
				id="contNo<s:property value="#s1.index" />" type="text" name="templateBean.businessBackRentCons[<s:property value="#s1.index" />].contNo"
				value="${arrItem.contNo}" /></td>

			<td class="tdGroupNodes" style="text-align: left">
			<input validate="required" class="inputBorder" type="text" id="owner<s:property value="#s1.index" />"
				name="templateBean.businessBackRentCons[<s:property value="#s1.index" />].owner" value="${arrItem.owner}"  readonly="readonly"/></td>
			<td class="tdGroupNodes" style="text-align: left">
			<input validate="required" class="inputBorder" type="text" id="shopPosition<s:property value="#s1.index" />"
				name="templateBean.businessBackRentCons[<s:property value="#s1.index" />].shopPosition" value="${arrItem.shopPosition}" readonly="readonly" /></td>

			<td class="tdGroupNodes" style="text-align: left">
			<input validate="required" class="inputBorder" type="text" 
				name="templateBean.businessBackRentCons[<s:property value="#s1.index" />].blongPeriod" value="${arrItem.blongPeriod}"  maxlength="50"/></td>

			<td class="tdGroupNodes" style="text-align: left"><input validate="required" class="inputBorder" type="text"
				name="templateBean.businessBackRentCons[<s:property value="#s1.index" />].downPayment" value="${arrItem.downPayment}" ctrType="number" maxlength="15" onblur="formatAndCalc($(this));"/></td>

			<td style="width: 80px; text-align: center;"><s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
				<a href="javascript:void(0)" onclick="delArrItem(this)"> 
				<img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0" /> </a>
			</s:if></td>

		</tr>
		<script type="text/javascript">
		var indexTmp = '${s1.index}';
		var ids = {
				contNo : "contNo" + indexTmp,
				owner : 'owner' + indexTmp,
				shopPosition : 'shopPosition' + indexTmp
			};	
				$("#contNo" + indexTmp).quickSearch("${ctx}/bis/bis-cont!quickSearch.action", [ 'contNo', 'storeNo' ], ids, {
	
				supStatus : '0'
			});
	
		</script>

	</s:iterator>


	<tr>
		<td class="td_title" colspan="3"></td><td class="td_title" >总份数：<span id="backRentCountId">0</span></td>
		<td class="td_title" >总租金：￥<span id="backRentSumMoneyId">0</span></td>
		<td class="td_title" ></td>
	</tr>
	<s:if test="statusCd==0 || statusCd==3">
	<tr>
		<td class="td_title" colspan="6" style="text-align: center">
		<input type="button" class="btn_blue_75_55" value="增加合同" onclick="addArrItem();"/>
		</td>
	</tr>
	</s:if>
</table>
<%@ include file="template-approver.jsp"%>
</form>

</div>

<%@ include file="template-footer.jsp"%>

<script type="text/javascript">
	$('#bisProjectName').onSelect( {
		muti : false
	});
	$(document).ready(function(){
		calcBackRentCont();
	});
</script>
<script type="text/javascript">
//判断是否为数字
function isNum(txt) {
	var _val = txt.trim();
	if ("" == _val || _val.length < 1 || isNaN(txt)) {

		return false;
	}

	var reg = /^(([0-9]+.?[0-9]*)|(\d*[0-9]+))$/; // /^((\d*[1-9]*)|([0]))(\.\d+)?$/;
	var _isN = reg.test(_val);
	// alert('isNum' + _val + " " + txt+" _isN:"+_isN);
	return _isN;
}

String.prototype.trim = function() {
	// 用正则表达式将前后空格
	// 用空字符串替代。
	var t = this.replace(/(^\s*)|(\s*$)/g, "");
	return t.replace(/(^　*)|(　*$)/g, "");
}
function toDecimal(x) {

	var f = parseFloat(x);
	if (isNaN(f)) {
		return "0";
	}
	var f = Math.round(x * 100) / 100;
	var s = f.toString();
	var rs = s.indexOf('.');
	if (rs < 0) {
		rs = s.length;
		s += '.';
	}
	while (s.length <= rs + 2) {
		s += '0';
	}
	return s;
}
	var trBackRentCont = $("#trBackRentCont").clone();
	$("#trBackRentCont").remove();

	var trBackCount = 0;//TR计数器
	var arraIndex = 0;

	try {

		arraIndex = Number("${backRentCount}");//获取单位控件的个数
	} catch (e) {
	}

	//增加回租合同
	function addArrItem() {

		var trArra_new = trBackRentCont.clone();
		//cloneArrCount++;
		$(trArra_new).show();
		trBackCount = $("tr[ctr=trBackRent]").length;
		$(trArra_new).attr("id", $(trArra_new).attr("id") + trBackCount);
		$(trArra_new).find(".inputBorder").each(function(i) {
			this.name = this.name.replace('0', arraIndex);
			if (this.id) {
				this.id = this.id + arraIndex;

			}
		});

		var ids = {
			contNo : "contNo" + arraIndex,
			owner : 'owner' + arraIndex,
			storeNo : 'shopPosition' + arraIndex
		};

		if ((trBackCount - 1) > -1) {
			$("#trBackRentCont" + (trBackCount - 1)).after(trArra_new);
		} else {
			$("#trHeader").after(trArra_new);
		}

		$("#contNo" + arraIndex).quickSearch("${ctx}/bis/bis-cont!quickSearch.action", [ 'contNo', 'storeNo' ], ids, {

			supStatus : '0'
		});
		arraIndex++;
     bindEvent();
	}
	function bindEvent(){		
		$("input[ctrType=number]").unbind("change");
		$("input[ctrType=number]").bind("change", function() {

               var _totalAmt=0.0;
		       $.each($("input[ctrType=number]"),function(i,singleDownCost){
               var _cost=$(singleDownCost).val().replace(/,/g, "");
                          if(isNum(_cost)){                              
                        	  _totalAmt+=parseFloat(_cost);                        	  
                              }
			       });
              var _val=""+_totalAmt;
              if(_val!="false"){
                $("#paymentTotalAmt").val(toDecimal(_val));                
                formatVal($("#paymentTotalAmt"));
              }
		       
		});
	}
	//删除用户
	function delArrItem(dom) {
		if($("input[ctrType=number]").length>1){
		    $(dom).parent().parent().remove();
			$.each($("tr[ctr=trBackRent]"), function(i, trBack) {
				$(trBack).attr("id", "trBackRentCont" + i);
			});
		}else{	
			alert("返租合同信息需要至少有一条！");
			return;
		}
	}
	/**将格式化和计算放到一起来*/
	function formatAndCalc(demo){
		formatVal(demo);
		calcBackRentCont();
	}
	/**
	* 计算返租合同的份数和总金额
	*/
	function calcBackRentCont(){
		var count = $("tr[ctr='trBackRent']").size();
		var sumMoney = 0;
		$("#backRentCountId").html(count);
		$.each($("tr[ctr='trBackRent']"),function(i,j){
			sumMoney +=  toFloat($(j).find("input").eq(4).val());
		});
		$("#backRentSumMoneyId").html(sumMoney);
	}
	/**转为浮点类型*/
	function toFloat(value){
		var demo = $.trim(value);
		if(demo==null||demo==undefined||demo==""||demo=="null"){
			return 0;
		}else{
			return parseFloat(demo);
		}
	}
</script>
<!-- 默认1条申请记录 -->
<s:if test="#canEdit== 'true'">
	<script>
	 bindEvent();
</script>

</s:if>
<s:if test="resApproveInfoId==null">
	<script type="text/javascript">
	addArrItem();
</script>
</s:if>
