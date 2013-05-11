<#include "/WEB-INF/content/com/template/autoimport.ftl"/>
<#--<%@ page contentType="text/html;charset=UTF-8" %>-->
<#--<%@ include file="/common/taglibs.jsp" %>-->
<#--<%@ include file="/common/nocache.jsp" %>-->

<style type="text/css">
    div.publish{background: #E4E7EC;}
    div.publish li select{padding-left: 0px;}
    div.form_body{border: 0px solid #cccccc; padding:19px 20px; padding-bottom: 10px; padding-top: 0px;}
    div.form_body li{clear:both; height: 25px;line-height: 25px; margin-bottom: 5px; border-bottom:1px dashed #E4E7EC;margin-bottom: 0px;padding: 5px 0;}
    div.form_body li label{width: 60px; border: 0px solid red; display: block; float: left; text-align: right}
    div.form_body li label.max{width: 75px;}
    div.form_body li label.max2{width: 75px;}
    div.form_body li input,div.form_body li textarea,div.form_body li select{ float: left; width: 100px; padding-left:5px}
    div.form_body li input.max{width: 611px;}
    div.form_body li input.min{width: 47px;}
    div.form_body li input.disabled,div.form_body li textarea.disabled{background:#dedede;}
    div.form_body li.hover{ padding: 5px 5px 4px 5px;}
    div.form_body li.hover button{display: none;}
    div.form_body li.hover:hover{ border-color: gray;}
    div.form_body li.hover:hover button{display: block; }

</style>
<div id="publish">
    <div class="form_body condition_panel  ">
        <ul class="clearfix">
            <form id="frmAddAdvance" method="post" action="bis-fact-yu-s!save.action">
                <input type="hidden" name="bisContId" id="bisContId" value="">

                <li style="padding-bottom: 10px;" class="informations none"></li>
                <li>
                    <label>项目名称：</label>
                    <input id="bisProjectNameInput" type="text" class="text required"style="width: 130px;" readonly="true"/>
                    <input type="hidden" id="bisProjectId" name="bisProjectId" value="${bisProjectId}" readonly="readonly"/>

                    <label class="max" style="width:50px;">业态：</label>
                    <input type="text" class="text required" style="width:59px;" readonly="true" value="商铺"/>
                    <input name="layOutCd" id="layOutCdInput" value="1" type="hidden"/>

                    <label class="max"  style="width:50px;" id="currDetailLabel">商铺：</label>
                    <select class="box required" style="width:111px; padding-left: 2px;" name="bisTenantId" onclick="selectTenant('layOutCdInput','bisProjectIdFact','layOutCdListInput');" id="layOutCdListInput">
                    </select>
                    <label class="max">实收日期：</label>
                    <input type="text" class="text required" name="factDate"  id="factDate" style="width:65px;" onfocus="WdatePicker()" value="<#if factDate=="">${helper.currentDate?string("yyyy-MM-dd")}</#if>"/>

                </li>
                <li>
                    <label>实收金额：</label><input type="text" maxlength="8"  class="text required number" style="width: 130px;" id='money' name='money' value="${money}" onkeyup="formManager.count()"/>
                    <label class="max"  style="width:50px;">备注：</label><input type="text" class="text" name="remark" style="width:222px" name="t10"/>
                </li>
                <li class="buttons clearfix" style="padding-left: 60px; border-bottom: none;">
                    <button class="green min " type="button" style="float: left;width:66px;">保存</button>
                    <button class="red min" type="button" style="float:left; margin-left: 10px;width:65px;" onclick="cance();">取消</button>
                </li>
            </form>
        </ul>
    </div>
    <script type="text/javascript">
        $("#bisProjectNameInput").val($("#bisProjectName").val());
        $("form#frmAddAdvance button:contains(保存)").click(function(){
            $("li.informations").hide();
            var errorMessage = ump.helper.validation(".required");
            if (errorMessage && errorMessage != "") {
                window.isSave = false;
                ump.ui.prompt.error({message:errorMessage});
            } else {
                try{
                    TB_showMaskLayer("正在发送数据...", 5000);
                    $("form#frmAddAdvance").ajaxSubmit(function(input){
                       TB_removeMaskLayer()
                        if(input=='success'){
                            alert('新增预收明细成功!');
                            cance();
                        }else {
                            alert('新增预收明细失败!');
                        }
                        toFactYuShou();
                    })
                }catch(ex){
                    alert(ex.message)
                }
            }
        });
        $("form#query button:contains(取消)").click(function(){
            $("div#publish").parent().hide();
        });
    </script>
</div>

