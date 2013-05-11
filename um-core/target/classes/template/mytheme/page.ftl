<span class="pagesNav">
<span class="tc">
${parameters.totalCount}
</span>
<span class="ni">
${parameters.noInfo}
</span>
<#if parameters.hasPre == true>
<a<#rt/> class="fp" title="首页" href="javascript:jumpPage${parameters.key}(1)"<#rt/>>${parameters.firstInfo}</a>
<a<#rt/> class="pp" title="上页" href="javascript:jumpPage${parameters.key}(${parameters.prePage})"<#rt/>>${parameters.perInfo}</a>
<#else>
<span class="fp" title="首页"></span>
<span class="pp" title="上页"></span>
</#if>
<#if parameters.hasNext==true>
<a<#rt/> class="np" title="下页" href="javascript:jumpPage${parameters.key}(${parameters.nextPage})"<#rt/>>${parameters.nextInfo}</a>
<a<#rt/> class="lp" title="末页" href="javascript:jumpPage${parameters.key}(${parameters.totalPage?string("###")})"<#rt/>>${parameters.lastInfo}</a>
<#else>
<span class="np" title="下页"></span>
<span class="lp" title="末页"></span>
</#if>
<#if parameters.showInput==true>
<input type="text" id="pageTo${parameters.key}" size="2" />
<a<#rt/> href="javascript:jumpPageTo${parameters.key}()"<#rt/>>Go</a>
</#if>
<#if parameters.createAttr==true>
<input type="hidden" value="${parameters.formId}" id="searFormId"/>
<input type="hidden" name="${parameters.page}.pageNo" value="${parameters.pageNo}" id="pageNo${parameters.key}"/>
<input type="hidden" name="${parameters.page}.orderBy" value="${parameters.orderBy!""}" id="orderBy${parameters.key}"/>
<input type="hidden" name="${parameters.page}.order" value="${parameters.order!""}" id="order${parameters.key}"/>
</#if>
</span>