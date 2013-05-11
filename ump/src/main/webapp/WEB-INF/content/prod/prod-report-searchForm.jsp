<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%--价格指数搜索表单 --%>
<s:if test="formType=='priceIndex'">
	<div id="piFormDiv" >
	 	<form id="pisearchForm" action="prod-report!priceIndexTwo.action" method="post" accept-charset="utf-8">
	 		<table style="width: 100%;">
	 			<tr><td  align="right">业态:</td>
		 			<td>
		 				<s:select id="sele_bussinessCd" list="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" name="bussinessCd" listKey="key" listValue="value" cssClass="ipt"  value="1"/>	 		
		 			</td>
	 			</tr>
	 			<tr  ><td  align="right">时间:</td><td><input id="inp_ym" class="date ipt" type="text"  name="ym" value="<s:if test="yearCd!=null&&monthCd!=null">${yearCd}-${monthCd}</s:if>" onfocus="WdatePicker({dateFmt:'yyyy-MM',onpicked:function(dp){}})"/></td></tr>
	 				 			 			
	 		</table>
	 	</form>
    </div>
</s:if>
<%--工料价格曲线 --%>
<s:if test="formType=='matePriceChart'">
	<div id="piFormDiv" >
	 	<form id="pisearchForm" action="prod-report!priceIndexTwo.action" method="post" accept-charset="utf-8">
	 		<table style="width: 100%;">
	 			<tr>
	 				<td  align="right">业态:</td>
		 			<td>
		 				<s:select id="sele_bussinessCd" list="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" name="bussinessCd" listKey="key" listValue="value" cssClass="ipt"  value="1"/>	 		
		 			</td>
	 			</tr>
	 			<tr  >
	 				<td  align="right">时间:</td>
		 			<td>
		 				<input id="inp_ym" class="date ipt" type="text"  name="ym" value="<s:if test="yearCd!=null&&monthCd!=null">${yearCd}-${monthCd}</s:if>" onfocus="WdatePicker({dateFmt:'yyyy-MM',onpicked:function(dp){}})"/>
		 			</td>
	 			</tr>
	 			<tr>
	 			 	<td  align="right">工料范围:</td>
	 			 	<td>
	 			 		<s:select id="sele_mateZoneCd" list="@com.hhz.ump.util.DictMapUtil@getMapProdMaterialZoneCd()" name="mateZoneCd" listKey="key" listValue="value" cssClass="ipt"  value="1"/>
	 			 	</td>
	 			</tr>	 			 			
	 		</table>
	 	</form>
    </div>
</s:if>
<%--价格指数曲线图表单 --%>
<s:if test="formType=='priceIndexChart'">
  <div id="searchForm">
 	<form   id="chartForm"  action="prod-report!priceIndexChart.action"  method="post" accept-charset="utf-8">
 		<table >
 			<tr><td  align="right">地区:</td><td><s:select list="@com.hhz.ump.util.DictMapUtil@getMapProdAreaCd()" id="input_areaCd" name="entity.areaCd" listKey="key" listValue="value" cssClass="ipt"  onchange="selectAreaCd(this);"/></td></tr>
 			<tr  ><td  align="right">产品业态:</td><td><s:select  list="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" id="input_bussiness" name="entity.bussinessCd" listKey="key" listValue="value" cssClass="ipt" onchange="selectBussinessCd(this);"/></td></tr>
 			<tr  ><td  align="right">开始时间:</td><td><input id="input_dateFrom_3"  class="date ipt" type="text"  name="dateFrom" value="<s:if test="yearCd!=null&&monthCd!=null">${yearCd}-${monthCd}</s:if>" onfocus="WdatePicker({dateFmt:'yyyy-MM',onpicked:function(dp){}})"/></td></tr>
 			<tr  ><td  align="right">结束时间:</td><td><input id="input_dateTo_3"  class="date ipt" type="text"  name="dateTo" value="<s:if test="yearCd!=null&&monthCd!=null">${yearCd}-${monthCd}</s:if>" onfocus="WdatePicker({dateFmt:'yyyy-MM',onpicked:function(dp){}})"/></td></tr>			
 			
 			
 			
 		</table>
 	</form>
  </div>
  <div style="margin-left: 10px;margin-right: 5px;margin-top: 10px;"><font color="red" >注：建议时间间隔最大为18个月。</font></div>
</s:if>
	