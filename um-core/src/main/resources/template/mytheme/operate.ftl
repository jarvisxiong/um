<#if parameters.enableName??>
<#if parameters.enableVal??>
<a<#rt/>
 href="${parameters.action}!save.action?id=${parameters.idValue}&${parameters.enableName}=${parameters.enableKey}"<#rt/>>
 <img src="${parameters.ctx}/images/tab/edt.gif" width="16" height="16" border="0"/>
 ${parameters.enableVal}
</a>
</#if>
</#if>
<a<#rt/>
 href="${parameters.action}!input.action?id=${parameters.idValue}"<#rt/>>
 <img src="${parameters.ctx}/images/tab/edt.gif" width="16" height="16" border="0"/>
 ${parameters.editInfo}
</a>
<a<#rt/>
 href="${parameters.action}!delete.action?id=${parameters.idValue}"<#rt/>>
 <img src="${parameters.ctx}/images/tab/del.gif" width="16" height="16" border="0"/>
 ${parameters.deleteInfo}
</a>
