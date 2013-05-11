<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<div>
  <table id="supInfo" class="sup_table" cellspacing="0" cellpadding="0">
    <col width="30px"/>
	<col width="120px"/>
	<col width="200px"/>
	<col width="200px"/>
	<col width="150px"/>
	<col width="120px"/>
    <tr>
     <td rowspan="6" class="td_bold"  style="border-bottom:0px;border-right:0px;">&nbsp;1</td>
     <td rowspan="6" class="td_bold"  style="border-bottom:0px;" valign="middle">
     	企业基本信息
     	<div>
      	<span class="supEdit spanBasic">
      		<s:if test="supBasicId == null">
      			<input type="button" class="btn_new btn_blue_new addImg" title="添加业务联系单元" onclick="addContactor();" value="添加业务联系单元" style="width:120px;"/>
      		</s:if>
      		<s:else>
      			<input type="button" class="btn_new btn_green_new addImg" title="添加业务联系单元" onclick="addContactor();" value="添加业务联系单元" style="width:120px;"/>
      		</s:else>
      	</span>
      	</div>
     </td>
     <td >
       <span  class="td_bold"> 注册资金(万)：</span>
       <span class="supQuery spanBasic">${comRegMoney}</span>
       <span class="supEdit spanBasic"><s:textfield name="comRegMoney" validate="required" maxlength="25" class="txtbox"/></span>
     </td>
     <td>
      <span class="td_bold">注册年份：</span>
      <span class="supQuery spanBasic">${supDetails[0].comRegTime}</span>
      <span class="supEdit spanBasic"><s:textfield name="supDetails[0].comRegTime" validate="required" maxlength="25"/></span>
      </td>
     <td  rowspan="6" valign='top' style="height:100%;padding:0;">
        <div id="itemDiv" class="supQuery spanBasic" style="min-width: 150px;max-width:99%;overflow: auto;">
        	<%--这里是树 --%>
        </div>
        <span class="supEdit spanBasic">
	        <textarea id="supTypeName" class="txtbox pHand" onclick="doSupType('','${supTypeSn}');" style="height:300px;" validate="required" readonly="readonly" title="点击编辑">
	        	<p:code2name mapCodeName="mapToItemNames" value="supTypeSn" />
	        </textarea>
        </span>
     </td>
     <td rowspan="6"  style="border-bottom:0px;border-right: 1px solid #8c8f94;">
      <div id="uploadAttaContainer" class="sup_attach supQuery"  style="overflow:hidden;line-height: 18px;white-space: nowrap;white-space: nowrap;max-width: 200px;">
			<s:iterator value="appAttachFiles_bas">
				<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
				  	  <s:param name="fileName">${fileName}</s:param>
				  	  <s:param name="realFileName">${realFileName}</s:param>
				  	  <s:param name="bizModuleCd">${bizModuleCd}</s:param>
				  	  <s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
				<p/>
			</s:iterator>
		</div>
		<div class="supEdit spanBasic">
			<div id="addAttaFile_bas" title="文件大小不能超过20M">
				<input id="upload_bas" class="mailFile" type="file" onchange="fileChange(this,'_bas')" name="upload_bas"  size="1" />
			</div>
			<a style="text-decoration: underline;float: left" href="javascript:void(0)" class="mailBlueText" title="文件大小不能超过20M">添加附件</a>
			<div id="uploadAttaContainer_bas"  style="float:left;">
			  <input type="hidden" id="fileSn_bas" name="supDetails[0].comBasFileSn" value="${supDetails[0].comBasFileSn}" />
			  <s:iterator value="appAttachFiles_bas">
					<div class="attachment">
						<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
							<s:param name="fileName">${fileName}</s:param>
							<s:param name="realFileName">${realFileName}</s:param>
							<s:param name="bizModuleCd">${bizModuleCd}</s:param>
							<s:param name="id">${appAttachFileId}</s:param>
						</s:url>
						<a href="${down}">${realFileName}</a>&nbsp;<font onclick="deleteFile('${appAttachFileId}',this);" class=hand style=color:red;font-weight:bold>×</font>|
						<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
					</div>
				</s:iterator>				
			</div>
			<div id="attaContainer_bas" style="float:left;">
	        </div>
		 </div>
     </td>
    </tr>
    <tr>
     <td>
       <span class="td_bold">企业性质：</span>
       <span class="supQuery spanBasic">${supDetails[0].comProperties}</span>
       <span class="supEdit spanBasic"><s:textfield name="supDetails[0].comProperties" validate="required"/></span>
     </td>
     <td>
           <span class="td_bold">在职员工（人）：</span>
           <span class="supQuery spanBasic">${supDetails[0].comPersonNo}</span>
           <span class="supEdit spanBasic"><s:textfield name="supDetails[0].comPersonNo" validate="required"/></span>
     </td>
    </tr>
     <tr>
     <td>
      <span class="td_bold">法人代表：</span>
      <span class="supQuery spanBasic">${supDetails[0].supManager}</span>
      <span class="supEdit spanBasic"><s:textfield name="supDetails[0].supManager" validate="required"/></span>
      </td>
     <td>
       <span class="td_bold">联系方式：</span>
       <span class="supQuery spanBasic">${supDetails[0].supManagerTel}</span>
       <span class="supEdit spanBasic"><s:textfield name="supDetails[0].supManagerTel" validate="required"/></span>
     </td>
    </tr>
    <tr>
     <td>
      <span class="td_bold">经营网点分布：</span>
      <span class="supQuery spanBasic">${operateRange} </span>
      <div  class="sup_attach supQuery" style="float:right;">
	      <div id="uploadAttaContainer_oper">
			<s:iterator value="appAttachFiles_oper">
					<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
						<s:param name="fileName">${fileName}</s:param>
						<s:param name="realFileName">${realFileName}</s:param>
						<s:param name="bizModuleCd">${bizModuleCd}</s:param>	
						<s:param name="id">${appAttachFileId}</s:param>				
					</s:url>
					<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
					<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			</s:iterator>
		 </div>
	 </div>
     <span class="supEdit spanBasic"><s:textfield name="operateRange" validate="required"/></span>
     <div class="supEdit spanBasic">
		  <div id="addAttaFile_oper" title="文件大小不能超过20M">
			<input id="upload_oper" class="mailFile" type="file" onchange="fileChange(this,'_oper')" name="upload_oper" size="1" />
		 </div>
		  <a style="text-decoration: underline;float:left;" href="javascript:void(0)" class="mailBlueText" title="文件大小不能超过20M">添加附件</a>
		  <div id="uploadAttaContainer_oper" style="float:left;">
	       <input type="hidden" id="fileSn_oper" name="operateRangeSn" value="${operateRangeSn}" />
	       <s:iterator value="appAttachFiles_oper">
				<div class="attachment">
					<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
						<s:param name="fileName">${fileName}</s:param>
						<s:param name="realFileName">${realFileName}</s:param>
						<s:param name="bizModuleCd">${bizModuleCd}</s:param>	
						<s:param name="id">${appAttachFileId}</s:param>				
					</s:url>
					<a href="${down}">${realFileName}</a>&nbsp;<font onclick="deleteFile('${appAttachFileId}',this);" class=hand style=color:red;font-weight:bold>×</font>|
					<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
				</div>
			</s:iterator>
		 </div>
		 <div id="attaContainer_oper" style="float:left;">
		 </div>
	  </div>
      </td>
     <td>
       <span class="td_bold">场地面积：</span>
       <span class="supQuery spanBasic">${companyArea}</span>
       <span class="supEdit spanBasic"><s:textfield name="companyArea"/></span>
     </td>
    </tr>
    <tr>
    <td colspan="2" style="padding:0;">
	    <table style="width:100%;top:0px;">
	    <tbody>
	    <s:iterator value="supContactors" status="st">
	      <input type="hidden" name="supContactors[<s:property value="#st.index"/>].supContactorId" value="${supContactorId}"/>
	      <input type="hidden" name="supContactors[<s:property value="#st.index"/>].recordVersion" value="${recordVersion}"/>
	     <tr class="cont pd-chill-tip" title="<p:code2name mapCodeName="mapToContract" value="supContactorId" />">
	      <td colspan="2" style="border:0px;" class="td_bold">
	      <img src="${ctx}/resources/images/common/point_6_6.png"/>&nbsp;联系人[<s:property value="#st.index+1"/>]
	       &nbsp;&nbsp;<span class="supQuery spanBasic">${area}</span>
	      </td>
	     </tr>
	     <tr class="pd-chill-tip" title="<p:code2name mapCodeName="mapToContract" value="supContactorId" />">
	       <td width="50%" style="border:0px;">
	         <span class="td_bold" style="width:50px;padding-left:18px;">姓名：</span>
	         <span class="supQuery spanBasic">${name}</span>
	         <span class="supEdit spanBasic">
	           <input type="text" name="supContactors[<s:property value="#st.index"/>].name" value="${name}"/>
	         </span>
	       </td>
	       <td width="50%" style="border:0px;">
	         <span class="td_bold" style="width:50px;"> 区域/职位：</span>
	          <span class="supEdit spanBasic">
	           <input type="text" name="supContactors[<s:property value="#st.index"/>].area" value="${area}"/>
	         </span>
	       </td>
	      </tr>
	      <tr class="pd-chill-tip" title="<p:code2name mapCodeName="mapToContract" value="supContactorId" />" style="border:1px;">
	       <td width="50%">
	         	<span class="td_bold" style="width:50px;padding-left:18px;">手机：</span>
	         	<span class="supQuery spanBasic">${telephone}</span>
	         	<span class="supEdit spanBasic">
	           		<input type="text" name="supContactors[<s:property value="#st.index"/>].telephone" value="${telephone}"/>
	         	</span>
	        </td>
	        <td>
		        <span class="td_bold" style="width:50px;">电子邮件：</span>
		        <span class="supQuery spanBasic">${EMail}</span>
		        <span class="supEdit spanBasic">
		           <input type="text" name="supContactors[<s:property value="#st.index"/>].EMail" value="${EMail}"/>
		     	</span>
	        </td>
	       </tr>
	    </s:iterator>
	    </tbody>
	    </table>
	    <div id="contact_div">
		    <div id="tr_contact${fn:length(supContactors)}" class="cont supEdit spanBasic">
			  <table width="100%;">
			     <tr>
			     <td style="width:50px;">姓名</td><td><input type="text" name="supContactors[${fn:length(supContactors)}].name"/></td>
			     <td style="width:50px;">区域</td><td><input type="text" name="supContactors[${fn:length(supContactors)}].area"/></td>
			     </tr>
			     <tr>
			     <td style="width:50px;">手机</td><td><input type="text" name="supContactors[${fn:length(supContactors)}].telephone"/></td>
			     <td style="width:50px;">e-mail</td><td><input type="text" name="supContactors[${fn:length(supContactors)}].EMail"/></td>
			     </tr>
			     <tr>
			      <td colspan="4" >
			        <button onclick="doCancel(this);" style="display:none;" class="btn_red_35_20" id="DelBtn" type="button">删除</button>
			      </td>
			     </tr>
			  </table>
		    </div>
   	 	</div>
    </td>
    </tr>
    <tr style="height:25px;">
     	<td style="border-bottom:0px;" colspan="2">
      <span class="td_bold">地址：</span>
      <span class="supQuery spanBasic">${supDetails[0].address}</span>
      <span class="supEdit spanBasic"><s:textfield name="supDetails[0].address" maxlength="200" cssClass="txtbox"/></span>
      	</td>
    </tr>
    <tr style="height:1px;;">
      <td colspan="6" style="border-bottom:1px solid #8c8f94;" ></td>
    </tr>
    <tr>
    <td rowspan="2" class="td_bold"  style="border-bottom:0px;border-right:0px;">&nbsp;2</td>
    <td rowspan="2" class="td_bold" style="border-bottom:0px;">企业经营管理范围</td>
    <td colspan="3">
      <span class="td_bold">主营：</span>
      <span class="supQuery spanBasic">${supDetails[0].comMainPro}</span>
      <span class="supEdit spanBasic"><s:textfield name="supDetails[0].comMainPro" maxlength="400" cssClass="txtbox" validate="required"/></span>
    </td>
    <td rowspan="2" style="border-bottom:0px;border-right: 1px solid #8c8f94;">
    
      	<div class="sup_attach supQuery">
			<s:iterator value="appAttachFiles_rang">
				<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>	
					<s:param name="id">${appAttachFileId}</s:param>				
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			</s:iterator>
	 	</div>
	 	
		<div class="supEdit spanBasic">
		  	<div id="addAttaFile_rang" title="文件大小不能超过20M">
				<input id="upload_rang" class="mailFile" type="file" onchange="fileChange(this,'_rang')" name="upload_rang" size="1" />
			</div>
		  	<a style="text-decoration: underline;float:left;" href="javascript:void(0)" class="mailBlueText" title="文件大小不能超过20M">添加附件</a>
		  	<div id="uploadAttaContainer_rang" style="float:left;">
	       		<input type="hidden" id="fileSn_rang" name="supDetails[0].comRangFileSn" value="${supDetails[0].comRangFileSn}" />
	       		<s:iterator value="appAttachFiles_rang">
				<div class="attachment">
					<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
						<s:param name="fileName">${fileName}</s:param>
						<s:param name="realFileName">${realFileName}</s:param>
						<s:param name="bizModuleCd">${bizModuleCd}</s:param>	
						<s:param name="id">${appAttachFileId}</s:param>				
					</s:url>
					<a href="${down}">${realFileName}</a>&nbsp;<font onclick="deleteFile('${appAttachFileId}',this);" class=hand style=color:red;font-weight:bold>×</font>|
					<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
				</div>
				</s:iterator>
		 	</div>
	     	<div id="attaContainer_rang" style="float:left;">
		 	</div>
	 	</div>
    </td>
    </tr>
    <tr>
    <td colspan="3" style="border-bottom:0px;">
    <span class="td_bold">兼营：</span>
    <span class="supQuery spanBasic">${supDetails[0].comSecoPro}</span>
    <span class="supEdit spanBasic"><s:textfield  name="supDetails[0].comSecoPro" maxlength="400" cssClass="txtbox"/></span>
    </td>
    </tr>
    <tr style="height:1px;;">
      <td colspan="6" style="border-bottom:1px solid #8c8f94;" ></td>
    </tr>
    <tr>
      <td class="td_bold" style="border-bottom:0px;border-right:0px;">&nbsp;3</td>
      <td class="td_bold" style="border-bottom:0px;">企业资信等级</td>
      <td colspan="3" style="border-bottom:0px;">
        <span class="supQuery spanBasic">${supDetails[0].comZxGrade}</span>
        <span class="supEdit spanBasic"><s:textfield cssClass="txtbox" name="supDetails[0].comZxGrade" maxlength="50" validate="required" alt="ShowPrompt"/></span>
      </td>
      <td style="border-bottom:0px;border-right: 1px solid #8c8f94;">
       <div id="uploadAttaContainer_zx" class="sup_attach supQuery">
		<s:iterator value="appAttachFiles_zx">
				<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
		</s:iterator>
	   	</div>
	   	<div class="supEdit spanBasic">
	    <div id="addAttaFile_zx" title="文件大小不能超过20M" style="float:left;">
			<input id="upload_zx" class="mailFile" type="file" onchange="fileChange(this,'_zx')" name="upload_zx" size="1" />
		</div>
		<a style="text-decoration: underline;float:left;" href="javascript:void(0)" class="mailBlueText" title="文件大小不能超过20M">添加附件</a>
       	<div id="uploadAttaContainer_zx" style="float:left;">
	       <input type="hidden" id="fileSn_zx" name="supDetails[0].comZxFileSn"  value="${supDetails[0].comZxFileSn}" />
	       <s:iterator value="appAttachFiles_zx">
				<div class="attachment">
					<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
						<s:param name="fileName">${fileName}</s:param>
						<s:param name="realFileName">${realFileName}</s:param>
						<s:param name="bizModuleCd">${bizModuleCd}</s:param>
						<s:param name="id">${appAttachFileId}</s:param>
					</s:url>
					<a href="${down}">${realFileName}</a>&nbsp;<font onclick="deleteFile('${appAttachFileId}',this);" class=hand style=color:red;font-weight:bold>×</font>|
					<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
				</div>
			</s:iterator>
		 </div>
	     <div id="attaContainer_zx" style="float:left;">
		 </div>
	   </div>
     </td>
    </tr>
    <tr style="height:1px;;">
      <td colspan="6" style="border-bottom:1px solid #8c8f94;" ></td>
    </tr>
    <tr>
      <td class="td_bold" style="border-bottom:0px;border-right:0px;">&nbsp;4</td>
      <td class="td_bold" style="border-bottom:0px">企业资质等级</td>
      <td colspan="3" style="border-bottom:0px">
       <span class="supQuery spanBasic">${comZzGrade}<br></br>${supDetails[0].comZzGrade2}</span>
       <span class="supEdit spanBasic"><s:textfield cssClass="txtbox" name="comZzGrade" maxlength="200" validate="required" alt="ShowPrompt"/></span>
      </td>
      <td style="border-bottom:0px;border-right: 1px solid #8c8f94;">
      <div id="uploadAttaContainer_zz" class="sup_attach supQuery">
		<s:iterator value="appAttachFiles_zz">
				<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
		</s:iterator>
	 </div>
	 <div class="supEdit spanBasic">
	  <div id="addAttaFile_zz" title="文件大小不能超过20M" style="float:left;">
		<input id="upload_zz" class="mailFile" type="file" onchange="fileChange(this,'_zz')" name="upload_zz" size="1" />
		</div>
		 <a style="text-decoration: underline;float:left;" href="javascript:void(0)" class="mailBlueText" title="文件大小不能超过20M">添加附件</a>
	      <div id="uploadAttaContainer_zz" style="float:left;">
	        <input type="hidden" id="fileSn_zz" name="supDetails[0].comZzFileSn" value="${supDetails[0].comZzFileSn}" />
			<s:iterator value="appAttachFiles_zz">
				<div class="attachment">
					<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
						<s:param name="fileName">${fileName}</s:param>
						<s:param name="realFileName">${realFileName}</s:param>
						<s:param name="bizModuleCd">${bizModuleCd}</s:param>
						<s:param name="id">${appAttachFileId}</s:param>
					</s:url>
					<a href="${down}">${realFileName}</a>&nbsp;<font onclick="deleteFile('${appAttachFileId}',this);" class=hand style=color:red;font-weight:bold>×</font>|
					<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
				</div>
			</s:iterator>
		 </div>
		<div id="attaContainer_zz" style="float:left;">
		   </div>
	 </div>
      </td>
    </tr>
    <tr style="height:1px;;">
      <td colspan="6" style="border-bottom:1px solid #8c8f94;" ></td>
    </tr>
    <tr>
      <td class="td_bold" style="border-bottom:0px;border-right:0px;">&nbsp;5</td>
      <td class="td_bold" style="border-bottom:0px">企业安全生产许可证</td>
      <td colspan="2" style="border-bottom:0px">
      <span class="supQuery spanBasic">${supDetails[0].comSaftCert}</span>
      <span class="supEdit spanBasic"><s:textfield name="supDetails[0].comSaftCert" maxlength="200" cssClass="txtbox" validate="required" alt="ShowPrompt"/></span>
      </td>
      <td style="border-bottom:0px">
      <span class="td_bold">有效期：</span>
      <span class="supQuery spanBasic">${supDetails[0].comSaftDate}</span>
      <span class="supEdit spanBasic"><s:textfield name="supDetails[0].comSaftDate" maxlength="25"/></span>
      </td>
      <td style="border-bottom:0px;border-right: 1px solid #8c8f94;">
       <div id="uploadAttaContainer_safe" class="sup_attach supQuery">
        <s:iterator value="appAttachFiles_save">
				<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			</s:iterator>
	   </div>
	   <div class="supEdit spanBasic">
	    <div id="addAttaFile_safe" title="文件大小不能超过20M" style="float:left;">
		<input id="upload_safe" class="mailFile" type="file" onchange="fileChange(this,'_safe')" name="upload_safe" size="1" />
		 </div>
		 <a style="text-decoration: underline;float:left;" href="javascript:void(0)" class="mailBlueText" title="文件大小不能超过20M">添加附件</a>
	     
	     <div id="uploadAttaContainer_safe" style="float:left;">
	       <input type="hidden" id="fileSn_safe" name="supDetails[0].comSaveFileSn" value="${supDetails[0].comSaveFileSn}" />
	       <s:iterator value="appAttachFiles_save">
				<div class="attachment">
					<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
						<s:param name="fileName">${fileName}</s:param>
						<s:param name="realFileName">${realFileName}</s:param>
						<s:param name="bizModuleCd">${bizModuleCd}</s:param>
						<s:param name="id">${appAttachFileId}</s:param>
					</s:url>
					<a href="${down}">${realFileName}</a>&nbsp;<font onclick="deleteFile('${appAttachFileId}',this);" class=hand style=color:red;font-weight:bold>×</font>|
					<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
				</div>
			</s:iterator>
		 </div>
	     <div id="attaContainer_safe" style="float:left;">
		 </div>
	   </div>
     </td>
    </tr>  
    <tr style="height:1px;;">
      <td colspan="6" style="border-bottom:1px solid #8c8f94;" ></td>
    </tr>
    <tr>
      <td class="td_bold" style="border-bottom:0px;border-right:0px;">&nbsp;6</td>
      <td class="td_bold" style="border-bottom:0px">质量、环境及职业健康安全认证</td>
      <td colspan="3" style="border-bottom:0px">
       <span class="supQuery spanBasic">
         <s:if test="supDetails[0].comQualCert1==1">质量&nbsp;&nbsp;</s:if>
	     <s:if test="supDetails[0].comQualCert2==2">环境&nbsp;&nbsp;</s:if>
	     <s:if test="supDetails[0].comQualCert3==3">职业健康安全&nbsp;&nbsp;</s:if>
	     <s:if test="supDetails[0].comQualCert4==4">无</s:if>
       </span>
       <span class="supEdit spanBasic">
	         <input type="checkbox" name="supDetails[0].comQualCert1"  value="1" <s:if test="supDetails[0].comQualCert1==1">checked</s:if> />质量&nbsp;&nbsp;
	        <input type="checkbox" name="supDetails[0].comQualCert2"  value="2" <s:if test="supDetails[0].comQualCert2==2">checked</s:if>/>环境&nbsp;&nbsp;
	        <input type="checkbox" name="supDetails[0].comQualCert3"  value="3" <s:if test="supDetails[0].comQualCert3==3">checked</s:if>/>职业健康安全&nbsp;&nbsp;
	        <input type="checkbox" name="supDetails[0].comQualCert4"  value="4" <s:if test="supDetails[0].comQualCert4==4">checked</s:if>/>无
       </span>
      </td>
      <td style="border-bottom:0px;border-right: 1px solid #8c8f94;">
      <div id="uploadAttaContainer_qual" class="sup_attach supQuery">
       <s:iterator value="appAttachFiles_qual">
			<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
		
		</s:iterator>
	 </div>
	 <div class="supEdit spanBasic">
		   <div id="addAttaFile_qual" title="文件大小不能超过20M" style="float:left;">
							<input id="upload_qual" class="mailFile" type="file" onchange="fileChange(this,'_qual')" name="upload_qual" size="1" />
						</div>
						<a style="text-decoration: underline;float:left;" href="javascript:void(0)" class="mailBlueText" title="文件大小不能超过20M">添加附件</a>
	     
	     <div id="uploadAttaContainer_qual" style="float:left;">
	       <input type="hidden" id="fileSn_qual" name="supDetails[0].comQualFileSn" value="${supDetails[0].comQualFileSn}" />
	       <s:iterator value="appAttachFiles_qual">
				<div class="attachment">
					<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
						<s:param name="fileName">${fileName}</s:param>
						<s:param name="realFileName">${realFileName}</s:param>
						<s:param name="bizModuleCd">${bizModuleCd}</s:param>
						<s:param name="id">${appAttachFileId}</s:param>
					</s:url>
					<a href="${down}">${realFileName}</a>&nbsp;<font onclick="deleteFile('${appAttachFileId}',this);" class=hand style=color:red;font-weight:bold>×</font>|
					<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
				</div>
			</s:iterator>
		 </div>
	     <div id="attaContainer_qual" style="float:left;">
		 </div>
	 </div>
      </td>
    </tr>
    <tr style="height:1px;;">
      <td colspan="6" style="border-bottom:1px solid #8c8f94;" ></td>
    </tr>
    <tr>
      <td class="td_bold" style="border-bottom:0px;border-right:0px;">&nbsp;7</td>
      <td class="td_bold" style="border-bottom:0px">企业或产品主要荣誉</td>
      <td colspan="3" style="border-bottom:0px;padding-top:8px;padding-bottom:8px;">
       <span class="supQuery spanBasic">${fn:replace(supDetails[0].comHonor,vEnter,"<br>")}</span>
       <span class="supEdit spanBasic">
         <s:textarea cssClass="txtAreaBox" name="supDetails[0].comHonor"/>
       </span>
      </td>
      <td style="border-bottom:0px;border-right: 1px solid #8c8f94;">
      <div id="uploadAttaContainer_hono" class="sup_attach supQuery">
       <s:iterator value="appAttachFiles_hono">
				<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
		</s:iterator>
	 </div>
	 <div class="supEdit spanBasic">
	   	<div id="addAttaFile_hono" title="文件大小不能超过20M" style="float:left;">
			<input id="upload_hono" class="mailFile" type="file" onchange="fileChange(this,'_hono')" name="upload_hono" size="1" />
		</div>
		<a style="text-decoration: underline;float:left;" href="javascript:void(0)" class="mailBlueText" title="文件大小不能超过20M">添加附件</a>
     
	     <div id="uploadAttaContainer_hono" style="float:left;">
	       <input type="hidden" id="fileSn_hono" name="supDetails[0].comHonoFileSn" value="${supDetails[0].comHonoFileSn}" />
	       <s:iterator value="appAttachFiles_hono">
				<div class="attachment">
					<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
						<s:param name="fileName">${fileName}</s:param>
						<s:param name="realFileName">${realFileName}</s:param>
						<s:param name="bizModuleCd">${bizModuleCd}</s:param>
						<s:param name="id">${appAttachFileId}</s:param>
					</s:url>
					<a href="${down}">${realFileName}</a>&nbsp;<font onclick="deleteFile('${appAttachFileId}',this);" class=hand style=color:red;font-weight:bold>×</font>|
					<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
				</div>
			</s:iterator>
		 </div>
	     <div id="attaContainer_hono" style="float:left;">
		 </div>
	 </div>
      </td>
    </tr>
   <tr style="height:1px;;">
      <td colspan="6" style="border-bottom:1px solid #8c8f94;" ></td>
    </tr>
    <tr>
      <td class="td_bold" style="border-bottom:0px;border-right:0px;">&nbsp;8</td>
      <td class="td_bold" style="border-bottom:0px">企业近三年经营收入</td>
      <td colspan="3" style="border-bottom:0px;padding-top:8px;padding-bottom:8px;">
        <span class="supQuery spanBasic">${fn:replace(supDetails[0].comFinSta,vEnter,"<br>")}</span>
        <span class="supEdit spanBasic"><s:textarea cssClass="txtAreaBox" name="supDetails[0].comFinSta" validate="required"/></span>
      </td>
      <td style="border-bottom:0px;border-right: 1px solid #8c8f94;">
       <div id="uploadAttaContainer_fin" class="sup_attach supQuery" style="line-height:18px;white-space: nowrap;white-space: nowrap;max-width: 200px;">
       <s:iterator value="appAttachFiles_fin">
			<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
				<s:param name="fileName">${fileName}</s:param>
				<s:param name="realFileName">${realFileName}</s:param>
				<s:param name="bizModuleCd">${bizModuleCd}</s:param>
				<s:param name="id">${appAttachFileId}</s:param>
			</s:url>
			<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
			<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			<p/>
		</s:iterator>
	  </div>
	  <div class="supEdit spanBasic">
		    <div id="addAttaFile_fin" title="文件大小不能超过20M" style="float:left;">
							<input id="upload_fin" class="mailFile" type="file" onchange="fileChange(this,'_fin')" name="upload_fin" size="1" />
						</div>
						<a style="text-decoration: underline;float:left;" href="javascript:void(0)" class="mailBlueText" title="文件大小不能超过20M">添加附件</a>
	     
	     <div id="uploadAttaContainer_fin" style="float:left;">
	       <input type="hidden" id="fileSn_fin" name="supDetails[0].comFinFileSn" value="${supDetails[0].comFinFileSn}" />
	       <s:iterator value="appAttachFiles_fin">
				<div class="attachment">
					<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
						<s:param name="fileName">${fileName}</s:param>
						<s:param name="realFileName">${realFileName}</s:param>
						<s:param name="bizModuleCd">${bizModuleCd}</s:param>
						<s:param name="id">${appAttachFileId}</s:param>
					</s:url>
					<a href="${down}">${realFileName}</a>&nbsp;<font onclick="deleteFile('${appAttachFileId}',this);" class=hand style=color:red;font-weight:bold>×</font>|
					<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
				</div>
			</s:iterator>
		 </div>
	     <div id="attaContainer_fin" style="float:left;">
		 </div>
	  </div>
      </td>
    </tr>
    <tr style="height:1px;;">
      <td colspan="6" style="border-bottom:1px solid #8c8f94;" ></td>
    </tr>
    <tr>
      <td class="td_bold" style="border-right:0px;border-bottom:0px #8c8f94;">&nbsp;9</td>
      <td class="td_bold"style="border-bottom:0px #8c8f94;">类似工程业绩<p/><font color="red">(&nbsp;至多200个汉字&nbsp;)</font></td>
      <td colspan="3"style="border-bottom:0px #8c8f94;padding-top:8px;padding-bottom:8px;">
        <span class="supQuery spanBasic">${fn:replace(supDetails[0].comProPerf,vEnter,"<br>")}</span>
        <span class="supEdit spanBasic"><s:textarea cssClass="txtAreaBox" name="supDetails[0].comProPerf" validate="required"/></span>
      </td>
      <td style="border-right: 0px solid #8c8f94;border-bottom:0px #8c8f94;">
      <div id="uploadAttaContainer_pro" class="sup_attach supQuery" style="line-height: 18px;white-space: nowrap;white-space: nowrap;max-width: 200px;">
       <s:iterator value="appAttachFiles_pro">
			<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
				<s:param name="fileName">${fileName}</s:param>
				<s:param name="realFileName">${realFileName}</s:param>
				<s:param name="bizModuleCd">${bizModuleCd}</s:param>
				<s:param name="id">${appAttachFileId}</s:param>
			</s:url>
			<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
			<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			<p/>
		</s:iterator>
		</div>
		<div class="supEdit spanBasic">
		  <div id="addAttaFile_pro" title="文件大小不能超过20M" style="float:left;">
				<input id="upload_pro" class="mailFile" type="file" onchange="fileChange(this,'_pro')" name="upload_pro" size="1" />
			</div>
			<a style="text-decoration: underline;float:left;" href="javascript:void(0)" class="mailBlueText" title="文件大小不能超过20M">添加附件</a>
   
		     <div id="uploadAttaContainer_pro" style="float:left;">
		       <input type="hidden" id="fileSn_pro" name="supDetails[0].comProFileSn" value="${supDetails[0].comProFileSn}" />
		       <s:iterator value="appAttachFiles_pro">
					<div class="attachment">
						<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
							<s:param name="fileName">${fileName}</s:param>
							<s:param name="realFileName">${realFileName}</s:param>
							<s:param name="bizModuleCd">${bizModuleCd}</s:param>
							<s:param name="id">${appAttachFileId}</s:param>
						</s:url>
						<a href="${down}">${realFileName}</a>&nbsp;<font onclick="deleteFile('${appAttachFileId}',this);" class=hand style=color:red;font-weight:bold>×</font>|
						<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
					</div>
				</s:iterator>
			 </div>
		     <div id="attaContainer_pro" style="float:left;">
			 </div>
				</div>
      </td>
    </tr>
    <tr style="height:1px;;">
      <td colspan="6" style="border-bottom:1px solid #8c8f94;" ></td>
    </tr>
    <tr>
      <td class="td_bold" style="border-right:0px;border-bottom:0px #8c8f94;">&nbsp;10</td>
      <td class="td_bold" style="border-bottom:0px">宝龙业绩</td>
      <td colspan="3" style="border-bottom:0px">
      <span class="supQuery spanBasic">
         <p:code2name mapCodeName="mapCooperated" value="supDetails[0].cooperated" />
         <br></br>
         ${supDetails[0].supChecView}
      </span>
      <span class="supEdit spanBasic">
        <s:radio list="mapCooperated" listKey="key" listValue="value" name="supDetails[0].cooperated" id="cooperated"></s:radio>
        <s:textfield cssClass="txtbox" name="supDetails[0].supChecView" validate="required"/>
      </span> 
      </td>
      <td style="border-bottom:0px;border-right: 1px solid #8c8f94;">
      <div id="uploadAttaContainer_mana" class="sup_attach supQuery">
        <s:iterator value="appAttachFiles_cop">
				<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
		</s:iterator>
		</div>
		<div class="supEdit spanBasic">
			 <div id="addAttaFile_cop" title="文件大小不能超过20M" style="float:left;">
					<input id="upload_cop" class="mailFile" type="file" onchange="fileChange(this,'_cop')" name="upload_cop" size="1" />
				</div>
				<a style="text-decoration: underline;float:left;" href="javascript:void(0)" class="mailBlueText" title="文件大小不能超过20M">添加附件</a>
    
		     <div id="uploadAttaContainer_cop" style="float:left;">
		       <input type="hidden" id="fileSn_cop" name="supDetails[0].cooperatedSn" value="${supDetails[0].cooperatedSn}" />
		        <s:iterator value="appAttachFiles_cop">
					<div class="attachment">
						<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
							<s:param name="fileName">${fileName}</s:param>
							<s:param name="realFileName">${realFileName}</s:param>
							<s:param name="bizModuleCd">${bizModuleCd}</s:param>
							<s:param name="id">${appAttachFileId}</s:param>
						</s:url>
						<a href="${down}">${realFileName}</a>&nbsp;<font onclick="deleteFile('${appAttachFileId}',this);" class=hand style=color:red;font-weight:bold>×</font>|
						<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
					</div>
				</s:iterator>
			 </div>
		     <div id="attaContainer_cop" style="float:left;">
			 </div>
		</div>
      </td>
    </tr>
    <tr style="height:1px;;">
      <td colspan="6" style="border-bottom:1px solid #8c8f94;" ></td>
    </tr>
    <tr>
       <td class="td_bold" style="border-right:0px;border-bottom:1px #8c8f94;">&nbsp;11</td>
      <td class="td_bold"style="border-bottom:1px #8c8f94;">备注</td>
	   <td colspan="3" style="border-bottom:1px #8c8f94;">
	   <span class="supQuery spanBasic">${remark}</span>
	   <span class="supEdit spanBasic"><input type="text" name="remark" value="${remark}"/></span>
	   </td>
	   <td style="border-right:1px #8c8f94;border-bottom:1px #8c8f94;">
	   </td>
  </tr>
  </table>
</div>
<div id="div_audit" style="padding-top:15px;">
 <table  class="sup_table" cellspacing="0" cellpadding="0"style="background-color:#e4e7ec;overflow:hidden;">
  <tr style="height:10px;background-color: #878787;">
  <td colspan="4"></td>
  </tr>
  <tr>
   <td width="30px" class="td_bold"  style="border-right:0px;" rowspan="6">&nbsp;12</td>
   <td  width="100px" class="td_bold"  rowspan="6">审核信息</td>
   <td>
   <span class="td_bold">资料验证：</span>
    <p:code2name mapCodeName="mapSupInfoVeri" value="supDetails[0].supInfoVeri" />
    
     <span class="supEdit spanAudit">
       <s:radio list="mapSupInfoVeri" listKey="key" listValue="value" name="supDetails[0].supInfoVeri" id="supInfoVeri"></s:radio>
     </span>
   </td>
   <td width="150px" style="border-right:1px solid #8c8f94;"></td>
  </tr>
  <tr>
   <td><span class="td_bold">现场考察结果：</span>
   <span class="supQuery spanAudit">
     <p:code2name mapCodeName="mapSupExamResu" value="supExamResu" />
    </span>
    <span class="supEdit spanAudit">
      <s:radio list="mapSupExamResu" listKey="key" listValue="value" name="supExamResu" id="supExamResu"></s:radio>
    </span>
    </td>
   <td style="border-right:1px solid #8c8f94;">
     <div class="sup_attach supQuery spanAudit">
       <s:iterator value="appAttachFiles_exam">
			<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}"  title="${realFileName}">${fn:substring(realFileName,0,8)}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			
		</s:iterator>
		</div>
	<div class="supEdit spanAudit">
		  <div id="addAttaFile_exam" title="文件大小不能超过20M" style="float:left;">
						<input id="upload_exam" class="mailFile" type="file" onchange="fileChange(this,'_exam')" name="upload_exam" size="1" />
					</div>
					<a style="text-decoration: underline;float:left;" href="javascript:void(0)" class="mailBlueText" title="文件大小不能超过20M">添加附件</a>
     
     <div id="uploadAttaContainer_exam" style="float:left;">
       <input type="hidden" id="fileSn_exam" name="supDetails[0].comExamFileSn" value="${supDetails[0].comExamFileSn}" />
        <s:iterator value="appAttachFiles_exam">
			<div class="attachment">
				<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}">${fn:substring(realFileName,0,8)}</a>&nbsp;<font onclick="deleteFile('${appAttachFileId}',this);" class=hand style=color:red;font-weight:bold>×</font>|
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			</div>
		</s:iterator>
	 </div>
     <div id="attaContainer_exam" style="float:left;">
	 </div>
	</div>
   </td>
  </tr>
  <tr>
  <td>
  <span class="td_bold">考察人员：</span>
  <span class="supQuery spanAudit">
  ${examResuPerson}
  </span>
  <span class="supEdit spanAudit">
  <input type="text" id="examResuPerson" name="examResuPerson" value="${examResuPerson}"/> 
  </span>
  </td>
  <td  style="border-right:1px solid #8c8f94;"></td>
  </tr>
  <tr>
  <td>
  <span class="td_bold">考察内容：</span>
  <span class="supQuery spanAudit">
  ${examResuContent}
  </span>
  <span class="supEdit spanAudit">
  <textarea class="txtAreaBox" id="examResuContent" name="examResuContent">${examResuContent}</textarea>
  </span>
  </td>
  <td style="border-right:1px solid #8c8f94;"></td>
  </tr>
  <tr>
   <td>
    <span class="td_bold">履约评估 ：</span>
     <span class="supQuery spanAudit">
       <p:code2name mapCodeName="mapRealEvaluate" value="supDetails[0].supEvaluate" />
      </span>
      <span class="supEdit spanAudit">
        <s:radio list="mapRealEvaluate" listKey="key" listValue="value" name="supDetails[0].supEvaluate" id="supEvaluate"></s:radio>
      </span>
    </td>
   <td style="border-right:1px solid #8c8f94;">
    <div class="sup_attach supQuery spanAudit">
    <s:iterator value="appAttachFiles_eval">
			<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}"  title="${realFileName}">${fn:substring(realFileName,0,8)}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			
		</s:iterator>
	 </div>
	 <div class="supEdit spanAudit">
	   <div id="addAttaFile_eval" title="文件大小不能超过20M" style="float:left;">
			<input id="upload_eval" class="mailFile" type="file" onchange="fileChange(this,'_eval')" name="upload_eval" size="1" />
		</div>
		<a style="text-decoration: underline;float:left;" href="javascript:void(0)" class="mailBlueText" title="文件大小不能超过20M">添加附件</a>
     
	     <div id="uploadAttaContainer_eval" style="float:left;">
	       <input type="hidden" id="fileSn_eval" name="supDetails[0].comEvalFileSn" value="${supDetails[0].comEvalFileSn}" />
	       <s:iterator value="appAttachFiles_eval">
				<div class="attachment">
					<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
						<s:param name="fileName">${fileName}</s:param>
						<s:param name="realFileName">${realFileName}</s:param>
						<s:param name="bizModuleCd">${bizModuleCd}</s:param>
						<s:param name="id">${appAttachFileId}</s:param>
					</s:url>
					<a href="${down}">${fn:substring(realFileName,0,8)}</a>&nbsp;<font onclick="deleteFile('${appAttachFileId}',this);" class=hand style=color:red;font-weight:bold>×</font>|
					<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
				</div>
			</s:iterator>
		 </div>
	     <div id="attaContainer_eval" style="float:left;">
		 </div>
	 </div>
    </td>
  </tr>
  <tr>
   <td>
   <span class="td_bold">供方级别：</span>
   <span class="spanAudit">
     <p:code2name mapCodeName="mapSupEvaluate" value="supManaEval" />
   </span>
 <%--
   <span class="supEdit spanAudit">
      <s:radio list="mapSupEvaluate" listKey="key" listValue="value" name="supManaEval" id="supManaEval"></s:radio>
  </span>
 --%>
   </td>
   <td style="border-right:1px solid #8c8f94;">
     <div class="sup_attach supQuery spanAudit">
     <s:iterator value="appAttachFiles_main">
			<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${fn:substring(realFileName,0,8)}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			
		</s:iterator>
	</div>
	<div class="supEdit spanAudit">
	 <div id="addAttaFile_main" title="文件大小不能超过20M" style="float:left;">
			<input id="upload_main" class="mailFile" type="file" onchange="fileChange(this,'_main')" name="upload_main" size="1" />
		</div>
		<a style="text-decoration: underline;float:left;" href="javascript:void(0)" class="mailBlueText" title="文件大小不能超过20M">添加附件</a>
	 
	     <div id="uploadAttaContainer_main" style="float:left;">
	       <input type="hidden" id="fileSn_main" name="supDetails[0].comMainFileSn" value="${supDetails[0].comMainFileSn}" />
	        <s:iterator value="appAttachFiles_main">
				<div class="attachment">
					<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
						<s:param name="fileName">${fileName}</s:param>
						<s:param name="realFileName">${realFileName}</s:param>
						<s:param name="bizModuleCd">${bizModuleCd}</s:param>
						<s:param name="id">${appAttachFileId}</s:param>
					</s:url>
					<a href="${down}">${fn:substring(realFileName,0,8)}</a>&nbsp;<font onclick="deleteFile('${appAttachFileId}',this);" class=hand style=color:red;font-weight:bold>×</font>|
					<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
				</div>
			</s:iterator>
		 </div>
	     <div id="attaContainer_main" style="float:left;">
		 </div>
	</div>
   </td>
  </tr>
 </table>
 <s:if test="supApproveReses!=null&&supApproveReses.size()>0">
	 <table class="sup_table" cellspacing="0" cellpadding="0"style="background-color:#e4e7ec;overflow:hidden;">
	  <tr>
	   <th>类型</th>
	   <th>时间</th>
	   <th>项目名称</th>
	   <th>工程名称</th>
	   <!-- th>签署时间</th -->
	   <th>相关网批</th>
	  </tr>
	   <s:set var="haveVal1">true</s:set>
	   <s:set var="haveVal2">true</s:set>
	   <s:set var="haveVal3">true</s:set>
	  <s:iterator value="supApproveReses" status="st">
	  <tr>
	   <s:if test="invitationBidSize!=0&&#st.index<invitationBidSize&&#haveVal1=='true'">
	    <td align="center" rowspan="${invitationBidSize}">邀标&nbsp;(${invitationBidSize}条)</td>
	    <s:set var="haveVal1">false</s:set>
	   </s:if>
	   <s:elseif test="bidSize!=0&&invitationBidSize<=#st.index&&#haveVal2=='true'">
	    <td align="center" rowspan="${bidSize}">定标&nbsp;(${bidSize}条)</td>
	    <s:set var="haveVal2">false</s:set>
	   </s:elseif>
	   <s:elseif test="contractSize!=0&&(invitationBidSize+bidSize)<=#st.index&&#haveVal3=='true'">
	   <td align="center" rowspan="${contractSize}">合同&nbsp;(${contractSize}条)</td>
	   <s:set var="haveVal3">false</s:set>
	   </s:elseif>
	   <td align="center"><s:date name ="appCompTime" format="yyyy-MM-dd"/></td>
	   <td align="center">${projectName}</td>
	   <td align="center">${appProjectName}</td>
	   <td align="center"><div onclick="parent.showAll('${ctx}/res/res-approve-info.action?id=${resApproveInfoId}','resApprove');" style="cursor: pointer; text-decoration: underline;">审批内容</div></td>
	  </tr>
	  </s:iterator>
	 </table>
 </s:if>
</div>