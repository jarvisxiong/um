<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<div style="height: 170px;">
	  <div style="clear:left; padding-top: 10px;">
	 	<span style="padding-left: 2px;">【${entity.supName}】存在以下联系人,请选择一位作为投标联系人：</span>
	  </div>
 	
 	  <br/>
 
	  <table style="width: 100%;height: 140" >
	  	<tr>
	  		<td>
	  			<div>
				  <s:iterator value="contactors" var="con" status="st">		 
					  	   <div style="line-height: 30px;height: 30px;clear: left;" class="choosePanel" condiv="condiv" contactor="${supContactorId}">
					  	   		<pre ><span style="margin-left: 5px;"><s:property value="#st.index+1"/>、
					  	   		${name} 电话:${telephone} 邮件:${EMail}
					  	   		</span></pre>
					  	   </div>			  	 	  
				  </s:iterator>
			    </div>
		  	</td>  
		</tr>
	  </table>
	  
</div>

<script type="text/javascript">
	$(function(){
		$("div[condiv='condiv']").click(
				function(){					
					var o=$(this);
					o.addClass('choosed').siblings().removeClass('choosed');
					o.children().eq(0).children().eq(0).val('checked');
				}
			);		
	});
function cancelChoose(){
	$("#contactWin").hide();
}

function chooseContacor(bidSupId){
	var url=_ctx+"/bid/bid-sup!chooseContactor.action";
	var supContactorId=$("input[name=supContactorId]:checked").attr('value');		
	if(typeof(supContactorId)=='undefine'||supContactorId==''){
			alert('请选择一位联系人！');
			return false;
		}
	$.post(url, {bidSupId : bidSupId,supContactorId:supContactorId}, function(result) {
		var rs=result.split(",");
		if(rs[0]=='success'){
				cancelChoose();
				loadBidSup(rs[1]);
			}
		
	});	
	
}
 </script>
 