<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--考察申请表--%>
<%@ include file="template-header.jsp"%>


<%@page import="com.hhz.ump.util.DictContants"%>
<div align="center" class="billContent">
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
	</s:set>
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="80"/>
			<col/>
			<col/>
			<col/>
		
			<tr>
				<td class="td_title">项目</td>
				<td style="text-align:left">
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" 
					id="projectName" readonly="readonly"   <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else> />
				<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
			
				</td>
				<td class="td_title">考察供方类别</td>
				<td  style="text-align:left">
				<input class="inputBorder" validate="required" type="text"   name="templateBean.reviewSupportType"  value="${templateBean.reviewSupportType}" />
				</td>
			</tr>
				<tr>
				<td class="td_title">考察要求</td>
				<td colspan="3" style="text-align:left">
				<textarea rows="5" cols="10" class="inputBorder" validate="required"  name="templateBean.reviewRequirement" >${templateBean.reviewRequirement}</textarea>
			</td>
			</tr>
		
			<tr>
				<td class="td_title">考察时间</td>
				<td colspan="3" style="text-align:left">
				<input class="inputBorder" validate="required" type="text" onfocus="WdatePicker()" name="templateBean.reviewDate" value="${templateBean.reviewDate}" style="width:100px;" />
				</td>
			</tr>
			<tr>
				<td class="td_title">考察部门及参与人员</td>
				<td colspan="3">
				<table id="tbConItem" style="margin-top: 5px;" width="100%">
							<col>
							<col>
							<col>		
							<col></col>				
							<tr>
							    <td align="center">姓名</td>
								<td align="center">部门</td>								
								<td align="center">职务</td>
								<td align="center" width="40px">操作</td>
							
							</tr>
							<s:if test="statusCd==0 || statusCd==3">
							<tr id="trConItem" style="display: none;margin-bottom:5px;">
								
								<td style="text-align:left">
									<input validate="required"  ctr="person" index='0' class="inputBorder" id="person0" type="text" name="templateBean.reviewDeptAndPersons[0].firstName" readonly="readonly" />
							     <input validate="required"  class="inputBorder" ctr="uiid" id='uiid0' type="hidden" name="templateBean.reviewDeptAndPersons[0].userCd" />								
								</td>
								<td class="tdGroupNodes" style="text-align:left">		
									<input validate="required" class="inputBorder"  ctr="dept"   id="dept0" type="text" name="templateBean.reviewDeptAndPersons[0].department" readonly="readonly" />
							     </td>									
								<td>
									<input validate="required" class="inputBorder" ctr="post" id="post0" type="text" name="templateBean.reviewDeptAndPersons[0].post" readonly="readonly" />
								<input type="hidden"  id="postid0" class="inputBorder"  ctr="postid"  name="templateBean.reviewDeptAndPersons[0].posCd"/>
								</td>
								<td width="40px;text-align:ceter;"><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/></a></td>
							</tr>
							</s:if>							
							<s:set var="conItemCount"><s:property value="templateBean.reviewDeptAndPersons.size()"/></s:set>
							<s:iterator value="templateBean.reviewDeptAndPersons" var="item" status="s">
								<tr style="margin-bottom:5px;">
								
									<td class="tdGroupNodes" style="text-align:left">
									  <input validate="required"  index="<s:property value="#s.index" />" class="inputBorder" ctr="person" id='person<s:property value="#s.index" />'  readonly="readonly"  type="text" name="templateBean.reviewDeptAndPersons[<s:property value="#s.index" />].firstName" value="${item.firstName}"/>
								    
								 	  <input validate="required"  index="<s:property value="#s.index" />" class="inputBorder" ctr="uiid" id='uiid<s:property value="#s.index" />'  readonly="readonly" type="hidden" name="templateBean.reviewDeptAndPersons[<s:property value="#s.index"/>].userCd" value="${item.userCd}"/>
							
								   </td>
								<td class="tdGroupNodes" style="text-align:left">
									<input validate="required" class="inputBorder" ctr="dept" id="dept<s:property value="#s.index" />" type="text" name="templateBean.reviewDeptAndPersons[<s:property value="#s.index" />].department" readonly="readonly"  value="${item.department}"/>
								</td>
							
								<td class="tdGroupNodes" style="text-align:left">
									<input validate="required" class="inputBorder" ctr="post" id="post<s:property value="#s.index" />" type="text" name="templateBean.reviewDeptAndPersons[<s:property value="#s.index" />].post" readonly="readonly"  value="${item.post}"/>
								<input   id="postid<s:property value="#s.index" />" class="inputBorder"  ctr="postid"  type="hidden" name="templateBean.reviewDeptAndPersons[<s:property value="#s.index" />].posCd" value="${item.posCd}"/></td>							
									
							<td width="40px;text-align:center;">
								
								<s:if test="statusCd==0 || statusCd==3">
								     <a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/></a>
									</s:if>
									</td>
							</s:iterator>
						</table>
				</td>
			</tr>
			<tr>
				<td class="td_title" colspan="4" style="text-align:center">
					<s:if test="statusCd==0 || statusCd==3">
							<input type="button"  class="btn_blue_75_55" value="增加人员" onclick="addItem();" />
			       </s:if>
			     </td>
		     </tr>			
			<tr>
				<td class="td_title" colspan="4"  style="text-align:center">考察安排</td>				
			</tr>
			<tr>			
				<td colspan="4">
				<table id="tbConItem1" style="margin-top: 5px;" width="100%">
							<col>
							<col>
							<col>		
							<col></col>				
					<tr>
						<td align="center" width="30px">序号</td>
						<td align="center" colspan="2">单位</td>
						<td align="center">所属区域</td>
						<td align="center" width="80px">操作</td>
					
					</tr>
					<s:if test="statusCd==0 || statusCd==3">
					<tr id="trConItem1" style="display: none;margin-bottom:5px;">						
						<td class="tdGroupNodes" style="text-align:center" ctr="seri">
					    1	
					    </td>						
				       	<td colspan="2" style="text-align:left">			
							<input type="hidden" class="sid" id="supBasicId" name="templateBean.reviewArranges[0].supBasicId">
							<input class="inputBorder supName" validate="required" id="unitName" type="text" name="templateBean.reviewArranges[0].unitName"/>
					     </td>
					    <td style="text-align:left">
						<input validate="required" class="inputBorder" type="text" name="templateBean.reviewArranges[0].blongArea"/>
			        	</td>
					   <td style="width:80px;text-align:left;" >
 
					     <span onclick="getSup(this);" class="link"  style="poniter:cursor;">打开 </span>
					     <a href="javascript:void(0)"  onclick="delArrItem(this)" >
					     <img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/>
					     </a> 
					  
					   
					   </td>
					 </tr>
					</s:if>				
					<s:set var="conArrItemCount1"><s:property value="templateBean.reviewArranges.size()"/></s:set>
					<s:iterator value="templateBean.reviewArranges" var="arrItem" status="s1">
					<tr style="margin-bottom:5px;">
						
							<td class="tdGroupNodes" style="text-align:center" ctr="seri">
							   <s:property value="#s1.index+1" />
						    </td>		    
						    <td class="tdGroupNodes" colspan="2" style="text-align:left">
								<input type="hidden" class="sid" id="supBasicId<s:property value="#s1.index" />" name="templateBean.reviewArranges[<s:property value="#s.index" />].supBasicId" value="${arrItem.supBasicId}">
								<input class="inputBorder supName" validate="required" id="unitName<s:property value="#s1.index" />" type="text" name="templateBean.reviewArranges[<s:property value="#s1.index" />].unitName" value="${arrItem.unitName}"/>
							</td>
							<td class="tdGroupNodes" style="text-align:left">
								<input validate="required" class="inputBorder" type="text" name="templateBean.reviewArranges[<s:property value="#s1.index" />].blongArea" value="${arrItem.blongArea}"/>
							</td>
							
							
							  <td style="width:80px;text-align:left;" >
 
							     <span onclick="getSup(this);" class="link"  style="poniter:cursor;">打开 </span>
							     <s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
										
										
							     <a href="javascript:void(0)"  onclick="delArrItem(this)" >
							     <img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/>
							     </a> 
							    </s:if>
					   
					   </td>
							
					</tr>						
								
					<script type="text/javascript">
				
					var indexTmp='${s1.index}';
					
					var ids={sid:'supBasicId'+indexTmp,sname:'unitName'+indexTmp};
		
					$("#unitName"+indexTmp).quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],ids,{supStatus:'0'});
					</script>
					
				</s:iterator>
				</table>
				</td>
			</tr>			
			</table>
			
			<s:if test="statusCd==0 || statusCd==3">
			
							<input type="button"  class="btn_blue_75_55" value="增加单位" onclick="addArrItem();" />
		</s:if>			
		
		<%@ include file="template-approver.jsp"%>
		
	
	</form>
</div>

<%@ include file="template-footer.jsp"%>



<script type="text/javascript">

	var trApprover=$("#trConItem").clone();
	var trArrItem=$("#trConItem1").clone();//单位TR
	$("#trConItem").remove();
	$("#trConItem1").remove();
	
	var cloneCount = 0;
	var cloneArrCount=0;//克隆TR计数器
	var index=0;
	var arraIndex=0;
	
	try{
		index = Number("${conItemCount}");
		arraIndex=Number("${conArrItemCount1}");//获取单位控件的个数
	}catch(e){}
	//增加用户
	function addItem(){

		var trApprover_new=trApprover.clone();
		cloneCount++;
		index++;
		$(trApprover_new).show();
     	var iIndex=(index+1);//控件号
		$(trApprover_new).find(".inputBorder").each(function(i){			
			this.name=this.name.replace('0',index);		
			if($(this).attr("ctr") == "person")
			{
               $(this).attr("index",iIndex);
			}	
			
			
			$(this).attr("id",$(this).attr("ctr")+(iIndex));			
		});		

		$("#tbConItem").append(trApprover_new);
		$("#person"+iIndex+"").userSelect({muti:false,sysPosEId:'postid'+iIndex,sysPosName:'post'+iIndex,sysOrgName:'dept'+iIndex,cdField:'uiid'+iIndex});
		
	}
//增加单位
function addArrItem(){
	
		var trArra_new=trArrItem.clone();
		cloneArrCount++;
		$(trArra_new).show();
		$(trArra_new).find(".inputBorder").each(function(i){
			this.name=this.name.replace('0',arraIndex);
			if (this.id){
				this.id=this.id+arraIndex;
				
			}
		});	
		var domSidId=$(trArra_new).find(".sid").attr('id');
		$(trArra_new).find(".sid").attr('id',domSidId+arraIndex);
		var domSidName=$(trArra_new).find(".sid").attr('name');
		$(trArra_new).find(".sid").attr('name',domSidName.replace('0',arraIndex));
		var ids={sid:"supBasicId"+arraIndex,sname:'unitName'+arraIndex,grade:'unitLevel'+arraIndex};

		$(trArra_new).find(".tdGroupNodes").each(function(i){
			$(this).html($("td[ctr=seri]").length+1);
		});
		$("#tbConItem1").append(trArra_new);
	
		$("#unitName"+arraIndex).quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],ids,{supStatus:'0'});
		arraIndex++;
	}
     //删除用户
	function delItem(dom){
		$(dom).parent().parent().remove();
	}
	//删除单位
	function delArrItem(dom)
	{			
	  $(dom).parent().parent().remove();
	 $.each($("td[ctr=seri]"),function(i,item){
		  $(item).html(i+1);
		  });		
	}

    //打开  
	function getSup(dom){
	
		var sid=$(dom).parent().parent().find(".sid").val();
	
		var supName=$(dom).parent().parent().find(".supName").val();
	
		var url='';
		if(isNotEmpty(sid)){
			url="${ctx}/sup/sup-basic!input.action?id="+sid;
		}else if(isNotEmpty(supName)){
			url="${ctx}/sup/sup-basic!input.action?sName="+supName;
		}	
		if (url!=''){
		
			if(parent.TabUtils==null){
			 window.open(url);
			}else{  
			  parent.TabUtils.newTab("supQuery","明细",url,true);
			}
		}
	}
	
</script>
<!-- 默认1条申请记录 -->
<s:if test="#canEdit== 'true'">
<script>

/**
绑定参与人员姓名事件
**/
$.each($("input[ctr=person]"),function(i,item){
	var iIndex=$(item).attr("index");
	

var	_userMap = {};
         //定义用户数据格式用于点击文本框时默认选中已选件用户	
var o = {
				orgName:$("#dept"+i).val(),
				userName:$(item).val(),
				uiid:$("#uiid"+i).val(),
				sysPosName:$("#post"+i).val(),
				sysPosCd:$("#postid").val()
		};
		_userMap[$("#uiid"+i).val()] = o;
		var data = $.extend(true,{},_userMap);
		$(item).data('userMap',data);
	
	$(item).userSelect({muti:false,sysPosEId:'postid'+i,sysPosName:'post'+i,sysOrgName:'dept'+i,cdField:'uiid'+i});
});

</script>

</s:if>
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
	addArrItem();

	
</script>
</s:if>
