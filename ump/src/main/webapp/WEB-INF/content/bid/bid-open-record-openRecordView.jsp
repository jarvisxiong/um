<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

	
		<div id="bidLedgerDetailPanel" class="ledgerDetailPanel">
				
			   <table width="100%" border="0" class="bidOpenTable">
			        <col width="100px"/>
		       		<col />
		       		<col width="115px"/>
		       		<col />
		       		<col width="120px"/>
		       		<col />
		       		<tr>
		       		   	<td  style="border-left-color: #aaabb0;" class="rowItem" colspan="6">
							<div class="fLeft headTitle">开标信息(第${entity.batchNo}轮)</div>
							<div class="fLeft">		</div>						</td>
		       		</tr>	
				    <tr>
				      <td align="right">&nbsp;</td>
				      <td>&nbsp;</td>
				      <td align="right">编号：</td>
				      <td><input name="itemNo" type="text" id="itemNo" value="${entity.itemNo}"/></td>
				      <td align="right">版号：</td>
				      <td><input name="versionNo" type="text" id="versionNo" value="${entity.versionNo}"/></td>
		         </tr>
			      <tr>
				    <td align="right">招标项目名称：</td>
				    <td><input name="bidSectionName" type="text" id="bidSectionName" value="${entity.bidSectionName}"/></td>
				    <td align="right">工程名称：</td>
				    <td><input name="construction" type="text" id="construction"  value="${entity.construction}"/></td>
				    <td align="right">执行董事审阅后签名：</td>
				    <td><input name="sign" type="text" id="sign" value="${entity.sign}"/></td>
				  </tr>
				  <tr>
				    <td align="right">回标截止时间：</td>
				    <td><input name="lastReceDate" type="text" id="lastReceDate"  onfocus="WdatePicker()" class="Wdate"  value='<s:property  value="entity.lastReceDate" />' /></td>
				    <td align="right">开标时间：</td>
				    <td><input name="openDate" type="text" id="openDate"  onfocus="WdatePicker()" class="Wdate"/ value='<s:property  value="entity.openDate" />'></td>
				    <td align="right"> 邀请单位数量：</td>
				    <td><input name="inviteSupNum" type="text" id="inviteSupNum" value="${entity.inviteSupNum}"/>
			        个</td>
				  </tr>
				  <tr>
				    <td align="right">项目目标成本：</td>
				    <td><input name="projectCost" type="text" id="projectCost" value="${entity.projectCost}"/>
			        元</td>
				    <td align="right">建筑面积：</td>
				    <td><input name="builtArea" type="text" value="${entity.builtArea}" />
			        ㎡</td>
				    <td align="right">投标单位数量： </td>
				    <td><input name="receiveSupNum" type="text" id="receiveSupNum"  value="${entity.receiveSupNum}"/>
			        个</td>
				  </tr>
				  <tr>
				    <td align="right">标                底：</td>
				    <td><input name="basePrice" type="text" id="basePrice" value="${entity.basePrice}"/>
				    元</td>
				    <td align="right">&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				  </tr>
				  <tr>
				    <td colspan="6">&nbsp;</td>
			      </tr>
				</table>
                
       		   <table width="100%" border="0" class="opennerTable">
			     <col width="100px"/>
				 <col />
				 <col />
				 <col />
				 <col/>
			     <col />
			     <col/>
				 <col/>
                 <tr style="line-height:20px">
                   <td>&nbsp;</td>
                   <td align="center">1</td>
                   <td align="center">2</td>
                   <td align="center">3</td>
                   <td align="center">4</td>
                   <td align="center">5</td>
                   <td align="center">6</td>
                   <td align="center">7</td>
                 </tr>
                 <tr>
                   <td>参加开标的部门</td>
                   <td><input name="openDep1" type="text" id="openDep1" value="${entity.openDep1}" /></td>
                   <td><input name="openDep2" type="text" id="openDep2" value="${entity.openDep2}"/></td>
                   <td><input name="openDep3" type="text" id="openDep3" value="${entity.openDep3}"/></td>
                   <td><input name="openDep4" type="text" id="openDep4" value="${entity.openDep4}"/></td>
                   <td><input name="openDep5" type="text" id="openDep5" value="${entity.openDep5}"/></td>
                   <td><input name="openDep6" type="text" id="openDep6" value="${entity.openDep6}"/></td>
                   <td><input name="openDep7" type="text" id="openDep7" value="${entity.openDep7}"/></td>
                 </tr>
                 <tr>
                   <td>参加开标的人员</td>
                   <td><input name="openner1" type="text" id="openner1"  value="${entity.openner1}"/></td>
                   <td><input name="openner2" type="text" id="openner2"  value="${entity.openner2}"/></td>
                   <td><input name="openner3" type="text" id="openner3"  value="${entity.openner3}"/></td>
                   <td><input name="openner4" type="text" id="openner4"  value="${entity.openner4}"/></td>
                   <td><input name="openner5" type="text" id="openner5"  value="${entity.openner5}"/></td>
                   <td><input name="openner6" type="text" id="openner6"  value="${entity.openner6}"/></td>
                   <td><input name="openner7" type="text" id="openner7"  value="${entity.openner7}"/></td>
                 </tr>
               </table>
               
               <%--投标单位 --%>
		<div id="supListPanel" class="supList">
		   <table cellspacing="0" cellpadding="0" border="0" class="commonTable">
			 	<colgroup><col width="40">
			 	<col>
				<col width="110">
				<col width="110">
				<col width="110">
				<col width="110">
				<col width="110">
				<col width="110">
				<col width="110">

			</colgroup><thead>
			 	<tr>
			 		<th class="first">序号</th>
			 		<th title="投标单位名称"><div class="partHide  ellipsisDiv_full">投标单位</div></th>
			 		<th>报价总金额</th>
			 		<th>百分比</th>
			 		<th>排序</th>			 		
			 		<th>投标工期</th>
			 		<th>前一轮报价金额</th>	
			 		<th>两轮报价差额</th>		 		
			 		<th>备注</th>
			 	</tr>
				</thead>
				<tbody>				
				<s:iterator value="entity.bidOpenRecordSups" var="openRecordSup" status="stas">
					<!-- 显示概要 -->
					
					<tr id="supBrief_402829dd387e368c01387e407c620066" class="supBrief">
		  			  	<td style="text-align: center;">
		  			  		<div>
		  			  			<s:property value="#stas.index+1"/>
		  			  		</div>
		  			  	</td>
						<td title="${openRecordSup.supName}">
							<div class="partHide ellipsisDiv_full">
											${openRecordSup.supName}
											
							</div>
						</td>
						<td><input width="100" type="text" value="${openRecordSup.totalPrice}" name="bidOpenRecordSups[0].totalPrice"></td>
						<td><input width="100" type="text" value="${openRecordSup.percent}" name="bidOpenRecordSups[0].percent"></td>
						<td><input width="100" type="text" value="${openRecordSup.sequence}" name="bidOpenRecordSups[0].sequence"></td>
						<td><input width="100" type="text" value="${openRecordSup.timeLimit}" name="bidOpenRecordSups[0].timeLimit"></td>
						<td><input width="100" type="text" value="${openRecordSup.prePriece}" name="bidOpenRecordSups[0].prePriece"></td>
						<td><input width="100" type="text" value="${openRecordSup.difference}" name="bidOpenRecordSups[0].difference"></td>
						<td><textarea style="border:1px solid #999999;height:25px;" name="bidOpenRecordSups[0].contentDesc">${openRecordSup.contentDesc}</textarea></td>
					</tr>
				
				</s:iterator>
				</tbody>
			</table>
		 </div>
		
       	</div>
       	
