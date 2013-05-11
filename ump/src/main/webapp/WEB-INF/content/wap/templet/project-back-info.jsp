<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 项目交底信息 --%>
<%@ include file="template-var.jsp"%>
<div id="billContent">
	<div class="div_row">
			<span class="wap_title">项目名称 :</span>
			<span class="wap_value">${templateBean.projectName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">项目地理位置 :</span>
			<span class="wap_value">${templateBean.projectPosition}</span>
	</div>
	
	<div class="div_label">
		<span class="wap_label">【用地规划要求】</span>
		<div class="div_row">
			<span class="wap_title"> 总用地面积:</span>
			<span class="wap_value">${templateBean.totalArea}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">用地性质:</span>
			<div><s:checkbox name="templateBean.placeNature1" cssClass="group"></s:checkbox><span>居住</span></div>
			<div><s:checkbox name="templateBean.placeNature2" cssClass="group"></s:checkbox><span>商业</span></div>
			<div><s:checkbox name="templateBean.placeNature3" cssClass="group"></s:checkbox><span>办公</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title"> 容积率 	:</span>
			<span class="wap_value">${templateBean.volumeRate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title"> 建筑密度:</span>
			<span class="wap_value">${templateBean.buildingDensity}</span>
		</div>
	</div>
	
	<div class="div_label">
		<span class="wap_label">【建筑规划要求】</span>
		<div class="div_row">
			<span class="wap_title"> 建筑使用性质或功能要求 （住宅、商业、酒店、办公的建筑面积、比例等） :</span>
			<span class="wap_value">${templateBean.otherRequire}</span>
		</div>
		<div class="div_row">
			<span class="wap_title"> 建筑高度:</span>
			<span class="wap_value">${templateBean.buildingHeight}</span>
		</div>
		<div class="div_row">
			<span class="wap_title"> 其他规划限制条件:</span>
			<span class="wap_value">${templateBean.otherCondition}</span>
		</div>
	</div>
	
	<div class="div_label">
		<span class="wap_label">【是否符合公司选址标准】</span>
		<div class="div_row">
			<span class="wap_title">一.	地段要求</span>
			<div><span class="wap_value">1.城市选择：以上海为重心，以长三角、珠三角、渤海和成渝经济区作为重点拓展区域，这些区域的省会城市、地级市及百强县。</span></div>	
			<div><span class="wap_value">2.城市地段选址：城市越小，越往核心区走。</span></div>
			<div><span class="wap_value">a).地级及以上城市(市区常住人口≥50万)：新老城区结合部。</span></div>
			<div><span class="wap_value">b).地级及以上城市(市区常住人口≤50万)：市内新中心。</span></div>
			<div><span class="wap_value">c).百强县：</span></div>
			<div><span class="wap_value">——GDP超过500亿的，选址新老城区结合部 </span></div>
			<div><span class="wap_value">——GDP300-500亿的，选址市内新中心</span></div>
			<div><s:checkbox name="templateBean.siteConf" cssClass="group"></s:checkbox><span>符合</span></div>
			<div><s:checkbox name="templateBean.siteUnconf" cssClass="group"></s:checkbox><span>不符合</span></div>
			<div>说明:${templateBean.siteRemark}</div>
		</div>
		<div class="div_row">
			<span class="wap_title">二.	商业要求：超市可进入</span>
			<div><span class="wap_value">1.人口要求：2公里超过10万人，4公里超过20万人。</span></div>
			<div><span class="wap_value">2.交通要求：最好四面临街，至少两面临街，其中一条为大于双向六车道的主干道。</span></div>
			<div><span class="wap_value">3.周边商业竞争要求：3公里范围内无同等体量商业。(核心区例外)</span></div>
			<div><s:checkbox name="templateBean.busConf" cssClass="group"></s:checkbox><span>符合</span></div>
			<div><s:checkbox name="templateBean.busUnconf" cssClass="group"></s:checkbox><span>不符合</span></div>
			<span class="wap_value">说明:${templateBean.busRemark}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">三. 财务要求:投资回报率(持有物业估值按成本)≥25%</span>
			<div><span class="wap_value">1.地块面积要求：核心商圈100-200亩；非核心区200-400亩。</span></div>
			<div><span class="wap_value">2.商住比：要求小于4:6</span></div>
			<div><span class="wap_value">3.地价要求：楼板价一般控制在20%住宅售价内。（核心区域商业价值高的地块商住比例可提高）</span></div>
		    <div><s:checkbox name="templateBean.finConf" cssClass="group"></s:checkbox><span>符合</span></div>
			<div><s:checkbox name="templateBean.finUnconf" cssClass="group"></s:checkbox><span>不符合</span></div>
			<span class="wap_value">说明:${templateBean.finRemark}</span>
		</div>
	</div>
	
	<div class="div_row">
			<span class="wap_title"> 需注意的有关地块内、周边设施情况和其他情况:</span>
			<span class="wap_value">${templateBean.otherRemark}</span>
	</div>
	
	<div class="div_label">
		<span class="wap_label">【资料附件】</span>
		<div class="div_row">
				<span class="wap_title">城市地图和地块位置标示:</span>
				<div id="show_mapFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.mapFileId}','mapFileId','${canEdit}');
				</script>
		</div>
		<div class="div_row">
				<span class="wap_title">项目用地地形和红线图（比例图或电子文件）:</span>
				<div id="show_landFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.landFileId}','landFileId','${canEdit}');
				</script>
		</div>
		<div class="div_row">
				<span class="wap_title">有关规划文件和资料（列明文件名称）:</span>
				<div id="show_otherInfoFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.otherInfoFileId}','otherInfoFileId','${canEdit}');
				</script>
		</div>
	</div>
	
</div>