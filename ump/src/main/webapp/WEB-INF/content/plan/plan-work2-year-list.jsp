<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.springside.modules.web.struts2.Struts2Utils"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="java.util.Date"%>
<%@page import="com.hhz.core.utils.CollectionHelper"%>
<%@page import="java.util.List"%>
<%@page import="com.hhz.core.utils.RandomUtils"%>
<style>
.weightPoint_input{
	width:16px;
}
.td_queren{
	background-color:#0068a2;
	color:#fff;
	border:1px solid #fff;
}
</style>
<form id="newScheForm" method="post">
	<input type="hidden" name="planYear"/>
	<input type="hidden" name="serialNumber" value="${newSerialNumber}"/>
	<input type="hidden" name="serialOrder" value="${newSerialOrder}"/>
	<input type="hidden" name="centerCd"/>
	<input type="hidden" name="accordingType1"/>
	<input type="hidden" name="accordingType2"/>
	<input type="hidden" name="accordingType3"/>
	<input type="hidden" name="accordingType4"/>
	<input type="hidden" name="workTarget"/>
	<input type="hidden" name="detailStep"/>
	<input type="hidden" name="coordinateCenterCds"/>
	<input type="hidden" name="coordinateCenterNames"/>
	<input type="hidden" name="weightPoint1"/>
	<input type="hidden" name="weightPoint2"/>
	<input type="hidden" name="weightPoint3"/>
	<input type="hidden" name="weightPoint4"/>
	<input type="hidden" name="weightPoint5"/>
	<input type="hidden" name="weightPoint6"/>
	<input type="hidden" name="weightPoint7"/>
	<input type="hidden" name="weightPoint8"/>
	<input type="hidden" name="weightPoint9"/>
	<input type="hidden" name="weightPoint10"/>
	<input type="hidden" name="weightPoint11"/>
	<input type="hidden" name="weightPoint12"/>
	
	<input type="hidden" name="page.pageNo" value="1"/>
	<input type="hidden" name="statusCd" value="0"/>
	<input type="hidden" name="isEditOrg"/>
</form>
<table class="content_table" id="scheTable">
	<thead>
		<tr>
			<th align="left" width="20px;" style="background:none;"></th>
			<th align="left" width="30px;">编号</th>
			<!-- 
			<th align="left" width="100px" nowrap="nowrap">计划依据</th>
			 -->
			<th align="left" nowrap="nowrap">年度重点工作目标</th>
			<th align="left" nowrap="nowrap">具体措施</th>
			<th align="left" width="30px" nowrap="nowrap">1月</th>
			<th align="left" width="30px" nowrap="nowrap">2月</th>
			<th align="left" width="30px" nowrap="nowrap">3月</th>
			<th align="left" width="30px" nowrap="nowrap">4月</th>
			<th align="left" width="30px" nowrap="nowrap">5月</th>
			<th align="left" width="30px" nowrap="nowrap">6月</th>
			<th align="left" width="30px" nowrap="nowrap">7月</th>
			<th align="left" width="30px" nowrap="nowrap">8月</th>
			<th align="left" width="30px" nowrap="nowrap">9月</th>
			<th align="left" width="30px" nowrap="nowrap">10月</th>
			<th align="left" width="30px" nowrap="nowrap">11月</th>
			<th align="left" width="30px" nowrap="nowrap">12月</th>
			<th align="left" width="55px" nowrap="nowrap">操作</th>
		</tr>
	</thead>
	<tbody>
		<tr id="newScheTemplate1" class="detailTr"  style="display:none; height:50px;">
			<td colspan="2" align="right">${newSerialNumber}${newSerialOrder}</td>
			
			<!-- 
			<td align="left">
				<div><input type="checkbox" id="new_accordingType1" value="1">上年计划延伸</div>
				<div><input type="checkbox" id="new_accordingType2" value="1">本年公司要求</div>
				<div><input type="checkbox" id="new_accordingType3" value="1">分管领导要求</div>
				<div><input type="checkbox" id="new_accordingType4" value="1">中心内部要求</div>
			</td>
			-->
			<td align="left"><textarea rows="3" cols="20" id="new_workTarget"></textarea></td>
			<td align="left"><textarea rows="3" cols="20" id="new_detailStep"></textarea></td>
			<td align="left"><input type="checkbox" id="new_weightPoint1" value="1" class="weightPoint_input"></td>
			<td align="left"><input type="checkbox" id="new_weightPoint2" value="1" class="weightPoint_input"></td>
			<td align="left"><input type="checkbox" id="new_weightPoint3" value="1" class="weightPoint_input"></td>
			<td align="left"><input type="checkbox" id="new_weightPoint4" value="1" class="weightPoint_input"></td>
			<td align="left"><input type="checkbox" id="new_weightPoint5" value="1" class="weightPoint_input"></td>
			<td align="left"><input type="checkbox" id="new_weightPoint6" value="1" class="weightPoint_input"></td>
			<td align="left"><input type="checkbox" id="new_weightPoint7" value="1" class="weightPoint_input"></td>
			<td align="left"><input type="checkbox" id="new_weightPoint8" value="1" class="weightPoint_input"></td>
			<td align="left"><input type="checkbox" id="new_weightPoint9" value="1" class="weightPoint_input"></td>
			<td align="left"><input type="checkbox" id="new_weightPoint10" value="1" class="weightPoint_input"></td>
			<td align="left"><input type="checkbox" id="new_weightPoint11" value="1" class="weightPoint_input"></td>
			<td align="left"><input type="checkbox" id="new_weightPoint12" value="1" class="weightPoint_input"></td>
			<td align="left">
				<input style="display:none;" type="text" id="new_coordinateCenterNames" size="20">
			</td>
		</tr>
		<tr id="newScheTemplate2" class="detailTr"  style="display:none; height:60px;">
		    <td colspan="17" valign="middle">
				<div style="float:left; padding-left:12px;" id="monthScheSave">
					<button type="button" class="btn_green_55_20" onclick="addSavePlanWork2Year('${centerCd}');">保存</button>&nbsp;
					<button type="button" class="btn_red_35_20" onclick="cancelSchedule();">取消</button>
				</div>
		    </td>
		</tr>
		<s:if test="page.result.size == 0">
		<tr>
			<td id="noResult_td" colspan="17" align="center" style="height:200px;">没有搜索到的记录</td>
		</tr>
		</s:if>
		<s:iterator value="page.result" var="pagePlanWork2Year">
		<tr id="main_${planWork2YearId}" class="mainTr" style="cursor:pointer;">
			<td id="chk_${planWork2YearId}" nowrap="nowrap" align="center">
<form action="${ctx}/plan/plan-work2-year!save.action" id="scheForm_${planWork2YearId}" method="post">
	<input type="hidden" name="planWork2YearId" value="${planWork2YearId}"/>
	<input type="hidden" name="planYear" value="${planYear}"/>
	<input type="hidden" name="serialNumber" value="${newSerialNumber}"/>
	<input type="hidden" name="serialOrder" value="${newSerialOrder}"/>
	<input type="hidden" name="centerCd" value="${centerCd}"/>
	<input type="hidden" name="accordingType1" value="${accordingType1}"/>
	<input type="hidden" name="accordingType2" value="${accordingType2}"/>
	<input type="hidden" name="accordingType3" value="${accordingType3}"/>
	<input type="hidden" name="accordingType4" value="${accordingType4}"/>
	<input type="hidden" name="workTarget" value="${workTarget}"/>
	<input type="hidden" name="detailStep" value="${detailStep}"/>
	<input type="hidden" name="coordinateCenterNames" value="${coordinateCenterNames}"/>
	<input type="hidden" name="weightPoint1" value="${weightPoint1}"/>
	<input type="hidden" name="weightPoint2" value="${weightPoint2}"/>
	<input type="hidden" name="weightPoint3" value="${weightPoint3}"/>
	<input type="hidden" name="weightPoint4" value="${weightPoint4}"/>
	<input type="hidden" name="weightPoint5" value="${weightPoint5}"/>
	<input type="hidden" name="weightPoint6" value="${weightPoint6}"/>
	<input type="hidden" name="weightPoint7" value="${weightPoint7}"/>
	<input type="hidden" name="weightPoint8" value="${weightPoint8}"/>
	<input type="hidden" name="weightPoint9" value="${weightPoint9}"/>
	<input type="hidden" name="weightPoint10" value="${weightPoint10}"/>
	<input type="hidden" name="weightPoint11" value="${weightPoint11}"/>
	<input type="hidden" name="weightPoint12" value="${weightPoint12}"/>
	<input type="hidden" name="statusCd" value="${statusCd}"/>
	<input type="hidden" name="recordVersion" value ="${recordVersion}"/>
</form>
				<div style="display:inline;"><input type="checkbox" id="chk_all" value="${planWork2YearId}" onClick="CAN_scheClick=false;" recordVersion="${recordVersion}"></input></div>
			</td>
			<td id="td_serialOrder_${planWork2YearId}" nowrap="nowrap" onclick="toggleDetail(this,'${planWork2YearId}');">
				<div id="serialOrder_show_${planWork2YearId}" class="span_show">${serialOrder}</div>
				<div id="serialOrder_hide_${planWork2YearId}" class="span_hide" style="display:none;">
					<input type='text' size="4" id="serialOrder_input_${planWork2YearId}" onblur="savePlanWork2Year('${planWork2YearId}','serialOrder',$(this).val());" value="${serialOrder}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;"/>
				</div>
			</td>
			<!-- 
			<td id="td_accordingType_${planWork2YearId}" onclick="toggleDetail(this,'${planWork2YearId}');">
				<div id="accordingType_show_${planWork2YearId}" class="span_show">
					<s:if test="accordingType1==1"><div>上年计划延伸</div></s:if>
					<s:if test="accordingType2==1"><div>本年公司要求</div></s:if>
					<s:if test="accordingType3==1"><div>分管领导要求</div></s:if>
					<s:if test="accordingType4==1"><div>中心内部要求</div></s:if>
				</div>
				<div id="accordingType_hide_${planWork2YearId}" class="span_hide" style="display:none;">
					<div><input type="checkbox" id="accordingType1_input_${planWork2YearId}" value="1" <s:if test="accordingType1==1">checked</s:if> id="accordingType1_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="savePlanWork2Year('${planWork2YearId}','accordingType1',$(this).val());">上年计划延伸</div>
					<div><input type="checkbox" id="accordingType2_input_${planWork2YearId}" value="1" <s:if test="accordingType2==1">checked</s:if> id="accordingType2_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="savePlanWork2Year('${planWork2YearId}','accordingType2',$(this).val());">本年公司要求</div>
					<div><input type="checkbox" id="accordingType3_input_${planWork2YearId}" value="1" <s:if test="accordingType3==1">checked</s:if> id="accordingType3_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="savePlanWork2Year('${planWork2YearId}','accordingType3',$(this).val());">分管领导要求</div>
					<div><input type="checkbox" id="accordingType4_input_${planWork2YearId}" value="1" <s:if test="accordingType4==1">checked</s:if> id="accordingType4_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="savePlanWork2Year('${planWork2YearId}','accordingType4',$(this).val());">中心内部要求</div>
				</div>
			</td>
			 -->
			<td id="td_workTarget_${planWork2YearId}" onclick="toggleDetail(this,'${planWork2YearId}');">
				<div id="workTarget_show_${planWork2YearId}" class="span_show ellipsisDiv_full" title="${workTarget}">${workTarget}</div>
				<div id="workTarget_hide_${planWork2YearId}" class="span_hide" style="display:none;">
					<textarea rows="3" cols="20" id="workTarget_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onblur="savePlanWork2Year('${planWork2YearId}','workTarget',$(this).val());">${workTarget}</textarea>
				</div>
			</td>
			<td id="td_detailStep_${planWork2YearId}" onclick="toggleDetail(this,'${planWork2YearId}');">
				<div id="detailStep_show_${planWork2YearId}" class="span_show ellipsisDiv_full" title="${detailStep}">${detailStep}</div>
				<div id="detailStep_hide_${planWork2YearId}" class="span_hide" style="display:none;">
					<textarea rows="3" cols="20" id="detailStep_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onblur="savePlanWork2Year('${planWork2YearId}','detailStep',$(this).val());">${detailStep}</textarea>
				</div>
			</td>
			<td id="td_weightPoint1_${planWork2YearId}" onclick="toggleDetail(this,'${planWork2YearId}');" title="1月" <s:if test="weightPoint1>0">class="td_queren"</s:if>>
				<div id="weightPoint1_show_${planWork2YearId}" class="span_show"><s:if test="weightPoint1>0">1月</s:if></div>
				<div id="weightPoint1_hide_${planWork2YearId}" class="span_hide" style="display:none;">
					<input type="checkbox" value="1" <s:if test="weightPoint1>0"> checked</s:if> class="weightPoint_input" id="weightPoint1_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="savePlanWork2Year('${planWork2YearId}','weightPoint1',$(this).val());"/>
				</div>
			</td>
			<td id="td_weightPoint2_${planWork2YearId}" onclick="toggleDetail(this,'${planWork2YearId}');" title="2月" <s:if test="weightPoint2>0">class="td_queren"</s:if>>
				<div id="weightPoint2_show_${planWork2YearId}" class="span_show"><s:if test="weightPoint2>0">2月</s:if></div>
				<div id="weightPoint2_hide_${planWork2YearId}" class="span_hide" style="display:none;">
					<input type="checkbox" value="1" <s:if test="weightPoint2>0"> checked</s:if> class="weightPoint_input" id="weightPoint2_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="savePlanWork2Year('${planWork2YearId}','weightPoint2',$(this).val());"/>
				</div>
			</td>
			<td id="td_weightPoint3_${planWork2YearId}" onclick="toggleDetail(this,'${planWork2YearId}');" title="3月" <s:if test="weightPoint3>0">class="td_queren"</s:if>>
				<div id="weightPoint3_show_${planWork2YearId}" class="span_show"><s:if test="weightPoint3>0">3月</s:if></div>
				<div id="weightPoint3_hide_${planWork2YearId}" class="span_hide" style="display:none;">
					<input type="checkbox" value="1" <s:if test="weightPoint3>0"> checked</s:if> class="weightPoint_input" id="weightPoint3_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="savePlanWork2Year('${planWork2YearId}','weightPoint3',$(this).val());"/>
				</div>
			</td>
			<td id="td_weightPoint4_${planWork2YearId}" onclick="toggleDetail(this,'${planWork2YearId}');" title="4月" <s:if test="weightPoint4>0">class="td_queren"</s:if>>
				<div id="weightPoint4_show_${planWork2YearId}" class="span_show"><s:if test="weightPoint4>0">4月</s:if></div>
				<div id="weightPoint4_hide_${planWork2YearId}" class="span_hide" style="display:none;">
					<input type="checkbox" value="1" <s:if test="weightPoint4>0"> checked</s:if> class="weightPoint_input" id="weightPoint4_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="savePlanWork2Year('${planWork2YearId}','weightPoint4',$(this).val());"/>
				</div>
			</td>
			<td id="td_weightPoint5_${planWork2YearId}" onclick="toggleDetail(this,'${planWork2YearId}');" title="5月" <s:if test="weightPoint5>0">class="td_queren"</s:if>>
				<div id="weightPoint5_show_${planWork2YearId}" class="span_show"><s:if test="weightPoint5>0">5月</s:if></div>
				<div id="weightPoint5_hide_${planWork2YearId}" class="span_hide" style="display:none;">
					<input type="checkbox" value="1" <s:if test="weightPoint5>0"> checked</s:if> class="weightPoint_input" id="weightPoint5_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="savePlanWork2Year('${planWork2YearId}','weightPoint5',$(this).val());"/>
				</div>
			</td>
			<td id="td_weightPoint6_${planWork2YearId}" onclick="toggleDetail(this,'${planWork2YearId}');" title="6月" <s:if test="weightPoint6>0">class="td_queren"</s:if>>
				<div id="weightPoint6_show_${planWork2YearId}" class="span_show"><s:if test="weightPoint6>0">6月</s:if></div>
				<div id="weightPoint6_hide_${planWork2YearId}" class="span_hide" style="display:none;">
					<input type="checkbox" value="1" <s:if test="weightPoint6>0"> checked</s:if> class="weightPoint_input" id="weightPoint6_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="savePlanWork2Year('${planWork2YearId}','weightPoint6',$(this).val());"/>
				</div>
			</td>
			<td id="td_weightPoint7_${planWork2YearId}" onclick="toggleDetail(this,'${planWork2YearId}');" title="7月" <s:if test="weightPoint7>0">class="td_queren"</s:if>>
				<div id="weightPoint7_show_${planWork2YearId}" class="span_show"><s:if test="weightPoint7>0">7月</s:if></div>
				<div id="weightPoint7_hide_${planWork2YearId}" class="span_hide" style="display:none;">
					<input type="checkbox" value="1" <s:if test="weightPoint7>0"> checked</s:if> class="weightPoint_input" id="weightPoint7_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="savePlanWork2Year('${planWork2YearId}','weightPoint7',$(this).val());"/>
				</div>
			</td>
			<td id="td_weightPoint8_${planWork2YearId}" onclick="toggleDetail(this,'${planWork2YearId}');" title="8月" <s:if test="weightPoint8>0">class="td_queren"</s:if>>
				<div id="weightPoint8_show_${planWork2YearId}" class="span_show"><s:if test="weightPoint8>0">8月</s:if></div>
				<div id="weightPoint8_hide_${planWork2YearId}" class="span_hide" style="display:none;">
					<input type="checkbox" value="1" <s:if test="weightPoint8>0"> checked</s:if> class="weightPoint_input" id="weightPoint8_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="savePlanWork2Year('${planWork2YearId}','weightPoint8',$(this).val());"/>
				</div>
			</td>
			<td id="td_weightPoint9_${planWork2YearId}" onclick="toggleDetail(this,'${planWork2YearId}');" title="9月" <s:if test="weightPoint9>0">class="td_queren"</s:if>>
				<div id="weightPoint9_show_${planWork2YearId}" class="span_show"><s:if test="weightPoint9>0">9月</s:if></div>
				<div id="weightPoint9_hide_${planWork2YearId}" class="span_hide" style="display:none;">
					<input type="checkbox" value="1" <s:if test="weightPoint9>0"> checked</s:if> class="weightPoint_input" id="weightPoint9_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="savePlanWork2Year('${planWork2YearId}','weightPoint9',$(this).val());"/>
				</div>
			</td>
			<td id="td_weightPoint10_${planWork2YearId}" onclick="toggleDetail(this,'${planWork2YearId}');" title="10月" <s:if test="weightPoint10>0">class="td_queren"</s:if>>
				<div id="weightPoint10_show_${planWork2YearId}" class="span_show"><s:if test="weightPoint10>0">10月</s:if></div>
				<div id="weightPoint10_hide_${planWork2YearId}" class="span_hide" style="display:none;">
					<input type="checkbox" value="1" <s:if test="weightPoint10>0"> checked</s:if> class="weightPoint_input" id="weightPoint10_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="savePlanWork2Year('${planWork2YearId}','weightPoint10',$(this).val());"/>
				</div>
			</td>
			<td id="td_weightPoint11_${planWork2YearId}" onclick="toggleDetail(this,'${planWork2YearId}');" title="11月" <s:if test="weightPoint11>0">class="td_queren"</s:if>>
				<div id="weightPoint11_show_${planWork2YearId}" class="span_show"><s:if test="weightPoint11>0">11月</s:if></div>
				<div id="weightPoint11_hide_${planWork2YearId}" class="span_hide" style="display:none;">
					<input type="checkbox" value="1" <s:if test="weightPoint11>0"> checked</s:if> class="weightPoint_input" id="weightPoint11_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="savePlanWork2Year('${planWork2YearId}','weightPoint11',$(this).val());"/>
				</div>
			</td>
			<td id="td_weightPoint12_${planWork2YearId}" onclick="toggleDetail(this,'${planWork2YearId}');" title="12月" <s:if test="weightPoint12>0">class="td_queren"</s:if>>
				<div id="weightPoint12_show_${planWork2YearId}" class="span_show"><s:if test="weightPoint12>0">12月</s:if></div>
				<div id="weightPoint12_hide_${planWork2YearId}" class="span_hide" style="display:none;">
					<input type="checkbox" value="1" <s:if test="weightPoint12>0"> checked</s:if> class="weightPoint_input" id="weightPoint12_input_${planWork2YearId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onChange="savePlanWork2Year('${planWork2YearId}','weightPoint12',$(this).val());"/>
				</div>
			</td>
			<td>
				<security:authorize ifAnyGranted="A_PLAN_WORK2_YEAR">
					<s:if test="statusCd!=5">
						<button class="save5Btn button_red" type="button" onclick="updateStatusCd('${planWork2YearId}',5);">删除</button>
					</s:if>
					<s:else>
						<button class="save5Btn button_green" type="button" onclick="updateStatusCd('${planWork2YearId}',0);">还原</button>
					</s:else>
				</security:authorize>
			</td>
		</tr>
		</s:iterator>
	</tbody>
</table>
<table cellpadding="0" cellspacing="0" border="0" width="100%" style="display:none;">
	<tr>
		<td align="center" id="td_page_source"><p:page/></td>
	</tr>
</table>
<script type="text/javascript">
//if(1==$("#if_search_all").val()){
	$("#td_page").html($("#td_page_source").html());
	$("#td_page_source").html("");
//}

	<security:authorize ifAnyGranted="A_PLAN_WORK2_YEARV">
	isEditOrg = false;
	</security:authorize>
	<security:authorize ifAnyGranted="A_PLAN_WORK2_YEAR">
	isEditOrg = true;
	</security:authorize>
</script>
