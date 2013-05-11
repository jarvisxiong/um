<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@ include file="/common/global.jsp" %>
<script type="text/javascript">
   /**
  	* 计算百分率
  	* 参数：value1：被除数、value2：除数、domId：值显示的控件ID
   	*/
	function calculatePerc(value1,value2,domId){
		var number1 = 0;
		var number2 = 0;
		if(value1 != null && ""!=value1){
			number1 = parseFloat(value1);
		}
		if(value2 != null && ""!=value2){
			number2 = parseFloat(value2);
		}
		if((isNaN(number1) && isNaN(number2)) || number1 == 0){
			$("#"+domId).html("0");
			return false;
		}
		var num = (number2/number1)*100;
		var prec = Math.round(num*100)/100;
		var retValue = prec.toString();
		var pos_decimal = retValue.indexOf('.');
		if (pos_decimal < 0){
			pos_decimal = retValue.length;
			retValue += '.';
		}
		while (retValue.length <= pos_decimal + 2){
			retValue += '0';
		}
		$("#"+domId).html(retValue+"%");
		return retValue+"%";
	}
	
   /**
  	* 计算和几行百分率
  	* 参数：id1：被除数值ID、id2：除数值ID、domId：值显示的控件ID、domIdTr：tr title属性显示ID
   	*/
	function calculateCount(id1,id2,domId,domIdTr){
		var value1=0; 
		var value2=0;
		$("#sysf_table").find("#"+id1).each(function(i){
			var val = $(this).val();
			if(val != null && "" != val){
				value1 = parseFloat(value1) + parseFloat(val);
			}
		});
		$("#sysf_table").find("#"+id2).each(function(i){
			var val = $(this).val();
			if(val != null && "" != val){
				value2 = parseFloat(value2) + parseFloat(val);
			}
		});
		var retValue = calculatePerc(value1,value2,domId);
		if(retValue == null  || !retValue || "false" == retValue){
			retValue = "0";
		}
		$("#"+domIdTr).attr("title",retValue); //设置title
	}
</script>

<div style="width: 98%;height: 100%;overflow-x: hidden;margin-left: 15px;">
	<table id="sysf_table" class="decTable" style="width: 100%;height: 100%;" cellpadding="0" cellspacing="0">
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="10%"/>
		<thead>
			<tr id="linebottom">
				<th rowspan="2" style="text-align: left;">项目</th>
				<th colspan="3"><div style="margin:0px 80px 0px 80px;">今年累计</div></th>
				<th colspan="3"><div style="margin:0px 70px 0px 70px;">本月(${curMonth})</div></th>
				<th colspan="3"><div style="margin:0px 70px 0px 70px;">截止(${lastMonth})月</div></th>
			</tr>
			<tr id="linebottom">
				<th><div>租金</div></th>
				<th><div>物管费</div></th>
				<th><div>能耗</div></th>
				<th><div>租金</div></th>
				<th><div>物管费</div></th>
				<th><div>能耗</div></th>
				<th><div>租金</div></th>
				<th><div>物管费</div></th>
				<th><div>能耗</div></th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="bisRentalCollectionRateUtilList" status="sta">
				<tr style="height: 35px;">
					<td style="text-align: right;" class="pd-chill-tip" title="<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId")) %>">
						<%--
						<input type="hidden" id="status<s:property value="#sta.index"/>" value="${statusFlg}"/>
						<input type="hidden" id="updator<s:property value="#sta.index"/>" value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("updator")) %>"/>
						<input type="hidden" id="updatedDate<s:property value="#sta.index"/>" value="<s:date name="updatedDate" format="yyyy-MM-dd"/>"/>
						--%>
						<div class="short_div">
							<%=CodeNameUtil.getProjectCity(JspUtil.findString("bisProjectId")) %>
							<span style="font-weight: bolder;">. . . . . . . . . . . . . . .</span>
						</div>
					</td>
					<td class="pd-chill-tip" title="应收(万元):${rentalRentYs},实收(万元):${rentalRentSs}">
						<input type="hidden" id="rentalRentYs" value="${rentalRentYs}"/>
						<input type="hidden" id="rentalRentSs" value="${rentalRentSs}"/>
						<span id="zj_lj<s:property value="#sta.index"/>"></span>
						<script type="text/javascript">
							calculatePerc('${rentalRentYs}','${rentalRentSs}','zj_lj'+<s:property value="#sta.index"/>);
						</script>
					</td>
					<td class="pd-chill-tip" title="应收(万元):${rentalPropertyYs},实收(万元):${rentalPropertySs}">
						<input type="hidden" id="rentalPropertyYs" value="${rentalPropertyYs}"/>
						<input type="hidden" id="rentalPropertySs" value="${rentalPropertySs}"/>
						<span id="wy_lj<s:property value="#sta.index"/>"></span>
						<script type="text/javascript">
							calculatePerc('${rentalPropertyYs}','${rentalPropertySs}','wy_lj'+<s:property value="#sta.index"/>);
						</script>
					</td>
					<td class="pd-chill-tip" title="应收(万元):${rentalEnergyYs},实收(万元):${rentalEnergySs}">
						<input type="hidden" id="rentalEnergyYs" value="${rentalEnergyYs}"/>
						<input type="hidden" id="rentalEnergySs" value="${rentalEnergySs}"/>
						<span id="nh_lj<s:property value="#sta.index"/>"></span>
						<script type="text/javascript">
							calculatePerc('${rentalEnergyYs}','${rentalEnergySs}','nh_lj'+<s:property value="#sta.index"/>);
						</script>
					</td>
					<td class="pd-chill-tip" title="应收(万元):${rentalRentYsCur},实收(万元):${rentalRentSsCur}">
						<input type="hidden" id="rentalRentYsCur" value="${rentalRentYsCur}"/>
						<input type="hidden" id="rentalRentSsCur" value="${rentalRentSsCur}"/>
						<span id="zj_y<s:property value="#sta.index"/>"></span>
						<script type="text/javascript">
							calculatePerc('${rentalRentYsCur}','${rentalRentSsCur}','zj_y'+<s:property value="#sta.index"/>);
						</script>
					</td>
					<td class="pd-chill-tip" title="应收(万元):${rentalPropertyYsCur},实收(万元):${rentalPropertySsCur}">
						<input type="hidden" id="rentalPropertyYsCur" value="${rentalPropertyYsCur}"/>
						<input type="hidden" id="rentalPropertySsCur" value="${rentalPropertySsCur}"/>
						<span id="wy_y<s:property value="#sta.index"/>"></span>
						<script type="text/javascript">
							calculatePerc('${rentalPropertyYsCur}','${rentalPropertySsCur}','wy_y'+<s:property value="#sta.index"/>);
						</script>
					</td>
					<td class="pd-chill-tip" title="应收(万元):${rentalEnergyYsCur},实收(万元):${rentalEnergySsCur}">
						<input type="hidden" id="rentalEnergyYsCur" value="${rentalEnergyYsCur}"/>
						<input type="hidden" id="rentalEnergySsCur" value="${rentalEnergySsCur}"/>
						<span id="nh_y<s:property value="#sta.index"/>"></span>
						<script type="text/javascript">
							calculatePerc('${rentalEnergyYsCur}','${rentalEnergySsCur}','nh_y'+<s:property value="#sta.index"/>);
						</script>
					</td>
					<td class="pd-chill-tip" title="应收(万元):${lastRentalRentYs},实收(万元):${lastRentalRentSs}">
						<input type="hidden" id="lastRentalRentYs" value="${lastRentalRentYs}"/>
						<input type="hidden" id="lastRentalRentSs" value="${lastRentalRentSs}"/>
						<span id="zj_n_y<s:property value="#sta.index"/>"></span>
						<script>
							calculatePerc('${lastRentalRentYs}','${lastRentalRentSs}','zj_n_y'+<s:property value="#sta.index"/>);
						</script>
					</td>
					<td class="pd-chill-tip" title="应收(万元):${lastRentalPropertyYs},实收(万元):${lastRentalPropertySs}">
						<input type="hidden" id="lastRentalPropertyYs" value="${lastRentalPropertyYs}"/>
						<input type="hidden" id="lastRentalPropertySs" value="${lastRentalPropertySs}"/>
						<span id="wy_n_y<s:property value="#sta.index"/>"></span>
						<script type="text/javascript">
							calculatePerc('${lastRentalPropertyYs}','${lastRentalPropertySs}','wy_n_y'+<s:property value="#sta.index"/>);
						</script>
					</td>
					<td class="pd-chill-tip" title="应收(万元):${lastRentalEnergyYs},实收(万元):${lastRentalEnergySs}">
						<input type="hidden" id="lastRentalEnergyYs" value="${lastRentalEnergyYs}"/>
						<input type="hidden" id="lastRentalEnergySs" value="${lastRentalEnergySs}"/>
						<span id="nh_n_y<s:property value="#sta.index"/>"></span>
						<script type="text/javascript">
							calculatePerc('${lastRentalEnergyYs}','${lastRentalEnergySs}','nh_n_y'+<s:property value="#sta.index"/>);
						</script>
					</td>
				</tr>
			</s:iterator>
			
			<%-- 合计列 --%>
			<s:if test="bisRentalCollectionRateUtilList.size() > 0">
				<tr style="height: 45px;">
					<td style="text-align: left;font-weight: bolder;">合计</td>
					<td id="rentalRentCountTr" class="pd-chill-tip">
						<div class="countline" id="rentalRentCount"></div>
						<div class="countline2"></div>
						<script type="text/javascript">
							calculateCount("rentalRentYs","rentalRentSs","rentalRentCount","rentalRentCountTr");
						</script>
					</td>
					<td id="rentalPropertyCountTr" class="pd-chill-tip">
						<div class="countline" id="rentalPropertyCount"></div>
						<div class="countline2"></div>
						<script type="text/javascript">
							calculateCount("rentalPropertyYs","rentalPropertySs","rentalPropertyCount","rentalPropertyCountTr");
						</script>
					</td>
					<td id="rentalEnergyCountTr" class="pd-chill-tip">
						<div class="countline" id="rentalEnergyCount"></div>
						<div class="countline2"></div>
						<script type="text/javascript">
							calculateCount("rentalEnergyYs","rentalEnergySs","rentalEnergyCount","rentalEnergyCountTr");
						</script>
					</td>
					<td id="rentalRentCurCountTr" class="pd-chill-tip">
						<div class="countline" id="rentalRentCurCount"></div>
						<div class="countline2"></div>
						<script type="text/javascript">
							calculateCount("rentalRentYsCur","rentalRentSsCur","rentalRentCurCount","rentalRentCurCountTr");
						</script>
					</td>
					<td id="rentalPropertyCurCountTr" class="pd-chill-tip">
						<div class="countline" id="rentalPropertyCurCount"></div>
						<div class="countline2"></div>
						<script type="text/javascript">
							calculateCount("rentalPropertyYsCur","rentalPropertySsCur","rentalPropertyCurCount","rentalPropertyCurCountTr");
						</script>
					</td>
					<td id="rentalEnergyCurCountTr" class="pd-chill-tip">
						<div class="countline" id="rentalEnergyCurCount"></div>
						<div class="countline2"></div>
						<script type="text/javascript">
							calculateCount("rentalEnergyYsCur","rentalEnergySsCur","rentalEnergyCurCount","rentalEnergyCurCountTr");
						</script>
					</td>
					<td id="lastRentalRentCountTr" class="pd-chill-tip">
						<div class="countline" id="lastRentalRentCount"></div>
						<div class="countline2"></div>
						<script type="text/javascript" class="pd-chill-tip">
							calculateCount("lastRentalRentYs","lastRentalRentSs","lastRentalRentCount","lastRentalRentCountTr");
						</script>
					</td>
					<td id="lastRentalPropertyCountTr" class="pd-chill-tip">
						<div class="countline" id="lastRentalPropertyCount"></div>
						<div class="countline2"></div>
						<script type="text/javascript">
							calculateCount("lastRentalPropertyYs","lastRentalPropertySs","lastRentalPropertyCount","lastRentalPropertyCountTr");
						</script>
					</td>
					<td id="lastRentalEnergyCountTr" class="pd-chill-tip">
						<div class="countline" id="lastRentalEnergyCount"></div>
						<div class="countline2"></div>
						<script type="text/javascript">
							calculateCount("lastRentalEnergyYs","lastRentalEnergySs","lastRentalEnergyCount","lastRentalEnergyCountTr");
						</script>
					</td>
				</tr>
			</s:if> 
		</tbody>
	</table>
</div>
