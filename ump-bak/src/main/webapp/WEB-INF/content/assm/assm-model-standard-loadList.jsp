<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:set var="height">
${fn:length(standVos)-2}
</c:set>
<div style="margin-top: 5px;">
	<div style="float: left;width: 190px;overflow: scroll;height: ${height*30+155}px;margin-left: 5px;">
		<div id="leftTreePanel2" class="leftPanel"
			style="padding-top: 5px; float: left; overflow-y: auto; overflow-x: hidden; width: 145px; border: none;max-width: 300px;">							
		</div>
	</div>
	<div  style="width: 425px;display: block;float: right;">
		<table style="width: 100%;">
			<tr>
				<td>
					<div class="res_tip" style="margin: 10px 10px;margin-right: 5px;" >
						<span style="margin-left: 10px;">当前设备类型：</span>
						<span id="pop_cur_model" style="font-weight: bold;font-size: 12px;">
							<s:if test="assmModel!=null">
								${assmModel.assmName}
							</s:if>
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="popStandTable" style="margin-left: 10px;">
						<table class="assmTable" cellpadding="0" cellspacing="0" style="margin-top: 5px;width: 100%;">			
								<thead>
									<tr>
										<th title="商业公司/总部" nowrap="nowrap"  style="background-image: url('');">商业公司/总部</th>
										<th title="当前配置数" nowrap="nowrap"  width="80">当前配置数</th>
										<th title="标准配置数" nowrap="nowrap" width="80">标准配置数</th>
										<th title="操作" nowrap="nowrap" width="50" align="center"  style="text-align: center;">操作</th>				
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
												<input type="hidden" id="inp_hasNum_${st.index}" value="${hasNum}"/>
											</td>
											<td  style="overflow: hidden;" class="pd-chill-tip" title="${stanNum}">
												<div class="partHide" id="stanNum_${st.index}">${stanNum}</div>
												<input id="inp_stanNum_${st.index}" value="${stanNum}" onblur="getValue(this);" onkeyup="clearNoNum_2(this);"  maxlength="8" style="display: none;width: 55px;padding-left: 5px;"/>
											</td>
											<td  style="overflow: hidden;text-align: center;" class="pd-chill-tip" align="center">
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
					</div>
				</td>
			</tr>
		</table>		
	</div>
</div>

<script type="text/javascript">
$(function(){
	loadAssmTree2('${assmModel.pratentId}','${assmModel.assmModelId}');	
});

function modifyStand(line,dom){
	$("#stanNum_"+line).hide();
	$("#inp_stanNum_"+line).show();
	$(dom).hide();
	$(dom).next().show();
}
//确认修改
function confirmStand(line,dom){
	$("#stanNum_"+line).html($("#inp_stanNum_"+line).val());
	$("#stanNum_"+line).show();
	$("#inp_stanNum_"+line).hide();
	$(dom).hide();
	$(dom).prev().show();
	var assmModelStandardId=$(dom).attr('standId');
	var hasNum = $("#inp_hasNum_"+line).val();
	var stanNum = $("#inp_stanNum_"+line).val();
	var tips = "";
	if(parseInt(hasNum) > parseInt(stanNum)){
		tips = '标准配置数小于当前配置数，确认修改?';
	}else{
		tips = '确认修改?';
	}
	if(window.confirm(tips)){		
		var upurl= _ctx+"/assm/assm-model-standard!save.action";
		var data={assmModelStandardId:assmModelStandardId};
		data['entity.stanNum']=$("#inp_stanNum_"+line).val();
		$.post(upurl,data,function(result){
		});
	}
}
function getValue(dom){
	if(dom.value==''){
		dom.value='0';
	}
}
</script>

	

