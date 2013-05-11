<%@ page contentType="text/html;charset=UTF-8" %>


<div id="footerDiv" style="width:100%; border-top:0 solid #909090;">
	<div>
		<ul id="bottom-nav" class="bottom-nav">
			<li class="bottom-nav-click" onclick="toggleBottomType(this,1);">商业</li>
			<li onclick="toggleBottomType(this,2);">广告</li>
			<!--
			<li onclick="toggleBottomType(this,3);">街面</li>
			<li onclick="toggleBottomType(this,4);">公寓</li>
			<li onclick="toggleBottomType(this,5);">多经</li>
			-->
		</ul>
	</div> 
	<div style="float:right; padding:6px 8px;">
		<security:authorize ifAnyGranted="A_REPO_PROJ_QUERY,A_REPO_ARRE_QUERY,A_REPO_DETA_QUERY">
		<input type="button" class="btn_blue" style="width:75px; height:26px; line-height:26px;" onclick="changeLayout(2);" value="项目报表"/>
		</security:authorize>
        <security:authorize ifAnyGranted="A_BIS_DAY_WRITE,A_BIS_DAY_SELECT">
            <input type="button" class="btn_green" style="width:75px; height:26px; line-height:26px;" onclick="changeLayout(9);" value="经营数据"/>
        </security:authorize>
		<security:authorize ifAnyGranted="A_FACT_QUERY">
		<input type="button" class="btn_green" style="width:75px; height:26px; line-height:26px;" onclick="changeLayout(3);" value="收费明细"/>
		</security:authorize>
		<security:authorize ifAnyGranted="A_EXPE_QUERY">
		<input type="button" class="btn_green" style="width:75px; height:26px; line-height:26px;" onclick="go2Pay();" value="支出明细"/>
		</security:authorize>
		<security:authorize ifAnyGranted="A_CONT_QUERY_PERS,A_CONT_QUERY_ALL">
		<input type="button" class="btn_green" style="width:75px; height:26px; line-height:26px;" onclick="changeLayout(4);" value="合同台账"/>
		</security:authorize>
		<security:authorize ifAnyGranted="A_SHOP_QUERY_DEPA,A_SHOP_QUERY_PERS,A_SHOP_QUERY_ALL,A_SHOP_QUERY_UNIM,A_SHOP_QUERY_MAIN,A_SHOP_ADMIN">
		<input type="button" class="btn_green" style="width:65px; height:26px; line-height:26px;" onclick="changeLayout(6);" value="商家库"/>
		</security:authorize>
		<security:authorize ifAnyGranted="A_ENER_QUERY,A_ENER_IMPORT">
		<input type="button" class="btn_green" style="width:65px; height:26px; line-height:26px;" onclick="goIncomeOther();" value="能源费"/>
		</security:authorize>
		<security:authorize ifAnyGranted="A_PROJ_PROP_QUERY,A_PROJ_PROP_MODI,A_PROJ_DATA_MODI">
		<input type="button" class="btn_green" style="width:75px; height:26px; line-height:26px;" onclick="changeLayout(7);" value="基础资料"/>
		</security:authorize>

	</div>
</div>
<script language="javascript">
function changeLayout(module) {
	var bisProjectId = $("#bisProjectId").val();
	var url = "${ctx}/bis/bis-manage!layout.action?module="+module+"&bisProjectId="+$("#bisProjectId").val();
	var tabname="bis";
	var title="商业管理系统";
	
	if(module==2) {
		var tabname="bisProjectOverview";
		var title="项目报表";
	} else if(module==3) {
		var tabname="bisFee";
		var title="收费明细";
	} else if(module==4) {
		tabname='bisCont';
		title='商业合同管理';
	} else if(module==6) {
		tabname='bisShop';
		title='商家库';
	} else if(module==7) {
        tabname='bisProjectMain';
        title='基础数据维护';
    } else if(module==9) {
        tabname='bisManageDay';
        title='日经营数据管理';
    }
	
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab(tabname,title,url,true);
}

function go2Pay(){
	var bisProjectId = $("#bisProjectId").val();
	var url = '${ctx}/bis/bis-pay.action?bisProjectId='+bisProjectId;
	parent.TabUtils.newTab('payOut','支出明细',url,true);
}

function goIncomeOther() {
	var bisProjectId = $("#bisProjectId").val();
	var url = '${ctx}/bis/bis-income-other.action?bisProjectId='+bisProjectId;
	parent.TabUtils.newTab('bisIncomeOther','能源费',url,true);
}

</script>

