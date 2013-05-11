<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 项目交底信息 --%>
<%@ include file="template-header.jsp"%>

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
		<table  class="mainTable">
			<col width="90"/>
			<col width="110"/>
			<col />
			<col width="60"/>
			<col/>
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="4">
				<input class="inputBorder" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">项目地理位置</td>
				<td colspan="4">
				<input class="inputBorder" validate="required" type="text" name="templateBean.projectPosition" value="${templateBean.projectPosition}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3">用地规划要求</td>
				<td class="td_title">总用地面积</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.totalArea" value="${templateBean.totalArea}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">用地性质</td>
				<td class="chkGroup" align="left" validate="required" colspan="3">
				<table class="tb_checkbox">
				<col width="100">
				<col width="100">
				<col width="100">
				<tr>
				<td><s:checkbox name="templateBean.placeNature1" cssClass="group"></s:checkbox>居住</td>
				<td><s:checkbox name="templateBean.placeNature2" cssClass="group"></s:checkbox>商业</td>
				<td><s:checkbox name="templateBean.placeNature3" cssClass="group"></s:checkbox>办公</td>
				</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">容积率</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.volumeRate" value="${templateBean.volumeRate}"/>
				</td>
				<td class="td_title">建筑密度</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.buildingDensity" value="${templateBean.buildingDensity}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3">建筑规划要求</td>
				<td class="td_title">建筑使用性质或功能要求
				（住宅、商业、酒店、办公的建筑面积、比例等）
				</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.otherRequire">${templateBean.otherRequire}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">建筑高度
				</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.buildingHeight">${templateBean.buildingHeight}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">其他规划限制条件
				</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.otherCondition">${templateBean.otherCondition}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3">是否符合公司选址标准</td>
				<td colspan="2" align="left">
					<strong>一.	地段要求</strong>
					<ul class="ul_none">
					<li>1.	城市选择：以上海为重心，以长三角、珠三角、渤海和成渝经济区作为重点拓展区域，这些区域的省会城市、地级市及百强县。</li>
					<li>2.	城市地段选址：城市越小，越往核心区走。
					<ul>
					<li>a)	地级及以上城市（市区常住人口≥50万）：新老城区结合部。</li>
					<li>b)	地级及以上城市（市区常住人口≤50万）：市内新中心。</li>
					<li>c)	百强县：
					<ul>
					<li>——GDP超过500亿的，选址新老城区结合部 </li>
					<li>——GDP300-500亿的，选址市内新中心</li>
					</ul>
					</li>
					</ul>
					</li>
					</ul>
				</td>
				<td colspan="2">
					<table validate="required" class="tb_checkbox chkGroup">
					<col width="80"/>
					<col width="80"/>
					<tr>
					<td><s:checkbox name="templateBean.siteConf" cssClass="group"></s:checkbox>符合</td>
					<td><s:checkbox name="templateBean.siteUnconf" cssClass="group"></s:checkbox>不符合</td>
					</tr>
					</table>
					<table class="tb_checkbox">
					<tr>
					<td class="td_title">说明</td>
					<td>
					<input class="inputBorder" type="text" name="templateBean.siteRemark" value="${templateBean.siteRemark}"/>
					</td>
					</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="left">
					<strong>二.	商业要求：超市可进入</strong>
					<ul class="ul_none">
					<li>1.	人口要求：2公里超过10万人，4公里超过20万人。</li>
					<li>2.	交通要求：最好四面临街，至少两面临街，其中一条为大于双向六车道的主干道。</li>
					<li>3.	周边商业竞争要求：3公里范围内无同等体量商业。（核心区例外）</li>
					</ul>
				</td>
				<td colspan="2">
					<table validate="required" class="tb_checkbox chkGroup">
					<col width="80"/>
					<col width="80"/>
					<tr>
					<td><s:checkbox name="templateBean.busConf" cssClass="group"></s:checkbox>符合</td>
					<td><s:checkbox name="templateBean.busUnconf" cssClass="group"></s:checkbox>不符合</td>
					</tr>
					</table>
					<table class="tb_checkbox">
					<tr>
					<td class="td_title">说明</td>
					<td>
					<input class="inputBorder" type="text" name="templateBean.busRemark" value="${templateBean.busRemark}"/>
					</td>
					</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="left">
					<strong>三.财务要求：	投资回报率（持有物业估值按成本）≥25%</strong>
					<ul class="ul_none">
					<li>1.	地块面积要求：核心商圈100-200亩；非核心区200-400亩。</li>
					<li>2.	商住比：要求小于4:6</li>
					<li>3.	地价要求：楼板价一般控制在20%住宅售价内。（核心区域商业价值高的地块商住比例可提高）</li>
					</ul>
				</td>
				<td colspan="2">
					<table validate="required" class="tb_checkbox chkGroup">
					<col width="80"/>
					<col width="80"/>
					<tr>
					<td><s:checkbox name="templateBean.finConf" cssClass="group"></s:checkbox>符合</td>
					<td><s:checkbox name="templateBean.finUnconf" cssClass="group"></s:checkbox>不符合</td>
					</tr>
					</table>
					<table class="tb_checkbox">
					<tr>
					<td class="td_title">说明</td>
					<td>
					<input class="inputBorder" type="text" name="templateBean.finRemark" value="${templateBean.finRemark}"/>
					</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
			<td class="td_title">需注意的有关地块内、周边设施情况和其他情况</td>
			<td colspan="4">
			<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.otherRemark">${templateBean.otherRemark}</textarea>
			</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3">资料上传<br/>(请作为附件上传)</td>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.mapFileId}">城市地图和地块位置标示</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('城市地图和地块位置标示','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','mapFileId',event);"/>
				<input type="hidden" name="templateBean.mapFileId" id="mapFileId" value="${templateBean.mapFileId}"/>
				</s:if>
				</td>
				<td colspan="2">
				<div id="show_mapFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.mapFileId}','mapFileId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.landFileId}">项目用地地形和红线图（比例图或电子文件）</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('项目用地地形和红线图（比例图或电子文件）','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','landFileId',event);"/>
				<input type="hidden" name="templateBean.landFileId" id="landFileId" value="${templateBean.landFileId}"/>
				</s:if>
				</td>
				<td colspan="2">
				<div id="show_landFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.landFileId}','landFileId','${canEdit}');
				</script>
				</td>
			</tr> 
			<tr>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.otherInfoFileId}">有关规划文件和资料（列明文件名称）</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('有关规划文件和资料（列明文件名称）','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','otherInfoFileId',event);"/>
				<input type="hidden" name="templateBean.otherInfoFileId" id="otherInfoFileId" value="${templateBean.otherInfoFileId}"/>
				</s:if>
				</td>
				<td colspan="2">
				<div id="show_otherInfoFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.otherInfoFileId}','otherInfoFileId','${canEdit}');
				</script>
				</td>
			</tr>
			
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>

