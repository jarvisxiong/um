<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<textarea id="addGridView">
	<s:iterator value="costContPlanTplDetailVoList">
		<tr class="itemClass" onmouseover="changeBg(this);" onmouseout="changeBgOut(this);"
			 id="${costContPlanTplDetailId}" subcd="${subjectCd}" rowcd="${rowTypeCd}"	onclick="selItem(this);">
		              <%--选中标记 --%>
			<td  id="td${costContPlanTplDetailId}" contPlanTplId="${costContPlanTplId }" field="sort">&nbsp;&nbsp;</td>
			<%--序号--%>
			<td class="clswrap" title="${dispOrderDesc }"  cid="${costContPlanTplDetailId}">
				<input class="input1 txtCenter" type="text" value='${dispOrderDesc }' value='${dispOrderDesc }' field="dispOrderDesc" oid="${costContPlanTplDetailId}" onchange='save(this)'></input>
			</td>
			<%--合同分类--%>
			<td class="clswrap" title="${contTypeName }"  cid="${costContPlanTplDetailId}">
				<div class="ellipsisDiv_full txtLeft cls${costContPlanTplDetailId} " optype="disText" oid="${costContPlanTplDetailId}">
					<c:forEach	items="${costContTypeCdMap}" var="myMap">
						<c:if test="${contTypeCd==myMap.key}">${myMap.value }</c:if>
					</c:forEach>
				</div>
				<select style="display: none;" field="contTypeCd" oid="${costContPlanTplDetailId}" class="icls${costContPlanTplDetailId}" onchange="save(this)">
					<option value="">请选择</option>
					<c:forEach	items="${costContTypeCdMap}" var="myMap">
						<option value="${myMap.key }" <c:if test="${contTypeCd==myMap.key}">selected="selected"</c:if>>${myMap.value }</option>
					</c:forEach>
				</select>
			</td>
			<%--合同名称--%>
			<td class="clswrap" title="${contName }"  cid="${costContPlanTplDetailId}">
				<div class="ellipsisDiv_full txtLeft cls${costContPlanTplDetailId}"  optype="disText" oid="${costContPlanTplDetailId}">
					
					<span>${contName }</span>
				</div>
				<input class="input1 txtLeft icls${costContPlanTplDetailId}" type="text" value='${contName}'  oid="i${ costContPlanTplDetailId}" onchange="save(this)" style="display:none"/>
			</td>
			<%--合同性质--%>
			<td class="clswrap" title="${contCharTypeName }"  cid="${costContPlanTplDetailId}">
				<div class="ellipsisDiv_full txtLeft cls${costContPlanTplDetailId} " optype="disText" oid="${costContPlanTplDetailId}">
					<c:forEach	items="${costContCharTypeCdMap}" var="myMap">
						<c:if test="${contCharTypeCd==myMap.key}">${myMap.value }</c:if>
					</c:forEach>
				</div>
				<select style="display: none;" field="contCharTypeCd" oid="${costContPlanTplDetailId}" class="icls${costContPlanTplDetailId}" onchange="save(this)">
					<option value="">请选择</option>
					<c:forEach items="${costContCharTypeCdMap}" var="myMap">
						<option value="${myMap.key }" <c:if test="${contCharTypeCd==myMap.key}">selected="selected"</c:if>>${myMap.value }</option>
					</c:forEach>
				</select>
			</td>
			<%--合约子目标成本 --%>
			<td class="clswrap" title="${contSubTargetAmt }"  cid="${costContPlanTplDetailId}">
				<input type="text" identity="${subjectCd}_${rowTypeCd}" pid="contSubTargetAmt_${subjectCd}" field="contSubTargetAmt" oid="${costContPlanTplDetailId}" value='${contSubTargetAmt }' onchange="autoCalcContSubTargAmt(this);"></input>
			</td>
			<%--地块、业态范围本 --%>
			<td class="clswrap" title="${landDesc }"  cid="${costContPlanTplDetailId}">
				<input type="text" class="txtleft"  value='${landDesc }' field="landDesc" oid="${costContPlanTplDetailId}" onchange="save(this)"></input>
			</td>
			<%--单位定义--%>
			<td class="clswrap" title="${unitDefineDesc }"  cid="${costContPlanTplDetailId}">
				<input type="text" class="txtleft" value='${unitDefineDesc }' field="unitDefineDesc" oid="${costContPlanTplDetailId}" onchange="save(this)"></input>
			</td>
			<%--数量 --%>
			<td class="clswrap" title="${amount }"  cid="${costContPlanTplDetailId}">
				<input type="text" value='${amount }' field="amount" oid="${costContPlanTplDetailId}" onchange="save(this)"></input>
			</td>
			<%--工作内容、边界条件、技术要求--%>
			<td class="clswrap" title="${workReqDesc }"  cid="${costContPlanTplDetailId}">
				<div class="ellipsisDiv_full">
					<input type="text" value='${workReqDesc }' field="workReqDesc" oid="${costContPlanTplDetailId}" onchange="save(this)"></input>
				</div>
			</td>
			<%--甲供材料、设备 --%>
			<td class="clswrap" title="${partMateTypeNames }"  cid="${costContPlanTplDetailId}">
				<input type="hidden" id="partMateTypeNames" field="partMateTypeNames" value="${partMateTypeNames }"/>
				<div class="ellipsisDiv_full">
					<input type="text" field="partMateTypeCds" oid="${costContPlanTplDetailId}" value="${partMateTypeNames }" onclick="selectPartMateType(this,'0');" ></input>
				</div>
			</td>
			<%--招标前置条件 --%>
			<td class="clswrap" title="${inviPreCondNames }"  cid="${costContPlanTplDetailId}">
				<div class="ellipsisDiv_full txtLeft cls${costContPlanTplDetailId} " optype="disText" oid="${costContPlanTplDetailId}">
					<c:forEach	items="${costInviPreCondCdsMap}" var="myMap">
						<c:if test="${inviPreCondCds==myMap.key}">${myMap.value }</c:if>
					</c:forEach>
				</div>
				<select style="display: none;" field="inviPreCondCds" oid="${costContPlanTplDetailId}" class="icls${costContPlanTplDetailId}" onchange="save(this)">
					<option value="">请选择</option>
					<c:forEach items="${costInviPreCondCdsMap}" var="myMap">
						<option value="${myMap.key }" <c:if test="${inviPreCondCds==myMap.key}">selected="selected"</c:if>>${myMap.value }</option>
					</c:forEach>
				</select>
			</td>
			<%--出图时间--%>
			<td title="${outDrawDate }"  cid="${costContPlanTplDetailId}">
				<input type="text" class="txtCenter" value='<s:date name="outDrawDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker();" field="outDrawDate" oid="${costContPlanTplDetailId}" onchange="save(this)"></input>
			</td>
			<%--计划开工时间 --%>
			<td title="${planStartDate }"  cid="${costContPlanTplDetailId}">
				<input type="text" class="txtCenter" value='<s:date name="planStartDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker()" field="planStartDate" oid="${costContPlanTplDetailId}" onchange="save(this)"></input>
			</td>
		              <%--计划竣工时间 --%>
			<td title="${planEndDate }"  cid="${costContPlanTplDetailId}">
				<input type="text" class="txtCenter" value='<s:date name="planEndDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker()" field="planEndDate" oid="${costContPlanTplDetailId}" onchange="save(this)"></input>
			</td>
			<%--招标开始时间 --%>
			<td title="${tendStartDate }"  cid="${costContPlanTplDetailId}">
				<input type="text"  class="txtCenter" value='<s:date name="tendStartDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker()" field="tendStartDate" oid="${costContPlanTplDetailId}" onchange="save(this)"></input>
			</td>
			<%--备注 --%>
			<td class="clswrap" title="${memoDesc }"  cid="${costContPlanTplDetailId}">
				<div class="ellipsisDiv_full"><input type="text" class="txtLeft" value='${memoDesc }' field="memoDesc" oid="${costContPlanTplDetailId}" onchange="save(this)"></input></div>
			</td>
		</tr>
	</s:iterator>

</textarea>

