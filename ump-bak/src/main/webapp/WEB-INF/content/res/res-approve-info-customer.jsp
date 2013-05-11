<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
	<form action="res-approve-info!submit.action" method="post" id="form_user" >
		<input type="hidden" name="id" id="resApproveInfoId_c" value="${resApproveInfoId}" />
		<input type="hidden" name="recordVersion" id="recordVersion_c" value="${recordVersion}" />
	<div class="clear" style="margin:20px;text-align: left;">
	
	<s:if test="approveNodes.size>0"> 
		<div>
		<table class="tb_approver noprint" cellspacing="0" cellpadding="0" >
				<col width="180">
				<col width="200">
				<tr>
				<td colspan="4" align="center">
				<span style="margin:20px;text-align: left;color: red;">如需修改表单，请点击‘编辑’按钮</span>
				</td>
				</tr>
	    <s:iterator value="approveNodes" status="i">
	       <!--  <input id="approveRank" type="hidden" name="approveRank" value="${approveRank}" />
		     <script type="text/javascript">
		        if($("#approveRank").val()==1){ 
		        	$("#nodecd").attr("class","approver");
		        	$("#autoapprover").attr("class","inputBorderApprover");
		      
			     }else{
			    	 $("#nodecd").attr("class","approver2");
			    	 $("#autoapprover").attr("class","inputBorderApprover2");
				 }       
	         </script>
	         -->
  			<tr>
  			<td id="nodecd" class="approver" colspan="2">
	  			
	  			<div style="overflow-x:hidden;">
	  			<div class="level${approveRank }">
				    <%=CodeNameUtil.getResNodeNameByCd(JspUtil.findString("nodeCd")) %>:
	
					<s:set var="isMuti" value="false"></s:set>
					<s:set var="isLock" value="mapNodeLock[nodeCd]"/>
					<s:if test="userName==null || userName!=null && !#isLock">
					<s:set var="tmpUserCd2" value="mapCustomSteps[nodeCd]"/>
					<s:set var="isMuti" value="%{isMutiNode(nodeCd)}"></s:set>
					
					<s:if test="isMuti" >
					    <input id="${i.index}Name" class="inputBorderApprover mutiSelect_user" type="text" validate="required" readonly="readonly" value="${userName}"/>
					</s:if>
					<s:else>
					  <input id="${i.index}Name" class="inputBorderApprover singleSelect_user" ref="${nodeCd}Name" type="text" validate="required" readonly="readonly" value="${userName}"/>
		        	</s:else>
					<input id="${i.index}Cd" ref="${nodeCd}" type="hidden" name="templateBean.mapCustomNode[${nodeCd}]" value="${userCd}"  value2="${tmpUserCd2}"/>
					</s:if>
					<s:else>
					   <input id="autoapprover" class="inputBorderApprover" style="background-color: #DDDBDC;" type="text" readonly="readonly" value="${userName}"/>
					</s:else>
				</div>
				</div>
			</td>
			<td width="5">
				<s:if test="isMuti" >
				*
				</s:if>
				
			</td>
			<td>
				<s:if test="groupNodeCd!=null">
				<span><%=CodeNameUtil.getResNodeNameByCd(JspUtil.findString("groupNodeCd")) %> </span>
				</s:if>
			</td>
			
			</tr>
		</s:iterator>
		</table>
		</div>
	</s:if>
	</div>
	</form>
	<script type="text/javascript">
		
		try{
			$('.singleSelect_user').userSelect({
				muti:false,
				callback:function(map,dom){
					userCallback(dom);
				}
			});
			$('.mutiSelect_user').userSelect({
				muti:true,
				callback:function(map,dom){
					userCallback(dom);
				}
			});

			//更改弹出框位置,lujy,20120706
			$(".jqmDialog").css("marginTop","300px");
		}catch(e){

		}
		function disableSame(){
			$(".inputBorderApprover[ref]").each(function(){
				userCallback(this);
			});
		}
		function userCallback(dom){
			var _dom=$(dom);
			var ref2=_dom.attr('ref2');
			if (ref2 == '1'){
				return ;
			}
			var _domCd=_dom.next();
			var name=_dom.val();
			var cd=_domCd.val();
			name=isEmpty(name)?'':name;
			cd=isEmpty(cd)?'':cd;
			var nameId=_dom.attr('id');
			var cdId=_domCd.attr('id');
			var refName=_dom.attr('ref');
			var refCd=_domCd.attr('ref');
			$("input[ref='"+refName+"'][id!='"+nameId+"']").val(name).css('background-color','#DDDBDC').unbind('click').attr('ref2','1');
			$("input[ref='"+refCd+"'][id!='"+cdId+"']").val(cd).removeAttr('name');
			var value2=_domCd.attr("value2");
			if (_domCd.val().indexOf(value2)==-1){
				_dom.css("color","black");
				_dom.css("border","2px solid orange");
				_dom.attr("title","选择的人员与默认不匹配");
			}else{
				_dom.css("color","");
				_dom.css("border","");
				_dom.removeAttr("title");
			}
			
		}
		disableSame();
	</script>