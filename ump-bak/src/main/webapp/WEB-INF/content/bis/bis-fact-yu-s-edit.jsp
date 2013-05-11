<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<style>

    input {
        display: inline;
    }

    -->
</style>
<script type="text/javascript">

</script>
<form action="${ctx}/bis/bis-fact-yu-s!save.action" id="frmUpdteAdvances" method="post">
    <input type="hidden" id="bisProjectId" name="bisProjectId" value="${bisProjectId }"/>
    <div>
        <table class="sup_table main_content" border='0' style="margin-top: 5px;">
            <input type="hidden" id="id" name="id" value="${bisFactId}"/>
            <input type="hidden" id="bisFactId" name="bisFactId" value="${bisFactId}"/>
            <input type="hidden" id="bisTenantId" name="bisTenantId" value="${bisTenantId}"/>
            <input type="hidden" id="hasDichong" name="hasDichong" value="${hasDichong }"/>
            <col width="80px">
            <col>
            <tr>
                <td align="right">
                    <div id="currDetailLabel">租户：</div>
                </td>
                <td>${tenant}
                </td>
            </tr>
            <tr>
                <td align="right">实收日期：</td>
                
                <td><input type="text" class="required" onfocus="WdatePicker()"
                           value="<s:date name="factDate" format="yyyy-MM-dd"/>" name="factDate" id="factDate"
                           validate="required" style="width:125px;"/></td>
            </tr>
            <tr>
                <td align="right">实收金额：</td>
                <td><input type='text' class="required" id='money' name='money' value="${money }"
                           onblur="formatNumber1($(this));"/></td>
            </tr>
            <tr>
                <td align="right">备注：</td>
                <td><textarea rows="2" cols="20" name="remark"
                              id="remark" style="font-size: 12px;width: 95%;">${remark }</textarea></td>
            </tr>
            <tr>
                <td></td>
                <td align="left" style="padding-top:8px;">
                	<s:if test="statusCd==1">
	                    <security:authorize ifAnyGranted="A_FACT_CHECK">
	                        <input type="button" class='btn_red' id="btn_reject" value="驳回"
	                               onclick='rejectYuShou("${bisFactId}");'/>
	                    </security:authorize>
                    </s:if>
                    <s:if test="statusCd==0||statusCd==2">
                        <security:authorize ifAnyGranted="A_FACT_CHECK">
                            <input type="button" class='btn_blue' id="btn_pass" value='审核'
                                   onclick='passYuShou("${bisFactId}");'/>
                        </security:authorize>
                        <security:authorize ifAnyGranted="A_FACT_INSERT">
                            <input class='btn_green' type="button" id="btn_save" value='保存'
                                   onclick='updateAdvances("${bisFactId}");'/>
                        </security:authorize>
                        <security:authorize ifAnyGranted="A_FACT_DELETE">
                            <input class='btn_red' id="btn_del" type="button" value="删除"
                                   onclick='removeYuShou("${bisFactId}");'/>
                        </security:authorize>
                    </s:if>
                    <input type="button" class='btn_red' id="btn_cance" value="取消" onclick='ymPromptClose();'/>
                </td>
            </tr>
        </table>
    </div>
</form>


