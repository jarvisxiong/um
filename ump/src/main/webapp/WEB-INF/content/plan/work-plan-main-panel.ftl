<table class="work_plan_main" cellpadding="0" border="0">
	<tr id="banner" class="banner">
		<td style="border-bottom: 1px solid #8c8f94;"><div class="title">计划管理</div></td>
		<td style="border-bottom: 1px solid #8c8f94;">
			<div class="funcs">
				<div style="float:right;" class="func_double_plan_list" onclick="gotoDoubleList()">月计划总表</div>
				<div style="float:right;" class="split_vertial">&nbsp;</div> 
				<div style="float:right;" onclick="gotoExecList()" class="func_exec_plan_list">执行计划总表</div> 
				<div style="float:right;" class="split_vertial">&nbsp;</div>
			</div>
		</td>
	</tr>
	<tr>
		<td style="border-right: 1px solid #8c8f94;border-bottom: 1px solid #8c8f94;background-color:#e4e7ec;">
			<div class="branch_center_title">集团各中心</div>
		</td>
		<td style="border-bottom: 1px solid #8c8f94;background-color:#e4e7ec;">
			<div class="org_title">各地产公司</div>
		</td>
	</tr>
	<tr>
		<td valign="top"  style="border-right: 1px solid #8c8f94;">
			<div id="branchCenterOrgList" class="left_bottom_content" onmouseout="hiddenPopDiv();">
				<#list branchList as branchOrg>
					<#assign childrenCount = 0>
					<#if (branchOrg.orgTypeCd == '2')>
					<div class="center_item_branch" id="branch_${branchOrg.orgCd}" onmouseover="showPopDiv(this,'${branchOrg.uaapOrgId}','${branchOrg.orgCd}','${branchOrg.orgName}',0,100)" >${branchOrg.orgName}</div>
					</#if>
					<#if (branchOrg.orgTypeCd == '3')>
					<div class="branch_item" id="branch_${branchOrg.orgCd}">${branchOrg.orgName}</div>
					</#if>
					<#list centerList as centerOrg>
					<#if (centerOrg.parentOrgCd == branchOrg.orgCd) >
					<#assign childrenCount = childrenCount+1>
						<div id="${centerOrg.uaapOrgId}" 
							 class="center_item" 
							 onmouseover="showPopDiv(this,'${centerOrg.uaapOrgId}','${centerOrg.orgCd}','${centerOrg.orgName}',0,100)"
							 onclick="showPopDiv(this,'${centerOrg.uaapOrgId}','${centerOrg.orgCd}','${centerOrg.orgName}',0,100)"  
						 >${centerOrg.orgName}</div>
						 <!-- onclick="gotoOrgExec('${centerOrg.orgCd}')" -->
					</#if>
					</#list>
					<#if (childrenCount == 0)>
						<script language="javascript">
							//若一级菜单下没有中心,则自身支持鼠标经过显示浮动菜单.
							$('#branch_${branchOrg.orgCd}').mouseover(function(){
								$(this).css({cursor:"pointer"});
								$(this).removeClass().addClass("center_item_branch");
								showPopDiv(this,'${branchOrg.uaapOrgId}','${branchOrg.orgCd}','${branchOrg.orgName}',0,100);
							});
							$('#branch_${branchOrg.orgCd}').addClass('branch_drop_right');
						</script> 
					<#else>
						<script language="javascript">
							//若有中心,则鼠标经过不显浮动菜单.
							//$('#branch_${branchOrg.orgCd}').mouseover(function(){
							//	hiddenPopDiv();
							//});
							$('#branch_${branchOrg.orgCd}').addClass('branch_drop_down');
						</script> 
					</#if>
				</#list>
			</div>
		</td>
		<td valign="top">
			<div id="estateOrgList"  class="right_bottom_content">
				<#list estateOrgList as estateOrg>
					<div class="esteate_item"><div id="esteate_item_${estateOrg.orgCd}" onclick="gotoRelExecPlanEstateOrg('${estateOrg.orgCd}')">${estateOrg.orgName}</div></div>
				</#list>
			</div>
		</td>
	</tr>
</table>  



