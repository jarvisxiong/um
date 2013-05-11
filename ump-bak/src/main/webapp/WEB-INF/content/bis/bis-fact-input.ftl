<#include "/WEB-INF/content/com/template/autoimport.ftl"/>
<#--<%@ page contentType="text/html;charset=UTF-8" %>-->
<#--<%@ include file="/common/taglibs.jsp" %>-->
<#--<%@ include file="/common/nocache.jsp" %>-->

<style type="text/css">
    div.publish{background: #E4E7EC;}
    div.publish li select{padding-left: 0px;}
    div.form_body{border: 0px solid #cccccc;margin-top: 10px; padding:19px 20px; padding-bottom: 10px; padding-top: 0px;}
    div.form_body li{clear:both; height: 25px;line-height: 25px; margin-bottom: 5px;}
    div.form_body li label{width: 40px; border: 0px solid red; display: block; float: left; text-align: right}
    div.form_body li label.max{width: 50px;}
    div.form_body li label.max2{width: 75px;}
    div.form_body li input,div.form_body li textarea,div.form_body li select{ float: left; width: 100px; padding-left:5px}
    div.form_body li input.max{width: 611px;}
    div.form_body li input.min{width: 47px;}
    div.form_body li input.disabled,div.form_body li textarea.disabled{background:#dedede;}
    div.form_body li.hover{ padding: 5px 5px 4px 5px; border:1px dashed #E4E7EC;margin-bottom: 0px;}
    div.form_body li.hover button{display: none;}
    div.form_body li.hover:hover{ border-color: gray;}
    div.form_body li.hover:hover button{display: block; }

</style>
<div id="publish">

    <div class="form_body condition_panel  ">
        <ul class="clearfix">
            <form id="query" method="post" action="bis-fact!saveBatchs.action">
                <#--<input type="hidden" name="recodeType" value="0"/>-->
                <input type="hidden" id="id" name="id" value="${bisFactId}"/>
                <#--<input type="hidden" id="currDetail" name="currDetail" value="${currDetail}"/>
                <input type="hidden" id="currDetailName" name="currDetailName" value="${currDetailName}"/>-->
                <input type="hidden" id="bisContIdInput" name="bisContIdInput"/>
                <input type="hidden" id="bisContId" name="bisContId"/>

                <li style="padding-bottom: 10px;" class="informations none"></li>

                <li style="padding-left: 41px;">

                    <label>项目：</label><input id="bisProjectNameInput" type="text" class="text required"style="width: 130px;" readonly="true"/>
                    <input type="hidden" id="bisProjectIdInput" name="bisProjectId" class="required" value="${bisProjectId}"
                           readonly="readonly"/>

                    <label class="max">业态：</label>
                    <input type="text" class="text required" style="width:59px;" id="layOutCdInput0" readonly="true"/>
                    <input name="layOutCd" id="layOutCdInput" class="required " value="" type="hidden"/>

                    <label class="max" id="currDetailLabel">商铺：</label>
                    <select class="box required" style="width:111px; padding-left: 2px;" name="currDetail" onclick="selectDetail2();" id="layOutCdListInput">
                    </select>

                    <label class="max2">实收日期：</label>
                    <input type="text" class="text required" name="factDate" id="factDate" style="width:65px;" onfocus="WdatePicker()" value="<#if factDate=="">${helper.currentDate?string("yyyy-MM-dd")}</#if>"/>

                    <button class="green min " type="button" style="margin-left:10px; float: left;height: 24px;line-height: 24px;">添加</button>
                </li>
                <li class="hover">
                    <input type="text" name="index" style="width:18px;height:18px;border:1px solid #999" value="1"/>
                    <label>年：</label>
                    <select class="box required" style="width:70px; padding-left: 2px;" name="factYear" id="factYear">
                        <option value="">请选择</option>
                    <#list 2008..helper.currentDate?string("yyyy")?number+4 as year>
                        <option value="#{year}"
                                <#if year==helper.currentDate?string("yyyy")?number>selected="selected" </#if>>#{year}</option>
                    </#list>
                    </select>
                    <label class="max" style="margin-left:-3px;">月：</label>
                    <select class="box required" style="width:50px; padding-left: 2px;" name="factMonth" id="factMonth">
                        <option value="">请选择</option>
                    <#list 1..12 as month>
                        <option value="#{month}"
                                <#if month==helper.currentDate?string("MM")?number>selected="selected" </#if>>#{month}</option>
                    </#list>
                    </select>
                    <label class="max">类别：</label>
                    <select class="box required" style="width:111px; padding-left: 2px;" name="chargeTypeCd" id="chargeTypeCdInput" class="box">
                    <#list chargeTypes?keys?sort as key>
                        <#if key_index==0>
                            <option value="">请选择</option>
                        <#else>
                            <option value="${key}">${chargeTypes.get(key)}</option>
                        </#if>
                    </#list>
                    </select>
                    <label class="max2">实收金额：</label><input type="text" class="text required number" style="width:65px;" id='money' name='money' value="${money}" onkeyup="formManager.count()"/>
                    <label class="max">备注：</label><input type="text" class="text" name="remark" style="width:120px" name="t10"/>
                    <button class="red min" type="button" style="margin-left: 2px; float: left;height: 24px;line-height: 24px;" onclick="formManager.del(this)">删除</button>
                </li>
                <li class="buttons clearfix" style="padding-left: 81px;margin-top: 5px; margin-bottom: 5px;">

                    <button class="green min " type="button" style="float: left;width:65px;">保存</button>
                    <button class="red min" type="button" style="float:left;width:65px;">取消</button>
                    <label style="margin-left: 316px">合计：</label><input type="text" class="text" name="count" id="count" style="width:66px;" readonly="true" value="0"/>
                </li>
            </form>
        </ul>
    </div>
    <script type="text/javascript">
        window.formManager={del:function(element){
            $("li.informations").hide();
            var lis=$("form#query li.hover");
            if(lis.size()>=2){
                $(element).parent().remove();
                this.count();
            }else{
                powerdesk.ui.prompt.error({message:"再删就没喽..."});
            }
            $("form#query li.hover").each(function(index){
                $("input:first",this).val(index+1);
            });
        },count:function(){
            var moneys=$("form#query li.hover input[name=money]");
            var count=$("#count").val("0");
            moneys.each(function($this){
                var input=$(this);
                if(/^\d+(\.\d{0,2})?$/.test(input.val())){
                    count.val(parseFloat(count.val())+parseFloat(input.val()));
                    if(count.val().indexOf(".")!=-1){
                        count.val(count.val().substring(0,count.val().indexOf(".")+3));
                    }
                }
            });
        }}
        $("form#query button:contains(添加)").click(function(){
            var li=  $("form#query li.hover:eq(0)").clone();
            li.find("input").val("");
            li.find("select").each(function(){
                this.selectedIndex=0;
            })
            $("form#query li.buttons").before(li);
            $("form#query li.hover").each(function(index){
                $("input:first",this).val(index+1);
            });
        });
        $("form#query button:contains(保存)").click(function(){
            $("li.informations").hide();
            var errorMessage = powerdesk.helper.validation(".required");
            if (errorMessage && errorMessage != "") {
                window.isSave = false;
                powerdesk.ui.prompt.error({message:errorMessage});
            } else {
                try{
                    TB_showMaskLayer("正在发送数据...", 5000);
                    var param = $("form#query").serializeArray();
                    //alert($.param(param));
                    $("form#query").ajaxSubmit(function(input){
                        //alert(input);
                        //alert($("#btn_search").size())
                        $("#btn_search").trigger("click");
                    })
                    /*$.ajax({type:'POST', url:"bis-fact!saveBatchs.action", data:$.param(param), dataType:"json", success:function ($result) {
                        alert($result);
                    }})*/
                }catch(ex){
                    alert(ex.message)
                }
                /*alert($result);$("div#publish").parent().hide();*/
            }
        });
        $("form#query button:contains(取消)").click(function(){
            $("div#publish").parent().hide();
        });
    </script>
</div>

<#--form action="${url.ctx}/bis/bis-fact!saveBatchs.action" id="inputForm" method="post">
    <legend>
    <#if inputStatus==0>
        <font style="margin-left: 10px;color:#ffa613;font-size:14px ;font-weight:bold;">新增</font>
    <#elseif inputStatus==1>
        <span style="height:10px;width:90%;display:block"></span>
    </#if>
    </legend>
    <table class="sup_table main_content">
        <input type="hidden" id="id" name="id" value="${bisFactId}"/>
        <input type="hidden" id="currDetail" name="currDetail" value="${currDetail}"/>
        <input type="hidden" id="currDetailName" name="currDetailName" value="${currDetailName}"/>
        <input type="hidden" id="bisContIdInput" name="bisContIdInput"/>
        <input type="hidden" id="bisContId" name="bisContId"/>
        <col width="6%">
        <col width="12%">
        <col width="5%">
        <col width="12%">
        <col width="5%">
        <col width="12%">
        <col width="7%">
        <col width="12%">
        <col width="4%">
        <col width="12%">
        <tr>
            <td align="right">
                项目：
            </td>
            <td>

            </td>
            <td align="right">
                业态：
            </td>
            <td>

            </td>
            <td align="right">
            </td>
            <td>
            </td>
            <td align="right">
                实收日期：
            </td>
            <td>
            </td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td align="right">
                年：
            </td>
            <td>

            </td>
            <td align="right">
                月：
            </td>
            <td>
            </td>
            <td align="right">
                类别：
            </td>
            <td>
            </td>
            <td align="right">实收金额：
            </td>
            <td>

            </td>
            <td>备注：</td>
            <td style="text-align: left; background: red;"><textarea rows="1" cols="20" name="remark" id="remark" style="font-size:12px;"></textarea></td>
        </tr>
        <tr>
            <td align="right">备注：
            </td>
            <td colspan="4">
                <textarea rows="1" cols="20" name="remark" id="remark" style="font-size:12px;"></textarea>
            </td>
        </tr>
    <#if inputStatus==0>
        <tr>
            <td></td>
            <td colspan="7">
                <button type="button" class="btn_green" onclick="save('inputForm');">保存</button>
                <button type="button" class="btn_red" style="margin-left:-5px;" onclick="cance();">取消</button>
            </td>
        </tr>
    </#if>
    </table>

</form-->

