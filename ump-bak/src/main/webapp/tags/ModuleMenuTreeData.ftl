{'text':'模块',
    id:'0',
    nodeType:'root',
    children:[
<#assign isContained = false>
<#assign isContaineMenu = false>
<#assign checkFuncs = 0>
<#assign checkMenus = 0>
<#assign checkModules = 0>
<#if appModules?exists>
  <#list appModules as module>
  {
    'text':'${module.moduleName}',
    id:'${module.appModuleId}',
    nodeType:'module',
    children:[
    <#list module.appModuleMenuRels as appModuleMenuRel>
    <#assign appMenu = appModuleMenuRel.appMenu>
    <#if appMenu?exists>
	    {
	    'text':'${appMenu.menuName}',
	    'id':'${appMenu.appMenuId}',
	    nodeType:'menu',
	    <@roleMenuList appMenu.appMenuId/>
	    children:[
	    <#list appMenu.appPage.appFunctions as appFunction>
	    {
	    'text':'${appFunction.functionName}',
	    'id':'${appMenu.menuCd}-${appFunction.appFunctionId}',
	    nodeType:'function',
	    <@roleFuncList appMenu.menuCd,appFunction.appFunctionId/>
	    <#if isContained>
	    'checked':1
	    <#else>
	    'checked':0
	    </#if>
	    }
		<#if appFunction_has_next>
		 ,
	 	</#if>
    	</#list>
	    ],
	    <#if (appMenu.appPage.appFunctions.size()==0 && isContaineMenu) || (checkFuncs>0 && checkFuncs=appMenu.appPage.appFunctions.size())>
	    <#assign checkMenus =  checkMenus+1>
	    'checked':1
	    <#elseif (checkFuncs>0||isContaineMenu)>
	    <#assign checkMenus =  checkMenus+0.5>
	    'checked':2
	    <#else>
	    'checked':0
	    </#if>
	    }
	    <#assign checkFuncs = 0>
	  <#if appModuleMenuRel_has_next>
	  ,
	  </#if>
	</#if>
    </#list>
    ],
    <#if (checkMenus>0 && checkMenus=module.appModuleMenuRels.size())>
    <#assign checkModules =  checkModules+1>
    'checked':1
    <#elseif (checkMenus>0)>
    <#assign checkModules =  checkModules+0.5>
    'checked':2
    <#else>
    'checked':0
    </#if>
  <#assign checkMenus = 0>
  }
  <#if module_has_next>
  ,
  </#if>
  </#list>
</#if>
],
 	<#if (checkModules>0 && checkModules=appModules.size())>
    'checked':1
    <#elseif (checkModules>0)>
    'checked':2
    <#else>
    'checked':0
    </#if>
}
<#macro roleMenuList menuId>
	<#if appRoleMenuRels?exists>
	<#list appRoleMenuRels as appRoleMenuRel>
		<#if menuId==appRoleMenuRel.appMenu.appMenuId>
			relId:'${appRoleMenuRel.appRoleMenuRelId}',
			<#assign isContaineMenu = true>
			<#return>
		</#if>
	</#list>
	</#if>
	<#assign isContaineMenu = false>
</#macro>
<#macro roleFuncList menuCd,funcId>
	<#if appRoleFunctionRels?exists && isContaineMenu>
	<#list appRoleFunctionRels as appRoleFunctionRel>
		<#if (funcId==appRoleFunctionRel.appFunction.appFunctionId) && (appRoleFunctionRel.menuCd?exists&&menuCd==appRoleFunctionRel.menuCd)>
			relId:'${appRoleFunctionRel.appRoleFunctionRelId}',
			<#assign isContained = true>
			<#assign checkFuncs = checkFuncs+1>
			<#return>
		</#if>
	</#list>
	</#if>
	<#assign isContained = false>
</#macro>
