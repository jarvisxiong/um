<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.hhz.ump.util.DictMapUtil" %>


<style>
	th{
		padding:2px;
		text-align: right;
		font-weight: normal;
	}
	
	div div.item{
		font-weight:bold;
		font-size:13px;
		line-height: 30px;
		float:left;
		margin-left:40px;
	}
</style>

<div style="clear:both;">
<form id="listForm" action="ctdb-list-content!save.action" method="post" accept-charset="utf-8">
	<%--如果是修改，则隐藏主键listContentId --%>
	<s:if test="entity!=null">
		<input type="hidden" name="listId" value="${entity.listContentId}"></input>
	</s:if>
	<div class="item">
		<div>新增清单数据
		<s:if test="entity!=null">
			<s:if test="entity.statusCd==0">当前状态: [暂存]</s:if>
			<s:elseif test="entity.statusCd==1">当前状态: [待确认]</s:elseif>
			<s:elseif test="entity.statusCd==2">当前状态: [已确认]</s:elseif>			
			<s:elseif test="entity.statusCd==3">当前状态: [驳回]</s:elseif>			
		</s:if>		
		</div>
	</div>
	<div class="firstPart" style="background-color: none;clear: both;">
		<table width="100%;">
			<colgroup>
				<col width="100px"></col>
				<col></col>
				<col width="120px"></col>
				<col></col>
				<col width="80px"></col>
				<col></col>
			</colgroup>
			
			<tbody>
				<tr>
					<th ><font color="red">*</font>数据大类:</th>
					<td >
					<%--<input name="1" style="width: 90%;"></input> --%>
					<select name="firstCat" 
					style="width: 92%;" 
					<s:if test="formType>0">onchange="firstCatChange2(this);"</s:if>
					<s:else>onchange="firstCatChange(this);"</s:else>						
					<s:if test="entity.statusCd ==2">
						<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
					</s:if>
					<s:elseif test="entity.statusCd ==1">
					 disabled="true"
					</s:elseif>
					 >
						<option value="">-</option>
						<option value="CTDB_CONSTRUCTION_CATE" 
							<s:if test='entity!=null&&entity.firstCat=="CTDB_CONSTRUCTION_CATE"'>
							selected="true"
							</s:if>
						>土建</option>
						<option value="CTDB_INSTALLATION_CATE"
							<s:if test='entity!=null&&entity.firstCat=="CTDB_INSTALLATION_CATE"'>
							selected="true"
							</s:if>
						>安装</option>
					</select>
					</td>
					<th ><font color="red">*</font>小类:</th>
					<td colspan="3">	
						<s:if test="entity!=null">
							<select name="secCat"
							 style="width: 96%;" 
							 <s:if test="entity.statusCd ==2">
								<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
							</s:if>
							<s:elseif test="entity.statusCd ==1">
							 	 disabled="true"	
							</s:elseif>
							 id="<s:if test="formType>0">secCat2</s:if><s:else>secCat</s:else>">
								<s:iterator value="secOndCats" var="sec">									
										<option value="${key }" 
										<s:if test='entity!=null&&entity.secCate==key'>
											selected="true"
											</s:if>
										> ${value}</option>
								</s:iterator>
							</select>
						</s:if>
						<s:else>
							<select name="secCat"
									style="width: 96%;" 
									<s:if test="entity.statusCd ==2">
										<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
									</s:if>
									<s:elseif test="entity.statusCd ==1">
									 	 disabled="true"	
									</s:elseif>
									id="<s:if test="formType>0">secCat2</s:if><s:else>secCat</s:else>">
								<option>-</option>
								<option></option>
							</select>
						</s:else>					
					</td>
					
				</tr>
				<tr>
					<th ><font color="red">*</font>清单编号:</th>
					<td>
						<input name="entity.listCd"
							<s:if test="entity.statusCd ==2">
								<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
							</s:if>
							<s:elseif test="entity.statusCd ==1">
							 	 disabled="true" 
							</s:elseif> 
						  id="listCd" value="${entity.listCd}" style="width: 90%;" type="text"></input>
					</td>
					<th ><font color="red">*</font>清单名称:</th>
					<td colspan="3">
					<input name="entity.listName" 
							<s:if test="entity.statusCd ==2">
								<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
							</s:if>
							<s:elseif test="entity.statusCd ==1">
							 	 disabled="true"
							</s:elseif> 
						id="listName" value="${entity.listName}"  style="width: 95%;" type="text"></input>
					</td>
					
				</tr>
				<tr>
					<th ><font color="red">*</font>说明:</th>
					<td colspan="5">
						<input name="entity.listDesc" 
							<s:if test="entity.statusCd ==2">
								<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
							</s:if>
							<s:elseif test="entity.statusCd ==1">
							 	 disabled="true"
							</s:elseif> 
							id="listDesc" value="${entity.listDesc}"  style="width: 97%;" type="text"></input>
					</td>
				</tr>
				<tr>
					<th><font color="red">*</font>数量:</th>
					<td>
						<input name="entity.qty" 
								<s:if test="entity.statusCd ==2">
									<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
								</s:if>
								<s:elseif test="entity.statusCd ==1">
								 	 disabled="true"
								</s:elseif> 
						value="${entity.qty}" id="qty"  style="width: 90%;"  onkeyup="clearNoNum_1(this);" onblur="formatVal($(this));" type="text"></input>
					</td>
					<th  ><font color="red">*</font>单位:</th>
					<td>
						<input name="entity.measurement" 
							  <s:if test="entity.statusCd ==2">
									<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
								</s:if>
								<s:elseif test="entity.statusCd ==1">
								 	 disabled="true" 
								</s:elseif>
							  value="${entity.measurement}" id="measurement"  style="width: 90%;" type="text"></input>
					</td>
					<th ><font color="red">*</font>单价(元):</th>
					<td>
						<input name="entity.unitPrice" 
								<s:if test="entity.statusCd ==2">
									<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
								</s:if>
								<s:elseif test="entity.statusCd ==1">
								 	 disabled="true"
								</s:elseif>
						value="${entity.unitPrice}" id="unitPrice"  style="width: 88%;"  onkeyup="clearNoNum_1(this);" onblur="formatVal($(this));"  type="text"></input>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="item">
		<div>单价组价明细</div>
	</div>
	<div class="firstPart" style="clear: left;">
		<table  width="100%;">
			<colgroup>
				<col width="100px"></col>
				<col></col>
				<col width="120px"></col>
				<col></col>
				<col width="80px"></col>
				<col></col>
			</colgroup>
			<tbody>
				<tr>
					<th >人工费(元):</th>
					<td>
						<input name="entity.personalPrice" 
								<s:if test="entity.statusCd ==2">
									<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
								</s:if>
								<s:elseif test="entity.statusCd ==1">
								 	 disabled="true"
								</s:elseif>
							  value="${entity.personalPrice}" style="width: 90%;"  onkeyup="clearNoNum_1(this);" id="personalPrice" type="text"></input>
					</td>
					<th >主材费(元):</th>
					<td >
						<input name="entity.mainMatePrice" 
								<s:if test="entity.statusCd ==2">
									<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
								</s:if>
								<s:elseif test="entity.statusCd ==1">
								 	 disabled="true"
								</s:elseif>
							  value="${entity.mainMatePrice}" style="width: 90%;"  onkeyup="clearNoNum_1(this);" id="mainMatePrice" type="text"></input>
					</td>
					<th>辅材费(元):</th>
					<td >
						<input name="entity.aidMatePrice" 
								<s:if test="entity.statusCd ==2">
									<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
								</s:if>
								<s:elseif test="entity.statusCd ==1">
								 	 disabled="true"
								</s:elseif>
						value="${entity.aidMatePrice}" style="width: 88%;"  onkeyup="clearNoNum_1(this);" id="aidMatePrice" type="text"></input>
					</td>
				</tr>
				<tr>
					<th>机械费(元):</th>
					<td>
						<input name="entity.machinePrice" 
								<s:if test="entity.statusCd ==2">
									<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
								</s:if>
								<s:elseif test="entity.statusCd ==1">
								 	 disabled="true" 
								</s:elseif> 
								value="${entity.machinePrice}" style="width: 90%;"  onkeyup="clearNoNum_1(this);" id="machinePrice" type="text"></input>
						</td>
					<th>管理费及利润(元):</th>
					<td>
						<input name="entity.manageFees" 
								<s:if test="entity.statusCd ==2">
									<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
								</s:if>
								<s:elseif test="entity.statusCd ==1">
								 	 disabled="true"
								</s:elseif>
								value="${entity.manageFees}" style="width: 90%;" onkeyup="clearNoNum_1(this);" id="manageFees" type="text"></input>
					</td>
					<th>税金(元):</th>
					<td>
						<input name="entity.tax" 
								<s:if test="entity.statusCd ==2">
									<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
								</s:if>
								<s:elseif test="entity.statusCd ==1">
								 	 disabled="true"
								</s:elseif>
						 value="${entity.tax}" style="width: 88%;" onkeyup="clearNoNum_1(this);" id="tax" type="text"></input>
					</td>
				</tr>
				<tr>
					<th ><font color="red">*</font>主材耗损率:</th>
					<td colspan="5">
						<input name="entity.mainMateRate" 
								<s:if test="entity.statusCd ==2">
									<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
								</s:if>
								<s:elseif test="entity.statusCd ==1">
								 	 disabled="true"
								</s:elseif>
						 		value="${entity.mainMateRate}" id="mainMateRate"  style="width: 97%;" onkeyup="clearNoNum_1(this);"  type="text"></input>
					</td>
				</tr>
				<tr>
					<th ><font color="red"></font>价格来源:</th>
					<td colspan="5">
							<input name="entity.priceFrom" 
									<s:if test="entity.statusCd ==2">
										<security:authorize ifNotGranted="A_COST_CTDB_REDIT"> disabled="true" </security:authorize>				 	
									</s:if>
									<s:elseif test="entity.statusCd ==1">
									 	 disabled="true"
									</s:elseif>
									value="${entity.priceFrom}" id="priceFrom"  style="width: 97%;"  type="text" ></input>
						</td>
				</tr>
				<s:if test="entity!=null">
				<tr>
					<th ><font color="red"></font>附件信息:</th>
					<td colspan="5">
						<div 							
						<security:authorize ifAnyGranted="A_COST_CTDB_EDIT">
								<s:if test="entity.statusCd==0||entity.statusCd==3">
									onclick="openAttachmentRefresh($(this),'${entity.listContentId}','ctdblistContent',false);"								
								</s:if>
								<s:else>
									onclick="openAttachmentRefresh($(this),'${entity.listContentId}','ctdblistContent',true);"	
								</s:else>
						</security:authorize>
						<security:authorize ifNotGranted="A_COST_CTDB_EDIT">
							<security:authorize ifAnyGranted="A_COST_CTDB_REDIT">
								<s:if test="entity.statusCd==2">
									onclick="openAttachmentRefresh($(this),'${entity.listContentId}','ctdblistContent',false);"
								</s:if>
							</security:authorize>	
						</security:authorize>						 
						<s:if test="entity!=null&&entity.attachFlg==1">
							class="hasAttachFile"
							title="已上传附件"
						</s:if>
						<s:else>
							class="noAttachFile"
							title="请上传附件"
						</s:else>						
					>&nbsp;</div>
				</td>
			</tr>
			</s:if>
			<tr>
				<td></td>
				<td colspan="4">
					<s:if test="entity!=null">
						<%--如果状态为0或1,新增，或暂存 ,显示修改按钮--%>
						<s:if test="entity.statusCd==0">
							<security:authorize ifAnyGranted="A_COST_CTDB_EDIT">
								<input type="button" onclick="editCtdbListContent(this);" value="修改" class="btn_new btn_green_new"/>													
								<input type="button" onclick="commitList('1','${entity.listContentId}','${pageNo}');" value="提交" class="btn_new btn_save_new"/>
								<input type="button" onclick="delteList('${entity.listContentId}','${pageNo}');" value="删除" class="btn_new btn_del_new"/>
							</security:authorize>
							<input type="button" onclick="backListContent('${pageNo}');" value="返回" class="btn_new btn_green_new"/>			
						</s:if>
						<s:elseif test="entity.statusCd==1">
							<security:authorize ifAnyGranted="A_COST_CTDB_EDIT">
								<input type="button" onclick="delteList('${entity.listContentId}','${pageNo}');" value="删除" class="btn_new btn_del_new" />
							</security:authorize>
							<security:authorize ifAnyGranted="A_COST_CTDB_CHK">
								<input type="button" onclick="reject('3','${entity.listContentId}','${pageNo}');" value="驳回" class="btn_new btn_red_new"/>																
								<input type="button" onclick="commitList('2','${entity.listContentId}','${pageNo}');" value="确认" class="btn_new btn_save_new"/>
							</security:authorize>
							<input type="button" onclick="backListContent('${pageNo}');" value="返回" class="btn_new btn_green_new" />	
						</s:elseif>		
						<s:elseif test="entity.statusCd==2">
							<security:authorize ifAnyGranted="A_COST_CTDB_REDIT">
								<input type="button" onclick="editCtdbListContent(this);" value="修改" class="btn_new btn_green_new" />	
								<input type="button" onclick="reject('3','${entity.listContentId}','${pageNo}');" value="驳回" class="btn_new btn_red_new" />
																						
							</security:authorize>
								<input type="button" onclick="backListContent('${pageNo}');"value="返回" class="btn_new btn_green_new" />	
						</s:elseif>		
						<s:elseif test="entity.statusCd==3">
							<div >
								<security:authorize ifAnyGranted="A_COST_CTDB_EDIT,A_COST_CTDB_REDIT">
									<input type="button" onclick="editCtdbListContent(this);" value="修改" class="btn_new btn_green_new" />													
									<input type="button" onclick="commitList('1','${entity.listContentId}','${pageNo}');" value="提交" class="btn_new btn_save_new" />
									<input type="button" onclick="delteList('${entity.listContentId}','${pageNo}');" value="删除" class="btn_new btn_red_new" />
								</security:authorize>
								<input type="button" onclick="backListContent('${pageNo}');" value="返回" class="btn_new btn_green_new"/>		
							</div>	
						</s:elseif>		
					</s:if>
					<s:else>
						<security:authorize ifAnyGranted="A_COST_CTDB_EDIT">
							<input type="button" onclick="saveCtdbListContent(this);" value="保存" class="btn_new btn_save_new fLeft"/>
						</security:authorize>						
					</s:else>
				</td>
			</tr>
		</table>
	</div>
</form>
</div>