<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<table class="assmTable" cellpadding="0" cellspacing="0" style="margin-top: 5px;width: 100%;">			
		<thead>
			<tr>
				<th title="商业公司/总部" nowrap="nowrap"  style="background-image: url('');">商业公司/总部</th>
				<th title="当前配置数" nowrap="nowrap"  width="80">当前配置数</th>
				<th title="标准配置数" nowrap="nowrap" width="80">标准配置数</th>
				<th title="操作" nowrap="nowrap" width="50" align="center" style="text-align: center;">操作</th>				
			</tr>
		</thead>
		
		<tbody>
			<s:iterator value="standVos" var="stand" status="st">
				<tr>
					<td style="overflow: hidden;" class="pd-chill-tip" title="${projectName}">
						<div class="partHide">${projectName}</div>
					</td>
					<td  style="overflow: hidden;" class="pd-chill-tip" title="${hasNum}">
						<div class="partHide"  >${hasNum}</div>
					</td>
					<td  style="overflow: hidden;" class="pd-chill-tip" title="${stanNum}">
						<div class="partHide" id="stanNum_${st.index}">${stanNum}</div>
						<input  id="inp_stanNum_${st.index}" value="${stanNum}" style="display: none;width: 55px;padding-left: 5px;"/>
					</td>
					<td  style="overflow: hidden;text-align: center;" class="pd-chill-tip"  align="center">
						<security:authorize ifAnyGranted="A_ASSM_MOD_NEW">
							<div class="partHide">																
								<a onclick="modifyStand('${st.index}',this);" ><font style="color: blue;">编辑</font></a>
								<a onclick="confirmStand('${st.index}',this);" style="display: none;" standId="${assmModelStandardId}"><font style="color: blue;">确认</font></a>									
								<%--<a onclick="deleteStand();" ><font style="color: blue;">删除</font></a> --%>									
							</div>
						</security:authorize>
						<security:authorize ifNotGranted="A_ASSM_MOD_NEW">
													<div style="text-align: center;">-</div>
												</security:authorize>
					</td>											
				</tr>
			</s:iterator>			
		</tbody>
</table>
<s:if test="standVos==null||standVos.size()<1">
	<center>
		<div style="margin-top: 10px;">无记录！</div>
	</center>
</s:if>


