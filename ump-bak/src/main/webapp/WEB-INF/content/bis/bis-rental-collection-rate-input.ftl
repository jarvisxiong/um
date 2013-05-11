<#macro print key  value >
	<#assign len=value?length>
	<#if len gt 17 && value[0..17] =="<font color='red'>" >
		<input type="text" class="text required" onafterpaste="this.value=this.value.replace(/[^\d\.]/g,'');$(this).val($(this).val().substr(0,50));"  onchange="this.value=this.value.replace(/[^\d\.]/g,'');$(this).val($(this).val().substr(0,50));"  name="bisRentalCollectionRate.${key}" maxlength="80" value="${value[18..]?replace('</font>','')?replace(' > ','>')?replace(' < ','<')}" />
	<#else>
		<input type="text" class="text" onafterpaste="this.value=this.value.replace(/[^\d\.]/g,'');$(this).val($(this).val().substr(0,50));"  onchange="this.value=this.value.replace(/[^\d\.]/g,'');$(this).val($(this).val().substr(0,50));"  name="bisRentalCollectionRate.${key}" maxlength="80" value="${value?replace(' > ','>')?replace(' < ','<')}" />
	</#if>
</#macro>

<#macro printChangeColor1 key value>
<#assign len=value?length>
	<#assign class1="">
	<#if len gt 17 && value[0..18] =="<font class='tred'>" >
		<#assign class1="tred">
	<#elseif len gt 17 && value[0..21]=="<font class='tyellow'>">
		<#assign class1="tyellow">
	<#elseif len gt 17 && value[0..20]=="<font class='tgreen'>">
		<#assign class1="tgreen">
	</#if>
	<input type="text" readonly="readonly" onfocus="calcModi(1,this)" class="text ${class1}" name="bisRentalCollectionRate.${key}" value="${helper.clearHtml(value!"")?trim}" />
	<input type="hidden" id="bisRentalCollectionRate.${key}" value="5" />
</#macro>

<#macro printChangeColor2 key value>
<#assign len=value?length>
	<#assign class2="">
	<#if len gt 17 && value[0..18] =="<font class='tred'>" >
		<#assign class2="tred">
	<#elseif len gt 17 && value[0..21]=="<font class='tyellow'>">
		<#assign class2="tyellow">
	<#elseif len gt 17 && value[0..20]=="<font class='tgreen'>">
		<#assign class2="tgreen">
	</#if>
	<input type="text" readonly="readonly" onfocus="calcModi(2,this)" class="text ${class2}" name="bisRentalCollectionRate.${key}"  value="${helper.clearHtml(value!"")?trim}" />
	<input type="hidden" id="bisRentalCollectionRate.${key}" value="5" />
</#macro>
<#macro printRemark key  value >
	<#assign len=value?length>
	<#if len gt 17 && value[0..17] =="<font color='red'>" >
		<textarea onafterpaste="$(this).val($(this).val().substr(0,50));"  onchange="$(this).val($(this).val().substr(0,50));"  name="bisRentalCollectionRate.${key}" style='color:red;overflow: auto;width:521px;' maxlength="80" cols="39" rows="3" >${value[18..]?replace('</font>','')?replace(' > ','>')?replace(' < ','<')}</textarea>
		<input type="hidden" id="bisRentalCollectionRate.${key}" value="1" />
	<#else>
		<textarea onafterpaste="$(this).val($(this).val().substr(0,50));"  onchange="$(this).val($(this).val().substr(0,50));"  name="bisRentalCollectionRate.${key}" maxlength="80" cols="39" rows="3" style='overflow: auto;width:521px;'>${value?replace(' > ','>')?replace(' < ','<')}</textarea>
		<input type="hidden" id="bisRentalCollectionRate.${key}" value="0" />
	</#if>
</#macro>
<style type="text/css">
.titleDiv{width:auto;}

div.form_body{border: 0px solid #cccccc;margin-top: 5px; padding:19px 20px;}
div.form_body li{clear:both; height: 25px;line-height: 25px; margin-bottom: 5px;}
div.form_body li label{width: 80px; border: 0px solid red; display: block; float: left; text-align: right;  /*color: gray*/}
div.form_body li label.max{width: 100px;}
div.form_body li input,div.form_body li textarea,div.form_body li select{ float: left; width: 100px; padding-left:5px}
div.form_body li input.max{width: 611px;}
div.form_body li input.min{width: 47px;}
div.form_body li input.disabled,div.form_body li textarea.disabled{background:#dedede;}
div.form_body li textarea{float: left;width: 610px; padding-top: 5px; height: 50px;}

#publish {
    width: 620px;
    margin-bottom:0px;
}

span.select-color {
    position: relative;
}

span.select-color:hover input {
    cursor: pointer;
}

span.select-color ul {
    display: none;
    border: 1px solid #000;
    position: absolute;
    width: 106px;
    top: 28px;
    right: 0px;
    background: #FFF;
    clear: both;
    padding: 1px;
    /*height: 25px;*/
    overflow: hidden;
    z-index: 1000;
}

span.select-color ul li {
    /*float: left;*/
    clear: none;
    text-align: center;
    width: 106px;
    font-weight: normal;
    height: 25px;
    font-size: 12px;
    line-height: 25px;
    cursor: pointer;
    margin-bottom: 0px;
}

span.select-color  .white{
    background: white
}
span.select-color  .red {
    background: red;
}

span.select-color  .yellow {
    background: yellow;
}

span.select-color  .green {
    background: green;
}

span.select-color ul .line {
    height: 1px;
    background: #000;
    width: 100px;
}

#publish  span.select-color:hover  ul {
    display: block;;
}

span.select-color:hover ul li:hover {
    border-bottom: 1px solid #000;
    height: 24px;
}

input.tyellow,.tyellow {
	color: black;
	background: #fff000;
}

input.tgreen,.tgreen {
	color: black;
	background: #53b147
}

input.tred,.tred {
	color: black;
	background: #ac2727;
}
</style>
<form action="bis-rental-collection-rate!save.action"  id="saveReport" method="post" >
<input type="hidden" id="colorFlagId" value="1"/>
  <input type="hidden" name="id" value="${bisRentalCollectionRate.bisRentalCollectionRatId?if_exists}"/>
  <input type="hidden" name="bisRentalCollectionRate.bisProjectId" value="${bisRentalCollectionRate.bisProjectId?if_exists}"/>
        <div id="publish" class="form_body">
            <ul class="clearfix">
                    <li>
                        <h2 style="text-align: center;margin:0px;padding:0px;">
                        <#list rateProjects.keySet() as m> 
                            <#list rateProjects.get(m) as p> 
                              <#if p.bisProjectId==bisRentalCollectionRate.bisProjectId>
                                &nbsp;${p.projectName}
                              </#if>
                            </#list>	
                      </#list>
                        </h2>
                    </li>
                    <li>
                        <label style="color: red; text-align: center;">租金</label>
                    </li>
                    <li>
                        <label>累计应收额：</label>
                        <@print key="cumulativeRecAmountRent" value="${bisRentalCollectionRate.cumulativeRecAmountRent?if_exists}"/> 
                        <label class="max">累计实收额：</label>
                        <@print key="cumulativeColRateRent" value="${bisRentalCollectionRate.cumulativeColRateRent?if_exists}"/>
                        <span class="select-color" style="float: left;">
                            <ul>
                                <li class="tred">红</li>
                                <li class="tyellow">黄</li>
                                <li class="tgreen">绿</li>
                                <li class="white">无</li>
                            </ul>
                             <label class="max">累计收缴率：</label>
                            <@printChangeColor1 key="rentalCollPointRent" value="${bisRentalCollectionRate.rentalCollPointRent?if_exists}"/>
                        </span>
                    </li>
                    <li>
					    <label>本月应收额：</label>
					    <@print key="cumulativeRecAmountCurRent" value="${bisRentalCollectionRate.cumulativeRecAmountCurRent?if_exists}"/> 
					    <label class="max">本月实收额：</label>
					    <@print key="cumulativeColRateCurRent" value="${bisRentalCollectionRate.cumulativeColRateCurRent?if_exists}"/>
					</li>
                    <li>
                        <label style="color: red; text-align: center;">物管</label>
                    </li>
                    <li>
                        <label>累计应收额：</label>
                        <@print key="cumulativeRecAmountProperty" value="${bisRentalCollectionRate.cumulativeRecAmountProperty?if_exists}"/>
                        <label class="max">累计实收额：</label>
                         <@print key="cumulativeColRateProperty" value="${bisRentalCollectionRate.cumulativeColRateProperty?if_exists}"/>
                         <span class="select-color" style="float: left;">
                            <ul>
                                <li class="tred">红</li>
                                <li class="tyellow">黄</li>
                                <li class="tgreen">绿</li>
                                <li class="white">无</li>
                            </ul>
                             <label class="max">累计收缴率：</label>
                            <@printChangeColor2 key="rentalCollPointProperty" value="${bisRentalCollectionRate.rentalCollPointProperty?if_exists}"/>
                        </span>
                    </li>
                    <li>
					    <label>本月应收额：</label>
					    <@print key="cumulativeRecAmountCurPpt" value="${bisRentalCollectionRate.cumulativeRecAmountCurPpt?if_exists}"/> 
					    <label class="max">本月实收额：</label>
					    <@print key="cumulativeColRateCurPpt" value="${bisRentalCollectionRate.cumulativeColRateCurPpt?if_exists}"/>
					</li>
                    <li style="height: 60px; margin-top: 15px;margin-bottom:0px;">
                        <label>备&nbsp;&nbsp;注：</label>
                        <@printRemark key="remark" value="${bisRentalCollectionRate.remark?if_exists}"/>
                    </li>
            </ul>
        </div>
        <script type="text/javascript">
            $("span.select-color").each(function () {
                var container = $(this);
                var options = $("li", this).click(function () {
                    var input = $(this).parent().parent().find("input[type=text]");
                    var hd = $(this).parent().parent().find("input[type=hidden]");
                    input.attr("class", "text");
                    input.addClass($(this).attr("class"));
                    $(hd).val((options.index(this)+2));
                });
            });
        </script>
</form>
 <script type="text/javascript">
    	$(document).ready(function(){
    		  /**初始化显示的颜色*/
    		  initColor();
    		  /**实收失去焦点事件*/
    		  blurCalcModi();
    	});
    	
    	/**设置颜色*/
    	function setColor(type){
    		var demo = ":input[name=bisRentalCollectionRate."+$("#colorFlagId").val()+"]";
    		if(type==1){
	    		$(demo).removeClass('tyellow');
	    		$(demo).removeClass('tgreen');
    			$(demo).addClass('tred');
    		}
    		if(type==2){
	    		$(demo).removeClass('tred');
	    		$(demo).removeClass('tgreen');
    			$(demo).addClass('tyellow');
    		}
    		if(type==3){
	    		$(demo).removeClass('tred');
	    		$(demo).removeClass('tyellow');
    			$(demo).addClass('tgreen');
    		}
    		$('#changColorDiv').hide();
    		$(":hidden[id=bisRentalCollectionRate."+$("#colorFlagId").val()+"]").val(type+2);
    	}
    	/**初始化显示颜色的标示*/
    	function initColor(){
    		if($(":input[name=bisRentalCollectionRate.rentalCollPointRent]").hasClass('tred')){
    			$(":hidden[id=bisRentalCollectionRate.rentalCollPointRent]").val(2);	
    		}else if($(":input[name=bisRentalCollectionRate.rentalCollPointRent]").hasClass('tyellow')){
    			$(":hidden[id=bisRentalCollectionRate.rentalCollPointRent]").val(3);
    		}else if($(":input[name=bisRentalCollectionRate.rentalCollPointRent]").hasClass('tgreen')){
    			$(":hidden[id=bisRentalCollectionRate.rentalCollPointRent]").val(4);
    		}else if($(":input[name=bisRentalCollectionRate.rentalCollPointRent]").hasClass('text')){
    			$(":hidden[id=bisRentalCollectionRate.rentalCollPointRent]").val(5);
    		}
			if($(":input[name=bisRentalCollectionRate.rentalCollPointProperty]").hasClass('tred')){
    			$(":hidden[id=bisRentalCollectionRate.rentalCollPointProperty]").val(2);	
    		}else if($(":input[name=bisRentalCollectionRate.rentalCollPointProperty]").hasClass('tyellow')){
    			$(":hidden[id=bisRentalCollectionRate.rentalCollPointProperty]").val(3);
    		}else if($(":input[name=bisRentalCollectionRate.rentalCollPointProperty]").hasClass('tgreen')){
    			$(":hidden[id=bisRentalCollectionRate.rentalCollPointProperty]").val(4);
    		}else if($(":input[name=bisRentalCollectionRate.rentalCollPointProperty]").hasClass('text')){
    			$(":hidden[id=bisRentalCollectionRate.rentalCollPointProperty]").val(5);
    		}
    	}
    	/**更改显示颜色*/
    	function changeDisColor(demo,demoId,type){
			var x = $(demo).position();
			$("#changColorDiv").css("left",x.left-5);
			$("#changColorDiv").css("top",x.top-28);
			/**设置标示*/
			$("#colorFlagId").val(demoId);
			$('#changColorDiv').show();
    	}
    	/**及时计算*/
    	function calcModi(demo,obj){
    		var t ="",bt="";
    		if(demo==1){
    			t = $(":input[name='bisRentalCollectionRate.cumulativeRecAmountRent']").val();
    			bt= $(":input[name='bisRentalCollectionRate.cumulativeColRateRent']").val();
    		}else{
    			t = $(":input[name='bisRentalCollectionRate.cumulativeRecAmountProperty']").val();
    			bt= $(":input[name='bisRentalCollectionRate.cumulativeColRateProperty']").val();
    		}
			if(filterNaN(t)==0){
				alert("请正确填写'应收'数据!");
				$(obj).val("");
				return ;
			}
			$(obj).val(changeTwoDecimal_f(filterNaN(bt)*100/filterNaN(t)));
    	}
    	/**实收失去焦点，即刻让收缴率获得焦点,并将输入数字转换成2位小数*/
    	function blurCalcModi(){
    		$(":input[name='bisRentalCollectionRate.cumulativeColRateRent']").bind("blur",function(){
    			$(this).val(changeTwoDecimal_f($(this).val()));
				$(":input[name='bisRentalCollectionRate.rentalCollPointRent']").focus();
			})
			$(":input[name='bisRentalCollectionRate.cumulativeColRateProperty']").bind("blur",function(){
    			$(this).val(changeTwoDecimal_f($(this).val()));
				$(":input[name='bisRentalCollectionRate.rentalCollPointProperty']").focus();
			})
			$(":input[name='bisRentalCollectionRate.cumulativeRecAmountRent']").bind("blur",function(){
			  $(this).val(changeTwoDecimal_f($(this).val()));
			});
			
			$(":input[name='bisRentalCollectionRate.cumulativeColRateRent']").bind("blur",function(){
			  $(this).val(changeTwoDecimal_f($(this).val()));
			});
			
			$(":input[name='bisRentalCollectionRate.cumulativeRecAmountCurRent']").bind("blur",function(){
			  $(this).val(changeTwoDecimal_f($(this).val()));
			});
			
			$(":input[name='bisRentalCollectionRate.cumulativeColRateCurRent']").bind("blur",function(){
			  $(this).val(changeTwoDecimal_f($(this).val()));
			});
			
			$(":input[name='bisRentalCollectionRate.cumulativeRecAmountProperty']").bind("blur",function(){
			  $(this).val(changeTwoDecimal_f($(this).val()));
			});
			
			$(":input[name='bisRentalCollectionRate.cumulativeColRateProperty']").bind("blur",function(){
			  $(this).val(changeTwoDecimal_f($(this).val()));
			});
			
			$(":input[name='bisRentalCollectionRate.cumulativeRecAmountCurPpt']").bind("blur",function(){
			  $(this).val(changeTwoDecimal_f($(this).val()));
			});
			
			$(":input[name='bisRentalCollectionRate.cumulativeColRateCurPpt']").bind("blur",function(){
			  $(this).val(changeTwoDecimal_f($(this).val()));
			});
    	}
</script>

	