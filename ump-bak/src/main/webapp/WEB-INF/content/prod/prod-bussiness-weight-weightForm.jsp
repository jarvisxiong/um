<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div style="float: left;clear: both;width: 100%;margin-top: 10px;" id="mateWeightForm">
<ul id="errorlist"></ul>
			<form id="newProdWeightForm" action="${ctx}/prod/prod-bussiness-weight!save.action" accept-charset="utf-8" method="post">			
				<table style="width: 100%;">
				<colgroup>
					<col width="30%"/>
					<col width="70%"/>
				</colgroup>						
					<tbody style="margin-top: 15px;">							
						<tr style="margin-top: 15px;margin-bottom: 2px;">							
							<td align="right"><font color="red">*</font>业态:</td>
							<td>
							<s:if test="entity!=null">
								<s:select list="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" id="input_bussiness" name="bussinessCd" listKey="key" listValue="value" cssClass="ipt" value="entity.bussinessCd" disabled="true"/>
								<input type="hidden" name="bussinessCd" value="${entity.bussinessCd}"></input>
							</s:if>
							<s:else>
								<s:select list="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" id="input_bussiness" name="bussinessCd" listKey="key" listValue="value" cssClass="ipt" value="entity.bussinessCd"/>
							</s:else>
															
							</td>
						</tr>
						<tr style="margin-top: 2px;margin-bottom: 2px;">
						<td align="right"><font color="red">*</font>权重:</td>
						<td><input weight="weight" name="entity.weight" id="input_weight" class="ipt" value="${entity.weight }" onkeyup="clearNoNum_1(this);" onblur="formatFloat(this,6,10000000);" ></input></td>
						</tr>
						<tr style="margin-top: 2px;margin-bottom: 2px;">
						<td align="right">描述:</td>
						<td><input weightDesc="weightDesc" name="entity.weightDesc" id="input_weightDesc"  class="ipt" value="${entity.weightDesc }"></input></td>
						</tr>
						<%--
						<tr style="margin-top: 2px;margin-bottom: 2px;">
							<td colspan="2"><input type="button" value="保存" onclick="saveProdWeight();" class="btn_green" style="margin-top: 2px;margin-bottom: 2px;margin-left: 120px;"></input></td>
						</tr>
						 --%>
					 </tbody>
					
				</table>
			</form>
			</div>