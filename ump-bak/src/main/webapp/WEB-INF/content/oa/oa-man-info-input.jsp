<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

   <s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
   <div id="idDivTitle1" class="title_bar1">
       <form action="oa-man-info!save.action" method="post" id="oaManInfoForm" >
          <input type="hidden" name="id" value="${oaManInfoId}"/>
          <input type="hidden" name="oaManInfoId" value="${oaManInfoId}"/>
		 <table style="width: 100%;">
		     <tr>
		     	<td>
		       <div class='noprint' id="funcPanelDiv" style="padding:5px;border-bottom:1px solid #8C8F94;">
		            <s:if test="statusCd==0 || statusCd==null">
		            <input type="button" value="保存"  class="btn_green_55_20" style="border:none;" onclick="doSave();" />
					<input type="button" value="申请"  class="btn_apply" style="border:none;" onclick="doApply();" />
					<input type="button" value="附件"  class="btn_green_55_20" style="border:none;" onclick="openAttachmentByModel('<s:text name="appAttachment.title"/>','${oaManInfoId==null?entityTmpId:oaManInfoId}','oaManInfo',''); return false;" />
					</s:if>
					<s:if test="statusCd==0">
					<input type="button" value="删除"  class="btn_green_55_20" style="border:none;" onclick="doDelete();" />
					</s:if>
					<input type="button" value="返回"  class="btn_green_55_20" style="border:none;" onclick="doReturn();" />	
					<input type = "hidden" value="${dictCd}" name="manInfoCd" id ="manInfoCd"/>
					<input type = "hidden" value="${entityTmpId}"  name="entityTmpId" id ="entityTmpId"/>
					<input type = "hidden" value="${proposalCatCd}"  name="proposalCatCd" id ="proposalCatCd"/>
		       </div>
		     	</td>
		     </tr>
		     <tr>
		   	  <td>
		        <div style="padding-top: 20px;" align="center" id="detailPanelDiv">
		             <span class="subject" > <p:code2name mapCodeName="mapDictCdType" value="proposalCatCd"/>  </span>
		             <table class="mainTable" style="width: 90%;margin-left: 20px;">
		                  <tr>
		                       <td width="20%" align="right">提案标题:</td>
		                       <td width="30%"><input type="text" id="remark" validate="required" name="remark" value="${remark}" /> </td>
		                       <td width="20%" align="right">建议人:</td>
		                       <s:if test="creator!=null">
		                       <s:set  var="tyUser">${creator}</s:set>
		                       </s:if>
		                       <s:else>
		                        <s:set  var="tyUser"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
		                       </s:else>
		                       <td width="30%"><input type="text"   value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("#tyUser")) %>"  readonly="readonly"  /></td>
		                  </tr>
		                  <tr>
		                       <td align="right">提案类别:</td>
		                       <td colspan="3"><p:code2name mapCodeName="mapDictCdType" value="proposalCatCd"/></td>
		                  </tr>
		                  <tr>
		                       <td align="right">提案概要说明:</td>
		                       <td colspan="3"><s:textarea id="proposalCatInfo" validate="required" name="proposalCatInfo"></s:textarea> </td>
		                  </tr>
		                  <tr>
		                       <td align="right">提案附件(明细):</td>
		                       <td colspan="3">
		                       <!-- 附件列表 -->
									<div class='noprint' id="attach_files_div" style="padding-left:5px;">
										
									</div>
		                       </td>
		                  </tr>
		                  <s:if test="isZcApprove==1 || statusCd==2">
		                  <tr>
		                       <td align="right">提案评级:</td>
		                       <td colspan="3"><s:select list="mapRatingCd" listKey="key" listValue="value" id="rating" name="rating" ></s:select></td>
		                  </tr>
		                  </s:if>
		                  <s:else>
		                  	 	<input type="hidden" name="rating" id="rating" value="${rating}"/>
		                  </s:else>
		                 
		             </table>
		        </div>
		        </td>
		     </tr>
		 </table>
		  </form>


		  <s:if test="(oaManInfoId != null && oaManInfoId != '')&&(statusCd != 0) ">
			  <div style="padding-left:5px;paclear:both;padding-top: 40px;">
				<div class="list_header"><span style="margin-left: 5px;">审批记录</span></div>
				<div style="background-color: infobackground;">
					<table class="content_table showTable" id="showTable" width="100%">
						<s:iterator value="oaManInfoNodes">
							<tr class="mainTr">
								<td style="padding-left:5px; width:50px;"><div>${userName}</div></td>
								<td style="padding-left:5px; width:150px;"><p:code2name mapCodeName="mapNode" value="nodeCd"/></td>
								<td style="padding-left:5px; width:50px;">
								
								<s:if test="groupNodeCd==39 || nodeCd==97">
									<p:code2name mapCodeName="mapOptionZyps" value="approveOptionCd"/>
								</s:if>
								<s:else>
									<p:code2name mapCodeName="mapOption" value="approveOptionCd"/>
								</s:else>
								</td>
								<td style="padding-left:5px;" title="${remark}">${remark}</td>
								<td style="padding-left:5px; width:85px;"><p:code2name mapCodeName="mapNode" value="groupNodeCd"/></td>
								<td style="padding-left:5px; width:120px;"><s:date name="approveDate" format="yyyy-MM-dd HH:mm" /></td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<s:if test="isMyApprove==1">
				<div style="margin-left: 5px;" class="noprint">
					<table width="100%" style="table-layout: auto;">
						<tr class="detailTr">
							<td style="color: #363636;" valign="top">
								<div style="padding-top:5px;">
								审批意见<span style="color:red;" >（请在这里填写有效的审核/审批意见）</span>:
								</div>
							</td>
							<td style="width:130px;">&nbsp;</td>
						</tr>
						<tr class="detailTr">
							<td style="padding: 5px;">
								<s:textarea cssStyle="width:100%;height:51px;" id="approveRemark"/>
							</td>
							<td style="width:130px;padding-left:10px;">
								
									<s:if test="myMod=='HQ'">
										<input class="btn_blue_55_55" type="button" value="完成审核"  class="btn_agree" style="border:none;" onclick="doComplete();" />
									</s:if>
									<s:elseif test="myMod=='publish'">
										<input class="btn_blue_55_55" type="button" value="发布完成"  class="btn_agree" style="border:none;" onclick="doComplete();" />
									</s:elseif>
									<s:else>
										<input type="hidden" name="signFile" id="signFile" value="${signFile}"/>
										<s:set name="agreeDisplay" value="'推荐'"/>
										<input class="btn_blue_55_55" type="button" value="${agreeDisplay}"  class="btn_agree" style="border:none;" onclick='doAgree("${nodeCd}","${curUserCd}");' />
										&nbsp;<input class="btn_red_55_55"  type="button" value="驳回"  class="btn_reject" style="border:none;" onclick="doBack();" />
									</s:else>
							</td>
						</tr>
					</table>
				</div>
				</s:if>	
			</div>
				
			<s:if test="statusCd!=null "> 
			<div class="noprint" style="padding-left:5px;">
				<div style="cursor:pointer;" onclick="$('#msgContent').toggle();" class="list_header" >
					<span style="margin-left: 5px;">留言记录</span>
				</div>
				<div id="msgContent" style="paddig:5px;">
					<jsp:include page="oa-man-info-say.jsp"></jsp:include>
				</div>
			</div>
			</s:if>
		
		</s:if>
	
	</div>
<!-- 加载附件列表 -->
	<script type="text/javascript"> 
		loadAttachment('${oaManInfoId}');
		
		
		function openAttachmentByModel(title, entityId, bizModuleCd,
				attachModelType) {
			if (typeof (bizModuleCd) == "undefined") {
				alert("bizModuleCd is null! ");
				return;
			}
			if (typeof (attachModelType) == "undefined") {
				alert("attachModelType is null! ");
				return;
			}
			//
			ymPrompt
					.alert( {
						message : "${ctx}/app/app-attachment!list.action?bizEntityId="
								+ entityId
								+ "&bizModuleCd="
								+ bizModuleCd
								+ "&filterType=image|office&bizEntityName=OaManInfo&attachModelType="
								+ attachModelType,
						width : 500,
						height : 300,
						title : title,
						iframe : true,
						handler : showAttach
					});
		}
		
		function loadAttachment(oaManInfoId){
			//$("#attach_files_div").empty();
		var data = {
				bizEntityId : oaManInfoId
			};
		$.post("${ctx}/app/app-attachment!show.action",data,
				function(data) {
					$("#attach_files_div").html(data);
				});
		}
		function showAttach() {
		loadAttachment('${oaManInfoId==null?entityTmpId:oaManInfoId}');
		}
		
		function doSave(){
			var pdv = new Validate("oaManInfoForm");
			if (pdv.validate()) {
			TB_showMaskLayer('正在执行...');
			$("#oaManInfoForm").ajaxSubmit(function(result) {
				 $("#content").html(result);
				 resetLeftPanel();
				 TB_removeMaskLayer();
			});	
	 	 }
		}
		
		function doReturn(){
			$.post("${ctx}/oa/oa-man-info!load.action",{
				dictCd:curAuthTypeCd,
				proposalCatCd:entityCd
				},
				function(result) {
				 	$("#content").html(result);
				 	resetLeftPanel();
			});
		}
		function doDelete(){
			ymPrompt.confirmInfo({message:'确认删除记录？',title:'删除',handler:function (tp){
				if (tp=='ok'){
					$.post("${ctx}/oa/oa-man-info!delete.action",{id:'${oaManInfoId}',manInfoCd:'${manInfoCd}'},
							 function(result) {
							 $("#content").html(result);
							 resetLeftPanel();
					});
				}
			}});
		}
		function doApply(){
			var pdv = new Validate("oaManInfoForm");
			if (pdv.validate()) {
			TB_showMaskLayer('正在执行...');
			$("#oaManInfoForm").attr("action","${ctx}/oa/oa-man-info!apply.action");
			$("#oaManInfoForm").ajaxSubmit(function(result) {
				 $("#content").html(result);
				 resetLeftPanel();
				 TB_removeMaskLayer();
			});
		 }
		}
        //同意
		function doAgree(nodeCd,curUserCd){
			var rating = $("#rating").val();
			if(curUserCd=="xuhf"){
				if(rating==""){
                     alert("请选择提案评级");
                     return;
					}
		     }
			doProcess("${ctx}/oa/oa-man-info!agree.action","同意");
	    }
	    //不同意
		function doBack(){
			var approveRemark=$("#approveRemark").val();
			if(isEmpty(approveRemark)){
				ymPrompt.alert({message:'请填写驳回原因!',title:"提示",width:220,height:180});
				return ;
			}
			process("${ctx}/oa/oa-man-info!back.action","驳回");
			TB_removeMaskLayer();
		}

		function doProcess(url,title,fn){
			var approveRemark=$("#approveRemark").val();
			if(approveRemark==''){
				ymPrompt.confirmInfo({message:'您的意见为空，将默认填写同意，请问是否继续？',title:title,handler:function (tp){
					if (tp=='ok'){
						if(title=="驳回"){
							$("#approveRemark").val('驳回');
						}else if(title=="同意"){
							$("#approveRemark").val('同意');
						}
						
						process(url,fn);
					}
				}});
			}else{
				process(url);
			}
		}

		function process(url,fn){
			TB_showMaskLayer('正在执行...');
			var data ={id:'${oaManInfoId}',
					approveRemark:$("#approveRemark").val(),
					rating:$("#rating").val(),
					dictCd:dictCdVal };
			
			//return false;
			$.post(url,data,
				function(result) {
					if(result=='fail'){
						ymPrompt.errorInfo({message:'操作失败,您无权处理该记录!',title:"提示",width:220,height:180});
					}else{
						$("#content").html(result);
					}
					if (fn) fn();
					resetLeftPanel();
					TB_removeMaskLayer();
			});
		}
		  
</script>
