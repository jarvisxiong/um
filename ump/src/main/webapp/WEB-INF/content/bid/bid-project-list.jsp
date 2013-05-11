<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<table style="width:100%" cellpadding="0" cellspacing="0" border="0">
	   	<tr>
		  	<td class="rowItem" colspan="6">
				<div class="headTitle">工程基本信息(<span id="numProjectCount"><s:property value="listProjects.size"/></span>)</div>
				<security:authorize ifAnyGranted="A_BID_EDIT,A_ADMIN_BID">
					<s:if test="bidLedger.bidStatusCd<=2">	       		   	  	
						<input type="button" class="fRight btn_new btn_add_new " id="btnAddProject" value="新增"/>
					</s:if>
				</security:authorize>
				<div id="succInfoId" class="fRight resultTip "></div>
		  	</td>
	   	</tr>
    	<tr id="divAddProject" style="display:none;background-color: #f7f7f7;">
	   		<td>
	           	<%-- 新增工程 --%>
	        	    <div>     	    
	        	        <form id="newProjectForm" action="${ctx}/bid/bid-project!save.action" method="post">
	        	        	<%-- 很重要! --%>
	        	            <input type="hidden" name="bidLedgerId" value="${bidLedgerId}"/>
							<div style="float: left;">
								<table class="editProTable" cellpadding="0" cellspacing="0" border="0">
										<col width="100px"/>
										<col/>
				         	            <tr>
				        	            	<th>工程名称:</th> 
				        	            	<td><input name="entity.projectName" style="width:500px" class="input_require"></input></td>
				         	            </tr>
				         	            <tr>
				         	            	<th>工程介绍:</th>  
				         	            	<td valign="top"><textarea rows="3" name="entity.projectDesc" style="width:500px;margin-top: 5px;" class="input_require"></textarea> </td>	
				         	            </tr>  
				         	            <%--
				         	            <tr>
				         	            	<th>显示序号:</th>  
				         	            	<td valign="top"><input type="text" alt="amount" name="entity.dispOrderNo"/></td>	
				         	            </tr>  
				         	             --%>
				         	             <security:authorize ifAnyGranted="A_BID_EDIT,A_ADMIN_BID">
					         	            <tr>
					         	            	<td></td>
					         	            	<td>
					         	            		<input type="button" class="btn_new btn_blue_new" onclick="saveNewBidProject('${bidLedgerId}')" value="保存"/>
					         	            		<input type="button" class="btn_new btn_cancel_new"  onclick="$('#btnAddProject').trigger('click');" value="取消"/>
					         	            	</td>
					         	            </tr>
				         	            </security:authorize>            	            	
		        	            </table>
							</div>
	        	        </form>
	       	    </div>
	   		</td>
		</tr>
		<tr>
			<td>
				<table class="commonTable" style="margin:0;">                       
						<col width="40"/>
						<col width="150"/>
						<col/>
						<col width="50"/>
						<col width="60"/>
					<thead>
					<tr>
						<th class="first">序号</th>
						<th>工程项目(工程名目)</th>
						<th>工程介绍</th>
						<th>附件</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody>
					<s:iterator value="listProjects" var="vo"  status="status">
					
						<tr class="projectBrief">
						    <td style="text-align: center;" <s:if test="#status.last"> class='lastRowTd' </s:if>><s:property value="#status.index+1"/></td>
							<td editable="true" <s:if test="#status.last"> class='lastRowTd' </s:if>>                
							 	<textarea name="projectName"
							 		 onblur="updateBidProject('${vo.bidProjectId}','projectName',this);"
							 	 >${vo.projectName}</textarea>
							</td>
							<td editable="true" <s:if test="#status.index==listProjects.size()-1"> class='lastRowTd' </s:if>>
								<textarea name="projectDesc" onblur="updateBidProject('${vo.bidProjectId}','projectDesc',this);">${vo.projectDesc}</textarea>
							</td>
							<td valign="middle" <s:if test="#status.index==listProjects.size()-1"> class='lastRowTd' </s:if>>
							<%--
							<div style="float:left;" onclick="showAttachment('${bidPro.bidProjectId}');">
							查看
							</div>
							--%>
							<div onclick='openAttachmentRefresh($(this),"${vo.bidProjectId}","bidProject",true)'
								<s:if test="attachFlg == 1">
								class="hasAttachFile"
								title="已上传附件"
								</s:if>
								<s:else>
								class="noAttachFile"
								title="无附件"
								</s:else>
							>
							&nbsp;&nbsp;
							</div>
						</td>
						<td <s:if test="status.last"> class='lastRowTd' </s:if>>
							<%--如果已经有数据就不能再删除 --%>
							<%--进入邀标阶段后就不能删除 --%>		
							<s:if test="bidLedger.bidStatusCd > 0">
							<%-- <div class='plgray' >删除</div>--%>	
							</s:if>
							<s:else>
								<div class="btn_new btn_del_new" onclick="doDeleteProject('${vo.bidProjectId}','${vo.projectName}',this,'${bidLedgerId}');">删除</div>
							</s:else>
						</td>
					</tr>
					</s:iterator>
					</tbody>
				</table> 
	           	</td>
	        </tr>
 </table>
  <input type="hidden" name="supCnt" id="hidden_projectCnts" value="<s:property value="listProjects.size"/>"></input>  			
<script language="javascript">
	$(function(){
		$('#projectListPanel input[alt="amount"]').live('keyup',function(){
			clearNoNum_2(this);
		});
		//点击"新增"行时事件，显示/隐藏新增表单
		$('#btnAddProject').toggle(
			function(){
				$("#divAddProject").show();
			},function(){
				$("#divAddProject").hide();
			}
		);
		
		$('#projectListPanel td[editable=true] textarea').each(function(){
			var oldVal = $(this).html();
			if($.trim(oldVal) == ''){
				oldVal = '&nbsp;';
			}
			// 插入显示栏,同时注册事件
			$(this).before('<div style="width:100%;" class="pd-chill-tip">'+ oldVal +'</div>').height($(this).height()).hide();
			<security:authorize ifAnyGranted="A_BID_BASIC_EDIT,A_ADMIN_BID">
			$(this).prev().css('cursor','pointer').attr('title','点击编辑').click(function(){
				$(this).hide().next().show();
			});
			</security:authorize>
		});
	 	 
	});

</script>