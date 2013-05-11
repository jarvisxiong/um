<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%--搜索表单 --%>
<s:if test="'query'==formType">
	<div id="queryMatePriceDiv">
			<table style="width: 100%;" >
				<colgroup>
					<col width="30%"/>
					<col width="70%"/>
				</colgroup>						
				<tbody style="margin-top: 15px;">								
					<tr >
						<td align="right">产品业态:</td>
						<td>
							<s:select  cl="1" list="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" id="q_bussiness" q_bussiness="1" name="entity.bussinessCd" listKey="key" listValue="value" cssClass="ipt" cssStyle="width:100px;"/>
						</td>
					</tr>
					<tr>
						<td align="right">工料范围:</td>
						<td>
							<s:select  cl="1" list="@com.hhz.ump.util.DictMapUtil@getMapProdMaterialZoneCd()" id="q_materialZone" name="entity.materialZoneCd" listKey="key" listValue="value" cssClass="ipt" cssStyle="width:100px;"/>
						</td>
					</tr>								
					<tr>
						<td align="right">地区:</td>
						<td>
							<s:if test="areaCd!=null">
								<s:select cl="1" list="@com.hhz.ump.util.DictMapUtil@getMapProdAreaCd()" id="q_areaCd" name="entity.areaCd" listKey="key" listValue="value" cssClass="ipt" cssStyle="width:100px;" value="areaCd"/>
							</s:if>
							<s:else>
								<s:select cl="1" list="@com.hhz.ump.util.DictMapUtil@getMapProdAreaCd()" id="q_areaCd" name="entity.areaCd" listKey="key" listValue="value" cssClass="ipt" cssStyle="width:100px;"/>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="right">时间:</td>
						<td>
						
						<s:if test="ym!=null">
							<input id="q_ym" cl="1" class="date ipt" type="text" style="color:red;width:120px;" name="monthAndYear" value="${ym}" onfocus="WdatePicker({dateFmt:'yyyy-MM',onpicked:function(dp){}})"/>
						</s:if>
						<s:else>
						<input id="q_ym" cl="1" class="date ipt" type="text" style="color:red;width:120px;" name="monthAndYear" value="<s:if test="yearCd!=null&&monthCd!=null">${yearCd}-${monthCd}</s:if>" onfocus="WdatePicker({dateFmt:'yyyy-MM',onpicked:function(dp){}})"/>
						</s:else>
						</td>
					</tr>
				</tbody>
				
			</table>
	</div>
</s:if>

<s:if  test="'priceForm'==formType">
	<div id="priceFormDiv">
		<form id="newPriceForm" action="${ctx}/prod/prod-material-price!save.action" accept-charset="utf-8" method="post">			
			<table style="width: 100%;" >
				<colgroup>
					<col width="30%"/>
					<col width="70%"/>
				</colgroup>						
				<tbody style="margin-top: 15px;">
					<s:if test="entity!=null">
						<input  cl="1" type="hidden" name="prodMaterialPriceId" id="prodMaterialPriceId" value="${entity.prodMaterialPriceId}"></input>
					</s:if>
					<tr>
						<td align="right"><font color="red">*</font>时间:</td>
						<td>
							<s:if test="entity!=null">
								<input  value="${entity.yearCd}-${entity.monthCd}" readonly="readonly" id="input_ym" cl="1" class="date ipt" type="text" style="color:red;width:120px;border: 0px;" name="monthAndYear" />
							</s:if>
							<s:elseif test="ym!=null">
								<input  value="${ym}" readonly="readonly" id="input_ym" cl="1" class="date ipt" type="text" style="color:red;width:120px;border: 0px;" name="monthAndYear" />
							</s:elseif>
							<s:else>
								<input onchange="getBasicDetailPrice();" id="input_ym" cl="1" class="date ipt" type="text" style="color:red;width:120px;" name="monthAndYear" value="<s:if test="yearCd!=null&&monthCd!=null">${yearCd}-${monthCd}</s:if>" onfocus="WdatePicker({dateFmt:'yyyy-MM',onpicked:function(dp){}})"/>
							</s:else>
							
						</td>
					</tr>
					<tr>
						<td align="right"><font color="red">*</font>地区:</td>
						<td>
							<s:if test="entity!=null">
								<s:select cl="1" disabled="true" list="@com.hhz.ump.util.DictMapUtil@getMapProdAreaCd()" id="input_areaCd" name="entity.areaCd" listKey="key" listValue="value" cssClass="ipt" cssStyle="width:100px;" value="entity.areaCd"/>
							</s:if>
							<s:elseif test="areaCd!=null">
								<s:select cl="1"  list="@com.hhz.ump.util.DictMapUtil@getMapProdAreaCd()" id="input_areaCd" name="entity.areaCd" listKey="key" listValue="value" cssClass="ipt" cssStyle="width:100px;" value="areaCd"/>
							</s:elseif>
							<s:else>
								<s:select onchange="getBasicDetailPrice();"  cl="1" list="@com.hhz.ump.util.DictMapUtil@getMapProdAreaCd()" id="input_areaCd" name="entity.areaCd" listKey="key" listValue="value" cssClass="ipt" cssStyle="width:100px;"/>
							</s:else>
							
						</td>
					</tr>								
					<tr >
						<td align="right"><font color="red">*</font>产品业态:</td>
						<td>
							<s:if test="entity!=null">
								<s:select disabled="true"  cl="1" list="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" id="input_bussiness" name="entity.bussinessCd" listKey="key" listValue="value" cssClass="ipt" cssStyle="width:100px;" value="entity.bussinessCd"/>
							</s:if>
							<s:else>
								<s:select  onchange="getBasicDetailPrice();"  cl="1" list="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" id="input_bussiness" name="entity.bussinessCd" listKey="key" listValue="value" cssClass="ipt" cssStyle="width:100px;"/>
							</s:else>
							
						</td>
					</tr>
					<tr>
						<td align="right"><font color="red">*</font>工料范围:</td>
						<td>
							<s:if test="entity!=null">
								<s:select disabled="true" cl="1" list="@com.hhz.ump.util.DictMapUtil@getMapProdMaterialZoneCd()" id="input_materialZone" name="entity.materialZoneCd" listKey="key" listValue="value" cssClass="ipt" cssStyle="width:100px;"  value="entity.materialZoneCd"/>
							</s:if>
							<s:else>
								<s:select  onchange="getBasicDetailPrice();"  cl="1" list="@com.hhz.ump.util.DictMapUtil@getMapProdMaterialZoneCd()" id="input_materialZone" name="entity.materialZoneCd" listKey="key" listValue="value" cssClass="ipt" cssStyle="width:100px;"/>
							</s:else>
							
						</td>
					</tr>
					<%--
					<tr>
						<td align="right">基准工料价格</td>
						<td><input  cl="1" name="basicPrice"  class="ipt"  readonly="readonly" disabled="disabled" id="inp_basicPrice" value="${prodVersionDetail.price}" ></input></td>
					</tr>
					 --%>	
					<tr>
						<td align="right">工料价格:</td>
						<td><input  onkeyup="clearNoNum_1(this);"  onblur="formatFloat(this,6,10000000);"    cl="1" name="entity.currentMonthPrice"  class="ipt" id="input_currentMonthPrice" value="${entity.currentMonthPrice }"></input></td>
					</tr>									
					<tr style="margin-top: 50px;line-height: 30px;height: 30px;">
						<td colspan="2">
							<div style="margin-left: 60px;">
							<font style="color: red;">
							<span>各工料价格取当地信息价，如无信息价，取市场价
							    <pre>1）人工：以当地定额机构发布的相关调价文件。上海信息价动态取模板工的下限； 
								<pre>2）砼：取泵送C30石子粒径5-25的信息价； 
								<pre>3）钢筋：取直径18mm II级螺纹钢的信息价； 
								<pre>4）砌块：取加气砌块（200×300×600mm/A3.5级、B06）的信息价</span></font>
							</div>
						</td>
					</tr>
					
				</tbody>
				
			</table>
		</form>
	</div>
</s:if>
