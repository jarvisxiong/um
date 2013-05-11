<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<div class="fakeContainer">
<table class="stat_table" id="MyTable">
	<thead>
		<tr>
			<th width="20px" nowrap="nowrap" >
			<div class="ellipsisDiv_full">&nbsp;</div>
			</th>
			<th width="40px" nowrap="nowrap" >
			<div class="ellipsisDiv_full">序号</div>
			</th>

			<th width="80px" nowrap="nowrap">
			<div class="ellipsisDiv_full">合同分类</div>
			</th>
			<th width="150px" nowrap="nowrap">
				<div class="ellipsisDiv_full">标段/合同名称</div>
			</th>
			<th width="100px" nowrap="nowrap">
			<div class="ellipsisDiv_full">合同性质</div>
			</th>
			<th width="100px">
			<div class="ellipsisDiv_full">合约子目标成本</div>
			</th>
			<th width="120px" nowrap="nowrap">
			<div class="ellipsisDiv_full">地块、业态范围</div>
			</th>
			<th width="120px" nowrap="nowrap">
			<div class="ellipsisDiv_full">单位定义</div>
			</th>
			<th width="100px" nowrap="nowrap">数量</th>
			<th nowrap="nowrap">
			<div class="ellipsisDiv_full">工作内容、边界条件、技术要求</div>
			</th>
			<th width="100px" nowrap="nowrap">
			<div class="ellipsisDiv_full">甲供材料、设备</div>
			</th>
			<th width="100px" nowrap="nowrap">
			<div class="ellipsisDiv_full">招标前置条件</div>
			</th>
			<th width="100px" nowrap="nowrap">
			<div class="ellipsisDiv_full">出图时间</div>
			</th>
			<th width="100px" nowrap="nowrap">
			<div class="ellipsisDiv_full">计划开工时间</div>
			</th>

			<th width="100px" nowrap="nowrap">
			<div class="ellipsisDiv_full">计划竣工时间</div>
			</th>
			<th width="100px;" nowrap="nowrap">
			<div class="ellipsisDiv_full">招标开始时间</div>
			</th>

			<th width="150px;" nowrap="nowrap">
			<div class="ellipsisDiv_full">备注</div>
			</th>
		</tr>
	</thead>
	<tbody>
		<s:if test="costContPlanTplDetailVoList!=null">
			<s:iterator value="costContPlanTplDetailVoList">
				<tr class="itemClass" onmouseover="changeBg(this);" onmouseout="changeBgOut(this);"
					 id="${costContPlanTplDetailId}" subcd="${subjectCd}" rowcd="${rowTypeCd}" onclick="selItem(this);">
	                 <%--选中标记 --%>
					<td  id="td${costContPlanTplDetailId}" contPlanTplId="${costContPlanTplId }" field="sort" >&nbsp;&nbsp;</td>
					<%--序号--%>
					<td class="clswrap" title="${dispOrderDesc }"  cid="${costContPlanTplDetailId}">
						<input class="input1 txtCenter" type="text" value='${dispOrderDesc }' field="dispOrderDesc" oid="${costContPlanTplDetailId}" onchange='save(this)' disabled="disabled"></input>
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
							<script type="text/javascript">
								var subjectSequNo="${subjectSequNo}";
								var html="";
								for(var i=1;i<subjectSequNo;i++){
									html+="&nbsp;&nbsp;";
								}
								document.write(html);
							</script>
							<span>${contName }</span>
						</div>
						<input class="input1 txtLeft icls${costContPlanTplDetailId}" type="text" value='${contName}'  field="contName" oid="${costContPlanTplDetailId}" onchange="save(this)" style="display:none"/>
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
						<input type="text" identity="${subjectCd}_${rowTypeCd}" value='${contSubTargetAmt }' pid="contSubTargetAmt_${subjectCd}" field="contSubTargetAmt" oid="${costContPlanTplDetailId}" onchange="autoCalcContSubTargAmt(this);" ></input>
					</td>
					<%--地块、业态范围本 --%>
					<td class="clswrap" title="${landDesc }"  cid="${costContPlanTplDetailId}">
						<input type="text" class="txtleft"  value='${landDesc }' field="landDesc" oid="${costContPlanTplDetailId}" onchange="save(this)" ></input>
					</td>
					<%--单位定义--%>
					<td class="clswrap" title="${unitDefineDesc }"  cid="${costContPlanTplDetailId}">
						<input type="text" class="txtleft" value='${unitDefineDesc }' field="unitDefineDesc" oid="${costContPlanTplDetailId}" onchange="save(this)" ></input>
					</td>
					<%--数量 --%>
					<td class="clswrap" title="${amount }"  cid="${costContPlanTplDetailId}">
						<input type="text" value='${amount }' field="amount" oid="${costContPlanTplDetailId}" onchange="save(this)" ></input>
					</td>
					<%--工作内容、边界条件、技术要求--%>
					<td class="clswrap" title="${workReqDesc }"  cid="${costContPlanTplDetailId}">
						<div class="ellipsisDiv_full">
							<input type="text" value='${workReqDesc }' field="workReqDesc" oid="${costContPlanTplDetailId}" onchange="save(this)" ></input>
						</div>
					</td>
					<%--甲供材料、设备 --%>
					<td class="clswrap" title="${partMateTypeNames }"  cid="${costContPlanTplDetailId}">
						<input type="hidden" id="partMateTypeNames" field="partMateTypeNames" value="${partMateTypeNames }"/>
						<div class="ellipsisDiv_full">
							<input type="text" field="partMateTypeCds" oid="${costContPlanTplDetailId}" value="${partMateTypeNames }" onclick="selectPartMateType(this,'0');"></input>
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
						<input type="text" class="txtCenter" value='<s:date name="outDrawDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker();" field="outDrawDate" oid="${costContPlanTplDetailId}" onchange="save(this)" ></input>
					</td>
					<%--计划开工时间 --%>
					<td title="${planStartDate }"  cid="${costContPlanTplDetailId}">
						<input type="text" class="txtCenter" value='<s:date name="planStartDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker()" field="planStartDate" oid="${costContPlanTplDetailId}" onchange="save(this)" ></input>
					</td>
	                 <%--计划竣工时间 --%>
					<td title="${planEndDate }"  cid="${costContPlanTplDetailId}">
						<input type="text" class="txtCenter" value='<s:date name="planEndDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker()" field="planEndDate" oid="${costContPlanTplDetailId}" onchange="save(this)" ></input>
					</td>
					<%--招标开始时间 --%>
					<td title="${tendStartDate }"  cid="${costContPlanTplDetailId}">
						<input type="text"  class="txtCenter" value='<s:date name="tendStartDate" format="yyyy-MM-dd"/>' onfocus="WdatePicker()" field="tendStartDate" oid="${costContPlanTplDetailId}" onchange="save(this)" ></input>
					</td>
					<%--备注 --%>
					<td class="clswrap" title="${memoDesc}"  cid="${costContPlanTplDetailId}">
						<div class="ellipsisDiv_full"><input type="text" class="txtLeft" value='${memoDesc }' field="memoDesc" oid="${costContPlanTplDetailId}" onchange="save(this)" ></input></div>
					</td>
				</tr>
			</s:iterator>
		</s:if>
		<s:else>
			<style type="text/css">
				.sFHeader table {
					border-spacing: 0px 0px !important;
					border-collapse: collapse !important;	
					table-layout: fixed !important;
					width: auto !important;
					background-color: #ffffff; /* Here b/c of Opera 9.25 :( */
				}
				
				.sData table {
				    border-collapse: collapse !important;
				    border-spacing: 0 !important;
				    table-layout: fixed !important;
				    text-align: left;
				    width: auto !important;
				}
			</style>
			<tr>
				<td colspan="17" style="text-align: center;">查无合约计划，请联系管理员授权项目权限！</td>
			</tr>
		</s:else>
	</tbody>
</table>
</div>
<s:if test="costContPlanTplDetailVoList!=null">
	<input type="button" value="增加行" class="btnStyle handler" onclick="apppendRow('1')"/>
	<input type="button" value="删除行" class="btnStyle handler" onclick="delRow()"/>
</s:if>

<script type="text/javascript">

 $(function(){

	//解决ymprompt bug. 
	ymPrompt.params={}; 
	
	//TB_showMaskLayer("模版加载中...");
	
	//$('body').mask('模版加载中...');
	
	// 调整框高
	var width = document.body.clientWidth - 20;
	// 冻结效果
	// 2: 行
	// width: 300
	// height: 200
	$('.fakeContainer').css( {
		'width' : width
	});
	var myST = new superTable("MyTable", {
		fixedCols : 4,
		cssSkin : "sOrange",
		onFinish : function() {
		}
	});

	//$('body').unmask();
	
	//TB_removeMaskLayer();
 }); 

</script>