<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%--版本号表单 --%>
<s:if test="'basicVersionForm'==formType">
	<div >
		<form id="newBasicVersionForm" action="${ctx}/prod/prod-basic-version!save.action" accept-charset="utf-8" method="post">			
			<table style="width: 100%;" >
				<colgroup>
					<col width="30%"/>
					<col width="70%"/>
				</colgroup>						
				<tbody style="margin-top: 15px;">							
					<tr >
						<td align="right"><font color="red">*</font>时间:</td>
						<td>
							<s:if test="prodBasicVersion!=null">
								<input type="hidden" name="prodBasicVersionId" value="${prodBasicVersion.prodBasicVersionId}"></input>
								 <input id="versionTime"  validate="required" readonly="readonly"   class="date ipt" type="text" value="${prodBasicVersion.yearCd}-${prodBasicVersion.monthCd}" name="versionTime"   style= "border:0px;"/>
								</s:if>
							<s:else>
								<input  id="versionTime"    class="date ipt" type="text" style="ipt" name="versionTime"  onfocus="WdatePicker({dateFmt:'yyyy-MM',onpicked:function(dp){}})"/>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="right"><font color="red">*</font>版本号:</td>
						<td>
							<s:if test="prodBasicVersion!=null">
								<input name="entity.versionNo" id="inp_versionNo" class="ipt" value="${prodBasicVersion.versionNo}" ></input>
							</s:if>
							<s:else>
								<input name="entity.versionNo"  id="inp_versionNo"  class="ipt" ></input>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="right">备注:</td>
						<td>
						<textarea rows="5"  id="inp_remark" class="ipt" name="entity.versionDes" style="font-size: 12px;font-weight: lighter;">${prodBasicVersion.versionDes}</textarea>
						</td>
					</tr>
					<s:if test="prodBasicVersion!=null">
					<tr>
						<td align="right">状态:</td>
						<td>
							<select name="active"  class="ipt">
								<option value="0">未使用</option>
								
								<option value="1"
								<s:if test="prodBasicVersion.active==1">
								selected="true"
								</s:if>
								>激活</option>
							</select>
						</td>
					</tr>
					</s:if>
				</tbody>
				
			</table>
		</form>
	</div>
</s:if>

<s:if test="'newVersionDetailForm'==formType">
	<%--版本号表单 --%>
	<div id="versionDetailForm">
		<form id="newVersionDetailForm" action="${ctx}/prod/prod-version-detail!save.action" accept-charset="utf-8" method="post">			
			<table style="width: 100%;" >
				<colgroup>
					<col width="30%"/>
					<col width="70%"/>
				</colgroup>						
				<tbody style="margin-top: 15px;">							
					<tr >
						<td align="right">版本号:</td>
						<td>										
							<s:if test="entity==null">
								<input name="versionNo" id="input_versionno" class="ipt" value="${prodBasicVersion.versionNo}" readonly="readonly"  style= "border:0px;"></input>	
								<input type="hidden" id="hidden_versionno" name="prodBasicVersionNo" value="${prodBasicVersion.prodBasicVersionId}"></input>									
							</s:if>
							<s:else>
								<input name="versionNo" id="input_versionno" class="ipt" value="${entity.prodBasicVersion.versionNo}" readonly="readonly"  style= "border:0px;"></input>	
								<input type="hidden" id="hidden_versionno" name="prodBasicVersionNo" value="${entity.prodBasicVersion.prodBasicVersionId}"></input>									
								<input type="hidden" id="hidden_prodVersionDetailId" name="prodVersionDetailId" value="${entity.prodVersionDetailId}"></input>									
							</s:else>
						</td>
					</tr>
					<%--取消地区 --%>
					<%--
					<tr>
					<td align="right">地区:</td>
					<td>
							<s:if test="entity==null">
								<input name="areaCd_un" id="input_areaCd" class="ipt" value="${entity.areaCd}" readonly="readonly"  style= "border:0px;"></input>	
								<input type="hidden" id="hidden_areaCd" name="entity.areaCd" value="${entity.areaCd}"></input>									
							</s:if>
							<s:else>
								<input name="areaCd_un" id="input_areaCd" class="ipt" value="<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdAreaCd()" value="entity.areaCd"/>" readonly="readonly"  style= "border:0px;"></input>	
								<input type="hidden" id="hidden_areaCd" name="entity.areaCd" value="${entity.areaCd}"></input>									
							</s:else>
					</td>					
					</tr>
					
					 --%>
					<tr >
						<td align="right"><font color="red">*</font>产品业态:</td>
						<td>
						<s:if test="entity!=null">
							<s:select value="entity.bussinessCd"  disabled="true" list="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" id="input_bussiness"  name="entity.bussinessCd"  bussinesscd="bussinesscd" listKey="key" listValue="value" cssClass="ipt" cssStyle="width:100px;"/>
						</s:if>
						<s:else>
							<s:select onchange="loadEstimatePrice();"  value="entity.bussinessCd" list="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" id="input_bussiness"  name="entity.bussinessCd"  bussinesscd="bussinesscd" listKey="key" listValue="value" cssClass="ipt" cssStyle="width:100px;"/>
						</s:else>
						</td>
					</tr>
					<tr>
						<td align="right"><font color="red">*</font>工料范围:</td>
						<td>
							<%-- <input name="entity.versionNo"  class="ipt"></input>--%>
							<s:if test="entity!=null">
								<s:select  value="entity.materialZoneCd" disabled="true" list="@com.hhz.ump.util.DictMapUtil@getMapProdMaterialZoneCd()" id="input_materialZone"  name="entity.materialZoneCd" materialzonecd="materialzonecd" listKey="key" listValue="value" cssClass="ipt" cssStyle="width:100px;"/>
							</s:if>
							<s:else>
								<s:select onchange="loadEstimatePrice();"  value="entity.materialZoneCd" list="@com.hhz.ump.util.DictMapUtil@getMapProdMaterialZoneCd()" id="input_materialZone"  name="entity.materialZoneCd" materialzonecd="materialzonecd" listKey="key" listValue="value" cssClass="ipt" cssStyle="width:100px;"/>
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="right">消耗量(/m2):</td>
						<td><input onkeyup="clearNoNum_1(this);"  onblur="formatFloat(this,6,10000000);"  name="entity.permeterQuantity"  class="ipt" id="input_permeterQuantity" permeterQuantity="1" value="${entity.permeterQuantity}"></input></td>
					</tr>
					<tr>
						<td align="right">工料价格:</td>
						<td><input  onkeyup="clearNoNum_1(this);"  onblur="formatFloat(this,6,10000000);"  name="entity.price"  class="ipt" id="input_price" price="1" value="${entity.price}"></input></td>
					</tr>
					<tr style="margin-top: 5px;margin-bottom: 5px;">
					<td colspan="2"><hr style="margin-top: 5px;margin-bottom: 5px;margin-left: 20px;margin-right: 20px;color: #9DB7C6;" size="2"></hr></td>
					</tr>
					<tr>
						<td align="right"><font color="red">*</font>基准单位估算价格:</td>
						<td><input  onkeyup="clearNoNum_1(this);"  onblur="formatFloat(this,6,10000000);"  name="entity.estimatePrice"  class="ipt" id="input_estimatePrice" estimatePrice="1" value="${entity.estimatePrice}"></input></td>										
					</tr>
				</tbody>
				
			</table>
		</form>
	</div>
</s:if>
